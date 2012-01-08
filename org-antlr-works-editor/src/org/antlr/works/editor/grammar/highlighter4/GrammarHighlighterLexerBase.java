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

@SuppressWarnings({"all", "warnings", "unchecked", "unused"})
public class GrammarHighlighterLexerBase extends Lexer {
	public static final int
		LEXER=3, PARSER=4, CATCH=5, FINALLY=6, GRAMMAR=7, PRIVATE=8, PROTECTED=9, 
		PUBLIC=10, RETURNS=11, THROWS=12, IMPORT=13, FRAGMENT=14, TOKENS=15, OPTIONS=16, 
		MODE=17, LOCALS=18, TREE=19, SCOPE=20, OPEN_ELEMENT_OPTION=21, CLOSE_ELEMENT_OPTION=22, 
		AMPERSAND=23, COMMA=24, QUESTION=25, TREE_BEGIN=26, LPAREN=27, RPAREN=28, 
		COLON=29, STAR=30, PLUS=31, ASSIGN=32, PLUS_ASSIGN=33, IMPLIES=34, REWRITE=35, 
		SEMI=36, ROOT=37, BANG=38, OR=39, WILDCARD=40, ETC=41, RANGE=42, NOT=43, 
		LBRACK=44, RBRACK=45, LCURLY=46, RCURLY=47, DOLLAR=48, LABEL=49, IDENTIFIER=50, 
		INT=51, DIRECTIVE=52, REFERENCE=53, WS=54, NEWLINE=55, COMMENT=56, ML_COMMENT=57, 
		CHAR_LITERAL=58, STRING_LITERAL=59, ANYCHAR=60, BlockComment_NEWLINE=61, 
		CONTINUE_ML_COMMENT=62, END_ML_COMMENT=63, ML_COMMENT_STAR=64, BlockComment_ANYCHAR=65, 
		ArgAction_NEWLINE=66, ArgAction_RBRACK=67, ArgAction_TEXT=68, ArgAction_CHAR_LITERAL=69, 
		ArgAction_STRING_LITERAL=70, ArgAction_ESCAPE=71, ArgAction_REFERENCE=72, 
		ArgAction_SPECIAL=73, ArgAction_ANYCHAR=74, NonActionBrace_NEWLINE=75, 
		NonActionBrace_WS=76, NonActionBrace_LCURLY=77, Action_NEWLINE=78, Action_COMMENT=79, 
		Action_ML_COMMENT=80, Action_LCURLY=81, Action_RCURLY=82, Action_TEXT=83, 
		Action_CHAR_LITERAL=84, Action_STRING_LITERAL=85, Action_ESCAPE=86, Action_REFERENCE=87, 
		Action_SPECIAL=88, Action_ANYCHAR=89;
	// Lexer modes
	public static final int BlockComment = 1;
	public static final int ArgAction = 2;
	public static final int NonActionBrace = 3;
	public static final int Action = 4;

