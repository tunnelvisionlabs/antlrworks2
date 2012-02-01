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
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public interface TrackingPositionRegion {

    public @NonNull VersionedDocument getVersionedDocument();

    public @NonNull TrackingFidelity getTrackingFidelity();

    public @NonNull TrackingPositionRegion.Bias getBias();

    public @NonNull SnapshotPosition getStartPosition(@NonNull DocumentSnapshot snapshot);

    public @NonNull SnapshotPosition getEndPosition(@NonNull DocumentSnapshot snapshot);

    public @NonNull SnapshotPositionRegion getRegion(@NonNull DocumentSnapshot snapshot);

    public @NonNull SnapshotPositionRegion getRegion(@NonNull DocumentVersion version);

    public @NonNull String getText(@NonNull DocumentSnapshot snapshot);

    public enum Bias {
        Exclusive,
        Inclusive,
        Forward,
        Backward,
        Custom,
        ;

        public static @NonNull Bias fromPositionBias(@NonNull TrackingPosition.Bias startBias, @NonNull TrackingPosition.Bias endBias) {
            Parameters.notNull("startBias", startBias);
            Parameters.notNull("endBias", endBias);

            if (startBias == TrackingPosition.Bias.Forward) {
                return (endBias == TrackingPosition.Bias.Forward) ? Forward : Exclusive;
            } else {
                return (endBias == TrackingPosition.Bias.Forward) ? Inclusive : Backward;
            }
        }

        public @CheckForNull TrackingPosition.Bias getStartBias() {
            switch (this) {
            case Exclusive:
            case Forward:
                return TrackingPosition.Bias.Forward;

            case Inclusive:
            case Backward:
                return TrackingPosition.Bias.Backward;

            case Custom:
            default:
                return null;
            }
        }

        public @CheckForNull TrackingPosition.Bias getEndBias() {
            switch (this) {
            case Inclusive:
            case Forward:
                return TrackingPosition.Bias.Forward;

            case Exclusive:
            case Backward:
                return TrackingPosition.Bias.Backward;

            case Custom:
            default:
                return null;
            }
        }

        @Override
        public String toString() {
            switch (this) {
            case Exclusive:
                return "→←";

            case Inclusive:
                return "←→";

            case Backward:
                return "←←";

            case Forward:
                return "→→";

            case Custom:
                return "custom";

            default:
                return "???";
            }
        }
    }
}
