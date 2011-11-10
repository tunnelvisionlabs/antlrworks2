// $ANTLR ANTLRVersion> GrammarParser.java generatedTimestamp>

/*
 [The "BSD licence"]
 Copyright (c) 2005-2009 Terence Parr
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.antlr.works.editor.grammar.experimental;

//import org.antlr.v4.tool.*;
//import org.antlr.v4.tool.ast.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused"})
public class GrammarParser extends Parser {
	public static final int
		DOLLAR=46, PROTECTED=19, LT=36, ACTION_COLON=84, STAR=41, NESTED_ACTION=73, 
		DOUBLE_ANGLE_STRING_LITERAL=7, FRAGMENT=14, ACTION_ESCAPE=86, ACTION_REFERENCE=88, 
		ACTION_COLON2=83, ACTION_DOT=74, NOT=54, TREE_BEGIN=51, ARG_ACTION_LT=62, 
		ACTION_NEWLINE=93, TOKEN_REF=56, LPAREN=33, ARG_ACTION_ELEMENT=70, ARG_ACTION_RPAREN=65, 
		ARG_ACTION_GT=63, AT=52, RPAREN=34, ARG_ACTION_LPAREN=64, IMPORT=13, STRING_LITERAL=59, 
		TREE=17, SCOPE=12, END_ACTION=94, ETC=49, COMMA=31, ACTION_GT=76, ACTION_LITERAL=90, 
		DOC_COMMENT=3, BLOCK_COMMENT=4, PLUS=42, BEGIN_ACTION=9, DOUBLE_QUOTE_STRING_LITERAL=6, 
		DOT=47, ACTION_LBRACK=79, MODE=28, ACTION_WORD=87, GRAMMAR=18, ARG_ACTION_ESCAPE=68, 
		RETURNS=22, ARG_ACTION_TEXT=71, ACTION_EQUALS=81, LOCALS=23, ACTION_WS=92, 
		IMPLIES=35, RBRACE=55, POUND=53, LINE_COMMENT=5, PRIVATE=21, RARROW=50, 
		END_ARG_ACTION=72, TOKENS=11, RANGE=48, THROWS=24, INT=58, BANG=40, SEMI=32, 
		ROOT=45, RULE_REF=57, ARG_ACTION_EQUALS=66, ACTION_RPAREN=78, ACTION_COMMA=82, 
		COLON=29, COLONCOLON=30, ACTION_RBRACK=80, WS=60, ACTION_COMMENT=89, QUESTION=39, 
		FINALLY=26, ACTION_LT=75, TEMPLATE=27, ACTION_TEXT=91, LEXER=15, ERRCHAR=61, 
		ACTION_MINUS=85, ARG_ACTION_COMMA=67, OR=44, ASSIGN=38, PLUS_ASSIGN=43, 
		GT=37, CATCH=25, ARG_ACTION_WORD=69, PUBLIC=20, PARSER=16, ACTION_LPAREN=77, 
		OPTIONS=10, BEGIN_ARG_ACTION=8, RULE=95, PREC_RULE=96, RULES=97, RULEMODIFIERS=98, 
		RULEACTIONS=99, BLOCK=100, REWRITE_BLOCK=101, REWRITE_SEQ=102, OPTIONAL=103, 
		CLOSURE=104, POSITIVE_CLOSURE=105, SET=106, CHAR_RANGE=107, EPSILON=108, 
		ALT=109, ALTLIST=110, ID=111, ARG=112, ARGLIST=113, RET=114, COMBINED=115, 
		INITACTION=116, LABEL=117, WILDCARD=118, LIST=119, ELEMENT_OPTIONS=120, 
		ST_RESULT=121, RESULT=122, ALT_REWRITE=123, DOWN_TOKEN=124, UP_TOKEN=125, 
		SEMPRED=126;
	public static final String[] tokenNames = {
		"<INVALID>", "<INVALID>", "<INVALID>",
		"DOC_COMMENT", "BLOCK_COMMENT", "LINE_COMMENT", "DOUBLE_QUOTE_STRING_LITERAL", 
		"DOUBLE_ANGLE_STRING_LITERAL", "BEGIN_ARG_ACTION", "BEGIN_ACTION", "OPTIONS", 
		"TOKENS", "'scope'", "'import'", "'fragment'", "'lexer'", "'parser'", 
		"'tree'", "'grammar'", "'protected'", "'public'", "'private'", "'returns'", 
		"'locals'", "'throws'", "'catch'", "'finally'", "'template'", "'mode'", 
		"':'", "'::'", "','", "';'", "'('", "')'", "'=>'", "'<'", "'>'", "'='", 
		"'?'", "'!'", "'*'", "'+'", "'+='", "'|'", "'^'", "'$'", "'.'", "'..'", 
		"'...'", "'->'", "'^('", "'@'", "'#'", "'~'", "'}'", "TOKEN_REF", "RULE_REF", 
		"INT", "STRING_LITERAL", "WS", "ERRCHAR", "ARG_ACTION_LT", "ARG_ACTION_GT", 
		"ARG_ACTION_LPAREN", "ARG_ACTION_RPAREN", "ARG_ACTION_EQUALS", "ARG_ACTION_COMMA", 
		"ARG_ACTION_ESCAPE", "ARG_ACTION_WORD", "ARG_ACTION_ELEMENT", "ARG_ACTION_TEXT", 
		"END_ARG_ACTION", "NESTED_ACTION", "ACTION_DOT", "ACTION_LT", "ACTION_GT", 
		"ACTION_LPAREN", "ACTION_RPAREN", "ACTION_LBRACK", "ACTION_RBRACK", "ACTION_EQUALS", 
		"ACTION_COMMA", "ACTION_COLON2", "ACTION_COLON", "ACTION_MINUS", "ACTION_ESCAPE", 
		"ACTION_WORD", "ACTION_REFERENCE", "ACTION_COMMENT", "ACTION_LITERAL", 
		"ACTION_TEXT", "ACTION_WS", "ACTION_NEWLINE", "END_ACTION", "RULE", "PREC_RULE", 
		"RULES", "RULEMODIFIERS", "RULEACTIONS", "BLOCK", "REWRITE_BLOCK", "REWRITE_SEQ", 
		"OPTIONAL", "CLOSURE", "POSITIVE_CLOSURE", "SET", "CHAR_RANGE", "EPSILON", 
		"ALT", "ALTLIST", "ID", "ARG", "ARGLIST", "RET", "COMBINED", "INITACTION", 
		"LABEL", "WILDCARD", "LIST", "ELEMENT_OPTIONS", "ST_RESULT", "RESULT", 
		"ALT_REWRITE", "DOWN_TOKEN", "UP_TOKEN", "SEMPRED"
	};
	public static final int
		RULE_grammarSpec = 0, RULE_grammarType = 1, RULE_prequelConstruct = 2, 
		RULE_optionsSpec = 3, RULE_option = 4, RULE_optionValue = 5, RULE_delegateGrammars = 6, 
		RULE_delegateGrammar = 7, RULE_tokensSpec = 8, RULE_tokenSpec = 9, RULE_actionBlock = 10, 
		RULE_actionExpression = 11, RULE_actionScopeExpression = 12, RULE_actionIgnored = 13, 
		RULE_argActionBlock = 14, RULE_action = 15, RULE_actionScopeName = 16, 
		RULE_mode_ = 17, RULE_rules = 18, RULE_rule = 19, RULE_exceptionGroup = 20, 
		RULE_exceptionHandler = 21, RULE_finallyClause = 22, RULE_rulePrequels = 23, 
		RULE_rulePrequel = 24, RULE_ruleReturns = 25, RULE_throwsSpec = 26, RULE_locals_ = 27, 
		RULE_ruleAction = 28, RULE_ruleModifiers = 29, RULE_ruleModifier = 30, 
		RULE_ruleBlock = 31, RULE_ruleAltList = 32, RULE_labeledAlt = 33, RULE_altList = 34, 
		RULE_alternative = 35, RULE_elements = 36, RULE_element = 37, RULE_labeledElement = 38, 
		RULE_treeSpec = 39, RULE_ebnf = 40, RULE_blockSuffix = 41, RULE_ebnfSuffix = 42, 
		RULE_atom = 43, RULE_notSet = 44, RULE_blockSet = 45, RULE_setElement = 46, 
		RULE_block = 47, RULE_ruleref = 48, RULE_range = 49, RULE_terminal = 50, 
		RULE_elementOptions = 51, RULE_elementOption = 52, RULE_rewrite = 53, 
		RULE_predicatedRewrite = 54, RULE_nakedRewrite = 55, RULE_rewriteAlt = 56, 
		RULE_rewriteTreeAlt = 57, RULE_rewriteTreeElement = 58, RULE_rewriteTreeAtom = 59, 
		RULE_rewriteTreeEbnf = 60, RULE_rewriteEbnfSuffix = 61, RULE_rewriteTree = 62, 
		RULE_rewriteTemplate = 63, RULE_rewriteTemplateRef = 64, RULE_rewriteIndirectTemplateHead = 65, 
		RULE_rewriteTemplateArgs = 66, RULE_rewriteTemplateArg = 67, RULE_id = 68, 
		RULE_qid = 69, RULE_alternativeEntry = 70, RULE_elementEntry = 71, RULE_ruleEntry = 72, 
		RULE_blockEntry = 73;
	public static final String[] ruleNames = {
		"grammarSpec", "grammarType", "prequelConstruct", "optionsSpec", "option", 
		"optionValue", "delegateGrammars", "delegateGrammar", "tokensSpec", "tokenSpec", 
		"actionBlock", "actionExpression", "actionScopeExpression", "actionIgnored", 
		"argActionBlock", "action", "actionScopeName", "mode_", "rules", "rule", 
		"exceptionGroup", "exceptionHandler", "finallyClause", "rulePrequels", 
		"rulePrequel", "ruleReturns", "throwsSpec", "locals_", "ruleAction", "ruleModifiers", 
		"ruleModifier", "ruleBlock", "ruleAltList", "labeledAlt", "altList", "alternative", 
		"elements", "element", "labeledElement", "treeSpec", "ebnf", "blockSuffix", 
		"ebnfSuffix", "atom", "notSet", "blockSet", "setElement", "block", "ruleref", 
		"range", "terminal", "elementOptions", "elementOption", "rewrite", "predicatedRewrite", 
		"nakedRewrite", "rewriteAlt", "rewriteTreeAlt", "rewriteTreeElement", 
		"rewriteTreeAtom", "rewriteTreeEbnf", "rewriteEbnfSuffix", "rewriteTree", 
		"rewriteTemplate", "rewriteTemplateRef", "rewriteIndirectTemplateHead", 
		"rewriteTemplateArgs", "rewriteTemplateArg", "id", "qid", "alternativeEntry", 
		"elementEntry", "ruleEntry", "blockEntry"
	};

	//Stack paraphrases = new Stack();
	/** Affects tree construction; no SET collapsing if AST (ID|INT) would hide them from rewrite.
	 *  Could use for just AST ops, but we can't see -> until after building sets.
	boolean buildAST;
	 */

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN);
	}
	public static class grammarSpecContext extends ParserRuleContext {
		public grammarSpecContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final grammarSpecContext grammarSpec() throws RecognitionException {
		grammarSpecContext _localctx = new grammarSpecContext(_ctx, 0);
		enterRule(_localctx, RULE_grammarSpec);
		//System.out.println("enter "+ruleNames[RULE_grammarSpec]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==DOC_COMMENT || _interp.adaptivePredict(_input,0,_ctx) == 1 ) {
				{
				setState(148); match(DOC_COMMENT);
				}
			}

			setState(152); grammarType();
			setState(154); id();
			setState(156); match(SEMI);
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==OPTIONS || _la==TOKENS || _la==IMPORT || _la==AT || _interp.adaptivePredict(_input,1,_ctx) == 1 ) {
				{
				{
				setState(158); prequelConstruct();
				}
				}
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(165); rules();
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==MODE || _interp.adaptivePredict(_input,2,_ctx) == 1 ) {
				{
				{
				setState(167); mode_();
				}
				}
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(174); match(EOF);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_grammarSpec);
			// System.out.println("exit "+ruleNames[RULE_grammarSpec]);
		}
		return _localctx;
	}

	public static class grammarTypeContext extends ParserRuleContext {
		public Token t;;
		public Token g;;
		public grammarTypeContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final grammarTypeContext grammarType() throws RecognitionException {
		grammarTypeContext _localctx = new grammarTypeContext(_ctx, 2);
		enterRule(_localctx, RULE_grammarType);
		//System.out.println("enter "+ruleNames[RULE_grammarType]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			_errHandler.sync(this);
			int alt3;
			switch ( _input.LA(1) ) {
			case LEXER:
				alt3 = 1;
				break;
			case PARSER:
				alt3 = 2;
				break;
			case TREE:
				alt3 = 3;
				break;
			case GRAMMAR:
				alt3 = 4;
				break;
			default:
				alt3 = _interp.adaptivePredict(_input,3,_ctx);
				break;
			}
			switch (alt3) {
				case 1:
					{
					setState(176); _localctx.t = match(LEXER);
					setState(178); _localctx.g = match(GRAMMAR);
					}
					break;

				case 2:
					{
					setState(180); _localctx.t = match(PARSER);
					setState(182); _localctx.g = match(GRAMMAR);
					}
					break;

				case 3:
					{
					setState(184); _localctx.t = match(TREE);
					setState(186); _localctx.g = match(GRAMMAR);
					}
					break;

				case 4:
					{
					setState(188); _localctx.g = match(GRAMMAR);
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_grammarType);
			// System.out.println("exit "+ruleNames[RULE_grammarType]);
		}
		return _localctx;
	}

	public static class prequelConstructContext extends ParserRuleContext {
		public prequelConstructContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final prequelConstructContext prequelConstruct() throws RecognitionException {
		prequelConstructContext _localctx = new prequelConstructContext(_ctx, 4);
		enterRule(_localctx, RULE_prequelConstruct);
		//System.out.println("enter "+ruleNames[RULE_prequelConstruct]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(200);
			_errHandler.sync(this);
			int alt4;
			switch ( _input.LA(1) ) {
			case OPTIONS:
				alt4 = 1;
				break;
			case IMPORT:
				alt4 = 2;
				break;
			case TOKENS:
				alt4 = 3;
				break;
			case AT:
				alt4 = 4;
				break;
			default:
				alt4 = _interp.adaptivePredict(_input,4,_ctx);
				break;
			}
			switch (alt4) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(192); optionsSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(194); delegateGrammars();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(196); tokensSpec();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(198); action();
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_prequelConstruct);
			// System.out.println("exit "+ruleNames[RULE_prequelConstruct]);
		}
		return _localctx;
	}

	public static class optionsSpecContext extends ParserRuleContext {
		public optionsSpecContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final optionsSpecContext optionsSpec() throws RecognitionException {
		optionsSpecContext _localctx = new optionsSpecContext(_ctx, 6);
		enterRule(_localctx, RULE_optionsSpec);
		//System.out.println("enter "+ruleNames[RULE_optionsSpec]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202); match(OPTIONS);
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==TEMPLATE || _la==TOKEN_REF || _la==RULE_REF || _interp.adaptivePredict(_input,5,_ctx) == 1 ) {
				{
				{
				setState(204); option();
				setState(206); match(SEMI);
				}
				}
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(213); match(RBRACE);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_optionsSpec);
			// System.out.println("exit "+ruleNames[RULE_optionsSpec]);
		}
		return _localctx;
	}

	public static class optionContext extends ParserRuleContext {
		public optionContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final optionContext option() throws RecognitionException {
		optionContext _localctx = new optionContext(_ctx, 8);
		enterRule(_localctx, RULE_option);
		//System.out.println("enter "+ruleNames[RULE_option]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215); id();
			setState(217); match(ASSIGN);
			setState(219); optionValue();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_option);
			// System.out.println("exit "+ruleNames[RULE_option]);
		}
		return _localctx;
	}

	public static class optionValueContext extends ParserRuleContext {
		public optionValueContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final optionValueContext optionValue() throws RecognitionException {
		optionValueContext _localctx = new optionValueContext(_ctx, 10);
		enterRule(_localctx, RULE_optionValue);
		//System.out.println("enter "+ruleNames[RULE_optionValue]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(229);
			_errHandler.sync(this);
			int alt6;
			switch ( _input.LA(1) ) {
			case TEMPLATE:
			case TOKEN_REF:
			case RULE_REF:
				alt6 = 1;
				break;
			case STRING_LITERAL:
				alt6 = 2;
				break;
			case INT:
				alt6 = 3;
				break;
			case STAR:
				alt6 = 4;
				break;
			default:
				alt6 = _interp.adaptivePredict(_input,6,_ctx);
				break;
			}
			switch (alt6) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(221); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(223); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(225); match(INT);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(227); match(STAR);
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_optionValue);
			// System.out.println("exit "+ruleNames[RULE_optionValue]);
		}
		return _localctx;
	}

	public static class delegateGrammarsContext extends ParserRuleContext {
		public delegateGrammarsContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final delegateGrammarsContext delegateGrammars() throws RecognitionException {
		delegateGrammarsContext _localctx = new delegateGrammarsContext(_ctx, 12);
		enterRule(_localctx, RULE_delegateGrammars);
		//System.out.println("enter "+ruleNames[RULE_delegateGrammars]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231); match(IMPORT);
			setState(233); delegateGrammar();
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==COMMA || _interp.adaptivePredict(_input,7,_ctx) == 1 ) {
				{
				{
				setState(235); match(COMMA);
				setState(237); delegateGrammar();
				}
				}
				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(244); match(SEMI);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_delegateGrammars);
			// System.out.println("exit "+ruleNames[RULE_delegateGrammars]);
		}
		return _localctx;
	}

	public static class delegateGrammarContext extends ParserRuleContext {
		public delegateGrammarContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final delegateGrammarContext delegateGrammar() throws RecognitionException {
		delegateGrammarContext _localctx = new delegateGrammarContext(_ctx, 14);
		enterRule(_localctx, RULE_delegateGrammar);
		//System.out.println("enter "+ruleNames[RULE_delegateGrammar]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(254);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,8,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(246); id();
					setState(248); match(ASSIGN);
					setState(250); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(252); id();
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
			exitRule(RULE_delegateGrammar);
			// System.out.println("exit "+ruleNames[RULE_delegateGrammar]);
		}
		return _localctx;
	}

	public static class tokensSpecContext extends ParserRuleContext {
		public tokensSpecContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final tokensSpecContext tokensSpec() throws RecognitionException {
		tokensSpecContext _localctx = new tokensSpecContext(_ctx, 16);
		enterRule(_localctx, RULE_tokensSpec);
		//System.out.println("enter "+ruleNames[RULE_tokensSpec]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256); match(TOKENS);
			setState(260); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(258); tokenSpec();
				}
				}
				setState(262); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			    if (!(_la==TEMPLATE || _la==TOKEN_REF || _la==RULE_REF) && _interp.adaptivePredict(_input,9,_ctx) != 1) {
			        break;
			    }
			} while (true);
			setState(264); match(RBRACE);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_tokensSpec);
			// System.out.println("exit "+ruleNames[RULE_tokensSpec]);
		}
		return _localctx;
	}

	public static class tokenSpecContext extends ParserRuleContext {
		public tokenSpecContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final tokenSpecContext tokenSpec() throws RecognitionException {
		tokenSpecContext _localctx = new tokenSpecContext(_ctx, 18);
		enterRule(_localctx, RULE_tokenSpec);
		//System.out.println("enter "+ruleNames[RULE_tokenSpec]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(280);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,11,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(266); id();
					setState(274);
					_errHandler.sync(this);
					int alt10;
					switch ( _input.LA(1) ) {
					case ASSIGN:
						alt10 = 1;
						break;
					case SEMI:
						alt10 = 2;
						break;
					default:
						alt10 = _interp.adaptivePredict(_input,10,_ctx);
						break;
					}
					switch (alt10) {
						case 1:
							{
							setState(268); match(ASSIGN);
							setState(270); match(STRING_LITERAL);
							}
							break;

						case 2:
							{
							}
							break;

					default:
						throw new NoViableAltException(this);
					}
					setState(276); match(SEMI);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(278); match(RULE_REF);
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
			exitRule(RULE_tokenSpec);
			// System.out.println("exit "+ruleNames[RULE_tokenSpec]);
		}
		return _localctx;
	}

	public static class actionBlockContext extends ParserRuleContext {
		public actionBlockContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionBlockContext actionBlock() throws RecognitionException {
		actionBlockContext _localctx = new actionBlockContext(_ctx, 20);
		enterRule(_localctx, RULE_actionBlock);
		//System.out.println("enter "+ruleNames[RULE_actionBlock]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282); match(BEGIN_ACTION);
			setState(330);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==BEGIN_ACTION || _la==ACTION_DOT || _la==ACTION_LT || _la==ACTION_GT || _la==ACTION_LPAREN || _la==ACTION_RPAREN || _la==ACTION_LBRACK || _la==ACTION_RBRACK || _la==ACTION_EQUALS || _la==ACTION_COMMA || _la==ACTION_COLON2 || _la==ACTION_COLON || _la==ACTION_MINUS || _la==ACTION_ESCAPE || _la==ACTION_WORD || _la==ACTION_REFERENCE || _la==ACTION_COMMENT || _la==ACTION_LITERAL || _la==ACTION_TEXT || _la==ACTION_WS || _la==ACTION_NEWLINE || _interp.adaptivePredict(_input,13,_ctx) == 1 ) {
				{
				setState(328);
				_errHandler.sync(this);
				switch ( _interp.adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						setState(284); actionBlock();
						}
						break;

					case 2:
						{
						setState(286); actionExpression();
						}
						break;

					case 3:
						{
						setState(288); actionScopeExpression();
						}
						break;

					case 4:
						{
						setState(290); actionIgnored();
						}
						break;

					case 5:
						{
						setState(292); match(ACTION_COMMENT);
						}
						break;

					case 6:
						{
						setState(294); match(ACTION_LITERAL);
						}
						break;

					case 7:
						{
						setState(296); match(ACTION_TEXT);
						}
						break;

					case 8:
						{
						setState(298); match(ACTION_LT);
						}
						break;

					case 9:
						{
						setState(300); match(ACTION_GT);
						}
						break;

					case 10:
						{
						setState(302); match(ACTION_LPAREN);
						}
						break;

					case 11:
						{
						setState(304); match(ACTION_RPAREN);
						}
						break;

					case 12:
						{
						setState(306); match(ACTION_LBRACK);
						}
						break;

					case 13:
						{
						setState(308); match(ACTION_RBRACK);
						}
						break;

					case 14:
						{
						setState(310); match(ACTION_EQUALS);
						}
						break;

					case 15:
						{
						setState(312); match(ACTION_COMMA);
						}
						break;

					case 16:
						{
						setState(314); match(ACTION_ESCAPE);
						}
						break;

					case 17:
						{
						setState(316); match(ACTION_WORD);
						}
						break;

					case 18:
						{
						setState(318); match(ACTION_REFERENCE);
						}
						break;

					case 19:
						{
						setState(320); match(ACTION_COLON);
						}
						break;

					case 20:
						{
						setState(322); match(ACTION_COLON2);
						}
						break;

					case 21:
						{
						setState(324); match(ACTION_MINUS);
						}
						break;

					case 22:
						{
						setState(326); match(ACTION_DOT);
						}
						break;
				}
				}
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(333); match(END_ACTION);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_actionBlock);
			// System.out.println("exit "+ruleNames[RULE_actionBlock]);
		}
		return _localctx;
	}

	public static class actionExpressionContext extends ParserRuleContext {
		public actionExpressionContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionExpressionContext actionExpression() throws RecognitionException {
		actionExpressionContext _localctx = new actionExpressionContext(_ctx, 22);
		enterRule(_localctx, RULE_actionExpression);
		//System.out.println("enter "+ruleNames[RULE_actionExpression]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335); match(ACTION_REFERENCE);
			setState(341);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==ACTION_COMMENT || _la==ACTION_WS || _la==ACTION_NEWLINE || _interp.adaptivePredict(_input,14,_ctx) == 1 ) {
				{
				{
				setState(337); actionIgnored();
				}
				}
				setState(343);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(344); match(ACTION_DOT);
			setState(350);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==ACTION_COMMENT || _la==ACTION_WS || _la==ACTION_NEWLINE || _interp.adaptivePredict(_input,15,_ctx) == 1 ) {
				{
				{
				setState(346); actionIgnored();
				}
				}
				setState(352);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(353); match(ACTION_WORD);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_actionExpression);
			// System.out.println("exit "+ruleNames[RULE_actionExpression]);
		}
		return _localctx;
	}

	public static class actionScopeExpressionContext extends ParserRuleContext {
		public actionScopeExpressionContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionScopeExpressionContext actionScopeExpression() throws RecognitionException {
		actionScopeExpressionContext _localctx = new actionScopeExpressionContext(_ctx, 24);
		enterRule(_localctx, RULE_actionScopeExpression);
		//System.out.println("enter "+ruleNames[RULE_actionScopeExpression]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355); match(ACTION_REFERENCE);
			setState(361);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==ACTION_COMMENT || _la==ACTION_WS || _la==ACTION_NEWLINE || _interp.adaptivePredict(_input,16,_ctx) == 1 ) {
				{
				{
				setState(357); actionIgnored();
				}
				}
				setState(363);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(402);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==ACTION_LBRACK || _interp.adaptivePredict(_input,22,_ctx) == 1 ) {
				{
				setState(364); match(ACTION_LBRACK);
				setState(370);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ( _la==ACTION_COMMENT || _la==ACTION_WS || _la==ACTION_NEWLINE || _interp.adaptivePredict(_input,17,_ctx) == 1 ) {
					{
					{
					setState(366); actionIgnored();
					}
					}
					setState(372);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(382);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ( _la==ACTION_MINUS || _interp.adaptivePredict(_input,19,_ctx) == 1 ) {
					{
					setState(373); match(ACTION_MINUS);
					setState(379);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ( _la==ACTION_COMMENT || _la==ACTION_WS || _la==ACTION_NEWLINE || _interp.adaptivePredict(_input,18,_ctx) == 1 ) {
						{
						{
						setState(375); actionIgnored();
						}
						}
						setState(381);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(384); match(ACTION_WORD);
				setState(390);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ( _la==ACTION_COMMENT || _la==ACTION_WS || _la==ACTION_NEWLINE || _interp.adaptivePredict(_input,20,_ctx) == 1 ) {
					{
					{
					setState(386); actionIgnored();
					}
					}
					setState(392);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(393); match(ACTION_RBRACK);
				setState(399);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ( _la==ACTION_COMMENT || _la==ACTION_WS || _la==ACTION_NEWLINE || _interp.adaptivePredict(_input,21,_ctx) == 1 ) {
					{
					{
					setState(395); actionIgnored();
					}
					}
					setState(401);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(404); match(ACTION_COLON2);
			setState(410);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==ACTION_COMMENT || _la==ACTION_WS || _la==ACTION_NEWLINE || _interp.adaptivePredict(_input,23,_ctx) == 1 ) {
				{
				{
				setState(406); actionIgnored();
				}
				}
				setState(412);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(413); match(ACTION_WORD);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_actionScopeExpression);
			// System.out.println("exit "+ruleNames[RULE_actionScopeExpression]);
		}
		return _localctx;
	}

	public static class actionIgnoredContext extends ParserRuleContext {
		public actionIgnoredContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionIgnoredContext actionIgnored() throws RecognitionException {
		actionIgnoredContext _localctx = new actionIgnoredContext(_ctx, 26);
		enterRule(_localctx, RULE_actionIgnored);
		//System.out.println("enter "+ruleNames[RULE_actionIgnored]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(421);
			_errHandler.sync(this);
			int alt24;
			switch ( _input.LA(1) ) {
			case ACTION_WS:
				alt24 = 1;
				break;
			case ACTION_NEWLINE:
				alt24 = 2;
				break;
			case ACTION_COMMENT:
				alt24 = 3;
				break;
			default:
				alt24 = _interp.adaptivePredict(_input,24,_ctx);
				break;
			}
			switch (alt24) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(415); match(ACTION_WS);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(417); match(ACTION_NEWLINE);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(419); match(ACTION_COMMENT);
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_actionIgnored);
			// System.out.println("exit "+ruleNames[RULE_actionIgnored]);
		}
		return _localctx;
	}

	public static class argActionBlockContext extends ParserRuleContext {
		public argActionBlockContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final argActionBlockContext argActionBlock() throws RecognitionException {
		argActionBlockContext _localctx = new argActionBlockContext(_ctx, 28);
		enterRule(_localctx, RULE_argActionBlock);
		//System.out.println("enter "+ruleNames[RULE_argActionBlock]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423); match(BEGIN_ARG_ACTION);
			setState(429);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==ARG_ACTION_LT || _la==ARG_ACTION_GT || _la==ARG_ACTION_LPAREN || _la==ARG_ACTION_RPAREN || _la==ARG_ACTION_EQUALS || _la==ARG_ACTION_COMMA || _la==ARG_ACTION_ESCAPE || _la==ARG_ACTION_WORD || _la==ARG_ACTION_ELEMENT || _la==ARG_ACTION_TEXT || _interp.adaptivePredict(_input,25,_ctx) == 1 ) {
				{
				{
				setState(425);
				_input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ARG_ACTION_LT || _la==ARG_ACTION_GT || _la==ARG_ACTION_LPAREN || _la==ARG_ACTION_RPAREN || _la==ARG_ACTION_EQUALS || _la==ARG_ACTION_COMMA || _la==ARG_ACTION_ESCAPE || _la==ARG_ACTION_WORD || _la==ARG_ACTION_ELEMENT || _la==ARG_ACTION_TEXT) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(431);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(432); match(END_ARG_ACTION);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_argActionBlock);
			// System.out.println("exit "+ruleNames[RULE_argActionBlock]);
		}
		return _localctx;
	}

	public static class actionContext extends ParserRuleContext {
		public actionContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionContext action() throws RecognitionException {
		actionContext _localctx = new actionContext(_ctx, 30);
		enterRule(_localctx, RULE_action);
		//System.out.println("enter "+ruleNames[RULE_action]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(434); match(AT);
			setState(440);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(436); actionScopeName();
					setState(438); match(COLONCOLON);
					}
					break;
			}
			setState(442); id();
			setState(444); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_action);
			// System.out.println("exit "+ruleNames[RULE_action]);
		}
		return _localctx;
	}

	public static class actionScopeNameContext extends ParserRuleContext {
		public actionScopeNameContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionScopeNameContext actionScopeName() throws RecognitionException {
		actionScopeNameContext _localctx = new actionScopeNameContext(_ctx, 32);
		enterRule(_localctx, RULE_actionScopeName);
		//System.out.println("enter "+ruleNames[RULE_actionScopeName]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(452);
			_errHandler.sync(this);
			int alt27;
			switch ( _input.LA(1) ) {
			case TEMPLATE:
			case TOKEN_REF:
			case RULE_REF:
				alt27 = 1;
				break;
			case LEXER:
				alt27 = 2;
				break;
			case PARSER:
				alt27 = 3;
				break;
			default:
				alt27 = _interp.adaptivePredict(_input,27,_ctx);
				break;
			}
			switch (alt27) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(446); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(448); match(LEXER);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(450); match(PARSER);
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_actionScopeName);
			// System.out.println("exit "+ruleNames[RULE_actionScopeName]);
		}
		return _localctx;
	}

	public static class mode_Context extends ParserRuleContext {
		public mode_Context(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final mode_Context mode_() throws RecognitionException {
		mode_Context _localctx = new mode_Context(_ctx, 34);
		enterRule(_localctx, RULE_mode_);
		//System.out.println("enter "+ruleNames[RULE_mode_]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(454); match(MODE);
			setState(456); id();
			setState(458); match(SEMI);
			setState(462); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(460); rule();
				}
				}
				setState(464); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			    if (!(_la==DOC_COMMENT || _la==FRAGMENT || _la==PROTECTED || _la==PUBLIC || _la==PRIVATE || _la==TEMPLATE || _la==TOKEN_REF || _la==RULE_REF) && _interp.adaptivePredict(_input,28,_ctx) != 1) {
			        break;
			    }
			} while (true);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_mode_);
			// System.out.println("exit "+ruleNames[RULE_mode_]);
		}
		return _localctx;
	}

	public static class rulesContext extends ParserRuleContext {
		public rulesContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rulesContext rules() throws RecognitionException {
		rulesContext _localctx = new rulesContext(_ctx, 36);
		enterRule(_localctx, RULE_rules);
		//System.out.println("enter "+ruleNames[RULE_rules]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(470);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==DOC_COMMENT || _la==FRAGMENT || _la==PROTECTED || _la==PUBLIC || _la==PRIVATE || _la==TEMPLATE || _la==TOKEN_REF || _la==RULE_REF || _interp.adaptivePredict(_input,29,_ctx) == 1 ) {
				{
				{
				setState(466); rule();
				}
				}
				setState(472);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rules);
			// System.out.println("exit "+ruleNames[RULE_rules]);
		}
		return _localctx;
	}

	public static class ruleContext extends ParserRuleContext {
		public idContext name;;
		public ruleContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleContext rule() throws RecognitionException {
		ruleContext _localctx = new ruleContext(_ctx, 38);
		enterRule(_localctx, RULE_rule);
		//System.out.println("enter "+ruleNames[RULE_rule]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==DOC_COMMENT || _interp.adaptivePredict(_input,30,_ctx) == 1 ) {
				{
				setState(473); match(DOC_COMMENT);
				}
			}

			setState(479);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==FRAGMENT || _la==PROTECTED || _la==PUBLIC || _la==PRIVATE || _interp.adaptivePredict(_input,31,_ctx) == 1 ) {
				{
				setState(477); ruleModifiers();
				}
			}

			setState(481); _localctx.name = id();
			setState(485);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==BEGIN_ARG_ACTION || _interp.adaptivePredict(_input,32,_ctx) == 1 ) {
				{
				setState(483); argActionBlock();
				}
			}

			setState(489);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==RETURNS || _interp.adaptivePredict(_input,33,_ctx) == 1 ) {
				{
				setState(487); ruleReturns();
				}
			}

			setState(493);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==THROWS || _interp.adaptivePredict(_input,34,_ctx) == 1 ) {
				{
				setState(491); throwsSpec();
				}
			}

			setState(497);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==LOCALS || _interp.adaptivePredict(_input,35,_ctx) == 1 ) {
				{
				setState(495); locals_();
				}
			}

			setState(499); rulePrequels();
			setState(501); match(COLON);
			setState(503); ruleBlock();
			setState(505); match(SEMI);
			setState(507); exceptionGroup();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rule);
			// System.out.println("exit "+ruleNames[RULE_rule]);
		}
		return _localctx;
	}

	public static class exceptionGroupContext extends ParserRuleContext {
		public exceptionGroupContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final exceptionGroupContext exceptionGroup() throws RecognitionException {
		exceptionGroupContext _localctx = new exceptionGroupContext(_ctx, 40);
		enterRule(_localctx, RULE_exceptionGroup);
		//System.out.println("enter "+ruleNames[RULE_exceptionGroup]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==CATCH || _interp.adaptivePredict(_input,36,_ctx) == 1 ) {
				{
				{
				setState(509); exceptionHandler();
				}
				}
				setState(515);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(518);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==FINALLY || _interp.adaptivePredict(_input,37,_ctx) == 1 ) {
				{
				setState(516); finallyClause();
				}
			}

			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_exceptionGroup);
			// System.out.println("exit "+ruleNames[RULE_exceptionGroup]);
		}
		return _localctx;
	}

	public static class exceptionHandlerContext extends ParserRuleContext {
		public exceptionHandlerContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final exceptionHandlerContext exceptionHandler() throws RecognitionException {
		exceptionHandlerContext _localctx = new exceptionHandlerContext(_ctx, 42);
		enterRule(_localctx, RULE_exceptionHandler);
		//System.out.println("enter "+ruleNames[RULE_exceptionHandler]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(520); match(CATCH);
			setState(522); argActionBlock();
			setState(524); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_exceptionHandler);
			// System.out.println("exit "+ruleNames[RULE_exceptionHandler]);
		}
		return _localctx;
	}

	public static class finallyClauseContext extends ParserRuleContext {
		public finallyClauseContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final finallyClauseContext finallyClause() throws RecognitionException {
		finallyClauseContext _localctx = new finallyClauseContext(_ctx, 44);
		enterRule(_localctx, RULE_finallyClause);
		//System.out.println("enter "+ruleNames[RULE_finallyClause]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(526); match(FINALLY);
			setState(528); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_finallyClause);
			// System.out.println("exit "+ruleNames[RULE_finallyClause]);
		}
		return _localctx;
	}

	public static class rulePrequelsContext extends ParserRuleContext {
		public rulePrequelsContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rulePrequelsContext rulePrequels() throws RecognitionException {
		rulePrequelsContext _localctx = new rulePrequelsContext(_ctx, 46);
		enterRule(_localctx, RULE_rulePrequels);
		//System.out.println("enter "+ruleNames[RULE_rulePrequels]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(534);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==OPTIONS || _la==AT || _interp.adaptivePredict(_input,38,_ctx) == 1 ) {
				{
				{
				setState(530); rulePrequel();
				}
				}
				setState(536);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rulePrequels);
			// System.out.println("exit "+ruleNames[RULE_rulePrequels]);
		}
		return _localctx;
	}

	public static class rulePrequelContext extends ParserRuleContext {
		public rulePrequelContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rulePrequelContext rulePrequel() throws RecognitionException {
		rulePrequelContext _localctx = new rulePrequelContext(_ctx, 48);
		enterRule(_localctx, RULE_rulePrequel);
		//System.out.println("enter "+ruleNames[RULE_rulePrequel]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(541);
			_errHandler.sync(this);
			int alt39;
			switch ( _input.LA(1) ) {
			case OPTIONS:
				alt39 = 1;
				break;
			case AT:
				alt39 = 2;
				break;
			default:
				alt39 = _interp.adaptivePredict(_input,39,_ctx);
				break;
			}
			switch (alt39) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(537); optionsSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(539); ruleAction();
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rulePrequel);
			// System.out.println("exit "+ruleNames[RULE_rulePrequel]);
		}
		return _localctx;
	}

	public static class ruleReturnsContext extends ParserRuleContext {
		public ruleReturnsContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleReturnsContext ruleReturns() throws RecognitionException {
		ruleReturnsContext _localctx = new ruleReturnsContext(_ctx, 50);
		enterRule(_localctx, RULE_ruleReturns);
		//System.out.println("enter "+ruleNames[RULE_ruleReturns]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543); match(RETURNS);
			setState(545); argActionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_ruleReturns);
			// System.out.println("exit "+ruleNames[RULE_ruleReturns]);
		}
		return _localctx;
	}

	public static class throwsSpecContext extends ParserRuleContext {
		public throwsSpecContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final throwsSpecContext throwsSpec() throws RecognitionException {
		throwsSpecContext _localctx = new throwsSpecContext(_ctx, 52);
		enterRule(_localctx, RULE_throwsSpec);
		//System.out.println("enter "+ruleNames[RULE_throwsSpec]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(547); match(THROWS);
			setState(549); qid();
			setState(557);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==COMMA || _interp.adaptivePredict(_input,40,_ctx) == 1 ) {
				{
				{
				setState(551); match(COMMA);
				setState(553); qid();
				}
				}
				setState(559);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_throwsSpec);
			// System.out.println("exit "+ruleNames[RULE_throwsSpec]);
		}
		return _localctx;
	}

	public static class locals_Context extends ParserRuleContext {
		public locals_Context(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final locals_Context locals_() throws RecognitionException {
		locals_Context _localctx = new locals_Context(_ctx, 54);
		enterRule(_localctx, RULE_locals_);
		//System.out.println("enter "+ruleNames[RULE_locals_]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(560); match(LOCALS);
			setState(562); argActionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_locals_);
			// System.out.println("exit "+ruleNames[RULE_locals_]);
		}
		return _localctx;
	}

	public static class ruleActionContext extends ParserRuleContext {
		public ruleActionContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleActionContext ruleAction() throws RecognitionException {
		ruleActionContext _localctx = new ruleActionContext(_ctx, 56);
		enterRule(_localctx, RULE_ruleAction);
		//System.out.println("enter "+ruleNames[RULE_ruleAction]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(564); match(AT);
			setState(566); id();
			setState(568); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_ruleAction);
			// System.out.println("exit "+ruleNames[RULE_ruleAction]);
		}
		return _localctx;
	}

	public static class ruleModifiersContext extends ParserRuleContext {
		public ruleModifiersContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleModifiersContext ruleModifiers() throws RecognitionException {
		ruleModifiersContext _localctx = new ruleModifiersContext(_ctx, 58);
		enterRule(_localctx, RULE_ruleModifiers);
		//System.out.println("enter "+ruleNames[RULE_ruleModifiers]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(572); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(570); ruleModifier();
				}
				}
				setState(574); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			    if (!(_la==FRAGMENT || _la==PROTECTED || _la==PUBLIC || _la==PRIVATE) && _interp.adaptivePredict(_input,41,_ctx) != 1) {
			        break;
			    }
			} while (true);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_ruleModifiers);
			// System.out.println("exit "+ruleNames[RULE_ruleModifiers]);
		}
		return _localctx;
	}

	public static class ruleModifierContext extends ParserRuleContext {
		public ruleModifierContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleModifierContext ruleModifier() throws RecognitionException {
		ruleModifierContext _localctx = new ruleModifierContext(_ctx, 60);
		enterRule(_localctx, RULE_ruleModifier);
		//System.out.println("enter "+ruleNames[RULE_ruleModifier]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(584);
			_errHandler.sync(this);
			int alt42;
			switch ( _input.LA(1) ) {
			case PUBLIC:
				alt42 = 1;
				break;
			case PRIVATE:
				alt42 = 2;
				break;
			case PROTECTED:
				alt42 = 3;
				break;
			case FRAGMENT:
				alt42 = 4;
				break;
			default:
				alt42 = _interp.adaptivePredict(_input,42,_ctx);
				break;
			}
			switch (alt42) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(576); match(PUBLIC);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(578); match(PRIVATE);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(580); match(PROTECTED);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(582); match(FRAGMENT);
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_ruleModifier);
			// System.out.println("exit "+ruleNames[RULE_ruleModifier]);
		}
		return _localctx;
	}

	public static class ruleBlockContext extends ParserRuleContext {
		public ruleBlockContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleBlockContext ruleBlock() throws RecognitionException {
		ruleBlockContext _localctx = new ruleBlockContext(_ctx, 62);
		enterRule(_localctx, RULE_ruleBlock);
		//System.out.println("enter "+ruleNames[RULE_ruleBlock]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(586); ruleAltList();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_ruleBlock);
			// System.out.println("exit "+ruleNames[RULE_ruleBlock]);
		}
		return _localctx;
	}

	public static class ruleAltListContext extends ParserRuleContext {
		public ruleAltListContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleAltListContext ruleAltList() throws RecognitionException {
		ruleAltListContext _localctx = new ruleAltListContext(_ctx, 64);
		enterRule(_localctx, RULE_ruleAltList);
		//System.out.println("enter "+ruleNames[RULE_ruleAltList]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(588); labeledAlt();
			setState(596);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==OR || _interp.adaptivePredict(_input,43,_ctx) == 1 ) {
				{
				{
				setState(590); match(OR);
				setState(592); labeledAlt();
				}
				}
				setState(598);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_ruleAltList);
			// System.out.println("exit "+ruleNames[RULE_ruleAltList]);
		}
		return _localctx;
	}

	public static class labeledAltContext extends ParserRuleContext {
		public labeledAltContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final labeledAltContext labeledAlt() throws RecognitionException {
		labeledAltContext _localctx = new labeledAltContext(_ctx, 66);
		enterRule(_localctx, RULE_labeledAlt);
		//System.out.println("enter "+ruleNames[RULE_labeledAlt]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(599); alternative();
			setState(605);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==POUND || _interp.adaptivePredict(_input,44,_ctx) == 1 ) {
				{
				setState(601); match(POUND);
				setState(603); id();
				}
			}

			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_labeledAlt);
			// System.out.println("exit "+ruleNames[RULE_labeledAlt]);
		}
		return _localctx;
	}

	public static class altListContext extends ParserRuleContext {
		public altListContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final altListContext altList() throws RecognitionException {
		altListContext _localctx = new altListContext(_ctx, 68);
		enterRule(_localctx, RULE_altList);
		//System.out.println("enter "+ruleNames[RULE_altList]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(607); alternative();
			setState(615);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==OR || _interp.adaptivePredict(_input,45,_ctx) == 1 ) {
				{
				{
				setState(609); match(OR);
				setState(611); alternative();
				}
				}
				setState(617);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_altList);
			// System.out.println("exit "+ruleNames[RULE_altList]);
		}
		return _localctx;
	}

	public static class alternativeContext extends ParserRuleContext {
		public alternativeContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final alternativeContext alternative() throws RecognitionException {
		alternativeContext _localctx = new alternativeContext(_ctx, 70);
		enterRule(_localctx, RULE_alternative);
		//System.out.println("enter "+ruleNames[RULE_alternative]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(630);
			_errHandler.sync(this);
			int alt47;
			switch ( _input.LA(1) ) {
			case BEGIN_ACTION:
			case TEMPLATE:
			case LPAREN:
			case DOT:
			case TREE_BEGIN:
			case NOT:
			case TOKEN_REF:
			case RULE_REF:
			case STRING_LITERAL:
				alt47 = 1;
				break;
			case RARROW:
				alt47 = 2;
				break;
			case EOF:
			case SEMI:
			case RPAREN:
			case OR:
			case POUND:
				alt47 = 3;
				break;
			default:
				alt47 = _interp.adaptivePredict(_input,47,_ctx);
				break;
			}
			switch (alt47) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(618); elements();
					setState(624);
					_errHandler.sync(this);
					int alt46;
					switch ( _input.LA(1) ) {
					case RARROW:
						alt46 = 1;
						break;
					case EOF:
					case SEMI:
					case RPAREN:
					case OR:
					case POUND:
						alt46 = 2;
						break;
					default:
						alt46 = _interp.adaptivePredict(_input,46,_ctx);
						break;
					}
					switch (alt46) {
						case 1:
							{
							setState(620); rewrite();
							}
							break;

						case 2:
							{
							}
							break;

					default:
						throw new NoViableAltException(this);
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(626); rewrite();
					}
					break;

				case 3:
					{
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_alternative);
			// System.out.println("exit "+ruleNames[RULE_alternative]);
		}
		return _localctx;
	}

	public static class elementsContext extends ParserRuleContext {
		public elementContext e;;
		public List<elementContext> e_list = new ArrayList<elementContext>();;
		public elementsContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final elementsContext elements() throws RecognitionException {
		elementsContext _localctx = new elementsContext(_ctx, 72);
		enterRule(_localctx, RULE_elements);
		//System.out.println("enter "+ruleNames[RULE_elements]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(634); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(632); _localctx.e = element();
				_localctx.e_list.add(_localctx.e);
				}
				}
				setState(636); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			    if (!(_la==BEGIN_ACTION || _la==TEMPLATE || _la==LPAREN || _la==DOT || _la==TREE_BEGIN || _la==NOT || _la==TOKEN_REF || _la==RULE_REF || _la==STRING_LITERAL) && _interp.adaptivePredict(_input,48,_ctx) != 1) {
			        break;
			    }
			} while (true);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_elements);
			// System.out.println("exit "+ruleNames[RULE_elements]);
		}
		return _localctx;
	}

	public static class elementContext extends ParserRuleContext {
		public elementContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final elementContext element() throws RecognitionException {
		elementContext _localctx = new elementContext(_ctx, 74);
		enterRule(_localctx, RULE_element);
		//System.out.println("enter "+ruleNames[RULE_element]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			setState(670);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,53,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(638); labeledElement();
					setState(644);
					_errHandler.sync(this);
					int alt49;
					switch ( _input.LA(1) ) {
					case QUESTION:
					case STAR:
					case PLUS:
						alt49 = 1;
						break;
					case EOF:
					case BEGIN_ACTION:
					case TEMPLATE:
					case SEMI:
					case LPAREN:
					case RPAREN:
					case OR:
					case DOT:
					case RARROW:
					case TREE_BEGIN:
					case POUND:
					case NOT:
					case TOKEN_REF:
					case RULE_REF:
					case STRING_LITERAL:
						alt49 = 2;
						break;
					default:
						alt49 = _interp.adaptivePredict(_input,49,_ctx);
						break;
					}
					switch (alt49) {
						case 1:
							{
							setState(640); ebnfSuffix();
							}
							break;

						case 2:
							{
							}
							break;

					default:
						throw new NoViableAltException(this);
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(646); atom();
					setState(652);
					_errHandler.sync(this);
					int alt50;
					switch ( _input.LA(1) ) {
					case QUESTION:
					case STAR:
					case PLUS:
						alt50 = 1;
						break;
					case EOF:
					case BEGIN_ACTION:
					case TEMPLATE:
					case SEMI:
					case LPAREN:
					case RPAREN:
					case OR:
					case DOT:
					case RARROW:
					case TREE_BEGIN:
					case POUND:
					case NOT:
					case TOKEN_REF:
					case RULE_REF:
					case STRING_LITERAL:
						alt50 = 2;
						break;
					default:
						alt50 = _interp.adaptivePredict(_input,50,_ctx);
						break;
					}
					switch (alt50) {
						case 1:
							{
							setState(648); ebnfSuffix();
							}
							break;

						case 2:
							{
							}
							break;

					default:
						throw new NoViableAltException(this);
					}
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(654); ebnf();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(656); actionBlock();
					setState(660);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==QUESTION || _interp.adaptivePredict(_input,51,_ctx) == 1 ) {
						{
						setState(658); match(QUESTION);
						}
					}

					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(662); treeSpec();
					setState(668);
					_errHandler.sync(this);
					int alt52;
					switch ( _input.LA(1) ) {
					case QUESTION:
					case STAR:
					case PLUS:
						alt52 = 1;
						break;
					case EOF:
					case BEGIN_ACTION:
					case TEMPLATE:
					case SEMI:
					case LPAREN:
					case RPAREN:
					case OR:
					case DOT:
					case RARROW:
					case TREE_BEGIN:
					case POUND:
					case NOT:
					case TOKEN_REF:
					case RULE_REF:
					case STRING_LITERAL:
						alt52 = 2;
						break;
					default:
						alt52 = _interp.adaptivePredict(_input,52,_ctx);
						break;
					}
					switch (alt52) {
						case 1:
							{
							setState(664); ebnfSuffix();
							}
							break;

						case 2:
							{
							}
							break;

					default:
						throw new NoViableAltException(this);
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
			exitRule(RULE_element);
			// System.out.println("exit "+ruleNames[RULE_element]);
		}
		return _localctx;
	}

	public static class labeledElementContext extends ParserRuleContext {
		public Token ass;;
		public Token op;;
		public labeledElementContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final labeledElementContext labeledElement() throws RecognitionException {
		labeledElementContext _localctx = new labeledElementContext(_ctx, 76);
		enterRule(_localctx, RULE_labeledElement);
		//System.out.println("enter "+ruleNames[RULE_labeledElement]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(672); id();
			setState(678);
			_errHandler.sync(this);
			int alt54;
			switch ( _input.LA(1) ) {
			case ASSIGN:
				alt54 = 1;
				break;
			case PLUS_ASSIGN:
				alt54 = 2;
				break;
			default:
				alt54 = _interp.adaptivePredict(_input,54,_ctx);
				break;
			}
			switch (alt54) {
				case 1:
					{
					setState(674); _localctx.ass = match(ASSIGN);
					}
					break;

				case 2:
					{
					setState(676); _localctx.ass = match(PLUS_ASSIGN);
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			setState(690);
			_errHandler.sync(this);
			int alt56;
			switch ( _input.LA(1) ) {
			case DOT:
			case NOT:
			case TOKEN_REF:
			case RULE_REF:
			case STRING_LITERAL:
				alt56 = 1;
				break;
			case LPAREN:
				alt56 = 2;
				break;
			default:
				alt56 = _interp.adaptivePredict(_input,56,_ctx);
				break;
			}
			switch (alt56) {
				case 1:
					{
					setState(680); atom();
					}
					break;

				case 2:
					{
					setState(682); block();
					setState(688);
					_errHandler.sync(this);
					int alt55;
					switch ( _input.LA(1) ) {
						case ROOT:
							alt55 = 1;
							break;
						case BANG:
							alt55 = 2;
							break;
						case EOF:
						case BEGIN_ACTION:
						case TEMPLATE:
						case SEMI:
						case LPAREN:
						case RPAREN:
						case QUESTION:
						case STAR:
						case PLUS:
						case OR:
						case DOT:
						case RARROW:
						case TREE_BEGIN:
						case POUND:
						case NOT:
						case TOKEN_REF:
						case RULE_REF:
						case STRING_LITERAL:
							alt55 = 3;
							break;
						default :
							alt55 = _interp.adaptivePredict(_input,55,_ctx);
							break;
					}
					switch (alt55) {
						case 1:
							{
							setState(684); _localctx.op = match(ROOT);
							}
							break;

						case 2:
							{
							setState(686); _localctx.op = match(BANG);
							}
							break;
					}
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_labeledElement);
			// System.out.println("exit "+ruleNames[RULE_labeledElement]);
		}
		return _localctx;
	}

	public static class treeSpecContext extends ParserRuleContext {
		public Token begin;;
		public elementContext root;;
		public elementContext kids;;
		public List<elementContext> kids_list = new ArrayList<elementContext>();;
		public treeSpecContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final treeSpecContext treeSpec() throws RecognitionException {
		treeSpecContext _localctx = new treeSpecContext(_ctx, 78);
		enterRule(_localctx, RULE_treeSpec);
		//System.out.println("enter "+ruleNames[RULE_treeSpec]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(692); _localctx.begin = match(TREE_BEGIN);
			setState(694); _localctx.root = element();
			setState(698); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(696); _localctx.kids = element();
				_localctx.kids_list.add(_localctx.kids);
				}
				}
				setState(700); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			    if (!(_la==BEGIN_ACTION || _la==TEMPLATE || _la==LPAREN || _la==DOT || _la==TREE_BEGIN || _la==NOT || _la==TOKEN_REF || _la==RULE_REF || _la==STRING_LITERAL) && _interp.adaptivePredict(_input,57,_ctx) != 1) {
			        break;
			    }
			} while (true);
			setState(702); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_treeSpec);
			// System.out.println("exit "+ruleNames[RULE_treeSpec]);
		}
		return _localctx;
	}

	public static class ebnfContext extends ParserRuleContext {
		public ebnfContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ebnfContext ebnf() throws RecognitionException {
		ebnfContext _localctx = new ebnfContext(_ctx, 80);
		enterRule(_localctx, RULE_ebnf);
		//System.out.println("enter "+ruleNames[RULE_ebnf]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(704); block();
			setState(710);
			_errHandler.sync(this);
			int alt58;
			switch ( _input.LA(1) ) {
			case QUESTION:
			case BANG:
			case STAR:
			case PLUS:
			case ROOT:
				alt58 = 1;
				break;
			case EOF:
			case BEGIN_ACTION:
			case TEMPLATE:
			case SEMI:
			case LPAREN:
			case RPAREN:
			case OR:
			case DOT:
			case RARROW:
			case TREE_BEGIN:
			case POUND:
			case NOT:
			case TOKEN_REF:
			case RULE_REF:
			case STRING_LITERAL:
				alt58 = 2;
				break;
			default:
				alt58 = _interp.adaptivePredict(_input,58,_ctx);
				break;
			}
			switch (alt58) {
				case 1:
					{
					setState(706); blockSuffix();
					}
					break;

				case 2:
					{
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_ebnf);
			// System.out.println("exit "+ruleNames[RULE_ebnf]);
		}
		return _localctx;
	}

	public static class blockSuffixContext extends ParserRuleContext {
		public blockSuffixContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final blockSuffixContext blockSuffix() throws RecognitionException {
		blockSuffixContext _localctx = new blockSuffixContext(_ctx, 82);
		enterRule(_localctx, RULE_blockSuffix);
		//System.out.println("enter "+ruleNames[RULE_blockSuffix]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(718);
			_errHandler.sync(this);
			int alt59;
			switch ( _input.LA(1) ) {
			case QUESTION:
			case STAR:
			case PLUS:
				alt59 = 1;
				break;
			case ROOT:
				alt59 = 2;
				break;
			case BANG:
				alt59 = 3;
				break;
			default:
				alt59 = _interp.adaptivePredict(_input,59,_ctx);
				break;
			}
			switch (alt59) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(712); ebnfSuffix();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(714); match(ROOT);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(716); match(BANG);
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_blockSuffix);
			// System.out.println("exit "+ruleNames[RULE_blockSuffix]);
		}
		return _localctx;
	}

	public static class ebnfSuffixContext extends ParserRuleContext {
		public ebnfSuffixContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ebnfSuffixContext ebnfSuffix() throws RecognitionException {
		ebnfSuffixContext _localctx = new ebnfSuffixContext(_ctx, 84);
		enterRule(_localctx, RULE_ebnfSuffix);
		//System.out.println("enter "+ruleNames[RULE_ebnfSuffix]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(726);
			_errHandler.sync(this);
			int alt60;
			switch ( _input.LA(1) ) {
			case QUESTION:
				alt60 = 1;
				break;
			case STAR:
				alt60 = 2;
				break;
			case PLUS:
				alt60 = 3;
				break;
			default:
				alt60 = _interp.adaptivePredict(_input,60,_ctx);
				break;
			}
			switch (alt60) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(720); match(QUESTION);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(722); match(STAR);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(724); match(PLUS);
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_ebnfSuffix);
			// System.out.println("exit "+ruleNames[RULE_ebnfSuffix]);
		}
		return _localctx;
	}

	public static class atomContext extends ParserRuleContext {
		public Token astop;;
		public atomContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final atomContext atom() throws RecognitionException {
		atomContext _localctx = new atomContext(_ctx, 86);
		enterRule(_localctx, RULE_atom);
		//System.out.println("enter "+ruleNames[RULE_atom]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			setState(760);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,66,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(728); range();
					setState(732);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==BANG || _la==ROOT || _interp.adaptivePredict(_input,61,_ctx) == 1 ) {
						{
						setState(730);
						_input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==BANG || _la==ROOT) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						}
					}

					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(734); terminal();
					setState(738);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==BANG || _la==ROOT || _interp.adaptivePredict(_input,62,_ctx) == 1 ) {
						{
						setState(736);
						_input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==BANG || _la==ROOT) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						}
					}

					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(740); ruleref();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(742); notSet();
					setState(746);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==BANG || _la==ROOT || _interp.adaptivePredict(_input,63,_ctx) == 1 ) {
						{
						setState(744);
						_input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==BANG || _la==ROOT) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						}
					}

					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(748); match(DOT);
					setState(752);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==LT || _interp.adaptivePredict(_input,64,_ctx) == 1 ) {
						{
						setState(750); elementOptions();
						}
					}

					setState(758);
					_errHandler.sync(this);
					int alt65;
					switch ( _input.LA(1) ) {
						case ROOT:
							alt65 = 1;
							break;
						case BANG:
							alt65 = 2;
							break;
						case EOF:
						case BEGIN_ACTION:
						case TEMPLATE:
						case SEMI:
						case LPAREN:
						case RPAREN:
						case QUESTION:
						case STAR:
						case PLUS:
						case OR:
						case DOT:
						case RARROW:
						case TREE_BEGIN:
						case POUND:
						case NOT:
						case TOKEN_REF:
						case RULE_REF:
						case STRING_LITERAL:
							alt65 = 3;
							break;
						default :
							alt65 = _interp.adaptivePredict(_input,65,_ctx);
							break;
					}
					switch (alt65) {
						case 1:
							{
							setState(754); _localctx.astop = match(ROOT);
							}
							break;

						case 2:
							{
							setState(756); _localctx.astop = match(BANG);
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
			exitRule(RULE_atom);
			// System.out.println("exit "+ruleNames[RULE_atom]);
		}
		return _localctx;
	}

	public static class notSetContext extends ParserRuleContext {
		public notSetContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final notSetContext notSet() throws RecognitionException {
		notSetContext _localctx = new notSetContext(_ctx, 88);
		enterRule(_localctx, RULE_notSet);
		//System.out.println("enter "+ruleNames[RULE_notSet]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(770);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,67,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(762); match(NOT);
					setState(764); setElement();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(766); match(NOT);
					setState(768); blockSet();
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
			exitRule(RULE_notSet);
			// System.out.println("exit "+ruleNames[RULE_notSet]);
		}
		return _localctx;
	}

	public static class blockSetContext extends ParserRuleContext {
		public blockSetContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final blockSetContext blockSet() throws RecognitionException {
		blockSetContext _localctx = new blockSetContext(_ctx, 90);
		enterRule(_localctx, RULE_blockSet);
		//System.out.println("enter "+ruleNames[RULE_blockSet]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(772); match(LPAREN);
			setState(774); setElement();
			setState(782);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==OR || _interp.adaptivePredict(_input,68,_ctx) == 1 ) {
				{
				{
				setState(776); match(OR);
				setState(778); setElement();
				}
				}
				setState(784);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(785); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_blockSet);
			// System.out.println("exit "+ruleNames[RULE_blockSet]);
		}
		return _localctx;
	}

	public static class setElementContext extends ParserRuleContext {
		public setElementContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final setElementContext setElement() throws RecognitionException {
		setElementContext _localctx = new setElementContext(_ctx, 92);
		enterRule(_localctx, RULE_setElement);
		//System.out.println("enter "+ruleNames[RULE_setElement]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(793);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,69,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(787); match(TOKEN_REF);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(789); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(791); range();
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
			exitRule(RULE_setElement);
			// System.out.println("exit "+ruleNames[RULE_setElement]);
		}
		return _localctx;
	}

	public static class blockContext extends ParserRuleContext {
		public ruleActionContext ra;;
		public List<ruleActionContext> ra_list = new ArrayList<ruleActionContext>();;
		public blockContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final blockContext block() throws RecognitionException {
		blockContext _localctx = new blockContext(_ctx, 94);
		enterRule(_localctx, RULE_block);
		//System.out.println("enter "+ruleNames[RULE_block]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(795); match(LPAREN);
			setState(810);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==OPTIONS || _la==COLON || _la==AT || _interp.adaptivePredict(_input,72,_ctx) == 1 ) {
				{
				setState(799);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ( _la==OPTIONS || _interp.adaptivePredict(_input,70,_ctx) == 1 ) {
					{
					setState(797); optionsSpec();
					}
				}

				setState(805);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ( _la==AT || _interp.adaptivePredict(_input,71,_ctx) == 1 ) {
					{
					{
					setState(801); _localctx.ra = ruleAction();
					_localctx.ra_list.add(_localctx.ra);
					}
					}
					setState(807);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(808); match(COLON);
				}
			}

			setState(812); altList();
			setState(814); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_block);
			// System.out.println("exit "+ruleNames[RULE_block]);
		}
		return _localctx;
	}

	public static class rulerefContext extends ParserRuleContext {
		public Token op;;
		public rulerefContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rulerefContext ruleref() throws RecognitionException {
		rulerefContext _localctx = new rulerefContext(_ctx, 96);
		enterRule(_localctx, RULE_ruleref);
		//System.out.println("enter "+ruleNames[RULE_ruleref]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(816); match(RULE_REF);
			setState(820);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==BEGIN_ARG_ACTION || _interp.adaptivePredict(_input,73,_ctx) == 1 ) {
				{
				setState(818); argActionBlock();
				}
			}

			setState(830);
			_errHandler.sync(this);
			int alt75;
			switch ( _input.LA(1) ) {
			case BANG:
			case ROOT:
				alt75 = 1;
				break;
			case EOF:
			case BEGIN_ACTION:
			case TEMPLATE:
			case SEMI:
			case LPAREN:
			case RPAREN:
			case QUESTION:
			case STAR:
			case PLUS:
			case OR:
			case DOT:
			case RARROW:
			case TREE_BEGIN:
			case POUND:
			case NOT:
			case TOKEN_REF:
			case RULE_REF:
			case STRING_LITERAL:
				alt75 = 2;
				break;
			default:
				alt75 = _interp.adaptivePredict(_input,75,_ctx);
				break;
			}
			switch (alt75) {
				case 1:
					{
					setState(826);
					_errHandler.sync(this);
					int alt74;
					switch ( _input.LA(1) ) {
					case ROOT:
						alt74 = 1;
						break;
					case BANG:
						alt74 = 2;
						break;
					default:
						alt74 = _interp.adaptivePredict(_input,74,_ctx);
						break;
					}
					switch (alt74) {
						case 1:
							{
							setState(822); _localctx.op = match(ROOT);
							}
							break;

						case 2:
							{
							setState(824); _localctx.op = match(BANG);
							}
							break;

					default:
						throw new NoViableAltException(this);
					}
					}
					break;

				case 2:
					{
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_ruleref);
			// System.out.println("exit "+ruleNames[RULE_ruleref]);
		}
		return _localctx;
	}

	public static class rangeContext extends ParserRuleContext {
		public rangeContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rangeContext range() throws RecognitionException {
		rangeContext _localctx = new rangeContext(_ctx, 98);
		enterRule(_localctx, RULE_range);
		//System.out.println("enter "+ruleNames[RULE_range]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(832); match(STRING_LITERAL);
			setState(834); match(RANGE);
			setState(836); match(STRING_LITERAL);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_range);
			// System.out.println("exit "+ruleNames[RULE_range]);
		}
		return _localctx;
	}

	public static class terminalContext extends ParserRuleContext {
		public terminalContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final terminalContext terminal() throws RecognitionException {
		terminalContext _localctx = new terminalContext(_ctx, 100);
		enterRule(_localctx, RULE_terminal);
		//System.out.println("enter "+ruleNames[RULE_terminal]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			setState(850);
			_errHandler.sync(this);
			int alt78;
			switch ( _input.LA(1) ) {
			case TOKEN_REF:
				alt78 = 1;
				break;
			case STRING_LITERAL:
				alt78 = 2;
				break;
			default:
				alt78 = _interp.adaptivePredict(_input,78,_ctx);
				break;
			}
			switch (alt78) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(838); match(TOKEN_REF);
					setState(842);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==LT || _interp.adaptivePredict(_input,76,_ctx) == 1 ) {
						{
						setState(840); elementOptions();
						}
					}

					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(844); match(STRING_LITERAL);
					setState(848);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==LT || _interp.adaptivePredict(_input,77,_ctx) == 1 ) {
						{
						setState(846); elementOptions();
						}
					}

					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_terminal);
			// System.out.println("exit "+ruleNames[RULE_terminal]);
		}
		return _localctx;
	}

	public static class elementOptionsContext extends ParserRuleContext {
		public elementOptionsContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final elementOptionsContext elementOptions() throws RecognitionException {
		elementOptionsContext _localctx = new elementOptionsContext(_ctx, 102);
		enterRule(_localctx, RULE_elementOptions);
		//System.out.println("enter "+ruleNames[RULE_elementOptions]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(852); match(LT);
			setState(854); elementOption();
			setState(862);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==COMMA || _interp.adaptivePredict(_input,79,_ctx) == 1 ) {
				{
				{
				setState(856); match(COMMA);
				setState(858); elementOption();
				}
				}
				setState(864);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(865); match(GT);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_elementOptions);
			// System.out.println("exit "+ruleNames[RULE_elementOptions]);
		}
		return _localctx;
	}

	public static class elementOptionContext extends ParserRuleContext {
		public elementOptionContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final elementOptionContext elementOption() throws RecognitionException {
		elementOptionContext _localctx = new elementOptionContext(_ctx, 104);
		enterRule(_localctx, RULE_elementOption);
		//System.out.println("enter "+ruleNames[RULE_elementOption]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(879);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,81,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(867); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(869); id();
					setState(871); match(ASSIGN);
					setState(877);
					_errHandler.sync(this);
					int alt80;
					switch ( _input.LA(1) ) {
					case TEMPLATE:
					case TOKEN_REF:
					case RULE_REF:
						alt80 = 1;
						break;
					case STRING_LITERAL:
						alt80 = 2;
						break;
					default:
						alt80 = _interp.adaptivePredict(_input,80,_ctx);
						break;
					}
					switch (alt80) {
						case 1:
							{
							setState(873); qid();
							}
							break;

						case 2:
							{
							setState(875); match(STRING_LITERAL);
							}
							break;

					default:
						throw new NoViableAltException(this);
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
			exitRule(RULE_elementOption);
			// System.out.println("exit "+ruleNames[RULE_elementOption]);
		}
		return _localctx;
	}

	public static class rewriteContext extends ParserRuleContext {
		public rewriteContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteContext rewrite() throws RecognitionException {
		rewriteContext _localctx = new rewriteContext(_ctx, 106);
		enterRule(_localctx, RULE_rewrite);
		//System.out.println("enter "+ruleNames[RULE_rewrite]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(885);
			_errHandler.sync(this);
			int _alt2282 = _interp.adaptivePredict(_input,82,_ctx);
			while ( _alt2282!=2 && _alt2282!=-1 ) {
				switch ( _alt2282 ) {
					case 1:
						{
						{
						setState(881); predicatedRewrite();
						}
						}
						break;
				}
				setState(887);
				_errHandler.sync(this);
				_alt2282 = _interp.adaptivePredict(_input,82,_ctx);
			}
			setState(888); nakedRewrite();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rewrite);
			// System.out.println("exit "+ruleNames[RULE_rewrite]);
		}
		return _localctx;
	}

	public static class predicatedRewriteContext extends ParserRuleContext {
		public predicatedRewriteContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final predicatedRewriteContext predicatedRewrite() throws RecognitionException {
		predicatedRewriteContext _localctx = new predicatedRewriteContext(_ctx, 108);
		enterRule(_localctx, RULE_predicatedRewrite);
		//System.out.println("enter "+ruleNames[RULE_predicatedRewrite]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(890); match(RARROW);
			setState(892); match(SEMPRED);
			setState(894); rewriteAlt();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_predicatedRewrite);
			// System.out.println("exit "+ruleNames[RULE_predicatedRewrite]);
		}
		return _localctx;
	}

	public static class nakedRewriteContext extends ParserRuleContext {
		public nakedRewriteContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final nakedRewriteContext nakedRewrite() throws RecognitionException {
		nakedRewriteContext _localctx = new nakedRewriteContext(_ctx, 110);
		enterRule(_localctx, RULE_nakedRewrite);
		//System.out.println("enter "+ruleNames[RULE_nakedRewrite]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(896); match(RARROW);
			setState(898); rewriteAlt();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_nakedRewrite);
			// System.out.println("exit "+ruleNames[RULE_nakedRewrite]);
		}
		return _localctx;
	}

	public static class rewriteAltContext extends ParserRuleContext {
		public boolean isTemplate;
		public rewriteAltContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteAltContext rewriteAlt() throws RecognitionException {
		rewriteAltContext _localctx = new rewriteAltContext(_ctx, 112);
		enterRule(_localctx, RULE_rewriteAlt);
		//System.out.println("enter "+ruleNames[RULE_rewriteAlt]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(908);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,83,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(900); rewriteTreeAlt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(902); rewriteTemplate();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(904); match(ETC);
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
			exitRule(RULE_rewriteAlt);
			// System.out.println("exit "+ruleNames[RULE_rewriteAlt]);
		}
		return _localctx;
	}

	public static class rewriteTreeAltContext extends ParserRuleContext {
		public rewriteTreeAltContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTreeAltContext rewriteTreeAlt() throws RecognitionException {
		rewriteTreeAltContext _localctx = new rewriteTreeAltContext(_ctx, 114);
		enterRule(_localctx, RULE_rewriteTreeAlt);
		//System.out.println("enter "+ruleNames[RULE_rewriteTreeAlt]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(912); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(910); rewriteTreeElement();
				}
				}
				setState(914); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			    if (!(_la==BEGIN_ACTION || _la==LPAREN || _la==DOLLAR || _la==TREE_BEGIN || _la==TOKEN_REF || _la==RULE_REF || _la==STRING_LITERAL) && _interp.adaptivePredict(_input,84,_ctx) != 1) {
			        break;
			    }
			} while (true);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rewriteTreeAlt);
			// System.out.println("exit "+ruleNames[RULE_rewriteTreeAlt]);
		}
		return _localctx;
	}

	public static class rewriteTreeElementContext extends ParserRuleContext {
		public rewriteTreeElementContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTreeElementContext rewriteTreeElement() throws RecognitionException {
		rewriteTreeElementContext _localctx = new rewriteTreeElementContext(_ctx, 116);
		enterRule(_localctx, RULE_rewriteTreeElement);
		//System.out.println("enter "+ruleNames[RULE_rewriteTreeElement]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			setState(932);
			_errHandler.sync(this);
			int alt87;
			switch ( _input.LA(1) ) {
			case BEGIN_ACTION:
			case DOLLAR:
			case TOKEN_REF:
			case RULE_REF:
			case STRING_LITERAL:
				alt87 = 1;
				break;
			case TREE_BEGIN:
				alt87 = 2;
				break;
			case LPAREN:
				alt87 = 3;
				break;
			default:
				alt87 = _interp.adaptivePredict(_input,87,_ctx);
				break;
			}
			switch (alt87) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(916); rewriteTreeAtom();
					setState(920);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==QUESTION || _la==STAR || _la==PLUS || _interp.adaptivePredict(_input,85,_ctx) == 1 ) {
						{
						setState(918); ebnfSuffix();
						}
					}

					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(922); rewriteTree();
					setState(928);
					_errHandler.sync(this);
					int alt86;
					switch ( _input.LA(1) ) {
					case QUESTION:
					case STAR:
					case PLUS:
						alt86 = 1;
						break;
					case EOF:
					case BEGIN_ACTION:
					case SEMI:
					case LPAREN:
					case RPAREN:
					case OR:
					case DOLLAR:
					case RARROW:
					case TREE_BEGIN:
					case POUND:
					case TOKEN_REF:
					case RULE_REF:
					case STRING_LITERAL:
						alt86 = 2;
						break;
					default:
						alt86 = _interp.adaptivePredict(_input,86,_ctx);
						break;
					}
					switch (alt86) {
						case 1:
							{
							setState(924); ebnfSuffix();
							}
							break;

						case 2:
							{
							}
							break;

					default:
						throw new NoViableAltException(this);
					}
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(930); rewriteTreeEbnf();
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rewriteTreeElement);
			// System.out.println("exit "+ruleNames[RULE_rewriteTreeElement]);
		}
		return _localctx;
	}

	public static class rewriteTreeAtomContext extends ParserRuleContext {
		public rewriteTreeAtomContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTreeAtomContext rewriteTreeAtom() throws RecognitionException {
		rewriteTreeAtomContext _localctx = new rewriteTreeAtomContext(_ctx, 118);
		enterRule(_localctx, RULE_rewriteTreeAtom);
		//System.out.println("enter "+ruleNames[RULE_rewriteTreeAtom]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			setState(958);
			_errHandler.sync(this);
			int alt91;
			switch ( _input.LA(1) ) {
			case TOKEN_REF:
				alt91 = 1;
				break;
			case RULE_REF:
				alt91 = 2;
				break;
			case STRING_LITERAL:
				alt91 = 3;
				break;
			case DOLLAR:
				alt91 = 4;
				break;
			case BEGIN_ACTION:
				alt91 = 5;
				break;
			default:
				alt91 = _interp.adaptivePredict(_input,91,_ctx);
				break;
			}
			switch (alt91) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(934); match(TOKEN_REF);
					setState(938);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==LT || _interp.adaptivePredict(_input,88,_ctx) == 1 ) {
						{
						setState(936); elementOptions();
						}
					}

					setState(942);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==BEGIN_ARG_ACTION || _interp.adaptivePredict(_input,89,_ctx) == 1 ) {
						{
						setState(940); argActionBlock();
						}
					}

					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(944); match(RULE_REF);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(946); match(STRING_LITERAL);
					setState(950);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==LT || _interp.adaptivePredict(_input,90,_ctx) == 1 ) {
						{
						setState(948); elementOptions();
						}
					}

					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(952); match(DOLLAR);
					setState(954); id();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(956); actionBlock();
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rewriteTreeAtom);
			// System.out.println("exit "+ruleNames[RULE_rewriteTreeAtom]);
		}
		return _localctx;
	}

	public static class rewriteTreeEbnfContext extends ParserRuleContext {
		public Token lp;;
		public rewriteTreeEbnfContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTreeEbnfContext rewriteTreeEbnf() throws RecognitionException {
		rewriteTreeEbnfContext _localctx = new rewriteTreeEbnfContext(_ctx, 120);
		enterRule(_localctx, RULE_rewriteTreeEbnf);
		//System.out.println("enter "+ruleNames[RULE_rewriteTreeEbnf]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(960); _localctx.lp = match(LPAREN);
			setState(962); rewriteTreeAlt();
			setState(964); match(RPAREN);
			setState(966); rewriteEbnfSuffix();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rewriteTreeEbnf);
			// System.out.println("exit "+ruleNames[RULE_rewriteTreeEbnf]);
		}
		return _localctx;
	}

	public static class rewriteEbnfSuffixContext extends ParserRuleContext {
		public rewriteEbnfSuffixContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteEbnfSuffixContext rewriteEbnfSuffix() throws RecognitionException {
		rewriteEbnfSuffixContext _localctx = new rewriteEbnfSuffixContext(_ctx, 122);
		enterRule(_localctx, RULE_rewriteEbnfSuffix);
		//System.out.println("enter "+ruleNames[RULE_rewriteEbnfSuffix]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(972);
			_errHandler.sync(this);
			int alt92;
			switch ( _input.LA(1) ) {
			case QUESTION:
				alt92 = 1;
				break;
			case STAR:
				alt92 = 2;
				break;
			default:
				alt92 = _interp.adaptivePredict(_input,92,_ctx);
				break;
			}
			switch (alt92) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(968); match(QUESTION);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(970); match(STAR);
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rewriteEbnfSuffix);
			// System.out.println("exit "+ruleNames[RULE_rewriteEbnfSuffix]);
		}
		return _localctx;
	}

	public static class rewriteTreeContext extends ParserRuleContext {
		public rewriteTreeContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTreeContext rewriteTree() throws RecognitionException {
		rewriteTreeContext _localctx = new rewriteTreeContext(_ctx, 124);
		enterRule(_localctx, RULE_rewriteTree);
		//System.out.println("enter "+ruleNames[RULE_rewriteTree]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(974); match(TREE_BEGIN);
			setState(976); rewriteTreeAtom();
			setState(982);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==BEGIN_ACTION || _la==LPAREN || _la==DOLLAR || _la==TREE_BEGIN || _la==TOKEN_REF || _la==RULE_REF || _la==STRING_LITERAL || _interp.adaptivePredict(_input,93,_ctx) == 1 ) {
				{
				{
				setState(978); rewriteTreeElement();
				}
				}
				setState(984);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(985); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rewriteTree);
			// System.out.println("exit "+ruleNames[RULE_rewriteTree]);
		}
		return _localctx;
	}

	public static class rewriteTemplateContext extends ParserRuleContext {
		public Token str;;
		public rewriteTemplateContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTemplateContext rewriteTemplate() throws RecognitionException {
		rewriteTemplateContext _localctx = new rewriteTemplateContext(_ctx, 126);
		enterRule(_localctx, RULE_rewriteTemplate);
		//System.out.println("enter "+ruleNames[RULE_rewriteTemplate]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(1007);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,95,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(987); match(TEMPLATE);
					setState(989); match(LPAREN);
					setState(991); rewriteTemplateArgs();
					setState(993); match(RPAREN);
					setState(999);
					_errHandler.sync(this);
					int alt94;
					switch ( _input.LA(1) ) {
					case DOUBLE_QUOTE_STRING_LITERAL:
						alt94 = 1;
						break;
					case DOUBLE_ANGLE_STRING_LITERAL:
						alt94 = 2;
						break;
					default:
						alt94 = _interp.adaptivePredict(_input,94,_ctx);
						break;
					}
					switch (alt94) {
						case 1:
							{
							setState(995); _localctx.str = match(DOUBLE_QUOTE_STRING_LITERAL);
							}
							break;

						case 2:
							{
							setState(997); _localctx.str = match(DOUBLE_ANGLE_STRING_LITERAL);
							}
							break;

					default:
						throw new NoViableAltException(this);
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1001); rewriteTemplateRef();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1003); rewriteIndirectTemplateHead();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(1005); actionBlock();
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
			exitRule(RULE_rewriteTemplate);
			// System.out.println("exit "+ruleNames[RULE_rewriteTemplate]);
		}
		return _localctx;
	}

	public static class rewriteTemplateRefContext extends ParserRuleContext {
		public rewriteTemplateRefContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTemplateRefContext rewriteTemplateRef() throws RecognitionException {
		rewriteTemplateRefContext _localctx = new rewriteTemplateRefContext(_ctx, 128);
		enterRule(_localctx, RULE_rewriteTemplateRef);
		//System.out.println("enter "+ruleNames[RULE_rewriteTemplateRef]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1009); id();
			setState(1011); match(LPAREN);
			setState(1013); rewriteTemplateArgs();
			setState(1015); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rewriteTemplateRef);
			// System.out.println("exit "+ruleNames[RULE_rewriteTemplateRef]);
		}
		return _localctx;
	}

	public static class rewriteIndirectTemplateHeadContext extends ParserRuleContext {
		public Token lp;;
		public rewriteIndirectTemplateHeadContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteIndirectTemplateHeadContext rewriteIndirectTemplateHead() throws RecognitionException {
		rewriteIndirectTemplateHeadContext _localctx = new rewriteIndirectTemplateHeadContext(_ctx, 130);
		enterRule(_localctx, RULE_rewriteIndirectTemplateHead);
		//System.out.println("enter "+ruleNames[RULE_rewriteIndirectTemplateHead]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1017); _localctx.lp = match(LPAREN);
			setState(1019); actionBlock();
			setState(1021); match(RPAREN);
			setState(1023); match(LPAREN);
			setState(1025); rewriteTemplateArgs();
			setState(1027); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rewriteIndirectTemplateHead);
			// System.out.println("exit "+ruleNames[RULE_rewriteIndirectTemplateHead]);
		}
		return _localctx;
	}

	public static class rewriteTemplateArgsContext extends ParserRuleContext {
		public rewriteTemplateArgsContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTemplateArgsContext rewriteTemplateArgs() throws RecognitionException {
		rewriteTemplateArgsContext _localctx = new rewriteTemplateArgsContext(_ctx, 132);
		enterRule(_localctx, RULE_rewriteTemplateArgs);
		//System.out.println("enter "+ruleNames[RULE_rewriteTemplateArgs]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			setState(1042);
			_errHandler.sync(this);
			int alt97;
			switch ( _input.LA(1) ) {
			case TEMPLATE:
			case TOKEN_REF:
			case RULE_REF:
				alt97 = 1;
				break;
			case RPAREN:
				alt97 = 2;
				break;
			default:
				alt97 = _interp.adaptivePredict(_input,97,_ctx);
				break;
			}
			switch (alt97) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1029); rewriteTemplateArg();
					setState(1037);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ( _la==COMMA || _interp.adaptivePredict(_input,96,_ctx) == 1 ) {
						{
						{
						setState(1031); match(COMMA);
						setState(1033); rewriteTemplateArg();
						}
						}
						setState(1039);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;

				case 2:
					{
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rewriteTemplateArgs);
			// System.out.println("exit "+ruleNames[RULE_rewriteTemplateArgs]);
		}
		return _localctx;
	}

	public static class rewriteTemplateArgContext extends ParserRuleContext {
		public rewriteTemplateArgContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTemplateArgContext rewriteTemplateArg() throws RecognitionException {
		rewriteTemplateArgContext _localctx = new rewriteTemplateArgContext(_ctx, 134);
		enterRule(_localctx, RULE_rewriteTemplateArg);
		//System.out.println("enter "+ruleNames[RULE_rewriteTemplateArg]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1044); id();
			setState(1046); match(ASSIGN);
			setState(1048); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_rewriteTemplateArg);
			// System.out.println("exit "+ruleNames[RULE_rewriteTemplateArg]);
		}
		return _localctx;
	}

	public static class idContext extends ParserRuleContext {
		public idContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final idContext id() throws RecognitionException {
		idContext _localctx = new idContext(_ctx, 136);
		enterRule(_localctx, RULE_id);
		//System.out.println("enter "+ruleNames[RULE_id]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(1056);
			_errHandler.sync(this);
			int alt98;
			switch ( _input.LA(1) ) {
			case RULE_REF:
				alt98 = 1;
				break;
			case TOKEN_REF:
				alt98 = 2;
				break;
			case TEMPLATE:
				alt98 = 3;
				break;
			default:
				alt98 = _interp.adaptivePredict(_input,98,_ctx);
				break;
			}
			switch (alt98) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1050); match(RULE_REF);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1052); match(TOKEN_REF);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1054); match(TEMPLATE);
					}
					break;

			default:
				throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_id);
			// System.out.println("exit "+ruleNames[RULE_id]);
		}
		return _localctx;
	}

	public static class qidContext extends ParserRuleContext {
		public qidContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final qidContext qid() throws RecognitionException {
		qidContext _localctx = new qidContext(_ctx, 138);
		enterRule(_localctx, RULE_qid);
		//System.out.println("enter "+ruleNames[RULE_qid]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1058); id();
			setState(1066);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==DOT || _interp.adaptivePredict(_input,99,_ctx) == 1 ) {
				{
				{
				setState(1060); match(DOT);
				setState(1062); id();
				}
				}
				setState(1068);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_qid);
			// System.out.println("exit "+ruleNames[RULE_qid]);
		}
		return _localctx;
	}

	public static class alternativeEntryContext extends ParserRuleContext {
		public alternativeEntryContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final alternativeEntryContext alternativeEntry() throws RecognitionException {
		alternativeEntryContext _localctx = new alternativeEntryContext(_ctx, 140);
		enterRule(_localctx, RULE_alternativeEntry);
		//System.out.println("enter "+ruleNames[RULE_alternativeEntry]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1069); alternative();
			setState(1071); match(EOF);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_alternativeEntry);
			// System.out.println("exit "+ruleNames[RULE_alternativeEntry]);
		}
		return _localctx;
	}

	public static class elementEntryContext extends ParserRuleContext {
		public elementEntryContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final elementEntryContext elementEntry() throws RecognitionException {
		elementEntryContext _localctx = new elementEntryContext(_ctx, 142);
		enterRule(_localctx, RULE_elementEntry);
		//System.out.println("enter "+ruleNames[RULE_elementEntry]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1073); element();
			setState(1075); match(EOF);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_elementEntry);
			// System.out.println("exit "+ruleNames[RULE_elementEntry]);
		}
		return _localctx;
	}

	public static class ruleEntryContext extends ParserRuleContext {
		public ruleEntryContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleEntryContext ruleEntry() throws RecognitionException {
		ruleEntryContext _localctx = new ruleEntryContext(_ctx, 144);
		enterRule(_localctx, RULE_ruleEntry);
		//System.out.println("enter "+ruleNames[RULE_ruleEntry]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1077); rule();
			setState(1079); match(EOF);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_ruleEntry);
			// System.out.println("exit "+ruleNames[RULE_ruleEntry]);
		}
		return _localctx;
	}

	public static class blockEntryContext extends ParserRuleContext {
		public blockEntryContext(RuleContext parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final blockEntryContext blockEntry() throws RecognitionException {
		blockEntryContext _localctx = new blockEntryContext(_ctx, 146);
		enterRule(_localctx, RULE_blockEntry);
		//System.out.println("enter "+ruleNames[RULE_blockEntry]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1081); block();
			setState(1083); match(EOF);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule(RULE_blockEntry);
			// System.out.println("exit "+ruleNames[RULE_blockEntry]);
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
		"\01\176\u043e\02\00\07\00\02\01\07\01\02\02\07\02\02\03\07\03\02\04\07"+
	  "\04\02\05\07\05\02\06\07\06\02\07\07\07\02\010\07\010\02\011\07\011\02"+
	  "\012\07\012\02\013\07\013\02\014\07\014\02\015\07\015\02\016\07\016\02"+
	  "\017\07\017\02\020\07\020\02\021\07\021\02\022\07\022\02\023\07\023\02"+
	  "\024\07\024\02\025\07\025\02\026\07\026\02\027\07\027\02\030\07\030\02"+
	  "\031\07\031\02\032\07\032\02\033\07\033\02\034\07\034\02\035\07\035\02"+
	  "\036\07\036\02\037\07\037\02\040\07\040\02\041\07\041\02\042\07\042\02"+
	  "\043\07\043\02\044\07\044\02\045\07\045\02\046\07\046\02\047\07\047\02"+
	  "\050\07\050\02\051\07\051\02\052\07\052\02\053\07\053\02\054\07\054\02"+
	  "\055\07\055\02\056\07\056\02\057\07\057\02\060\07\060\02\061\07\061\02"+
	  "\062\07\062\02\063\07\063\02\064\07\064\02\065\07\065\02\066\07\066\02"+
	  "\067\07\067\02\070\07\070\02\071\07\071\02\072\07\072\02\073\07\073\02"+
	  "\074\07\074\02\075\07\075\02\076\07\076\02\077\07\077\02\100\07\100\02"+
	  "\101\07\101\02\102\07\102\02\103\07\103\02\104\07\104\02\105\07\105\02"+
	  "\106\07\106\02\107\07\107\02\110\07\110\02\111\07\111\01\00\01\00\03"+
	  "\00\010\00\01\00\01\00\01\00\01\00\01\00\01\00\01\00\01\00\05\00\010"+
	  "\00\012\00\01\00\011\00\01\00\01\00\01\00\01\00\05\00\010\00\012\00\01"+
	  "\00\011\00\01\00\01\00\01\01\01\01\01\01\01\01\01\01\01\01\01\01\01\01"+
	  "\01\01\01\01\01\01\01\01\01\01\01\01\03\01\010\01\01\02\01\02\01\02\01"+
	  "\02\01\02\01\02\01\02\01\02\03\02\010\02\01\03\01\03\01\03\01\03\01\03"+
	  "\01\03\05\03\010\03\012\03\01\03\011\03\01\03\01\03\01\04\01\04\01\04"+
	  "\01\04\01\04\01\04\01\05\01\05\01\05\01\05\01\05\01\05\01\05\01\05\03"+
	  "\05\010\05\01\06\01\06\01\06\01\06\01\06\01\06\01\06\01\06\05\06\010"+
	  "\06\012\06\01\06\011\06\01\06\01\06\01\07\01\07\01\07\01\07\01\07\01"+
	  "\07\01\07\01\07\03\07\010\07\01\010\01\010\01\010\01\010\04\010\010\010"+
	  "\013\010\01\010\01\010\01\010\01\011\01\011\01\011\01\011\01\011\01\011"+
	  "\01\011\01\011\03\011\010\011\01\011\01\011\01\011\01\011\03\011\010"+
	  "\011\01\012\01\012\01\012\01\012\01\012\01\012\01\012\01\012\01\012\01"+
	  "\012\01\012\01\012\01\012\01\012\01\012\01\012\01\012\01\012\01\012\01"+
	  "\012\01\012\01\012\01\012\01\012\01\012\01\012\01\012\01\012\01\012\01"+
	  "\012\01\012\01\012\01\012\01\012\01\012\01\012\01\012\01\012\01\012\01"+
	  "\012\01\012\01\012\01\012\01\012\01\012\01\012\05\012\010\012\012\012"+
	  "\01\012\011\012\01\012\01\012\01\013\01\013\01\013\01\013\05\013\010"+
	  "\013\012\013\01\013\011\013\01\013\01\013\01\013\01\013\05\013\010\013"+
	  "\012\013\01\013\011\013\01\013\01\013\01\014\01\014\01\014\01\014\05"+
	  "\014\010\014\012\014\01\014\011\014\01\014\01\014\01\014\01\014\05\014"+
	  "\010\014\012\014\01\014\011\014\01\014\01\014\01\014\01\014\05\014\010"+
	  "\014\012\014\01\014\011\014\03\014\010\014\01\014\01\014\01\014\01\014"+
	  "\05\014\010\014\012\014\01\014\011\014\01\014\01\014\01\014\01\014\05"+
	  "\014\010\014\012\014\01\014\011\014\03\014\010\014\01\014\01\014\01\014"+
	  "\01\014\05\014\010\014\012\014\01\014\011\014\01\014\01\014\01\015\01"+
	  "\015\01\015\01\015\01\015\01\015\03\015\010\015\01\016\01\016\01\016"+
	  "\01\016\05\016\010\016\012\016\01\016\011\016\01\016\01\016\01\017\01"+
	  "\017\01\017\01\017\01\017\01\017\03\017\010\017\01\017\01\017\01\017"+
	  "\01\017\01\020\01\020\01\020\01\020\01\020\01\020\03\020\010\020\01\021"+
	  "\01\021\01\021\01\021\01\021\01\021\01\021\01\021\04\021\010\021\013"+
	  "\021\01\021\01\022\01\022\05\022\010\022\012\022\01\022\011\022\01\023"+
	  "\01\023\03\023\010\023\01\023\01\023\03\023\010\023\01\023\01\023\01"+
	  "\023\01\023\03\023\010\023\01\023\01\023\03\023\010\023\01\023\01\023"+
	  "\03\023\010\023\01\023\01\023\03\023\010\023\01\023\01\023\01\023\01"+
	  "\023\01\023\01\023\01\023\01\023\01\023\01\023\01\024\01\024\05\024\010"+
	  "\024\012\024\01\024\011\024\01\024\01\024\03\024\010\024\01\025\01\025"+
	  "\01\025\01\025\01\025\01\025\01\026\01\026\01\026\01\026\01\027\01\027"+
	  "\05\027\010\027\012\027\01\027\011\027\01\030\01\030\01\030\01\030\03"+
	  "\030\010\030\01\031\01\031\01\031\01\031\01\032\01\032\01\032\01\032"+
	  "\01\032\01\032\01\032\01\032\05\032\010\032\012\032\01\032\011\032\01"+
	  "\033\01\033\01\033\01\033\01\034\01\034\01\034\01\034\01\034\01\034\01"+
	  "\035\01\035\04\035\010\035\013\035\01\035\01\036\01\036\01\036\01\036"+
	  "\01\036\01\036\01\036\01\036\03\036\010\036\01\037\01\037\01\040\01\040"+
	  "\01\040\01\040\01\040\01\040\05\040\010\040\012\040\01\040\011\040\01"+
	  "\041\01\041\01\041\01\041\01\041\01\041\03\041\010\041\01\042\01\042"+
	  "\01\042\01\042\01\042\01\042\05\042\010\042\012\042\01\042\011\042\01"+
	  "\043\01\043\01\043\01\043\01\043\01\043\03\043\010\043\01\043\01\043"+
	  "\01\043\01\043\03\043\010\043\01\044\01\044\04\044\010\044\013\044\01"+
	  "\044\01\045\01\045\01\045\01\045\01\045\01\045\03\045\010\045\01\045"+
	  "\01\045\01\045\01\045\01\045\01\045\03\045\010\045\01\045\01\045\01\045"+
	  "\01\045\01\045\01\045\03\045\010\045\01\045\01\045\01\045\01\045\01\045"+
	  "\01\045\03\045\010\045\03\045\010\045\01\046\01\046\01\046\01\046\01"+
	  "\046\01\046\03\046\010\046\01\046\01\046\01\046\01\046\01\046\01\046"+
	  "\01\046\01\046\03\046\010\046\03\046\010\046\01\047\01\047\01\047\01"+
	  "\047\01\047\01\047\04\047\010\047\013\047\01\047\01\047\01\047\01\050"+
	  "\01\050\01\050\01\050\01\050\01\050\03\050\010\050\01\051\01\051\01\051"+
	  "\01\051\01\051\01\051\03\051\010\051\01\052\01\052\01\052\01\052\01\052"+
	  "\01\052\03\052\010\052\01\053\01\053\01\053\01\053\03\053\010\053\01"+
	  "\053\01\053\01\053\01\053\03\053\010\053\01\053\01\053\01\053\01\053"+
	  "\01\053\01\053\03\053\010\053\01\053\01\053\01\053\01\053\03\053\010"+
	  "\053\01\053\01\053\01\053\01\053\03\053\010\053\03\053\010\053\01\054"+
	  "\01\054\01\054\01\054\01\054\01\054\01\054\01\054\03\054\010\054\01\055"+
	  "\01\055\01\055\01\055\01\055\01\055\01\055\01\055\05\055\010\055\012"+
	  "\055\01\055\011\055\01\055\01\055\01\056\01\056\01\056\01\056\01\056"+
	  "\01\056\03\056\010\056\01\057\01\057\01\057\01\057\03\057\010\057\01"+
	  "\057\01\057\05\057\010\057\012\057\01\057\011\057\01\057\01\057\03\057"+
	  "\010\057\01\057\01\057\01\057\01\057\01\060\01\060\01\060\01\060\03\060"+
	  "\010\060\01\060\01\060\01\060\01\060\03\060\010\060\01\060\01\060\03"+
	  "\060\010\060\01\061\01\061\01\061\01\061\01\061\01\061\01\062\01\062"+
	  "\01\062\01\062\03\062\010\062\01\062\01\062\01\062\01\062\03\062\010"+
	  "\062\03\062\010\062\01\063\01\063\01\063\01\063\01\063\01\063\01\063"+
	  "\01\063\05\063\010\063\012\063\01\063\011\063\01\063\01\063\01\064\01"+
	  "\064\01\064\01\064\01\064\01\064\01\064\01\064\01\064\01\064\03\064\010"+
	  "\064\03\064\010\064\01\065\01\065\05\065\010\065\012\065\01\065\011\065"+
	  "\01\065\01\065\01\066\01\066\01\066\01\066\01\066\01\066\01\067\01\067"+
	  "\01\067\01\067\01\070\01\070\01\070\01\070\01\070\01\070\01\070\01\070"+
	  "\03\070\010\070\01\071\01\071\04\071\010\071\013\071\01\071\01\072\01"+
	  "\072\01\072\01\072\03\072\010\072\01\072\01\072\01\072\01\072\01\072"+
	  "\01\072\03\072\010\072\01\072\01\072\03\072\010\072\01\073\01\073\01"+
	  "\073\01\073\03\073\010\073\01\073\01\073\03\073\010\073\01\073\01\073"+
	  "\01\073\01\073\01\073\01\073\03\073\010\073\01\073\01\073\01\073\01\073"+
	  "\01\073\01\073\03\073\010\073\01\074\01\074\01\074\01\074\01\074\01\074"+
	  "\01\074\01\074\01\075\01\075\01\075\01\075\03\075\010\075\01\076\01\076"+
	  "\01\076\01\076\01\076\01\076\05\076\010\076\012\076\01\076\011\076\01"+
	  "\076\01\076\01\077\01\077\01\077\01\077\01\077\01\077\01\077\01\077\01"+
	  "\077\01\077\01\077\01\077\03\077\010\077\01\077\01\077\01\077\01\077"+
	  "\01\077\01\077\03\077\010\077\01\100\01\100\01\100\01\100\01\100\01\100"+
	  "\01\100\01\100\01\101\01\101\01\101\01\101\01\101\01\101\01\101\01\101"+
	  "\01\101\01\101\01\101\01\101\01\102\01\102\01\102\01\102\01\102\01\102"+
	  "\05\102\010\102\012\102\01\102\011\102\01\102\01\102\03\102\010\102\01"+
	  "\103\01\103\01\103\01\103\01\103\01\103\01\104\01\104\01\104\01\104\01"+
	  "\104\01\104\03\104\010\104\01\105\01\105\01\105\01\105\01\105\01\105"+
	  "\05\105\010\105\012\105\01\105\011\105\01\106\01\106\01\106\01\106\01"+
	  "\107\01\107\01\107\01\107\01\110\01\110\01\110\01\110\01\111\01\111\01"+
	  "\111\01\111\01\111\112\00\00\00\02\00\00\04\00\00\06\00\00\010\00\00"+
	  "\012\00\00\014\00\00\016\00\00\020\00\00\022\00\00\024\00\00\026\00\00"+
	  "\030\00\00\032\00\00\034\00\00\036\00\00\040\00\00\042\00\00\044\00\00"+
	  "\046\00\00\050\00\00\052\00\00\054\00\00\056\00\00\060\00\00\062\00\00"+
	  "\064\00\00\066\00\00\070\00\00\072\00\00\074\00\00\076\00\00\100\00\00"+
	  "\102\00\00\104\00\00\106\00\00\110\00\00\112\00\00\114\00\00\116\00\00"+
	  "\120\00\00\122\00\00\124\00\00\126\00\00\130\00\00\132\00\00\134\00\00"+
	  "\136\00\00\140\00\00\142\00\00\144\00\00\146\00\00\150\00\00\152\00\00"+
	  "\154\00\00\156\00\00\160\00\00\162\00\00\164\00\00\166\00\00\170\00\00"+
	  "\172\00\00\174\00\00\176\00\00\u0080\00\00\u0082\00\00\u0084\00\00\u0086"+
	  "\00\00\u0088\00\00\u008a\00\00\u008c\00\00\u008e\00\00\u0090\00\00\u0092"+
	  "\00\00\00\04\01\076\107\02\050\050\055\055\02\050\050\055\055\02\050"+
	  "\050\055\055\u0525\00\u0096\01\00\00\00\01\u043d\05\uffff\00\00\02\u00be"+
	  "\01\00\00\00\03\u0099\01\00\00\00\04\u00c8\01\00\00\00\05\u009f\01\00"+
	  "\00\00\06\u00ca\01\00\00\00\07\u00c1\01\00\00\00\07\u021a\01\00\00\00"+
	  "\07\u031e\01\00\00\00\010\u00d7\01\00\00\00\011\u00cd\01\00\00\00\012"+
	  "\u00e5\01\00\00\00\013\u00dc\01\00\00\00\014\u00e7\01\00\00\00\015\u00c3"+
	  "\01\00\00\00\016\u00fe\01\00\00\00\017\u00ea\01\00\00\00\017\u00ee\01"+
	  "\00\00\00\020\u0100\01\00\00\00\021\u00c5\01\00\00\00\022\u0118\01\00"+
	  "\00\00\023\u0103\01\00\00\00\024\u011a\01\00\00\00\025\u011d\01\00\00"+
	  "\00\025\u01bd\01\00\00\00\025\u020d\01\00\00\00\025\u0211\01\00\00\00"+
	  "\025\u0239\01\00\00\00\025\u0291\01\00\00\00\025\u03bd\01\00\00\00\025"+
	  "\u03ee\01\00\00\00\025\u03fc\01\00\00\00\025\u0419\01\00\00\00\026\u014f"+
	  "\01\00\00\00\027\u011f\01\00\00\00\030\u0163\01\00\00\00\031\u0121\01"+
	  "\00\00\00\032\u01a5\01\00\00\00\033\u0123\01\00\00\00\033\u0152\01\00"+
	  "\00\00\033\u015b\01\00\00\00\033\u0166\01\00\00\00\033\u016f\01\00\00"+
	  "\00\033\u0178\01\00\00\00\033\u0183\01\00\00\00\033\u018c\01\00\00\00"+
	  "\033\u0197\01\00\00\00\034\u01a7\01\00\00\00\035\u01e4\01\00\00\00\035"+
	  "\u020b\01\00\00\00\035\u0222\01\00\00\00\035\u0233\01\00\00\00\035\u0333"+
	  "\01\00\00\00\035\u03ad\01\00\00\00\036\u01b2\01\00\00\00\037\u00c7\01"+
	  "\00\00\00\040\u01c4\01\00\00\00\041\u01b5\01\00\00\00\042\u01c6\01\00"+
	  "\00\00\043\u00a8\01\00\00\00\044\u01d6\01\00\00\00\045\u00a6\01\00\00"+
	  "\00\046\u01db\01\00\00\00\047\u01cd\01\00\00\00\047\u01d3\01\00\00\00"+
	  "\047\u0436\01\00\00\00\050\u0201\01\00\00\00\051\u01fc\01\00\00\00\052"+
	  "\u0208\01\00\00\00\053\u01fe\01\00\00\00\054\u020e\01\00\00\00\055\u0205"+
	  "\01\00\00\00\056\u0216\01\00\00\00\057\u01f4\01\00\00\00\060\u021d\01"+
	  "\00\00\00\061\u0213\01\00\00\00\062\u021f\01\00\00\00\063\u01e8\01\00"+
	  "\00\00\064\u0223\01\00\00\00\065\u01ec\01\00\00\00\066\u0230\01\00\00"+
	  "\00\067\u01f0\01\00\00\00\070\u0234\01\00\00\00\071\u021c\01\00\00\00"+
	  "\071\u0322\01\00\00\00\072\u023c\01\00\00\00\073\u01de\01\00\00\00\074"+
	  "\u0248\01\00\00\00\075\u023b\01\00\00\00\076\u024a\01\00\00\00\077\u01f8"+
	  "\01\00\00\00\100\u024c\01\00\00\00\101\u024b\01\00\00\00\102\u0257\01"+
	  "\00\00\00\103\u024d\01\00\00\00\103\u0251\01\00\00\00\104\u025f\01\00"+
	  "\00\00\105\u032d\01\00\00\00\106\u0276\01\00\00\00\107\u0258\01\00\00"+
	  "\00\107\u0260\01\00\00\00\107\u0264\01\00\00\00\107\u042e\01\00\00\00"+
	  "\110\u027a\01\00\00\00\111\u026b\01\00\00\00\112\u029e\01\00\00\00\113"+
	  "\u0279\01\00\00\00\113\u02b7\01\00\00\00\113\u02b9\01\00\00\00\113\u0432"+
	  "\01\00\00\00\114\u02a0\01\00\00\00\115\u027f\01\00\00\00\116\u02b4\01"+
	  "\00\00\00\117\u0297\01\00\00\00\120\u02c0\01\00\00\00\121\u028f\01\00"+
	  "\00\00\122\u02ce\01\00\00\00\123\u02c3\01\00\00\00\124\u02d6\01\00\00"+
	  "\00\125\u0281\01\00\00\00\125\u0289\01\00\00\00\125\u0299\01\00\00\00"+
	  "\125\u02c9\01\00\00\00\125\u0397\01\00\00\00\125\u039d\01\00\00\00\126"+
	  "\u02f8\01\00\00\00\127\u0287\01\00\00\00\127\u02a9\01\00\00\00\130\u0302"+
	  "\01\00\00\00\131\u02e7\01\00\00\00\132\u0304\01\00\00\00\133\u0301\01"+
	  "\00\00\00\134\u0319\01\00\00\00\135\u02fd\01\00\00\00\135\u0307\01\00"+
	  "\00\00\135\u030b\01\00\00\00\136\u031b\01\00\00\00\137\u02ab\01\00\00"+
	  "\00\137\u02c1\01\00\00\00\137\u043a\01\00\00\00\140\u0330\01\00\00\00"+
	  "\141\u02e5\01\00\00\00\142\u0340\01\00\00\00\143\u02d9\01\00\00\00\143"+
	  "\u0318\01\00\00\00\144\u0352\01\00\00\00\145\u02df\01\00\00\00\146\u0354"+
	  "\01\00\00\00\147\u02ef\01\00\00\00\147\u0349\01\00\00\00\147\u034f\01"+
	  "\00\00\00\147\u03a9\01\00\00\00\147\u03b5\01\00\00\00\150\u036f\01\00"+
	  "\00\00\151\u0357\01\00\00\00\151\u035b\01\00\00\00\152\u0375\01\00\00"+
	  "\00\153\u026d\01\00\00\00\153\u0273\01\00\00\00\154\u037a\01\00\00\00"+
	  "\155\u0372\01\00\00\00\156\u0380\01\00\00\00\157\u0379\01\00\00\00\160"+
	  "\u038c\01\00\00\00\161\u037f\01\00\00\00\161\u0383\01\00\00\00\162\u0390"+
	  "\01\00\00\00\163\u0385\01\00\00\00\163\u03c3\01\00\00\00\164\u03a4\01"+
	  "\00\00\00\165\u038f\01\00\00\00\165\u03d3\01\00\00\00\166\u03be\01\00"+
	  "\00\00\167\u0395\01\00\00\00\167\u03d1\01\00\00\00\170\u03c0\01\00\00"+
	  "\00\171\u03a3\01\00\00\00\172\u03cc\01\00\00\00\173\u03c7\01\00\00\00"+
	  "\174\u03ce\01\00\00\00\175\u039b\01\00\00\00\176\u03ef\01\00\00\00\177"+
	  "\u0387\01\00\00\00\u0080\u03f1\01\00\00\00\u0081\u03ea\01\00\00\00\u0082"+
	  "\u03f9\01\00\00\00\u0083\u03ec\01\00\00\00\u0084\u0412\01\00\00\00\u0085"+
	  "\u03e0\01\00\00\00\u0085\u03f6\01\00\00\00\u0085\u0402\01\00\00\00\u0086"+
	  "\u0414\01\00\00\00\u0087\u0406\01\00\00\00\u0087\u040a\01\00\00\00\u0088"+
	  "\u0420\01\00\00\00\u0089\u009b\01\00\00\00\u0089\u00d8\01\00\00\00\u0089"+
	  "\u00f7\01\00\00\00\u0089\u00fb\01\00\00\00\u0089\u00fd\01\00\00\00\u0089"+
	  "\u010b\01\00\00\00\u0089\u01bb\01\00\00\00\u0089\u01bf\01\00\00\00\u0089"+
	  "\u01c9\01\00\00\00\u0089\u01e2\01\00\00\00\u0089\u0237\01\00\00\00\u0089"+
	  "\u025c\01\00\00\00\u0089\u02a1\01\00\00\00\u0089\u0366\01\00\00\00\u0089"+
	  "\u03bb\01\00\00\00\u0089\u03f2\01\00\00\00\u0089\u0415\01\00\00\00\u0089"+
	  "\u0423\01\00\00\00\u0089\u0427\01\00\00\00\u008a\u0422\01\00\00\00\u008b"+
	  "\u00de\01\00\00\00\u008b\u0226\01\00\00\00\u008b\u022a\01\00\00\00\u008b"+
	  "\u0364\01\00\00\00\u008b\u036a\01\00\00\00\u008c\u042d\01\00\00\00\u008d"+
	  "\u043d\05\uffff\00\00\u008e\u0431\01\00\00\00\u008f\u043d\05\uffff\00"+
	  "\00\u0090\u0435\01\00\00\00\u0091\u043d\05\uffff\00\00\u0092\u0439\01"+
	  "\00\00\00\u0093\u043d\05\uffff\00\00\u0094\u0095\05\03\00\00\u0095\u0097"+
	  "\01\00\00\00\u0096\u0094\01\00\00\00\u0096\u0097\01\00\00\00\u0097\u0098"+
	  "\01\00\00\00\u0098\u0099\03\02\01\00\u0099\u009a\01\00\00\00\u009a\u009b"+
	  "\03\u0088\104\00\u009b\u009c\01\00\00\00\u009c\u009d\05\040\00\00\u009d"+
	  "\u00a2\01\00\00\00\u009e\u009f\03\04\02\00\u009f\u00a1\01\00\00\00\u00a0"+
	  "\u009e\01\00\00\00\u00a1\u00a4\01\00\00\00\u00a2\u00a0\01\00\00\00\u00a2"+
	  "\u00a3\01\00\00\00\u00a3\u00a5\01\00\00\00\u00a4\u00a2\01\00\00\00\u00a5"+
	  "\u00a6\03\044\022\00\u00a6\u00ab\01\00\00\00\u00a7\u00a8\03\042\021\00"+
	  "\u00a8\u00aa\01\00\00\00\u00a9\u00a7\01\00\00\00\u00aa\u00ad\01\00\00"+
	  "\00\u00ab\u00a9\01\00\00\00\u00ab\u00ac\01\00\00\00\u00ac\u00ae\01\00"+
	  "\00\00\u00ad\u00ab\01\00\00\00\u00ae\u00af\05\uffff\00\00\u00af\01\01"+
	  "\00\00\00\u00b0\u00b1\05\017\00\00\u00b1\u00b2\01\00\00\00\u00b2\u00b3"+
	  "\05\022\00\00\u00b3\u00bf\01\00\00\00\u00b4\u00b5\05\020\00\00\u00b5"+
	  "\u00b6\01\00\00\00\u00b6\u00b7\05\022\00\00\u00b7\u00bf\01\00\00\00\u00b8"+
	  "\u00b9\05\021\00\00\u00b9\u00ba\01\00\00\00\u00ba\u00bb\05\022\00\00"+
	  "\u00bb\u00bf\01\00\00\00\u00bc\u00bd\05\022\00\00\u00bd\u00bf\01\00\00"+
	  "\00\u00be\u00b0\01\00\00\00\u00be\u00b4\01\00\00\00\u00be\u00b8\01\00"+
	  "\00\00\u00be\u00bc\01\00\00\00\u00bf\03\01\00\00\00\u00c0\u00c1\03\06"+
	  "\03\00\u00c1\u00c9\01\00\00\00\u00c2\u00c3\03\014\06\00\u00c3\u00c9\01"+
	  "\00\00\00\u00c4\u00c5\03\020\010\00\u00c5\u00c9\01\00\00\00\u00c6\u00c7"+
	  "\03\036\017\00\u00c7\u00c9\01\00\00\00\u00c8\u00c0\01\00\00\00\u00c8"+
	  "\u00c2\01\00\00\00\u00c8\u00c4\01\00\00\00\u00c8\u00c6\01\00\00\00\u00c9"+
	  "\05\01\00\00\00\u00ca\u00cb\05\012\00\00\u00cb\u00d2\01\00\00\00\u00cc"+
	  "\u00cd\03\010\04\00\u00cd\u00ce\01\00\00\00\u00ce\u00cf\05\040\00\00"+
	  "\u00cf\u00d1\01\00\00\00\u00d0\u00cc\01\00\00\00\u00d1\u00d4\01\00\00"+
	  "\00\u00d2\u00d0\01\00\00\00\u00d2\u00d3\01\00\00\00\u00d3\u00d5\01\00"+
	  "\00\00\u00d4\u00d2\01\00\00\00\u00d5\u00d6\05\067\00\00\u00d6\07\01\00"+
	  "\00\00\u00d7\u00d8\03\u0088\104\00\u00d8\u00d9\01\00\00\00\u00d9\u00da"+
	  "\05\046\00\00\u00da\u00db\01\00\00\00\u00db\u00dc\03\012\05\00\u00dc"+
	  "\011\01\00\00\00\u00dd\u00de\03\u008a\105\00\u00de\u00e6\01\00\00\00"+
	  "\u00df\u00e0\05\073\00\00\u00e0\u00e6\01\00\00\00\u00e1\u00e2\05\072"+
	  "\00\00\u00e2\u00e6\01\00\00\00\u00e3\u00e4\05\051\00\00\u00e4\u00e6\01"+
	  "\00\00\00\u00e5\u00dd\01\00\00\00\u00e5\u00df\01\00\00\00\u00e5\u00e1"+
	  "\01\00\00\00\u00e5\u00e3\01\00\00\00\u00e6\013\01\00\00\00\u00e7\u00e8"+
	  "\05\015\00\00\u00e8\u00e9\01\00\00\00\u00e9\u00ea\03\016\07\00\u00ea"+
	  "\u00f1\01\00\00\00\u00eb\u00ec\05\037\00\00\u00ec\u00ed\01\00\00\00\u00ed"+
	  "\u00ee\03\016\07\00\u00ee\u00f0\01\00\00\00\u00ef\u00eb\01\00\00\00\u00f0"+
	  "\u00f3\01\00\00\00\u00f1\u00ef\01\00\00\00\u00f1\u00f2\01\00\00\00\u00f2"+
	  "\u00f4\01\00\00\00\u00f3\u00f1\01\00\00\00\u00f4\u00f5\05\040\00\00\u00f5"+
	  "\015\01\00\00\00\u00f6\u00f7\03\u0088\104\00\u00f7\u00f8\01\00\00\00"+
	  "\u00f8\u00f9\05\046\00\00\u00f9\u00fa\01\00\00\00\u00fa\u00fb\03\u0088"+
	  "\104\00\u00fb\u00ff\01\00\00\00\u00fc\u00fd\03\u0088\104\00\u00fd\u00ff"+
	  "\01\00\00\00\u00fe\u00f6\01\00\00\00\u00fe\u00fc\01\00\00\00\u00ff\017"+
	  "\01\00\00\00\u0100\u0101\05\013\00\00\u0101\u0104\01\00\00\00\u0102\u0103"+
	  "\03\022\011\00\u0103\u0105\01\00\00\00\u0104\u0102\01\00\00\00\u0105"+
	  "\u0106\01\00\00\00\u0106\u0104\01\00\00\00\u0106\u0107\01\00\00\00\u0107"+
	  "\u0108\01\00\00\00\u0108\u0109\05\067\00\00\u0109\021\01\00\00\00\u010a"+
	  "\u010b\03\u0088\104\00\u010b\u0112\01\00\00\00\u010c\u010d\05\046\00"+
	  "\00\u010d\u010e\01\00\00\00\u010e\u010f\05\073\00\00\u010f\u0113\01\00"+
	  "\00\00\u0110\u0111\01\00\00\00\u0111\u0113\01\00\00\00\u0112\u010c\01"+
	  "\00\00\00\u0112\u0110\01\00\00\00\u0113\u0114\01\00\00\00\u0114\u0115"+
	  "\05\040\00\00\u0115\u0119\01\00\00\00\u0116\u0117\05\071\00\00\u0117"+
	  "\u0119\01\00\00\00\u0118\u010a\01\00\00\00\u0118\u0116\01\00\00\00\u0119"+
	  "\023\01\00\00\00\u011a\u011b\05\011\00\00\u011b\u014a\01\00\00\00\u011c"+
	  "\u011d\03\024\012\00\u011d\u0149\01\00\00\00\u011e\u011f\03\026\013\00"+
	  "\u011f\u0149\01\00\00\00\u0120\u0121\03\030\014\00\u0121\u0149\01\00"+
	  "\00\00\u0122\u0123\03\032\015\00\u0123\u0149\01\00\00\00\u0124\u0125"+
	  "\05\131\00\00\u0125\u0149\01\00\00\00\u0126\u0127\05\132\00\00\u0127"+
	  "\u0149\01\00\00\00\u0128\u0129\05\133\00\00\u0129\u0149\01\00\00\00\u012a"+
	  "\u012b\05\113\00\00\u012b\u0149\01\00\00\00\u012c\u012d\05\114\00\00"+
	  "\u012d\u0149\01\00\00\00\u012e\u012f\05\115\00\00\u012f\u0149\01\00\00"+
	  "\00\u0130\u0131\05\116\00\00\u0131\u0149\01\00\00\00\u0132\u0133\05\117"+
	  "\00\00\u0133\u0149\01\00\00\00\u0134\u0135\05\120\00\00\u0135\u0149\01"+
	  "\00\00\00\u0136\u0137\05\121\00\00\u0137\u0149\01\00\00\00\u0138\u0139"+
	  "\05\122\00\00\u0139\u0149\01\00\00\00\u013a\u013b\05\126\00\00\u013b"+
	  "\u0149\01\00\00\00\u013c\u013d\05\127\00\00\u013d\u0149\01\00\00\00\u013e"+
	  "\u013f\05\130\00\00\u013f\u0149\01\00\00\00\u0140\u0141\05\124\00\00"+
	  "\u0141\u0149\01\00\00\00\u0142\u0143\05\123\00\00\u0143\u0149\01\00\00"+
	  "\00\u0144\u0145\05\125\00\00\u0145\u0149\01\00\00\00\u0146\u0147\05\112"+
	  "\00\00\u0147\u0149\01\00\00\00\u0148\u011c\01\00\00\00\u0148\u011e\01"+
	  "\00\00\00\u0148\u0120\01\00\00\00\u0148\u0122\01\00\00\00\u0148\u0124"+
	  "\01\00\00\00\u0148\u0126\01\00\00\00\u0148\u0128\01\00\00\00\u0148\u012a"+
	  "\01\00\00\00\u0148\u012c\01\00\00\00\u0148\u012e\01\00\00\00\u0148\u0130"+
	  "\01\00\00\00\u0148\u0132\01\00\00\00\u0148\u0134\01\00\00\00\u0148\u0136"+
	  "\01\00\00\00\u0148\u0138\01\00\00\00\u0148\u013a\01\00\00\00\u0148\u013c"+
	  "\01\00\00\00\u0148\u013e\01\00\00\00\u0148\u0140\01\00\00\00\u0148\u0142"+
	  "\01\00\00\00\u0148\u0144\01\00\00\00\u0148\u0146\01\00\00\00\u0149\u014c"+
	  "\01\00\00\00\u014a\u0148\01\00\00\00\u014a\u014b\01\00\00\00\u014b\u014d"+
	  "\01\00\00\00\u014c\u014a\01\00\00\00\u014d\u014e\05\136\00\00\u014e\025"+
	  "\01\00\00\00\u014f\u0150\05\130\00\00\u0150\u0155\01\00\00\00\u0151\u0152"+
	  "\03\032\015\00\u0152\u0154\01\00\00\00\u0153\u0151\01\00\00\00\u0154"+
	  "\u0157\01\00\00\00\u0155\u0153\01\00\00\00\u0155\u0156\01\00\00\00\u0156"+
	  "\u0158\01\00\00\00\u0157\u0155\01\00\00\00\u0158\u0159\05\112\00\00\u0159"+
	  "\u015e\01\00\00\00\u015a\u015b\03\032\015\00\u015b\u015d\01\00\00\00"+
	  "\u015c\u015a\01\00\00\00\u015d\u0160\01\00\00\00\u015e\u015c\01\00\00"+
	  "\00\u015e\u015f\01\00\00\00\u015f\u0161\01\00\00\00\u0160\u015e\01\00"+
	  "\00\00\u0161\u0162\05\127\00\00\u0162\027\01\00\00\00\u0163\u0164\05"+
	  "\130\00\00\u0164\u0169\01\00\00\00\u0165\u0166\03\032\015\00\u0166\u0168"+
	  "\01\00\00\00\u0167\u0165\01\00\00\00\u0168\u016b\01\00\00\00\u0169\u0167"+
	  "\01\00\00\00\u0169\u016a\01\00\00\00\u016a\u0192\01\00\00\00\u016b\u0169"+
	  "\01\00\00\00\u016c\u016d\05\117\00\00\u016d\u0172\01\00\00\00\u016e\u016f"+
	  "\03\032\015\00\u016f\u0171\01\00\00\00\u0170\u016e\01\00\00\00\u0171"+
	  "\u0174\01\00\00\00\u0172\u0170\01\00\00\00\u0172\u0173\01\00\00\00\u0173"+
	  "\u017e\01\00\00\00\u0174\u0172\01\00\00\00\u0175\u0176\05\125\00\00\u0176"+
	  "\u017b\01\00\00\00\u0177\u0178\03\032\015\00\u0178\u017a\01\00\00\00"+
	  "\u0179\u0177\01\00\00\00\u017a\u017d\01\00\00\00\u017b\u0179\01\00\00"+
	  "\00\u017b\u017c\01\00\00\00\u017c\u017f\01\00\00\00\u017d\u017b\01\00"+
	  "\00\00\u017e\u0175\01\00\00\00\u017e\u017f\01\00\00\00\u017f\u0180\01"+
	  "\00\00\00\u0180\u0181\05\127\00\00\u0181\u0186\01\00\00\00\u0182\u0183"+
	  "\03\032\015\00\u0183\u0185\01\00\00\00\u0184\u0182\01\00\00\00\u0185"+
	  "\u0188\01\00\00\00\u0186\u0184\01\00\00\00\u0186\u0187\01\00\00\00\u0187"+
	  "\u0189\01\00\00\00\u0188\u0186\01\00\00\00\u0189\u018a\05\120\00\00\u018a"+
	  "\u018f\01\00\00\00\u018b\u018c\03\032\015\00\u018c\u018e\01\00\00\00"+
	  "\u018d\u018b\01\00\00\00\u018e\u0191\01\00\00\00\u018f\u018d\01\00\00"+
	  "\00\u018f\u0190\01\00\00\00\u0190\u0193\01\00\00\00\u0191\u018f\01\00"+
	  "\00\00\u0192\u016c\01\00\00\00\u0192\u0193\01\00\00\00\u0193\u0194\01"+
	  "\00\00\00\u0194\u0195\05\123\00\00\u0195\u019a\01\00\00\00\u0196\u0197"+
	  "\03\032\015\00\u0197\u0199\01\00\00\00\u0198\u0196\01\00\00\00\u0199"+
	  "\u019c\01\00\00\00\u019a\u0198\01\00\00\00\u019a\u019b\01\00\00\00\u019b"+
	  "\u019d\01\00\00\00\u019c\u019a\01\00\00\00\u019d\u019e\05\127\00\00\u019e"+
	  "\031\01\00\00\00\u019f\u01a0\05\134\00\00\u01a0\u01a6\01\00\00\00\u01a1"+
	  "\u01a2\05\135\00\00\u01a2\u01a6\01\00\00\00\u01a3\u01a4\05\131\00\00"+
	  "\u01a4\u01a6\01\00\00\00\u01a5\u019f\01\00\00\00\u01a5\u01a1\01\00\00"+
	  "\00\u01a5\u01a3\01\00\00\00\u01a6\033\01\00\00\00\u01a7\u01a8\05\010"+
	  "\00\00\u01a8\u01ad\01\00\00\00\u01a9\u01aa\07\00\00\00\u01aa\u01ac\01"+
	  "\00\00\00\u01ab\u01a9\01\00\00\00\u01ac\u01af\01\00\00\00\u01ad\u01ab"+
	  "\01\00\00\00\u01ad\u01ae\01\00\00\00\u01ae\u01b0\01\00\00\00\u01af\u01ad"+
	  "\01\00\00\00\u01b0\u01b1\05\110\00\00\u01b1\035\01\00\00\00\u01b2\u01b3"+
	  "\05\064\00\00\u01b3\u01b8\01\00\00\00\u01b4\u01b5\03\040\020\00\u01b5"+
	  "\u01b6\01\00\00\00\u01b6\u01b7\05\036\00\00\u01b7\u01b9\01\00\00\00\u01b8"+
	  "\u01b4\01\00\00\00\u01b8\u01b9\01\00\00\00\u01b9\u01ba\01\00\00\00\u01ba"+
	  "\u01bb\03\u0088\104\00\u01bb\u01bc\01\00\00\00\u01bc\u01bd\03\024\012"+
	  "\00\u01bd\037\01\00\00\00\u01be\u01bf\03\u0088\104\00\u01bf\u01c5\01"+
	  "\00\00\00\u01c0\u01c1\05\017\00\00\u01c1\u01c5\01\00\00\00\u01c2\u01c3"+
	  "\05\020\00\00\u01c3\u01c5\01\00\00\00\u01c4\u01be\01\00\00\00\u01c4\u01c0"+
	  "\01\00\00\00\u01c4\u01c2\01\00\00\00\u01c5\041\01\00\00\00\u01c6\u01c7"+
	  "\05\034\00\00\u01c7\u01c8\01\00\00\00\u01c8\u01c9\03\u0088\104\00\u01c9"+
	  "\u01ca\01\00\00\00\u01ca\u01cb\05\040\00\00\u01cb\u01ce\01\00\00\00\u01cc"+
	  "\u01cd\03\046\023\00\u01cd\u01cf\01\00\00\00\u01ce\u01cc\01\00\00\00"+
	  "\u01cf\u01d0\01\00\00\00\u01d0\u01ce\01\00\00\00\u01d0\u01d1\01\00\00"+
	  "\00\u01d1\043\01\00\00\00\u01d2\u01d3\03\046\023\00\u01d3\u01d5\01\00"+
	  "\00\00\u01d4\u01d2\01\00\00\00\u01d5\u01d8\01\00\00\00\u01d6\u01d4\01"+
	  "\00\00\00\u01d6\u01d7\01\00\00\00\u01d7\045\01\00\00\00\u01d8\u01d6\01"+
	  "\00\00\00\u01d9\u01da\05\03\00\00\u01da\u01dc\01\00\00\00\u01db\u01d9"+
	  "\01\00\00\00\u01db\u01dc\01\00\00\00\u01dc\u01df\01\00\00\00\u01dd\u01de"+
	  "\03\072\035\00\u01de\u01e0\01\00\00\00\u01df\u01dd\01\00\00\00\u01df"+
	  "\u01e0\01\00\00\00\u01e0\u01e1\01\00\00\00\u01e1\u01e2\03\u0088\104\00"+
	  "\u01e2\u01e5\01\00\00\00\u01e3\u01e4\03\034\016\00\u01e4\u01e6\01\00"+
	  "\00\00\u01e5\u01e3\01\00\00\00\u01e5\u01e6\01\00\00\00\u01e6\u01e9\01"+
	  "\00\00\00\u01e7\u01e8\03\062\031\00\u01e8\u01ea\01\00\00\00\u01e9\u01e7"+
	  "\01\00\00\00\u01e9\u01ea\01\00\00\00\u01ea\u01ed\01\00\00\00\u01eb\u01ec"+
	  "\03\064\032\00\u01ec\u01ee\01\00\00\00\u01ed\u01eb\01\00\00\00\u01ed"+
	  "\u01ee\01\00\00\00\u01ee\u01f1\01\00\00\00\u01ef\u01f0\03\066\033\00"+
	  "\u01f0\u01f2\01\00\00\00\u01f1\u01ef\01\00\00\00\u01f1\u01f2\01\00\00"+
	  "\00\u01f2\u01f3\01\00\00\00\u01f3\u01f4\03\056\027\00\u01f4\u01f5\01"+
	  "\00\00\00\u01f5\u01f6\05\035\00\00\u01f6\u01f7\01\00\00\00\u01f7\u01f8"+
	  "\03\076\037\00\u01f8\u01f9\01\00\00\00\u01f9\u01fa\05\040\00\00\u01fa"+
	  "\u01fb\01\00\00\00\u01fb\u01fc\03\050\024\00\u01fc\047\01\00\00\00\u01fd"+
	  "\u01fe\03\052\025\00\u01fe\u0200\01\00\00\00\u01ff\u01fd\01\00\00\00"+
	  "\u0200\u0203\01\00\00\00\u0201\u01ff\01\00\00\00\u0201\u0202\01\00\00"+
	  "\00\u0202\u0206\01\00\00\00\u0203\u0201\01\00\00\00\u0204\u0205\03\054"+
	  "\026\00\u0205\u0207\01\00\00\00\u0206\u0204\01\00\00\00\u0206\u0207\01"+
	  "\00\00\00\u0207\051\01\00\00\00\u0208\u0209\05\031\00\00\u0209\u020a"+
	  "\01\00\00\00\u020a\u020b\03\034\016\00\u020b\u020c\01\00\00\00\u020c"+
	  "\u020d\03\024\012\00\u020d\053\01\00\00\00\u020e\u020f\05\032\00\00\u020f"+
	  "\u0210\01\00\00\00\u0210\u0211\03\024\012\00\u0211\055\01\00\00\00\u0212"+
	  "\u0213\03\060\030\00\u0213\u0215\01\00\00\00\u0214\u0212\01\00\00\00"+
	  "\u0215\u0218\01\00\00\00\u0216\u0214\01\00\00\00\u0216\u0217\01\00\00"+
	  "\00\u0217\057\01\00\00\00\u0218\u0216\01\00\00\00\u0219\u021a\03\06\03"+
	  "\00\u021a\u021e\01\00\00\00\u021b\u021c\03\070\034\00\u021c\u021e\01"+
	  "\00\00\00\u021d\u0219\01\00\00\00\u021d\u021b\01\00\00\00\u021e\061\01"+
	  "\00\00\00\u021f\u0220\05\026\00\00\u0220\u0221\01\00\00\00\u0221\u0222"+
	  "\03\034\016\00\u0222\063\01\00\00\00\u0223\u0224\05\030\00\00\u0224\u0225"+
	  "\01\00\00\00\u0225\u0226\03\u008a\105\00\u0226\u022d\01\00\00\00\u0227"+
	  "\u0228\05\037\00\00\u0228\u0229\01\00\00\00\u0229\u022a\03\u008a\105"+
	  "\00\u022a\u022c\01\00\00\00\u022b\u0227\01\00\00\00\u022c\u022f\01\00"+
	  "\00\00\u022d\u022b\01\00\00\00\u022d\u022e\01\00\00\00\u022e\065\01\00"+
	  "\00\00\u022f\u022d\01\00\00\00\u0230\u0231\05\027\00\00\u0231\u0232\01"+
	  "\00\00\00\u0232\u0233\03\034\016\00\u0233\067\01\00\00\00\u0234\u0235"+
	  "\05\064\00\00\u0235\u0236\01\00\00\00\u0236\u0237\03\u0088\104\00\u0237"+
	  "\u0238\01\00\00\00\u0238\u0239\03\024\012\00\u0239\071\01\00\00\00\u023a"+
	  "\u023b\03\074\036\00\u023b\u023d\01\00\00\00\u023c\u023a\01\00\00\00"+
	  "\u023d\u023e\01\00\00\00\u023e\u023c\01\00\00\00\u023e\u023f\01\00\00"+
	  "\00\u023f\073\01\00\00\00\u0240\u0241\05\024\00\00\u0241\u0249\01\00"+
	  "\00\00\u0242\u0243\05\025\00\00\u0243\u0249\01\00\00\00\u0244\u0245\05"+
	  "\023\00\00\u0245\u0249\01\00\00\00\u0246\u0247\05\016\00\00\u0247\u0249"+
	  "\01\00\00\00\u0248\u0240\01\00\00\00\u0248\u0242\01\00\00\00\u0248\u0244"+
	  "\01\00\00\00\u0248\u0246\01\00\00\00\u0249\075\01\00\00\00\u024a\u024b"+
	  "\03\100\040\00\u024b\077\01\00\00\00\u024c\u024d\03\102\041\00\u024d"+
	  "\u0254\01\00\00\00\u024e\u024f\05\054\00\00\u024f\u0250\01\00\00\00\u0250"+
	  "\u0251\03\102\041\00\u0251\u0253\01\00\00\00\u0252\u024e\01\00\00\00"+
	  "\u0253\u0256\01\00\00\00\u0254\u0252\01\00\00\00\u0254\u0255\01\00\00"+
	  "\00\u0255\101\01\00\00\00\u0256\u0254\01\00\00\00\u0257\u0258\03\106"+
	  "\043\00\u0258\u025d\01\00\00\00\u0259\u025a\05\065\00\00\u025a\u025b"+
	  "\01\00\00\00\u025b\u025c\03\u0088\104\00\u025c\u025e\01\00\00\00\u025d"+
	  "\u0259\01\00\00\00\u025d\u025e\01\00\00\00\u025e\103\01\00\00\00\u025f"+
	  "\u0260\03\106\043\00\u0260\u0267\01\00\00\00\u0261\u0262\05\054\00\00"+
	  "\u0262\u0263\01\00\00\00\u0263\u0264\03\106\043\00\u0264\u0266\01\00"+
	  "\00\00\u0265\u0261\01\00\00\00\u0266\u0269\01\00\00\00\u0267\u0265\01"+
	  "\00\00\00\u0267\u0268\01\00\00\00\u0268\105\01\00\00\00\u0269\u0267\01"+
	  "\00\00\00\u026a\u026b\03\110\044\00\u026b\u0270\01\00\00\00\u026c\u026d"+
	  "\03\152\065\00\u026d\u0271\01\00\00\00\u026e\u026f\01\00\00\00\u026f"+
	  "\u0271\01\00\00\00\u0270\u026c\01\00\00\00\u0270\u026e\01\00\00\00\u0271"+
	  "\u0277\01\00\00\00\u0272\u0273\03\152\065\00\u0273\u0277\01\00\00\00"+
	  "\u0274\u0275\01\00\00\00\u0275\u0277\01\00\00\00\u0276\u026a\01\00\00"+
	  "\00\u0276\u0272\01\00\00\00\u0276\u0274\01\00\00\00\u0277\107\01\00\00"+
	  "\00\u0278\u0279\03\112\045\00\u0279\u027b\01\00\00\00\u027a\u0278\01"+
	  "\00\00\00\u027b\u027c\01\00\00\00\u027c\u027a\01\00\00\00\u027c\u027d"+
	  "\01\00\00\00\u027d\111\01\00\00\00\u027e\u027f\03\114\046\00\u027f\u0284"+
	  "\01\00\00\00\u0280\u0281\03\124\052\00\u0281\u0285\01\00\00\00\u0282"+
	  "\u0283\01\00\00\00\u0283\u0285\01\00\00\00\u0284\u0280\01\00\00\00\u0284"+
	  "\u0282\01\00\00\00\u0285\u029f\01\00\00\00\u0286\u0287\03\126\053\00"+
	  "\u0287\u028c\01\00\00\00\u0288\u0289\03\124\052\00\u0289\u028d\01\00"+
	  "\00\00\u028a\u028b\01\00\00\00\u028b\u028d\01\00\00\00\u028c\u0288\01"+
	  "\00\00\00\u028c\u028a\01\00\00\00\u028d\u029f\01\00\00\00\u028e\u028f"+
	  "\03\120\050\00\u028f\u029f\01\00\00\00\u0290\u0291\03\024\012\00\u0291"+
	  "\u0294\01\00\00\00\u0292\u0293\05\047\00\00\u0293\u0295\01\00\00\00\u0294"+
	  "\u0292\01\00\00\00\u0294\u0295\01\00\00\00\u0295\u029f\01\00\00\00\u0296"+
	  "\u0297\03\116\047\00\u0297\u029c\01\00\00\00\u0298\u0299\03\124\052\00"+
	  "\u0299\u029d\01\00\00\00\u029a\u029b\01\00\00\00\u029b\u029d\01\00\00"+
	  "\00\u029c\u0298\01\00\00\00\u029c\u029a\01\00\00\00\u029d\u029f\01\00"+
	  "\00\00\u029e\u027e\01\00\00\00\u029e\u0286\01\00\00\00\u029e\u028e\01"+
	  "\00\00\00\u029e\u0290\01\00\00\00\u029e\u0296\01\00\00\00\u029f\113\01"+
	  "\00\00\00\u02a0\u02a1\03\u0088\104\00\u02a1\u02a6\01\00\00\00\u02a2\u02a3"+
	  "\05\046\00\00\u02a3\u02a7\01\00\00\00\u02a4\u02a5\05\053\00\00\u02a5"+
	  "\u02a7\01\00\00\00\u02a6\u02a2\01\00\00\00\u02a6\u02a4\01\00\00\00\u02a7"+
	  "\u02b2\01\00\00\00\u02a8\u02a9\03\126\053\00\u02a9\u02b3\01\00\00\00"+
	  "\u02aa\u02ab\03\136\057\00\u02ab\u02b0\01\00\00\00\u02ac\u02ad\05\055"+
	  "\00\00\u02ad\u02b1\01\00\00\00\u02ae\u02af\05\050\00\00\u02af\u02b1\01"+
	  "\00\00\00\u02b0\u02ac\01\00\00\00\u02b0\u02ae\01\00\00\00\u02b0\u02b1"+
	  "\01\00\00\00\u02b1\u02b3\01\00\00\00\u02b2\u02a8\01\00\00\00\u02b2\u02aa"+
	  "\01\00\00\00\u02b3\115\01\00\00\00\u02b4\u02b5\05\063\00\00\u02b5\u02b6"+
	  "\01\00\00\00\u02b6\u02b7\03\112\045\00\u02b7\u02ba\01\00\00\00\u02b8"+
	  "\u02b9\03\112\045\00\u02b9\u02bb\01\00\00\00\u02ba\u02b8\01\00\00\00"+
	  "\u02bb\u02bc\01\00\00\00\u02bc\u02ba\01\00\00\00\u02bc\u02bd\01\00\00"+
	  "\00\u02bd\u02be\01\00\00\00\u02be\u02bf\05\042\00\00\u02bf\117\01\00"+
	  "\00\00\u02c0\u02c1\03\136\057\00\u02c1\u02c6\01\00\00\00\u02c2\u02c3"+
	  "\03\122\051\00\u02c3\u02c7\01\00\00\00\u02c4\u02c5\01\00\00\00\u02c5"+
	  "\u02c7\01\00\00\00\u02c6\u02c2\01\00\00\00\u02c6\u02c4\01\00\00\00\u02c7"+
	  "\121\01\00\00\00\u02c8\u02c9\03\124\052\00\u02c9\u02cf\01\00\00\00\u02ca"+
	  "\u02cb\05\055\00\00\u02cb\u02cf\01\00\00\00\u02cc\u02cd\05\050\00\00"+
	  "\u02cd\u02cf\01\00\00\00\u02ce\u02c8\01\00\00\00\u02ce\u02ca\01\00\00"+
	  "\00\u02ce\u02cc\01\00\00\00\u02cf\123\01\00\00\00\u02d0\u02d1\05\047"+
	  "\00\00\u02d1\u02d7\01\00\00\00\u02d2\u02d3\05\051\00\00\u02d3\u02d7\01"+
	  "\00\00\00\u02d4\u02d5\05\052\00\00\u02d5\u02d7\01\00\00\00\u02d6\u02d0"+
	  "\01\00\00\00\u02d6\u02d2\01\00\00\00\u02d6\u02d4\01\00\00\00\u02d7\125"+
	  "\01\00\00\00\u02d8\u02d9\03\142\061\00\u02d9\u02dc\01\00\00\00\u02da"+
	  "\u02db\07\01\00\00\u02db\u02dd\01\00\00\00\u02dc\u02da\01\00\00\00\u02dc"+
	  "\u02dd\01\00\00\00\u02dd\u02f9\01\00\00\00\u02de\u02df\03\144\062\00"+
	  "\u02df\u02e2\01\00\00\00\u02e0\u02e1\07\02\00\00\u02e1\u02e3\01\00\00"+
	  "\00\u02e2\u02e0\01\00\00\00\u02e2\u02e3\01\00\00\00\u02e3\u02f9\01\00"+
	  "\00\00\u02e4\u02e5\03\140\060\00\u02e5\u02f9\01\00\00\00\u02e6\u02e7"+
	  "\03\130\054\00\u02e7\u02ea\01\00\00\00\u02e8\u02e9\07\03\00\00\u02e9"+
	  "\u02eb\01\00\00\00\u02ea\u02e8\01\00\00\00\u02ea\u02eb\01\00\00\00\u02eb"+
	  "\u02f9\01\00\00\00\u02ec\u02ed\05\057\00\00\u02ed\u02f0\01\00\00\00\u02ee"+
	  "\u02ef\03\146\063\00\u02ef\u02f1\01\00\00\00\u02f0\u02ee\01\00\00\00"+
	  "\u02f0\u02f1\01\00\00\00\u02f1\u02f6\01\00\00\00\u02f2\u02f3\05\055\00"+
	  "\00\u02f3\u02f7\01\00\00\00\u02f4\u02f5\05\050\00\00\u02f5\u02f7\01\00"+
	  "\00\00\u02f6\u02f2\01\00\00\00\u02f6\u02f4\01\00\00\00\u02f6\u02f7\01"+
	  "\00\00\00\u02f7\u02f9\01\00\00\00\u02f8\u02d8\01\00\00\00\u02f8\u02de"+
	  "\01\00\00\00\u02f8\u02e4\01\00\00\00\u02f8\u02e6\01\00\00\00\u02f8\u02ec"+
	  "\01\00\00\00\u02f9\127\01\00\00\00\u02fa\u02fb\05\066\00\00\u02fb\u02fc"+
	  "\01\00\00\00\u02fc\u02fd\03\134\056\00\u02fd\u0303\01\00\00\00\u02fe"+
	  "\u02ff\05\066\00\00\u02ff\u0300\01\00\00\00\u0300\u0301\03\132\055\00"+
	  "\u0301\u0303\01\00\00\00\u0302\u02fa\01\00\00\00\u0302\u02fe\01\00\00"+
	  "\00\u0303\131\01\00\00\00\u0304\u0305\05\041\00\00\u0305\u0306\01\00"+
	  "\00\00\u0306\u0307\03\134\056\00\u0307\u030e\01\00\00\00\u0308\u0309"+
	  "\05\054\00\00\u0309\u030a\01\00\00\00\u030a\u030b\03\134\056\00\u030b"+
	  "\u030d\01\00\00\00\u030c\u0308\01\00\00\00\u030d\u0310\01\00\00\00\u030e"+
	  "\u030c\01\00\00\00\u030e\u030f\01\00\00\00\u030f\u0311\01\00\00\00\u0310"+
	  "\u030e\01\00\00\00\u0311\u0312\05\042\00\00\u0312\133\01\00\00\00\u0313"+
	  "\u0314\05\070\00\00\u0314\u031a\01\00\00\00\u0315\u0316\05\073\00\00"+
	  "\u0316\u031a\01\00\00\00\u0317\u0318\03\142\061\00\u0318\u031a\01\00"+
	  "\00\00\u0319\u0313\01\00\00\00\u0319\u0315\01\00\00\00\u0319\u0317\01"+
	  "\00\00\00\u031a\135\01\00\00\00\u031b\u031c\05\041\00\00\u031c\u032a"+
	  "\01\00\00\00\u031d\u031e\03\06\03\00\u031e\u0320\01\00\00\00\u031f\u031d"+
	  "\01\00\00\00\u031f\u0320\01\00\00\00\u0320\u0325\01\00\00\00\u0321\u0322"+
	  "\03\070\034\00\u0322\u0324\01\00\00\00\u0323\u0321\01\00\00\00\u0324"+
	  "\u0327\01\00\00\00\u0325\u0323\01\00\00\00\u0325\u0326\01\00\00\00\u0326"+
	  "\u0328\01\00\00\00\u0327\u0325\01\00\00\00\u0328\u0329\05\035\00\00\u0329"+
	  "\u032b\01\00\00\00\u032a\u031f\01\00\00\00\u032a\u032b\01\00\00\00\u032b"+
	  "\u032c\01\00\00\00\u032c\u032d\03\104\042\00\u032d\u032e\01\00\00\00"+
	  "\u032e\u032f\05\042\00\00\u032f\137\01\00\00\00\u0330\u0331\05\071\00"+
	  "\00\u0331\u0334\01\00\00\00\u0332\u0333\03\034\016\00\u0333\u0335\01"+
	  "\00\00\00\u0334\u0332\01\00\00\00\u0334\u0335\01\00\00\00\u0335\u033e"+
	  "\01\00\00\00\u0336\u0337\05\055\00\00\u0337\u033b\01\00\00\00\u0338\u0339"+
	  "\05\050\00\00\u0339\u033b\01\00\00\00\u033a\u0336\01\00\00\00\u033a\u0338"+
	  "\01\00\00\00\u033b\u033f\01\00\00\00\u033c\u033d\01\00\00\00\u033d\u033f"+
	  "\01\00\00\00\u033e\u033a\01\00\00\00\u033e\u033c\01\00\00\00\u033f\141"+
	  "\01\00\00\00\u0340\u0341\05\073\00\00\u0341\u0342\01\00\00\00\u0342\u0343"+
	  "\05\060\00\00\u0343\u0344\01\00\00\00\u0344\u0345\05\073\00\00\u0345"+
	  "\143\01\00\00\00\u0346\u0347\05\070\00\00\u0347\u034a\01\00\00\00\u0348"+
	  "\u0349\03\146\063\00\u0349\u034b\01\00\00\00\u034a\u0348\01\00\00\00"+
	  "\u034a\u034b\01\00\00\00\u034b\u0353\01\00\00\00\u034c\u034d\05\073\00"+
	  "\00\u034d\u0350\01\00\00\00\u034e\u034f\03\146\063\00\u034f\u0351\01"+
	  "\00\00\00\u0350\u034e\01\00\00\00\u0350\u0351\01\00\00\00\u0351\u0353"+
	  "\01\00\00\00\u0352\u0346\01\00\00\00\u0352\u034c\01\00\00\00\u0353\145"+
	  "\01\00\00\00\u0354\u0355\05\044\00\00\u0355\u0356\01\00\00\00\u0356\u0357"+
	  "\03\150\064\00\u0357\u035e\01\00\00\00\u0358\u0359\05\037\00\00\u0359"+
	  "\u035a\01\00\00\00\u035a\u035b\03\150\064\00\u035b\u035d\01\00\00\00"+
	  "\u035c\u0358\01\00\00\00\u035d\u0360\01\00\00\00\u035e\u035c\01\00\00"+
	  "\00\u035e\u035f\01\00\00\00\u035f\u0361\01\00\00\00\u0360\u035e\01\00"+
	  "\00\00\u0361\u0362\05\045\00\00\u0362\147\01\00\00\00\u0363\u0364\03"+
	  "\u008a\105\00\u0364\u0370\01\00\00\00\u0365\u0366\03\u0088\104\00\u0366"+
	  "\u0367\01\00\00\00\u0367\u0368\05\046\00\00\u0368\u036d\01\00\00\00\u0369"+
	  "\u036a\03\u008a\105\00\u036a\u036e\01\00\00\00\u036b\u036c\05\073\00"+
	  "\00\u036c\u036e\01\00\00\00\u036d\u0369\01\00\00\00\u036d\u036b\01\00"+
	  "\00\00\u036e\u0370\01\00\00\00\u036f\u0363\01\00\00\00\u036f\u0365\01"+
	  "\00\00\00\u0370\151\01\00\00\00\u0371\u0372\03\154\066\00\u0372\u0374"+
	  "\01\00\00\00\u0373\u0371\01\00\00\00\u0374\u0377\01\00\00\00\u0375\u0373"+
	  "\01\00\00\00\u0375\u0376\01\00\00\00\u0376\u0378\01\00\00\00\u0377\u0375"+
	  "\01\00\00\00\u0378\u0379\03\156\067\00\u0379\153\01\00\00\00\u037a\u037b"+
	  "\05\062\00\00\u037b\u037c\01\00\00\00\u037c\u037d\05\176\00\00\u037d"+
	  "\u037e\01\00\00\00\u037e\u037f\03\160\070\00\u037f\155\01\00\00\00\u0380"+
	  "\u0381\05\062\00\00\u0381\u0382\01\00\00\00\u0382\u0383\03\160\070\00"+
	  "\u0383\157\01\00\00\00\u0384\u0385\03\162\071\00\u0385\u038d\01\00\00"+
	  "\00\u0386\u0387\03\176\077\00\u0387\u038d\01\00\00\00\u0388\u0389\05"+
	  "\061\00\00\u0389\u038d\01\00\00\00\u038a\u038b\01\00\00\00\u038b\u038d"+
	  "\01\00\00\00\u038c\u0384\01\00\00\00\u038c\u0386\01\00\00\00\u038c\u0388"+
	  "\01\00\00\00\u038c\u038a\01\00\00\00\u038d\161\01\00\00\00\u038e\u038f"+
	  "\03\164\072\00\u038f\u0391\01\00\00\00\u0390\u038e\01\00\00\00\u0391"+
	  "\u0392\01\00\00\00\u0392\u0390\01\00\00\00\u0392\u0393\01\00\00\00\u0393"+
	  "\163\01\00\00\00\u0394\u0395\03\166\073\00\u0395\u0398\01\00\00\00\u0396"+
	  "\u0397\03\124\052\00\u0397\u0399\01\00\00\00\u0398\u0396\01\00\00\00"+
	  "\u0398\u0399\01\00\00\00\u0399\u03a5\01\00\00\00\u039a\u039b\03\174\076"+
	  "\00\u039b\u03a0\01\00\00\00\u039c\u039d\03\124\052\00\u039d\u03a1\01"+
	  "\00\00\00\u039e\u039f\01\00\00\00\u039f\u03a1\01\00\00\00\u03a0\u039c"+
	  "\01\00\00\00\u03a0\u039e\01\00\00\00\u03a1\u03a5\01\00\00\00\u03a2\u03a3"+
	  "\03\170\074\00\u03a3\u03a5\01\00\00\00\u03a4\u0394\01\00\00\00\u03a4"+
	  "\u039a\01\00\00\00\u03a4\u03a2\01\00\00\00\u03a5\165\01\00\00\00\u03a6"+
	  "\u03a7\05\070\00\00\u03a7\u03aa\01\00\00\00\u03a8\u03a9\03\146\063\00"+
	  "\u03a9\u03ab\01\00\00\00\u03aa\u03a8\01\00\00\00\u03aa\u03ab\01\00\00"+
	  "\00\u03ab\u03ae\01\00\00\00\u03ac\u03ad\03\034\016\00\u03ad\u03af\01"+
	  "\00\00\00\u03ae\u03ac\01\00\00\00\u03ae\u03af\01\00\00\00\u03af\u03bf"+
	  "\01\00\00\00\u03b0\u03b1\05\071\00\00\u03b1\u03bf\01\00\00\00\u03b2\u03b3"+
	  "\05\073\00\00\u03b3\u03b6\01\00\00\00\u03b4\u03b5\03\146\063\00\u03b5"+
	  "\u03b7\01\00\00\00\u03b6\u03b4\01\00\00\00\u03b6\u03b7\01\00\00\00\u03b7"+
	  "\u03bf\01\00\00\00\u03b8\u03b9\05\056\00\00\u03b9\u03ba\01\00\00\00\u03ba"+
	  "\u03bb\03\u0088\104\00\u03bb\u03bf\01\00\00\00\u03bc\u03bd\03\024\012"+
	  "\00\u03bd\u03bf\01\00\00\00\u03be\u03a6\01\00\00\00\u03be\u03b0\01\00"+
	  "\00\00\u03be\u03b2\01\00\00\00\u03be\u03b8\01\00\00\00\u03be\u03bc\01"+
	  "\00\00\00\u03bf\167\01\00\00\00\u03c0\u03c1\05\041\00\00\u03c1\u03c2"+
	  "\01\00\00\00\u03c2\u03c3\03\162\071\00\u03c3\u03c4\01\00\00\00\u03c4"+
	  "\u03c5\05\042\00\00\u03c5\u03c6\01\00\00\00\u03c6\u03c7\03\172\075\00"+
	  "\u03c7\171\01\00\00\00\u03c8\u03c9\05\047\00\00\u03c9\u03cd\01\00\00"+
	  "\00\u03ca\u03cb\05\051\00\00\u03cb\u03cd\01\00\00\00\u03cc\u03c8\01\00"+
	  "\00\00\u03cc\u03ca\01\00\00\00\u03cd\173\01\00\00\00\u03ce\u03cf\05\063"+
	  "\00\00\u03cf\u03d0\01\00\00\00\u03d0\u03d1\03\166\073\00\u03d1\u03d6"+
	  "\01\00\00\00\u03d2\u03d3\03\164\072\00\u03d3\u03d5\01\00\00\00\u03d4"+
	  "\u03d2\01\00\00\00\u03d5\u03d8\01\00\00\00\u03d6\u03d4\01\00\00\00\u03d6"+
	  "\u03d7\01\00\00\00\u03d7\u03d9\01\00\00\00\u03d8\u03d6\01\00\00\00\u03d9"+
	  "\u03da\05\042\00\00\u03da\175\01\00\00\00\u03db\u03dc\05\033\00\00\u03dc"+
	  "\u03dd\01\00\00\00\u03dd\u03de\05\041\00\00\u03de\u03df\01\00\00\00\u03df"+
	  "\u03e0\03\u0084\102\00\u03e0\u03e1\01\00\00\00\u03e1\u03e2\05\042\00"+
	  "\00\u03e2\u03e7\01\00\00\00\u03e3\u03e4\05\06\00\00\u03e4\u03e8\01\00"+
	  "\00\00\u03e5\u03e6\05\07\00\00\u03e6\u03e8\01\00\00\00\u03e7\u03e3\01"+
	  "\00\00\00\u03e7\u03e5\01\00\00\00\u03e8\u03f0\01\00\00\00\u03e9\u03ea"+
	  "\03\u0080\100\00\u03ea\u03f0\01\00\00\00\u03eb\u03ec\03\u0082\101\00"+
	  "\u03ec\u03f0\01\00\00\00\u03ed\u03ee\03\024\012\00\u03ee\u03f0\01\00"+
	  "\00\00\u03ef\u03db\01\00\00\00\u03ef\u03e9\01\00\00\00\u03ef\u03eb\01"+
	  "\00\00\00\u03ef\u03ed\01\00\00\00\u03f0\177\01\00\00\00\u03f1\u03f2\03"+
	  "\u0088\104\00\u03f2\u03f3\01\00\00\00\u03f3\u03f4\05\041\00\00\u03f4"+
	  "\u03f5\01\00\00\00\u03f5\u03f6\03\u0084\102\00\u03f6\u03f7\01\00\00\00"+
	  "\u03f7\u03f8\05\042\00\00\u03f8\u0081\01\00\00\00\u03f9\u03fa\05\041"+
	  "\00\00\u03fa\u03fb\01\00\00\00\u03fb\u03fc\03\024\012\00\u03fc\u03fd"+
	  "\01\00\00\00\u03fd\u03fe\05\042\00\00\u03fe\u03ff\01\00\00\00\u03ff\u0400"+
	  "\05\041\00\00\u0400\u0401\01\00\00\00\u0401\u0402\03\u0084\102\00\u0402"+
	  "\u0403\01\00\00\00\u0403\u0404\05\042\00\00\u0404\u0083\01\00\00\00\u0405"+
	  "\u0406\03\u0086\103\00\u0406\u040d\01\00\00\00\u0407\u0408\05\037\00"+
	  "\00\u0408\u0409\01\00\00\00\u0409\u040a\03\u0086\103\00\u040a\u040c\01"+
	  "\00\00\00\u040b\u0407\01\00\00\00\u040c\u040f\01\00\00\00\u040d\u040b"+
	  "\01\00\00\00\u040d\u040e\01\00\00\00\u040e\u0413\01\00\00\00\u040f\u040d"+
	  "\01\00\00\00\u0410\u0411\01\00\00\00\u0411\u0413\01\00\00\00\u0412\u0405"+
	  "\01\00\00\00\u0412\u0410\01\00\00\00\u0413\u0085\01\00\00\00\u0414\u0415"+
	  "\03\u0088\104\00\u0415\u0416\01\00\00\00\u0416\u0417\05\046\00\00\u0417"+
	  "\u0418\01\00\00\00\u0418\u0419\03\024\012\00\u0419\u0087\01\00\00\00"+
	  "\u041a\u041b\05\071\00\00\u041b\u0421\01\00\00\00\u041c\u041d\05\070"+
	  "\00\00\u041d\u0421\01\00\00\00\u041e\u041f\05\033\00\00\u041f\u0421\01"+
	  "\00\00\00\u0420\u041a\01\00\00\00\u0420\u041c\01\00\00\00\u0420\u041e"+
	  "\01\00\00\00\u0421\u0089\01\00\00\00\u0422\u0423\03\u0088\104\00\u0423"+
	  "\u042a\01\00\00\00\u0424\u0425\05\057\00\00\u0425\u0426\01\00\00\00\u0426"+
	  "\u0427\03\u0088\104\00\u0427\u0429\01\00\00\00\u0428\u0424\01\00\00\00"+
	  "\u0429\u042c\01\00\00\00\u042a\u0428\01\00\00\00\u042a\u042b\01\00\00"+
	  "\00\u042b\u008b\01\00\00\00\u042c\u042a\01\00\00\00\u042d\u042e\03\106"+
	  "\043\00\u042e\u042f\01\00\00\00\u042f\u0430\05\uffff\00\00\u0430\u008d"+
	  "\01\00\00\00\u0431\u0432\03\112\045\00\u0432\u0433\01\00\00\00\u0433"+
	  "\u0434\05\uffff\00\00\u0434\u008f\01\00\00\00\u0435\u0436\03\046\023"+
	  "\00\u0436\u0437\01\00\00\00\u0437\u0438\05\uffff\00\00\u0438\u0091\01"+
	  "\00\00\00\u0439\u043a\03\136\057\00\u043a\u043b\01\00\00\00\u043b\u043c"+
	  "\05\uffff\00\00\u043c\u0093\01\00\00\00\144\u0096\01\u00a2\01\u00ab\01"+
	  "\u00be\01\u00c8\01\u00d2\01\u00e5\01\u00f1\01\u00fe\01\u0106\01\u0112"+
	  "\01\u0118\01\u0148\01\u014a\01\u0155\01\u015e\01\u0169\01\u0172\01\u017b"+
	  "\01\u017e\01\u0186\01\u018f\01\u0192\01\u019a\01\u01a5\01\u01ad\01\u01b8"+
	  "\01\u01c4\01\u01d0\01\u01d6\01\u01db\01\u01df\01\u01e5\01\u01e9\01\u01ed"+
	  "\01\u01f1\01\u0201\01\u0206\01\u0216\01\u021d\01\u022d\01\u023e\01\u0248"+
	  "\01\u0254\01\u025d\01\u0267\01\u0270\01\u0276\01\u027c\01\u0284\01\u028c"+
	  "\01\u0294\01\u029c\01\u029e\01\u02a6\01\u02b0\01\u02b2\01\u02bc\01\u02c6"+
	  "\01\u02ce\01\u02d6\01\u02dc\01\u02e2\01\u02ea\01\u02f0\01\u02f6\01\u02f8"+
	  "\01\u0302\01\u030e\01\u0319\01\u031f\01\u0325\01\u032a\01\u0334\01\u033a"+
	  "\01\u033e\01\u034a\01\u0350\01\u0352\01\u035e\01\u036d\01\u036f\01\u0375"+
	  "\01\u038c\01\u0392\01\u0398\01\u03a0\01\u03a4\01\u03aa\01\u03ae\01\u03b6"+
	  "\01\u03be\01\u03cc\01\u03d6\01\u03e7\01\u03ef\01\u040d\01\u0412\01\u0420"+
	  "\01\u042a\01";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}