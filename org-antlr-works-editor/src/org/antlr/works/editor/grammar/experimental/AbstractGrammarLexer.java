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
		OR=42, DOLLAR=43, DOT=44, RANGE=45, ETC=46, AT=47, POUND=48, NOT=49, RBRACE=50, 
		ID=51, INT=52, STRING_LITERAL=53, WS=54, ERRCHAR=55, ARG_ACTION_LT=56, 
		ARG_ACTION_GT=57, ARG_ACTION_LPAREN=58, ARG_ACTION_RPAREN=59, ARG_ACTION_EQUALS=60, 
		ARG_ACTION_COMMA=61, ARG_ACTION_ESCAPE=62, ARG_ACTION_WORD=63, ARG_ACTION_ELEMENT=64, 
		ARG_ACTION_TEXT=65, ARG_ACTION_WS=66, ARG_ACTION_NEWLINE=67, END_ARG_ACTION=68, 
		NESTED_ACTION=69, ACTION_DOT=70, ACTION_LT=71, ACTION_GT=72, ACTION_LPAREN=73, 
		ACTION_RPAREN=74, ACTION_LBRACK=75, ACTION_RBRACK=76, ACTION_EQUALS=77, 
		ACTION_COMMA=78, ACTION_COLON2=79, ACTION_COLON=80, ACTION_MINUS=81, ACTION_ESCAPE=82, 
		ACTION_WORD=83, ACTION_REFERENCE=84, ACTION_COMMENT=85, ACTION_LITERAL=86, 
		ACTION_TEXT=87, ACTION_WS=88, ACTION_NEWLINE=89, END_ACTION=90, LEXER_CHAR_SET_BODY=91, 
		END_LEXER_CHAR_SET=92;
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
		"BEGIN_ARG_ACTION", "BEGIN_ACTION", "OPTIONS", "TOKENS", "'import'", "'fragment'", 
		"'lexer'", "'parser'", "'grammar'", "'protected'", "'public'", "'private'", 
		"'returns'", "'locals'", "'throws'", "'catch'", "'finally'", "'template'", 
		"'mode'", "COLON", "COLONCOLON", "COMMA", "';'", "LPAREN", "RPAREN", "'->'", 
		"LT", "GT", "ASSIGN", "'?'", "'*'", "'+'", "'+='", "'|'", "'$'", "DOT", 
		"'..'", "'...'", "'@'", "'#'", "'~'", "'}'", "ID", "INT", "STRING_LITERAL", 
		"WS", "ERRCHAR", "ARG_ACTION_LT", "ARG_ACTION_GT", "ARG_ACTION_LPAREN", 
		"ARG_ACTION_RPAREN", "ARG_ACTION_EQUALS", "ARG_ACTION_COMMA", "ARG_ACTION_ESCAPE", 
		"ARG_ACTION_WORD", "ARG_ACTION_ELEMENT", "ARG_ACTION_TEXT", "ARG_ACTION_WS", 
		"ARG_ACTION_NEWLINE", "END_ARG_ACTION", "NESTED_ACTION", "'.'", "'<'", 
		"'>'", "'('", "')'", "'['", "']'", "'='", "','", "'::'", "':'", "'-'", 
		"ACTION_ESCAPE", "ACTION_WORD", "ACTION_REFERENCE", "ACTION_COMMENT", 
		"ACTION_LITERAL", "ACTION_TEXT", "ACTION_WS", "ACTION_NEWLINE", "END_ACTION", 
		"LEXER_CHAR_SET_BODY", "END_LEXER_CHAR_SET"
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

			case 100 : LEXER_CHAR_SET_BODY_action(_localctx, actionIndex); break;

			case 101 : END_LEXER_CHAR_SET_action(_localctx, actionIndex); break;
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
		"\2\\\u0324\6\uffff\6\uffff\6\uffff\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3"+
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
		"\7a\2b\7b\2c\7c\2d\7d\2e\7e\1\0\1\0\1\0\1\0\1\0\1\0\1\1\1\1\1\1\1\1\1"+
		"\1\1\1\1\1\1\2\0\1\2\0\3\2\b\2\1\3\0\5\3\b\3\n\3\f\3\u00e9\t\3\1\3\1\3"+
		"\1\3\1\4\1\4\1\4\1\4\0\5\4\b\4\n\4\f\4\u00f6\t\4\1\4\1\4\1\5\0\1\5\0\1"+
		"\5\0\1\5\0\5\5\b\5\n\5\f\5\u0105\t\5\1\5\1\5\1\6\1\6\1\6\1\6\0\5\6\b\6"+
		"\n\6\f\6\u0111\t\6\1\6\1\6\1\6\1\7\0\1\7\1\7\1\b\1\b\1\b\1\b\1\t\1\t\1"+
		"\t\1\t\1\t\1\t\1\t\1\t\1\t\0\5\t\b\t\n\t\f\t\u012b\t\t\1\t\1\t\1\n\1\n"+
		"\1\n\1\n\1\n\1\n\1\n\1\n\0\5\n\b\n\n\n\f\n\u013b\t\n\1\n\1\n\1\13\1\13"+
		"\1\13\1\13\1\13\1\13\1\13\1\f\1\f\1\f\1\f\1\f\1\f\1\f\1\f\1\f\1\r\1\r"+
		"\1\r\1\r\1\r\1\r\1\16\1\16\1\16\1\16\1\16\1\16\1\16\1\17\1\17\1\17\1\17"+
		"\1\17\1\17\1\17\1\17\1\20\1\20\1\20\1\20\1\20\1\20\1\20\1\20\1\20\1\20"+
		"\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\22\1\22\1\22\1\22\1\22\1\22\1\22"+
		"\1\22\1\23\1\23\1\23\1\23\1\23\1\23\1\23\1\23\1\24\1\24\1\24\1\24\1\24"+
		"\1\24\1\24\1\25\1\25\1\25\1\25\1\25\1\25\1\25\1\26\1\26\1\26\1\26\1\26"+
		"\1\26\1\27\1\27\1\27\1\27\1\27\1\27\1\27\1\27\1\30\1\30\1\30\1\30\1\30"+
		"\1\30\1\30\1\30\1\30\1\31\1\31\1\31\1\31\1\31\1\32\1\32\1\33\1\33\1\33"+
		"\1\34\1\34\1\35\1\35\1\36\1\36\1\37\1\37\1 \1 \1 \1!\1!\1\"\1\"\1#\1#"+
		"\1$\1$\1%\1%\1&\1&\1\'\1\'\1\'\1(\1(\1)\1)\1*\1*\1+\1+\1+\1,\1,\1,\1,"+
		"\1-\1-\1.\1.\1/\1/\1\60\1\60\1\61\0\1\61\0\5\61\b\61\n\61\f\61\u01ea\t"+
		"\61\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\3\62\b\62\1\63\0\1\63\0"+
		"\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0"+
		"\1\63\0\3\63\b\63\1\64\0\1\64\0\1\64\0\5\64\b\64\n\64\f\64\u021f\t\64"+
		"\1\64\1\64\1\65\0\1\65\0\1\65\0\5\65\b\65\n\65\f\65\u022c\t\65\1\65\1"+
		"\65\1\66\0\1\66\1\66\1\67\0\4\67\b\67\13\67\f\67\u0237\18\0\18\0\18\0"+
		"\38\b8\58\b8\n8\f8\u0245\t8\18\18\19\19\1:\0\1:\0\1:\0\3:\b:\1;\0\1;\0"+
		"\1;\0\1;\0\1;\0\3;\b;\3;\b;\3;\b;\3;\b;\1<\0\4<\b<\13<\f<\u0268\1<\1<"+
		"\1=\0\1=\0\3=\b=\1>\0\1>\0\1>\0\3>\b>\1?\0\1?\0\1?\0\1?\0\1?\0\3?\b?\1"+
		"@\1@\1A\1A\1B\1B\1C\1C\1D\1D\1E\1E\1F\1F\1G\0\1G\1G\1H\0\1H\0\5H\bH\n"+
		"H\fH\u02a0\tH\1I\0\1I\0\3I\bI\1J\0\4J\bJ\13J\fJ\u02ab\1K\0\4K\bK\13K\f"+
		"K\u02b1\1L\0\1L\0\3L\bL\1L\0\3L\bL\1M\1M\1M\1M\1N\1N\1N\1N\1O\1O\1P\1"+
		"P\1Q\1Q\1R\1R\1S\1S\1T\1T\1U\1U\1V\1V\1W\1W\1X\1X\1X\1Y\1Y\1Z\1Z\1[\0"+
		"\1[\1[\1\\\0\4\\\b\\\13\\\f\\\u02e6\1]\0\1]\0\3]\b]\1^\1^\1_\0\1_\0\3"+
		"_\b_\1`\0\1`\0\1`\0\4`\b`\13`\f`\u02fe\1a\0\4a\ba\13a\fa\u0304\1b\0\1"+
		"b\0\3b\bb\1b\0\3b\bb\1c\1c\1c\1c\1d\0\1d\0\1d\0\4d\bd\13d\fd\u031c\1d"+
		"\1d\1e\1e\1e\1ef\4\4\uffff\6\5\1\b\0\uffff\n\0\uffff\f\6\2\16\7\uffff"+
		"\20\b\uffff\22\t\0\24\n\3\26\13\uffff\30\f\uffff\32\r\uffff\34\16\uffff"+
		"\36\17\uffff \20\uffff\"\21\uffff$\22\uffff&\23\uffff(\24\uffff*\25\uffff"+
		",\26\uffff.\27\uffff\60\30\uffff\62\31\uffff\64\32\uffff\66\33\uffff8"+
		"\34\uffff:\35\uffff<\36\uffff>\37\uffff@ \uffffB!\uffffD\"\uffffF#\uffff"+
		"H$\uffffJ%\uffffL&\uffffN\'\uffffP(\uffffR)\uffffT*\uffffV+\uffffX,\uffff"+
		"Z-\uffff\\.\uffff^/\uffff`\60\uffffb\61\uffffd\62\ufffff\63\uffffh\0\uffff"+
		"j\0\uffffl\0\uffffn\0\uffffp\0\uffffr\64\ufffft\65\uffffv\0\uffffx\0\uffff"+
		"z\0\uffff|\66\4~\0\uffff\u0080\0\uffff\u0082\0\uffff\u0084\67\uffff\u0086"+
		"8\uffff\u00889\uffff\u008a:\uffff\u008c;\uffff\u008e<\uffff\u0090=\uffff"+
		"\u0092>\uffff\u0094?\uffff\u0096@\uffff\u0098A\uffff\u009aB\uffff\u009c"+
		"C\uffff\u009eD\5\u00a0E\6\u00a2F\uffff\u00a4G\uffff\u00a6H\uffff\u00a8"+
		"I\uffff\u00aaJ\uffff\u00acK\uffff\u00aeL\uffff\u00b0M\uffff\u00b2N\uffff"+
		"\u00b4O\uffff\u00b6P\uffff\u00b8Q\uffff\u00baR\uffff\u00bcS\uffff\u00be"+
		"T\uffff\u00c0U\uffff\u00c2V\uffff\u00c4W\uffff\u00c6X\uffff\u00c8Y\uffff"+
		"\u00caZ\7\u00cc[\b\u00ce\\\t\4\0\1\2\3\21\2\n\n\r\r\1\"\"\1\'\'\1\"\""+
		"\2\'\'\\\\\3\609AFaf\b\"\"\'\'\\\\bbffnnrrtt\3\t\n\f\r  \5$$\609AZ__a"+
		"z\4\609AZ__az\r\t\n\r\r  \"\"$$\'),,\609<>AZ\\]__az\2\t\t  \4\609AZ__"+
		"az\2**//\f\t\n\r\r  \"\"$$\'),:<>A]__a{}}\2\t\t  \1\\]\u02f8\0\4\1\0\0"+
		"\0\0\6\1\0\0\0\0\f\1\0\0\0\0\16\1\0\0\0\0\20\1\0\0\0\0\22\1\0\0\0\0\24"+
		"\1\0\0\0\0\26\1\0\0\0\0\30\1\0\0\0\0\32\1\0\0\0\0\34\1\0\0\0\0\36\1\0"+
		"\0\0\0 \1\0\0\0\0\"\1\0\0\0\0$\1\0\0\0\0&\1\0\0\0\0(\1\0\0\0\0*\1\0\0"+
		"\0\0,\1\0\0\0\0.\1\0\0\0\0\60\1\0\0\0\0\62\1\0\0\0\0\64\1\0\0\0\0\66\1"+
		"\0\0\0\08\1\0\0\0\0:\1\0\0\0\0<\1\0\0\0\0>\1\0\0\0\0@\1\0\0\0\0B\1\0\0"+
		"\0\0D\1\0\0\0\0F\1\0\0\0\0H\1\0\0\0\0J\1\0\0\0\0L\1\0\0\0\0N\1\0\0\0\0"+
		"P\1\0\0\0\0R\1\0\0\0\0T\1\0\0\0\0V\1\0\0\0\0X\1\0\0\0\0Z\1\0\0\0\0\\\1"+
		"\0\0\0\0^\1\0\0\0\0`\1\0\0\0\0b\1\0\0\0\0d\1\0\0\0\0f\1\0\0\0\0r\1\0\0"+
		"\0\0t\1\0\0\0\0|\1\0\0\0\0\u0084\1\0\0\0\1\u0086\1\0\0\0\1\u0088\1\0\0"+
		"\0\1\u008a\1\0\0\0\1\u008c\1\0\0\0\1\u008e\1\0\0\0\1\u0090\1\0\0\0\1\u0092"+
		"\1\0\0\0\1\u0094\1\0\0\0\1\u0096\1\0\0\0\1\u0098\1\0\0\0\1\u009a\1\0\0"+
		"\0\1\u009c\1\0\0\0\1\u009e\1\0\0\0\2\u00a0\1\0\0\0\2\u00a2\1\0\0\0\2\u00a4"+
		"\1\0\0\0\2\u00a6\1\0\0\0\2\u00a8\1\0\0\0\2\u00aa\1\0\0\0\2\u00ac\1\0\0"+
		"\0\2\u00ae\1\0\0\0\2\u00b0\1\0\0\0\2\u00b2\1\0\0\0\2\u00b4\1\0\0\0\2\u00b6"+
		"\1\0\0\0\2\u00b8\1\0\0\0\2\u00ba\1\0\0\0\2\u00bc\1\0\0\0\2\u00be\1\0\0"+
		"\0\2\u00c0\1\0\0\0\2\u00c2\1\0\0\0\2\u00c4\1\0\0\0\2\u00c6\1\0\0\0\2\u00c8"+
		"\1\0\0\0\2\u00ca\1\0\0\0\3\u00cc\1\0\0\0\3\u00ce\1\0\0\0\4\u00d0\1\0\0"+
		"\0\6\u00d6\1\0\0\0\b\u00e1\1\0\0\0\n\u00e7\1\0\0\0\f\u00ed\1\0\0\0\16"+
		"\u00f9\1\0\0\0\20\u0108\1\0\0\0\22\u0115\1\0\0\0\24\u0119\1\0\0\0\26\u011d"+
		"\1\0\0\0\30\u012e\1\0\0\0\32\u013e\1\0\0\0\34\u0145\1\0\0\0\36\u014e\1"+
		"\0\0\0 \u0154\1\0\0\0\"\u015b\1\0\0\0$\u0163\1\0\0\0&\u016d\1\0\0\0(\u0174"+
		"\1\0\0\0*\u017c\1\0\0\0,\u0184\1\0\0\0.\u018b\1\0\0\0\60\u0192\1\0\0\0"+
		"\62\u0198\1\0\0\0\64\u01a0\1\0\0\0\66\u01a9\1\0\0\08\u01ae\1\0\0\0:\u01b0"+
		"\1\0\0\0<\u01b3\1\0\0\0>\u01b5\1\0\0\0@\u01b7\1\0\0\0B\u01b9\1\0\0\0D"+
		"\u01bb\1\0\0\0F\u01be\1\0\0\0H\u01c0\1\0\0\0J\u01c2\1\0\0\0L\u01c4\1\0"+
		"\0\0N\u01c6\1\0\0\0P\u01c8\1\0\0\0R\u01ca\1\0\0\0T\u01cd\1\0\0\0V\u01cf"+
		"\1\0\0\0X\u01d1\1\0\0\0Z\u01d3\1\0\0\0\\\u01d6\1\0\0\0^\u01da\1\0\0\0"+
		"`\u01dc\1\0\0\0b\u01de\1\0\0\0d\u01e0\1\0\0\0f\u01e2\1\0\0\0h\u01f7\1"+
		"\0\0\0j\u0213\1\0\0\0l\u0215\1\0\0\0n\u0222\1\0\0\0p\u022f\1\0\0\0r\u0235"+
		"\1\0\0\0t\u0239\1\0\0\0v\u0248\1\0\0\0x\u024a\1\0\0\0z\u0252\1\0\0\0|"+
		"\u0266\1\0\0\0~\u0270\1\0\0\0\u0080\u0278\1\0\0\0\u0082\u0284\1\0\0\0"+
		"\u0084\u0286\1\0\0\0\u0086\u0288\1\0\0\0\u0088\u028a\1\0\0\0\u008a\u028c"+
		"\1\0\0\0\u008c\u028e\1\0\0\0\u008e\u0290\1\0\0\0\u0090\u0292\1\0\0\0\u0092"+
		"\u0294\1\0\0\0\u0094\u0298\1\0\0\0\u0096\u02a5\1\0\0\0\u0098\u02a9\1\0"+
		"\0\0\u009a\u02af\1\0\0\0\u009c\u02bb\1\0\0\0\u009e\u02bd\1\0\0\0\u00a0"+
		"\u02c1\1\0\0\0\u00a2\u02c5\1\0\0\0\u00a4\u02c7\1\0\0\0\u00a6\u02c9\1\0"+
		"\0\0\u00a8\u02cb\1\0\0\0\u00aa\u02cd\1\0\0\0\u00ac\u02cf\1\0\0\0\u00ae"+
		"\u02d1\1\0\0\0\u00b0\u02d3\1\0\0\0\u00b2\u02d5\1\0\0\0\u00b4\u02d7\1\0"+
		"\0\0\u00b6\u02da\1\0\0\0\u00b8\u02dc\1\0\0\0\u00ba\u02de\1\0\0\0\u00bc"+
		"\u02e4\1\0\0\0\u00be\u02e8\1\0\0\0\u00c0\u02ee\1\0\0\0\u00c2\u02f4\1\0"+
		"\0\0\u00c4\u02fc\1\0\0\0\u00c6\u0302\1\0\0\0\u00c8\u030e\1\0\0\0\u00ca"+
		"\u0310\1\0\0\0\u00cc\u031a\1\0\0\0\u00ce\u0320\1\0\0\0\u00d0\u00d1\5/"+
		"\0\0\u00d1\u00d2\5*\0\0\u00d2\u00d3\5*\0\0\u00d3\u00d4\1\0\0\0\u00d4\u00d5"+
		"\3\n\3\0\u00d5\5\1\0\0\0\u00d6\u00d7\5/\0\0\u00d7\u00d8\5*\0\0\u00d8\u00d9"+
		"\1\0\0\0\u00d9\u00da\3\n\3\0\u00da\u00db\1\0\0\0\u00db\u00dc\6\1\1\0\u00dc"+
		"\7\1\0\0\0\u00dd\u00e2\3\6\1\0\u00df\u00e2\3\f\4\0\u00e1\u00dd\1\0\0\0"+
		"\u00e1\u00df\1\0\0\0\u00e2\t\1\0\0\0\u00e3\u00e6\t\0\0\0\u00e5\u00e3\1"+
		"\0\0\0\u00e6\u00e9\1\0\0\0\u00e7\u00e8\1\0\0\0\u00e7\u00e5\1\0\0\0\u00e8"+
		"\u00ea\1\0\0\0\u00e9\u00e7\1\0\0\0\u00ea\u00eb\5*\0\0\u00eb\u00ec\5/\0"+
		"\0\u00ec\13\1\0\0\0\u00ed\u00ee\5/\0\0\u00ee\u00ef\5/\0\0\u00ef\u00f4"+
		"\1\0\0\0\u00f0\u00f3\b\0\0\0\u00f2\u00f0\1\0\0\0\u00f3\u00f6\1\0\0\0\u00f4"+
		"\u00f2\1\0\0\0\u00f4\u00f5\1\0\0\0\u00f5\u00f7\1\0\0\0\u00f6\u00f4\1\0"+
		"\0\0\u00f7\u00f8\6\4\2\0\u00f8\r\1\0\0\0\u00f9\u0103\5\"\0\0\u00fb\u00fd"+
		"\5\\\0\0\u00fd\u0102\t\0\0\0\u00ff\u0102\b\1\0\0\u0101\u00fb\1\0\0\0\u0101"+
		"\u00ff\1\0\0\0\u0102\u0105\1\0\0\0\u0103\u0101\1\0\0\0\u0103\u0104\1\0"+
		"\0\0\u0104\u0106\1\0\0\0\u0105\u0103\1\0\0\0\u0106\u0107\5\"\0\0\u0107"+
		"\17\1\0\0\0\u0108\u0109\5<\0\0\u0109\u010a\5<\0\0\u010a\u010f\1\0\0\0"+
		"\u010b\u010e\t\0\0\0\u010d\u010b\1\0\0\0\u010e\u0111\1\0\0\0\u010f\u0110"+
		"\1\0\0\0\u010f\u010d\1\0\0\0\u0110\u0112\1\0\0\0\u0111\u010f\1\0\0\0\u0112"+
		"\u0113\5>\0\0\u0113\u0114\5>\0\0\u0114\21\1\0\0\0\u0115\u0117\5[\0\0\u0117"+
		"\u0118\6\7\0\0\u0118\23\1\0\0\0\u0119\u011a\5{\0\0\u011a\u011b\1\0\0\0"+
		"\u011b\u011c\6\b\3\0\u011c\25\1\0\0\0\u011d\u011e\5o\0\0\u011e\u011f\5"+
		"p\0\0\u011f\u0120\5t\0\0\u0120\u0121\5i\0\0\u0121\u0122\5o\0\0\u0122\u0123"+
		"\5n\0\0\u0123\u0124\5s\0\0\u0124\u0129\1\0\0\0\u0125\u0128\3\u0082?\0"+
		"\u0127\u0125\1\0\0\0\u0128\u012b\1\0\0\0\u0129\u0127\1\0\0\0\u0129\u012a"+
		"\1\0\0\0\u012a\u012c\1\0\0\0\u012b\u0129\1\0\0\0\u012c\u012d\5{\0\0\u012d"+
		"\27\1\0\0\0\u012e\u012f\5t\0\0\u012f\u0130\5o\0\0\u0130\u0131\5k\0\0\u0131"+
		"\u0132\5e\0\0\u0132\u0133\5n\0\0\u0133\u0134\5s\0\0\u0134\u0139\1\0\0"+
		"\0\u0135\u0138\3\u0082?\0\u0137\u0135\1\0\0\0\u0138\u013b\1\0\0\0\u0139"+
		"\u0137\1\0\0\0\u0139\u013a\1\0\0\0\u013a\u013c\1\0\0\0\u013b\u0139\1\0"+
		"\0\0\u013c\u013d\5{\0\0\u013d\31\1\0\0\0\u013e\u013f\5i\0\0\u013f\u0140"+
		"\5m\0\0\u0140\u0141\5p\0\0\u0141\u0142\5o\0\0\u0142\u0143\5r\0\0\u0143"+
		"\u0144\5t\0\0\u0144\33\1\0\0\0\u0145\u0146\5f\0\0\u0146\u0147\5r\0\0\u0147"+
		"\u0148\5a\0\0\u0148\u0149\5g\0\0\u0149\u014a\5m\0\0\u014a\u014b\5e\0\0"+
		"\u014b\u014c\5n\0\0\u014c\u014d\5t\0\0\u014d\35\1\0\0\0\u014e\u014f\5"+
		"l\0\0\u014f\u0150\5e\0\0\u0150\u0151\5x\0\0\u0151\u0152\5e\0\0\u0152\u0153"+
		"\5r\0\0\u0153\37\1\0\0\0\u0154\u0155\5p\0\0\u0155\u0156\5a\0\0\u0156\u0157"+
		"\5r\0\0\u0157\u0158\5s\0\0\u0158\u0159\5e\0\0\u0159\u015a\5r\0\0\u015a"+
		"!\1\0\0\0\u015b\u015c\5g\0\0\u015c\u015d\5r\0\0\u015d\u015e\5a\0\0\u015e"+
		"\u015f\5m\0\0\u015f\u0160\5m\0\0\u0160\u0161\5a\0\0\u0161\u0162\5r\0\0"+
		"\u0162#\1\0\0\0\u0163\u0164\5p\0\0\u0164\u0165\5r\0\0\u0165\u0166\5o\0"+
		"\0\u0166\u0167\5t\0\0\u0167\u0168\5e\0\0\u0168\u0169\5c\0\0\u0169\u016a"+
		"\5t\0\0\u016a\u016b\5e\0\0\u016b\u016c\5d\0\0\u016c%\1\0\0\0\u016d\u016e"+
		"\5p\0\0\u016e\u016f\5u\0\0\u016f\u0170\5b\0\0\u0170\u0171\5l\0\0\u0171"+
		"\u0172\5i\0\0\u0172\u0173\5c\0\0\u0173\'\1\0\0\0\u0174\u0175\5p\0\0\u0175"+
		"\u0176\5r\0\0\u0176\u0177\5i\0\0\u0177\u0178\5v\0\0\u0178\u0179\5a\0\0"+
		"\u0179\u017a\5t\0\0\u017a\u017b\5e\0\0\u017b)\1\0\0\0\u017c\u017d\5r\0"+
		"\0\u017d\u017e\5e\0\0\u017e\u017f\5t\0\0\u017f\u0180\5u\0\0\u0180\u0181"+
		"\5r\0\0\u0181\u0182\5n\0\0\u0182\u0183\5s\0\0\u0183+\1\0\0\0\u0184\u0185"+
		"\5l\0\0\u0185\u0186\5o\0\0\u0186\u0187\5c\0\0\u0187\u0188\5a\0\0\u0188"+
		"\u0189\5l\0\0\u0189\u018a\5s\0\0\u018a-\1\0\0\0\u018b\u018c\5t\0\0\u018c"+
		"\u018d\5h\0\0\u018d\u018e\5r\0\0\u018e\u018f\5o\0\0\u018f\u0190\5w\0\0"+
		"\u0190\u0191\5s\0\0\u0191/\1\0\0\0\u0192\u0193\5c\0\0\u0193\u0194\5a\0"+
		"\0\u0194\u0195\5t\0\0\u0195\u0196\5c\0\0\u0196\u0197\5h\0\0\u0197\61\1"+
		"\0\0\0\u0198\u0199\5f\0\0\u0199\u019a\5i\0\0\u019a\u019b\5n\0\0\u019b"+
		"\u019c\5a\0\0\u019c\u019d\5l\0\0\u019d\u019e\5l\0\0\u019e\u019f\5y\0\0"+
		"\u019f\63\1\0\0\0\u01a0\u01a1\5t\0\0\u01a1\u01a2\5e\0\0\u01a2\u01a3\5"+
		"m\0\0\u01a3\u01a4\5p\0\0\u01a4\u01a5\5l\0\0\u01a5\u01a6\5a\0\0\u01a6\u01a7"+
		"\5t\0\0\u01a7\u01a8\5e\0\0\u01a8\65\1\0\0\0\u01a9\u01aa\5m\0\0\u01aa\u01ab"+
		"\5o\0\0\u01ab\u01ac\5d\0\0\u01ac\u01ad\5e\0\0\u01ad\67\1\0\0\0\u01ae\u01af"+
		"\5:\0\0\u01af9\1\0\0\0\u01b0\u01b1\5:\0\0\u01b1\u01b2\5:\0\0\u01b2;\1"+
		"\0\0\0\u01b3\u01b4\5,\0\0\u01b4=\1\0\0\0\u01b5\u01b6\5;\0\0\u01b6?\1\0"+
		"\0\0\u01b7\u01b8\5(\0\0\u01b8A\1\0\0\0\u01b9\u01ba\5)\0\0\u01baC\1\0\0"+
		"\0\u01bb\u01bc\5-\0\0\u01bc\u01bd\5>\0\0\u01bdE\1\0\0\0\u01be\u01bf\5"+
		"<\0\0\u01bfG\1\0\0\0\u01c0\u01c1\5>\0\0\u01c1I\1\0\0\0\u01c2\u01c3\5="+
		"\0\0\u01c3K\1\0\0\0\u01c4\u01c5\5?\0\0\u01c5M\1\0\0\0\u01c6\u01c7\5*\0"+
		"\0\u01c7O\1\0\0\0\u01c8\u01c9\5+\0\0\u01c9Q\1\0\0\0\u01ca\u01cb\5+\0\0"+
		"\u01cb\u01cc\5=\0\0\u01ccS\1\0\0\0\u01cd\u01ce\5|\0\0\u01ceU\1\0\0\0\u01cf"+
		"\u01d0\5$\0\0\u01d0W\1\0\0\0\u01d1\u01d2\5.\0\0\u01d2Y\1\0\0\0\u01d3\u01d4"+
		"\5.\0\0\u01d4\u01d5\5.\0\0\u01d5[\1\0\0\0\u01d6\u01d7\5.\0\0\u01d7\u01d8"+
		"\5.\0\0\u01d8\u01d9\5.\0\0\u01d9]\1\0\0\0\u01da\u01db\5@\0\0\u01db_\1"+
		"\0\0\0\u01dc\u01dd\5#\0\0\u01dda\1\0\0\0\u01de\u01df\5~\0\0\u01dfc\1\0"+
		"\0\0\u01e0\u01e1\5}\0\0\u01e1e\1\0\0\0\u01e2\u01e8\3j\63\0\u01e4\u01e7"+
		"\3h\62\0\u01e6\u01e4\1\0\0\0\u01e7\u01ea\1\0\0\0\u01e8\u01e6\1\0\0\0\u01e8"+
		"\u01e9\1\0\0\0\u01e9g\1\0\0\0\u01ea\u01e8\1\0\0\0\u01eb\u01f8\3j\63\0"+
		"\u01ed\u01f8\2\609\0\u01ef\u01f8\5_\0\0\u01f1\u01f8\5\u00b7\0\0\u01f3"+
		"\u01f8\2\u0300\u036f\0\u01f5\u01f8\2\u203f\u2040\0\u01f7\u01eb\1\0\0\0"+
		"\u01f7\u01ed\1\0\0\0\u01f7\u01ef\1\0\0\0\u01f7\u01f1\1\0\0\0\u01f7\u01f3"+
		"\1\0\0\0\u01f7\u01f5\1\0\0\0\u01f8i\1\0\0\0\u01f9\u0214\2AZ\0\u01fb\u0214"+
		"\2az\0\u01fd\u0214\2\u00c0\u00d6\0\u01ff\u0214\2\u00d8\u00f6\0\u0201\u0214"+
		"\2\u00f8\u02ff\0\u0203\u0214\2\u0370\u037d\0\u0205\u0214\2\u037f\u1fff"+
		"\0\u0207\u0214\2\u200c\u200d\0\u0209\u0214\2\u2070\u218f\0\u020b\u0214"+
		"\2\u2c00\u2fef\0\u020d\u0214\2\u3001\ud7ff\0\u020f\u0214\2\uf900\ufdcf"+
		"\0\u0211\u0214\2\ufdf0\ufffd\0\u0213\u01f9\1\0\0\0\u0213\u01fb\1\0\0\0"+
		"\u0213\u01fd\1\0\0\0\u0213\u01ff\1\0\0\0\u0213\u0201\1\0\0\0\u0213\u0203"+
		"\1\0\0\0\u0213\u0205\1\0\0\0\u0213\u0207\1\0\0\0\u0213\u0209\1\0\0\0\u0213"+
		"\u020b\1\0\0\0\u0213\u020d\1\0\0\0\u0213\u020f\1\0\0\0\u0213\u0211\1\0"+
		"\0\0\u0214k\1\0\0\0\u0215\u021d\5\'\0\0\u0217\u021c\3p\66\0\u0219\u021c"+
		"\b\2\0\0\u021b\u0217\1\0\0\0\u021b\u0219\1\0\0\0\u021c\u021f\1\0\0\0\u021d"+
		"\u021b\1\0\0\0\u021d\u021e\1\0\0\0\u021e\u0220\1\0\0\0\u021f\u021d\1\0"+
		"\0\0\u0220\u0221\5\'\0\0\u0221m\1\0\0\0\u0222\u022a\5\"\0\0\u0224\u0229"+
		"\3p\66\0\u0226\u0229\b\3\0\0\u0228\u0224\1\0\0\0\u0228\u0226\1\0\0\0\u0229"+
		"\u022c\1\0\0\0\u022a\u0228\1\0\0\0\u022a\u022b\1\0\0\0\u022b\u022d\1\0"+
		"\0\0\u022c\u022a\1\0\0\0\u022d\u022e\5\"\0\0\u022eo\1\0\0\0\u022f\u0231"+
		"\5\\\0\0\u0231\u0232\t\0\0\0\u0232q\1\0\0\0\u0233\u0236\2\609\0\u0235"+
		"\u0233\1\0\0\0\u0236\u0237\1\0\0\0\u0237\u0235\1\0\0\0\u0237\u0238\1\0"+
		"\0\0\u0238s\1\0\0\0\u0239\u0243\5\'\0\0\u023b\u0240\3x:\0\u023d\u0240"+
		"\b\4\0\0\u023f\u023b\1\0\0\0\u023f\u023d\1\0\0\0\u0240\u0242\1\0\0\0\u0241"+
		"\u023f\1\0\0\0\u0242\u0245\1\0\0\0\u0243\u0241\1\0\0\0\u0243\u0244\1\0"+
		"\0\0\u0244\u0246\1\0\0\0\u0245\u0243\1\0\0\0\u0246\u0247\5\'\0\0\u0247"+
		"u\1\0\0\0\u0248\u0249\7\5\0\0\u0249w\1\0\0\0\u024a\u0250\5\\\0\0\u024c"+
		"\u0251\7\6\0\0\u024e\u0251\3z;\0\u0250\u024c\1\0\0\0\u0250\u024e\1\0\0"+
		"\0\u0251y\1\0\0\0\u0252\u0262\5u\0\0\u0254\u0260\3v9\0\u0256\u025e\3v"+
		"9\0\u0258\u025c\3v9\0\u025a\u025d\3v9\0\u025c\u025a\1\0\0\0\u025c\u025d"+
		"\1\0\0\0\u025d\u025f\1\0\0\0\u025e\u0258\1\0\0\0\u025e\u025f\1\0\0\0\u025f"+
		"\u0261\1\0\0\0\u0260\u0256\1\0\0\0\u0260\u0261\1\0\0\0\u0261\u0263\1\0"+
		"\0\0\u0262\u0254\1\0\0\0\u0262\u0263\1\0\0\0\u0263{\1\0\0\0\u0264\u0267"+
		"\7\7\0\0\u0266\u0264\1\0\0\0\u0267\u0268\1\0\0\0\u0268\u0266\1\0\0\0\u0268"+
		"\u0269\1\0\0\0\u0269\u026a\1\0\0\0\u026a\u026b\6<\4\0\u026b}\1\0\0\0\u026c"+
		"\u0271\5\n\0\0\u026e\u0271\5\r\0\0\u0270\u026c\1\0\0\0\u0270\u026e\1\0"+
		"\0\0\u0271\177\1\0\0\0\u0272\u0279\5 \0\0\u0274\u0279\5\t\0\0\u0276\u0279"+
		"\5\f\0\0\u0278\u0272\1\0\0\0\u0278\u0274\1\0\0\0\u0278\u0276\1\0\0\0\u0279"+
		"\u0081\1\0\0\0\u027a\u0285\5 \0\0\u027c\u0285\5\t\0\0\u027e\u0285\5\f"+
		"\0\0\u0280\u0285\5\n\0\0\u0282\u0285\5\r\0\0\u0284\u027a\1\0\0\0\u0284"+
		"\u027c\1\0\0\0\u0284\u027e\1\0\0\0\u0284\u0280\1\0\0\0\u0284\u0282\1\0"+
		"\0\0\u0285\u0083\1\0\0\0\u0286\u0287\t\0\0\0\u0287\u0085\1\0\0\0\u0288"+
		"\u0289\5<\0\0\u0289\u0087\1\0\0\0\u028a\u028b\5>\0\0\u028b\u0089\1\0\0"+
		"\0\u028c\u028d\5(\0\0\u028d\u008b\1\0\0\0\u028e\u028f\5)\0\0\u028f\u008d"+
		"\1\0\0\0\u0290\u0291\5=\0\0\u0291\u008f\1\0\0\0\u0292\u0293\5,\0\0\u0293"+
		"\u0091\1\0\0\0\u0294\u0296\5\\\0\0\u0296\u0297\t\0\0\0\u0297\u0093\1\0"+
		"\0\0\u0298\u029e\7\b\0\0\u029a\u029d\7\t\0\0\u029c\u029a\1\0\0\0\u029d"+
		"\u02a0\1\0\0\0\u029e\u029c\1\0\0\0\u029e\u029f\1\0\0\0\u029f\u0095\1\0"+
		"\0\0\u02a0\u029e\1\0\0\0\u02a1\u02a6\3n\65\0\u02a3\u02a6\3l\64\0\u02a5"+
		"\u02a1\1\0\0\0\u02a5\u02a3\1\0\0\0\u02a6\u0097\1\0\0\0\u02a7\u02aa\b\n"+
		"\0\0\u02a9\u02a7\1\0\0\0\u02aa\u02ab\1\0\0\0\u02ab\u02a9\1\0\0\0\u02ab"+
		"\u02ac\1\0\0\0\u02ac\u0099\1\0\0\0\u02ad\u02b0\7\13\0\0\u02af\u02ad\1"+
		"\0\0\0\u02b0\u02b1\1\0\0\0\u02b1\u02af\1\0\0\0\u02b1\u02b2\1\0\0\0\u02b2"+
		"\u009b\1\0\0\0\u02b3\u02b7\5\r\0\0\u02b5\u02b8\5\n\0\0\u02b7\u02b5\1\0"+
		"\0\0\u02b7\u02b8\1\0\0\0\u02b8\u02bc\1\0\0\0\u02b9\u02bc\5\n\0\0\u02bb"+
		"\u02b3\1\0\0\0\u02bb\u02b9\1\0\0\0\u02bc\u009d\1\0\0\0\u02bd\u02be\5]"+
		"\0\0\u02be\u02bf\1\0\0\0\u02bf\u02c0\6M\5\0\u02c0\u009f\1\0\0\0\u02c1"+
		"\u02c2\5{\0\0\u02c2\u02c3\1\0\0\0\u02c3\u02c4\6N\6\0\u02c4\u00a1\1\0\0"+
		"\0\u02c5\u02c6\5.\0\0\u02c6\u00a3\1\0\0\0\u02c7\u02c8\5<\0\0\u02c8\u00a5"+
		"\1\0\0\0\u02c9\u02ca\5>\0\0\u02ca\u00a7\1\0\0\0\u02cb\u02cc\5(\0\0\u02cc"+
		"\u00a9\1\0\0\0\u02cd\u02ce\5)\0\0\u02ce\u00ab\1\0\0\0\u02cf\u02d0\5[\0"+
		"\0\u02d0\u00ad\1\0\0\0\u02d1\u02d2\5]\0\0\u02d2\u00af\1\0\0\0\u02d3\u02d4"+
		"\5=\0\0\u02d4\u00b1\1\0\0\0\u02d5\u02d6\5,\0\0\u02d6\u00b3\1\0\0\0\u02d7"+
		"\u02d8\5:\0\0\u02d8\u02d9\5:\0\0\u02d9\u00b5\1\0\0\0\u02da\u02db\5:\0"+
		"\0\u02db\u00b7\1\0\0\0\u02dc\u02dd\5-\0\0\u02dd\u00b9\1\0\0\0\u02de\u02e0"+
		"\5\\\0\0\u02e0\u02e1\t\0\0\0\u02e1\u00bb\1\0\0\0\u02e2\u02e5\7\f\0\0\u02e4"+
		"\u02e2\1\0\0\0\u02e5\u02e6\1\0\0\0\u02e6\u02e4\1\0\0\0\u02e6\u02e7\1\0"+
		"\0\0\u02e7\u00bd\1\0\0\0\u02e8\u02ec\5$\0\0\u02ea\u02ed\3\u00bc\\\0\u02ec"+
		"\u02ea\1\0\0\0\u02ec\u02ed\1\0\0\0\u02ed\u00bf\1\0\0\0\u02ee\u02ef\3\b"+
		"\2\0\u02ef\u00c1\1\0\0\0\u02f0\u02f5\3n\65\0\u02f2\u02f5\3l\64\0\u02f4"+
		"\u02f0\1\0\0\0\u02f4\u02f2\1\0\0\0\u02f5\u00c3\1\0\0\0\u02f6\u02f8\5/"+
		"\0\0\u02f8\u02fd\b\r\0\0\u02fa\u02fd\b\16\0\0\u02fc\u02f6\1\0\0\0\u02fc"+
		"\u02fa\1\0\0\0\u02fd\u02fe\1\0\0\0\u02fe\u02fc\1\0\0\0\u02fe\u02ff\1\0"+
		"\0\0\u02ff\u00c5\1\0\0\0\u0300\u0303\7\17\0\0\u0302\u0300\1\0\0\0\u0303"+
		"\u0304\1\0\0\0\u0304\u0302\1\0\0\0\u0304\u0305\1\0\0\0\u0305\u00c7\1\0"+
		"\0\0\u0306\u030a\5\r\0\0\u0308\u030b\5\n\0\0\u030a\u0308\1\0\0\0\u030a"+
		"\u030b\1\0\0\0\u030b\u030f\1\0\0\0\u030c\u030f\5\n\0\0\u030e\u0306\1\0"+
		"\0\0\u030e\u030c\1\0\0\0\u030f\u00c9\1\0\0\0\u0310\u0311\5}\0\0\u0311"+
		"\u0312\1\0\0\0\u0312\u0313\6c\7\0\u0313\u00cb\1\0\0\0\u0314\u031b\b\20"+
		"\0\0\u0316\u0318\5\\\0\0\u0318\u031b\t\0\0\0\u031a\u0314\1\0\0\0\u031a"+
		"\u0316\1\0\0\0\u031b\u031c\1\0\0\0\u031c\u031a\1\0\0\0\u031c\u031d\1\0"+
		"\0\0\u031d\u031e\1\0\0\0\u031e\u031f\6d\b\0\u031f\u00cd\1\0\0\0\u0320"+
		"\u0321\5]\0\0\u0321\u0322\1\0\0\0\u0322\u0323\6e\t\0\u0323\u00cf\1\0\0"+
		"\0/\0\1\1\1\2\1\3\1\u00e1\1\u00e7\0\u00f4\1\u0101\1\u0103\1\u010f\0\u0129"+
		"\1\u0139\1\u01e8\1\u01f7\1\u0213\1\u021b\1\u021d\1\u0228\1\u022a\1\u0237"+
		"\1\u023f\1\u0243\1\u0250\1\u025c\1\u025e\1\u0260\1\u0262\1\u0268\1\u0270"+
		"\1\u0278\1\u0284\1\u029e\1\u02a5\1\u02ab\1\u02b1\1\u02b7\1\u02bb\1\u02e6"+
		"\1\u02ec\1\u02f4\1\u02fc\1\u02fe\1\u0304\1\u030a\1\u030e\1\u031a\1\u031c"+
		"\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}