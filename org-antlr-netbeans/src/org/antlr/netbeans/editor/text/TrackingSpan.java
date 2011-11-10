/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author sam
 */
public interface TrackingSpan {

    public @NonNull TextBuffer getTextBuffer();

    public @NonNull TrackingFidelityMode getTrackingFidelity();

    public @NonNull SpanTrackingMode getTrackingMode();

    public @NonNull SnapshotPoint getStartPoint(@NonNull TextSnapshot snapshot);

    public @NonNull SnapshotPoint getEndPoint(@NonNull TextSnapshot snapshot);

    public @NonNull SnapshotSpan getSpan(@NonNull TextSnapshot snapshot);

    public @NonNull SnapshotSpan getSpan(@NonNull TextVersion version);

    public @NonNull String getText(@NonNull TextSnapshot snapshot);

}
