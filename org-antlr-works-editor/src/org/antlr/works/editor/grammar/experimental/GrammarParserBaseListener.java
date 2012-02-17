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

public class GrammarParserBaseListener implements GrammarParserListener {
	@Override public void parserRuleEnter(GrammarParser.parserRuleContext ctx) { }
	@Override public void parserRuleExit(GrammarParser.parserRuleContext ctx) { }

	@Override public void atomEnter(GrammarParser.atomContext ctx) { }
	@Override public void atomExit(GrammarParser.atomContext ctx) { }

	@Override public void rulePrequelsEnter(GrammarParser.rulePrequelsContext ctx) { }
	@Override public void rulePrequelsExit(GrammarParser.rulePrequelsContext ctx) { }

	@Override public void ruleBlockEnter(GrammarParser.ruleBlockContext ctx) { }
	@Override public void ruleBlockExit(GrammarParser.ruleBlockContext ctx) { }

	@Override public void notSetEnter(GrammarParser.notSetContext ctx) { }
	@Override public void notSetExit(GrammarParser.notSetContext ctx) { }

	@Override public void lexerAltListEnter(GrammarParser.lexerAltListContext ctx) { }
	@Override public void lexerAltListExit(GrammarParser.lexerAltListContext ctx) { }

	@Override public void argActionParameterEnter(GrammarParser.argActionParameterContext ctx) { }
	@Override public void argActionParameterExit(GrammarParser.argActionParameterContext ctx) { }

	@Override public void ruleModifierEnter(GrammarParser.ruleModifierContext ctx) { }
	@Override public void ruleModifierExit(GrammarParser.ruleModifierContext ctx) { }

	@Override public void ruleAltListEnter(GrammarParser.ruleAltListContext ctx) { }
	@Override public void ruleAltListExit(GrammarParser.ruleAltListContext ctx) { }

	@Override public void terminalEnter(GrammarParser.terminalContext ctx) { }
	@Override public void terminalExit(GrammarParser.terminalContext ctx) { }

	@Override public void throwsSpecEnter(GrammarParser.throwsSpecContext ctx) { }
	@Override public void throwsSpecExit(GrammarParser.throwsSpecContext ctx) { }

	@Override public void actionEnter(GrammarParser.actionContext ctx) { }
	@Override public void actionExit(GrammarParser.actionContext ctx) { }

	@Override public void actionScopeExpressionEnter(GrammarParser.actionScopeExpressionContext ctx) { }
	@Override public void actionScopeExpressionExit(GrammarParser.actionScopeExpressionContext ctx) { }

	@Override public void localsSpecEnter(GrammarParser.localsSpecContext ctx) { }
	@Override public void localsSpecExit(GrammarParser.localsSpecContext ctx) { }

	@Override public void modeSpecEnter(GrammarParser.modeSpecContext ctx) { }
	@Override public void modeSpecExit(GrammarParser.modeSpecContext ctx) { }

	@Override public void elementsEnter(GrammarParser.elementsContext ctx) { }
	@Override public void elementsExit(GrammarParser.elementsContext ctx) { }

	@Override public void optionEnter(GrammarParser.optionContext ctx) { }
	@Override public void optionExit(GrammarParser.optionContext ctx) { }

	@Override public void elementEnter(GrammarParser.elementContext ctx) { }
	@Override public void elementExit(GrammarParser.elementContext ctx) { }

	@Override public void elementOptionsEnter(GrammarParser.elementOptionsContext ctx) { }
	@Override public void elementOptionsExit(GrammarParser.elementOptionsContext ctx) { }

	@Override public void lexerElementEnter(GrammarParser.lexerElementContext ctx) { }
	@Override public void lexerElementExit(GrammarParser.lexerElementContext ctx) { }

	@Override public void alternativeEnter(GrammarParser.alternativeContext ctx) { }
	@Override public void alternativeExit(GrammarParser.alternativeContext ctx) { }

	@Override public void lexerActionExprEnter(GrammarParser.lexerActionExprContext ctx) { }
	@Override public void lexerActionExprExit(GrammarParser.lexerActionExprContext ctx) { }

	@Override public void grammarTypeEnter(GrammarParser.grammarTypeContext ctx) { }
	@Override public void grammarTypeExit(GrammarParser.grammarTypeContext ctx) { }

	@Override public void ruleActionEnter(GrammarParser.ruleActionContext ctx) { }
	@Override public void ruleActionExit(GrammarParser.ruleActionContext ctx) { }

