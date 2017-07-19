/*
 *  Copyright (c) 2014 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.util.BitSet;
import org.antlr.runtime.Token;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.atn.SimulatorState;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.dfa.DFAState;
import org.antlr.v4.runtime.misc.Tuple2;

/**
 *
 * @author Sam Harwell
 */
public class StatisticsParserATNSimulator extends ParserATNSimulator {

    public final long[] decisionInvocations;
    public final long[] decisionCost;
    public final long[] decisionLlCost;
    public final long[] fullContextFallback;
    public final long[] nonSll;
    public final long[] ambiguousResult;
    public final long[] totalTransitions;
    public final long[] computedTransitions;
    public final long[] fullContextTransitions;
    public final long[] totalLookaheadSll;
    public final long[] totalLookaheadLl;
    public final long[] minLookaheadSll;
    public final long[] maxLookaheadSll;
    public final long[] minLookaheadLl;
    public final long[] maxLookaheadLl;

    private int decision;
    private boolean reportedLookahead;

    public StatisticsParserATNSimulator(ATN atn) {
        super(atn);
        decisionInvocations = new long[atn.decisionToState.size()];
        decisionCost = new long[atn.decisionToState.size()];
        decisionLlCost = new long[atn.decisionToState.size()];
        fullContextFallback = new long[atn.decisionToState.size()];
        nonSll = new long[atn.decisionToState.size()];
        ambiguousResult = new long[atn.decisionToState.size()];
        totalTransitions = new long[atn.decisionToState.size()];
        computedTransitions = new long[atn.decisionToState.size()];
        fullContextTransitions = new long[atn.decisionToState.size()];
        totalLookaheadSll = new long[atn.decisionToState.size()];
        totalLookaheadLl = new long[atn.decisionToState.size()];
        minLookaheadSll = new long[atn.decisionToState.size()];
        maxLookaheadSll = new long[atn.decisionToState.size()];
        minLookaheadLl = new long[atn.decisionToState.size()];
        maxLookaheadLl = new long[atn.decisionToState.size()];
        for (int i = 0; i < minLookaheadSll.length; i++) {
            minLookaheadSll[i] = Long.MAX_VALUE;
            minLookaheadLl[i] = Long.MAX_VALUE;
            maxLookaheadSll[i] = Long.MIN_VALUE;
            maxLookaheadLl[i] = Long.MIN_VALUE;
        }
    }

    public StatisticsParserATNSimulator(Parser parser, ATN atn) {
        super(parser, atn);
        decisionInvocations = new long[atn.decisionToState.size()];
        decisionCost = new long[atn.decisionToState.size()];
        decisionLlCost = new long[atn.decisionToState.size()];
        fullContextFallback = new long[atn.decisionToState.size()];
        nonSll = new long[atn.decisionToState.size()];
        ambiguousResult = new long[atn.decisionToState.size()];
        totalTransitions = new long[atn.decisionToState.size()];
        computedTransitions = new long[atn.decisionToState.size()];
        fullContextTransitions = new long[atn.decisionToState.size()];
        totalLookaheadSll = new long[atn.decisionToState.size()];
        totalLookaheadLl = new long[atn.decisionToState.size()];
        minLookaheadSll = new long[atn.decisionToState.size()];
        maxLookaheadSll = new long[atn.decisionToState.size()];
        minLookaheadLl = new long[atn.decisionToState.size()];
        maxLookaheadLl = new long[atn.decisionToState.size()];
        for (int i = 0; i < minLookaheadSll.length; i++) {
            minLookaheadSll[i] = Long.MAX_VALUE;
            minLookaheadLl[i] = Long.MAX_VALUE;
            maxLookaheadSll[i] = Long.MIN_VALUE;
            maxLookaheadLl[i] = Long.MIN_VALUE;
        }
    }

    @Override
    public int adaptivePredict(TokenStream input, int decision, ParserRuleContext outerContext) {
        try {
            this.decision = decision;
            this.reportedLookahead = false;
            decisionInvocations[decision]++;
            return super.adaptivePredict(input, decision, outerContext);
        } finally {
            this.decision = -1;
        }
    }

    @Override
    protected SimulatorState computeStartState(DFA dfa, ParserRuleContext globalContext, boolean useContext) {
        long startTime = System.nanoTime();
        try {
            return super.computeStartState(dfa, globalContext, useContext);
        } finally {
            long totalTime = System.nanoTime() - startTime;
            decisionCost[dfa.decision] += totalTime;
            if (useContext) {
                decisionLlCost[dfa.decision] += totalTime;
            }
        }
    }

