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
package org.antlr.netbeans.editor.text.impl;

import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentVersion;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.TrackingFidelity;
import org.antlr.netbeans.editor.text.TrackingPosition;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public abstract class NbTrackingPosition implements TrackingPosition {
    @NonNull
    private final NbDocumentVersion textVersion;
    private final int position;
    @NonNull
    private final TrackingPosition.Bias trackingMode;

    private final Object lock = new Object();

    @NonNull
    private DocumentVersion cachedVersion;
    private int cachedPosition;

    public NbTrackingPosition(@NonNull NbDocumentVersion textVersion, int position, @NonNull TrackingPosition.Bias trackingMode) {
        Parameters.notNull("textVersion", textVersion);
        Parameters.notNull("trackingMode", trackingMode);
        if (position < 0 || position > textVersion.getLength()) {
            throw new IndexOutOfBoundsException();
        }

        this.textVersion = textVersion;
        this.position = position;
        this.trackingMode = trackingMode;

        this.cachedVersion = textVersion;
        this.cachedPosition = position;
    }

    public final @NonNull NbDocumentVersion getTextVersion() {
        return textVersion;
    }

    @Override
    public final VersionedDocument getVersionedDocument() {
        return textVersion.getVersionedDocument();
    }

    @Override
    public final TrackingPosition.Bias getBias() {
        return trackingMode;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public abstract TrackingFidelity getTrackingFidelity();

    protected abstract int getOffset(@NonNull DocumentVersion version, @NonNull DocumentVersion cachedVersion, int cachedPosition);

    @Override
    public int getOffset(DocumentVersion version) {
        DocumentVersion referenceVersion;
        int referencePosition;

        synchronized (lock) {
            referenceVersion = cachedVersion;
            referencePosition = cachedPosition;
        }

        int updatedPosition = getOffset(version, referenceVersion, referencePosition);

        synchronized (lock) {
            cachedVersion = version;
            cachedPosition = updatedPosition;
        }

        return updatedPosition;
    }

    @Override
    public SnapshotPosition getPosition(DocumentSnapshot snapshot) {
        Parameters.notNull("snapshot", snapshot);
        return new SnapshotPosition(snapshot, getOffset(snapshot.getVersion()));
    }

    @Override
    public int getOffset(DocumentSnapshot snapshot) {
        Parameters.notNull("snapshot", snapshot);
        return getOffset(snapshot.getVersion());
    }

    @Override
    public char getCharacter(DocumentSnapshot snapshot) {
        Parameters.notNull("snapshot", snapshot);
        return getPosition(snapshot).getCharacter();
    }

}
