/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.text.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentSnapshotLine;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.TrackingFidelity;
import org.antlr.netbeans.editor.text.TrackingPosition;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class NbDocumentSnapshot implements DocumentSnapshot {

    @NonNull
    private final NbDocumentVersion textVersion;

    public NbDocumentSnapshot(@NonNull NbDocumentVersion textVersion) {
        Parameters.notNull("textVersion", textVersion);
        this.textVersion = textVersion;
    }

    @Override
    public int getLineCount() {
        return getVersion().getLineData().getLineCount();
    }

    @Override
    public Iterable<DocumentSnapshotLine> getLines() {
        return new LineIterable(this);
    }

    @Override
    public VersionedDocument getVersionedDocument() {
        return getVersion().getVersionedDocument();
    }

    @Override
    public NbDocumentVersion getVersion() {
        return textVersion;
    }

    @Override
    public TrackingPosition createTrackingPosition(int position, TrackingPosition.Bias bias) {
        return textVersion.createTrackingPosition(position, bias);
    }

    @Override
    public TrackingPosition createTrackingPosition(int position, TrackingPosition.Bias bias, TrackingFidelity fidelity) {
        return textVersion.createTrackingPosition(position, bias, fidelity);
    }

    @Override
    public TrackingPositionRegion createTrackingRegion(OffsetRegion span, TrackingPositionRegion.Bias bias) {
        return textVersion.createTrackingRegion(span, bias);
    }

    @Override
    public TrackingPositionRegion createTrackingRegion(int start, int length, TrackingPositionRegion.Bias bias) {
        return textVersion.createTrackingRegion(start, length, bias);
    }

    @Override
    public TrackingPositionRegion createTrackingRegion(OffsetRegion span, TrackingPositionRegion.Bias bias, TrackingFidelity fidelity) {
        return textVersion.createTrackingRegion(span, bias, fidelity);
    }

    @Override
    public TrackingPositionRegion createTrackingRegion(int start, int length, TrackingPositionRegion.Bias bias, TrackingFidelity fidelity) {
        return textVersion.createTrackingRegion(start, length, bias);
    }

    @Override
    public DocumentSnapshotLine findLineFromLineNumber(int lineNumber) {
        if (lineNumber < 0 || lineNumber >= getLineCount()) {
            throw new IndexOutOfBoundsException();
        }

        return new NbDocumentSnapshotLine(this, lineNumber);
    }

    @Override
    public DocumentSnapshotLine findLineFromOffset(int offset) {
        return findLineFromLineNumber(findLineNumber(offset));
    }

    @Override
    public int findLineNumber(int offset) {
        return getVersion().getLineData().getLineNumberFromPosition(offset);
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

    @Override
    public String toString() {
        String content = "???";
        return String.format("version: %d lines: %d length: %d\n content: %s",
                             getVersion().getVersionNumber(),
                             getLineCount(),
                             length(),
                             content);
    }

    private static final class LineIterable implements Iterable<DocumentSnapshotLine> {

        @NonNull
        private final NbDocumentSnapshot snapshot;

        public LineIterable(@NonNull NbDocumentSnapshot snapshot) {
            Parameters.notNull("snapshot", snapshot);
            this.snapshot = snapshot;
        }

        @Override
        public Iterator<DocumentSnapshotLine> iterator() {
            return new LineIterator(snapshot);
        }
    }

    private static class LineIterator implements Iterator<DocumentSnapshotLine> {

        @NonNull
        private final NbDocumentSnapshot snapshot;

        private int currentLine = -1;

        public LineIterator(@NonNull NbDocumentSnapshot snapshot) {
            Parameters.notNull("snapshot", snapshot);
            this.snapshot = snapshot;
        }

        @Override
        public boolean hasNext() {
            return currentLine < snapshot.getLineCount();
        }

        @Override
        public DocumentSnapshotLine next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            currentLine++;
            return new NbDocumentSnapshotLine(snapshot, currentLine);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("The iterator is read only.");
        }
    }

    private static final class SubSequence implements CharSequence {

        @NonNull
        private final NbDocumentSnapshot snapshot;
        private final int start;
        private final int end;

        public SubSequence(@NonNull NbDocumentSnapshot snapshot, int start, int end) {
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
            if (length() == 0) {
                return "";
            }

            StringBuilder builder = new StringBuilder(length());
            LineTextCache data = snapshot.textVersion.getLineData();

            int startBlock = data.getBlockFromPosition(start);
            int startLine = data.getBlockLineFromPosition(startBlock, start);
            int startColumn = start - data.getLineStart(startBlock, data.getBlockLineOffsets().get(startBlock) + startLine);

            int endBlock = end == start ? startBlock : data.getBlockFromPosition(end - 1);
            int endLine = end == start ? startLine : data.getBlockLineFromPosition(endBlock, end - 1);
            int endColumn = end == start ? startColumn : (end - 1) - data.getLineStart(endBlock, data.getBlockLineOffsets().get(endBlock) + endLine);

            for (int block = startBlock; block <= endBlock; block++) {
                List<String> blockData = data.getLineData().get(block);
                for (int line = (block == startBlock) ? startLine : 0; line < ((block == endBlock) ? endLine + 1 : blockData.size()); line++) {
                    @SuppressWarnings("LocalVariableHidesMemberVariable")
                    int start = 0;
                    @SuppressWarnings("LocalVariableHidesMemberVariable")
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
