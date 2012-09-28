package org.antlr.works.editor.grammar.experimental;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface GrammarParserListener extends ParseTreeListener<Token> {
	void enterRuleSpec(AbstractGrammarParser.RuleSpecContext ctx);
	void exitRuleSpec(AbstractGrammarParser.RuleSpecContext ctx);

	void enterAtom(AbstractGrammarParser.AtomContext ctx);
	void exitAtom(AbstractGrammarParser.AtomContext ctx);

	void enterRulePrequels(AbstractGrammarParser.RulePrequelsContext ctx);
	void exitRulePrequels(AbstractGrammarParser.RulePrequelsContext ctx);

	void enterRuleBlock(AbstractGrammarParser.RuleBlockContext ctx);
	void exitRuleBlock(AbstractGrammarParser.RuleBlockContext ctx);

	void enterNotSet(AbstractGrammarParser.NotSetContext ctx);
	void exitNotSet(AbstractGrammarParser.NotSetContext ctx);

	void enterLexerCommands(AbstractGrammarParser.LexerCommandsContext ctx);
	void exitLexerCommands(AbstractGrammarParser.LexerCommandsContext ctx);

	void enterLexerAltList(AbstractGrammarParser.LexerAltListContext ctx);
	void exitLexerAltList(AbstractGrammarParser.LexerAltListContext ctx);

	void enterArgActionParameter(AbstractGrammarParser.ArgActionParameterContext ctx);
	void exitArgActionParameter(AbstractGrammarParser.ArgActionParameterContext ctx);

	void enterRuleModifier(AbstractGrammarParser.RuleModifierContext ctx);
	void exitRuleModifier(AbstractGrammarParser.RuleModifierContext ctx);

	void enterRuleAltList(AbstractGrammarParser.RuleAltListContext ctx);
	void exitRuleAltList(AbstractGrammarParser.RuleAltListContext ctx);

	void enterTerminal(AbstractGrammarParser.TerminalContext ctx);
	void exitTerminal(AbstractGrammarParser.TerminalContext ctx);

	void enterLexerCommand(AbstractGrammarParser.LexerCommandContext ctx);
	void exitLexerCommand(AbstractGrammarParser.LexerCommandContext ctx);

	void enterThrowsSpec(AbstractGrammarParser.ThrowsSpecContext ctx);
	void exitThrowsSpec(AbstractGrammarParser.ThrowsSpecContext ctx);

	void enterAction(AbstractGrammarParser.ActionContext ctx);
	void exitAction(AbstractGrammarParser.ActionContext ctx);

	void enterActionScopeExpression(AbstractGrammarParser.ActionScopeExpressionContext ctx);
	void exitActionScopeExpression(AbstractGrammarParser.ActionScopeExpressionContext ctx);

	void enterLocalsSpec(AbstractGrammarParser.LocalsSpecContext ctx);
	void exitLocalsSpec(AbstractGrammarParser.LocalsSpecContext ctx);

	void enterModeSpec(AbstractGrammarParser.ModeSpecContext ctx);
	void exitModeSpec(AbstractGrammarParser.ModeSpecContext ctx);

	void enterElements(AbstractGrammarParser.ElementsContext ctx);
	void exitElements(AbstractGrammarParser.ElementsContext ctx);

	void enterOption(AbstractGrammarParser.OptionContext ctx);
	void exitOption(AbstractGrammarParser.OptionContext ctx);

	void enterElement(AbstractGrammarParser.ElementContext ctx);
	void exitElement(AbstractGrammarParser.ElementContext ctx);

	void enterElementOptions(AbstractGrammarParser.ElementOptionsContext ctx);
	void exitElementOptions(AbstractGrammarParser.ElementOptionsContext ctx);

	void enterLexerElement(AbstractGrammarParser.LexerElementContext ctx);
	void exitLexerElement(AbstractGrammarParser.LexerElementContext ctx);

	void enterAlternative(AbstractGrammarParser.AlternativeContext ctx);
	void exitAlternative(AbstractGrammarParser.AlternativeContext ctx);

	void enterParserRuleSpec(AbstractGrammarParser.ParserRuleSpecContext ctx);
	void exitParserRuleSpec(AbstractGrammarParser.ParserRuleSpecContext ctx);

	void enterGrammarType(AbstractGrammarParser.GrammarTypeContext ctx);
	void exitGrammarType(AbstractGrammarParser.GrammarTypeContext ctx);

	void enterRuleAction(AbstractGrammarParser.RuleActionContext ctx);
	void exitRuleAction(AbstractGrammarParser.RuleActionContext ctx);

	void enterEbnfSuffix(AbstractGrammarParser.EbnfSuffixContext ctx);
	void exitEbnfSuffix(AbstractGrammarParser.EbnfSuffixContext ctx);

	void enterExceptionGroup(AbstractGrammarParser.ExceptionGroupContext ctx);
	void exitExceptionGroup(AbstractGrammarParser.ExceptionGroupContext ctx);

	void enterRulePrequel(AbstractGrammarParser.RulePrequelContext ctx);
	void exitRulePrequel(AbstractGrammarParser.RulePrequelContext ctx);

	void enterLexerBlock(AbstractGrammarParser.LexerBlockContext ctx);
	void exitLexerBlock(AbstractGrammarParser.LexerBlockContext ctx);

	void enterSetElement(AbstractGrammarParser.SetElementContext ctx);
	void exitSetElement(AbstractGrammarParser.SetElementContext ctx);

	void enterBlockSet(AbstractGrammarParser.BlockSetContext ctx);
	void exitBlockSet(AbstractGrammarParser.BlockSetContext ctx);

	void enterActionScopeName(AbstractGrammarParser.ActionScopeNameContext ctx);
	void exitActionScopeName(AbstractGrammarParser.ActionScopeNameContext ctx);

	void enterLabeledAlt(AbstractGrammarParser.LabeledAltContext ctx);
	void exitLabeledAlt(AbstractGrammarParser.LabeledAltContext ctx);

	void enterArgActionParameterType(AbstractGrammarParser.ArgActionParameterTypeContext ctx);
	void exitArgActionParameterType(AbstractGrammarParser.ArgActionParameterTypeContext ctx);

	void enterLexerAtom(AbstractGrammarParser.LexerAtomContext ctx);
	void exitLexerAtom(AbstractGrammarParser.LexerAtomContext ctx);

	void enterLabeledElement(AbstractGrammarParser.LabeledElementContext ctx);
	void exitLabeledElement(AbstractGrammarParser.LabeledElementContext ctx);

	void enterLexerRuleBlock(AbstractGrammarParser.LexerRuleBlockContext ctx);
	void exitLexerRuleBlock(AbstractGrammarParser.LexerRuleBlockContext ctx);

	void enterFinallyClause(AbstractGrammarParser.FinallyClauseContext ctx);
	void exitFinallyClause(AbstractGrammarParser.FinallyClauseContext ctx);

	void enterIgnored(AbstractGrammarParser.IgnoredContext ctx);
	void exitIgnored(AbstractGrammarParser.IgnoredContext ctx);

	void enterGrammarSpec(AbstractGrammarParser.GrammarSpecContext ctx);
	void exitGrammarSpec(AbstractGrammarParser.GrammarSpecContext ctx);

	void enterDelegateGrammar(AbstractGrammarParser.DelegateGrammarContext ctx);
	void exitDelegateGrammar(AbstractGrammarParser.DelegateGrammarContext ctx);

	void enterLexerElements(AbstractGrammarParser.LexerElementsContext ctx);
	void exitLexerElements(AbstractGrammarParser.LexerElementsContext ctx);

	void enterRange(AbstractGrammarParser.RangeContext ctx);
	void exitRange(AbstractGrammarParser.RangeContext ctx);

	void enterLexerCommandName(AbstractGrammarParser.LexerCommandNameContext ctx);
	void exitLexerCommandName(AbstractGrammarParser.LexerCommandNameContext ctx);

	void enterBlock(AbstractGrammarParser.BlockContext ctx);
	void exitBlock(AbstractGrammarParser.BlockContext ctx);

	void enterArgActionParameterTypePart(AbstractGrammarParser.ArgActionParameterTypePartContext ctx);
	void exitArgActionParameterTypePart(AbstractGrammarParser.ArgActionParameterTypePartContext ctx);

	void enterLexerRule(AbstractGrammarParser.LexerRuleContext ctx);
	void exitLexerRule(AbstractGrammarParser.LexerRuleContext ctx);

	void enterLabeledLexerElement(AbstractGrammarParser.LabeledLexerElementContext ctx);
	void exitLabeledLexerElement(AbstractGrammarParser.LabeledLexerElementContext ctx);

	void enterDelegateGrammars(AbstractGrammarParser.DelegateGrammarsContext ctx);
	void exitDelegateGrammars(AbstractGrammarParser.DelegateGrammarsContext ctx);

	void enterActionExpression(AbstractGrammarParser.ActionExpressionContext ctx);
	void exitActionExpression(AbstractGrammarParser.ActionExpressionContext ctx);

	void enterRuleref(AbstractGrammarParser.RulerefContext ctx);
	void exitRuleref(AbstractGrammarParser.RulerefContext ctx);

	void enterBlockSuffix(AbstractGrammarParser.BlockSuffixContext ctx);
	void exitBlockSuffix(AbstractGrammarParser.BlockSuffixContext ctx);

	void enterId(AbstractGrammarParser.IdContext ctx);
	void exitId(AbstractGrammarParser.IdContext ctx);

	void enterActionBlock(AbstractGrammarParser.ActionBlockContext ctx);
	void exitActionBlock(AbstractGrammarParser.ActionBlockContext ctx);

	void enterElementOption(AbstractGrammarParser.ElementOptionContext ctx);
	void exitElementOption(AbstractGrammarParser.ElementOptionContext ctx);

	void enterExceptionHandler(AbstractGrammarParser.ExceptionHandlerContext ctx);
	void exitExceptionHandler(AbstractGrammarParser.ExceptionHandlerContext ctx);

	void enterTokensSpec(AbstractGrammarParser.TokensSpecContext ctx);
	void exitTokensSpec(AbstractGrammarParser.TokensSpecContext ctx);

	void enterRuleReturns(AbstractGrammarParser.RuleReturnsContext ctx);
	void exitRuleReturns(AbstractGrammarParser.RuleReturnsContext ctx);

	void enterQid(AbstractGrammarParser.QidContext ctx);
	void exitQid(AbstractGrammarParser.QidContext ctx);

	void enterOptionsSpec(AbstractGrammarParser.OptionsSpecContext ctx);
	void exitOptionsSpec(AbstractGrammarParser.OptionsSpecContext ctx);

	void enterAltList(AbstractGrammarParser.AltListContext ctx);
	void exitAltList(AbstractGrammarParser.AltListContext ctx);

	void enterPrequelConstruct(AbstractGrammarParser.PrequelConstructContext ctx);
	void exitPrequelConstruct(AbstractGrammarParser.PrequelConstructContext ctx);

	void enterRules(AbstractGrammarParser.RulesContext ctx);
	void exitRules(AbstractGrammarParser.RulesContext ctx);

	void enterOptionValue(AbstractGrammarParser.OptionValueContext ctx);
	void exitOptionValue(AbstractGrammarParser.OptionValueContext ctx);

	void enterLexerAlt(AbstractGrammarParser.LexerAltContext ctx);
	void exitLexerAlt(AbstractGrammarParser.LexerAltContext ctx);

	void enterArgActionBlock(AbstractGrammarParser.ArgActionBlockContext ctx);
	void exitArgActionBlock(AbstractGrammarParser.ArgActionBlockContext ctx);

	void enterRuleModifiers(AbstractGrammarParser.RuleModifiersContext ctx);
	void exitRuleModifiers(AbstractGrammarParser.RuleModifiersContext ctx);

	void enterArgActionParameters(AbstractGrammarParser.ArgActionParametersContext ctx);
	void exitArgActionParameters(AbstractGrammarParser.ArgActionParametersContext ctx);

	void enterEbnf(AbstractGrammarParser.EbnfContext ctx);
	void exitEbnf(AbstractGrammarParser.EbnfContext ctx);

	void enterLexerCommandExpr(AbstractGrammarParser.LexerCommandExprContext ctx);
	void exitLexerCommandExpr(AbstractGrammarParser.LexerCommandExprContext ctx);
}