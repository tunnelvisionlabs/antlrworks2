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
		DOLLAR=46, PROTECTED=19, LT=36, STAR=41, NESTED_ACTION=73, DOUBLE_ANGLE_STRING_LITERAL=7, 
		FRAGMENT=14, ACTION_ELEMENT=75, NOT=54, TREE_BEGIN=51, ARG_ACTION_LT=62, 
		TOKEN_REF=56, LPAREN=33, ARG_ACTION_ELEMENT=70, ARG_ACTION_RPAREN=65, 
		ARG_ACTION_GT=63, AT=52, RPAREN=34, ARG_ACTION_LPAREN=64, IMPORT=13, STRING_LITERAL=59, 
		TREE=17, SCOPE=12, END_ACTION=77, ETC=49, COMMA=31, DOC_COMMENT=3, BLOCK_COMMENT=4, 
		PLUS=42, BEGIN_ACTION=9, DOUBLE_QUOTE_STRING_LITERAL=6, DOT=47, MODE=28, 
		GRAMMAR=18, ARG_ACTION_ESCAPE=68, RETURNS=22, ARG_ACTION_TEXT=71, LOCALS=23, 
		IMPLIES=35, RBRACE=55, POUND=53, LINE_COMMENT=5, PRIVATE=21, RARROW=50, 
		END_ARG_ACTION=72, TOKENS=11, RANGE=48, THROWS=24, INT=58, BANG=40, SEMI=32, 
		ROOT=45, RULE_REF=57, ARG_ACTION_EQUALS=66, COLON=29, COLONCOLON=30, WS=60, 
		ACTION_COMMENT=74, QUESTION=39, FINALLY=26, TEMPLATE=27, ACTION_TEXT=76, 
		LEXER=15, ERRCHAR=61, ARG_ACTION_COMMA=67, OR=44, ASSIGN=38, PLUS_ASSIGN=43, 
		GT=37, CATCH=25, ARG_ACTION_WORD=69, PUBLIC=20, PARSER=16, OPTIONS=10, 
		BEGIN_ARG_ACTION=8, RULE=78, PREC_RULE=79, RULES=80, RULEMODIFIERS=81, 
		RULEACTIONS=82, BLOCK=83, REWRITE_BLOCK=84, REWRITE_SEQ=85, OPTIONAL=86, 
		CLOSURE=87, POSITIVE_CLOSURE=88, SET=89, CHAR_RANGE=90, EPSILON=91, ALT=92, 
		ALTLIST=93, ID=94, ARG=95, ARGLIST=96, RET=97, COMBINED=98, INITACTION=99, 
		LABEL=100, WILDCARD=101, LIST=102, ELEMENT_OPTIONS=103, ST_RESULT=104, 
		RESULT=105, ALT_REWRITE=106, DOWN_TOKEN=107, UP_TOKEN=108, SEMPRED=109;
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
		"END_ARG_ACTION", "NESTED_ACTION", "ACTION_COMMENT", "ACTION_ELEMENT", 
		"ACTION_TEXT", "END_ACTION", "RULE", "PREC_RULE", "RULES", "RULEMODIFIERS", 
		"RULEACTIONS", "BLOCK", "REWRITE_BLOCK", "REWRITE_SEQ", "OPTIONAL", "CLOSURE", 
		"POSITIVE_CLOSURE", "SET", "CHAR_RANGE", "EPSILON", "ALT", "ALTLIST", 
		"ID", "ARG", "ARGLIST", "RET", "COMBINED", "INITACTION", "LABEL", "WILDCARD", 
		"LIST", "ELEMENT_OPTIONS", "ST_RESULT", "RESULT", "ALT_REWRITE", "DOWN_TOKEN", 
		"UP_TOKEN", "SEMPRED"
	};
	public static final int
		RULE_grammarSpec = 0, RULE_grammarType = 1, RULE_prequelConstruct = 2, 
		RULE_optionsSpec = 3, RULE_option = 4, RULE_optionValue = 5, RULE_delegateGrammars = 6, 
		RULE_delegateGrammar = 7, RULE_tokensSpec = 8, RULE_tokenSpec = 9, RULE_actionBlock = 10, 
		RULE_argActionBlock = 11, RULE_action = 12, RULE_actionScopeName = 13, 
		RULE_mode_ = 14, RULE_rules = 15, RULE_rule = 16, RULE_exceptionGroup = 17, 
		RULE_exceptionHandler = 18, RULE_finallyClause = 19, RULE_rulePrequels = 20, 
		RULE_rulePrequel = 21, RULE_ruleReturns = 22, RULE_throwsSpec = 23, RULE_locals_ = 24, 
		RULE_ruleAction = 25, RULE_ruleModifiers = 26, RULE_ruleModifier = 27, 
		RULE_ruleBlock = 28, RULE_ruleAltList = 29, RULE_labeledAlt = 30, RULE_altList = 31, 
		RULE_alternative = 32, RULE_elements = 33, RULE_element = 34, RULE_labeledElement = 35, 
		RULE_treeSpec = 36, RULE_ebnf = 37, RULE_blockSuffix = 38, RULE_ebnfSuffix = 39, 
		RULE_atom = 40, RULE_notSet = 41, RULE_blockSet = 42, RULE_setElement = 43, 
		RULE_block = 44, RULE_ruleref = 45, RULE_range = 46, RULE_terminal = 47, 
		RULE_elementOptions = 48, RULE_elementOption = 49, RULE_rewrite = 50, 
		RULE_predicatedRewrite = 51, RULE_nakedRewrite = 52, RULE_rewriteAlt = 53, 
		RULE_rewriteTreeAlt = 54, RULE_rewriteTreeElement = 55, RULE_rewriteTreeAtom = 56, 
		RULE_rewriteTreeEbnf = 57, RULE_rewriteEbnfSuffix = 58, RULE_rewriteTree = 59, 
		RULE_rewriteTemplate = 60, RULE_rewriteTemplateRef = 61, RULE_rewriteIndirectTemplateHead = 62, 
		RULE_rewriteTemplateArgs = 63, RULE_rewriteTemplateArg = 64, RULE_id = 65, 
		RULE_qid = 66, RULE_alternativeEntry = 67, RULE_elementEntry = 68, RULE_ruleEntry = 69, 
		RULE_blockEntry = 70;
	public static final String[] ruleNames = {
		"grammarSpec", "grammarType", "prequelConstruct", "optionsSpec", "option", 
		"optionValue", "delegateGrammars", "delegateGrammar", "tokensSpec", "tokenSpec", 
		"actionBlock", "argActionBlock", "action", "actionScopeName", "mode_", 
		"rules", "rule", "exceptionGroup", "exceptionHandler", "finallyClause", 
		"rulePrequels", "rulePrequel", "ruleReturns", "throwsSpec", "locals_", 
		"ruleAction", "ruleModifiers", "ruleModifier", "ruleBlock", "ruleAltList", 
		"labeledAlt", "altList", "alternative", "elements", "element", "labeledElement", 
		"treeSpec", "ebnf", "blockSuffix", "ebnfSuffix", "atom", "notSet", "blockSet", 
		"setElement", "block", "ruleref", "range", "terminal", "elementOptions", 
		"elementOption", "rewrite", "predicatedRewrite", "nakedRewrite", "rewriteAlt", 
		"rewriteTreeAlt", "rewriteTreeElement", "rewriteTreeAtom", "rewriteTreeEbnf", 
		"rewriteEbnfSuffix", "rewriteTree", "rewriteTemplate", "rewriteTemplateRef", 
		"rewriteIndirectTemplateHead", "rewriteTemplateArgs", "rewriteTemplateArg", 
		"id", "qid", "alternativeEntry", "elementEntry", "ruleEntry", "blockEntry"
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
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==DOC_COMMENT ) {
				{
				setState(142); match(DOC_COMMENT);
				}
			}

			setState(146); grammarType();
			setState(148); id();
			setState(150); match(SEMI);
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==OPTIONS || _la==TOKENS || _la==IMPORT || _la==AT ) {
				{
				{
				setState(152); prequelConstruct();
				}
				}
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(159); rules();
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==MODE ) {
				{
				{
				setState(161); mode_();
				}
				}
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(168); match(EOF);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
			setState(184);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case LEXER:
					{
					setState(170); _localctx.t = match(LEXER);
					setState(172); _localctx.g = match(GRAMMAR);
					}
					break;
				case PARSER:
					{
					setState(174); _localctx.t = match(PARSER);
					setState(176); _localctx.g = match(GRAMMAR);
					}
					break;
				case TREE:
					{
					setState(178); _localctx.t = match(TREE);
					setState(180); _localctx.g = match(GRAMMAR);
					}
					break;
				case GRAMMAR:
					{
					setState(182); _localctx.g = match(GRAMMAR);
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
			setState(194);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case OPTIONS:
					enterOuterAlt(_localctx, 1);
					{
					setState(186); optionsSpec();
					}
					break;
				case IMPORT:
					enterOuterAlt(_localctx, 2);
					{
					setState(188); delegateGrammars();
					}
					break;
				case TOKENS:
					enterOuterAlt(_localctx, 3);
					{
					setState(190); tokensSpec();
					}
					break;
				case AT:
					enterOuterAlt(_localctx, 4);
					{
					setState(192); action();
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
			setState(196); match(OPTIONS);
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==TEMPLATE || _la==TOKEN_REF || _la==RULE_REF ) {
				{
				{
				setState(198); option();
				setState(200); match(SEMI);
				}
				}
				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(207); match(RBRACE);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
			setState(209); id();
			setState(211); match(ASSIGN);
			setState(213); optionValue();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
			setState(223);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case TEMPLATE:
				case TOKEN_REF:
				case RULE_REF:
					enterOuterAlt(_localctx, 1);
					{
					setState(215); qid();
					}
					break;
				case STRING_LITERAL:
					enterOuterAlt(_localctx, 2);
					{
					setState(217); match(STRING_LITERAL);
					}
					break;
				case INT:
					enterOuterAlt(_localctx, 3);
					{
					setState(219); match(INT);
					}
					break;
				case STAR:
					enterOuterAlt(_localctx, 4);
					{
					setState(221); match(STAR);
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
			setState(225); match(IMPORT);
			setState(227); delegateGrammar();
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==COMMA ) {
				{
				{
				setState(229); match(COMMA);
				setState(231); delegateGrammar();
				}
				}
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(238); match(SEMI);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
			setState(248);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,8,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(240); id();
					setState(242); match(ASSIGN);
					setState(244); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(246); id();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
			setState(250); match(TOKENS);
			setState(254); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(252); tokenSpec();
				}
				}
				setState(256); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TEMPLATE || _la==TOKEN_REF || _la==RULE_REF );
			setState(258); match(RBRACE);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
			setState(274);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,11,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(260); id();
					setState(268);
					_errHandler.sync(this);
					switch ( _input.LA(1) ) {
						case ASSIGN:
							{
							setState(262); match(ASSIGN);
							setState(264); match(STRING_LITERAL);
							}
							break;
						case SEMI:
							{
							}
							break;
						default :
							throw new NoViableAltException(this);
					}
					setState(270); match(SEMI);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(272); match(RULE_REF);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
			setState(276); match(BEGIN_ACTION);
			setState(288);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==BEGIN_ACTION || _la==ACTION_COMMENT || _la==ACTION_ELEMENT || _la==ACTION_TEXT ) {
				{
				setState(286);
				_errHandler.sync(this);
				switch ( _input.LA(1) ) {
					case BEGIN_ACTION:
						{
						setState(278); actionBlock();
						}
						break;
					case ACTION_COMMENT:
						{
						setState(280); match(ACTION_COMMENT);
						}
						break;
					case ACTION_ELEMENT:
						{
						setState(282); match(ACTION_ELEMENT);
						}
						break;
					case ACTION_TEXT:
						{
						setState(284); match(ACTION_TEXT);
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				}
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(291); match(END_ACTION);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
		}
		finally {
			exitRule(RULE_actionBlock);
			// System.out.println("exit "+ruleNames[RULE_actionBlock]);
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
		argActionBlockContext _localctx = new argActionBlockContext(_ctx, 22);
		enterRule(_localctx, RULE_argActionBlock);
		//System.out.println("enter "+ruleNames[RULE_argActionBlock]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293); match(BEGIN_ARG_ACTION);
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==ARG_ACTION_LT || _la==ARG_ACTION_GT || _la==ARG_ACTION_LPAREN || _la==ARG_ACTION_RPAREN || _la==ARG_ACTION_EQUALS || _la==ARG_ACTION_COMMA || _la==ARG_ACTION_ESCAPE || _la==ARG_ACTION_WORD || _la==ARG_ACTION_ELEMENT || _la==ARG_ACTION_TEXT ) {
				{
				{
				setState(295);
				_input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ARG_ACTION_LT || _la==ARG_ACTION_GT || _la==ARG_ACTION_LPAREN || _la==ARG_ACTION_RPAREN || _la==ARG_ACTION_EQUALS || _la==ARG_ACTION_COMMA || _la==ARG_ACTION_ESCAPE || _la==ARG_ACTION_WORD || _la==ARG_ACTION_ELEMENT || _la==ARG_ACTION_TEXT) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(301);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(302); match(END_ARG_ACTION);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		actionContext _localctx = new actionContext(_ctx, 24);
		enterRule(_localctx, RULE_action);
		//System.out.println("enter "+ruleNames[RULE_action]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304); match(AT);
			setState(310);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(306); actionScopeName();
					setState(308); match(COLONCOLON);
					}
					break;
			}
			setState(312); id();
			setState(314); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		actionScopeNameContext _localctx = new actionScopeNameContext(_ctx, 26);
		enterRule(_localctx, RULE_actionScopeName);
		//System.out.println("enter "+ruleNames[RULE_actionScopeName]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(322);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case TEMPLATE:
				case TOKEN_REF:
				case RULE_REF:
					enterOuterAlt(_localctx, 1);
					{
					setState(316); id();
					}
					break;
				case LEXER:
					enterOuterAlt(_localctx, 2);
					{
					setState(318); match(LEXER);
					}
					break;
				case PARSER:
					enterOuterAlt(_localctx, 3);
					{
					setState(320); match(PARSER);
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		mode_Context _localctx = new mode_Context(_ctx, 28);
		enterRule(_localctx, RULE_mode_);
		//System.out.println("enter "+ruleNames[RULE_mode_]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324); match(MODE);
			setState(326); id();
			setState(328); match(SEMI);
			setState(332); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(330); rule();
				}
				}
				setState(334); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DOC_COMMENT || _la==FRAGMENT || _la==PROTECTED || _la==PUBLIC || _la==PRIVATE || _la==TEMPLATE || _la==TOKEN_REF || _la==RULE_REF );
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rulesContext _localctx = new rulesContext(_ctx, 30);
		enterRule(_localctx, RULE_rules);
		//System.out.println("enter "+ruleNames[RULE_rules]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==DOC_COMMENT || _la==FRAGMENT || _la==PROTECTED || _la==PUBLIC || _la==PRIVATE || _la==TEMPLATE || _la==TOKEN_REF || _la==RULE_REF ) {
				{
				{
				setState(336); rule();
				}
				}
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		ruleContext _localctx = new ruleContext(_ctx, 32);
		enterRule(_localctx, RULE_rule);
		//System.out.println("enter "+ruleNames[RULE_rule]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==DOC_COMMENT ) {
				{
				setState(343); match(DOC_COMMENT);
				}
			}

			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==FRAGMENT || _la==PROTECTED || _la==PUBLIC || _la==PRIVATE ) {
				{
				setState(347); ruleModifiers();
				}
			}

			setState(351); _localctx.name = id();
			setState(355);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==BEGIN_ARG_ACTION ) {
				{
				setState(353); argActionBlock();
				}
			}

			setState(359);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==RETURNS ) {
				{
				setState(357); ruleReturns();
				}
			}

			setState(363);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==THROWS ) {
				{
				setState(361); throwsSpec();
				}
			}

			setState(367);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==LOCALS ) {
				{
				setState(365); locals_();
				}
			}

			setState(369); rulePrequels();
			setState(371); match(COLON);
			setState(373); ruleBlock();
			setState(375); match(SEMI);
			setState(377); exceptionGroup();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		exceptionGroupContext _localctx = new exceptionGroupContext(_ctx, 34);
		enterRule(_localctx, RULE_exceptionGroup);
		//System.out.println("enter "+ruleNames[RULE_exceptionGroup]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==CATCH ) {
				{
				{
				setState(379); exceptionHandler();
				}
				}
				setState(385);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(388);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==FINALLY ) {
				{
				setState(386); finallyClause();
				}
			}

			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		exceptionHandlerContext _localctx = new exceptionHandlerContext(_ctx, 36);
		enterRule(_localctx, RULE_exceptionHandler);
		//System.out.println("enter "+ruleNames[RULE_exceptionHandler]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(390); match(CATCH);
			setState(392); argActionBlock();
			setState(394); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		finallyClauseContext _localctx = new finallyClauseContext(_ctx, 38);
		enterRule(_localctx, RULE_finallyClause);
		//System.out.println("enter "+ruleNames[RULE_finallyClause]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396); match(FINALLY);
			setState(398); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rulePrequelsContext _localctx = new rulePrequelsContext(_ctx, 40);
		enterRule(_localctx, RULE_rulePrequels);
		//System.out.println("enter "+ruleNames[RULE_rulePrequels]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==OPTIONS || _la==AT ) {
				{
				{
				setState(400); rulePrequel();
				}
				}
				setState(406);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rulePrequelContext _localctx = new rulePrequelContext(_ctx, 42);
		enterRule(_localctx, RULE_rulePrequel);
		//System.out.println("enter "+ruleNames[RULE_rulePrequel]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(411);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case OPTIONS:
					enterOuterAlt(_localctx, 1);
					{
					setState(407); optionsSpec();
					}
					break;
				case AT:
					enterOuterAlt(_localctx, 2);
					{
					setState(409); ruleAction();
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		ruleReturnsContext _localctx = new ruleReturnsContext(_ctx, 44);
		enterRule(_localctx, RULE_ruleReturns);
		//System.out.println("enter "+ruleNames[RULE_ruleReturns]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413); match(RETURNS);
			setState(415); argActionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		throwsSpecContext _localctx = new throwsSpecContext(_ctx, 46);
		enterRule(_localctx, RULE_throwsSpec);
		//System.out.println("enter "+ruleNames[RULE_throwsSpec]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417); match(THROWS);
			setState(419); qid();
			setState(427);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==COMMA ) {
				{
				{
				setState(421); match(COMMA);
				setState(423); qid();
				}
				}
				setState(429);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		locals_Context _localctx = new locals_Context(_ctx, 48);
		enterRule(_localctx, RULE_locals_);
		//System.out.println("enter "+ruleNames[RULE_locals_]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(430); match(LOCALS);
			setState(432); argActionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		ruleActionContext _localctx = new ruleActionContext(_ctx, 50);
		enterRule(_localctx, RULE_ruleAction);
		//System.out.println("enter "+ruleNames[RULE_ruleAction]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(434); match(AT);
			setState(436); id();
			setState(438); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		ruleModifiersContext _localctx = new ruleModifiersContext(_ctx, 52);
		enterRule(_localctx, RULE_ruleModifiers);
		//System.out.println("enter "+ruleNames[RULE_ruleModifiers]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(440); ruleModifier();
				}
				}
				setState(444); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==FRAGMENT || _la==PROTECTED || _la==PUBLIC || _la==PRIVATE );
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		ruleModifierContext _localctx = new ruleModifierContext(_ctx, 54);
		enterRule(_localctx, RULE_ruleModifier);
		//System.out.println("enter "+ruleNames[RULE_ruleModifier]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(454);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case PUBLIC:
					enterOuterAlt(_localctx, 1);
					{
					setState(446); match(PUBLIC);
					}
					break;
				case PRIVATE:
					enterOuterAlt(_localctx, 2);
					{
					setState(448); match(PRIVATE);
					}
					break;
				case PROTECTED:
					enterOuterAlt(_localctx, 3);
					{
					setState(450); match(PROTECTED);
					}
					break;
				case FRAGMENT:
					enterOuterAlt(_localctx, 4);
					{
					setState(452); match(FRAGMENT);
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		ruleBlockContext _localctx = new ruleBlockContext(_ctx, 56);
		enterRule(_localctx, RULE_ruleBlock);
		//System.out.println("enter "+ruleNames[RULE_ruleBlock]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(456); ruleAltList();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		ruleAltListContext _localctx = new ruleAltListContext(_ctx, 58);
		enterRule(_localctx, RULE_ruleAltList);
		//System.out.println("enter "+ruleNames[RULE_ruleAltList]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(458); labeledAlt();
			setState(466);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==OR ) {
				{
				{
				setState(460); match(OR);
				setState(462); labeledAlt();
				}
				}
				setState(468);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		labeledAltContext _localctx = new labeledAltContext(_ctx, 60);
		enterRule(_localctx, RULE_labeledAlt);
		//System.out.println("enter "+ruleNames[RULE_labeledAlt]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469); alternative();
			setState(475);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==POUND ) {
				{
				setState(471); match(POUND);
				setState(473); id();
				}
			}

			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		altListContext _localctx = new altListContext(_ctx, 62);
		enterRule(_localctx, RULE_altList);
		//System.out.println("enter "+ruleNames[RULE_altList]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(477); alternative();
			setState(485);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==OR ) {
				{
				{
				setState(479); match(OR);
				setState(481); alternative();
				}
				}
				setState(487);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		alternativeContext _localctx = new alternativeContext(_ctx, 64);
		enterRule(_localctx, RULE_alternative);
		//System.out.println("enter "+ruleNames[RULE_alternative]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(500);
			_errHandler.sync(this);
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
					enterOuterAlt(_localctx, 1);
					{
					setState(488); elements();
					setState(494);
					_errHandler.sync(this);
					switch ( _input.LA(1) ) {
						case RARROW:
							{
							setState(490); rewrite();
							}
							break;
						case EOF:
						case SEMI:
						case RPAREN:
						case OR:
						case POUND:
							{
							}
							break;
						default :
							throw new NoViableAltException(this);
					}
					}
					break;
				case RARROW:
					enterOuterAlt(_localctx, 2);
					{
					setState(496); rewrite();
					}
					break;
				case EOF:
				case SEMI:
				case RPAREN:
				case OR:
				case POUND:
					{
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		elementsContext _localctx = new elementsContext(_ctx, 66);
		enterRule(_localctx, RULE_elements);
		//System.out.println("enter "+ruleNames[RULE_elements]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(504); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(502); _localctx.e = element();
				_localctx.e_list.add(_localctx.e);
				}
				}
				setState(506); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==BEGIN_ACTION || _la==TEMPLATE || _la==LPAREN || _la==DOT || _la==TREE_BEGIN || _la==NOT || _la==TOKEN_REF || _la==RULE_REF || _la==STRING_LITERAL );
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		elementContext _localctx = new elementContext(_ctx, 68);
		enterRule(_localctx, RULE_element);
		//System.out.println("enter "+ruleNames[RULE_element]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			setState(540);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,42,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(508); labeledElement();
					setState(514);
					_errHandler.sync(this);
					switch ( _input.LA(1) ) {
						case QUESTION:
						case STAR:
						case PLUS:
							{
							setState(510); ebnfSuffix();
							}
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
							{
							}
							break;
						default :
							throw new NoViableAltException(this);
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(516); atom();
					setState(522);
					_errHandler.sync(this);
					switch ( _input.LA(1) ) {
						case QUESTION:
						case STAR:
						case PLUS:
							{
							setState(518); ebnfSuffix();
							}
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
							{
							}
							break;
						default :
							throw new NoViableAltException(this);
					}
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(524); ebnf();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(526); actionBlock();
					setState(530);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==QUESTION ) {
						{
						setState(528); match(QUESTION);
						}
					}

					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(532); treeSpec();
					setState(538);
					_errHandler.sync(this);
					switch ( _input.LA(1) ) {
						case QUESTION:
						case STAR:
						case PLUS:
							{
							setState(534); ebnfSuffix();
							}
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
							{
							}
							break;
						default :
							throw new NoViableAltException(this);
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		labeledElementContext _localctx = new labeledElementContext(_ctx, 70);
		enterRule(_localctx, RULE_labeledElement);
		//System.out.println("enter "+ruleNames[RULE_labeledElement]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(542); id();
			setState(548);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case ASSIGN:
					{
					setState(544); _localctx.ass = match(ASSIGN);
					}
					break;
				case PLUS_ASSIGN:
					{
					setState(546); _localctx.ass = match(PLUS_ASSIGN);
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			setState(560);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case DOT:
				case NOT:
				case TOKEN_REF:
				case RULE_REF:
				case STRING_LITERAL:
					{
					setState(550); atom();
					}
					break;
				case LPAREN:
					{
					setState(552); block();
					setState(558);
					_errHandler.sync(this);
					switch ( _input.LA(1) ) {
						case ROOT:
							{
							setState(554); _localctx.op = match(ROOT);
							}
							break;
						case BANG:
							{
							setState(556); _localctx.op = match(BANG);
							}
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
							break;
						default :
							throw new NoViableAltException(this);
					}
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		treeSpecContext _localctx = new treeSpecContext(_ctx, 72);
		enterRule(_localctx, RULE_treeSpec);
		//System.out.println("enter "+ruleNames[RULE_treeSpec]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(562); _localctx.begin = match(TREE_BEGIN);
			setState(564); _localctx.root = element();
			setState(568); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(566); _localctx.kids = element();
				_localctx.kids_list.add(_localctx.kids);
				}
				}
				setState(570); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==BEGIN_ACTION || _la==TEMPLATE || _la==LPAREN || _la==DOT || _la==TREE_BEGIN || _la==NOT || _la==TOKEN_REF || _la==RULE_REF || _la==STRING_LITERAL );
			setState(572); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		ebnfContext _localctx = new ebnfContext(_ctx, 74);
		enterRule(_localctx, RULE_ebnf);
		//System.out.println("enter "+ruleNames[RULE_ebnf]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(574); block();
			setState(580);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case QUESTION:
				case BANG:
				case STAR:
				case PLUS:
				case ROOT:
					{
					setState(576); blockSuffix();
					}
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
					{
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		blockSuffixContext _localctx = new blockSuffixContext(_ctx, 76);
		enterRule(_localctx, RULE_blockSuffix);
		//System.out.println("enter "+ruleNames[RULE_blockSuffix]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(588);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case QUESTION:
				case STAR:
				case PLUS:
					enterOuterAlt(_localctx, 1);
					{
					setState(582); ebnfSuffix();
					}
					break;
				case ROOT:
					enterOuterAlt(_localctx, 2);
					{
					setState(584); match(ROOT);
					}
					break;
				case BANG:
					enterOuterAlt(_localctx, 3);
					{
					setState(586); match(BANG);
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		ebnfSuffixContext _localctx = new ebnfSuffixContext(_ctx, 78);
		enterRule(_localctx, RULE_ebnfSuffix);
		//System.out.println("enter "+ruleNames[RULE_ebnfSuffix]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(596);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case QUESTION:
					enterOuterAlt(_localctx, 1);
					{
					setState(590); match(QUESTION);
					}
					break;
				case STAR:
					enterOuterAlt(_localctx, 2);
					{
					setState(592); match(STAR);
					}
					break;
				case PLUS:
					enterOuterAlt(_localctx, 3);
					{
					setState(594); match(PLUS);
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		atomContext _localctx = new atomContext(_ctx, 80);
		enterRule(_localctx, RULE_atom);
		//System.out.println("enter "+ruleNames[RULE_atom]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			setState(630);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,55,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(598); range();
					setState(602);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==BANG || _la==ROOT ) {
						{
						setState(600);
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
					setState(604); terminal();
					setState(608);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==BANG || _la==ROOT ) {
						{
						setState(606);
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
					setState(610); ruleref();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(612); notSet();
					setState(616);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==BANG || _la==ROOT ) {
						{
						setState(614);
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
					setState(618); match(DOT);
					setState(622);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==LT ) {
						{
						setState(620); elementOptions();
						}
					}

					setState(628);
					_errHandler.sync(this);
					switch ( _input.LA(1) ) {
						case ROOT:
							{
							setState(624); _localctx.astop = match(ROOT);
							}
							break;
						case BANG:
							{
							setState(626); _localctx.astop = match(BANG);
							}
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
							break;
						default :
							throw new NoViableAltException(this);
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		notSetContext _localctx = new notSetContext(_ctx, 82);
		enterRule(_localctx, RULE_notSet);
		//System.out.println("enter "+ruleNames[RULE_notSet]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(640);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,56,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(632); match(NOT);
					setState(634); setElement();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(636); match(NOT);
					setState(638); blockSet();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		blockSetContext _localctx = new blockSetContext(_ctx, 84);
		enterRule(_localctx, RULE_blockSet);
		//System.out.println("enter "+ruleNames[RULE_blockSet]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(642); match(LPAREN);
			setState(644); setElement();
			setState(652);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==OR ) {
				{
				{
				setState(646); match(OR);
				setState(648); setElement();
				}
				}
				setState(654);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(655); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		setElementContext _localctx = new setElementContext(_ctx, 86);
		enterRule(_localctx, RULE_setElement);
		//System.out.println("enter "+ruleNames[RULE_setElement]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(663);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,58,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(657); match(TOKEN_REF);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(659); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(661); range();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		blockContext _localctx = new blockContext(_ctx, 88);
		enterRule(_localctx, RULE_block);
		//System.out.println("enter "+ruleNames[RULE_block]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(665); match(LPAREN);
			setState(680);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==OPTIONS || _la==COLON || _la==AT ) {
				{
				setState(669);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ( _la==OPTIONS ) {
					{
					setState(667); optionsSpec();
					}
				}

				setState(675);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ( _la==AT ) {
					{
					{
					setState(671); _localctx.ra = ruleAction();
					_localctx.ra_list.add(_localctx.ra);
					}
					}
					setState(677);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(678); match(COLON);
				}
			}

			setState(682); altList();
			setState(684); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rulerefContext _localctx = new rulerefContext(_ctx, 90);
		enterRule(_localctx, RULE_ruleref);
		//System.out.println("enter "+ruleNames[RULE_ruleref]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(686); match(RULE_REF);
			setState(690);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ( _la==BEGIN_ARG_ACTION ) {
				{
				setState(688); argActionBlock();
				}
			}

			setState(700);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case BANG:
				case ROOT:
					{
					setState(696);
					_errHandler.sync(this);
					switch ( _input.LA(1) ) {
						case ROOT:
							{
							setState(692); _localctx.op = match(ROOT);
							}
							break;
						case BANG:
							{
							setState(694); _localctx.op = match(BANG);
							}
							break;
						default :
							throw new NoViableAltException(this);
					}
					}
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
					{
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rangeContext _localctx = new rangeContext(_ctx, 92);
		enterRule(_localctx, RULE_range);
		//System.out.println("enter "+ruleNames[RULE_range]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(702); match(STRING_LITERAL);
			setState(704); match(RANGE);
			setState(706); match(STRING_LITERAL);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		terminalContext _localctx = new terminalContext(_ctx, 94);
		enterRule(_localctx, RULE_terminal);
		//System.out.println("enter "+ruleNames[RULE_terminal]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			setState(720);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case TOKEN_REF:
					enterOuterAlt(_localctx, 1);
					{
					setState(708); match(TOKEN_REF);
					setState(712);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==LT ) {
						{
						setState(710); elementOptions();
						}
					}

					}
					break;
				case STRING_LITERAL:
					enterOuterAlt(_localctx, 2);
					{
					setState(714); match(STRING_LITERAL);
					setState(718);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==LT ) {
						{
						setState(716); elementOptions();
						}
					}

					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		elementOptionsContext _localctx = new elementOptionsContext(_ctx, 96);
		enterRule(_localctx, RULE_elementOptions);
		//System.out.println("enter "+ruleNames[RULE_elementOptions]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(722); match(LT);
			setState(724); elementOption();
			setState(732);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==COMMA ) {
				{
				{
				setState(726); match(COMMA);
				setState(728); elementOption();
				}
				}
				setState(734);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(735); match(GT);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		elementOptionContext _localctx = new elementOptionContext(_ctx, 98);
		enterRule(_localctx, RULE_elementOption);
		//System.out.println("enter "+ruleNames[RULE_elementOption]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(749);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,70,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(737); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(739); id();
					setState(741); match(ASSIGN);
					setState(747);
					_errHandler.sync(this);
					switch ( _input.LA(1) ) {
						case TEMPLATE:
						case TOKEN_REF:
						case RULE_REF:
							{
							setState(743); qid();
							}
							break;
						case STRING_LITERAL:
							{
							setState(745); match(STRING_LITERAL);
							}
							break;
						default :
							throw new NoViableAltException(this);
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rewriteContext _localctx = new rewriteContext(_ctx, 100);
		enterRule(_localctx, RULE_rewrite);
		//System.out.println("enter "+ruleNames[RULE_rewrite]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(755);
			_errHandler.sync(this);
			int _alt2132 = _interp.adaptivePredict(_input,71,_ctx);
			while ( _alt2132!=2 && _alt2132!=-1 ) {
				switch ( _alt2132 ) {
					case 1:
						{
						{
						setState(751); predicatedRewrite();
						}
						}
						break;
				}
				setState(757);
				_errHandler.sync(this);
				_alt2132 = _interp.adaptivePredict(_input,71,_ctx);
			}
			setState(758); nakedRewrite();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		predicatedRewriteContext _localctx = new predicatedRewriteContext(_ctx, 102);
		enterRule(_localctx, RULE_predicatedRewrite);
		//System.out.println("enter "+ruleNames[RULE_predicatedRewrite]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(760); match(RARROW);
			setState(762); match(SEMPRED);
			setState(764); rewriteAlt();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		nakedRewriteContext _localctx = new nakedRewriteContext(_ctx, 104);
		enterRule(_localctx, RULE_nakedRewrite);
		//System.out.println("enter "+ruleNames[RULE_nakedRewrite]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(766); match(RARROW);
			setState(768); rewriteAlt();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rewriteAltContext _localctx = new rewriteAltContext(_ctx, 106);
		enterRule(_localctx, RULE_rewriteAlt);
		//System.out.println("enter "+ruleNames[RULE_rewriteAlt]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(778);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,72,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(770); rewriteTreeAlt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(772); rewriteTemplate();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(774); match(ETC);
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
			_errHandler.recover(this);
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
		rewriteTreeAltContext _localctx = new rewriteTreeAltContext(_ctx, 108);
		enterRule(_localctx, RULE_rewriteTreeAlt);
		//System.out.println("enter "+ruleNames[RULE_rewriteTreeAlt]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(782); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(780); rewriteTreeElement();
				}
				}
				setState(784); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==BEGIN_ACTION || _la==LPAREN || _la==DOLLAR || _la==TREE_BEGIN || _la==TOKEN_REF || _la==RULE_REF || _la==STRING_LITERAL );
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rewriteTreeElementContext _localctx = new rewriteTreeElementContext(_ctx, 110);
		enterRule(_localctx, RULE_rewriteTreeElement);
		//System.out.println("enter "+ruleNames[RULE_rewriteTreeElement]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(802);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,75,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(786); rewriteTreeAtom();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(788); rewriteTreeAtom();
					setState(790); ebnfSuffix();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(792); rewriteTree();
					setState(798);
					_errHandler.sync(this);
					switch ( _input.LA(1) ) {
						case QUESTION:
						case STAR:
						case PLUS:
							{
							setState(794); ebnfSuffix();
							}
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
							{
							}
							break;
						default :
							throw new NoViableAltException(this);
					}
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(800); rewriteTreeEbnf();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rewriteTreeAtomContext _localctx = new rewriteTreeAtomContext(_ctx, 112);
		enterRule(_localctx, RULE_rewriteTreeAtom);
		//System.out.println("enter "+ruleNames[RULE_rewriteTreeAtom]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			setState(828);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case TOKEN_REF:
					enterOuterAlt(_localctx, 1);
					{
					setState(804); match(TOKEN_REF);
					setState(808);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==LT ) {
						{
						setState(806); elementOptions();
						}
					}

					setState(812);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==BEGIN_ARG_ACTION ) {
						{
						setState(810); argActionBlock();
						}
					}

					}
					break;
				case RULE_REF:
					enterOuterAlt(_localctx, 2);
					{
					setState(814); match(RULE_REF);
					}
					break;
				case STRING_LITERAL:
					enterOuterAlt(_localctx, 3);
					{
					setState(816); match(STRING_LITERAL);
					setState(820);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ( _la==LT ) {
						{
						setState(818); elementOptions();
						}
					}

					}
					break;
				case DOLLAR:
					enterOuterAlt(_localctx, 4);
					{
					setState(822); match(DOLLAR);
					setState(824); id();
					}
					break;
				case BEGIN_ACTION:
					enterOuterAlt(_localctx, 5);
					{
					setState(826); actionBlock();
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rewriteTreeEbnfContext _localctx = new rewriteTreeEbnfContext(_ctx, 114);
		enterRule(_localctx, RULE_rewriteTreeEbnf);
		//System.out.println("enter "+ruleNames[RULE_rewriteTreeEbnf]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(830); _localctx.lp = match(LPAREN);
			setState(832); rewriteTreeAlt();
			setState(834); match(RPAREN);
			setState(836); rewriteEbnfSuffix();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rewriteEbnfSuffixContext _localctx = new rewriteEbnfSuffixContext(_ctx, 116);
		enterRule(_localctx, RULE_rewriteEbnfSuffix);
		//System.out.println("enter "+ruleNames[RULE_rewriteEbnfSuffix]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(842);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case QUESTION:
					enterOuterAlt(_localctx, 1);
					{
					setState(838); match(QUESTION);
					}
					break;
				case STAR:
					enterOuterAlt(_localctx, 2);
					{
					setState(840); match(STAR);
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rewriteTreeContext _localctx = new rewriteTreeContext(_ctx, 118);
		enterRule(_localctx, RULE_rewriteTree);
		//System.out.println("enter "+ruleNames[RULE_rewriteTree]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(844); match(TREE_BEGIN);
			setState(846); rewriteTreeAtom();
			setState(852);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==BEGIN_ACTION || _la==LPAREN || _la==DOLLAR || _la==TREE_BEGIN || _la==TOKEN_REF || _la==RULE_REF || _la==STRING_LITERAL ) {
				{
				{
				setState(848); rewriteTreeElement();
				}
				}
				setState(854);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(855); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rewriteTemplateContext _localctx = new rewriteTemplateContext(_ctx, 120);
		enterRule(_localctx, RULE_rewriteTemplate);
		//System.out.println("enter "+ruleNames[RULE_rewriteTemplate]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(877);
			_errHandler.sync(this);
			switch ( _interp.adaptivePredict(_input,83,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(857); match(TEMPLATE);
					setState(859); match(LPAREN);
					setState(861); rewriteTemplateArgs();
					setState(863); match(RPAREN);
					setState(869);
					_errHandler.sync(this);
					switch ( _input.LA(1) ) {
						case DOUBLE_QUOTE_STRING_LITERAL:
							{
							setState(865); _localctx.str = match(DOUBLE_QUOTE_STRING_LITERAL);
							}
							break;
						case DOUBLE_ANGLE_STRING_LITERAL:
							{
							setState(867); _localctx.str = match(DOUBLE_ANGLE_STRING_LITERAL);
							}
							break;
						default :
							throw new NoViableAltException(this);
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(871); rewriteTemplateRef();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(873); rewriteIndirectTemplateHead();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(875); actionBlock();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rewriteTemplateRefContext _localctx = new rewriteTemplateRefContext(_ctx, 122);
		enterRule(_localctx, RULE_rewriteTemplateRef);
		//System.out.println("enter "+ruleNames[RULE_rewriteTemplateRef]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(879); id();
			setState(881); match(LPAREN);
			setState(883); rewriteTemplateArgs();
			setState(885); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rewriteIndirectTemplateHeadContext _localctx = new rewriteIndirectTemplateHeadContext(_ctx, 124);
		enterRule(_localctx, RULE_rewriteIndirectTemplateHead);
		//System.out.println("enter "+ruleNames[RULE_rewriteIndirectTemplateHead]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(887); _localctx.lp = match(LPAREN);
			setState(889); actionBlock();
			setState(891); match(RPAREN);
			setState(893); match(LPAREN);
			setState(895); rewriteTemplateArgs();
			setState(897); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rewriteTemplateArgsContext _localctx = new rewriteTemplateArgsContext(_ctx, 126);
		enterRule(_localctx, RULE_rewriteTemplateArgs);
		//System.out.println("enter "+ruleNames[RULE_rewriteTemplateArgs]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			setState(912);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case TEMPLATE:
				case TOKEN_REF:
				case RULE_REF:
					enterOuterAlt(_localctx, 1);
					{
					setState(899); rewriteTemplateArg();
					setState(907);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ( _la==COMMA ) {
						{
						{
						setState(901); match(COMMA);
						setState(903); rewriteTemplateArg();
						}
						}
						setState(909);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case RPAREN:
					{
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		rewriteTemplateArgContext _localctx = new rewriteTemplateArgContext(_ctx, 128);
		enterRule(_localctx, RULE_rewriteTemplateArg);
		//System.out.println("enter "+ruleNames[RULE_rewriteTemplateArg]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(914); id();
			setState(916); match(ASSIGN);
			setState(918); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		idContext _localctx = new idContext(_ctx, 130);
		enterRule(_localctx, RULE_id);
		//System.out.println("enter "+ruleNames[RULE_id]+", LT(1)="+_input.LT(1).getText());
		try {
			setState(926);
			_errHandler.sync(this);
			switch ( _input.LA(1) ) {
				case RULE_REF:
					enterOuterAlt(_localctx, 1);
					{
					setState(920); match(RULE_REF);
					}
					break;
				case TOKEN_REF:
					enterOuterAlt(_localctx, 2);
					{
					setState(922); match(TOKEN_REF);
					}
					break;
				case TEMPLATE:
					enterOuterAlt(_localctx, 3);
					{
					setState(924); match(TEMPLATE);
					}
					break;
				default :
					throw new NoViableAltException(this);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		qidContext _localctx = new qidContext(_ctx, 132);
		enterRule(_localctx, RULE_qid);
		//System.out.println("enter "+ruleNames[RULE_qid]+", LT(1)="+_input.LT(1).getText());
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(928); id();
			setState(936);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ( _la==DOT ) {
				{
				{
				setState(930); match(DOT);
				setState(932); id();
				}
				}
				setState(938);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		alternativeEntryContext _localctx = new alternativeEntryContext(_ctx, 134);
		enterRule(_localctx, RULE_alternativeEntry);
		//System.out.println("enter "+ruleNames[RULE_alternativeEntry]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(939); alternative();
			setState(941); match(EOF);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		elementEntryContext _localctx = new elementEntryContext(_ctx, 136);
		enterRule(_localctx, RULE_elementEntry);
		//System.out.println("enter "+ruleNames[RULE_elementEntry]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(943); element();
			setState(945); match(EOF);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		ruleEntryContext _localctx = new ruleEntryContext(_ctx, 138);
		enterRule(_localctx, RULE_ruleEntry);
		//System.out.println("enter "+ruleNames[RULE_ruleEntry]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(947); rule();
			setState(949); match(EOF);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		blockEntryContext _localctx = new blockEntryContext(_ctx, 140);
		enterRule(_localctx, RULE_blockEntry);
		//System.out.println("enter "+ruleNames[RULE_blockEntry]+", LT(1)="+_input.LT(1).getText());
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(951); block();
			setState(953); match(EOF);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this);
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
		"\01\155\u03bc\02\00\07\00\02\01\07\01\02\02\07\02\02\03\07\03\02\04\07"+
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
	  "\106\07\106\01\00\01\00\03\00\010\00\01\00\01\00\01\00\01\00\01\00\01"+
	  "\00\01\00\01\00\05\00\010\00\012\00\01\00\011\00\01\00\01\00\01\00\01"+
	  "\00\05\00\010\00\012\00\01\00\011\00\01\00\01\00\01\01\01\01\01\01\01"+
	  "\01\01\01\01\01\01\01\01\01\01\01\01\01\01\01\01\01\01\01\01\01\03\01"+
	  "\010\01\01\02\01\02\01\02\01\02\01\02\01\02\01\02\01\02\03\02\010\02"+
	  "\01\03\01\03\01\03\01\03\01\03\01\03\05\03\010\03\012\03\01\03\011\03"+
	  "\01\03\01\03\01\04\01\04\01\04\01\04\01\04\01\04\01\05\01\05\01\05\01"+
	  "\05\01\05\01\05\01\05\01\05\03\05\010\05\01\06\01\06\01\06\01\06\01\06"+
	  "\01\06\01\06\01\06\05\06\010\06\012\06\01\06\011\06\01\06\01\06\01\07"+
	  "\01\07\01\07\01\07\01\07\01\07\01\07\01\07\03\07\010\07\01\010\01\010"+
	  "\01\010\01\010\04\010\010\010\013\010\01\010\01\010\01\010\01\011\01"+
	  "\011\01\011\01\011\01\011\01\011\01\011\01\011\03\011\010\011\01\011"+
	  "\01\011\01\011\01\011\03\011\010\011\01\012\01\012\01\012\01\012\01\012"+
	  "\01\012\01\012\01\012\01\012\01\012\05\012\010\012\012\012\01\012\011"+
	  "\012\01\012\01\012\01\013\01\013\01\013\01\013\05\013\010\013\012\013"+
	  "\01\013\011\013\01\013\01\013\01\014\01\014\01\014\01\014\01\014\01\014"+
	  "\03\014\010\014\01\014\01\014\01\014\01\014\01\015\01\015\01\015\01\015"+
	  "\01\015\01\015\03\015\010\015\01\016\01\016\01\016\01\016\01\016\01\016"+
	  "\01\016\01\016\04\016\010\016\013\016\01\016\01\017\01\017\05\017\010"+
	  "\017\012\017\01\017\011\017\01\020\01\020\03\020\010\020\01\020\01\020"+
	  "\03\020\010\020\01\020\01\020\01\020\01\020\03\020\010\020\01\020\01"+
	  "\020\03\020\010\020\01\020\01\020\03\020\010\020\01\020\01\020\03\020"+
	  "\010\020\01\020\01\020\01\020\01\020\01\020\01\020\01\020\01\020\01\020"+
	  "\01\020\01\021\01\021\05\021\010\021\012\021\01\021\011\021\01\021\01"+
	  "\021\03\021\010\021\01\022\01\022\01\022\01\022\01\022\01\022\01\023"+
	  "\01\023\01\023\01\023\01\024\01\024\05\024\010\024\012\024\01\024\011"+
	  "\024\01\025\01\025\01\025\01\025\03\025\010\025\01\026\01\026\01\026"+
	  "\01\026\01\027\01\027\01\027\01\027\01\027\01\027\01\027\01\027\05\027"+
	  "\010\027\012\027\01\027\011\027\01\030\01\030\01\030\01\030\01\031\01"+
	  "\031\01\031\01\031\01\031\01\031\01\032\01\032\04\032\010\032\013\032"+
	  "\01\032\01\033\01\033\01\033\01\033\01\033\01\033\01\033\01\033\03\033"+
	  "\010\033\01\034\01\034\01\035\01\035\01\035\01\035\01\035\01\035\05\035"+
	  "\010\035\012\035\01\035\011\035\01\036\01\036\01\036\01\036\01\036\01"+
	  "\036\03\036\010\036\01\037\01\037\01\037\01\037\01\037\01\037\05\037"+
	  "\010\037\012\037\01\037\011\037\01\040\01\040\01\040\01\040\01\040\01"+
	  "\040\03\040\010\040\01\040\01\040\01\040\01\040\03\040\010\040\01\041"+
	  "\01\041\04\041\010\041\013\041\01\041\01\042\01\042\01\042\01\042\01"+
	  "\042\01\042\03\042\010\042\01\042\01\042\01\042\01\042\01\042\01\042"+
	  "\03\042\010\042\01\042\01\042\01\042\01\042\01\042\01\042\03\042\010"+
	  "\042\01\042\01\042\01\042\01\042\01\042\01\042\03\042\010\042\03\042"+
	  "\010\042\01\043\01\043\01\043\01\043\01\043\01\043\03\043\010\043\01"+
	  "\043\01\043\01\043\01\043\01\043\01\043\01\043\01\043\03\043\010\043"+
	  "\03\043\010\043\01\044\01\044\01\044\01\044\01\044\01\044\04\044\010"+
	  "\044\013\044\01\044\01\044\01\044\01\045\01\045\01\045\01\045\01\045"+
	  "\01\045\03\045\010\045\01\046\01\046\01\046\01\046\01\046\01\046\03\046"+
	  "\010\046\01\047\01\047\01\047\01\047\01\047\01\047\03\047\010\047\01"+
	  "\050\01\050\01\050\01\050\03\050\010\050\01\050\01\050\01\050\01\050"+
	  "\03\050\010\050\01\050\01\050\01\050\01\050\01\050\01\050\03\050\010"+
	  "\050\01\050\01\050\01\050\01\050\03\050\010\050\01\050\01\050\01\050"+
	  "\01\050\03\050\010\050\03\050\010\050\01\051\01\051\01\051\01\051\01"+
	  "\051\01\051\01\051\01\051\03\051\010\051\01\052\01\052\01\052\01\052"+
	  "\01\052\01\052\01\052\01\052\05\052\010\052\012\052\01\052\011\052\01"+
	  "\052\01\052\01\053\01\053\01\053\01\053\01\053\01\053\03\053\010\053"+
	  "\01\054\01\054\01\054\01\054\03\054\010\054\01\054\01\054\05\054\010"+
	  "\054\012\054\01\054\011\054\01\054\01\054\03\054\010\054\01\054\01\054"+
	  "\01\054\01\054\01\055\01\055\01\055\01\055\03\055\010\055\01\055\01\055"+
	  "\01\055\01\055\03\055\010\055\01\055\01\055\03\055\010\055\01\056\01"+
	  "\056\01\056\01\056\01\056\01\056\01\057\01\057\01\057\01\057\03\057\010"+
	  "\057\01\057\01\057\01\057\01\057\03\057\010\057\03\057\010\057\01\060"+
	  "\01\060\01\060\01\060\01\060\01\060\01\060\01\060\05\060\010\060\012"+
	  "\060\01\060\011\060\01\060\01\060\01\061\01\061\01\061\01\061\01\061"+
	  "\01\061\01\061\01\061\01\061\01\061\03\061\010\061\03\061\010\061\01"+
	  "\062\01\062\05\062\010\062\012\062\01\062\011\062\01\062\01\062\01\063"+
	  "\01\063\01\063\01\063\01\063\01\063\01\064\01\064\01\064\01\064\01\065"+
	  "\01\065\01\065\01\065\01\065\01\065\01\065\01\065\03\065\010\065\01\066"+
	  "\01\066\04\066\010\066\013\066\01\066\01\067\01\067\01\067\01\067\01"+
	  "\067\01\067\01\067\01\067\01\067\01\067\01\067\01\067\03\067\010\067"+
	  "\01\067\01\067\03\067\010\067\01\070\01\070\01\070\01\070\03\070\010"+
	  "\070\01\070\01\070\03\070\010\070\01\070\01\070\01\070\01\070\01\070"+
	  "\01\070\03\070\010\070\01\070\01\070\01\070\01\070\01\070\01\070\03\070"+
	  "\010\070\01\071\01\071\01\071\01\071\01\071\01\071\01\071\01\071\01\072"+
	  "\01\072\01\072\01\072\03\072\010\072\01\073\01\073\01\073\01\073\01\073"+
	  "\01\073\05\073\010\073\012\073\01\073\011\073\01\073\01\073\01\074\01"+
	  "\074\01\074\01\074\01\074\01\074\01\074\01\074\01\074\01\074\01\074\01"+
	  "\074\03\074\010\074\01\074\01\074\01\074\01\074\01\074\01\074\03\074"+
	  "\010\074\01\075\01\075\01\075\01\075\01\075\01\075\01\075\01\075\01\076"+
	  "\01\076\01\076\01\076\01\076\01\076\01\076\01\076\01\076\01\076\01\076"+
	  "\01\076\01\077\01\077\01\077\01\077\01\077\01\077\05\077\010\077\012"+
	  "\077\01\077\011\077\01\077\01\077\03\077\010\077\01\100\01\100\01\100"+
	  "\01\100\01\100\01\100\01\101\01\101\01\101\01\101\01\101\01\101\03\101"+
	  "\010\101\01\102\01\102\01\102\01\102\01\102\01\102\05\102\010\102\012"+
	  "\102\01\102\011\102\01\103\01\103\01\103\01\103\01\104\01\104\01\104"+
	  "\01\104\01\105\01\105\01\105\01\105\01\106\01\106\01\106\01\106\01\106"+
	  "\107\00\00\00\02\00\00\04\00\00\06\00\00\010\00\00\012\00\00\014\00\00"+
	  "\016\00\00\020\00\00\022\00\00\024\00\00\026\00\00\030\00\00\032\00\00"+
	  "\034\00\00\036\00\00\040\00\00\042\00\00\044\00\00\046\00\00\050\00\00"+
	  "\052\00\00\054\00\00\056\00\00\060\00\00\062\00\00\064\00\00\066\00\00"+
	  "\070\00\00\072\00\00\074\00\00\076\00\00\100\00\00\102\00\00\104\00\00"+
	  "\106\00\00\110\00\00\112\00\00\114\00\00\116\00\00\120\00\00\122\00\00"+
	  "\124\00\00\126\00\00\130\00\00\132\00\00\134\00\00\136\00\00\140\00\00"+
	  "\142\00\00\144\00\00\146\00\00\150\00\00\152\00\00\154\00\00\156\00\00"+
	  "\160\00\00\162\00\00\164\00\00\166\00\00\170\00\00\172\00\00\174\00\00"+
	  "\176\00\00\u0080\00\00\u0082\00\00\u0084\00\00\u0086\00\00\u0088\00\00"+
	  "\u008a\00\00\u008c\00\00\00\04\01\076\107\02\050\050\055\055\02\050\050"+
	  "\055\055\02\050\050\055\055\u047e\00\u0090\01\00\00\00\01\u03bb\05\uffff"+
	  "\00\00\02\u00b8\01\00\00\00\03\u0093\01\00\00\00\04\u00c2\01\00\00\00"+
	  "\05\u0099\01\00\00\00\06\u00c4\01\00\00\00\07\u00bb\01\00\00\00\07\u0198"+
	  "\01\00\00\00\07\u029c\01\00\00\00\010\u00d1\01\00\00\00\011\u00c7\01"+
	  "\00\00\00\012\u00df\01\00\00\00\013\u00d6\01\00\00\00\014\u00e1\01\00"+
	  "\00\00\015\u00bd\01\00\00\00\016\u00f8\01\00\00\00\017\u00e4\01\00\00"+
	  "\00\017\u00e8\01\00\00\00\020\u00fa\01\00\00\00\021\u00bf\01\00\00\00"+
	  "\022\u0112\01\00\00\00\023\u00fd\01\00\00\00\024\u0114\01\00\00\00\025"+
	  "\u0117\01\00\00\00\025\u013b\01\00\00\00\025\u018b\01\00\00\00\025\u018f"+
	  "\01\00\00\00\025\u01b7\01\00\00\00\025\u020f\01\00\00\00\025\u033b\01"+
	  "\00\00\00\025\u036c\01\00\00\00\025\u037a\01\00\00\00\025\u0397\01\00"+
	  "\00\00\026\u0125\01\00\00\00\027\u0162\01\00\00\00\027\u0189\01\00\00"+
	  "\00\027\u01a0\01\00\00\00\027\u01b1\01\00\00\00\027\u02b1\01\00\00\00"+
	  "\027\u032b\01\00\00\00\030\u0130\01\00\00\00\031\u00c1\01\00\00\00\032"+
	  "\u0142\01\00\00\00\033\u0133\01\00\00\00\034\u0144\01\00\00\00\035\u00a2"+
	  "\01\00\00\00\036\u0154\01\00\00\00\037\u00a0\01\00\00\00\040\u0159\01"+
	  "\00\00\00\041\u014b\01\00\00\00\041\u0151\01\00\00\00\041\u03b4\01\00"+
	  "\00\00\042\u017f\01\00\00\00\043\u017a\01\00\00\00\044\u0186\01\00\00"+
	  "\00\045\u017c\01\00\00\00\046\u018c\01\00\00\00\047\u0183\01\00\00\00"+
	  "\050\u0194\01\00\00\00\051\u0172\01\00\00\00\052\u019b\01\00\00\00\053"+
	  "\u0191\01\00\00\00\054\u019d\01\00\00\00\055\u0166\01\00\00\00\056\u01a1"+
	  "\01\00\00\00\057\u016a\01\00\00\00\060\u01ae\01\00\00\00\061\u016e\01"+
	  "\00\00\00\062\u01b2\01\00\00\00\063\u019a\01\00\00\00\063\u02a0\01\00"+
	  "\00\00\064\u01ba\01\00\00\00\065\u015c\01\00\00\00\066\u01c6\01\00\00"+
	  "\00\067\u01b9\01\00\00\00\070\u01c8\01\00\00\00\071\u0176\01\00\00\00"+
	  "\072\u01ca\01\00\00\00\073\u01c9\01\00\00\00\074\u01d5\01\00\00\00\075"+
	  "\u01cb\01\00\00\00\075\u01cf\01\00\00\00\076\u01dd\01\00\00\00\077\u02ab"+
	  "\01\00\00\00\100\u01f4\01\00\00\00\101\u01d6\01\00\00\00\101\u01de\01"+
	  "\00\00\00\101\u01e2\01\00\00\00\101\u03ac\01\00\00\00\102\u01f8\01\00"+
	  "\00\00\103\u01e9\01\00\00\00\104\u021c\01\00\00\00\105\u01f7\01\00\00"+
	  "\00\105\u0235\01\00\00\00\105\u0237\01\00\00\00\105\u03b0\01\00\00\00"+
	  "\106\u021e\01\00\00\00\107\u01fd\01\00\00\00\110\u0232\01\00\00\00\111"+
	  "\u0215\01\00\00\00\112\u023e\01\00\00\00\113\u020d\01\00\00\00\114\u024c"+
	  "\01\00\00\00\115\u0241\01\00\00\00\116\u0254\01\00\00\00\117\u01ff\01"+
	  "\00\00\00\117\u0207\01\00\00\00\117\u0217\01\00\00\00\117\u0247\01\00"+
	  "\00\00\117\u0317\01\00\00\00\117\u031b\01\00\00\00\120\u0276\01\00\00"+
	  "\00\121\u0205\01\00\00\00\121\u0227\01\00\00\00\122\u0280\01\00\00\00"+
	  "\123\u0265\01\00\00\00\124\u0282\01\00\00\00\125\u027f\01\00\00\00\126"+
	  "\u0297\01\00\00\00\127\u027b\01\00\00\00\127\u0285\01\00\00\00\127\u0289"+
	  "\01\00\00\00\130\u0299\01\00\00\00\131\u0229\01\00\00\00\131\u023f\01"+
	  "\00\00\00\131\u03b8\01\00\00\00\132\u02ae\01\00\00\00\133\u0263\01\00"+
	  "\00\00\134\u02be\01\00\00\00\135\u0257\01\00\00\00\135\u0296\01\00\00"+
	  "\00\136\u02d0\01\00\00\00\137\u025d\01\00\00\00\140\u02d2\01\00\00\00"+
	  "\141\u026d\01\00\00\00\141\u02c7\01\00\00\00\141\u02cd\01\00\00\00\141"+
	  "\u0327\01\00\00\00\141\u0333\01\00\00\00\142\u02ed\01\00\00\00\143\u02d5"+
	  "\01\00\00\00\143\u02d9\01\00\00\00\144\u02f3\01\00\00\00\145\u01eb\01"+
	  "\00\00\00\145\u01f1\01\00\00\00\146\u02f8\01\00\00\00\147\u02f0\01\00"+
	  "\00\00\150\u02fe\01\00\00\00\151\u02f7\01\00\00\00\152\u030a\01\00\00"+
	  "\00\153\u02fd\01\00\00\00\153\u0301\01\00\00\00\154\u030e\01\00\00\00"+
	  "\155\u0303\01\00\00\00\155\u0341\01\00\00\00\156\u0322\01\00\00\00\157"+
	  "\u030d\01\00\00\00\157\u0351\01\00\00\00\160\u033c\01\00\00\00\161\u0313"+
	  "\01\00\00\00\161\u0315\01\00\00\00\161\u034f\01\00\00\00\162\u033e\01"+
	  "\00\00\00\163\u0321\01\00\00\00\164\u034a\01\00\00\00\165\u0345\01\00"+
	  "\00\00\166\u034c\01\00\00\00\167\u0319\01\00\00\00\170\u036d\01\00\00"+
	  "\00\171\u0305\01\00\00\00\172\u036f\01\00\00\00\173\u0368\01\00\00\00"+
	  "\174\u0377\01\00\00\00\175\u036a\01\00\00\00\176\u0390\01\00\00\00\177"+
	  "\u035e\01\00\00\00\177\u0374\01\00\00\00\177\u0380\01\00\00\00\u0080"+
	  "\u0392\01\00\00\00\u0081\u0384\01\00\00\00\u0081\u0388\01\00\00\00\u0082"+
	  "\u039e\01\00\00\00\u0083\u0095\01\00\00\00\u0083\u00d2\01\00\00\00\u0083"+
	  "\u00f1\01\00\00\00\u0083\u00f5\01\00\00\00\u0083\u00f7\01\00\00\00\u0083"+
	  "\u0105\01\00\00\00\u0083\u0139\01\00\00\00\u0083\u013d\01\00\00\00\u0083"+
	  "\u0147\01\00\00\00\u0083\u0160\01\00\00\00\u0083\u01b5\01\00\00\00\u0083"+
	  "\u01da\01\00\00\00\u0083\u021f\01\00\00\00\u0083\u02e4\01\00\00\00\u0083"+
	  "\u0339\01\00\00\00\u0083\u0370\01\00\00\00\u0083\u0393\01\00\00\00\u0083"+
	  "\u03a1\01\00\00\00\u0083\u03a5\01\00\00\00\u0084\u03a0\01\00\00\00\u0085"+
	  "\u00d8\01\00\00\00\u0085\u01a4\01\00\00\00\u0085\u01a8\01\00\00\00\u0085"+
	  "\u02e2\01\00\00\00\u0085\u02e8\01\00\00\00\u0086\u03ab\01\00\00\00\u0087"+
	  "\u03bb\05\uffff\00\00\u0088\u03af\01\00\00\00\u0089\u03bb\05\uffff\00"+
	  "\00\u008a\u03b3\01\00\00\00\u008b\u03bb\05\uffff\00\00\u008c\u03b7\01"+
	  "\00\00\00\u008d\u03bb\05\uffff\00\00\u008e\u008f\05\03\00\00\u008f\u0091"+
	  "\01\00\00\00\u0090\u008e\01\00\00\00\u0090\u0091\01\00\00\00\u0091\u0092"+
	  "\01\00\00\00\u0092\u0093\03\02\01\00\u0093\u0094\01\00\00\00\u0094\u0095"+
	  "\03\u0082\101\00\u0095\u0096\01\00\00\00\u0096\u0097\05\040\00\00\u0097"+
	  "\u009c\01\00\00\00\u0098\u0099\03\04\02\00\u0099\u009b\01\00\00\00\u009a"+
	  "\u0098\01\00\00\00\u009b\u009e\01\00\00\00\u009c\u009a\01\00\00\00\u009c"+
	  "\u009d\01\00\00\00\u009d\u009f\01\00\00\00\u009e\u009c\01\00\00\00\u009f"+
	  "\u00a0\03\036\017\00\u00a0\u00a5\01\00\00\00\u00a1\u00a2\03\034\016\00"+
	  "\u00a2\u00a4\01\00\00\00\u00a3\u00a1\01\00\00\00\u00a4\u00a7\01\00\00"+
	  "\00\u00a5\u00a3\01\00\00\00\u00a5\u00a6\01\00\00\00\u00a6\u00a8\01\00"+
	  "\00\00\u00a7\u00a5\01\00\00\00\u00a8\u00a9\05\uffff\00\00\u00a9\01\01"+
	  "\00\00\00\u00aa\u00ab\05\017\00\00\u00ab\u00ac\01\00\00\00\u00ac\u00ad"+
	  "\05\022\00\00\u00ad\u00b9\01\00\00\00\u00ae\u00af\05\020\00\00\u00af"+
	  "\u00b0\01\00\00\00\u00b0\u00b1\05\022\00\00\u00b1\u00b9\01\00\00\00\u00b2"+
	  "\u00b3\05\021\00\00\u00b3\u00b4\01\00\00\00\u00b4\u00b5\05\022\00\00"+
	  "\u00b5\u00b9\01\00\00\00\u00b6\u00b7\05\022\00\00\u00b7\u00b9\01\00\00"+
	  "\00\u00b8\u00aa\01\00\00\00\u00b8\u00ae\01\00\00\00\u00b8\u00b2\01\00"+
	  "\00\00\u00b8\u00b6\01\00\00\00\u00b9\03\01\00\00\00\u00ba\u00bb\03\06"+
	  "\03\00\u00bb\u00c3\01\00\00\00\u00bc\u00bd\03\014\06\00\u00bd\u00c3\01"+
	  "\00\00\00\u00be\u00bf\03\020\010\00\u00bf\u00c3\01\00\00\00\u00c0\u00c1"+
	  "\03\030\014\00\u00c1\u00c3\01\00\00\00\u00c2\u00ba\01\00\00\00\u00c2"+
	  "\u00bc\01\00\00\00\u00c2\u00be\01\00\00\00\u00c2\u00c0\01\00\00\00\u00c3"+
	  "\05\01\00\00\00\u00c4\u00c5\05\012\00\00\u00c5\u00cc\01\00\00\00\u00c6"+
	  "\u00c7\03\010\04\00\u00c7\u00c8\01\00\00\00\u00c8\u00c9\05\040\00\00"+
	  "\u00c9\u00cb\01\00\00\00\u00ca\u00c6\01\00\00\00\u00cb\u00ce\01\00\00"+
	  "\00\u00cc\u00ca\01\00\00\00\u00cc\u00cd\01\00\00\00\u00cd\u00cf\01\00"+
	  "\00\00\u00ce\u00cc\01\00\00\00\u00cf\u00d0\05\067\00\00\u00d0\07\01\00"+
	  "\00\00\u00d1\u00d2\03\u0082\101\00\u00d2\u00d3\01\00\00\00\u00d3\u00d4"+
	  "\05\046\00\00\u00d4\u00d5\01\00\00\00\u00d5\u00d6\03\012\05\00\u00d6"+
	  "\011\01\00\00\00\u00d7\u00d8\03\u0084\102\00\u00d8\u00e0\01\00\00\00"+
	  "\u00d9\u00da\05\073\00\00\u00da\u00e0\01\00\00\00\u00db\u00dc\05\072"+
	  "\00\00\u00dc\u00e0\01\00\00\00\u00dd\u00de\05\051\00\00\u00de\u00e0\01"+
	  "\00\00\00\u00df\u00d7\01\00\00\00\u00df\u00d9\01\00\00\00\u00df\u00db"+
	  "\01\00\00\00\u00df\u00dd\01\00\00\00\u00e0\013\01\00\00\00\u00e1\u00e2"+
	  "\05\015\00\00\u00e2\u00e3\01\00\00\00\u00e3\u00e4\03\016\07\00\u00e4"+
	  "\u00eb\01\00\00\00\u00e5\u00e6\05\037\00\00\u00e6\u00e7\01\00\00\00\u00e7"+
	  "\u00e8\03\016\07\00\u00e8\u00ea\01\00\00\00\u00e9\u00e5\01\00\00\00\u00ea"+
	  "\u00ed\01\00\00\00\u00eb\u00e9\01\00\00\00\u00eb\u00ec\01\00\00\00\u00ec"+
	  "\u00ee\01\00\00\00\u00ed\u00eb\01\00\00\00\u00ee\u00ef\05\040\00\00\u00ef"+
	  "\015\01\00\00\00\u00f0\u00f1\03\u0082\101\00\u00f1\u00f2\01\00\00\00"+
	  "\u00f2\u00f3\05\046\00\00\u00f3\u00f4\01\00\00\00\u00f4\u00f5\03\u0082"+
	  "\101\00\u00f5\u00f9\01\00\00\00\u00f6\u00f7\03\u0082\101\00\u00f7\u00f9"+
	  "\01\00\00\00\u00f8\u00f0\01\00\00\00\u00f8\u00f6\01\00\00\00\u00f9\017"+
	  "\01\00\00\00\u00fa\u00fb\05\013\00\00\u00fb\u00fe\01\00\00\00\u00fc\u00fd"+
	  "\03\022\011\00\u00fd\u00ff\01\00\00\00\u00fe\u00fc\01\00\00\00\u00ff"+
	  "\u0100\01\00\00\00\u0100\u00fe\01\00\00\00\u0100\u0101\01\00\00\00\u0101"+
	  "\u0102\01\00\00\00\u0102\u0103\05\067\00\00\u0103\021\01\00\00\00\u0104"+
	  "\u0105\03\u0082\101\00\u0105\u010c\01\00\00\00\u0106\u0107\05\046\00"+
	  "\00\u0107\u0108\01\00\00\00\u0108\u0109\05\073\00\00\u0109\u010d\01\00"+
	  "\00\00\u010a\u010b\01\00\00\00\u010b\u010d\01\00\00\00\u010c\u0106\01"+
	  "\00\00\00\u010c\u010a\01\00\00\00\u010d\u010e\01\00\00\00\u010e\u010f"+
	  "\05\040\00\00\u010f\u0113\01\00\00\00\u0110\u0111\05\071\00\00\u0111"+
	  "\u0113\01\00\00\00\u0112\u0104\01\00\00\00\u0112\u0110\01\00\00\00\u0113"+
	  "\023\01\00\00\00\u0114\u0115\05\011\00\00\u0115\u0120\01\00\00\00\u0116"+
	  "\u0117\03\024\012\00\u0117\u011f\01\00\00\00\u0118\u0119\05\112\00\00"+
	  "\u0119\u011f\01\00\00\00\u011a\u011b\05\113\00\00\u011b\u011f\01\00\00"+
	  "\00\u011c\u011d\05\114\00\00\u011d\u011f\01\00\00\00\u011e\u0116\01\00"+
	  "\00\00\u011e\u0118\01\00\00\00\u011e\u011a\01\00\00\00\u011e\u011c\01"+
	  "\00\00\00\u011f\u0122\01\00\00\00\u0120\u011e\01\00\00\00\u0120\u0121"+
	  "\01\00\00\00\u0121\u0123\01\00\00\00\u0122\u0120\01\00\00\00\u0123\u0124"+
	  "\05\115\00\00\u0124\025\01\00\00\00\u0125\u0126\05\010\00\00\u0126\u012b"+
	  "\01\00\00\00\u0127\u0128\07\00\00\00\u0128\u012a\01\00\00\00\u0129\u0127"+
	  "\01\00\00\00\u012a\u012d\01\00\00\00\u012b\u0129\01\00\00\00\u012b\u012c"+
	  "\01\00\00\00\u012c\u012e\01\00\00\00\u012d\u012b\01\00\00\00\u012e\u012f"+
	  "\05\110\00\00\u012f\027\01\00\00\00\u0130\u0131\05\064\00\00\u0131\u0136"+
	  "\01\00\00\00\u0132\u0133\03\032\015\00\u0133\u0134\01\00\00\00\u0134"+
	  "\u0135\05\036\00\00\u0135\u0137\01\00\00\00\u0136\u0132\01\00\00\00\u0136"+
	  "\u0137\01\00\00\00\u0137\u0138\01\00\00\00\u0138\u0139\03\u0082\101\00"+
	  "\u0139\u013a\01\00\00\00\u013a\u013b\03\024\012\00\u013b\031\01\00\00"+
	  "\00\u013c\u013d\03\u0082\101\00\u013d\u0143\01\00\00\00\u013e\u013f\05"+
	  "\017\00\00\u013f\u0143\01\00\00\00\u0140\u0141\05\020\00\00\u0141\u0143"+
	  "\01\00\00\00\u0142\u013c\01\00\00\00\u0142\u013e\01\00\00\00\u0142\u0140"+
	  "\01\00\00\00\u0143\033\01\00\00\00\u0144\u0145\05\034\00\00\u0145\u0146"+
	  "\01\00\00\00\u0146\u0147\03\u0082\101\00\u0147\u0148\01\00\00\00\u0148"+
	  "\u0149\05\040\00\00\u0149\u014c\01\00\00\00\u014a\u014b\03\040\020\00"+
	  "\u014b\u014d\01\00\00\00\u014c\u014a\01\00\00\00\u014d\u014e\01\00\00"+
	  "\00\u014e\u014c\01\00\00\00\u014e\u014f\01\00\00\00\u014f\035\01\00\00"+
	  "\00\u0150\u0151\03\040\020\00\u0151\u0153\01\00\00\00\u0152\u0150\01"+
	  "\00\00\00\u0153\u0156\01\00\00\00\u0154\u0152\01\00\00\00\u0154\u0155"+
	  "\01\00\00\00\u0155\037\01\00\00\00\u0156\u0154\01\00\00\00\u0157\u0158"+
	  "\05\03\00\00\u0158\u015a\01\00\00\00\u0159\u0157\01\00\00\00\u0159\u015a"+
	  "\01\00\00\00\u015a\u015d\01\00\00\00\u015b\u015c\03\064\032\00\u015c"+
	  "\u015e\01\00\00\00\u015d\u015b\01\00\00\00\u015d\u015e\01\00\00\00\u015e"+
	  "\u015f\01\00\00\00\u015f\u0160\03\u0082\101\00\u0160\u0163\01\00\00\00"+
	  "\u0161\u0162\03\026\013\00\u0162\u0164\01\00\00\00\u0163\u0161\01\00"+
	  "\00\00\u0163\u0164\01\00\00\00\u0164\u0167\01\00\00\00\u0165\u0166\03"+
	  "\054\026\00\u0166\u0168\01\00\00\00\u0167\u0165\01\00\00\00\u0167\u0168"+
	  "\01\00\00\00\u0168\u016b\01\00\00\00\u0169\u016a\03\056\027\00\u016a"+
	  "\u016c\01\00\00\00\u016b\u0169\01\00\00\00\u016b\u016c\01\00\00\00\u016c"+
	  "\u016f\01\00\00\00\u016d\u016e\03\060\030\00\u016e\u0170\01\00\00\00"+
	  "\u016f\u016d\01\00\00\00\u016f\u0170\01\00\00\00\u0170\u0171\01\00\00"+
	  "\00\u0171\u0172\03\050\024\00\u0172\u0173\01\00\00\00\u0173\u0174\05"+
	  "\035\00\00\u0174\u0175\01\00\00\00\u0175\u0176\03\070\034\00\u0176\u0177"+
	  "\01\00\00\00\u0177\u0178\05\040\00\00\u0178\u0179\01\00\00\00\u0179\u017a"+
	  "\03\042\021\00\u017a\041\01\00\00\00\u017b\u017c\03\044\022\00\u017c"+
	  "\u017e\01\00\00\00\u017d\u017b\01\00\00\00\u017e\u0181\01\00\00\00\u017f"+
	  "\u017d\01\00\00\00\u017f\u0180\01\00\00\00\u0180\u0184\01\00\00\00\u0181"+
	  "\u017f\01\00\00\00\u0182\u0183\03\046\023\00\u0183\u0185\01\00\00\00"+
	  "\u0184\u0182\01\00\00\00\u0184\u0185\01\00\00\00\u0185\043\01\00\00\00"+
	  "\u0186\u0187\05\031\00\00\u0187\u0188\01\00\00\00\u0188\u0189\03\026"+
	  "\013\00\u0189\u018a\01\00\00\00\u018a\u018b\03\024\012\00\u018b\045\01"+
	  "\00\00\00\u018c\u018d\05\032\00\00\u018d\u018e\01\00\00\00\u018e\u018f"+
	  "\03\024\012\00\u018f\047\01\00\00\00\u0190\u0191\03\052\025\00\u0191"+
	  "\u0193\01\00\00\00\u0192\u0190\01\00\00\00\u0193\u0196\01\00\00\00\u0194"+
	  "\u0192\01\00\00\00\u0194\u0195\01\00\00\00\u0195\051\01\00\00\00\u0196"+
	  "\u0194\01\00\00\00\u0197\u0198\03\06\03\00\u0198\u019c\01\00\00\00\u0199"+
	  "\u019a\03\062\031\00\u019a\u019c\01\00\00\00\u019b\u0197\01\00\00\00"+
	  "\u019b\u0199\01\00\00\00\u019c\053\01\00\00\00\u019d\u019e\05\026\00"+
	  "\00\u019e\u019f\01\00\00\00\u019f\u01a0\03\026\013\00\u01a0\055\01\00"+
	  "\00\00\u01a1\u01a2\05\030\00\00\u01a2\u01a3\01\00\00\00\u01a3\u01a4\03"+
	  "\u0084\102\00\u01a4\u01ab\01\00\00\00\u01a5\u01a6\05\037\00\00\u01a6"+
	  "\u01a7\01\00\00\00\u01a7\u01a8\03\u0084\102\00\u01a8\u01aa\01\00\00\00"+
	  "\u01a9\u01a5\01\00\00\00\u01aa\u01ad\01\00\00\00\u01ab\u01a9\01\00\00"+
	  "\00\u01ab\u01ac\01\00\00\00\u01ac\057\01\00\00\00\u01ad\u01ab\01\00\00"+
	  "\00\u01ae\u01af\05\027\00\00\u01af\u01b0\01\00\00\00\u01b0\u01b1\03\026"+
	  "\013\00\u01b1\061\01\00\00\00\u01b2\u01b3\05\064\00\00\u01b3\u01b4\01"+
	  "\00\00\00\u01b4\u01b5\03\u0082\101\00\u01b5\u01b6\01\00\00\00\u01b6\u01b7"+
	  "\03\024\012\00\u01b7\063\01\00\00\00\u01b8\u01b9\03\066\033\00\u01b9"+
	  "\u01bb\01\00\00\00\u01ba\u01b8\01\00\00\00\u01bb\u01bc\01\00\00\00\u01bc"+
	  "\u01ba\01\00\00\00\u01bc\u01bd\01\00\00\00\u01bd\065\01\00\00\00\u01be"+
	  "\u01bf\05\024\00\00\u01bf\u01c7\01\00\00\00\u01c0\u01c1\05\025\00\00"+
	  "\u01c1\u01c7\01\00\00\00\u01c2\u01c3\05\023\00\00\u01c3\u01c7\01\00\00"+
	  "\00\u01c4\u01c5\05\016\00\00\u01c5\u01c7\01\00\00\00\u01c6\u01be\01\00"+
	  "\00\00\u01c6\u01c0\01\00\00\00\u01c6\u01c2\01\00\00\00\u01c6\u01c4\01"+
	  "\00\00\00\u01c7\067\01\00\00\00\u01c8\u01c9\03\072\035\00\u01c9\071\01"+
	  "\00\00\00\u01ca\u01cb\03\074\036\00\u01cb\u01d2\01\00\00\00\u01cc\u01cd"+
	  "\05\054\00\00\u01cd\u01ce\01\00\00\00\u01ce\u01cf\03\074\036\00\u01cf"+
	  "\u01d1\01\00\00\00\u01d0\u01cc\01\00\00\00\u01d1\u01d4\01\00\00\00\u01d2"+
	  "\u01d0\01\00\00\00\u01d2\u01d3\01\00\00\00\u01d3\073\01\00\00\00\u01d4"+
	  "\u01d2\01\00\00\00\u01d5\u01d6\03\100\040\00\u01d6\u01db\01\00\00\00"+
	  "\u01d7\u01d8\05\065\00\00\u01d8\u01d9\01\00\00\00\u01d9\u01da\03\u0082"+
	  "\101\00\u01da\u01dc\01\00\00\00\u01db\u01d7\01\00\00\00\u01db\u01dc\01"+
	  "\00\00\00\u01dc\075\01\00\00\00\u01dd\u01de\03\100\040\00\u01de\u01e5"+
	  "\01\00\00\00\u01df\u01e0\05\054\00\00\u01e0\u01e1\01\00\00\00\u01e1\u01e2"+
	  "\03\100\040\00\u01e2\u01e4\01\00\00\00\u01e3\u01df\01\00\00\00\u01e4"+
	  "\u01e7\01\00\00\00\u01e5\u01e3\01\00\00\00\u01e5\u01e6\01\00\00\00\u01e6"+
	  "\077\01\00\00\00\u01e7\u01e5\01\00\00\00\u01e8\u01e9\03\102\041\00\u01e9"+
	  "\u01ee\01\00\00\00\u01ea\u01eb\03\144\062\00\u01eb\u01ef\01\00\00\00"+
	  "\u01ec\u01ed\01\00\00\00\u01ed\u01ef\01\00\00\00\u01ee\u01ea\01\00\00"+
	  "\00\u01ee\u01ec\01\00\00\00\u01ef\u01f5\01\00\00\00\u01f0\u01f1\03\144"+
	  "\062\00\u01f1\u01f5\01\00\00\00\u01f2\u01f3\01\00\00\00\u01f3\u01f5\01"+
	  "\00\00\00\u01f4\u01e8\01\00\00\00\u01f4\u01f0\01\00\00\00\u01f4\u01f2"+
	  "\01\00\00\00\u01f5\101\01\00\00\00\u01f6\u01f7\03\104\042\00\u01f7\u01f9"+
	  "\01\00\00\00\u01f8\u01f6\01\00\00\00\u01f9\u01fa\01\00\00\00\u01fa\u01f8"+
	  "\01\00\00\00\u01fa\u01fb\01\00\00\00\u01fb\103\01\00\00\00\u01fc\u01fd"+
	  "\03\106\043\00\u01fd\u0202\01\00\00\00\u01fe\u01ff\03\116\047\00\u01ff"+
	  "\u0203\01\00\00\00\u0200\u0201\01\00\00\00\u0201\u0203\01\00\00\00\u0202"+
	  "\u01fe\01\00\00\00\u0202\u0200\01\00\00\00\u0203\u021d\01\00\00\00\u0204"+
	  "\u0205\03\120\050\00\u0205\u020a\01\00\00\00\u0206\u0207\03\116\047\00"+
	  "\u0207\u020b\01\00\00\00\u0208\u0209\01\00\00\00\u0209\u020b\01\00\00"+
	  "\00\u020a\u0206\01\00\00\00\u020a\u0208\01\00\00\00\u020b\u021d\01\00"+
	  "\00\00\u020c\u020d\03\112\045\00\u020d\u021d\01\00\00\00\u020e\u020f"+
	  "\03\024\012\00\u020f\u0212\01\00\00\00\u0210\u0211\05\047\00\00\u0211"+
	  "\u0213\01\00\00\00\u0212\u0210\01\00\00\00\u0212\u0213\01\00\00\00\u0213"+
	  "\u021d\01\00\00\00\u0214\u0215\03\110\044\00\u0215\u021a\01\00\00\00"+
	  "\u0216\u0217\03\116\047\00\u0217\u021b\01\00\00\00\u0218\u0219\01\00"+
	  "\00\00\u0219\u021b\01\00\00\00\u021a\u0216\01\00\00\00\u021a\u0218\01"+
	  "\00\00\00\u021b\u021d\01\00\00\00\u021c\u01fc\01\00\00\00\u021c\u0204"+
	  "\01\00\00\00\u021c\u020c\01\00\00\00\u021c\u020e\01\00\00\00\u021c\u0214"+
	  "\01\00\00\00\u021d\105\01\00\00\00\u021e\u021f\03\u0082\101\00\u021f"+
	  "\u0224\01\00\00\00\u0220\u0221\05\046\00\00\u0221\u0225\01\00\00\00\u0222"+
	  "\u0223\05\053\00\00\u0223\u0225\01\00\00\00\u0224\u0220\01\00\00\00\u0224"+
	  "\u0222\01\00\00\00\u0225\u0230\01\00\00\00\u0226\u0227\03\120\050\00"+
	  "\u0227\u0231\01\00\00\00\u0228\u0229\03\130\054\00\u0229\u022e\01\00"+
	  "\00\00\u022a\u022b\05\055\00\00\u022b\u022f\01\00\00\00\u022c\u022d\05"+
	  "\050\00\00\u022d\u022f\01\00\00\00\u022e\u022a\01\00\00\00\u022e\u022c"+
	  "\01\00\00\00\u022e\u022f\01\00\00\00\u022f\u0231\01\00\00\00\u0230\u0226"+
	  "\01\00\00\00\u0230\u0228\01\00\00\00\u0231\107\01\00\00\00\u0232\u0233"+
	  "\05\063\00\00\u0233\u0234\01\00\00\00\u0234\u0235\03\104\042\00\u0235"+
	  "\u0238\01\00\00\00\u0236\u0237\03\104\042\00\u0237\u0239\01\00\00\00"+
	  "\u0238\u0236\01\00\00\00\u0239\u023a\01\00\00\00\u023a\u0238\01\00\00"+
	  "\00\u023a\u023b\01\00\00\00\u023b\u023c\01\00\00\00\u023c\u023d\05\042"+
	  "\00\00\u023d\111\01\00\00\00\u023e\u023f\03\130\054\00\u023f\u0244\01"+
	  "\00\00\00\u0240\u0241\03\114\046\00\u0241\u0245\01\00\00\00\u0242\u0243"+
	  "\01\00\00\00\u0243\u0245\01\00\00\00\u0244\u0240\01\00\00\00\u0244\u0242"+
	  "\01\00\00\00\u0245\113\01\00\00\00\u0246\u0247\03\116\047\00\u0247\u024d"+
	  "\01\00\00\00\u0248\u0249\05\055\00\00\u0249\u024d\01\00\00\00\u024a\u024b"+
	  "\05\050\00\00\u024b\u024d\01\00\00\00\u024c\u0246\01\00\00\00\u024c\u0248"+
	  "\01\00\00\00\u024c\u024a\01\00\00\00\u024d\115\01\00\00\00\u024e\u024f"+
	  "\05\047\00\00\u024f\u0255\01\00\00\00\u0250\u0251\05\051\00\00\u0251"+
	  "\u0255\01\00\00\00\u0252\u0253\05\052\00\00\u0253\u0255\01\00\00\00\u0254"+
	  "\u024e\01\00\00\00\u0254\u0250\01\00\00\00\u0254\u0252\01\00\00\00\u0255"+
	  "\117\01\00\00\00\u0256\u0257\03\134\056\00\u0257\u025a\01\00\00\00\u0258"+
	  "\u0259\07\01\00\00\u0259\u025b\01\00\00\00\u025a\u0258\01\00\00\00\u025a"+
	  "\u025b\01\00\00\00\u025b\u0277\01\00\00\00\u025c\u025d\03\136\057\00"+
	  "\u025d\u0260\01\00\00\00\u025e\u025f\07\02\00\00\u025f\u0261\01\00\00"+
	  "\00\u0260\u025e\01\00\00\00\u0260\u0261\01\00\00\00\u0261\u0277\01\00"+
	  "\00\00\u0262\u0263\03\132\055\00\u0263\u0277\01\00\00\00\u0264\u0265"+
	  "\03\122\051\00\u0265\u0268\01\00\00\00\u0266\u0267\07\03\00\00\u0267"+
	  "\u0269\01\00\00\00\u0268\u0266\01\00\00\00\u0268\u0269\01\00\00\00\u0269"+
	  "\u0277\01\00\00\00\u026a\u026b\05\057\00\00\u026b\u026e\01\00\00\00\u026c"+
	  "\u026d\03\140\060\00\u026d\u026f\01\00\00\00\u026e\u026c\01\00\00\00"+
	  "\u026e\u026f\01\00\00\00\u026f\u0274\01\00\00\00\u0270\u0271\05\055\00"+
	  "\00\u0271\u0275\01\00\00\00\u0272\u0273\05\050\00\00\u0273\u0275\01\00"+
	  "\00\00\u0274\u0270\01\00\00\00\u0274\u0272\01\00\00\00\u0274\u0275\01"+
	  "\00\00\00\u0275\u0277\01\00\00\00\u0276\u0256\01\00\00\00\u0276\u025c"+
	  "\01\00\00\00\u0276\u0262\01\00\00\00\u0276\u0264\01\00\00\00\u0276\u026a"+
	  "\01\00\00\00\u0277\121\01\00\00\00\u0278\u0279\05\066\00\00\u0279\u027a"+
	  "\01\00\00\00\u027a\u027b\03\126\053\00\u027b\u0281\01\00\00\00\u027c"+
	  "\u027d\05\066\00\00\u027d\u027e\01\00\00\00\u027e\u027f\03\124\052\00"+
	  "\u027f\u0281\01\00\00\00\u0280\u0278\01\00\00\00\u0280\u027c\01\00\00"+
	  "\00\u0281\123\01\00\00\00\u0282\u0283\05\041\00\00\u0283\u0284\01\00"+
	  "\00\00\u0284\u0285\03\126\053\00\u0285\u028c\01\00\00\00\u0286\u0287"+
	  "\05\054\00\00\u0287\u0288\01\00\00\00\u0288\u0289\03\126\053\00\u0289"+
	  "\u028b\01\00\00\00\u028a\u0286\01\00\00\00\u028b\u028e\01\00\00\00\u028c"+
	  "\u028a\01\00\00\00\u028c\u028d\01\00\00\00\u028d\u028f\01\00\00\00\u028e"+
	  "\u028c\01\00\00\00\u028f\u0290\05\042\00\00\u0290\125\01\00\00\00\u0291"+
	  "\u0292\05\070\00\00\u0292\u0298\01\00\00\00\u0293\u0294\05\073\00\00"+
	  "\u0294\u0298\01\00\00\00\u0295\u0296\03\134\056\00\u0296\u0298\01\00"+
	  "\00\00\u0297\u0291\01\00\00\00\u0297\u0293\01\00\00\00\u0297\u0295\01"+
	  "\00\00\00\u0298\127\01\00\00\00\u0299\u029a\05\041\00\00\u029a\u02a8"+
	  "\01\00\00\00\u029b\u029c\03\06\03\00\u029c\u029e\01\00\00\00\u029d\u029b"+
	  "\01\00\00\00\u029d\u029e\01\00\00\00\u029e\u02a3\01\00\00\00\u029f\u02a0"+
	  "\03\062\031\00\u02a0\u02a2\01\00\00\00\u02a1\u029f\01\00\00\00\u02a2"+
	  "\u02a5\01\00\00\00\u02a3\u02a1\01\00\00\00\u02a3\u02a4\01\00\00\00\u02a4"+
	  "\u02a6\01\00\00\00\u02a5\u02a3\01\00\00\00\u02a6\u02a7\05\035\00\00\u02a7"+
	  "\u02a9\01\00\00\00\u02a8\u029d\01\00\00\00\u02a8\u02a9\01\00\00\00\u02a9"+
	  "\u02aa\01\00\00\00\u02aa\u02ab\03\076\037\00\u02ab\u02ac\01\00\00\00"+
	  "\u02ac\u02ad\05\042\00\00\u02ad\131\01\00\00\00\u02ae\u02af\05\071\00"+
	  "\00\u02af\u02b2\01\00\00\00\u02b0\u02b1\03\026\013\00\u02b1\u02b3\01"+
	  "\00\00\00\u02b2\u02b0\01\00\00\00\u02b2\u02b3\01\00\00\00\u02b3\u02bc"+
	  "\01\00\00\00\u02b4\u02b5\05\055\00\00\u02b5\u02b9\01\00\00\00\u02b6\u02b7"+
	  "\05\050\00\00\u02b7\u02b9\01\00\00\00\u02b8\u02b4\01\00\00\00\u02b8\u02b6"+
	  "\01\00\00\00\u02b9\u02bd\01\00\00\00\u02ba\u02bb\01\00\00\00\u02bb\u02bd"+
	  "\01\00\00\00\u02bc\u02b8\01\00\00\00\u02bc\u02ba\01\00\00\00\u02bd\133"+
	  "\01\00\00\00\u02be\u02bf\05\073\00\00\u02bf\u02c0\01\00\00\00\u02c0\u02c1"+
	  "\05\060\00\00\u02c1\u02c2\01\00\00\00\u02c2\u02c3\05\073\00\00\u02c3"+
	  "\135\01\00\00\00\u02c4\u02c5\05\070\00\00\u02c5\u02c8\01\00\00\00\u02c6"+
	  "\u02c7\03\140\060\00\u02c7\u02c9\01\00\00\00\u02c8\u02c6\01\00\00\00"+
	  "\u02c8\u02c9\01\00\00\00\u02c9\u02d1\01\00\00\00\u02ca\u02cb\05\073\00"+
	  "\00\u02cb\u02ce\01\00\00\00\u02cc\u02cd\03\140\060\00\u02cd\u02cf\01"+
	  "\00\00\00\u02ce\u02cc\01\00\00\00\u02ce\u02cf\01\00\00\00\u02cf\u02d1"+
	  "\01\00\00\00\u02d0\u02c4\01\00\00\00\u02d0\u02ca\01\00\00\00\u02d1\137"+
	  "\01\00\00\00\u02d2\u02d3\05\044\00\00\u02d3\u02d4\01\00\00\00\u02d4\u02d5"+
	  "\03\142\061\00\u02d5\u02dc\01\00\00\00\u02d6\u02d7\05\037\00\00\u02d7"+
	  "\u02d8\01\00\00\00\u02d8\u02d9\03\142\061\00\u02d9\u02db\01\00\00\00"+
	  "\u02da\u02d6\01\00\00\00\u02db\u02de\01\00\00\00\u02dc\u02da\01\00\00"+
	  "\00\u02dc\u02dd\01\00\00\00\u02dd\u02df\01\00\00\00\u02de\u02dc\01\00"+
	  "\00\00\u02df\u02e0\05\045\00\00\u02e0\141\01\00\00\00\u02e1\u02e2\03"+
	  "\u0084\102\00\u02e2\u02ee\01\00\00\00\u02e3\u02e4\03\u0082\101\00\u02e4"+
	  "\u02e5\01\00\00\00\u02e5\u02e6\05\046\00\00\u02e6\u02eb\01\00\00\00\u02e7"+
	  "\u02e8\03\u0084\102\00\u02e8\u02ec\01\00\00\00\u02e9\u02ea\05\073\00"+
	  "\00\u02ea\u02ec\01\00\00\00\u02eb\u02e7\01\00\00\00\u02eb\u02e9\01\00"+
	  "\00\00\u02ec\u02ee\01\00\00\00\u02ed\u02e1\01\00\00\00\u02ed\u02e3\01"+
	  "\00\00\00\u02ee\143\01\00\00\00\u02ef\u02f0\03\146\063\00\u02f0\u02f2"+
	  "\01\00\00\00\u02f1\u02ef\01\00\00\00\u02f2\u02f5\01\00\00\00\u02f3\u02f1"+
	  "\01\00\00\00\u02f3\u02f4\01\00\00\00\u02f4\u02f6\01\00\00\00\u02f5\u02f3"+
	  "\01\00\00\00\u02f6\u02f7\03\150\064\00\u02f7\145\01\00\00\00\u02f8\u02f9"+
	  "\05\062\00\00\u02f9\u02fa\01\00\00\00\u02fa\u02fb\05\155\00\00\u02fb"+
	  "\u02fc\01\00\00\00\u02fc\u02fd\03\152\065\00\u02fd\147\01\00\00\00\u02fe"+
	  "\u02ff\05\062\00\00\u02ff\u0300\01\00\00\00\u0300\u0301\03\152\065\00"+
	  "\u0301\151\01\00\00\00\u0302\u0303\03\154\066\00\u0303\u030b\01\00\00"+
	  "\00\u0304\u0305\03\170\074\00\u0305\u030b\01\00\00\00\u0306\u0307\05"+
	  "\061\00\00\u0307\u030b\01\00\00\00\u0308\u0309\01\00\00\00\u0309\u030b"+
	  "\01\00\00\00\u030a\u0302\01\00\00\00\u030a\u0304\01\00\00\00\u030a\u0306"+
	  "\01\00\00\00\u030a\u0308\01\00\00\00\u030b\153\01\00\00\00\u030c\u030d"+
	  "\03\156\067\00\u030d\u030f\01\00\00\00\u030e\u030c\01\00\00\00\u030f"+
	  "\u0310\01\00\00\00\u0310\u030e\01\00\00\00\u0310\u0311\01\00\00\00\u0311"+
	  "\155\01\00\00\00\u0312\u0313\03\160\070\00\u0313\u0323\01\00\00\00\u0314"+
	  "\u0315\03\160\070\00\u0315\u0316\01\00\00\00\u0316\u0317\03\116\047\00"+
	  "\u0317\u0323\01\00\00\00\u0318\u0319\03\166\073\00\u0319\u031e\01\00"+
	  "\00\00\u031a\u031b\03\116\047\00\u031b\u031f\01\00\00\00\u031c\u031d"+
	  "\01\00\00\00\u031d\u031f\01\00\00\00\u031e\u031a\01\00\00\00\u031e\u031c"+
	  "\01\00\00\00\u031f\u0323\01\00\00\00\u0320\u0321\03\162\071\00\u0321"+
	  "\u0323\01\00\00\00\u0322\u0312\01\00\00\00\u0322\u0314\01\00\00\00\u0322"+
	  "\u0318\01\00\00\00\u0322\u0320\01\00\00\00\u0323\157\01\00\00\00\u0324"+
	  "\u0325\05\070\00\00\u0325\u0328\01\00\00\00\u0326\u0327\03\140\060\00"+
	  "\u0327\u0329\01\00\00\00\u0328\u0326\01\00\00\00\u0328\u0329\01\00\00"+
	  "\00\u0329\u032c\01\00\00\00\u032a\u032b\03\026\013\00\u032b\u032d\01"+
	  "\00\00\00\u032c\u032a\01\00\00\00\u032c\u032d\01\00\00\00\u032d\u033d"+
	  "\01\00\00\00\u032e\u032f\05\071\00\00\u032f\u033d\01\00\00\00\u0330\u0331"+
	  "\05\073\00\00\u0331\u0334\01\00\00\00\u0332\u0333\03\140\060\00\u0333"+
	  "\u0335\01\00\00\00\u0334\u0332\01\00\00\00\u0334\u0335\01\00\00\00\u0335"+
	  "\u033d\01\00\00\00\u0336\u0337\05\056\00\00\u0337\u0338\01\00\00\00\u0338"+
	  "\u0339\03\u0082\101\00\u0339\u033d\01\00\00\00\u033a\u033b\03\024\012"+
	  "\00\u033b\u033d\01\00\00\00\u033c\u0324\01\00\00\00\u033c\u032e\01\00"+
	  "\00\00\u033c\u0330\01\00\00\00\u033c\u0336\01\00\00\00\u033c\u033a\01"+
	  "\00\00\00\u033d\161\01\00\00\00\u033e\u033f\05\041\00\00\u033f\u0340"+
	  "\01\00\00\00\u0340\u0341\03\154\066\00\u0341\u0342\01\00\00\00\u0342"+
	  "\u0343\05\042\00\00\u0343\u0344\01\00\00\00\u0344\u0345\03\164\072\00"+
	  "\u0345\163\01\00\00\00\u0346\u0347\05\047\00\00\u0347\u034b\01\00\00"+
	  "\00\u0348\u0349\05\051\00\00\u0349\u034b\01\00\00\00\u034a\u0346\01\00"+
	  "\00\00\u034a\u0348\01\00\00\00\u034b\165\01\00\00\00\u034c\u034d\05\063"+
	  "\00\00\u034d\u034e\01\00\00\00\u034e\u034f\03\160\070\00\u034f\u0354"+
	  "\01\00\00\00\u0350\u0351\03\156\067\00\u0351\u0353\01\00\00\00\u0352"+
	  "\u0350\01\00\00\00\u0353\u0356\01\00\00\00\u0354\u0352\01\00\00\00\u0354"+
	  "\u0355\01\00\00\00\u0355\u0357\01\00\00\00\u0356\u0354\01\00\00\00\u0357"+
	  "\u0358\05\042\00\00\u0358\167\01\00\00\00\u0359\u035a\05\033\00\00\u035a"+
	  "\u035b\01\00\00\00\u035b\u035c\05\041\00\00\u035c\u035d\01\00\00\00\u035d"+
	  "\u035e\03\176\077\00\u035e\u035f\01\00\00\00\u035f\u0360\05\042\00\00"+
	  "\u0360\u0365\01\00\00\00\u0361\u0362\05\06\00\00\u0362\u0366\01\00\00"+
	  "\00\u0363\u0364\05\07\00\00\u0364\u0366\01\00\00\00\u0365\u0361\01\00"+
	  "\00\00\u0365\u0363\01\00\00\00\u0366\u036e\01\00\00\00\u0367\u0368\03"+
	  "\172\075\00\u0368\u036e\01\00\00\00\u0369\u036a\03\174\076\00\u036a\u036e"+
	  "\01\00\00\00\u036b\u036c\03\024\012\00\u036c\u036e\01\00\00\00\u036d"+
	  "\u0359\01\00\00\00\u036d\u0367\01\00\00\00\u036d\u0369\01\00\00\00\u036d"+
	  "\u036b\01\00\00\00\u036e\171\01\00\00\00\u036f\u0370\03\u0082\101\00"+
	  "\u0370\u0371\01\00\00\00\u0371\u0372\05\041\00\00\u0372\u0373\01\00\00"+
	  "\00\u0373\u0374\03\176\077\00\u0374\u0375\01\00\00\00\u0375\u0376\05"+
	  "\042\00\00\u0376\173\01\00\00\00\u0377\u0378\05\041\00\00\u0378\u0379"+
	  "\01\00\00\00\u0379\u037a\03\024\012\00\u037a\u037b\01\00\00\00\u037b"+
	  "\u037c\05\042\00\00\u037c\u037d\01\00\00\00\u037d\u037e\05\041\00\00"+
	  "\u037e\u037f\01\00\00\00\u037f\u0380\03\176\077\00\u0380\u0381\01\00"+
	  "\00\00\u0381\u0382\05\042\00\00\u0382\175\01\00\00\00\u0383\u0384\03"+
	  "\u0080\100\00\u0384\u038b\01\00\00\00\u0385\u0386\05\037\00\00\u0386"+
	  "\u0387\01\00\00\00\u0387\u0388\03\u0080\100\00\u0388\u038a\01\00\00\00"+
	  "\u0389\u0385\01\00\00\00\u038a\u038d\01\00\00\00\u038b\u0389\01\00\00"+
	  "\00\u038b\u038c\01\00\00\00\u038c\u0391\01\00\00\00\u038d\u038b\01\00"+
	  "\00\00\u038e\u038f\01\00\00\00\u038f\u0391\01\00\00\00\u0390\u0383\01"+
	  "\00\00\00\u0390\u038e\01\00\00\00\u0391\177\01\00\00\00\u0392\u0393\03"+
	  "\u0082\101\00\u0393\u0394\01\00\00\00\u0394\u0395\05\046\00\00\u0395"+
	  "\u0396\01\00\00\00\u0396\u0397\03\024\012\00\u0397\u0081\01\00\00\00"+
	  "\u0398\u0399\05\071\00\00\u0399\u039f\01\00\00\00\u039a\u039b\05\070"+
	  "\00\00\u039b\u039f\01\00\00\00\u039c\u039d\05\033\00\00\u039d\u039f\01"+
	  "\00\00\00\u039e\u0398\01\00\00\00\u039e\u039a\01\00\00\00\u039e\u039c"+
	  "\01\00\00\00\u039f\u0083\01\00\00\00\u03a0\u03a1\03\u0082\101\00\u03a1"+
	  "\u03a8\01\00\00\00\u03a2\u03a3\05\057\00\00\u03a3\u03a4\01\00\00\00\u03a4"+
	  "\u03a5\03\u0082\101\00\u03a5\u03a7\01\00\00\00\u03a6\u03a2\01\00\00\00"+
	  "\u03a7\u03aa\01\00\00\00\u03a8\u03a6\01\00\00\00\u03a8\u03a9\01\00\00"+
	  "\00\u03a9\u0085\01\00\00\00\u03aa\u03a8\01\00\00\00\u03ab\u03ac\03\100"+
	  "\040\00\u03ac\u03ad\01\00\00\00\u03ad\u03ae\05\uffff\00\00\u03ae\u0087"+
	  "\01\00\00\00\u03af\u03b0\03\104\042\00\u03b0\u03b1\01\00\00\00\u03b1"+
	  "\u03b2\05\uffff\00\00\u03b2\u0089\01\00\00\00\u03b3\u03b4\03\040\020"+
	  "\00\u03b4\u03b5\01\00\00\00\u03b5\u03b6\05\uffff\00\00\u03b6\u008b\01"+
	  "\00\00\00\u03b7\u03b8\03\130\054\00\u03b8\u03b9\01\00\00\00\u03b9\u03ba"+
	  "\05\uffff\00\00\u03ba\u008d\01\00\00\00\130\u0090\01\u009c\01\u00a5\01"+
	  "\u00b8\01\u00c2\01\u00cc\01\u00df\01\u00eb\01\u00f8\01\u0100\01\u010c"+
	  "\01\u0112\01\u011e\01\u0120\01\u012b\01\u0136\01\u0142\01\u014e\01\u0154"+
	  "\01\u0159\01\u015d\01\u0163\01\u0167\01\u016b\01\u016f\01\u017f\01\u0184"+
	  "\01\u0194\01\u019b\01\u01ab\01\u01bc\01\u01c6\01\u01d2\01\u01db\01\u01e5"+
	  "\01\u01ee\01\u01f4\01\u01fa\01\u0202\01\u020a\01\u0212\01\u021a\01\u021c"+
	  "\01\u0224\01\u022e\01\u0230\01\u023a\01\u0244\01\u024c\01\u0254\01\u025a"+
	  "\01\u0260\01\u0268\01\u026e\01\u0274\01\u0276\01\u0280\01\u028c\01\u0297"+
	  "\01\u029d\01\u02a3\01\u02a8\01\u02b2\01\u02b8\01\u02bc\01\u02c8\01\u02ce"+
	  "\01\u02d0\01\u02dc\01\u02eb\01\u02ed\01\u02f3\01\u030a\01\u0310\01\u031e"+
	  "\01\u0322\01\u0328\01\u032c\01\u0334\01\u033c\01\u034a\01\u0354\01\u0365"+
	  "\01\u036d\01\u038b\01\u0390\01\u039e\01\u03a8\01";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}