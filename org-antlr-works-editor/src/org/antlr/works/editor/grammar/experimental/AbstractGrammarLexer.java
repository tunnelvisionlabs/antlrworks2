// $ANTLR ANTLRVersion> AbstractGrammarLexer.java generatedTimestamp>
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

public abstract class AbstractGrammarLexer extends Lexer {
	public static final int
		TOKEN_REF=1, RULE_REF=2, LEXER_CHAR_SET=3, DOC_COMMENT=4, BLOCK_COMMENT=5, 
		LINE_COMMENT=6, DOUBLE_QUOTE_STRING_LITERAL=7, DOUBLE_ANGLE_STRING_LITERAL=8, 
		BEGIN_ARG_ACTION=9, BEGIN_ACTION=10, OPTIONS=11, TOKENS=12, IMPORT=13, 
		FRAGMENT=14, LEXER=15, PARSER=16, GRAMMAR=17, PROTECTED=18, PUBLIC=19, 
		PRIVATE=20, RETURNS=21, LOCALS=22, THROWS=23, CATCH=24, FINALLY=25, TEMPLATE=26, 
		MODE=27, COLON=28, COLONCOLON=29, COMMA=30, SEMI=31, LPAREN=32, RPAREN=33, 
		RARROW=34, LT=35, GT=36, ASSIGN=37, QUESTION=38, STAR=39, PLUS=40, PLUS_ASSIGN=41, 
		OR=42, DOLLAR=43, DOT=44, RANGE=45, AT=46, POUND=47, NOT=48, RBRACE=49, 
		ID=50, INT=51, STRING_LITERAL=52, WS=53, ERRCHAR=54, ARG_ACTION_LT=55, 
		ARG_ACTION_GT=56, ARG_ACTION_LPAREN=57, ARG_ACTION_RPAREN=58, ARG_ACTION_EQUALS=59, 
		ARG_ACTION_COMMA=60, ARG_ACTION_ESCAPE=61, ARG_ACTION_WORD=62, ARG_ACTION_ELEMENT=63, 
		ARG_ACTION_TEXT=64, ARG_ACTION_WS=65, ARG_ACTION_NEWLINE=66, END_ARG_ACTION=67, 
		ACTION_DOT=68, ACTION_LT=69, ACTION_GT=70, ACTION_LPAREN=71, ACTION_RPAREN=72, 
		ACTION_LBRACK=73, ACTION_RBRACK=74, ACTION_EQUALS=75, ACTION_COMMA=76, 
		ACTION_COLON2=77, ACTION_COLON=78, ACTION_MINUS=79, ACTION_ESCAPE=80, 
		ACTION_WORD=81, ACTION_REFERENCE=82, ACTION_COMMENT=83, ACTION_LITERAL=84, 
		ACTION_TEXT=85, ACTION_WS=86, ACTION_NEWLINE=87, END_ACTION=88;
	public static final int ArgAction = 1;
	public static final int Action = 2;
	public static final int LexerCharSet = 3;
	public static String[] modeNames = {
		"DEFAULT_MODE", "ArgAction", "Action", "LexerCharSet"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"TOKEN_REF", "RULE_REF", "LEXER_CHAR_SET", "DOC_COMMENT", "BLOCK_COMMENT", 
		"LINE_COMMENT", "DOUBLE_QUOTE_STRING_LITERAL", "DOUBLE_ANGLE_STRING_LITERAL", 
		"BEGIN_ARG_ACTION", "'{'", "OPTIONS", "TOKENS", "'import'", "'fragment'", 
		"'lexer'", "'parser'", "'grammar'", "'protected'", "'public'", "'private'", 
		"'returns'", "'locals'", "'throws'", "'catch'", "'finally'", "'template'", 
		"'mode'", "COLON", "COLONCOLON", "COMMA", "';'", "LPAREN", "RPAREN", "'->'", 
		"LT", "GT", "ASSIGN", "'?'", "'*'", "'+'", "'+='", "'|'", "'$'", "DOT", 
		"'..'", "'@'", "'#'", "'~'", "RBRACE", "ID", "INT", "STRING_LITERAL", 
		"WS", "ERRCHAR", "ARG_ACTION_LT", "ARG_ACTION_GT", "ARG_ACTION_LPAREN", 
		"ARG_ACTION_RPAREN", "ARG_ACTION_EQUALS", "ARG_ACTION_COMMA", "ARG_ACTION_ESCAPE", 
		"ARG_ACTION_WORD", "ARG_ACTION_ELEMENT", "ARG_ACTION_TEXT", "ARG_ACTION_WS", 
		"ARG_ACTION_NEWLINE", "END_ARG_ACTION", "ACTION_DOT", "ACTION_LT", "ACTION_GT", 
		"ACTION_LPAREN", "ACTION_RPAREN", "ACTION_LBRACK", "ACTION_RBRACK", "ACTION_EQUALS", 
		"ACTION_COMMA", "ACTION_COLON2", "ACTION_COLON", "'-'", "ACTION_ESCAPE", 
		"ACTION_WORD", "ACTION_REFERENCE", "ACTION_COMMENT", "ACTION_LITERAL", 
		"ACTION_TEXT", "ACTION_WS", "ACTION_NEWLINE", "END_ACTION"
	};
	public static final String[] ruleNames = {
		"DOC_COMMENT", "BLOCK_COMMENT", "COMMENT", "BLOCK_COMMENT_BODY", "LINE_COMMENT", 
		"DOUBLE_QUOTE_STRING_LITERAL", "DOUBLE_ANGLE_STRING_LITERAL", "BEGIN_ARG_ACTION", 
		"BEGIN_ACTION", "OPTIONS", "TOKENS", "IMPORT", "FRAGMENT", "LEXER", "PARSER", 
		"GRAMMAR", "PROTECTED", "PUBLIC", "PRIVATE", "RETURNS", "LOCALS", "THROWS", 
		"CATCH", "FINALLY", "TEMPLATE", "MODE", "COLON", "COLONCOLON", "COMMA", 
		"SEMI", "LPAREN", "RPAREN", "RARROW", "LT", "GT", "ASSIGN", "QUESTION", 
		"STAR", "PLUS", "PLUS_ASSIGN", "OR", "DOLLAR", "DOT", "RANGE", "AT", "POUND", 
		"NOT", "RBRACE", "ID", "NameChar", "NameStartChar", "ACTION_CHAR_LITERAL", 
		"ACTION_STRING_LITERAL", "ACTION_ESC", "INT", "STRING_LITERAL", "HEX_DIGIT", 
		"ESC_SEQ", "UNICODE_ESC", "WS", "NLCHARS", "WSCHARS", "WSNLCHARS", "ERRCHAR", 
		"ARG_ACTION_LT", "ARG_ACTION_GT", "ARG_ACTION_LPAREN", "ARG_ACTION_RPAREN", 
		"ARG_ACTION_EQUALS", "ARG_ACTION_COMMA", "ARG_ACTION_ESCAPE", "ARG_ACTION_WORD", 
		"ARG_ACTION_ELEMENT", "ARG_ACTION_TEXT", "ARG_ACTION_WS", "ARG_ACTION_NEWLINE", 
		"END_ARG_ACTION", "NESTED_ACTION", "ACTION_DOT", "ACTION_LT", "ACTION_GT", 
		"ACTION_LPAREN", "ACTION_RPAREN", "ACTION_LBRACK", "ACTION_RBRACK", "ACTION_EQUALS", 
		"ACTION_COMMA", "ACTION_COLON2", "ACTION_COLON", "ACTION_MINUS", "ACTION_ESCAPE", 
		"ACTION_WORD", "ACTION_REFERENCE", "ACTION_COMMENT", "ACTION_LITERAL", 
		"ACTION_TEXT", "ACTION_WS", "ACTION_NEWLINE", "END_ACTION", "LEXER_CHAR_SET_BODY", 
		"END_LEXER_CHAR_SET"
	};


