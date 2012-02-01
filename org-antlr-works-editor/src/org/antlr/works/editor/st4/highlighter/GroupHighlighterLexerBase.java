// $ANTLR 3.4 GroupHighlighterLexerBase.g 2012-01-31 21:39:24
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
public class GroupHighlighterLexerBase extends Lexer {
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

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public GroupHighlighterLexerBase() {} 
    public GroupHighlighterLexerBase(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public GroupHighlighterLexerBase(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "GroupHighlighterLexerBase.g"; }

    // $ANTLR start "PARAMETER_DEFINITION"
    public final void mPARAMETER_DEFINITION() throws RecognitionException {
        try {
            // GroupHighlighterLexerBase.g:27:31: ()
            // GroupHighlighterLexerBase.g:27:33: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PARAMETER_DEFINITION"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:29:8: ( '(' )
            // GroupHighlighterLexerBase.g:29:10: '('
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
            // GroupHighlighterLexerBase.g:30:8: ( ')' )
            // GroupHighlighterLexerBase.g:30:10: ')'
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

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:31:7: ( ',' )
            // GroupHighlighterLexerBase.g:31:9: ','
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

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:32:5: ( '.' )
            // GroupHighlighterLexerBase.g:32:7: '.'
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

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:33:7: ( ':' )
            // GroupHighlighterLexerBase.g:33:9: ':'
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

    // $ANTLR start "DEFINED"
    public final void mDEFINED() throws RecognitionException {
        try {
            int _type = DEFINED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:34:9: ( '::=' )
            // GroupHighlighterLexerBase.g:34:11: '::='
            {
            match("::="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DEFINED"

    // $ANTLR start "EQUALS"
    public final void mEQUALS() throws RecognitionException {
        try {
            int _type = EQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:35:8: ( '=' )
            // GroupHighlighterLexerBase.g:35:10: '='
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

    // $ANTLR start "AT"
    public final void mAT() throws RecognitionException {
        try {
            int _type = AT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:36:4: ( '@' )
            // GroupHighlighterLexerBase.g:36:6: '@'
            {
            match('@'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AT"

    // $ANTLR start "LBRACK"
    public final void mLBRACK() throws RecognitionException {
        try {
            int _type = LBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:37:8: ( '[' )
            // GroupHighlighterLexerBase.g:37:10: '['
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
            // GroupHighlighterLexerBase.g:38:8: ( ']' )
            // GroupHighlighterLexerBase.g:38:10: ']'
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

    // $ANTLR start "LBRACE"
    public final void mLBRACE() throws RecognitionException {
        try {
            int _type = LBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:39:8: ( '{' )
            // GroupHighlighterLexerBase.g:39:10: '{'
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
            // GroupHighlighterLexerBase.g:40:8: ( '}' )
            // GroupHighlighterLexerBase.g:40:10: '}'
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

    // $ANTLR start "QUOTE"
    public final void mQUOTE() throws RecognitionException {
        try {
            int _type = QUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:41:7: ( '\"' )
            // GroupHighlighterLexerBase.g:41:9: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUOTE"

    // $ANTLR start "BEGIN_BIGSTRING"
    public final void mBEGIN_BIGSTRING() throws RecognitionException {
        try {
            int _type = BEGIN_BIGSTRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:44:2: ( '<<' )
            // GroupHighlighterLexerBase.g:44:4: '<<'
            {
            match("<<"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BEGIN_BIGSTRING"

    // $ANTLR start "END_BIGSTRING"
    public final void mEND_BIGSTRING() throws RecognitionException {
        try {
            int _type = END_BIGSTRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:48:2: ( '>>' )
            // GroupHighlighterLexerBase.g:48:4: '>>'
            {
            match(">>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "END_BIGSTRING"

    // $ANTLR start "BEGIN_BIGSTRINGLINE"
    public final void mBEGIN_BIGSTRINGLINE() throws RecognitionException {
        try {
            int _type = BEGIN_BIGSTRINGLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:52:2: ( '<%' )
            // GroupHighlighterLexerBase.g:52:4: '<%'
            {
            match("<%"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BEGIN_BIGSTRINGLINE"

    // $ANTLR start "END_BIGSTRINGLINE"
    public final void mEND_BIGSTRINGLINE() throws RecognitionException {
        try {
            int _type = END_BIGSTRINGLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:56:2: ( '%>' )
            // GroupHighlighterLexerBase.g:56:4: '%>'
            {
            match("%>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "END_BIGSTRINGLINE"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:59:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' )* )
            // GroupHighlighterLexerBase.g:59:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // GroupHighlighterLexerBase.g:59:30: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='-'||(LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // GroupHighlighterLexerBase.g:
            	    {
            	    if ( input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
    // $ANTLR end "ID"

    // $ANTLR start "LEGACY_DELIMITERS"
    public final void mLEGACY_DELIMITERS() throws RecognitionException {
        try {
            // GroupHighlighterLexerBase.g:63:19: ()
            // GroupHighlighterLexerBase.g:63:21: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LEGACY_DELIMITERS"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:66:2: ( '//' ( options {greedy=true; } : '!delimiters' ( ' ' | '\\t' ) | (~ ( '\\r' | '\\n' ) )* ) )
            // GroupHighlighterLexerBase.g:66:4: '//' ( options {greedy=true; } : '!delimiters' ( ' ' | '\\t' ) | (~ ( '\\r' | '\\n' ) )* )
            {
            match("//"); 



            // GroupHighlighterLexerBase.g:67:3: ( options {greedy=true; } : '!delimiters' ( ' ' | '\\t' ) | (~ ( '\\r' | '\\n' ) )* )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='!') ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1=='d') ) {
                    int LA3_3 = input.LA(3);

                    if ( (LA3_3=='e') ) {
                        int LA3_4 = input.LA(4);

                        if ( (LA3_4=='l') ) {
                            int LA3_5 = input.LA(5);

                            if ( (LA3_5=='i') ) {
                                int LA3_6 = input.LA(6);

                                if ( (LA3_6=='m') ) {
                                    int LA3_7 = input.LA(7);

                                    if ( (LA3_7=='i') ) {
                                        int LA3_8 = input.LA(8);

                                        if ( (LA3_8=='t') ) {
                                            int LA3_9 = input.LA(9);

                                            if ( (LA3_9=='e') ) {
                                                int LA3_10 = input.LA(10);

                                                if ( (LA3_10=='r') ) {
                                                    int LA3_11 = input.LA(11);

                                                    if ( (LA3_11=='s') ) {
                                                        int LA3_12 = input.LA(12);

                                                        if ( (LA3_12=='\t'||LA3_12==' ') ) {
                                                            alt3=1;
                                                        }
                                                        else {
                                                            alt3=2;
                                                        }
                                                    }
                                                    else {
                                                        alt3=2;
                                                    }
                                                }
                                                else {
                                                    alt3=2;
                                                }
                                            }
                                            else {
                                                alt3=2;
                                            }
                                        }
                                        else {
                                            alt3=2;
                                        }
                                    }
                                    else {
                                        alt3=2;
                                    }
                                }
                                else {
                                    alt3=2;
                                }
                            }
                            else {
                                alt3=2;
                            }
                        }
                        else {
                            alt3=2;
                        }
                    }
                    else {
                        alt3=2;
                    }
                }
                else {
                    alt3=2;
                }
            }
            else {
                alt3=2;
            }
            switch (alt3) {
                case 1 :
                    // GroupHighlighterLexerBase.g:68:5: '!delimiters' ( ' ' | '\\t' )
                    {
                    match("!delimiters"); 



                    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    _type = LEGACY_DELIMITERS;

                    }
                    break;
                case 2 :
                    // GroupHighlighterLexerBase.g:69:5: (~ ( '\\r' | '\\n' ) )*
                    {
                    // GroupHighlighterLexerBase.g:69:5: (~ ( '\\r' | '\\n' ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( ((LA2_0 >= '\u0000' && LA2_0 <= '\t')||(LA2_0 >= '\u000B' && LA2_0 <= '\f')||(LA2_0 >= '\u000E' && LA2_0 <= '\uFFFF')) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // GroupHighlighterLexerBase.g:
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
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LINE_COMMENT"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:74:2: ( '/*' CONTINUE_COMMENT )
            // GroupHighlighterLexerBase.g:74:4: '/*' CONTINUE_COMMENT
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
    // $ANTLR end "COMMENT"

    // $ANTLR start "END_COMMENT"
    public final void mEND_COMMENT() throws RecognitionException {
        try {
            // GroupHighlighterLexerBase.g:77:22: ()
            // GroupHighlighterLexerBase.g:77:24: 
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
            // GroupHighlighterLexerBase.g:81:2: ( (~ ( '\\r' | '\\n' | '*' ) |{...}? => '*' )* ( '*/' |) )
            // GroupHighlighterLexerBase.g:81:4: (~ ( '\\r' | '\\n' | '*' ) |{...}? => '*' )* ( '*/' |)
            {
            // GroupHighlighterLexerBase.g:81:4: (~ ( '\\r' | '\\n' | '*' ) |{...}? => '*' )*
            loop4:
            do {
                int alt4=3;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='*') ) {
                    int LA4_1 = input.LA(2);

                    if ( (LA4_1=='/') ) {
                        int LA4_4 = input.LA(3);

                        if ( ((input.LA(2) != '/')) ) {
                            alt4=2;
                        }


                    }

                    else {
                        alt4=2;
                    }


                }
                else if ( ((LA4_0 >= '\u0000' && LA4_0 <= '\t')||(LA4_0 >= '\u000B' && LA4_0 <= '\f')||(LA4_0 >= '\u000E' && LA4_0 <= ')')||(LA4_0 >= '+' && LA4_0 <= '\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // GroupHighlighterLexerBase.g:81:6: ~ ( '\\r' | '\\n' | '*' )
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
            	    // GroupHighlighterLexerBase.g:82:5: {...}? => '*'
            	    {
            	    if ( !((input.LA(2) != '/')) ) {
            	        throw new FailedPredicateException(input, "CONTINUE_COMMENT", "input.LA(2) != '/'");
            	    }

            	    match('*'); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            // GroupHighlighterLexerBase.g:84:3: ( '*/' |)
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='*') ) {
                alt5=1;
            }
            else {
                alt5=2;
            }
            switch (alt5) {
                case 1 :
                    // GroupHighlighterLexerBase.g:84:5: '*/'
                    {
                    match("*/"); 



                    state.type = END_COMMENT;

                    }
                    break;
                case 2 :
                    // GroupHighlighterLexerBase.g:85:5: 
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

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:90:2: ( ( ' ' | '\\t' )* )
            // GroupHighlighterLexerBase.g:90:4: ( ' ' | '\\t' )*
            {
            // GroupHighlighterLexerBase.g:90:4: ( ' ' | '\\t' )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='\t'||LA6_0==' ') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // GroupHighlighterLexerBase.g:
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
            	    break loop6;
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
    // $ANTLR end "WS"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:94:2: ( ( '\\r' | '\\n' )* )
            // GroupHighlighterLexerBase.g:94:4: ( '\\r' | '\\n' )*
            {
            // GroupHighlighterLexerBase.g:94:4: ( '\\r' | '\\n' )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='\n'||LA7_0=='\r') ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // GroupHighlighterLexerBase.g:
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
            	    break loop7;
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
    // $ANTLR end "NEWLINE"

    // $ANTLR start "DELIMITER_SPEC"
    public final void mDELIMITER_SPEC() throws RecognitionException {
        try {
            // GroupHighlighterLexerBase.g:99:2: ( '\"' (~ ( '\\n' | '\\r' | '\"' ) )* ( '\"' )? )
            // GroupHighlighterLexerBase.g:99:4: '\"' (~ ( '\\n' | '\\r' | '\"' ) )* ( '\"' )?
            {
            match('\"'); 

            // GroupHighlighterLexerBase.g:99:8: (~ ( '\\n' | '\\r' | '\"' ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= '\u0000' && LA8_0 <= '\t')||(LA8_0 >= '\u000B' && LA8_0 <= '\f')||(LA8_0 >= '\u000E' && LA8_0 <= '!')||(LA8_0 >= '#' && LA8_0 <= '\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // GroupHighlighterLexerBase.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
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


            // GroupHighlighterLexerBase.g:99:30: ( '\"' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='\"') ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // GroupHighlighterLexerBase.g:99:30: '\"'
                    {
                    match('\"'); 

                    }
                    break;

            }


            state.type = DELIMITER_SPEC;

            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DELIMITER_SPEC"

    // $ANTLR start "ANYCHAR"
    public final void mANYCHAR() throws RecognitionException {
        try {
            int _type = ANYCHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GroupHighlighterLexerBase.g:104:2: ( . )
            // GroupHighlighterLexerBase.g:104:4: .
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
        // GroupHighlighterLexerBase.g:1:8: ( LPAREN | RPAREN | COMMA | DOT | COLON | DEFINED | EQUALS | AT | LBRACK | RBRACK | LBRACE | RBRACE | QUOTE | BEGIN_BIGSTRING | END_BIGSTRING | BEGIN_BIGSTRINGLINE | END_BIGSTRINGLINE | ID | LINE_COMMENT | COMMENT | WS | NEWLINE | ANYCHAR )
        int alt10=23;
        int LA10_0 = input.LA(1);

        if ( (LA10_0=='(') ) {
            alt10=1;
        }
        else if ( (LA10_0==')') ) {
            alt10=2;
        }
        else if ( (LA10_0==',') ) {
            alt10=3;
        }
        else if ( (LA10_0=='.') ) {
            alt10=4;
        }
        else if ( (LA10_0==':') ) {
            int LA10_5 = input.LA(2);

            if ( (LA10_5==':') ) {
                alt10=6;
            }
            else {
                alt10=5;
            }
        }
        else if ( (LA10_0=='=') ) {
            alt10=7;
        }
        else if ( (LA10_0=='@') ) {
            alt10=8;
        }
        else if ( (LA10_0=='[') ) {
            alt10=9;
        }
        else if ( (LA10_0==']') ) {
            alt10=10;
        }
        else if ( (LA10_0=='{') ) {
            alt10=11;
        }
        else if ( (LA10_0=='}') ) {
            alt10=12;
        }
        else if ( (LA10_0=='\"') ) {
            alt10=13;
        }
        else if ( (LA10_0=='<') ) {
            switch ( input.LA(2) ) {
            case '<':
                {
                alt10=14;
                }
                break;
            case '%':
                {
                alt10=16;
                }
                break;
            default:
                alt10=23;
            }

        }
        else if ( (LA10_0=='>') ) {
            int LA10_14 = input.LA(2);

            if ( (LA10_14=='>') ) {
                alt10=15;
            }
            else {
                alt10=23;
            }
        }
        else if ( (LA10_0=='%') ) {
            int LA10_15 = input.LA(2);

            if ( (LA10_15=='>') ) {
                alt10=17;
            }
            else {
                alt10=23;
            }
        }
        else if ( ((LA10_0 >= 'A' && LA10_0 <= 'Z')||LA10_0=='_'||(LA10_0 >= 'a' && LA10_0 <= 'z')) ) {
            alt10=18;
        }
        else if ( (LA10_0=='/') ) {
            switch ( input.LA(2) ) {
            case '/':
                {
                alt10=19;
                }
                break;
            case '*':
                {
                alt10=20;
                }
                break;
            default:
                alt10=23;
            }

        }
        else if ( (LA10_0=='\t'||LA10_0==' ') ) {
            alt10=21;
        }
        else if ( (LA10_0=='\n'||LA10_0=='\r') ) {
            alt10=22;
        }
        else if ( ((LA10_0 >= '\u0000' && LA10_0 <= '\b')||(LA10_0 >= '\u000B' && LA10_0 <= '\f')||(LA10_0 >= '\u000E' && LA10_0 <= '\u001F')||LA10_0=='!'||(LA10_0 >= '#' && LA10_0 <= '$')||(LA10_0 >= '&' && LA10_0 <= '\'')||(LA10_0 >= '*' && LA10_0 <= '+')||LA10_0=='-'||(LA10_0 >= '0' && LA10_0 <= '9')||LA10_0==';'||LA10_0=='?'||LA10_0=='\\'||LA10_0=='^'||LA10_0=='`'||LA10_0=='|'||(LA10_0 >= '~' && LA10_0 <= '\uFFFF')) ) {
            alt10=23;
        }
        else {
            alt10=21;
        }
        switch (alt10) {
            case 1 :
                // GroupHighlighterLexerBase.g:1:10: LPAREN
                {
                mLPAREN(); 


                }
                break;
            case 2 :
                // GroupHighlighterLexerBase.g:1:17: RPAREN
                {
                mRPAREN(); 


                }
                break;
            case 3 :
                // GroupHighlighterLexerBase.g:1:24: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 4 :
                // GroupHighlighterLexerBase.g:1:30: DOT
                {
                mDOT(); 


                }
                break;
            case 5 :
                // GroupHighlighterLexerBase.g:1:34: COLON
                {
                mCOLON(); 


                }
                break;
            case 6 :
                // GroupHighlighterLexerBase.g:1:40: DEFINED
                {
                mDEFINED(); 


                }
                break;
            case 7 :
                // GroupHighlighterLexerBase.g:1:48: EQUALS
                {
                mEQUALS(); 


                }
                break;
            case 8 :
                // GroupHighlighterLexerBase.g:1:55: AT
                {
                mAT(); 


                }
                break;
            case 9 :
                // GroupHighlighterLexerBase.g:1:58: LBRACK
                {
                mLBRACK(); 


                }
                break;
            case 10 :
                // GroupHighlighterLexerBase.g:1:65: RBRACK
                {
                mRBRACK(); 


                }
                break;
            case 11 :
                // GroupHighlighterLexerBase.g:1:72: LBRACE
                {
                mLBRACE(); 


                }
                break;
            case 12 :
                // GroupHighlighterLexerBase.g:1:79: RBRACE
                {
                mRBRACE(); 


                }
                break;
            case 13 :
                // GroupHighlighterLexerBase.g:1:86: QUOTE
                {
                mQUOTE(); 


                }
                break;
            case 14 :
                // GroupHighlighterLexerBase.g:1:92: BEGIN_BIGSTRING
                {
                mBEGIN_BIGSTRING(); 


                }
                break;
            case 15 :
                // GroupHighlighterLexerBase.g:1:108: END_BIGSTRING
                {
                mEND_BIGSTRING(); 


                }
                break;
            case 16 :
                // GroupHighlighterLexerBase.g:1:122: BEGIN_BIGSTRINGLINE
                {
                mBEGIN_BIGSTRINGLINE(); 


                }
                break;
            case 17 :
                // GroupHighlighterLexerBase.g:1:142: END_BIGSTRINGLINE
                {
                mEND_BIGSTRINGLINE(); 


                }
                break;
            case 18 :
                // GroupHighlighterLexerBase.g:1:160: ID
                {
                mID(); 


                }
                break;
            case 19 :
                // GroupHighlighterLexerBase.g:1:163: LINE_COMMENT
                {
                mLINE_COMMENT(); 


                }
                break;
            case 20 :
                // GroupHighlighterLexerBase.g:1:176: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 21 :
                // GroupHighlighterLexerBase.g:1:184: WS
                {
                mWS(); 


                }
                break;
            case 22 :
                // GroupHighlighterLexerBase.g:1:187: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 23 :
                // GroupHighlighterLexerBase.g:1:195: ANYCHAR
                {
                mANYCHAR(); 


                }
                break;

        }

    }


 

}