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

public class GrammarParserBaseVisitor<T> extends ParseTreeVisitor<T> implements GrammarParserVisitor<T> {
	@Override public T parserRuleVisit(GrammarParser.parserRuleContext ctx) { visitChildren(ctx); return null; }

	@Override public T atomVisit(GrammarParser.atomContext ctx) { visitChildren(ctx); return null; }

	@Override public T rulePrequelsVisit(GrammarParser.rulePrequelsContext ctx) { visitChildren(ctx); return null; }

	@Override public T ruleBlockVisit(GrammarParser.ruleBlockContext ctx) { visitChildren(ctx); return null; }

	@Override public T notSetVisit(GrammarParser.notSetContext ctx) { visitChildren(ctx); return null; }

	@Override public T lexerAltListVisit(GrammarParser.lexerAltListContext ctx) { visitChildren(ctx); return null; }

	@Override public T argActionParameterVisit(GrammarParser.argActionParameterContext ctx) { visitChildren(ctx); return null; }

	@Override public T ruleModifierVisit(GrammarParser.ruleModifierContext ctx) { visitChildren(ctx); return null; }

	@Override public T ruleAltListVisit(GrammarParser.ruleAltListContext ctx) { visitChildren(ctx); return null; }

	@Override public T terminalVisit(GrammarParser.terminalContext ctx) { visitChildren(ctx); return null; }

	@Override public T throwsSpecVisit(GrammarParser.throwsSpecContext ctx) { visitChildren(ctx); return null; }

	@Override public T actionVisit(GrammarParser.actionContext ctx) { visitChildren(ctx); return null; }

	@Override public T actionScopeExpressionVisit(GrammarParser.actionScopeExpressionContext ctx) { visitChildren(ctx); return null; }

	@Override public T localsSpecVisit(GrammarParser.localsSpecContext ctx) { visitChildren(ctx); return null; }

	@Override public T modeSpecVisit(GrammarParser.modeSpecContext ctx) { visitChildren(ctx); return null; }

	@Override public T elementsVisit(GrammarParser.elementsContext ctx) { visitChildren(ctx); return null; }

	@Override public T optionVisit(GrammarParser.optionContext ctx) { visitChildren(ctx); return null; }

	@Override public T elementVisit(GrammarParser.elementContext ctx) { visitChildren(ctx); return null; }

	@Override public T elementOptionsVisit(GrammarParser.elementOptionsContext ctx) { visitChildren(ctx); return null; }

	@Override public T lexerElementVisit(GrammarParser.lexerElementContext ctx) { visitChildren(ctx); return null; }

	@Override public T alternativeVisit(GrammarParser.alternativeContext ctx) { visitChildren(ctx); return null; }

