/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.completion;

import org.antlr.netbeans.editor.text.TrackingPositionRegion;

/**
 * This interface represents an anchor region in the document. Anchors are used
 * by various features to improve performance by allowing the parser to analyze
 * a portion of a source file rather than start from the top for every
 * operation.
 *
 * @author Sam Harwell
 */
public interface Anchor {

    /**
     * The span of the anchor in the source file.
     *
     * @return The span of the anchor.
     */
    public TrackingPositionRegion getSpan();

    /**
     * The grammar rule associated with the anchor.
     *
     * @return The rule index.
     */
    public int getRule();
}
