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
package org.antlr.works.editor.st4.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RuleReturnScope;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;
import org.netbeans.lib.editor.util.ListenerList;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
import org.openide.util.Exceptions;
import org.stringtemplate.v4.compiler.CompiledST;
import org.stringtemplate.v4.compiler.GroupLexer;

public class TemplateParser extends Parser {
    // Working around weird behavior in the Parsing API
    private static final boolean SINGLE_RESULT = true;

    private final ListenerList<ChangeListener> listeners = new ListenerList<ChangeListener>();

    private final Map<Task, TemplateParserResult> results = new HashMap<Task, TemplateParserResult>();

    private GroupParserWrapper lastParser;
    private Snapshot lastSnapshot;
    private TemplateGroupRuleReturnScope lastResult;

    @Override
    public void parse(Snapshot snapshot, Task task, SourceModificationEvent sme) throws ParseException {
        ANTLRStringStream input = new ANTLRStringStream(snapshot.getText().toString());
        input.name = snapshot.getSource().getFileObject().getNameExt();

        GroupLexer lexer = new GroupLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GroupParserWrapper parser = new GroupParserWrapper(tokens);
        TemplateGroupWrapper group = new TemplateGroupWrapper('<', '>');
        try {
            parser.group(group, "/");
            TemplateGroupRuleReturnScope returnScope = BuildAstForGroupTemplates(group);
            synchronized (this) {
                if (SINGLE_RESULT) {
                    lastParser = parser;
                    lastSnapshot = snapshot;
                    lastResult = returnScope;
                } else {
                    results.put(task, new TemplateParserResult(parser, returnScope, snapshot, task));
                }
            }
        } catch (RecognitionException ex) {
            Exceptions.printStackTrace(ex);
        }

        /*ANTLRErrorProvidingLexer lexer = new ANTLRErrorProvidingLexer(input);
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
            synchronized (this) {
                if (SINGLE_RESULT) {
                    lastParser = parser;
                    lastSnapshot = snapshot;
                    lastResult = result;
                } else {
                    results.put(task, new GrammarParserResult(parser, result, snapshot, task));
                }
            }
        } catch (Exception ex) {
            throw new ParseException("An unexpected error occurred.", ex);
        }*/
    }

    @Override
    public Result getResult(Task task) throws ParseException {
        synchronized (this) {
            if (SINGLE_RESULT) {
                return new TemplateParserResult(lastParser, lastResult, lastSnapshot, task);
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

    private TemplateGroupRuleReturnScope BuildAstForGroupTemplates(TemplateGroupWrapper group) {
        TreeAdaptor adaptor = new CommonTreeAdaptor();
        Object tree = adaptor.nil();
        for (CompiledST template : group.getCompiledTemplates()) {
            adaptor.addChild(tree, template.ast);
        }

        return new TemplateGroupRuleReturnScope(group, (CommonTree)tree);
    }

    public class TemplateGroupRuleReturnScope extends RuleReturnScope {
        private final TemplateGroupWrapper group;
        private final CommonTree tree;

        public TemplateGroupRuleReturnScope(TemplateGroupWrapper group, CommonTree tree) {
            this.group = group;
            this.tree = tree;
        }

        public TemplateGroupWrapper getGroup() {
            return group;
        }

        @Override
        public CommonTree getTree() {
            return tree;
        }

        @Override
        public Object getStart() {
            return null;
        }

        @Override
        public Object getStop() {
            return null;
        }
    }

    public class TemplateParserResult extends Result {

        private final GroupParserWrapper parser;
        private final TemplateGroupRuleReturnScope result;
        private final Task task;

        public TemplateParserResult(GroupParserWrapper parser, TemplateGroupRuleReturnScope result, Snapshot snapshot, Task task) {
            super(snapshot);
            this.parser = parser;
            this.result = result;
            this.task = task;
        }

        public GroupParserWrapper getParser() {
            return parser;
        }
        
        public TemplateGroupRuleReturnScope getResult() {
            return result;
        }

        public Task getTask() {
            return task;
        }

        @Override
        protected void invalidate() {
            if (!SINGLE_RESULT) {
                results.remove(task);
            }
        }

    }
}
