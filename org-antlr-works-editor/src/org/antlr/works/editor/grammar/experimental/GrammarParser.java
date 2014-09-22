/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.OptionValueContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.QidContext;
import org.netbeans.api.annotations.common.NullAllowed;

/**
 *
 * @author Sam Harwell
 */
public class GrammarParser extends AbstractGrammarParser {

    public GrammarParser(TokenStream input) {
        super(input);
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_optionValue, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_qid, version=1, dependents=Dependents.DESCENDANTS),
    })
    public static String getOptionValue(@NullAllowed OptionValueContext ctx) {
        if (ctx == null) {
            return null;
        }

        QidContext qid = ctx.qid();
        if (qid != null) {
            return qid.getText();
        }

        TerminalNode node = ctx.INT();
        if (node != null) {
            return node.getSymbol().getText();
        }

        node = ctx.STAR();
        if (node != null) {
            return node.getSymbol().getText();
        }

        node = ctx.STRING_LITERAL();
        if (node != null) {
            String result = node.getSymbol().getText();
            result = result.substring(0, result.length() - 1);
            result = result.replace("\\\"", "\"");
            return result;
        }

        return null;
    }

}
