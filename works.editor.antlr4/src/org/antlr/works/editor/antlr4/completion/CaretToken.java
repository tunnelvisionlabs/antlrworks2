/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.completion;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Tuple2;

/**
 *
 * @author Sam Harwell
 */
public class CaretToken extends CommonToken {
    public static final int CARET_TOKEN_TYPE = -2;
    private final Token originalToken;

    public CaretToken(Tuple2<? extends TokenSource, CharStream> source, int channel, int start, int stop) {
        super(source, CARET_TOKEN_TYPE, channel, start, stop);
        this.originalToken = null;
    }

    public CaretToken(Token oldToken) {
        super(oldToken);
        this.channel = Token.DEFAULT_CHANNEL;
        this.originalToken = oldToken;
        this.setType(CARET_TOKEN_TYPE);
    }

    public Token getOriginalToken() {
        return originalToken;
    }

    public int getOriginalType() {
        return originalToken.getType();
    }
}
