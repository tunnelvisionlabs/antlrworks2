/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface GrammarParserVisitor<T> {
	T parserRuleVisit(GrammarParser.parserRuleContext ctx);

	T atomVisit(GrammarParser.atomContext ctx);

	T rulePrequelsVisit(GrammarParser.rulePrequelsContext ctx);

	T ruleBlockVisit(GrammarParser.ruleBlockContext ctx);

	T notSetVisit(GrammarParser.notSetContext ctx);

	T lexerAltListVisit(GrammarParser.lexerAltListContext ctx);

	T argActionParameterVisit(GrammarParser.argActionParameterContext ctx);

	T ruleModifierVisit(GrammarParser.ruleModifierContext ctx);

	T ruleAltListVisit(GrammarParser.ruleAltListContext ctx);

	T terminalVisit(GrammarParser.terminalContext ctx);

	T throwsSpecVisit(GrammarParser.throwsSpecContext ctx);

	T actionVisit(GrammarParser.actionContext ctx);

	T actionScopeExpressionVisit(GrammarParser.actionScopeExpressionContext ctx);

	T localsSpecVisit(GrammarParser.localsSpecContext ctx);

	T modeSpecVisit(GrammarParser.modeSpecContext ctx);

	T elementsVisit(GrammarParser.elementsContext ctx);

	T optionVisit(GrammarParser.optionContext ctx);

	T elementVisit(GrammarParser.elementContext ctx);

	T elementOptionsVisit(GrammarParser.elementOptionsContext ctx);

	T lexerElementVisit(GrammarParser.lexerElementContext ctx);

	T alternativeVisit(GrammarParser.alternativeContext ctx);

	T lexerActionExprVisit(GrammarParser.lexerActionExprContext ctx);

	T grammarTypeVisit(GrammarParser.grammarTypeContext ctx);

	T ruleActionVisit(GrammarParser.ruleActionContext ctx);

	T ebnfSuffixVisit(GrammarParser.ebnfSuffixContext ctx);

	T exceptionGroupVisit(GrammarParser.exceptionGroupContext ctx);

	T rulePrequelVisit(GrammarParser.rulePrequelContext ctx);

	T lexerBlockVisit(GrammarParser.lexerBlockContext ctx);

	T setElementVisit(GrammarParser.setElementContext ctx);

	T blockSetVisit(GrammarParser.blockSetContext ctx);

	T actionScopeNameVisit(GrammarParser.actionScopeNameContext ctx);

	T labeledAltVisit(GrammarParser.labeledAltContext ctx);

	T argActionParameterTypeVisit(GrammarParser.argActionParameterTypeContext ctx);

	T lexerAtomVisit(GrammarParser.lexerAtomContext ctx);

	T labeledElementVisit(GrammarParser.labeledElementContext ctx);

	T lexerRuleBlockVisit(GrammarParser.lexerRuleBlockContext ctx);

	T finallyClauseVisit(GrammarParser.finallyClauseContext ctx);

	T ignoredVisit(GrammarParser.ignoredContext ctx);

	T grammarSpecVisit(GrammarParser.grammarSpecContext ctx);

	T delegateGrammarVisit(GrammarParser.delegateGrammarContext ctx);

	T lexerElementsVisit(GrammarParser.lexerElementsContext ctx);

	T rangeVisit(GrammarParser.rangeContext ctx);

	T tokenSpecVisit(GrammarParser.tokenSpecContext ctx);

	T blockVisit(GrammarParser.blockContext ctx);

	T argActionParameterTypePartVisit(GrammarParser.argActionParameterTypePartContext ctx);

	T lexerRuleVisit(GrammarParser.lexerRuleContext ctx);

	T labeledLexerElementVisit(GrammarParser.labeledLexerElementContext ctx);

	T delegateGrammarsVisit(GrammarParser.delegateGrammarsContext ctx);

	T actionExpressionVisit(GrammarParser.actionExpressionContext ctx);

	T rulerefVisit(GrammarParser.rulerefContext ctx);

	T blockSuffixVisit(GrammarParser.blockSuffixContext ctx);

	T idVisit(GrammarParser.idContext ctx);

	T actionBlockVisit(GrammarParser.actionBlockContext ctx);

	T elementOptionVisit(GrammarParser.elementOptionContext ctx);

	T ruleVisit(GrammarParser.ruleContext ctx);

	T exceptionHandlerVisit(GrammarParser.exceptionHandlerContext ctx);

	T tokensSpecVisit(GrammarParser.tokensSpecContext ctx);

	T ruleReturnsVisit(GrammarParser.ruleReturnsContext ctx);

	T qidVisit(GrammarParser.qidContext ctx);

	T optionsSpecVisit(GrammarParser.optionsSpecContext ctx);

	T altListVisit(GrammarParser.altListContext ctx);

	T prequelConstructVisit(GrammarParser.prequelConstructContext ctx);

	T rulesVisit(GrammarParser.rulesContext ctx);

	T lexerActionsVisit(GrammarParser.lexerActionsContext ctx);

	T optionValueVisit(GrammarParser.optionValueContext ctx);

	T lexerAltVisit(GrammarParser.lexerAltContext ctx);

	T argActionBlockVisit(GrammarParser.argActionBlockContext ctx);

	T lexerActionVisit(GrammarParser.lexerActionContext ctx);

	T ruleModifiersVisit(GrammarParser.ruleModifiersContext ctx);

	T argActionParametersVisit(GrammarParser.argActionParametersContext ctx);

	T ebnfVisit(GrammarParser.ebnfContext ctx);
}