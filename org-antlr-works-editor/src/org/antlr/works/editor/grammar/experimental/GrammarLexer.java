// $ANTLR ANTLRVersion> GrammarLexer.java generatedTimestamp>
/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

public class GrammarLexer extends Lexer {
	public static final int
		TOKEN_REF=3, RULE_REF=4, DOC_COMMENT=5, BLOCK_COMMENT=6, LINE_COMMENT=7, 
		DOUBLE_QUOTE_STRING_LITERAL=8, DOUBLE_ANGLE_STRING_LITERAL=9, BEGIN_ARG_ACTION=10, 
		BEGIN_ACTION=11, OPTIONS=12, TOKENS=13, IMPORT=14, FRAGMENT=15, LEXER=16, 
		PARSER=17, GRAMMAR=18, PROTECTED=19, PUBLIC=20, PRIVATE=21, RETURNS=22, 
		LOCALS=23, THROWS=24, CATCH=25, FINALLY=26, TEMPLATE=27, MODE=28, COLON=29, 
		COLONCOLON=30, COMMA=31, SEMI=32, LPAREN=33, RPAREN=34, RARROW=35, LT=36, 
		GT=37, ASSIGN=38, QUESTION=39, STAR=40, PLUS=41, PLUS_ASSIGN=42, OR=43, 
		DOLLAR=44, DOT=45, RANGE=46, ETC=47, AT=48, POUND=49, NOT=50, RBRACE=51, 
		ID=52, INT=53, STRING_LITERAL=54, WS=55, ERRCHAR=56, ARG_ACTION_LT=57, 
		ARG_ACTION_GT=58, ARG_ACTION_LPAREN=59, ARG_ACTION_RPAREN=60, ARG_ACTION_EQUALS=61, 
		ARG_ACTION_COMMA=62, ARG_ACTION_ESCAPE=63, ARG_ACTION_WORD=64, ARG_ACTION_ELEMENT=65, 
		ARG_ACTION_TEXT=66, ARG_ACTION_WS=67, ARG_ACTION_NEWLINE=68, END_ARG_ACTION=69, 
		NESTED_ACTION=70, ACTION_DOT=71, ACTION_LT=72, ACTION_GT=73, ACTION_LPAREN=74, 
		ACTION_RPAREN=75, ACTION_LBRACK=76, ACTION_RBRACK=77, ACTION_EQUALS=78, 
		ACTION_COMMA=79, ACTION_COLON2=80, ACTION_COLON=81, ACTION_MINUS=82, ACTION_ESCAPE=83, 
		ACTION_WORD=84, ACTION_REFERENCE=85, ACTION_COMMENT=86, ACTION_LITERAL=87, 
		ACTION_TEXT=88, ACTION_WS=89, ACTION_NEWLINE=90, END_ACTION=91;
	public static final int ArgAction = 1;
	public static final int Action = 2;
	public static String[] modeNames = {
		"DEFAULT_MODE", "ArgAction", "Action"
	};

