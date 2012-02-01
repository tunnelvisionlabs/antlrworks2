// $ANTLR 3.4 GrammarHighlighterLexerBase.g 2012-01-31 21:39:22
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
public class GrammarHighlighterLexerBase extends Lexer {
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

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public GrammarHighlighterLexerBase() {} 
    public GrammarHighlighterLexerBase(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public GrammarHighlighterLexerBase(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "GrammarHighlighterLexerBase.g"; }

    // $ANTLR start "ParserRule"
    public final void mParserRule() throws RecognitionException {
        try {
            // GrammarHighlighterLexerBase.g:32:21: ()
            // GrammarHighlighterLexerBase.g:32:23: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ParserRule"

    // $ANTLR start "LexerRule"
    public final void mLexerRule() throws RecognitionException {
        try {
            // GrammarHighlighterLexerBase.g:33:20: ()
            // GrammarHighlighterLexerBase.g:33:22: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LexerRule"

    // $ANTLR start "Keyword"
    public final void mKeyword() throws RecognitionException {
        try {
            // GrammarHighlighterLexerBase.g:34:18: ()
            // GrammarHighlighterLexerBase.g:34:20: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Keyword"

    // $ANTLR start "ACTION"
    public final void mACTION() throws RecognitionException {
        try {
            // GrammarHighlighterLexerBase.g:35:17: ()
            // GrammarHighlighterLexerBase.g:35:19: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ACTION"

    // $ANTLR start "ARG_ACTION"
    public final void mARG_ACTION() throws RecognitionException {
        try {
            // GrammarHighlighterLexerBase.g:36:21: ()
            // GrammarHighlighterLexerBase.g:36:23: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ARG_ACTION"

    // $ANTLR start "LABEL"
    public final void mLABEL() throws RecognitionException {
        try {
            // GrammarHighlighterLexerBase.g:37:16: ()
            // GrammarHighlighterLexerBase.g:37:18: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LABEL"

    // $ANTLR start "ValidGrammarOption"
    public final void mValidGrammarOption() throws RecognitionException {
        try {
            // GrammarHighlighterLexerBase.g:39:29: ()
            // GrammarHighlighterLexerBase.g:39:31: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ValidGrammarOption"

    // $ANTLR start "InvalidGrammarOption"
    public final void mInvalidGrammarOption() throws RecognitionException {
        try {
            // GrammarHighlighterLexerBase.g:40:31: ()
            // GrammarHighlighterLexerBase.g:40:33: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "InvalidGrammarOption"

    // $ANTLR start "OptionValue"
    public final void mOptionValue() throws RecognitionException {
        try {
            // GrammarHighlighterLexerBase.g:41:22: ()
            // GrammarHighlighterLexerBase.g:41:24: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OptionValue"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:44:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // GrammarHighlighterLexerBase.g:44:4: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // GrammarHighlighterLexerBase.g:45:3: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // GrammarHighlighterLexerBase.g:
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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "DIRECTIVE"
    public final void mDIRECTIVE() throws RecognitionException {
        try {
            int _type = DIRECTIVE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:49:2: ( '@' IDENTIFIER )
            // GrammarHighlighterLexerBase.g:49:4: '@' IDENTIFIER
            {
            match('@'); 

            mIDENTIFIER(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIRECTIVE"

    // $ANTLR start "REFERENCE"
    public final void mREFERENCE() throws RecognitionException {
        try {
            int _type = REFERENCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:53:2: ( '$' IDENTIFIER )
            // GrammarHighlighterLexerBase.g:53:4: '$' IDENTIFIER
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
    // $ANTLR end "REFERENCE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:57:2: ( ( ' ' | '\\t' )+ )
            // GrammarHighlighterLexerBase.g:57:4: ( ' ' | '\\t' )+
            {
            // GrammarHighlighterLexerBase.g:57:4: ( ' ' | '\\t' )+
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
            	    // GrammarHighlighterLexerBase.g:
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
            // GrammarHighlighterLexerBase.g:63:2: ( ( '\\r' )? '\\n' )
            // GrammarHighlighterLexerBase.g:63:4: ( '\\r' )? '\\n'
            {
            // GrammarHighlighterLexerBase.g:63:4: ( '\\r' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\r') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // GrammarHighlighterLexerBase.g:63:4: '\\r'
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

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:67:2: ( '//' (~ ( '\\r' | '\\n' ) )* )
            // GrammarHighlighterLexerBase.g:67:4: '//' (~ ( '\\r' | '\\n' ) )*
            {
            match("//"); 



            // GrammarHighlighterLexerBase.g:67:9: (~ ( '\\r' | '\\n' ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '\u0000' && LA4_0 <= '\t')||(LA4_0 >= '\u000B' && LA4_0 <= '\f')||(LA4_0 >= '\u000E' && LA4_0 <= '\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // GrammarHighlighterLexerBase.g:
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
            	    break loop4;
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
    // $ANTLR end "COMMENT"

    // $ANTLR start "ML_COMMENT"
    public final void mML_COMMENT() throws RecognitionException {
        try {
            int _type = ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:71:2: ( '/*' CONTINUE_COMMENT )
            // GrammarHighlighterLexerBase.g:71:4: '/*' CONTINUE_COMMENT
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
    // $ANTLR end "ML_COMMENT"

    // $ANTLR start "END_COMMENT"
    public final void mEND_COMMENT() throws RecognitionException {
        try {
            // GrammarHighlighterLexerBase.g:74:22: ()
            // GrammarHighlighterLexerBase.g:74:24: 
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
            // GrammarHighlighterLexerBase.g:78:2: ( (~ ( '\\r' | '\\n' | '*' ) |{...}? => '*' )* ( '*/' |) )
            // GrammarHighlighterLexerBase.g:78:4: (~ ( '\\r' | '\\n' | '*' ) |{...}? => '*' )* ( '*/' |)
            {
            // GrammarHighlighterLexerBase.g:78:4: (~ ( '\\r' | '\\n' | '*' ) |{...}? => '*' )*
            loop5:
            do {
                int alt5=3;
                int LA5_0 = input.LA(1);

                if ( (LA5_0=='*') ) {
                    int LA5_1 = input.LA(2);

                    if ( (LA5_1=='/') ) {
                        int LA5_4 = input.LA(3);

                        if ( ((input.LA(2) != '/')) ) {
                            alt5=2;
                        }


                    }

                    else {
                        alt5=2;
                    }


                }
                else if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\t')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= ')')||(LA5_0 >= '+' && LA5_0 <= '\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // GrammarHighlighterLexerBase.g:78:6: ~ ( '\\r' | '\\n' | '*' )
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
            	    // GrammarHighlighterLexerBase.g:79:5: {...}? => '*'
            	    {
            	    if ( !((input.LA(2) != '/')) ) {
            	        throw new FailedPredicateException(input, "CONTINUE_COMMENT", "input.LA(2) != '/'");
            	    }

            	    match('*'); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            // GrammarHighlighterLexerBase.g:81:3: ( '*/' |)
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='*') ) {
                alt6=1;
            }
            else {
                alt6=2;
            }
            switch (alt6) {
                case 1 :
                    // GrammarHighlighterLexerBase.g:81:5: '*/'
                    {
                    match("*/"); 



                    state.type = END_COMMENT;

                    }
                    break;
                case 2 :
                    // GrammarHighlighterLexerBase.g:82:5: 
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

    // $ANTLR start "CHAR_LITERAL"
    public final void mCHAR_LITERAL() throws RecognitionException {
        try {
            int _type = CHAR_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:87:2: ( '\\'' ( '\\\\' . |~ ( '\\r' | '\\n' | '\\'' | '\\\\' ) )* ( '\\'' )? )
            // GrammarHighlighterLexerBase.g:87:4: '\\'' ( '\\\\' . |~ ( '\\r' | '\\n' | '\\'' | '\\\\' ) )* ( '\\'' )?
            {
            match('\''); 

            // GrammarHighlighterLexerBase.g:88:3: ( '\\\\' . |~ ( '\\r' | '\\n' | '\\'' | '\\\\' ) )*
            loop7:
            do {
                int alt7=3;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='\\') ) {
                    alt7=1;
                }
                else if ( ((LA7_0 >= '\u0000' && LA7_0 <= '\t')||(LA7_0 >= '\u000B' && LA7_0 <= '\f')||(LA7_0 >= '\u000E' && LA7_0 <= '&')||(LA7_0 >= '(' && LA7_0 <= '[')||(LA7_0 >= ']' && LA7_0 <= '\uFFFF')) ) {
                    alt7=2;
                }


                switch (alt7) {
            	case 1 :
            	    // GrammarHighlighterLexerBase.g:88:5: '\\\\' .
            	    {
            	    match('\\'); 

            	    matchAny(); 

            	    }
            	    break;
            	case 2 :
            	    // GrammarHighlighterLexerBase.g:89:5: ~ ( '\\r' | '\\n' | '\\'' | '\\\\' )
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

            	default :
            	    break loop7;
                }
            } while (true);


            // GrammarHighlighterLexerBase.g:91:3: ( '\\'' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='\'') ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // GrammarHighlighterLexerBase.g:91:3: '\\''
                    {
                    match('\''); 

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
    // $ANTLR end "CHAR_LITERAL"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:95:2: ( '\"' ( '\\\\' . |~ ( '\\r' | '\\n' | '\"' | '\\\\' ) )* ( '\"' )? )
            // GrammarHighlighterLexerBase.g:95:4: '\"' ( '\\\\' . |~ ( '\\r' | '\\n' | '\"' | '\\\\' ) )* ( '\"' )?
            {
            match('\"'); 

            // GrammarHighlighterLexerBase.g:96:3: ( '\\\\' . |~ ( '\\r' | '\\n' | '\"' | '\\\\' ) )*
            loop9:
            do {
                int alt9=3;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='\\') ) {
                    alt9=1;
                }
                else if ( ((LA9_0 >= '\u0000' && LA9_0 <= '\t')||(LA9_0 >= '\u000B' && LA9_0 <= '\f')||(LA9_0 >= '\u000E' && LA9_0 <= '!')||(LA9_0 >= '#' && LA9_0 <= '[')||(LA9_0 >= ']' && LA9_0 <= '\uFFFF')) ) {
                    alt9=2;
                }


                switch (alt9) {
            	case 1 :
            	    // GrammarHighlighterLexerBase.g:96:5: '\\\\' .
            	    {
            	    match('\\'); 

            	    matchAny(); 

            	    }
            	    break;
            	case 2 :
            	    // GrammarHighlighterLexerBase.g:97:5: ~ ( '\\r' | '\\n' | '\"' | '\\\\' )
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

            	default :
            	    break loop9;
                }
            } while (true);


            // GrammarHighlighterLexerBase.g:99:3: ( '\"' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\"') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // GrammarHighlighterLexerBase.g:99:3: '\"'
                    {
                    match('\"'); 

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
    // $ANTLR end "STRING_LITERAL"

    // $ANTLR start "OPEN_ELEMENT_OPTION"
    public final void mOPEN_ELEMENT_OPTION() throws RecognitionException {
        try {
            int _type = OPEN_ELEMENT_OPTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:103:2: ( '<' )
            // GrammarHighlighterLexerBase.g:103:4: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OPEN_ELEMENT_OPTION"

    // $ANTLR start "CLOSE_ELEMENT_OPTION"
    public final void mCLOSE_ELEMENT_OPTION() throws RecognitionException {
        try {
            int _type = CLOSE_ELEMENT_OPTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:107:2: ( '>' )
            // GrammarHighlighterLexerBase.g:107:4: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CLOSE_ELEMENT_OPTION"

    // $ANTLR start "AMPERSAND"
    public final void mAMPERSAND() throws RecognitionException {
        try {
            int _type = AMPERSAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:110:11: ( '@' )
            // GrammarHighlighterLexerBase.g:110:13: '@'
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
    // $ANTLR end "AMPERSAND"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:112:7: ( ',' )
            // GrammarHighlighterLexerBase.g:112:9: ','
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

    // $ANTLR start "QUESTION"
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:114:10: ( '?' )
            // GrammarHighlighterLexerBase.g:114:12: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUESTION"

    // $ANTLR start "TREE_BEGIN"
    public final void mTREE_BEGIN() throws RecognitionException {
        try {
            int _type = TREE_BEGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:116:12: ( '^(' )
            // GrammarHighlighterLexerBase.g:116:14: '^('
            {
            match("^("); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TREE_BEGIN"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:118:7: ( '(' )
            // GrammarHighlighterLexerBase.g:118:9: '('
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
            // GrammarHighlighterLexerBase.g:120:7: ( ')' )
            // GrammarHighlighterLexerBase.g:120:9: ')'
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

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:122:7: ( ':' )
            // GrammarHighlighterLexerBase.g:122:9: ':'
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

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:124:5: ( '*' )
            // GrammarHighlighterLexerBase.g:124:7: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:126:5: ( '+' )
            // GrammarHighlighterLexerBase.g:126:7: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:128:8: ( '=' )
            // GrammarHighlighterLexerBase.g:128:10: '='
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
    // $ANTLR end "ASSIGN"

    // $ANTLR start "PLUS_ASSIGN"
    public final void mPLUS_ASSIGN() throws RecognitionException {
        try {
            int _type = PLUS_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:130:13: ( '+=' )
            // GrammarHighlighterLexerBase.g:130:15: '+='
            {
            match("+="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PLUS_ASSIGN"

    // $ANTLR start "IMPLIES"
    public final void mIMPLIES() throws RecognitionException {
        try {
            int _type = IMPLIES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:132:9: ( '=>' )
            // GrammarHighlighterLexerBase.g:132:11: '=>'
            {
            match("=>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IMPLIES"

    // $ANTLR start "REWRITE"
    public final void mREWRITE() throws RecognitionException {
        try {
            int _type = REWRITE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:134:9: ( '->' )
            // GrammarHighlighterLexerBase.g:134:11: '->'
            {
            match("->"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REWRITE"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:136:5: ( ';' )
            // GrammarHighlighterLexerBase.g:136:7: ';'
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

    // $ANTLR start "ROOT"
    public final void mROOT() throws RecognitionException {
        try {
            int _type = ROOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:138:6: ( '^' )
            // GrammarHighlighterLexerBase.g:138:8: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ROOT"

    // $ANTLR start "BANG"
    public final void mBANG() throws RecognitionException {
        try {
            int _type = BANG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:140:6: ( '!' )
            // GrammarHighlighterLexerBase.g:140:8: '!'
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
    // $ANTLR end "BANG"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:142:4: ( '|' )
            // GrammarHighlighterLexerBase.g:142:6: '|'
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
    // $ANTLR end "OR"

    // $ANTLR start "WILDCARD"
    public final void mWILDCARD() throws RecognitionException {
        try {
            int _type = WILDCARD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:144:10: ( '.' )
            // GrammarHighlighterLexerBase.g:144:12: '.'
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
    // $ANTLR end "WILDCARD"

    // $ANTLR start "ETC"
    public final void mETC() throws RecognitionException {
        try {
            int _type = ETC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:146:5: ( '...' )
            // GrammarHighlighterLexerBase.g:146:7: '...'
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
    // $ANTLR end "ETC"

    // $ANTLR start "RANGE"
    public final void mRANGE() throws RecognitionException {
        try {
            int _type = RANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:148:7: ( '..' )
            // GrammarHighlighterLexerBase.g:148:9: '..'
            {
            match(".."); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RANGE"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:150:5: ( '~' )
            // GrammarHighlighterLexerBase.g:150:7: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "LBRACK"
    public final void mLBRACK() throws RecognitionException {
        try {
            int _type = LBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:152:7: ( '[' )
            // GrammarHighlighterLexerBase.g:152:9: '['
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
            // GrammarHighlighterLexerBase.g:154:7: ( ']' )
            // GrammarHighlighterLexerBase.g:154:9: ']'
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

    // $ANTLR start "LCURLY"
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:156:7: ( '{' )
            // GrammarHighlighterLexerBase.g:156:9: '{'
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
    // $ANTLR end "LCURLY"

    // $ANTLR start "RCURLY"
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:158:7: ( '}' )
            // GrammarHighlighterLexerBase.g:158:9: '}'
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
    // $ANTLR end "RCURLY"

    // $ANTLR start "DOLLAR"
    public final void mDOLLAR() throws RecognitionException {
        try {
            int _type = DOLLAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:160:8: ( '$' )
            // GrammarHighlighterLexerBase.g:160:10: '$'
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

    // $ANTLR start "ESCAPE_BRACKET"
    public final void mESCAPE_BRACKET() throws RecognitionException {
        try {
            int _type = ESCAPE_BRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:164:2: ( '\\\\]' )
            // GrammarHighlighterLexerBase.g:164:4: '\\\\]'
            {
            match("\\]"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ESCAPE_BRACKET"

    // $ANTLR start "DOUBLE_ANGLE_STRING_LITERAL"
    public final void mDOUBLE_ANGLE_STRING_LITERAL() throws RecognitionException {
        try {
            int _type = DOUBLE_ANGLE_STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:168:2: ( '<<' CONTINUE_DOUBLE_ANGLE_STRING_LITERAL )
            // GrammarHighlighterLexerBase.g:168:4: '<<' CONTINUE_DOUBLE_ANGLE_STRING_LITERAL
            {
            match("<<"); 



            mCONTINUE_DOUBLE_ANGLE_STRING_LITERAL(); 


            _type = state.type;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOUBLE_ANGLE_STRING_LITERAL"

    // $ANTLR start "END_DOUBLE_ANGLE_STRING_LITERAL"
    public final void mEND_DOUBLE_ANGLE_STRING_LITERAL() throws RecognitionException {
        try {
            // GrammarHighlighterLexerBase.g:171:42: ()
            // GrammarHighlighterLexerBase.g:171:44: 
            {
            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "END_DOUBLE_ANGLE_STRING_LITERAL"

    // $ANTLR start "CONTINUE_DOUBLE_ANGLE_STRING_LITERAL"
    public final void mCONTINUE_DOUBLE_ANGLE_STRING_LITERAL() throws RecognitionException {
        try {
            // GrammarHighlighterLexerBase.g:175:2: ( (~ ( '\\r' | '\\n' | '>' ) |{...}? => '>' )* ( '>>' |) )
            // GrammarHighlighterLexerBase.g:175:4: (~ ( '\\r' | '\\n' | '>' ) |{...}? => '>' )* ( '>>' |)
            {
            // GrammarHighlighterLexerBase.g:175:4: (~ ( '\\r' | '\\n' | '>' ) |{...}? => '>' )*
            loop11:
            do {
                int alt11=3;
                int LA11_0 = input.LA(1);

                if ( (LA11_0=='>') ) {
                    int LA11_1 = input.LA(2);

                    if ( (LA11_1=='>') ) {
                        int LA11_4 = input.LA(3);

                        if ( ((input.LA(2) != '>')) ) {
                            alt11=2;
                        }


                    }

                    else {
                        alt11=2;
                    }


                }
                else if ( ((LA11_0 >= '\u0000' && LA11_0 <= '\t')||(LA11_0 >= '\u000B' && LA11_0 <= '\f')||(LA11_0 >= '\u000E' && LA11_0 <= '=')||(LA11_0 >= '?' && LA11_0 <= '\uFFFF')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // GrammarHighlighterLexerBase.g:175:6: ~ ( '\\r' | '\\n' | '>' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '=')||(input.LA(1) >= '?' && input.LA(1) <= '\uFFFF') ) {
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
            	    // GrammarHighlighterLexerBase.g:176:5: {...}? => '>'
            	    {
            	    if ( !((input.LA(2) != '>')) ) {
            	        throw new FailedPredicateException(input, "CONTINUE_DOUBLE_ANGLE_STRING_LITERAL", "input.LA(2) != '>'");
            	    }

            	    match('>'); 

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            // GrammarHighlighterLexerBase.g:178:3: ( '>>' |)
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='>') ) {
                alt12=1;
            }
            else {
                alt12=2;
            }
            switch (alt12) {
                case 1 :
                    // GrammarHighlighterLexerBase.g:178:5: '>>'
                    {
                    match(">>"); 



                    state.type = END_DOUBLE_ANGLE_STRING_LITERAL;

                    }
                    break;
                case 2 :
                    // GrammarHighlighterLexerBase.g:179:5: 
                    {
                    state.type = CONTINUE_DOUBLE_ANGLE_STRING_LITERAL;

                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONTINUE_DOUBLE_ANGLE_STRING_LITERAL"

    // $ANTLR start "ESC"
    public final void mESC() throws RecognitionException {
        try {
            // GrammarHighlighterLexerBase.g:185:2: ( '\\\\' ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '\\\\' | '>' | 'u' XDIGIT XDIGIT XDIGIT XDIGIT | . ) )
            // GrammarHighlighterLexerBase.g:185:4: '\\\\' ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '\\\\' | '>' | 'u' XDIGIT XDIGIT XDIGIT XDIGIT | . )
            {
            match('\\'); 

            // GrammarHighlighterLexerBase.g:186:3: ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '\\\\' | '>' | 'u' XDIGIT XDIGIT XDIGIT XDIGIT | . )
            int alt13=11;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='n') ) {
                alt13=1;
            }
            else if ( (LA13_0=='r') ) {
                alt13=2;
            }
            else if ( (LA13_0=='t') ) {
                alt13=3;
            }
            else if ( (LA13_0=='b') ) {
                alt13=4;
            }
            else if ( (LA13_0=='f') ) {
                alt13=5;
            }
            else if ( (LA13_0=='\"') ) {
                alt13=6;
            }
            else if ( (LA13_0=='\'') ) {
                alt13=7;
            }
            else if ( (LA13_0=='\\') ) {
                alt13=8;
            }
            else if ( (LA13_0=='>') ) {
                alt13=9;
            }
            else if ( (LA13_0=='u') ) {
                int LA13_10 = input.LA(2);

                if ( ((LA13_10 >= '0' && LA13_10 <= '9')||(LA13_10 >= 'A' && LA13_10 <= 'F')||(LA13_10 >= 'a' && LA13_10 <= 'f')) ) {
                    alt13=10;
                }
                else {
                    alt13=11;
                }
            }
            else if ( ((LA13_0 >= '\u0000' && LA13_0 <= '!')||(LA13_0 >= '#' && LA13_0 <= '&')||(LA13_0 >= '(' && LA13_0 <= '=')||(LA13_0 >= '?' && LA13_0 <= '[')||(LA13_0 >= ']' && LA13_0 <= 'a')||(LA13_0 >= 'c' && LA13_0 <= 'e')||(LA13_0 >= 'g' && LA13_0 <= 'm')||(LA13_0 >= 'o' && LA13_0 <= 'q')||LA13_0=='s'||(LA13_0 >= 'v' && LA13_0 <= '\uFFFF')) ) {
                alt13=11;
            }
            else {
            	NoViableAltException nvae =
            		new NoViableAltException("", 13, 0, input);

            	throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // GrammarHighlighterLexerBase.g:186:5: 'n'
                    {
                    match('n'); 

                    }
                    break;
                case 2 :
                    // GrammarHighlighterLexerBase.g:187:5: 'r'
                    {
                    match('r'); 

                    }
                    break;
                case 3 :
                    // GrammarHighlighterLexerBase.g:188:5: 't'
                    {
                    match('t'); 

                    }
                    break;
                case 4 :
                    // GrammarHighlighterLexerBase.g:189:5: 'b'
                    {
                    match('b'); 

                    }
                    break;
                case 5 :
                    // GrammarHighlighterLexerBase.g:190:5: 'f'
                    {
                    match('f'); 

                    }
                    break;
                case 6 :
                    // GrammarHighlighterLexerBase.g:191:5: '\"'
                    {
                    match('\"'); 

                    }
                    break;
                case 7 :
                    // GrammarHighlighterLexerBase.g:192:5: '\\''
                    {
                    match('\''); 

                    }
                    break;
                case 8 :
                    // GrammarHighlighterLexerBase.g:193:5: '\\\\'
                    {
                    match('\\'); 

                    }
                    break;
                case 9 :
                    // GrammarHighlighterLexerBase.g:194:5: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 10 :
                    // GrammarHighlighterLexerBase.g:195:5: 'u' XDIGIT XDIGIT XDIGIT XDIGIT
                    {
                    match('u'); 

                    mXDIGIT(); 


                    mXDIGIT(); 


                    mXDIGIT(); 


                    mXDIGIT(); 


                    }
                    break;
                case 11 :
                    // GrammarHighlighterLexerBase.g:196:5: .
                    {
                    matchAny(); 

                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ESC"

    // $ANTLR start "XDIGIT"
    public final void mXDIGIT() throws RecognitionException {
        try {
            // GrammarHighlighterLexerBase.g:202:2: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            // GrammarHighlighterLexerBase.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
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
    // $ANTLR end "XDIGIT"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:208:2: ( ( '0' .. '9' )+ )
            // GrammarHighlighterLexerBase.g:208:4: ( '0' .. '9' )+
            {
            // GrammarHighlighterLexerBase.g:208:4: ( '0' .. '9' )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0 >= '0' && LA14_0 <= '9')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // GrammarHighlighterLexerBase.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
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
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "ANYCHAR"
    public final void mANYCHAR() throws RecognitionException {
        try {
            int _type = ANYCHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GrammarHighlighterLexerBase.g:212:2: ( . )
            // GrammarHighlighterLexerBase.g:212:4: .
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
        // GrammarHighlighterLexerBase.g:1:8: ( IDENTIFIER | DIRECTIVE | REFERENCE | WS | NEWLINE | COMMENT | ML_COMMENT | CHAR_LITERAL | STRING_LITERAL | OPEN_ELEMENT_OPTION | CLOSE_ELEMENT_OPTION | AMPERSAND | COMMA | QUESTION | TREE_BEGIN | LPAREN | RPAREN | COLON | STAR | PLUS | ASSIGN | PLUS_ASSIGN | IMPLIES | REWRITE | SEMI | ROOT | BANG | OR | WILDCARD | ETC | RANGE | NOT | LBRACK | RBRACK | LCURLY | RCURLY | DOLLAR | ESCAPE_BRACKET | DOUBLE_ANGLE_STRING_LITERAL | INT | ANYCHAR )
        int alt15=41;
        alt15 = dfa15.predict(input);
        switch (alt15) {
            case 1 :
                // GrammarHighlighterLexerBase.g:1:10: IDENTIFIER
                {
                mIDENTIFIER(); 


                }
                break;
            case 2 :
                // GrammarHighlighterLexerBase.g:1:21: DIRECTIVE
                {
                mDIRECTIVE(); 


                }
                break;
            case 3 :
                // GrammarHighlighterLexerBase.g:1:31: REFERENCE
                {
                mREFERENCE(); 


                }
                break;
            case 4 :
                // GrammarHighlighterLexerBase.g:1:41: WS
                {
                mWS(); 


                }
                break;
            case 5 :
                // GrammarHighlighterLexerBase.g:1:44: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 6 :
                // GrammarHighlighterLexerBase.g:1:52: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 7 :
                // GrammarHighlighterLexerBase.g:1:60: ML_COMMENT
                {
                mML_COMMENT(); 


                }
                break;
            case 8 :
                // GrammarHighlighterLexerBase.g:1:71: CHAR_LITERAL
                {
                mCHAR_LITERAL(); 


                }
                break;
            case 9 :
                // GrammarHighlighterLexerBase.g:1:84: STRING_LITERAL
                {
                mSTRING_LITERAL(); 


                }
                break;
            case 10 :
                // GrammarHighlighterLexerBase.g:1:99: OPEN_ELEMENT_OPTION
                {
                mOPEN_ELEMENT_OPTION(); 


                }
                break;
            case 11 :
                // GrammarHighlighterLexerBase.g:1:119: CLOSE_ELEMENT_OPTION
                {
                mCLOSE_ELEMENT_OPTION(); 


                }
                break;
            case 12 :
                // GrammarHighlighterLexerBase.g:1:140: AMPERSAND
                {
                mAMPERSAND(); 


                }
                break;
            case 13 :
                // GrammarHighlighterLexerBase.g:1:150: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 14 :
                // GrammarHighlighterLexerBase.g:1:156: QUESTION
                {
                mQUESTION(); 


                }
                break;
            case 15 :
                // GrammarHighlighterLexerBase.g:1:165: TREE_BEGIN
                {
                mTREE_BEGIN(); 


                }
                break;
            case 16 :
                // GrammarHighlighterLexerBase.g:1:176: LPAREN
                {
                mLPAREN(); 


                }
                break;
            case 17 :
                // GrammarHighlighterLexerBase.g:1:183: RPAREN
                {
                mRPAREN(); 


                }
                break;
            case 18 :
                // GrammarHighlighterLexerBase.g:1:190: COLON
                {
                mCOLON(); 


                }
                break;
            case 19 :
                // GrammarHighlighterLexerBase.g:1:196: STAR
                {
                mSTAR(); 


                }
                break;
            case 20 :
                // GrammarHighlighterLexerBase.g:1:201: PLUS
                {
                mPLUS(); 


                }
                break;
            case 21 :
                // GrammarHighlighterLexerBase.g:1:206: ASSIGN
                {
                mASSIGN(); 


                }
                break;
            case 22 :
                // GrammarHighlighterLexerBase.g:1:213: PLUS_ASSIGN
                {
                mPLUS_ASSIGN(); 


                }
                break;
            case 23 :
                // GrammarHighlighterLexerBase.g:1:225: IMPLIES
                {
                mIMPLIES(); 


                }
                break;
            case 24 :
                // GrammarHighlighterLexerBase.g:1:233: REWRITE
                {
                mREWRITE(); 


                }
                break;
            case 25 :
                // GrammarHighlighterLexerBase.g:1:241: SEMI
                {
                mSEMI(); 


                }
                break;
            case 26 :
                // GrammarHighlighterLexerBase.g:1:246: ROOT
                {
                mROOT(); 


                }
                break;
            case 27 :
                // GrammarHighlighterLexerBase.g:1:251: BANG
                {
                mBANG(); 


                }
                break;
            case 28 :
                // GrammarHighlighterLexerBase.g:1:256: OR
                {
                mOR(); 


                }
                break;
            case 29 :
                // GrammarHighlighterLexerBase.g:1:259: WILDCARD
                {
                mWILDCARD(); 


                }
                break;
            case 30 :
                // GrammarHighlighterLexerBase.g:1:268: ETC
                {
                mETC(); 


                }
                break;
            case 31 :
                // GrammarHighlighterLexerBase.g:1:272: RANGE
                {
                mRANGE(); 


                }
                break;
            case 32 :
                // GrammarHighlighterLexerBase.g:1:278: NOT
                {
                mNOT(); 


                }
                break;
            case 33 :
                // GrammarHighlighterLexerBase.g:1:282: LBRACK
                {
                mLBRACK(); 


                }
                break;
            case 34 :
                // GrammarHighlighterLexerBase.g:1:289: RBRACK
                {
                mRBRACK(); 


                }
                break;
            case 35 :
                // GrammarHighlighterLexerBase.g:1:296: LCURLY
                {
                mLCURLY(); 


                }
                break;
            case 36 :
                // GrammarHighlighterLexerBase.g:1:303: RCURLY
                {
                mRCURLY(); 


                }
                break;
            case 37 :
                // GrammarHighlighterLexerBase.g:1:310: DOLLAR
                {
                mDOLLAR(); 


                }
                break;
            case 38 :
                // GrammarHighlighterLexerBase.g:1:317: ESCAPE_BRACKET
                {
                mESCAPE_BRACKET(); 


                }
                break;
            case 39 :
                // GrammarHighlighterLexerBase.g:1:332: DOUBLE_ANGLE_STRING_LITERAL
                {
                mDOUBLE_ANGLE_STRING_LITERAL(); 


                }
                break;
            case 40 :
                // GrammarHighlighterLexerBase.g:1:360: INT
                {
                mINT(); 


                }
                break;
            case 41 :
                // GrammarHighlighterLexerBase.g:1:364: ANYCHAR
                {
                mANYCHAR(); 


                }
                break;

        }

    }


    protected DFA15 dfa15 = new DFA15(this);
    static final String DFA15_eotS =
        "\2\uffff\1\44\1\46\1\uffff\1\41\1\uffff\1\41\2\uffff\1\56\3\uffff"+
        "\1\63\4\uffff\1\71\1\73\1\41\3\uffff\1\101\5\uffff\1\41\40\uffff"+
        "\1\112\12\uffff";
    static final String DFA15_eofS =
        "\113\uffff";
    static final String DFA15_minS =
        "\1\0\1\uffff\2\101\1\uffff\1\12\1\uffff\1\52\2\uffff\1\74\3\uffff"+
        "\1\50\4\uffff\1\75\2\76\3\uffff\1\56\5\uffff\1\135\40\uffff\1\56"+
        "\12\uffff";
    static final String DFA15_maxS =
        "\1\uffff\1\uffff\2\172\1\uffff\1\12\1\uffff\1\57\2\uffff\1\74\3"+
        "\uffff\1\50\4\uffff\1\75\2\76\3\uffff\1\56\5\uffff\1\135\40\uffff"+
        "\1\56\12\uffff";
    static final String DFA15_acceptS =
        "\1\uffff\1\1\2\uffff\1\4\1\uffff\1\5\1\uffff\1\10\1\11\1\uffff\1"+
        "\13\1\15\1\16\1\uffff\1\20\1\21\1\22\1\23\3\uffff\1\31\1\33\1\34"+
        "\1\uffff\1\40\1\41\1\42\1\43\1\44\1\uffff\1\50\1\51\1\1\1\2\1\14"+
        "\1\3\1\45\1\4\1\5\1\6\1\7\1\10\1\11\1\47\1\12\1\13\1\15\1\16\1\17"+
        "\1\32\1\20\1\21\1\22\1\23\1\26\1\24\1\27\1\25\1\30\1\31\1\33\1\34"+
        "\1\uffff\1\35\1\40\1\41\1\42\1\43\1\44\1\46\1\50\1\36\1\37";
    static final String DFA15_specialS =
        "\1\0\112\uffff}>";
    static final String[] DFA15_transitionS = {
            "\11\41\1\4\1\6\2\41\1\5\22\41\1\4\1\27\1\11\1\41\1\3\2\41\1"+
            "\10\1\17\1\20\1\22\1\23\1\14\1\25\1\31\1\7\12\40\1\21\1\26\1"+
            "\12\1\24\1\13\1\15\1\2\32\1\1\33\1\37\1\34\1\16\1\1\1\41\32"+
            "\1\1\35\1\30\1\36\1\32\uff81\41",
            "",
            "\32\43\4\uffff\1\43\1\uffff\32\43",
            "\32\45\4\uffff\1\45\1\uffff\32\45",
            "",
            "\1\50",
            "",
            "\1\52\4\uffff\1\51",
            "",
            "",
            "\1\55",
            "",
            "",
            "",
            "\1\62",
            "",
            "",
            "",
            "",
            "\1\70",
            "\1\72",
            "\1\74",
            "",
            "",
            "",
            "\1\100",
            "",
            "",
            "",
            "",
            "",
            "\1\107",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\111",
            "",
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

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( IDENTIFIER | DIRECTIVE | REFERENCE | WS | NEWLINE | COMMENT | ML_COMMENT | CHAR_LITERAL | STRING_LITERAL | OPEN_ELEMENT_OPTION | CLOSE_ELEMENT_OPTION | AMPERSAND | COMMA | QUESTION | TREE_BEGIN | LPAREN | RPAREN | COLON | STAR | PLUS | ASSIGN | PLUS_ASSIGN | IMPLIES | REWRITE | SEMI | ROOT | BANG | OR | WILDCARD | ETC | RANGE | NOT | LBRACK | RBRACK | LCURLY | RCURLY | DOLLAR | ESCAPE_BRACKET | DOUBLE_ANGLE_STRING_LITERAL | INT | ANYCHAR );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA15_0 = input.LA(1);

                        s = -1;
                        if ( ((LA15_0 >= 'A' && LA15_0 <= 'Z')||LA15_0=='_'||(LA15_0 >= 'a' && LA15_0 <= 'z')) ) {s = 1;}

                        else if ( (LA15_0=='@') ) {s = 2;}

                        else if ( (LA15_0=='$') ) {s = 3;}

                        else if ( (LA15_0=='\t'||LA15_0==' ') ) {s = 4;}

                        else if ( (LA15_0=='\r') ) {s = 5;}

                        else if ( (LA15_0=='\n') ) {s = 6;}

                        else if ( (LA15_0=='/') ) {s = 7;}

                        else if ( (LA15_0=='\'') ) {s = 8;}

                        else if ( (LA15_0=='\"') ) {s = 9;}

                        else if ( (LA15_0=='<') ) {s = 10;}

                        else if ( (LA15_0=='>') ) {s = 11;}

                        else if ( (LA15_0==',') ) {s = 12;}

                        else if ( (LA15_0=='?') ) {s = 13;}

                        else if ( (LA15_0=='^') ) {s = 14;}

                        else if ( (LA15_0=='(') ) {s = 15;}

                        else if ( (LA15_0==')') ) {s = 16;}

                        else if ( (LA15_0==':') ) {s = 17;}

                        else if ( (LA15_0=='*') ) {s = 18;}

                        else if ( (LA15_0=='+') ) {s = 19;}

                        else if ( (LA15_0=='=') ) {s = 20;}

                        else if ( (LA15_0=='-') ) {s = 21;}

                        else if ( (LA15_0==';') ) {s = 22;}

                        else if ( (LA15_0=='!') ) {s = 23;}

                        else if ( (LA15_0=='|') ) {s = 24;}

                        else if ( (LA15_0=='.') ) {s = 25;}

                        else if ( (LA15_0=='~') ) {s = 26;}

                        else if ( (LA15_0=='[') ) {s = 27;}

                        else if ( (LA15_0==']') ) {s = 28;}

                        else if ( (LA15_0=='{') ) {s = 29;}

                        else if ( (LA15_0=='}') ) {s = 30;}

                        else if ( (LA15_0=='\\') ) {s = 31;}

                        else if ( ((LA15_0 >= '0' && LA15_0 <= '9')) ) {s = 32;}

                        else if ( ((LA15_0 >= '\u0000' && LA15_0 <= '\b')||(LA15_0 >= '\u000B' && LA15_0 <= '\f')||(LA15_0 >= '\u000E' && LA15_0 <= '\u001F')||LA15_0=='#'||(LA15_0 >= '%' && LA15_0 <= '&')||LA15_0=='`'||(LA15_0 >= '\u007F' && LA15_0 <= '\uFFFF')) ) {s = 33;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 15, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

}