// $ANTLR ANTLRVersion> TemplateLexerBase.java generatedTimestamp>

/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
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
package org.antlr.works.editor.st4.experimental;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused"})
public class TemplateLexerBase extends Lexer {
	public static final int
		OPEN_DELIMITER=3, CLOSE_DELIMITER=4, QUOTE=5, LPAREN=6, RPAREN=7, COMMA=8, 
		DOT=9, COLON=10, DEFINED=11, EQUALS=12, AT=13, LBRACK=14, RBRACK=15, LBRACE=16, 
		RBRACE=17, GROUP=18, DEFAULT=19, IMPORT=20, TRUE=21, FALSE=22, DELIMITERS=23, 
		WS=24, NEWLINE=25, ID=26, STRING=27, BIGSTRING=28, BIGSTRINGLINE=29, LINE_COMMENT=30, 
		COMMENT=31, ANYCHAR=32, BlockComment_NEWLINE=33, CONTINUE_COMMENT=34, 
		END_COMMENT=35, BLOCK_COMMENT_STAR=36, BlockComment_ANYCHAR=37, TemplateComment_NEWLINE=38, 
		TemplateComment_CONTINUE_COMMENT=39, TemplateComment_END_COMMENT=40, TemplateComment_BLOCK_COMMENT_BANG=41, 
		TemplateComment_ANYCHAR=42, AnonymousTemplate_RBRACE=43, AnonymousTemplate_NEWLINE=44, 
		AnonymousTemplate_COMMENT=45, AnonymousTemplate_OPEN_DELIMITER=46, TEXT=47, 
		ESCAPE_RBRACE=48, AnonymousTemplate_ANYCHAR=49, AnonymousTemplateParameters_WS=50, 
		AnonymousTemplateParameters_NEWLINE=51, AnonymousTemplateParameters_COMMA=52, 
		TEMPLATE_PARAMETER=53, PIPE=54, AnonymousTemplateParameters_ANYCHAR=55, 
		BigStringTemplate_NEWLINE=56, BigStringTemplate_COMMENT=57, BigStringTemplate_OPEN_DELIMITER=58, 
		BigStringTemplate_TEXT=59, BigStringTemplate_ESCAPE=60, BigStringTemplate_END=61, 
		BigStringTemplate_LANGLE=62, BigStringTemplate_ANYCHAR=63, BigStringLineTemplate_NEWLINE=64, 
		BigStringLineTemplate_COMMENT=65, BigStringLineTemplate_OPEN_DELIMITER=66, 
		BigStringLineTemplate_TEXT=67, BigStringLineTemplate_ESCAPE=68, BigStringLineTemplate_END=69, 
		BigStringLineTemplate_PERCENT=70, BigStringLineTemplate_ANYCHAR=71, TemplateExpression_NEWLINE=72, 
		TemplateExpression_DOT=73, TemplateExpression_COMMA=74, TemplateExpression_COLON=75, 
		TemplateExpression_LPAREN=76, TemplateExpression_RPAREN=77, TemplateExpression_LBRACK=78, 
		TemplateExpression_RBRACK=79, TemplateExpression_EQUALS=80, TemplateExpression_LBRACE=81, 
		TemplateExpression_WS=82, SUPER=83, IF=84, ELSEIF=85, ENDIF=86, ELSE=87, 
		END=88, ELLIPSIS=89, NOT=90, OR=91, AND=92, SEMI=93, ESCAPE=94, REGION_END=95, 
		REGION_ID=96, TemplateExpression_ID=97, TemplateExpression_CLOSE_DELIMITER=98, 
		TemplateExpression_STRING=99, TemplateExpression_ANYCHAR=100, StringTemplate_NEWLINE=101, 
		StringTemplate_COMMENT=102, StringTemplate_OPEN_DELIMITER=103, StringTemplate_TEXT=104, 
		StringTemplate_ESCAPE=105, StringTemplate_END=106, StringTemplate_ANYCHAR=107, 
		DelimitersOpenSpec_WS=108, DelimitersOpenSpec_NEWLINE=109, DelimitersOpenSpec_DELIMITER_STRING=110, 
		DelimitersOpenSpec_COMMA=111, DelimitersCloseSpec_WS=112, DelimitersCloseSpec_NEWLINE=113, 
		DelimitersCloseSpec_DELIMITER_STRING=114;
	// Lexer modes
	public static final int BlockComment = 1;
	public static final int TemplateComment = 2;
	public static final int AnonymousTemplate = 3;
	public static final int AnonymousTemplateParameters = 4;
	public static final int BigStringTemplate = 5;
	public static final int BigStringLineTemplate = 6;
	public static final int TemplateExpression = 7;
	public static final int StringTemplate = 8;
	public static final int DelimitersOpenSpec = 9;
	public static final int DelimitersCloseSpec = 10;

