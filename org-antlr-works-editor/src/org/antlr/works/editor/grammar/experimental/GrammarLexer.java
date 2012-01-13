// $ANTLR ANTLRVersion> GrammarLexer.java generatedTimestamp>
package org.antlr.works.editor.grammar.experimental;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused"})
public class GrammarLexer extends Lexer {
	public static final int
		TOKEN_REF=3, RULE_REF=4, DOC_COMMENT=5, BLOCK_COMMENT=6, LINE_COMMENT=7, 
		DOUBLE_QUOTE_STRING_LITERAL=8, DOUBLE_ANGLE_STRING_LITERAL=9, BEGIN_ARG_ACTION=10, 
		BEGIN_ACTION=11, OPTIONS=12, TOKENS=13, IMPORT=14, FRAGMENT=15, LEXER=16, 
		PARSER=17, GRAMMAR=18, PROTECTED=19, PUBLIC=20, PRIVATE=21, RETURNS=22, 
		LOCALS=23, THROWS=24, CATCH=25, FINALLY=26, TEMPLATE=27, MODE=28, COLON=29, 
		COLONCOLON=30, COMMA=31, SEMI=32, LPAREN=33, RPAREN=34, RARROW=35, LT=36, 
		GT=37, ASSIGN=38, QUESTION=39, STAR=40, PLUS=41, PLUS_ASSIGN=42, OR=43, 
		DOLLAR=44, DOT=45, RANGE=46, ETC=47, AT=48, POUND=49, NOT=50, RBRACE=51, 
		ID=52, INT=53, STRING_LITERAL=54, WS=55, ERRCHAR=56, ARG_ACTION_LT=57, 
		ARG_ACTION_GT=58, ARG_ACTION_LPAREN=59, ARG_ACTION_RPAREN=60, ARG_ACTION_EQUALS=61, 
		ARG_ACTION_COMMA=62, ARG_ACTION_ESCAPE=63, ARG_ACTION_WORD=64, ARG_ACTION_ELEMENT=65, 
		ARG_ACTION_TEXT=66, ARG_ACTION_WS=67, ARG_ACTION_NEWLINE=68, END_ARG_ACTION=69, 
		NESTED_ACTION=70, ACTION_DOT=71, ACTION_LT=72, ACTION_GT=73, ACTION_LPAREN=74, 
		ACTION_RPAREN=75, ACTION_LBRACK=76, ACTION_RBRACK=77, ACTION_EQUALS=78, 
		ACTION_COMMA=79, ACTION_COLON2=80, ACTION_COLON=81, ACTION_MINUS=82, ACTION_ESCAPE=83, 
		ACTION_WORD=84, ACTION_REFERENCE=85, ACTION_COMMENT=86, ACTION_LITERAL=87, 
		ACTION_TEXT=88, ACTION_WS=89, ACTION_NEWLINE=90, END_ACTION=91;
	// Lexer modes
	public static final int ArgAction = 1;
	public static final int Action = 2;

