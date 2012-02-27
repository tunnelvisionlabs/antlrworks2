/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.text;

import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public interface DocumentVersion {

    public @NonNull VersionedDocument getVersionedDocument();

    public @NonNull DocumentSnapshot getSnapshot();

    public int getLength();

    public int getVersionNumber();

    public @CheckForNull NormalizedDocumentChangeCollection getChanges();

    public @CheckForNull DocumentVersion getNext();

    public @NonNull TrackingPositionRegion createCustomTrackingRegion(@NonNull OffsetRegion region, @NonNull TrackingFidelity fidelity, @NonNull CustomTrackToBehavior behavior);

    public @NonNull TrackingPosition createTrackingPosition(int offset, @NonNull TrackingPosition.Bias bias);

    public @NonNull TrackingPosition createTrackingPosition(int offset, @NonNull TrackingPosition.Bias bias, @NonNull TrackingFidelity fidelity);

    public @NonNull TrackingPositionRegion createTrackingRegion(@NonNull OffsetRegion region, @NonNull TrackingPositionRegion.Bias bias);

    public @NonNull TrackingPositionRegion createTrackingRegion(int start, int length, @NonNull TrackingPositionRegion.Bias bias);

    public @NonNull TrackingPositionRegion createTrackingRegion(@NonNull OffsetRegion region, @NonNull TrackingPositionRegion.Bias bias, @NonNull TrackingFidelity fidelity);

    public @NonNull TrackingPositionRegion createTrackingRegion(int start, int length, @NonNull TrackingPositionRegion.Bias bias, @NonNull TrackingFidelity fidelity);
}
