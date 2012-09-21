/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.completion;

/**
 *
 * @author Sam Harwell
 */
public class ReferenceAnchors {

    private final Anchor previous;
    private final Anchor enclosing;

    public ReferenceAnchors(Anchor previous, Anchor enclosing) {
        this.previous = previous;
        this.enclosing = enclosing;
    }

    public Anchor getPrevious() {
        return previous;
    }

    public Anchor getEnclosing() {
        return enclosing;
    }

}
