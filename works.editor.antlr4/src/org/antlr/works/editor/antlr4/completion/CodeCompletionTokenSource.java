/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
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
package org.antlr.works.editor.antlr4.completion;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.WritableToken;

/**
 *
 * @author Sam Harwell
 */
public class CodeCompletionTokenSource implements TokenSource {
    private final int caretOffset;
    private final TokenSource source;

    private TokenFactory<?> tokenFactory = CommonTokenFactory.DEFAULT;

    private Token caretToken;

    public CodeCompletionTokenSource(int caretOffset, TokenSource source) {
        this.caretOffset = caretOffset;
        this.source = source;
    }

    @Override
    public Token nextToken() {
        if (caretToken == null) {
            Token token = source.nextToken();
            if (token.getStopIndex() + 1 < caretOffset) {
                // the caret is after this token, nothing special to do
            } else if (token.getStartIndex() > caretOffset) {
                // the token is after the caret, no need to include it
                token = new CaretToken(source, Token.DEFAULT_CHANNEL, caretOffset, caretOffset);
                caretToken = token;
            } else {
                if (token.getStopIndex() + 1 == caretOffset) {
                    // the caret is at the end of this token, and this
                    // isn't a word token
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

        return emitEOF();
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
    public void setTokenFactory(TokenFactory<?> tokenFactory) {
        source.setTokenFactory(tokenFactory);
        this.tokenFactory = tokenFactory != null ? tokenFactory : CommonTokenFactory.DEFAULT;
    }

    protected boolean isWordToken(Token token) {
        return AbstractCompletionQuery.WORD_PATTERN.matcher(token.getText()).matches();
    }

    protected Token emitEOF() {
        return tokenFactory.create(source,
                                   Token.EOF,
                                   (String)null,
                                   Token.DEFAULT_CHANNEL,
                                   getInputStream().index(),
                                   getInputStream().index() - 1,
                                   getLine(),
                                   getCharPositionInLine());
    }
}