	public static final String[] tokenNames = {
		"<INVALID>", "<INVALID>", "<INVALID>",
		"TOKEN_REF", "RULE_REF", "DOC_COMMENT", "BLOCK_COMMENT", "LINE_COMMENT", 
		"DOUBLE_QUOTE_STRING_LITERAL", "DOUBLE_ANGLE_STRING_LITERAL", "BEGIN_ARG_ACTION", 
		"BEGIN_ACTION", "OPTIONS", "TOKENS", "'import'", "'fragment'", "'lexer'", 
		"'parser'", "'grammar'", "'protected'", "'public'", "'private'", "'returns'", 
		"'locals'", "'throws'", "'catch'", "'finally'", "'template'", "'mode'", 
		"COLON", "COLONCOLON", "COMMA", "';'", "LPAREN", "RPAREN", "'->'", "LT", 
		"GT", "ASSIGN", "'?'", "'*'", "'+'", "'+='", "'|'", "'$'", "DOT", "'..'", 
		"'...'", "'@'", "'#'", "'~'", "'}'", "ID", "INT", "STRING_LITERAL", "WS", 
		"ERRCHAR", "ARG_ACTION_LT", "ARG_ACTION_GT", "ARG_ACTION_LPAREN", "ARG_ACTION_RPAREN", 
		"ARG_ACTION_EQUALS", "ARG_ACTION_COMMA", "ARG_ACTION_ESCAPE", "ARG_ACTION_WORD", 
		"ARG_ACTION_ELEMENT", "ARG_ACTION_TEXT", "ARG_ACTION_WS", "ARG_ACTION_NEWLINE", 
		"END_ARG_ACTION", "NESTED_ACTION", "'.'", "'<'", "'>'", "'('", "')'", 
		"'['", "']'", "'='", "','", "'::'", "':'", "'-'", "ACTION_ESCAPE", "ACTION_WORD", 
		"ACTION_REFERENCE", "ACTION_COMMENT", "ACTION_LITERAL", "ACTION_TEXT", 
		"ACTION_WS", "ACTION_NEWLINE", "END_ACTION"
	};
	public static final String[] ruleNames = {
		"DOC_COMMENT", "BLOCK_COMMENT", "COMMENT", "BLOCK_COMMENT_BODY", "LINE_COMMENT", 
		"DOUBLE_QUOTE_STRING_LITERAL", "DOUBLE_ANGLE_STRING_LITERAL", "BEGIN_ARG_ACTION", 
		"BEGIN_ACTION", "OPTIONS", "TOKENS", "IMPORT", "FRAGMENT", "LEXER", "PARSER", 
		"GRAMMAR", "PROTECTED", "PUBLIC", "PRIVATE", "RETURNS", "LOCALS", "THROWS", 
		"CATCH", "FINALLY", "TEMPLATE", "MODE", "COLON", "COLONCOLON", "COMMA", 
		"SEMI", "LPAREN", "RPAREN", "RARROW", "LT", "GT", "ASSIGN", "QUESTION", 
		"STAR", "PLUS", "PLUS_ASSIGN", "OR", "DOLLAR", "DOT", "RANGE", "ETC", 
		"AT", "POUND", "NOT", "RBRACE", "ID", "NameChar", "NameStartChar", "ACTION_CHAR_LITERAL", 
		"ACTION_STRING_LITERAL", "ACTION_ESC", "INT", "STRING_LITERAL", "HEX_DIGIT", 
		"ESC_SEQ", "UNICODE_ESC", "WS", "NLCHARS", "WSCHARS", "WSNLCHARS", "ERRCHAR", 
		"ARG_ACTION_LT", "ARG_ACTION_GT", "ARG_ACTION_LPAREN", "ARG_ACTION_RPAREN", 
		"ARG_ACTION_EQUALS", "ARG_ACTION_COMMA", "ARG_ACTION_ESCAPE", "ARG_ACTION_WORD", 
		"ARG_ACTION_ELEMENT", "ARG_ACTION_TEXT", "ARG_ACTION_WS", "ARG_ACTION_NEWLINE", 
		"END_ARG_ACTION", "NESTED_ACTION", "ACTION_DOT", "ACTION_LT", "ACTION_GT", 
		"ACTION_LPAREN", "ACTION_RPAREN", "ACTION_LBRACK", "ACTION_RBRACK", "ACTION_EQUALS", 
		"ACTION_COMMA", "ACTION_COLON2", "ACTION_COLON", "ACTION_MINUS", "ACTION_ESCAPE", 
		"ACTION_WORD", "ACTION_REFERENCE", "ACTION_COMMENT", "ACTION_LITERAL", 
		"ACTION_TEXT", "ACTION_WS", "ACTION_NEWLINE", "END_ACTION"
	};


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN);
	}

	@Override
	public String getGrammarFileName() { return "GrammarLexer.g"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }


	@Override
	public void action(RuleContext<Integer> _localctx, int ruleIndex, int actionIndex) {
		switch ( ruleIndex ) {
			case 0 : DOC_COMMENT_action(_localctx, actionIndex); break;

			case 1 : BLOCK_COMMENT_action(_localctx, actionIndex); break;

			case 2 : COMMENT_action(_localctx, actionIndex); break;

			case 3 : BLOCK_COMMENT_BODY_action(_localctx, actionIndex); break;

			case 4 : LINE_COMMENT_action(_localctx, actionIndex); break;

			case 5 : DOUBLE_QUOTE_STRING_LITERAL_action(_localctx, actionIndex); break;

			case 6 : DOUBLE_ANGLE_STRING_LITERAL_action(_localctx, actionIndex); break;

			case 7 : BEGIN_ARG_ACTION_action(_localctx, actionIndex); break;

			case 8 : BEGIN_ACTION_action(_localctx, actionIndex); break;

			case 9 : OPTIONS_action(_localctx, actionIndex); break;

			case 10 : TOKENS_action(_localctx, actionIndex); break;

			case 11 : IMPORT_action(_localctx, actionIndex); break;

			case 12 : FRAGMENT_action(_localctx, actionIndex); break;

			case 13 : LEXER_action(_localctx, actionIndex); break;

			case 14 : PARSER_action(_localctx, actionIndex); break;

			case 15 : GRAMMAR_action(_localctx, actionIndex); break;

			case 16 : PROTECTED_action(_localctx, actionIndex); break;

			case 17 : PUBLIC_action(_localctx, actionIndex); break;

			case 18 : PRIVATE_action(_localctx, actionIndex); break;

			case 19 : RETURNS_action(_localctx, actionIndex); break;

			case 20 : LOCALS_action(_localctx, actionIndex); break;

			case 21 : THROWS_action(_localctx, actionIndex); break;

			case 22 : CATCH_action(_localctx, actionIndex); break;

			case 23 : FINALLY_action(_localctx, actionIndex); break;

			case 24 : TEMPLATE_action(_localctx, actionIndex); break;

			case 25 : MODE_action(_localctx, actionIndex); break;

			case 26 : COLON_action(_localctx, actionIndex); break;

			case 27 : COLONCOLON_action(_localctx, actionIndex); break;

			case 28 : COMMA_action(_localctx, actionIndex); break;

			case 29 : SEMI_action(_localctx, actionIndex); break;

			case 30 : LPAREN_action(_localctx, actionIndex); break;

			case 31 : RPAREN_action(_localctx, actionIndex); break;

			case 32 : RARROW_action(_localctx, actionIndex); break;

			case 33 : LT_action(_localctx, actionIndex); break;

			case 34 : GT_action(_localctx, actionIndex); break;

			case 35 : ASSIGN_action(_localctx, actionIndex); break;

			case 36 : QUESTION_action(_localctx, actionIndex); break;

			case 37 : STAR_action(_localctx, actionIndex); break;

			case 38 : PLUS_action(_localctx, actionIndex); break;

			case 39 : PLUS_ASSIGN_action(_localctx, actionIndex); break;

			case 40 : OR_action(_localctx, actionIndex); break;

			case 41 : DOLLAR_action(_localctx, actionIndex); break;

			case 42 : DOT_action(_localctx, actionIndex); break;

			case 43 : RANGE_action(_localctx, actionIndex); break;

			case 44 : ETC_action(_localctx, actionIndex); break;

			case 45 : AT_action(_localctx, actionIndex); break;

			case 46 : POUND_action(_localctx, actionIndex); break;

			case 47 : NOT_action(_localctx, actionIndex); break;

			case 48 : RBRACE_action(_localctx, actionIndex); break;

			case 49 : ID_action(_localctx, actionIndex); break;

			case 50 : NameChar_action(_localctx, actionIndex); break;

			case 51 : NameStartChar_action(_localctx, actionIndex); break;

			case 52 : ACTION_CHAR_LITERAL_action(_localctx, actionIndex); break;

			case 53 : ACTION_STRING_LITERAL_action(_localctx, actionIndex); break;

			case 54 : ACTION_ESC_action(_localctx, actionIndex); break;

			case 55 : INT_action(_localctx, actionIndex); break;

			case 56 : STRING_LITERAL_action(_localctx, actionIndex); break;

			case 57 : HEX_DIGIT_action(_localctx, actionIndex); break;

			case 58 : ESC_SEQ_action(_localctx, actionIndex); break;

			case 59 : UNICODE_ESC_action(_localctx, actionIndex); break;

			case 60 : WS_action(_localctx, actionIndex); break;

			case 61 : NLCHARS_action(_localctx, actionIndex); break;

			case 62 : WSCHARS_action(_localctx, actionIndex); break;

			case 63 : WSNLCHARS_action(_localctx, actionIndex); break;

			case 64 : ERRCHAR_action(_localctx, actionIndex); break;

			case 65 : ARG_ACTION_LT_action(_localctx, actionIndex); break;

			case 66 : ARG_ACTION_GT_action(_localctx, actionIndex); break;

			case 67 : ARG_ACTION_LPAREN_action(_localctx, actionIndex); break;

			case 68 : ARG_ACTION_RPAREN_action(_localctx, actionIndex); break;

			case 69 : ARG_ACTION_EQUALS_action(_localctx, actionIndex); break;

			case 70 : ARG_ACTION_COMMA_action(_localctx, actionIndex); break;

			case 71 : ARG_ACTION_ESCAPE_action(_localctx, actionIndex); break;

			case 72 : ARG_ACTION_WORD_action(_localctx, actionIndex); break;

			case 73 : ARG_ACTION_ELEMENT_action(_localctx, actionIndex); break;

			case 74 : ARG_ACTION_TEXT_action(_localctx, actionIndex); break;

			case 75 : ARG_ACTION_WS_action(_localctx, actionIndex); break;

			case 76 : ARG_ACTION_NEWLINE_action(_localctx, actionIndex); break;

			case 77 : END_ARG_ACTION_action(_localctx, actionIndex); break;

			case 78 : NESTED_ACTION_action(_localctx, actionIndex); break;

			case 79 : ACTION_DOT_action(_localctx, actionIndex); break;

			case 80 : ACTION_LT_action(_localctx, actionIndex); break;

			case 81 : ACTION_GT_action(_localctx, actionIndex); break;

			case 82 : ACTION_LPAREN_action(_localctx, actionIndex); break;

			case 83 : ACTION_RPAREN_action(_localctx, actionIndex); break;

			case 84 : ACTION_LBRACK_action(_localctx, actionIndex); break;

			case 85 : ACTION_RBRACK_action(_localctx, actionIndex); break;

			case 86 : ACTION_EQUALS_action(_localctx, actionIndex); break;

			case 87 : ACTION_COMMA_action(_localctx, actionIndex); break;

			case 88 : ACTION_COLON2_action(_localctx, actionIndex); break;

			case 89 : ACTION_COLON_action(_localctx, actionIndex); break;

			case 90 : ACTION_MINUS_action(_localctx, actionIndex); break;

			case 91 : ACTION_ESCAPE_action(_localctx, actionIndex); break;

			case 92 : ACTION_WORD_action(_localctx, actionIndex); break;

			case 93 : ACTION_REFERENCE_action(_localctx, actionIndex); break;

			case 94 : ACTION_COMMENT_action(_localctx, actionIndex); break;

			case 95 : ACTION_LITERAL_action(_localctx, actionIndex); break;

			case 96 : ACTION_TEXT_action(_localctx, actionIndex); break;

			case 97 : ACTION_WS_action(_localctx, actionIndex); break;

			case 98 : ACTION_NEWLINE_action(_localctx, actionIndex); break;

			case 99 : END_ACTION_action(_localctx, actionIndex); break;
		}
	}
	public void LT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void STAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_COLON_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void DOUBLE_ANGLE_STRING_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_ESCAPE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_COLON2_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void NOT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ARG_ACTION_LT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ARG_ACTION_ELEMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RPAREN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void IMPORT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void STRING_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BLOCK_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 2 : _channel = HIDDEN;  break;
		}
	}
	public void DOUBLE_QUOTE_STRING_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void GRAMMAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_CHAR_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void WSCHARS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ARG_ACTION_ESCAPE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ARG_ACTION_TEXT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_WS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RBRACE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void POUND_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_ESC_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LINE_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 0 : _channel =  HIDDEN; break;
		}
	}
	public void PRIVATE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void THROWS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void INT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void COLONCOLON_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void WSNLCHARS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void WS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 5 : _channel = HIDDEN;  break;
		}
	}
	public void ACTION_RBRACK_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LEXER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_TEXT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void OR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ARG_ACTION_COMMA_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_MINUS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void GT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CATCH_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PARSER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void DOLLAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PROTECTED_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void NESTED_ACTION_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 7 : _type = BEGIN_ACTION; pushMode(Action);  break;
		}
	}
	public void FRAGMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_REFERENCE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_DOT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ID_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 1 : 
							if ( Character.isUpperCase(getInputStream().getText(Interval.of(_tokenStartCharIndex, _tokenStartCharIndex)).charAt(0)) ) _type =  TOKEN_REF;
							else _type =  RULE_REF;
							 break;
		}
	}
	public void LPAREN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ARG_ACTION_RPAREN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void AT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ARG_ACTION_GT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ARG_ACTION_LPAREN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ESC_SEQ_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void END_ACTION_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 8 : popMode();  break;
		}
	}
	public void ETC_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void COMMA_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_GT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void DOC_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ARG_ACTION_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PLUS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BEGIN_ACTION_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 4 : pushMode(Action);  break;
		}
	}
	public void DOT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void MODE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_LBRACK_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BLOCK_COMMENT_BODY_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_WORD_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RETURNS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_EQUALS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LOCALS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RARROW_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void UNICODE_ESC_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void NameChar_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RANGE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TOKENS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void HEX_DIGIT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void END_ARG_ACTION_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 6 : popMode();  break;
		}
	}
	public void ACTION_STRING_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void SEMI_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ARG_ACTION_EQUALS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_RPAREN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void NameStartChar_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void NLCHARS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_COMMA_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void COLON_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void QUESTION_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void FINALLY_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TEMPLATE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_LT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ERRCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PLUS_ASSIGN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ASSIGN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ARG_ACTION_WS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ARG_ACTION_WORD_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PUBLIC_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ACTION_LPAREN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void OPTIONS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BEGIN_ARG_ACTION_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 3 : pushMode(ArgAction);  break;
		}
	}

	public static final String _serializedATN =
		"\2[\u0311\6\uffff\6\uffff\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4"+
		"\7\4\2\5\7\5\2\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f"+
		"\2\r\7\r\2\16\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2"+
		"\24\7\24\2\25\7\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2"+
		"\33\7\33\2\34\7\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2"+
		"#\7#\2$\7$\2%\7%\2&\7&\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2."+
		"\7.\2/\7/\2\60\7\60\2\61\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65"+
		"\2\66\7\66\2\67\7\67\28\78\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2"+
		"@\7@\2A\7A\2B\7B\2C\7C\2D\7D\2E\7E\2F\7F\2G\7G\2H\7H\2I\7I\2J\7J\2K\7"+
		"K\2L\7L\2M\7M\2N\7N\2O\7O\2P\7P\2Q\7Q\2R\7R\2S\7S\2T\7T\2U\7U\2V\7V\2"+
		"W\7W\2X\7X\2Y\7Y\2Z\7Z\2[\7[\2\\\7\\\2]\7]\2^\7^\2_\7_\2`\7`\2a\7a\2b"+
		"\7b\2c\7c\1\0\1\0\1\0\1\0\1\0\1\0\1\1\1\1\1\1\1\1\1\1\1\1\1\1\1\2\0\1"+
		"\2\0\3\2\b\2\1\3\0\5\3\b\3\n\3\f\3\u00e4\t\3\1\3\1\3\1\3\1\4\1\4\1\4\1"+
		"\4\0\5\4\b\4\n\4\f\4\u00f1\t\4\1\4\1\4\1\5\0\1\5\0\1\5\0\1\5\0\5\5\b\5"+
		"\n\5\f\5\u0100\t\5\1\5\1\5\1\6\1\6\1\6\1\6\0\5\6\b\6\n\6\f\6\u010c\t\6"+
		"\1\6\1\6\1\6\1\7\1\7\1\7\1\7\1\b\1\b\1\b\1\b\1\t\1\t\1\t\1\t\1\t\1\t\1"+
		"\t\1\t\1\t\0\5\t\b\t\n\t\f\t\u0126\t\t\1\t\1\t\1\n\1\n\1\n\1\n\1\n\1\n"+
		"\1\n\1\n\0\5\n\b\n\n\n\f\n\u0136\t\n\1\n\1\n\1\13\1\13\1\13\1\13\1\13"+
		"\1\13\1\13\1\f\1\f\1\f\1\f\1\f\1\f\1\f\1\f\1\f\1\r\1\r\1\r\1\r\1\r\1\r"+
		"\1\16\1\16\1\16\1\16\1\16\1\16\1\16\1\17\1\17\1\17\1\17\1\17\1\17\1\17"+
		"\1\17\1\20\1\20\1\20\1\20\1\20\1\20\1\20\1\20\1\20\1\20\1\21\1\21\1\21"+
		"\1\21\1\21\1\21\1\21\1\22\1\22\1\22\1\22\1\22\1\22\1\22\1\22\1\23\1\23"+
		"\1\23\1\23\1\23\1\23\1\23\1\23\1\24\1\24\1\24\1\24\1\24\1\24\1\24\1\25"+
		"\1\25\1\25\1\25\1\25\1\25\1\25\1\26\1\26\1\26\1\26\1\26\1\26\1\27\1\27"+
		"\1\27\1\27\1\27\1\27\1\27\1\27\1\30\1\30\1\30\1\30\1\30\1\30\1\30\1\30"+
		"\1\30\1\31\1\31\1\31\1\31\1\31\1\32\1\32\1\33\1\33\1\33\1\34\1\34\1\35"+
		"\1\35\1\36\1\36\1\37\1\37\1 \1 \1 \1!\1!\1\"\1\"\1#\1#\1$\1$\1%\1%\1&"+
		"\1&\1\'\1\'\1\'\1(\1(\1)\1)\1*\1*\1+\1+\1+\1,\1,\1,\1,\1-\1-\1.\1.\1/"+
		"\1/\1\60\1\60\1\61\0\1\61\0\5\61\b\61\n\61\f\61\u01e5\t\61\1\61\1\61\1"+
		"\62\0\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\3\62\b\62\1\63\0\1\63\0\1\63"+
		"\0\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0\1\63"+
		"\0\3\63\b\63\1\64\0\1\64\0\1\64\0\5\64\b\64\n\64\f\64\u021c\t\64\1\64"+
		"\1\64\1\65\0\1\65\0\1\65\0\5\65\b\65\n\65\f\65\u0229\t\65\1\65\1\65\1"+
		"\66\0\1\66\1\66\1\67\0\4\67\b\67\13\67\f\67\u0234\18\0\18\0\18\0\38\b"+
		"8\58\b8\n8\f8\u0242\t8\18\18\19\19\1:\0\1:\0\1:\0\3:\b:\1;\0\1;\0\1;\0"+
		"\1;\0\1;\0\3;\b;\3;\b;\3;\b;\3;\b;\1<\0\4<\b<\13<\f<\u0265\1<\1<\1=\0"+
		"\1=\0\3=\b=\1>\0\1>\0\1>\0\3>\b>\1?\0\1?\0\1?\0\1?\0\1?\0\3?\b?\1@\1@"+
		"\1A\1A\1B\1B\1C\1C\1D\1D\1E\1E\1F\1F\1G\0\1G\1G\1H\0\1H\0\5H\bH\nH\fH"+
		"\u029d\tH\1I\0\1I\0\3I\bI\1J\0\4J\bJ\13J\fJ\u02a8\1K\0\4K\bK\13K\fK\u02ae"+
		"\1L\0\1L\0\3L\bL\1L\0\3L\bL\1M\1M\1M\1M\1N\1N\1N\1N\1O\1O\1P\1P\1Q\1Q"+
		"\1R\1R\1S\1S\1T\1T\1U\1U\1V\1V\1W\1W\1X\1X\1X\1Y\1Y\1Z\1Z\1[\0\1[\1[\1"+
		"\\\0\4\\\b\\\13\\\f\\\u02e3\1]\0\1]\0\3]\b]\1^\1^\1_\0\1_\0\3_\b_\1`\0"+
		"\1`\0\1`\0\4`\b`\13`\f`\u02fb\1a\0\4a\ba\13a\fa\u0301\1b\0\1b\0\3b\bb"+
		"\1b\0\3b\bb\1c\1c\1c\1cd\3\5\uffff\5\6\2\7\0\uffff\t\0\uffff\13\7\0\r"+
		"\b\uffff\17\t\uffff\21\n\3\23\13\4\25\f\uffff\27\r\uffff\31\16\uffff\33"+
		"\17\uffff\35\20\uffff\37\21\uffff!\22\uffff#\23\uffff%\24\uffff\'\25\uffff"+
		")\26\uffff+\27\uffff-\30\uffff/\31\uffff\61\32\uffff\63\33\uffff\65\34"+
		"\uffff\67\35\uffff9\36\uffff;\37\uffff= \uffff?!\uffffA\"\uffffC#\uffff"+
		"E$\uffffG%\uffffI&\uffffK\'\uffffM(\uffffO)\uffffQ*\uffffS+\uffffU,\uffff"+
		"W-\uffffY.\uffff[/\uffff]\60\uffff_\61\uffffa\62\uffffc\63\uffffe\64\1"+
		"g\0\uffffi\0\uffffk\0\uffffm\0\uffffo\0\uffffq\65\uffffs\66\uffffu\0\uffff"+
		"w\0\uffffy\0\uffff{\67\5}\0\uffff\177\0\uffff\u0081\0\uffff\u00838\uffff"+
		"\u00859\uffff\u0087:\uffff\u0089;\uffff\u008b<\uffff\u008d=\uffff\u008f"+
		">\uffff\u0091?\uffff\u0093@\uffff\u0095A\uffff\u0097B\uffff\u0099C\uffff"+
		"\u009bD\uffff\u009dE\6\u009fF\7\u00a1G\uffff\u00a3H\uffff\u00a5I\uffff"+
		"\u00a7J\uffff\u00a9K\uffff\u00abL\uffff\u00adM\uffff\u00afN\uffff\u00b1"+
		"O\uffff\u00b3P\uffff\u00b5Q\uffff\u00b7R\uffff\u00b9S\uffff\u00bbT\uffff"+
		"\u00bdU\uffff\u00bfV\uffff\u00c1W\uffff\u00c3X\uffff\u00c5Y\uffff\u00c7"+
		"Z\uffff\u00c9[\b\3\0\1\2\20\2\n\n\r\r\1\"\"\1\'\'\1\"\"\2\'\'\\\\\3\60"+
		"9AFaf\b\"\"\'\'\\\\bbffnnrrtt\3\t\n\f\r  \5$$\609AZ__az\4\609AZ__az\r"+
		"\t\n\r\r  \"\"$$\'),,\609<>AZ\\]__az\2\t\t  \4\609AZ__az\2**//\f\t\n\r"+
		"\r  \"\"$$\'),:<>A]__a{}}\2\t\t  \u02e8\0\3\1\0\0\0\0\5\1\0\0\0\0\13\1"+
		"\0\0\0\0\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1\0\0\0\0\25\1\0\0\0"+
		"\0\27\1\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0\0\0\37\1\0\0\0\0!\1"+
		"\0\0\0\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0\0\0+\1\0\0\0\0-\1\0"+
		"\0\0\0/\1\0\0\0\0\61\1\0\0\0\0\63\1\0\0\0\0\65\1\0\0\0\0\67\1\0\0\0\0"+
		"9\1\0\0\0\0;\1\0\0\0\0=\1\0\0\0\0?\1\0\0\0\0A\1\0\0\0\0C\1\0\0\0\0E\1"+
		"\0\0\0\0G\1\0\0\0\0I\1\0\0\0\0K\1\0\0\0\0M\1\0\0\0\0O\1\0\0\0\0Q\1\0\0"+
		"\0\0S\1\0\0\0\0U\1\0\0\0\0W\1\0\0\0\0Y\1\0\0\0\0[\1\0\0\0\0]\1\0\0\0\0"+
		"_\1\0\0\0\0a\1\0\0\0\0c\1\0\0\0\0e\1\0\0\0\0q\1\0\0\0\0s\1\0\0\0\0{\1"+
		"\0\0\0\0\u0083\1\0\0\0\1\u0085\1\0\0\0\1\u0087\1\0\0\0\1\u0089\1\0\0\0"+
		"\1\u008b\1\0\0\0\1\u008d\1\0\0\0\1\u008f\1\0\0\0\1\u0091\1\0\0\0\1\u0093"+
		"\1\0\0\0\1\u0095\1\0\0\0\1\u0097\1\0\0\0\1\u0099\1\0\0\0\1\u009b\1\0\0"+
		"\0\1\u009d\1\0\0\0\2\u009f\1\0\0\0\2\u00a1\1\0\0\0\2\u00a3\1\0\0\0\2\u00a5"+
		"\1\0\0\0\2\u00a7\1\0\0\0\2\u00a9\1\0\0\0\2\u00ab\1\0\0\0\2\u00ad\1\0\0"+
		"\0\2\u00af\1\0\0\0\2\u00b1\1\0\0\0\2\u00b3\1\0\0\0\2\u00b5\1\0\0\0\2\u00b7"+
		"\1\0\0\0\2\u00b9\1\0\0\0\2\u00bb\1\0\0\0\2\u00bd\1\0\0\0\2\u00bf\1\0\0"+
		"\0\2\u00c1\1\0\0\0\2\u00c3\1\0\0\0\2\u00c5\1\0\0\0\2\u00c7\1\0\0\0\2\u00c9"+
		"\1\0\0\0\3\u00cb\1\0\0\0\5\u00d1\1\0\0\0\7\u00dc\1\0\0\0\t\u00e2\1\0\0"+
		"\0\13\u00e8\1\0\0\0\r\u00f4\1\0\0\0\17\u0103\1\0\0\0\21\u0110\1\0\0\0"+
		"\23\u0114\1\0\0\0\25\u0118\1\0\0\0\27\u0129\1\0\0\0\31\u0139\1\0\0\0\33"+
		"\u0140\1\0\0\0\35\u0149\1\0\0\0\37\u014f\1\0\0\0!\u0156\1\0\0\0#\u015e"+
		"\1\0\0\0%\u0168\1\0\0\0\'\u016f\1\0\0\0)\u0177\1\0\0\0+\u017f\1\0\0\0"+
		"-\u0186\1\0\0\0/\u018d\1\0\0\0\61\u0193\1\0\0\0\63\u019b\1\0\0\0\65\u01a4"+
		"\1\0\0\0\67\u01a9\1\0\0\09\u01ab\1\0\0\0;\u01ae\1\0\0\0=\u01b0\1\0\0\0"+
		"?\u01b2\1\0\0\0A\u01b4\1\0\0\0C\u01b6\1\0\0\0E\u01b9\1\0\0\0G\u01bb\1"+
		"\0\0\0I\u01bd\1\0\0\0K\u01bf\1\0\0\0M\u01c1\1\0\0\0O\u01c3\1\0\0\0Q\u01c5"+
		"\1\0\0\0S\u01c8\1\0\0\0U\u01ca\1\0\0\0W\u01cc\1\0\0\0Y\u01ce\1\0\0\0["+
		"\u01d1\1\0\0\0]\u01d5\1\0\0\0_\u01d7\1\0\0\0a\u01d9\1\0\0\0c\u01db\1\0"+
		"\0\0e\u01dd\1\0\0\0g\u01f4\1\0\0\0i\u0210\1\0\0\0k\u0212\1\0\0\0m\u021f"+
		"\1\0\0\0o\u022c\1\0\0\0q\u0232\1\0\0\0s\u0236\1\0\0\0u\u0245\1\0\0\0w"+
		"\u0247\1\0\0\0y\u024f\1\0\0\0{\u0263\1\0\0\0}\u026d\1\0\0\0\177\u0275"+
		"\1\0\0\0\u0081\u0281\1\0\0\0\u0083\u0283\1\0\0\0\u0085\u0285\1\0\0\0\u0087"+
		"\u0287\1\0\0\0\u0089\u0289\1\0\0\0\u008b\u028b\1\0\0\0\u008d\u028d\1\0"+
		"\0\0\u008f\u028f\1\0\0\0\u0091\u0291\1\0\0\0\u0093\u0295\1\0\0\0\u0095"+
		"\u02a2\1\0\0\0\u0097\u02a6\1\0\0\0\u0099\u02ac\1\0\0\0\u009b\u02b8\1\0"+
		"\0\0\u009d\u02ba\1\0\0\0\u009f\u02be\1\0\0\0\u00a1\u02c2\1\0\0\0\u00a3"+
		"\u02c4\1\0\0\0\u00a5\u02c6\1\0\0\0\u00a7\u02c8\1\0\0\0\u00a9\u02ca\1\0"+
		"\0\0\u00ab\u02cc\1\0\0\0\u00ad\u02ce\1\0\0\0\u00af\u02d0\1\0\0\0\u00b1"+
		"\u02d2\1\0\0\0\u00b3\u02d4\1\0\0\0\u00b5\u02d7\1\0\0\0\u00b7\u02d9\1\0"+
		"\0\0\u00b9\u02db\1\0\0\0\u00bb\u02e1\1\0\0\0\u00bd\u02e5\1\0\0\0\u00bf"+
		"\u02eb\1\0\0\0\u00c1\u02f1\1\0\0\0\u00c3\u02f9\1\0\0\0\u00c5\u02ff\1\0"+
		"\0\0\u00c7\u030b\1\0\0\0\u00c9\u030d\1\0\0\0\u00cb\u00cc\5/\0\0\u00cc"+
		"\u00cd\5*\0\0\u00cd\u00ce\5*\0\0\u00ce\u00cf\1\0\0\0\u00cf\u00d0\3\t\3"+
		"\0\u00d0\4\1\0\0\0\u00d1\u00d2\5/\0\0\u00d2\u00d3\5*\0\0\u00d3\u00d4\1"+
		"\0\0\0\u00d4\u00d5\3\t\3\0\u00d5\u00d6\1\0\0\0\u00d6\u00d7\6\1\2\0\u00d7"+
		"\6\1\0\0\0\u00d8\u00dd\3\5\1\0\u00da\u00dd\3\13\4\0\u00dc\u00d8\1\0\0"+
		"\0\u00dc\u00da\1\0\0\0\u00dd\b\1\0\0\0\u00de\u00e1\t\0\0\0\u00e0\u00de"+
		"\1\0\0\0\u00e1\u00e4\1\0\0\0\u00e2\u00e3\1\0\0\0\u00e2\u00e0\1\0\0\0\u00e3"+
		"\u00e5\1\0\0\0\u00e4\u00e2\1\0\0\0\u00e5\u00e6\5*\0\0\u00e6\u00e7\5/\0"+
		"\0\u00e7\n\1\0\0\0\u00e8\u00e9\5/\0\0\u00e9\u00ea\5/\0\0\u00ea\u00ef\1"+
		"\0\0\0\u00eb\u00ee\b\0\0\0\u00ed\u00eb\1\0\0\0\u00ee\u00f1\1\0\0\0\u00ef"+
		"\u00ed\1\0\0\0\u00ef\u00f0\1\0\0\0\u00f0\u00f2\1\0\0\0\u00f1\u00ef\1\0"+
		"\0\0\u00f2\u00f3\6\4\0\0\u00f3\f\1\0\0\0\u00f4\u00fe\5\"\0\0\u00f6\u00f8"+
		"\5\\\0\0\u00f8\u00fd\t\0\0\0\u00fa\u00fd\b\1\0\0\u00fc\u00f6\1\0\0\0\u00fc"+
		"\u00fa\1\0\0\0\u00fd\u0100\1\0\0\0\u00fe\u00fc\1\0\0\0\u00fe\u00ff\1\0"+
		"\0\0\u00ff\u0101\1\0\0\0\u0100\u00fe\1\0\0\0\u0101\u0102\5\"\0\0\u0102"+
		"\16\1\0\0\0\u0103\u0104\5<\0\0\u0104\u0105\5<\0\0\u0105\u010a\1\0\0\0"+
		"\u0106\u0109\t\0\0\0\u0108\u0106\1\0\0\0\u0109\u010c\1\0\0\0\u010a\u010b"+
		"\1\0\0\0\u010a\u0108\1\0\0\0\u010b\u010d\1\0\0\0\u010c\u010a\1\0\0\0\u010d"+
		"\u010e\5>\0\0\u010e\u010f\5>\0\0\u010f\20\1\0\0\0\u0110\u0111\5[\0\0\u0111"+
		"\u0112\1\0\0\0\u0112\u0113\6\7\3\0\u0113\22\1\0\0\0\u0114\u0115\5{\0\0"+
		"\u0115\u0116\1\0\0\0\u0116\u0117\6\b\4\0\u0117\24\1\0\0\0\u0118\u0119"+
		"\5o\0\0\u0119\u011a\5p\0\0\u011a\u011b\5t\0\0\u011b\u011c\5i\0\0\u011c"+
		"\u011d\5o\0\0\u011d\u011e\5n\0\0\u011e\u011f\5s\0\0\u011f\u0124\1\0\0"+
		"\0\u0120\u0123\3\u0081?\0\u0122\u0120\1\0\0\0\u0123\u0126\1\0\0\0\u0124"+
		"\u0122\1\0\0\0\u0124\u0125\1\0\0\0\u0125\u0127\1\0\0\0\u0126\u0124\1\0"+
		"\0\0\u0127\u0128\5{\0\0\u0128\26\1\0\0\0\u0129\u012a\5t\0\0\u012a\u012b"+
		"\5o\0\0\u012b\u012c\5k\0\0\u012c\u012d\5e\0\0\u012d\u012e\5n\0\0\u012e"+
		"\u012f\5s\0\0\u012f\u0134\1\0\0\0\u0130\u0133\3\u0081?\0\u0132\u0130\1"+
		"\0\0\0\u0133\u0136\1\0\0\0\u0134\u0132\1\0\0\0\u0134\u0135\1\0\0\0\u0135"+
		"\u0137\1\0\0\0\u0136\u0134\1\0\0\0\u0137\u0138\5{\0\0\u0138\30\1\0\0\0"+
		"\u0139\u013a\5i\0\0\u013a\u013b\5m\0\0\u013b\u013c\5p\0\0\u013c\u013d"+
		"\5o\0\0\u013d\u013e\5r\0\0\u013e\u013f\5t\0\0\u013f\32\1\0\0\0\u0140\u0141"+
		"\5f\0\0\u0141\u0142\5r\0\0\u0142\u0143\5a\0\0\u0143\u0144\5g\0\0\u0144"+
		"\u0145\5m\0\0\u0145\u0146\5e\0\0\u0146\u0147\5n\0\0\u0147\u0148\5t\0\0"+
		"\u0148\34\1\0\0\0\u0149\u014a\5l\0\0\u014a\u014b\5e\0\0\u014b\u014c\5"+
		"x\0\0\u014c\u014d\5e\0\0\u014d\u014e\5r\0\0\u014e\36\1\0\0\0\u014f\u0150"+
		"\5p\0\0\u0150\u0151\5a\0\0\u0151\u0152\5r\0\0\u0152\u0153\5s\0\0\u0153"+
		"\u0154\5e\0\0\u0154\u0155\5r\0\0\u0155 \1\0\0\0\u0156\u0157\5g\0\0\u0157"+
		"\u0158\5r\0\0\u0158\u0159\5a\0\0\u0159\u015a\5m\0\0\u015a\u015b\5m\0\0"+
		"\u015b\u015c\5a\0\0\u015c\u015d\5r\0\0\u015d\"\1\0\0\0\u015e\u015f\5p"+
		"\0\0\u015f\u0160\5r\0\0\u0160\u0161\5o\0\0\u0161\u0162\5t\0\0\u0162\u0163"+
		"\5e\0\0\u0163\u0164\5c\0\0\u0164\u0165\5t\0\0\u0165\u0166\5e\0\0\u0166"+
		"\u0167\5d\0\0\u0167$\1\0\0\0\u0168\u0169\5p\0\0\u0169\u016a\5u\0\0\u016a"+
		"\u016b\5b\0\0\u016b\u016c\5l\0\0\u016c\u016d\5i\0\0\u016d\u016e\5c\0\0"+
		"\u016e&\1\0\0\0\u016f\u0170\5p\0\0\u0170\u0171\5r\0\0\u0171\u0172\5i\0"+
		"\0\u0172\u0173\5v\0\0\u0173\u0174\5a\0\0\u0174\u0175\5t\0\0\u0175\u0176"+
		"\5e\0\0\u0176(\1\0\0\0\u0177\u0178\5r\0\0\u0178\u0179\5e\0\0\u0179\u017a"+
		"\5t\0\0\u017a\u017b\5u\0\0\u017b\u017c\5r\0\0\u017c\u017d\5n\0\0\u017d"+
		"\u017e\5s\0\0\u017e*\1\0\0\0\u017f\u0180\5l\0\0\u0180\u0181\5o\0\0\u0181"+
		"\u0182\5c\0\0\u0182\u0183\5a\0\0\u0183\u0184\5l\0\0\u0184\u0185\5s\0\0"+
		"\u0185,\1\0\0\0\u0186\u0187\5t\0\0\u0187\u0188\5h\0\0\u0188\u0189\5r\0"+
		"\0\u0189\u018a\5o\0\0\u018a\u018b\5w\0\0\u018b\u018c\5s\0\0\u018c.\1\0"+
		"\0\0\u018d\u018e\5c\0\0\u018e\u018f\5a\0\0\u018f\u0190\5t\0\0\u0190\u0191"+
		"\5c\0\0\u0191\u0192\5h\0\0\u0192\60\1\0\0\0\u0193\u0194\5f\0\0\u0194\u0195"+
		"\5i\0\0\u0195\u0196\5n\0\0\u0196\u0197\5a\0\0\u0197\u0198\5l\0\0\u0198"+
		"\u0199\5l\0\0\u0199\u019a\5y\0\0\u019a\62\1\0\0\0\u019b\u019c\5t\0\0\u019c"+
		"\u019d\5e\0\0\u019d\u019e\5m\0\0\u019e\u019f\5p\0\0\u019f\u01a0\5l\0\0"+
		"\u01a0\u01a1\5a\0\0\u01a1\u01a2\5t\0\0\u01a2\u01a3\5e\0\0\u01a3\64\1\0"+
		"\0\0\u01a4\u01a5\5m\0\0\u01a5\u01a6\5o\0\0\u01a6\u01a7\5d\0\0\u01a7\u01a8"+
		"\5e\0\0\u01a8\66\1\0\0\0\u01a9\u01aa\5:\0\0\u01aa8\1\0\0\0\u01ab\u01ac"+
		"\5:\0\0\u01ac\u01ad\5:\0\0\u01ad:\1\0\0\0\u01ae\u01af\5,\0\0\u01af<\1"+
		"\0\0\0\u01b0\u01b1\5;\0\0\u01b1>\1\0\0\0\u01b2\u01b3\5(\0\0\u01b3@\1\0"+
		"\0\0\u01b4\u01b5\5)\0\0\u01b5B\1\0\0\0\u01b6\u01b7\5-\0\0\u01b7\u01b8"+
		"\5>\0\0\u01b8D\1\0\0\0\u01b9\u01ba\5<\0\0\u01baF\1\0\0\0\u01bb\u01bc\5"+
		">\0\0\u01bcH\1\0\0\0\u01bd\u01be\5=\0\0\u01beJ\1\0\0\0\u01bf\u01c0\5?"+
		"\0\0\u01c0L\1\0\0\0\u01c1\u01c2\5*\0\0\u01c2N\1\0\0\0\u01c3\u01c4\5+\0"+
		"\0\u01c4P\1\0\0\0\u01c5\u01c6\5+\0\0\u01c6\u01c7\5=\0\0\u01c7R\1\0\0\0"+
		"\u01c8\u01c9\5|\0\0\u01c9T\1\0\0\0\u01ca\u01cb\5$\0\0\u01cbV\1\0\0\0\u01cc"+
		"\u01cd\5.\0\0\u01cdX\1\0\0\0\u01ce\u01cf\5.\0\0\u01cf\u01d0\5.\0\0\u01d0"+
		"Z\1\0\0\0\u01d1\u01d2\5.\0\0\u01d2\u01d3\5.\0\0\u01d3\u01d4\5.\0\0\u01d4"+
		"\\\1\0\0\0\u01d5\u01d6\5@\0\0\u01d6^\1\0\0\0\u01d7\u01d8\5#\0\0\u01d8"+
		"`\1\0\0\0\u01d9\u01da\5~\0\0\u01dab\1\0\0\0\u01db\u01dc\5}\0\0\u01dcd"+
		"\1\0\0\0\u01dd\u01e3\3i\63\0\u01df\u01e2\3g\62\0\u01e1\u01df\1\0\0\0\u01e2"+
		"\u01e5\1\0\0\0\u01e3\u01e1\1\0\0\0\u01e3\u01e4\1\0\0\0\u01e4\u01e6\1\0"+
		"\0\0\u01e5\u01e3\1\0\0\0\u01e6\u01e7\6\61\1\0\u01e7f\1\0\0\0\u01e8\u01f5"+
		"\3i\63\0\u01ea\u01f5\2\609\0\u01ec\u01f5\5_\0\0\u01ee\u01f5\5\u00b7\0"+
		"\0\u01f0\u01f5\2\u0300\u036f\0\u01f2\u01f5\2\u203f\u2040\0\u01f4\u01e8"+
		"\1\0\0\0\u01f4\u01ea\1\0\0\0\u01f4\u01ec\1\0\0\0\u01f4\u01ee\1\0\0\0\u01f4"+
		"\u01f0\1\0\0\0\u01f4\u01f2\1\0\0\0\u01f5h\1\0\0\0\u01f6\u0211\2AZ\0\u01f8"+
		"\u0211\2az\0\u01fa\u0211\2\u00c0\u00d6\0\u01fc\u0211\2\u00d8\u00f6\0\u01fe"+
		"\u0211\2\u00f8\u02ff\0\u0200\u0211\2\u0370\u037d\0\u0202\u0211\2\u037f"+
		"\u1fff\0\u0204\u0211\2\u200c\u200d\0\u0206\u0211\2\u2070\u218f\0\u0208"+
		"\u0211\2\u2c00\u2fef\0\u020a\u0211\2\u3001\ud7ff\0\u020c\u0211\2\uf900"+
		"\ufdcf\0\u020e\u0211\2\ufdf0\ufffd\0\u0210\u01f6\1\0\0\0\u0210\u01f8\1"+
		"\0\0\0\u0210\u01fa\1\0\0\0\u0210\u01fc\1\0\0\0\u0210\u01fe\1\0\0\0\u0210"+
		"\u0200\1\0\0\0\u0210\u0202\1\0\0\0\u0210\u0204\1\0\0\0\u0210\u0206\1\0"+
		"\0\0\u0210\u0208\1\0\0\0\u0210\u020a\1\0\0\0\u0210\u020c\1\0\0\0\u0210"+
		"\u020e\1\0\0\0\u0211j\1\0\0\0\u0212\u021a\5\'\0\0\u0214\u0219\3o\66\0"+
		"\u0216\u0219\b\2\0\0\u0218\u0214\1\0\0\0\u0218\u0216\1\0\0\0\u0219\u021c"+
		"\1\0\0\0\u021a\u0218\1\0\0\0\u021a\u021b\1\0\0\0\u021b\u021d\1\0\0\0\u021c"+
		"\u021a\1\0\0\0\u021d\u021e\5\'\0\0\u021el\1\0\0\0\u021f\u0227\5\"\0\0"+
		"\u0221\u0226\3o\66\0\u0223\u0226\b\3\0\0\u0225\u0221\1\0\0\0\u0225\u0223"+
		"\1\0\0\0\u0226\u0229\1\0\0\0\u0227\u0225\1\0\0\0\u0227\u0228\1\0\0\0\u0228"+
		"\u022a\1\0\0\0\u0229\u0227\1\0\0\0\u022a\u022b\5\"\0\0\u022bn\1\0\0\0"+
		"\u022c\u022e\5\\\0\0\u022e\u022f\t\0\0\0\u022fp\1\0\0\0\u0230\u0233\2"+
		"\609\0\u0232\u0230\1\0\0\0\u0233\u0234\1\0\0\0\u0234\u0232\1\0\0\0\u0234"+
		"\u0235\1\0\0\0\u0235r\1\0\0\0\u0236\u0240\5\'\0\0\u0238\u023d\3w:\0\u023a"+
		"\u023d\b\4\0\0\u023c\u0238\1\0\0\0\u023c\u023a\1\0\0\0\u023d\u023f\1\0"+
		"\0\0\u023e\u023c\1\0\0\0\u023f\u0242\1\0\0\0\u0240\u023e\1\0\0\0\u0240"+
		"\u0241\1\0\0\0\u0241\u0243\1\0\0\0\u0242\u0240\1\0\0\0\u0243\u0244\5\'"+
		"\0\0\u0244t\1\0\0\0\u0245\u0246\7\5\0\0\u0246v\1\0\0\0\u0247\u024d\5\\"+
		"\0\0\u0249\u024e\7\6\0\0\u024b\u024e\3y;\0\u024d\u0249\1\0\0\0\u024d\u024b"+
		"\1\0\0\0\u024ex\1\0\0\0\u024f\u025f\5u\0\0\u0251\u025d\3u9\0\u0253\u025b"+
		"\3u9\0\u0255\u0259\3u9\0\u0257\u025a\3u9\0\u0259\u0257\1\0\0\0\u0259\u025a"+
		"\1\0\0\0\u025a\u025c\1\0\0\0\u025b\u0255\1\0\0\0\u025b\u025c\1\0\0\0\u025c"+
		"\u025e\1\0\0\0\u025d\u0253\1\0\0\0\u025d\u025e\1\0\0\0\u025e\u0260\1\0"+
		"\0\0\u025f\u0251\1\0\0\0\u025f\u0260\1\0\0\0\u0260z\1\0\0\0\u0261\u0264"+
		"\7\7\0\0\u0263\u0261\1\0\0\0\u0264\u0265\1\0\0\0\u0265\u0263\1\0\0\0\u0265"+
		"\u0266\1\0\0\0\u0266\u0267\1\0\0\0\u0267\u0268\6<\5\0\u0268|\1\0\0\0\u0269"+
		"\u026e\5\n\0\0\u026b\u026e\5\r\0\0\u026d\u0269\1\0\0\0\u026d\u026b\1\0"+
		"\0\0\u026e~\1\0\0\0\u026f\u0276\5 \0\0\u0271\u0276\5\t\0\0\u0273\u0276"+
		"\5\f\0\0\u0275\u026f\1\0\0\0\u0275\u0271\1\0\0\0\u0275\u0273\1\0\0\0\u0276"+
		"\u0080\1\0\0\0\u0277\u0282\5 \0\0\u0279\u0282\5\t\0\0\u027b\u0282\5\f"+
		"\0\0\u027d\u0282\5\n\0\0\u027f\u0282\5\r\0\0\u0281\u0277\1\0\0\0\u0281"+
		"\u0279\1\0\0\0\u0281\u027b\1\0\0\0\u0281\u027d\1\0\0\0\u0281\u027f\1\0"+
		"\0\0\u0282\u0082\1\0\0\0\u0283\u0284\t\0\0\0\u0284\u0084\1\0\0\0\u0285"+
		"\u0286\5<\0\0\u0286\u0086\1\0\0\0\u0287\u0288\5>\0\0\u0288\u0088\1\0\0"+
		"\0\u0289\u028a\5(\0\0\u028a\u008a\1\0\0\0\u028b\u028c\5)\0\0\u028c\u008c"+
		"\1\0\0\0\u028d\u028e\5=\0\0\u028e\u008e\1\0\0\0\u028f\u0290\5,\0\0\u0290"+
		"\u0090\1\0\0\0\u0291\u0293\5\\\0\0\u0293\u0294\t\0\0\0\u0294\u0092\1\0"+
		"\0\0\u0295\u029b\7\b\0\0\u0297\u029a\7\t\0\0\u0299\u0297\1\0\0\0\u029a"+
		"\u029d\1\0\0\0\u029b\u0299\1\0\0\0\u029b\u029c\1\0\0\0\u029c\u0094\1\0"+
		"\0\0\u029d\u029b\1\0\0\0\u029e\u02a3\3m\65\0\u02a0\u02a3\3k\64\0\u02a2"+
		"\u029e\1\0\0\0\u02a2\u02a0\1\0\0\0\u02a3\u0096\1\0\0\0\u02a4\u02a7\b\n"+
		"\0\0\u02a6\u02a4\1\0\0\0\u02a7\u02a8\1\0\0\0\u02a8\u02a6\1\0\0\0\u02a8"+
		"\u02a9\1\0\0\0\u02a9\u0098\1\0\0\0\u02aa\u02ad\7\13\0\0\u02ac\u02aa\1"+
		"\0\0\0\u02ad\u02ae\1\0\0\0\u02ae\u02ac\1\0\0\0\u02ae\u02af\1\0\0\0\u02af"+
		"\u009a\1\0\0\0\u02b0\u02b4\5\r\0\0\u02b2\u02b5\5\n\0\0\u02b4\u02b2\1\0"+
		"\0\0\u02b4\u02b5\1\0\0\0\u02b5\u02b9\1\0\0\0\u02b6\u02b9\5\n\0\0\u02b8"+
		"\u02b0\1\0\0\0\u02b8\u02b6\1\0\0\0\u02b9\u009c\1\0\0\0\u02ba\u02bb\5]"+
		"\0\0\u02bb\u02bc\1\0\0\0\u02bc\u02bd\6M\6\0\u02bd\u009e\1\0\0\0\u02be"+
		"\u02bf\5{\0\0\u02bf\u02c0\1\0\0\0\u02c0\u02c1\6N\7\0\u02c1\u00a0\1\0\0"+
		"\0\u02c2\u02c3\5.\0\0\u02c3\u00a2\1\0\0\0\u02c4\u02c5\5<\0\0\u02c5\u00a4"+
		"\1\0\0\0\u02c6\u02c7\5>\0\0\u02c7\u00a6\1\0\0\0\u02c8\u02c9\5(\0\0\u02c9"+
		"\u00a8\1\0\0\0\u02ca\u02cb\5)\0\0\u02cb\u00aa\1\0\0\0\u02cc\u02cd\5[\0"+
		"\0\u02cd\u00ac\1\0\0\0\u02ce\u02cf\5]\0\0\u02cf\u00ae\1\0\0\0\u02d0\u02d1"+
		"\5=\0\0\u02d1\u00b0\1\0\0\0\u02d2\u02d3\5,\0\0\u02d3\u00b2\1\0\0\0\u02d4"+
		"\u02d5\5:\0\0\u02d5\u02d6\5:\0\0\u02d6\u00b4\1\0\0\0\u02d7\u02d8\5:\0"+
		"\0\u02d8\u00b6\1\0\0\0\u02d9\u02da\5-\0\0\u02da\u00b8\1\0\0\0\u02db\u02dd"+
		"\5\\\0\0\u02dd\u02de\t\0\0\0\u02de\u00ba\1\0\0\0\u02df\u02e2\7\f\0\0\u02e1"+
		"\u02df\1\0\0\0\u02e2\u02e3\1\0\0\0\u02e3\u02e1\1\0\0\0\u02e3\u02e4\1\0"+
		"\0\0\u02e4\u00bc\1\0\0\0\u02e5\u02e9\5$\0\0\u02e7\u02ea\3\u00bb\\\0\u02e9"+
		"\u02e7\1\0\0\0\u02e9\u02ea\1\0\0\0\u02ea\u00be\1\0\0\0\u02eb\u02ec\3\7"+
		"\2\0\u02ec\u00c0\1\0\0\0\u02ed\u02f2\3m\65\0\u02ef\u02f2\3k\64\0\u02f1"+
		"\u02ed\1\0\0\0\u02f1\u02ef\1\0\0\0\u02f2\u00c2\1\0\0\0\u02f3\u02f5\5/"+
		"\0\0\u02f5\u02fa\b\r\0\0\u02f7\u02fa\b\16\0\0\u02f9\u02f3\1\0\0\0\u02f9"+
		"\u02f7\1\0\0\0\u02fa\u02fb\1\0\0\0\u02fb\u02f9\1\0\0\0\u02fb\u02fc\1\0"+
		"\0\0\u02fc\u00c4\1\0\0\0\u02fd\u0300\7\17\0\0\u02ff\u02fd\1\0\0\0\u0300"+
		"\u0301\1\0\0\0\u0301\u02ff\1\0\0\0\u0301\u0302\1\0\0\0\u0302\u00c6\1\0"+
		"\0\0\u0303\u0307\5\r\0\0\u0305\u0308\5\n\0\0\u0307\u0305\1\0\0\0\u0307"+
		"\u0308\1\0\0\0\u0308\u030c\1\0\0\0\u0309\u030c\5\n\0\0\u030b\u0303\1\0"+
		"\0\0\u030b\u0309\1\0\0\0\u030c\u00c8\1\0\0\0\u030d\u030e\5}\0\0\u030e"+
		"\u030f\1\0\0\0\u030f\u0310\6c\b\0\u0310\u00ca\1\0\0\0,\0\1\1\1\2\1\u00dc"+
		"\1\u00e2\0\u00ef\1\u00fc\1\u00fe\1\u010a\0\u0124\1\u0134\1\u01e3\1\u01f4"+
		"\1\u0210\1\u0218\1\u021a\1\u0225\1\u0227\1\u0234\1\u023c\1\u0240\1\u024d"+
		"\1\u0259\1\u025b\1\u025d\1\u025f\1\u0265\1\u026d\1\u0275\1\u0281\1\u029b"+
		"\1\u02a2\1\u02a8\1\u02ae\1\u02b4\1\u02b8\1\u02e3\1\u02e9\1\u02f1\1\u02f9"+
		"\1\u02fb\1\u0301\1\u0307\1\u030b\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}