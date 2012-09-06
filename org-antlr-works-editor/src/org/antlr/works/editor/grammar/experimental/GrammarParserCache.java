/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.works.editor.antlr4.completion.AbstractParserCache;
import org.antlr.works.editor.antlr4.parsing.DescriptiveErrorListener;

/**
 *
 * @author Sam Harwell
 */
public class GrammarParserCache extends AbstractParserCache<Token, GrammarParser> {
    public static final GrammarParserCache DEFAULT = new GrammarParserCache();

    @Override
    protected GrammarParser createParser(TokenStream<? extends Token> input) {
        GrammarParser parser = new GrammarParser(input);
        return parser;
    }

    @Override
    public GrammarParser getParser(TokenStream<? extends Token> input) {
        GrammarParser result = super.getParser(input);
        result.getInterpreter().disable_global_context = false;
        result.removeErrorListeners();
        result.addErrorListener(DescriptiveErrorListener.INSTANCE);
        result.setBuildParseTree(false);
        result.setErrorHandler(new DefaultErrorStrategy<Token>());
        return result;
    }
}
