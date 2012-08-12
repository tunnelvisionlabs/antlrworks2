// $ANTLR ANTLRVersion> AbstractTemplateLexer.java generatedTimestamp>
/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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

public abstract class AbstractTemplateLexer extends Lexer {
	public static final int
		OPEN_DELIMITER=1, CLOSE_DELIMITER=2, QUOTE=3, STRING=4, LPAREN=5, RPAREN=6, 
		COMMA=7, DOT=8, COLON=9, DEFINED=10, EQUALS=11, AT=12, LBRACK=13, RBRACK=14, 
		LBRACE=15, RBRACE=16, GROUP=17, DEFAULT=18, IMPORT=19, TRUE=20, FALSE=21, 
		DELIMITERS=22, WS=23, NEWLINE=24, ID=25, BIGSTRING=26, BIGSTRINGLINE=27, 
		LINE_COMMENT=28, COMMENT=29, ANYCHAR=30, TEXT=31, TEMPLATE_PARAMETER=32, 
		PIPE=33, BigStringTemplate_END=34, BigStringLineTemplate_END=35, SUPER=36, 
		IF=37, ELSEIF=38, ENDIF=39, ELSE=40, END=41, ELLIPSIS=42, NOT=43, OR=44, 
		AND=45, SEMI=46, ESCAPE=47, REGION_END=48, REGION_ID=49, DelimitersOpenSpec_DELIMITER_STRING=50, 
		DelimitersCloseSpec_DELIMITER_STRING=51, BigStringTemplate_LANGLE=52, 
		BigStringLineTemplate_PERCENT=53, StringTemplate_END=54;
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
	public static String[] modeNames = {
		"DEFAULT_MODE", "BlockComment", "TemplateComment", "AnonymousTemplate", 
		"AnonymousTemplateParameters", "BigStringTemplate", "BigStringLineTemplate", 
		"TemplateExpression", "StringTemplate", "DelimitersOpenSpec", "DelimitersCloseSpec"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"OPEN_DELIMITER", "CLOSE_DELIMITER", "QUOTE", "STRING", "'('", "')'", 
		"','", "'.'", "':'", "'::='", "'='", "'@'", "'['", "']'", "LBRACE", "'}'", 
		"'group'", "'default'", "'import'", "'true'", "'false'", "DELIMITERS", 
		"WS", "NEWLINE", "ID", "'<<'", "'<%'", "LINE_COMMENT", "COMMENT", "ANYCHAR", 
		"TEXT", "TEMPLATE_PARAMETER", "'|'", "'>>'", "'%>'", "'super'", "'if'", 
		"'elseif'", "'endif'", "'else'", "'end'", "'...'", "'!'", "'||'", "'&&'", 
		"';'", "ESCAPE", "'@end'", "REGION_ID", "DelimitersOpenSpec_DELIMITER_STRING", 
		"DelimitersCloseSpec_DELIMITER_STRING", "'>'", "'%'", "'\"'"
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


	public AbstractTemplateLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN);
	}

	@Override
	public String getGrammarFileName() { return "TemplateLexer.g4"; }

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
			case 0 : LPAREN_action(_localctx, actionIndex); break;

			case 1 : RPAREN_action(_localctx, actionIndex); break;

			case 2 : COMMA_action(_localctx, actionIndex); break;

			case 3 : DOT_action(_localctx, actionIndex); break;

			case 4 : COLON_action(_localctx, actionIndex); break;

			case 5 : DEFINED_action(_localctx, actionIndex); break;

			case 6 : EQUALS_action(_localctx, actionIndex); break;

			case 7 : AT_action(_localctx, actionIndex); break;

			case 8 : LBRACK_action(_localctx, actionIndex); break;

			case 9 : RBRACK_action(_localctx, actionIndex); break;

			case 10 : LBRACE_action(_localctx, actionIndex); break;

			case 11 : RBRACE_action(_localctx, actionIndex); break;

			case 12 : GROUP_action(_localctx, actionIndex); break;

			case 13 : DEFAULT_action(_localctx, actionIndex); break;

			case 14 : IMPORT_action(_localctx, actionIndex); break;

			case 15 : TRUE_action(_localctx, actionIndex); break;

			case 16 : FALSE_action(_localctx, actionIndex); break;

			case 17 : DELIMITERS_action(_localctx, actionIndex); break;

			case 18 : WS_action(_localctx, actionIndex); break;

			case 19 : NEWLINE_action(_localctx, actionIndex); break;

			case 20 : ID_action(_localctx, actionIndex); break;

			case 21 : STRING_action(_localctx, actionIndex); break;

			case 22 : BIGSTRING_action(_localctx, actionIndex); break;

			case 23 : BIGSTRINGLINE_action(_localctx, actionIndex); break;

			case 24 : LINE_COMMENT_action(_localctx, actionIndex); break;

			case 25 : COMMENT_action(_localctx, actionIndex); break;

			case 26 : ANONYMOUS_PARAMETERS_action(_localctx, actionIndex); break;

			case 27 : DELIMITERS_SPEC_action(_localctx, actionIndex); break;

			case 28 : DELIMITER_STRING_action(_localctx, actionIndex); break;

			case 29 : WS_CHAR_action(_localctx, actionIndex); break;

			case 30 : OPEN_DELIMITER_action(_localctx, actionIndex); break;

			case 31 : CLOSE_DELIMITER_action(_localctx, actionIndex); break;

			case 32 : ANYCHAR_action(_localctx, actionIndex); break;

			case 33 : BlockComment_NEWLINE_action(_localctx, actionIndex); break;

			case 34 : CONTINUE_COMMENT_action(_localctx, actionIndex); break;

			case 35 : END_COMMENT_action(_localctx, actionIndex); break;

			case 36 : BLOCK_COMMENT_STAR_action(_localctx, actionIndex); break;

			case 37 : BlockComment_ANYCHAR_action(_localctx, actionIndex); break;

			case 38 : TemplateComment_NEWLINE_action(_localctx, actionIndex); break;

			case 39 : TemplateComment_CONTINUE_COMMENT_action(_localctx, actionIndex); break;

			case 40 : TemplateComment_END_COMMENT_action(_localctx, actionIndex); break;

			case 41 : TemplateComment_BLOCK_COMMENT_BANG_action(_localctx, actionIndex); break;

			case 42 : TemplateComment_ANYCHAR_action(_localctx, actionIndex); break;

			case 43 : AnonymousTemplate_RBRACE_action(_localctx, actionIndex); break;

			case 44 : AnonymousTemplate_NEWLINE_action(_localctx, actionIndex); break;

			case 45 : AnonymousTemplate_COMMENT_action(_localctx, actionIndex); break;

			case 46 : AnonymousTemplate_OPEN_DELIMITER_action(_localctx, actionIndex); break;

			case 47 : TEXT_action(_localctx, actionIndex); break;

			case 48 : ESCAPE_RBRACE_action(_localctx, actionIndex); break;

			case 49 : AnonymousTemplate_ANYCHAR_action(_localctx, actionIndex); break;

			case 50 : AnonymousTemplateParameters_WS_action(_localctx, actionIndex); break;

			case 51 : AnonymousTemplateParameters_NEWLINE_action(_localctx, actionIndex); break;

			case 52 : AnonymousTemplateParameters_COMMA_action(_localctx, actionIndex); break;

			case 53 : TEMPLATE_PARAMETER_action(_localctx, actionIndex); break;

			case 54 : PIPE_action(_localctx, actionIndex); break;

			case 55 : AnonymousTemplateParameters_ANYCHAR_action(_localctx, actionIndex); break;

			case 56 : BigStringTemplate_NEWLINE_action(_localctx, actionIndex); break;

			case 57 : BigStringTemplate_COMMENT_action(_localctx, actionIndex); break;

			case 58 : BigStringTemplate_OPEN_DELIMITER_action(_localctx, actionIndex); break;

			case 59 : BigStringTemplate_TEXT_action(_localctx, actionIndex); break;

			case 60 : BigStringTemplate_ESCAPE_action(_localctx, actionIndex); break;

			case 61 : BigStringTemplate_END_action(_localctx, actionIndex); break;

			case 62 : BigStringTemplate_LANGLE_action(_localctx, actionIndex); break;

			case 63 : BigStringTemplate_ANYCHAR_action(_localctx, actionIndex); break;

			case 64 : BigStringLineTemplate_NEWLINE_action(_localctx, actionIndex); break;

			case 65 : BigStringLineTemplate_COMMENT_action(_localctx, actionIndex); break;

			case 66 : BigStringLineTemplate_OPEN_DELIMITER_action(_localctx, actionIndex); break;

			case 67 : BigStringLineTemplate_TEXT_action(_localctx, actionIndex); break;

			case 68 : BigStringLineTemplate_ESCAPE_action(_localctx, actionIndex); break;

			case 69 : BigStringLineTemplate_END_action(_localctx, actionIndex); break;

			case 70 : BigStringLineTemplate_PERCENT_action(_localctx, actionIndex); break;

			case 71 : BigStringLineTemplate_ANYCHAR_action(_localctx, actionIndex); break;

			case 72 : TemplateExpression_NEWLINE_action(_localctx, actionIndex); break;

			case 73 : TemplateExpression_DOT_action(_localctx, actionIndex); break;

			case 74 : TemplateExpression_COMMA_action(_localctx, actionIndex); break;

			case 75 : TemplateExpression_COLON_action(_localctx, actionIndex); break;

			case 76 : TemplateExpression_LPAREN_action(_localctx, actionIndex); break;

			case 77 : TemplateExpression_RPAREN_action(_localctx, actionIndex); break;

			case 78 : TemplateExpression_LBRACK_action(_localctx, actionIndex); break;

			case 79 : TemplateExpression_RBRACK_action(_localctx, actionIndex); break;

			case 80 : TemplateExpression_EQUALS_action(_localctx, actionIndex); break;

			case 81 : TemplateExpression_LBRACE_action(_localctx, actionIndex); break;

			case 82 : TemplateExpression_WS_action(_localctx, actionIndex); break;

			case 83 : SUPER_action(_localctx, actionIndex); break;

			case 84 : IF_action(_localctx, actionIndex); break;

			case 85 : ELSEIF_action(_localctx, actionIndex); break;

			case 86 : ENDIF_action(_localctx, actionIndex); break;

			case 87 : ELSE_action(_localctx, actionIndex); break;

			case 88 : END_action(_localctx, actionIndex); break;

			case 89 : ELLIPSIS_action(_localctx, actionIndex); break;

			case 90 : NOT_action(_localctx, actionIndex); break;

			case 91 : OR_action(_localctx, actionIndex); break;

			case 92 : AND_action(_localctx, actionIndex); break;

			case 93 : SEMI_action(_localctx, actionIndex); break;

			case 94 : ESCAPE_action(_localctx, actionIndex); break;

			case 95 : REGION_END_action(_localctx, actionIndex); break;

			case 96 : REGION_ID_action(_localctx, actionIndex); break;

			case 97 : TemplateExpression_ID_action(_localctx, actionIndex); break;

			case 98 : TemplateExpression_CLOSE_DELIMITER_action(_localctx, actionIndex); break;

			case 99 : TemplateExpression_STRING_action(_localctx, actionIndex); break;

			case 100 : TemplateExpression_ANYCHAR_action(_localctx, actionIndex); break;

			case 101 : StringTemplate_NEWLINE_action(_localctx, actionIndex); break;

			case 102 : StringTemplate_COMMENT_action(_localctx, actionIndex); break;

			case 103 : StringTemplate_OPEN_DELIMITER_action(_localctx, actionIndex); break;

			case 104 : StringTemplate_TEXT_action(_localctx, actionIndex); break;

			case 105 : StringTemplate_ESCAPE_action(_localctx, actionIndex); break;

			case 106 : StringTemplate_END_action(_localctx, actionIndex); break;

			case 107 : StringTemplate_ANYCHAR_action(_localctx, actionIndex); break;

			case 108 : DelimitersOpenSpec_WS_action(_localctx, actionIndex); break;

			case 109 : DelimitersOpenSpec_NEWLINE_action(_localctx, actionIndex); break;

			case 110 : DelimitersOpenSpec_DELIMITER_STRING_action(_localctx, actionIndex); break;

			case 111 : DelimitersOpenSpec_COMMA_action(_localctx, actionIndex); break;

			case 112 : DelimitersCloseSpec_WS_action(_localctx, actionIndex); break;

			case 113 : DelimitersCloseSpec_NEWLINE_action(_localctx, actionIndex); break;

			case 114 : DelimitersCloseSpec_DELIMITER_STRING_action(_localctx, actionIndex); break;
		}
	}
	public void StringTemplate_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 60 : _type = NEWLINE;  break;
		}
	}
	public void BigStringLineTemplate_PERCENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 43 : _type = TEXT;  break;
		}
	}
	public void EQUALS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void NOT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void AnonymousTemplateParameters_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 25 : _type = NEWLINE; _channel = HIDDEN;  break;
		}
	}
	public void TemplateExpression_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 59 : _type = ANYCHAR;  break;
		}
	}
	public void TemplateExpression_CLOSE_DELIMITER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 57 : _type = CLOSE_DELIMITER; popMode();  break;
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
	public void ESCAPE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TemplateComment_CONTINUE_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 14 : _type = COMMENT; _channel = HIDDEN;  break;
		}
	}
	public void DELIMITER_STRING_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BigStringLineTemplate_OPEN_DELIMITER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 39 : _type = OPEN_DELIMITER; pushMode(TemplateExpression);  break;
		}
	}
	public void TemplateExpression_RPAREN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 50 : _type = RPAREN;  break;
		}
	}
	public void BIGSTRING_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 4 : pushMode(BigStringTemplate);  break;
		}
	}
	public void ENDIF_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CLOSE_DELIMITER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void SUPER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TemplateExpression_LBRACE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 54 : _type = LBRACE; pushMode(AnonymousTemplate);  break;
		}
	}
	public void TemplateExpression_ID_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 56 : _type = ID;  break;
		}
	}
	public void COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 7 : _channel = HIDDEN; pushMode(BlockComment);  break;
		}
	}
	public void DelimitersCloseSpec_WS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 70 : _type = WS;  break;
		}
	}
	public void TemplateExpression_LBRACK_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 51 : _type = LBRACK;  break;
		}
	}
	public void RBRACK_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BigStringTemplate_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 36 : _type = ANYCHAR;  break;
		}
	}
	public void RBRACE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LINE_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 6 : _channel = HIDDEN;  break;
		}
	}
	public void AnonymousTemplate_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 20 : _type = COMMENT; pushMode(TemplateComment);  break;
		}
	}
	public void OPEN_DELIMITER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ANONYMOUS_PARAMETERS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void DEFINED_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ELSE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ELLIPSIS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void DelimitersOpenSpec_WS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 67 : _type = WS;  break;
		}
	}
	public void BigStringLineTemplate_END_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 42 : popMode();  break;
		}
	}
	public void GROUP_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BigStringTemplate_ESCAPE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 33 : _type = TEXT;  break;
		}
	}
	public void WS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 1 : _channel = HIDDEN;  break;
		}
	}
	public void BIGSTRINGLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 5 : pushMode(BigStringLineTemplate);  break;
		}
	}
	public void AnonymousTemplateParameters_WS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 24 : _type = WS; _channel = HIDDEN;  break;
		}
	}
	public void AnonymousTemplateParameters_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 28 : _type = ANYCHAR;  break;
		}
	}
	public void OR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TemplateExpression_COLON_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 48 : _type = COLON;  break;
		}
	}
	public void END_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void FALSE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void DelimitersCloseSpec_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 71 : _type = NEWLINE;  break;
		}
	}
	public void BlockComment_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 8 : _type = NEWLINE; _channel = HIDDEN;  break;
		}
	}
	public void BigStringTemplate_END_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 34 : popMode();  break;
		}
	}
	public void DELIMITERS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TemplateExpression_LPAREN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 49 : _type = LPAREN;  break;
		}
	}
	public void TemplateComment_BLOCK_COMMENT_BANG_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 16 : _type = COMMENT; _channel = HIDDEN;  break;
		}
	}
	public void TemplateComment_END_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 15 : _type = COMMENT; _channel = HIDDEN; popMode();  break;
		}
	}
	public void BigStringTemplate_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 30 : _type = COMMENT; pushMode(TemplateComment);  break;
		}
	}
	public void LBRACK_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void StringTemplate_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 61 : _type = COMMENT; pushMode(TemplateComment);  break;
		}
	}
	public void END_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 10 : _type = COMMENT; _channel = HIDDEN; popMode();  break;
		}
	}
	public void REGION_END_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BigStringTemplate_OPEN_DELIMITER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 31 : _type = OPEN_DELIMITER; pushMode(TemplateExpression);  break;
		}
	}
	public void LBRACE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 0 : pushMode(AnonymousTemplate);  break;
		}
	}
	public void BigStringLineTemplate_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 37 : _type = NEWLINE;  break;
		}
	}
	public void StringTemplate_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 66 : _type = ANYCHAR;  break;
		}
	}
	public void ID_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void AND_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BigStringLineTemplate_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 44 : _type = ANYCHAR;  break;
		}
	}
	public void TemplateComment_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 17 : _type = ANYCHAR; _channel = HIDDEN;  break;
		}
	}
	public void LPAREN_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TemplateExpression_EQUALS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 53 : _type = EQUALS;  break;
		}
	}
	public void IF_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void StringTemplate_ESCAPE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 64 : _type = TEXT;  break;
		}
	}
	public void AT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void AnonymousTemplate_OPEN_DELIMITER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 21 : _type = OPEN_DELIMITER; pushMode(TemplateExpression);  break;
		}
	}
	public void AnonymousTemplate_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 23 : _type = ANYCHAR;  break;
		}
	}
	public void BigStringTemplate_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 29 : _type = NEWLINE;  break;
		}
	}
	public void DelimitersOpenSpec_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 68 : _type = NEWLINE;  break;
		}
	}
	public void DELIMITERS_SPEC_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void COMMA_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TemplateExpression_COMMA_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 47 : _type = COMMA;  break;
		}
	}
	public void BigStringLineTemplate_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 38 : _type = COMMENT; pushMode(TemplateComment);  break;
		}
	}
	public void AnonymousTemplate_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 19 : _type = NEWLINE;  break;
		}
	}
	public void CONTINUE_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 9 : _type = COMMENT; _channel = HIDDEN;  break;
		}
	}
	public void PIPE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 27 : popMode();  break;
		}
	}
	public void TEMPLATE_PARAMETER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void DOT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BigStringTemplate_TEXT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 32 : _type = TEXT;  break;
		}
	}
	public void AnonymousTemplate_RBRACE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 18 : _type = RBRACE; popMode();  break;
		}
	}
	public void AnonymousTemplateParameters_COMMA_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 26 : _type = COMMA;  break;
		}
	}
	public void TemplateExpression_RBRACK_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 52 : _type = RBRACK;  break;
		}
	}
	public void BigStringLineTemplate_TEXT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 40 : _type = TEXT;  break;
		}
	}
	public void WS_CHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BigStringLineTemplate_ESCAPE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 41 : _type = TEXT;  break;
		}
	}
	public void DEFAULT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TemplateExpression_WS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 55 : _type = WS; _channel = HIDDEN;  break;
		}
	}
	public void TemplateExpression_DOT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 46 : _type = DOT;  break;
		}
	}
	public void TEXT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TemplateExpression_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 45 : _type = NEWLINE; _channel = HIDDEN;  break;
		}
	}
	public void TRUE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void SEMI_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void StringTemplate_OPEN_DELIMITER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 62 : _type = OPEN_DELIMITER; pushMode(TemplateExpression);  break;
		}
	}
	public void ELSEIF_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void DelimitersOpenSpec_DELIMITER_STRING_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void TemplateExpression_STRING_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 58 : _type = STRING;  break;
		}
	}
	public void COLON_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ESCAPE_RBRACE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 22 : _type = TEXT;  break;
		}
	}
	public void BlockComment_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 12 : _type = ANYCHAR; _channel = HIDDEN;  break;
		}
	}
	public void NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 2 : _channel = HIDDEN;  break;
		}
	}
	public void TemplateComment_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 13 : _type = NEWLINE; _channel = HIDDEN;  break;
		}
	}
	public void StringTemplate_TEXT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 63 : _type = TEXT;  break;
		}
	}
	public void StringTemplate_END_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 65 : _type = QUOTE; popMode();  break;
		}
	}
	public void REGION_ID_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void DelimitersCloseSpec_DELIMITER_STRING_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 72 : popMode();  break;
		}
	}
	public void BLOCK_COMMENT_STAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 11 : _type = COMMENT; _channel = HIDDEN;  break;
		}
	}
	public void DelimitersOpenSpec_COMMA_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 69 : _type = COMMA; _mode = DelimitersCloseSpec;  break;
		}
	}
	public void BigStringTemplate_LANGLE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 35 : _type = TEXT;  break;
		}
	}
	public void STRING_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 3 : _type = QUOTE; pushMode(StringTemplate);  break;
		}
	}

	public static final String _serializedATN =
		"\2\66\u0333\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\6"+
		"\uffff\6\uffff\6\uffff\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4"+
		"\2\5\7\5\2\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r"+
		"\7\r\2\16\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24"+
		"\7\24\2\25\7\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33"+
		"\7\33\2\34\7\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7"+
		"#\2$\7$\2%\7%\2&\7&\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2.\7."+
		"\2/\7/\2\60\7\60\2\61\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65\2\66"+
		"\7\66\2\67\7\67\28\78\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2@\7@"+
		"\2A\7A\2B\7B\2C\7C\2D\7D\2E\7E\2F\7F\2G\7G\2H\7H\2I\7I\2J\7J\2K\7K\2L"+
		"\7L\2M\7M\2N\7N\2O\7O\2P\7P\2Q\7Q\2R\7R\2S\7S\2T\7T\2U\7U\2V\7V\2W\7W"+
		"\2X\7X\2Y\7Y\2Z\7Z\2[\7[\2\\\7\\\2]\7]\2^\7^\2_\7_\2`\7`\2a\7a\2b\7b\2"+
		"c\7c\2d\7d\2e\7e\2f\7f\2g\7g\2h\7h\2i\7i\2j\7j\2k\7k\2l\7l\2m\7m\2n\7"+
		"n\2o\7o\2p\7p\2q\7q\2r\7r\1\0\1\0\1\1\1\1\1\2\1\2\1\3\1\3\1\4\1\4\1\5"+
		"\1\5\1\5\1\5\1\6\1\6\1\7\1\7\1\b\1\b\1\t\1\t\1\n\1\n\3\n\u010a\b\n\1\n"+
		"\1\n\1\13\1\13\1\f\1\f\1\f\1\f\1\f\1\f\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r"+
		"\1\16\1\16\1\16\1\16\1\16\1\16\1\16\1\17\1\17\1\17\1\17\1\17\1\20\1\20"+
		"\1\20\1\20\1\20\1\20\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\21"+
		"\1\21\1\21\3\21\u013c\b\21\1\22\4\22\u013f\b\22\13\22\f\22\u0140\1\22"+
		"\1\22\1\23\1\23\3\23\u0147\b\23\1\23\3\23\u014a\b\23\1\23\1\23\1\24\1"+
		"\24\5\24\u0150\b\24\n\24\f\24\u0153\t\24\1\25\1\25\1\25\1\25\1\26\1\26"+
		"\1\26\1\26\1\26\1\27\1\27\1\27\1\27\1\27\1\30\1\30\1\30\1\30\5\30\u0167"+
		"\b\30\n\30\f\30\u016a\t\30\1\30\1\30\1\31\1\31\1\31\1\31\1\31\1\32\5\32"+
		"\u0174\b\32\n\32\f\32\u0177\t\32\1\32\1\32\5\32\u017b\b\32\n\32\f\32\u017e"+
		"\t\32\1\32\1\32\5\32\u0182\b\32\n\32\f\32\u0185\t\32\1\32\1\32\5\32\u0189"+
		"\b\32\n\32\f\32\u018c\t\32\1\32\5\32\u018f\b\32\n\32\f\32\u0192\t\32\1"+
		"\32\1\32\1\33\5\33\u0197\b\33\n\33\f\33\u019a\t\33\1\33\1\33\5\33\u019e"+
		"\b\33\n\33\f\33\u01a1\t\33\1\33\1\33\5\33\u01a5\b\33\n\33\f\33\u01a8\t"+
		"\33\1\33\1\33\1\34\1\34\4\34\u01ae\b\34\13\34\f\34\u01af\1\34\1\34\1\35"+
		"\1\35\1\36\1\36\1\37\1\37\1 \1 \1!\1!\1!\1!\1\"\4\"\u01c1\b\"\13\"\f\""+
		"\u01c2\1\"\1\"\1#\1#\1#\1#\1#\1$\1$\1$\1$\1%\1%\1%\1%\1&\1&\1&\1&\1\'"+
		"\4\'\u01d9\b\'\13\'\f\'\u01da\1\'\1\'\1(\1(\1(\1(\1(\1)\1)\1)\1)\1*\1"+
		"*\1*\1*\1+\1+\1+\1+\1,\1,\1,\1,\1-\1-\1-\1-\1-\1.\1.\1.\1.\1/\4/\u01fe"+
		"\b/\13/\f/\u01ff\1\60\1\60\1\60\3\60\u0205\b\60\1\60\1\60\1\61\1\61\1"+
		"\61\1\61\1\62\1\62\1\62\1\62\1\63\1\63\1\63\1\63\1\64\1\64\1\64\1\64\1"+
		"\65\1\65\1\66\1\66\1\66\1\66\1\67\1\67\1\67\1\67\18\18\18\18\19\19\19"+
		"\19\19\1:\1:\1:\1:\1;\4;\u0231\b;\13;\f;\u0232\1;\1;\1<\1<\1<\3<\u023a"+
		"\b<\1<\1<\1=\1=\1=\1=\1=\1>\1>\1>\1>\1?\1?\1?\1?\1@\1@\1@\1@\1A\1A\1A"+
		"\1A\1A\1B\1B\1B\1B\1C\4C\u0259\bC\13C\fC\u025a\1C\1C\1D\1D\1D\3D\u0262"+
		"\bD\1D\1D\1E\1E\1E\1E\1E\1F\1F\1F\1F\1G\1G\1G\1G\1H\1H\1H\1H\1I\1I\1I"+
		"\1I\1J\1J\1J\1J\1K\1K\1K\1K\1L\1L\1L\1L\1M\1M\1M\1M\1N\1N\1N\1N\1O\1O"+
		"\1O\1O\1P\1P\1P\1P\1Q\1Q\1Q\1Q\1R\1R\1R\1R\1S\1S\1S\1S\1S\1S\1T\1T\1T"+
		"\1U\1U\1U\1U\1U\1U\1U\1V\1V\1V\1V\1V\1V\1W\1W\1W\1W\1W\1X\1X\1X\1X\1Y"+
		"\1Y\1Y\1Y\1Z\1Z\1[\1[\1[\1\\\1\\\1\\\1]\1]\1^\1^\5^\u02ce\b^\n^\f^\u02d1"+
		"\t^\1_\1_\1_\1_\1_\1`\1`\1`\1a\1a\1a\1a\1b\1b\1b\1b\1c\1c\1c\1c\1c\3c"+
		"\u02e8\bc\5c\u02ea\bc\nc\fc\u02ed\tc\1c\1c\1c\1c\1d\1d\1d\1d\1e\1e\1e"+
		"\1e\1f\1f\1f\1f\1f\1g\1g\1g\1g\1h\4h\u0305\bh\13h\fh\u0306\1h\1h\1i\1"+
		"i\1i\3i\u030e\bi\1i\1i\1j\1j\1j\1j\1k\1k\1k\1k\1l\1l\1l\1l\1m\1m\1m\1"+
		"m\1n\1n\1o\1o\1o\1o\1p\1p\1p\1p\1q\1q\1q\1q\1r\1r\1r\1rs\13\5\uffff\r"+
		"\6\uffff\17\7\uffff\21\b\uffff\23\t\uffff\25\n\uffff\27\13\uffff\31\f"+
		"\uffff\33\r\uffff\35\16\uffff\37\17\0!\20\uffff#\21\uffff%\22\uffff\'"+
		"\23\uffff)\24\uffff+\25\uffff-\26\uffff/\27\1\61\30\2\63\31\uffff\65\4"+
		"\3\67\32\49\33\5;\34\6=\35\7?\0\uffffA\0\uffffC\0\uffffE\0\uffffG\1\uffff"+
		"I\2\uffffK\36\uffffM\0\bO\0\tQ\0\nS\0\13U\0\fW\0\rY\0\16[\0\17]\0\20_"+
		"\0\21a\0\22c\0\23e\0\24g\0\25i\37\uffffk\0\26m\0\27o\0\30q\0\31s\0\32"+
		"u \uffffw!\33y\0\34{\0\35}\0\36\177\0\37\u0081\0 \u0083\0!\u0085\"\"\u0087"+
		"\64#\u0089\0$\u008b\0%\u008d\0&\u008f\0\'\u0091\0(\u0093\0)\u0095#*\u0097"+
		"\65+\u0099\0,\u009b\0-\u009d\0.\u009f\0/\u00a1\0\60\u00a3\0\61\u00a5\0"+
		"\62\u00a7\0\63\u00a9\0\64\u00ab\0\65\u00ad\0\66\u00af\0\67\u00b1$\uffff"+
		"\u00b3%\uffff\u00b5&\uffff\u00b7\'\uffff\u00b9(\uffff\u00bb)\uffff\u00bd"+
		"*\uffff\u00bf+\uffff\u00c1,\uffff\u00c3-\uffff\u00c5.\uffff\u00c7/\uffff"+
		"\u00c9\60\uffff\u00cb\61\uffff\u00cd\08\u00cf\09\u00d1\0:\u00d3\0;\u00d5"+
		"\0<\u00d7\0=\u00d9\0>\u00db\0?\u00dd\0@\u00df\66A\u00e1\0B\u00e3\0C\u00e5"+
		"\0D\u00e7\62\uffff\u00e9\0E\u00eb\0F\u00ed\0G\u00ef\63H\13\0\1\2\3\4\5"+
		"\6\7\b\t\n\16\2\t\t  \3AZ__az\5--\609AZ__az\2\n\n\r\r\3\n\n\r\r\"\"\3"+
		"\t\n\r\r  \3\n\n\r\r**\3\n\n\r\r!!\5\n\n\r\r\\\\}}\ufff0\ufff0\5\n\n\r"+
		"\r>>\\\\\ufff0\ufff0\5\n\n\r\r%%\\\\\ufff0\ufff0\1\ufff1\ufff1\2\"\"\\"+
		"\\\5\n\n\r\r\"\"\\\\\ufff0\ufff0\u0340\0\13\1\0\0\0\0\r\1\0\0\0\0\17\1"+
		"\0\0\0\0\21\1\0\0\0\0\23\1\0\0\0\0\25\1\0\0\0\0\27\1\0\0\0\0\31\1\0\0"+
		"\0\0\33\1\0\0\0\0\35\1\0\0\0\0\37\1\0\0\0\0!\1\0\0\0\0#\1\0\0\0\0%\1\0"+
		"\0\0\0\'\1\0\0\0\0)\1\0\0\0\0+\1\0\0\0\0-\1\0\0\0\0/\1\0\0\0\0\61\1\0"+
		"\0\0\0\63\1\0\0\0\0\65\1\0\0\0\0\67\1\0\0\0\09\1\0\0\0\0;\1\0\0\0\0=\1"+
		"\0\0\0\0K\1\0\0\0\1M\1\0\0\0\1O\1\0\0\0\1Q\1\0\0\0\1S\1\0\0\0\1U\1\0\0"+
		"\0\2W\1\0\0\0\2Y\1\0\0\0\2[\1\0\0\0\2]\1\0\0\0\2_\1\0\0\0\3a\1\0\0\0\3"+
		"c\1\0\0\0\3e\1\0\0\0\3g\1\0\0\0\3i\1\0\0\0\3k\1\0\0\0\3m\1\0\0\0\4o\1"+
		"\0\0\0\4q\1\0\0\0\4s\1\0\0\0\4u\1\0\0\0\4w\1\0\0\0\4y\1\0\0\0\5{\1\0\0"+
		"\0\5}\1\0\0\0\5\177\1\0\0\0\5\u0081\1\0\0\0\5\u0083\1\0\0\0\5\u0085\1"+
		"\0\0\0\5\u0087\1\0\0\0\5\u0089\1\0\0\0\6\u008b\1\0\0\0\6\u008d\1\0\0\0"+
		"\6\u008f\1\0\0\0\6\u0091\1\0\0\0\6\u0093\1\0\0\0\6\u0095\1\0\0\0\6\u0097"+
		"\1\0\0\0\6\u0099\1\0\0\0\7\u009b\1\0\0\0\7\u009d\1\0\0\0\7\u009f\1\0\0"+
		"\0\7\u00a1\1\0\0\0\7\u00a3\1\0\0\0\7\u00a5\1\0\0\0\7\u00a7\1\0\0\0\7\u00a9"+
		"\1\0\0\0\7\u00ab\1\0\0\0\7\u00ad\1\0\0\0\7\u00af\1\0\0\0\7\u00b1\1\0\0"+
		"\0\7\u00b3\1\0\0\0\7\u00b5\1\0\0\0\7\u00b7\1\0\0\0\7\u00b9\1\0\0\0\7\u00bb"+
		"\1\0\0\0\7\u00bd\1\0\0\0\7\u00bf\1\0\0\0\7\u00c1\1\0\0\0\7\u00c3\1\0\0"+
		"\0\7\u00c5\1\0\0\0\7\u00c7\1\0\0\0\7\u00c9\1\0\0\0\7\u00cb\1\0\0\0\7\u00cd"+
		"\1\0\0\0\7\u00cf\1\0\0\0\7\u00d1\1\0\0\0\7\u00d3\1\0\0\0\b\u00d5\1\0\0"+
		"\0\b\u00d7\1\0\0\0\b\u00d9\1\0\0\0\b\u00db\1\0\0\0\b\u00dd\1\0\0\0\b\u00df"+
		"\1\0\0\0\b\u00e1\1\0\0\0\t\u00e3\1\0\0\0\t\u00e5\1\0\0\0\t\u00e7\1\0\0"+
		"\0\t\u00e9\1\0\0\0\n\u00eb\1\0\0\0\n\u00ed\1\0\0\0\n\u00ef\1\0\0\0\13"+
		"\u00f1\1\0\0\0\r\u00f3\1\0\0\0\17\u00f5\1\0\0\0\21\u00f7\1\0\0\0\23\u00f9"+
		"\1\0\0\0\25\u00fb\1\0\0\0\27\u00ff\1\0\0\0\31\u0101\1\0\0\0\33\u0103\1"+
		"\0\0\0\35\u0105\1\0\0\0\37\u0107\1\0\0\0!\u010d\1\0\0\0#\u010f\1\0\0\0"+
		"%\u0115\1\0\0\0\'\u011d\1\0\0\0)\u0124\1\0\0\0+\u0129\1\0\0\0-\u012f\1"+
		"\0\0\0/\u013e\1\0\0\0\61\u0149\1\0\0\0\63\u014d\1\0\0\0\65\u0154\1\0\0"+
		"\0\67\u0158\1\0\0\09\u015d\1\0\0\0;\u0162\1\0\0\0=\u016d\1\0\0\0?\u0175"+
		"\1\0\0\0A\u0198\1\0\0\0C\u01ab\1\0\0\0E\u01b3\1\0\0\0G\u01b5\1\0\0\0I"+
		"\u01b7\1\0\0\0K\u01b9\1\0\0\0M\u01bb\1\0\0\0O\u01c0\1\0\0\0Q\u01c6\1\0"+
		"\0\0S\u01cb\1\0\0\0U\u01cf\1\0\0\0W\u01d3\1\0\0\0Y\u01d8\1\0\0\0[\u01de"+
		"\1\0\0\0]\u01e3\1\0\0\0_\u01e7\1\0\0\0a\u01eb\1\0\0\0c\u01ef\1\0\0\0e"+
		"\u01f3\1\0\0\0g\u01f8\1\0\0\0i\u01fd\1\0\0\0k\u0201\1\0\0\0m\u0208\1\0"+
		"\0\0o\u020c\1\0\0\0q\u0210\1\0\0\0s\u0214\1\0\0\0u\u0218\1\0\0\0w\u021a"+
		"\1\0\0\0y\u021e\1\0\0\0{\u0222\1\0\0\0}\u0226\1\0\0\0\177\u022b\1\0\0"+
		"\0\u0081\u0230\1\0\0\0\u0083\u0236\1\0\0\0\u0085\u023d\1\0\0\0\u0087\u0242"+
		"\1\0\0\0\u0089\u0246\1\0\0\0\u008b\u024a\1\0\0\0\u008d\u024e\1\0\0\0\u008f"+
		"\u0253\1\0\0\0\u0091\u0258\1\0\0\0\u0093\u025e\1\0\0\0\u0095\u0265\1\0"+
		"\0\0\u0097\u026a\1\0\0\0\u0099\u026e\1\0\0\0\u009b\u0272\1\0\0\0\u009d"+
		"\u0276\1\0\0\0\u009f\u027a\1\0\0\0\u00a1\u027e\1\0\0\0\u00a3\u0282\1\0"+
		"\0\0\u00a5\u0286\1\0\0\0\u00a7\u028a\1\0\0\0\u00a9\u028e\1\0\0\0\u00ab"+
		"\u0292\1\0\0\0\u00ad\u0296\1\0\0\0\u00af\u029a\1\0\0\0\u00b1\u029e\1\0"+
		"\0\0\u00b3\u02a4\1\0\0\0\u00b5\u02a7\1\0\0\0\u00b7\u02ae\1\0\0\0\u00b9"+
		"\u02b4\1\0\0\0\u00bb\u02b9\1\0\0\0\u00bd\u02bd\1\0\0\0\u00bf\u02c1\1\0"+
		"\0\0\u00c1\u02c3\1\0\0\0\u00c3\u02c6\1\0\0\0\u00c5\u02c9\1\0\0\0\u00c7"+
		"\u02cb\1\0\0\0\u00c9\u02d2\1\0\0\0\u00cb\u02d7\1\0\0\0\u00cd\u02da\1\0"+
		"\0\0\u00cf\u02de\1\0\0\0\u00d1\u02e2\1\0\0\0\u00d3\u02f2\1\0\0\0\u00d5"+
		"\u02f6\1\0\0\0\u00d7\u02fa\1\0\0\0\u00d9\u02ff\1\0\0\0\u00db\u0304\1\0"+
		"\0\0\u00dd\u030a\1\0\0\0\u00df\u0311\1\0\0\0\u00e1\u0315\1\0\0\0\u00e3"+
		"\u0319\1\0\0\0\u00e5\u031d\1\0\0\0\u00e7\u0321\1\0\0\0\u00e9\u0323\1\0"+
		"\0\0\u00eb\u0327\1\0\0\0\u00ed\u032b\1\0\0\0\u00ef\u032f\1\0\0\0\u00f1"+
		"\u00f2\5(\0\0\u00f2\f\1\0\0\0\u00f3\u00f4\5)\0\0\u00f4\16\1\0\0\0\u00f5"+
		"\u00f6\5,\0\0\u00f6\20\1\0\0\0\u00f7\u00f8\5.\0\0\u00f8\22\1\0\0\0\u00f9"+
		"\u00fa\5:\0\0\u00fa\24\1\0\0\0\u00fb\u00fc\5:\0\0\u00fc\u00fd\5:\0\0\u00fd"+
		"\u00fe\5=\0\0\u00fe\26\1\0\0\0\u00ff\u0100\5=\0\0\u0100\30\1\0\0\0\u0101"+
		"\u0102\5@\0\0\u0102\32\1\0\0\0\u0103\u0104\5[\0\0\u0104\34\1\0\0\0\u0105"+
		"\u0106\5]\0\0\u0106\36\1\0\0\0\u0107\u0109\5{\0\0\u0108\u010a\3?\32\0"+
		"\u0109\u0108\1\0\0\0\u0109\u010a\1\0\0\0\u010a\u010b\1\0\0\0\u010b\u010c"+
		"\6\n\0\0\u010c \1\0\0\0\u010d\u010e\5}\0\0\u010e\"\1\0\0\0\u010f\u0110"+
		"\5g\0\0\u0110\u0111\5r\0\0\u0111\u0112\5o\0\0\u0112\u0113\5u\0\0\u0113"+
		"\u0114\5p\0\0\u0114$\1\0\0\0\u0115\u0116\5d\0\0\u0116\u0117\5e\0\0\u0117"+
		"\u0118\5f\0\0\u0118\u0119\5a\0\0\u0119\u011a\5u\0\0\u011a\u011b\5l\0\0"+
		"\u011b\u011c\5t\0\0\u011c&\1\0\0\0\u011d\u011e\5i\0\0\u011e\u011f\5m\0"+
		"\0\u011f\u0120\5p\0\0\u0120\u0121\5o\0\0\u0121\u0122\5r\0\0\u0122\u0123"+
		"\5t\0\0\u0123(\1\0\0\0\u0124\u0125\5t\0\0\u0125\u0126\5r\0\0\u0126\u0127"+
		"\5u\0\0\u0127\u0128\5e\0\0\u0128*\1\0\0\0\u0129\u012a\5f\0\0\u012a\u012b"+
		"\5a\0\0\u012b\u012c\5l\0\0\u012c\u012d\5s\0\0\u012d\u012e\5e\0\0\u012e"+
		",\1\0\0\0\u012f\u0130\5d\0\0\u0130\u0131\5e\0\0\u0131\u0132\5l\0\0\u0132"+
		"\u0133\5i\0\0\u0133\u0134\5m\0\0\u0134\u0135\5i\0\0\u0135\u0136\5t\0\0"+
		"\u0136\u0137\5e\0\0\u0137\u0138\5r\0\0\u0138\u0139\5s\0\0\u0139\u013b"+
		"\1\0\0\0\u013a\u013c\3A\33\0\u013b\u013a\1\0\0\0\u013b\u013c\1\0\0\0\u013c"+
		".\1\0\0\0\u013d\u013f\7\0\0\0\u013e\u013d\1\0\0\0\u013f\u0140\1\0\0\0"+
		"\u0140\u013e\1\0\0\0\u0140\u0141\1\0\0\0\u0141\u0142\1\0\0\0\u0142\u0143"+
		"\6\22\1\0\u0143\60\1\0\0\0\u0144\u0146\5\r\0\0\u0145\u0147\5\n\0\0\u0146"+
		"\u0145\1\0\0\0\u0146\u0147\1\0\0\0\u0147\u014a\1\0\0\0\u0148\u014a\5\n"+
		"\0\0\u0149\u0144\1\0\0\0\u0149\u0148\1\0\0\0\u014a\u014b\1\0\0\0\u014b"+
		"\u014c\6\23\2\0\u014c\62\1\0\0\0\u014d\u0151\7\1\0\0\u014e\u0150\7\2\0"+
		"\0\u014f\u014e\1\0\0\0\u0150\u0153\1\0\0\0\u0151\u014f\1\0\0\0\u0151\u0152"+
		"\1\0\0\0\u0152\64\1\0\0\0\u0153\u0151\1\0\0\0\u0154\u0155\5\"\0\0\u0155"+
		"\u0156\1\0\0\0\u0156\u0157\6\25\3\0\u0157\66\1\0\0\0\u0158\u0159\5<\0"+
		"\0\u0159\u015a\5<\0\0\u015a\u015b\1\0\0\0\u015b\u015c\6\26\4\0\u015c8"+
		"\1\0\0\0\u015d\u015e\5<\0\0\u015e\u015f\5%\0\0\u015f\u0160\1\0\0\0\u0160"+
		"\u0161\6\27\5\0\u0161:\1\0\0\0\u0162\u0163\5/\0\0\u0163\u0164\5/\0\0\u0164"+
		"\u0168\1\0\0\0\u0165\u0167\b\3\0\0\u0166\u0165\1\0\0\0\u0167\u016a\1\0"+
		"\0\0\u0168\u0166\1\0\0\0\u0168\u0169\1\0\0\0\u0169\u016b\1\0\0\0\u016a"+
		"\u0168\1\0\0\0\u016b\u016c\6\30\6\0\u016c<\1\0\0\0\u016d\u016e\5/\0\0"+
		"\u016e\u016f\5*\0\0\u016f\u0170\1\0\0\0\u0170\u0171\6\31\7\0\u0171>\1"+
		"\0\0\0\u0172\u0174\3E\35\0\u0173\u0172\1\0\0\0\u0174\u0177\1\0\0\0\u0175"+
		"\u0173\1\0\0\0\u0175\u0176\1\0\0\0\u0176\u0178\1\0\0\0\u0177\u0175\1\0"+
		"\0\0\u0178\u018a\3\63\24\0\u0179\u017b\3E\35\0\u017a\u0179\1\0\0\0\u017b"+
		"\u017e\1\0\0\0\u017c\u017a\1\0\0\0\u017c\u017d\1\0\0\0\u017d\u017f\1\0"+
		"\0\0\u017e\u017c\1\0\0\0\u017f\u0183\3\17\2\0\u0180\u0182\3E\35\0\u0181"+
		"\u0180\1\0\0\0\u0182\u0185\1\0\0\0\u0183\u0181\1\0\0\0\u0183\u0184\1\0"+
		"\0\0\u0184\u0186\1\0\0\0\u0185\u0183\1\0\0\0\u0186\u0187\3\63\24\0\u0187"+
		"\u0189\1\0\0\0\u0188\u017c\1\0\0\0\u0189\u018c\1\0\0\0\u018a\u0188\1\0"+
		"\0\0\u018a\u018b\1\0\0\0\u018b\u0190\1\0\0\0\u018c\u018a\1\0\0\0\u018d"+
		"\u018f\3E\35\0\u018e\u018d\1\0\0\0\u018f\u0192\1\0\0\0\u0190\u018e\1\0"+
		"\0\0\u0190\u0191\1\0\0\0\u0191\u0193\1\0\0\0\u0192\u0190\1\0\0\0\u0193"+
		"\u0194\5|\0\0\u0194@\1\0\0\0\u0195\u0197\3E\35\0\u0196\u0195\1\0\0\0\u0197"+
		"\u019a\1\0\0\0\u0198\u0196\1\0\0\0\u0198\u0199\1\0\0\0\u0199\u019b\1\0"+
		"\0\0\u019a\u0198\1\0\0\0\u019b\u019f\3C\34\0\u019c\u019e\3E\35\0\u019d"+
		"\u019c\1\0\0\0\u019e\u01a1\1\0\0\0\u019f\u019d\1\0\0\0\u019f\u01a0\1\0"+
		"\0\0\u01a0\u01a2\1\0\0\0\u01a1\u019f\1\0\0\0\u01a2\u01a6\3\17\2\0\u01a3"+
		"\u01a5\3E\35\0\u01a4\u01a3\1\0\0\0\u01a5\u01a8\1\0\0\0\u01a6\u01a4\1\0"+
		"\0\0\u01a6\u01a7\1\0\0\0\u01a7\u01a9\1\0\0\0\u01a8\u01a6\1\0\0\0\u01a9"+
		"\u01aa\3C\34\0\u01aaB\1\0\0\0\u01ab\u01ad\5\"\0\0\u01ac\u01ae\b\4\0\0"+
		"\u01ad\u01ac\1\0\0\0\u01ae\u01af\1\0\0\0\u01af\u01ad\1\0\0\0\u01af\u01b0"+
		"\1\0\0\0\u01b0\u01b1\1\0\0\0\u01b1\u01b2\5\"\0\0\u01b2D\1\0\0\0\u01b3"+
		"\u01b4\7\5\0\0\u01b4F\1\0\0\0\u01b5\u01b6\5\ufff0\0\0\u01b6H\1\0\0\0\u01b7"+
		"\u01b8\5\ufff1\0\0\u01b8J\1\0\0\0\u01b9\u01ba\t\0\0\0\u01baL\1\0\0\0\u01bb"+
		"\u01bc\3\61\23\0\u01bc\u01bd\1\0\0\0\u01bd\u01be\6!\b\0\u01beN\1\0\0\0"+
		"\u01bf\u01c1\b\6\0\0\u01c0\u01bf\1\0\0\0\u01c1\u01c2\1\0\0\0\u01c2\u01c0"+
		"\1\0\0\0\u01c2\u01c3\1\0\0\0\u01c3\u01c4\1\0\0\0\u01c4\u01c5\6\"\t\0\u01c5"+
		"P\1\0\0\0\u01c6\u01c7\5*\0\0\u01c7\u01c8\5/\0\0\u01c8\u01c9\1\0\0\0\u01c9"+
		"\u01ca\6#\n\0\u01caR\1\0\0\0\u01cb\u01cc\5*\0\0\u01cc\u01cd\1\0\0\0\u01cd"+
		"\u01ce\6$\13\0\u01ceT\1\0\0\0\u01cf\u01d0\t\0\0\0\u01d0\u01d1\1\0\0\0"+
		"\u01d1\u01d2\6%\f\0\u01d2V\1\0\0\0\u01d3\u01d4\3\61\23\0\u01d4\u01d5\1"+
		"\0\0\0\u01d5\u01d6\6&\r\0\u01d6X\1\0\0\0\u01d7\u01d9\b\7\0\0\u01d8\u01d7"+
		"\1\0\0\0\u01d9\u01da\1\0\0\0\u01da\u01d8\1\0\0\0\u01da\u01db\1\0\0\0\u01db"+
		"\u01dc\1\0\0\0\u01dc\u01dd\6\'\16\0\u01ddZ\1\0\0\0\u01de\u01df\5!\0\0"+
		"\u01df\u01e0\3I\37\0\u01e0\u01e1\1\0\0\0\u01e1\u01e2\6(\17\0\u01e2\\\1"+
		"\0\0\0\u01e3\u01e4\5!\0\0\u01e4\u01e5\1\0\0\0\u01e5\u01e6\6)\20\0\u01e6"+
		"^\1\0\0\0\u01e7\u01e8\t\0\0\0\u01e8\u01e9\1\0\0\0\u01e9\u01ea\6*\21\0"+
		"\u01ea`\1\0\0\0\u01eb\u01ec\3!\13\0\u01ec\u01ed\1\0\0\0\u01ed\u01ee\6"+
		"+\22\0\u01eeb\1\0\0\0\u01ef\u01f0\3\61\23\0\u01f0\u01f1\1\0\0\0\u01f1"+
		"\u01f2\6,\23\0\u01f2d\1\0\0\0\u01f3\u01f4\3G\36\0\u01f4\u01f5\5!\0\0\u01f5"+
		"\u01f6\1\0\0\0\u01f6\u01f7\6-\24\0\u01f7f\1\0\0\0\u01f8\u01f9\3G\36\0"+
		"\u01f9\u01fa\1\0\0\0\u01fa\u01fb\6.\25\0\u01fbh\1\0\0\0\u01fc\u01fe\b"+
		"\b\0\0\u01fd\u01fc\1\0\0\0\u01fe\u01ff\1\0\0\0\u01ff\u01fd\1\0\0\0\u01ff"+
		"\u0200\1\0\0\0\u0200j\1\0\0\0\u0201\u0204\5\\\0\0\u0202\u0205\t\0\0\0"+
		"\u0203\u0205\5\uffff\0\0\u0204\u0202\1\0\0\0\u0204\u0203\1\0\0\0\u0205"+
		"\u0206\1\0\0\0\u0206\u0207\6\60\26\0\u0207l\1\0\0\0\u0208\u0209\t\0\0"+
		"\0\u0209\u020a\1\0\0\0\u020a\u020b\6\61\27\0\u020bn\1\0\0\0\u020c\u020d"+
		"\3/\22\0\u020d\u020e\1\0\0\0\u020e\u020f\6\62\30\0\u020fp\1\0\0\0\u0210"+
		"\u0211\3\61\23\0\u0211\u0212\1\0\0\0\u0212\u0213\6\63\31\0\u0213r\1\0"+
		"\0\0\u0214\u0215\3\17\2\0\u0215\u0216\1\0\0\0\u0216\u0217\6\64\32\0\u0217"+
		"t\1\0\0\0\u0218\u0219\3\63\24\0\u0219v\1\0\0\0\u021a\u021b\5|\0\0\u021b"+
		"\u021c\1\0\0\0\u021c\u021d\6\66\33\0\u021dx\1\0\0\0\u021e\u021f\t\0\0"+
		"\0\u021f\u0220\1\0\0\0\u0220\u0221\6\67\34\0\u0221z\1\0\0\0\u0222\u0223"+
		"\3\61\23\0\u0223\u0224\1\0\0\0\u0224\u0225\68\35\0\u0225|\1\0\0\0\u0226"+
		"\u0227\3G\36\0\u0227\u0228\5!\0\0\u0228\u0229\1\0\0\0\u0229\u022a\69\36"+
		"\0\u022a~\1\0\0\0\u022b\u022c\3G\36\0\u022c\u022d\1\0\0\0\u022d\u022e"+
		"\6:\37\0\u022e\u0080\1\0\0\0\u022f\u0231\b\t\0\0\u0230\u022f\1\0\0\0\u0231"+
		"\u0232\1\0\0\0\u0232\u0230\1\0\0\0\u0232\u0233\1\0\0\0\u0233\u0234\1\0"+
		"\0\0\u0234\u0235\6; \0\u0235\u0082\1\0\0\0\u0236\u0239\5\\\0\0\u0237\u023a"+
		"\t\0\0\0\u0238\u023a\5\uffff\0\0\u0239\u0237\1\0\0\0\u0239\u0238\1\0\0"+
		"\0\u023a\u023b\1\0\0\0\u023b\u023c\6<!\0\u023c\u0084\1\0\0\0\u023d\u023e"+
		"\5>\0\0\u023e\u023f\5>\0\0\u023f\u0240\1\0\0\0\u0240\u0241\6=\"\0\u0241"+
		"\u0086\1\0\0\0\u0242\u0243\5>\0\0\u0243\u0244\1\0\0\0\u0244\u0245\6>#"+
		"\0\u0245\u0088\1\0\0\0\u0246\u0247\t\0\0\0\u0247\u0248\1\0\0\0\u0248\u0249"+
		"\6?$\0\u0249\u008a\1\0\0\0\u024a\u024b\3\61\23\0\u024b\u024c\1\0\0\0\u024c"+
		"\u024d\6@%\0\u024d\u008c\1\0\0\0\u024e\u024f\3G\36\0\u024f\u0250\5!\0"+
		"\0\u0250\u0251\1\0\0\0\u0251\u0252\6A&\0\u0252\u008e\1\0\0\0\u0253\u0254"+
		"\3G\36\0\u0254\u0255\1\0\0\0\u0255\u0256\6B\'\0\u0256\u0090\1\0\0\0\u0257"+
		"\u0259\b\n\0\0\u0258\u0257\1\0\0\0\u0259\u025a\1\0\0\0\u025a\u0258\1\0"+
		"\0\0\u025a\u025b\1\0\0\0\u025b\u025c\1\0\0\0\u025c\u025d\6C(\0\u025d\u0092"+
		"\1\0\0\0\u025e\u0261\5\\\0\0\u025f\u0262\t\0\0\0\u0260\u0262\5\uffff\0"+
		"\0\u0261\u025f\1\0\0\0\u0261\u0260\1\0\0\0\u0262\u0263\1\0\0\0\u0263\u0264"+
		"\6D)\0\u0264\u0094\1\0\0\0\u0265\u0266\5%\0\0\u0266\u0267\5>\0\0\u0267"+
		"\u0268\1\0\0\0\u0268\u0269\6E*\0\u0269\u0096\1\0\0\0\u026a\u026b\5%\0"+
		"\0\u026b\u026c\1\0\0\0\u026c\u026d\6F+\0\u026d\u0098\1\0\0\0\u026e\u026f"+
		"\t\0\0\0\u026f\u0270\1\0\0\0\u0270\u0271\6G,\0\u0271\u009a\1\0\0\0\u0272"+
		"\u0273\3\61\23\0\u0273\u0274\1\0\0\0\u0274\u0275\6H-\0\u0275\u009c\1\0"+
		"\0\0\u0276\u0277\3\21\3\0\u0277\u0278\1\0\0\0\u0278\u0279\6I.\0\u0279"+
		"\u009e\1\0\0\0\u027a\u027b\3\17\2\0\u027b\u027c\1\0\0\0\u027c\u027d\6"+
		"J/\0\u027d\u00a0\1\0\0\0\u027e\u027f\3\23\4\0\u027f\u0280\1\0\0\0\u0280"+
		"\u0281\6K\60\0\u0281\u00a2\1\0\0\0\u0282\u0283\3\13\0\0\u0283\u0284\1"+
		"\0\0\0\u0284\u0285\6L\61\0\u0285\u00a4\1\0\0\0\u0286\u0287\3\r\1\0\u0287"+
		"\u0288\1\0\0\0\u0288\u0289\6M\62\0\u0289\u00a6\1\0\0\0\u028a\u028b\3\33"+
		"\b\0\u028b\u028c\1\0\0\0\u028c\u028d\6N\63\0\u028d\u00a8\1\0\0\0\u028e"+
		"\u028f\3\35\t\0\u028f\u0290\1\0\0\0\u0290\u0291\6O\64\0\u0291\u00aa\1"+
		"\0\0\0\u0292\u0293\3\27\6\0\u0293\u0294\1\0\0\0\u0294\u0295\6P\65\0\u0295"+
		"\u00ac\1\0\0\0\u0296\u0297\3\37\n\0\u0297\u0298\1\0\0\0\u0298\u0299\6"+
		"Q\66\0\u0299\u00ae\1\0\0\0\u029a\u029b\3/\22\0\u029b\u029c\1\0\0\0\u029c"+
		"\u029d\6R\67\0\u029d\u00b0\1\0\0\0\u029e\u029f\5s\0\0\u029f\u02a0\5u\0"+
		"\0\u02a0\u02a1\5p\0\0\u02a1\u02a2\5e\0\0\u02a2\u02a3\5r\0\0\u02a3\u00b2"+
		"\1\0\0\0\u02a4\u02a5\5i\0\0\u02a5\u02a6\5f\0\0\u02a6\u00b4\1\0\0\0\u02a7"+
		"\u02a8\5e\0\0\u02a8\u02a9\5l\0\0\u02a9\u02aa\5s\0\0\u02aa\u02ab\5e\0\0"+
		"\u02ab\u02ac\5i\0\0\u02ac\u02ad\5f\0\0\u02ad\u00b6\1\0\0\0\u02ae\u02af"+
		"\5e\0\0\u02af\u02b0\5n\0\0\u02b0\u02b1\5d\0\0\u02b1\u02b2\5i\0\0\u02b2"+
		"\u02b3\5f\0\0\u02b3\u00b8\1\0\0\0\u02b4\u02b5\5e\0\0\u02b5\u02b6\5l\0"+
		"\0\u02b6\u02b7\5s\0\0\u02b7\u02b8\5e\0\0\u02b8\u00ba\1\0\0\0\u02b9\u02ba"+
		"\5e\0\0\u02ba\u02bb\5n\0\0\u02bb\u02bc\5d\0\0\u02bc\u00bc\1\0\0\0\u02bd"+
		"\u02be\5.\0\0\u02be\u02bf\5.\0\0\u02bf\u02c0\5.\0\0\u02c0\u00be\1\0\0"+
		"\0\u02c1\u02c2\5!\0\0\u02c2\u00c0\1\0\0\0\u02c3\u02c4\5|\0\0\u02c4\u02c5"+
		"\5|\0\0\u02c5\u00c2\1\0\0\0\u02c6\u02c7\5&\0\0\u02c7\u02c8\5&\0\0\u02c8"+
		"\u00c4\1\0\0\0\u02c9\u02ca\5;\0\0\u02ca\u00c6\1\0\0\0\u02cb\u02cf\5\\"+
		"\0\0\u02cc\u02ce\b\13\0\0\u02cd\u02cc\1\0\0\0\u02ce\u02d1\1\0\0\0\u02cf"+
		"\u02cd\1\0\0\0\u02cf\u02d0\1\0\0\0\u02d0\u00c8\1\0\0\0\u02d1\u02cf\1\0"+
		"\0\0\u02d2\u02d3\5@\0\0\u02d3\u02d4\5e\0\0\u02d4\u02d5\5n\0\0\u02d5\u02d6"+
		"\5d\0\0\u02d6\u00ca\1\0\0\0\u02d7\u02d8\5@\0\0\u02d8\u02d9\3\63\24\0\u02d9"+
		"\u00cc\1\0\0\0\u02da\u02db\3\63\24\0\u02db\u02dc\1\0\0\0\u02dc\u02dd\6"+
		"a8\0\u02dd\u00ce\1\0\0\0\u02de\u02df\3I\37\0\u02df\u02e0\1\0\0\0\u02e0"+
		"\u02e1\6b9\0\u02e1\u00d0\1\0\0\0\u02e2\u02eb\5\"\0\0\u02e3\u02ea\b\f\0"+
		"\0\u02e4\u02e7\5\\\0\0\u02e5\u02e8\t\0\0\0\u02e6\u02e8\5\uffff\0\0\u02e7"+
		"\u02e5\1\0\0\0\u02e7\u02e6\1\0\0\0\u02e8\u02ea\1\0\0\0\u02e9\u02e3\1\0"+
		"\0\0\u02e9\u02e4\1\0\0\0\u02ea\u02ed\1\0\0\0\u02eb\u02e9\1\0\0\0\u02eb"+
		"\u02ec\1\0\0\0\u02ec\u02ee\1\0\0\0\u02ed\u02eb\1\0\0\0\u02ee\u02ef\5\""+
		"\0\0\u02ef\u02f0\1\0\0\0\u02f0\u02f1\6c:\0\u02f1\u00d2\1\0\0\0\u02f2\u02f3"+
		"\t\0\0\0\u02f3\u02f4\1\0\0\0\u02f4\u02f5\6d;\0\u02f5\u00d4\1\0\0\0\u02f6"+
		"\u02f7\3\61\23\0\u02f7\u02f8\1\0\0\0\u02f8\u02f9\6e<\0\u02f9\u00d6\1\0"+
		"\0\0\u02fa\u02fb\3G\36\0\u02fb\u02fc\5!\0\0\u02fc\u02fd\1\0\0\0\u02fd"+
		"\u02fe\6f=\0\u02fe\u00d8\1\0\0\0\u02ff\u0300\3G\36\0\u0300\u0301\1\0\0"+
		"\0\u0301\u0302\6g>\0\u0302\u00da\1\0\0\0\u0303\u0305\b\r\0\0\u0304\u0303"+
		"\1\0\0\0\u0305\u0306\1\0\0\0\u0306\u0304\1\0\0\0\u0306\u0307\1\0\0\0\u0307"+
		"\u0308\1\0\0\0\u0308\u0309\6h?\0\u0309\u00dc\1\0\0\0\u030a\u030d\5\\\0"+
		"\0\u030b\u030e\t\0\0\0\u030c\u030e\5\uffff\0\0\u030d\u030b\1\0\0\0\u030d"+
		"\u030c\1\0\0\0\u030e\u030f\1\0\0\0\u030f\u0310\6i@\0\u0310\u00de\1\0\0"+
		"\0\u0311\u0312\5\"\0\0\u0312\u0313\1\0\0\0\u0313\u0314\6jA\0\u0314\u00e0"+
		"\1\0\0\0\u0315\u0316\t\0\0\0\u0316\u0317\1\0\0\0\u0317\u0318\6kB\0\u0318"+
		"\u00e2\1\0\0\0\u0319\u031a\3/\22\0\u031a\u031b\1\0\0\0\u031b\u031c\6l"+
		"C\0\u031c\u00e4\1\0\0\0\u031d\u031e\3\61\23\0\u031e\u031f\1\0\0\0\u031f"+
		"\u0320\6mD\0\u0320\u00e6\1\0\0\0\u0321\u0322\3C\34\0\u0322\u00e8\1\0\0"+
		"\0\u0323\u0324\3\17\2\0\u0324\u0325\1\0\0\0\u0325\u0326\6oE\0\u0326\u00ea"+
		"\1\0\0\0\u0327\u0328\3/\22\0\u0328\u0329\1\0\0\0\u0329\u032a\6pF\0\u032a"+
		"\u00ec\1\0\0\0\u032b\u032c\3\61\23\0\u032c\u032d\1\0\0\0\u032d\u032e\6"+
		"qG\0\u032e\u00ee\1\0\0\0\u032f\u0330\3C\34\0\u0330\u0331\1\0\0\0\u0331"+
		"\u0332\6rH\0\u0332\u00f0\1\0\0\0)\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\b\1"+
		"\t\1\n\1\u0109\1\u013b\1\u0140\1\u0146\1\u0149\1\u0151\1\u0168\1\u0175"+
		"\1\u017c\1\u0183\1\u018a\1\u0190\1\u0198\1\u019f\1\u01a6\1\u01af\1\u01c2"+
		"\1\u01da\1\u01ff\1\u0204\1\u0232\1\u0239\1\u025a\1\u0261\1\u02cf\1\u02e7"+
		"\1\u02e9\1\u02eb\1\u0306\1\u030d\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}