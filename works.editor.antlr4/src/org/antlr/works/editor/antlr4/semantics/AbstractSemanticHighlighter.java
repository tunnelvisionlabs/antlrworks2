/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.semantics;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
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
import org.antlr.v4.runtime.misc.Tuple;
import org.antlr.v4.runtime.misc.Tuple2;
import org.netbeans.api.annotations.common.NonNull;
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
import org.openide.util.WeakSet;

/**
 *
 * @author Sam Harwell
 * @param <SemanticData>
 */
public abstract class AbstractSemanticHighlighter<SemanticData> extends AbstractHighlightsContainer {
    // -J-Dorg.antlr.works.editor.antlr4.semantics.AbstractSemanticHighlighter.level=FINE
    private static final Logger LOGGER = Logger.getLogger(AbstractSemanticHighlighter.class.getName());

    private final StyledDocument document;
    private final ParserDataDefinition<? extends SemanticData> semanticDataDefinition;
    private final ParserTaskManager taskManager;
    private final VersionedDocument versionedDocument;
    private final DataListener dataListener;
    private final OffsetsBag container;

    private final Set<JTextComponent> components = new WeakSet<>();

    protected AbstractSemanticHighlighter(@NonNull StyledDocument document, @NonNull ParserDataDefinition<? extends SemanticData> semanticDataDefinition) {
        Parameters.notNull("document", document);
        Parameters.notNull("semanticDataDefinition", semanticDataDefinition);

        this.document = document;
        this.semanticDataDefinition = semanticDataDefinition;
        this.taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
        this.versionedDocument = VersionedDocumentUtilities.getVersionedDocument(document);
        this.dataListener = new DataListener();
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
        if (attributes == null) {
            LOGGER.log(Level.WARNING, "No font attributes found for category {0}.", category);
            return SimpleAttributeSet.EMPTY;
        }

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
            taskManager.addDataListener(semanticDataDefinition, new WeakDataListener<>(semanticDataDefinition, dataListener));
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

    protected void addHighlights(List<Tuple2<OffsetRegion, AttributeSet>> intermediateContainer, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, Collection<Token> tokens, AttributeSet attributes) {
        for (Token token : tokens) {
            int startIndex = token.getStartIndex();
            int stopIndex = token.getStopIndex();
            if (startIndex < 0 || stopIndex < 0 || (startIndex > stopIndex + 1)) {
                continue;
            }

            TrackingPositionRegion trackingRegion = sourceSnapshot.createTrackingRegion(OffsetRegion.fromBounds(startIndex, stopIndex + 1), Bias.Forward);
            SnapshotPositionRegion region = trackingRegion.getRegion(currentSnapshot);
            intermediateContainer.add(Tuple.create(region.getRegion(), attributes));
        }
    }

    protected void fillHighlights(OffsetsBag targetContainer, List<Tuple2<OffsetRegion, AttributeSet>> highlights) {
        Collections.sort(highlights, new Comparator<Tuple2<OffsetRegion, AttributeSet>>() {

            @Override
            public int compare(Tuple2<OffsetRegion, AttributeSet> o1, Tuple2<OffsetRegion, AttributeSet> o2) {
                int diff = o1.getItem1().getStart() - o2.getItem1().getStart();
                if (diff != 0) {
                    return diff;
                }

                return o1.getItem1().getEnd() - o2.getItem1().getEnd();
            }

        });

        for (Tuple2<OffsetRegion, AttributeSet> highlight : highlights) {
            targetContainer.addHighlight(highlight.getItem1().getStart(), highlight.getItem1().getEnd(), highlight.getItem2());
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
            if (!versionedDocument.equals(snapshot.getVersionedDocument())) {
                return;
            }

            Callable<Void> task = createAnalyzerTask(parserData);
            if (task != null) {
                taskManager.scheduleHighPriority(task);
            }
        }

    }

    protected static class WeakDataListener<T> implements ParserDataListener<T> {
        @NonNull
        private final WeakReference<ParserDataListener<T>> _listener;
        @NonNull
        private final ParserDataDefinition<? extends T> dataDefinition;

        public WeakDataListener(@NonNull ParserDataDefinition<? extends T> dataDefinition, @NonNull ParserDataListener<T> listener) {
            this._listener = new WeakReference<>(listener);
            this.dataDefinition = dataDefinition;
        }

        @Override
        public void dataChanged(ParserDataEvent<? extends T> event) {
            ParserDataListener<T> listener = getListener();
            if (listener == null) {
                ParserTaskManager taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
                taskManager.removeDataListener(dataDefinition, this);
                return;
            }

            listener.dataChanged(event);
        }

        protected ParserDataListener<T> getListener() {
            return _listener.get();
        }
    }
}
