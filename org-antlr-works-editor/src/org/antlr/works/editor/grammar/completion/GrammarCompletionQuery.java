/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.NotSetTransition;
import org.antlr.v4.runtime.atn.PredictionContext;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.atn.WildcardTransition;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.Trees;
import org.antlr.works.editor.antlr4.classification.TaggerTokenSource;
import org.antlr.works.editor.antlr4.completion.AbstractCompletionQuery;
import org.antlr.works.editor.antlr4.completion.CaretReachedException;
import org.antlr.works.editor.antlr4.completion.CaretToken;
import org.antlr.works.editor.antlr4.completion.CodeCompletionErrorStrategy;
import org.antlr.works.editor.antlr4.completion.CodeCompletionParser;
import org.antlr.works.editor.antlr4.completion.CodeCompletionTokenSource;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.codemodel.AttributeModel;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParser.actionExpressionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.actionScopeExpressionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParserAnchorListener;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.openide.util.Exceptions;

/**
 *
 * @author Sam Harwell
 */
public final class GrammarCompletionQuery extends AbstractCompletionQuery {
    // -J-Dorg.antlr.works.editor.grammar.completion.GrammarCompletionQuery.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GrammarCompletionQuery.class.getName());

    private static final ParserCache parserCache = new ParserCache();

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
    protected boolean isQueryContext(CompletionResultSet resultSet, Document doc, int caretOffset) {
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
        protected void runImpl(BaseDocument document) {
            results = new ArrayList<CompletionItem>();
            possibleDeclaration = true;
            possibleReference = true;
            possibleKeyword = true;

            // Add context items (labels, etc). Use anchor points to optimize information gathering.
            if (document == null) {
                return;
            }

            VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(document);
            DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();

            boolean possibleInAction;
            boolean definiteInAction;
            Map<RuleContext, CaretReachedException> parseTrees = null;
            CaretToken caretToken = null;
            int grammarType = -1;

            ParserTaskManager taskManager = getParserTaskManager();
            if (taskManager == null) {
                return;
            }

            Collection<Description> rules = null;

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

            if (anchors != null) {
                Anchor enclosing = null;
                Anchor previous = null;
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

                if (previous != null) {
                    Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = taskManager.getData(snapshot, GrammarParserDataDefinitions.LEXER_TOKENS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
                    Tagger<TokenTag<Token>> tagger = null;
                    try {
                        tagger = futureTokensData.get().getData();
                    } catch (InterruptedException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (ExecutionException ex) {
                        Exceptions.printStackTrace(ex);
                    }

                    int regionEnd = Math.min(snapshot.length(), getCaretOffset() + 1);
                    OffsetRegion region;
                    if (enclosing != null) {
                        region = OffsetRegion.fromBounds(enclosing.getSpan().getStartPosition(snapshot).getOffset(), regionEnd);
                    } else {
                        region = OffsetRegion.fromBounds(previous.getSpan().getEndPosition(snapshot).getOffset(), regionEnd);
                    }

                    if (LOGGER.isLoggable(Level.FINE)) {
                        LOGGER.log(Level.FINE, "Code completion from anchor region: {0}.", region);
                    }

                    TaggerTokenSource taggerTokenSource = new TaggerTokenSource(tagger, new SnapshotPositionRegion(snapshot, region));

//                        CharStream input = new DocumentSnapshotCharStream(snapshot);
//                        input.seek(enclosing.getSpan().getStartPosition(snapshot).getOffset());
//                        GrammarLexer lexer = new GrammarLexer(input);
                    TokenSource tokenSource = new CodeCompletionTokenSource(getCaretOffset(), taggerTokenSource);
                    CommonTokenStream tokens = new CommonTokenStream(tokenSource);

                    CodeCompletionGrammarParser parser = parserCache.getParser(tokens);
                    try {
                        parser.setBuildParseTree(true);
                        parser.setErrorHandler(new CodeCompletionErrorStrategy());

                        switch (previous.getRule()) {
                        case GrammarParser.RULE_rule:
                            parseTrees = getParseTrees(parser);
                            break;

                        default:
                            parseTrees = null;
                            break;
                        }

                        boolean hasActionConfig = false;
                        boolean hasNonActionConfig = false;
                        final boolean hasRewriteConfig = false;
                        boolean hasNonRewriteConfig = false;

                        if (parseTrees != null) {
                            possibleDeclaration = false;
                            possibleReference = false;

                            declarationOrReferenceLoop:
                            for (Map.Entry<RuleContext, CaretReachedException> entry : parseTrees.entrySet()) {
                                CaretReachedException ex = entry.getValue();
                                if (ex == null || ex.getTransitions() == null) {
                                    continue;
                                }

                                if (ex.getCaretToken() != null) {
                                    caretToken = ex.getCaretToken();
                                }

                                Map<ATNConfig, List<Transition>> transitions = entry.getValue().getTransitions();
                                IdentityHashMap<PredictionContext, PredictionContext> visited = new IdentityHashMap<PredictionContext, PredictionContext>();
                                Deque<PredictionContext> workList = new ArrayDeque<PredictionContext>();
                                Deque<Integer> stateWorkList = new ArrayDeque<Integer>();
                                for (ATNConfig c : transitions.keySet()) {
                                    boolean currentActionConfig = false;
                                    final boolean currentRewriteConfig = false;
                                    visited.clear();
                                    workList.clear();
                                    stateWorkList.clear();
                                    workList.add(c.context);
                                    stateWorkList.add(c.state.stateNumber);
                                    while (!workList.isEmpty()) {
                                        PredictionContext context = workList.poll();
                                        int state = stateWorkList.poll();
                                        if (visited.put(context, context) != null) {
                                            continue;
                                        }

                                        for (int i = 0; i < context.invokingStates.length; i++) {
                                            workList.add(context.parents[i]);
                                            stateWorkList.add(context.invokingStates[i]);
                                        }

                                        int ruleIndex = parser.getATN().states.get(state).ruleIndex;
                                        if (ruleIndex == GrammarParser.RULE_actionBlock) {
                                            currentActionConfig = true;
                                        }

                                        if (currentActionConfig && currentRewriteConfig) {
                                            break;
                                        }
                                    }

                                    hasActionConfig |= currentActionConfig;
                                    hasNonActionConfig |= !currentActionConfig;
                                    hasNonRewriteConfig |= !currentRewriteConfig;

                                    for (Transition t : transitions.get(c)) {
                                        int ruleIndex = t.target.ruleIndex;
                                        if (ruleIndex == GrammarParser.RULE_id) {
                                            possibleDeclaration = true;
                                        } else if (ruleIndex == GrammarParser.RULE_ruleref
                                            || ruleIndex == GrammarParser.RULE_terminal) {
                                            possibleReference = true;
                                        }

                                        if (possibleDeclaration && possibleReference) {
                                            break;
                                        }
                                    }

                                    if (hasActionConfig && hasNonActionConfig && hasRewriteConfig && hasNonRewriteConfig) {
                                        break declarationOrReferenceLoop;
                                    }
                                }
                            }
                        }

                        Map<String, CompletionItem> intermediateResults = new HashMap<String, CompletionItem>();
                        if (parseTrees != null) {
                            /*
                            * KEYWORD ANALYSIS
                            */
                            IntervalSet remainingKeywords = new IntervalSet();
                            remainingKeywords.add(GrammarLexer.OPTIONS);
                            remainingKeywords.add(GrammarLexer.TOKENS);
                            remainingKeywords.add(GrammarLexer.IMPORT);
                            remainingKeywords.add(GrammarLexer.FRAGMENT);
                            remainingKeywords.add(GrammarLexer.LEXER);
                            remainingKeywords.add(GrammarLexer.PARSER);
                            remainingKeywords.add(GrammarLexer.GRAMMAR);
                            remainingKeywords.add(GrammarLexer.PROTECTED);
                            remainingKeywords.add(GrammarLexer.PUBLIC);
                            remainingKeywords.add(GrammarLexer.PRIVATE);
                            remainingKeywords.add(GrammarLexer.RETURNS);
                            remainingKeywords.add(GrammarLexer.LOCALS);
                            remainingKeywords.add(GrammarLexer.THROWS);
                            remainingKeywords.add(GrammarLexer.CATCH);
                            remainingKeywords.add(GrammarLexer.FINALLY);
                            remainingKeywords.add(GrammarLexer.TEMPLATE);
                            remainingKeywords.add(GrammarLexer.MODE);

                            for (Map.Entry<RuleContext, CaretReachedException> entry : parseTrees.entrySet()) {
                                CaretReachedException caretReachedException = entry.getValue();
                                if (caretReachedException == null || caretReachedException.getTransitions() == null) {
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
                                                KeywordCompletionItem item = new KeywordCompletionItem(GrammarLexer.tokenNames[keyword].toLowerCase());
                                                intermediateResults.put(item.getInsertPrefix().toString(), item);
                                            }
                                        }
                                    }
                                }
                            }

                            /*
                            * EXPRESSION ANALYSIS
                            */
                            FileModel fileModel = null;
                            boolean fileModelDataFailed = false;
                            boolean inExpression = false;

                            for (Map.Entry<RuleContext, CaretReachedException> entry : parseTrees.entrySet()) {
                                RuleContext finalContext = entry.getValue() != null ? entry.getValue().getFinalContext() : null;
                                if (finalContext == null) {
                                    continue;
                                }

                                ParseTree expressionRoot = null;
                                if (finalContext instanceof actionScopeExpressionContext
                                    || finalContext instanceof actionExpressionContext) {
                                    expressionRoot = finalContext;
                                }

                                for (Tree tree : Trees.getAncestors(finalContext)) {
                                    if (tree instanceof actionScopeExpressionContext
                                        || tree instanceof actionExpressionContext) {
                                        expressionRoot = (ParseTree)tree;
                                    }
                                }

                                if (expressionRoot == null) {
                                    continue;
                                } else if (expressionRoot instanceof actionScopeExpressionContext) {
                                    if (((actionScopeExpressionContext)expressionRoot).op == null) {
                                        continue;
                                    }
                                } else if (expressionRoot instanceof actionExpressionContext) {
                                    if (((actionExpressionContext)expressionRoot).op == null) {
                                        continue;
                                    }
                                }

                                if (fileModel == null && !fileModelDataFailed) {
                                    Future<ParserData<FileModel>> futureFileModelData = taskManager.getData(snapshot, GrammarParserDataDefinitions.FILE_MODEL, EnumSet.of(ParserDataOptions.ALLOW_STALE, ParserDataOptions.SYNCHRONOUS));
                                    try {
                                        fileModel = futureFileModelData.get().getData();
                                    } catch (InterruptedException ex) {
                                        Exceptions.printStackTrace(ex);
                                        fileModelDataFailed = true;
                                    } catch (ExecutionException ex) {
                                        Exceptions.printStackTrace(ex);
                                        fileModelDataFailed = true;
                                    }
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

                            for (Map.Entry<RuleContext, CaretReachedException> entry : parseTrees.entrySet()) {
                                ParseTree parseTree = entry.getKey();
                                RuleContext finalContext = entry.getValue() != null ? entry.getValue().getFinalContext() : null;
                                LabelAnalyzer labelAnalyzer = new LabelAnalyzer(finalContext);
                                ParseTreeWalker.DEFAULT.walk(labelAnalyzer, parseTree);

                                possibleInAction = labelAnalyzer.isInAction() || hasActionConfig;
                                definiteInAction = labelAnalyzer.isInAction() || (hasActionConfig && !hasNonActionConfig);
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
                                            if (rules == null) {
                                                rules = GrammarCompletionProvider.getRulesFromGrammar(taskManager, snapshot);
                                            }

                                            for (Description rule : rules) {
                                                if (Character.isLowerCase(rule.getName().charAt(0))) {
                                                    results.add(new ActionReferenceCompletionItem(rule.getName(), false));
                                                }
                                            }
                                        }

                                        switch (grammarType) {
                                        case GrammarParser.LEXER:
                                            intermediateResults.put("$text", new KeywordCompletionItem("$text"));
                                            intermediateResults.put("$type", new KeywordCompletionItem("$type"));
                                            intermediateResults.put("$line", new KeywordCompletionItem("$line"));
                                            intermediateResults.put("$index", new KeywordCompletionItem("$index"));
                                            intermediateResults.put("$pos", new KeywordCompletionItem("$pos"));
                                            intermediateResults.put("$channel", new KeywordCompletionItem("$channel"));
                                            intermediateResults.put("$start", new KeywordCompletionItem("$start"));
                                            intermediateResults.put("$stop", new KeywordCompletionItem("$stop"));
                                            intermediateResults.put("$int", new KeywordCompletionItem("$int"));
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
                                            intermediateResults.put("$type", new KeywordCompletionItem("$type"));
                                            intermediateResults.put("$line", new KeywordCompletionItem("$line"));
                                            intermediateResults.put("$index", new KeywordCompletionItem("$index"));
                                            intermediateResults.put("$pos", new KeywordCompletionItem("$pos"));
                                            intermediateResults.put("$channel", new KeywordCompletionItem("$channel"));
                                            intermediateResults.put("$start", new KeywordCompletionItem("$start"));
                                            intermediateResults.put("$stop", new KeywordCompletionItem("$stop"));
                                            intermediateResults.put("$int", new KeywordCompletionItem("$int"));
                                            intermediateResults.put("$ctx", new KeywordCompletionItem("$ctx"));
                                            break;
                                        }
                                    }
                                }
                            }

                            results.addAll(intermediateResults.values());
                        }
                    } finally {
                        parserCache.putParser(parser);
                    }
                }
            }

            if (parseTrees == null && possibleKeyword) {
                // Add keywords
                results.addAll(KeywordCompletionItem.KEYWORD_ITEMS);
            }

            if (possibleReference) {
                boolean tokenReferencesOnly = grammarType == GrammarParser.LEXER;

                // Add rules from the grammar
                if (rules == null) {
                    rules = GrammarCompletionProvider.getRulesFromGrammar(taskManager, snapshot);
                }

                for (Description rule : rules) {
                    if (!tokenReferencesOnly || Character.isUpperCase(rule.getName().charAt(0))) {
                        results.add(new GrammarRuleCompletionItem(rule));
                    }
                }
            }

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

            applicableTo = snapshot.createTrackingRegion(applicableToSpan, TrackingPositionRegion.Bias.Inclusive);
        }

        @Override
        protected RuleContext parseImpl(CodeCompletionParser parser) {
            return ((CodeCompletionGrammarParser)parser).rules();
        }

    }
}
