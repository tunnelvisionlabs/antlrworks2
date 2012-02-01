/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.highlighting;

import java.util.Iterator;
import javax.swing.text.AttributeSet;
import org.netbeans.spi.editor.highlighting.HighlightsSequence;

public class HighlightsList implements HighlightsSequence {
    
    private final Iterator<Highlight> highlights;
    private Highlight current;

    public HighlightsList(Iterable<Highlight> highlights) {
        this.highlights = highlights.iterator();
    }

    @Override
    public boolean moveNext() {
        if (!highlights.hasNext()) {
            current = null;
            return false;
        }
        
        current = highlights.next();
        return true;
    }

    @Override
    public int getStartOffset() {
        return current.getStartOffset();
    }

    @Override
    public int getEndOffset() {
        return current.getEndOffset();
    }

    @Override
    public AttributeSet getAttributes() {
        return current.getAttributes();
    }
    
}
