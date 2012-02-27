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
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ActionBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ArgActionBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LabeledAltContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LabeledElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RulerefContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.TerminalContext;
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
    public void enterParserRuleSpec(ParserRuleSpecContext ctx) {
        if (ctx.name != null) {
            enclosingRuleName = ctx.name;
        }
    }

    @Override
    public void enterLexerRule(LexerRuleContext ctx) {
        if (ctx.name != null) {
            enclosingRuleName = ctx.name;
        }
    }

    @Override
    public void enterLabeledAlt(LabeledAltContext ctx) {
        if (isCaretReached()) {
            return;
        }

        labels.clear();
        unlabeledElements.clear();
    }

    @Override
    public void enterLabeledElement(LabeledElementContext ctx) {
        if (isCaretReached()) {
            return;
        }

        if (!labels.containsKey(ctx.start.getText())) {
            labels.put(ctx.start.getText(), ctx.start);
        }
    }

    @Override
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
    public void enterActionBlock(ActionBlockContext ctx) {
        if (isCaretReached()) {
            return;
        }

        inAction = true;
    }

    @Override
    public void exitActionBlock(ActionBlockContext ctx) {
        checkCaretReached(ctx);

        if (isCaretReached()) {
            return;
        }

        inAction = false;
    }

    @Override
    public void enterArgActionBlock(ArgActionBlockContext ctx) {
        if (isCaretReached()) {
            return;
        }

        inAction = true;
    }

    @Override
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
