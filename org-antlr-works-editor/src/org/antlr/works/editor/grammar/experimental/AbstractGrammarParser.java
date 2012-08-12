// $ANTLR ANTLRVersion> AbstractGrammarParser.java generatedTimestamp>
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

public abstract class AbstractGrammarParser extends Parser<Token> {
	public static final int
		DOLLAR=43, PROTECTED=18, LT=35, ACTION_COLON=79, STAR=39, DOUBLE_ANGLE_STRING_LITERAL=8, 
		FRAGMENT=14, ACTION_ESCAPE=81, ACTION_REFERENCE=83, ACTION_COLON2=78, 
		ACTION_DOT=69, NOT=49, ID=51, ARG_ACTION_LT=56, ACTION_NEWLINE=88, TOKEN_REF=1, 
		LPAREN=32, ARG_ACTION_ELEMENT=64, ARG_ACTION_RPAREN=59, ARG_ACTION_GT=57, 
		AT=47, LEXER_CHAR_SET=3, RPAREN=33, ARG_ACTION_LPAREN=58, IMPORT=13, STRING_LITERAL=53, 
		END_ACTION=89, ETC=46, COMMA=30, ACTION_GT=71, ACTION_LITERAL=85, ARG_ACTION_NEWLINE=67, 
		DOC_COMMENT=4, BLOCK_COMMENT=5, PLUS=40, BEGIN_ACTION=10, DOUBLE_QUOTE_STRING_LITERAL=7, 
		DOT=44, ACTION_LBRACK=74, MODE=27, ACTION_WORD=82, GRAMMAR=17, ARG_ACTION_ESCAPE=62, 
		RETURNS=21, ARG_ACTION_TEXT=65, ACTION_EQUALS=76, LOCALS=22, ACTION_WS=87, 
		RBRACE=50, POUND=48, LINE_COMMENT=6, PRIVATE=20, RARROW=34, END_ARG_ACTION=68, 
		TOKENS=12, RANGE=45, THROWS=23, INT=52, SEMI=31, RULE_REF=2, ARG_ACTION_EQUALS=60, 
		ACTION_RPAREN=73, ACTION_COMMA=77, COLON=28, COLONCOLON=29, ACTION_RBRACK=75, 
		WS=54, ACTION_COMMENT=84, QUESTION=38, FINALLY=25, ACTION_LT=70, TEMPLATE=26, 
		ACTION_TEXT=86, LEXER=15, ERRCHAR=55, ACTION_MINUS=80, ARG_ACTION_COMMA=61, 
		OR=42, ASSIGN=37, PLUS_ASSIGN=41, ARG_ACTION_WS=66, GT=36, CATCH=24, ARG_ACTION_WORD=63, 
		PUBLIC=19, PARSER=16, ACTION_LPAREN=72, OPTIONS=11, BEGIN_ARG_ACTION=9, 
		RULE=90, PREC_RULE=91, RULES=92, RULEMODIFIERS=93, RULEACTIONS=94, BLOCK=95, 
		OPTIONAL=96, CLOSURE=97, POSITIVE_CLOSURE=98, SET=99, CHAR_RANGE=100, 
		EPSILON=101, ALT=102, ALTLIST=103, ARG=104, ARGLIST=105, RET=106, COMBINED=107, 
		INITACTION=108, LABEL=109, WILDCARD=110, LIST=111, ELEMENT_OPTIONS=112, 
		RESULT=113, LEXER_ALT_ACTION=114, LEXER_ACTION_CALL=115;
	public static final String[] tokenNames = {
        "<INVALID>", "TOKEN_REF", "RULE_REF", "LEXER_CHAR_SET", "DOC_COMMENT", 
        "BLOCK_COMMENT", "LINE_COMMENT", "DOUBLE_QUOTE_STRING_LITERAL", 
        "DOUBLE_ANGLE_STRING_LITERAL", "'['", "'{'", "OPTIONS", "TOKENS", 
        "'import'", "'fragment'", "'lexer'", "'parser'", "'grammar'", "'protected'", 
        "'public'", "'private'", "'returns'", "'locals'", "'throws'", "'catch'", 
        "'finally'", "'template'", "'mode'", "':'", "'::'", "','", "';'", 
        "'('", "')'", "'->'", "'<'", "'>'", "'='", "'?'", "'*'", "'+'", 
        "'+='", "'|'", "'$'", "'.'", "'..'", "'...'", "'@'", "'#'", "'~'", 
        "'}'", "ID", "INT", "STRING_LITERAL", "WS", "ERRCHAR", "ARG_ACTION_LT", 
        "ARG_ACTION_GT", "ARG_ACTION_LPAREN", "ARG_ACTION_RPAREN", "ARG_ACTION_EQUALS", 
        "ARG_ACTION_COMMA", "ARG_ACTION_ESCAPE", "ARG_ACTION_WORD", "ARG_ACTION_ELEMENT", 
        "ARG_ACTION_TEXT", "ARG_ACTION_WS", "ARG_ACTION_NEWLINE", "']'", 
        "ACTION_DOT", "ACTION_LT", "ACTION_GT", "ACTION_LPAREN", "ACTION_RPAREN", 
        "ACTION_LBRACK", "ACTION_RBRACK", "ACTION_EQUALS", "ACTION_COMMA", 
        "ACTION_COLON2", "ACTION_COLON", "'-'", "ACTION_ESCAPE", "ACTION_WORD", 
        "ACTION_REFERENCE", "ACTION_COMMENT", "ACTION_LITERAL", "ACTION_TEXT", 
        "ACTION_WS", "ACTION_NEWLINE", "END_ACTION", "RULE", "PREC_RULE", 
        "RULES", "RULEMODIFIERS", "RULEACTIONS", "BLOCK", "OPTIONAL", "CLOSURE", 
        "POSITIVE_CLOSURE", "SET", "CHAR_RANGE", "EPSILON", "ALT", "ALTLIST", 
        "ARG", "ARGLIST", "RET", "COMBINED", "INITACTION", "LABEL", "WILDCARD", 
        "LIST", "ELEMENT_OPTIONS", "RESULT", "LEXER_ALT_ACTION", "LEXER_ACTION_CALL"
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
		RULE_lexerCommand = 48, RULE_lexerCommandName = 49, RULE_lexerCommandExpr = 50, 
		RULE_altList = 51, RULE_alternative = 52, RULE_elements = 53, RULE_element = 54, 
		RULE_labeledElement = 55, RULE_ebnf = 56, RULE_blockSuffix = 57, RULE_ebnfSuffix = 58, 
		RULE_lexerAtom = 59, RULE_atom = 60, RULE_notSet = 61, RULE_blockSet = 62, 
		RULE_setElement = 63, RULE_block = 64, RULE_ruleref = 65, RULE_range = 66, 
		RULE_terminal = 67, RULE_elementOptions = 68, RULE_elementOption = 69, 
		RULE_id = 70, RULE_qid = 71;
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
		"lexerCommands", "lexerCommand", "lexerCommandName", "lexerCommandExpr", 
		"altList", "alternative", "elements", "element", "labeledElement", "ebnf", 
		"blockSuffix", "ebnfSuffix", "lexerAtom", "atom", "notSet", "blockSet", 
		"setElement", "block", "ruleref", "range", "terminal", "elementOptions", 
		"elementOption", "id", "qid"
	};

