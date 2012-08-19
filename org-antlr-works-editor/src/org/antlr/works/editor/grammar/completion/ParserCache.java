/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.works.editor.antlr4.completion.AbstractParserCache;

/**
 *
 * @author Sam Harwell
 */
public class ParserCache extends AbstractParserCache<Token, CodeCompletionGrammarParser> {
    public static final ParserCache DEFAULT = new ParserCache();

    private ParserCache() {
    }

    @Override
    protected CodeCompletionGrammarParser createParser(TokenStream<? extends Token> input) {
        CodeCompletionGrammarParser parser = new CodeCompletionGrammarParser(input);
        parser.removeErrorListeners();
        return parser;
    }

}
