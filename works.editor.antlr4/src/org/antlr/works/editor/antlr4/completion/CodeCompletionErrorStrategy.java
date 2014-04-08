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
import java.util.LinkedHashMap;
import java.util.List;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.PredictionContext;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.misc.IntervalSet;

/**
 *
 * @author Sam Harwell
 */
public class CodeCompletionErrorStrategy extends DefaultErrorStrategy {

    @Override
    public void reportError(Parser recognizer, RecognitionException e) throws RecognitionException {
        if (e != null && e.getOffendingToken() != null && e.getOffendingToken().getType() == CaretToken.CARET_TOKEN_TYPE) {
            return;
        }

        super.reportError(recognizer, e);
    }

    @Override
    public void sync(Parser recognizer) {
        if (recognizer.getInputStream().LA(1) == CaretToken.CARET_TOKEN_TYPE) {
            return;
        }

        // TODO: incorporate error recovery as a fallback option if no trees match correctly
        if (true) {
            return;
        }

        super.sync(recognizer);
    }

    /** Consume tokens until one matches the given token set
     * @param recognizer
     * @param set
     */
    @Override
    public void consumeUntil(Parser recognizer, IntervalSet set) {
        //System.out.println("consumeUntil("+set.toString(getTokenNames())+")");
        int ttype = recognizer.getInputStream().LA(1);
        while (ttype != Token.EOF && ttype != CaretToken.CARET_TOKEN_TYPE && !set.contains(ttype) ) {
            //System.out.println("consume during recover LA(1)="+getTokenNames()[input.LA(1)]);
            recognizer.getInputStream().consume();
            //recognizer.consume();
            ttype = recognizer.getInputStream().LA(1);
        }
    }

    @Override
    public void recover(Parser recognizer, RecognitionException e) {
        if (recognizer instanceof CodeCompletionParser
            && ((CodeCompletionParser)recognizer).getInterpreter().getCaretTransitions() != null) {

//                    int stateNumber = recognizer.getContext().s;
//                    ATNState state = recognizer.getATN().states.get(stateNumber);
//                    if (state instanceof DecisionState && recognizer.getInputStream() instanceof ObjectStream) {
//                        int decision = ((DecisionState)state).decision;
//                        ParserATNSimulator simulator = recognizer.getInterpreter();
//                        int prediction = simulator.adaptivePredict((ObjectStream)recognizer.getInputStream(), decision, recognizer.getContext());
//                    }

            CodeCompletionParser parser = (CodeCompletionParser)recognizer;
            CaretToken token = parser.getInterpreter().getCaretToken();
            AbstractCompletionParserATNSimulator interpreter = parser.getInterpreter();

            throw new CaretReachedException(parser.getContext(), token, interpreter.getCaretTransitions(), e);
        }

        // TODO: incorporate error recovery as a fallback option if no trees match correctly
        throw e;
        //super.recover(recognizer, e);
    }

    @Override
    public Token recoverInline(Parser recognizer) throws RecognitionException {
        if (recognizer instanceof CodeCompletionParser
            && recognizer.getInputStream().LT(1) instanceof CaretToken) {

            CodeCompletionParser parser = (CodeCompletionParser)recognizer;
            CaretToken token = (CaretToken)recognizer.getInputStream().LT(1);

            AbstractCompletionParserATNSimulator interp = parser.getInterpreter();
            int stateNumber = recognizer.getState();
            ATNState state = interp.atn.states.get(stateNumber);

            PredictionContext context = PredictionContext.fromRuleContext(interp.atn, recognizer.getContext(), false);
            ATNConfigSet intermediate = new ATNConfigSet();
            ATNConfigSet closure = new ATNConfigSet();
            for (int i = 0; i < state.getNumberOfTransitions(); i++) {
                Transition transition = state.transition(i);
                if (transition.isEpsilon()) {
                    ATNState target = transition.target;
                    ATNConfig config = ATNConfig.create(target, i + 1, context);
                    intermediate.add(config);
                }
            }

            final boolean collectPredicates = false;
            final boolean hasMoreContext = true;
            interp.closure(intermediate, closure, collectPredicates, hasMoreContext, PredictionContextCache.UNCACHED, false);

            if (!state.onlyHasEpsilonTransitions()) {
                for (int i = 0; i < state.getNumberOfTransitions(); i++) {
                    closure.add(ATNConfig.create(state, i + 1, PredictionContext.fromRuleContext(interp.atn, recognizer.getContext())));
                }
            }

            LinkedHashMap<ATNConfig, List<Transition>> transitions = null;
            int ncl = closure.size();
            for (int ci = 0; ci < ncl; ci++) { // TODO: foreach
                ATNConfig c = closure.get(ci);

                List<Transition> configTransitions = null;

                int n = c.getState().getNumberOfTransitions();
                for (int ti = 0; ti < n; ti++) {               // for each transition
                    Transition trans = c.getState().transition(ti);
                    ATNState target = interp.getReachableTarget(c, trans, CaretToken.CARET_TOKEN_TYPE);
                    if (target != null) {
                        if (transitions == null) {
                            transitions = new LinkedHashMap<>();
                        }

                        if (configTransitions == null) {
                            configTransitions = new ArrayList<>();
                            transitions.put(c, configTransitions);
                        }

                        configTransitions.add(trans);
                    }
                }
            }

            /*
             * This should be null if the intended token is not "wordlike", and
             * should be a single transition from the current state.
             */
            if (transitions != null) {
                assert transitions.size() == 1;
                assert transitions.values().iterator().next().size() == 1;
                assert state.getNumberOfTransitions() == 1;
                assert transitions.values().iterator().next().get(0) == state.transition(0);
            }

            throw new CaretReachedException(parser.getContext(), token, transitions, null);
        }

        return super.recoverInline(recognizer);
    }

    @Override
    public boolean singleTokenInsertion(Parser recognizer) {
        if (recognizer.getInputStream().LA(1) == CaretToken.CARET_TOKEN_TYPE) {
            return false;
        }

        return false;
//        return super.singleTokenInsertion(recognizer);
    }

    @Override
    public Token singleTokenDeletion(Parser recognizer) {
        if (recognizer.getInputStream().LA(1) == CaretToken.CARET_TOKEN_TYPE) {
            return null;
        }

        return null;
//        return super.singleTokenDeletion(recognizer);
    }

}
