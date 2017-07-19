/*
 *  Copyright (c) 2014 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */

package org.antlr.works.editor.grammar.debugger;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.Nullable;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.grammar.debugger.ParserDebuggerReferenceAnchorsParserTask.TracingParserInterpreter;

/**
 *
 * @author Sam Harwell
 */
public class FileParseResult {
    public final String sourceName;
    public final int checksum;
    public final ParseTree parseTree;
    public final Map<ParseTree, Transition> associatedTransitions;
    public final int tokenCount;
    public final long startTime;
    public final long endTime;

    public final int lexerDFASize;
    public final long lexerTotalTransitions;
    public final long lexerComputedTransitions;

    public final int parserDFASize;
    public final long[] decisionInvocations;
    public final long[] decisionCost;
    public final long[] decisionLlCost;
    public final long[] fullContextFallback;
    public final long[] nonSll;
    public final long[] ambiguousResult;
    public final long[] parserTotalTransitions;
    public final long[] parserComputedTransitions;
    public final long[] parserFullContextTransitions;
    public final long[] totalLookaheadSll;
    public final long[] totalLookaheadLl;
    public final long[] minLookaheadSll;
    public final long[] maxLookaheadSll;
    public final long[] minLookaheadLl;
    public final long[] maxLookaheadLl;

    private final List<? extends SyntaxError> syntaxErrors;

    public FileParseResult(String sourceName, int checksum, @Nullable ParseTree parseTree, List<? extends SyntaxError> syntaxErrors, int tokenCount, long startTime, Lexer lexer, Parser parser) {
        this.sourceName = sourceName;
        this.checksum = checksum;
        this.parseTree = parseTree;
        this.syntaxErrors = syntaxErrors;
        this.tokenCount = tokenCount;
        this.startTime = startTime;
        this.endTime = System.nanoTime();

        if (lexer != null) {
            LexerATNSimulator interpreter = lexer.getInterpreter();
            if (interpreter instanceof StatisticsLexerATNSimulator) {
                lexerTotalTransitions = ((StatisticsLexerATNSimulator)interpreter).totalTransitions;
                lexerComputedTransitions = ((StatisticsLexerATNSimulator)interpreter).computedTransitions;
            } else {
                lexerTotalTransitions = 0;
                lexerComputedTransitions = 0;
            }

            int dfaSize = 0;
            for (DFA dfa : interpreter.atn.decisionToDFA) {
                if (dfa != null) {
                    dfaSize += dfa.states.size();
                }
            }

            lexerDFASize = dfaSize;
        } else {
            lexerDFASize = 0;
            lexerTotalTransitions = 0;
            lexerComputedTransitions = 0;
        }

        if (parser != null) {
            if (parser instanceof TracingParserInterpreter) {
                associatedTransitions = ((TracingParserInterpreter)parser).associatedTransitions;
            } else {
                associatedTransitions = null;
            }

            ParserATNSimulator interpreter = parser.getInterpreter();
            if (interpreter instanceof StatisticsParserATNSimulator) {
                decisionInvocations = ((StatisticsParserATNSimulator)interpreter).decisionInvocations;
                decisionCost = ((StatisticsParserATNSimulator)interpreter).decisionCost;
                decisionLlCost = ((StatisticsParserATNSimulator)interpreter).decisionLlCost;
                fullContextFallback = ((StatisticsParserATNSimulator)interpreter).fullContextFallback;
                nonSll = ((StatisticsParserATNSimulator)interpreter).nonSll;
                ambiguousResult = ((StatisticsParserATNSimulator)interpreter).ambiguousResult;
                parserTotalTransitions = ((StatisticsParserATNSimulator)interpreter).totalTransitions;
                parserComputedTransitions = ((StatisticsParserATNSimulator)interpreter).computedTransitions;
                parserFullContextTransitions = ((StatisticsParserATNSimulator)interpreter).fullContextTransitions;
                totalLookaheadSll = ((StatisticsParserATNSimulator)interpreter).totalLookaheadSll;
                totalLookaheadLl = ((StatisticsParserATNSimulator)interpreter).totalLookaheadLl;
                minLookaheadSll = ((StatisticsParserATNSimulator)interpreter).minLookaheadSll;
                maxLookaheadSll = ((StatisticsParserATNSimulator)interpreter).maxLookaheadSll;
                minLookaheadLl = ((StatisticsParserATNSimulator)interpreter).minLookaheadLl;
                maxLookaheadLl = ((StatisticsParserATNSimulator)interpreter).maxLookaheadLl;
            } else {
                decisionInvocations = new long[0];
                decisionCost = new long[0];
                decisionLlCost = new long[0];
                fullContextFallback = new long[0];
                nonSll = new long[0];
                ambiguousResult = new long[0];
                parserTotalTransitions = new long[0];
                parserComputedTransitions = new long[0];
                parserFullContextTransitions = new long[0];
                totalLookaheadSll = new long[0];
                totalLookaheadLl = new long[0];
                minLookaheadSll = new long[0];
                maxLookaheadSll = new long[0];
                minLookaheadLl = new long[0];
                maxLookaheadLl = new long[0];
            }

            int dfaSize = 0;
            for (DFA dfa : interpreter.atn.decisionToDFA) {
                if (dfa != null) {
                    dfaSize += dfa.states.size();
                }
            }

            parserDFASize = dfaSize;
        } else {
            associatedTransitions = null;
            parserDFASize = 0;
            decisionInvocations = new long[0];
            decisionCost = new long[0];
            decisionLlCost = new long[0];
            fullContextFallback = new long[0];
            nonSll = new long[0];
            ambiguousResult = new long[0];
            parserTotalTransitions = new long[0];
            parserComputedTransitions = new long[0];
            parserFullContextTransitions = new long[0];
            totalLookaheadSll = new long[0];
            totalLookaheadLl = new long[0];
            minLookaheadSll = new long[0];
            maxLookaheadSll = new long[0];
            minLookaheadLl = new long[0];
            maxLookaheadLl = new long[0];
        }
    }

    public List<? extends SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

    public Set<ParseTree> getErrorNodes() {
        ErrorNodeAnalyzer analyzer = new ErrorNodeAnalyzer();
        return analyzer.analyzeTree(parseTree, syntaxErrors);
    }

    private static class ErrorNodeAnalyzer {

        public ErrorNodeAnalyzer() {
        }

        public Set<ParseTree> analyzeTree(ParseTree tree, List<? extends SyntaxError> syntaxErrors) {
            // eventually check syntax errors in case skipped nodes from errors were omitted from the tree
            Set<ParseTree> result = new HashSet<>();
            ErrorNodeListener listener = new ErrorNodeListener(result);
            ParseTreeWalker.DEFAULT.walk(listener, tree);
            return result;
        }

        private static class ErrorNodeListener implements ParseTreeListener {
            private final Set<ParseTree> result;

            private ErrorNodeListener(Set<ParseTree> result) {
                this.result = result;
            }

            @Override
            public void visitTerminal(TerminalNode node) {
            }

            @Override
            public void visitErrorNode(ErrorNode node) {
                result.add(node);
            }

            @Override
            public void enterEveryRule(ParserRuleContext ctx) {
                if (ctx.exception != null) {
                    result.add(ctx);
                }
            }

            @Override
            public void exitEveryRule(ParserRuleContext ctx) {
                if (result.contains(ctx) && ctx.getParent() != null) {
                    result.add(ctx.getParent());
                }
            }
        }
    }
}
