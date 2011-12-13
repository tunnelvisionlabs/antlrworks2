/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.antlr.works.editor.grammar.experimental;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.tagging.TaggedPositionRegion;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentSnapshotLine;
import org.antlr.netbeans.editor.text.NormalizedSnapshotPositionRegionCollection;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.parsing.spi.BaseParserData;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.experimental.GrammarParserAnchorListener.Anchor;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public class ReferenceAnchorsParserTask implements ParserTask {

    private final VersionedDocument document;

    private ReferenceAnchorsParserTask(VersionedDocument document) {
        this.document = document;
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, JTextComponent component, DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        boolean legacyMode = GrammarEditorKit.isLegacyMode(document.getDocument());
        if (legacyMode) {
            ParserData<List<Anchor>> emptyResult = new BaseParserData<List<Anchor>>(GrammarParserDataDefinitions.REFERENCE_ANCHOR_POINTS, snapshot, null);
            results.addResult(emptyResult);
            return;
        }

        Future<ParserData<Tagger<TokenTag>>> futureTokensData = taskManager.getData(snapshot, GrammarParserDataDefinitions.LEXER_TOKENS);
        Tagger<TokenTag> tagger = futureTokensData.get().getData();
        TaggerTokenSource tokenSource = new TaggerTokenSource(tagger, snapshot);
//        DocumentSnapshotCharStream input = new DocumentSnapshotCharStream(snapshot);
//        input.setSourceName((String)document.getDocument().getProperty(Document.TitleProperty));
//        GrammarLexer lexer = new GrammarLexer(input);
        InterruptableTokenStream tokenStream = new InterruptableTokenStream(tokenSource);
        GrammarParser parser = new GrammarParser(tokenStream);
        parser.setBuildParseTree(true);
        GrammarParser.grammarSpecContext parseResult = parser.grammarSpec();

        GrammarParserAnchorListener listener = new GrammarParserAnchorListener(snapshot);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, parseResult);
        ParserData<List<Anchor>> result = new BaseParserData<List<Anchor>>(GrammarParserDataDefinitions.REFERENCE_ANCHOR_POINTS, snapshot, listener.getAnchors());
        results.addResult(result);
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

    private static final class Definition implements ParserTaskDefinition {
        public static final Definition INSTANCE = new Definition();

        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>emptyList();
        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GrammarParserDataDefinitions.REFERENCE_ANCHOR_POINTS);

        @Override
        public Collection<ParserDataDefinition<?>> getInputs() {
            return INPUTS;
        }

        @Override
        public Collection<ParserDataDefinition<?>> getOutputs() {
            return OUTPUTS;
        }

        @Override
        public Class<? extends ParserTaskScheduler> getScheduler() {
            return ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER;
        }
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider implements ParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTask(VersionedDocument document) {
            return new ReferenceAnchorsParserTask(document);
        }

    }
}