	public static final String[] tokenNames = {
		"<INVALID>", "<INVALID>", "<INVALID>",
		"lexer", "parser", "catch", "finally", "grammar", "private", "protected", 
		"public", "returns", "throws", "import", "fragment", "TOKENS", "OPTIONS", 
		"mode", "locals", "tree", "scope", "<", ">", "@", ",", "?", "^(", "(", 
		")", ":", "STAR", "+", "=", "+=", "=>", "->", ";", "^", "!", "|", ".", 
		"...", "..", "~", "[", "RBRACK", "LCURLY", "RCURLY", "$", "LABEL", "IDENTIFIER", 
		"INT", "DIRECTIVE", "REFERENCE", "WS", "NEWLINE", "COMMENT", "ML_COMMENT", 
		"CHAR_LITERAL", "STRING_LITERAL", "ANYCHAR", "BlockComment_NEWLINE", "CONTINUE_ML_COMMENT", 
		"*/", "*", "BlockComment_ANYCHAR", "ArgAction_NEWLINE", "]", "ArgAction_TEXT", 
		"ArgAction_CHAR_LITERAL", "ArgAction_STRING_LITERAL", "ArgAction_ESCAPE", 
		"ArgAction_REFERENCE", "ArgAction_SPECIAL", "ArgAction_ANYCHAR", "NonActionBrace_NEWLINE", 
		"NonActionBrace_WS", "NonActionBrace_LCURLY", "Action_NEWLINE", "Action_COMMENT", 
		"/*", "{", "}", "Action_TEXT", "Action_CHAR_LITERAL", "Action_STRING_LITERAL", 
		"Action_ESCAPE", "Action_REFERENCE", "Action_SPECIAL", "Action_ANYCHAR"
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
	public ATN getATN() { return _ATN; }


	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch ( ruleIndex ) {
			case 12 : TOKENS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 13 : OPTIONS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 43 : LCURLY_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 54 : ML_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 60 : BlockComment_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 61 : CONTINUE_ML_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 62 : END_ML_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 63 : ML_COMMENT_STAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 64 : BlockComment_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 65 : ArgAction_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 66 : ArgAction_RBRACK_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 72 : ArgAction_SPECIAL_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 73 : ArgAction_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 74 : NonActionBrace_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 75 : NonActionBrace_WS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 76 : NonActionBrace_LCURLY_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 77 : Action_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 79 : Action_ML_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 80 : Action_LCURLY_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 81 : Action_RCURLY_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 87 : Action_SPECIAL_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 88 : Action_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;
		}
	}
	public void ML_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 3 : pushMode(BlockComment); break;
		}
	}
	public void END_ML_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 6 : type =  getMultilineCommentType(); popMode(); break;
		}
	}
	public void ArgAction_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 9 : type =  NEWLINE; break;
		}
	}
	public void ArgAction_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 12 : type =  ANYCHAR; break;
		}
	}
	public void BlockComment_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 8 : type =  ANYCHAR; break;
		}
	}
	public void NonActionBrace_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 13 : type =  NEWLINE; break;
		}
	}
	public void Action_RCURLY_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 19 : popMode(); type =  Action_TEXT; break;
		}
	}
	public void NonActionBrace_WS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 14 : type =  WS; break;
		}
	}
	public void CONTINUE_ML_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 5 : type =  getMultilineCommentType(); break;
		}
	}
	public void ArgAction_RBRACK_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 10 : type =  RBRACK; popMode(); break;
		}
	}
	public void TOKENS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 0 : pushMode(NonActionBrace); break;
		}
	}
	public void LCURLY_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 2 : type = Action_TEXT; pushMode(Action); break;
		}
	}
	public void Action_ML_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 17 : pushMode(BlockComment); break;
		}
	}
	public void Action_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 21 : type =  ANYCHAR; break;
		}
	}
	public void Action_LCURLY_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 18 : pushMode(Action); type =  Action_TEXT; break;
		}
	}
	public void Action_SPECIAL_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 20 : type =  Action_TEXT; break;
		}
	}
	public void Action_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 16 : type =  NEWLINE; break;
		}
	}
	public void ML_COMMENT_STAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 7 : type =  getMultilineCommentType(); break;
		}
	}
	public void NonActionBrace_LCURLY_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 15 : type =  LCURLY; popMode(); break;
		}
	}
	public void OPTIONS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 1 : pushMode(NonActionBrace); break;
		}
	}
	public void ArgAction_SPECIAL_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 11 : type =  Action_TEXT; break;
		}
	}
	public void BlockComment_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 4 : type =  NEWLINE; break;
		}
	}

	public static final String _serializedATN =
		"\2Y\u028c\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\2\0\7\0\2\1\7\1\2\2"+
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
		"\1\f\1\f\1\f\0\1\f\0\1\f\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\0\1\r\0\1"+
		"\r\1\16\1\16\1\16\1\16\1\16\1\17\1\17\1\17\1\17\1\17\1\17\1\17\1\20\1"+
		"\20\1\20\1\20\1\20\1\21\1\21\1\21\1\21\1\21\1\21\1\22\1\22\1\23\1\23\1"+
		"\24\1\24\1\25\1\25\1\26\1\26\1\27\1\27\1\27\1\30\1\30\1\31\1\31\1\32\1"+
		"\32\1\33\1\33\1\34\1\34\1\35\1\35\1\36\1\36\1\36\1\37\1\37\1\37\1 \1 "+
		"\1 \1!\1!\1\"\1\"\1#\1#\1$\1$\1%\1%\1&\1&\1&\1&\1\'\1\'\1\'\1(\1(\1)\1"+
		")\1*\1*\1+\0\1+\1,\1,\1-\1-\1.\0\1.\0\1.\1.\1/\0\1/\0\5/\b/\n/\f/\u0190"+
		"\t/\1\60\0\4\60\b\60\13\60\f\60\u0195\1\61\0\1\61\1\61\1\62\0\1\62\1\62"+
		"\1\63\0\4\63\b\63\13\63\f\63\u01a3\1\64\0\3\64\b\64\1\64\1\64\1\65\1\65"+
		"\1\65\1\65\0\5\65\b\65\n\65\f\65\u01b4\t\65\1\66\1\66\1\66\1\66\1\67\0"+
		"\1\67\0\1\67\0\1\67\0\5\67\b\67\n\67\f\67\u01c5\t\67\1\67\0\3\67\b\67"+
		"\18\0\18\0\18\0\18\0\58\b8\n8\f8\u01d6\t8\18\0\38\b8\19\0\59\b9\n9\f9"+
		"\u01e1\t9\1:\0\1:\0\1:\0\3:\b:\1;\1;\1<\0\1<\1=\0\4=\b=\13=\f=\u01f3\1"+
		"=\1>\1>\1>\1>\1?\0\1?\1@\0\1@\1A\0\1A\1B\0\1B\1C\0\4C\bC\13C\fC\u020a"+
		"\1D\1D\1E\1E\1F\0\1F\0\1F\0\1F\0\1F\0\1F\0\1F\0\1F\0\1F\0\1F\0\1F\0\1"+
		"F\0\1F\0\1F\0\1F\0\1F\1F\3F\bF\1G\1G\1H\0\1H\1I\0\1I\1J\0\1J\1K\0\1K\1"+
		"L\0\1L\1M\0\1M\1N\1N\1N\1N\0\5N\bN\nN\fN\u024f\tN\1O\1O\1O\1O\1P\0\1P"+
		"\1Q\0\1Q\1R\0\4R\bR\13R\fR\u025e\1S\1S\1T\1T\1U\0\1U\0\1U\0\1U\0\1U\0"+
		"\1U\0\1U\0\1U\0\1U\0\1U\0\1U\0\1U\0\1U\0\1U\0\1U\1U\3U\bU\1V\1V\1W\0\1"+
		"W\1X\0\1XY\5\3\uffff\7\4\uffff\t\5\uffff\13\6\uffff\r\7\uffff\17\b\uffff"+
		"\21\t\uffff\23\n\uffff\25\13\uffff\27\f\uffff\31\r\uffff\33\16\uffff\35"+
		"\17\0\37\20\1!\21\uffff#\22\uffff%\23\uffff\'\24\uffff)\25\uffff+\26\uffff"+
		"-\27\uffff/\30\uffff\61\31\uffff\63\32\uffff\65\33\uffff\67\34\uffff9"+
		"\35\uffff;\36\uffff=\37\uffff? \uffffA!\uffffC\"\uffffE#\uffffG$\uffff"+
		"I%\uffffK&\uffffM\'\uffffO(\uffffQ)\uffffS*\uffffU+\uffffW,\uffffY-\uffff"+
		"[.\2]/\uffff_\60\uffffa\61\uffffc\62\uffffe\63\uffffg\64\uffffi\65\uffff"+
		"k\66\uffffm\67\uffffo8\uffffq9\3s:\uffffu;\uffffw\0\uffffy\0\uffff{<\uffff"+
		"}=\4\177>\5\u0081?\6\u0083@\7\u0085A\b\u0087B\t\u0089C\n\u008bD\uffff"+
		"\u008dE\uffff\u008fF\uffff\u0091G\uffff\u0093H\uffff\u0095I\13\u0097J"+
		"\f\u0099K\r\u009bL\16\u009dM\17\u009fN\20\u00a1O\uffff\u00a3P\21\u00a5"+
		"Q\22\u00a7R\23\u00a9S\uffff\u00abT\uffff\u00adU\uffff\u00afV\uffff\u00b1"+
		"W\uffff\u00b3X\24\u00b5Y\25\5\0\1\2\3\4\r\3AZ__az\4\609AZ__az\2\t\t  "+
		"\2\n\n\r\r\4\n\n\r\r\'\'\\\\\4\n\n\r\r\"\"\\\\\3\t\n\r\r  \3\n\n\r\r*"+
		"*\t\n\n\r\r\"\"$$\'\'//\\\\{{}}\3$$//\\\\\2\n\n\r\r\t\n\n\r\r\"\"$$\'"+
		"\'//\\\\{{}}\3$$//\\\\\u025d\0\5\1\0\0\0\0\7\1\0\0\0\0\t\1\0\0\0\0\13"+
		"\1\0\0\0\0\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1\0\0\0\0\25\1\0\0"+
		"\0\0\27\1\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0\0\0\37\1\0\0\0\0"+
		"!\1\0\0\0\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0\0\0+\1\0\0\0\0-\1"+
		"\0\0\0\0/\1\0\0\0\0\61\1\0\0\0\0\63\1\0\0\0\0\65\1\0\0\0\0\67\1\0\0\0"+
		"\09\1\0\0\0\0;\1\0\0\0\0=\1\0\0\0\0?\1\0\0\0\0A\1\0\0\0\0C\1\0\0\0\0E"+
		"\1\0\0\0\0G\1\0\0\0\0I\1\0\0\0\0K\1\0\0\0\0M\1\0\0\0\0O\1\0\0\0\0Q\1\0"+
		"\0\0\0S\1\0\0\0\0U\1\0\0\0\0W\1\0\0\0\0Y\1\0\0\0\0[\1\0\0\0\0]\1\0\0\0"+
		"\0_\1\0\0\0\0a\1\0\0\0\0c\1\0\0\0\0e\1\0\0\0\0g\1\0\0\0\0i\1\0\0\0\0k"+
		"\1\0\0\0\0m\1\0\0\0\0o\1\0\0\0\0q\1\0\0\0\0s\1\0\0\0\0u\1\0\0\0\0{\1\0"+
		"\0\0\1}\1\0\0\0\1\177\1\0\0\0\1\u0081\1\0\0\0\1\u0083\1\0\0\0\1\u0085"+
		"\1\0\0\0\2\u0087\1\0\0\0\2\u0089\1\0\0\0\2\u008b\1\0\0\0\2\u008d\1\0\0"+
		"\0\2\u008f\1\0\0\0\2\u0091\1\0\0\0\2\u0093\1\0\0\0\2\u0095\1\0\0\0\2\u0097"+
		"\1\0\0\0\3\u0099\1\0\0\0\3\u009b\1\0\0\0\3\u009d\1\0\0\0\4\u009f\1\0\0"+
		"\0\4\u00a1\1\0\0\0\4\u00a3\1\0\0\0\4\u00a5\1\0\0\0\4\u00a7\1\0\0\0\4\u00a9"+
		"\1\0\0\0\4\u00ab\1\0\0\0\4\u00ad\1\0\0\0\4\u00af\1\0\0\0\4\u00b1\1\0\0"+
		"\0\4\u00b3\1\0\0\0\4\u00b5\1\0\0\0\5\u00b7\1\0\0\0\7\u00bd\1\0\0\0\t\u00c4"+
		"\1\0\0\0\13\u00ca\1\0\0\0\r\u00d2\1\0\0\0\17\u00da\1\0\0\0\21\u00e2\1"+
		"\0\0\0\23\u00ec\1\0\0\0\25\u00f3\1\0\0\0\27\u00fb\1\0\0\0\31\u0102\1\0"+
		"\0\0\33\u0109\1\0\0\0\35\u0112\1\0\0\0\37\u011e\1\0\0\0!\u012b\1\0\0\0"+
		"#\u0130\1\0\0\0%\u0137\1\0\0\0\'\u013c\1\0\0\0)\u0142\1\0\0\0+\u0144\1"+
		"\0\0\0-\u0146\1\0\0\0/\u0148\1\0\0\0\61\u014a\1\0\0\0\63\u014c\1\0\0\0"+
		"\65\u014f\1\0\0\0\67\u0151\1\0\0\09\u0153\1\0\0\0;\u0155\1\0\0\0=\u0157"+
		"\1\0\0\0?\u0159\1\0\0\0A\u015b\1\0\0\0C\u015e\1\0\0\0E\u0161\1\0\0\0G"+
		"\u0164\1\0\0\0I\u0166\1\0\0\0K\u0168\1\0\0\0M\u016a\1\0\0\0O\u016c\1\0"+
		"\0\0Q\u016e\1\0\0\0S\u0172\1\0\0\0U\u0175\1\0\0\0W\u0177\1\0\0\0Y\u0179"+
		"\1\0\0\0[\u017b\1\0\0\0]\u017e\1\0\0\0_\u0180\1\0\0\0a\u0182\1\0\0\0c"+
		"\u0188\1\0\0\0e\u0193\1\0\0\0g\u0197\1\0\0\0i\u019b\1\0\0\0k\u01a1\1\0"+
		"\0\0m\u01a7\1\0\0\0o\u01ab\1\0\0\0q\u01b5\1\0\0\0s\u01b9\1\0\0\0u\u01ca"+
		"\1\0\0\0w\u01df\1\0\0\0y\u01e8\1\0\0\0{\u01ea\1\0\0\0}\u01ec\1\0\0\0\177"+
		"\u01f1\1\0\0\0\u0081\u01f6\1\0\0\0\u0083\u01fa\1\0\0\0\u0085\u01fd\1\0"+
		"\0\0\u0087\u0200\1\0\0\0\u0089\u0203\1\0\0\0\u008b\u0208\1\0\0\0\u008d"+
		"\u020c\1\0\0\0\u008f\u020e\1\0\0\0\u0091\u0210\1\0\0\0\u0093\u0232\1\0"+
		"\0\0\u0095\u0234\1\0\0\0\u0097\u0237\1\0\0\0\u0099\u023a\1\0\0\0\u009b"+
		"\u023d\1\0\0\0\u009d\u0240\1\0\0\0\u009f\u0243\1\0\0\0\u00a1\u0246\1\0"+
		"\0\0\u00a3\u0250\1\0\0\0\u00a5\u0254\1\0\0\0\u00a7\u0257\1\0\0\0\u00a9"+
		"\u025c\1\0\0\0\u00ab\u0260\1\0\0\0\u00ad\u0262\1\0\0\0\u00af\u0264\1\0"+
		"\0\0\u00b1\u0284\1\0\0\0\u00b3\u0286\1\0\0\0\u00b5\u0289\1\0\0\0\u00b7"+
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
		"\5{\0\0\u011d\36\1\0\0\0\u011e\u011f\5o\0\0\u011f\u0120\5p\0\0\u0120\u0121"+
		"\5t\0\0\u0121\u0122\5i\0\0\u0122\u0123\5o\0\0\u0123\u0124\5n\0\0\u0124"+
		"\u0125\5s\0\0\u0125\u0126\1\0\0\0\u0126\u0128\3w9\0\u0128\u012a\5{\0\0"+
		"\u012a \1\0\0\0\u012b\u012c\5m\0\0\u012c\u012d\5o\0\0\u012d\u012e\5d\0"+
		"\0\u012e\u012f\5e\0\0\u012f\"\1\0\0\0\u0130\u0131\5l\0\0\u0131\u0132\5"+
		"o\0\0\u0132\u0133\5c\0\0\u0133\u0134\5a\0\0\u0134\u0135\5l\0\0\u0135\u0136"+
		"\5s\0\0\u0136$\1\0\0\0\u0137\u0138\5t\0\0\u0138\u0139\5r\0\0\u0139\u013a"+
		"\5e\0\0\u013a\u013b\5e\0\0\u013b&\1\0\0\0\u013c\u013d\5s\0\0\u013d\u013e"+
		"\5c\0\0\u013e\u013f\5o\0\0\u013f\u0140\5p\0\0\u0140\u0141\5e\0\0\u0141"+
		"(\1\0\0\0\u0142\u0143\5<\0\0\u0143*\1\0\0\0\u0144\u0145\5>\0\0\u0145,"+
		"\1\0\0\0\u0146\u0147\5@\0\0\u0147.\1\0\0\0\u0148\u0149\5,\0\0\u0149\60"+
		"\1\0\0\0\u014a\u014b\5?\0\0\u014b\62\1\0\0\0\u014c\u014d\5^\0\0\u014d"+
		"\u014e\5(\0\0\u014e\64\1\0\0\0\u014f\u0150\5(\0\0\u0150\66\1\0\0\0\u0151"+
		"\u0152\5)\0\0\u01528\1\0\0\0\u0153\u0154\5:\0\0\u0154:\1\0\0\0\u0155\u0156"+
		"\5*\0\0\u0156<\1\0\0\0\u0157\u0158\5+\0\0\u0158>\1\0\0\0\u0159\u015a\5"+
		"=\0\0\u015a@\1\0\0\0\u015b\u015c\5+\0\0\u015c\u015d\5=\0\0\u015dB\1\0"+
		"\0\0\u015e\u015f\5=\0\0\u015f\u0160\5>\0\0\u0160D\1\0\0\0\u0161\u0162"+
		"\5-\0\0\u0162\u0163\5>\0\0\u0163F\1\0\0\0\u0164\u0165\5;\0\0\u0165H\1"+
		"\0\0\0\u0166\u0167\5^\0\0\u0167J\1\0\0\0\u0168\u0169\5!\0\0\u0169L\1\0"+
		"\0\0\u016a\u016b\5|\0\0\u016bN\1\0\0\0\u016c\u016d\5.\0\0\u016dP\1\0\0"+
		"\0\u016e\u016f\5.\0\0\u016f\u0170\5.\0\0\u0170\u0171\5.\0\0\u0171R\1\0"+
		"\0\0\u0172\u0173\5.\0\0\u0173\u0174\5.\0\0\u0174T\1\0\0\0\u0175\u0176"+
		"\5~\0\0\u0176V\1\0\0\0\u0177\u0178\5[\0\0\u0178X\1\0\0\0\u0179\u017a\5"+
		"]\0\0\u017aZ\1\0\0\0\u017b\u017d\5{\0\0\u017d\\\1\0\0\0\u017e\u017f\5"+
		"}\0\0\u017f^\1\0\0\0\u0180\u0181\5$\0\0\u0181`\1\0\0\0\u0182\u0184\3c"+
		"/\0\u0184\u0186\3w9\0\u0186\u0187\5=\0\0\u0187b\1\0\0\0\u0188\u018e\7"+
		"\0\0\0\u018a\u018d\7\1\0\0\u018c\u018a\1\0\0\0\u018d\u0190\1\0\0\0\u018e"+
		"\u018c\1\0\0\0\u018e\u018f\1\0\0\0\u018fd\1\0\0\0\u0190\u018e\1\0\0\0"+
		"\u0191\u0194\2\609\0\u0193\u0191\1\0\0\0\u0194\u0195\1\0\0\0\u0195\u0193"+
		"\1\0\0\0\u0195\u0196\1\0\0\0\u0196f\1\0\0\0\u0197\u0199\5@\0\0\u0199\u019a"+
		"\3c/\0\u019ah\1\0\0\0\u019b\u019d\5$\0\0\u019d\u019e\3c/\0\u019ej\1\0"+
		"\0\0\u019f\u01a2\7\2\0\0\u01a1\u019f\1\0\0\0\u01a2\u01a3\1\0\0\0\u01a3"+
		"\u01a1\1\0\0\0\u01a3\u01a4\1\0\0\0\u01a4l\1\0\0\0\u01a5\u01a8\5\r\0\0"+
		"\u01a7\u01a5\1\0\0\0\u01a7\u01a8\1\0\0\0\u01a8\u01a9\1\0\0\0\u01a9\u01aa"+
		"\5\n\0\0\u01aan\1\0\0\0\u01ab\u01ac\5/\0\0\u01ac\u01ad\5/\0\0\u01ad\u01b2"+
		"\1\0\0\0\u01ae\u01b1\b\3\0\0\u01b0\u01ae\1\0\0\0\u01b1\u01b4\1\0\0\0\u01b2"+
		"\u01b0\1\0\0\0\u01b2\u01b3\1\0\0\0\u01b3p\1\0\0\0\u01b4\u01b2\1\0\0\0"+
		"\u01b5\u01b6\5/\0\0\u01b6\u01b7\5*\0\0\u01b7\u01b8\1\0\0\0\u01b8r\1\0"+
		"\0\0\u01b9\u01c3\5\'\0\0\u01bb\u01bd\5\\\0\0\u01bd\u01c2\t\0\0\0\u01bf"+
		"\u01c2\b\4\0\0\u01c1\u01bb\1\0\0\0\u01c1\u01bf\1\0\0\0\u01c2\u01c5\1\0"+
		"\0\0\u01c3\u01c1\1\0\0\0\u01c3\u01c4\1\0\0\0\u01c4\u01c8\1\0\0\0\u01c5"+
		"\u01c3\1\0\0\0\u01c6\u01c9\5\'\0\0\u01c8\u01c6\1\0\0\0\u01c8\u01c9\1\0"+
		"\0\0\u01c9t\1\0\0\0\u01ca\u01d4\5\"\0\0\u01cc\u01ce\5\\\0\0\u01ce\u01d3"+
		"\t\0\0\0\u01d0\u01d3\b\5\0\0\u01d2\u01cc\1\0\0\0\u01d2\u01d0\1\0\0\0\u01d3"+
		"\u01d6\1\0\0\0\u01d4\u01d2\1\0\0\0\u01d4\u01d5\1\0\0\0\u01d5\u01d9\1\0"+
		"\0\0\u01d6\u01d4\1\0\0\0\u01d7\u01da\5\"\0\0\u01d9\u01d7\1\0\0\0\u01d9"+
		"\u01da\1\0\0\0\u01dav\1\0\0\0\u01db\u01de\7\6\0\0\u01dd\u01db\1\0\0\0"+
		"\u01de\u01e1\1\0\0\0\u01df\u01dd\1\0\0\0\u01df\u01e0\1\0\0\0\u01e0x\1"+
		"\0\0\0\u01e1\u01df\1\0\0\0\u01e2\u01e9\2\609\0\u01e4\u01e9\2af\0\u01e6"+
		"\u01e9\2AF\0\u01e8\u01e2\1\0\0\0\u01e8\u01e4\1\0\0\0\u01e8\u01e6\1\0\0"+
		"\0\u01e9z\1\0\0\0\u01ea\u01eb\t\0\0\0\u01eb|\1\0\0\0\u01ec\u01ee\3m\64"+
		"\0\u01ee~\1\0\0\0\u01ef\u01f2\b\7\0\0\u01f1\u01ef\1\0\0\0\u01f2\u01f3"+
		"\1\0\0\0\u01f3\u01f1\1\0\0\0\u01f3\u01f4\1\0\0\0\u01f4\u01f5\1\0\0\0\u01f5"+
		"\u0080\1\0\0\0\u01f6\u01f7\5*\0\0\u01f7\u01f8\5/\0\0\u01f8\u01f9\1\0\0"+
		"\0\u01f9\u0082\1\0\0\0\u01fa\u01fc\5*\0\0\u01fc\u0084\1\0\0\0\u01fd\u01ff"+
		"\t\0\0\0\u01ff\u0086\1\0\0\0\u0200\u0202\3m\64\0\u0202\u0088\1\0\0\0\u0203"+
		"\u0205\5]\0\0\u0205\u008a\1\0\0\0\u0206\u0209\b\b\0\0\u0208\u0206\1\0"+
		"\0\0\u0209\u020a\1\0\0\0\u020a\u0208\1\0\0\0\u020a\u020b\1\0\0\0\u020b"+
		"\u008c\1\0\0\0\u020c\u020d\3s\67\0\u020d\u008e\1\0\0\0\u020e\u020f\3u"+
		"8\0\u020f\u0090\1\0\0\0\u0210\u0230\5\\\0\0\u0212\u0231\5n\0\0\u0214\u0231"+
		"\5r\0\0\u0216\u0231\5t\0\0\u0218\u0231\5b\0\0\u021a\u0231\5f\0\0\u021c"+
		"\u0231\5\"\0\0\u021e\u0231\5\'\0\0\u0220\u0231\5\\\0\0\u0222\u0231\5>"+
		"\0\0\u0224\u0231\5]\0\0\u0226\u0228\5u\0\0\u0228\u022a\3y:\0\u022a\u022c"+
		"\3y:\0\u022c\u022e\3y:\0\u022e\u022f\3y:\0\u022f\u0231\1\0\0\0\u0230\u0212"+
		"\1\0\0\0\u0230\u0214\1\0\0\0\u0230\u0216\1\0\0\0\u0230\u0218\1\0\0\0\u0230"+
		"\u021a\1\0\0\0\u0230\u021c\1\0\0\0\u0230\u021e\1\0\0\0\u0230\u0220\1\0"+
		"\0\0\u0230\u0222\1\0\0\0\u0230\u0224\1\0\0\0\u0230\u0226\1\0\0\0\u0231"+
		"\u0092\1\0\0\0\u0232\u0233\3i\62\0\u0233\u0094\1\0\0\0\u0234\u0236\7\t"+
		"\0\0\u0236\u0096\1\0\0\0\u0237\u0239\t\0\0\0\u0239\u0098\1\0\0\0\u023a"+
		"\u023c\3m\64\0\u023c\u009a\1\0\0\0\u023d\u023f\3k\63\0\u023f\u009c\1\0"+
		"\0\0\u0240\u0242\3[+\0\u0242\u009e\1\0\0\0\u0243\u0245\3m\64\0\u0245\u00a0"+
		"\1\0\0\0\u0246\u0247\5/\0\0\u0247\u0248\5/\0\0\u0248\u024d\1\0\0\0\u0249"+
		"\u024c\b\n\0\0\u024b\u0249\1\0\0\0\u024c\u024f\1\0\0\0\u024d\u024b\1\0"+
		"\0\0\u024d\u024e\1\0\0\0\u024e\u00a2\1\0\0\0\u024f\u024d\1\0\0\0\u0250"+
		"\u0251\5/\0\0\u0251\u0252\5*\0\0\u0252\u0253\1\0\0\0\u0253\u00a4\1\0\0"+
		"\0\u0254\u0256\5{\0\0\u0256\u00a6\1\0\0\0\u0257\u0259\5}\0\0\u0259\u00a8"+
		"\1\0\0\0\u025a\u025d\b\13\0\0\u025c\u025a\1\0\0\0\u025d\u025e\1\0\0\0"+
		"\u025e\u025c\1\0\0\0\u025e\u025f\1\0\0\0\u025f\u00aa\1\0\0\0\u0260\u0261"+
		"\3s\67\0\u0261\u00ac\1\0\0\0\u0262\u0263\3u8\0\u0263\u00ae\1\0\0\0\u0264"+
		"\u0282\5\\\0\0\u0266\u0283\5n\0\0\u0268\u0283\5r\0\0\u026a\u0283\5t\0"+
		"\0\u026c\u0283\5b\0\0\u026e\u0283\5f\0\0\u0270\u0283\5\"\0\0\u0272\u0283"+
		"\5\'\0\0\u0274\u0283\5\\\0\0\u0276\u0283\5>\0\0\u0278\u027a\5u\0\0\u027a"+
		"\u027c\3y:\0\u027c\u027e\3y:\0\u027e\u0280\3y:\0\u0280\u0281\3y:\0\u0281"+
		"\u0283\1\0\0\0\u0282\u0266\1\0\0\0\u0282\u0268\1\0\0\0\u0282\u026a\1\0"+
		"\0\0\u0282\u026c\1\0\0\0\u0282\u026e\1\0\0\0\u0282\u0270\1\0\0\0\u0282"+
		"\u0272\1\0\0\0\u0282\u0274\1\0\0\0\u0282\u0276\1\0\0\0\u0282\u0278\1\0"+
		"\0\0\u0283\u00b0\1\0\0\0\u0284\u0285\3i\62\0\u0285\u00b2\1\0\0\0\u0286"+
		"\u0288\7\f\0\0\u0288\u00b4\1\0\0\0\u0289\u028b\t\0\0\0\u028b\u00b6\1\0"+
		"\0\0\30\0\1\1\1\2\1\3\1\4\1\u018e\1\u0195\1\u01a3\1\u01a7\1\u01b2\1\u01c1"+
		"\1\u01c3\1\u01c8\1\u01d2\1\u01d4\1\u01d9\1\u01df\1\u01e8\1\u01f3\1\u020a"+
		"\1\u0230\1\u024d\1\u025e\1\u0282\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}