    @Override
    protected int execDFA(DFA dfa, TokenStream input, int startIndex, SimulatorState state) {
        int result = super.execDFA(dfa, input, startIndex, state);
        if (!reportedLookahead) {
            int stopIndex = input.index();
            int k;
            if (startIndex == stopIndex) {
                k = 1;
            } else if (startIndex == stopIndex - 1) {
                k = 2;
            } else {
                k = 0;
                for (int i = startIndex; i <= stopIndex; i++) {
                    input.seek(i);
                    if (input.LT(1).getChannel() == Token.DEFAULT_CHANNEL) {
                        k++;
                    }
                }
            }

            if (!state.useContext) {
                totalLookaheadSll[dfa.decision] += k;
                minLookaheadSll[dfa.decision] = Math.min(minLookaheadSll[dfa.decision], k);
                maxLookaheadSll[dfa.decision] = Math.max(maxLookaheadSll[dfa.decision], k);
            }
            else {
                totalLookaheadLl[dfa.decision] += k;
                minLookaheadLl[dfa.decision] = Math.min(minLookaheadLl[dfa.decision], k);
                maxLookaheadLl[dfa.decision] = Math.max(maxLookaheadLl[dfa.decision], k);
            }

            reportedLookahead = true;
        }

        return result;
    }

    @Override
    protected int execATN(DFA dfa, TokenStream input, int startIndex, SimulatorState initialState) {
        int result = super.execATN(dfa, input, startIndex, initialState);
        if (!reportedLookahead) {
            int stopIndex = input.index();
            int k;
            if (startIndex == stopIndex) {
                k = 1;
            } else if (startIndex == stopIndex - 1) {
                k = 2;
            } else {
                k = 0;
                for (int i = startIndex; i <= stopIndex; i++) {
                    input.seek(i);
                    if (input.LT(1).getChannel() == Token.DEFAULT_CHANNEL) {
                        k++;
                    }
                }
            }

            if (!initialState.useContext) {
                totalLookaheadSll[dfa.decision] += k;
                minLookaheadSll[dfa.decision] = Math.min(minLookaheadSll[dfa.decision], k);
                maxLookaheadSll[dfa.decision] = Math.max(maxLookaheadSll[dfa.decision], k);
            }
            else {
                totalLookaheadLl[dfa.decision] += k;
                minLookaheadLl[dfa.decision] = Math.min(minLookaheadLl[dfa.decision], k);
                maxLookaheadLl[dfa.decision] = Math.max(maxLookaheadLl[dfa.decision], k);
            }

            reportedLookahead = true;
        }

        return result;
    }

    @Override
    protected DFAState getExistingTargetState(DFAState previousD, int t) {
        totalTransitions[decision]++;
        return super.getExistingTargetState(previousD, t);
    }

    @Override
    protected Tuple2<DFAState, ParserRuleContext> computeTargetState(DFA dfa, DFAState s, ParserRuleContext remainingGlobalContext, int t, boolean useContext, PredictionContextCache contextCache) {
        computedTransitions[decision]++;

        long startTime = System.nanoTime();
        try {
            return super.computeTargetState(dfa, s, remainingGlobalContext, t, useContext, contextCache);
        } finally {
            long totalTime = System.nanoTime() - startTime;
            decisionCost[dfa.decision] += totalTime;
            if (useContext) {
                decisionLlCost[dfa.decision] += totalTime;
            }
        }
    }

    @Override
    protected SimulatorState computeReachSet(DFA dfa, SimulatorState previous, int t, PredictionContextCache contextCache) {
        if (previous.useContext) {
            totalTransitions[decision]++;
            computedTransitions[decision]++;
            fullContextTransitions[decision]++;
        }

        return super.computeReachSet(dfa, previous, t, contextCache);
    }

    @Override
    protected void reportAttemptingFullContext(DFA dfa, BitSet conflictingAlts, SimulatorState conflictState, int startIndex, int stopIndex) {
        super.reportAttemptingFullContext(dfa, conflictingAlts, conflictState, startIndex, stopIndex);
            int k;
            if (startIndex == stopIndex) {
                k = 1;
            } else if (startIndex == stopIndex - 1) {
                k = 2;
            } else {
                k = 0;
                for (int i = startIndex; i <= stopIndex; i++) {
                    parser.getInputStream().seek(i);
                    if (parser.getInputStream().LT(1).getChannel() == Token.DEFAULT_CHANNEL) {
                        k++;
                    }
                }
            }

        fullContextFallback[dfa.decision]++;
        totalLookaheadSll[dfa.decision] += k;
        minLookaheadSll[dfa.decision] = Math.min(minLookaheadSll[dfa.decision], k);
        maxLookaheadSll[dfa.decision] = Math.max(maxLookaheadSll[dfa.decision], k);
    }
}
