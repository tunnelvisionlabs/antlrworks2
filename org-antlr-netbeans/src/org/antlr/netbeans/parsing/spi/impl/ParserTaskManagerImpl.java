/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
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

import com.sun.xml.internal.ws.util.CompletedFuture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserDataEvent;
import org.antlr.netbeans.parsing.spi.ParserDataListener;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.editor.BaseDocument;
import org.netbeans.lib.editor.util.ListenerList;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.Parameters;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=ParserTaskManager.class)
public class ParserTaskManagerImpl implements ParserTaskManager {
    // -J-Dorg.antlr.netbeans.parsing.spi.impl.ParserTaskManagerImpl.level=FINE
    private static final Logger LOGGER = Logger.getLogger(ParserTaskManagerImpl.class.getName());

    private static final Long DEFAULT_DELAY = 500L;
    private static final TimeUnit DEFAULT_TIMEUNIT = TimeUnit.MILLISECONDS;

    private static final int HIGH_THREAD_PRIORITY_VALUE = Thread.NORM_PRIORITY;
    private static final int LOW_THREAD_PRIORITY_VALUE = Thread.NORM_PRIORITY - 2;

    private final ListenerList<ParserDataListener<Object>> globalListeners = new ListenerList<ParserDataListener<Object>>();

    private final Map<ParserDataDefinition<?>, ListenerList<ParserDataListener<?>>> dataListeners =
        new HashMap<ParserDataDefinition<?>, ListenerList<ParserDataListener<?>>>();

    private final Map<String, Collection<? extends ParserTaskProvider>> taskProviders =
        new HashMap<String, Collection<? extends ParserTaskProvider>>();

    private final RejectionHandler rejectionHandler;
    private final ScheduledThreadPoolExecutor highPriorityExecutor;
    private final ScheduledThreadPoolExecutor lowPriorityExecutor;

    public ParserTaskManagerImpl() {
        rejectionHandler = new RejectionHandler();

        int highPriorityPoolSize = 2;
        highPriorityExecutor = new ScheduledThreadPoolExecutor(highPriorityPoolSize, new ParserThreadFactory(HIGH_THREAD_PRIORITY_VALUE), rejectionHandler);

        int lowPriorityPoolSize = Math.max(2, Runtime.getRuntime().availableProcessors());
        lowPriorityExecutor = new ScheduledThreadPoolExecutor(lowPriorityPoolSize, new ParserThreadFactory(LOW_THREAD_PRIORITY_VALUE), rejectionHandler);
    }

    @Override
    public <T> Future<ParserData<T>> getData(DocumentSnapshot snapshot, ParserDataDefinition<T> definition) {
        return getData(snapshot, null, definition);
    }

    @Override
    public Future<ParserData<?>>[] getData(DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> definitions) {
        return getData(snapshot, null, definitions);
    }

    @Override
    public <T> Future<ParserData<T>> getData(final DocumentSnapshot snapshot, final ParserDataDefinition<T> definition, EnumSet<ParserDataOptions> options) {
        return getData(snapshot, null, definition, options);
    }

    @Override
    public Future<ParserData<?>>[] getData(DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> definitions, EnumSet<ParserDataOptions> options) {
        return getData(snapshot, null, definitions, options);
    }

    @Override
    public <T> Future<ParserData<T>> getData(DocumentSnapshot snapshot, JTextComponent component, ParserDataDefinition<T> definition) {
        return getData(snapshot, definition, EnumSet.noneOf(ParserDataOptions.class));
    }

    @Override
    public Future<ParserData<?>>[] getData(DocumentSnapshot snapshot, JTextComponent component, Collection<ParserDataDefinition<?>> definitions) {
        return getData(snapshot, definitions, EnumSet.noneOf(ParserDataOptions.class));
    }

    @Override
    public <T> Future<ParserData<T>> getData(final DocumentSnapshot snapshot, JTextComponent component, final ParserDataDefinition<T> definition, EnumSet<ParserDataOptions> options) {
        Parameters.notNull("snapshot", snapshot);
        Parameters.notNull("definition", definition);
        Parameters.notNull("options", options);

        @SuppressWarnings("unchecked")
        ParserData<T> cachedData = (ParserData<T>)snapshot.getVersionedDocument().getDocument().getProperty(definition);
        boolean useCached = options.contains(ParserDataOptions.NO_UPDATE);
        if (!useCached && cachedData != null) {
            if (options.contains(ParserDataOptions.ALLOW_STALE)) {
                useCached = true;
            } else if (cachedData.getSnapshot().equals(snapshot)) {
                useCached = true;
            }
        }

        if (useCached) {
            return new CompletedFuture<ParserData<T>>(cachedData, null);
        }

        Callable<ParserData<T>> callable = createCallable(snapshot, component, definition);
        if (options.contains(ParserDataOptions.SYNCHRONOUS) || isParserThread()) {
            try {
                return new CompletedFuture<ParserData<T>>(callable.call(), null);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
                return new CompletedFuture<ParserData<T>>(null, ex);
            }
        }

        return lowPriorityExecutor.schedule(callable, 0, TimeUnit.NANOSECONDS);
    }

