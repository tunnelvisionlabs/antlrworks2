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
public interface TrackingPosition {

    public @NonNull VersionedDocument getVersionedDocument();

    public @NonNull TrackingFidelity getTrackingFidelity();

    public @NonNull TrackingPosition.Bias getBias();

    public @NonNull SnapshotPosition getPosition(@NonNull DocumentSnapshot snapshot);

    public int getOffset(@NonNull DocumentSnapshot snapshot);

    public int getOffset(@NonNull DocumentVersion version);

    public char getCharacter(@NonNull DocumentSnapshot snapshot);

    public enum Bias {
        Forward,
        Backward,
        ;

        @Override
        public String toString() {
            switch (this) {
            case Forward:
                return "→";

            case Backward:
                return "←";

            default:
                return "???";
            }
        }
    }
}
