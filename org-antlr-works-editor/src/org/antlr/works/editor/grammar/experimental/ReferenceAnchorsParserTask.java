/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.tagging.Tagger;
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
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.antlr4.classification.TaggerTokenSource;
import org.antlr.works.editor.antlr4.parsing.DescriptiveErrorListener;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.codemodel.impl.CodeModelCacheImpl;
import org.antlr.works.editor.grammar.codemodel.impl.FileModelImpl;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.GrammarSpecContext;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public final class ReferenceAnchorsParserTask implements ParserTask {

    private final VersionedDocument document;

    private final Object lock = new Object();

    private ReferenceAnchorsParserTask(VersionedDocument document) {
        this.document = document;
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0, dependents=Dependents.SELF)
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        boolean legacyMode = GrammarEditorKit.isLegacyMode(snapshot);
        if (legacyMode) {
            ParserData<List<Anchor>> emptyResult = new BaseParserData<>(context, GrammarParserDataDefinitions.REFERENCE_ANCHOR_POINTS, snapshot, null);
            results.addResult(emptyResult);
            return;
        }

        synchronized (lock) {
            ParserData<GrammarSpecContext> parseTreeResult = taskManager.getData(snapshot, GrammarParserDataDefinitions.REFERENCE_PARSE_TREE, EnumSet.of(ParserDataOptions.NO_UPDATE)).get();
            ParserData<List<Anchor>> anchorPointsResult = taskManager.getData(snapshot, GrammarParserDataDefinitions.REFERENCE_ANCHOR_POINTS, EnumSet.of(ParserDataOptions.NO_UPDATE)).get();
            ParserData<FileModel> fileModelResult = taskManager.getData(snapshot, GrammarParserDataDefinitions.FILE_MODEL, EnumSet.of(ParserDataOptions.NO_UPDATE)).get();
            if (parseTreeResult == null || anchorPointsResult == null || fileModelResult == null) {
                Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = taskManager.getData(snapshot, GrammarParserDataDefinitions.LEXER_TOKENS);
                Tagger<TokenTag<Token>> tagger = futureTokensData.get().getData();
                TaggerTokenSource tokenSource = new TaggerTokenSource(tagger, snapshot);
        //        DocumentSnapshotCharStream input = new DocumentSnapshotCharStream(snapshot);
        //        input.setSourceName((String)document.getDocument().getProperty(Document.TitleProperty));
        //        GrammarLexer lexer = new GrammarLexer(input);
                InterruptableTokenStream tokenStream = new InterruptableTokenStream(tokenSource);
                GrammarSpecContext parseResult;
                GrammarParser parser = GrammarParserFactory.DEFAULT.getParser(tokenStream);
                try {
                    parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
                    parser.removeErrorListeners();
                    parser.setBuildParseTree(true);
                    parser.setErrorHandler(new BailErrorStrategy());
                    parseResult = parser.grammarSpec();
                } catch (ParseCancellationException ex) {
                    if (ex.getCause() instanceof RecognitionException) {
                        // retry with default error handler
                        tokenStream.reset();
                        parser.getInterpreter().setPredictionMode(PredictionMode.LL);
                        parser.addErrorListener(DescriptiveErrorListener.INSTANCE);
                        parser.setInputStream(tokenStream);
                        parser.setErrorHandler(new DefaultErrorStrategy());
                        parseResult = parser.grammarSpec();
                    } else {
                        throw ex;
                    }
                }

                parseTreeResult = new BaseParserData<>(context, GrammarParserDataDefinitions.REFERENCE_PARSE_TREE, snapshot, parseResult);

                if (anchorPointsResult == null && snapshot.getVersionedDocument().getDocument() != null) {
                    GrammarParserAnchorListener listener = new GrammarParserAnchorListener(snapshot);
                    ParseTreeWalker.DEFAULT.walk(listener, parseResult);
                    anchorPointsResult = new BaseParserData<>(context, GrammarParserDataDefinitions.REFERENCE_ANCHOR_POINTS, snapshot, listener.getAnchors());
                }

                if (fileModelResult == null) {
                    FileModelImpl fileModel = null;
                    if (snapshot.getVersionedDocument().getFileObject() != null) {
                        CodeModelBuilderListener codeModelBuilderListener = new CodeModelBuilderListener(snapshot, tokenStream);
                        ParseTreeWalker.DEFAULT.walk(codeModelBuilderListener, parseResult);
                        fileModel = codeModelBuilderListener.getFileModel();
                        if (fileModel != null) {
                            updateCodeModelCache(fileModel);
                        }
                    }

                    fileModelResult = new BaseParserData<>(context, GrammarParserDataDefinitions.FILE_MODEL, snapshot, fileModel);
                }
            }

            results.addResult(parseTreeResult);
            results.addResult(fileModelResult);
            if (anchorPointsResult != null) {
                results.addResult(anchorPointsResult);
            }
        }
    }

    private void updateCodeModelCache(FileModelImpl fileModel) {
        CodeModelCacheImpl codeModelCache = CodeModelCacheImpl.getInstance();
        codeModelCache.updateFile(fileModel);
    }

    private static class InterruptableTokenStream extends CommonTokenStream {
        public InterruptableTokenStream(TokenSource tokenSource) {
            super(tokenSource);
        }

        @Override
        public void consume() {
            if (Thread.interrupted()) {
                throw new CancellationException();
            }

            super.consume();
        }
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>emptyList();
        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Arrays.<ParserDataDefinition<?>>asList(
                GrammarParserDataDefinitions.REFERENCE_ANCHOR_POINTS,
                GrammarParserDataDefinitions.REFERENCE_PARSE_TREE,
                GrammarParserDataDefinitions.FILE_MODEL);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Grammar Reference Anchors", INPUTS, OUTPUTS, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
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
            return new ReferenceAnchorsParserTask(document);
        }

    }
}
