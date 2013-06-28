/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.completion;

import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.Transition;

/**
 *
 * @author Sam Harwell
 */
public class CaretReachedException extends RuntimeException {
    private final RuleContext finalContext;
    private final CaretToken caretToken;
    private final Map<ATNConfig, List<Transition>> transitions;

    public CaretReachedException(RuleContext finalContext, CaretToken caretToken, Map<ATNConfig, List<Transition>> transitions, RecognitionException cause) {
        super(cause);
        this.finalContext = finalContext;
        this.caretToken = caretToken;
        this.transitions = transitions;
    }

    public RuleContext getFinalContext() {
        return finalContext;
    }

    public CaretToken getCaretToken() {
        return caretToken;
    }

    public Map<ATNConfig, List<Transition>> getTransitions() {
        return transitions;
    }

    @Override
    public RecognitionException getCause() {
        return (RecognitionException)super.getCause();
    }
}
