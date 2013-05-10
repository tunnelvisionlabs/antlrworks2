/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.navigation;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.antlr.netbeans.editor.navigation.Description;
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
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.antlr.works.editor.st4.TemplateParserDataDefinitions;
import org.antlr.works.editor.st4.parser.CompiledModel;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public final class TemplateScanningParserTask implements ParserTask {
    private final TemplateScanner templateScanner = new TemplateScanner();
    private final Object lock = new Object();

    private TemplateScanningParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        boolean explicitRequest = ParserTaskScheduler.MANUAL_TASK_SCHEDULER.isAssignableFrom(context.getSchedulerClass());
        if (requestedData.contains(TemplateParserDataDefinitions.NAVIGATOR_ROOT)) {
            synchronized (lock) {
                ParserData<Description> data = taskManager.getData(snapshot, TemplateParserDataDefinitions.NAVIGATOR_ROOT, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS)).get();
                if (data != null) {
                    results.addResult(data);
                    return;
                }

                EnumSet<ParserDataOptions> options;
                if (explicitRequest) {
                    options = EnumSet.of(ParserDataOptions.SYNCHRONOUS);
                } else {
                    options = EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS);
                }

                Future<ParserData<CompiledModel>> futureData = taskManager.getData(snapshot, context.getComponent(), TemplateParserDataDefinitions.COMPILED_MODEL, options);
                ParserData<CompiledModel> parserData = futureData != null ? futureData.get() : null;
                if (parserData == null && !explicitRequest) {
                    return;
                }

                CompiledModel model = parserData != null ? parserData.getData() : null;
                if (model != null) {
                    TemplateScanner scanner = getScanner(model);
                    Description description = scanner.scan(model);
                    if (description != null) {
                        data = new BaseParserData<>(context, TemplateParserDataDefinitions.NAVIGATOR_ROOT, snapshot, description);
                        results.addResult(data);
                    }
                }
            }
        }
    }

    private TemplateScanner getScanner(CompiledModel model) {
        return templateScanner;
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(TemplateParserDataDefinitions.COMPILED_MODEL);
        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(TemplateParserDataDefinitions.NAVIGATOR_ROOT);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("StringTemplate Template Scanner", INPUTS, OUTPUTS, ParserTaskScheduler.INPUT_SENSITIVE_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends DocumentParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTaskImpl(VersionedDocument document) {
            return new TemplateScanningParserTask();
        }

    }

}
