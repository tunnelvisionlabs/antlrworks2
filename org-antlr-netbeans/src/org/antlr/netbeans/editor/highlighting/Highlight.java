/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.highlighting;

import javax.swing.text.AttributeSet;

public final class Highlight {
    private final int startOffset;
    private final int endOffset;
    private final AttributeSet attributes;
    
    public Highlight(int startOffset, int endOffset, AttributeSet attributes) {
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.attributes = attributes;
    }
    
    public int getStartOffset() {
        return startOffset;
    }
    
    public int getEndOffset() {
        return endOffset;
    }
    
    public AttributeSet getAttributes() {
        return attributes;
    }
}
