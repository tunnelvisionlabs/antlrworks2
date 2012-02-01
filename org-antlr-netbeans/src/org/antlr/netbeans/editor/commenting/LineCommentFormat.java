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
public class LineCommentFormat extends CommentFormat {

    private final String startText;

    public LineCommentFormat(String startText) {
        this.startText = startText;
    }

    public String getStartText() {
        return startText;
    }

}
