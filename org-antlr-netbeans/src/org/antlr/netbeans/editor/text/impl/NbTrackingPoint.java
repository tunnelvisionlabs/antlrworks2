/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text.impl;

import org.antlr.netbeans.editor.text.PointTrackingMode;
import org.antlr.netbeans.editor.text.SnapshotPoint;
import org.antlr.netbeans.editor.text.TextBuffer;
import org.antlr.netbeans.editor.text.TextSnapshot;
import org.antlr.netbeans.editor.text.TextVersion;
import org.antlr.netbeans.editor.text.TrackingFidelityMode;
import org.antlr.netbeans.editor.text.TrackingPoint;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public abstract class NbTrackingPoint implements TrackingPoint {
    @NonNull
    private final NbTextVersion textVersion;
    private final int position;
    @NonNull
    private final PointTrackingMode trackingMode;

    private final Object lock = new Object();

    @NonNull
    private TextVersion cachedVersion;
    private int cachedPosition;

    public NbTrackingPoint(@NonNull NbTextVersion textVersion, int position, @NonNull PointTrackingMode trackingMode) {
        Parameters.notNull("textVersion", textVersion);
        Parameters.notNull("trackingMode", trackingMode);
        if (position < 0 || position > textVersion.getLength()) {
            throw new IndexOutOfBoundsException();
        }

        this.textVersion = textVersion;
        this.position = position;
        this.trackingMode = trackingMode;

        this.cachedVersion = textVersion;
        this.cachedPosition = position;
    }

    public final @NonNull NbTextVersion getTextVersion() {
        return textVersion;
    }

    @Override
    public final TextBuffer getTextBuffer() {
        return textVersion.getTextBuffer();
    }

    @Override
    public final PointTrackingMode getTrackingMode() {
        return trackingMode;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public abstract TrackingFidelityMode getTrackingFidelity();

    protected abstract int getPosition(@NonNull TextVersion version, @NonNull TextVersion cachedVersion, int cachedPosition);

    @Override
    public int getPosition(TextVersion version) {
        TextVersion referenceVersion;
        int referencePosition;

        synchronized (lock) {
            referenceVersion = cachedVersion;
            referencePosition = cachedPosition;
        }

        int updatedPosition = getPosition(version, referenceVersion, referencePosition);

        synchronized (lock) {
            cachedVersion = version;
            cachedPosition = updatedPosition;
        }

        return updatedPosition;
    }

    @Override
    public SnapshotPoint getPoint(TextSnapshot snapshot) {
        Parameters.notNull("snapshot", snapshot);
        return new SnapshotPoint(snapshot, getPosition(snapshot.getVersion()));
    }

    @Override
    public int getPosition(TextSnapshot snapshot) {
        Parameters.notNull("snapshot", snapshot);
        return getPosition(snapshot.getVersion());
    }

    @Override
    public char getCharacter(TextSnapshot snapshot) {
        Parameters.notNull("snapshot", snapshot);
        return getPoint(snapshot).getCharacter();
    }

}
