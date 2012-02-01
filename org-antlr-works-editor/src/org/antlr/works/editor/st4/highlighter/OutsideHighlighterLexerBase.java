// $ANTLR 3.4 OutsideHighlighterLexerBase.g 2012-01-31 21:39:24
/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.highlighter;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class OutsideHighlighterLexerBase extends Lexer {
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

    protected int getAnonymousTemplateLevel() {
        throw new UnsupportedOperationException("This operation is not supported by the base class.");
    }

    protected char getOpenDelimiter() {
        throw new UnsupportedOperationException("This operation is not supported by the base class.");
    }

    protected char getCloseDelimiter() {
        throw new UnsupportedOperationException("This operation is not supported by the base class.");
    }

    protected OutermostTemplate getOutermost() {
        throw new UnsupportedOperationException("This operation is not supported by the base class.");
    }


    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public OutsideHighlighterLexerBase() {} 
    public OutsideHighlighterLexerBase(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public OutsideHighlighterLexerBase(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "OutsideHighlighterLexerBase.g"; }

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // OutsideHighlighterLexerBase.g:48:7: ( ',' )
            // OutsideHighlighterLexerBase.g:48:9: ','
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

    // $ANTLR start "PIPE"
    public final void mPIPE() throws RecognitionException {
        try {
            int _type = PIPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // OutsideHighlighterLexerBase.g:49:6: ( '|' )
            // OutsideHighlighterLexerBase.g:49:8: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PIPE"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // OutsideHighlighterLexerBase.g:52:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+ )
            // OutsideHighlighterLexerBase.g:52:4: ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+
            {
            // OutsideHighlighterLexerBase.g:52:4: ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // OutsideHighlighterLexerBase.g:
            	    {
            	    if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // OutsideHighlighterLexerBase.g:56:2: ( ( ' ' | '\\t' )+ )
            // OutsideHighlighterLexerBase.g:56:4: ( ' ' | '\\t' )+
            {
            // OutsideHighlighterLexerBase.g:56:4: ( ' ' | '\\t' )+
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
            	    // OutsideHighlighterLexerBase.g:
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

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // OutsideHighlighterLexerBase.g:66:2: ( LDELIM '!' CONTINUE_COMMENT )
            // OutsideHighlighterLexerBase.g:66:4: LDELIM '!' CONTINUE_COMMENT
            {
            mLDELIM(); 


            match('!'); 

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
    // $ANTLR end "COMMENT"

    // $ANTLR start "END_COMMENT"
    public final void mEND_COMMENT() throws RecognitionException {
        try {
            // OutsideHighlighterLexerBase.g:69:22: ()
            // OutsideHighlighterLexerBase.g:69:24: 
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
            // OutsideHighlighterLexerBase.g:73:2: ( (~ ( '\\r' | '\\n' | '!' ) |{...}? => '!' )* ( '!' RDELIM |) )
            // OutsideHighlighterLexerBase.g:73:4: (~ ( '\\r' | '\\n' | '!' ) |{...}? => '!' )* ( '!' RDELIM |)
            {
            // OutsideHighlighterLexerBase.g:73:4: (~ ( '\\r' | '\\n' | '!' ) |{...}? => '!' )*
            loop3:
            do {
                int alt3=3;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='!') ) {
                    int LA3_1 = input.LA(2);

                    if ( (LA3_1=='!') ) {
                        int LA3_4 = input.LA(3);

                        if ( ((input.LA(2) != getCloseDelimiter())) ) {
                            alt3=2;
                        }


                    }
                    else if ( ((LA3_1 >= '\u0000' && LA3_1 <= '\t')||(LA3_1 >= '\u000B' && LA3_1 <= '\f')||(LA3_1 >= '\u000E' && LA3_1 <= ' ')||(LA3_1 >= '\"' && LA3_1 <= '[')||(LA3_1 >= ']' && LA3_1 <= '\uFFFF')) ) {
                        int LA3_5 = input.LA(3);

                        if ( ((input.LA(2) != getCloseDelimiter())) ) {
                            alt3=2;
                        }


                    }

                    else {
                        alt3=2;
                    }


                }
                else if ( ((LA3_0 >= '\u0000' && LA3_0 <= '\t')||(LA3_0 >= '\u000B' && LA3_0 <= '\f')||(LA3_0 >= '\u000E' && LA3_0 <= ' ')||(LA3_0 >= '\"' && LA3_0 <= '\uFFFF')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // OutsideHighlighterLexerBase.g:73:6: ~ ( '\\r' | '\\n' | '!' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= ' ')||(input.LA(1) >= '\"' && input.LA(1) <= '\uFFFF') ) {
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
            	    // OutsideHighlighterLexerBase.g:74:5: {...}? => '!'
            	    {
            	    if ( !((input.LA(2) != getCloseDelimiter())) ) {
            	        throw new FailedPredicateException(input, "CONTINUE_COMMENT", "input.LA(2) != getCloseDelimiter()");
            	    }

            	    match('!'); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            // OutsideHighlighterLexerBase.g:76:3: ( '!' RDELIM |)
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='!') ) {
                alt4=1;
            }
            else {
                alt4=2;
            }
            switch (alt4) {
                case 1 :
                    // OutsideHighlighterLexerBase.g:76:5: '!' RDELIM
                    {
                    match('!'); 

                    mRDELIM(); 


                    state.type = END_COMMENT;

                    }
                    break;
                case 2 :
                    // OutsideHighlighterLexerBase.g:77:5: 
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

    // $ANTLR start "ESCAPE_TAG"
    public final void mESCAPE_TAG() throws RecognitionException {
        try {
            int _type = ESCAPE_TAG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // OutsideHighlighterLexerBase.g:82:2: ( LDELIM '\\\\' ({...}? => . )* RDELIM )
            // OutsideHighlighterLexerBase.g:82:4: LDELIM '\\\\' ({...}? => . )* RDELIM
            {
            mLDELIM(); 


            match('\\'); 

            // OutsideHighlighterLexerBase.g:82:16: ({...}? => . )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0 >= '\u0000' && LA5_0 <= '[')||(LA5_0 >= ']' && LA5_0 <= '\uFFFF')) && (((input.LA(1) != EOF && input.LA(1) != getCloseDelimiter())||(input.LA(1) == getCloseDelimiter())))) {
                    int LA5_1 = input.LA(2);

                    if ( ((LA5_1 >= '\u0000' && LA5_1 <= '\uFFFF')) && ((input.LA(1) != EOF && input.LA(1) != getCloseDelimiter()))) {
                        alt5=1;
                    }


                }
                else if ( (LA5_0=='\\') && ((input.LA(1) != EOF && input.LA(1) != getCloseDelimiter()))) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // OutsideHighlighterLexerBase.g:82:17: {...}? => .
            	    {
            	    if ( !((input.LA(1) != EOF && input.LA(1) != getCloseDelimiter())) ) {
            	        throw new FailedPredicateException(input, "ESCAPE_TAG", "input.LA(1) != EOF && input.LA(1) != getCloseDelimiter()");
            	    }

            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            mRDELIM(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ESCAPE_TAG"

    // $ANTLR start "ESCAPE_CHAR"
    public final void mESCAPE_CHAR() throws RecognitionException {
        try {
            int _type = ESCAPE_CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            int c;

            // OutsideHighlighterLexerBase.g:86:2: ( '\\\\' c= . )
            // OutsideHighlighterLexerBase.g:86:4: '\\\\' c= .
            {
            match('\\'); 

            c = input.LA(1);

            matchAny(); 


            			if (c != '>' && (getOutermost() != OutermostTemplate.String || c != '"'))
            				_type = TEXT;
            		

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ESCAPE_CHAR"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // OutsideHighlighterLexerBase.g:94:2: ( ( '\\n' | '\\r' )+ )
            // OutsideHighlighterLexerBase.g:94:4: ( '\\n' | '\\r' )+
            {
            // OutsideHighlighterLexerBase.g:94:4: ( '\\n' | '\\r' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='\n'||LA6_0=='\r') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // OutsideHighlighterLexerBase.g:
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
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
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

    // $ANTLR start "TEXT"
    public final void mTEXT() throws RecognitionException {
        try {
            int _type = TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // OutsideHighlighterLexerBase.g:98:2: ( ({...}? =>~ ( '}' | '\\n' | '\\r' | '\"' | '>' | '\\\\' | '%' | 'a' .. 'z' | 'A' .. 'Z' | '_' | ' ' | '\\t' | '|' ) |{...}? => '}' |{...}? => '\"' |{...}? => '>' |{...}? => '%' )+ )
            // OutsideHighlighterLexerBase.g:98:4: ({...}? =>~ ( '}' | '\\n' | '\\r' | '\"' | '>' | '\\\\' | '%' | 'a' .. 'z' | 'A' .. 'Z' | '_' | ' ' | '\\t' | '|' ) |{...}? => '}' |{...}? => '\"' |{...}? => '>' |{...}? => '%' )+
            {
            // OutsideHighlighterLexerBase.g:98:4: ({...}? =>~ ( '}' | '\\n' | '\\r' | '\"' | '>' | '\\\\' | '%' | 'a' .. 'z' | 'A' .. 'Z' | '_' | ' ' | '\\t' | '|' ) |{...}? => '}' |{...}? => '\"' |{...}? => '>' |{...}? => '%' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=6;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0 >= '\u0000' && LA7_0 <= '\b')||(LA7_0 >= '\u000B' && LA7_0 <= '\f')||(LA7_0 >= '\u000E' && LA7_0 <= '\u001F')||LA7_0=='!'||(LA7_0 >= '#' && LA7_0 <= '$')||(LA7_0 >= '&' && LA7_0 <= '=')||(LA7_0 >= '?' && LA7_0 <= '@')||LA7_0=='['||(LA7_0 >= ']' && LA7_0 <= '^')||LA7_0=='`'||LA7_0=='{'||(LA7_0 >= '~' && LA7_0 <= '\uFFFF')) && ((input.LA(1) != getOpenDelimiter()))) {
                    alt7=1;
                }
                else if ( (LA7_0=='}') && ((getAnonymousTemplateLevel() == 0))) {
                    alt7=2;
                }
                else if ( (LA7_0=='\"') && ((getOutermost() != OutermostTemplate.String))) {
                    alt7=3;
                }
                else if ( (LA7_0=='>') && ((getOutermost() != OutermostTemplate.BigString || input.LA(2) != '>'))) {
                    alt7=4;
                }
                else if ( (LA7_0=='%') && ((getOutermost() != OutermostTemplate.BigStringLine || input.LA(2) != '>'))) {
                    alt7=5;
                }


                switch (alt7) {
            	case 1 :
            	    // OutsideHighlighterLexerBase.g:98:6: {...}? =>~ ( '}' | '\\n' | '\\r' | '\"' | '>' | '\\\\' | '%' | 'a' .. 'z' | 'A' .. 'Z' | '_' | ' ' | '\\t' | '|' )
            	    {
            	    if ( !((input.LA(1) != getOpenDelimiter())) ) {
            	        throw new FailedPredicateException(input, "TEXT", "input.LA(1) != getOpenDelimiter()");
            	    }

            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\b')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\u001F')||input.LA(1)=='!'||(input.LA(1) >= '#' && input.LA(1) <= '$')||(input.LA(1) >= '&' && input.LA(1) <= '=')||(input.LA(1) >= '?' && input.LA(1) <= '@')||input.LA(1)=='['||(input.LA(1) >= ']' && input.LA(1) <= '^')||input.LA(1)=='`'||input.LA(1)=='{'||(input.LA(1) >= '~' && input.LA(1) <= '\uFFFF') ) {
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
            	    // OutsideHighlighterLexerBase.g:99:5: {...}? => '}'
            	    {
            	    if ( !((getAnonymousTemplateLevel() == 0)) ) {
            	        throw new FailedPredicateException(input, "TEXT", "getAnonymousTemplateLevel() == 0");
            	    }

            	    match('}'); 

            	    }
            	    break;
            	case 3 :
            	    // OutsideHighlighterLexerBase.g:100:5: {...}? => '\"'
            	    {
            	    if ( !((getOutermost() != OutermostTemplate.String)) ) {
            	        throw new FailedPredicateException(input, "TEXT", "getOutermost() != OutermostTemplate.String");
            	    }

            	    match('\"'); 

            	    }
            	    break;
            	case 4 :
            	    // OutsideHighlighterLexerBase.g:101:5: {...}? => '>'
            	    {
            	    if ( !((getOutermost() != OutermostTemplate.BigString || input.LA(2) != '>')) ) {
            	        throw new FailedPredicateException(input, "TEXT", "getOutermost() != OutermostTemplate.BigString || input.LA(2) != '>'");
            	    }

            	    match('>'); 

            	    }
            	    break;
            	case 5 :
            	    // OutsideHighlighterLexerBase.g:102:5: {...}? => '%'
            	    {
            	    if ( !((getOutermost() != OutermostTemplate.BigStringLine || input.LA(2) != '>')) ) {
            	        throw new FailedPredicateException(input, "TEXT", "getOutermost() != OutermostTemplate.BigStringLine || input.LA(2) != '>'");
            	    }

            	    match('%'); 

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TEXT"

    // $ANTLR start "LDELIM"
    public final void mLDELIM() throws RecognitionException {
        try {
            int _type = LDELIM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // OutsideHighlighterLexerBase.g:106:8: ({...}? =>~ '\\\\' )
            // OutsideHighlighterLexerBase.g:106:10: {...}? =>~ '\\\\'
            {
            if ( !((input.LA(1) == getOpenDelimiter())) ) {
                throw new FailedPredicateException(input, "LDELIM", "input.LA(1) == getOpenDelimiter()");
            }

            if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LDELIM"

    // $ANTLR start "RDELIM"
    public final void mRDELIM() throws RecognitionException {
        try {
            // OutsideHighlighterLexerBase.g:109:8: ({...}? =>~ '\\\\' )
            // OutsideHighlighterLexerBase.g:109:10: {...}? =>~ '\\\\'
            {
            if ( !((input.LA(1) == getCloseDelimiter())) ) {
                throw new FailedPredicateException(input, "RDELIM", "input.LA(1) == getCloseDelimiter()");
            }

            if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RDELIM"

    public void mTokens() throws RecognitionException {
        // OutsideHighlighterLexerBase.g:1:8: ( COMMA | PIPE | ID | WS | COMMENT | ESCAPE_TAG | ESCAPE_CHAR | NEWLINE | TEXT | LDELIM )
        int alt8=10;
        alt8 = dfa8.predict(input);
        switch (alt8) {
            case 1 :
                // OutsideHighlighterLexerBase.g:1:10: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 2 :
                // OutsideHighlighterLexerBase.g:1:16: PIPE
                {
                mPIPE(); 


                }
                break;
            case 3 :
                // OutsideHighlighterLexerBase.g:1:21: ID
                {
                mID(); 


                }
                break;
            case 4 :
                // OutsideHighlighterLexerBase.g:1:24: WS
                {
                mWS(); 


                }
                break;
            case 5 :
                // OutsideHighlighterLexerBase.g:1:27: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 6 :
                // OutsideHighlighterLexerBase.g:1:35: ESCAPE_TAG
                {
                mESCAPE_TAG(); 


                }
                break;
            case 7 :
                // OutsideHighlighterLexerBase.g:1:46: ESCAPE_CHAR
                {
                mESCAPE_CHAR(); 


                }
                break;
            case 8 :
                // OutsideHighlighterLexerBase.g:1:58: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 9 :
                // OutsideHighlighterLexerBase.g:1:66: TEXT
                {
                mTEXT(); 


                }
                break;
            case 10 :
                // OutsideHighlighterLexerBase.g:1:71: LDELIM
                {
                mLDELIM(); 


                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA8_eotS =
        "\1\uffff\1\14\1\24\1\27\1\33\1\37\1\uffff\1\43\1\53\1\63\1\73\1"+
        "\103\102\uffff";
    static final String DFA8_eofS =
        "\116\uffff";
    static final String DFA8_minS =
        "\2\0\2\41\1\11\1\12\1\uffff\7\0\6\uffff\1\0\2\uffff\1\0\3\uffff"+
        "\1\0\3\uffff\1\0\3\uffff\2\0\6\uffff\2\0\6\uffff\2\0\6\uffff\2\0"+
        "\6\uffff\2\0\11\uffff";
    static final String DFA8_maxS =
        "\2\uffff\1\134\1\172\2\134\1\uffff\5\uffff\2\0\6\uffff\1\0\2\uffff"+
        "\1\0\3\uffff\1\0\3\uffff\1\0\3\uffff\2\0\6\uffff\2\0\6\uffff\2\0"+
        "\6\uffff\2\0\6\uffff\2\0\11\uffff";
    static final String DFA8_acceptS =
        "\6\uffff\1\7\7\uffff\1\6\1\11\5\uffff\1\5\2\uffff\1\3\3\uffff\1"+
        "\4\5\uffff\1\10\13\uffff\1\11\7\uffff\1\11\7\uffff\1\11\7\uffff"+
        "\1\11\4\uffff\1\1\1\12\1\2";
    static final String DFA8_specialS =
        "\1\0\1\1\1\2\1\3\1\4\1\5\1\uffff\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\6\uffff\1\15\2\uffff\1\16\3\uffff\1\17\3\uffff\1\20\3\uffff\1\21"+
        "\1\22\6\uffff\1\23\1\24\6\uffff\1\25\1\26\6\uffff\1\27\1\30\6\uffff"+
        "\1\31\1\32\11\uffff}>";
    static final String[] DFA8_transitionS = {
            "\11\7\1\4\1\5\2\7\1\5\22\7\1\4\1\7\1\11\2\7\1\13\6\7\1\1\21"+
            "\7\1\12\2\7\32\3\1\7\1\6\2\7\1\3\1\7\32\3\1\7\1\2\1\10\uff82"+
            "\7",
            "\11\17\2\uffff\2\17\1\uffff\22\17\1\uffff\1\15\37\17\32\uffff"+
            "\1\17\1\16\2\17\1\uffff\1\17\32\uffff\1\17\1\uffff\uff83\17",
            "\1\25\72\uffff\1\16",
            "\1\25\37\uffff\32\30\1\uffff\1\16\2\uffff\1\30\1\uffff\32\30",
            "\1\34\26\uffff\1\34\1\25\72\uffff\1\16",
            "\1\42\2\uffff\1\42\23\uffff\1\25\72\uffff\1\16",
            "",
            "\11\17\2\uffff\2\17\1\uffff\22\17\1\uffff\1\44\37\17\32\uffff"+
            "\1\17\1\16\2\17\1\uffff\1\17\32\uffff\1\17\1\uffff\uff83\17",
            "\11\56\2\uffff\2\56\1\uffff\22\56\1\uffff\1\54\37\56\32\uffff"+
            "\1\56\1\16\2\56\1\uffff\1\56\32\uffff\1\56\1\uffff\uff83\56",
            "\11\66\2\uffff\2\66\1\uffff\22\66\1\uffff\1\64\37\66\32\uffff"+
            "\1\66\1\16\2\66\1\uffff\1\66\32\uffff\1\66\1\uffff\uff83\66",
            "\11\76\2\uffff\2\76\1\uffff\22\76\1\uffff\1\74\37\76\32\uffff"+
            "\1\76\1\16\2\76\1\uffff\1\76\32\uffff\1\76\1\uffff\uff83\76",
            "\11\106\2\uffff\2\106\1\uffff\22\106\1\uffff\1\104\37\106\32"+
            "\uffff\1\106\1\16\2\106\1\uffff\1\106\32\uffff\1\106\1\uffff"+
            "\uff83\106",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( COMMA | PIPE | ID | WS | COMMENT | ESCAPE_TAG | ESCAPE_CHAR | NEWLINE | TEXT | LDELIM );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA8_0 = input.LA(1);

                         
                        int index8_0 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA8_0==',') ) {s = 1;}

                        else if ( (LA8_0=='|') ) {s = 2;}

                        else if ( ((LA8_0 >= 'A' && LA8_0 <= 'Z')||LA8_0=='_'||(LA8_0 >= 'a' && LA8_0 <= 'z')) ) {s = 3;}

                        else if ( (LA8_0=='\t'||LA8_0==' ') ) {s = 4;}

                        else if ( (LA8_0=='\n'||LA8_0=='\r') ) {s = 5;}

                        else if ( (LA8_0=='\\') ) {s = 6;}

                        else if ( ((LA8_0 >= '\u0000' && LA8_0 <= '\b')||(LA8_0 >= '\u000B' && LA8_0 <= '\f')||(LA8_0 >= '\u000E' && LA8_0 <= '\u001F')||LA8_0=='!'||(LA8_0 >= '#' && LA8_0 <= '$')||(LA8_0 >= '&' && LA8_0 <= '+')||(LA8_0 >= '-' && LA8_0 <= '=')||(LA8_0 >= '?' && LA8_0 <= '@')||LA8_0=='['||(LA8_0 >= ']' && LA8_0 <= '^')||LA8_0=='`'||LA8_0=='{'||(LA8_0 >= '~' && LA8_0 <= '\uFFFF')) && (((input.LA(1) != getOpenDelimiter())||(input.LA(1) == getOpenDelimiter())))) {s = 7;}

                        else if ( (LA8_0=='}') && (((input.LA(1) == getOpenDelimiter())||(getAnonymousTemplateLevel() == 0)))) {s = 8;}

                        else if ( (LA8_0=='\"') && (((input.LA(1) == getOpenDelimiter())||(getOutermost() != OutermostTemplate.String)))) {s = 9;}

                        else if ( (LA8_0=='>') && (((input.LA(1) == getOpenDelimiter())||(getOutermost() != OutermostTemplate.BigString || input.LA(2) != '>')))) {s = 10;}

                        else if ( (LA8_0=='%') && (((getOutermost() != OutermostTemplate.BigStringLine || input.LA(2) != '>')||(input.LA(1) == getOpenDelimiter())))) {s = 11;}

                         
                        input.seek(index8_0);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA8_1 = input.LA(1);

                         
                        int index8_1 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA8_1=='!') && (((input.LA(1) != getOpenDelimiter())||(input.LA(1) == getOpenDelimiter())))) {s = 13;}

                        else if ( (LA8_1=='\\') && ((input.LA(1) == getOpenDelimiter()))) {s = 14;}

                        else if ( ((LA8_1 >= '\u0000' && LA8_1 <= '\b')||(LA8_1 >= '\u000B' && LA8_1 <= '\f')||(LA8_1 >= '\u000E' && LA8_1 <= '\u001F')||(LA8_1 >= '\"' && LA8_1 <= '@')||LA8_1=='['||(LA8_1 >= ']' && LA8_1 <= '^')||LA8_1=='`'||LA8_1=='{'||(LA8_1 >= '}' && LA8_1 <= '\uFFFF')) && ((input.LA(1) != getOpenDelimiter()))) {s = 15;}

                        else s = 12;

                         
                        input.seek(index8_1);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA8_2 = input.LA(1);

                         
                        int index8_2 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA8_2=='!') && ((input.LA(1) == getOpenDelimiter()))) {s = 21;}

                        else if ( (LA8_2=='\\') && ((input.LA(1) == getOpenDelimiter()))) {s = 14;}

                        else s = 20;

                         
                        input.seek(index8_2);

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA8_3 = input.LA(1);

                         
                        int index8_3 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((LA8_3 >= 'A' && LA8_3 <= 'Z')||LA8_3=='_'||(LA8_3 >= 'a' && LA8_3 <= 'z')) ) {s = 24;}

                        else if ( (LA8_3=='!') && ((input.LA(1) == getOpenDelimiter()))) {s = 21;}

                        else if ( (LA8_3=='\\') && ((input.LA(1) == getOpenDelimiter()))) {s = 14;}

                        else s = 23;

                         
                        input.seek(index8_3);

                        if ( s>=0 ) return s;
                        break;

                    case 4 : 
                        int LA8_4 = input.LA(1);

                         
                        int index8_4 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA8_4=='\t'||LA8_4==' ') ) {s = 28;}

                        else if ( (LA8_4=='!') && ((input.LA(1) == getOpenDelimiter()))) {s = 21;}

                        else if ( (LA8_4=='\\') && ((input.LA(1) == getOpenDelimiter()))) {s = 14;}

                        else s = 27;

                         
                        input.seek(index8_4);

                        if ( s>=0 ) return s;
                        break;

                    case 5 : 
                        int LA8_5 = input.LA(1);

                         
                        int index8_5 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA8_5=='!') && ((input.LA(1) == getOpenDelimiter()))) {s = 21;}

                        else if ( (LA8_5=='\\') && ((input.LA(1) == getOpenDelimiter()))) {s = 14;}

                        else if ( (LA8_5=='\n'||LA8_5=='\r') ) {s = 34;}

                        else s = 31;

                         
                        input.seek(index8_5);

                        if ( s>=0 ) return s;
                        break;

                    case 6 : 
                        int LA8_7 = input.LA(1);

                         
                        int index8_7 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA8_7=='!') && (((input.LA(1) != getOpenDelimiter())||(input.LA(1) == getOpenDelimiter())))) {s = 36;}

                        else if ( (LA8_7=='\\') && ((input.LA(1) == getOpenDelimiter()))) {s = 14;}

                        else if ( ((LA8_7 >= '\u0000' && LA8_7 <= '\b')||(LA8_7 >= '\u000B' && LA8_7 <= '\f')||(LA8_7 >= '\u000E' && LA8_7 <= '\u001F')||(LA8_7 >= '\"' && LA8_7 <= '@')||LA8_7=='['||(LA8_7 >= ']' && LA8_7 <= '^')||LA8_7=='`'||LA8_7=='{'||(LA8_7 >= '}' && LA8_7 <= '\uFFFF')) && ((input.LA(1) != getOpenDelimiter()))) {s = 15;}

                        else s = 35;

                         
                        input.seek(index8_7);

                        if ( s>=0 ) return s;
                        break;

                    case 7 : 
                        int LA8_8 = input.LA(1);

                         
                        int index8_8 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA8_8=='!') && (((input.LA(1) == getOpenDelimiter())||(getAnonymousTemplateLevel() == 0)))) {s = 44;}

                        else if ( (LA8_8=='\\') && ((input.LA(1) == getOpenDelimiter()))) {s = 14;}

                        else if ( ((LA8_8 >= '\u0000' && LA8_8 <= '\b')||(LA8_8 >= '\u000B' && LA8_8 <= '\f')||(LA8_8 >= '\u000E' && LA8_8 <= '\u001F')||(LA8_8 >= '\"' && LA8_8 <= '@')||LA8_8=='['||(LA8_8 >= ']' && LA8_8 <= '^')||LA8_8=='`'||LA8_8=='{'||(LA8_8 >= '}' && LA8_8 <= '\uFFFF')) && ((getAnonymousTemplateLevel() == 0))) {s = 46;}

                        else s = 43;

                         
                        input.seek(index8_8);

                        if ( s>=0 ) return s;
                        break;

                    case 8 : 
                        int LA8_9 = input.LA(1);

                         
                        int index8_9 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA8_9=='!') && (((input.LA(1) == getOpenDelimiter())||(getOutermost() != OutermostTemplate.String)))) {s = 52;}

                        else if ( (LA8_9=='\\') && ((input.LA(1) == getOpenDelimiter()))) {s = 14;}

                        else if ( ((LA8_9 >= '\u0000' && LA8_9 <= '\b')||(LA8_9 >= '\u000B' && LA8_9 <= '\f')||(LA8_9 >= '\u000E' && LA8_9 <= '\u001F')||(LA8_9 >= '\"' && LA8_9 <= '@')||LA8_9=='['||(LA8_9 >= ']' && LA8_9 <= '^')||LA8_9=='`'||LA8_9=='{'||(LA8_9 >= '}' && LA8_9 <= '\uFFFF')) && ((getOutermost() != OutermostTemplate.String))) {s = 54;}

                        else s = 51;

                         
                        input.seek(index8_9);

                        if ( s>=0 ) return s;
                        break;

                    case 9 : 
                        int LA8_10 = input.LA(1);

                         
                        int index8_10 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA8_10=='!') && (((input.LA(1) == getOpenDelimiter())||(getOutermost() != OutermostTemplate.BigString || input.LA(2) != '>')))) {s = 60;}

                        else if ( (LA8_10=='\\') && ((input.LA(1) == getOpenDelimiter()))) {s = 14;}

                        else if ( ((LA8_10 >= '\u0000' && LA8_10 <= '\b')||(LA8_10 >= '\u000B' && LA8_10 <= '\f')||(LA8_10 >= '\u000E' && LA8_10 <= '\u001F')||(LA8_10 >= '\"' && LA8_10 <= '@')||LA8_10=='['||(LA8_10 >= ']' && LA8_10 <= '^')||LA8_10=='`'||LA8_10=='{'||(LA8_10 >= '}' && LA8_10 <= '\uFFFF')) && ((getOutermost() != OutermostTemplate.BigString || input.LA(2) != '>'))) {s = 62;}

                        else s = 59;

                         
                        input.seek(index8_10);

                        if ( s>=0 ) return s;
                        break;

                    case 10 : 
                        int LA8_11 = input.LA(1);

                         
                        int index8_11 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA8_11=='!') && (((getOutermost() != OutermostTemplate.BigStringLine || input.LA(2) != '>')||(input.LA(1) == getOpenDelimiter())))) {s = 68;}

                        else if ( (LA8_11=='\\') && ((input.LA(1) == getOpenDelimiter()))) {s = 14;}

                        else if ( ((LA8_11 >= '\u0000' && LA8_11 <= '\b')||(LA8_11 >= '\u000B' && LA8_11 <= '\f')||(LA8_11 >= '\u000E' && LA8_11 <= '\u001F')||(LA8_11 >= '\"' && LA8_11 <= '@')||LA8_11=='['||(LA8_11 >= ']' && LA8_11 <= '^')||LA8_11=='`'||LA8_11=='{'||(LA8_11 >= '}' && LA8_11 <= '\uFFFF')) && ((getOutermost() != OutermostTemplate.BigStringLine || input.LA(2) != '>'))) {s = 70;}

                        else s = 67;

                         
                        input.seek(index8_11);

                        if ( s>=0 ) return s;
                        break;

                    case 11 : 
                        int LA8_12 = input.LA(1);

                         
                        int index8_12 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!((((input.LA(1) != getOpenDelimiter())||(input.LA(1) == getOpenDelimiter()))))) ) {s = 75;}

                        else if ( ((input.LA(1) != getOpenDelimiter())) ) {s = 70;}

                        else if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 76;}

                         
                        input.seek(index8_12);

                        if ( s>=0 ) return s;
                        break;

                    case 12 : 
                        int LA8_13 = input.LA(1);

                         
                        int index8_13 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 21;}

                        else if ( ((input.LA(1) != getOpenDelimiter())) ) {s = 70;}

                         
                        input.seek(index8_13);

                        if ( s>=0 ) return s;
                        break;

                    case 13 : 
                        int LA8_20 = input.LA(1);

                         
                        int index8_20 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(1) == getOpenDelimiter())))) ) {s = 77;}

                        else if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 76;}

                         
                        input.seek(index8_20);

                        if ( s>=0 ) return s;
                        break;

                    case 14 : 
                        int LA8_23 = input.LA(1);

                         
                        int index8_23 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(1) == getOpenDelimiter())))) ) {s = 24;}

                        else if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 76;}

                         
                        input.seek(index8_23);

                        if ( s>=0 ) return s;
                        break;

                    case 15 : 
                        int LA8_27 = input.LA(1);

                         
                        int index8_27 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(1) == getOpenDelimiter())))) ) {s = 28;}

                        else if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 76;}

                         
                        input.seek(index8_27);

                        if ( s>=0 ) return s;
                        break;

                    case 16 : 
                        int LA8_31 = input.LA(1);

                         
                        int index8_31 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (!(((input.LA(1) == getOpenDelimiter())))) ) {s = 34;}

                        else if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 76;}

                         
                        input.seek(index8_31);

                        if ( s>=0 ) return s;
                        break;

                    case 17 : 
                        int LA8_35 = input.LA(1);

                         
                        int index8_35 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((input.LA(1) != getOpenDelimiter())) ) {s = 70;}

                        else if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 76;}

                         
                        input.seek(index8_35);

                        if ( s>=0 ) return s;
                        break;

                    case 18 : 
                        int LA8_36 = input.LA(1);

                         
                        int index8_36 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 21;}

                        else if ( ((input.LA(1) != getOpenDelimiter())) ) {s = 70;}

                         
                        input.seek(index8_36);

                        if ( s>=0 ) return s;
                        break;

                    case 19 : 
                        int LA8_43 = input.LA(1);

                         
                        int index8_43 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((getAnonymousTemplateLevel() == 0)) ) {s = 70;}

                        else if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 76;}

                         
                        input.seek(index8_43);

                        if ( s>=0 ) return s;
                        break;

                    case 20 : 
                        int LA8_44 = input.LA(1);

                         
                        int index8_44 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 21;}

                        else if ( ((getAnonymousTemplateLevel() == 0)) ) {s = 70;}

                         
                        input.seek(index8_44);

                        if ( s>=0 ) return s;
                        break;

                    case 21 : 
                        int LA8_51 = input.LA(1);

                         
                        int index8_51 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((getOutermost() != OutermostTemplate.String)) ) {s = 70;}

                        else if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 76;}

                         
                        input.seek(index8_51);

                        if ( s>=0 ) return s;
                        break;

                    case 22 : 
                        int LA8_52 = input.LA(1);

                         
                        int index8_52 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 21;}

                        else if ( ((getOutermost() != OutermostTemplate.String)) ) {s = 70;}

                         
                        input.seek(index8_52);

                        if ( s>=0 ) return s;
                        break;

                    case 23 : 
                        int LA8_59 = input.LA(1);

                         
                        int index8_59 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((getOutermost() != OutermostTemplate.BigString || input.LA(2) != '>')) ) {s = 70;}

                        else if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 76;}

                         
                        input.seek(index8_59);

                        if ( s>=0 ) return s;
                        break;

                    case 24 : 
                        int LA8_60 = input.LA(1);

                         
                        int index8_60 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 21;}

                        else if ( ((getOutermost() != OutermostTemplate.BigString || input.LA(2) != '>')) ) {s = 70;}

                         
                        input.seek(index8_60);

                        if ( s>=0 ) return s;
                        break;

                    case 25 : 
                        int LA8_67 = input.LA(1);

                         
                        int index8_67 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((getOutermost() != OutermostTemplate.BigStringLine || input.LA(2) != '>')) ) {s = 70;}

                        else if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 76;}

                         
                        input.seek(index8_67);

                        if ( s>=0 ) return s;
                        break;

                    case 26 : 
                        int LA8_68 = input.LA(1);

                         
                        int index8_68 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((input.LA(1) == getOpenDelimiter())) ) {s = 21;}

                        else if ( ((getOutermost() != OutermostTemplate.BigStringLine || input.LA(2) != '>')) ) {s = 70;}

                         
                        input.seek(index8_68);

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 8, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

}