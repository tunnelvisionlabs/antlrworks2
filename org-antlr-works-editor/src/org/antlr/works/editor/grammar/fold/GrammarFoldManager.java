/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.fold;

import java.util.prefs.Preferences;
import org.antlr.netbeans.editor.fold.AbstractFoldManager;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.spi.editor.fold.FoldOperation;

/**
 *
 * @author Sam Harwell
 */
public class GrammarFoldManager extends AbstractFoldManager {

    @Override
    public void init(FoldOperation operation) {
        super.init(operation);

        Preferences preferences = MimeLookup.getLookup(GrammarEditorKit.GRAMMAR_MIME_TYPE).lookup(Preferences.class);
        //foldImportsPreset = prefs.getBoolean(SimpleValueNames.CODE_FOLDING_COLLAPSE_IMPORT, foldImportsPreset);
    }

}
