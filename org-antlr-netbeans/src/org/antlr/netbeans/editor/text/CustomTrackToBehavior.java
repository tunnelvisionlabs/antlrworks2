/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

/**
 *
 * @author sam
 */
public interface CustomTrackToBehavior {

    Span trackSpan(TrackingSpan customSpan, TextVersion currentVersion, TextVersion targetVersion, Span currentSpan);

}
