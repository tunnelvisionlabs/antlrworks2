/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Document;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentTextUtilities;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.NotSetTransition;
import org.antlr.v4.runtime.atn.PredictionContext;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.atn.WildcardTransition;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.tool.Rule;
import org.antlr.works.editor.antlr4.classification.TaggerTokenSource;
import org.antlr.works.editor.antlr4.completion.AbstractCompletionQuery;
import org.antlr.works.editor.antlr4.completion.CaretReachedException;
import org.antlr.works.editor.antlr4.completion.CaretToken;
import org.antlr.works.editor.antlr4.completion.CodeCompletionErrorStrategy;
import org.antlr.works.editor.antlr4.completion.CodeCompletionTokenSource;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.codemodel.AttributeModel;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ActionExpressionContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ActionScopeExpressionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParserAnchorListener;
import org.antlr.works.editor.grammar.experimental.GrammarReferenceAnchors;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.openide.util.Exceptions;

/**
 *
 * @author Sam Harwell
 */
public final class GrammarCompletionQuery extends AbstractCompletionQuery {
    // -J-Dorg.antlr.works.editor.grammar.completion.GrammarCompletionQuery.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GrammarCompletionQuery.class.getName());

    private boolean possibleReference;
    private boolean possibleKeyword;

    /*package*/ GrammarCompletionQuery(GrammarCompletionProvider completionProvider, int queryType, int caretOffset, boolean hasTask, boolean extend) {
        super(completionProvider, queryType, caretOffset, hasTask, extend);
    }

    @Override
    public GrammarCompletionProvider getCompletionProvider() {
        return (GrammarCompletionProvider)super.getCompletionProvider();
    }

    @Override
    protected boolean isQueryContext(Document doc, int caretOffset) {
        return getCompletionProvider().isContext(getComponent(), caretOffset, true, true);
    }

    @Override
    protected Task getTask(BaseDocument document) {
        return new TaskImpl(document);
    }

