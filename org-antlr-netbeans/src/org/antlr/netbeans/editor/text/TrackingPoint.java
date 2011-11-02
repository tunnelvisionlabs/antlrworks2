/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

/**
 *
 * @author sam
 */
public interface TrackingPoint {

    public TextBuffer getTextBuffer();

    public TrackingFidelityMode getTrackingFidelity();

    public PointTrackingMode getTrackingMode();

    public SnapshotPoint getPoint(TextSnapshot snapshot);

    public int getPosition(TextSnapshot snapshot);

    public int getPosition(TextVersion version);

    public char getCharacter(TextSnapshot snapshot);

}
