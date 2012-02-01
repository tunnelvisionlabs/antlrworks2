/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
