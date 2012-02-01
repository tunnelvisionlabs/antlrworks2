/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.commenting;

/**
 *
 * @author Sam Harwell
 */
public class BlockCommentFormat extends CommentFormat {

    private final String startText;
    private final String endText;
    private final boolean allowNesting;

    public BlockCommentFormat(String startText, String endText) {
        this(startText, endText, false);
    }

    public BlockCommentFormat(String startText, String endText, boolean allowNesting) {
        this.startText = startText;
        this.endText = endText;
        this.allowNesting = allowNesting;
    }

    public String getStartText() {
        return startText;
    }

    public String getEndText() {
        return endText;
    }

    public boolean isAllowNesting() {
        return allowNesting;
    }

}
