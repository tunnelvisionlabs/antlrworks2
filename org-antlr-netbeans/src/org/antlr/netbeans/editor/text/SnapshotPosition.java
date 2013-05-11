/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.text;

import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public final class SnapshotPosition implements Comparable<SnapshotPosition> {

    @NonNull
    private final DocumentSnapshot snapshot;
    private final int offset;

    public SnapshotPosition(@NonNull DocumentSnapshot snapshot, int offset) {
        Parameters.notNull("snapshot", snapshot);
        if (offset < 0) {
            throw new IllegalArgumentException("offset cannot be negative");
        }
        if (offset > snapshot.length()) {
            throw new IllegalArgumentException("offset does not lie within the document");
        }

        this.snapshot = snapshot;
        this.offset = offset;
    }

    public @NonNull DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    public int getOffset() {
        return offset;
    }

    public @NonNull DocumentSnapshotLine getContainingLine() {
        return snapshot.findLineFromOffset(offset);
    }

    public char getCharacter() {
        return snapshot.charAt(offset);
    }

    @Override
    public int compareTo(@NonNull SnapshotPosition other) {
        return offset < other.offset ? -1 : (offset > other.offset ? 1 : 0);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.snapshot.hashCode();
        hash = 89 * hash + this.offset;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SnapshotPosition)) {
            return false;
        }

        SnapshotPosition other = (SnapshotPosition)obj;
        return this.offset == other.offset && this.snapshot.equals(other.snapshot);
    }

    public @NonNull SnapshotPosition add(int offset) {
        if (offset == 0) {
            return this;
        }

        return new SnapshotPosition(snapshot, this.offset + offset);
    }

    public @NonNull SnapshotPosition subtract(int offset) {
        return add(-offset);
    }

    public int difference(@NonNull SnapshotPosition other) {
        Parameters.notNull("other", other);
        if (!other.getSnapshot().equals(this.getSnapshot())) {
            throw new IllegalArgumentException();
        }

        return other.getOffset() - this.getOffset();
    }

    public @NonNull SnapshotPosition translateTo(@NonNull DocumentSnapshot targetSnapshot, @NonNull TrackingPosition.Bias bias) {
        Parameters.notNull("targetSnapshot", targetSnapshot);
        Parameters.notNull("bias", bias);
        return snapshot.createTrackingPosition(getOffset(), bias).getPosition(targetSnapshot);
    }
}
