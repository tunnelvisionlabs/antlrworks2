/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.completion.ReferenceAnchors;

/**
 *
 * @author Sam Harwell
 */
public final class GrammarReferenceAnchors extends ReferenceAnchors {

    private final int grammarType;

    public GrammarReferenceAnchors(int grammarType, Anchor previous, Anchor enclosing) {
        super(previous, enclosing);
        this.grammarType = grammarType;
    }

    public int getGrammarType() {
        return grammarType;
    }

}
