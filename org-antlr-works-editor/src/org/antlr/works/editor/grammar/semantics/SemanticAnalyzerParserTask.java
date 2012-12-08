/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.semantics;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.parsing.spi.BaseParserData;
import org.antlr.netbeans.parsing.spi.DocumentParserTaskProvider;
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
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author Sam Harwell
 */
public final class SemanticAnalyzerParserTask implements ParserTask {

    private final Object lock = new Object();

    private SemanticAnalyzerParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        if (requestedData.contains(GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE)) {
            synchronized (lock) {
                ParserData<GrammarAnnotatedParseTree> parseTreeResult = getTaskManager().getData(snapshot, GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS)).get();
                if (parseTreeResult != null) {
                    results.addResult(parseTreeResult);
                    return;
                }

                ParserRuleContext<Token> referenceParseTree = null;
                try {
                    Future<ParserData<ParserRuleContext<Token>>> futureRefParseTreeData = getTaskManager().getData(snapshot, GrammarParserDataDefinitions.REFERENCE_PARSE_TREE, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
                    ParserData<ParserRuleContext<Token>> refParseTreeData = futureRefParseTreeData != null ? futureRefParseTreeData.get() : null;
                    referenceParseTree = refParseTreeData != null ? refParseTreeData.getData() : null;
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                }

                if (referenceParseTree != null) {
                    GrammarAnnotatedParseTree annotatedParseTree = new GrammarAnnotatedParseTree(referenceParseTree);
                    SemanticAnalyzerListener listener = new SemanticAnalyzerListener(annotatedParseTree.getTreeDecorator(), annotatedParseTree.getTokenDecorator());
                    ParseTreeWalker.DEFAULT.walk(listener, referenceParseTree);
                    parseTreeResult = new BaseParserData<GrammarAnnotatedParseTree>(context, GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE, snapshot, annotatedParseTree);
                } else {
                    parseTreeResult = new BaseParserData<GrammarAnnotatedParseTree>(context, GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE, snapshot, null);
                }

                results.addResult(parseTreeResult);
            }
        }
    }

    private ParserTaskManager getTaskManager() {
        return Lookup.getDefault().lookup(ParserTaskManager.class);
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GrammarParserDataDefinitions.COMPILED_MODEL);
        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Grammar Semantic Analyzer", INPUTS, OUTPUTS, ParserTaskScheduler.INPUT_SENSITIVE_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends DocumentParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTaskImpl(VersionedDocument document) {
            return new SemanticAnalyzerParserTask();
        }

    }

}
