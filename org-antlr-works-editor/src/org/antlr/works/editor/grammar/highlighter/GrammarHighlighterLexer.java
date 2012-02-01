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

public class GrammarHighlighterLexer extends GrammarHighlighterLexerBase {
    private final ANTLRHighlighterLexer lexer;
    
    public GrammarHighlighterLexer(CharStream input, ANTLRHighlighterLexer lexer) {
        super(input);
        this.lexer = lexer;
    }
    
    @Override
    public Token nextToken() {
        Token token = super.nextToken();
        
        switch (token.getType()) {
            case CONTINUE_DOUBLE_ANGLE_STRING_LITERAL:
                lexer.setInDoubleAngleStringLiteral(true);
                token.setType(DOUBLE_ANGLE_STRING_LITERAL);
                break;

            case END_DOUBLE_ANGLE_STRING_LITERAL:
                lexer.setInDoubleAngleStringLiteral(false);
                token.setType(DOUBLE_ANGLE_STRING_LITERAL);
                break;

            case CONTINUE_COMMENT:
                lexer.setInComment(true);
                token.setType(ML_COMMENT);
                break;

            case END_COMMENT:
                lexer.setInComment(false);
                token.setType(ML_COMMENT);
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

        if (lexer.getInDoubleAngleStringLiteral()) {
            mCONTINUE_DOUBLE_ANGLE_STRING_LITERAL();
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
}
