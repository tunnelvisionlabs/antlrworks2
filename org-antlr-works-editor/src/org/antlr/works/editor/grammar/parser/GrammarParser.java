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
package org.antlr.works.editor.grammar.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.antlr.runtime.CommonToken;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.lib.editor.util.ListenerList;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;

public abstract class GrammarParser extends Parser {
    private final ListenerList<ChangeListener> listeners = new ListenerList<ChangeListener>();

    private final Map<Task, GrammarParserResult> results = new HashMap<Task, GrammarParserResult>();
    private Snapshot previousSnapshot;
    private SourceModificationEvent previousEvent;

    @Override
    public final void parse(Snapshot snapshot, Task task, SourceModificationEvent sme) throws ParseException {
        Parameters.notNull("snapshot", snapshot);

        synchronized (results) {
            previousSnapshot = snapshot;
            previousEvent = sme;
        }
    }

    @Override
    public final Result getResult(Task task) throws ParseException {
        Parameters.notNull("task", task);

        Snapshot snapshot;
        SourceModificationEvent event;
        synchronized (results) {
            snapshot = previousSnapshot;
            event = previousEvent;
        }

        if (snapshot == null) {
            throw new UnsupportedOperationException("No snapshot was specified.");
        }

        GrammarParserResult result = parseImpl(snapshot, task, event);
        synchronized (results) {
            results.put(task, result);
        }

        return result;
    }

    protected abstract GrammarParserResult parseImpl(@NonNull Snapshot snapshot, @NonNull Task task, @NullAllowed SourceModificationEvent event) throws ParseException;

    @Override
    public void cancel(CancelReason reason, SourceModificationEvent event) {
    }

    @Override
    public final void addChangeListener(ChangeListener listener) {
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    @Override
    public final void removeChangeListener(ChangeListener listener) {
        synchronized (listeners) {
            listeners.remove(listener);
        }
    }

    protected final void fireStateChanged() {
        List<ChangeListener> targets;
        synchronized (listeners) {
            targets = listeners.getListeners();
        }

        if (targets.size() > 0) {
            ChangeEvent event = new ChangeEvent(this);
            for (ChangeListener listener : targets) {
                listener.stateChanged(event);
            }
        }
    }

    public static abstract class GrammarFileResult {
        private final FileObject fileObject;
        private final CommonToken[] tokens;

        public GrammarFileResult(@NonNull FileObject fileObject, @NullAllowed CommonToken[] tokens) {
            Parameters.notNull("fileObject", fileObject);

            this.fileObject = fileObject;
            this.tokens = tokens;
        }

        public @NonNull FileObject getFileObject() {
            return fileObject;
        }

        public @CheckForNull CommonToken[] getTokens() {
            return tokens;
        }

        public abstract List<? extends SyntaxError> getSyntaxErrors();
    }

    public abstract class GrammarParserResult extends Result {

        private final Task task;
        private final GrammarFileResult result;

        public GrammarParserResult(Snapshot snapshot, Task task, GrammarFileResult result) {
            super(snapshot);
            Parameters.notNull("result", result);

            this.task = task;
            this.result = result;
        }

        public Task getTask() {
            return task;
        }

        public GrammarFileResult getResult() {
            return result;
        }

        @Override
        protected void invalidate() {
            results.remove(task);
        }

    }
}
