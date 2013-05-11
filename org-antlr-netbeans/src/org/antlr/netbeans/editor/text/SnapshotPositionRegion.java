/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.text;

import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public final class SnapshotPositionRegion {

    @NonNull
    private final DocumentSnapshot snapshot;
    @NonNull
    private final OffsetRegion region;

    public SnapshotPositionRegion(@NonNull DocumentSnapshot snapshot, int start, int length) {
        this(snapshot, new OffsetRegion(start, length));
    }

    public SnapshotPositionRegion(@NonNull DocumentSnapshot snapshot, @NonNull OffsetRegion region) {
        Parameters.notNull("snapshot", snapshot);
        Parameters.notNull("region", region);
        if (region.getEnd() > snapshot.length()) {
            throw new IllegalArgumentException("The specified region does not lie within the snapshot.");
        }

        this.snapshot = snapshot;
        this.region = region;
    }

    public SnapshotPositionRegion(@NonNull SnapshotPosition start, int length) {
        this(start.getSnapshot(), start.getOffset(), length);
    }

    public SnapshotPositionRegion(@NonNull SnapshotPosition start, @NonNull SnapshotPosition end) {
        this(start, start.difference(end));
    }

    public @NonNull SnapshotPosition getStart() {
        return new SnapshotPosition(snapshot, region.getStart());
    }

    public @NonNull SnapshotPosition getEnd() {
        return new SnapshotPosition(snapshot, region.getEnd());
    }

    public int getLength() {
        return region.getLength();
    }

    public boolean isEmpty() {
        return region.isEmpty();
    }

    public @NonNull DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    public @NonNull OffsetRegion getRegion() {
        return region;
    }

    public boolean contains(int offset) {
        return region.contains(offset);
    }

    public boolean contains(@NonNull SnapshotPosition position) {
        Parameters.notNull("position", position);
        if (!position.getSnapshot().equals(snapshot)) {
            throw new IllegalArgumentException();
        }

        return contains(position.getOffset());
    }

    public boolean contains(@NonNull SnapshotPositionRegion region) {
        Parameters.notNull("region", region);
        if (!region.getSnapshot().equals(snapshot)) {
            throw new IllegalArgumentException();
        }

        return contains(region.getRegion());
    }

    public boolean contains(@NonNull OffsetRegion region) {
        Parameters.notNull("region", region);
        return region.getStart() >= this.region.getStart()
            && region.getEnd() <= this.region.getEnd();
    }

    public @NonNull String getText() {
        return snapshot.subSequence(region.getStart(), region.getEnd()).toString();
    }

    public @CheckForNull SnapshotPositionRegion intersection(@NonNull SnapshotPositionRegion other) {
        Parameters.notNull("other", other);
        if (!other.getSnapshot().equals(snapshot)) {
            throw new IllegalArgumentException();
        }

        return intersection(other.getRegion());
    }

    public @CheckForNull SnapshotPositionRegion intersection(@NonNull OffsetRegion other) {
        Parameters.notNull("other", other);
        int newStart = Math.max(this.region.getStart(), other.getStart());
        int newEnd = Math.min(this.region.getEnd(), other.getEnd());
        if (newEnd < newStart) {
            return null;
        }

        return new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(newStart, newEnd));
    }

    public boolean intersectsWith(@NonNull SnapshotPositionRegion other) {
        Parameters.notNull("other", other);
        if (!other.getSnapshot().equals(snapshot)) {
            throw new IllegalArgumentException();
        }

        return intersectsWith(other.getRegion());
    }

    public boolean intersectsWith(@NonNull OffsetRegion other) {
        Parameters.notNull("other", other);
        int newStart = Math.max(this.region.getStart(), other.getStart());
        int newEnd = Math.min(this.region.getEnd(), other.getEnd());
        return newEnd >= newStart;
    }

    public @CheckForNull SnapshotPositionRegion overlap(@NonNull SnapshotPositionRegion other) {
        Parameters.notNull("other", other);
        if (!other.getSnapshot().equals(snapshot)) {
            throw new IllegalArgumentException();
        }

        return overlap(other.getRegion());
    }

    public @CheckForNull SnapshotPositionRegion overlap(@NonNull OffsetRegion other) {
        Parameters.notNull("other", other);
        int newStart = Math.max(this.region.getStart(), other.getStart());
        int newEnd = Math.min(this.region.getEnd(), other.getEnd());
        if (newEnd <= newStart) {
            return null;
        }

        return new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(newStart, newEnd));
    }

    public boolean overlapsWith(@NonNull SnapshotPositionRegion other) {
        Parameters.notNull("other", other);
        if (!other.getSnapshot().equals(snapshot)) {
            throw new IllegalArgumentException();
        }

        return overlapsWith(other.getRegion());
    }

    public boolean overlapsWith(@NonNull OffsetRegion other) {
        Parameters.notNull("other", other);
        int newStart = Math.max(this.region.getStart(), other.getStart());
        int newEnd = Math.min(this.region.getEnd(), other.getEnd());
        return newEnd > newStart;
    }

    public @NonNull SnapshotPositionRegion translateTo(@NonNull DocumentSnapshot targetSnapshot, @NonNull TrackingPositionRegion.Bias bias) {
        Parameters.notNull("targetSnapshot", targetSnapshot);
        Parameters.notNull("bias", bias);

        TrackingPosition.Bias startTrackingBias;
        TrackingPosition.Bias endTrackingBias;

        switch (bias) {
        case Exclusive:
            startTrackingBias = TrackingPosition.Bias.Forward;
            endTrackingBias = TrackingPosition.Bias.Backward;
            break;

        case Inclusive:
            startTrackingBias = TrackingPosition.Bias.Backward;
            endTrackingBias = TrackingPosition.Bias.Forward;
            break;

        case Forward:
            startTrackingBias = TrackingPosition.Bias.Forward;
            endTrackingBias = TrackingPosition.Bias.Forward;
            break;

        case Backward:
            startTrackingBias = TrackingPosition.Bias.Backward;
            endTrackingBias = TrackingPosition.Bias.Backward;
            break;

        case Custom:
        default:
            throw new UnsupportedOperationException();
        }

        SnapshotPosition start = getStart().translateTo(targetSnapshot, startTrackingBias);
        SnapshotPosition end = getEnd().translateTo(targetSnapshot, endTrackingBias);
        assert targetSnapshot.getVersionedDocument().equals(snapshot.getVersionedDocument()) : "exception should have been thrown";
        return new SnapshotPositionRegion(start, end);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SnapshotPositionRegion)) {
            return false;
        }

        return equals((SnapshotPositionRegion)obj);
    }

    public boolean equals(SnapshotPositionRegion other) {
        if (other == null) {
            return false;
        }

        return this.snapshot.equals(other.snapshot)
            && this.region.equals(other.region);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.snapshot.hashCode();
        hash = 23 * hash + this.region.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        int version = getSnapshot().getVersion().getVersionNumber();
        String regionText = getRegion().toString();
        int textLength = Math.min(this.getLength(), 40);
        String text = getSnapshot().subSequence(getStart().getOffset(), getStart().getOffset() + textLength).toString();
        return String.format("v%d_%s_'%s'", version, regionText, text);
    }

}
