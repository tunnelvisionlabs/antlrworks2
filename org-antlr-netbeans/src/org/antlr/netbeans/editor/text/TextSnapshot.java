/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

/**
 *
 * @author sam
 */
public interface TextSnapshot {

    public int length();

    public char charAt(int index);

    public CharSequence subSequence(int start, int end);

    public String getText();

    public int getLineCount();

    public Iterable<TextSnapshotLine> getLines();

    public TextBuffer getTextBuffer();

    public TextVersion getVersion();

    public TrackingPoint createTrackingPoint(int position, PointTrackingMode trackingMode);

    public TrackingPoint createTrackingPoint(int position, PointTrackingMode trackingMode, TrackingFidelityMode trackingFidelity);

    public TrackingSpan createTrackingSpan(Span span, SpanTrackingMode trackingMode);

    public TrackingSpan createTrackingSpan(int start, int length, SpanTrackingMode trackingMode);

    public TrackingSpan createTrackingSpan(Span span, SpanTrackingMode trackingMode, TrackingFidelityMode trackingFidelity);

    public TrackingSpan createTrackingSpan(int start, int length, SpanTrackingMode trackingMode, TrackingFidelityMode trackingFidelity);

    public TextSnapshotLine getLineFromLineNumber(int lineNumber);

    public TextSnapshotLine getLineFromPosition(int position);

    public int getLineNumberFromPosition(int position);
}
