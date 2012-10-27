/*
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.netbeans.editor.whitespace;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.spi.editor.highlighting.HighlightsLayer;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.HighlightsSequence;
import org.netbeans.spi.editor.highlighting.ZOrder;
import org.netbeans.spi.editor.highlighting.support.AbstractHighlightsContainer;
import org.openide.util.Lookup;
import org.openide.util.Parameters;
import org.openide.util.WeakListeners;

/**
 *
 * @author Sam Harwell
 */
public class WhitespaceHighlighter extends AbstractHighlightsContainer {
    // -J-Dorg.tvl.netbeans.editor.whitespace.WhitespaceHighlighter.level=FINE
    private static final Logger LOGGER = Logger.getLogger(WhitespaceHighlighter.class.getName());

    private final StyledDocument document;
    private final AttributeSet attributes;

    protected WhitespaceHighlighter(@NonNull StyledDocument document) {
        Parameters.notNull("document", document);

        this.document = document;

        Lookup lookup = MimeLookup.getLookup(MimePath.EMPTY);
        FontColorSettings settings = lookup.lookup(FontColorSettings.class);
        this.attributes = getFontAndColors(settings, "whitespace");

        if (this.attributes != null) {
            this.document.addDocumentListener(WeakListeners.document(new DocumentListenerImpl(), document));
        }
    }

    protected StyledDocument getDocument() {
        return document;
    }

    protected static AttributeSet getFontAndColors(FontColorSettings settings, String category) {
        AttributeSet attributes = settings.getTokenFontColors(category);
        return attributes;
    }

    @Override
    public HighlightsSequence getHighlights(int startOffset, int endOffset) {
        if (attributes == null) {
            return HighlightsSequence.EMPTY;
        }

        return new HighlightsSequenceImpl(document, startOffset, endOffset, attributes);
    }

    @MimeRegistration(mimeType="", service=HighlightsLayerFactory.class)
    public static class LayerFactory implements HighlightsLayerFactory {

        @Override
        public HighlightsLayer[] createLayers(Context context) {
            Document document = context.getDocument();
            if (!(document instanceof StyledDocument)) {
                return new HighlightsLayer[0];
            }

            WhitespaceHighlighter highlighter = (WhitespaceHighlighter)document.getProperty(WhitespaceHighlighter.class);
            if (highlighter == null) {
                highlighter = createHighlighter(context);
                document.putProperty(WhitespaceHighlighter.class, highlighter);
            }

            return new HighlightsLayer[] { HighlightsLayer.create(WhitespaceHighlighter.class.getName(), getPosition(), true, highlighter) };
        }

        protected WhitespaceHighlighter createHighlighter(Context context) {
            return new WhitespaceHighlighter((StyledDocument)context.getDocument());
        }

        protected ZOrder getPosition() {
            return ZOrder.SYNTAX_RACK.forPosition(1000);
        }
    }

