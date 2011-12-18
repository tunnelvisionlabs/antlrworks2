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
import javax.swing.text.Document;
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
import org.netbeans.api.editor.mimelookup.MimeLookup;
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
    private static final Long DEFAULT_DELAY = 500L;
    private static final TimeUnit DEFAULT_TIMEUNIT = TimeUnit.MILLISECONDS;

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
        highPriorityExecutor = new ScheduledThreadPoolExecutor(highPriorityPoolSize, new ParserThreadFactory(Thread.NORM_PRIORITY), rejectionHandler);

        int lowPriorityPoolSize = Math.max(2, Runtime.getRuntime().availableProcessors());
        lowPriorityExecutor = new ScheduledThreadPoolExecutor(lowPriorityPoolSize, new ParserThreadFactory(Thread.NORM_PRIORITY - 2), rejectionHandler);
    }

    @Override
    public <T> Future<ParserData<T>> getData(DocumentSnapshot snapshot, ParserDataDefinition<T> definition) {
        return getData(snapshot, definition, EnumSet.noneOf(ParserDataOptions.class));
    }

    @Override
    public Future<ParserData<?>>[] getData(DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> definitions) {
        return getData(snapshot, definitions, EnumSet.noneOf(ParserDataOptions.class));
    }

    @Override
    public <T> Future<ParserData<T>> getData(final DocumentSnapshot snapshot, final ParserDataDefinition<T> definition, EnumSet<ParserDataOptions> options) {
        Parameters.notNull("snapshot", snapshot);
        Parameters.notNull("definition", definition);
        Parameters.notNull("options", options);

        Callable<ParserData<T>> callable = createCallable(snapshot, definition);
        if (options.contains(ParserDataOptions.ALLOW_STALE)) {
            @SuppressWarnings("unchecked")
            ParserData<T> cachedData = (ParserData<T>)snapshot.getVersionedDocument().getDocument().getProperty(definition);
            if (cachedData != null || options.contains(ParserDataOptions.NO_UPDATE)) {
                return new CompletedFuture<ParserData<T>>(cachedData, null);
            }
        }

        if (options.contains(ParserDataOptions.SYNCHRONOUS)) {
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
    public Future<ParserData<?>>[] getData(DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> definitions, EnumSet<ParserDataOptions> options) {
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
    public <T> ScheduledFuture<ParserData<T>> schedule(VersionedDocument document, ParserDataDefinition<T> data) {
        return schedule(document, data, DEFAULT_DELAY, DEFAULT_TIMEUNIT);
    }

    @Override
    public Collection<ScheduledFuture<ParserData<?>>> schedule(VersionedDocument document, Collection<ParserDataDefinition<?>> data) {
        return schedule(document, data, DEFAULT_DELAY, DEFAULT_TIMEUNIT);
    }

    @Override
    public <T> ScheduledFuture<ParserData<T>> schedule(VersionedDocument document, ParserDataDefinition<T> data, long delay, TimeUnit timeUnit) {
        Callable<ParserData<T>> callable = createCallable(document.getCurrentSnapshot(), data);
        return lowPriorityExecutor.schedule(callable, delay, timeUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<ScheduledFuture<ParserData<?>>> schedule(VersionedDocument document, @NonNull Collection<ParserDataDefinition<?>> data, long delay, TimeUnit timeUnit) {
        if (data.isEmpty()) {
            return Collections.emptyList();
        }

        List<ScheduledFuture<ParserData<?>>> futures = new ArrayList<ScheduledFuture<ParserData<?>>>();
        for (ParserDataDefinition<?> dataDefinition : data) {
            futures.add((ScheduledFuture<ParserData<?>>)schedule(document, dataDefinition, delay, timeUnit));
        }

        return futures;
    }

    @Override
    public <T> ScheduledFuture<T> scheduleHighPriority(Callable<T> callable) {
        Parameters.notNull("callable", callable);

        return highPriorityExecutor.schedule(callable, 0, TimeUnit.MILLISECONDS);
    }

    @Override
    public <T> void addDataListener(ParserDataDefinition<T> definition, ParserDataListener<T> listener) {
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

    private <T> Callable<ParserData<T>> createCallable(DocumentSnapshot snapshot, ParserDataDefinition<T> data) {
        Callable<ParserData<T>> callable = new UpdateDataCallable<T>(snapshot, data);
        return callable;
    }

    private <T> void fireDataChanged(ParserDataDefinition<T> definition, ParserData<T> data) {
        ListenerList<ParserDataListener<?>> listeners;
        synchronized (dataListeners) {
            listeners = dataListeners.get(definition);
        }

        if (listeners == null) {
            return;
        }

        ParserDataEvent<T> event = new ParserDataEvent<T>(this, definition, data);
        for (ParserDataListener<?> listener : listeners.getListeners()) {
            @SuppressWarnings("unchecked")
            ParserDataListener<T> typedListener = (ParserDataListener<T>)listener;
            typedListener.dataChanged(event);
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

    private static class ParserThreadFactory implements ThreadFactory {
        private final int priority;

        public ParserThreadFactory(int priority) {
            if (priority < Thread.MIN_PRIORITY || priority > Thread.MAX_PRIORITY) {
                throw new IllegalArgumentException("Invalid thread priority.");
            }

            this.priority = priority;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setPriority(priority);
            return thread;
        }
    }

    private class UpdateDataCallable<T> implements Callable<ParserData<T>> {
        private final DocumentSnapshot snapshot;
        private final ParserDataDefinition<T> data;

        public UpdateDataCallable(DocumentSnapshot snapshot, ParserDataDefinition<T> data) {
            this.snapshot = snapshot;
            this.data = data;
        }

        @Override
        @SuppressWarnings("unchecked")
        public ParserData<T> call() throws Exception {
            ParserTaskProvider provider = getTaskProvider(snapshot.getVersionedDocument(), data);
            final ParserTask task = provider.createTask(snapshot.getVersionedDocument());

            ResultAggregator handler = new ResultAggregator();
            task.parse(ParserTaskManagerImpl.this, null, snapshot, Collections.<ParserDataDefinition<?>>singleton(data), handler);

            Document document = snapshot.getVersionedDocument().getDocument();
            if (document != null) {
                for (ParserData<?> result : handler.getResults()) {
                    document.putProperty(result.getDefinition(), result);
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
