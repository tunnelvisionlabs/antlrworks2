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

public interface GrammarParserListener extends ParseTreeListener<Token> {
	void parserRuleEnter(GrammarParser.parserRuleContext ctx);
	void parserRuleExit(GrammarParser.parserRuleContext ctx);

	void atomEnter(GrammarParser.atomContext ctx);
	void atomExit(GrammarParser.atomContext ctx);

	void rulePrequelsEnter(GrammarParser.rulePrequelsContext ctx);
	void rulePrequelsExit(GrammarParser.rulePrequelsContext ctx);

	void ruleBlockEnter(GrammarParser.ruleBlockContext ctx);
	void ruleBlockExit(GrammarParser.ruleBlockContext ctx);

	void notSetEnter(GrammarParser.notSetContext ctx);
	void notSetExit(GrammarParser.notSetContext ctx);

	void lexerAltListEnter(GrammarParser.lexerAltListContext ctx);
	void lexerAltListExit(GrammarParser.lexerAltListContext ctx);

	void argActionParameterEnter(GrammarParser.argActionParameterContext ctx);
	void argActionParameterExit(GrammarParser.argActionParameterContext ctx);

	void ruleModifierEnter(GrammarParser.ruleModifierContext ctx);
	void ruleModifierExit(GrammarParser.ruleModifierContext ctx);

	void ruleAltListEnter(GrammarParser.ruleAltListContext ctx);
	void ruleAltListExit(GrammarParser.ruleAltListContext ctx);

	void terminalEnter(GrammarParser.terminalContext ctx);
	void terminalExit(GrammarParser.terminalContext ctx);

	void throwsSpecEnter(GrammarParser.throwsSpecContext ctx);
	void throwsSpecExit(GrammarParser.throwsSpecContext ctx);

	void actionEnter(GrammarParser.actionContext ctx);
	void actionExit(GrammarParser.actionContext ctx);

	void actionScopeExpressionEnter(GrammarParser.actionScopeExpressionContext ctx);
	void actionScopeExpressionExit(GrammarParser.actionScopeExpressionContext ctx);

	void localsSpecEnter(GrammarParser.localsSpecContext ctx);
	void localsSpecExit(GrammarParser.localsSpecContext ctx);

	void modeSpecEnter(GrammarParser.modeSpecContext ctx);
	void modeSpecExit(GrammarParser.modeSpecContext ctx);

	void elementsEnter(GrammarParser.elementsContext ctx);
	void elementsExit(GrammarParser.elementsContext ctx);

	void optionEnter(GrammarParser.optionContext ctx);
	void optionExit(GrammarParser.optionContext ctx);

	void elementEnter(GrammarParser.elementContext ctx);
	void elementExit(GrammarParser.elementContext ctx);

	void elementOptionsEnter(GrammarParser.elementOptionsContext ctx);
	void elementOptionsExit(GrammarParser.elementOptionsContext ctx);

	void lexerElementEnter(GrammarParser.lexerElementContext ctx);
	void lexerElementExit(GrammarParser.lexerElementContext ctx);

	void alternativeEnter(GrammarParser.alternativeContext ctx);
	void alternativeExit(GrammarParser.alternativeContext ctx);

	void lexerActionExprEnter(GrammarParser.lexerActionExprContext ctx);
	void lexerActionExprExit(GrammarParser.lexerActionExprContext ctx);

	void grammarTypeEnter(GrammarParser.grammarTypeContext ctx);
	void grammarTypeExit(GrammarParser.grammarTypeContext ctx);

	void ruleActionEnter(GrammarParser.ruleActionContext ctx);
	void ruleActionExit(GrammarParser.ruleActionContext ctx);

	void ebnfSuffixEnter(GrammarParser.ebnfSuffixContext ctx);
	void ebnfSuffixExit(GrammarParser.ebnfSuffixContext ctx);

	void exceptionGroupEnter(GrammarParser.exceptionGroupContext ctx);
	void exceptionGroupExit(GrammarParser.exceptionGroupContext ctx);

	void rulePrequelEnter(GrammarParser.rulePrequelContext ctx);
	void rulePrequelExit(GrammarParser.rulePrequelContext ctx);

	void lexerBlockEnter(GrammarParser.lexerBlockContext ctx);
	void lexerBlockExit(GrammarParser.lexerBlockContext ctx);

	void setElementEnter(GrammarParser.setElementContext ctx);
	void setElementExit(GrammarParser.setElementContext ctx);

	void blockSetEnter(GrammarParser.blockSetContext ctx);
	void blockSetExit(GrammarParser.blockSetContext ctx);

	void actionScopeNameEnter(GrammarParser.actionScopeNameContext ctx);
	void actionScopeNameExit(GrammarParser.actionScopeNameContext ctx);

	void labeledAltEnter(GrammarParser.labeledAltContext ctx);
	void labeledAltExit(GrammarParser.labeledAltContext ctx);

	void argActionParameterTypeEnter(GrammarParser.argActionParameterTypeContext ctx);
	void argActionParameterTypeExit(GrammarParser.argActionParameterTypeContext ctx);

	void lexerAtomEnter(GrammarParser.lexerAtomContext ctx);
	void lexerAtomExit(GrammarParser.lexerAtomContext ctx);

	void labeledElementEnter(GrammarParser.labeledElementContext ctx);
	void labeledElementExit(GrammarParser.labeledElementContext ctx);

