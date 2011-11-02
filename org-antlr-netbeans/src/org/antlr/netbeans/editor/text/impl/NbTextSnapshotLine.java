/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text.impl;

import org.antlr.netbeans.editor.text.SnapshotPoint;
import org.antlr.netbeans.editor.text.SnapshotSpan;
import org.antlr.netbeans.editor.text.TextSnapshot;
import org.antlr.netbeans.editor.text.TextSnapshotLine;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
class NbTextSnapshotLine implements TextSnapshotLine {
    private final NbTextSnapshot snapshot;
    private final int lineNumber;

    private final SnapshotPoint start;
    private final String textIncludingLineBreak;
    private final int lineBreakLength;

    public NbTextSnapshotLine(NbTextSnapshot snapshot, int lineNumber) {
        Parameters.notNull("snapshot", snapshot);
        if (lineNumber < 0 || lineNumber >= snapshot.getLineCount()) {
            throw new IndexOutOfBoundsException();
        }

        this.snapshot = snapshot;
        this.lineNumber = lineNumber;

        LineTextCache lineData = snapshot.getVersion().getLineData();
        int block = lineData.getBlockFromLineNumber(lineNumber);

        int lineStart = lineData.getLineStart(block, lineNumber);
        this.start = new SnapshotPoint(snapshot, lineStart);
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
    public TextSnapshot getSnapshot() {
        return snapshot;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public SnapshotPoint getStart() {
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
    public SnapshotPoint getEnd() {
        return start.add(getLength());
    }

    @Override
    public SnapshotPoint getEndIncludingLineBreak() {
        return start.add(getLengthIncludingLineBreak());
    }

    @Override
    public SnapshotSpan getExtent() {
        return new SnapshotSpan(start, getLength());
    }

    @Override
    public SnapshotSpan getExtentIncludingLineBreak() {
        return new SnapshotSpan(start, getLengthIncludingLineBreak());
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
