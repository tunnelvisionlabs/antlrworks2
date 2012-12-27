/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ActionBlockContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ArgActionBlockContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LabeledAltContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LabeledElementContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RulerefContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.TerminalContext;
import org.antlr.works.editor.grammar.experimental.GrammarParserBaseListener;

/**
 *
 * @author Sam Harwell
 */
public class LabelAnalyzer extends GrammarParserBaseListener {

    private final Map<String, Token> labels = new HashMap<String, Token>();
    private final Map<String, Token> unlabeledElements = new HashMap<String, Token>();
    private final RuleContext<?> finalContext;

    private Token enclosingRuleName;
    private boolean caretReached;
    private boolean inAction;

    public LabelAnalyzer(RuleContext<?> finalContext) {
        this.finalContext = finalContext;
    }

    public final RuleContext<?> getFinalContext() {
        return finalContext;
    }

    public final Collection<Token> getLabels() {
        return labels.values();
    }

    public final Collection<Token> getUnlabeledElements() {
        return unlabeledElements.values();
    }

    public final Token getEnclosingRuleName() {
        return enclosingRuleName;
    }

    public final boolean isCaretReached() {
        return caretReached;
    }

    public final boolean isInAction() {
        return inAction;
    }

    @Override
    public void exitEveryRule(ParserRuleContext<? extends Token> ctx) {
        checkCaretReached(ctx);
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionExpression, version=0, dependents=Dependents.PARENTS)
    public void enterParserRuleSpec(ParserRuleSpecContext ctx) {
        if (ctx.name != null) {
            enclosingRuleName = ctx.name;
        }
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0, dependents=Dependents.PARENTS)
    public void enterLexerRule(LexerRuleContext ctx) {
        if (ctx.name != null) {
            enclosingRuleName = ctx.name;
        }
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_labeledAlt, version=1, dependents=Dependents.PARENTS)
    public void enterLabeledAlt(LabeledAltContext ctx) {
        if (isCaretReached()) {
            return;
        }

        labels.clear();
        unlabeledElements.clear();
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_labeledElement, version=2, dependents={Dependents.PARENTS, Dependents.DESCENDANTS})
    public void enterLabeledElement(LabeledElementContext ctx) {
        if (isCaretReached()) {
            return;
        }

        if (!labels.containsKey(ctx.start.getText())) {
            labels.put(ctx.start.getText(), ctx.start);
        }
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_terminal, version=1, dependents={Dependents.PARENTS, Dependents.DESCENDANTS})
    public void enterTerminal(TerminalContext ctx) {
        if (isCaretReached()) {
            return;
        }

        if (isLabeledContext(ctx)) {
            return;
        }

        if (ctx.start.getType() == GrammarParser.TOKEN_REF) {
            if (!labels.containsKey(ctx.start.getText())) {
                unlabeledElements.put(ctx.start.getText(), ctx.start);
            }
        }
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleref, version=1, dependents={Dependents.PARENTS, Dependents.DESCENDANTS})
    public void enterRuleref(RulerefContext ctx) {
        if (isCaretReached()) {
            return;
        }

        if (isLabeledContext(ctx)) {
            return;
        }

        if (ctx.start.getType() == GrammarParser.RULE_REF) {
            if (!labels.containsKey(ctx.start.getText())) {
                unlabeledElements.put(ctx.start.getText(), ctx.start);
            }
        }
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionBlock, version=0, dependents=Dependents.PARENTS)
    public void enterActionBlock(ActionBlockContext ctx) {
        if (isCaretReached()) {
            return;
        }

        inAction = true;
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionBlock, version=0, dependents=Dependents.PARENTS)
    public void exitActionBlock(ActionBlockContext ctx) {
        checkCaretReached(ctx);

        if (isCaretReached()) {
            return;
        }

        inAction = false;
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionBlock, version=1, dependents=Dependents.PARENTS)
    public void enterArgActionBlock(ArgActionBlockContext ctx) {
        if (isCaretReached()) {
            return;
        }

        inAction = true;
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionBlock, version=1, dependents=Dependents.PARENTS)
    public void exitArgActionBlock(ArgActionBlockContext ctx) {
        checkCaretReached(ctx);

        if (isCaretReached()) {
            return;
        }

        inAction = false;
    }

    private void checkCaretReached(RuleContext<?> ctx) {
        if (ctx == getFinalContext()) {
            caretReached = true;
        }
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_labeledElement, version=2, dependents=Dependents.DESCENDANTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_notSet, version=2, dependents=Dependents.DESCENDANTS),
    })
    private static boolean isLabeledContext(ParserRuleContext<Token> ctx) {
        for (RuleContext<?> current = ctx; current != null; current = current.parent) {
            if (current instanceof GrammarParser.LabeledElementContext) {
                return true;
            } else if (current instanceof GrammarParser.NotSetContext) {
                return true;
            }
        }

        return false;
    }
}
