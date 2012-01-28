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

    /** Consume tokens until one matches the given token set */
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
            int stateNumber = recognizer.getContext().s;
            ATNState state = interp.atn.states.get(stateNumber);

            PredictionContext context = PredictionContext.fromRuleContext(recognizer.getContext());
            ATNConfigSet intermediate = new ATNConfigSet(true);
            ATNConfigSet closure = new ATNConfigSet(true);
            for (int i = 0; i < state.getNumberOfTransitions(); i++) {
                Transition transition = state.transition(i);
                if (transition.isEpsilon()) {
                    ATNState target = transition.target;
                    ATNConfig config = new ATNConfig(target, i + 1, context);
                    intermediate.add(config);
                }
            }

            interp.closure(intermediate, closure, PredictionContextCache.UNCACHED_LOCAL, false, true, false, true);

            if (!state.onlyHasEpsilonTransitions()) {
                for (int i = 0; i < state.getNumberOfTransitions(); i++) {
                    closure.add(new ATNConfig(state, 1, PredictionContext.fromRuleContext(recognizer.getContext())));
                }
            }

            LinkedHashMap<ATNConfig, List<Transition>> transitions = null;
            int ncl = closure.size();
            for (int ci = 0; ci < ncl; ci++) { // TODO: foreach
                ATNConfig c = closure.get(ci);

                List<Transition> configTransitions = null;

                int n = c.state.getNumberOfTransitions();
                for (int ti = 0; ti < n; ti++) {               // for each transition
                    Transition trans = c.state.transition(ti);
                    ATNState target = interp.getReachableTarget(trans, CaretToken.CARET_TOKEN_TYPE);
                    if (target != null) {
                        if (transitions == null) {
                            transitions = new LinkedHashMap<ATNConfig, List<Transition>>();
                        }

                        if (configTransitions == null) {
                            configTransitions = new ArrayList<Transition>();
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
