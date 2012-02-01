/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
        if (input.LA(1) == '\r' || input.LA(1) == '\n') {
            super.mTokens();
            return;
        }

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
