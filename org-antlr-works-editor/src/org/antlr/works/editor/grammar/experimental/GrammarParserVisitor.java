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

public interface GrammarParserVisitor<Symbol extends Token, Result> extends ParseTreeVisitor<Symbol, Result> {
	Result visitRuleSpec(GrammarParser.RuleSpecContext ctx);

	Result visitAtom(GrammarParser.AtomContext ctx);

	Result visitRulePrequels(GrammarParser.RulePrequelsContext ctx);

	Result visitRuleBlock(GrammarParser.RuleBlockContext ctx);

	Result visitNotSet(GrammarParser.NotSetContext ctx);

	Result visitLexerCommands(GrammarParser.LexerCommandsContext ctx);

	Result visitLexerAltList(GrammarParser.LexerAltListContext ctx);

	Result visitArgActionParameter(GrammarParser.ArgActionParameterContext ctx);

	Result visitRuleModifier(GrammarParser.RuleModifierContext ctx);

	Result visitRuleAltList(GrammarParser.RuleAltListContext ctx);

	Result visitTerminal(GrammarParser.TerminalContext ctx);

	Result visitLexerCommand(GrammarParser.LexerCommandContext ctx);

	Result visitThrowsSpec(GrammarParser.ThrowsSpecContext ctx);

	Result visitAction(GrammarParser.ActionContext ctx);

	Result visitActionScopeExpression(GrammarParser.ActionScopeExpressionContext ctx);

	Result visitLocalsSpec(GrammarParser.LocalsSpecContext ctx);

	Result visitModeSpec(GrammarParser.ModeSpecContext ctx);

	Result visitElements(GrammarParser.ElementsContext ctx);

	Result visitOption(GrammarParser.OptionContext ctx);

	Result visitElement(GrammarParser.ElementContext ctx);

	Result visitElementOptions(GrammarParser.ElementOptionsContext ctx);

	Result visitLexerElement(GrammarParser.LexerElementContext ctx);

	Result visitAlternative(GrammarParser.AlternativeContext ctx);

	Result visitParserRuleSpec(GrammarParser.ParserRuleSpecContext ctx);

	Result visitGrammarType(GrammarParser.GrammarTypeContext ctx);

	Result visitRuleAction(GrammarParser.RuleActionContext ctx);

	Result visitEbnfSuffix(GrammarParser.EbnfSuffixContext ctx);

	Result visitExceptionGroup(GrammarParser.ExceptionGroupContext ctx);

	Result visitRulePrequel(GrammarParser.RulePrequelContext ctx);

	Result visitLexerBlock(GrammarParser.LexerBlockContext ctx);

	Result visitSetElement(GrammarParser.SetElementContext ctx);

	Result visitBlockSet(GrammarParser.BlockSetContext ctx);

	Result visitActionScopeName(GrammarParser.ActionScopeNameContext ctx);

	Result visitLabeledAlt(GrammarParser.LabeledAltContext ctx);

	Result visitArgActionParameterType(GrammarParser.ArgActionParameterTypeContext ctx);

	Result visitLexerAtom(GrammarParser.LexerAtomContext ctx);

	Result visitLabeledElement(GrammarParser.LabeledElementContext ctx);

	Result visitLexerRuleBlock(GrammarParser.LexerRuleBlockContext ctx);

	Result visitFinallyClause(GrammarParser.FinallyClauseContext ctx);

	Result visitIgnored(GrammarParser.IgnoredContext ctx);

	Result visitGrammarSpec(GrammarParser.GrammarSpecContext ctx);

	Result visitDelegateGrammar(GrammarParser.DelegateGrammarContext ctx);

	Result visitLexerElements(GrammarParser.LexerElementsContext ctx);

	Result visitRange(GrammarParser.RangeContext ctx);

	Result visitTokenSpec(GrammarParser.TokenSpecContext ctx);

	Result visitBlock(GrammarParser.BlockContext ctx);

	Result visitArgActionParameterTypePart(GrammarParser.ArgActionParameterTypePartContext ctx);

	Result visitLexerRule(GrammarParser.LexerRuleContext ctx);

	Result visitLabeledLexerElement(GrammarParser.LabeledLexerElementContext ctx);

	Result visitDelegateGrammars(GrammarParser.DelegateGrammarsContext ctx);

	Result visitActionExpression(GrammarParser.ActionExpressionContext ctx);

	Result visitRuleref(GrammarParser.RulerefContext ctx);

	Result visitBlockSuffix(GrammarParser.BlockSuffixContext ctx);

	Result visitId(GrammarParser.IdContext ctx);

	Result visitActionBlock(GrammarParser.ActionBlockContext ctx);

	Result visitElementOption(GrammarParser.ElementOptionContext ctx);

	Result visitExceptionHandler(GrammarParser.ExceptionHandlerContext ctx);

	Result visitTokensSpec(GrammarParser.TokensSpecContext ctx);

	Result visitRuleReturns(GrammarParser.RuleReturnsContext ctx);

	Result visitQid(GrammarParser.QidContext ctx);

	Result visitOptionsSpec(GrammarParser.OptionsSpecContext ctx);

	Result visitAltList(GrammarParser.AltListContext ctx);

	Result visitPrequelConstruct(GrammarParser.PrequelConstructContext ctx);

	Result visitRules(GrammarParser.RulesContext ctx);

	Result visitOptionValue(GrammarParser.OptionValueContext ctx);

	Result visitLexerAlt(GrammarParser.LexerAltContext ctx);

	Result visitArgActionBlock(GrammarParser.ArgActionBlockContext ctx);

	Result visitRuleModifiers(GrammarParser.RuleModifiersContext ctx);

	Result visitArgActionParameters(GrammarParser.ArgActionParametersContext ctx);

	Result visitEbnf(GrammarParser.EbnfContext ctx);

	Result visitLexerCommandExpr(GrammarParser.LexerCommandExprContext ctx);
}