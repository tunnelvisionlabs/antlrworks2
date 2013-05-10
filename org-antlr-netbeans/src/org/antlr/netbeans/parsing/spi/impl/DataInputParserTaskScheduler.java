/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserDataEvent;
import org.antlr.netbeans.parsing.spi.ParserDataListener;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=ParserTaskScheduler.class)
public class DataInputParserTaskScheduler extends ParserTaskScheduler {
    private final ParserDataListener<Object> listener = new Listener();
    private final Map<ParserDataDefinition<?>, List<ParserTaskProvider>> dependentTasks =
        new HashMap<>();

    @Override
    protected void initializeImpl() {
        ParserTaskManager taskManager = getTaskManager();
        taskManager.addDataListener(listener);
    }

    @Override
    protected int getParseDelayMilliseconds() {
        return 0;
    }

    private class Listener implements ParserDataListener<Object> {

        @Override
        public void dataChanged(ParserDataEvent<?> event) {
            if (event == null || event.getData() == null) {
                return;
            }

            List<ParserTaskProvider> tasks;
            synchronized (dependentTasks) {
                tasks = dependentTasks.get(event.getDefinition());
                if (tasks == null) {
                    tasks = new ArrayList<>();
                    String mimeType = event.getData().getSnapshot().getVersionedDocument().getMimeType();
                    Collection<? extends ParserTaskProvider> providers = MimeLookup.getLookup(mimeType).lookupAll(ParserTaskProvider.class);
                    for (ParserTaskProvider provider : providers) {
                        if (!DataInputParserTaskScheduler.this.getClass().equals(provider.getDefinition().getScheduler())) {
                            continue;
                        }

                        if (provider.getDefinition().getInputs().contains(event.getData().getDefinition())) {
                            tasks.add(provider);
                        }
                    }

                    dependentTasks.put(event.getDefinition(), tasks);
                }
            }

            ParseContext context = new ParseContext(DataInputParserTaskScheduler.this.getClass(), event.getData().getSnapshot());
            schedule(context, tasks);
        }

    }
}