	protected abstract void handleBeginArgAction();


	public AbstractGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN);
	}

	@Override
	public String getGrammarFileName() { return "GrammarLexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }


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

			case 44 : AT_action(_localctx, actionIndex); break;

			case 45 : POUND_action(_localctx, actionIndex); break;

			case 46 : NOT_action(_localctx, actionIndex); break;

			case 47 : RBRACE_action(_localctx, actionIndex); break;

			case 48 : ID_action(_localctx, actionIndex); break;

			case 49 : NameChar_action(_localctx, actionIndex); break;

			case 50 : NameStartChar_action(_localctx, actionIndex); break;

			case 51 : ACTION_CHAR_LITERAL_action(_localctx, actionIndex); break;

			case 52 : ACTION_STRING_LITERAL_action(_localctx, actionIndex); break;

			case 53 : ACTION_ESC_action(_localctx, actionIndex); break;

			case 54 : INT_action(_localctx, actionIndex); break;

			case 55 : STRING_LITERAL_action(_localctx, actionIndex); break;

			case 56 : HEX_DIGIT_action(_localctx, actionIndex); break;

			case 57 : ESC_SEQ_action(_localctx, actionIndex); break;

			case 58 : UNICODE_ESC_action(_localctx, actionIndex); break;

			case 59 : WS_action(_localctx, actionIndex); break;

			case 60 : NLCHARS_action(_localctx, actionIndex); break;

			case 61 : WSCHARS_action(_localctx, actionIndex); break;

			case 62 : WSNLCHARS_action(_localctx, actionIndex); break;

			case 63 : ERRCHAR_action(_localctx, actionIndex); break;

			case 64 : ARG_ACTION_LT_action(_localctx, actionIndex); break;

			case 65 : ARG_ACTION_GT_action(_localctx, actionIndex); break;

			case 66 : ARG_ACTION_LPAREN_action(_localctx, actionIndex); break;

			case 67 : ARG_ACTION_RPAREN_action(_localctx, actionIndex); break;

			case 68 : ARG_ACTION_EQUALS_action(_localctx, actionIndex); break;

			case 69 : ARG_ACTION_COMMA_action(_localctx, actionIndex); break;

			case 70 : ARG_ACTION_ESCAPE_action(_localctx, actionIndex); break;

			case 71 : ARG_ACTION_WORD_action(_localctx, actionIndex); break;

			case 72 : ARG_ACTION_ELEMENT_action(_localctx, actionIndex); break;

			case 73 : ARG_ACTION_TEXT_action(_localctx, actionIndex); break;

			case 74 : ARG_ACTION_WS_action(_localctx, actionIndex); break;

			case 75 : ARG_ACTION_NEWLINE_action(_localctx, actionIndex); break;

			case 76 : END_ARG_ACTION_action(_localctx, actionIndex); break;

			case 77 : NESTED_ACTION_action(_localctx, actionIndex); break;

			case 78 : ACTION_DOT_action(_localctx, actionIndex); break;

			case 79 : ACTION_LT_action(_localctx, actionIndex); break;

			case 80 : ACTION_GT_action(_localctx, actionIndex); break;

			case 81 : ACTION_LPAREN_action(_localctx, actionIndex); break;

			case 82 : ACTION_RPAREN_action(_localctx, actionIndex); break;

			case 83 : ACTION_LBRACK_action(_localctx, actionIndex); break;

			case 84 : ACTION_RBRACK_action(_localctx, actionIndex); break;

			case 85 : ACTION_EQUALS_action(_localctx, actionIndex); break;

			case 86 : ACTION_COMMA_action(_localctx, actionIndex); break;

			case 87 : ACTION_COLON2_action(_localctx, actionIndex); break;

			case 88 : ACTION_COLON_action(_localctx, actionIndex); break;

			case 89 : ACTION_MINUS_action(_localctx, actionIndex); break;

			case 90 : ACTION_ESCAPE_action(_localctx, actionIndex); break;

			case 91 : ACTION_WORD_action(_localctx, actionIndex); break;

			case 92 : ACTION_REFERENCE_action(_localctx, actionIndex); break;

			case 93 : ACTION_COMMENT_action(_localctx, actionIndex); break;

			case 94 : ACTION_LITERAL_action(_localctx, actionIndex); break;

			case 95 : ACTION_TEXT_action(_localctx, actionIndex); break;

			case 96 : ACTION_WS_action(_localctx, actionIndex); break;

			case 97 : ACTION_NEWLINE_action(_localctx, actionIndex); break;

			case 98 : END_ACTION_action(_localctx, actionIndex); break;

			case 99 : LEXER_CHAR_SET_BODY_action(_localctx, actionIndex); break;

			case 100 : END_LEXER_CHAR_SET_action(_localctx, actionIndex); break;
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
			case 1 : _channel = HIDDEN;  break;
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
			case 2 : _channel = HIDDEN;  break;
		}
	}
	public void PRIVATE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void END_LEXER_CHAR_SET_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 9 : _type = LEXER_CHAR_SET; popMode();  break;
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
			case 4 : _channel = HIDDEN;  break;
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
	public void LEXER_CHAR_SET_BODY_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 8 : more();  break;
		}
	}
	public void NESTED_ACTION_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 6 : _type = BEGIN_ACTION; pushMode(Action);  break;
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
			case 7 : popMode();  break;
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
			case 3 : pushMode(Action);  break;
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
			case 5 : popMode();  break;
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
			case 0 : handleBeginArgAction(); break;
		}
	}

	public static final String _serializedATN =
		"\2X\u02aa\6\uffff\6\uffff\6\uffff\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3"+
		"\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13"+
		"\2\f\7\f\2\r\7\r\2\16\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23"+
		"\7\23\2\24\7\24\2\25\7\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32"+
		"\7\32\2\33\7\33\2\34\7\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2"+
		"\"\7\"\2#\7#\2$\7$\2%\7%\2&\7&\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2"+
		"-\7-\2.\7.\2/\7/\2\60\7\60\2\61\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65"+
		"\7\65\2\66\7\66\2\67\7\67\28\78\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2"+
		"?\7?\2@\7@\2A\7A\2B\7B\2C\7C\2D\7D\2E\7E\2F\7F\2G\7G\2H\7H\2I\7I\2J\7"+
		"J\2K\7K\2L\7L\2M\7M\2N\7N\2O\7O\2P\7P\2Q\7Q\2R\7R\2S\7S\2T\7T\2U\7U\2"+
		"V\7V\2W\7W\2X\7X\2Y\7Y\2Z\7Z\2[\7[\2\\\7\\\2]\7]\2^\7^\2_\7_\2`\7`\2a"+
		"\7a\2b\7b\2c\7c\2d\7d\1\0\1\0\1\0\1\0\1\0\1\0\1\1\1\1\1\1\1\1\1\1\1\1"+
		"\1\1\1\2\1\2\3\2\u00de\b\2\1\3\5\3\u00e1\b\3\n\3\f\3\u00e4\t\3\1\3\1\3"+
		"\1\3\1\4\1\4\1\4\1\4\5\4\u00ed\b\4\n\4\f\4\u00f0\t\4\1\4\1\4\1\5\1\5\1"+
		"\5\1\5\5\5\u00f8\b\5\n\5\f\5\u00fb\t\5\1\5\1\5\1\6\1\6\1\6\1\6\5\6\u0103"+
		"\b\6\n\6\f\6\u0106\t\6\1\6\1\6\1\6\1\7\1\7\1\7\1\b\1\b\1\b\1\b\1\t\1\t"+
		"\1\t\1\t\1\t\1\t\1\t\1\t\1\t\5\t\u011b\b\t\n\t\f\t\u011e\t\t\1\t\1\t\1"+
		"\n\1\n\1\n\1\n\1\n\1\n\1\n\1\n\5\n\u012a\b\n\n\n\f\n\u012d\t\n\1\n\1\n"+
		"\1\13\1\13\1\13\1\13\1\13\1\13\1\13\1\f\1\f\1\f\1\f\1\f\1\f\1\f\1\f\1"+
		"\f\1\r\1\r\1\r\1\r\1\r\1\r\1\16\1\16\1\16\1\16\1\16\1\16\1\16\1\17\1\17"+
		"\1\17\1\17\1\17\1\17\1\17\1\17\1\20\1\20\1\20\1\20\1\20\1\20\1\20\1\20"+
		"\1\20\1\20\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\22\1\22\1\22\1\22\1\22"+
		"\1\22\1\22\1\22\1\23\1\23\1\23\1\23\1\23\1\23\1\23\1\23\1\24\1\24\1\24"+
		"\1\24\1\24\1\24\1\24\1\25\1\25\1\25\1\25\1\25\1\25\1\25\1\26\1\26\1\26"+
		"\1\26\1\26\1\26\1\27\1\27\1\27\1\27\1\27\1\27\1\27\1\27\1\30\1\30\1\30"+
		"\1\30\1\30\1\30\1\30\1\30\1\30\1\31\1\31\1\31\1\31\1\31\1\32\1\32\1\33"+
		"\1\33\1\33\1\34\1\34\1\35\1\35\1\36\1\36\1\37\1\37\1 \1 \1 \1!\1!\1\""+
		"\1\"\1#\1#\1$\1$\1%\1%\1&\1&\1\'\1\'\1\'\1(\1(\1)\1)\1*\1*\1+\1+\1+\1"+
		",\1,\1-\1-\1.\1.\1/\1/\1\60\1\60\5\60\u01d3\b\60\n\60\f\60\u01d6\t\60"+
		"\1\61\1\61\3\61\u01da\b\61\1\62\1\62\1\63\1\63\1\63\5\63\u01e1\b\63\n"+
		"\63\f\63\u01e4\t\63\1\63\1\63\1\64\1\64\1\64\5\64\u01eb\b\64\n\64\f\64"+
		"\u01ee\t\64\1\64\1\64\1\65\1\65\1\65\1\66\4\66\u01f6\b\66\13\66\f\66\u01f7"+
		"\1\67\1\67\1\67\3\67\u01fd\b\67\5\67\u01ff\b\67\n\67\f\67\u0202\t\67\1"+
		"\67\1\67\18\18\19\19\19\39\u020b\b9\1:\1:\1:\1:\1:\3:\u0212\b:\3:\u0214"+
		"\b:\3:\u0216\b:\3:\u0218\b:\1;\4;\u021b\b;\13;\f;\u021c\1;\1;\1<\1<\1"+
		"=\1=\1>\1>\1?\1?\1@\1@\1A\1A\1B\1B\1C\1C\1D\1D\1E\1E\1F\1F\1F\1G\1G\5"+
		"G\u023a\bG\nG\fG\u023d\tG\1H\1H\3H\u0241\bH\1I\4I\u0244\bI\13I\fI\u0245"+
		"\1J\4J\u0249\bJ\13J\fJ\u024a\1K\1K\3K\u024f\bK\1K\3K\u0252\bK\1L\1L\1"+
		"L\1L\1M\1M\1M\1M\1N\1N\1O\1O\1P\1P\1Q\1Q\1R\1R\1S\1S\1T\1T\1U\1U\1V\1"+
		"V\1W\1W\1W\1X\1X\1Y\1Y\1Z\1Z\1Z\1[\4[\u0279\b[\13[\f[\u027a\1\\\1\\\3"+
		"\\\u027f\b\\\1]\1]\1^\1^\3^\u0285\b^\1_\1_\1_\4_\u028a\b_\13_\f_\u028b"+
		"\1`\4`\u028f\b`\13`\f`\u0290\1a\1a\3a\u0295\ba\1a\3a\u0298\ba\1b\1b\1"+
		"b\1b\1c\1c\1c\4c\u02a1\bc\13c\fc\u02a2\1c\1c\1d\1d\1d\1de\4\4\uffff\6"+
		"\5\1\b\0\uffff\n\0\uffff\f\6\2\16\7\uffff\20\b\uffff\22\t\0\24\n\3\26"+
		"\13\uffff\30\f\uffff\32\r\uffff\34\16\uffff\36\17\uffff \20\uffff\"\21"+
		"\uffff$\22\uffff&\23\uffff(\24\uffff*\25\uffff,\26\uffff.\27\uffff\60"+
		"\30\uffff\62\31\uffff\64\32\uffff\66\33\uffff8\34\uffff:\35\uffff<\36"+
		"\uffff>\37\uffff@ \uffffB!\uffffD\"\uffffF#\uffffH$\uffffJ%\uffffL&\uffff"+
		"N\'\uffffP(\uffffR)\uffffT*\uffffV+\uffffX,\uffffZ-\uffff\\.\uffff^/\uffff"+
		"`\60\uffffb\61\uffffd\62\ufffff\0\uffffh\0\uffffj\0\uffffl\0\uffffn\0"+
		"\uffffp\63\uffffr\64\ufffft\0\uffffv\0\uffffx\0\uffffz\65\4|\0\uffff~"+
		"\0\uffff\u0080\0\uffff\u0082\66\uffff\u0084\67\uffff\u00868\uffff\u0088"+
		"9\uffff\u008a:\uffff\u008c;\uffff\u008e<\uffff\u0090=\uffff\u0092>\uffff"+
		"\u0094?\uffff\u0096@\uffff\u0098A\uffff\u009aB\uffff\u009cC\5\u009e\0"+
		"\6\u00a0D\uffff\u00a2E\uffff\u00a4F\uffff\u00a6G\uffff\u00a8H\uffff\u00aa"+
		"I\uffff\u00acJ\uffff\u00aeK\uffff\u00b0L\uffff\u00b2M\uffff\u00b4N\uffff"+
		"\u00b6O\uffff\u00b8P\uffff\u00baQ\uffff\u00bcR\uffff\u00beS\uffff\u00c0"+
		"T\uffff\u00c2U\uffff\u00c4V\uffff\u00c6W\uffff\u00c8X\7\u00ca\0\b\u00cc"+
		"\0\t\4\0\1\2\3\26\2\n\n\r\r\1\"\"\5\609__\u00b7\u00b7\u0300\u036f\u203f"+
		"\u2040\rAZaz\u00c0\u00d6\u00d8\u00f6\u00f8\u02ff\u0370\u037d\u037f\u1fff"+
		"\u200c\u200d\u2070\u218f\u2c00\u2fef\u3001\ud7ff\uf900\ufdcf\ufdf0\ufffd"+
		"\1\'\'\1\"\"\2\'\'\\\\\3\609AFaf\b\"\"\'\'\\\\bbffnnrrtt\3\t\n\f\r  \2"+
		"\n\n\r\r\3\t\t\f\f  \3\t\n\f\r  \5$$\609AZ__az\4\609AZ__az\r\t\n\r\r "+
		" \"\"$$\'),,\609<>AZ\\]__az\2\t\t  \4\609AZ__az\2**//\f\t\n\r\r  \"\""+
		"$$\'),:<>A]__a{}}\2\t\t  \1\\]\u02c0\0\4\1\0\0\0\0\6\1\0\0\0\0\f\1\0\0"+
		"\0\0\16\1\0\0\0\0\20\1\0\0\0\0\22\1\0\0\0\0\24\1\0\0\0\0\26\1\0\0\0\0"+
		"\30\1\0\0\0\0\32\1\0\0\0\0\34\1\0\0\0\0\36\1\0\0\0\0 \1\0\0\0\0\"\1\0"+
		"\0\0\0$\1\0\0\0\0&\1\0\0\0\0(\1\0\0\0\0*\1\0\0\0\0,\1\0\0\0\0.\1\0\0\0"+
		"\0\60\1\0\0\0\0\62\1\0\0\0\0\64\1\0\0\0\0\66\1\0\0\0\08\1\0\0\0\0:\1\0"+
		"\0\0\0<\1\0\0\0\0>\1\0\0\0\0@\1\0\0\0\0B\1\0\0\0\0D\1\0\0\0\0F\1\0\0\0"+
		"\0H\1\0\0\0\0J\1\0\0\0\0L\1\0\0\0\0N\1\0\0\0\0P\1\0\0\0\0R\1\0\0\0\0T"+
		"\1\0\0\0\0V\1\0\0\0\0X\1\0\0\0\0Z\1\0\0\0\0\\\1\0\0\0\0^\1\0\0\0\0`\1"+
		"\0\0\0\0b\1\0\0\0\0d\1\0\0\0\0p\1\0\0\0\0r\1\0\0\0\0z\1\0\0\0\0\u0082"+
		"\1\0\0\0\1\u0084\1\0\0\0\1\u0086\1\0\0\0\1\u0088\1\0\0\0\1\u008a\1\0\0"+
		"\0\1\u008c\1\0\0\0\1\u008e\1\0\0\0\1\u0090\1\0\0\0\1\u0092\1\0\0\0\1\u0094"+
		"\1\0\0\0\1\u0096\1\0\0\0\1\u0098\1\0\0\0\1\u009a\1\0\0\0\1\u009c\1\0\0"+
		"\0\2\u009e\1\0\0\0\2\u00a0\1\0\0\0\2\u00a2\1\0\0\0\2\u00a4\1\0\0\0\2\u00a6"+
		"\1\0\0\0\2\u00a8\1\0\0\0\2\u00aa\1\0\0\0\2\u00ac\1\0\0\0\2\u00ae\1\0\0"+
		"\0\2\u00b0\1\0\0\0\2\u00b2\1\0\0\0\2\u00b4\1\0\0\0\2\u00b6\1\0\0\0\2\u00b8"+
		"\1\0\0\0\2\u00ba\1\0\0\0\2\u00bc\1\0\0\0\2\u00be\1\0\0\0\2\u00c0\1\0\0"+
		"\0\2\u00c2\1\0\0\0\2\u00c4\1\0\0\0\2\u00c6\1\0\0\0\2\u00c8\1\0\0\0\3\u00ca"+
		"\1\0\0\0\3\u00cc\1\0\0\0\4\u00ce\1\0\0\0\6\u00d4\1\0\0\0\b\u00dd\1\0\0"+
		"\0\n\u00e2\1\0\0\0\f\u00e8\1\0\0\0\16\u00f3\1\0\0\0\20\u00fe\1\0\0\0\22"+
		"\u010a\1\0\0\0\24\u010d\1\0\0\0\26\u0111\1\0\0\0\30\u0121\1\0\0\0\32\u0130"+
		"\1\0\0\0\34\u0137\1\0\0\0\36\u0140\1\0\0\0 \u0146\1\0\0\0\"\u014d\1\0"+
		"\0\0$\u0155\1\0\0\0&\u015f\1\0\0\0(\u0166\1\0\0\0*\u016e\1\0\0\0,\u0176"+
		"\1\0\0\0.\u017d\1\0\0\0\60\u0184\1\0\0\0\62\u018a\1\0\0\0\64\u0192\1\0"+
		"\0\0\66\u019b\1\0\0\08\u01a0\1\0\0\0:\u01a2\1\0\0\0<\u01a5\1\0\0\0>\u01a7"+
		"\1\0\0\0@\u01a9\1\0\0\0B\u01ab\1\0\0\0D\u01ad\1\0\0\0F\u01b0\1\0\0\0H"+
		"\u01b2\1\0\0\0J\u01b4\1\0\0\0L\u01b6\1\0\0\0N\u01b8\1\0\0\0P\u01ba\1\0"+
		"\0\0R\u01bc\1\0\0\0T\u01bf\1\0\0\0V\u01c1\1\0\0\0X\u01c3\1\0\0\0Z\u01c5"+
		"\1\0\0\0\\\u01c8\1\0\0\0^\u01ca\1\0\0\0`\u01cc\1\0\0\0b\u01ce\1\0\0\0"+
		"d\u01d0\1\0\0\0f\u01d9\1\0\0\0h\u01db\1\0\0\0j\u01dd\1\0\0\0l\u01e7\1"+
		"\0\0\0n\u01f1\1\0\0\0p\u01f5\1\0\0\0r\u01f9\1\0\0\0t\u0205\1\0\0\0v\u0207"+
		"\1\0\0\0x\u020c\1\0\0\0z\u021a\1\0\0\0|\u0220\1\0\0\0~\u0222\1\0\0\0\u0080"+
		"\u0224\1\0\0\0\u0082\u0226\1\0\0\0\u0084\u0228\1\0\0\0\u0086\u022a\1\0"+
		"\0\0\u0088\u022c\1\0\0\0\u008a\u022e\1\0\0\0\u008c\u0230\1\0\0\0\u008e"+
		"\u0232\1\0\0\0\u0090\u0234\1\0\0\0\u0092\u0237\1\0\0\0\u0094\u0240\1\0"+
		"\0\0\u0096\u0243\1\0\0\0\u0098\u0248\1\0\0\0\u009a\u0251\1\0\0\0\u009c"+
		"\u0253\1\0\0\0\u009e\u0257\1\0\0\0\u00a0\u025b\1\0\0\0\u00a2\u025d\1\0"+
		"\0\0\u00a4\u025f\1\0\0\0\u00a6\u0261\1\0\0\0\u00a8\u0263\1\0\0\0\u00aa"+
		"\u0265\1\0\0\0\u00ac\u0267\1\0\0\0\u00ae\u0269\1\0\0\0\u00b0\u026b\1\0"+
		"\0\0\u00b2\u026d\1\0\0\0\u00b4\u0270\1\0\0\0\u00b6\u0272\1\0\0\0\u00b8"+
		"\u0274\1\0\0\0\u00ba\u0278\1\0\0\0\u00bc\u027c\1\0\0\0\u00be\u0280\1\0"+
		"\0\0\u00c0\u0284\1\0\0\0\u00c2\u0289\1\0\0\0\u00c4\u028e\1\0\0\0\u00c6"+
		"\u0297\1\0\0\0\u00c8\u0299\1\0\0\0\u00ca\u02a0\1\0\0\0\u00cc\u02a6\1\0"+
		"\0\0\u00ce\u00cf\5/\0\0\u00cf\u00d0\5*\0\0\u00d0\u00d1\5*\0\0\u00d1\u00d2"+
		"\1\0\0\0\u00d2\u00d3\3\n\3\0\u00d3\5\1\0\0\0\u00d4\u00d5\5/\0\0\u00d5"+
		"\u00d6\5*\0\0\u00d6\u00d7\1\0\0\0\u00d7\u00d8\3\n\3\0\u00d8\u00d9\1\0"+
		"\0\0\u00d9\u00da\6\1\1\0\u00da\7\1\0\0\0\u00db\u00de\3\6\1\0\u00dc\u00de"+
		"\3\f\4\0\u00dd\u00db\1\0\0\0\u00dd\u00dc\1\0\0\0\u00de\t\1\0\0\0\u00df"+
		"\u00e1\t\0\0\0\u00e0\u00df\1\0\0\0\u00e1\u00e4\1\0\0\0\u00e2\u00e3\1\0"+
		"\0\0\u00e2\u00e0\1\0\0\0\u00e3\u00e5\1\0\0\0\u00e4\u00e2\1\0\0\0\u00e5"+
		"\u00e6\5*\0\0\u00e6\u00e7\5/\0\0\u00e7\13\1\0\0\0\u00e8\u00e9\5/\0\0\u00e9"+
		"\u00ea\5/\0\0\u00ea\u00ee\1\0\0\0\u00eb\u00ed\b\0\0\0\u00ec\u00eb\1\0"+
		"\0\0\u00ed\u00f0\1\0\0\0\u00ee\u00ec\1\0\0\0\u00ee\u00ef\1\0\0\0\u00ef"+
		"\u00f1\1\0\0\0\u00f0\u00ee\1\0\0\0\u00f1\u00f2\6\4\2\0\u00f2\r\1\0\0\0"+
		"\u00f3\u00f9\5\"\0\0\u00f4\u00f5\5\\\0\0\u00f5\u00f8\t\0\0\0\u00f6\u00f8"+
		"\b\1\0\0\u00f7\u00f4\1\0\0\0\u00f7\u00f6\1\0\0\0\u00f8\u00fb\1\0\0\0\u00f9"+
		"\u00f7\1\0\0\0\u00f9\u00fa\1\0\0\0\u00fa\u00fc\1\0\0\0\u00fb\u00f9\1\0"+
		"\0\0\u00fc\u00fd\5\"\0\0\u00fd\17\1\0\0\0\u00fe\u00ff\5<\0\0\u00ff\u0100"+
		"\5<\0\0\u0100\u0104\1\0\0\0\u0101\u0103\t\0\0\0\u0102\u0101\1\0\0\0\u0103"+
		"\u0106\1\0\0\0\u0104\u0105\1\0\0\0\u0104\u0102\1\0\0\0\u0105\u0107\1\0"+
		"\0\0\u0106\u0104\1\0\0\0\u0107\u0108\5>\0\0\u0108\u0109\5>\0\0\u0109\21"+
		"\1\0\0\0\u010a\u010b\5[\0\0\u010b\u010c\6\7\0\0\u010c\23\1\0\0\0\u010d"+
		"\u010e\5{\0\0\u010e\u010f\1\0\0\0\u010f\u0110\6\b\3\0\u0110\25\1\0\0\0"+
		"\u0111\u0112\5o\0\0\u0112\u0113\5p\0\0\u0113\u0114\5t\0\0\u0114\u0115"+
		"\5i\0\0\u0115\u0116\5o\0\0\u0116\u0117\5n\0\0\u0117\u0118\5s\0\0\u0118"+
		"\u011c\1\0\0\0\u0119\u011b\3\u0080>\0\u011a\u0119\1\0\0\0\u011b\u011e"+
		"\1\0\0\0\u011c\u011a\1\0\0\0\u011c\u011d\1\0\0\0\u011d\u011f\1\0\0\0\u011e"+
		"\u011c\1\0\0\0\u011f\u0120\5{\0\0\u0120\27\1\0\0\0\u0121\u0122\5t\0\0"+
		"\u0122\u0123\5o\0\0\u0123\u0124\5k\0\0\u0124\u0125\5e\0\0\u0125\u0126"+
		"\5n\0\0\u0126\u0127\5s\0\0\u0127\u012b\1\0\0\0\u0128\u012a\3\u0080>\0"+
		"\u0129\u0128\1\0\0\0\u012a\u012d\1\0\0\0\u012b\u0129\1\0\0\0\u012b\u012c"+
		"\1\0\0\0\u012c\u012e\1\0\0\0\u012d\u012b\1\0\0\0\u012e\u012f\5{\0\0\u012f"+
		"\31\1\0\0\0\u0130\u0131\5i\0\0\u0131\u0132\5m\0\0\u0132\u0133\5p\0\0\u0133"+
		"\u0134\5o\0\0\u0134\u0135\5r\0\0\u0135\u0136\5t\0\0\u0136\33\1\0\0\0\u0137"+
		"\u0138\5f\0\0\u0138\u0139\5r\0\0\u0139\u013a\5a\0\0\u013a\u013b\5g\0\0"+
		"\u013b\u013c\5m\0\0\u013c\u013d\5e\0\0\u013d\u013e\5n\0\0\u013e\u013f"+
		"\5t\0\0\u013f\35\1\0\0\0\u0140\u0141\5l\0\0\u0141\u0142\5e\0\0\u0142\u0143"+
		"\5x\0\0\u0143\u0144\5e\0\0\u0144\u0145\5r\0\0\u0145\37\1\0\0\0\u0146\u0147"+
		"\5p\0\0\u0147\u0148\5a\0\0\u0148\u0149\5r\0\0\u0149\u014a\5s\0\0\u014a"+
		"\u014b\5e\0\0\u014b\u014c\5r\0\0\u014c!\1\0\0\0\u014d\u014e\5g\0\0\u014e"+
		"\u014f\5r\0\0\u014f\u0150\5a\0\0\u0150\u0151\5m\0\0\u0151\u0152\5m\0\0"+
		"\u0152\u0153\5a\0\0\u0153\u0154\5r\0\0\u0154#\1\0\0\0\u0155\u0156\5p\0"+
		"\0\u0156\u0157\5r\0\0\u0157\u0158\5o\0\0\u0158\u0159\5t\0\0\u0159\u015a"+
		"\5e\0\0\u015a\u015b\5c\0\0\u015b\u015c\5t\0\0\u015c\u015d\5e\0\0\u015d"+
		"\u015e\5d\0\0\u015e%\1\0\0\0\u015f\u0160\5p\0\0\u0160\u0161\5u\0\0\u0161"+
		"\u0162\5b\0\0\u0162\u0163\5l\0\0\u0163\u0164\5i\0\0\u0164\u0165\5c\0\0"+
		"\u0165\'\1\0\0\0\u0166\u0167\5p\0\0\u0167\u0168\5r\0\0\u0168\u0169\5i"+
		"\0\0\u0169\u016a\5v\0\0\u016a\u016b\5a\0\0\u016b\u016c\5t\0\0\u016c\u016d"+
		"\5e\0\0\u016d)\1\0\0\0\u016e\u016f\5r\0\0\u016f\u0170\5e\0\0\u0170\u0171"+
		"\5t\0\0\u0171\u0172\5u\0\0\u0172\u0173\5r\0\0\u0173\u0174\5n\0\0\u0174"+
		"\u0175\5s\0\0\u0175+\1\0\0\0\u0176\u0177\5l\0\0\u0177\u0178\5o\0\0\u0178"+
		"\u0179\5c\0\0\u0179\u017a\5a\0\0\u017a\u017b\5l\0\0\u017b\u017c\5s\0\0"+
		"\u017c-\1\0\0\0\u017d\u017e\5t\0\0\u017e\u017f\5h\0\0\u017f\u0180\5r\0"+
		"\0\u0180\u0181\5o\0\0\u0181\u0182\5w\0\0\u0182\u0183\5s\0\0\u0183/\1\0"+
		"\0\0\u0184\u0185\5c\0\0\u0185\u0186\5a\0\0\u0186\u0187\5t\0\0\u0187\u0188"+
		"\5c\0\0\u0188\u0189\5h\0\0\u0189\61\1\0\0\0\u018a\u018b\5f\0\0\u018b\u018c"+
		"\5i\0\0\u018c\u018d\5n\0\0\u018d\u018e\5a\0\0\u018e\u018f\5l\0\0\u018f"+
		"\u0190\5l\0\0\u0190\u0191\5y\0\0\u0191\63\1\0\0\0\u0192\u0193\5t\0\0\u0193"+
		"\u0194\5e\0\0\u0194\u0195\5m\0\0\u0195\u0196\5p\0\0\u0196\u0197\5l\0\0"+
		"\u0197\u0198\5a\0\0\u0198\u0199\5t\0\0\u0199\u019a\5e\0\0\u019a\65\1\0"+
		"\0\0\u019b\u019c\5m\0\0\u019c\u019d\5o\0\0\u019d\u019e\5d\0\0\u019e\u019f"+
		"\5e\0\0\u019f\67\1\0\0\0\u01a0\u01a1\5:\0\0\u01a19\1\0\0\0\u01a2\u01a3"+
		"\5:\0\0\u01a3\u01a4\5:\0\0\u01a4;\1\0\0\0\u01a5\u01a6\5,\0\0\u01a6=\1"+
		"\0\0\0\u01a7\u01a8\5;\0\0\u01a8?\1\0\0\0\u01a9\u01aa\5(\0\0\u01aaA\1\0"+
		"\0\0\u01ab\u01ac\5)\0\0\u01acC\1\0\0\0\u01ad\u01ae\5-\0\0\u01ae\u01af"+
		"\5>\0\0\u01afE\1\0\0\0\u01b0\u01b1\5<\0\0\u01b1G\1\0\0\0\u01b2\u01b3\5"+
		">\0\0\u01b3I\1\0\0\0\u01b4\u01b5\5=\0\0\u01b5K\1\0\0\0\u01b6\u01b7\5?"+
		"\0\0\u01b7M\1\0\0\0\u01b8\u01b9\5*\0\0\u01b9O\1\0\0\0\u01ba\u01bb\5+\0"+
		"\0\u01bbQ\1\0\0\0\u01bc\u01bd\5+\0\0\u01bd\u01be\5=\0\0\u01beS\1\0\0\0"+
		"\u01bf\u01c0\5|\0\0\u01c0U\1\0\0\0\u01c1\u01c2\5$\0\0\u01c2W\1\0\0\0\u01c3"+
		"\u01c4\5.\0\0\u01c4Y\1\0\0\0\u01c5\u01c6\5.\0\0\u01c6\u01c7\5.\0\0\u01c7"+
		"[\1\0\0\0\u01c8\u01c9\5@\0\0\u01c9]\1\0\0\0\u01ca\u01cb\5#\0\0\u01cb_"+
		"\1\0\0\0\u01cc\u01cd\5~\0\0\u01cda\1\0\0\0\u01ce\u01cf\5}\0\0\u01cfc\1"+
		"\0\0\0\u01d0\u01d4\3h\62\0\u01d1\u01d3\3f\61\0\u01d2\u01d1\1\0\0\0\u01d3"+
		"\u01d6\1\0\0\0\u01d4\u01d2\1\0\0\0\u01d4\u01d5\1\0\0\0\u01d5e\1\0\0\0"+
		"\u01d6\u01d4\1\0\0\0\u01d7\u01da\3h\62\0\u01d8\u01da\7\2\0\0\u01d9\u01d7"+
		"\1\0\0\0\u01d9\u01d8\1\0\0\0\u01dag\1\0\0\0\u01db\u01dc\7\3\0\0\u01dc"+
		"i\1\0\0\0\u01dd\u01e2\5\'\0\0\u01de\u01e1\3n\65\0\u01df\u01e1\b\4\0\0"+
		"\u01e0\u01de\1\0\0\0\u01e0\u01df\1\0\0\0\u01e1\u01e4\1\0\0\0\u01e2\u01e0"+
		"\1\0\0\0\u01e2\u01e3\1\0\0\0\u01e3\u01e5\1\0\0\0\u01e4\u01e2\1\0\0\0\u01e5"+
		"\u01e6\5\'\0\0\u01e6k\1\0\0\0\u01e7\u01ec\5\"\0\0\u01e8\u01eb\3n\65\0"+
		"\u01e9\u01eb\b\5\0\0\u01ea\u01e8\1\0\0\0\u01ea\u01e9\1\0\0\0\u01eb\u01ee"+
		"\1\0\0\0\u01ec\u01ea\1\0\0\0\u01ec\u01ed\1\0\0\0\u01ed\u01ef\1\0\0\0\u01ee"+
		"\u01ec\1\0\0\0\u01ef\u01f0\5\"\0\0\u01f0m\1\0\0\0\u01f1\u01f2\5\\\0\0"+
		"\u01f2\u01f3\t\0\0\0\u01f3o\1\0\0\0\u01f4\u01f6\2\609\0\u01f5\u01f4\1"+
		"\0\0\0\u01f6\u01f7\1\0\0\0\u01f7\u01f5\1\0\0\0\u01f7\u01f8\1\0\0\0\u01f8"+
		"q\1\0\0\0\u01f9\u0200\5\'\0\0\u01fa\u01fd\3v9\0\u01fb\u01fd\b\6\0\0\u01fc"+
		"\u01fa\1\0\0\0\u01fc\u01fb\1\0\0\0\u01fd\u01ff\1\0\0\0\u01fe\u01fc\1\0"+
		"\0\0\u01ff\u0202\1\0\0\0\u0200\u01fe\1\0\0\0\u0200\u0201\1\0\0\0\u0201"+
		"\u0203\1\0\0\0\u0202\u0200\1\0\0\0\u0203\u0204\5\'\0\0\u0204s\1\0\0\0"+
		"\u0205\u0206\7\7\0\0\u0206u\1\0\0\0\u0207\u020a\5\\\0\0\u0208\u020b\7"+
		"\b\0\0\u0209\u020b\3x:\0\u020a\u0208\1\0\0\0\u020a\u0209\1\0\0\0\u020b"+
		"w\1\0\0\0\u020c\u0217\5u\0\0\u020d\u0215\3t8\0\u020e\u0213\3t8\0\u020f"+
		"\u0211\3t8\0\u0210\u0212\3t8\0\u0211\u0210\1\0\0\0\u0211\u0212\1\0\0\0"+
		"\u0212\u0214\1\0\0\0\u0213\u020f\1\0\0\0\u0213\u0214\1\0\0\0\u0214\u0216"+
		"\1\0\0\0\u0215\u020e\1\0\0\0\u0215\u0216\1\0\0\0\u0216\u0218\1\0\0\0\u0217"+
		"\u020d\1\0\0\0\u0217\u0218\1\0\0\0\u0218y\1\0\0\0\u0219\u021b\7\t\0\0"+
		"\u021a\u0219\1\0\0\0\u021b\u021c\1\0\0\0\u021c\u021a\1\0\0\0\u021c\u021d"+
		"\1\0\0\0\u021d\u021e\1\0\0\0\u021e\u021f\6;\4\0\u021f{\1\0\0\0\u0220\u0221"+
		"\7\n\0\0\u0221}\1\0\0\0\u0222\u0223\7\13\0\0\u0223\177\1\0\0\0\u0224\u0225"+
		"\7\f\0\0\u0225\u0081\1\0\0\0\u0226\u0227\t\0\0\0\u0227\u0083\1\0\0\0\u0228"+
		"\u0229\5<\0\0\u0229\u0085\1\0\0\0\u022a\u022b\5>\0\0\u022b\u0087\1\0\0"+
		"\0\u022c\u022d\5(\0\0\u022d\u0089\1\0\0\0\u022e\u022f\5)\0\0\u022f\u008b"+
		"\1\0\0\0\u0230\u0231\5=\0\0\u0231\u008d\1\0\0\0\u0232\u0233\5,\0\0\u0233"+
		"\u008f\1\0\0\0\u0234\u0235\5\\\0\0\u0235\u0236\t\0\0\0\u0236\u0091\1\0"+
		"\0\0\u0237\u023b\7\r\0\0\u0238\u023a\7\16\0\0\u0239\u0238\1\0\0\0\u023a"+
		"\u023d\1\0\0\0\u023b\u0239\1\0\0\0\u023b\u023c\1\0\0\0\u023c\u0093\1\0"+
		"\0\0\u023d\u023b\1\0\0\0\u023e\u0241\3l\64\0\u023f\u0241\3j\63\0\u0240"+
		"\u023e\1\0\0\0\u0240\u023f\1\0\0\0\u0241\u0095\1\0\0\0\u0242\u0244\b\17"+
		"\0\0\u0243\u0242\1\0\0\0\u0244\u0245\1\0\0\0\u0245\u0243\1\0\0\0\u0245"+
		"\u0246\1\0\0\0\u0246\u0097\1\0\0\0\u0247\u0249\7\20\0\0\u0248\u0247\1"+
		"\0\0\0\u0249\u024a\1\0\0\0\u024a\u0248\1\0\0\0\u024a\u024b\1\0\0\0\u024b"+
		"\u0099\1\0\0\0\u024c\u024e\5\r\0\0\u024d\u024f\5\n\0\0\u024e\u024d\1\0"+
		"\0\0\u024e\u024f\1\0\0\0\u024f\u0252\1\0\0\0\u0250\u0252\5\n\0\0\u0251"+
		"\u024c\1\0\0\0\u0251\u0250\1\0\0\0\u0252\u009b\1\0\0\0\u0253\u0254\5]"+
		"\0\0\u0254\u0255\1\0\0\0\u0255\u0256\6L\5\0\u0256\u009d\1\0\0\0\u0257"+
		"\u0258\5{\0\0\u0258\u0259\1\0\0\0\u0259\u025a\6M\6\0\u025a\u009f\1\0\0"+
		"\0\u025b\u025c\5.\0\0\u025c\u00a1\1\0\0\0\u025d\u025e\5<\0\0\u025e\u00a3"+
		"\1\0\0\0\u025f\u0260\5>\0\0\u0260\u00a5\1\0\0\0\u0261\u0262\5(\0\0\u0262"+
		"\u00a7\1\0\0\0\u0263\u0264\5)\0\0\u0264\u00a9\1\0\0\0\u0265\u0266\5[\0"+
		"\0\u0266\u00ab\1\0\0\0\u0267\u0268\5]\0\0\u0268\u00ad\1\0\0\0\u0269\u026a"+
		"\5=\0\0\u026a\u00af\1\0\0\0\u026b\u026c\5,\0\0\u026c\u00b1\1\0\0\0\u026d"+
		"\u026e\5:\0\0\u026e\u026f\5:\0\0\u026f\u00b3\1\0\0\0\u0270\u0271\5:\0"+
		"\0\u0271\u00b5\1\0\0\0\u0272\u0273\5-\0\0\u0273\u00b7\1\0\0\0\u0274\u0275"+
		"\5\\\0\0\u0275\u0276\t\0\0\0\u0276\u00b9\1\0\0\0\u0277\u0279\7\21\0\0"+
		"\u0278\u0277\1\0\0\0\u0279\u027a\1\0\0\0\u027a\u0278\1\0\0\0\u027a\u027b"+
		"\1\0\0\0\u027b\u00bb\1\0\0\0\u027c\u027e\5$\0\0\u027d\u027f\3\u00ba[\0"+
		"\u027e\u027d\1\0\0\0\u027e\u027f\1\0\0\0\u027f\u00bd\1\0\0\0\u0280\u0281"+
		"\3\b\2\0\u0281\u00bf\1\0\0\0\u0282\u0285\3l\64\0\u0283\u0285\3j\63\0\u0284"+
		"\u0282\1\0\0\0\u0284\u0283\1\0\0\0\u0285\u00c1\1\0\0\0\u0286\u0287\5/"+
		"\0\0\u0287\u028a\b\22\0\0\u0288\u028a\b\23\0\0\u0289\u0286\1\0\0\0\u0289"+
		"\u0288\1\0\0\0\u028a\u028b\1\0\0\0\u028b\u0289\1\0\0\0\u028b\u028c\1\0"+
		"\0\0\u028c\u00c3\1\0\0\0\u028d\u028f\7\24\0\0\u028e\u028d\1\0\0\0\u028f"+
		"\u0290\1\0\0\0\u0290\u028e\1\0\0\0\u0290\u0291\1\0\0\0\u0291\u00c5\1\0"+
		"\0\0\u0292\u0294\5\r\0\0\u0293\u0295\5\n\0\0\u0294\u0293\1\0\0\0\u0294"+
		"\u0295\1\0\0\0\u0295\u0298\1\0\0\0\u0296\u0298\5\n\0\0\u0297\u0292\1\0"+
		"\0\0\u0297\u0296\1\0\0\0\u0298\u00c7\1\0\0\0\u0299\u029a\5}\0\0\u029a"+
		"\u029b\1\0\0\0\u029b\u029c\6b\7\0\u029c\u00c9\1\0\0\0\u029d\u02a1\b\25"+
		"\0\0\u029e\u029f\5\\\0\0\u029f\u02a1\t\0\0\0\u02a0\u029d\1\0\0\0\u02a0"+
		"\u029e\1\0\0\0\u02a1\u02a2\1\0\0\0\u02a2\u02a0\1\0\0\0\u02a2\u02a3\1\0"+
		"\0\0\u02a3\u02a4\1\0\0\0\u02a4\u02a5\6c\b\0\u02a5\u00cb\1\0\0\0\u02a6"+
		"\u02a7\5]\0\0\u02a7\u02a8\1\0\0\0\u02a8\u02a9\6d\t\0\u02a9\u00cd\1\0\0"+
		"\0+\0\1\1\1\2\1\3\1\u00dd\1\u00e2\0\u00ee\1\u00f7\1\u00f9\1\u0104\0\u011c"+
		"\1\u012b\1\u01d4\1\u01d9\1\u01e0\1\u01e2\1\u01ea\1\u01ec\1\u01f7\1\u01fc"+
		"\1\u0200\1\u020a\1\u0211\1\u0213\1\u0215\1\u0217\1\u021c\1\u023b\1\u0240"+
		"\1\u0245\1\u024a\1\u024e\1\u0251\1\u027a\1\u027e\1\u0284\1\u0289\1\u028b"+
		"\1\u0290\1\u0294\1\u0297\1\u02a0\1\u02a2\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}