/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.highlighting;

import org.antlr.netbeans.editor.text.OffsetRegion;

public final class ParseRequest<TState> {
    private final OffsetRegion span;
    private final TState state;

    public ParseRequest(OffsetRegion span, TState state) {
        this.span = span;
        this.state = state;
    }
    
    public OffsetRegion getRegion() {
        return span;
    }
    
    public TState getState() {
        return state;
    }
}