	@Override
	public String getGrammarFileName() { return "GrammarParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public AbstractGrammarParser(TokenStream<? extends Token> input) {
		super(input);
		_interp = new ParserATNSimulator<Token>(this,_ATN);
	}
	public static class GrammarSpecContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> DOC_COMMENT() { return getToken(AbstractGrammarParser.DOC_COMMENT, 0); }
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public List<? extends ModeSpecContext> modeSpec() {
		    return getRuleContexts(ModeSpecContext.class);
		}
		public TerminalNode<Token> EOF() { return getToken(AbstractGrammarParser.EOF, 0); }
		public TerminalNode<Token> SEMI() { return getToken(AbstractGrammarParser.SEMI, 0); }
		public PrequelConstructContext prequelConstruct(int i) {
		    return getRuleContext(PrequelConstructContext.class,i);
		}
		public ModeSpecContext modeSpec(int i) {
		    return getRuleContext(ModeSpecContext.class,i);
		}
		public List<? extends PrequelConstructContext> prequelConstruct() {
		    return getRuleContexts(PrequelConstructContext.class);
		}
		public GrammarTypeContext grammarType() {
		    return getRuleContext(GrammarTypeContext.class,0);
		}
		public RulesContext rules() {
		    return getRuleContext(RulesContext.class,0);
		}
		public GrammarSpecContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarSpec; }
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
		GrammarSpecContext _localctx = new GrammarSpecContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_grammarSpec);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(144); match(DOC_COMMENT);
					}
					break;
			}
			setState(147); grammarType();
			setState(148); id();
			setState(149); match(SEMI);
			setState(153);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(150); prequelConstruct();
					}
					} 
				}
				setState(155);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(156); rules();
			setState(160);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(157); modeSpec();
					}
					} 
				}
				setState(162);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(163); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GrammarTypeContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> PARSER() { return getToken(AbstractGrammarParser.PARSER, 0); }
		public TerminalNode<Token> GRAMMAR(int i) {
		    return getToken(AbstractGrammarParser.GRAMMAR, i);
		}
		public TerminalNode<Token> LEXER() { return getToken(AbstractGrammarParser.LEXER, 0); }
		public List<? extends TerminalNode<Token>> GRAMMAR() { return getTokens(AbstractGrammarParser.GRAMMAR); }
		public GrammarTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarType; }
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
		GrammarTypeContext _localctx = new GrammarTypeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_grammarType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(165); match(LEXER);
					setState(166); match(GRAMMAR);
					}
					break;

				case 2:
					{
					setState(167); match(PARSER);
					setState(168); match(GRAMMAR);
					}
					break;

				case 3:
					{
					setState(169); match(GRAMMAR);
					}
					break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public PrequelConstructContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prequelConstruct; }
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
		PrequelConstructContext _localctx = new PrequelConstructContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_prequelConstruct);
		try {
			setState(176);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(172); optionsSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(173); delegateGrammars();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(174); tokensSpec();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(175); action();
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionsSpecContext extends ParserRuleContext<Token> {
		public OptionContext option(int i) {
		    return getRuleContext(OptionContext.class,i);
		}
		public TerminalNode<Token> RBRACE() { return getToken(AbstractGrammarParser.RBRACE, 0); }
		public List<? extends TerminalNode<Token>> SEMI() { return getTokens(AbstractGrammarParser.SEMI); }
		public TerminalNode<Token> SEMI(int i) {
		    return getToken(AbstractGrammarParser.SEMI, i);
		}
		public TerminalNode<Token> OPTIONS() { return getToken(AbstractGrammarParser.OPTIONS, 0); }
		public List<? extends OptionContext> option() {
		    return getRuleContexts(OptionContext.class);
		}
		public OptionsSpecContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionsSpec; }
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
		OptionsSpecContext _localctx = new OptionsSpecContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_optionsSpec);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(178); match(OPTIONS);
			setState(184);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(179); option();
					setState(180); match(SEMI);
					}
					} 
				}
				setState(186);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(187); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> ASSIGN() { return getToken(AbstractGrammarParser.ASSIGN, 0); }
		public OptionContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
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
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189); id();
			setState(190); match(ASSIGN);
			setState(191); optionValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionValueContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> STAR() { return getToken(AbstractGrammarParser.STAR, 0); }
		public TerminalNode<Token> INT() { return getToken(AbstractGrammarParser.INT, 0); }
		public TerminalNode<Token> STRING_LITERAL() { return getToken(AbstractGrammarParser.STRING_LITERAL, 0); }
		public QidContext qid() {
		    return getRuleContext(QidContext.class,0);
		}
		public OptionValueContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionValue; }
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
		OptionValueContext _localctx = new OptionValueContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_optionValue);
		try {
			setState(197);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(193); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(194); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(195); match(INT);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(196); match(STAR);
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> IMPORT() { return getToken(AbstractGrammarParser.IMPORT, 0); }
		public TerminalNode<Token> COMMA(int i) {
		    return getToken(AbstractGrammarParser.COMMA, i);
		}
		public List<? extends TerminalNode<Token>> COMMA() { return getTokens(AbstractGrammarParser.COMMA); }
		public TerminalNode<Token> SEMI() { return getToken(AbstractGrammarParser.SEMI, 0); }
		public DelegateGrammarContext delegateGrammar(int i) {
		    return getRuleContext(DelegateGrammarContext.class,i);
		}
		public DelegateGrammarsContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delegateGrammars; }
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
		DelegateGrammarsContext _localctx = new DelegateGrammarsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_delegateGrammars);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(199); match(IMPORT);
			setState(200); delegateGrammar();
			setState(205);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(201); match(COMMA);
					setState(202); delegateGrammar();
					}
					} 
				}
				setState(207);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(208); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> ASSIGN() { return getToken(AbstractGrammarParser.ASSIGN, 0); }
		public DelegateGrammarContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delegateGrammar; }
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
		DelegateGrammarContext _localctx = new DelegateGrammarContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_delegateGrammar);
		try {
			setState(215);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(210); id();
					setState(211); match(ASSIGN);
					setState(212); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(214); id();
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TokensSpecContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> TOKENS() { return getToken(AbstractGrammarParser.TOKENS, 0); }
		public TokenSpecContext tokenSpec(int i) {
		    return getRuleContext(TokenSpecContext.class,i);
		}
		public TerminalNode<Token> RBRACE() { return getToken(AbstractGrammarParser.RBRACE, 0); }
		public List<? extends TokenSpecContext> tokenSpec() {
		    return getRuleContexts(TokenSpecContext.class);
		}
		public TokensSpecContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tokensSpec; }
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
		TokensSpecContext _localctx = new TokensSpecContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_tokensSpec);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(217); match(TOKENS);
			setState(219); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(218); tokenSpec();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(221); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			setState(223); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> STRING_LITERAL() { return getToken(AbstractGrammarParser.STRING_LITERAL, 0); }
		public TerminalNode<Token> SEMI() { return getToken(AbstractGrammarParser.SEMI, 0); }
		public TerminalNode<Token> RULE_REF() { return getToken(AbstractGrammarParser.RULE_REF, 0); }
		public TerminalNode<Token> ASSIGN() { return getToken(AbstractGrammarParser.ASSIGN, 0); }
		public TokenSpecContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tokenSpec; }
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
		TokenSpecContext _localctx = new TokenSpecContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tokenSpec);
		try {
			setState(234);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(225); id();
					setState(229);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
						case 1:
							{
							setState(226); match(ASSIGN);
							setState(227); match(STRING_LITERAL);
							}
							break;

						case 2:
							{
							}
							break;
					}
					setState(231); match(SEMI);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(233); match(RULE_REF);
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionBlockContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> ACTION_COMMENT(int i) {
		    return getToken(AbstractGrammarParser.ACTION_COMMENT, i);
		}
		public ActionScopeExpressionContext actionScopeExpression(int i) {
		    return getRuleContext(ActionScopeExpressionContext.class,i);
		}
		public List<? extends TerminalNode<Token>> ACTION_COLON() { return getTokens(AbstractGrammarParser.ACTION_COLON); }
		public List<? extends TerminalNode<Token>> ACTION_WS() { return getTokens(AbstractGrammarParser.ACTION_WS); }
		public TerminalNode<Token> ACTION_MINUS(int i) {
		    return getToken(AbstractGrammarParser.ACTION_MINUS, i);
		}
		public List<? extends ActionExpressionContext> actionExpression() {
		    return getRuleContexts(ActionExpressionContext.class);
		}
		public TerminalNode<Token> ACTION_COMMA(int i) {
		    return getToken(AbstractGrammarParser.ACTION_COMMA, i);
		}
		public TerminalNode<Token> ACTION_ESCAPE(int i) {
		    return getToken(AbstractGrammarParser.ACTION_ESCAPE, i);
		}
		public List<? extends ActionBlockContext> actionBlock() {
		    return getRuleContexts(ActionBlockContext.class);
		}
		public TerminalNode<Token> ACTION_GT(int i) {
		    return getToken(AbstractGrammarParser.ACTION_GT, i);
		}
		public List<? extends TerminalNode<Token>> ACTION_ESCAPE() { return getTokens(AbstractGrammarParser.ACTION_ESCAPE); }
		public List<? extends TerminalNode<Token>> ACTION_REFERENCE() { return getTokens(AbstractGrammarParser.ACTION_REFERENCE); }
		public List<? extends TerminalNode<Token>> ACTION_COLON2() { return getTokens(AbstractGrammarParser.ACTION_COLON2); }
		public TerminalNode<Token> ACTION_COLON2(int i) {
		    return getToken(AbstractGrammarParser.ACTION_COLON2, i);
		}
		public List<? extends TerminalNode<Token>> ACTION_DOT() { return getTokens(AbstractGrammarParser.ACTION_DOT); }
		public TerminalNode<Token> ACTION_NEWLINE(int i) {
		    return getToken(AbstractGrammarParser.ACTION_NEWLINE, i);
		}
		public List<? extends ActionScopeExpressionContext> actionScopeExpression() {
		    return getRuleContexts(ActionScopeExpressionContext.class);
		}
		public ActionBlockContext actionBlock(int i) {
		    return getRuleContext(ActionBlockContext.class,i);
		}
		public List<? extends TerminalNode<Token>> ACTION_NEWLINE() { return getTokens(AbstractGrammarParser.ACTION_NEWLINE); }
		public List<? extends TerminalNode<Token>> ACTION_RPAREN() { return getTokens(AbstractGrammarParser.ACTION_RPAREN); }
		public List<? extends TerminalNode<Token>> ACTION_COMMA() { return getTokens(AbstractGrammarParser.ACTION_COMMA); }
		public TerminalNode<Token> ACTION_LBRACK(int i) {
		    return getToken(AbstractGrammarParser.ACTION_LBRACK, i);
		}
		public TerminalNode<Token> ACTION_RBRACK(int i) {
		    return getToken(AbstractGrammarParser.ACTION_RBRACK, i);
		}
		public List<? extends TerminalNode<Token>> ACTION_RBRACK() { return getTokens(AbstractGrammarParser.ACTION_RBRACK); }
		public List<? extends TerminalNode<Token>> ACTION_COMMENT() { return getTokens(AbstractGrammarParser.ACTION_COMMENT); }
		public TerminalNode<Token> END_ACTION() { return getToken(AbstractGrammarParser.END_ACTION, 0); }
		public List<? extends TerminalNode<Token>> ACTION_LT() { return getTokens(AbstractGrammarParser.ACTION_LT); }
		public List<? extends TerminalNode<Token>> ACTION_GT() { return getTokens(AbstractGrammarParser.ACTION_GT); }
		public List<? extends TerminalNode<Token>> ACTION_TEXT() { return getTokens(AbstractGrammarParser.ACTION_TEXT); }
		public TerminalNode<Token> ACTION_TEXT(int i) {
		    return getToken(AbstractGrammarParser.ACTION_TEXT, i);
		}
		public TerminalNode<Token> ACTION_EQUALS(int i) {
		    return getToken(AbstractGrammarParser.ACTION_EQUALS, i);
		}
		public List<? extends TerminalNode<Token>> ACTION_MINUS() { return getTokens(AbstractGrammarParser.ACTION_MINUS); }
		public List<? extends TerminalNode<Token>> ACTION_LITERAL() { return getTokens(AbstractGrammarParser.ACTION_LITERAL); }
		public TerminalNode<Token> ACTION_WS(int i) {
		    return getToken(AbstractGrammarParser.ACTION_WS, i);
		}
		public TerminalNode<Token> ACTION_COLON(int i) {
		    return getToken(AbstractGrammarParser.ACTION_COLON, i);
		}
		public TerminalNode<Token> ACTION_LT(int i) {
		    return getToken(AbstractGrammarParser.ACTION_LT, i);
		}
		public ActionExpressionContext actionExpression(int i) {
		    return getRuleContext(ActionExpressionContext.class,i);
		}
		public TerminalNode<Token> BEGIN_ACTION() { return getToken(AbstractGrammarParser.BEGIN_ACTION, 0); }
		public TerminalNode<Token> ACTION_REFERENCE(int i) {
		    return getToken(AbstractGrammarParser.ACTION_REFERENCE, i);
		}
		public TerminalNode<Token> ACTION_WORD(int i) {
		    return getToken(AbstractGrammarParser.ACTION_WORD, i);
		}
		public TerminalNode<Token> ACTION_LPAREN(int i) {
		    return getToken(AbstractGrammarParser.ACTION_LPAREN, i);
		}
		public TerminalNode<Token> ACTION_LITERAL(int i) {
		    return getToken(AbstractGrammarParser.ACTION_LITERAL, i);
		}
		public TerminalNode<Token> ACTION_DOT(int i) {
		    return getToken(AbstractGrammarParser.ACTION_DOT, i);
		}
		public List<? extends TerminalNode<Token>> ACTION_LBRACK() { return getTokens(AbstractGrammarParser.ACTION_LBRACK); }
		public List<? extends TerminalNode<Token>> ACTION_LPAREN() { return getTokens(AbstractGrammarParser.ACTION_LPAREN); }
		public List<? extends TerminalNode<Token>> ACTION_WORD() { return getTokens(AbstractGrammarParser.ACTION_WORD); }
		public TerminalNode<Token> ACTION_RPAREN(int i) {
		    return getToken(AbstractGrammarParser.ACTION_RPAREN, i);
		}
		public List<? extends TerminalNode<Token>> ACTION_EQUALS() { return getTokens(AbstractGrammarParser.ACTION_EQUALS); }
		public ActionBlockContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionBlock; }
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
		ActionBlockContext _localctx = new ActionBlockContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_actionBlock);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(236); match(BEGIN_ACTION);
			setState(262);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					setState(260);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
						case 1:
							{
							setState(237); actionBlock();
							}
							break;

						case 2:
							{
							setState(238); actionExpression();
							}
							break;

						case 3:
							{
							setState(239); actionScopeExpression();
							}
							break;

						case 4:
							{
							setState(240); match(ACTION_WS);
							}
							break;

						case 5:
							{
							setState(241); match(ACTION_NEWLINE);
							}
							break;

						case 6:
							{
							setState(242); match(ACTION_COMMENT);
							}
							break;

						case 7:
							{
							setState(243); match(ACTION_LITERAL);
							}
							break;

						case 8:
							{
							setState(244); match(ACTION_TEXT);
							}
							break;

						case 9:
							{
							setState(245); match(ACTION_LT);
							}
							break;

						case 10:
							{
							setState(246); match(ACTION_GT);
							}
							break;

						case 11:
							{
							setState(247); match(ACTION_LPAREN);
							}
							break;

						case 12:
							{
							setState(248); match(ACTION_RPAREN);
							}
							break;

						case 13:
							{
							setState(249); match(ACTION_LBRACK);
							}
							break;

						case 14:
							{
							setState(250); match(ACTION_RBRACK);
							}
							break;

						case 15:
							{
							setState(251); match(ACTION_EQUALS);
							}
							break;

						case 16:
							{
							setState(252); match(ACTION_COMMA);
							}
							break;

						case 17:
							{
							setState(253); match(ACTION_ESCAPE);
							}
							break;

						case 18:
							{
							setState(254); match(ACTION_WORD);
							}
							break;

						case 19:
							{
							setState(255); match(ACTION_REFERENCE);
							}
							break;

						case 20:
							{
							setState(256); match(ACTION_COLON);
							}
							break;

						case 21:
							{
							setState(257); match(ACTION_COLON2);
							}
							break;

						case 22:
							{
							setState(258); match(ACTION_MINUS);
							}
							break;

						case 23:
							{
							setState(259); match(ACTION_DOT);
							}
							break;
					}
					} 
				}
				setState(264);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			setState(265); match(END_ACTION);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> ACTION_REFERENCE() { return getToken(AbstractGrammarParser.ACTION_REFERENCE, 0); }
		public TerminalNode<Token> ACTION_DOT() { return getToken(AbstractGrammarParser.ACTION_DOT, 0); }
		public IgnoredContext ignored(int i) {
		    return getRuleContext(IgnoredContext.class,i);
		}
		public TerminalNode<Token> ACTION_WORD() { return getToken(AbstractGrammarParser.ACTION_WORD, 0); }
		public ActionExpressionContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionExpression; }
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
		ActionExpressionContext _localctx = new ActionExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_actionExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(267); _localctx.ref = match(ACTION_REFERENCE);
			setState(271);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(268); ignored();
					}
					} 
				}
				setState(273);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(274); _localctx.op = match(ACTION_DOT);
			setState(278);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(275); ignored();
					}
					} 
				}
				setState(280);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(281); _localctx.member = match(ACTION_WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> ACTION_REFERENCE() { return getToken(AbstractGrammarParser.ACTION_REFERENCE, 0); }
		public TerminalNode<Token> ACTION_COLON2() { return getToken(AbstractGrammarParser.ACTION_COLON2, 0); }
		public TerminalNode<Token> ACTION_RBRACK() { return getToken(AbstractGrammarParser.ACTION_RBRACK, 0); }
		public IgnoredContext ignored(int i) {
		    return getRuleContext(IgnoredContext.class,i);
		}
		public TerminalNode<Token> ACTION_WORD(int i) {
		    return getToken(AbstractGrammarParser.ACTION_WORD, i);
		}
		public TerminalNode<Token> ACTION_LBRACK() { return getToken(AbstractGrammarParser.ACTION_LBRACK, 0); }
		public List<? extends TerminalNode<Token>> ACTION_WORD() { return getTokens(AbstractGrammarParser.ACTION_WORD); }
		public TerminalNode<Token> ACTION_MINUS() { return getToken(AbstractGrammarParser.ACTION_MINUS, 0); }
		public ActionScopeExpressionContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionScopeExpression; }
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
		ActionScopeExpressionContext _localctx = new ActionScopeExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_actionScopeExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(283); _localctx.ref = match(ACTION_REFERENCE);
			setState(287);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(284); ignored();
					}
					} 
				}
				setState(289);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(320);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(290); match(ACTION_LBRACK);
					setState(294);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(291); ignored();
							}
							} 
						}
						setState(296);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
					}
					setState(304);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
						case 1:
							{
							setState(297); _localctx.neg = match(ACTION_MINUS);
							setState(301);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(298); ignored();
									}
									} 
								}
								setState(303);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
							}
							}
							break;
					}
					setState(306); _localctx.index = match(ACTION_WORD);
					setState(310);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(307); ignored();
							}
							} 
						}
						setState(312);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
					}
					setState(313); match(ACTION_RBRACK);
					setState(317);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(314); ignored();
							}
							} 
						}
						setState(319);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
					}
					}
					break;
			}
			setState(322); _localctx.op = match(ACTION_COLON2);
			setState(326);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(323); ignored();
					}
					} 
				}
				setState(328);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			setState(329); _localctx.member = match(ACTION_WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgActionBlockContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> ARG_ACTION_GT(int i) {
		    return getToken(AbstractGrammarParser.ARG_ACTION_GT, i);
		}
		public TerminalNode<Token> ARG_ACTION_LPAREN(int i) {
		    return getToken(AbstractGrammarParser.ARG_ACTION_LPAREN, i);
		}
		public TerminalNode<Token> ARG_ACTION_NEWLINE(int i) {
		    return getToken(AbstractGrammarParser.ARG_ACTION_NEWLINE, i);
		}
		public TerminalNode<Token> ARG_ACTION_RPAREN(int i) {
		    return getToken(AbstractGrammarParser.ARG_ACTION_RPAREN, i);
		}
		public TerminalNode<Token> END_ARG_ACTION() { return getToken(AbstractGrammarParser.END_ARG_ACTION, 0); }
		public TerminalNode<Token> ARG_ACTION_WORD(int i) {
		    return getToken(AbstractGrammarParser.ARG_ACTION_WORD, i);
		}
		public List<? extends TerminalNode<Token>> ARG_ACTION_LT() { return getTokens(AbstractGrammarParser.ARG_ACTION_LT); }
		public List<? extends TerminalNode<Token>> ARG_ACTION_EQUALS() { return getTokens(AbstractGrammarParser.ARG_ACTION_EQUALS); }
		public List<? extends TerminalNode<Token>> ARG_ACTION_ELEMENT() { return getTokens(AbstractGrammarParser.ARG_ACTION_ELEMENT); }
		public TerminalNode<Token> ARG_ACTION_ESCAPE(int i) {
		    return getToken(AbstractGrammarParser.ARG_ACTION_ESCAPE, i);
		}
		public List<? extends TerminalNode<Token>> ARG_ACTION_RPAREN() { return getTokens(AbstractGrammarParser.ARG_ACTION_RPAREN); }
		public List<? extends TerminalNode<Token>> ARG_ACTION_GT() { return getTokens(AbstractGrammarParser.ARG_ACTION_GT); }
		public TerminalNode<Token> ARG_ACTION_COMMA(int i) {
		    return getToken(AbstractGrammarParser.ARG_ACTION_COMMA, i);
		}
		public List<? extends TerminalNode<Token>> ARG_ACTION_LPAREN() { return getTokens(AbstractGrammarParser.ARG_ACTION_LPAREN); }
		public List<? extends TerminalNode<Token>> ARG_ACTION_COMMA() { return getTokens(AbstractGrammarParser.ARG_ACTION_COMMA); }
		public TerminalNode<Token> ARG_ACTION_ELEMENT(int i) {
		    return getToken(AbstractGrammarParser.ARG_ACTION_ELEMENT, i);
		}
		public List<? extends TerminalNode<Token>> ARG_ACTION_NEWLINE() { return getTokens(AbstractGrammarParser.ARG_ACTION_NEWLINE); }
		public TerminalNode<Token> ARG_ACTION_WS(int i) {
		    return getToken(AbstractGrammarParser.ARG_ACTION_WS, i);
		}
		public List<? extends TerminalNode<Token>> ARG_ACTION_WS() { return getTokens(AbstractGrammarParser.ARG_ACTION_WS); }
		public TerminalNode<Token> ARG_ACTION_LT(int i) {
		    return getToken(AbstractGrammarParser.ARG_ACTION_LT, i);
		}
		public TerminalNode<Token> ARG_ACTION_EQUALS(int i) {
		    return getToken(AbstractGrammarParser.ARG_ACTION_EQUALS, i);
		}
		public List<? extends TerminalNode<Token>> ARG_ACTION_WORD() { return getTokens(AbstractGrammarParser.ARG_ACTION_WORD); }
		public TerminalNode<Token> ARG_ACTION_TEXT(int i) {
		    return getToken(AbstractGrammarParser.ARG_ACTION_TEXT, i);
		}
		public List<? extends TerminalNode<Token>> ARG_ACTION_ESCAPE() { return getTokens(AbstractGrammarParser.ARG_ACTION_ESCAPE); }
		public List<? extends TerminalNode<Token>> ARG_ACTION_TEXT() { return getTokens(AbstractGrammarParser.ARG_ACTION_TEXT); }
		public TerminalNode<Token> BEGIN_ARG_ACTION() { return getToken(AbstractGrammarParser.BEGIN_ARG_ACTION, 0); }
		public ArgActionBlockContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argActionBlock; }
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

	@RuleVersion(1)
	public final ArgActionBlockContext argActionBlock() throws RecognitionException {
		ArgActionBlockContext _localctx = new ArgActionBlockContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_argActionBlock);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(331); match(BEGIN_ARG_ACTION);
			setState(335);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(332);
					_input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==ARG_ACTION_LT || _la==ARG_ACTION_GT || _la==ARG_ACTION_LPAREN || _la==ARG_ACTION_RPAREN || _la==ARG_ACTION_EQUALS || _la==ARG_ACTION_COMMA || _la==ARG_ACTION_ESCAPE || _la==ARG_ACTION_WORD || _la==ARG_ACTION_ELEMENT || _la==ARG_ACTION_TEXT || _la==ARG_ACTION_WS || _la==ARG_ACTION_NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					} 
				}
				setState(337);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			setState(338); match(END_ARG_ACTION);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> ARG_ACTION_COMMA(int i) {
		    return getToken(AbstractGrammarParser.ARG_ACTION_COMMA, i);
		}
		public List<? extends IgnoredContext> ignored() {
		    return getRuleContexts(IgnoredContext.class);
		}
		public TerminalNode<Token> END_ARG_ACTION() { return getToken(AbstractGrammarParser.END_ARG_ACTION, 0); }
		public IgnoredContext ignored(int i) {
		    return getRuleContext(IgnoredContext.class,i);
		}
		public ArgActionParameterContext argActionParameter(int i) {
		    return getRuleContext(ArgActionParameterContext.class,i);
		}
		public List<? extends ArgActionParameterContext> argActionParameter() {
		    return getRuleContexts(ArgActionParameterContext.class);
		}
		public List<? extends TerminalNode<Token>> ARG_ACTION_COMMA() { return getTokens(AbstractGrammarParser.ARG_ACTION_COMMA); }
		public TerminalNode<Token> BEGIN_ARG_ACTION() { return getToken(AbstractGrammarParser.BEGIN_ARG_ACTION, 0); }
		public ArgActionParametersContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argActionParameters; }
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
		ArgActionParametersContext _localctx = new ArgActionParametersContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_argActionParameters);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(340); match(BEGIN_ARG_ACTION);
			setState(344);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(341); ignored();
					}
					} 
				}
				setState(346);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(373);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(347); _localctx.argActionParameter = argActionParameter();
					_localctx.parameters.add(_localctx.argActionParameter);
					setState(351);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(348); ignored();
							}
							} 
						}
						setState(353);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
					}
					setState(370);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(354); match(ARG_ACTION_COMMA);
							setState(358);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(355); ignored();
									}
									} 
								}
								setState(360);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
							}
							setState(361); _localctx.argActionParameter = argActionParameter();
							_localctx.parameters.add(_localctx.argActionParameter);
							setState(365);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(362); ignored();
									}
									} 
								}
								setState(367);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
							}
							}
							} 
						}
						setState(372);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
					}
					}
					break;
			}
			setState(375); match(END_ARG_ACTION);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public List<? extends IgnoredContext> ignored() {
		    return getRuleContexts(IgnoredContext.class);
		}
		public IgnoredContext ignored(int i) {
		    return getRuleContext(IgnoredContext.class,i);
		}
		public TerminalNode<Token> ARG_ACTION_WORD() { return getToken(AbstractGrammarParser.ARG_ACTION_WORD, 0); }
		public ArgActionParameterTypeContext argActionParameterType() {
		    return getRuleContext(ArgActionParameterTypeContext.class,0);
		}
		public ArgActionParameterContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argActionParameter; }
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
		ArgActionParameterContext _localctx = new ArgActionParameterContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_argActionParameter);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(377); _localctx.type = argActionParameterType();
					}
					break;
			}
			setState(383);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(380); ignored();
					}
					} 
				}
				setState(385);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			setState(386); _localctx.name = match(ARG_ACTION_WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgActionParameterTypeContext extends ParserRuleContext<Token> {
		public List<? extends IgnoredContext> ignored() {
		    return getRuleContexts(IgnoredContext.class);
		}
		public ArgActionParameterTypePartContext argActionParameterTypePart(int i) {
		    return getRuleContext(ArgActionParameterTypePartContext.class,i);
		}
		public IgnoredContext ignored(int i) {
		    return getRuleContext(IgnoredContext.class,i);
		}
		public List<? extends ArgActionParameterTypePartContext> argActionParameterTypePart() {
		    return getRuleContexts(ArgActionParameterTypePartContext.class);
		}
		public ArgActionParameterTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argActionParameterType; }
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
		ArgActionParameterTypeContext _localctx = new ArgActionParameterTypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_argActionParameterType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(388); argActionParameterTypePart();
			setState(398);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(392);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(389); ignored();
							}
							} 
						}
						setState(394);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
					}
					setState(395); argActionParameterTypePart();
					}
					} 
				}
				setState(400);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgActionParameterTypePartContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> ARG_ACTION_GT() { return getToken(AbstractGrammarParser.ARG_ACTION_GT, 0); }
		public TerminalNode<Token> ARG_ACTION_LPAREN() { return getToken(AbstractGrammarParser.ARG_ACTION_LPAREN, 0); }
		public TerminalNode<Token> ARG_ACTION_WORD() { return getToken(AbstractGrammarParser.ARG_ACTION_WORD, 0); }
		public ArgActionParameterTypeContext argActionParameterType() {
		    return getRuleContext(ArgActionParameterTypeContext.class,0);
		}
		public TerminalNode<Token> ARG_ACTION_LT() { return getToken(AbstractGrammarParser.ARG_ACTION_LT, 0); }
		public TerminalNode<Token> ARG_ACTION_RPAREN() { return getToken(AbstractGrammarParser.ARG_ACTION_RPAREN, 0); }
		public ArgActionParameterTypePartContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argActionParameterTypePart; }
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
		ArgActionParameterTypePartContext _localctx = new ArgActionParameterTypePartContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_argActionParameterTypePart);
		try {
			setState(412);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(401); match(ARG_ACTION_WORD);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(402); match(ARG_ACTION_LT);
					setState(404);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
						case 1:
							{
							setState(403); argActionParameterType();
							}
							break;
					}
					setState(406); match(ARG_ACTION_GT);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(407); match(ARG_ACTION_LPAREN);
					setState(409);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
						case 1:
							{
							setState(408); argActionParameterType();
							}
							break;
					}
					setState(411); match(ARG_ACTION_RPAREN);
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IgnoredContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> ARG_ACTION_NEWLINE() { return getToken(AbstractGrammarParser.ARG_ACTION_NEWLINE, 0); }
		public TerminalNode<Token> ARG_ACTION_WS() { return getToken(AbstractGrammarParser.ARG_ACTION_WS, 0); }
		public TerminalNode<Token> ACTION_WS() { return getToken(AbstractGrammarParser.ACTION_WS, 0); }
		public TerminalNode<Token> ACTION_COMMENT() { return getToken(AbstractGrammarParser.ACTION_COMMENT, 0); }
		public TerminalNode<Token> ACTION_NEWLINE() { return getToken(AbstractGrammarParser.ACTION_NEWLINE, 0); }
		public IgnoredContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ignored; }
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
		IgnoredContext _localctx = new IgnoredContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ignored);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			_input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==ARG_ACTION_WS || _la==ARG_ACTION_NEWLINE || _la==ACTION_COMMENT || _la==ACTION_WS || _la==ACTION_NEWLINE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> AT() { return getToken(AbstractGrammarParser.AT, 0); }
		public TerminalNode<Token> COLONCOLON() { return getToken(AbstractGrammarParser.COLONCOLON, 0); }
		public ActionScopeNameContext actionScopeName() {
		    return getRuleContext(ActionScopeNameContext.class,0);
		}
		public ActionContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
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
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(416); match(AT);
			setState(420);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					{
					setState(417); actionScopeName();
					setState(418); match(COLONCOLON);
					}
					break;
			}
			setState(422); id();
			setState(423); actionBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> PARSER() { return getToken(AbstractGrammarParser.PARSER, 0); }
		public TerminalNode<Token> LEXER() { return getToken(AbstractGrammarParser.LEXER, 0); }
		public ActionScopeNameContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionScopeName; }
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
		ActionScopeNameContext _localctx = new ActionScopeNameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_actionScopeName);
		try {
			setState(428);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(425); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(426); match(LEXER);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(427); match(PARSER);
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModeSpecContext extends ParserRuleContext<Token> {
		public List<? extends RuleSpecContext> ruleSpec() {
		    return getRuleContexts(RuleSpecContext.class);
		}
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public RuleSpecContext ruleSpec(int i) {
		    return getRuleContext(RuleSpecContext.class,i);
		}
		public TerminalNode<Token> SEMI() { return getToken(AbstractGrammarParser.SEMI, 0); }
		public TerminalNode<Token> MODE() { return getToken(AbstractGrammarParser.MODE, 0); }
		public ModeSpecContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modeSpec; }
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
		ModeSpecContext _localctx = new ModeSpecContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_modeSpec);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(430); match(MODE);
			setState(431); id();
			setState(432); match(SEMI);
			setState(434); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(433); ruleSpec();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(436); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RulesContext extends ParserRuleContext<Token> {
		public List<? extends RuleSpecContext> ruleSpec() {
		    return getRuleContexts(RuleSpecContext.class);
		}
		public RuleSpecContext ruleSpec(int i) {
		    return getRuleContext(RuleSpecContext.class,i);
		}
		public RulesContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rules; }
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
		RulesContext _localctx = new RulesContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_rules);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(438); ruleSpec();
					}
					} 
				}
				setState(443);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public RuleSpecContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleSpec; }
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
		RuleSpecContext _localctx = new RuleSpecContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_ruleSpec);
		try {
			setState(446);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(444); parserRuleSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(445); lexerRule();
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> DOC_COMMENT() { return getToken(AbstractGrammarParser.DOC_COMMENT, 0); }
		public TerminalNode<Token> COLON() { return getToken(AbstractGrammarParser.COLON, 0); }
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
		public TerminalNode<Token> SEMI() { return getToken(AbstractGrammarParser.SEMI, 0); }
		public TerminalNode<Token> RULE_REF() { return getToken(AbstractGrammarParser.RULE_REF, 0); }
		public ParserRuleSpecContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parserRuleSpec; }
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
		ParserRuleSpecContext _localctx = new ParserRuleSpecContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_parserRuleSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(448); match(DOC_COMMENT);
					}
					break;
			}
			setState(452);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(451); ruleModifiers();
					}
					break;
			}
			setState(454); _localctx.name = match(RULE_REF);
			setState(456);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(455); argActionParameters();
					}
					break;
			}
			setState(459);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					{
					setState(458); ruleReturns();
					}
					break;
			}
			setState(462);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(461); throwsSpec();
					}
					break;
			}
			setState(465);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					{
					setState(464); localsSpec();
					}
					break;
			}
			setState(467); rulePrequels();
			setState(468); match(COLON);
			setState(469); ruleBlock();
			setState(470); match(SEMI);
			setState(471); exceptionGroup();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public ExceptionHandlerContext exceptionHandler(int i) {
		    return getRuleContext(ExceptionHandlerContext.class,i);
		}
		public List<? extends ExceptionHandlerContext> exceptionHandler() {
		    return getRuleContexts(ExceptionHandlerContext.class);
		}
		public ExceptionGroupContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exceptionGroup; }
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
		ExceptionGroupContext _localctx = new ExceptionGroupContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_exceptionGroup);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(476);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(473); exceptionHandler();
					}
					} 
				}
				setState(478);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			}
			setState(480);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
				case 1:
					{
					setState(479); finallyClause();
					}
					break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> CATCH() { return getToken(AbstractGrammarParser.CATCH, 0); }
		public ExceptionHandlerContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exceptionHandler; }
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
		ExceptionHandlerContext _localctx = new ExceptionHandlerContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_exceptionHandler);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482); match(CATCH);
			setState(483); argActionBlock();
			setState(484); actionBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> FINALLY() { return getToken(AbstractGrammarParser.FINALLY, 0); }
		public FinallyClauseContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finallyClause; }
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
		FinallyClauseContext _localctx = new FinallyClauseContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_finallyClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(486); match(FINALLY);
			setState(487); actionBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RulePrequelsContext extends ParserRuleContext<Token> {
		public List<? extends RulePrequelContext> rulePrequel() {
		    return getRuleContexts(RulePrequelContext.class);
		}
		public RulePrequelContext rulePrequel(int i) {
		    return getRuleContext(RulePrequelContext.class,i);
		}
		public RulePrequelsContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rulePrequels; }
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
		RulePrequelsContext _localctx = new RulePrequelsContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_rulePrequels);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(492);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(489); rulePrequel();
					}
					} 
				}
				setState(494);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public RulePrequelContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rulePrequel; }
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
		RulePrequelContext _localctx = new RulePrequelContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_rulePrequel);
		try {
			setState(497);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(495); optionsSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(496); ruleAction();
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> RETURNS() { return getToken(AbstractGrammarParser.RETURNS, 0); }
		public RuleReturnsContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleReturns; }
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
		RuleReturnsContext _localctx = new RuleReturnsContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_ruleReturns);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(499); match(RETURNS);
			setState(500); argActionParameters();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThrowsSpecContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> THROWS() { return getToken(AbstractGrammarParser.THROWS, 0); }
		public TerminalNode<Token> COMMA(int i) {
		    return getToken(AbstractGrammarParser.COMMA, i);
		}
		public QidContext qid(int i) {
		    return getRuleContext(QidContext.class,i);
		}
		public List<? extends QidContext> qid() {
		    return getRuleContexts(QidContext.class);
		}
		public List<? extends TerminalNode<Token>> COMMA() { return getTokens(AbstractGrammarParser.COMMA); }
		public ThrowsSpecContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwsSpec; }
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
		ThrowsSpecContext _localctx = new ThrowsSpecContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_throwsSpec);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(502); match(THROWS);
			setState(503); qid();
			setState(508);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(504); match(COMMA);
					setState(505); qid();
					}
					} 
				}
				setState(510);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocalsSpecContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> LOCALS() { return getToken(AbstractGrammarParser.LOCALS, 0); }
		public ArgActionParametersContext argActionParameters() {
		    return getRuleContext(ArgActionParametersContext.class,0);
		}
		public LocalsSpecContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localsSpec; }
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
		LocalsSpecContext _localctx = new LocalsSpecContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_localsSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(511); match(LOCALS);
			setState(512); argActionParameters();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> AT() { return getToken(AbstractGrammarParser.AT, 0); }
		public RuleActionContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleAction; }
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
		RuleActionContext _localctx = new RuleActionContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_ruleAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514); match(AT);
			setState(515); id();
			setState(516); actionBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleModifiersContext extends ParserRuleContext<Token> {
		public List<? extends RuleModifierContext> ruleModifier() {
		    return getRuleContexts(RuleModifierContext.class);
		}
		public RuleModifierContext ruleModifier(int i) {
		    return getRuleContext(RuleModifierContext.class,i);
		}
		public RuleModifiersContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleModifiers; }
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
		RuleModifiersContext _localctx = new RuleModifiersContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_ruleModifiers);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(519); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(518); ruleModifier();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(521); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleModifierContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> PROTECTED() { return getToken(AbstractGrammarParser.PROTECTED, 0); }
		public TerminalNode<Token> PRIVATE() { return getToken(AbstractGrammarParser.PRIVATE, 0); }
		public TerminalNode<Token> PUBLIC() { return getToken(AbstractGrammarParser.PUBLIC, 0); }
		public TerminalNode<Token> FRAGMENT() { return getToken(AbstractGrammarParser.FRAGMENT, 0); }
		public RuleModifierContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleModifier; }
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
		RuleModifierContext _localctx = new RuleModifierContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_ruleModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(523);
			_input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==FRAGMENT || _la==PROTECTED || _la==PUBLIC || _la==PRIVATE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public RuleBlockContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleBlock; }
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
		RuleBlockContext _localctx = new RuleBlockContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_ruleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(525); ruleAltList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleAltListContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> OR(int i) {
		    return getToken(AbstractGrammarParser.OR, i);
		}
		public List<? extends LabeledAltContext> labeledAlt() {
		    return getRuleContexts(LabeledAltContext.class);
		}
		public LabeledAltContext labeledAlt(int i) {
		    return getRuleContext(LabeledAltContext.class,i);
		}
		public List<? extends TerminalNode<Token>> OR() { return getTokens(AbstractGrammarParser.OR); }
		public RuleAltListContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleAltList; }
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
		RuleAltListContext _localctx = new RuleAltListContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_ruleAltList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(527); labeledAlt();
			setState(532);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(528); match(OR);
					setState(529); labeledAlt();
					}
					} 
				}
				setState(534);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> POUND() { return getToken(AbstractGrammarParser.POUND, 0); }
		public AlternativeContext alternative() {
		    return getRuleContext(AlternativeContext.class,0);
		}
		public LabeledAltContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labeledAlt; }
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

	@RuleVersion(1)
	public final LabeledAltContext labeledAlt() throws RecognitionException {
		LabeledAltContext _localctx = new LabeledAltContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_labeledAlt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(535); alternative();
			setState(538);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
				case 1:
					{
					setState(536); match(POUND);
					setState(537); id();
					}
					break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> DOC_COMMENT() { return getToken(AbstractGrammarParser.DOC_COMMENT, 0); }
		public TerminalNode<Token> COLON() { return getToken(AbstractGrammarParser.COLON, 0); }
		public TerminalNode<Token> SEMI() { return getToken(AbstractGrammarParser.SEMI, 0); }
		public TerminalNode<Token> FRAGMENT() { return getToken(AbstractGrammarParser.FRAGMENT, 0); }
		public TerminalNode<Token> TOKEN_REF() { return getToken(AbstractGrammarParser.TOKEN_REF, 0); }
		public LexerRuleBlockContext lexerRuleBlock() {
		    return getRuleContext(LexerRuleBlockContext.class,0);
		}
		public LexerRuleContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerRule; }
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
		LexerRuleContext _localctx = new LexerRuleContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_lexerRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(541);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
				case 1:
					{
					setState(540); match(DOC_COMMENT);
					}
					break;
			}
			setState(544);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					{
					setState(543); match(FRAGMENT);
					}
					break;
			}
			setState(546); _localctx.name = match(TOKEN_REF);
			setState(547); match(COLON);
			setState(548); lexerRuleBlock();
			setState(549); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public LexerRuleBlockContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerRuleBlock; }
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
		LexerRuleBlockContext _localctx = new LexerRuleBlockContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_lexerRuleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(551); lexerAltList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerAltListContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> OR(int i) {
		    return getToken(AbstractGrammarParser.OR, i);
		}
		public LexerAltContext lexerAlt(int i) {
		    return getRuleContext(LexerAltContext.class,i);
		}
		public List<? extends TerminalNode<Token>> OR() { return getTokens(AbstractGrammarParser.OR); }
		public List<? extends LexerAltContext> lexerAlt() {
		    return getRuleContexts(LexerAltContext.class);
		}
		public LexerAltListContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerAltList; }
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
		LexerAltListContext _localctx = new LexerAltListContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_lexerAltList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(553); lexerAlt();
			setState(558);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(554); match(OR);
					setState(555); lexerAlt();
					}
					} 
				}
				setState(560);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public LexerAltContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerAlt; }
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
		LexerAltContext _localctx = new LexerAltContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_lexerAlt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(561); lexerElements();
			setState(564);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
				case 1:
					{
					setState(562); lexerCommands();
					}
					break;

				case 2:
					{
					}
					break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerElementsContext extends ParserRuleContext<Token> {
		public LexerElementContext lexerElement(int i) {
		    return getRuleContext(LexerElementContext.class,i);
		}
		public List<? extends LexerElementContext> lexerElement() {
		    return getRuleContexts(LexerElementContext.class);
		}
		public LexerElementsContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerElements; }
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
		LexerElementsContext _localctx = new LexerElementsContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_lexerElements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(567); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(566); lexerElement();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(569); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> QUESTION() { return getToken(AbstractGrammarParser.QUESTION, 0); }
		public LabeledLexerElementContext labeledLexerElement() {
		    return getRuleContext(LabeledLexerElementContext.class,0);
		}
		public LexerAtomContext lexerAtom() {
		    return getRuleContext(LexerAtomContext.class,0);
		}
		public LexerElementContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerElement; }
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
		LexerElementContext _localctx = new LexerElementContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_lexerElement);
		try {
			setState(590);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(571); labeledLexerElement();
					setState(574);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
						case 1:
							{
							setState(572); ebnfSuffix();
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
					setState(576); lexerAtom();
					setState(579);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
						case 1:
							{
							setState(577); ebnfSuffix();
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
					setState(581); lexerBlock();
					setState(584);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
						case 1:
							{
							setState(582); ebnfSuffix();
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
					setState(586); actionBlock();
					setState(588);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
						case 1:
							{
							setState(587); match(QUESTION);
							}
							break;
					}
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> PLUS_ASSIGN() { return getToken(AbstractGrammarParser.PLUS_ASSIGN, 0); }
		public TerminalNode<Token> ASSIGN() { return getToken(AbstractGrammarParser.ASSIGN, 0); }
		public LabeledLexerElementContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labeledLexerElement; }
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
		LabeledLexerElementContext _localctx = new LabeledLexerElementContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_labeledLexerElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(592); id();
			setState(595);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
				case 1:
					{
					setState(593); _localctx.ass = match(ASSIGN);
					}
					break;

				case 2:
					{
					setState(594); _localctx.ass = match(PLUS_ASSIGN);
					}
					break;
			}
			setState(599);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
				case 1:
					{
					setState(597); lexerAtom();
					}
					break;

				case 2:
					{
					setState(598); block();
					}
					break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerBlockContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> RPAREN() { return getToken(AbstractGrammarParser.RPAREN, 0); }
		public LexerAltListContext lexerAltList() {
		    return getRuleContext(LexerAltListContext.class,0);
		}
		public TerminalNode<Token> LPAREN() { return getToken(AbstractGrammarParser.LPAREN, 0); }
		public LexerBlockContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerBlock; }
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
		LexerBlockContext _localctx = new LexerBlockContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_lexerBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(601); match(LPAREN);
			setState(602); lexerAltList();
			setState(603); match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerCommandsContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> COMMA(int i) {
		    return getToken(AbstractGrammarParser.COMMA, i);
		}
		public List<? extends LexerCommandContext> lexerCommand() {
		    return getRuleContexts(LexerCommandContext.class);
		}
		public List<? extends TerminalNode<Token>> COMMA() { return getTokens(AbstractGrammarParser.COMMA); }
		public LexerCommandContext lexerCommand(int i) {
		    return getRuleContext(LexerCommandContext.class,i);
		}
		public TerminalNode<Token> RARROW() { return getToken(AbstractGrammarParser.RARROW, 0); }
		public LexerCommandsContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerCommands; }
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
		LexerCommandsContext _localctx = new LexerCommandsContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_lexerCommands);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(605); match(RARROW);
			setState(606); lexerCommand();
			setState(611);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(607); match(COMMA);
					setState(608); lexerCommand();
					}
					} 
				}
				setState(613);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerCommandContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> RPAREN() { return getToken(AbstractGrammarParser.RPAREN, 0); }
		public LexerCommandExprContext lexerCommandExpr() {
		    return getRuleContext(LexerCommandExprContext.class,0);
		}
		public LexerCommandNameContext lexerCommandName() {
		    return getRuleContext(LexerCommandNameContext.class,0);
		}
		public TerminalNode<Token> LPAREN() { return getToken(AbstractGrammarParser.LPAREN, 0); }
		public LexerCommandContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerCommand; }
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

	@RuleVersion(1)
	public final LexerCommandContext lexerCommand() throws RecognitionException {
		LexerCommandContext _localctx = new LexerCommandContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_lexerCommand);
		try {
			setState(620);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(614); lexerCommandName();
					setState(615); match(LPAREN);
					setState(616); lexerCommandExpr();
					setState(617); match(RPAREN);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(619); lexerCommandName();
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerCommandNameContext extends ParserRuleContext<Token> {
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
		}
		public TerminalNode<Token> MODE() { return getToken(AbstractGrammarParser.MODE, 0); }
		public LexerCommandNameContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerCommandName; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).enterLexerCommandName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GrammarParserListener ) ((GrammarParserListener)listener).exitLexerCommandName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GrammarParserVisitor<?, ?> ) return ((GrammarParserVisitor<? super Token, ? extends Result>)visitor).visitLexerCommandName(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LexerCommandNameContext lexerCommandName() throws RecognitionException {
		LexerCommandNameContext _localctx = new LexerCommandNameContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_lexerCommandName);
		try {
			setState(624);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(622); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(623); match(MODE);
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> INT() { return getToken(AbstractGrammarParser.INT, 0); }
		public LexerCommandExprContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerCommandExpr; }
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
		LexerCommandExprContext _localctx = new LexerCommandExprContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_lexerCommandExpr);
		try {
			setState(628);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(626); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(627); match(INT);
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> OR(int i) {
		    return getToken(AbstractGrammarParser.OR, i);
		}
		public List<? extends AlternativeContext> alternative() {
		    return getRuleContexts(AlternativeContext.class);
		}
		public List<? extends TerminalNode<Token>> OR() { return getTokens(AbstractGrammarParser.OR); }
		public AltListContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_altList; }
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
		AltListContext _localctx = new AltListContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_altList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(630); alternative();
			setState(635);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(631); match(OR);
					setState(632); alternative();
					}
					} 
				}
				setState(637);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public AlternativeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alternative; }
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
		AlternativeContext _localctx = new AlternativeContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_alternative);
		try {
			setState(640);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(638); elements();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementsContext extends ParserRuleContext<Token> {
		public List<? extends ElementContext> element() {
		    return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
		    return getRuleContext(ElementContext.class,i);
		}
		public ElementsContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elements; }
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
		ElementsContext _localctx = new ElementsContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_elements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(643); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(642); element();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(645); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> QUESTION() { return getToken(AbstractGrammarParser.QUESTION, 0); }
		public LabeledElementContext labeledElement() {
		    return getRuleContext(LabeledElementContext.class,0);
		}
		public ElementContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
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
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_element);
		try {
			setState(662);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(647); labeledElement();
					setState(650);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
						case 1:
							{
							setState(648); ebnfSuffix();
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
					setState(652); atom();
					setState(655);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
						case 1:
							{
							setState(653); ebnfSuffix();
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
					setState(657); ebnf();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(658); actionBlock();
					setState(660);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
						case 1:
							{
							setState(659); match(QUESTION);
							}
							break;
					}
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> PLUS_ASSIGN() { return getToken(AbstractGrammarParser.PLUS_ASSIGN, 0); }
		public TerminalNode<Token> ASSIGN() { return getToken(AbstractGrammarParser.ASSIGN, 0); }
		public LabeledElementContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labeledElement; }
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
		LabeledElementContext _localctx = new LabeledElementContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_labeledElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(664); _localctx.label = id();
			setState(667);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
				case 1:
					{
					setState(665); _localctx.ass = match(ASSIGN);
					}
					break;

				case 2:
					{
					setState(666); _localctx.ass = match(PLUS_ASSIGN);
					}
					break;
			}
			setState(671);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					{
					setState(669); atom();
					}
					break;

				case 2:
					{
					setState(670); block();
					}
					break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public EbnfContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ebnf; }
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
		EbnfContext _localctx = new EbnfContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_ebnf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(673); block();
			setState(676);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
				case 1:
					{
					setState(674); blockSuffix();
					}
					break;

				case 2:
					{
					}
					break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public BlockSuffixContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockSuffix; }
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
		BlockSuffixContext _localctx = new BlockSuffixContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_blockSuffix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(678); ebnfSuffix();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EbnfSuffixContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> PLUS() { return getToken(AbstractGrammarParser.PLUS, 0); }
		public TerminalNode<Token> STAR() { return getToken(AbstractGrammarParser.STAR, 0); }
		public TerminalNode<Token> QUESTION() { return getToken(AbstractGrammarParser.QUESTION, 0); }
		public EbnfSuffixContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ebnfSuffix; }
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
		EbnfSuffixContext _localctx = new EbnfSuffixContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_ebnfSuffix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(680);
			_input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==QUESTION || _la==STAR || _la==PLUS) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LexerAtomContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> LEXER_CHAR_SET() { return getToken(AbstractGrammarParser.LEXER_CHAR_SET, 0); }
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
		public TerminalNode<Token> DOT() { return getToken(AbstractGrammarParser.DOT, 0); }
		public TerminalNode<Token> RULE_REF() { return getToken(AbstractGrammarParser.RULE_REF, 0); }
		public LexerAtomContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lexerAtom; }
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

	@RuleVersion(1)
	public final LexerAtomContext lexerAtom() throws RecognitionException {
		LexerAtomContext _localctx = new LexerAtomContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_lexerAtom);
		try {
			setState(691);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(682); range();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(683); terminal();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(684); match(RULE_REF);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(685); notSet();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(686); match(LEXER_CHAR_SET);
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(687); match(DOT);
					setState(689);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
						case 1:
							{
							setState(688); elementOptions();
							}
							break;
					}
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> DOT() { return getToken(AbstractGrammarParser.DOT, 0); }
		public RulerefContext ruleref() {
		    return getRuleContext(RulerefContext.class,0);
		}
		public AtomContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
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
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_atom);
		try {
			setState(701);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(693); range();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(694); terminal();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(695); ruleref();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(696); notSet();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(697); match(DOT);
					setState(699);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
						case 1:
							{
							setState(698); elementOptions();
							}
							break;
					}
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> NOT() { return getToken(AbstractGrammarParser.NOT, 0); }
		public NotSetContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notSet; }
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
		NotSetContext _localctx = new NotSetContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_notSet);
		try {
			setState(707);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(703); match(NOT);
					setState(704); setElement();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(705); match(NOT);
					setState(706); blockSet();
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockSetContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> RPAREN() { return getToken(AbstractGrammarParser.RPAREN, 0); }
		public List<? extends SetElementContext> setElement() {
		    return getRuleContexts(SetElementContext.class);
		}
		public TerminalNode<Token> OR(int i) {
		    return getToken(AbstractGrammarParser.OR, i);
		}
		public SetElementContext setElement(int i) {
		    return getRuleContext(SetElementContext.class,i);
		}
		public TerminalNode<Token> LPAREN() { return getToken(AbstractGrammarParser.LPAREN, 0); }
		public List<? extends TerminalNode<Token>> OR() { return getTokens(AbstractGrammarParser.OR); }
		public BlockSetContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockSet; }
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
		BlockSetContext _localctx = new BlockSetContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_blockSet);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(709); match(LPAREN);
			setState(710); setElement();
			setState(715);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(711); match(OR);
					setState(712); setElement();
					}
					} 
				}
				setState(717);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			}
			setState(718); match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetElementContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> LEXER_CHAR_SET() { return getToken(AbstractGrammarParser.LEXER_CHAR_SET, 0); }
		public RangeContext range() {
		    return getRuleContext(RangeContext.class,0);
		}
		public TerminalNode<Token> STRING_LITERAL() { return getToken(AbstractGrammarParser.STRING_LITERAL, 0); }
		public TerminalNode<Token> TOKEN_REF() { return getToken(AbstractGrammarParser.TOKEN_REF, 0); }
		public SetElementContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setElement; }
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

	@RuleVersion(2)
	public final SetElementContext setElement() throws RecognitionException {
		SetElementContext _localctx = new SetElementContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_setElement);
		try {
			setState(724);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(720); match(TOKEN_REF);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(721); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(722); range();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(723); match(LEXER_CHAR_SET);
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext<Token> {
		public RuleActionContext ruleAction(int i) {
		    return getRuleContext(RuleActionContext.class,i);
		}
		public TerminalNode<Token> COLON() { return getToken(AbstractGrammarParser.COLON, 0); }
		public TerminalNode<Token> RPAREN() { return getToken(AbstractGrammarParser.RPAREN, 0); }
		public OptionsSpecContext optionsSpec() {
		    return getRuleContext(OptionsSpecContext.class,0);
		}
		public AltListContext altList() {
		    return getRuleContext(AltListContext.class,0);
		}
		public TerminalNode<Token> LPAREN() { return getToken(AbstractGrammarParser.LPAREN, 0); }
		public List<? extends RuleActionContext> ruleAction() {
		    return getRuleContexts(RuleActionContext.class);
		}
		public BlockContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
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
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_block);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(726); match(LPAREN);
			setState(737);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
				case 1:
					{
					setState(728);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
						case 1:
							{
							setState(727); optionsSpec();
							}
							break;
					}
					setState(733);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(730); ruleAction();
							}
							} 
						}
						setState(735);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
					}
					setState(736); match(COLON);
					}
					break;
			}
			setState(739); altList();
			setState(740); match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> RULE_REF() { return getToken(AbstractGrammarParser.RULE_REF, 0); }
		public RulerefContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleref; }
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
		RulerefContext _localctx = new RulerefContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_ruleref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(742); match(RULE_REF);
			setState(744);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
				case 1:
					{
					setState(743); argActionBlock();
					}
					break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RangeContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> RANGE() { return getToken(AbstractGrammarParser.RANGE, 0); }
		public List<? extends TerminalNode<Token>> STRING_LITERAL() { return getTokens(AbstractGrammarParser.STRING_LITERAL); }
		public TerminalNode<Token> STRING_LITERAL(int i) {
		    return getToken(AbstractGrammarParser.STRING_LITERAL, i);
		}
		public RangeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
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
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(746); match(STRING_LITERAL);
			setState(747); match(RANGE);
			setState(748); match(STRING_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TerminalContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> STRING_LITERAL() { return getToken(AbstractGrammarParser.STRING_LITERAL, 0); }
		public ElementOptionsContext elementOptions() {
		    return getRuleContext(ElementOptionsContext.class,0);
		}
		public TerminalNode<Token> TOKEN_REF() { return getToken(AbstractGrammarParser.TOKEN_REF, 0); }
		public TerminalContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminal; }
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
		TerminalContext _localctx = new TerminalContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_terminal);
		try {
			setState(758);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(750); match(TOKEN_REF);
					setState(752);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
						case 1:
							{
							setState(751); elementOptions();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(754); match(STRING_LITERAL);
					setState(756);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
						case 1:
							{
							setState(755); elementOptions();
							}
							break;
					}
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementOptionsContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> GT() { return getToken(AbstractGrammarParser.GT, 0); }
		public TerminalNode<Token> LT() { return getToken(AbstractGrammarParser.LT, 0); }
		public TerminalNode<Token> COMMA(int i) {
		    return getToken(AbstractGrammarParser.COMMA, i);
		}
		public List<? extends ElementOptionContext> elementOption() {
		    return getRuleContexts(ElementOptionContext.class);
		}
		public List<? extends TerminalNode<Token>> COMMA() { return getTokens(AbstractGrammarParser.COMMA); }
		public ElementOptionContext elementOption(int i) {
		    return getRuleContext(ElementOptionContext.class,i);
		}
		public ElementOptionsContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementOptions; }
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
		ElementOptionsContext _localctx = new ElementOptionsContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_elementOptions);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(760); match(LT);
			setState(761); elementOption();
			setState(766);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,97,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(762); match(COMMA);
					setState(763); elementOption();
					}
					} 
				}
				setState(768);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,97,_ctx);
			}
			setState(769); match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> STRING_LITERAL() { return getToken(AbstractGrammarParser.STRING_LITERAL, 0); }
		public QidContext qid() {
		    return getRuleContext(QidContext.class,0);
		}
		public TerminalNode<Token> ASSIGN() { return getToken(AbstractGrammarParser.ASSIGN, 0); }
		public ElementOptionContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementOption; }
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
		ElementOptionContext _localctx = new ElementOptionContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_elementOption);
		try {
			setState(778);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(771); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(772); id();
					setState(773); match(ASSIGN);
					setState(776);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
						case 1:
							{
							setState(774); qid();
							}
							break;

						case 2:
							{
							setState(775); match(STRING_LITERAL);
							}
							break;
					}
					}
					break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> TEMPLATE() { return getToken(AbstractGrammarParser.TEMPLATE, 0); }
		public TerminalNode<Token> RULE_REF() { return getToken(AbstractGrammarParser.RULE_REF, 0); }
		public TerminalNode<Token> TOKEN_REF() { return getToken(AbstractGrammarParser.TOKEN_REF, 0); }
		public IdContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
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

	@RuleVersion(1)
	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(780);
			_input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==TOKEN_REF || _la==RULE_REF || _la==TEMPLATE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
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
		public TerminalNode<Token> DOT(int i) {
		    return getToken(AbstractGrammarParser.DOT, i);
		}
		public List<? extends TerminalNode<Token>> DOT() { return getTokens(AbstractGrammarParser.DOT); }
		public IdContext id(int i) {
		    return getRuleContext(IdContext.class,i);
		}
		public QidContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qid; }
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
		QidContext _localctx = new QidContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_qid);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(782); id();
			setState(787);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,100,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(783); match(DOT);
					setState(784); id();
					}
					} 
				}
				setState(789);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,100,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\1s\u0317\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7"+
		"\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16\7\16\2\17"+
		"\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25\7\25\2\26"+
		"\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34\7\34\2\35"+
		"\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7%\2&\7&"+
		"\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2.\7.\2/\7/\2\60\7\60\2\61"+
		"\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65\2\66\7\66\2\67\7\67\28\7"+
		"8\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2@\7@\2A\7A\2B\7B\2C\7C\2"+
		"D\7D\2E\7E\2F\7F\2G\7G\1\0\3\0\u0092\b\0\1\0\1\0\1\0\1\0\5\0\u0098\b\0"+
		"\n\0\f\0\u009b\t\0\1\0\1\0\5\0\u009f\b\0\n\0\f\0\u00a2\t\0\1\0\1\0\1\1"+
		"\1\1\1\1\1\1\1\1\3\1\u00ab\b\1\1\2\1\2\1\2\1\2\3\2\u00b1\b\2\1\3\1\3\1"+
		"\3\1\3\5\3\u00b7\b\3\n\3\f\3\u00ba\t\3\1\3\1\3\1\4\1\4\1\4\1\4\1\5\1\5"+
		"\1\5\1\5\3\5\u00c6\b\5\1\6\1\6\1\6\1\6\5\6\u00cc\b\6\n\6\f\6\u00cf\t\6"+
		"\1\6\1\6\1\7\1\7\1\7\1\7\1\7\3\7\u00d8\b\7\1\b\1\b\4\b\u00dc\b\b\13\b"+
		"\f\b\u00dd\1\b\1\b\1\t\1\t\1\t\1\t\3\t\u00e6\b\t\1\t\1\t\1\t\3\t\u00eb"+
		"\b\t\1\n\1\n\1\n\1\n\1\n\1\n\1\n\1\n\1\n\1\n\1\n\1\n\1\n\1\n\1\n\1\n\1"+
		"\n\1\n\1\n\1\n\1\n\1\n\1\n\1\n\5\n\u0105\b\n\n\n\f\n\u0108\t\n\1\n\1\n"+
		"\1\13\1\13\5\13\u010e\b\13\n\13\f\13\u0111\t\13\1\13\1\13\5\13\u0115\b"+
		"\13\n\13\f\13\u0118\t\13\1\13\1\13\1\f\1\f\5\f\u011e\b\f\n\f\f\f\u0121"+
		"\t\f\1\f\1\f\5\f\u0125\b\f\n\f\f\f\u0128\t\f\1\f\1\f\5\f\u012c\b\f\n\f"+
		"\f\f\u012f\t\f\3\f\u0131\b\f\1\f\1\f\5\f\u0135\b\f\n\f\f\f\u0138\t\f\1"+
		"\f\1\f\5\f\u013c\b\f\n\f\f\f\u013f\t\f\3\f\u0141\b\f\1\f\1\f\5\f\u0145"+
		"\b\f\n\f\f\f\u0148\t\f\1\f\1\f\1\r\1\r\5\r\u014e\b\r\n\r\f\r\u0151\t\r"+
		"\1\r\1\r\1\16\1\16\5\16\u0157\b\16\n\16\f\16\u015a\t\16\1\16\1\16\5\16"+
		"\u015e\b\16\n\16\f\16\u0161\t\16\1\16\1\16\5\16\u0165\b\16\n\16\f\16\u0168"+
		"\t\16\1\16\1\16\5\16\u016c\b\16\n\16\f\16\u016f\t\16\5\16\u0171\b\16\n"+
		"\16\f\16\u0174\t\16\3\16\u0176\b\16\1\16\1\16\1\17\3\17\u017b\b\17\1\17"+
		"\5\17\u017e\b\17\n\17\f\17\u0181\t\17\1\17\1\17\1\20\1\20\5\20\u0187\b"+
		"\20\n\20\f\20\u018a\t\20\1\20\5\20\u018d\b\20\n\20\f\20\u0190\t\20\1\21"+
		"\1\21\1\21\3\21\u0195\b\21\1\21\1\21\1\21\3\21\u019a\b\21\1\21\3\21\u019d"+
		"\b\21\1\22\1\22\1\23\1\23\1\23\1\23\3\23\u01a5\b\23\1\23\1\23\1\23\1\24"+
		"\1\24\1\24\3\24\u01ad\b\24\1\25\1\25\1\25\1\25\4\25\u01b3\b\25\13\25\f"+
		"\25\u01b4\1\26\5\26\u01b8\b\26\n\26\f\26\u01bb\t\26\1\27\1\27\3\27\u01bf"+
		"\b\27\1\30\3\30\u01c2\b\30\1\30\3\30\u01c5\b\30\1\30\1\30\3\30\u01c9\b"+
		"\30\1\30\3\30\u01cc\b\30\1\30\3\30\u01cf\b\30\1\30\3\30\u01d2\b\30\1\30"+
		"\1\30\1\30\1\30\1\30\1\30\1\31\5\31\u01db\b\31\n\31\f\31\u01de\t\31\1"+
		"\31\3\31\u01e1\b\31\1\32\1\32\1\32\1\32\1\33\1\33\1\33\1\34\5\34\u01eb"+
		"\b\34\n\34\f\34\u01ee\t\34\1\35\1\35\3\35\u01f2\b\35\1\36\1\36\1\36\1"+
		"\37\1\37\1\37\1\37\5\37\u01fb\b\37\n\37\f\37\u01fe\t\37\1 \1 \1 \1!\1"+
		"!\1!\1!\1\"\4\"\u0208\b\"\13\"\f\"\u0209\1#\1#\1$\1$\1%\1%\1%\5%\u0213"+
		"\b%\n%\f%\u0216\t%\1&\1&\1&\3&\u021b\b&\1\'\3\'\u021e\b\'\1\'\3\'\u0221"+
		"\b\'\1\'\1\'\1\'\1\'\1\'\1(\1(\1)\1)\1)\5)\u022d\b)\n)\f)\u0230\t)\1*"+
		"\1*\1*\3*\u0235\b*\1+\4+\u0238\b+\13+\f+\u0239\1,\1,\1,\3,\u023f\b,\1"+
		",\1,\1,\3,\u0244\b,\1,\1,\1,\3,\u0249\b,\1,\1,\3,\u024d\b,\3,\u024f\b"+
		",\1-\1-\1-\3-\u0254\b-\1-\1-\3-\u0258\b-\1.\1.\1.\1.\1/\1/\1/\1/\5/\u0262"+
		"\b/\n/\f/\u0265\t/\1\60\1\60\1\60\1\60\1\60\1\60\3\60\u026d\b\60\1\61"+
		"\1\61\3\61\u0271\b\61\1\62\1\62\3\62\u0275\b\62\1\63\1\63\1\63\5\63\u027a"+
		"\b\63\n\63\f\63\u027d\t\63\1\64\1\64\3\64\u0281\b\64\1\65\4\65\u0284\b"+
		"\65\13\65\f\65\u0285\1\66\1\66\1\66\3\66\u028b\b\66\1\66\1\66\1\66\3\66"+
		"\u0290\b\66\1\66\1\66\1\66\3\66\u0295\b\66\3\66\u0297\b\66\1\67\1\67\1"+
		"\67\3\67\u029c\b\67\1\67\1\67\3\67\u02a0\b\67\18\18\18\38\u02a5\b8\19"+
		"\19\1:\1:\1;\1;\1;\1;\1;\1;\1;\3;\u02b2\b;\3;\u02b4\b;\1<\1<\1<\1<\1<"+
		"\1<\3<\u02bc\b<\3<\u02be\b<\1=\1=\1=\1=\3=\u02c4\b=\1>\1>\1>\1>\5>\u02ca"+
		"\b>\n>\f>\u02cd\t>\1>\1>\1?\1?\1?\1?\3?\u02d5\b?\1@\1@\3@\u02d9\b@\1@"+
		"\5@\u02dc\b@\n@\f@\u02df\t@\1@\3@\u02e2\b@\1@\1@\1@\1A\1A\3A\u02e9\bA"+
		"\1B\1B\1B\1B\1C\1C\3C\u02f1\bC\1C\1C\3C\u02f5\bC\3C\u02f7\bC\1D\1D\1D"+
		"\1D\5D\u02fd\bD\nD\fD\u0300\tD\1D\1D\1E\1E\1E\1E\1E\3E\u0309\bE\3E\u030b"+
		"\bE\1F\1F\1G\1G\1G\5G\u0312\bG\nG\fG\u0315\tG\1GH\0\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhj"+
		"lnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e\0\5\18C\3B"+
		"CTTWX\2\16\16\22\24\1&(\2\1\2\32\32\u03f2\0\u0091\1\0\0\0\1\u0316\5\uffff"+
		"\0\0\2\u00aa\1\0\0\0\3\u0094\1\0\0\0\4\u00b0\1\0\0\0\5\u0098\1\0\0\0\6"+
		"\u00b2\1\0\0\0\7\u00b1\1\0\0\0\7\u01f2\1\0\0\0\7\u02d9\1\0\0\0\b\u00bd"+
		"\1\0\0\0\t\u00b4\1\0\0\0\n\u00c5\1\0\0\0\13\u00c0\1\0\0\0\f\u00c7\1\0"+
		"\0\0\r\u00b1\1\0\0\0\16\u00d7\1\0\0\0\17\u00cd\1\0\0\0\17\u00cc\1\0\0"+
		"\0\20\u00d9\1\0\0\0\21\u00b1\1\0\0\0\22\u00ea\1\0\0\0\23\u00dc\1\0\0\0"+
		"\24\u00ec\1\0\0\0\25\u0105\1\0\0\0\25\u01a8\1\0\0\0\25\u01e5\1\0\0\0\25"+
		"\u01e8\1\0\0\0\25\u0205\1\0\0\0\25\u024c\1\0\0\0\25\u0294\1\0\0\0\26\u010b"+
		"\1\0\0\0\27\u0105\1\0\0\0\30\u011b\1\0\0\0\31\u0105\1\0\0\0\32\u014b\1"+
		"\0\0\0\33\u01e4\1\0\0\0\33\u02e9\1\0\0\0\34\u0154\1\0\0\0\35\u01c9\1\0"+
		"\0\0\35\u01f5\1\0\0\0\35\u0201\1\0\0\0\36\u017a\1\0\0\0\37\u015f\1\0\0"+
		"\0\37\u016d\1\0\0\0 \u0184\1\0\0\0!\u017b\1\0\0\0!\u0195\1\0\0\0!\u019a"+
		"\1\0\0\0\"\u019c\1\0\0\0#\u018e\1\0\0\0#\u018d\1\0\0\0$\u019e\1\0\0\0"+
		"%\u010e\1\0\0\0%\u0115\1\0\0\0%\u011e\1\0\0\0%\u0125\1\0\0\0%\u012c\1"+
		"\0\0\0%\u0135\1\0\0\0%\u013c\1\0\0\0%\u0145\1\0\0\0%\u0157\1\0\0\0%\u015e"+
		"\1\0\0\0%\u0165\1\0\0\0%\u016c\1\0\0\0%\u017e\1\0\0\0%\u0187\1\0\0\0&"+
		"\u01a0\1\0\0\0\'\u00b1\1\0\0\0(\u01ac\1\0\0\0)\u01a2\1\0\0\0*\u01ae\1"+
		"\0\0\0+\u009f\1\0\0\0,\u01b9\1\0\0\0-\u00a0\1\0\0\0.\u01be\1\0\0\0/\u01b3"+
		"\1\0\0\0/\u01b8\1\0\0\0\60\u01c1\1\0\0\0\61\u01bf\1\0\0\0\62\u01dc\1\0"+
		"\0\0\63\u01d8\1\0\0\0\64\u01e2\1\0\0\0\65\u01db\1\0\0\0\66\u01e6\1\0\0"+
		"\0\67\u01e1\1\0\0\08\u01ec\1\0\0\09\u01d4\1\0\0\0:\u01f1\1\0\0\0;\u01eb"+
		"\1\0\0\0<\u01f3\1\0\0\0=\u01cc\1\0\0\0>\u01f6\1\0\0\0?\u01cf\1\0\0\0@"+
		"\u01ff\1\0\0\0A\u01d2\1\0\0\0B\u0202\1\0\0\0C\u01f2\1\0\0\0C\u02dc\1\0"+
		"\0\0D\u0207\1\0\0\0E\u01c5\1\0\0\0F\u020b\1\0\0\0G\u0208\1\0\0\0H\u020d"+
		"\1\0\0\0I\u01d6\1\0\0\0J\u020f\1\0\0\0K\u020e\1\0\0\0L\u0217\1\0\0\0M"+
		"\u0214\1\0\0\0M\u0213\1\0\0\0N\u021d\1\0\0\0O\u01bf\1\0\0\0P\u0227\1\0"+
		"\0\0Q\u0225\1\0\0\0R\u0229\1\0\0\0S\u0228\1\0\0\0S\u025b\1\0\0\0T\u0231"+
		"\1\0\0\0U\u022e\1\0\0\0U\u022d\1\0\0\0V\u0237\1\0\0\0W\u0234\1\0\0\0X"+
		"\u024e\1\0\0\0Y\u0238\1\0\0\0Z\u0250\1\0\0\0[\u023e\1\0\0\0\\\u0259\1"+
		"\0\0\0]\u0248\1\0\0\0^\u025d\1\0\0\0_\u0235\1\0\0\0`\u026c\1\0\0\0a\u0263"+
		"\1\0\0\0a\u0262\1\0\0\0b\u0270\1\0\0\0c\u0267\1\0\0\0c\u026d\1\0\0\0d"+
		"\u0274\1\0\0\0e\u0269\1\0\0\0f\u0276\1\0\0\0g\u02e4\1\0\0\0h\u0280\1\0"+
		"\0\0i\u021a\1\0\0\0i\u027b\1\0\0\0i\u027a\1\0\0\0j\u0283\1\0\0\0k\u0281"+
		"\1\0\0\0l\u0296\1\0\0\0m\u0284\1\0\0\0n\u0298\1\0\0\0o\u028a\1\0\0\0p"+
		"\u02a1\1\0\0\0q\u0297\1\0\0\0r\u02a6\1\0\0\0s\u02a5\1\0\0\0t\u02a8\1\0"+
		"\0\0u\u023f\1\0\0\0u\u0244\1\0\0\0u\u0249\1\0\0\0u\u028b\1\0\0\0u\u0290"+
		"\1\0\0\0u\u02a7\1\0\0\0v\u02b3\1\0\0\0w\u0243\1\0\0\0w\u0258\1\0\0\0x"+
		"\u02bd\1\0\0\0y\u028f\1\0\0\0y\u02a0\1\0\0\0z\u02c3\1\0\0\0{\u02b4\1\0"+
		"\0\0{\u02be\1\0\0\0|\u02c5\1\0\0\0}\u02c4\1\0\0\0~\u02d4\1\0\0\0\177\u02c4"+
		"\1\0\0\0\177\u02cb\1\0\0\0\177\u02ca\1\0\0\0\u0080\u02d6\1\0\0\0\u0081"+
		"\u0258\1\0\0\0\u0081\u02a0\1\0\0\0\u0081\u02a4\1\0\0\0\u0082\u02e6\1\0"+
		"\0\0\u0083\u02be\1\0\0\0\u0084\u02ea\1\0\0\0\u0085\u02b4\1\0\0\0\u0085"+
		"\u02be\1\0\0\0\u0085\u02d5\1\0\0\0\u0086\u02f6\1\0\0\0\u0087\u02b4\1\0"+
		"\0\0\u0087\u02be\1\0\0\0\u0088\u02f8\1\0\0\0\u0089\u02b2\1\0\0\0\u0089"+
		"\u02bc\1\0\0\0\u0089\u02f1\1\0\0\0\u0089\u02f5\1\0\0\0\u008a\u030a\1\0"+
		"\0\0\u008b\u02fe\1\0\0\0\u008b\u02fd\1\0\0\0\u008c\u030c\1\0\0\0\u008d"+
		"\u0095\1\0\0\0\u008d\u00be\1\0\0\0\u008d\u00d3\1\0\0\0\u008d\u00d5\1\0"+
		"\0\0\u008d\u00d8\1\0\0\0\u008d\u00e5\1\0\0\0\u008d\u01a7\1\0\0\0\u008d"+
		"\u01ad\1\0\0\0\u008d\u01b0\1\0\0\0\u008d\u0204\1\0\0\0\u008d\u021b\1\0"+
		"\0\0\u008d\u0253\1\0\0\0\u008d\u0271\1\0\0\0\u008d\u0275\1\0\0\0\u008d"+
		"\u029b\1\0\0\0\u008d\u0305\1\0\0\0\u008d\u0313\1\0\0\0\u008d\u0312\1\0"+
		"\0\0\u008e\u030e\1\0\0\0\u008f\u00c6\1\0\0\0\u008f\u01fc\1\0\0\0\u008f"+
		"\u01fb\1\0\0\0\u008f\u030b\1\0\0\0\u008f\u0309\1\0\0\0\u0090\u0092\5\4"+
		"\0\0\u0091\u0090\1\0\0\0\u0091\u0092\1\0\0\0\u0092\u0093\1\0\0\0\u0093"+
		"\u0094\3\2\1\0\u0094\u0095\3\u008cF\0\u0095\u0099\5\37\0\0\u0096\u0098"+
		"\3\4\2\0\u0097\u0096\1\0\0\0\u0098\u009b\1\0\0\0\u0099\u0097\1\0\0\0\u0099"+
		"\u009a\1\0\0\0\u009a\u009c\1\0\0\0\u009b\u0099\1\0\0\0\u009c\u00a0\3,"+
		"\26\0\u009d\u009f\3*\25\0\u009e\u009d\1\0\0\0\u009f\u00a2\1\0\0\0\u00a0"+
		"\u009e\1\0\0\0\u00a0\u00a1\1\0\0\0\u00a1\u00a3\1\0\0\0\u00a2\u00a0\1\0"+
		"\0\0\u00a3\u00a4\5\uffff\0\0\u00a4\1\1\0\0\0\u00a5\u00a6\5\17\0\0\u00a6"+
		"\u00ab\5\21\0\0\u00a7\u00a8\5\20\0\0\u00a8\u00ab\5\21\0\0\u00a9\u00ab"+
		"\5\21\0\0\u00aa\u00a5\1\0\0\0\u00aa\u00a7\1\0\0\0\u00aa\u00a9\1\0\0\0"+
		"\u00ab\3\1\0\0\0\u00ac\u00b1\3\6\3\0\u00ad\u00b1\3\f\6\0\u00ae\u00b1\3"+
		"\20\b\0\u00af\u00b1\3&\23\0\u00b0\u00ac\1\0\0\0\u00b0\u00ad\1\0\0\0\u00b0"+
		"\u00ae\1\0\0\0\u00b0\u00af\1\0\0\0\u00b1\5\1\0\0\0\u00b2\u00b8\5\13\0"+
		"\0\u00b3\u00b4\3\b\4\0\u00b4\u00b5\5\37\0\0\u00b5\u00b7\1\0\0\0\u00b6"+
		"\u00b3\1\0\0\0\u00b7\u00ba\1\0\0\0\u00b8\u00b6\1\0\0\0\u00b8\u00b9\1\0"+
		"\0\0\u00b9\u00bb\1\0\0\0\u00ba\u00b8\1\0\0\0\u00bb\u00bc\5\62\0\0\u00bc"+
		"\7\1\0\0\0\u00bd\u00be\3\u008cF\0\u00be\u00bf\5%\0\0\u00bf\u00c0\3\n\5"+
		"\0\u00c0\t\1\0\0\0\u00c1\u00c6\3\u008eG\0\u00c2\u00c6\5\65\0\0\u00c3\u00c6"+
		"\5\64\0\0\u00c4\u00c6\5\'\0\0\u00c5\u00c1\1\0\0\0\u00c5\u00c2\1\0\0\0"+
		"\u00c5\u00c3\1\0\0\0\u00c5\u00c4\1\0\0\0\u00c6\13\1\0\0\0\u00c7\u00c8"+
		"\5\r\0\0\u00c8\u00cd\3\16\7\0\u00c9\u00ca\5\36\0\0\u00ca\u00cc\3\16\7"+
		"\0\u00cb\u00c9\1\0\0\0\u00cc\u00cf\1\0\0\0\u00cd\u00cb\1\0\0\0\u00cd\u00ce"+
		"\1\0\0\0\u00ce\u00d0\1\0\0\0\u00cf\u00cd\1\0\0\0\u00d0\u00d1\5\37\0\0"+
		"\u00d1\r\1\0\0\0\u00d2\u00d3\3\u008cF\0\u00d3\u00d4\5%\0\0\u00d4\u00d5"+
		"\3\u008cF\0\u00d5\u00d8\1\0\0\0\u00d6\u00d8\3\u008cF\0\u00d7\u00d2\1\0"+
		"\0\0\u00d7\u00d6\1\0\0\0\u00d8\17\1\0\0\0\u00d9\u00db\5\f\0\0\u00da\u00dc"+
		"\3\22\t\0\u00db\u00da\1\0\0\0\u00dc\u00dd\1\0\0\0\u00dd\u00db\1\0\0\0"+
		"\u00dd\u00de\1\0\0\0\u00de\u00df\1\0\0\0\u00df\u00e0\5\62\0\0\u00e0\21"+
		"\1\0\0\0\u00e1\u00e5\3\u008cF\0\u00e2\u00e3\5%\0\0\u00e3\u00e6\5\65\0"+
		"\0\u00e4\u00e6\1\0\0\0\u00e5\u00e2\1\0\0\0\u00e5\u00e4\1\0\0\0\u00e6\u00e7"+
		"\1\0\0\0\u00e7\u00e8\5\37\0\0\u00e8\u00eb\1\0\0\0\u00e9\u00eb\5\2\0\0"+
		"\u00ea\u00e1\1\0\0\0\u00ea\u00e9\1\0\0\0\u00eb\23\1\0\0\0\u00ec\u0106"+
		"\5\n\0\0\u00ed\u0105\3\24\n\0\u00ee\u0105\3\26\13\0\u00ef\u0105\3\30\f"+
		"\0\u00f0\u0105\5W\0\0\u00f1\u0105\5X\0\0\u00f2\u0105\5T\0\0\u00f3\u0105"+
		"\5U\0\0\u00f4\u0105\5V\0\0\u00f5\u0105\5F\0\0\u00f6\u0105\5G\0\0\u00f7"+
		"\u0105\5H\0\0\u00f8\u0105\5I\0\0\u00f9\u0105\5J\0\0\u00fa\u0105\5K\0\0"+
		"\u00fb\u0105\5L\0\0\u00fc\u0105\5M\0\0\u00fd\u0105\5Q\0\0\u00fe\u0105"+
		"\5R\0\0\u00ff\u0105\5S\0\0\u0100\u0105\5O\0\0\u0101\u0105\5N\0\0\u0102"+
		"\u0105\5P\0\0\u0103\u0105\5E\0\0\u0104\u00ed\1\0\0\0\u0104\u00ee\1\0\0"+
		"\0\u0104\u00ef\1\0\0\0\u0104\u00f0\1\0\0\0\u0104\u00f1\1\0\0\0\u0104\u00f2"+
		"\1\0\0\0\u0104\u00f3\1\0\0\0\u0104\u00f4\1\0\0\0\u0104\u00f5\1\0\0\0\u0104"+
		"\u00f6\1\0\0\0\u0104\u00f7\1\0\0\0\u0104\u00f8\1\0\0\0\u0104\u00f9\1\0"+
		"\0\0\u0104\u00fa\1\0\0\0\u0104\u00fb\1\0\0\0\u0104\u00fc\1\0\0\0\u0104"+
		"\u00fd\1\0\0\0\u0104\u00fe\1\0\0\0\u0104\u00ff\1\0\0\0\u0104\u0100\1\0"+
		"\0\0\u0104\u0101\1\0\0\0\u0104\u0102\1\0\0\0\u0104\u0103\1\0\0\0\u0105"+
		"\u0108\1\0\0\0\u0106\u0104\1\0\0\0\u0106\u0107\1\0\0\0\u0107\u0109\1\0"+
		"\0\0\u0108\u0106\1\0\0\0\u0109\u010a\5Y\0\0\u010a\25\1\0\0\0\u010b\u010f"+
		"\5S\0\0\u010c\u010e\3$\22\0\u010d\u010c\1\0\0\0\u010e\u0111\1\0\0\0\u010f"+
		"\u010d\1\0\0\0\u010f\u0110\1\0\0\0\u0110\u0112\1\0\0\0\u0111\u010f\1\0"+
		"\0\0\u0112\u0116\5E\0\0\u0113\u0115\3$\22\0\u0114\u0113\1\0\0\0\u0115"+
		"\u0118\1\0\0\0\u0116\u0114\1\0\0\0\u0116\u0117\1\0\0\0\u0117\u0119\1\0"+
		"\0\0\u0118\u0116\1\0\0\0\u0119\u011a\5R\0\0\u011a\27\1\0\0\0\u011b\u011f"+
		"\5S\0\0\u011c\u011e\3$\22\0\u011d\u011c\1\0\0\0\u011e\u0121\1\0\0\0\u011f"+
		"\u011d\1\0\0\0\u011f\u0120\1\0\0\0\u0120\u0140\1\0\0\0\u0121\u011f\1\0"+
		"\0\0\u0122\u0126\5J\0\0\u0123\u0125\3$\22\0\u0124\u0123\1\0\0\0\u0125"+
		"\u0128\1\0\0\0\u0126\u0124\1\0\0\0\u0126\u0127\1\0\0\0\u0127\u0130\1\0"+
		"\0\0\u0128\u0126\1\0\0\0\u0129\u012d\5P\0\0\u012a\u012c\3$\22\0\u012b"+
		"\u012a\1\0\0\0\u012c\u012f\1\0\0\0\u012d\u012b\1\0\0\0\u012d\u012e\1\0"+
		"\0\0\u012e\u0131\1\0\0\0\u012f\u012d\1\0\0\0\u0130\u0129\1\0\0\0\u0130"+
		"\u0131\1\0\0\0\u0131\u0132\1\0\0\0\u0132\u0136\5R\0\0\u0133\u0135\3$\22"+
		"\0\u0134\u0133\1\0\0\0\u0135\u0138\1\0\0\0\u0136\u0134\1\0\0\0\u0136\u0137"+
		"\1\0\0\0\u0137\u0139\1\0\0\0\u0138\u0136\1\0\0\0\u0139\u013d\5K\0\0\u013a"+
		"\u013c\3$\22\0\u013b\u013a\1\0\0\0\u013c\u013f\1\0\0\0\u013d\u013b\1\0"+
		"\0\0\u013d\u013e\1\0\0\0\u013e\u0141\1\0\0\0\u013f\u013d\1\0\0\0\u0140"+
		"\u0122\1\0\0\0\u0140\u0141\1\0\0\0\u0141\u0142\1\0\0\0\u0142\u0146\5N"+
		"\0\0\u0143\u0145\3$\22\0\u0144\u0143\1\0\0\0\u0145\u0148\1\0\0\0\u0146"+
		"\u0144\1\0\0\0\u0146\u0147\1\0\0\0\u0147\u0149\1\0\0\0\u0148\u0146\1\0"+
		"\0\0\u0149\u014a\5R\0\0\u014a\31\1\0\0\0\u014b\u014f\5\t\0\0\u014c\u014e"+
		"\7\0\0\0\u014d\u014c\1\0\0\0\u014e\u0151\1\0\0\0\u014f\u014d\1\0\0\0\u014f"+
		"\u0150\1\0\0\0\u0150\u0152\1\0\0\0\u0151\u014f\1\0\0\0\u0152\u0153\5D"+
		"\0\0\u0153\33\1\0\0\0\u0154\u0158\5\t\0\0\u0155\u0157\3$\22\0\u0156\u0155"+
		"\1\0\0\0\u0157\u015a\1\0\0\0\u0158\u0156\1\0\0\0\u0158\u0159\1\0\0\0\u0159"+
		"\u0175\1\0\0\0\u015a\u0158\1\0\0\0\u015b\u015f\3\36\17\0\u015c\u015e\3"+
		"$\22\0\u015d\u015c\1\0\0\0\u015e\u0161\1\0\0\0\u015f\u015d\1\0\0\0\u015f"+
		"\u0160\1\0\0\0\u0160\u0172\1\0\0\0\u0161\u015f\1\0\0\0\u0162\u0166\5="+
		"\0\0\u0163\u0165\3$\22\0\u0164\u0163\1\0\0\0\u0165\u0168\1\0\0\0\u0166"+
		"\u0164\1\0\0\0\u0166\u0167\1\0\0\0\u0167\u0169\1\0\0\0\u0168\u0166\1\0"+
		"\0\0\u0169\u016d\3\36\17\0\u016a\u016c\3$\22\0\u016b\u016a\1\0\0\0\u016c"+
		"\u016f\1\0\0\0\u016d\u016b\1\0\0\0\u016d\u016e\1\0\0\0\u016e\u0171\1\0"+
		"\0\0\u016f\u016d\1\0\0\0\u0170\u0162\1\0\0\0\u0171\u0174\1\0\0\0\u0172"+
		"\u0170\1\0\0\0\u0172\u0173\1\0\0\0\u0173\u0176\1\0\0\0\u0174\u0172\1\0"+
		"\0\0\u0175\u015b\1\0\0\0\u0175\u0176\1\0\0\0\u0176\u0177\1\0\0\0\u0177"+
		"\u0178\5D\0\0\u0178\35\1\0\0\0\u0179\u017b\3 \20\0\u017a\u0179\1\0\0\0"+
		"\u017a\u017b\1\0\0\0\u017b\u017f\1\0\0\0\u017c\u017e\3$\22\0\u017d\u017c"+
		"\1\0\0\0\u017e\u0181\1\0\0\0\u017f\u017d\1\0\0\0\u017f\u0180\1\0\0\0\u0180"+
		"\u0182\1\0\0\0\u0181\u017f\1\0\0\0\u0182\u0183\5?\0\0\u0183\37\1\0\0\0"+
		"\u0184\u018e\3\"\21\0\u0185\u0187\3$\22\0\u0186\u0185\1\0\0\0\u0187\u018a"+
		"\1\0\0\0\u0188\u0186\1\0\0\0\u0188\u0189\1\0\0\0\u0189\u018b\1\0\0\0\u018a"+
		"\u0188\1\0\0\0\u018b\u018d\3\"\21\0\u018c\u0188\1\0\0\0\u018d\u0190\1"+
		"\0\0\0\u018e\u018c\1\0\0\0\u018e\u018f\1\0\0\0\u018f!\1\0\0\0\u0190\u018e"+
		"\1\0\0\0\u0191\u019d\5?\0\0\u0192\u0194\58\0\0\u0193\u0195\3 \20\0\u0194"+
		"\u0193\1\0\0\0\u0194\u0195\1\0\0\0\u0195\u0196\1\0\0\0\u0196\u019d\59"+
		"\0\0\u0197\u0199\5:\0\0\u0198\u019a\3 \20\0\u0199\u0198\1\0\0\0\u0199"+
		"\u019a\1\0\0\0\u019a\u019b\1\0\0\0\u019b\u019d\5;\0\0\u019c\u0191\1\0"+
		"\0\0\u019c\u0192\1\0\0\0\u019c\u0197\1\0\0\0\u019d#\1\0\0\0\u019e\u019f"+
		"\7\1\0\0\u019f%\1\0\0\0\u01a0\u01a4\5/\0\0\u01a1\u01a2\3(\24\0\u01a2\u01a3"+
		"\5\35\0\0\u01a3\u01a5\1\0\0\0\u01a4\u01a1\1\0\0\0\u01a4\u01a5\1\0\0\0"+
		"\u01a5\u01a6\1\0\0\0\u01a6\u01a7\3\u008cF\0\u01a7\u01a8\3\24\n\0\u01a8"+
		"\'\1\0\0\0\u01a9\u01ad\3\u008cF\0\u01aa\u01ad\5\17\0\0\u01ab\u01ad\5\20"+
		"\0\0\u01ac\u01a9\1\0\0\0\u01ac\u01aa\1\0\0\0\u01ac\u01ab\1\0\0\0\u01ad"+
		")\1\0\0\0\u01ae\u01af\5\33\0\0\u01af\u01b0\3\u008cF\0\u01b0\u01b2\5\37"+
		"\0\0\u01b1\u01b3\3.\27\0\u01b2\u01b1\1\0\0\0\u01b3\u01b4\1\0\0\0\u01b4"+
		"\u01b2\1\0\0\0\u01b4\u01b5\1\0\0\0\u01b5+\1\0\0\0\u01b6\u01b8\3.\27\0"+
		"\u01b7\u01b6\1\0\0\0\u01b8\u01bb\1\0\0\0\u01b9\u01b7\1\0\0\0\u01b9\u01ba"+
		"\1\0\0\0\u01ba-\1\0\0\0\u01bb\u01b9\1\0\0\0\u01bc\u01bf\3\60\30\0\u01bd"+
		"\u01bf\3N\'\0\u01be\u01bc\1\0\0\0\u01be\u01bd\1\0\0\0\u01bf/\1\0\0\0\u01c0"+
		"\u01c2\5\4\0\0\u01c1\u01c0\1\0\0\0\u01c1\u01c2\1\0\0\0\u01c2\u01c4\1\0"+
		"\0\0\u01c3\u01c5\3D\"\0\u01c4\u01c3\1\0\0\0\u01c4\u01c5\1\0\0\0\u01c5"+
		"\u01c6\1\0\0\0\u01c6\u01c8\5\2\0\0\u01c7\u01c9\3\34\16\0\u01c8\u01c7\1"+
		"\0\0\0\u01c8\u01c9\1\0\0\0\u01c9\u01cb\1\0\0\0\u01ca\u01cc\3<\36\0\u01cb"+
		"\u01ca\1\0\0\0\u01cb\u01cc\1\0\0\0\u01cc\u01ce\1\0\0\0\u01cd\u01cf\3>"+
		"\37\0\u01ce\u01cd\1\0\0\0\u01ce\u01cf\1\0\0\0\u01cf\u01d1\1\0\0\0\u01d0"+
		"\u01d2\3@ \0\u01d1\u01d0\1\0\0\0\u01d1\u01d2\1\0\0\0\u01d2\u01d3\1\0\0"+
		"\0\u01d3\u01d4\38\34\0\u01d4\u01d5\5\34\0\0\u01d5\u01d6\3H$\0\u01d6\u01d7"+
		"\5\37\0\0\u01d7\u01d8\3\62\31\0\u01d8\61\1\0\0\0\u01d9\u01db\3\64\32\0"+
		"\u01da\u01d9\1\0\0\0\u01db\u01de\1\0\0\0\u01dc\u01da\1\0\0\0\u01dc\u01dd"+
		"\1\0\0\0\u01dd\u01e0\1\0\0\0\u01de\u01dc\1\0\0\0\u01df\u01e1\3\66\33\0"+
		"\u01e0\u01df\1\0\0\0\u01e0\u01e1\1\0\0\0\u01e1\63\1\0\0\0\u01e2\u01e3"+
		"\5\30\0\0\u01e3\u01e4\3\32\r\0\u01e4\u01e5\3\24\n\0\u01e5\65\1\0\0\0\u01e6"+
		"\u01e7\5\31\0\0\u01e7\u01e8\3\24\n\0\u01e8\67\1\0\0\0\u01e9\u01eb\3:\35"+
		"\0\u01ea\u01e9\1\0\0\0\u01eb\u01ee\1\0\0\0\u01ec\u01ea\1\0\0\0\u01ec\u01ed"+
		"\1\0\0\0\u01ed9\1\0\0\0\u01ee\u01ec\1\0\0\0\u01ef\u01f2\3\6\3\0\u01f0"+
		"\u01f2\3B!\0\u01f1\u01ef\1\0\0\0\u01f1\u01f0\1\0\0\0\u01f2;\1\0\0\0\u01f3"+
		"\u01f4\5\25\0\0\u01f4\u01f5\3\34\16\0\u01f5=\1\0\0\0\u01f6\u01f7\5\27"+
		"\0\0\u01f7\u01fc\3\u008eG\0\u01f8\u01f9\5\36\0\0\u01f9\u01fb\3\u008eG"+
		"\0\u01fa\u01f8\1\0\0\0\u01fb\u01fe\1\0\0\0\u01fc\u01fa\1\0\0\0\u01fc\u01fd"+
		"\1\0\0\0\u01fd?\1\0\0\0\u01fe\u01fc\1\0\0\0\u01ff\u0200\5\26\0\0\u0200"+
		"\u0201\3\34\16\0\u0201A\1\0\0\0\u0202\u0203\5/\0\0\u0203\u0204\3\u008c"+
		"F\0\u0204\u0205\3\24\n\0\u0205C\1\0\0\0\u0206\u0208\3F#\0\u0207\u0206"+
		"\1\0\0\0\u0208\u0209\1\0\0\0\u0209\u0207\1\0\0\0\u0209\u020a\1\0\0\0\u020a"+
		"E\1\0\0\0\u020b\u020c\7\2\0\0\u020cG\1\0\0\0\u020d\u020e\3J%\0\u020eI"+
		"\1\0\0\0\u020f\u0214\3L&\0\u0210\u0211\5*\0\0\u0211\u0213\3L&\0\u0212"+
		"\u0210\1\0\0\0\u0213\u0216\1\0\0\0\u0214\u0212\1\0\0\0\u0214\u0215\1\0"+
		"\0\0\u0215K\1\0\0\0\u0216\u0214\1\0\0\0\u0217\u021a\3h\64\0\u0218\u0219"+
		"\5\60\0\0\u0219\u021b\3\u008cF\0\u021a\u0218\1\0\0\0\u021a\u021b\1\0\0"+
		"\0\u021bM\1\0\0\0\u021c\u021e\5\4\0\0\u021d\u021c\1\0\0\0\u021d\u021e"+
		"\1\0\0\0\u021e\u0220\1\0\0\0\u021f\u0221\5\16\0\0\u0220\u021f\1\0\0\0"+
		"\u0220\u0221\1\0\0\0\u0221\u0222\1\0\0\0\u0222\u0223\5\1\0\0\u0223\u0224"+
		"\5\34\0\0\u0224\u0225\3P(\0\u0225\u0226\5\37\0\0\u0226O\1\0\0\0\u0227"+
		"\u0228\3R)\0\u0228Q\1\0\0\0\u0229\u022e\3T*\0\u022a\u022b\5*\0\0\u022b"+
		"\u022d\3T*\0\u022c\u022a\1\0\0\0\u022d\u0230\1\0\0\0\u022e\u022c\1\0\0"+
		"\0\u022e\u022f\1\0\0\0\u022fS\1\0\0\0\u0230\u022e\1\0\0\0\u0231\u0234"+
		"\3V+\0\u0232\u0235\3^/\0\u0233\u0235\1\0\0\0\u0234\u0232\1\0\0\0\u0234"+
		"\u0233\1\0\0\0\u0235U\1\0\0\0\u0236\u0238\3X,\0\u0237\u0236\1\0\0\0\u0238"+
		"\u0239\1\0\0\0\u0239\u0237\1\0\0\0\u0239\u023a\1\0\0\0\u023aW\1\0\0\0"+
		"\u023b\u023e\3Z-\0\u023c\u023f\3t:\0\u023d\u023f\1\0\0\0\u023e\u023c\1"+
		"\0\0\0\u023e\u023d\1\0\0\0\u023f\u024f\1\0\0\0\u0240\u0243\3v;\0\u0241"+
		"\u0244\3t:\0\u0242\u0244\1\0\0\0\u0243\u0241\1\0\0\0\u0243\u0242\1\0\0"+
		"\0\u0244\u024f\1\0\0\0\u0245\u0248\3\\.\0\u0246\u0249\3t:\0\u0247\u0249"+
		"\1\0\0\0\u0248\u0246\1\0\0\0\u0248\u0247\1\0\0\0\u0249\u024f\1\0\0\0\u024a"+
		"\u024c\3\24\n\0\u024b\u024d\5&\0\0\u024c\u024b\1\0\0\0\u024c\u024d\1\0"+
		"\0\0\u024d\u024f\1\0\0\0\u024e\u023b\1\0\0\0\u024e\u0240\1\0\0\0\u024e"+
		"\u0245\1\0\0\0\u024e\u024a\1\0\0\0\u024fY\1\0\0\0\u0250\u0253\3\u008c"+
		"F\0\u0251\u0254\5%\0\0\u0252\u0254\5)\0\0\u0253\u0251\1\0\0\0\u0253\u0252"+
		"\1\0\0\0\u0254\u0257\1\0\0\0\u0255\u0258\3v;\0\u0256\u0258\3\u0080@\0"+
		"\u0257\u0255\1\0\0\0\u0257\u0256\1\0\0\0\u0258[\1\0\0\0\u0259\u025a\5"+
		" \0\0\u025a\u025b\3R)\0\u025b\u025c\5!\0\0\u025c]\1\0\0\0\u025d\u025e"+
		"\5\"\0\0\u025e\u0263\3`\60\0\u025f\u0260\5\36\0\0\u0260\u0262\3`\60\0"+
		"\u0261\u025f\1\0\0\0\u0262\u0265\1\0\0\0\u0263\u0261\1\0\0\0\u0263\u0264"+
		"\1\0\0\0\u0264_\1\0\0\0\u0265\u0263\1\0\0\0\u0266\u0267\3b\61\0\u0267"+
		"\u0268\5 \0\0\u0268\u0269\3d\62\0\u0269\u026a\5!\0\0\u026a\u026d\1\0\0"+
		"\0\u026b\u026d\3b\61\0\u026c\u0266\1\0\0\0\u026c\u026b\1\0\0\0\u026da"+
		"\1\0\0\0\u026e\u0271\3\u008cF\0\u026f\u0271\5\33\0\0\u0270\u026e\1\0\0"+
		"\0\u0270\u026f\1\0\0\0\u0271c\1\0\0\0\u0272\u0275\3\u008cF\0\u0273\u0275"+
		"\5\64\0\0\u0274\u0272\1\0\0\0\u0274\u0273\1\0\0\0\u0275e\1\0\0\0\u0276"+
		"\u027b\3h\64\0\u0277\u0278\5*\0\0\u0278\u027a\3h\64\0\u0279\u0277\1\0"+
		"\0\0\u027a\u027d\1\0\0\0\u027b\u0279\1\0\0\0\u027b\u027c\1\0\0\0\u027c"+
		"g\1\0\0\0\u027d\u027b\1\0\0\0\u027e\u0281\3j\65\0\u027f\u0281\1\0\0\0"+
		"\u0280\u027e\1\0\0\0\u0280\u027f\1\0\0\0\u0281i\1\0\0\0\u0282\u0284\3"+
		"l\66\0\u0283\u0282\1\0\0\0\u0284\u0285\1\0\0\0\u0285\u0283\1\0\0\0\u0285"+
		"\u0286\1\0\0\0\u0286k\1\0\0\0\u0287\u028a\3n\67\0\u0288\u028b\3t:\0\u0289"+
		"\u028b\1\0\0\0\u028a\u0288\1\0\0\0\u028a\u0289\1\0\0\0\u028b\u0297\1\0"+
		"\0\0\u028c\u028f\3x<\0\u028d\u0290\3t:\0\u028e\u0290\1\0\0\0\u028f\u028d"+
		"\1\0\0\0\u028f\u028e\1\0\0\0\u0290\u0297\1\0\0\0\u0291\u0297\3p8\0\u0292"+
		"\u0294\3\24\n\0\u0293\u0295\5&\0\0\u0294\u0293\1\0\0\0\u0294\u0295\1\0"+
		"\0\0\u0295\u0297\1\0\0\0\u0296\u0287\1\0\0\0\u0296\u028c\1\0\0\0\u0296"+
		"\u0291\1\0\0\0\u0296\u0292\1\0\0\0\u0297m\1\0\0\0\u0298\u029b\3\u008c"+
		"F\0\u0299\u029c\5%\0\0\u029a\u029c\5)\0\0\u029b\u0299\1\0\0\0\u029b\u029a"+
		"\1\0\0\0\u029c\u029f\1\0\0\0\u029d\u02a0\3x<\0\u029e\u02a0\3\u0080@\0"+
		"\u029f\u029d\1\0\0\0\u029f\u029e\1\0\0\0\u02a0o\1\0\0\0\u02a1\u02a4\3"+
		"\u0080@\0\u02a2\u02a5\3r9\0\u02a3\u02a5\1\0\0\0\u02a4\u02a2\1\0\0\0\u02a4"+
		"\u02a3\1\0\0\0\u02a5q\1\0\0\0\u02a6\u02a7\3t:\0\u02a7s\1\0\0\0\u02a8\u02a9"+
		"\7\3\0\0\u02a9u\1\0\0\0\u02aa\u02b4\3\u0084B\0\u02ab\u02b4\3\u0086C\0"+
		"\u02ac\u02b4\5\2\0\0\u02ad\u02b4\3z=\0\u02ae\u02b4\5\3\0\0\u02af\u02b1"+
		"\5,\0\0\u02b0\u02b2\3\u0088D\0\u02b1\u02b0\1\0\0\0\u02b1\u02b2\1\0\0\0"+
		"\u02b2\u02b4\1\0\0\0\u02b3\u02aa\1\0\0\0\u02b3\u02ab\1\0\0\0\u02b3\u02ac"+
		"\1\0\0\0\u02b3\u02ad\1\0\0\0\u02b3\u02ae\1\0\0\0\u02b3\u02af\1\0\0\0\u02b4"+
		"w\1\0\0\0\u02b5\u02be\3\u0084B\0\u02b6\u02be\3\u0086C\0\u02b7\u02be\3"+
		"\u0082A\0\u02b8\u02be\3z=\0\u02b9\u02bb\5,\0\0\u02ba\u02bc\3\u0088D\0"+
		"\u02bb\u02ba\1\0\0\0\u02bb\u02bc\1\0\0\0\u02bc\u02be\1\0\0\0\u02bd\u02b5"+
		"\1\0\0\0\u02bd\u02b6\1\0\0\0\u02bd\u02b7\1\0\0\0\u02bd\u02b8\1\0\0\0\u02bd"+
		"\u02b9\1\0\0\0\u02bey\1\0\0\0\u02bf\u02c0\5\61\0\0\u02c0\u02c4\3~?\0\u02c1"+
		"\u02c2\5\61\0\0\u02c2\u02c4\3|>\0\u02c3\u02bf\1\0\0\0\u02c3\u02c1\1\0"+
		"\0\0\u02c4{\1\0\0\0\u02c5\u02c6\5 \0\0\u02c6\u02cb\3~?\0\u02c7\u02c8\5"+
		"*\0\0\u02c8\u02ca\3~?\0\u02c9\u02c7\1\0\0\0\u02ca\u02cd\1\0\0\0\u02cb"+
		"\u02c9\1\0\0\0\u02cb\u02cc\1\0\0\0\u02cc\u02ce\1\0\0\0\u02cd\u02cb\1\0"+
		"\0\0\u02ce\u02cf\5!\0\0\u02cf}\1\0\0\0\u02d0\u02d5\5\1\0\0\u02d1\u02d5"+
		"\5\65\0\0\u02d2\u02d5\3\u0084B\0\u02d3\u02d5\5\3\0\0\u02d4\u02d0\1\0\0"+
		"\0\u02d4\u02d1\1\0\0\0\u02d4\u02d2\1\0\0\0\u02d4\u02d3\1\0\0\0\u02d5\177"+
		"\1\0\0\0\u02d6\u02e1\5 \0\0\u02d7\u02d9\3\6\3\0\u02d8\u02d7\1\0\0\0\u02d8"+
		"\u02d9\1\0\0\0\u02d9\u02dd\1\0\0\0\u02da\u02dc\3B!\0\u02db\u02da\1\0\0"+
		"\0\u02dc\u02df\1\0\0\0\u02dd\u02db\1\0\0\0\u02dd\u02de\1\0\0\0\u02de\u02e0"+
		"\1\0\0\0\u02df\u02dd\1\0\0\0\u02e0\u02e2\5\34\0\0\u02e1\u02d8\1\0\0\0"+
		"\u02e1\u02e2\1\0\0\0\u02e2\u02e3\1\0\0\0\u02e3\u02e4\3f\63\0\u02e4\u02e5"+
		"\5!\0\0\u02e5\u0081\1\0\0\0\u02e6\u02e8\5\2\0\0\u02e7\u02e9\3\32\r\0\u02e8"+
		"\u02e7\1\0\0\0\u02e8\u02e9\1\0\0\0\u02e9\u0083\1\0\0\0\u02ea\u02eb\5\65"+
		"\0\0\u02eb\u02ec\5-\0\0\u02ec\u02ed\5\65\0\0\u02ed\u0085\1\0\0\0\u02ee"+
		"\u02f0\5\1\0\0\u02ef\u02f1\3\u0088D\0\u02f0\u02ef\1\0\0\0\u02f0\u02f1"+
		"\1\0\0\0\u02f1\u02f7\1\0\0\0\u02f2\u02f4\5\65\0\0\u02f3\u02f5\3\u0088"+
		"D\0\u02f4\u02f3\1\0\0\0\u02f4\u02f5\1\0\0\0\u02f5\u02f7\1\0\0\0\u02f6"+
		"\u02ee\1\0\0\0\u02f6\u02f2\1\0\0\0\u02f7\u0087\1\0\0\0\u02f8\u02f9\5#"+
		"\0\0\u02f9\u02fe\3\u008aE\0\u02fa\u02fb\5\36\0\0\u02fb\u02fd\3\u008aE"+
		"\0\u02fc\u02fa\1\0\0\0\u02fd\u0300\1\0\0\0\u02fe\u02fc\1\0\0\0\u02fe\u02ff"+
		"\1\0\0\0\u02ff\u0301\1\0\0\0\u0300\u02fe\1\0\0\0\u0301\u0302\5$\0\0\u0302"+
		"\u0089\1\0\0\0\u0303\u030b\3\u008eG\0\u0304\u0305\3\u008cF\0\u0305\u0308"+
		"\5%\0\0\u0306\u0309\3\u008eG\0\u0307\u0309\5\65\0\0\u0308\u0306\1\0\0"+
		"\0\u0308\u0307\1\0\0\0\u0309\u030b\1\0\0\0\u030a\u0303\1\0\0\0\u030a\u0304"+
		"\1\0\0\0\u030b\u008b\1\0\0\0\u030c\u030d\7\4\0\0\u030d\u008d\1\0\0\0\u030e"+
		"\u0313\3\u008cF\0\u030f\u0310\5,\0\0\u0310\u0312\3\u008cF\0\u0311\u030f"+
		"\1\0\0\0\u0312\u0315\1\0\0\0\u0313\u0311\1\0\0\0\u0313\u0314\1\0\0\0\u0314"+
		"\u008f\1\0\0\0\u0315\u0313\1\0\0\0e\u0091\1\u0099\1\u00a0\1\u00aa\1\u00b0"+
		"\1\u00b8\1\u00c5\1\u00cd\1\u00d7\1\u00dd\1\u00e5\1\u00ea\1\u0104\1\u0106"+
		"\1\u010f\1\u0116\1\u011f\1\u0126\1\u012d\1\u0130\1\u0136\1\u013d\1\u0140"+
		"\1\u0146\1\u014f\1\u0158\1\u015f\1\u0166\1\u016d\1\u0172\1\u0175\1\u017a"+
		"\1\u017f\1\u0188\1\u018e\1\u0194\1\u0199\1\u019c\1\u01a4\1\u01ac\1\u01b4"+
		"\1\u01b9\1\u01be\1\u01c1\1\u01c4\1\u01c8\1\u01cb\1\u01ce\1\u01d1\1\u01dc"+
		"\1\u01e0\1\u01ec\1\u01f1\1\u01fc\1\u0209\1\u0214\1\u021a\1\u021d\1\u0220"+
		"\1\u022e\1\u0234\1\u0239\1\u023e\1\u0243\1\u0248\1\u024c\1\u024e\1\u0253"+
		"\1\u0257\1\u0263\1\u026c\1\u0270\1\u0274\1\u027b\1\u0280\1\u0285\1\u028a"+
		"\1\u028f\1\u0294\1\u0296\1\u029b\1\u029f\1\u02a4\1\u02b1\1\u02b3\1\u02bb"+
		"\1\u02bd\1\u02c3\1\u02cb\1\u02d4\1\u02d8\1\u02dd\1\u02e1\1\u02e8\1\u02f0"+
		"\1\u02f4\1\u02f6\1\u02fe\1\u0308\1\u030a\1\u0313\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}