	@Override public T lexerActionExprVisit(GrammarParser.lexerActionExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T grammarTypeVisit(GrammarParser.grammarTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T ruleActionVisit(GrammarParser.ruleActionContext ctx) { visitChildren(ctx); return null; }

	@Override public T ebnfSuffixVisit(GrammarParser.ebnfSuffixContext ctx) { visitChildren(ctx); return null; }

	@Override public T exceptionGroupVisit(GrammarParser.exceptionGroupContext ctx) { visitChildren(ctx); return null; }

	@Override public T rulePrequelVisit(GrammarParser.rulePrequelContext ctx) { visitChildren(ctx); return null; }

	@Override public T lexerBlockVisit(GrammarParser.lexerBlockContext ctx) { visitChildren(ctx); return null; }

	@Override public T setElementVisit(GrammarParser.setElementContext ctx) { visitChildren(ctx); return null; }

	@Override public T blockSetVisit(GrammarParser.blockSetContext ctx) { visitChildren(ctx); return null; }

	@Override public T actionScopeNameVisit(GrammarParser.actionScopeNameContext ctx) { visitChildren(ctx); return null; }

	@Override public T labeledAltVisit(GrammarParser.labeledAltContext ctx) { visitChildren(ctx); return null; }

	@Override public T argActionParameterTypeVisit(GrammarParser.argActionParameterTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T lexerAtomVisit(GrammarParser.lexerAtomContext ctx) { visitChildren(ctx); return null; }

	@Override public T labeledElementVisit(GrammarParser.labeledElementContext ctx) { visitChildren(ctx); return null; }

	@Override public T lexerRuleBlockVisit(GrammarParser.lexerRuleBlockContext ctx) { visitChildren(ctx); return null; }

	@Override public T finallyClauseVisit(GrammarParser.finallyClauseContext ctx) { visitChildren(ctx); return null; }

	@Override public T ignoredVisit(GrammarParser.ignoredContext ctx) { visitChildren(ctx); return null; }

	@Override public T grammarSpecVisit(GrammarParser.grammarSpecContext ctx) { visitChildren(ctx); return null; }

	@Override public T delegateGrammarVisit(GrammarParser.delegateGrammarContext ctx) { visitChildren(ctx); return null; }

	@Override public T lexerElementsVisit(GrammarParser.lexerElementsContext ctx) { visitChildren(ctx); return null; }

	@Override public T rangeVisit(GrammarParser.rangeContext ctx) { visitChildren(ctx); return null; }

	@Override public T tokenSpecVisit(GrammarParser.tokenSpecContext ctx) { visitChildren(ctx); return null; }

	@Override public T blockVisit(GrammarParser.blockContext ctx) { visitChildren(ctx); return null; }

	@Override public T argActionParameterTypePartVisit(GrammarParser.argActionParameterTypePartContext ctx) { visitChildren(ctx); return null; }

	@Override public T lexerRuleVisit(GrammarParser.lexerRuleContext ctx) { visitChildren(ctx); return null; }

	@Override public T labeledLexerElementVisit(GrammarParser.labeledLexerElementContext ctx) { visitChildren(ctx); return null; }

	@Override public T delegateGrammarsVisit(GrammarParser.delegateGrammarsContext ctx) { visitChildren(ctx); return null; }

	@Override public T actionExpressionVisit(GrammarParser.actionExpressionContext ctx) { visitChildren(ctx); return null; }

	@Override public T rulerefVisit(GrammarParser.rulerefContext ctx) { visitChildren(ctx); return null; }

	@Override public T blockSuffixVisit(GrammarParser.blockSuffixContext ctx) { visitChildren(ctx); return null; }

	@Override public T idVisit(GrammarParser.idContext ctx) { visitChildren(ctx); return null; }

	@Override public T actionBlockVisit(GrammarParser.actionBlockContext ctx) { visitChildren(ctx); return null; }

	@Override public T elementOptionVisit(GrammarParser.elementOptionContext ctx) { visitChildren(ctx); return null; }

	@Override public T ruleVisit(GrammarParser.ruleContext ctx) { visitChildren(ctx); return null; }

	@Override public T exceptionHandlerVisit(GrammarParser.exceptionHandlerContext ctx) { visitChildren(ctx); return null; }

	@Override public T tokensSpecVisit(GrammarParser.tokensSpecContext ctx) { visitChildren(ctx); return null; }

	@Override public T ruleReturnsVisit(GrammarParser.ruleReturnsContext ctx) { visitChildren(ctx); return null; }

	@Override public T qidVisit(GrammarParser.qidContext ctx) { visitChildren(ctx); return null; }

	@Override public T optionsSpecVisit(GrammarParser.optionsSpecContext ctx) { visitChildren(ctx); return null; }

	@Override public T altListVisit(GrammarParser.altListContext ctx) { visitChildren(ctx); return null; }

	@Override public T prequelConstructVisit(GrammarParser.prequelConstructContext ctx) { visitChildren(ctx); return null; }

	@Override public T rulesVisit(GrammarParser.rulesContext ctx) { visitChildren(ctx); return null; }

	@Override public T lexerActionsVisit(GrammarParser.lexerActionsContext ctx) { visitChildren(ctx); return null; }

	@Override public T optionValueVisit(GrammarParser.optionValueContext ctx) { visitChildren(ctx); return null; }

	@Override public T lexerAltVisit(GrammarParser.lexerAltContext ctx) { visitChildren(ctx); return null; }

	@Override public T argActionBlockVisit(GrammarParser.argActionBlockContext ctx) { visitChildren(ctx); return null; }

	@Override public T lexerActionVisit(GrammarParser.lexerActionContext ctx) { visitChildren(ctx); return null; }

	@Override public T ruleModifiersVisit(GrammarParser.ruleModifiersContext ctx) { visitChildren(ctx); return null; }

	@Override public T argActionParametersVisit(GrammarParser.argActionParametersContext ctx) { visitChildren(ctx); return null; }

	@Override public T ebnfVisit(GrammarParser.ebnfContext ctx) { visitChildren(ctx); return null; }
}