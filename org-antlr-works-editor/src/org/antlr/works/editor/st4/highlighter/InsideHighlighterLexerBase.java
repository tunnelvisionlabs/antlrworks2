// $ANTLR 3.4 InsideHighlighterLexerBase.g 2011-09-24 13:13:46

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


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class InsideHighlighterLexerBase extends Lexer {
    public static final int EOF=-1;
    public static final int ANYCHAR=4;
    public static final int AT=5;
    public static final int BEGIN_BIGSTRING=6;
    public static final int BEGIN_BIGSTRINGLINE=7;
    public static final int COLON=8;
    public static final int COMMA=9;
    public static final int COMMENT=10;
    public static final int CONTINUE_COMMENT=11;
    public static final int DEFINED=12;
    public static final int DELIMITER_SPEC=13;
    public static final int DOT=14;
    public static final int END_BIGSTRING=15;
    public static final int END_BIGSTRINGLINE=16;
    public static final int END_COMMENT=17;
    public static final int EQUALS=18;
    public static final int ID=19;
    public static final int LBRACE=20;
    public static final int LBRACK=21;
    public static final int LEGACY_DELIMITERS=22;
    public static final int LINE_COMMENT=23;
    public static final int LPAREN=24;
    public static final int NEWLINE=25;
    public static final int PARAMETER_DEFINITION=26;
    public static final int QUOTE=27;
    public static final int RBRACE=28;
    public static final int RBRACK=29;
    public static final int RPAREN=30;
    public static final int WS=31;
    public static final int ESCAPE_CHAR=32;
    public static final int ESCAPE_TAG=33;
    public static final int LDELIM=34;
    public static final int PIPE=35;
    public static final int RDELIM=36;
    public static final int TEXT=37;
    public static final int AND=38;
    public static final int ELLIPSIS=39;
    public static final int EXPR_IDENTIFIER=40;
    public static final int NOT=41;
    public static final int OR=42;
    public static final int REGION_REF=43;
    public static final int SEMI=44;
    public static final int STRING=45;

    protected char getCloseDelimiter() {
        throw new UnsupportedOperationException("This operation is not supported by the base class.");
    }


    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public InsideHighlighterLexerBase() {} 
    public InsideHighlighterLexerBase(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InsideHighlighterLexerBase(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "InsideHighlighterLexerBase.g"; }

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:74:5: ( '.' )
            // InsideHighlighterLexerBase.g:74:7: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "ELLIPSIS"
    public final void mELLIPSIS() throws RecognitionException {
        try {
            int _type = ELLIPSIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:75:10: ( '...' )
            // InsideHighlighterLexerBase.g:75:12: '...'
            {
            match("..."); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ELLIPSIS"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:76:7: ( ',' )
            // InsideHighlighterLexerBase.g:76:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:77:7: ( ':' )
            // InsideHighlighterLexerBase.g:77:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:78:6: ( ';' )
            // InsideHighlighterLexerBase.g:78:8: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:79:8: ( '(' )
            // InsideHighlighterLexerBase.g:79:10: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:80:8: ( ')' )
            // InsideHighlighterLexerBase.g:80:10: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "LBRACK"
    public final void mLBRACK() throws RecognitionException {
        try {
            int _type = LBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:81:8: ( '[' )
            // InsideHighlighterLexerBase.g:81:10: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LBRACK"

    // $ANTLR start "RBRACK"
    public final void mRBRACK() throws RecognitionException {
        try {
            int _type = RBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:82:8: ( ']' )
            // InsideHighlighterLexerBase.g:82:10: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RBRACK"

    // $ANTLR start "EQUALS"
    public final void mEQUALS() throws RecognitionException {
        try {
            int _type = EQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:83:8: ( '=' )
            // InsideHighlighterLexerBase.g:83:10: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQUALS"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:84:5: ( '!' )
            // InsideHighlighterLexerBase.g:84:7: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:85:4: ( '||' )
            // InsideHighlighterLexerBase.g:85:6: '||'
            {
            match("||"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:86:5: ( '&&' )
            // InsideHighlighterLexerBase.g:86:7: '&&'
            {
            match("&&"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "LBRACE"
    public final void mLBRACE() throws RecognitionException {
        try {
            int _type = LBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:87:8: ( '{' )
            // InsideHighlighterLexerBase.g:87:10: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LBRACE"

    // $ANTLR start "RBRACE"
    public final void mRBRACE() throws RecognitionException {
        try {
            int _type = RBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:88:8: ( '}' )
            // InsideHighlighterLexerBase.g:88:10: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RBRACE"

    // $ANTLR start "REGION_REF"
    public final void mREGION_REF() throws RecognitionException {
        try {
            int _type = REGION_REF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:91:2: ( '@' EXPR_IDENTIFIER )
            // InsideHighlighterLexerBase.g:91:4: '@' EXPR_IDENTIFIER
            {
            match('@'); 

            mEXPR_IDENTIFIER(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REGION_REF"

    // $ANTLR start "EXPR_IDENTIFIER"
    public final void mEXPR_IDENTIFIER() throws RecognitionException {
        try {
            int _type = EXPR_IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:95:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '/' )* )
            // InsideHighlighterLexerBase.g:95:4: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '/' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // InsideHighlighterLexerBase.g:96:3: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '/' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '/' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InsideHighlighterLexerBase.g:
            	    {
            	    if ( (input.LA(1) >= '/' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPR_IDENTIFIER"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:100:2: ( ( ' ' | '\\t' )+ )
            // InsideHighlighterLexerBase.g:100:4: ( ' ' | '\\t' )+
            {
            // InsideHighlighterLexerBase.g:100:4: ( ' ' | '\\t' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='\t'||LA2_0==' ') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InsideHighlighterLexerBase.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:104:2: ( ( '\\r' | '\\n' )+ )
            // InsideHighlighterLexerBase.g:104:4: ( '\\r' | '\\n' )+
            {
            // InsideHighlighterLexerBase.g:104:4: ( '\\r' | '\\n' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='\n'||LA3_0=='\r') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InsideHighlighterLexerBase.g:
            	    {
            	    if ( input.LA(1)=='\n'||input.LA(1)=='\r' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:108:2: ( '\"' (~ ( '\"' | '\\\\' ) | '\\\\' . )* '\"' )
            // InsideHighlighterLexerBase.g:108:4: '\"' (~ ( '\"' | '\\\\' ) | '\\\\' . )* '\"'
            {
            match('\"'); 

            // InsideHighlighterLexerBase.g:109:3: (~ ( '\"' | '\\\\' ) | '\\\\' . )*
            loop4:
            do {
                int alt4=3;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '\u0000' && LA4_0 <= '!')||(LA4_0 >= '#' && LA4_0 <= '[')||(LA4_0 >= ']' && LA4_0 <= '\uFFFF')) ) {
                    alt4=1;
                }
                else if ( (LA4_0=='\\') ) {
                    alt4=2;
                }


                switch (alt4) {
            	case 1 :
            	    // InsideHighlighterLexerBase.g:109:5: ~ ( '\"' | '\\\\' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;
            	case 2 :
            	    // InsideHighlighterLexerBase.g:110:5: '\\\\' .
            	    {
            	    match('\\'); 

            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "RDELIM"
    public final void mRDELIM() throws RecognitionException {
        try {
            int _type = RDELIM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:116:8: ({...}? => . )
            // InsideHighlighterLexerBase.g:116:10: {...}? => .
            {
            if ( !((input.LA(1) == getCloseDelimiter())) ) {
                throw new FailedPredicateException(input, "RDELIM", "input.LA(1) == getCloseDelimiter()");
            }

            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RDELIM"

    // $ANTLR start "ANYCHAR"
    public final void mANYCHAR() throws RecognitionException {
        try {
            int _type = ANYCHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InsideHighlighterLexerBase.g:119:2: ( . )
            // InsideHighlighterLexerBase.g:119:4: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ANYCHAR"

    public void mTokens() throws RecognitionException {
        // InsideHighlighterLexerBase.g:1:8: ( DOT | ELLIPSIS | COMMA | COLON | SEMI | LPAREN | RPAREN | LBRACK | RBRACK | EQUALS | NOT | OR | AND | LBRACE | RBRACE | REGION_REF | EXPR_IDENTIFIER | WS | NEWLINE | STRING | RDELIM | ANYCHAR )
        int alt5=22;
        int LA5_0 = input.LA(1);

        if ( (LA5_0=='.') ) {
            int LA5_1 = input.LA(2);

            if ( (LA5_1=='.') ) {
                alt5=2;
            }
            else {
                alt5=1;
            }
        }
        else if ( (LA5_0==',') ) {
            alt5=3;
        }
        else if ( (LA5_0==':') ) {
            alt5=4;
        }
        else if ( (LA5_0==';') ) {
            alt5=5;
        }
        else if ( (LA5_0=='(') ) {
            alt5=6;
        }
        else if ( (LA5_0==')') ) {
            alt5=7;
        }
        else if ( (LA5_0=='[') ) {
            alt5=8;
        }
        else if ( (LA5_0==']') ) {
            alt5=9;
        }
        else if ( (LA5_0=='=') ) {
            alt5=10;
        }
        else if ( (LA5_0=='!') ) {
            alt5=11;
        }
        else if ( (LA5_0=='|') ) {
            int LA5_11 = input.LA(2);

            if ( (LA5_11=='|') ) {
                alt5=12;
            }
            else if ( ((input.LA(1) == getCloseDelimiter())) ) {
                alt5=21;
            }
            else if ( (true) ) {
                alt5=22;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 11, input);

                throw nvae;

            }
        }
        else if ( (LA5_0=='&') ) {
            int LA5_12 = input.LA(2);

            if ( (LA5_12=='&') ) {
                alt5=13;
            }
            else if ( ((input.LA(1) == getCloseDelimiter())) ) {
                alt5=21;
            }
            else if ( (true) ) {
                alt5=22;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 12, input);

                throw nvae;

            }
        }
        else if ( (LA5_0=='{') ) {
            alt5=14;
        }
        else if ( (LA5_0=='}') ) {
            alt5=15;
        }
        else if ( (LA5_0=='@') ) {
            int LA5_15 = input.LA(2);

            if ( ((LA5_15 >= 'A' && LA5_15 <= 'Z')||LA5_15=='_'||(LA5_15 >= 'a' && LA5_15 <= 'z')) ) {
                alt5=16;
            }
            else if ( ((input.LA(1) == getCloseDelimiter())) ) {
                alt5=21;
            }
            else if ( (true) ) {
                alt5=22;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 15, input);

                throw nvae;

            }
        }
        else if ( ((LA5_0 >= 'A' && LA5_0 <= 'Z')||LA5_0=='_'||(LA5_0 >= 'a' && LA5_0 <= 'z')) ) {
            alt5=17;
        }
        else if ( (LA5_0=='\t'||LA5_0==' ') ) {
            alt5=18;
        }
        else if ( (LA5_0=='\n'||LA5_0=='\r') ) {
            alt5=19;
        }
        else if ( (LA5_0=='\"') ) {
            int LA5_19 = input.LA(2);

            if ( ((LA5_19 >= '\u0000' && LA5_19 <= '\uFFFF')) ) {
                alt5=20;
            }
            else if ( ((input.LA(1) == getCloseDelimiter())) ) {
                alt5=21;
            }
            else if ( (true) ) {
                alt5=22;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 19, input);

                throw nvae;

            }
        }
        else if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\b')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= '\u001F')||(LA5_0 >= '#' && LA5_0 <= '%')||LA5_0=='\''||(LA5_0 >= '*' && LA5_0 <= '+')||LA5_0=='-'||(LA5_0 >= '/' && LA5_0 <= '9')||LA5_0=='<'||(LA5_0 >= '>' && LA5_0 <= '?')||LA5_0=='\\'||LA5_0=='^'||LA5_0=='`'||(LA5_0 >= '~' && LA5_0 <= '\uFFFF')) ) {
            int LA5_20 = input.LA(2);

            if ( ((input.LA(1) == getCloseDelimiter())) ) {
                alt5=21;
            }
            else if ( (true) ) {
                alt5=22;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 20, input);

                throw nvae;

            }
        }
        else {
            NoViableAltException nvae =
                new NoViableAltException("", 5, 0, input);

            throw nvae;

        }
        switch (alt5) {
            case 1 :
                // InsideHighlighterLexerBase.g:1:10: DOT
                {
                mDOT(); 


                }
                break;
            case 2 :
                // InsideHighlighterLexerBase.g:1:14: ELLIPSIS
                {
                mELLIPSIS(); 


                }
                break;
            case 3 :
                // InsideHighlighterLexerBase.g:1:23: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 4 :
                // InsideHighlighterLexerBase.g:1:29: COLON
                {
                mCOLON(); 


                }
                break;
            case 5 :
                // InsideHighlighterLexerBase.g:1:35: SEMI
                {
                mSEMI(); 


                }
                break;
            case 6 :
                // InsideHighlighterLexerBase.g:1:40: LPAREN
                {
                mLPAREN(); 


                }
                break;
            case 7 :
                // InsideHighlighterLexerBase.g:1:47: RPAREN
                {
                mRPAREN(); 


                }
                break;
            case 8 :
                // InsideHighlighterLexerBase.g:1:54: LBRACK
                {
                mLBRACK(); 


                }
                break;
            case 9 :
                // InsideHighlighterLexerBase.g:1:61: RBRACK
                {
                mRBRACK(); 


                }
                break;
            case 10 :
                // InsideHighlighterLexerBase.g:1:68: EQUALS
                {
                mEQUALS(); 


                }
                break;
            case 11 :
                // InsideHighlighterLexerBase.g:1:75: NOT
                {
                mNOT(); 


                }
                break;
            case 12 :
                // InsideHighlighterLexerBase.g:1:79: OR
                {
                mOR(); 


                }
                break;
            case 13 :
                // InsideHighlighterLexerBase.g:1:82: AND
                {
                mAND(); 


                }
                break;
            case 14 :
                // InsideHighlighterLexerBase.g:1:86: LBRACE
                {
                mLBRACE(); 


                }
                break;
            case 15 :
                // InsideHighlighterLexerBase.g:1:93: RBRACE
                {
                mRBRACE(); 


                }
                break;
            case 16 :
                // InsideHighlighterLexerBase.g:1:100: REGION_REF
                {
                mREGION_REF(); 


                }
                break;
            case 17 :
                // InsideHighlighterLexerBase.g:1:111: EXPR_IDENTIFIER
                {
                mEXPR_IDENTIFIER(); 


                }
                break;
            case 18 :
                // InsideHighlighterLexerBase.g:1:127: WS
                {
                mWS(); 


                }
                break;
            case 19 :
                // InsideHighlighterLexerBase.g:1:130: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 20 :
                // InsideHighlighterLexerBase.g:1:138: STRING
                {
                mSTRING(); 


                }
                break;
            case 21 :
                // InsideHighlighterLexerBase.g:1:145: RDELIM
                {
                mRDELIM(); 


                }
                break;
            case 22 :
                // InsideHighlighterLexerBase.g:1:152: ANYCHAR
                {
                mANYCHAR(); 


                }
                break;

        }

    }


 

}