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
