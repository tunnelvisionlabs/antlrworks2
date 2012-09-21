/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.formatting;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import javax.swing.text.BadLocationException;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.completion.ReferenceAnchors;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.NotSetTransition;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.misc.Tuple;
import org.antlr.v4.runtime.misc.Tuple2;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.antlr4.completion.CaretReachedException;
import org.antlr.works.editor.antlr4.completion.CodeCompletionErrorStrategy;
import org.antlr.works.editor.antlr4.formatting.AbstractIndentTask;
import org.antlr.works.editor.antlr4.formatting.AlignmentRequirement;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.completion.CodeCompletionGrammarParser;
import org.antlr.works.editor.grammar.completion.GrammarForestParser;
import org.antlr.works.editor.grammar.completion.ParserCache;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.OptionsSpecContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParserAnchorListener;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.indent.spi.IndentTask;
import org.netbeans.modules.editor.indent.spi.IndentTask.Factory;
import org.openide.util.Exceptions;
import org.openide.util.NotImplementedException;

/**
 *
 * @author Sam Harwell
 */
public class GrammarIndentTask extends AbstractIndentTask {

    private GrammarCodeStyle codeStyle;
    private CodeCompletionGrammarParser parser;

    public GrammarIndentTask(Context context) {
        super(context);
    }

    @Override
    public boolean smartReindent() throws BadLocationException {
        try {
            return super.smartReindent();
        } finally {
            if (parser != null) {
                ParserCache.DEFAULT.putParser(parser);
                parser = null;
            }
        }
    }

