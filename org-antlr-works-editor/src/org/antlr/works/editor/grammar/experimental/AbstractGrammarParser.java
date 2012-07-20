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
		DOLLAR=43, PROTECTED=18, LEXER_CHAR_SET_BODY=91, LT=35, ACTION_COLON=80, 
		STAR=39, NESTED_ACTION=69, DOUBLE_ANGLE_STRING_LITERAL=8, FRAGMENT=14, 
		ACTION_ESCAPE=82, ACTION_REFERENCE=84, ACTION_COLON2=79, ACTION_DOT=70, 
		NOT=49, ID=51, ARG_ACTION_LT=56, ACTION_NEWLINE=89, TOKEN_REF=1, LPAREN=32, 
		ARG_ACTION_ELEMENT=64, ARG_ACTION_RPAREN=59, ARG_ACTION_GT=57, AT=47, 
		LEXER_CHAR_SET=3, RPAREN=33, ARG_ACTION_LPAREN=58, IMPORT=13, STRING_LITERAL=53, 
		END_ACTION=90, ETC=46, COMMA=30, ACTION_GT=72, ACTION_LITERAL=86, ARG_ACTION_NEWLINE=67, 
		DOC_COMMENT=4, BLOCK_COMMENT=5, PLUS=40, BEGIN_ACTION=10, DOUBLE_QUOTE_STRING_LITERAL=7, 
		DOT=44, ACTION_LBRACK=75, MODE=27, ACTION_WORD=83, GRAMMAR=17, ARG_ACTION_ESCAPE=62, 
		RETURNS=21, ARG_ACTION_TEXT=65, ACTION_EQUALS=77, LOCALS=22, ACTION_WS=88, 
		RBRACE=50, POUND=48, LINE_COMMENT=6, PRIVATE=20, RARROW=34, END_LEXER_CHAR_SET=92, 
		END_ARG_ACTION=68, TOKENS=12, RANGE=45, THROWS=23, INT=52, SEMI=31, RULE_REF=2, 
		ARG_ACTION_EQUALS=60, ACTION_RPAREN=74, ACTION_COMMA=78, COLON=28, COLONCOLON=29, 
		ACTION_RBRACK=76, WS=54, ACTION_COMMENT=85, QUESTION=38, FINALLY=25, ACTION_LT=71, 
		TEMPLATE=26, ACTION_TEXT=87, LEXER=15, ERRCHAR=55, ACTION_MINUS=81, ARG_ACTION_COMMA=61, 
		OR=42, ASSIGN=37, PLUS_ASSIGN=41, ARG_ACTION_WS=66, GT=36, CATCH=24, ARG_ACTION_WORD=63, 
		PUBLIC=19, PARSER=16, ACTION_LPAREN=73, OPTIONS=11, BEGIN_ARG_ACTION=9, 
		RULE=93, PREC_RULE=94, RULES=95, RULEMODIFIERS=96, RULEACTIONS=97, BLOCK=98, 
		OPTIONAL=99, CLOSURE=100, POSITIVE_CLOSURE=101, SET=102, CHAR_RANGE=103, 
		EPSILON=104, ALT=105, ALTLIST=106, ARG=107, ARGLIST=108, RET=109, COMBINED=110, 
		INITACTION=111, LABEL=112, WILDCARD=113, LIST=114, ELEMENT_OPTIONS=115, 
		RESULT=116, LEXER_ALT_ACTION=117, LEXER_ACTION_CALL=118;
	public static final String[] tokenNames = {
        "<INVALID>", "TOKEN_REF", "RULE_REF", "LEXER_CHAR_SET", "DOC_COMMENT", 
        "BLOCK_COMMENT", "LINE_COMMENT", "DOUBLE_QUOTE_STRING_LITERAL", 
        "DOUBLE_ANGLE_STRING_LITERAL", "BEGIN_ARG_ACTION", "BEGIN_ACTION", 
        "OPTIONS", "TOKENS", "'import'", "'fragment'", "'lexer'", "'parser'", 
        "'grammar'", "'protected'", "'public'", "'private'", "'returns'", 
        "'locals'", "'throws'", "'catch'", "'finally'", "'template'", "'mode'", 
        "COLON", "COLONCOLON", "COMMA", "';'", "LPAREN", "RPAREN", "'->'", 
        "LT", "GT", "ASSIGN", "'?'", "'*'", "'+'", "'+='", "'|'", "'$'", 
        "DOT", "'..'", "'...'", "'@'", "'#'", "'~'", "'}'", "ID", "INT", 
        "STRING_LITERAL", "WS", "ERRCHAR", "ARG_ACTION_LT", "ARG_ACTION_GT", 
        "ARG_ACTION_LPAREN", "ARG_ACTION_RPAREN", "ARG_ACTION_EQUALS", "ARG_ACTION_COMMA", 
        "ARG_ACTION_ESCAPE", "ARG_ACTION_WORD", "ARG_ACTION_ELEMENT", "ARG_ACTION_TEXT", 
        "ARG_ACTION_WS", "ARG_ACTION_NEWLINE", "END_ARG_ACTION", "NESTED_ACTION", 
        "'.'", "'<'", "'>'", "'('", "')'", "'['", "']'", "'='", "','", "'::'", 
        "':'", "'-'", "ACTION_ESCAPE", "ACTION_WORD", "ACTION_REFERENCE", 
        "ACTION_COMMENT", "ACTION_LITERAL", "ACTION_TEXT", "ACTION_WS", 
        "ACTION_NEWLINE", "END_ACTION", "LEXER_CHAR_SET_BODY", "END_LEXER_CHAR_SET", 
        "RULE", "PREC_RULE", "RULES", "RULEMODIFIERS", "RULEACTIONS", "BLOCK", 
        "OPTIONAL", "CLOSURE", "POSITIVE_CLOSURE", "SET", "CHAR_RANGE", 
        "EPSILON", "ALT", "ALTLIST", "ARG", "ARGLIST", "RET", "COMBINED", 
        "INITACTION", "LABEL", "WILDCARD", "LIST", "ELEMENT_OPTIONS", "RESULT", 
        "LEXER_ALT_ACTION", "LEXER_ACTION_CALL"
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
		public ModeSpecContext modeSpec() {
		    return getRuleContext(ModeSpecContext.class,0);
		}
		public TerminalNode<Token> EOF() { return getToken(AbstractGrammarParser.EOF, 0); }
		public TerminalNode<Token> SEMI() { return getToken(AbstractGrammarParser.SEMI, 0); }
		public PrequelConstructContext prequelConstruct() {
		    return getRuleContext(PrequelConstructContext.class,0);
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
			setState(146);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(144); match(DOC_COMMENT);
					}
					break;
			}
			setState(148); grammarType();
			setState(150); id();
			setState(152); match(SEMI);
			setState(158);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(154); prequelConstruct();
					}
					} 
				}
				setState(160);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(161); rules();
			setState(167);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(163); modeSpec();
					}
					} 
				}
				setState(169);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(170); match(EOF);
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
			setState(182);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(172); match(LEXER);
					setState(174); match(GRAMMAR);
					}
					break;

				case 2:
					{
					setState(176); match(PARSER);
					setState(178); match(GRAMMAR);
					}
					break;

				case 3:
					{
					setState(180); match(GRAMMAR);
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
			setState(192);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(184); optionsSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(186); delegateGrammars();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(188); tokensSpec();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(190); action();
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
		public TerminalNode<Token> RBRACE() { return getToken(AbstractGrammarParser.RBRACE, 0); }
		public TerminalNode<Token> SEMI() { return getToken(AbstractGrammarParser.SEMI, 0); }
		public TerminalNode<Token> OPTIONS() { return getToken(AbstractGrammarParser.OPTIONS, 0); }
		public OptionContext option() {
		    return getRuleContext(OptionContext.class,0);
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
			setState(194); match(OPTIONS);
			setState(202);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(196); option();
					setState(198); match(SEMI);
					}
					} 
				}
				setState(204);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(205); match(RBRACE);
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
			setState(207); id();
			setState(209); match(ASSIGN);
			setState(211); optionValue();
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
			setState(221);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(213); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(215); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(217); match(INT);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(219); match(STAR);
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
		public TerminalNode<Token> COMMA() { return getToken(AbstractGrammarParser.COMMA, 0); }
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
			setState(223); match(IMPORT);
			setState(225); delegateGrammar();
			setState(233);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(227); match(COMMA);
					setState(229); delegateGrammar();
					}
					} 
				}
				setState(235);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(236); match(SEMI);
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
			setState(246);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(238); id();
					setState(240); match(ASSIGN);
					setState(242); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(244); id();
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
		public TerminalNode<Token> RBRACE() { return getToken(AbstractGrammarParser.RBRACE, 0); }
		public TokenSpecContext tokenSpec() {
		    return getRuleContext(TokenSpecContext.class,0);
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
			setState(248); match(TOKENS);
			setState(252); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(250); tokenSpec();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(254); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			setState(256); match(RBRACE);
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
			setState(272);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(258); id();
					setState(266);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
						case 1:
							{
							setState(260); match(ASSIGN);
							setState(262); match(STRING_LITERAL);
							}
							break;

						case 2:
							{
							}
							break;
					}
					setState(268); match(SEMI);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(270); match(RULE_REF);
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
		public TerminalNode<Token> ACTION_WS() { return getToken(AbstractGrammarParser.ACTION_WS, 0); }
		public TerminalNode<Token> ACTION_COLON() { return getToken(AbstractGrammarParser.ACTION_COLON, 0); }
		public ActionExpressionContext actionExpression() {
		    return getRuleContext(ActionExpressionContext.class,0);
		}
		public ActionBlockContext actionBlock() {
		    return getRuleContext(ActionBlockContext.class,0);
		}
		public TerminalNode<Token> ACTION_ESCAPE() { return getToken(AbstractGrammarParser.ACTION_ESCAPE, 0); }
		public TerminalNode<Token> ACTION_REFERENCE() { return getToken(AbstractGrammarParser.ACTION_REFERENCE, 0); }
		public TerminalNode<Token> ACTION_COLON2() { return getToken(AbstractGrammarParser.ACTION_COLON2, 0); }
		public TerminalNode<Token> ACTION_DOT() { return getToken(AbstractGrammarParser.ACTION_DOT, 0); }
		public ActionScopeExpressionContext actionScopeExpression() {
		    return getRuleContext(ActionScopeExpressionContext.class,0);
		}
		public TerminalNode<Token> ACTION_NEWLINE() { return getToken(AbstractGrammarParser.ACTION_NEWLINE, 0); }
		public TerminalNode<Token> ACTION_RPAREN() { return getToken(AbstractGrammarParser.ACTION_RPAREN, 0); }
		public TerminalNode<Token> ACTION_COMMA() { return getToken(AbstractGrammarParser.ACTION_COMMA, 0); }
		public TerminalNode<Token> ACTION_RBRACK() { return getToken(AbstractGrammarParser.ACTION_RBRACK, 0); }
		public TerminalNode<Token> ACTION_COMMENT() { return getToken(AbstractGrammarParser.ACTION_COMMENT, 0); }
		public TerminalNode<Token> ACTION_LT() { return getToken(AbstractGrammarParser.ACTION_LT, 0); }
		public TerminalNode<Token> END_ACTION() { return getToken(AbstractGrammarParser.END_ACTION, 0); }
		public TerminalNode<Token> ACTION_GT() { return getToken(AbstractGrammarParser.ACTION_GT, 0); }
		public TerminalNode<Token> ACTION_TEXT() { return getToken(AbstractGrammarParser.ACTION_TEXT, 0); }
		public TerminalNode<Token> ACTION_LITERAL() { return getToken(AbstractGrammarParser.ACTION_LITERAL, 0); }
		public TerminalNode<Token> ACTION_MINUS() { return getToken(AbstractGrammarParser.ACTION_MINUS, 0); }
		public TerminalNode<Token> BEGIN_ACTION() { return getToken(AbstractGrammarParser.BEGIN_ACTION, 0); }
		public TerminalNode<Token> ACTION_LPAREN() { return getToken(AbstractGrammarParser.ACTION_LPAREN, 0); }
		public TerminalNode<Token> ACTION_LBRACK() { return getToken(AbstractGrammarParser.ACTION_LBRACK, 0); }
		public TerminalNode<Token> ACTION_WORD() { return getToken(AbstractGrammarParser.ACTION_WORD, 0); }
		public TerminalNode<Token> ACTION_EQUALS() { return getToken(AbstractGrammarParser.ACTION_EQUALS, 0); }
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
			setState(274); match(BEGIN_ACTION);
			setState(324);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					setState(322);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
						case 1:
							{
							setState(276); actionBlock();
							}
							break;

						case 2:
							{
							setState(278); actionExpression();
							}
							break;

						case 3:
							{
							setState(280); actionScopeExpression();
							}
							break;

						case 4:
							{
							setState(282); match(ACTION_WS);
							}
							break;

						case 5:
							{
							setState(284); match(ACTION_NEWLINE);
							}
							break;

						case 6:
							{
							setState(286); match(ACTION_COMMENT);
							}
							break;

						case 7:
							{
							setState(288); match(ACTION_LITERAL);
							}
							break;

						case 8:
							{
							setState(290); match(ACTION_TEXT);
							}
							break;

						case 9:
							{
							setState(292); match(ACTION_LT);
							}
							break;

						case 10:
							{
							setState(294); match(ACTION_GT);
							}
							break;

						case 11:
							{
							setState(296); match(ACTION_LPAREN);
							}
							break;

						case 12:
							{
							setState(298); match(ACTION_RPAREN);
							}
							break;

						case 13:
							{
							setState(300); match(ACTION_LBRACK);
							}
							break;

						case 14:
							{
							setState(302); match(ACTION_RBRACK);
							}
							break;

						case 15:
							{
							setState(304); match(ACTION_EQUALS);
							}
							break;

						case 16:
							{
							setState(306); match(ACTION_COMMA);
							}
							break;

						case 17:
							{
							setState(308); match(ACTION_ESCAPE);
							}
							break;

						case 18:
							{
							setState(310); match(ACTION_WORD);
							}
							break;

						case 19:
							{
							setState(312); match(ACTION_REFERENCE);
							}
							break;

						case 20:
							{
							setState(314); match(ACTION_COLON);
							}
							break;

						case 21:
							{
							setState(316); match(ACTION_COLON2);
							}
							break;

						case 22:
							{
							setState(318); match(ACTION_MINUS);
							}
							break;

						case 23:
							{
							setState(320); match(ACTION_DOT);
							}
							break;
					}
					} 
				}
				setState(326);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			setState(327); match(END_ACTION);
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
			setState(329); _localctx.ref = match(ACTION_REFERENCE);
			setState(335);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(331); ignored();
					}
					} 
				}
				setState(337);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(338); _localctx.op = match(ACTION_DOT);
			setState(344);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(340); ignored();
					}
					} 
				}
				setState(346);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(347); _localctx.member = match(ACTION_WORD);
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
			setState(349); _localctx.ref = match(ACTION_REFERENCE);
			setState(355);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(351); ignored();
					}
					} 
				}
				setState(357);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(396);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(358); match(ACTION_LBRACK);
					setState(364);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(360); ignored();
							}
							} 
						}
						setState(366);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
					}
					setState(376);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
						case 1:
							{
							setState(367); _localctx.neg = match(ACTION_MINUS);
							setState(373);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(369); ignored();
									}
									} 
								}
								setState(375);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
							}
							}
							break;
					}
					setState(378); _localctx.index = match(ACTION_WORD);
					setState(384);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(380); ignored();
							}
							} 
						}
						setState(386);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
					}
					setState(387); match(ACTION_RBRACK);
					setState(393);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(389); ignored();
							}
							} 
						}
						setState(395);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
					}
					}
					break;
			}
			setState(398); _localctx.op = match(ACTION_COLON2);
			setState(404);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(400); ignored();
					}
					} 
				}
				setState(406);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			setState(407); _localctx.member = match(ACTION_WORD);
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
		public TerminalNode<Token> ARG_ACTION_GT() { return getToken(AbstractGrammarParser.ARG_ACTION_GT, 0); }
		public TerminalNode<Token> ARG_ACTION_LPAREN() { return getToken(AbstractGrammarParser.ARG_ACTION_LPAREN, 0); }
		public TerminalNode<Token> ARG_ACTION_COMMA() { return getToken(AbstractGrammarParser.ARG_ACTION_COMMA, 0); }
		public TerminalNode<Token> ARG_ACTION_NEWLINE() { return getToken(AbstractGrammarParser.ARG_ACTION_NEWLINE, 0); }
		public TerminalNode<Token> END_ARG_ACTION() { return getToken(AbstractGrammarParser.END_ARG_ACTION, 0); }
		public TerminalNode<Token> ARG_ACTION_WS() { return getToken(AbstractGrammarParser.ARG_ACTION_WS, 0); }
		public TerminalNode<Token> ARG_ACTION_WORD() { return getToken(AbstractGrammarParser.ARG_ACTION_WORD, 0); }
		public TerminalNode<Token> ARG_ACTION_LT() { return getToken(AbstractGrammarParser.ARG_ACTION_LT, 0); }
		public TerminalNode<Token> ARG_ACTION_EQUALS() { return getToken(AbstractGrammarParser.ARG_ACTION_EQUALS, 0); }
		public TerminalNode<Token> ARG_ACTION_ESCAPE() { return getToken(AbstractGrammarParser.ARG_ACTION_ESCAPE, 0); }
		public TerminalNode<Token> BEGIN_ARG_ACTION() { return getToken(AbstractGrammarParser.BEGIN_ARG_ACTION, 0); }
		public TerminalNode<Token> ARG_ACTION_ELEMENT() { return getToken(AbstractGrammarParser.ARG_ACTION_ELEMENT, 0); }
		public TerminalNode<Token> ARG_ACTION_TEXT() { return getToken(AbstractGrammarParser.ARG_ACTION_TEXT, 0); }
		public TerminalNode<Token> ARG_ACTION_RPAREN() { return getToken(AbstractGrammarParser.ARG_ACTION_RPAREN, 0); }
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
			setState(409); match(BEGIN_ARG_ACTION);
			setState(415);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(411);
					_input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==ARG_ACTION_LT || _la==ARG_ACTION_GT || _la==ARG_ACTION_LPAREN || _la==ARG_ACTION_RPAREN || _la==ARG_ACTION_EQUALS || _la==ARG_ACTION_COMMA || _la==ARG_ACTION_ESCAPE || _la==ARG_ACTION_WORD || _la==ARG_ACTION_ELEMENT || _la==ARG_ACTION_TEXT || _la==ARG_ACTION_WS || _la==ARG_ACTION_NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					} 
				}
				setState(417);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			setState(418); match(END_ARG_ACTION);
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
		public TerminalNode<Token> ARG_ACTION_COMMA() { return getToken(AbstractGrammarParser.ARG_ACTION_COMMA, 0); }
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
			setState(420); match(BEGIN_ARG_ACTION);
			setState(426);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(422); ignored();
					}
					} 
				}
				setState(428);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(461);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(429); _localctx.argActionParameter = argActionParameter();
					_localctx.parameters.add(_localctx.argActionParameter);
					setState(435);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(431); ignored();
							}
							} 
						}
						setState(437);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
					}
					setState(458);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(438); match(ARG_ACTION_COMMA);
							setState(444);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(440); ignored();
									}
									} 
								}
								setState(446);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
							}
							setState(447); _localctx.argActionParameter = argActionParameter();
							_localctx.parameters.add(_localctx.argActionParameter);
							setState(453);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(449); ignored();
									}
									} 
								}
								setState(455);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
							}
							}
							} 
						}
						setState(460);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
					}
					}
					break;
			}
			setState(463); match(END_ARG_ACTION);
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
		public IgnoredContext ignored() {
		    return getRuleContext(IgnoredContext.class,0);
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
			setState(467);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(465); _localctx.type = argActionParameterType();
					}
					break;
			}
			setState(473);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(469); ignored();
					}
					} 
				}
				setState(475);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			setState(476); _localctx.name = match(ARG_ACTION_WORD);
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
		public IgnoredContext ignored() {
		    return getRuleContext(IgnoredContext.class,0);
		}
		public ArgActionParameterTypePartContext argActionParameterTypePart(int i) {
		    return getRuleContext(ArgActionParameterTypePartContext.class,i);
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
			setState(478); argActionParameterTypePart();
			setState(491);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(484);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(480); ignored();
							}
							} 
						}
						setState(486);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
					}
					setState(487); argActionParameterTypePart();
					}
					} 
				}
				setState(493);
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
			setState(512);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(494); match(ARG_ACTION_WORD);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(496); match(ARG_ACTION_LT);
					setState(500);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
						case 1:
							{
							setState(498); argActionParameterType();
							}
							break;
					}
					setState(502); match(ARG_ACTION_GT);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(504); match(ARG_ACTION_LPAREN);
					setState(508);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
						case 1:
							{
							setState(506); argActionParameterType();
							}
							break;
					}
					setState(510); match(ARG_ACTION_RPAREN);
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
		try {
			setState(524);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(514); match(ACTION_WS);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(516); match(ACTION_NEWLINE);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(518); match(ACTION_COMMENT);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(520); match(ARG_ACTION_WS);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(522); match(ARG_ACTION_NEWLINE);
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
			setState(526); match(AT);
			setState(532);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
				case 1:
					{
					setState(528); actionScopeName();
					setState(530); match(COLONCOLON);
					}
					break;
			}
			setState(534); id();
			setState(536); actionBlock();
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
			setState(544);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(538); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(540); match(LEXER);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(542); match(PARSER);
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
		public RuleSpecContext ruleSpec() {
		    return getRuleContext(RuleSpecContext.class,0);
		}
		public IdContext id() {
		    return getRuleContext(IdContext.class,0);
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
			setState(546); match(MODE);
			setState(548); id();
			setState(550); match(SEMI);
			setState(554); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(552); ruleSpec();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(556); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
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
		public RuleSpecContext ruleSpec() {
		    return getRuleContext(RuleSpecContext.class,0);
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
			setState(562);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(558); ruleSpec();
					}
					} 
				}
				setState(564);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
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
			setState(569);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(565); parserRuleSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(567); lexerRule();
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
			setState(573);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(571); match(DOC_COMMENT);
					}
					break;
			}
			setState(577);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(575); ruleModifiers();
					}
					break;
			}
			setState(579); _localctx.name = match(RULE_REF);
			setState(583);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					{
					setState(581); argActionParameters();
					}
					break;
			}
			setState(587);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(585); ruleReturns();
					}
					break;
			}
			setState(591);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					{
					setState(589); throwsSpec();
					}
					break;
			}
			setState(595);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
				case 1:
					{
					setState(593); localsSpec();
					}
					break;
			}
			setState(597); rulePrequels();
			setState(599); match(COLON);
			setState(601); ruleBlock();
			setState(603); match(SEMI);
			setState(605); exceptionGroup();
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
		public ExceptionHandlerContext exceptionHandler() {
		    return getRuleContext(ExceptionHandlerContext.class,0);
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
			setState(611);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(607); exceptionHandler();
					}
					} 
				}
				setState(613);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
			}
			setState(616);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
				case 1:
					{
					setState(614); finallyClause();
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
			setState(618); match(CATCH);
			setState(620); argActionBlock();
			setState(622); actionBlock();
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
			setState(624); match(FINALLY);
			setState(626); actionBlock();
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
		public RulePrequelContext rulePrequel() {
		    return getRuleContext(RulePrequelContext.class,0);
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
			setState(632);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(628); rulePrequel();
					}
					} 
				}
				setState(634);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
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
			setState(639);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(635); optionsSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(637); ruleAction();
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
			setState(641); match(RETURNS);
			setState(643); argActionParameters();
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
		public QidContext qid(int i) {
		    return getRuleContext(QidContext.class,i);
		}
		public List<? extends QidContext> qid() {
		    return getRuleContexts(QidContext.class);
		}
		public TerminalNode<Token> COMMA() { return getToken(AbstractGrammarParser.COMMA, 0); }
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
			setState(645); match(THROWS);
			setState(647); qid();
			setState(655);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(649); match(COMMA);
					setState(651); qid();
					}
					} 
				}
				setState(657);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
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
			setState(658); match(LOCALS);
			setState(660); argActionParameters();
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
			setState(662); match(AT);
			setState(664); id();
			setState(666); actionBlock();
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
		public RuleModifierContext ruleModifier() {
		    return getRuleContext(RuleModifierContext.class,0);
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
			setState(670); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(668); ruleModifier();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(672); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
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
		try {
			setState(682);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(674); match(PUBLIC);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(676); match(PRIVATE);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(678); match(PROTECTED);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(680); match(FRAGMENT);
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
			setState(684); ruleAltList();
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
		public List<? extends LabeledAltContext> labeledAlt() {
		    return getRuleContexts(LabeledAltContext.class);
		}
		public LabeledAltContext labeledAlt(int i) {
		    return getRuleContext(LabeledAltContext.class,i);
		}
		public TerminalNode<Token> OR() { return getToken(AbstractGrammarParser.OR, 0); }
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
			setState(686); labeledAlt();
			setState(694);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(688); match(OR);
					setState(690); labeledAlt();
					}
					} 
				}
				setState(696);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
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
			setState(697); alternative();
			setState(703);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					{
					setState(699); match(POUND);
					setState(701); id();
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
			setState(707);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
				case 1:
					{
					setState(705); match(DOC_COMMENT);
					}
					break;
			}
			setState(711);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
				case 1:
					{
					setState(709); match(FRAGMENT);
					}
					break;
			}
			setState(713); _localctx.name = match(TOKEN_REF);
			setState(715); match(COLON);
			setState(717); lexerRuleBlock();
			setState(719); match(SEMI);
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
			setState(721); lexerAltList();
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
		public LexerAltContext lexerAlt(int i) {
		    return getRuleContext(LexerAltContext.class,i);
		}
		public TerminalNode<Token> OR() { return getToken(AbstractGrammarParser.OR, 0); }
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
			setState(723); lexerAlt();
			setState(731);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(725); match(OR);
					setState(727); lexerAlt();
					}
					} 
				}
				setState(733);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
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
			setState(734); lexerElements();
			setState(740);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
				case 1:
					{
					setState(736); lexerCommands();
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
		public LexerElementContext lexerElement() {
		    return getRuleContext(LexerElementContext.class,0);
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
			setState(744); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(742); lexerElement();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(746); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
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
			setState(778);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(748); labeledLexerElement();
					setState(754);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
						case 1:
							{
							setState(750); ebnfSuffix();
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
					setState(756); lexerAtom();
					setState(762);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
						case 1:
							{
							setState(758); ebnfSuffix();
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
					setState(764); lexerBlock();
					setState(770);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
						case 1:
							{
							setState(766); ebnfSuffix();
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
					setState(772); actionBlock();
					setState(776);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
						case 1:
							{
							setState(774); match(QUESTION);
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
			setState(780); id();
			setState(786);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
				case 1:
					{
					setState(782); _localctx.ass = match(ASSIGN);
					}
					break;

				case 2:
					{
					setState(784); _localctx.ass = match(PLUS_ASSIGN);
					}
					break;
			}
			setState(792);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
				case 1:
					{
					setState(788); lexerAtom();
					}
					break;

				case 2:
					{
					setState(790); block();
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
			setState(794); match(LPAREN);
			setState(796); lexerAltList();
			setState(798); match(RPAREN);
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
		public List<? extends LexerCommandContext> lexerCommand() {
		    return getRuleContexts(LexerCommandContext.class);
		}
		public TerminalNode<Token> COMMA() { return getToken(AbstractGrammarParser.COMMA, 0); }
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
			setState(800); match(RARROW);
			setState(802); lexerCommand();
			setState(810);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(804); match(COMMA);
					setState(806); lexerCommand();
					}
					} 
				}
				setState(812);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
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
			setState(823);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(813); lexerCommandName();
					setState(815); match(LPAREN);
					setState(817); lexerCommandExpr();
					setState(819); match(RPAREN);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(821); lexerCommandName();
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
			setState(829);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(825); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(827); match(MODE);
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
			setState(835);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(831); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(833); match(INT);
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
		public List<? extends AlternativeContext> alternative() {
		    return getRuleContexts(AlternativeContext.class);
		}
		public TerminalNode<Token> OR() { return getToken(AbstractGrammarParser.OR, 0); }
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
			setState(837); alternative();
			setState(845);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(839); match(OR);
					setState(841); alternative();
					}
					} 
				}
				setState(847);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
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
			setState(852);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(848); elements();
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
		public ElementContext element() {
		    return getRuleContext(ElementContext.class,0);
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
			setState(856); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(854); element();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(858); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
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
			setState(884);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(860); labeledElement();
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

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(868); atom();
					setState(874);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
						case 1:
							{
							setState(870); ebnfSuffix();
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
					setState(876); ebnf();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(878); actionBlock();
					setState(882);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
						case 1:
							{
							setState(880); match(QUESTION);
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
			setState(886); _localctx.label = id();
			setState(892);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
				case 1:
					{
					setState(888); _localctx.ass = match(ASSIGN);
					}
					break;

				case 2:
					{
					setState(890); _localctx.ass = match(PLUS_ASSIGN);
					}
					break;
			}
			setState(898);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
				case 1:
					{
					setState(894); atom();
					}
					break;

				case 2:
					{
					setState(896); block();
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
			setState(900); block();
			setState(906);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
				case 1:
					{
					setState(902); blockSuffix();
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
			setState(908); ebnfSuffix();
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
		try {
			setState(916);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(910); match(QUESTION);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(912); match(STAR);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(914); match(PLUS);
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
			setState(934);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(918); range();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(920); terminal();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(922); match(RULE_REF);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(924); notSet();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(926); match(LEXER_CHAR_SET);
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(928); match(DOT);
					setState(932);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
						case 1:
							{
							setState(930); elementOptions();
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
			setState(950);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(936); range();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(938); terminal();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(940); ruleref();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(942); notSet();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(944); match(DOT);
					setState(948);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
						case 1:
							{
							setState(946); elementOptions();
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
			setState(960);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(952); match(NOT);
					setState(954); setElement();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(956); match(NOT);
					setState(958); blockSet();
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
		public SetElementContext setElement(int i) {
		    return getRuleContext(SetElementContext.class,i);
		}
		public TerminalNode<Token> LPAREN() { return getToken(AbstractGrammarParser.LPAREN, 0); }
		public TerminalNode<Token> OR() { return getToken(AbstractGrammarParser.OR, 0); }
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
			setState(962); match(LPAREN);
			setState(964); setElement();
			setState(972);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(966); match(OR);
					setState(968); setElement();
					}
					} 
				}
				setState(974);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
			}
			setState(975); match(RPAREN);
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
			setState(985);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(977); match(TOKEN_REF);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(979); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(981); range();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(983); match(LEXER_CHAR_SET);
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
		public TerminalNode<Token> COLON() { return getToken(AbstractGrammarParser.COLON, 0); }
		public TerminalNode<Token> RPAREN() { return getToken(AbstractGrammarParser.RPAREN, 0); }
		public OptionsSpecContext optionsSpec() {
		    return getRuleContext(OptionsSpecContext.class,0);
		}
		public AltListContext altList() {
		    return getRuleContext(AltListContext.class,0);
		}
		public TerminalNode<Token> LPAREN() { return getToken(AbstractGrammarParser.LPAREN, 0); }
		public RuleActionContext ruleAction() {
		    return getRuleContext(RuleActionContext.class,0);
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
			setState(987); match(LPAREN);
			setState(1002);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
				case 1:
					{
					setState(991);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
						case 1:
							{
							setState(989); optionsSpec();
							}
							break;
					}
					setState(997);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(993); ruleAction();
							}
							} 
						}
						setState(999);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
					}
					setState(1000); match(COLON);
					}
					break;
			}
			setState(1004); altList();
			setState(1006); match(RPAREN);
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
			setState(1008); match(RULE_REF);
			setState(1012);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
				case 1:
					{
					setState(1010); argActionBlock();
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
			setState(1014); match(STRING_LITERAL);
			setState(1016); match(RANGE);
			setState(1018); match(STRING_LITERAL);
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
			setState(1032);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1020); match(TOKEN_REF);
					setState(1024);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
						case 1:
							{
							setState(1022); elementOptions();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1026); match(STRING_LITERAL);
					setState(1030);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
						case 1:
							{
							setState(1028); elementOptions();
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
		public List<? extends ElementOptionContext> elementOption() {
		    return getRuleContexts(ElementOptionContext.class);
		}
		public TerminalNode<Token> COMMA() { return getToken(AbstractGrammarParser.COMMA, 0); }
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
			setState(1034); match(LT);
			setState(1036); elementOption();
			setState(1044);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,100,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(1038); match(COMMA);
					setState(1040); elementOption();
					}
					} 
				}
				setState(1046);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,100,_ctx);
			}
			setState(1047); match(GT);
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
			setState(1061);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1049); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1051); id();
					setState(1053); match(ASSIGN);
					setState(1059);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
						case 1:
							{
							setState(1055); qid();
							}
							break;

						case 2:
							{
							setState(1057); match(STRING_LITERAL);
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
		try {
			setState(1069);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1063); match(RULE_REF);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1065); match(TOKEN_REF);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1067); match(TEMPLATE);
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

	public static class QidContext extends ParserRuleContext<Token> {
		public List<? extends IdContext> id() {
		    return getRuleContexts(IdContext.class);
		}
		public TerminalNode<Token> DOT() { return getToken(AbstractGrammarParser.DOT, 0); }
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
			setState(1071); id();
			setState(1079);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,104,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(1073); match(DOT);
					setState(1075); id();
					}
					} 
				}
				setState(1081);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,104,_ctx);
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
		"\1v\u043b\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7"+
		"\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16\7\16\2\17"+
		"\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25\7\25\2\26"+
		"\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34\7\34\2\35"+
		"\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7%\2&\7&"+
		"\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2.\7.\2/\7/\2\60\7\60\2\61"+
		"\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65\2\66\7\66\2\67\7\67\28\7"+
		"8\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2@\7@\2A\7A\2B\7B\2C\7C\2"+
		"D\7D\2E\7E\2F\7F\2G\7G\1\0\0\3\0\b\0\1\0\0\1\0\0\1\0\0\1\0\0\5\0\b\0\n"+
		"\0\f\0\u00a0\t\0\1\0\0\1\0\0\5\0\b\0\n\0\f\0\u00a9\t\0\1\0\1\0\1\1\0\1"+
		"\1\0\1\1\0\1\1\0\1\1\0\3\1\b\1\1\2\0\1\2\0\1\2\0\1\2\0\3\2\b\2\1\3\0\1"+
		"\3\0\1\3\1\3\5\3\b\3\n\3\f\3\u00cc\t\3\1\3\1\3\1\4\0\1\4\0\1\4\1\4\1\5"+
		"\0\1\5\0\1\5\0\1\5\0\3\5\b\5\1\6\0\1\6\0\1\6\0\1\6\0\5\6\b\6\n\6\f\6\u00eb"+
		"\t\6\1\6\1\6\1\7\0\1\7\0\1\7\1\7\1\7\0\3\7\b\7\1\b\0\1\b\0\4\b\b\b\13"+
		"\b\f\b\u00fe\1\b\1\b\1\t\0\1\t\0\1\t\0\1\t\0\3\t\b\t\1\t\1\t\1\t\0\3\t"+
		"\b\t\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0"+
		"\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n"+
		"\0\1\n\0\5\n\b\n\n\n\f\n\u0146\t\n\1\n\1\n\1\13\0\1\13\0\5\13\b\13\n\13"+
		"\f\13\u0151\t\13\1\13\0\1\13\0\5\13\b\13\n\13\f\13\u015a\t\13\1\13\1\13"+
		"\1\f\0\1\f\0\5\f\b\f\n\f\f\f\u0165\t\f\1\f\0\1\f\0\5\f\b\f\n\f\f\f\u016e"+
		"\t\f\1\f\0\1\f\0\5\f\b\f\n\f\f\f\u0177\t\f\3\f\b\f\1\f\0\1\f\0\5\f\b\f"+
		"\n\f\f\f\u0182\t\f\1\f\0\1\f\0\5\f\b\f\n\f\f\f\u018b\t\f\3\f\b\f\1\f\0"+
		"\1\f\0\5\f\b\f\n\f\f\f\u0196\t\f\1\f\1\f\1\r\0\1\r\0\5\r\b\r\n\r\f\r\u01a1"+
		"\t\r\1\r\1\r\1\16\0\1\16\0\5\16\b\16\n\16\f\16\u01ac\t\16\1\16\0\1\16"+
		"\0\5\16\b\16\n\16\f\16\u01b5\t\16\1\16\0\1\16\0\5\16\b\16\n\16\f\16\u01be"+
		"\t\16\1\16\0\1\16\0\5\16\b\16\n\16\f\16\u01c7\t\16\5\16\b\16\n\16\f\16"+
		"\u01cc\t\16\3\16\b\16\1\16\1\16\1\17\0\3\17\b\17\1\17\0\5\17\b\17\n\17"+
		"\f\17\u01db\t\17\1\17\1\17\1\20\0\1\20\0\5\20\b\20\n\20\f\20\u01e6\t\20"+
		"\1\20\0\5\20\b\20\n\20\f\20\u01ed\t\20\1\21\0\1\21\0\1\21\0\3\21\b\21"+
		"\1\21\0\1\21\0\1\21\0\3\21\b\21\1\21\0\3\21\b\21\1\22\0\1\22\0\1\22\0"+
		"\1\22\0\1\22\0\3\22\b\22\1\23\0\1\23\0\1\23\1\23\3\23\b\23\1\23\0\1\23"+
		"\1\23\1\24\0\1\24\0\1\24\0\3\24\b\24\1\25\0\1\25\0\1\25\0\1\25\0\4\25"+
		"\b\25\13\25\f\25\u022c\1\26\0\5\26\b\26\n\26\f\26\u0234\t\26\1\27\0\1"+
		"\27\0\3\27\b\27\1\30\0\3\30\b\30\1\30\0\3\30\b\30\1\30\0\1\30\0\3\30\b"+
		"\30\1\30\0\3\30\b\30\1\30\0\3\30\b\30\1\30\0\3\30\b\30\1\30\0\1\30\0\1"+
		"\30\0\1\30\0\1\30\1\30\1\31\0\5\31\b\31\n\31\f\31\u0265\t\31\1\31\0\3"+
		"\31\b\31\1\32\0\1\32\0\1\32\1\32\1\33\0\1\33\1\33\1\34\0\5\34\b\34\n\34"+
		"\f\34\u027a\t\34\1\35\0\1\35\0\3\35\b\35\1\36\0\1\36\1\36\1\37\0\1\37"+
		"\0\1\37\0\1\37\0\5\37\b\37\n\37\f\37\u0291\t\37\1 \0\1 \1 \1!\0\1!\0\1"+
		"!\1!\1\"\0\4\"\b\"\13\"\f\"\u02a0\1#\0\1#\0\1#\0\1#\0\3#\b#\1$\1$\1%\0"+
		"\1%\0\1%\0\5%\b%\n%\f%\u02b8\t%\1&\0\1&\0\1&\0\3&\b&\1\'\0\3\'\b\'\1\'"+
		"\0\3\'\b\'\1\'\0\1\'\0\1\'\0\1\'\1\'\1(\1(\1)\0\1)\0\1)\0\5)\b)\n)\f)"+
		"\u02dd\t)\1*\0\1*\0\1*\0\3*\b*\1+\0\4+\b+\13+\f+\u02ea\1,\0\1,\0\1,\0"+
		"\3,\b,\1,\0\1,\0\1,\0\3,\b,\1,\0\1,\0\1,\0\3,\b,\1,\0\1,\0\3,\b,\3,\b"+
		",\1-\0\1-\0\1-\0\3-\b-\1-\0\1-\0\3-\b-\1.\0\1.\0\1.\1.\1/\0\1/\0\1/\0"+
		"\1/\0\5/\b/\n/\f/\u032c\t/\1\60\0\1\60\0\1\60\0\1\60\1\60\1\60\0\3\60"+
		"\b\60\1\61\0\1\61\0\3\61\b\61\1\62\0\1\62\0\3\62\b\62\1\63\0\1\63\0\1"+
		"\63\0\5\63\b\63\n\63\f\63\u034f\t\63\1\64\0\1\64\0\3\64\b\64\1\65\0\4"+
		"\65\b\65\13\65\f\65\u035a\1\66\0\1\66\0\1\66\0\3\66\b\66\1\66\0\1\66\0"+
		"\1\66\0\3\66\b\66\1\66\0\1\66\0\1\66\0\3\66\b\66\3\66\b\66\1\67\0\1\67"+
		"\0\1\67\0\3\67\b\67\1\67\0\1\67\0\3\67\b\67\18\0\18\0\18\0\38\b8\19\1"+
		"9\1:\0\1:\0\1:\0\3:\b:\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\3;\b;\3;\b;"+
		"\1<\0\1<\0\1<\0\1<\0\1<\0\1<\0\3<\b<\3<\b<\1=\0\1=\0\1=\0\1=\0\3=\b=\1"+
		">\0\1>\0\1>\0\1>\0\5>\b>\n>\f>\u03ce\t>\1>\1>\1?\0\1?\0\1?\0\1?\0\3?\b"+
		"?\1@\0\1@\0\3@\b@\1@\0\5@\b@\n@\f@\u03e7\t@\1@\0\3@\b@\1@\0\1@\1@\1A\0"+
		"\1A\0\3A\bA\1B\0\1B\0\1B\1B\1C\0\1C\0\3C\bC\1C\0\1C\0\3C\bC\3C\bC\1D\0"+
		"\1D\0\1D\0\1D\0\5D\bD\nD\fD\u0416\tD\1D\1D\1E\0\1E\0\1E\0\1E\0\1E\0\3"+
		"E\bE\3E\bE\1F\0\1F\0\1F\0\3F\bF\1G\0\1G\0\1G\0\5G\bG\nG\fG\u0439\tG\1"+
		"GH\0\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@"+
		"BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c"+
		"\u008e\0\1\18C\u040c\0\u0092\1\0\0\0\1\u043a\5\uffff\0\0\2\u00b6\1\0\0"+
		"\0\3\u0096\1\0\0\0\4\u00c0\1\0\0\0\5\u009d\1\0\0\0\6\u00c2\1\0\0\0\7\u00c1"+
		"\1\0\0\0\7\u0280\1\0\0\0\7\u03e0\1\0\0\0\b\u00cf\1\0\0\0\t\u00c6\1\0\0"+
		"\0\n\u00dd\1\0\0\0\13\u00d4\1\0\0\0\f\u00df\1\0\0\0\r\u00c1\1\0\0\0\16"+
		"\u00f6\1\0\0\0\17\u00e9\1\0\0\0\17\u00e8\1\0\0\0\20\u00f8\1\0\0\0\21\u00c1"+
		"\1\0\0\0\22\u0110\1\0\0\0\23\u00fd\1\0\0\0\24\u0112\1\0\0\0\25\u0143\1"+
		"\0\0\0\25\u0219\1\0\0\0\25\u026f\1\0\0\0\25\u0273\1\0\0\0\25\u029b\1\0"+
		"\0\0\25\u0308\1\0\0\0\25\u0372\1\0\0\0\26\u0149\1\0\0\0\27\u0143\1\0\0"+
		"\0\30\u015d\1\0\0\0\31\u0143\1\0\0\0\32\u0199\1\0\0\0\33\u026e\1\0\0\0"+
		"\33\u03f5\1\0\0\0\34\u01a4\1\0\0\0\35\u0248\1\0\0\0\35\u0284\1\0\0\0\35"+
		"\u0295\1\0\0\0\36\u01d3\1\0\0\0\37\u01b3\1\0\0\0\37\u01c5\1\0\0\0 \u01de"+
		"\1\0\0\0!\u01d4\1\0\0\0!\u01f5\1\0\0\0!\u01fd\1\0\0\0\"\u0200\1\0\0\0"+
		"#\u01eb\1\0\0\0#\u01ea\1\0\0\0$\u020c\1\0\0\0%\u014e\1\0\0\0%\u0157\1"+
		"\0\0\0%\u0162\1\0\0\0%\u016b\1\0\0\0%\u0174\1\0\0\0%\u017f\1\0\0\0%\u0188"+
		"\1\0\0\0%\u0193\1\0\0\0%\u01a9\1\0\0\0%\u01b2\1\0\0\0%\u01bb\1\0\0\0%"+
		"\u01c4\1\0\0\0%\u01d8\1\0\0\0%\u01e3\1\0\0\0&\u020e\1\0\0\0\'\u00c1\1"+
		"\0\0\0(\u0220\1\0\0\0)\u0212\1\0\0\0*\u0222\1\0\0\0+\u00a6\1\0\0\0,\u0232"+
		"\1\0\0\0-\u00a7\1\0\0\0.\u0239\1\0\0\0/\u022b\1\0\0\0/\u0231\1\0\0\0\60"+
		"\u023d\1\0\0\0\61\u023a\1\0\0\0\62\u0263\1\0\0\0\63\u025e\1\0\0\0\64\u026a"+
		"\1\0\0\0\65\u0262\1\0\0\0\66\u0270\1\0\0\0\67\u0269\1\0\0\08\u0278\1\0"+
		"\0\09\u0257\1\0\0\0:\u027f\1\0\0\0;\u0277\1\0\0\0<\u0281\1\0\0\0=\u024c"+
		"\1\0\0\0>\u0285\1\0\0\0?\u0250\1\0\0\0@\u0292\1\0\0\0A\u0254\1\0\0\0B"+
		"\u0296\1\0\0\0C\u0280\1\0\0\0C\u03e4\1\0\0\0D\u029e\1\0\0\0E\u0242\1\0"+
		"\0\0F\u02aa\1\0\0\0G\u029f\1\0\0\0H\u02ac\1\0\0\0I\u025b\1\0\0\0J\u02ae"+
		"\1\0\0\0K\u02ad\1\0\0\0L\u02b9\1\0\0\0M\u02b6\1\0\0\0M\u02b5\1\0\0\0N"+
		"\u02c3\1\0\0\0O\u023a\1\0\0\0P\u02d1\1\0\0\0Q\u02cf\1\0\0\0R\u02d3\1\0"+
		"\0\0S\u02d2\1\0\0\0S\u031e\1\0\0\0T\u02de\1\0\0\0U\u02db\1\0\0\0U\u02da"+
		"\1\0\0\0V\u02e8\1\0\0\0W\u02e4\1\0\0\0X\u030a\1\0\0\0Y\u02e9\1\0\0\0Z"+
		"\u030c\1\0\0\0[\u02f2\1\0\0\0\\\u031a\1\0\0\0]\u0302\1\0\0\0^\u0320\1"+
		"\0\0\0_\u02e5\1\0\0\0`\u0337\1\0\0\0a\u032a\1\0\0\0a\u0329\1\0\0\0b\u033d"+
		"\1\0\0\0c\u032f\1\0\0\0c\u0338\1\0\0\0d\u0343\1\0\0\0e\u0333\1\0\0\0f"+
		"\u0345\1\0\0\0g\u03ee\1\0\0\0h\u0354\1\0\0\0i\u02bf\1\0\0\0i\u034d\1\0"+
		"\0\0i\u034c\1\0\0\0j\u0358\1\0\0\0k\u0355\1\0\0\0l\u0374\1\0\0\0m\u0359"+
		"\1\0\0\0n\u0376\1\0\0\0o\u0362\1\0\0\0p\u0384\1\0\0\0q\u0375\1\0\0\0r"+
		"\u038c\1\0\0\0s\u038b\1\0\0\0t\u0394\1\0\0\0u\u02f3\1\0\0\0u\u02fb\1\0"+
		"\0\0u\u0303\1\0\0\0u\u0363\1\0\0\0u\u036b\1\0\0\0u\u038d\1\0\0\0v\u03a6"+
		"\1\0\0\0w\u02fa\1\0\0\0w\u0319\1\0\0\0x\u03b6\1\0\0\0y\u036a\1\0\0\0y"+
		"\u0383\1\0\0\0z\u03c0\1\0\0\0{\u03a7\1\0\0\0{\u03b7\1\0\0\0|\u03c2\1\0"+
		"\0\0}\u03c1\1\0\0\0~\u03d9\1\0\0\0\177\u03c1\1\0\0\0\177\u03cc\1\0\0\0"+
		"\177\u03cb\1\0\0\0\u0080\u03db\1\0\0\0\u0081\u0319\1\0\0\0\u0081\u0383"+
		"\1\0\0\0\u0081\u038a\1\0\0\0\u0082\u03f0\1\0\0\0\u0083\u03b7\1\0\0\0\u0084"+
		"\u03f6\1\0\0\0\u0085\u03a7\1\0\0\0\u0085\u03b7\1\0\0\0\u0085\u03da\1\0"+
		"\0\0\u0086\u0408\1\0\0\0\u0087\u03a7\1\0\0\0\u0087\u03b7\1\0\0\0\u0088"+
		"\u040a\1\0\0\0\u0089\u03a5\1\0\0\0\u0089\u03b5\1\0\0\0\u0089\u0401\1\0"+
		"\0\0\u0089\u0407\1\0\0\0\u008a\u0425\1\0\0\0\u008b\u0414\1\0\0\0\u008b"+
		"\u0413\1\0\0\0\u008c\u042d\1\0\0\0\u008d\u0098\1\0\0\0\u008d\u00d1\1\0"+
		"\0\0\u008d\u00f0\1\0\0\0\u008d\u00f3\1\0\0\0\u008d\u00f7\1\0\0\0\u008d"+
		"\u010a\1\0\0\0\u008d\u0218\1\0\0\0\u008d\u0221\1\0\0\0\u008d\u0226\1\0"+
		"\0\0\u008d\u029a\1\0\0\0\u008d\u02c0\1\0\0\0\u008d\u0312\1\0\0\0\u008d"+
		"\u033e\1\0\0\0\u008d\u0344\1\0\0\0\u008d\u037c\1\0\0\0\u008d\u041d\1\0"+
		"\0\0\u008d\u0437\1\0\0\0\u008d\u0436\1\0\0\0\u008e\u042f\1\0\0\0\u008f"+
		"\u00de\1\0\0\0\u008f\u028f\1\0\0\0\u008f\u028e\1\0\0\0\u008f\u0426\1\0"+
		"\0\0\u008f\u0424\1\0\0\0\u0090\u0093\5\4\0\0\u0092\u0090\1\0\0\0\u0092"+
		"\u0093\1\0\0\0\u0093\u0094\1\0\0\0\u0094\u0096\3\2\1\0\u0096\u0098\3\u008c"+
		"F\0\u0098\u009e\5\37\0\0\u009a\u009d\3\4\2\0\u009c\u009a\1\0\0\0\u009d"+
		"\u00a0\1\0\0\0\u009e\u009c\1\0\0\0\u009e\u009f\1\0\0\0\u009f\u00a1\1\0"+
		"\0\0\u00a0\u009e\1\0\0\0\u00a1\u00a7\3,\26\0\u00a3\u00a6\3*\25\0\u00a5"+
		"\u00a3\1\0\0\0\u00a6\u00a9\1\0\0\0\u00a7\u00a5\1\0\0\0\u00a7\u00a8\1\0"+
		"\0\0\u00a8\u00aa\1\0\0\0\u00a9\u00a7\1\0\0\0\u00aa\u00ab\5\uffff\0\0\u00ab"+
		"\1\1\0\0\0\u00ac\u00ae\5\17\0\0\u00ae\u00b7\5\21\0\0\u00b0\u00b2\5\20"+
		"\0\0\u00b2\u00b7\5\21\0\0\u00b4\u00b7\5\21\0\0\u00b6\u00ac\1\0\0\0\u00b6"+
		"\u00b0\1\0\0\0\u00b6\u00b4\1\0\0\0\u00b7\3\1\0\0\0\u00b8\u00c1\3\6\3\0"+
		"\u00ba\u00c1\3\f\6\0\u00bc\u00c1\3\20\b\0\u00be\u00c1\3&\23\0\u00c0\u00b8"+
		"\1\0\0\0\u00c0\u00ba\1\0\0\0\u00c0\u00bc\1\0\0\0\u00c0\u00be\1\0\0\0\u00c1"+
		"\5\1\0\0\0\u00c2\u00ca\5\13\0\0\u00c4\u00c6\3\b\4\0\u00c6\u00c7\5\37\0"+
		"\0\u00c7\u00c9\1\0\0\0\u00c8\u00c4\1\0\0\0\u00c9\u00cc\1\0\0\0\u00ca\u00c8"+
		"\1\0\0\0\u00ca\u00cb\1\0\0\0\u00cb\u00cd\1\0\0\0\u00cc\u00ca\1\0\0\0\u00cd"+
		"\u00ce\5\62\0\0\u00ce\7\1\0\0\0\u00cf\u00d1\3\u008cF\0\u00d1\u00d3\5%"+
		"\0\0\u00d3\u00d4\3\n\5\0\u00d4\t\1\0\0\0\u00d5\u00de\3\u008eG\0\u00d7"+
		"\u00de\5\65\0\0\u00d9\u00de\5\64\0\0\u00db\u00de\5\'\0\0\u00dd\u00d5\1"+
		"\0\0\0\u00dd\u00d7\1\0\0\0\u00dd\u00d9\1\0\0\0\u00dd\u00db\1\0\0\0\u00de"+
		"\13\1\0\0\0\u00df\u00e1\5\r\0\0\u00e1\u00e9\3\16\7\0\u00e3\u00e5\5\36"+
		"\0\0\u00e5\u00e8\3\16\7\0\u00e7\u00e3\1\0\0\0\u00e8\u00eb\1\0\0\0\u00e9"+
		"\u00e7\1\0\0\0\u00e9\u00ea\1\0\0\0\u00ea\u00ec\1\0\0\0\u00eb\u00e9\1\0"+
		"\0\0\u00ec\u00ed\5\37\0\0\u00ed\r\1\0\0\0\u00ee\u00f0\3\u008cF\0\u00f0"+
		"\u00f2\5%\0\0\u00f2\u00f3\3\u008cF\0\u00f3\u00f7\1\0\0\0\u00f4\u00f7\3"+
		"\u008cF\0\u00f6\u00ee\1\0\0\0\u00f6\u00f4\1\0\0\0\u00f7\17\1\0\0\0\u00f8"+
		"\u00fc\5\f\0\0\u00fa\u00fd\3\22\t\0\u00fc\u00fa\1\0\0\0\u00fd\u00fe\1"+
		"\0\0\0\u00fe\u00fc\1\0\0\0\u00fe\u00ff\1\0\0\0\u00ff\u0100\1\0\0\0\u0100"+
		"\u0101\5\62\0\0\u0101\21\1\0\0\0\u0102\u010a\3\u008cF\0\u0104\u0106\5"+
		"%\0\0\u0106\u010b\5\65\0\0\u0108\u010b\1\0\0\0\u010a\u0104\1\0\0\0\u010a"+
		"\u0108\1\0\0\0\u010b\u010c\1\0\0\0\u010c\u010d\5\37\0\0\u010d\u0111\1"+
		"\0\0\0\u010e\u0111\5\2\0\0\u0110\u0102\1\0\0\0\u0110\u010e\1\0\0\0\u0111"+
		"\23\1\0\0\0\u0112\u0144\5\n\0\0\u0114\u0143\3\24\n\0\u0116\u0143\3\26"+
		"\13\0\u0118\u0143\3\30\f\0\u011a\u0143\5X\0\0\u011c\u0143\5Y\0\0\u011e"+
		"\u0143\5U\0\0\u0120\u0143\5V\0\0\u0122\u0143\5W\0\0\u0124\u0143\5G\0\0"+
		"\u0126\u0143\5H\0\0\u0128\u0143\5I\0\0\u012a\u0143\5J\0\0\u012c\u0143"+
		"\5K\0\0\u012e\u0143\5L\0\0\u0130\u0143\5M\0\0\u0132\u0143\5N\0\0\u0134"+
		"\u0143\5R\0\0\u0136\u0143\5S\0\0\u0138\u0143\5T\0\0\u013a\u0143\5P\0\0"+
		"\u013c\u0143\5O\0\0\u013e\u0143\5Q\0\0\u0140\u0143\5F\0\0\u0142\u0114"+
		"\1\0\0\0\u0142\u0116\1\0\0\0\u0142\u0118\1\0\0\0\u0142\u011a\1\0\0\0\u0142"+
		"\u011c\1\0\0\0\u0142\u011e\1\0\0\0\u0142\u0120\1\0\0\0\u0142\u0122\1\0"+
		"\0\0\u0142\u0124\1\0\0\0\u0142\u0126\1\0\0\0\u0142\u0128\1\0\0\0\u0142"+
		"\u012a\1\0\0\0\u0142\u012c\1\0\0\0\u0142\u012e\1\0\0\0\u0142\u0130\1\0"+
		"\0\0\u0142\u0132\1\0\0\0\u0142\u0134\1\0\0\0\u0142\u0136\1\0\0\0\u0142"+
		"\u0138\1\0\0\0\u0142\u013a\1\0\0\0\u0142\u013c\1\0\0\0\u0142\u013e\1\0"+
		"\0\0\u0142\u0140\1\0\0\0\u0143\u0146\1\0\0\0\u0144\u0142\1\0\0\0\u0144"+
		"\u0145\1\0\0\0\u0145\u0147\1\0\0\0\u0146\u0144\1\0\0\0\u0147\u0148\5Z"+
		"\0\0\u0148\25\1\0\0\0\u0149\u014f\5T\0\0\u014b\u014e\3$\22\0\u014d\u014b"+
		"\1\0\0\0\u014e\u0151\1\0\0\0\u014f\u014d\1\0\0\0\u014f\u0150\1\0\0\0\u0150"+
		"\u0152\1\0\0\0\u0151\u014f\1\0\0\0\u0152\u0158\5F\0\0\u0154\u0157\3$\22"+
		"\0\u0156\u0154\1\0\0\0\u0157\u015a\1\0\0\0\u0158\u0156\1\0\0\0\u0158\u0159"+
		"\1\0\0\0\u0159\u015b\1\0\0\0\u015a\u0158\1\0\0\0\u015b\u015c\5S\0\0\u015c"+
		"\27\1\0\0\0\u015d\u0163\5T\0\0\u015f\u0162\3$\22\0\u0161\u015f\1\0\0\0"+
		"\u0162\u0165\1\0\0\0\u0163\u0161\1\0\0\0\u0163\u0164\1\0\0\0\u0164\u018c"+
		"\1\0\0\0\u0165\u0163\1\0\0\0\u0166\u016c\5K\0\0\u0168\u016b\3$\22\0\u016a"+
		"\u0168\1\0\0\0\u016b\u016e\1\0\0\0\u016c\u016a\1\0\0\0\u016c\u016d\1\0"+
		"\0\0\u016d\u0178\1\0\0\0\u016e\u016c\1\0\0\0\u016f\u0175\5Q\0\0\u0171"+
		"\u0174\3$\22\0\u0173\u0171\1\0\0\0\u0174\u0177\1\0\0\0\u0175\u0173\1\0"+
		"\0\0\u0175\u0176\1\0\0\0\u0176\u0179\1\0\0\0\u0177\u0175\1\0\0\0\u0178"+
		"\u016f\1\0\0\0\u0178\u0179\1\0\0\0\u0179\u017a\1\0\0\0\u017a\u0180\5S"+
		"\0\0\u017c\u017f\3$\22\0\u017e\u017c\1\0\0\0\u017f\u0182\1\0\0\0\u0180"+
		"\u017e\1\0\0\0\u0180\u0181\1\0\0\0\u0181\u0183\1\0\0\0\u0182\u0180\1\0"+
		"\0\0\u0183\u0189\5L\0\0\u0185\u0188\3$\22\0\u0187\u0185\1\0\0\0\u0188"+
		"\u018b\1\0\0\0\u0189\u0187\1\0\0\0\u0189\u018a\1\0\0\0\u018a\u018d\1\0"+
		"\0\0\u018b\u0189\1\0\0\0\u018c\u0166\1\0\0\0\u018c\u018d\1\0\0\0\u018d"+
		"\u018e\1\0\0\0\u018e\u0194\5O\0\0\u0190\u0193\3$\22\0\u0192\u0190\1\0"+
		"\0\0\u0193\u0196\1\0\0\0\u0194\u0192\1\0\0\0\u0194\u0195\1\0\0\0\u0195"+
		"\u0197\1\0\0\0\u0196\u0194\1\0\0\0\u0197\u0198\5S\0\0\u0198\31\1\0\0\0"+
		"\u0199\u019f\5\t\0\0\u019b\u019e\7\0\0\0\u019d\u019b\1\0\0\0\u019e\u01a1"+
		"\1\0\0\0\u019f\u019d\1\0\0\0\u019f\u01a0\1\0\0\0\u01a0\u01a2\1\0\0\0\u01a1"+
		"\u019f\1\0\0\0\u01a2\u01a3\5D\0\0\u01a3\33\1\0\0\0\u01a4\u01aa\5\t\0\0"+
		"\u01a6\u01a9\3$\22\0\u01a8\u01a6\1\0\0\0\u01a9\u01ac\1\0\0\0\u01aa\u01a8"+
		"\1\0\0\0\u01aa\u01ab\1\0\0\0\u01ab\u01cd\1\0\0\0\u01ac\u01aa\1\0\0\0\u01ad"+
		"\u01b3\3\36\17\0\u01af\u01b2\3$\22\0\u01b1\u01af\1\0\0\0\u01b2\u01b5\1"+
		"\0\0\0\u01b3\u01b1\1\0\0\0\u01b3\u01b4\1\0\0\0\u01b4\u01ca\1\0\0\0\u01b5"+
		"\u01b3\1\0\0\0\u01b6\u01bc\5=\0\0\u01b8\u01bb\3$\22\0\u01ba\u01b8\1\0"+
		"\0\0\u01bb\u01be\1\0\0\0\u01bc\u01ba\1\0\0\0\u01bc\u01bd\1\0\0\0\u01bd"+
		"\u01bf\1\0\0\0\u01be\u01bc\1\0\0\0\u01bf\u01c5\3\36\17\0\u01c1\u01c4\3"+
		"$\22\0\u01c3\u01c1\1\0\0\0\u01c4\u01c7\1\0\0\0\u01c5\u01c3\1\0\0\0\u01c5"+
		"\u01c6\1\0\0\0\u01c6\u01c9\1\0\0\0\u01c7\u01c5\1\0\0\0\u01c8\u01b6\1\0"+
		"\0\0\u01c9\u01cc\1\0\0\0\u01ca\u01c8\1\0\0\0\u01ca\u01cb\1\0\0\0\u01cb"+
		"\u01ce\1\0\0\0\u01cc\u01ca\1\0\0\0\u01cd\u01ad\1\0\0\0\u01cd\u01ce\1\0"+
		"\0\0\u01ce\u01cf\1\0\0\0\u01cf\u01d0\5D\0\0\u01d0\35\1\0\0\0\u01d1\u01d4"+
		"\3 \20\0\u01d3\u01d1\1\0\0\0\u01d3\u01d4\1\0\0\0\u01d4\u01d9\1\0\0\0\u01d5"+
		"\u01d8\3$\22\0\u01d7\u01d5\1\0\0\0\u01d8\u01db\1\0\0\0\u01d9\u01d7\1\0"+
		"\0\0\u01d9\u01da\1\0\0\0\u01da\u01dc\1\0\0\0\u01db\u01d9\1\0\0\0\u01dc"+
		"\u01dd\5?\0\0\u01dd\37\1\0\0\0\u01de\u01eb\3\"\21\0\u01e0\u01e3\3$\22"+
		"\0\u01e2\u01e0\1\0\0\0\u01e3\u01e6\1\0\0\0\u01e4\u01e2\1\0\0\0\u01e4\u01e5"+
		"\1\0\0\0\u01e5\u01e7\1\0\0\0\u01e6\u01e4\1\0\0\0\u01e7\u01ea\3\"\21\0"+
		"\u01e9\u01e4\1\0\0\0\u01ea\u01ed\1\0\0\0\u01eb\u01e9\1\0\0\0\u01eb\u01ec"+
		"\1\0\0\0\u01ec!\1\0\0\0\u01ed\u01eb\1\0\0\0\u01ee\u0201\5?\0\0\u01f0\u01f4"+
		"\58\0\0\u01f2\u01f5\3 \20\0\u01f4\u01f2\1\0\0\0\u01f4\u01f5\1\0\0\0\u01f5"+
		"\u01f6\1\0\0\0\u01f6\u0201\59\0\0\u01f8\u01fc\5:\0\0\u01fa\u01fd\3 \20"+
		"\0\u01fc\u01fa\1\0\0\0\u01fc\u01fd\1\0\0\0\u01fd\u01fe\1\0\0\0\u01fe\u0201"+
		"\5;\0\0\u0200\u01ee\1\0\0\0\u0200\u01f0\1\0\0\0\u0200\u01f8\1\0\0\0\u0201"+
		"#\1\0\0\0\u0202\u020d\5X\0\0\u0204\u020d\5Y\0\0\u0206\u020d\5U\0\0\u0208"+
		"\u020d\5B\0\0\u020a\u020d\5C\0\0\u020c\u0202\1\0\0\0\u020c\u0204\1\0\0"+
		"\0\u020c\u0206\1\0\0\0\u020c\u0208\1\0\0\0\u020c\u020a\1\0\0\0\u020d%"+
		"\1\0\0\0\u020e\u0214\5/\0\0\u0210\u0212\3(\24\0\u0212\u0213\5\35\0\0\u0213"+
		"\u0215\1\0\0\0\u0214\u0210\1\0\0\0\u0214\u0215\1\0\0\0\u0215\u0216\1\0"+
		"\0\0\u0216\u0218\3\u008cF\0\u0218\u0219\3\24\n\0\u0219\'\1\0\0\0\u021a"+
		"\u0221\3\u008cF\0\u021c\u0221\5\17\0\0\u021e\u0221\5\20\0\0\u0220\u021a"+
		"\1\0\0\0\u0220\u021c\1\0\0\0\u0220\u021e\1\0\0\0\u0221)\1\0\0\0\u0222"+
		"\u0224\5\33\0\0\u0224\u0226\3\u008cF\0\u0226\u022a\5\37\0\0\u0228\u022b"+
		"\3.\27\0\u022a\u0228\1\0\0\0\u022b\u022c\1\0\0\0\u022c\u022a\1\0\0\0\u022c"+
		"\u022d\1\0\0\0\u022d+\1\0\0\0\u022e\u0231\3.\27\0\u0230\u022e\1\0\0\0"+
		"\u0231\u0234\1\0\0\0\u0232\u0230\1\0\0\0\u0232\u0233\1\0\0\0\u0233-\1"+
		"\0\0\0\u0234\u0232\1\0\0\0\u0235\u023a\3\60\30\0\u0237\u023a\3N\'\0\u0239"+
		"\u0235\1\0\0\0\u0239\u0237\1\0\0\0\u023a/\1\0\0\0\u023b\u023e\5\4\0\0"+
		"\u023d\u023b\1\0\0\0\u023d\u023e\1\0\0\0\u023e\u0241\1\0\0\0\u023f\u0242"+
		"\3D\"\0\u0241\u023f\1\0\0\0\u0241\u0242\1\0\0\0\u0242\u0243\1\0\0\0\u0243"+
		"\u0247\5\2\0\0\u0245\u0248\3\34\16\0\u0247\u0245\1\0\0\0\u0247\u0248\1"+
		"\0\0\0\u0248\u024b\1\0\0\0\u0249\u024c\3<\36\0\u024b\u0249\1\0\0\0\u024b"+
		"\u024c\1\0\0\0\u024c\u024f\1\0\0\0\u024d\u0250\3>\37\0\u024f\u024d\1\0"+
		"\0\0\u024f\u0250\1\0\0\0\u0250\u0253\1\0\0\0\u0251\u0254\3@ \0\u0253\u0251"+
		"\1\0\0\0\u0253\u0254\1\0\0\0\u0254\u0255\1\0\0\0\u0255\u0257\38\34\0\u0257"+
		"\u0259\5\34\0\0\u0259\u025b\3H$\0\u025b\u025d\5\37\0\0\u025d\u025e\3\62"+
		"\31\0\u025e\61\1\0\0\0\u025f\u0262\3\64\32\0\u0261\u025f\1\0\0\0\u0262"+
		"\u0265\1\0\0\0\u0263\u0261\1\0\0\0\u0263\u0264\1\0\0\0\u0264\u0268\1\0"+
		"\0\0\u0265\u0263\1\0\0\0\u0266\u0269\3\66\33\0\u0268\u0266\1\0\0\0\u0268"+
		"\u0269\1\0\0\0\u0269\63\1\0\0\0\u026a\u026c\5\30\0\0\u026c\u026e\3\32"+
		"\r\0\u026e\u026f\3\24\n\0\u026f\65\1\0\0\0\u0270\u0272\5\31\0\0\u0272"+
		"\u0273\3\24\n\0\u0273\67\1\0\0\0\u0274\u0277\3:\35\0\u0276\u0274\1\0\0"+
		"\0\u0277\u027a\1\0\0\0\u0278\u0276\1\0\0\0\u0278\u0279\1\0\0\0\u02799"+
		"\1\0\0\0\u027a\u0278\1\0\0\0\u027b\u0280\3\6\3\0\u027d\u0280\3B!\0\u027f"+
		"\u027b\1\0\0\0\u027f\u027d\1\0\0\0\u0280;\1\0\0\0\u0281\u0283\5\25\0\0"+
		"\u0283\u0284\3\34\16\0\u0284=\1\0\0\0\u0285\u0287\5\27\0\0\u0287\u028f"+
		"\3\u008eG\0\u0289\u028b\5\36\0\0\u028b\u028e\3\u008eG\0\u028d\u0289\1"+
		"\0\0\0\u028e\u0291\1\0\0\0\u028f\u028d\1\0\0\0\u028f\u0290\1\0\0\0\u0290"+
		"?\1\0\0\0\u0291\u028f\1\0\0\0\u0292\u0294\5\26\0\0\u0294\u0295\3\34\16"+
		"\0\u0295A\1\0\0\0\u0296\u0298\5/\0\0\u0298\u029a\3\u008cF\0\u029a\u029b"+
		"\3\24\n\0\u029bC\1\0\0\0\u029c\u029f\3F#\0\u029e\u029c\1\0\0\0\u029f\u02a0"+
		"\1\0\0\0\u02a0\u029e\1\0\0\0\u02a0\u02a1\1\0\0\0\u02a1E\1\0\0\0\u02a2"+
		"\u02ab\5\23\0\0\u02a4\u02ab\5\24\0\0\u02a6\u02ab\5\22\0\0\u02a8\u02ab"+
		"\5\16\0\0\u02aa\u02a2\1\0\0\0\u02aa\u02a4\1\0\0\0\u02aa\u02a6\1\0\0\0"+
		"\u02aa\u02a8\1\0\0\0\u02abG\1\0\0\0\u02ac\u02ad\3J%\0\u02adI\1\0\0\0\u02ae"+
		"\u02b6\3L&\0\u02b0\u02b2\5*\0\0\u02b2\u02b5\3L&\0\u02b4\u02b0\1\0\0\0"+
		"\u02b5\u02b8\1\0\0\0\u02b6\u02b4\1\0\0\0\u02b6\u02b7\1\0\0\0\u02b7K\1"+
		"\0\0\0\u02b8\u02b6\1\0\0\0\u02b9\u02bf\3h\64\0\u02bb\u02bd\5\60\0\0\u02bd"+
		"\u02c0\3\u008cF\0\u02bf\u02bb\1\0\0\0\u02bf\u02c0\1\0\0\0\u02c0M\1\0\0"+
		"\0\u02c1\u02c4\5\4\0\0\u02c3\u02c1\1\0\0\0\u02c3\u02c4\1\0\0\0\u02c4\u02c7"+
		"\1\0\0\0\u02c5\u02c8\5\16\0\0\u02c7\u02c5\1\0\0\0\u02c7\u02c8\1\0\0\0"+
		"\u02c8\u02c9\1\0\0\0\u02c9\u02cb\5\1\0\0\u02cb\u02cd\5\34\0\0\u02cd\u02cf"+
		"\3P(\0\u02cf\u02d0\5\37\0\0\u02d0O\1\0\0\0\u02d1\u02d2\3R)\0\u02d2Q\1"+
		"\0\0\0\u02d3\u02db\3T*\0\u02d5\u02d7\5*\0\0\u02d7\u02da\3T*\0\u02d9\u02d5"+
		"\1\0\0\0\u02da\u02dd\1\0\0\0\u02db\u02d9\1\0\0\0\u02db\u02dc\1\0\0\0\u02dc"+
		"S\1\0\0\0\u02dd\u02db\1\0\0\0\u02de\u02e4\3V+\0\u02e0\u02e5\3^/\0\u02e2"+
		"\u02e5\1\0\0\0\u02e4\u02e0\1\0\0\0\u02e4\u02e2\1\0\0\0\u02e5U\1\0\0\0"+
		"\u02e6\u02e9\3X,\0\u02e8\u02e6\1\0\0\0\u02e9\u02ea\1\0\0\0\u02ea\u02e8"+
		"\1\0\0\0\u02ea\u02eb\1\0\0\0\u02ebW\1\0\0\0\u02ec\u02f2\3Z-\0\u02ee\u02f3"+
		"\3t:\0\u02f0\u02f3\1\0\0\0\u02f2\u02ee\1\0\0\0\u02f2\u02f0\1\0\0\0\u02f3"+
		"\u030b\1\0\0\0\u02f4\u02fa\3v;\0\u02f6\u02fb\3t:\0\u02f8\u02fb\1\0\0\0"+
		"\u02fa\u02f6\1\0\0\0\u02fa\u02f8\1\0\0\0\u02fb\u030b\1\0\0\0\u02fc\u0302"+
		"\3\\.\0\u02fe\u0303\3t:\0\u0300\u0303\1\0\0\0\u0302\u02fe\1\0\0\0\u0302"+
		"\u0300\1\0\0\0\u0303\u030b\1\0\0\0\u0304\u0308\3\24\n\0\u0306\u0309\5"+
		"&\0\0\u0308\u0306\1\0\0\0\u0308\u0309\1\0\0\0\u0309\u030b\1\0\0\0\u030a"+
		"\u02ec\1\0\0\0\u030a\u02f4\1\0\0\0\u030a\u02fc\1\0\0\0\u030a\u0304\1\0"+
		"\0\0\u030bY\1\0\0\0\u030c\u0312\3\u008cF\0\u030e\u0313\5%\0\0\u0310\u0313"+
		"\5)\0\0\u0312\u030e\1\0\0\0\u0312\u0310\1\0\0\0\u0313\u0318\1\0\0\0\u0314"+
		"\u0319\3v;\0\u0316\u0319\3\u0080@\0\u0318\u0314\1\0\0\0\u0318\u0316\1"+
		"\0\0\0\u0319[\1\0\0\0\u031a\u031c\5 \0\0\u031c\u031e\3R)\0\u031e\u031f"+
		"\5!\0\0\u031f]\1\0\0\0\u0320\u0322\5\"\0\0\u0322\u032a\3`\60\0\u0324\u0326"+
		"\5\36\0\0\u0326\u0329\3`\60\0\u0328\u0324\1\0\0\0\u0329\u032c\1\0\0\0"+
		"\u032a\u0328\1\0\0\0\u032a\u032b\1\0\0\0\u032b_\1\0\0\0\u032c\u032a\1"+
		"\0\0\0\u032d\u032f\3b\61\0\u032f\u0331\5 \0\0\u0331\u0333\3d\62\0\u0333"+
		"\u0334\5!\0\0\u0334\u0338\1\0\0\0\u0335\u0338\3b\61\0\u0337\u032d\1\0"+
		"\0\0\u0337\u0335\1\0\0\0\u0338a\1\0\0\0\u0339\u033e\3\u008cF\0\u033b\u033e"+
		"\5\33\0\0\u033d\u0339\1\0\0\0\u033d\u033b\1\0\0\0\u033ec\1\0\0\0\u033f"+
		"\u0344\3\u008cF\0\u0341\u0344\5\64\0\0\u0343\u033f\1\0\0\0\u0343\u0341"+
		"\1\0\0\0\u0344e\1\0\0\0\u0345\u034d\3h\64\0\u0347\u0349\5*\0\0\u0349\u034c"+
		"\3h\64\0\u034b\u0347\1\0\0\0\u034c\u034f\1\0\0\0\u034d\u034b\1\0\0\0\u034d"+
		"\u034e\1\0\0\0\u034eg\1\0\0\0\u034f\u034d\1\0\0\0\u0350\u0355\3j\65\0"+
		"\u0352\u0355\1\0\0\0\u0354\u0350\1\0\0\0\u0354\u0352\1\0\0\0\u0355i\1"+
		"\0\0\0\u0356\u0359\3l\66\0\u0358\u0356\1\0\0\0\u0359\u035a\1\0\0\0\u035a"+
		"\u0358\1\0\0\0\u035a\u035b\1\0\0\0\u035bk\1\0\0\0\u035c\u0362\3n\67\0"+
		"\u035e\u0363\3t:\0\u0360\u0363\1\0\0\0\u0362\u035e\1\0\0\0\u0362\u0360"+
		"\1\0\0\0\u0363\u0375\1\0\0\0\u0364\u036a\3x<\0\u0366\u036b\3t:\0\u0368"+
		"\u036b\1\0\0\0\u036a\u0366\1\0\0\0\u036a\u0368\1\0\0\0\u036b\u0375\1\0"+
		"\0\0\u036c\u0375\3p8\0\u036e\u0372\3\24\n\0\u0370\u0373\5&\0\0\u0372\u0370"+
		"\1\0\0\0\u0372\u0373\1\0\0\0\u0373\u0375\1\0\0\0\u0374\u035c\1\0\0\0\u0374"+
		"\u0364\1\0\0\0\u0374\u036c\1\0\0\0\u0374\u036e\1\0\0\0\u0375m\1\0\0\0"+
		"\u0376\u037c\3\u008cF\0\u0378\u037d\5%\0\0\u037a\u037d\5)\0\0\u037c\u0378"+
		"\1\0\0\0\u037c\u037a\1\0\0\0\u037d\u0382\1\0\0\0\u037e\u0383\3x<\0\u0380"+
		"\u0383\3\u0080@\0\u0382\u037e\1\0\0\0\u0382\u0380\1\0\0\0\u0383o\1\0\0"+
		"\0\u0384\u038a\3\u0080@\0\u0386\u038b\3r9\0\u0388\u038b\1\0\0\0\u038a"+
		"\u0386\1\0\0\0\u038a\u0388\1\0\0\0\u038bq\1\0\0\0\u038c\u038d\3t:\0\u038d"+
		"s\1\0\0\0\u038e\u0395\5&\0\0\u0390\u0395\5\'\0\0\u0392\u0395\5(\0\0\u0394"+
		"\u038e\1\0\0\0\u0394\u0390\1\0\0\0\u0394\u0392\1\0\0\0\u0395u\1\0\0\0"+
		"\u0396\u03a7\3\u0084B\0\u0398\u03a7\3\u0086C\0\u039a\u03a7\5\2\0\0\u039c"+
		"\u03a7\3z=\0\u039e\u03a7\5\3\0\0\u03a0\u03a4\5,\0\0\u03a2\u03a5\3\u0088"+
		"D\0\u03a4\u03a2\1\0\0\0\u03a4\u03a5\1\0\0\0\u03a5\u03a7\1\0\0\0\u03a6"+
		"\u0396\1\0\0\0\u03a6\u0398\1\0\0\0\u03a6\u039a\1\0\0\0\u03a6\u039c\1\0"+
		"\0\0\u03a6\u039e\1\0\0\0\u03a6\u03a0\1\0\0\0\u03a7w\1\0\0\0\u03a8\u03b7"+
		"\3\u0084B\0\u03aa\u03b7\3\u0086C\0\u03ac\u03b7\3\u0082A\0\u03ae\u03b7"+
		"\3z=\0\u03b0\u03b4\5,\0\0\u03b2\u03b5\3\u0088D\0\u03b4\u03b2\1\0\0\0\u03b4"+
		"\u03b5\1\0\0\0\u03b5\u03b7\1\0\0\0\u03b6\u03a8\1\0\0\0\u03b6\u03aa\1\0"+
		"\0\0\u03b6\u03ac\1\0\0\0\u03b6\u03ae\1\0\0\0\u03b6\u03b0\1\0\0\0\u03b7"+
		"y\1\0\0\0\u03b8\u03ba\5\61\0\0\u03ba\u03c1\3~?\0\u03bc\u03be\5\61\0\0"+
		"\u03be\u03c1\3|>\0\u03c0\u03b8\1\0\0\0\u03c0\u03bc\1\0\0\0\u03c1{\1\0"+
		"\0\0\u03c2\u03c4\5 \0\0\u03c4\u03cc\3~?\0\u03c6\u03c8\5*\0\0\u03c8\u03cb"+
		"\3~?\0\u03ca\u03c6\1\0\0\0\u03cb\u03ce\1\0\0\0\u03cc\u03ca\1\0\0\0\u03cc"+
		"\u03cd\1\0\0\0\u03cd\u03cf\1\0\0\0\u03ce\u03cc\1\0\0\0\u03cf\u03d0\5!"+
		"\0\0\u03d0}\1\0\0\0\u03d1\u03da\5\1\0\0\u03d3\u03da\5\65\0\0\u03d5\u03da"+
		"\3\u0084B\0\u03d7\u03da\5\3\0\0\u03d9\u03d1\1\0\0\0\u03d9\u03d3\1\0\0"+
		"\0\u03d9\u03d5\1\0\0\0\u03d9\u03d7\1\0\0\0\u03da\177\1\0\0\0\u03db\u03ea"+
		"\5 \0\0\u03dd\u03e0\3\6\3\0\u03df\u03dd\1\0\0\0\u03df\u03e0\1\0\0\0\u03e0"+
		"\u03e5\1\0\0\0\u03e1\u03e4\3B!\0\u03e3\u03e1\1\0\0\0\u03e4\u03e7\1\0\0"+
		"\0\u03e5\u03e3\1\0\0\0\u03e5\u03e6\1\0\0\0\u03e6\u03e8\1\0\0\0\u03e7\u03e5"+
		"\1\0\0\0\u03e8\u03eb\5\34\0\0\u03ea\u03df\1\0\0\0\u03ea\u03eb\1\0\0\0"+
		"\u03eb\u03ec\1\0\0\0\u03ec\u03ee\3f\63\0\u03ee\u03ef\5!\0\0\u03ef\u0081"+
		"\1\0\0\0\u03f0\u03f4\5\2\0\0\u03f2\u03f5\3\32\r\0\u03f4\u03f2\1\0\0\0"+
		"\u03f4\u03f5\1\0\0\0\u03f5\u0083\1\0\0\0\u03f6\u03f8\5\65\0\0\u03f8\u03fa"+
		"\5-\0\0\u03fa\u03fb\5\65\0\0\u03fb\u0085\1\0\0\0\u03fc\u0400\5\1\0\0\u03fe"+
		"\u0401\3\u0088D\0\u0400\u03fe\1\0\0\0\u0400\u0401\1\0\0\0\u0401\u0409"+
		"\1\0\0\0\u0402\u0406\5\65\0\0\u0404\u0407\3\u0088D\0\u0406\u0404\1\0\0"+
		"\0\u0406\u0407\1\0\0\0\u0407\u0409\1\0\0\0\u0408\u03fc\1\0\0\0\u0408\u0402"+
		"\1\0\0\0\u0409\u0087\1\0\0\0\u040a\u040c\5#\0\0\u040c\u0414\3\u008aE\0"+
		"\u040e\u0410\5\36\0\0\u0410\u0413\3\u008aE\0\u0412\u040e\1\0\0\0\u0413"+
		"\u0416\1\0\0\0\u0414\u0412\1\0\0\0\u0414\u0415\1\0\0\0\u0415\u0417\1\0"+
		"\0\0\u0416\u0414\1\0\0\0\u0417\u0418\5$\0\0\u0418\u0089\1\0\0\0\u0419"+
		"\u0426\3\u008eG\0\u041b\u041d\3\u008cF\0\u041d\u0423\5%\0\0\u041f\u0424"+
		"\3\u008eG\0\u0421\u0424\5\65\0\0\u0423\u041f\1\0\0\0\u0423\u0421\1\0\0"+
		"\0\u0424\u0426\1\0\0\0\u0425\u0419\1\0\0\0\u0425\u041b\1\0\0\0\u0426\u008b"+
		"\1\0\0\0\u0427\u042e\5\2\0\0\u0429\u042e\5\1\0\0\u042b\u042e\5\32\0\0"+
		"\u042d\u0427\1\0\0\0\u042d\u0429\1\0\0\0\u042d\u042b\1\0\0\0\u042e\u008d"+
		"\1\0\0\0\u042f\u0437\3\u008cF\0\u0431\u0433\5,\0\0\u0433\u0436\3\u008c"+
		"F\0\u0435\u0431\1\0\0\0\u0436\u0439\1\0\0\0\u0437\u0435\1\0\0\0\u0437"+
		"\u0438\1\0\0\0\u0438\u008f\1\0\0\0\u0439\u0437\1\0\0\0i\u0092\1\u009e"+
		"\1\u00a7\1\u00b6\1\u00c0\1\u00ca\1\u00dd\1\u00e9\1\u00f6\1\u00fe\1\u010a"+
		"\1\u0110\1\u0142\1\u0144\1\u014f\1\u0158\1\u0163\1\u016c\1\u0175\1\u0178"+
		"\1\u0180\1\u0189\1\u018c\1\u0194\1\u019f\1\u01aa\1\u01b3\1\u01bc\1\u01c5"+
		"\1\u01ca\1\u01cd\1\u01d3\1\u01d9\1\u01e4\1\u01eb\1\u01f4\1\u01fc\1\u0200"+
		"\1\u020c\1\u0214\1\u0220\1\u022c\1\u0232\1\u0239\1\u023d\1\u0241\1\u0247"+
		"\1\u024b\1\u024f\1\u0253\1\u0263\1\u0268\1\u0278\1\u027f\1\u028f\1\u02a0"+
		"\1\u02aa\1\u02b6\1\u02bf\1\u02c3\1\u02c7\1\u02db\1\u02e4\1\u02ea\1\u02f2"+
		"\1\u02fa\1\u0302\1\u0308\1\u030a\1\u0312\1\u0318\1\u032a\1\u0337\1\u033d"+
		"\1\u0343\1\u034d\1\u0354\1\u035a\1\u0362\1\u036a\1\u0372\1\u0374\1\u037c"+
		"\1\u0382\1\u038a\1\u0394\1\u03a4\1\u03a6\1\u03b4\1\u03b6\1\u03c0\1\u03cc"+
		"\1\u03d9\1\u03df\1\u03e5\1\u03ea\1\u03f4\1\u0400\1\u0406\1\u0408\1\u0414"+
		"\1\u0423\1\u0425\1\u042d\1\u0437\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}