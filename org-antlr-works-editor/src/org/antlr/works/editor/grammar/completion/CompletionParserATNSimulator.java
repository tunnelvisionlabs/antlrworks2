/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
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

import java.util.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.dfa.DFAState;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
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

    public CompletionParserATNSimulator(ATN atn) {
        super(atn);
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
    public boolean closure(ATNConfig config, ATNConfigSet configs, DecisionState decState, Set<ATNConfig> closureBusy, boolean collectPredicates, boolean hasMoreContexts, int globalContextCount, int depth) {
        return super.closure(config, configs, decState, closureBusy, collectPredicates, hasMoreContexts, globalContextCount, depth);
    }

    @Override
    public int execATN(SymbolStream<Token> input, DFA dfa, int startIndex, ATNConfigSet s0, ParserRuleContext<?> globalContext, boolean useContext, int consumedContextFrames) {
        if ( debug ) System.out.println("execATN decision "+dfa.decision+" exec LA(1)=="+ getLookaheadName(input));
        ATN_failover++;
        ATNConfigSet closure = new ATNConfigSet(!dfa.isContextSensitive, mergeATNConfigs);

        closure.addAll(s0);

        if ( debug ) System.out.println("start state closure="+closure);

        int t = input.LA(1);
        if ( t==Token.EOF && prevAccept!=null ) {
            // computeStartState must have reached end of rule
            return prevAccept.alt;
        }

        @NonNull DecisionState decState = null;
        if ( atn.decisionToState.size()>0 ) {
            decState = atn.decisionToState.get(dfa.decision);
            if ( debug ) System.out.println("decision state = "+decState);
        }

        prevAccept = null;
        prevAcceptIndex = -1;
        ATNConfigSet reach = new ATNConfigSet(!dfa.isContextSensitive, mergeATNConfigs);
        LinkedHashMap<ATNConfig, List<Transition>> transitions = null;

        RuleContext remainingGlobalContext = getRemainingContext(globalContext, consumedContextFrames);

        do { // while more work
            if ( debug ) System.out.println("in reach starting closure: " + closure);

//            if (useContext) {
//
//                for (ATNConfig c : closure) {
//                    IntervalSet following = c.state.atn.nextTokens(c.state);
//                    if (following.contains(Token.EPSILON)) {
//
//                    }
//                }
//            }

            List<ATNConfig> closureConfigs = new ArrayList<ATNConfig>(closure);
            List<Integer> contextElements = null;
            boolean stepIntoGlobal;
            do {
                stepIntoGlobal = false;
                boolean hasMoreContext = !useContext || remainingGlobalContext != null;
                int ncl = closureConfigs.size();
                stateReachLoop:
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

                            Set<ATNConfig> closureBusy = new HashSet<ATNConfig>();
                            final boolean collectPredicates = false;
                            stepIntoGlobal |= closure(new ATNConfig(c, target), reach, decState, closureBusy, collectPredicates, hasMoreContext, consumedContextFrames, consumedContextFrames);
                        }
                    }
                }

                if (useContext && stepIntoGlobal) {
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

            // resolve ambig in DFAState for reach
            IntervalSet ambigAlts = getAmbiguousAlts(reach);
            if ( ambigAlts!=null ) {
                if ( debug ) {
                    int i = -1;
                    if ( outerContext!=null && outerContext.s>=0 ) {
                        i = atn.states.get(outerContext.s).ruleIndex;
                    }
                    String rname = getRuleName(i);
                    System.out.println("AMBIG dec "+dfa.decision+" in "+rname+" for alt "+ambigAlts+" upon "+
                                    getInputString(input, startIndex));
                    System.out.println("REACH="+reach);
                }

                // can we resolve with predicates?
                SemanticContext[] altToPred =
                    getPredsForAmbigAlts(decState, ambigAlts, reach);
                if ( altToPred!=null ) {
                    // We need at least n-1 predicates for n ambiguous alts
                    if ( tooFewPredicates(altToPred) ) {
                        reportInsufficientPredicates(startIndex, input.index(),
                                                    ambigAlts, altToPred, reach);
                    }
                    List<DFAState.PredPrediction> predPredictions =
                        getPredicatePredictions(ambigAlts, altToPred);

                    if ( buildDFA ) {
                        DFAState accept = addDFAEdge(dfa, closure, t, contextElements, reach);
                        makeAcceptState(accept, predPredictions);
                    }
                    // rewind input so pred's LT(i) calls make sense
                    input.seek(startIndex);
                    int uniqueAlt = evalSemanticContext(predPredictions);
                    if ( uniqueAlt==ATN.INVALID_ALT_NUMBER ) {
                        // no true pred and/or no uncovered alt
                        // to fall back on. must announce parsing error.
                        throw noViableAlt(input, outerContext, closure, startIndex);
                    }
                    return uniqueAlt;
                }

                dfa.conflictSet = reach.clone(); // at least one DFA state is ambiguous
                if ( !userWantsCtxSensitive ) reportConflict(startIndex, input.index(), ambigAlts, reach);

                if (userWantsCtxSensitive && !useContext && !stepIntoGlobal) {
                    for (ATNConfig config : reach) {
                        if (config.reachesIntoOuterContext > 0) {
                            stepIntoGlobal = true;
                            break;
                        }
                    }
                }

                if ( !userWantsCtxSensitive || useContext || !stepIntoGlobal ) {
                    // resolve ambiguity
                    if ( decState!=null && decState.isGreedy ) {
                        // if greedy, resolve in favor of alt coming first
                        resolveToMinAlt(reach, ambigAlts);
                    }
                    else {
                        // if nongreedy loop, always pick exit branch to match
                        // what follows instead of re-entering loop
                        resolveNongreedyToExitBranch(reach, ambigAlts);
                    }
                }
                else {
                    DFAState from = addDFAState(dfa, closure);
                    if (from != null) {
                        addDFAEdge(from, t, RETRY_WITH_CONTEXT);
                    }

                    input.seek(startIndex);
                    dfa.isContextSensitive = true;
                    return predictATN(dfa, input, outerContext, true);
                }
            }

            // if reach predicts single alt, can stop

            int uniqueAlt = getUniqueAlt(reach);
            if ( uniqueAlt!=ATN.INVALID_ALT_NUMBER ) {
                if ( debug ) System.out.println("PREDICT alt "+uniqueAlt+
                                                " decision "+dfa.decision+
                                                " at index "+input.index());
                if ( !buildDFA ) return uniqueAlt;
                // edge from closure-t->reach now

                DFAState accept = addDFAEdge(dfa, closure, t, contextElements, reach);
                // now check to see if we have a validating predicate.
                // We know that it's validating because there is only
                // one predicted alternative
                IntervalSet uniqueAltSet = new IntervalSet();
                uniqueAltSet.add(uniqueAlt);
                SemanticContext[] altToPred =
                    getPredsForAmbigAlts(decState, uniqueAltSet, reach);
                // altToPred[uniqueAlt] is now our validating predicate (if any)
                if ( altToPred!=null ) {
                    // we have a validating predicate; test it
                    // Update DFA so reach becomes accept state with predicate
                    List<DFAState.PredPrediction> predPredictions =
                        getPredicatePredictions(uniqueAltSet, altToPred);
                    makeAcceptState(accept, predPredictions);
                    // rewind input so pred's LT(i) calls make sense
                    input.seek(startIndex);
                    boolean validated = altToPred[uniqueAlt].eval(parser, outerContext);
                    if ( debug || dfa_debug ) {
                        System.out.println("eval alt "+uniqueAlt+" pred "+
                                            altToPred[uniqueAlt]+"="+ validated);
                    }
                    if ( !validated ) {
                        throw noViableAlt(input, outerContext, closure, startIndex);
                    }
                    return uniqueAlt;
                }
                makeAcceptState(accept, uniqueAlt);
                return uniqueAlt;
            }
            else if (t == CaretToken.CARET_TOKEN_TYPE) {
//                        LOGGER.warning("The current implementation does not support multiple ambiguous alternatives."); //NOI18N
                caretTransitions = transitions;
                caretToken = (CaretToken)input.LT(1);
                break;
            }

            if ( decState!=null && !decState.isGreedy ) {
                // if we reached end of rule via exit branch, we matched
                int exitAlt = 2;
                ATNConfig cstop = configWithAltAtStopState(reach, exitAlt);
                if ( cstop!=null ) {
                    if ( debug ) System.out.println("nongreedy at stop state for exit branch");
                    prevAccept = cstop;
                    prevAcceptIndex = input.index();
                    break;
                }
            }

            if ( reach.isEmpty() ) {
                break;
            }

            // If we matched t anywhere, need to consume and add closer-t->reach DFA edge
            // else error if no previous accept
            input.consume();
            if ( buildDFA ) addDFAEdge(dfa, closure, t, contextElements, reach);
            t = input.LA(1);

            // swap to avoid reallocating space
            ATNConfigSet tmp = reach;
            reach = closure;
            closure = tmp;
            reach.clear(); // TODO: THIS MIGHT BE SLOW! kills each element; realloc might be faster
        } while ( true );

        if ( prevAccept==null ) {
//			System.out.println("no viable token at input "+ getLookaheadName(input) +", index "+input.index());
            throw noViableAlt(input, outerContext, closure, startIndex);
        }

        if ( debug ) System.out.println("PREDICT " + prevAccept + " index " + prevAccept.alt);
        return prevAccept.alt;
    }

    private static final List<Integer> WORDLIKE_TOKEN_TYPES =
        new ArrayList<Integer>() {{
            // keywords
            add(GrammarLexer.OPTIONS);
            add(GrammarLexer.TOKENS);
            add(GrammarLexer.SCOPE);
            add(GrammarLexer.IMPORT);
            add(GrammarLexer.FRAGMENT);
            add(GrammarLexer.LEXER);
            add(GrammarLexer.PARSER);
            add(GrammarLexer.TREE);
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

    public ParserRuleContext<Token> interpret(int ruleIndex) {
        ParserRuleContext<Token> localctx = new GrammarParser.prequelConstructContext(parser.getContext(), 4);
        parser.enterRule(localctx, ruleIndex);
        try {
            ATNState atnstate = this.atn.ruleToStartState[ruleIndex];
            throw new UnsupportedOperationException("Not implemented yet.");
        } finally {
            parser.exitRule(ruleIndex);
        }
        //return localctx;
    }

    private String getRuleName(int index) {
        if ( parser!=null && index>=0 ) return parser.getRuleNames()[index];
        return "<rule "+index+">";
    }
}
