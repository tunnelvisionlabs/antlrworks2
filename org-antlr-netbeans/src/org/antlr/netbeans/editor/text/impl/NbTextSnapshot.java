/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.antlr.netbeans.editor.text.PointTrackingMode;
import org.antlr.netbeans.editor.text.Span;
import org.antlr.netbeans.editor.text.SpanTrackingMode;
import org.antlr.netbeans.editor.text.TextBuffer;
import org.antlr.netbeans.editor.text.TextSnapshot;
import org.antlr.netbeans.editor.text.TextSnapshotLine;
import org.antlr.netbeans.editor.text.TrackingFidelityMode;
import org.antlr.netbeans.editor.text.TrackingPoint;
import org.antlr.netbeans.editor.text.TrackingSpan;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public class NbTextSnapshot implements TextSnapshot {

    private final NbTextVersion textVersion;

    public NbTextSnapshot(NbTextVersion textVersion) {
        Parameters.notNull("textVersion", textVersion);
        this.textVersion = textVersion;
    }

    @Override
    public int getLineCount() {
        return getVersion().getLineData().getLineCount();
    }

    @Override
    public Iterable<TextSnapshotLine> getLines() {
        return new LineIterable(this);
    }

    @Override
    public TextBuffer getTextBuffer() {
        return getVersion().getTextBuffer();
    }

    @Override
    public NbTextVersion getVersion() {
        return textVersion;
    }

    @Override
    public TrackingPoint createTrackingPoint(int position, PointTrackingMode trackingMode) {
        return textVersion.createTrackingPoint(position, trackingMode);
    }

    @Override
    public TrackingPoint createTrackingPoint(int position, PointTrackingMode trackingMode, TrackingFidelityMode trackingFidelity) {
        return textVersion.createTrackingPoint(position, trackingMode, trackingFidelity);
    }

    @Override
    public TrackingSpan createTrackingSpan(Span span, SpanTrackingMode trackingMode) {
        return textVersion.createTrackingSpan(span, trackingMode);
    }

    @Override
    public TrackingSpan createTrackingSpan(int start, int length, SpanTrackingMode trackingMode) {
        return textVersion.createTrackingSpan(start, length, trackingMode);
    }

    @Override
    public TrackingSpan createTrackingSpan(Span span, SpanTrackingMode trackingMode, TrackingFidelityMode trackingFidelity) {
        return textVersion.createTrackingSpan(span, trackingMode, trackingFidelity);
    }

    @Override
    public TrackingSpan createTrackingSpan(int start, int length, SpanTrackingMode trackingMode, TrackingFidelityMode trackingFidelity) {
        return textVersion.createTrackingSpan(start, length, trackingMode);
    }

    @Override
    public TextSnapshotLine getLineFromLineNumber(int lineNumber) {
        if (lineNumber < 0 || lineNumber >= getLineCount()) {
            throw new IndexOutOfBoundsException();
        }

        return new NbTextSnapshotLine(this, lineNumber);
    }

    @Override
    public TextSnapshotLine getLineFromPosition(int position) {
        return getLineFromLineNumber(getLineNumberFromPosition(position));
    }

    @Override
    public int getLineNumberFromPosition(int position) {
        return getVersion().getLineData().getLineNumberFromPosition(position);
    }

    @Override
    public int length() {
        return getVersion().getLineData().getLength();
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index >= length()) {
            throw new IndexOutOfBoundsException();
        }

        LineTextCache lineData = getVersion().getLineData();
        int block = lineData.getBlockFromPosition(index);
        int line = lineData.getLineNumberFromPosition(index);
        int lineStart = lineData.getLineStart(block, line);
        String lineText = lineData.getLineText(block, line);
        return lineText.charAt(index - lineStart);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new SubSequence(this, start, end);
    }

    @Override
    public String getText() {
        return subSequence(0, length()).toString();
    }

    private static final class LineIterable implements Iterable<TextSnapshotLine> {

        private final NbTextSnapshot snapshot;

        public LineIterable(NbTextSnapshot snapshot) {
            Parameters.notNull("snapshot", snapshot);
            this.snapshot = snapshot;
        }

        @Override
        public Iterator<TextSnapshotLine> iterator() {
            return new LineIterator(snapshot);
        }
    }

    private static class LineIterator implements Iterator<TextSnapshotLine> {

        private final NbTextSnapshot snapshot;

        private int currentLine = -1;

        public LineIterator(NbTextSnapshot snapshot) {
            Parameters.notNull("snapshot", snapshot);
            this.snapshot = snapshot;
        }

        @Override
        public boolean hasNext() {
            return currentLine < snapshot.getLineCount();
        }

        @Override
        public TextSnapshotLine next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            currentLine++;
            return new NbTextSnapshotLine(snapshot, currentLine);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("The iterator is read only.");
        }
    }

    private static final class SubSequence implements CharSequence {

        private final NbTextSnapshot snapshot;
        private final int start;
        private final int end;

        public SubSequence(NbTextSnapshot snapshot, int start, int end) {
            Parameters.notNull("snapshot", snapshot);
            if (start < 0 || end > snapshot.length()) {
                throw new IndexOutOfBoundsException();
            }

            if (end < start) {
                throw new IllegalArgumentException();
            }

            assert start <= snapshot.length();
            this.snapshot = snapshot;
            this.start = start;
            this.end = end;
        }

        @Override
        public int length() {
            return end - start;
        }

        @Override
        public char charAt(int index) {
            if (index < 0 || index >= length()) {
                throw new IndexOutOfBoundsException();
            }

            return snapshot.charAt(start + index);
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            if (end > length()) {
                throw new IndexOutOfBoundsException();
            }

            SubSequence result = new SubSequence(snapshot, this.start + start, this.start + end);
            assert start >= 0 && end >= start : "should have thrown exception";
            return result;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(length());
            LineTextCache data = snapshot.textVersion.getLineData();

            int startBlock = data.getBlockFromPosition(start);
            int startLine = data.getBlockLineFromPosition(startBlock, start);
            int startColumn = start - data.getLineStart(startBlock, data.getBlockLineOffsets().get(startBlock) + startLine);

            int endBlock = data.getBlockFromPosition(end - 1);
            int endLine = data.getBlockLineFromPosition(endBlock, end - 1);
            int endColumn = (end - 1) - data.getLineStart(endBlock, data.getBlockLineOffsets().get(endBlock) + endLine);

            for (int block = startBlock; block <= endBlock; block++) {
                List<String> blockData = data.getLineData().get(block);
                for (int line = (block == startBlock) ? startLine : 0; line < ((block == endBlock) ? endLine + 1 : blockData.size()); line++) {
                    int start = 0;
                    int end = blockData.get(line).length();
                    if (block == startBlock && line == startLine) {
                        start = startColumn;
                    }

                    if (block == endBlock && line == endLine) {
                        end = endColumn + 1;
                    }

                    builder.append(blockData.get(line).substring(start, end));
                }
            }

            return builder.toString();
        }
    }

}
