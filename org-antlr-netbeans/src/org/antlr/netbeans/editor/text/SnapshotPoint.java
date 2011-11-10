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
public final class SnapshotPoint implements Comparable<SnapshotPoint> {

    @NonNull
    private final TextSnapshot snapshot;
    private final int position;

    public SnapshotPoint(@NonNull TextSnapshot snapshot, int position) {
        Parameters.notNull("snapshot", snapshot);
        if (position < 0) {
            throw new IllegalArgumentException("position cannot be negative");
        }
        if (position > snapshot.length()) {
            throw new IllegalArgumentException("position does not lie within the document");
        }

        this.snapshot = snapshot;
        this.position = position;
    }

    public @NonNull TextSnapshot getSnapshot() {
        return snapshot;
    }

    public int getPosition() {
        return position;
    }

    public @NonNull TextSnapshotLine getContainingLine() {
        return snapshot.getLineFromPosition(position);
    }

    public char getCharacter() {
        return snapshot.charAt(position);
    }

    @Override
    public int compareTo(@NonNull SnapshotPoint other) {
        return difference(other);
    }

    public @NonNull SnapshotPoint add(int offset) {
        if (offset == 0) {
            return this;
        }

        return new SnapshotPoint(snapshot, position + offset);
    }

    public @NonNull SnapshotPoint subtract(int offset) {
        return add(-offset);
    }

    public int difference(@NonNull SnapshotPoint other) {
        Parameters.notNull("other", other);
        if (!other.getSnapshot().equals(this.getSnapshot())) {
            throw new IllegalArgumentException();
        }

        return other.getPosition() - this.getPosition();
    }

    public @NonNull SnapshotPoint translateTo(@NonNull TextSnapshot targetSnapshot, @NonNull PointTrackingMode trackingMode) {
        Parameters.notNull("targetSnapshot", targetSnapshot);
        Parameters.notNull("trackingMode", trackingMode);
        return snapshot.createTrackingPoint(getPosition(), trackingMode).getPoint(targetSnapshot);
    }

}
