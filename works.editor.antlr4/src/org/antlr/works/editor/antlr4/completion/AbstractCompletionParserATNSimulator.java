/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.completion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.AtomTransition;
import org.antlr.v4.runtime.atn.NotSetTransition;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.atn.RangeTransition;
import org.antlr.v4.runtime.atn.SetTransition;
import org.antlr.v4.runtime.atn.SimulatorState;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.atn.WildcardTransition;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.dfa.DFAState;
import org.antlr.v4.runtime.misc.IntegerList;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractCompletionParserATNSimulator extends ParserATNSimulator {

    private Map<ATNConfig, List<Transition>> caretTransitions;
    private CaretToken caretToken;

    private List<MultipleDecisionData> decisionPoints;
    private IntegerList selections;
    private int _firstDecisionIndex;

    // state variables used for the custom implementation
    private TokenStream _input;
    private int _startIndex;
    private ParserRuleContext _outerContext;

    /** Avoid throwing an exception when the caret is found while computing the start state. */
    private boolean _computingStartState;

    public AbstractCompletionParserATNSimulator(@NonNull Parser parser, ATN atn) {
        super(parser, atn);
        Parameters.notNull("parser", parser);
        setPredictionMode(PredictionMode.SLL);
    }

    public Map<ATNConfig, List<Transition>> getCaretTransitions() {
        return caretTransitions;
    }

    public CaretToken getCaretToken() {
        return caretToken;
    }

    public Parser getParser() {
        return parser;
    }

    public void setFixedDecisions(List<MultipleDecisionData> decisionPoints, IntegerList selections) {
        Parameters.notNull("decisionPoints", decisionPoints);
        Parameters.notNull("selections", selections);
        this.decisionPoints = decisionPoints;
        this.selections = selections;
        this.caretTransitions = null;
        if (decisionPoints != null && !decisionPoints.isEmpty()) {
            _firstDecisionIndex = decisionPoints.get(0).inputIndex;
        } else {
            _firstDecisionIndex = Integer.MAX_VALUE;
        }
    }

    @Override
    public int adaptivePredict(TokenStream input, int decision, ParserRuleContext outerContext) {
        _input = input;
        _startIndex = input.index();
        _outerContext = outerContext;
        caretTransitions = null;

        if (decisionPoints != null && _firstDecisionIndex <= _startIndex) {
            int index = input.index();
            for (int i = 0; i < decisionPoints.size(); i++) {
                if (decisionPoints.get(i).inputIndex == index && decisionPoints.get(i).decision == decision) {
                    return decisionPoints.get(i).alternatives[selections.get(i)];
                }
            }
        }

        return super.adaptivePredict(input, decision, outerContext);
    }

    @Override
    public SimulatorState computeStartState(DFA dfa, ParserRuleContext globalContext, boolean useContext) {
        _computingStartState = true;
        try {
            return super.computeStartState(dfa, globalContext, useContext);
        } finally {
            _computingStartState = false;
        }
    }

    @Override
    protected DFAState createDFAState(DFA dfa, ATNConfigSet configs) {
        int t = _input.LA(1);
        if (t == CaretToken.CARET_TOKEN_TYPE && !_computingStartState) {
            caretToken = (CaretToken)_input.LT(1);
            throw noViableAlt(_input, _outerContext, configs, _startIndex);
        }

        return super.createDFAState(dfa, configs);
    }

    @Override
    protected void closure(ATNConfigSet sourceConfigs, ATNConfigSet configs, boolean collectPredicates, boolean hasMoreContext, PredictionContextCache contextCache, boolean treatEofAsEpsilon) {
        super.closure(sourceConfigs, configs, collectPredicates, hasMoreContext, contextCache, treatEofAsEpsilon);
    }

    protected abstract IntervalSet getWordlikeTokenTypes();

    @Override
    public ATNState getReachableTarget(ATNConfig source, Transition trans, int ttype) {
        if (ttype == CaretToken.CARET_TOKEN_TYPE) {
            ATNState target = null;
            if (trans instanceof AtomTransition) {
                AtomTransition at = (AtomTransition)trans;
                if (getWordlikeTokenTypes().contains(at.label)) {
                    target = at.target;
                }
            } else if (trans instanceof SetTransition) {
                SetTransition st = (SetTransition)trans;
                boolean not = trans instanceof NotSetTransition;
                // TODO: this could probably be done with an intersects method?
                for (int t : getWordlikeTokenTypes().toArray()) {
                    if (!not && st.set.contains(t) || not && !st.set.contains(t)) {
                        target = st.target;
                        break;
                    }
                }
            } else if (trans instanceof RangeTransition) {
                RangeTransition rt = (RangeTransition)trans;
                // TODO: there must be a better algorithm here :)
                int[] wordlikeTokenTypes = getWordlikeTokenTypes().toArray();
                int lb = Arrays.binarySearch(wordlikeTokenTypes, rt.from);
                int ub = Arrays.binarySearch(wordlikeTokenTypes, rt.to);
                if (lb >= 0 || ub >= 0 || lb != ub) {
                    target = rt.target;
                }
            } else if (trans instanceof WildcardTransition) {
                target = trans.target;
            }

            if (caretTransitions == null) {
                caretTransitions = new LinkedHashMap<>();
            }

            List<Transition> configTransitions = caretTransitions.get(source);
            if (configTransitions == null) {
                configTransitions = new ArrayList<>();
                caretTransitions.put(source, configTransitions);
            }

            configTransitions.add(trans);
            return target;
        }

        return super.getReachableTarget(source, trans, ttype);
    }
}
