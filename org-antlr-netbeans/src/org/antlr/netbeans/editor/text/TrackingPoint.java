/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author sam
 */
public interface TrackingPoint {

    public @NonNull TextBuffer getTextBuffer();

    public @NonNull TrackingFidelityMode getTrackingFidelity();

    public @NonNull PointTrackingMode getTrackingMode();

    public @NonNull SnapshotPoint getPoint(@NonNull TextSnapshot snapshot);

    public int getPosition(@NonNull TextSnapshot snapshot);

    public int getPosition(@NonNull TextVersion version);

    public char getCharacter(@NonNull TextSnapshot snapshot);

}
