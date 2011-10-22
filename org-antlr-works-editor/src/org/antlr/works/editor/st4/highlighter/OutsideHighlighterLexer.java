/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
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
package org.antlr.works.editor.st4.highlighter;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;

public class OutsideHighlighterLexer extends OutsideHighlighterLexerBase {
    private final TemplateHighlighterLexer aggregateLexer;

    public OutsideHighlighterLexer(CharStream input, TemplateHighlighterLexer lexer) {
        super(input);
        aggregateLexer = lexer;
    }

    public TemplateHighlighterLexer getAggregateLexer() {
        return aggregateLexer;
    }

    public TemplateLexerMode getMode() {
        return getAggregateLexer().getMode();
    }

    @Override
    public OutermostTemplate getOutermost() {
        return getAggregateLexer().getOutermost();
    }

    @Override
    public int getAnonymousTemplateLevel() {
        return getAggregateLexer().getAnonymousTemplateLevel();
    }

    @Override
    public char getOpenDelimiter() {
        return getAggregateLexer().getOpenDelimiter();
    }

    @Override
    public char getCloseDelimiter() {
        return getAggregateLexer().getCloseDelimiter();
    }

    public boolean getInComment() {
        return getAggregateLexer().getInComment();
    }

    public void setInComment(boolean value) {
        getAggregateLexer().setInComment(value);
    }

    @Override
    public Token nextToken() {
        Token token = super.nextToken();
        switch (token.getType()) {
        case CONTINUE_COMMENT:
            setInComment(true);
            token.setType(COMMENT);
            break;

        case END_COMMENT:
            setInComment(false);
            token.setType(COMMENT);
            break;

        default:
            break;
        }

        return token;
    }

    @Override
    public void mTokens() throws RecognitionException {
        if (input.LA(1) == '\r' || input.LA(1) == '\n') {
            super.mTokens();
            return;
        }

        if (getInComment()) {
            mCONTINUE_COMMENT();
        } else {
            super.mTokens();
        }
    }
}
