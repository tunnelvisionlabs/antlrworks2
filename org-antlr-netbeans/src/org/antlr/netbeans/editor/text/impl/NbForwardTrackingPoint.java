/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text.impl;

import org.antlr.netbeans.editor.text.NormalizedTextChangeCollection;
import org.antlr.netbeans.editor.text.PointTrackingMode;
import org.antlr.netbeans.editor.text.TextChange;
import org.antlr.netbeans.editor.text.TextVersion;
import org.antlr.netbeans.editor.text.TrackingFidelityMode;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public class NbForwardTrackingPoint extends NbTrackingPoint {

    public NbForwardTrackingPoint(NbTextVersion textVersion, int position, PointTrackingMode trackingMode) {
        super(textVersion, position, trackingMode);
    }

    @Override
    public TrackingFidelityMode getTrackingFidelity() {
        return TrackingFidelityMode.Forward;
    }

    @Override
    public int getPosition(TextVersion version, TextVersion cachedVersion, int cachedPosition) {
        Parameters.notNull("version", version);
        if (version.equals(this.getTextVersion())) {
            return getPosition();
        }

        if (version.equals(cachedVersion)) {
            return cachedPosition;
        }

        if (!version.getTextBuffer().equals(this.getTextBuffer())) {
            throw new IllegalArgumentException();
        }

        TextVersion sourceVersion;
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

        boolean positive = getTrackingMode() == PointTrackingMode.Positive;
        for (TextVersion currentVersion = sourceVersion; currentVersion.getVersionNumber() < version.getVersionNumber(); currentVersion = currentVersion.getNext()) {
            NormalizedTextChangeCollection changes = currentVersion.getChanges();
            TextChange relevantChange = null;
            for (TextChange change : changes) {
                boolean relevant = sourcePosition >= change.getOldPosition();
                if (relevant) {
                    relevantChange = change;
                } else {
                    break;
                }
            }
            
            if (relevantChange != null) {
                if (sourcePosition >= relevantChange.getOldPosition() && sourcePosition <= relevantChange.getOldEnd()) {
                    sourcePosition = relevantChange.getNewPosition();
                    if (positive) {
                        sourcePosition += relevantChange.getNewLength();
                    }
                } else {
                    sourcePosition += relevantChange.getDelta();
                }
            }
        }

        return sourcePosition;
    }

    @Override
    public String toString() {
        return String.format("V%d %s@%s", getTextVersion().getVersionNumber(), getTrackingMode().toString(), getPosition());
    }
}
