/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.text.impl;

import org.antlr.netbeans.editor.text.DocumentChange;
import org.antlr.netbeans.editor.text.DocumentVersion;
import org.antlr.netbeans.editor.text.NormalizedDocumentChangeCollection;
import org.antlr.netbeans.editor.text.TrackingFidelity;
import org.antlr.netbeans.editor.text.TrackingPosition;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class NbForwardTrackingPosition extends NbTrackingPosition {

    public NbForwardTrackingPosition(@NonNull NbDocumentVersion textVersion, int position, @NonNull TrackingPosition.Bias bias) {
        super(textVersion, position, bias);
    }

    @Override
    public TrackingFidelity getTrackingFidelity() {
        return TrackingFidelity.Forward;
    }

    @Override
    public int getOffset(DocumentVersion version, DocumentVersion cachedVersion, int cachedPosition) {
        Parameters.notNull("version", version);
        if (version.equals(this.getTextVersion())) {
            return getPosition();
        }

        if (version.equals(cachedVersion)) {
            return cachedPosition;
        }

        if (!version.getVersionedDocument().equals(this.getVersionedDocument())) {
            throw new IllegalArgumentException();
        }

        DocumentVersion sourceVersion;
        int sourcePosition;
        if (version.getVersionNumber() > cachedVersion.getVersionNumber()) {
            sourceVersion = cachedVersion;
            sourcePosition = cachedPosition;
        } else if (version.getVersionNumber() > getTextVersion().getVersionNumber()) {
            sourceVersion = getTextVersion();
            sourcePosition = getPosition();
        } else {
            throw new UnsupportedOperationException("This tracking point has forward fidelity.");
        }

        boolean positive = getBias() == TrackingPosition.Bias.Forward;
        for (DocumentVersion currentVersion = sourceVersion; currentVersion != null && currentVersion.getVersionNumber() < version.getVersionNumber(); currentVersion = currentVersion.getNext()) {
            NormalizedDocumentChangeCollection changes = currentVersion.getChanges();
            DocumentChange relevantChange = null;
            for (DocumentChange change : changes) {
                boolean relevant = sourcePosition >= change.getOldOffset();
                if (relevant) {
                    relevantChange = change;
                } else {
                    break;
                }
            }
            
            if (relevantChange != null) {
                if (sourcePosition >= relevantChange.getOldOffset() && sourcePosition <= relevantChange.getOldEnd()) {
                    sourcePosition = relevantChange.getNewOffset();
                    if (positive) {
                        sourcePosition += relevantChange.getNewLength();
                    }
                } else {
                    sourcePosition += relevantChange.getNewOffset() - relevantChange.getOldOffset();
                    sourcePosition += relevantChange.getDelta();
                }
            }
        }

        return sourcePosition;
    }

    @Override
    public String toString() {
        return String.format("V%d %s@%s", getTextVersion().getVersionNumber(), getBias().toString(), getPosition());
    }
}
