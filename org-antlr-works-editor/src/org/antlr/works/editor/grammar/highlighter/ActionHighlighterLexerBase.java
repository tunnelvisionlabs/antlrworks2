// $ANTLR 3.4 ActionHighlighterLexerBase.g 2012-01-31 21:39:22
/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.highlighter;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class ActionHighlighterLexerBase extends Lexer {
    public static final int EOF=-1;
    public static final int ACTION=4;
    public static final int AMPERSAND=5;
    public static final int ANYCHAR=6;
    public static final int ARG_ACTION=7;
    public static final int ASSIGN=8;
    public static final int BANG=9;
    public static final int CHAR_LITERAL=10;
    public static final int CLOSE_ELEMENT_OPTION=11;
    public static final int COLON=12;
    public static final int COMMA=13;
    public static final int COMMENT=14;
    public static final int CONTINUE_COMMENT=15;
    public static final int CONTINUE_DOUBLE_ANGLE_STRING_LITERAL=16;
    public static final int DIRECTIVE=17;
    public static final int DOLLAR=18;
    public static final int DOUBLE_ANGLE_STRING_LITERAL=19;
    public static final int END_COMMENT=20;
    public static final int END_DOUBLE_ANGLE_STRING_LITERAL=21;
    public static final int ESC=22;
    public static final int ESCAPE_BRACKET=23;
    public static final int ETC=24;
    public static final int IDENTIFIER=25;
    public static final int IMPLIES=26;
    public static final int INT=27;
    public static final int InvalidGrammarOption=28;
    public static final int Keyword=29;
    public static final int LABEL=30;
    public static final int LBRACK=31;
    public static final int LCURLY=32;
    public static final int LPAREN=33;
    public static final int LexerRule=34;
    public static final int ML_COMMENT=35;
    public static final int NEWLINE=36;
    public static final int NOT=37;
    public static final int OPEN_ELEMENT_OPTION=38;
    public static final int OR=39;
    public static final int OptionValue=40;
    public static final int PLUS=41;
    public static final int PLUS_ASSIGN=42;
    public static final int ParserRule=43;
    public static final int QUESTION=44;
    public static final int RANGE=45;
    public static final int RBRACK=46;
    public static final int RCURLY=47;
    public static final int REFERENCE=48;
    public static final int REWRITE=49;
    public static final int ROOT=50;
    public static final int RPAREN=51;
    public static final int SEMI=52;
    public static final int STAR=53;
    public static final int STRING_LITERAL=54;
    public static final int TREE_BEGIN=55;
    public static final int ValidGrammarOption=56;
    public static final int WILDCARD=57;
    public static final int WS=58;
    public static final int XDIGIT=59;
    public static final int ACTION_CHAR_LITERAL=60;
    public static final int ACTION_COMMENT=61;
    public static final int ACTION_ESCAPE=62;
    public static final int ACTION_ML_COMMENT=63;
    public static final int ACTION_REFERENCE=64;
    public static final int ACTION_STRING_LITERAL=65;
    public static final int ACTION_TEXT=66;
    public static final int CONTINUE_CHAR_LITERAL=67;
    public static final int CONTINUE_STRING_LITERAL=68;
    public static final int END_CHAR_LITERAL=69;
    public static final int END_STRING_LITERAL=70;

    protected ANTLRHighlighterLexer getLexer() {
        throw new UnsupportedOperationException("This method is not implemented in the base lexer.");
    }

    private static boolean isIdStartChar(int c) {
        return (c >= 'a' && c <= 'z')
            || (c >= 'A' && c <= 'Z')
            || c == '_';
    }


    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public ActionHighlighterLexerBase() {} 
    public ActionHighlighterLexerBase(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public ActionHighlighterLexerBase(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "ActionHighlighterLexerBase.g"; }

    // $ANTLR start "ACTION_REFERENCE"
    public final void mACTION_REFERENCE() throws RecognitionException {
        try {
            int _type = ACTION_REFERENCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ActionHighlighterLexerBase.g:41:2: ( '$' IDENTIFIER )
            // ActionHighlighterLexerBase.g:41:4: '$' IDENTIFIER
            {
            match('$'); 

            mIDENTIFIER(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ACTION_REFERENCE"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            // ActionHighlighterLexerBase.g:46:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // ActionHighlighterLexerBase.g:46:4: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // ActionHighlighterLexerBase.g:47:3: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ActionHighlighterLexerBase.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "DOLLAR"
    public final void mDOLLAR() throws RecognitionException {
        try {
            int _type = DOLLAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ActionHighlighterLexerBase.g:50:8: ( '$' )
            // ActionHighlighterLexerBase.g:50:10: '$'
            {
            match('$'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOLLAR"

    // $ANTLR start "ACTION_CHAR_LITERAL"
    public final void mACTION_CHAR_LITERAL() throws RecognitionException {
        try {
            int _type = ACTION_CHAR_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ActionHighlighterLexerBase.g:53:2: ( '\\'' CONTINUE_CHAR_LITERAL )
            // ActionHighlighterLexerBase.g:53:4: '\\'' CONTINUE_CHAR_LITERAL
            {
            match('\''); 

            mCONTINUE_CHAR_LITERAL(); 


            _type = state.type;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ACTION_CHAR_LITERAL"

    // $ANTLR start "END_CHAR_LITERAL"
    public final void mEND_CHAR_LITERAL() throws RecognitionException {
        try {
            // ActionHighlighterLexerBase.g:56:27: ()
            // ActionHighlighterLexerBase.g:56:29: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "END_CHAR_LITERAL"

    // $ANTLR start "CONTINUE_CHAR_LITERAL"
    public final void mCONTINUE_CHAR_LITERAL() throws RecognitionException {
        try {
            // ActionHighlighterLexerBase.g:59:2: ( (~ ( '\\r' | '\\n' | '\\'' | '\\\\' ) | '\\\\' ( options {greedy=true; } : . )? )* ( '\\'' |) )
            // ActionHighlighterLexerBase.g:59:4: (~ ( '\\r' | '\\n' | '\\'' | '\\\\' ) | '\\\\' ( options {greedy=true; } : . )? )* ( '\\'' |)
            {
            // ActionHighlighterLexerBase.g:59:4: (~ ( '\\r' | '\\n' | '\\'' | '\\\\' ) | '\\\\' ( options {greedy=true; } : . )? )*
            loop3:
            do {
                int alt3=3;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '\u0000' && LA3_0 <= '\t')||(LA3_0 >= '\u000B' && LA3_0 <= '\f')||(LA3_0 >= '\u000E' && LA3_0 <= '&')||(LA3_0 >= '(' && LA3_0 <= '[')||(LA3_0 >= ']' && LA3_0 <= '\uFFFF')) ) {
                    alt3=1;
                }
                else if ( (LA3_0=='\\') ) {
                    alt3=2;
                }


                switch (alt3) {
            	case 1 :
            	    // ActionHighlighterLexerBase.g:59:6: ~ ( '\\r' | '\\n' | '\\'' | '\\\\' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
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
            	    // ActionHighlighterLexerBase.g:60:5: '\\\\' ( options {greedy=true; } : . )?
            	    {
            	    match('\\'); 

            	    // ActionHighlighterLexerBase.g:60:10: ( options {greedy=true; } : . )?
            	    int alt2=2;
            	    int LA2_0 = input.LA(1);

            	    if ( (LA2_0=='\'') ) {
            	        alt2=1;
            	    }
            	    else if ( ((LA2_0 >= '\u0000' && LA2_0 <= '\t')||(LA2_0 >= '\u000B' && LA2_0 <= '\f')||(LA2_0 >= '\u000E' && LA2_0 <= '&')||(LA2_0 >= '(' && LA2_0 <= '[')||(LA2_0 >= ']' && LA2_0 <= '\uFFFF')) ) {
            	        alt2=1;
            	    }
            	    else if ( (LA2_0=='\n'||LA2_0=='\r'||LA2_0=='\\') ) {
            	        alt2=1;
            	    }
            	    switch (alt2) {
            	        case 1 :
            	            // ActionHighlighterLexerBase.g:60:35: .
            	            {
            	            matchAny(); 

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            // ActionHighlighterLexerBase.g:62:3: ( '\\'' |)
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\'') ) {
                alt4=1;
            }
            else {
                alt4=2;
            }
            switch (alt4) {
                case 1 :
                    // ActionHighlighterLexerBase.g:62:5: '\\''
                    {
                    match('\''); 

                    state.type = END_CHAR_LITERAL;

                    }
                    break;
                case 2 :
                    // ActionHighlighterLexerBase.g:63:5: 
                    {
                    state.type = CONTINUE_CHAR_LITERAL;

                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONTINUE_CHAR_LITERAL"

    // $ANTLR start "ACTION_STRING_LITERAL"
    public final void mACTION_STRING_LITERAL() throws RecognitionException {
        try {
            int _type = ACTION_STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ActionHighlighterLexerBase.g:68:2: ( '\"' CONTINUE_STRING_LITERAL )
            // ActionHighlighterLexerBase.g:68:4: '\"' CONTINUE_STRING_LITERAL
            {
            match('\"'); 

            mCONTINUE_STRING_LITERAL(); 


            _type = state.type;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ACTION_STRING_LITERAL"

    // $ANTLR start "END_STRING_LITERAL"
    public final void mEND_STRING_LITERAL() throws RecognitionException {
        try {
            // ActionHighlighterLexerBase.g:71:29: ()
            // ActionHighlighterLexerBase.g:71:31: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "END_STRING_LITERAL"

    // $ANTLR start "CONTINUE_STRING_LITERAL"
    public final void mCONTINUE_STRING_LITERAL() throws RecognitionException {
        try {
            // ActionHighlighterLexerBase.g:74:2: ( (~ ( '\\r' | '\\n' | '\"' | '\\\\' ) | '\\\\' ( options {greedy=true; } : . )? )* ( '\"' |) )
            // ActionHighlighterLexerBase.g:74:4: (~ ( '\\r' | '\\n' | '\"' | '\\\\' ) | '\\\\' ( options {greedy=true; } : . )? )* ( '\"' |)
            {
            // ActionHighlighterLexerBase.g:74:4: (~ ( '\\r' | '\\n' | '\"' | '\\\\' ) | '\\\\' ( options {greedy=true; } : . )? )*
            loop6:
            do {
                int alt6=3;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0 >= '\u0000' && LA6_0 <= '\t')||(LA6_0 >= '\u000B' && LA6_0 <= '\f')||(LA6_0 >= '\u000E' && LA6_0 <= '!')||(LA6_0 >= '#' && LA6_0 <= '[')||(LA6_0 >= ']' && LA6_0 <= '\uFFFF')) ) {
                    alt6=1;
                }
                else if ( (LA6_0=='\\') ) {
                    alt6=2;
                }


                switch (alt6) {
            	case 1 :
            	    // ActionHighlighterLexerBase.g:74:6: ~ ( '\\r' | '\\n' | '\"' | '\\\\' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
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
            	    // ActionHighlighterLexerBase.g:75:5: '\\\\' ( options {greedy=true; } : . )?
            	    {
            	    match('\\'); 

            	    // ActionHighlighterLexerBase.g:75:10: ( options {greedy=true; } : . )?
            	    int alt5=2;
            	    int LA5_0 = input.LA(1);

            	    if ( (LA5_0=='\"') ) {
            	        alt5=1;
            	    }
            	    else if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\t')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= '!')||(LA5_0 >= '#' && LA5_0 <= '[')||(LA5_0 >= ']' && LA5_0 <= '\uFFFF')) ) {
            	        alt5=1;
            	    }
            	    else if ( (LA5_0=='\n'||LA5_0=='\r'||LA5_0=='\\') ) {
            	        alt5=1;
            	    }
            	    switch (alt5) {
            	        case 1 :
            	            // ActionHighlighterLexerBase.g:75:35: .
            	            {
            	            matchAny(); 

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            // ActionHighlighterLexerBase.g:77:3: ( '\"' |)
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='\"') ) {
                alt7=1;
            }
            else {
                alt7=2;
            }
            switch (alt7) {
                case 1 :
                    // ActionHighlighterLexerBase.g:77:5: '\"'
                    {
                    match('\"'); 

                    state.type = END_STRING_LITERAL;

                    }
                    break;
                case 2 :
                    // ActionHighlighterLexerBase.g:78:5: 
                    {
                    state.type = CONTINUE_STRING_LITERAL;

                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONTINUE_STRING_LITERAL"

    // $ANTLR start "ACTION_COMMENT"
    public final void mACTION_COMMENT() throws RecognitionException {
        try {
            int _type = ACTION_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ActionHighlighterLexerBase.g:83:2: ( '//' (~ ( '\\r' | '\\n' ) )* )
            // ActionHighlighterLexerBase.g:83:4: '//' (~ ( '\\r' | '\\n' ) )*
            {
            match("//"); 



            // ActionHighlighterLexerBase.g:83:9: (~ ( '\\r' | '\\n' ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= '\u0000' && LA8_0 <= '\t')||(LA8_0 >= '\u000B' && LA8_0 <= '\f')||(LA8_0 >= '\u000E' && LA8_0 <= '\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ActionHighlighterLexerBase.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
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
            	    break loop8;
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
    // $ANTLR end "ACTION_COMMENT"

    // $ANTLR start "ACTION_ML_COMMENT"
    public final void mACTION_ML_COMMENT() throws RecognitionException {
        try {
            int _type = ACTION_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ActionHighlighterLexerBase.g:87:2: ( '/*' CONTINUE_COMMENT )
            // ActionHighlighterLexerBase.g:87:4: '/*' CONTINUE_COMMENT
            {
            match("/*"); 



            mCONTINUE_COMMENT(); 


            _type = state.type;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ACTION_ML_COMMENT"

    // $ANTLR start "END_COMMENT"
    public final void mEND_COMMENT() throws RecognitionException {
        try {
            // ActionHighlighterLexerBase.g:90:22: ()
            // ActionHighlighterLexerBase.g:90:24: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "END_COMMENT"

    // $ANTLR start "CONTINUE_COMMENT"
    public final void mCONTINUE_COMMENT() throws RecognitionException {
        try {
            // ActionHighlighterLexerBase.g:94:2: ( (~ ( '\\r' | '\\n' | '*' ) |{...}? => '*' )* ( '*/' |) )
            // ActionHighlighterLexerBase.g:94:4: (~ ( '\\r' | '\\n' | '*' ) |{...}? => '*' )* ( '*/' |)
            {
            // ActionHighlighterLexerBase.g:94:4: (~ ( '\\r' | '\\n' | '*' ) |{...}? => '*' )*
            loop9:
            do {
                int alt9=3;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='*') ) {
                    int LA9_1 = input.LA(2);

                    if ( (LA9_1=='/') ) {
                        int LA9_4 = input.LA(3);

                        if ( ((input.LA(2) != '/')) ) {
                            alt9=2;
                        }


                    }

                    else {
                        alt9=2;
                    }


                }
                else if ( ((LA9_0 >= '\u0000' && LA9_0 <= '\t')||(LA9_0 >= '\u000B' && LA9_0 <= '\f')||(LA9_0 >= '\u000E' && LA9_0 <= ')')||(LA9_0 >= '+' && LA9_0 <= '\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ActionHighlighterLexerBase.g:94:6: ~ ( '\\r' | '\\n' | '*' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= ')')||(input.LA(1) >= '+' && input.LA(1) <= '\uFFFF') ) {
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
            	    // ActionHighlighterLexerBase.g:95:5: {...}? => '*'
            	    {
            	    if ( !((input.LA(2) != '/')) ) {
            	        throw new FailedPredicateException(input, "CONTINUE_COMMENT", "input.LA(2) != '/'");
            	    }

            	    match('*'); 

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            // ActionHighlighterLexerBase.g:97:3: ( '*/' |)
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='*') ) {
                alt10=1;
            }
            else {
                alt10=2;
            }
            switch (alt10) {
                case 1 :
                    // ActionHighlighterLexerBase.g:97:5: '*/'
                    {
                    match("*/"); 



                    state.type = END_COMMENT;

                    }
                    break;
                case 2 :
                    // ActionHighlighterLexerBase.g:98:5: 
                    {
                    state.type = CONTINUE_COMMENT;

                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONTINUE_COMMENT"

    // $ANTLR start "ACTION_ESCAPE"
    public final void mACTION_ESCAPE() throws RecognitionException {
        try {
            int _type = ACTION_ESCAPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ActionHighlighterLexerBase.g:103:2: ( '\\\\' . )
            // ActionHighlighterLexerBase.g:103:4: '\\\\' .
            {
            match('\\'); 

            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ACTION_ESCAPE"

    // $ANTLR start "ACTION_TEXT"
    public final void mACTION_TEXT() throws RecognitionException {
        try {
            int _type = ACTION_TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ActionHighlighterLexerBase.g:107:2: ( (~ ( '\\r' | '\\n' | '\\\\' | '/' | '{' | '}' | ']' | '$' | '\\'' | '\"' ) |{...}? => ( '{' | '}' ) |{...}? => ']' |{...}? => '$' |{...}? => '/' )+ )
            // ActionHighlighterLexerBase.g:107:4: (~ ( '\\r' | '\\n' | '\\\\' | '/' | '{' | '}' | ']' | '$' | '\\'' | '\"' ) |{...}? => ( '{' | '}' ) |{...}? => ']' |{...}? => '$' |{...}? => '/' )+
            {
            // ActionHighlighterLexerBase.g:107:4: (~ ( '\\r' | '\\n' | '\\\\' | '/' | '{' | '}' | ']' | '$' | '\\'' | '\"' ) |{...}? => ( '{' | '}' ) |{...}? => ']' |{...}? => '$' |{...}? => '/' )+
            int cnt11=0;
            loop11:
            do {
                int alt11=6;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0 >= '\u0000' && LA11_0 <= '\t')||(LA11_0 >= '\u000B' && LA11_0 <= '\f')||(LA11_0 >= '\u000E' && LA11_0 <= '!')||LA11_0=='#'||(LA11_0 >= '%' && LA11_0 <= '&')||(LA11_0 >= '(' && LA11_0 <= '.')||(LA11_0 >= '0' && LA11_0 <= '[')||(LA11_0 >= '^' && LA11_0 <= 'z')||LA11_0=='|'||(LA11_0 >= '~' && LA11_0 <= '\uFFFF')) ) {
                    alt11=1;
                }
                else if ( (LA11_0=='{'||LA11_0=='}') && ((!getLexer().getMode().equals(ANTLRHighlighterMode.ACTION)))) {
                    alt11=2;
                }
                else if ( (LA11_0==']') && ((!getLexer().getMode().equals(ANTLRHighlighterMode.ARG_ACTION)))) {
                    alt11=3;
                }
                else if ( (LA11_0=='$') && ((!isIdStartChar(input.LA(2))))) {
                    alt11=4;
                }
                else if ( (LA11_0=='/') && ((input.LA(2) != '/' && input.LA(2) != '*'))) {
                    alt11=5;
                }


                switch (alt11) {
            	case 1 :
            	    // ActionHighlighterLexerBase.g:107:6: ~ ( '\\r' | '\\n' | '\\\\' | '/' | '{' | '}' | ']' | '$' | '\\'' | '\"' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '!')||input.LA(1)=='#'||(input.LA(1) >= '%' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '[')||(input.LA(1) >= '^' && input.LA(1) <= 'z')||input.LA(1)=='|'||(input.LA(1) >= '~' && input.LA(1) <= '\uFFFF') ) {
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
            	    // ActionHighlighterLexerBase.g:108:5: {...}? => ( '{' | '}' )
            	    {
            	    if ( !((!getLexer().getMode().equals(ANTLRHighlighterMode.ACTION))) ) {
            	        throw new FailedPredicateException(input, "ACTION_TEXT", "!getLexer().getMode().equals(ANTLRHighlighterMode.ACTION)");
            	    }

            	    if ( input.LA(1)=='{'||input.LA(1)=='}' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;
            	case 3 :
            	    // ActionHighlighterLexerBase.g:109:5: {...}? => ']'
            	    {
            	    if ( !((!getLexer().getMode().equals(ANTLRHighlighterMode.ARG_ACTION))) ) {
            	        throw new FailedPredicateException(input, "ACTION_TEXT", "!getLexer().getMode().equals(ANTLRHighlighterMode.ARG_ACTION)");
            	    }

            	    match(']'); 

            	    }
            	    break;
            	case 4 :
            	    // ActionHighlighterLexerBase.g:110:5: {...}? => '$'
            	    {
            	    if ( !((!isIdStartChar(input.LA(2)))) ) {
            	        throw new FailedPredicateException(input, "ACTION_TEXT", "!isIdStartChar(input.LA(2))");
            	    }

            	    match('$'); 

            	    }
            	    break;
            	case 5 :
            	    // ActionHighlighterLexerBase.g:111:5: {...}? => '/'
            	    {
            	    if ( !((input.LA(2) != '/' && input.LA(2) != '*')) ) {
            	        throw new FailedPredicateException(input, "ACTION_TEXT", "input.LA(2) != '/' && input.LA(2) != '*'");
            	    }

            	    match('/'); 

            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ACTION_TEXT"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ActionHighlighterLexerBase.g:116:2: ( ( '\\r' )? '\\n' )
            // ActionHighlighterLexerBase.g:116:4: ( '\\r' )? '\\n'
            {
            // ActionHighlighterLexerBase.g:116:4: ( '\\r' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='\r') ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ActionHighlighterLexerBase.g:116:4: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "ANYCHAR"
    public final void mANYCHAR() throws RecognitionException {
        try {
            int _type = ANYCHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ActionHighlighterLexerBase.g:120:2: ( . )
            // ActionHighlighterLexerBase.g:120:4: .
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
        // ActionHighlighterLexerBase.g:1:8: ( ACTION_REFERENCE | DOLLAR | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL | ACTION_COMMENT | ACTION_ML_COMMENT | ACTION_ESCAPE | ACTION_TEXT | NEWLINE | ANYCHAR )
        int alt13=10;
        alt13 = dfa13.predict(input);
        switch (alt13) {
            case 1 :
                // ActionHighlighterLexerBase.g:1:10: ACTION_REFERENCE
                {
                mACTION_REFERENCE(); 


                }
                break;
            case 2 :
                // ActionHighlighterLexerBase.g:1:27: DOLLAR
                {
                mDOLLAR(); 


                }
                break;
            case 3 :
                // ActionHighlighterLexerBase.g:1:34: ACTION_CHAR_LITERAL
                {
                mACTION_CHAR_LITERAL(); 


                }
                break;
            case 4 :
                // ActionHighlighterLexerBase.g:1:54: ACTION_STRING_LITERAL
                {
                mACTION_STRING_LITERAL(); 


                }
                break;
            case 5 :
                // ActionHighlighterLexerBase.g:1:76: ACTION_COMMENT
                {
                mACTION_COMMENT(); 


                }
                break;
            case 6 :
                // ActionHighlighterLexerBase.g:1:91: ACTION_ML_COMMENT
                {
                mACTION_ML_COMMENT(); 


                }
                break;
            case 7 :
                // ActionHighlighterLexerBase.g:1:109: ACTION_ESCAPE
                {
                mACTION_ESCAPE(); 


                }
                break;
            case 8 :
                // ActionHighlighterLexerBase.g:1:123: ACTION_TEXT
                {
                mACTION_TEXT(); 


                }
                break;
            case 9 :
                // ActionHighlighterLexerBase.g:1:135: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 10 :
                // ActionHighlighterLexerBase.g:1:143: ANYCHAR
                {
                mANYCHAR(); 


                }
                break;

        }

    }


    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA13_eotS =
        "\1\uffff\1\14\2\uffff\1\22\1\25\1\uffff\1\27\1\31\1\25\1\uffff\1"+
        "\35\4\uffff\1\40\1\50\12\uffff\1\56\2\uffff\1\60\1\uffff\1\61\1"+
        "\62\1\63\1\64\1\uffff\1\65\1\67\1\uffff\1\70\1\71\1\72\1\73\11\uffff"+
        "\1\74\6\uffff";
    static final String DFA13_eofS =
        "\75\uffff";
    static final String DFA13_minS =
        "\2\0\2\uffff\2\0\1\uffff\2\0\1\12\1\uffff\1\0\4\uffff\3\0\4\uffff"+
        "\1\0\1\uffff\1\0\2\uffff\2\0\1\uffff\6\0\1\uffff\7\0\1\uffff\1\0"+
        "\1\uffff\15\0";
    static final String DFA13_maxS =
        "\2\uffff\2\uffff\2\uffff\1\uffff\2\uffff\1\12\1\uffff\1\uffff\4"+
        "\uffff\2\uffff\1\0\4\uffff\1\0\1\uffff\1\0\2\uffff\1\uffff\1\0\1"+
        "\uffff\1\uffff\1\0\4\uffff\1\uffff\2\uffff\1\0\4\uffff\1\uffff\1"+
        "\0\1\uffff\6\0\1\uffff\6\0";
    static final String DFA13_acceptS =
        "\2\uffff\1\3\1\4\2\uffff\1\10\3\uffff\1\11\1\uffff\1\2\1\10\1\3"+
        "\1\4\3\uffff\1\10\1\7\1\12\1\10\1\uffff\1\10\1\uffff\1\10\1\11\2"+
        "\uffff\1\10\6\uffff\1\5\7\uffff\1\6\1\uffff\1\1\15\uffff";
    static final String DFA13_specialS =
        "\1\46\1\33\2\uffff\1\37\1\35\1\uffff\1\50\1\41\2\uffff\1\40\4\uffff"+
        "\1\34\1\32\1\22\4\uffff\1\21\1\uffff\1\23\2\uffff\1\16\1\17\1\uffff"+
        "\1\43\1\14\1\45\1\47\1\36\1\44\1\uffff\1\31\1\42\1\7\1\27\1\26\1"+
        "\25\1\24\1\uffff\1\20\1\uffff\1\15\1\13\1\12\1\11\1\10\1\6\1\30"+
        "\1\5\1\4\1\3\1\2\1\1\1\0}>";
    static final String[] DFA13_transitionS = {
            "\12\6\1\12\2\6\1\11\24\6\1\3\1\6\1\1\2\6\1\2\7\6\1\4\54\6\1"+
            "\5\1\10\35\6\1\7\1\6\1\7\uff82\6",
            "\12\15\1\uffff\2\15\1\uffff\24\15\1\uffff\4\15\1\uffff\31\15"+
            "\32\13\1\15\1\uffff\2\15\1\13\1\15\32\13\uff85\15",
            "",
            "",
            "\12\23\1\uffff\2\23\1\uffff\24\23\1\uffff\4\23\1\uffff\2\23"+
            "\1\21\4\23\1\20\54\23\1\uffff\uffa3\23",
            "\0\24",
            "",
            "\12\30\1\uffff\2\30\1\uffff\24\30\1\uffff\4\30\1\uffff\64\30"+
            "\1\uffff\uffa3\30",
            "\12\32\1\uffff\2\32\1\uffff\24\32\1\uffff\4\32\1\uffff\64\32"+
            "\1\uffff\uffa3\32",
            "\1\33",
            "",
            "\12\15\1\uffff\2\15\1\uffff\24\15\1\uffff\1\15\1\36\2\15\1"+
            "\uffff\7\15\1\36\12\34\7\15\32\34\1\15\1\uffff\1\36\1\15\1\34"+
            "\1\15\32\34\1\36\1\15\1\36\uff82\15",
            "",
            "",
            "",
            "",
            "\12\37\1\uffff\2\37\1\uffff\24\37\1\45\1\37\1\43\2\37\1\45"+
            "\7\37\1\44\54\37\1\45\1\42\35\37\1\41\1\37\1\41\uff82\37",
            "\12\46\1\uffff\2\46\1\uffff\24\46\1\55\1\46\1\53\2\46\1\55"+
            "\2\46\1\47\4\46\1\54\54\46\1\55\1\52\35\46\1\51\1\46\1\51\uff82"+
            "\46",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "\1\uffff",
            "",
            "",
            "\12\15\1\uffff\2\15\1\uffff\24\15\1\uffff\1\15\1\36\2\15\1"+
            "\uffff\7\15\1\36\12\34\7\15\32\34\1\15\1\uffff\1\36\1\15\1\34"+
            "\1\15\32\34\1\36\1\15\1\36\uff82\15",
            "\1\uffff",
            "",
            "\12\37\1\uffff\2\37\1\uffff\24\37\1\45\1\37\1\43\2\37\1\45"+
            "\7\37\1\44\54\37\1\45\1\42\35\37\1\41\1\37\1\41\uff82\37",
            "\1\uffff",
            "\12\37\1\uffff\2\37\1\uffff\24\37\1\45\1\37\1\43\2\37\1\45"+
            "\7\37\1\44\54\37\1\45\1\42\35\37\1\41\1\37\1\41\uff82\37",
            "\12\37\1\uffff\2\37\1\uffff\24\37\1\45\1\37\1\43\2\37\1\45"+
            "\7\37\1\44\54\37\1\45\1\42\35\37\1\41\1\37\1\41\uff82\37",
            "\12\37\1\uffff\2\37\1\uffff\24\37\1\45\1\37\1\43\2\37\1\45"+
            "\7\37\1\44\54\37\1\45\1\42\35\37\1\41\1\37\1\41\uff82\37",
            "\12\37\1\uffff\2\37\1\uffff\24\37\1\45\1\37\1\43\2\37\1\45"+
            "\7\37\1\44\54\37\1\45\1\42\35\37\1\41\1\37\1\41\uff82\37",
            "",
            "\12\46\1\uffff\2\46\1\uffff\24\46\1\55\1\46\1\53\2\46\1\55"+
            "\2\46\1\47\4\46\1\54\54\46\1\55\1\52\35\46\1\51\1\46\1\51\uff82"+
            "\46",
            "\12\46\1\uffff\2\46\1\uffff\24\46\1\55\1\46\1\53\2\46\1\55"+
            "\2\46\1\47\4\46\1\66\54\46\1\55\1\52\35\46\1\51\1\46\1\51\uff82"+
            "\46",
            "\1\uffff",
            "\12\46\1\uffff\2\46\1\uffff\24\46\1\55\1\46\1\53\2\46\1\55"+
            "\2\46\1\47\4\46\1\54\54\46\1\55\1\52\35\46\1\51\1\46\1\51\uff82"+
            "\46",
            "\12\46\1\uffff\2\46\1\uffff\24\46\1\55\1\46\1\53\2\46\1\55"+
            "\2\46\1\47\4\46\1\54\54\46\1\55\1\52\35\46\1\51\1\46\1\51\uff82"+
            "\46",
            "\12\46\1\uffff\2\46\1\uffff\24\46\1\55\1\46\1\53\2\46\1\55"+
            "\2\46\1\47\4\46\1\54\54\46\1\55\1\52\35\46\1\51\1\46\1\51\uff82"+
            "\46",
            "\12\46\1\uffff\2\46\1\uffff\24\46\1\55\1\46\1\53\2\46\1\55"+
            "\2\46\1\47\4\46\1\54\54\46\1\55\1\52\35\46\1\51\1\46\1\51\uff82"+
            "\46",
            "",
            "\1\uffff",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\12\46\1\uffff\2\46\1\uffff\24\46\1\55\1\46\1\53\2\46\1\55"+
            "\2\46\1\47\4\46\1\54\54\46\1\55\1\52\35\46\1\51\1\46\1\51\uff82"+
            "\46",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff"
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( ACTION_REFERENCE | DOLLAR | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL | ACTION_COMMENT | ACTION_ML_COMMENT | ACTION_ESCAPE | ACTION_TEXT | NEWLINE | ANYCHAR );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_60 = input.LA(1);

                         
                        int index13_60 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(2) != '/' && input.LA(2) != '*')))) ) {s = 45;}

                        else if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                         
                        input.seek(index13_60);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA13_59 = input.LA(1);

                         
                        int index13_59 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(2) != '/' && input.LA(2) != '*')))) ) {s = 45;}

                        else if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                         
                        input.seek(index13_59);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA13_58 = input.LA(1);

                         
                        int index13_58 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(2) != '/' && input.LA(2) != '*')))) ) {s = 45;}

                        else if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                         
                        input.seek(index13_58);

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA13_57 = input.LA(1);

                         
                        int index13_57 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(2) != '/' && input.LA(2) != '*')))) ) {s = 45;}

                        else if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                         
                        input.seek(index13_57);

                        if ( s>=0 ) return s;
                        break;

                    case 4 : 
                        int LA13_56 = input.LA(1);

                         
                        int index13_56 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(2) != '/' && input.LA(2) != '*')))) ) {s = 45;}

                        else if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                         
                        input.seek(index13_56);

                        if ( s>=0 ) return s;
                        break;

                    case 5 : 
                        int LA13_55 = input.LA(1);

                         
                        int index13_55 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(2) != '/' && input.LA(2) != '*')))) ) {s = 45;}

                        else if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                         
                        input.seek(index13_55);

                        if ( s>=0 ) return s;
                        break;

                    case 6 : 
                        int LA13_53 = input.LA(1);

                         
                        int index13_53 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(2) != '/' && input.LA(2) != '*')))) ) {s = 45;}

                        else if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                         
                        input.seek(index13_53);

                        if ( s>=0 ) return s;
                        break;

                    case 7 : 
                        int LA13_40 = input.LA(1);

                         
                        int index13_40 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(2) != '/' && input.LA(2) != '*')))) ) {s = 45;}

                        else if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                         
                        input.seek(index13_40);

                        if ( s>=0 ) return s;
                        break;

                    case 8 : 
                        int LA13_52 = input.LA(1);

                         
                        int index13_52 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(2) != '/' && input.LA(2) != '*')))) ) {s = 37;}

                        else if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                         
                        input.seek(index13_52);

                        if ( s>=0 ) return s;
                        break;

                    case 9 : 
                        int LA13_51 = input.LA(1);

                         
                        int index13_51 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(2) != '/' && input.LA(2) != '*')))) ) {s = 37;}

                        else if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                         
                        input.seek(index13_51);

                        if ( s>=0 ) return s;
                        break;

                    case 10 : 
                        int LA13_50 = input.LA(1);

                         
                        int index13_50 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(2) != '/' && input.LA(2) != '*')))) ) {s = 37;}

                        else if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                         
                        input.seek(index13_50);

                        if ( s>=0 ) return s;
                        break;

                    case 11 : 
                        int LA13_49 = input.LA(1);

                         
                        int index13_49 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(2) != '/' && input.LA(2) != '*')))) ) {s = 37;}

                        else if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                         
                        input.seek(index13_49);

                        if ( s>=0 ) return s;
                        break;

                    case 12 : 
                        int LA13_32 = input.LA(1);

                         
                        int index13_32 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(2) != '/' && input.LA(2) != '*')))) ) {s = 37;}

                        else if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                         
                        input.seek(index13_32);

                        if ( s>=0 ) return s;
                        break;

                    case 13 : 
                        int LA13_48 = input.LA(1);

                         
                        int index13_48 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(2) != '/' && input.LA(2) != '*')))) ) {s = 37;}

                        else if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                         
                        input.seek(index13_48);

                        if ( s>=0 ) return s;
                        break;

                    case 14 : 
                        int LA13_28 = input.LA(1);

                         
                        int index13_28 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((LA13_28 >= '0' && LA13_28 <= '9')||(LA13_28 >= 'A' && LA13_28 <= 'Z')||LA13_28=='_'||(LA13_28 >= 'a' && LA13_28 <= 'z')) ) {s = 28;}

                        else if ( ((LA13_28 >= '\u0000' && LA13_28 <= '\t')||(LA13_28 >= '\u000B' && LA13_28 <= '\f')||(LA13_28 >= '\u000E' && LA13_28 <= '!')||LA13_28=='#'||(LA13_28 >= '%' && LA13_28 <= '&')||(LA13_28 >= '(' && LA13_28 <= '.')||(LA13_28 >= ':' && LA13_28 <= '@')||LA13_28=='['||LA13_28=='^'||LA13_28=='`'||LA13_28=='|'||(LA13_28 >= '~' && LA13_28 <= '\uFFFF')) && ((!isIdStartChar(input.LA(2))))) {s = 13;}

                        else if ( (LA13_28=='$'||LA13_28=='/'||LA13_28==']'||LA13_28=='{'||LA13_28=='}') && ((!isIdStartChar(input.LA(2))))) {s = 30;}

                        else s = 46;

                         
                        input.seek(index13_28);

                        if ( s>=0 ) return s;
                        break;

                    case 15 : 
                        int LA13_29 = input.LA(1);

                         
                        int index13_29 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((!isIdStartChar(input.LA(2)))))) ) {s = 47;}

                        else if ( ((!isIdStartChar(input.LA(2)))) ) {s = 30;}

                         
                        input.seek(index13_29);

                        if ( s>=0 ) return s;
                        break;

                    case 16 : 
                        int LA13_46 = input.LA(1);

                         
                        int index13_46 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((!isIdStartChar(input.LA(2)))))) ) {s = 47;}

                        else if ( ((!isIdStartChar(input.LA(2)))) ) {s = 30;}

                         
                        input.seek(index13_46);

                        if ( s>=0 ) return s;
                        break;

                    case 17 : 
                        int LA13_23 = input.LA(1);

                         
                        int index13_23 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((!getLexer().getMode().equals(ANTLRHighlighterMode.ACTION))) ) {s = 30;}

                        else if ( (true) ) {s = 21;}

                         
                        input.seek(index13_23);

                        if ( s>=0 ) return s;
                        break;

                    case 18 : 
                        int LA13_18 = input.LA(1);

                         
                        int index13_18 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((input.LA(2) != '/' && input.LA(2) != '*')) ) {s = 30;}

                        else if ( (true) ) {s = 21;}

                         
                        input.seek(index13_18);

                        if ( s>=0 ) return s;
                        break;

                    case 19 : 
                        int LA13_25 = input.LA(1);

                         
                        int index13_25 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((!getLexer().getMode().equals(ANTLRHighlighterMode.ARG_ACTION))) ) {s = 30;}

                        else if ( (true) ) {s = 21;}

                         
                        input.seek(index13_25);

                        if ( s>=0 ) return s;
                        break;

                    case 20 : 
                        int LA13_44 = input.LA(1);

                        s = -1;
                        if ( (LA13_44=='*') ) {s = 39;}

                        else if ( ((LA13_44 >= '\u0000' && LA13_44 <= '\t')||(LA13_44 >= '\u000B' && LA13_44 <= '\f')||(LA13_44 >= '\u000E' && LA13_44 <= '!')||LA13_44=='#'||(LA13_44 >= '%' && LA13_44 <= '&')||(LA13_44 >= '(' && LA13_44 <= ')')||(LA13_44 >= '+' && LA13_44 <= '.')||(LA13_44 >= '0' && LA13_44 <= '[')||(LA13_44 >= '^' && LA13_44 <= 'z')||LA13_44=='|'||(LA13_44 >= '~' && LA13_44 <= '\uFFFF')) ) {s = 38;}

                        else if ( (LA13_44=='{'||LA13_44=='}') ) {s = 41;}

                        else if ( (LA13_44==']') ) {s = 42;}

                        else if ( (LA13_44=='$') ) {s = 43;}

                        else if ( (LA13_44=='/') ) {s = 44;}

                        else if ( (LA13_44=='\"'||LA13_44=='\''||LA13_44=='\\') ) {s = 45;}

                        else s = 59;

                        if ( s>=0 ) return s;
                        break;

                    case 21 : 
                        int LA13_43 = input.LA(1);

                        s = -1;
                        if ( (LA13_43=='*') ) {s = 39;}

                        else if ( ((LA13_43 >= '\u0000' && LA13_43 <= '\t')||(LA13_43 >= '\u000B' && LA13_43 <= '\f')||(LA13_43 >= '\u000E' && LA13_43 <= '!')||LA13_43=='#'||(LA13_43 >= '%' && LA13_43 <= '&')||(LA13_43 >= '(' && LA13_43 <= ')')||(LA13_43 >= '+' && LA13_43 <= '.')||(LA13_43 >= '0' && LA13_43 <= '[')||(LA13_43 >= '^' && LA13_43 <= 'z')||LA13_43=='|'||(LA13_43 >= '~' && LA13_43 <= '\uFFFF')) ) {s = 38;}

                        else if ( (LA13_43=='{'||LA13_43=='}') ) {s = 41;}

                        else if ( (LA13_43==']') ) {s = 42;}

                        else if ( (LA13_43=='$') ) {s = 43;}

                        else if ( (LA13_43=='/') ) {s = 44;}

                        else if ( (LA13_43=='\"'||LA13_43=='\''||LA13_43=='\\') ) {s = 45;}

                        else s = 58;

                        if ( s>=0 ) return s;
                        break;

                    case 22 : 
                        int LA13_42 = input.LA(1);

                        s = -1;
                        if ( (LA13_42=='*') ) {s = 39;}

                        else if ( ((LA13_42 >= '\u0000' && LA13_42 <= '\t')||(LA13_42 >= '\u000B' && LA13_42 <= '\f')||(LA13_42 >= '\u000E' && LA13_42 <= '!')||LA13_42=='#'||(LA13_42 >= '%' && LA13_42 <= '&')||(LA13_42 >= '(' && LA13_42 <= ')')||(LA13_42 >= '+' && LA13_42 <= '.')||(LA13_42 >= '0' && LA13_42 <= '[')||(LA13_42 >= '^' && LA13_42 <= 'z')||LA13_42=='|'||(LA13_42 >= '~' && LA13_42 <= '\uFFFF')) ) {s = 38;}

                        else if ( (LA13_42=='{'||LA13_42=='}') ) {s = 41;}

                        else if ( (LA13_42==']') ) {s = 42;}

                        else if ( (LA13_42=='$') ) {s = 43;}

                        else if ( (LA13_42=='/') ) {s = 44;}

                        else if ( (LA13_42=='\"'||LA13_42=='\''||LA13_42=='\\') ) {s = 45;}

                        else s = 57;

                        if ( s>=0 ) return s;
                        break;

                    case 23 : 
                        int LA13_41 = input.LA(1);

                        s = -1;
                        if ( (LA13_41=='*') ) {s = 39;}

                        else if ( ((LA13_41 >= '\u0000' && LA13_41 <= '\t')||(LA13_41 >= '\u000B' && LA13_41 <= '\f')||(LA13_41 >= '\u000E' && LA13_41 <= '!')||LA13_41=='#'||(LA13_41 >= '%' && LA13_41 <= '&')||(LA13_41 >= '(' && LA13_41 <= ')')||(LA13_41 >= '+' && LA13_41 <= '.')||(LA13_41 >= '0' && LA13_41 <= '[')||(LA13_41 >= '^' && LA13_41 <= 'z')||LA13_41=='|'||(LA13_41 >= '~' && LA13_41 <= '\uFFFF')) ) {s = 38;}

                        else if ( (LA13_41=='{'||LA13_41=='}') ) {s = 41;}

                        else if ( (LA13_41==']') ) {s = 42;}

                        else if ( (LA13_41=='$') ) {s = 43;}

                        else if ( (LA13_41=='/') ) {s = 44;}

                        else if ( (LA13_41=='\"'||LA13_41=='\''||LA13_41=='\\') ) {s = 45;}

                        else s = 56;

                        if ( s>=0 ) return s;
                        break;

                    case 24 : 
                        int LA13_54 = input.LA(1);

                        s = -1;
                        if ( (LA13_54=='*') ) {s = 39;}

                        else if ( ((LA13_54 >= '\u0000' && LA13_54 <= '\t')||(LA13_54 >= '\u000B' && LA13_54 <= '\f')||(LA13_54 >= '\u000E' && LA13_54 <= '!')||LA13_54=='#'||(LA13_54 >= '%' && LA13_54 <= '&')||(LA13_54 >= '(' && LA13_54 <= ')')||(LA13_54 >= '+' && LA13_54 <= '.')||(LA13_54 >= '0' && LA13_54 <= '[')||(LA13_54 >= '^' && LA13_54 <= 'z')||LA13_54=='|'||(LA13_54 >= '~' && LA13_54 <= '\uFFFF')) ) {s = 38;}

                        else if ( (LA13_54=='{'||LA13_54=='}') ) {s = 41;}

                        else if ( (LA13_54==']') ) {s = 42;}

                        else if ( (LA13_54=='$') ) {s = 43;}

                        else if ( (LA13_54=='/') ) {s = 44;}

                        else if ( (LA13_54=='\"'||LA13_54=='\''||LA13_54=='\\') ) {s = 45;}

                        else s = 60;

                        if ( s>=0 ) return s;
                        break;

                    case 25 : 
                        int LA13_38 = input.LA(1);

                        s = -1;
                        if ( (LA13_38=='*') ) {s = 39;}

                        else if ( ((LA13_38 >= '\u0000' && LA13_38 <= '\t')||(LA13_38 >= '\u000B' && LA13_38 <= '\f')||(LA13_38 >= '\u000E' && LA13_38 <= '!')||LA13_38=='#'||(LA13_38 >= '%' && LA13_38 <= '&')||(LA13_38 >= '(' && LA13_38 <= ')')||(LA13_38 >= '+' && LA13_38 <= '.')||(LA13_38 >= '0' && LA13_38 <= '[')||(LA13_38 >= '^' && LA13_38 <= 'z')||LA13_38=='|'||(LA13_38 >= '~' && LA13_38 <= '\uFFFF')) ) {s = 38;}

                        else if ( (LA13_38=='{'||LA13_38=='}') ) {s = 41;}

                        else if ( (LA13_38==']') ) {s = 42;}

                        else if ( (LA13_38=='$') ) {s = 43;}

                        else if ( (LA13_38=='/') ) {s = 44;}

                        else if ( (LA13_38=='\"'||LA13_38=='\''||LA13_38=='\\') ) {s = 45;}

                        else s = 53;

                        if ( s>=0 ) return s;
                        break;

                    case 26 : 
                        int LA13_17 = input.LA(1);

                        s = -1;
                        if ( ((LA13_17 >= '\u0000' && LA13_17 <= '\t')||(LA13_17 >= '\u000B' && LA13_17 <= '\f')||(LA13_17 >= '\u000E' && LA13_17 <= '!')||LA13_17=='#'||(LA13_17 >= '%' && LA13_17 <= '&')||(LA13_17 >= '(' && LA13_17 <= ')')||(LA13_17 >= '+' && LA13_17 <= '.')||(LA13_17 >= '0' && LA13_17 <= '[')||(LA13_17 >= '^' && LA13_17 <= 'z')||LA13_17=='|'||(LA13_17 >= '~' && LA13_17 <= '\uFFFF')) ) {s = 38;}

                        else if ( (LA13_17=='*') ) {s = 39;}

                        else if ( (LA13_17=='{'||LA13_17=='}') ) {s = 41;}

                        else if ( (LA13_17==']') ) {s = 42;}

                        else if ( (LA13_17=='$') ) {s = 43;}

                        else if ( (LA13_17=='/') ) {s = 44;}

                        else if ( (LA13_17=='\"'||LA13_17=='\''||LA13_17=='\\') ) {s = 45;}

                        else s = 40;

                        if ( s>=0 ) return s;
                        break;

                    case 27 : 
                        int LA13_1 = input.LA(1);

                         
                        int index13_1 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((LA13_1 >= 'A' && LA13_1 <= 'Z')||LA13_1=='_'||(LA13_1 >= 'a' && LA13_1 <= 'z')) ) {s = 11;}

                        else if ( ((LA13_1 >= '\u0000' && LA13_1 <= '\t')||(LA13_1 >= '\u000B' && LA13_1 <= '\f')||(LA13_1 >= '\u000E' && LA13_1 <= '!')||(LA13_1 >= '#' && LA13_1 <= '&')||(LA13_1 >= '(' && LA13_1 <= '@')||LA13_1=='['||(LA13_1 >= ']' && LA13_1 <= '^')||LA13_1=='`'||(LA13_1 >= '{' && LA13_1 <= '\uFFFF')) && ((!isIdStartChar(input.LA(2))))) {s = 13;}

                        else s = 12;

                         
                        input.seek(index13_1);

                        if ( s>=0 ) return s;
                        break;

                    case 28 : 
                        int LA13_16 = input.LA(1);

                        s = -1;
                        if ( ((LA13_16 >= '\u0000' && LA13_16 <= '\t')||(LA13_16 >= '\u000B' && LA13_16 <= '\f')||(LA13_16 >= '\u000E' && LA13_16 <= '!')||LA13_16=='#'||(LA13_16 >= '%' && LA13_16 <= '&')||(LA13_16 >= '(' && LA13_16 <= '.')||(LA13_16 >= '0' && LA13_16 <= '[')||(LA13_16 >= '^' && LA13_16 <= 'z')||LA13_16=='|'||(LA13_16 >= '~' && LA13_16 <= '\uFFFF')) ) {s = 31;}

                        else if ( (LA13_16=='{'||LA13_16=='}') ) {s = 33;}

                        else if ( (LA13_16==']') ) {s = 34;}

                        else if ( (LA13_16=='$') ) {s = 35;}

                        else if ( (LA13_16=='/') ) {s = 36;}

                        else if ( (LA13_16=='\"'||LA13_16=='\''||LA13_16=='\\') ) {s = 37;}

                        else s = 32;

                        if ( s>=0 ) return s;
                        break;

                    case 29 : 
                        int LA13_5 = input.LA(1);

                        s = -1;
                        if ( ((LA13_5 >= '\u0000' && LA13_5 <= '\uFFFF')) ) {s = 20;}

                        else s = 21;

                        if ( s>=0 ) return s;
                        break;

                    case 30 : 
                        int LA13_35 = input.LA(1);

                        s = -1;
                        if ( ((LA13_35 >= '\u0000' && LA13_35 <= '\t')||(LA13_35 >= '\u000B' && LA13_35 <= '\f')||(LA13_35 >= '\u000E' && LA13_35 <= '!')||LA13_35=='#'||(LA13_35 >= '%' && LA13_35 <= '&')||(LA13_35 >= '(' && LA13_35 <= '.')||(LA13_35 >= '0' && LA13_35 <= '[')||(LA13_35 >= '^' && LA13_35 <= 'z')||LA13_35=='|'||(LA13_35 >= '~' && LA13_35 <= '\uFFFF')) ) {s = 31;}

                        else if ( (LA13_35=='{'||LA13_35=='}') ) {s = 33;}

                        else if ( (LA13_35==']') ) {s = 34;}

                        else if ( (LA13_35=='$') ) {s = 35;}

                        else if ( (LA13_35=='/') ) {s = 36;}

                        else if ( (LA13_35=='\"'||LA13_35=='\''||LA13_35=='\\') ) {s = 37;}

                        else s = 51;

                        if ( s>=0 ) return s;
                        break;

                    case 31 : 
                        int LA13_4 = input.LA(1);

                         
                        int index13_4 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA13_4=='/') ) {s = 16;}

                        else if ( (LA13_4=='*') ) {s = 17;}

                        else if ( ((LA13_4 >= '\u0000' && LA13_4 <= '\t')||(LA13_4 >= '\u000B' && LA13_4 <= '\f')||(LA13_4 >= '\u000E' && LA13_4 <= '!')||(LA13_4 >= '#' && LA13_4 <= '&')||(LA13_4 >= '(' && LA13_4 <= ')')||(LA13_4 >= '+' && LA13_4 <= '.')||(LA13_4 >= '0' && LA13_4 <= '[')||(LA13_4 >= ']' && LA13_4 <= '\uFFFF')) && ((input.LA(2) != '/' && input.LA(2) != '*'))) {s = 19;}

                        else s = 18;

                         
                        input.seek(index13_4);

                        if ( s>=0 ) return s;
                        break;

                    case 32 : 
                        int LA13_11 = input.LA(1);

                         
                        int index13_11 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((LA13_11 >= '0' && LA13_11 <= '9')||(LA13_11 >= 'A' && LA13_11 <= 'Z')||LA13_11=='_'||(LA13_11 >= 'a' && LA13_11 <= 'z')) ) {s = 28;}

                        else if ( ((LA13_11 >= '\u0000' && LA13_11 <= '\t')||(LA13_11 >= '\u000B' && LA13_11 <= '\f')||(LA13_11 >= '\u000E' && LA13_11 <= '!')||LA13_11=='#'||(LA13_11 >= '%' && LA13_11 <= '&')||(LA13_11 >= '(' && LA13_11 <= '.')||(LA13_11 >= ':' && LA13_11 <= '@')||LA13_11=='['||LA13_11=='^'||LA13_11=='`'||LA13_11=='|'||(LA13_11 >= '~' && LA13_11 <= '\uFFFF')) && ((!isIdStartChar(input.LA(2))))) {s = 13;}

                        else if ( (LA13_11=='$'||LA13_11=='/'||LA13_11==']'||LA13_11=='{'||LA13_11=='}') && ((!isIdStartChar(input.LA(2))))) {s = 30;}

                        else s = 29;

                         
                        input.seek(index13_11);

                        if ( s>=0 ) return s;
                        break;

                    case 33 : 
                        int LA13_8 = input.LA(1);

                         
                        int index13_8 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((LA13_8 >= '\u0000' && LA13_8 <= '\t')||(LA13_8 >= '\u000B' && LA13_8 <= '\f')||(LA13_8 >= '\u000E' && LA13_8 <= '!')||(LA13_8 >= '#' && LA13_8 <= '&')||(LA13_8 >= '(' && LA13_8 <= '[')||(LA13_8 >= ']' && LA13_8 <= '\uFFFF')) && ((!getLexer().getMode().equals(ANTLRHighlighterMode.ARG_ACTION)))) {s = 26;}

                        else s = 25;

                         
                        input.seek(index13_8);

                        if ( s>=0 ) return s;
                        break;

                    case 34 : 
                        int LA13_39 = input.LA(1);

                        s = -1;
                        if ( (LA13_39=='/') ) {s = 54;}

                        else if ( (LA13_39=='*') ) {s = 39;}

                        else if ( ((LA13_39 >= '\u0000' && LA13_39 <= '\t')||(LA13_39 >= '\u000B' && LA13_39 <= '\f')||(LA13_39 >= '\u000E' && LA13_39 <= '!')||LA13_39=='#'||(LA13_39 >= '%' && LA13_39 <= '&')||(LA13_39 >= '(' && LA13_39 <= ')')||(LA13_39 >= '+' && LA13_39 <= '.')||(LA13_39 >= '0' && LA13_39 <= '[')||(LA13_39 >= '^' && LA13_39 <= 'z')||LA13_39=='|'||(LA13_39 >= '~' && LA13_39 <= '\uFFFF')) ) {s = 38;}

                        else if ( (LA13_39=='{'||LA13_39=='}') ) {s = 41;}

                        else if ( (LA13_39==']') ) {s = 42;}

                        else if ( (LA13_39=='$') ) {s = 43;}

                        else if ( (LA13_39=='\"'||LA13_39=='\''||LA13_39=='\\') ) {s = 45;}

                        else s = 55;

                        if ( s>=0 ) return s;
                        break;

                    case 35 : 
                        int LA13_31 = input.LA(1);

                        s = -1;
                        if ( ((LA13_31 >= '\u0000' && LA13_31 <= '\t')||(LA13_31 >= '\u000B' && LA13_31 <= '\f')||(LA13_31 >= '\u000E' && LA13_31 <= '!')||LA13_31=='#'||(LA13_31 >= '%' && LA13_31 <= '&')||(LA13_31 >= '(' && LA13_31 <= '.')||(LA13_31 >= '0' && LA13_31 <= '[')||(LA13_31 >= '^' && LA13_31 <= 'z')||LA13_31=='|'||(LA13_31 >= '~' && LA13_31 <= '\uFFFF')) ) {s = 31;}

                        else if ( (LA13_31=='{'||LA13_31=='}') ) {s = 33;}

                        else if ( (LA13_31==']') ) {s = 34;}

                        else if ( (LA13_31=='$') ) {s = 35;}

                        else if ( (LA13_31=='/') ) {s = 36;}

                        else if ( (LA13_31=='\"'||LA13_31=='\''||LA13_31=='\\') ) {s = 37;}

                        else s = 48;

                        if ( s>=0 ) return s;
                        break;

                    case 36 : 
                        int LA13_36 = input.LA(1);

                        s = -1;
                        if ( ((LA13_36 >= '\u0000' && LA13_36 <= '\t')||(LA13_36 >= '\u000B' && LA13_36 <= '\f')||(LA13_36 >= '\u000E' && LA13_36 <= '!')||LA13_36=='#'||(LA13_36 >= '%' && LA13_36 <= '&')||(LA13_36 >= '(' && LA13_36 <= '.')||(LA13_36 >= '0' && LA13_36 <= '[')||(LA13_36 >= '^' && LA13_36 <= 'z')||LA13_36=='|'||(LA13_36 >= '~' && LA13_36 <= '\uFFFF')) ) {s = 31;}

                        else if ( (LA13_36=='{'||LA13_36=='}') ) {s = 33;}

                        else if ( (LA13_36==']') ) {s = 34;}

                        else if ( (LA13_36=='$') ) {s = 35;}

                        else if ( (LA13_36=='/') ) {s = 36;}

                        else if ( (LA13_36=='\"'||LA13_36=='\''||LA13_36=='\\') ) {s = 37;}

                        else s = 52;

                        if ( s>=0 ) return s;
                        break;

                    case 37 : 
                        int LA13_33 = input.LA(1);

                        s = -1;
                        if ( ((LA13_33 >= '\u0000' && LA13_33 <= '\t')||(LA13_33 >= '\u000B' && LA13_33 <= '\f')||(LA13_33 >= '\u000E' && LA13_33 <= '!')||LA13_33=='#'||(LA13_33 >= '%' && LA13_33 <= '&')||(LA13_33 >= '(' && LA13_33 <= '.')||(LA13_33 >= '0' && LA13_33 <= '[')||(LA13_33 >= '^' && LA13_33 <= 'z')||LA13_33=='|'||(LA13_33 >= '~' && LA13_33 <= '\uFFFF')) ) {s = 31;}

                        else if ( (LA13_33=='{'||LA13_33=='}') ) {s = 33;}

                        else if ( (LA13_33==']') ) {s = 34;}

                        else if ( (LA13_33=='$') ) {s = 35;}

                        else if ( (LA13_33=='/') ) {s = 36;}

                        else if ( (LA13_33=='\"'||LA13_33=='\''||LA13_33=='\\') ) {s = 37;}

                        else s = 49;

                        if ( s>=0 ) return s;
                        break;

                    case 38 : 
                        int LA13_0 = input.LA(1);

                        s = -1;
                        if ( (LA13_0=='$') ) {s = 1;}

                        else if ( (LA13_0=='\'') ) {s = 2;}

                        else if ( (LA13_0=='\"') ) {s = 3;}

                        else if ( (LA13_0=='/') ) {s = 4;}

                        else if ( (LA13_0=='\\') ) {s = 5;}

                        else if ( ((LA13_0 >= '\u0000' && LA13_0 <= '\t')||(LA13_0 >= '\u000B' && LA13_0 <= '\f')||(LA13_0 >= '\u000E' && LA13_0 <= '!')||LA13_0=='#'||(LA13_0 >= '%' && LA13_0 <= '&')||(LA13_0 >= '(' && LA13_0 <= '.')||(LA13_0 >= '0' && LA13_0 <= '[')||(LA13_0 >= '^' && LA13_0 <= 'z')||LA13_0=='|'||(LA13_0 >= '~' && LA13_0 <= '\uFFFF')) ) {s = 6;}

                        else if ( (LA13_0=='{'||LA13_0=='}') ) {s = 7;}

                        else if ( (LA13_0==']') ) {s = 8;}

                        else if ( (LA13_0=='\r') ) {s = 9;}

                        else if ( (LA13_0=='\n') ) {s = 10;}

                        if ( s>=0 ) return s;
                        break;

                    case 39 : 
                        int LA13_34 = input.LA(1);

                        s = -1;
                        if ( ((LA13_34 >= '\u0000' && LA13_34 <= '\t')||(LA13_34 >= '\u000B' && LA13_34 <= '\f')||(LA13_34 >= '\u000E' && LA13_34 <= '!')||LA13_34=='#'||(LA13_34 >= '%' && LA13_34 <= '&')||(LA13_34 >= '(' && LA13_34 <= '.')||(LA13_34 >= '0' && LA13_34 <= '[')||(LA13_34 >= '^' && LA13_34 <= 'z')||LA13_34=='|'||(LA13_34 >= '~' && LA13_34 <= '\uFFFF')) ) {s = 31;}

                        else if ( (LA13_34=='{'||LA13_34=='}') ) {s = 33;}

                        else if ( (LA13_34==']') ) {s = 34;}

                        else if ( (LA13_34=='$') ) {s = 35;}

                        else if ( (LA13_34=='/') ) {s = 36;}

                        else if ( (LA13_34=='\"'||LA13_34=='\''||LA13_34=='\\') ) {s = 37;}

                        else s = 50;

                        if ( s>=0 ) return s;
                        break;

                    case 40 : 
                        int LA13_7 = input.LA(1);

                         
                        int index13_7 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((LA13_7 >= '\u0000' && LA13_7 <= '\t')||(LA13_7 >= '\u000B' && LA13_7 <= '\f')||(LA13_7 >= '\u000E' && LA13_7 <= '!')||(LA13_7 >= '#' && LA13_7 <= '&')||(LA13_7 >= '(' && LA13_7 <= '[')||(LA13_7 >= ']' && LA13_7 <= '\uFFFF')) && ((!getLexer().getMode().equals(ANTLRHighlighterMode.ACTION)))) {s = 24;}

                        else s = 23;

                         
                        input.seek(index13_7);

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

}