	public static final String[] tokenNames = {
		"<INVALID>", "<INVALID>", "<INVALID>",
		"OPEN_DELIMITER", "CLOSE_DELIMITER", "QUOTE", "(", ")", ",", ".", ":", 
		"::=", "=", "@", "[", "]", "LBRACE", "}", "group", "default", "import", 
		"true", "false", "DELIMITERS", "WS", "NEWLINE", "ID", "STRING", "BIGSTRING", 
		"BIGSTRINGLINE", "LINE_COMMENT", "COMMENT", "ANYCHAR", "BlockComment_NEWLINE", 
		"CONTINUE_COMMENT", "END_COMMENT", "BLOCK_COMMENT_STAR", "BlockComment_ANYCHAR", 
		"TemplateComment_NEWLINE", "TemplateComment_CONTINUE_COMMENT", "TemplateComment_END_COMMENT", 
		"TemplateComment_BLOCK_COMMENT_BANG", "TemplateComment_ANYCHAR", "AnonymousTemplate_RBRACE", 
		"AnonymousTemplate_NEWLINE", "AnonymousTemplate_COMMENT", "AnonymousTemplate_OPEN_DELIMITER", 
		"TEXT", "ESCAPE_RBRACE", "AnonymousTemplate_ANYCHAR", "AnonymousTemplateParameters_WS", 
		"AnonymousTemplateParameters_NEWLINE", "AnonymousTemplateParameters_COMMA", 
		"TEMPLATE_PARAMETER", "PIPE", "AnonymousTemplateParameters_ANYCHAR", "BigStringTemplate_NEWLINE", 
		"BigStringTemplate_COMMENT", "BigStringTemplate_OPEN_DELIMITER", "BigStringTemplate_TEXT", 
		"BigStringTemplate_ESCAPE", "BigStringTemplate_END", "BigStringTemplate_LANGLE", 
		"BigStringTemplate_ANYCHAR", "BigStringLineTemplate_NEWLINE", "BigStringLineTemplate_COMMENT", 
		"BigStringLineTemplate_OPEN_DELIMITER", "BigStringLineTemplate_TEXT", 
		"BigStringLineTemplate_ESCAPE", "BigStringLineTemplate_END", "BigStringLineTemplate_PERCENT", 
		"BigStringLineTemplate_ANYCHAR", "TemplateExpression_NEWLINE", "TemplateExpression_DOT", 
		"TemplateExpression_COMMA", "TemplateExpression_COLON", "TemplateExpression_LPAREN", 
		"TemplateExpression_RPAREN", "TemplateExpression_LBRACK", "TemplateExpression_RBRACK", 
		"TemplateExpression_EQUALS", "TemplateExpression_LBRACE", "TemplateExpression_WS", 
		"SUPER", "IF", "ELSEIF", "ENDIF", "ELSE", "END", "ELLIPSIS", "NOT", "OR", 
		"AND", "SEMI", "ESCAPE", "REGION_END", "REGION_ID", "TemplateExpression_ID", 
		"TemplateExpression_CLOSE_DELIMITER", "TemplateExpression_STRING", "TemplateExpression_ANYCHAR", 
		"StringTemplate_NEWLINE", "StringTemplate_COMMENT", "StringTemplate_OPEN_DELIMITER", 
		"StringTemplate_TEXT", "StringTemplate_ESCAPE", "StringTemplate_END", 
		"StringTemplate_ANYCHAR", "DelimitersOpenSpec_WS", "DelimitersOpenSpec_NEWLINE", 
		"DelimitersOpenSpec_DELIMITER_STRING", "DelimitersOpenSpec_COMMA", "DelimitersCloseSpec_WS", 
		"DelimitersCloseSpec_NEWLINE", "DelimitersCloseSpec_DELIMITER_STRING"
	};
	public static final String[] ruleNames = {
		"LPAREN", "RPAREN", "COMMA", "DOT", "COLON", "DEFINED", "EQUALS", "AT", 
		"LBRACK", "RBRACK", "LBRACE", "RBRACE", "GROUP", "DEFAULT", "IMPORT", 
		"TRUE", "FALSE", "DELIMITERS", "WS", "NEWLINE", "ID", "STRING", "BIGSTRING", 
		"BIGSTRINGLINE", "LINE_COMMENT", "COMMENT", "ANONYMOUS_PARAMETERS", "DELIMITERS_SPEC", 
		"DELIMITER_STRING", "WS_CHAR", "OPEN_DELIMITER", "CLOSE_DELIMITER", "ANYCHAR", 
		"BlockComment_NEWLINE", "CONTINUE_COMMENT", "END_COMMENT", "BLOCK_COMMENT_STAR", 
		"BlockComment_ANYCHAR", "TemplateComment_NEWLINE", "TemplateComment_CONTINUE_COMMENT", 
		"TemplateComment_END_COMMENT", "TemplateComment_BLOCK_COMMENT_BANG", "TemplateComment_ANYCHAR", 
		"AnonymousTemplate_RBRACE", "AnonymousTemplate_NEWLINE", "AnonymousTemplate_COMMENT", 
		"AnonymousTemplate_OPEN_DELIMITER", "TEXT", "ESCAPE_RBRACE", "AnonymousTemplate_ANYCHAR", 
		"AnonymousTemplateParameters_WS", "AnonymousTemplateParameters_NEWLINE", 
		"AnonymousTemplateParameters_COMMA", "TEMPLATE_PARAMETER", "PIPE", "AnonymousTemplateParameters_ANYCHAR", 
		"BigStringTemplate_NEWLINE", "BigStringTemplate_COMMENT", "BigStringTemplate_OPEN_DELIMITER", 
		"BigStringTemplate_TEXT", "BigStringTemplate_ESCAPE", "BigStringTemplate_END", 
		"BigStringTemplate_LANGLE", "BigStringTemplate_ANYCHAR", "BigStringLineTemplate_NEWLINE", 
		"BigStringLineTemplate_COMMENT", "BigStringLineTemplate_OPEN_DELIMITER", 
		"BigStringLineTemplate_TEXT", "BigStringLineTemplate_ESCAPE", "BigStringLineTemplate_END", 
		"BigStringLineTemplate_PERCENT", "BigStringLineTemplate_ANYCHAR", "TemplateExpression_NEWLINE", 
		"TemplateExpression_DOT", "TemplateExpression_COMMA", "TemplateExpression_COLON", 
		"TemplateExpression_LPAREN", "TemplateExpression_RPAREN", "TemplateExpression_LBRACK", 
		"TemplateExpression_RBRACK", "TemplateExpression_EQUALS", "TemplateExpression_LBRACE", 
		"TemplateExpression_WS", "SUPER", "IF", "ELSEIF", "ENDIF", "ELSE", "END", 
		"ELLIPSIS", "NOT", "OR", "AND", "SEMI", "ESCAPE", "REGION_END", "REGION_ID", 
		"TemplateExpression_ID", "TemplateExpression_CLOSE_DELIMITER", "TemplateExpression_STRING", 
		"TemplateExpression_ANYCHAR", "StringTemplate_NEWLINE", "StringTemplate_COMMENT", 
		"StringTemplate_OPEN_DELIMITER", "StringTemplate_TEXT", "StringTemplate_ESCAPE", 
		"StringTemplate_END", "StringTemplate_ANYCHAR", "DelimitersOpenSpec_WS", 
		"DelimitersOpenSpec_NEWLINE", "DelimitersOpenSpec_DELIMITER_STRING", "DelimitersOpenSpec_COMMA", 
		"DelimitersCloseSpec_WS", "DelimitersCloseSpec_NEWLINE", "DelimitersCloseSpec_DELIMITER_STRING"
	};


