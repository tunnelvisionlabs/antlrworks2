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
	void enterRuleSpec(GrammarParser.RuleSpecContext ctx);
	void exitRuleSpec(GrammarParser.RuleSpecContext ctx);

	void enterAtom(GrammarParser.AtomContext ctx);
	void exitAtom(GrammarParser.AtomContext ctx);

	void enterRulePrequels(GrammarParser.RulePrequelsContext ctx);
	void exitRulePrequels(GrammarParser.RulePrequelsContext ctx);

	void enterRuleBlock(GrammarParser.RuleBlockContext ctx);
	void exitRuleBlock(GrammarParser.RuleBlockContext ctx);

	void enterNotSet(GrammarParser.NotSetContext ctx);
	void exitNotSet(GrammarParser.NotSetContext ctx);

	void enterLexerCommands(GrammarParser.LexerCommandsContext ctx);
	void exitLexerCommands(GrammarParser.LexerCommandsContext ctx);

	void enterLexerAltList(GrammarParser.LexerAltListContext ctx);
	void exitLexerAltList(GrammarParser.LexerAltListContext ctx);

	void enterArgActionParameter(GrammarParser.ArgActionParameterContext ctx);
	void exitArgActionParameter(GrammarParser.ArgActionParameterContext ctx);

	void enterRuleModifier(GrammarParser.RuleModifierContext ctx);
	void exitRuleModifier(GrammarParser.RuleModifierContext ctx);

	void enterRuleAltList(GrammarParser.RuleAltListContext ctx);
	void exitRuleAltList(GrammarParser.RuleAltListContext ctx);

	void enterTerminal(GrammarParser.TerminalContext ctx);
	void exitTerminal(GrammarParser.TerminalContext ctx);

	void enterLexerCommand(GrammarParser.LexerCommandContext ctx);
	void exitLexerCommand(GrammarParser.LexerCommandContext ctx);

	void enterThrowsSpec(GrammarParser.ThrowsSpecContext ctx);
	void exitThrowsSpec(GrammarParser.ThrowsSpecContext ctx);

	void enterAction(GrammarParser.ActionContext ctx);
	void exitAction(GrammarParser.ActionContext ctx);

	void enterActionScopeExpression(GrammarParser.ActionScopeExpressionContext ctx);
	void exitActionScopeExpression(GrammarParser.ActionScopeExpressionContext ctx);

	void enterLocalsSpec(GrammarParser.LocalsSpecContext ctx);
	void exitLocalsSpec(GrammarParser.LocalsSpecContext ctx);

	void enterModeSpec(GrammarParser.ModeSpecContext ctx);
	void exitModeSpec(GrammarParser.ModeSpecContext ctx);

	void enterElements(GrammarParser.ElementsContext ctx);
	void exitElements(GrammarParser.ElementsContext ctx);

	void enterOption(GrammarParser.OptionContext ctx);
	void exitOption(GrammarParser.OptionContext ctx);

	void enterElement(GrammarParser.ElementContext ctx);
	void exitElement(GrammarParser.ElementContext ctx);

	void enterElementOptions(GrammarParser.ElementOptionsContext ctx);
	void exitElementOptions(GrammarParser.ElementOptionsContext ctx);

	void enterLexerElement(GrammarParser.LexerElementContext ctx);
	void exitLexerElement(GrammarParser.LexerElementContext ctx);

	void enterAlternative(GrammarParser.AlternativeContext ctx);
	void exitAlternative(GrammarParser.AlternativeContext ctx);

	void enterParserRuleSpec(GrammarParser.ParserRuleSpecContext ctx);
	void exitParserRuleSpec(GrammarParser.ParserRuleSpecContext ctx);

	void enterGrammarType(GrammarParser.GrammarTypeContext ctx);
	void exitGrammarType(GrammarParser.GrammarTypeContext ctx);

	void enterRuleAction(GrammarParser.RuleActionContext ctx);
	void exitRuleAction(GrammarParser.RuleActionContext ctx);

	void enterEbnfSuffix(GrammarParser.EbnfSuffixContext ctx);
	void exitEbnfSuffix(GrammarParser.EbnfSuffixContext ctx);

	void enterExceptionGroup(GrammarParser.ExceptionGroupContext ctx);
	void exitExceptionGroup(GrammarParser.ExceptionGroupContext ctx);

	void enterRulePrequel(GrammarParser.RulePrequelContext ctx);
	void exitRulePrequel(GrammarParser.RulePrequelContext ctx);

	void enterLexerBlock(GrammarParser.LexerBlockContext ctx);
	void exitLexerBlock(GrammarParser.LexerBlockContext ctx);

	void enterSetElement(GrammarParser.SetElementContext ctx);
	void exitSetElement(GrammarParser.SetElementContext ctx);

	void enterBlockSet(GrammarParser.BlockSetContext ctx);
	void exitBlockSet(GrammarParser.BlockSetContext ctx);

	void enterActionScopeName(GrammarParser.ActionScopeNameContext ctx);
	void exitActionScopeName(GrammarParser.ActionScopeNameContext ctx);

	void enterLabeledAlt(GrammarParser.LabeledAltContext ctx);
	void exitLabeledAlt(GrammarParser.LabeledAltContext ctx);

	void enterArgActionParameterType(GrammarParser.ArgActionParameterTypeContext ctx);
	void exitArgActionParameterType(GrammarParser.ArgActionParameterTypeContext ctx);

	void enterLexerAtom(GrammarParser.LexerAtomContext ctx);
	void exitLexerAtom(GrammarParser.LexerAtomContext ctx);

	void enterLabeledElement(GrammarParser.LabeledElementContext ctx);
	void exitLabeledElement(GrammarParser.LabeledElementContext ctx);

	void enterLexerRuleBlock(GrammarParser.LexerRuleBlockContext ctx);
	void exitLexerRuleBlock(GrammarParser.LexerRuleBlockContext ctx);

	void enterFinallyClause(GrammarParser.FinallyClauseContext ctx);
	void exitFinallyClause(GrammarParser.FinallyClauseContext ctx);

	void enterIgnored(GrammarParser.IgnoredContext ctx);
	void exitIgnored(GrammarParser.IgnoredContext ctx);

	void enterGrammarSpec(GrammarParser.GrammarSpecContext ctx);
	void exitGrammarSpec(GrammarParser.GrammarSpecContext ctx);

	void enterDelegateGrammar(GrammarParser.DelegateGrammarContext ctx);
	void exitDelegateGrammar(GrammarParser.DelegateGrammarContext ctx);

	void enterLexerElements(GrammarParser.LexerElementsContext ctx);
	void exitLexerElements(GrammarParser.LexerElementsContext ctx);

	void enterRange(GrammarParser.RangeContext ctx);
	void exitRange(GrammarParser.RangeContext ctx);

	void enterTokenSpec(GrammarParser.TokenSpecContext ctx);
	void exitTokenSpec(GrammarParser.TokenSpecContext ctx);

	void enterBlock(GrammarParser.BlockContext ctx);
	void exitBlock(GrammarParser.BlockContext ctx);

	void enterArgActionParameterTypePart(GrammarParser.ArgActionParameterTypePartContext ctx);
	void exitArgActionParameterTypePart(GrammarParser.ArgActionParameterTypePartContext ctx);

	void enterLexerRule(GrammarParser.LexerRuleContext ctx);
	void exitLexerRule(GrammarParser.LexerRuleContext ctx);

	void enterLabeledLexerElement(GrammarParser.LabeledLexerElementContext ctx);
	void exitLabeledLexerElement(GrammarParser.LabeledLexerElementContext ctx);

	void enterDelegateGrammars(GrammarParser.DelegateGrammarsContext ctx);
	void exitDelegateGrammars(GrammarParser.DelegateGrammarsContext ctx);

	void enterActionExpression(GrammarParser.ActionExpressionContext ctx);
	void exitActionExpression(GrammarParser.ActionExpressionContext ctx);

	void enterRuleref(GrammarParser.RulerefContext ctx);
	void exitRuleref(GrammarParser.RulerefContext ctx);

	void enterBlockSuffix(GrammarParser.BlockSuffixContext ctx);
	void exitBlockSuffix(GrammarParser.BlockSuffixContext ctx);

	void enterId(GrammarParser.IdContext ctx);
	void exitId(GrammarParser.IdContext ctx);

	void enterActionBlock(GrammarParser.ActionBlockContext ctx);
	void exitActionBlock(GrammarParser.ActionBlockContext ctx);

	void enterElementOption(GrammarParser.ElementOptionContext ctx);
	void exitElementOption(GrammarParser.ElementOptionContext ctx);

	void enterExceptionHandler(GrammarParser.ExceptionHandlerContext ctx);
	void exitExceptionHandler(GrammarParser.ExceptionHandlerContext ctx);

	void enterTokensSpec(GrammarParser.TokensSpecContext ctx);
	void exitTokensSpec(GrammarParser.TokensSpecContext ctx);

	void enterRuleReturns(GrammarParser.RuleReturnsContext ctx);
	void exitRuleReturns(GrammarParser.RuleReturnsContext ctx);

	void enterQid(GrammarParser.QidContext ctx);
	void exitQid(GrammarParser.QidContext ctx);

	void enterOptionsSpec(GrammarParser.OptionsSpecContext ctx);
	void exitOptionsSpec(GrammarParser.OptionsSpecContext ctx);

	void enterAltList(GrammarParser.AltListContext ctx);
	void exitAltList(GrammarParser.AltListContext ctx);

	void enterPrequelConstruct(GrammarParser.PrequelConstructContext ctx);
	void exitPrequelConstruct(GrammarParser.PrequelConstructContext ctx);

	void enterRules(GrammarParser.RulesContext ctx);
	void exitRules(GrammarParser.RulesContext ctx);

	void enterOptionValue(GrammarParser.OptionValueContext ctx);
	void exitOptionValue(GrammarParser.OptionValueContext ctx);

	void enterLexerAlt(GrammarParser.LexerAltContext ctx);
	void exitLexerAlt(GrammarParser.LexerAltContext ctx);

	void enterArgActionBlock(GrammarParser.ArgActionBlockContext ctx);
	void exitArgActionBlock(GrammarParser.ArgActionBlockContext ctx);

	void enterRuleModifiers(GrammarParser.RuleModifiersContext ctx);
	void exitRuleModifiers(GrammarParser.RuleModifiersContext ctx);

	void enterArgActionParameters(GrammarParser.ArgActionParametersContext ctx);
	void exitArgActionParameters(GrammarParser.ArgActionParametersContext ctx);

	void enterEbnf(GrammarParser.EbnfContext ctx);
	void exitEbnf(GrammarParser.EbnfContext ctx);

	void enterLexerCommandExpr(GrammarParser.LexerCommandExprContext ctx);
	void exitLexerCommandExpr(GrammarParser.LexerCommandExprContext ctx);
}