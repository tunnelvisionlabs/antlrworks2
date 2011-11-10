/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author sam
 */
public interface TextSnapshot {

    public int length();

    public char charAt(int index);

    public @NonNull CharSequence subSequence(int start, int end);

    public @NonNull String getText();

    public int getLineCount();

    public @NonNull Iterable<TextSnapshotLine> getLines();

    public @NonNull TextBuffer getTextBuffer();

    public @NonNull TextVersion getVersion();

    public @NonNull TrackingPoint createTrackingPoint(int position, @NonNull PointTrackingMode trackingMode);

    public @NonNull TrackingPoint createTrackingPoint(int position, @NonNull PointTrackingMode trackingMode, @NonNull TrackingFidelityMode trackingFidelity);

    public @NonNull TrackingSpan createTrackingSpan(@NonNull Span span, @NonNull SpanTrackingMode trackingMode);

    public @NonNull TrackingSpan createTrackingSpan(int start, int length, @NonNull SpanTrackingMode trackingMode);

    public @NonNull TrackingSpan createTrackingSpan(@NonNull Span span, @NonNull SpanTrackingMode trackingMode, @NonNull TrackingFidelityMode trackingFidelity);

    public @NonNull TrackingSpan createTrackingSpan(int start, int length, @NonNull SpanTrackingMode trackingMode, @NonNull TrackingFidelityMode trackingFidelity);

    public @NonNull TextSnapshotLine getLineFromLineNumber(int lineNumber);

    public @NonNull TextSnapshotLine getLineFromPosition(int position);

    public int getLineNumberFromPosition(int position);
}
