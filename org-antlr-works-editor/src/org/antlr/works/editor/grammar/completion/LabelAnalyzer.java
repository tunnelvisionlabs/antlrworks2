/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.antlr.works.editor.grammar.completion;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.experimental.BlankGrammarParserListener;
import org.antlr.works.editor.grammar.experimental.GrammarParser;

/**
 *
 * @author Sam Harwell
 */
public class LabelAnalyzer extends BlankGrammarParserListener {

    private final Map<String, Token> labels = new HashMap<String, Token>();
    private final Map<String, Token> unlabeledElements = new HashMap<String, Token>();
    private final RuleContext finalContext;

    private Token enclosingRuleName;
    private boolean caretReached;
    private boolean inRewrite;
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

    public final boolean isInRewrite() {
        return inRewrite;
    }

    public final boolean isInAction() {
        return inAction;
    }

    @Override
    public void exitEveryRule(ParserRuleContext<Token> ctx) {
        checkCaretReached(ctx);
    }

    @Override
    public void enterRule(GrammarParser.ruleContext ctx) {
        if (ctx.name != null) {
            enclosingRuleName = ctx.name.start;
        }
    }

    @Override
    public void enterRule(GrammarParser.labeledAltContext ctx) {
        if (isCaretReached()) {
            return;
        }

        labels.clear();
        unlabeledElements.clear();
    }

    @Override
    public void enterRule(GrammarParser.labeledElementContext ctx) {
        if (isCaretReached()) {
            return;
        }

        if (isInRewrite()) {
            return;
        }

        if (!labels.containsKey(ctx.start.getText())) {
            labels.put(ctx.start.getText(), ctx.start);
        }
    }

    @Override
    public void enterRule(GrammarParser.terminalContext ctx) {
        if (isCaretReached()) {
            return;
        }

        if (isInRewrite()) {
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
    public void enterRule(GrammarParser.rulerefContext ctx) {
        if (isCaretReached()) {
            return;
        }

        if (isInRewrite()) {
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
    public void enterRule(GrammarParser.rewriteContext ctx) {
        if (isCaretReached()) {
            return;
        }

        inRewrite = true;
    }

    @Override
    public void exitRule(GrammarParser.rewriteContext ctx) {
        checkCaretReached(ctx);

        if (isCaretReached()) {
            return;
        }

        inRewrite = false;
    }

    @Override
    public void enterRule(GrammarParser.actionBlockContext ctx) {
        if (isCaretReached()) {
            return;
        }

        inAction = true;
    }

    @Override
    public void exitRule(GrammarParser.actionBlockContext ctx) {
        checkCaretReached(ctx);

        if (isCaretReached()) {
            return;
        }

        inAction = false;
    }

    @Override
    public void enterRule(GrammarParser.argActionBlockContext ctx) {
        if (isCaretReached()) {
            return;
        }

        inAction = true;
    }

    @Override
    public void exitRule(GrammarParser.argActionBlockContext ctx) {
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
