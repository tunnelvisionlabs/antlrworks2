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
		DOLLAR=46, PROTECTED=19, LT=36, ACTION_COLON=86, STAR=41, NESTED_ACTION=75, 
		DOUBLE_ANGLE_STRING_LITERAL=7, FRAGMENT=14, ACTION_ESCAPE=88, ACTION_REFERENCE=90, 
		ACTION_COLON2=85, ACTION_DOT=76, NOT=54, TREE_BEGIN=51, ARG_ACTION_LT=62, 
		ACTION_NEWLINE=95, TOKEN_REF=56, LPAREN=33, ARG_ACTION_ELEMENT=70, ARG_ACTION_RPAREN=65, 
		ARG_ACTION_GT=63, AT=52, RPAREN=34, ARG_ACTION_LPAREN=64, IMPORT=13, STRING_LITERAL=59, 
		TREE=17, SCOPE=12, END_ACTION=96, ETC=49, COMMA=31, ACTION_GT=78, ACTION_LITERAL=92, 
		ARG_ACTION_NEWLINE=73, DOC_COMMENT=3, BLOCK_COMMENT=4, PLUS=42, BEGIN_ACTION=9, 
		DOUBLE_QUOTE_STRING_LITERAL=6, DOT=47, ACTION_LBRACK=81, MODE=28, ACTION_WORD=89, 
		GRAMMAR=18, ARG_ACTION_ESCAPE=68, RETURNS=22, ARG_ACTION_TEXT=71, ACTION_EQUALS=83, 
		LOCALS=23, ACTION_WS=94, IMPLIES=35, RBRACE=55, POUND=53, LINE_COMMENT=5, 
		PRIVATE=21, RARROW=50, END_ARG_ACTION=74, TOKENS=11, RANGE=48, THROWS=24, 
		INT=58, BANG=40, SEMI=32, ROOT=45, RULE_REF=57, ARG_ACTION_EQUALS=66, 
		ACTION_RPAREN=80, ACTION_COMMA=84, COLON=29, COLONCOLON=30, ACTION_RBRACK=82, 
		WS=60, ACTION_COMMENT=91, QUESTION=39, FINALLY=26, ACTION_LT=77, TEMPLATE=27, 
		ACTION_TEXT=93, LEXER=15, ERRCHAR=61, ACTION_MINUS=87, ARG_ACTION_COMMA=67, 
		OR=44, ASSIGN=38, PLUS_ASSIGN=43, ARG_ACTION_WS=72, GT=37, CATCH=25, ARG_ACTION_WORD=69, 
		PUBLIC=20, PARSER=16, ACTION_LPAREN=79, OPTIONS=10, BEGIN_ARG_ACTION=8, 
		RULE=97, PREC_RULE=98, RULES=99, RULEMODIFIERS=100, RULEACTIONS=101, BLOCK=102, 
		REWRITE_BLOCK=103, REWRITE_SEQ=104, OPTIONAL=105, CLOSURE=106, POSITIVE_CLOSURE=107, 
		SET=108, CHAR_RANGE=109, EPSILON=110, ALT=111, ALTLIST=112, ID=113, ARG=114, 
		ARGLIST=115, RET=116, COMBINED=117, INITACTION=118, LABEL=119, WILDCARD=120, 
		LIST=121, ELEMENT_OPTIONS=122, ST_RESULT=123, RESULT=124, ALT_REWRITE=125, 
		DOWN_TOKEN=126, UP_TOKEN=127, SEMPRED=128;
	public static final String[] tokenNames = {
		"<INVALID>", "<INVALID>", "<INVALID>",
		"DOC_COMMENT", "BLOCK_COMMENT", "LINE_COMMENT", "DOUBLE_QUOTE_STRING_LITERAL", 
		"DOUBLE_ANGLE_STRING_LITERAL", "BEGIN_ARG_ACTION", "BEGIN_ACTION", "OPTIONS", 
		"TOKENS", "scope", "import", "fragment", "lexer", "parser", "tree", "grammar", 
		"protected", "public", "private", "returns", "locals", "throws", "catch", 
		"finally", "template", "mode", "COLON", "COLONCOLON", "COMMA", ";", "LPAREN", 
		"RPAREN", "=>", "LT", "GT", "ASSIGN", "?", "!", "*", "+", "+=", "|", "^", 
		"$", "DOT", "..", "...", "->", "^(", "@", "#", "~", "RBRACE", "TOKEN_REF", 
		"RULE_REF", "INT", "STRING_LITERAL", "WS", "ERRCHAR", "ARG_ACTION_LT", 
		"ARG_ACTION_GT", "ARG_ACTION_LPAREN", "ARG_ACTION_RPAREN", "ARG_ACTION_EQUALS", 
		"ARG_ACTION_COMMA", "ARG_ACTION_ESCAPE", "ARG_ACTION_WORD", "ARG_ACTION_ELEMENT", 
		"ARG_ACTION_TEXT", "ARG_ACTION_WS", "ARG_ACTION_NEWLINE", "END_ARG_ACTION", 
		"{", ".", "<", ">", "(", ")", "[", "]", "=", ",", "::", ":", "-", "ACTION_ESCAPE", 
		"ACTION_WORD", "ACTION_REFERENCE", "ACTION_COMMENT", "ACTION_LITERAL", 
		"ACTION_TEXT", "ACTION_WS", "ACTION_NEWLINE", "}", "RULE", "PREC_RULE", 
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
		RULE_actionExpression = 11, RULE_actionScopeExpression = 12, RULE_argActionBlock = 13, 
		RULE_argActionParameters = 14, RULE_argActionParameter = 15, RULE_argActionParameterType = 16, 
		RULE_argActionParameterTypePart = 17, RULE_ignored = 18, RULE_action = 19, 
		RULE_actionScopeName = 20, RULE_mode_ = 21, RULE_rules = 22, RULE_rule = 23, 
		RULE_exceptionGroup = 24, RULE_exceptionHandler = 25, RULE_finallyClause = 26, 
		RULE_rulePrequels = 27, RULE_rulePrequel = 28, RULE_ruleReturns = 29, 
		RULE_throwsSpec = 30, RULE_locals_ = 31, RULE_ruleAction = 32, RULE_ruleModifiers = 33, 
		RULE_ruleModifier = 34, RULE_ruleBlock = 35, RULE_ruleAltList = 36, RULE_labeledAlt = 37, 
		RULE_altList = 38, RULE_alternative = 39, RULE_elements = 40, RULE_element = 41, 
		RULE_labeledElement = 42, RULE_treeSpec = 43, RULE_ebnf = 44, RULE_blockSuffix = 45, 
		RULE_ebnfSuffix = 46, RULE_atom = 47, RULE_notSet = 48, RULE_blockSet = 49, 
		RULE_setElement = 50, RULE_block = 51, RULE_ruleref = 52, RULE_range = 53, 
		RULE_terminal = 54, RULE_elementOptions = 55, RULE_elementOption = 56, 
		RULE_rewrite = 57, RULE_predicatedRewrite = 58, RULE_nakedRewrite = 59, 
		RULE_rewriteAlt = 60, RULE_rewriteTreeAlt = 61, RULE_rewriteTreeElement = 62, 
		RULE_rewriteTreeAtom = 63, RULE_rewriteTreeEbnf = 64, RULE_rewriteEbnfSuffix = 65, 
		RULE_rewriteTree = 66, RULE_rewriteTemplate = 67, RULE_rewriteTemplateRef = 68, 
		RULE_rewriteIndirectTemplateHead = 69, RULE_rewriteTemplateArgs = 70, 
		RULE_rewriteTemplateArg = 71, RULE_id = 72, RULE_qid = 73, RULE_alternativeEntry = 74, 
		RULE_elementEntry = 75, RULE_ruleEntry = 76, RULE_blockEntry = 77;
	public static final String[] ruleNames = {
		"grammarSpec", "grammarType", "prequelConstruct", "optionsSpec", "option", 
		"optionValue", "delegateGrammars", "delegateGrammar", "tokensSpec", "tokenSpec", 
		"actionBlock", "actionExpression", "actionScopeExpression", "argActionBlock", 
		"argActionParameters", "argActionParameter", "argActionParameterType", 
		"argActionParameterTypePart", "ignored", "action", "actionScopeName", 
		"mode_", "rules", "rule", "exceptionGroup", "exceptionHandler", "finallyClause", 
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(156); match(DOC_COMMENT);
					}
					break;
			}
			setState(160); grammarType();
			setState(162); id();
			setState(164); match(SEMI);
			setState(170);
			_errHandler.sync(this);
			int _alt272 = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt272!=2 && _alt272!=-1 ) {
			    if ( _alt272==1 ) {
			        {
			        {
			        setState(166); prequelConstruct();
			        }
			        } 
			    }
				setState(172);
				_errHandler.sync(this);
				_alt272 = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(173); rules();
			setState(179);
			_errHandler.sync(this);
			int _alt285 = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt285!=2 && _alt285!=-1 ) {
			    if ( _alt285==1 ) {
			        {
			        {
			        setState(175); mode_();
			        }
			        } 
			    }
				setState(181);
				_errHandler.sync(this);
				_alt285 = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(182); match(EOF);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(184); _localctx.t = match(LEXER);
					setState(186); _localctx.g = match(GRAMMAR);
					}
					break;

				case 2:
					{
					setState(188); _localctx.t = match(PARSER);
					setState(190); _localctx.g = match(GRAMMAR);
					}
					break;

				case 3:
					{
					setState(192); _localctx.t = match(TREE);
					setState(194); _localctx.g = match(GRAMMAR);
					}
					break;

				case 4:
					{
					setState(196); _localctx.g = match(GRAMMAR);
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
		try {
			setState(208);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(200); optionsSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(202); delegateGrammars();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(204); tokensSpec();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(206); action();
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210); match(OPTIONS);
			setState(218);
			_errHandler.sync(this);
			int _alt450 = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt450!=2 && _alt450!=-1 ) {
			    if ( _alt450==1 ) {
			        {
			        {
			        setState(212); option();
			        setState(214); match(SEMI);
			        }
			        } 
			    }
				setState(220);
				_errHandler.sync(this);
				_alt450 = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(221); match(RBRACE);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223); id();
			setState(225); match(ASSIGN);
			setState(227); optionValue();
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
		try {
			setState(237);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(229); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(231); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(233); match(INT);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(235); match(STAR);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239); match(IMPORT);
			setState(241); delegateGrammar();
			setState(249);
			_errHandler.sync(this);
			int _alt543 = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt543!=2 && _alt543!=-1 ) {
			    if ( _alt543==1 ) {
			        {
			        {
			        setState(243); match(COMMA);
			        setState(245); delegateGrammar();
			        }
			        } 
			    }
				setState(251);
				_errHandler.sync(this);
				_alt543 = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(252); match(SEMI);
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
		try {
			setState(262);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(254); id();
					setState(256); match(ASSIGN);
					setState(258); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(260); id();
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264); match(TOKENS);
			setState(268); 
			_errHandler.sync(this);
			int _alt581 = getInterpreter().adaptivePredict(_input,9,_ctx);
			do {
				switch ( _alt581 ) {
					case 1:
						{
						{
						setState(266); tokenSpec();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(270); 
				_errHandler.sync(this);
				_alt581 = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt581!=2 && _alt581!=-1 );
			setState(272); match(RBRACE);
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
		try {
			setState(288);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(274); id();
					setState(282);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
						case 1:
							{
							setState(276); match(ASSIGN);
							setState(278); match(STRING_LITERAL);
							}
							break;

						case 2:
							{
							}
							break;
					}
					setState(284); match(SEMI);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(286); match(RULE_REF);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290); match(BEGIN_ACTION);
			setState(340);
			_errHandler.sync(this);
			int _alt718 = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt718!=2 && _alt718!=-1 ) {
			    if ( _alt718==1 ) {
			        {
			        setState(338);
			        //_errHandler.sync(this);
			        switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			        	case 1:
			        		{
			        		setState(292); actionBlock();
			        		}
			        		break;

			        	case 2:
			        		{
			        		setState(294); actionExpression();
			        		}
			        		break;

			        	case 3:
			        		{
			        		setState(296); actionScopeExpression();
			        		}
			        		break;

			        	case 4:
			        		{
			        		setState(298); match(ACTION_WS);
			        		}
			        		break;

			        	case 5:
			        		{
			        		setState(300); match(ACTION_NEWLINE);
			        		}
			        		break;

			        	case 6:
			        		{
			        		setState(302); match(ACTION_COMMENT);
			        		}
			        		break;

			        	case 7:
			        		{
			        		setState(304); match(ACTION_LITERAL);
			        		}
			        		break;

			        	case 8:
			        		{
			        		setState(306); match(ACTION_TEXT);
			        		}
			        		break;

			        	case 9:
			        		{
			        		setState(308); match(ACTION_LT);
			        		}
			        		break;

			        	case 10:
			        		{
			        		setState(310); match(ACTION_GT);
			        		}
			        		break;

			        	case 11:
			        		{
			        		setState(312); match(ACTION_LPAREN);
			        		}
			        		break;

			        	case 12:
			        		{
			        		setState(314); match(ACTION_RPAREN);
			        		}
			        		break;

			        	case 13:
			        		{
			        		setState(316); match(ACTION_LBRACK);
			        		}
			        		break;

			        	case 14:
			        		{
			        		setState(318); match(ACTION_RBRACK);
			        		}
			        		break;

			        	case 15:
			        		{
			        		setState(320); match(ACTION_EQUALS);
			        		}
			        		break;

			        	case 16:
			        		{
			        		setState(322); match(ACTION_COMMA);
			        		}
			        		break;

			        	case 17:
			        		{
			        		setState(324); match(ACTION_ESCAPE);
			        		}
			        		break;

			        	case 18:
			        		{
			        		setState(326); match(ACTION_WORD);
			        		}
			        		break;

			        	case 19:
			        		{
			        		setState(328); match(ACTION_REFERENCE);
			        		}
			        		break;

			        	case 20:
			        		{
			        		setState(330); match(ACTION_COLON);
			        		}
			        		break;

			        	case 21:
			        		{
			        		setState(332); match(ACTION_COLON2);
			        		}
			        		break;

			        	case 22:
			        		{
			        		setState(334); match(ACTION_MINUS);
			        		}
			        		break;

			        	case 23:
			        		{
			        		setState(336); match(ACTION_DOT);
			        		}
			        		break;
			        }
			        } 
			    }
				setState(342);
				_errHandler.sync(this);
				_alt718 = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			setState(343); match(END_ACTION);
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

	public static class actionExpressionContext extends ParserRuleContext<Token> {
		public Token ref;;
		public Token op;;
		public Token member;;
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345); _localctx.ref = match(ACTION_REFERENCE);
			setState(351);
			_errHandler.sync(this);
			int _alt733 = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt733!=2 && _alt733!=-1 ) {
			    if ( _alt733==1 ) {
			        {
			        {
			        setState(347); ignored();
			        }
			        } 
			    }
				setState(353);
				_errHandler.sync(this);
				_alt733 = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(354); _localctx.op = match(ACTION_DOT);
			setState(360);
			_errHandler.sync(this);
			int _alt740 = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt740!=2 && _alt740!=-1 ) {
			    if ( _alt740==1 ) {
			        {
			        {
			        setState(356); ignored();
			        }
			        } 
			    }
				setState(362);
				_errHandler.sync(this);
				_alt740 = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(363); _localctx.member = match(ACTION_WORD);
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

	public static class actionScopeExpressionContext extends ParserRuleContext<Token> {
		public Token ref;;
		public Token neg;;
		public Token index;;
		public Token op;;
		public Token member;;
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365); _localctx.ref = match(ACTION_REFERENCE);
			setState(371);
			_errHandler.sync(this);
			int _alt757 = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt757!=2 && _alt757!=-1 ) {
			    if ( _alt757==1 ) {
			        {
			        {
			        setState(367); ignored();
			        }
			        } 
			    }
				setState(373);
				_errHandler.sync(this);
				_alt757 = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(412);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(374); match(ACTION_LBRACK);
					setState(380);
					_errHandler.sync(this);
					int _alt763 = getInterpreter().adaptivePredict(_input,17,_ctx);
					while ( _alt763!=2 && _alt763!=-1 ) {
					    if ( _alt763==1 ) {
					        {
					        {
					        setState(376); ignored();
					        }
					        } 
					    }
						setState(382);
						_errHandler.sync(this);
						_alt763 = getInterpreter().adaptivePredict(_input,17,_ctx);
					}
					setState(392);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
						case 1:
							{
							setState(383); _localctx.neg = match(ACTION_MINUS);
							setState(389);
							_errHandler.sync(this);
							int _alt771 = getInterpreter().adaptivePredict(_input,18,_ctx);
							while ( _alt771!=2 && _alt771!=-1 ) {
							    if ( _alt771==1 ) {
							        {
							        {
							        setState(385); ignored();
							        }
							        } 
							    }
								setState(391);
								_errHandler.sync(this);
								_alt771 = getInterpreter().adaptivePredict(_input,18,_ctx);
							}
							}
							break;
					}
					setState(394); _localctx.index = match(ACTION_WORD);
					setState(400);
					_errHandler.sync(this);
					int _alt780 = getInterpreter().adaptivePredict(_input,20,_ctx);
					while ( _alt780!=2 && _alt780!=-1 ) {
					    if ( _alt780==1 ) {
					        {
					        {
					        setState(396); ignored();
					        }
					        } 
					    }
						setState(402);
						_errHandler.sync(this);
						_alt780 = getInterpreter().adaptivePredict(_input,20,_ctx);
					}
					setState(403); match(ACTION_RBRACK);
					setState(409);
					_errHandler.sync(this);
					int _alt785 = getInterpreter().adaptivePredict(_input,21,_ctx);
					while ( _alt785!=2 && _alt785!=-1 ) {
					    if ( _alt785==1 ) {
					        {
					        {
					        setState(405); ignored();
					        }
					        } 
					    }
						setState(411);
						_errHandler.sync(this);
						_alt785 = getInterpreter().adaptivePredict(_input,21,_ctx);
					}
					}
					break;
			}
			setState(414); _localctx.op = match(ACTION_COLON2);
			setState(420);
			_errHandler.sync(this);
			int _alt794 = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt794!=2 && _alt794!=-1 ) {
			    if ( _alt794==1 ) {
			        {
			        {
			        setState(416); ignored();
			        }
			        } 
			    }
				setState(422);
				_errHandler.sync(this);
				_alt794 = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			setState(423); _localctx.member = match(ACTION_WORD);
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
		argActionBlockContext _localctx = new argActionBlockContext(_ctx, 26);
		enterRule(_localctx, RULE_argActionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(425); match(BEGIN_ARG_ACTION);
			setState(431);
			_errHandler.sync(this);
			int _alt857 = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt857!=2 && _alt857!=-1 ) {
			    if ( _alt857==1 ) {
			        {
			        {
			        setState(427);
			        _input.LT(1);
			        _la = _input.LA(1);
			        if ( !(_la==ARG_ACTION_LT || _la==ARG_ACTION_GT || _la==ARG_ACTION_LPAREN || _la==ARG_ACTION_RPAREN || _la==ARG_ACTION_EQUALS || _la==ARG_ACTION_COMMA || _la==ARG_ACTION_ESCAPE || _la==ARG_ACTION_WORD || _la==ARG_ACTION_ELEMENT || _la==ARG_ACTION_TEXT || _la==ARG_ACTION_WS || _la==ARG_ACTION_NEWLINE) ) {
			        _errHandler.recoverInline(this);
			        }
			        consume();
			        }
			        } 
			    }
				setState(433);
				_errHandler.sync(this);
				_alt857 = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			setState(434); match(END_ARG_ACTION);
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

	public static class argActionParametersContext extends ParserRuleContext<Token> {
		public argActionParameterContext parameters;;
		public List<argActionParameterContext> parameters_list = new ArrayList<argActionParameterContext>();;
		public argActionParametersContext(ParserRuleContext<Token> parent, int state) {
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

	public final argActionParametersContext argActionParameters() throws RecognitionException {
		argActionParametersContext _localctx = new argActionParametersContext(_ctx, 28);
		enterRule(_localctx, RULE_argActionParameters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436); match(BEGIN_ARG_ACTION);
			setState(442);
			_errHandler.sync(this);
			int _alt870 = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt870!=2 && _alt870!=-1 ) {
			    if ( _alt870==1 ) {
			        {
			        {
			        setState(438); ignored();
			        }
			        } 
			    }
				setState(444);
				_errHandler.sync(this);
				_alt870 = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(477);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(445); _localctx.parameters = argActionParameter();
					_localctx.parameters_list.add(_localctx.parameters);
					setState(451);
					_errHandler.sync(this);
					int _alt878 = getInterpreter().adaptivePredict(_input,26,_ctx);
					while ( _alt878!=2 && _alt878!=-1 ) {
					    if ( _alt878==1 ) {
					        {
					        {
					        setState(447); ignored();
					        }
					        } 
					    }
						setState(453);
						_errHandler.sync(this);
						_alt878 = getInterpreter().adaptivePredict(_input,26,_ctx);
					}
					setState(474);
					_errHandler.sync(this);
					int _alt893 = getInterpreter().adaptivePredict(_input,29,_ctx);
					while ( _alt893!=2 && _alt893!=-1 ) {
					    if ( _alt893==1 ) {
					        {
					        {
					        setState(454); match(ARG_ACTION_COMMA);
					        setState(460);
					        _errHandler.sync(this);
					        int _alt884 = getInterpreter().adaptivePredict(_input,27,_ctx);
					        while ( _alt884!=2 && _alt884!=-1 ) {
					            if ( _alt884==1 ) {
					                {
					                {
					                setState(456); ignored();
					                }
					                } 
					            }
					        	setState(462);
					        	_errHandler.sync(this);
					        	_alt884 = getInterpreter().adaptivePredict(_input,27,_ctx);
					        }
					        setState(463); _localctx.parameters = argActionParameter();
					        _localctx.parameters_list.add(_localctx.parameters);
					        setState(469);
					        _errHandler.sync(this);
					        int _alt891 = getInterpreter().adaptivePredict(_input,28,_ctx);
					        while ( _alt891!=2 && _alt891!=-1 ) {
					            if ( _alt891==1 ) {
					                {
					                {
					                setState(465); ignored();
					                }
					                } 
					            }
					        	setState(471);
					        	_errHandler.sync(this);
					        	_alt891 = getInterpreter().adaptivePredict(_input,28,_ctx);
					        }
					        }
					        } 
					    }
						setState(476);
						_errHandler.sync(this);
						_alt893 = getInterpreter().adaptivePredict(_input,29,_ctx);
					}
					}
					break;
			}
			setState(479); match(END_ARG_ACTION);
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

	public static class argActionParameterContext extends ParserRuleContext<Token> {
		public argActionParameterTypeContext type;;
		public Token name;;
		public argActionParameterContext(ParserRuleContext<Token> parent, int state) {
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

	public final argActionParameterContext argActionParameter() throws RecognitionException {
		argActionParameterContext _localctx = new argActionParameterContext(_ctx, 30);
		enterRule(_localctx, RULE_argActionParameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(481); _localctx.type = argActionParameterType();
					}
					break;
			}
			setState(489);
			_errHandler.sync(this);
			int _alt911 = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt911!=2 && _alt911!=-1 ) {
			    if ( _alt911==1 ) {
			        {
			        {
			        setState(485); ignored();
			        }
			        } 
			    }
				setState(491);
				_errHandler.sync(this);
				_alt911 = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			setState(492); _localctx.name = match(ARG_ACTION_WORD);
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

	public static class argActionParameterTypeContext extends ParserRuleContext<Token> {
		public argActionParameterTypeContext(ParserRuleContext<Token> parent, int state) {
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

	public final argActionParameterTypeContext argActionParameterType() throws RecognitionException {
		argActionParameterTypeContext _localctx = new argActionParameterTypeContext(_ctx, 32);
		enterRule(_localctx, RULE_argActionParameterType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(494); argActionParameterTypePart();
			setState(507);
			_errHandler.sync(this);
			int _alt931 = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt931!=2 && _alt931!=-1 ) {
			    if ( _alt931==1 ) {
			        {
			        {
			        setState(500);
			        _errHandler.sync(this);
			        int _alt927 = getInterpreter().adaptivePredict(_input,33,_ctx);
			        while ( _alt927!=2 && _alt927!=-1 ) {
			            if ( _alt927==1 ) {
			                {
			                {
			                setState(496); ignored();
			                }
			                } 
			            }
			        	setState(502);
			        	_errHandler.sync(this);
			        	_alt927 = getInterpreter().adaptivePredict(_input,33,_ctx);
			        }
			        setState(503); argActionParameterTypePart();
			        }
			        } 
			    }
				setState(509);
				_errHandler.sync(this);
				_alt931 = getInterpreter().adaptivePredict(_input,34,_ctx);
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

	public static class argActionParameterTypePartContext extends ParserRuleContext<Token> {
		public argActionParameterTypePartContext(ParserRuleContext<Token> parent, int state) {
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

	public final argActionParameterTypePartContext argActionParameterTypePart() throws RecognitionException {
		argActionParameterTypePartContext _localctx = new argActionParameterTypePartContext(_ctx, 34);
		enterRule(_localctx, RULE_argActionParameterTypePart);
		try {
			setState(528);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(510); match(ARG_ACTION_WORD);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(512); match(ARG_ACTION_LT);
					setState(516);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
						case 1:
							{
							setState(514); argActionParameterType();
							}
							break;
					}
					setState(518); match(ARG_ACTION_GT);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(520); match(ARG_ACTION_LPAREN);
					setState(524);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
						case 1:
							{
							setState(522); argActionParameterType();
							}
							break;
					}
					setState(526); match(ARG_ACTION_RPAREN);
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

	public static class ignoredContext extends ParserRuleContext<Token> {
		public ignoredContext(ParserRuleContext<Token> parent, int state) {
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

	public final ignoredContext ignored() throws RecognitionException {
		ignoredContext _localctx = new ignoredContext(_ctx, 36);
		enterRule(_localctx, RULE_ignored);
		try {
			setState(540);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(530); match(ACTION_WS);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(532); match(ACTION_NEWLINE);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(534); match(ACTION_COMMENT);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(536); match(ARG_ACTION_WS);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(538); match(ARG_ACTION_NEWLINE);
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
		actionContext _localctx = new actionContext(_ctx, 38);
		enterRule(_localctx, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(542); match(AT);
			setState(548);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
				case 1:
					{
					setState(544); actionScopeName();
					setState(546); match(COLONCOLON);
					}
					break;
			}
			setState(550); id();
			setState(552); actionBlock();
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
		actionScopeNameContext _localctx = new actionScopeNameContext(_ctx, 40);
		enterRule(_localctx, RULE_actionScopeName);
		try {
			setState(560);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(554); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(556); match(LEXER);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(558); match(PARSER);
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
		mode_Context _localctx = new mode_Context(_ctx, 42);
		enterRule(_localctx, RULE_mode_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(562); match(MODE);
			setState(564); id();
			setState(566); match(SEMI);
			setState(570); 
			_errHandler.sync(this);
			int _alt1052 = getInterpreter().adaptivePredict(_input,41,_ctx);
			do {
				switch ( _alt1052 ) {
					case 1:
						{
						{
						setState(568); rule();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(572); 
				_errHandler.sync(this);
				_alt1052 = getInterpreter().adaptivePredict(_input,41,_ctx);
			} while ( _alt1052!=2 && _alt1052!=-1 );
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
		rulesContext _localctx = new rulesContext(_ctx, 44);
		enterRule(_localctx, RULE_rules);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(578);
			_errHandler.sync(this);
			int _alt1069 = getInterpreter().adaptivePredict(_input,42,_ctx);
			while ( _alt1069!=2 && _alt1069!=-1 ) {
			    if ( _alt1069==1 ) {
			        {
			        {
			        setState(574); rule();
			        }
			        } 
			    }
				setState(580);
				_errHandler.sync(this);
				_alt1069 = getInterpreter().adaptivePredict(_input,42,_ctx);
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

	public static class ruleContext extends ParserRuleContext<Token> {
		public idContext name;;
		public argActionParametersContext parameters;;
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
		ruleContext _localctx = new ruleContext(_ctx, 46);
		enterRule(_localctx, RULE_rule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(583);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(581); match(DOC_COMMENT);
					}
					break;
			}
			setState(587);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(585); ruleModifiers();
					}
					break;
			}
			setState(589); _localctx.name = id();
			setState(593);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(591); _localctx.parameters = argActionParameters();
					}
					break;
			}
			setState(597);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					{
					setState(595); ruleReturns();
					}
					break;
			}
			setState(601);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(599); throwsSpec();
					}
					break;
			}
			setState(605);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					{
					setState(603); locals_();
					}
					break;
			}
			setState(607); rulePrequels();
			setState(609); match(COLON);
			setState(611); ruleBlock();
			setState(613); match(SEMI);
			setState(615); exceptionGroup();
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
		exceptionGroupContext _localctx = new exceptionGroupContext(_ctx, 48);
		enterRule(_localctx, RULE_exceptionGroup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(621);
			_errHandler.sync(this);
			int _alt1262 = getInterpreter().adaptivePredict(_input,49,_ctx);
			while ( _alt1262!=2 && _alt1262!=-1 ) {
			    if ( _alt1262==1 ) {
			        {
			        {
			        setState(617); exceptionHandler();
			        }
			        } 
			    }
				setState(623);
				_errHandler.sync(this);
				_alt1262 = getInterpreter().adaptivePredict(_input,49,_ctx);
			}
			setState(626);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
				case 1:
					{
					setState(624); finallyClause();
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
		exceptionHandlerContext _localctx = new exceptionHandlerContext(_ctx, 50);
		enterRule(_localctx, RULE_exceptionHandler);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(628); match(CATCH);
			setState(630); argActionBlock();
			setState(632); actionBlock();
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
		finallyClauseContext _localctx = new finallyClauseContext(_ctx, 52);
		enterRule(_localctx, RULE_finallyClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(634); match(FINALLY);
			setState(636); actionBlock();
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
		rulePrequelsContext _localctx = new rulePrequelsContext(_ctx, 54);
		enterRule(_localctx, RULE_rulePrequels);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(642);
			_errHandler.sync(this);
			int _alt1318 = getInterpreter().adaptivePredict(_input,51,_ctx);
			while ( _alt1318!=2 && _alt1318!=-1 ) {
			    if ( _alt1318==1 ) {
			        {
			        {
			        setState(638); rulePrequel();
			        }
			        } 
			    }
				setState(644);
				_errHandler.sync(this);
				_alt1318 = getInterpreter().adaptivePredict(_input,51,_ctx);
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
		rulePrequelContext _localctx = new rulePrequelContext(_ctx, 56);
		enterRule(_localctx, RULE_rulePrequel);
		try {
			setState(649);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(645); optionsSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(647); ruleAction();
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

	public static class ruleReturnsContext extends ParserRuleContext<Token> {
		public argActionParametersContext values;;
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
		ruleReturnsContext _localctx = new ruleReturnsContext(_ctx, 58);
		enterRule(_localctx, RULE_ruleReturns);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(651); match(RETURNS);
			setState(653); _localctx.values = argActionParameters();
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
		throwsSpecContext _localctx = new throwsSpecContext(_ctx, 60);
		enterRule(_localctx, RULE_throwsSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(655); match(THROWS);
			setState(657); qid();
			setState(665);
			_errHandler.sync(this);
			int _alt1403 = getInterpreter().adaptivePredict(_input,53,_ctx);
			while ( _alt1403!=2 && _alt1403!=-1 ) {
			    if ( _alt1403==1 ) {
			        {
			        {
			        setState(659); match(COMMA);
			        setState(661); qid();
			        }
			        } 
			    }
				setState(667);
				_errHandler.sync(this);
				_alt1403 = getInterpreter().adaptivePredict(_input,53,_ctx);
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

	public static class locals_Context extends ParserRuleContext<Token> {
		public argActionParametersContext values;;
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
		locals_Context _localctx = new locals_Context(_ctx, 62);
		enterRule(_localctx, RULE_locals_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(668); match(LOCALS);
			setState(670); _localctx.values = argActionParameters();
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
		ruleActionContext _localctx = new ruleActionContext(_ctx, 64);
		enterRule(_localctx, RULE_ruleAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(672); match(AT);
			setState(674); id();
			setState(676); actionBlock();
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
		ruleModifiersContext _localctx = new ruleModifiersContext(_ctx, 66);
		enterRule(_localctx, RULE_ruleModifiers);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(680); 
			_errHandler.sync(this);
			int _alt1472 = getInterpreter().adaptivePredict(_input,54,_ctx);
			do {
				switch ( _alt1472 ) {
					case 1:
						{
						{
						setState(678); ruleModifier();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(682); 
				_errHandler.sync(this);
				_alt1472 = getInterpreter().adaptivePredict(_input,54,_ctx);
			} while ( _alt1472!=2 && _alt1472!=-1 );
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
		ruleModifierContext _localctx = new ruleModifierContext(_ctx, 68);
		enterRule(_localctx, RULE_ruleModifier);
		try {
			setState(692);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(684); match(PUBLIC);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(686); match(PRIVATE);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(688); match(PROTECTED);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(690); match(FRAGMENT);
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
		ruleBlockContext _localctx = new ruleBlockContext(_ctx, 70);
		enterRule(_localctx, RULE_ruleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(694); ruleAltList();
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
		ruleAltListContext _localctx = new ruleAltListContext(_ctx, 72);
		enterRule(_localctx, RULE_ruleAltList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(696); labeledAlt();
			setState(704);
			_errHandler.sync(this);
			int _alt1553 = getInterpreter().adaptivePredict(_input,56,_ctx);
			while ( _alt1553!=2 && _alt1553!=-1 ) {
			    if ( _alt1553==1 ) {
			        {
			        {
			        setState(698); match(OR);
			        setState(700); labeledAlt();
			        }
			        } 
			    }
				setState(706);
				_errHandler.sync(this);
				_alt1553 = getInterpreter().adaptivePredict(_input,56,_ctx);
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
		labeledAltContext _localctx = new labeledAltContext(_ctx, 74);
		enterRule(_localctx, RULE_labeledAlt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(707); alternative();
			setState(713);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
				case 1:
					{
					setState(709); match(POUND);
					setState(711); id();
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
		altListContext _localctx = new altListContext(_ctx, 76);
		enterRule(_localctx, RULE_altList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(715); alternative();
			setState(723);
			_errHandler.sync(this);
			int _alt1589 = getInterpreter().adaptivePredict(_input,58,_ctx);
			while ( _alt1589!=2 && _alt1589!=-1 ) {
			    if ( _alt1589==1 ) {
			        {
			        {
			        setState(717); match(OR);
			        setState(719); alternative();
			        }
			        } 
			    }
				setState(725);
				_errHandler.sync(this);
				_alt1589 = getInterpreter().adaptivePredict(_input,58,_ctx);
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
		alternativeContext _localctx = new alternativeContext(_ctx, 78);
		enterRule(_localctx, RULE_alternative);
		try {
			setState(738);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(726); elements();
					setState(732);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
						case 1:
							{
							setState(728); rewrite();
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
					setState(734); rewrite();
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
			exitRule();
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
		elementsContext _localctx = new elementsContext(_ctx, 80);
		enterRule(_localctx, RULE_elements);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(742); 
			_errHandler.sync(this);
			int _alt1640 = getInterpreter().adaptivePredict(_input,61,_ctx);
			do {
				switch ( _alt1640 ) {
					case 1:
						{
						{
						setState(740); _localctx.e = element();
						_localctx.e_list.add(_localctx.e);
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(744); 
				_errHandler.sync(this);
				_alt1640 = getInterpreter().adaptivePredict(_input,61,_ctx);
			} while ( _alt1640!=2 && _alt1640!=-1 );
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
		elementContext _localctx = new elementContext(_ctx, 82);
		enterRule(_localctx, RULE_element);
		try {
			setState(778);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(746); labeledElement();
					setState(752);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
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
					setState(754); atom();
					setState(760);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
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
					setState(762); ebnf();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(764); actionBlock();
					setState(768);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
						case 1:
							{
							setState(766); match(QUESTION);
							}
							break;
					}
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(770); treeSpec();
					setState(776);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
						case 1:
							{
							setState(772); ebnfSuffix();
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
			exitRule();
		}
		return _localctx;
	}

	public static class labeledElementContext extends ParserRuleContext<Token> {
		public idContext label;;
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
		labeledElementContext _localctx = new labeledElementContext(_ctx, 84);
		enterRule(_localctx, RULE_labeledElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(780); _localctx.label = id();
			setState(786);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
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
			setState(798);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
				case 1:
					{
					setState(788); atom();
					}
					break;

				case 2:
					{
					setState(790); block();
					setState(796);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
						case 1:
							{
							setState(792); _localctx.op = match(ROOT);
							}
							break;

						case 2:
							{
							setState(794); _localctx.op = match(BANG);
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
		treeSpecContext _localctx = new treeSpecContext(_ctx, 86);
		enterRule(_localctx, RULE_treeSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(800); _localctx.begin = match(TREE_BEGIN);
			setState(802); _localctx.root = element();
			setState(806); 
			_errHandler.sync(this);
			int _alt1899 = getInterpreter().adaptivePredict(_input,70,_ctx);
			do {
				switch ( _alt1899 ) {
					case 1:
						{
						{
						setState(804); _localctx.kids = element();
						_localctx.kids_list.add(_localctx.kids);
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(808); 
				_errHandler.sync(this);
				_alt1899 = getInterpreter().adaptivePredict(_input,70,_ctx);
			} while ( _alt1899!=2 && _alt1899!=-1 );
			setState(810); match(RPAREN);
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
		ebnfContext _localctx = new ebnfContext(_ctx, 88);
		enterRule(_localctx, RULE_ebnf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(812); block();
			setState(818);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
				case 1:
					{
					setState(814); blockSuffix();
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
		blockSuffixContext _localctx = new blockSuffixContext(_ctx, 90);
		enterRule(_localctx, RULE_blockSuffix);
		try {
			setState(826);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(820); ebnfSuffix();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(822); match(ROOT);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(824); match(BANG);
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
		ebnfSuffixContext _localctx = new ebnfSuffixContext(_ctx, 92);
		enterRule(_localctx, RULE_ebnfSuffix);
		try {
			setState(834);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(828); match(QUESTION);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(830); match(STAR);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(832); match(PLUS);
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
		atomContext _localctx = new atomContext(_ctx, 94);
		enterRule(_localctx, RULE_atom);
		int _la;
		try {
			setState(868);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(836); range();
					setState(840);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
						case 1:
							{
							setState(838);
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
					setState(842); terminal();
					setState(846);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
						case 1:
							{
							setState(844);
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
					setState(848); ruleref();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(850); notSet();
					setState(854);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
						case 1:
							{
							setState(852);
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
					setState(856); match(DOT);
					setState(860);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
						case 1:
							{
							setState(858); elementOptions();
							}
							break;
					}
					setState(866);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
						case 1:
							{
							setState(862); _localctx.astop = match(ROOT);
							}
							break;

						case 2:
							{
							setState(864); _localctx.astop = match(BANG);
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
		notSetContext _localctx = new notSetContext(_ctx, 96);
		enterRule(_localctx, RULE_notSet);
		try {
			setState(878);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(870); match(NOT);
					setState(872); setElement();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(874); match(NOT);
					setState(876); blockSet();
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
		blockSetContext _localctx = new blockSetContext(_ctx, 98);
		enterRule(_localctx, RULE_blockSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(880); match(LPAREN);
			setState(882); setElement();
			setState(890);
			_errHandler.sync(this);
			int _alt2147 = getInterpreter().adaptivePredict(_input,81,_ctx);
			while ( _alt2147!=2 && _alt2147!=-1 ) {
			    if ( _alt2147==1 ) {
			        {
			        {
			        setState(884); match(OR);
			        setState(886); setElement();
			        }
			        } 
			    }
				setState(892);
				_errHandler.sync(this);
				_alt2147 = getInterpreter().adaptivePredict(_input,81,_ctx);
			}
			setState(893); match(RPAREN);
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
		setElementContext _localctx = new setElementContext(_ctx, 100);
		enterRule(_localctx, RULE_setElement);
		try {
			setState(901);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(895); match(TOKEN_REF);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(897); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(899); range();
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
		blockContext _localctx = new blockContext(_ctx, 102);
		enterRule(_localctx, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(903); match(LPAREN);
			setState(918);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
				case 1:
					{
					setState(907);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
						case 1:
							{
							setState(905); optionsSpec();
							}
							break;
					}
					setState(913);
					_errHandler.sync(this);
					int _alt2221 = getInterpreter().adaptivePredict(_input,84,_ctx);
					while ( _alt2221!=2 && _alt2221!=-1 ) {
					    if ( _alt2221==1 ) {
					        {
					        {
					        setState(909); _localctx.ra = ruleAction();
					        _localctx.ra_list.add(_localctx.ra);
					        }
					        } 
					    }
						setState(915);
						_errHandler.sync(this);
						_alt2221 = getInterpreter().adaptivePredict(_input,84,_ctx);
					}
					setState(916); match(COLON);
					}
					break;
			}
			setState(920); altList();
			setState(922); match(RPAREN);
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
		rulerefContext _localctx = new rulerefContext(_ctx, 104);
		enterRule(_localctx, RULE_ruleref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(924); match(RULE_REF);
			setState(928);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
				case 1:
					{
					setState(926); argActionBlock();
					}
					break;
			}
			setState(938);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
				case 1:
					{
					setState(934);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
						case 1:
							{
							setState(930); _localctx.op = match(ROOT);
							}
							break;

						case 2:
							{
							setState(932); _localctx.op = match(BANG);
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
			exitRule();
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
		rangeContext _localctx = new rangeContext(_ctx, 106);
		enterRule(_localctx, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(940); match(STRING_LITERAL);
			setState(942); match(RANGE);
			setState(944); match(STRING_LITERAL);
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
		terminalContext _localctx = new terminalContext(_ctx, 108);
		enterRule(_localctx, RULE_terminal);
		try {
			setState(958);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(946); match(TOKEN_REF);
					setState(950);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
						case 1:
							{
							setState(948); elementOptions();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(952); match(STRING_LITERAL);
					setState(956);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
						case 1:
							{
							setState(954); elementOptions();
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
		elementOptionsContext _localctx = new elementOptionsContext(_ctx, 110);
		enterRule(_localctx, RULE_elementOptions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(960); match(LT);
			setState(962); elementOption();
			setState(970);
			_errHandler.sync(this);
			int _alt2372 = getInterpreter().adaptivePredict(_input,92,_ctx);
			while ( _alt2372!=2 && _alt2372!=-1 ) {
			    if ( _alt2372==1 ) {
			        {
			        {
			        setState(964); match(COMMA);
			        setState(966); elementOption();
			        }
			        } 
			    }
				setState(972);
				_errHandler.sync(this);
				_alt2372 = getInterpreter().adaptivePredict(_input,92,_ctx);
			}
			setState(973); match(GT);
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
		elementOptionContext _localctx = new elementOptionContext(_ctx, 112);
		enterRule(_localctx, RULE_elementOption);
		try {
			setState(987);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(975); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(977); id();
					setState(979); match(ASSIGN);
					setState(985);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
						case 1:
							{
							setState(981); qid();
							}
							break;

						case 2:
							{
							setState(983); match(STRING_LITERAL);
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
		rewriteContext _localctx = new rewriteContext(_ctx, 114);
		enterRule(_localctx, RULE_rewrite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(993);
			_errHandler.sync(this);
			int _alt2419 = getInterpreter().adaptivePredict(_input,95,_ctx);
			while ( _alt2419!=2 && _alt2419!=-1 ) {
			    if ( _alt2419==1 ) {
			        {
			        {
			        setState(989); predicatedRewrite();
			        }
			        } 
			    }
				setState(995);
				_errHandler.sync(this);
				_alt2419 = getInterpreter().adaptivePredict(_input,95,_ctx);
			}
			setState(996); nakedRewrite();
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
		predicatedRewriteContext _localctx = new predicatedRewriteContext(_ctx, 116);
		enterRule(_localctx, RULE_predicatedRewrite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(998); match(RARROW);
			setState(1000); match(SEMPRED);
			setState(1002); rewriteAlt();
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
		nakedRewriteContext _localctx = new nakedRewriteContext(_ctx, 118);
		enterRule(_localctx, RULE_nakedRewrite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1004); match(RARROW);
			setState(1006); rewriteAlt();
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
		rewriteAltContext _localctx = new rewriteAltContext(_ctx, 120);
		enterRule(_localctx, RULE_rewriteAlt);
		try {
			setState(1016);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1008); rewriteTreeAlt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1010); rewriteTemplate();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1012); match(ETC);
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
		rewriteTreeAltContext _localctx = new rewriteTreeAltContext(_ctx, 122);
		enterRule(_localctx, RULE_rewriteTreeAlt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1020); 
			_errHandler.sync(this);
			int _alt2508 = getInterpreter().adaptivePredict(_input,97,_ctx);
			do {
				switch ( _alt2508 ) {
					case 1:
						{
						{
						setState(1018); rewriteTreeElement();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(1022); 
				_errHandler.sync(this);
				_alt2508 = getInterpreter().adaptivePredict(_input,97,_ctx);
			} while ( _alt2508!=2 && _alt2508!=-1 );
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
		rewriteTreeElementContext _localctx = new rewriteTreeElementContext(_ctx, 124);
		enterRule(_localctx, RULE_rewriteTreeElement);
		try {
			setState(1040);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1024); rewriteTreeAtom();
					setState(1028);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
						case 1:
							{
							setState(1026); ebnfSuffix();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1030); rewriteTree();
					setState(1036);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
						case 1:
							{
							setState(1032); ebnfSuffix();
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
					setState(1038); rewriteTreeEbnf();
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
		rewriteTreeAtomContext _localctx = new rewriteTreeAtomContext(_ctx, 126);
		enterRule(_localctx, RULE_rewriteTreeAtom);
		try {
			setState(1066);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1042); match(TOKEN_REF);
					setState(1046);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
						case 1:
							{
							setState(1044); elementOptions();
							}
							break;
					}
					setState(1050);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
						case 1:
							{
							setState(1048); argActionBlock();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1052); match(RULE_REF);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1054); match(STRING_LITERAL);
					setState(1058);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
						case 1:
							{
							setState(1056); elementOptions();
							}
							break;
					}
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(1060); match(DOLLAR);
					setState(1062); id();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(1064); actionBlock();
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
		rewriteTreeEbnfContext _localctx = new rewriteTreeEbnfContext(_ctx, 128);
		enterRule(_localctx, RULE_rewriteTreeEbnf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1068); _localctx.lp = match(LPAREN);
			setState(1070); rewriteTreeAlt();
			setState(1072); match(RPAREN);
			setState(1074); rewriteEbnfSuffix();
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
		rewriteEbnfSuffixContext _localctx = new rewriteEbnfSuffixContext(_ctx, 130);
		enterRule(_localctx, RULE_rewriteEbnfSuffix);
		try {
			setState(1080);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1076); match(QUESTION);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1078); match(STAR);
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
		rewriteTreeContext _localctx = new rewriteTreeContext(_ctx, 132);
		enterRule(_localctx, RULE_rewriteTree);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1082); match(TREE_BEGIN);
			setState(1084); rewriteTreeAtom();
			setState(1090);
			_errHandler.sync(this);
			int _alt2658 = getInterpreter().adaptivePredict(_input,106,_ctx);
			while ( _alt2658!=2 && _alt2658!=-1 ) {
			    if ( _alt2658==1 ) {
			        {
			        {
			        setState(1086); rewriteTreeElement();
			        }
			        } 
			    }
				setState(1092);
				_errHandler.sync(this);
				_alt2658 = getInterpreter().adaptivePredict(_input,106,_ctx);
			}
			setState(1093); match(RPAREN);
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
		rewriteTemplateContext _localctx = new rewriteTemplateContext(_ctx, 134);
		enterRule(_localctx, RULE_rewriteTemplate);
		try {
			setState(1115);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1095); match(TEMPLATE);
					setState(1097); match(LPAREN);
					setState(1099); rewriteTemplateArgs();
					setState(1101); match(RPAREN);
					setState(1107);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
						case 1:
							{
							setState(1103); _localctx.str = match(DOUBLE_QUOTE_STRING_LITERAL);
							}
							break;

						case 2:
							{
							setState(1105); _localctx.str = match(DOUBLE_ANGLE_STRING_LITERAL);
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1109); rewriteTemplateRef();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1111); rewriteIndirectTemplateHead();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(1113); actionBlock();
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
		rewriteTemplateRefContext _localctx = new rewriteTemplateRefContext(_ctx, 136);
		enterRule(_localctx, RULE_rewriteTemplateRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1117); id();
			setState(1119); match(LPAREN);
			setState(1121); rewriteTemplateArgs();
			setState(1123); match(RPAREN);
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
		rewriteIndirectTemplateHeadContext _localctx = new rewriteIndirectTemplateHeadContext(_ctx, 138);
		enterRule(_localctx, RULE_rewriteIndirectTemplateHead);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1125); _localctx.lp = match(LPAREN);
			setState(1127); actionBlock();
			setState(1129); match(RPAREN);
			setState(1131); match(LPAREN);
			setState(1133); rewriteTemplateArgs();
			setState(1135); match(RPAREN);
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
		rewriteTemplateArgsContext _localctx = new rewriteTemplateArgsContext(_ctx, 140);
		enterRule(_localctx, RULE_rewriteTemplateArgs);
		try {
			setState(1150);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1137); rewriteTemplateArg();
					setState(1145);
					_errHandler.sync(this);
					int _alt2772 = getInterpreter().adaptivePredict(_input,109,_ctx);
					while ( _alt2772!=2 && _alt2772!=-1 ) {
					    if ( _alt2772==1 ) {
					        {
					        {
					        setState(1139); match(COMMA);
					        setState(1141); rewriteTemplateArg();
					        }
					        } 
					    }
						setState(1147);
						_errHandler.sync(this);
						_alt2772 = getInterpreter().adaptivePredict(_input,109,_ctx);
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
		rewriteTemplateArgContext _localctx = new rewriteTemplateArgContext(_ctx, 142);
		enterRule(_localctx, RULE_rewriteTemplateArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1152); id();
			setState(1154); match(ASSIGN);
			setState(1156); actionBlock();
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
		idContext _localctx = new idContext(_ctx, 144);
		enterRule(_localctx, RULE_id);
		try {
			setState(1164);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,111,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1158); match(RULE_REF);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1160); match(TOKEN_REF);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1162); match(TEMPLATE);
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
		qidContext _localctx = new qidContext(_ctx, 146);
		enterRule(_localctx, RULE_qid);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1166); id();
			setState(1174);
			_errHandler.sync(this);
			int _alt2843 = getInterpreter().adaptivePredict(_input,112,_ctx);
			while ( _alt2843!=2 && _alt2843!=-1 ) {
			    if ( _alt2843==1 ) {
			        {
			        {
			        setState(1168); match(DOT);
			        setState(1170); id();
			        }
			        } 
			    }
				setState(1176);
				_errHandler.sync(this);
				_alt2843 = getInterpreter().adaptivePredict(_input,112,_ctx);
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
		alternativeEntryContext _localctx = new alternativeEntryContext(_ctx, 148);
		enterRule(_localctx, RULE_alternativeEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1177); alternative();
			setState(1179); match(EOF);
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
		elementEntryContext _localctx = new elementEntryContext(_ctx, 150);
		enterRule(_localctx, RULE_elementEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1181); element();
			setState(1183); match(EOF);
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
		ruleEntryContext _localctx = new ruleEntryContext(_ctx, 152);
		enterRule(_localctx, RULE_ruleEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1185); rule();
			setState(1187); match(EOF);
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
		blockEntryContext _localctx = new blockEntryContext(_ctx, 154);
		enterRule(_localctx, RULE_blockEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1189); block();
			setState(1191); match(EOF);
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
		"\1\u0080\u04aa\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6"+
		"\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16\7\16\2"+
		"\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25\7\25\2"+
		"\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34\7\34\2"+
		"\35\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7%\2&"+
		"\7&\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2.\7.\2/\7/\2\60\7\60"+
		"\2\61\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65\2\66\7\66\2\67\7\67"+
		"\28\78\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2@\7@\2A\7A\2B\7B\2C"+
		"\7C\2D\7D\2E\7E\2F\7F\2G\7G\2H\7H\2I\7I\2J\7J\2K\7K\2L\7L\2M\7M\1\0\0"+
		"\3\0\b\0\1\0\0\1\0\0\1\0\0\1\0\0\5\0\b\0\n\0\f\0\u00ac\t\0\1\0\0\1\0\0"+
		"\5\0\b\0\n\0\f\0\u00b5\t\0\1\0\1\0\1\1\0\1\1\0\1\1\0\1\1\0\1\1\0\1\1\0"+
		"\1\1\0\3\1\b\1\1\2\0\1\2\0\1\2\0\1\2\0\3\2\b\2\1\3\0\1\3\0\1\3\1\3\5\3"+
		"\b\3\n\3\f\3\u00dc\t\3\1\3\1\3\1\4\0\1\4\0\1\4\1\4\1\5\0\1\5\0\1\5\0\1"+
		"\5\0\3\5\b\5\1\6\0\1\6\0\1\6\0\1\6\0\5\6\b\6\n\6\f\6\u00fb\t\6\1\6\1\6"+
		"\1\7\0\1\7\0\1\7\1\7\1\7\0\3\7\b\7\1\b\0\1\b\0\4\b\b\b\13\b\f\b\u010e"+
		"\1\b\1\b\1\t\0\1\t\0\1\t\0\1\t\0\3\t\b\t\1\t\1\t\1\t\0\3\t\b\t\1\n\0\1"+
		"\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0"+
		"\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\5\n"+
		"\b\n\n\n\f\n\u0156\t\n\1\n\1\n\1\13\0\1\13\0\5\13\b\13\n\13\f\13\u0161"+
		"\t\13\1\13\0\1\13\0\5\13\b\13\n\13\f\13\u016a\t\13\1\13\1\13\1\f\0\1\f"+
		"\0\5\f\b\f\n\f\f\f\u0175\t\f\1\f\0\1\f\0\5\f\b\f\n\f\f\f\u017e\t\f\1\f"+
		"\0\1\f\0\5\f\b\f\n\f\f\f\u0187\t\f\3\f\b\f\1\f\0\1\f\0\5\f\b\f\n\f\f\f"+
		"\u0192\t\f\1\f\0\1\f\0\5\f\b\f\n\f\f\f\u019b\t\f\3\f\b\f\1\f\0\1\f\0\5"+
		"\f\b\f\n\f\f\f\u01a6\t\f\1\f\1\f\1\r\0\1\r\0\5\r\b\r\n\r\f\r\u01b1\t\r"+
		"\1\r\1\r\1\16\0\1\16\0\5\16\b\16\n\16\f\16\u01bc\t\16\1\16\0\1\16\0\5"+
		"\16\b\16\n\16\f\16\u01c5\t\16\1\16\0\1\16\0\5\16\b\16\n\16\f\16\u01ce"+
		"\t\16\1\16\0\1\16\0\5\16\b\16\n\16\f\16\u01d7\t\16\5\16\b\16\n\16\f\16"+
		"\u01dc\t\16\3\16\b\16\1\16\1\16\1\17\0\3\17\b\17\1\17\0\5\17\b\17\n\17"+
		"\f\17\u01eb\t\17\1\17\1\17\1\20\0\1\20\0\5\20\b\20\n\20\f\20\u01f6\t\20"+
		"\1\20\0\5\20\b\20\n\20\f\20\u01fd\t\20\1\21\0\1\21\0\1\21\0\3\21\b\21"+
		"\1\21\0\1\21\0\1\21\0\3\21\b\21\1\21\0\3\21\b\21\1\22\0\1\22\0\1\22\0"+
		"\1\22\0\1\22\0\3\22\b\22\1\23\0\1\23\0\1\23\1\23\3\23\b\23\1\23\0\1\23"+
		"\1\23\1\24\0\1\24\0\1\24\0\3\24\b\24\1\25\0\1\25\0\1\25\0\1\25\0\4\25"+
		"\b\25\13\25\f\25\u023c\1\26\0\5\26\b\26\n\26\f\26\u0244\t\26\1\27\0\3"+
		"\27\b\27\1\27\0\3\27\b\27\1\27\0\1\27\0\3\27\b\27\1\27\0\3\27\b\27\1\27"+
		"\0\3\27\b\27\1\27\0\3\27\b\27\1\27\0\1\27\0\1\27\0\1\27\0\1\27\1\27\1"+
		"\30\0\5\30\b\30\n\30\f\30\u026f\t\30\1\30\0\3\30\b\30\1\31\0\1\31\0\1"+
		"\31\1\31\1\32\0\1\32\1\32\1\33\0\5\33\b\33\n\33\f\33\u0284\t\33\1\34\0"+
		"\1\34\0\3\34\b\34\1\35\0\1\35\1\35\1\36\0\1\36\0\1\36\0\1\36\0\5\36\b"+
		"\36\n\36\f\36\u029b\t\36\1\37\0\1\37\1\37\1 \0\1 \0\1 \1 \1!\0\4!\b!\13"+
		"!\f!\u02aa\1\"\0\1\"\0\1\"\0\1\"\0\3\"\b\"\1#\1#\1$\0\1$\0\1$\0\5$\b$"+
		"\n$\f$\u02c2\t$\1%\0\1%\0\1%\0\3%\b%\1&\0\1&\0\1&\0\5&\b&\n&\f&\u02d5"+
		"\t&\1\'\0\1\'\0\1\'\0\3\'\b\'\1\'\0\1\'\0\3\'\b\'\1(\0\4(\b(\13(\f(\u02e8"+
		"\1)\0\1)\0\1)\0\3)\b)\1)\0\1)\0\1)\0\3)\b)\1)\0\1)\0\1)\0\3)\b)\1)\0\1"+
		")\0\1)\0\3)\b)\3)\b)\1*\0\1*\0\1*\0\3*\b*\1*\0\1*\0\1*\0\1*\0\3*\b*\3"+
		"*\b*\1+\0\1+\0\1+\0\4+\b+\13+\f+\u0328\1+\1+\1,\0\1,\0\1,\0\3,\b,\1-\0"+
		"\1-\0\1-\0\3-\b-\1.\0\1.\0\1.\0\3.\b.\1/\0\1/\0\3/\b/\1/\0\1/\0\3/\b/"+
		"\1/\0\1/\0\1/\0\3/\b/\1/\0\1/\0\3/\b/\1/\0\1/\0\3/\b/\3/\b/\1\60\0\1\60"+
		"\0\1\60\0\1\60\0\3\60\b\60\1\61\0\1\61\0\1\61\0\1\61\0\5\61\b\61\n\61"+
		"\f\61\u037c\t\61\1\61\1\61\1\62\0\1\62\0\1\62\0\3\62\b\62\1\63\0\1\63"+
		"\0\3\63\b\63\1\63\0\5\63\b\63\n\63\f\63\u0393\t\63\1\63\0\3\63\b\63\1"+
		"\63\0\1\63\1\63\1\64\0\1\64\0\3\64\b\64\1\64\0\1\64\0\3\64\b\64\1\64\0"+
		"\3\64\b\64\1\65\0\1\65\0\1\65\1\65\1\66\0\1\66\0\3\66\b\66\1\66\0\1\66"+
		"\0\3\66\b\66\3\66\b\66\1\67\0\1\67\0\1\67\0\1\67\0\5\67\b\67\n\67\f\67"+
		"\u03cc\t\67\1\67\1\67\18\0\18\0\18\0\18\0\18\0\38\b8\38\b8\19\0\59\b9"+
		"\n9\f9\u03e3\t9\19\19\1:\0\1:\0\1:\1:\1;\0\1;\1;\1<\0\1<\0\1<\0\1<\0\3"+
		"<\b<\1=\0\4=\b=\13=\f=\u03fe\1>\0\1>\0\3>\b>\1>\0\1>\0\1>\0\3>\b>\1>\0"+
		"\3>\b>\1?\0\1?\0\3?\b?\1?\0\3?\b?\1?\0\1?\0\1?\0\3?\b?\1?\0\1?\0\1?\0"+
		"\3?\b?\1@\0\1@\0\1@\0\1@\1@\1A\0\1A\0\3A\bA\1B\0\1B\0\1B\0\5B\bB\nB\f"+
		"B\u0444\tB\1B\1B\1C\0\1C\0\1C\0\1C\0\1C\0\1C\0\3C\bC\1C\0\1C\0\1C\0\3"+
		"C\bC\1D\0\1D\0\1D\0\1D\1D\1E\0\1E\0\1E\0\1E\0\1E\0\1E\1E\1F\0\1F\0\1F"+
		"\0\5F\bF\nF\fF\u047b\tF\1F\0\3F\bF\1G\0\1G\0\1G\1G\1H\0\1H\0\1H\0\3H\b"+
		"H\1I\0\1I\0\1I\0\5I\bI\nI\fI\u0498\tI\1J\0\1J\1J\1K\0\1K\1K\1L\0\1L\1"+
		"L\1M\0\1M\1M\1MN\0\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086"+
		"\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\0\4\1>I\2"+
		"((--\2((--\2((--\u0476\0\u009e\1\0\0\0\1\u04a9\5\uffff\0\0\2\u00c6\1\0"+
		"\0\0\3\u00a2\1\0\0\0\4\u00d0\1\0\0\0\5\u00a9\1\0\0\0\6\u00d2\1\0\0\0\7"+
		"\u00d1\1\0\0\0\7\u028a\1\0\0\0\7\u038c\1\0\0\0\b\u00df\1\0\0\0\t\u00d6"+
		"\1\0\0\0\n\u00ed\1\0\0\0\13\u00e4\1\0\0\0\f\u00ef\1\0\0\0\r\u00d1\1\0"+
		"\0\0\16\u0106\1\0\0\0\17\u00f9\1\0\0\0\17\u00f8\1\0\0\0\20\u0108\1\0\0"+
		"\0\21\u00d1\1\0\0\0\22\u0120\1\0\0\0\23\u010d\1\0\0\0\24\u0122\1\0\0\0"+
		"\25\u0153\1\0\0\0\25\u0229\1\0\0\0\25\u0279\1\0\0\0\25\u027d\1\0\0\0\25"+
		"\u02a5\1\0\0\0\25\u0300\1\0\0\0\25\u042b\1\0\0\0\25\u045c\1\0\0\0\25\u0469"+
		"\1\0\0\0\25\u0485\1\0\0\0\26\u0159\1\0\0\0\27\u0153\1\0\0\0\30\u016d\1"+
		"\0\0\0\31\u0153\1\0\0\0\32\u01a9\1\0\0\0\33\u0278\1\0\0\0\33\u03a1\1\0"+
		"\0\0\33\u041b\1\0\0\0\34\u01b4\1\0\0\0\35\u0252\1\0\0\0\35\u028e\1\0\0"+
		"\0\35\u029f\1\0\0\0\36\u01e3\1\0\0\0\37\u01c3\1\0\0\0\37\u01d5\1\0\0\0"+
		" \u01ee\1\0\0\0!\u01e4\1\0\0\0!\u0205\1\0\0\0!\u020d\1\0\0\0\"\u0210\1"+
		"\0\0\0#\u01fb\1\0\0\0#\u01fa\1\0\0\0$\u021c\1\0\0\0%\u015e\1\0\0\0%\u0167"+
		"\1\0\0\0%\u0172\1\0\0\0%\u017b\1\0\0\0%\u0184\1\0\0\0%\u018f\1\0\0\0%"+
		"\u0198\1\0\0\0%\u01a3\1\0\0\0%\u01b9\1\0\0\0%\u01c2\1\0\0\0%\u01cb\1\0"+
		"\0\0%\u01d4\1\0\0\0%\u01e8\1\0\0\0%\u01f3\1\0\0\0&\u021e\1\0\0\0\'\u00d1"+
		"\1\0\0\0(\u0230\1\0\0\0)\u0222\1\0\0\0*\u0232\1\0\0\0+\u00b2\1\0\0\0,"+
		"\u0242\1\0\0\0-\u00b3\1\0\0\0.\u0247\1\0\0\0/\u023b\1\0\0\0/\u0241\1\0"+
		"\0\0/\u04a3\1\0\0\0\60\u026d\1\0\0\0\61\u0268\1\0\0\0\62\u0274\1\0\0\0"+
		"\63\u026c\1\0\0\0\64\u027a\1\0\0\0\65\u0273\1\0\0\0\66\u0282\1\0\0\0\67"+
		"\u0261\1\0\0\08\u0289\1\0\0\09\u0281\1\0\0\0:\u028b\1\0\0\0;\u0256\1\0"+
		"\0\0<\u028f\1\0\0\0=\u025a\1\0\0\0>\u029c\1\0\0\0?\u025e\1\0\0\0@\u02a0"+
		"\1\0\0\0A\u028a\1\0\0\0A\u0390\1\0\0\0B\u02a8\1\0\0\0C\u024c\1\0\0\0D"+
		"\u02b4\1\0\0\0E\u02a9\1\0\0\0F\u02b6\1\0\0\0G\u0265\1\0\0\0H\u02b8\1\0"+
		"\0\0I\u02b7\1\0\0\0J\u02c3\1\0\0\0K\u02c0\1\0\0\0K\u02bf\1\0\0\0L\u02cb"+
		"\1\0\0\0M\u039a\1\0\0\0N\u02e2\1\0\0\0O\u02c9\1\0\0\0O\u02d3\1\0\0\0O"+
		"\u02d2\1\0\0\0O\u049b\1\0\0\0P\u02e6\1\0\0\0Q\u02dc\1\0\0\0R\u030a\1\0"+
		"\0\0S\u02e7\1\0\0\0S\u0326\1\0\0\0S\u0327\1\0\0\0S\u049f\1\0\0\0T\u030c"+
		"\1\0\0\0U\u02f0\1\0\0\0V\u0320\1\0\0\0W\u0308\1\0\0\0X\u032c\1\0\0\0Y"+
		"\u030b\1\0\0\0Z\u033a\1\0\0\0[\u0333\1\0\0\0\\\u0342\1\0\0\0]\u02f1\1"+
		"\0\0\0]\u02f9\1\0\0\0]\u0309\1\0\0\0]\u033b\1\0\0\0]\u0405\1\0\0\0]\u040d"+
		"\1\0\0\0^\u0364\1\0\0\0_\u02f8\1\0\0\0_\u031f\1\0\0\0`\u036e\1\0\0\0a"+
		"\u0356\1\0\0\0b\u0370\1\0\0\0c\u036f\1\0\0\0d\u0385\1\0\0\0e\u036f\1\0"+
		"\0\0e\u037a\1\0\0\0e\u0379\1\0\0\0f\u0387\1\0\0\0g\u031c\1\0\0\0g\u0332"+
		"\1\0\0\0g\u04a7\1\0\0\0h\u039c\1\0\0\0i\u0365\1\0\0\0j\u03ac\1\0\0\0k"+
		"\u0348\1\0\0\0k\u0386\1\0\0\0l\u03be\1\0\0\0m\u034e\1\0\0\0n\u03c0\1\0"+
		"\0\0o\u035d\1\0\0\0o\u03b7\1\0\0\0o\u03bd\1\0\0\0o\u0417\1\0\0\0o\u0423"+
		"\1\0\0\0p\u03db\1\0\0\0q\u03ca\1\0\0\0q\u03c9\1\0\0\0r\u03e1\1\0\0\0s"+
		"\u02dd\1\0\0\0s\u02e3\1\0\0\0t\u03e6\1\0\0\0u\u03e0\1\0\0\0v\u03ec\1\0"+
		"\0\0w\u03e5\1\0\0\0x\u03f8\1\0\0\0y\u03eb\1\0\0\0y\u03ef\1\0\0\0z\u03fc"+
		"\1\0\0\0{\u03f9\1\0\0\0{\u0430\1\0\0\0|\u0410\1\0\0\0}\u03fd\1\0\0\0}"+
		"\u0441\1\0\0\0~\u042a\1\0\0\0\177\u0404\1\0\0\0\177\u0442\1\0\0\0\u0080"+
		"\u042c\1\0\0\0\u0081\u0411\1\0\0\0\u0082\u0438\1\0\0\0\u0083\u0433\1\0"+
		"\0\0\u0084\u043a\1\0\0\0\u0085\u040c\1\0\0\0\u0086\u045b\1\0\0\0\u0087"+
		"\u03f9\1\0\0\0\u0088\u045d\1\0\0\0\u0089\u045c\1\0\0\0\u008a\u0465\1\0"+
		"\0\0\u008b\u045c\1\0\0\0\u008c\u047e\1\0\0\0\u008d\u044d\1\0\0\0\u008d"+
		"\u0463\1\0\0\0\u008d\u046f\1\0\0\0\u008e\u0480\1\0\0\0\u008f\u0479\1\0"+
		"\0\0\u008f\u0478\1\0\0\0\u0090\u048c\1\0\0\0\u0091\u00a4\1\0\0\0\u0091"+
		"\u00e1\1\0\0\0\u0091\u0100\1\0\0\0\u0091\u0103\1\0\0\0\u0091\u0107\1\0"+
		"\0\0\u0091\u011a\1\0\0\0\u0091\u0228\1\0\0\0\u0091\u0231\1\0\0\0\u0091"+
		"\u0236\1\0\0\0\u0091\u0251\1\0\0\0\u0091\u02a4\1\0\0\0\u0091\u02ca\1\0"+
		"\0\0\u0091\u0312\1\0\0\0\u0091\u03d3\1\0\0\0\u0091\u042b\1\0\0\0\u0091"+
		"\u045f\1\0\0\0\u0091\u0482\1\0\0\0\u0091\u0496\1\0\0\0\u0091\u0495\1\0"+
		"\0\0\u0092\u048e\1\0\0\0\u0093\u00ee\1\0\0\0\u0093\u0299\1\0\0\0\u0093"+
		"\u0298\1\0\0\0\u0093\u03dc\1\0\0\0\u0093\u03da\1\0\0\0\u0094\u0499\1\0"+
		"\0\0\u0095\u04a9\5\uffff\0\0\u0096\u049d\1\0\0\0\u0097\u04a9\5\uffff\0"+
		"\0\u0098\u04a1\1\0\0\0\u0099\u04a9\5\uffff\0\0\u009a\u04a5\1\0\0\0\u009b"+
		"\u04a9\5\uffff\0\0\u009c\u009f\5\3\0\0\u009e\u009c\1\0\0\0\u009e\u009f"+
		"\1\0\0\0\u009f\u00a0\1\0\0\0\u00a0\u00a2\3\2\1\0\u00a2\u00a4\3\u0090H"+
		"\0\u00a4\u00aa\5 \0\0\u00a6\u00a9\3\4\2\0\u00a8\u00a6\1\0\0\0\u00a9\u00ac"+
		"\1\0\0\0\u00aa\u00a8\1\0\0\0\u00aa\u00ab\1\0\0\0\u00ab\u00ad\1\0\0\0\u00ac"+
		"\u00aa\1\0\0\0\u00ad\u00b3\3,\26\0\u00af\u00b2\3*\25\0\u00b1\u00af\1\0"+
		"\0\0\u00b2\u00b5\1\0\0\0\u00b3\u00b1\1\0\0\0\u00b3\u00b4\1\0\0\0\u00b4"+
		"\u00b6\1\0\0\0\u00b5\u00b3\1\0\0\0\u00b6\u00b7\5\uffff\0\0\u00b7\1\1\0"+
		"\0\0\u00b8\u00ba\5\17\0\0\u00ba\u00c7\5\22\0\0\u00bc\u00be\5\20\0\0\u00be"+
		"\u00c7\5\22\0\0\u00c0\u00c2\5\21\0\0\u00c2\u00c7\5\22\0\0\u00c4\u00c7"+
		"\5\22\0\0\u00c6\u00b8\1\0\0\0\u00c6\u00bc\1\0\0\0\u00c6\u00c0\1\0\0\0"+
		"\u00c6\u00c4\1\0\0\0\u00c7\3\1\0\0\0\u00c8\u00d1\3\6\3\0\u00ca\u00d1\3"+
		"\f\6\0\u00cc\u00d1\3\20\b\0\u00ce\u00d1\3&\23\0\u00d0\u00c8\1\0\0\0\u00d0"+
		"\u00ca\1\0\0\0\u00d0\u00cc\1\0\0\0\u00d0\u00ce\1\0\0\0\u00d1\5\1\0\0\0"+
		"\u00d2\u00da\5\n\0\0\u00d4\u00d6\3\b\4\0\u00d6\u00d7\5 \0\0\u00d7\u00d9"+
		"\1\0\0\0\u00d8\u00d4\1\0\0\0\u00d9\u00dc\1\0\0\0\u00da\u00d8\1\0\0\0\u00da"+
		"\u00db\1\0\0\0\u00db\u00dd\1\0\0\0\u00dc\u00da\1\0\0\0\u00dd\u00de\5\67"+
		"\0\0\u00de\7\1\0\0\0\u00df\u00e1\3\u0090H\0\u00e1\u00e3\5&\0\0\u00e3\u00e4"+
		"\3\n\5\0\u00e4\t\1\0\0\0\u00e5\u00ee\3\u0092I\0\u00e7\u00ee\5;\0\0\u00e9"+
		"\u00ee\5:\0\0\u00eb\u00ee\5)\0\0\u00ed\u00e5\1\0\0\0\u00ed\u00e7\1\0\0"+
		"\0\u00ed\u00e9\1\0\0\0\u00ed\u00eb\1\0\0\0\u00ee\13\1\0\0\0\u00ef\u00f1"+
		"\5\r\0\0\u00f1\u00f9\3\16\7\0\u00f3\u00f5\5\37\0\0\u00f5\u00f8\3\16\7"+
		"\0\u00f7\u00f3\1\0\0\0\u00f8\u00fb\1\0\0\0\u00f9\u00f7\1\0\0\0\u00f9\u00fa"+
		"\1\0\0\0\u00fa\u00fc\1\0\0\0\u00fb\u00f9\1\0\0\0\u00fc\u00fd\5 \0\0\u00fd"+
		"\r\1\0\0\0\u00fe\u0100\3\u0090H\0\u0100\u0102\5&\0\0\u0102\u0103\3\u0090"+
		"H\0\u0103\u0107\1\0\0\0\u0104\u0107\3\u0090H\0\u0106\u00fe\1\0\0\0\u0106"+
		"\u0104\1\0\0\0\u0107\17\1\0\0\0\u0108\u010c\5\13\0\0\u010a\u010d\3\22"+
		"\t\0\u010c\u010a\1\0\0\0\u010d\u010e\1\0\0\0\u010e\u010c\1\0\0\0\u010e"+
		"\u010f\1\0\0\0\u010f\u0110\1\0\0\0\u0110\u0111\5\67\0\0\u0111\21\1\0\0"+
		"\0\u0112\u011a\3\u0090H\0\u0114\u0116\5&\0\0\u0116\u011b\5;\0\0\u0118"+
		"\u011b\1\0\0\0\u011a\u0114\1\0\0\0\u011a\u0118\1\0\0\0\u011b\u011c\1\0"+
		"\0\0\u011c\u011d\5 \0\0\u011d\u0121\1\0\0\0\u011e\u0121\59\0\0\u0120\u0112"+
		"\1\0\0\0\u0120\u011e\1\0\0\0\u0121\23\1\0\0\0\u0122\u0154\5\t\0\0\u0124"+
		"\u0153\3\24\n\0\u0126\u0153\3\26\13\0\u0128\u0153\3\30\f\0\u012a\u0153"+
		"\5^\0\0\u012c\u0153\5_\0\0\u012e\u0153\5[\0\0\u0130\u0153\5\\\0\0\u0132"+
		"\u0153\5]\0\0\u0134\u0153\5M\0\0\u0136\u0153\5N\0\0\u0138\u0153\5O\0\0"+
		"\u013a\u0153\5P\0\0\u013c\u0153\5Q\0\0\u013e\u0153\5R\0\0\u0140\u0153"+
		"\5S\0\0\u0142\u0153\5T\0\0\u0144\u0153\5X\0\0\u0146\u0153\5Y\0\0\u0148"+
		"\u0153\5Z\0\0\u014a\u0153\5V\0\0\u014c\u0153\5U\0\0\u014e\u0153\5W\0\0"+
		"\u0150\u0153\5L\0\0\u0152\u0124\1\0\0\0\u0152\u0126\1\0\0\0\u0152\u0128"+
		"\1\0\0\0\u0152\u012a\1\0\0\0\u0152\u012c\1\0\0\0\u0152\u012e\1\0\0\0\u0152"+
		"\u0130\1\0\0\0\u0152\u0132\1\0\0\0\u0152\u0134\1\0\0\0\u0152\u0136\1\0"+
		"\0\0\u0152\u0138\1\0\0\0\u0152\u013a\1\0\0\0\u0152\u013c\1\0\0\0\u0152"+
		"\u013e\1\0\0\0\u0152\u0140\1\0\0\0\u0152\u0142\1\0\0\0\u0152\u0144\1\0"+
		"\0\0\u0152\u0146\1\0\0\0\u0152\u0148\1\0\0\0\u0152\u014a\1\0\0\0\u0152"+
		"\u014c\1\0\0\0\u0152\u014e\1\0\0\0\u0152\u0150\1\0\0\0\u0153\u0156\1\0"+
		"\0\0\u0154\u0152\1\0\0\0\u0154\u0155\1\0\0\0\u0155\u0157\1\0\0\0\u0156"+
		"\u0154\1\0\0\0\u0157\u0158\5`\0\0\u0158\25\1\0\0\0\u0159\u015f\5Z\0\0"+
		"\u015b\u015e\3$\22\0\u015d\u015b\1\0\0\0\u015e\u0161\1\0\0\0\u015f\u015d"+
		"\1\0\0\0\u015f\u0160\1\0\0\0\u0160\u0162\1\0\0\0\u0161\u015f\1\0\0\0\u0162"+
		"\u0168\5L\0\0\u0164\u0167\3$\22\0\u0166\u0164\1\0\0\0\u0167\u016a\1\0"+
		"\0\0\u0168\u0166\1\0\0\0\u0168\u0169\1\0\0\0\u0169\u016b\1\0\0\0\u016a"+
		"\u0168\1\0\0\0\u016b\u016c\5Y\0\0\u016c\27\1\0\0\0\u016d\u0173\5Z\0\0"+
		"\u016f\u0172\3$\22\0\u0171\u016f\1\0\0\0\u0172\u0175\1\0\0\0\u0173\u0171"+
		"\1\0\0\0\u0173\u0174\1\0\0\0\u0174\u019c\1\0\0\0\u0175\u0173\1\0\0\0\u0176"+
		"\u017c\5Q\0\0\u0178\u017b\3$\22\0\u017a\u0178\1\0\0\0\u017b\u017e\1\0"+
		"\0\0\u017c\u017a\1\0\0\0\u017c\u017d\1\0\0\0\u017d\u0188\1\0\0\0\u017e"+
		"\u017c\1\0\0\0\u017f\u0185\5W\0\0\u0181\u0184\3$\22\0\u0183\u0181\1\0"+
		"\0\0\u0184\u0187\1\0\0\0\u0185\u0183\1\0\0\0\u0185\u0186\1\0\0\0\u0186"+
		"\u0189\1\0\0\0\u0187\u0185\1\0\0\0\u0188\u017f\1\0\0\0\u0188\u0189\1\0"+
		"\0\0\u0189\u018a\1\0\0\0\u018a\u0190\5Y\0\0\u018c\u018f\3$\22\0\u018e"+
		"\u018c\1\0\0\0\u018f\u0192\1\0\0\0\u0190\u018e\1\0\0\0\u0190\u0191\1\0"+
		"\0\0\u0191\u0193\1\0\0\0\u0192\u0190\1\0\0\0\u0193\u0199\5R\0\0\u0195"+
		"\u0198\3$\22\0\u0197\u0195\1\0\0\0\u0198\u019b\1\0\0\0\u0199\u0197\1\0"+
		"\0\0\u0199\u019a\1\0\0\0\u019a\u019d\1\0\0\0\u019b\u0199\1\0\0\0\u019c"+
		"\u0176\1\0\0\0\u019c\u019d\1\0\0\0\u019d\u019e\1\0\0\0\u019e\u01a4\5U"+
		"\0\0\u01a0\u01a3\3$\22\0\u01a2\u01a0\1\0\0\0\u01a3\u01a6\1\0\0\0\u01a4"+
		"\u01a2\1\0\0\0\u01a4\u01a5\1\0\0\0\u01a5\u01a7\1\0\0\0\u01a6\u01a4\1\0"+
		"\0\0\u01a7\u01a8\5Y\0\0\u01a8\31\1\0\0\0\u01a9\u01af\5\b\0\0\u01ab\u01ae"+
		"\7\0\0\0\u01ad\u01ab\1\0\0\0\u01ae\u01b1\1\0\0\0\u01af\u01ad\1\0\0\0\u01af"+
		"\u01b0\1\0\0\0\u01b0\u01b2\1\0\0\0\u01b1\u01af\1\0\0\0\u01b2\u01b3\5J"+
		"\0\0\u01b3\33\1\0\0\0\u01b4\u01ba\5\b\0\0\u01b6\u01b9\3$\22\0\u01b8\u01b6"+
		"\1\0\0\0\u01b9\u01bc\1\0\0\0\u01ba\u01b8\1\0\0\0\u01ba\u01bb\1\0\0\0\u01bb"+
		"\u01dd\1\0\0\0\u01bc\u01ba\1\0\0\0\u01bd\u01c3\3\36\17\0\u01bf\u01c2\3"+
		"$\22\0\u01c1\u01bf\1\0\0\0\u01c2\u01c5\1\0\0\0\u01c3\u01c1\1\0\0\0\u01c3"+
		"\u01c4\1\0\0\0\u01c4\u01da\1\0\0\0\u01c5\u01c3\1\0\0\0\u01c6\u01cc\5C"+
		"\0\0\u01c8\u01cb\3$\22\0\u01ca\u01c8\1\0\0\0\u01cb\u01ce\1\0\0\0\u01cc"+
		"\u01ca\1\0\0\0\u01cc\u01cd\1\0\0\0\u01cd\u01cf\1\0\0\0\u01ce\u01cc\1\0"+
		"\0\0\u01cf\u01d5\3\36\17\0\u01d1\u01d4\3$\22\0\u01d3\u01d1\1\0\0\0\u01d4"+
		"\u01d7\1\0\0\0\u01d5\u01d3\1\0\0\0\u01d5\u01d6\1\0\0\0\u01d6\u01d9\1\0"+
		"\0\0\u01d7\u01d5\1\0\0\0\u01d8\u01c6\1\0\0\0\u01d9\u01dc\1\0\0\0\u01da"+
		"\u01d8\1\0\0\0\u01da\u01db\1\0\0\0\u01db\u01de\1\0\0\0\u01dc\u01da\1\0"+
		"\0\0\u01dd\u01bd\1\0\0\0\u01dd\u01de\1\0\0\0\u01de\u01df\1\0\0\0\u01df"+
		"\u01e0\5J\0\0\u01e0\35\1\0\0\0\u01e1\u01e4\3 \20\0\u01e3\u01e1\1\0\0\0"+
		"\u01e3\u01e4\1\0\0\0\u01e4\u01e9\1\0\0\0\u01e5\u01e8\3$\22\0\u01e7\u01e5"+
		"\1\0\0\0\u01e8\u01eb\1\0\0\0\u01e9\u01e7\1\0\0\0\u01e9\u01ea\1\0\0\0\u01ea"+
		"\u01ec\1\0\0\0\u01eb\u01e9\1\0\0\0\u01ec\u01ed\5E\0\0\u01ed\37\1\0\0\0"+
		"\u01ee\u01fb\3\"\21\0\u01f0\u01f3\3$\22\0\u01f2\u01f0\1\0\0\0\u01f3\u01f6"+
		"\1\0\0\0\u01f4\u01f2\1\0\0\0\u01f4\u01f5\1\0\0\0\u01f5\u01f7\1\0\0\0\u01f6"+
		"\u01f4\1\0\0\0\u01f7\u01fa\3\"\21\0\u01f9\u01f4\1\0\0\0\u01fa\u01fd\1"+
		"\0\0\0\u01fb\u01f9\1\0\0\0\u01fb\u01fc\1\0\0\0\u01fc!\1\0\0\0\u01fd\u01fb"+
		"\1\0\0\0\u01fe\u0211\5E\0\0\u0200\u0204\5>\0\0\u0202\u0205\3 \20\0\u0204"+
		"\u0202\1\0\0\0\u0204\u0205\1\0\0\0\u0205\u0206\1\0\0\0\u0206\u0211\5?"+
		"\0\0\u0208\u020c\5@\0\0\u020a\u020d\3 \20\0\u020c\u020a\1\0\0\0\u020c"+
		"\u020d\1\0\0\0\u020d\u020e\1\0\0\0\u020e\u0211\5A\0\0\u0210\u01fe\1\0"+
		"\0\0\u0210\u0200\1\0\0\0\u0210\u0208\1\0\0\0\u0211#\1\0\0\0\u0212\u021d"+
		"\5^\0\0\u0214\u021d\5_\0\0\u0216\u021d\5[\0\0\u0218\u021d\5H\0\0\u021a"+
		"\u021d\5I\0\0\u021c\u0212\1\0\0\0\u021c\u0214\1\0\0\0\u021c\u0216\1\0"+
		"\0\0\u021c\u0218\1\0\0\0\u021c\u021a\1\0\0\0\u021d%\1\0\0\0\u021e\u0224"+
		"\5\64\0\0\u0220\u0222\3(\24\0\u0222\u0223\5\36\0\0\u0223\u0225\1\0\0\0"+
		"\u0224\u0220\1\0\0\0\u0224\u0225\1\0\0\0\u0225\u0226\1\0\0\0\u0226\u0228"+
		"\3\u0090H\0\u0228\u0229\3\24\n\0\u0229\'\1\0\0\0\u022a\u0231\3\u0090H"+
		"\0\u022c\u0231\5\17\0\0\u022e\u0231\5\20\0\0\u0230\u022a\1\0\0\0\u0230"+
		"\u022c\1\0\0\0\u0230\u022e\1\0\0\0\u0231)\1\0\0\0\u0232\u0234\5\34\0\0"+
		"\u0234\u0236\3\u0090H\0\u0236\u023a\5 \0\0\u0238\u023b\3.\27\0\u023a\u0238"+
		"\1\0\0\0\u023b\u023c\1\0\0\0\u023c\u023a\1\0\0\0\u023c\u023d\1\0\0\0\u023d"+
		"+\1\0\0\0\u023e\u0241\3.\27\0\u0240\u023e\1\0\0\0\u0241\u0244\1\0\0\0"+
		"\u0242\u0240\1\0\0\0\u0242\u0243\1\0\0\0\u0243-\1\0\0\0\u0244\u0242\1"+
		"\0\0\0\u0245\u0248\5\3\0\0\u0247\u0245\1\0\0\0\u0247\u0248\1\0\0\0\u0248"+
		"\u024b\1\0\0\0\u0249\u024c\3B!\0\u024b\u0249\1\0\0\0\u024b\u024c\1\0\0"+
		"\0\u024c\u024d\1\0\0\0\u024d\u0251\3\u0090H\0\u024f\u0252\3\34\16\0\u0251"+
		"\u024f\1\0\0\0\u0251\u0252\1\0\0\0\u0252\u0255\1\0\0\0\u0253\u0256\3:"+
		"\35\0\u0255\u0253\1\0\0\0\u0255\u0256\1\0\0\0\u0256\u0259\1\0\0\0\u0257"+
		"\u025a\3<\36\0\u0259\u0257\1\0\0\0\u0259\u025a\1\0\0\0\u025a\u025d\1\0"+
		"\0\0\u025b\u025e\3>\37\0\u025d\u025b\1\0\0\0\u025d\u025e\1\0\0\0\u025e"+
		"\u025f\1\0\0\0\u025f\u0261\3\66\33\0\u0261\u0263\5\35\0\0\u0263\u0265"+
		"\3F#\0\u0265\u0267\5 \0\0\u0267\u0268\3\60\30\0\u0268/\1\0\0\0\u0269\u026c"+
		"\3\62\31\0\u026b\u0269\1\0\0\0\u026c\u026f\1\0\0\0\u026d\u026b\1\0\0\0"+
		"\u026d\u026e\1\0\0\0\u026e\u0272\1\0\0\0\u026f\u026d\1\0\0\0\u0270\u0273"+
		"\3\64\32\0\u0272\u0270\1\0\0\0\u0272\u0273\1\0\0\0\u0273\61\1\0\0\0\u0274"+
		"\u0276\5\31\0\0\u0276\u0278\3\32\r\0\u0278\u0279\3\24\n\0\u0279\63\1\0"+
		"\0\0\u027a\u027c\5\32\0\0\u027c\u027d\3\24\n\0\u027d\65\1\0\0\0\u027e"+
		"\u0281\38\34\0\u0280\u027e\1\0\0\0\u0281\u0284\1\0\0\0\u0282\u0280\1\0"+
		"\0\0\u0282\u0283\1\0\0\0\u0283\67\1\0\0\0\u0284\u0282\1\0\0\0\u0285\u028a"+
		"\3\6\3\0\u0287\u028a\3@ \0\u0289\u0285\1\0\0\0\u0289\u0287\1\0\0\0\u028a"+
		"9\1\0\0\0\u028b\u028d\5\26\0\0\u028d\u028e\3\34\16\0\u028e;\1\0\0\0\u028f"+
		"\u0291\5\30\0\0\u0291\u0299\3\u0092I\0\u0293\u0295\5\37\0\0\u0295\u0298"+
		"\3\u0092I\0\u0297\u0293\1\0\0\0\u0298\u029b\1\0\0\0\u0299\u0297\1\0\0"+
		"\0\u0299\u029a\1\0\0\0\u029a=\1\0\0\0\u029b\u0299\1\0\0\0\u029c\u029e"+
		"\5\27\0\0\u029e\u029f\3\34\16\0\u029f?\1\0\0\0\u02a0\u02a2\5\64\0\0\u02a2"+
		"\u02a4\3\u0090H\0\u02a4\u02a5\3\24\n\0\u02a5A\1\0\0\0\u02a6\u02a9\3D\""+
		"\0\u02a8\u02a6\1\0\0\0\u02a9\u02aa\1\0\0\0\u02aa\u02a8\1\0\0\0\u02aa\u02ab"+
		"\1\0\0\0\u02abC\1\0\0\0\u02ac\u02b5\5\24\0\0\u02ae\u02b5\5\25\0\0\u02b0"+
		"\u02b5\5\23\0\0\u02b2\u02b5\5\16\0\0\u02b4\u02ac\1\0\0\0\u02b4\u02ae\1"+
		"\0\0\0\u02b4\u02b0\1\0\0\0\u02b4\u02b2\1\0\0\0\u02b5E\1\0\0\0\u02b6\u02b7"+
		"\3H$\0\u02b7G\1\0\0\0\u02b8\u02c0\3J%\0\u02ba\u02bc\5,\0\0\u02bc\u02bf"+
		"\3J%\0\u02be\u02ba\1\0\0\0\u02bf\u02c2\1\0\0\0\u02c0\u02be\1\0\0\0\u02c0"+
		"\u02c1\1\0\0\0\u02c1I\1\0\0\0\u02c2\u02c0\1\0\0\0\u02c3\u02c9\3N\'\0\u02c5"+
		"\u02c7\5\65\0\0\u02c7\u02ca\3\u0090H\0\u02c9\u02c5\1\0\0\0\u02c9\u02ca"+
		"\1\0\0\0\u02caK\1\0\0\0\u02cb\u02d3\3N\'\0\u02cd\u02cf\5,\0\0\u02cf\u02d2"+
		"\3N\'\0\u02d1\u02cd\1\0\0\0\u02d2\u02d5\1\0\0\0\u02d3\u02d1\1\0\0\0\u02d3"+
		"\u02d4\1\0\0\0\u02d4M\1\0\0\0\u02d5\u02d3\1\0\0\0\u02d6\u02dc\3P(\0\u02d8"+
		"\u02dd\3r9\0\u02da\u02dd\1\0\0\0\u02dc\u02d8\1\0\0\0\u02dc\u02da\1\0\0"+
		"\0\u02dd\u02e3\1\0\0\0\u02de\u02e3\3r9\0\u02e0\u02e3\1\0\0\0\u02e2\u02d6"+
		"\1\0\0\0\u02e2\u02de\1\0\0\0\u02e2\u02e0\1\0\0\0\u02e3O\1\0\0\0\u02e4"+
		"\u02e7\3R)\0\u02e6\u02e4\1\0\0\0\u02e7\u02e8\1\0\0\0\u02e8\u02e6\1\0\0"+
		"\0\u02e8\u02e9\1\0\0\0\u02e9Q\1\0\0\0\u02ea\u02f0\3T*\0\u02ec\u02f1\3"+
		"\\.\0\u02ee\u02f1\1\0\0\0\u02f0\u02ec\1\0\0\0\u02f0\u02ee\1\0\0\0\u02f1"+
		"\u030b\1\0\0\0\u02f2\u02f8\3^/\0\u02f4\u02f9\3\\.\0\u02f6\u02f9\1\0\0"+
		"\0\u02f8\u02f4\1\0\0\0\u02f8\u02f6\1\0\0\0\u02f9\u030b\1\0\0\0\u02fa\u030b"+
		"\3X,\0\u02fc\u0300\3\24\n\0\u02fe\u0301\5\'\0\0\u0300\u02fe\1\0\0\0\u0300"+
		"\u0301\1\0\0\0\u0301\u030b\1\0\0\0\u0302\u0308\3V+\0\u0304\u0309\3\\."+
		"\0\u0306\u0309\1\0\0\0\u0308\u0304\1\0\0\0\u0308\u0306\1\0\0\0\u0309\u030b"+
		"\1\0\0\0\u030a\u02ea\1\0\0\0\u030a\u02f2\1\0\0\0\u030a\u02fa\1\0\0\0\u030a"+
		"\u02fc\1\0\0\0\u030a\u0302\1\0\0\0\u030bS\1\0\0\0\u030c\u0312\3\u0090"+
		"H\0\u030e\u0313\5&\0\0\u0310\u0313\5+\0\0\u0312\u030e\1\0\0\0\u0312\u0310"+
		"\1\0\0\0\u0313\u031e\1\0\0\0\u0314\u031f\3^/\0\u0316\u031c\3f\63\0\u0318"+
		"\u031d\5-\0\0\u031a\u031d\5(\0\0\u031c\u0318\1\0\0\0\u031c\u031a\1\0\0"+
		"\0\u031c\u031d\1\0\0\0\u031d\u031f\1\0\0\0\u031e\u0314\1\0\0\0\u031e\u0316"+
		"\1\0\0\0\u031fU\1\0\0\0\u0320\u0322\5\63\0\0\u0322\u0326\3R)\0\u0324\u0327"+
		"\3R)\0\u0326\u0324\1\0\0\0\u0327\u0328\1\0\0\0\u0328\u0326\1\0\0\0\u0328"+
		"\u0329\1\0\0\0\u0329\u032a\1\0\0\0\u032a\u032b\5\"\0\0\u032bW\1\0\0\0"+
		"\u032c\u0332\3f\63\0\u032e\u0333\3Z-\0\u0330\u0333\1\0\0\0\u0332\u032e"+
		"\1\0\0\0\u0332\u0330\1\0\0\0\u0333Y\1\0\0\0\u0334\u033b\3\\.\0\u0336\u033b"+
		"\5-\0\0\u0338\u033b\5(\0\0\u033a\u0334\1\0\0\0\u033a\u0336\1\0\0\0\u033a"+
		"\u0338\1\0\0\0\u033b[\1\0\0\0\u033c\u0343\5\'\0\0\u033e\u0343\5)\0\0\u0340"+
		"\u0343\5*\0\0\u0342\u033c\1\0\0\0\u0342\u033e\1\0\0\0\u0342\u0340\1\0"+
		"\0\0\u0343]\1\0\0\0\u0344\u0348\3j\65\0\u0346\u0349\7\1\0\0\u0348\u0346"+
		"\1\0\0\0\u0348\u0349\1\0\0\0\u0349\u0365\1\0\0\0\u034a\u034e\3l\66\0\u034c"+
		"\u034f\7\2\0\0\u034e\u034c\1\0\0\0\u034e\u034f\1\0\0\0\u034f\u0365\1\0"+
		"\0\0\u0350\u0365\3h\64\0\u0352\u0356\3`\60\0\u0354\u0357\7\3\0\0\u0356"+
		"\u0354\1\0\0\0\u0356\u0357\1\0\0\0\u0357\u0365\1\0\0\0\u0358\u035c\5/"+
		"\0\0\u035a\u035d\3n\67\0\u035c\u035a\1\0\0\0\u035c\u035d\1\0\0\0\u035d"+
		"\u0362\1\0\0\0\u035e\u0363\5-\0\0\u0360\u0363\5(\0\0\u0362\u035e\1\0\0"+
		"\0\u0362\u0360\1\0\0\0\u0362\u0363\1\0\0\0\u0363\u0365\1\0\0\0\u0364\u0344"+
		"\1\0\0\0\u0364\u034a\1\0\0\0\u0364\u0350\1\0\0\0\u0364\u0352\1\0\0\0\u0364"+
		"\u0358\1\0\0\0\u0365_\1\0\0\0\u0366\u0368\5\66\0\0\u0368\u036f\3d\62\0"+
		"\u036a\u036c\5\66\0\0\u036c\u036f\3b\61\0\u036e\u0366\1\0\0\0\u036e\u036a"+
		"\1\0\0\0\u036fa\1\0\0\0\u0370\u0372\5!\0\0\u0372\u037a\3d\62\0\u0374\u0376"+
		"\5,\0\0\u0376\u0379\3d\62\0\u0378\u0374\1\0\0\0\u0379\u037c\1\0\0\0\u037a"+
		"\u0378\1\0\0\0\u037a\u037b\1\0\0\0\u037b\u037d\1\0\0\0\u037c\u037a\1\0"+
		"\0\0\u037d\u037e\5\"\0\0\u037ec\1\0\0\0\u037f\u0386\58\0\0\u0381\u0386"+
		"\5;\0\0\u0383\u0386\3j\65\0\u0385\u037f\1\0\0\0\u0385\u0381\1\0\0\0\u0385"+
		"\u0383\1\0\0\0\u0386e\1\0\0\0\u0387\u0396\5!\0\0\u0389\u038c\3\6\3\0\u038b"+
		"\u0389\1\0\0\0\u038b\u038c\1\0\0\0\u038c\u0391\1\0\0\0\u038d\u0390\3@"+
		" \0\u038f\u038d\1\0\0\0\u0390\u0393\1\0\0\0\u0391\u038f\1\0\0\0\u0391"+
		"\u0392\1\0\0\0\u0392\u0394\1\0\0\0\u0393\u0391\1\0\0\0\u0394\u0397\5\35"+
		"\0\0\u0396\u038b\1\0\0\0\u0396\u0397\1\0\0\0\u0397\u0398\1\0\0\0\u0398"+
		"\u039a\3L&\0\u039a\u039b\5\"\0\0\u039bg\1\0\0\0\u039c\u03a0\59\0\0\u039e"+
		"\u03a1\3\32\r\0\u03a0\u039e\1\0\0\0\u03a0\u03a1\1\0\0\0\u03a1\u03aa\1"+
		"\0\0\0\u03a2\u03a7\5-\0\0\u03a4\u03a7\5(\0\0\u03a6\u03a2\1\0\0\0\u03a6"+
		"\u03a4\1\0\0\0\u03a7\u03ab\1\0\0\0\u03a8\u03ab\1\0\0\0\u03aa\u03a6\1\0"+
		"\0\0\u03aa\u03a8\1\0\0\0\u03abi\1\0\0\0\u03ac\u03ae\5;\0\0\u03ae\u03b0"+
		"\5\60\0\0\u03b0\u03b1\5;\0\0\u03b1k\1\0\0\0\u03b2\u03b6\58\0\0\u03b4\u03b7"+
		"\3n\67\0\u03b6\u03b4\1\0\0\0\u03b6\u03b7\1\0\0\0\u03b7\u03bf\1\0\0\0\u03b8"+
		"\u03bc\5;\0\0\u03ba\u03bd\3n\67\0\u03bc\u03ba\1\0\0\0\u03bc\u03bd\1\0"+
		"\0\0\u03bd\u03bf\1\0\0\0\u03be\u03b2\1\0\0\0\u03be\u03b8\1\0\0\0\u03bf"+
		"m\1\0\0\0\u03c0\u03c2\5$\0\0\u03c2\u03ca\3p8\0\u03c4\u03c6\5\37\0\0\u03c6"+
		"\u03c9\3p8\0\u03c8\u03c4\1\0\0\0\u03c9\u03cc\1\0\0\0\u03ca\u03c8\1\0\0"+
		"\0\u03ca\u03cb\1\0\0\0\u03cb\u03cd\1\0\0\0\u03cc\u03ca\1\0\0\0\u03cd\u03ce"+
		"\5%\0\0\u03ceo\1\0\0\0\u03cf\u03dc\3\u0092I\0\u03d1\u03d3\3\u0090H\0\u03d3"+
		"\u03d9\5&\0\0\u03d5\u03da\3\u0092I\0\u03d7\u03da\5;\0\0\u03d9\u03d5\1"+
		"\0\0\0\u03d9\u03d7\1\0\0\0\u03da\u03dc\1\0\0\0\u03db\u03cf\1\0\0\0\u03db"+
		"\u03d1\1\0\0\0\u03dcq\1\0\0\0\u03dd\u03e0\3t:\0\u03df\u03dd\1\0\0\0\u03e0"+
		"\u03e3\1\0\0\0\u03e1\u03df\1\0\0\0\u03e1\u03e2\1\0\0\0\u03e2\u03e4\1\0"+
		"\0\0\u03e3\u03e1\1\0\0\0\u03e4\u03e5\3v;\0\u03e5s\1\0\0\0\u03e6\u03e8"+
		"\5\62\0\0\u03e8\u03ea\5\u0080\0\0\u03ea\u03eb\3x<\0\u03ebu\1\0\0\0\u03ec"+
		"\u03ee\5\62\0\0\u03ee\u03ef\3x<\0\u03efw\1\0\0\0\u03f0\u03f9\3z=\0\u03f2"+
		"\u03f9\3\u0086C\0\u03f4\u03f9\5\61\0\0\u03f6\u03f9\1\0\0\0\u03f8\u03f0"+
		"\1\0\0\0\u03f8\u03f2\1\0\0\0\u03f8\u03f4\1\0\0\0\u03f8\u03f6\1\0\0\0\u03f9"+
		"y\1\0\0\0\u03fa\u03fd\3|>\0\u03fc\u03fa\1\0\0\0\u03fd\u03fe\1\0\0\0\u03fe"+
		"\u03fc\1\0\0\0\u03fe\u03ff\1\0\0\0\u03ff{\1\0\0\0\u0400\u0404\3~?\0\u0402"+
		"\u0405\3\\.\0\u0404\u0402\1\0\0\0\u0404\u0405\1\0\0\0\u0405\u0411\1\0"+
		"\0\0\u0406\u040c\3\u0084B\0\u0408\u040d\3\\.\0\u040a\u040d\1\0\0\0\u040c"+
		"\u0408\1\0\0\0\u040c\u040a\1\0\0\0\u040d\u0411\1\0\0\0\u040e\u0411\3\u0080"+
		"@\0\u0410\u0400\1\0\0\0\u0410\u0406\1\0\0\0\u0410\u040e\1\0\0\0\u0411"+
		"}\1\0\0\0\u0412\u0416\58\0\0\u0414\u0417\3n\67\0\u0416\u0414\1\0\0\0\u0416"+
		"\u0417\1\0\0\0\u0417\u041a\1\0\0\0\u0418\u041b\3\32\r\0\u041a\u0418\1"+
		"\0\0\0\u041a\u041b\1\0\0\0\u041b\u042b\1\0\0\0\u041c\u042b\59\0\0\u041e"+
		"\u0422\5;\0\0\u0420\u0423\3n\67\0\u0422\u0420\1\0\0\0\u0422\u0423\1\0"+
		"\0\0\u0423\u042b\1\0\0\0\u0424\u0426\5.\0\0\u0426\u042b\3\u0090H\0\u0428"+
		"\u042b\3\24\n\0\u042a\u0412\1\0\0\0\u042a\u041c\1\0\0\0\u042a\u041e\1"+
		"\0\0\0\u042a\u0424\1\0\0\0\u042a\u0428\1\0\0\0\u042b\177\1\0\0\0\u042c"+
		"\u042e\5!\0\0\u042e\u0430\3z=\0\u0430\u0432\5\"\0\0\u0432\u0433\3\u0082"+
		"A\0\u0433\u0081\1\0\0\0\u0434\u0439\5\'\0\0\u0436\u0439\5)\0\0\u0438\u0434"+
		"\1\0\0\0\u0438\u0436\1\0\0\0\u0439\u0083\1\0\0\0\u043a\u043c\5\63\0\0"+
		"\u043c\u0442\3~?\0\u043e\u0441\3|>\0\u0440\u043e\1\0\0\0\u0441\u0444\1"+
		"\0\0\0\u0442\u0440\1\0\0\0\u0442\u0443\1\0\0\0\u0443\u0445\1\0\0\0\u0444"+
		"\u0442\1\0\0\0\u0445\u0446\5\"\0\0\u0446\u0085\1\0\0\0\u0447\u0449\5\33"+
		"\0\0\u0449\u044b\5!\0\0\u044b\u044d\3\u008cF\0\u044d\u0453\5\"\0\0\u044f"+
		"\u0454\5\6\0\0\u0451\u0454\5\7\0\0\u0453\u044f\1\0\0\0\u0453\u0451\1\0"+
		"\0\0\u0454\u045c\1\0\0\0\u0455\u045c\3\u0088D\0\u0457\u045c\3\u008aE\0"+
		"\u0459\u045c\3\24\n\0\u045b\u0447\1\0\0\0\u045b\u0455\1\0\0\0\u045b\u0457"+
		"\1\0\0\0\u045b\u0459\1\0\0\0\u045c\u0087\1\0\0\0\u045d\u045f\3\u0090H"+
		"\0\u045f\u0461\5!\0\0\u0461\u0463\3\u008cF\0\u0463\u0464\5\"\0\0\u0464"+
		"\u0089\1\0\0\0\u0465\u0467\5!\0\0\u0467\u0469\3\24\n\0\u0469\u046b\5\""+
		"\0\0\u046b\u046d\5!\0\0\u046d\u046f\3\u008cF\0\u046f\u0470\5\"\0\0\u0470"+
		"\u008b\1\0\0\0\u0471\u0479\3\u008eG\0\u0473\u0475\5\37\0\0\u0475\u0478"+
		"\3\u008eG\0\u0477\u0473\1\0\0\0\u0478\u047b\1\0\0\0\u0479\u0477\1\0\0"+
		"\0\u0479\u047a\1\0\0\0\u047a\u047f\1\0\0\0\u047b\u0479\1\0\0\0\u047c\u047f"+
		"\1\0\0\0\u047e\u0471\1\0\0\0\u047e\u047c\1\0\0\0\u047f\u008d\1\0\0\0\u0480"+
		"\u0482\3\u0090H\0\u0482\u0484\5&\0\0\u0484\u0485\3\24\n\0\u0485\u008f"+
		"\1\0\0\0\u0486\u048d\59\0\0\u0488\u048d\58\0\0\u048a\u048d\5\33\0\0\u048c"+
		"\u0486\1\0\0\0\u048c\u0488\1\0\0\0\u048c\u048a\1\0\0\0\u048d\u0091\1\0"+
		"\0\0\u048e\u0496\3\u0090H\0\u0490\u0492\5/\0\0\u0492\u0495\3\u0090H\0"+
		"\u0494\u0490\1\0\0\0\u0495\u0498\1\0\0\0\u0496\u0494\1\0\0\0\u0496\u0497"+
		"\1\0\0\0\u0497\u0093\1\0\0\0\u0498\u0496\1\0\0\0\u0499\u049b\3N\'\0\u049b"+
		"\u049c\5\uffff\0\0\u049c\u0095\1\0\0\0\u049d\u049f\3R)\0\u049f\u04a0\5"+
		"\uffff\0\0\u04a0\u0097\1\0\0\0\u04a1\u04a3\3.\27\0\u04a3\u04a4\5\uffff"+
		"\0\0\u04a4\u0099\1\0\0\0\u04a5\u04a7\3f\63\0\u04a7\u04a8\5\uffff\0\0\u04a8"+
		"\u009b\1\0\0\0q\u009e\1\u00aa\1\u00b3\1\u00c6\1\u00d0\1\u00da\1\u00ed"+
		"\1\u00f9\1\u0106\1\u010e\1\u011a\1\u0120\1\u0152\1\u0154\1\u015f\1\u0168"+
		"\1\u0173\1\u017c\1\u0185\1\u0188\1\u0190\1\u0199\1\u019c\1\u01a4\1\u01af"+
		"\1\u01ba\1\u01c3\1\u01cc\1\u01d5\1\u01da\1\u01dd\1\u01e3\1\u01e9\1\u01f4"+
		"\1\u01fb\1\u0204\1\u020c\1\u0210\1\u021c\1\u0224\1\u0230\1\u023c\1\u0242"+
		"\1\u0247\1\u024b\1\u0251\1\u0255\1\u0259\1\u025d\1\u026d\1\u0272\1\u0282"+
		"\1\u0289\1\u0299\1\u02aa\1\u02b4\1\u02c0\1\u02c9\1\u02d3\1\u02dc\1\u02e2"+
		"\1\u02e8\1\u02f0\1\u02f8\1\u0300\1\u0308\1\u030a\1\u0312\1\u031c\1\u031e"+
		"\1\u0328\1\u0332\1\u033a\1\u0342\1\u0348\1\u034e\1\u0356\1\u035c\1\u0362"+
		"\1\u0364\1\u036e\1\u037a\1\u0385\1\u038b\1\u0391\1\u0396\1\u03a0\1\u03a6"+
		"\1\u03aa\1\u03b6\1\u03bc\1\u03be\1\u03ca\1\u03d9\1\u03db\1\u03e1\1\u03f8"+
		"\1\u03fe\1\u0404\1\u040c\1\u0410\1\u0416\1\u041a\1\u0422\1\u042a\1\u0438"+
		"\1\u0442\1\u0453\1\u045b\1\u0479\1\u047e\1\u048c\1\u0496\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}