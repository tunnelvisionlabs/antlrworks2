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
import org.antlr.works.editor.grammar.experimental.GrammarParserBaseListener;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParser.actionBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.argActionBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.labeledAltContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.labeledElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.parserRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.rulerefContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.terminalContext;

/**
 *
 * @author Sam Harwell
 */
public class LabelAnalyzer extends GrammarParserBaseListener {

    private final Map<String, Token> labels = new HashMap<String, Token>();
    private final Map<String, Token> unlabeledElements = new HashMap<String, Token>();
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
    public void exitEveryRule(ParserRuleContext<Token> ctx) {
        checkCaretReached(ctx);
    }

    @Override
    public void parserRuleEnter(parserRuleContext ctx) {
        if (ctx.name != null) {
            enclosingRuleName = ctx.name;
        }
    }

    @Override
    public void lexerRuleEnter(lexerRuleContext ctx) {
        if (ctx.name != null) {
            enclosingRuleName = ctx.name;
        }
    }

    @Override
    public void labeledAltEnter(labeledAltContext ctx) {
        if (isCaretReached()) {
            return;
        }

        labels.clear();
        unlabeledElements.clear();
    }

    @Override
    public void labeledElementEnter(labeledElementContext ctx) {
        if (isCaretReached()) {
            return;
        }

        if (!labels.containsKey(ctx.start.getText())) {
            labels.put(ctx.start.getText(), ctx.start);
        }
    }

    @Override
    public void terminalEnter(terminalContext ctx) {
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
    public void rulerefEnter(rulerefContext ctx) {
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
    public void actionBlockEnter(actionBlockContext ctx) {
        if (isCaretReached()) {
            return;
        }

        inAction = true;
    }

    @Override
    public void actionBlockExit(actionBlockContext ctx) {
        checkCaretReached(ctx);

        if (isCaretReached()) {
            return;
        }

        inAction = false;
    }

    @Override
    public void argActionBlockEnter(argActionBlockContext ctx) {
        if (isCaretReached()) {
            return;
        }

        inAction = true;
    }

    @Override
    public void argActionBlockExit(argActionBlockContext ctx) {
        checkCaretReached(ctx);

        if (isCaretReached()) {
            return;
        }

        inAction = false;
    }

    private void checkCaretReached(RuleContext ctx) {
        if (ctx == getFinalContext()) {
            caretReached = true;
        }
    }

    private static boolean isLabeledContext(ParserRuleContext<Token> ctx) {
        for (RuleContext current = ctx; current != null; current = current.parent) {
            if (current instanceof GrammarParser.labeledElementContext) {
                return true;
            } else if (current instanceof GrammarParser.notSetContext) {
                return true;
            }
        }

        return false;
    }
}
