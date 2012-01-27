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

import java.util.Collection;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutionException;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.classification.TokenTag;
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
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public class LexerTokensParserTask implements ParserTask {

    private static final String DOCUMENT_CACHE_KEY = LexerTokensParserTask.class.getName() + "-snapshot-data";

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void parse(ParserTaskManager taskManager, JTextComponent component, DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        if (requestedData.contains(GrammarParserDataDefinitions.LEXER_TOKENS)) {
            WeakHashMap<DocumentSnapshot, ParserData<Tagger<TokenTag<Token>>>> documentCache =
                (WeakHashMap<DocumentSnapshot, ParserData<Tagger<TokenTag<Token>>>>)snapshot.getVersionedDocument().getProperty(DOCUMENT_CACHE_KEY);

            if (documentCache == null) {
                documentCache = new WeakHashMap<DocumentSnapshot, ParserData<Tagger<TokenTag<Token>>>>();
                snapshot.getVersionedDocument().putProperty(DOCUMENT_CACHE_KEY, documentCache);
            }
            
            ParserData<Tagger<TokenTag<Token>>> result;
            synchronized (documentCache) {
                result = documentCache.get(snapshot);
            }

            if (result == null) {
                int requestedVersion = snapshot.getVersion().getVersionNumber();
                ParserData<Tagger<TokenTag<Token>>> previousResult = null;
                int previousVersion = -1;
                ParserData<Tagger<TokenTag<Token>>>[] values;
                synchronized (documentCache) {
                    values = documentCache.values().toArray(new ParserData[0]);
                }

                for (ParserData<Tagger<TokenTag<Token>>> data : values) {
                    int dataVersion = data.getSnapshot().getVersion().getVersionNumber();
                    if (dataVersion > previousVersion && dataVersion < requestedVersion) {
                        previousResult = data;
                        previousVersion = dataVersion;
                    }
                }
                
                if (previousResult != null) {
                    GrammarTokensTaskTaggerSnapshot previousTagger = (GrammarTokensTaskTaggerSnapshot)previousResult.getData();
                    result = new BaseParserData<Tagger<TokenTag<Token>>>(GrammarParserDataDefinitions.LEXER_TOKENS, snapshot, previousTagger.translateTo(snapshot));
                } else {
                    GrammarTokensTaskTaggerSnapshot tagger = new GrammarTokensTaskTaggerSnapshot(snapshot);
                    tagger.initialize();
                    result = new BaseParserData<Tagger<TokenTag<Token>>>(GrammarParserDataDefinitions.LEXER_TOKENS, snapshot, tagger);
                }

                synchronized (documentCache) {
                    ParserData<Tagger<TokenTag<Token>>> updatedResult = documentCache.get(snapshot);
                    if (updatedResult != null) {
                        result = updatedResult;
                    } else {
                        documentCache.put(snapshot, result);
                    }
                }
            }
            
            results.addResult(result);
        }
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>emptyList();
        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GrammarParserDataDefinitions.LEXER_TOKENS);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Grammar Lexer Tokens", INPUTS, OUTPUTS, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
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
            return new LexerTokensParserTask();
        }

    }
}
