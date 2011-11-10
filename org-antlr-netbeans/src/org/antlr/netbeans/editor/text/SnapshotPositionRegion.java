/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.antlr.netbeans.editor.text;

import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class SnapshotPositionRegion {

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
        hash = 23 * hash + (this.snapshot != null ? this.snapshot.hashCode() : 0);
        hash = 23 * hash + (this.region != null ? this.region.hashCode() : 0);
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
