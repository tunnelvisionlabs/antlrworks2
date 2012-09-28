package org.antlr.works.editor.grammar.experimental;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface GrammarParserVisitor<Symbol extends Token, Result> extends ParseTreeVisitor<Symbol, Result> {
	Result visitRuleSpec(AbstractGrammarParser.RuleSpecContext ctx);

	Result visitAtom(AbstractGrammarParser.AtomContext ctx);

	Result visitRulePrequels(AbstractGrammarParser.RulePrequelsContext ctx);

	Result visitRuleBlock(AbstractGrammarParser.RuleBlockContext ctx);

	Result visitNotSet(AbstractGrammarParser.NotSetContext ctx);

	Result visitLexerCommands(AbstractGrammarParser.LexerCommandsContext ctx);

	Result visitLexerAltList(AbstractGrammarParser.LexerAltListContext ctx);

	Result visitArgActionParameter(AbstractGrammarParser.ArgActionParameterContext ctx);

	Result visitRuleModifier(AbstractGrammarParser.RuleModifierContext ctx);

	Result visitRuleAltList(AbstractGrammarParser.RuleAltListContext ctx);

	Result visitTerminal(AbstractGrammarParser.TerminalContext ctx);

	Result visitLexerCommand(AbstractGrammarParser.LexerCommandContext ctx);

	Result visitThrowsSpec(AbstractGrammarParser.ThrowsSpecContext ctx);

	Result visitAction(AbstractGrammarParser.ActionContext ctx);

	Result visitActionScopeExpression(AbstractGrammarParser.ActionScopeExpressionContext ctx);

	Result visitLocalsSpec(AbstractGrammarParser.LocalsSpecContext ctx);

	Result visitModeSpec(AbstractGrammarParser.ModeSpecContext ctx);

	Result visitElements(AbstractGrammarParser.ElementsContext ctx);

	Result visitOption(AbstractGrammarParser.OptionContext ctx);

	Result visitElement(AbstractGrammarParser.ElementContext ctx);

	Result visitElementOptions(AbstractGrammarParser.ElementOptionsContext ctx);

	Result visitLexerElement(AbstractGrammarParser.LexerElementContext ctx);

	Result visitAlternative(AbstractGrammarParser.AlternativeContext ctx);

	Result visitParserRuleSpec(AbstractGrammarParser.ParserRuleSpecContext ctx);

	Result visitGrammarType(AbstractGrammarParser.GrammarTypeContext ctx);

	Result visitRuleAction(AbstractGrammarParser.RuleActionContext ctx);

	Result visitEbnfSuffix(AbstractGrammarParser.EbnfSuffixContext ctx);

	Result visitExceptionGroup(AbstractGrammarParser.ExceptionGroupContext ctx);

	Result visitRulePrequel(AbstractGrammarParser.RulePrequelContext ctx);

	Result visitLexerBlock(AbstractGrammarParser.LexerBlockContext ctx);

	Result visitSetElement(AbstractGrammarParser.SetElementContext ctx);

	Result visitBlockSet(AbstractGrammarParser.BlockSetContext ctx);

	Result visitActionScopeName(AbstractGrammarParser.ActionScopeNameContext ctx);

	Result visitLabeledAlt(AbstractGrammarParser.LabeledAltContext ctx);

	Result visitArgActionParameterType(AbstractGrammarParser.ArgActionParameterTypeContext ctx);

	Result visitLexerAtom(AbstractGrammarParser.LexerAtomContext ctx);

	Result visitLabeledElement(AbstractGrammarParser.LabeledElementContext ctx);

	Result visitLexerRuleBlock(AbstractGrammarParser.LexerRuleBlockContext ctx);

	Result visitFinallyClause(AbstractGrammarParser.FinallyClauseContext ctx);

	Result visitIgnored(AbstractGrammarParser.IgnoredContext ctx);

	Result visitGrammarSpec(AbstractGrammarParser.GrammarSpecContext ctx);

	Result visitDelegateGrammar(AbstractGrammarParser.DelegateGrammarContext ctx);

	Result visitLexerElements(AbstractGrammarParser.LexerElementsContext ctx);

	Result visitRange(AbstractGrammarParser.RangeContext ctx);

	Result visitLexerCommandName(AbstractGrammarParser.LexerCommandNameContext ctx);

	Result visitBlock(AbstractGrammarParser.BlockContext ctx);

	Result visitArgActionParameterTypePart(AbstractGrammarParser.ArgActionParameterTypePartContext ctx);

	Result visitLexerRule(AbstractGrammarParser.LexerRuleContext ctx);

	Result visitLabeledLexerElement(AbstractGrammarParser.LabeledLexerElementContext ctx);

	Result visitDelegateGrammars(AbstractGrammarParser.DelegateGrammarsContext ctx);

	Result visitActionExpression(AbstractGrammarParser.ActionExpressionContext ctx);

	Result visitRuleref(AbstractGrammarParser.RulerefContext ctx);

	Result visitBlockSuffix(AbstractGrammarParser.BlockSuffixContext ctx);

	Result visitId(AbstractGrammarParser.IdContext ctx);

	Result visitActionBlock(AbstractGrammarParser.ActionBlockContext ctx);

	Result visitElementOption(AbstractGrammarParser.ElementOptionContext ctx);

	Result visitExceptionHandler(AbstractGrammarParser.ExceptionHandlerContext ctx);

	Result visitTokensSpec(AbstractGrammarParser.TokensSpecContext ctx);

	Result visitRuleReturns(AbstractGrammarParser.RuleReturnsContext ctx);

	Result visitQid(AbstractGrammarParser.QidContext ctx);

	Result visitOptionsSpec(AbstractGrammarParser.OptionsSpecContext ctx);

	Result visitAltList(AbstractGrammarParser.AltListContext ctx);

	Result visitPrequelConstruct(AbstractGrammarParser.PrequelConstructContext ctx);

	Result visitRules(AbstractGrammarParser.RulesContext ctx);

	Result visitOptionValue(AbstractGrammarParser.OptionValueContext ctx);

	Result visitLexerAlt(AbstractGrammarParser.LexerAltContext ctx);

	Result visitArgActionBlock(AbstractGrammarParser.ArgActionBlockContext ctx);

	Result visitRuleModifiers(AbstractGrammarParser.RuleModifiersContext ctx);

	Result visitArgActionParameters(AbstractGrammarParser.ArgActionParametersContext ctx);

	Result visitEbnf(AbstractGrammarParser.EbnfContext ctx);

	Result visitLexerCommandExpr(AbstractGrammarParser.LexerCommandExprContext ctx);
}