/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.navigation;

import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.works.editor.grammar.parser.CompiledModel;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "LBL_ParserRules=Parser Rules",
    "LBL_LexerRules=Lexer Rules"
})
public abstract class RuleScanner {

    public Description scan(CompiledModel model) {
        GrammarNode.GrammarNodeDescription rootDescription = scanImpl(model);
        return rootDescription;
    }

    protected abstract GrammarNode.GrammarNodeDescription scanImpl(CompiledModel model);

}
