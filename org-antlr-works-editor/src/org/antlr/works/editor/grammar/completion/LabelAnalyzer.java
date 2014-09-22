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
import org.antlr.v4.runtime.misc.Tuple;
import org.antlr.v4.runtime.misc.Tuple2;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ActionBlockContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ArgActionBlockContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ArgActionParameterContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ArgActionParameterTypeContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LabeledAltContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LabeledElementContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.RulerefContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.TerminalContext;
import org.antlr.works.editor.grammar.experimental.generated.GrammarParserBaseListener;

/**
 *
 * @author Sam Harwell
 */
public class LabelAnalyzer extends GrammarParserBaseListener {

    private final Map<String, Token> labels = new HashMap<>();
    private final Map<String, Token> unlabeledElements = new HashMap<>();
    private final Map<String, Tuple2<Token, ArgActionParameterTypeContext>> arguments = new HashMap<>();
    private final Map<String, Tuple2<Token, ArgActionParameterTypeContext>> returnValues = new HashMap<>();
    private final Map<String, Tuple2<Token, ArgActionParameterTypeContext>> locals = new HashMap<>();
    private final RuleContext finalContext;

    private Token enclosingRuleName;
    private boolean caretReached;
    private boolean inAction;

    public LabelAnalyzer(RuleContext finalContext) {
        this.finalContext = finalContext;
    }

    public final RuleContext getFinalContext() {
        return finalContext;
    }

    public final Collection<Token> getLabels() {
        return labels.values();
    }

    public final Collection<Token> getUnlabeledElements() {
        return unlabeledElements.values();
    }

    public final Collection<Tuple2<Token, ArgActionParameterTypeContext>> getArguments() {
        return arguments.values();
    }

    public final Collection<Tuple2<Token, ArgActionParameterTypeContext>> getReturnValues() {
        return returnValues.values();
    }

    public final Collection<Tuple2<Token, ArgActionParameterTypeContext>> getLocals() {
        return locals.values();
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
    public void exitEveryRule(ParserRuleContext ctx) {
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
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_labeledElement, version=5, dependents={Dependents.PARENTS, Dependents.DESCENDANTS})
    public void enterLabeledElement(LabeledElementContext ctx) {
        if (isCaretReached()) {
            return;
        }

        if (!labels.containsKey(ctx.start.getText())) {
            labels.put(ctx.start.getText(), ctx.start);
        }
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_terminal, version=3, dependents={Dependents.PARENTS, Dependents.DESCENDANTS})
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
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleref, version=5, dependents={Dependents.PARENTS, Dependents.DESCENDANTS})
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
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionBlock, version=5, dependents=Dependents.PARENTS)
    public void enterActionBlock(ActionBlockContext ctx) {
        if (isCaretReached()) {
            return;
        }

        inAction = true;
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionBlock, version=5, dependents=Dependents.PARENTS)
    public void exitActionBlock(ActionBlockContext ctx) {
        checkCaretReached(ctx);

        if (isCaretReached()) {
            return;
        }

        inAction = false;
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionBlock, version=5, dependents=Dependents.PARENTS)
    public void enterArgActionBlock(ArgActionBlockContext ctx) {
        if (isCaretReached()) {
            return;
        }

        inAction = true;
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionBlock, version=5, dependents=Dependents.PARENTS)
    public void exitArgActionBlock(ArgActionBlockContext ctx) {
        checkCaretReached(ctx);

        if (isCaretReached()) {
            return;
        }

        inAction = false;
    }

    @Override
    @RuleDependencies({
        // general dependencies for the target of an arg action parameter
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameter, version=7, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameters, version=0, dependents=Dependents.PARENTS),
        // dependencies for specific constructs which allow arg action parameters
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleReturns, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_localsSpec, version=0, dependents=Dependents.SELF),
    })
    public void enterArgActionParameter(ArgActionParameterContext ctx) {
        checkCaretReached(ctx);
        if (isCaretReached()) {
            return;
        }

        TerminalNode name = ctx.ARG_ACTION_WORD();
        ParserRuleContext parent = ctx.getParent();
        if (parent == null) {
            return;
        }

        ParserRuleContext grandParent = parent.getParent();
        if (grandParent == null) {
            return;
        }

        if (name != null) {
            Tuple2<Token, ArgActionParameterTypeContext> parameter = Tuple.create(name.getSymbol(), ctx.argActionParameterType(0));
            switch (grandParent.getRuleIndex()) {
            case GrammarParser.RULE_parserRuleSpec:
                arguments.put(name.getText(), parameter);
                break;

            case GrammarParser.RULE_ruleReturns:
                returnValues.put(name.getText(), parameter);
                break;

            case GrammarParser.RULE_localsSpec:
                locals.put(name.getText(), parameter);
                break;

            default:
                break;
            }
        }
    }

    private void checkCaretReached(RuleContext ctx) {
        if (ctx == getFinalContext()) {
            caretReached = true;
        }
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_labeledElement, version=5, dependents=Dependents.DESCENDANTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_notSet, version=4, dependents=Dependents.DESCENDANTS),
    })
    private static boolean isLabeledContext(ParserRuleContext ctx) {
        for (RuleContext current = ctx; current != null; current = current.parent) {
            if (current instanceof GrammarParser.LabeledElementContext) {
                return true;
            } else if (current instanceof GrammarParser.NotSetContext) {
                return true;
            }
        }

        return false;
    }
}
