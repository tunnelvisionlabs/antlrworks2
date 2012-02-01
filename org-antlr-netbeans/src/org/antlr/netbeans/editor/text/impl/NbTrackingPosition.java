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
