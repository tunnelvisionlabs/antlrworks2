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
public interface LexerTraceListener {

    void beginMatch(int mode, int index);

    void endMatch();

    void transition(boolean computed);

    void acceptState(int tokenType);

    void predict(int tokenType);

    void seek(int index);

    void consume(int symbol, int nextIndex);

    void lookahead(int k);

    void pushMode(int mode);

    void popMode();

    void emit(int startIndex, int stopIndex, int type, int channel);

}
