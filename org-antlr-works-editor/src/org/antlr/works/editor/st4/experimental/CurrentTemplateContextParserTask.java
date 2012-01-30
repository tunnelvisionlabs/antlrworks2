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
package org.antlr.works.editor.st4.experimental;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.BaseParserData;
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
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.works.editor.antlr4.classification.DocumentSnapshotCharStream;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.antlr.works.editor.st4.TemplateParserDataDefinitions;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public class CurrentTemplateContextParserTask implements ParserTask {

    private CurrentTemplateContextParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        if (context.getPosition() == null) {
            return;
        }

        int caretOffset = context.getPosition().getOffset();

        Future<ParserData<List<Anchor>>> result =
            taskManager.getData(snapshot, TemplateParserDataDefinitions.DYNAMIC_ANCHOR_POINTS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));

        ParserData<List<Anchor>> anchorsData = result.get();
        List<Anchor> anchors = anchorsData.getData();

        TemplateParser.groupContext ruleContext = null;

        if (anchors != null) {
            Anchor enclosing = null;
//            int grammarType = -1;

            /*
             * parse the current template
             */
            for (Anchor anchor : anchors) {
//                if (anchor instanceof TemplateParserAnchorListener.TemplateTypeAnchor) {
//                    grammarType = ((TemplateParserAnchorListener.TemplateTypeAnchor)anchor).getGrammarType();
//                    continue;
//                }

                if (anchor.getSpan().getStartPosition(snapshot).getOffset() <= caretOffset && anchor.getSpan().getEndPosition(snapshot).getOffset() > caretOffset) {
                    enclosing = anchor;
                } else if (anchor.getSpan().getStartPosition(snapshot).getOffset() > caretOffset) {
                    break;
                }
            }

            if (enclosing != null) {
                CharStream input = new DocumentSnapshotCharStream(snapshot);
                input.seek(enclosing.getSpan().getStartPosition(snapshot).getOffset());
                TemplateLexer lexer = new TemplateLexer(input);
                CommonTokenStream tokens = new TaskTokenStream(lexer);
                TemplateParser parser = TemplateParserCache.DEFAULT.getParser(tokens);
                try {
                    parser.setBuildParseTree(true);
                    ruleContext = parser.group();
                } finally {
                    TemplateParserCache.DEFAULT.putParser(parser);
                }
            }
        }

        CurrentTemplateContextData data = new CurrentTemplateContextData(snapshot, ruleContext);
        results.addResult(new BaseParserData<CurrentTemplateContextData>(context, TemplateParserDataDefinitions.CURRENT_TEMPLATE_CONTEXT, snapshot, data));
    }

    private static class TaskTokenStream extends CommonTokenStream {

        public TaskTokenStream(TokenSource tokenSource) {
            super(tokenSource);
        }

        @Override
        public int LA(int i) {
//            if (cancelled) {
//                throw new CancellationException();
//            }

            return super.LA(i);
        }

    }

    private static final class Definition extends ParserTaskDefinition {

        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(TemplateParserDataDefinitions.DYNAMIC_ANCHOR_POINTS);
        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(TemplateParserDataDefinitions.CURRENT_TEMPLATE_CONTEXT);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("StringTemplate Current Template Context", INPUTS, OUTPUTS, ParserTaskScheduler.CURSOR_SENSITIVE_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends SingletonParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTaskImpl() {
            return new CurrentTemplateContextParserTask();
        }

    }
}
