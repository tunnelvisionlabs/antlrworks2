/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import org.antlr.v4.runtime.ANTLRInputStream;

/**
 *
 * @author Sam Harwell
 */
public class TracingCharStream extends ANTLRInputStream {
    private final LexerTraceListener _listener;

    private int inConsume;

    public TracingCharStream(LexerTraceListener listener, String input) {
        super(input);
        _listener = listener;
    }

    @Override
    public void seek(int index) {
        _listener.seek(index);
        super.seek(index);
    }

    @Override
    public void consume() {
        inConsume++;
        try {
            int symbol = LA(1);
            try {
                super.consume();
            } finally {
                _listener.consume(symbol, index());
            }
        } finally {
            inConsume--;
        }
    }

    @Override
    public int LA(int i) {
        if (inConsume == 0) {
            _listener.lookahead(i);
        }

        return super.LA(i);
    }

}
