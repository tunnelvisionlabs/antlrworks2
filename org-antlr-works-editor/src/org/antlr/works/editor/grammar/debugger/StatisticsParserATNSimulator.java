/*
 *  Copyright (c) 2014 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

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
    public final long[] fullContextFallback;
    public final long[] nonSll;
    public final long[] ambiguousResult;
    public final long[] totalTransitions;
    public final long[] computedTransitions;
    public final long[] fullContextTransitions;

    private int decision;

    public StatisticsParserATNSimulator(ATN atn) {
        super(atn);
        decisionInvocations = new long[atn.decisionToState.size()];
        fullContextFallback = new long[atn.decisionToState.size()];
        nonSll = new long[atn.decisionToState.size()];
        ambiguousResult = new long[atn.decisionToState.size()];
        totalTransitions = new long[atn.decisionToState.size()];
        computedTransitions = new long[atn.decisionToState.size()];
        fullContextTransitions = new long[atn.decisionToState.size()];
    }

    public StatisticsParserATNSimulator(Parser parser, ATN atn) {
        super(parser, atn);
        decisionInvocations = new long[atn.decisionToState.size()];
        fullContextFallback = new long[atn.decisionToState.size()];
        nonSll = new long[atn.decisionToState.size()];
        ambiguousResult = new long[atn.decisionToState.size()];
        totalTransitions = new long[atn.decisionToState.size()];
        computedTransitions = new long[atn.decisionToState.size()];
        fullContextTransitions = new long[atn.decisionToState.size()];
    }

    @Override
    public int adaptivePredict(TokenStream input, int decision, ParserRuleContext outerContext) {
        try {
            this.decision = decision;
            decisionInvocations[decision]++;
            return super.adaptivePredict(input, decision, outerContext);
        } finally {
            this.decision = -1;
        }
    }

    @Override
    public int adaptivePredict(TokenStream input, int decision, ParserRuleContext outerContext, boolean useContext) {
        if (useContext) {
            fullContextFallback[decision]++;
        }

        return super.adaptivePredict(input, decision, outerContext, useContext);
    }

    @Override
    protected DFAState getExistingTargetState(DFAState previousD, int t) {
        totalTransitions[decision]++;
        return super.getExistingTargetState(previousD, t);
    }

    @Override
    protected Tuple2<DFAState, ParserRuleContext> computeTargetState(DFA dfa, DFAState s, ParserRuleContext remainingGlobalContext, int t, boolean useContext, PredictionContextCache contextCache) {
        computedTransitions[decision]++;
        return super.computeTargetState(dfa, s, remainingGlobalContext, t, useContext, contextCache);
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

}
