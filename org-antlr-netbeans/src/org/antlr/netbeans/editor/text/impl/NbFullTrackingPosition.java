/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.text.impl;

import org.antlr.netbeans.editor.text.DocumentVersion;
import org.antlr.netbeans.editor.text.TrackingFidelity;
import org.antlr.netbeans.editor.text.TrackingPosition;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class NbFullTrackingPosition extends NbTrackingPosition {
    @NonNull
    private final TrackingFidelity fidelity;

    public NbFullTrackingPosition(@NonNull NbDocumentVersion textVersion, int position, @NonNull TrackingPosition.Bias bias, @NonNull TrackingFidelity fidelity) {
        super(textVersion, position, bias);
        Parameters.notNull("fidelity", fidelity);
        this.fidelity = fidelity;
    }

    @Override
    public TrackingFidelity getTrackingFidelity() {
        return fidelity;
    }

    @Override
    public int getOffset(DocumentVersion version, DocumentVersion cachedVersion, int cachedPosition) {
        Parameters.notNull("version", version);
        if (version.equals(this.getTextVersion())) {
            return getPosition();
        }

        if (!version.getVersionedDocument().equals(this.getVersionedDocument())) {
            throw new IllegalArgumentException();
        }

        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
