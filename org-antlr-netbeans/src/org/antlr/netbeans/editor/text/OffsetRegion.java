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
public final class OffsetRegion {

    private final int start;
    private final int length;

    public OffsetRegion(int start, int length) {
        if (start < 0) {
            throw new IllegalArgumentException("start cannot be negative");
        }
        if (length < 0) {
            throw new IllegalArgumentException("length cannot be negative");
        }

        this.start = start;
        this.length = length;
    }

    public static @NonNull OffsetRegion fromBounds(int start, int end) {
        return new OffsetRegion(start, end - start);
    }

    public int getStart() {
        return start;
    }

    public int getLength() {
        return length;
    }

    public int getEnd() {
        return start + length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean contains(int position) {
        return position >= start && position < getEnd();
    }

    public boolean contains(@NonNull OffsetRegion region) {
        Parameters.notNull("region", region);
        return region.getStart() >= start && region.getEnd() <= getEnd();
    }

    public @CheckForNull OffsetRegion intersection(@NonNull OffsetRegion region) {
        Parameters.notNull("region", region);
        int commonStart = Math.max(this.start, region.start);
        int commonEnd = Math.min(getEnd(), region.getEnd());
        if (commonEnd < commonStart) {
            return null;
        }

        return fromBounds(commonStart, commonEnd);
    }

    public boolean intersectsWith(@NonNull OffsetRegion region) {
        Parameters.notNull("region", region);
        int commonStart = Math.max(this.start, region.start);
        int commonEnd = Math.min(getEnd(), region.getEnd());
        return commonEnd >= commonStart;
    }

    public @CheckForNull OffsetRegion overlap(@NonNull OffsetRegion region) {
        Parameters.notNull("region", region);
        if (isEmpty() || region.isEmpty()) {
            return null;
        }

        OffsetRegion intersection = intersection(region);
        if (intersection == null || intersection.isEmpty()) {
            return null;
        }

        return intersection;
    }

    public boolean overlapsWith(@NonNull OffsetRegion region) {
        Parameters.notNull("region", region);
        int commonStart = Math.max(this.start, region.start);
        int commonEnd = Math.min(getEnd(), region.getEnd());
        return commonEnd > commonStart;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OffsetRegion)) {
            return false;
        }

        return equals((OffsetRegion)obj);
    }

    public boolean equals(OffsetRegion region) {
        if (region == null) {
            return false;
        }

        if (this == region) {
            return true;
        }

        return start == region.start
            && length == region.length;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.start;
        hash = 73 * hash + this.length;
        return hash;
    }

    @Override
    public String toString() {
        return String.format("[%d..%d)", start, getEnd());
    }
}
