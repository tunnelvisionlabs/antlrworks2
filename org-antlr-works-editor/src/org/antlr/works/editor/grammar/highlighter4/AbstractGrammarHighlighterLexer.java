// $ANTLR ANTLRVersion> AbstractGrammarHighlighterLexer.java generatedTimestamp>
package org.antlr.works.editor.grammar.highlighter4;
/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */

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
		TEMPLATE=20, TREE=21, SCOPE=22, OPEN_ELEMENT_OPTION=23, CLOSE_ELEMENT_OPTION=24, 
		AMPERSAND=25, COMMA=26, QUESTION=27, TREE_BEGIN=28, LPAREN=29, RPAREN=30, 
		COLON=31, STAR=32, PLUS=33, ASSIGN=34, PLUS_ASSIGN=35, IMPLIES=36, REWRITE=37, 
		SEMI=38, ROOT=39, BANG=40, OR=41, WILDCARD=42, ETC=43, RANGE=44, NOT=45, 
		LBRACK=46, RBRACK=47, RCURLY=48, DOLLAR=49, LABEL=50, IDENTIFIER=51, INT=52, 
		DIRECTIVE=53, REFERENCE=54, WS=55, NEWLINE=56, COMMENT=57, ML_COMMENT=58, 
		CHAR_LITERAL=59, STRING_LITERAL=60, ANYCHAR=61, CONTINUE_ML_COMMENT=62, 
		END_ML_COMMENT=63, ML_COMMENT_STAR=64, ArgAction_TEXT=65, ArgAction_CHAR_LITERAL=66, 
		ArgAction_STRING_LITERAL=67, ArgAction_ESCAPE=68, ArgAction_REFERENCE=69, 
		Action_COMMENT=70, Action_ML_COMMENT=71, Action_TEXT=72, Action_CHAR_LITERAL=73, 
		Action_STRING_LITERAL=74, Action_ESCAPE=75, Action_REFERENCE=76, LexerCharSet_ESCAPE=77, 
		LexerCharSet_INVALID_ESCAPE=78, LexerCharSet_TEXT=79;
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
		"'mode'", "'locals'", "'template'", "'tree'", "'scope'", "'<'", "'>'", 
		"'@'", "','", "'?'", "'^('", "'('", "')'", "':'", "STAR", "'+'", "'='", 
		"'+='", "'=>'", "'->'", "';'", "'^'", "'!'", "'|'", "'.'", "'...'", "'..'", 
		"'~'", "'['", "RBRACK", "RCURLY", "'$'", "LABEL", "IDENTIFIER", "INT", 
		"DIRECTIVE", "REFERENCE", "WS", "NEWLINE", "COMMENT", "ML_COMMENT", "CHAR_LITERAL", 
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
		"MODE", "LOCALS", "TEMPLATE", "TREE", "SCOPE", "OPEN_ELEMENT_OPTION", 
		"CLOSE_ELEMENT_OPTION", "AMPERSAND", "COMMA", "QUESTION", "TREE_BEGIN", 
		"LPAREN", "RPAREN", "COLON", "STAR", "PLUS", "ASSIGN", "PLUS_ASSIGN", 
		"IMPLIES", "REWRITE", "SEMI", "ROOT", "BANG", "OR", "WILDCARD", "ETC", 
		"RANGE", "NOT", "LBRACK", "RBRACK", "LCURLY", "RCURLY", "DOLLAR", "LABEL", 
		"IDENTIFIER", "INT", "DIRECTIVE", "REFERENCE", "WS", "NEWLINE", "COMMENT", 
		"ML_COMMENT", "CHAR_LITERAL", "STRING_LITERAL", "IGNORED", "XDIGIT", "ANYCHAR", 
		"BlockComment_NEWLINE", "CONTINUE_ML_COMMENT", "END_ML_COMMENT", "ML_COMMENT_STAR", 
		"BlockComment_ANYCHAR", "ArgAction_NEWLINE", "ArgAction_LBRACK", "ArgAction_RBRACK", 
		"ArgAction_TEXT", "ArgAction_CHAR_LITERAL", "ArgAction_STRING_LITERAL", 
		"ArgAction_ESCAPE", "ArgAction_REFERENCE", "ArgAction_SPECIAL", "ArgAction_ANYCHAR", 
		"NonActionBrace_NEWLINE", "NonActionBrace_WS", "NonActionBrace_LCURLY", 
		"Action_NEWLINE", "Action_COMMENT", "Action_ML_COMMENT", "Action_LCURLY", 
		"Action_RCURLY", "Action_TEXT", "Action_CHAR_LITERAL", "Action_STRING_LITERAL", 
		"Action_ESCAPE", "Action_REFERENCE", "Action_SPECIAL", "Action_ANYCHAR", 
		"LexerCharSet_NEWLINE", "LexerCharSet_ESCAPE", "LexerCharSet_INVALID_ESCAPE", 
		"LexerCharSet_TEXT", "LexerCharSet_RBRACK"
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

			case 16 : TEMPLATE_action(_localctx, actionIndex); break;

			case 17 : TREE_action(_localctx, actionIndex); break;

			case 18 : SCOPE_action(_localctx, actionIndex); break;

			case 19 : OPEN_ELEMENT_OPTION_action(_localctx, actionIndex); break;

			case 20 : CLOSE_ELEMENT_OPTION_action(_localctx, actionIndex); break;

			case 21 : AMPERSAND_action(_localctx, actionIndex); break;

			case 22 : COMMA_action(_localctx, actionIndex); break;

			case 23 : QUESTION_action(_localctx, actionIndex); break;

			case 24 : TREE_BEGIN_action(_localctx, actionIndex); break;

			case 25 : LPAREN_action(_localctx, actionIndex); break;

			case 26 : RPAREN_action(_localctx, actionIndex); break;

			case 27 : COLON_action(_localctx, actionIndex); break;

			case 28 : STAR_action(_localctx, actionIndex); break;

			case 29 : PLUS_action(_localctx, actionIndex); break;

			case 30 : ASSIGN_action(_localctx, actionIndex); break;

			case 31 : PLUS_ASSIGN_action(_localctx, actionIndex); break;

			case 32 : IMPLIES_action(_localctx, actionIndex); break;

			case 33 : REWRITE_action(_localctx, actionIndex); break;

			case 34 : SEMI_action(_localctx, actionIndex); break;

			case 35 : ROOT_action(_localctx, actionIndex); break;

			case 36 : BANG_action(_localctx, actionIndex); break;

			case 37 : OR_action(_localctx, actionIndex); break;

			case 38 : WILDCARD_action(_localctx, actionIndex); break;

			case 39 : ETC_action(_localctx, actionIndex); break;

			case 40 : RANGE_action(_localctx, actionIndex); break;

			case 41 : NOT_action(_localctx, actionIndex); break;

			case 42 : LBRACK_action(_localctx, actionIndex); break;

			case 43 : RBRACK_action(_localctx, actionIndex); break;

			case 44 : LCURLY_action(_localctx, actionIndex); break;

			case 45 : RCURLY_action(_localctx, actionIndex); break;

			case 46 : DOLLAR_action(_localctx, actionIndex); break;

			case 47 : LABEL_action(_localctx, actionIndex); break;

			case 48 : IDENTIFIER_action(_localctx, actionIndex); break;

			case 49 : INT_action(_localctx, actionIndex); break;

			case 50 : DIRECTIVE_action(_localctx, actionIndex); break;

			case 51 : REFERENCE_action(_localctx, actionIndex); break;

			case 52 : WS_action(_localctx, actionIndex); break;

			case 53 : NEWLINE_action(_localctx, actionIndex); break;

			case 54 : COMMENT_action(_localctx, actionIndex); break;

			case 55 : ML_COMMENT_action(_localctx, actionIndex); break;

			case 56 : CHAR_LITERAL_action(_localctx, actionIndex); break;

			case 57 : STRING_LITERAL_action(_localctx, actionIndex); break;

			case 58 : IGNORED_action(_localctx, actionIndex); break;

			case 59 : XDIGIT_action(_localctx, actionIndex); break;

			case 60 : ANYCHAR_action(_localctx, actionIndex); break;

			case 61 : BlockComment_NEWLINE_action(_localctx, actionIndex); break;

			case 62 : CONTINUE_ML_COMMENT_action(_localctx, actionIndex); break;

			case 63 : END_ML_COMMENT_action(_localctx, actionIndex); break;

			case 64 : ML_COMMENT_STAR_action(_localctx, actionIndex); break;

			case 65 : BlockComment_ANYCHAR_action(_localctx, actionIndex); break;

			case 66 : ArgAction_NEWLINE_action(_localctx, actionIndex); break;

			case 67 : ArgAction_LBRACK_action(_localctx, actionIndex); break;

			case 68 : ArgAction_RBRACK_action(_localctx, actionIndex); break;

			case 69 : ArgAction_TEXT_action(_localctx, actionIndex); break;

			case 70 : ArgAction_CHAR_LITERAL_action(_localctx, actionIndex); break;

			case 71 : ArgAction_STRING_LITERAL_action(_localctx, actionIndex); break;

			case 72 : ArgAction_ESCAPE_action(_localctx, actionIndex); break;

			case 73 : ArgAction_REFERENCE_action(_localctx, actionIndex); break;

			case 74 : ArgAction_SPECIAL_action(_localctx, actionIndex); break;

			case 75 : ArgAction_ANYCHAR_action(_localctx, actionIndex); break;

			case 76 : NonActionBrace_NEWLINE_action(_localctx, actionIndex); break;

			case 77 : NonActionBrace_WS_action(_localctx, actionIndex); break;

			case 78 : NonActionBrace_LCURLY_action(_localctx, actionIndex); break;

			case 79 : Action_NEWLINE_action(_localctx, actionIndex); break;

			case 80 : Action_COMMENT_action(_localctx, actionIndex); break;

			case 81 : Action_ML_COMMENT_action(_localctx, actionIndex); break;

			case 82 : Action_LCURLY_action(_localctx, actionIndex); break;

			case 83 : Action_RCURLY_action(_localctx, actionIndex); break;

			case 84 : Action_TEXT_action(_localctx, actionIndex); break;

			case 85 : Action_CHAR_LITERAL_action(_localctx, actionIndex); break;

			case 86 : Action_STRING_LITERAL_action(_localctx, actionIndex); break;

			case 87 : Action_ESCAPE_action(_localctx, actionIndex); break;

			case 88 : Action_REFERENCE_action(_localctx, actionIndex); break;

			case 89 : Action_SPECIAL_action(_localctx, actionIndex); break;

			case 90 : Action_ANYCHAR_action(_localctx, actionIndex); break;

			case 91 : LexerCharSet_NEWLINE_action(_localctx, actionIndex); break;

			case 92 : LexerCharSet_ESCAPE_action(_localctx, actionIndex); break;

			case 93 : LexerCharSet_INVALID_ESCAPE_action(_localctx, actionIndex); break;

			case 94 : LexerCharSet_TEXT_action(_localctx, actionIndex); break;

			case 95 : LexerCharSet_RBRACK_action(_localctx, actionIndex); break;
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
			case 2 : setType(getMultilineCommentType()); popMode(); break;
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
			case 3 : setType(getMultilineCommentType()); break;
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
	public void INT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BANG_action(RuleContext<Integer> _localctx, int actionIndex) {
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
	public void TEMPLATE_action(RuleContext<Integer> _localctx, int actionIndex) {
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
			case 1 : setType(getMultilineCommentType()); break;
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
		"\2O\u0298\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\2\0\7\0\2\1"+
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
		"^\7^\2_\7_\1\0\1\0\1\0\1\0\1\0\1\0\1\1\1\1\1\1\1\1\1\1\1\1\1\1\1\2\1\2"+
		"\1\2\1\2\1\2\1\2\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\4\1\4\1\4\1\4\1\4\1"+
		"\4\1\4\1\4\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\6\1\6\1\6\1\6\1\6\1\6\1\6"+
		"\1\6\1\6\1\6\1\7\1\7\1\7\1\7\1\7\1\7\1\7\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1"+
		"\b\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\n\1\n\1\n\1\n\1\n\1\n\1\n\1\13\1\13\1"+
		"\13\1\13\1\13\1\13\1\13\1\13\1\13\1\f\1\f\1\f\1\f\1\f\1\f\1\f\1\f\1\f"+
		"\1\f\1\f\1\f\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\16"+
		"\1\16\1\16\1\16\1\16\1\17\1\17\1\17\1\17\1\17\1\17\1\17\1\20\1\20\1\20"+
		"\1\20\1\20\1\20\1\20\1\20\1\20\1\21\1\21\1\21\1\21\1\21\1\22\1\22\1\22"+
		"\1\22\1\22\1\22\1\23\1\23\1\24\1\24\1\25\1\25\1\26\1\26\1\27\1\27\1\30"+
		"\1\30\1\30\1\31\1\31\1\32\1\32\1\33\1\33\1\34\1\34\1\35\1\35\1\36\1\36"+
		"\1\37\1\37\1\37\1 \1 \1 \1!\1!\1!\1\"\1\"\1#\1#\1$\1$\1%\1%\1&\1&\1\'"+
		"\1\'\1\'\1\'\1(\1(\1(\1)\1)\1*\1*\1*\1+\1+\1,\1,\1,\1,\1-\1-\1.\1.\1/"+
		"\1/\1/\3/\u01a0\b/\1/\1/\1\60\1\60\5\60\u01a6\b\60\n\60\f\60\u01a9\t\60"+
		"\1\61\4\61\u01ac\b\61\13\61\f\61\u01ad\1\62\1\62\1\62\1\63\1\63\1\63\1"+
		"\64\4\64\u01b7\b\64\13\64\f\64\u01b8\1\65\3\65\u01bc\b\65\1\65\1\65\1"+
		"\66\1\66\1\66\1\66\5\66\u01c4\b\66\n\66\f\66\u01c7\t\66\1\67\1\67\1\67"+
		"\1\67\1\67\18\18\18\18\58\u01d2\b8\n8\f8\u01d5\t8\18\38\u01d8\b8\19\1"+
		"9\19\19\59\u01de\b9\n9\f9\u01e1\t9\19\39\u01e4\b9\1:\5:\u01e7\b:\n:\f"+
		":\u01ea\t:\1;\1;\1<\1<\1=\1=\1=\1=\1>\4>\u01f5\b>\13>\f>\u01f6\1>\1>\1"+
		"?\1?\1?\1?\1?\1@\1@\1@\1A\1A\1A\1A\1B\1B\1B\1B\1C\1C\1C\1C\1D\1D\1D\1"+
		"D\1E\4E\u0214\bE\13E\fE\u0215\1F\1F\1G\1G\1H\1H\1H\1H\1H\1H\1H\1H\3H\u0224"+
		"\bH\1I\1I\1J\1J\1J\1J\1K\1K\1K\1K\1L\1L\1L\1L\1M\1M\1M\1M\1N\1N\1N\1N"+
		"\1O\1O\1O\1O\1P\1P\1P\1P\5P\u0244\bP\nP\fP\u0247\tP\1Q\1Q\1Q\1Q\1Q\1R"+
		"\1R\1R\1R\1S\1S\1S\1S\1T\4T\u0257\bT\13T\fT\u0258\1U\1U\1V\1V\1W\1W\1"+
		"W\1W\1W\1W\1W\1W\3W\u0267\bW\1X\1X\1Y\1Y\1Y\1Y\1Z\1Z\1Z\1Z\1[\1[\1[\1"+
		"[\1\\\1\\\1\\\1\\\1\\\1\\\1\\\1\\\3\\\u027f\b\\\1]\1]\1]\1]\1]\1]\1]\3"+
		"]\u0288\b]\3]\u028a\b]\3]\u028c\b]\3]\u028e\b]\1^\4^\u0291\b^\13^\f^\u0292"+
		"\1_\1_\1_\1_`\6\4\uffff\b\5\uffff\n\6\uffff\f\7\uffff\16\b\uffff\20\t"+
		"\uffff\22\n\uffff\24\13\uffff\26\f\uffff\30\r\uffff\32\16\uffff\34\17"+
		"\uffff\36\20\4 \21\5\"\22\uffff$\23\uffff&\24\uffff(\25\uffff*\26\uffff"+
		",\27\uffff.\30\uffff\60\31\uffff\62\32\uffff\64\33\uffff\66\34\uffff8"+
		"\35\uffff:\36\uffff<\37\uffff> \uffff@!\uffffB\"\uffffD#\uffffF$\uffff"+
		"H%\uffffJ&\uffffL\'\uffffN(\uffffP)\uffffR*\uffffT+\uffffV,\uffffX-\uffff"+
		"Z.\0\\/\uffff^\3\6`\60\uffffb\61\uffffd\62\ufffff\63\uffffh\64\uffffj"+
		"\65\uffffl\66\uffffn\67\uffffp8\uffffr9\ufffft:\7v;\uffffx<\uffffz\0\uffff"+
		"|\0\uffff~=\uffff\u0080\0\b\u0082>\1\u0084?\2\u0086@\3\u0088\0\t\u008a"+
		"\0\n\u008c\0\13\u008e\0\f\u0090A\uffff\u0092B\uffff\u0094C\uffff\u0096"+
		"D\uffff\u0098E\uffff\u009a\0\r\u009c\0\16\u009e\0\17\u00a0\0\20\u00a2"+
		"\0\21\u00a4\0\22\u00a6F\uffff\u00a8G\23\u00aa\0\24\u00ac\0\25\u00aeH\uffff"+
		"\u00b0I\uffff\u00b2J\uffff\u00b4K\uffff\u00b6L\uffff\u00b8\0\26\u00ba"+
		"\0\27\u00bc\0\30\u00beM\uffff\u00c0N\uffff\u00c2O\uffff\u00c4\0\31\6\0"+
		"\1\2\3\4\5\22\3AZ__az\4\609AZ__az\2\t\t  \2\n\n\r\r\4\n\n\r\r\'\'\\\\"+
		"\4\n\n\r\r\"\"\\\\\3\t\n\r\r  \3\609AFaf\3\n\n\r\r**\t\n\n\r\r\"\"$$\'"+
		"\'//[]{{}}\t\"\"\'\'>>\\]bbffnnrrtt\3$$//\\\\\2\n\n\r\r\t\n\n\r\r\"\""+
		"$$\'\'//\\\\{{}}\t\"\"\'\'>>\\\\bbffnnrrtt\3$$//\\\\\3\n\n\r\ruu\3\n\n"+
		"\r\r\\]\u02a9\0\6\1\0\0\0\0\b\1\0\0\0\0\n\1\0\0\0\0\f\1\0\0\0\0\16\1\0"+
		"\0\0\0\20\1\0\0\0\0\22\1\0\0\0\0\24\1\0\0\0\0\26\1\0\0\0\0\30\1\0\0\0"+
		"\0\32\1\0\0\0\0\34\1\0\0\0\0\36\1\0\0\0\0 \1\0\0\0\0\"\1\0\0\0\0$\1\0"+
		"\0\0\0&\1\0\0\0\0(\1\0\0\0\0*\1\0\0\0\0,\1\0\0\0\0.\1\0\0\0\0\60\1\0\0"+
		"\0\0\62\1\0\0\0\0\64\1\0\0\0\0\66\1\0\0\0\08\1\0\0\0\0:\1\0\0\0\0<\1\0"+
		"\0\0\0>\1\0\0\0\0@\1\0\0\0\0B\1\0\0\0\0D\1\0\0\0\0F\1\0\0\0\0H\1\0\0\0"+
		"\0J\1\0\0\0\0L\1\0\0\0\0N\1\0\0\0\0P\1\0\0\0\0R\1\0\0\0\0T\1\0\0\0\0V"+
		"\1\0\0\0\0X\1\0\0\0\0Z\1\0\0\0\0\\\1\0\0\0\0^\1\0\0\0\0`\1\0\0\0\0b\1"+
		"\0\0\0\0d\1\0\0\0\0f\1\0\0\0\0h\1\0\0\0\0j\1\0\0\0\0l\1\0\0\0\0n\1\0\0"+
		"\0\0p\1\0\0\0\0r\1\0\0\0\0t\1\0\0\0\0v\1\0\0\0\0x\1\0\0\0\0~\1\0\0\0\1"+
		"\u0080\1\0\0\0\1\u0082\1\0\0\0\1\u0084\1\0\0\0\1\u0086\1\0\0\0\1\u0088"+
		"\1\0\0\0\2\u008a\1\0\0\0\2\u008c\1\0\0\0\2\u008e\1\0\0\0\2\u0090\1\0\0"+
		"\0\2\u0092\1\0\0\0\2\u0094\1\0\0\0\2\u0096\1\0\0\0\2\u0098\1\0\0\0\2\u009a"+
		"\1\0\0\0\2\u009c\1\0\0\0\3\u009e\1\0\0\0\3\u00a0\1\0\0\0\3\u00a2\1\0\0"+
		"\0\4\u00a4\1\0\0\0\4\u00a6\1\0\0\0\4\u00a8\1\0\0\0\4\u00aa\1\0\0\0\4\u00ac"+
		"\1\0\0\0\4\u00ae\1\0\0\0\4\u00b0\1\0\0\0\4\u00b2\1\0\0\0\4\u00b4\1\0\0"+
		"\0\4\u00b6\1\0\0\0\4\u00b8\1\0\0\0\4\u00ba\1\0\0\0\5\u00bc\1\0\0\0\5\u00be"+
		"\1\0\0\0\5\u00c0\1\0\0\0\5\u00c2\1\0\0\0\5\u00c4\1\0\0\0\6\u00c6\1\0\0"+
		"\0\b\u00cc\1\0\0\0\n\u00d3\1\0\0\0\f\u00d9\1\0\0\0\16\u00e1\1\0\0\0\20"+
		"\u00e9\1\0\0\0\22\u00f1\1\0\0\0\24\u00fb\1\0\0\0\26\u0102\1\0\0\0\30\u010a"+
		"\1\0\0\0\32\u0111\1\0\0\0\34\u0118\1\0\0\0\36\u0121\1\0\0\0 \u012d\1\0"+
		"\0\0\"\u013a\1\0\0\0$\u013f\1\0\0\0&\u0146\1\0\0\0(\u014f\1\0\0\0*\u0154"+
		"\1\0\0\0,\u015a\1\0\0\0.\u015c\1\0\0\0\60\u015e\1\0\0\0\62\u0160\1\0\0"+
		"\0\64\u0162\1\0\0\0\66\u0164\1\0\0\08\u0167\1\0\0\0:\u0169\1\0\0\0<\u016b"+
		"\1\0\0\0>\u016d\1\0\0\0@\u016f\1\0\0\0B\u0171\1\0\0\0D\u0173\1\0\0\0F"+
		"\u0176\1\0\0\0H\u0179\1\0\0\0J\u017c\1\0\0\0L\u017e\1\0\0\0N\u0180\1\0"+
		"\0\0P\u0182\1\0\0\0R\u0184\1\0\0\0T\u0186\1\0\0\0V\u018a\1\0\0\0X\u018d"+
		"\1\0\0\0Z\u018f\1\0\0\0\\\u0192\1\0\0\0^\u0194\1\0\0\0`\u0198\1\0\0\0"+
		"b\u019a\1\0\0\0d\u019c\1\0\0\0f\u01a3\1\0\0\0h\u01ab\1\0\0\0j\u01af\1"+
		"\0\0\0l\u01b2\1\0\0\0n\u01b6\1\0\0\0p\u01bb\1\0\0\0r\u01bf\1\0\0\0t\u01c8"+
		"\1\0\0\0v\u01cd\1\0\0\0x\u01d9\1\0\0\0z\u01e8\1\0\0\0|\u01eb\1\0\0\0~"+
		"\u01ed\1\0\0\0\u0080\u01ef\1\0\0\0\u0082\u01f4\1\0\0\0\u0084\u01fa\1\0"+
		"\0\0\u0086\u01ff\1\0\0\0\u0088\u0202\1\0\0\0\u008a\u0206\1\0\0\0\u008c"+
		"\u020a\1\0\0\0\u008e\u020e\1\0\0\0\u0090\u0213\1\0\0\0\u0092\u0217\1\0"+
		"\0\0\u0094\u0219\1\0\0\0\u0096\u021b\1\0\0\0\u0098\u0225\1\0\0\0\u009a"+
		"\u0227\1\0\0\0\u009c\u022b\1\0\0\0\u009e\u022f\1\0\0\0\u00a0\u0233\1\0"+
		"\0\0\u00a2\u0237\1\0\0\0\u00a4\u023b\1\0\0\0\u00a6\u023f\1\0\0\0\u00a8"+
		"\u0248\1\0\0\0\u00aa\u024d\1\0\0\0\u00ac\u0251\1\0\0\0\u00ae\u0256\1\0"+
		"\0\0\u00b0\u025a\1\0\0\0\u00b2\u025c\1\0\0\0\u00b4\u025e\1\0\0\0\u00b6"+
		"\u0268\1\0\0\0\u00b8\u026a\1\0\0\0\u00ba\u026e\1\0\0\0\u00bc\u0272\1\0"+
		"\0\0\u00be\u0276\1\0\0\0\u00c0\u028d\1\0\0\0\u00c2\u0290\1\0\0\0\u00c4"+
		"\u0294\1\0\0\0\u00c6\u00c7\5l\0\0\u00c7\u00c8\5e\0\0\u00c8\u00c9\5x\0"+
		"\0\u00c9\u00ca\5e\0\0\u00ca\u00cb\5r\0\0\u00cb\7\1\0\0\0\u00cc\u00cd\5"+
		"p\0\0\u00cd\u00ce\5a\0\0\u00ce\u00cf\5r\0\0\u00cf\u00d0\5s\0\0\u00d0\u00d1"+
		"\5e\0\0\u00d1\u00d2\5r\0\0\u00d2\t\1\0\0\0\u00d3\u00d4\5c\0\0\u00d4\u00d5"+
		"\5a\0\0\u00d5\u00d6\5t\0\0\u00d6\u00d7\5c\0\0\u00d7\u00d8\5h\0\0\u00d8"+
		"\13\1\0\0\0\u00d9\u00da\5f\0\0\u00da\u00db\5i\0\0\u00db\u00dc\5n\0\0\u00dc"+
		"\u00dd\5a\0\0\u00dd\u00de\5l\0\0\u00de\u00df\5l\0\0\u00df\u00e0\5y\0\0"+
		"\u00e0\r\1\0\0\0\u00e1\u00e2\5g\0\0\u00e2\u00e3\5r\0\0\u00e3\u00e4\5a"+
		"\0\0\u00e4\u00e5\5m\0\0\u00e5\u00e6\5m\0\0\u00e6\u00e7\5a\0\0\u00e7\u00e8"+
		"\5r\0\0\u00e8\17\1\0\0\0\u00e9\u00ea\5p\0\0\u00ea\u00eb\5r\0\0\u00eb\u00ec"+
		"\5i\0\0\u00ec\u00ed\5v\0\0\u00ed\u00ee\5a\0\0\u00ee\u00ef\5t\0\0\u00ef"+
		"\u00f0\5e\0\0\u00f0\21\1\0\0\0\u00f1\u00f2\5p\0\0\u00f2\u00f3\5r\0\0\u00f3"+
		"\u00f4\5o\0\0\u00f4\u00f5\5t\0\0\u00f5\u00f6\5e\0\0\u00f6\u00f7\5c\0\0"+
		"\u00f7\u00f8\5t\0\0\u00f8\u00f9\5e\0\0\u00f9\u00fa\5d\0\0\u00fa\23\1\0"+
		"\0\0\u00fb\u00fc\5p\0\0\u00fc\u00fd\5u\0\0\u00fd\u00fe\5b\0\0\u00fe\u00ff"+
		"\5l\0\0\u00ff\u0100\5i\0\0\u0100\u0101\5c\0\0\u0101\25\1\0\0\0\u0102\u0103"+
		"\5r\0\0\u0103\u0104\5e\0\0\u0104\u0105\5t\0\0\u0105\u0106\5u\0\0\u0106"+
		"\u0107\5r\0\0\u0107\u0108\5n\0\0\u0108\u0109\5s\0\0\u0109\27\1\0\0\0\u010a"+
		"\u010b\5t\0\0\u010b\u010c\5h\0\0\u010c\u010d\5r\0\0\u010d\u010e\5o\0\0"+
		"\u010e\u010f\5w\0\0\u010f\u0110\5s\0\0\u0110\31\1\0\0\0\u0111\u0112\5"+
		"i\0\0\u0112\u0113\5m\0\0\u0113\u0114\5p\0\0\u0114\u0115\5o\0\0\u0115\u0116"+
		"\5r\0\0\u0116\u0117\5t\0\0\u0117\33\1\0\0\0\u0118\u0119\5f\0\0\u0119\u011a"+
		"\5r\0\0\u011a\u011b\5a\0\0\u011b\u011c\5g\0\0\u011c\u011d\5m\0\0\u011d"+
		"\u011e\5e\0\0\u011e\u011f\5n\0\0\u011f\u0120\5t\0\0\u0120\35\1\0\0\0\u0121"+
		"\u0122\5t\0\0\u0122\u0123\5o\0\0\u0123\u0124\5k\0\0\u0124\u0125\5e\0\0"+
		"\u0125\u0126\5n\0\0\u0126\u0127\5s\0\0\u0127\u0128\1\0\0\0\u0128\u0129"+
		"\3z:\0\u0129\u012a\5{\0\0\u012a\u012b\1\0\0\0\u012b\u012c\6\f\4\0\u012c"+
		"\37\1\0\0\0\u012d\u012e\5o\0\0\u012e\u012f\5p\0\0\u012f\u0130\5t\0\0\u0130"+
		"\u0131\5i\0\0\u0131\u0132\5o\0\0\u0132\u0133\5n\0\0\u0133\u0134\5s\0\0"+
		"\u0134\u0135\1\0\0\0\u0135\u0136\3z:\0\u0136\u0137\5{\0\0\u0137\u0138"+
		"\1\0\0\0\u0138\u0139\6\r\5\0\u0139!\1\0\0\0\u013a\u013b\5m\0\0\u013b\u013c"+
		"\5o\0\0\u013c\u013d\5d\0\0\u013d\u013e\5e\0\0\u013e#\1\0\0\0\u013f\u0140"+
		"\5l\0\0\u0140\u0141\5o\0\0\u0141\u0142\5c\0\0\u0142\u0143\5a\0\0\u0143"+
		"\u0144\5l\0\0\u0144\u0145\5s\0\0\u0145%\1\0\0\0\u0146\u0147\5t\0\0\u0147"+
		"\u0148\5e\0\0\u0148\u0149\5m\0\0\u0149\u014a\5p\0\0\u014a\u014b\5l\0\0"+
		"\u014b\u014c\5a\0\0\u014c\u014d\5t\0\0\u014d\u014e\5e\0\0\u014e\'\1\0"+
		"\0\0\u014f\u0150\5t\0\0\u0150\u0151\5r\0\0\u0151\u0152\5e\0\0\u0152\u0153"+
		"\5e\0\0\u0153)\1\0\0\0\u0154\u0155\5s\0\0\u0155\u0156\5c\0\0\u0156\u0157"+
		"\5o\0\0\u0157\u0158\5p\0\0\u0158\u0159\5e\0\0\u0159+\1\0\0\0\u015a\u015b"+
		"\5<\0\0\u015b-\1\0\0\0\u015c\u015d\5>\0\0\u015d/\1\0\0\0\u015e\u015f\5"+
		"@\0\0\u015f\61\1\0\0\0\u0160\u0161\5,\0\0\u0161\63\1\0\0\0\u0162\u0163"+
		"\5?\0\0\u0163\65\1\0\0\0\u0164\u0165\5^\0\0\u0165\u0166\5(\0\0\u0166\67"+
		"\1\0\0\0\u0167\u0168\5(\0\0\u01689\1\0\0\0\u0169\u016a\5)\0\0\u016a;\1"+
		"\0\0\0\u016b\u016c\5:\0\0\u016c=\1\0\0\0\u016d\u016e\5*\0\0\u016e?\1\0"+
		"\0\0\u016f\u0170\5+\0\0\u0170A\1\0\0\0\u0171\u0172\5=\0\0\u0172C\1\0\0"+
		"\0\u0173\u0174\5+\0\0\u0174\u0175\5=\0\0\u0175E\1\0\0\0\u0176\u0177\5"+
		"=\0\0\u0177\u0178\5>\0\0\u0178G\1\0\0\0\u0179\u017a\5-\0\0\u017a\u017b"+
		"\5>\0\0\u017bI\1\0\0\0\u017c\u017d\5;\0\0\u017dK\1\0\0\0\u017e\u017f\5"+
		"^\0\0\u017fM\1\0\0\0\u0180\u0181\5!\0\0\u0181O\1\0\0\0\u0182\u0183\5|"+
		"\0\0\u0183Q\1\0\0\0\u0184\u0185\5.\0\0\u0185S\1\0\0\0\u0186\u0187\5.\0"+
		"\0\u0187\u0188\5.\0\0\u0188\u0189\5.\0\0\u0189U\1\0\0\0\u018a\u018b\5"+
		".\0\0\u018b\u018c\5.\0\0\u018cW\1\0\0\0\u018d\u018e\5~\0\0\u018eY\1\0"+
		"\0\0\u018f\u0190\5[\0\0\u0190\u0191\6*\0\0\u0191[\1\0\0\0\u0192\u0193"+
		"\5]\0\0\u0193]\1\0\0\0\u0194\u0195\5{\0\0\u0195\u0196\1\0\0\0\u0196\u0197"+
		"\6,\6\0\u0197_\1\0\0\0\u0198\u0199\5}\0\0\u0199a\1\0\0\0\u019a\u019b\5"+
		"$\0\0\u019bc\1\0\0\0\u019c\u019d\3f\60\0\u019d\u019f\3z:\0\u019e\u01a0"+
		"\5+\0\0\u019f\u019e\1\0\0\0\u019f\u01a0\1\0\0\0\u01a0\u01a1\1\0\0\0\u01a1"+
		"\u01a2\5=\0\0\u01a2e\1\0\0\0\u01a3\u01a7\7\0\0\0\u01a4\u01a6\7\1\0\0\u01a5"+
		"\u01a4\1\0\0\0\u01a6\u01a9\1\0\0\0\u01a7\u01a5\1\0\0\0\u01a7\u01a8\1\0"+
		"\0\0\u01a8g\1\0\0\0\u01a9\u01a7\1\0\0\0\u01aa\u01ac\2\609\0\u01ab\u01aa"+
		"\1\0\0\0\u01ac\u01ad\1\0\0\0\u01ad\u01ab\1\0\0\0\u01ad\u01ae\1\0\0\0\u01ae"+
		"i\1\0\0\0\u01af\u01b0\5@\0\0\u01b0\u01b1\3f\60\0\u01b1k\1\0\0\0\u01b2"+
		"\u01b3\5$\0\0\u01b3\u01b4\3f\60\0\u01b4m\1\0\0\0\u01b5\u01b7\7\2\0\0\u01b6"+
		"\u01b5\1\0\0\0\u01b7\u01b8\1\0\0\0\u01b8\u01b6\1\0\0\0\u01b8\u01b9\1\0"+
		"\0\0\u01b9o\1\0\0\0\u01ba\u01bc\5\r\0\0\u01bb\u01ba\1\0\0\0\u01bb\u01bc"+
		"\1\0\0\0\u01bc\u01bd\1\0\0\0\u01bd\u01be\5\n\0\0\u01beq\1\0\0\0\u01bf"+
		"\u01c0\5/\0\0\u01c0\u01c1\5/\0\0\u01c1\u01c5\1\0\0\0\u01c2\u01c4\b\3\0"+
		"\0\u01c3\u01c2\1\0\0\0\u01c4\u01c7\1\0\0\0\u01c5\u01c3\1\0\0\0\u01c5\u01c6"+
		"\1\0\0\0\u01c6s\1\0\0\0\u01c7\u01c5\1\0\0\0\u01c8\u01c9\5/\0\0\u01c9\u01ca"+
		"\5*\0\0\u01ca\u01cb\1\0\0\0\u01cb\u01cc\6\67\7\0\u01ccu\1\0\0\0\u01cd"+
		"\u01d3\5\'\0\0\u01ce\u01cf\5\\\0\0\u01cf\u01d2\t\0\0\0\u01d0\u01d2\b\4"+
		"\0\0\u01d1\u01ce\1\0\0\0\u01d1\u01d0\1\0\0\0\u01d2\u01d5\1\0\0\0\u01d3"+
		"\u01d1\1\0\0\0\u01d3\u01d4\1\0\0\0\u01d4\u01d7\1\0\0\0\u01d5\u01d3\1\0"+
		"\0\0\u01d6\u01d8\5\'\0\0\u01d7\u01d6\1\0\0\0\u01d7\u01d8\1\0\0\0\u01d8"+
		"w\1\0\0\0\u01d9\u01df\5\"\0\0\u01da\u01db\5\\\0\0\u01db\u01de\t\0\0\0"+
		"\u01dc\u01de\b\5\0\0\u01dd\u01da\1\0\0\0\u01dd\u01dc\1\0\0\0\u01de\u01e1"+
		"\1\0\0\0\u01df\u01dd\1\0\0\0\u01df\u01e0\1\0\0\0\u01e0\u01e3\1\0\0\0\u01e1"+
		"\u01df\1\0\0\0\u01e2\u01e4\5\"\0\0\u01e3\u01e2\1\0\0\0\u01e3\u01e4\1\0"+
		"\0\0\u01e4y\1\0\0\0\u01e5\u01e7\7\6\0\0\u01e6\u01e5\1\0\0\0\u01e7\u01ea"+
		"\1\0\0\0\u01e8\u01e6\1\0\0\0\u01e8\u01e9\1\0\0\0\u01e9{\1\0\0\0\u01ea"+
		"\u01e8\1\0\0\0\u01eb\u01ec\7\7\0\0\u01ec}\1\0\0\0\u01ed\u01ee\t\0\0\0"+
		"\u01ee\177\1\0\0\0\u01ef\u01f0\3p\65\0\u01f0\u01f1\1\0\0\0\u01f1\u01f2"+
		"\6=\b\0\u01f2\u0081\1\0\0\0\u01f3\u01f5\b\b\0\0\u01f4\u01f3\1\0\0\0\u01f5"+
		"\u01f6\1\0\0\0\u01f6\u01f4\1\0\0\0\u01f6\u01f7\1\0\0\0\u01f7\u01f8\1\0"+
		"\0\0\u01f8\u01f9\6>\1\0\u01f9\u0083\1\0\0\0\u01fa\u01fb\5*\0\0\u01fb\u01fc"+
		"\5/\0\0\u01fc\u01fd\1\0\0\0\u01fd\u01fe\6?\2\0\u01fe\u0085\1\0\0\0\u01ff"+
		"\u0200\5*\0\0\u0200\u0201\6@\3\0\u0201\u0087\1\0\0\0\u0202\u0203\t\0\0"+
		"\0\u0203\u0204\1\0\0\0\u0204\u0205\6A\t\0\u0205\u0089\1\0\0\0\u0206\u0207"+
		"\3p\65\0\u0207\u0208\1\0\0\0\u0208\u0209\6B\n\0\u0209\u008b\1\0\0\0\u020a"+
		"\u020b\5[\0\0\u020b\u020c\1\0\0\0\u020c\u020d\6C\13\0\u020d\u008d\1\0"+
		"\0\0\u020e\u020f\5]\0\0\u020f\u0210\1\0\0\0\u0210\u0211\6D\f\0\u0211\u008f"+
		"\1\0\0\0\u0212\u0214\b\t\0\0\u0213\u0212\1\0\0\0\u0214\u0215\1\0\0\0\u0215"+
		"\u0213\1\0\0\0\u0215\u0216\1\0\0\0\u0216\u0091\1\0\0\0\u0217\u0218\3v"+
		"8\0\u0218\u0093\1\0\0\0\u0219\u021a\3x9\0\u021a\u0095\1\0\0\0\u021b\u0223"+
		"\5\\\0\0\u021c\u0224\7\n\0\0\u021d\u021e\5u\0\0\u021e\u021f\3|;\0\u021f"+
		"\u0220\3|;\0\u0220\u0221\3|;\0\u0221\u0222\3|;\0\u0222\u0224\1\0\0\0\u0223"+
		"\u021c\1\0\0\0\u0223\u021d\1\0\0\0\u0224\u0097\1\0\0\0\u0225\u0226\3l"+
		"\63\0\u0226\u0099\1\0\0\0\u0227\u0228\7\13\0\0\u0228\u0229\1\0\0\0\u0229"+
		"\u022a\6J\r\0\u022a\u009b\1\0\0\0\u022b\u022c\t\0\0\0\u022c\u022d\1\0"+
		"\0\0\u022d\u022e\6K\16\0\u022e\u009d\1\0\0\0\u022f\u0230\3p\65\0\u0230"+
		"\u0231\1\0\0\0\u0231\u0232\6L\17\0\u0232\u009f\1\0\0\0\u0233\u0234\3n"+
		"\64\0\u0234\u0235\1\0\0\0\u0235\u0236\6M\20\0\u0236\u00a1\1\0\0\0\u0237"+
		"\u0238\3^,\0\u0238\u0239\1\0\0\0\u0239\u023a\6N\21\0\u023a\u00a3\1\0\0"+
		"\0\u023b\u023c\3p\65\0\u023c\u023d\1\0\0\0\u023d\u023e\6O\22\0\u023e\u00a5"+
		"\1\0\0\0\u023f\u0240\5/\0\0\u0240\u0241\5/\0\0\u0241\u0245\1\0\0\0\u0242"+
		"\u0244\b\f\0\0\u0243\u0242\1\0\0\0\u0244\u0247\1\0\0\0\u0245\u0243\1\0"+
		"\0\0\u0245\u0246\1\0\0\0\u0246\u00a7\1\0\0\0\u0247\u0245\1\0\0\0\u0248"+
		"\u0249\5/\0\0\u0249\u024a\5*\0\0\u024a\u024b\1\0\0\0\u024b\u024c\6Q\23"+
		"\0\u024c\u00a9\1\0\0\0\u024d\u024e\5{\0\0\u024e\u024f\1\0\0\0\u024f\u0250"+
		"\6R\24\0\u0250\u00ab\1\0\0\0\u0251\u0252\5}\0\0\u0252\u0253\1\0\0\0\u0253"+
		"\u0254\6S\25\0\u0254\u00ad\1\0\0\0\u0255\u0257\b\r\0\0\u0256\u0255\1\0"+
		"\0\0\u0257\u0258\1\0\0\0\u0258\u0256\1\0\0\0\u0258\u0259\1\0\0\0\u0259"+
		"\u00af\1\0\0\0\u025a\u025b\3v8\0\u025b\u00b1\1\0\0\0\u025c\u025d\3x9\0"+
		"\u025d\u00b3\1\0\0\0\u025e\u0266\5\\\0\0\u025f\u0267\7\16\0\0\u0260\u0261"+
		"\5u\0\0\u0261\u0262\3|;\0\u0262\u0263\3|;\0\u0263\u0264\3|;\0\u0264\u0265"+
		"\3|;\0\u0265\u0267\1\0\0\0\u0266\u025f\1\0\0\0\u0266\u0260\1\0\0\0\u0267"+
		"\u00b5\1\0\0\0\u0268\u0269\3l\63\0\u0269\u00b7\1\0\0\0\u026a\u026b\7\17"+
		"\0\0\u026b\u026c\1\0\0\0\u026c\u026d\6Y\26\0\u026d\u00b9\1\0\0\0\u026e"+
		"\u026f\t\0\0\0\u026f\u0270\1\0\0\0\u0270\u0271\6Z\27\0\u0271\u00bb\1\0"+
		"\0\0\u0272\u0273\3p\65\0\u0273\u0274\1\0\0\0\u0274\u0275\6[\30\0\u0275"+
		"\u00bd\1\0\0\0\u0276\u027e\5\\\0\0\u0277\u027f\b\20\0\0\u0278\u0279\5"+
		"u\0\0\u0279\u027a\3|;\0\u027a\u027b\3|;\0\u027b\u027c\3|;\0\u027c\u027d"+
		"\3|;\0\u027d\u027f\1\0\0\0\u027e\u0277\1\0\0\0\u027e\u0278\1\0\0\0\u027f"+
		"\u00bf\1\0\0\0\u0280\u028e\5\\\0\0\u0281\u0282\5\\\0\0\u0282\u0283\5u"+
		"\0\0\u0283\u028b\1\0\0\0\u0284\u0289\3|;\0\u0285\u0287\3|;\0\u0286\u0288"+
		"\3|;\0\u0287\u0286\1\0\0\0\u0287\u0288\1\0\0\0\u0288\u028a\1\0\0\0\u0289"+
		"\u0285\1\0\0\0\u0289\u028a\1\0\0\0\u028a\u028c\1\0\0\0\u028b\u0284\1\0"+
		"\0\0\u028b\u028c\1\0\0\0\u028c\u028e\1\0\0\0\u028d\u0280\1\0\0\0\u028d"+
		"\u0281\1\0\0\0\u028e\u00c1\1\0\0\0\u028f\u0291\b\21\0\0\u0290\u028f\1"+
		"\0\0\0\u0291\u0292\1\0\0\0\u0292\u0290\1\0\0\0\u0292\u0293\1\0\0\0\u0293"+
		"\u00c3\1\0\0\0\u0294\u0295\5]\0\0\u0295\u0296\1\0\0\0\u0296\u0297\6_\31"+
		"\0\u0297\u00c5\1\0\0\0\37\0\1\2\3\4\5\u019f\u01a7\u01ad\u01b8\u01bb\u01c5"+
		"\u01d1\u01d3\u01d7\u01dd\u01df\u01e3\u01e8\u01f6\u0215\u0223\u0245\u0258"+
		"\u0266\u027e\u0287\u0289\u028b\u028d\u0292";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}