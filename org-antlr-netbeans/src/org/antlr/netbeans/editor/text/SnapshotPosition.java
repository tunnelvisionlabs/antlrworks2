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
