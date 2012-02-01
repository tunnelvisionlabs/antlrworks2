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
import org.antlr.v4.runtime.TokenStream;
import org.antlr.works.editor.antlr4.completion.AbstractParserCache;

/**
 *
 * @author Sam Harwell
 */
public class TemplateParserCache extends AbstractParserCache<TemplateParser> {
    public static final TemplateParserCache DEFAULT = new TemplateParserCache();

    @Override
    protected TemplateParser createParser(TokenStream input) {
        TemplateParser parser = new TemplateParser(input);
        parser.getInterpreter().disable_global_context = true;
        return parser;
    }

    @Override
    public TemplateParser getParser(TokenStream input) {
        TemplateParser result = super.getParser(input);
        result.setBuildParseTree(false);
        result.setErrorHandler(new DefaultErrorStrategy());
        return result;
    }

}
