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
package org.antlr.works.editor.antlr4.semantics;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashSet;
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
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserDataEvent;
import org.antlr.netbeans.parsing.spi.ParserDataListener;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.Token;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.api.editor.settings.FontColorSettings;
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
public abstract class AbstractSemanticHighlighter<SemanticData> extends AbstractHighlightsContainer {
    private final StyledDocument document;
    private final ParserDataDefinition<SemanticData> semanticDataDefinition;
    private final ParserTaskManager taskManager;
    private final VersionedDocument versionedDocument;
    private final DataListener dataListener;
    private final EditorRegistryListener editorRegistryListener;
    private final OffsetsBag container;

    private final Set<JTextComponent> components = new HashSet<JTextComponent>();

    protected AbstractSemanticHighlighter(@NonNull StyledDocument document, @NonNull ParserDataDefinition<SemanticData> semanticDataDefinition) {
        Parameters.notNull("document", document);
        Parameters.notNull("semanticDataDefinition", semanticDataDefinition);

        this.document = document;
        this.semanticDataDefinition = semanticDataDefinition;
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
    }

    protected StyledDocument getDocument() {
        return document;
    }

    protected ParserTaskManager getTaskManager() {
        return taskManager;
    }

    protected VersionedDocument getVersionedDocument() {
        return versionedDocument;
    }

    protected OffsetsBag getContainer() {
        return container;
    }

    protected static AttributeSet getFontAndColors(FontColorSettings settings, String category) {
        AttributeSet attributes = settings.getTokenFontColors(category);
        return attributes;
    }

    @Override
    public HighlightsSequence getHighlights(int startOffset, int endOffset) {
        return container.getHighlights(startOffset, endOffset);
    }

    protected void initialize() {
    }

    protected void addComponent(JTextComponent component) {
        components.add(component);
        if (components.size() == 1) {
            taskManager.addDataListener(semanticDataDefinition, dataListener);
            EditorRegistry.addPropertyChangeListener(editorRegistryListener);
        }
    }

    protected void removeComponent(JTextComponent component) {
        if (components.remove(component) && components.isEmpty()) {
            taskManager.removeDataListener(semanticDataDefinition, dataListener);
            EditorRegistry.removePropertyChangeListener(editorRegistryListener);
        }
    }

    public static abstract class AbstractLayerFactory implements HighlightsLayerFactory {
        private final Class<? extends AbstractSemanticHighlighter<?>> highlighterClass;

        public AbstractLayerFactory(@NonNull Class<? extends AbstractSemanticHighlighter<?>> highlighterClass) {
            Parameters.notNull("highlighterClass", highlighterClass);

            this.highlighterClass = highlighterClass;
        }

        @Override
        public HighlightsLayer[] createLayers(Context context) {
            Document document = context.getDocument();
            if (!(document instanceof StyledDocument)) {
                return new HighlightsLayer[0];
            }

            AbstractSemanticHighlighter<?> highlighter = (AbstractSemanticHighlighter<?>)document.getProperty(highlighterClass);
            if (highlighter == null) {
                highlighter = createHighlighter(context);
                highlighter.initialize();
                document.putProperty(highlighterClass, highlighter);
            }

            highlighter.addComponent(context.getComponent());
            return new HighlightsLayer[] { HighlightsLayer.create(highlighterClass.getName(), getPosition(), true, highlighter) };
        }

        protected abstract AbstractSemanticHighlighter<?> createHighlighter(Context context);

        protected ZOrder getPosition() {
            return ZOrder.SYNTAX_RACK.forPosition(3);
        }
    }

    protected abstract Callable<Void> createAnalyzerTask(final ParserData<? extends SemanticData> parserData);

    protected void addHighlights(OffsetsBag container, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, Collection<Token> tokens, AttributeSet attributes) {
        for (Token token : tokens) {
            TrackingPositionRegion trackingRegion = sourceSnapshot.createTrackingRegion(OffsetRegion.fromBounds(token.getStartIndex(), token.getStopIndex() + 1), Bias.Forward);
            SnapshotPositionRegion region = trackingRegion.getRegion(currentSnapshot);
            container.addHighlight(region.getStart().getOffset(), region.getEnd().getOffset(), attributes);
        }
    }

    protected class DataListener implements ParserDataListener<SemanticData> {

        @Override
        public void dataChanged(ParserDataEvent<? extends SemanticData> event) {
            final ParserData<? extends SemanticData> parserData = event.getData();
            if (parserData == null) {
                return;
            }

            final DocumentSnapshot snapshot = parserData.getSnapshot();
            if (snapshot == null || !versionedDocument.equals(snapshot.getVersionedDocument())) {
                return;
            }

            taskManager.scheduleHighPriority(createAnalyzerTask(parserData));
        }

    }

    protected class EditorRegistryListener implements PropertyChangeListener {

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