    @Override
    protected ReferenceAnchors findNearestAnchors(ParserTaskManager taskManager, DocumentSnapshot snapshot, int endOffset) {
        List<Anchor> anchors = getDynamicAnchorPoints();

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

                if (anchor.getSpan().getStartPosition(snapshot).getOffset() <= endOffset) {
                    previous = anchor;
                    if (anchor.getSpan().getEndPosition(snapshot).getOffset() > endOffset) {
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

    @Override
    protected Tagger<TokenTag<Token>> getTagger() {
        Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = getTaskManager().getData(getSnapshot(), GrammarParserDataDefinitions.LEXER_TOKENS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
        Tagger<TokenTag<Token>> tagger = null;
        try {
            tagger = futureTokensData != null ? futureTokensData.get().getData() : null;
        } catch (InterruptedException ex) {
            LOGGER.log(Level.WARNING, "An exception occurred while getting the token tagger.", ex);
        } catch (ExecutionException ex) {
            LOGGER.log(Level.WARNING, "An exception occurred while getting the token tagger.", ex);
        }

        return tagger;
    }

    @Override
    protected Map<RuleContext<Token>, CaretReachedException> getParseTrees(CommonTokenStream tokens, ReferenceAnchors anchors) {
        parser = ParserCache.DEFAULT.getParser(tokens);

        parser.setBuildParseTree(true);
        parser.setErrorHandler(new CodeCompletionErrorStrategy<Token>());

        GrammarForestParser forestParser;
        if (anchors.getPrevious() != null) {
            switch (anchors.getPrevious().getRule()) {
            case GrammarParser.RULE_ruleSpec:
                forestParser = GrammarForestParser.RULES;
                break;

            default:
                forestParser = null;
            }
        } else {
            forestParser = GrammarForestParser.GRAMMAR_SPEC;
        }

        if (forestParser == null) {
            return null;
        }

        Map<RuleContext<Token>, CaretReachedException> parseTrees = forestParser.getParseTrees(parser);
        return parseTrees;
    }

    @Override
    protected Set<AlignmentRequirement> getAlignmentRequirement(
        Map.Entry<RuleContext<Token>, CaretReachedException> parseTree,
        ParseTree<? extends Token> targetElement,
        ParseTree<? extends Token> ancestor)
    {
        if (ancestor == targetElement && !(targetElement instanceof TerminalNode)) {
            // special handling for predicted tokens that don't actually exist yet
            CaretReachedException ex = parseTree.getValue();
            if (ex != null && ex.getTransitions() != null) {
                boolean validTransition = false;
                boolean selfTransition = false;

                // examine transitions for predictions that don't actually exist yet
                for (Map.Entry<ATNConfig, List<Transition>> entry : ex.getTransitions().entrySet()) {
                    if (entry.getValue() == null) {
                        continue;
                    }

                    for (Transition transition : entry.getValue()) {
                        IntervalSet label = transition.label();
                        if (label == null) {
                            continue;
                        }

                        boolean containsInvalid =
                            label.contains(GrammarParser.OR)
                            || label.contains(GrammarParser.RPAREN)
                            || label.contains(GrammarParser.RBRACE)
                            || label.contains(GrammarParser.END_ACTION)
                            || label.contains(GrammarParser.END_ARG_ACTION)
                            || label.contains(GrammarParser.OPTIONS)
                            || label.contains(GrammarParser.AT)
                            || label.contains(GrammarParser.ASSIGN)
                            || label.contains(GrammarParser.SEMI)
                            || label.contains(GrammarParser.COMMA);
                        boolean containsInvalidSelf =
                            label.contains(GrammarParser.LPAREN)
                            || label.contains(GrammarParser.BEGIN_ACTION)
                            || label.contains(GrammarParser.BEGIN_ARG_ACTION);

                        if (transition instanceof NotSetTransition) {
                            containsInvalid = !containsInvalid;
                            containsInvalidSelf = !containsInvalidSelf;
                        }

                        validTransition |= !containsInvalid;
                        selfTransition |= !containsInvalidSelf;
                    }
                }

                if (!validTransition) {
                    return EnumSet.of(AlignmentRequirement.IGNORE_TREE);
                } else if (!selfTransition) {
                    return EnumSet.of(AlignmentRequirement.USE_ANCESTOR);
                }
            }
        }

        if (!(ancestor instanceof RuleNode)) {
            return EnumSet.of(AlignmentRequirement.USE_ANCESTOR);
        }

        RuleNode<? extends Token> ruleNode = (RuleNode<? extends Token>)ancestor;
        RuleContext<? extends Token> ruleContext = ruleNode.getRuleContext();
        switch (ruleContext.getRuleIndex()) {
        case GrammarParser.RULE_parserRuleSpec:
        case GrammarParser.RULE_ruleAltList:
        case GrammarParser.RULE_lexerRule:
        case GrammarParser.RULE_lexerAltList:
        case GrammarParser.RULE_altList:
        case GrammarParser.RULE_blockSet:
        case GrammarParser.RULE_lexerBlock:
        case GrammarParser.RULE_block:
        case GrammarParser.RULE_optionsSpec:
        case GrammarParser.RULE_tokensSpec:
        case GrammarParser.RULE_modeSpec:
        case GrammarParser.RULE_delegateGrammars:
        case GrammarParser.RULE_actionBlock:
        case GrammarParser.RULE_elements:
        case GrammarParser.RULE_lexerElements:
            return EnumSet.of(AlignmentRequirement.PRIOR_SIBLING);

        case GrammarParser.RULE_delegateGrammar:
            return EnumSet.noneOf(AlignmentRequirement.class);

        default:
            return EnumSet.of(AlignmentRequirement.USE_ANCESTOR);
        }
    }

    protected int getTerminalNodeType(@NonNull ParseTree<? extends Token> node) {
        if (!(node instanceof TerminalNode)) {
            return Token.INVALID_TYPE;
        }

        return ((TerminalNode<? extends Token>)node).getSymbol().getType();
    }

    protected <T extends ParserRuleContext<? extends Token>> T getTypedRuleContext(ParseTree<? extends Token> node, Class<? extends T> clazz) {
        if (!(node instanceof RuleNode)) {
            return null;
        }

        RuleContext<? extends Token> ruleContext = ((RuleNode<? extends Token>)node).getRuleContext();
        if (clazz.isInstance(ruleContext)) {
            return clazz.cast(ruleContext);
        }

        return null;
    }

    @Override
    protected Tuple2<? extends ParseTree<? extends Token>, Integer> getAlignmentElement(
        Map.Entry<RuleContext<Token>, CaretReachedException> parseTree,
        ParseTree<? extends Token> targetElement,
        ParseTree<? extends Token> container,
        List<? extends ParseTree<? extends Token>> priorSiblings)
    {
        assert container instanceof RuleNode;

        RuleNode<? extends Token> ruleNode = (RuleNode<? extends Token>)container;
        RuleContext<? extends Token> ruleContext = (RuleContext<? extends Token>)ruleNode.getRuleContext();

        switch (ruleContext.getRuleIndex()) {
        case GrammarParser.RULE_elements:
        case GrammarParser.RULE_lexerElements:
            // the non-terminals under these rules are straightforward
            int firstElementIndex = -1;
            for (int i = 0; i < priorSiblings.size(); i++) {
                ParseTree<?> sibling = priorSiblings.get(i);
                if (sibling instanceof RuleNode) {
                    firstElementIndex = i;
                    break;
                }
            }

            for (int i = priorSiblings.size() - 2; i >= 0; i--) {
                ParseTree<? extends Token> sibling = priorSiblings.get(i);
                if (!(sibling instanceof RuleNode)) {
                    continue;
                }

                if (i == firstElementIndex || ParseTrees.elementStartsLine(sibling)) {
                    return Tuple.create(sibling, 0);
                }
            }

            // handle at the parent
            return null;

        case GrammarParser.RULE_ruleAltList:
        case GrammarParser.RULE_lexerAltList:
        case GrammarParser.RULE_altList:
        case GrammarParser.RULE_blockSet:
        case GrammarParser.RULE_lexerBlock:
        case GrammarParser.RULE_block:
            if (targetElement == ParseTrees.getStartNode(container)) {
                return null;
            }

            if (getTerminalNodeType(targetElement) == GrammarParser.RPAREN) {
                return Tuple.create(container, 0);
            }

            // OR lines up with previous OR
            boolean orNode = getTerminalNodeType(targetElement) == GrammarParser.OR;
            if (orNode) {
                for (int i = priorSiblings.size() - 2; i >= 0; i--) {
                    ParseTree<? extends Token> sibling = priorSiblings.get(i);
                    if (!(sibling instanceof TerminalNode)) {
                        continue;
                    }

                    if (i == 0 || ParseTrees.elementStartsLine(sibling)) {
                        return Tuple.create(sibling, 0);
                    }
                }

                if (getTerminalNodeType(ParseTrees.getStartNode(container)) != GrammarParser.LPAREN) {
                    // handle at the parent so it aligns at the (
                    return null;
                }

                return Tuple.create(container, 0);
            }

            // the non-terminals under these rules are straightforward
            int firstRuleIndex = -1;
            for (int i = 0; i < priorSiblings.size(); i++) {
                ParseTree<?> sibling = priorSiblings.get(i);
                if (sibling instanceof RuleNode) {
                    firstRuleIndex = i;
                    break;
                }
            }

            for (int i = priorSiblings.size() - 2; i >= 0; i--) {
                ParseTree<? extends Token> sibling = priorSiblings.get(i);
                if (!(sibling instanceof RuleNode)) {
                    continue;
                }

                if (i == firstRuleIndex || ParseTrees.elementStartsLine(sibling)) {
                    return Tuple.create(sibling, 0);
                }
            }

            // handle at the parent
            return null;

        case GrammarParser.RULE_parserRuleSpec:
            if (getTerminalNodeType(targetElement) == GrammarParser.AT) {
                return Tuple.create(container, 0);
            }

            ParserRuleSpecContext parserRuleSpecContext = getTypedRuleContext(container, ParserRuleSpecContext.class);
            if (parserRuleSpecContext == null) {
                return null;
            }
            
            if (parserRuleSpecContext.COLON() != null) {
                if (ParseTrees.startsBeforeStartOf(parserRuleSpecContext.COLON(), targetElement)) {
                    switch (getTerminalNodeType(targetElement)) {
                    case GrammarParser.SEMI:
                    case GrammarParser.OR:
                        return Tuple.create(parserRuleSpecContext.COLON(), 0);
                        
                    default:
                        return Tuple.create(parserRuleSpecContext.COLON(), getCodeStyle().getIndentSize());
                    }
                }
            }

            return Tuple.create(container, getCodeStyle().getIndentSize());

        case GrammarParser.RULE_lexerRule:
            if (getTerminalNodeType(targetElement) == GrammarParser.AT) {
                return Tuple.create(container, 0);
            }

            LexerRuleContext lexerRuleContext = getTypedRuleContext(container, LexerRuleContext.class);
            if (lexerRuleContext == null) {
                return null;
            }
            
            if (lexerRuleContext.COLON() != null) {
                if (ParseTrees.startsBeforeStartOf(lexerRuleContext.COLON(), targetElement)) {
                    switch (getTerminalNodeType(targetElement)) {
                    case GrammarParser.SEMI:
                    case GrammarParser.OR:
                        return Tuple.create(lexerRuleContext.COLON(), 0);
                        
                    default:
                        return Tuple.create(lexerRuleContext.COLON(), getCodeStyle().getIndentSize());
                    }
                }
            }

            return Tuple.create(container, getCodeStyle().getIndentSize());

        case GrammarParser.RULE_optionsSpec:
            // use previous option if any, otherwise use the block.
            // special handling for closing }
            if (targetElement == ((OptionsSpecContext)ruleContext).RBRACE()) {
                return Tuple.create(container, 0);
            }

            int firstOptionIndex = -1;
            for (int i = 0; i < priorSiblings.size(); i++) {
                ParseTree<?> sibling = priorSiblings.get(i);
                if (!(sibling instanceof RuleNode)) {
                    continue;
                }

                if (((RuleNode<?>)sibling).getRuleContext().getRuleIndex() == GrammarParser.RULE_option) {
                    firstOptionIndex = i;
                    break;
                }
            }

            boolean semi = getTerminalNodeType(targetElement) == GrammarParser.SEMI;
            for (int i = priorSiblings.size() - 2; i >= 0; i--) {
                ParseTree<? extends Token> sibling = priorSiblings.get(i);
                if (!(sibling instanceof RuleNode)) {
                    continue;
                }

                RuleContext<? extends Token> context = ((RuleNode<? extends Token>)sibling).getRuleContext();
                if (context.getRuleIndex() == GrammarParser.RULE_option) {
                    if (i == firstOptionIndex || ParseTrees.elementStartsLine(sibling)) {
                        return Tuple.create(sibling, semi ? getCodeStyle().getIndentSize() : 0);
                    }
                }
            }

            return Tuple.create(container, getCodeStyle().getIndentSize());

        case GrammarParser.RULE_tokensSpec:
        case GrammarParser.RULE_actionBlock:
            if (container.getChildCount() == 0 || targetElement == container.getChild(0)) {
                return null;
            }

            if (getTerminalNodeType(targetElement) == GrammarParser.RBRACE) {
                return Tuple.create(container, 0);
            }

            // align to the previous element
            for (int i = priorSiblings.size() - 2; i >= 0; i--) {
                ParseTree<? extends Token> sibling = priorSiblings.get(i);
                // stop at the first id rule, index 0 is the TOKENS terminal itself
                if (i == 1 || ParseTrees.elementStartsLine(sibling)) {
                    return Tuple.create(sibling, 0);
                }
            }

            return Tuple.create(container, getCodeStyle().getIndentSize());

        case GrammarParser.RULE_delegateGrammars:
            throw new NotImplementedException();

        case GrammarParser.RULE_delegateGrammar:
            throw new NotImplementedException();

        case GrammarParser.RULE_modeSpec:
            // use the preceeding rule (if any), otherwise relative to mode
            for (int i = priorSiblings.size() - 2; i >= 0; i--) {
                ParseTree<? extends Token> sibling = priorSiblings.get(i);
                if (!(sibling instanceof RuleNode)) {
                    continue;
                }

                RuleContext<? extends Token> context = ((RuleNode<? extends Token>)sibling).getRuleContext();
                if (context.getRuleIndex() == GrammarParser.RULE_ruleSpec) {
                    return Tuple.create(context, 0);
                }
            }

            return Tuple.create(container, getCodeStyle().getIndentSize());

        default:
            throw new UnsupportedOperationException("Unexpected ancestor node type.");
        }
    }

    @Override
    protected List<Anchor> getDynamicAnchorPoints() {
        List<Anchor> anchors;
        Future<ParserData<List<Anchor>>> result =
            getTaskManager().getData(getSnapshot(), GrammarParserDataDefinitions.DYNAMIC_ANCHOR_POINTS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
        try {
            anchors = result != null ? result.get().getData() : null;
        } catch (InterruptedException ex) {
            anchors = null;
        } catch (ExecutionException ex) {
            Exceptions.printStackTrace(ex);
            anchors = null;
        }

        return anchors;
    }

    @Override
    protected GrammarCodeStyle getCodeStyle() {
        if (codeStyle == null) {
            codeStyle = GrammarCodeStyle.getDefault(getContext().document());
        }

        return codeStyle;
    }

    private static final class GrammarReferenceAnchors extends ReferenceAnchors {

        private final int grammarType;

        public GrammarReferenceAnchors(int grammarType, Anchor previous, Anchor enclosing) {
            super(previous, enclosing);
            this.grammarType = grammarType;
        }

        public int getGrammarType() {
            return grammarType;
        }

    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=IndentTask.Factory.class)
    public static final class FactoryImpl implements Factory {

        @Override
        public IndentTask createTask(Context context) {
            return new GrammarIndentTask(context);
        }

    }
}
