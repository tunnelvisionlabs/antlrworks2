/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author sam
 */
public interface TextVersion {

    public @NonNull TextBuffer getTextBuffer();

    public int getLength();

    public int getVersionNumber();

    //public int getReiteratedVersionNumber();

    public @CheckForNull NormalizedTextChangeCollection getChanges();

    public @CheckForNull TextVersion getNext();

    public @NonNull TrackingSpan createCustomTrackingSpan(@NonNull Span span, @NonNull TrackingFidelityMode trackingFidelity, @NonNull CustomTrackToBehavior behavior);

    public @NonNull TrackingPoint createTrackingPoint(int position, @NonNull PointTrackingMode trackingMode);

    public @NonNull TrackingPoint createTrackingPoint(int position, @NonNull PointTrackingMode trackingMode, @NonNull TrackingFidelityMode trackingFidelity);

    public @NonNull TrackingSpan createTrackingSpan(@NonNull Span span, @NonNull SpanTrackingMode trackingMode);

    public @NonNull TrackingSpan createTrackingSpan(int start, int length, @NonNull SpanTrackingMode trackingMode);

    public @NonNull TrackingSpan createTrackingSpan(@NonNull Span span, @NonNull SpanTrackingMode trackingMode, @NonNull TrackingFidelityMode trackingFidelity);

    public @NonNull TrackingSpan createTrackingSpan(int start, int length, @NonNull SpanTrackingMode trackingMode, @NonNull TrackingFidelityMode trackingFidelity);
}
