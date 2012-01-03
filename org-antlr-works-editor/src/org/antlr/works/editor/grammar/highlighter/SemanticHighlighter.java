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
package org.antlr.works.editor.grammar.highlighter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion.Bias;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataEvent;
import org.antlr.netbeans.parsing.spi.ParserDataListener;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.experimental.BlankGrammarParserListener;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParser.argActionParameterContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.locals_Context;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleReturnsContext;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.highlighting.HighlightsChangeEvent;
import org.netbeans.spi.editor.highlighting.HighlightsChangeListener;
import org.netbeans.spi.editor.highlighting.HighlightsLayer;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.HighlightsSequence;
import org.netbeans.spi.editor.highlighting.ZOrder;
import org.netbeans.spi.editor.highlighting.support.AbstractHighlightsContainer;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;
import org.openide.util.Lookup;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class SemanticHighlighter extends AbstractHighlightsContainer {
    private final StyledDocument document;
    private final ParserTaskManager taskManager;
    private final VersionedDocument versionedDocument;
    private final DataListener dataListener;
    private final EditorRegistryListener editorRegistryListener;
    private final OffsetsBag container;

    private final AttributeSet parameterDeclarationAttributes;
    private final AttributeSet returnValueDeclarationAttributes;
    private final AttributeSet localDeclarationAttributes;

    private final Set<JTextComponent> components = new HashSet<JTextComponent>();

    private SemanticHighlighter(@NonNull StyledDocument document) {
        Parameters.notNull("document", document);
        this.document = document;
        this.taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
        this.versionedDocument = VersionedDocumentUtilities.getVersionedDocument(document);
        this.dataListener = new DataListener();
        this.editorRegistryListener = new EditorRegistryListener();
        this.container = new OffsetsBag(document, true);
        this.container.addHighlightsChangeListener(new HighlightsChangeListener() {
            @Override
            public void highlightChanged(HighlightsChangeEvent event) {
                fireHighlightsChange(event.getStartOffset(), event.getEndOffset());
            }
        });

        Lookup lookup = MimeLookup.getLookup(MimePath.parse(GrammarEditorKit.GRAMMAR_MIME_TYPE));
        FontColorSettings settings = lookup.lookup(FontColorSettings.class);
        this.parameterDeclarationAttributes = getFontAndColors(settings, "definition");
        this.returnValueDeclarationAttributes = getFontAndColors(settings, "definition");
        this.localDeclarationAttributes = getFontAndColors(settings, "definition");
    }

    private static AttributeSet getFontAndColors(FontColorSettings settings, String category) {
        AttributeSet attributes = settings.getTokenFontColors(category);
        return attributes;
    }

    @Override
    public HighlightsSequence getHighlights(int startOffset, int endOffset) {
        return container.getHighlights(startOffset, endOffset);
    }

    private void initialize() {
    }

    private void addComponent(JTextComponent component) {
        components.add(component);
        if (components.size() == 1) {
            taskManager.addDataListener(GrammarParserDataDefinitions.REFERENCE_PARSE_TREE, dataListener);
            EditorRegistry.addPropertyChangeListener(editorRegistryListener);
        }
    }

    private void removeComponent(JTextComponent component) {
        if (components.remove(component) && components.isEmpty()) {
            taskManager.removeDataListener(GrammarParserDataDefinitions.REFERENCE_PARSE_TREE, dataListener);
            EditorRegistry.removePropertyChangeListener(editorRegistryListener);
        }
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=HighlightsLayerFactory.class)
    public static class LayerFactory implements HighlightsLayerFactory {

        @Override
        public HighlightsLayer[] createLayers(Context context) {
            Document document = context.getDocument();
            if (!(document instanceof StyledDocument)) {
                return new HighlightsLayer[0];
            }

            SemanticHighlighter highlighter = (SemanticHighlighter)document.getProperty(SemanticHighlighter.class);
            if (highlighter == null) {
                highlighter = new SemanticHighlighter((StyledDocument)document);
                highlighter.initialize();
                document.putProperty(SemanticHighlighter.class, highlighter);
            }

            highlighter.addComponent(context.getComponent());
            return new HighlightsLayer[] { HighlightsLayer.create(SemanticHighlighter.class.getName(), ZOrder.SYNTAX_RACK.forPosition(3), true, highlighter) };
        }

    }

    private static class SemanticAnalyzerListener extends BlankGrammarParserListener {
        private final Deque<Integer> memberContext = new ArrayDeque<Integer>();

        private final List<Token> parameterDeclarations = new ArrayList<Token>();
        private final List<Token> returnValueDeclarations = new ArrayList<Token>();
        private final List<Token> localsDeclarations = new ArrayList<Token>();

        public List<Token> getParameterDeclarations() {
            return parameterDeclarations;
        }

        public List<Token> getReturnValueDeclarations() {
            return returnValueDeclarations;
        }

        public List<Token> getLocalsDeclarations() {
            return localsDeclarations;
        }

        @Override
        public void enterRule(argActionParameterContext ctx) {
            if (ctx.name != null) {
                int context = memberContext.isEmpty() ? GrammarParser.RULE_rule : memberContext.peek();
                switch (context) {
                case GrammarParser.RULE_ruleReturns:
                    returnValueDeclarations.add(ctx.name);
                    break;

                case GrammarParser.RULE_locals_:
                    localsDeclarations.add(ctx.name);
                    break;

                case GrammarParser.RULE_rule:
                default:
                    parameterDeclarations.add(ctx.name);
                    break;
                }
            }
        }

        @Override
        public void enterRule(ruleContext ctx) {
            memberContext.push(GrammarParser.RULE_rule);
        }

        @Override
        public void exitRule(ruleContext ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_rule;
        }

        @Override
        public void enterRule(ruleReturnsContext ctx) {
            memberContext.push(GrammarParser.RULE_ruleReturns);
        }

        @Override
        public void exitRule(ruleReturnsContext ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_ruleReturns;
        }

        @Override
        public void enterRule(locals_Context ctx) {
            memberContext.push(GrammarParser.RULE_locals_);
        }

        @Override
        public void exitRule(locals_Context ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_locals_;
        }
    }

    private class DataListener implements ParserDataListener<ParserRuleContext<Token>> {

        @Override
        public void dataChanged(ParserDataEvent<? extends ParserRuleContext<Token>> event) {
            final ParserData<? extends ParserRuleContext<Token>> parserData = event.getData();
            if (parserData == null) {
                return;
            }

            final DocumentSnapshot snapshot = parserData.getSnapshot();
            if (snapshot == null || !versionedDocument.equals(snapshot.getVersionedDocument())) {
                return;
            }

            taskManager.scheduleHighPriority(new Callable<Void>() {
                @Override
                public Void call() {
                    final SemanticAnalyzerListener listener = new SemanticAnalyzerListener();
                    ParseTreeWalker.DEFAULT.walk(listener, parserData.getData());
                    ((BaseDocument)document).render(new Runnable() {
                        @Override
                        public void run() {
                            container.clear();
                            DocumentSnapshot currentSnapshot = snapshot.getVersionedDocument().getCurrentSnapshot();
                            addHighlights(container, snapshot, currentSnapshot, listener.getParameterDeclarations(), parameterDeclarationAttributes);
                            addHighlights(container, snapshot, currentSnapshot, listener.getReturnValueDeclarations(), returnValueDeclarationAttributes);
                            addHighlights(container, snapshot, currentSnapshot, listener.getLocalsDeclarations(), localDeclarationAttributes);
                        }
                    });

                    return null;
                }
            });
        }

        private void addHighlights(OffsetsBag container, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, Collection<Token> tokens, AttributeSet attributes) {
            for (Token token : tokens) {
                TrackingPositionRegion trackingRegion = sourceSnapshot.createTrackingRegion(OffsetRegion.fromBounds(token.getStartIndex(), token.getStopIndex() + 1), Bias.Forward);
                SnapshotPositionRegion region = trackingRegion.getRegion(currentSnapshot);
                container.addHighlight(region.getStart().getOffset(), region.getEnd().getOffset(), attributes);
            }
        }

    }

    private class EditorRegistryListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals(EditorRegistry.COMPONENT_REMOVED_PROPERTY)) {
                Object component = evt.getOldValue();
                if (component instanceof JTextComponent) {
                    removeComponent((JTextComponent)component);
                }
            }
        }

    }
}
