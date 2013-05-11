/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.text;

import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public interface DocumentSnapshot {

    public int length();

    public char charAt(int index);

    public @NonNull CharSequence subSequence(int start, int end);

    public @NonNull String getText();

    public int getLineCount();

    public @NonNull Iterable<DocumentSnapshotLine> getLines();

    public @NonNull VersionedDocument getVersionedDocument();

    public @NonNull DocumentVersion getVersion();

    public @NonNull TrackingPosition createTrackingPosition(int offset, @NonNull TrackingPosition.Bias bias);

    public @NonNull TrackingPosition createTrackingPosition(int offset, @NonNull TrackingPosition.Bias bias, @NonNull TrackingFidelity fidelity);

    public @NonNull TrackingPositionRegion createTrackingRegion(@NonNull OffsetRegion region, @NonNull TrackingPositionRegion.Bias bias);

    public @NonNull TrackingPositionRegion createTrackingRegion(int start, int length, @NonNull TrackingPositionRegion.Bias bias);

    public @NonNull TrackingPositionRegion createTrackingRegion(@NonNull OffsetRegion region, @NonNull TrackingPositionRegion.Bias bias, @NonNull TrackingFidelity fidelity);

    public @NonNull TrackingPositionRegion createTrackingRegion(int start, int length, @NonNull TrackingPositionRegion.Bias bias, @NonNull TrackingFidelity fidelity);

    /**
     * @param lineNumber
     * @return
     * @throws IndexOutOfBoundsException
     *      if {@code lineNumber < 0}
     *      or
     *      {@code lineNumber >= }{@link #getLineCount getLineCount()}
     */
    public @NonNull DocumentSnapshotLine findLineFromLineNumber(int lineNumber);

    public @NonNull DocumentSnapshotLine findLineFromOffset(int offset);

    public int findLineNumber(int offset);

}
