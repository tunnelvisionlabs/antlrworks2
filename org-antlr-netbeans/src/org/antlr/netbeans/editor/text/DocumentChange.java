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
public interface DocumentChange {

    int getDelta();

    int getLineCountDelta();

    int getOldOffset();

    int getOldLength();

    int getOldEnd();

    @NonNull OffsetRegion getOldRegion();

    @NonNull String getOldText();

    int getNewOffset();

    int getNewLength();

    int getNewEnd();

    @NonNull OffsetRegion getNewRegion();

    @NonNull String getNewText();

}
