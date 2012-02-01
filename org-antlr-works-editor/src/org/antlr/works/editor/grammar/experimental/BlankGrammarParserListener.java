/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;


import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class BlankGrammarParserListener implements GrammarParserListener {
	@Override public void enterRule(GrammarParser.parserRuleContext ctx) { }
	@Override public void exitRule(GrammarParser.parserRuleContext ctx) { }

	@Override public void enterRule(GrammarParser.atomContext ctx) { }
	@Override public void exitRule(GrammarParser.atomContext ctx) { }

	@Override public void enterRule(GrammarParser.rulePrequelsContext ctx) { }
	@Override public void exitRule(GrammarParser.rulePrequelsContext ctx) { }

	@Override public void enterRule(GrammarParser.ruleBlockContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleBlockContext ctx) { }

	@Override public void enterRule(GrammarParser.notSetContext ctx) { }
	@Override public void exitRule(GrammarParser.notSetContext ctx) { }

	@Override public void enterRule(GrammarParser.lexerAltListContext ctx) { }
	@Override public void exitRule(GrammarParser.lexerAltListContext ctx) { }

	@Override public void enterRule(GrammarParser.argActionParameterContext ctx) { }
	@Override public void exitRule(GrammarParser.argActionParameterContext ctx) { }

	@Override public void enterRule(GrammarParser.ruleModifierContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleModifierContext ctx) { }

	@Override public void enterRule(GrammarParser.ruleAltListContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleAltListContext ctx) { }

	@Override public void enterRule(GrammarParser.terminalContext ctx) { }
	@Override public void exitRule(GrammarParser.terminalContext ctx) { }

	@Override public void enterRule(GrammarParser.throwsSpecContext ctx) { }
	@Override public void exitRule(GrammarParser.throwsSpecContext ctx) { }

	@Override public void enterRule(GrammarParser.actionContext ctx) { }
	@Override public void exitRule(GrammarParser.actionContext ctx) { }

	@Override public void enterRule(GrammarParser.actionScopeExpressionContext ctx) { }
	@Override public void exitRule(GrammarParser.actionScopeExpressionContext ctx) { }

	@Override public void enterRule(GrammarParser.localsSpecContext ctx) { }
	@Override public void exitRule(GrammarParser.localsSpecContext ctx) { }

	@Override public void enterRule(GrammarParser.modeSpecContext ctx) { }
	@Override public void exitRule(GrammarParser.modeSpecContext ctx) { }

	@Override public void enterRule(GrammarParser.elementsContext ctx) { }
	@Override public void exitRule(GrammarParser.elementsContext ctx) { }

	@Override public void enterRule(GrammarParser.optionContext ctx) { }
	@Override public void exitRule(GrammarParser.optionContext ctx) { }

	@Override public void enterRule(GrammarParser.elementContext ctx) { }
	@Override public void exitRule(GrammarParser.elementContext ctx) { }

	@Override public void enterRule(GrammarParser.elementOptionsContext ctx) { }
	@Override public void exitRule(GrammarParser.elementOptionsContext ctx) { }

	@Override public void enterRule(GrammarParser.lexerElementContext ctx) { }
	@Override public void exitRule(GrammarParser.lexerElementContext ctx) { }

	@Override public void enterRule(GrammarParser.alternativeContext ctx) { }
	@Override public void exitRule(GrammarParser.alternativeContext ctx) { }

	@Override public void enterRule(GrammarParser.lexerActionExprContext ctx) { }
	@Override public void exitRule(GrammarParser.lexerActionExprContext ctx) { }

	@Override public void enterRule(GrammarParser.grammarTypeContext ctx) { }
	@Override public void exitRule(GrammarParser.grammarTypeContext ctx) { }

	@Override public void enterRule(GrammarParser.ruleActionContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleActionContext ctx) { }

	@Override public void enterRule(GrammarParser.ebnfSuffixContext ctx) { }
	@Override public void exitRule(GrammarParser.ebnfSuffixContext ctx) { }

	@Override public void enterRule(GrammarParser.exceptionGroupContext ctx) { }
	@Override public void exitRule(GrammarParser.exceptionGroupContext ctx) { }

	@Override public void enterRule(GrammarParser.rulePrequelContext ctx) { }
	@Override public void exitRule(GrammarParser.rulePrequelContext ctx) { }

	@Override public void enterRule(GrammarParser.lexerBlockContext ctx) { }
	@Override public void exitRule(GrammarParser.lexerBlockContext ctx) { }

	@Override public void enterRule(GrammarParser.setElementContext ctx) { }
	@Override public void exitRule(GrammarParser.setElementContext ctx) { }

	@Override public void enterRule(GrammarParser.blockSetContext ctx) { }
	@Override public void exitRule(GrammarParser.blockSetContext ctx) { }

	@Override public void enterRule(GrammarParser.actionScopeNameContext ctx) { }
	@Override public void exitRule(GrammarParser.actionScopeNameContext ctx) { }

	@Override public void enterRule(GrammarParser.labeledAltContext ctx) { }
	@Override public void exitRule(GrammarParser.labeledAltContext ctx) { }

	@Override public void enterRule(GrammarParser.argActionParameterTypeContext ctx) { }
	@Override public void exitRule(GrammarParser.argActionParameterTypeContext ctx) { }

	@Override public void enterRule(GrammarParser.lexerAtomContext ctx) { }
	@Override public void exitRule(GrammarParser.lexerAtomContext ctx) { }

	@Override public void enterRule(GrammarParser.labeledElementContext ctx) { }
	@Override public void exitRule(GrammarParser.labeledElementContext ctx) { }

	@Override public void enterRule(GrammarParser.lexerRuleBlockContext ctx) { }
	@Override public void exitRule(GrammarParser.lexerRuleBlockContext ctx) { }

	@Override public void enterRule(GrammarParser.finallyClauseContext ctx) { }
	@Override public void exitRule(GrammarParser.finallyClauseContext ctx) { }

	@Override public void enterRule(GrammarParser.ignoredContext ctx) { }
	@Override public void exitRule(GrammarParser.ignoredContext ctx) { }

	@Override public void enterRule(GrammarParser.grammarSpecContext ctx) { }
	@Override public void exitRule(GrammarParser.grammarSpecContext ctx) { }

	@Override public void enterRule(GrammarParser.delegateGrammarContext ctx) { }
	@Override public void exitRule(GrammarParser.delegateGrammarContext ctx) { }

	@Override public void enterRule(GrammarParser.lexerElementsContext ctx) { }
	@Override public void exitRule(GrammarParser.lexerElementsContext ctx) { }

	@Override public void enterRule(GrammarParser.rangeContext ctx) { }
	@Override public void exitRule(GrammarParser.rangeContext ctx) { }

	@Override public void enterRule(GrammarParser.tokenSpecContext ctx) { }
	@Override public void exitRule(GrammarParser.tokenSpecContext ctx) { }

	@Override public void enterRule(GrammarParser.blockContext ctx) { }
	@Override public void exitRule(GrammarParser.blockContext ctx) { }

	@Override public void enterRule(GrammarParser.argActionParameterTypePartContext ctx) { }
	@Override public void exitRule(GrammarParser.argActionParameterTypePartContext ctx) { }

	@Override public void enterRule(GrammarParser.lexerRuleContext ctx) { }
	@Override public void exitRule(GrammarParser.lexerRuleContext ctx) { }

	@Override public void enterRule(GrammarParser.labeledLexerElementContext ctx) { }
	@Override public void exitRule(GrammarParser.labeledLexerElementContext ctx) { }

	@Override public void enterRule(GrammarParser.delegateGrammarsContext ctx) { }
	@Override public void exitRule(GrammarParser.delegateGrammarsContext ctx) { }

	@Override public void enterRule(GrammarParser.actionExpressionContext ctx) { }
	@Override public void exitRule(GrammarParser.actionExpressionContext ctx) { }

	@Override public void enterRule(GrammarParser.rulerefContext ctx) { }
	@Override public void exitRule(GrammarParser.rulerefContext ctx) { }

	@Override public void enterRule(GrammarParser.blockSuffixContext ctx) { }
	@Override public void exitRule(GrammarParser.blockSuffixContext ctx) { }

	@Override public void enterRule(GrammarParser.idContext ctx) { }
	@Override public void exitRule(GrammarParser.idContext ctx) { }

	@Override public void enterRule(GrammarParser.actionBlockContext ctx) { }
	@Override public void exitRule(GrammarParser.actionBlockContext ctx) { }

	@Override public void enterRule(GrammarParser.elementOptionContext ctx) { }
	@Override public void exitRule(GrammarParser.elementOptionContext ctx) { }

	@Override public void enterRule(GrammarParser.ruleContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleContext ctx) { }

	@Override public void enterRule(GrammarParser.exceptionHandlerContext ctx) { }
	@Override public void exitRule(GrammarParser.exceptionHandlerContext ctx) { }

	@Override public void enterRule(GrammarParser.tokensSpecContext ctx) { }
	@Override public void exitRule(GrammarParser.tokensSpecContext ctx) { }

	@Override public void enterRule(GrammarParser.ruleReturnsContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleReturnsContext ctx) { }

	@Override public void enterRule(GrammarParser.qidContext ctx) { }
	@Override public void exitRule(GrammarParser.qidContext ctx) { }

	@Override public void enterRule(GrammarParser.optionsSpecContext ctx) { }
	@Override public void exitRule(GrammarParser.optionsSpecContext ctx) { }

	@Override public void enterRule(GrammarParser.altListContext ctx) { }
	@Override public void exitRule(GrammarParser.altListContext ctx) { }

	@Override public void enterRule(GrammarParser.prequelConstructContext ctx) { }
	@Override public void exitRule(GrammarParser.prequelConstructContext ctx) { }

	@Override public void enterRule(GrammarParser.rulesContext ctx) { }
	@Override public void exitRule(GrammarParser.rulesContext ctx) { }

	@Override public void enterRule(GrammarParser.lexerActionsContext ctx) { }
	@Override public void exitRule(GrammarParser.lexerActionsContext ctx) { }

	@Override public void enterRule(GrammarParser.optionValueContext ctx) { }
	@Override public void exitRule(GrammarParser.optionValueContext ctx) { }

	@Override public void enterRule(GrammarParser.lexerAltContext ctx) { }
	@Override public void exitRule(GrammarParser.lexerAltContext ctx) { }

	@Override public void enterRule(GrammarParser.argActionBlockContext ctx) { }
	@Override public void exitRule(GrammarParser.argActionBlockContext ctx) { }

	@Override public void enterRule(GrammarParser.lexerActionContext ctx) { }
	@Override public void exitRule(GrammarParser.lexerActionContext ctx) { }

	@Override public void enterRule(GrammarParser.ruleModifiersContext ctx) { }
	@Override public void exitRule(GrammarParser.ruleModifiersContext ctx) { }

	@Override public void enterRule(GrammarParser.argActionParametersContext ctx) { }
	@Override public void exitRule(GrammarParser.argActionParametersContext ctx) { }

	@Override public void enterRule(GrammarParser.ebnfContext ctx) { }
	@Override public void exitRule(GrammarParser.ebnfContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext<Token> ctx) { }
	@Override public void exitEveryRule(ParserRuleContext<Token> ctx) { }
	@Override public void visitTerminal(ParserRuleContext<Token> ctx, Token symbol) { }
}