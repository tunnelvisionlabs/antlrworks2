/*
 *  Copyright (c) 2014 Sam Harwell, Tunnel Vision Laboratories LLC
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
public class StatisticsLexerATNSimulator extends LexerATNSimulator {

    public long totalTransitions;
    public long computedTransitions;

    public StatisticsLexerATNSimulator(ATN atn) {
        super(atn);
    }

    public StatisticsLexerATNSimulator(Lexer recog, ATN atn) {
        super(recog, atn);
    }

    @Override
    protected DFAState getExistingTargetState(DFAState s, int t) {
        totalTransitions++;
        return super.getExistingTargetState(s, t);
    }

    @Override
    protected DFAState computeTargetState(CharStream input, DFAState s, int t) {
        computedTransitions++;
        return super.computeTargetState(input, s, t);
    }

}
