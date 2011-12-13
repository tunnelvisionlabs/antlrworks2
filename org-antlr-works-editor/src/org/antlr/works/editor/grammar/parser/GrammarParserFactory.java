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

import java.util.Collection;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.lib.editor.util.ListenerList;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.ParserFactory;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;

@MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserFactory.class)
public class GrammarParserFactory extends ParserFactory {

    @Override
    public Parser createParser(Collection<Snapshot> snapshots) {
        return new ParserSelector();
    }

    private static class ParserSelector extends Parser {

        private final ListenerList<ChangeListener> listeners = new ListenerList<ChangeListener>();
        private final GrammarParserV3 v3 = new GrammarParserV3();
        private final GrammarParserV4 v4 = new GrammarParserV4();
        private boolean compatibility;

        public ParserSelector() {
            Listener listener = new Listener();
            v3.addChangeListener(listener);
            v4.addChangeListener(listener);
        }

        @Override
        public void parse(Snapshot snapshot, Task task, SourceModificationEvent sme) throws ParseException {
            compatibility = GrammarEditorKit.isLegacyMode(snapshot);
            if (compatibility) {
                v3.parse(snapshot, task, sme);
            } else {
                v4.parse(snapshot, task, sme);
            }
        }

        @Override
        public Result getResult(Task task) throws ParseException {
            if (compatibility) {
                return v3.getResult(task);
            } else {
                return v4.getResult(task);
            }
        }

        @Override
        public void addChangeListener(ChangeListener changeListener) {
            listeners.add(changeListener);
        }

        @Override
        public void removeChangeListener(ChangeListener changeListener) {
            listeners.remove(changeListener);
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

        private class Listener implements ChangeListener {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof GrammarParserV3) {
                    if (compatibility) {
                        fireStateChanged();
                    }
                } else {
                    if (!compatibility) {
                        fireStateChanged();
                    }
                }
            }
        }
    }
}
