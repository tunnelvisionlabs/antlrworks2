/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author sam
 */
public interface CustomTrackToBehavior {

    @NonNull Span trackSpan(@NonNull TrackingSpan customSpan, @NonNull TextVersion currentVersion, @NonNull TextVersion targetVersion, @NonNull Span currentSpan);

}
