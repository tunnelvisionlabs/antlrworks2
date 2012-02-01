/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.parser;

import java.util.List;
import org.antlr.netbeans.editor.text.DocumentSnapshot;

/**
 *
 * @author Sam Harwell
 */
public class CompiledModelV3 extends CompiledModel {

    public CompiledModelV3(DocumentSnapshot snapshot, CompiledFileModelV3 result) {
        super(snapshot, result);
    }

    @Override
    public CompiledFileModelV3 getResult() {
        return (CompiledFileModelV3)super.getResult();
    }

    public GrammarWrapper getRootGrammar() {
        return getResult().getGrammar();
    }

    public List<CompiledFileModelV3> getImportedGrammarResults() {
        return getRootGrammar().getImportedGrammarResults();
    }

}
