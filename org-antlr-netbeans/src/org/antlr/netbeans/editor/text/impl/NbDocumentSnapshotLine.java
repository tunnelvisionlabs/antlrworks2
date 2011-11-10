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
package org.antlr.netbeans.editor.text.impl;

import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentSnapshotLine;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
class NbDocumentSnapshotLine implements DocumentSnapshotLine {
    @NonNull
    private final NbDocumentSnapshot snapshot;
    private final int lineNumber;

    @NonNull
    private final SnapshotPosition start;
    @NonNull
    private final String textIncludingLineBreak;
    private final int lineBreakLength;

    public NbDocumentSnapshotLine(@NonNull NbDocumentSnapshot snapshot, int lineNumber) {
        Parameters.notNull("snapshot", snapshot);
        if (lineNumber < 0 || lineNumber >= snapshot.getLineCount()) {
            throw new IndexOutOfBoundsException();
        }

        this.snapshot = snapshot;
        this.lineNumber = lineNumber;

        LineTextCache lineData = snapshot.getVersion().getLineData();
        int block = lineData.getBlockFromLineNumber(lineNumber);

        int lineStart = lineData.getLineStart(block, lineNumber);
        this.start = new SnapshotPosition(snapshot, lineStart);
        this.textIncludingLineBreak = lineData.getLineText(block, lineNumber);

        if (textIncludingLineBreak.length() > 0) {
            char lastChar = textIncludingLineBreak.charAt(textIncludingLineBreak.length() - 1);
            if (lastChar == '\n') {
                if (textIncludingLineBreak.length() > 1 && textIncludingLineBreak.charAt(textIncludingLineBreak.length() - 2) == '\r') {
                    lineBreakLength = 2;
                } else {
                    lineBreakLength = 1;
                }
            } else if (lastChar == '\r') {
                lineBreakLength = 1;
            } else {
                lineBreakLength = 0;
            }
        } else {
            lineBreakLength = 0;
        }
    }

    @Override
    public DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public SnapshotPosition getStart() {
        return start;
    }

    @Override
    public int getLength() {
        return textIncludingLineBreak.length() - lineBreakLength;
    }

    @Override
    public int getLengthIncludingLineBreak() {
        return textIncludingLineBreak.length();
    }

    @Override
    public SnapshotPosition getEnd() {
        return start.add(getLength());
    }

    @Override
    public SnapshotPosition getEndIncludingLineBreak() {
        return start.add(getLengthIncludingLineBreak());
    }

    @Override
    public SnapshotPositionRegion getRegion() {
        return new SnapshotPositionRegion(start, getLength());
    }

    @Override
    public SnapshotPositionRegion getRegionIncludingLineBreak() {
        return new SnapshotPositionRegion(start, getLengthIncludingLineBreak());
    }

    @Override
    public int getLineBreakLength() {
        return lineBreakLength;
    }

    @Override
    public String getText() {
        return textIncludingLineBreak.substring(0, getLength());
    }

    @Override
    public String getTextIncludingLineBreak() {
        return textIncludingLineBreak;
    }

    @Override
    public String getLineBreakText() {
        return textIncludingLineBreak.substring(getLength(), getLengthIncludingLineBreak());
    }

}
