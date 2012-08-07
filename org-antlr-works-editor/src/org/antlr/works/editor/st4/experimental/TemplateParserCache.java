/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.experimental;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.works.editor.antlr4.completion.AbstractParserCache;
import org.antlr.works.editor.antlr4.parsing.DescriptiveErrorListener;

/**
 *
 * @author Sam Harwell
 */
public class TemplateParserCache extends AbstractParserCache<Token, TemplateParser> {
    public static final TemplateParserCache DEFAULT = new TemplateParserCache();

    @Override
    protected TemplateParser createParser(TokenStream<? extends Token> input) {
        TemplateParser parser = new TemplateParser(input);
        parser.getInterpreter().disable_global_context = true;
        return parser;
    }

    @Override
    public TemplateParser getParser(TokenStream<? extends Token> input) {
        TemplateParser result = super.getParser(input);
        result.removeErrorListeners();
        result.addErrorListener(DescriptiveErrorListener.INSTANCE);
        result.setBuildParseTree(false);
        result.setErrorHandler(new DefaultErrorStrategy<Token>());
        return result;
    }

}