	public static final String[] tokenNames = {
		"<INVALID>", "<INVALID>", "<INVALID>",
		"TOKEN_REF", "RULE_REF", "DOC_COMMENT", "BLOCK_COMMENT", "LINE_COMMENT", 
		"DOUBLE_QUOTE_STRING_LITERAL", "DOUBLE_ANGLE_STRING_LITERAL", "BEGIN_ARG_ACTION", 
		"BEGIN_ACTION", "OPTIONS", "TOKENS", "import", "fragment", "lexer", "parser", 
		"grammar", "protected", "public", "private", "returns", "locals", "throws", 
		"catch", "finally", "template", "mode", "COLON", "COLONCOLON", "COMMA", 
		";", "LPAREN", "RPAREN", "->", "LT", "GT", "ASSIGN", "?", "*", "+", "+=", 
		"|", "$", "DOT", "..", "...", "@", "#", "~", "RBRACE", "ID", "INT", "STRING_LITERAL", 
		"WS", "ERRCHAR", "ARG_ACTION_LT", "ARG_ACTION_GT", "ARG_ACTION_LPAREN", 
		"ARG_ACTION_RPAREN", "ARG_ACTION_EQUALS", "ARG_ACTION_COMMA", "ARG_ACTION_ESCAPE", 
		"ARG_ACTION_WORD", "ARG_ACTION_ELEMENT", "ARG_ACTION_TEXT", "ARG_ACTION_WS", 
		"ARG_ACTION_NEWLINE", "END_ARG_ACTION", "{", ".", "<", ">", "(", ")", 
		"[", "]", "=", ",", "::", ":", "-", "ACTION_ESCAPE", "ACTION_WORD", "ACTION_REFERENCE", 
		"ACTION_COMMENT", "ACTION_LITERAL", "ACTION_TEXT", "ACTION_WS", "ACTION_NEWLINE", 
		"}"
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
		"ACTION_TEXT", "ACTION_WS", "ACTION_NEWLINE", "END_ACTION"
	};


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN);
	}

	public String getGrammarFileName() { return "GrammarLexer.java"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch ( ruleIndex ) {
			case 1 : BLOCK_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 4 : LINE_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 7 : BEGIN_ARG_ACTION_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 8 : BEGIN_ACTION_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 49 : ID_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 60 : WS_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 77 : END_ARG_ACTION_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 78 : NESTED_ACTION_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 99 : END_ACTION_action((ParserRuleContext<?>)_localctx, actionIndex); break;
		}
	}
	public void BLOCK_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 0 : channel =  HIDDEN; break;
		}
	}
	public void END_ARG_ACTION_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 6 : popMode(); break;
		}
	}
	public void WS_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 5 : channel =  HIDDEN; break;
		}
	}
	public void NESTED_ACTION_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 7 : type =  BEGIN_ACTION; pushMode(Action); break;
		}
	}
	public void BEGIN_ACTION_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 3 : pushMode(Action); break;
		}
	}
	public void END_ACTION_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 8 : popMode(); break;
		}
	}
	public void ID_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 4 : 
							if ( Character.isUpperCase(getInputStream().substring(tokenStartCharIndex, tokenStartCharIndex).charAt(0)) ) type =  TOKEN_REF;
							else type =  RULE_REF;
							 break;
		}
	}
	public void LINE_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 1 : channel =  HIDDEN; break;
		}
	}
	public void BEGIN_ARG_ACTION_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 2 : pushMode(ArgAction); break;
		}
	}

	public static final String _serializedATN =
		"\2[\u0308\6\uffff\6\uffff\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4"+
		"\7\4\2\5\7\5\2\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f"+
		"\2\r\7\r\2\16\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2"+
		"\24\7\24\2\25\7\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2"+
		"\33\7\33\2\34\7\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2"+
		"#\7#\2$\7$\2%\7%\2&\7&\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2."+
		"\7.\2/\7/\2\60\7\60\2\61\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65"+
		"\2\66\7\66\2\67\7\67\28\78\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2"+
		"@\7@\2A\7A\2B\7B\2C\7C\2D\7D\2E\7E\2F\7F\2G\7G\2H\7H\2I\7I\2J\7J\2K\7"+
		"K\2L\7L\2M\7M\2N\7N\2O\7O\2P\7P\2Q\7Q\2R\7R\2S\7S\2T\7T\2U\7U\2V\7V\2"+
		"W\7W\2X\7X\2Y\7Y\2Z\7Z\2[\7[\2\\\7\\\2]\7]\2^\7^\2_\7_\2`\7`\2a\7a\2b"+
		"\7b\2c\7c\1\0\1\0\1\0\1\0\1\0\1\0\1\1\1\1\1\1\1\1\0\1\1\1\2\0\1\2\0\3"+
		"\2\b\2\1\3\0\5\3\b\3\n\3\f\3\u00e3\t\3\1\3\1\3\1\3\1\4\1\4\1\4\1\4\0\5"+
		"\4\b\4\n\4\f\4\u00f0\t\4\1\4\1\5\0\1\5\0\1\5\0\1\5\0\5\5\b\5\n\5\f\5\u00fe"+
		"\t\5\1\5\1\5\1\6\1\6\1\6\1\6\0\5\6\b\6\n\6\f\6\u010a\t\6\1\6\1\6\1\6\1"+
		"\7\0\1\7\1\b\0\1\b\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\t\0\5\t\b\t\n\t\f"+
		"\t\u0122\t\t\1\t\1\t\1\n\1\n\1\n\1\n\1\n\1\n\1\n\1\n\0\5\n\b\n\n\n\f\n"+
		"\u0132\t\n\1\n\1\n\1\13\1\13\1\13\1\13\1\13\1\13\1\13\1\f\1\f\1\f\1\f"+
		"\1\f\1\f\1\f\1\f\1\f\1\r\1\r\1\r\1\r\1\r\1\r\1\16\1\16\1\16\1\16\1\16"+
		"\1\16\1\16\1\17\1\17\1\17\1\17\1\17\1\17\1\17\1\17\1\20\1\20\1\20\1\20"+
		"\1\20\1\20\1\20\1\20\1\20\1\20\1\21\1\21\1\21\1\21\1\21\1\21\1\21\1\22"+
		"\1\22\1\22\1\22\1\22\1\22\1\22\1\22\1\23\1\23\1\23\1\23\1\23\1\23\1\23"+
		"\1\23\1\24\1\24\1\24\1\24\1\24\1\24\1\24\1\25\1\25\1\25\1\25\1\25\1\25"+
		"\1\25\1\26\1\26\1\26\1\26\1\26\1\26\1\27\1\27\1\27\1\27\1\27\1\27\1\27"+
		"\1\27\1\30\1\30\1\30\1\30\1\30\1\30\1\30\1\30\1\30\1\31\1\31\1\31\1\31"+
		"\1\31\1\32\1\32\1\33\1\33\1\33\1\34\1\34\1\35\1\35\1\36\1\36\1\37\1\37"+
		"\1 \1 \1 \1!\1!\1\"\1\"\1#\1#\1$\1$\1%\1%\1&\1&\1\'\1\'\1\'\1(\1(\1)\1"+
		")\1*\1*\1+\1+\1+\1,\1,\1,\1,\1-\1-\1.\1.\1/\1/\1\60\1\60\1\61\0\1\61\0"+
		"\5\61\b\61\n\61\f\61\u01e1\t\61\1\61\1\62\0\1\62\0\1\62\0\1\62\0\1\62"+
		"\0\1\62\0\3\62\b\62\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0\1"+
		"\63\0\1\63\0\1\63\0\1\63\0\1\63\0\1\63\0\3\63\b\63\1\64\0\1\64\0\1\64"+
		"\0\5\64\b\64\n\64\f\64\u0217\t\64\1\64\1\64\1\65\0\1\65\0\1\65\0\5\65"+
		"\b\65\n\65\f\65\u0224\t\65\1\65\1\65\1\66\0\1\66\1\66\1\67\0\4\67\b\67"+
		"\13\67\f\67\u022f\18\0\18\0\18\0\38\b8\58\b8\n8\f8\u023d\t8\18\18\19\1"+
		"9\1:\0\1:\0\1:\0\3:\b:\1;\0\1;\0\1;\0\1;\0\1;\0\3;\b;\3;\b;\3;\b;\3;\b"+
		";\1<\0\4<\b<\13<\f<\u0260\1<\1=\0\1=\0\3=\b=\1>\0\1>\0\1>\0\3>\b>\1?\0"+
		"\1?\0\1?\0\1?\0\1?\0\3?\b?\1@\1@\1A\1A\1B\1B\1C\1C\1D\1D\1E\1E\1F\1F\1"+
		"G\0\1G\1G\1H\0\1H\0\5H\bH\nH\fH\u0297\tH\1I\0\1I\0\3I\bI\1J\0\4J\bJ\13"+
		"J\fJ\u02a2\1K\0\4K\bK\13K\fK\u02a8\1L\0\1L\0\3L\bL\1L\0\3L\bL\1M\0\1M"+
		"\1N\0\1N\1O\1O\1P\1P\1Q\1Q\1R\1R\1S\1S\1T\1T\1U\1U\1V\1V\1W\1W\1X\1X\1"+
		"X\1Y\1Y\1Z\1Z\1[\0\1[\1[\1\\\0\4\\\b\\\13\\\f\\\u02db\1]\0\1]\0\3]\b]"+
		"\1^\1^\1_\0\1_\0\3_\b_\1`\0\1`\0\1`\0\4`\b`\13`\f`\u02f3\1a\0\4a\ba\13"+
		"a\fa\u02f9\1b\0\1b\0\3b\bb\1b\0\3b\bb\1c\0\1cd\3\5\uffff\5\6\0\7\0\uffff"+
		"\t\0\uffff\13\7\1\r\b\uffff\17\t\uffff\21\n\2\23\13\3\25\f\uffff\27\r"+
		"\uffff\31\16\uffff\33\17\uffff\35\20\uffff\37\21\uffff!\22\uffff#\23\uffff"+
		"%\24\uffff\'\25\uffff)\26\uffff+\27\uffff-\30\uffff/\31\uffff\61\32\uffff"+
		"\63\33\uffff\65\34\uffff\67\35\uffff9\36\uffff;\37\uffff= \uffff?!\uffff"+
		"A\"\uffffC#\uffffE$\uffffG%\uffffI&\uffffK\'\uffffM(\uffffO)\uffffQ*\uffff"+
		"S+\uffffU,\uffffW-\uffffY.\uffff[/\uffff]\60\uffff_\61\uffffa\62\uffff"+
		"c\63\uffffe\64\4g\0\uffffi\0\uffffk\0\uffffm\0\uffffo\0\uffffq\65\uffff"+
		"s\66\uffffu\0\uffffw\0\uffffy\0\uffff{\67\5}\0\uffff\177\0\uffff\u0081"+
		"\0\uffff\u00838\uffff\u00859\uffff\u0087:\uffff\u0089;\uffff\u008b<\uffff"+
		"\u008d=\uffff\u008f>\uffff\u0091?\uffff\u0093@\uffff\u0095A\uffff\u0097"+
		"B\uffff\u0099C\uffff\u009bD\uffff\u009dE\6\u009fF\7\u00a1G\uffff\u00a3"+
		"H\uffff\u00a5I\uffff\u00a7J\uffff\u00a9K\uffff\u00abL\uffff\u00adM\uffff"+
		"\u00afN\uffff\u00b1O\uffff\u00b3P\uffff\u00b5Q\uffff\u00b7R\uffff\u00b9"+
		"S\uffff\u00bbT\uffff\u00bdU\uffff\u00bfV\uffff\u00c1W\uffff\u00c3X\uffff"+
		"\u00c5Y\uffff\u00c7Z\uffff\u00c9[\b\3\0\1\2\20\2\n\n\r\r\1\"\"\1\'\'\1"+
		"\"\"\2\'\'\\\\\3\609AFaf\b\"\"\'\'\\\\bbffnnrrtt\3\t\n\f\r  \5$$\609A"+
		"Z__az\4\609AZ__az\r\t\n\r\r  \"\"$$\'),,\609<>AZ\\]__az\2\t\t  \4\609"+
		"AZ__az\2**//\f\t\n\r\r  \"\"$$\'),:<>A]__a{}}\2\t\t  \u02d9\0\3\1\0\0"+
		"\0\0\5\1\0\0\0\0\13\1\0\0\0\0\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23"+
		"\1\0\0\0\0\25\1\0\0\0\0\27\1\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0"+
		"\0\0\0\37\1\0\0\0\0!\1\0\0\0\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0"+
		"\0\0\0+\1\0\0\0\0-\1\0\0\0\0/\1\0\0\0\0\61\1\0\0\0\0\63\1\0\0\0\0\65\1"+
		"\0\0\0\0\67\1\0\0\0\09\1\0\0\0\0;\1\0\0\0\0=\1\0\0\0\0?\1\0\0\0\0A\1\0"+
		"\0\0\0C\1\0\0\0\0E\1\0\0\0\0G\1\0\0\0\0I\1\0\0\0\0K\1\0\0\0\0M\1\0\0\0"+
		"\0O\1\0\0\0\0Q\1\0\0\0\0S\1\0\0\0\0U\1\0\0\0\0W\1\0\0\0\0Y\1\0\0\0\0["+
		"\1\0\0\0\0]\1\0\0\0\0_\1\0\0\0\0a\1\0\0\0\0c\1\0\0\0\0e\1\0\0\0\0q\1\0"+
		"\0\0\0s\1\0\0\0\0{\1\0\0\0\0\u0083\1\0\0\0\1\u0085\1\0\0\0\1\u0087\1\0"+
		"\0\0\1\u0089\1\0\0\0\1\u008b\1\0\0\0\1\u008d\1\0\0\0\1\u008f\1\0\0\0\1"+
		"\u0091\1\0\0\0\1\u0093\1\0\0\0\1\u0095\1\0\0\0\1\u0097\1\0\0\0\1\u0099"+
		"\1\0\0\0\1\u009b\1\0\0\0\1\u009d\1\0\0\0\2\u009f\1\0\0\0\2\u00a1\1\0\0"+
		"\0\2\u00a3\1\0\0\0\2\u00a5\1\0\0\0\2\u00a7\1\0\0\0\2\u00a9\1\0\0\0\2\u00ab"+
		"\1\0\0\0\2\u00ad\1\0\0\0\2\u00af\1\0\0\0\2\u00b1\1\0\0\0\2\u00b3\1\0\0"+
		"\0\2\u00b5\1\0\0\0\2\u00b7\1\0\0\0\2\u00b9\1\0\0\0\2\u00bb\1\0\0\0\2\u00bd"+
		"\1\0\0\0\2\u00bf\1\0\0\0\2\u00c1\1\0\0\0\2\u00c3\1\0\0\0\2\u00c5\1\0\0"+
		"\0\2\u00c7\1\0\0\0\2\u00c9\1\0\0\0\3\u00cb\1\0\0\0\5\u00d1\1\0\0\0\7\u00db"+
		"\1\0\0\0\t\u00e1\1\0\0\0\13\u00e7\1\0\0\0\r\u00f2\1\0\0\0\17\u0101\1\0"+
		"\0\0\21\u010e\1\0\0\0\23\u0111\1\0\0\0\25\u0114\1\0\0\0\27\u0125\1\0\0"+
		"\0\31\u0135\1\0\0\0\33\u013c\1\0\0\0\35\u0145\1\0\0\0\37\u014b\1\0\0\0"+
		"!\u0152\1\0\0\0#\u015a\1\0\0\0%\u0164\1\0\0\0\'\u016b\1\0\0\0)\u0173\1"+
		"\0\0\0+\u017b\1\0\0\0-\u0182\1\0\0\0/\u0189\1\0\0\0\61\u018f\1\0\0\0\63"+
		"\u0197\1\0\0\0\65\u01a0\1\0\0\0\67\u01a5\1\0\0\09\u01a7\1\0\0\0;\u01aa"+
		"\1\0\0\0=\u01ac\1\0\0\0?\u01ae\1\0\0\0A\u01b0\1\0\0\0C\u01b2\1\0\0\0E"+
		"\u01b5\1\0\0\0G\u01b7\1\0\0\0I\u01b9\1\0\0\0K\u01bb\1\0\0\0M\u01bd\1\0"+
		"\0\0O\u01bf\1\0\0\0Q\u01c1\1\0\0\0S\u01c4\1\0\0\0U\u01c6\1\0\0\0W\u01c8"+
		"\1\0\0\0Y\u01ca\1\0\0\0[\u01cd\1\0\0\0]\u01d1\1\0\0\0_\u01d3\1\0\0\0a"+
		"\u01d5\1\0\0\0c\u01d7\1\0\0\0e\u01d9\1\0\0\0g\u01ef\1\0\0\0i\u020b\1\0"+
		"\0\0k\u020d\1\0\0\0m\u021a\1\0\0\0o\u0227\1\0\0\0q\u022d\1\0\0\0s\u0231"+
		"\1\0\0\0u\u0240\1\0\0\0w\u0242\1\0\0\0y\u024a\1\0\0\0{\u025e\1\0\0\0}"+
		"\u0267\1\0\0\0\177\u026f\1\0\0\0\u0081\u027b\1\0\0\0\u0083\u027d\1\0\0"+
		"\0\u0085\u027f\1\0\0\0\u0087\u0281\1\0\0\0\u0089\u0283\1\0\0\0\u008b\u0285"+
		"\1\0\0\0\u008d\u0287\1\0\0\0\u008f\u0289\1\0\0\0\u0091\u028b\1\0\0\0\u0093"+
		"\u028f\1\0\0\0\u0095\u029c\1\0\0\0\u0097\u02a0\1\0\0\0\u0099\u02a6\1\0"+
		"\0\0\u009b\u02b2\1\0\0\0\u009d\u02b4\1\0\0\0\u009f\u02b7\1\0\0\0\u00a1"+
		"\u02ba\1\0\0\0\u00a3\u02bc\1\0\0\0\u00a5\u02be\1\0\0\0\u00a7\u02c0\1\0"+
		"\0\0\u00a9\u02c2\1\0\0\0\u00ab\u02c4\1\0\0\0\u00ad\u02c6\1\0\0\0\u00af"+
		"\u02c8\1\0\0\0\u00b1\u02ca\1\0\0\0\u00b3\u02cc\1\0\0\0\u00b5\u02cf\1\0"+
		"\0\0\u00b7\u02d1\1\0\0\0\u00b9\u02d3\1\0\0\0\u00bb\u02d9\1\0\0\0\u00bd"+
		"\u02dd\1\0\0\0\u00bf\u02e3\1\0\0\0\u00c1\u02e9\1\0\0\0\u00c3\u02f1\1\0"+
		"\0\0\u00c5\u02f7\1\0\0\0\u00c7\u0303\1\0\0\0\u00c9\u0305\1\0\0\0\u00cb"+
		"\u00cc\5/\0\0\u00cc\u00cd\5*\0\0\u00cd\u00ce\5*\0\0\u00ce\u00cf\1\0\0"+
		"\0\u00cf\u00d0\3\t\3\0\u00d0\4\1\0\0\0\u00d1\u00d2\5/\0\0\u00d2\u00d3"+
		"\5*\0\0\u00d3\u00d4\1\0\0\0\u00d4\u00d6\3\t\3\0\u00d6\6\1\0\0\0\u00d7"+
		"\u00dc\3\5\1\0\u00d9\u00dc\3\13\4\0\u00db\u00d7\1\0\0\0\u00db\u00d9\1"+
		"\0\0\0\u00dc\b\1\0\0\0\u00dd\u00e0\t\0\0\0\u00df\u00dd\1\0\0\0\u00e0\u00e3"+
		"\1\0\0\0\u00e1\u00e2\1\0\0\0\u00e1\u00df\1\0\0\0\u00e2\u00e4\1\0\0\0\u00e3"+
		"\u00e1\1\0\0\0\u00e4\u00e5\5*\0\0\u00e5\u00e6\5/\0\0\u00e6\n\1\0\0\0\u00e7"+
		"\u00e8\5/\0\0\u00e8\u00e9\5/\0\0\u00e9\u00ee\1\0\0\0\u00ea\u00ed\b\0\0"+
		"\0\u00ec\u00ea\1\0\0\0\u00ed\u00f0\1\0\0\0\u00ee\u00ec\1\0\0\0\u00ee\u00ef"+
		"\1\0\0\0\u00ef\u00f1\1\0\0\0\u00f0\u00ee\1\0\0\0\u00f1\f\1\0\0\0\u00f2"+
		"\u00fc\5\"\0\0\u00f4\u00f6\5\\\0\0\u00f6\u00fb\t\0\0\0\u00f8\u00fb\b\1"+
		"\0\0\u00fa\u00f4\1\0\0\0\u00fa\u00f8\1\0\0\0\u00fb\u00fe\1\0\0\0\u00fc"+
		"\u00fa\1\0\0\0\u00fc\u00fd\1\0\0\0\u00fd\u00ff\1\0\0\0\u00fe\u00fc\1\0"+
		"\0\0\u00ff\u0100\5\"\0\0\u0100\16\1\0\0\0\u0101\u0102\5<\0\0\u0102\u0103"+
		"\5<\0\0\u0103\u0108\1\0\0\0\u0104\u0107\t\0\0\0\u0106\u0104\1\0\0\0\u0107"+
		"\u010a\1\0\0\0\u0108\u0109\1\0\0\0\u0108\u0106\1\0\0\0\u0109\u010b\1\0"+
		"\0\0\u010a\u0108\1\0\0\0\u010b\u010c\5>\0\0\u010c\u010d\5>\0\0\u010d\20"+
		"\1\0\0\0\u010e\u0110\5[\0\0\u0110\22\1\0\0\0\u0111\u0113\5{\0\0\u0113"+
		"\24\1\0\0\0\u0114\u0115\5o\0\0\u0115\u0116\5p\0\0\u0116\u0117\5t\0\0\u0117"+
		"\u0118\5i\0\0\u0118\u0119\5o\0\0\u0119\u011a\5n\0\0\u011a\u011b\5s\0\0"+
		"\u011b\u0120\1\0\0\0\u011c\u011f\3\u0081?\0\u011e\u011c\1\0\0\0\u011f"+
		"\u0122\1\0\0\0\u0120\u011e\1\0\0\0\u0120\u0121\1\0\0\0\u0121\u0123\1\0"+
		"\0\0\u0122\u0120\1\0\0\0\u0123\u0124\5{\0\0\u0124\26\1\0\0\0\u0125\u0126"+
		"\5t\0\0\u0126\u0127\5o\0\0\u0127\u0128\5k\0\0\u0128\u0129\5e\0\0\u0129"+
		"\u012a\5n\0\0\u012a\u012b\5s\0\0\u012b\u0130\1\0\0\0\u012c\u012f\3\u0081"+
		"?\0\u012e\u012c\1\0\0\0\u012f\u0132\1\0\0\0\u0130\u012e\1\0\0\0\u0130"+
		"\u0131\1\0\0\0\u0131\u0133\1\0\0\0\u0132\u0130\1\0\0\0\u0133\u0134\5{"+
		"\0\0\u0134\30\1\0\0\0\u0135\u0136\5i\0\0\u0136\u0137\5m\0\0\u0137\u0138"+
		"\5p\0\0\u0138\u0139\5o\0\0\u0139\u013a\5r\0\0\u013a\u013b\5t\0\0\u013b"+
		"\32\1\0\0\0\u013c\u013d\5f\0\0\u013d\u013e\5r\0\0\u013e\u013f\5a\0\0\u013f"+
		"\u0140\5g\0\0\u0140\u0141\5m\0\0\u0141\u0142\5e\0\0\u0142\u0143\5n\0\0"+
		"\u0143\u0144\5t\0\0\u0144\34\1\0\0\0\u0145\u0146\5l\0\0\u0146\u0147\5"+
		"e\0\0\u0147\u0148\5x\0\0\u0148\u0149\5e\0\0\u0149\u014a\5r\0\0\u014a\36"+
		"\1\0\0\0\u014b\u014c\5p\0\0\u014c\u014d\5a\0\0\u014d\u014e\5r\0\0\u014e"+
		"\u014f\5s\0\0\u014f\u0150\5e\0\0\u0150\u0151\5r\0\0\u0151 \1\0\0\0\u0152"+
		"\u0153\5g\0\0\u0153\u0154\5r\0\0\u0154\u0155\5a\0\0\u0155\u0156\5m\0\0"+
		"\u0156\u0157\5m\0\0\u0157\u0158\5a\0\0\u0158\u0159\5r\0\0\u0159\"\1\0"+
		"\0\0\u015a\u015b\5p\0\0\u015b\u015c\5r\0\0\u015c\u015d\5o\0\0\u015d\u015e"+
		"\5t\0\0\u015e\u015f\5e\0\0\u015f\u0160\5c\0\0\u0160\u0161\5t\0\0\u0161"+
		"\u0162\5e\0\0\u0162\u0163\5d\0\0\u0163$\1\0\0\0\u0164\u0165\5p\0\0\u0165"+
		"\u0166\5u\0\0\u0166\u0167\5b\0\0\u0167\u0168\5l\0\0\u0168\u0169\5i\0\0"+
		"\u0169\u016a\5c\0\0\u016a&\1\0\0\0\u016b\u016c\5p\0\0\u016c\u016d\5r\0"+
		"\0\u016d\u016e\5i\0\0\u016e\u016f\5v\0\0\u016f\u0170\5a\0\0\u0170\u0171"+
		"\5t\0\0\u0171\u0172\5e\0\0\u0172(\1\0\0\0\u0173\u0174\5r\0\0\u0174\u0175"+
		"\5e\0\0\u0175\u0176\5t\0\0\u0176\u0177\5u\0\0\u0177\u0178\5r\0\0\u0178"+
		"\u0179\5n\0\0\u0179\u017a\5s\0\0\u017a*\1\0\0\0\u017b\u017c\5l\0\0\u017c"+
		"\u017d\5o\0\0\u017d\u017e\5c\0\0\u017e\u017f\5a\0\0\u017f\u0180\5l\0\0"+
		"\u0180\u0181\5s\0\0\u0181,\1\0\0\0\u0182\u0183\5t\0\0\u0183\u0184\5h\0"+
		"\0\u0184\u0185\5r\0\0\u0185\u0186\5o\0\0\u0186\u0187\5w\0\0\u0187\u0188"+
		"\5s\0\0\u0188.\1\0\0\0\u0189\u018a\5c\0\0\u018a\u018b\5a\0\0\u018b\u018c"+
		"\5t\0\0\u018c\u018d\5c\0\0\u018d\u018e\5h\0\0\u018e\60\1\0\0\0\u018f\u0190"+
		"\5f\0\0\u0190\u0191\5i\0\0\u0191\u0192\5n\0\0\u0192\u0193\5a\0\0\u0193"+
		"\u0194\5l\0\0\u0194\u0195\5l\0\0\u0195\u0196\5y\0\0\u0196\62\1\0\0\0\u0197"+
		"\u0198\5t\0\0\u0198\u0199\5e\0\0\u0199\u019a\5m\0\0\u019a\u019b\5p\0\0"+
		"\u019b\u019c\5l\0\0\u019c\u019d\5a\0\0\u019d\u019e\5t\0\0\u019e\u019f"+
		"\5e\0\0\u019f\64\1\0\0\0\u01a0\u01a1\5m\0\0\u01a1\u01a2\5o\0\0\u01a2\u01a3"+
		"\5d\0\0\u01a3\u01a4\5e\0\0\u01a4\66\1\0\0\0\u01a5\u01a6\5:\0\0\u01a68"+
		"\1\0\0\0\u01a7\u01a8\5:\0\0\u01a8\u01a9\5:\0\0\u01a9:\1\0\0\0\u01aa\u01ab"+
		"\5,\0\0\u01ab<\1\0\0\0\u01ac\u01ad\5;\0\0\u01ad>\1\0\0\0\u01ae\u01af\5"+
		"(\0\0\u01af@\1\0\0\0\u01b0\u01b1\5)\0\0\u01b1B\1\0\0\0\u01b2\u01b3\5-"+
		"\0\0\u01b3\u01b4\5>\0\0\u01b4D\1\0\0\0\u01b5\u01b6\5<\0\0\u01b6F\1\0\0"+
		"\0\u01b7\u01b8\5>\0\0\u01b8H\1\0\0\0\u01b9\u01ba\5=\0\0\u01baJ\1\0\0\0"+
		"\u01bb\u01bc\5?\0\0\u01bcL\1\0\0\0\u01bd\u01be\5*\0\0\u01beN\1\0\0\0\u01bf"+
		"\u01c0\5+\0\0\u01c0P\1\0\0\0\u01c1\u01c2\5+\0\0\u01c2\u01c3\5=\0\0\u01c3"+
		"R\1\0\0\0\u01c4\u01c5\5|\0\0\u01c5T\1\0\0\0\u01c6\u01c7\5$\0\0\u01c7V"+
		"\1\0\0\0\u01c8\u01c9\5.\0\0\u01c9X\1\0\0\0\u01ca\u01cb\5.\0\0\u01cb\u01cc"+
		"\5.\0\0\u01ccZ\1\0\0\0\u01cd\u01ce\5.\0\0\u01ce\u01cf\5.\0\0\u01cf\u01d0"+
		"\5.\0\0\u01d0\\\1\0\0\0\u01d1\u01d2\5@\0\0\u01d2^\1\0\0\0\u01d3\u01d4"+
		"\5#\0\0\u01d4`\1\0\0\0\u01d5\u01d6\5~\0\0\u01d6b\1\0\0\0\u01d7\u01d8\5"+
		"}\0\0\u01d8d\1\0\0\0\u01d9\u01df\3i\63\0\u01db\u01de\3g\62\0\u01dd\u01db"+
		"\1\0\0\0\u01de\u01e1\1\0\0\0\u01df\u01dd\1\0\0\0\u01df\u01e0\1\0\0\0\u01e0"+
		"\u01e2\1\0\0\0\u01e1\u01df\1\0\0\0\u01e2f\1\0\0\0\u01e3\u01f0\3i\63\0"+
		"\u01e5\u01f0\2\609\0\u01e7\u01f0\5_\0\0\u01e9\u01f0\5\u00b7\0\0\u01eb"+
		"\u01f0\2\u0300\u036f\0\u01ed\u01f0\2\u203f\u2040\0\u01ef\u01e3\1\0\0\0"+
		"\u01ef\u01e5\1\0\0\0\u01ef\u01e7\1\0\0\0\u01ef\u01e9\1\0\0\0\u01ef\u01eb"+
		"\1\0\0\0\u01ef\u01ed\1\0\0\0\u01f0h\1\0\0\0\u01f1\u020c\2AZ\0\u01f3\u020c"+
		"\2az\0\u01f5\u020c\2\u00c0\u00d6\0\u01f7\u020c\2\u00d8\u00f6\0\u01f9\u020c"+
		"\2\u00f8\u02ff\0\u01fb\u020c\2\u0370\u037d\0\u01fd\u020c\2\u037f\u1fff"+
		"\0\u01ff\u020c\2\u200c\u200d\0\u0201\u020c\2\u2070\u218f\0\u0203\u020c"+
		"\2\u2c00\u2fef\0\u0205\u020c\2\u3001\ud7ff\0\u0207\u020c\2\uf900\ufdcf"+
		"\0\u0209\u020c\2\ufdf0\ufffd\0\u020b\u01f1\1\0\0\0\u020b\u01f3\1\0\0\0"+
		"\u020b\u01f5\1\0\0\0\u020b\u01f7\1\0\0\0\u020b\u01f9\1\0\0\0\u020b\u01fb"+
		"\1\0\0\0\u020b\u01fd\1\0\0\0\u020b\u01ff\1\0\0\0\u020b\u0201\1\0\0\0\u020b"+
		"\u0203\1\0\0\0\u020b\u0205\1\0\0\0\u020b\u0207\1\0\0\0\u020b\u0209\1\0"+
		"\0\0\u020cj\1\0\0\0\u020d\u0215\5\'\0\0\u020f\u0214\3o\66\0\u0211\u0214"+
		"\b\2\0\0\u0213\u020f\1\0\0\0\u0213\u0211\1\0\0\0\u0214\u0217\1\0\0\0\u0215"+
		"\u0213\1\0\0\0\u0215\u0216\1\0\0\0\u0216\u0218\1\0\0\0\u0217\u0215\1\0"+
		"\0\0\u0218\u0219\5\'\0\0\u0219l\1\0\0\0\u021a\u0222\5\"\0\0\u021c\u0221"+
		"\3o\66\0\u021e\u0221\b\3\0\0\u0220\u021c\1\0\0\0\u0220\u021e\1\0\0\0\u0221"+
		"\u0224\1\0\0\0\u0222\u0220\1\0\0\0\u0222\u0223\1\0\0\0\u0223\u0225\1\0"+
		"\0\0\u0224\u0222\1\0\0\0\u0225\u0226\5\"\0\0\u0226n\1\0\0\0\u0227\u0229"+
		"\5\\\0\0\u0229\u022a\t\0\0\0\u022ap\1\0\0\0\u022b\u022e\2\609\0\u022d"+
		"\u022b\1\0\0\0\u022e\u022f\1\0\0\0\u022f\u022d\1\0\0\0\u022f\u0230\1\0"+
		"\0\0\u0230r\1\0\0\0\u0231\u023b\5\'\0\0\u0233\u0238\3w:\0\u0235\u0238"+
		"\b\4\0\0\u0237\u0233\1\0\0\0\u0237\u0235\1\0\0\0\u0238\u023a\1\0\0\0\u0239"+
		"\u0237\1\0\0\0\u023a\u023d\1\0\0\0\u023b\u0239\1\0\0\0\u023b\u023c\1\0"+
		"\0\0\u023c\u023e\1\0\0\0\u023d\u023b\1\0\0\0\u023e\u023f\5\'\0\0\u023f"+
		"t\1\0\0\0\u0240\u0241\7\5\0\0\u0241v\1\0\0\0\u0242\u0248\5\\\0\0\u0244"+
		"\u0249\7\6\0\0\u0246\u0249\3y;\0\u0248\u0244\1\0\0\0\u0248\u0246\1\0\0"+
		"\0\u0249x\1\0\0\0\u024a\u025a\5u\0\0\u024c\u0258\3u9\0\u024e\u0256\3u"+
		"9\0\u0250\u0254\3u9\0\u0252\u0255\3u9\0\u0254\u0252\1\0\0\0\u0254\u0255"+
		"\1\0\0\0\u0255\u0257\1\0\0\0\u0256\u0250\1\0\0\0\u0256\u0257\1\0\0\0\u0257"+
		"\u0259\1\0\0\0\u0258\u024e\1\0\0\0\u0258\u0259\1\0\0\0\u0259\u025b\1\0"+
		"\0\0\u025a\u024c\1\0\0\0\u025a\u025b\1\0\0\0\u025bz\1\0\0\0\u025c\u025f"+
		"\7\7\0\0\u025e\u025c\1\0\0\0\u025f\u0260\1\0\0\0\u0260\u025e\1\0\0\0\u0260"+
		"\u0261\1\0\0\0\u0261\u0262\1\0\0\0\u0262|\1\0\0\0\u0263\u0268\5\n\0\0"+
		"\u0265\u0268\5\r\0\0\u0267\u0263\1\0\0\0\u0267\u0265\1\0\0\0\u0268~\1"+
		"\0\0\0\u0269\u0270\5 \0\0\u026b\u0270\5\t\0\0\u026d\u0270\5\f\0\0\u026f"+
		"\u0269\1\0\0\0\u026f\u026b\1\0\0\0\u026f\u026d\1\0\0\0\u0270\u0080\1\0"+
		"\0\0\u0271\u027c\5 \0\0\u0273\u027c\5\t\0\0\u0275\u027c\5\f\0\0\u0277"+
		"\u027c\5\n\0\0\u0279\u027c\5\r\0\0\u027b\u0271\1\0\0\0\u027b\u0273\1\0"+
		"\0\0\u027b\u0275\1\0\0\0\u027b\u0277\1\0\0\0\u027b\u0279\1\0\0\0\u027c"+
		"\u0082\1\0\0\0\u027d\u027e\t\0\0\0\u027e\u0084\1\0\0\0\u027f\u0280\5<"+
		"\0\0\u0280\u0086\1\0\0\0\u0281\u0282\5>\0\0\u0282\u0088\1\0\0\0\u0283"+
		"\u0284\5(\0\0\u0284\u008a\1\0\0\0\u0285\u0286\5)\0\0\u0286\u008c\1\0\0"+
		"\0\u0287\u0288\5=\0\0\u0288\u008e\1\0\0\0\u0289\u028a\5,\0\0\u028a\u0090"+
		"\1\0\0\0\u028b\u028d\5\\\0\0\u028d\u028e\t\0\0\0\u028e\u0092\1\0\0\0\u028f"+
		"\u0295\7\b\0\0\u0291\u0294\7\t\0\0\u0293\u0291\1\0\0\0\u0294\u0297\1\0"+
		"\0\0\u0295\u0293\1\0\0\0\u0295\u0296\1\0\0\0\u0296\u0094\1\0\0\0\u0297"+
		"\u0295\1\0\0\0\u0298\u029d\3m\65\0\u029a\u029d\3k\64\0\u029c\u0298\1\0"+
		"\0\0\u029c\u029a\1\0\0\0\u029d\u0096\1\0\0\0\u029e\u02a1\b\n\0\0\u02a0"+
		"\u029e\1\0\0\0\u02a1\u02a2\1\0\0\0\u02a2\u02a0\1\0\0\0\u02a2\u02a3\1\0"+
		"\0\0\u02a3\u0098\1\0\0\0\u02a4\u02a7\7\13\0\0\u02a6\u02a4\1\0\0\0\u02a7"+
		"\u02a8\1\0\0\0\u02a8\u02a6\1\0\0\0\u02a8\u02a9\1\0\0\0\u02a9\u009a\1\0"+
		"\0\0\u02aa\u02ae\5\r\0\0\u02ac\u02af\5\n\0\0\u02ae\u02ac\1\0\0\0\u02ae"+
		"\u02af\1\0\0\0\u02af\u02b3\1\0\0\0\u02b0\u02b3\5\n\0\0\u02b2\u02aa\1\0"+
		"\0\0\u02b2\u02b0\1\0\0\0\u02b3\u009c\1\0\0\0\u02b4\u02b6\5]\0\0\u02b6"+
		"\u009e\1\0\0\0\u02b7\u02b9\5{\0\0\u02b9\u00a0\1\0\0\0\u02ba\u02bb\5.\0"+
		"\0\u02bb\u00a2\1\0\0\0\u02bc\u02bd\5<\0\0\u02bd\u00a4\1\0\0\0\u02be\u02bf"+
		"\5>\0\0\u02bf\u00a6\1\0\0\0\u02c0\u02c1\5(\0\0\u02c1\u00a8\1\0\0\0\u02c2"+
		"\u02c3\5)\0\0\u02c3\u00aa\1\0\0\0\u02c4\u02c5\5[\0\0\u02c5\u00ac\1\0\0"+
		"\0\u02c6\u02c7\5]\0\0\u02c7\u00ae\1\0\0\0\u02c8\u02c9\5=\0\0\u02c9\u00b0"+
		"\1\0\0\0\u02ca\u02cb\5,\0\0\u02cb\u00b2\1\0\0\0\u02cc\u02cd\5:\0\0\u02cd"+
		"\u02ce\5:\0\0\u02ce\u00b4\1\0\0\0\u02cf\u02d0\5:\0\0\u02d0\u00b6\1\0\0"+
		"\0\u02d1\u02d2\5-\0\0\u02d2\u00b8\1\0\0\0\u02d3\u02d5\5\\\0\0\u02d5\u02d6"+
		"\t\0\0\0\u02d6\u00ba\1\0\0\0\u02d7\u02da\7\f\0\0\u02d9\u02d7\1\0\0\0\u02da"+
		"\u02db\1\0\0\0\u02db\u02d9\1\0\0\0\u02db\u02dc\1\0\0\0\u02dc\u00bc\1\0"+
		"\0\0\u02dd\u02e1\5$\0\0\u02df\u02e2\3\u00bb\\\0\u02e1\u02df\1\0\0\0\u02e1"+
		"\u02e2\1\0\0\0\u02e2\u00be\1\0\0\0\u02e3\u02e4\3\7\2\0\u02e4\u00c0\1\0"+
		"\0\0\u02e5\u02ea\3m\65\0\u02e7\u02ea\3k\64\0\u02e9\u02e5\1\0\0\0\u02e9"+
		"\u02e7\1\0\0\0\u02ea\u00c2\1\0\0\0\u02eb\u02ed\5/\0\0\u02ed\u02f2\b\r"+
		"\0\0\u02ef\u02f2\b\16\0\0\u02f1\u02eb\1\0\0\0\u02f1\u02ef\1\0\0\0\u02f2"+
		"\u02f3\1\0\0\0\u02f3\u02f1\1\0\0\0\u02f3\u02f4\1\0\0\0\u02f4\u00c4\1\0"+
		"\0\0\u02f5\u02f8\7\17\0\0\u02f7\u02f5\1\0\0\0\u02f8\u02f9\1\0\0\0\u02f9"+
		"\u02f7\1\0\0\0\u02f9\u02fa\1\0\0\0\u02fa\u00c6\1\0\0\0\u02fb\u02ff\5\r"+
		"\0\0\u02fd\u0300\5\n\0\0\u02ff\u02fd\1\0\0\0\u02ff\u0300\1\0\0\0\u0300"+
		"\u0304\1\0\0\0\u0301\u0304\5\n\0\0\u0303\u02fb\1\0\0\0\u0303\u0301\1\0"+
		"\0\0\u0304\u00c8\1\0\0\0\u0305\u0307\5}\0\0\u0307\u00ca\1\0\0\0,\0\1\1"+
		"\1\2\1\u00db\1\u00e1\0\u00ee\1\u00fa\1\u00fc\1\u0108\0\u0120\1\u0130\1"+
		"\u01df\1\u01ef\1\u020b\1\u0213\1\u0215\1\u0220\1\u0222\1\u022f\1\u0237"+
		"\1\u023b\1\u0248\1\u0254\1\u0256\1\u0258\1\u025a\1\u0260\1\u0267\1\u026f"+
		"\1\u027b\1\u0295\1\u029c\1\u02a2\1\u02a8\1\u02ae\1\u02b2\1\u02db\1\u02e1"+
		"\1\u02e9\1\u02f1\1\u02f3\1\u02f9\1\u02ff\1\u0303\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}