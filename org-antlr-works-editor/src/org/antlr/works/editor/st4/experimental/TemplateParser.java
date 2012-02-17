// $ANTLR ANTLRVersion> TemplateParser.java generatedTimestamp>
/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.experimental;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TemplateParser extends Parser {
	public static final int
		StringTemplate_NEWLINE=101, BigStringLineTemplate_PERCENT=70, EQUALS=12, 
		NOT=90, AnonymousTemplateParameters_NEWLINE=51, TemplateExpression_ANYCHAR=100, 
		TemplateExpression_CLOSE_DELIMITER=98, RPAREN=7, QUOTE=5, IMPORT=20, ESCAPE=94, 
		TemplateComment_CONTINUE_COMMENT=39, BigStringLineTemplate_OPEN_DELIMITER=66, 
		TemplateExpression_RPAREN=77, BIGSTRING=28, ENDIF=86, ANYCHAR=32, CLOSE_DELIMITER=4, 
		SUPER=83, TemplateExpression_LBRACE=81, COMMENT=31, TemplateExpression_ID=97, 
		DelimitersCloseSpec_WS=112, TemplateExpression_LBRACK=78, RBRACK=15, BigStringTemplate_ANYCHAR=63, 
		RBRACE=17, LINE_COMMENT=30, AnonymousTemplate_COMMENT=45, OPEN_DELIMITER=3, 
		DEFINED=11, ELSE=87, ELLIPSIS=89, DelimitersOpenSpec_WS=108, BigStringLineTemplate_END=69, 
		GROUP=18, BigStringTemplate_ESCAPE=60, WS=24, BIGSTRINGLINE=29, AnonymousTemplateParameters_WS=50, 
		AnonymousTemplateParameters_ANYCHAR=55, OR=91, TemplateExpression_COLON=75, 
		END=88, FALSE=22, DelimitersCloseSpec_NEWLINE=113, BlockComment_NEWLINE=33, 
		BigStringTemplate_END=61, DELIMITERS=23, TemplateExpression_LPAREN=76, 
		TemplateComment_BLOCK_COMMENT_BANG=41, TemplateComment_END_COMMENT=40, 
		BigStringTemplate_COMMENT=57, LBRACK=14, StringTemplate_COMMENT=102, END_COMMENT=35, 
		REGION_END=95, BigStringTemplate_OPEN_DELIMITER=58, LBRACE=16, BigStringLineTemplate_NEWLINE=64, 
		StringTemplate_ANYCHAR=107, ID=26, AND=92, BigStringLineTemplate_ANYCHAR=71, 
		TemplateComment_ANYCHAR=42, LPAREN=6, TemplateExpression_EQUALS=80, IF=84, 
		StringTemplate_ESCAPE=105, AT=13, AnonymousTemplate_OPEN_DELIMITER=46, 
		AnonymousTemplate_ANYCHAR=49, BigStringTemplate_NEWLINE=56, DelimitersOpenSpec_NEWLINE=109, 
		COMMA=8, TemplateExpression_COMMA=74, BigStringLineTemplate_COMMENT=65, 
		AnonymousTemplate_NEWLINE=44, CONTINUE_COMMENT=34, PIPE=54, TEMPLATE_PARAMETER=53, 
		DOT=9, BigStringTemplate_TEXT=59, AnonymousTemplate_RBRACE=43, AnonymousTemplateParameters_COMMA=52, 
		TemplateExpression_RBRACK=79, BigStringLineTemplate_TEXT=67, BigStringLineTemplate_ESCAPE=68, 
		DEFAULT=19, TemplateExpression_WS=82, TemplateExpression_DOT=73, TEXT=47, 
		TemplateExpression_NEWLINE=72, TRUE=21, SEMI=93, StringTemplate_OPEN_DELIMITER=103, 
		ELSEIF=85, DelimitersOpenSpec_DELIMITER_STRING=110, COLON=10, TemplateExpression_STRING=99, 
		ESCAPE_RBRACE=48, BlockComment_ANYCHAR=37, NEWLINE=25, TemplateComment_NEWLINE=38, 
		StringTemplate_TEXT=104, StringTemplate_END=106, REGION_ID=96, DelimitersCloseSpec_DELIMITER_STRING=114, 
		BLOCK_COMMENT_STAR=36, DelimitersOpenSpec_COMMA=111, BigStringTemplate_LANGLE=62, 
		STRING=27;
	public static final String[] tokenNames = {
		"<INVALID>", "<INVALID>", "<INVALID>",
		"OPEN_DELIMITER", "CLOSE_DELIMITER", "QUOTE", "'('", "')'", "','", "'.'", 
		"':'", "'::='", "'='", "'@'", "'['", "']'", "LBRACE", "'}'", "'group'", 
		"'default'", "'import'", "'true'", "'false'", "DELIMITERS", "WS", "NEWLINE", 
		"ID", "STRING", "BIGSTRING", "BIGSTRINGLINE", "LINE_COMMENT", "COMMENT", 
		"ANYCHAR", "BlockComment_NEWLINE", "CONTINUE_COMMENT", "END_COMMENT", 
		"BLOCK_COMMENT_STAR", "BlockComment_ANYCHAR", "TemplateComment_NEWLINE", 
		"TemplateComment_CONTINUE_COMMENT", "TemplateComment_END_COMMENT", "TemplateComment_BLOCK_COMMENT_BANG", 
		"TemplateComment_ANYCHAR", "AnonymousTemplate_RBRACE", "AnonymousTemplate_NEWLINE", 
		"AnonymousTemplate_COMMENT", "AnonymousTemplate_OPEN_DELIMITER", "TEXT", 
		"ESCAPE_RBRACE", "AnonymousTemplate_ANYCHAR", "AnonymousTemplateParameters_WS", 
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
		"'super'", "'if'", "'elseif'", "'endif'", "'else'", "'end'", "'...'", 
		"'!'", "'||'", "'&&'", "';'", "ESCAPE", "'@end'", "REGION_ID", "TemplateExpression_ID", 
		"TemplateExpression_CLOSE_DELIMITER", "TemplateExpression_STRING", "TemplateExpression_ANYCHAR", 
		"StringTemplate_NEWLINE", "StringTemplate_COMMENT", "StringTemplate_OPEN_DELIMITER", 
		"StringTemplate_TEXT", "StringTemplate_ESCAPE", "StringTemplate_END", 
		"StringTemplate_ANYCHAR", "DelimitersOpenSpec_WS", "DelimitersOpenSpec_NEWLINE", 
		"DelimitersOpenSpec_DELIMITER_STRING", "DelimitersOpenSpec_COMMA", "DelimitersCloseSpec_WS", 
		"DelimitersCloseSpec_NEWLINE", "DelimitersCloseSpec_DELIMITER_STRING", 
		"'delimiters'", "'implements'"
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
	public TemplateParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator<Token>(this,_ATN);
	}
	public static class groupContext extends ParserRuleContext<Token> {
		public oldStyleHeaderContext oldStyleHeader() {
		    return (oldStyleHeaderContext)getRuleContext(oldStyleHeaderContext.class,0);
		}
		public defContext def() {
		    return (defContext)getRuleContext(defContext.class,0);
		}
		public delimitersContext delimiters() {
		    return (delimitersContext)getRuleContext(delimitersContext.class,0);
		}
		public stringContext string() {
		    return (stringContext)getRuleContext(stringContext.class,0);
		}
		public groupContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).groupEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).groupExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).groupVisit(this);
		    else return null;
		}
	}

	public final groupContext group() throws RecognitionException {
		groupContext _localctx = new groupContext(_ctx, 0);
		enterRule(_localctx, RULE_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(88); oldStyleHeader();
					}
					break;
			}
			setState(94);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(92); delimiters();
					}
					break;
			}
			setState(102);
			_errHandler.sync(this);
			int _alt44 = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt44!=2 && _alt44!=-1 ) {
				if ( _alt44==1 ) {
					{
					{
					setState(96); match(IMPORT);
					setState(98); string();
					}
					} 
				}
				setState(104);
				_errHandler.sync(this);
				_alt44 = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(107); 
			_errHandler.sync(this);
			int _alt47 = getInterpreter().adaptivePredict(_input,3,_ctx);
			do {
				switch ( _alt47 ) {
					case 1:
						{
						{
						setState(105); def();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(109); 
				_errHandler.sync(this);
				_alt47 = getInterpreter().adaptivePredict(_input,3,_ctx);
			} while ( _alt47!=2 && _alt47!=-1 );
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

	public static class oldStyleHeaderContext extends ParserRuleContext<Token> {
		public Token ID(int i) {
		    return getToken(TemplateParser.ID, i);
		}
		public List<? extends Token> ID() { return getTokens(TemplateParser.ID); }
		public oldStyleHeaderContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).oldStyleHeaderEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).oldStyleHeaderExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).oldStyleHeaderVisit(this);
		    else return null;
		}
	}

	public final oldStyleHeaderContext oldStyleHeader() throws RecognitionException {
		oldStyleHeaderContext _localctx = new oldStyleHeaderContext(_ctx, 2);
		enterRule(_localctx, RULE_oldStyleHeader);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111); match(GROUP);
			setState(113); match(ID);
			setState(119);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(115); match(COLON);
					setState(117); match(ID);
					}
					break;
			}
			setState(134);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(121); match(116);
					setState(123); match(ID);
					setState(131);
					_errHandler.sync(this);
					int _alt76 = getInterpreter().adaptivePredict(_input,5,_ctx);
					while ( _alt76!=2 && _alt76!=-1 ) {
						if ( _alt76==1 ) {
							{
							{
							setState(125); match(COMMA);
							setState(127); match(ID);
							}
							} 
						}
						setState(133);
						_errHandler.sync(this);
						_alt76 = getInterpreter().adaptivePredict(_input,5,_ctx);
					}
					}
					break;
			}
			setState(136); match(SEMI);
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

	public static class groupNameContext extends ParserRuleContext<Token> {
		public Token ID(int i) {
		    return getToken(TemplateParser.ID, i);
		}
		public List<? extends Token> ID() { return getTokens(TemplateParser.ID); }
		public groupNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).groupNameEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).groupNameExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).groupNameVisit(this);
		    else return null;
		}
	}

	public final groupNameContext groupName() throws RecognitionException {
		groupNameContext _localctx = new groupNameContext(_ctx, 4);
		enterRule(_localctx, RULE_groupName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138); match(ID);
			setState(146);
			_errHandler.sync(this);
			int _alt95 = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt95!=2 && _alt95!=-1 ) {
				if ( _alt95==1 ) {
					{
					{
					setState(140); match(DOT);
					setState(142); match(ID);
					}
					} 
				}
				setState(148);
				_errHandler.sync(this);
				_alt95 = getInterpreter().adaptivePredict(_input,7,_ctx);
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

	public static class delimitersContext extends ParserRuleContext<Token> {
		public Token DelimitersOpenSpec_DELIMITER_STRING() { return getToken(TemplateParser.DelimitersOpenSpec_DELIMITER_STRING, 0); }
		public Token DelimitersCloseSpec_DELIMITER_STRING() { return getToken(TemplateParser.DelimitersCloseSpec_DELIMITER_STRING, 0); }
		public delimitersContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).delimitersEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).delimitersExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).delimitersVisit(this);
		    else return null;
		}
	}

	public final delimitersContext delimiters() throws RecognitionException {
		delimitersContext _localctx = new delimitersContext(_ctx, 6);
		enterRule(_localctx, RULE_delimiters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149); match(115);
			setState(151); match(DelimitersOpenSpec_DELIMITER_STRING);
			setState(153); match(COMMA);
			setState(155); match(DelimitersCloseSpec_DELIMITER_STRING);
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

	public static class defContext extends ParserRuleContext<Token> {
		public dictDefContext dictDef() {
		    return (dictDefContext)getRuleContext(dictDefContext.class,0);
		}
		public templateDefContext templateDef() {
		    return (templateDefContext)getRuleContext(templateDefContext.class,0);
		}
		public defContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).defEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).defExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).defVisit(this);
		    else return null;
		}
	}

	public final defContext def() throws RecognitionException {
		defContext _localctx = new defContext(_ctx, 8);
		enterRule(_localctx, RULE_def);
		try {
			setState(161);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(157); templateDef();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(159); dictDef();
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

	public static class templateDefContext extends ParserRuleContext<Token> {
		public Token enclosing;
		public Token name;
		public Token alias;
		public Token target;
		public stringTemplateContext stringTemplate() {
		    return (stringTemplateContext)getRuleContext(stringTemplateContext.class,0);
		}
		public bigstringTemplateNoNewlineContext bigstringTemplateNoNewline() {
		    return (bigstringTemplateNoNewlineContext)getRuleContext(bigstringTemplateNoNewlineContext.class,0);
		}
		public Token ID(int i) {
		    return getToken(TemplateParser.ID, i);
		}
		public List<? extends Token> ID() { return getTokens(TemplateParser.ID); }
		public bigstringTemplateContext bigstringTemplate() {
		    return (bigstringTemplateContext)getRuleContext(bigstringTemplateContext.class,0);
		}
		public formalArgsContext formalArgs() {
		    return (formalArgsContext)getRuleContext(formalArgsContext.class,0);
		}
		public templateDefContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).templateDefEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).templateDefExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).templateDefVisit(this);
		    else return null;
		}
	}

	public final templateDefContext templateDef() throws RecognitionException {
		templateDefContext _localctx = new templateDefContext(_ctx, 10);
		enterRule(_localctx, RULE_templateDef);
		try {
			setState(201);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(183);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
						case 1:
							{
							setState(163); match(AT);
							setState(165); ((templateDefContext)_localctx).enclosing = match(ID);
							setState(167); match(DOT);
							setState(169); ((templateDefContext)_localctx).name = match(ID);
							setState(171); match(LPAREN);
							setState(173); match(RPAREN);
							}
							break;

						case 2:
							{
							setState(175); ((templateDefContext)_localctx).name = match(ID);
							setState(177); match(LPAREN);
							setState(179); formalArgs();
							setState(181); match(RPAREN);
							}
							break;
					}
					setState(185); match(DEFINED);
					setState(193);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
						case 1:
							{
							setState(187); stringTemplate();
							}
							break;

						case 2:
							{
							setState(189); bigstringTemplate();
							}
							break;

						case 3:
							{
							setState(191); bigstringTemplateNoNewline();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(195); ((templateDefContext)_localctx).alias = match(ID);
					setState(197); match(DEFINED);
					setState(199); ((templateDefContext)_localctx).target = match(ID);
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

	public static class formalArgsContext extends ParserRuleContext<Token> {
		public List<? extends formalArgContext> formalArg() {
		    return (List<formalArgContext>)getRuleContexts(formalArgContext.class);
		}
		public formalArgContext formalArg(int i) {
		    return (formalArgContext)getRuleContext(formalArgContext.class,i);
		}
		public formalArgsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).formalArgsEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).formalArgsExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).formalArgsVisit(this);
		    else return null;
		}
	}

	public final formalArgsContext formalArgs() throws RecognitionException {
		formalArgsContext _localctx = new formalArgsContext(_ctx, 12);
		enterRule(_localctx, RULE_formalArgs);
		try {
			setState(216);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(203); formalArg();
					setState(211);
					_errHandler.sync(this);
					int _alt202 = getInterpreter().adaptivePredict(_input,12,_ctx);
					while ( _alt202!=2 && _alt202!=-1 ) {
						if ( _alt202==1 ) {
							{
							{
							setState(205); match(COMMA);
							setState(207); formalArg();
							}
							} 
						}
						setState(213);
						_errHandler.sync(this);
						_alt202 = getInterpreter().adaptivePredict(_input,12,_ctx);
					}
					}
					break;

				case 2:
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

	public static class formalArgContext extends ParserRuleContext<Token> {
		public Token name;
		public stringContext string() {
		    return (stringContext)getRuleContext(stringContext.class,0);
		}
		public Token ID() { return getToken(TemplateParser.ID, 0); }
		public Token FALSE() { return getToken(TemplateParser.FALSE, 0); }
		public Token TRUE() { return getToken(TemplateParser.TRUE, 0); }
		public anonymousTemplateContext anonymousTemplate() {
		    return (anonymousTemplateContext)getRuleContext(anonymousTemplateContext.class,0);
		}
		public formalArgContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).formalArgEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).formalArgExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).formalArgVisit(this);
		    else return null;
		}
	}

	public final formalArgContext formalArg() throws RecognitionException {
		formalArgContext _localctx = new formalArgContext(_ctx, 14);
		enterRule(_localctx, RULE_formalArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218); ((formalArgContext)_localctx).name = match(ID);
			setState(232);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(220); match(EQUALS);
					setState(230);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
						case 1:
							{
							setState(222); string();
							}
							break;

						case 2:
							{
							setState(224); anonymousTemplate();
							}
							break;

						case 3:
							{
							setState(226); match(TRUE);
							}
							break;

						case 4:
							{
							setState(228); match(FALSE);
							}
							break;
					}
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

	public static class dictDefContext extends ParserRuleContext<Token> {
		public Token name;
		public dictContext dict() {
		    return (dictContext)getRuleContext(dictContext.class,0);
		}
		public Token ID() { return getToken(TemplateParser.ID, 0); }
		public dictDefContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).dictDefEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).dictDefExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).dictDefVisit(this);
		    else return null;
		}
	}

	public final dictDefContext dictDef() throws RecognitionException {
		dictDefContext _localctx = new dictDefContext(_ctx, 16);
		enterRule(_localctx, RULE_dictDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234); ((dictDefContext)_localctx).name = match(ID);
			setState(236); match(DEFINED);
			setState(238); dict();
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

	public static class dictContext extends ParserRuleContext<Token> {
		public dictPairsContext dictPairs() {
		    return (dictPairsContext)getRuleContext(dictPairsContext.class,0);
		}
		public dictContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).dictEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).dictExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).dictVisit(this);
		    else return null;
		}
	}

	public final dictContext dict() throws RecognitionException {
		dictContext _localctx = new dictContext(_ctx, 18);
		enterRule(_localctx, RULE_dict);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240); match(LBRACK);
			setState(242); dictPairs();
			setState(244); match(RBRACK);
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

	public static class dictPairsContext extends ParserRuleContext<Token> {
		public List<? extends keyValuePairContext> keyValuePair() {
		    return (List<keyValuePairContext>)getRuleContexts(keyValuePairContext.class);
		}
		public keyValuePairContext keyValuePair(int i) {
		    return (keyValuePairContext)getRuleContext(keyValuePairContext.class,i);
		}
		public defaultValuePairContext defaultValuePair() {
		    return (defaultValuePairContext)getRuleContext(defaultValuePairContext.class,0);
		}
		public dictPairsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).dictPairsEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).dictPairsExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).dictPairsVisit(this);
		    else return null;
		}
	}

	public final dictPairsContext dictPairs() throws RecognitionException {
		dictPairsContext _localctx = new dictPairsContext(_ctx, 20);
		enterRule(_localctx, RULE_dictPairs);
		try {
			setState(265);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(246); keyValuePair();
					setState(254);
					_errHandler.sync(this);
					int _alt278 = getInterpreter().adaptivePredict(_input,16,_ctx);
					while ( _alt278!=2 && _alt278!=-1 ) {
						if ( _alt278==1 ) {
							{
							{
							setState(248); match(COMMA);
							setState(250); keyValuePair();
							}
							} 
						}
						setState(256);
						_errHandler.sync(this);
						_alt278 = getInterpreter().adaptivePredict(_input,16,_ctx);
					}
					setState(261);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
						case 1:
							{
							setState(257); match(COMMA);
							setState(259); defaultValuePair();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(263); defaultValuePair();
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

	public static class defaultValuePairContext extends ParserRuleContext<Token> {
		public keyValueContext keyValue() {
		    return (keyValueContext)getRuleContext(keyValueContext.class,0);
		}
		public defaultValuePairContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).defaultValuePairEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).defaultValuePairExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).defaultValuePairVisit(this);
		    else return null;
		}
	}

	public final defaultValuePairContext defaultValuePair() throws RecognitionException {
		defaultValuePairContext _localctx = new defaultValuePairContext(_ctx, 22);
		enterRule(_localctx, RULE_defaultValuePair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267); match(DEFAULT);
			setState(269); match(COLON);
			setState(271); keyValue();
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

	public static class keyValuePairContext extends ParserRuleContext<Token> {
		public stringContext string() {
		    return (stringContext)getRuleContext(stringContext.class,0);
		}
		public keyValueContext keyValue() {
		    return (keyValueContext)getRuleContext(keyValueContext.class,0);
		}
		public keyValuePairContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).keyValuePairEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).keyValuePairExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).keyValuePairVisit(this);
		    else return null;
		}
	}

	public final keyValuePairContext keyValuePair() throws RecognitionException {
		keyValuePairContext _localctx = new keyValuePairContext(_ctx, 24);
		enterRule(_localctx, RULE_keyValuePair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273); string();
			setState(275); match(COLON);
			setState(277); keyValue();
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

	public static class keyValueContext extends ParserRuleContext<Token> {
		public bigstringTemplateNoNewlineContext bigstringTemplateNoNewline() {
		    return (bigstringTemplateNoNewlineContext)getRuleContext(bigstringTemplateNoNewlineContext.class,0);
		}
		public stringContext string() {
		    return (stringContext)getRuleContext(stringContext.class,0);
		}
		public Token ID() { return getToken(TemplateParser.ID, 0); }
		public Token FALSE() { return getToken(TemplateParser.FALSE, 0); }
		public bigstringTemplateContext bigstringTemplate() {
		    return (bigstringTemplateContext)getRuleContext(bigstringTemplateContext.class,0);
		}
		public Token TRUE() { return getToken(TemplateParser.TRUE, 0); }
		public anonymousTemplateContext anonymousTemplate() {
		    return (anonymousTemplateContext)getRuleContext(anonymousTemplateContext.class,0);
		}
		public keyValueContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).keyValueEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).keyValueExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).keyValueVisit(this);
		    else return null;
		}
	}

	public final keyValueContext keyValue() throws RecognitionException {
		keyValueContext _localctx = new keyValueContext(_ctx, 26);
		enterRule(_localctx, RULE_keyValue);
		try {
			setState(293);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(279); bigstringTemplate();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(281); bigstringTemplateNoNewline();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(283); anonymousTemplate();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(285); string();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(287); match(TRUE);
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(289); match(FALSE);
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(291); match(ID);
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

	public static class stringContext extends ParserRuleContext<Token> {
		public stringTemplateContext stringTemplate() {
		    return (stringTemplateContext)getRuleContext(stringTemplateContext.class,0);
		}
		public stringContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).stringEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).stringExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).stringVisit(this);
		    else return null;
		}
	}

	public final stringContext string() throws RecognitionException {
		stringContext _localctx = new stringContext(_ctx, 28);
		enterRule(_localctx, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295); stringTemplate();
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

	public static class stringTemplateContext extends ParserRuleContext<Token> {
		public List<? extends Token> QUOTE() { return getTokens(TemplateParser.QUOTE); }
		public templateBodyContext templateBody() {
		    return (templateBodyContext)getRuleContext(templateBodyContext.class,0);
		}
		public Token QUOTE(int i) {
		    return getToken(TemplateParser.QUOTE, i);
		}
		public stringTemplateContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).stringTemplateEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).stringTemplateExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).stringTemplateVisit(this);
		    else return null;
		}
	}

	public final stringTemplateContext stringTemplate() throws RecognitionException {
		stringTemplateContext _localctx = new stringTemplateContext(_ctx, 30);
		enterRule(_localctx, RULE_stringTemplate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297); match(QUOTE);
			setState(299); templateBody();
			setState(301); match(QUOTE);
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

	public static class bigstringTemplateContext extends ParserRuleContext<Token> {
		public templateBodyContext templateBody() {
		    return (templateBodyContext)getRuleContext(templateBodyContext.class,0);
		}
		public Token BigStringTemplate_END() { return getToken(TemplateParser.BigStringTemplate_END, 0); }
		public Token BIGSTRING() { return getToken(TemplateParser.BIGSTRING, 0); }
		public bigstringTemplateContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).bigstringTemplateEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).bigstringTemplateExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).bigstringTemplateVisit(this);
		    else return null;
		}
	}

	public final bigstringTemplateContext bigstringTemplate() throws RecognitionException {
		bigstringTemplateContext _localctx = new bigstringTemplateContext(_ctx, 32);
		enterRule(_localctx, RULE_bigstringTemplate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303); match(BIGSTRING);
			setState(305); templateBody();
			setState(307); match(BigStringTemplate_END);
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

	public static class bigstringTemplateNoNewlineContext extends ParserRuleContext<Token> {
		public Token BIGSTRINGLINE() { return getToken(TemplateParser.BIGSTRINGLINE, 0); }
		public templateBodyContext templateBody() {
		    return (templateBodyContext)getRuleContext(templateBodyContext.class,0);
		}
		public Token BigStringLineTemplate_END() { return getToken(TemplateParser.BigStringLineTemplate_END, 0); }
		public bigstringTemplateNoNewlineContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).bigstringTemplateNoNewlineEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).bigstringTemplateNoNewlineExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).bigstringTemplateNoNewlineVisit(this);
		    else return null;
		}
	}

	public final bigstringTemplateNoNewlineContext bigstringTemplateNoNewline() throws RecognitionException {
		bigstringTemplateNoNewlineContext _localctx = new bigstringTemplateNoNewlineContext(_ctx, 34);
		enterRule(_localctx, RULE_bigstringTemplateNoNewline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309); match(BIGSTRINGLINE);
			setState(311); templateBody();
			setState(313); match(BigStringLineTemplate_END);
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

	public static class anonymousTemplateContext extends ParserRuleContext<Token> {
		public Token RBRACE() { return getToken(TemplateParser.RBRACE, 0); }
		public anonymousTemplateParametersContext anonymousTemplateParameters() {
		    return (anonymousTemplateParametersContext)getRuleContext(anonymousTemplateParametersContext.class,0);
		}
		public templateBodyContext templateBody() {
		    return (templateBodyContext)getRuleContext(templateBodyContext.class,0);
		}
		public Token LBRACE() { return getToken(TemplateParser.LBRACE, 0); }
		public anonymousTemplateContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).anonymousTemplateEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).anonymousTemplateExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).anonymousTemplateVisit(this);
		    else return null;
		}
	}

	public final anonymousTemplateContext anonymousTemplate() throws RecognitionException {
		anonymousTemplateContext _localctx = new anonymousTemplateContext(_ctx, 36);
		enterRule(_localctx, RULE_anonymousTemplate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315); match(LBRACE);
			setState(319);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(317); anonymousTemplateParameters();
					}
					break;
			}
			setState(321); templateBody();
			setState(323); match(RBRACE);
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

	public static class anonymousTemplateParametersContext extends ParserRuleContext<Token> {
		public Token TEMPLATE_PARAMETER;
		public List<Token> names = new ArrayList<Token>();
		public Token TEMPLATE_PARAMETER(int i) {
		    return getToken(TemplateParser.TEMPLATE_PARAMETER, i);
		}
		public Token PIPE() { return getToken(TemplateParser.PIPE, 0); }
		public List<? extends Token> TEMPLATE_PARAMETER() { return getTokens(TemplateParser.TEMPLATE_PARAMETER); }
		public Token COMMA() { return getToken(TemplateParser.COMMA, 0); }
		public anonymousTemplateParametersContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).anonymousTemplateParametersEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).anonymousTemplateParametersExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).anonymousTemplateParametersVisit(this);
		    else return null;
		}
	}

	public final anonymousTemplateParametersContext anonymousTemplateParameters() throws RecognitionException {
		anonymousTemplateParametersContext _localctx = new anonymousTemplateParametersContext(_ctx, 38);
		enterRule(_localctx, RULE_anonymousTemplateParameters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325); ((anonymousTemplateParametersContext)_localctx).TEMPLATE_PARAMETER = match(TEMPLATE_PARAMETER);
			((anonymousTemplateParametersContext)_localctx).names.add(((anonymousTemplateParametersContext)_localctx).TEMPLATE_PARAMETER);
			setState(333);
			_errHandler.sync(this);
			int _alt423 = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt423!=2 && _alt423!=-1 ) {
				if ( _alt423==1 ) {
					{
					{
					setState(327); match(COMMA);
					setState(329); ((anonymousTemplateParametersContext)_localctx).TEMPLATE_PARAMETER = match(TEMPLATE_PARAMETER);
					((anonymousTemplateParametersContext)_localctx).names.add(((anonymousTemplateParametersContext)_localctx).TEMPLATE_PARAMETER);
					}
					} 
				}
				setState(335);
				_errHandler.sync(this);
				_alt423 = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			setState(336); match(PIPE);
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

	public static class templateBodyContext extends ParserRuleContext<Token> {
		public exprTagContext exprTag() {
		    return (exprTagContext)getRuleContext(exprTagContext.class,0);
		}
		public regionContext region() {
		    return (regionContext)getRuleContext(regionContext.class,0);
		}
		public Token NEWLINE() { return getToken(TemplateParser.NEWLINE, 0); }
		public Token TEXT() { return getToken(TemplateParser.TEXT, 0); }
		public Token COMMENT() { return getToken(TemplateParser.COMMENT, 0); }
		public ifstatContext ifstat() {
		    return (ifstatContext)getRuleContext(ifstatContext.class,0);
		}
		public escapeContext escape() {
		    return (escapeContext)getRuleContext(escapeContext.class,0);
		}
		public templateBodyContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).templateBodyEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).templateBodyExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).templateBodyVisit(this);
		    else return null;
		}
	}

	public final templateBodyContext templateBody() throws RecognitionException {
		templateBodyContext _localctx = new templateBodyContext(_ctx, 40);
		enterRule(_localctx, RULE_templateBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			_errHandler.sync(this);
			int _alt462 = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt462!=2 && _alt462!=-1 ) {
				if ( _alt462==1 ) {
					{
					setState(352);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
						case 1:
							{
							setState(338); match(NEWLINE);
							}
							break;

						case 2:
							{
							setState(340); match(COMMENT);
							}
							break;

						case 3:
							{
							setState(342); ifstat();
							}
							break;

						case 4:
							{
							setState(344); region();
							}
							break;

						case 5:
							{
							setState(346); exprTag();
							}
							break;

						case 6:
							{
							setState(348); escape();
							}
							break;

						case 7:
							{
							setState(350); match(TEXT);
							}
							break;
					}
					} 
				}
				setState(356);
				_errHandler.sync(this);
				_alt462 = getInterpreter().adaptivePredict(_input,23,_ctx);
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

	public static class escapeContext extends ParserRuleContext<Token> {
		public Token CLOSE_DELIMITER() { return getToken(TemplateParser.CLOSE_DELIMITER, 0); }
		public Token ESCAPE() { return getToken(TemplateParser.ESCAPE, 0); }
		public Token OPEN_DELIMITER() { return getToken(TemplateParser.OPEN_DELIMITER, 0); }
		public escapeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).escapeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).escapeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).escapeVisit(this);
		    else return null;
		}
	}

	public final escapeContext escape() throws RecognitionException {
		escapeContext _localctx = new escapeContext(_ctx, 42);
		enterRule(_localctx, RULE_escape);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357); match(OPEN_DELIMITER);
			setState(359); match(ESCAPE);
			setState(361); match(CLOSE_DELIMITER);
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

	public static class exprTagContext extends ParserRuleContext<Token> {
		public Token CLOSE_DELIMITER() { return getToken(TemplateParser.CLOSE_DELIMITER, 0); }
		public exprOptionsContext exprOptions() {
		    return (exprOptionsContext)getRuleContext(exprOptionsContext.class,0);
		}
		public Token SEMI() { return getToken(TemplateParser.SEMI, 0); }
		public exprContext expr() {
		    return (exprContext)getRuleContext(exprContext.class,0);
		}
		public Token OPEN_DELIMITER() { return getToken(TemplateParser.OPEN_DELIMITER, 0); }
		public exprTagContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exprTagEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exprTagExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).exprTagVisit(this);
		    else return null;
		}
	}

	public final exprTagContext exprTag() throws RecognitionException {
		exprTagContext _localctx = new exprTagContext(_ctx, 44);
		enterRule(_localctx, RULE_exprTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363); match(OPEN_DELIMITER);
			setState(365); expr();
			setState(371);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(367); match(SEMI);
					setState(369); exprOptions();
					}
					break;
			}
			setState(373); match(CLOSE_DELIMITER);
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

	public static class regionContext extends ParserRuleContext<Token> {
		public Token REGION_ID() { return getToken(TemplateParser.REGION_ID, 0); }
		public List<? extends Token> CLOSE_DELIMITER() { return getTokens(TemplateParser.CLOSE_DELIMITER); }
		public Token OPEN_DELIMITER(int i) {
		    return getToken(TemplateParser.OPEN_DELIMITER, i);
		}
		public Token REGION_END() { return getToken(TemplateParser.REGION_END, 0); }
		public List<? extends Token> OPEN_DELIMITER() { return getTokens(TemplateParser.OPEN_DELIMITER); }
		public templateBodyContext templateBody() {
		    return (templateBodyContext)getRuleContext(templateBodyContext.class,0);
		}
		public Token CLOSE_DELIMITER(int i) {
		    return getToken(TemplateParser.CLOSE_DELIMITER, i);
		}
		public regionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).regionEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).regionExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).regionVisit(this);
		    else return null;
		}
	}

	public final regionContext region() throws RecognitionException {
		regionContext _localctx = new regionContext(_ctx, 46);
		enterRule(_localctx, RULE_region);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375); match(OPEN_DELIMITER);
			setState(377); match(REGION_ID);
			setState(379); match(CLOSE_DELIMITER);
			setState(381); templateBody();
			setState(383); match(OPEN_DELIMITER);
			setState(385); match(REGION_END);
			setState(387); match(CLOSE_DELIMITER);
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

	public static class subtemplateContext extends ParserRuleContext<Token> {
		public anonymousTemplateContext anonymousTemplate() {
		    return (anonymousTemplateContext)getRuleContext(anonymousTemplateContext.class,0);
		}
		public subtemplateContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).subtemplateEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).subtemplateExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).subtemplateVisit(this);
		    else return null;
		}
	}

	public final subtemplateContext subtemplate() throws RecognitionException {
		subtemplateContext _localctx = new subtemplateContext(_ctx, 48);
		enterRule(_localctx, RULE_subtemplate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(389); anonymousTemplate();
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

	public static class ifstatContext extends ParserRuleContext<Token> {
		public List<? extends Token> RPAREN() { return getTokens(TemplateParser.RPAREN); }
		public Token RPAREN(int i) {
		    return getToken(TemplateParser.RPAREN, i);
		}
		public Token OPEN_DELIMITER(int i) {
		    return getToken(TemplateParser.OPEN_DELIMITER, i);
		}
		public List<? extends Token> OPEN_DELIMITER() { return getTokens(TemplateParser.OPEN_DELIMITER); }
		public Token CLOSE_DELIMITER(int i) {
		    return getToken(TemplateParser.CLOSE_DELIMITER, i);
		}
		public Token ELSE() { return getToken(TemplateParser.ELSE, 0); }
		public Token ENDIF() { return getToken(TemplateParser.ENDIF, 0); }
		public templateBodyContext templateBody(int i) {
		    return (templateBodyContext)getRuleContext(templateBodyContext.class,i);
		}
		public List<? extends Token> CLOSE_DELIMITER() { return getTokens(TemplateParser.CLOSE_DELIMITER); }
		public Token LPAREN(int i) {
		    return getToken(TemplateParser.LPAREN, i);
		}
		public conditionalContext conditional(int i) {
		    return (conditionalContext)getRuleContext(conditionalContext.class,i);
		}
		public List<? extends conditionalContext> conditional() {
		    return (List<conditionalContext>)getRuleContexts(conditionalContext.class);
		}
		public List<? extends templateBodyContext> templateBody() {
		    return (List<templateBodyContext>)getRuleContexts(templateBodyContext.class);
		}
		public List<? extends Token> LPAREN() { return getTokens(TemplateParser.LPAREN); }
		public Token IF() { return getToken(TemplateParser.IF, 0); }
		public Token ELSEIF() { return getToken(TemplateParser.ELSEIF, 0); }
		public ifstatContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).ifstatEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).ifstatExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).ifstatVisit(this);
		    else return null;
		}
	}

	public final ifstatContext ifstat() throws RecognitionException {
		ifstatContext _localctx = new ifstatContext(_ctx, 50);
		enterRule(_localctx, RULE_ifstat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391); match(OPEN_DELIMITER);
			setState(393); match(IF);
			setState(395); match(LPAREN);
			setState(397); conditional();
			setState(399); match(RPAREN);
			setState(401); match(CLOSE_DELIMITER);
			setState(403); templateBody();
			setState(421);
			_errHandler.sync(this);
			int _alt558 = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt558!=2 && _alt558!=-1 ) {
				if ( _alt558==1 ) {
					{
					{
					setState(405); match(OPEN_DELIMITER);
					setState(407); match(ELSEIF);
					setState(409); match(LPAREN);
					setState(411); conditional();
					setState(413); match(RPAREN);
					setState(415); match(CLOSE_DELIMITER);
					setState(417); templateBody();
					}
					} 
				}
				setState(423);
				_errHandler.sync(this);
				_alt558 = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(432);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(424); match(OPEN_DELIMITER);
					setState(426); match(ELSE);
					setState(428); match(CLOSE_DELIMITER);
					setState(430); templateBody();
					}
					break;
			}
			setState(434); match(OPEN_DELIMITER);
			setState(436); match(ENDIF);
			setState(438); match(CLOSE_DELIMITER);
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

	public static class conditionalContext extends ParserRuleContext<Token> {
		public andConditionalContext andConditional(int i) {
		    return (andConditionalContext)getRuleContext(andConditionalContext.class,i);
		}
		public List<? extends andConditionalContext> andConditional() {
		    return (List<andConditionalContext>)getRuleContexts(andConditionalContext.class);
		}
		public Token OR() { return getToken(TemplateParser.OR, 0); }
		public conditionalContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).conditionalEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).conditionalExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).conditionalVisit(this);
		    else return null;
		}
	}

	public final conditionalContext conditional() throws RecognitionException {
		conditionalContext _localctx = new conditionalContext(_ctx, 52);
		enterRule(_localctx, RULE_conditional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440); andConditional();
			setState(448);
			_errHandler.sync(this);
			int _alt590 = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt590!=2 && _alt590!=-1 ) {
				if ( _alt590==1 ) {
					{
					{
					setState(442); match(OR);
					setState(444); andConditional();
					}
					} 
				}
				setState(450);
				_errHandler.sync(this);
				_alt590 = getInterpreter().adaptivePredict(_input,27,_ctx);
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

	public static class andConditionalContext extends ParserRuleContext<Token> {
		public List<? extends notConditionalContext> notConditional() {
		    return (List<notConditionalContext>)getRuleContexts(notConditionalContext.class);
		}
		public notConditionalContext notConditional(int i) {
		    return (notConditionalContext)getRuleContext(notConditionalContext.class,i);
		}
		public Token AND() { return getToken(TemplateParser.AND, 0); }
		public andConditionalContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).andConditionalEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).andConditionalExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).andConditionalVisit(this);
		    else return null;
		}
	}

	public final andConditionalContext andConditional() throws RecognitionException {
		andConditionalContext _localctx = new andConditionalContext(_ctx, 54);
		enterRule(_localctx, RULE_andConditional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451); notConditional();
			setState(459);
			_errHandler.sync(this);
			int _alt605 = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt605!=2 && _alt605!=-1 ) {
				if ( _alt605==1 ) {
					{
					{
					setState(453); match(AND);
					setState(455); notConditional();
					}
					} 
				}
				setState(461);
				_errHandler.sync(this);
				_alt605 = getInterpreter().adaptivePredict(_input,28,_ctx);
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

	public static class notConditionalContext extends ParserRuleContext<Token> {
		public memberExprContext memberExpr() {
		    return (memberExprContext)getRuleContext(memberExprContext.class,0);
		}
		public notConditionalContext notConditional() {
		    return (notConditionalContext)getRuleContext(notConditionalContext.class,0);
		}
		public Token NOT() { return getToken(TemplateParser.NOT, 0); }
		public notConditionalContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).notConditionalEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).notConditionalExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).notConditionalVisit(this);
		    else return null;
		}
	}

	public final notConditionalContext notConditional() throws RecognitionException {
		notConditionalContext _localctx = new notConditionalContext(_ctx, 56);
		enterRule(_localctx, RULE_notConditional);
		try {
			setState(468);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(462); match(NOT);
					setState(464); notConditional();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(466); memberExpr();
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

	public static class exprOptionsContext extends ParserRuleContext<Token> {
		public optionContext option;
		public List<optionContext> options_ = new ArrayList<optionContext>();
		public optionContext option(int i) {
		    return (optionContext)getRuleContext(optionContext.class,i);
		}
		public Token COMMA() { return getToken(TemplateParser.COMMA, 0); }
		public List<? extends optionContext> option() {
		    return (List<optionContext>)getRuleContexts(optionContext.class);
		}
		public exprOptionsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exprOptionsEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exprOptionsExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).exprOptionsVisit(this);
		    else return null;
		}
	}

	public final exprOptionsContext exprOptions() throws RecognitionException {
		exprOptionsContext _localctx = new exprOptionsContext(_ctx, 58);
		enterRule(_localctx, RULE_exprOptions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(470); ((exprOptionsContext)_localctx).option = option();
			((exprOptionsContext)_localctx).options_.add(((exprOptionsContext)_localctx).option);
			setState(478);
			_errHandler.sync(this);
			int _alt638 = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt638!=2 && _alt638!=-1 ) {
				if ( _alt638==1 ) {
					{
					{
					setState(472); match(COMMA);
					setState(474); ((exprOptionsContext)_localctx).option = option();
					((exprOptionsContext)_localctx).options_.add(((exprOptionsContext)_localctx).option);
					}
					} 
				}
				setState(480);
				_errHandler.sync(this);
				_alt638 = getInterpreter().adaptivePredict(_input,30,_ctx);
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

	public static class optionContext extends ParserRuleContext<Token> {
		public Token name;
		public exprNoCommaContext value;
		public Token EQUALS() { return getToken(TemplateParser.EQUALS, 0); }
		public Token ID() { return getToken(TemplateParser.ID, 0); }
		public exprNoCommaContext exprNoComma() {
		    return (exprNoCommaContext)getRuleContext(exprNoCommaContext.class,0);
		}
		public optionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).optionEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).optionExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).optionVisit(this);
		    else return null;
		}
	}

	public final optionContext option() throws RecognitionException {
		optionContext _localctx = new optionContext(_ctx, 60);
		enterRule(_localctx, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(481); ((optionContext)_localctx).name = match(ID);
			setState(487);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(483); match(EQUALS);
					setState(485); ((optionContext)_localctx).value = exprNoComma();
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

	public static class exprNoCommaContext extends ParserRuleContext<Token> {
		public Token COLON() { return getToken(TemplateParser.COLON, 0); }
		public memberExprContext memberExpr() {
		    return (memberExprContext)getRuleContext(memberExprContext.class,0);
		}
		public mapTemplateRefContext mapTemplateRef() {
		    return (mapTemplateRefContext)getRuleContext(mapTemplateRefContext.class,0);
		}
		public exprNoCommaContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exprNoCommaEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exprNoCommaExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).exprNoCommaVisit(this);
		    else return null;
		}
	}

	public final exprNoCommaContext exprNoComma() throws RecognitionException {
		exprNoCommaContext _localctx = new exprNoCommaContext(_ctx, 62);
		enterRule(_localctx, RULE_exprNoComma);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(489); memberExpr();
			setState(495);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
				case 1:
					{
					setState(491); match(COLON);
					setState(493); mapTemplateRef();
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

	public static class exprContext extends ParserRuleContext<Token> {
		public mapExprContext mapExpr() {
		    return (mapExprContext)getRuleContext(mapExprContext.class,0);
		}
		public exprContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).exprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).exprVisit(this);
		    else return null;
		}
	}

	public final exprContext expr() throws RecognitionException {
		exprContext _localctx = new exprContext(_ctx, 64);
		enterRule(_localctx, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(497); mapExpr();
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

	public static class mapExprContext extends ParserRuleContext<Token> {
		public List<? extends Token> COLON() { return getTokens(TemplateParser.COLON); }
		public List<? extends memberExprContext> memberExpr() {
		    return (List<memberExprContext>)getRuleContexts(memberExprContext.class);
		}
		public Token COMMA(int i) {
		    return getToken(TemplateParser.COMMA, i);
		}
		public List<? extends mapTemplateRefContext> mapTemplateRef() {
		    return (List<mapTemplateRefContext>)getRuleContexts(mapTemplateRefContext.class);
		}
		public List<? extends Token> COMMA() { return getTokens(TemplateParser.COMMA); }
		public mapTemplateRefContext mapTemplateRef(int i) {
		    return (mapTemplateRefContext)getRuleContext(mapTemplateRefContext.class,i);
		}
		public memberExprContext memberExpr(int i) {
		    return (memberExprContext)getRuleContext(memberExprContext.class,i);
		}
		public Token COLON(int i) {
		    return getToken(TemplateParser.COLON, i);
		}
		public mapExprContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).mapExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).mapExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).mapExprVisit(this);
		    else return null;
		}
	}

	public final mapExprContext mapExpr() throws RecognitionException {
		mapExprContext _localctx = new mapExprContext(_ctx, 66);
		enterRule(_localctx, RULE_mapExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(499); memberExpr();
			setState(513);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
				case 1:
					{
					setState(505); 
					_errHandler.sync(this);
					int _alt698 = getInterpreter().adaptivePredict(_input,33,_ctx);
					do {
						switch ( _alt698 ) {
							case 1:
								{
								{
								setState(501); match(COMMA);
								setState(503); memberExpr();
								}
								}
								break;
							default :
								throw new NoViableAltException(this);
						}
						setState(507); 
						_errHandler.sync(this);
						_alt698 = getInterpreter().adaptivePredict(_input,33,_ctx);
					} while ( _alt698!=2 && _alt698!=-1 );
					setState(509); match(COLON);
					setState(511); mapTemplateRef();
					}
					break;
			}
			setState(530);
			_errHandler.sync(this);
			int _alt718 = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt718!=2 && _alt718!=-1 ) {
				if ( _alt718==1 ) {
					{
					{
					setState(515); match(COLON);
					setState(517); mapTemplateRef();
					setState(525);
					_errHandler.sync(this);
					int _alt716 = getInterpreter().adaptivePredict(_input,35,_ctx);
					while ( _alt716!=2 && _alt716!=-1 ) {
						if ( _alt716==1 ) {
							{
							{
							setState(519); match(COMMA);
							setState(521); mapTemplateRef();
							}
							} 
						}
						setState(527);
						_errHandler.sync(this);
						_alt716 = getInterpreter().adaptivePredict(_input,35,_ctx);
					}
					}
					} 
				}
				setState(532);
				_errHandler.sync(this);
				_alt718 = getInterpreter().adaptivePredict(_input,36,_ctx);
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

	public static class mapTemplateRefContext extends ParserRuleContext<Token> {
		public argExprListContext argExprList() {
		    return (argExprListContext)getRuleContext(argExprListContext.class,0);
		}
		public subtemplateContext subtemplate() {
		    return (subtemplateContext)getRuleContext(subtemplateContext.class,0);
		}
		public List<? extends Token> RPAREN() { return getTokens(TemplateParser.RPAREN); }
		public Token RPAREN(int i) {
		    return getToken(TemplateParser.RPAREN, i);
		}
		public argumentsContext arguments() {
		    return (argumentsContext)getRuleContext(argumentsContext.class,0);
		}
		public Token LPAREN(int i) {
		    return getToken(TemplateParser.LPAREN, i);
		}
		public Token ID() { return getToken(TemplateParser.ID, 0); }
		public mapExprContext mapExpr() {
		    return (mapExprContext)getRuleContext(mapExprContext.class,0);
		}
		public List<? extends Token> LPAREN() { return getTokens(TemplateParser.LPAREN); }
		public mapTemplateRefContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).mapTemplateRefEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).mapTemplateRefExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).mapTemplateRefVisit(this);
		    else return null;
		}
	}

	public final mapTemplateRefContext mapTemplateRef() throws RecognitionException {
		mapTemplateRefContext _localctx = new mapTemplateRefContext(_ctx, 68);
		enterRule(_localctx, RULE_mapTemplateRef);
		try {
			setState(557);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(533); match(ID);
					setState(535); match(LPAREN);
					setState(537); arguments();
					setState(539); match(RPAREN);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(541); subtemplate();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(543); match(LPAREN);
					setState(545); mapExpr();
					setState(547); match(RPAREN);
					setState(549); match(LPAREN);
					setState(553);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
						case 1:
							{
							setState(551); argExprList();
							}
							break;
					}
					setState(555); match(RPAREN);
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

	public static class memberExprContext extends ParserRuleContext<Token> {
		public Token RPAREN() { return getToken(TemplateParser.RPAREN, 0); }
		public Token DOT(int i) {
		    return getToken(TemplateParser.DOT, i);
		}
		public includeExprContext includeExpr() {
		    return (includeExprContext)getRuleContext(includeExprContext.class,0);
		}
		public Token ID() { return getToken(TemplateParser.ID, 0); }
		public List<? extends Token> DOT() { return getTokens(TemplateParser.DOT); }
		public mapExprContext mapExpr() {
		    return (mapExprContext)getRuleContext(mapExprContext.class,0);
		}
		public Token LPAREN() { return getToken(TemplateParser.LPAREN, 0); }
		public memberExprContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).memberExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).memberExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).memberExprVisit(this);
		    else return null;
		}
	}

	public final memberExprContext memberExpr() throws RecognitionException {
		memberExprContext _localctx = new memberExprContext(_ctx, 70);
		enterRule(_localctx, RULE_memberExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(559); includeExpr();
			setState(575);
			_errHandler.sync(this);
			int _alt778 = getInterpreter().adaptivePredict(_input,40,_ctx);
			while ( _alt778!=2 && _alt778!=-1 ) {
				if ( _alt778==1 ) {
					{
					setState(573);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
						case 1:
							{
							setState(561); match(DOT);
							setState(563); match(ID);
							}
							break;

						case 2:
							{
							setState(565); match(DOT);
							setState(567); match(LPAREN);
							setState(569); mapExpr();
							setState(571); match(RPAREN);
							}
							break;
					}
					} 
				}
				setState(577);
				_errHandler.sync(this);
				_alt778 = getInterpreter().adaptivePredict(_input,40,_ctx);
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

	public static class includeExprContext extends ParserRuleContext<Token> {
		public Token super_;
		public Token templateName;
		public argumentsContext args;
		public Token at;
		public Token regionName;
		public Token REGION_ID() { return getToken(TemplateParser.REGION_ID, 0); }
		public Token AT() { return getToken(TemplateParser.AT, 0); }
		public Token RPAREN() { return getToken(TemplateParser.RPAREN, 0); }
		public argumentsContext arguments() {
		    return (argumentsContext)getRuleContext(argumentsContext.class,0);
		}
		public Token SUPER() { return getToken(TemplateParser.SUPER, 0); }
		public primaryContext primary() {
		    return (primaryContext)getRuleContext(primaryContext.class,0);
		}
		public Token ID() { return getToken(TemplateParser.ID, 0); }
		public Token DOT() { return getToken(TemplateParser.DOT, 0); }
		public Token LPAREN() { return getToken(TemplateParser.LPAREN, 0); }
		public includeExprContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).includeExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).includeExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).includeExprVisit(this);
		    else return null;
		}
	}

	public final includeExprContext includeExpr() throws RecognitionException {
		includeExprContext _localctx = new includeExprContext(_ctx, 72);
		enterRule(_localctx, RULE_includeExpr);
		try {
			setState(618);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(578); ((includeExprContext)_localctx).super_ = match(SUPER);
					setState(580); match(DOT);
					setState(582); ((includeExprContext)_localctx).templateName = match(ID);
					setState(584); match(LPAREN);
					setState(586); ((includeExprContext)_localctx).args = arguments();
					setState(588); match(RPAREN);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(590); ((includeExprContext)_localctx).templateName = match(ID);
					setState(592); match(LPAREN);
					setState(594); ((includeExprContext)_localctx).args = arguments();
					setState(596); match(RPAREN);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(598); ((includeExprContext)_localctx).at = match(AT);
					setState(600); ((includeExprContext)_localctx).super_ = match(SUPER);
					setState(602); match(DOT);
					setState(604); ((includeExprContext)_localctx).templateName = match(ID);
					setState(606); match(LPAREN);
					setState(608); match(RPAREN);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(610); ((includeExprContext)_localctx).regionName = match(REGION_ID);
					setState(612); match(LPAREN);
					setState(614); match(RPAREN);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(616); primary();
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

	public static class primaryContext extends ParserRuleContext<Token> {
		public Token id;
		public List<? extends Token> RPAREN() { return getTokens(TemplateParser.RPAREN); }
		public Token RPAREN(int i) {
		    return getToken(TemplateParser.RPAREN, i);
		}
		public listContext list() {
		    return (listContext)getRuleContext(listContext.class,0);
		}
		public exprContext expr() {
		    return (exprContext)getRuleContext(exprContext.class,0);
		}
		public argExprListContext argExprList() {
		    return (argExprListContext)getRuleContext(argExprListContext.class,0);
		}
		public subtemplateContext subtemplate() {
		    return (subtemplateContext)getRuleContext(subtemplateContext.class,0);
		}
		public Token LPAREN(int i) {
		    return getToken(TemplateParser.LPAREN, i);
		}
		public Token ID() { return getToken(TemplateParser.ID, 0); }
		public Token FALSE() { return getToken(TemplateParser.FALSE, 0); }
		public conditionalContext conditional() {
		    return (conditionalContext)getRuleContext(conditionalContext.class,0);
		}
		public Token TRUE() { return getToken(TemplateParser.TRUE, 0); }
		public List<? extends Token> LPAREN() { return getTokens(TemplateParser.LPAREN); }
		public Token STRING() { return getToken(TemplateParser.STRING, 0); }
		public primaryContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).primaryEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).primaryExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).primaryVisit(this);
		    else return null;
		}
	}

	public final primaryContext primary() throws RecognitionException {
		primaryContext _localctx = new primaryContext(_ctx, 74);
		enterRule(_localctx, RULE_primary);
		try {
			setState(654);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(620); ((primaryContext)_localctx).id = match(ID);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(622); match(STRING);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(624); match(TRUE);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(626); match(FALSE);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(628); subtemplate();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(630); list();
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(632); match(LPAREN);
					setState(634); conditional();
					setState(636); match(RPAREN);
					}
					break;

				case 8:
					enterOuterAlt(_localctx, 8);
					{
					setState(638); match(LPAREN);
					setState(640); expr();
					setState(642); match(RPAREN);
					setState(652);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
						case 1:
							{
							setState(644); match(LPAREN);
							setState(648);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
								case 1:
									{
									setState(646); argExprList();
									}
									break;
							}
							setState(650); match(RPAREN);
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

	public static class argumentsContext extends ParserRuleContext<Token> {
		public Token ELLIPSIS() { return getToken(TemplateParser.ELLIPSIS, 0); }
		public argExprListContext argExprList() {
		    return (argExprListContext)getRuleContext(argExprListContext.class,0);
		}
		public Token COMMA(int i) {
		    return getToken(TemplateParser.COMMA, i);
		}
		public namedArgContext namedArg(int i) {
		    return (namedArgContext)getRuleContext(namedArgContext.class,i);
		}
		public List<? extends Token> COMMA() { return getTokens(TemplateParser.COMMA); }
		public List<? extends namedArgContext> namedArg() {
		    return (List<namedArgContext>)getRuleContexts(namedArgContext.class);
		}
		public argumentsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).argumentsEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).argumentsExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).argumentsVisit(this);
		    else return null;
		}
	}

	public final argumentsContext arguments() throws RecognitionException {
		argumentsContext _localctx = new argumentsContext(_ctx, 76);
		enterRule(_localctx, RULE_arguments);
		try {
			setState(679);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(656); argExprList();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(658); namedArg();
					setState(666);
					_errHandler.sync(this);
					int _alt925 = getInterpreter().adaptivePredict(_input,45,_ctx);
					while ( _alt925!=2 && _alt925!=-1 ) {
						if ( _alt925==1 ) {
							{
							{
							setState(660); match(COMMA);
							setState(662); namedArg();
							}
							} 
						}
						setState(668);
						_errHandler.sync(this);
						_alt925 = getInterpreter().adaptivePredict(_input,45,_ctx);
					}
					setState(673);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
						case 1:
							{
							setState(669); match(COMMA);
							setState(671); match(ELLIPSIS);
							}
							break;
					}
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(675); match(ELLIPSIS);
					}
					break;

				case 4:
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

	public static class argExprListContext extends ParserRuleContext<Token> {
		public List<? extends argContext> arg() {
		    return (List<argContext>)getRuleContexts(argContext.class);
		}
		public argContext arg(int i) {
		    return (argContext)getRuleContext(argContext.class,i);
		}
		public Token COMMA() { return getToken(TemplateParser.COMMA, 0); }
		public argExprListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).argExprListEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).argExprListExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).argExprListVisit(this);
		    else return null;
		}
	}

	public final argExprListContext argExprList() throws RecognitionException {
		argExprListContext _localctx = new argExprListContext(_ctx, 78);
		enterRule(_localctx, RULE_argExprList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(681); arg();
			setState(689);
			_errHandler.sync(this);
			int _alt955 = getInterpreter().adaptivePredict(_input,48,_ctx);
			while ( _alt955!=2 && _alt955!=-1 ) {
				if ( _alt955==1 ) {
					{
					{
					setState(683); match(COMMA);
					setState(685); arg();
					}
					} 
				}
				setState(691);
				_errHandler.sync(this);
				_alt955 = getInterpreter().adaptivePredict(_input,48,_ctx);
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

	public static class argContext extends ParserRuleContext<Token> {
		public exprNoCommaContext exprNoComma() {
		    return (exprNoCommaContext)getRuleContext(exprNoCommaContext.class,0);
		}
		public argContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).argEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).argExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).argVisit(this);
		    else return null;
		}
	}

	public final argContext arg() throws RecognitionException {
		argContext _localctx = new argContext(_ctx, 80);
		enterRule(_localctx, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(692); exprNoComma();
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

	public static class namedArgContext extends ParserRuleContext<Token> {
		public Token name;
		public argContext value;
		public argContext arg() {
		    return (argContext)getRuleContext(argContext.class,0);
		}
		public Token EQUALS() { return getToken(TemplateParser.EQUALS, 0); }
		public Token ID() { return getToken(TemplateParser.ID, 0); }
		public namedArgContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).namedArgEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).namedArgExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).namedArgVisit(this);
		    else return null;
		}
	}

	public final namedArgContext namedArg() throws RecognitionException {
		namedArgContext _localctx = new namedArgContext(_ctx, 82);
		enterRule(_localctx, RULE_namedArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(694); ((namedArgContext)_localctx).name = match(ID);
			setState(696); match(EQUALS);
			setState(698); ((namedArgContext)_localctx).value = arg();
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

	public static class listContext extends ParserRuleContext<Token> {
		public listElementContext listElement;
		public List<listElementContext> elements = new ArrayList<listElementContext>();
		public Token RBRACK() { return getToken(TemplateParser.RBRACK, 0); }
		public listElementContext listElement(int i) {
		    return (listElementContext)getRuleContext(listElementContext.class,i);
		}
		public Token LBRACK() { return getToken(TemplateParser.LBRACK, 0); }
		public Token COMMA() { return getToken(TemplateParser.COMMA, 0); }
		public List<? extends listElementContext> listElement() {
		    return (List<listElementContext>)getRuleContexts(listElementContext.class);
		}
		public listContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).listEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).listExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).listVisit(this);
		    else return null;
		}
	}

	public final listContext list() throws RecognitionException {
		listContext _localctx = new listContext(_ctx, 84);
		enterRule(_localctx, RULE_list);
		try {
			setState(719);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(700); match(LBRACK);
					setState(702); match(RBRACK);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(704); match(LBRACK);
					setState(706); ((listContext)_localctx).listElement = listElement();
					((listContext)_localctx).elements.add(((listContext)_localctx).listElement);
					setState(714);
					_errHandler.sync(this);
					int _alt1006 = getInterpreter().adaptivePredict(_input,49,_ctx);
					while ( _alt1006!=2 && _alt1006!=-1 ) {
						if ( _alt1006==1 ) {
							{
							{
							setState(708); match(COMMA);
							setState(710); ((listContext)_localctx).listElement = listElement();
							((listContext)_localctx).elements.add(((listContext)_localctx).listElement);
							}
							} 
						}
						setState(716);
						_errHandler.sync(this);
						_alt1006 = getInterpreter().adaptivePredict(_input,49,_ctx);
					}
					setState(717); match(RBRACK);
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

	public static class listElementContext extends ParserRuleContext<Token> {
		public exprNoCommaContext exprNoComma() {
		    return (exprNoCommaContext)getRuleContext(exprNoCommaContext.class,0);
		}
		public listElementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).listElementEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof TemplateParserListener ) ((TemplateParserListener)listener).listElementExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof TemplateParserVisitor ) return ((TemplateParserVisitor<T>)visitor).listElementVisit(this);
		    else return null;
		}
	}

	public final listElementContext listElement() throws RecognitionException {
		listElementContext _localctx = new listElementContext(_ctx, 86);
		enterRule(_localctx, RULE_listElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(723);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
				case 1:
					{
					setState(721); exprNoComma();
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

	@Override
	public String[] getTokenNames() { return tokenNames; }
	@Override
	public String[] getRuleNames() { return ruleNames; }
	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\1t\u02d6\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7"+
		"\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16\7\16\2\17"+
		"\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25\7\25\2\26"+
		"\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34\7\34\2\35"+
		"\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7%\2&\7&"+
		"\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\1\0\0\3\0\b\0\1\0\0\3\0\b\0\1\0\0\1\0"+
		"\0\5\0\b\0\n\0\f\0h\t\0\1\0\0\4\0\b\0\13\0\f\0m\1\1\0\1\1\0\1\1\0\1\1"+
		"\0\3\1\b\1\1\1\0\1\1\0\1\1\0\1\1\0\5\1\b\1\n\1\f\1\u0085\t\1\3\1\b\1\1"+
		"\1\1\1\1\2\0\1\2\0\1\2\0\5\2\b\2\n\2\f\2\u0094\t\2\1\3\0\1\3\0\1\3\0\1"+
		"\3\1\3\1\4\0\1\4\0\3\4\b\4\1\5\0\1\5\0\1\5\0\1\5\0\1\5\0\1\5\0\1\5\0\1"+
		"\5\0\1\5\0\1\5\1\5\3\5\b\5\1\5\0\1\5\0\1\5\0\1\5\0\3\5\b\5\1\5\0\1\5\0"+
		"\1\5\0\3\5\b\5\1\6\0\1\6\0\1\6\0\5\6\b\6\n\6\f\6\u00d5\t\6\1\6\0\3\6\b"+
		"\6\1\7\0\1\7\0\1\7\0\1\7\0\1\7\0\1\7\0\3\7\b\7\3\7\b\7\1\b\0\1\b\0\1\b"+
		"\1\b\1\t\0\1\t\0\1\t\1\t\1\n\0\1\n\0\1\n\0\5\n\b\n\n\n\f\n\u0100\t\n\1"+
		"\n\0\1\n\0\3\n\b\n\1\n\0\3\n\b\n\1\13\0\1\13\0\1\13\1\13\1\f\0\1\f\0\1"+
		"\f\1\f\1\r\0\1\r\0\1\r\0\1\r\0\1\r\0\1\r\0\1\r\0\3\r\b\r\1\16\1\16\1\17"+
		"\0\1\17\0\1\17\1\17\1\20\0\1\20\0\1\20\1\20\1\21\0\1\21\0\1\21\1\21\1"+
		"\22\0\1\22\0\3\22\b\22\1\22\0\1\22\1\22\1\23\0\1\23\0\1\23\0\5\23\b\23"+
		"\n\23\f\23\u014f\t\23\1\23\1\23\1\24\0\1\24\0\1\24\0\1\24\0\1\24\0\1\24"+
		"\0\1\24\0\5\24\b\24\n\24\f\24\u0164\t\24\1\25\0\1\25\0\1\25\1\25\1\26"+
		"\0\1\26\0\1\26\0\1\26\0\3\26\b\26\1\26\1\26\1\27\0\1\27\0\1\27\0\1\27"+
		"\0\1\27\0\1\27\0\1\27\1\27\1\30\1\30\1\31\0\1\31\0\1\31\0\1\31\0\1\31"+
		"\0\1\31\0\1\31\0\1\31\0\1\31\0\1\31\0\1\31\0\1\31\0\1\31\0\1\31\1\31\5"+
		"\31\b\31\n\31\f\31\u01a7\t\31\1\31\0\1\31\0\1\31\0\1\31\0\3\31\b\31\1"+
		"\31\0\1\31\0\1\31\1\31\1\32\0\1\32\0\1\32\0\5\32\b\32\n\32\f\32\u01c2"+
		"\t\32\1\33\0\1\33\0\1\33\0\5\33\b\33\n\33\f\33\u01cd\t\33\1\34\0\1\34"+
		"\0\1\34\0\3\34\b\34\1\35\0\1\35\0\1\35\0\5\35\b\35\n\35\f\35\u01e0\t\35"+
		"\1\36\0\1\36\0\1\36\0\3\36\b\36\1\37\0\1\37\0\1\37\0\3\37\b\37\1 \1 \1"+
		"!\0\1!\0\1!\0\4!\b!\13!\f!\u01fb\1!\0\1!\1!\3!\b!\1!\0\1!\0\1!\0\1!\0"+
		"\5!\b!\n!\f!\u020f\t!\5!\b!\n!\f!\u0214\t!\1\"\0\1\"\0\1\"\0\1\"\1\"\1"+
		"\"\0\1\"\0\1\"\0\1\"\0\1\"\0\1\"\0\3\"\b\"\1\"\1\"\3\"\b\"\1#\0\1#\0\1"+
		"#\0\1#\0\1#\0\1#\0\1#\1#\5#\b#\n#\f#\u0241\t#\1$\0\1$\0\1$\0\1$\0\1$\0"+
		"\1$\1$\1$\0\1$\0\1$\0\1$\1$\1$\0\1$\0\1$\0\1$\0\1$\0\1$\0\1$\0\1$\0\1"+
		"$\0\1$\0\3$\b$\1%\0\1%\0\1%\0\1%\0\1%\0\1%\0\1%\0\1%\0\1%\1%\1%\0\1%\0"+
		"\1%\0\1%\0\1%\0\3%\b%\1%\0\3%\b%\3%\b%\1&\0\1&\0\1&\0\1&\0\5&\b&\n&\f"+
		"&\u029c\t&\1&\0\1&\0\3&\b&\1&\0\1&\0\3&\b&\1\'\0\1\'\0\1\'\0\5\'\b\'\n"+
		"\'\f\'\u02b3\t\'\1(\1(\1)\0\1)\0\1)\1)\1*\0\1*\0\1*\0\1*\0\1*\0\1*\0\5"+
		"*\b*\n*\f*\u02cc\t*\1*\1*\3*\b*\1+\0\3+\b+\1+,\0\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTV\0\0\u027c\0Z\1"+
		"\0\0\0\1\u02d5\5\uffff\0\0\2o\1\0\0\0\3[\1\0\0\0\4\u008a\1\0\0\0\5\u02d5"+
		"\5\uffff\0\0\6\u0095\1\0\0\0\7_\1\0\0\0\b\u00a1\1\0\0\0\tl\1\0\0\0\n\u00c9"+
		"\1\0\0\0\13\u00a2\1\0\0\0\f\u00d8\1\0\0\0\r\u00b5\1\0\0\0\16\u00da\1\0"+
		"\0\0\17\u00d3\1\0\0\0\17\u00d2\1\0\0\0\20\u00ea\1\0\0\0\21\u00a2\1\0\0"+
		"\0\22\u00f0\1\0\0\0\23\u00ef\1\0\0\0\24\u0109\1\0\0\0\25\u00f4\1\0\0\0"+
		"\26\u010b\1\0\0\0\27\u0106\1\0\0\0\27\u010a\1\0\0\0\30\u0111\1\0\0\0\31"+
		"\u00fe\1\0\0\0\31\u00fd\1\0\0\0\32\u0125\1\0\0\0\33\u0110\1\0\0\0\33\u0116"+
		"\1\0\0\0\34\u0127\1\0\0\0\35e\1\0\0\0\35\u00e7\1\0\0\0\35\u0113\1\0\0"+
		"\0\35\u0126\1\0\0\0\36\u0129\1\0\0\0\37\u00c2\1\0\0\0\37\u0128\1\0\0\0"+
		" \u012f\1\0\0\0!\u00c2\1\0\0\0!\u0126\1\0\0\0\"\u0135\1\0\0\0#\u00c2\1"+
		"\0\0\0#\u0126\1\0\0\0$\u013b\1\0\0\0%\u00e7\1\0\0\0%\u0126\1\0\0\0%\u0186"+
		"\1\0\0\0&\u0145\1\0\0\0\'\u0140\1\0\0\0(\u0162\1\0\0\0)\u012d\1\0\0\0"+
		")\u0133\1\0\0\0)\u0139\1\0\0\0)\u0143\1\0\0\0)\u017f\1\0\0\0)\u01a5\1"+
		"\0\0\0)\u01a2\1\0\0\0)\u01b1\1\0\0\0*\u0165\1\0\0\0+\u0161\1\0\0\0,\u016b"+
		"\1\0\0\0-\u0161\1\0\0\0.\u0177\1\0\0\0/\u0161\1\0\0\0\60\u0185\1\0\0\0"+
		"\61\u022e\1\0\0\0\61\u028f\1\0\0\0\62\u0187\1\0\0\0\63\u0161\1\0\0\0\64"+
		"\u01b8\1\0\0\0\65\u018f\1\0\0\0\65\u019d\1\0\0\0\65\u027c\1\0\0\0\66\u01c3"+
		"\1\0\0\0\67\u01c0\1\0\0\0\67\u01bf\1\0\0\08\u01d4\1\0\0\09\u01cb\1\0\0"+
		"\09\u01ca\1\0\0\09\u01d5\1\0\0\0:\u01d6\1\0\0\0;\u0174\1\0\0\0<\u01e1"+
		"\1\0\0\0=\u01de\1\0\0\0=\u01dd\1\0\0\0>\u01e9\1\0\0\0?\u01e8\1\0\0\0?"+
		"\u02b5\1\0\0\0?\u02d4\1\0\0\0@\u01f1\1\0\0\0A\u0173\1\0\0\0A\u0282\1\0"+
		"\0\0B\u01f3\1\0\0\0C\u01f2\1\0\0\0C\u0223\1\0\0\0C\u023b\1\0\0\0D\u022d"+
		"\1\0\0\0E\u01f0\1\0\0\0E\u0200\1\0\0\0E\u020d\1\0\0\0E\u020c\1\0\0\0F"+
		"\u022f\1\0\0\0G\u01d5\1\0\0\0G\u01ef\1\0\0\0G\u0201\1\0\0\0G\u01fa\1\0"+
		"\0\0H\u026a\1\0\0\0I\u023f\1\0\0\0J\u028e\1\0\0\0K\u026b\1\0\0\0L\u02a7"+
		"\1\0\0\0M\u021b\1\0\0\0M\u024c\1\0\0\0M\u0254\1\0\0\0N\u02a9\1\0\0\0O"+
		"\u022a\1\0\0\0O\u0289\1\0\0\0O\u02a8\1\0\0\0P\u02b4\1\0\0\0Q\u02b1\1\0"+
		"\0\0Q\u02b0\1\0\0\0Q\u02bb\1\0\0\0R\u02b6\1\0\0\0S\u029a\1\0\0\0S\u0299"+
		"\1\0\0\0T\u02cf\1\0\0\0U\u028f\1\0\0\0V\u02d3\1\0\0\0W\u02ca\1\0\0\0W"+
		"\u02c9\1\0\0\0X[\3\2\1\0ZX\1\0\0\0Z[\1\0\0\0[^\1\0\0\0\\_\3\6\3\0^\\\1"+
		"\0\0\0^_\1\0\0\0_f\1\0\0\0`b\5\24\0\0be\3\34\16\0d`\1\0\0\0eh\1\0\0\0"+
		"fd\1\0\0\0fg\1\0\0\0gk\1\0\0\0hf\1\0\0\0il\3\b\4\0ki\1\0\0\0lm\1\0\0\0"+
		"mk\1\0\0\0mn\1\0\0\0n\1\1\0\0\0oq\5\22\0\0qw\5\32\0\0su\5\n\0\0ux\5\32"+
		"\0\0ws\1\0\0\0wx\1\0\0\0x\u0086\1\0\0\0y{\5t\0\0{\u0083\5\32\0\0}\177"+
		"\5\b\0\0\177\u0082\5\32\0\0\u0081}\1\0\0\0\u0082\u0085\1\0\0\0\u0083\u0081"+
		"\1\0\0\0\u0083\u0084\1\0\0\0\u0084\u0087\1\0\0\0\u0085\u0083\1\0\0\0\u0086"+
		"y\1\0\0\0\u0086\u0087\1\0\0\0\u0087\u0088\1\0\0\0\u0088\u0089\5]\0\0\u0089"+
		"\3\1\0\0\0\u008a\u0092\5\32\0\0\u008c\u008e\5\t\0\0\u008e\u0091\5\32\0"+
		"\0\u0090\u008c\1\0\0\0\u0091\u0094\1\0\0\0\u0092\u0090\1\0\0\0\u0092\u0093"+
		"\1\0\0\0\u0093\5\1\0\0\0\u0094\u0092\1\0\0\0\u0095\u0097\5s\0\0\u0097"+
		"\u0099\5n\0\0\u0099\u009b\5\b\0\0\u009b\u009c\5r\0\0\u009c\7\1\0\0\0\u009d"+
		"\u00a2\3\n\5\0\u009f\u00a2\3\20\b\0\u00a1\u009d\1\0\0\0\u00a1\u009f\1"+
		"\0\0\0\u00a2\t\1\0\0\0\u00a3\u00a5\5\r\0\0\u00a5\u00a7\5\32\0\0\u00a7"+
		"\u00a9\5\t\0\0\u00a9\u00ab\5\32\0\0\u00ab\u00ad\5\6\0\0\u00ad\u00b8\5"+
		"\7\0\0\u00af\u00b1\5\32\0\0\u00b1\u00b3\5\6\0\0\u00b3\u00b5\3\f\6\0\u00b5"+
		"\u00b6\5\7\0\0\u00b6\u00b8\1\0\0\0\u00b7\u00a3\1\0\0\0\u00b7\u00af\1\0"+
		"\0\0\u00b8\u00b9\1\0\0\0\u00b9\u00c1\5\13\0\0\u00bb\u00c2\3\36\17\0\u00bd"+
		"\u00c2\3 \20\0\u00bf\u00c2\3\"\21\0\u00c1\u00bb\1\0\0\0\u00c1\u00bd\1"+
		"\0\0\0\u00c1\u00bf\1\0\0\0\u00c2\u00ca\1\0\0\0\u00c3\u00c5\5\32\0\0\u00c5"+
		"\u00c7\5\13\0\0\u00c7\u00ca\5\32\0\0\u00c9\u00b7\1\0\0\0\u00c9\u00c3\1"+
		"\0\0\0\u00ca\13\1\0\0\0\u00cb\u00d3\3\16\7\0\u00cd\u00cf\5\b\0\0\u00cf"+
		"\u00d2\3\16\7\0\u00d1\u00cd\1\0\0\0\u00d2\u00d5\1\0\0\0\u00d3\u00d1\1"+
		"\0\0\0\u00d3\u00d4\1\0\0\0\u00d4\u00d9\1\0\0\0\u00d5\u00d3\1\0\0\0\u00d6"+
		"\u00d9\1\0\0\0\u00d8\u00cb\1\0\0\0\u00d8\u00d6\1\0\0\0\u00d9\r\1\0\0\0"+
		"\u00da\u00e8\5\32\0\0\u00dc\u00e6\5\f\0\0\u00de\u00e7\3\34\16\0\u00e0"+
		"\u00e7\3$\22\0\u00e2\u00e7\5\25\0\0\u00e4\u00e7\5\26\0\0\u00e6\u00de\1"+
		"\0\0\0\u00e6\u00e0\1\0\0\0\u00e6\u00e2\1\0\0\0\u00e6\u00e4\1\0\0\0\u00e7"+
		"\u00e9\1\0\0\0\u00e8\u00dc\1\0\0\0\u00e8\u00e9\1\0\0\0\u00e9\17\1\0\0"+
		"\0\u00ea\u00ec\5\32\0\0\u00ec\u00ee\5\13\0\0\u00ee\u00ef\3\22\t\0\u00ef"+
		"\21\1\0\0\0\u00f0\u00f2\5\16\0\0\u00f2\u00f4\3\24\n\0\u00f4\u00f5\5\17"+
		"\0\0\u00f5\23\1\0\0\0\u00f6\u00fe\3\30\f\0\u00f8\u00fa\5\b\0\0\u00fa\u00fd"+
		"\3\30\f\0\u00fc\u00f8\1\0\0\0\u00fd\u0100\1\0\0\0\u00fe\u00fc\1\0\0\0"+
		"\u00fe\u00ff\1\0\0\0\u00ff\u0105\1\0\0\0\u0100\u00fe\1\0\0\0\u0101\u0103"+
		"\5\b\0\0\u0103\u0106\3\26\13\0\u0105\u0101\1\0\0\0\u0105\u0106\1\0\0\0"+
		"\u0106\u010a\1\0\0\0\u0107\u010a\3\26\13\0\u0109\u00f6\1\0\0\0\u0109\u0107"+
		"\1\0\0\0\u010a\25\1\0\0\0\u010b\u010d\5\23\0\0\u010d\u010f\5\n\0\0\u010f"+
		"\u0110\3\32\r\0\u0110\27\1\0\0\0\u0111\u0113\3\34\16\0\u0113\u0115\5\n"+
		"\0\0\u0115\u0116\3\32\r\0\u0116\31\1\0\0\0\u0117\u0126\3 \20\0\u0119\u0126"+
		"\3\"\21\0\u011b\u0126\3$\22\0\u011d\u0126\3\34\16\0\u011f\u0126\5\25\0"+
		"\0\u0121\u0126\5\26\0\0\u0123\u0126\5\32\0\0\u0125\u0117\1\0\0\0\u0125"+
		"\u0119\1\0\0\0\u0125\u011b\1\0\0\0\u0125\u011d\1\0\0\0\u0125\u011f\1\0"+
		"\0\0\u0125\u0121\1\0\0\0\u0125\u0123\1\0\0\0\u0126\33\1\0\0\0\u0127\u0128"+
		"\3\36\17\0\u0128\35\1\0\0\0\u0129\u012b\5\5\0\0\u012b\u012d\3(\24\0\u012d"+
		"\u012e\5\5\0\0\u012e\37\1\0\0\0\u012f\u0131\5\34\0\0\u0131\u0133\3(\24"+
		"\0\u0133\u0134\5=\0\0\u0134!\1\0\0\0\u0135\u0137\5\35\0\0\u0137\u0139"+
		"\3(\24\0\u0139\u013a\5E\0\0\u013a#\1\0\0\0\u013b\u013f\5\20\0\0\u013d"+
		"\u0140\3&\23\0\u013f\u013d\1\0\0\0\u013f\u0140\1\0\0\0\u0140\u0141\1\0"+
		"\0\0\u0141\u0143\3(\24\0\u0143\u0144\5\21\0\0\u0144%\1\0\0\0\u0145\u014d"+
		"\5\65\0\0\u0147\u0149\5\b\0\0\u0149\u014c\5\65\0\0\u014b\u0147\1\0\0\0"+
		"\u014c\u014f\1\0\0\0\u014d\u014b\1\0\0\0\u014d\u014e\1\0\0\0\u014e\u0150"+
		"\1\0\0\0\u014f\u014d\1\0\0\0\u0150\u0151\5\66\0\0\u0151\'\1\0\0\0\u0152"+
		"\u0161\5\31\0\0\u0154\u0161\5\37\0\0\u0156\u0161\3\62\31\0\u0158\u0161"+
		"\3.\27\0\u015a\u0161\3,\26\0\u015c\u0161\3*\25\0\u015e\u0161\5/\0\0\u0160"+
		"\u0152\1\0\0\0\u0160\u0154\1\0\0\0\u0160\u0156\1\0\0\0\u0160\u0158\1\0"+
		"\0\0\u0160\u015a\1\0\0\0\u0160\u015c\1\0\0\0\u0160\u015e\1\0\0\0\u0161"+
		"\u0164\1\0\0\0\u0162\u0160\1\0\0\0\u0162\u0163\1\0\0\0\u0163)\1\0\0\0"+
		"\u0164\u0162\1\0\0\0\u0165\u0167\5\3\0\0\u0167\u0169\5^\0\0\u0169\u016a"+
		"\5\4\0\0\u016a+\1\0\0\0\u016b\u016d\5\3\0\0\u016d\u0173\3@ \0\u016f\u0171"+
		"\5]\0\0\u0171\u0174\3:\35\0\u0173\u016f\1\0\0\0\u0173\u0174\1\0\0\0\u0174"+
		"\u0175\1\0\0\0\u0175\u0176\5\4\0\0\u0176-\1\0\0\0\u0177\u0179\5\3\0\0"+
		"\u0179\u017b\5`\0\0\u017b\u017d\5\4\0\0\u017d\u017f\3(\24\0\u017f\u0181"+
		"\5\3\0\0\u0181\u0183\5_\0\0\u0183\u0184\5\4\0\0\u0184/\1\0\0\0\u0185\u0186"+
		"\3$\22\0\u0186\61\1\0\0\0\u0187\u0189\5\3\0\0\u0189\u018b\5T\0\0\u018b"+
		"\u018d\5\6\0\0\u018d\u018f\3\64\32\0\u018f\u0191\5\7\0\0\u0191\u0193\5"+
		"\4\0\0\u0193\u01a5\3(\24\0\u0195\u0197\5\3\0\0\u0197\u0199\5U\0\0\u0199"+
		"\u019b\5\6\0\0\u019b\u019d\3\64\32\0\u019d\u019f\5\7\0\0\u019f\u01a1\5"+
		"\4\0\0\u01a1\u01a2\3(\24\0\u01a2\u01a4\1\0\0\0\u01a3\u0195\1\0\0\0\u01a4"+
		"\u01a7\1\0\0\0\u01a5\u01a3\1\0\0\0\u01a5\u01a6\1\0\0\0\u01a6\u01b0\1\0"+
		"\0\0\u01a7\u01a5\1\0\0\0\u01a8\u01aa\5\3\0\0\u01aa\u01ac\5W\0\0\u01ac"+
		"\u01ae\5\4\0\0\u01ae\u01b1\3(\24\0\u01b0\u01a8\1\0\0\0\u01b0\u01b1\1\0"+
		"\0\0\u01b1\u01b2\1\0\0\0\u01b2\u01b4\5\3\0\0\u01b4\u01b6\5V\0\0\u01b6"+
		"\u01b7\5\4\0\0\u01b7\63\1\0\0\0\u01b8\u01c0\3\66\33\0\u01ba\u01bc\5[\0"+
		"\0\u01bc\u01bf\3\66\33\0\u01be\u01ba\1\0\0\0\u01bf\u01c2\1\0\0\0\u01c0"+
		"\u01be\1\0\0\0\u01c0\u01c1\1\0\0\0\u01c1\65\1\0\0\0\u01c2\u01c0\1\0\0"+
		"\0\u01c3\u01cb\38\34\0\u01c5\u01c7\5\\\0\0\u01c7\u01ca\38\34\0\u01c9\u01c5"+
		"\1\0\0\0\u01ca\u01cd\1\0\0\0\u01cb\u01c9\1\0\0\0\u01cb\u01cc\1\0\0\0\u01cc"+
		"\67\1\0\0\0\u01cd\u01cb\1\0\0\0\u01ce\u01d0\5Z\0\0\u01d0\u01d5\38\34\0"+
		"\u01d2\u01d5\3F#\0\u01d4\u01ce\1\0\0\0\u01d4\u01d2\1\0\0\0\u01d59\1\0"+
		"\0\0\u01d6\u01de\3<\36\0\u01d8\u01da\5\b\0\0\u01da\u01dd\3<\36\0\u01dc"+
		"\u01d8\1\0\0\0\u01dd\u01e0\1\0\0\0\u01de\u01dc\1\0\0\0\u01de\u01df\1\0"+
		"\0\0\u01df;\1\0\0\0\u01e0\u01de\1\0\0\0\u01e1\u01e7\5\32\0\0\u01e3\u01e5"+
		"\5\f\0\0\u01e5\u01e8\3>\37\0\u01e7\u01e3\1\0\0\0\u01e7\u01e8\1\0\0\0\u01e8"+
		"=\1\0\0\0\u01e9\u01ef\3F#\0\u01eb\u01ed\5\n\0\0\u01ed\u01f0\3D\"\0\u01ef"+
		"\u01eb\1\0\0\0\u01ef\u01f0\1\0\0\0\u01f0?\1\0\0\0\u01f1\u01f2\3B!\0\u01f2"+
		"A\1\0\0\0\u01f3\u0201\3F#\0\u01f5\u01f7\5\b\0\0\u01f7\u01fa\3F#\0\u01f9"+
		"\u01f5\1\0\0\0\u01fa\u01fb\1\0\0\0\u01fb\u01f9\1\0\0\0\u01fb\u01fc\1\0"+
		"\0\0\u01fc\u01fd\1\0\0\0\u01fd\u01ff\5\n\0\0\u01ff\u0200\3D\"\0\u0200"+
		"\u0202\1\0\0\0\u0201\u01f9\1\0\0\0\u0201\u0202\1\0\0\0\u0202\u0212\1\0"+
		"\0\0\u0203\u0205\5\n\0\0\u0205\u020d\3D\"\0\u0207\u0209\5\b\0\0\u0209"+
		"\u020c\3D\"\0\u020b\u0207\1\0\0\0\u020c\u020f\1\0\0\0\u020d\u020b\1\0"+
		"\0\0\u020d\u020e\1\0\0\0\u020e\u0211\1\0\0\0\u020f\u020d\1\0\0\0\u0210"+
		"\u0203\1\0\0\0\u0211\u0214\1\0\0\0\u0212\u0210\1\0\0\0\u0212\u0213\1\0"+
		"\0\0\u0213C\1\0\0\0\u0214\u0212\1\0\0\0\u0215\u0217\5\32\0\0\u0217\u0219"+
		"\5\6\0\0\u0219\u021b\3L&\0\u021b\u021c\5\7\0\0\u021c\u022e\1\0\0\0\u021d"+
		"\u022e\3\60\30\0\u021f\u0221\5\6\0\0\u0221\u0223\3B!\0\u0223\u0225\5\7"+
		"\0\0\u0225\u0229\5\6\0\0\u0227\u022a\3N\'\0\u0229\u0227\1\0\0\0\u0229"+
		"\u022a\1\0\0\0\u022a\u022b\1\0\0\0\u022b\u022c\5\7\0\0\u022c\u022e\1\0"+
		"\0\0\u022d\u0215\1\0\0\0\u022d\u021d\1\0\0\0\u022d\u021f\1\0\0\0\u022e"+
		"E\1\0\0\0\u022f\u023f\3H$\0\u0231\u0233\5\t\0\0\u0233\u023e\5\32\0\0\u0235"+
		"\u0237\5\t\0\0\u0237\u0239\5\6\0\0\u0239\u023b\3B!\0\u023b\u023c\5\7\0"+
		"\0\u023c\u023e\1\0\0\0\u023d\u0231\1\0\0\0\u023d\u0235\1\0\0\0\u023e\u0241"+
		"\1\0\0\0\u023f\u023d\1\0\0\0\u023f\u0240\1\0\0\0\u0240G\1\0\0\0\u0241"+
		"\u023f\1\0\0\0\u0242\u0244\5S\0\0\u0244\u0246\5\t\0\0\u0246\u0248\5\32"+
		"\0\0\u0248\u024a\5\6\0\0\u024a\u024c\3L&\0\u024c\u024d\5\7\0\0\u024d\u026b"+
		"\1\0\0\0\u024e\u0250\5\32\0\0\u0250\u0252\5\6\0\0\u0252\u0254\3L&\0\u0254"+
		"\u0255\5\7\0\0\u0255\u026b\1\0\0\0\u0256\u0258\5\r\0\0\u0258\u025a\5S"+
		"\0\0\u025a\u025c\5\t\0\0\u025c\u025e\5\32\0\0\u025e\u0260\5\6\0\0\u0260"+
		"\u026b\5\7\0\0\u0262\u0264\5`\0\0\u0264\u0266\5\6\0\0\u0266\u026b\5\7"+
		"\0\0\u0268\u026b\3J%\0\u026a\u0242\1\0\0\0\u026a\u024e\1\0\0\0\u026a\u0256"+
		"\1\0\0\0\u026a\u0262\1\0\0\0\u026a\u0268\1\0\0\0\u026bI\1\0\0\0\u026c"+
		"\u028f\5\32\0\0\u026e\u028f\5\33\0\0\u0270\u028f\5\25\0\0\u0272\u028f"+
		"\5\26\0\0\u0274\u028f\3\60\30\0\u0276\u028f\3T*\0\u0278\u027a\5\6\0\0"+
		"\u027a\u027c\3\64\32\0\u027c\u027d\5\7\0\0\u027d\u028f\1\0\0\0\u027e\u0280"+
		"\5\6\0\0\u0280\u0282\3@ \0\u0282\u028c\5\7\0\0\u0284\u0288\5\6\0\0\u0286"+
		"\u0289\3N\'\0\u0288\u0286\1\0\0\0\u0288\u0289\1\0\0\0\u0289\u028a\1\0"+
		"\0\0\u028a\u028d\5\7\0\0\u028c\u0284\1\0\0\0\u028c\u028d\1\0\0\0\u028d"+
		"\u028f\1\0\0\0\u028e\u026c\1\0\0\0\u028e\u026e\1\0\0\0\u028e\u0270\1\0"+
		"\0\0\u028e\u0272\1\0\0\0\u028e\u0274\1\0\0\0\u028e\u0276\1\0\0\0\u028e"+
		"\u0278\1\0\0\0\u028e\u027e\1\0\0\0\u028fK\1\0\0\0\u0290\u02a8\3N\'\0\u0292"+
		"\u029a\3R)\0\u0294\u0296\5\b\0\0\u0296\u0299\3R)\0\u0298\u0294\1\0\0\0"+
		"\u0299\u029c\1\0\0\0\u029a\u0298\1\0\0\0\u029a\u029b\1\0\0\0\u029b\u02a1"+
		"\1\0\0\0\u029c\u029a\1\0\0\0\u029d\u029f\5\b\0\0\u029f\u02a2\5Y\0\0\u02a1"+
		"\u029d\1\0\0\0\u02a1\u02a2\1\0\0\0\u02a2\u02a8\1\0\0\0\u02a3\u02a8\5Y"+
		"\0\0\u02a5\u02a8\1\0\0\0\u02a7\u0290\1\0\0\0\u02a7\u0292\1\0\0\0\u02a7"+
		"\u02a3\1\0\0\0\u02a7\u02a5\1\0\0\0\u02a8M\1\0\0\0\u02a9\u02b1\3P(\0\u02ab"+
		"\u02ad\5\b\0\0\u02ad\u02b0\3P(\0\u02af\u02ab\1\0\0\0\u02b0\u02b3\1\0\0"+
		"\0\u02b1\u02af\1\0\0\0\u02b1\u02b2\1\0\0\0\u02b2O\1\0\0\0\u02b3\u02b1"+
		"\1\0\0\0\u02b4\u02b5\3>\37\0\u02b5Q\1\0\0\0\u02b6\u02b8\5\32\0\0\u02b8"+
		"\u02ba\5\f\0\0\u02ba\u02bb\3P(\0\u02bbS\1\0\0\0\u02bc\u02be\5\16\0\0\u02be"+
		"\u02d0\5\17\0\0\u02c0\u02c2\5\16\0\0\u02c2\u02ca\3V+\0\u02c4\u02c6\5\b"+
		"\0\0\u02c6\u02c9\3V+\0\u02c8\u02c4\1\0\0\0\u02c9\u02cc\1\0\0\0\u02ca\u02c8"+
		"\1\0\0\0\u02ca\u02cb\1\0\0\0\u02cb\u02cd\1\0\0\0\u02cc\u02ca\1\0\0\0\u02cd"+
		"\u02ce\5\17\0\0\u02ce\u02d0\1\0\0\0\u02cf\u02bc\1\0\0\0\u02cf\u02c0\1"+
		"\0\0\0\u02d0U\1\0\0\0\u02d1\u02d4\3>\37\0\u02d3\u02d1\1\0\0\0\u02d3\u02d4"+
		"\1\0\0\0\u02d4W\1\0\0\0\64Z\1^\1f\1m\1w\1\u0083\1\u0086\1\u0092\1\u00a1"+
		"\1\u00b7\1\u00c1\1\u00c9\1\u00d3\1\u00d8\1\u00e6\1\u00e8\1\u00fe\1\u0105"+
		"\1\u0109\1\u0125\1\u013f\1\u014d\1\u0160\1\u0162\1\u0173\1\u01a5\1\u01b0"+
		"\1\u01c0\1\u01cb\1\u01d4\1\u01de\1\u01e7\1\u01ef\1\u01fb\1\u0201\1\u020d"+
		"\1\u0212\1\u0229\1\u022d\1\u023d\1\u023f\1\u026a\1\u0288\1\u028c\1\u028e"+
		"\1\u029a\1\u02a1\1\u02a7\1\u02b1\1\u02ca\1\u02cf\1\u02d3\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}