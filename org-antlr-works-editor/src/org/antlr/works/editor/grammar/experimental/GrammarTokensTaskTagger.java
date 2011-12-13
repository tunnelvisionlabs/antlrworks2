/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.antlr.works.editor.grammar.experimental;

import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.tagging.AbstractTagger;
import org.antlr.netbeans.editor.tagging.TaggedPositionRegion;
import org.antlr.netbeans.editor.text.NormalizedSnapshotPositionRegionCollection;

/**
 *
 * @author Sam Harwell
 */
public class GrammarTokensTaskTagger extends AbstractTagger<TokenTag> {

    @Override
    public Iterable<TaggedPositionRegion<TokenTag>> getTags(NormalizedSnapshotPositionRegionCollection regions) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
