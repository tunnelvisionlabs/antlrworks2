/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.tagging;

import org.antlr.netbeans.editor.text.NormalizedSnapshotPositionRegionCollection;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 * @param <T>
 */
public interface Tagger<T extends Tag> {

    @NonNull Iterable<TaggedPositionRegion<T>> getTags(@NonNull NormalizedSnapshotPositionRegionCollection regions);

    void addTagsChangeListener(@NonNull SnapshotPositionRegionEventListener listener);

    void removeTagsChangeListener(@NonNull SnapshotPositionRegionEventListener listener);

}
