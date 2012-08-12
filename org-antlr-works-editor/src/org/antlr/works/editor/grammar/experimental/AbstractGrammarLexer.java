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
		ACTION_DOT=69, ACTION_LT=70, ACTION_GT=71, ACTION_LPAREN=72, ACTION_RPAREN=73, 
		ACTION_LBRACK=74, ACTION_RBRACK=75, ACTION_EQUALS=76, ACTION_COMMA=77, 
		ACTION_COLON2=78, ACTION_COLON=79, ACTION_MINUS=80, ACTION_ESCAPE=81, 
		ACTION_WORD=82, ACTION_REFERENCE=83, ACTION_COMMENT=84, ACTION_LITERAL=85, 
		ACTION_TEXT=86, ACTION_WS=87, ACTION_NEWLINE=88, END_ACTION=89;
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
		"'['", "'{'", "OPTIONS", "TOKENS", "'import'", "'fragment'", "'lexer'", 
		"'parser'", "'grammar'", "'protected'", "'public'", "'private'", "'returns'", 
		"'locals'", "'throws'", "'catch'", "'finally'", "'template'", "'mode'", 
		"':'", "'::'", "','", "';'", "'('", "')'", "'->'", "'<'", "'>'", "'='", 
		"'?'", "'*'", "'+'", "'+='", "'|'", "'$'", "'.'", "'..'", "'...'", "'@'", 
		"'#'", "'~'", "'}'", "ID", "INT", "STRING_LITERAL", "WS", "ERRCHAR", "ARG_ACTION_LT", 
		"ARG_ACTION_GT", "ARG_ACTION_LPAREN", "ARG_ACTION_RPAREN", "ARG_ACTION_EQUALS", 
		"ARG_ACTION_COMMA", "ARG_ACTION_ESCAPE", "ARG_ACTION_WORD", "ARG_ACTION_ELEMENT", 
		"ARG_ACTION_TEXT", "ARG_ACTION_WS", "ARG_ACTION_NEWLINE", "']'", "ACTION_DOT", 
		"ACTION_LT", "ACTION_GT", "ACTION_LPAREN", "ACTION_RPAREN", "ACTION_LBRACK", 
		"ACTION_RBRACK", "ACTION_EQUALS", "ACTION_COMMA", "ACTION_COLON2", "ACTION_COLON", 
		"'-'", "ACTION_ESCAPE", "ACTION_WORD", "ACTION_REFERENCE", "ACTION_COMMENT", 
		"ACTION_LITERAL", "ACTION_TEXT", "ACTION_WS", "ACTION_NEWLINE", "END_ACTION"
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
		"\2Y\u02b0\6\uffff\6\uffff\6\uffff\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3"+
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
		"\1\1\1\1\1\1\2\1\2\3\2\u00e0\b\2\1\3\5\3\u00e3\b\3\n\3\f\3\u00e6\t\3\1"+
		"\3\1\3\1\3\1\4\1\4\1\4\1\4\5\4\u00ef\b\4\n\4\f\4\u00f2\t\4\1\4\1\4\1\5"+
		"\1\5\1\5\1\5\5\5\u00fa\b\5\n\5\f\5\u00fd\t\5\1\5\1\5\1\6\1\6\1\6\1\6\5"+
		"\6\u0105\b\6\n\6\f\6\u0108\t\6\1\6\1\6\1\6\1\7\1\7\1\7\1\b\1\b\1\b\1\b"+
		"\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\t\5\t\u011d\b\t\n\t\f\t\u0120\t\t\1"+
		"\t\1\t\1\n\1\n\1\n\1\n\1\n\1\n\1\n\1\n\5\n\u012c\b\n\n\n\f\n\u012f\t\n"+
		"\1\n\1\n\1\13\1\13\1\13\1\13\1\13\1\13\1\13\1\f\1\f\1\f\1\f\1\f\1\f\1"+
		"\f\1\f\1\f\1\r\1\r\1\r\1\r\1\r\1\r\1\16\1\16\1\16\1\16\1\16\1\16\1\16"+
		"\1\17\1\17\1\17\1\17\1\17\1\17\1\17\1\17\1\20\1\20\1\20\1\20\1\20\1\20"+
		"\1\20\1\20\1\20\1\20\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\22\1\22\1\22"+
		"\1\22\1\22\1\22\1\22\1\22\1\23\1\23\1\23\1\23\1\23\1\23\1\23\1\23\1\24"+
		"\1\24\1\24\1\24\1\24\1\24\1\24\1\25\1\25\1\25\1\25\1\25\1\25\1\25\1\26"+
		"\1\26\1\26\1\26\1\26\1\26\1\27\1\27\1\27\1\27\1\27\1\27\1\27\1\27\1\30"+
		"\1\30\1\30\1\30\1\30\1\30\1\30\1\30\1\30\1\31\1\31\1\31\1\31\1\31\1\32"+
		"\1\32\1\33\1\33\1\33\1\34\1\34\1\35\1\35\1\36\1\36\1\37\1\37\1 \1 \1 "+
		"\1!\1!\1\"\1\"\1#\1#\1$\1$\1%\1%\1&\1&\1\'\1\'\1\'\1(\1(\1)\1)\1*\1*\1"+
		"+\1+\1+\1,\1,\1,\1,\1-\1-\1.\1.\1/\1/\1\60\1\60\1\61\1\61\5\61\u01d9\b"+
		"\61\n\61\f\61\u01dc\t\61\1\62\1\62\3\62\u01e0\b\62\1\63\1\63\1\64\1\64"+
		"\1\64\5\64\u01e7\b\64\n\64\f\64\u01ea\t\64\1\64\1\64\1\65\1\65\1\65\5"+
		"\65\u01f1\b\65\n\65\f\65\u01f4\t\65\1\65\1\65\1\66\1\66\1\66\1\67\4\67"+
		"\u01fc\b\67\13\67\f\67\u01fd\18\18\18\38\u0203\b8\58\u0205\b8\n8\f8\u0208"+
		"\t8\18\18\19\19\1:\1:\1:\3:\u0211\b:\1;\1;\1;\1;\1;\3;\u0218\b;\3;\u021a"+
		"\b;\3;\u021c\b;\3;\u021e\b;\1<\4<\u0221\b<\13<\f<\u0222\1<\1<\1=\1=\1"+
		">\1>\1?\1?\1@\1@\1A\1A\1B\1B\1C\1C\1D\1D\1E\1E\1F\1F\1G\1G\1G\1H\1H\5"+
		"H\u0240\bH\nH\fH\u0243\tH\1I\1I\3I\u0247\bI\1J\4J\u024a\bJ\13J\fJ\u024b"+
		"\1K\4K\u024f\bK\13K\fK\u0250\1L\1L\3L\u0255\bL\1L\3L\u0258\bL\1M\1M\1"+
		"M\1M\1N\1N\1N\1N\1O\1O\1P\1P\1Q\1Q\1R\1R\1S\1S\1T\1T\1U\1U\1V\1V\1W\1"+
		"W\1X\1X\1X\1Y\1Y\1Z\1Z\1[\1[\1[\1\\\4\\\u027f\b\\\13\\\f\\\u0280\1]\1"+
		"]\3]\u0285\b]\1^\1^\1_\1_\3_\u028b\b_\1`\1`\1`\4`\u0290\b`\13`\f`\u0291"+
		"\1a\4a\u0295\ba\13a\fa\u0296\1b\1b\3b\u029b\bb\1b\3b\u029e\bb\1c\1c\1"+
		"c\1c\1d\1d\1d\4d\u02a7\bd\13d\fd\u02a8\1d\1d\1e\1e\1e\1ef\4\4\uffff\6"+
		"\5\1\b\0\uffff\n\0\uffff\f\6\2\16\7\uffff\20\b\uffff\22\t\0\24\n\3\26"+
		"\13\uffff\30\f\uffff\32\r\uffff\34\16\uffff\36\17\uffff \20\uffff\"\21"+
		"\uffff$\22\uffff&\23\uffff(\24\uffff*\25\uffff,\26\uffff.\27\uffff\60"+
		"\30\uffff\62\31\uffff\64\32\uffff\66\33\uffff8\34\uffff:\35\uffff<\36"+
		"\uffff>\37\uffff@ \uffffB!\uffffD\"\uffffF#\uffffH$\uffffJ%\uffffL&\uffff"+
		"N\'\uffffP(\uffffR)\uffffT*\uffffV+\uffffX,\uffffZ-\uffff\\.\uffff^/\uffff"+
		"`\60\uffffb\61\uffffd\62\ufffff\63\uffffh\0\uffffj\0\uffffl\0\uffffn\0"+
		"\uffffp\0\uffffr\64\ufffft\65\uffffv\0\uffffx\0\uffffz\0\uffff|\66\4~"+
		"\0\uffff\u0080\0\uffff\u0082\0\uffff\u0084\67\uffff\u00868\uffff\u0088"+
		"9\uffff\u008a:\uffff\u008c;\uffff\u008e<\uffff\u0090=\uffff\u0092>\uffff"+
		"\u0094?\uffff\u0096@\uffff\u0098A\uffff\u009aB\uffff\u009cC\uffff\u009e"+
		"D\5\u00a0\0\6\u00a2E\uffff\u00a4F\uffff\u00a6G\uffff\u00a8H\uffff\u00aa"+
		"I\uffff\u00acJ\uffff\u00aeK\uffff\u00b0L\uffff\u00b2M\uffff\u00b4N\uffff"+
		"\u00b6O\uffff\u00b8P\uffff\u00baQ\uffff\u00bcR\uffff\u00beS\uffff\u00c0"+
		"T\uffff\u00c2U\uffff\u00c4V\uffff\u00c6W\uffff\u00c8X\uffff\u00caY\7\u00cc"+
		"\0\b\u00ce\0\t\4\0\1\2\3\26\2\n\n\r\r\1\"\"\5\609__\u00b7\u00b7\u0300"+
		"\u036f\u203f\u2040\rAZaz\u00c0\u00d6\u00d8\u00f6\u00f8\u02ff\u0370\u037d"+
		"\u037f\u1fff\u200c\u200d\u2070\u218f\u2c00\u2fef\u3001\ud7ff\uf900\ufdcf"+
		"\ufdf0\ufffd\1\'\'\1\"\"\2\'\'\\\\\3\609AFaf\b\"\"\'\'\\\\bbffnnrrtt\3"+
		"\t\n\f\r  \2\n\n\r\r\3\t\t\f\f  \3\t\n\f\r  \5$$\609AZ__az\4\609AZ__a"+
		"z\r\t\n\r\r  \"\"$$\'),,\609<>AZ\\]__az\2\t\t  \4\609AZ__az\2**//\f\t"+
		"\n\r\r  \"\"$$\'),:<>A]__a{}}\2\t\t  \1\\]\u02c6\0\4\1\0\0\0\0\6\1\0\0"+
		"\0\0\f\1\0\0\0\0\16\1\0\0\0\0\20\1\0\0\0\0\22\1\0\0\0\0\24\1\0\0\0\0\26"+
		"\1\0\0\0\0\30\1\0\0\0\0\32\1\0\0\0\0\34\1\0\0\0\0\36\1\0\0\0\0 \1\0\0"+
		"\0\0\"\1\0\0\0\0$\1\0\0\0\0&\1\0\0\0\0(\1\0\0\0\0*\1\0\0\0\0,\1\0\0\0"+
		"\0.\1\0\0\0\0\60\1\0\0\0\0\62\1\0\0\0\0\64\1\0\0\0\0\66\1\0\0\0\08\1\0"+
		"\0\0\0:\1\0\0\0\0<\1\0\0\0\0>\1\0\0\0\0@\1\0\0\0\0B\1\0\0\0\0D\1\0\0\0"+
		"\0F\1\0\0\0\0H\1\0\0\0\0J\1\0\0\0\0L\1\0\0\0\0N\1\0\0\0\0P\1\0\0\0\0R"+
		"\1\0\0\0\0T\1\0\0\0\0V\1\0\0\0\0X\1\0\0\0\0Z\1\0\0\0\0\\\1\0\0\0\0^\1"+
		"\0\0\0\0`\1\0\0\0\0b\1\0\0\0\0d\1\0\0\0\0f\1\0\0\0\0r\1\0\0\0\0t\1\0\0"+
		"\0\0|\1\0\0\0\0\u0084\1\0\0\0\1\u0086\1\0\0\0\1\u0088\1\0\0\0\1\u008a"+
		"\1\0\0\0\1\u008c\1\0\0\0\1\u008e\1\0\0\0\1\u0090\1\0\0\0\1\u0092\1\0\0"+
		"\0\1\u0094\1\0\0\0\1\u0096\1\0\0\0\1\u0098\1\0\0\0\1\u009a\1\0\0\0\1\u009c"+
		"\1\0\0\0\1\u009e\1\0\0\0\2\u00a0\1\0\0\0\2\u00a2\1\0\0\0\2\u00a4\1\0\0"+
		"\0\2\u00a6\1\0\0\0\2\u00a8\1\0\0\0\2\u00aa\1\0\0\0\2\u00ac\1\0\0\0\2\u00ae"+
		"\1\0\0\0\2\u00b0\1\0\0\0\2\u00b2\1\0\0\0\2\u00b4\1\0\0\0\2\u00b6\1\0\0"+
		"\0\2\u00b8\1\0\0\0\2\u00ba\1\0\0\0\2\u00bc\1\0\0\0\2\u00be\1\0\0\0\2\u00c0"+
		"\1\0\0\0\2\u00c2\1\0\0\0\2\u00c4\1\0\0\0\2\u00c6\1\0\0\0\2\u00c8\1\0\0"+
		"\0\2\u00ca\1\0\0\0\3\u00cc\1\0\0\0\3\u00ce\1\0\0\0\4\u00d0\1\0\0\0\6\u00d6"+
		"\1\0\0\0\b\u00df\1\0\0\0\n\u00e4\1\0\0\0\f\u00ea\1\0\0\0\16\u00f5\1\0"+
		"\0\0\20\u0100\1\0\0\0\22\u010c\1\0\0\0\24\u010f\1\0\0\0\26\u0113\1\0\0"+
		"\0\30\u0123\1\0\0\0\32\u0132\1\0\0\0\34\u0139\1\0\0\0\36\u0142\1\0\0\0"+
		" \u0148\1\0\0\0\"\u014f\1\0\0\0$\u0157\1\0\0\0&\u0161\1\0\0\0(\u0168\1"+
		"\0\0\0*\u0170\1\0\0\0,\u0178\1\0\0\0.\u017f\1\0\0\0\60\u0186\1\0\0\0\62"+
		"\u018c\1\0\0\0\64\u0194\1\0\0\0\66\u019d\1\0\0\08\u01a2\1\0\0\0:\u01a4"+
		"\1\0\0\0<\u01a7\1\0\0\0>\u01a9\1\0\0\0@\u01ab\1\0\0\0B\u01ad\1\0\0\0D"+
		"\u01af\1\0\0\0F\u01b2\1\0\0\0H\u01b4\1\0\0\0J\u01b6\1\0\0\0L\u01b8\1\0"+
		"\0\0N\u01ba\1\0\0\0P\u01bc\1\0\0\0R\u01be\1\0\0\0T\u01c1\1\0\0\0V\u01c3"+
		"\1\0\0\0X\u01c5\1\0\0\0Z\u01c7\1\0\0\0\\\u01ca\1\0\0\0^\u01ce\1\0\0\0"+
		"`\u01d0\1\0\0\0b\u01d2\1\0\0\0d\u01d4\1\0\0\0f\u01d6\1\0\0\0h\u01df\1"+
		"\0\0\0j\u01e1\1\0\0\0l\u01e3\1\0\0\0n\u01ed\1\0\0\0p\u01f7\1\0\0\0r\u01fb"+
		"\1\0\0\0t\u01ff\1\0\0\0v\u020b\1\0\0\0x\u020d\1\0\0\0z\u0212\1\0\0\0|"+
		"\u0220\1\0\0\0~\u0226\1\0\0\0\u0080\u0228\1\0\0\0\u0082\u022a\1\0\0\0"+
		"\u0084\u022c\1\0\0\0\u0086\u022e\1\0\0\0\u0088\u0230\1\0\0\0\u008a\u0232"+
		"\1\0\0\0\u008c\u0234\1\0\0\0\u008e\u0236\1\0\0\0\u0090\u0238\1\0\0\0\u0092"+
		"\u023a\1\0\0\0\u0094\u023d\1\0\0\0\u0096\u0246\1\0\0\0\u0098\u0249\1\0"+
		"\0\0\u009a\u024e\1\0\0\0\u009c\u0257\1\0\0\0\u009e\u0259\1\0\0\0\u00a0"+
		"\u025d\1\0\0\0\u00a2\u0261\1\0\0\0\u00a4\u0263\1\0\0\0\u00a6\u0265\1\0"+
		"\0\0\u00a8\u0267\1\0\0\0\u00aa\u0269\1\0\0\0\u00ac\u026b\1\0\0\0\u00ae"+
		"\u026d\1\0\0\0\u00b0\u026f\1\0\0\0\u00b2\u0271\1\0\0\0\u00b4\u0273\1\0"+
		"\0\0\u00b6\u0276\1\0\0\0\u00b8\u0278\1\0\0\0\u00ba\u027a\1\0\0\0\u00bc"+
		"\u027e\1\0\0\0\u00be\u0282\1\0\0\0\u00c0\u0286\1\0\0\0\u00c2\u028a\1\0"+
		"\0\0\u00c4\u028f\1\0\0\0\u00c6\u0294\1\0\0\0\u00c8\u029d\1\0\0\0\u00ca"+
		"\u029f\1\0\0\0\u00cc\u02a6\1\0\0\0\u00ce\u02ac\1\0\0\0\u00d0\u00d1\5/"+
		"\0\0\u00d1\u00d2\5*\0\0\u00d2\u00d3\5*\0\0\u00d3\u00d4\1\0\0\0\u00d4\u00d5"+
		"\3\n\3\0\u00d5\5\1\0\0\0\u00d6\u00d7\5/\0\0\u00d7\u00d8\5*\0\0\u00d8\u00d9"+
		"\1\0\0\0\u00d9\u00da\3\n\3\0\u00da\u00db\1\0\0\0\u00db\u00dc\6\1\1\0\u00dc"+
		"\7\1\0\0\0\u00dd\u00e0\3\6\1\0\u00de\u00e0\3\f\4\0\u00df\u00dd\1\0\0\0"+
		"\u00df\u00de\1\0\0\0\u00e0\t\1\0\0\0\u00e1\u00e3\t\0\0\0\u00e2\u00e1\1"+
		"\0\0\0\u00e3\u00e6\1\0\0\0\u00e4\u00e5\1\0\0\0\u00e4\u00e2\1\0\0\0\u00e5"+
		"\u00e7\1\0\0\0\u00e6\u00e4\1\0\0\0\u00e7\u00e8\5*\0\0\u00e8\u00e9\5/\0"+
		"\0\u00e9\13\1\0\0\0\u00ea\u00eb\5/\0\0\u00eb\u00ec\5/\0\0\u00ec\u00f0"+
		"\1\0\0\0\u00ed\u00ef\b\0\0\0\u00ee\u00ed\1\0\0\0\u00ef\u00f2\1\0\0\0\u00f0"+
		"\u00ee\1\0\0\0\u00f0\u00f1\1\0\0\0\u00f1\u00f3\1\0\0\0\u00f2\u00f0\1\0"+
		"\0\0\u00f3\u00f4\6\4\2\0\u00f4\r\1\0\0\0\u00f5\u00fb\5\"\0\0\u00f6\u00f7"+
		"\5\\\0\0\u00f7\u00fa\t\0\0\0\u00f8\u00fa\b\1\0\0\u00f9\u00f6\1\0\0\0\u00f9"+
		"\u00f8\1\0\0\0\u00fa\u00fd\1\0\0\0\u00fb\u00f9\1\0\0\0\u00fb\u00fc\1\0"+
		"\0\0\u00fc\u00fe\1\0\0\0\u00fd\u00fb\1\0\0\0\u00fe\u00ff\5\"\0\0\u00ff"+
		"\17\1\0\0\0\u0100\u0101\5<\0\0\u0101\u0102\5<\0\0\u0102\u0106\1\0\0\0"+
		"\u0103\u0105\t\0\0\0\u0104\u0103\1\0\0\0\u0105\u0108\1\0\0\0\u0106\u0107"+
		"\1\0\0\0\u0106\u0104\1\0\0\0\u0107\u0109\1\0\0\0\u0108\u0106\1\0\0\0\u0109"+
		"\u010a\5>\0\0\u010a\u010b\5>\0\0\u010b\21\1\0\0\0\u010c\u010d\5[\0\0\u010d"+
		"\u010e\6\7\0\0\u010e\23\1\0\0\0\u010f\u0110\5{\0\0\u0110\u0111\1\0\0\0"+
		"\u0111\u0112\6\b\3\0\u0112\25\1\0\0\0\u0113\u0114\5o\0\0\u0114\u0115\5"+
		"p\0\0\u0115\u0116\5t\0\0\u0116\u0117\5i\0\0\u0117\u0118\5o\0\0\u0118\u0119"+
		"\5n\0\0\u0119\u011a\5s\0\0\u011a\u011e\1\0\0\0\u011b\u011d\3\u0082?\0"+
		"\u011c\u011b\1\0\0\0\u011d\u0120\1\0\0\0\u011e\u011c\1\0\0\0\u011e\u011f"+
		"\1\0\0\0\u011f\u0121\1\0\0\0\u0120\u011e\1\0\0\0\u0121\u0122\5{\0\0\u0122"+
		"\27\1\0\0\0\u0123\u0124\5t\0\0\u0124\u0125\5o\0\0\u0125\u0126\5k\0\0\u0126"+
		"\u0127\5e\0\0\u0127\u0128\5n\0\0\u0128\u0129\5s\0\0\u0129\u012d\1\0\0"+
		"\0\u012a\u012c\3\u0082?\0\u012b\u012a\1\0\0\0\u012c\u012f\1\0\0\0\u012d"+
		"\u012b\1\0\0\0\u012d\u012e\1\0\0\0\u012e\u0130\1\0\0\0\u012f\u012d\1\0"+
		"\0\0\u0130\u0131\5{\0\0\u0131\31\1\0\0\0\u0132\u0133\5i\0\0\u0133\u0134"+
		"\5m\0\0\u0134\u0135\5p\0\0\u0135\u0136\5o\0\0\u0136\u0137\5r\0\0\u0137"+
		"\u0138\5t\0\0\u0138\33\1\0\0\0\u0139\u013a\5f\0\0\u013a\u013b\5r\0\0\u013b"+
		"\u013c\5a\0\0\u013c\u013d\5g\0\0\u013d\u013e\5m\0\0\u013e\u013f\5e\0\0"+
		"\u013f\u0140\5n\0\0\u0140\u0141\5t\0\0\u0141\35\1\0\0\0\u0142\u0143\5"+
		"l\0\0\u0143\u0144\5e\0\0\u0144\u0145\5x\0\0\u0145\u0146\5e\0\0\u0146\u0147"+
		"\5r\0\0\u0147\37\1\0\0\0\u0148\u0149\5p\0\0\u0149\u014a\5a\0\0\u014a\u014b"+
		"\5r\0\0\u014b\u014c\5s\0\0\u014c\u014d\5e\0\0\u014d\u014e\5r\0\0\u014e"+
		"!\1\0\0\0\u014f\u0150\5g\0\0\u0150\u0151\5r\0\0\u0151\u0152\5a\0\0\u0152"+
		"\u0153\5m\0\0\u0153\u0154\5m\0\0\u0154\u0155\5a\0\0\u0155\u0156\5r\0\0"+
		"\u0156#\1\0\0\0\u0157\u0158\5p\0\0\u0158\u0159\5r\0\0\u0159\u015a\5o\0"+
		"\0\u015a\u015b\5t\0\0\u015b\u015c\5e\0\0\u015c\u015d\5c\0\0\u015d\u015e"+
		"\5t\0\0\u015e\u015f\5e\0\0\u015f\u0160\5d\0\0\u0160%\1\0\0\0\u0161\u0162"+
		"\5p\0\0\u0162\u0163\5u\0\0\u0163\u0164\5b\0\0\u0164\u0165\5l\0\0\u0165"+
		"\u0166\5i\0\0\u0166\u0167\5c\0\0\u0167\'\1\0\0\0\u0168\u0169\5p\0\0\u0169"+
		"\u016a\5r\0\0\u016a\u016b\5i\0\0\u016b\u016c\5v\0\0\u016c\u016d\5a\0\0"+
		"\u016d\u016e\5t\0\0\u016e\u016f\5e\0\0\u016f)\1\0\0\0\u0170\u0171\5r\0"+
		"\0\u0171\u0172\5e\0\0\u0172\u0173\5t\0\0\u0173\u0174\5u\0\0\u0174\u0175"+
		"\5r\0\0\u0175\u0176\5n\0\0\u0176\u0177\5s\0\0\u0177+\1\0\0\0\u0178\u0179"+
		"\5l\0\0\u0179\u017a\5o\0\0\u017a\u017b\5c\0\0\u017b\u017c\5a\0\0\u017c"+
		"\u017d\5l\0\0\u017d\u017e\5s\0\0\u017e-\1\0\0\0\u017f\u0180\5t\0\0\u0180"+
		"\u0181\5h\0\0\u0181\u0182\5r\0\0\u0182\u0183\5o\0\0\u0183\u0184\5w\0\0"+
		"\u0184\u0185\5s\0\0\u0185/\1\0\0\0\u0186\u0187\5c\0\0\u0187\u0188\5a\0"+
		"\0\u0188\u0189\5t\0\0\u0189\u018a\5c\0\0\u018a\u018b\5h\0\0\u018b\61\1"+
		"\0\0\0\u018c\u018d\5f\0\0\u018d\u018e\5i\0\0\u018e\u018f\5n\0\0\u018f"+
		"\u0190\5a\0\0\u0190\u0191\5l\0\0\u0191\u0192\5l\0\0\u0192\u0193\5y\0\0"+
		"\u0193\63\1\0\0\0\u0194\u0195\5t\0\0\u0195\u0196\5e\0\0\u0196\u0197\5"+
		"m\0\0\u0197\u0198\5p\0\0\u0198\u0199\5l\0\0\u0199\u019a\5a\0\0\u019a\u019b"+
		"\5t\0\0\u019b\u019c\5e\0\0\u019c\65\1\0\0\0\u019d\u019e\5m\0\0\u019e\u019f"+
		"\5o\0\0\u019f\u01a0\5d\0\0\u01a0\u01a1\5e\0\0\u01a1\67\1\0\0\0\u01a2\u01a3"+
		"\5:\0\0\u01a39\1\0\0\0\u01a4\u01a5\5:\0\0\u01a5\u01a6\5:\0\0\u01a6;\1"+
		"\0\0\0\u01a7\u01a8\5,\0\0\u01a8=\1\0\0\0\u01a9\u01aa\5;\0\0\u01aa?\1\0"+
		"\0\0\u01ab\u01ac\5(\0\0\u01acA\1\0\0\0\u01ad\u01ae\5)\0\0\u01aeC\1\0\0"+
		"\0\u01af\u01b0\5-\0\0\u01b0\u01b1\5>\0\0\u01b1E\1\0\0\0\u01b2\u01b3\5"+
		"<\0\0\u01b3G\1\0\0\0\u01b4\u01b5\5>\0\0\u01b5I\1\0\0\0\u01b6\u01b7\5="+
		"\0\0\u01b7K\1\0\0\0\u01b8\u01b9\5?\0\0\u01b9M\1\0\0\0\u01ba\u01bb\5*\0"+
		"\0\u01bbO\1\0\0\0\u01bc\u01bd\5+\0\0\u01bdQ\1\0\0\0\u01be\u01bf\5+\0\0"+
		"\u01bf\u01c0\5=\0\0\u01c0S\1\0\0\0\u01c1\u01c2\5|\0\0\u01c2U\1\0\0\0\u01c3"+
		"\u01c4\5$\0\0\u01c4W\1\0\0\0\u01c5\u01c6\5.\0\0\u01c6Y\1\0\0\0\u01c7\u01c8"+
		"\5.\0\0\u01c8\u01c9\5.\0\0\u01c9[\1\0\0\0\u01ca\u01cb\5.\0\0\u01cb\u01cc"+
		"\5.\0\0\u01cc\u01cd\5.\0\0\u01cd]\1\0\0\0\u01ce\u01cf\5@\0\0\u01cf_\1"+
		"\0\0\0\u01d0\u01d1\5#\0\0\u01d1a\1\0\0\0\u01d2\u01d3\5~\0\0\u01d3c\1\0"+
		"\0\0\u01d4\u01d5\5}\0\0\u01d5e\1\0\0\0\u01d6\u01da\3j\63\0\u01d7\u01d9"+
		"\3h\62\0\u01d8\u01d7\1\0\0\0\u01d9\u01dc\1\0\0\0\u01da\u01d8\1\0\0\0\u01da"+
		"\u01db\1\0\0\0\u01dbg\1\0\0\0\u01dc\u01da\1\0\0\0\u01dd\u01e0\3j\63\0"+
		"\u01de\u01e0\7\2\0\0\u01df\u01dd\1\0\0\0\u01df\u01de\1\0\0\0\u01e0i\1"+
		"\0\0\0\u01e1\u01e2\7\3\0\0\u01e2k\1\0\0\0\u01e3\u01e8\5\'\0\0\u01e4\u01e7"+
		"\3p\66\0\u01e5\u01e7\b\4\0\0\u01e6\u01e4\1\0\0\0\u01e6\u01e5\1\0\0\0\u01e7"+
		"\u01ea\1\0\0\0\u01e8\u01e6\1\0\0\0\u01e8\u01e9\1\0\0\0\u01e9\u01eb\1\0"+
		"\0\0\u01ea\u01e8\1\0\0\0\u01eb\u01ec\5\'\0\0\u01ecm\1\0\0\0\u01ed\u01f2"+
		"\5\"\0\0\u01ee\u01f1\3p\66\0\u01ef\u01f1\b\5\0\0\u01f0\u01ee\1\0\0\0\u01f0"+
		"\u01ef\1\0\0\0\u01f1\u01f4\1\0\0\0\u01f2\u01f0\1\0\0\0\u01f2\u01f3\1\0"+
		"\0\0\u01f3\u01f5\1\0\0\0\u01f4\u01f2\1\0\0\0\u01f5\u01f6\5\"\0\0\u01f6"+
		"o\1\0\0\0\u01f7\u01f8\5\\\0\0\u01f8\u01f9\t\0\0\0\u01f9q\1\0\0\0\u01fa"+
		"\u01fc\2\609\0\u01fb\u01fa\1\0\0\0\u01fc\u01fd\1\0\0\0\u01fd\u01fb\1\0"+
		"\0\0\u01fd\u01fe\1\0\0\0\u01fes\1\0\0\0\u01ff\u0206\5\'\0\0\u0200\u0203"+
		"\3x:\0\u0201\u0203\b\6\0\0\u0202\u0200\1\0\0\0\u0202\u0201\1\0\0\0\u0203"+
		"\u0205\1\0\0\0\u0204\u0202\1\0\0\0\u0205\u0208\1\0\0\0\u0206\u0204\1\0"+
		"\0\0\u0206\u0207\1\0\0\0\u0207\u0209\1\0\0\0\u0208\u0206\1\0\0\0\u0209"+
		"\u020a\5\'\0\0\u020au\1\0\0\0\u020b\u020c\7\7\0\0\u020cw\1\0\0\0\u020d"+
		"\u0210\5\\\0\0\u020e\u0211\7\b\0\0\u020f\u0211\3z;\0\u0210\u020e\1\0\0"+
		"\0\u0210\u020f\1\0\0\0\u0211y\1\0\0\0\u0212\u021d\5u\0\0\u0213\u021b\3"+
		"v9\0\u0214\u0219\3v9\0\u0215\u0217\3v9\0\u0216\u0218\3v9\0\u0217\u0216"+
		"\1\0\0\0\u0217\u0218\1\0\0\0\u0218\u021a\1\0\0\0\u0219\u0215\1\0\0\0\u0219"+
		"\u021a\1\0\0\0\u021a\u021c\1\0\0\0\u021b\u0214\1\0\0\0\u021b\u021c\1\0"+
		"\0\0\u021c\u021e\1\0\0\0\u021d\u0213\1\0\0\0\u021d\u021e\1\0\0\0\u021e"+
		"{\1\0\0\0\u021f\u0221\7\t\0\0\u0220\u021f\1\0\0\0\u0221\u0222\1\0\0\0"+
		"\u0222\u0220\1\0\0\0\u0222\u0223\1\0\0\0\u0223\u0224\1\0\0\0\u0224\u0225"+
		"\6<\4\0\u0225}\1\0\0\0\u0226\u0227\7\n\0\0\u0227\177\1\0\0\0\u0228\u0229"+
		"\7\13\0\0\u0229\u0081\1\0\0\0\u022a\u022b\7\f\0\0\u022b\u0083\1\0\0\0"+
		"\u022c\u022d\t\0\0\0\u022d\u0085\1\0\0\0\u022e\u022f\5<\0\0\u022f\u0087"+
		"\1\0\0\0\u0230\u0231\5>\0\0\u0231\u0089\1\0\0\0\u0232\u0233\5(\0\0\u0233"+
		"\u008b\1\0\0\0\u0234\u0235\5)\0\0\u0235\u008d\1\0\0\0\u0236\u0237\5=\0"+
		"\0\u0237\u008f\1\0\0\0\u0238\u0239\5,\0\0\u0239\u0091\1\0\0\0\u023a\u023b"+
		"\5\\\0\0\u023b\u023c\t\0\0\0\u023c\u0093\1\0\0\0\u023d\u0241\7\r\0\0\u023e"+
		"\u0240\7\16\0\0\u023f\u023e\1\0\0\0\u0240\u0243\1\0\0\0\u0241\u023f\1"+
		"\0\0\0\u0241\u0242\1\0\0\0\u0242\u0095\1\0\0\0\u0243\u0241\1\0\0\0\u0244"+
		"\u0247\3n\65\0\u0245\u0247\3l\64\0\u0246\u0244\1\0\0\0\u0246\u0245\1\0"+
		"\0\0\u0247\u0097\1\0\0\0\u0248\u024a\b\17\0\0\u0249\u0248\1\0\0\0\u024a"+
		"\u024b\1\0\0\0\u024b\u0249\1\0\0\0\u024b\u024c\1\0\0\0\u024c\u0099\1\0"+
		"\0\0\u024d\u024f\7\20\0\0\u024e\u024d\1\0\0\0\u024f\u0250\1\0\0\0\u0250"+
		"\u024e\1\0\0\0\u0250\u0251\1\0\0\0\u0251\u009b\1\0\0\0\u0252\u0254\5\r"+
		"\0\0\u0253\u0255\5\n\0\0\u0254\u0253\1\0\0\0\u0254\u0255\1\0\0\0\u0255"+
		"\u0258\1\0\0\0\u0256\u0258\5\n\0\0\u0257\u0252\1\0\0\0\u0257\u0256\1\0"+
		"\0\0\u0258\u009d\1\0\0\0\u0259\u025a\5]\0\0\u025a\u025b\1\0\0\0\u025b"+
		"\u025c\6M\5\0\u025c\u009f\1\0\0\0\u025d\u025e\5{\0\0\u025e\u025f\1\0\0"+
		"\0\u025f\u0260\6N\6\0\u0260\u00a1\1\0\0\0\u0261\u0262\5.\0\0\u0262\u00a3"+
		"\1\0\0\0\u0263\u0264\5<\0\0\u0264\u00a5\1\0\0\0\u0265\u0266\5>\0\0\u0266"+
		"\u00a7\1\0\0\0\u0267\u0268\5(\0\0\u0268\u00a9\1\0\0\0\u0269\u026a\5)\0"+
		"\0\u026a\u00ab\1\0\0\0\u026b\u026c\5[\0\0\u026c\u00ad\1\0\0\0\u026d\u026e"+
		"\5]\0\0\u026e\u00af\1\0\0\0\u026f\u0270\5=\0\0\u0270\u00b1\1\0\0\0\u0271"+
		"\u0272\5,\0\0\u0272\u00b3\1\0\0\0\u0273\u0274\5:\0\0\u0274\u0275\5:\0"+
		"\0\u0275\u00b5\1\0\0\0\u0276\u0277\5:\0\0\u0277\u00b7\1\0\0\0\u0278\u0279"+
		"\5-\0\0\u0279\u00b9\1\0\0\0\u027a\u027b\5\\\0\0\u027b\u027c\t\0\0\0\u027c"+
		"\u00bb\1\0\0\0\u027d\u027f\7\21\0\0\u027e\u027d\1\0\0\0\u027f\u0280\1"+
		"\0\0\0\u0280\u027e\1\0\0\0\u0280\u0281\1\0\0\0\u0281\u00bd\1\0\0\0\u0282"+
		"\u0284\5$\0\0\u0283\u0285\3\u00bc\\\0\u0284\u0283\1\0\0\0\u0284\u0285"+
		"\1\0\0\0\u0285\u00bf\1\0\0\0\u0286\u0287\3\b\2\0\u0287\u00c1\1\0\0\0\u0288"+
		"\u028b\3n\65\0\u0289\u028b\3l\64\0\u028a\u0288\1\0\0\0\u028a\u0289\1\0"+
		"\0\0\u028b\u00c3\1\0\0\0\u028c\u028d\5/\0\0\u028d\u0290\b\22\0\0\u028e"+
		"\u0290\b\23\0\0\u028f\u028c\1\0\0\0\u028f\u028e\1\0\0\0\u0290\u0291\1"+
		"\0\0\0\u0291\u028f\1\0\0\0\u0291\u0292\1\0\0\0\u0292\u00c5\1\0\0\0\u0293"+
		"\u0295\7\24\0\0\u0294\u0293\1\0\0\0\u0295\u0296\1\0\0\0\u0296\u0294\1"+
		"\0\0\0\u0296\u0297\1\0\0\0\u0297\u00c7\1\0\0\0\u0298\u029a\5\r\0\0\u0299"+
		"\u029b\5\n\0\0\u029a\u0299\1\0\0\0\u029a\u029b\1\0\0\0\u029b\u029e\1\0"+
		"\0\0\u029c\u029e\5\n\0\0\u029d\u0298\1\0\0\0\u029d\u029c\1\0\0\0\u029e"+
		"\u00c9\1\0\0\0\u029f\u02a0\5}\0\0\u02a0\u02a1\1\0\0\0\u02a1\u02a2\6c\7"+
		"\0\u02a2\u00cb\1\0\0\0\u02a3\u02a7\b\25\0\0\u02a4\u02a5\5\\\0\0\u02a5"+
		"\u02a7\t\0\0\0\u02a6\u02a3\1\0\0\0\u02a6\u02a4\1\0\0\0\u02a7\u02a8\1\0"+
		"\0\0\u02a8\u02a6\1\0\0\0\u02a8\u02a9\1\0\0\0\u02a9\u02aa\1\0\0\0\u02aa"+
		"\u02ab\6d\b\0\u02ab\u00cd\1\0\0\0\u02ac\u02ad\5]\0\0\u02ad\u02ae\1\0\0"+
		"\0\u02ae\u02af\6e\t\0\u02af\u00cf\1\0\0\0+\0\1\1\1\2\1\3\1\u00df\1\u00e4"+
		"\0\u00f0\1\u00f9\1\u00fb\1\u0106\0\u011e\1\u012d\1\u01da\1\u01df\1\u01e6"+
		"\1\u01e8\1\u01f0\1\u01f2\1\u01fd\1\u0202\1\u0206\1\u0210\1\u0217\1\u0219"+
		"\1\u021b\1\u021d\1\u0222\1\u0241\1\u0246\1\u024b\1\u0250\1\u0254\1\u0257"+
		"\1\u0280\1\u0284\1\u028a\1\u028f\1\u0291\1\u0296\1\u029a\1\u029d\1\u02a6"+
		"\1\u02a8\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}