    @Override
    public Future<ParserData<?>>[] getData(DocumentSnapshot snapshot, JTextComponent component, Collection<ParserDataDefinition<?>> definitions, EnumSet<ParserDataOptions> options) {
        Parameters.notNull("snapshot", snapshot);
        Parameters.notNull("definitions", definitions);
        Parameters.notNull("options", options);

        throw new UnsupportedOperationException("Not supported yet.");
//        List<ParserData<?>> data = new ArrayList<ParserData<?>>();
//        for (ParserDataDefinition<?> definition : definitions) {
//            data.add(getData(snapshot, definition, options));
//        }
//
//        return data.toArray(new ParserData<?>[0]);
    }

    @Override
    public void reschedule(VersionedDocument document, Class<? extends ParserTaskScheduler> schedulerClass) {
        reschedule(document, null, schedulerClass);
    }

    @Override
    public void reschedule(VersionedDocument document, JTextComponent component, Class<? extends ParserTaskScheduler> schedulerClass) {
        Collection<? extends ParserTaskScheduler> schedulers = Lookup.getDefault().lookupAll(ParserTaskScheduler.class);
        ParserTaskScheduler scheduler = null;
        for (ParserTaskScheduler i : schedulers) {
            if (i.getClass() == schedulerClass) {
                scheduler = i;
                break;
            }
        }

        if (scheduler != null) {
            @SuppressWarnings("rawtypes")
            Collection<? extends ParserDataDefinition> data = MimeLookup.getLookup(document.getMimeType()).lookupAll(ParserDataDefinition.class);
            for (ParserDataDefinition<?> definition : data) {
                if (definition.getScheduler() == schedulerClass && definition.isCacheable()) {
                    document.getDocument().putProperty(definition, null);
                }
            }

            scheduler.schedule(document, component);
        }
    }

    @Override
    public void reschedule(VersionedDocument document, JTextComponent component, long delay, TimeUnit timeUnit, Class<? extends ParserTaskScheduler> schedulerClass) {
        Collection<? extends ParserTaskScheduler> schedulers = Lookup.getDefault().lookupAll(ParserTaskScheduler.class);
        ParserTaskScheduler scheduler = null;
        for (ParserTaskScheduler i : schedulers) {
            if (i.getClass() == schedulerClass) {
                scheduler = i;
                break;
            }
        }

        if (scheduler != null) {
            @SuppressWarnings("rawtypes")
            Collection<? extends ParserDataDefinition> data = MimeLookup.getLookup(document.getMimeType()).lookupAll(ParserDataDefinition.class);
            for (ParserDataDefinition<?> definition : data) {
                if (definition.getScheduler() == schedulerClass && definition.isCacheable()) {
                    document.getDocument().putProperty(definition, null);
                }
            }

            scheduler.schedule(document, component, delay, timeUnit);
        }
    }

    @Override
    public <T> ScheduledFuture<ParserData<T>> scheduleData(VersionedDocument document, ParserDataDefinition<T> data) {
        return scheduleData(document, null, data);
    }

    @Override
    public Collection<ScheduledFuture<ParserData<?>>> scheduleData(VersionedDocument document, Collection<ParserDataDefinition<?>> data) {
        return scheduleData(document, null, data);
    }

    @Override
    public <T> ScheduledFuture<ParserData<T>> scheduleData(VersionedDocument document, ParserDataDefinition<T> data, long delay, TimeUnit timeUnit) {
        return scheduleData(document, null, data, delay, timeUnit);
    }

    @Override
    public Collection<ScheduledFuture<ParserData<?>>> scheduleData(VersionedDocument document, @NonNull Collection<ParserDataDefinition<?>> data, long delay, TimeUnit timeUnit) {
        return scheduleData(document, null, data, delay, timeUnit);
    }

    @Override
    public <T> ScheduledFuture<ParserData<T>> scheduleData(VersionedDocument document, JTextComponent component, ParserDataDefinition<T> data) {
        return scheduleData(document, component, data, DEFAULT_DELAY, DEFAULT_TIMEUNIT);
    }

