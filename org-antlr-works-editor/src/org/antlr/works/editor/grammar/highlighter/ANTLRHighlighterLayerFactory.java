/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.highlighter;

import javax.swing.text.StyledDocument;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.highlighting.HighlightsLayer;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.ZOrder;

//@MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=HighlightsLayerFactory.class)
public class ANTLRHighlighterLayerFactory implements HighlightsLayerFactory {
    @Override
    public HighlightsLayer[] createLayers(Context context) {
        if (!(context.getDocument() instanceof StyledDocument))
            return new HighlightsLayer[0];

        StyledDocument document = (StyledDocument)context.getDocument();
        ANTLRHighlighter highlighter = new ANTLRHighlighter(document);
        highlighter.initialize();
        return new HighlightsLayer[] { HighlightsLayer.create(ANTLRHighlighter.class.getName(), ZOrder.SYNTAX_RACK, true, highlighter) };
    }
}
