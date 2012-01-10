
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

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface GrammarParserListener extends ParseTreeListener<Token> {
	void enterRule(GrammarParser.grammarSpecContext ctx);
	void exitRule(GrammarParser.grammarSpecContext ctx);

	void enterRule(GrammarParser.grammarTypeContext ctx);
	void exitRule(GrammarParser.grammarTypeContext ctx);

	void enterRule(GrammarParser.prequelConstructContext ctx);
	void exitRule(GrammarParser.prequelConstructContext ctx);

	void enterRule(GrammarParser.optionsSpecContext ctx);
	void exitRule(GrammarParser.optionsSpecContext ctx);

	void enterRule(GrammarParser.optionContext ctx);
	void exitRule(GrammarParser.optionContext ctx);

	void enterRule(GrammarParser.optionValueContext ctx);
	void exitRule(GrammarParser.optionValueContext ctx);

	void enterRule(GrammarParser.delegateGrammarsContext ctx);
	void exitRule(GrammarParser.delegateGrammarsContext ctx);

	void enterRule(GrammarParser.delegateGrammarContext ctx);
	void exitRule(GrammarParser.delegateGrammarContext ctx);

	void enterRule(GrammarParser.tokensSpecContext ctx);
	void exitRule(GrammarParser.tokensSpecContext ctx);

	void enterRule(GrammarParser.tokenSpecContext ctx);
	void exitRule(GrammarParser.tokenSpecContext ctx);

	void enterRule(GrammarParser.actionBlockContext ctx);
	void exitRule(GrammarParser.actionBlockContext ctx);

	void enterRule(GrammarParser.actionExpressionContext ctx);
	void exitRule(GrammarParser.actionExpressionContext ctx);

	void enterRule(GrammarParser.actionScopeExpressionContext ctx);
	void exitRule(GrammarParser.actionScopeExpressionContext ctx);

	void enterRule(GrammarParser.argActionBlockContext ctx);
	void exitRule(GrammarParser.argActionBlockContext ctx);

	void enterRule(GrammarParser.argActionParametersContext ctx);
	void exitRule(GrammarParser.argActionParametersContext ctx);

	void enterRule(GrammarParser.argActionParameterContext ctx);
	void exitRule(GrammarParser.argActionParameterContext ctx);

	void enterRule(GrammarParser.argActionParameterTypeContext ctx);
	void exitRule(GrammarParser.argActionParameterTypeContext ctx);

	void enterRule(GrammarParser.argActionParameterTypePartContext ctx);
	void exitRule(GrammarParser.argActionParameterTypePartContext ctx);

	void enterRule(GrammarParser.ignoredContext ctx);
	void exitRule(GrammarParser.ignoredContext ctx);

	void enterRule(GrammarParser.actionContext ctx);
	void exitRule(GrammarParser.actionContext ctx);

	void enterRule(GrammarParser.actionScopeNameContext ctx);
	void exitRule(GrammarParser.actionScopeNameContext ctx);

	void enterRule(GrammarParser.modeSpecContext ctx);
	void exitRule(GrammarParser.modeSpecContext ctx);

	void enterRule(GrammarParser.rulesContext ctx);
	void exitRule(GrammarParser.rulesContext ctx);

	void enterRule(GrammarParser.ruleContext ctx);
	void exitRule(GrammarParser.ruleContext ctx);

	void enterRule(GrammarParser.parserRuleContext ctx);
	void exitRule(GrammarParser.parserRuleContext ctx);

	void enterRule(GrammarParser.exceptionGroupContext ctx);
	void exitRule(GrammarParser.exceptionGroupContext ctx);

	void enterRule(GrammarParser.exceptionHandlerContext ctx);
	void exitRule(GrammarParser.exceptionHandlerContext ctx);

	void enterRule(GrammarParser.finallyClauseContext ctx);
	void exitRule(GrammarParser.finallyClauseContext ctx);

	void enterRule(GrammarParser.rulePrequelsContext ctx);
	void exitRule(GrammarParser.rulePrequelsContext ctx);

	void enterRule(GrammarParser.rulePrequelContext ctx);
	void exitRule(GrammarParser.rulePrequelContext ctx);

	void enterRule(GrammarParser.ruleReturnsContext ctx);
	void exitRule(GrammarParser.ruleReturnsContext ctx);

	void enterRule(GrammarParser.throwsSpecContext ctx);
	void exitRule(GrammarParser.throwsSpecContext ctx);

	void enterRule(GrammarParser.localsSpecContext ctx);
	void exitRule(GrammarParser.localsSpecContext ctx);

	void enterRule(GrammarParser.ruleActionContext ctx);
	void exitRule(GrammarParser.ruleActionContext ctx);

	void enterRule(GrammarParser.ruleModifiersContext ctx);
	void exitRule(GrammarParser.ruleModifiersContext ctx);

	void enterRule(GrammarParser.ruleModifierContext ctx);
	void exitRule(GrammarParser.ruleModifierContext ctx);

	void enterRule(GrammarParser.ruleBlockContext ctx);
	void exitRule(GrammarParser.ruleBlockContext ctx);

	void enterRule(GrammarParser.ruleAltListContext ctx);
	void exitRule(GrammarParser.ruleAltListContext ctx);

	void enterRule(GrammarParser.labeledAltContext ctx);
	void exitRule(GrammarParser.labeledAltContext ctx);

	void enterRule(GrammarParser.lexerRuleContext ctx);
	void exitRule(GrammarParser.lexerRuleContext ctx);

	void enterRule(GrammarParser.lexerRuleBlockContext ctx);
	void exitRule(GrammarParser.lexerRuleBlockContext ctx);

	void enterRule(GrammarParser.lexerAltListContext ctx);
	void exitRule(GrammarParser.lexerAltListContext ctx);

	void enterRule(GrammarParser.lexerAltContext ctx);
	void exitRule(GrammarParser.lexerAltContext ctx);

	void enterRule(GrammarParser.lexerElementsContext ctx);
	void exitRule(GrammarParser.lexerElementsContext ctx);

	void enterRule(GrammarParser.lexerElementContext ctx);
	void exitRule(GrammarParser.lexerElementContext ctx);

	void enterRule(GrammarParser.labeledLexerElementContext ctx);
	void exitRule(GrammarParser.labeledLexerElementContext ctx);

	void enterRule(GrammarParser.lexerBlockContext ctx);
	void exitRule(GrammarParser.lexerBlockContext ctx);

	void enterRule(GrammarParser.lexerActionsContext ctx);
	void exitRule(GrammarParser.lexerActionsContext ctx);

	void enterRule(GrammarParser.lexerActionContext ctx);
	void exitRule(GrammarParser.lexerActionContext ctx);

	void enterRule(GrammarParser.lexerActionExprContext ctx);
	void exitRule(GrammarParser.lexerActionExprContext ctx);

	void enterRule(GrammarParser.altListContext ctx);
	void exitRule(GrammarParser.altListContext ctx);

	void enterRule(GrammarParser.alternativeContext ctx);
	void exitRule(GrammarParser.alternativeContext ctx);

	void enterRule(GrammarParser.elementsContext ctx);
	void exitRule(GrammarParser.elementsContext ctx);

	void enterRule(GrammarParser.elementContext ctx);
	void exitRule(GrammarParser.elementContext ctx);

	void enterRule(GrammarParser.labeledElementContext ctx);
	void exitRule(GrammarParser.labeledElementContext ctx);

	void enterRule(GrammarParser.ebnfContext ctx);
	void exitRule(GrammarParser.ebnfContext ctx);

	void enterRule(GrammarParser.blockSuffixContext ctx);
	void exitRule(GrammarParser.blockSuffixContext ctx);

	void enterRule(GrammarParser.ebnfSuffixContext ctx);
	void exitRule(GrammarParser.ebnfSuffixContext ctx);

	void enterRule(GrammarParser.lexerAtomContext ctx);
	void exitRule(GrammarParser.lexerAtomContext ctx);

	void enterRule(GrammarParser.atomContext ctx);
	void exitRule(GrammarParser.atomContext ctx);

	void enterRule(GrammarParser.notSetContext ctx);
	void exitRule(GrammarParser.notSetContext ctx);

	void enterRule(GrammarParser.blockSetContext ctx);
	void exitRule(GrammarParser.blockSetContext ctx);

	void enterRule(GrammarParser.setElementContext ctx);
	void exitRule(GrammarParser.setElementContext ctx);

	void enterRule(GrammarParser.blockContext ctx);
	void exitRule(GrammarParser.blockContext ctx);

	void enterRule(GrammarParser.rulerefContext ctx);
	void exitRule(GrammarParser.rulerefContext ctx);

	void enterRule(GrammarParser.rangeContext ctx);
	void exitRule(GrammarParser.rangeContext ctx);

	void enterRule(GrammarParser.terminalContext ctx);
	void exitRule(GrammarParser.terminalContext ctx);

	void enterRule(GrammarParser.elementOptionsContext ctx);
	void exitRule(GrammarParser.elementOptionsContext ctx);

	void enterRule(GrammarParser.elementOptionContext ctx);
	void exitRule(GrammarParser.elementOptionContext ctx);

	void enterRule(GrammarParser.idContext ctx);
	void exitRule(GrammarParser.idContext ctx);

	void enterRule(GrammarParser.qidContext ctx);
	void exitRule(GrammarParser.qidContext ctx);
}