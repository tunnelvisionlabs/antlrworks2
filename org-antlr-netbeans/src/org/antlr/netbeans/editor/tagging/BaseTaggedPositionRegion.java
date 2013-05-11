/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.tagging;

import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 * @param <T>
 */
public class BaseTaggedPositionRegion<T extends Tag> implements TaggedPositionRegion<T> {
    private final SnapshotPositionRegion region;
    private final T tag;

    public BaseTaggedPositionRegion(@NonNull SnapshotPositionRegion region, @NonNull T tag) {
        this.region = region;
        this.tag = tag;
    }

    @Override
    public SnapshotPositionRegion getRegion() {
        return region;
    }

    @Override
    public T getTag() {
        return tag;
    }

}
