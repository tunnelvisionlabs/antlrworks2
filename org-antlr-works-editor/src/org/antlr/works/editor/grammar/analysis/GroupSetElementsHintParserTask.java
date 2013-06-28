/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.SingletonParserTaskProvider;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser;
import org.antlr.works.editor.grammar.experimental.generated.GrammarParserBaseListener;
import org.antlr.works.editor.grammar.parser.CompiledModel;
import org.antlr.works.editor.grammar.semantics.GrammarAnnotatedParseTree;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.ErrorDescriptionFactory;
import org.netbeans.spi.editor.hints.HintsController;
import org.netbeans.spi.editor.hints.Severity;
import org.openide.util.Exceptions;

/**
 * This hint finds sections of code like {@code (a | B | C)}, which can be rewritten
 * as {@code (a | (B | C))}. When the simple terminals are moved to a distinct group,
 * ANTLR is able to collapse the alternatives into a single match operation for the set.
 *
 * @author Sam Harwell
 */
public final class GroupSetElementsHintParserTask implements ParserTask {
    private static final String HINT_LAYER = "antlr4/group-terminals";

    private GroupSetElementsHintParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results) throws InterruptedException, ExecutionException {

        Document document = context.getDocument().getDocument();
        if (document == null) {
            return;
        }

        if (GrammarEditorKit.isLegacyMode(document)) {
            HintsController.setErrors(document, HINT_LAYER, Collections.<ErrorDescription>emptyList());
            return;
        }

        CompiledModel model = getCachedData(taskManager, context, snapshot, GrammarParserDataDefinitions.COMPILED_MODEL);
        GrammarAnnotatedParseTree grammarAnnotatedParseTree = getCachedData(taskManager, context, snapshot, GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE);
        if (model == null || grammarAnnotatedParseTree == null) {
            return;
        }

        Listener listener = new Listener();
        ParseTreeWalker.DEFAULT.walk(listener, grammarAnnotatedParseTree.getParseTree());

        List<ErrorDescription> hints = new ArrayList<>();
        for (Interval interval : listener.getRewriteRanges()) {
            try {
                hints.add(ErrorDescriptionFactory.createErrorDescription(Severity.HINT, "Group terminals into set", document, document.createPosition(interval.a), document.createPosition(interval.b + 1)));
            } catch (BadLocationException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        HintsController.setErrors(document, HINT_LAYER, hints);
    }

    private static <T> T getCachedData(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, ParserDataDefinition<T> definition) throws InterruptedException, ExecutionException {
        Future<ParserData<T>> futureData = taskManager.getData(snapshot, context.getComponent(), definition, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS));
        ParserData<T> parserData = futureData != null ? futureData.get() : null;
        T data = parserData != null ? parserData.getData() : null;
        return data;
    }

    private static final class Listener extends GrammarParserBaseListener {
        private final IntervalSet _ignoreRanges = new IntervalSet();
        private final IntervalSet _rewriteRanges = new IntervalSet();

        public List<Interval> getRewriteRanges() {
            return _rewriteRanges.getIntervals();
        }

        // actions and predicates cannot be in a set
        @Override
        public void exitActionBlock(AbstractGrammarParser.ActionBlockContext ctx) {
            ignore(ctx);
        }

        // */?/+ cannot be in a set
        @Override
        public void exitEbnfSuffix(AbstractGrammarParser.EbnfSuffixContext ctx) {
            ignore(ctx);
        }

        // cannot call other rules from a set (only terminals)
        @Override
        public void exitRuleref(AbstractGrammarParser.RulerefContext ctx) {
            ignore(ctx);
        }

        // cannot specify element options for terminals in a set
        @Override
        public void exitElementOptions(AbstractGrammarParser.ElementOptionsContext ctx) {
            ignore(ctx);
        }

        @Override
        public void exitElements(AbstractGrammarParser.ElementsContext ctx) {
            if (ctx.element().size() != 1) {
                ignore(ctx);
            }
        }

        @Override
        public void exitLabeledAlt(AbstractGrammarParser.LabeledAltContext ctx) {
            if (ctx.POUND() != null) {
                ignore(ctx);
            }
        }

        @Override
        public void exitLabeledElement(AbstractGrammarParser.LabeledElementContext ctx) {
            ignore(ctx);
        }

        @Override
        public void exitAltList(AbstractGrammarParser.AltListContext ctx) {
            processAlternatives(ctx.alternative());
        }

        @Override
        public void exitRuleAltList(AbstractGrammarParser.RuleAltListContext ctx) {
            List<? extends AbstractGrammarParser.LabeledAltContext> labeledAlts = ctx.labeledAlt();
            if (labeledAlts.isEmpty()) {
                return;
            }

            List<AbstractGrammarParser.AlternativeContext> alternatives = new ArrayList<>(labeledAlts.size());
            for (AbstractGrammarParser.LabeledAltContext labeledAltContext : labeledAlts) {
                AbstractGrammarParser.AlternativeContext alternative = labeledAltContext.alternative();
                if (alternative == null) {
                    return;
                }

                alternatives.add(labeledAltContext.alternative());
            }

            processAlternatives(alternatives);
        }

        private void processAlternatives(List<? extends AbstractGrammarParser.AlternativeContext> alternatives) {
            if (alternatives.size() <= 1) {
                return;
            }

            IntervalSet setlikeAlts = new IntervalSet();
            for (int i = 0; i < alternatives.size(); i++) {
                if (!isIgnored(alternatives.get(i))) {
                    setlikeAlts.add(i);
                }
            }

            if (setlikeAlts.size() <= 1 || setlikeAlts.size() == alternatives.size()) {
                return;
            }

            for (Interval interval : setlikeAlts.getIntervals()) {
                if (interval.length() > 1) {
                    TerminalNode firstNode = ParseTrees.getStartNode(alternatives.get(interval.a));
                    TerminalNode lastNode = ParseTrees.getStopNode(alternatives.get(interval.b));
                    if (firstNode == null || lastNode == null) {
                        continue;
                    }

                    int startIndex = firstNode.getSymbol().getStartIndex();
                    int stopIndex = lastNode.getSymbol().getStopIndex();
                    _rewriteRanges.add(startIndex, stopIndex);
                }
            }
        }

        private boolean isIgnored(ParseTree ctx) {
            Interval interval = ctx.getSourceInterval();
            for (Interval ignored : _ignoreRanges.getIntervals()) {
                if (!ignored.disjoint(interval)) {
                    return true;
                }
            }

            return false;
        }

        private void ignore(ParseTree ctx) {
            Interval interval = ctx.getSourceInterval();
            _ignoreRanges.add(interval.a, interval.b);
        }

    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Arrays.<ParserDataDefinition<?>>asList(GrammarParserDataDefinitions.COMPILED_MODEL, GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE);
        private static final Collection<ParserDataDefinition<?>> OUTPUTS = Collections.emptyList();

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Grammar Group Set Elements Hint", INPUTS, OUTPUTS, ParserTaskScheduler.INPUT_SENSITIVE_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends SingletonParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        protected ParserTask createTaskImpl() {
            return new GroupSetElementsHintParserTask();
        }

    }

}
