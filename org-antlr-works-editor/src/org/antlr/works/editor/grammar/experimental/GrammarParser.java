/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class GrammarParser extends AbstractGrammarParser {

    public GrammarParser(TokenStream<? extends Token> input) {
        super(input);
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_qid, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_optionValue, version=0),
    })
    public static String getOptionValue(@NonNull OptionValueContext ctx) {
        QidContext qid = ctx.qid();
        if (qid != null) {
            return qid.getText();
        }

        TerminalNode<Token> node = ctx.INT();
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
