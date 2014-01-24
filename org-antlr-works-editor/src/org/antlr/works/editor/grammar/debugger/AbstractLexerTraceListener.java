/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractLexerTraceListener implements LexerTraceListener {

    @Override
    public void beginMatch(int mode, int index) {
    }

    @Override
    public void endMatch() {
    }

    @Override
    public void transition(boolean computed) {
    }

    @Override
    public void acceptState(int tokenType) {
    }

    @Override
    public void predict(int tokenType) {
    }

    @Override
    public void seek(int index) {
    }

    @Override
    public void consume(int symbol, int nextIndex) {
    }

    @Override
    public void lookahead(int k) {
    }

    @Override
    public void pushMode(int mode) {
    }

    @Override
    public void popMode() {
    }

    @Override
    public void emit(int startIndex, int stopIndex, int type, int channel) {
    }

}
