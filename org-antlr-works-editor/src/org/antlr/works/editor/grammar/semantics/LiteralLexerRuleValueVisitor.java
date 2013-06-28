/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.semantics;

import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.TerminalContext;
import org.antlr.works.editor.grammar.experimental.generated.GrammarParserBaseVisitor;

/**
 *
 * @author Sam Harwell
 */
public class LiteralLexerRuleValueVisitor extends GrammarParserBaseVisitor<TerminalNode> {
    public static final LiteralLexerRuleValueVisitor INSTANCE = new LiteralLexerRuleValueVisitor();

    @Override
    protected TerminalNode aggregateResult(TerminalNode aggregate, TerminalNode nextResult) {
        return aggregate != null ? aggregate : nextResult;
    }

    @Override
    protected boolean shouldVisitNextChild(RuleNode node, TerminalNode currentResult) {
        return currentResult == null;
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_terminal, version=1, dependents=Dependents.PARENTS)
    public TerminalNode visitTerminal(TerminalContext ctx) {
        return ctx.STRING_LITERAL();
    }

}
