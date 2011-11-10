/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public final class VirtualSnapshotPoint implements Comparable<VirtualSnapshotPoint> {

    @NonNull
    private final SnapshotPoint position;
    private final int virtualSpaces;

    public VirtualSnapshotPoint(@NonNull SnapshotPoint position) {
        this(position, 0);
    }

    public VirtualSnapshotPoint(@NonNull TextSnapshot snapshot, int position) {
        this(new SnapshotPoint(snapshot, position));
    }

    public VirtualSnapshotPoint(@NonNull TextSnapshotLine line, int offset) {
        Parameters.notNull("line", line);

        this.position = line.getEnd();
        this.virtualSpaces = Math.max(0, offset - line.getLength());
    }

    public VirtualSnapshotPoint(@NonNull SnapshotPoint position, int virtualSpaces) {
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

    public @NonNull TextSnapshot getSnapshot() {
        return position.getSnapshot();
    }

    public @NonNull SnapshotPoint getPosition() {
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
