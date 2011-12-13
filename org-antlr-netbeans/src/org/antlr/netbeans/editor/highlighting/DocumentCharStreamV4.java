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
package org.antlr.netbeans.editor.highlighting;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.openide.text.NbDocument;

/**
 *
 * @author sam
 */
public class DocumentCharStreamV4 implements CharStream {
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
                }
            } else {
                currentSnapshotLine = null;
                currentSnapshotLineStartIndex = 0;
            }

        }
    }
}
