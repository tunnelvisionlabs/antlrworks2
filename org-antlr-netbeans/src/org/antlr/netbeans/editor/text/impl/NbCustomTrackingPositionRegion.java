/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.text.impl;

import org.antlr.netbeans.editor.text.CustomTrackToBehavior;
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
public class NbCustomTrackingPositionRegion implements TrackingPositionRegion {
    @NonNull
    private final NbDocumentVersion documentVersion;
    @NonNull
    private final OffsetRegion span;
    @NonNull
    private final TrackingFidelity fidelity;
    @NonNull
    private final CustomTrackToBehavior behavior;

    public NbCustomTrackingPositionRegion(@NonNull NbDocumentVersion documentVersion, @NonNull OffsetRegion span, @NonNull TrackingFidelity fidelity, @NonNull CustomTrackToBehavior behavior) {
        Parameters.notNull("documentVersion", documentVersion);
        Parameters.notNull("span", span);
        Parameters.notNull("fidelity", fidelity);
        Parameters.notNull("behavior", behavior);

        this.documentVersion = documentVersion;
        this.span = span;
        this.fidelity = fidelity;
        this.behavior = behavior;
    }

    @Override
    public VersionedDocument getVersionedDocument() {
        return documentVersion.getVersionedDocument();
    }

    @Override
    public TrackingFidelity getTrackingFidelity() {
        return fidelity;
    }

    @Override
    public TrackingPositionRegion.Bias getBias() {
        return TrackingPositionRegion.Bias.Custom;
    }

    @Override
    public SnapshotPosition getStartPosition(DocumentSnapshot snapshot) {
        OffsetRegion targetSpan = behavior.trackRegion(this, documentVersion, snapshot.getVersion(), span);
        return new SnapshotPosition(snapshot, targetSpan.getStart());
    }

    @Override
    public SnapshotPosition getEndPosition(DocumentSnapshot snapshot) {
        OffsetRegion targetSpan = behavior.trackRegion(this, documentVersion, snapshot.getVersion(), span);
        return new SnapshotPosition(snapshot, targetSpan.getEnd());
    }

    @Override
    public SnapshotPositionRegion getRegion(DocumentSnapshot snapshot) {
        OffsetRegion targetSpan = behavior.trackRegion(this, documentVersion, snapshot.getVersion(), span);
        return new SnapshotPositionRegion(snapshot, targetSpan);
    }

    @Override
    public SnapshotPositionRegion getRegion(DocumentVersion version) {
        Parameters.notNull("version", version);
        if (!(version instanceof NbDocumentVersion)) {
            throw new UnsupportedOperationException();
        }

        OffsetRegion targetSpan = behavior.trackRegion(this, documentVersion, version, span);
        return new SnapshotPositionRegion(((NbDocumentVersion)version).getSnapshot(), targetSpan);
    }

    @Override
    public String getText(DocumentSnapshot snapshot) {
        return getRegion(snapshot).getText();
    }

}
