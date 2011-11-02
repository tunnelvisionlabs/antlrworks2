/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

/**
 *
 * @author sam
 */
public interface TextVersion {

    public TextBuffer getTextBuffer();

    public int getLength();

    public int getVersionNumber();

    //public int getReiteratedVersionNumber();

    public NormalizedTextChangeCollection getChanges();

    public TextVersion getNext();

    public TrackingSpan createCustomTrackingSpan(Span span, TrackingFidelityMode trackingFidelity, CustomTrackToBehavior behavior);

    public TrackingPoint createTrackingPoint(int position, PointTrackingMode trackingMode);

    public TrackingPoint createTrackingPoint(int position, PointTrackingMode trackingMode, TrackingFidelityMode trackingFidelity);

    public TrackingSpan createTrackingSpan(Span span, SpanTrackingMode trackingMode);

    public TrackingSpan createTrackingSpan(int start, int length, SpanTrackingMode trackingMode);

    public TrackingSpan createTrackingSpan(Span span, SpanTrackingMode trackingMode, TrackingFidelityMode trackingFidelity);

    public TrackingSpan createTrackingSpan(int start, int length, SpanTrackingMode trackingMode, TrackingFidelityMode trackingFidelity);
}
