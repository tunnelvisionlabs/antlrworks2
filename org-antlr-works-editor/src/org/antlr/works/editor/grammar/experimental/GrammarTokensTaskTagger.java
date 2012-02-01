/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.tagging.AbstractTagger;
import org.antlr.netbeans.editor.tagging.TaggedPositionRegion;
import org.antlr.netbeans.editor.text.NormalizedSnapshotPositionRegionCollection;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author Sam Harwell
 */
public class GrammarTokensTaskTagger extends AbstractTagger<TokenTag<Token>> {

    @Override
    public Iterable<TaggedPositionRegion<TokenTag<Token>>> getTags(NormalizedSnapshotPositionRegionCollection regions) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
