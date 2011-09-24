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
import org.antlr.grammar.v3.ANTLRParser.grammar__return;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.tool.ErrorManager;
import org.antlr.tool.Grammar;
import org.netbeans.lib.editor.util.ListenerList;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;

public class GrammarParser extends Parser {
    private final ListenerList<ChangeListener> listeners = new ListenerList<ChangeListener>();
    private final Map<Task, GrammarParserResult> results = new HashMap<Task, GrammarParserResult>();

    @Override
    public void parse(Snapshot snapshot, Task task, SourceModificationEvent sme) throws ParseException {
        ANTLRStringStream input = new ANTLRStringStream(snapshot.getText().toString());
        ANTLRErrorProvidingLexer lexer = new ANTLRErrorProvidingLexer(input);
        ANTLRParserTokenStream tokenStream = new ANTLRParserTokenStream(lexer);
        ANTLRErrorProvidingParser parser = new ANTLRErrorProvidingParser(tokenStream);

        lexer.setParser(parser);
        tokenStream.setParser(parser);
        parser.setTreeAdaptor(new ANTLRErrorProvidingParser.grammar_Adaptor(parser));

        try {
            ErrorManager.setErrorListener(new ANTLRErrorProvidingParser.ErrorListener());
            Grammar g = new Grammar();
            g.setFileName(""); // work around a bug in Grammar.setName that results in a NPE
            grammar__return result = parser.grammar_(g);
            results.put(task, new GrammarParserResult(parser, snapshot, task));
        } catch (Exception ex) {
            throw new ParseException("An unexpected error occurred.", ex);
        }
    }

    @Override
    public Result getResult(Task task) throws ParseException {
        return results.get(task);
    }
    
    @Override
    public void cancel(CancelReason reason, SourceModificationEvent event) {
    }

    @Override
    public void addChangeListener(ChangeListener listener) {
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeChangeListener(ChangeListener listener) {
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

    public class GrammarParserResult extends Result {

        private final ANTLRErrorProvidingParser parser;
        private final Task task;

        public GrammarParserResult(ANTLRErrorProvidingParser parser, Snapshot snapshot, Task task) {
            super(snapshot);
            this.parser = parser;
            this.task = task;
        }

        public ANTLRErrorProvidingParser getParser() {
            return parser;
        }

        @Override
        protected void invalidate() {
            results.remove(task);
        }

    }
}
