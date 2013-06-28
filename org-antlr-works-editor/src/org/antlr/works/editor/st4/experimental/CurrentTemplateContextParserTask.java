/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.works.editor.antlr4.classification.DocumentSnapshotCharStream;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.antlr.works.editor.st4.TemplateParserDataDefinitions;
import org.antlr.works.editor.st4.experimental.generated.TemplateParser;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public final class CurrentTemplateContextParserTask implements ParserTask {

    private CurrentTemplateContextParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_group, version=0, dependents=Dependents.SELF)
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        if (context.getPosition() == null) {
            return;
        }

        int caretOffset = context.getPosition().getOffset();

        Future<ParserData<List<Anchor>>> result =
            taskManager.getData(snapshot, TemplateParserDataDefinitions.DYNAMIC_ANCHOR_POINTS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));

        ParserData<List<Anchor>> anchorsData = result.get();
        List<Anchor> anchors = anchorsData.getData();

        TemplateParser.GroupContext ruleContext = null;

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
                TemplateParser parser = TemplateParserFactory.DEFAULT.getParser(tokens);
                try {
                    parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
                    parser.removeErrorListeners();
                    parser.setBuildParseTree(true);
                    parser.setErrorHandler(new BailErrorStrategy());
                    ruleContext = parser.group();
                } catch (ParseCancellationException ex) {
                    if (ex.getCause() instanceof RecognitionException) {
                        // retry with default error handler
                        tokens.reset();
                        parser.getInterpreter().setPredictionMode(PredictionMode.LL);
                        parser.setInputStream(tokens);
                        parser.setErrorHandler(new DefaultErrorStrategy());
                        ruleContext = parser.group();
                    } else {
                        throw ex;
                    }
                }
            }
        }

        CurrentTemplateContextData data = new CurrentTemplateContextData(snapshot, ruleContext);
        results.addResult(new BaseParserData<>(context, TemplateParserDataDefinitions.CURRENT_TEMPLATE_CONTEXT, snapshot, data));
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