	public TemplateLexerBase(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN);
	}

	public String getGrammarFileName() { return "TemplateLexerBase.java"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch ( ruleIndex ) {
			case 10 : LBRACE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 18 : WS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 19 : NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 21 : STRING_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 22 : BIGSTRING_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 23 : BIGSTRINGLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 24 : LINE_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 25 : COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 33 : BlockComment_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 34 : CONTINUE_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 35 : END_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 36 : BLOCK_COMMENT_STAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 37 : BlockComment_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 38 : TemplateComment_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 39 : TemplateComment_CONTINUE_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 40 : TemplateComment_END_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 41 : TemplateComment_BLOCK_COMMENT_BANG_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 42 : TemplateComment_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 43 : AnonymousTemplate_RBRACE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 44 : AnonymousTemplate_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 45 : AnonymousTemplate_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 46 : AnonymousTemplate_OPEN_DELIMITER_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 48 : ESCAPE_RBRACE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 49 : AnonymousTemplate_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 50 : AnonymousTemplateParameters_WS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 51 : AnonymousTemplateParameters_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 52 : AnonymousTemplateParameters_COMMA_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 54 : PIPE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 55 : AnonymousTemplateParameters_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 56 : BigStringTemplate_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 57 : BigStringTemplate_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 58 : BigStringTemplate_OPEN_DELIMITER_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 59 : BigStringTemplate_TEXT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 60 : BigStringTemplate_ESCAPE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 61 : BigStringTemplate_END_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 62 : BigStringTemplate_LANGLE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 63 : BigStringTemplate_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 64 : BigStringLineTemplate_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 65 : BigStringLineTemplate_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 66 : BigStringLineTemplate_OPEN_DELIMITER_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 67 : BigStringLineTemplate_TEXT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 68 : BigStringLineTemplate_ESCAPE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 69 : BigStringLineTemplate_END_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 70 : BigStringLineTemplate_PERCENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 71 : BigStringLineTemplate_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 72 : TemplateExpression_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 73 : TemplateExpression_DOT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 74 : TemplateExpression_COMMA_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 75 : TemplateExpression_COLON_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 76 : TemplateExpression_LPAREN_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 77 : TemplateExpression_RPAREN_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 78 : TemplateExpression_LBRACK_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 79 : TemplateExpression_RBRACK_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 80 : TemplateExpression_EQUALS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 81 : TemplateExpression_LBRACE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 82 : TemplateExpression_WS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 97 : TemplateExpression_ID_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 98 : TemplateExpression_CLOSE_DELIMITER_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 99 : TemplateExpression_STRING_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 100 : TemplateExpression_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 101 : StringTemplate_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 102 : StringTemplate_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 103 : StringTemplate_OPEN_DELIMITER_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 104 : StringTemplate_TEXT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 105 : StringTemplate_ESCAPE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 106 : StringTemplate_END_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 107 : StringTemplate_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 108 : DelimitersOpenSpec_WS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 109 : DelimitersOpenSpec_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 111 : DelimitersOpenSpec_COMMA_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 112 : DelimitersCloseSpec_WS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 113 : DelimitersCloseSpec_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 114 : DelimitersCloseSpec_DELIMITER_STRING_action((ParserRuleContext<?>)_localctx, actionIndex); break;
		}
	}
	public void TemplateExpression_LPAREN_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 49 : type =  LPAREN; break;
		}
	}
	public void TemplateComment_BLOCK_COMMENT_BANG_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 16 : type =  COMMENT; channel =  HIDDEN; break;
		}
	}
	public void TemplateComment_END_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 15 : type =  COMMENT; channel =  HIDDEN; popMode(); break;
		}
	}
	public void BigStringTemplate_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 30 : type =  COMMENT; pushMode(TemplateComment); break;
		}
	}
	public void StringTemplate_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 60 : type =  NEWLINE; break;
		}
	}
	public void StringTemplate_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 61 : type =  COMMENT; pushMode(TemplateComment); break;
		}
	}
	public void BigStringLineTemplate_PERCENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 43 : type =  TEXT; break;
		}
	}
	public void END_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 10 : type =  COMMENT; channel =  HIDDEN; popMode(); break;
		}
	}
	public void BigStringTemplate_OPEN_DELIMITER_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 31 : type =  OPEN_DELIMITER; pushMode(TemplateExpression); break;
		}
	}
	public void LBRACE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 0 : pushMode(AnonymousTemplate); break;
		}
	}
	public void BigStringLineTemplate_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 37 : type =  NEWLINE; break;
		}
	}
	public void StringTemplate_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 66 : type =  ANYCHAR; break;
		}
	}
	public void AnonymousTemplateParameters_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 25 : type =  NEWLINE; channel =  HIDDEN; break;
		}
	}
	public void TemplateExpression_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 59 : type =  ANYCHAR; break;
		}
	}
	public void BigStringLineTemplate_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 44 : type =  ANYCHAR; break;
		}
	}
	public void TemplateComment_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 17 : type =  ANYCHAR; channel =  HIDDEN; break;
		}
	}
	public void TemplateExpression_EQUALS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 53 : type =  EQUALS; break;
		}
	}
	public void StringTemplate_ESCAPE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 64 : type =  TEXT; break;
		}
	}
	public void TemplateExpression_CLOSE_DELIMITER_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 57 : type =  CLOSE_DELIMITER; popMode(); break;
		}
	}
	public void AnonymousTemplate_OPEN_DELIMITER_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 21 : type =  OPEN_DELIMITER; pushMode(TemplateExpression); break;
		}
	}
	public void AnonymousTemplate_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 23 : type =  ANYCHAR; break;
		}
	}
	public void BigStringTemplate_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 29 : type =  NEWLINE; break;
		}
	}
	public void DelimitersOpenSpec_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 68 : type =  NEWLINE; break;
		}
	}
	public void TemplateComment_CONTINUE_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 14 : type =  COMMENT; channel =  HIDDEN; break;
		}
	}
	public void BigStringLineTemplate_OPEN_DELIMITER_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 39 : type =  OPEN_DELIMITER; pushMode(TemplateExpression); break;
		}
	}
	public void TemplateExpression_COMMA_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 47 : type =  COMMA; break;
		}
	}
	public void TemplateExpression_RPAREN_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 50 : type =  RPAREN; break;
		}
	}
	public void BIGSTRING_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 4 : pushMode(BigStringTemplate); break;
		}
	}
	public void BigStringLineTemplate_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 38 : type =  COMMENT; pushMode(TemplateComment); break;
		}
	}
	public void AnonymousTemplate_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 19 : type =  NEWLINE; break;
		}
	}
	public void CONTINUE_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 9 : type =  COMMENT; channel =  HIDDEN; break;
		}
	}
	public void PIPE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 27 : popMode(); break;
		}
	}
	public void TemplateExpression_LBRACE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 54 : type =  LBRACE; pushMode(AnonymousTemplate); break;
		}
	}
	public void TemplateExpression_ID_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 56 : type =  ID; break;
		}
	}
	public void COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 7 : channel =  HIDDEN; pushMode(BlockComment); break;
		}
	}
	public void DelimitersCloseSpec_WS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 70 : type =  WS; break;
		}
	}
	public void TemplateExpression_LBRACK_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 51 : type =  LBRACK; break;
		}
	}
	public void BigStringTemplate_TEXT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 32 : type =  TEXT; break;
		}
	}
	public void AnonymousTemplate_RBRACE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 18 : type =  RBRACE; popMode(); break;
		}
	}
	public void TemplateExpression_RBRACK_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 52 : type =  RBRACK; break;
		}
	}
	public void AnonymousTemplateParameters_COMMA_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 26 : type =  COMMA; break;
		}
	}
	public void BigStringTemplate_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 36 : type =  ANYCHAR; break;
		}
	}
	public void BigStringLineTemplate_TEXT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 40 : type =  TEXT; break;
		}
	}
	public void LINE_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 6 : channel =  HIDDEN; break;
		}
	}
	public void AnonymousTemplate_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 20 : type =  COMMENT; pushMode(TemplateComment); break;
		}
	}
	public void BigStringLineTemplate_ESCAPE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 41 : type =  TEXT; break;
		}
	}
	public void TemplateExpression_WS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 55 : type =  WS; channel =  HIDDEN; break;
		}
	}
	public void TemplateExpression_DOT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 46 : type =  DOT; break;
		}
	}
	public void TemplateExpression_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 45 : type =  NEWLINE; channel =  HIDDEN; break;
		}
	}
	public void DelimitersOpenSpec_WS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 67 : type =  WS; break;
		}
	}
	public void StringTemplate_OPEN_DELIMITER_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 62 : type =  OPEN_DELIMITER; pushMode(TemplateExpression); break;
		}
	}
	public void BigStringLineTemplate_END_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 42 : popMode(); break;
		}
	}
	public void TemplateExpression_STRING_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 58 : type =  STRING; break;
		}
	}
	public void ESCAPE_RBRACE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 22 : type =  TEXT; break;
		}
	}
	public void BigStringTemplate_ESCAPE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 33 : type =  TEXT; break;
		}
	}
	public void WS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 1 : channel =  HIDDEN; break;
		}
	}
	public void NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 2 : channel =  HIDDEN; break;
		}
	}
	public void BIGSTRINGLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 5 : pushMode(BigStringLineTemplate); break;
		}
	}
	public void BlockComment_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 12 : type =  ANYCHAR; channel =  HIDDEN; break;
		}
	}
	public void TemplateComment_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 13 : type =  NEWLINE; channel =  HIDDEN; break;
		}
	}
	public void StringTemplate_TEXT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 63 : type =  TEXT; break;
		}
	}
	public void AnonymousTemplateParameters_WS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 24 : type =  WS; channel =  HIDDEN; break;
		}
	}
	public void AnonymousTemplateParameters_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 28 : type =  ANYCHAR; break;
		}
	}
	public void StringTemplate_END_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 65 : type =  QUOTE; popMode(); break;
		}
	}
	public void TemplateExpression_COLON_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 48 : type =  COLON; break;
		}
	}
	public void DelimitersCloseSpec_DELIMITER_STRING_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 72 : popMode(); break;
		}
	}
	public void BLOCK_COMMENT_STAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 11 : type =  COMMENT; channel =  HIDDEN; break;
		}
	}
	public void DelimitersCloseSpec_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 71 : type =  NEWLINE; break;
		}
	}
	public void DelimitersOpenSpec_COMMA_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 69 : type =  COMMA; mode(DelimitersCloseSpec); break;
		}
	}
	public void BigStringTemplate_LANGLE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 35 : type =  TEXT; break;
		}
	}
	public void STRING_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 3 : type =  QUOTE; pushMode(StringTemplate); break;
		}
	}
	public void BlockComment_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 8 : type =  NEWLINE; channel =  HIDDEN; break;
		}
	}
	public void BigStringTemplate_END_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 34 : popMode(); break;
		}
	}

	public static final String _serializedATN =
		"\2r\u0328\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff"+
		"\6\uffff\6\uffff\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7"+
		"\5\2\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2"+
		"\16\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2"+
		"\25\7\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2"+
		"\34\7\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7"+
		"$\2%\7%\2&\7&\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2.\7.\2/\7/"+
		"\2\60\7\60\2\61\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65\2\66\7\66"+
		"\2\67\7\67\28\78\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2@\7@\2A\7"+
		"A\2B\7B\2C\7C\2D\7D\2E\7E\2F\7F\2G\7G\2H\7H\2I\7I\2J\7J\2K\7K\2L\7L\2"+
		"M\7M\2N\7N\2O\7O\2P\7P\2Q\7Q\2R\7R\2S\7S\2T\7T\2U\7U\2V\7V\2W\7W\2X\7"+
		"X\2Y\7Y\2Z\7Z\2[\7[\2\\\7\\\2]\7]\2^\7^\2_\7_\2`\7`\2a\7a\2b\7b\2c\7c"+
		"\2d\7d\2e\7e\2f\7f\2g\7g\2h\7h\2i\7i\2j\7j\2k\7k\2l\7l\2m\7m\2n\7n\2o"+
		"\7o\2p\7p\2q\7q\2r\7r\1\0\1\0\1\1\1\1\1\2\1\2\1\3\1\3\1\4\1\4\1\5\1\5"+
		"\1\5\1\5\1\6\1\6\1\7\1\7\1\b\1\b\1\t\1\t\1\n\0\1\n\0\3\n\b\n\1\n\1\13"+
		"\1\13\1\f\1\f\1\f\1\f\1\f\1\f\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\16\1\16"+
		"\1\16\1\16\1\16\1\16\1\16\1\17\1\17\1\17\1\17\1\17\1\20\1\20\1\20\1\20"+
		"\1\20\1\20\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21"+
		"\0\3\21\b\21\1\22\0\4\22\b\22\13\22\1\22\1\22\1\23\0\1\23\0\3\23\b\23"+
		"\1\23\0\3\23\b\23\1\23\1\24\0\1\24\0\5\24\b\24\n\24\1\24\t\24\1\25\0\1"+
		"\25\1\26\1\26\1\26\1\26\1\27\1\27\1\27\1\27\1\30\1\30\1\30\1\30\0\5\30"+
		"\b\30\n\30\1\30\t\30\1\30\1\31\1\31\1\31\1\31\1\32\0\5\32\b\32\n\32\1"+
		"\32\t\32\1\32\0\1\32\0\5\32\b\32\n\32\1\32\t\32\1\32\0\1\32\0\5\32\b\32"+
		"\n\32\1\32\t\32\1\32\1\32\5\32\b\32\n\32\1\32\t\32\1\32\0\5\32\b\32\n"+
		"\32\1\32\t\32\1\32\1\32\1\33\0\5\33\b\33\n\33\1\33\t\33\1\33\0\1\33\0"+
		"\5\33\b\33\n\33\1\33\t\33\1\33\0\1\33\0\5\33\b\33\n\33\1\33\t\33\1\33"+
		"\1\33\1\34\0\1\34\0\4\34\b\34\13\34\1\34\1\34\1\34\1\35\0\1\35\0\1\35"+
		"\0\1\35\0\3\35\b\35\1\36\1\36\1\37\1\37\1 \1 \1!\0\1!\1\"\0\4\"\b\"\13"+
		"\"\1\"\1\"\1#\1#\1#\1#\1$\0\1$\1%\0\1%\1&\0\1&\1\'\0\4\'\b\'\13\'\1\'"+
		"\1\'\1(\0\1(\0\1(\1)\0\1)\1*\0\1*\1+\0\1+\1,\0\1,\1-\0\1-\0\1-\1.\0\1"+
		".\1/\0\4/\b/\13/\1/\1\60\0\1\60\0\1\60\0\3\60\b\60\1\60\1\61\0\1\61\1"+
		"\62\0\1\62\1\63\0\1\63\1\64\0\1\64\1\65\1\65\1\66\0\1\66\1\67\0\1\67\1"+
		"8\0\18\19\0\19\0\19\1:\0\1:\1;\0\4;\b;\13;\1;\1;\1<\0\1<\0\1<\0\3<\b<"+
		"\1<\1=\1=\1=\1=\1>\0\1>\1?\0\1?\1@\0\1@\1A\0\1A\0\1A\1B\0\1B\1C\0\4C\b"+
		"C\13C\1C\1C\1D\0\1D\0\1D\0\3D\bD\1D\1E\1E\1E\1E\1F\0\1F\1G\0\1G\1H\0\1"+
		"H\1I\0\1I\1J\0\1J\1K\0\1K\1L\0\1L\1M\0\1M\1N\0\1N\1O\0\1O\1P\0\1P\1Q\0"+
		"\1Q\1R\0\1R\1S\1S\1S\1S\1S\1S\1T\1T\1T\1U\1U\1U\1U\1U\1U\1U\1V\1V\1V\1"+
		"V\1V\1V\1W\1W\1W\1W\1W\1X\1X\1X\1X\1Y\1Y\1Y\1Y\1Z\1Z\1[\1[\1[\1\\\1\\"+
		"\1\\\1]\1]\1^\0\1^\0\5^\b^\n^\1^\t^\1_\1_\1_\1_\1_\1`\0\1`\1`\1a\0\1a"+
		"\1b\0\1b\1c\0\1c\0\1c\0\1c\0\1c\0\3c\bc\5c\bc\nc\1c\tc\1c\0\1c\1d\0\1"+
		"d\1e\0\1e\1f\0\1f\0\1f\1g\0\1g\1h\0\4h\bh\13h\1h\1h\1i\0\1i\0\1i\0\3i"+
		"\bi\1i\1j\0\1j\1k\0\1k\1l\0\1l\1m\0\1m\1n\1n\1o\0\1o\1p\0\1p\1q\0\1q\1"+
		"r\0\1rs\13\6\uffff\r\7\uffff\17\b\uffff\21\t\uffff\23\n\uffff\25\13\uffff"+
		"\27\f\uffff\31\r\uffff\33\16\uffff\35\17\uffff\37\20\0!\21\uffff#\22\uffff"+
		"%\23\uffff\'\24\uffff)\25\uffff+\26\uffff-\27\uffff/\30\1\61\31\2\63\32"+
		"\uffff\65\33\3\67\34\49\35\5;\36\6=\37\7?\0\uffffA\0\uffffC\0\uffffE\0"+
		"\uffffG\3\uffffI\4\uffffK \uffffM!\bO\"\tQ#\nS$\13U%\fW&\rY\'\16[(\17"+
		"])\20_*\21a+\22c,\23e-\24g.\25i/\uffffk\60\26m\61\27o\62\30q\63\31s\64"+
		"\32u\65\uffffw\66\33y\67\34{8\35}9\36\177:\37\u0081; \u0083<!\u0085=\""+
		"\u0087>#\u0089?$\u008b@%\u008dA&\u008fB\'\u0091C(\u0093D)\u0095E*\u0097"+
		"F+\u0099G,\u009bH-\u009dI.\u009fJ/\u00a1K\60\u00a3L\61\u00a5M\62\u00a7"+
		"N\63\u00a9O\64\u00abP\65\u00adQ\66\u00afR\67\u00b1S\uffff\u00b3T\uffff"+
		"\u00b5U\uffff\u00b7V\uffff\u00b9W\uffff\u00bbX\uffff\u00bdY\uffff\u00bf"+
		"Z\uffff\u00c1[\uffff\u00c3\\\uffff\u00c5]\uffff\u00c7^\uffff\u00c9_\uffff"+
		"\u00cb`\uffff\u00cda8\u00cfb9\u00d1c:\u00d3d;\u00d5e<\u00d7f=\u00d9g>"+
		"\u00dbh?\u00ddi@\u00dfjA\u00e1kB\u00e3lC\u00e5mD\u00e7n\uffff\u00e9oE"+
		"\u00ebpF\u00edqG\u00efrH\13\0\1\2\3\4\5\6\7\b\t\n\r\2\t\t  \3AZ__az\5"+
		"--\609AZ__az\2\n\n\r\r\3\n\n\r\r\"\"\3\n\n\r\r**\3\n\n\r\r!!\5\n\n\r\r"+
		"\\\\}}\ufff0\ufff0\5\n\n\r\r>>\\\\\ufff0\ufff0\5\n\n\r\r%%\\\\\ufff0\ufff0"+
		"\1\ufff1\ufff1\2\"\"\\\\\5\n\n\r\r\"\"\\\\\ufff0\ufff0\u02c8\0\13\1\0"+
		"\0\0\0\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1\0\0\0\0\25\1\0\0\0\0"+
		"\27\1\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0\0\0\37\1\0\0\0\0!\1\0"+
		"\0\0\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0\0\0+\1\0\0\0\0-\1\0\0"+
		"\0\0/\1\0\0\0\0\61\1\0\0\0\0\63\1\0\0\0\0\65\1\0\0\0\0\67\1\0\0\0\09\1"+
		"\0\0\0\0;\1\0\0\0\0=\1\0\0\0\0K\1\0\0\0\1M\1\0\0\0\1O\1\0\0\0\1Q\1\0\0"+
		"\0\1S\1\0\0\0\1U\1\0\0\0\2W\1\0\0\0\2Y\1\0\0\0\2[\1\0\0\0\2]\1\0\0\0\2"+
		"_\1\0\0\0\3a\1\0\0\0\3c\1\0\0\0\3e\1\0\0\0\3g\1\0\0\0\3i\1\0\0\0\3k\1"+
		"\0\0\0\3m\1\0\0\0\4o\1\0\0\0\4q\1\0\0\0\4s\1\0\0\0\4u\1\0\0\0\4w\1\0\0"+
		"\0\4y\1\0\0\0\5{\1\0\0\0\5}\1\0\0\0\5\177\1\0\0\0\5\u0081\1\0\0\0\5\u0083"+
		"\1\0\0\0\5\u0085\1\0\0\0\5\u0087\1\0\0\0\5\u0089\1\0\0\0\6\u008b\1\0\0"+
		"\0\6\u008d\1\0\0\0\6\u008f\1\0\0\0\6\u0091\1\0\0\0\6\u0093\1\0\0\0\6\u0095"+
		"\1\0\0\0\6\u0097\1\0\0\0\6\u0099\1\0\0\0\7\u009b\1\0\0\0\7\u009d\1\0\0"+
		"\0\7\u009f\1\0\0\0\7\u00a1\1\0\0\0\7\u00a3\1\0\0\0\7\u00a5\1\0\0\0\7\u00a7"+
		"\1\0\0\0\7\u00a9\1\0\0\0\7\u00ab\1\0\0\0\7\u00ad\1\0\0\0\7\u00af\1\0\0"+
		"\0\7\u00b1\1\0\0\0\7\u00b3\1\0\0\0\7\u00b5\1\0\0\0\7\u00b7\1\0\0\0\7\u00b9"+
		"\1\0\0\0\7\u00bb\1\0\0\0\7\u00bd\1\0\0\0\7\u00bf\1\0\0\0\7\u00c1\1\0\0"+
		"\0\7\u00c3\1\0\0\0\7\u00c5\1\0\0\0\7\u00c7\1\0\0\0\7\u00c9\1\0\0\0\7\u00cb"+
		"\1\0\0\0\7\u00cd\1\0\0\0\7\u00cf\1\0\0\0\7\u00d1\1\0\0\0\7\u00d3\1\0\0"+
		"\0\b\u00d5\1\0\0\0\b\u00d7\1\0\0\0\b\u00d9\1\0\0\0\b\u00db\1\0\0\0\b\u00dd"+
		"\1\0\0\0\b\u00df\1\0\0\0\b\u00e1\1\0\0\0\t\u00e3\1\0\0\0\t\u00e5\1\0\0"+
		"\0\t\u00e7\1\0\0\0\t\u00e9\1\0\0\0\n\u00eb\1\0\0\0\n\u00ed\1\0\0\0\n\u00ef"+
		"\1\0\0\0\13\u00f1\1\0\0\0\r\u00f3\1\0\0\0\17\u00f5\1\0\0\0\21\u00f7\1"+
		"\0\0\0\23\u00f9\1\0\0\0\25\u00fb\1\0\0\0\27\u00ff\1\0\0\0\31\u0101\1\0"+
		"\0\0\33\u0103\1\0\0\0\35\u0105\1\0\0\0\37\u0107\1\0\0\0!\u010e\1\0\0\0"+
		"#\u0110\1\0\0\0%\u0116\1\0\0\0\'\u011e\1\0\0\0)\u0125\1\0\0\0+\u012a\1"+
		"\0\0\0-\u0130\1\0\0\0/\u0141\1\0\0\0\61\u014e\1\0\0\0\63\u0151\1\0\0\0"+
		"\65\u015a\1\0\0\0\67\u015d\1\0\0\09\u0161\1\0\0\0;\u0165\1\0\0\0=\u0170"+
		"\1\0\0\0?\u0178\1\0\0\0A\u01a1\1\0\0\0C\u01b8\1\0\0\0E\u01ca\1\0\0\0G"+
		"\u01cc\1\0\0\0I\u01ce\1\0\0\0K\u01d0\1\0\0\0M\u01d2\1\0\0\0O\u01d7\1\0"+
		"\0\0Q\u01dc\1\0\0\0S\u01e0\1\0\0\0U\u01e3\1\0\0\0W\u01e6\1\0\0\0Y\u01eb"+
		"\1\0\0\0[\u01f0\1\0\0\0]\u01f5\1\0\0\0_\u01f8\1\0\0\0a\u01fb\1\0\0\0c"+
		"\u01fe\1\0\0\0e\u0201\1\0\0\0g\u0206\1\0\0\0i\u020b\1\0\0\0k\u020f\1\0"+
		"\0\0m\u0218\1\0\0\0o\u021b\1\0\0\0q\u021e\1\0\0\0s\u0221\1\0\0\0u\u0224"+
		"\1\0\0\0w\u0226\1\0\0\0y\u0229\1\0\0\0{\u022c\1\0\0\0}\u022f\1\0\0\0\177"+
		"\u0234\1\0\0\0\u0081\u0239\1\0\0\0\u0083\u023e\1\0\0\0\u0085\u0247\1\0"+
		"\0\0\u0087\u024b\1\0\0\0\u0089\u024e\1\0\0\0\u008b\u0251\1\0\0\0\u008d"+
		"\u0254\1\0\0\0\u008f\u0259\1\0\0\0\u0091\u025e\1\0\0\0\u0093\u0263\1\0"+
		"\0\0\u0095\u026c\1\0\0\0\u0097\u0270\1\0\0\0\u0099\u0273\1\0\0\0\u009b"+
		"\u0276\1\0\0\0\u009d\u0279\1\0\0\0\u009f\u027c\1\0\0\0\u00a1\u027f\1\0"+
		"\0\0\u00a3\u0282\1\0\0\0\u00a5\u0285\1\0\0\0\u00a7\u0288\1\0\0\0\u00a9"+
		"\u028b\1\0\0\0\u00ab\u028e\1\0\0\0\u00ad\u0291\1\0\0\0\u00af\u0294\1\0"+
		"\0\0\u00b1\u0297\1\0\0\0\u00b3\u029d\1\0\0\0\u00b5\u02a0\1\0\0\0\u00b7"+
		"\u02a7\1\0\0\0\u00b9\u02ad\1\0\0\0\u00bb\u02b2\1\0\0\0\u00bd\u02b6\1\0"+
		"\0\0\u00bf\u02ba\1\0\0\0\u00c1\u02bc\1\0\0\0\u00c3\u02bf\1\0\0\0\u00c5"+
		"\u02c2\1\0\0\0\u00c7\u02c4\1\0\0\0\u00c9\u02cd\1\0\0\0\u00cb\u02d2\1\0"+
		"\0\0\u00cd\u02d6\1\0\0\0\u00cf\u02d9\1\0\0\0\u00d1\u02dc\1\0\0\0\u00d3"+
		"\u02f0\1\0\0\0\u00d5\u02f3\1\0\0\0\u00d7\u02f6\1\0\0\0\u00d9\u02fb\1\0"+
		"\0\0\u00db\u0300\1\0\0\0\u00dd\u0305\1\0\0\0\u00df\u030e\1\0\0\0\u00e1"+
		"\u0311\1\0\0\0\u00e3\u0314\1\0\0\0\u00e5\u0317\1\0\0\0\u00e7\u031a\1\0"+
		"\0\0\u00e9\u031c\1\0\0\0\u00eb\u031f\1\0\0\0\u00ed\u0322\1\0\0\0\u00ef"+
		"\u0325\1\0\0\0\u00f1\u00f2\5(\0\0\u00f2\f\1\0\0\0\u00f3\u00f4\5)\0\0\u00f4"+
		"\16\1\0\0\0\u00f5\u00f6\5,\0\0\u00f6\20\1\0\0\0\u00f7\u00f8\5.\0\0\u00f8"+
		"\22\1\0\0\0\u00f9\u00fa\5:\0\0\u00fa\24\1\0\0\0\u00fb\u00fc\5:\0\0\u00fc"+
		"\u00fd\5:\0\0\u00fd\u00fe\5=\0\0\u00fe\26\1\0\0\0\u00ff\u0100\5=\0\0\u0100"+
		"\30\1\0\0\0\u0101\u0102\5@\0\0\u0102\32\1\0\0\0\u0103\u0104\5[\0\0\u0104"+
		"\34\1\0\0\0\u0105\u0106\5]\0\0\u0106\36\1\0\0\0\u0107\u010b\5{\0\0\u0109"+
		"\u010c\3?\32\0\u010b\u0109\1\0\0\0\u010b\u010c\1\0\0\0\u010c\u010d\1\0"+
		"\0\0\u010d \1\0\0\0\u010e\u010f\5}\0\0\u010f\"\1\0\0\0\u0110\u0111\5g"+
		"\0\0\u0111\u0112\5r\0\0\u0112\u0113\5o\0\0\u0113\u0114\5u\0\0\u0114\u0115"+
		"\5p\0\0\u0115$\1\0\0\0\u0116\u0117\5d\0\0\u0117\u0118\5e\0\0\u0118\u0119"+
		"\5f\0\0\u0119\u011a\5a\0\0\u011a\u011b\5u\0\0\u011b\u011c\5l\0\0\u011c"+
		"\u011d\5t\0\0\u011d&\1\0\0\0\u011e\u011f\5i\0\0\u011f\u0120\5m\0\0\u0120"+
		"\u0121\5p\0\0\u0121\u0122\5o\0\0\u0122\u0123\5r\0\0\u0123\u0124\5t\0\0"+
		"\u0124(\1\0\0\0\u0125\u0126\5t\0\0\u0126\u0127\5r\0\0\u0127\u0128\5u\0"+
		"\0\u0128\u0129\5e\0\0\u0129*\1\0\0\0\u012a\u012b\5f\0\0\u012b\u012c\5"+
		"a\0\0\u012c\u012d\5l\0\0\u012d\u012e\5s\0\0\u012e\u012f\5e\0\0\u012f,"+
		"\1\0\0\0\u0130\u0131\5d\0\0\u0131\u0132\5e\0\0\u0132\u0133\5l\0\0\u0133"+
		"\u0134\5i\0\0\u0134\u0135\5m\0\0\u0135\u0136\5i\0\0\u0136\u0137\5t\0\0"+
		"\u0137\u0138\5e\0\0\u0138\u0139\5r\0\0\u0139\u013a\5s\0\0\u013a\u013d"+
		"\1\0\0\0\u013b\u013e\3A\33\0\u013d\u013b\1\0\0\0\u013d\u013e\1\0\0\0\u013e"+
		".\1\0\0\0\u013f\u0142\7\0\0\0\u0141\u013f\1\0\0\0\u0142\u0143\1\0\0\0"+
		"\u0143\u0141\1\0\0\0\u0143\u0144\1\0\0\0\u0144\u0145\1\0\0\0\u0145\60"+
		"\1\0\0\0\u0146\u014a\5\r\0\0\u0148\u014b\5\n\0\0\u014a\u0148\1\0\0\0\u014a"+
		"\u014b\1\0\0\0\u014b\u014f\1\0\0\0\u014c\u014f\5\n\0\0\u014e\u0146\1\0"+
		"\0\0\u014e\u014c\1\0\0\0\u014f\u0150\1\0\0\0\u0150\62\1\0\0\0\u0151\u0157"+
		"\7\1\0\0\u0153\u0156\7\2\0\0\u0155\u0153\1\0\0\0\u0156\u0159\1\0\0\0\u0157"+
		"\u0155\1\0\0\0\u0157\u0158\1\0\0\0\u0158\64\1\0\0\0\u0159\u0157\1\0\0"+
		"\0\u015a\u015c\5\"\0\0\u015c\66\1\0\0\0\u015d\u015e\5<\0\0\u015e\u015f"+
		"\5<\0\0\u015f\u0160\1\0\0\0\u01608\1\0\0\0\u0161\u0162\5<\0\0\u0162\u0163"+
		"\5%\0\0\u0163\u0164\1\0\0\0\u0164:\1\0\0\0\u0165\u0166\5/\0\0\u0166\u0167"+
		"\5/\0\0\u0167\u016c\1\0\0\0\u0168\u016b\b\3\0\0\u016a\u0168\1\0\0\0\u016b"+
		"\u016e\1\0\0\0\u016c\u016a\1\0\0\0\u016c\u016d\1\0\0\0\u016d\u016f\1\0"+
		"\0\0\u016e\u016c\1\0\0\0\u016f<\1\0\0\0\u0170\u0171\5/\0\0\u0171\u0172"+
		"\5*\0\0\u0172\u0173\1\0\0\0\u0173>\1\0\0\0\u0174\u0177\3E\35\0\u0176\u0174"+
		"\1\0\0\0\u0177\u017a\1\0\0\0\u0178\u0176\1\0\0\0\u0178\u0179\1\0\0\0\u0179"+
		"\u017b\1\0\0\0\u017a\u0178\1\0\0\0\u017b\u0191\3\63\24\0\u017d\u0180\3"+
		"E\35\0\u017f\u017d\1\0\0\0\u0180\u0183\1\0\0\0\u0181\u017f\1\0\0\0\u0181"+
		"\u0182\1\0\0\0\u0182\u0184\1\0\0\0\u0183\u0181\1\0\0\0\u0184\u018a\3\17"+
		"\2\0\u0186\u0189\3E\35\0\u0188\u0186\1\0\0\0\u0189\u018c\1\0\0\0\u018a"+
		"\u0188\1\0\0\0\u018a\u018b\1\0\0\0\u018b\u018d\1\0\0\0\u018c\u018a\1\0"+
		"\0\0\u018d\u018e\3\63\24\0\u018e\u0190\1\0\0\0\u018f\u0181\1\0\0\0\u0190"+
		"\u0193\1\0\0\0\u0191\u018f\1\0\0\0\u0191\u0192\1\0\0\0\u0192\u0198\1\0"+
		"\0\0\u0193\u0191\1\0\0\0\u0194\u0197\3E\35\0\u0196\u0194\1\0\0\0\u0197"+
		"\u019a\1\0\0\0\u0198\u0196\1\0\0\0\u0198\u0199\1\0\0\0\u0199\u019b\1\0"+
		"\0\0\u019a\u0198\1\0\0\0\u019b\u019c\5|\0\0\u019c@\1\0\0\0\u019d\u01a0"+
		"\3E\35\0\u019f\u019d\1\0\0\0\u01a0\u01a3\1\0\0\0\u01a1\u019f\1\0\0\0\u01a1"+
		"\u01a2\1\0\0\0\u01a2\u01a4\1\0\0\0\u01a3\u01a1\1\0\0\0\u01a4\u01aa\3C"+
		"\34\0\u01a6\u01a9\3E\35\0\u01a8\u01a6\1\0\0\0\u01a9\u01ac\1\0\0\0\u01aa"+
		"\u01a8\1\0\0\0\u01aa\u01ab\1\0\0\0\u01ab\u01ad\1\0\0\0\u01ac\u01aa\1\0"+
		"\0\0\u01ad\u01b3\3\17\2\0\u01af\u01b2\3E\35\0\u01b1\u01af\1\0\0\0\u01b2"+
		"\u01b5\1\0\0\0\u01b3\u01b1\1\0\0\0\u01b3\u01b4\1\0\0\0\u01b4\u01b6\1\0"+
		"\0\0\u01b5\u01b3\1\0\0\0\u01b6\u01b7\3C\34\0\u01b7B\1\0\0\0\u01b8\u01bc"+
		"\5\"\0\0\u01ba\u01bd\b\4\0\0\u01bc\u01ba\1\0\0\0\u01bd\u01be\1\0\0\0\u01be"+
		"\u01bc\1\0\0\0\u01be\u01bf\1\0\0\0\u01bf\u01c0\1\0\0\0\u01c0\u01c1\5\""+
		"\0\0\u01c1D\1\0\0\0\u01c2\u01cb\5 \0\0\u01c4\u01cb\5\t\0\0\u01c6\u01cb"+
		"\5\r\0\0\u01c8\u01cb\5\n\0\0\u01ca\u01c2\1\0\0\0\u01ca\u01c4\1\0\0\0\u01ca"+
		"\u01c6\1\0\0\0\u01ca\u01c8\1\0\0\0\u01cbF\1\0\0\0\u01cc\u01cd\5\ufff0"+
		"\0\0\u01cdH\1\0\0\0\u01ce\u01cf\5\ufff1\0\0\u01cfJ\1\0\0\0\u01d0\u01d1"+
		"\t\0\0\0\u01d1L\1\0\0\0\u01d2\u01d4\3\61\23\0\u01d4N\1\0\0\0\u01d5\u01d8"+
		"\b\5\0\0\u01d7\u01d5\1\0\0\0\u01d8\u01d9\1\0\0\0\u01d9\u01d7\1\0\0\0\u01d9"+
		"\u01da\1\0\0\0\u01da\u01db\1\0\0\0\u01dbP\1\0\0\0\u01dc\u01dd\5*\0\0\u01dd"+
		"\u01de\5/\0\0\u01de\u01df\1\0\0\0\u01dfR\1\0\0\0\u01e0\u01e2\5*\0\0\u01e2"+
		"T\1\0\0\0\u01e3\u01e5\t\0\0\0\u01e5V\1\0\0\0\u01e6\u01e8\3\61\23\0\u01e8"+
		"X\1\0\0\0\u01e9\u01ec\b\6\0\0\u01eb\u01e9\1\0\0\0\u01ec\u01ed\1\0\0\0"+
		"\u01ed\u01eb\1\0\0\0\u01ed\u01ee\1\0\0\0\u01ee\u01ef\1\0\0\0\u01efZ\1"+
		"\0\0\0\u01f0\u01f2\5!\0\0\u01f2\u01f4\3I\37\0\u01f4\\\1\0\0\0\u01f5\u01f7"+
		"\5!\0\0\u01f7^\1\0\0\0\u01f8\u01fa\t\0\0\0\u01fa`\1\0\0\0\u01fb\u01fd"+
		"\3!\13\0\u01fdb\1\0\0\0\u01fe\u0200\3\61\23\0\u0200d\1\0\0\0\u0201\u0203"+
		"\3G\36\0\u0203\u0205\5!\0\0\u0205f\1\0\0\0\u0206\u0208\3G\36\0\u0208h"+
		"\1\0\0\0\u0209\u020c\b\7\0\0\u020b\u0209\1\0\0\0\u020c\u020d\1\0\0\0\u020d"+
		"\u020b\1\0\0\0\u020d\u020e\1\0\0\0\u020ej\1\0\0\0\u020f\u0215\5\\\0\0"+
		"\u0211\u0216\t\0\0\0\u0213\u0216\5\uffff\0\0\u0215\u0211\1\0\0\0\u0215"+
		"\u0213\1\0\0\0\u0216\u0217\1\0\0\0\u0217l\1\0\0\0\u0218\u021a\t\0\0\0"+
		"\u021an\1\0\0\0\u021b\u021d\3/\22\0\u021dp\1\0\0\0\u021e\u0220\3\61\23"+
		"\0\u0220r\1\0\0\0\u0221\u0223\3\17\2\0\u0223t\1\0\0\0\u0224\u0225\3\63"+
		"\24\0\u0225v\1\0\0\0\u0226\u0228\5|\0\0\u0228x\1\0\0\0\u0229\u022b\t\0"+
		"\0\0\u022bz\1\0\0\0\u022c\u022e\3\61\23\0\u022e|\1\0\0\0\u022f\u0231\3"+
		"G\36\0\u0231\u0233\5!\0\0\u0233~\1\0\0\0\u0234\u0236\3G\36\0\u0236\u0080"+
		"\1\0\0\0\u0237\u023a\b\b\0\0\u0239\u0237\1\0\0\0\u023a\u023b\1\0\0\0\u023b"+
		"\u0239\1\0\0\0\u023b\u023c\1\0\0\0\u023c\u023d\1\0\0\0\u023d\u0082\1\0"+
		"\0\0\u023e\u0244\5\\\0\0\u0240\u0245\t\0\0\0\u0242\u0245\5\uffff\0\0\u0244"+
		"\u0240\1\0\0\0\u0244\u0242\1\0\0\0\u0245\u0246\1\0\0\0\u0246\u0084\1\0"+
		"\0\0\u0247\u0248\5>\0\0\u0248\u0249\5>\0\0\u0249\u024a\1\0\0\0\u024a\u0086"+
		"\1\0\0\0\u024b\u024d\5>\0\0\u024d\u0088\1\0\0\0\u024e\u0250\t\0\0\0\u0250"+
		"\u008a\1\0\0\0\u0251\u0253\3\61\23\0\u0253\u008c\1\0\0\0\u0254\u0256\3"+
		"G\36\0\u0256\u0258\5!\0\0\u0258\u008e\1\0\0\0\u0259\u025b\3G\36\0\u025b"+
		"\u0090\1\0\0\0\u025c\u025f\b\t\0\0\u025e\u025c\1\0\0\0\u025f\u0260\1\0"+
		"\0\0\u0260\u025e\1\0\0\0\u0260\u0261\1\0\0\0\u0261\u0262\1\0\0\0\u0262"+
		"\u0092\1\0\0\0\u0263\u0269\5\\\0\0\u0265\u026a\t\0\0\0\u0267\u026a\5\uffff"+
		"\0\0\u0269\u0265\1\0\0\0\u0269\u0267\1\0\0\0\u026a\u026b\1\0\0\0\u026b"+
		"\u0094\1\0\0\0\u026c\u026d\5%\0\0\u026d\u026e\5>\0\0\u026e\u026f\1\0\0"+
		"\0\u026f\u0096\1\0\0\0\u0270\u0272\5%\0\0\u0272\u0098\1\0\0\0\u0273\u0275"+
		"\t\0\0\0\u0275\u009a\1\0\0\0\u0276\u0278\3\61\23\0\u0278\u009c\1\0\0\0"+
		"\u0279\u027b\3\21\3\0\u027b\u009e\1\0\0\0\u027c\u027e\3\17\2\0\u027e\u00a0"+
		"\1\0\0\0\u027f\u0281\3\23\4\0\u0281\u00a2\1\0\0\0\u0282\u0284\3\13\0\0"+
		"\u0284\u00a4\1\0\0\0\u0285\u0287\3\r\1\0\u0287\u00a6\1\0\0\0\u0288\u028a"+
		"\3\33\b\0\u028a\u00a8\1\0\0\0\u028b\u028d\3\35\t\0\u028d\u00aa\1\0\0\0"+
		"\u028e\u0290\3\27\6\0\u0290\u00ac\1\0\0\0\u0291\u0293\3\37\n\0\u0293\u00ae"+
		"\1\0\0\0\u0294\u0296\3/\22\0\u0296\u00b0\1\0\0\0\u0297\u0298\5s\0\0\u0298"+
		"\u0299\5u\0\0\u0299\u029a\5p\0\0\u029a\u029b\5e\0\0\u029b\u029c\5r\0\0"+
		"\u029c\u00b2\1\0\0\0\u029d\u029e\5i\0\0\u029e\u029f\5f\0\0\u029f\u00b4"+
		"\1\0\0\0\u02a0\u02a1\5e\0\0\u02a1\u02a2\5l\0\0\u02a2\u02a3\5s\0\0\u02a3"+
		"\u02a4\5e\0\0\u02a4\u02a5\5i\0\0\u02a5\u02a6\5f\0\0\u02a6\u00b6\1\0\0"+
		"\0\u02a7\u02a8\5e\0\0\u02a8\u02a9\5n\0\0\u02a9\u02aa\5d\0\0\u02aa\u02ab"+
		"\5i\0\0\u02ab\u02ac\5f\0\0\u02ac\u00b8\1\0\0\0\u02ad\u02ae\5e\0\0\u02ae"+
		"\u02af\5l\0\0\u02af\u02b0\5s\0\0\u02b0\u02b1\5e\0\0\u02b1\u00ba\1\0\0"+
		"\0\u02b2\u02b3\5e\0\0\u02b3\u02b4\5n\0\0\u02b4\u02b5\5d\0\0\u02b5\u00bc"+
		"\1\0\0\0\u02b6\u02b7\5.\0\0\u02b7\u02b8\5.\0\0\u02b8\u02b9\5.\0\0\u02b9"+
		"\u00be\1\0\0\0\u02ba\u02bb\5!\0\0\u02bb\u00c0\1\0\0\0\u02bc\u02bd\5|\0"+
		"\0\u02bd\u02be\5|\0\0\u02be\u00c2\1\0\0\0\u02bf\u02c0\5&\0\0\u02c0\u02c1"+
		"\5&\0\0\u02c1\u00c4\1\0\0\0\u02c2\u02c3\5;\0\0\u02c3\u00c6\1\0\0\0\u02c4"+
		"\u02ca\5\\\0\0\u02c6\u02c9\b\n\0\0\u02c8\u02c6\1\0\0\0\u02c9\u02cc\1\0"+
		"\0\0\u02ca\u02c8\1\0\0\0\u02ca\u02cb\1\0\0\0\u02cb\u00c8\1\0\0\0\u02cc"+
		"\u02ca\1\0\0\0\u02cd\u02ce\5@\0\0\u02ce\u02cf\5e\0\0\u02cf\u02d0\5n\0"+
		"\0\u02d0\u02d1\5d\0\0\u02d1\u00ca\1\0\0\0\u02d2\u02d4\5@\0\0\u02d4\u02d5"+
		"\3\63\24\0\u02d5\u00cc\1\0\0\0\u02d6\u02d8\3\63\24\0\u02d8\u00ce\1\0\0"+
		"\0\u02d9\u02db\3I\37\0\u02db\u00d0\1\0\0\0\u02dc\u02ea\5\"\0\0\u02de\u02e9"+
		"\b\13\0\0\u02e0\u02e6\5\\\0\0\u02e2\u02e7\t\0\0\0\u02e4\u02e7\5\uffff"+
		"\0\0\u02e6\u02e2\1\0\0\0\u02e6\u02e4\1\0\0\0\u02e7\u02e9\1\0\0\0\u02e8"+
		"\u02de\1\0\0\0\u02e8\u02e0\1\0\0\0\u02e9\u02ec\1\0\0\0\u02ea\u02e8\1\0"+
		"\0\0\u02ea\u02eb\1\0\0\0\u02eb\u02ed\1\0\0\0\u02ec\u02ea\1\0\0\0\u02ed"+
		"\u02ef\5\"\0\0\u02ef\u00d2\1\0\0\0\u02f0\u02f2\t\0\0\0\u02f2\u00d4\1\0"+
		"\0\0\u02f3\u02f5\3\61\23\0\u02f5\u00d6\1\0\0\0\u02f6\u02f8\3G\36\0\u02f8"+
		"\u02fa\5!\0\0\u02fa\u00d8\1\0\0\0\u02fb\u02fd\3G\36\0\u02fd\u00da\1\0"+
		"\0\0\u02fe\u0301\b\f\0\0\u0300\u02fe\1\0\0\0\u0301\u0302\1\0\0\0\u0302"+
		"\u0300\1\0\0\0\u0302\u0303\1\0\0\0\u0303\u0304\1\0\0\0\u0304\u00dc\1\0"+
		"\0\0\u0305\u030b\5\\\0\0\u0307\u030c\t\0\0\0\u0309\u030c\5\uffff\0\0\u030b"+
		"\u0307\1\0\0\0\u030b\u0309\1\0\0\0\u030c\u030d\1\0\0\0\u030d\u00de\1\0"+
		"\0\0\u030e\u0310\5\"\0\0\u0310\u00e0\1\0\0\0\u0311\u0313\t\0\0\0\u0313"+
		"\u00e2\1\0\0\0\u0314\u0316\3/\22\0\u0316\u00e4\1\0\0\0\u0317\u0319\3\61"+
		"\23\0\u0319\u00e6\1\0\0\0\u031a\u031b\3C\34\0\u031b\u00e8\1\0\0\0\u031c"+
		"\u031e\3\17\2\0\u031e\u00ea\1\0\0\0\u031f\u0321\3/\22\0\u0321\u00ec\1"+
		"\0\0\0\u0322\u0324\3\61\23\0\u0324\u00ee\1\0\0\0\u0325\u0327\3C\34\0\u0327"+
		"\u00f0\1\0\0\0*\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\b\1\t\1\n\1\u010b\1\u013d"+
		"\1\u0143\1\u014a\1\u014e\1\u0157\1\u016c\1\u0178\1\u0181\1\u018a\1\u0191"+
		"\1\u0198\1\u01a1\1\u01aa\1\u01b3\1\u01be\1\u01ca\1\u01d9\1\u01ed\1\u020d"+
		"\1\u0215\1\u023b\1\u0244\1\u0260\1\u0269\1\u02ca\1\u02e6\1\u02e8\1\u02ea"+
		"\1\u0302\1\u030b\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}