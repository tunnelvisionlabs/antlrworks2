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

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.antlr.Tool;
import org.antlr.grammar.v3.ANTLRParser.grammar__return;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonToken;
import org.antlr.tool.ErrorManager;
import org.netbeans.lib.editor.util.ListenerList;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;

public class GrammarParser extends Parser {
    // Working around weird behavior in the Parsing API
    private static final boolean SINGLE_RESULT = true;

    private final ListenerList<ChangeListener> listeners = new ListenerList<ChangeListener>();

    private final Map<Task, GrammarParserResult> results = new HashMap<Task, GrammarParserResult>();

    private ANTLRErrorProvidingParser lastParser;
    private Snapshot lastSnapshot;
    private GrammarFileResult lastResult;

    @Override
    @SuppressWarnings("unchecked")
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
            Tool tool = new Tool();
            tool.setLibDirectory(new File(snapshot.getSource().getFileObject().getPath()).getParent());
            GrammarWrapper g = new GrammarWrapper(tool);
            g.setFileName(""); // work around a bug in Grammar.setName that results in a NPE
            grammar__return result = parser.grammar_(g);

            List tokenList = tokenStream.getTokens();
            CommonToken[] tokens = new CommonToken[tokenList.size()];
            tokens = (CommonToken[])tokenList.toArray(tokens);

            GrammarFileResult currentResult = new GrammarFileResult(snapshot.getSource().getFileObject(), g, result, tokens);

            synchronized (this) {
                if (SINGLE_RESULT) {
                    lastParser = parser;
                    lastSnapshot = snapshot;
                    lastResult = currentResult;
                } else {
                    results.put(task, new GrammarParserResult(parser, snapshot, task, currentResult));
                }
            }
        } catch (Exception ex) {
            throw new ParseException("An unexpected error occurred.", ex);
        }
    }

    @Override
    public Result getResult(Task task) throws ParseException {
        synchronized (this) {
            if (SINGLE_RESULT) {
                return new GrammarParserResult(lastParser, lastSnapshot, task, lastResult);
            } else {
                return results.get(task);
            }
        }
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

    public static class GrammarFileResult {
        private final FileObject fileObject;
        private final GrammarWrapper grammar;
        private final grammar__return result;
        private final CommonToken[] tokens;

        public GrammarFileResult(FileObject fileObject, GrammarWrapper grammar, grammar__return result, CommonToken[] tokens) {
            this.fileObject = fileObject;
            this.grammar = grammar;
            this.result = result;
            this.tokens = tokens;
        }

        public FileObject getFileObject() {
            return fileObject;
        }

        public GrammarWrapper getGrammar() {
            return grammar;
        }

        public grammar__return getResult() {
            return result;
        }

        public CommonToken[] getTokens() {
            return tokens;
        }
    }

    public class GrammarParserResult extends Result {

        private final ANTLRErrorProvidingParser parser;
        private final Task task;
        private final GrammarFileResult result;

        public GrammarParserResult(ANTLRErrorProvidingParser parser, Snapshot snapshot, Task task, GrammarFileResult result) {
            super(snapshot);
            Parameters.notNull("result", result);

            this.parser = parser;
            this.task = task;
            this.result = result;
        }

        public ANTLRErrorProvidingParser getParser() {
            return parser;
        }

        public Task getTask() {
            return task;
        }

        public GrammarWrapper getRootGrammar() {
            return result.grammar;
        }

        public GrammarFileResult getResult() {
            return result;
        }

        public List<GrammarFileResult> getImportedGrammarResults() {
            return getRootGrammar().getImportedGrammarResults();
        }

        @Override
        protected void invalidate() {
            if (!SINGLE_RESULT) {
                results.remove(task);
            }
        }

    }
}