    /*package*/ static boolean isGrammarIdentifierPart(String typedText) {
        for (int i = 0; i < typedText.length(); i++) {
            if (!Character.isJavaIdentifierPart(typedText.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected CompletionItem createDeclarationCompletionItem(Document document, TrackingPositionRegion applicableTo) {
        return new DeclarationCompletionItem(document, applicableTo);
    }

    private class TaskImpl extends Task {

        public TaskImpl(BaseDocument document) {
            super(document);
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionBlock, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommandName, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommandExpr, version=1),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleref, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_terminal, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionExpression, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionScopeExpression, version=0),
        })
        protected void runImpl(BaseDocument document) {
            results = new ArrayList<CompletionItem>();
            possibleDeclaration = true;
            possibleReference = true;
            possibleKeyword = true;

            if (document == null) {
                return;
            }

            ParserTaskManager taskManager = getParserTaskManager();
            if (taskManager == null) {
                return;
            }

            // Add context items (labels, etc). Use anchor points to optimize information gathering.
            VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(document);
            DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();

            Map<RuleContext<Token>, CaretReachedException> parseTrees = null;
            CaretToken caretToken = null;

            final Collection<Description> rules = new ArrayList<Description>();

            GrammarReferenceAnchors anchors = findNearestAnchors(taskManager, snapshot);
            int grammarType = anchors.getGrammarType();
            final Anchor previous = anchors.getPrevious();

            GrammarForestParser forestParser;
            if (previous != null) {
                switch (previous.getRule()) {
                case GrammarParser.RULE_ruleSpec:
                    forestParser = GrammarForestParser.RULES;
                    break;

                default:
                    forestParser = null;
                    break;
                }
            } else {
                forestParser = GrammarForestParser.GRAMMAR_SPEC;
            }

            if (forestParser != null) {
                Tagger<TokenTag<Token>> tagger = getTagger(taskManager, snapshot);

                final OffsetRegion region = getParseRegion(snapshot, anchors);
                if (LOGGER.isLoggable(Level.FINE)) {
                    LOGGER.log(Level.FINE, "Code completion from anchor region: {0}.", region);
                }

                TaggerTokenSource<Token> taggerTokenSource = new TaggerTokenSource<Token>(tagger, new SnapshotPositionRegion(snapshot, region));
                TokenSource<Token> tokenSource = new CodeCompletionTokenSource(getCaretOffset(), taggerTokenSource);
                CommonTokenStream tokens = new CommonTokenStream(tokenSource);

                CodeCompletionGrammarParser parser = ParserCache.DEFAULT.getParser(tokens);
                ATN atn = null;
                try {
                    parser.setBuildParseTree(true);
                    parser.setErrorHandler(new CodeCompletionErrorStrategy<Token>());
                    atn = parser.getATN();
                    parseTrees = forestParser.getParseTrees(parser);
                } finally {
                    ParserCache.DEFAULT.putParser(parser);
                }

                boolean hasActionConfig = false;
                boolean hasNonActionConfig = false;

                if (parseTrees != null) {
                    possibleDeclaration = false;
                    possibleReference = false;

                    declarationOrReferenceLoop:
                    for (Map.Entry<RuleContext<Token>, CaretReachedException> entry : parseTrees.entrySet()) {
                        CaretReachedException ex = entry.getValue();
                        if (ex == null || ex.getTransitions() == null) {
                            continue;
                        }

                        if (ex.getCaretToken() != null) {
                            caretToken = ex.getCaretToken();
                        }

                        RuleContext<Token> finalContext = entry.getValue().getFinalContext();
                        if (finalContext != null && finalContext.getRuleIndex() == GrammarParser.RULE_id) {
                            RuleContext<Token> parent = finalContext.getParent();
                            int parentIndex = parent != null ? parent.getRuleIndex() : -1;
                            switch (parentIndex) {
                            case GrammarParser.RULE_lexerCommandName:
                                // this is a lexer command (keyword), which is not considered a declaration or reference
                                break;

                            case GrammarParser.RULE_lexerCommandExpr:
                                // Reference to token, channel, or mode. Since channels and modes are not included
                                // in reference analysis, consider this as a possible declaration to prevent unwanted
                                // completion replacements.
                                possibleDeclaration = true;
                                possibleReference = true;
                                break;

                            default:
                                // other instances of 'id' in the grammar are declarations
                                possibleDeclaration = true;
                                break;
                            }
                        }

                        Map<ATNConfig, List<Transition>> transitions = entry.getValue().getTransitions();
                        IdentityHashMap<PredictionContext, PredictionContext> visited = new IdentityHashMap<PredictionContext, PredictionContext>();
                        Deque<PredictionContext> workList = new ArrayDeque<PredictionContext>();
                        Deque<Integer> stateWorkList = new ArrayDeque<Integer>();
                        for (Map.Entry<ATNConfig, List<Transition>> transitionEntry : transitions.entrySet()) {
                            boolean currentActionConfig = false;
                            visited.clear();
                            workList.clear();
                            stateWorkList.clear();
                            workList.add(transitionEntry.getKey().getContext());
                            stateWorkList.add(transitionEntry.getKey().getState().stateNumber);
                            while (!workList.isEmpty()) {
                                PredictionContext context = workList.poll();
                                int state = stateWorkList.poll();
                                if (visited.put(context, context) != null) {
                                    continue;
                                }

                                for (int i = 0; i < context.size(); i++) {
                                    workList.add(context.getParent(i));
                                    stateWorkList.add(context.getReturnState(i));
                                }

                                int ruleIndex = atn.states.get(state).ruleIndex;
                                if (ruleIndex == GrammarParser.RULE_actionBlock) {
                                    currentActionConfig = true;
                                }

                                if (currentActionConfig) {
                                    break;
                                }
                            }

                            hasActionConfig |= currentActionConfig;
                            hasNonActionConfig |= !currentActionConfig;

                            for (Transition t : transitionEntry.getValue()) {
                                int ruleIndex = t.target.ruleIndex;
                                switch (ruleIndex) {
                                case GrammarParser.RULE_ruleref:
                                case GrammarParser.RULE_terminal:
                                    possibleReference = true;
                                    break;

                                case GrammarParser.RULE_lexerRule:
                                    {
                                        IntervalSet label = t.label();
                                        if (label != null && label.contains(GrammarParser.TOKEN_REF)) {
                                            possibleDeclaration = true;
                                        }

                                        break;
                                    }

                                case GrammarParser.RULE_parserRuleSpec:
                                    {
                                        IntervalSet label = t.label();
                                        if (label != null && label.contains(GrammarParser.RULE_REF)) {
                                            possibleDeclaration = true;
                                        }

                                        break;
                                    }

                                default:
                                    break;
                                }

                                if (possibleDeclaration && possibleReference) {
                                    break;
                                }
                            }

                            if (hasActionConfig && hasNonActionConfig) {
                                break declarationOrReferenceLoop;
                            }
                        }
                    }
                }

                if (parseTrees != null) {
                    Map<String, CompletionItem> intermediateResults = new HashMap<String, CompletionItem>();

                    // Keyword analysis
                    analyzeKeywords(parseTrees, intermediateResults);

                    // Expression analysis
                    grammarType = analyzeExpressions(taskManager, snapshot, hasActionConfig, hasNonActionConfig, grammarType, rules, parseTrees, intermediateResults);

                    results.addAll(intermediateResults.values());
                }
            }

            if (parseTrees == null && possibleKeyword) {
                // Add keywords
                results.addAll(KeywordCompletionItem.KEYWORD_ITEMS.values());
            }

            if (possibleReference) {
                boolean tokenReferencesOnly = grammarType == GrammarParser.LEXER;

                // Add rules from the grammar
                if (rules.isEmpty()) {
                    rules.addAll(GrammarCompletionProvider.getRulesFromGrammar(taskManager, snapshot, !tokenReferencesOnly));
                }

                for (Description rule : rules) {
                    if (!tokenReferencesOnly || Character.isUpperCase(rule.getName().charAt(0))) {
                        results.add(new GrammarRuleCompletionItem(rule));
                    }
                }
            }

            OffsetRegion applicableToSpan = getApplicableToSpan(snapshot, caretToken);
            applicableTo = snapshot.createTrackingRegion(applicableToSpan, TrackingPositionRegion.Bias.Inclusive);
        }

        private GrammarReferenceAnchors findNearestAnchors(ParserTaskManager taskManager, DocumentSnapshot snapshot) {
            List<Anchor> anchors;
            Future<ParserData<List<Anchor>>> result =
                taskManager.getData(snapshot, GrammarParserDataDefinitions.DYNAMIC_ANCHOR_POINTS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
            try {
                anchors = result.get().getData();
            } catch (InterruptedException ex) {
                anchors = null;
            } catch (ExecutionException ex) {
                Exceptions.printStackTrace(ex);
                anchors = null;
            }

            int grammarType = -1;
            // the innermost anchor enclosing the caret
            Anchor enclosing = null;
            // the last anchor starting before the caret
            Anchor previous = null;
            if (anchors != null) {
                Anchor next = null;

                /*
                 * parse the current rule
                 */
                for (Anchor anchor : anchors) {
                    if (anchor instanceof GrammarParserAnchorListener.GrammarTypeAnchor) {
                        grammarType = ((GrammarParserAnchorListener.GrammarTypeAnchor)anchor).getGrammarType();
                        continue;
                    }

                    if (anchor.getSpan().getStartPosition(snapshot).getOffset() <= getCaretOffset()) {
                        previous = anchor;
                        if (anchor.getSpan().getEndPosition(snapshot).getOffset() > getCaretOffset()) {
                            enclosing = anchor;
                        }
                    } else {
                        next = anchor;
                        break;
                    }
                }
            }

            return new GrammarReferenceAnchors(grammarType, previous, enclosing);
        }

        private Tagger<TokenTag<Token>> getTagger(ParserTaskManager taskManager, DocumentSnapshot snapshot) {
            Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = taskManager.getData(snapshot, GrammarParserDataDefinitions.LEXER_TOKENS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
            Tagger<TokenTag<Token>> tagger = null;
            try {
                tagger = futureTokensData.get().getData();
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
            } catch (ExecutionException ex) {
                Exceptions.printStackTrace(ex);
            }

            return tagger;
        }

        private OffsetRegion getParseRegion(DocumentSnapshot snapshot, GrammarReferenceAnchors anchors) {
            int regionEnd = Math.min(snapshot.length(), getCaretOffset() + 1);
            OffsetRegion region;
            if (anchors.getEnclosing() != null) {
                region = OffsetRegion.fromBounds(anchors.getEnclosing().getSpan().getStartPosition(snapshot).getOffset(), regionEnd);
            } else if (anchors.getPrevious() != null) {
                region = OffsetRegion.fromBounds(anchors.getPrevious().getSpan().getEndPosition(snapshot).getOffset(), regionEnd);
            } else {
                region = OffsetRegion.fromBounds(0, regionEnd);
            }

            return region;
        }

        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommandName, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1),
        })
        private void analyzeKeywords(Map<RuleContext<Token>, CaretReachedException> parseTrees, Map<String, CompletionItem> intermediateResults) {
            boolean maybeLexerCommand = false;

            IntervalSet remainingKeywords = new IntervalSet(KeywordCompletionItem.KEYWORD_TYPES);
            for (Map.Entry<RuleContext<Token>, CaretReachedException> entry : parseTrees.entrySet()) {
                CaretReachedException caretReachedException = entry.getValue();
                if (caretReachedException == null || caretReachedException.getTransitions() == null) {
                    continue;
                }

                RuleContext<Token> finalContext = caretReachedException.getFinalContext();
                if (finalContext.getRuleIndex() == GrammarParser.RULE_id) {
                    RuleContext<Token> parent = finalContext.getParent();
                    if (parent != null && parent.getRuleIndex() == GrammarParser.RULE_lexerCommandName) {
                        maybeLexerCommand = true;
                    }

                    continue;
                }

                Map<ATNConfig, List<Transition>> transitions = caretReachedException.getTransitions();
                for (List<Transition> transitionList : transitions.values()) {
                    for (Transition transition : transitionList) {
                        if (transition.isEpsilon() || transition instanceof WildcardTransition || transition instanceof NotSetTransition) {
                            continue;
                        }

                        IntervalSet label = transition.label();
                        if (label == null) {
                            continue;
                        }

                        for (int keyword : remainingKeywords.toArray()) {
                            if (label.contains(keyword)) {
                                remainingKeywords.remove(keyword);
                                KeywordCompletionItem item = KeywordCompletionItem.KEYWORD_ITEMS.get(keyword);
                                intermediateResults.put(item.getInsertPrefix().toString(), item);
                            }
                        }
                    }
                }
            }

            if (maybeLexerCommand) {
                addLexerCommands(intermediateResults);
            }
        }

        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionExpression, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionScopeExpression, version=0),
        })
        private int analyzeExpressions(ParserTaskManager taskManager,
                                       DocumentSnapshot snapshot,
                                       boolean hasActionConfig,
                                       boolean hasNonActionConfig,
                                       int grammarType,
                                       Collection<Description> rules,
                                       Map<RuleContext<Token>, CaretReachedException> parseTrees,
                                       Map<String, CompletionItem> intermediateResults) {
            FileModel fileModel = null;
            boolean fileModelDataFailed = false;
            boolean inExpression = false;

            for (Map.Entry<RuleContext<Token>, CaretReachedException> entry : parseTrees.entrySet()) {
                RuleContext<Token> finalContext = entry.getValue() != null ? entry.getValue().getFinalContext() : null;
                if (finalContext == null) {
                    continue;
                }

                ParseTree<Token> expressionRoot = null;
                if (finalContext instanceof ActionScopeExpressionContext
                    || finalContext instanceof ActionExpressionContext) {
                    expressionRoot = finalContext;
                }

                for (ParseTree<Token> tree : ParseTrees.getAncestors(finalContext)) {
                    if (tree instanceof ActionScopeExpressionContext
                        || tree instanceof ActionExpressionContext) {
                        expressionRoot = tree;
                    }
                }

                if (expressionRoot == null) {
                    continue;
                } else if (expressionRoot instanceof ActionScopeExpressionContext) {
                    if (((ActionScopeExpressionContext)expressionRoot).op == null) {
                        continue;
                    }
                } else if (expressionRoot instanceof ActionExpressionContext) {
                    if (((ActionExpressionContext)expressionRoot).op == null) {
                        continue;
                    }
                }

                if (fileModel == null && !fileModelDataFailed) {
                    fileModel = getFileModel(taskManager, snapshot);
                    fileModelDataFailed = fileModel == null;
                }

                if (fileModel == null) {
                    continue;
                }

                inExpression = true;
                ActionExpressionAnalyzer expressionAnalyzer = new ActionExpressionAnalyzer(fileModel, finalContext);
                ParseTreeWalker.DEFAULT.walk(expressionAnalyzer, expressionRoot);
                for (AttributeModel member : expressionAnalyzer.getMembers()) {
                    CompletionItem item = new MemberCompletionItem(member);
                    intermediateResults.put(item.getInsertPrefix().toString(), item);
                }
            }

            for (Map.Entry<RuleContext<Token>, CaretReachedException> entry : parseTrees.entrySet()) {
                ParseTree<Token> parseTree = entry.getKey();
                RuleContext<Token> finalContext = entry.getValue() != null ? entry.getValue().getFinalContext() : null;
                LabelAnalyzer labelAnalyzer = new LabelAnalyzer(finalContext);
                ParseTreeWalker.DEFAULT.walk(labelAnalyzer, parseTree);

                boolean possibleInAction = labelAnalyzer.isInAction() || hasActionConfig;
                boolean definiteInAction = labelAnalyzer.isInAction() || (hasActionConfig && !hasNonActionConfig);
                possibleKeyword |= !definiteInAction;
                possibleDeclaration &= !definiteInAction;
                possibleReference &= !definiteInAction;

                if (grammarType == GrammarParser.COMBINED) {
                    Token enclosingRule = labelAnalyzer.getEnclosingRuleName();
                    if (enclosingRule != null) {
                        if (enclosingRule.getType() == GrammarParser.RULE_REF) {
                            grammarType = GrammarParser.PARSER;
                        } else {
                            grammarType = GrammarParser.LEXER;
                        }
                    }
                }

                if (!inExpression && possibleInAction) {
                    if (!definiteInAction && labelAnalyzer.getEnclosingRuleName() != null) {
                        CompletionItem item = new EnclosingRuleCompletionItem(labelAnalyzer.getEnclosingRuleName().getText());
                        intermediateResults.put(item.getInsertPrefix().toString(), item);
                    }

                    for (Token label : labelAnalyzer.getLabels()) {
                        CompletionItem item = new RewriteReferenceCompletionItem(label.getText(), true);
                        intermediateResults.put(item.getInsertPrefix().toString(), item);
                    }

                    if (possibleInAction && !inExpression) {
                        for (Token implicit : labelAnalyzer.getUnlabeledElements()) {
                            // only add implicit tokens here. all implicit rule references will be added separately
                            if (Character.isUpperCase(implicit.getText().charAt(0))) {
                                CompletionItem item = new ActionReferenceCompletionItem(implicit.getText(), false);
                                intermediateResults.put(item.getInsertPrefix().toString(), item);
                            }
                        }

                        if (grammarType != GrammarParser.LEXER) {
                            // Add rules from the grammar
                            if (rules.isEmpty()) {
                                rules.addAll(GrammarCompletionProvider.getRulesFromGrammar(taskManager, snapshot, true));
                            }

                            for (Description rule : rules) {
                                if (Character.isLowerCase(rule.getName().charAt(0))) {
                                    results.add(new ActionReferenceCompletionItem(rule.getName(), false));
                                }
                            }
                        }

                        addRootActionExpressions(intermediateResults, grammarType);
                    }
                }
            }

            return grammarType;
        }

        private FileModel getFileModel(ParserTaskManager taskManager, DocumentSnapshot snapshot) {
            Future<ParserData<FileModel>> futureFileModelData = taskManager.getData(snapshot, GrammarParserDataDefinitions.FILE_MODEL, EnumSet.of(ParserDataOptions.ALLOW_STALE, ParserDataOptions.SYNCHRONOUS));
            try {
                return futureFileModelData.get().getData();
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
                return null;
            } catch (ExecutionException ex) {
                Exceptions.printStackTrace(ex);
                return null;
            }
        }

        private void addLexerCommands(Map<String, CompletionItem> intermediateResults) {
            for (String command : Rule.validLexerCommands) {
                if (!intermediateResults.containsKey(command)) {
                    intermediateResults.put(command, new KeywordCompletionItem(command));
                }
            }
        }

        private void addRootActionExpressions(Map<String, CompletionItem> intermediateResults, int grammarType) {
            switch (grammarType) {
            case GrammarParser.LEXER:
                // lexer actions were removed
                break;

            case GrammarParser.PARSER:
                intermediateResults.put("$text", new KeywordCompletionItem("$text"));
                intermediateResults.put("$start", new KeywordCompletionItem("$start"));
                intermediateResults.put("$stop", new KeywordCompletionItem("$stop"));
                intermediateResults.put("$ctx", new KeywordCompletionItem("$ctx"));
                break;

            default:
                // if we're unsure about the type, include all possibilities to make sure we're covered
                intermediateResults.put("$text", new KeywordCompletionItem("$text"));
                intermediateResults.put("$start", new KeywordCompletionItem("$start"));
                intermediateResults.put("$stop", new KeywordCompletionItem("$stop"));
                intermediateResults.put("$ctx", new KeywordCompletionItem("$ctx"));
                break;
            }
        }

        private OffsetRegion getApplicableToSpan(DocumentSnapshot snapshot, CaretToken caretToken) {
            OffsetRegion applicableToSpan;
            if (caretToken != null && caretToken.getOriginalToken() != null && caretToken.getOriginalToken().getChannel() == Token.DEFAULT_CHANNEL) {
                applicableToSpan = OffsetRegion.fromBounds(caretToken.getStartIndex(), caretToken.getStopIndex() + 1);
            } else {
                SnapshotPositionRegion identifier = DocumentTextUtilities.getIdentifierBlock(new SnapshotPosition(snapshot, getCaretOffset()));
                if (identifier != null) {
                    applicableToSpan = identifier.getRegion();
                } else {
                    applicableToSpan = OffsetRegion.fromBounds(getCaretOffset(), getCaretOffset());
                }
            }

            if (!isExtend() && applicableToSpan.contains(getCaretOffset())) {
                applicableToSpan = OffsetRegion.fromBounds(applicableToSpan.getStart(), getCaretOffset());
            }

            if (!applicableToSpan.isEmpty()) {
                // make sure this is a word
                String applicableText = snapshot.subSequence(applicableToSpan.getStart(), applicableToSpan.getEnd()).toString();
                if (!WORD_PATTERN.matcher(applicableText).matches()) {
                    applicableToSpan = OffsetRegion.fromBounds(getCaretOffset(), getCaretOffset());
                }
            }

            return applicableToSpan;
        }

    }
}
