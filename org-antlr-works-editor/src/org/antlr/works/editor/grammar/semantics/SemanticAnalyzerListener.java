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
    public void enterRule(parserRuleContext ctx) {
    }

    @Override
    public void exitRule(parserRuleContext ctx) {
    }

    @Override
    public void enterRule(atomContext ctx) {
    }

    @Override
    public void exitRule(atomContext ctx) {
    }

    @Override
    public void enterRule(rulePrequelsContext ctx) {
    }

    @Override
    public void exitRule(rulePrequelsContext ctx) {
    }

    @Override
    public void enterRule(ruleBlockContext ctx) {
    }

    @Override
    public void exitRule(ruleBlockContext ctx) {
    }

    @Override
    public void enterRule(notSetContext ctx) {
    }

    @Override
    public void exitRule(notSetContext ctx) {
    }

    @Override
    public void enterRule(lexerAltListContext ctx) {
    }

    @Override
    public void exitRule(lexerAltListContext ctx) {
    }

    @Override
    public void enterRule(argActionParameterContext ctx) {
    }

    @Override
    public void exitRule(argActionParameterContext ctx) {
    }

    @Override
    public void enterRule(ruleModifierContext ctx) {
    }

    @Override
    public void exitRule(ruleModifierContext ctx) {
    }

    @Override
    public void enterRule(ruleAltListContext ctx) {
    }

    @Override
    public void exitRule(ruleAltListContext ctx) {
    }

    @Override
    public void enterRule(terminalContext ctx) {
    }

    @Override
    public void exitRule(terminalContext ctx) {
    }

    @Override
    public void enterRule(throwsSpecContext ctx) {
    }

    @Override
    public void exitRule(throwsSpecContext ctx) {
    }

    @Override
    public void enterRule(actionContext ctx) {
    }

    @Override
    public void exitRule(actionContext ctx) {
    }

    @Override
    public void enterRule(actionScopeExpressionContext ctx) {
    }

    @Override
    public void exitRule(actionScopeExpressionContext ctx) {
    }

    @Override
    public void enterRule(localsSpecContext ctx) {
    }

    @Override
    public void exitRule(localsSpecContext ctx) {
    }

    @Override
    public void enterRule(modeSpecContext ctx) {
    }

    @Override
    public void exitRule(modeSpecContext ctx) {
    }

    @Override
    public void enterRule(elementsContext ctx) {
    }

    @Override
    public void exitRule(elementsContext ctx) {
    }

    @Override
    public void enterRule(optionContext ctx) {
    }

    @Override
    public void exitRule(optionContext ctx) {
    }

    @Override
    public void enterRule(elementContext ctx) {
    }

    @Override
    public void exitRule(elementContext ctx) {
    }

    @Override
    public void enterRule(elementOptionsContext ctx) {
    }

    @Override
    public void exitRule(elementOptionsContext ctx) {
    }

    @Override
    public void enterRule(lexerElementContext ctx) {
    }

    @Override
    public void exitRule(lexerElementContext ctx) {
    }

    @Override
    public void enterRule(alternativeContext ctx) {
    }

    @Override
    public void exitRule(alternativeContext ctx) {
    }

    @Override
    public void enterRule(lexerActionExprContext ctx) {
    }

    @Override
    public void exitRule(lexerActionExprContext ctx) {
    }

    @Override
    public void enterRule(grammarTypeContext ctx) {
    }

    @Override
    public void exitRule(grammarTypeContext ctx) {
    }

    @Override
    public void enterRule(ruleActionContext ctx) {
    }

    @Override
    public void exitRule(ruleActionContext ctx) {
    }

    @Override
    public void enterRule(ebnfSuffixContext ctx) {
    }

    @Override
    public void exitRule(ebnfSuffixContext ctx) {
    }

    @Override
    public void enterRule(exceptionGroupContext ctx) {
    }

    @Override
    public void exitRule(exceptionGroupContext ctx) {
    }

    @Override
    public void enterRule(rulePrequelContext ctx) {
    }

    @Override
    public void exitRule(rulePrequelContext ctx) {
    }

    @Override
    public void enterRule(lexerBlockContext ctx) {
    }

    @Override
    public void exitRule(lexerBlockContext ctx) {
    }

    @Override
    public void enterRule(setElementContext ctx) {
    }

    @Override
    public void exitRule(setElementContext ctx) {
    }

    @Override
    public void enterRule(blockSetContext ctx) {
    }

    @Override
    public void exitRule(blockSetContext ctx) {
    }

    @Override
    public void enterRule(actionScopeNameContext ctx) {
    }

    @Override
    public void exitRule(actionScopeNameContext ctx) {
    }

    @Override
    public void enterRule(labeledAltContext ctx) {
        if (ctx.getChildCount() == 3 && ctx.getChild(2) instanceof idContext) {
            treeDecorator.putProperty(ctx, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.ALT_LABEL);
        }
    }

    @Override
    public void exitRule(labeledAltContext ctx) {
    }

    @Override
    public void enterRule(argActionParameterTypeContext ctx) {
    }

    @Override
    public void exitRule(argActionParameterTypeContext ctx) {
    }

    @Override
    public void enterRule(lexerAtomContext ctx) {
    }

    @Override
    public void exitRule(lexerAtomContext ctx) {
    }

    @Override
    public void enterRule(labeledElementContext ctx) {
    }

    @Override
    public void exitRule(labeledElementContext ctx) {
    }

    @Override
    public void enterRule(lexerRuleBlockContext ctx) {
    }

    @Override
    public void exitRule(lexerRuleBlockContext ctx) {
    }

    @Override
    public void enterRule(finallyClauseContext ctx) {
    }

    @Override
    public void exitRule(finallyClauseContext ctx) {
    }

    @Override
    public void enterRule(ignoredContext ctx) {
    }

    @Override
    public void exitRule(ignoredContext ctx) {
    }

    @Override
    public void enterRule(grammarSpecContext ctx) {
    }

    @Override
    public void exitRule(grammarSpecContext ctx) {
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
    public void enterRule(delegateGrammarContext ctx) {
    }

    @Override
    public void exitRule(delegateGrammarContext ctx) {
    }

    @Override
    public void enterRule(lexerElementsContext ctx) {
    }

    @Override
    public void exitRule(lexerElementsContext ctx) {
    }

    @Override
    public void enterRule(rangeContext ctx) {
    }

    @Override
    public void exitRule(rangeContext ctx) {
    }

    @Override
    public void enterRule(tokenSpecContext ctx) {
    }

    @Override
    public void exitRule(tokenSpecContext ctx) {
    }

    @Override
    public void enterRule(blockContext ctx) {
    }

    @Override
    public void exitRule(blockContext ctx) {
    }

    @Override
    public void enterRule(argActionParameterTypePartContext ctx) {
    }

    @Override
    public void exitRule(argActionParameterTypePartContext ctx) {
    }

    @Override
    public void enterRule(lexerRuleContext ctx) {
    }

    @Override
    public void exitRule(lexerRuleContext ctx) {
    }

    @Override
    public void enterRule(labeledLexerElementContext ctx) {
        if (ctx.getChildCount() == 0 || !(ctx.getChild(0) instanceof idContext)) {
            return;
        }

        treeDecorator.putProperty(ctx, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.LABEL_DECL);
    }

    @Override
    public void exitRule(labeledLexerElementContext ctx) {
    }

    @Override
    public void enterRule(delegateGrammarsContext ctx) {
    }

    @Override
    public void exitRule(delegateGrammarsContext ctx) {
    }

    @Override
    public void enterRule(actionExpressionContext ctx) {
    }

    @Override
    public void exitRule(actionExpressionContext ctx) {
    }

    @Override
    public void enterRule(rulerefContext ctx) {
    }

    @Override
    public void exitRule(rulerefContext ctx) {
    }

    @Override
    public void enterRule(blockSuffixContext ctx) {
    }

    @Override
    public void exitRule(blockSuffixContext ctx) {
    }

    @Override
    public void enterRule(idContext ctx) {
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
    public void exitRule(idContext ctx) {
    }

    @Override
    public void enterRule(actionBlockContext ctx) {
    }

    @Override
    public void exitRule(actionBlockContext ctx) {
    }

    @Override
    public void enterRule(elementOptionContext ctx) {
    }

    @Override
    public void exitRule(elementOptionContext ctx) {
    }

    @Override
    public void enterRule(ruleContext ctx) {
    }

    @Override
    public void exitRule(ruleContext ctx) {
    }

    @Override
    public void enterRule(exceptionHandlerContext ctx) {
    }

    @Override
    public void exitRule(exceptionHandlerContext ctx) {
    }

    @Override
    public void enterRule(tokensSpecContext ctx) {
    }

    @Override
    public void exitRule(tokensSpecContext ctx) {
    }

    @Override
    public void enterRule(ruleReturnsContext ctx) {
    }

    @Override
    public void exitRule(ruleReturnsContext ctx) {
    }

    @Override
    public void enterRule(qidContext ctx) {
    }

    @Override
    public void exitRule(qidContext ctx) {
    }

    @Override
    public void enterRule(optionsSpecContext ctx) {
    }

    @Override
    public void exitRule(optionsSpecContext ctx) {
    }

    @Override
    public void enterRule(altListContext ctx) {
    }

    @Override
    public void exitRule(altListContext ctx) {
    }

    @Override
    public void enterRule(prequelConstructContext ctx) {
    }

    @Override
    public void exitRule(prequelConstructContext ctx) {
    }

    @Override
    public void enterRule(rulesContext ctx) {
    }

    @Override
    public void exitRule(rulesContext ctx) {
    }

    @Override
    public void enterRule(lexerActionsContext ctx) {
    }

    @Override
    public void exitRule(lexerActionsContext ctx) {
    }

    @Override
    public void enterRule(optionValueContext ctx) {
    }

    @Override
    public void exitRule(optionValueContext ctx) {
    }

    @Override
    public void enterRule(lexerAltContext ctx) {
    }

    @Override
    public void exitRule(lexerAltContext ctx) {
    }

    @Override
    public void enterRule(argActionBlockContext ctx) {
    }

    @Override
    public void exitRule(argActionBlockContext ctx) {
    }

    @Override
    public void enterRule(lexerActionContext ctx) {
        if (ctx.getChildCount() == 0 || !(ctx.getChild(0) instanceof idContext)) {
            return;
        }

        treeDecorator.putProperty(ctx, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.LEXER_ACTION);
    }

    @Override
    public void exitRule(lexerActionContext ctx) {
    }

    @Override
    public void enterRule(ruleModifiersContext ctx) {
    }

    @Override
    public void exitRule(ruleModifiersContext ctx) {
    }

    @Override
    public void enterRule(argActionParametersContext ctx) {
    }

    @Override
    public void exitRule(argActionParametersContext ctx) {
    }

    @Override
    public void enterRule(ebnfContext ctx) {
    }

    @Override
    public void exitRule(ebnfContext ctx) {
    }

}
