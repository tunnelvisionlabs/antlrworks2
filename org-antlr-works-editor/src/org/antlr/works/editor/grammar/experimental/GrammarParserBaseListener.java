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
import org.antlr.v4.runtime.tree.ParseTree;

public class GrammarParserBaseListener implements GrammarParserListener {
	@Override public void enterRuleSpec(GrammarParser.RuleSpecContext ctx) { }
	@Override public void exitRuleSpec(GrammarParser.RuleSpecContext ctx) { }

	@Override public void enterAtom(GrammarParser.AtomContext ctx) { }
	@Override public void exitAtom(GrammarParser.AtomContext ctx) { }

	@Override public void enterRulePrequels(GrammarParser.RulePrequelsContext ctx) { }
	@Override public void exitRulePrequels(GrammarParser.RulePrequelsContext ctx) { }

	@Override public void enterRuleBlock(GrammarParser.RuleBlockContext ctx) { }
	@Override public void exitRuleBlock(GrammarParser.RuleBlockContext ctx) { }

	@Override public void enterNotSet(GrammarParser.NotSetContext ctx) { }
	@Override public void exitNotSet(GrammarParser.NotSetContext ctx) { }

	@Override public void enterLexerCommands(GrammarParser.LexerCommandsContext ctx) { }
	@Override public void exitLexerCommands(GrammarParser.LexerCommandsContext ctx) { }

	@Override public void enterLexerAltList(GrammarParser.LexerAltListContext ctx) { }
	@Override public void exitLexerAltList(GrammarParser.LexerAltListContext ctx) { }

	@Override public void enterArgActionParameter(GrammarParser.ArgActionParameterContext ctx) { }
	@Override public void exitArgActionParameter(GrammarParser.ArgActionParameterContext ctx) { }

	@Override public void enterRuleModifier(GrammarParser.RuleModifierContext ctx) { }
	@Override public void exitRuleModifier(GrammarParser.RuleModifierContext ctx) { }

	@Override public void enterRuleAltList(GrammarParser.RuleAltListContext ctx) { }
	@Override public void exitRuleAltList(GrammarParser.RuleAltListContext ctx) { }

	@Override public void enterTerminal(GrammarParser.TerminalContext ctx) { }
	@Override public void exitTerminal(GrammarParser.TerminalContext ctx) { }

	@Override public void enterLexerCommand(GrammarParser.LexerCommandContext ctx) { }
	@Override public void exitLexerCommand(GrammarParser.LexerCommandContext ctx) { }

	@Override public void enterThrowsSpec(GrammarParser.ThrowsSpecContext ctx) { }
	@Override public void exitThrowsSpec(GrammarParser.ThrowsSpecContext ctx) { }

	@Override public void enterAction(GrammarParser.ActionContext ctx) { }
	@Override public void exitAction(GrammarParser.ActionContext ctx) { }

	@Override public void enterActionScopeExpression(GrammarParser.ActionScopeExpressionContext ctx) { }
	@Override public void exitActionScopeExpression(GrammarParser.ActionScopeExpressionContext ctx) { }

	@Override public void enterLocalsSpec(GrammarParser.LocalsSpecContext ctx) { }
	@Override public void exitLocalsSpec(GrammarParser.LocalsSpecContext ctx) { }

	@Override public void enterModeSpec(GrammarParser.ModeSpecContext ctx) { }
	@Override public void exitModeSpec(GrammarParser.ModeSpecContext ctx) { }

	@Override public void enterElements(GrammarParser.ElementsContext ctx) { }
	@Override public void exitElements(GrammarParser.ElementsContext ctx) { }

	@Override public void enterOption(GrammarParser.OptionContext ctx) { }
	@Override public void exitOption(GrammarParser.OptionContext ctx) { }

	@Override public void enterElement(GrammarParser.ElementContext ctx) { }
	@Override public void exitElement(GrammarParser.ElementContext ctx) { }

	@Override public void enterElementOptions(GrammarParser.ElementOptionsContext ctx) { }
	@Override public void exitElementOptions(GrammarParser.ElementOptionsContext ctx) { }

	@Override public void enterLexerElement(GrammarParser.LexerElementContext ctx) { }
	@Override public void exitLexerElement(GrammarParser.LexerElementContext ctx) { }

	@Override public void enterAlternative(GrammarParser.AlternativeContext ctx) { }
	@Override public void exitAlternative(GrammarParser.AlternativeContext ctx) { }

	@Override public void enterParserRuleSpec(GrammarParser.ParserRuleSpecContext ctx) { }
	@Override public void exitParserRuleSpec(GrammarParser.ParserRuleSpecContext ctx) { }

