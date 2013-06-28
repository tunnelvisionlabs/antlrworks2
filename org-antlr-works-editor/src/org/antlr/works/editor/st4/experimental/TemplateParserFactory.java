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
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.works.editor.antlr4.parsing.DescriptiveErrorListener;
import org.antlr.works.editor.st4.experimental.generated.TemplateParser;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class TemplateParserFactory {
    public static final TemplateParserFactory DEFAULT = new TemplateParserFactory();

    @NonNull
    protected TemplateParser createParser(@NonNull TokenStream input) {
        TemplateParser parser = new TemplateParser(input);
        return parser;
    }

    @NonNull
    public TemplateParser getParser(@NonNull TokenStream input) {
        TemplateParser result = createParser(input);
        result.getInterpreter().setPredictionMode(PredictionMode.LL);
        result.removeErrorListeners();
        result.addErrorListener(DescriptiveErrorListener.INSTANCE);
        result.setBuildParseTree(false);
        result.setErrorHandler(new DefaultErrorStrategy());
        return result;
    }

}
