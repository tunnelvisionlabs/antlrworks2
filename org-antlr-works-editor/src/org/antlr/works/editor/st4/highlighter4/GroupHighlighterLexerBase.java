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
		ELSEIF=88, ENDIF=89, ELSE=90, END=91, ELLIPSIS=92, NOT=93, OR=94, AND=95, 
		SEMI=96, ESCAPE=97, REGION_ID=98, TemplateExpression_ID=99, TemplateExpression_CLOSE_DELIMITER=100, 
		TemplateExpression_STRING=101, TemplateExpression_ANYCHAR=102, StringTemplate_NEWLINE=103, 
		StringTemplate_COMMENT=104, StringTemplate_OPEN_DELIMITER=105, StringTemplate_TEXT=106, 
		StringTemplate_ESCAPE=107, StringTemplate_END=108, StringTemplate_ANYCHAR=109, 
		DelimitersOpenSpec_WS=110, DelimitersOpenSpec_NEWLINE=111, DelimitersOpenSpec_DELIMITER_STRING=112, 
		DelimitersOpenSpec_COMMA=113, DelimitersCloseSpec_WS=114, DelimitersCloseSpec_NEWLINE=115, 
		DelimitersCloseSpec_DELIMITER_STRING=116;
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
		"ELLIPSIS", "NOT", "OR", "AND", "SEMI", "ESCAPE", "REGION_ID", "TemplateExpression_ID", 
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
		"ENDIF", "ELSE", "END", "ELLIPSIS", "NOT", "OR", "AND", "SEMI", "ESCAPE", 
		"REGION_ID", "TemplateExpression_ID", "TemplateExpression_CLOSE_DELIMITER", 
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

			case 100 : TemplateExpression_ID_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 101 : TemplateExpression_CLOSE_DELIMITER_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 102 : TemplateExpression_STRING_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 103 : TemplateExpression_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 104 : StringTemplate_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 105 : StringTemplate_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 106 : StringTemplate_OPEN_DELIMITER_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 107 : StringTemplate_TEXT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 108 : StringTemplate_ESCAPE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 109 : StringTemplate_END_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 110 : StringTemplate_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 111 : DelimitersOpenSpec_WS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 112 : DelimitersOpenSpec_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 114 : DelimitersOpenSpec_COMMA_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 115 : DelimitersCloseSpec_WS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 116 : DelimitersCloseSpec_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 117 : DelimitersCloseSpec_DELIMITER_STRING_action((ParserRuleContext<?>)_localctx, actionIndex); break;
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
		"\2t\u0330\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff"+
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
		"\7o\2p\7p\2q\7q\2r\7r\2s\7s\2t\7t\2u\7u\1\0\1\0\1\1\1\1\1\2\1\2\1\3\1"+
		"\3\1\4\1\4\1\5\1\5\1\5\1\5\1\6\1\6\1\7\1\7\1\b\1\b\1\t\1\t\1\n\0\1\n\0"+
		"\3\n\b\n\1\n\1\13\1\13\1\f\1\f\1\f\1\f\1\f\1\f\1\r\1\r\1\r\1\r\1\r\1\r"+
		"\1\r\1\r\1\16\1\16\1\16\1\16\1\16\1\16\1\16\1\17\1\17\1\17\1\17\1\17\1"+
		"\20\1\20\1\20\1\20\1\20\1\20\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1"+
		"\21\1\21\1\21\1\21\0\3\21\b\21\1\22\0\4\22\b\22\13\22\1\22\1\23\0\1\23"+
		"\0\3\23\b\23\1\23\0\3\23\b\23\1\24\0\1\24\0\5\24\b\24\n\24\1\24\t\24\1"+
		"\25\0\1\25\1\26\1\26\1\26\1\26\1\27\1\27\1\27\1\27\1\30\1\30\1\30\1\30"+
		"\0\5\30\b\30\n\30\1\30\t\30\1\31\1\31\1\31\1\31\1\32\0\5\32\b\32\n\32"+
		"\1\32\t\32\1\32\0\1\32\0\5\32\b\32\n\32\1\32\t\32\1\32\0\1\32\0\5\32\b"+
		"\32\n\32\1\32\t\32\1\32\1\32\5\32\b\32\n\32\1\32\t\32\1\32\0\5\32\b\32"+
		"\n\32\1\32\t\32\1\32\1\32\1\33\0\5\33\b\33\n\33\1\33\t\33\1\33\0\1\33"+
		"\0\5\33\b\33\n\33\1\33\t\33\1\33\0\1\33\0\5\33\b\33\n\33\1\33\t\33\1\33"+
		"\1\33\1\34\0\1\34\0\4\34\b\34\13\34\1\34\1\34\1\34\1\35\0\1\35\0\1\35"+
		"\0\1\35\0\3\35\b\35\1\36\1\36\1\37\1\37\1 \1 \1!\0\1!\1\"\0\4\"\b\"\13"+
		"\"\1\"\1\"\1#\1#\1#\1#\1$\0\1$\1%\0\1%\1&\0\1&\1\'\0\4\'\b\'\13\'\1\'"+
		"\1\'\1(\0\1(\0\1(\1)\0\1)\1*\0\1*\1+\1+\1,\0\1,\1-\0\1-\1.\1.\1/\0\1/"+
		"\1\60\0\1\60\0\1\60\1\61\0\1\61\1\62\0\4\62\b\62\13\62\1\62\1\63\0\1\63"+
		"\0\1\63\0\3\63\b\63\1\63\1\64\0\1\64\1\65\0\1\65\1\66\0\1\66\1\67\0\1"+
		"\67\18\18\19\0\19\1:\0\1:\1;\0\1;\1<\0\1<\0\1<\1=\0\1=\1>\0\4>\b>\13>"+
		"\1>\1>\1?\0\1?\0\1?\0\3?\b?\1?\1@\1@\1@\1@\1A\0\1A\1B\0\1B\1C\0\1C\1D"+
		"\0\1D\0\1D\1E\0\1E\1F\0\4F\bF\13F\1F\1F\1G\0\1G\0\1G\0\3G\bG\1G\1H\1H"+
		"\1H\1H\1I\0\1I\1J\0\1J\1K\0\1K\1L\0\1L\1M\0\1M\1N\0\1N\1O\0\1O\1P\0\1"+
		"P\1Q\0\1Q\1R\0\1R\1S\0\1S\1T\0\1T\1U\0\1U\1V\0\1V\1W\1W\1W\1W\1W\1W\1"+
		"X\1X\1X\1Y\1Y\1Y\1Y\1Y\1Y\1Y\1Z\1Z\1Z\1Z\1Z\1Z\1[\1[\1[\1[\1[\1\\\1\\"+
		"\1\\\1\\\1]\1]\1]\1]\1^\1^\1_\1_\1_\1`\1`\1`\1a\1a\1b\0\1b\0\5b\bb\nb"+
		"\1b\tb\1c\0\1c\1c\1d\0\1d\1e\0\1e\1f\0\1f\0\1f\0\1f\0\1f\0\3f\bf\5f\b"+
		"f\nf\1f\tf\1f\0\1f\1g\0\1g\1h\0\1h\1i\0\1i\0\1i\1j\0\1j\1k\0\4k\bk\13"+
		"k\1k\1k\1l\0\1l\0\1l\0\3l\bl\1l\1m\0\1m\1n\0\1n\1o\0\1o\1p\0\1p\1q\1q"+
		"\1r\0\1r\1s\0\1s\1t\0\1t\1u\0\1uv\13\5\uffff\r\6\uffff\17\7\uffff\21\b"+
		"\uffff\23\t\uffff\25\n\uffff\27\13\uffff\31\f\uffff\33\r\uffff\35\16\uffff"+
		"\37\17\0!\20\uffff#\21\uffff%\22\uffff\'\23\uffff)\24\uffff+\25\uffff"+
		"-\26\uffff/\27\uffff\61\30\uffff\63\31\uffff\65\32\1\67\33\29\34\3;\35"+
		"\uffff=\36\4?\0\uffffA\0\uffffC\0\uffffE\0\uffffG\3\uffffI\4\uffffK\37"+
		"\uffffM \5O!\6Q\"\7S#\bU$\tW%\nY&\13[\'\f](\r_)\16a*\uffffc+\17e,\20g"+
		"-\uffffi.\21k/\22m\60\23o\61\uffffq\62\24s\63\25u\64\26w\65\27y\66\30"+
		"{\67\uffff}8\31\1779\32\u0081:\33\u0083;\34\u0085<\35\u0087=\36\u0089"+
		">\37\u008b? \u008d@!\u008fA\"\u0091B#\u0093C$\u0095D%\u0097E&\u0099F\'"+
		"\u009bG(\u009dH)\u009fI*\u00a1J+\u00a3K,\u00a5L-\u00a7M.\u00a9N/\u00ab"+
		"O\60\u00adP\61\u00afQ\62\u00b1R\63\u00b3S\64\u00b5T\65\u00b7U\66\u00b9"+
		"V\uffff\u00bbW\uffff\u00bdX\uffff\u00bfY\uffff\u00c1Z\uffff\u00c3[\uffff"+
		"\u00c5\\\uffff\u00c7]\uffff\u00c9^\uffff\u00cb_\uffff\u00cd`\uffff\u00cf"+
		"a\uffff\u00d1b\uffff\u00d3c\67\u00d5d8\u00d7e9\u00d9f:\u00dbg;\u00ddh"+
		"<\u00dfi=\u00e1j>\u00e3k?\u00e5l@\u00e7mA\u00e9nB\u00eboC\u00edp\uffff"+
		"\u00efqD\u00f1rE\u00f3sF\u00f5tG\13\0\1\2\3\4\5\6\7\b\t\n\r\2\t\t  \3"+
		"AZ__az\5--\609AZ__az\2\n\n\r\r\3\n\n\r\r\"\"\3\n\n\r\r**\3\n\n\r\r!!\n"+
		"\t\n\r\r  ,,AZ\\\\__az}}\ufff0\ufff0\5\n\n\r\r>>\\\\\ufff0\ufff0\5\n\n"+
		"\r\r%%\\\\\ufff0\ufff0\1\ufff1\ufff1\2\"\"\\\\\5\n\n\r\r\"\"\\\\\ufff0"+
		"\ufff0\u02ce\0\13\1\0\0\0\0\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1"+
		"\0\0\0\0\25\1\0\0\0\0\27\1\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0"+
		"\0\0\37\1\0\0\0\0!\1\0\0\0\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0"+
		"\0\0+\1\0\0\0\0-\1\0\0\0\0/\1\0\0\0\0\61\1\0\0\0\0\63\1\0\0\0\0\65\1\0"+
		"\0\0\0\67\1\0\0\0\09\1\0\0\0\0;\1\0\0\0\0=\1\0\0\0\0K\1\0\0\0\1M\1\0\0"+
		"\0\1O\1\0\0\0\1Q\1\0\0\0\1S\1\0\0\0\1U\1\0\0\0\2W\1\0\0\0\2Y\1\0\0\0\2"+
		"[\1\0\0\0\2]\1\0\0\0\2_\1\0\0\0\3a\1\0\0\0\3c\1\0\0\0\3e\1\0\0\0\3g\1"+
		"\0\0\0\3i\1\0\0\0\3k\1\0\0\0\3m\1\0\0\0\3o\1\0\0\0\3q\1\0\0\0\3s\1\0\0"+
		"\0\4u\1\0\0\0\4w\1\0\0\0\4y\1\0\0\0\4{\1\0\0\0\4}\1\0\0\0\4\177\1\0\0"+
		"\0\5\u0081\1\0\0\0\5\u0083\1\0\0\0\5\u0085\1\0\0\0\5\u0087\1\0\0\0\5\u0089"+
		"\1\0\0\0\5\u008b\1\0\0\0\5\u008d\1\0\0\0\5\u008f\1\0\0\0\6\u0091\1\0\0"+
		"\0\6\u0093\1\0\0\0\6\u0095\1\0\0\0\6\u0097\1\0\0\0\6\u0099\1\0\0\0\6\u009b"+
		"\1\0\0\0\6\u009d\1\0\0\0\6\u009f\1\0\0\0\7\u00a1\1\0\0\0\7\u00a3\1\0\0"+
		"\0\7\u00a5\1\0\0\0\7\u00a7\1\0\0\0\7\u00a9\1\0\0\0\7\u00ab\1\0\0\0\7\u00ad"+
		"\1\0\0\0\7\u00af\1\0\0\0\7\u00b1\1\0\0\0\7\u00b3\1\0\0\0\7\u00b5\1\0\0"+
		"\0\7\u00b7\1\0\0\0\7\u00b9\1\0\0\0\7\u00bb\1\0\0\0\7\u00bd\1\0\0\0\7\u00bf"+
		"\1\0\0\0\7\u00c1\1\0\0\0\7\u00c3\1\0\0\0\7\u00c5\1\0\0\0\7\u00c7\1\0\0"+
		"\0\7\u00c9\1\0\0\0\7\u00cb\1\0\0\0\7\u00cd\1\0\0\0\7\u00cf\1\0\0\0\7\u00d1"+
		"\1\0\0\0\7\u00d3\1\0\0\0\7\u00d5\1\0\0\0\7\u00d7\1\0\0\0\7\u00d9\1\0\0"+
		"\0\b\u00db\1\0\0\0\b\u00dd\1\0\0\0\b\u00df\1\0\0\0\b\u00e1\1\0\0\0\b\u00e3"+
		"\1\0\0\0\b\u00e5\1\0\0\0\b\u00e7\1\0\0\0\t\u00e9\1\0\0\0\t\u00eb\1\0\0"+
		"\0\t\u00ed\1\0\0\0\t\u00ef\1\0\0\0\n\u00f1\1\0\0\0\n\u00f3\1\0\0\0\n\u00f5"+
		"\1\0\0\0\13\u00f7\1\0\0\0\r\u00f9\1\0\0\0\17\u00fb\1\0\0\0\21\u00fd\1"+
		"\0\0\0\23\u00ff\1\0\0\0\25\u0101\1\0\0\0\27\u0105\1\0\0\0\31\u0107\1\0"+
		"\0\0\33\u0109\1\0\0\0\35\u010b\1\0\0\0\37\u010d\1\0\0\0!\u0114\1\0\0\0"+
		"#\u0116\1\0\0\0%\u011c\1\0\0\0\'\u0124\1\0\0\0)\u012b\1\0\0\0+\u0130\1"+
		"\0\0\0-\u0136\1\0\0\0/\u0147\1\0\0\0\61\u0153\1\0\0\0\63\u0155\1\0\0\0"+
		"\65\u015e\1\0\0\0\67\u0161\1\0\0\09\u0165\1\0\0\0;\u0169\1\0\0\0=\u0173"+
		"\1\0\0\0?\u017b\1\0\0\0A\u01a4\1\0\0\0C\u01bb\1\0\0\0E\u01cd\1\0\0\0G"+
		"\u01cf\1\0\0\0I\u01d1\1\0\0\0K\u01d3\1\0\0\0M\u01d5\1\0\0\0O\u01da\1\0"+
		"\0\0Q\u01df\1\0\0\0S\u01e3\1\0\0\0U\u01e6\1\0\0\0W\u01e9\1\0\0\0Y\u01ee"+
		"\1\0\0\0[\u01f3\1\0\0\0]\u01f8\1\0\0\0_\u01fb\1\0\0\0a\u01fe\1\0\0\0c"+
		"\u0200\1\0\0\0e\u0203\1\0\0\0g\u0206\1\0\0\0i\u0208\1\0\0\0k\u020b\1\0"+
		"\0\0m\u0210\1\0\0\0o\u0215\1\0\0\0q\u0219\1\0\0\0s\u0222\1\0\0\0u\u0225"+
		"\1\0\0\0w\u0228\1\0\0\0y\u022b\1\0\0\0{\u022e\1\0\0\0}\u0230\1\0\0\0\177"+
		"\u0233\1\0\0\0\u0081\u0236\1\0\0\0\u0083\u0239\1\0\0\0\u0085\u023e\1\0"+
		"\0\0\u0087\u0243\1\0\0\0\u0089\u0248\1\0\0\0\u008b\u0251\1\0\0\0\u008d"+
		"\u0255\1\0\0\0\u008f\u0258\1\0\0\0\u0091\u025b\1\0\0\0\u0093\u025e\1\0"+
		"\0\0\u0095\u0263\1\0\0\0\u0097\u0268\1\0\0\0\u0099\u026d\1\0\0\0\u009b"+
		"\u0276\1\0\0\0\u009d\u027a\1\0\0\0\u009f\u027d\1\0\0\0\u00a1\u0280\1\0"+
		"\0\0\u00a3\u0283\1\0\0\0\u00a5\u0286\1\0\0\0\u00a7\u0289\1\0\0\0\u00a9"+
		"\u028c\1\0\0\0\u00ab\u028f\1\0\0\0\u00ad\u0292\1\0\0\0\u00af\u0295\1\0"+
		"\0\0\u00b1\u0298\1\0\0\0\u00b3\u029b\1\0\0\0\u00b5\u029e\1\0\0\0\u00b7"+
		"\u02a1\1\0\0\0\u00b9\u02a4\1\0\0\0\u00bb\u02aa\1\0\0\0\u00bd\u02ad\1\0"+
		"\0\0\u00bf\u02b4\1\0\0\0\u00c1\u02ba\1\0\0\0\u00c3\u02bf\1\0\0\0\u00c5"+
		"\u02c3\1\0\0\0\u00c7\u02c7\1\0\0\0\u00c9\u02c9\1\0\0\0\u00cb\u02cc\1\0"+
		"\0\0\u00cd\u02cf\1\0\0\0\u00cf\u02d1\1\0\0\0\u00d1\u02da\1\0\0\0\u00d3"+
		"\u02de\1\0\0\0\u00d5\u02e1\1\0\0\0\u00d7\u02e4\1\0\0\0\u00d9\u02f8\1\0"+
		"\0\0\u00db\u02fb\1\0\0\0\u00dd\u02fe\1\0\0\0\u00df\u0303\1\0\0\0\u00e1"+
		"\u0308\1\0\0\0\u00e3\u030d\1\0\0\0\u00e5\u0316\1\0\0\0\u00e7\u0319\1\0"+
		"\0\0\u00e9\u031c\1\0\0\0\u00eb\u031f\1\0\0\0\u00ed\u0322\1\0\0\0\u00ef"+
		"\u0324\1\0\0\0\u00f1\u0327\1\0\0\0\u00f3\u032a\1\0\0\0\u00f5\u032d\1\0"+
		"\0\0\u00f7\u00f8\5(\0\0\u00f8\f\1\0\0\0\u00f9\u00fa\5)\0\0\u00fa\16\1"+
		"\0\0\0\u00fb\u00fc\5,\0\0\u00fc\20\1\0\0\0\u00fd\u00fe\5.\0\0\u00fe\22"+
		"\1\0\0\0\u00ff\u0100\5:\0\0\u0100\24\1\0\0\0\u0101\u0102\5:\0\0\u0102"+
		"\u0103\5:\0\0\u0103\u0104\5=\0\0\u0104\26\1\0\0\0\u0105\u0106\5=\0\0\u0106"+
		"\30\1\0\0\0\u0107\u0108\5@\0\0\u0108\32\1\0\0\0\u0109\u010a\5]\0\0\u010a"+
		"\34\1\0\0\0\u010b\u010c\5[\0\0\u010c\36\1\0\0\0\u010d\u0111\5{\0\0\u010f"+
		"\u0112\3?\32\0\u0111\u010f\1\0\0\0\u0111\u0112\1\0\0\0\u0112\u0113\1\0"+
		"\0\0\u0113 \1\0\0\0\u0114\u0115\5}\0\0\u0115\"\1\0\0\0\u0116\u0117\5g"+
		"\0\0\u0117\u0118\5r\0\0\u0118\u0119\5o\0\0\u0119\u011a\5u\0\0\u011a\u011b"+
		"\5p\0\0\u011b$\1\0\0\0\u011c\u011d\5d\0\0\u011d\u011e\5e\0\0\u011e\u011f"+
		"\5f\0\0\u011f\u0120\5a\0\0\u0120\u0121\5u\0\0\u0121\u0122\5l\0\0\u0122"+
		"\u0123\5t\0\0\u0123&\1\0\0\0\u0124\u0125\5i\0\0\u0125\u0126\5m\0\0\u0126"+
		"\u0127\5p\0\0\u0127\u0128\5o\0\0\u0128\u0129\5r\0\0\u0129\u012a\5t\0\0"+
		"\u012a(\1\0\0\0\u012b\u012c\5t\0\0\u012c\u012d\5r\0\0\u012d\u012e\5u\0"+
		"\0\u012e\u012f\5e\0\0\u012f*\1\0\0\0\u0130\u0131\5f\0\0\u0131\u0132\5"+
		"a\0\0\u0132\u0133\5l\0\0\u0133\u0134\5s\0\0\u0134\u0135\5e\0\0\u0135,"+
		"\1\0\0\0\u0136\u0137\5d\0\0\u0137\u0138\5e\0\0\u0138\u0139\5l\0\0\u0139"+
		"\u013a\5i\0\0\u013a\u013b\5m\0\0\u013b\u013c\5i\0\0\u013c\u013d\5t\0\0"+
		"\u013d\u013e\5e\0\0\u013e\u013f\5r\0\0\u013f\u0140\5s\0\0\u0140\u0143"+
		"\1\0\0\0\u0141\u0144\3A\33\0\u0143\u0141\1\0\0\0\u0143\u0144\1\0\0\0\u0144"+
		".\1\0\0\0\u0145\u0148\7\0\0\0\u0147\u0145\1\0\0\0\u0148\u0149\1\0\0\0"+
		"\u0149\u0147\1\0\0\0\u0149\u014a\1\0\0\0\u014a\60\1\0\0\0\u014b\u014f"+
		"\5\r\0\0\u014d\u0150\5\n\0\0\u014f\u014d\1\0\0\0\u014f\u0150\1\0\0\0\u0150"+
		"\u0154\1\0\0\0\u0151\u0154\5\n\0\0\u0153\u014b\1\0\0\0\u0153\u0151\1\0"+
		"\0\0\u0154\62\1\0\0\0\u0155\u015b\7\1\0\0\u0157\u015a\7\2\0\0\u0159\u0157"+
		"\1\0\0\0\u015a\u015d\1\0\0\0\u015b\u0159\1\0\0\0\u015b\u015c\1\0\0\0\u015c"+
		"\64\1\0\0\0\u015d\u015b\1\0\0\0\u015e\u0160\5\"\0\0\u0160\66\1\0\0\0\u0161"+
		"\u0162\5<\0\0\u0162\u0163\5<\0\0\u0163\u0164\1\0\0\0\u01648\1\0\0\0\u0165"+
		"\u0166\5<\0\0\u0166\u0167\5%\0\0\u0167\u0168\1\0\0\0\u0168:\1\0\0\0\u0169"+
		"\u016a\5/\0\0\u016a\u016b\5/\0\0\u016b\u0170\1\0\0\0\u016c\u016f\b\3\0"+
		"\0\u016e\u016c\1\0\0\0\u016f\u0172\1\0\0\0\u0170\u016e\1\0\0\0\u0170\u0171"+
		"\1\0\0\0\u0171<\1\0\0\0\u0172\u0170\1\0\0\0\u0173\u0174\5/\0\0\u0174\u0175"+
		"\5*\0\0\u0175\u0176\1\0\0\0\u0176>\1\0\0\0\u0177\u017a\3E\35\0\u0179\u0177"+
		"\1\0\0\0\u017a\u017d\1\0\0\0\u017b\u0179\1\0\0\0\u017b\u017c\1\0\0\0\u017c"+
		"\u017e\1\0\0\0\u017d\u017b\1\0\0\0\u017e\u0194\3\63\24\0\u0180\u0183\3"+
		"E\35\0\u0182\u0180\1\0\0\0\u0183\u0186\1\0\0\0\u0184\u0182\1\0\0\0\u0184"+
		"\u0185\1\0\0\0\u0185\u0187\1\0\0\0\u0186\u0184\1\0\0\0\u0187\u018d\3\17"+
		"\2\0\u0189\u018c\3E\35\0\u018b\u0189\1\0\0\0\u018c\u018f\1\0\0\0\u018d"+
		"\u018b\1\0\0\0\u018d\u018e\1\0\0\0\u018e\u0190\1\0\0\0\u018f\u018d\1\0"+
		"\0\0\u0190\u0191\3\63\24\0\u0191\u0193\1\0\0\0\u0192\u0184\1\0\0\0\u0193"+
		"\u0196\1\0\0\0\u0194\u0192\1\0\0\0\u0194\u0195\1\0\0\0\u0195\u019b\1\0"+
		"\0\0\u0196\u0194\1\0\0\0\u0197\u019a\3E\35\0\u0199\u0197\1\0\0\0\u019a"+
		"\u019d\1\0\0\0\u019b\u0199\1\0\0\0\u019b\u019c\1\0\0\0\u019c\u019e\1\0"+
		"\0\0\u019d\u019b\1\0\0\0\u019e\u019f\5|\0\0\u019f@\1\0\0\0\u01a0\u01a3"+
		"\3E\35\0\u01a2\u01a0\1\0\0\0\u01a3\u01a6\1\0\0\0\u01a4\u01a2\1\0\0\0\u01a4"+
		"\u01a5\1\0\0\0\u01a5\u01a7\1\0\0\0\u01a6\u01a4\1\0\0\0\u01a7\u01ad\3C"+
		"\34\0\u01a9\u01ac\3E\35\0\u01ab\u01a9\1\0\0\0\u01ac\u01af\1\0\0\0\u01ad"+
		"\u01ab\1\0\0\0\u01ad\u01ae\1\0\0\0\u01ae\u01b0\1\0\0\0\u01af\u01ad\1\0"+
		"\0\0\u01b0\u01b6\3\17\2\0\u01b2\u01b5\3E\35\0\u01b4\u01b2\1\0\0\0\u01b5"+
		"\u01b8\1\0\0\0\u01b6\u01b4\1\0\0\0\u01b6\u01b7\1\0\0\0\u01b7\u01b9\1\0"+
		"\0\0\u01b8\u01b6\1\0\0\0\u01b9\u01ba\3C\34\0\u01baB\1\0\0\0\u01bb\u01bf"+
		"\5\"\0\0\u01bd\u01c0\b\4\0\0\u01bf\u01bd\1\0\0\0\u01c0\u01c1\1\0\0\0\u01c1"+
		"\u01bf\1\0\0\0\u01c1\u01c2\1\0\0\0\u01c2\u01c3\1\0\0\0\u01c3\u01c4\5\""+
		"\0\0\u01c4D\1\0\0\0\u01c5\u01ce\5 \0\0\u01c7\u01ce\5\t\0\0\u01c9\u01ce"+
		"\5\r\0\0\u01cb\u01ce\5\n\0\0\u01cd\u01c5\1\0\0\0\u01cd\u01c7\1\0\0\0\u01cd"+
		"\u01c9\1\0\0\0\u01cd\u01cb\1\0\0\0\u01ceF\1\0\0\0\u01cf\u01d0\5\ufff0"+
		"\0\0\u01d0H\1\0\0\0\u01d1\u01d2\5\ufff1\0\0\u01d2J\1\0\0\0\u01d3\u01d4"+
		"\t\0\0\0\u01d4L\1\0\0\0\u01d5\u01d7\3\61\23\0\u01d7N\1\0\0\0\u01d8\u01db"+
		"\b\5\0\0\u01da\u01d8\1\0\0\0\u01db\u01dc\1\0\0\0\u01dc\u01da\1\0\0\0\u01dc"+
		"\u01dd\1\0\0\0\u01dd\u01de\1\0\0\0\u01deP\1\0\0\0\u01df\u01e0\5*\0\0\u01e0"+
		"\u01e1\5/\0\0\u01e1\u01e2\1\0\0\0\u01e2R\1\0\0\0\u01e3\u01e5\5*\0\0\u01e5"+
		"T\1\0\0\0\u01e6\u01e8\t\0\0\0\u01e8V\1\0\0\0\u01e9\u01eb\3\61\23\0\u01eb"+
		"X\1\0\0\0\u01ec\u01ef\b\6\0\0\u01ee\u01ec\1\0\0\0\u01ef\u01f0\1\0\0\0"+
		"\u01f0\u01ee\1\0\0\0\u01f0\u01f1\1\0\0\0\u01f1\u01f2\1\0\0\0\u01f2Z\1"+
		"\0\0\0\u01f3\u01f5\5!\0\0\u01f5\u01f7\3I\37\0\u01f7\\\1\0\0\0\u01f8\u01fa"+
		"\5!\0\0\u01fa^\1\0\0\0\u01fb\u01fd\t\0\0\0\u01fd`\1\0\0\0\u01fe\u01ff"+
		"\3\63\24\0\u01ffb\1\0\0\0\u0200\u0202\3/\22\0\u0202d\1\0\0\0\u0203\u0205"+
		"\3!\13\0\u0205f\1\0\0\0\u0206\u0207\3\17\2\0\u0207h\1\0\0\0\u0208\u020a"+
		"\3\61\23\0\u020aj\1\0\0\0\u020b\u020d\3G\36\0\u020d\u020f\5!\0\0\u020f"+
		"l\1\0\0\0\u0210\u0212\3G\36\0\u0212n\1\0\0\0\u0213\u0216\b\7\0\0\u0215"+
		"\u0213\1\0\0\0\u0216\u0217\1\0\0\0\u0217\u0215\1\0\0\0\u0217\u0218\1\0"+
		"\0\0\u0218p\1\0\0\0\u0219\u021f\5\\\0\0\u021b\u0220\t\0\0\0\u021d\u0220"+
		"\5\uffff\0\0\u021f\u021b\1\0\0\0\u021f\u021d\1\0\0\0\u0220\u0221\1\0\0"+
		"\0\u0221r\1\0\0\0\u0222\u0224\t\0\0\0\u0224t\1\0\0\0\u0225\u0227\3/\22"+
		"\0\u0227v\1\0\0\0\u0228\u022a\3\61\23\0\u022ax\1\0\0\0\u022b\u022d\3\17"+
		"\2\0\u022dz\1\0\0\0\u022e\u022f\3\63\24\0\u022f|\1\0\0\0\u0230\u0232\5"+
		"|\0\0\u0232~\1\0\0\0\u0233\u0235\t\0\0\0\u0235\u0080\1\0\0\0\u0236\u0238"+
		"\3\61\23\0\u0238\u0082\1\0\0\0\u0239\u023b\3G\36\0\u023b\u023d\5!\0\0"+
		"\u023d\u0084\1\0\0\0\u023e\u0240\3G\36\0\u0240\u0086\1\0\0\0\u0241\u0244"+
		"\b\b\0\0\u0243\u0241\1\0\0\0\u0244\u0245\1\0\0\0\u0245\u0243\1\0\0\0\u0245"+
		"\u0246\1\0\0\0\u0246\u0247\1\0\0\0\u0247\u0088\1\0\0\0\u0248\u024e\5\\"+
		"\0\0\u024a\u024f\t\0\0\0\u024c\u024f\5\uffff\0\0\u024e\u024a\1\0\0\0\u024e"+
		"\u024c\1\0\0\0\u024f\u0250\1\0\0\0\u0250\u008a\1\0\0\0\u0251\u0252\5>"+
		"\0\0\u0252\u0253\5>\0\0\u0253\u0254\1\0\0\0\u0254\u008c\1\0\0\0\u0255"+
		"\u0257\5>\0\0\u0257\u008e\1\0\0\0\u0258\u025a\t\0\0\0\u025a\u0090\1\0"+
		"\0\0\u025b\u025d\3\61\23\0\u025d\u0092\1\0\0\0\u025e\u0260\3G\36\0\u0260"+
		"\u0262\5!\0\0\u0262\u0094\1\0\0\0\u0263\u0265\3G\36\0\u0265\u0096\1\0"+
		"\0\0\u0266\u0269\b\t\0\0\u0268\u0266\1\0\0\0\u0269\u026a\1\0\0\0\u026a"+
		"\u0268\1\0\0\0\u026a\u026b\1\0\0\0\u026b\u026c\1\0\0\0\u026c\u0098\1\0"+
		"\0\0\u026d\u0273\5\\\0\0\u026f\u0274\t\0\0\0\u0271\u0274\5\uffff\0\0\u0273"+
		"\u026f\1\0\0\0\u0273\u0271\1\0\0\0\u0274\u0275\1\0\0\0\u0275\u009a\1\0"+
		"\0\0\u0276\u0277\5%\0\0\u0277\u0278\5>\0\0\u0278\u0279\1\0\0\0\u0279\u009c"+
		"\1\0\0\0\u027a\u027c\5%\0\0\u027c\u009e\1\0\0\0\u027d\u027f\t\0\0\0\u027f"+
		"\u00a0\1\0\0\0\u0280\u0282\3\61\23\0\u0282\u00a2\1\0\0\0\u0283\u0285\3"+
		"\31\7\0\u0285\u00a4\1\0\0\0\u0286\u0288\3\21\3\0\u0288\u00a6\1\0\0\0\u0289"+
		"\u028b\3\17\2\0\u028b\u00a8\1\0\0\0\u028c\u028e\3\23\4\0\u028e\u00aa\1"+
		"\0\0\0\u028f\u0291\3\13\0\0\u0291\u00ac\1\0\0\0\u0292\u0294\3\r\1\0\u0294"+
		"\u00ae\1\0\0\0\u0295\u0297\3\33\b\0\u0297\u00b0\1\0\0\0\u0298\u029a\3"+
		"\35\t\0\u029a\u00b2\1\0\0\0\u029b\u029d\3\27\6\0\u029d\u00b4\1\0\0\0\u029e"+
		"\u02a0\3\37\n\0\u02a0\u00b6\1\0\0\0\u02a1\u02a3\3/\22\0\u02a3\u00b8\1"+
		"\0\0\0\u02a4\u02a5\5s\0\0\u02a5\u02a6\5u\0\0\u02a6\u02a7\5p\0\0\u02a7"+
		"\u02a8\5e\0\0\u02a8\u02a9\5r\0\0\u02a9\u00ba\1\0\0\0\u02aa\u02ab\5i\0"+
		"\0\u02ab\u02ac\5f\0\0\u02ac\u00bc\1\0\0\0\u02ad\u02ae\5e\0\0\u02ae\u02af"+
		"\5l\0\0\u02af\u02b0\5s\0\0\u02b0\u02b1\5e\0\0\u02b1\u02b2\5i\0\0\u02b2"+
		"\u02b3\5f\0\0\u02b3\u00be\1\0\0\0\u02b4\u02b5\5e\0\0\u02b5\u02b6\5n\0"+
		"\0\u02b6\u02b7\5d\0\0\u02b7\u02b8\5i\0\0\u02b8\u02b9\5f\0\0\u02b9\u00c0"+
		"\1\0\0\0\u02ba\u02bb\5e\0\0\u02bb\u02bc\5l\0\0\u02bc\u02bd\5s\0\0\u02bd"+
		"\u02be\5e\0\0\u02be\u00c2\1\0\0\0\u02bf\u02c0\5e\0\0\u02c0\u02c1\5n\0"+
		"\0\u02c1\u02c2\5d\0\0\u02c2\u00c4\1\0\0\0\u02c3\u02c4\5.\0\0\u02c4\u02c5"+
		"\5.\0\0\u02c5\u02c6\5.\0\0\u02c6\u00c6\1\0\0\0\u02c7\u02c8\5!\0\0\u02c8"+
		"\u00c8\1\0\0\0\u02c9\u02ca\5|\0\0\u02ca\u02cb\5|\0\0\u02cb\u00ca\1\0\0"+
		"\0\u02cc\u02cd\5&\0\0\u02cd\u02ce\5&\0\0\u02ce\u00cc\1\0\0\0\u02cf\u02d0"+
		"\5;\0\0\u02d0\u00ce\1\0\0\0\u02d1\u02d7\5\\\0\0\u02d3\u02d6\b\n\0\0\u02d5"+
		"\u02d3\1\0\0\0\u02d6\u02d9\1\0\0\0\u02d7\u02d5\1\0\0\0\u02d7\u02d8\1\0"+
		"\0\0\u02d8\u00d0\1\0\0\0\u02d9\u02d7\1\0\0\0\u02da\u02dc\5@\0\0\u02dc"+
		"\u02dd\3\63\24\0\u02dd\u00d2\1\0\0\0\u02de\u02e0\3\63\24\0\u02e0\u00d4"+
		"\1\0\0\0\u02e1\u02e3\3I\37\0\u02e3\u00d6\1\0\0\0\u02e4\u02f2\5\"\0\0\u02e6"+
		"\u02f1\b\13\0\0\u02e8\u02ee\5\\\0\0\u02ea\u02ef\t\0\0\0\u02ec\u02ef\5"+
		"\uffff\0\0\u02ee\u02ea\1\0\0\0\u02ee\u02ec\1\0\0\0\u02ef\u02f1\1\0\0\0"+
		"\u02f0\u02e6\1\0\0\0\u02f0\u02e8\1\0\0\0\u02f1\u02f4\1\0\0\0\u02f2\u02f0"+
		"\1\0\0\0\u02f2\u02f3\1\0\0\0\u02f3\u02f5\1\0\0\0\u02f4\u02f2\1\0\0\0\u02f5"+
		"\u02f7\5\"\0\0\u02f7\u00d8\1\0\0\0\u02f8\u02fa\t\0\0\0\u02fa\u00da\1\0"+
		"\0\0\u02fb\u02fd\3\61\23\0\u02fd\u00dc\1\0\0\0\u02fe\u0300\3G\36\0\u0300"+
		"\u0302\5!\0\0\u0302\u00de\1\0\0\0\u0303\u0305\3G\36\0\u0305\u00e0\1\0"+
		"\0\0\u0306\u0309\b\f\0\0\u0308\u0306\1\0\0\0\u0309\u030a\1\0\0\0\u030a"+
		"\u0308\1\0\0\0\u030a\u030b\1\0\0\0\u030b\u030c\1\0\0\0\u030c\u00e2\1\0"+
		"\0\0\u030d\u0313\5\\\0\0\u030f\u0314\t\0\0\0\u0311\u0314\5\uffff\0\0\u0313"+
		"\u030f\1\0\0\0\u0313\u0311\1\0\0\0\u0314\u0315\1\0\0\0\u0315\u00e4\1\0"+
		"\0\0\u0316\u0318\5\"\0\0\u0318\u00e6\1\0\0\0\u0319\u031b\t\0\0\0\u031b"+
		"\u00e8\1\0\0\0\u031c\u031e\3/\22\0\u031e\u00ea\1\0\0\0\u031f\u0321\3\61"+
		"\23\0\u0321\u00ec\1\0\0\0\u0322\u0323\3C\34\0\u0323\u00ee\1\0\0\0\u0324"+
		"\u0326\3\17\2\0\u0326\u00f0\1\0\0\0\u0327\u0329\3/\22\0\u0329\u00f2\1"+
		"\0\0\0\u032a\u032c\3\61\23\0\u032c\u00f4\1\0\0\0\u032d\u032f\3C\34\0\u032f"+
		"\u00f6\1\0\0\0*\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\b\1\t\1\n\1\u0111\1\u0143"+
		"\1\u0149\1\u014f\1\u0153\1\u015b\1\u0170\1\u017b\1\u0184\1\u018d\1\u0194"+
		"\1\u019b\1\u01a4\1\u01ad\1\u01b6\1\u01c1\1\u01cd\1\u01dc\1\u01f0\1\u0217"+
		"\1\u021f\1\u0245\1\u024e\1\u026a\1\u0273\1\u02d7\1\u02ee\1\u02f0\1\u02f2"+
		"\1\u030a\1\u0313\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}