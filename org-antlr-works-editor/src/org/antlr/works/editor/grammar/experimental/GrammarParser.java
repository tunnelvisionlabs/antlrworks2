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
		_interp = new ParserATNSimulator<Token>(this,_ATN);
	}
	public static class grammarSpecContext extends ParserRuleContext<Token> {
		public grammarSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final grammarSpecContext grammarSpec() throws RecognitionException {
		grammarSpecContext _localctx = new grammarSpecContext(_ctx, 0);
		enterRule(_localctx, RULE_grammarSpec);
		//System.out.println("enter "+ruleNames[RULE_grammarSpec]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(148); match(DOC_COMMENT);
					}
					break;
			}
			setState(152); grammarType();
			setState(154); id();
			setState(156); match(SEMI);
			setState(162);
			_errHandler.sync(this);
			int _alt272 = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt272!=2 && _alt272!=-1 ) {
				switch ( _alt272 ) {
					case 1:
						{
						{
						setState(158); prequelConstruct();
						}
						}
						break;
				}
				setState(164);
				_errHandler.sync(this);
				_alt272 = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(165); rules();
			setState(171);
			_errHandler.sync(this);
			int _alt285 = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt285!=2 && _alt285!=-1 ) {
				switch ( _alt285 ) {
					case 1:
						{
						{
						setState(167); mode_();
						}
						}
						break;
				}
				setState(173);
				_errHandler.sync(this);
				_alt285 = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class grammarTypeContext extends ParserRuleContext<Token> {
		public Token t;;
		public Token g;;
		public grammarTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
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

	public static class prequelConstructContext extends ParserRuleContext<Token> {
		public prequelConstructContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final prequelConstructContext prequelConstruct() throws RecognitionException {
		prequelConstructContext _localctx = new prequelConstructContext(_ctx, 4);
		enterRule(_localctx, RULE_prequelConstruct);
		//System.out.println("enter "+ruleNames[RULE_prequelConstruct]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(200);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
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

	public static class optionsSpecContext extends ParserRuleContext<Token> {
		public optionsSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final optionsSpecContext optionsSpec() throws RecognitionException {
		optionsSpecContext _localctx = new optionsSpecContext(_ctx, 6);
		enterRule(_localctx, RULE_optionsSpec);
		//System.out.println("enter "+ruleNames[RULE_optionsSpec]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202); match(OPTIONS);
			setState(210);
			_errHandler.sync(this);
			int _alt450 = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt450!=2 && _alt450!=-1 ) {
				switch ( _alt450 ) {
					case 1:
						{
						{
						setState(204); option();
						setState(206); match(SEMI);
						}
						}
						break;
				}
				setState(212);
				_errHandler.sync(this);
				_alt450 = getInterpreter().adaptivePredict(_input,5,_ctx);
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

	public static class optionContext extends ParserRuleContext<Token> {
		public optionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class optionValueContext extends ParserRuleContext<Token> {
		public optionValueContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final optionValueContext optionValue() throws RecognitionException {
		optionValueContext _localctx = new optionValueContext(_ctx, 10);
		enterRule(_localctx, RULE_optionValue);
		//System.out.println("enter "+ruleNames[RULE_optionValue]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(229);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
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

	public static class delegateGrammarsContext extends ParserRuleContext<Token> {
		public delegateGrammarsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final delegateGrammarsContext delegateGrammars() throws RecognitionException {
		delegateGrammarsContext _localctx = new delegateGrammarsContext(_ctx, 12);
		enterRule(_localctx, RULE_delegateGrammars);
		//System.out.println("enter "+ruleNames[RULE_delegateGrammars]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231); match(IMPORT);
			setState(233); delegateGrammar();
			setState(241);
			_errHandler.sync(this);
			int _alt543 = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt543!=2 && _alt543!=-1 ) {
				switch ( _alt543 ) {
					case 1:
						{
						{
						setState(235); match(COMMA);
						setState(237); delegateGrammar();
						}
						}
						break;
				}
				setState(243);
				_errHandler.sync(this);
				_alt543 = getInterpreter().adaptivePredict(_input,7,_ctx);
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

	public static class delegateGrammarContext extends ParserRuleContext<Token> {
		public delegateGrammarContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final delegateGrammarContext delegateGrammar() throws RecognitionException {
		delegateGrammarContext _localctx = new delegateGrammarContext(_ctx, 14);
		enterRule(_localctx, RULE_delegateGrammar);
		//System.out.println("enter "+ruleNames[RULE_delegateGrammar]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(254);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
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

	public static class tokensSpecContext extends ParserRuleContext<Token> {
		public tokensSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final tokensSpecContext tokensSpec() throws RecognitionException {
		tokensSpecContext _localctx = new tokensSpecContext(_ctx, 16);
		enterRule(_localctx, RULE_tokensSpec);
		//System.out.println("enter "+ruleNames[RULE_tokensSpec]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256); match(TOKENS);
			setState(260); 
			_errHandler.sync(this);
			int _alt581 = getInterpreter().adaptivePredict(_input,9,_ctx);
			do {
				switch ( _alt581 ) {
					case 1:
						{
						{
						setState(258); tokenSpec();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(262); 
				_errHandler.sync(this);
				_alt581 = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt581!=2 && _alt581!=-1 );
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

	public static class tokenSpecContext extends ParserRuleContext<Token> {
		public tokenSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final tokenSpecContext tokenSpec() throws RecognitionException {
		tokenSpecContext _localctx = new tokenSpecContext(_ctx, 18);
		enterRule(_localctx, RULE_tokenSpec);
		//System.out.println("enter "+ruleNames[RULE_tokenSpec]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(280);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(266); id();
					setState(274);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
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

	public static class actionBlockContext extends ParserRuleContext<Token> {
		public actionBlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionBlockContext actionBlock() throws RecognitionException {
		actionBlockContext _localctx = new actionBlockContext(_ctx, 20);
		enterRule(_localctx, RULE_actionBlock);
		//System.out.println("enter "+ruleNames[RULE_actionBlock]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282); match(BEGIN_ACTION);
			setState(330);
			_errHandler.sync(this);
			int _alt714 = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt714!=2 && _alt714!=-1 ) {
				switch ( _alt714 ) {
					case 1:
						{
						setState(328);
						//_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
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
						break;
				}
				setState(332);
				_errHandler.sync(this);
				_alt714 = getInterpreter().adaptivePredict(_input,13,_ctx);
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

	public static class actionExpressionContext extends ParserRuleContext<Token> {
		public actionExpressionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionExpressionContext actionExpression() throws RecognitionException {
		actionExpressionContext _localctx = new actionExpressionContext(_ctx, 22);
		enterRule(_localctx, RULE_actionExpression);
		//System.out.println("enter "+ruleNames[RULE_actionExpression]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335); match(ACTION_REFERENCE);
			setState(341);
			_errHandler.sync(this);
			int _alt727 = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt727!=2 && _alt727!=-1 ) {
				switch ( _alt727 ) {
					case 1:
						{
						{
						setState(337); actionIgnored();
						}
						}
						break;
				}
				setState(343);
				_errHandler.sync(this);
				_alt727 = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(344); match(ACTION_DOT);
			setState(350);
			_errHandler.sync(this);
			int _alt732 = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt732!=2 && _alt732!=-1 ) {
				switch ( _alt732 ) {
					case 1:
						{
						{
						setState(346); actionIgnored();
						}
						}
						break;
				}
				setState(352);
				_errHandler.sync(this);
				_alt732 = getInterpreter().adaptivePredict(_input,15,_ctx);
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

	public static class actionScopeExpressionContext extends ParserRuleContext<Token> {
		public actionScopeExpressionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionScopeExpressionContext actionScopeExpression() throws RecognitionException {
		actionScopeExpressionContext _localctx = new actionScopeExpressionContext(_ctx, 24);
		enterRule(_localctx, RULE_actionScopeExpression);
		//System.out.println("enter "+ruleNames[RULE_actionScopeExpression]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355); match(ACTION_REFERENCE);
			setState(361);
			_errHandler.sync(this);
			int _alt745 = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt745!=2 && _alt745!=-1 ) {
				switch ( _alt745 ) {
					case 1:
						{
						{
						setState(357); actionIgnored();
						}
						}
						break;
				}
				setState(363);
				_errHandler.sync(this);
				_alt745 = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(402);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(364); match(ACTION_LBRACK);
					setState(370);
					_errHandler.sync(this);
					int _alt751 = getInterpreter().adaptivePredict(_input,17,_ctx);
					while ( _alt751!=2 && _alt751!=-1 ) {
						switch ( _alt751 ) {
							case 1:
								{
								{
								setState(366); actionIgnored();
								}
								}
								break;
						}
						setState(372);
						_errHandler.sync(this);
						_alt751 = getInterpreter().adaptivePredict(_input,17,_ctx);
					}
					setState(382);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
						case 1:
							{
							setState(373); match(ACTION_MINUS);
							setState(379);
							_errHandler.sync(this);
							int _alt757 = getInterpreter().adaptivePredict(_input,18,_ctx);
							while ( _alt757!=2 && _alt757!=-1 ) {
								switch ( _alt757 ) {
									case 1:
										{
										{
										setState(375); actionIgnored();
										}
										}
										break;
								}
								setState(381);
								_errHandler.sync(this);
								_alt757 = getInterpreter().adaptivePredict(_input,18,_ctx);
							}
							}
							break;
					}
					setState(384); match(ACTION_WORD);
					setState(390);
					_errHandler.sync(this);
					int _alt764 = getInterpreter().adaptivePredict(_input,20,_ctx);
					while ( _alt764!=2 && _alt764!=-1 ) {
						switch ( _alt764 ) {
							case 1:
								{
								{
								setState(386); actionIgnored();
								}
								}
								break;
						}
						setState(392);
						_errHandler.sync(this);
						_alt764 = getInterpreter().adaptivePredict(_input,20,_ctx);
					}
					setState(393); match(ACTION_RBRACK);
					setState(399);
					_errHandler.sync(this);
					int _alt769 = getInterpreter().adaptivePredict(_input,21,_ctx);
					while ( _alt769!=2 && _alt769!=-1 ) {
						switch ( _alt769 ) {
							case 1:
								{
								{
								setState(395); actionIgnored();
								}
								}
								break;
						}
						setState(401);
						_errHandler.sync(this);
						_alt769 = getInterpreter().adaptivePredict(_input,21,_ctx);
					}
					}
					break;
			}
			setState(404); match(ACTION_COLON2);
			setState(410);
			_errHandler.sync(this);
			int _alt776 = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt776!=2 && _alt776!=-1 ) {
				switch ( _alt776 ) {
					case 1:
						{
						{
						setState(406); actionIgnored();
						}
						}
						break;
				}
				setState(412);
				_errHandler.sync(this);
				_alt776 = getInterpreter().adaptivePredict(_input,23,_ctx);
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

	public static class actionIgnoredContext extends ParserRuleContext<Token> {
		public actionIgnoredContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionIgnoredContext actionIgnored() throws RecognitionException {
		actionIgnoredContext _localctx = new actionIgnoredContext(_ctx, 26);
		enterRule(_localctx, RULE_actionIgnored);
		//System.out.println("enter "+ruleNames[RULE_actionIgnored]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(421);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
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

	public static class argActionBlockContext extends ParserRuleContext<Token> {
		public argActionBlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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
			int _alt845 = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt845!=2 && _alt845!=-1 ) {
				switch ( _alt845 ) {
					case 1:
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
						break;
				}
				setState(431);
				_errHandler.sync(this);
				_alt845 = getInterpreter().adaptivePredict(_input,25,_ctx);
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

	public static class actionContext extends ParserRuleContext<Token> {
		public actionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
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

	public static class actionScopeNameContext extends ParserRuleContext<Token> {
		public actionScopeNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionScopeNameContext actionScopeName() throws RecognitionException {
		actionScopeNameContext _localctx = new actionScopeNameContext(_ctx, 32);
		enterRule(_localctx, RULE_actionScopeName);
		//System.out.println("enter "+ruleNames[RULE_actionScopeName]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(452);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
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

	public static class mode_Context extends ParserRuleContext<Token> {
		public mode_Context(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final mode_Context mode_() throws RecognitionException {
		mode_Context _localctx = new mode_Context(_ctx, 34);
		enterRule(_localctx, RULE_mode_);
		//System.out.println("enter "+ruleNames[RULE_mode_]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(454); match(MODE);
			setState(456); id();
			setState(458); match(SEMI);
			setState(462); 
			_errHandler.sync(this);
			int _alt918 = getInterpreter().adaptivePredict(_input,28,_ctx);
			do {
				switch ( _alt918 ) {
					case 1:
						{
						{
						setState(460); rule();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(464); 
				_errHandler.sync(this);
				_alt918 = getInterpreter().adaptivePredict(_input,28,_ctx);
			} while ( _alt918!=2 && _alt918!=-1 );
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

	public static class rulesContext extends ParserRuleContext<Token> {
		public rulesContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rulesContext rules() throws RecognitionException {
		rulesContext _localctx = new rulesContext(_ctx, 36);
		enterRule(_localctx, RULE_rules);
		//System.out.println("enter "+ruleNames[RULE_rules]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(470);
			_errHandler.sync(this);
			int _alt935 = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt935!=2 && _alt935!=-1 ) {
				switch ( _alt935 ) {
					case 1:
						{
						{
						setState(466); rule();
						}
						}
						break;
				}
				setState(472);
				_errHandler.sync(this);
				_alt935 = getInterpreter().adaptivePredict(_input,29,_ctx);
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

	public static class ruleContext extends ParserRuleContext<Token> {
		public idContext name;;
		public ruleContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleContext rule() throws RecognitionException {
		ruleContext _localctx = new ruleContext(_ctx, 38);
		enterRule(_localctx, RULE_rule);
		//System.out.println("enter "+ruleNames[RULE_rule]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(473); match(DOC_COMMENT);
					}
					break;
			}
			setState(479);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(477); ruleModifiers();
					}
					break;
			}
			setState(481); _localctx.name = id();
			setState(485);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
				case 1:
					{
					setState(483); argActionBlock();
					}
					break;
			}
			setState(489);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
				case 1:
					{
					setState(487); ruleReturns();
					}
					break;
			}
			setState(493);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
				case 1:
					{
					setState(491); throwsSpec();
					}
					break;
			}
			setState(497);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(495); locals_();
					}
					break;
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

	public static class exceptionGroupContext extends ParserRuleContext<Token> {
		public exceptionGroupContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final exceptionGroupContext exceptionGroup() throws RecognitionException {
		exceptionGroupContext _localctx = new exceptionGroupContext(_ctx, 40);
		enterRule(_localctx, RULE_exceptionGroup);
		//System.out.println("enter "+ruleNames[RULE_exceptionGroup]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			_errHandler.sync(this);
			int _alt1126 = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt1126!=2 && _alt1126!=-1 ) {
				switch ( _alt1126 ) {
					case 1:
						{
						{
						setState(509); exceptionHandler();
						}
						}
						break;
				}
				setState(515);
				_errHandler.sync(this);
				_alt1126 = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			setState(518);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					{
					setState(516); finallyClause();
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
			exitRule(RULE_exceptionGroup);
			// System.out.println("exit "+ruleNames[RULE_exceptionGroup]);
		}
		return _localctx;
	}

	public static class exceptionHandlerContext extends ParserRuleContext<Token> {
		public exceptionHandlerContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class finallyClauseContext extends ParserRuleContext<Token> {
		public finallyClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class rulePrequelsContext extends ParserRuleContext<Token> {
		public rulePrequelsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rulePrequelsContext rulePrequels() throws RecognitionException {
		rulePrequelsContext _localctx = new rulePrequelsContext(_ctx, 46);
		enterRule(_localctx, RULE_rulePrequels);
		//System.out.println("enter "+ruleNames[RULE_rulePrequels]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(534);
			_errHandler.sync(this);
			int _alt1182 = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt1182!=2 && _alt1182!=-1 ) {
				switch ( _alt1182 ) {
					case 1:
						{
						{
						setState(530); rulePrequel();
						}
						}
						break;
				}
				setState(536);
				_errHandler.sync(this);
				_alt1182 = getInterpreter().adaptivePredict(_input,38,_ctx);
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

	public static class rulePrequelContext extends ParserRuleContext<Token> {
		public rulePrequelContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rulePrequelContext rulePrequel() throws RecognitionException {
		rulePrequelContext _localctx = new rulePrequelContext(_ctx, 48);
		enterRule(_localctx, RULE_rulePrequel);
		//System.out.println("enter "+ruleNames[RULE_rulePrequel]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(541);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
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

	public static class ruleReturnsContext extends ParserRuleContext<Token> {
		public ruleReturnsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class throwsSpecContext extends ParserRuleContext<Token> {
		public throwsSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final throwsSpecContext throwsSpec() throws RecognitionException {
		throwsSpecContext _localctx = new throwsSpecContext(_ctx, 52);
		enterRule(_localctx, RULE_throwsSpec);
		//System.out.println("enter "+ruleNames[RULE_throwsSpec]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(547); match(THROWS);
			setState(549); qid();
			setState(557);
			_errHandler.sync(this);
			int _alt1265 = getInterpreter().adaptivePredict(_input,40,_ctx);
			while ( _alt1265!=2 && _alt1265!=-1 ) {
				switch ( _alt1265 ) {
					case 1:
						{
						{
						setState(551); match(COMMA);
						setState(553); qid();
						}
						}
						break;
				}
				setState(559);
				_errHandler.sync(this);
				_alt1265 = getInterpreter().adaptivePredict(_input,40,_ctx);
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

	public static class locals_Context extends ParserRuleContext<Token> {
		public locals_Context(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class ruleActionContext extends ParserRuleContext<Token> {
		public ruleActionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class ruleModifiersContext extends ParserRuleContext<Token> {
		public ruleModifiersContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleModifiersContext ruleModifiers() throws RecognitionException {
		ruleModifiersContext _localctx = new ruleModifiersContext(_ctx, 58);
		enterRule(_localctx, RULE_ruleModifiers);
		//System.out.println("enter "+ruleNames[RULE_ruleModifiers]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(572); 
			_errHandler.sync(this);
			int _alt1332 = getInterpreter().adaptivePredict(_input,41,_ctx);
			do {
				switch ( _alt1332 ) {
					case 1:
						{
						{
						setState(570); ruleModifier();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(574); 
				_errHandler.sync(this);
				_alt1332 = getInterpreter().adaptivePredict(_input,41,_ctx);
			} while ( _alt1332!=2 && _alt1332!=-1 );
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

	public static class ruleModifierContext extends ParserRuleContext<Token> {
		public ruleModifierContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleModifierContext ruleModifier() throws RecognitionException {
		ruleModifierContext _localctx = new ruleModifierContext(_ctx, 60);
		enterRule(_localctx, RULE_ruleModifier);
		//System.out.println("enter "+ruleNames[RULE_ruleModifier]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(584);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
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

	public static class ruleBlockContext extends ParserRuleContext<Token> {
		public ruleBlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class ruleAltListContext extends ParserRuleContext<Token> {
		public ruleAltListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleAltListContext ruleAltList() throws RecognitionException {
		ruleAltListContext _localctx = new ruleAltListContext(_ctx, 64);
		enterRule(_localctx, RULE_ruleAltList);
		//System.out.println("enter "+ruleNames[RULE_ruleAltList]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(588); labeledAlt();
			setState(596);
			_errHandler.sync(this);
			int _alt1413 = getInterpreter().adaptivePredict(_input,43,_ctx);
			while ( _alt1413!=2 && _alt1413!=-1 ) {
				switch ( _alt1413 ) {
					case 1:
						{
						{
						setState(590); match(OR);
						setState(592); labeledAlt();
						}
						}
						break;
				}
				setState(598);
				_errHandler.sync(this);
				_alt1413 = getInterpreter().adaptivePredict(_input,43,_ctx);
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

	public static class labeledAltContext extends ParserRuleContext<Token> {
		public labeledAltContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final labeledAltContext labeledAlt() throws RecognitionException {
		labeledAltContext _localctx = new labeledAltContext(_ctx, 66);
		enterRule(_localctx, RULE_labeledAlt);
		//System.out.println("enter "+ruleNames[RULE_labeledAlt]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(599); alternative();
			setState(605);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(601); match(POUND);
					setState(603); id();
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
			exitRule(RULE_labeledAlt);
			// System.out.println("exit "+ruleNames[RULE_labeledAlt]);
		}
		return _localctx;
	}

	public static class altListContext extends ParserRuleContext<Token> {
		public altListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final altListContext altList() throws RecognitionException {
		altListContext _localctx = new altListContext(_ctx, 68);
		enterRule(_localctx, RULE_altList);
		//System.out.println("enter "+ruleNames[RULE_altList]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(607); alternative();
			setState(615);
			_errHandler.sync(this);
			int _alt1449 = getInterpreter().adaptivePredict(_input,45,_ctx);
			while ( _alt1449!=2 && _alt1449!=-1 ) {
				switch ( _alt1449 ) {
					case 1:
						{
						{
						setState(609); match(OR);
						setState(611); alternative();
						}
						}
						break;
				}
				setState(617);
				_errHandler.sync(this);
				_alt1449 = getInterpreter().adaptivePredict(_input,45,_ctx);
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

	public static class alternativeContext extends ParserRuleContext<Token> {
		public alternativeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final alternativeContext alternative() throws RecognitionException {
		alternativeContext _localctx = new alternativeContext(_ctx, 70);
		enterRule(_localctx, RULE_alternative);
		//System.out.println("enter "+ruleNames[RULE_alternative]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(630);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(618); elements();
					setState(624);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
						case 1:
							{
							setState(620); rewrite();
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
					setState(626); rewrite();
					}
					break;

				case 3:
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
			exitRule(RULE_alternative);
			// System.out.println("exit "+ruleNames[RULE_alternative]);
		}
		return _localctx;
	}

	public static class elementsContext extends ParserRuleContext<Token> {
		public elementContext e;;
		public List<elementContext> e_list = new ArrayList<elementContext>();;
		public elementsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final elementsContext elements() throws RecognitionException {
		elementsContext _localctx = new elementsContext(_ctx, 72);
		enterRule(_localctx, RULE_elements);
		//System.out.println("enter "+ruleNames[RULE_elements]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(634); 
			_errHandler.sync(this);
			int _alt1500 = getInterpreter().adaptivePredict(_input,48,_ctx);
			do {
				switch ( _alt1500 ) {
					case 1:
						{
						{
						setState(632); _localctx.e = element();
						_localctx.e_list.add(_localctx.e);
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(636); 
				_errHandler.sync(this);
				_alt1500 = getInterpreter().adaptivePredict(_input,48,_ctx);
			} while ( _alt1500!=2 && _alt1500!=-1 );
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

	public static class elementContext extends ParserRuleContext<Token> {
		public elementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final elementContext element() throws RecognitionException {
		elementContext _localctx = new elementContext(_ctx, 74);
		enterRule(_localctx, RULE_element);
		//System.out.println("enter "+ruleNames[RULE_element]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(670);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(638); labeledElement();
					setState(644);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
						case 1:
							{
							setState(640); ebnfSuffix();
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
					setState(646); atom();
					setState(652);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
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
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
						case 1:
							{
							setState(658); match(QUESTION);
							}
							break;
					}
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(662); treeSpec();
					setState(668);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
						case 1:
							{
							setState(664); ebnfSuffix();
							}
							break;

						case 2:
							{
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
			exitRule(RULE_element);
			// System.out.println("exit "+ruleNames[RULE_element]);
		}
		return _localctx;
	}

	public static class labeledElementContext extends ParserRuleContext<Token> {
		public Token ass;;
		public Token op;;
		public labeledElementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
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
			}
			setState(690);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
				case 1:
					{
					setState(680); atom();
					}
					break;

				case 2:
					{
					setState(682); block();
					setState(688);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
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

	public static class treeSpecContext extends ParserRuleContext<Token> {
		public Token begin;;
		public elementContext root;;
		public elementContext kids;;
		public List<elementContext> kids_list = new ArrayList<elementContext>();;
		public treeSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final treeSpecContext treeSpec() throws RecognitionException {
		treeSpecContext _localctx = new treeSpecContext(_ctx, 78);
		enterRule(_localctx, RULE_treeSpec);
		//System.out.println("enter "+ruleNames[RULE_treeSpec]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(692); _localctx.begin = match(TREE_BEGIN);
			setState(694); _localctx.root = element();
			setState(698); 
			_errHandler.sync(this);
			int _alt1757 = getInterpreter().adaptivePredict(_input,57,_ctx);
			do {
				switch ( _alt1757 ) {
					case 1:
						{
						{
						setState(696); _localctx.kids = element();
						_localctx.kids_list.add(_localctx.kids);
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(700); 
				_errHandler.sync(this);
				_alt1757 = getInterpreter().adaptivePredict(_input,57,_ctx);
			} while ( _alt1757!=2 && _alt1757!=-1 );
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

	public static class ebnfContext extends ParserRuleContext<Token> {
		public ebnfContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					{
					setState(706); blockSuffix();
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
			exitRule(RULE_ebnf);
			// System.out.println("exit "+ruleNames[RULE_ebnf]);
		}
		return _localctx;
	}

	public static class blockSuffixContext extends ParserRuleContext<Token> {
		public blockSuffixContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final blockSuffixContext blockSuffix() throws RecognitionException {
		blockSuffixContext _localctx = new blockSuffixContext(_ctx, 82);
		enterRule(_localctx, RULE_blockSuffix);
		//System.out.println("enter "+ruleNames[RULE_blockSuffix]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(718);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
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

	public static class ebnfSuffixContext extends ParserRuleContext<Token> {
		public ebnfSuffixContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ebnfSuffixContext ebnfSuffix() throws RecognitionException {
		ebnfSuffixContext _localctx = new ebnfSuffixContext(_ctx, 84);
		enterRule(_localctx, RULE_ebnfSuffix);
		//System.out.println("enter "+ruleNames[RULE_ebnfSuffix]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(726);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
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

	public static class atomContext extends ParserRuleContext<Token> {
		public Token astop;;
		public atomContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(728); range();
					setState(732);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
						case 1:
							{
							setState(730);
							_input.LT(1);
							_la = _input.LA(1);
							if ( !(_la==BANG || _la==ROOT) ) {
							_errHandler.recoverInline(this);
							}
							consume();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(734); terminal();
					setState(738);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
						case 1:
							{
							setState(736);
							_input.LT(1);
							_la = _input.LA(1);
							if ( !(_la==BANG || _la==ROOT) ) {
							_errHandler.recoverInline(this);
							}
							consume();
							}
							break;
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
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
						case 1:
							{
							setState(744);
							_input.LT(1);
							_la = _input.LA(1);
							if ( !(_la==BANG || _la==ROOT) ) {
							_errHandler.recoverInline(this);
							}
							consume();
							}
							break;
					}
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(748); match(DOT);
					setState(752);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
						case 1:
							{
							setState(750); elementOptions();
							}
							break;
					}
					setState(758);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
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

	public static class notSetContext extends ParserRuleContext<Token> {
		public notSetContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final notSetContext notSet() throws RecognitionException {
		notSetContext _localctx = new notSetContext(_ctx, 88);
		enterRule(_localctx, RULE_notSet);
		//System.out.println("enter "+ruleNames[RULE_notSet]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(770);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
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

	public static class blockSetContext extends ParserRuleContext<Token> {
		public blockSetContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final blockSetContext blockSet() throws RecognitionException {
		blockSetContext _localctx = new blockSetContext(_ctx, 90);
		enterRule(_localctx, RULE_blockSet);
		//System.out.println("enter "+ruleNames[RULE_blockSet]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(772); match(LPAREN);
			setState(774); setElement();
			setState(782);
			_errHandler.sync(this);
			int _alt2005 = getInterpreter().adaptivePredict(_input,68,_ctx);
			while ( _alt2005!=2 && _alt2005!=-1 ) {
				switch ( _alt2005 ) {
					case 1:
						{
						{
						setState(776); match(OR);
						setState(778); setElement();
						}
						}
						break;
				}
				setState(784);
				_errHandler.sync(this);
				_alt2005 = getInterpreter().adaptivePredict(_input,68,_ctx);
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

	public static class setElementContext extends ParserRuleContext<Token> {
		public setElementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final setElementContext setElement() throws RecognitionException {
		setElementContext _localctx = new setElementContext(_ctx, 92);
		enterRule(_localctx, RULE_setElement);
		//System.out.println("enter "+ruleNames[RULE_setElement]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(793);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
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

	public static class blockContext extends ParserRuleContext<Token> {
		public ruleActionContext ra;;
		public List<ruleActionContext> ra_list = new ArrayList<ruleActionContext>();;
		public blockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final blockContext block() throws RecognitionException {
		blockContext _localctx = new blockContext(_ctx, 94);
		enterRule(_localctx, RULE_block);
		//System.out.println("enter "+ruleNames[RULE_block]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(795); match(LPAREN);
			setState(810);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					{
					setState(799);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
						case 1:
							{
							setState(797); optionsSpec();
							}
							break;
					}
					setState(805);
					_errHandler.sync(this);
					int _alt2079 = getInterpreter().adaptivePredict(_input,71,_ctx);
					while ( _alt2079!=2 && _alt2079!=-1 ) {
						switch ( _alt2079 ) {
							case 1:
								{
								{
								setState(801); _localctx.ra = ruleAction();
								_localctx.ra_list.add(_localctx.ra);
								}
								}
								break;
						}
						setState(807);
						_errHandler.sync(this);
						_alt2079 = getInterpreter().adaptivePredict(_input,71,_ctx);
					}
					setState(808); match(COLON);
					}
					break;
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

	public static class rulerefContext extends ParserRuleContext<Token> {
		public Token op;;
		public rulerefContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rulerefContext ruleref() throws RecognitionException {
		rulerefContext _localctx = new rulerefContext(_ctx, 96);
		enterRule(_localctx, RULE_ruleref);
		//System.out.println("enter "+ruleNames[RULE_ruleref]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(816); match(RULE_REF);
			setState(820);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
				case 1:
					{
					setState(818); argActionBlock();
					}
					break;
			}
			setState(830);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
				case 1:
					{
					setState(826);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
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
					}
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
			exitRule(RULE_ruleref);
			// System.out.println("exit "+ruleNames[RULE_ruleref]);
		}
		return _localctx;
	}

	public static class rangeContext extends ParserRuleContext<Token> {
		public rangeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class terminalContext extends ParserRuleContext<Token> {
		public terminalContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final terminalContext terminal() throws RecognitionException {
		terminalContext _localctx = new terminalContext(_ctx, 100);
		enterRule(_localctx, RULE_terminal);
		//System.out.println("enter "+ruleNames[RULE_terminal]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(850);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(838); match(TOKEN_REF);
					setState(842);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
						case 1:
							{
							setState(840); elementOptions();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(844); match(STRING_LITERAL);
					setState(848);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
						case 1:
							{
							setState(846); elementOptions();
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
			exitRule(RULE_terminal);
			// System.out.println("exit "+ruleNames[RULE_terminal]);
		}
		return _localctx;
	}

	public static class elementOptionsContext extends ParserRuleContext<Token> {
		public elementOptionsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final elementOptionsContext elementOptions() throws RecognitionException {
		elementOptionsContext _localctx = new elementOptionsContext(_ctx, 102);
		enterRule(_localctx, RULE_elementOptions);
		//System.out.println("enter "+ruleNames[RULE_elementOptions]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(852); match(LT);
			setState(854); elementOption();
			setState(862);
			_errHandler.sync(this);
			int _alt2230 = getInterpreter().adaptivePredict(_input,79,_ctx);
			while ( _alt2230!=2 && _alt2230!=-1 ) {
				switch ( _alt2230 ) {
					case 1:
						{
						{
						setState(856); match(COMMA);
						setState(858); elementOption();
						}
						}
						break;
				}
				setState(864);
				_errHandler.sync(this);
				_alt2230 = getInterpreter().adaptivePredict(_input,79,_ctx);
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

	public static class elementOptionContext extends ParserRuleContext<Token> {
		public elementOptionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final elementOptionContext elementOption() throws RecognitionException {
		elementOptionContext _localctx = new elementOptionContext(_ctx, 104);
		enterRule(_localctx, RULE_elementOption);
		//System.out.println("enter "+ruleNames[RULE_elementOption]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(879);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
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
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
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

	public static class rewriteContext extends ParserRuleContext<Token> {
		public rewriteContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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
			int _alt2277 = getInterpreter().adaptivePredict(_input,82,_ctx);
			while ( _alt2277!=2 && _alt2277!=-1 ) {
				switch ( _alt2277 ) {
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
				_alt2277 = getInterpreter().adaptivePredict(_input,82,_ctx);
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

	public static class predicatedRewriteContext extends ParserRuleContext<Token> {
		public predicatedRewriteContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class nakedRewriteContext extends ParserRuleContext<Token> {
		public nakedRewriteContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class rewriteAltContext extends ParserRuleContext<Token> {
		public boolean isTemplate;
		public rewriteAltContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteAltContext rewriteAlt() throws RecognitionException {
		rewriteAltContext _localctx = new rewriteAltContext(_ctx, 112);
		enterRule(_localctx, RULE_rewriteAlt);
		//System.out.println("enter "+ruleNames[RULE_rewriteAlt]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(908);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
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

	public static class rewriteTreeAltContext extends ParserRuleContext<Token> {
		public rewriteTreeAltContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTreeAltContext rewriteTreeAlt() throws RecognitionException {
		rewriteTreeAltContext _localctx = new rewriteTreeAltContext(_ctx, 114);
		enterRule(_localctx, RULE_rewriteTreeAlt);
		//System.out.println("enter "+ruleNames[RULE_rewriteTreeAlt]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(912); 
			_errHandler.sync(this);
			int _alt2366 = getInterpreter().adaptivePredict(_input,84,_ctx);
			do {
				switch ( _alt2366 ) {
					case 1:
						{
						{
						setState(910); rewriteTreeElement();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(914); 
				_errHandler.sync(this);
				_alt2366 = getInterpreter().adaptivePredict(_input,84,_ctx);
			} while ( _alt2366!=2 && _alt2366!=-1 );
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

	public static class rewriteTreeElementContext extends ParserRuleContext<Token> {
		public rewriteTreeElementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTreeElementContext rewriteTreeElement() throws RecognitionException {
		rewriteTreeElementContext _localctx = new rewriteTreeElementContext(_ctx, 116);
		enterRule(_localctx, RULE_rewriteTreeElement);
		//System.out.println("enter "+ruleNames[RULE_rewriteTreeElement]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(932);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(916); rewriteTreeAtom();
					setState(920);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
						case 1:
							{
							setState(918); ebnfSuffix();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(922); rewriteTree();
					setState(928);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
						case 1:
							{
							setState(924); ebnfSuffix();
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
					setState(930); rewriteTreeEbnf();
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
			exitRule(RULE_rewriteTreeElement);
			// System.out.println("exit "+ruleNames[RULE_rewriteTreeElement]);
		}
		return _localctx;
	}

	public static class rewriteTreeAtomContext extends ParserRuleContext<Token> {
		public rewriteTreeAtomContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTreeAtomContext rewriteTreeAtom() throws RecognitionException {
		rewriteTreeAtomContext _localctx = new rewriteTreeAtomContext(_ctx, 118);
		enterRule(_localctx, RULE_rewriteTreeAtom);
		//System.out.println("enter "+ruleNames[RULE_rewriteTreeAtom]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(958);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(934); match(TOKEN_REF);
					setState(938);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
						case 1:
							{
							setState(936); elementOptions();
							}
							break;
					}
					setState(942);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
						case 1:
							{
							setState(940); argActionBlock();
							}
							break;
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
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
						case 1:
							{
							setState(948); elementOptions();
							}
							break;
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

	public static class rewriteTreeEbnfContext extends ParserRuleContext<Token> {
		public Token lp;;
		public rewriteTreeEbnfContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class rewriteEbnfSuffixContext extends ParserRuleContext<Token> {
		public rewriteEbnfSuffixContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteEbnfSuffixContext rewriteEbnfSuffix() throws RecognitionException {
		rewriteEbnfSuffixContext _localctx = new rewriteEbnfSuffixContext(_ctx, 122);
		enterRule(_localctx, RULE_rewriteEbnfSuffix);
		//System.out.println("enter "+ruleNames[RULE_rewriteEbnfSuffix]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(972);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
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

	public static class rewriteTreeContext extends ParserRuleContext<Token> {
		public rewriteTreeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTreeContext rewriteTree() throws RecognitionException {
		rewriteTreeContext _localctx = new rewriteTreeContext(_ctx, 124);
		enterRule(_localctx, RULE_rewriteTree);
		//System.out.println("enter "+ruleNames[RULE_rewriteTree]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(974); match(TREE_BEGIN);
			setState(976); rewriteTreeAtom();
			setState(982);
			_errHandler.sync(this);
			int _alt2516 = getInterpreter().adaptivePredict(_input,93,_ctx);
			while ( _alt2516!=2 && _alt2516!=-1 ) {
				switch ( _alt2516 ) {
					case 1:
						{
						{
						setState(978); rewriteTreeElement();
						}
						}
						break;
				}
				setState(984);
				_errHandler.sync(this);
				_alt2516 = getInterpreter().adaptivePredict(_input,93,_ctx);
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

	public static class rewriteTemplateContext extends ParserRuleContext<Token> {
		public Token str;;
		public rewriteTemplateContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTemplateContext rewriteTemplate() throws RecognitionException {
		rewriteTemplateContext _localctx = new rewriteTemplateContext(_ctx, 126);
		enterRule(_localctx, RULE_rewriteTemplate);
		//System.out.println("enter "+ruleNames[RULE_rewriteTemplate]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(1007);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(987); match(TEMPLATE);
					setState(989); match(LPAREN);
					setState(991); rewriteTemplateArgs();
					setState(993); match(RPAREN);
					setState(999);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
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

	public static class rewriteTemplateRefContext extends ParserRuleContext<Token> {
		public rewriteTemplateRefContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class rewriteIndirectTemplateHeadContext extends ParserRuleContext<Token> {
		public Token lp;;
		public rewriteIndirectTemplateHeadContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class rewriteTemplateArgsContext extends ParserRuleContext<Token> {
		public rewriteTemplateArgsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rewriteTemplateArgsContext rewriteTemplateArgs() throws RecognitionException {
		rewriteTemplateArgsContext _localctx = new rewriteTemplateArgsContext(_ctx, 132);
		enterRule(_localctx, RULE_rewriteTemplateArgs);
		//System.out.println("enter "+ruleNames[RULE_rewriteTemplateArgs]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(1042);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1029); rewriteTemplateArg();
					setState(1037);
					_errHandler.sync(this);
					int _alt2630 = getInterpreter().adaptivePredict(_input,96,_ctx);
					while ( _alt2630!=2 && _alt2630!=-1 ) {
						switch ( _alt2630 ) {
							case 1:
								{
								{
								setState(1031); match(COMMA);
								setState(1033); rewriteTemplateArg();
								}
								}
								break;
						}
						setState(1039);
						_errHandler.sync(this);
						_alt2630 = getInterpreter().adaptivePredict(_input,96,_ctx);
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
			exitRule(RULE_rewriteTemplateArgs);
			// System.out.println("exit "+ruleNames[RULE_rewriteTemplateArgs]);
		}
		return _localctx;
	}

	public static class rewriteTemplateArgContext extends ParserRuleContext<Token> {
		public rewriteTemplateArgContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class idContext extends ParserRuleContext<Token> {
		public idContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final idContext id() throws RecognitionException {
		idContext _localctx = new idContext(_ctx, 136);
		enterRule(_localctx, RULE_id);
		//System.out.println("enter "+ruleNames[RULE_id]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(1056);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
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

	public static class qidContext extends ParserRuleContext<Token> {
		public qidContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final qidContext qid() throws RecognitionException {
		qidContext _localctx = new qidContext(_ctx, 138);
		enterRule(_localctx, RULE_qid);
		//System.out.println("enter "+ruleNames[RULE_qid]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1058); id();
			setState(1066);
			_errHandler.sync(this);
			int _alt2701 = getInterpreter().adaptivePredict(_input,99,_ctx);
			while ( _alt2701!=2 && _alt2701!=-1 ) {
				switch ( _alt2701 ) {
					case 1:
						{
						{
						setState(1060); match(DOT);
						setState(1062); id();
						}
						}
						break;
				}
				setState(1068);
				_errHandler.sync(this);
				_alt2701 = getInterpreter().adaptivePredict(_input,99,_ctx);
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

	public static class alternativeEntryContext extends ParserRuleContext<Token> {
		public alternativeEntryContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class elementEntryContext extends ParserRuleContext<Token> {
		public elementEntryContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class ruleEntryContext extends ParserRuleContext<Token> {
		public ruleEntryContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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

	public static class blockEntryContext extends ParserRuleContext<Token> {
		public blockEntryContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
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
		"\1~\u043e\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7"+
		"\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16\7\16\2\17"+
		"\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25\7\25\2\26"+
		"\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34\7\34\2\35"+
		"\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7%\2&\7&"+
		"\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2.\7.\2/\7/\2\60\7\60\2\61"+
		"\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65\2\66\7\66\2\67\7\67\28\7"+
		"8\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2@\7@\2A\7A\2B\7B\2C\7C\2"+
		"D\7D\2E\7E\2F\7F\2G\7G\2H\7H\2I\7I\1\0\0\3\0\b\0\1\0\0\1\0\0\1\0\0\1\0"+
		"\0\5\0\b\0\n\0\1\0\t\0\1\0\0\1\0\0\5\0\b\0\n\0\1\0\t\0\1\0\1\0\1\1\0\1"+
		"\1\0\1\1\0\1\1\0\1\1\0\1\1\0\1\1\0\3\1\b\1\1\2\0\1\2\0\1\2\0\1\2\0\3\2"+
		"\b\2\1\3\0\1\3\0\1\3\1\3\5\3\b\3\n\3\1\3\t\3\1\3\1\3\1\4\0\1\4\0\1\4\1"+
		"\4\1\5\0\1\5\0\1\5\0\1\5\0\3\5\b\5\1\6\0\1\6\0\1\6\0\1\6\0\5\6\b\6\n\6"+
		"\1\6\t\6\1\6\1\6\1\7\0\1\7\0\1\7\1\7\1\7\0\3\7\b\7\1\b\0\1\b\0\4\b\b\b"+
		"\13\b\1\b\1\b\1\b\1\t\0\1\t\0\1\t\0\1\t\0\3\t\b\t\1\t\1\t\1\t\0\3\t\b"+
		"\t\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1"+
		"\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0"+
		"\5\n\b\n\n\n\1\n\t\n\1\n\1\n\1\13\0\1\13\0\5\13\b\13\n\13\1\13\t\13\1"+
		"\13\0\1\13\0\5\13\b\13\n\13\1\13\t\13\1\13\1\13\1\f\0\1\f\0\5\f\b\f\n"+
		"\f\1\f\t\f\1\f\0\1\f\0\5\f\b\f\n\f\1\f\t\f\1\f\0\1\f\0\5\f\b\f\n\f\1\f"+
		"\t\f\3\f\b\f\1\f\0\1\f\0\5\f\b\f\n\f\1\f\t\f\1\f\0\1\f\0\5\f\b\f\n\f\1"+
		"\f\t\f\3\f\b\f\1\f\0\1\f\0\5\f\b\f\n\f\1\f\t\f\1\f\1\f\1\r\0\1\r\0\1\r"+
		"\0\3\r\b\r\1\16\0\1\16\0\5\16\b\16\n\16\1\16\t\16\1\16\1\16\1\17\0\1\17"+
		"\0\1\17\1\17\3\17\b\17\1\17\0\1\17\1\17\1\20\0\1\20\0\1\20\0\3\20\b\20"+
		"\1\21\0\1\21\0\1\21\0\1\21\0\4\21\b\21\13\21\1\21\1\22\0\5\22\b\22\n\22"+
		"\1\22\t\22\1\23\0\3\23\b\23\1\23\0\3\23\b\23\1\23\0\1\23\0\3\23\b\23\1"+
		"\23\0\3\23\b\23\1\23\0\3\23\b\23\1\23\0\3\23\b\23\1\23\0\1\23\0\1\23\0"+
		"\1\23\0\1\23\1\23\1\24\0\5\24\b\24\n\24\1\24\t\24\1\24\0\3\24\b\24\1\25"+
		"\0\1\25\0\1\25\1\25\1\26\0\1\26\1\26\1\27\0\5\27\b\27\n\27\1\27\t\27\1"+
		"\30\0\1\30\0\3\30\b\30\1\31\0\1\31\1\31\1\32\0\1\32\0\1\32\0\1\32\0\5"+
		"\32\b\32\n\32\1\32\t\32\1\33\0\1\33\1\33\1\34\0\1\34\0\1\34\1\34\1\35"+
		"\0\4\35\b\35\13\35\1\35\1\36\0\1\36\0\1\36\0\1\36\0\3\36\b\36\1\37\1\37"+
		"\1 \0\1 \0\1 \0\5 \b \n \1 \t \1!\0\1!\0\1!\0\3!\b!\1\"\0\1\"\0\1\"\0"+
		"\5\"\b\"\n\"\1\"\t\"\1#\0\1#\0\1#\0\3#\b#\1#\0\1#\0\3#\b#\1$\0\4$\b$\13"+
		"$\1$\1%\0\1%\0\1%\0\3%\b%\1%\0\1%\0\1%\0\3%\b%\1%\0\1%\0\1%\0\3%\b%\1"+
		"%\0\1%\0\1%\0\3%\b%\3%\b%\1&\0\1&\0\1&\0\3&\b&\1&\0\1&\0\1&\0\1&\0\3&"+
		"\b&\3&\b&\1\'\0\1\'\0\1\'\0\4\'\b\'\13\'\1\'\1\'\1\'\1(\0\1(\0\1(\0\3"+
		"(\b(\1)\0\1)\0\1)\0\3)\b)\1*\0\1*\0\1*\0\3*\b*\1+\0\1+\0\3+\b+\1+\0\1"+
		"+\0\3+\b+\1+\0\1+\0\1+\0\3+\b+\1+\0\1+\0\3+\b+\1+\0\1+\0\3+\b+\3+\b+\1"+
		",\0\1,\0\1,\0\1,\0\3,\b,\1-\0\1-\0\1-\0\1-\0\5-\b-\n-\1-\t-\1-\1-\1.\0"+
		"\1.\0\1.\0\3.\b.\1/\0\1/\0\3/\b/\1/\0\5/\b/\n/\1/\t/\1/\0\3/\b/\1/\0\1"+
		"/\1/\1\60\0\1\60\0\3\60\b\60\1\60\0\1\60\0\3\60\b\60\1\60\0\3\60\b\60"+
		"\1\61\0\1\61\0\1\61\1\61\1\62\0\1\62\0\3\62\b\62\1\62\0\1\62\0\3\62\b"+
		"\62\3\62\b\62\1\63\0\1\63\0\1\63\0\1\63\0\5\63\b\63\n\63\1\63\t\63\1\63"+
		"\1\63\1\64\0\1\64\0\1\64\0\1\64\0\1\64\0\3\64\b\64\3\64\b\64\1\65\0\5"+
		"\65\b\65\n\65\1\65\t\65\1\65\1\65\1\66\0\1\66\0\1\66\1\66\1\67\0\1\67"+
		"\1\67\18\0\18\0\18\0\18\0\38\b8\19\0\49\b9\139\19\1:\0\1:\0\3:\b:\1:\0"+
		"\1:\0\1:\0\3:\b:\1:\0\3:\b:\1;\0\1;\0\3;\b;\1;\0\3;\b;\1;\0\1;\0\1;\0"+
		"\3;\b;\1;\0\1;\0\1;\0\3;\b;\1<\0\1<\0\1<\0\1<\1<\1=\0\1=\0\3=\b=\1>\0"+
		"\1>\0\1>\0\5>\b>\n>\1>\t>\1>\1>\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\3?\b?\1"+
		"?\0\1?\0\1?\0\3?\b?\1@\0\1@\0\1@\0\1@\1@\1A\0\1A\0\1A\0\1A\0\1A\0\1A\1"+
		"A\1B\0\1B\0\1B\0\5B\bB\nB\1B\tB\1B\0\3B\bB\1C\0\1C\0\1C\1C\1D\0\1D\0\1"+
		"D\0\3D\bD\1E\0\1E\0\1E\0\5E\bE\nE\1E\tE\1F\0\1F\1F\1G\0\1G\1G\1H\0\1H"+
		"\1H\1I\0\1I\1I\1IJ\0\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086"+
		"\u0088\u008a\u008c\u008e\u0090\u0092\0\4\1>G\2((--\2((--\2((--\u0408\0"+
		"\u0096\1\0\0\0\1\u043d\5\uffff\0\0\2\u00be\1\0\0\0\3\u009a\1\0\0\0\4\u00c8"+
		"\1\0\0\0\5\u00a1\1\0\0\0\6\u00ca\1\0\0\0\7\u00c9\1\0\0\0\7\u021e\1\0\0"+
		"\0\7\u0320\1\0\0\0\b\u00d7\1\0\0\0\t\u00ce\1\0\0\0\n\u00e5\1\0\0\0\13"+
		"\u00dc\1\0\0\0\f\u00e7\1\0\0\0\r\u00c9\1\0\0\0\16\u00fe\1\0\0\0\17\u00f1"+
		"\1\0\0\0\17\u00f0\1\0\0\0\20\u0100\1\0\0\0\21\u00c9\1\0\0\0\22\u0118\1"+
		"\0\0\0\23\u0105\1\0\0\0\24\u011a\1\0\0\0\25\u0149\1\0\0\0\25\u01bd\1\0"+
		"\0\0\25\u020d\1\0\0\0\25\u0211\1\0\0\0\25\u0239\1\0\0\0\25\u0294\1\0\0"+
		"\0\25\u03bf\1\0\0\0\25\u03f0\1\0\0\0\25\u03fd\1\0\0\0\25\u0419\1\0\0\0"+
		"\26\u014f\1\0\0\0\27\u0149\1\0\0\0\30\u0163\1\0\0\0\31\u0149\1\0\0\0\32"+
		"\u01a5\1\0\0\0\33\u0149\1\0\0\0\33\u0154\1\0\0\0\33\u015d\1\0\0\0\33\u0168"+
		"\1\0\0\0\33\u0171\1\0\0\0\33\u017a\1\0\0\0\33\u0185\1\0\0\0\33\u018e\1"+
		"\0\0\0\33\u0199\1\0\0\0\34\u01a7\1\0\0\0\35\u01e6\1\0\0\0\35\u020c\1\0"+
		"\0\0\35\u0222\1\0\0\0\35\u0233\1\0\0\0\35\u0335\1\0\0\0\35\u03af\1\0\0"+
		"\0\36\u01b2\1\0\0\0\37\u00c9\1\0\0\0 \u01c4\1\0\0\0!\u01b6\1\0\0\0\"\u01c6"+
		"\1\0\0\0#\u00aa\1\0\0\0$\u01d6\1\0\0\0%\u00ab\1\0\0\0&\u01db\1\0\0\0\'"+
		"\u01cf\1\0\0\0\'\u01d5\1\0\0\0\'\u0437\1\0\0\0(\u0201\1\0\0\0)\u01fc\1"+
		"\0\0\0*\u0208\1\0\0\0+\u0200\1\0\0\0,\u020e\1\0\0\0-\u0207\1\0\0\0.\u0216"+
		"\1\0\0\0/\u01f5\1\0\0\0\60\u021d\1\0\0\0\61\u0215\1\0\0\0\62\u021f\1\0"+
		"\0\0\63\u01ea\1\0\0\0\64\u0223\1\0\0\0\65\u01ee\1\0\0\0\66\u0230\1\0\0"+
		"\0\67\u01f2\1\0\0\08\u0234\1\0\0\09\u021e\1\0\0\09\u0324\1\0\0\0:\u023c"+
		"\1\0\0\0;\u01e0\1\0\0\0<\u0248\1\0\0\0=\u023d\1\0\0\0>\u024a\1\0\0\0?"+
		"\u01f9\1\0\0\0@\u024c\1\0\0\0A\u024b\1\0\0\0B\u0257\1\0\0\0C\u0254\1\0"+
		"\0\0C\u0253\1\0\0\0D\u025f\1\0\0\0E\u032e\1\0\0\0F\u0276\1\0\0\0G\u025d"+
		"\1\0\0\0G\u0267\1\0\0\0G\u0266\1\0\0\0G\u042f\1\0\0\0H\u027a\1\0\0\0I"+
		"\u0270\1\0\0\0J\u029e\1\0\0\0K\u027b\1\0\0\0K\u02ba\1\0\0\0K\u02bb\1\0"+
		"\0\0K\u0433\1\0\0\0L\u02a0\1\0\0\0M\u0284\1\0\0\0N\u02b4\1\0\0\0O\u029c"+
		"\1\0\0\0P\u02c0\1\0\0\0Q\u029f\1\0\0\0R\u02ce\1\0\0\0S\u02c7\1\0\0\0T"+
		"\u02d6\1\0\0\0U\u0285\1\0\0\0U\u028d\1\0\0\0U\u029d\1\0\0\0U\u02cf\1\0"+
		"\0\0U\u0399\1\0\0\0U\u03a1\1\0\0\0V\u02f8\1\0\0\0W\u028c\1\0\0\0W\u02b3"+
		"\1\0\0\0X\u0302\1\0\0\0Y\u02ea\1\0\0\0Z\u0304\1\0\0\0[\u0303\1\0\0\0\\"+
		"\u0319\1\0\0\0]\u0303\1\0\0\0]\u030e\1\0\0\0]\u030d\1\0\0\0^\u031b\1\0"+
		"\0\0_\u02b0\1\0\0\0_\u02c6\1\0\0\0_\u043b\1\0\0\0`\u0330\1\0\0\0a\u02f9"+
		"\1\0\0\0b\u0340\1\0\0\0c\u02dc\1\0\0\0c\u031a\1\0\0\0d\u0352\1\0\0\0e"+
		"\u02e2\1\0\0\0f\u0354\1\0\0\0g\u02f1\1\0\0\0g\u034b\1\0\0\0g\u0351\1\0"+
		"\0\0g\u03ab\1\0\0\0g\u03b7\1\0\0\0h\u036f\1\0\0\0i\u035e\1\0\0\0i\u035d"+
		"\1\0\0\0j\u0375\1\0\0\0k\u0271\1\0\0\0k\u0277\1\0\0\0l\u037a\1\0\0\0m"+
		"\u0374\1\0\0\0n\u0380\1\0\0\0o\u0379\1\0\0\0p\u038c\1\0\0\0q\u037f\1\0"+
		"\0\0q\u0383\1\0\0\0r\u0390\1\0\0\0s\u038d\1\0\0\0s\u03c4\1\0\0\0t\u03a4"+
		"\1\0\0\0u\u0391\1\0\0\0u\u03d5\1\0\0\0v\u03be\1\0\0\0w\u0398\1\0\0\0w"+
		"\u03d6\1\0\0\0x\u03c0\1\0\0\0y\u03a5\1\0\0\0z\u03cc\1\0\0\0{\u03c7\1\0"+
		"\0\0|\u03ce\1\0\0\0}\u03a0\1\0\0\0~\u03ef\1\0\0\0\177\u038d\1\0\0\0\u0080"+
		"\u03f1\1\0\0\0\u0081\u03f0\1\0\0\0\u0082\u03f9\1\0\0\0\u0083\u03f0\1\0"+
		"\0\0\u0084\u0412\1\0\0\0\u0085\u03e1\1\0\0\0\u0085\u03f7\1\0\0\0\u0085"+
		"\u0403\1\0\0\0\u0086\u0414\1\0\0\0\u0087\u040d\1\0\0\0\u0087\u040c\1\0"+
		"\0\0\u0088\u0420\1\0\0\0\u0089\u009c\1\0\0\0\u0089\u00d9\1\0\0\0\u0089"+
		"\u00f8\1\0\0\0\u0089\u00fb\1\0\0\0\u0089\u00ff\1\0\0\0\u0089\u0112\1\0"+
		"\0\0\u0089\u01bc\1\0\0\0\u0089\u01c5\1\0\0\0\u0089\u01ca\1\0\0\0\u0089"+
		"\u01e5\1\0\0\0\u0089\u0238\1\0\0\0\u0089\u025e\1\0\0\0\u0089\u02a6\1\0"+
		"\0\0\u0089\u0367\1\0\0\0\u0089\u03bf\1\0\0\0\u0089\u03f3\1\0\0\0\u0089"+
		"\u0416\1\0\0\0\u0089\u042a\1\0\0\0\u0089\u0429\1\0\0\0\u008a\u0422\1\0"+
		"\0\0\u008b\u00e6\1\0\0\0\u008b\u022d\1\0\0\0\u008b\u022c\1\0\0\0\u008b"+
		"\u0370\1\0\0\0\u008b\u036e\1\0\0\0\u008c\u042d\1\0\0\0\u008d\u043d\5\uffff"+
		"\0\0\u008e\u0431\1\0\0\0\u008f\u043d\5\uffff\0\0\u0090\u0435\1\0\0\0\u0091"+
		"\u043d\5\uffff\0\0\u0092\u0439\1\0\0\0\u0093\u043d\5\uffff\0\0\u0094\u0097"+
		"\5\3\0\0\u0096\u0094\1\0\0\0\u0096\u0097\1\0\0\0\u0097\u0098\1\0\0\0\u0098"+
		"\u009a\3\2\1\0\u009a\u009c\3\u0088D\0\u009c\u00a2\5 \0\0\u009e\u00a1\3"+
		"\4\2\0\u00a0\u009e\1\0\0\0\u00a1\u00a4\1\0\0\0\u00a2\u00a0\1\0\0\0\u00a2"+
		"\u00a3\1\0\0\0\u00a3\u00a5\1\0\0\0\u00a4\u00a2\1\0\0\0\u00a5\u00ab\3$"+
		"\22\0\u00a7\u00aa\3\"\21\0\u00a9\u00a7\1\0\0\0\u00aa\u00ad\1\0\0\0\u00ab"+
		"\u00a9\1\0\0\0\u00ab\u00ac\1\0\0\0\u00ac\u00ae\1\0\0\0\u00ad\u00ab\1\0"+
		"\0\0\u00ae\u00af\5\uffff\0\0\u00af\1\1\0\0\0\u00b0\u00b2\5\17\0\0\u00b2"+
		"\u00bf\5\22\0\0\u00b4\u00b6\5\20\0\0\u00b6\u00bf\5\22\0\0\u00b8\u00ba"+
		"\5\21\0\0\u00ba\u00bf\5\22\0\0\u00bc\u00bf\5\22\0\0\u00be\u00b0\1\0\0"+
		"\0\u00be\u00b4\1\0\0\0\u00be\u00b8\1\0\0\0\u00be\u00bc\1\0\0\0\u00bf\3"+
		"\1\0\0\0\u00c0\u00c9\3\6\3\0\u00c2\u00c9\3\f\6\0\u00c4\u00c9\3\20\b\0"+
		"\u00c6\u00c9\3\36\17\0\u00c8\u00c0\1\0\0\0\u00c8\u00c2\1\0\0\0\u00c8\u00c4"+
		"\1\0\0\0\u00c8\u00c6\1\0\0\0\u00c9\5\1\0\0\0\u00ca\u00d2\5\n\0\0\u00cc"+
		"\u00ce\3\b\4\0\u00ce\u00cf\5 \0\0\u00cf\u00d1\1\0\0\0\u00d0\u00cc\1\0"+
		"\0\0\u00d1\u00d4\1\0\0\0\u00d2\u00d0\1\0\0\0\u00d2\u00d3\1\0\0\0\u00d3"+
		"\u00d5\1\0\0\0\u00d4\u00d2\1\0\0\0\u00d5\u00d6\5\67\0\0\u00d6\7\1\0\0"+
		"\0\u00d7\u00d9\3\u0088D\0\u00d9\u00db\5&\0\0\u00db\u00dc\3\n\5\0\u00dc"+
		"\t\1\0\0\0\u00dd\u00e6\3\u008aE\0\u00df\u00e6\5;\0\0\u00e1\u00e6\5:\0"+
		"\0\u00e3\u00e6\5)\0\0\u00e5\u00dd\1\0\0\0\u00e5\u00df\1\0\0\0\u00e5\u00e1"+
		"\1\0\0\0\u00e5\u00e3\1\0\0\0\u00e6\13\1\0\0\0\u00e7\u00e9\5\r\0\0\u00e9"+
		"\u00f1\3\16\7\0\u00eb\u00ed\5\37\0\0\u00ed\u00f0\3\16\7\0\u00ef\u00eb"+
		"\1\0\0\0\u00f0\u00f3\1\0\0\0\u00f1\u00ef\1\0\0\0\u00f1\u00f2\1\0\0\0\u00f2"+
		"\u00f4\1\0\0\0\u00f3\u00f1\1\0\0\0\u00f4\u00f5\5 \0\0\u00f5\r\1\0\0\0"+
		"\u00f6\u00f8\3\u0088D\0\u00f8\u00fa\5&\0\0\u00fa\u00fb\3\u0088D\0\u00fb"+
		"\u00ff\1\0\0\0\u00fc\u00ff\3\u0088D\0\u00fe\u00f6\1\0\0\0\u00fe\u00fc"+
		"\1\0\0\0\u00ff\17\1\0\0\0\u0100\u0104\5\13\0\0\u0102\u0105\3\22\t\0\u0104"+
		"\u0102\1\0\0\0\u0105\u0106\1\0\0\0\u0106\u0104\1\0\0\0\u0106\u0107\1\0"+
		"\0\0\u0107\u0108\1\0\0\0\u0108\u0109\5\67\0\0\u0109\21\1\0\0\0\u010a\u0112"+
		"\3\u0088D\0\u010c\u010e\5&\0\0\u010e\u0113\5;\0\0\u0110\u0113\1\0\0\0"+
		"\u0112\u010c\1\0\0\0\u0112\u0110\1\0\0\0\u0113\u0114\1\0\0\0\u0114\u0115"+
		"\5 \0\0\u0115\u0119\1\0\0\0\u0116\u0119\59\0\0\u0118\u010a\1\0\0\0\u0118"+
		"\u0116\1\0\0\0\u0119\23\1\0\0\0\u011a\u014a\5\t\0\0\u011c\u0149\3\24\n"+
		"\0\u011e\u0149\3\26\13\0\u0120\u0149\3\30\f\0\u0122\u0149\3\32\r\0\u0124"+
		"\u0149\5Y\0\0\u0126\u0149\5Z\0\0\u0128\u0149\5[\0\0\u012a\u0149\5K\0\0"+
		"\u012c\u0149\5L\0\0\u012e\u0149\5M\0\0\u0130\u0149\5N\0\0\u0132\u0149"+
		"\5O\0\0\u0134\u0149\5P\0\0\u0136\u0149\5Q\0\0\u0138\u0149\5R\0\0\u013a"+
		"\u0149\5V\0\0\u013c\u0149\5W\0\0\u013e\u0149\5X\0\0\u0140\u0149\5T\0\0"+
		"\u0142\u0149\5S\0\0\u0144\u0149\5U\0\0\u0146\u0149\5J\0\0\u0148\u011c"+
		"\1\0\0\0\u0148\u011e\1\0\0\0\u0148\u0120\1\0\0\0\u0148\u0122\1\0\0\0\u0148"+
		"\u0124\1\0\0\0\u0148\u0126\1\0\0\0\u0148\u0128\1\0\0\0\u0148\u012a\1\0"+
		"\0\0\u0148\u012c\1\0\0\0\u0148\u012e\1\0\0\0\u0148\u0130\1\0\0\0\u0148"+
		"\u0132\1\0\0\0\u0148\u0134\1\0\0\0\u0148\u0136\1\0\0\0\u0148\u0138\1\0"+
		"\0\0\u0148\u013a\1\0\0\0\u0148\u013c\1\0\0\0\u0148\u013e\1\0\0\0\u0148"+
		"\u0140\1\0\0\0\u0148\u0142\1\0\0\0\u0148\u0144\1\0\0\0\u0148\u0146\1\0"+
		"\0\0\u0149\u014c\1\0\0\0\u014a\u0148\1\0\0\0\u014a\u014b\1\0\0\0\u014b"+
		"\u014d\1\0\0\0\u014c\u014a\1\0\0\0\u014d\u014e\5^\0\0\u014e\25\1\0\0\0"+
		"\u014f\u0155\5X\0\0\u0151\u0154\3\32\r\0\u0153\u0151\1\0\0\0\u0154\u0157"+
		"\1\0\0\0\u0155\u0153\1\0\0\0\u0155\u0156\1\0\0\0\u0156\u0158\1\0\0\0\u0157"+
		"\u0155\1\0\0\0\u0158\u015e\5J\0\0\u015a\u015d\3\32\r\0\u015c\u015a\1\0"+
		"\0\0\u015d\u0160\1\0\0\0\u015e\u015c\1\0\0\0\u015e\u015f\1\0\0\0\u015f"+
		"\u0161\1\0\0\0\u0160\u015e\1\0\0\0\u0161\u0162\5W\0\0\u0162\27\1\0\0\0"+
		"\u0163\u0169\5X\0\0\u0165\u0168\3\32\r\0\u0167\u0165\1\0\0\0\u0168\u016b"+
		"\1\0\0\0\u0169\u0167\1\0\0\0\u0169\u016a\1\0\0\0\u016a\u0192\1\0\0\0\u016b"+
		"\u0169\1\0\0\0\u016c\u0172\5O\0\0\u016e\u0171\3\32\r\0\u0170\u016e\1\0"+
		"\0\0\u0171\u0174\1\0\0\0\u0172\u0170\1\0\0\0\u0172\u0173\1\0\0\0\u0173"+
		"\u017e\1\0\0\0\u0174\u0172\1\0\0\0\u0175\u017b\5U\0\0\u0177\u017a\3\32"+
		"\r\0\u0179\u0177\1\0\0\0\u017a\u017d\1\0\0\0\u017b\u0179\1\0\0\0\u017b"+
		"\u017c\1\0\0\0\u017c\u017f\1\0\0\0\u017d\u017b\1\0\0\0\u017e\u0175\1\0"+
		"\0\0\u017e\u017f\1\0\0\0\u017f\u0180\1\0\0\0\u0180\u0186\5W\0\0\u0182"+
		"\u0185\3\32\r\0\u0184\u0182\1\0\0\0\u0185\u0188\1\0\0\0\u0186\u0184\1"+
		"\0\0\0\u0186\u0187\1\0\0\0\u0187\u0189\1\0\0\0\u0188\u0186\1\0\0\0\u0189"+
		"\u018f\5P\0\0\u018b\u018e\3\32\r\0\u018d\u018b\1\0\0\0\u018e\u0191\1\0"+
		"\0\0\u018f\u018d\1\0\0\0\u018f\u0190\1\0\0\0\u0190\u0193\1\0\0\0\u0191"+
		"\u018f\1\0\0\0\u0192\u016c\1\0\0\0\u0192\u0193\1\0\0\0\u0193\u0194\1\0"+
		"\0\0\u0194\u019a\5S\0\0\u0196\u0199\3\32\r\0\u0198\u0196\1\0\0\0\u0199"+
		"\u019c\1\0\0\0\u019a\u0198\1\0\0\0\u019a\u019b\1\0\0\0\u019b\u019d\1\0"+
		"\0\0\u019c\u019a\1\0\0\0\u019d\u019e\5W\0\0\u019e\31\1\0\0\0\u019f\u01a6"+
		"\5\\\0\0\u01a1\u01a6\5]\0\0\u01a3\u01a6\5Y\0\0\u01a5\u019f\1\0\0\0\u01a5"+
		"\u01a1\1\0\0\0\u01a5\u01a3\1\0\0\0\u01a6\33\1\0\0\0\u01a7\u01ad\5\b\0"+
		"\0\u01a9\u01ac\7\0\0\0\u01ab\u01a9\1\0\0\0\u01ac\u01af\1\0\0\0\u01ad\u01ab"+
		"\1\0\0\0\u01ad\u01ae\1\0\0\0\u01ae\u01b0\1\0\0\0\u01af\u01ad\1\0\0\0\u01b0"+
		"\u01b1\5H\0\0\u01b1\35\1\0\0\0\u01b2\u01b8\5\64\0\0\u01b4\u01b6\3 \20"+
		"\0\u01b6\u01b7\5\36\0\0\u01b7\u01b9\1\0\0\0\u01b8\u01b4\1\0\0\0\u01b8"+
		"\u01b9\1\0\0\0\u01b9\u01ba\1\0\0\0\u01ba\u01bc\3\u0088D\0\u01bc\u01bd"+
		"\3\24\n\0\u01bd\37\1\0\0\0\u01be\u01c5\3\u0088D\0\u01c0\u01c5\5\17\0\0"+
		"\u01c2\u01c5\5\20\0\0\u01c4\u01be\1\0\0\0\u01c4\u01c0\1\0\0\0\u01c4\u01c2"+
		"\1\0\0\0\u01c5!\1\0\0\0\u01c6\u01c8\5\34\0\0\u01c8\u01ca\3\u0088D\0\u01ca"+
		"\u01ce\5 \0\0\u01cc\u01cf\3&\23\0\u01ce\u01cc\1\0\0\0\u01cf\u01d0\1\0"+
		"\0\0\u01d0\u01ce\1\0\0\0\u01d0\u01d1\1\0\0\0\u01d1#\1\0\0\0\u01d2\u01d5"+
		"\3&\23\0\u01d4\u01d2\1\0\0\0\u01d5\u01d8\1\0\0\0\u01d6\u01d4\1\0\0\0\u01d6"+
		"\u01d7\1\0\0\0\u01d7%\1\0\0\0\u01d8\u01d6\1\0\0\0\u01d9\u01dc\5\3\0\0"+
		"\u01db\u01d9\1\0\0\0\u01db\u01dc\1\0\0\0\u01dc\u01df\1\0\0\0\u01dd\u01e0"+
		"\3:\35\0\u01df\u01dd\1\0\0\0\u01df\u01e0\1\0\0\0\u01e0\u01e1\1\0\0\0\u01e1"+
		"\u01e5\3\u0088D\0\u01e3\u01e6\3\34\16\0\u01e5\u01e3\1\0\0\0\u01e5\u01e6"+
		"\1\0\0\0\u01e6\u01e9\1\0\0\0\u01e7\u01ea\3\62\31\0\u01e9\u01e7\1\0\0\0"+
		"\u01e9\u01ea\1\0\0\0\u01ea\u01ed\1\0\0\0\u01eb\u01ee\3\64\32\0\u01ed\u01eb"+
		"\1\0\0\0\u01ed\u01ee\1\0\0\0\u01ee\u01f1\1\0\0\0\u01ef\u01f2\3\66\33\0"+
		"\u01f1\u01ef\1\0\0\0\u01f1\u01f2\1\0\0\0\u01f2\u01f3\1\0\0\0\u01f3\u01f5"+
		"\3.\27\0\u01f5\u01f7\5\35\0\0\u01f7\u01f9\3>\37\0\u01f9\u01fb\5 \0\0\u01fb"+
		"\u01fc\3(\24\0\u01fc\'\1\0\0\0\u01fd\u0200\3*\25\0\u01ff\u01fd\1\0\0\0"+
		"\u0200\u0203\1\0\0\0\u0201\u01ff\1\0\0\0\u0201\u0202\1\0\0\0\u0202\u0206"+
		"\1\0\0\0\u0203\u0201\1\0\0\0\u0204\u0207\3,\26\0\u0206\u0204\1\0\0\0\u0206"+
		"\u0207\1\0\0\0\u0207)\1\0\0\0\u0208\u020a\5\31\0\0\u020a\u020c\3\34\16"+
		"\0\u020c\u020d\3\24\n\0\u020d+\1\0\0\0\u020e\u0210\5\32\0\0\u0210\u0211"+
		"\3\24\n\0\u0211-\1\0\0\0\u0212\u0215\3\60\30\0\u0214\u0212\1\0\0\0\u0215"+
		"\u0218\1\0\0\0\u0216\u0214\1\0\0\0\u0216\u0217\1\0\0\0\u0217/\1\0\0\0"+
		"\u0218\u0216\1\0\0\0\u0219\u021e\3\6\3\0\u021b\u021e\38\34\0\u021d\u0219"+
		"\1\0\0\0\u021d\u021b\1\0\0\0\u021e\61\1\0\0\0\u021f\u0221\5\26\0\0\u0221"+
		"\u0222\3\34\16\0\u0222\63\1\0\0\0\u0223\u0225\5\30\0\0\u0225\u022d\3\u008a"+
		"E\0\u0227\u0229\5\37\0\0\u0229\u022c\3\u008aE\0\u022b\u0227\1\0\0\0\u022c"+
		"\u022f\1\0\0\0\u022d\u022b\1\0\0\0\u022d\u022e\1\0\0\0\u022e\65\1\0\0"+
		"\0\u022f\u022d\1\0\0\0\u0230\u0232\5\27\0\0\u0232\u0233\3\34\16\0\u0233"+
		"\67\1\0\0\0\u0234\u0236\5\64\0\0\u0236\u0238\3\u0088D\0\u0238\u0239\3"+
		"\24\n\0\u02399\1\0\0\0\u023a\u023d\3<\36\0\u023c\u023a\1\0\0\0\u023d\u023e"+
		"\1\0\0\0\u023e\u023c\1\0\0\0\u023e\u023f\1\0\0\0\u023f;\1\0\0\0\u0240"+
		"\u0249\5\24\0\0\u0242\u0249\5\25\0\0\u0244\u0249\5\23\0\0\u0246\u0249"+
		"\5\16\0\0\u0248\u0240\1\0\0\0\u0248\u0242\1\0\0\0\u0248\u0244\1\0\0\0"+
		"\u0248\u0246\1\0\0\0\u0249=\1\0\0\0\u024a\u024b\3@ \0\u024b?\1\0\0\0\u024c"+
		"\u0254\3B!\0\u024e\u0250\5,\0\0\u0250\u0253\3B!\0\u0252\u024e\1\0\0\0"+
		"\u0253\u0256\1\0\0\0\u0254\u0252\1\0\0\0\u0254\u0255\1\0\0\0\u0255A\1"+
		"\0\0\0\u0256\u0254\1\0\0\0\u0257\u025d\3F#\0\u0259\u025b\5\65\0\0\u025b"+
		"\u025e\3\u0088D\0\u025d\u0259\1\0\0\0\u025d\u025e\1\0\0\0\u025eC\1\0\0"+
		"\0\u025f\u0267\3F#\0\u0261\u0263\5,\0\0\u0263\u0266\3F#\0\u0265\u0261"+
		"\1\0\0\0\u0266\u0269\1\0\0\0\u0267\u0265\1\0\0\0\u0267\u0268\1\0\0\0\u0268"+
		"E\1\0\0\0\u0269\u0267\1\0\0\0\u026a\u0270\3H$\0\u026c\u0271\3j\65\0\u026e"+
		"\u0271\1\0\0\0\u0270\u026c\1\0\0\0\u0270\u026e\1\0\0\0\u0271\u0277\1\0"+
		"\0\0\u0272\u0277\3j\65\0\u0274\u0277\1\0\0\0\u0276\u026a\1\0\0\0\u0276"+
		"\u0272\1\0\0\0\u0276\u0274\1\0\0\0\u0277G\1\0\0\0\u0278\u027b\3J%\0\u027a"+
		"\u0278\1\0\0\0\u027b\u027c\1\0\0\0\u027c\u027a\1\0\0\0\u027c\u027d\1\0"+
		"\0\0\u027dI\1\0\0\0\u027e\u0284\3L&\0\u0280\u0285\3T*\0\u0282\u0285\1"+
		"\0\0\0\u0284\u0280\1\0\0\0\u0284\u0282\1\0\0\0\u0285\u029f\1\0\0\0\u0286"+
		"\u028c\3V+\0\u0288\u028d\3T*\0\u028a\u028d\1\0\0\0\u028c\u0288\1\0\0\0"+
		"\u028c\u028a\1\0\0\0\u028d\u029f\1\0\0\0\u028e\u029f\3P(\0\u0290\u0294"+
		"\3\24\n\0\u0292\u0295\5\'\0\0\u0294\u0292\1\0\0\0\u0294\u0295\1\0\0\0"+
		"\u0295\u029f\1\0\0\0\u0296\u029c\3N\'\0\u0298\u029d\3T*\0\u029a\u029d"+
		"\1\0\0\0\u029c\u0298\1\0\0\0\u029c\u029a\1\0\0\0\u029d\u029f\1\0\0\0\u029e"+
		"\u027e\1\0\0\0\u029e\u0286\1\0\0\0\u029e\u028e\1\0\0\0\u029e\u0290\1\0"+
		"\0\0\u029e\u0296\1\0\0\0\u029fK\1\0\0\0\u02a0\u02a6\3\u0088D\0\u02a2\u02a7"+
		"\5&\0\0\u02a4\u02a7\5+\0\0\u02a6\u02a2\1\0\0\0\u02a6\u02a4\1\0\0\0\u02a7"+
		"\u02b2\1\0\0\0\u02a8\u02b3\3V+\0\u02aa\u02b0\3^/\0\u02ac\u02b1\5-\0\0"+
		"\u02ae\u02b1\5(\0\0\u02b0\u02ac\1\0\0\0\u02b0\u02ae\1\0\0\0\u02b0\u02b1"+
		"\1\0\0\0\u02b1\u02b3\1\0\0\0\u02b2\u02a8\1\0\0\0\u02b2\u02aa\1\0\0\0\u02b3"+
		"M\1\0\0\0\u02b4\u02b6\5\63\0\0\u02b6\u02ba\3J%\0\u02b8\u02bb\3J%\0\u02ba"+
		"\u02b8\1\0\0\0\u02bb\u02bc\1\0\0\0\u02bc\u02ba\1\0\0\0\u02bc\u02bd\1\0"+
		"\0\0\u02bd\u02be\1\0\0\0\u02be\u02bf\5\"\0\0\u02bfO\1\0\0\0\u02c0\u02c6"+
		"\3^/\0\u02c2\u02c7\3R)\0\u02c4\u02c7\1\0\0\0\u02c6\u02c2\1\0\0\0\u02c6"+
		"\u02c4\1\0\0\0\u02c7Q\1\0\0\0\u02c8\u02cf\3T*\0\u02ca\u02cf\5-\0\0\u02cc"+
		"\u02cf\5(\0\0\u02ce\u02c8\1\0\0\0\u02ce\u02ca\1\0\0\0\u02ce\u02cc\1\0"+
		"\0\0\u02cfS\1\0\0\0\u02d0\u02d7\5\'\0\0\u02d2\u02d7\5)\0\0\u02d4\u02d7"+
		"\5*\0\0\u02d6\u02d0\1\0\0\0\u02d6\u02d2\1\0\0\0\u02d6\u02d4\1\0\0\0\u02d7"+
		"U\1\0\0\0\u02d8\u02dc\3b\61\0\u02da\u02dd\7\1\0\0\u02dc\u02da\1\0\0\0"+
		"\u02dc\u02dd\1\0\0\0\u02dd\u02f9\1\0\0\0\u02de\u02e2\3d\62\0\u02e0\u02e3"+
		"\7\2\0\0\u02e2\u02e0\1\0\0\0\u02e2\u02e3\1\0\0\0\u02e3\u02f9\1\0\0\0\u02e4"+
		"\u02f9\3`\60\0\u02e6\u02ea\3X,\0\u02e8\u02eb\7\3\0\0\u02ea\u02e8\1\0\0"+
		"\0\u02ea\u02eb\1\0\0\0\u02eb\u02f9\1\0\0\0\u02ec\u02f0\5/\0\0\u02ee\u02f1"+
		"\3f\63\0\u02f0\u02ee\1\0\0\0\u02f0\u02f1\1\0\0\0\u02f1\u02f6\1\0\0\0\u02f2"+
		"\u02f7\5-\0\0\u02f4\u02f7\5(\0\0\u02f6\u02f2\1\0\0\0\u02f6\u02f4\1\0\0"+
		"\0\u02f6\u02f7\1\0\0\0\u02f7\u02f9\1\0\0\0\u02f8\u02d8\1\0\0\0\u02f8\u02de"+
		"\1\0\0\0\u02f8\u02e4\1\0\0\0\u02f8\u02e6\1\0\0\0\u02f8\u02ec\1\0\0\0\u02f9"+
		"W\1\0\0\0\u02fa\u02fc\5\66\0\0\u02fc\u0303\3\\.\0\u02fe\u0300\5\66\0\0"+
		"\u0300\u0303\3Z-\0\u0302\u02fa\1\0\0\0\u0302\u02fe\1\0\0\0\u0303Y\1\0"+
		"\0\0\u0304\u0306\5!\0\0\u0306\u030e\3\\.\0\u0308\u030a\5,\0\0\u030a\u030d"+
		"\3\\.\0\u030c\u0308\1\0\0\0\u030d\u0310\1\0\0\0\u030e\u030c\1\0\0\0\u030e"+
		"\u030f\1\0\0\0\u030f\u0311\1\0\0\0\u0310\u030e\1\0\0\0\u0311\u0312\5\""+
		"\0\0\u0312[\1\0\0\0\u0313\u031a\58\0\0\u0315\u031a\5;\0\0\u0317\u031a"+
		"\3b\61\0\u0319\u0313\1\0\0\0\u0319\u0315\1\0\0\0\u0319\u0317\1\0\0\0\u031a"+
		"]\1\0\0\0\u031b\u032a\5!\0\0\u031d\u0320\3\6\3\0\u031f\u031d\1\0\0\0\u031f"+
		"\u0320\1\0\0\0\u0320\u0325\1\0\0\0\u0321\u0324\38\34\0\u0323\u0321\1\0"+
		"\0\0\u0324\u0327\1\0\0\0\u0325\u0323\1\0\0\0\u0325\u0326\1\0\0\0\u0326"+
		"\u0328\1\0\0\0\u0327\u0325\1\0\0\0\u0328\u032b\5\35\0\0\u032a\u031f\1"+
		"\0\0\0\u032a\u032b\1\0\0\0\u032b\u032c\1\0\0\0\u032c\u032e\3D\"\0\u032e"+
		"\u032f\5\"\0\0\u032f_\1\0\0\0\u0330\u0334\59\0\0\u0332\u0335\3\34\16\0"+
		"\u0334\u0332\1\0\0\0\u0334\u0335\1\0\0\0\u0335\u033e\1\0\0\0\u0336\u033b"+
		"\5-\0\0\u0338\u033b\5(\0\0\u033a\u0336\1\0\0\0\u033a\u0338\1\0\0\0\u033b"+
		"\u033f\1\0\0\0\u033c\u033f\1\0\0\0\u033e\u033a\1\0\0\0\u033e\u033c\1\0"+
		"\0\0\u033fa\1\0\0\0\u0340\u0342\5;\0\0\u0342\u0344\5\60\0\0\u0344\u0345"+
		"\5;\0\0\u0345c\1\0\0\0\u0346\u034a\58\0\0\u0348\u034b\3f\63\0\u034a\u0348"+
		"\1\0\0\0\u034a\u034b\1\0\0\0\u034b\u0353\1\0\0\0\u034c\u0350\5;\0\0\u034e"+
		"\u0351\3f\63\0\u0350\u034e\1\0\0\0\u0350\u0351\1\0\0\0\u0351\u0353\1\0"+
		"\0\0\u0352\u0346\1\0\0\0\u0352\u034c\1\0\0\0\u0353e\1\0\0\0\u0354\u0356"+
		"\5$\0\0\u0356\u035e\3h\64\0\u0358\u035a\5\37\0\0\u035a\u035d\3h\64\0\u035c"+
		"\u0358\1\0\0\0\u035d\u0360\1\0\0\0\u035e\u035c\1\0\0\0\u035e\u035f\1\0"+
		"\0\0\u035f\u0361\1\0\0\0\u0360\u035e\1\0\0\0\u0361\u0362\5%\0\0\u0362"+
		"g\1\0\0\0\u0363\u0370\3\u008aE\0\u0365\u0367\3\u0088D\0\u0367\u036d\5"+
		"&\0\0\u0369\u036e\3\u008aE\0\u036b\u036e\5;\0\0\u036d\u0369\1\0\0\0\u036d"+
		"\u036b\1\0\0\0\u036e\u0370\1\0\0\0\u036f\u0363\1\0\0\0\u036f\u0365\1\0"+
		"\0\0\u0370i\1\0\0\0\u0371\u0374\3l\66\0\u0373\u0371\1\0\0\0\u0374\u0377"+
		"\1\0\0\0\u0375\u0373\1\0\0\0\u0375\u0376\1\0\0\0\u0376\u0378\1\0\0\0\u0377"+
		"\u0375\1\0\0\0\u0378\u0379\3n\67\0\u0379k\1\0\0\0\u037a\u037c\5\62\0\0"+
		"\u037c\u037e\5~\0\0\u037e\u037f\3p8\0\u037fm\1\0\0\0\u0380\u0382\5\62"+
		"\0\0\u0382\u0383\3p8\0\u0383o\1\0\0\0\u0384\u038d\3r9\0\u0386\u038d\3"+
		"~?\0\u0388\u038d\5\61\0\0\u038a\u038d\1\0\0\0\u038c\u0384\1\0\0\0\u038c"+
		"\u0386\1\0\0\0\u038c\u0388\1\0\0\0\u038c\u038a\1\0\0\0\u038dq\1\0\0\0"+
		"\u038e\u0391\3t:\0\u0390\u038e\1\0\0\0\u0391\u0392\1\0\0\0\u0392\u0390"+
		"\1\0\0\0\u0392\u0393\1\0\0\0\u0393s\1\0\0\0\u0394\u0398\3v;\0\u0396\u0399"+
		"\3T*\0\u0398\u0396\1\0\0\0\u0398\u0399\1\0\0\0\u0399\u03a5\1\0\0\0\u039a"+
		"\u03a0\3|>\0\u039c\u03a1\3T*\0\u039e\u03a1\1\0\0\0\u03a0\u039c\1\0\0\0"+
		"\u03a0\u039e\1\0\0\0\u03a1\u03a5\1\0\0\0\u03a2\u03a5\3x<\0\u03a4\u0394"+
		"\1\0\0\0\u03a4\u039a\1\0\0\0\u03a4\u03a2\1\0\0\0\u03a5u\1\0\0\0\u03a6"+
		"\u03aa\58\0\0\u03a8\u03ab\3f\63\0\u03aa\u03a8\1\0\0\0\u03aa\u03ab\1\0"+
		"\0\0\u03ab\u03ae\1\0\0\0\u03ac\u03af\3\34\16\0\u03ae\u03ac\1\0\0\0\u03ae"+
		"\u03af\1\0\0\0\u03af\u03bf\1\0\0\0\u03b0\u03bf\59\0\0\u03b2\u03b6\5;\0"+
		"\0\u03b4\u03b7\3f\63\0\u03b6\u03b4\1\0\0\0\u03b6\u03b7\1\0\0\0\u03b7\u03bf"+
		"\1\0\0\0\u03b8\u03ba\5.\0\0\u03ba\u03bf\3\u0088D\0\u03bc\u03bf\3\24\n"+
		"\0\u03be\u03a6\1\0\0\0\u03be\u03b0\1\0\0\0\u03be\u03b2\1\0\0\0\u03be\u03b8"+
		"\1\0\0\0\u03be\u03bc\1\0\0\0\u03bfw\1\0\0\0\u03c0\u03c2\5!\0\0\u03c2\u03c4"+
		"\3r9\0\u03c4\u03c6\5\"\0\0\u03c6\u03c7\3z=\0\u03c7y\1\0\0\0\u03c8\u03cd"+
		"\5\'\0\0\u03ca\u03cd\5)\0\0\u03cc\u03c8\1\0\0\0\u03cc\u03ca\1\0\0\0\u03cd"+
		"{\1\0\0\0\u03ce\u03d0\5\63\0\0\u03d0\u03d6\3v;\0\u03d2\u03d5\3t:\0\u03d4"+
		"\u03d2\1\0\0\0\u03d5\u03d8\1\0\0\0\u03d6\u03d4\1\0\0\0\u03d6\u03d7\1\0"+
		"\0\0\u03d7\u03d9\1\0\0\0\u03d8\u03d6\1\0\0\0\u03d9\u03da\5\"\0\0\u03da"+
		"}\1\0\0\0\u03db\u03dd\5\33\0\0\u03dd\u03df\5!\0\0\u03df\u03e1\3\u0084"+
		"B\0\u03e1\u03e7\5\"\0\0\u03e3\u03e8\5\6\0\0\u03e5\u03e8\5\7\0\0\u03e7"+
		"\u03e3\1\0\0\0\u03e7\u03e5\1\0\0\0\u03e8\u03f0\1\0\0\0\u03e9\u03f0\3\u0080"+
		"@\0\u03eb\u03f0\3\u0082A\0\u03ed\u03f0\3\24\n\0\u03ef\u03db\1\0\0\0\u03ef"+
		"\u03e9\1\0\0\0\u03ef\u03eb\1\0\0\0\u03ef\u03ed\1\0\0\0\u03f0\177\1\0\0"+
		"\0\u03f1\u03f3\3\u0088D\0\u03f3\u03f5\5!\0\0\u03f5\u03f7\3\u0084B\0\u03f7"+
		"\u03f8\5\"\0\0\u03f8\u0081\1\0\0\0\u03f9\u03fb\5!\0\0\u03fb\u03fd\3\24"+
		"\n\0\u03fd\u03ff\5\"\0\0\u03ff\u0401\5!\0\0\u0401\u0403\3\u0084B\0\u0403"+
		"\u0404\5\"\0\0\u0404\u0083\1\0\0\0\u0405\u040d\3\u0086C\0\u0407\u0409"+
		"\5\37\0\0\u0409\u040c\3\u0086C\0\u040b\u0407\1\0\0\0\u040c\u040f\1\0\0"+
		"\0\u040d\u040b\1\0\0\0\u040d\u040e\1\0\0\0\u040e\u0413\1\0\0\0\u040f\u040d"+
		"\1\0\0\0\u0410\u0413\1\0\0\0\u0412\u0405\1\0\0\0\u0412\u0410\1\0\0\0\u0413"+
		"\u0085\1\0\0\0\u0414\u0416\3\u0088D\0\u0416\u0418\5&\0\0\u0418\u0419\3"+
		"\24\n\0\u0419\u0087\1\0\0\0\u041a\u0421\59\0\0\u041c\u0421\58\0\0\u041e"+
		"\u0421\5\33\0\0\u0420\u041a\1\0\0\0\u0420\u041c\1\0\0\0\u0420\u041e\1"+
		"\0\0\0\u0421\u0089\1\0\0\0\u0422\u042a\3\u0088D\0\u0424\u0426\5/\0\0\u0426"+
		"\u0429\3\u0088D\0\u0428\u0424\1\0\0\0\u0429\u042c\1\0\0\0\u042a\u0428"+
		"\1\0\0\0\u042a\u042b\1\0\0\0\u042b\u008b\1\0\0\0\u042c\u042a\1\0\0\0\u042d"+
		"\u042f\3F#\0\u042f\u0430\5\uffff\0\0\u0430\u008d\1\0\0\0\u0431\u0433\3"+
		"J%\0\u0433\u0434\5\uffff\0\0\u0434\u008f\1\0\0\0\u0435\u0437\3&\23\0\u0437"+
		"\u0438\5\uffff\0\0\u0438\u0091\1\0\0\0\u0439\u043b\3^/\0\u043b\u043c\5"+
		"\uffff\0\0\u043c\u0093\1\0\0\0d\u0096\1\u00a2\1\u00ab\1\u00be\1\u00c8"+
		"\1\u00d2\1\u00e5\1\u00f1\1\u00fe\1\u0106\1\u0112\1\u0118\1\u0148\1\u014a"+
		"\1\u0155\1\u015e\1\u0169\1\u0172\1\u017b\1\u017e\1\u0186\1\u018f\1\u0192"+
		"\1\u019a\1\u01a5\1\u01ad\1\u01b8\1\u01c4\1\u01d0\1\u01d6\1\u01db\1\u01df"+
		"\1\u01e5\1\u01e9\1\u01ed\1\u01f1\1\u0201\1\u0206\1\u0216\1\u021d\1\u022d"+
		"\1\u023e\1\u0248\1\u0254\1\u025d\1\u0267\1\u0270\1\u0276\1\u027c\1\u0284"+
		"\1\u028c\1\u0294\1\u029c\1\u029e\1\u02a6\1\u02b0\1\u02b2\1\u02bc\1\u02c6"+
		"\1\u02ce\1\u02d6\1\u02dc\1\u02e2\1\u02ea\1\u02f0\1\u02f6\1\u02f8\1\u0302"+
		"\1\u030e\1\u0319\1\u031f\1\u0325\1\u032a\1\u0334\1\u033a\1\u033e\1\u034a"+
		"\1\u0350\1\u0352\1\u035e\1\u036d\1\u036f\1\u0375\1\u038c\1\u0392\1\u0398"+
		"\1\u03a0\1\u03a4\1\u03aa\1\u03ae\1\u03b6\1\u03be\1\u03cc\1\u03d6\1\u03e7"+
		"\1\u03ef\1\u040d\1\u0412\1\u0420\1\u042a\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}