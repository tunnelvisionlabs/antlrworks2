// $ANTLR ANTLRVersion> AbstractGrammarHighlighterLexer.java generatedTimestamp>
/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.highlighter4;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

public abstract class AbstractGrammarHighlighterLexer extends Lexer {
	public static final int
		InvalidGrammarOption=1, ValidGrammarOption=2, LCURLY=3, LEXER=4, PARSER=5, 
		CATCH=6, FINALLY=7, GRAMMAR=8, PRIVATE=9, PROTECTED=10, PUBLIC=11, RETURNS=12, 
		THROWS=13, IMPORT=14, FRAGMENT=15, TOKENS=16, OPTIONS=17, MODE=18, LOCALS=19, 
		TREE=20, SCOPE=21, OPEN_ELEMENT_OPTION=22, CLOSE_ELEMENT_OPTION=23, AMPERSAND=24, 
		COMMA=25, QUESTION=26, TREE_BEGIN=27, LPAREN=28, RPAREN=29, COLON=30, 
		STAR=31, PLUS=32, ASSIGN=33, PLUS_ASSIGN=34, IMPLIES=35, REWRITE=36, SEMI=37, 
		ROOT=38, BANG=39, OR=40, WILDCARD=41, ETC=42, RANGE=43, NOT=44, LBRACK=45, 
		RBRACK=46, RCURLY=47, DOLLAR=48, LABEL=49, IDENTIFIER=50, INT=51, DIRECTIVE=52, 
		REFERENCE=53, WS=54, NEWLINE=55, COMMENT=56, ML_COMMENT=57, CHAR_LITERAL=58, 
		STRING_LITERAL=59, ANYCHAR=60, CONTINUE_ML_COMMENT=61, END_ML_COMMENT=62, 
		ML_COMMENT_STAR=63, ArgAction_TEXT=64, ArgAction_CHAR_LITERAL=65, ArgAction_STRING_LITERAL=66, 
		ArgAction_ESCAPE=67, ArgAction_REFERENCE=68, Action_COMMENT=69, Action_ML_COMMENT=70, 
		Action_TEXT=71, Action_CHAR_LITERAL=72, Action_STRING_LITERAL=73, Action_ESCAPE=74, 
		Action_REFERENCE=75, LexerCharSet_ESCAPE=76, LexerCharSet_INVALID_ESCAPE=77, 
		LexerCharSet_TEXT=78;
	public static final int BlockComment = 1;
	public static final int ArgAction = 2;
	public static final int NonActionBrace = 3;
	public static final int Action = 4;
	public static final int LexerCharSet = 5;
	public static String[] modeNames = {
		"DEFAULT_MODE", "BlockComment", "ArgAction", "NonActionBrace", "Action", 
		"LexerCharSet"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"InvalidGrammarOption", "ValidGrammarOption", "LCURLY", "'lexer'", "'parser'", 
		"'catch'", "'finally'", "'grammar'", "'private'", "'protected'", "'public'", 
		"'returns'", "'throws'", "'import'", "'fragment'", "TOKENS", "OPTIONS", 
		"'mode'", "'locals'", "'tree'", "'scope'", "'<'", "'>'", "'@'", "','", 
		"'?'", "'^('", "'('", "')'", "':'", "STAR", "'+'", "'='", "'+='", "'=>'", 
		"'->'", "';'", "'^'", "'!'", "'|'", "'.'", "'...'", "'..'", "'~'", "'['", 
		"RBRACK", "RCURLY", "'$'", "LABEL", "IDENTIFIER", "INT", "DIRECTIVE", 
		"REFERENCE", "WS", "NEWLINE", "COMMENT", "ML_COMMENT", "CHAR_LITERAL", 
		"STRING_LITERAL", "ANYCHAR", "CONTINUE_ML_COMMENT", "'*/'", "ML_COMMENT_STAR", 
		"ArgAction_TEXT", "ArgAction_CHAR_LITERAL", "ArgAction_STRING_LITERAL", 
		"ArgAction_ESCAPE", "ArgAction_REFERENCE", "Action_COMMENT", "Action_ML_COMMENT", 
		"Action_TEXT", "Action_CHAR_LITERAL", "Action_STRING_LITERAL", "Action_ESCAPE", 
		"Action_REFERENCE", "LexerCharSet_ESCAPE", "LexerCharSet_INVALID_ESCAPE", 
		"LexerCharSet_TEXT"
	};
	public static final String[] ruleNames = {
		"LEXER", "PARSER", "CATCH", "FINALLY", "GRAMMAR", "PRIVATE", "PROTECTED", 
		"PUBLIC", "RETURNS", "THROWS", "IMPORT", "FRAGMENT", "TOKENS", "OPTIONS", 
		"MODE", "LOCALS", "TREE", "SCOPE", "OPEN_ELEMENT_OPTION", "CLOSE_ELEMENT_OPTION", 
		"AMPERSAND", "COMMA", "QUESTION", "TREE_BEGIN", "LPAREN", "RPAREN", "COLON", 
		"STAR", "PLUS", "ASSIGN", "PLUS_ASSIGN", "IMPLIES", "REWRITE", "SEMI", 
		"ROOT", "BANG", "OR", "WILDCARD", "ETC", "RANGE", "NOT", "LBRACK", "RBRACK", 
		"LCURLY", "RCURLY", "DOLLAR", "LABEL", "IDENTIFIER", "INT", "DIRECTIVE", 
		"REFERENCE", "WS", "NEWLINE", "COMMENT", "ML_COMMENT", "CHAR_LITERAL", 
		"STRING_LITERAL", "IGNORED", "XDIGIT", "ANYCHAR", "BlockComment_NEWLINE", 
		"CONTINUE_ML_COMMENT", "END_ML_COMMENT", "ML_COMMENT_STAR", "BlockComment_ANYCHAR", 
		"ArgAction_NEWLINE", "ArgAction_LBRACK", "ArgAction_RBRACK", "ArgAction_TEXT", 
		"ArgAction_CHAR_LITERAL", "ArgAction_STRING_LITERAL", "ArgAction_ESCAPE", 
		"ArgAction_REFERENCE", "ArgAction_SPECIAL", "ArgAction_ANYCHAR", "NonActionBrace_NEWLINE", 
		"NonActionBrace_WS", "NonActionBrace_LCURLY", "Action_NEWLINE", "Action_COMMENT", 
		"Action_ML_COMMENT", "Action_LCURLY", "Action_RCURLY", "Action_TEXT", 
		"Action_CHAR_LITERAL", "Action_STRING_LITERAL", "Action_ESCAPE", "Action_REFERENCE", 
		"Action_SPECIAL", "Action_ANYCHAR", "LexerCharSet_NEWLINE", "LexerCharSet_ESCAPE", 
		"LexerCharSet_INVALID_ESCAPE", "LexerCharSet_TEXT", "LexerCharSet_RBRACK"
	};


