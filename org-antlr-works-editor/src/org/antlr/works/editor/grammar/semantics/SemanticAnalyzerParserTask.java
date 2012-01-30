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
public class SemanticAnalyzerParserTask implements ParserTask {

    private final Object lock = new Object();

    private SemanticAnalyzerParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        if (requestedData.contains(GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE)) {
            synchronized (lock) {
                ParserData<GrammarAnnotatedParseTree> parseTreeResult = getTaskManager().getData(snapshot, GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE, EnumSet.of(ParserDataOptions.NO_UPDATE)).get();
                if (parseTreeResult != null) {
                    results.addResult(parseTreeResult);
                    return;
                }

                ParserRuleContext<Token> referenceParseTree = null;
                try {
                    Future<ParserData<ParserRuleContext<Token>>> futureRefParseTreeData = getTaskManager().getData(snapshot, GrammarParserDataDefinitions.REFERENCE_PARSE_TREE);
                    referenceParseTree = futureRefParseTreeData.get().getData();
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                }

                GrammarAnnotatedParseTree annotatedParseTree = new GrammarAnnotatedParseTree(referenceParseTree);
                SemanticAnalyzerListener listener = new SemanticAnalyzerListener(annotatedParseTree.getTreeDecorator(), annotatedParseTree.getTokenDecorator());
                ParseTreeWalker.DEFAULT.walk(listener, referenceParseTree);
                parseTreeResult = new BaseParserData<GrammarAnnotatedParseTree>(context, GrammarParserDataDefinitions.ANNOTATED_PARSE_TREE, snapshot, annotatedParseTree);
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
