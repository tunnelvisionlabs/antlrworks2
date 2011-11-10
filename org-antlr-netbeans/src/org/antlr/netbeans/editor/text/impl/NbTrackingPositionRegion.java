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
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingFidelity;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class NbTrackingPositionRegion implements TrackingPositionRegion {
    @NonNull
    private final NbTrackingPosition trackingStart;
    @NonNull
    private final NbTrackingPosition trackingEnd;

    public NbTrackingPositionRegion(@NonNull NbTrackingPosition trackingStart, @NonNull NbTrackingPosition trackingEnd) {
        Parameters.notNull("trackingStart", trackingStart);
        Parameters.notNull("trackingEnd", trackingEnd);
        if (!trackingStart.getTrackingFidelity().equals(trackingEnd.getTrackingFidelity())) {
            throw new IllegalArgumentException("Mismatched tracking fidelity.");
        }

        this.trackingStart = trackingStart;
        this.trackingEnd = trackingEnd;
    }

    @Override
    public VersionedDocument getVersionedDocument() {
        return trackingStart.getVersionedDocument();
    }

    @Override
    public TrackingFidelity getTrackingFidelity() {
        return trackingStart.getTrackingFidelity();
    }

    @Override
    public TrackingPositionRegion.Bias getBias() {
        return TrackingPositionRegion.Bias.fromPositionBias(trackingStart.getBias(), trackingEnd.getBias());
    }

    @Override
    public SnapshotPosition getStartPosition(DocumentSnapshot snapshot) {
        return trackingStart.getPosition(snapshot);
    }

    @Override
    public SnapshotPosition getEndPosition(DocumentSnapshot snapshot) {
        return trackingEnd.getPosition(snapshot);
    }

    @Override
    public SnapshotPositionRegion getRegion(DocumentSnapshot snapshot) {
        return new SnapshotPositionRegion(getStartPosition(snapshot), getEndPosition(snapshot));
    }

    @Override
    public SnapshotPositionRegion getRegion(DocumentVersion version) {
        Parameters.notNull("version", version);
        if (!(version instanceof NbDocumentVersion)) {
            throw new UnsupportedOperationException();
        }

        NbDocumentVersion textVersion = (NbDocumentVersion)version;
        return getRegion(textVersion.getSnapshot());
    }

    @Override
    public String getText(DocumentSnapshot snapshot) {
        return getRegion(snapshot).getText();
    }

    @Override
    public String toString() {
        OffsetRegion span = OffsetRegion.fromBounds(trackingStart.getPosition(), trackingEnd.getPosition());
        return String.format("V%d %s@%s", trackingStart.getTextVersion().getVersionNumber(), getBias().toString(), span.toString());
    }
}