	@Override public void ebnfSuffixEnter(GrammarParser.ebnfSuffixContext ctx) { }
	@Override public void ebnfSuffixExit(GrammarParser.ebnfSuffixContext ctx) { }

	@Override public void exceptionGroupEnter(GrammarParser.exceptionGroupContext ctx) { }
	@Override public void exceptionGroupExit(GrammarParser.exceptionGroupContext ctx) { }

	@Override public void rulePrequelEnter(GrammarParser.rulePrequelContext ctx) { }
	@Override public void rulePrequelExit(GrammarParser.rulePrequelContext ctx) { }

	@Override public void lexerBlockEnter(GrammarParser.lexerBlockContext ctx) { }
	@Override public void lexerBlockExit(GrammarParser.lexerBlockContext ctx) { }

	@Override public void setElementEnter(GrammarParser.setElementContext ctx) { }
	@Override public void setElementExit(GrammarParser.setElementContext ctx) { }

	@Override public void blockSetEnter(GrammarParser.blockSetContext ctx) { }
	@Override public void blockSetExit(GrammarParser.blockSetContext ctx) { }

	@Override public void actionScopeNameEnter(GrammarParser.actionScopeNameContext ctx) { }
	@Override public void actionScopeNameExit(GrammarParser.actionScopeNameContext ctx) { }

	@Override public void labeledAltEnter(GrammarParser.labeledAltContext ctx) { }
	@Override public void labeledAltExit(GrammarParser.labeledAltContext ctx) { }

	@Override public void argActionParameterTypeEnter(GrammarParser.argActionParameterTypeContext ctx) { }
	@Override public void argActionParameterTypeExit(GrammarParser.argActionParameterTypeContext ctx) { }

	@Override public void lexerAtomEnter(GrammarParser.lexerAtomContext ctx) { }
	@Override public void lexerAtomExit(GrammarParser.lexerAtomContext ctx) { }

	@Override public void labeledElementEnter(GrammarParser.labeledElementContext ctx) { }
	@Override public void labeledElementExit(GrammarParser.labeledElementContext ctx) { }

	@Override public void lexerRuleBlockEnter(GrammarParser.lexerRuleBlockContext ctx) { }
	@Override public void lexerRuleBlockExit(GrammarParser.lexerRuleBlockContext ctx) { }

	@Override public void finallyClauseEnter(GrammarParser.finallyClauseContext ctx) { }
	@Override public void finallyClauseExit(GrammarParser.finallyClauseContext ctx) { }

	@Override public void ignoredEnter(GrammarParser.ignoredContext ctx) { }
	@Override public void ignoredExit(GrammarParser.ignoredContext ctx) { }

	@Override public void grammarSpecEnter(GrammarParser.grammarSpecContext ctx) { }
	@Override public void grammarSpecExit(GrammarParser.grammarSpecContext ctx) { }

	@Override public void delegateGrammarEnter(GrammarParser.delegateGrammarContext ctx) { }
	@Override public void delegateGrammarExit(GrammarParser.delegateGrammarContext ctx) { }

	@Override public void lexerElementsEnter(GrammarParser.lexerElementsContext ctx) { }
	@Override public void lexerElementsExit(GrammarParser.lexerElementsContext ctx) { }

	@Override public void rangeEnter(GrammarParser.rangeContext ctx) { }
	@Override public void rangeExit(GrammarParser.rangeContext ctx) { }

	@Override public void tokenSpecEnter(GrammarParser.tokenSpecContext ctx) { }
	@Override public void tokenSpecExit(GrammarParser.tokenSpecContext ctx) { }

	@Override public void blockEnter(GrammarParser.blockContext ctx) { }
	@Override public void blockExit(GrammarParser.blockContext ctx) { }

	@Override public void argActionParameterTypePartEnter(GrammarParser.argActionParameterTypePartContext ctx) { }
	@Override public void argActionParameterTypePartExit(GrammarParser.argActionParameterTypePartContext ctx) { }

	@Override public void lexerRuleEnter(GrammarParser.lexerRuleContext ctx) { }
	@Override public void lexerRuleExit(GrammarParser.lexerRuleContext ctx) { }

	@Override public void labeledLexerElementEnter(GrammarParser.labeledLexerElementContext ctx) { }
	@Override public void labeledLexerElementExit(GrammarParser.labeledLexerElementContext ctx) { }

