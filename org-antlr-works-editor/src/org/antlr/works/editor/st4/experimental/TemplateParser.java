// Generated from TemplateParser.g4 by ANTLR 4.0-SNAPSHOT
package org.antlr.works.editor.st4.experimental;
/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class TemplateParser extends Parser<Token> {
	public static final int
		DELIMITERS=22, LBRACK=13, BigStringLineTemplate_PERCENT=53, REGION_END=48, 
		LBRACE=15, EQUALS=11, NOT=43, ID=25, AND=45, LPAREN=5, IF=37, AT=12, QUOTE=3, 
		RPAREN=6, IMPORT=19, ESCAPE=47, COMMA=7, BIGSTRING=26, ENDIF=39, ANYCHAR=30, 
		PIPE=33, CLOSE_DELIMITER=2, SUPER=36, TEMPLATE_PARAMETER=32, DOT=8, COMMENT=29, 
		RBRACK=14, RBRACE=16, LINE_COMMENT=28, OPEN_DELIMITER=1, DEFINED=10, DEFAULT=18, 
		ELSE=40, ELLIPSIS=42, TEXT=31, TRUE=20, SEMI=46, BigStringLineTemplate_END=35, 
		ELSEIF=38, DelimitersOpenSpec_DELIMITER_STRING=50, COLON=9, GROUP=17, 
		WS=23, NEWLINE=24, BIGSTRINGLINE=27, OR=44, StringTemplate_END=54, REGION_ID=49, 
		DelimitersCloseSpec_DELIMITER_STRING=51, END=41, FALSE=21, BigStringTemplate_LANGLE=52, 
		STRING=4, BigStringTemplate_END=34;
	public static final String[] tokenNames = {
		"<INVALID>", "OPEN_DELIMITER", "CLOSE_DELIMITER", "QUOTE", "STRING", "'('", 
		"')'", "','", "'.'", "':'", "'::='", "'='", "'@'", "'['", "']'", "LBRACE", 
		"'}'", "'group'", "'default'", "'import'", "'true'", "'false'", "DELIMITERS", 
		"WS", "NEWLINE", "ID", "'<<'", "'<%'", "LINE_COMMENT", "COMMENT", "ANYCHAR", 
		"TEXT", "TEMPLATE_PARAMETER", "'|'", "'>>'", "'%>'", "'super'", "'if'", 
		"'elseif'", "'endif'", "'else'", "'end'", "'...'", "'!'", "'||'", "'&&'", 
		"';'", "ESCAPE", "'@end'", "REGION_ID", "DelimitersOpenSpec_DELIMITER_STRING", 
		"DelimitersCloseSpec_DELIMITER_STRING", "'>'", "'%'", "'\"'"
	};
	public static final int
		RULE_group = 0, RULE_oldStyleHeader = 1, RULE_groupName = 2, RULE_delimiters = 3, 
		RULE_def = 4, RULE_templateDef = 5, RULE_formalArgs = 6, RULE_formalArg = 7, 
		RULE_dictDef = 8, RULE_dict = 9, RULE_dictPairs = 10, RULE_defaultValuePair = 11, 
		RULE_keyValuePair = 12, RULE_keyValue = 13, RULE_string = 14, RULE_stringTemplate = 15, 
		RULE_bigstringTemplate = 16, RULE_bigstringTemplateNoNewline = 17, RULE_anonymousTemplate = 18, 
		RULE_anonymousTemplateParameters = 19, RULE_templateBody = 20, RULE_escape = 21, 
		RULE_exprTag = 22, RULE_region = 23, RULE_subtemplate = 24, RULE_ifstat = 25, 
		RULE_conditional = 26, RULE_andConditional = 27, RULE_notConditional = 28, 
		RULE_exprOptions = 29, RULE_option = 30, RULE_exprNoComma = 31, RULE_expr = 32, 
		RULE_mapExpr = 33, RULE_mapTemplateRef = 34, RULE_memberExpr = 35, RULE_includeExpr = 36, 
		RULE_primary = 37, RULE_arguments = 38, RULE_argExprList = 39, RULE_arg = 40, 
		RULE_namedArg = 41, RULE_list = 42, RULE_listElement = 43;
	public static final String[] ruleNames = {
		"group", "oldStyleHeader", "groupName", "delimiters", "def", "templateDef", 
		"formalArgs", "formalArg", "dictDef", "dict", "dictPairs", "defaultValuePair", 
		"keyValuePair", "keyValue", "string", "stringTemplate", "bigstringTemplate", 
		"bigstringTemplateNoNewline", "anonymousTemplate", "anonymousTemplateParameters", 
		"templateBody", "escape", "exprTag", "region", "subtemplate", "ifstat", 
		"conditional", "andConditional", "notConditional", "exprOptions", "option", 
		"exprNoComma", "expr", "mapExpr", "mapTemplateRef", "memberExpr", "includeExpr", 
		"primary", "arguments", "argExprList", "arg", "namedArg", "list", "listElement"
	};

	@Override
	public String getGrammarFileName() { return "TemplateParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	public TemplateParser(TokenStream<? extends Token> input) {
		super(input);
		_interp = new ParserATNSimulator<Token>(this,_ATN);
	}
	public static class GroupContext extends ParserRuleContext<Token> {
		public OldStyleHeaderContext oldStyleHeader() {
			return getRuleContext(OldStyleHeaderContext.class,0);
		}
		public List<? extends DefContext> def() {
			return getRuleContexts(DefContext.class);
		}
		public DelimitersContext delimiters() {
			return getRuleContext(DelimitersContext.class,0);
		}
		public List<? extends StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public DefContext def(int i) {
			return getRuleContext(DefContext.class,i);
		}
		public GroupContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_group; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitGroup(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final GroupContext group() throws RecognitionException {
		GroupContext _localctx = new GroupContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_group);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(88); oldStyleHeader();
				}
				break;
			}
			setState(92);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(91); delimiters();
				}
				break;
			}
			setState(98);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(94); match(IMPORT);
					setState(95); string();
					}
					} 
				}
				setState(100);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(102); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(101); def();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(104); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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

	public static class OldStyleHeaderContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> ID(int i) {
			return getToken(TemplateParser.ID, i);
		}
		public List<? extends TerminalNode<Token>> ID() { return getTokens(TemplateParser.ID); }
		public OldStyleHeaderContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oldStyleHeader; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterOldStyleHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitOldStyleHeader(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitOldStyleHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(1)
	public final OldStyleHeaderContext oldStyleHeader() throws RecognitionException {
		OldStyleHeaderContext _localctx = new OldStyleHeaderContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_oldStyleHeader);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106); match(GROUP);
			setState(107); match(ID);
			setState(110);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(108); match(COLON);
				setState(109); match(ID);
				}
				break;
			}
			setState(112); match(SEMI);
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

	public static class GroupNameContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> ID(int i) {
			return getToken(TemplateParser.ID, i);
		}
		public List<? extends TerminalNode<Token>> ID() { return getTokens(TemplateParser.ID); }
		public GroupNameContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupName; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterGroupName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitGroupName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitGroupName(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final GroupNameContext groupName() throws RecognitionException {
		GroupNameContext _localctx = new GroupNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_groupName);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(114); match(ID);
			setState(119);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(115); match(DOT);
					setState(116); match(ID);
					}
					} 
				}
				setState(121);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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

	public static class DelimitersContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> DelimitersOpenSpec_DELIMITER_STRING() { return getToken(TemplateParser.DelimitersOpenSpec_DELIMITER_STRING, 0); }
		public TerminalNode<Token> DELIMITERS() { return getToken(TemplateParser.DELIMITERS, 0); }
		public TerminalNode<Token> DelimitersCloseSpec_DELIMITER_STRING() { return getToken(TemplateParser.DelimitersCloseSpec_DELIMITER_STRING, 0); }
		public DelimitersContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delimiters; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterDelimiters(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitDelimiters(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitDelimiters(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(1)
	public final DelimitersContext delimiters() throws RecognitionException {
		DelimitersContext _localctx = new DelimitersContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_delimiters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122); match(DELIMITERS);
			setState(123); match(DelimitersOpenSpec_DELIMITER_STRING);
			setState(124); match(COMMA);
			setState(125); match(DelimitersCloseSpec_DELIMITER_STRING);
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

	public static class DefContext extends ParserRuleContext<Token> {
		public DictDefContext dictDef() {
			return getRuleContext(DictDefContext.class,0);
		}
		public TemplateDefContext templateDef() {
			return getRuleContext(TemplateDefContext.class,0);
		}
		public DefContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitDef(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitDef(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_def);
		try {
			setState(129);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(127); templateDef();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(128); dictDef();
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

	public static class TemplateDefContext extends ParserRuleContext<Token> {
		public Token enclosing;
		public Token name;
		public Token alias;
		public Token target;
		public StringTemplateContext stringTemplate() {
			return getRuleContext(StringTemplateContext.class,0);
		}
		public BigstringTemplateNoNewlineContext bigstringTemplateNoNewline() {
			return getRuleContext(BigstringTemplateNoNewlineContext.class,0);
		}
		public TerminalNode<Token> ID(int i) {
			return getToken(TemplateParser.ID, i);
		}
		public List<? extends TerminalNode<Token>> ID() { return getTokens(TemplateParser.ID); }
		public BigstringTemplateContext bigstringTemplate() {
			return getRuleContext(BigstringTemplateContext.class,0);
		}
		public FormalArgsContext formalArgs() {
			return getRuleContext(FormalArgsContext.class,0);
		}
		public TemplateDefContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateDef; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterTemplateDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitTemplateDef(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitTemplateDef(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final TemplateDefContext templateDef() throws RecognitionException {
		TemplateDefContext _localctx = new TemplateDefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_templateDef);
		try {
			setState(153);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(131); match(AT);
					setState(132); _localctx.enclosing = match(ID);
					setState(133); match(DOT);
					setState(134); _localctx.name = match(ID);
					setState(135); match(LPAREN);
					setState(136); match(RPAREN);
					}
					break;

				case 2:
					{
					setState(137); _localctx.name = match(ID);
					setState(138); match(LPAREN);
					setState(139); formalArgs();
					setState(140); match(RPAREN);
					}
					break;
				}
				setState(144); match(DEFINED);
				setState(148);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(145); stringTemplate();
					}
					break;

				case 2:
					{
					setState(146); bigstringTemplate();
					}
					break;

				case 3:
					{
					setState(147); bigstringTemplateNoNewline();
					}
					break;
				}
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(150); _localctx.alias = match(ID);
				setState(151); match(DEFINED);
				setState(152); _localctx.target = match(ID);
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

	public static class FormalArgsContext extends ParserRuleContext<Token> {
		public List<? extends FormalArgContext> formalArg() {
			return getRuleContexts(FormalArgContext.class);
		}
		public FormalArgContext formalArg(int i) {
			return getRuleContext(FormalArgContext.class,i);
		}
		public FormalArgsContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalArgs; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterFormalArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitFormalArgs(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitFormalArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final FormalArgsContext formalArgs() throws RecognitionException {
		FormalArgsContext _localctx = new FormalArgsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_formalArgs);
		try {
			int _alt;
			setState(164);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(155); formalArg();
				setState(160);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(156); match(COMMA);
						setState(157); formalArg();
						}
						} 
					}
					setState(162);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
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

	public static class FormalArgContext extends ParserRuleContext<Token> {
		public Token name;
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode<Token> ID() { return getToken(TemplateParser.ID, 0); }
		public TerminalNode<Token> FALSE() { return getToken(TemplateParser.FALSE, 0); }
		public TerminalNode<Token> TRUE() { return getToken(TemplateParser.TRUE, 0); }
		public AnonymousTemplateContext anonymousTemplate() {
			return getRuleContext(AnonymousTemplateContext.class,0);
		}
		public FormalArgContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalArg; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterFormalArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitFormalArg(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitFormalArg(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(1)
	public final FormalArgContext formalArg() throws RecognitionException {
		FormalArgContext _localctx = new FormalArgContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_formalArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166); _localctx.name = match(ID);
			setState(175);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(167); match(EQUALS);
				setState(173);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(168); string();
					}
					break;

				case 2:
					{
					setState(169); anonymousTemplate();
					}
					break;

				case 3:
					{
					setState(170);
					_la = _input.LA(1);
					if ( !(_la==TRUE || _la==FALSE) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					break;

				case 4:
					{
					setState(171); match(LBRACK);
					setState(172); match(RBRACK);
					}
					break;
				}
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

	public static class DictDefContext extends ParserRuleContext<Token> {
		public Token name;
		public DictContext dict() {
			return getRuleContext(DictContext.class,0);
		}
		public TerminalNode<Token> ID() { return getToken(TemplateParser.ID, 0); }
		public DictDefContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dictDef; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterDictDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitDictDef(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitDictDef(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final DictDefContext dictDef() throws RecognitionException {
		DictDefContext _localctx = new DictDefContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_dictDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177); _localctx.name = match(ID);
			setState(178); match(DEFINED);
			setState(179); dict();
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

	public static class DictContext extends ParserRuleContext<Token> {
		public DictPairsContext dictPairs() {
			return getRuleContext(DictPairsContext.class,0);
		}
		public DictContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dict; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterDict(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitDict(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitDict(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final DictContext dict() throws RecognitionException {
		DictContext _localctx = new DictContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_dict);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181); match(LBRACK);
			setState(182); dictPairs();
			setState(183); match(RBRACK);
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

	public static class DictPairsContext extends ParserRuleContext<Token> {
		public List<? extends KeyValuePairContext> keyValuePair() {
			return getRuleContexts(KeyValuePairContext.class);
		}
		public KeyValuePairContext keyValuePair(int i) {
			return getRuleContext(KeyValuePairContext.class,i);
		}
		public DefaultValuePairContext defaultValuePair() {
			return getRuleContext(DefaultValuePairContext.class,0);
		}
		public DictPairsContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dictPairs; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterDictPairs(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitDictPairs(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitDictPairs(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final DictPairsContext dictPairs() throws RecognitionException {
		DictPairsContext _localctx = new DictPairsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_dictPairs);
		try {
			int _alt;
			setState(198);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(185); keyValuePair();
				setState(190);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(186); match(COMMA);
						setState(187); keyValuePair();
						}
						} 
					}
					setState(192);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				}
				setState(195);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(193); match(COMMA);
					setState(194); defaultValuePair();
					}
					break;
				}
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(197); defaultValuePair();
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

	public static class DefaultValuePairContext extends ParserRuleContext<Token> {
		public KeyValueContext keyValue() {
			return getRuleContext(KeyValueContext.class,0);
		}
		public DefaultValuePairContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValuePair; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterDefaultValuePair(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitDefaultValuePair(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitDefaultValuePair(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final DefaultValuePairContext defaultValuePair() throws RecognitionException {
		DefaultValuePairContext _localctx = new DefaultValuePairContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_defaultValuePair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200); match(DEFAULT);
			setState(201); match(COLON);
			setState(202); keyValue();
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

	public static class KeyValuePairContext extends ParserRuleContext<Token> {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public KeyValueContext keyValue() {
			return getRuleContext(KeyValueContext.class,0);
		}
		public KeyValuePairContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyValuePair; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterKeyValuePair(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitKeyValuePair(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitKeyValuePair(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final KeyValuePairContext keyValuePair() throws RecognitionException {
		KeyValuePairContext _localctx = new KeyValuePairContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_keyValuePair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204); string();
			setState(205); match(COLON);
			setState(206); keyValue();
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

	public static class KeyValueContext extends ParserRuleContext<Token> {
		public BigstringTemplateNoNewlineContext bigstringTemplateNoNewline() {
			return getRuleContext(BigstringTemplateNoNewlineContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode<Token> ID() { return getToken(TemplateParser.ID, 0); }
		public TerminalNode<Token> FALSE() { return getToken(TemplateParser.FALSE, 0); }
		public BigstringTemplateContext bigstringTemplate() {
			return getRuleContext(BigstringTemplateContext.class,0);
		}
		public TerminalNode<Token> TRUE() { return getToken(TemplateParser.TRUE, 0); }
		public AnonymousTemplateContext anonymousTemplate() {
			return getRuleContext(AnonymousTemplateContext.class,0);
		}
		public KeyValueContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyValue; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterKeyValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitKeyValue(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitKeyValue(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final KeyValueContext keyValue() throws RecognitionException {
		KeyValueContext _localctx = new KeyValueContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_keyValue);
		int _la;
		try {
			setState(213);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(208); bigstringTemplate();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(209); bigstringTemplateNoNewline();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(210); anonymousTemplate();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(211); string();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(212);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << ID))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
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

	public static class StringContext extends ParserRuleContext<Token> {
		public StringTemplateContext stringTemplate() {
			return getRuleContext(StringTemplateContext.class,0);
		}
		public StringContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitString(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215); stringTemplate();
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

	public static class StringTemplateContext extends ParserRuleContext<Token> {
		public List<? extends TerminalNode<Token>> QUOTE() { return getTokens(TemplateParser.QUOTE); }
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public TerminalNode<Token> QUOTE(int i) {
			return getToken(TemplateParser.QUOTE, i);
		}
		public StringTemplateContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringTemplate; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterStringTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitStringTemplate(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitStringTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final StringTemplateContext stringTemplate() throws RecognitionException {
		StringTemplateContext _localctx = new StringTemplateContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_stringTemplate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217); match(QUOTE);
			setState(218); templateBody();
			setState(219); match(QUOTE);
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

	public static class BigstringTemplateContext extends ParserRuleContext<Token> {
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public TerminalNode<Token> BigStringTemplate_END() { return getToken(TemplateParser.BigStringTemplate_END, 0); }
		public TerminalNode<Token> BIGSTRING() { return getToken(TemplateParser.BIGSTRING, 0); }
		public BigstringTemplateContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bigstringTemplate; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterBigstringTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitBigstringTemplate(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitBigstringTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final BigstringTemplateContext bigstringTemplate() throws RecognitionException {
		BigstringTemplateContext _localctx = new BigstringTemplateContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_bigstringTemplate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221); match(BIGSTRING);
			setState(222); templateBody();
			setState(223); match(BigStringTemplate_END);
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

	public static class BigstringTemplateNoNewlineContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> BIGSTRINGLINE() { return getToken(TemplateParser.BIGSTRINGLINE, 0); }
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public TerminalNode<Token> BigStringLineTemplate_END() { return getToken(TemplateParser.BigStringLineTemplate_END, 0); }
		public BigstringTemplateNoNewlineContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bigstringTemplateNoNewline; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterBigstringTemplateNoNewline(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitBigstringTemplateNoNewline(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitBigstringTemplateNoNewline(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final BigstringTemplateNoNewlineContext bigstringTemplateNoNewline() throws RecognitionException {
		BigstringTemplateNoNewlineContext _localctx = new BigstringTemplateNoNewlineContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_bigstringTemplateNoNewline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225); match(BIGSTRINGLINE);
			setState(226); templateBody();
			setState(227); match(BigStringLineTemplate_END);
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

	public static class AnonymousTemplateContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> RBRACE() { return getToken(TemplateParser.RBRACE, 0); }
		public AnonymousTemplateParametersContext anonymousTemplateParameters() {
			return getRuleContext(AnonymousTemplateParametersContext.class,0);
		}
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public TerminalNode<Token> LBRACE() { return getToken(TemplateParser.LBRACE, 0); }
		public AnonymousTemplateContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonymousTemplate; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterAnonymousTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitAnonymousTemplate(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitAnonymousTemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final AnonymousTemplateContext anonymousTemplate() throws RecognitionException {
		AnonymousTemplateContext _localctx = new AnonymousTemplateContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_anonymousTemplate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229); match(LBRACE);
			setState(231);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(230); anonymousTemplateParameters();
				}
				break;
			}
			setState(233); templateBody();
			setState(234); match(RBRACE);
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

	public static class AnonymousTemplateParametersContext extends ParserRuleContext<Token> {
		public Token TEMPLATE_PARAMETER;
		public List<Token> names = new ArrayList<Token>();
		public TerminalNode<Token> TEMPLATE_PARAMETER(int i) {
			return getToken(TemplateParser.TEMPLATE_PARAMETER, i);
		}
		public TerminalNode<Token> PIPE() { return getToken(TemplateParser.PIPE, 0); }
		public TerminalNode<Token> COMMA(int i) {
			return getToken(TemplateParser.COMMA, i);
		}
		public List<? extends TerminalNode<Token>> TEMPLATE_PARAMETER() { return getTokens(TemplateParser.TEMPLATE_PARAMETER); }
		public List<? extends TerminalNode<Token>> COMMA() { return getTokens(TemplateParser.COMMA); }
		public AnonymousTemplateParametersContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonymousTemplateParameters; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterAnonymousTemplateParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitAnonymousTemplateParameters(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitAnonymousTemplateParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final AnonymousTemplateParametersContext anonymousTemplateParameters() throws RecognitionException {
		AnonymousTemplateParametersContext _localctx = new AnonymousTemplateParametersContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_anonymousTemplateParameters);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(236); _localctx.TEMPLATE_PARAMETER = match(TEMPLATE_PARAMETER);
			_localctx.names.add(_localctx.TEMPLATE_PARAMETER);
			setState(241);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(237); match(COMMA);
					setState(238); _localctx.TEMPLATE_PARAMETER = match(TEMPLATE_PARAMETER);
					_localctx.names.add(_localctx.TEMPLATE_PARAMETER);
					}
					} 
				}
				setState(243);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			setState(244); match(PIPE);
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

	public static class TemplateBodyContext extends ParserRuleContext<Token> {
		public List<? extends RegionContext> region() {
			return getRuleContexts(RegionContext.class);
		}
		public TerminalNode<Token> TEXT(int i) {
			return getToken(TemplateParser.TEXT, i);
		}
		public IfstatContext ifstat(int i) {
			return getRuleContext(IfstatContext.class,i);
		}
		public List<? extends TerminalNode<Token>> NEWLINE() { return getTokens(TemplateParser.NEWLINE); }
		public ExprTagContext exprTag(int i) {
			return getRuleContext(ExprTagContext.class,i);
		}
		public EscapeContext escape(int i) {
			return getRuleContext(EscapeContext.class,i);
		}
		public List<? extends EscapeContext> escape() {
			return getRuleContexts(EscapeContext.class);
		}
		public List<? extends ExprTagContext> exprTag() {
			return getRuleContexts(ExprTagContext.class);
		}
		public List<? extends TerminalNode<Token>> TEXT() { return getTokens(TemplateParser.TEXT); }
		public List<? extends TerminalNode<Token>> COMMENT() { return getTokens(TemplateParser.COMMENT); }
		public List<? extends IfstatContext> ifstat() {
			return getRuleContexts(IfstatContext.class);
		}
		public TerminalNode<Token> NEWLINE(int i) {
			return getToken(TemplateParser.NEWLINE, i);
		}
		public TerminalNode<Token> COMMENT(int i) {
			return getToken(TemplateParser.COMMENT, i);
		}
		public RegionContext region(int i) {
			return getRuleContext(RegionContext.class,i);
		}
		public TemplateBodyContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateBody; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterTemplateBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitTemplateBody(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitTemplateBody(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final TemplateBodyContext templateBody() throws RecognitionException {
		TemplateBodyContext _localctx = new TemplateBodyContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_templateBody);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					setState(251);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						setState(246);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << COMMENT) | (1L << TEXT))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						}
						break;

					case 2:
						{
						setState(247); ifstat();
						}
						break;

					case 3:
						{
						setState(248); region();
						}
						break;

					case 4:
						{
						setState(249); exprTag();
						}
						break;

					case 5:
						{
						setState(250); escape();
						}
						break;
					}
					} 
				}
				setState(255);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
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

	public static class EscapeContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> CLOSE_DELIMITER() { return getToken(TemplateParser.CLOSE_DELIMITER, 0); }
		public TerminalNode<Token> ESCAPE() { return getToken(TemplateParser.ESCAPE, 0); }
		public TerminalNode<Token> OPEN_DELIMITER() { return getToken(TemplateParser.OPEN_DELIMITER, 0); }
		public EscapeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_escape; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterEscape(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitEscape(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitEscape(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final EscapeContext escape() throws RecognitionException {
		EscapeContext _localctx = new EscapeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_escape);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256); match(OPEN_DELIMITER);
			setState(257); match(ESCAPE);
			setState(258); match(CLOSE_DELIMITER);
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

	public static class ExprTagContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> CLOSE_DELIMITER() { return getToken(TemplateParser.CLOSE_DELIMITER, 0); }
		public ExprOptionsContext exprOptions() {
			return getRuleContext(ExprOptionsContext.class,0);
		}
		public TerminalNode<Token> SEMI() { return getToken(TemplateParser.SEMI, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode<Token> OPEN_DELIMITER() { return getToken(TemplateParser.OPEN_DELIMITER, 0); }
		public ExprTagContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprTag; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterExprTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitExprTag(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitExprTag(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final ExprTagContext exprTag() throws RecognitionException {
		ExprTagContext _localctx = new ExprTagContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_exprTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260); match(OPEN_DELIMITER);
			setState(261); expr();
			setState(264);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(262); match(SEMI);
				setState(263); exprOptions();
				}
				break;
			}
			setState(266); match(CLOSE_DELIMITER);
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

	public static class RegionContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> REGION_ID() { return getToken(TemplateParser.REGION_ID, 0); }
		public List<? extends TerminalNode<Token>> CLOSE_DELIMITER() { return getTokens(TemplateParser.CLOSE_DELIMITER); }
		public TerminalNode<Token> OPEN_DELIMITER(int i) {
			return getToken(TemplateParser.OPEN_DELIMITER, i);
		}
		public TerminalNode<Token> REGION_END() { return getToken(TemplateParser.REGION_END, 0); }
		public List<? extends TerminalNode<Token>> OPEN_DELIMITER() { return getTokens(TemplateParser.OPEN_DELIMITER); }
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public TerminalNode<Token> CLOSE_DELIMITER(int i) {
			return getToken(TemplateParser.CLOSE_DELIMITER, i);
		}
		public RegionContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_region; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterRegion(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitRegion(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitRegion(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final RegionContext region() throws RecognitionException {
		RegionContext _localctx = new RegionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_region);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268); match(OPEN_DELIMITER);
			setState(269); match(REGION_ID);
			setState(270); match(CLOSE_DELIMITER);
			setState(271); templateBody();
			setState(272); match(OPEN_DELIMITER);
			setState(273); match(REGION_END);
			setState(274); match(CLOSE_DELIMITER);
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

	public static class SubtemplateContext extends ParserRuleContext<Token> {
		public AnonymousTemplateContext anonymousTemplate() {
			return getRuleContext(AnonymousTemplateContext.class,0);
		}
		public SubtemplateContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subtemplate; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterSubtemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitSubtemplate(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitSubtemplate(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final SubtemplateContext subtemplate() throws RecognitionException {
		SubtemplateContext _localctx = new SubtemplateContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_subtemplate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276); anonymousTemplate();
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

	public static class IfstatContext extends ParserRuleContext<Token> {
		public List<? extends TerminalNode<Token>> RPAREN() { return getTokens(TemplateParser.RPAREN); }
		public TerminalNode<Token> RPAREN(int i) {
			return getToken(TemplateParser.RPAREN, i);
		}
		public TerminalNode<Token> ELSEIF(int i) {
			return getToken(TemplateParser.ELSEIF, i);
		}
		public TerminalNode<Token> OPEN_DELIMITER(int i) {
			return getToken(TemplateParser.OPEN_DELIMITER, i);
		}
		public List<? extends TerminalNode<Token>> OPEN_DELIMITER() { return getTokens(TemplateParser.OPEN_DELIMITER); }
		public TerminalNode<Token> CLOSE_DELIMITER(int i) {
			return getToken(TemplateParser.CLOSE_DELIMITER, i);
		}
		public TerminalNode<Token> ELSE() { return getToken(TemplateParser.ELSE, 0); }
		public TerminalNode<Token> ENDIF() { return getToken(TemplateParser.ENDIF, 0); }
		public TemplateBodyContext templateBody(int i) {
			return getRuleContext(TemplateBodyContext.class,i);
		}
		public List<? extends TerminalNode<Token>> CLOSE_DELIMITER() { return getTokens(TemplateParser.CLOSE_DELIMITER); }
		public TerminalNode<Token> LPAREN(int i) {
			return getToken(TemplateParser.LPAREN, i);
		}
		public ConditionalContext conditional(int i) {
			return getRuleContext(ConditionalContext.class,i);
		}
		public List<? extends ConditionalContext> conditional() {
			return getRuleContexts(ConditionalContext.class);
		}
		public List<? extends TemplateBodyContext> templateBody() {
			return getRuleContexts(TemplateBodyContext.class);
		}
		public List<? extends TerminalNode<Token>> LPAREN() { return getTokens(TemplateParser.LPAREN); }
		public TerminalNode<Token> IF() { return getToken(TemplateParser.IF, 0); }
		public List<? extends TerminalNode<Token>> ELSEIF() { return getTokens(TemplateParser.ELSEIF); }
		public IfstatContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifstat; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterIfstat(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitIfstat(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitIfstat(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final IfstatContext ifstat() throws RecognitionException {
		IfstatContext _localctx = new IfstatContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_ifstat);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(278); match(OPEN_DELIMITER);
			setState(279); match(IF);
			setState(280); match(LPAREN);
			setState(281); conditional();
			setState(282); match(RPAREN);
			setState(283); match(CLOSE_DELIMITER);
			setState(284); templateBody();
			setState(295);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(285); match(OPEN_DELIMITER);
					setState(286); match(ELSEIF);
					setState(287); match(LPAREN);
					setState(288); conditional();
					setState(289); match(RPAREN);
					setState(290); match(CLOSE_DELIMITER);
					setState(291); templateBody();
					}
					} 
				}
				setState(297);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			setState(302);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(298); match(OPEN_DELIMITER);
				setState(299); match(ELSE);
				setState(300); match(CLOSE_DELIMITER);
				setState(301); templateBody();
				}
				break;
			}
			setState(304); match(OPEN_DELIMITER);
			setState(305); match(ENDIF);
			setState(306); match(CLOSE_DELIMITER);
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

	public static class ConditionalContext extends ParserRuleContext<Token> {
		public AndConditionalContext andConditional(int i) {
			return getRuleContext(AndConditionalContext.class,i);
		}
		public List<? extends AndConditionalContext> andConditional() {
			return getRuleContexts(AndConditionalContext.class);
		}
		public TerminalNode<Token> OR(int i) {
			return getToken(TemplateParser.OR, i);
		}
		public List<? extends TerminalNode<Token>> OR() { return getTokens(TemplateParser.OR); }
		public ConditionalContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitConditional(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitConditional(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final ConditionalContext conditional() throws RecognitionException {
		ConditionalContext _localctx = new ConditionalContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_conditional);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(308); andConditional();
			setState(313);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(309); match(OR);
					setState(310); andConditional();
					}
					} 
				}
				setState(315);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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

	public static class AndConditionalContext extends ParserRuleContext<Token> {
		public List<? extends NotConditionalContext> notConditional() {
			return getRuleContexts(NotConditionalContext.class);
		}
		public NotConditionalContext notConditional(int i) {
			return getRuleContext(NotConditionalContext.class,i);
		}
		public List<? extends TerminalNode<Token>> AND() { return getTokens(TemplateParser.AND); }
		public TerminalNode<Token> AND(int i) {
			return getToken(TemplateParser.AND, i);
		}
		public AndConditionalContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andConditional; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterAndConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitAndConditional(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitAndConditional(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final AndConditionalContext andConditional() throws RecognitionException {
		AndConditionalContext _localctx = new AndConditionalContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_andConditional);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(316); notConditional();
			setState(321);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(317); match(AND);
					setState(318); notConditional();
					}
					} 
				}
				setState(323);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
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

	public static class NotConditionalContext extends ParserRuleContext<Token> {
		public MemberExprContext memberExpr() {
			return getRuleContext(MemberExprContext.class,0);
		}
		public NotConditionalContext notConditional() {
			return getRuleContext(NotConditionalContext.class,0);
		}
		public TerminalNode<Token> NOT() { return getToken(TemplateParser.NOT, 0); }
		public NotConditionalContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notConditional; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterNotConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitNotConditional(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitNotConditional(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final NotConditionalContext notConditional() throws RecognitionException {
		NotConditionalContext _localctx = new NotConditionalContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_notConditional);
		try {
			setState(327);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(324); match(NOT);
				setState(325); notConditional();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(326); memberExpr();
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

	public static class ExprOptionsContext extends ParserRuleContext<Token> {
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public TerminalNode<Token> COMMA(int i) {
			return getToken(TemplateParser.COMMA, i);
		}
		public List<? extends TerminalNode<Token>> COMMA() { return getTokens(TemplateParser.COMMA); }
		public List<? extends OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public ExprOptionsContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprOptions; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterExprOptions(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitExprOptions(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitExprOptions(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final ExprOptionsContext exprOptions() throws RecognitionException {
		ExprOptionsContext _localctx = new ExprOptionsContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_exprOptions);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(329); option();
			setState(334);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(330); match(COMMA);
					setState(331); option();
					}
					} 
				}
				setState(336);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
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

	public static class OptionContext extends ParserRuleContext<Token> {
		public Token name;
		public ExprNoCommaContext value;
		public TerminalNode<Token> EQUALS() { return getToken(TemplateParser.EQUALS, 0); }
		public TerminalNode<Token> ID() { return getToken(TemplateParser.ID, 0); }
		public ExprNoCommaContext exprNoComma() {
			return getRuleContext(ExprNoCommaContext.class,0);
		}
		public OptionContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitOption(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(337); _localctx.name = match(ID);
			setState(340);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(338); match(EQUALS);
				setState(339); _localctx.value = exprNoComma();
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

	public static class ExprNoCommaContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> COLON() { return getToken(TemplateParser.COLON, 0); }
		public MemberExprContext memberExpr() {
			return getRuleContext(MemberExprContext.class,0);
		}
		public MapTemplateRefContext mapTemplateRef() {
			return getRuleContext(MapTemplateRefContext.class,0);
		}
		public ExprNoCommaContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprNoComma; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterExprNoComma(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitExprNoComma(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitExprNoComma(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final ExprNoCommaContext exprNoComma() throws RecognitionException {
		ExprNoCommaContext _localctx = new ExprNoCommaContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_exprNoComma);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342); memberExpr();
			setState(345);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(343); match(COLON);
				setState(344); mapTemplateRef();
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

	public static class ExprContext extends ParserRuleContext<Token> {
		public MapExprContext mapExpr() {
			return getRuleContext(MapExprContext.class,0);
		}
		public ExprContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347); mapExpr();
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

	public static class MapExprContext extends ParserRuleContext<Token> {
		public List<? extends TerminalNode<Token>> COLON() { return getTokens(TemplateParser.COLON); }
		public List<? extends MemberExprContext> memberExpr() {
			return getRuleContexts(MemberExprContext.class);
		}
		public TerminalNode<Token> COMMA(int i) {
			return getToken(TemplateParser.COMMA, i);
		}
		public List<? extends MapTemplateRefContext> mapTemplateRef() {
			return getRuleContexts(MapTemplateRefContext.class);
		}
		public List<? extends TerminalNode<Token>> COMMA() { return getTokens(TemplateParser.COMMA); }
		public MapTemplateRefContext mapTemplateRef(int i) {
			return getRuleContext(MapTemplateRefContext.class,i);
		}
		public MemberExprContext memberExpr(int i) {
			return getRuleContext(MemberExprContext.class,i);
		}
		public TerminalNode<Token> COLON(int i) {
			return getToken(TemplateParser.COLON, i);
		}
		public MapExprContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapExpr; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterMapExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitMapExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitMapExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final MapExprContext mapExpr() throws RecognitionException {
		MapExprContext _localctx = new MapExprContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_mapExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(349); memberExpr();
			setState(359);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(352); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(350); match(COMMA);
						setState(351); memberExpr();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(354); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				} while ( _alt!=2 && _alt!=-1 );
				setState(356); match(COLON);
				setState(357); mapTemplateRef();
				}
				break;
			}
			setState(372);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(361); match(COLON);
					setState(362); mapTemplateRef();
					setState(367);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(363); match(COMMA);
							setState(364); mapTemplateRef();
							}
							} 
						}
						setState(369);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
					}
					}
					} 
				}
				setState(374);
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

	public static class MapTemplateRefContext extends ParserRuleContext<Token> {
		public ArgExprListContext argExprList() {
			return getRuleContext(ArgExprListContext.class,0);
		}
		public SubtemplateContext subtemplate() {
			return getRuleContext(SubtemplateContext.class,0);
		}
		public List<? extends TerminalNode<Token>> RPAREN() { return getTokens(TemplateParser.RPAREN); }
		public TerminalNode<Token> RPAREN(int i) {
			return getToken(TemplateParser.RPAREN, i);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode<Token> LPAREN(int i) {
			return getToken(TemplateParser.LPAREN, i);
		}
		public TerminalNode<Token> ID() { return getToken(TemplateParser.ID, 0); }
		public MapExprContext mapExpr() {
			return getRuleContext(MapExprContext.class,0);
		}
		public List<? extends TerminalNode<Token>> LPAREN() { return getTokens(TemplateParser.LPAREN); }
		public MapTemplateRefContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapTemplateRef; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterMapTemplateRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitMapTemplateRef(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitMapTemplateRef(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final MapTemplateRefContext mapTemplateRef() throws RecognitionException {
		MapTemplateRefContext _localctx = new MapTemplateRefContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_mapTemplateRef);
		try {
			setState(390);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(375); match(ID);
				setState(376); match(LPAREN);
				setState(377); arguments();
				setState(378); match(RPAREN);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(380); subtemplate();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(381); match(LPAREN);
				setState(382); mapExpr();
				setState(383); match(RPAREN);
				setState(384); match(LPAREN);
				setState(386);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(385); argExprList();
					}
					break;
				}
				setState(388); match(RPAREN);
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

	public static class MemberExprContext extends ParserRuleContext<Token> {
		public List<? extends TerminalNode<Token>> RPAREN() { return getTokens(TemplateParser.RPAREN); }
		public MapExprContext mapExpr(int i) {
			return getRuleContext(MapExprContext.class,i);
		}
		public TerminalNode<Token> RPAREN(int i) {
			return getToken(TemplateParser.RPAREN, i);
		}
		public TerminalNode<Token> ID(int i) {
			return getToken(TemplateParser.ID, i);
		}
		public TerminalNode<Token> DOT(int i) {
			return getToken(TemplateParser.DOT, i);
		}
		public IncludeExprContext includeExpr() {
			return getRuleContext(IncludeExprContext.class,0);
		}
		public TerminalNode<Token> LPAREN(int i) {
			return getToken(TemplateParser.LPAREN, i);
		}
		public List<? extends TerminalNode<Token>> ID() { return getTokens(TemplateParser.ID); }
		public List<? extends TerminalNode<Token>> DOT() { return getTokens(TemplateParser.DOT); }
		public List<? extends MapExprContext> mapExpr() {
			return getRuleContexts(MapExprContext.class);
		}
		public List<? extends TerminalNode<Token>> LPAREN() { return getTokens(TemplateParser.LPAREN); }
		public MemberExprContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberExpr; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterMemberExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitMemberExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitMemberExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final MemberExprContext memberExpr() throws RecognitionException {
		MemberExprContext _localctx = new MemberExprContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_memberExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(392); includeExpr();
			setState(402);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					setState(400);
					switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
					case 1:
						{
						setState(393); match(DOT);
						setState(394); match(ID);
						}
						break;

					case 2:
						{
						setState(395); match(DOT);
						setState(396); match(LPAREN);
						setState(397); mapExpr();
						setState(398); match(RPAREN);
						}
						break;
					}
					} 
				}
				setState(404);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
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

	public static class IncludeExprContext extends ParserRuleContext<Token> {
		public Token templateName;
		public Token regionName;
		public TerminalNode<Token> REGION_ID() { return getToken(TemplateParser.REGION_ID, 0); }
		public TerminalNode<Token> AT() { return getToken(TemplateParser.AT, 0); }
		public TerminalNode<Token> RPAREN() { return getToken(TemplateParser.RPAREN, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode<Token> SUPER() { return getToken(TemplateParser.SUPER, 0); }
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public TerminalNode<Token> ID() { return getToken(TemplateParser.ID, 0); }
		public TerminalNode<Token> DOT() { return getToken(TemplateParser.DOT, 0); }
		public TerminalNode<Token> LPAREN() { return getToken(TemplateParser.LPAREN, 0); }
		public IncludeExprContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_includeExpr; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterIncludeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitIncludeExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitIncludeExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final IncludeExprContext includeExpr() throws RecognitionException {
		IncludeExprContext _localctx = new IncludeExprContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_includeExpr);
		try {
			setState(427);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(405); match(SUPER);
				setState(406); match(DOT);
				setState(407); _localctx.templateName = match(ID);
				setState(408); match(LPAREN);
				setState(409); arguments();
				setState(410); match(RPAREN);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(412); _localctx.templateName = match(ID);
				setState(413); match(LPAREN);
				setState(414); arguments();
				setState(415); match(RPAREN);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(417); match(AT);
				setState(418); match(SUPER);
				setState(419); match(DOT);
				setState(420); _localctx.templateName = match(ID);
				setState(421); match(LPAREN);
				setState(422); match(RPAREN);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(423); _localctx.regionName = match(REGION_ID);
				setState(424); match(LPAREN);
				setState(425); match(RPAREN);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(426); primary();
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

	public static class PrimaryContext extends ParserRuleContext<Token> {
		public List<? extends TerminalNode<Token>> RPAREN() { return getTokens(TemplateParser.RPAREN); }
		public TerminalNode<Token> RPAREN(int i) {
			return getToken(TemplateParser.RPAREN, i);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArgExprListContext argExprList() {
			return getRuleContext(ArgExprListContext.class,0);
		}
		public SubtemplateContext subtemplate() {
			return getRuleContext(SubtemplateContext.class,0);
		}
		public TerminalNode<Token> LPAREN(int i) {
			return getToken(TemplateParser.LPAREN, i);
		}
		public TerminalNode<Token> ID() { return getToken(TemplateParser.ID, 0); }
		public ConditionalContext conditional() {
			return getRuleContext(ConditionalContext.class,0);
		}
		public TerminalNode<Token> FALSE() { return getToken(TemplateParser.FALSE, 0); }
		public TerminalNode<Token> TRUE() { return getToken(TemplateParser.TRUE, 0); }
		public List<? extends TerminalNode<Token>> LPAREN() { return getTokens(TemplateParser.LPAREN); }
		public TerminalNode<Token> STRING() { return getToken(TemplateParser.STRING, 0); }
		public PrimaryContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitPrimary(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(1)
	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_primary);
		int _la;
		try {
			setState(446);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(429);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << TRUE) | (1L << FALSE) | (1L << ID))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(430); subtemplate();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(431); list();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(432); match(LPAREN);
				setState(433); expr();
				setState(434); match(RPAREN);
				setState(440);
				switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
				case 1:
					{
					setState(435); match(LPAREN);
					setState(437);
					switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
					case 1:
						{
						setState(436); argExprList();
						}
						break;
					}
					setState(439); match(RPAREN);
					}
					break;
				}
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(442); match(LPAREN);
				setState(443); conditional();
				setState(444); match(RPAREN);
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

	public static class ArgumentsContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> ELLIPSIS() { return getToken(TemplateParser.ELLIPSIS, 0); }
		public ArgExprListContext argExprList() {
			return getRuleContext(ArgExprListContext.class,0);
		}
		public TerminalNode<Token> COMMA(int i) {
			return getToken(TemplateParser.COMMA, i);
		}
		public NamedArgContext namedArg(int i) {
			return getRuleContext(NamedArgContext.class,i);
		}
		public List<? extends TerminalNode<Token>> COMMA() { return getTokens(TemplateParser.COMMA); }
		public List<? extends NamedArgContext> namedArg() {
			return getRuleContexts(NamedArgContext.class);
		}
		public ArgumentsContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitArguments(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_arguments);
		try {
			int _alt;
			setState(463);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(448); argExprList();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(449); namedArg();
				setState(454);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(450); match(COMMA);
						setState(451); namedArg();
						}
						} 
					}
					setState(456);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
				}
				setState(459);
				switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(457); match(COMMA);
					setState(458); match(ELLIPSIS);
					}
					break;
				}
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(461); match(ELLIPSIS);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
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

	public static class ArgExprListContext extends ParserRuleContext<Token> {
		public List<? extends ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public TerminalNode<Token> COMMA(int i) {
			return getToken(TemplateParser.COMMA, i);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public List<? extends TerminalNode<Token>> COMMA() { return getTokens(TemplateParser.COMMA); }
		public ArgExprListContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argExprList; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterArgExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitArgExprList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitArgExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final ArgExprListContext argExprList() throws RecognitionException {
		ArgExprListContext _localctx = new ArgExprListContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_argExprList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(465); arg();
			setState(470);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(466); match(COMMA);
					setState(467); arg();
					}
					} 
				}
				setState(472);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
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

	public static class ArgContext extends ParserRuleContext<Token> {
		public ExprNoCommaContext exprNoComma() {
			return getRuleContext(ExprNoCommaContext.class,0);
		}
		public ArgContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitArg(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(473); exprNoComma();
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

	public static class NamedArgContext extends ParserRuleContext<Token> {
		public Token name;
		public ArgContext value;
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public TerminalNode<Token> EQUALS() { return getToken(TemplateParser.EQUALS, 0); }
		public TerminalNode<Token> ID() { return getToken(TemplateParser.ID, 0); }
		public NamedArgContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namedArg; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterNamedArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitNamedArg(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitNamedArg(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final NamedArgContext namedArg() throws RecognitionException {
		NamedArgContext _localctx = new NamedArgContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_namedArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475); _localctx.name = match(ID);
			setState(476); match(EQUALS);
			setState(477); _localctx.value = arg();
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

	public static class ListContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> RBRACK() { return getToken(TemplateParser.RBRACK, 0); }
		public ListElementContext listElement(int i) {
			return getRuleContext(ListElementContext.class,i);
		}
		public TerminalNode<Token> COMMA(int i) {
			return getToken(TemplateParser.COMMA, i);
		}
		public TerminalNode<Token> LBRACK() { return getToken(TemplateParser.LBRACK, 0); }
		public List<? extends TerminalNode<Token>> COMMA() { return getTokens(TemplateParser.COMMA); }
		public List<? extends ListElementContext> listElement() {
			return getRuleContexts(ListElementContext.class);
		}
		public ListContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_list);
		try {
			int _alt;
			setState(492);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(479); match(LBRACK);
				setState(480); match(RBRACK);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(481); match(LBRACK);
				setState(482); listElement();
				setState(487);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(483); match(COMMA);
						setState(484); listElement();
						}
						} 
					}
					setState(489);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
				}
				setState(490); match(RBRACK);
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

	public static class ListElementContext extends ParserRuleContext<Token> {
		public ExprNoCommaContext exprNoComma() {
			return getRuleContext(ExprNoCommaContext.class,0);
		}
		public ListElementContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listElement; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).enterListElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exitListElement(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof TemplateParserVisitor<?, ?> ) return ((TemplateParserVisitor<? super Token, ? extends Result>)visitor).visitListElement(this);
			else return visitor.visitChildren(this);
		}
	}

	@RuleVersion(0)
	public final ListElementContext listElement() throws RecognitionException {
		ListElementContext _localctx = new ListElementContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_listElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(495);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				{
				setState(494); exprNoComma();
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

	public static final String _serializedATN =
		"\3\1\66\u01f2\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6"+
		"\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16\7\16\2"+
		"\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25\7\25\2"+
		"\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34\7\34\2"+
		"\35\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7%\2&"+
		"\7&\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\1\0\3\0Z\b\0\1\0\3\0]\b\0\1\0\1\0"+
		"\5\0a\b\0\n\0\f\0d\t\0\1\0\4\0g\b\0\13\0\f\0h\1\1\1\1\1\1\1\1\3\1o\b\1"+
		"\1\1\1\1\1\2\1\2\1\2\5\2v\b\2\n\2\f\2y\t\2\1\3\1\3\1\3\1\3\1\3\1\4\1\4"+
		"\3\4\u0082\b\4\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\3\5\u008f\b"+
		"\5\1\5\1\5\1\5\1\5\3\5\u0095\b\5\1\5\1\5\1\5\3\5\u009a\b\5\1\6\1\6\1\6"+
		"\5\6\u009f\b\6\n\6\f\6\u00a2\t\6\1\6\3\6\u00a5\b\6\1\7\1\7\1\7\1\7\1\7"+
		"\1\7\1\7\3\7\u00ae\b\7\3\7\u00b0\b\7\1\b\1\b\1\b\1\b\1\t\1\t\1\t\1\t\1"+
		"\n\1\n\1\n\5\n\u00bd\b\n\n\n\f\n\u00c0\t\n\1\n\1\n\3\n\u00c4\b\n\1\n\3"+
		"\n\u00c7\b\n\1\13\1\13\1\13\1\13\1\f\1\f\1\f\1\f\1\r\1\r\1\r\1\r\1\r\3"+
		"\r\u00d6\b\r\1\16\1\16\1\17\1\17\1\17\1\17\1\20\1\20\1\20\1\20\1\21\1"+
		"\21\1\21\1\21\1\22\1\22\3\22\u00e8\b\22\1\22\1\22\1\22\1\23\1\23\1\23"+
		"\5\23\u00f0\b\23\n\23\f\23\u00f3\t\23\1\23\1\23\1\24\1\24\1\24\1\24\1"+
		"\24\5\24\u00fc\b\24\n\24\f\24\u00ff\t\24\1\25\1\25\1\25\1\25\1\26\1\26"+
		"\1\26\1\26\3\26\u0109\b\26\1\26\1\26\1\27\1\27\1\27\1\27\1\27\1\27\1\27"+
		"\1\27\1\30\1\30\1\31\1\31\1\31\1\31\1\31\1\31\1\31\1\31\1\31\1\31\1\31"+
		"\1\31\1\31\1\31\1\31\5\31\u0126\b\31\n\31\f\31\u0129\t\31\1\31\1\31\1"+
		"\31\1\31\3\31\u012f\b\31\1\31\1\31\1\31\1\31\1\32\1\32\1\32\5\32\u0138"+
		"\b\32\n\32\f\32\u013b\t\32\1\33\1\33\1\33\5\33\u0140\b\33\n\33\f\33\u0143"+
		"\t\33\1\34\1\34\1\34\3\34\u0148\b\34\1\35\1\35\1\35\5\35\u014d\b\35\n"+
		"\35\f\35\u0150\t\35\1\36\1\36\1\36\3\36\u0155\b\36\1\37\1\37\1\37\3\37"+
		"\u015a\b\37\1 \1 \1!\1!\1!\4!\u0161\b!\13!\f!\u0162\1!\1!\1!\3!\u0168"+
		"\b!\1!\1!\1!\1!\5!\u016e\b!\n!\f!\u0171\t!\5!\u0173\b!\n!\f!\u0176\t!"+
		"\1\"\1\"\1\"\1\"\1\"\1\"\1\"\1\"\1\"\1\"\1\"\3\"\u0183\b\"\1\"\1\"\3\""+
		"\u0187\b\"\1#\1#\1#\1#\1#\1#\1#\1#\5#\u0191\b#\n#\f#\u0194\t#\1$\1$\1"+
		"$\1$\1$\1$\1$\1$\1$\1$\1$\1$\1$\1$\1$\1$\1$\1$\1$\1$\1$\1$\3$\u01ac\b"+
		"$\1%\1%\1%\1%\1%\1%\1%\1%\3%\u01b6\b%\1%\3%\u01b9\b%\1%\1%\1%\1%\3%\u01bf"+
		"\b%\1&\1&\1&\1&\5&\u01c5\b&\n&\f&\u01c8\t&\1&\1&\3&\u01cc\b&\1&\1&\3&"+
		"\u01d0\b&\1\'\1\'\1\'\5\'\u01d5\b\'\n\'\f\'\u01d8\t\'\1(\1(\1)\1)\1)\1"+
		")\1*\1*\1*\1*\1*\1*\5*\u01e6\b*\n*\f*\u01e9\t*\1*\1*\3*\u01ed\b*\1+\3"+
		"+\u01f0\b+\1+\0\0,\0\0\2\0\4\0\6\0\b\0\n\0\f\0\16\0\20\0\22\0\24\0\26"+
		"\0\30\0\32\0\34\0\36\0 \0\"\0$\0&\0(\0*\0,\0.\0\60\0\62\0\64\0\66\08\0"+
		":\0<\0>\0@\0B\0D\0F\0H\0J\0L\0N\0P\0R\0T\0V\0\0\4\1\24\25\2\24\25\31\31"+
		"\3\30\30\35\35\37\37\3\4\4\24\25\31\31\u0209\0Y\1\0\0\0\2j\1\0\0\0\4r"+
		"\1\0\0\0\6z\1\0\0\0\b\u0081\1\0\0\0\n\u0099\1\0\0\0\f\u00a4\1\0\0\0\16"+
		"\u00a6\1\0\0\0\20\u00b1\1\0\0\0\22\u00b5\1\0\0\0\24\u00c6\1\0\0\0\26\u00c8"+
		"\1\0\0\0\30\u00cc\1\0\0\0\32\u00d5\1\0\0\0\34\u00d7\1\0\0\0\36\u00d9\1"+
		"\0\0\0 \u00dd\1\0\0\0\"\u00e1\1\0\0\0$\u00e5\1\0\0\0&\u00ec\1\0\0\0(\u00fd"+
		"\1\0\0\0*\u0100\1\0\0\0,\u0104\1\0\0\0.\u010c\1\0\0\0\60\u0114\1\0\0\0"+
		"\62\u0116\1\0\0\0\64\u0134\1\0\0\0\66\u013c\1\0\0\08\u0147\1\0\0\0:\u0149"+
		"\1\0\0\0<\u0151\1\0\0\0>\u0156\1\0\0\0@\u015b\1\0\0\0B\u015d\1\0\0\0D"+
		"\u0186\1\0\0\0F\u0188\1\0\0\0H\u01ab\1\0\0\0J\u01be\1\0\0\0L\u01cf\1\0"+
		"\0\0N\u01d1\1\0\0\0P\u01d9\1\0\0\0R\u01db\1\0\0\0T\u01ec\1\0\0\0V\u01ef"+
		"\1\0\0\0XZ\3\2\1\0YX\1\0\0\0YZ\1\0\0\0Z\\\1\0\0\0[]\3\6\3\0\\[\1\0\0\0"+
		"\\]\1\0\0\0]b\1\0\0\0^_\5\23\0\0_a\3\34\16\0`^\1\0\0\0ad\1\0\0\0b`\1\0"+
		"\0\0bc\1\0\0\0cf\1\0\0\0db\1\0\0\0eg\3\b\4\0fe\1\0\0\0gh\1\0\0\0hf\1\0"+
		"\0\0hi\1\0\0\0i\1\1\0\0\0jk\5\21\0\0kn\5\31\0\0lm\5\t\0\0mo\5\31\0\0n"+
		"l\1\0\0\0no\1\0\0\0op\1\0\0\0pq\5.\0\0q\3\1\0\0\0rw\5\31\0\0st\5\b\0\0"+
		"tv\5\31\0\0us\1\0\0\0vy\1\0\0\0wu\1\0\0\0wx\1\0\0\0x\5\1\0\0\0yw\1\0\0"+
		"\0z{\5\26\0\0{|\5\62\0\0|}\5\7\0\0}~\5\63\0\0~\7\1\0\0\0\177\u0082\3\n"+
		"\5\0\u0080\u0082\3\20\b\0\u0081\177\1\0\0\0\u0081\u0080\1\0\0\0\u0082"+
		"\t\1\0\0\0\u0083\u0084\5\f\0\0\u0084\u0085\5\31\0\0\u0085\u0086\5\b\0"+
		"\0\u0086\u0087\5\31\0\0\u0087\u0088\5\5\0\0\u0088\u008f\5\6\0\0\u0089"+
		"\u008a\5\31\0\0\u008a\u008b\5\5\0\0\u008b\u008c\3\f\6\0\u008c\u008d\5"+
		"\6\0\0\u008d\u008f\1\0\0\0\u008e\u0083\1\0\0\0\u008e\u0089\1\0\0\0\u008f"+
		"\u0090\1\0\0\0\u0090\u0094\5\n\0\0\u0091\u0095\3\36\17\0\u0092\u0095\3"+
		" \20\0\u0093\u0095\3\"\21\0\u0094\u0091\1\0\0\0\u0094\u0092\1\0\0\0\u0094"+
		"\u0093\1\0\0\0\u0095\u009a\1\0\0\0\u0096\u0097\5\31\0\0\u0097\u0098\5"+
		"\n\0\0\u0098\u009a\5\31\0\0\u0099\u008e\1\0\0\0\u0099\u0096\1\0\0\0\u009a"+
		"\13\1\0\0\0\u009b\u00a0\3\16\7\0\u009c\u009d\5\7\0\0\u009d\u009f\3\16"+
		"\7\0\u009e\u009c\1\0\0\0\u009f\u00a2\1\0\0\0\u00a0\u009e\1\0\0\0\u00a0"+
		"\u00a1\1\0\0\0\u00a1\u00a5\1\0\0\0\u00a2\u00a0\1\0\0\0\u00a3\u00a5\1\0"+
		"\0\0\u00a4\u009b\1\0\0\0\u00a4\u00a3\1\0\0\0\u00a5\r\1\0\0\0\u00a6\u00af"+
		"\5\31\0\0\u00a7\u00ad\5\13\0\0\u00a8\u00ae\3\34\16\0\u00a9\u00ae\3$\22"+
		"\0\u00aa\u00ae\7\0\0\0\u00ab\u00ac\5\r\0\0\u00ac\u00ae\5\16\0\0\u00ad"+
		"\u00a8\1\0\0\0\u00ad\u00a9\1\0\0\0\u00ad\u00aa\1\0\0\0\u00ad\u00ab\1\0"+
		"\0\0\u00ae\u00b0\1\0\0\0\u00af\u00a7\1\0\0\0\u00af\u00b0\1\0\0\0\u00b0"+
		"\17\1\0\0\0\u00b1\u00b2\5\31\0\0\u00b2\u00b3\5\n\0\0\u00b3\u00b4\3\22"+
		"\t\0\u00b4\21\1\0\0\0\u00b5\u00b6\5\r\0\0\u00b6\u00b7\3\24\n\0\u00b7\u00b8"+
		"\5\16\0\0\u00b8\23\1\0\0\0\u00b9\u00be\3\30\f\0\u00ba\u00bb\5\7\0\0\u00bb"+
		"\u00bd\3\30\f\0\u00bc\u00ba\1\0\0\0\u00bd\u00c0\1\0\0\0\u00be\u00bc\1"+
		"\0\0\0\u00be\u00bf\1\0\0\0\u00bf\u00c3\1\0\0\0\u00c0\u00be\1\0\0\0\u00c1"+
		"\u00c2\5\7\0\0\u00c2\u00c4\3\26\13\0\u00c3\u00c1\1\0\0\0\u00c3\u00c4\1"+
		"\0\0\0\u00c4\u00c7\1\0\0\0\u00c5\u00c7\3\26\13\0\u00c6\u00b9\1\0\0\0\u00c6"+
		"\u00c5\1\0\0\0\u00c7\25\1\0\0\0\u00c8\u00c9\5\22\0\0\u00c9\u00ca\5\t\0"+
		"\0\u00ca\u00cb\3\32\r\0\u00cb\27\1\0\0\0\u00cc\u00cd\3\34\16\0\u00cd\u00ce"+
		"\5\t\0\0\u00ce\u00cf\3\32\r\0\u00cf\31\1\0\0\0\u00d0\u00d6\3 \20\0\u00d1"+
		"\u00d6\3\"\21\0\u00d2\u00d6\3$\22\0\u00d3\u00d6\3\34\16\0\u00d4\u00d6"+
		"\7\1\0\0\u00d5\u00d0\1\0\0\0\u00d5\u00d1\1\0\0\0\u00d5\u00d2\1\0\0\0\u00d5"+
		"\u00d3\1\0\0\0\u00d5\u00d4\1\0\0\0\u00d6\33\1\0\0\0\u00d7\u00d8\3\36\17"+
		"\0\u00d8\35\1\0\0\0\u00d9\u00da\5\3\0\0\u00da\u00db\3(\24\0\u00db\u00dc"+
		"\5\3\0\0\u00dc\37\1\0\0\0\u00dd\u00de\5\32\0\0\u00de\u00df\3(\24\0\u00df"+
		"\u00e0\5\"\0\0\u00e0!\1\0\0\0\u00e1\u00e2\5\33\0\0\u00e2\u00e3\3(\24\0"+
		"\u00e3\u00e4\5#\0\0\u00e4#\1\0\0\0\u00e5\u00e7\5\17\0\0\u00e6\u00e8\3"+
		"&\23\0\u00e7\u00e6\1\0\0\0\u00e7\u00e8\1\0\0\0\u00e8\u00e9\1\0\0\0\u00e9"+
		"\u00ea\3(\24\0\u00ea\u00eb\5\20\0\0\u00eb%\1\0\0\0\u00ec\u00f1\5 \0\0"+
		"\u00ed\u00ee\5\7\0\0\u00ee\u00f0\5 \0\0\u00ef\u00ed\1\0\0\0\u00f0\u00f3"+
		"\1\0\0\0\u00f1\u00ef\1\0\0\0\u00f1\u00f2\1\0\0\0\u00f2\u00f4\1\0\0\0\u00f3"+
		"\u00f1\1\0\0\0\u00f4\u00f5\5!\0\0\u00f5\'\1\0\0\0\u00f6\u00fc\7\2\0\0"+
		"\u00f7\u00fc\3\62\31\0\u00f8\u00fc\3.\27\0\u00f9\u00fc\3,\26\0\u00fa\u00fc"+
		"\3*\25\0\u00fb\u00f6\1\0\0\0\u00fb\u00f7\1\0\0\0\u00fb\u00f8\1\0\0\0\u00fb"+
		"\u00f9\1\0\0\0\u00fb\u00fa\1\0\0\0\u00fc\u00ff\1\0\0\0\u00fd\u00fb\1\0"+
		"\0\0\u00fd\u00fe\1\0\0\0\u00fe)\1\0\0\0\u00ff\u00fd\1\0\0\0\u0100\u0101"+
		"\5\1\0\0\u0101\u0102\5/\0\0\u0102\u0103\5\2\0\0\u0103+\1\0\0\0\u0104\u0105"+
		"\5\1\0\0\u0105\u0108\3@ \0\u0106\u0107\5.\0\0\u0107\u0109\3:\35\0\u0108"+
		"\u0106\1\0\0\0\u0108\u0109\1\0\0\0\u0109\u010a\1\0\0\0\u010a\u010b\5\2"+
		"\0\0\u010b-\1\0\0\0\u010c\u010d\5\1\0\0\u010d\u010e\5\61\0\0\u010e\u010f"+
		"\5\2\0\0\u010f\u0110\3(\24\0\u0110\u0111\5\1\0\0\u0111\u0112\5\60\0\0"+
		"\u0112\u0113\5\2\0\0\u0113/\1\0\0\0\u0114\u0115\3$\22\0\u0115\61\1\0\0"+
		"\0\u0116\u0117\5\1\0\0\u0117\u0118\5%\0\0\u0118\u0119\5\5\0\0\u0119\u011a"+
		"\3\64\32\0\u011a\u011b\5\6\0\0\u011b\u011c\5\2\0\0\u011c\u0127\3(\24\0"+
		"\u011d\u011e\5\1\0\0\u011e\u011f\5&\0\0\u011f\u0120\5\5\0\0\u0120\u0121"+
		"\3\64\32\0\u0121\u0122\5\6\0\0\u0122\u0123\5\2\0\0\u0123\u0124\3(\24\0"+
		"\u0124\u0126\1\0\0\0\u0125\u011d\1\0\0\0\u0126\u0129\1\0\0\0\u0127\u0125"+
		"\1\0\0\0\u0127\u0128\1\0\0\0\u0128\u012e\1\0\0\0\u0129\u0127\1\0\0\0\u012a"+
		"\u012b\5\1\0\0\u012b\u012c\5(\0\0\u012c\u012d\5\2\0\0\u012d\u012f\3(\24"+
		"\0\u012e\u012a\1\0\0\0\u012e\u012f\1\0\0\0\u012f\u0130\1\0\0\0\u0130\u0131"+
		"\5\1\0\0\u0131\u0132\5\'\0\0\u0132\u0133\5\2\0\0\u0133\63\1\0\0\0\u0134"+
		"\u0139\3\66\33\0\u0135\u0136\5,\0\0\u0136\u0138\3\66\33\0\u0137\u0135"+
		"\1\0\0\0\u0138\u013b\1\0\0\0\u0139\u0137\1\0\0\0\u0139\u013a\1\0\0\0\u013a"+
		"\65\1\0\0\0\u013b\u0139\1\0\0\0\u013c\u0141\38\34\0\u013d\u013e\5-\0\0"+
		"\u013e\u0140\38\34\0\u013f\u013d\1\0\0\0\u0140\u0143\1\0\0\0\u0141\u013f"+
		"\1\0\0\0\u0141\u0142\1\0\0\0\u0142\67\1\0\0\0\u0143\u0141\1\0\0\0\u0144"+
		"\u0145\5+\0\0\u0145\u0148\38\34\0\u0146\u0148\3F#\0\u0147\u0144\1\0\0"+
		"\0\u0147\u0146\1\0\0\0\u01489\1\0\0\0\u0149\u014e\3<\36\0\u014a\u014b"+
		"\5\7\0\0\u014b\u014d\3<\36\0\u014c\u014a\1\0\0\0\u014d\u0150\1\0\0\0\u014e"+
		"\u014c\1\0\0\0\u014e\u014f\1\0\0\0\u014f;\1\0\0\0\u0150\u014e\1\0\0\0"+
		"\u0151\u0154\5\31\0\0\u0152\u0153\5\13\0\0\u0153\u0155\3>\37\0\u0154\u0152"+
		"\1\0\0\0\u0154\u0155\1\0\0\0\u0155=\1\0\0\0\u0156\u0159\3F#\0\u0157\u0158"+
		"\5\t\0\0\u0158\u015a\3D\"\0\u0159\u0157\1\0\0\0\u0159\u015a\1\0\0\0\u015a"+
		"?\1\0\0\0\u015b\u015c\3B!\0\u015cA\1\0\0\0\u015d\u0167\3F#\0\u015e\u015f"+
		"\5\7\0\0\u015f\u0161\3F#\0\u0160\u015e\1\0\0\0\u0161\u0162\1\0\0\0\u0162"+
		"\u0160\1\0\0\0\u0162\u0163\1\0\0\0\u0163\u0164\1\0\0\0\u0164\u0165\5\t"+
		"\0\0\u0165\u0166\3D\"\0\u0166\u0168\1\0\0\0\u0167\u0160\1\0\0\0\u0167"+
		"\u0168\1\0\0\0\u0168\u0174\1\0\0\0\u0169\u016a\5\t\0\0\u016a\u016f\3D"+
		"\"\0\u016b\u016c\5\7\0\0\u016c\u016e\3D\"\0\u016d\u016b\1\0\0\0\u016e"+
		"\u0171\1\0\0\0\u016f\u016d\1\0\0\0\u016f\u0170\1\0\0\0\u0170\u0173\1\0"+
		"\0\0\u0171\u016f\1\0\0\0\u0172\u0169\1\0\0\0\u0173\u0176\1\0\0\0\u0174"+
		"\u0172\1\0\0\0\u0174\u0175\1\0\0\0\u0175C\1\0\0\0\u0176\u0174\1\0\0\0"+
		"\u0177\u0178\5\31\0\0\u0178\u0179\5\5\0\0\u0179\u017a\3L&\0\u017a\u017b"+
		"\5\6\0\0\u017b\u0187\1\0\0\0\u017c\u0187\3\60\30\0\u017d\u017e\5\5\0\0"+
		"\u017e\u017f\3B!\0\u017f\u0180\5\6\0\0\u0180\u0182\5\5\0\0\u0181\u0183"+
		"\3N\'\0\u0182\u0181\1\0\0\0\u0182\u0183\1\0\0\0\u0183\u0184\1\0\0\0\u0184"+
		"\u0185\5\6\0\0\u0185\u0187\1\0\0\0\u0186\u0177\1\0\0\0\u0186\u017c\1\0"+
		"\0\0\u0186\u017d\1\0\0\0\u0187E\1\0\0\0\u0188\u0192\3H$\0\u0189\u018a"+
		"\5\b\0\0\u018a\u0191\5\31\0\0\u018b\u018c\5\b\0\0\u018c\u018d\5\5\0\0"+
		"\u018d\u018e\3B!\0\u018e\u018f\5\6\0\0\u018f\u0191\1\0\0\0\u0190\u0189"+
		"\1\0\0\0\u0190\u018b\1\0\0\0\u0191\u0194\1\0\0\0\u0192\u0190\1\0\0\0\u0192"+
		"\u0193\1\0\0\0\u0193G\1\0\0\0\u0194\u0192\1\0\0\0\u0195\u0196\5$\0\0\u0196"+
		"\u0197\5\b\0\0\u0197\u0198\5\31\0\0\u0198\u0199\5\5\0\0\u0199\u019a\3"+
		"L&\0\u019a\u019b\5\6\0\0\u019b\u01ac\1\0\0\0\u019c\u019d\5\31\0\0\u019d"+
		"\u019e\5\5\0\0\u019e\u019f\3L&\0\u019f\u01a0\5\6\0\0\u01a0\u01ac\1\0\0"+
		"\0\u01a1\u01a2\5\f\0\0\u01a2\u01a3\5$\0\0\u01a3\u01a4\5\b\0\0\u01a4\u01a5"+
		"\5\31\0\0\u01a5\u01a6\5\5\0\0\u01a6\u01ac\5\6\0\0\u01a7\u01a8\5\61\0\0"+
		"\u01a8\u01a9\5\5\0\0\u01a9\u01ac\5\6\0\0\u01aa\u01ac\3J%\0\u01ab\u0195"+
		"\1\0\0\0\u01ab\u019c\1\0\0\0\u01ab\u01a1\1\0\0\0\u01ab\u01a7\1\0\0\0\u01ab"+
		"\u01aa\1\0\0\0\u01acI\1\0\0\0\u01ad\u01bf\7\3\0\0\u01ae\u01bf\3\60\30"+
		"\0\u01af\u01bf\3T*\0\u01b0\u01b1\5\5\0\0\u01b1\u01b2\3@ \0\u01b2\u01b8"+
		"\5\6\0\0\u01b3\u01b5\5\5\0\0\u01b4\u01b6\3N\'\0\u01b5\u01b4\1\0\0\0\u01b5"+
		"\u01b6\1\0\0\0\u01b6\u01b7\1\0\0\0\u01b7\u01b9\5\6\0\0\u01b8\u01b3\1\0"+
		"\0\0\u01b8\u01b9\1\0\0\0\u01b9\u01bf\1\0\0\0\u01ba\u01bb\5\5\0\0\u01bb"+
		"\u01bc\3\64\32\0\u01bc\u01bd\5\6\0\0\u01bd\u01bf\1\0\0\0\u01be\u01ad\1"+
		"\0\0\0\u01be\u01ae\1\0\0\0\u01be\u01af\1\0\0\0\u01be\u01b0\1\0\0\0\u01be"+
		"\u01ba\1\0\0\0\u01bfK\1\0\0\0\u01c0\u01d0\3N\'\0\u01c1\u01c6\3R)\0\u01c2"+
		"\u01c3\5\7\0\0\u01c3\u01c5\3R)\0\u01c4\u01c2\1\0\0\0\u01c5\u01c8\1\0\0"+
		"\0\u01c6\u01c4\1\0\0\0\u01c6\u01c7\1\0\0\0\u01c7\u01cb\1\0\0\0\u01c8\u01c6"+
		"\1\0\0\0\u01c9\u01ca\5\7\0\0\u01ca\u01cc\5*\0\0\u01cb\u01c9\1\0\0\0\u01cb"+
		"\u01cc\1\0\0\0\u01cc\u01d0\1\0\0\0\u01cd\u01d0\5*\0\0\u01ce\u01d0\1\0"+
		"\0\0\u01cf\u01c0\1\0\0\0\u01cf\u01c1\1\0\0\0\u01cf\u01cd\1\0\0\0\u01cf"+
		"\u01ce\1\0\0\0\u01d0M\1\0\0\0\u01d1\u01d6\3P(\0\u01d2\u01d3\5\7\0\0\u01d3"+
		"\u01d5\3P(\0\u01d4\u01d2\1\0\0\0\u01d5\u01d8\1\0\0\0\u01d6\u01d4\1\0\0"+
		"\0\u01d6\u01d7\1\0\0\0\u01d7O\1\0\0\0\u01d8\u01d6\1\0\0\0\u01d9\u01da"+
		"\3>\37\0\u01daQ\1\0\0\0\u01db\u01dc\5\31\0\0\u01dc\u01dd\5\13\0\0\u01dd"+
		"\u01de\3P(\0\u01deS\1\0\0\0\u01df\u01e0\5\r\0\0\u01e0\u01ed\5\16\0\0\u01e1"+
		"\u01e2\5\r\0\0\u01e2\u01e7\3V+\0\u01e3\u01e4\5\7\0\0\u01e4\u01e6\3V+\0"+
		"\u01e5\u01e3\1\0\0\0\u01e6\u01e9\1\0\0\0\u01e7\u01e5\1\0\0\0\u01e7\u01e8"+
		"\1\0\0\0\u01e8\u01ea\1\0\0\0\u01e9\u01e7\1\0\0\0\u01ea\u01eb\5\16\0\0"+
		"\u01eb\u01ed\1\0\0\0\u01ec\u01df\1\0\0\0\u01ec\u01e1\1\0\0\0\u01edU\1"+
		"\0\0\0\u01ee\u01f0\3>\37\0\u01ef\u01ee\1\0\0\0\u01ef\u01f0\1\0\0\0\u01f0"+
		"W\1\0\0\0\62Y\\bhnw\u0081\u008e\u0094\u0099\u00a0\u00a4\u00ad\u00af\u00be"+
		"\u00c3\u00c6\u00d5\u00e7\u00f1\u00fb\u00fd\u0108\u0127\u012e\u0139\u0141"+
		"\u0147\u014e\u0154\u0159\u0162\u0167\u016f\u0174\u0182\u0186\u0190\u0192"+
		"\u01ab\u01b5\u01b8\u01be\u01c6\u01cb\u01cf\u01d6\u01e7\u01ec\u01ef";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	}
}