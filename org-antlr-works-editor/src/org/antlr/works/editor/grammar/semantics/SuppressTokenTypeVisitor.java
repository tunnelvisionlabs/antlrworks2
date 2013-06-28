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
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.IdContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerCommandNameContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.generated.GrammarParserBaseVisitor;

/**
 * Token types are suppressed for fragment rules and for rules containing
 * the lexer commands {@code more}, {@code skip}, and/or {@code type}.
 */
public class SuppressTokenTypeVisitor extends GrammarParserBaseVisitor<Boolean> {
    public static final SuppressTokenTypeVisitor INSTANCE = new SuppressTokenTypeVisitor();

    @Override
    protected Boolean defaultResult() {
        return false;
    }

    @Override
    protected Boolean aggregateResult(Boolean aggregate, Boolean nextResult) {
        return aggregate || nextResult;
    }

    @Override
    protected boolean shouldVisitNextChild(RuleNode node, Boolean currentResult) {
        // have to stop when we reach true
        return !currentResult;
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0, dependents=Dependents.PARENTS)
    public Boolean visitLexerRule(LexerRuleContext ctx) {
        if (ctx.FRAGMENT() != null) {
            return true;
        }
        return super.visitLexerRule(ctx);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommandName, version=1, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1, dependents=Dependents.SELF),
    })
    public Boolean visitLexerCommandName(LexerCommandNameContext ctx) {
        IdContext id = ctx.id();
        if (id != null && id.start != null) {
            String command = id.start.getText();
            if ("type".equals(command) || "more".equals(command) || "skip".equals(command)) {
                return true;
            }
        }
        return super.visitLexerCommandName(ctx);
    }

}
