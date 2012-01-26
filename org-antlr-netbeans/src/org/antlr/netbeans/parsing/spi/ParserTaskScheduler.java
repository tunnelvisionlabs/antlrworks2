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
package org.antlr.netbeans.parsing.spi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.parsing.spi.impl.CurrentDocumentParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.impl.CursorSensitiveParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.impl.DataInputParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.impl.DocumentContentParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.impl.SelectedNodesParserTaskScheduler;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.openide.util.Lookup;

/**
 *
 * @author Sam Harwell
 */
public abstract class ParserTaskScheduler {
    // -J-Dorg.antlr.netbeans.parsing.spi.ParserTaskScheduler.level=FINE
    private static final Logger LOGGER = Logger.getLogger(ParserTaskScheduler.class.getName());

    public static final Class<? extends ParserTaskScheduler> CONTENT_SENSITIVE_TASK_SCHEDULER =
        DocumentContentParserTaskScheduler.class;

    public static final Class<? extends ParserTaskScheduler> EDITOR_SENSITIVE_TASK_SCHEDULER =
        CurrentDocumentParserTaskScheduler.class;

    public static final Class<? extends ParserTaskScheduler> CURSOR_SENSITIVE_TASK_SCHEDULER =
        CursorSensitiveParserTaskScheduler.class;

    public static final Class<? extends ParserTaskScheduler> SELECTED_NODES_SENSITIVE_TASK_SCHEDULER =
        SelectedNodesParserTaskScheduler.class;

    public static final Class<? extends ParserTaskScheduler> INPUT_SENSITIVE_TASK_SCHEDULER =
        DataInputParserTaskScheduler.class;

    private final Map<VersionedDocument, Map<ParserDataDefinition<?>, ScheduledFuture<ParserData<?>>>> scheduledDocumentDataTasks =
        new WeakHashMap<VersionedDocument, Map<ParserDataDefinition<?>, ScheduledFuture<ParserData<?>>>>();

    private final Map<VersionedDocument, Map<ParserTaskProvider, ScheduledFuture<Collection<ParserData<?>>>>> scheduledDocumentTasks =
        new WeakHashMap<VersionedDocument, Map<ParserTaskProvider, ScheduledFuture<Collection<ParserData<?>>>>>();

    private boolean initialized;

    public final void initialize() {
        if (initialized) {
            throw new IllegalStateException("The scheduler is already initialized.");
        }

        initializeImpl();
    }

    public void schedule(VersionedDocument document) {
        schedule(document, (JTextComponent)null);
    }

    public void schedule(VersionedDocument document, JTextComponent component) {
        schedule(document, component, getParseDelayMilliseconds(), TimeUnit.MILLISECONDS);
    }

    public void schedule(VersionedDocument document, JTextComponent component, long delay, TimeUnit timeUnit) {
        if (document == null) {
            return;
        }

        // Schedule data updates
        @SuppressWarnings("unchecked")
        Collection<? extends ParserDataDefinition<?>> mimeData = (Collection<? extends ParserDataDefinition<?>>)MimeLookup.getLookup(document.getMimeType()).lookupAll(ParserDataDefinition.class);
        Set<ParserDataDefinition<?>> currentScheduledData = new HashSet<ParserDataDefinition<?>>();
        for (ParserDataDefinition<?> data : mimeData) {
            if (getClass().equals(data.getScheduler())) {
                currentScheduledData.add(data);
            }
        }

        if (!currentScheduledData.isEmpty()) {
            Map<ParserDataDefinition<?>, ScheduledFuture<ParserData<?>>> existing;
            synchronized(scheduledDocumentDataTasks) {
                existing = scheduledDocumentDataTasks.get(document);
                if (existing == null) {
                    existing = new HashMap<ParserDataDefinition<?>, ScheduledFuture<ParserData<?>>>();
                    scheduledDocumentDataTasks.put(document, existing);
                }
            }

            synchronized (existing) {
                for (ParserDataDefinition<?> definition : currentScheduledData) {
                    ScheduledFuture<?> previous = existing.remove(definition);
                    if (previous != null) {
                        previous.cancel(false);
                    }
                }
            }

            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "Rescheduling {0} data, document={1}, delay={2}{3}, data={4}", new Object[] { getClass().getSimpleName(), document.getFileObject().getPath(), delay, getTimeUnitDisplay(timeUnit), currentScheduledData });
            }

            Map<ParserDataDefinition<?>, ScheduledFuture<ParserData<?>>> futures = getTaskManager().scheduleData(document, component, currentScheduledData, delay, timeUnit);
            synchronized (existing) {
                existing.putAll(futures);
            }
        }
    }

    protected void schedule(VersionedDocument document, Collection<ParserTaskProvider> tasks) {
        schedule(document, null, tasks);
    }

    protected void schedule(VersionedDocument document, JTextComponent component, Collection<ParserTaskProvider> tasks) {
        schedule(document, component, tasks, getParseDelayMilliseconds(), TimeUnit.MILLISECONDS);
    }

    protected void schedule(VersionedDocument document, JTextComponent component, Collection<? extends ParserTaskProvider> taskProviders, long delay, TimeUnit timeUnit) {
        if (document == null) {
            return;
        }

        // Schedule task updates
        Set<ParserTaskProvider> currentScheduledProviders = new HashSet<ParserTaskProvider>();
        providerLoop:
        for (ParserTaskProvider provider : taskProviders) {
            if (getClass().equals(provider.getDefinition().getScheduler())) {
                currentScheduledProviders.add(provider);
            }
        }

        if (!currentScheduledProviders.isEmpty()) {
            Map<ParserTaskProvider, ScheduledFuture<Collection<ParserData<?>>>> existing;
            synchronized(scheduledDocumentTasks) {
                existing = scheduledDocumentTasks.get(document);
                if (existing == null) {
                    existing = new HashMap<ParserTaskProvider, ScheduledFuture<Collection<ParserData<?>>>>();
                    scheduledDocumentTasks.put(document, existing);
                }
            }

            synchronized (existing) {
                for (ParserTaskProvider provider : currentScheduledProviders) {
                    ScheduledFuture<?> previous = existing.remove(provider);
                    if (previous != null) {
                        previous.cancel(false);
                    }
                }
            }

            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "Rescheduling {0} tasks, document={1}, delay={2}{3}, data={4}", new Object[] { getClass().getSimpleName(), document.getFileObject().getPath(), delay, getTimeUnitDisplay(timeUnit), currentScheduledProviders });
            }

            Map<ParserTaskProvider, ScheduledFuture<Collection<ParserData<?>>>> futures = getTaskManager().schedule(document, component, currentScheduledProviders, delay, timeUnit);
            synchronized (existing) {
                existing.putAll(futures);
            }
        }
    }

    protected int getParseDelayMilliseconds() {
        return 500;
    }

    protected ParserTaskManager getTaskManager() {
        return Lookup.getDefault().lookup(ParserTaskManager.class);
    }

    protected void initializeImpl() {
    }

    private static String getTimeUnitDisplay(TimeUnit timeUnit) {
        switch (timeUnit) {
        case MICROSECONDS:
            return "usec";
        case MILLISECONDS:
            return "ms";
        case MINUTES:
            return "min";
        case SECONDS:
            return "sec";
        case NANOSECONDS:
            return "ns";
        default:
            return timeUnit.name().toLowerCase();
        }
    }
}
