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

import java.util.ArrayList;
import java.util.Collections;
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
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class CompletionParserATNSimulator extends ParserATNSimulator<Token> {

    private Map<ATNConfig, List<Transition>> caretTransitions;
    private CaretToken caretToken;

    private List<MultipleDecisionData> decisionPoints;
    private List<Integer> selections;

    public CompletionParserATNSimulator(@NonNull Parser parser, ATN atn) {
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
    }

    @Override
    public int adaptivePredict(SymbolStream<Token> input, int decision, ParserRuleContext<?> outerContext) {
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
    public int execATN(@NonNull SymbolStream<Token> input,
					   @NonNull DFA dfa,
					   int startIndex, @NonNull SimulatorState initialState)
	{
		if ( debug ) System.out.println("execATN decision "+dfa.decision+" exec LA(1)=="+ getLookaheadName(input));
		ATN_failover++;

		final ParserRuleContext<?> outerContext = initialState.outerContext;
		final boolean useContext = initialState.useContext;

		int t = input.LA(1);

        DecisionState decState = atn.getDecisionState(dfa.decision);
		boolean greedy = decState.isGreedy;
		SimulatorState previous = initialState;

        PredictionContextCache contextCache = new PredictionContextCache(dfa.isContextSensitive());
		while (true) { // while more work
            CodeCompletionSimulatorState nextState = computeReachSet(dfa, previous, t, greedy, contextCache);
            if (nextState == null) throw noViableAlt(input, outerContext, previous.s0.configs, startIndex);
            DFAState D = nextState.s0;
            ATNConfigSet reach = nextState.s0.configs;

            int predictedAlt = getUniqueAlt(reach);
            if (predictedAlt != ATN.INVALID_ALT_NUMBER) {
                D.isAcceptState = true;
//                D.configs.uniqueAlt = predictedAlt;
                D.prediction = predictedAlt;

                if (optimize_ll1
                    && input.index() == startIndex
                    && nextState.outerContext == nextState.remainingOuterContext
                    && dfa.decision != 0
                    && greedy
                    && !D.configs.hasSemanticContext())
                {
                    if (t >= 0 && t <= Short.MAX_VALUE) {
                        int key = (dfa.decision << 16) + t;
                        LL1Table.put(key, predictedAlt);
                    }
                }

                if (useContext && always_try_local_context) {
                    retry_with_context_indicates_no_conflict++;
                    reportContextSensitivity(dfa, reach, startIndex, input.index());
                }
            }
            else if (t == CaretToken.CARET_TOKEN_TYPE) {
//                        LOGGER.warning("The current implementation does not support multiple ambiguous alternatives."); //NOI18N
                caretTransitions = nextState.caretTransitions;
                caretToken = (CaretToken)input.LT(1);
                throw noViableAlt(input, outerContext, nextState.s0.configs, startIndex);
            }
            else {
				D.configs.conflictingAlts = getAmbiguousAlts(reach);
				if ( D.configs.conflictingAlts!=null ) {
					if ( greedy ) {
						D.isAcceptState = true;
						D.prediction = predictedAlt = resolveToMinAlt(D, D.configs.conflictingAlts);

						int k = input.index() - startIndex + 1; // how much input we used
//						System.out.println("used k="+k);
						if ( k == 1 || // SLL(1) == LL(1)
							 !userWantsCtxSensitive ||
							 !D.configs.getDipsIntoOuterContext() )
						{
							if ( !D.configs.hasSemanticContext() ) {
								reportAmbiguity(dfa, D, startIndex, input.index(), D.configs.conflictingAlts, D.configs);
							}
						}
						else {
                            int ambigIndex = input.index();

                            if ( D.isAcceptState && D.configs.hasSemanticContext() ) {
                                int nalts = decState.getNumberOfTransitions();
                                List<DFAState.PredPrediction> predPredictions =
                                    predicateDFAState(D, D.configs, outerContext, nalts);
                                if (predPredictions != null) {
                                    IntervalSet conflictingAlts = getConflictingAltsFromConfigSet(D.configs);
                                    if ( D.predicates.size() < conflictingAlts.size() ) {
                                        reportInsufficientPredicates(dfa, startIndex, ambigIndex,
                                                                        conflictingAlts,
                                                                        decState,
                                                                        getPredsForAmbigAlts(conflictingAlts, D.configs, nalts),
                                                                        D.configs,
                                                                        false);
                                    }
                                    input.seek(startIndex);
                                    predictedAlt = evalSemanticContext(predPredictions, outerContext, true);
                                    if ( predictedAlt!=ATN.INVALID_ALT_NUMBER ) {
                                        return predictedAlt;
                                    }
                                }
                            }

                            if ( debug ) System.out.println("RETRY with outerContext="+outerContext);

                            reportAttemptingFullContext(dfa, reach, startIndex, ambigIndex);
							input.seek(startIndex);
							dfa.setContextSensitive(true);
							return predictATN(dfa, input, outerContext, true);
						}
					}
					else {
						// upon ambiguity for nongreedy, default to exit branch to avoid inf loop
						// this handles case where we find ambiguity that stops DFA construction
						// before a config hits rule stop state. Was leaving prediction blank.
						int exitAlt = 2;
						D.isAcceptState = true; // when ambig or ctx sens or nongreedy or .* loop hitting rule stop
						D.prediction = predictedAlt = exitAlt;
					}
				}
			}

			if ( !greedy ) {
				int exitAlt = 2;
				if ( predictedAlt != ATN.INVALID_ALT_NUMBER && configWithAltAtStopState(reach, 1) ) {
					if ( debug ) System.out.println("nongreedy loop but unique alt "+D.configs.getUniqueAlt()+" at "+reach);
					// reaches end via .* means nothing after.
					D.isAcceptState = true;
					D.prediction = predictedAlt = exitAlt;
				}
				else {// if we reached end of rule via exit branch and decision nongreedy, we matched
					if ( configWithAltAtStopState(reach, exitAlt) ) {
						if ( debug ) System.out.println("nongreedy at stop state for exit branch");
						D.isAcceptState = true;
						D.prediction = predictedAlt = exitAlt;
					}
				}
			}

			if ( D.isAcceptState && D.configs.hasSemanticContext() ) {
				int nalts = decState.getNumberOfTransitions();
				List<DFAState.PredPrediction> predPredictions =
					predicateDFAState(D, D.configs, outerContext, nalts);
                if (predPredictions != null) {
                    IntervalSet conflictingAlts = getConflictingAltsFromConfigSet(D.configs);
                    if ( D.predicates.size() < conflictingAlts.size() ) {
                        reportInsufficientPredicates(dfa, startIndex, input.index(),
                                                    conflictingAlts,
                                                    decState,
                                                    getPredsForAmbigAlts(conflictingAlts, D.configs, nalts),
                                                    D.configs,
                                                    false);
                    }
                    input.seek(startIndex);
                    predictedAlt = evalSemanticContext(predPredictions, outerContext, false);
                    if ( predictedAlt!=ATN.INVALID_ALT_NUMBER ) {
                        return predictedAlt;
                    }
                }

                if (D.prediction == ATN.INVALID_ALT_NUMBER) {
                    throw noViableAlt(input, outerContext, D.configs, startIndex);
                }

                predictedAlt = D.prediction;
			}

			if ( D.isAcceptState ) return predictedAlt;

			previous = nextState;
			input.consume();
			t = input.LA(1);
		}
	}

    @Override
    protected boolean closure(ATNConfigSet configs, ATNConfigSet reach, PredictionContextCache contextCache, boolean contextSensitiveDfa, boolean greedy, boolean collectPredicates, boolean hasMoreContext) {
        return super.closure(configs, reach, contextCache, contextSensitiveDfa, greedy, collectPredicates, hasMoreContext);
    }

    @Override
    protected CodeCompletionSimulatorState computeReachSet(DFA dfa, SimulatorState previous, int t, boolean greedy, PredictionContextCache contextCache) {
		final boolean useContext = previous.useContext;
		RuleContext remainingGlobalContext = previous.remainingOuterContext;
		List<ATNConfig> closureConfigs = new ArrayList<ATNConfig>(previous.s0.configs);
		List<Integer> contextElements = null;
		ATNConfigSet reach = new ATNConfigSet(!useContext, mergeATNConfigs);
        LinkedHashMap<ATNConfig, List<Transition>> transitions = null;
        boolean stepIntoGlobal;
        do {
            boolean hasMoreContext = !useContext || remainingGlobalContext != null;
            ATNConfigSet reachIntermediate = new ATNConfigSet(!useContext, mergeATNConfigs);
            int ncl = closureConfigs.size();
            for (int ci=0; ci<ncl; ci++) { // TODO: foreach
                ATNConfig c = closureConfigs.get(ci);
                if ( debug ) System.out.println("testing "+getTokenName(t)+" at "+c.toString());

                List<Transition> configTransitions = null;

                int n = c.state.getNumberOfTransitions();
                for (int ti=0; ti<n; ti++) {               // for each transition
                    Transition trans = c.state.transition(ti);
                    ATNState target = getReachableTarget(trans, t);
                    if ( target!=null ) {
                        if (t == CaretToken.CARET_TOKEN_TYPE) {
                            if (transitions == null) {
                                transitions = new LinkedHashMap<ATNConfig, List<Transition>>();
                            }

                            if (configTransitions == null) {
                                configTransitions = new ArrayList<Transition>();
                                transitions.put(c, configTransitions);
                            }

                            configTransitions.add(trans);
                        }

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

        return new CodeCompletionSimulatorState(previous.outerContext, dfaState, useContext, (ParserRuleContext<?>)remainingGlobalContext, transitions);
    }

    private static final List<Integer> WORDLIKE_TOKEN_TYPES =
        new ArrayList<Integer>() {{
            // keywords
            add(GrammarLexer.OPTIONS);
            add(GrammarLexer.TOKENS);
            add(GrammarLexer.IMPORT);
            add(GrammarLexer.FRAGMENT);
            add(GrammarLexer.LEXER);
            add(GrammarLexer.PARSER);
            add(GrammarLexer.GRAMMAR);
            add(GrammarLexer.PROTECTED);
            add(GrammarLexer.PUBLIC);
            add(GrammarLexer.PRIVATE);
            add(GrammarLexer.RETURNS);
            add(GrammarLexer.LOCALS);
            add(GrammarLexer.THROWS);
            add(GrammarLexer.CATCH);
            add(GrammarLexer.FINALLY);
            add(GrammarLexer.TEMPLATE);
            add(GrammarLexer.MODE);
            // atoms
            add(GrammarLexer.RULE_REF);
            add(GrammarLexer.TOKEN_REF);
            add(GrammarLexer.ID);
            // special
            add(GrammarLexer.ARG_ACTION_WORD);
            add(GrammarLexer.ACTION_REFERENCE);
            add(GrammarLexer.ACTION_WORD);

            Collections.sort(this);
        }};

    @Override
    public ATNState getReachableTarget(Transition trans, int ttype) {
        if (ttype == CaretToken.CARET_TOKEN_TYPE) {
            if (trans instanceof AtomTransition) {
                AtomTransition at = (AtomTransition)trans;
                if (Collections.binarySearch(WORDLIKE_TOKEN_TYPES, at.label) >= 0) {
                    return at.target;
                }
            } else if (trans instanceof SetTransition) {
                SetTransition st = (SetTransition)trans;
                boolean not = trans instanceof NotSetTransition;
                for (int t : WORDLIKE_TOKEN_TYPES) {
                    if (!not && st.set.contains(t) || not && !st.set.contains(t)) {
                        return st.target;
                    }
                }
            } else if (trans instanceof RangeTransition) {
                RangeTransition rt = (RangeTransition)trans;
                int lb = Collections.binarySearch(WORDLIKE_TOKEN_TYPES, rt.from);
                int ub = Collections.binarySearch(WORDLIKE_TOKEN_TYPES, rt.to);
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
