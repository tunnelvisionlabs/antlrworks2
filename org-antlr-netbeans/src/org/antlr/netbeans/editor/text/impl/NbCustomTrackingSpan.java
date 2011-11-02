/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text.impl;

import org.antlr.netbeans.editor.text.CustomTrackToBehavior;
import org.antlr.netbeans.editor.text.SnapshotPoint;
import org.antlr.netbeans.editor.text.SnapshotSpan;
import org.antlr.netbeans.editor.text.Span;
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
public class NbCustomTrackingSpan implements TrackingSpan {
    private final NbTextVersion textVersion;
    private final Span span;
    private final TrackingFidelityMode trackingFidelity;
    private final CustomTrackToBehavior behavior;

    public NbCustomTrackingSpan(NbTextVersion textVersion, Span span, TrackingFidelityMode trackingFidelity, CustomTrackToBehavior behavior) {
        Parameters.notNull("textVersion", textVersion);
        Parameters.notNull("span", span);
        Parameters.notNull("trackingFidelity", trackingFidelity);
        Parameters.notNull("behavior", behavior);

        this.textVersion = textVersion;
        this.span = span;
        this.trackingFidelity = trackingFidelity;
        this.behavior = behavior;
    }

    @Override
    public TextBuffer getTextBuffer() {
        return textVersion.getTextBuffer();
    }

    @Override
    public TrackingFidelityMode getTrackingFidelity() {
        return trackingFidelity;
    }

    @Override
    public SpanTrackingMode getTrackingMode() {
        return SpanTrackingMode.Custom;
    }

    @Override
    public SnapshotPoint getStartPoint(TextSnapshot snapshot) {
        Span targetSpan = behavior.trackSpan(this, textVersion, snapshot.getVersion(), span);
        return new SnapshotPoint(snapshot, targetSpan.getStart());
    }

    @Override
    public SnapshotPoint getEndPoint(TextSnapshot snapshot) {
        Span targetSpan = behavior.trackSpan(this, textVersion, snapshot.getVersion(), span);
        return new SnapshotPoint(snapshot, targetSpan.getEnd());
    }

    @Override
    public SnapshotSpan getSpan(TextSnapshot snapshot) {
        Span targetSpan = behavior.trackSpan(this, textVersion, snapshot.getVersion(), span);
        return new SnapshotSpan(snapshot, targetSpan);
    }

    @Override
    public SnapshotSpan getSpan(TextVersion version) {
        Parameters.notNull("version", version);
        if (!(version instanceof NbTextVersion)) {
            throw new UnsupportedOperationException();
        }

        Span targetSpan = behavior.trackSpan(this, textVersion, version, span);
        return new SnapshotSpan(((NbTextVersion)version).getSnapshot(), targetSpan);
    }

    @Override
    public String getText(TextSnapshot snapshot) {
        return getSpan(snapshot).getText();
    }

}
