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
import org.antlr.v4.runtime.ParserRuleContext;

public class GrammarParserBaseVisitor<Result> extends AbstractParseTreeVisitor<Token, Result> implements GrammarParserVisitor<Token, Result> {
	@Override public Result visitRuleSpec(GrammarParser.RuleSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAtom(GrammarParser.AtomContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRulePrequels(GrammarParser.RulePrequelsContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRuleBlock(GrammarParser.RuleBlockContext ctx) { return visitChildren(ctx); }

	@Override public Result visitNotSet(GrammarParser.NotSetContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLexerCommands(GrammarParser.LexerCommandsContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLexerAltList(GrammarParser.LexerAltListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitArgActionParameter(GrammarParser.ArgActionParameterContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRuleModifier(GrammarParser.RuleModifierContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRuleAltList(GrammarParser.RuleAltListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTerminal(GrammarParser.TerminalContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLexerCommand(GrammarParser.LexerCommandContext ctx) { return visitChildren(ctx); }

	@Override public Result visitThrowsSpec(GrammarParser.ThrowsSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAction(GrammarParser.ActionContext ctx) { return visitChildren(ctx); }

	@Override public Result visitActionScopeExpression(GrammarParser.ActionScopeExpressionContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLocalsSpec(GrammarParser.LocalsSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitModeSpec(GrammarParser.ModeSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitElements(GrammarParser.ElementsContext ctx) { return visitChildren(ctx); }

	@Override public Result visitOption(GrammarParser.OptionContext ctx) { return visitChildren(ctx); }

	@Override public Result visitElement(GrammarParser.ElementContext ctx) { return visitChildren(ctx); }

	@Override public Result visitElementOptions(GrammarParser.ElementOptionsContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLexerElement(GrammarParser.LexerElementContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAlternative(GrammarParser.AlternativeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitParserRuleSpec(GrammarParser.ParserRuleSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitGrammarType(GrammarParser.GrammarTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRuleAction(GrammarParser.RuleActionContext ctx) { return visitChildren(ctx); }

	@Override public Result visitEbnfSuffix(GrammarParser.EbnfSuffixContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExceptionGroup(GrammarParser.ExceptionGroupContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRulePrequel(GrammarParser.RulePrequelContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLexerBlock(GrammarParser.LexerBlockContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSetElement(GrammarParser.SetElementContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBlockSet(GrammarParser.BlockSetContext ctx) { return visitChildren(ctx); }

	@Override public Result visitActionScopeName(GrammarParser.ActionScopeNameContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLabeledAlt(GrammarParser.LabeledAltContext ctx) { return visitChildren(ctx); }

	@Override public Result visitArgActionParameterType(GrammarParser.ArgActionParameterTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLexerAtom(GrammarParser.LexerAtomContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLabeledElement(GrammarParser.LabeledElementContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLexerRuleBlock(GrammarParser.LexerRuleBlockContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFinallyClause(GrammarParser.FinallyClauseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitIgnored(GrammarParser.IgnoredContext ctx) { return visitChildren(ctx); }

	@Override public Result visitGrammarSpec(GrammarParser.GrammarSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitDelegateGrammar(GrammarParser.DelegateGrammarContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLexerElements(GrammarParser.LexerElementsContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRange(GrammarParser.RangeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTokenSpec(GrammarParser.TokenSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBlock(GrammarParser.BlockContext ctx) { return visitChildren(ctx); }

	@Override public Result visitArgActionParameterTypePart(GrammarParser.ArgActionParameterTypePartContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLexerRule(GrammarParser.LexerRuleContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLabeledLexerElement(GrammarParser.LabeledLexerElementContext ctx) { return visitChildren(ctx); }

	@Override public Result visitDelegateGrammars(GrammarParser.DelegateGrammarsContext ctx) { return visitChildren(ctx); }

	@Override public Result visitActionExpression(GrammarParser.ActionExpressionContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRuleref(GrammarParser.RulerefContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBlockSuffix(GrammarParser.BlockSuffixContext ctx) { return visitChildren(ctx); }

	@Override public Result visitId(GrammarParser.IdContext ctx) { return visitChildren(ctx); }

	@Override public Result visitActionBlock(GrammarParser.ActionBlockContext ctx) { return visitChildren(ctx); }

	@Override public Result visitElementOption(GrammarParser.ElementOptionContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExceptionHandler(GrammarParser.ExceptionHandlerContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTokensSpec(GrammarParser.TokensSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRuleReturns(GrammarParser.RuleReturnsContext ctx) { return visitChildren(ctx); }

	@Override public Result visitQid(GrammarParser.QidContext ctx) { return visitChildren(ctx); }

	@Override public Result visitOptionsSpec(GrammarParser.OptionsSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAltList(GrammarParser.AltListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitPrequelConstruct(GrammarParser.PrequelConstructContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRules(GrammarParser.RulesContext ctx) { return visitChildren(ctx); }

	@Override public Result visitOptionValue(GrammarParser.OptionValueContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLexerAlt(GrammarParser.LexerAltContext ctx) { return visitChildren(ctx); }

	@Override public Result visitArgActionBlock(GrammarParser.ArgActionBlockContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRuleModifiers(GrammarParser.RuleModifiersContext ctx) { return visitChildren(ctx); }

	@Override public Result visitArgActionParameters(GrammarParser.ArgActionParametersContext ctx) { return visitChildren(ctx); }

	@Override public Result visitEbnf(GrammarParser.EbnfContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLexerCommandExpr(GrammarParser.LexerCommandExprContext ctx) { return visitChildren(ctx); }
}