	@Override public void enterGrammarType(GrammarParser.GrammarTypeContext ctx) { }
	@Override public void exitGrammarType(GrammarParser.GrammarTypeContext ctx) { }

	@Override public void enterRuleAction(GrammarParser.RuleActionContext ctx) { }
	@Override public void exitRuleAction(GrammarParser.RuleActionContext ctx) { }

	@Override public void enterEbnfSuffix(GrammarParser.EbnfSuffixContext ctx) { }
	@Override public void exitEbnfSuffix(GrammarParser.EbnfSuffixContext ctx) { }

	@Override public void enterExceptionGroup(GrammarParser.ExceptionGroupContext ctx) { }
	@Override public void exitExceptionGroup(GrammarParser.ExceptionGroupContext ctx) { }

	@Override public void enterRulePrequel(GrammarParser.RulePrequelContext ctx) { }
	@Override public void exitRulePrequel(GrammarParser.RulePrequelContext ctx) { }

	@Override public void enterLexerBlock(GrammarParser.LexerBlockContext ctx) { }
	@Override public void exitLexerBlock(GrammarParser.LexerBlockContext ctx) { }

	@Override public void enterSetElement(GrammarParser.SetElementContext ctx) { }
	@Override public void exitSetElement(GrammarParser.SetElementContext ctx) { }

	@Override public void enterBlockSet(GrammarParser.BlockSetContext ctx) { }
	@Override public void exitBlockSet(GrammarParser.BlockSetContext ctx) { }

	@Override public void enterActionScopeName(GrammarParser.ActionScopeNameContext ctx) { }
	@Override public void exitActionScopeName(GrammarParser.ActionScopeNameContext ctx) { }

	@Override public void enterLabeledAlt(GrammarParser.LabeledAltContext ctx) { }
	@Override public void exitLabeledAlt(GrammarParser.LabeledAltContext ctx) { }

	@Override public void enterArgActionParameterType(GrammarParser.ArgActionParameterTypeContext ctx) { }
	@Override public void exitArgActionParameterType(GrammarParser.ArgActionParameterTypeContext ctx) { }

	@Override public void enterLexerAtom(GrammarParser.LexerAtomContext ctx) { }
	@Override public void exitLexerAtom(GrammarParser.LexerAtomContext ctx) { }

	@Override public void enterLabeledElement(GrammarParser.LabeledElementContext ctx) { }
	@Override public void exitLabeledElement(GrammarParser.LabeledElementContext ctx) { }

	@Override public void enterLexerRuleBlock(GrammarParser.LexerRuleBlockContext ctx) { }
	@Override public void exitLexerRuleBlock(GrammarParser.LexerRuleBlockContext ctx) { }

	@Override public void enterFinallyClause(GrammarParser.FinallyClauseContext ctx) { }
	@Override public void exitFinallyClause(GrammarParser.FinallyClauseContext ctx) { }

	@Override public void enterIgnored(GrammarParser.IgnoredContext ctx) { }
	@Override public void exitIgnored(GrammarParser.IgnoredContext ctx) { }

	@Override public void enterGrammarSpec(GrammarParser.GrammarSpecContext ctx) { }
	@Override public void exitGrammarSpec(GrammarParser.GrammarSpecContext ctx) { }

	@Override public void enterDelegateGrammar(GrammarParser.DelegateGrammarContext ctx) { }
	@Override public void exitDelegateGrammar(GrammarParser.DelegateGrammarContext ctx) { }

	@Override public void enterLexerElements(GrammarParser.LexerElementsContext ctx) { }
	@Override public void exitLexerElements(GrammarParser.LexerElementsContext ctx) { }

	@Override public void enterRange(GrammarParser.RangeContext ctx) { }
	@Override public void exitRange(GrammarParser.RangeContext ctx) { }

	@Override public void enterTokenSpec(GrammarParser.TokenSpecContext ctx) { }
	@Override public void exitTokenSpec(GrammarParser.TokenSpecContext ctx) { }

	@Override public void enterBlock(GrammarParser.BlockContext ctx) { }
	@Override public void exitBlock(GrammarParser.BlockContext ctx) { }

	@Override public void enterArgActionParameterTypePart(GrammarParser.ArgActionParameterTypePartContext ctx) { }
	@Override public void exitArgActionParameterTypePart(GrammarParser.ArgActionParameterTypePartContext ctx) { }

	@Override public void enterLexerRule(GrammarParser.LexerRuleContext ctx) { }
	@Override public void exitLexerRule(GrammarParser.LexerRuleContext ctx) { }

