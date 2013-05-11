/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr3.highlighting;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.Token;
import org.openide.text.NbDocument;

/**
 *
 * @author Sam Harwell
 */
public class DocumentCharStream implements CharStream {
    private boolean explicitCache;
    
    private int currentSnapshotLineStartIndex;
    
    private String currentSnapshotLine;
    
    private final StyledDocument document;
    
    private int markDepth;
    
    private List<CharStreamState> markers;
    
    private int lastMarker;
    
    private int line;
    
    private int charPositionInLine;
    
    private int index;

    public DocumentCharStream(StyledDocument document) {
        this.document = document;
        //updateCachedLine();
    }

    public DocumentCharStream(StyledDocument document, OffsetRegion cachedSpan) throws BadLocationException {
        this.document = document;
        this.explicitCache = true;
        this.currentSnapshotLineStartIndex = cachedSpan.getStart();
        this.currentSnapshotLine = document.getText(cachedSpan.getStart(), cachedSpan.getLength()).toString();
    }

    @Override
    public int getCharPositionInLine() {
        return charPositionInLine;
    }

    @Override
    public void setCharPositionInLine(int value) {
        charPositionInLine = value;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public void setLine(int value) {
        line = value;
    }

    @Override
    public int LT(int i) {
        return LA(i);
    }

    @Override
    public String substring(int startIndex, int endIndexInclusive) {
        if (currentSnapshotLine != null) {
            if (startIndex >= currentSnapshotLineStartIndex && (endIndexInclusive + 1) <= currentSnapshotLineStartIndex + currentSnapshotLine.length()) {
                return currentSnapshotLine.substring(startIndex - currentSnapshotLineStartIndex, endIndexInclusive - currentSnapshotLineStartIndex + 1);
            }
        }
        try {
            return document.getText(startIndex, endIndexInclusive - startIndex + 1).toString();
        } catch (BadLocationException ex) {
            return null;
        }
    }

    @Override
    public void consume() {
        int la = LA(1);
        if (la < 0)
            return;

        if (la == '\n') {
            setLine(getLine() + 1);
            setCharPositionInLine(0);
        } else {
            setCharPositionInLine(getCharPositionInLine() + 1);
        }

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
            return Token.EOF;
        }
    }

    @Override
    public int mark() {
        if (markers == null) {
            markers = new ArrayList<>();
            // depth 0 means no backtracking, leave blank
            markers.add(null);
        }

        markDepth++;
        CharStreamState state;
        if (markDepth >= markers.size()) {
            state = new CharStreamState();
            markers.add(state);
        } else {
            state = markers.get(markDepth);
        }

        state.index = index();
        state.line = getLine();
        state.charPositionInLine = getCharPositionInLine();
        lastMarker = markDepth;
        return markDepth;
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public void rewind(int marker) {
        CharStreamState state = markers.get(marker);

        // restore stream state (don't use seek() because it calls updateCachedLine() unnecessarily)
        index = state.index;
        setLine(state.line);
        setCharPositionInLine(state.charPositionInLine);
        release(marker);

        updateCachedLine();
    }

    @Override
    public void rewind() {
        rewind(lastMarker);
    }

    @Override
    public void release(int marker) {
        // unwind any other markers made after m and release m
        markDepth = marker;
        // release this marker
        markDepth--;
    }

    @Override
    public void seek(int index) {
        if (this.index() == index) {
            return;
        }

        this.index = index;
        this.line = NbDocument.findLineNumber(document, index);
        this.charPositionInLine = NbDocument.findLineColumn(document, index);
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
                }
            } else {
                currentSnapshotLine = null;
                currentSnapshotLineStartIndex = 0;
            }

        }
    }

    private static class CharStreamState {
        int index;
        int line;
        int charPositionInLine;
    }
}
