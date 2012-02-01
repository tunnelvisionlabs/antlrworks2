/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.highlighter4;

import javax.swing.text.StyledDocument;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.highlighting.HighlightsLayer;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.HighlightsSequence;
import org.netbeans.spi.editor.highlighting.ZOrder;
import org.netbeans.spi.editor.highlighting.support.AbstractHighlightsContainer;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class SemanticReferencesHighlighter extends AbstractHighlightsContainer {
    private final StyledDocument document;

    private SemanticReferencesHighlighter(@NonNull StyledDocument document) {
        Parameters.notNull("document", document);
        this.document = document;
    }

    @Override
    public HighlightsSequence getHighlights(int startOffset, int endOffset) {
        return HighlightsSequence.EMPTY;
    }

    private void initialize() {
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=HighlightsLayerFactory.class)
    public static class LayerFactory implements HighlightsLayerFactory {

        @Override
        public HighlightsLayer[] createLayers(Context context) {
            if (!(context.getDocument() instanceof StyledDocument)) {
                return new HighlightsLayer[0];
            }

            StyledDocument document = (StyledDocument)context.getDocument();
            SemanticReferencesHighlighter highlighter = new SemanticReferencesHighlighter(document);
            highlighter.initialize();
            return new HighlightsLayer[] { HighlightsLayer.create(SemanticReferencesHighlighter.class.getName(), ZOrder.SYNTAX_RACK.forPosition(5), true, highlighter) };
        }

    }
}
