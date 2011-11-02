/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public final class SnapshotPoint implements Comparable<SnapshotPoint> {

    private final TextSnapshot snapshot;
    private final int position;

    public SnapshotPoint(TextSnapshot snapshot, int position) {
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

    public TextSnapshot getSnapshot() {
        return snapshot;
    }

    public int getPosition() {
        return position;
    }

    public TextSnapshotLine getContainingLine() {
        return snapshot.getLineFromPosition(position);
    }

    public char getCharacter() {
        return snapshot.charAt(position);
    }

    @Override
    public int compareTo(SnapshotPoint other) {
        return difference(other);
    }

    public SnapshotPoint add(int offset) {
        if (offset == 0) {
            return this;
        }

        return new SnapshotPoint(snapshot, position + offset);
    }

    public SnapshotPoint subtract(int offset) {
        return add(-offset);
    }

    public int difference(SnapshotPoint other) {
        Parameters.notNull("other", other);
        if (!other.getSnapshot().equals(this.getSnapshot())) {
            throw new IllegalArgumentException();
        }

        return other.getPosition() - this.getPosition();
    }

    public SnapshotPoint translateTo(TextSnapshot targetSnapshot, PointTrackingMode trackingMode) {
        Parameters.notNull("targetSnapshot", targetSnapshot);
        Parameters.notNull("trackingMode", trackingMode);
        return snapshot.createTrackingPoint(getPosition(), trackingMode).getPoint(targetSnapshot);
    }

}
