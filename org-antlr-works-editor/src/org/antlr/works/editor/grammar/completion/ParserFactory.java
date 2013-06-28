/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import org.antlr.v4.runtime.TokenStream;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class ParserFactory {
    public static final ParserFactory DEFAULT = new ParserFactory();

    @NonNull
    protected CodeCompletionGrammarParser createParser(@NonNull TokenStream input) {
        CodeCompletionGrammarParser parser = new CodeCompletionGrammarParser(input);
        parser.removeErrorListeners();
        return parser;
    }

    @NonNull
    public CodeCompletionGrammarParser getParser(@NonNull TokenStream input) {
        return createParser(input);
    }
}
