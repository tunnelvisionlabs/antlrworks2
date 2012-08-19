/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.antlr4.completion.AbstractForestParser;
import org.antlr.works.editor.antlr4.completion.CodeCompletionParser;

/**
 *
 * @author Sam Harwell
 */
public class GrammarForestParser extends AbstractForestParser {
    public static final GrammarForestParser INSTANCE = new GrammarForestParser();

    protected GrammarForestParser() {
    }

    @Override
    protected RuleContext<Token> parseImpl(CodeCompletionParser parser) {
        return ((CodeCompletionGrammarParser)parser).rules();
    }

}
