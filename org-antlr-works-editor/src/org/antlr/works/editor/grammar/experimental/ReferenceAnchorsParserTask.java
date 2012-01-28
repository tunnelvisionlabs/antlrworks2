/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
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
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.antlr4.classification.TaggerTokenSource;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.codemodel.FileModel;
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

        Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = taskManager.getData(snapshot, GrammarParserDataDefinitions.LEXER_TOKENS);
        Tagger<TokenTag<Token>> tagger = futureTokensData.get().getData();
        TaggerTokenSource tokenSource = new TaggerTokenSource(tagger, snapshot);
//        DocumentSnapshotCharStream input = new DocumentSnapshotCharStream(snapshot);
//        input.setSourceName((String)document.getDocument().getProperty(Document.TitleProperty));
//        GrammarLexer lexer = new GrammarLexer(input);
        InterruptableTokenStream tokenStream = new InterruptableTokenStream(tokenSource);
        ParserRuleContext<Token> parseResult;
        GrammarParser parser = GrammarParserCache.DEFAULT.getParser(tokenStream);
        try {
            parser.setBuildParseTree(true);
            parser.setErrorHandler(new BailErrorStrategy());
            parseResult = parser.grammarSpec();
        } catch (RuntimeException ex) {
            if (ex.getClass() == RuntimeException.class && ex.getCause() instanceof RecognitionException) {
                // retry with default error handler
                tokenStream.reset();
                parser.setTokenStream(tokenStream);
                parser.setErrorHandler(new DefaultErrorStrategy());
                parseResult = parser.grammarSpec();
            } else {
                throw ex;
            }
        } finally {
            GrammarParserCache.DEFAULT.putParser(parser);
        }

        ParserData<ParserRuleContext<Token>> parseTreeResult = new BaseParserData<ParserRuleContext<Token>>(GrammarParserDataDefinitions.REFERENCE_PARSE_TREE, snapshot, parseResult);
        results.addResult(parseTreeResult);

        if (snapshot.getVersionedDocument().getDocument() != null) {
            GrammarParserAnchorListener listener = new GrammarParserAnchorListener(snapshot);
            ParseTreeWalker.DEFAULT.walk(listener, parseResult);
            ParserData<List<Anchor>> result = new BaseParserData<List<Anchor>>(GrammarParserDataDefinitions.REFERENCE_ANCHOR_POINTS, snapshot, listener.getAnchors());
            results.addResult(result);
        }

        CodeModelBuilderListener codeModelBuilderListener = new CodeModelBuilderListener(snapshot, tokenStream);
        ParseTreeWalker.DEFAULT.walk(codeModelBuilderListener, parseResult);
        ParserData<FileModel> fileModelResult = new BaseParserData<FileModel>(GrammarParserDataDefinitions.FILE_MODEL, snapshot, codeModelBuilderListener.getFileModel());
        results.addResult(fileModelResult);
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
