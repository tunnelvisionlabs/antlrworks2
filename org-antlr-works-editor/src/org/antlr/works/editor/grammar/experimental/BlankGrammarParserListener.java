
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


import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class BlankGrammarParserListener implements GrammarParserListener {
	@Override public void enterRule(GrammarParser.grammarSpecContext ctx) { }
	@Override public void exitRule(GrammarParser.grammarSpecContext ctx) { }

	@Override public void enterRule(GrammarParser.grammarTypeContext ctx) { }
	@Override public void exitRule(GrammarParser.grammarTypeContext ctx) { }

	@Override public void enterRule(GrammarParser.prequelConstructContext ctx) { }
	@Override public void exitRule(GrammarParser.prequelConstructContext ctx) { }

	@Override public void enterRule(GrammarParser.optionsSpecContext ctx) { }
	@Override public void exitRule(GrammarParser.optionsSpecContext ctx) { }

	@Override public void enterRule(GrammarParser.optionContext ctx) { }
	@Override public void exitRule(GrammarParser.optionContext ctx) { }

	@Override public void enterRule(GrammarParser.optionValueContext ctx) { }
	@Override public void exitRule(GrammarParser.optionValueContext ctx) { }

	@Override public void enterRule(GrammarParser.delegateGrammarsContext ctx) { }
	@Override public void exitRule(GrammarParser.delegateGrammarsContext ctx) { }

	@Override public void enterRule(GrammarParser.delegateGrammarContext ctx) { }
	@Override public void exitRule(GrammarParser.delegateGrammarContext ctx) { }

	@Override public void enterRule(GrammarParser.tokensSpecContext ctx) { }
	@Override public void exitRule(GrammarParser.tokensSpecContext ctx) { }

	@Override public void enterRule(GrammarParser.tokenSpecContext ctx) { }
	@Override public void exitRule(GrammarParser.tokenSpecContext ctx) { }

	@Override public void enterRule(GrammarParser.actionBlockContext ctx) { }
	@Override public void exitRule(GrammarParser.actionBlockContext ctx) { }

	@Override public void enterRule(GrammarParser.actionExpressionContext ctx) { }
	@Override public void exitRule(GrammarParser.actionExpressionContext ctx) { }

	@Override public void enterRule(GrammarParser.actionScopeExpressionContext ctx) { }
	@Override public void exitRule(GrammarParser.actionScopeExpressionContext ctx) { }

	@Override public void enterRule(GrammarParser.argActionBlockContext ctx) { }
	@Override public void exitRule(GrammarParser.argActionBlockContext ctx) { }

	@Override public void enterRule(GrammarParser.argActionParametersContext ctx) { }
	@Override public void exitRule(GrammarParser.argActionParametersContext ctx) { }

	@Override public void enterRule(GrammarParser.argActionParameterContext ctx) { }
	@Override public void exitRule(GrammarParser.argActionParameterContext ctx) { }

	@Override public void enterRule(GrammarParser.argActionParameterTypeContext ctx) { }
	@Override public void exitRule(GrammarParser.argActionParameterTypeContext ctx) { }

	@Override public void enterRule(GrammarParser.argActionParameterTypePartContext ctx) { }
	@Override public void exitRule(GrammarParser.argActionParameterTypePartContext ctx) { }

	@Override public void enterRule(GrammarParser.ignoredContext ctx) { }
	@Override public void exitRule(GrammarParser.ignoredContext ctx) { }

	@Override public void enterRule(GrammarParser.actionContext ctx) { }
	@Override public void exitRule(GrammarParser.actionContext ctx) { }

	@Override public void enterRule(GrammarParser.actionScopeNameContext ctx) { }
	@Override public void exitRule(GrammarParser.actionScopeNameContext ctx) { }

	@Override public void enterRule(GrammarParser.mode_Context ctx) { }
	@Override public void exitRule(GrammarParser.mode_Context ctx) { }

	@Override public void enterRule(GrammarParser.rulesContext ctx) { }
	@Override public void exitRule(GrammarParser.rulesContext ctx) { }

	@Override public void enterRule(GrammarParser.ruleContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleContext ctx) { }

	@Override public void enterRule(GrammarParser.exceptionGroupContext ctx) { }
	@Override public void exitRule(GrammarParser.exceptionGroupContext ctx) { }

	@Override public void enterRule(GrammarParser.exceptionHandlerContext ctx) { }
	@Override public void exitRule(GrammarParser.exceptionHandlerContext ctx) { }

	@Override public void enterRule(GrammarParser.finallyClauseContext ctx) { }
	@Override public void exitRule(GrammarParser.finallyClauseContext ctx) { }

	@Override public void enterRule(GrammarParser.rulePrequelsContext ctx) { }
	@Override public void exitRule(GrammarParser.rulePrequelsContext ctx) { }

	@Override public void enterRule(GrammarParser.rulePrequelContext ctx) { }
	@Override public void exitRule(GrammarParser.rulePrequelContext ctx) { }

	@Override public void enterRule(GrammarParser.ruleReturnsContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleReturnsContext ctx) { }

	@Override public void enterRule(GrammarParser.throwsSpecContext ctx) { }
	@Override public void exitRule(GrammarParser.throwsSpecContext ctx) { }

	@Override public void enterRule(GrammarParser.locals_Context ctx) { }
	@Override public void exitRule(GrammarParser.locals_Context ctx) { }

	@Override public void enterRule(GrammarParser.ruleActionContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleActionContext ctx) { }

	@Override public void enterRule(GrammarParser.ruleModifiersContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleModifiersContext ctx) { }

	@Override public void enterRule(GrammarParser.ruleModifierContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleModifierContext ctx) { }

	@Override public void enterRule(GrammarParser.ruleBlockContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleBlockContext ctx) { }

	@Override public void enterRule(GrammarParser.ruleAltListContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleAltListContext ctx) { }

	@Override public void enterRule(GrammarParser.labeledAltContext ctx) { }
	@Override public void exitRule(GrammarParser.labeledAltContext ctx) { }

	@Override public void enterRule(GrammarParser.altListContext ctx) { }
	@Override public void exitRule(GrammarParser.altListContext ctx) { }

	@Override public void enterRule(GrammarParser.alternativeContext ctx) { }
	@Override public void exitRule(GrammarParser.alternativeContext ctx) { }

	@Override public void enterRule(GrammarParser.elementsContext ctx) { }
	@Override public void exitRule(GrammarParser.elementsContext ctx) { }

	@Override public void enterRule(GrammarParser.elementContext ctx) { }
	@Override public void exitRule(GrammarParser.elementContext ctx) { }

	@Override public void enterRule(GrammarParser.labeledElementContext ctx) { }
	@Override public void exitRule(GrammarParser.labeledElementContext ctx) { }

	@Override public void enterRule(GrammarParser.treeSpecContext ctx) { }
	@Override public void exitRule(GrammarParser.treeSpecContext ctx) { }

	@Override public void enterRule(GrammarParser.ebnfContext ctx) { }
	@Override public void exitRule(GrammarParser.ebnfContext ctx) { }

	@Override public void enterRule(GrammarParser.blockSuffixContext ctx) { }
	@Override public void exitRule(GrammarParser.blockSuffixContext ctx) { }

	@Override public void enterRule(GrammarParser.ebnfSuffixContext ctx) { }
	@Override public void exitRule(GrammarParser.ebnfSuffixContext ctx) { }

	@Override public void enterRule(GrammarParser.atomContext ctx) { }
	@Override public void exitRule(GrammarParser.atomContext ctx) { }

	@Override public void enterRule(GrammarParser.notSetContext ctx) { }
	@Override public void exitRule(GrammarParser.notSetContext ctx) { }

	@Override public void enterRule(GrammarParser.blockSetContext ctx) { }
	@Override public void exitRule(GrammarParser.blockSetContext ctx) { }

	@Override public void enterRule(GrammarParser.setElementContext ctx) { }
	@Override public void exitRule(GrammarParser.setElementContext ctx) { }

	@Override public void enterRule(GrammarParser.blockContext ctx) { }
	@Override public void exitRule(GrammarParser.blockContext ctx) { }

	@Override public void enterRule(GrammarParser.rulerefContext ctx) { }
	@Override public void exitRule(GrammarParser.rulerefContext ctx) { }

	@Override public void enterRule(GrammarParser.rangeContext ctx) { }
	@Override public void exitRule(GrammarParser.rangeContext ctx) { }

	@Override public void enterRule(GrammarParser.terminalContext ctx) { }
	@Override public void exitRule(GrammarParser.terminalContext ctx) { }

	@Override public void enterRule(GrammarParser.elementOptionsContext ctx) { }
	@Override public void exitRule(GrammarParser.elementOptionsContext ctx) { }

	@Override public void enterRule(GrammarParser.elementOptionContext ctx) { }
	@Override public void exitRule(GrammarParser.elementOptionContext ctx) { }

	@Override public void enterRule(GrammarParser.rewriteContext ctx) { }
	@Override public void exitRule(GrammarParser.rewriteContext ctx) { }

	@Override public void enterRule(GrammarParser.predicatedRewriteContext ctx) { }
	@Override public void exitRule(GrammarParser.predicatedRewriteContext ctx) { }

	@Override public void enterRule(GrammarParser.nakedRewriteContext ctx) { }
	@Override public void exitRule(GrammarParser.nakedRewriteContext ctx) { }

	@Override public void enterRule(GrammarParser.rewriteAltContext ctx) { }
	@Override public void exitRule(GrammarParser.rewriteAltContext ctx) { }

	@Override public void enterRule(GrammarParser.rewriteTreeAltContext ctx) { }
	@Override public void exitRule(GrammarParser.rewriteTreeAltContext ctx) { }

	@Override public void enterRule(GrammarParser.rewriteTreeElementContext ctx) { }
	@Override public void exitRule(GrammarParser.rewriteTreeElementContext ctx) { }

	@Override public void enterRule(GrammarParser.rewriteTreeAtomContext ctx) { }
	@Override public void exitRule(GrammarParser.rewriteTreeAtomContext ctx) { }

	@Override public void enterRule(GrammarParser.rewriteTreeEbnfContext ctx) { }
	@Override public void exitRule(GrammarParser.rewriteTreeEbnfContext ctx) { }

	@Override public void enterRule(GrammarParser.rewriteEbnfSuffixContext ctx) { }
	@Override public void exitRule(GrammarParser.rewriteEbnfSuffixContext ctx) { }

	@Override public void enterRule(GrammarParser.rewriteTreeContext ctx) { }
	@Override public void exitRule(GrammarParser.rewriteTreeContext ctx) { }

	@Override public void enterRule(GrammarParser.rewriteTemplateContext ctx) { }
	@Override public void exitRule(GrammarParser.rewriteTemplateContext ctx) { }

	@Override public void enterRule(GrammarParser.rewriteTemplateRefContext ctx) { }
	@Override public void exitRule(GrammarParser.rewriteTemplateRefContext ctx) { }

	@Override public void enterRule(GrammarParser.rewriteIndirectTemplateHeadContext ctx) { }
	@Override public void exitRule(GrammarParser.rewriteIndirectTemplateHeadContext ctx) { }

	@Override public void enterRule(GrammarParser.rewriteTemplateArgsContext ctx) { }
	@Override public void exitRule(GrammarParser.rewriteTemplateArgsContext ctx) { }

	@Override public void enterRule(GrammarParser.rewriteTemplateArgContext ctx) { }
	@Override public void exitRule(GrammarParser.rewriteTemplateArgContext ctx) { }

	@Override public void enterRule(GrammarParser.idContext ctx) { }
	@Override public void exitRule(GrammarParser.idContext ctx) { }

	@Override public void enterRule(GrammarParser.qidContext ctx) { }
	@Override public void exitRule(GrammarParser.qidContext ctx) { }

	@Override public void enterRule(GrammarParser.alternativeEntryContext ctx) { }
	@Override public void exitRule(GrammarParser.alternativeEntryContext ctx) { }

	@Override public void enterRule(GrammarParser.elementEntryContext ctx) { }
	@Override public void exitRule(GrammarParser.elementEntryContext ctx) { }

	@Override public void enterRule(GrammarParser.ruleEntryContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleEntryContext ctx) { }

	@Override public void enterRule(GrammarParser.blockEntryContext ctx) { }
	@Override public void exitRule(GrammarParser.blockEntryContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext<Token> ctx) { }
	@Override public void exitEveryRule(ParserRuleContext<Token> ctx) { }
	@Override public void visitTerminal(ParserRuleContext<Token> ctx, Token symbol) { }
}