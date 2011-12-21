// $ANTLR ANTLRVersion> GroupHighlighterLexerBase.java generatedTimestamp>

package org.antlr.works.editor.st4.highlighter4;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused"})
public class GroupHighlighterLexerBase extends Lexer {
	public static final int
		OPEN_DELIMITER=3, CLOSE_DELIMITER=4, LPAREN=5, RPAREN=6, COMMA=7, DOT=8, 
		COLON=9, DEFINED=10, EQUALS=11, AT=12, LBRACK=13, RBRACK=14, LBRACE=15, 
		RBRACE=16, GROUP=17, DEFAULT=18, IMPORT=19, TRUE=20, FALSE=21, DELIMITERS=22, 
		WS=23, NEWLINE=24, ID=25, STRING=26, BIGSTRING=27, BIGSTRINGLINE=28, LINE_COMMENT=29, 
		COMMENT=30, ANYCHAR=31, BlockComment_NEWLINE=32, CONTINUE_COMMENT=33, 
		END_COMMENT=34, BLOCK_COMMENT_STAR=35, BlockComment_ANYCHAR=36, TemplateComment_NEWLINE=37, 
		TemplateComment_CONTINUE_COMMENT=38, TemplateComment_END_COMMENT=39, TemplateComment_BLOCK_COMMENT_BANG=40, 
		TemplateComment_ANYCHAR=41, AnonymousTemplate_ID=42, AnonymousTemplate_WS=43, 
		AnonymousTemplate_RBRACE=44, AnonymousTemplate_COMMA=45, AnonymousTemplate_NEWLINE=46, 
		AnonymousTemplate_COMMENT=47, AnonymousTemplate_OPEN_DELIMITER=48, TEXT=49, 
		ESCAPE_RBRACE=50, AnonymousTemplate_ANYCHAR=51, AnonymousTemplateParameters_WS=52, 
		AnonymousTemplateParameters_NEWLINE=53, AnonymousTemplateParameters_COMMA=54, 
		TEMPLATE_PARAMETER=55, PIPE=56, AnonymousTemplateParameters_ANYCHAR=57, 
		BigStringTemplate_NEWLINE=58, BigStringTemplate_COMMENT=59, BigStringTemplate_OPEN_DELIMITER=60, 
		BigStringTemplate_TEXT=61, BigStringTemplate_ESCAPE=62, BigStringTemplate_END=63, 
		BigStringTemplate_LANGLE=64, BigStringTemplate_ANYCHAR=65, BigStringLineTemplate_NEWLINE=66, 
		BigStringLineTemplate_COMMENT=67, BigStringLineTemplate_OPEN_DELIMITER=68, 
		BigStringLineTemplate_TEXT=69, BigStringLineTemplate_ESCAPE=70, BigStringLineTemplate_END=71, 
		BigStringLineTemplate_PERCENT=72, BigStringLineTemplate_ANYCHAR=73, TemplateExpression_NEWLINE=74, 
		TemplateExpression_AT=75, TemplateExpression_DOT=76, TemplateExpression_COMMA=77, 
		TemplateExpression_COLON=78, TemplateExpression_LPAREN=79, TemplateExpression_RPAREN=80, 
		TemplateExpression_LBRACK=81, TemplateExpression_RBRACK=82, TemplateExpression_EQUALS=83, 
		TemplateExpression_LBRACE=84, TemplateExpression_WS=85, SUPER=86, IF=87, 
		ELSEIF=88, ENDIF=89, ELSE=90, END=91, FIRST=92, LAST=93, REST=94, TRUNC=95, 
		STRIP=96, TRIM=97, LENGTH=98, STRLEN=99, REVERSE=100, ELLIPSIS=101, NOT=102, 
		OR=103, AND=104, SEMI=105, ESCAPE=106, REGION_ID=107, TemplateExpression_ID=108, 
		TemplateExpression_CLOSE_DELIMITER=109, TemplateExpression_STRING=110, 
		TemplateExpression_ANYCHAR=111, StringTemplate_NEWLINE=112, StringTemplate_COMMENT=113, 
		StringTemplate_OPEN_DELIMITER=114, StringTemplate_TEXT=115, StringTemplate_ESCAPE=116, 
		StringTemplate_END=117, StringTemplate_ANYCHAR=118, DelimitersOpenSpec_WS=119, 
		DelimitersOpenSpec_NEWLINE=120, DelimitersOpenSpec_DELIMITER_STRING=121, 
		DelimitersOpenSpec_COMMA=122, DelimitersCloseSpec_WS=123, DelimitersCloseSpec_NEWLINE=124, 
		DelimitersCloseSpec_DELIMITER_STRING=125;
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
		"OPEN_DELIMITER", "CLOSE_DELIMITER", "(", ")", ",", ".", ":", "::=", "=", 
		"@", "]", "[", "LBRACE", "}", "group", "default", "import", "true", "false", 
		"DELIMITERS", "WS", "NEWLINE", "ID", "STRING", "BIGSTRING", "BIGSTRINGLINE", 
		"LINE_COMMENT", "COMMENT", "ANYCHAR", "BlockComment_NEWLINE", "CONTINUE_COMMENT", 
		"END_COMMENT", "BLOCK_COMMENT_STAR", "BlockComment_ANYCHAR", "TemplateComment_NEWLINE", 
		"TemplateComment_CONTINUE_COMMENT", "TemplateComment_END_COMMENT", "TemplateComment_BLOCK_COMMENT_BANG", 
		"TemplateComment_ANYCHAR", "AnonymousTemplate_ID", "AnonymousTemplate_WS", 
		"AnonymousTemplate_RBRACE", "AnonymousTemplate_COMMA", "AnonymousTemplate_NEWLINE", 
		"AnonymousTemplate_COMMENT", "AnonymousTemplate_OPEN_DELIMITER", "TEXT", 
		"ESCAPE_RBRACE", "AnonymousTemplate_ANYCHAR", "AnonymousTemplateParameters_WS", 
		"AnonymousTemplateParameters_NEWLINE", "AnonymousTemplateParameters_COMMA", 
		"TEMPLATE_PARAMETER", "PIPE", "AnonymousTemplateParameters_ANYCHAR", "BigStringTemplate_NEWLINE", 
		"BigStringTemplate_COMMENT", "BigStringTemplate_OPEN_DELIMITER", "BigStringTemplate_TEXT", 
		"BigStringTemplate_ESCAPE", "BigStringTemplate_END", "BigStringTemplate_LANGLE", 
		"BigStringTemplate_ANYCHAR", "BigStringLineTemplate_NEWLINE", "BigStringLineTemplate_COMMENT", 
		"BigStringLineTemplate_OPEN_DELIMITER", "BigStringLineTemplate_TEXT", 
		"BigStringLineTemplate_ESCAPE", "BigStringLineTemplate_END", "BigStringLineTemplate_PERCENT", 
		"BigStringLineTemplate_ANYCHAR", "TemplateExpression_NEWLINE", "TemplateExpression_AT", 
		"TemplateExpression_DOT", "TemplateExpression_COMMA", "TemplateExpression_COLON", 
		"TemplateExpression_LPAREN", "TemplateExpression_RPAREN", "TemplateExpression_LBRACK", 
		"TemplateExpression_RBRACK", "TemplateExpression_EQUALS", "TemplateExpression_LBRACE", 
		"TemplateExpression_WS", "SUPER", "IF", "ELSEIF", "ENDIF", "ELSE", "END", 
		"FIRST", "LAST", "REST", "TRUNC", "STRIP", "TRIM", "LENGTH", "STRLEN", 
		"REVERSE", "ELLIPSIS", "NOT", "OR", "AND", "SEMI", "ESCAPE", "REGION_ID", 
		"TemplateExpression_ID", "TemplateExpression_CLOSE_DELIMITER", "TemplateExpression_STRING", 
		"TemplateExpression_ANYCHAR", "StringTemplate_NEWLINE", "StringTemplate_COMMENT", 
		"StringTemplate_OPEN_DELIMITER", "StringTemplate_TEXT", "StringTemplate_ESCAPE", 
		"StringTemplate_END", "StringTemplate_ANYCHAR", "DelimitersOpenSpec_WS", 
		"DelimitersOpenSpec_NEWLINE", "DelimitersOpenSpec_DELIMITER_STRING", "DelimitersOpenSpec_COMMA", 
		"DelimitersCloseSpec_WS", "DelimitersCloseSpec_NEWLINE", "DelimitersCloseSpec_DELIMITER_STRING"
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
		"AnonymousTemplate_ID", "AnonymousTemplate_WS", "AnonymousTemplate_RBRACE", 
		"AnonymousTemplate_COMMA", "AnonymousTemplate_NEWLINE", "AnonymousTemplate_COMMENT", 
		"AnonymousTemplate_OPEN_DELIMITER", "TEXT", "ESCAPE_RBRACE", "AnonymousTemplate_ANYCHAR", 
		"AnonymousTemplateParameters_WS", "AnonymousTemplateParameters_NEWLINE", 
		"AnonymousTemplateParameters_COMMA", "TEMPLATE_PARAMETER", "PIPE", "AnonymousTemplateParameters_ANYCHAR", 
		"BigStringTemplate_NEWLINE", "BigStringTemplate_COMMENT", "BigStringTemplate_OPEN_DELIMITER", 
		"BigStringTemplate_TEXT", "BigStringTemplate_ESCAPE", "BigStringTemplate_END", 
		"BigStringTemplate_LANGLE", "BigStringTemplate_ANYCHAR", "BigStringLineTemplate_NEWLINE", 
		"BigStringLineTemplate_COMMENT", "BigStringLineTemplate_OPEN_DELIMITER", 
		"BigStringLineTemplate_TEXT", "BigStringLineTemplate_ESCAPE", "BigStringLineTemplate_END", 
		"BigStringLineTemplate_PERCENT", "BigStringLineTemplate_ANYCHAR", "TemplateExpression_NEWLINE", 
		"TemplateExpression_AT", "TemplateExpression_DOT", "TemplateExpression_COMMA", 
		"TemplateExpression_COLON", "TemplateExpression_LPAREN", "TemplateExpression_RPAREN", 
		"TemplateExpression_LBRACK", "TemplateExpression_RBRACK", "TemplateExpression_EQUALS", 
		"TemplateExpression_LBRACE", "TemplateExpression_WS", "SUPER", "IF", "ELSEIF", 
		"ENDIF", "ELSE", "END", "FIRST", "LAST", "REST", "TRUNC", "STRIP", "TRIM", 
		"LENGTH", "STRLEN", "REVERSE", "ELLIPSIS", "NOT", "OR", "AND", "SEMI", 
		"ESCAPE", "REGION_ID", "TemplateExpression_ID", "TemplateExpression_CLOSE_DELIMITER", 
		"TemplateExpression_STRING", "TemplateExpression_ANYCHAR", "StringTemplate_NEWLINE", 
		"StringTemplate_COMMENT", "StringTemplate_OPEN_DELIMITER", "StringTemplate_TEXT", 
		"StringTemplate_ESCAPE", "StringTemplate_END", "StringTemplate_ANYCHAR", 
		"DelimitersOpenSpec_WS", "DelimitersOpenSpec_NEWLINE", "DelimitersOpenSpec_DELIMITER_STRING", 
		"DelimitersOpenSpec_COMMA", "DelimitersCloseSpec_WS", "DelimitersCloseSpec_NEWLINE", 
		"DelimitersCloseSpec_DELIMITER_STRING"
	};


	public GroupHighlighterLexerBase(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN);
	}

	public String getGrammarFileName() { return "GroupHighlighterLexerBase.java"; }

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

			case 21 : STRING_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 22 : BIGSTRING_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 23 : BIGSTRINGLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

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

			case 44 : AnonymousTemplate_WS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 45 : AnonymousTemplate_RBRACE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 47 : AnonymousTemplate_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 48 : AnonymousTemplate_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 49 : AnonymousTemplate_OPEN_DELIMITER_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 51 : ESCAPE_RBRACE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 52 : AnonymousTemplate_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 53 : AnonymousTemplateParameters_WS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 54 : AnonymousTemplateParameters_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 55 : AnonymousTemplateParameters_COMMA_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 57 : PIPE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 58 : AnonymousTemplateParameters_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 59 : BigStringTemplate_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 60 : BigStringTemplate_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 61 : BigStringTemplate_OPEN_DELIMITER_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 62 : BigStringTemplate_TEXT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 63 : BigStringTemplate_ESCAPE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 64 : BigStringTemplate_END_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 65 : BigStringTemplate_LANGLE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 66 : BigStringTemplate_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 67 : BigStringLineTemplate_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 68 : BigStringLineTemplate_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 69 : BigStringLineTemplate_OPEN_DELIMITER_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 70 : BigStringLineTemplate_TEXT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 71 : BigStringLineTemplate_ESCAPE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 72 : BigStringLineTemplate_END_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 73 : BigStringLineTemplate_PERCENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 74 : BigStringLineTemplate_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 75 : TemplateExpression_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 76 : TemplateExpression_AT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 77 : TemplateExpression_DOT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 78 : TemplateExpression_COMMA_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 79 : TemplateExpression_COLON_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 80 : TemplateExpression_LPAREN_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 81 : TemplateExpression_RPAREN_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 82 : TemplateExpression_LBRACK_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 83 : TemplateExpression_RBRACK_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 84 : TemplateExpression_EQUALS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 85 : TemplateExpression_LBRACE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 86 : TemplateExpression_WS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 109 : TemplateExpression_ID_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 110 : TemplateExpression_CLOSE_DELIMITER_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 111 : TemplateExpression_STRING_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 112 : TemplateExpression_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 113 : StringTemplate_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 114 : StringTemplate_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 115 : StringTemplate_OPEN_DELIMITER_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 116 : StringTemplate_TEXT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 117 : StringTemplate_ESCAPE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 118 : StringTemplate_END_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 119 : StringTemplate_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 120 : DelimitersOpenSpec_WS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 121 : DelimitersOpenSpec_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 123 : DelimitersOpenSpec_COMMA_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 124 : DelimitersCloseSpec_WS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 125 : DelimitersCloseSpec_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 126 : DelimitersCloseSpec_DELIMITER_STRING_action((ParserRuleContext<?>)_localctx, actionIndex); break;
		}
	}
	public void TemplateExpression_LPAREN_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 48 : type =  LPAREN; break;
		}
	}
	public void TemplateComment_BLOCK_COMMENT_BANG_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 13 : type =  COMMENT; break;
		}
	}
	public void TemplateComment_END_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 12 : type =  COMMENT; popMode(); break;
		}
	}
	public void BigStringTemplate_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 28 : type =  COMMENT; pushMode(TemplateComment); break;
		}
	}
	public void StringTemplate_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 59 : type =  NEWLINE; break;
		}
	}
	public void StringTemplate_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 60 : type =  COMMENT; pushMode(TemplateComment); break;
		}
	}
	public void AnonymousTemplate_WS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 15 : type =  WS; break;
		}
	}
	public void BigStringLineTemplate_PERCENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 41 : type =  TEXT; break;
		}
	}
	public void END_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 7 : type =  COMMENT; popMode(); break;
		}
	}
	public void BigStringTemplate_OPEN_DELIMITER_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 29 : type =  OPEN_DELIMITER; pushMode(TemplateExpression); break;
		}
	}
	public void LBRACE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 0 : pushMode(AnonymousTemplate); break;
		}
	}
	public void BigStringLineTemplate_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 35 : type =  NEWLINE; break;
		}
	}
	public void StringTemplate_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 65 : type =  ANYCHAR; break;
		}
	}
	public void AnonymousTemplateParameters_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 23 : type =  NEWLINE; break;
		}
	}
	public void TemplateExpression_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 58 : type =  ANYCHAR; break;
		}
	}
	public void BigStringLineTemplate_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 42 : type =  ANYCHAR; break;
		}
	}
	public void TemplateComment_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 14 : type =  ANYCHAR; break;
		}
	}
	public void TemplateExpression_EQUALS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 52 : type =  EQUALS; break;
		}
	}
	public void StringTemplate_ESCAPE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 63 : type =  TEXT; break;
		}
	}
	public void TemplateExpression_CLOSE_DELIMITER_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 56 : type =  CLOSE_DELIMITER; popMode(); break;
		}
	}
	public void AnonymousTemplate_OPEN_DELIMITER_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 19 : type =  OPEN_DELIMITER; pushMode(TemplateExpression); break;
		}
	}
	public void AnonymousTemplate_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 21 : type =  ANYCHAR; break;
		}
	}
	public void BigStringTemplate_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 27 : type =  NEWLINE; break;
		}
	}
	public void DelimitersOpenSpec_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 67 : type =  NEWLINE; break;
		}
	}
	public void TemplateComment_CONTINUE_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 11 : type =  COMMENT; break;
		}
	}
	public void BigStringLineTemplate_OPEN_DELIMITER_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 37 : type =  OPEN_DELIMITER; pushMode(TemplateExpression); break;
		}
	}
	public void TemplateExpression_COMMA_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 46 : type =  COMMA; break;
		}
	}
	public void TemplateExpression_RPAREN_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 49 : type =  RPAREN; break;
		}
	}
	public void BIGSTRING_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 2 : pushMode(BigStringTemplate); break;
		}
	}
	public void BigStringLineTemplate_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 36 : type =  COMMENT; pushMode(TemplateComment); break;
		}
	}
	public void AnonymousTemplate_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 17 : type =  NEWLINE; break;
		}
	}
	public void CONTINUE_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 6 : type =  COMMENT; break;
		}
	}
	public void PIPE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 25 : popMode(); break;
		}
	}
	public void TemplateExpression_LBRACE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 53 : type =  LBRACE; pushMode(AnonymousTemplate); break;
		}
	}
	public void TemplateExpression_ID_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 55 : type =  ID; break;
		}
	}
	public void COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 4 : pushMode(BlockComment); break;
		}
	}
	public void DelimitersCloseSpec_WS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 69 : type =  WS; break;
		}
	}
	public void TemplateExpression_LBRACK_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 50 : type =  LBRACK; break;
		}
	}
	public void BigStringTemplate_TEXT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 30 : type =  TEXT; break;
		}
	}
	public void AnonymousTemplate_RBRACE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 16 : type =  RBRACE; popMode(); break;
		}
	}
	public void TemplateExpression_RBRACK_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 51 : type =  RBRACK; break;
		}
	}
	public void AnonymousTemplateParameters_COMMA_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 24 : type =  COMMA; break;
		}
	}
	public void BigStringTemplate_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 34 : type =  ANYCHAR; break;
		}
	}
	public void TemplateExpression_AT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 44 : type =  AT; break;
		}
	}
	public void BigStringLineTemplate_TEXT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 38 : type =  TEXT; break;
		}
	}
	public void AnonymousTemplate_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 18 : type =  COMMENT; pushMode(TemplateComment); break;
		}
	}
	public void BigStringLineTemplate_ESCAPE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 39 : type =  TEXT; break;
		}
	}
	public void TemplateExpression_WS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 54 : type =  WS; break;
		}
	}
	public void TemplateExpression_DOT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 45 : type =  DOT; break;
		}
	}
	public void TemplateExpression_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 43 : type =  NEWLINE; break;
		}
	}
	public void DelimitersOpenSpec_WS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 66 : type =  WS; break;
		}
	}
	public void StringTemplate_OPEN_DELIMITER_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 61 : type =  OPEN_DELIMITER; pushMode(TemplateExpression); break;
		}
	}
	public void BigStringLineTemplate_END_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 40 : popMode(); break;
		}
	}
	public void TemplateExpression_STRING_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 57 : type =  STRING; break;
		}
	}
	public void ESCAPE_RBRACE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 20 : type =  TEXT; break;
		}
	}
	public void BigStringTemplate_ESCAPE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 31 : type =  TEXT; break;
		}
	}
	public void BIGSTRINGLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 3 : pushMode(BigStringLineTemplate); break;
		}
	}
	public void BlockComment_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 9 : type =  ANYCHAR; break;
		}
	}
	public void TemplateComment_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 10 : type =  NEWLINE; break;
		}
	}
	public void StringTemplate_TEXT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 62 : type =  TEXT; break;
		}
	}
	public void AnonymousTemplateParameters_WS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 22 : type =  WS; break;
		}
	}
	public void AnonymousTemplateParameters_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 26 : type =  ANYCHAR; break;
		}
	}
	public void StringTemplate_END_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 64 : type =  TEXT; popMode(); break;
		}
	}
	public void TemplateExpression_COLON_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 47 : type =  COLON; break;
		}
	}
	public void DelimitersCloseSpec_DELIMITER_STRING_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 71 : popMode(); break;
		}
	}
	public void BLOCK_COMMENT_STAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 8 : type =  COMMENT; break;
		}
	}
	public void DelimitersCloseSpec_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 70 : type =  NEWLINE; break;
		}
	}
	public void DelimitersOpenSpec_COMMA_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 68 : type =  COMMA; mode(DelimitersCloseSpec); break;
		}
	}
	public void BigStringTemplate_LANGLE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 33 : type =  TEXT; break;
		}
	}
	public void STRING_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 1 : pushMode(StringTemplate); break;
		}
	}
	public void BlockComment_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 5 : type =  NEWLINE; break;
		}
	}
	public void BigStringTemplate_END_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 32 : popMode(); break;
		}
	}

	public static final String _serializedATN =
		"\2}\u03ca\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff"+
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
		"\7o\2p\7p\2q\7q\2r\7r\2s\7s\2t\7t\2u\7u\2v\7v\2w\7w\2x\7x\2y\7y\2z\7z"+
		"\2{\7{\2|\7|\2}\7}\2~\7~\1\0\1\0\1\1\1\1\1\2\1\2\1\3\1\3\1\4\1\4\1\5\1"+
		"\5\1\5\1\5\1\6\1\6\1\7\1\7\1\b\1\b\1\t\1\t\1\n\0\1\n\0\3\n\b\n\1\n\1\13"+
		"\1\13\1\f\1\f\1\f\1\f\1\f\1\f\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\16\1\16"+
		"\1\16\1\16\1\16\1\16\1\16\1\17\1\17\1\17\1\17\1\17\1\20\1\20\1\20\1\20"+
		"\1\20\1\20\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21"+
		"\0\3\21\b\21\1\22\0\4\22\b\22\13\22\1\22\1\23\0\1\23\0\3\23\b\23\1\23"+
		"\0\3\23\b\23\1\24\0\1\24\0\5\24\b\24\n\24\1\24\t\24\1\25\0\1\25\1\26\1"+
		"\26\1\26\1\26\1\27\1\27\1\27\1\27\1\30\1\30\1\30\1\30\0\5\30\b\30\n\30"+
		"\1\30\t\30\1\31\1\31\1\31\1\31\1\32\0\5\32\b\32\n\32\1\32\t\32\1\32\0"+
		"\1\32\0\5\32\b\32\n\32\1\32\t\32\1\32\0\1\32\0\5\32\b\32\n\32\1\32\t\32"+
		"\1\32\1\32\5\32\b\32\n\32\1\32\t\32\1\32\0\5\32\b\32\n\32\1\32\t\32\1"+
		"\32\1\32\1\33\0\5\33\b\33\n\33\1\33\t\33\1\33\0\1\33\0\5\33\b\33\n\33"+
		"\1\33\t\33\1\33\0\1\33\0\5\33\b\33\n\33\1\33\t\33\1\33\1\33\1\34\0\1\34"+
		"\0\4\34\b\34\13\34\1\34\1\34\1\34\1\35\0\1\35\0\1\35\0\1\35\0\3\35\b\35"+
		"\1\36\1\36\1\37\1\37\1 \1 \1!\0\1!\1\"\0\4\"\b\"\13\"\1\"\1\"\1#\1#\1"+
		"#\1#\1$\0\1$\1%\0\1%\1&\0\1&\1\'\0\4\'\b\'\13\'\1\'\1\'\1(\0\1(\0\1(\1"+
		")\0\1)\1*\0\1*\1+\1+\1,\0\1,\1-\0\1-\1.\1.\1/\0\1/\1\60\0\1\60\0\1\60"+
		"\1\61\0\1\61\1\62\0\4\62\b\62\13\62\1\62\1\63\0\1\63\0\1\63\0\3\63\b\63"+
		"\1\63\1\64\0\1\64\1\65\0\1\65\1\66\0\1\66\1\67\0\1\67\18\18\19\0\19\1"+
		":\0\1:\1;\0\1;\1<\0\1<\0\1<\1=\0\1=\1>\0\4>\b>\13>\1>\1>\1?\0\1?\0\1?"+
		"\0\3?\b?\1?\1@\1@\1@\1@\1A\0\1A\1B\0\1B\1C\0\1C\1D\0\1D\0\1D\1E\0\1E\1"+
		"F\0\4F\bF\13F\1F\1F\1G\0\1G\0\1G\0\3G\bG\1G\1H\1H\1H\1H\1I\0\1I\1J\0\1"+
		"J\1K\0\1K\1L\0\1L\1M\0\1M\1N\0\1N\1O\0\1O\1P\0\1P\1Q\0\1Q\1R\0\1R\1S\0"+
		"\1S\1T\0\1T\1U\0\1U\1V\0\1V\1W\1W\1W\1W\1W\1W\1X\1X\1X\1Y\1Y\1Y\1Y\1Y"+
		"\1Y\1Y\1Z\1Z\1Z\1Z\1Z\1Z\1[\1[\1[\1[\1[\1\\\1\\\1\\\1\\\1]\1]\1]\1]\1"+
		"]\1]\1]\0\5]\b]\n]\1]\t]\1]\1]\1^\1^\1^\1^\1^\1^\0\5^\b^\n^\1^\t^\1^\1"+
		"^\1_\1_\1_\1_\1_\1_\0\5_\b_\n_\1_\t_\1_\1_\1`\1`\1`\1`\1`\1`\1`\0\5`\b"+
		"`\n`\1`\t`\1`\1`\1a\1a\1a\1a\1a\1a\1a\0\5a\ba\na\1a\ta\1a\1a\1b\1b\1b"+
		"\1b\1b\1b\0\5b\bb\nb\1b\tb\1b\1b\1c\1c\1c\1c\1c\1c\1c\1c\0\5c\bc\nc\1"+
		"c\tc\1c\1c\1d\1d\1d\1d\1d\1d\1d\1d\0\5d\bd\nd\1d\td\1d\1d\1e\1e\1e\1e"+
		"\1e\1e\1e\1e\1e\0\5e\be\ne\1e\te\1e\1e\1f\1f\1f\1f\1g\1g\1h\1h\1h\1i\1"+
		"i\1i\1j\1j\1k\0\1k\0\5k\bk\nk\1k\tk\1l\0\1l\1l\1m\0\1m\1n\0\1n\1o\0\1"+
		"o\0\1o\0\1o\0\1o\0\3o\bo\5o\bo\no\1o\to\1o\0\1o\1p\0\1p\1q\0\1q\1r\0\1"+
		"r\0\1r\1s\0\1s\1t\0\4t\bt\13t\1t\1t\1u\0\1u\0\1u\0\3u\bu\1u\1v\0\1v\1"+
		"w\0\1w\1x\0\1x\1y\0\1y\1z\1z\1{\0\1{\1|\0\1|\1}\0\1}\1~\0\1~\177\13\5"+
		"\uffff\r\6\uffff\17\7\uffff\21\b\uffff\23\t\uffff\25\n\uffff\27\13\uffff"+
		"\31\f\uffff\33\r\uffff\35\16\uffff\37\17\0!\20\uffff#\21\uffff%\22\uffff"+
		"\'\23\uffff)\24\uffff+\25\uffff-\26\uffff/\27\uffff\61\30\uffff\63\31"+
		"\uffff\65\32\1\67\33\29\34\3;\35\uffff=\36\4?\0\uffffA\0\uffffC\0\uffff"+
		"E\0\uffffG\3\uffffI\4\uffffK\37\uffffM \5O!\6Q\"\7S#\bU$\tW%\nY&\13[\'"+
		"\f](\r_)\16a*\uffffc+\17e,\20g-\uffffi.\21k/\22m\60\23o\61\uffffq\62\24"+
		"s\63\25u\64\26w\65\27y\66\30{\67\uffff}8\31\1779\32\u0081:\33\u0083;\34"+
		"\u0085<\35\u0087=\36\u0089>\37\u008b? \u008d@!\u008fA\"\u0091B#\u0093"+
		"C$\u0095D%\u0097E&\u0099F\'\u009bG(\u009dH)\u009fI*\u00a1J+\u00a3K,\u00a5"+
		"L-\u00a7M.\u00a9N/\u00abO\60\u00adP\61\u00afQ\62\u00b1R\63\u00b3S\64\u00b5"+
		"T\65\u00b7U\66\u00b9V\uffff\u00bbW\uffff\u00bdX\uffff\u00bfY\uffff\u00c1"+
		"Z\uffff\u00c3[\uffff\u00c5\\\uffff\u00c7]\uffff\u00c9^\uffff\u00cb_\uffff"+
		"\u00cd`\uffff\u00cfa\uffff\u00d1b\uffff\u00d3c\uffff\u00d5d\uffff\u00d7"+
		"e\uffff\u00d9f\uffff\u00dbg\uffff\u00ddh\uffff\u00dfi\uffff\u00e1j\uffff"+
		"\u00e3k\uffff\u00e5l\67\u00e7m8\u00e9n9\u00ebo:\u00edp;\u00efq<\u00f1"+
		"r=\u00f3s>\u00f5t?\u00f7u@\u00f9vA\u00fbwB\u00fdxC\u00ffy\uffff\u0101"+
		"zD\u0103{E\u0105|F\u0107}G\13\0\1\2\3\4\5\6\7\b\t\n\r\2\t\t  \3AZ__az"+
		"\5--\609AZ__az\2\n\n\r\r\3\n\n\r\r\"\"\3\n\n\r\r**\3\n\n\r\r!!\n\t\n\r"+
		"\r  ,,AZ\\\\__az}}\ufff0\ufff0\5\n\n\r\r>>\\\\\ufff0\ufff0\5\n\n\r\r%"+
		"%\\\\\ufff0\ufff0\1\ufff1\ufff1\2\"\"\\\\\5\n\n\r\r\"\"\\\\\ufff0\ufff0"+
		"\u0368\0\13\1\0\0\0\0\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1\0\0\0"+
		"\0\25\1\0\0\0\0\27\1\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0\0\0\37"+
		"\1\0\0\0\0!\1\0\0\0\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0\0\0+\1"+
		"\0\0\0\0-\1\0\0\0\0/\1\0\0\0\0\61\1\0\0\0\0\63\1\0\0\0\0\65\1\0\0\0\0"+
		"\67\1\0\0\0\09\1\0\0\0\0;\1\0\0\0\0=\1\0\0\0\0K\1\0\0\0\1M\1\0\0\0\1O"+
		"\1\0\0\0\1Q\1\0\0\0\1S\1\0\0\0\1U\1\0\0\0\2W\1\0\0\0\2Y\1\0\0\0\2[\1\0"+
		"\0\0\2]\1\0\0\0\2_\1\0\0\0\3a\1\0\0\0\3c\1\0\0\0\3e\1\0\0\0\3g\1\0\0\0"+
		"\3i\1\0\0\0\3k\1\0\0\0\3m\1\0\0\0\3o\1\0\0\0\3q\1\0\0\0\3s\1\0\0\0\4u"+
		"\1\0\0\0\4w\1\0\0\0\4y\1\0\0\0\4{\1\0\0\0\4}\1\0\0\0\4\177\1\0\0\0\5\u0081"+
		"\1\0\0\0\5\u0083\1\0\0\0\5\u0085\1\0\0\0\5\u0087\1\0\0\0\5\u0089\1\0\0"+
		"\0\5\u008b\1\0\0\0\5\u008d\1\0\0\0\5\u008f\1\0\0\0\6\u0091\1\0\0\0\6\u0093"+
		"\1\0\0\0\6\u0095\1\0\0\0\6\u0097\1\0\0\0\6\u0099\1\0\0\0\6\u009b\1\0\0"+
		"\0\6\u009d\1\0\0\0\6\u009f\1\0\0\0\7\u00a1\1\0\0\0\7\u00a3\1\0\0\0\7\u00a5"+
		"\1\0\0\0\7\u00a7\1\0\0\0\7\u00a9\1\0\0\0\7\u00ab\1\0\0\0\7\u00ad\1\0\0"+
		"\0\7\u00af\1\0\0\0\7\u00b1\1\0\0\0\7\u00b3\1\0\0\0\7\u00b5\1\0\0\0\7\u00b7"+
		"\1\0\0\0\7\u00b9\1\0\0\0\7\u00bb\1\0\0\0\7\u00bd\1\0\0\0\7\u00bf\1\0\0"+
		"\0\7\u00c1\1\0\0\0\7\u00c3\1\0\0\0\7\u00c5\1\0\0\0\7\u00c7\1\0\0\0\7\u00c9"+
		"\1\0\0\0\7\u00cb\1\0\0\0\7\u00cd\1\0\0\0\7\u00cf\1\0\0\0\7\u00d1\1\0\0"+
		"\0\7\u00d3\1\0\0\0\7\u00d5\1\0\0\0\7\u00d7\1\0\0\0\7\u00d9\1\0\0\0\7\u00db"+
		"\1\0\0\0\7\u00dd\1\0\0\0\7\u00df\1\0\0\0\7\u00e1\1\0\0\0\7\u00e3\1\0\0"+
		"\0\7\u00e5\1\0\0\0\7\u00e7\1\0\0\0\7\u00e9\1\0\0\0\7\u00eb\1\0\0\0\b\u00ed"+
		"\1\0\0\0\b\u00ef\1\0\0\0\b\u00f1\1\0\0\0\b\u00f3\1\0\0\0\b\u00f5\1\0\0"+
		"\0\b\u00f7\1\0\0\0\b\u00f9\1\0\0\0\t\u00fb\1\0\0\0\t\u00fd\1\0\0\0\t\u00ff"+
		"\1\0\0\0\t\u0101\1\0\0\0\n\u0103\1\0\0\0\n\u0105\1\0\0\0\n\u0107\1\0\0"+
		"\0\13\u0109\1\0\0\0\r\u010b\1\0\0\0\17\u010d\1\0\0\0\21\u010f\1\0\0\0"+
		"\23\u0111\1\0\0\0\25\u0113\1\0\0\0\27\u0117\1\0\0\0\31\u0119\1\0\0\0\33"+
		"\u011b\1\0\0\0\35\u011d\1\0\0\0\37\u011f\1\0\0\0!\u0126\1\0\0\0#\u0128"+
		"\1\0\0\0%\u012e\1\0\0\0\'\u0136\1\0\0\0)\u013d\1\0\0\0+\u0142\1\0\0\0"+
		"-\u0148\1\0\0\0/\u0159\1\0\0\0\61\u0165\1\0\0\0\63\u0167\1\0\0\0\65\u0170"+
		"\1\0\0\0\67\u0173\1\0\0\09\u0177\1\0\0\0;\u017b\1\0\0\0=\u0185\1\0\0\0"+
		"?\u018d\1\0\0\0A\u01b6\1\0\0\0C\u01cd\1\0\0\0E\u01df\1\0\0\0G\u01e1\1"+
		"\0\0\0I\u01e3\1\0\0\0K\u01e5\1\0\0\0M\u01e7\1\0\0\0O\u01ec\1\0\0\0Q\u01f1"+
		"\1\0\0\0S\u01f5\1\0\0\0U\u01f8\1\0\0\0W\u01fb\1\0\0\0Y\u0200\1\0\0\0["+
		"\u0205\1\0\0\0]\u020a\1\0\0\0_\u020d\1\0\0\0a\u0210\1\0\0\0c\u0212\1\0"+
		"\0\0e\u0215\1\0\0\0g\u0218\1\0\0\0i\u021a\1\0\0\0k\u021d\1\0\0\0m\u0222"+
		"\1\0\0\0o\u0227\1\0\0\0q\u022b\1\0\0\0s\u0234\1\0\0\0u\u0237\1\0\0\0w"+
		"\u023a\1\0\0\0y\u023d\1\0\0\0{\u0240\1\0\0\0}\u0242\1\0\0\0\177\u0245"+
		"\1\0\0\0\u0081\u0248\1\0\0\0\u0083\u024b\1\0\0\0\u0085\u0250\1\0\0\0\u0087"+
		"\u0255\1\0\0\0\u0089\u025a\1\0\0\0\u008b\u0263\1\0\0\0\u008d\u0267\1\0"+
		"\0\0\u008f\u026a\1\0\0\0\u0091\u026d\1\0\0\0\u0093\u0270\1\0\0\0\u0095"+
		"\u0275\1\0\0\0\u0097\u027a\1\0\0\0\u0099\u027f\1\0\0\0\u009b\u0288\1\0"+
		"\0\0\u009d\u028c\1\0\0\0\u009f\u028f\1\0\0\0\u00a1\u0292\1\0\0\0\u00a3"+
		"\u0295\1\0\0\0\u00a5\u0298\1\0\0\0\u00a7\u029b\1\0\0\0\u00a9\u029e\1\0"+
		"\0\0\u00ab\u02a1\1\0\0\0\u00ad\u02a4\1\0\0\0\u00af\u02a7\1\0\0\0\u00b1"+
		"\u02aa\1\0\0\0\u00b3\u02ad\1\0\0\0\u00b5\u02b0\1\0\0\0\u00b7\u02b3\1\0"+
		"\0\0\u00b9\u02b6\1\0\0\0\u00bb\u02bc\1\0\0\0\u00bd\u02bf\1\0\0\0\u00bf"+
		"\u02c6\1\0\0\0\u00c1\u02cc\1\0\0\0\u00c3\u02d1\1\0\0\0\u00c5\u02d5\1\0"+
		"\0\0\u00c7\u02e4\1\0\0\0\u00c9\u02f2\1\0\0\0\u00cb\u0300\1\0\0\0\u00cd"+
		"\u030f\1\0\0\0\u00cf\u031e\1\0\0\0\u00d1\u032c\1\0\0\0\u00d3\u033c\1\0"+
		"\0\0\u00d5\u034c\1\0\0\0\u00d7\u035d\1\0\0\0\u00d9\u0361\1\0\0\0\u00db"+
		"\u0363\1\0\0\0\u00dd\u0366\1\0\0\0\u00df\u0369\1\0\0\0\u00e1\u036b\1\0"+
		"\0\0\u00e3\u0374\1\0\0\0\u00e5\u0378\1\0\0\0\u00e7\u037b\1\0\0\0\u00e9"+
		"\u037e\1\0\0\0\u00eb\u0392\1\0\0\0\u00ed\u0395\1\0\0\0\u00ef\u0398\1\0"+
		"\0\0\u00f1\u039d\1\0\0\0\u00f3\u03a2\1\0\0\0\u00f5\u03a7\1\0\0\0\u00f7"+
		"\u03b0\1\0\0\0\u00f9\u03b3\1\0\0\0\u00fb\u03b6\1\0\0\0\u00fd\u03b9\1\0"+
		"\0\0\u00ff\u03bc\1\0\0\0\u0101\u03be\1\0\0\0\u0103\u03c1\1\0\0\0\u0105"+
		"\u03c4\1\0\0\0\u0107\u03c7\1\0\0\0\u0109\u010a\5(\0\0\u010a\f\1\0\0\0"+
		"\u010b\u010c\5)\0\0\u010c\16\1\0\0\0\u010d\u010e\5,\0\0\u010e\20\1\0\0"+
		"\0\u010f\u0110\5.\0\0\u0110\22\1\0\0\0\u0111\u0112\5:\0\0\u0112\24\1\0"+
		"\0\0\u0113\u0114\5:\0\0\u0114\u0115\5:\0\0\u0115\u0116\5=\0\0\u0116\26"+
		"\1\0\0\0\u0117\u0118\5=\0\0\u0118\30\1\0\0\0\u0119\u011a\5@\0\0\u011a"+
		"\32\1\0\0\0\u011b\u011c\5]\0\0\u011c\34\1\0\0\0\u011d\u011e\5[\0\0\u011e"+
		"\36\1\0\0\0\u011f\u0123\5{\0\0\u0121\u0124\3?\32\0\u0123\u0121\1\0\0\0"+
		"\u0123\u0124\1\0\0\0\u0124\u0125\1\0\0\0\u0125 \1\0\0\0\u0126\u0127\5"+
		"}\0\0\u0127\"\1\0\0\0\u0128\u0129\5g\0\0\u0129\u012a\5r\0\0\u012a\u012b"+
		"\5o\0\0\u012b\u012c\5u\0\0\u012c\u012d\5p\0\0\u012d$\1\0\0\0\u012e\u012f"+
		"\5d\0\0\u012f\u0130\5e\0\0\u0130\u0131\5f\0\0\u0131\u0132\5a\0\0\u0132"+
		"\u0133\5u\0\0\u0133\u0134\5l\0\0\u0134\u0135\5t\0\0\u0135&\1\0\0\0\u0136"+
		"\u0137\5i\0\0\u0137\u0138\5m\0\0\u0138\u0139\5p\0\0\u0139\u013a\5o\0\0"+
		"\u013a\u013b\5r\0\0\u013b\u013c\5t\0\0\u013c(\1\0\0\0\u013d\u013e\5t\0"+
		"\0\u013e\u013f\5r\0\0\u013f\u0140\5u\0\0\u0140\u0141\5e\0\0\u0141*\1\0"+
		"\0\0\u0142\u0143\5f\0\0\u0143\u0144\5a\0\0\u0144\u0145\5l\0\0\u0145\u0146"+
		"\5s\0\0\u0146\u0147\5e\0\0\u0147,\1\0\0\0\u0148\u0149\5d\0\0\u0149\u014a"+
		"\5e\0\0\u014a\u014b\5l\0\0\u014b\u014c\5i\0\0\u014c\u014d\5m\0\0\u014d"+
		"\u014e\5i\0\0\u014e\u014f\5t\0\0\u014f\u0150\5e\0\0\u0150\u0151\5r\0\0"+
		"\u0151\u0152\5s\0\0\u0152\u0155\1\0\0\0\u0153\u0156\3A\33\0\u0155\u0153"+
		"\1\0\0\0\u0155\u0156\1\0\0\0\u0156.\1\0\0\0\u0157\u015a\7\0\0\0\u0159"+
		"\u0157\1\0\0\0\u015a\u015b\1\0\0\0\u015b\u0159\1\0\0\0\u015b\u015c\1\0"+
		"\0\0\u015c\60\1\0\0\0\u015d\u0161\5\r\0\0\u015f\u0162\5\n\0\0\u0161\u015f"+
		"\1\0\0\0\u0161\u0162\1\0\0\0\u0162\u0166\1\0\0\0\u0163\u0166\5\n\0\0\u0165"+
		"\u015d\1\0\0\0\u0165\u0163\1\0\0\0\u0166\62\1\0\0\0\u0167\u016d\7\1\0"+
		"\0\u0169\u016c\7\2\0\0\u016b\u0169\1\0\0\0\u016c\u016f\1\0\0\0\u016d\u016b"+
		"\1\0\0\0\u016d\u016e\1\0\0\0\u016e\64\1\0\0\0\u016f\u016d\1\0\0\0\u0170"+
		"\u0172\5\"\0\0\u0172\66\1\0\0\0\u0173\u0174\5<\0\0\u0174\u0175\5<\0\0"+
		"\u0175\u0176\1\0\0\0\u01768\1\0\0\0\u0177\u0178\5<\0\0\u0178\u0179\5%"+
		"\0\0\u0179\u017a\1\0\0\0\u017a:\1\0\0\0\u017b\u017c\5/\0\0\u017c\u017d"+
		"\5/\0\0\u017d\u0182\1\0\0\0\u017e\u0181\b\3\0\0\u0180\u017e\1\0\0\0\u0181"+
		"\u0184\1\0\0\0\u0182\u0180\1\0\0\0\u0182\u0183\1\0\0\0\u0183<\1\0\0\0"+
		"\u0184\u0182\1\0\0\0\u0185\u0186\5/\0\0\u0186\u0187\5*\0\0\u0187\u0188"+
		"\1\0\0\0\u0188>\1\0\0\0\u0189\u018c\3E\35\0\u018b\u0189\1\0\0\0\u018c"+
		"\u018f\1\0\0\0\u018d\u018b\1\0\0\0\u018d\u018e\1\0\0\0\u018e\u0190\1\0"+
		"\0\0\u018f\u018d\1\0\0\0\u0190\u01a6\3\63\24\0\u0192\u0195\3E\35\0\u0194"+
		"\u0192\1\0\0\0\u0195\u0198\1\0\0\0\u0196\u0194\1\0\0\0\u0196\u0197\1\0"+
		"\0\0\u0197\u0199\1\0\0\0\u0198\u0196\1\0\0\0\u0199\u019f\3\17\2\0\u019b"+
		"\u019e\3E\35\0\u019d\u019b\1\0\0\0\u019e\u01a1\1\0\0\0\u019f\u019d\1\0"+
		"\0\0\u019f\u01a0\1\0\0\0\u01a0\u01a2\1\0\0\0\u01a1\u019f\1\0\0\0\u01a2"+
		"\u01a3\3\63\24\0\u01a3\u01a5\1\0\0\0\u01a4\u0196\1\0\0\0\u01a5\u01a8\1"+
		"\0\0\0\u01a6\u01a4\1\0\0\0\u01a6\u01a7\1\0\0\0\u01a7\u01ad\1\0\0\0\u01a8"+
		"\u01a6\1\0\0\0\u01a9\u01ac\3E\35\0\u01ab\u01a9\1\0\0\0\u01ac\u01af\1\0"+
		"\0\0\u01ad\u01ab\1\0\0\0\u01ad\u01ae\1\0\0\0\u01ae\u01b0\1\0\0\0\u01af"+
		"\u01ad\1\0\0\0\u01b0\u01b1\5|\0\0\u01b1@\1\0\0\0\u01b2\u01b5\3E\35\0\u01b4"+
		"\u01b2\1\0\0\0\u01b5\u01b8\1\0\0\0\u01b6\u01b4\1\0\0\0\u01b6\u01b7\1\0"+
		"\0\0\u01b7\u01b9\1\0\0\0\u01b8\u01b6\1\0\0\0\u01b9\u01bf\3C\34\0\u01bb"+
		"\u01be\3E\35\0\u01bd\u01bb\1\0\0\0\u01be\u01c1\1\0\0\0\u01bf\u01bd\1\0"+
		"\0\0\u01bf\u01c0\1\0\0\0\u01c0\u01c2\1\0\0\0\u01c1\u01bf\1\0\0\0\u01c2"+
		"\u01c8\3\17\2\0\u01c4\u01c7\3E\35\0\u01c6\u01c4\1\0\0\0\u01c7\u01ca\1"+
		"\0\0\0\u01c8\u01c6\1\0\0\0\u01c8\u01c9\1\0\0\0\u01c9\u01cb\1\0\0\0\u01ca"+
		"\u01c8\1\0\0\0\u01cb\u01cc\3C\34\0\u01ccB\1\0\0\0\u01cd\u01d1\5\"\0\0"+
		"\u01cf\u01d2\b\4\0\0\u01d1\u01cf\1\0\0\0\u01d2\u01d3\1\0\0\0\u01d3\u01d1"+
		"\1\0\0\0\u01d3\u01d4\1\0\0\0\u01d4\u01d5\1\0\0\0\u01d5\u01d6\5\"\0\0\u01d6"+
		"D\1\0\0\0\u01d7\u01e0\5 \0\0\u01d9\u01e0\5\t\0\0\u01db\u01e0\5\r\0\0\u01dd"+
		"\u01e0\5\n\0\0\u01df\u01d7\1\0\0\0\u01df\u01d9\1\0\0\0\u01df\u01db\1\0"+
		"\0\0\u01df\u01dd\1\0\0\0\u01e0F\1\0\0\0\u01e1\u01e2\5\ufff0\0\0\u01e2"+
		"H\1\0\0\0\u01e3\u01e4\5\ufff1\0\0\u01e4J\1\0\0\0\u01e5\u01e6\t\0\0\0\u01e6"+
		"L\1\0\0\0\u01e7\u01e9\3\61\23\0\u01e9N\1\0\0\0\u01ea\u01ed\b\5\0\0\u01ec"+
		"\u01ea\1\0\0\0\u01ed\u01ee\1\0\0\0\u01ee\u01ec\1\0\0\0\u01ee\u01ef\1\0"+
		"\0\0\u01ef\u01f0\1\0\0\0\u01f0P\1\0\0\0\u01f1\u01f2\5*\0\0\u01f2\u01f3"+
		"\5/\0\0\u01f3\u01f4\1\0\0\0\u01f4R\1\0\0\0\u01f5\u01f7\5*\0\0\u01f7T\1"+
		"\0\0\0\u01f8\u01fa\t\0\0\0\u01faV\1\0\0\0\u01fb\u01fd\3\61\23\0\u01fd"+
		"X\1\0\0\0\u01fe\u0201\b\6\0\0\u0200\u01fe\1\0\0\0\u0201\u0202\1\0\0\0"+
		"\u0202\u0200\1\0\0\0\u0202\u0203\1\0\0\0\u0203\u0204\1\0\0\0\u0204Z\1"+
		"\0\0\0\u0205\u0207\5!\0\0\u0207\u0209\3I\37\0\u0209\\\1\0\0\0\u020a\u020c"+
		"\5!\0\0\u020c^\1\0\0\0\u020d\u020f\t\0\0\0\u020f`\1\0\0\0\u0210\u0211"+
		"\3\63\24\0\u0211b\1\0\0\0\u0212\u0214\3/\22\0\u0214d\1\0\0\0\u0215\u0217"+
		"\3!\13\0\u0217f\1\0\0\0\u0218\u0219\3\17\2\0\u0219h\1\0\0\0\u021a\u021c"+
		"\3\61\23\0\u021cj\1\0\0\0\u021d\u021f\3G\36\0\u021f\u0221\5!\0\0\u0221"+
		"l\1\0\0\0\u0222\u0224\3G\36\0\u0224n\1\0\0\0\u0225\u0228\b\7\0\0\u0227"+
		"\u0225\1\0\0\0\u0228\u0229\1\0\0\0\u0229\u0227\1\0\0\0\u0229\u022a\1\0"+
		"\0\0\u022ap\1\0\0\0\u022b\u0231\5\\\0\0\u022d\u0232\t\0\0\0\u022f\u0232"+
		"\5\uffff\0\0\u0231\u022d\1\0\0\0\u0231\u022f\1\0\0\0\u0232\u0233\1\0\0"+
		"\0\u0233r\1\0\0\0\u0234\u0236\t\0\0\0\u0236t\1\0\0\0\u0237\u0239\3/\22"+
		"\0\u0239v\1\0\0\0\u023a\u023c\3\61\23\0\u023cx\1\0\0\0\u023d\u023f\3\17"+
		"\2\0\u023fz\1\0\0\0\u0240\u0241\3\63\24\0\u0241|\1\0\0\0\u0242\u0244\5"+
		"|\0\0\u0244~\1\0\0\0\u0245\u0247\t\0\0\0\u0247\u0080\1\0\0\0\u0248\u024a"+
		"\3\61\23\0\u024a\u0082\1\0\0\0\u024b\u024d\3G\36\0\u024d\u024f\5!\0\0"+
		"\u024f\u0084\1\0\0\0\u0250\u0252\3G\36\0\u0252\u0086\1\0\0\0\u0253\u0256"+
		"\b\b\0\0\u0255\u0253\1\0\0\0\u0256\u0257\1\0\0\0\u0257\u0255\1\0\0\0\u0257"+
		"\u0258\1\0\0\0\u0258\u0259\1\0\0\0\u0259\u0088\1\0\0\0\u025a\u0260\5\\"+
		"\0\0\u025c\u0261\t\0\0\0\u025e\u0261\5\uffff\0\0\u0260\u025c\1\0\0\0\u0260"+
		"\u025e\1\0\0\0\u0261\u0262\1\0\0\0\u0262\u008a\1\0\0\0\u0263\u0264\5>"+
		"\0\0\u0264\u0265\5>\0\0\u0265\u0266\1\0\0\0\u0266\u008c\1\0\0\0\u0267"+
		"\u0269\5>\0\0\u0269\u008e\1\0\0\0\u026a\u026c\t\0\0\0\u026c\u0090\1\0"+
		"\0\0\u026d\u026f\3\61\23\0\u026f\u0092\1\0\0\0\u0270\u0272\3G\36\0\u0272"+
		"\u0274\5!\0\0\u0274\u0094\1\0\0\0\u0275\u0277\3G\36\0\u0277\u0096\1\0"+
		"\0\0\u0278\u027b\b\t\0\0\u027a\u0278\1\0\0\0\u027b\u027c\1\0\0\0\u027c"+
		"\u027a\1\0\0\0\u027c\u027d\1\0\0\0\u027d\u027e\1\0\0\0\u027e\u0098\1\0"+
		"\0\0\u027f\u0285\5\\\0\0\u0281\u0286\t\0\0\0\u0283\u0286\5\uffff\0\0\u0285"+
		"\u0281\1\0\0\0\u0285\u0283\1\0\0\0\u0286\u0287\1\0\0\0\u0287\u009a\1\0"+
		"\0\0\u0288\u0289\5%\0\0\u0289\u028a\5>\0\0\u028a\u028b\1\0\0\0\u028b\u009c"+
		"\1\0\0\0\u028c\u028e\5%\0\0\u028e\u009e\1\0\0\0\u028f\u0291\t\0\0\0\u0291"+
		"\u00a0\1\0\0\0\u0292\u0294\3\61\23\0\u0294\u00a2\1\0\0\0\u0295\u0297\3"+
		"\31\7\0\u0297\u00a4\1\0\0\0\u0298\u029a\3\21\3\0\u029a\u00a6\1\0\0\0\u029b"+
		"\u029d\3\17\2\0\u029d\u00a8\1\0\0\0\u029e\u02a0\3\23\4\0\u02a0\u00aa\1"+
		"\0\0\0\u02a1\u02a3\3\13\0\0\u02a3\u00ac\1\0\0\0\u02a4\u02a6\3\r\1\0\u02a6"+
		"\u00ae\1\0\0\0\u02a7\u02a9\3\33\b\0\u02a9\u00b0\1\0\0\0\u02aa\u02ac\3"+
		"\35\t\0\u02ac\u00b2\1\0\0\0\u02ad\u02af\3\27\6\0\u02af\u00b4\1\0\0\0\u02b0"+
		"\u02b2\3\37\n\0\u02b2\u00b6\1\0\0\0\u02b3\u02b5\3/\22\0\u02b5\u00b8\1"+
		"\0\0\0\u02b6\u02b7\5s\0\0\u02b7\u02b8\5u\0\0\u02b8\u02b9\5p\0\0\u02b9"+
		"\u02ba\5e\0\0\u02ba\u02bb\5r\0\0\u02bb\u00ba\1\0\0\0\u02bc\u02bd\5i\0"+
		"\0\u02bd\u02be\5f\0\0\u02be\u00bc\1\0\0\0\u02bf\u02c0\5e\0\0\u02c0\u02c1"+
		"\5l\0\0\u02c1\u02c2\5s\0\0\u02c2\u02c3\5e\0\0\u02c3\u02c4\5i\0\0\u02c4"+
		"\u02c5\5f\0\0\u02c5\u00be\1\0\0\0\u02c6\u02c7\5e\0\0\u02c7\u02c8\5n\0"+
		"\0\u02c8\u02c9\5d\0\0\u02c9\u02ca\5i\0\0\u02ca\u02cb\5f\0\0\u02cb\u00c0"+
		"\1\0\0\0\u02cc\u02cd\5e\0\0\u02cd\u02ce\5l\0\0\u02ce\u02cf\5s\0\0\u02cf"+
		"\u02d0\5e\0\0\u02d0\u00c2\1\0\0\0\u02d1\u02d2\5e\0\0\u02d2\u02d3\5n\0"+
		"\0\u02d3\u02d4\5d\0\0\u02d4\u00c4\1\0\0\0\u02d5\u02d6\5f\0\0\u02d6\u02d7"+
		"\5i\0\0\u02d7\u02d8\5r\0\0\u02d8\u02d9\5s\0\0\u02d9\u02da\5t\0\0\u02da"+
		"\u02df\1\0\0\0\u02db\u02de\3E\35\0\u02dd\u02db\1\0\0\0\u02de\u02e1\1\0"+
		"\0\0\u02df\u02dd\1\0\0\0\u02df\u02e0\1\0\0\0\u02e0\u02e2\1\0\0\0\u02e1"+
		"\u02df\1\0\0\0\u02e2\u02e3\5(\0\0\u02e3\u00c6\1\0\0\0\u02e4\u02e5\5l\0"+
		"\0\u02e5\u02e6\5a\0\0\u02e6\u02e7\5s\0\0\u02e7\u02e8\5t\0\0\u02e8\u02ed"+
		"\1\0\0\0\u02e9\u02ec\3E\35\0\u02eb\u02e9\1\0\0\0\u02ec\u02ef\1\0\0\0\u02ed"+
		"\u02eb\1\0\0\0\u02ed\u02ee\1\0\0\0\u02ee\u02f0\1\0\0\0\u02ef\u02ed\1\0"+
		"\0\0\u02f0\u02f1\5(\0\0\u02f1\u00c8\1\0\0\0\u02f2\u02f3\5r\0\0\u02f3\u02f4"+
		"\5e\0\0\u02f4\u02f5\5s\0\0\u02f5\u02f6\5t\0\0\u02f6\u02fb\1\0\0\0\u02f7"+
		"\u02fa\3E\35\0\u02f9\u02f7\1\0\0\0\u02fa\u02fd\1\0\0\0\u02fb\u02f9\1\0"+
		"\0\0\u02fb\u02fc\1\0\0\0\u02fc\u02fe\1\0\0\0\u02fd\u02fb\1\0\0\0\u02fe"+
		"\u02ff\5(\0\0\u02ff\u00ca\1\0\0\0\u0300\u0301\5t\0\0\u0301\u0302\5r\0"+
		"\0\u0302\u0303\5u\0\0\u0303\u0304\5n\0\0\u0304\u0305\5c\0\0\u0305\u030a"+
		"\1\0\0\0\u0306\u0309\3E\35\0\u0308\u0306\1\0\0\0\u0309\u030c\1\0\0\0\u030a"+
		"\u0308\1\0\0\0\u030a\u030b\1\0\0\0\u030b\u030d\1\0\0\0\u030c\u030a\1\0"+
		"\0\0\u030d\u030e\5(\0\0\u030e\u00cc\1\0\0\0\u030f\u0310\5s\0\0\u0310\u0311"+
		"\5t\0\0\u0311\u0312\5r\0\0\u0312\u0313\5i\0\0\u0313\u0314\5p\0\0\u0314"+
		"\u0319\1\0\0\0\u0315\u0318\3E\35\0\u0317\u0315\1\0\0\0\u0318\u031b\1\0"+
		"\0\0\u0319\u0317\1\0\0\0\u0319\u031a\1\0\0\0\u031a\u031c\1\0\0\0\u031b"+
		"\u0319\1\0\0\0\u031c\u031d\5(\0\0\u031d\u00ce\1\0\0\0\u031e\u031f\5t\0"+
		"\0\u031f\u0320\5r\0\0\u0320\u0321\5i\0\0\u0321\u0322\5m\0\0\u0322\u0327"+
		"\1\0\0\0\u0323\u0326\3E\35\0\u0325\u0323\1\0\0\0\u0326\u0329\1\0\0\0\u0327"+
		"\u0325\1\0\0\0\u0327\u0328\1\0\0\0\u0328\u032a\1\0\0\0\u0329\u0327\1\0"+
		"\0\0\u032a\u032b\5(\0\0\u032b\u00d0\1\0\0\0\u032c\u032d\5l\0\0\u032d\u032e"+
		"\5e\0\0\u032e\u032f\5n\0\0\u032f\u0330\5g\0\0\u0330\u0331\5t\0\0\u0331"+
		"\u0332\5h\0\0\u0332\u0337\1\0\0\0\u0333\u0336\3E\35\0\u0335\u0333\1\0"+
		"\0\0\u0336\u0339\1\0\0\0\u0337\u0335\1\0\0\0\u0337\u0338\1\0\0\0\u0338"+
		"\u033a\1\0\0\0\u0339\u0337\1\0\0\0\u033a\u033b\5(\0\0\u033b\u00d2\1\0"+
		"\0\0\u033c\u033d\5s\0\0\u033d\u033e\5t\0\0\u033e\u033f\5r\0\0\u033f\u0340"+
		"\5l\0\0\u0340\u0341\5e\0\0\u0341\u0342\5n\0\0\u0342\u0347\1\0\0\0\u0343"+
		"\u0346\3E\35\0\u0345\u0343\1\0\0\0\u0346\u0349\1\0\0\0\u0347\u0345\1\0"+
		"\0\0\u0347\u0348\1\0\0\0\u0348\u034a\1\0\0\0\u0349\u0347\1\0\0\0\u034a"+
		"\u034b\5(\0\0\u034b\u00d4\1\0\0\0\u034c\u034d\5r\0\0\u034d\u034e\5e\0"+
		"\0\u034e\u034f\5v\0\0\u034f\u0350\5e\0\0\u0350\u0351\5r\0\0\u0351\u0352"+
		"\5s\0\0\u0352\u0353\5e\0\0\u0353\u0358\1\0\0\0\u0354\u0357\3E\35\0\u0356"+
		"\u0354\1\0\0\0\u0357\u035a\1\0\0\0\u0358\u0356\1\0\0\0\u0358\u0359\1\0"+
		"\0\0\u0359\u035b\1\0\0\0\u035a\u0358\1\0\0\0\u035b\u035c\5(\0\0\u035c"+
		"\u00d6\1\0\0\0\u035d\u035e\5.\0\0\u035e\u035f\5.\0\0\u035f\u0360\5.\0"+
		"\0\u0360\u00d8\1\0\0\0\u0361\u0362\5!\0\0\u0362\u00da\1\0\0\0\u0363\u0364"+
		"\5|\0\0\u0364\u0365\5|\0\0\u0365\u00dc\1\0\0\0\u0366\u0367\5&\0\0\u0367"+
		"\u0368\5&\0\0\u0368\u00de\1\0\0\0\u0369\u036a\5;\0\0\u036a\u00e0\1\0\0"+
		"\0\u036b\u0371\5\\\0\0\u036d\u0370\b\n\0\0\u036f\u036d\1\0\0\0\u0370\u0373"+
		"\1\0\0\0\u0371\u036f\1\0\0\0\u0371\u0372\1\0\0\0\u0372\u00e2\1\0\0\0\u0373"+
		"\u0371\1\0\0\0\u0374\u0376\5@\0\0\u0376\u0377\3\63\24\0\u0377\u00e4\1"+
		"\0\0\0\u0378\u037a\3\63\24\0\u037a\u00e6\1\0\0\0\u037b\u037d\3I\37\0\u037d"+
		"\u00e8\1\0\0\0\u037e\u038c\5\"\0\0\u0380\u038b\b\13\0\0\u0382\u0388\5"+
		"\\\0\0\u0384\u0389\t\0\0\0\u0386\u0389\5\uffff\0\0\u0388\u0384\1\0\0\0"+
		"\u0388\u0386\1\0\0\0\u0389\u038b\1\0\0\0\u038a\u0380\1\0\0\0\u038a\u0382"+
		"\1\0\0\0\u038b\u038e\1\0\0\0\u038c\u038a\1\0\0\0\u038c\u038d\1\0\0\0\u038d"+
		"\u038f\1\0\0\0\u038e\u038c\1\0\0\0\u038f\u0391\5\"\0\0\u0391\u00ea\1\0"+
		"\0\0\u0392\u0394\t\0\0\0\u0394\u00ec\1\0\0\0\u0395\u0397\3\61\23\0\u0397"+
		"\u00ee\1\0\0\0\u0398\u039a\3G\36\0\u039a\u039c\5!\0\0\u039c\u00f0\1\0"+
		"\0\0\u039d\u039f\3G\36\0\u039f\u00f2\1\0\0\0\u03a0\u03a3\b\f\0\0\u03a2"+
		"\u03a0\1\0\0\0\u03a3\u03a4\1\0\0\0\u03a4\u03a2\1\0\0\0\u03a4\u03a5\1\0"+
		"\0\0\u03a5\u03a6\1\0\0\0\u03a6\u00f4\1\0\0\0\u03a7\u03ad\5\\\0\0\u03a9"+
		"\u03ae\t\0\0\0\u03ab\u03ae\5\uffff\0\0\u03ad\u03a9\1\0\0\0\u03ad\u03ab"+
		"\1\0\0\0\u03ae\u03af\1\0\0\0\u03af\u00f6\1\0\0\0\u03b0\u03b2\5\"\0\0\u03b2"+
		"\u00f8\1\0\0\0\u03b3\u03b5\t\0\0\0\u03b5\u00fa\1\0\0\0\u03b6\u03b8\3/"+
		"\22\0\u03b8\u00fc\1\0\0\0\u03b9\u03bb\3\61\23\0\u03bb\u00fe\1\0\0\0\u03bc"+
		"\u03bd\3C\34\0\u03bd\u0100\1\0\0\0\u03be\u03c0\3\17\2\0\u03c0\u0102\1"+
		"\0\0\0\u03c1\u03c3\3/\22\0\u03c3\u0104\1\0\0\0\u03c4\u03c6\3\61\23\0\u03c6"+
		"\u0106\1\0\0\0\u03c7\u03c9\3C\34\0\u03c9\u0108\1\0\0\0\63\0\1\1\1\2\1"+
		"\3\1\4\1\5\1\6\1\7\1\b\1\t\1\n\1\u0123\1\u0155\1\u015b\1\u0161\1\u0165"+
		"\1\u016d\1\u0182\1\u018d\1\u0196\1\u019f\1\u01a6\1\u01ad\1\u01b6\1\u01bf"+
		"\1\u01c8\1\u01d3\1\u01df\1\u01ee\1\u0202\1\u0229\1\u0231\1\u0257\1\u0260"+
		"\1\u027c\1\u0285\1\u02df\1\u02ed\1\u02fb\1\u030a\1\u0319\1\u0327\1\u0337"+
		"\1\u0347\1\u0358\1\u0371\1\u0388\1\u038a\1\u038c\1\u03a4\1\u03ad\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}