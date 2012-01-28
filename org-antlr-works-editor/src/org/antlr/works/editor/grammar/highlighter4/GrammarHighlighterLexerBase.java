// $ANTLR ANTLRVersion> GrammarHighlighterLexerBase.java generatedTimestamp>

/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarHighlighterLexerBase extends Lexer {
	public static final int
		InvalidGrammarOption=3, ValidGrammarOption=4, LEXER=5, PARSER=6, CATCH=7, 
		FINALLY=8, GRAMMAR=9, PRIVATE=10, PROTECTED=11, PUBLIC=12, RETURNS=13, 
		THROWS=14, IMPORT=15, FRAGMENT=16, TOKENS=17, OPTIONS=18, MODE=19, LOCALS=20, 
		TREE=21, SCOPE=22, OPEN_ELEMENT_OPTION=23, CLOSE_ELEMENT_OPTION=24, AMPERSAND=25, 
		COMMA=26, QUESTION=27, TREE_BEGIN=28, LPAREN=29, RPAREN=30, COLON=31, 
		STAR=32, PLUS=33, ASSIGN=34, PLUS_ASSIGN=35, IMPLIES=36, REWRITE=37, SEMI=38, 
		ROOT=39, BANG=40, OR=41, WILDCARD=42, ETC=43, RANGE=44, NOT=45, LBRACK=46, 
		RBRACK=47, LCURLY=48, RCURLY=49, DOLLAR=50, LABEL=51, IDENTIFIER=52, INT=53, 
		DIRECTIVE=54, REFERENCE=55, WS=56, NEWLINE=57, COMMENT=58, ML_COMMENT=59, 
		CHAR_LITERAL=60, STRING_LITERAL=61, ANYCHAR=62, BlockComment_NEWLINE=63, 
		CONTINUE_ML_COMMENT=64, END_ML_COMMENT=65, ML_COMMENT_STAR=66, BlockComment_ANYCHAR=67, 
		ArgAction_NEWLINE=68, ArgAction_RBRACK=69, ArgAction_TEXT=70, ArgAction_CHAR_LITERAL=71, 
		ArgAction_STRING_LITERAL=72, ArgAction_ESCAPE=73, ArgAction_REFERENCE=74, 
		ArgAction_SPECIAL=75, ArgAction_ANYCHAR=76, NonActionBrace_NEWLINE=77, 
		NonActionBrace_WS=78, NonActionBrace_LCURLY=79, Action_NEWLINE=80, Action_COMMENT=81, 
		Action_ML_COMMENT=82, Action_LCURLY=83, Action_RCURLY=84, Action_TEXT=85, 
		Action_CHAR_LITERAL=86, Action_STRING_LITERAL=87, Action_ESCAPE=88, Action_REFERENCE=89, 
		Action_SPECIAL=90, Action_ANYCHAR=91;
	public static final int BlockComment = 1;
	public static final int ArgAction = 2;
	public static final int NonActionBrace = 3;
	public static final int Action = 4;
    public static String[] modeNames = {
        "DEFAULT_MODE", "BlockComment", "ArgAction", "NonActionBrace", "Action"
    };

	public static final String[] tokenNames = {
		"<INVALID>", "<INVALID>", "<INVALID>",
		"InvalidGrammarOption", "ValidGrammarOption", "lexer", "parser", "catch", 
		"finally", "grammar", "private", "protected", "public", "returns", "throws", 
		"import", "fragment", "TOKENS", "OPTIONS", "mode", "locals", "tree", "scope", 
		"<", ">", "@", ",", "?", "^(", "(", ")", ":", "STAR", "+", "=", "+=", 
		"=>", "->", ";", "^", "!", "|", ".", "...", "..", "~", "[", "RBRACK", 
		"LCURLY", "RCURLY", "$", "LABEL", "IDENTIFIER", "INT", "DIRECTIVE", "REFERENCE", 
		"WS", "NEWLINE", "COMMENT", "ML_COMMENT", "CHAR_LITERAL", "STRING_LITERAL", 
		"ANYCHAR", "BlockComment_NEWLINE", "CONTINUE_ML_COMMENT", "*/", "*", "BlockComment_ANYCHAR", 
		"ArgAction_NEWLINE", "]", "ArgAction_TEXT", "ArgAction_CHAR_LITERAL", 
		"ArgAction_STRING_LITERAL", "ArgAction_ESCAPE", "ArgAction_REFERENCE", 
		"ArgAction_SPECIAL", "ArgAction_ANYCHAR", "NonActionBrace_NEWLINE", "NonActionBrace_WS", 
		"NonActionBrace_LCURLY", "Action_NEWLINE", "Action_COMMENT", "/*", "{", 
		"}", "Action_TEXT", "Action_CHAR_LITERAL", "Action_STRING_LITERAL", "Action_ESCAPE", 
		"Action_REFERENCE", "Action_SPECIAL", "Action_ANYCHAR"
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
		"ArgAction_NEWLINE", "ArgAction_RBRACK", "ArgAction_TEXT", "ArgAction_CHAR_LITERAL", 
		"ArgAction_STRING_LITERAL", "ArgAction_ESCAPE", "ArgAction_REFERENCE", 
		"ArgAction_SPECIAL", "ArgAction_ANYCHAR", "NonActionBrace_NEWLINE", "NonActionBrace_WS", 
		"NonActionBrace_LCURLY", "Action_NEWLINE", "Action_COMMENT", "Action_ML_COMMENT", 
		"Action_LCURLY", "Action_RCURLY", "Action_TEXT", "Action_CHAR_LITERAL", 
		"Action_STRING_LITERAL", "Action_ESCAPE", "Action_REFERENCE", "Action_SPECIAL", 
		"Action_ANYCHAR"
	};


	protected int getMultilineCommentType() {
	    return modeStack.peek()==DEFAULT_MODE ? ML_COMMENT : Action_ML_COMMENT;
	}


	public GrammarHighlighterLexerBase(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN);
	}

	public String getGrammarFileName() { return "GrammarHighlighterLexerBase.java"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }


	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch ( ruleIndex ) {
			case 0 : LEXER_action((RuleContext)_localctx, actionIndex); break;

			case 1 : PARSER_action((RuleContext)_localctx, actionIndex); break;

			case 2 : CATCH_action((RuleContext)_localctx, actionIndex); break;

			case 3 : FINALLY_action((RuleContext)_localctx, actionIndex); break;

			case 4 : GRAMMAR_action((RuleContext)_localctx, actionIndex); break;

			case 5 : PRIVATE_action((RuleContext)_localctx, actionIndex); break;

			case 6 : PROTECTED_action((RuleContext)_localctx, actionIndex); break;

			case 7 : PUBLIC_action((RuleContext)_localctx, actionIndex); break;

			case 8 : RETURNS_action((RuleContext)_localctx, actionIndex); break;

			case 9 : THROWS_action((RuleContext)_localctx, actionIndex); break;

			case 10 : IMPORT_action((RuleContext)_localctx, actionIndex); break;

			case 11 : FRAGMENT_action((RuleContext)_localctx, actionIndex); break;

			case 12 : TOKENS_action((RuleContext)_localctx, actionIndex); break;

			case 13 : OPTIONS_action((RuleContext)_localctx, actionIndex); break;

			case 14 : MODE_action((RuleContext)_localctx, actionIndex); break;

			case 15 : LOCALS_action((RuleContext)_localctx, actionIndex); break;

			case 16 : TREE_action((RuleContext)_localctx, actionIndex); break;

			case 17 : SCOPE_action((RuleContext)_localctx, actionIndex); break;

			case 18 : OPEN_ELEMENT_OPTION_action((RuleContext)_localctx, actionIndex); break;

			case 19 : CLOSE_ELEMENT_OPTION_action((RuleContext)_localctx, actionIndex); break;

			case 20 : AMPERSAND_action((RuleContext)_localctx, actionIndex); break;

			case 21 : COMMA_action((RuleContext)_localctx, actionIndex); break;

			case 22 : QUESTION_action((RuleContext)_localctx, actionIndex); break;

			case 23 : TREE_BEGIN_action((RuleContext)_localctx, actionIndex); break;

			case 24 : LPAREN_action((RuleContext)_localctx, actionIndex); break;

			case 25 : RPAREN_action((RuleContext)_localctx, actionIndex); break;

			case 26 : COLON_action((RuleContext)_localctx, actionIndex); break;

			case 27 : STAR_action((RuleContext)_localctx, actionIndex); break;

			case 28 : PLUS_action((RuleContext)_localctx, actionIndex); break;

			case 29 : ASSIGN_action((RuleContext)_localctx, actionIndex); break;

			case 30 : PLUS_ASSIGN_action((RuleContext)_localctx, actionIndex); break;

			case 31 : IMPLIES_action((RuleContext)_localctx, actionIndex); break;

			case 32 : REWRITE_action((RuleContext)_localctx, actionIndex); break;

			case 33 : SEMI_action((RuleContext)_localctx, actionIndex); break;

			case 34 : ROOT_action((RuleContext)_localctx, actionIndex); break;

			case 35 : BANG_action((RuleContext)_localctx, actionIndex); break;

			case 36 : OR_action((RuleContext)_localctx, actionIndex); break;

			case 37 : WILDCARD_action((RuleContext)_localctx, actionIndex); break;

			case 38 : ETC_action((RuleContext)_localctx, actionIndex); break;

			case 39 : RANGE_action((RuleContext)_localctx, actionIndex); break;

			case 40 : NOT_action((RuleContext)_localctx, actionIndex); break;

			case 41 : LBRACK_action((RuleContext)_localctx, actionIndex); break;

			case 42 : RBRACK_action((RuleContext)_localctx, actionIndex); break;

			case 43 : LCURLY_action((RuleContext)_localctx, actionIndex); break;

			case 44 : RCURLY_action((RuleContext)_localctx, actionIndex); break;

			case 45 : DOLLAR_action((RuleContext)_localctx, actionIndex); break;

			case 46 : LABEL_action((RuleContext)_localctx, actionIndex); break;

			case 47 : IDENTIFIER_action((RuleContext)_localctx, actionIndex); break;

			case 48 : INT_action((RuleContext)_localctx, actionIndex); break;

			case 49 : DIRECTIVE_action((RuleContext)_localctx, actionIndex); break;

			case 50 : REFERENCE_action((RuleContext)_localctx, actionIndex); break;

			case 51 : WS_action((RuleContext)_localctx, actionIndex); break;

			case 52 : NEWLINE_action((RuleContext)_localctx, actionIndex); break;

			case 53 : COMMENT_action((RuleContext)_localctx, actionIndex); break;

			case 54 : ML_COMMENT_action((RuleContext)_localctx, actionIndex); break;

			case 55 : CHAR_LITERAL_action((RuleContext)_localctx, actionIndex); break;

			case 56 : STRING_LITERAL_action((RuleContext)_localctx, actionIndex); break;

			case 57 : IGNORED_action((RuleContext)_localctx, actionIndex); break;

			case 58 : XDIGIT_action((RuleContext)_localctx, actionIndex); break;

			case 59 : ANYCHAR_action((RuleContext)_localctx, actionIndex); break;

			case 60 : BlockComment_NEWLINE_action((RuleContext)_localctx, actionIndex); break;

			case 61 : CONTINUE_ML_COMMENT_action((RuleContext)_localctx, actionIndex); break;

			case 62 : END_ML_COMMENT_action((RuleContext)_localctx, actionIndex); break;

			case 63 : ML_COMMENT_STAR_action((RuleContext)_localctx, actionIndex); break;

			case 64 : BlockComment_ANYCHAR_action((RuleContext)_localctx, actionIndex); break;

			case 65 : ArgAction_NEWLINE_action((RuleContext)_localctx, actionIndex); break;

			case 66 : ArgAction_RBRACK_action((RuleContext)_localctx, actionIndex); break;

			case 67 : ArgAction_TEXT_action((RuleContext)_localctx, actionIndex); break;

			case 68 : ArgAction_CHAR_LITERAL_action((RuleContext)_localctx, actionIndex); break;

			case 69 : ArgAction_STRING_LITERAL_action((RuleContext)_localctx, actionIndex); break;

			case 70 : ArgAction_ESCAPE_action((RuleContext)_localctx, actionIndex); break;

			case 71 : ArgAction_REFERENCE_action((RuleContext)_localctx, actionIndex); break;

			case 72 : ArgAction_SPECIAL_action((RuleContext)_localctx, actionIndex); break;

			case 73 : ArgAction_ANYCHAR_action((RuleContext)_localctx, actionIndex); break;

			case 74 : NonActionBrace_NEWLINE_action((RuleContext)_localctx, actionIndex); break;

			case 75 : NonActionBrace_WS_action((RuleContext)_localctx, actionIndex); break;

			case 76 : NonActionBrace_LCURLY_action((RuleContext)_localctx, actionIndex); break;

			case 77 : Action_NEWLINE_action((RuleContext)_localctx, actionIndex); break;

			case 78 : Action_COMMENT_action((RuleContext)_localctx, actionIndex); break;

			case 79 : Action_ML_COMMENT_action((RuleContext)_localctx, actionIndex); break;

			case 80 : Action_LCURLY_action((RuleContext)_localctx, actionIndex); break;

			case 81 : Action_RCURLY_action((RuleContext)_localctx, actionIndex); break;

			case 82 : Action_TEXT_action((RuleContext)_localctx, actionIndex); break;

			case 83 : Action_CHAR_LITERAL_action((RuleContext)_localctx, actionIndex); break;

			case 84 : Action_STRING_LITERAL_action((RuleContext)_localctx, actionIndex); break;

			case 85 : Action_ESCAPE_action((RuleContext)_localctx, actionIndex); break;

			case 86 : Action_REFERENCE_action((RuleContext)_localctx, actionIndex); break;

			case 87 : Action_SPECIAL_action((RuleContext)_localctx, actionIndex); break;

			case 88 : Action_ANYCHAR_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	public void DOLLAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CLOSE_ELEMENT_OPTION_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PROTECTED_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 9 : type =  NEWLINE; break;
		}
	}
	public void END_ML_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 6 : type =  getMultilineCommentType(); popMode(); break;
		}
	}
	public void STAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LBRACK_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void NonActionBrace_NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 13 : type =  NEWLINE; break;
		}
	}
	public void FRAGMENT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_STRING_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void NOT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_SPECIAL_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 20 : type =  Action_TEXT; break;
		}
	}
	public void Action_NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 16 : type =  NEWLINE; break;
		}
	}
	public void DIRECTIVE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TREE_BEGIN_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LPAREN_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_REFERENCE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ML_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 3 : pushMode(BlockComment); break;
		}
	}
	public void RPAREN_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void IMPORT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_CHAR_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void STRING_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_ANYCHAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 12 : type =  ANYCHAR; break;
		}
	}
	public void SCOPE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TREE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ETC_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void COMMA_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void IDENTIFIER_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_RCURLY_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 19 : popMode(); type =  Action_TEXT; break;
		}
	}
	public void NonActionBrace_WS_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 14 : type =  WS; break;
		}
	}
	public void WILDCARD_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_ESCAPE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ANYCHAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PLUS_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_ML_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 17 : pushMode(BlockComment); break;
		}
	}
	public void IGNORED_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_ANYCHAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 21 : type =  ANYCHAR; break;
		}
	}
	public void ML_COMMENT_STAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 7 : type =  getMultilineCommentType(); break;
		}
	}
	public void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_REFERENCE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void MODE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void GRAMMAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_SPECIAL_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 11 : type =  Action_TEXT; break;
		}
	}
	public void RETURNS_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RBRACK_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LOCALS_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_CHAR_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void IMPLIES_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PRIVATE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_RBRACK_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 10 : type =  RBRACK; popMode(); break;
		}
	}
	public void AMPERSAND_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void REFERENCE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TOKENS_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 0 : pushMode(NonActionBrace); break;
		}
	}
	public void RANGE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void THROWS_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LCURLY_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 2 : type = Action_TEXT; pushMode(Action); break;
		}
	}
	public void BANG_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void INT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void SEMI_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ROOT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void NonActionBrace_LCURLY_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 15 : type =  LCURLY; popMode(); break;
		}
	}
	public void REWRITE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void COLON_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_STRING_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void OPEN_ELEMENT_OPTION_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void WS_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BlockComment_ANYCHAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 8 : type =  ANYCHAR; break;
		}
	}
	public void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void QUESTION_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CHAR_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void FINALLY_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_ESCAPE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LABEL_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LEXER_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void XDIGIT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ArgAction_TEXT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CONTINUE_ML_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 5 : type =  getMultilineCommentType(); break;
		}
	}
	public void OR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RCURLY_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ASSIGN_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PLUS_ASSIGN_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_TEXT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Action_LCURLY_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 18 : pushMode(Action); type =  Action_TEXT; break;
		}
	}
	public void CATCH_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PARSER_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PUBLIC_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void OPTIONS_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 1 : pushMode(NonActionBrace); break;
		}
	}
	public void BlockComment_NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 4 : type =  NEWLINE; break;
		}
	}

	public static final String _serializedATN =
		"\2[\u02a6\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\2\0\7\0\2\1\7\1\2\2"+
		"\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2"+
		"\13\7\13\2\f\7\f\2\r\7\r\2\16\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22"+
		"\7\22\2\23\7\23\2\24\7\24\2\25\7\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31"+
		"\7\31\2\32\7\32\2\33\7\33\2\34\7\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7"+
		" \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7%\2&\7&\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7"+
		"+\2,\7,\2-\7-\2.\7.\2/\7/\2\60\7\60\2\61\7\61\2\62\7\62\2\63\7\63\2\64"+
		"\7\64\2\65\7\65\2\66\7\66\2\67\7\67\28\78\29\79\2:\7:\2;\7;\2<\7<\2=\7"+
		"=\2>\7>\2?\7?\2@\7@\2A\7A\2B\7B\2C\7C\2D\7D\2E\7E\2F\7F\2G\7G\2H\7H\2"+
		"I\7I\2J\7J\2K\7K\2L\7L\2M\7M\2N\7N\2O\7O\2P\7P\2Q\7Q\2R\7R\2S\7S\2T\7"+
		"T\2U\7U\2V\7V\2W\7W\2X\7X\1\0\1\0\1\0\1\0\1\0\1\0\1\1\1\1\1\1\1\1\1\1"+
		"\1\1\1\1\1\2\1\2\1\2\1\2\1\2\1\2\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\4\1"+
		"\4\1\4\1\4\1\4\1\4\1\4\1\4\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\6\1\6\1\6"+
		"\1\6\1\6\1\6\1\6\1\6\1\6\1\6\1\7\1\7\1\7\1\7\1\7\1\7\1\7\1\b\1\b\1\b\1"+
		"\b\1\b\1\b\1\b\1\b\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\n\1\n\1\n\1\n\1\n\1\n"+
		"\1\n\1\13\1\13\1\13\1\13\1\13\1\13\1\13\1\13\1\13\1\f\1\f\1\f\1\f\1\f"+
		"\1\f\1\f\1\f\0\1\f\0\1\f\1\f\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\0\1\r"+
		"\0\1\r\1\r\1\16\1\16\1\16\1\16\1\16\1\17\1\17\1\17\1\17\1\17\1\17\1\17"+
		"\1\20\1\20\1\20\1\20\1\20\1\21\1\21\1\21\1\21\1\21\1\21\1\22\1\22\1\23"+
		"\1\23\1\24\1\24\1\25\1\25\1\26\1\26\1\27\1\27\1\27\1\30\1\30\1\31\1\31"+
		"\1\32\1\32\1\33\1\33\1\34\1\34\1\35\1\35\1\36\1\36\1\36\1\37\1\37\1\37"+
		"\1 \1 \1 \1!\1!\1\"\1\"\1#\1#\1$\1$\1%\1%\1&\1&\1&\1&\1\'\1\'\1\'\1(\1"+
		"(\1)\1)\1*\1*\1+\0\1+\1+\1,\1,\1-\1-\1.\0\1.\0\1.\0\3.\b.\1.\1.\1/\0\1"+
		"/\0\5/\b/\n/\f/\u0197\t/\1\60\0\4\60\b\60\13\60\f\60\u019c\1\61\0\1\61"+
		"\1\61\1\62\0\1\62\1\62\1\63\0\4\63\b\63\13\63\f\63\u01aa\1\64\0\3\64\b"+
		"\64\1\64\1\64\1\65\1\65\1\65\1\65\0\5\65\b\65\n\65\f\65\u01bb\t\65\1\66"+
		"\1\66\1\66\1\66\1\66\1\67\0\1\67\0\1\67\0\1\67\0\5\67\b\67\n\67\f\67\u01cd"+
		"\t\67\1\67\0\3\67\b\67\18\0\18\0\18\0\18\0\58\b8\n8\f8\u01de\t8\18\0\3"+
		"8\b8\19\0\59\b9\n9\f9\u01e9\t9\1:\0\1:\0\1:\0\3:\b:\1;\1;\1<\0\1<\1<\1"+
		"=\0\4=\b=\13=\f=\u01fc\1=\1=\1>\1>\1>\1>\1>\1?\0\1?\1?\1@\0\1@\1@\1A\0"+
		"\1A\1A\1B\0\1B\1B\1C\0\4C\bC\13C\fC\u0219\1D\1D\1E\1E\1F\0\1F\0\1F\0\1"+
		"F\0\1F\0\1F\0\1F\0\1F\0\1F\0\1F\0\1F\0\1F\0\1F\0\1F\0\1F\0\1F\1F\3F\b"+
		"F\1G\1G\1H\0\1H\1H\1I\0\1I\1I\1J\0\1J\1J\1K\0\1K\1K\1L\0\1L\1L\1M\0\1"+
		"M\1M\1N\1N\1N\1N\0\5N\bN\nN\fN\u0264\tN\1O\1O\1O\1O\1O\1P\0\1P\1P\1Q\0"+
		"\1Q\1Q\1R\0\4R\bR\13R\fR\u0276\1S\1S\1T\1T\1U\0\1U\0\1U\0\1U\0\1U\0\1"+
		"U\0\1U\0\1U\0\1U\0\1U\0\1U\0\1U\0\1U\0\1U\0\1U\1U\3U\bU\1V\1V\1W\0\1W"+
		"\1W\1X\0\1X\1XY\5\5\uffff\7\6\uffff\t\7\uffff\13\b\uffff\r\t\uffff\17"+
		"\n\uffff\21\13\uffff\23\f\uffff\25\r\uffff\27\16\uffff\31\17\uffff\33"+
		"\20\uffff\35\21\0\37\22\1!\23\uffff#\24\uffff%\25\uffff\'\26\uffff)\27"+
		"\uffff+\30\uffff-\31\uffff/\32\uffff\61\33\uffff\63\34\uffff\65\35\uffff"+
		"\67\36\uffff9\37\uffff; \uffff=!\uffff?\"\uffffA#\uffffC$\uffffE%\uffff"+
		"G&\uffffI\'\uffffK(\uffffM)\uffffO*\uffffQ+\uffffS,\uffffU-\uffffW.\uffff"+
		"Y/\uffff[\60\2]\61\uffff_\62\uffffa\63\uffffc\64\uffffe\65\uffffg\66\uffff"+
		"i\67\uffffk8\uffffm9\uffffo:\uffffq;\3s<\uffffu=\uffffw\0\uffffy\0\uffff"+
		"{>\uffff}?\4\177@\5\u0081A\6\u0083B\7\u0085C\b\u0087D\t\u0089E\n\u008b"+
		"F\uffff\u008dG\uffff\u008fH\uffff\u0091I\uffff\u0093J\uffff\u0095K\13"+
		"\u0097L\f\u0099M\r\u009bN\16\u009dO\17\u009fP\20\u00a1Q\uffff\u00a3R\21"+
		"\u00a5S\22\u00a7T\23\u00a9U\uffff\u00abV\uffff\u00adW\uffff\u00afX\uffff"+
		"\u00b1Y\uffff\u00b3Z\24\u00b5[\25\5\0\1\2\3\4\r\3AZ__az\4\609AZ__az\2"+
		"\t\t  \2\n\n\r\r\4\n\n\r\r\'\'\\\\\4\n\n\r\r\"\"\\\\\3\t\n\r\r  \3\n\n"+
		"\r\r**\t\n\n\r\r\"\"$$\'\'//\\\\{{}}\3$$//\\\\\2\n\n\r\r\t\n\n\r\r\"\""+
		"$$\'\'//\\\\{{}}\3$$//\\\\\u0277\0\5\1\0\0\0\0\7\1\0\0\0\0\t\1\0\0\0\0"+
		"\13\1\0\0\0\0\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1\0\0\0\0\25\1"+
		"\0\0\0\0\27\1\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0\0\0\37\1\0\0"+
		"\0\0!\1\0\0\0\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0\0\0+\1\0\0\0"+
		"\0-\1\0\0\0\0/\1\0\0\0\0\61\1\0\0\0\0\63\1\0\0\0\0\65\1\0\0\0\0\67\1\0"+
		"\0\0\09\1\0\0\0\0;\1\0\0\0\0=\1\0\0\0\0?\1\0\0\0\0A\1\0\0\0\0C\1\0\0\0"+
		"\0E\1\0\0\0\0G\1\0\0\0\0I\1\0\0\0\0K\1\0\0\0\0M\1\0\0\0\0O\1\0\0\0\0Q"+
		"\1\0\0\0\0S\1\0\0\0\0U\1\0\0\0\0W\1\0\0\0\0Y\1\0\0\0\0[\1\0\0\0\0]\1\0"+
		"\0\0\0_\1\0\0\0\0a\1\0\0\0\0c\1\0\0\0\0e\1\0\0\0\0g\1\0\0\0\0i\1\0\0\0"+
		"\0k\1\0\0\0\0m\1\0\0\0\0o\1\0\0\0\0q\1\0\0\0\0s\1\0\0\0\0u\1\0\0\0\0{"+
		"\1\0\0\0\1}\1\0\0\0\1\177\1\0\0\0\1\u0081\1\0\0\0\1\u0083\1\0\0\0\1\u0085"+
		"\1\0\0\0\2\u0087\1\0\0\0\2\u0089\1\0\0\0\2\u008b\1\0\0\0\2\u008d\1\0\0"+
		"\0\2\u008f\1\0\0\0\2\u0091\1\0\0\0\2\u0093\1\0\0\0\2\u0095\1\0\0\0\2\u0097"+
		"\1\0\0\0\3\u0099\1\0\0\0\3\u009b\1\0\0\0\3\u009d\1\0\0\0\4\u009f\1\0\0"+
		"\0\4\u00a1\1\0\0\0\4\u00a3\1\0\0\0\4\u00a5\1\0\0\0\4\u00a7\1\0\0\0\4\u00a9"+
		"\1\0\0\0\4\u00ab\1\0\0\0\4\u00ad\1\0\0\0\4\u00af\1\0\0\0\4\u00b1\1\0\0"+
		"\0\4\u00b3\1\0\0\0\4\u00b5\1\0\0\0\5\u00b7\1\0\0\0\7\u00bd\1\0\0\0\t\u00c4"+
		"\1\0\0\0\13\u00ca\1\0\0\0\r\u00d2\1\0\0\0\17\u00da\1\0\0\0\21\u00e2\1"+
		"\0\0\0\23\u00ec\1\0\0\0\25\u00f3\1\0\0\0\27\u00fb\1\0\0\0\31\u0102\1\0"+
		"\0\0\33\u0109\1\0\0\0\35\u0112\1\0\0\0\37\u011f\1\0\0\0!\u012d\1\0\0\0"+
		"#\u0132\1\0\0\0%\u0139\1\0\0\0\'\u013e\1\0\0\0)\u0144\1\0\0\0+\u0146\1"+
		"\0\0\0-\u0148\1\0\0\0/\u014a\1\0\0\0\61\u014c\1\0\0\0\63\u014e\1\0\0\0"+
		"\65\u0151\1\0\0\0\67\u0153\1\0\0\09\u0155\1\0\0\0;\u0157\1\0\0\0=\u0159"+
		"\1\0\0\0?\u015b\1\0\0\0A\u015d\1\0\0\0C\u0160\1\0\0\0E\u0163\1\0\0\0G"+
		"\u0166\1\0\0\0I\u0168\1\0\0\0K\u016a\1\0\0\0M\u016c\1\0\0\0O\u016e\1\0"+
		"\0\0Q\u0170\1\0\0\0S\u0174\1\0\0\0U\u0177\1\0\0\0W\u0179\1\0\0\0Y\u017b"+
		"\1\0\0\0[\u017d\1\0\0\0]\u0181\1\0\0\0_\u0183\1\0\0\0a\u0185\1\0\0\0c"+
		"\u018f\1\0\0\0e\u019a\1\0\0\0g\u019e\1\0\0\0i\u01a2\1\0\0\0k\u01a8\1\0"+
		"\0\0m\u01ae\1\0\0\0o\u01b2\1\0\0\0q\u01bc\1\0\0\0s\u01c1\1\0\0\0u\u01d2"+
		"\1\0\0\0w\u01e7\1\0\0\0y\u01f0\1\0\0\0{\u01f2\1\0\0\0}\u01f4\1\0\0\0\177"+
		"\u01fa\1\0\0\0\u0081\u0200\1\0\0\0\u0083\u0205\1\0\0\0\u0085\u0209\1\0"+
		"\0\0\u0087\u020d\1\0\0\0\u0089\u0211\1\0\0\0\u008b\u0217\1\0\0\0\u008d"+
		"\u021b\1\0\0\0\u008f\u021d\1\0\0\0\u0091\u021f\1\0\0\0\u0093\u0241\1\0"+
		"\0\0\u0095\u0243\1\0\0\0\u0097\u0247\1\0\0\0\u0099\u024b\1\0\0\0\u009b"+
		"\u024f\1\0\0\0\u009d\u0253\1\0\0\0\u009f\u0257\1\0\0\0\u00a1\u025b\1\0"+
		"\0\0\u00a3\u0265\1\0\0\0\u00a5\u026a\1\0\0\0\u00a7\u026e\1\0\0\0\u00a9"+
		"\u0274\1\0\0\0\u00ab\u0278\1\0\0\0\u00ad\u027a\1\0\0\0\u00af\u027c\1\0"+
		"\0\0\u00b1\u029c\1\0\0\0\u00b3\u029e\1\0\0\0\u00b5\u02a2\1\0\0\0\u00b7"+
		"\u00b8\5l\0\0\u00b8\u00b9\5e\0\0\u00b9\u00ba\5x\0\0\u00ba\u00bb\5e\0\0"+
		"\u00bb\u00bc\5r\0\0\u00bc\6\1\0\0\0\u00bd\u00be\5p\0\0\u00be\u00bf\5a"+
		"\0\0\u00bf\u00c0\5r\0\0\u00c0\u00c1\5s\0\0\u00c1\u00c2\5e\0\0\u00c2\u00c3"+
		"\5r\0\0\u00c3\b\1\0\0\0\u00c4\u00c5\5c\0\0\u00c5\u00c6\5a\0\0\u00c6\u00c7"+
		"\5t\0\0\u00c7\u00c8\5c\0\0\u00c8\u00c9\5h\0\0\u00c9\n\1\0\0\0\u00ca\u00cb"+
		"\5f\0\0\u00cb\u00cc\5i\0\0\u00cc\u00cd\5n\0\0\u00cd\u00ce\5a\0\0\u00ce"+
		"\u00cf\5l\0\0\u00cf\u00d0\5l\0\0\u00d0\u00d1\5y\0\0\u00d1\f\1\0\0\0\u00d2"+
		"\u00d3\5g\0\0\u00d3\u00d4\5r\0\0\u00d4\u00d5\5a\0\0\u00d5\u00d6\5m\0\0"+
		"\u00d6\u00d7\5m\0\0\u00d7\u00d8\5a\0\0\u00d8\u00d9\5r\0\0\u00d9\16\1\0"+
		"\0\0\u00da\u00db\5p\0\0\u00db\u00dc\5r\0\0\u00dc\u00dd\5i\0\0\u00dd\u00de"+
		"\5v\0\0\u00de\u00df\5a\0\0\u00df\u00e0\5t\0\0\u00e0\u00e1\5e\0\0\u00e1"+
		"\20\1\0\0\0\u00e2\u00e3\5p\0\0\u00e3\u00e4\5r\0\0\u00e4\u00e5\5o\0\0\u00e5"+
		"\u00e6\5t\0\0\u00e6\u00e7\5e\0\0\u00e7\u00e8\5c\0\0\u00e8\u00e9\5t\0\0"+
		"\u00e9\u00ea\5e\0\0\u00ea\u00eb\5d\0\0\u00eb\22\1\0\0\0\u00ec\u00ed\5"+
		"p\0\0\u00ed\u00ee\5u\0\0\u00ee\u00ef\5b\0\0\u00ef\u00f0\5l\0\0\u00f0\u00f1"+
		"\5i\0\0\u00f1\u00f2\5c\0\0\u00f2\24\1\0\0\0\u00f3\u00f4\5r\0\0\u00f4\u00f5"+
		"\5e\0\0\u00f5\u00f6\5t\0\0\u00f6\u00f7\5u\0\0\u00f7\u00f8\5r\0\0\u00f8"+
		"\u00f9\5n\0\0\u00f9\u00fa\5s\0\0\u00fa\26\1\0\0\0\u00fb\u00fc\5t\0\0\u00fc"+
		"\u00fd\5h\0\0\u00fd\u00fe\5r\0\0\u00fe\u00ff\5o\0\0\u00ff\u0100\5w\0\0"+
		"\u0100\u0101\5s\0\0\u0101\30\1\0\0\0\u0102\u0103\5i\0\0\u0103\u0104\5"+
		"m\0\0\u0104\u0105\5p\0\0\u0105\u0106\5o\0\0\u0106\u0107\5r\0\0\u0107\u0108"+
		"\5t\0\0\u0108\32\1\0\0\0\u0109\u010a\5f\0\0\u010a\u010b\5r\0\0\u010b\u010c"+
		"\5a\0\0\u010c\u010d\5g\0\0\u010d\u010e\5m\0\0\u010e\u010f\5e\0\0\u010f"+
		"\u0110\5n\0\0\u0110\u0111\5t\0\0\u0111\34\1\0\0\0\u0112\u0113\5t\0\0\u0113"+
		"\u0114\5o\0\0\u0114\u0115\5k\0\0\u0115\u0116\5e\0\0\u0116\u0117\5n\0\0"+
		"\u0117\u0118\5s\0\0\u0118\u0119\1\0\0\0\u0119\u011b\3w9\0\u011b\u011d"+
		"\5{\0\0\u011d\u011e\6\f\0\0\u011e\36\1\0\0\0\u011f\u0120\5o\0\0\u0120"+
		"\u0121\5p\0\0\u0121\u0122\5t\0\0\u0122\u0123\5i\0\0\u0123\u0124\5o\0\0"+
		"\u0124\u0125\5n\0\0\u0125\u0126\5s\0\0\u0126\u0127\1\0\0\0\u0127\u0129"+
		"\3w9\0\u0129\u012b\5{\0\0\u012b\u012c\6\r\1\0\u012c \1\0\0\0\u012d\u012e"+
		"\5m\0\0\u012e\u012f\5o\0\0\u012f\u0130\5d\0\0\u0130\u0131\5e\0\0\u0131"+
		"\"\1\0\0\0\u0132\u0133\5l\0\0\u0133\u0134\5o\0\0\u0134\u0135\5c\0\0\u0135"+
		"\u0136\5a\0\0\u0136\u0137\5l\0\0\u0137\u0138\5s\0\0\u0138$\1\0\0\0\u0139"+
		"\u013a\5t\0\0\u013a\u013b\5r\0\0\u013b\u013c\5e\0\0\u013c\u013d\5e\0\0"+
		"\u013d&\1\0\0\0\u013e\u013f\5s\0\0\u013f\u0140\5c\0\0\u0140\u0141\5o\0"+
		"\0\u0141\u0142\5p\0\0\u0142\u0143\5e\0\0\u0143(\1\0\0\0\u0144\u0145\5"+
		"<\0\0\u0145*\1\0\0\0\u0146\u0147\5>\0\0\u0147,\1\0\0\0\u0148\u0149\5@"+
		"\0\0\u0149.\1\0\0\0\u014a\u014b\5,\0\0\u014b\60\1\0\0\0\u014c\u014d\5"+
		"?\0\0\u014d\62\1\0\0\0\u014e\u014f\5^\0\0\u014f\u0150\5(\0\0\u0150\64"+
		"\1\0\0\0\u0151\u0152\5(\0\0\u0152\66\1\0\0\0\u0153\u0154\5)\0\0\u0154"+
		"8\1\0\0\0\u0155\u0156\5:\0\0\u0156:\1\0\0\0\u0157\u0158\5*\0\0\u0158<"+
		"\1\0\0\0\u0159\u015a\5+\0\0\u015a>\1\0\0\0\u015b\u015c\5=\0\0\u015c@\1"+
		"\0\0\0\u015d\u015e\5+\0\0\u015e\u015f\5=\0\0\u015fB\1\0\0\0\u0160\u0161"+
		"\5=\0\0\u0161\u0162\5>\0\0\u0162D\1\0\0\0\u0163\u0164\5-\0\0\u0164\u0165"+
		"\5>\0\0\u0165F\1\0\0\0\u0166\u0167\5;\0\0\u0167H\1\0\0\0\u0168\u0169\5"+
		"^\0\0\u0169J\1\0\0\0\u016a\u016b\5!\0\0\u016bL\1\0\0\0\u016c\u016d\5|"+
		"\0\0\u016dN\1\0\0\0\u016e\u016f\5.\0\0\u016fP\1\0\0\0\u0170\u0171\5.\0"+
		"\0\u0171\u0172\5.\0\0\u0172\u0173\5.\0\0\u0173R\1\0\0\0\u0174\u0175\5"+
		".\0\0\u0175\u0176\5.\0\0\u0176T\1\0\0\0\u0177\u0178\5~\0\0\u0178V\1\0"+
		"\0\0\u0179\u017a\5[\0\0\u017aX\1\0\0\0\u017b\u017c\5]\0\0\u017cZ\1\0\0"+
		"\0\u017d\u017f\5{\0\0\u017f\u0180\6+\2\0\u0180\\\1\0\0\0\u0181\u0182\5"+
		"}\0\0\u0182^\1\0\0\0\u0183\u0184\5$\0\0\u0184`\1\0\0\0\u0185\u0187\3c"+
		"/\0\u0187\u018b\3w9\0\u0189\u018c\5+\0\0\u018b\u0189\1\0\0\0\u018b\u018c"+
		"\1\0\0\0\u018c\u018d\1\0\0\0\u018d\u018e\5=\0\0\u018eb\1\0\0\0\u018f\u0195"+
		"\7\0\0\0\u0191\u0194\7\1\0\0\u0193\u0191\1\0\0\0\u0194\u0197\1\0\0\0\u0195"+
		"\u0193\1\0\0\0\u0195\u0196\1\0\0\0\u0196d\1\0\0\0\u0197\u0195\1\0\0\0"+
		"\u0198\u019b\2\609\0\u019a\u0198\1\0\0\0\u019b\u019c\1\0\0\0\u019c\u019a"+
		"\1\0\0\0\u019c\u019d\1\0\0\0\u019df\1\0\0\0\u019e\u01a0\5@\0\0\u01a0\u01a1"+
		"\3c/\0\u01a1h\1\0\0\0\u01a2\u01a4\5$\0\0\u01a4\u01a5\3c/\0\u01a5j\1\0"+
		"\0\0\u01a6\u01a9\7\2\0\0\u01a8\u01a6\1\0\0\0\u01a9\u01aa\1\0\0\0\u01aa"+
		"\u01a8\1\0\0\0\u01aa\u01ab\1\0\0\0\u01abl\1\0\0\0\u01ac\u01af\5\r\0\0"+
		"\u01ae\u01ac\1\0\0\0\u01ae\u01af\1\0\0\0\u01af\u01b0\1\0\0\0\u01b0\u01b1"+
		"\5\n\0\0\u01b1n\1\0\0\0\u01b2\u01b3\5/\0\0\u01b3\u01b4\5/\0\0\u01b4\u01b9"+
		"\1\0\0\0\u01b5\u01b8\b\3\0\0\u01b7\u01b5\1\0\0\0\u01b8\u01bb\1\0\0\0\u01b9"+
		"\u01b7\1\0\0\0\u01b9\u01ba\1\0\0\0\u01bap\1\0\0\0\u01bb\u01b9\1\0\0\0"+
		"\u01bc\u01bd\5/\0\0\u01bd\u01be\5*\0\0\u01be\u01bf\1\0\0\0\u01bf\u01c0"+
		"\6\66\3\0\u01c0r\1\0\0\0\u01c1\u01cb\5\'\0\0\u01c3\u01c5\5\\\0\0\u01c5"+
		"\u01ca\t\0\0\0\u01c7\u01ca\b\4\0\0\u01c9\u01c3\1\0\0\0\u01c9\u01c7\1\0"+
		"\0\0\u01ca\u01cd\1\0\0\0\u01cb\u01c9\1\0\0\0\u01cb\u01cc\1\0\0\0\u01cc"+
		"\u01d0\1\0\0\0\u01cd\u01cb\1\0\0\0\u01ce\u01d1\5\'\0\0\u01d0\u01ce\1\0"+
		"\0\0\u01d0\u01d1\1\0\0\0\u01d1t\1\0\0\0\u01d2\u01dc\5\"\0\0\u01d4\u01d6"+
		"\5\\\0\0\u01d6\u01db\t\0\0\0\u01d8\u01db\b\5\0\0\u01da\u01d4\1\0\0\0\u01da"+
		"\u01d8\1\0\0\0\u01db\u01de\1\0\0\0\u01dc\u01da\1\0\0\0\u01dc\u01dd\1\0"+
		"\0\0\u01dd\u01e1\1\0\0\0\u01de\u01dc\1\0\0\0\u01df\u01e2\5\"\0\0\u01e1"+
		"\u01df\1\0\0\0\u01e1\u01e2\1\0\0\0\u01e2v\1\0\0\0\u01e3\u01e6\7\6\0\0"+
		"\u01e5\u01e3\1\0\0\0\u01e6\u01e9\1\0\0\0\u01e7\u01e5\1\0\0\0\u01e7\u01e8"+
		"\1\0\0\0\u01e8x\1\0\0\0\u01e9\u01e7\1\0\0\0\u01ea\u01f1\2\609\0\u01ec"+
		"\u01f1\2af\0\u01ee\u01f1\2AF\0\u01f0\u01ea\1\0\0\0\u01f0\u01ec\1\0\0\0"+
		"\u01f0\u01ee\1\0\0\0\u01f1z\1\0\0\0\u01f2\u01f3\t\0\0\0\u01f3|\1\0\0\0"+
		"\u01f4\u01f6\3m\64\0\u01f6\u01f7\6<\4\0\u01f7~\1\0\0\0\u01f8\u01fb\b\7"+
		"\0\0\u01fa\u01f8\1\0\0\0\u01fb\u01fc\1\0\0\0\u01fc\u01fa\1\0\0\0\u01fc"+
		"\u01fd\1\0\0\0\u01fd\u01fe\1\0\0\0\u01fe\u01ff\6=\5\0\u01ff\u0080\1\0"+
		"\0\0\u0200\u0201\5*\0\0\u0201\u0202\5/\0\0\u0202\u0203\1\0\0\0\u0203\u0204"+
		"\6>\6\0\u0204\u0082\1\0\0\0\u0205\u0207\5*\0\0\u0207\u0208\6?\7\0\u0208"+
		"\u0084\1\0\0\0\u0209\u020b\t\0\0\0\u020b\u020c\6@\b\0\u020c\u0086\1\0"+
		"\0\0\u020d\u020f\3m\64\0\u020f\u0210\6A\t\0\u0210\u0088\1\0\0\0\u0211"+
		"\u0213\5]\0\0\u0213\u0214\6B\n\0\u0214\u008a\1\0\0\0\u0215\u0218\b\b\0"+
		"\0\u0217\u0215\1\0\0\0\u0218\u0219\1\0\0\0\u0219\u0217\1\0\0\0\u0219\u021a"+
		"\1\0\0\0\u021a\u008c\1\0\0\0\u021b\u021c\3s\67\0\u021c\u008e\1\0\0\0\u021d"+
		"\u021e\3u8\0\u021e\u0090\1\0\0\0\u021f\u023f\5\\\0\0\u0221\u0240\5n\0"+
		"\0\u0223\u0240\5r\0\0\u0225\u0240\5t\0\0\u0227\u0240\5b\0\0\u0229\u0240"+
		"\5f\0\0\u022b\u0240\5\"\0\0\u022d\u0240\5\'\0\0\u022f\u0240\5\\\0\0\u0231"+
		"\u0240\5>\0\0\u0233\u0240\5]\0\0\u0235\u0237\5u\0\0\u0237\u0239\3y:\0"+
		"\u0239\u023b\3y:\0\u023b\u023d\3y:\0\u023d\u023e\3y:\0\u023e\u0240\1\0"+
		"\0\0\u023f\u0221\1\0\0\0\u023f\u0223\1\0\0\0\u023f\u0225\1\0\0\0\u023f"+
		"\u0227\1\0\0\0\u023f\u0229\1\0\0\0\u023f\u022b\1\0\0\0\u023f\u022d\1\0"+
		"\0\0\u023f\u022f\1\0\0\0\u023f\u0231\1\0\0\0\u023f\u0233\1\0\0\0\u023f"+
		"\u0235\1\0\0\0\u0240\u0092\1\0\0\0\u0241\u0242\3i\62\0\u0242\u0094\1\0"+
		"\0\0\u0243\u0245\7\t\0\0\u0245\u0246\6H\13\0\u0246\u0096\1\0\0\0\u0247"+
		"\u0249\t\0\0\0\u0249\u024a\6I\f\0\u024a\u0098\1\0\0\0\u024b\u024d\3m\64"+
		"\0\u024d\u024e\6J\r\0\u024e\u009a\1\0\0\0\u024f\u0251\3k\63\0\u0251\u0252"+
		"\6K\16\0\u0252\u009c\1\0\0\0\u0253\u0255\3[+\0\u0255\u0256\6L\17\0\u0256"+
		"\u009e\1\0\0\0\u0257\u0259\3m\64\0\u0259\u025a\6M\20\0\u025a\u00a0\1\0"+
		"\0\0\u025b\u025c\5/\0\0\u025c\u025d\5/\0\0\u025d\u0262\1\0\0\0\u025e\u0261"+
		"\b\n\0\0\u0260\u025e\1\0\0\0\u0261\u0264\1\0\0\0\u0262\u0260\1\0\0\0\u0262"+
		"\u0263\1\0\0\0\u0263\u00a2\1\0\0\0\u0264\u0262\1\0\0\0\u0265\u0266\5/"+
		"\0\0\u0266\u0267\5*\0\0\u0267\u0268\1\0\0\0\u0268\u0269\6O\21\0\u0269"+
		"\u00a4\1\0\0\0\u026a\u026c\5{\0\0\u026c\u026d\6P\22\0\u026d\u00a6\1\0"+
		"\0\0\u026e\u0270\5}\0\0\u0270\u0271\6Q\23\0\u0271\u00a8\1\0\0\0\u0272"+
		"\u0275\b\13\0\0\u0274\u0272\1\0\0\0\u0275\u0276\1\0\0\0\u0276\u0274\1"+
		"\0\0\0\u0276\u0277\1\0\0\0\u0277\u00aa\1\0\0\0\u0278\u0279\3s\67\0\u0279"+
		"\u00ac\1\0\0\0\u027a\u027b\3u8\0\u027b\u00ae\1\0\0\0\u027c\u029a\5\\\0"+
		"\0\u027e\u029b\5n\0\0\u0280\u029b\5r\0\0\u0282\u029b\5t\0\0\u0284\u029b"+
		"\5b\0\0\u0286\u029b\5f\0\0\u0288\u029b\5\"\0\0\u028a\u029b\5\'\0\0\u028c"+
		"\u029b\5\\\0\0\u028e\u029b\5>\0\0\u0290\u0292\5u\0\0\u0292\u0294\3y:\0"+
		"\u0294\u0296\3y:\0\u0296\u0298\3y:\0\u0298\u0299\3y:\0\u0299\u029b\1\0"+
		"\0\0\u029a\u027e\1\0\0\0\u029a\u0280\1\0\0\0\u029a\u0282\1\0\0\0\u029a"+
		"\u0284\1\0\0\0\u029a\u0286\1\0\0\0\u029a\u0288\1\0\0\0\u029a\u028a\1\0"+
		"\0\0\u029a\u028c\1\0\0\0\u029a\u028e\1\0\0\0\u029a\u0290\1\0\0\0\u029b"+
		"\u00b0\1\0\0\0\u029c\u029d\3i\62\0\u029d\u00b2\1\0\0\0\u029e\u02a0\7\f"+
		"\0\0\u02a0\u02a1\6W\24\0\u02a1\u00b4\1\0\0\0\u02a2\u02a4\t\0\0\0\u02a4"+
		"\u02a5\6X\25\0\u02a5\u00b6\1\0\0\0\31\0\1\1\1\2\1\3\1\4\1\u018b\1\u0195"+
		"\1\u019c\1\u01aa\1\u01ae\1\u01b9\1\u01c9\1\u01cb\1\u01d0\1\u01da\1\u01dc"+
		"\1\u01e1\1\u01e7\1\u01f0\1\u01fc\1\u0219\1\u023f\1\u0262\1\u0276\1\u029a"+
		"\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}