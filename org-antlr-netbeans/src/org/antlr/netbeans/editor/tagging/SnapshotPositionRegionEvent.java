/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.tagging;

import java.util.EventObject;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class SnapshotPositionRegionEvent extends EventObject {
    private final SnapshotPositionRegion region;

    public SnapshotPositionRegionEvent(Object source, @NonNull SnapshotPositionRegion region) {
        super(source);
        this.region = region;
    }

    public @NonNull SnapshotPositionRegion getRegion() {
        return region;
    }
}
