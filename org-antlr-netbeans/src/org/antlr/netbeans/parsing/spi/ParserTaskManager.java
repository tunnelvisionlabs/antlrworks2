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

import java.util.Collection;
import java.util.EnumSet;
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

    @NonNull Future<ParserData<?>>[] getData(@NonNull DocumentSnapshot snapshot, @NonNull Collection<ParserDataDefinition<?>> definitions);

    @CheckForNull <T> Future<ParserData<T>> getData(@NonNull DocumentSnapshot snapshot, @NonNull ParserDataDefinition<T> definition, @NonNull EnumSet<ParserDataOptions> options);

    @NonNull Future<ParserData<?>>[] getData(@NonNull DocumentSnapshot snapshot, @NonNull Collection<ParserDataDefinition<?>> definitions, @NonNull EnumSet<ParserDataOptions> options);

    @CheckForNull <T> Future<ParserData<T>> getData(@NonNull DocumentSnapshot snapshot, @NullAllowed JTextComponent component, @NonNull ParserDataDefinition<T> definition);

    @NonNull Future<ParserData<?>>[] getData(@NonNull DocumentSnapshot snapshot, @NullAllowed JTextComponent component, @NonNull Collection<ParserDataDefinition<?>> definitions);

    @CheckForNull <T> Future<ParserData<T>> getData(@NonNull DocumentSnapshot snapshot, @NullAllowed JTextComponent component, @NonNull ParserDataDefinition<T> definition, @NonNull EnumSet<ParserDataOptions> options);

    @NonNull Future<ParserData<?>>[] getData(@NonNull DocumentSnapshot snapshot, @NullAllowed JTextComponent component, @NonNull Collection<ParserDataDefinition<?>> definitions, @NonNull EnumSet<ParserDataOptions> options);

    <T> ScheduledFuture<ParserData<T>> scheduleData(@NonNull VersionedDocument document, @NonNull ParserDataDefinition<T> data);

    Map<ParserDataDefinition<?>, ScheduledFuture<ParserData<?>>> scheduleData(@NonNull VersionedDocument document, @NonNull Collection<ParserDataDefinition<?>> data);

    <T> ScheduledFuture<ParserData<T>> scheduleData(@NonNull VersionedDocument document, @NonNull ParserDataDefinition<T> data, long delay, @NonNull TimeUnit timeUnit);

    Map<ParserDataDefinition<?>, ScheduledFuture<ParserData<?>>> scheduleData(@NonNull VersionedDocument document, @NonNull Collection<ParserDataDefinition<?>> data, long delay, @NonNull TimeUnit timeUnit);

    <T> ScheduledFuture<ParserData<T>> scheduleData(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, @NonNull ParserDataDefinition<T> data);

    Map<ParserDataDefinition<?>, ScheduledFuture<ParserData<?>>> scheduleData(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, @NonNull Collection<ParserDataDefinition<?>> data);

    <T> ScheduledFuture<ParserData<T>> scheduleData(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, @NonNull ParserDataDefinition<T> data, long delay, @NonNull TimeUnit timeUnit);

    Map<ParserDataDefinition<?>, ScheduledFuture<ParserData<?>>> scheduleData(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, @NonNull Collection<ParserDataDefinition<?>> data, long delay, @NonNull TimeUnit timeUnit);

    ScheduledFuture<Collection<ParserData<?>>> schedule(@NonNull VersionedDocument document, @NonNull ParserTaskProvider data);

    Map<ParserTaskProvider, ScheduledFuture<Collection<ParserData<?>>>> schedule(@NonNull VersionedDocument document, @NonNull Collection<ParserTaskProvider> data);

    ScheduledFuture<Collection<ParserData<?>>> schedule(@NonNull VersionedDocument document, @NonNull ParserTaskProvider data, long delay, @NonNull TimeUnit timeUnit);

    Map<ParserTaskProvider, ScheduledFuture<Collection<ParserData<?>>>> schedule(@NonNull VersionedDocument document, @NonNull Collection<ParserTaskProvider> data, long delay, @NonNull TimeUnit timeUnit);

    ScheduledFuture<Collection<ParserData<?>>> schedule(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, @NonNull ParserTaskProvider data);

    Map<ParserTaskProvider, ScheduledFuture<Collection<ParserData<?>>>> schedule(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, @NonNull Collection<ParserTaskProvider> data);

    ScheduledFuture<Collection<ParserData<?>>> schedule(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, @NonNull ParserTaskProvider data, long delay, @NonNull TimeUnit timeUnit);

    Map<ParserTaskProvider, ScheduledFuture<Collection<ParserData<?>>>> schedule(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, @NonNull Collection<ParserTaskProvider> data, long delay, @NonNull TimeUnit timeUnit);

    void reschedule(@NonNull VersionedDocument document, @NonNull Class<? extends ParserTaskScheduler> scheduler);

    void reschedule(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, @NonNull Class<? extends ParserTaskScheduler> scheduler);

    void reschedule(@NonNull VersionedDocument document, @NullAllowed JTextComponent component, long delay, @NonNull TimeUnit timeUnit, @NonNull Class<? extends ParserTaskScheduler> scheduler);

    @NonNull <T> ScheduledFuture<T> scheduleHighPriority(@NonNull Callable<T> callable);

    @NonNull <T> ScheduledFuture<T> scheduleLowPriority(@NonNull Callable<T> callable);

    void addDataListener(@NonNull ParserDataListener<Object> listener);

    void removeDataListener(@NonNull ParserDataListener<Object> listener);

    <T> void addDataListener(@NonNull ParserDataDefinition<T> definition, @NonNull ParserDataListener<T> listener);

    <T> void removeDataListener(@NonNull ParserDataDefinition<T> definition, @NonNull ParserDataListener<T> listener);
}
