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
package org.antlr.works.editor.grammar.experimental;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.antlr.netbeans.editor.text.SpanTrackingMode;
import org.antlr.netbeans.editor.text.TextSnapshot;
import org.antlr.netbeans.editor.text.TrackingSpan;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.grammarTypeContext;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public class GrammarParserAnchorListener extends BlankGrammarParserListener {

    private final Stack<Integer> anchorPositions = new Stack<Integer>();
    private final List<Anchor> anchors = new ArrayList<Anchor>();
    private final TextSnapshot snapshot;

    public GrammarParserAnchorListener(TextSnapshot snapshot) {
        Parameters.notNull("snapshot", snapshot);
        this.snapshot = snapshot;
    }

    public List<Anchor> getAnchors() {
        return anchors;
    }

    @Override
    public void enterRule(GrammarParser.grammarTypeContext ctx) {
        enterAnchor(ctx);
    }

    @Override
    public void exitRule(GrammarParser.grammarTypeContext ctx) {
        exitAnchor(ctx, GrammarParser.RULE_grammarType);
    }

    @Override
    public void enterRule(GrammarParser.ruleContext ctx) {
        enterAnchor(ctx);
    }

    @Override
    public void exitRule(GrammarParser.ruleContext ctx) {
        exitAnchor(ctx, GrammarParser.RULE_rule);
    }

    @Override
    public void enterRule(GrammarParser.tokenSpecContext ctx) {
        enterAnchor(ctx);
    }

    @Override
    public void exitRule(GrammarParser.tokenSpecContext ctx) {
        exitAnchor(ctx, GrammarParser.RULE_tokenSpec);
    }

    private void enterAnchor(ParserRuleContext ctx) {
        anchorPositions.push(ctx.getStart().getStartIndex());
    }

    private void exitAnchor(ParserRuleContext ctx, int anchorId) {
        int start = anchorPositions.pop();
        int stop = ctx.getStop() != null ? ctx.getStop().getStopIndex() + 1 : snapshot.length();
        SpanTrackingMode trackingMode = ctx.getStop() != null ? SpanTrackingMode.EdgeExclusive : SpanTrackingMode.EdgePositive;
        anchors.add(createAnchor(ctx, start, stop, trackingMode, anchorId));
    }

    private Anchor createAnchor(ParserRuleContext ctx, int start, int stop, SpanTrackingMode trackingMode, int rule) {
        TrackingSpan trackingSpan = snapshot.createTrackingSpan(start, stop - start, trackingMode);
        if (rule == GrammarParser.RULE_grammarType) {
            return new GrammarTypeAnchor((GrammarParser.grammarTypeContext)ctx, trackingSpan);
        } else {
            return new AnchorImpl(trackingSpan, rule);
        }
    }

    public interface Anchor {

        public TrackingSpan getSpan();

        public int getRule();

    }

    public static class AnchorImpl implements Anchor {
        private final TrackingSpan span;
        private final int rule;

        private AnchorImpl(TrackingSpan span, int rule) {
            Parameters.notNull("span", span);
            this.span = span;
            this.rule = rule;
        }

        @Override
        public TrackingSpan getSpan() {
            return span;
        }

        @Override
        public int getRule() {
            return rule;
        }
    }

    public static class GrammarTypeAnchor extends AnchorImpl {

        private final int grammarType;

        private GrammarTypeAnchor(grammarTypeContext ctx, TrackingSpan span) {
            super(span, GrammarParser.RULE_grammarType);
            if (ctx.t == null) {
                grammarType = GrammarParser.COMBINED;
            } else {
                grammarType = ctx.t.getType();
            }
        }

        public int getGrammarType() {
            return grammarType;
        }
    }

}
