/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.antlr.netbeans.editor.completion.AbstractAnchor;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.GrammarTypeContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.RuleSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.GrammarParserBaseListener;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class GrammarParserAnchorListener extends GrammarParserBaseListener {

    private final Deque<Integer> anchorPositions = new ArrayDeque<>();
    private final List<Anchor> anchors = new ArrayList<>();
    private final DocumentSnapshot snapshot;
    private final AtomicBoolean cancel;

    public GrammarParserAnchorListener(DocumentSnapshot snapshot) {
        Parameters.notNull("snapshot", snapshot);
        this.snapshot = snapshot;
        this.cancel = null;
    }

    public GrammarParserAnchorListener(DocumentSnapshot snapshot, AtomicBoolean cancel) {
        Parameters.notNull("snapshot", snapshot);
        this.snapshot = snapshot;
        this.cancel = cancel;
    }

    public List<Anchor> getAnchors() {
        return anchors;
    }

    private void checkCancellation() {
        boolean cancelled;
        if (cancel != null) {
            cancelled = cancel.get();
        } else {
            cancelled = Thread.interrupted();
        }

        if (cancelled) {
            throw new CancellationException();
        }
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        checkCancellation();
        super.enterEveryRule(ctx);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        checkCancellation();
        super.exitEveryRule(ctx);
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarType, version=0, dependents=Dependents.PARENTS)
    public void enterGrammarType(GrammarTypeContext ctx) {
        enterAnchor(ctx);
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarType, version=0, dependents=Dependents.PARENTS)
    public void exitGrammarType(GrammarTypeContext ctx) {
        exitAnchor(ctx, GrammarParser.RULE_grammarType);
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=3, dependents=Dependents.PARENTS)
    public void enterRuleSpec(RuleSpecContext ctx) {
        enterAnchor(ctx);
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=3, dependents=Dependents.PARENTS)
    public void exitRuleSpec(RuleSpecContext ctx) {
        exitAnchor(ctx, GrammarParser.RULE_ruleSpec);
    }

    private void enterAnchor(ParserRuleContext ctx) {
        anchorPositions.push(ctx.getStart().getStartIndex());
    }

    private void exitAnchor(ParserRuleContext ctx, int anchorId) {
        int start = anchorPositions.pop();
        Interval sourceInterval = ParseTrees.getSourceInterval(ctx);
        int stop = sourceInterval.b + 1;
        TrackingPositionRegion.Bias trackingMode = ctx.getStop() != null ? TrackingPositionRegion.Bias.Exclusive : TrackingPositionRegion.Bias.Forward;
        if (stop >= start) {
            anchors.add(createAnchor(ctx, start, stop, trackingMode, anchorId));
        }
    }

    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarType, version=0, dependents=Dependents.PARENTS)
    private Anchor createAnchor(ParserRuleContext ctx, int start, int stop, TrackingPositionRegion.Bias trackingMode, int rule) {
        TrackingPositionRegion trackingSpan = snapshot.createTrackingRegion(start, stop - start, trackingMode);
        if (rule == GrammarParser.RULE_grammarType) {
            return new GrammarTypeAnchor((GrammarParser.GrammarTypeContext)ctx, trackingSpan);
        } else {
            return new GrammarAnchor(trackingSpan, rule);
        }
    }

    public static class GrammarAnchor extends AbstractAnchor {
        private GrammarAnchor(@NonNull TrackingPositionRegion span, int rule) {
            super(span, rule);
        }

        @Override
        protected List<String> getRuleNames() {
            return Arrays.asList(GrammarParser.ruleNames);
        }
    }

    public static class GrammarTypeAnchor extends GrammarAnchor {

        private final int grammarType;

        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarType, version=0, dependents=Dependents.SELF)
        private GrammarTypeAnchor(GrammarTypeContext ctx, TrackingPositionRegion span) {
            super(span, GrammarParser.RULE_grammarType);
            if (ctx.LEXER() != null) {
                grammarType = GrammarParser.LEXER;
            } else if (ctx.PARSER() != null) {
                grammarType = GrammarParser.PARSER;
            } else {
                grammarType = GrammarParser.COMBINED;
            }
        }

        public int getGrammarType() {
            return grammarType;
        }
    }

}
