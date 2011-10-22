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
import org.netbeans.lib.editor.util.ListenerList;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;

public abstract class GrammarParser extends Parser {
    // Working around weird behavior in the Parsing API
    protected static final boolean SINGLE_RESULT = true;

    private final ListenerList<ChangeListener> listeners = new ListenerList<ChangeListener>();

    protected final Map<Task, GrammarParserResult> results = new HashMap<Task, GrammarParserResult>();

    @Override
    @SuppressWarnings("unchecked")
    public abstract void parse(Snapshot snapshot, Task task, SourceModificationEvent sme) throws ParseException;

    public abstract GrammarParserResult createResult(Task task);

    @Override
    public Result getResult(Task task) throws ParseException {
        synchronized (this) {
            if (SINGLE_RESULT) {
                return createResult(task);
            } else {
                return results.get(task);
            }
        }
    }

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

        public GrammarFileResult(FileObject fileObject, CommonToken[] tokens) {
            this.fileObject = fileObject;
            this.tokens = tokens;
        }

        public FileObject getFileObject() {
            return fileObject;
        }

        public CommonToken[] getTokens() {
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
            if (!SINGLE_RESULT) {
                results.remove(task);
            }
        }

    }
}