	void lexerRuleBlockEnter(GrammarParser.lexerRuleBlockContext ctx);
	void lexerRuleBlockExit(GrammarParser.lexerRuleBlockContext ctx);

	void finallyClauseEnter(GrammarParser.finallyClauseContext ctx);
	void finallyClauseExit(GrammarParser.finallyClauseContext ctx);

	void ignoredEnter(GrammarParser.ignoredContext ctx);
	void ignoredExit(GrammarParser.ignoredContext ctx);

	void grammarSpecEnter(GrammarParser.grammarSpecContext ctx);
	void grammarSpecExit(GrammarParser.grammarSpecContext ctx);

	void delegateGrammarEnter(GrammarParser.delegateGrammarContext ctx);
	void delegateGrammarExit(GrammarParser.delegateGrammarContext ctx);

	void lexerElementsEnter(GrammarParser.lexerElementsContext ctx);
	void lexerElementsExit(GrammarParser.lexerElementsContext ctx);

	void rangeEnter(GrammarParser.rangeContext ctx);
	void rangeExit(GrammarParser.rangeContext ctx);

	void tokenSpecEnter(GrammarParser.tokenSpecContext ctx);
	void tokenSpecExit(GrammarParser.tokenSpecContext ctx);

	void blockEnter(GrammarParser.blockContext ctx);
	void blockExit(GrammarParser.blockContext ctx);

	void argActionParameterTypePartEnter(GrammarParser.argActionParameterTypePartContext ctx);
	void argActionParameterTypePartExit(GrammarParser.argActionParameterTypePartContext ctx);

	void lexerRuleEnter(GrammarParser.lexerRuleContext ctx);
	void lexerRuleExit(GrammarParser.lexerRuleContext ctx);

	void labeledLexerElementEnter(GrammarParser.labeledLexerElementContext ctx);
	void labeledLexerElementExit(GrammarParser.labeledLexerElementContext ctx);

	void delegateGrammarsEnter(GrammarParser.delegateGrammarsContext ctx);
	void delegateGrammarsExit(GrammarParser.delegateGrammarsContext ctx);

	void actionExpressionEnter(GrammarParser.actionExpressionContext ctx);
	void actionExpressionExit(GrammarParser.actionExpressionContext ctx);

	void rulerefEnter(GrammarParser.rulerefContext ctx);
	void rulerefExit(GrammarParser.rulerefContext ctx);

	void blockSuffixEnter(GrammarParser.blockSuffixContext ctx);
	void blockSuffixExit(GrammarParser.blockSuffixContext ctx);

	void idEnter(GrammarParser.idContext ctx);
	void idExit(GrammarParser.idContext ctx);

	void actionBlockEnter(GrammarParser.actionBlockContext ctx);
	void actionBlockExit(GrammarParser.actionBlockContext ctx);

	void elementOptionEnter(GrammarParser.elementOptionContext ctx);
	void elementOptionExit(GrammarParser.elementOptionContext ctx);

	void ruleEnter(GrammarParser.ruleContext ctx);
	void ruleExit(GrammarParser.ruleContext ctx);

	void exceptionHandlerEnter(GrammarParser.exceptionHandlerContext ctx);
	void exceptionHandlerExit(GrammarParser.exceptionHandlerContext ctx);

	void tokensSpecEnter(GrammarParser.tokensSpecContext ctx);
	void tokensSpecExit(GrammarParser.tokensSpecContext ctx);

	void ruleReturnsEnter(GrammarParser.ruleReturnsContext ctx);
	void ruleReturnsExit(GrammarParser.ruleReturnsContext ctx);

	void qidEnter(GrammarParser.qidContext ctx);
	void qidExit(GrammarParser.qidContext ctx);

	void optionsSpecEnter(GrammarParser.optionsSpecContext ctx);
	void optionsSpecExit(GrammarParser.optionsSpecContext ctx);

	void altListEnter(GrammarParser.altListContext ctx);
	void altListExit(GrammarParser.altListContext ctx);

	void prequelConstructEnter(GrammarParser.prequelConstructContext ctx);
	void prequelConstructExit(GrammarParser.prequelConstructContext ctx);

	void rulesEnter(GrammarParser.rulesContext ctx);
	void rulesExit(GrammarParser.rulesContext ctx);

	void lexerActionsEnter(GrammarParser.lexerActionsContext ctx);
	void lexerActionsExit(GrammarParser.lexerActionsContext ctx);

	void optionValueEnter(GrammarParser.optionValueContext ctx);
	void optionValueExit(GrammarParser.optionValueContext ctx);

	void lexerAltEnter(GrammarParser.lexerAltContext ctx);
	void lexerAltExit(GrammarParser.lexerAltContext ctx);

	void argActionBlockEnter(GrammarParser.argActionBlockContext ctx);
	void argActionBlockExit(GrammarParser.argActionBlockContext ctx);

	void lexerActionEnter(GrammarParser.lexerActionContext ctx);
	void lexerActionExit(GrammarParser.lexerActionContext ctx);

	void ruleModifiersEnter(GrammarParser.ruleModifiersContext ctx);
	void ruleModifiersExit(GrammarParser.ruleModifiersContext ctx);

	void argActionParametersEnter(GrammarParser.argActionParametersContext ctx);
	void argActionParametersExit(GrammarParser.argActionParametersContext ctx);

	void ebnfEnter(GrammarParser.ebnfContext ctx);
	void ebnfExit(GrammarParser.ebnfContext ctx);
}