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
public interface CustomTrackToBehavior {

    @NonNull OffsetRegion trackRegion(@NonNull TrackingPositionRegion customRegion, @NonNull DocumentVersion currentVersion, @NonNull DocumentVersion targetVersion, @NonNull OffsetRegion currentRegion);

}
