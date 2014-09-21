/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.dfa.DFAState;

/**
 *
 * @author Sam Harwell
 */
public class TracingLexerATNSimulator extends LexerATNSimulator {
    private final LexerTraceListener _listener;

    public TracingLexerATNSimulator(LexerTraceListener listener, Lexer recog, ATN atn) {
        super(recog, atn);
        _listener = listener;
    }

    @Override
    public int match(CharStream input, int mode) {
        _listener.beginMatch(mode, input.index());
        try {
            int prediction = super.match(input, mode);
            _listener.predict(prediction);
            return prediction;
        } finally {
            _listener.endMatch();
        }
    }

    @Override
    protected DFAState getExistingTargetState(DFAState s, int t) {
        DFAState target = super.getExistingTargetState(s, t);
        if (target != null) {
            _listener.transition(false);
        }

        return target;
    }

    @Override
    protected DFAState computeTargetState(CharStream input, DFAState s, int t) {
        _listener.transition(true);
        return super.computeTargetState(input, s, t);
    }

    @Override
    protected void captureSimState(SimState settings, CharStream input, DFAState dfaState) {
        _listener.acceptState(dfaState.getPrediction());
        super.captureSimState(settings, input, dfaState);
    }
}
