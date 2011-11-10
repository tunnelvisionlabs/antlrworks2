/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text.impl;

import org.antlr.netbeans.editor.text.CustomTrackToBehavior;
import org.antlr.netbeans.editor.text.NormalizedTextChangeCollection;
import org.antlr.netbeans.editor.text.PointTrackingMode;
import org.antlr.netbeans.editor.text.Span;
import org.antlr.netbeans.editor.text.SpanTrackingMode;
import org.antlr.netbeans.editor.text.TextBuffer;
import org.antlr.netbeans.editor.text.TextVersion;
import org.antlr.netbeans.editor.text.TrackingFidelityMode;
import org.antlr.netbeans.editor.text.TrackingSpan;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public class NbTextVersion implements TextVersion {

    @NonNull
    private final NbTextBuffer textBuffer;
    private final int versionNumber;
    @NonNull
    private final LineTextCache lineData;

    private NbTextSnapshot snapshot;

    private final Object lock = new Object();
    private NormalizedTextChangeCollection changes;
    private NbTextVersion next;

    public NbTextVersion(@NonNull NbTextBuffer textBuffer, int versionNumber, @NonNull LineTextCache lineData) {
        Parameters.notNull("textBuffer", textBuffer);
        Parameters.notNull("lineData", lineData);

        this.textBuffer = textBuffer;
        this.versionNumber = versionNumber;
        this.lineData = lineData;
    }

    @Override
    public TextBuffer getTextBuffer() {
        return textBuffer;
    }

    @Override
    public int getLength() {
        return lineData.getLength();
    }

    @Override
    public int getVersionNumber() {
        return versionNumber;
    }

    @Override
    public NormalizedTextChangeCollection getChanges() {
        synchronized (lock) {
            return changes;
        }
    }

    @Override
    public TextVersion getNext() {
        synchronized (lock) {
            return next;
        }
    }

    @NonNull LineTextCache getLineData() {
        return lineData;
    }

    @NonNull NbTextSnapshot getSnapshot() {
        synchronized (this) {
            if (snapshot == null) {
                snapshot = new NbTextSnapshot(this);
            }

            return snapshot;
        }
    }

    @Override
    public TrackingSpan createCustomTrackingSpan(Span span, TrackingFidelityMode trackingFidelity, CustomTrackToBehavior behavior) {
        Parameters.notNull("span", span);
        Parameters.notNull("trackingFidelity", trackingFidelity);
        Parameters.notNull("behavior", behavior);

        return new NbCustomTrackingSpan(this, span, trackingFidelity, behavior);
    }

    @Override
    public NbTrackingPoint createTrackingPoint(int position, PointTrackingMode trackingMode) {
        Parameters.notNull("trackingMode", trackingMode);
        return createTrackingPoint(position, trackingMode, TrackingFidelityMode.Forward);
    }

    @Override
    public NbTrackingPoint createTrackingPoint(int position, PointTrackingMode trackingMode, TrackingFidelityMode trackingFidelity) {
        Parameters.notNull("trackingMode", trackingMode);
        Parameters.notNull("trackingFidelity", trackingFidelity);
        if (trackingFidelity == TrackingFidelityMode.Forward) {
            return new NbForwardTrackingPoint(this, position, trackingMode);
        }

        return new NbFullTrackingPoint(this, position, trackingMode, trackingFidelity);
    }

    @Override
    public NbTrackingSpan createTrackingSpan(Span span, SpanTrackingMode trackingMode) {
        Parameters.notNull("span", span);
        Parameters.notNull("trackingMode", trackingMode);
        return createTrackingSpan(span, trackingMode, TrackingFidelityMode.Forward);
    }

    @Override
    public NbTrackingSpan createTrackingSpan(int start, int length, SpanTrackingMode trackingMode) {
        Parameters.notNull("trackingMode", trackingMode);
        return createTrackingSpan(start, length, trackingMode, TrackingFidelityMode.Forward);
    }

    @Override
    public NbTrackingSpan createTrackingSpan(Span span, SpanTrackingMode trackingMode, TrackingFidelityMode trackingFidelity) {
        Parameters.notNull("span", span);
        Parameters.notNull("trackingMode", trackingMode);
        Parameters.notNull("trackingFidelity", trackingFidelity);
        return createTrackingSpan(span.getStart(), span.getLength(), trackingMode, trackingFidelity);
    }

    @Override
    public NbTrackingSpan createTrackingSpan(int start, int length, SpanTrackingMode trackingMode, TrackingFidelityMode trackingFidelity) {
        Parameters.notNull("trackingMode", trackingMode);
        Parameters.notNull("trackingFidelity", trackingFidelity);
        if (length < 0) {
            throw new IndexOutOfBoundsException();
        }

        NbTrackingPoint trackingStart = createTrackingPoint(start, trackingMode.getStartMode(), trackingFidelity);
        NbTrackingPoint trackingEnd = createTrackingPoint(start + length, trackingMode.getEndMode(), trackingFidelity);
        return new NbTrackingSpan(trackingStart, trackingEnd);
    }

    @NonNull NbTextVersion translate(@NonNull NormalizedTextChangeCollection changes) {
        Parameters.notNull("changes", changes);

        synchronized (lock) {
            if (this.changes != null) {
                throw new IllegalStateException();
            }

            LineTextCache nextLineData = lineData.applyChanges(changes);
            NbTextVersion nextVersion = new NbTextVersion(textBuffer, versionNumber + 1, nextLineData);
            this.changes = changes;
            this.next = nextVersion;
            return next;
        }
    }

}
