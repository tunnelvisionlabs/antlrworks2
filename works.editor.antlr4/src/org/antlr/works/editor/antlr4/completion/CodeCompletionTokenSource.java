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
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Tuple;
import org.antlr.v4.runtime.misc.Tuple2;

/**
 *
 * @author Sam Harwell
 */
public class CodeCompletionTokenSource implements TokenSource {
    private final int caretOffset;
    private final TokenSource source;
    private final Tuple2<? extends TokenSource, CharStream> tokenFactorySourcePair;

    private TokenFactory tokenFactory = CommonTokenFactory.DEFAULT;

    private Token caretToken;

    public CodeCompletionTokenSource(int caretOffset, TokenSource source) {
        this.caretOffset = caretOffset;
        this.source = source;
        this.tokenFactorySourcePair = Tuple.create(source, source.getInputStream());
    }

    @Override
    public Token nextToken() {
        if (caretToken == null) {
            Token token = source.nextToken();
            if (token.getStopIndex() + 1 < caretOffset) {
                // the caret is after this token, nothing special to do
            } else if (token.getStartIndex() > caretOffset) {
                // the token is after the caret, no need to include it
                token = new CaretToken(tokenFactorySourcePair, Token.DEFAULT_CHANNEL, caretOffset, caretOffset);
                caretToken = token;
            } else {
                if (token.getStopIndex() + 1 == caretOffset
                    && token.getStopIndex() >= token.getStartIndex()) {
                    // the caret is at the end of this token, and this
                    // isn't a word token or a zero-length token
                    if (!isWordToken(token)) {
                        return token;
                    }
                }

                // the caret is in the middle of or at the end of this token
                token = new CaretToken(token);
                caretToken = token;
            }

            return token;
        }

        throw new UnsupportedOperationException("Attempted to look past the caret.");
    }

    @Override
    public int getLine() {
        return source.getLine();
    }

    @Override
    public int getCharPositionInLine() {
        return source.getCharPositionInLine();
    }

    @Override
    public CharStream getInputStream() {
        return source.getInputStream();
    }

    @Override
    public String getSourceName() {
        return source.getSourceName();
    }

    @Override
    public TokenFactory getTokenFactory() {
        return tokenFactory;
    }

    @Override
    public void setTokenFactory(TokenFactory tokenFactory) {
        source.setTokenFactory(tokenFactory);
        this.tokenFactory = tokenFactory != null ? tokenFactory : CommonTokenFactory.DEFAULT;
    }

    protected boolean isWordToken(Token token) {
        return AbstractCompletionQuery.WORD_PATTERN.matcher(token.getText()).matches();
    }

    protected Token emitEOF() {
        return tokenFactory.create(tokenFactorySourcePair,
                                   Token.EOF,
                                   (String)null,
                                   Token.DEFAULT_CHANNEL,
                                   getInputStream().index(),
                                   getInputStream().index() - 1,
                                   getLine(),
                                   getCharPositionInLine());
    }
}
