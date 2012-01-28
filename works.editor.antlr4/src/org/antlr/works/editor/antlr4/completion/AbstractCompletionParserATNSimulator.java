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
package org.antlr.works.editor.antlr4.completion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.SymbolStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.AtomTransition;
import org.antlr.v4.runtime.atn.DecisionState;
import org.antlr.v4.runtime.atn.NotSetTransition;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContext;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.atn.RangeTransition;
import org.antlr.v4.runtime.atn.SetTransition;
import org.antlr.v4.runtime.atn.SimulatorState;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.atn.WildcardTransition;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.dfa.DFAState;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractCompletionParserATNSimulator extends ParserATNSimulator<Token> {

    private Map<ATNConfig, List<Transition>> caretTransitions;
    private CaretToken caretToken;

    private List<MultipleDecisionData> decisionPoints;
    private List<Integer> selections;

    // state variables used for the custom implementation
    private SymbolStream<Token> _input;
    private int _startIndex;
    private ParserRuleContext<?> _outerContext;
    private SimulatorState _nextState;

    public AbstractCompletionParserATNSimulator(@NonNull Parser parser, ATN atn) {
        super(parser, atn);
        Parameters.notNull("parser", parser);
        disable_global_context = true;
    }

    public Map<ATNConfig, List<Transition>> getCaretTransitions() {
        return caretTransitions;
    }

    public CaretToken getCaretToken() {
        return caretToken;
    }

    public void setFixedDecisions(List<MultipleDecisionData> decisionPoints, List<Integer> selections) {
        Parameters.notNull("decisionPoints", decisionPoints);
        Parameters.notNull("selections", selections);
        this.decisionPoints = decisionPoints;
        this.selections = selections;
        this.caretTransitions = null;
    }

    @Override
    public int adaptivePredict(SymbolStream<Token> input, int decision, ParserRuleContext<?> outerContext) {
        _input = input;
        _startIndex = input.index();
        _outerContext = outerContext;
        _nextState = null;

        if (decisionPoints != null) {
            int index = input.index();
            for (int i = 0; i < decisionPoints.size(); i++) {
                if (decisionPoints.get(i).inputIndex == index && (decision == 0 || decisionPoints.get(i).decision == decision)) {
                    return decisionPoints.get(i).alternatives[selections.get(i)];
                }
            }
        }

        return super.adaptivePredict(input, decision, outerContext);
    }

    @Override
    public int getUniqueAlt(Collection<ATNConfig> configs) {
        int result = super.getUniqueAlt(configs);

        int t = _input.LA(1);
        if (t == CaretToken.CARET_TOKEN_TYPE) {
            caretToken = (CaretToken)_input.LT(1);
            throw noViableAlt(_input, _outerContext, _nextState.s0.configs, _startIndex);
        }

        return result;
    }

    @Override
    public boolean closure(ATNConfigSet configs, ATNConfigSet reach, PredictionContextCache contextCache, boolean contextSensitiveDfa, boolean greedy, boolean collectPredicates, boolean hasMoreContext) {
        return super.closure(configs, reach, contextCache, contextSensitiveDfa, greedy, collectPredicates, hasMoreContext);
    }

    @Override
    protected SimulatorState computeReachSet(DFA dfa, SimulatorState previous, int t, boolean greedy, PredictionContextCache contextCache) {
		final boolean useContext = previous.useContext;
		RuleContext remainingGlobalContext = previous.remainingOuterContext;
		List<ATNConfig> closureConfigs = new ArrayList<ATNConfig>(previous.s0.configs);
		List<Integer> contextElements = null;
		ATNConfigSet reach = new ATNConfigSet(!useContext);
        caretTransitions = null;
        boolean stepIntoGlobal;
        do {
            boolean hasMoreContext = !useContext || remainingGlobalContext != null;
            ATNConfigSet reachIntermediate = new ATNConfigSet(!useContext);
            int ncl = closureConfigs.size();
            for (int ci=0; ci<ncl; ci++) { // TODO: foreach
                ATNConfig c = closureConfigs.get(ci);
                if ( debug ) System.out.println("testing "+getTokenName(t)+" at "+c.toString());

// BEGIN CC
                List<Transition> configTransitions = null;
// END CC

                int n = c.state.getNumberOfTransitions();
                for (int ti=0; ti<n; ti++) {               // for each transition
                    Transition trans = c.state.transition(ti);
                    ATNState target = getReachableTarget(trans, t);
                    if ( target!=null ) {
// BEGIN CC
                        if (t == CaretToken.CARET_TOKEN_TYPE) {
                            if (caretTransitions == null) {
                                caretTransitions = new LinkedHashMap<ATNConfig, List<Transition>>();
                            }

                            if (configTransitions == null) {
                                configTransitions = new ArrayList<Transition>();
                                caretTransitions.put(c, configTransitions);
                            }

                            configTransitions.add(trans);
                        }
// END CC

                        reachIntermediate.add(new ATNConfig(c, target));
                    }
                }
            }

            final boolean collectPredicates = false;
            stepIntoGlobal = closure(reachIntermediate, reach, contextCache, dfa.isContextSensitive(), greedy, collectPredicates, hasMoreContext);

            if (previous.useContext && stepIntoGlobal) {
                reach.clear();

                int nextContextElement = remainingGlobalContext.isEmpty() ? PredictionContext.EMPTY_STATE_KEY : remainingGlobalContext.invokingState;
                if (contextElements == null) {
                    contextElements = new ArrayList<Integer>();
                }

                if (remainingGlobalContext.isEmpty()) {
                    remainingGlobalContext = null;
                } else {
                    remainingGlobalContext = remainingGlobalContext.parent;
                }

                contextElements.add(nextContextElement);
                if (nextContextElement != PredictionContext.EMPTY_STATE_KEY) {
                    for (int i = 0; i < closureConfigs.size(); i++) {
                        closureConfigs.set(i, closureConfigs.get(i).appendContext(nextContextElement));
                    }
                }
            }
        } while (useContext && stepIntoGlobal);

        if (reach.isEmpty()) {
            return null;
        }

        DFAState dfaState = null;
        if (previous.s0 != null) {
            dfaState = addDFAEdge(dfa, previous.s0.configs, t, contextElements, reach);
        }

        _nextState = new SimulatorState(previous.outerContext, dfaState, useContext, (ParserRuleContext<?>)remainingGlobalContext);
        return _nextState;
    }

    protected abstract IntervalSet getWordlikeTokenTypes();

    @Override
    public ATNState getReachableTarget(Transition trans, int ttype) {
        if (ttype == CaretToken.CARET_TOKEN_TYPE) {
            if (trans instanceof AtomTransition) {
                AtomTransition at = (AtomTransition)trans;
                if (getWordlikeTokenTypes().contains(at.label)) {
                    return at.target;
                }
            } else if (trans instanceof SetTransition) {
                SetTransition st = (SetTransition)trans;
                boolean not = trans instanceof NotSetTransition;
                // TODO: this could probably be done with an intersects method?
                for (int t : getWordlikeTokenTypes().toArray()) {
                    if (!not && st.set.contains(t) || not && !st.set.contains(t)) {
                        return st.target;
                    }
                }
            } else if (trans instanceof RangeTransition) {
                RangeTransition rt = (RangeTransition)trans;
                // TODO: there must be a better algorithm here :)
                int[] wordlikeTokenTypes = getWordlikeTokenTypes().toArray();
                int lb = Arrays.binarySearch(wordlikeTokenTypes, rt.from);
                int ub = Arrays.binarySearch(wordlikeTokenTypes, rt.to);
                if (lb >= 0 || ub >= 0 || lb != ub) {
                    return rt.target;
                }
            } else if (trans instanceof WildcardTransition) {
                return trans.target;
            }

            return null;
        }

        return super.getReachableTarget(trans, ttype);
    }
}
