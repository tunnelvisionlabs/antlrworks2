// $ANTLR ANTLRVersion> GrammarParser.java generatedTimestamp>
/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class GrammarParser extends Parser<Token> {
	public static final int
		DOLLAR=44, PROTECTED=19, LT=36, ACTION_COLON=81, STAR=40, NESTED_ACTION=70, 
		DOUBLE_ANGLE_STRING_LITERAL=9, FRAGMENT=15, ACTION_ESCAPE=83, ACTION_REFERENCE=85, 
		ACTION_COLON2=80, ACTION_DOT=71, NOT=50, ID=52, ARG_ACTION_LT=57, ACTION_NEWLINE=90, 
		TOKEN_REF=3, LPAREN=33, ARG_ACTION_ELEMENT=65, ARG_ACTION_RPAREN=60, ARG_ACTION_GT=58, 
		AT=48, RPAREN=34, ARG_ACTION_LPAREN=59, IMPORT=14, STRING_LITERAL=54, 
		END_ACTION=91, ETC=47, COMMA=31, ACTION_GT=73, ACTION_LITERAL=87, ARG_ACTION_NEWLINE=68, 
		DOC_COMMENT=5, BLOCK_COMMENT=6, PLUS=41, BEGIN_ACTION=11, DOUBLE_QUOTE_STRING_LITERAL=8, 
		DOT=45, ACTION_LBRACK=76, MODE=28, ACTION_WORD=84, GRAMMAR=18, ARG_ACTION_ESCAPE=63, 
		RETURNS=22, ARG_ACTION_TEXT=66, ACTION_EQUALS=78, LOCALS=23, ACTION_WS=89, 
		RBRACE=51, POUND=49, LINE_COMMENT=7, PRIVATE=21, RARROW=35, END_ARG_ACTION=69, 
		TOKENS=13, RANGE=46, THROWS=24, INT=53, SEMI=32, RULE_REF=4, ARG_ACTION_EQUALS=61, 
		ACTION_RPAREN=75, ACTION_COMMA=79, COLON=29, COLONCOLON=30, ACTION_RBRACK=77, 
		WS=55, ACTION_COMMENT=86, QUESTION=39, FINALLY=26, ACTION_LT=72, TEMPLATE=27, 
		ACTION_TEXT=88, LEXER=16, ERRCHAR=56, ACTION_MINUS=82, ARG_ACTION_COMMA=62, 
		OR=43, ASSIGN=38, PLUS_ASSIGN=42, ARG_ACTION_WS=67, GT=37, CATCH=25, ARG_ACTION_WORD=64, 
		PUBLIC=20, PARSER=17, ACTION_LPAREN=74, OPTIONS=12, BEGIN_ARG_ACTION=10, 
		RULE=92, PREC_RULE=93, RULES=94, RULEMODIFIERS=95, RULEACTIONS=96, BLOCK=97, 
		OPTIONAL=98, CLOSURE=99, POSITIVE_CLOSURE=100, SET=101, CHAR_RANGE=102, 
		EPSILON=103, ALT=104, ALTLIST=105, ARG=106, ARGLIST=107, RET=108, COMBINED=109, 
		INITACTION=110, LABEL=111, WILDCARD=112, LIST=113, ELEMENT_OPTIONS=114, 
		RESULT=115, LEXER_ALT_ACTION=116, LEXER_ACTION_CALL=117;
	public static final String[] tokenNames = {
		"<INVALID>", "<INVALID>", "<INVALID>",
		"TOKEN_REF", "RULE_REF", "DOC_COMMENT", "BLOCK_COMMENT", "LINE_COMMENT", 
		"DOUBLE_QUOTE_STRING_LITERAL", "DOUBLE_ANGLE_STRING_LITERAL", "BEGIN_ARG_ACTION", 
		"BEGIN_ACTION", "OPTIONS", "TOKENS", "'import'", "'fragment'", "'lexer'", 
		"'parser'", "'grammar'", "'protected'", "'public'", "'private'", "'returns'", 
		"'locals'", "'throws'", "'catch'", "'finally'", "'template'", "'mode'", 
		"COLON", "COLONCOLON", "COMMA", "';'", "LPAREN", "RPAREN", "'->'", "LT", 
		"GT", "ASSIGN", "'?'", "'*'", "'+'", "'+='", "'|'", "'$'", "DOT", "'..'", 
		"'...'", "'@'", "'#'", "'~'", "'}'", "ID", "INT", "STRING_LITERAL", "WS", 
		"ERRCHAR", "ARG_ACTION_LT", "ARG_ACTION_GT", "ARG_ACTION_LPAREN", "ARG_ACTION_RPAREN", 
		"ARG_ACTION_EQUALS", "ARG_ACTION_COMMA", "ARG_ACTION_ESCAPE", "ARG_ACTION_WORD", 
		"ARG_ACTION_ELEMENT", "ARG_ACTION_TEXT", "ARG_ACTION_WS", "ARG_ACTION_NEWLINE", 
		"END_ARG_ACTION", "NESTED_ACTION", "'.'", "'<'", "'>'", "'('", "')'", 
		"'['", "']'", "'='", "','", "'::'", "':'", "'-'", "ACTION_ESCAPE", "ACTION_WORD", 
		"ACTION_REFERENCE", "ACTION_COMMENT", "ACTION_LITERAL", "ACTION_TEXT", 
		"ACTION_WS", "ACTION_NEWLINE", "END_ACTION", "RULE", "PREC_RULE", "RULES", 
		"RULEMODIFIERS", "RULEACTIONS", "BLOCK", "OPTIONAL", "CLOSURE", "POSITIVE_CLOSURE", 
		"SET", "CHAR_RANGE", "EPSILON", "ALT", "ALTLIST", "ARG", "ARGLIST", "RET", 
		"COMBINED", "INITACTION", "LABEL", "WILDCARD", "LIST", "ELEMENT_OPTIONS", 
		"RESULT", "LEXER_ALT_ACTION", "LEXER_ACTION_CALL"
	};
	public static final int
		RULE_grammarSpec = 0, RULE_grammarType = 1, RULE_prequelConstruct = 2, 
		RULE_optionsSpec = 3, RULE_option = 4, RULE_optionValue = 5, RULE_delegateGrammars = 6, 
		RULE_delegateGrammar = 7, RULE_tokensSpec = 8, RULE_tokenSpec = 9, RULE_actionBlock = 10, 
		RULE_actionExpression = 11, RULE_actionScopeExpression = 12, RULE_argActionBlock = 13, 
		RULE_argActionParameters = 14, RULE_argActionParameter = 15, RULE_argActionParameterType = 16, 
		RULE_argActionParameterTypePart = 17, RULE_ignored = 18, RULE_action = 19, 
		RULE_actionScopeName = 20, RULE_modeSpec = 21, RULE_rules = 22, RULE_ruleSpec = 23, 
		RULE_parserRuleSpec = 24, RULE_exceptionGroup = 25, RULE_exceptionHandler = 26, 
		RULE_finallyClause = 27, RULE_rulePrequels = 28, RULE_rulePrequel = 29, 
		RULE_ruleReturns = 30, RULE_throwsSpec = 31, RULE_localsSpec = 32, RULE_ruleAction = 33, 
		RULE_ruleModifiers = 34, RULE_ruleModifier = 35, RULE_ruleBlock = 36, 
		RULE_ruleAltList = 37, RULE_labeledAlt = 38, RULE_lexerRule = 39, RULE_lexerRuleBlock = 40, 
		RULE_lexerAltList = 41, RULE_lexerAlt = 42, RULE_lexerElements = 43, RULE_lexerElement = 44, 
		RULE_labeledLexerElement = 45, RULE_lexerBlock = 46, RULE_lexerCommands = 47, 
		RULE_lexerCommand = 48, RULE_lexerCommandExpr = 49, RULE_altList = 50, 
		RULE_alternative = 51, RULE_elements = 52, RULE_element = 53, RULE_labeledElement = 54, 
		RULE_ebnf = 55, RULE_blockSuffix = 56, RULE_ebnfSuffix = 57, RULE_lexerAtom = 58, 
		RULE_atom = 59, RULE_notSet = 60, RULE_blockSet = 61, RULE_setElement = 62, 
		RULE_block = 63, RULE_ruleref = 64, RULE_range = 65, RULE_terminal = 66, 
		RULE_elementOptions = 67, RULE_elementOption = 68, RULE_id = 69, RULE_qid = 70;
	public static final String[] ruleNames = {
		"grammarSpec", "grammarType", "prequelConstruct", "optionsSpec", "option", 
		"optionValue", "delegateGrammars", "delegateGrammar", "tokensSpec", "tokenSpec", 
		"actionBlock", "actionExpression", "actionScopeExpression", "argActionBlock", 
		"argActionParameters", "argActionParameter", "argActionParameterType", 
		"argActionParameterTypePart", "ignored", "action", "actionScopeName", 
		"modeSpec", "rules", "ruleSpec", "parserRuleSpec", "exceptionGroup", "exceptionHandler", 
		"finallyClause", "rulePrequels", "rulePrequel", "ruleReturns", "throwsSpec", 
		"localsSpec", "ruleAction", "ruleModifiers", "ruleModifier", "ruleBlock", 
		"ruleAltList", "labeledAlt", "lexerRule", "lexerRuleBlock", "lexerAltList", 
		"lexerAlt", "lexerElements", "lexerElement", "labeledLexerElement", "lexerBlock", 
		"lexerCommands", "lexerCommand", "lexerCommandExpr", "altList", "alternative", 
		"elements", "element", "labeledElement", "ebnf", "blockSuffix", "ebnfSuffix", 
		"lexerAtom", "atom", "notSet", "blockSet", "setElement", "block", "ruleref", 
		"range", "terminal", "elementOptions", "elementOption", "id", "qid"
	};

	@Override
	public String getGrammarFileName() { return "GrammarParser.g"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream<? extends Token> input) {
		super(input);
		_interp = new ParserATNSimulator<Token>(this,_ATN);
	}
	public static class GrammarSpecContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> DOC_COMMENT() { return getToken(GrammarParser.DOC_COMMENT, 0); }
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public ModeSpecContext modeSpec() {
		    return getRuleContext(ModeSpecContext.class,0);
		}
		public TerminalNode<Token> EOF() { return getToken(GrammarParser.EOF, 0); }
		public TerminalNode<Token> SEMI() { return getToken(GrammarParser.SEMI, 0); }
		public PrequelConstructContext prequelConstruct() {
		    return getRuleContext(PrequelConstructContext.class,0);
		}
		public GrammarTypeContext grammarType() {
		    return getRuleContext(GrammarTypeContext.class,0);
		}
		public RulesContext rules() {
		    return getRuleContext(RulesContext.class,0);
		}
		public GrammarSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterGrammarSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitGrammarSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitGrammarSpec(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final GrammarSpecContext grammarSpec() throws RecognitionException {
		GrammarSpecContext _localctx = new GrammarSpecContext(_ctx, 0);
		enterRule(_localctx, RULE_grammarSpec);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(142); match(DOC_COMMENT);
					}
					break;
			}
			setState(146); grammarType();
			setState(148); id();
			setState(150); match(SEMI);
			setState(156);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(152); prequelConstruct();
					}
					} 
				}
				setState(158);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(159); rules();
			setState(165);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(161); modeSpec();
					}
					} 
				}
				setState(167);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(168); match(EOF);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GrammarTypeContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> PARSER() { return getToken(GrammarParser.PARSER, 0); }
		public TerminalNode<Token> GRAMMAR(int i) {
		    return getToken(GrammarParser.GRAMMAR, i);
		}
		public TerminalNode<Token> LEXER() { return getToken(GrammarParser.LEXER, 0); }
		public List<? extends TerminalNode<Token>> GRAMMAR() { return getTokens(GrammarParser.GRAMMAR); }
		public GrammarTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterGrammarType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitGrammarType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitGrammarType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final GrammarTypeContext grammarType() throws RecognitionException {
		GrammarTypeContext _localctx = new GrammarTypeContext(_ctx, 2);
		enterRule(_localctx, RULE_grammarType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(170); match(LEXER);
					setState(172); match(GRAMMAR);
					}
					break;

				case 2:
					{
					setState(174); match(PARSER);
					setState(176); match(GRAMMAR);
					}
					break;

				case 3:
					{
					setState(178); match(GRAMMAR);
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrequelConstructContext extends ParserRuleContext<Token> {
		public TokensSpecContext tokensSpec() {
		    return getRuleContext(TokensSpecContext.class,0);
		}
		public ActionContext action() {
		    return getRuleContext(ActionContext.class,0);
		}
		public DelegateGrammarsContext delegateGrammars() {
		    return getRuleContext(DelegateGrammarsContext.class,0);
		}
		public OptionsSpecContext optionsSpec() {
		    return getRuleContext(OptionsSpecContext.class,0);
		}
		public PrequelConstructContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterPrequelConstruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitPrequelConstruct(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitPrequelConstruct(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final PrequelConstructContext prequelConstruct() throws RecognitionException {
		PrequelConstructContext _localctx = new PrequelConstructContext(_ctx, 4);
		enterRule(_localctx, RULE_prequelConstruct);
		try {
			setState(190);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(182); optionsSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(184); delegateGrammars();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(186); tokensSpec();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(188); action();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionsSpecContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> RBRACE() { return getToken(GrammarParser.RBRACE, 0); }
		public TerminalNode<Token> SEMI() { return getToken(GrammarParser.SEMI, 0); }
		public TerminalNode<Token> OPTIONS() { return getToken(GrammarParser.OPTIONS, 0); }
		public OptionContext option() {
		    return getRuleContext(OptionContext.class,0);
		}
		public OptionsSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterOptionsSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitOptionsSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitOptionsSpec(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final OptionsSpecContext optionsSpec() throws RecognitionException {
		OptionsSpecContext _localctx = new OptionsSpecContext(_ctx, 6);
		enterRule(_localctx, RULE_optionsSpec);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(192); match(OPTIONS);
			setState(200);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(194); option();
					setState(196); match(SEMI);
					}
					} 
				}
				setState(202);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(203); match(RBRACE);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionContext extends ParserRuleContext<Token> {
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public OptionValueContext optionValue() {
		    return getRuleContext(OptionValueContext.class,0);
		}
		public TerminalNode<Token> ASSIGN() { return getToken(GrammarParser.ASSIGN, 0); }
		public OptionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitOption(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitOption(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, 8);
		enterRule(_localctx, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205); id();
			setState(207); match(ASSIGN);
			setState(209); optionValue();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionValueContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> STAR() { return getToken(GrammarParser.STAR, 0); }
		public TerminalNode<Token> INT() { return getToken(GrammarParser.INT, 0); }
		public TerminalNode<Token> STRING_LITERAL() { return getToken(GrammarParser.STRING_LITERAL, 0); }
		public QidContext qid() {
		    return getRuleContext(QidContext.class,0);
		}
		public OptionValueContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterOptionValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitOptionValue(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitOptionValue(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final OptionValueContext optionValue() throws RecognitionException {
		OptionValueContext _localctx = new OptionValueContext(_ctx, 10);
		enterRule(_localctx, RULE_optionValue);
		try {
			setState(219);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(211); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(213); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(215); match(INT);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(217); match(STAR);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DelegateGrammarsContext extends ParserRuleContext<Token> {
		public List<? extends DelegateGrammarContext> delegateGrammar() {
		    return getRuleContexts(DelegateGrammarContext.class);
		}
		public TerminalNode<Token> IMPORT() { return getToken(GrammarParser.IMPORT, 0); }
		public TerminalNode<Token> COMMA() { return getToken(GrammarParser.COMMA, 0); }
		public TerminalNode<Token> SEMI() { return getToken(GrammarParser.SEMI, 0); }
		public DelegateGrammarContext delegateGrammar(int i) {
		    return getRuleContext(DelegateGrammarContext.class,i);
		}
		public DelegateGrammarsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterDelegateGrammars(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitDelegateGrammars(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitDelegateGrammars(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final DelegateGrammarsContext delegateGrammars() throws RecognitionException {
		DelegateGrammarsContext _localctx = new DelegateGrammarsContext(_ctx, 12);
		enterRule(_localctx, RULE_delegateGrammars);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(221); match(IMPORT);
			setState(223); delegateGrammar();
			setState(231);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(225); match(COMMA);
					setState(227); delegateGrammar();
					}
					} 
				}
				setState(233);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(234); match(SEMI);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DelegateGrammarContext extends ParserRuleContext<Token> {
		public List<? extends IdContext> id() {
		    return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
		    return getRuleContext(IdContext.class,i);
		}
		public TerminalNode<Token> ASSIGN() { return getToken(GrammarParser.ASSIGN, 0); }
		public DelegateGrammarContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterDelegateGrammar(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitDelegateGrammar(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitDelegateGrammar(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final DelegateGrammarContext delegateGrammar() throws RecognitionException {
		DelegateGrammarContext _localctx = new DelegateGrammarContext(_ctx, 14);
		enterRule(_localctx, RULE_delegateGrammar);
		try {
			setState(244);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(236); id();
					setState(238); match(ASSIGN);
					setState(240); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(242); id();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TokensSpecContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> TOKENS() { return getToken(GrammarParser.TOKENS, 0); }
		public TerminalNode<Token> RBRACE() { return getToken(GrammarParser.RBRACE, 0); }
		public TokenSpecContext tokenSpec() {
		    return getRuleContext(TokenSpecContext.class,0);
		}
		public TokensSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterTokensSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitTokensSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitTokensSpec(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TokensSpecContext tokensSpec() throws RecognitionException {
		TokensSpecContext _localctx = new TokensSpecContext(_ctx, 16);
		enterRule(_localctx, RULE_tokensSpec);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(246); match(TOKENS);
			setState(250); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(248); tokenSpec();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(252); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			setState(254); match(RBRACE);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TokenSpecContext extends ParserRuleContext<Token> {
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public TerminalNode<Token> STRING_LITERAL() { return getToken(GrammarParser.STRING_LITERAL, 0); }
		public TerminalNode<Token> SEMI() { return getToken(GrammarParser.SEMI, 0); }
		public TerminalNode<Token> RULE_REF() { return getToken(GrammarParser.RULE_REF, 0); }
		public TerminalNode<Token> ASSIGN() { return getToken(GrammarParser.ASSIGN, 0); }
		public TokenSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterTokenSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitTokenSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitTokenSpec(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TokenSpecContext tokenSpec() throws RecognitionException {
		TokenSpecContext _localctx = new TokenSpecContext(_ctx, 18);
		enterRule(_localctx, RULE_tokenSpec);
		try {
			setState(270);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(256); id();
					setState(264);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
						case 1:
							{
							setState(258); match(ASSIGN);
							setState(260); match(STRING_LITERAL);
							}
							break;

						case 2:
							{
							}
							break;
					}
					setState(266); match(SEMI);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(268); match(RULE_REF);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionBlockContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> ACTION_WS() { return getToken(GrammarParser.ACTION_WS, 0); }
		public TerminalNode<Token> ACTION_COLON() { return getToken(GrammarParser.ACTION_COLON, 0); }
		public ActionExpressionContext actionExpression() {
		    return getRuleContext(ActionExpressionContext.class,0);
		}
		public ActionBlockContext actionBlock() {
		    return getRuleContext(ActionBlockContext.class,0);
		}
		public TerminalNode<Token> ACTION_ESCAPE() { return getToken(GrammarParser.ACTION_ESCAPE, 0); }
		public TerminalNode<Token> ACTION_REFERENCE() { return getToken(GrammarParser.ACTION_REFERENCE, 0); }
		public TerminalNode<Token> ACTION_COLON2() { return getToken(GrammarParser.ACTION_COLON2, 0); }
		public TerminalNode<Token> ACTION_DOT() { return getToken(GrammarParser.ACTION_DOT, 0); }
		public ActionScopeExpressionContext actionScopeExpression() {
		    return getRuleContext(ActionScopeExpressionContext.class,0);
		}
		public TerminalNode<Token> ACTION_NEWLINE() { return getToken(GrammarParser.ACTION_NEWLINE, 0); }
		public TerminalNode<Token> ACTION_RPAREN() { return getToken(GrammarParser.ACTION_RPAREN, 0); }
		public TerminalNode<Token> ACTION_COMMA() { return getToken(GrammarParser.ACTION_COMMA, 0); }
		public TerminalNode<Token> ACTION_RBRACK() { return getToken(GrammarParser.ACTION_RBRACK, 0); }
		public TerminalNode<Token> ACTION_COMMENT() { return getToken(GrammarParser.ACTION_COMMENT, 0); }
		public TerminalNode<Token> ACTION_LT() { return getToken(GrammarParser.ACTION_LT, 0); }
		public TerminalNode<Token> END_ACTION() { return getToken(GrammarParser.END_ACTION, 0); }
		public TerminalNode<Token> ACTION_GT() { return getToken(GrammarParser.ACTION_GT, 0); }
		public TerminalNode<Token> ACTION_TEXT() { return getToken(GrammarParser.ACTION_TEXT, 0); }
		public TerminalNode<Token> ACTION_LITERAL() { return getToken(GrammarParser.ACTION_LITERAL, 0); }
		public TerminalNode<Token> ACTION_MINUS() { return getToken(GrammarParser.ACTION_MINUS, 0); }
		public TerminalNode<Token> BEGIN_ACTION() { return getToken(GrammarParser.BEGIN_ACTION, 0); }
		public TerminalNode<Token> ACTION_LPAREN() { return getToken(GrammarParser.ACTION_LPAREN, 0); }
		public TerminalNode<Token> ACTION_LBRACK() { return getToken(GrammarParser.ACTION_LBRACK, 0); }
		public TerminalNode<Token> ACTION_WORD() { return getToken(GrammarParser.ACTION_WORD, 0); }
		public TerminalNode<Token> ACTION_EQUALS() { return getToken(GrammarParser.ACTION_EQUALS, 0); }
		public ActionBlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterActionBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitActionBlock(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitActionBlock(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ActionBlockContext actionBlock() throws RecognitionException {
		ActionBlockContext _localctx = new ActionBlockContext(_ctx, 20);
		enterRule(_localctx, RULE_actionBlock);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(272); match(BEGIN_ACTION);
			setState(322);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					setState(320);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
						case 1:
							{
							setState(274); actionBlock();
							}
							break;

						case 2:
							{
							setState(276); actionExpression();
							}
							break;

						case 3:
							{
							setState(278); actionScopeExpression();
							}
							break;

						case 4:
							{
							setState(280); match(ACTION_WS);
							}
							break;

						case 5:
							{
							setState(282); match(ACTION_NEWLINE);
							}
							break;

						case 6:
							{
							setState(284); match(ACTION_COMMENT);
							}
							break;

						case 7:
							{
							setState(286); match(ACTION_LITERAL);
							}
							break;

						case 8:
							{
							setState(288); match(ACTION_TEXT);
							}
							break;

						case 9:
							{
							setState(290); match(ACTION_LT);
							}
							break;

						case 10:
							{
							setState(292); match(ACTION_GT);
							}
							break;

						case 11:
							{
							setState(294); match(ACTION_LPAREN);
							}
							break;

						case 12:
							{
							setState(296); match(ACTION_RPAREN);
							}
							break;

						case 13:
							{
							setState(298); match(ACTION_LBRACK);
							}
							break;

						case 14:
							{
							setState(300); match(ACTION_RBRACK);
							}
							break;

						case 15:
							{
							setState(302); match(ACTION_EQUALS);
							}
							break;

						case 16:
							{
							setState(304); match(ACTION_COMMA);
							}
							break;

						case 17:
							{
							setState(306); match(ACTION_ESCAPE);
							}
							break;

						case 18:
							{
							setState(308); match(ACTION_WORD);
							}
							break;

						case 19:
							{
							setState(310); match(ACTION_REFERENCE);
							}
							break;

						case 20:
							{
							setState(312); match(ACTION_COLON);
							}
							break;

						case 21:
							{
							setState(314); match(ACTION_COLON2);
							}
							break;

						case 22:
							{
							setState(316); match(ACTION_MINUS);
							}
							break;

						case 23:
							{
							setState(318); match(ACTION_DOT);
							}
							break;
					}
					} 
				}
				setState(324);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			setState(325); match(END_ACTION);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionExpressionContext extends ParserRuleContext<Token> {
		public Token ref;
		public Token op;
		public Token member;
		public List<? extends IgnoredContext> ignored() {
		    return getRuleContexts(IgnoredContext.class);
		}
		public TerminalNode<Token> ACTION_REFERENCE() { return getToken(GrammarParser.ACTION_REFERENCE, 0); }
		public TerminalNode<Token> ACTION_DOT() { return getToken(GrammarParser.ACTION_DOT, 0); }
		public IgnoredContext ignored(int i) {
		    return getRuleContext(IgnoredContext.class,i);
		}
		public TerminalNode<Token> ACTION_WORD() { return getToken(GrammarParser.ACTION_WORD, 0); }
		public ActionExpressionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterActionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitActionExpression(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitActionExpression(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ActionExpressionContext actionExpression() throws RecognitionException {
		ActionExpressionContext _localctx = new ActionExpressionContext(_ctx, 22);
		enterRule(_localctx, RULE_actionExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(327); _localctx.ref = match(ACTION_REFERENCE);
			setState(333);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(329); ignored();
					}
					} 
				}
				setState(335);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(336); _localctx.op = match(ACTION_DOT);
			setState(342);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(338); ignored();
					}
					} 
				}
				setState(344);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(345); _localctx.member = match(ACTION_WORD);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionScopeExpressionContext extends ParserRuleContext<Token> {
		public Token ref;
		public Token neg;
		public Token index;
		public Token op;
		public Token member;
		public List<? extends IgnoredContext> ignored() {
		    return getRuleContexts(IgnoredContext.class);
		}
		public TerminalNode<Token> ACTION_REFERENCE() { return getToken(GrammarParser.ACTION_REFERENCE, 0); }
		public TerminalNode<Token> ACTION_COLON2() { return getToken(GrammarParser.ACTION_COLON2, 0); }
		public TerminalNode<Token> ACTION_RBRACK() { return getToken(GrammarParser.ACTION_RBRACK, 0); }
		public IgnoredContext ignored(int i) {
		    return getRuleContext(IgnoredContext.class,i);
		}
		public TerminalNode<Token> ACTION_WORD(int i) {
		    return getToken(GrammarParser.ACTION_WORD, i);
		}
		public TerminalNode<Token> ACTION_LBRACK() { return getToken(GrammarParser.ACTION_LBRACK, 0); }
		public List<? extends TerminalNode<Token>> ACTION_WORD() { return getTokens(GrammarParser.ACTION_WORD); }
		public TerminalNode<Token> ACTION_MINUS() { return getToken(GrammarParser.ACTION_MINUS, 0); }
		public ActionScopeExpressionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterActionScopeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitActionScopeExpression(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitActionScopeExpression(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ActionScopeExpressionContext actionScopeExpression() throws RecognitionException {
		ActionScopeExpressionContext _localctx = new ActionScopeExpressionContext(_ctx, 24);
		enterRule(_localctx, RULE_actionScopeExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(347); _localctx.ref = match(ACTION_REFERENCE);
			setState(353);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(349); ignored();
					}
					} 
				}
				setState(355);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(394);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(356); match(ACTION_LBRACK);
					setState(362);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(358); ignored();
							}
							} 
						}
						setState(364);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
					}
					setState(374);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
						case 1:
							{
							setState(365); _localctx.neg = match(ACTION_MINUS);
							setState(371);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(367); ignored();
									}
									} 
								}
								setState(373);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
							}
							}
							break;
					}
					setState(376); _localctx.index = match(ACTION_WORD);
					setState(382);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(378); ignored();
							}
							} 
						}
						setState(384);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
					}
					setState(385); match(ACTION_RBRACK);
					setState(391);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(387); ignored();
							}
							} 
						}
						setState(393);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
					}
					}
					break;
			}
			setState(396); _localctx.op = match(ACTION_COLON2);
			setState(402);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(398); ignored();
					}
					} 
				}
				setState(404);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			setState(405); _localctx.member = match(ACTION_WORD);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgActionBlockContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> ARG_ACTION_GT() { return getToken(GrammarParser.ARG_ACTION_GT, 0); }
		public TerminalNode<Token> ARG_ACTION_LPAREN() { return getToken(GrammarParser.ARG_ACTION_LPAREN, 0); }
		public TerminalNode<Token> ARG_ACTION_COMMA() { return getToken(GrammarParser.ARG_ACTION_COMMA, 0); }
		public TerminalNode<Token> ARG_ACTION_NEWLINE() { return getToken(GrammarParser.ARG_ACTION_NEWLINE, 0); }
		public TerminalNode<Token> END_ARG_ACTION() { return getToken(GrammarParser.END_ARG_ACTION, 0); }
		public TerminalNode<Token> ARG_ACTION_WS() { return getToken(GrammarParser.ARG_ACTION_WS, 0); }
		public TerminalNode<Token> ARG_ACTION_WORD() { return getToken(GrammarParser.ARG_ACTION_WORD, 0); }
		public TerminalNode<Token> ARG_ACTION_LT() { return getToken(GrammarParser.ARG_ACTION_LT, 0); }
		public TerminalNode<Token> ARG_ACTION_EQUALS() { return getToken(GrammarParser.ARG_ACTION_EQUALS, 0); }
		public TerminalNode<Token> ARG_ACTION_ESCAPE() { return getToken(GrammarParser.ARG_ACTION_ESCAPE, 0); }
		public TerminalNode<Token> BEGIN_ARG_ACTION() { return getToken(GrammarParser.BEGIN_ARG_ACTION, 0); }
		public TerminalNode<Token> ARG_ACTION_ELEMENT() { return getToken(GrammarParser.ARG_ACTION_ELEMENT, 0); }
		public TerminalNode<Token> ARG_ACTION_TEXT() { return getToken(GrammarParser.ARG_ACTION_TEXT, 0); }
		public TerminalNode<Token> ARG_ACTION_RPAREN() { return getToken(GrammarParser.ARG_ACTION_RPAREN, 0); }
		public ArgActionBlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterArgActionBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitArgActionBlock(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitArgActionBlock(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ArgActionBlockContext argActionBlock() throws RecognitionException {
		ArgActionBlockContext _localctx = new ArgActionBlockContext(_ctx, 26);
		enterRule(_localctx, RULE_argActionBlock);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(407); match(BEGIN_ARG_ACTION);
			setState(413);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(409);
					_input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==ARG_ACTION_LT || _la==ARG_ACTION_GT || _la==ARG_ACTION_LPAREN || _la==ARG_ACTION_RPAREN || _la==ARG_ACTION_EQUALS || _la==ARG_ACTION_COMMA || _la==ARG_ACTION_ESCAPE || _la==ARG_ACTION_WORD || _la==ARG_ACTION_ELEMENT || _la==ARG_ACTION_TEXT || _la==ARG_ACTION_WS || _la==ARG_ACTION_NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					} 
				}
				setState(415);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			setState(416); match(END_ARG_ACTION);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgActionParametersContext extends ParserRuleContext<Token> {
		public ArgActionParameterContext argActionParameter;
		public List<ArgActionParameterContext> parameters = new ArrayList<ArgActionParameterContext>();
		public List<? extends IgnoredContext> ignored() {
		    return getRuleContexts(IgnoredContext.class);
		}
		public TerminalNode<Token> END_ARG_ACTION() { return getToken(GrammarParser.END_ARG_ACTION, 0); }
		public IgnoredContext ignored(int i) {
		    return getRuleContext(IgnoredContext.class,i);
		}
		public ArgActionParameterContext argActionParameter(int i) {
		    return getRuleContext(ArgActionParameterContext.class,i);
		}
		public List<? extends ArgActionParameterContext> argActionParameter() {
		    return getRuleContexts(ArgActionParameterContext.class);
		}
		public TerminalNode<Token> ARG_ACTION_COMMA() { return getToken(GrammarParser.ARG_ACTION_COMMA, 0); }
		public TerminalNode<Token> BEGIN_ARG_ACTION() { return getToken(GrammarParser.BEGIN_ARG_ACTION, 0); }
		public ArgActionParametersContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterArgActionParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitArgActionParameters(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitArgActionParameters(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ArgActionParametersContext argActionParameters() throws RecognitionException {
		ArgActionParametersContext _localctx = new ArgActionParametersContext(_ctx, 28);
		enterRule(_localctx, RULE_argActionParameters);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(418); match(BEGIN_ARG_ACTION);
			setState(424);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(420); ignored();
					}
					} 
				}
				setState(426);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(459);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(427); _localctx.argActionParameter = argActionParameter();
					_localctx.parameters.add(_localctx.argActionParameter);
					setState(433);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(429); ignored();
							}
							} 
						}
						setState(435);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
					}
					setState(456);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(436); match(ARG_ACTION_COMMA);
							setState(442);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(438); ignored();
									}
									} 
								}
								setState(444);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
							}
							setState(445); _localctx.argActionParameter = argActionParameter();
							_localctx.parameters.add(_localctx.argActionParameter);
							setState(451);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(447); ignored();
									}
									} 
								}
								setState(453);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
							}
							}
							} 
						}
						setState(458);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
					}
					}
					break;
			}
			setState(461); match(END_ARG_ACTION);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgActionParameterContext extends ParserRuleContext<Token> {
		public ArgActionParameterTypeContext type;
		public Token name;
		public IgnoredContext ignored() {
		    return getRuleContext(IgnoredContext.class,0);
		}
		public TerminalNode<Token> ARG_ACTION_WORD() { return getToken(GrammarParser.ARG_ACTION_WORD, 0); }
		public ArgActionParameterTypeContext argActionParameterType() {
		    return getRuleContext(ArgActionParameterTypeContext.class,0);
		}
		public ArgActionParameterContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterArgActionParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitArgActionParameter(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitArgActionParameter(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ArgActionParameterContext argActionParameter() throws RecognitionException {
		ArgActionParameterContext _localctx = new ArgActionParameterContext(_ctx, 30);
		enterRule(_localctx, RULE_argActionParameter);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(463); _localctx.type = argActionParameterType();
					}
					break;
			}
			setState(471);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(467); ignored();
					}
					} 
				}
				setState(473);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			setState(474); _localctx.name = match(ARG_ACTION_WORD);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgActionParameterTypeContext extends ParserRuleContext<Token> {
		public IgnoredContext ignored() {
		    return getRuleContext(IgnoredContext.class,0);
		}
		public ArgActionParameterTypePartContext argActionParameterTypePart(int i) {
		    return getRuleContext(ArgActionParameterTypePartContext.class,i);
		}
		public List<? extends ArgActionParameterTypePartContext> argActionParameterTypePart() {
		    return getRuleContexts(ArgActionParameterTypePartContext.class);
		}
		public ArgActionParameterTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterArgActionParameterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitArgActionParameterType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitArgActionParameterType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ArgActionParameterTypeContext argActionParameterType() throws RecognitionException {
		ArgActionParameterTypeContext _localctx = new ArgActionParameterTypeContext(_ctx, 32);
		enterRule(_localctx, RULE_argActionParameterType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(476); argActionParameterTypePart();
			setState(489);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(482);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(478); ignored();
							}
							} 
						}
						setState(484);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
					}
					setState(485); argActionParameterTypePart();
					}
					} 
				}
				setState(491);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgActionParameterTypePartContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> ARG_ACTION_GT() { return getToken(GrammarParser.ARG_ACTION_GT, 0); }
		public TerminalNode<Token> ARG_ACTION_LPAREN() { return getToken(GrammarParser.ARG_ACTION_LPAREN, 0); }
		public TerminalNode<Token> ARG_ACTION_WORD() { return getToken(GrammarParser.ARG_ACTION_WORD, 0); }
		public ArgActionParameterTypeContext argActionParameterType() {
		    return getRuleContext(ArgActionParameterTypeContext.class,0);
		}
		public TerminalNode<Token> ARG_ACTION_LT() { return getToken(GrammarParser.ARG_ACTION_LT, 0); }
		public TerminalNode<Token> ARG_ACTION_RPAREN() { return getToken(GrammarParser.ARG_ACTION_RPAREN, 0); }
		public ArgActionParameterTypePartContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterArgActionParameterTypePart(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitArgActionParameterTypePart(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitArgActionParameterTypePart(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ArgActionParameterTypePartContext argActionParameterTypePart() throws RecognitionException {
		ArgActionParameterTypePartContext _localctx = new ArgActionParameterTypePartContext(_ctx, 34);
		enterRule(_localctx, RULE_argActionParameterTypePart);
		try {
			setState(510);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(492); match(ARG_ACTION_WORD);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(494); match(ARG_ACTION_LT);
					setState(498);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
						case 1:
							{
							setState(496); argActionParameterType();
							}
							break;
					}
					setState(500); match(ARG_ACTION_GT);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(502); match(ARG_ACTION_LPAREN);
					setState(506);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
						case 1:
							{
							setState(504); argActionParameterType();
							}
							break;
					}
					setState(508); match(ARG_ACTION_RPAREN);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IgnoredContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> ARG_ACTION_NEWLINE() { return getToken(GrammarParser.ARG_ACTION_NEWLINE, 0); }
		public TerminalNode<Token> ARG_ACTION_WS() { return getToken(GrammarParser.ARG_ACTION_WS, 0); }
		public TerminalNode<Token> ACTION_WS() { return getToken(GrammarParser.ACTION_WS, 0); }
		public TerminalNode<Token> ACTION_COMMENT() { return getToken(GrammarParser.ACTION_COMMENT, 0); }
		public TerminalNode<Token> ACTION_NEWLINE() { return getToken(GrammarParser.ACTION_NEWLINE, 0); }
		public IgnoredContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterIgnored(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitIgnored(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitIgnored(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final IgnoredContext ignored() throws RecognitionException {
		IgnoredContext _localctx = new IgnoredContext(_ctx, 36);
		enterRule(_localctx, RULE_ignored);
		try {
			setState(522);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(512); match(ACTION_WS);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(514); match(ACTION_NEWLINE);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(516); match(ACTION_COMMENT);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(518); match(ARG_ACTION_WS);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(520); match(ARG_ACTION_NEWLINE);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionContext extends ParserRuleContext<Token> {
		public ActionBlockContext actionBlock() {
		    return getRuleContext(ActionBlockContext.class,0);
		}
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public TerminalNode<Token> AT() { return getToken(GrammarParser.AT, 0); }
		public TerminalNode<Token> COLONCOLON() { return getToken(GrammarParser.COLONCOLON, 0); }
		public ActionScopeNameContext actionScopeName() {
		    return getRuleContext(ActionScopeNameContext.class,0);
		}
		public ActionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitAction(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitAction(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, 38);
		enterRule(_localctx, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(524); match(AT);
			setState(530);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
				case 1:
					{
					setState(526); actionScopeName();
					setState(528); match(COLONCOLON);
					}
					break;
			}
			setState(532); id();
			setState(534); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionScopeNameContext extends ParserRuleContext<Token> {
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public TerminalNode<Token> PARSER() { return getToken(GrammarParser.PARSER, 0); }
		public TerminalNode<Token> LEXER() { return getToken(GrammarParser.LEXER, 0); }
		public ActionScopeNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterActionScopeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitActionScopeName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitActionScopeName(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ActionScopeNameContext actionScopeName() throws RecognitionException {
		ActionScopeNameContext _localctx = new ActionScopeNameContext(_ctx, 40);
		enterRule(_localctx, RULE_actionScopeName);
		try {
			setState(542);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(536); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(538); match(LEXER);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(540); match(PARSER);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModeSpecContext extends ParserRuleContext<Token> {
		public RuleSpecContext ruleSpec() {
		    return getRuleContext(RuleSpecContext.class,0);
		}
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public TerminalNode<Token> SEMI() { return getToken(GrammarParser.SEMI, 0); }
		public TerminalNode<Token> MODE() { return getToken(GrammarParser.MODE, 0); }
		public ModeSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterModeSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitModeSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitModeSpec(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ModeSpecContext modeSpec() throws RecognitionException {
		ModeSpecContext _localctx = new ModeSpecContext(_ctx, 42);
		enterRule(_localctx, RULE_modeSpec);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(544); match(MODE);
			setState(546); id();
			setState(548); match(SEMI);
			setState(552); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(550); ruleSpec();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(554); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RulesContext extends ParserRuleContext<Token> {
		public RuleSpecContext ruleSpec() {
		    return getRuleContext(RuleSpecContext.class,0);
		}
		public RulesContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitRules(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitRules(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RulesContext rules() throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, 44);
		enterRule(_localctx, RULE_rules);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(560);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(556); ruleSpec();
					}
					} 
				}
				setState(562);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleSpecContext extends ParserRuleContext<Token> {
		public ParserRuleSpecContext parserRuleSpec() {
		    return getRuleContext(ParserRuleSpecContext.class,0);
		}
		public LexerRuleContext lexerRule() {
		    return getRuleContext(LexerRuleContext.class,0);
		}
		public RuleSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterRuleSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitRuleSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitRuleSpec(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RuleSpecContext ruleSpec() throws RecognitionException {
		RuleSpecContext _localctx = new RuleSpecContext(_ctx, 46);
		enterRule(_localctx, RULE_ruleSpec);
		try {
			setState(567);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(563); parserRuleSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(565); lexerRule();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParserRuleSpecContext extends ParserRuleContext<Token> {
		public Token name;
		public TerminalNode<Token> DOC_COMMENT() { return getToken(GrammarParser.DOC_COMMENT, 0); }
		public TerminalNode<Token> COLON() { return getToken(GrammarParser.COLON, 0); }
		public RuleModifiersContext ruleModifiers() {
		    return getRuleContext(RuleModifiersContext.class,0);
		}
		public ExceptionGroupContext exceptionGroup() {
		    return getRuleContext(ExceptionGroupContext.class,0);
		}
		public RulePrequelsContext rulePrequels() {
		    return getRuleContext(RulePrequelsContext.class,0);
		}
		public RuleReturnsContext ruleReturns() {
		    return getRuleContext(RuleReturnsContext.class,0);
		}
		public ArgActionParametersContext argActionParameters() {
		    return getRuleContext(ArgActionParametersContext.class,0);
		}
		public ThrowsSpecContext throwsSpec() {
		    return getRuleContext(ThrowsSpecContext.class,0);
		}
		public RuleBlockContext ruleBlock() {
		    return getRuleContext(RuleBlockContext.class,0);
		}
		public LocalsSpecContext localsSpec() {
		    return getRuleContext(LocalsSpecContext.class,0);
		}
		public TerminalNode<Token> SEMI() { return getToken(GrammarParser.SEMI, 0); }
		public TerminalNode<Token> RULE_REF() { return getToken(GrammarParser.RULE_REF, 0); }
		public ParserRuleSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterParserRuleSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitParserRuleSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitParserRuleSpec(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ParserRuleSpecContext parserRuleSpec() throws RecognitionException {
		ParserRuleSpecContext _localctx = new ParserRuleSpecContext(_ctx, 48);
		enterRule(_localctx, RULE_parserRuleSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(571);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(569); match(DOC_COMMENT);
					}
					break;
			}
			setState(575);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(573); ruleModifiers();
					}
					break;
			}
			setState(577); _localctx.name = match(RULE_REF);
			setState(581);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					{
					setState(579); argActionParameters();
					}
					break;
			}
			setState(585);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(583); ruleReturns();
					}
					break;
			}
			setState(589);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					{
					setState(587); throwsSpec();
					}
					break;
			}
			setState(593);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
				case 1:
					{
					setState(591); localsSpec();
					}
					break;
			}
			setState(595); rulePrequels();
			setState(597); match(COLON);
			setState(599); ruleBlock();
			setState(601); match(SEMI);
			setState(603); exceptionGroup();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExceptionGroupContext extends ParserRuleContext<Token> {
		public FinallyClauseContext finallyClause() {
		    return getRuleContext(FinallyClauseContext.class,0);
		}
		public ExceptionHandlerContext exceptionHandler() {
		    return getRuleContext(ExceptionHandlerContext.class,0);
		}
		public ExceptionGroupContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterExceptionGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitExceptionGroup(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitExceptionGroup(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ExceptionGroupContext exceptionGroup() throws RecognitionException {
		ExceptionGroupContext _localctx = new ExceptionGroupContext(_ctx, 50);
		enterRule(_localctx, RULE_exceptionGroup);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(609);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(605); exceptionHandler();
					}
					} 
				}
				setState(611);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
			}
			setState(614);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
				case 1:
					{
					setState(612); finallyClause();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExceptionHandlerContext extends ParserRuleContext<Token> {
		public ActionBlockContext actionBlock() {
		    return getRuleContext(ActionBlockContext.class,0);
		}
		public ArgActionBlockContext argActionBlock() {
		    return getRuleContext(ArgActionBlockContext.class,0);
		}
		public TerminalNode<Token> CATCH() { return getToken(GrammarParser.CATCH, 0); }
		public ExceptionHandlerContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterExceptionHandler(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitExceptionHandler(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitExceptionHandler(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ExceptionHandlerContext exceptionHandler() throws RecognitionException {
		ExceptionHandlerContext _localctx = new ExceptionHandlerContext(_ctx, 52);
		enterRule(_localctx, RULE_exceptionHandler);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(616); match(CATCH);
			setState(618); argActionBlock();
			setState(620); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FinallyClauseContext extends ParserRuleContext<Token> {
		public ActionBlockContext actionBlock() {
		    return getRuleContext(ActionBlockContext.class,0);
		}
		public TerminalNode<Token> FINALLY() { return getToken(GrammarParser.FINALLY, 0); }
		public FinallyClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterFinallyClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitFinallyClause(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitFinallyClause(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final FinallyClauseContext finallyClause() throws RecognitionException {
		FinallyClauseContext _localctx = new FinallyClauseContext(_ctx, 54);
		enterRule(_localctx, RULE_finallyClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(622); match(FINALLY);
			setState(624); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RulePrequelsContext extends ParserRuleContext<Token> {
		public RulePrequelContext rulePrequel() {
		    return getRuleContext(RulePrequelContext.class,0);
		}
		public RulePrequelsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterRulePrequels(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitRulePrequels(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitRulePrequels(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RulePrequelsContext rulePrequels() throws RecognitionException {
		RulePrequelsContext _localctx = new RulePrequelsContext(_ctx, 56);
		enterRule(_localctx, RULE_rulePrequels);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(630);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(626); rulePrequel();
					}
					} 
				}
				setState(632);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RulePrequelContext extends ParserRuleContext<Token> {
		public OptionsSpecContext optionsSpec() {
		    return getRuleContext(OptionsSpecContext.class,0);
		}
		public RuleActionContext ruleAction() {
		    return getRuleContext(RuleActionContext.class,0);
		}
		public RulePrequelContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterRulePrequel(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitRulePrequel(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitRulePrequel(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RulePrequelContext rulePrequel() throws RecognitionException {
		RulePrequelContext _localctx = new RulePrequelContext(_ctx, 58);
		enterRule(_localctx, RULE_rulePrequel);
		try {
			setState(637);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(633); optionsSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(635); ruleAction();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleReturnsContext extends ParserRuleContext<Token> {
		public ArgActionParametersContext argActionParameters() {
		    return getRuleContext(ArgActionParametersContext.class,0);
		}
		public TerminalNode<Token> RETURNS() { return getToken(GrammarParser.RETURNS, 0); }
		public RuleReturnsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterRuleReturns(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitRuleReturns(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitRuleReturns(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RuleReturnsContext ruleReturns() throws RecognitionException {
		RuleReturnsContext _localctx = new RuleReturnsContext(_ctx, 60);
		enterRule(_localctx, RULE_ruleReturns);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(639); match(RETURNS);
			setState(641); argActionParameters();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThrowsSpecContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> THROWS() { return getToken(GrammarParser.THROWS, 0); }
		public QidContext qid(int i) {
		    return getRuleContext(QidContext.class,i);
		}
		public List<? extends QidContext> qid() {
		    return getRuleContexts(QidContext.class);
		}
		public TerminalNode<Token> COMMA() { return getToken(GrammarParser.COMMA, 0); }
		public ThrowsSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterThrowsSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitThrowsSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitThrowsSpec(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ThrowsSpecContext throwsSpec() throws RecognitionException {
		ThrowsSpecContext _localctx = new ThrowsSpecContext(_ctx, 62);
		enterRule(_localctx, RULE_throwsSpec);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(643); match(THROWS);
			setState(645); qid();
			setState(653);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(647); match(COMMA);
					setState(649); qid();
					}
					} 
				}
				setState(655);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocalsSpecContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> LOCALS() { return getToken(GrammarParser.LOCALS, 0); }
		public ArgActionParametersContext argActionParameters() {
		    return getRuleContext(ArgActionParametersContext.class,0);
		}
		public LocalsSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLocalsSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLocalsSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLocalsSpec(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LocalsSpecContext localsSpec() throws RecognitionException {
		LocalsSpecContext _localctx = new LocalsSpecContext(_ctx, 64);
		enterRule(_localctx, RULE_localsSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(656); match(LOCALS);
			setState(658); argActionParameters();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleActionContext extends ParserRuleContext<Token> {
		public ActionBlockContext actionBlock() {
		    return getRuleContext(ActionBlockContext.class,0);
		}
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public TerminalNode<Token> AT() { return getToken(GrammarParser.AT, 0); }
		public RuleActionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterRuleAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitRuleAction(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitRuleAction(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RuleActionContext ruleAction() throws RecognitionException {
		RuleActionContext _localctx = new RuleActionContext(_ctx, 66);
		enterRule(_localctx, RULE_ruleAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(660); match(AT);
			setState(662); id();
			setState(664); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleModifiersContext extends ParserRuleContext<Token> {
		public RuleModifierContext ruleModifier() {
		    return getRuleContext(RuleModifierContext.class,0);
		}
		public RuleModifiersContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterRuleModifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitRuleModifiers(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitRuleModifiers(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RuleModifiersContext ruleModifiers() throws RecognitionException {
		RuleModifiersContext _localctx = new RuleModifiersContext(_ctx, 68);
		enterRule(_localctx, RULE_ruleModifiers);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(668); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(666); ruleModifier();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(670); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleModifierContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> PROTECTED() { return getToken(GrammarParser.PROTECTED, 0); }
		public TerminalNode<Token> PRIVATE() { return getToken(GrammarParser.PRIVATE, 0); }
		public TerminalNode<Token> PUBLIC() { return getToken(GrammarParser.PUBLIC, 0); }
		public TerminalNode<Token> FRAGMENT() { return getToken(GrammarParser.FRAGMENT, 0); }
		public RuleModifierContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterRuleModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitRuleModifier(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitRuleModifier(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RuleModifierContext ruleModifier() throws RecognitionException {
		RuleModifierContext _localctx = new RuleModifierContext(_ctx, 70);
		enterRule(_localctx, RULE_ruleModifier);
		try {
			setState(680);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(672); match(PUBLIC);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(674); match(PRIVATE);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(676); match(PROTECTED);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(678); match(FRAGMENT);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleBlockContext extends ParserRuleContext<Token> {
		public RuleAltListContext ruleAltList() {
		    return getRuleContext(RuleAltListContext.class,0);
		}
		public RuleBlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterRuleBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitRuleBlock(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitRuleBlock(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RuleBlockContext ruleBlock() throws RecognitionException {
		RuleBlockContext _localctx = new RuleBlockContext(_ctx, 72);
		enterRule(_localctx, RULE_ruleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(682); ruleAltList();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleAltListContext extends ParserRuleContext<Token> {
		public List<? extends LabeledAltContext> labeledAlt() {
		    return getRuleContexts(LabeledAltContext.class);
		}
		public LabeledAltContext labeledAlt(int i) {
		    return getRuleContext(LabeledAltContext.class,i);
		}
		public TerminalNode<Token> OR() { return getToken(GrammarParser.OR, 0); }
		public RuleAltListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterRuleAltList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitRuleAltList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitRuleAltList(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RuleAltListContext ruleAltList() throws RecognitionException {
		RuleAltListContext _localctx = new RuleAltListContext(_ctx, 74);
		enterRule(_localctx, RULE_ruleAltList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(684); labeledAlt();
			setState(692);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(686); match(OR);
					setState(688); labeledAlt();
					}
					} 
				}
				setState(694);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabeledAltContext extends ParserRuleContext<Token> {
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public AlternativeContext alternative() {
		    return getRuleContext(AlternativeContext.class,0);
		}
		public TerminalNode<Token> RARROW() { return getToken(GrammarParser.RARROW, 0); }
		public LabeledAltContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLabeledAlt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLabeledAlt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLabeledAlt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LabeledAltContext labeledAlt() throws RecognitionException {
		LabeledAltContext _localctx = new LabeledAltContext(_ctx, 76);
		enterRule(_localctx, RULE_labeledAlt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(695); alternative();
			setState(701);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					{
					setState(697); match(RARROW);
					setState(699); id();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerRuleContext extends ParserRuleContext<Token> {
		public Token name;
		public TerminalNode<Token> DOC_COMMENT() { return getToken(GrammarParser.DOC_COMMENT, 0); }
		public TerminalNode<Token> COLON() { return getToken(GrammarParser.COLON, 0); }
		public TerminalNode<Token> SEMI() { return getToken(GrammarParser.SEMI, 0); }
		public TerminalNode<Token> FRAGMENT() { return getToken(GrammarParser.FRAGMENT, 0); }
		public TerminalNode<Token> TOKEN_REF() { return getToken(GrammarParser.TOKEN_REF, 0); }
		public LexerRuleBlockContext lexerRuleBlock() {
		    return getRuleContext(LexerRuleBlockContext.class,0);
		}
		public LexerRuleContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLexerRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLexerRule(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLexerRule(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LexerRuleContext lexerRule() throws RecognitionException {
		LexerRuleContext _localctx = new LexerRuleContext(_ctx, 78);
		enterRule(_localctx, RULE_lexerRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(705);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
				case 1:
					{
					setState(703); match(DOC_COMMENT);
					}
					break;
			}
			setState(709);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
				case 1:
					{
					setState(707); match(FRAGMENT);
					}
					break;
			}
			setState(711); _localctx.name = match(TOKEN_REF);
			setState(713); match(COLON);
			setState(715); lexerRuleBlock();
			setState(717); match(SEMI);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerRuleBlockContext extends ParserRuleContext<Token> {
		public LexerAltListContext lexerAltList() {
		    return getRuleContext(LexerAltListContext.class,0);
		}
		public LexerRuleBlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLexerRuleBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLexerRuleBlock(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLexerRuleBlock(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LexerRuleBlockContext lexerRuleBlock() throws RecognitionException {
		LexerRuleBlockContext _localctx = new LexerRuleBlockContext(_ctx, 80);
		enterRule(_localctx, RULE_lexerRuleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(719); lexerAltList();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerAltListContext extends ParserRuleContext<Token> {
		public LexerAltContext lexerAlt(int i) {
		    return getRuleContext(LexerAltContext.class,i);
		}
		public TerminalNode<Token> OR() { return getToken(GrammarParser.OR, 0); }
		public List<? extends LexerAltContext> lexerAlt() {
		    return getRuleContexts(LexerAltContext.class);
		}
		public LexerAltListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLexerAltList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLexerAltList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLexerAltList(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LexerAltListContext lexerAltList() throws RecognitionException {
		LexerAltListContext _localctx = new LexerAltListContext(_ctx, 82);
		enterRule(_localctx, RULE_lexerAltList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(721); lexerAlt();
			setState(729);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(723); match(OR);
					setState(725); lexerAlt();
					}
					} 
				}
				setState(731);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerAltContext extends ParserRuleContext<Token> {
		public LexerElementsContext lexerElements() {
		    return getRuleContext(LexerElementsContext.class,0);
		}
		public LexerCommandsContext lexerCommands() {
		    return getRuleContext(LexerCommandsContext.class,0);
		}
		public LexerAltContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLexerAlt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLexerAlt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLexerAlt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LexerAltContext lexerAlt() throws RecognitionException {
		LexerAltContext _localctx = new LexerAltContext(_ctx, 84);
		enterRule(_localctx, RULE_lexerAlt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(732); lexerElements();
			setState(738);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
				case 1:
					{
					setState(734); lexerCommands();
					}
					break;

				case 2:
					{
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerElementsContext extends ParserRuleContext<Token> {
		public LexerElementContext lexerElement() {
		    return getRuleContext(LexerElementContext.class,0);
		}
		public LexerElementsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLexerElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLexerElements(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLexerElements(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LexerElementsContext lexerElements() throws RecognitionException {
		LexerElementsContext _localctx = new LexerElementsContext(_ctx, 86);
		enterRule(_localctx, RULE_lexerElements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(742); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(740); lexerElement();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(744); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerElementContext extends ParserRuleContext<Token> {
		public ActionBlockContext actionBlock() {
		    return getRuleContext(ActionBlockContext.class,0);
		}
		public EbnfSuffixContext ebnfSuffix() {
		    return getRuleContext(EbnfSuffixContext.class,0);
		}
		public LexerBlockContext lexerBlock() {
		    return getRuleContext(LexerBlockContext.class,0);
		}
		public TerminalNode<Token> QUESTION() { return getToken(GrammarParser.QUESTION, 0); }
		public LabeledLexerElementContext labeledLexerElement() {
		    return getRuleContext(LabeledLexerElementContext.class,0);
		}
		public LexerAtomContext lexerAtom() {
		    return getRuleContext(LexerAtomContext.class,0);
		}
		public LexerElementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLexerElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLexerElement(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLexerElement(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LexerElementContext lexerElement() throws RecognitionException {
		LexerElementContext _localctx = new LexerElementContext(_ctx, 88);
		enterRule(_localctx, RULE_lexerElement);
		try {
			setState(776);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(746); labeledLexerElement();
					setState(752);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
						case 1:
							{
							setState(748); ebnfSuffix();
							}
							break;

						case 2:
							{
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(754); lexerAtom();
					setState(760);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
						case 1:
							{
							setState(756); ebnfSuffix();
							}
							break;

						case 2:
							{
							}
							break;
					}
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(762); lexerBlock();
					setState(768);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
						case 1:
							{
							setState(764); ebnfSuffix();
							}
							break;

						case 2:
							{
							}
							break;
					}
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(770); actionBlock();
					setState(774);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
						case 1:
							{
							setState(772); match(QUESTION);
							}
							break;
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabeledLexerElementContext extends ParserRuleContext<Token> {
		public Token ass;
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public BlockContext block() {
		    return getRuleContext(BlockContext.class,0);
		}
		public LexerAtomContext lexerAtom() {
		    return getRuleContext(LexerAtomContext.class,0);
		}
		public TerminalNode<Token> PLUS_ASSIGN() { return getToken(GrammarParser.PLUS_ASSIGN, 0); }
		public TerminalNode<Token> ASSIGN() { return getToken(GrammarParser.ASSIGN, 0); }
		public LabeledLexerElementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLabeledLexerElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLabeledLexerElement(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLabeledLexerElement(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LabeledLexerElementContext labeledLexerElement() throws RecognitionException {
		LabeledLexerElementContext _localctx = new LabeledLexerElementContext(_ctx, 90);
		enterRule(_localctx, RULE_labeledLexerElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(778); id();
			setState(784);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
				case 1:
					{
					setState(780); _localctx.ass = match(ASSIGN);
					}
					break;

				case 2:
					{
					setState(782); _localctx.ass = match(PLUS_ASSIGN);
					}
					break;
			}
			setState(790);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
				case 1:
					{
					setState(786); lexerAtom();
					}
					break;

				case 2:
					{
					setState(788); block();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerBlockContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> RPAREN() { return getToken(GrammarParser.RPAREN, 0); }
		public LexerAltListContext lexerAltList() {
		    return getRuleContext(LexerAltListContext.class,0);
		}
		public TerminalNode<Token> LPAREN() { return getToken(GrammarParser.LPAREN, 0); }
		public LexerBlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLexerBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLexerBlock(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLexerBlock(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LexerBlockContext lexerBlock() throws RecognitionException {
		LexerBlockContext _localctx = new LexerBlockContext(_ctx, 92);
		enterRule(_localctx, RULE_lexerBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(792); match(LPAREN);
			setState(794); lexerAltList();
			setState(796); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerCommandsContext extends ParserRuleContext<Token> {
		public List<? extends LexerCommandContext> lexerCommand() {
		    return getRuleContexts(LexerCommandContext.class);
		}
		public TerminalNode<Token> COMMA() { return getToken(GrammarParser.COMMA, 0); }
		public LexerCommandContext lexerCommand(int i) {
		    return getRuleContext(LexerCommandContext.class,i);
		}
		public TerminalNode<Token> RARROW() { return getToken(GrammarParser.RARROW, 0); }
		public LexerCommandsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLexerCommands(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLexerCommands(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLexerCommands(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LexerCommandsContext lexerCommands() throws RecognitionException {
		LexerCommandsContext _localctx = new LexerCommandsContext(_ctx, 94);
		enterRule(_localctx, RULE_lexerCommands);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(798); match(RARROW);
			setState(800); lexerCommand();
			setState(808);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(802); match(COMMA);
					setState(804); lexerCommand();
					}
					} 
				}
				setState(810);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerCommandContext extends ParserRuleContext<Token> {
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public TerminalNode<Token> RPAREN() { return getToken(GrammarParser.RPAREN, 0); }
		public LexerCommandExprContext lexerCommandExpr() {
		    return getRuleContext(LexerCommandExprContext.class,0);
		}
		public TerminalNode<Token> LPAREN() { return getToken(GrammarParser.LPAREN, 0); }
		public LexerCommandContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLexerCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLexerCommand(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLexerCommand(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LexerCommandContext lexerCommand() throws RecognitionException {
		LexerCommandContext _localctx = new LexerCommandContext(_ctx, 96);
		enterRule(_localctx, RULE_lexerCommand);
		try {
			setState(821);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(811); id();
					setState(813); match(LPAREN);
					setState(815); lexerCommandExpr();
					setState(817); match(RPAREN);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(819); id();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerCommandExprContext extends ParserRuleContext<Token> {
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public TerminalNode<Token> INT() { return getToken(GrammarParser.INT, 0); }
		public LexerCommandExprContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLexerCommandExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLexerCommandExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLexerCommandExpr(this);
			else return null;
		}
	}

	@RuleVersion(1)
	public final LexerCommandExprContext lexerCommandExpr() throws RecognitionException {
		LexerCommandExprContext _localctx = new LexerCommandExprContext(_ctx, 98);
		enterRule(_localctx, RULE_lexerCommandExpr);
		try {
			setState(827);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(823); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(825); match(INT);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AltListContext extends ParserRuleContext<Token> {
		public AlternativeContext alternative(int i) {
		    return getRuleContext(AlternativeContext.class,i);
		}
		public List<? extends AlternativeContext> alternative() {
		    return getRuleContexts(AlternativeContext.class);
		}
		public TerminalNode<Token> OR() { return getToken(GrammarParser.OR, 0); }
		public AltListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterAltList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitAltList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitAltList(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final AltListContext altList() throws RecognitionException {
		AltListContext _localctx = new AltListContext(_ctx, 100);
		enterRule(_localctx, RULE_altList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(829); alternative();
			setState(837);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(831); match(OR);
					setState(833); alternative();
					}
					} 
				}
				setState(839);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AlternativeContext extends ParserRuleContext<Token> {
		public ElementsContext elements() {
		    return getRuleContext(ElementsContext.class,0);
		}
		public AlternativeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterAlternative(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitAlternative(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitAlternative(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final AlternativeContext alternative() throws RecognitionException {
		AlternativeContext _localctx = new AlternativeContext(_ctx, 102);
		enterRule(_localctx, RULE_alternative);
		try {
			setState(844);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(840); elements();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementsContext extends ParserRuleContext<Token> {
		public ElementContext element() {
		    return getRuleContext(ElementContext.class,0);
		}
		public ElementsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitElements(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitElements(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ElementsContext elements() throws RecognitionException {
		ElementsContext _localctx = new ElementsContext(_ctx, 104);
		enterRule(_localctx, RULE_elements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(848); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(846); element();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(850); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementContext extends ParserRuleContext<Token> {
		public ActionBlockContext actionBlock() {
		    return getRuleContext(ActionBlockContext.class,0);
		}
		public AtomContext atom() {
		    return getRuleContext(AtomContext.class,0);
		}
		public EbnfSuffixContext ebnfSuffix() {
		    return getRuleContext(EbnfSuffixContext.class,0);
		}
		public EbnfContext ebnf() {
		    return getRuleContext(EbnfContext.class,0);
		}
		public TerminalNode<Token> QUESTION() { return getToken(GrammarParser.QUESTION, 0); }
		public LabeledElementContext labeledElement() {
		    return getRuleContext(LabeledElementContext.class,0);
		}
		public ElementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitElement(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitElement(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, 106);
		enterRule(_localctx, RULE_element);
		try {
			setState(876);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(852); labeledElement();
					setState(858);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
						case 1:
							{
							setState(854); ebnfSuffix();
							}
							break;

						case 2:
							{
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(860); atom();
					setState(866);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
						case 1:
							{
							setState(862); ebnfSuffix();
							}
							break;

						case 2:
							{
							}
							break;
					}
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(868); ebnf();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(870); actionBlock();
					setState(874);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
						case 1:
							{
							setState(872); match(QUESTION);
							}
							break;
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabeledElementContext extends ParserRuleContext<Token> {
		public IdContext label;
		public Token ass;
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public AtomContext atom() {
		    return getRuleContext(AtomContext.class,0);
		}
		public BlockContext block() {
		    return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode<Token> PLUS_ASSIGN() { return getToken(GrammarParser.PLUS_ASSIGN, 0); }
		public TerminalNode<Token> ASSIGN() { return getToken(GrammarParser.ASSIGN, 0); }
		public LabeledElementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLabeledElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLabeledElement(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLabeledElement(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LabeledElementContext labeledElement() throws RecognitionException {
		LabeledElementContext _localctx = new LabeledElementContext(_ctx, 108);
		enterRule(_localctx, RULE_labeledElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(878); _localctx.label = id();
			setState(884);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					{
					setState(880); _localctx.ass = match(ASSIGN);
					}
					break;

				case 2:
					{
					setState(882); _localctx.ass = match(PLUS_ASSIGN);
					}
					break;
			}
			setState(890);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
				case 1:
					{
					setState(886); atom();
					}
					break;

				case 2:
					{
					setState(888); block();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EbnfContext extends ParserRuleContext<Token> {
		public BlockContext block() {
		    return getRuleContext(BlockContext.class,0);
		}
		public BlockSuffixContext blockSuffix() {
		    return getRuleContext(BlockSuffixContext.class,0);
		}
		public EbnfContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterEbnf(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitEbnf(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitEbnf(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final EbnfContext ebnf() throws RecognitionException {
		EbnfContext _localctx = new EbnfContext(_ctx, 110);
		enterRule(_localctx, RULE_ebnf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(892); block();
			setState(898);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
				case 1:
					{
					setState(894); blockSuffix();
					}
					break;

				case 2:
					{
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockSuffixContext extends ParserRuleContext<Token> {
		public EbnfSuffixContext ebnfSuffix() {
		    return getRuleContext(EbnfSuffixContext.class,0);
		}
		public BlockSuffixContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterBlockSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitBlockSuffix(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitBlockSuffix(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final BlockSuffixContext blockSuffix() throws RecognitionException {
		BlockSuffixContext _localctx = new BlockSuffixContext(_ctx, 112);
		enterRule(_localctx, RULE_blockSuffix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(900); ebnfSuffix();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EbnfSuffixContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> PLUS() { return getToken(GrammarParser.PLUS, 0); }
		public TerminalNode<Token> STAR() { return getToken(GrammarParser.STAR, 0); }
		public TerminalNode<Token> QUESTION() { return getToken(GrammarParser.QUESTION, 0); }
		public EbnfSuffixContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterEbnfSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitEbnfSuffix(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitEbnfSuffix(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final EbnfSuffixContext ebnfSuffix() throws RecognitionException {
		EbnfSuffixContext _localctx = new EbnfSuffixContext(_ctx, 114);
		enterRule(_localctx, RULE_ebnfSuffix);
		try {
			setState(908);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(902); match(QUESTION);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(904); match(STAR);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(906); match(PLUS);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerAtomContext extends ParserRuleContext<Token> {
		public ArgActionBlockContext argActionBlock() {
		    return getRuleContext(ArgActionBlockContext.class,0);
		}
		public TerminalContext terminal() {
		    return getRuleContext(TerminalContext.class,0);
		}
		public RangeContext range() {
		    return getRuleContext(RangeContext.class,0);
		}
		public ElementOptionsContext elementOptions() {
		    return getRuleContext(ElementOptionsContext.class,0);
		}
		public NotSetContext notSet() {
		    return getRuleContext(NotSetContext.class,0);
		}
		public TerminalNode<Token> DOT() { return getToken(GrammarParser.DOT, 0); }
		public TerminalNode<Token> RULE_REF() { return getToken(GrammarParser.RULE_REF, 0); }
		public LexerAtomContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLexerAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLexerAtom(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLexerAtom(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LexerAtomContext lexerAtom() throws RecognitionException {
		LexerAtomContext _localctx = new LexerAtomContext(_ctx, 116);
		enterRule(_localctx, RULE_lexerAtom);
		try {
			setState(926);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(910); range();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(912); terminal();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(914); match(RULE_REF);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(916); notSet();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(918); argActionBlock();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(920); match(DOT);
					setState(924);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
						case 1:
							{
							setState(922); elementOptions();
							}
							break;
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext<Token> {
		public TerminalContext terminal() {
		    return getRuleContext(TerminalContext.class,0);
		}
		public RangeContext range() {
		    return getRuleContext(RangeContext.class,0);
		}
		public ElementOptionsContext elementOptions() {
		    return getRuleContext(ElementOptionsContext.class,0);
		}
		public NotSetContext notSet() {
		    return getRuleContext(NotSetContext.class,0);
		}
		public TerminalNode<Token> DOT() { return getToken(GrammarParser.DOT, 0); }
		public RulerefContext ruleref() {
		    return getRuleContext(RulerefContext.class,0);
		}
		public AtomContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitAtom(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitAtom(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, 118);
		enterRule(_localctx, RULE_atom);
		try {
			setState(942);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(928); range();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(930); terminal();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(932); ruleref();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(934); notSet();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(936); match(DOT);
					setState(940);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
						case 1:
							{
							setState(938); elementOptions();
							}
							break;
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NotSetContext extends ParserRuleContext<Token> {
		public SetElementContext setElement() {
		    return getRuleContext(SetElementContext.class,0);
		}
		public BlockSetContext blockSet() {
		    return getRuleContext(BlockSetContext.class,0);
		}
		public TerminalNode<Token> NOT() { return getToken(GrammarParser.NOT, 0); }
		public NotSetContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterNotSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitNotSet(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitNotSet(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final NotSetContext notSet() throws RecognitionException {
		NotSetContext _localctx = new NotSetContext(_ctx, 120);
		enterRule(_localctx, RULE_notSet);
		try {
			setState(952);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(944); match(NOT);
					setState(946); setElement();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(948); match(NOT);
					setState(950); blockSet();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockSetContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> RPAREN() { return getToken(GrammarParser.RPAREN, 0); }
		public List<? extends SetElementContext> setElement() {
		    return getRuleContexts(SetElementContext.class);
		}
		public SetElementContext setElement(int i) {
		    return getRuleContext(SetElementContext.class,i);
		}
		public TerminalNode<Token> LPAREN() { return getToken(GrammarParser.LPAREN, 0); }
		public TerminalNode<Token> OR() { return getToken(GrammarParser.OR, 0); }
		public BlockSetContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterBlockSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitBlockSet(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitBlockSet(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final BlockSetContext blockSet() throws RecognitionException {
		BlockSetContext _localctx = new BlockSetContext(_ctx, 122);
		enterRule(_localctx, RULE_blockSet);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(954); match(LPAREN);
			setState(956); setElement();
			setState(964);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(958); match(OR);
					setState(960); setElement();
					}
					} 
				}
				setState(966);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
			}
			setState(967); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetElementContext extends ParserRuleContext<Token> {
		public ArgActionBlockContext argActionBlock() {
		    return getRuleContext(ArgActionBlockContext.class,0);
		}
		public RangeContext range() {
		    return getRuleContext(RangeContext.class,0);
		}
		public TerminalNode<Token> STRING_LITERAL() { return getToken(GrammarParser.STRING_LITERAL, 0); }
		public TerminalNode<Token> TOKEN_REF() { return getToken(GrammarParser.TOKEN_REF, 0); }
		public SetElementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterSetElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitSetElement(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitSetElement(this);
			else return null;
		}
	}

	@RuleVersion(1)
	public final SetElementContext setElement() throws RecognitionException {
		SetElementContext _localctx = new SetElementContext(_ctx, 124);
		enterRule(_localctx, RULE_setElement);
		try {
			setState(977);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(969); match(TOKEN_REF);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(971); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(973); range();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(975); argActionBlock();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> COLON() { return getToken(GrammarParser.COLON, 0); }
		public TerminalNode<Token> RPAREN() { return getToken(GrammarParser.RPAREN, 0); }
		public OptionsSpecContext optionsSpec() {
		    return getRuleContext(OptionsSpecContext.class,0);
		}
		public AltListContext altList() {
		    return getRuleContext(AltListContext.class,0);
		}
		public TerminalNode<Token> LPAREN() { return getToken(GrammarParser.LPAREN, 0); }
		public RuleActionContext ruleAction() {
		    return getRuleContext(RuleActionContext.class,0);
		}
		public BlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitBlock(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitBlock(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, 126);
		enterRule(_localctx, RULE_block);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(979); match(LPAREN);
			setState(994);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
				case 1:
					{
					setState(983);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
						case 1:
							{
							setState(981); optionsSpec();
							}
							break;
					}
					setState(989);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,93,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(985); ruleAction();
							}
							} 
						}
						setState(991);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,93,_ctx);
					}
					setState(992); match(COLON);
					}
					break;
			}
			setState(996); altList();
			setState(998); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RulerefContext extends ParserRuleContext<Token> {
		public ArgActionBlockContext argActionBlock() {
		    return getRuleContext(ArgActionBlockContext.class,0);
		}
		public TerminalNode<Token> RULE_REF() { return getToken(GrammarParser.RULE_REF, 0); }
		public RulerefContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterRuleref(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitRuleref(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitRuleref(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RulerefContext ruleref() throws RecognitionException {
		RulerefContext _localctx = new RulerefContext(_ctx, 128);
		enterRule(_localctx, RULE_ruleref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1000); match(RULE_REF);
			setState(1004);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
				case 1:
					{
					setState(1002); argActionBlock();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RangeContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> RANGE() { return getToken(GrammarParser.RANGE, 0); }
		public List<? extends TerminalNode<Token>> STRING_LITERAL() { return getTokens(GrammarParser.STRING_LITERAL); }
		public TerminalNode<Token> STRING_LITERAL(int i) {
		    return getToken(GrammarParser.STRING_LITERAL, i);
		}
		public RangeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitRange(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitRange(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, 130);
		enterRule(_localctx, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1006); match(STRING_LITERAL);
			setState(1008); match(RANGE);
			setState(1010); match(STRING_LITERAL);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TerminalContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> STRING_LITERAL() { return getToken(GrammarParser.STRING_LITERAL, 0); }
		public ElementOptionsContext elementOptions() {
		    return getRuleContext(ElementOptionsContext.class,0);
		}
		public TerminalNode<Token> TOKEN_REF() { return getToken(GrammarParser.TOKEN_REF, 0); }
		public TerminalContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterTerminal(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitTerminal(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitTerminal(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TerminalContext terminal() throws RecognitionException {
		TerminalContext _localctx = new TerminalContext(_ctx, 132);
		enterRule(_localctx, RULE_terminal);
		try {
			setState(1024);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1012); match(TOKEN_REF);
					setState(1016);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
						case 1:
							{
							setState(1014); elementOptions();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1018); match(STRING_LITERAL);
					setState(1022);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
						case 1:
							{
							setState(1020); elementOptions();
							}
							break;
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementOptionsContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> GT() { return getToken(GrammarParser.GT, 0); }
		public TerminalNode<Token> LT() { return getToken(GrammarParser.LT, 0); }
		public List<? extends ElementOptionContext> elementOption() {
		    return getRuleContexts(ElementOptionContext.class);
		}
		public TerminalNode<Token> COMMA() { return getToken(GrammarParser.COMMA, 0); }
		public ElementOptionContext elementOption(int i) {
		    return getRuleContext(ElementOptionContext.class,i);
		}
		public ElementOptionsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterElementOptions(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitElementOptions(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitElementOptions(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ElementOptionsContext elementOptions() throws RecognitionException {
		ElementOptionsContext _localctx = new ElementOptionsContext(_ctx, 134);
		enterRule(_localctx, RULE_elementOptions);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1026); match(LT);
			setState(1028); elementOption();
			setState(1036);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(1030); match(COMMA);
					setState(1032); elementOption();
					}
					} 
				}
				setState(1038);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
			}
			setState(1039); match(GT);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementOptionContext extends ParserRuleContext<Token> {
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public TerminalNode<Token> STRING_LITERAL() { return getToken(GrammarParser.STRING_LITERAL, 0); }
		public QidContext qid() {
		    return getRuleContext(QidContext.class,0);
		}
		public TerminalNode<Token> ASSIGN() { return getToken(GrammarParser.ASSIGN, 0); }
		public ElementOptionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterElementOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitElementOption(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitElementOption(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ElementOptionContext elementOption() throws RecognitionException {
		ElementOptionContext _localctx = new ElementOptionContext(_ctx, 136);
		enterRule(_localctx, RULE_elementOption);
		try {
			setState(1053);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1041); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1043); id();
					setState(1045); match(ASSIGN);
					setState(1051);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
						case 1:
							{
							setState(1047); qid();
							}
							break;

						case 2:
							{
							setState(1049); match(STRING_LITERAL);
							}
							break;
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> TEMPLATE() { return getToken(GrammarParser.TEMPLATE, 0); }
		public TerminalNode<Token> RULE_REF() { return getToken(GrammarParser.RULE_REF, 0); }
		public TerminalNode<Token> TOKEN_REF() { return getToken(GrammarParser.TOKEN_REF, 0); }
		public IdContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitId(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitId(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, 138);
		enterRule(_localctx, RULE_id);
		try {
			setState(1061);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1055); match(RULE_REF);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1057); match(TOKEN_REF);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1059); match(TEMPLATE);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QidContext extends ParserRuleContext<Token> {
		public List<? extends IdContext> id() {
		    return getRuleContexts(IdContext.class);
		}
		public TerminalNode<Token> DOT() { return getToken(GrammarParser.DOT, 0); }
		public IdContext id(int i) {
		    return getRuleContext(IdContext.class,i);
		}
		public QidContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterQid(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitQid(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitQid(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final QidContext qid() throws RecognitionException {
		QidContext _localctx = new QidContext(_ctx, 140);
		enterRule(_localctx, RULE_qid);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1063); id();
			setState(1071);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,103,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(1065); match(DOT);
					setState(1067); id();
					}
					} 
				}
				setState(1073);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,103,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\1u\u0433\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7"+
		"\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16\7\16\2\17"+
		"\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25\7\25\2\26"+
		"\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34\7\34\2\35"+
		"\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7%\2&\7&"+
		"\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2.\7.\2/\7/\2\60\7\60\2\61"+
		"\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65\2\66\7\66\2\67\7\67\28\7"+
		"8\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2@\7@\2A\7A\2B\7B\2C\7C\2"+
		"D\7D\2E\7E\2F\7F\1\0\0\3\0\b\0\1\0\0\1\0\0\1\0\0\1\0\0\5\0\b\0\n\0\f\0"+
		"\u009e\t\0\1\0\0\1\0\0\5\0\b\0\n\0\f\0\u00a7\t\0\1\0\1\0\1\1\0\1\1\0\1"+
		"\1\0\1\1\0\1\1\0\3\1\b\1\1\2\0\1\2\0\1\2\0\1\2\0\3\2\b\2\1\3\0\1\3\0\1"+
		"\3\1\3\5\3\b\3\n\3\f\3\u00ca\t\3\1\3\1\3\1\4\0\1\4\0\1\4\1\4\1\5\0\1\5"+
		"\0\1\5\0\1\5\0\3\5\b\5\1\6\0\1\6\0\1\6\0\1\6\0\5\6\b\6\n\6\f\6\u00e9\t"+
		"\6\1\6\1\6\1\7\0\1\7\0\1\7\1\7\1\7\0\3\7\b\7\1\b\0\1\b\0\4\b\b\b\13\b"+
		"\f\b\u00fc\1\b\1\b\1\t\0\1\t\0\1\t\0\1\t\0\3\t\b\t\1\t\1\t\1\t\0\3\t\b"+
		"\t\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1"+
		"\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0"+
		"\1\n\0\5\n\b\n\n\n\f\n\u0144\t\n\1\n\1\n\1\13\0\1\13\0\5\13\b\13\n\13"+
		"\f\13\u014f\t\13\1\13\0\1\13\0\5\13\b\13\n\13\f\13\u0158\t\13\1\13\1\13"+
		"\1\f\0\1\f\0\5\f\b\f\n\f\f\f\u0163\t\f\1\f\0\1\f\0\5\f\b\f\n\f\f\f\u016c"+
		"\t\f\1\f\0\1\f\0\5\f\b\f\n\f\f\f\u0175\t\f\3\f\b\f\1\f\0\1\f\0\5\f\b\f"+
		"\n\f\f\f\u0180\t\f\1\f\0\1\f\0\5\f\b\f\n\f\f\f\u0189\t\f\3\f\b\f\1\f\0"+
		"\1\f\0\5\f\b\f\n\f\f\f\u0194\t\f\1\f\1\f\1\r\0\1\r\0\5\r\b\r\n\r\f\r\u019f"+
		"\t\r\1\r\1\r\1\16\0\1\16\0\5\16\b\16\n\16\f\16\u01aa\t\16\1\16\0\1\16"+
		"\0\5\16\b\16\n\16\f\16\u01b3\t\16\1\16\0\1\16\0\5\16\b\16\n\16\f\16\u01bc"+
		"\t\16\1\16\0\1\16\0\5\16\b\16\n\16\f\16\u01c5\t\16\5\16\b\16\n\16\f\16"+
		"\u01ca\t\16\3\16\b\16\1\16\1\16\1\17\0\3\17\b\17\1\17\0\5\17\b\17\n\17"+
		"\f\17\u01d9\t\17\1\17\1\17\1\20\0\1\20\0\5\20\b\20\n\20\f\20\u01e4\t\20"+
		"\1\20\0\5\20\b\20\n\20\f\20\u01eb\t\20\1\21\0\1\21\0\1\21\0\3\21\b\21"+
		"\1\21\0\1\21\0\1\21\0\3\21\b\21\1\21\0\3\21\b\21\1\22\0\1\22\0\1\22\0"+
		"\1\22\0\1\22\0\3\22\b\22\1\23\0\1\23\0\1\23\1\23\3\23\b\23\1\23\0\1\23"+
		"\1\23\1\24\0\1\24\0\1\24\0\3\24\b\24\1\25\0\1\25\0\1\25\0\1\25\0\4\25"+
		"\b\25\13\25\f\25\u022a\1\26\0\5\26\b\26\n\26\f\26\u0232\t\26\1\27\0\1"+
		"\27\0\3\27\b\27\1\30\0\3\30\b\30\1\30\0\3\30\b\30\1\30\0\1\30\0\3\30\b"+
		"\30\1\30\0\3\30\b\30\1\30\0\3\30\b\30\1\30\0\3\30\b\30\1\30\0\1\30\0\1"+
		"\30\0\1\30\0\1\30\1\30\1\31\0\5\31\b\31\n\31\f\31\u0263\t\31\1\31\0\3"+
		"\31\b\31\1\32\0\1\32\0\1\32\1\32\1\33\0\1\33\1\33\1\34\0\5\34\b\34\n\34"+
		"\f\34\u0278\t\34\1\35\0\1\35\0\3\35\b\35\1\36\0\1\36\1\36\1\37\0\1\37"+
		"\0\1\37\0\1\37\0\5\37\b\37\n\37\f\37\u028f\t\37\1 \0\1 \1 \1!\0\1!\0\1"+
		"!\1!\1\"\0\4\"\b\"\13\"\f\"\u029e\1#\0\1#\0\1#\0\1#\0\3#\b#\1$\1$\1%\0"+
		"\1%\0\1%\0\5%\b%\n%\f%\u02b6\t%\1&\0\1&\0\1&\0\3&\b&\1\'\0\3\'\b\'\1\'"+
		"\0\3\'\b\'\1\'\0\1\'\0\1\'\0\1\'\1\'\1(\1(\1)\0\1)\0\1)\0\5)\b)\n)\f)"+
		"\u02db\t)\1*\0\1*\0\1*\0\3*\b*\1+\0\4+\b+\13+\f+\u02e8\1,\0\1,\0\1,\0"+
		"\3,\b,\1,\0\1,\0\1,\0\3,\b,\1,\0\1,\0\1,\0\3,\b,\1,\0\1,\0\3,\b,\3,\b"+
		",\1-\0\1-\0\1-\0\3-\b-\1-\0\1-\0\3-\b-\1.\0\1.\0\1.\1.\1/\0\1/\0\1/\0"+
		"\1/\0\5/\b/\n/\f/\u032a\t/\1\60\0\1\60\0\1\60\0\1\60\1\60\1\60\0\3\60"+
		"\b\60\1\61\0\1\61\0\3\61\b\61\1\62\0\1\62\0\1\62\0\5\62\b\62\n\62\f\62"+
		"\u0347\t\62\1\63\0\1\63\0\3\63\b\63\1\64\0\4\64\b\64\13\64\f\64\u0352"+
		"\1\65\0\1\65\0\1\65\0\3\65\b\65\1\65\0\1\65\0\1\65\0\3\65\b\65\1\65\0"+
		"\1\65\0\1\65\0\3\65\b\65\3\65\b\65\1\66\0\1\66\0\1\66\0\3\66\b\66\1\66"+
		"\0\1\66\0\3\66\b\66\1\67\0\1\67\0\1\67\0\3\67\b\67\18\18\19\0\19\0\19"+
		"\0\39\b9\1:\0\1:\0\1:\0\1:\0\1:\0\1:\0\1:\0\3:\b:\3:\b:\1;\0\1;\0\1;\0"+
		"\1;\0\1;\0\1;\0\3;\b;\3;\b;\1<\0\1<\0\1<\0\1<\0\3<\b<\1=\0\1=\0\1=\0\1"+
		"=\0\5=\b=\n=\f=\u03c6\t=\1=\1=\1>\0\1>\0\1>\0\1>\0\3>\b>\1?\0\1?\0\3?"+
		"\b?\1?\0\5?\b?\n?\f?\u03df\t?\1?\0\3?\b?\1?\0\1?\1?\1@\0\1@\0\3@\b@\1"+
		"A\0\1A\0\1A\1A\1B\0\1B\0\3B\bB\1B\0\1B\0\3B\bB\3B\bB\1C\0\1C\0\1C\0\1"+
		"C\0\5C\bC\nC\fC\u040e\tC\1C\1C\1D\0\1D\0\1D\0\1D\0\1D\0\3D\bD\3D\bD\1"+
		"E\0\1E\0\1E\0\3E\bE\1F\0\1F\0\1F\0\5F\bF\nF\fF\u0431\tF\1FG\0\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVX"+
		"Z\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\0\1\19"+
		"D\u0407\0\u0090\1\0\0\0\1\u0432\5\uffff\0\0\2\u00b4\1\0\0\0\3\u0094\1"+
		"\0\0\0\4\u00be\1\0\0\0\5\u009b\1\0\0\0\6\u00c0\1\0\0\0\7\u00bf\1\0\0\0"+
		"\7\u027e\1\0\0\0\7\u03d8\1\0\0\0\b\u00cd\1\0\0\0\t\u00c4\1\0\0\0\n\u00db"+
		"\1\0\0\0\13\u00d2\1\0\0\0\f\u00dd\1\0\0\0\r\u00bf\1\0\0\0\16\u00f4\1\0"+
		"\0\0\17\u00e7\1\0\0\0\17\u00e6\1\0\0\0\20\u00f6\1\0\0\0\21\u00bf\1\0\0"+
		"\0\22\u010e\1\0\0\0\23\u00fb\1\0\0\0\24\u0110\1\0\0\0\25\u0141\1\0\0\0"+
		"\25\u0217\1\0\0\0\25\u026d\1\0\0\0\25\u0271\1\0\0\0\25\u0299\1\0\0\0\25"+
		"\u0306\1\0\0\0\25\u036a\1\0\0\0\26\u0147\1\0\0\0\27\u0141\1\0\0\0\30\u015b"+
		"\1\0\0\0\31\u0141\1\0\0\0\32\u0197\1\0\0\0\33\u026c\1\0\0\0\33\u039f\1"+
		"\0\0\0\33\u03d2\1\0\0\0\33\u03ed\1\0\0\0\34\u01a2\1\0\0\0\35\u0246\1\0"+
		"\0\0\35\u0282\1\0\0\0\35\u0293\1\0\0\0\36\u01d1\1\0\0\0\37\u01b1\1\0\0"+
		"\0\37\u01c3\1\0\0\0 \u01dc\1\0\0\0!\u01d2\1\0\0\0!\u01f3\1\0\0\0!\u01fb"+
		"\1\0\0\0\"\u01fe\1\0\0\0#\u01e9\1\0\0\0#\u01e8\1\0\0\0$\u020a\1\0\0\0"+
		"%\u014c\1\0\0\0%\u0155\1\0\0\0%\u0160\1\0\0\0%\u0169\1\0\0\0%\u0172\1"+
		"\0\0\0%\u017d\1\0\0\0%\u0186\1\0\0\0%\u0191\1\0\0\0%\u01a7\1\0\0\0%\u01b0"+
		"\1\0\0\0%\u01b9\1\0\0\0%\u01c2\1\0\0\0%\u01d6\1\0\0\0%\u01e1\1\0\0\0&"+
		"\u020c\1\0\0\0\'\u00bf\1\0\0\0(\u021e\1\0\0\0)\u0210\1\0\0\0*\u0220\1"+
		"\0\0\0+\u00a4\1\0\0\0,\u0230\1\0\0\0-\u00a5\1\0\0\0.\u0237\1\0\0\0/\u0229"+
		"\1\0\0\0/\u022f\1\0\0\0\60\u023b\1\0\0\0\61\u0238\1\0\0\0\62\u0261\1\0"+
		"\0\0\63\u025c\1\0\0\0\64\u0268\1\0\0\0\65\u0260\1\0\0\0\66\u026e\1\0\0"+
		"\0\67\u0267\1\0\0\08\u0276\1\0\0\09\u0255\1\0\0\0:\u027d\1\0\0\0;\u0275"+
		"\1\0\0\0<\u027f\1\0\0\0=\u024a\1\0\0\0>\u0283\1\0\0\0?\u024e\1\0\0\0@"+
		"\u0290\1\0\0\0A\u0252\1\0\0\0B\u0294\1\0\0\0C\u027e\1\0\0\0C\u03dc\1\0"+
		"\0\0D\u029c\1\0\0\0E\u0240\1\0\0\0F\u02a8\1\0\0\0G\u029d\1\0\0\0H\u02aa"+
		"\1\0\0\0I\u0259\1\0\0\0J\u02ac\1\0\0\0K\u02ab\1\0\0\0L\u02b7\1\0\0\0M"+
		"\u02b4\1\0\0\0M\u02b3\1\0\0\0N\u02c1\1\0\0\0O\u0238\1\0\0\0P\u02cf\1\0"+
		"\0\0Q\u02cd\1\0\0\0R\u02d1\1\0\0\0S\u02d0\1\0\0\0S\u031c\1\0\0\0T\u02dc"+
		"\1\0\0\0U\u02d9\1\0\0\0U\u02d8\1\0\0\0V\u02e6\1\0\0\0W\u02e2\1\0\0\0X"+
		"\u0308\1\0\0\0Y\u02e7\1\0\0\0Z\u030a\1\0\0\0[\u02f0\1\0\0\0\\\u0318\1"+
		"\0\0\0]\u0300\1\0\0\0^\u031e\1\0\0\0_\u02e3\1\0\0\0`\u0335\1\0\0\0a\u0328"+
		"\1\0\0\0a\u0327\1\0\0\0b\u033b\1\0\0\0c\u0331\1\0\0\0d\u033d\1\0\0\0e"+
		"\u03e6\1\0\0\0f\u034c\1\0\0\0g\u02bd\1\0\0\0g\u0345\1\0\0\0g\u0344\1\0"+
		"\0\0h\u0350\1\0\0\0i\u034d\1\0\0\0j\u036c\1\0\0\0k\u0351\1\0\0\0l\u036e"+
		"\1\0\0\0m\u035a\1\0\0\0n\u037c\1\0\0\0o\u036d\1\0\0\0p\u0384\1\0\0\0q"+
		"\u0383\1\0\0\0r\u038c\1\0\0\0s\u02f1\1\0\0\0s\u02f9\1\0\0\0s\u0301\1\0"+
		"\0\0s\u035b\1\0\0\0s\u0363\1\0\0\0s\u0385\1\0\0\0t\u039e\1\0\0\0u\u02f8"+
		"\1\0\0\0u\u0317\1\0\0\0v\u03ae\1\0\0\0w\u0362\1\0\0\0w\u037b\1\0\0\0x"+
		"\u03b8\1\0\0\0y\u039f\1\0\0\0y\u03af\1\0\0\0z\u03ba\1\0\0\0{\u03b9\1\0"+
		"\0\0|\u03d1\1\0\0\0}\u03b9\1\0\0\0}\u03c4\1\0\0\0}\u03c3\1\0\0\0~\u03d3"+
		"\1\0\0\0\177\u0317\1\0\0\0\177\u037b\1\0\0\0\177\u0382\1\0\0\0\u0080\u03e8"+
		"\1\0\0\0\u0081\u03af\1\0\0\0\u0082\u03ee\1\0\0\0\u0083\u039f\1\0\0\0\u0083"+
		"\u03af\1\0\0\0\u0083\u03d2\1\0\0\0\u0084\u0400\1\0\0\0\u0085\u039f\1\0"+
		"\0\0\u0085\u03af\1\0\0\0\u0086\u0402\1\0\0\0\u0087\u039d\1\0\0\0\u0087"+
		"\u03ad\1\0\0\0\u0087\u03f9\1\0\0\0\u0087\u03ff\1\0\0\0\u0088\u041d\1\0"+
		"\0\0\u0089\u040c\1\0\0\0\u0089\u040b\1\0\0\0\u008a\u0425\1\0\0\0\u008b"+
		"\u0096\1\0\0\0\u008b\u00cf\1\0\0\0\u008b\u00ee\1\0\0\0\u008b\u00f1\1\0"+
		"\0\0\u008b\u00f5\1\0\0\0\u008b\u0108\1\0\0\0\u008b\u0216\1\0\0\0\u008b"+
		"\u021f\1\0\0\0\u008b\u0224\1\0\0\0\u008b\u0298\1\0\0\0\u008b\u02be\1\0"+
		"\0\0\u008b\u0310\1\0\0\0\u008b\u032d\1\0\0\0\u008b\u0336\1\0\0\0\u008b"+
		"\u033c\1\0\0\0\u008b\u0374\1\0\0\0\u008b\u0415\1\0\0\0\u008b\u042f\1\0"+
		"\0\0\u008b\u042e\1\0\0\0\u008c\u0427\1\0\0\0\u008d\u00dc\1\0\0\0\u008d"+
		"\u028d\1\0\0\0\u008d\u028c\1\0\0\0\u008d\u041e\1\0\0\0\u008d\u041c\1\0"+
		"\0\0\u008e\u0091\5\5\0\0\u0090\u008e\1\0\0\0\u0090\u0091\1\0\0\0\u0091"+
		"\u0092\1\0\0\0\u0092\u0094\3\2\1\0\u0094\u0096\3\u008aE\0\u0096\u009c"+
		"\5 \0\0\u0098\u009b\3\4\2\0\u009a\u0098\1\0\0\0\u009b\u009e\1\0\0\0\u009c"+
		"\u009a\1\0\0\0\u009c\u009d\1\0\0\0\u009d\u009f\1\0\0\0\u009e\u009c\1\0"+
		"\0\0\u009f\u00a5\3,\26\0\u00a1\u00a4\3*\25\0\u00a3\u00a1\1\0\0\0\u00a4"+
		"\u00a7\1\0\0\0\u00a5\u00a3\1\0\0\0\u00a5\u00a6\1\0\0\0\u00a6\u00a8\1\0"+
		"\0\0\u00a7\u00a5\1\0\0\0\u00a8\u00a9\5\uffff\0\0\u00a9\1\1\0\0\0\u00aa"+
		"\u00ac\5\20\0\0\u00ac\u00b5\5\22\0\0\u00ae\u00b0\5\21\0\0\u00b0\u00b5"+
		"\5\22\0\0\u00b2\u00b5\5\22\0\0\u00b4\u00aa\1\0\0\0\u00b4\u00ae\1\0\0\0"+
		"\u00b4\u00b2\1\0\0\0\u00b5\3\1\0\0\0\u00b6\u00bf\3\6\3\0\u00b8\u00bf\3"+
		"\f\6\0\u00ba\u00bf\3\20\b\0\u00bc\u00bf\3&\23\0\u00be\u00b6\1\0\0\0\u00be"+
		"\u00b8\1\0\0\0\u00be\u00ba\1\0\0\0\u00be\u00bc\1\0\0\0\u00bf\5\1\0\0\0"+
		"\u00c0\u00c8\5\f\0\0\u00c2\u00c4\3\b\4\0\u00c4\u00c5\5 \0\0\u00c5\u00c7"+
		"\1\0\0\0\u00c6\u00c2\1\0\0\0\u00c7\u00ca\1\0\0\0\u00c8\u00c6\1\0\0\0\u00c8"+
		"\u00c9\1\0\0\0\u00c9\u00cb\1\0\0\0\u00ca\u00c8\1\0\0\0\u00cb\u00cc\5\63"+
		"\0\0\u00cc\7\1\0\0\0\u00cd\u00cf\3\u008aE\0\u00cf\u00d1\5&\0\0\u00d1\u00d2"+
		"\3\n\5\0\u00d2\t\1\0\0\0\u00d3\u00dc\3\u008cF\0\u00d5\u00dc\5\66\0\0\u00d7"+
		"\u00dc\5\65\0\0\u00d9\u00dc\5(\0\0\u00db\u00d3\1\0\0\0\u00db\u00d5\1\0"+
		"\0\0\u00db\u00d7\1\0\0\0\u00db\u00d9\1\0\0\0\u00dc\13\1\0\0\0\u00dd\u00df"+
		"\5\16\0\0\u00df\u00e7\3\16\7\0\u00e1\u00e3\5\37\0\0\u00e3\u00e6\3\16\7"+
		"\0\u00e5\u00e1\1\0\0\0\u00e6\u00e9\1\0\0\0\u00e7\u00e5\1\0\0\0\u00e7\u00e8"+
		"\1\0\0\0\u00e8\u00ea\1\0\0\0\u00e9\u00e7\1\0\0\0\u00ea\u00eb\5 \0\0\u00eb"+
		"\r\1\0\0\0\u00ec\u00ee\3\u008aE\0\u00ee\u00f0\5&\0\0\u00f0\u00f1\3\u008a"+
		"E\0\u00f1\u00f5\1\0\0\0\u00f2\u00f5\3\u008aE\0\u00f4\u00ec\1\0\0\0\u00f4"+
		"\u00f2\1\0\0\0\u00f5\17\1\0\0\0\u00f6\u00fa\5\r\0\0\u00f8\u00fb\3\22\t"+
		"\0\u00fa\u00f8\1\0\0\0\u00fb\u00fc\1\0\0\0\u00fc\u00fa\1\0\0\0\u00fc\u00fd"+
		"\1\0\0\0\u00fd\u00fe\1\0\0\0\u00fe\u00ff\5\63\0\0\u00ff\21\1\0\0\0\u0100"+
		"\u0108\3\u008aE\0\u0102\u0104\5&\0\0\u0104\u0109\5\66\0\0\u0106\u0109"+
		"\1\0\0\0\u0108\u0102\1\0\0\0\u0108\u0106\1\0\0\0\u0109\u010a\1\0\0\0\u010a"+
		"\u010b\5 \0\0\u010b\u010f\1\0\0\0\u010c\u010f\5\4\0\0\u010e\u0100\1\0"+
		"\0\0\u010e\u010c\1\0\0\0\u010f\23\1\0\0\0\u0110\u0142\5\13\0\0\u0112\u0141"+
		"\3\24\n\0\u0114\u0141\3\26\13\0\u0116\u0141\3\30\f\0\u0118\u0141\5Y\0"+
		"\0\u011a\u0141\5Z\0\0\u011c\u0141\5V\0\0\u011e\u0141\5W\0\0\u0120\u0141"+
		"\5X\0\0\u0122\u0141\5H\0\0\u0124\u0141\5I\0\0\u0126\u0141\5J\0\0\u0128"+
		"\u0141\5K\0\0\u012a\u0141\5L\0\0\u012c\u0141\5M\0\0\u012e\u0141\5N\0\0"+
		"\u0130\u0141\5O\0\0\u0132\u0141\5S\0\0\u0134\u0141\5T\0\0\u0136\u0141"+
		"\5U\0\0\u0138\u0141\5Q\0\0\u013a\u0141\5P\0\0\u013c\u0141\5R\0\0\u013e"+
		"\u0141\5G\0\0\u0140\u0112\1\0\0\0\u0140\u0114\1\0\0\0\u0140\u0116\1\0"+
		"\0\0\u0140\u0118\1\0\0\0\u0140\u011a\1\0\0\0\u0140\u011c\1\0\0\0\u0140"+
		"\u011e\1\0\0\0\u0140\u0120\1\0\0\0\u0140\u0122\1\0\0\0\u0140\u0124\1\0"+
		"\0\0\u0140\u0126\1\0\0\0\u0140\u0128\1\0\0\0\u0140\u012a\1\0\0\0\u0140"+
		"\u012c\1\0\0\0\u0140\u012e\1\0\0\0\u0140\u0130\1\0\0\0\u0140\u0132\1\0"+
		"\0\0\u0140\u0134\1\0\0\0\u0140\u0136\1\0\0\0\u0140\u0138\1\0\0\0\u0140"+
		"\u013a\1\0\0\0\u0140\u013c\1\0\0\0\u0140\u013e\1\0\0\0\u0141\u0144\1\0"+
		"\0\0\u0142\u0140\1\0\0\0\u0142\u0143\1\0\0\0\u0143\u0145\1\0\0\0\u0144"+
		"\u0142\1\0\0\0\u0145\u0146\5[\0\0\u0146\25\1\0\0\0\u0147\u014d\5U\0\0"+
		"\u0149\u014c\3$\22\0\u014b\u0149\1\0\0\0\u014c\u014f\1\0\0\0\u014d\u014b"+
		"\1\0\0\0\u014d\u014e\1\0\0\0\u014e\u0150\1\0\0\0\u014f\u014d\1\0\0\0\u0150"+
		"\u0156\5G\0\0\u0152\u0155\3$\22\0\u0154\u0152\1\0\0\0\u0155\u0158\1\0"+
		"\0\0\u0156\u0154\1\0\0\0\u0156\u0157\1\0\0\0\u0157\u0159\1\0\0\0\u0158"+
		"\u0156\1\0\0\0\u0159\u015a\5T\0\0\u015a\27\1\0\0\0\u015b\u0161\5U\0\0"+
		"\u015d\u0160\3$\22\0\u015f\u015d\1\0\0\0\u0160\u0163\1\0\0\0\u0161\u015f"+
		"\1\0\0\0\u0161\u0162\1\0\0\0\u0162\u018a\1\0\0\0\u0163\u0161\1\0\0\0\u0164"+
		"\u016a\5L\0\0\u0166\u0169\3$\22\0\u0168\u0166\1\0\0\0\u0169\u016c\1\0"+
		"\0\0\u016a\u0168\1\0\0\0\u016a\u016b\1\0\0\0\u016b\u0176\1\0\0\0\u016c"+
		"\u016a\1\0\0\0\u016d\u0173\5R\0\0\u016f\u0172\3$\22\0\u0171\u016f\1\0"+
		"\0\0\u0172\u0175\1\0\0\0\u0173\u0171\1\0\0\0\u0173\u0174\1\0\0\0\u0174"+
		"\u0177\1\0\0\0\u0175\u0173\1\0\0\0\u0176\u016d\1\0\0\0\u0176\u0177\1\0"+
		"\0\0\u0177\u0178\1\0\0\0\u0178\u017e\5T\0\0\u017a\u017d\3$\22\0\u017c"+
		"\u017a\1\0\0\0\u017d\u0180\1\0\0\0\u017e\u017c\1\0\0\0\u017e\u017f\1\0"+
		"\0\0\u017f\u0181\1\0\0\0\u0180\u017e\1\0\0\0\u0181\u0187\5M\0\0\u0183"+
		"\u0186\3$\22\0\u0185\u0183\1\0\0\0\u0186\u0189\1\0\0\0\u0187\u0185\1\0"+
		"\0\0\u0187\u0188\1\0\0\0\u0188\u018b\1\0\0\0\u0189\u0187\1\0\0\0\u018a"+
		"\u0164\1\0\0\0\u018a\u018b\1\0\0\0\u018b\u018c\1\0\0\0\u018c\u0192\5P"+
		"\0\0\u018e\u0191\3$\22\0\u0190\u018e\1\0\0\0\u0191\u0194\1\0\0\0\u0192"+
		"\u0190\1\0\0\0\u0192\u0193\1\0\0\0\u0193\u0195\1\0\0\0\u0194\u0192\1\0"+
		"\0\0\u0195\u0196\5T\0\0\u0196\31\1\0\0\0\u0197\u019d\5\n\0\0\u0199\u019c"+
		"\7\0\0\0\u019b\u0199\1\0\0\0\u019c\u019f\1\0\0\0\u019d\u019b\1\0\0\0\u019d"+
		"\u019e\1\0\0\0\u019e\u01a0\1\0\0\0\u019f\u019d\1\0\0\0\u01a0\u01a1\5E"+
		"\0\0\u01a1\33\1\0\0\0\u01a2\u01a8\5\n\0\0\u01a4\u01a7\3$\22\0\u01a6\u01a4"+
		"\1\0\0\0\u01a7\u01aa\1\0\0\0\u01a8\u01a6\1\0\0\0\u01a8\u01a9\1\0\0\0\u01a9"+
		"\u01cb\1\0\0\0\u01aa\u01a8\1\0\0\0\u01ab\u01b1\3\36\17\0\u01ad\u01b0\3"+
		"$\22\0\u01af\u01ad\1\0\0\0\u01b0\u01b3\1\0\0\0\u01b1\u01af\1\0\0\0\u01b1"+
		"\u01b2\1\0\0\0\u01b2\u01c8\1\0\0\0\u01b3\u01b1\1\0\0\0\u01b4\u01ba\5>"+
		"\0\0\u01b6\u01b9\3$\22\0\u01b8\u01b6\1\0\0\0\u01b9\u01bc\1\0\0\0\u01ba"+
		"\u01b8\1\0\0\0\u01ba\u01bb\1\0\0\0\u01bb\u01bd\1\0\0\0\u01bc\u01ba\1\0"+
		"\0\0\u01bd\u01c3\3\36\17\0\u01bf\u01c2\3$\22\0\u01c1\u01bf\1\0\0\0\u01c2"+
		"\u01c5\1\0\0\0\u01c3\u01c1\1\0\0\0\u01c3\u01c4\1\0\0\0\u01c4\u01c7\1\0"+
		"\0\0\u01c5\u01c3\1\0\0\0\u01c6\u01b4\1\0\0\0\u01c7\u01ca\1\0\0\0\u01c8"+
		"\u01c6\1\0\0\0\u01c8\u01c9\1\0\0\0\u01c9\u01cc\1\0\0\0\u01ca\u01c8\1\0"+
		"\0\0\u01cb\u01ab\1\0\0\0\u01cb\u01cc\1\0\0\0\u01cc\u01cd\1\0\0\0\u01cd"+
		"\u01ce\5E\0\0\u01ce\35\1\0\0\0\u01cf\u01d2\3 \20\0\u01d1\u01cf\1\0\0\0"+
		"\u01d1\u01d2\1\0\0\0\u01d2\u01d7\1\0\0\0\u01d3\u01d6\3$\22\0\u01d5\u01d3"+
		"\1\0\0\0\u01d6\u01d9\1\0\0\0\u01d7\u01d5\1\0\0\0\u01d7\u01d8\1\0\0\0\u01d8"+
		"\u01da\1\0\0\0\u01d9\u01d7\1\0\0\0\u01da\u01db\5@\0\0\u01db\37\1\0\0\0"+
		"\u01dc\u01e9\3\"\21\0\u01de\u01e1\3$\22\0\u01e0\u01de\1\0\0\0\u01e1\u01e4"+
		"\1\0\0\0\u01e2\u01e0\1\0\0\0\u01e2\u01e3\1\0\0\0\u01e3\u01e5\1\0\0\0\u01e4"+
		"\u01e2\1\0\0\0\u01e5\u01e8\3\"\21\0\u01e7\u01e2\1\0\0\0\u01e8\u01eb\1"+
		"\0\0\0\u01e9\u01e7\1\0\0\0\u01e9\u01ea\1\0\0\0\u01ea!\1\0\0\0\u01eb\u01e9"+
		"\1\0\0\0\u01ec\u01ff\5@\0\0\u01ee\u01f2\59\0\0\u01f0\u01f3\3 \20\0\u01f2"+
		"\u01f0\1\0\0\0\u01f2\u01f3\1\0\0\0\u01f3\u01f4\1\0\0\0\u01f4\u01ff\5:"+
		"\0\0\u01f6\u01fa\5;\0\0\u01f8\u01fb\3 \20\0\u01fa\u01f8\1\0\0\0\u01fa"+
		"\u01fb\1\0\0\0\u01fb\u01fc\1\0\0\0\u01fc\u01ff\5<\0\0\u01fe\u01ec\1\0"+
		"\0\0\u01fe\u01ee\1\0\0\0\u01fe\u01f6\1\0\0\0\u01ff#\1\0\0\0\u0200\u020b"+
		"\5Y\0\0\u0202\u020b\5Z\0\0\u0204\u020b\5V\0\0\u0206\u020b\5C\0\0\u0208"+
		"\u020b\5D\0\0\u020a\u0200\1\0\0\0\u020a\u0202\1\0\0\0\u020a\u0204\1\0"+
		"\0\0\u020a\u0206\1\0\0\0\u020a\u0208\1\0\0\0\u020b%\1\0\0\0\u020c\u0212"+
		"\5\60\0\0\u020e\u0210\3(\24\0\u0210\u0211\5\36\0\0\u0211\u0213\1\0\0\0"+
		"\u0212\u020e\1\0\0\0\u0212\u0213\1\0\0\0\u0213\u0214\1\0\0\0\u0214\u0216"+
		"\3\u008aE\0\u0216\u0217\3\24\n\0\u0217\'\1\0\0\0\u0218\u021f\3\u008aE"+
		"\0\u021a\u021f\5\20\0\0\u021c\u021f\5\21\0\0\u021e\u0218\1\0\0\0\u021e"+
		"\u021a\1\0\0\0\u021e\u021c\1\0\0\0\u021f)\1\0\0\0\u0220\u0222\5\34\0\0"+
		"\u0222\u0224\3\u008aE\0\u0224\u0228\5 \0\0\u0226\u0229\3.\27\0\u0228\u0226"+
		"\1\0\0\0\u0229\u022a\1\0\0\0\u022a\u0228\1\0\0\0\u022a\u022b\1\0\0\0\u022b"+
		"+\1\0\0\0\u022c\u022f\3.\27\0\u022e\u022c\1\0\0\0\u022f\u0232\1\0\0\0"+
		"\u0230\u022e\1\0\0\0\u0230\u0231\1\0\0\0\u0231-\1\0\0\0\u0232\u0230\1"+
		"\0\0\0\u0233\u0238\3\60\30\0\u0235\u0238\3N\'\0\u0237\u0233\1\0\0\0\u0237"+
		"\u0235\1\0\0\0\u0238/\1\0\0\0\u0239\u023c\5\5\0\0\u023b\u0239\1\0\0\0"+
		"\u023b\u023c\1\0\0\0\u023c\u023f\1\0\0\0\u023d\u0240\3D\"\0\u023f\u023d"+
		"\1\0\0\0\u023f\u0240\1\0\0\0\u0240\u0241\1\0\0\0\u0241\u0245\5\4\0\0\u0243"+
		"\u0246\3\34\16\0\u0245\u0243\1\0\0\0\u0245\u0246\1\0\0\0\u0246\u0249\1"+
		"\0\0\0\u0247\u024a\3<\36\0\u0249\u0247\1\0\0\0\u0249\u024a\1\0\0\0\u024a"+
		"\u024d\1\0\0\0\u024b\u024e\3>\37\0\u024d\u024b\1\0\0\0\u024d\u024e\1\0"+
		"\0\0\u024e\u0251\1\0\0\0\u024f\u0252\3@ \0\u0251\u024f\1\0\0\0\u0251\u0252"+
		"\1\0\0\0\u0252\u0253\1\0\0\0\u0253\u0255\38\34\0\u0255\u0257\5\35\0\0"+
		"\u0257\u0259\3H$\0\u0259\u025b\5 \0\0\u025b\u025c\3\62\31\0\u025c\61\1"+
		"\0\0\0\u025d\u0260\3\64\32\0\u025f\u025d\1\0\0\0\u0260\u0263\1\0\0\0\u0261"+
		"\u025f\1\0\0\0\u0261\u0262\1\0\0\0\u0262\u0266\1\0\0\0\u0263\u0261\1\0"+
		"\0\0\u0264\u0267\3\66\33\0\u0266\u0264\1\0\0\0\u0266\u0267\1\0\0\0\u0267"+
		"\63\1\0\0\0\u0268\u026a\5\31\0\0\u026a\u026c\3\32\r\0\u026c\u026d\3\24"+
		"\n\0\u026d\65\1\0\0\0\u026e\u0270\5\32\0\0\u0270\u0271\3\24\n\0\u0271"+
		"\67\1\0\0\0\u0272\u0275\3:\35\0\u0274\u0272\1\0\0\0\u0275\u0278\1\0\0"+
		"\0\u0276\u0274\1\0\0\0\u0276\u0277\1\0\0\0\u02779\1\0\0\0\u0278\u0276"+
		"\1\0\0\0\u0279\u027e\3\6\3\0\u027b\u027e\3B!\0\u027d\u0279\1\0\0\0\u027d"+
		"\u027b\1\0\0\0\u027e;\1\0\0\0\u027f\u0281\5\26\0\0\u0281\u0282\3\34\16"+
		"\0\u0282=\1\0\0\0\u0283\u0285\5\30\0\0\u0285\u028d\3\u008cF\0\u0287\u0289"+
		"\5\37\0\0\u0289\u028c\3\u008cF\0\u028b\u0287\1\0\0\0\u028c\u028f\1\0\0"+
		"\0\u028d\u028b\1\0\0\0\u028d\u028e\1\0\0\0\u028e?\1\0\0\0\u028f\u028d"+
		"\1\0\0\0\u0290\u0292\5\27\0\0\u0292\u0293\3\34\16\0\u0293A\1\0\0\0\u0294"+
		"\u0296\5\60\0\0\u0296\u0298\3\u008aE\0\u0298\u0299\3\24\n\0\u0299C\1\0"+
		"\0\0\u029a\u029d\3F#\0\u029c\u029a\1\0\0\0\u029d\u029e\1\0\0\0\u029e\u029c"+
		"\1\0\0\0\u029e\u029f\1\0\0\0\u029fE\1\0\0\0\u02a0\u02a9\5\24\0\0\u02a2"+
		"\u02a9\5\25\0\0\u02a4\u02a9\5\23\0\0\u02a6\u02a9\5\17\0\0\u02a8\u02a0"+
		"\1\0\0\0\u02a8\u02a2\1\0\0\0\u02a8\u02a4\1\0\0\0\u02a8\u02a6\1\0\0\0\u02a9"+
		"G\1\0\0\0\u02aa\u02ab\3J%\0\u02abI\1\0\0\0\u02ac\u02b4\3L&\0\u02ae\u02b0"+
		"\5+\0\0\u02b0\u02b3\3L&\0\u02b2\u02ae\1\0\0\0\u02b3\u02b6\1\0\0\0\u02b4"+
		"\u02b2\1\0\0\0\u02b4\u02b5\1\0\0\0\u02b5K\1\0\0\0\u02b6\u02b4\1\0\0\0"+
		"\u02b7\u02bd\3f\63\0\u02b9\u02bb\5#\0\0\u02bb\u02be\3\u008aE\0\u02bd\u02b9"+
		"\1\0\0\0\u02bd\u02be\1\0\0\0\u02beM\1\0\0\0\u02bf\u02c2\5\5\0\0\u02c1"+
		"\u02bf\1\0\0\0\u02c1\u02c2\1\0\0\0\u02c2\u02c5\1\0\0\0\u02c3\u02c6\5\17"+
		"\0\0\u02c5\u02c3\1\0\0\0\u02c5\u02c6\1\0\0\0\u02c6\u02c7\1\0\0\0\u02c7"+
		"\u02c9\5\3\0\0\u02c9\u02cb\5\35\0\0\u02cb\u02cd\3P(\0\u02cd\u02ce\5 \0"+
		"\0\u02ceO\1\0\0\0\u02cf\u02d0\3R)\0\u02d0Q\1\0\0\0\u02d1\u02d9\3T*\0\u02d3"+
		"\u02d5\5+\0\0\u02d5\u02d8\3T*\0\u02d7\u02d3\1\0\0\0\u02d8\u02db\1\0\0"+
		"\0\u02d9\u02d7\1\0\0\0\u02d9\u02da\1\0\0\0\u02daS\1\0\0\0\u02db\u02d9"+
		"\1\0\0\0\u02dc\u02e2\3V+\0\u02de\u02e3\3^/\0\u02e0\u02e3\1\0\0\0\u02e2"+
		"\u02de\1\0\0\0\u02e2\u02e0\1\0\0\0\u02e3U\1\0\0\0\u02e4\u02e7\3X,\0\u02e6"+
		"\u02e4\1\0\0\0\u02e7\u02e8\1\0\0\0\u02e8\u02e6\1\0\0\0\u02e8\u02e9\1\0"+
		"\0\0\u02e9W\1\0\0\0\u02ea\u02f0\3Z-\0\u02ec\u02f1\3r9\0\u02ee\u02f1\1"+
		"\0\0\0\u02f0\u02ec\1\0\0\0\u02f0\u02ee\1\0\0\0\u02f1\u0309\1\0\0\0\u02f2"+
		"\u02f8\3t:\0\u02f4\u02f9\3r9\0\u02f6\u02f9\1\0\0\0\u02f8\u02f4\1\0\0\0"+
		"\u02f8\u02f6\1\0\0\0\u02f9\u0309\1\0\0\0\u02fa\u0300\3\\.\0\u02fc\u0301"+
		"\3r9\0\u02fe\u0301\1\0\0\0\u0300\u02fc\1\0\0\0\u0300\u02fe\1\0\0\0\u0301"+
		"\u0309\1\0\0\0\u0302\u0306\3\24\n\0\u0304\u0307\5\'\0\0\u0306\u0304\1"+
		"\0\0\0\u0306\u0307\1\0\0\0\u0307\u0309\1\0\0\0\u0308\u02ea\1\0\0\0\u0308"+
		"\u02f2\1\0\0\0\u0308\u02fa\1\0\0\0\u0308\u0302\1\0\0\0\u0309Y\1\0\0\0"+
		"\u030a\u0310\3\u008aE\0\u030c\u0311\5&\0\0\u030e\u0311\5*\0\0\u0310\u030c"+
		"\1\0\0\0\u0310\u030e\1\0\0\0\u0311\u0316\1\0\0\0\u0312\u0317\3t:\0\u0314"+
		"\u0317\3~?\0\u0316\u0312\1\0\0\0\u0316\u0314\1\0\0\0\u0317[\1\0\0\0\u0318"+
		"\u031a\5!\0\0\u031a\u031c\3R)\0\u031c\u031d\5\"\0\0\u031d]\1\0\0\0\u031e"+
		"\u0320\5#\0\0\u0320\u0328\3`\60\0\u0322\u0324\5\37\0\0\u0324\u0327\3`"+
		"\60\0\u0326\u0322\1\0\0\0\u0327\u032a\1\0\0\0\u0328\u0326\1\0\0\0\u0328"+
		"\u0329\1\0\0\0\u0329_\1\0\0\0\u032a\u0328\1\0\0\0\u032b\u032d\3\u008a"+
		"E\0\u032d\u032f\5!\0\0\u032f\u0331\3b\61\0\u0331\u0332\5\"\0\0\u0332\u0336"+
		"\1\0\0\0\u0333\u0336\3\u008aE\0\u0335\u032b\1\0\0\0\u0335\u0333\1\0\0"+
		"\0\u0336a\1\0\0\0\u0337\u033c\3\u008aE\0\u0339\u033c\5\65\0\0\u033b\u0337"+
		"\1\0\0\0\u033b\u0339\1\0\0\0\u033cc\1\0\0\0\u033d\u0345\3f\63\0\u033f"+
		"\u0341\5+\0\0\u0341\u0344\3f\63\0\u0343\u033f\1\0\0\0\u0344\u0347\1\0"+
		"\0\0\u0345\u0343\1\0\0\0\u0345\u0346\1\0\0\0\u0346e\1\0\0\0\u0347\u0345"+
		"\1\0\0\0\u0348\u034d\3h\64\0\u034a\u034d\1\0\0\0\u034c\u0348\1\0\0\0\u034c"+
		"\u034a\1\0\0\0\u034dg\1\0\0\0\u034e\u0351\3j\65\0\u0350\u034e\1\0\0\0"+
		"\u0351\u0352\1\0\0\0\u0352\u0350\1\0\0\0\u0352\u0353\1\0\0\0\u0353i\1"+
		"\0\0\0\u0354\u035a\3l\66\0\u0356\u035b\3r9\0\u0358\u035b\1\0\0\0\u035a"+
		"\u0356\1\0\0\0\u035a\u0358\1\0\0\0\u035b\u036d\1\0\0\0\u035c\u0362\3v"+
		";\0\u035e\u0363\3r9\0\u0360\u0363\1\0\0\0\u0362\u035e\1\0\0\0\u0362\u0360"+
		"\1\0\0\0\u0363\u036d\1\0\0\0\u0364\u036d\3n\67\0\u0366\u036a\3\24\n\0"+
		"\u0368\u036b\5\'\0\0\u036a\u0368\1\0\0\0\u036a\u036b\1\0\0\0\u036b\u036d"+
		"\1\0\0\0\u036c\u0354\1\0\0\0\u036c\u035c\1\0\0\0\u036c\u0364\1\0\0\0\u036c"+
		"\u0366\1\0\0\0\u036dk\1\0\0\0\u036e\u0374\3\u008aE\0\u0370\u0375\5&\0"+
		"\0\u0372\u0375\5*\0\0\u0374\u0370\1\0\0\0\u0374\u0372\1\0\0\0\u0375\u037a"+
		"\1\0\0\0\u0376\u037b\3v;\0\u0378\u037b\3~?\0\u037a\u0376\1\0\0\0\u037a"+
		"\u0378\1\0\0\0\u037bm\1\0\0\0\u037c\u0382\3~?\0\u037e\u0383\3p8\0\u0380"+
		"\u0383\1\0\0\0\u0382\u037e\1\0\0\0\u0382\u0380\1\0\0\0\u0383o\1\0\0\0"+
		"\u0384\u0385\3r9\0\u0385q\1\0\0\0\u0386\u038d\5\'\0\0\u0388\u038d\5(\0"+
		"\0\u038a\u038d\5)\0\0\u038c\u0386\1\0\0\0\u038c\u0388\1\0\0\0\u038c\u038a"+
		"\1\0\0\0\u038ds\1\0\0\0\u038e\u039f\3\u0082A\0\u0390\u039f\3\u0084B\0"+
		"\u0392\u039f\5\4\0\0\u0394\u039f\3x<\0\u0396\u039f\3\32\r\0\u0398\u039c"+
		"\5-\0\0\u039a\u039d\3\u0086C\0\u039c\u039a\1\0\0\0\u039c\u039d\1\0\0\0"+
		"\u039d\u039f\1\0\0\0\u039e\u038e\1\0\0\0\u039e\u0390\1\0\0\0\u039e\u0392"+
		"\1\0\0\0\u039e\u0394\1\0\0\0\u039e\u0396\1\0\0\0\u039e\u0398\1\0\0\0\u039f"+
		"u\1\0\0\0\u03a0\u03af\3\u0082A\0\u03a2\u03af\3\u0084B\0\u03a4\u03af\3"+
		"\u0080@\0\u03a6\u03af\3x<\0\u03a8\u03ac\5-\0\0\u03aa\u03ad\3\u0086C\0"+
		"\u03ac\u03aa\1\0\0\0\u03ac\u03ad\1\0\0\0\u03ad\u03af\1\0\0\0\u03ae\u03a0"+
		"\1\0\0\0\u03ae\u03a2\1\0\0\0\u03ae\u03a4\1\0\0\0\u03ae\u03a6\1\0\0\0\u03ae"+
		"\u03a8\1\0\0\0\u03afw\1\0\0\0\u03b0\u03b2\5\62\0\0\u03b2\u03b9\3|>\0\u03b4"+
		"\u03b6\5\62\0\0\u03b6\u03b9\3z=\0\u03b8\u03b0\1\0\0\0\u03b8\u03b4\1\0"+
		"\0\0\u03b9y\1\0\0\0\u03ba\u03bc\5!\0\0\u03bc\u03c4\3|>\0\u03be\u03c0\5"+
		"+\0\0\u03c0\u03c3\3|>\0\u03c2\u03be\1\0\0\0\u03c3\u03c6\1\0\0\0\u03c4"+
		"\u03c2\1\0\0\0\u03c4\u03c5\1\0\0\0\u03c5\u03c7\1\0\0\0\u03c6\u03c4\1\0"+
		"\0\0\u03c7\u03c8\5\"\0\0\u03c8{\1\0\0\0\u03c9\u03d2\5\3\0\0\u03cb\u03d2"+
		"\5\66\0\0\u03cd\u03d2\3\u0082A\0\u03cf\u03d2\3\32\r\0\u03d1\u03c9\1\0"+
		"\0\0\u03d1\u03cb\1\0\0\0\u03d1\u03cd\1\0\0\0\u03d1\u03cf\1\0\0\0\u03d2"+
		"}\1\0\0\0\u03d3\u03e2\5!\0\0\u03d5\u03d8\3\6\3\0\u03d7\u03d5\1\0\0\0\u03d7"+
		"\u03d8\1\0\0\0\u03d8\u03dd\1\0\0\0\u03d9\u03dc\3B!\0\u03db\u03d9\1\0\0"+
		"\0\u03dc\u03df\1\0\0\0\u03dd\u03db\1\0\0\0\u03dd\u03de\1\0\0\0\u03de\u03e0"+
		"\1\0\0\0\u03df\u03dd\1\0\0\0\u03e0\u03e3\5\35\0\0\u03e2\u03d7\1\0\0\0"+
		"\u03e2\u03e3\1\0\0\0\u03e3\u03e4\1\0\0\0\u03e4\u03e6\3d\62\0\u03e6\u03e7"+
		"\5\"\0\0\u03e7\177\1\0\0\0\u03e8\u03ec\5\4\0\0\u03ea\u03ed\3\32\r\0\u03ec"+
		"\u03ea\1\0\0\0\u03ec\u03ed\1\0\0\0\u03ed\u0081\1\0\0\0\u03ee\u03f0\5\66"+
		"\0\0\u03f0\u03f2\5.\0\0\u03f2\u03f3\5\66\0\0\u03f3\u0083\1\0\0\0\u03f4"+
		"\u03f8\5\3\0\0\u03f6\u03f9\3\u0086C\0\u03f8\u03f6\1\0\0\0\u03f8\u03f9"+
		"\1\0\0\0\u03f9\u0401\1\0\0\0\u03fa\u03fe\5\66\0\0\u03fc\u03ff\3\u0086"+
		"C\0\u03fe\u03fc\1\0\0\0\u03fe\u03ff\1\0\0\0\u03ff\u0401\1\0\0\0\u0400"+
		"\u03f4\1\0\0\0\u0400\u03fa\1\0\0\0\u0401\u0085\1\0\0\0\u0402\u0404\5$"+
		"\0\0\u0404\u040c\3\u0088D\0\u0406\u0408\5\37\0\0\u0408\u040b\3\u0088D"+
		"\0\u040a\u0406\1\0\0\0\u040b\u040e\1\0\0\0\u040c\u040a\1\0\0\0\u040c\u040d"+
		"\1\0\0\0\u040d\u040f\1\0\0\0\u040e\u040c\1\0\0\0\u040f\u0410\5%\0\0\u0410"+
		"\u0087\1\0\0\0\u0411\u041e\3\u008cF\0\u0413\u0415\3\u008aE\0\u0415\u041b"+
		"\5&\0\0\u0417\u041c\3\u008cF\0\u0419\u041c\5\66\0\0\u041b\u0417\1\0\0"+
		"\0\u041b\u0419\1\0\0\0\u041c\u041e\1\0\0\0\u041d\u0411\1\0\0\0\u041d\u0413"+
		"\1\0\0\0\u041e\u0089\1\0\0\0\u041f\u0426\5\4\0\0\u0421\u0426\5\3\0\0\u0423"+
		"\u0426\5\33\0\0\u0425\u041f\1\0\0\0\u0425\u0421\1\0\0\0\u0425\u0423\1"+
		"\0\0\0\u0426\u008b\1\0\0\0\u0427\u042f\3\u008aE\0\u0429\u042b\5-\0\0\u042b"+
		"\u042e\3\u008aE\0\u042d\u0429\1\0\0\0\u042e\u0431\1\0\0\0\u042f\u042d"+
		"\1\0\0\0\u042f\u0430\1\0\0\0\u0430\u008d\1\0\0\0\u0431\u042f\1\0\0\0h"+
		"\u0090\1\u009c\1\u00a5\1\u00b4\1\u00be\1\u00c8\1\u00db\1\u00e7\1\u00f4"+
		"\1\u00fc\1\u0108\1\u010e\1\u0140\1\u0142\1\u014d\1\u0156\1\u0161\1\u016a"+
		"\1\u0173\1\u0176\1\u017e\1\u0187\1\u018a\1\u0192\1\u019d\1\u01a8\1\u01b1"+
		"\1\u01ba\1\u01c3\1\u01c8\1\u01cb\1\u01d1\1\u01d7\1\u01e2\1\u01e9\1\u01f2"+
		"\1\u01fa\1\u01fe\1\u020a\1\u0212\1\u021e\1\u022a\1\u0230\1\u0237\1\u023b"+
		"\1\u023f\1\u0245\1\u0249\1\u024d\1\u0251\1\u0261\1\u0266\1\u0276\1\u027d"+
		"\1\u028d\1\u029e\1\u02a8\1\u02b4\1\u02bd\1\u02c1\1\u02c5\1\u02d9\1\u02e2"+
		"\1\u02e8\1\u02f0\1\u02f8\1\u0300\1\u0306\1\u0308\1\u0310\1\u0316\1\u0328"+
		"\1\u0335\1\u033b\1\u0345\1\u034c\1\u0352\1\u035a\1\u0362\1\u036a\1\u036c"+
		"\1\u0374\1\u037a\1\u0382\1\u038c\1\u039c\1\u039e\1\u03ac\1\u03ae\1\u03b8"+
		"\1\u03c4\1\u03d1\1\u03d7\1\u03dd\1\u03e2\1\u03ec\1\u03f8\1\u03fe\1\u0400"+
		"\1\u040c\1\u041b\1\u041d\1\u0425\1\u042f\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}