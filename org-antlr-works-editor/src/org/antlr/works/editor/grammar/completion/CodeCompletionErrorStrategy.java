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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.antlr.v4.runtime.BaseRecognizer;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.PredictionContext;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.misc.IntervalSet;

/**
 *
 * @author Sam Harwell
 */
public class CodeCompletionErrorStrategy extends DefaultErrorStrategy<Token> {

    public CodeCompletionErrorStrategy() {
    }

    @Override
    public void reportError(BaseRecognizer<Token> recognizer, RecognitionException e) throws RecognitionException {
        if (e != null && e.getOffendingToken() != null && e.getOffendingToken().getType() == CaretToken.CARET_TOKEN_TYPE) {
            return;
        }

        super.reportError(recognizer, e);
    }

    @Override
    public void sync(BaseRecognizer<Token> recognizer) {
        if (recognizer.getInputStream().LA(1) == CaretToken.CARET_TOKEN_TYPE) {
            return;
        }

        super.sync(recognizer);
    }

    /** Consume tokens until one matches the given token set */
    @Override
    public void consumeUntil(BaseRecognizer<Token> recognizer, IntervalSet set) {
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
    public void recover(BaseRecognizer<Token> recognizer, RecognitionException e) {
        if (recognizer instanceof CodeCompletionGrammarParser
            && recognizer.getInputStream() instanceof TokenStream
            && ((CodeCompletionGrammarParser)recognizer).getInterpreter().getCaretTransitions() != null) {

//                    int stateNumber = recognizer.getContext().s;
//                    ATNState state = recognizer.getATN().states.get(stateNumber);
//                    if (state instanceof DecisionState && recognizer.getInputStream() instanceof ObjectStream) {
//                        int decision = ((DecisionState)state).decision;
//                        ParserATNSimulator simulator = recognizer.getInterpreter();
//                        int prediction = simulator.adaptivePredict((ObjectStream)recognizer.getInputStream(), decision, recognizer.getContext());
//                    }

            CodeCompletionGrammarParser parser = (CodeCompletionGrammarParser)recognizer;
            CaretToken token = parser.getInterpreter().getCaretToken();
            CompletionParserATNSimulator interpreter = parser.getInterpreter();

            throw new CaretReachedException(parser.getContext(), token, interpreter.getCaretTransitions(), e);
        }

        super.recover(recognizer, e);
    }

    @Override
    public Token recoverInline(BaseRecognizer<Token> recognizer) throws RecognitionException {
        if (recognizer instanceof CodeCompletionGrammarParser
            && recognizer.getInputStream() instanceof TokenStream
            && ((TokenStream)recognizer.getInputStream()).LT(1) instanceof CaretToken) {

            CodeCompletionGrammarParser parser = (CodeCompletionGrammarParser)recognizer;
            CaretToken token = (CaretToken)((TokenStream)recognizer.getInputStream()).LT(1);

            CompletionParserATNSimulator interp = parser.getInterpreter();
            int stateNumber = recognizer.getContext().s;
            ATNState state = interp.atn.states.get(stateNumber);

            ATNConfigSet closure = new ATNConfigSet(true, true);
            for (int i = 0; i < state.getNumberOfTransitions(); i++) {
                Transition transition = state.transition(i);
                if (transition.isEpsilon()) {
                    ATNState target = transition.target;
                    ATNConfig config = new ATNConfig(target, i + 1, PredictionContext.fromRuleContext(recognizer.getContext()));
                    Set<ATNConfig> closureBusy = new HashSet<ATNConfig>();
                    interp.closure(config, closure, null, closureBusy, false, true, 0, 0);
                }
            }

            if (!state.onlyHasEpsilonTransitions()) {
                closure.add(new ATNConfig(state, 1, PredictionContext.fromRuleContext(recognizer.getContext())));
            }

            ATNConfigSet reach = new ATNConfigSet(true, true);
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

                        Set<ATNConfig> closureBusy = new HashSet<ATNConfig>();
                        interp.closure(new ATNConfig(c, target), reach, null, closureBusy, false, true, 0, 0);
                    }
                }
            }

            throw new CaretReachedException(parser.getContext(), token, transitions, null);
        }

        return super.recoverInline(recognizer);
    }
}
