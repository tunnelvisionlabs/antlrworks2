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
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParser.actionBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.actionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.actionExpressionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.actionScopeExpressionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.actionScopeNameContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.altListContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.alternativeContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.argActionBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.argActionParameterContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.argActionParameterTypeContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.argActionParameterTypePartContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.argActionParametersContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.atomContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.blockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.blockSetContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.blockSuffixContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.delegateGrammarContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.delegateGrammarsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ebnfContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ebnfSuffixContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.elementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.elementOptionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.elementOptionsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.elementsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.exceptionGroupContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.exceptionHandlerContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.finallyClauseContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.grammarSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.grammarTypeContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.idContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ignoredContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.labeledAltContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.labeledElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.labeledLexerElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerActionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerActionExprContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerActionsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerAltContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerAltListContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerAtomContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerElementsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerRuleBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.localsSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.modeSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.notSetContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.optionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.optionValueContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.optionsSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.parserRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.prequelConstructContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.qidContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.rangeContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleActionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleAltListContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleModifierContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleModifiersContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.rulePrequelContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.rulePrequelsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleReturnsContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.rulerefContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.rulesContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.setElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.terminalContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.throwsSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.tokenSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.tokensSpecContext;
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
    public void visitTerminal(ParserRuleContext<Token> ctx, Token symbol) {
        NodeType nodeType = treeDecorator.getProperty(ctx, GrammarTreeProperties.PROP_NODE_TYPE);

        switch (symbol.getType()) {
        case GrammarParser.RULE_REF:
            switch (ctx.ruleIndex) {
            case GrammarParser.RULE_parserRule:
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
            switch (ctx.ruleIndex) {
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
    public void enterEveryRule(ParserRuleContext<Token> ctx) {
    }

    @Override
    public void exitEveryRule(ParserRuleContext<Token> ctx) {
    }

    @Override
    public void parserRuleEnter(parserRuleContext ctx) {
    }

    @Override
    public void parserRuleExit(parserRuleContext ctx) {
    }

    @Override
    public void atomEnter(atomContext ctx) {
    }

    @Override
    public void atomExit(atomContext ctx) {
    }

    @Override
    public void rulePrequelsEnter(rulePrequelsContext ctx) {
    }

    @Override
    public void rulePrequelsExit(rulePrequelsContext ctx) {
    }

    @Override
    public void ruleBlockEnter(ruleBlockContext ctx) {
    }

    @Override
    public void ruleBlockExit(ruleBlockContext ctx) {
    }

    @Override
    public void notSetEnter(notSetContext ctx) {
    }

    @Override
    public void notSetExit(notSetContext ctx) {
    }

    @Override
    public void lexerAltListEnter(lexerAltListContext ctx) {
    }

    @Override
    public void lexerAltListExit(lexerAltListContext ctx) {
    }

    @Override
    public void argActionParameterEnter(argActionParameterContext ctx) {
    }

    @Override
    public void argActionParameterExit(argActionParameterContext ctx) {
    }

    @Override
    public void ruleModifierEnter(ruleModifierContext ctx) {
    }

    @Override
    public void ruleModifierExit(ruleModifierContext ctx) {
    }

    @Override
    public void ruleAltListEnter(ruleAltListContext ctx) {
    }

    @Override
    public void ruleAltListExit(ruleAltListContext ctx) {
    }

    @Override
    public void terminalEnter(terminalContext ctx) {
    }

    @Override
    public void terminalExit(terminalContext ctx) {
    }

    @Override
    public void throwsSpecEnter(throwsSpecContext ctx) {
    }

    @Override
    public void throwsSpecExit(throwsSpecContext ctx) {
    }

    @Override
    public void actionEnter(actionContext ctx) {
    }

    @Override
    public void actionExit(actionContext ctx) {
    }

    @Override
    public void actionScopeExpressionEnter(actionScopeExpressionContext ctx) {
    }

    @Override
    public void actionScopeExpressionExit(actionScopeExpressionContext ctx) {
    }

    @Override
    public void localsSpecEnter(localsSpecContext ctx) {
    }

    @Override
    public void localsSpecExit(localsSpecContext ctx) {
    }

    @Override
    public void modeSpecEnter(modeSpecContext ctx) {
    }

    @Override
    public void modeSpecExit(modeSpecContext ctx) {
    }

    @Override
    public void elementsEnter(elementsContext ctx) {
    }

    @Override
    public void elementsExit(elementsContext ctx) {
    }

    @Override
    public void optionEnter(optionContext ctx) {
    }

    @Override
    public void optionExit(optionContext ctx) {
    }

    @Override
    public void elementEnter(elementContext ctx) {
    }

    @Override
    public void elementExit(elementContext ctx) {
    }

    @Override
    public void elementOptionsEnter(elementOptionsContext ctx) {
    }

    @Override
    public void elementOptionsExit(elementOptionsContext ctx) {
    }

    @Override
    public void lexerElementEnter(lexerElementContext ctx) {
    }

    @Override
    public void lexerElementExit(lexerElementContext ctx) {
    }

    @Override
    public void alternativeEnter(alternativeContext ctx) {
    }

    @Override
    public void alternativeExit(alternativeContext ctx) {
    }

    @Override
    public void lexerActionExprEnter(lexerActionExprContext ctx) {
    }

    @Override
    public void lexerActionExprExit(lexerActionExprContext ctx) {
    }

    @Override
    public void grammarTypeEnter(grammarTypeContext ctx) {
    }

    @Override
    public void grammarTypeExit(grammarTypeContext ctx) {
    }

    @Override
    public void ruleActionEnter(ruleActionContext ctx) {
    }

    @Override
    public void ruleActionExit(ruleActionContext ctx) {
    }

    @Override
    public void ebnfSuffixEnter(ebnfSuffixContext ctx) {
    }

    @Override
    public void ebnfSuffixExit(ebnfSuffixContext ctx) {
    }

    @Override
    public void exceptionGroupEnter(exceptionGroupContext ctx) {
    }

    @Override
    public void exceptionGroupExit(exceptionGroupContext ctx) {
    }

    @Override
    public void rulePrequelEnter(rulePrequelContext ctx) {
    }

    @Override
    public void rulePrequelExit(rulePrequelContext ctx) {
    }

    @Override
    public void lexerBlockEnter(lexerBlockContext ctx) {
    }

    @Override
    public void lexerBlockExit(lexerBlockContext ctx) {
    }

    @Override
    public void setElementEnter(setElementContext ctx) {
    }

    @Override
    public void setElementExit(setElementContext ctx) {
    }

    @Override
    public void blockSetEnter(blockSetContext ctx) {
    }

    @Override
    public void blockSetExit(blockSetContext ctx) {
    }

    @Override
    public void actionScopeNameEnter(actionScopeNameContext ctx) {
    }

    @Override
    public void actionScopeNameExit(actionScopeNameContext ctx) {
    }

    @Override
    public void labeledAltEnter(labeledAltContext ctx) {
        if (ctx.getChildCount() == 3 && ctx.getChild(2) instanceof idContext) {
            treeDecorator.putProperty(ctx, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.ALT_LABEL);
        }
    }

    @Override
    public void labeledAltExit(labeledAltContext ctx) {
    }

    @Override
    public void argActionParameterTypeEnter(argActionParameterTypeContext ctx) {
    }

    @Override
    public void argActionParameterTypeExit(argActionParameterTypeContext ctx) {
    }

    @Override
    public void lexerAtomEnter(lexerAtomContext ctx) {
    }

    @Override
    public void lexerAtomExit(lexerAtomContext ctx) {
    }

    @Override
    public void labeledElementEnter(labeledElementContext ctx) {
    }

    @Override
    public void labeledElementExit(labeledElementContext ctx) {
    }

    @Override
    public void lexerRuleBlockEnter(lexerRuleBlockContext ctx) {
    }

    @Override
    public void lexerRuleBlockExit(lexerRuleBlockContext ctx) {
    }

    @Override
    public void finallyClauseEnter(finallyClauseContext ctx) {
    }

    @Override
    public void finallyClauseExit(finallyClauseContext ctx) {
    }

    @Override
    public void ignoredEnter(ignoredContext ctx) {
    }

    @Override
    public void ignoredExit(ignoredContext ctx) {
    }

    @Override
    public void grammarSpecEnter(grammarSpecContext ctx) {
    }

    @Override
    public void grammarSpecExit(grammarSpecContext ctx) {
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
    public void delegateGrammarEnter(delegateGrammarContext ctx) {
    }

    @Override
    public void delegateGrammarExit(delegateGrammarContext ctx) {
    }

    @Override
    public void lexerElementsEnter(lexerElementsContext ctx) {
    }

    @Override
    public void lexerElementsExit(lexerElementsContext ctx) {
    }

    @Override
    public void rangeEnter(rangeContext ctx) {
    }

    @Override
    public void rangeExit(rangeContext ctx) {
    }

    @Override
    public void tokenSpecEnter(tokenSpecContext ctx) {
    }

    @Override
    public void tokenSpecExit(tokenSpecContext ctx) {
    }

    @Override
    public void blockEnter(blockContext ctx) {
    }

    @Override
    public void blockExit(blockContext ctx) {
    }

    @Override
    public void argActionParameterTypePartEnter(argActionParameterTypePartContext ctx) {
    }

    @Override
    public void argActionParameterTypePartExit(argActionParameterTypePartContext ctx) {
    }

    @Override
    public void lexerRuleEnter(lexerRuleContext ctx) {
    }

    @Override
    public void lexerRuleExit(lexerRuleContext ctx) {
    }

    @Override
    public void labeledLexerElementEnter(labeledLexerElementContext ctx) {
        if (ctx.getChildCount() == 0 || !(ctx.getChild(0) instanceof idContext)) {
            return;
        }

        treeDecorator.putProperty(ctx, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.LABEL_DECL);
    }

    @Override
    public void labeledLexerElementExit(labeledLexerElementContext ctx) {
    }

    @Override
    public void delegateGrammarsEnter(delegateGrammarsContext ctx) {
    }

    @Override
    public void delegateGrammarsExit(delegateGrammarsContext ctx) {
    }

    @Override
    public void actionExpressionEnter(actionExpressionContext ctx) {
    }

    @Override
    public void actionExpressionExit(actionExpressionContext ctx) {
    }

    @Override
    public void rulerefEnter(rulerefContext ctx) {
    }

    @Override
    public void rulerefExit(rulerefContext ctx) {
    }

    @Override
    public void blockSuffixEnter(blockSuffixContext ctx) {
    }

    @Override
    public void blockSuffixExit(blockSuffixContext ctx) {
    }

    @Override
    public void idEnter(idContext ctx) {
        if (ctx.start != null && ctx.parent instanceof ParserRuleContext<?>) {

            int caller = ((ParserRuleContext<?>)ctx.parent).ruleIndex;
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
    public void idExit(idContext ctx) {
    }

    @Override
    public void actionBlockEnter(actionBlockContext ctx) {
    }

    @Override
    public void actionBlockExit(actionBlockContext ctx) {
    }

    @Override
    public void elementOptionEnter(elementOptionContext ctx) {
    }

    @Override
    public void elementOptionExit(elementOptionContext ctx) {
    }

    @Override
    public void ruleEnter(ruleContext ctx) {
    }

    @Override
    public void ruleExit(ruleContext ctx) {
    }

    @Override
    public void exceptionHandlerEnter(exceptionHandlerContext ctx) {
    }

    @Override
    public void exceptionHandlerExit(exceptionHandlerContext ctx) {
    }

    @Override
    public void tokensSpecEnter(tokensSpecContext ctx) {
    }

    @Override
    public void tokensSpecExit(tokensSpecContext ctx) {
    }

    @Override
    public void ruleReturnsEnter(ruleReturnsContext ctx) {
    }

    @Override
    public void ruleReturnsExit(ruleReturnsContext ctx) {
    }

    @Override
    public void qidEnter(qidContext ctx) {
    }

    @Override
    public void qidExit(qidContext ctx) {
    }

    @Override
    public void optionsSpecEnter(optionsSpecContext ctx) {
    }

    @Override
    public void optionsSpecExit(optionsSpecContext ctx) {
    }

    @Override
    public void altListEnter(altListContext ctx) {
    }

    @Override
    public void altListExit(altListContext ctx) {
    }

    @Override
    public void prequelConstructEnter(prequelConstructContext ctx) {
    }

    @Override
    public void prequelConstructExit(prequelConstructContext ctx) {
    }

    @Override
    public void rulesEnter(rulesContext ctx) {
    }

    @Override
    public void rulesExit(rulesContext ctx) {
    }

    @Override
    public void lexerActionsEnter(lexerActionsContext ctx) {
    }

    @Override
    public void lexerActionsExit(lexerActionsContext ctx) {
    }

    @Override
    public void optionValueEnter(optionValueContext ctx) {
    }

    @Override
    public void optionValueExit(optionValueContext ctx) {
    }

    @Override
    public void lexerAltEnter(lexerAltContext ctx) {
    }

    @Override
    public void lexerAltExit(lexerAltContext ctx) {
    }

    @Override
    public void argActionBlockEnter(argActionBlockContext ctx) {
    }

    @Override
    public void argActionBlockExit(argActionBlockContext ctx) {
    }

    @Override
    public void lexerActionEnter(lexerActionContext ctx) {
        if (ctx.getChildCount() == 0 || !(ctx.getChild(0) instanceof idContext)) {
            return;
        }

        treeDecorator.putProperty(ctx, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.LEXER_ACTION);
    }

    @Override
    public void lexerActionExit(lexerActionContext ctx) {
    }

    @Override
    public void ruleModifiersEnter(ruleModifiersContext ctx) {
    }

    @Override
    public void ruleModifiersExit(ruleModifiersContext ctx) {
    }

    @Override
    public void argActionParametersEnter(argActionParametersContext ctx) {
    }

    @Override
    public void argActionParametersExit(argActionParametersContext ctx) {
    }

    @Override
    public void ebnfEnter(ebnfContext ctx) {
    }

    @Override
    public void ebnfExit(ebnfContext ctx) {
    }

}
