/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

/**
 *
 * @author sam
 */
public interface TrackingSpan {

    public TextBuffer getTextBuffer();

    public TrackingFidelityMode getTrackingFidelity();

    public SpanTrackingMode getTrackingMode();

    public SnapshotPoint getStartPoint(TextSnapshot snapshot);

    public SnapshotPoint getEndPoint(TextSnapshot snapshot);

    public SnapshotSpan getSpan(TextSnapshot snapshot);

    public SnapshotSpan getSpan(TextVersion version);

    public String getText(TextSnapshot snapshot);

}
