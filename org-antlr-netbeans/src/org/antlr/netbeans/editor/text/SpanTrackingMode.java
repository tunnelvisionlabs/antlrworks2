/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public enum SpanTrackingMode {

    EdgeExclusive,
    EdgeInclusive,
    EdgePositive,
    EdgeNegative,
    Custom,
    ;

    public static SpanTrackingMode fromPointTrackingMode(PointTrackingMode startMode, PointTrackingMode endMode) {
        Parameters.notNull("startMode", startMode);
        Parameters.notNull("endMode", endMode);

        if (startMode == PointTrackingMode.Positive) {
            return (endMode == PointTrackingMode.Positive) ? EdgePositive : EdgeExclusive;
        } else {
            return (endMode == PointTrackingMode.Positive) ? EdgeInclusive : EdgeNegative;
        }
    }

    public PointTrackingMode getStartMode() {
        switch (this) {
        case EdgeExclusive:
        case EdgePositive:
            return PointTrackingMode.Positive;

        case EdgeInclusive:
        case EdgeNegative:
            return PointTrackingMode.Negative;

        case Custom:
        default:
            return null;
        }
    }

    public PointTrackingMode getEndMode() {
        switch (this) {
        case EdgeInclusive:
        case EdgePositive:
            return PointTrackingMode.Positive;

        case EdgeExclusive:
        case EdgeNegative:
            return PointTrackingMode.Negative;

        case Custom:
        default:
            return null;
        }
    }

    @Override
    public String toString() {
        switch (this) {
        case EdgeExclusive:
            return "→←";

        case EdgeInclusive:
            return "←→";

        case EdgeNegative:
            return "←←";

        case EdgePositive:
            return "→→";

        case Custom:
            return "custom";

        default:
            return "???";
        }
    }
}
