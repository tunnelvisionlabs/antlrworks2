/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public final class VirtualSnapshotSpan {

    private final VirtualSnapshotPoint start;
    private final VirtualSnapshotPoint end;

    public VirtualSnapshotSpan(SnapshotSpan span) {
        this(new VirtualSnapshotPoint(span.getStart()), new VirtualSnapshotPoint(span.getEnd()));
    }

    public VirtualSnapshotSpan(VirtualSnapshotPoint start, VirtualSnapshotPoint end) {
        Parameters.notNull("start", start);
        Parameters.notNull("end", end);
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("The start position cannot be after the end position.");
        }

        this.start = start;
        this.end = end;
    }

    public VirtualSnapshotPoint getStart() {
        return start;
    }

    public VirtualSnapshotPoint getEnd() {
        return end;
    }

    public boolean isEmpty() {
        return getStart().compareTo(getEnd()) == 0;
    }

    public boolean isInVirtualSpace() {
        return getStart().isInVirtualSpace() || getEnd().isInVirtualSpace();
    }

    public int getLength() {
        int realLength = end.getPosition().getPosition() - start.getPosition().getPosition();
        int virtualLength = realLength + end.getVirtualSpaces();
        return virtualLength;
    }

    public TextSnapshot getSnapshot() {
        return start.getSnapshot();
    }

    public SnapshotSpan getSnapshotSpan() {
        return new SnapshotSpan(start.getPosition(), end.getPosition());
    }
}
