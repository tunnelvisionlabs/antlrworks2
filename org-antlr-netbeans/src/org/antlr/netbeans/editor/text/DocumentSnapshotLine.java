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
public interface DocumentSnapshotLine {

    public @NonNull DocumentSnapshot getSnapshot();

    public int getLineNumber();

    public @NonNull SnapshotPosition getStart();

    public int getLength();

    public int getLengthIncludingLineBreak();

    public @NonNull SnapshotPosition getEnd();

    public @NonNull SnapshotPosition getEndIncludingLineBreak();

    public @NonNull SnapshotPositionRegion getRegion();

    public @NonNull SnapshotPositionRegion getRegionIncludingLineBreak();

    public int getLineBreakLength();

    public @NonNull String getText();

    public @NonNull String getTextIncludingLineBreak();

    public @NonNull String getLineBreakText();

}