	protected int getMultilineCommentType() {
	    return _modeStack.peek()==DEFAULT_MODE ? ML_COMMENT : Action_ML_COMMENT;
	}

	protected abstract void handleBeginArgAction();


	public AbstractGrammarHighlighterLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN);
	}

	@Override
	public String getGrammarFileName() { return "GrammarHighlighterLexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }


	@Override
	public void action(RuleContext<Integer> _localctx, int ruleIndex, int actionIndex) {
		switch ( ruleIndex ) {
			case 0 : LEXER_action(_localctx, actionIndex); break;

			case 1 : PARSER_action(_localctx, actionIndex); break;

			case 2 : CATCH_action(_localctx, actionIndex); break;

			case 3 : FINALLY_action(_localctx, actionIndex); break;

			case 4 : GRAMMAR_action(_localctx, actionIndex); break;

			case 5 : PRIVATE_action(_localctx, actionIndex); break;

			case 6 : PROTECTED_action(_localctx, actionIndex); break;

			case 7 : PUBLIC_action(_localctx, actionIndex); break;

			case 8 : RETURNS_action(_localctx, actionIndex); break;

			case 9 : THROWS_action(_localctx, actionIndex); break;

			case 10 : IMPORT_action(_localctx, actionIndex); break;

			case 11 : FRAGMENT_action(_localctx, actionIndex); break;

			case 12 : TOKENS_action(_localctx, actionIndex); break;

			case 13 : OPTIONS_action(_localctx, actionIndex); break;

			case 14 : MODE_action(_localctx, actionIndex); break;

			case 15 : LOCALS_action(_localctx, actionIndex); break;

			case 16 : TREE_action(_localctx, actionIndex); break;

			case 17 : SCOPE_action(_localctx, actionIndex); break;

			case 18 : OPEN_ELEMENT_OPTION_action(_localctx, actionIndex); break;

			case 19 : CLOSE_ELEMENT_OPTION_action(_localctx, actionIndex); break;

			case 20 : AMPERSAND_action(_localctx, actionIndex); break;

			case 21 : COMMA_action(_localctx, actionIndex); break;

			case 22 : QUESTION_action(_localctx, actionIndex); break;

			case 23 : TREE_BEGIN_action(_localctx, actionIndex); break;

			case 24 : LPAREN_action(_localctx, actionIndex); break;

			case 25 : RPAREN_action(_localctx, actionIndex); break;

			case 26 : COLON_action(_localctx, actionIndex); break;

			case 27 : STAR_action(_localctx, actionIndex); break;

			case 28 : PLUS_action(_localctx, actionIndex); break;

			case 29 : ASSIGN_action(_localctx, actionIndex); break;

			case 30 : PLUS_ASSIGN_action(_localctx, actionIndex); break;

			case 31 : IMPLIES_action(_localctx, actionIndex); break;

			case 32 : REWRITE_action(_localctx, actionIndex); break;

			case 33 : SEMI_action(_localctx, actionIndex); break;

			case 34 : ROOT_action(_localctx, actionIndex); break;

			case 35 : BANG_action(_localctx, actionIndex); break;

			case 36 : OR_action(_localctx, actionIndex); break;

			case 37 : WILDCARD_action(_localctx, actionIndex); break;

			case 38 : ETC_action(_localctx, actionIndex); break;

			case 39 : RANGE_action(_localctx, actionIndex); break;

			case 40 : NOT_action(_localctx, actionIndex); break;

			case 41 : LBRACK_action(_localctx, actionIndex); break;

			case 42 : RBRACK_action(_localctx, actionIndex); break;

			case 43 : LCURLY_action(_localctx, actionIndex); break;

			case 44 : RCURLY_action(_localctx, actionIndex); break;

			case 45 : DOLLAR_action(_localctx, actionIndex); break;

			case 46 : LABEL_action(_localctx, actionIndex); break;

			case 47 : IDENTIFIER_action(_localctx, actionIndex); break;

			case 48 : INT_action(_localctx, actionIndex); break;

			case 49 : DIRECTIVE_action(_localctx, actionIndex); break;

			case 50 : REFERENCE_action(_localctx, actionIndex); break;

			case 51 : WS_action(_localctx, actionIndex); break;

			case 52 : NEWLINE_action(_localctx, actionIndex); break;

			case 53 : COMMENT_action(_localctx, actionIndex); break;

			case 54 : ML_COMMENT_action(_localctx, actionIndex); break;

			case 55 : CHAR_LITERAL_action(_localctx, actionIndex); break;

			case 56 : STRING_LITERAL_action(_localctx, actionIndex); break;

			case 57 : IGNORED_action(_localctx, actionIndex); break;

			case 58 : XDIGIT_action(_localctx, actionIndex); break;

			case 59 : ANYCHAR_action(_localctx, actionIndex); break;

			case 60 : BlockComment_NEWLINE_action(_localctx, actionIndex); break;

			case 61 : CONTINUE_ML_COMMENT_action(_localctx, actionIndex); break;

			case 62 : END_ML_COMMENT_action(_localctx, actionIndex); break;

			case 63 : ML_COMMENT_STAR_action(_localctx, actionIndex); break;

			case 64 : BlockComment_ANYCHAR_action(_localctx, actionIndex); break;

			case 65 : ArgAction_NEWLINE_action(_localctx, actionIndex); break;

			case 66 : ArgAction_LBRACK_action(_localctx, actionIndex); break;

			case 67 : ArgAction_RBRACK_action(_localctx, actionIndex); break;

			case 68 : ArgAction_TEXT_action(_localctx, actionIndex); break;

			case 69 : ArgAction_CHAR_LITERAL_action(_localctx, actionIndex); break;

			case 70 : ArgAction_STRING_LITERAL_action(_localctx, actionIndex); break;

			case 71 : ArgAction_ESCAPE_action(_localctx, actionIndex); break;

			case 72 : ArgAction_REFERENCE_action(_localctx, actionIndex); break;

			case 73 : ArgAction_SPECIAL_action(_localctx, actionIndex); break;

			case 74 : ArgAction_ANYCHAR_action(_localctx, actionIndex); break;

			case 75 : NonActionBrace_NEWLINE_action(_localctx, actionIndex); break;

			case 76 : NonActionBrace_WS_action(_localctx, actionIndex); break;

			case 77 : NonActionBrace_LCURLY_action(_localctx, actionIndex); break;

			case 78 : Action_NEWLINE_action(_localctx, actionIndex); break;

			case 79 : Action_COMMENT_action(_localctx, actionIndex); break;

			case 80 : Action_ML_COMMENT_action(_localctx, actionIndex); break;

			case 81 : Action_LCURLY_action(_localctx, actionIndex); break;

			case 82 : Action_RCURLY_action(_localctx, actionIndex); break;

			case 83 : Action_TEXT_action(_localctx, actionIndex); break;

			case 84 : Action_CHAR_LITERAL_action(_localctx, actionIndex); break;

			case 85 : Action_STRING_LITERAL_action(_localctx, actionIndex); break;

			case 86 : Action_ESCAPE_action(_localctx, actionIndex); break;

			case 87 : Action_REFERENCE_action(_localctx, actionIndex); break;

			case 88 : Action_SPECIAL_action(_localctx, actionIndex); break;

			case 89 : Action_ANYCHAR_action(_localctx, actionIndex); break;

			case 90 : LexerCharSet_NEWLINE_action(_localctx, actionIndex); break;

			case 91 : LexerCharSet_ESCAPE_action(_localctx, actionIndex); break;

			case 92 : LexerCharSet_INVALID_ESCAPE_action(_localctx, actionIndex); break;

			case 93 : LexerCharSet_TEXT_action(_localctx, actionIndex); break;

			case 94 : LexerCharSet_RBRACK_action(_localctx, actionIndex); break;
		}
	}
	public void DOLLAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CLOSE_ELEMENT_OPTION_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PROTECTED_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 10 : _type = NEWLINE;  break;
		}
	}
	public void END_ML_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 2 : _type =  getMultilineCommentType(); popMode(); break;
		}
	}
	public void STAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LBRACK_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 0 : handleBeginArgAction(); break;
		}
	}
	public void NonActionBrace_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 15 : _type = NEWLINE;  break;
		}
	}
	public void FRAGMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_STRING_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LexerCharSet_ESCAPE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void NOT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_SPECIAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 22 : _type = Action_TEXT;  break;
		}
	}
	public void Action_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 18 : _type = NEWLINE;  break;
		}
	}
	public void DIRECTIVE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TREE_BEGIN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LPAREN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_REFERENCE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ML_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 7 : pushMode(BlockComment);  break;
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
	public void Action_CHAR_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void STRING_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 14 : _type = ANYCHAR;  break;
		}
	}
	public void SCOPE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TREE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
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
	public void IDENTIFIER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_RCURLY_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 21 : popMode(); _type = Action_TEXT;  break;
		}
	}
	public void NonActionBrace_WS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 16 : _type = WS;  break;
		}
	}
	public void WILDCARD_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_ESCAPE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PLUS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_ML_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 19 : pushMode(BlockComment);  break;
		}
	}
	public void IGNORED_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 23 : _type = ANYCHAR;  break;
		}
	}
	public void ML_COMMENT_STAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 3 : _type =  getMultilineCommentType(); break;
		}
	}
	public void COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_REFERENCE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void MODE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void GRAMMAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_SPECIAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 13 : _type = Action_TEXT;  break;
		}
	}
	public void RETURNS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LexerCharSet_TEXT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RBRACK_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LOCALS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LexerCharSet_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 24 : _type = NEWLINE; popMode();  break;
		}
	}
	public void ArgAction_CHAR_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void IMPLIES_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PRIVATE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_LBRACK_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 11 : _type = LBRACK; pushMode(ArgAction);  break;
		}
	}
	public void ArgAction_RBRACK_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 12 : _type = RBRACK; popMode();  break;
		}
	}
	public void AMPERSAND_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void REFERENCE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TOKENS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 4 : pushMode(NonActionBrace);  break;
		}
	}
	public void RANGE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void THROWS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LCURLY_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 6 : _type = Action_TEXT; pushMode(Action);  break;
		}
	}
	public void BANG_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void INT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void SEMI_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ROOT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void NonActionBrace_LCURLY_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 17 : _type = LCURLY; popMode();  break;
		}
	}
	public void REWRITE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void COLON_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_STRING_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void OPEN_ELEMENT_OPTION_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void WS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BlockComment_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 9 : _type = ANYCHAR;  break;
		}
	}
	public void NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void QUESTION_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CHAR_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void FINALLY_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_ESCAPE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LABEL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LEXER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LexerCharSet_RBRACK_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 25 : _type = RBRACK; popMode();  break;
		}
	}
	public void XDIGIT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_TEXT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CONTINUE_ML_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 1 : _type =  getMultilineCommentType(); break;
		}
	}
	public void OR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RCURLY_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ASSIGN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PLUS_ASSIGN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_TEXT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_LCURLY_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 20 : pushMode(Action); _type = Action_TEXT;  break;
		}
	}
	public void CATCH_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LexerCharSet_INVALID_ESCAPE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PARSER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PUBLIC_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void OPTIONS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 5 : pushMode(NonActionBrace);  break;
		}
	}
	public void BlockComment_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 8 : _type = NEWLINE;  break;
		}
	}

	public static final String _serializedATN =
		"\2N\u028d\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\2\0\7\0\2\1"+
		"\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2"+
		"\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16\7\16\2\17\7\17\2\20\7\20\2\21\7"+
		"\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25\7\25\2\26\7\26\2\27\7\27\2\30\7"+
		"\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34\7\34\2\35\7\35\2\36\7\36\2\37\7"+
		"\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7%\2&\7&\2\'\7\'\2(\7(\2)\7)\2"+
		"*\7*\2+\7+\2,\7,\2-\7-\2.\7.\2/\7/\2\60\7\60\2\61\7\61\2\62\7\62\2\63"+
		"\7\63\2\64\7\64\2\65\7\65\2\66\7\66\2\67\7\67\28\78\29\79\2:\7:\2;\7;"+
		"\2<\7<\2=\7=\2>\7>\2?\7?\2@\7@\2A\7A\2B\7B\2C\7C\2D\7D\2E\7E\2F\7F\2G"+
		"\7G\2H\7H\2I\7I\2J\7J\2K\7K\2L\7L\2M\7M\2N\7N\2O\7O\2P\7P\2Q\7Q\2R\7R"+
		"\2S\7S\2T\7T\2U\7U\2V\7V\2W\7W\2X\7X\2Y\7Y\2Z\7Z\2[\7[\2\\\7\\\2]\7]\2"+
		"^\7^\1\0\1\0\1\0\1\0\1\0\1\0\1\1\1\1\1\1\1\1\1\1\1\1\1\1\1\2\1\2\1\2\1"+
		"\2\1\2\1\2\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\4\1\4\1\4\1\4\1\4\1\4\1\4"+
		"\1\4\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\6\1\6\1\6\1\6\1\6\1\6\1\6\1\6\1"+
		"\6\1\6\1\7\1\7\1\7\1\7\1\7\1\7\1\7\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\t"+
		"\1\t\1\t\1\t\1\t\1\t\1\t\1\n\1\n\1\n\1\n\1\n\1\n\1\n\1\13\1\13\1\13\1"+
		"\13\1\13\1\13\1\13\1\13\1\13\1\f\1\f\1\f\1\f\1\f\1\f\1\f\1\f\1\f\1\f\1"+
		"\f\1\f\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\16\1\16\1"+
		"\16\1\16\1\16\1\17\1\17\1\17\1\17\1\17\1\17\1\17\1\20\1\20\1\20\1\20\1"+
		"\20\1\21\1\21\1\21\1\21\1\21\1\21\1\22\1\22\1\23\1\23\1\24\1\24\1\25\1"+
		"\25\1\26\1\26\1\27\1\27\1\27\1\30\1\30\1\31\1\31\1\32\1\32\1\33\1\33\1"+
		"\34\1\34\1\35\1\35\1\36\1\36\1\36\1\37\1\37\1\37\1 \1 \1 \1!\1!\1\"\1"+
		"\"\1#\1#\1$\1$\1%\1%\1&\1&\1&\1&\1\'\1\'\1\'\1(\1(\1)\1)\1)\1*\1*\1+\1"+
		"+\1+\1+\1,\1,\1-\1-\1.\1.\1.\3.\u0195\b.\1.\1.\1/\1/\5/\u019b\b/\n/\f"+
		"/\u019e\t/\1\60\4\60\u01a1\b\60\13\60\f\60\u01a2\1\61\1\61\1\61\1\62\1"+
		"\62\1\62\1\63\4\63\u01ac\b\63\13\63\f\63\u01ad\1\64\3\64\u01b1\b\64\1"+
		"\64\1\64\1\65\1\65\1\65\1\65\5\65\u01b9\b\65\n\65\f\65\u01bc\t\65\1\66"+
		"\1\66\1\66\1\66\1\66\1\67\1\67\1\67\1\67\5\67\u01c7\b\67\n\67\f\67\u01ca"+
		"\t\67\1\67\3\67\u01cd\b\67\18\18\18\18\58\u01d3\b8\n8\f8\u01d6\t8\18\3"+
		"8\u01d9\b8\19\59\u01dc\b9\n9\f9\u01df\t9\1:\1:\1;\1;\1<\1<\1<\1<\1=\4"+
		"=\u01ea\b=\13=\f=\u01eb\1=\1=\1>\1>\1>\1>\1>\1?\1?\1?\1@\1@\1@\1@\1A\1"+
		"A\1A\1A\1B\1B\1B\1B\1C\1C\1C\1C\1D\4D\u0209\bD\13D\fD\u020a\1E\1E\1F\1"+
		"F\1G\1G\1G\1G\1G\1G\1G\1G\3G\u0219\bG\1H\1H\1I\1I\1I\1I\1J\1J\1J\1J\1"+
		"K\1K\1K\1K\1L\1L\1L\1L\1M\1M\1M\1M\1N\1N\1N\1N\1O\1O\1O\1O\5O\u0239\b"+
		"O\nO\fO\u023c\tO\1P\1P\1P\1P\1P\1Q\1Q\1Q\1Q\1R\1R\1R\1R\1S\4S\u024c\b"+
		"S\13S\fS\u024d\1T\1T\1U\1U\1V\1V\1V\1V\1V\1V\1V\1V\3V\u025c\bV\1W\1W\1"+
		"X\1X\1X\1X\1Y\1Y\1Y\1Y\1Z\1Z\1Z\1Z\1[\1[\1[\1[\1[\1[\1[\1[\3[\u0274\b"+
		"[\1\\\1\\\1\\\1\\\1\\\1\\\1\\\3\\\u027d\b\\\3\\\u027f\b\\\3\\\u0281\b"+
		"\\\3\\\u0283\b\\\1]\4]\u0286\b]\13]\f]\u0287\1^\1^\1^\1^_\6\4\uffff\b"+
		"\5\uffff\n\6\uffff\f\7\uffff\16\b\uffff\20\t\uffff\22\n\uffff\24\13\uffff"+
		"\26\f\uffff\30\r\uffff\32\16\uffff\34\17\uffff\36\20\4 \21\5\"\22\uffff"+
		"$\23\uffff&\24\uffff(\25\uffff*\26\uffff,\27\uffff.\30\uffff\60\31\uffff"+
		"\62\32\uffff\64\33\uffff\66\34\uffff8\35\uffff:\36\uffff<\37\uffff> \uffff"+
		"@!\uffffB\"\uffffD#\uffffF$\uffffH%\uffffJ&\uffffL\'\uffffN(\uffffP)\uffff"+
		"R*\uffffT+\uffffV,\uffffX-\0Z.\uffff\\\3\6^/\uffff`\60\uffffb\61\uffff"+
		"d\62\ufffff\63\uffffh\64\uffffj\65\uffffl\66\uffffn\67\uffffp8\uffffr"+
		"9\7t:\uffffv;\uffffx\0\uffffz\0\uffff|<\uffff~\0\b\u0080=\1\u0082>\2\u0084"+
		"?\3\u0086\0\t\u0088\0\n\u008a\0\13\u008c\0\f\u008e@\uffff\u0090A\uffff"+
		"\u0092B\uffff\u0094C\uffff\u0096D\uffff\u0098\0\r\u009a\0\16\u009c\0\17"+
		"\u009e\0\20\u00a0\0\21\u00a2\0\22\u00a4E\uffff\u00a6F\23\u00a8\0\24\u00aa"+
		"\0\25\u00acG\uffff\u00aeH\uffff\u00b0I\uffff\u00b2J\uffff\u00b4K\uffff"+
		"\u00b6\0\26\u00b8\0\27\u00ba\0\30\u00bcL\uffff\u00beM\uffff\u00c0N\uffff"+
		"\u00c2\0\31\6\0\1\2\3\4\5\22\3AZ__az\4\609AZ__az\2\t\t  \2\n\n\r\r\4\n"+
		"\n\r\r\'\'\\\\\4\n\n\r\r\"\"\\\\\3\t\n\r\r  \3\609AFaf\3\n\n\r\r**\t\n"+
		"\n\r\r\"\"$$\'\'//[]{{}}\t\"\"\'\'>>\\]bbffnnrrtt\3$$//\\\\\2\n\n\r\r"+
		"\t\n\n\r\r\"\"$$\'\'//\\\\{{}}\t\"\"\'\'>>\\\\bbffnnrrtt\3$$//\\\\\3\n"+
		"\n\r\ruu\3\n\n\r\r\\]\u029e\0\6\1\0\0\0\0\b\1\0\0\0\0\n\1\0\0\0\0\f\1"+
		"\0\0\0\0\16\1\0\0\0\0\20\1\0\0\0\0\22\1\0\0\0\0\24\1\0\0\0\0\26\1\0\0"+
		"\0\0\30\1\0\0\0\0\32\1\0\0\0\0\34\1\0\0\0\0\36\1\0\0\0\0 \1\0\0\0\0\""+
		"\1\0\0\0\0$\1\0\0\0\0&\1\0\0\0\0(\1\0\0\0\0*\1\0\0\0\0,\1\0\0\0\0.\1\0"+
		"\0\0\0\60\1\0\0\0\0\62\1\0\0\0\0\64\1\0\0\0\0\66\1\0\0\0\08\1\0\0\0\0"+
		":\1\0\0\0\0<\1\0\0\0\0>\1\0\0\0\0@\1\0\0\0\0B\1\0\0\0\0D\1\0\0\0\0F\1"+
		"\0\0\0\0H\1\0\0\0\0J\1\0\0\0\0L\1\0\0\0\0N\1\0\0\0\0P\1\0\0\0\0R\1\0\0"+
		"\0\0T\1\0\0\0\0V\1\0\0\0\0X\1\0\0\0\0Z\1\0\0\0\0\\\1\0\0\0\0^\1\0\0\0"+
		"\0`\1\0\0\0\0b\1\0\0\0\0d\1\0\0\0\0f\1\0\0\0\0h\1\0\0\0\0j\1\0\0\0\0l"+
		"\1\0\0\0\0n\1\0\0\0\0p\1\0\0\0\0r\1\0\0\0\0t\1\0\0\0\0v\1\0\0\0\0|\1\0"+
		"\0\0\1~\1\0\0\0\1\u0080\1\0\0\0\1\u0082\1\0\0\0\1\u0084\1\0\0\0\1\u0086"+
		"\1\0\0\0\2\u0088\1\0\0\0\2\u008a\1\0\0\0\2\u008c\1\0\0\0\2\u008e\1\0\0"+
		"\0\2\u0090\1\0\0\0\2\u0092\1\0\0\0\2\u0094\1\0\0\0\2\u0096\1\0\0\0\2\u0098"+
		"\1\0\0\0\2\u009a\1\0\0\0\3\u009c\1\0\0\0\3\u009e\1\0\0\0\3\u00a0\1\0\0"+
		"\0\4\u00a2\1\0\0\0\4\u00a4\1\0\0\0\4\u00a6\1\0\0\0\4\u00a8\1\0\0\0\4\u00aa"+
		"\1\0\0\0\4\u00ac\1\0\0\0\4\u00ae\1\0\0\0\4\u00b0\1\0\0\0\4\u00b2\1\0\0"+
		"\0\4\u00b4\1\0\0\0\4\u00b6\1\0\0\0\4\u00b8\1\0\0\0\5\u00ba\1\0\0\0\5\u00bc"+
		"\1\0\0\0\5\u00be\1\0\0\0\5\u00c0\1\0\0\0\5\u00c2\1\0\0\0\6\u00c4\1\0\0"+
		"\0\b\u00ca\1\0\0\0\n\u00d1\1\0\0\0\f\u00d7\1\0\0\0\16\u00df\1\0\0\0\20"+
		"\u00e7\1\0\0\0\22\u00ef\1\0\0\0\24\u00f9\1\0\0\0\26\u0100\1\0\0\0\30\u0108"+
		"\1\0\0\0\32\u010f\1\0\0\0\34\u0116\1\0\0\0\36\u011f\1\0\0\0 \u012b\1\0"+
		"\0\0\"\u0138\1\0\0\0$\u013d\1\0\0\0&\u0144\1\0\0\0(\u0149\1\0\0\0*\u014f"+
		"\1\0\0\0,\u0151\1\0\0\0.\u0153\1\0\0\0\60\u0155\1\0\0\0\62\u0157\1\0\0"+
		"\0\64\u0159\1\0\0\0\66\u015c\1\0\0\08\u015e\1\0\0\0:\u0160\1\0\0\0<\u0162"+
		"\1\0\0\0>\u0164\1\0\0\0@\u0166\1\0\0\0B\u0168\1\0\0\0D\u016b\1\0\0\0F"+
		"\u016e\1\0\0\0H\u0171\1\0\0\0J\u0173\1\0\0\0L\u0175\1\0\0\0N\u0177\1\0"+
		"\0\0P\u0179\1\0\0\0R\u017b\1\0\0\0T\u017f\1\0\0\0V\u0182\1\0\0\0X\u0184"+
		"\1\0\0\0Z\u0187\1\0\0\0\\\u0189\1\0\0\0^\u018d\1\0\0\0`\u018f\1\0\0\0"+
		"b\u0191\1\0\0\0d\u0198\1\0\0\0f\u01a0\1\0\0\0h\u01a4\1\0\0\0j\u01a7\1"+
		"\0\0\0l\u01ab\1\0\0\0n\u01b0\1\0\0\0p\u01b4\1\0\0\0r\u01bd\1\0\0\0t\u01c2"+
		"\1\0\0\0v\u01ce\1\0\0\0x\u01dd\1\0\0\0z\u01e0\1\0\0\0|\u01e2\1\0\0\0~"+
		"\u01e4\1\0\0\0\u0080\u01e9\1\0\0\0\u0082\u01ef\1\0\0\0\u0084\u01f4\1\0"+
		"\0\0\u0086\u01f7\1\0\0\0\u0088\u01fb\1\0\0\0\u008a\u01ff\1\0\0\0\u008c"+
		"\u0203\1\0\0\0\u008e\u0208\1\0\0\0\u0090\u020c\1\0\0\0\u0092\u020e\1\0"+
		"\0\0\u0094\u0210\1\0\0\0\u0096\u021a\1\0\0\0\u0098\u021c\1\0\0\0\u009a"+
		"\u0220\1\0\0\0\u009c\u0224\1\0\0\0\u009e\u0228\1\0\0\0\u00a0\u022c\1\0"+
		"\0\0\u00a2\u0230\1\0\0\0\u00a4\u0234\1\0\0\0\u00a6\u023d\1\0\0\0\u00a8"+
		"\u0242\1\0\0\0\u00aa\u0246\1\0\0\0\u00ac\u024b\1\0\0\0\u00ae\u024f\1\0"+
		"\0\0\u00b0\u0251\1\0\0\0\u00b2\u0253\1\0\0\0\u00b4\u025d\1\0\0\0\u00b6"+
		"\u025f\1\0\0\0\u00b8\u0263\1\0\0\0\u00ba\u0267\1\0\0\0\u00bc\u026b\1\0"+
		"\0\0\u00be\u0282\1\0\0\0\u00c0\u0285\1\0\0\0\u00c2\u0289\1\0\0\0\u00c4"+
		"\u00c5\5l\0\0\u00c5\u00c6\5e\0\0\u00c6\u00c7\5x\0\0\u00c7\u00c8\5e\0\0"+
		"\u00c8\u00c9\5r\0\0\u00c9\7\1\0\0\0\u00ca\u00cb\5p\0\0\u00cb\u00cc\5a"+
		"\0\0\u00cc\u00cd\5r\0\0\u00cd\u00ce\5s\0\0\u00ce\u00cf\5e\0\0\u00cf\u00d0"+
		"\5r\0\0\u00d0\t\1\0\0\0\u00d1\u00d2\5c\0\0\u00d2\u00d3\5a\0\0\u00d3\u00d4"+
		"\5t\0\0\u00d4\u00d5\5c\0\0\u00d5\u00d6\5h\0\0\u00d6\13\1\0\0\0\u00d7\u00d8"+
		"\5f\0\0\u00d8\u00d9\5i\0\0\u00d9\u00da\5n\0\0\u00da\u00db\5a\0\0\u00db"+
		"\u00dc\5l\0\0\u00dc\u00dd\5l\0\0\u00dd\u00de\5y\0\0\u00de\r\1\0\0\0\u00df"+
		"\u00e0\5g\0\0\u00e0\u00e1\5r\0\0\u00e1\u00e2\5a\0\0\u00e2\u00e3\5m\0\0"+
		"\u00e3\u00e4\5m\0\0\u00e4\u00e5\5a\0\0\u00e5\u00e6\5r\0\0\u00e6\17\1\0"+
		"\0\0\u00e7\u00e8\5p\0\0\u00e8\u00e9\5r\0\0\u00e9\u00ea\5i\0\0\u00ea\u00eb"+
		"\5v\0\0\u00eb\u00ec\5a\0\0\u00ec\u00ed\5t\0\0\u00ed\u00ee\5e\0\0\u00ee"+
		"\21\1\0\0\0\u00ef\u00f0\5p\0\0\u00f0\u00f1\5r\0\0\u00f1\u00f2\5o\0\0\u00f2"+
		"\u00f3\5t\0\0\u00f3\u00f4\5e\0\0\u00f4\u00f5\5c\0\0\u00f5\u00f6\5t\0\0"+
		"\u00f6\u00f7\5e\0\0\u00f7\u00f8\5d\0\0\u00f8\23\1\0\0\0\u00f9\u00fa\5"+
		"p\0\0\u00fa\u00fb\5u\0\0\u00fb\u00fc\5b\0\0\u00fc\u00fd\5l\0\0\u00fd\u00fe"+
		"\5i\0\0\u00fe\u00ff\5c\0\0\u00ff\25\1\0\0\0\u0100\u0101\5r\0\0\u0101\u0102"+
		"\5e\0\0\u0102\u0103\5t\0\0\u0103\u0104\5u\0\0\u0104\u0105\5r\0\0\u0105"+
		"\u0106\5n\0\0\u0106\u0107\5s\0\0\u0107\27\1\0\0\0\u0108\u0109\5t\0\0\u0109"+
		"\u010a\5h\0\0\u010a\u010b\5r\0\0\u010b\u010c\5o\0\0\u010c\u010d\5w\0\0"+
		"\u010d\u010e\5s\0\0\u010e\31\1\0\0\0\u010f\u0110\5i\0\0\u0110\u0111\5"+
		"m\0\0\u0111\u0112\5p\0\0\u0112\u0113\5o\0\0\u0113\u0114\5r\0\0\u0114\u0115"+
		"\5t\0\0\u0115\33\1\0\0\0\u0116\u0117\5f\0\0\u0117\u0118\5r\0\0\u0118\u0119"+
		"\5a\0\0\u0119\u011a\5g\0\0\u011a\u011b\5m\0\0\u011b\u011c\5e\0\0\u011c"+
		"\u011d\5n\0\0\u011d\u011e\5t\0\0\u011e\35\1\0\0\0\u011f\u0120\5t\0\0\u0120"+
		"\u0121\5o\0\0\u0121\u0122\5k\0\0\u0122\u0123\5e\0\0\u0123\u0124\5n\0\0"+
		"\u0124\u0125\5s\0\0\u0125\u0126\1\0\0\0\u0126\u0127\3x9\0\u0127\u0128"+
		"\5{\0\0\u0128\u0129\1\0\0\0\u0129\u012a\6\f\4\0\u012a\37\1\0\0\0\u012b"+
		"\u012c\5o\0\0\u012c\u012d\5p\0\0\u012d\u012e\5t\0\0\u012e\u012f\5i\0\0"+
		"\u012f\u0130\5o\0\0\u0130\u0131\5n\0\0\u0131\u0132\5s\0\0\u0132\u0133"+
		"\1\0\0\0\u0133\u0134\3x9\0\u0134\u0135\5{\0\0\u0135\u0136\1\0\0\0\u0136"+
		"\u0137\6\r\5\0\u0137!\1\0\0\0\u0138\u0139\5m\0\0\u0139\u013a\5o\0\0\u013a"+
		"\u013b\5d\0\0\u013b\u013c\5e\0\0\u013c#\1\0\0\0\u013d\u013e\5l\0\0\u013e"+
		"\u013f\5o\0\0\u013f\u0140\5c\0\0\u0140\u0141\5a\0\0\u0141\u0142\5l\0\0"+
		"\u0142\u0143\5s\0\0\u0143%\1\0\0\0\u0144\u0145\5t\0\0\u0145\u0146\5r\0"+
		"\0\u0146\u0147\5e\0\0\u0147\u0148\5e\0\0\u0148\'\1\0\0\0\u0149\u014a\5"+
		"s\0\0\u014a\u014b\5c\0\0\u014b\u014c\5o\0\0\u014c\u014d\5p\0\0\u014d\u014e"+
		"\5e\0\0\u014e)\1\0\0\0\u014f\u0150\5<\0\0\u0150+\1\0\0\0\u0151\u0152\5"+
		">\0\0\u0152-\1\0\0\0\u0153\u0154\5@\0\0\u0154/\1\0\0\0\u0155\u0156\5,"+
		"\0\0\u0156\61\1\0\0\0\u0157\u0158\5?\0\0\u0158\63\1\0\0\0\u0159\u015a"+
		"\5^\0\0\u015a\u015b\5(\0\0\u015b\65\1\0\0\0\u015c\u015d\5(\0\0\u015d\67"+
		"\1\0\0\0\u015e\u015f\5)\0\0\u015f9\1\0\0\0\u0160\u0161\5:\0\0\u0161;\1"+
		"\0\0\0\u0162\u0163\5*\0\0\u0163=\1\0\0\0\u0164\u0165\5+\0\0\u0165?\1\0"+
		"\0\0\u0166\u0167\5=\0\0\u0167A\1\0\0\0\u0168\u0169\5+\0\0\u0169\u016a"+
		"\5=\0\0\u016aC\1\0\0\0\u016b\u016c\5=\0\0\u016c\u016d\5>\0\0\u016dE\1"+
		"\0\0\0\u016e\u016f\5-\0\0\u016f\u0170\5>\0\0\u0170G\1\0\0\0\u0171\u0172"+
		"\5;\0\0\u0172I\1\0\0\0\u0173\u0174\5^\0\0\u0174K\1\0\0\0\u0175\u0176\5"+
		"!\0\0\u0176M\1\0\0\0\u0177\u0178\5|\0\0\u0178O\1\0\0\0\u0179\u017a\5."+
		"\0\0\u017aQ\1\0\0\0\u017b\u017c\5.\0\0\u017c\u017d\5.\0\0\u017d\u017e"+
		"\5.\0\0\u017eS\1\0\0\0\u017f\u0180\5.\0\0\u0180\u0181\5.\0\0\u0181U\1"+
		"\0\0\0\u0182\u0183\5~\0\0\u0183W\1\0\0\0\u0184\u0185\5[\0\0\u0185\u0186"+
		"\6)\0\0\u0186Y\1\0\0\0\u0187\u0188\5]\0\0\u0188[\1\0\0\0\u0189\u018a\5"+
		"{\0\0\u018a\u018b\1\0\0\0\u018b\u018c\6+\6\0\u018c]\1\0\0\0\u018d\u018e"+
		"\5}\0\0\u018e_\1\0\0\0\u018f\u0190\5$\0\0\u0190a\1\0\0\0\u0191\u0192\3"+
		"d/\0\u0192\u0194\3x9\0\u0193\u0195\5+\0\0\u0194\u0193\1\0\0\0\u0194\u0195"+
		"\1\0\0\0\u0195\u0196\1\0\0\0\u0196\u0197\5=\0\0\u0197c\1\0\0\0\u0198\u019c"+
		"\7\0\0\0\u0199\u019b\7\1\0\0\u019a\u0199\1\0\0\0\u019b\u019e\1\0\0\0\u019c"+
		"\u019a\1\0\0\0\u019c\u019d\1\0\0\0\u019de\1\0\0\0\u019e\u019c\1\0\0\0"+
		"\u019f\u01a1\2\609\0\u01a0\u019f\1\0\0\0\u01a1\u01a2\1\0\0\0\u01a2\u01a0"+
		"\1\0\0\0\u01a2\u01a3\1\0\0\0\u01a3g\1\0\0\0\u01a4\u01a5\5@\0\0\u01a5\u01a6"+
		"\3d/\0\u01a6i\1\0\0\0\u01a7\u01a8\5$\0\0\u01a8\u01a9\3d/\0\u01a9k\1\0"+
		"\0\0\u01aa\u01ac\7\2\0\0\u01ab\u01aa\1\0\0\0\u01ac\u01ad\1\0\0\0\u01ad"+
		"\u01ab\1\0\0\0\u01ad\u01ae\1\0\0\0\u01aem\1\0\0\0\u01af\u01b1\5\r\0\0"+
		"\u01b0\u01af\1\0\0\0\u01b0\u01b1\1\0\0\0\u01b1\u01b2\1\0\0\0\u01b2\u01b3"+
		"\5\n\0\0\u01b3o\1\0\0\0\u01b4\u01b5\5/\0\0\u01b5\u01b6\5/\0\0\u01b6\u01ba"+
		"\1\0\0\0\u01b7\u01b9\b\3\0\0\u01b8\u01b7\1\0\0\0\u01b9\u01bc\1\0\0\0\u01ba"+
		"\u01b8\1\0\0\0\u01ba\u01bb\1\0\0\0\u01bbq\1\0\0\0\u01bc\u01ba\1\0\0\0"+
		"\u01bd\u01be\5/\0\0\u01be\u01bf\5*\0\0\u01bf\u01c0\1\0\0\0\u01c0\u01c1"+
		"\6\66\7\0\u01c1s\1\0\0\0\u01c2\u01c8\5\'\0\0\u01c3\u01c4\5\\\0\0\u01c4"+
		"\u01c7\t\0\0\0\u01c5\u01c7\b\4\0\0\u01c6\u01c3\1\0\0\0\u01c6\u01c5\1\0"+
		"\0\0\u01c7\u01ca\1\0\0\0\u01c8\u01c6\1\0\0\0\u01c8\u01c9\1\0\0\0\u01c9"+
		"\u01cc\1\0\0\0\u01ca\u01c8\1\0\0\0\u01cb\u01cd\5\'\0\0\u01cc\u01cb\1\0"+
		"\0\0\u01cc\u01cd\1\0\0\0\u01cdu\1\0\0\0\u01ce\u01d4\5\"\0\0\u01cf\u01d0"+
		"\5\\\0\0\u01d0\u01d3\t\0\0\0\u01d1\u01d3\b\5\0\0\u01d2\u01cf\1\0\0\0\u01d2"+
		"\u01d1\1\0\0\0\u01d3\u01d6\1\0\0\0\u01d4\u01d2\1\0\0\0\u01d4\u01d5\1\0"+
		"\0\0\u01d5\u01d8\1\0\0\0\u01d6\u01d4\1\0\0\0\u01d7\u01d9\5\"\0\0\u01d8"+
		"\u01d7\1\0\0\0\u01d8\u01d9\1\0\0\0\u01d9w\1\0\0\0\u01da\u01dc\7\6\0\0"+
		"\u01db\u01da\1\0\0\0\u01dc\u01df\1\0\0\0\u01dd\u01db\1\0\0\0\u01dd\u01de"+
		"\1\0\0\0\u01dey\1\0\0\0\u01df\u01dd\1\0\0\0\u01e0\u01e1\7\7\0\0\u01e1"+
		"{\1\0\0\0\u01e2\u01e3\t\0\0\0\u01e3}\1\0\0\0\u01e4\u01e5\3n\64\0\u01e5"+
		"\u01e6\1\0\0\0\u01e6\u01e7\6<\b\0\u01e7\177\1\0\0\0\u01e8\u01ea\b\b\0"+
		"\0\u01e9\u01e8\1\0\0\0\u01ea\u01eb\1\0\0\0\u01eb\u01e9\1\0\0\0\u01eb\u01ec"+
		"\1\0\0\0\u01ec\u01ed\1\0\0\0\u01ed\u01ee\6=\1\0\u01ee\u0081\1\0\0\0\u01ef"+
		"\u01f0\5*\0\0\u01f0\u01f1\5/\0\0\u01f1\u01f2\1\0\0\0\u01f2\u01f3\6>\2"+
		"\0\u01f3\u0083\1\0\0\0\u01f4\u01f5\5*\0\0\u01f5\u01f6\6?\3\0\u01f6\u0085"+
		"\1\0\0\0\u01f7\u01f8\t\0\0\0\u01f8\u01f9\1\0\0\0\u01f9\u01fa\6@\t\0\u01fa"+
		"\u0087\1\0\0\0\u01fb\u01fc\3n\64\0\u01fc\u01fd\1\0\0\0\u01fd\u01fe\6A"+
		"\n\0\u01fe\u0089\1\0\0\0\u01ff\u0200\5[\0\0\u0200\u0201\1\0\0\0\u0201"+
		"\u0202\6B\13\0\u0202\u008b\1\0\0\0\u0203\u0204\5]\0\0\u0204\u0205\1\0"+
		"\0\0\u0205\u0206\6C\f\0\u0206\u008d\1\0\0\0\u0207\u0209\b\t\0\0\u0208"+
		"\u0207\1\0\0\0\u0209\u020a\1\0\0\0\u020a\u0208\1\0\0\0\u020a\u020b\1\0"+
		"\0\0\u020b\u008f\1\0\0\0\u020c\u020d\3t\67\0\u020d\u0091\1\0\0\0\u020e"+
		"\u020f\3v8\0\u020f\u0093\1\0\0\0\u0210\u0218\5\\\0\0\u0211\u0219\7\n\0"+
		"\0\u0212\u0213\5u\0\0\u0213\u0214\3z:\0\u0214\u0215\3z:\0\u0215\u0216"+
		"\3z:\0\u0216\u0217\3z:\0\u0217\u0219\1\0\0\0\u0218\u0211\1\0\0\0\u0218"+
		"\u0212\1\0\0\0\u0219\u0095\1\0\0\0\u021a\u021b\3j\62\0\u021b\u0097\1\0"+
		"\0\0\u021c\u021d\7\13\0\0\u021d\u021e\1\0\0\0\u021e\u021f\6I\r\0\u021f"+
		"\u0099\1\0\0\0\u0220\u0221\t\0\0\0\u0221\u0222\1\0\0\0\u0222\u0223\6J"+
		"\16\0\u0223\u009b\1\0\0\0\u0224\u0225\3n\64\0\u0225\u0226\1\0\0\0\u0226"+
		"\u0227\6K\17\0\u0227\u009d\1\0\0\0\u0228\u0229\3l\63\0\u0229\u022a\1\0"+
		"\0\0\u022a\u022b\6L\20\0\u022b\u009f\1\0\0\0\u022c\u022d\3\\+\0\u022d"+
		"\u022e\1\0\0\0\u022e\u022f\6M\21\0\u022f\u00a1\1\0\0\0\u0230\u0231\3n"+
		"\64\0\u0231\u0232\1\0\0\0\u0232\u0233\6N\22\0\u0233\u00a3\1\0\0\0\u0234"+
		"\u0235\5/\0\0\u0235\u0236\5/\0\0\u0236\u023a\1\0\0\0\u0237\u0239\b\f\0"+
		"\0\u0238\u0237\1\0\0\0\u0239\u023c\1\0\0\0\u023a\u0238\1\0\0\0\u023a\u023b"+
		"\1\0\0\0\u023b\u00a5\1\0\0\0\u023c\u023a\1\0\0\0\u023d\u023e\5/\0\0\u023e"+
		"\u023f\5*\0\0\u023f\u0240\1\0\0\0\u0240\u0241\6P\23\0\u0241\u00a7\1\0"+
		"\0\0\u0242\u0243\5{\0\0\u0243\u0244\1\0\0\0\u0244\u0245\6Q\24\0\u0245"+
		"\u00a9\1\0\0\0\u0246\u0247\5}\0\0\u0247\u0248\1\0\0\0\u0248\u0249\6R\25"+
		"\0\u0249\u00ab\1\0\0\0\u024a\u024c\b\r\0\0\u024b\u024a\1\0\0\0\u024c\u024d"+
		"\1\0\0\0\u024d\u024b\1\0\0\0\u024d\u024e\1\0\0\0\u024e\u00ad\1\0\0\0\u024f"+
		"\u0250\3t\67\0\u0250\u00af\1\0\0\0\u0251\u0252\3v8\0\u0252\u00b1\1\0\0"+
		"\0\u0253\u025b\5\\\0\0\u0254\u025c\7\16\0\0\u0255\u0256\5u\0\0\u0256\u0257"+
		"\3z:\0\u0257\u0258\3z:\0\u0258\u0259\3z:\0\u0259\u025a\3z:\0\u025a\u025c"+
		"\1\0\0\0\u025b\u0254\1\0\0\0\u025b\u0255\1\0\0\0\u025c\u00b3\1\0\0\0\u025d"+
		"\u025e\3j\62\0\u025e\u00b5\1\0\0\0\u025f\u0260\7\17\0\0\u0260\u0261\1"+
		"\0\0\0\u0261\u0262\6X\26\0\u0262\u00b7\1\0\0\0\u0263\u0264\t\0\0\0\u0264"+
		"\u0265\1\0\0\0\u0265\u0266\6Y\27\0\u0266\u00b9\1\0\0\0\u0267\u0268\3n"+
		"\64\0\u0268\u0269\1\0\0\0\u0269\u026a\6Z\30\0\u026a\u00bb\1\0\0\0\u026b"+
		"\u0273\5\\\0\0\u026c\u0274\b\20\0\0\u026d\u026e\5u\0\0\u026e\u026f\3z"+
		":\0\u026f\u0270\3z:\0\u0270\u0271\3z:\0\u0271\u0272\3z:\0\u0272\u0274"+
		"\1\0\0\0\u0273\u026c\1\0\0\0\u0273\u026d\1\0\0\0\u0274\u00bd\1\0\0\0\u0275"+
		"\u0283\5\\\0\0\u0276\u0277\5\\\0\0\u0277\u0278\5u\0\0\u0278\u0280\1\0"+
		"\0\0\u0279\u027e\3z:\0\u027a\u027c\3z:\0\u027b\u027d\3z:\0\u027c\u027b"+
		"\1\0\0\0\u027c\u027d\1\0\0\0\u027d\u027f\1\0\0\0\u027e\u027a\1\0\0\0\u027e"+
		"\u027f\1\0\0\0\u027f\u0281\1\0\0\0\u0280\u0279\1\0\0\0\u0280\u0281\1\0"+
		"\0\0\u0281\u0283\1\0\0\0\u0282\u0275\1\0\0\0\u0282\u0276\1\0\0\0\u0283"+
		"\u00bf\1\0\0\0\u0284\u0286\b\21\0\0\u0285\u0284\1\0\0\0\u0286\u0287\1"+
		"\0\0\0\u0287\u0285\1\0\0\0\u0287\u0288\1\0\0\0\u0288\u00c1\1\0\0\0\u0289"+
		"\u028a\5]\0\0\u028a\u028b\1\0\0\0\u028b\u028c\6^\31\0\u028c\u00c3\1\0"+
		"\0\0\37\0\1\1\1\2\1\3\1\4\1\5\1\u0194\1\u019c\1\u01a2\1\u01ad\1\u01b0"+
		"\1\u01ba\1\u01c6\1\u01c8\1\u01cc\1\u01d2\1\u01d4\1\u01d8\1\u01dd\1\u01eb"+
		"\1\u020a\1\u0218\1\u023a\1\u024d\1\u025b\1\u0273\1\u027c\1\u027e\1\u0280"+
		"\1\u0282\1\u0287\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}