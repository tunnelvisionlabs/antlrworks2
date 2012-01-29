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
    private ParserDataListener<Object> listener = new Listener();
    private final Map<ParserDataDefinition<?>, List<ParserTaskProvider>> dependentTasks =
        new HashMap<ParserDataDefinition<?>, List<ParserTaskProvider>>();

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
                    tasks = new ArrayList<ParserTaskProvider>();
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

            ParseContext context = new ParseContext(DataInputParserTaskScheduler.this, event.getData().getSnapshot());
            schedule(context, tasks);
        }

    }
}