	@Override public void delegateGrammarsEnter(GrammarParser.delegateGrammarsContext ctx) { }
	@Override public void delegateGrammarsExit(GrammarParser.delegateGrammarsContext ctx) { }

	@Override public void actionExpressionEnter(GrammarParser.actionExpressionContext ctx) { }
	@Override public void actionExpressionExit(GrammarParser.actionExpressionContext ctx) { }

	@Override public void rulerefEnter(GrammarParser.rulerefContext ctx) { }
	@Override public void rulerefExit(GrammarParser.rulerefContext ctx) { }

	@Override public void blockSuffixEnter(GrammarParser.blockSuffixContext ctx) { }
	@Override public void blockSuffixExit(GrammarParser.blockSuffixContext ctx) { }

	@Override public void idEnter(GrammarParser.idContext ctx) { }
	@Override public void idExit(GrammarParser.idContext ctx) { }

	@Override public void actionBlockEnter(GrammarParser.actionBlockContext ctx) { }
	@Override public void actionBlockExit(GrammarParser.actionBlockContext ctx) { }

	@Override public void elementOptionEnter(GrammarParser.elementOptionContext ctx) { }
	@Override public void elementOptionExit(GrammarParser.elementOptionContext ctx) { }

	@Override public void ruleEnter(GrammarParser.ruleContext ctx) { }
	@Override public void ruleExit(GrammarParser.ruleContext ctx) { }

	@Override public void exceptionHandlerEnter(GrammarParser.exceptionHandlerContext ctx) { }
	@Override public void exceptionHandlerExit(GrammarParser.exceptionHandlerContext ctx) { }

	@Override public void tokensSpecEnter(GrammarParser.tokensSpecContext ctx) { }
	@Override public void tokensSpecExit(GrammarParser.tokensSpecContext ctx) { }

	@Override public void ruleReturnsEnter(GrammarParser.ruleReturnsContext ctx) { }
	@Override public void ruleReturnsExit(GrammarParser.ruleReturnsContext ctx) { }

	@Override public void qidEnter(GrammarParser.qidContext ctx) { }
	@Override public void qidExit(GrammarParser.qidContext ctx) { }

	@Override public void optionsSpecEnter(GrammarParser.optionsSpecContext ctx) { }
	@Override public void optionsSpecExit(GrammarParser.optionsSpecContext ctx) { }

	@Override public void altListEnter(GrammarParser.altListContext ctx) { }
	@Override public void altListExit(GrammarParser.altListContext ctx) { }

	@Override public void prequelConstructEnter(GrammarParser.prequelConstructContext ctx) { }
	@Override public void prequelConstructExit(GrammarParser.prequelConstructContext ctx) { }

	@Override public void rulesEnter(GrammarParser.rulesContext ctx) { }
	@Override public void rulesExit(GrammarParser.rulesContext ctx) { }

	@Override public void lexerActionsEnter(GrammarParser.lexerActionsContext ctx) { }
	@Override public void lexerActionsExit(GrammarParser.lexerActionsContext ctx) { }

	@Override public void optionValueEnter(GrammarParser.optionValueContext ctx) { }
	@Override public void optionValueExit(GrammarParser.optionValueContext ctx) { }

	@Override public void lexerAltEnter(GrammarParser.lexerAltContext ctx) { }
	@Override public void lexerAltExit(GrammarParser.lexerAltContext ctx) { }

	@Override public void argActionBlockEnter(GrammarParser.argActionBlockContext ctx) { }
	@Override public void argActionBlockExit(GrammarParser.argActionBlockContext ctx) { }

	@Override public void lexerActionEnter(GrammarParser.lexerActionContext ctx) { }
	@Override public void lexerActionExit(GrammarParser.lexerActionContext ctx) { }

	@Override public void ruleModifiersEnter(GrammarParser.ruleModifiersContext ctx) { }
	@Override public void ruleModifiersExit(GrammarParser.ruleModifiersContext ctx) { }

	@Override public void argActionParametersEnter(GrammarParser.argActionParametersContext ctx) { }
	@Override public void argActionParametersExit(GrammarParser.argActionParametersContext ctx) { }

	@Override public void ebnfEnter(GrammarParser.ebnfContext ctx) { }
	@Override public void ebnfExit(GrammarParser.ebnfContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext<Token> ctx) { }
	@Override public void exitEveryRule(ParserRuleContext<Token> ctx) { }
	@Override public void visitTerminal(ParserRuleContext<Token> ctx, Token symbol) { }
}