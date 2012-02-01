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
import org.antlr.v4.tool.Grammar;

/**
 *
 * @author Sam Harwell
 */
public class CompiledModelV4 extends CompiledModel {

    public CompiledModelV4(DocumentSnapshot snapshot, CompiledFileModelV4 result) {
        super(snapshot, result);
    }

    @Override
    public CompiledFileModelV4 getResult() {
        return (CompiledFileModelV4)super.getResult();
    }

    public Grammar getRootGrammar() {
        return getResult().getGrammar();
    }

    public List<CompiledFileModelV4> getImportedGrammarResults() {
        return getResult().getImportedGrammarResults();
    }

}
