/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel;

import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Sam Harwell
 */
public interface CodeElementPositionRegion {

    @NonNull
    FileObject getFileObject();

    @NonNull
    OffsetRegion getOffsetRegion();

    @NonNull
    SnapshotPositionRegion getSnapshotPositionRegion(boolean translateToOpenDocument);

}
