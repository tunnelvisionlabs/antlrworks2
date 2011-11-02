/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Terence Parr
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
package org.antlr.works.editor.grammar.syndiag;

import java.util.List;
import java.util.concurrent.CancellationException;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.TextBuffer;
import org.antlr.netbeans.editor.text.TextBufferUtilities;
import org.antlr.netbeans.editor.text.TextSnapshot;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParserAnchorListener;
import org.antlr.works.editor.grammar.experimental.TextSnapshotCharStream;
import org.antlr.works.editor.grammar.experimental.UpdateAnchorsTask;
import org.antlr.works.editor.grammar.parser.GrammarParser.GrammarParserResult;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.parsing.spi.SchedulerEvent;

/**
 *
 * @author sam
 */
public class UpdateSyntaxDiagramTaskV4 extends UpdateSyntaxDiagramTask {

    private boolean cancelled;

    @Override
    public void run(GrammarParserResult result, SchedulerEvent event) {
        Document document = result.getSnapshot().getSource().getDocument(false);
        if (!(document instanceof BaseDocument)) {
            return;
        }

        JTextComponent component = EditorRegistry.lastFocusedComponent();
        if (component == null || !component.getDocument().equals(document)) {
            return;
        }

        int caretOffset = component.getCaretPosition();
        TextBuffer textBuffer = TextBufferUtilities.getTextBufferForDocument((BaseDocument)document);
        final TextSnapshot snapshot = textBuffer.getCurrentSnapshot();

        Object anchorsObject = document.getProperty(UpdateAnchorsTask.class);
        @SuppressWarnings("unchecked")
        List<GrammarParserAnchorListener.Anchor> anchors = (List<GrammarParserAnchorListener.Anchor>)anchorsObject;
        if (anchors != null) {
            GrammarParserAnchorListener.Anchor enclosing = null;
            int grammarType = -1;

            /*
             * parse the current rule
             */
            for (GrammarParserAnchorListener.Anchor anchor : anchors) {
                if (anchor instanceof GrammarParserAnchorListener.GrammarTypeAnchor) {
                    grammarType = ((GrammarParserAnchorListener.GrammarTypeAnchor)anchor).getGrammarType();
                    continue;
                }

                if (anchor.getSpan().getStartPoint(snapshot).getPosition() <= caretOffset && anchor.getSpan().getEndPoint(snapshot).getPosition() > caretOffset) {
                    enclosing = anchor;
                } else if (anchor.getSpan().getStartPoint(snapshot).getPosition() > caretOffset) {
                    break;
                }
            }

            if (enclosing != null) {
                CharStream input = new TextSnapshotCharStream(snapshot);
                input.seek(enclosing.getSpan().getStartPoint(snapshot).getPosition());
                GrammarLexer lexer = new GrammarLexer(input);
                CommonTokenStream tokens = new TaskTokenStream(lexer);
                GrammarParser parser = new GrammarParser(tokens);
                parser.setBuildParseTree(true);

                final int finalGrammarType = grammarType;
                final GrammarParser.ruleContext ruleContext = parser.rule();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        SyntaxDiagramTopComponent syntaxDiagram = SyntaxDiagramTopComponent.getInstance();
                        if (syntaxDiagram != null) {
                            syntaxDiagram.setRuleContext(snapshot, finalGrammarType, ruleContext);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void cancel() {
        super.cancel();
    }

    private class TaskTokenStream extends CommonTokenStream {

        public TaskTokenStream(TokenSource tokenSource) {
            super(tokenSource);
        }

        @Override
        public int LA(int i) {
            if (cancelled) {
                throw new CancellationException();
            }

            return super.LA(i);
        }

    }
}
