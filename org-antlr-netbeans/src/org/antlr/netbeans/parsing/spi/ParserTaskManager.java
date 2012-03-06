/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;

/**
 *
 * @author Sam Harwell
 */
public interface ParserTaskManager {

    @CheckForNull <T> Future<ParserData<T>> getData(@NonNull DocumentSnapshot snapshot, @NonNull ParserDataDefinition<T> definition);

    @NonNull Future<ParserData<?>>[] getData(@NonNull DocumentSnapshot snapshot, @NonNull Collection<? extends ParserDataDefinition<?>> definitions);

    @CheckForNull <T> Future<ParserData<T>> getData(@NonNull DocumentSnapshot snapshot, @NonNull ParserDataDefinition<T> definition, @NonNull Collection<ParserDataOptions> options);

    @NonNull Future<ParserData<?>>[] getData(@NonNull DocumentSnapshot snapshot, @NonNull Collection<? extends ParserDataDefinition<?>> definitions, @NonNull Collection<ParserDataOptions> options);

    @CheckForNull <T> Future<ParserData<T>> getData(@NonNull DocumentSnapshot snapshot, @NullAllowed JTextComponent component, @NonNull ParserDataDefinition<T> definition);

    @NonNull Future<ParserData<?>>[] getData(@NonNull DocumentSnapshot snapshot, @NullAllowed JTextComponent component, @NonNull Collection<? extends ParserDataDefinition<?>> definitions);

    @CheckForNull <T> Future<ParserData<T>> getData(@NonNull DocumentSnapshot snapshot, @NullAllowed JTextComponent component, @NonNull ParserDataDefinition<T> definition, @NonNull Collection<ParserDataOptions> options);

    @NonNull Future<ParserData<?>>[] getData(@NonNull DocumentSnapshot snapshot, @NullAllowed JTextComponent component, @NonNull Collection<? extends ParserDataDefinition<?>> definitions, @NonNull Collection<ParserDataOptions> options);

    <T> ScheduledFuture<ParserData<T>> scheduleData(@NonNull ParseContext context, @NonNull ParserDataDefinition<T> data);

    Map<ParserDataDefinition<?>, ScheduledFuture<ParserData<?>>> scheduleData(@NonNull ParseContext context, @NonNull Collection<? extends ParserDataDefinition<?>> data);

    <T> ScheduledFuture<ParserData<T>> scheduleData(@NonNull ParseContext context, @NonNull ParserDataDefinition<T> data, long delay, @NonNull TimeUnit timeUnit);

    Map<ParserDataDefinition<?>, ScheduledFuture<ParserData<?>>> scheduleData(@NonNull ParseContext context, @NonNull Collection<? extends ParserDataDefinition<?>> data, long delay, @NonNull TimeUnit timeUnit);

    ScheduledFuture<Collection<? extends ParserData<?>>> scheduleTask(@NonNull ParseContext context, @NonNull ParserTaskProvider data);

    Map<ParserTaskProvider, ScheduledFuture<Collection<? extends ParserData<?>>>> scheduleTask(@NonNull ParseContext context, @NonNull Collection<? extends ParserTaskProvider> data);

    ScheduledFuture<Collection<? extends ParserData<?>>> scheduleTask(@NonNull ParseContext context, @NonNull ParserTaskProvider data, long delay, @NonNull TimeUnit timeUnit);

    Map<ParserTaskProvider, ScheduledFuture<Collection<? extends ParserData<?>>>> scheduleTask(@NonNull ParseContext context, @NonNull Collection<? extends ParserTaskProvider> data, long delay, @NonNull TimeUnit timeUnit);

    void reschedule(@NonNull VersionedDocument document, @NonNull Class<? extends ParserTaskScheduler> scheduler);

    void reschedule(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, @NonNull Class<? extends ParserTaskScheduler> scheduler);

    void reschedule(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, long delay, @NonNull TimeUnit timeUnit, @NonNull Class<? extends ParserTaskScheduler> scheduler);

    @NonNull <T> ScheduledFuture<T> scheduleHighPriority(@NonNull Callable<T> callable);

    @NonNull <T> ScheduledFuture<T> scheduleLowPriority(@NonNull Callable<T> callable);

    void addDataListener(@NonNull ParserDataListener<Object> listener);

    void removeDataListener(@NonNull ParserDataListener<Object> listener);

    <T> void addDataListener(@NonNull ParserDataDefinition<T> definition, @NonNull ParserDataListener<? super T> listener);

    <T> void removeDataListener(@NonNull ParserDataDefinition<T> definition, @NonNull ParserDataListener<? super T> listener);
}
