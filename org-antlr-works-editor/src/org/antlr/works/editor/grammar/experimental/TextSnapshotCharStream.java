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

import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentSnapshotLine;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.v4.runtime.CharStream;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public class TextSnapshotCharStream implements CharStream {

    // input info
    private final DocumentSnapshot snapshot;
    private final int count;
    private String sourceName = "Snapshot";

    // position info
    private int index;
    private int line;
    private int charPositionInLine;

    // cache info
    private boolean explicitCache;
    private int currentSnapshotLineStartIndex;
    private String currentSnapshotLine;

    public TextSnapshotCharStream(DocumentSnapshot snapshot) {
        Parameters.notNull("snapshot", snapshot);

        this.snapshot = snapshot;
        this.count = snapshot.length();

        updateCachedLine();
    }

    public TextSnapshotCharStream(DocumentSnapshot snapshot, OffsetRegion cachedSpan) {
        this(new SnapshotPositionRegion(snapshot, cachedSpan));
    }

    public TextSnapshotCharStream(SnapshotPositionRegion cachedSpan) {
        this.snapshot = cachedSpan.getSnapshot();
        this.count = getSnapshot().length();
        this.explicitCache = true;
        this.currentSnapshotLineStartIndex = cachedSpan.getStart().getOffset();
        this.currentSnapshotLine = cachedSpan.getText();
    }

    public final DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getCharPositionInLine() {
        return charPositionInLine;
    }

    public void setCharPositionInLine(int charPositionInLine) {
        this.charPositionInLine = charPositionInLine;
    }

    @Override
    public final int size() {
        return count;
    }

    @Override
    public final int index() {
        return index;
    }

    private void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        Parameters.notNull("sourceName", sourceName);
        this.sourceName = sourceName;
    }

    public int LT(int i) {
        return LA(i);
    }

    @Override
    public String substring(int start, int stop) {
        if (currentSnapshotLine != null) {
            if (start >= currentSnapshotLineStartIndex && stop < currentSnapshotLineStartIndex + currentSnapshotLine.length()) {
                return currentSnapshotLine.substring(start - currentSnapshotLineStartIndex, stop + 1 - currentSnapshotLineStartIndex);
            }
        }

        return getSnapshot().subSequence(start, stop + 1).toString();
    }

    @Override
    public void consume() {
        int la = LA(1);
        if (la < 0) {
            return;
        }

        if (la == '\n') {
            setLine(getLine() + 1);
            setCharPositionInLine(0);
        } else {
            setCharPositionInLine(getCharPositionInLine() + 1);
        }

        setIndex(index() + 1);
        updateCachedLine();
    }

    @Override
    public int LA(int i) {
        if (i == 0) {
            // undefined
            return 0;
        }

        if (i < 0) {
            // e.g., translate LA(-1) to use offset i=0; then data[p+0-1]
            i++;
            if ((index() + i - 1) < 0) {
                // invalid; no char before first char
                return CharStream.EOF;
            }
        }

        if ((index() + i - 1) >= size()) {
            return CharStream.EOF;
        }

        int actualIndex = index() + i - 1;

        if (currentSnapshotLine != null
            && actualIndex >= currentSnapshotLineStartIndex
            && actualIndex < currentSnapshotLineStartIndex + currentSnapshotLine.length()) {
            return currentSnapshotLine.charAt(actualIndex - currentSnapshotLineStartIndex);
        }

        return getSnapshot().charAt(actualIndex);
    }

    @Override
    public int mark() {
        return 0;
    }

    @Override
    public void release(int marker) {
    }

    @Override
    public void seek(int index) {
        if (index == index()) {
            return;
        }

        setIndex(index);
        DocumentSnapshotLine currentLine = getSnapshot().findLineFromOffset(index());
        setLine(currentLine.getLineNumber());
        setCharPositionInLine(index() - currentLine.getStart().getOffset());
        updateCachedLine();
    }

    private void updateCachedLine() {
        if (explicitCache) {
            return;
        }

        if (currentSnapshotLine == null
            || index() < currentSnapshotLineStartIndex
            || index() >= currentSnapshotLineStartIndex + currentSnapshotLine.length()) {
            if (index() >= 0 && index() < size()) {
                DocumentSnapshotLine currentLine = getSnapshot().findLineFromOffset(index());
                currentSnapshotLineStartIndex = currentLine.getStart().getOffset();
                currentSnapshotLine = currentLine.getTextIncludingLineBreak();
            } else {
                currentSnapshotLine = null;
                currentSnapshotLineStartIndex = 0;
            }
        }
    }

}
