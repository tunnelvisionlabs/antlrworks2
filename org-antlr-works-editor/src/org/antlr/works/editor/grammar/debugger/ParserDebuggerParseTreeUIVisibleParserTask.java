/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.BaseParserData;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.SingletonParserTaskProvider;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public final class ParserDebuggerParseTreeUIVisibleParserTask implements ParserTask {

    private ParserDebuggerParseTreeUIVisibleParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        if (requestedData.contains(ParserDebuggerParserDataDefinitions.PARSE_TREE_UI_VISIBLE)) {
            BaseParserData<Boolean> data = new BaseParserData<>(context, ParserDebuggerParserDataDefinitions.PARSE_TREE_UI_VISIBLE, snapshot, ParserDebuggerParseTreeNavigatorPanel.getInstance() != null);
            results.addResult(data);
        }
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.emptyList();
        private static final Collection<? extends ParserDataDefinition<?>> OUTPUTS =
            Collections.singletonList(ParserDebuggerParserDataDefinitions.PARSE_TREE_UI_VISIBLE);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Parse Tree UI Visible Update", INPUTS, OUTPUTS, ParserTaskScheduler.MANUAL_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=ParserDebuggerEditorKit.PARSER_DEBUGGER_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends SingletonParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTaskImpl() {
            return new ParserDebuggerParseTreeUIVisibleParserTask();
        }

    }

}
