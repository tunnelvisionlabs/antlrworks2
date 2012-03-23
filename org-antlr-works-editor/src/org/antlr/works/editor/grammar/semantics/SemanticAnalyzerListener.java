/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.semantics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.netbeans.semantics.ObjectDecorator;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTree.ErrorNode;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ActionBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ActionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ActionExpressionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ActionScopeExpressionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ActionScopeNameContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.AltListContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.AlternativeContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ArgActionBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ArgActionParameterContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ArgActionParameterTypeContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ArgActionParameterTypePartContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ArgActionParametersContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.AtomContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.BlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.BlockSetContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.BlockSuffixContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.DelegateGrammarContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.DelegateGrammarsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.EbnfContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.EbnfSuffixContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ElementOptionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ElementOptionsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ElementsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ExceptionGroupContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ExceptionHandlerContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.FinallyClauseContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.GrammarSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.GrammarTypeContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.IdContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.IgnoredContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LabeledAltContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LabeledElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LabeledLexerElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerAltContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerAltListContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerAtomContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerCommandContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerCommandExprContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerCommandsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerElementsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerRuleBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LocalsSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ModeSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.NotSetContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.OptionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.OptionValueContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.OptionsSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.PrequelConstructContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.QidContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RangeContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RuleActionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RuleAltListContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RuleBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RuleModifierContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RuleModifiersContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RulePrequelContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RulePrequelsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RuleReturnsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RuleSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RulerefContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RulesContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.SetElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.TerminalContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ThrowsSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.TokenSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.TokensSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParserListener;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class SemanticAnalyzerListener implements GrammarParserListener {

    private final ObjectDecorator<Tree> treeDecorator;
    private final ObjectDecorator<Token> tokenDecorator;

    private final Map<String, Token> declaredRules = new HashMap<String, Token>();

    private final List<Token> unresolvedReferences = new ArrayList<Token>();

    public SemanticAnalyzerListener(@NonNull ObjectDecorator<Tree> treeDecorator, @NonNull ObjectDecorator<Token> tokenDecorator) {
        Parameters.notNull("treeDecorator", treeDecorator);
        Parameters.notNull("tokenDecorator", tokenDecorator);
        this.treeDecorator = treeDecorator;
        this.tokenDecorator = tokenDecorator;
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleref, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_terminal, version=0),
    })
    public void visitTerminal(ParseTree.TerminalNode<? extends Token> node) {
        NodeType nodeType = treeDecorator.getProperty(node.getParent(), GrammarTreeProperties.PROP_NODE_TYPE);

        Token symbol = node.getSymbol();
        int RuleIndex = node.getParent().getRuleContext().getRuleIndex();
        switch (symbol.getType()) {
        case GrammarParser.RULE_REF:
            switch (RuleIndex) {
            case GrammarParser.RULE_parserRuleSpec:
                nodeType = NodeType.RULE_DECL;
                declaredRules.put(symbol.getText(), symbol);
                break;

            case GrammarParser.RULE_ruleref:
                nodeType = NodeType.RULE_REF;
                unresolvedReferences.add(symbol);
                break;
            }
            break;

        case GrammarParser.TOKEN_REF:
            switch (RuleIndex) {
            case GrammarParser.RULE_lexerRule:
                nodeType = NodeType.TOKEN_DECL;
                declaredRules.put(symbol.getText(), symbol);
                break;

            case GrammarParser.RULE_terminal:
                nodeType = NodeType.TOKEN_REF;
                unresolvedReferences.add(symbol);
                break;
            }
            break;
        }

        if (nodeType != null) {
            tokenDecorator.putProperty(symbol, GrammarTreeProperties.PROP_NODE_TYPE, nodeType);
        }
    }

    @Override
    public void visitErrorNode(ErrorNode<? extends Token> node) {
        visitTerminal(node);
    }

    @Override
    public void enterEveryRule(ParserRuleContext<? extends Token> ctx) {
    }

    @Override
    public void exitEveryRule(ParserRuleContext<? extends Token> ctx) {
    }

    @Override
    public void enterParserRuleSpec(ParserRuleSpecContext ctx) {
    }

    @Override
    public void exitParserRuleSpec(ParserRuleSpecContext ctx) {
    }

    @Override
    public void enterAtom(AtomContext ctx) {
    }

    @Override
    public void exitAtom(AtomContext ctx) {
    }

    @Override
    public void enterRulePrequels(RulePrequelsContext ctx) {
    }

    @Override
    public void exitRulePrequels(RulePrequelsContext ctx) {
    }

    @Override
    public void enterRuleBlock(RuleBlockContext ctx) {
    }

    @Override
    public void exitRuleBlock(RuleBlockContext ctx) {
    }

    @Override
    public void enterNotSet(NotSetContext ctx) {
    }

    @Override
    public void exitNotSet(NotSetContext ctx) {
    }

    @Override
    public void enterLexerAltList(LexerAltListContext ctx) {
    }

    @Override
    public void exitLexerAltList(LexerAltListContext ctx) {
    }

    @Override
    public void enterArgActionParameter(ArgActionParameterContext ctx) {
    }

    @Override
    public void exitArgActionParameter(ArgActionParameterContext ctx) {
    }

    @Override
    public void enterRuleModifier(RuleModifierContext ctx) {
    }

    @Override
    public void exitRuleModifier(RuleModifierContext ctx) {
    }

    @Override
    public void enterRuleAltList(RuleAltListContext ctx) {
    }

    @Override
    public void exitRuleAltList(RuleAltListContext ctx) {
    }

    @Override
    public void enterTerminal(TerminalContext ctx) {
    }

    @Override
    public void exitTerminal(TerminalContext ctx) {
    }

    @Override
    public void enterThrowsSpec(ThrowsSpecContext ctx) {
    }

    @Override
    public void exitThrowsSpec(ThrowsSpecContext ctx) {
    }

    @Override
    public void enterAction(ActionContext ctx) {
    }

    @Override
    public void exitAction(ActionContext ctx) {
    }

    @Override
    public void enterActionScopeExpression(ActionScopeExpressionContext ctx) {
    }

    @Override
    public void exitActionScopeExpression(ActionScopeExpressionContext ctx) {
    }

    @Override
    public void enterLocalsSpec(LocalsSpecContext ctx) {
    }

    @Override
    public void exitLocalsSpec(LocalsSpecContext ctx) {
    }

    @Override
    public void enterModeSpec(ModeSpecContext ctx) {
    }

    @Override
    public void exitModeSpec(ModeSpecContext ctx) {
    }

    @Override
    public void enterElements(ElementsContext ctx) {
    }

    @Override
    public void exitElements(ElementsContext ctx) {
    }

    @Override
    public void enterOption(OptionContext ctx) {
    }

    @Override
    public void exitOption(OptionContext ctx) {
    }

    @Override
    public void enterElement(ElementContext ctx) {
    }

    @Override
    public void exitElement(ElementContext ctx) {
    }

    @Override
    public void enterElementOptions(ElementOptionsContext ctx) {
    }

    @Override
    public void exitElementOptions(ElementOptionsContext ctx) {
    }

    @Override
    public void enterLexerElement(LexerElementContext ctx) {
    }

    @Override
    public void exitLexerElement(LexerElementContext ctx) {
    }

    @Override
    public void enterAlternative(AlternativeContext ctx) {
    }

    @Override
    public void exitAlternative(AlternativeContext ctx) {
    }

    @Override
    public void enterLexerCommandExpr(LexerCommandExprContext ctx) {
    }

    @Override
    public void exitLexerCommandExpr(LexerCommandExprContext ctx) {
    }

    @Override
    public void enterGrammarType(GrammarTypeContext ctx) {
    }

    @Override
    public void exitGrammarType(GrammarTypeContext ctx) {
    }

    @Override
    public void enterRuleAction(RuleActionContext ctx) {
    }

    @Override
    public void exitRuleAction(RuleActionContext ctx) {
    }

    @Override
    public void enterEbnfSuffix(EbnfSuffixContext ctx) {
    }

    @Override
    public void exitEbnfSuffix(EbnfSuffixContext ctx) {
    }

    @Override
    public void enterExceptionGroup(ExceptionGroupContext ctx) {
    }

    @Override
    public void exitExceptionGroup(ExceptionGroupContext ctx) {
    }

    @Override
    public void enterRulePrequel(RulePrequelContext ctx) {
    }

    @Override
    public void exitRulePrequel(RulePrequelContext ctx) {
    }

    @Override
    public void enterLexerBlock(LexerBlockContext ctx) {
    }

    @Override
    public void exitLexerBlock(LexerBlockContext ctx) {
    }

    @Override
    public void enterSetElement(SetElementContext ctx) {
    }

    @Override
    public void exitSetElement(SetElementContext ctx) {
    }

    @Override
    public void enterBlockSet(BlockSetContext ctx) {
    }

    @Override
    public void exitBlockSet(BlockSetContext ctx) {
    }

    @Override
    public void enterActionScopeName(ActionScopeNameContext ctx) {
    }

    @Override
    public void exitActionScopeName(ActionScopeNameContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_labeledAlt, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=0),
    })
    public void enterLabeledAlt(LabeledAltContext ctx) {
        if (ctx.getChildCount() == 3 && ctx.getChild(2) instanceof IdContext) {
            treeDecorator.putProperty(ctx, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.ALT_LABEL);
        }
    }

    @Override
    public void exitLabeledAlt(LabeledAltContext ctx) {
    }

    @Override
    public void enterArgActionParameterType(ArgActionParameterTypeContext ctx) {
    }

    @Override
    public void exitArgActionParameterType(ArgActionParameterTypeContext ctx) {
    }

    @Override
    public void enterLexerAtom(LexerAtomContext ctx) {
    }

    @Override
    public void exitLexerAtom(LexerAtomContext ctx) {
    }

    @Override
    public void enterLabeledElement(LabeledElementContext ctx) {
    }

    @Override
    public void exitLabeledElement(LabeledElementContext ctx) {
    }

    @Override
    public void enterLexerRuleBlock(LexerRuleBlockContext ctx) {
    }

    @Override
    public void exitLexerRuleBlock(LexerRuleBlockContext ctx) {
    }

    @Override
    public void enterFinallyClause(FinallyClauseContext ctx) {
    }

    @Override
    public void exitFinallyClause(FinallyClauseContext ctx) {
    }

    @Override
    public void enterIgnored(IgnoredContext ctx) {
    }

    @Override
    public void exitIgnored(IgnoredContext ctx) {
    }

    @Override
    public void enterGrammarSpec(GrammarSpecContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0)
    public void exitGrammarSpec(GrammarSpecContext ctx) {
        for (Token token : unresolvedReferences) {
            String text = token.getText();
            if (text == null || text.isEmpty()) {
                continue;
            }

            Token decl = declaredRules.get(text);
            if (decl != null) {
                tokenDecorator.putProperty(token, GrammarTreeProperties.PROP_TARGET, decl);
            }
        }
    }

    @Override
    public void enterDelegateGrammar(DelegateGrammarContext ctx) {
    }

    @Override
    public void exitDelegateGrammar(DelegateGrammarContext ctx) {
    }

    @Override
    public void enterLexerElements(LexerElementsContext ctx) {
    }

    @Override
    public void exitLexerElements(LexerElementsContext ctx) {
    }

    @Override
    public void enterRange(RangeContext ctx) {
    }

    @Override
    public void exitRange(RangeContext ctx) {
    }

    @Override
    public void enterTokenSpec(TokenSpecContext ctx) {
    }

    @Override
    public void exitTokenSpec(TokenSpecContext ctx) {
    }

    @Override
    public void enterBlock(BlockContext ctx) {
    }

    @Override
    public void exitBlock(BlockContext ctx) {
    }

    @Override
    public void enterArgActionParameterTypePart(ArgActionParameterTypePartContext ctx) {
    }

    @Override
    public void exitArgActionParameterTypePart(ArgActionParameterTypePartContext ctx) {
    }

    @Override
    public void enterLexerRule(LexerRuleContext ctx) {
    }

    @Override
    public void exitLexerRule(LexerRuleContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_labeledLexerElement, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=0),
    })
    public void enterLabeledLexerElement(LabeledLexerElementContext ctx) {
        if (ctx.getChildCount() == 0 || !(ctx.getChild(0) instanceof IdContext)) {
            return;
        }

        treeDecorator.putProperty(ctx, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.LABEL_DECL);
    }

    @Override
    public void exitLabeledLexerElement(LabeledLexerElementContext ctx) {
    }

    @Override
    public void enterDelegateGrammars(DelegateGrammarsContext ctx) {
    }

    @Override
    public void exitDelegateGrammars(DelegateGrammarsContext ctx) {
    }

    @Override
    public void enterActionExpression(ActionExpressionContext ctx) {
    }

    @Override
    public void exitActionExpression(ActionExpressionContext ctx) {
    }

    @Override
    public void enterRuleref(RulerefContext ctx) {
    }

    @Override
    public void exitRuleref(RulerefContext ctx) {
    }

    @Override
    public void enterBlockSuffix(BlockSuffixContext ctx) {
    }

    @Override
    public void exitBlockSuffix(BlockSuffixContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_modeSpec, version=0),
    })
    public void enterId(IdContext ctx) {
        if (ctx.start != null && ctx.parent != null) {

            int caller = ctx.parent.getRuleIndex();
            switch (caller) {
            case GrammarParser.RULE_grammarSpec:
                tokenDecorator.putProperty(ctx.start, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.GRAMMAR_DECL);
                break;

            case GrammarParser.RULE_modeSpec:
                tokenDecorator.putProperty(ctx.start, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.MODE_DECL);
                break;
            }

        }
    }

    @Override
    public void exitId(IdContext ctx) {
    }

    @Override
    public void enterActionBlock(ActionBlockContext ctx) {
    }

    @Override
    public void exitActionBlock(ActionBlockContext ctx) {
    }

    @Override
    public void enterElementOption(ElementOptionContext ctx) {
    }

    @Override
    public void exitElementOption(ElementOptionContext ctx) {
    }

    @Override
    public void enterRuleSpec(RuleSpecContext ctx) {
    }

    @Override
    public void exitRuleSpec(RuleSpecContext ctx) {
    }

    @Override
    public void enterExceptionHandler(ExceptionHandlerContext ctx) {
    }

    @Override
    public void exitExceptionHandler(ExceptionHandlerContext ctx) {
    }

    @Override
    public void enterTokensSpec(TokensSpecContext ctx) {
    }

    @Override
    public void exitTokensSpec(TokensSpecContext ctx) {
    }

    @Override
    public void enterRuleReturns(RuleReturnsContext ctx) {
    }

    @Override
    public void exitRuleReturns(RuleReturnsContext ctx) {
    }

    @Override
    public void enterQid(QidContext ctx) {
    }

    @Override
    public void exitQid(QidContext ctx) {
    }

    @Override
    public void enterOptionsSpec(OptionsSpecContext ctx) {
    }

    @Override
    public void exitOptionsSpec(OptionsSpecContext ctx) {
    }

    @Override
    public void enterAltList(AltListContext ctx) {
    }

    @Override
    public void exitAltList(AltListContext ctx) {
    }

    @Override
    public void enterPrequelConstruct(PrequelConstructContext ctx) {
    }

    @Override
    public void exitPrequelConstruct(PrequelConstructContext ctx) {
    }

    @Override
    public void enterRules(RulesContext ctx) {
    }

    @Override
    public void exitRules(RulesContext ctx) {
    }

    @Override
    public void enterLexerCommands(LexerCommandsContext ctx) {
    }

    @Override
    public void exitLexerCommands(LexerCommandsContext ctx) {
    }

    @Override
    public void enterOptionValue(OptionValueContext ctx) {
    }

    @Override
    public void exitOptionValue(OptionValueContext ctx) {
    }

    @Override
    public void enterLexerAlt(LexerAltContext ctx) {
    }

    @Override
    public void exitLexerAlt(LexerAltContext ctx) {
    }

    @Override
    public void enterArgActionBlock(ArgActionBlockContext ctx) {
    }

    @Override
    public void exitArgActionBlock(ArgActionBlockContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommand, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=0),
    })
    public void enterLexerCommand(LexerCommandContext ctx) {
        if (ctx.getChildCount() == 0 || !(ctx.getChild(0) instanceof IdContext)) {
            return;
        }

        treeDecorator.putProperty(ctx, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.LEXER_ACTION);
    }

    @Override
    public void exitLexerCommand(LexerCommandContext ctx) {
    }

    @Override
    public void enterRuleModifiers(RuleModifiersContext ctx) {
    }

    @Override
    public void exitRuleModifiers(RuleModifiersContext ctx) {
    }

    @Override
    public void enterArgActionParameters(ArgActionParametersContext ctx) {
    }

    @Override
    public void exitArgActionParameters(ArgActionParametersContext ctx) {
    }

    @Override
    public void enterEbnf(EbnfContext ctx) {
    }

    @Override
    public void exitEbnf(EbnfContext ctx) {
    }

}
