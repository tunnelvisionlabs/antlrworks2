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
package org.antlr.works.editor.grammar.experimental;

import java.util.EnumSet;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.text.Document;
import org.antlr.netbeans.editor.classification.DocumentSnapshotCharStream;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.grammar.parser.GrammarParser.GrammarParserResult;
import org.antlr.works.editor.grammar.parser.GrammarParserResultTask;
import org.antlr.works.editor.grammar.parser.GrammarParserV3;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.openide.util.Exceptions;

/**
 *
 * @author Sam Harwell
 */
public class UpdateAnchorsTask extends GrammarParserResultTask {
    private static final EnumSet<Input> INPUTS = EnumSet.<Input>of(Input.ToolAST, Input.SyntaxErrors);

    private final AtomicBoolean cancel = new AtomicBoolean();

    public UpdateAnchorsTask() {
    }

    @Override
    public void run(GrammarParserResult result, SchedulerEvent event) {
        try {
            Document document = result.getSnapshot().getSource().getDocument(false);
            if (document == null) {
                return;
            }

            if (result instanceof GrammarParserV3.GrammarParserResultV3) {
                document.putProperty(UpdateAnchorsTask.class, null);
                return;
            }

            VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(document);
            DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();

            DocumentSnapshotCharStream input = new DocumentSnapshotCharStream(snapshot);
            input.setSourceName(result.getSnapshot().getSource().getFileObject().getNameExt());
            GrammarLexer lexer = new GrammarLexer(input);
            CancellableTokenStream tokenStream = new CancellableTokenStream(lexer, cancel);
            GrammarParser parser = new GrammarParser(tokenStream);
            parser.setBuildParseTree(true);
            GrammarParser.grammarSpecContext parseResult = parser.grammarSpec();

            GrammarParserAnchorListener listener = new GrammarParserAnchorListener(snapshot, cancel);
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listener, parseResult);
            document.putProperty(UpdateAnchorsTask.class, listener.getAnchors());
        } catch (CancellationException ex) {
        } catch (RuntimeException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public EnumSet<Input> getTaskInputs() {
        return INPUTS;
    }

    @Override
    public boolean allowStaleInput() {
        return false;
    }

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public Class<? extends Scheduler> getSchedulerClass() {
        return Scheduler.EDITOR_SENSITIVE_TASK_SCHEDULER;
    }

    @Override
    public void cancel() {
        cancel.set(true);
    }

    private static class CancellableTokenStream extends CommonTokenStream {
        private final AtomicBoolean cancel;

        public CancellableTokenStream(TokenSource tokenSource, AtomicBoolean cancel) {
            super(tokenSource);
            this.cancel = cancel;
        }

        @Override
        public void consume() {
            if (cancel.get()) {
                throw new CancellationException();
            }

            super.consume();
        }
    }
}
