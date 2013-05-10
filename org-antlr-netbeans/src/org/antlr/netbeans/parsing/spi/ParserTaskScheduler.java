/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
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
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.parsing.spi.impl.CurrentDocumentParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.impl.CursorSensitiveParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.impl.DataInputParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.impl.DocumentContentParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.impl.ManualParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.impl.SelectedNodesParserTaskScheduler;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.Parameters;

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

    public static final Class<? extends ParserTaskScheduler> MANUAL_TASK_SCHEDULER =
        ManualParserTaskScheduler.class;

    private final Map<VersionedDocument, Map<ParserDataDefinition<?>, Reference<ScheduledFuture<ParserData<?>>>>> scheduledDocumentDataTasks =
        new WeakHashMap<>();

    private final Map<VersionedDocument, Map<ParserTaskProvider, Reference<ScheduledFuture<Collection<? extends ParserData<?>>>>>> scheduledDocumentTasks =
        new WeakHashMap<>();

    private boolean initialized;

    public final void initialize() {
        if (initialized) {
            throw new IllegalStateException("The scheduler is already initialized.");
        }

        initializeImpl();
    }

    public void cancelAll(boolean mayInterruptIfRunning) {
        Collection<VersionedDocument> dataDocuments;
        Collection<VersionedDocument> taskDocuments;

        synchronized (scheduledDocumentDataTasks) {
            dataDocuments = new ArrayList<>(scheduledDocumentDataTasks.keySet());
        }

        synchronized (scheduledDocumentTasks) {
            taskDocuments = new ArrayList<>(scheduledDocumentTasks.keySet());
        }

        for (VersionedDocument document : dataDocuments) {
            cancelDataTasks(document, mayInterruptIfRunning);
        }

        for (VersionedDocument document : taskDocuments) {
            cancelTasks(document, mayInterruptIfRunning);
        }
    }

    public void cancelAll(@NonNull VersionedDocument document, boolean mayInterruptIfRunning) {
        Parameters.notNull("document", document);
        cancelDataTasks(document, mayInterruptIfRunning);
        cancelTasks(document, mayInterruptIfRunning);
    }

    public void cancelDataTasks(VersionedDocument document, boolean mayInterruptIfRunning) {
        Map<ParserDataDefinition<?>, Reference<ScheduledFuture<ParserData<?>>>> dataTasks;
        synchronized (scheduledDocumentDataTasks) {
            dataTasks = scheduledDocumentDataTasks.remove(document);
        }

        if (dataTasks != null) {
            for (Map.Entry<ParserDataDefinition<?>, Reference<ScheduledFuture<ParserData<?>>>> entry : dataTasks.entrySet()) {
                ScheduledFuture<?> scheduled = entry.getValue().get();
                if (scheduled == null) {
                    continue;
                }

                scheduled.cancel(mayInterruptIfRunning && entry.getKey().isInterruptable());
            }
        }
    }

    public void cancelTasks(VersionedDocument document, boolean mayInterruptIfRunning) {
        Map<ParserTaskProvider, Reference<ScheduledFuture<Collection<? extends ParserData<?>>>>> tasks;
        synchronized (scheduledDocumentTasks) {
            tasks = scheduledDocumentTasks.remove(document);
        }

        if (tasks != null) {
            for (Map.Entry<ParserTaskProvider, Reference<ScheduledFuture<Collection<? extends ParserData<?>>>>> entry : tasks.entrySet()) {
                ScheduledFuture<?> scheduled = entry.getValue().get();
                if (scheduled == null) {
                    continue;
                }

                scheduled.cancel(mayInterruptIfRunning && entry.getKey().getDefinition().isInterruptable());
            }
        }
    }

    public void schedule(ParseContext context) {
        schedule(context, getParseDelayMilliseconds(), TimeUnit.MILLISECONDS);
    }

    public void schedule(ParseContext context, long delay, TimeUnit timeUnit) {
        if (context == null) {
            return;
        }

        assert context.getSchedulerClass() == getClass();

        // Schedule data updates
        String mimeType = context.getDocument().getMimeType();
        Set<ParserDataDefinition<?>> currentScheduledData = new HashSet<>();
        for (ParserDataDefinition<?> data : MimeLookup.getLookup(mimeType).lookupAll(ParserDataDefinition.class)) {
            if (getClass().equals(data.getScheduler())) {
                currentScheduledData.add(data);
            }
        }

        if (!currentScheduledData.isEmpty()) {
            VersionedDocument document = context.getDocument();
            Map<ParserDataDefinition<?>, Reference<ScheduledFuture<ParserData<?>>>> existing;
            synchronized (scheduledDocumentDataTasks) {
                existing = scheduledDocumentDataTasks.get(document);
                if (existing == null) {
                    existing = new HashMap<>();
                    scheduledDocumentDataTasks.put(document, existing);
                }
            }

            synchronized (existing) {
                for (ParserDataDefinition<?> definition : currentScheduledData) {
                    Reference<? extends ScheduledFuture<?>> previousRef = existing.remove(definition);
                    ScheduledFuture<?> previous = previousRef != null ? previousRef.get() : null;
                    if (previous != null) {
                        previous.cancel(false);
                    }
                }
            }

            if (LOGGER.isLoggable(Level.FINE)) {
                FileObject fileObject = document.getFileObject();
                String path = fileObject != null ? fileObject.getPath() : "";
                LOGGER.log(Level.FINE, "Rescheduling {0} data, document={1}, delay={2}{3}, data={4}", new Object[] { getClass().getSimpleName(), path, delay, getTimeUnitDisplay(timeUnit), currentScheduledData });
            }

            Map<ParserDataDefinition<?>, ScheduledFuture<ParserData<?>>> futures = getTaskManager().scheduleData(context, currentScheduledData, delay, timeUnit);
            synchronized (existing) {
                for (Map.Entry<ParserDataDefinition<?>, ScheduledFuture<ParserData<?>>> entry : futures.entrySet()) {
                    existing.put(entry.getKey(), new WeakReference<>(entry.getValue()));
                }
            }
        }
    }

    protected void schedule(ParseContext context, Collection<ParserTaskProvider> tasks) {
        schedule(context, tasks, getParseDelayMilliseconds(), TimeUnit.MILLISECONDS);
    }

    protected void schedule(ParseContext context, Collection<? extends ParserTaskProvider> taskProviders, long delay, TimeUnit timeUnit) {
        if (context == null) {
            return;
        }

        assert context.getSchedulerClass() == getClass();

        // Schedule task updates
        Set<ParserTaskProvider> currentScheduledProviders = new HashSet<>();
        providerLoop:
        for (ParserTaskProvider provider : taskProviders) {
            if (getClass().equals(provider.getDefinition().getScheduler())) {
                currentScheduledProviders.add(provider);
            }
        }

        if (!currentScheduledProviders.isEmpty()) {
            VersionedDocument document = context.getDocument();
            Map<ParserTaskProvider, Reference<ScheduledFuture<Collection<? extends ParserData<?>>>>> existing;
            synchronized(scheduledDocumentTasks) {
                existing = scheduledDocumentTasks.get(document);
                if (existing == null) {
                    existing = new HashMap<>();
                    scheduledDocumentTasks.put(document, existing);
                }
            }

            synchronized (existing) {
                for (ParserTaskProvider provider : currentScheduledProviders) {
                    Reference<? extends ScheduledFuture<?>> previousRef = existing.remove(provider);
                    ScheduledFuture<?> previous = previousRef != null ? previousRef.get() : null;
                    if (previous != null) {
                        previous.cancel(false);
                    }
                }
            }

            if (LOGGER.isLoggable(Level.FINE)) {
                FileObject fileObject = document.getFileObject();
                String path = fileObject != null ? fileObject.getPath() : "";
                LOGGER.log(Level.FINE, "Rescheduling {0} tasks, document={1}, delay={2}{3}, data={4}", new Object[] { getClass().getSimpleName(), path, delay, getTimeUnitDisplay(timeUnit), currentScheduledProviders });
            }

            Map<ParserTaskProvider, ScheduledFuture<Collection<? extends ParserData<?>>>> futures = getTaskManager().scheduleTask(context, currentScheduledProviders, delay, timeUnit);
            synchronized (existing) {
                for (Map.Entry<ParserTaskProvider, ScheduledFuture<Collection<? extends ParserData<?>>>> entry : futures.entrySet()) {
                    existing.put(entry.getKey(), new WeakReference<>(entry.getValue()));
                }
            }
        }
    }

    protected static boolean hasAssociatedDataDefinitions(@NonNull Class<? extends ParserTaskScheduler> schedulerClass, @NullAllowed String mimeType) {
        if (mimeType == null) {
            return false;
        }

        for (ParserDataDefinition<?> data : MimeLookup.getLookup(mimeType).lookupAll(ParserDataDefinition.class)) {
            if (schedulerClass.equals(data.getScheduler())) {
                return true;
            }
        }

        return false;
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
