/*
 *  Copyright (c) 2014 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.util.BitSet;
import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.atn.SimulatorState;
import org.antlr.v4.runtime.dfa.DFA;

/**
 *
 * @author Sam Harwell
 */
public class StatisticsParserErrorListener extends DiagnosticErrorListener {

    private BitSet _sllConflict;
    private ATNConfigSet _sllConfigs;

    @Override
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
        BitSet sllPredictions = getConflictingAlts(_sllConflict, _sllConfigs);
        int sllPrediction = sllPredictions.nextSetBit(0);
        BitSet llPredictions = getConflictingAlts(ambigAlts, configs);
        int llPrediction = llPredictions.cardinality() == 0 ? ATN.INVALID_ALT_NUMBER : llPredictions.nextSetBit(0);
        if (recognizer.getInterpreter() instanceof StatisticsParserATNSimulator) {
            if (sllPrediction != llPrediction) {
                ((StatisticsParserATNSimulator)recognizer.getInterpreter()).nonSll[dfa.decision]++;
            }

            ((StatisticsParserATNSimulator)recognizer.getInterpreter()).ambiguousResult[dfa.decision]++;
        }
    }

    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, SimulatorState conflictState) {
        _sllConflict = conflictingAlts;
        _sllConfigs = conflictState.s0.configs;
    }

    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, SimulatorState acceptState) {
        BitSet sllPredictions = getConflictingAlts(_sllConflict, _sllConfigs);
        int sllPrediction = sllPredictions.nextSetBit(0);
        if (sllPrediction != prediction && recognizer.getInterpreter() instanceof StatisticsParserATNSimulator) {
            ((StatisticsParserATNSimulator)recognizer.getInterpreter()).nonSll[dfa.decision]++;
        }
    }

}
