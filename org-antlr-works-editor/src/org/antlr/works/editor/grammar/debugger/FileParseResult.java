/*
 *  Copyright (c) 2014 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */

package org.antlr.works.editor.grammar.debugger;

import java.util.Map;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.Nullable;
import org.antlr.v4.runtime.tree.ParseTree;
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
    public final long[] fullContextFallback;
    public final long[] nonSll;
    public final long[] ambiguousResult;
    public final long[] parserTotalTransitions;
    public final long[] parserComputedTransitions;
    public final long[] parserFullContextTransitions;

    public FileParseResult(String sourceName, int checksum, @Nullable ParseTree parseTree, int tokenCount, long startTime, Lexer lexer, Parser parser) {
        this.sourceName = sourceName;
        this.checksum = checksum;
        this.parseTree = parseTree;
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
                fullContextFallback = ((StatisticsParserATNSimulator)interpreter).fullContextFallback;
                nonSll = ((StatisticsParserATNSimulator)interpreter).nonSll;
                ambiguousResult = ((StatisticsParserATNSimulator)interpreter).ambiguousResult;
                parserTotalTransitions = ((StatisticsParserATNSimulator)interpreter).totalTransitions;
                parserComputedTransitions = ((StatisticsParserATNSimulator)interpreter).computedTransitions;
                parserFullContextTransitions = ((StatisticsParserATNSimulator)interpreter).fullContextTransitions;
            } else {
                decisionInvocations = new long[0];
                fullContextFallback = new long[0];
                nonSll = new long[0];
                ambiguousResult = new long[0];
                parserTotalTransitions = new long[0];
                parserComputedTransitions = new long[0];
                parserFullContextTransitions = new long[0];
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
            fullContextFallback = new long[0];
            nonSll = new long[0];
            ambiguousResult = new long[0];
            parserTotalTransitions = new long[0];
            parserComputedTransitions = new long[0];
            parserFullContextTransitions = new long[0];
        }
    }
}
