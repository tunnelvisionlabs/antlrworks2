/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public final class NormalizedSnapshotPositionRegionCollection extends ArrayList<SnapshotPositionRegion> {

    public NormalizedSnapshotPositionRegionCollection() {
        super();
    }

    public NormalizedSnapshotPositionRegionCollection(@NonNull SnapshotPositionRegion region) {
        super(Collections.singletonList(region));
    }

    public NormalizedSnapshotPositionRegionCollection(@NonNull Collection<SnapshotPositionRegion> collection) {
        super(collection);
    }

    public NormalizedSnapshotPositionRegionCollection(@NonNull DocumentSnapshot snapshot, @NonNull Collection<OffsetRegion> spans) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
