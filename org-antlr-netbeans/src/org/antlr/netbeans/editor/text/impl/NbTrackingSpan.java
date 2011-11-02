/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text.impl;

import org.antlr.netbeans.editor.text.SnapshotPoint;
import org.antlr.netbeans.editor.text.SnapshotSpan;
import org.antlr.netbeans.editor.text.SpanTrackingMode;
import org.antlr.netbeans.editor.text.TextBuffer;
import org.antlr.netbeans.editor.text.TextSnapshot;
import org.antlr.netbeans.editor.text.TextVersion;
import org.antlr.netbeans.editor.text.TrackingFidelityMode;
import org.antlr.netbeans.editor.text.TrackingSpan;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public class NbTrackingSpan implements TrackingSpan {
    private final NbTrackingPoint trackingStart;
    private final NbTrackingPoint trackingEnd;

    public NbTrackingSpan(NbTrackingPoint trackingStart, NbTrackingPoint trackingEnd) {
        Parameters.notNull("trackingStart", trackingStart);
        Parameters.notNull("trackingEnd", trackingEnd);
        if (!trackingStart.getTrackingFidelity().equals(trackingEnd.getTrackingFidelity())) {
            throw new IllegalArgumentException("Mismatched tracking fidelity.");
        }

        this.trackingStart = trackingStart;
        this.trackingEnd = trackingEnd;
    }

    @Override
    public TextBuffer getTextBuffer() {
        return trackingStart.getTextBuffer();
    }

    @Override
    public TrackingFidelityMode getTrackingFidelity() {
        return trackingStart.getTrackingFidelity();
    }

    @Override
    public SpanTrackingMode getTrackingMode() {
        return SpanTrackingMode.fromPointTrackingMode(trackingStart.getTrackingMode(), trackingEnd.getTrackingMode());
    }

    @Override
    public SnapshotPoint getStartPoint(TextSnapshot snapshot) {
        return trackingStart.getPoint(snapshot);
    }

    @Override
    public SnapshotPoint getEndPoint(TextSnapshot snapshot) {
        return trackingEnd.getPoint(snapshot);
    }

    @Override
    public SnapshotSpan getSpan(TextSnapshot snapshot) {
        return new SnapshotSpan(getStartPoint(snapshot), getEndPoint(snapshot));
    }

    @Override
    public SnapshotSpan getSpan(TextVersion version) {
        Parameters.notNull("version", version);
        if (!(version instanceof NbTextVersion)) {
            throw new UnsupportedOperationException();
        }

        NbTextVersion textVersion = (NbTextVersion)version;
        return getSpan(textVersion.getSnapshot());
    }

    @Override
    public String getText(TextSnapshot snapshot) {
        return getSpan(snapshot).getText();
    }

}