    public class DocumentListenerImpl implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            fireHighlightsChange(e.getOffset(), e.getOffset() + e.getLength());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            fireHighlightsChange(e.getOffset(), e.getOffset() + e.getLength());
        }

    }

    protected static class HighlightsSequenceImpl implements HighlightsSequence {
        private static final int BLOCK_SIZE = 1024;
        private static final AttributeSet NEWLINE_ATTRIBUTES;

        private final StyledDocument document;
        private final int startOffset;
        private final int endOffset;
        private final AttributeSet attributes;
        private final boolean includesEof;

        private int currentOffset;
        private int currentBlockOffset;
        private String currentBlock;

        private int currentWhitespaceStart;
        private int currentWhitespaceEnd;
        private boolean currentNewline;
        private boolean finished;

        static {
            MutableAttributeSet attributes = new SimpleAttributeSet();
            attributes.addAttribute(StyleConstants.Foreground, new Color(0, 0, 0, 0));
            NEWLINE_ATTRIBUTES = attributes.copyAttributes();
        }

        private HighlightsSequenceImpl(@NonNull StyledDocument document, int startOffset, int endOffset, @NonNull AttributeSet attributes) {
            Parameters.notNull("document", document);
            Parameters.notNull("attributes", attributes);

            this.document = document;
            this.startOffset = startOffset;
            int documentLength = document.getLength();
            this.endOffset = Math.min(endOffset, documentLength);
            this.includesEof = endOffset >= documentLength;
            this.attributes = attributes;

            this.currentOffset = startOffset;
            this.currentBlockOffset = startOffset;
            this.currentBlock = "";
        }

        @Override
        public boolean moveNext() {
            if (finished) {
                return false;
            }

            int whitespaceStart = Integer.MAX_VALUE;
            boolean inWhitespace = false;
            boolean newline = false;

            int effectiveEndOffset = endOffset + (includesEof ? 1 : 0);

            searchLoop:
            while (currentOffset < effectiveEndOffset) {
                int offsetInBlock = currentOffset - currentBlockOffset;
                while (offsetInBlock >= currentBlock.length()) {
                    int previousBlockEnd = currentBlockOffset + currentBlock.length();
                    if (!nextBlock()) {
                        break searchLoop;
                    }

                    int currentBlockEnd = currentBlockOffset + currentBlock.length();
                    assert currentBlockEnd > previousBlockEnd : "Whitespace search terminated due to lack of forward progress."; // NOI18N
                    if (currentBlockEnd <= previousBlockEnd) {
                        break searchLoop;
                    }

                    offsetInBlock = currentOffset - currentBlockOffset;
                }

                char c = currentBlock.charAt(offsetInBlock);
                if (inWhitespace) {
                    if (newline && c != '\n' && c != '\r') {
                        break;
                    } else if (!newline && (!Character.isWhitespace(c) || c == '\n' || c == '\r')) {
                        break;
                    }
                } else if (Character.isWhitespace(c)) {
                    whitespaceStart = currentOffset;
                    inWhitespace = true;
                    newline = c == '\r' || c == '\n';
                }
                
                currentOffset++;
            }

            if (currentOffset > whitespaceStart) {
                currentWhitespaceStart = whitespaceStart;
                currentWhitespaceEnd = currentOffset;
                currentNewline = newline;
                return true;
            } else {
                currentWhitespaceStart = currentOffset;
                currentWhitespaceEnd = currentOffset;
                currentNewline = false;
                return false;
            }
        }

        @Override
        public int getStartOffset() {
            return currentWhitespaceStart;
        }

        @Override
        public int getEndOffset() {
            return currentWhitespaceEnd;
        }

        @Override
        public AttributeSet getAttributes() {
            return currentNewline ? NEWLINE_ATTRIBUTES : attributes;
        }

        private boolean nextBlock() {
            int effectiveEndOffset = endOffset + (includesEof ? 1 : 0);

            int blockStart = currentBlockOffset + currentBlock.length();
            int blockEnd = Math.min(effectiveEndOffset, blockStart + BLOCK_SIZE);

            if (blockEnd == blockStart) {
                currentBlock = "";
                currentBlockOffset = endOffset;
                finished = true;
                return false;
            }

            try {
                int boundedEnd = Math.min(blockEnd, endOffset);
                if (boundedEnd == blockStart) {
                    currentBlock = "";
                } else {
                    currentBlock = document.getText(blockStart, boundedEnd - blockStart);
                }

                if (boundedEnd < blockEnd) {
                    assert blockEnd == boundedEnd + 1;
                    assert blockEnd == effectiveEndOffset;
                    currentBlock += "\n";
                }

                currentBlockOffset = blockStart;
                return true;
            } catch (BadLocationException ex) {
                LOGGER.log(Level.WARNING, "An exception occurred.", ex);
                finished = true;
                return false;
            }
        }
    }

}
