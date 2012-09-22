/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.highlighting;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.openide.text.NbDocument;

/**
 *
 * @author Sam Harwell
 */
public class DocumentCharStreamV4 implements CharStream {
    // -J-Dorg.antlr.works.editor.antlr4.highlighting.DocumentCharStreamV4.level=FINE
    private static final Logger LOGGER = Logger.getLogger(DocumentCharStreamV4.class.getName());

    private boolean explicitCache;

    private int currentSnapshotLineStartIndex;

    private String currentSnapshotLine;

    private final StyledDocument document;

    private int index;

    public DocumentCharStreamV4(StyledDocument document) {
        this.document = document;
        //updateCachedLine();
    }

    public DocumentCharStreamV4(StyledDocument document, OffsetRegion cachedSpan) throws BadLocationException {
        this.document = document;
        this.explicitCache = true;
        this.currentSnapshotLineStartIndex = cachedSpan.getStart();
        this.currentSnapshotLine = document.getText(cachedSpan.getStart(), cachedSpan.getLength()).toString();
    }

    @Override
    public String getText(Interval interval) {
        int startIndex = interval.a;
        int endIndexInclusive = interval.b;
        if (currentSnapshotLine != null) {
            if (startIndex >= currentSnapshotLineStartIndex && (endIndexInclusive + 1) <= currentSnapshotLineStartIndex + currentSnapshotLine.length()) {
                return currentSnapshotLine.substring(startIndex - currentSnapshotLineStartIndex, endIndexInclusive - currentSnapshotLineStartIndex + 1);
            }
        }
        try {
            return document.getText(startIndex, endIndexInclusive - startIndex + 1).toString();
        } catch (BadLocationException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public void consume() {
        int la = LA(1);
        if (la < 0)
            return;

        index++;
        updateCachedLine();
    }

    @Override
    public int LA(int i) {
        if (i == 0) {
            // undefined
            return 0;
        }

        if (i < 0) {
            // e.g. translate LA(-1) to use offset i=0, then data[p+0-1]
            i++;
            if ((index() + i - 1) < 0) {
                // invalid; no char before first char
                return Token.EOF;
            }
        }

        if ((index() + i - 1) >= size()) {
            return Token.EOF;
        }

        int actualIndex = index() + i - 1;
        if (currentSnapshotLine != null
            && actualIndex >= currentSnapshotLineStartIndex
            && actualIndex < currentSnapshotLineStartIndex + currentSnapshotLine.length()) {
            return currentSnapshotLine.charAt(actualIndex - currentSnapshotLineStartIndex);
        }
        try {
            return document.getText(actualIndex, 1).charAt(0);
        } catch (BadLocationException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            return Token.EOF;
        }
    }

    @Override
    public int mark() {
        return 0;
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public void release(int marker) {
    }

    @Override
    public void seek(int index) {
        if (this.index() == index) {
            return;
        }

        this.index = index;
        updateCachedLine();
    }

    @Override
    public int size() {
        return document.getLength();
    }

    @Override
    public String getSourceName() {
        return "NbEditor";
    }

    private void updateCachedLine() {
        if (explicitCache)
            return;

        if (currentSnapshotLine == null
            || index() < currentSnapshotLineStartIndex
            || index() >= currentSnapshotLineStartIndex + currentSnapshotLine.length()) {
            if (index() >= 0 && index() < size()) {
                int currentLine = NbDocument.findLineNumber(document, index());
                currentSnapshotLineStartIndex = NbDocument.findLineOffset(document, currentLine);
                int endIndex = (currentLine < NbDocument.findLineRootElement(document).getElementCount() - 1) ? NbDocument.findLineOffset(document, currentLine + 1) : document.getLength();
                try {
                    currentSnapshotLine = document.getText(currentSnapshotLineStartIndex, endIndex - currentSnapshotLineStartIndex).toString();
                } catch (BadLocationException ex) {
                    LOGGER.log(Level.WARNING, ex.getMessage(), ex);
                }
            } else {
                currentSnapshotLine = null;
                currentSnapshotLineStartIndex = 0;
            }

        }
    }
}
