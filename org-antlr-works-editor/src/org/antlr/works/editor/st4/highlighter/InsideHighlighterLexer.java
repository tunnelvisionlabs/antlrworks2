/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.highlighter;

import org.antlr.runtime.CharStream;

public class InsideHighlighterLexer extends InsideHighlighterLexerBase {
    private final TemplateHighlighterLexer aggregateLexer;

    public InsideHighlighterLexer(CharStream input, TemplateHighlighterLexer lexer) {
        super(input);
        aggregateLexer = lexer;
    }

    public TemplateHighlighterLexer getAggregateLexer() {
        return aggregateLexer;
    }

    protected char getOpenDelimiter() {
        return getAggregateLexer().getOpenDelimiter();
    }

    @Override
    protected char getCloseDelimiter() {
        return getAggregateLexer().getCloseDelimiter();
    }
}
