/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public final class VirtualSnapshotPoint implements Comparable<VirtualSnapshotPoint> {

    private final SnapshotPoint position;
    private final int virtualSpaces;

    public VirtualSnapshotPoint(SnapshotPoint position) {
        this(position, 0);
    }

    public VirtualSnapshotPoint(TextSnapshot snapshot, int position) {
        this(new SnapshotPoint(snapshot, position));
    }

    public VirtualSnapshotPoint(TextSnapshotLine line, int offset) {
        Parameters.notNull("line", line);

        this.position = line.getEnd();
        this.virtualSpaces = Math.max(0, offset - line.getLength());
    }

    public VirtualSnapshotPoint(SnapshotPoint position, int virtualSpaces) {
        Parameters.notNull("position", position);
        if (virtualSpaces < 0) {
            throw new IllegalArgumentException("virtualSpaces cannot be negative");
        }

        if (virtualSpaces > 0 && !position.getContainingLine().getEnd().equals(position)) {
            throw new IllegalArgumentException("virtualSpaces only apply when the position is at the end of a line.");
        }

        this.position = position;
        this.virtualSpaces = virtualSpaces;
    }

    public TextSnapshot getSnapshot() {
        return position.getSnapshot();
    }

    public SnapshotPoint getPosition() {
        return position;
    }

    public int getVirtualSpaces() {
        return virtualSpaces;
    }

    public boolean isInVirtualSpace() {
        return getVirtualSpaces() > 0;
    }

    @Override
    public int compareTo(VirtualSnapshotPoint other) {
        Parameters.notNull("other", other);
        int result = position.compareTo(other.position);
        if (result == 0) {
            result = other.virtualSpaces - virtualSpaces;
        }

        return result;
    }

}
