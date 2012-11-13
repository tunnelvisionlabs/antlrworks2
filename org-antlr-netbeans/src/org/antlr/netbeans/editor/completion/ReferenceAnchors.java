/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.completion;

import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NullAllowed;

/**
 *
 * @author Sam Harwell
 */
public class ReferenceAnchors {

    private final Anchor previous;
    private final Anchor enclosing;

    /**
     *
     * @param previous The last anchor which ended before the start of the
     * enclosing anchor.
     * @param enclosing The last anchor which started before a position of
     * interest.
     */
    public ReferenceAnchors(@NullAllowed Anchor previous, @NullAllowed Anchor enclosing) {
        this.previous = previous;
        this.enclosing = enclosing;
    }

    /**
     * The previous anchor is the last anchor which ended before the start of
     * the enclosing anchor.
     *
     * @return The previous anchor.
     */
    @CheckForNull
    public Anchor getPrevious() {
        return previous;
    }

    /**
     * The enclosing anchor is the last anchor which started before the position
     * of interest.
     *
     * @return The enclosing anchor.
     */
    @CheckForNull
    public Anchor getEnclosing() {
        return enclosing;
    }
}