	@Override public void enterLabeledLexerElement(GrammarParser.LabeledLexerElementContext ctx) { }
	@Override public void exitLabeledLexerElement(GrammarParser.LabeledLexerElementContext ctx) { }

	@Override public void enterDelegateGrammars(GrammarParser.DelegateGrammarsContext ctx) { }
	@Override public void exitDelegateGrammars(GrammarParser.DelegateGrammarsContext ctx) { }

	@Override public void enterActionExpression(GrammarParser.ActionExpressionContext ctx) { }
	@Override public void exitActionExpression(GrammarParser.ActionExpressionContext ctx) { }

	@Override public void enterRuleref(GrammarParser.RulerefContext ctx) { }
	@Override public void exitRuleref(GrammarParser.RulerefContext ctx) { }

	@Override public void enterBlockSuffix(GrammarParser.BlockSuffixContext ctx) { }
	@Override public void exitBlockSuffix(GrammarParser.BlockSuffixContext ctx) { }

	@Override public void enterId(GrammarParser.IdContext ctx) { }
	@Override public void exitId(GrammarParser.IdContext ctx) { }

	@Override public void enterActionBlock(GrammarParser.ActionBlockContext ctx) { }
	@Override public void exitActionBlock(GrammarParser.ActionBlockContext ctx) { }

	@Override public void enterElementOption(GrammarParser.ElementOptionContext ctx) { }
	@Override public void exitElementOption(GrammarParser.ElementOptionContext ctx) { }

	@Override public void enterExceptionHandler(GrammarParser.ExceptionHandlerContext ctx) { }
	@Override public void exitExceptionHandler(GrammarParser.ExceptionHandlerContext ctx) { }

	@Override public void enterTokensSpec(GrammarParser.TokensSpecContext ctx) { }
	@Override public void exitTokensSpec(GrammarParser.TokensSpecContext ctx) { }

	@Override public void enterRuleReturns(GrammarParser.RuleReturnsContext ctx) { }
	@Override public void exitRuleReturns(GrammarParser.RuleReturnsContext ctx) { }

	@Override public void enterQid(GrammarParser.QidContext ctx) { }
	@Override public void exitQid(GrammarParser.QidContext ctx) { }

	@Override public void enterOptionsSpec(GrammarParser.OptionsSpecContext ctx) { }
	@Override public void exitOptionsSpec(GrammarParser.OptionsSpecContext ctx) { }

	@Override public void enterAltList(GrammarParser.AltListContext ctx) { }
	@Override public void exitAltList(GrammarParser.AltListContext ctx) { }

	@Override public void enterPrequelConstruct(GrammarParser.PrequelConstructContext ctx) { }
	@Override public void exitPrequelConstruct(GrammarParser.PrequelConstructContext ctx) { }

	@Override public void enterRules(GrammarParser.RulesContext ctx) { }
	@Override public void exitRules(GrammarParser.RulesContext ctx) { }

	@Override public void enterOptionValue(GrammarParser.OptionValueContext ctx) { }
	@Override public void exitOptionValue(GrammarParser.OptionValueContext ctx) { }

	@Override public void enterLexerAlt(GrammarParser.LexerAltContext ctx) { }
	@Override public void exitLexerAlt(GrammarParser.LexerAltContext ctx) { }

	@Override public void enterArgActionBlock(GrammarParser.ArgActionBlockContext ctx) { }
	@Override public void exitArgActionBlock(GrammarParser.ArgActionBlockContext ctx) { }

	@Override public void enterRuleModifiers(GrammarParser.RuleModifiersContext ctx) { }
	@Override public void exitRuleModifiers(GrammarParser.RuleModifiersContext ctx) { }

	@Override public void enterArgActionParameters(GrammarParser.ArgActionParametersContext ctx) { }
	@Override public void exitArgActionParameters(GrammarParser.ArgActionParametersContext ctx) { }

	@Override public void enterEbnf(GrammarParser.EbnfContext ctx) { }
	@Override public void exitEbnf(GrammarParser.EbnfContext ctx) { }

	@Override public void enterLexerCommandExpr(GrammarParser.LexerCommandExprContext ctx) { }
	@Override public void exitLexerCommandExpr(GrammarParser.LexerCommandExprContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext<? extends Token> ctx) { }
	@Override public void exitEveryRule(ParserRuleContext<? extends Token> ctx) { }
	@Override public void visitTerminal(ParseTree.TerminalNode<? extends Token> node) { }
	@Override public void visitErrorNode(ParseTree.ErrorNode<? extends Token> node) { }
}