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
package org.antlr.works.editor.grammar.highlighter;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;

public class ActionHighlighterLexer extends ActionHighlighterLexerBase {
    private final ANTLRHighlighterLexer lexer;
    
    public ActionHighlighterLexer(CharStream input, ANTLRHighlighterLexer lexer) {
        super(input);
        this.lexer = lexer;
    }
    
    @Override
    public Token nextToken() {
        Token token = super.nextToken();
        
        switch (token.getType()) {
            case CONTINUE_CHAR_LITERAL:
                lexer.setInCharLiteral(true);
                state.token.setType(ACTION_CHAR_LITERAL);
                break;
                
            case END_CHAR_LITERAL:
                lexer.setInCharLiteral(false);
                state.token.setType(ACTION_CHAR_LITERAL);
                break;
                
            case CONTINUE_STRING_LITERAL:
                lexer.setInStringLiteral(true);
                state.token.setType(ACTION_STRING_LITERAL);
                break;
                
            case END_STRING_LITERAL:
                lexer.setInStringLiteral(false);
                state.token.setType(ACTION_STRING_LITERAL);
                break;
                
            case CONTINUE_COMMENT:
                lexer.setInComment(true);
                state.token.setType(ACTION_ML_COMMENT);
                break;
                
            case END_COMMENT:
                lexer.setInComment(false);
                state.token.setType(ACTION_ML_COMMENT);
                break;
                
            default:
                break;
        }
        
        return token;
    }
    
    @Override
    public void mTokens() throws RecognitionException {
        if (lexer.getInCharLiteral()) {
            mCONTINUE_CHAR_LITERAL();
        } else if (lexer.getInStringLiteral()) {
            mCONTINUE_STRING_LITERAL();
        } else if (lexer.getInComment()) {
            mCONTINUE_COMMENT();
        } else {
            super.mTokens();
        }        
    }

    @Override
    public Token emit() {
        CommonToken token = (CommonToken)super.emit();
        if (token.getType() != EOF)
            token.setText(input.substring(token.getStartIndex(), token.getStopIndex()));
        return token;
    }
    
    @Override
    protected ANTLRHighlighterLexer getLexer() {
        return lexer;
    }
}
