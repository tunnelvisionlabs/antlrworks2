/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

/**
 *
 * @author sam
 */
public enum PointTrackingMode {

    Positive,
    Negative,
    ;

    @Override
    public String toString() {
        switch (this) {
        case Positive:
            return "→";

        case Negative:
            return "←";

        default:
            return "???";
        }
    }
}
