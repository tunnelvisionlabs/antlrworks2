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
import org.antlr.v4.runtime.TokenStream;
import org.antlr.works.editor.antlr4.completion.AbstractParserCache;

/**
 *
 * @author Sam Harwell
 */
public class GrammarParserCache extends AbstractParserCache<GrammarParser> {
    public static final GrammarParserCache DEFAULT = new GrammarParserCache();

    @Override
    protected GrammarParser createParser(TokenStream input) {
        GrammarParser parser = new GrammarParser(input);
        parser.getInterpreter().disable_global_context = true;
        return parser;
    }

    @Override
    public GrammarParser getParser(TokenStream input) {
        GrammarParser result = super.getParser(input);
        result.setBuildParseTree(false);
        result.setErrorHandler(new DefaultErrorStrategy());
        return result;
    }

}