    @Override
    public Collection<ScheduledFuture<ParserData<?>>> scheduleData(VersionedDocument document, JTextComponent component, Collection<ParserDataDefinition<?>> data) {
        return scheduleData(document, component, data, DEFAULT_DELAY, DEFAULT_TIMEUNIT);
    }

    @Override
    public <T> ScheduledFuture<ParserData<T>> scheduleData(VersionedDocument document, JTextComponent component, ParserDataDefinition<T> data, long delay, TimeUnit timeUnit) {
        Callable<ParserData<T>> callable = createCallable(document.getCurrentSnapshot(), component, data);
        return lowPriorityExecutor.schedule(callable, delay, timeUnit);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Collection<ScheduledFuture<ParserData<?>>> scheduleData(VersionedDocument document, JTextComponent component, @NonNull Collection<ParserDataDefinition<?>> data, long delay, TimeUnit timeUnit) {
        if (data.isEmpty()) {
            return Collections.emptyList();
        }

        List<ScheduledFuture<ParserData<?>>> futures = new ArrayList<ScheduledFuture<ParserData<?>>>();
        for (ParserDataDefinition dataDefinition : data) {
            futures.add((ScheduledFuture<ParserData<?>>)scheduleData(document, component, dataDefinition, delay, timeUnit));
        }

        return futures;
    }

    @Override
    public ScheduledFuture<Collection<ParserData<?>>> schedule(@NonNull VersionedDocument document, @NonNull ParserTaskProvider provider) {
        return schedule(document, null, provider);
    }

    @Override
    public Collection<ScheduledFuture<Collection<ParserData<?>>>> schedule(@NonNull VersionedDocument document, @NonNull Collection<ParserTaskProvider> providers) {
        return schedule(document, null, providers);
    }

    @Override
    public ScheduledFuture<Collection<ParserData<?>>> schedule(@NonNull VersionedDocument document, @NonNull ParserTaskProvider provider, long delay, @NonNull TimeUnit timeUnit) {
        return schedule(document, null, provider, delay, timeUnit);
    }

    @Override
    public Collection<ScheduledFuture<Collection<ParserData<?>>>> schedule(@NonNull VersionedDocument document, @NonNull Collection<ParserTaskProvider> providers, long delay, @NonNull TimeUnit timeUnit) {
        return schedule(document, null, providers, delay, timeUnit);
    }

    @Override
    public ScheduledFuture<Collection<ParserData<?>>> schedule(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, @NonNull ParserTaskProvider provider) {
        return schedule(document, component, provider, DEFAULT_DELAY, DEFAULT_TIMEUNIT);
    }

    @Override
    public Collection<ScheduledFuture<Collection<ParserData<?>>>> schedule(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, @NonNull Collection<ParserTaskProvider> providers) {
        return schedule(document, component, providers, DEFAULT_DELAY, DEFAULT_TIMEUNIT);
    }

    @Override
    public ScheduledFuture<Collection<ParserData<?>>> schedule(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, @NonNull ParserTaskProvider provider, long delay, @NonNull TimeUnit timeUnit) {
        Callable<Collection<ParserData<?>>> callable = createCallable(document.getCurrentSnapshot(), component, provider);
        return lowPriorityExecutor.schedule(callable, delay, timeUnit);
    }

    @Override
    public Collection<ScheduledFuture<Collection<ParserData<?>>>> schedule(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, @NonNull Collection<ParserTaskProvider> providers, long delay, @NonNull TimeUnit timeUnit) {
        if (providers.isEmpty()) {
            return Collections.emptyList();
        }

        Collection<ScheduledFuture<Collection<ParserData<?>>>> result = new ArrayList<ScheduledFuture<Collection<ParserData<?>>>>();
        for (ParserTaskProvider provider : providers) {
            result.add(schedule(document, component, provider, delay, timeUnit));
        }

        return result;
    }

    @Override
    public <T> ScheduledFuture<T> scheduleLowPriority(Callable<T> callable) {
        Parameters.notNull("callable", callable);

        return lowPriorityExecutor.schedule(callable, 0, TimeUnit.MILLISECONDS);
    }

    @Override
    public <T> ScheduledFuture<T> scheduleHighPriority(Callable<T> callable) {
        Parameters.notNull("callable", callable);

        return highPriorityExecutor.schedule(callable, 0, TimeUnit.MILLISECONDS);
    }

    @Override
    public void addDataListener(ParserDataListener<Object> listener) {
        Parameters.notNull("listener", listener);

        synchronized (globalListeners) {
            globalListeners.add(listener);
        }
    }

    @Override
    public void removeDataListener(ParserDataListener<Object> listener) {
        Parameters.notNull("listener", listener);

        synchronized (globalListeners) {
            globalListeners.remove(listener);
        }
    }

    @Override
    public <T> void addDataListener(ParserDataDefinition<T> definition, ParserDataListener<T> listener) {
        Parameters.notNull("definition", definition);
        Parameters.notNull("listener", listener);

        synchronized (dataListeners) {
            ListenerList<ParserDataListener<?>> listeners = dataListeners.get(definition);
            if (listeners == null) {
                listeners = new ListenerList<ParserDataListener<?>>();
                dataListeners.put(definition, listeners);
            }

            listeners.add(listener);
        }
    }

    @Override
    public <T> void removeDataListener(ParserDataDefinition<T> definition, ParserDataListener<T> listener) {
        Parameters.notNull("definition", definition);
        Parameters.notNull("listener", listener);

        synchronized (dataListeners) {
            ListenerList<ParserDataListener<?>> listeners = dataListeners.get(definition);
            if (listeners == null) {
                return;
            }

            listeners.remove(listener);
            if (listeners.getListenerCount() == 0) {
                dataListeners.remove(definition);
            }
        }
    }

    private static boolean isParserThread() {
        return Thread.currentThread() instanceof ParserThread;
    }

    private <T> Callable<ParserData<T>> createCallable(DocumentSnapshot snapshot, JTextComponent component, ParserDataDefinition<T> data) {
        Callable<ParserData<T>> callable = new UpdateDataCallable<T>(snapshot, component, data);
        return callable;
    }

    private Callable<Collection<ParserData<?>>> createCallable(DocumentSnapshot snapshot, JTextComponent component, ParserTaskProvider provider) {
        Callable<Collection<ParserData<?>>> callable = new UpdateTaskCallable(snapshot, component, provider);
        return callable;
    }

    private <T> void fireDataChanged(ParserDataDefinition<T> definition, ParserData<T> data) {
        ListenerList<ParserDataListener<?>> listeners;
        synchronized (dataListeners) {
            listeners = dataListeners.get(definition);
        }

        if (listeners == null && globalListeners.getListenerCount() == 0) {
            return;
        }

        ParserDataEvent<T> event = new ParserDataEvent<T>(this, definition, data);

        if (listeners != null) {
            for (ParserDataListener<?> listener : listeners.getListeners()) {
                @SuppressWarnings("unchecked")
                ParserDataListener<T> typedListener = (ParserDataListener<T>)listener;
                typedListener.dataChanged(event);
            }
        }

        for (ParserDataListener<Object> listener : globalListeners.getListeners()) {
            listener.dataChanged(event);
        }
    }

    private ParserTaskProvider getTaskProvider(VersionedDocument versionedDocument, ParserDataDefinition<?> definition) {
        Collection<? extends ParserTaskProvider> providers = getTaskProviders(versionedDocument);
        for (ParserTaskProvider provider : providers) {
            ParserTaskDefinition taskDefinition = provider.getDefinition();
            boolean hasOutput = taskDefinition.getOutputs().contains(definition);
            if (hasOutput) {
                return provider;
            }
        }

        return null;
    }

    private Collection<? extends ParserTaskProvider> getTaskProviders(VersionedDocument versionedDocument) {
        Document document = versionedDocument.getDocument();
        String mimeType = (String)document.getProperty(BaseDocument.MIME_TYPE_PROP);
        synchronized (taskProviders) {
            Collection<? extends ParserTaskProvider> providers = taskProviders.get(mimeType);
            if (providers == null) {
                providers = MimeLookup.getLookup(mimeType).lookupAll(ParserTaskProvider.class);
                taskProviders.put(mimeType, providers);
            }

            return providers;
        }
    }

    private static class RejectionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    private static class ParserThread extends Thread {

        public ParserThread(Runnable target) {
            super(target);
        }

    }

    private static class ParserThreadFactory implements ThreadFactory {
        private final int priority;
        private int threadCount;

        public ParserThreadFactory(int priority) {
            if (priority < Thread.MIN_PRIORITY || priority > Thread.MAX_PRIORITY) {
                throw new IllegalArgumentException("Invalid thread priority.");
            }

            this.priority = priority;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new ParserThread(r);
            thread.setPriority(priority);

            String priorityName = priority >= HIGH_THREAD_PRIORITY_VALUE ? "Foreground" : "Background";
            String name = String.format("Parse (%s) #%d", priorityName, ++threadCount);
            thread.setName(name);

            return thread;
        }
    }

    private class UpdateDataCallable<T> implements Callable<ParserData<T>> {
        private final DocumentSnapshot snapshot;
        private final JTextComponent component;
        private final ParserDataDefinition<T> data;

        public UpdateDataCallable(DocumentSnapshot snapshot, JTextComponent component, ParserDataDefinition<T> data) {
            this.snapshot = snapshot;
            this.component = component;
            this.data = data;
        }

        @Override
        @SuppressWarnings("unchecked")
        public ParserData<T> call() throws Exception {
            if (data.isCacheable()) {
                Document document = snapshot.getVersionedDocument().getDocument();
                if (document != null) {
                    ParserData<T> cachedData = (ParserData<T>)document.getProperty(data);
                    if (cachedData != null && cachedData.getSnapshot().equals(snapshot)) {
                        return cachedData;
                    }
                }
            }

            ParserTaskProvider provider = getTaskProvider(snapshot.getVersionedDocument(), data);
            if (provider == null) {
                LOGGER.log(Level.WARNING, "No provider found for parser data \"{0}\".", data.getName());
            } else if (LOGGER.isLoggable(Level.FINE)) {
                Object[] args = { provider.getDefinition().getName(), data.getName() };
                LOGGER.log(Level.FINE, "Using provider \"{0}\" for data \"{1}\".", args);
            }

            final ParserTask task = provider.createTask(snapshot.getVersionedDocument());

            if (LOGGER.isLoggable(Level.FINE)) {
                String threadName = Thread.currentThread().getName();
                String messageFormat = "{0}: Updating data \"{1}\" with task \"{2}\" for {3}#{4}";
                LOGGER.log(Level.FINE, messageFormat, new Object[] { threadName, data.getName(), task.getDefinition().getName(), snapshot.getVersionedDocument().getFileObject().getPath(), snapshot.getVersion().getVersionNumber() });
            }

            ResultAggregator handler = new ResultAggregator();
            task.parse(ParserTaskManagerImpl.this, component, snapshot, Collections.<ParserDataDefinition<?>>singleton(data), handler);

            Document document = snapshot.getVersionedDocument().getDocument();
            if (document != null) {
                for (ParserData<?> result : handler.getResults()) {
                    if (result.getDefinition().isCacheable()) {
                        document.putProperty(result.getDefinition(), result);
                    }

                    fireDataChanged((ParserDataDefinition)result.getDefinition(), result);
                }
            }

            for (ParserData<?> result : handler.getResults()) {
                if (result.getDefinition().equals(data)) {
                    return (ParserData<T>)result;
                }
            }

            return null;
        }
    }

    private class UpdateTaskCallable implements Callable<Collection<ParserData<?>>> {
        private final DocumentSnapshot snapshot;
        private final JTextComponent component;
        private final ParserTaskProvider provider;

        public UpdateTaskCallable(DocumentSnapshot snapshot, JTextComponent component, ParserTaskProvider provider) {
            this.snapshot = snapshot;
            this.component = component;
            this.provider = provider;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Collection<ParserData<?>> call() throws Exception {
            final ParserTask task = provider.createTask(snapshot.getVersionedDocument());

            if (LOGGER.isLoggable(Level.FINE)) {
                String messageFormat = "{0}: Updating task \"{1}\" for {2}#{3}";
                Object[] args =
                    {
                        Thread.currentThread().getName(),
                        task.getDefinition().getName(),
                        snapshot.getVersionedDocument().getFileObject().getPath(),
                        snapshot.getVersion().getVersionNumber()
                    };
                LOGGER.log(Level.FINE, messageFormat, args);
            }

            ResultAggregator handler = new ResultAggregator();
            task.parse(ParserTaskManagerImpl.this, component, snapshot, provider.getDefinition().getOutputs(), handler);

            Document document = snapshot.getVersionedDocument().getDocument();
            if (document != null) {
                for (ParserData<?> result : handler.getResults()) {
                    if (result.getDefinition().isCacheable()) {
                        document.putProperty(result.getDefinition(), result);
                    }

                    fireDataChanged((ParserDataDefinition)result.getDefinition(), result);
                }
            }

            return handler.getResults();
        }
    }

    private static class ResultAggregator implements ParserResultHandler {
        private final List<ParserData<?>> results = new ArrayList<ParserData<?>>();

        @Override
        public <T> void addResult(ParserData<T> result) {
            results.add(result);
        }

        public List<ParserData<?>> getResults() {
            return results;
        }
    }
}
