/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
