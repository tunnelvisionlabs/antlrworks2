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
		DOLLAR=43, PROTECTED=18, LT=35, ACTION_COLON=78, STAR=39, DOUBLE_ANGLE_STRING_LITERAL=8, 
		FRAGMENT=14, ACTION_ESCAPE=80, ACTION_REFERENCE=82, ACTION_COLON2=77, 
		ACTION_DOT=68, NOT=48, ID=50, ARG_ACTION_LT=55, ACTION_NEWLINE=87, TOKEN_REF=1, 
		LPAREN=32, ARG_ACTION_ELEMENT=63, ARG_ACTION_RPAREN=58, ARG_ACTION_GT=56, 
		AT=46, LEXER_CHAR_SET=3, RPAREN=33, ARG_ACTION_LPAREN=57, IMPORT=13, STRING_LITERAL=52, 
		END_ACTION=88, COMMA=30, ACTION_GT=70, ACTION_LITERAL=84, ARG_ACTION_NEWLINE=66, 
		DOC_COMMENT=4, BLOCK_COMMENT=5, PLUS=40, BEGIN_ACTION=10, DOUBLE_QUOTE_STRING_LITERAL=7, 
		DOT=44, ACTION_LBRACK=73, MODE=27, ACTION_WORD=81, GRAMMAR=17, ARG_ACTION_ESCAPE=61, 
		RETURNS=21, ARG_ACTION_TEXT=64, ACTION_EQUALS=75, LOCALS=22, ACTION_WS=86, 
		RBRACE=49, POUND=47, LINE_COMMENT=6, PRIVATE=20, RARROW=34, END_ARG_ACTION=67, 
		TOKENS=12, RANGE=45, THROWS=23, INT=51, SEMI=31, RULE_REF=2, ARG_ACTION_EQUALS=59, 
		ACTION_RPAREN=72, ACTION_COMMA=76, COLON=28, COLONCOLON=29, ACTION_RBRACK=74, 
		WS=53, ACTION_COMMENT=83, QUESTION=38, FINALLY=25, ACTION_LT=69, TEMPLATE=26, 
		ACTION_TEXT=85, LEXER=15, ERRCHAR=54, ACTION_MINUS=79, ARG_ACTION_COMMA=60, 
		OR=42, ASSIGN=37, PLUS_ASSIGN=41, ARG_ACTION_WS=65, GT=36, CATCH=24, ARG_ACTION_WORD=62, 
		PUBLIC=19, PARSER=16, ACTION_LPAREN=71, OPTIONS=11, BEGIN_ARG_ACTION=9, 
		RULE=89, PREC_RULE=90, RULES=91, RULEMODIFIERS=92, RULEACTIONS=93, BLOCK=94, 
		OPTIONAL=95, CLOSURE=96, POSITIVE_CLOSURE=97, SET=98, CHAR_RANGE=99, EPSILON=100, 
		ALT=101, ALTLIST=102, ARG=103, ARGLIST=104, RET=105, COMBINED=106, INITACTION=107, 
		LABEL=108, WILDCARD=109, LIST=110, ELEMENT_OPTIONS=111, RESULT=112, LEXER_ALT_ACTION=113, 
		LEXER_ACTION_CALL=114;
	public static final String[] tokenNames = {
        "<INVALID>", "TOKEN_REF", "RULE_REF", "LEXER_CHAR_SET", "DOC_COMMENT", 
        "BLOCK_COMMENT", "LINE_COMMENT", "DOUBLE_QUOTE_STRING_LITERAL", 
        "DOUBLE_ANGLE_STRING_LITERAL", "BEGIN_ARG_ACTION", "'{'", "OPTIONS", 
        "TOKENS", "'import'", "'fragment'", "'lexer'", "'parser'", "'grammar'", 
        "'protected'", "'public'", "'private'", "'returns'", "'locals'", 
        "'throws'", "'catch'", "'finally'", "'template'", "'mode'", "COLON", 
        "COLONCOLON", "COMMA", "';'", "LPAREN", "RPAREN", "'->'", "LT", 
        "GT", "ASSIGN", "'?'", "'*'", "'+'", "'+='", "'|'", "'$'", "DOT", 
        "'..'", "'@'", "'#'", "'~'", "RBRACE", "ID", "INT", "STRING_LITERAL", 
        "WS", "ERRCHAR", "ARG_ACTION_LT", "ARG_ACTION_GT", "ARG_ACTION_LPAREN", 
        "ARG_ACTION_RPAREN", "ARG_ACTION_EQUALS", "ARG_ACTION_COMMA", "ARG_ACTION_ESCAPE", 
        "ARG_ACTION_WORD", "ARG_ACTION_ELEMENT", "ARG_ACTION_TEXT", "ARG_ACTION_WS", 
        "ARG_ACTION_NEWLINE", "END_ARG_ACTION", "ACTION_DOT", "ACTION_LT", 
        "ACTION_GT", "ACTION_LPAREN", "ACTION_RPAREN", "ACTION_LBRACK", 
        "ACTION_RBRACK", "ACTION_EQUALS", "ACTION_COMMA", "ACTION_COLON2", 
        "ACTION_COLON", "'-'", "ACTION_ESCAPE", "ACTION_WORD", "ACTION_REFERENCE", 
        "ACTION_COMMENT", "ACTION_LITERAL", "ACTION_TEXT", "ACTION_WS", 
        "ACTION_NEWLINE", "END_ACTION", "RULE", "PREC_RULE", "RULES", "RULEMODIFIERS", 
        "RULEACTIONS", "BLOCK", "OPTIONAL", "CLOSURE", "POSITIVE_CLOSURE", 
        "SET", "CHAR_RANGE", "EPSILON", "ALT", "ALTLIST", "ARG", "ARGLIST", 
        "RET", "COMBINED", "INITACTION", "LABEL", "WILDCARD", "LIST", "ELEMENT_OPTIONS", 
        "RESULT", "LEXER_ALT_ACTION", "LEXER_ACTION_CALL"
	};
	public static final int
		RULE_grammarSpec = 0, RULE_grammarType = 1, RULE_prequelConstruct = 2, 
		RULE_optionsSpec = 3, RULE_option = 4, RULE_optionValue = 5, RULE_delegateGrammars = 6, 
		RULE_delegateGrammar = 7, RULE_tokensSpec = 8, RULE_actionBlock = 9, RULE_actionExpression = 10, 
		RULE_actionScopeExpression = 11, RULE_argActionBlock = 12, RULE_argActionParameters = 13, 
		RULE_argActionParameter = 14, RULE_argActionParameterType = 15, RULE_argActionParameterTypePart = 16, 
		RULE_ignored = 17, RULE_action = 18, RULE_actionScopeName = 19, RULE_modeSpec = 20, 
		RULE_rules = 21, RULE_ruleSpec = 22, RULE_parserRuleSpec = 23, RULE_exceptionGroup = 24, 
		RULE_exceptionHandler = 25, RULE_finallyClause = 26, RULE_rulePrequels = 27, 
		RULE_rulePrequel = 28, RULE_ruleReturns = 29, RULE_throwsSpec = 30, RULE_localsSpec = 31, 
		RULE_ruleAction = 32, RULE_ruleModifiers = 33, RULE_ruleModifier = 34, 
		RULE_ruleBlock = 35, RULE_ruleAltList = 36, RULE_labeledAlt = 37, RULE_lexerRule = 38, 
		RULE_lexerRuleBlock = 39, RULE_lexerAltList = 40, RULE_lexerAlt = 41, 
		RULE_lexerElements = 42, RULE_lexerElement = 43, RULE_labeledLexerElement = 44, 
		RULE_lexerBlock = 45, RULE_lexerCommands = 46, RULE_lexerCommand = 47, 
		RULE_lexerCommandName = 48, RULE_lexerCommandExpr = 49, RULE_altList = 50, 
		RULE_alternative = 51, RULE_elements = 52, RULE_element = 53, RULE_labeledElement = 54, 
		RULE_ebnf = 55, RULE_blockSuffix = 56, RULE_ebnfSuffix = 57, RULE_lexerAtom = 58, 
		RULE_atom = 59, RULE_notSet = 60, RULE_blockSet = 61, RULE_setElement = 62, 
		RULE_block = 63, RULE_ruleref = 64, RULE_range = 65, RULE_terminal = 66, 
		RULE_elementOptions = 67, RULE_elementOption = 68, RULE_id = 69, RULE_qid = 70;
	public static final String[] ruleNames = {
		"grammarSpec", "grammarType", "prequelConstruct", "optionsSpec", "option", 
		"optionValue", "delegateGrammars", "delegateGrammar", "tokensSpec", "actionBlock", 
		"actionExpression", "actionScopeExpression", "argActionBlock", "argActionParameters", 
		"argActionParameter", "argActionParameterType", "argActionParameterTypePart", 
		"ignored", "action", "actionScopeName", "modeSpec", "rules", "ruleSpec", 
		"parserRuleSpec", "exceptionGroup", "exceptionHandler", "finallyClause", 
		"rulePrequels", "rulePrequel", "ruleReturns", "throwsSpec", "localsSpec", 
		"ruleAction", "ruleModifiers", "ruleModifier", "ruleBlock", "ruleAltList", 
		"labeledAlt", "lexerRule", "lexerRuleBlock", "lexerAltList", "lexerAlt", 
		"lexerElements", "lexerElement", "labeledLexerElement", "lexerBlock", 
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
			setState(143);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(142); match(DOC_COMMENT);
					}
					break;
			}
			setState(145); grammarType();
			setState(146); id();
			setState(147); match(SEMI);
			setState(151);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(148); prequelConstruct();
					}
					} 
				}
				setState(153);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(154); rules();
			setState(158);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(155); modeSpec();
					}
					} 
				}
				setState(160);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(161); match(EOF);
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
			setState(168);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(163); match(LEXER);
					setState(164); match(GRAMMAR);
					}
					break;

				case 2:
					{
					setState(165); match(PARSER);
					setState(166); match(GRAMMAR);
					}
					break;

				case 3:
					{
					setState(167); match(GRAMMAR);
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
			setState(174);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(170); optionsSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(171); delegateGrammars();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(172); tokensSpec();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(173); action();
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
			setState(176); match(OPTIONS);
			setState(182);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(177); option();
					setState(178); match(SEMI);
					}
					} 
				}
				setState(184);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(185); match(RBRACE);
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
			setState(187); id();
			setState(188); match(ASSIGN);
			setState(189); optionValue();
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
			setState(195);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(191); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(192); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(193); match(INT);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(194); match(STAR);
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
			setState(197); match(IMPORT);
			setState(198); delegateGrammar();
			setState(203);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(199); match(COMMA);
					setState(200); delegateGrammar();
					}
					} 
				}
				setState(205);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(206); match(SEMI);
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
			setState(213);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(208); id();
					setState(209); match(ASSIGN);
					setState(210); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(212); id();
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
		public List<? extends IdContext> id() {
		    return getRuleContexts(IdContext.class);
		}
		public TerminalNode<Token> TOKENS() { return getToken(AbstractGrammarParser.TOKENS, 0); }
		public TerminalNode<Token> COMMA(int i) {
		    return getToken(AbstractGrammarParser.COMMA, i);
		}
		public TerminalNode<Token> RBRACE() { return getToken(AbstractGrammarParser.RBRACE, 0); }
		public List<? extends TerminalNode<Token>> COMMA() { return getTokens(AbstractGrammarParser.COMMA); }
		public IdContext id(int i) {
		    return getRuleContext(IdContext.class,i);
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

	@RuleVersion(1)
	public final TokensSpecContext tokensSpec() throws RecognitionException {
		TokensSpecContext _localctx = new TokensSpecContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_tokensSpec);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(215); match(TOKENS);
			setState(216); id();
			setState(221);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(217); match(COMMA);
					setState(218); id();
					}
					} 
				}
				setState(223);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			setState(225);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(224); match(COMMA);
					}
					break;
			}
			setState(227); match(RBRACE);
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
		enterRule(_localctx, 18, RULE_actionBlock);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(229); match(BEGIN_ACTION);
			setState(255);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					setState(253);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
						case 1:
							{
							setState(230); actionBlock();
							}
							break;

						case 2:
							{
							setState(231); actionExpression();
							}
							break;

						case 3:
							{
							setState(232); actionScopeExpression();
							}
							break;

						case 4:
							{
							setState(233); match(ACTION_WS);
							}
							break;

						case 5:
							{
							setState(234); match(ACTION_NEWLINE);
							}
							break;

						case 6:
							{
							setState(235); match(ACTION_COMMENT);
							}
							break;

						case 7:
							{
							setState(236); match(ACTION_LITERAL);
							}
							break;

						case 8:
							{
							setState(237); match(ACTION_TEXT);
							}
							break;

						case 9:
							{
							setState(238); match(ACTION_LT);
							}
							break;

						case 10:
							{
							setState(239); match(ACTION_GT);
							}
							break;

						case 11:
							{
							setState(240); match(ACTION_LPAREN);
							}
							break;

						case 12:
							{
							setState(241); match(ACTION_RPAREN);
							}
							break;

						case 13:
							{
							setState(242); match(ACTION_LBRACK);
							}
							break;

						case 14:
							{
							setState(243); match(ACTION_RBRACK);
							}
							break;

						case 15:
							{
							setState(244); match(ACTION_EQUALS);
							}
							break;

						case 16:
							{
							setState(245); match(ACTION_COMMA);
							}
							break;

						case 17:
							{
							setState(246); match(ACTION_ESCAPE);
							}
							break;

						case 18:
							{
							setState(247); match(ACTION_WORD);
							}
							break;

						case 19:
							{
							setState(248); match(ACTION_REFERENCE);
							}
							break;

						case 20:
							{
							setState(249); match(ACTION_COLON);
							}
							break;

						case 21:
							{
							setState(250); match(ACTION_COLON2);
							}
							break;

						case 22:
							{
							setState(251); match(ACTION_MINUS);
							}
							break;

						case 23:
							{
							setState(252); match(ACTION_DOT);
							}
							break;
					}
					} 
				}
				setState(257);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			setState(258); match(END_ACTION);
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
		enterRule(_localctx, 20, RULE_actionExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(260); _localctx.ref = match(ACTION_REFERENCE);
			setState(264);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(261); ignored();
					}
					} 
				}
				setState(266);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			setState(267); _localctx.op = match(ACTION_DOT);
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
			setState(274); _localctx.member = match(ACTION_WORD);
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
		enterRule(_localctx, 22, RULE_actionScopeExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(276); _localctx.ref = match(ACTION_REFERENCE);
			setState(280);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(277); ignored();
					}
					} 
				}
				setState(282);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(313);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(283); match(ACTION_LBRACK);
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
					setState(297);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
						case 1:
							{
							setState(290); _localctx.neg = match(ACTION_MINUS);
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
							}
							break;
					}
					setState(299); _localctx.index = match(ACTION_WORD);
					setState(303);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(300); ignored();
							}
							} 
						}
						setState(305);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
					}
					setState(306); match(ACTION_RBRACK);
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
					}
					break;
			}
			setState(315); _localctx.op = match(ACTION_COLON2);
			setState(319);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(316); ignored();
					}
					} 
				}
				setState(321);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			setState(322); _localctx.member = match(ACTION_WORD);
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
		enterRule(_localctx, 24, RULE_argActionBlock);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(324); match(BEGIN_ARG_ACTION);
			setState(328);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(325);
					_input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==ARG_ACTION_LT || _la==ARG_ACTION_GT || _la==ARG_ACTION_LPAREN || _la==ARG_ACTION_RPAREN || _la==ARG_ACTION_EQUALS || _la==ARG_ACTION_COMMA || _la==ARG_ACTION_ESCAPE || _la==ARG_ACTION_WORD || _la==ARG_ACTION_ELEMENT || _la==ARG_ACTION_TEXT || _la==ARG_ACTION_WS || _la==ARG_ACTION_NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					} 
				}
				setState(330);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			setState(331); match(END_ARG_ACTION);
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
		enterRule(_localctx, 26, RULE_argActionParameters);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(333); match(BEGIN_ARG_ACTION);
			setState(337);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(334); ignored();
					}
					} 
				}
				setState(339);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			setState(366);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(340); _localctx.argActionParameter = argActionParameter();
					_localctx.parameters.add(_localctx.argActionParameter);
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
					setState(363);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(347); match(ARG_ACTION_COMMA);
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
							setState(354); _localctx.argActionParameter = argActionParameter();
							_localctx.parameters.add(_localctx.argActionParameter);
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
							}
							} 
						}
						setState(365);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
					}
					}
					break;
			}
			setState(368); match(END_ARG_ACTION);
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
		enterRule(_localctx, 28, RULE_argActionParameter);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(371);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(370); _localctx.type = argActionParameterType();
					}
					break;
			}
			setState(376);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(373); ignored();
					}
					} 
				}
				setState(378);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			setState(379); _localctx.name = match(ARG_ACTION_WORD);
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
		enterRule(_localctx, 30, RULE_argActionParameterType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(381); argActionParameterTypePart();
			setState(391);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(385);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(382); ignored();
							}
							} 
						}
						setState(387);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
					}
					setState(388); argActionParameterTypePart();
					}
					} 
				}
				setState(393);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
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
		enterRule(_localctx, 32, RULE_argActionParameterTypePart);
		try {
			setState(405);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(394); match(ARG_ACTION_WORD);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(395); match(ARG_ACTION_LT);
					setState(397);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
						case 1:
							{
							setState(396); argActionParameterType();
							}
							break;
					}
					setState(399); match(ARG_ACTION_GT);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(400); match(ARG_ACTION_LPAREN);
					setState(402);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
						case 1:
							{
							setState(401); argActionParameterType();
							}
							break;
					}
					setState(404); match(ARG_ACTION_RPAREN);
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
		enterRule(_localctx, 34, RULE_ignored);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(407);
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
		enterRule(_localctx, 36, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(409); match(AT);
			setState(413);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					{
					setState(410); actionScopeName();
					setState(411); match(COLONCOLON);
					}
					break;
			}
			setState(415); id();
			setState(416); actionBlock();
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
		enterRule(_localctx, 38, RULE_actionScopeName);
		try {
			setState(421);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(418); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(419); match(LEXER);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(420); match(PARSER);
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
		enterRule(_localctx, 40, RULE_modeSpec);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(423); match(MODE);
			setState(424); id();
			setState(425); match(SEMI);
			setState(427); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(426); ruleSpec();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(429); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
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
		enterRule(_localctx, 42, RULE_rules);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(434);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(431); ruleSpec();
					}
					} 
				}
				setState(436);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
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
		enterRule(_localctx, 44, RULE_ruleSpec);
		try {
			setState(439);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(437); parserRuleSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(438); lexerRule();
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
		enterRule(_localctx, 46, RULE_parserRuleSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(441); match(DOC_COMMENT);
					}
					break;
			}
			setState(445);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(444); ruleModifiers();
					}
					break;
			}
			setState(447); _localctx.name = match(RULE_REF);
			setState(449);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(448); argActionParameters();
					}
					break;
			}
			setState(452);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(451); ruleReturns();
					}
					break;
			}
			setState(455);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					{
					setState(454); throwsSpec();
					}
					break;
			}
			setState(458);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(457); localsSpec();
					}
					break;
			}
			setState(460); rulePrequels();
			setState(461); match(COLON);
			setState(462); ruleBlock();
			setState(463); match(SEMI);
			setState(464); exceptionGroup();
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
		enterRule(_localctx, 48, RULE_exceptionGroup);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(469);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(466); exceptionHandler();
					}
					} 
				}
				setState(471);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			}
			setState(473);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
				case 1:
					{
					setState(472); finallyClause();
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
		enterRule(_localctx, 50, RULE_exceptionHandler);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475); match(CATCH);
			setState(476); argActionBlock();
			setState(477); actionBlock();
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
		enterRule(_localctx, 52, RULE_finallyClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479); match(FINALLY);
			setState(480); actionBlock();
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
		enterRule(_localctx, 54, RULE_rulePrequels);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(485);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(482); rulePrequel();
					}
					} 
				}
				setState(487);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
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
		enterRule(_localctx, 56, RULE_rulePrequel);
		try {
			setState(490);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(488); optionsSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(489); ruleAction();
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
		enterRule(_localctx, 58, RULE_ruleReturns);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(492); match(RETURNS);
			setState(493); argActionParameters();
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
		enterRule(_localctx, 60, RULE_throwsSpec);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(495); match(THROWS);
			setState(496); qid();
			setState(501);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(497); match(COMMA);
					setState(498); qid();
					}
					} 
				}
				setState(503);
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
		enterRule(_localctx, 62, RULE_localsSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(504); match(LOCALS);
			setState(505); argActionParameters();
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
		enterRule(_localctx, 64, RULE_ruleAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(507); match(AT);
			setState(508); id();
			setState(509); actionBlock();
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
		enterRule(_localctx, 66, RULE_ruleModifiers);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(512); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(511); ruleModifier();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(514); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,53,_ctx);
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
		enterRule(_localctx, 68, RULE_ruleModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(516);
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
		enterRule(_localctx, 70, RULE_ruleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(518); ruleAltList();
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
		enterRule(_localctx, 72, RULE_ruleAltList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(520); labeledAlt();
			setState(525);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(521); match(OR);
					setState(522); labeledAlt();
					}
					} 
				}
				setState(527);
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
		enterRule(_localctx, 74, RULE_labeledAlt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(528); alternative();
			setState(531);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
				case 1:
					{
					setState(529); match(POUND);
					setState(530); id();
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
		enterRule(_localctx, 76, RULE_lexerRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(534);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
				case 1:
					{
					setState(533); match(DOC_COMMENT);
					}
					break;
			}
			setState(537);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
				case 1:
					{
					setState(536); match(FRAGMENT);
					}
					break;
			}
			setState(539); _localctx.name = match(TOKEN_REF);
			setState(540); match(COLON);
			setState(541); lexerRuleBlock();
			setState(542); match(SEMI);
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
		enterRule(_localctx, 78, RULE_lexerRuleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(544); lexerAltList();
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
		enterRule(_localctx, 80, RULE_lexerAltList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(546); lexerAlt();
			setState(551);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(547); match(OR);
					setState(548); lexerAlt();
					}
					} 
				}
				setState(553);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
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

	@RuleVersion(1)
	public final LexerAltContext lexerAlt() throws RecognitionException {
		LexerAltContext _localctx = new LexerAltContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_lexerAlt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(555);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
				case 1:
					{
					setState(554); lexerElements();
					}
					break;
			}
			setState(558);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
				case 1:
					{
					setState(557); lexerCommands();
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
		enterRule(_localctx, 84, RULE_lexerElements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(561); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(560); lexerElement();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(563); 
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
		enterRule(_localctx, 86, RULE_lexerElement);
		try {
			setState(581);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(565); labeledLexerElement();
					setState(567);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
						case 1:
							{
							setState(566); ebnfSuffix();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(569); lexerAtom();
					setState(571);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
						case 1:
							{
							setState(570); ebnfSuffix();
							}
							break;
					}
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(573); lexerBlock();
					setState(575);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
						case 1:
							{
							setState(574); ebnfSuffix();
							}
							break;
					}
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(577); actionBlock();
					setState(579);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
						case 1:
							{
							setState(578); match(QUESTION);
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
		enterRule(_localctx, 88, RULE_labeledLexerElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(583); id();
			setState(586);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
				case 1:
					{
					setState(584); _localctx.ass = match(ASSIGN);
					}
					break;

				case 2:
					{
					setState(585); _localctx.ass = match(PLUS_ASSIGN);
					}
					break;
			}
			setState(590);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
				case 1:
					{
					setState(588); lexerAtom();
					}
					break;

				case 2:
					{
					setState(589); block();
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
		enterRule(_localctx, 90, RULE_lexerBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(592); match(LPAREN);
			setState(593); lexerAltList();
			setState(594); match(RPAREN);
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
		enterRule(_localctx, 92, RULE_lexerCommands);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(596); match(RARROW);
			setState(597); lexerCommand();
			setState(602);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(598); match(COMMA);
					setState(599); lexerCommand();
					}
					} 
				}
				setState(604);
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
		enterRule(_localctx, 94, RULE_lexerCommand);
		try {
			setState(611);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(605); lexerCommandName();
					setState(606); match(LPAREN);
					setState(607); lexerCommandExpr();
					setState(608); match(RPAREN);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(610); lexerCommandName();
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
		enterRule(_localctx, 96, RULE_lexerCommandName);
		try {
			setState(615);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(613); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(614); match(MODE);
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
		enterRule(_localctx, 98, RULE_lexerCommandExpr);
		try {
			setState(619);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(617); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(618); match(INT);
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
		enterRule(_localctx, 100, RULE_altList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(621); alternative();
			setState(626);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(622); match(OR);
					setState(623); alternative();
					}
					} 
				}
				setState(628);
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
		enterRule(_localctx, 102, RULE_alternative);
		try {
			setState(631);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(629); elements();
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
		enterRule(_localctx, 104, RULE_elements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(634); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
			do {
				switch ( _alt ) {
					case 1:
						{
						{
						setState(633); element();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(636); 
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
		enterRule(_localctx, 106, RULE_element);
		try {
			setState(653);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(638); labeledElement();
					setState(641);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
						case 1:
							{
							setState(639); ebnfSuffix();
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
					setState(643); atom();
					setState(646);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
						case 1:
							{
							setState(644); ebnfSuffix();
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
					setState(648); ebnf();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(649); actionBlock();
					setState(651);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
						case 1:
							{
							setState(650); match(QUESTION);
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
		enterRule(_localctx, 108, RULE_labeledElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(655); _localctx.label = id();
			setState(658);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
				case 1:
					{
					setState(656); _localctx.ass = match(ASSIGN);
					}
					break;

				case 2:
					{
					setState(657); _localctx.ass = match(PLUS_ASSIGN);
					}
					break;
			}
			setState(662);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					{
					setState(660); atom();
					}
					break;

				case 2:
					{
					setState(661); block();
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
		enterRule(_localctx, 110, RULE_ebnf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(664); block();
			setState(667);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
				case 1:
					{
					setState(665); blockSuffix();
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
		enterRule(_localctx, 112, RULE_blockSuffix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(669); ebnfSuffix();
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
		enterRule(_localctx, 114, RULE_ebnfSuffix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(671);
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
		enterRule(_localctx, 116, RULE_lexerAtom);
		try {
			setState(682);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(673); range();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(674); terminal();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(675); match(RULE_REF);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(676); notSet();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(677); match(LEXER_CHAR_SET);
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(678); match(DOT);
					setState(680);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
						case 1:
							{
							setState(679); elementOptions();
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
		enterRule(_localctx, 118, RULE_atom);
		try {
			setState(692);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(684); range();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(685); terminal();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(686); ruleref();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(687); notSet();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(688); match(DOT);
					setState(690);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
						case 1:
							{
							setState(689); elementOptions();
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
		enterRule(_localctx, 120, RULE_notSet);
		try {
			setState(698);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(694); match(NOT);
					setState(695); setElement();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(696); match(NOT);
					setState(697); blockSet();
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
		enterRule(_localctx, 122, RULE_blockSet);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(700); match(LPAREN);
			setState(701); setElement();
			setState(706);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(702); match(OR);
					setState(703); setElement();
					}
					} 
				}
				setState(708);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			}
			setState(709); match(RPAREN);
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
		enterRule(_localctx, 124, RULE_setElement);
		try {
			setState(715);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(711); match(TOKEN_REF);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(712); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(713); range();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(714); match(LEXER_CHAR_SET);
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
		enterRule(_localctx, 126, RULE_block);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(717); match(LPAREN);
			setState(728);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
				case 1:
					{
					setState(719);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
						case 1:
							{
							setState(718); optionsSpec();
							}
							break;
					}
					setState(724);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(721); ruleAction();
							}
							} 
						}
						setState(726);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
					}
					setState(727); match(COLON);
					}
					break;
			}
			setState(730); altList();
			setState(731); match(RPAREN);
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
		enterRule(_localctx, 128, RULE_ruleref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(733); match(RULE_REF);
			setState(735);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
				case 1:
					{
					setState(734); argActionBlock();
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
		enterRule(_localctx, 130, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(737); match(STRING_LITERAL);
			setState(738); match(RANGE);
			setState(739); match(STRING_LITERAL);
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
		enterRule(_localctx, 132, RULE_terminal);
		try {
			setState(749);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(741); match(TOKEN_REF);
					setState(743);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
						case 1:
							{
							setState(742); elementOptions();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(745); match(STRING_LITERAL);
					setState(747);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
						case 1:
							{
							setState(746); elementOptions();
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
		enterRule(_localctx, 134, RULE_elementOptions);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(751); match(LT);
			setState(752); elementOption();
			setState(757);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,97,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(753); match(COMMA);
					setState(754); elementOption();
					}
					} 
				}
				setState(759);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,97,_ctx);
			}
			setState(760); match(GT);
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
		enterRule(_localctx, 136, RULE_elementOption);
		try {
			setState(769);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(762); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(763); id();
					setState(764); match(ASSIGN);
					setState(767);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
						case 1:
							{
							setState(765); qid();
							}
							break;

						case 2:
							{
							setState(766); match(STRING_LITERAL);
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
		enterRule(_localctx, 138, RULE_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(771);
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
		enterRule(_localctx, 140, RULE_qid);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(773); id();
			setState(778);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,100,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(774); match(DOT);
					setState(775); id();
					}
					} 
				}
				setState(780);
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
		"\1r\u030e\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7"+
		"\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16\7\16\2\17"+
		"\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25\7\25\2\26"+
		"\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34\7\34\2\35"+
		"\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7%\2&\7&"+
		"\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2.\7.\2/\7/\2\60\7\60\2\61"+
		"\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65\2\66\7\66\2\67\7\67\28\7"+
		"8\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2@\7@\2A\7A\2B\7B\2C\7C\2"+
		"D\7D\2E\7E\2F\7F\1\0\3\0\u0090\b\0\1\0\1\0\1\0\1\0\5\0\u0096\b\0\n\0\f"+
		"\0\u0099\t\0\1\0\1\0\5\0\u009d\b\0\n\0\f\0\u00a0\t\0\1\0\1\0\1\1\1\1\1"+
		"\1\1\1\1\1\3\1\u00a9\b\1\1\2\1\2\1\2\1\2\3\2\u00af\b\2\1\3\1\3\1\3\1\3"+
		"\5\3\u00b5\b\3\n\3\f\3\u00b8\t\3\1\3\1\3\1\4\1\4\1\4\1\4\1\5\1\5\1\5\1"+
		"\5\3\5\u00c4\b\5\1\6\1\6\1\6\1\6\5\6\u00ca\b\6\n\6\f\6\u00cd\t\6\1\6\1"+
		"\6\1\7\1\7\1\7\1\7\1\7\3\7\u00d6\b\7\1\b\1\b\1\b\1\b\5\b\u00dc\b\b\n\b"+
		"\f\b\u00df\t\b\1\b\3\b\u00e2\b\b\1\b\1\b\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1"+
		"\t\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\t\1\t\5\t"+
		"\u00fe\b\t\n\t\f\t\u0101\t\t\1\t\1\t\1\n\1\n\5\n\u0107\b\n\n\n\f\n\u010a"+
		"\t\n\1\n\1\n\5\n\u010e\b\n\n\n\f\n\u0111\t\n\1\n\1\n\1\13\1\13\5\13\u0117"+
		"\b\13\n\13\f\13\u011a\t\13\1\13\1\13\5\13\u011e\b\13\n\13\f\13\u0121\t"+
		"\13\1\13\1\13\5\13\u0125\b\13\n\13\f\13\u0128\t\13\3\13\u012a\b\13\1\13"+
		"\1\13\5\13\u012e\b\13\n\13\f\13\u0131\t\13\1\13\1\13\5\13\u0135\b\13\n"+
		"\13\f\13\u0138\t\13\3\13\u013a\b\13\1\13\1\13\5\13\u013e\b\13\n\13\f\13"+
		"\u0141\t\13\1\13\1\13\1\f\1\f\5\f\u0147\b\f\n\f\f\f\u014a\t\f\1\f\1\f"+
		"\1\r\1\r\5\r\u0150\b\r\n\r\f\r\u0153\t\r\1\r\1\r\5\r\u0157\b\r\n\r\f\r"+
		"\u015a\t\r\1\r\1\r\5\r\u015e\b\r\n\r\f\r\u0161\t\r\1\r\1\r\5\r\u0165\b"+
		"\r\n\r\f\r\u0168\t\r\5\r\u016a\b\r\n\r\f\r\u016d\t\r\3\r\u016f\b\r\1\r"+
		"\1\r\1\16\3\16\u0174\b\16\1\16\5\16\u0177\b\16\n\16\f\16\u017a\t\16\1"+
		"\16\1\16\1\17\1\17\5\17\u0180\b\17\n\17\f\17\u0183\t\17\1\17\5\17\u0186"+
		"\b\17\n\17\f\17\u0189\t\17\1\20\1\20\1\20\3\20\u018e\b\20\1\20\1\20\1"+
		"\20\3\20\u0193\b\20\1\20\3\20\u0196\b\20\1\21\1\21\1\22\1\22\1\22\1\22"+
		"\3\22\u019e\b\22\1\22\1\22\1\22\1\23\1\23\1\23\3\23\u01a6\b\23\1\24\1"+
		"\24\1\24\1\24\4\24\u01ac\b\24\13\24\f\24\u01ad\1\25\5\25\u01b1\b\25\n"+
		"\25\f\25\u01b4\t\25\1\26\1\26\3\26\u01b8\b\26\1\27\3\27\u01bb\b\27\1\27"+
		"\3\27\u01be\b\27\1\27\1\27\3\27\u01c2\b\27\1\27\3\27\u01c5\b\27\1\27\3"+
		"\27\u01c8\b\27\1\27\3\27\u01cb\b\27\1\27\1\27\1\27\1\27\1\27\1\27\1\30"+
		"\5\30\u01d4\b\30\n\30\f\30\u01d7\t\30\1\30\3\30\u01da\b\30\1\31\1\31\1"+
		"\31\1\31\1\32\1\32\1\32\1\33\5\33\u01e4\b\33\n\33\f\33\u01e7\t\33\1\34"+
		"\1\34\3\34\u01eb\b\34\1\35\1\35\1\35\1\36\1\36\1\36\1\36\5\36\u01f4\b"+
		"\36\n\36\f\36\u01f7\t\36\1\37\1\37\1\37\1 \1 \1 \1 \1!\4!\u0201\b!\13"+
		"!\f!\u0202\1\"\1\"\1#\1#\1$\1$\1$\5$\u020c\b$\n$\f$\u020f\t$\1%\1%\1%"+
		"\3%\u0214\b%\1&\3&\u0217\b&\1&\3&\u021a\b&\1&\1&\1&\1&\1&\1\'\1\'\1(\1"+
		"(\1(\5(\u0226\b(\n(\f(\u0229\t(\1)\3)\u022c\b)\1)\3)\u022f\b)\1*\4*\u0232"+
		"\b*\13*\f*\u0233\1+\1+\3+\u0238\b+\1+\1+\3+\u023c\b+\1+\1+\3+\u0240\b"+
		"+\1+\1+\3+\u0244\b+\3+\u0246\b+\1,\1,\1,\3,\u024b\b,\1,\1,\3,\u024f\b"+
		",\1-\1-\1-\1-\1.\1.\1.\1.\5.\u0259\b.\n.\f.\u025c\t.\1/\1/\1/\1/\1/\1"+
		"/\3/\u0264\b/\1\60\1\60\3\60\u0268\b\60\1\61\1\61\3\61\u026c\b\61\1\62"+
		"\1\62\1\62\5\62\u0271\b\62\n\62\f\62\u0274\t\62\1\63\1\63\3\63\u0278\b"+
		"\63\1\64\4\64\u027b\b\64\13\64\f\64\u027c\1\65\1\65\1\65\3\65\u0282\b"+
		"\65\1\65\1\65\1\65\3\65\u0287\b\65\1\65\1\65\1\65\3\65\u028c\b\65\3\65"+
		"\u028e\b\65\1\66\1\66\1\66\3\66\u0293\b\66\1\66\1\66\3\66\u0297\b\66\1"+
		"\67\1\67\1\67\3\67\u029c\b\67\18\18\19\19\1:\1:\1:\1:\1:\1:\1:\3:\u02a9"+
		"\b:\3:\u02ab\b:\1;\1;\1;\1;\1;\1;\3;\u02b3\b;\3;\u02b5\b;\1<\1<\1<\1<"+
		"\3<\u02bb\b<\1=\1=\1=\1=\5=\u02c1\b=\n=\f=\u02c4\t=\1=\1=\1>\1>\1>\1>"+
		"\3>\u02cc\b>\1?\1?\3?\u02d0\b?\1?\5?\u02d3\b?\n?\f?\u02d6\t?\1?\3?\u02d9"+
		"\b?\1?\1?\1?\1@\1@\3@\u02e0\b@\1A\1A\1A\1A\1B\1B\3B\u02e8\bB\1B\1B\3B"+
		"\u02ec\bB\3B\u02ee\bB\1C\1C\1C\1C\5C\u02f4\bC\nC\fC\u02f7\tC\1C\1C\1D"+
		"\1D\1D\1D\1D\3D\u0300\bD\3D\u0302\bD\1E\1E\1F\1F\1F\5F\u0309\bF\nF\fF"+
		"\u030c\tF\1FG\0\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088"+
		"\u008a\u008c\0\5\1\67B\3ABSSVW\2\16\16\22\24\1&(\2\1\2\32\32\u0354\0\u008f"+
		"\1\0\0\0\2\u00a8\1\0\0\0\4\u00ae\1\0\0\0\6\u00b0\1\0\0\0\b\u00bb\1\0\0"+
		"\0\n\u00c3\1\0\0\0\f\u00c5\1\0\0\0\16\u00d5\1\0\0\0\20\u00d7\1\0\0\0\22"+
		"\u00e5\1\0\0\0\24\u0104\1\0\0\0\26\u0114\1\0\0\0\30\u0144\1\0\0\0\32\u014d"+
		"\1\0\0\0\34\u0173\1\0\0\0\36\u017d\1\0\0\0 \u0195\1\0\0\0\"\u0197\1\0"+
		"\0\0$\u0199\1\0\0\0&\u01a5\1\0\0\0(\u01a7\1\0\0\0*\u01b2\1\0\0\0,\u01b7"+
		"\1\0\0\0.\u01ba\1\0\0\0\60\u01d5\1\0\0\0\62\u01db\1\0\0\0\64\u01df\1\0"+
		"\0\0\66\u01e5\1\0\0\08\u01ea\1\0\0\0:\u01ec\1\0\0\0<\u01ef\1\0\0\0>\u01f8"+
		"\1\0\0\0@\u01fb\1\0\0\0B\u0200\1\0\0\0D\u0204\1\0\0\0F\u0206\1\0\0\0H"+
		"\u0208\1\0\0\0J\u0210\1\0\0\0L\u0216\1\0\0\0N\u0220\1\0\0\0P\u0222\1\0"+
		"\0\0R\u022b\1\0\0\0T\u0231\1\0\0\0V\u0245\1\0\0\0X\u0247\1\0\0\0Z\u0250"+
		"\1\0\0\0\\\u0254\1\0\0\0^\u0263\1\0\0\0`\u0267\1\0\0\0b\u026b\1\0\0\0"+
		"d\u026d\1\0\0\0f\u0277\1\0\0\0h\u027a\1\0\0\0j\u028d\1\0\0\0l\u028f\1"+
		"\0\0\0n\u0298\1\0\0\0p\u029d\1\0\0\0r\u029f\1\0\0\0t\u02aa\1\0\0\0v\u02b4"+
		"\1\0\0\0x\u02ba\1\0\0\0z\u02bc\1\0\0\0|\u02cb\1\0\0\0~\u02cd\1\0\0\0\u0080"+
		"\u02dd\1\0\0\0\u0082\u02e1\1\0\0\0\u0084\u02ed\1\0\0\0\u0086\u02ef\1\0"+
		"\0\0\u0088\u0301\1\0\0\0\u008a\u0303\1\0\0\0\u008c\u0305\1\0\0\0\u008e"+
		"\u0090\5\4\0\0\u008f\u008e\1\0\0\0\u008f\u0090\1\0\0\0\u0090\u0091\1\0"+
		"\0\0\u0091\u0092\3\2\1\0\u0092\u0093\3\u008aE\0\u0093\u0097\5\37\0\0\u0094"+
		"\u0096\3\4\2\0\u0095\u0094\1\0\0\0\u0096\u0099\1\0\0\0\u0097\u0095\1\0"+
		"\0\0\u0097\u0098\1\0\0\0\u0098\u009a\1\0\0\0\u0099\u0097\1\0\0\0\u009a"+
		"\u009e\3*\25\0\u009b\u009d\3(\24\0\u009c\u009b\1\0\0\0\u009d\u00a0\1\0"+
		"\0\0\u009e\u009c\1\0\0\0\u009e\u009f\1\0\0\0\u009f\u00a1\1\0\0\0\u00a0"+
		"\u009e\1\0\0\0\u00a1\u00a2\5\uffff\0\0\u00a2\1\1\0\0\0\u00a3\u00a4\5\17"+
		"\0\0\u00a4\u00a9\5\21\0\0\u00a5\u00a6\5\20\0\0\u00a6\u00a9\5\21\0\0\u00a7"+
		"\u00a9\5\21\0\0\u00a8\u00a3\1\0\0\0\u00a8\u00a5\1\0\0\0\u00a8\u00a7\1"+
		"\0\0\0\u00a9\3\1\0\0\0\u00aa\u00af\3\6\3\0\u00ab\u00af\3\f\6\0\u00ac\u00af"+
		"\3\20\b\0\u00ad\u00af\3$\22\0\u00ae\u00aa\1\0\0\0\u00ae\u00ab\1\0\0\0"+
		"\u00ae\u00ac\1\0\0\0\u00ae\u00ad\1\0\0\0\u00af\5\1\0\0\0\u00b0\u00b6\5"+
		"\13\0\0\u00b1\u00b2\3\b\4\0\u00b2\u00b3\5\37\0\0\u00b3\u00b5\1\0\0\0\u00b4"+
		"\u00b1\1\0\0\0\u00b5\u00b8\1\0\0\0\u00b6\u00b4\1\0\0\0\u00b6\u00b7\1\0"+
		"\0\0\u00b7\u00b9\1\0\0\0\u00b8\u00b6\1\0\0\0\u00b9\u00ba\5\61\0\0\u00ba"+
		"\7\1\0\0\0\u00bb\u00bc\3\u008aE\0\u00bc\u00bd\5%\0\0\u00bd\u00be\3\n\5"+
		"\0\u00be\t\1\0\0\0\u00bf\u00c4\3\u008cF\0\u00c0\u00c4\5\64\0\0\u00c1\u00c4"+
		"\5\63\0\0\u00c2\u00c4\5\'\0\0\u00c3\u00bf\1\0\0\0\u00c3\u00c0\1\0\0\0"+
		"\u00c3\u00c1\1\0\0\0\u00c3\u00c2\1\0\0\0\u00c4\13\1\0\0\0\u00c5\u00c6"+
		"\5\r\0\0\u00c6\u00cb\3\16\7\0\u00c7\u00c8\5\36\0\0\u00c8\u00ca\3\16\7"+
		"\0\u00c9\u00c7\1\0\0\0\u00ca\u00cd\1\0\0\0\u00cb\u00c9\1\0\0\0\u00cb\u00cc"+
		"\1\0\0\0\u00cc\u00ce\1\0\0\0\u00cd\u00cb\1\0\0\0\u00ce\u00cf\5\37\0\0"+
		"\u00cf\r\1\0\0\0\u00d0\u00d1\3\u008aE\0\u00d1\u00d2\5%\0\0\u00d2\u00d3"+
		"\3\u008aE\0\u00d3\u00d6\1\0\0\0\u00d4\u00d6\3\u008aE\0\u00d5\u00d0\1\0"+
		"\0\0\u00d5\u00d4\1\0\0\0\u00d6\17\1\0\0\0\u00d7\u00d8\5\f\0\0\u00d8\u00dd"+
		"\3\u008aE\0\u00d9\u00da\5\36\0\0\u00da\u00dc\3\u008aE\0\u00db\u00d9\1"+
		"\0\0\0\u00dc\u00df\1\0\0\0\u00dd\u00db\1\0\0\0\u00dd\u00de\1\0\0\0\u00de"+
		"\u00e1\1\0\0\0\u00df\u00dd\1\0\0\0\u00e0\u00e2\5\36\0\0\u00e1\u00e0\1"+
		"\0\0\0\u00e1\u00e2\1\0\0\0\u00e2\u00e3\1\0\0\0\u00e3\u00e4\5\61\0\0\u00e4"+
		"\21\1\0\0\0\u00e5\u00ff\5\n\0\0\u00e6\u00fe\3\22\t\0\u00e7\u00fe\3\24"+
		"\n\0\u00e8\u00fe\3\26\13\0\u00e9\u00fe\5V\0\0\u00ea\u00fe\5W\0\0\u00eb"+
		"\u00fe\5S\0\0\u00ec\u00fe\5T\0\0\u00ed\u00fe\5U\0\0\u00ee\u00fe\5E\0\0"+
		"\u00ef\u00fe\5F\0\0\u00f0\u00fe\5G\0\0\u00f1\u00fe\5H\0\0\u00f2\u00fe"+
		"\5I\0\0\u00f3\u00fe\5J\0\0\u00f4\u00fe\5K\0\0\u00f5\u00fe\5L\0\0\u00f6"+
		"\u00fe\5P\0\0\u00f7\u00fe\5Q\0\0\u00f8\u00fe\5R\0\0\u00f9\u00fe\5N\0\0"+
		"\u00fa\u00fe\5M\0\0\u00fb\u00fe\5O\0\0\u00fc\u00fe\5D\0\0\u00fd\u00e6"+
		"\1\0\0\0\u00fd\u00e7\1\0\0\0\u00fd\u00e8\1\0\0\0\u00fd\u00e9\1\0\0\0\u00fd"+
		"\u00ea\1\0\0\0\u00fd\u00eb\1\0\0\0\u00fd\u00ec\1\0\0\0\u00fd\u00ed\1\0"+
		"\0\0\u00fd\u00ee\1\0\0\0\u00fd\u00ef\1\0\0\0\u00fd\u00f0\1\0\0\0\u00fd"+
		"\u00f1\1\0\0\0\u00fd\u00f2\1\0\0\0\u00fd\u00f3\1\0\0\0\u00fd\u00f4\1\0"+
		"\0\0\u00fd\u00f5\1\0\0\0\u00fd\u00f6\1\0\0\0\u00fd\u00f7\1\0\0\0\u00fd"+
		"\u00f8\1\0\0\0\u00fd\u00f9\1\0\0\0\u00fd\u00fa\1\0\0\0\u00fd\u00fb\1\0"+
		"\0\0\u00fd\u00fc\1\0\0\0\u00fe\u0101\1\0\0\0\u00ff\u00fd\1\0\0\0\u00ff"+
		"\u0100\1\0\0\0\u0100\u0102\1\0\0\0\u0101\u00ff\1\0\0\0\u0102\u0103\5X"+
		"\0\0\u0103\23\1\0\0\0\u0104\u0108\5R\0\0\u0105\u0107\3\"\21\0\u0106\u0105"+
		"\1\0\0\0\u0107\u010a\1\0\0\0\u0108\u0106\1\0\0\0\u0108\u0109\1\0\0\0\u0109"+
		"\u010b\1\0\0\0\u010a\u0108\1\0\0\0\u010b\u010f\5D\0\0\u010c\u010e\3\""+
		"\21\0\u010d\u010c\1\0\0\0\u010e\u0111\1\0\0\0\u010f\u010d\1\0\0\0\u010f"+
		"\u0110\1\0\0\0\u0110\u0112\1\0\0\0\u0111\u010f\1\0\0\0\u0112\u0113\5Q"+
		"\0\0\u0113\25\1\0\0\0\u0114\u0118\5R\0\0\u0115\u0117\3\"\21\0\u0116\u0115"+
		"\1\0\0\0\u0117\u011a\1\0\0\0\u0118\u0116\1\0\0\0\u0118\u0119\1\0\0\0\u0119"+
		"\u0139\1\0\0\0\u011a\u0118\1\0\0\0\u011b\u011f\5I\0\0\u011c\u011e\3\""+
		"\21\0\u011d\u011c\1\0\0\0\u011e\u0121\1\0\0\0\u011f\u011d\1\0\0\0\u011f"+
		"\u0120\1\0\0\0\u0120\u0129\1\0\0\0\u0121\u011f\1\0\0\0\u0122\u0126\5O"+
		"\0\0\u0123\u0125\3\"\21\0\u0124\u0123\1\0\0\0\u0125\u0128\1\0\0\0\u0126"+
		"\u0124\1\0\0\0\u0126\u0127\1\0\0\0\u0127\u012a\1\0\0\0\u0128\u0126\1\0"+
		"\0\0\u0129\u0122\1\0\0\0\u0129\u012a\1\0\0\0\u012a\u012b\1\0\0\0\u012b"+
		"\u012f\5Q\0\0\u012c\u012e\3\"\21\0\u012d\u012c\1\0\0\0\u012e\u0131\1\0"+
		"\0\0\u012f\u012d\1\0\0\0\u012f\u0130\1\0\0\0\u0130\u0132\1\0\0\0\u0131"+
		"\u012f\1\0\0\0\u0132\u0136\5J\0\0\u0133\u0135\3\"\21\0\u0134\u0133\1\0"+
		"\0\0\u0135\u0138\1\0\0\0\u0136\u0134\1\0\0\0\u0136\u0137\1\0\0\0\u0137"+
		"\u013a\1\0\0\0\u0138\u0136\1\0\0\0\u0139\u011b\1\0\0\0\u0139\u013a\1\0"+
		"\0\0\u013a\u013b\1\0\0\0\u013b\u013f\5M\0\0\u013c\u013e\3\"\21\0\u013d"+
		"\u013c\1\0\0\0\u013e\u0141\1\0\0\0\u013f\u013d\1\0\0\0\u013f\u0140\1\0"+
		"\0\0\u0140\u0142\1\0\0\0\u0141\u013f\1\0\0\0\u0142\u0143\5Q\0\0\u0143"+
		"\27\1\0\0\0\u0144\u0148\5\t\0\0\u0145\u0147\7\0\0\0\u0146\u0145\1\0\0"+
		"\0\u0147\u014a\1\0\0\0\u0148\u0146\1\0\0\0\u0148\u0149\1\0\0\0\u0149\u014b"+
		"\1\0\0\0\u014a\u0148\1\0\0\0\u014b\u014c\5C\0\0\u014c\31\1\0\0\0\u014d"+
		"\u0151\5\t\0\0\u014e\u0150\3\"\21\0\u014f\u014e\1\0\0\0\u0150\u0153\1"+
		"\0\0\0\u0151\u014f\1\0\0\0\u0151\u0152\1\0\0\0\u0152\u016e\1\0\0\0\u0153"+
		"\u0151\1\0\0\0\u0154\u0158\3\34\16\0\u0155\u0157\3\"\21\0\u0156\u0155"+
		"\1\0\0\0\u0157\u015a\1\0\0\0\u0158\u0156\1\0\0\0\u0158\u0159\1\0\0\0\u0159"+
		"\u016b\1\0\0\0\u015a\u0158\1\0\0\0\u015b\u015f\5<\0\0\u015c\u015e\3\""+
		"\21\0\u015d\u015c\1\0\0\0\u015e\u0161\1\0\0\0\u015f\u015d\1\0\0\0\u015f"+
		"\u0160\1\0\0\0\u0160\u0162\1\0\0\0\u0161\u015f\1\0\0\0\u0162\u0166\3\34"+
		"\16\0\u0163\u0165\3\"\21\0\u0164\u0163\1\0\0\0\u0165\u0168\1\0\0\0\u0166"+
		"\u0164\1\0\0\0\u0166\u0167\1\0\0\0\u0167\u016a\1\0\0\0\u0168\u0166\1\0"+
		"\0\0\u0169\u015b\1\0\0\0\u016a\u016d\1\0\0\0\u016b\u0169\1\0\0\0\u016b"+
		"\u016c\1\0\0\0\u016c\u016f\1\0\0\0\u016d\u016b\1\0\0\0\u016e\u0154\1\0"+
		"\0\0\u016e\u016f\1\0\0\0\u016f\u0170\1\0\0\0\u0170\u0171\5C\0\0\u0171"+
		"\33\1\0\0\0\u0172\u0174\3\36\17\0\u0173\u0172\1\0\0\0\u0173\u0174\1\0"+
		"\0\0\u0174\u0178\1\0\0\0\u0175\u0177\3\"\21\0\u0176\u0175\1\0\0\0\u0177"+
		"\u017a\1\0\0\0\u0178\u0176\1\0\0\0\u0178\u0179\1\0\0\0\u0179\u017b\1\0"+
		"\0\0\u017a\u0178\1\0\0\0\u017b\u017c\5>\0\0\u017c\35\1\0\0\0\u017d\u0187"+
		"\3 \20\0\u017e\u0180\3\"\21\0\u017f\u017e\1\0\0\0\u0180\u0183\1\0\0\0"+
		"\u0181\u017f\1\0\0\0\u0181\u0182\1\0\0\0\u0182\u0184\1\0\0\0\u0183\u0181"+
		"\1\0\0\0\u0184\u0186\3 \20\0\u0185\u0181\1\0\0\0\u0186\u0189\1\0\0\0\u0187"+
		"\u0185\1\0\0\0\u0187\u0188\1\0\0\0\u0188\37\1\0\0\0\u0189\u0187\1\0\0"+
		"\0\u018a\u0196\5>\0\0\u018b\u018d\5\67\0\0\u018c\u018e\3\36\17\0\u018d"+
		"\u018c\1\0\0\0\u018d\u018e\1\0\0\0\u018e\u018f\1\0\0\0\u018f\u0196\58"+
		"\0\0\u0190\u0192\59\0\0\u0191\u0193\3\36\17\0\u0192\u0191\1\0\0\0\u0192"+
		"\u0193\1\0\0\0\u0193\u0194\1\0\0\0\u0194\u0196\5:\0\0\u0195\u018a\1\0"+
		"\0\0\u0195\u018b\1\0\0\0\u0195\u0190\1\0\0\0\u0196!\1\0\0\0\u0197\u0198"+
		"\7\1\0\0\u0198#\1\0\0\0\u0199\u019d\5.\0\0\u019a\u019b\3&\23\0\u019b\u019c"+
		"\5\35\0\0\u019c\u019e\1\0\0\0\u019d\u019a\1\0\0\0\u019d\u019e\1\0\0\0"+
		"\u019e\u019f\1\0\0\0\u019f\u01a0\3\u008aE\0\u01a0\u01a1\3\22\t\0\u01a1"+
		"%\1\0\0\0\u01a2\u01a6\3\u008aE\0\u01a3\u01a6\5\17\0\0\u01a4\u01a6\5\20"+
		"\0\0\u01a5\u01a2\1\0\0\0\u01a5\u01a3\1\0\0\0\u01a5\u01a4\1\0\0\0\u01a6"+
		"\'\1\0\0\0\u01a7\u01a8\5\33\0\0\u01a8\u01a9\3\u008aE\0\u01a9\u01ab\5\37"+
		"\0\0\u01aa\u01ac\3,\26\0\u01ab\u01aa\1\0\0\0\u01ac\u01ad\1\0\0\0\u01ad"+
		"\u01ab\1\0\0\0\u01ad\u01ae\1\0\0\0\u01ae)\1\0\0\0\u01af\u01b1\3,\26\0"+
		"\u01b0\u01af\1\0\0\0\u01b1\u01b4\1\0\0\0\u01b2\u01b0\1\0\0\0\u01b2\u01b3"+
		"\1\0\0\0\u01b3+\1\0\0\0\u01b4\u01b2\1\0\0\0\u01b5\u01b8\3.\27\0\u01b6"+
		"\u01b8\3L&\0\u01b7\u01b5\1\0\0\0\u01b7\u01b6\1\0\0\0\u01b8-\1\0\0\0\u01b9"+
		"\u01bb\5\4\0\0\u01ba\u01b9\1\0\0\0\u01ba\u01bb\1\0\0\0\u01bb\u01bd\1\0"+
		"\0\0\u01bc\u01be\3B!\0\u01bd\u01bc\1\0\0\0\u01bd\u01be\1\0\0\0\u01be\u01bf"+
		"\1\0\0\0\u01bf\u01c1\5\2\0\0\u01c0\u01c2\3\32\r\0\u01c1\u01c0\1\0\0\0"+
		"\u01c1\u01c2\1\0\0\0\u01c2\u01c4\1\0\0\0\u01c3\u01c5\3:\35\0\u01c4\u01c3"+
		"\1\0\0\0\u01c4\u01c5\1\0\0\0\u01c5\u01c7\1\0\0\0\u01c6\u01c8\3<\36\0\u01c7"+
		"\u01c6\1\0\0\0\u01c7\u01c8\1\0\0\0\u01c8\u01ca\1\0\0\0\u01c9\u01cb\3>"+
		"\37\0\u01ca\u01c9\1\0\0\0\u01ca\u01cb\1\0\0\0\u01cb\u01cc\1\0\0\0\u01cc"+
		"\u01cd\3\66\33\0\u01cd\u01ce\5\34\0\0\u01ce\u01cf\3F#\0\u01cf\u01d0\5"+
		"\37\0\0\u01d0\u01d1\3\60\30\0\u01d1/\1\0\0\0\u01d2\u01d4\3\62\31\0\u01d3"+
		"\u01d2\1\0\0\0\u01d4\u01d7\1\0\0\0\u01d5\u01d3\1\0\0\0\u01d5\u01d6\1\0"+
		"\0\0\u01d6\u01d9\1\0\0\0\u01d7\u01d5\1\0\0\0\u01d8\u01da\3\64\32\0\u01d9"+
		"\u01d8\1\0\0\0\u01d9\u01da\1\0\0\0\u01da\61\1\0\0\0\u01db\u01dc\5\30\0"+
		"\0\u01dc\u01dd\3\30\f\0\u01dd\u01de\3\22\t\0\u01de\63\1\0\0\0\u01df\u01e0"+
		"\5\31\0\0\u01e0\u01e1\3\22\t\0\u01e1\65\1\0\0\0\u01e2\u01e4\38\34\0\u01e3"+
		"\u01e2\1\0\0\0\u01e4\u01e7\1\0\0\0\u01e5\u01e3\1\0\0\0\u01e5\u01e6\1\0"+
		"\0\0\u01e6\67\1\0\0\0\u01e7\u01e5\1\0\0\0\u01e8\u01eb\3\6\3\0\u01e9\u01eb"+
		"\3@ \0\u01ea\u01e8\1\0\0\0\u01ea\u01e9\1\0\0\0\u01eb9\1\0\0\0\u01ec\u01ed"+
		"\5\25\0\0\u01ed\u01ee\3\32\r\0\u01ee;\1\0\0\0\u01ef\u01f0\5\27\0\0\u01f0"+
		"\u01f5\3\u008cF\0\u01f1\u01f2\5\36\0\0\u01f2\u01f4\3\u008cF\0\u01f3\u01f1"+
		"\1\0\0\0\u01f4\u01f7\1\0\0\0\u01f5\u01f3\1\0\0\0\u01f5\u01f6\1\0\0\0\u01f6"+
		"=\1\0\0\0\u01f7\u01f5\1\0\0\0\u01f8\u01f9\5\26\0\0\u01f9\u01fa\3\32\r"+
		"\0\u01fa?\1\0\0\0\u01fb\u01fc\5.\0\0\u01fc\u01fd\3\u008aE\0\u01fd\u01fe"+
		"\3\22\t\0\u01feA\1\0\0\0\u01ff\u0201\3D\"\0\u0200\u01ff\1\0\0\0\u0201"+
		"\u0202\1\0\0\0\u0202\u0200\1\0\0\0\u0202\u0203\1\0\0\0\u0203C\1\0\0\0"+
		"\u0204\u0205\7\2\0\0\u0205E\1\0\0\0\u0206\u0207\3H$\0\u0207G\1\0\0\0\u0208"+
		"\u020d\3J%\0\u0209\u020a\5*\0\0\u020a\u020c\3J%\0\u020b\u0209\1\0\0\0"+
		"\u020c\u020f\1\0\0\0\u020d\u020b\1\0\0\0\u020d\u020e\1\0\0\0\u020eI\1"+
		"\0\0\0\u020f\u020d\1\0\0\0\u0210\u0213\3f\63\0\u0211\u0212\5/\0\0\u0212"+
		"\u0214\3\u008aE\0\u0213\u0211\1\0\0\0\u0213\u0214\1\0\0\0\u0214K\1\0\0"+
		"\0\u0215\u0217\5\4\0\0\u0216\u0215\1\0\0\0\u0216\u0217\1\0\0\0\u0217\u0219"+
		"\1\0\0\0\u0218\u021a\5\16\0\0\u0219\u0218\1\0\0\0\u0219\u021a\1\0\0\0"+
		"\u021a\u021b\1\0\0\0\u021b\u021c\5\1\0\0\u021c\u021d\5\34\0\0\u021d\u021e"+
		"\3N\'\0\u021e\u021f\5\37\0\0\u021fM\1\0\0\0\u0220\u0221\3P(\0\u0221O\1"+
		"\0\0\0\u0222\u0227\3R)\0\u0223\u0224\5*\0\0\u0224\u0226\3R)\0\u0225\u0223"+
		"\1\0\0\0\u0226\u0229\1\0\0\0\u0227\u0225\1\0\0\0\u0227\u0228\1\0\0\0\u0228"+
		"Q\1\0\0\0\u0229\u0227\1\0\0\0\u022a\u022c\3T*\0\u022b\u022a\1\0\0\0\u022b"+
		"\u022c\1\0\0\0\u022c\u022e\1\0\0\0\u022d\u022f\3\\.\0\u022e\u022d\1\0"+
		"\0\0\u022e\u022f\1\0\0\0\u022fS\1\0\0\0\u0230\u0232\3V+\0\u0231\u0230"+
		"\1\0\0\0\u0232\u0233\1\0\0\0\u0233\u0231\1\0\0\0\u0233\u0234\1\0\0\0\u0234"+
		"U\1\0\0\0\u0235\u0237\3X,\0\u0236\u0238\3r9\0\u0237\u0236\1\0\0\0\u0237"+
		"\u0238\1\0\0\0\u0238\u0246\1\0\0\0\u0239\u023b\3t:\0\u023a\u023c\3r9\0"+
		"\u023b\u023a\1\0\0\0\u023b\u023c\1\0\0\0\u023c\u0246\1\0\0\0\u023d\u023f"+
		"\3Z-\0\u023e\u0240\3r9\0\u023f\u023e\1\0\0\0\u023f\u0240\1\0\0\0\u0240"+
		"\u0246\1\0\0\0\u0241\u0243\3\22\t\0\u0242\u0244\5&\0\0\u0243\u0242\1\0"+
		"\0\0\u0243\u0244\1\0\0\0\u0244\u0246\1\0\0\0\u0245\u0235\1\0\0\0\u0245"+
		"\u0239\1\0\0\0\u0245\u023d\1\0\0\0\u0245\u0241\1\0\0\0\u0246W\1\0\0\0"+
		"\u0247\u024a\3\u008aE\0\u0248\u024b\5%\0\0\u0249\u024b\5)\0\0\u024a\u0248"+
		"\1\0\0\0\u024a\u0249\1\0\0\0\u024b\u024e\1\0\0\0\u024c\u024f\3t:\0\u024d"+
		"\u024f\3~?\0\u024e\u024c\1\0\0\0\u024e\u024d\1\0\0\0\u024fY\1\0\0\0\u0250"+
		"\u0251\5 \0\0\u0251\u0252\3P(\0\u0252\u0253\5!\0\0\u0253[\1\0\0\0\u0254"+
		"\u0255\5\"\0\0\u0255\u025a\3^/\0\u0256\u0257\5\36\0\0\u0257\u0259\3^/"+
		"\0\u0258\u0256\1\0\0\0\u0259\u025c\1\0\0\0\u025a\u0258\1\0\0\0\u025a\u025b"+
		"\1\0\0\0\u025b]\1\0\0\0\u025c\u025a\1\0\0\0\u025d\u025e\3`\60\0\u025e"+
		"\u025f\5 \0\0\u025f\u0260\3b\61\0\u0260\u0261\5!\0\0\u0261\u0264\1\0\0"+
		"\0\u0262\u0264\3`\60\0\u0263\u025d\1\0\0\0\u0263\u0262\1\0\0\0\u0264_"+
		"\1\0\0\0\u0265\u0268\3\u008aE\0\u0266\u0268\5\33\0\0\u0267\u0265\1\0\0"+
		"\0\u0267\u0266\1\0\0\0\u0268a\1\0\0\0\u0269\u026c\3\u008aE\0\u026a\u026c"+
		"\5\63\0\0\u026b\u0269\1\0\0\0\u026b\u026a\1\0\0\0\u026cc\1\0\0\0\u026d"+
		"\u0272\3f\63\0\u026e\u026f\5*\0\0\u026f\u0271\3f\63\0\u0270\u026e\1\0"+
		"\0\0\u0271\u0274\1\0\0\0\u0272\u0270\1\0\0\0\u0272\u0273\1\0\0\0\u0273"+
		"e\1\0\0\0\u0274\u0272\1\0\0\0\u0275\u0278\3h\64\0\u0276\u0278\1\0\0\0"+
		"\u0277\u0275\1\0\0\0\u0277\u0276\1\0\0\0\u0278g\1\0\0\0\u0279\u027b\3"+
		"j\65\0\u027a\u0279\1\0\0\0\u027b\u027c\1\0\0\0\u027c\u027a\1\0\0\0\u027c"+
		"\u027d\1\0\0\0\u027di\1\0\0\0\u027e\u0281\3l\66\0\u027f\u0282\3r9\0\u0280"+
		"\u0282\1\0\0\0\u0281\u027f\1\0\0\0\u0281\u0280\1\0\0\0\u0282\u028e\1\0"+
		"\0\0\u0283\u0286\3v;\0\u0284\u0287\3r9\0\u0285\u0287\1\0\0\0\u0286\u0284"+
		"\1\0\0\0\u0286\u0285\1\0\0\0\u0287\u028e\1\0\0\0\u0288\u028e\3n\67\0\u0289"+
		"\u028b\3\22\t\0\u028a\u028c\5&\0\0\u028b\u028a\1\0\0\0\u028b\u028c\1\0"+
		"\0\0\u028c\u028e\1\0\0\0\u028d\u027e\1\0\0\0\u028d\u0283\1\0\0\0\u028d"+
		"\u0288\1\0\0\0\u028d\u0289\1\0\0\0\u028ek\1\0\0\0\u028f\u0292\3\u008a"+
		"E\0\u0290\u0293\5%\0\0\u0291\u0293\5)\0\0\u0292\u0290\1\0\0\0\u0292\u0291"+
		"\1\0\0\0\u0293\u0296\1\0\0\0\u0294\u0297\3v;\0\u0295\u0297\3~?\0\u0296"+
		"\u0294\1\0\0\0\u0296\u0295\1\0\0\0\u0297m\1\0\0\0\u0298\u029b\3~?\0\u0299"+
		"\u029c\3p8\0\u029a\u029c\1\0\0\0\u029b\u0299\1\0\0\0\u029b\u029a\1\0\0"+
		"\0\u029co\1\0\0\0\u029d\u029e\3r9\0\u029eq\1\0\0\0\u029f\u02a0\7\3\0\0"+
		"\u02a0s\1\0\0\0\u02a1\u02ab\3\u0082A\0\u02a2\u02ab\3\u0084B\0\u02a3\u02ab"+
		"\5\2\0\0\u02a4\u02ab\3x<\0\u02a5\u02ab\5\3\0\0\u02a6\u02a8\5,\0\0\u02a7"+
		"\u02a9\3\u0086C\0\u02a8\u02a7\1\0\0\0\u02a8\u02a9\1\0\0\0\u02a9\u02ab"+
		"\1\0\0\0\u02aa\u02a1\1\0\0\0\u02aa\u02a2\1\0\0\0\u02aa\u02a3\1\0\0\0\u02aa"+
		"\u02a4\1\0\0\0\u02aa\u02a5\1\0\0\0\u02aa\u02a6\1\0\0\0\u02abu\1\0\0\0"+
		"\u02ac\u02b5\3\u0082A\0\u02ad\u02b5\3\u0084B\0\u02ae\u02b5\3\u0080@\0"+
		"\u02af\u02b5\3x<\0\u02b0\u02b2\5,\0\0\u02b1\u02b3\3\u0086C\0\u02b2\u02b1"+
		"\1\0\0\0\u02b2\u02b3\1\0\0\0\u02b3\u02b5\1\0\0\0\u02b4\u02ac\1\0\0\0\u02b4"+
		"\u02ad\1\0\0\0\u02b4\u02ae\1\0\0\0\u02b4\u02af\1\0\0\0\u02b4\u02b0\1\0"+
		"\0\0\u02b5w\1\0\0\0\u02b6\u02b7\5\60\0\0\u02b7\u02bb\3|>\0\u02b8\u02b9"+
		"\5\60\0\0\u02b9\u02bb\3z=\0\u02ba\u02b6\1\0\0\0\u02ba\u02b8\1\0\0\0\u02bb"+
		"y\1\0\0\0\u02bc\u02bd\5 \0\0\u02bd\u02c2\3|>\0\u02be\u02bf\5*\0\0\u02bf"+
		"\u02c1\3|>\0\u02c0\u02be\1\0\0\0\u02c1\u02c4\1\0\0\0\u02c2\u02c0\1\0\0"+
		"\0\u02c2\u02c3\1\0\0\0\u02c3\u02c5\1\0\0\0\u02c4\u02c2\1\0\0\0\u02c5\u02c6"+
		"\5!\0\0\u02c6{\1\0\0\0\u02c7\u02cc\5\1\0\0\u02c8\u02cc\5\64\0\0\u02c9"+
		"\u02cc\3\u0082A\0\u02ca\u02cc\5\3\0\0\u02cb\u02c7\1\0\0\0\u02cb\u02c8"+
		"\1\0\0\0\u02cb\u02c9\1\0\0\0\u02cb\u02ca\1\0\0\0\u02cc}\1\0\0\0\u02cd"+
		"\u02d8\5 \0\0\u02ce\u02d0\3\6\3\0\u02cf\u02ce\1\0\0\0\u02cf\u02d0\1\0"+
		"\0\0\u02d0\u02d4\1\0\0\0\u02d1\u02d3\3@ \0\u02d2\u02d1\1\0\0\0\u02d3\u02d6"+
		"\1\0\0\0\u02d4\u02d2\1\0\0\0\u02d4\u02d5\1\0\0\0\u02d5\u02d7\1\0\0\0\u02d6"+
		"\u02d4\1\0\0\0\u02d7\u02d9\5\34\0\0\u02d8\u02cf\1\0\0\0\u02d8\u02d9\1"+
		"\0\0\0\u02d9\u02da\1\0\0\0\u02da\u02db\3d\62\0\u02db\u02dc\5!\0\0\u02dc"+
		"\177\1\0\0\0\u02dd\u02df\5\2\0\0\u02de\u02e0\3\30\f\0\u02df\u02de\1\0"+
		"\0\0\u02df\u02e0\1\0\0\0\u02e0\u0081\1\0\0\0\u02e1\u02e2\5\64\0\0\u02e2"+
		"\u02e3\5-\0\0\u02e3\u02e4\5\64\0\0\u02e4\u0083\1\0\0\0\u02e5\u02e7\5\1"+
		"\0\0\u02e6\u02e8\3\u0086C\0\u02e7\u02e6\1\0\0\0\u02e7\u02e8\1\0\0\0\u02e8"+
		"\u02ee\1\0\0\0\u02e9\u02eb\5\64\0\0\u02ea\u02ec\3\u0086C\0\u02eb\u02ea"+
		"\1\0\0\0\u02eb\u02ec\1\0\0\0\u02ec\u02ee\1\0\0\0\u02ed\u02e5\1\0\0\0\u02ed"+
		"\u02e9\1\0\0\0\u02ee\u0085\1\0\0\0\u02ef\u02f0\5#\0\0\u02f0\u02f5\3\u0088"+
		"D\0\u02f1\u02f2\5\36\0\0\u02f2\u02f4\3\u0088D\0\u02f3\u02f1\1\0\0\0\u02f4"+
		"\u02f7\1\0\0\0\u02f5\u02f3\1\0\0\0\u02f5\u02f6\1\0\0\0\u02f6\u02f8\1\0"+
		"\0\0\u02f7\u02f5\1\0\0\0\u02f8\u02f9\5$\0\0\u02f9\u0087\1\0\0\0\u02fa"+
		"\u0302\3\u008cF\0\u02fb\u02fc\3\u008aE\0\u02fc\u02ff\5%\0\0\u02fd\u0300"+
		"\3\u008cF\0\u02fe\u0300\5\64\0\0\u02ff\u02fd\1\0\0\0\u02ff\u02fe\1\0\0"+
		"\0\u0300\u0302\1\0\0\0\u0301\u02fa\1\0\0\0\u0301\u02fb\1\0\0\0\u0302\u0089"+
		"\1\0\0\0\u0303\u0304\7\4\0\0\u0304\u008b\1\0\0\0\u0305\u030a\3\u008aE"+
		"\0\u0306\u0307\5,\0\0\u0307\u0309\3\u008aE\0\u0308\u0306\1\0\0\0\u0309"+
		"\u030c\1\0\0\0\u030a\u0308\1\0\0\0\u030a\u030b\1\0\0\0\u030b\u008d\1\0"+
		"\0\0\u030c\u030a\1\0\0\0e\u008f\1\u0097\1\u009e\1\u00a8\1\u00ae\1\u00b6"+
		"\1\u00c3\1\u00cb\1\u00d5\1\u00dd\1\u00e1\1\u00fd\1\u00ff\1\u0108\1\u010f"+
		"\1\u0118\1\u011f\1\u0126\1\u0129\1\u012f\1\u0136\1\u0139\1\u013f\1\u0148"+
		"\1\u0151\1\u0158\1\u015f\1\u0166\1\u016b\1\u016e\1\u0173\1\u0178\1\u0181"+
		"\1\u0187\1\u018d\1\u0192\1\u0195\1\u019d\1\u01a5\1\u01ad\1\u01b2\1\u01b7"+
		"\1\u01ba\1\u01bd\1\u01c1\1\u01c4\1\u01c7\1\u01ca\1\u01d5\1\u01d9\1\u01e5"+
		"\1\u01ea\1\u01f5\1\u0202\1\u020d\1\u0213\1\u0216\1\u0219\1\u0227\1\u022b"+
		"\1\u022e\1\u0233\1\u0237\1\u023b\1\u023f\1\u0243\1\u0245\1\u024a\1\u024e"+
		"\1\u025a\1\u0263\1\u0267\1\u026b\1\u0272\1\u0277\1\u027c\1\u0281\1\u0286"+
		"\1\u028b\1\u028d\1\u0292\1\u0296\1\u029b\1\u02a8\1\u02aa\1\u02b2\1\u02b4"+
		"\1\u02ba\1\u02c2\1\u02cb\1\u02cf\1\u02d4\1\u02d8\1\u02df\1\u02e7\1\u02eb"+
		"\1\u02ed\1\u02f5\1\u02ff\1\u0301\1\u030a\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}