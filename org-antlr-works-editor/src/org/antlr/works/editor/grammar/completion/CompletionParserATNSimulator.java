/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.works.editor.antlr4.completion.AbstractCompletionParserATNSimulator;
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class CompletionParserATNSimulator extends AbstractCompletionParserATNSimulator {

    public CompletionParserATNSimulator(@NonNull Parser parser, ATN atn) {
        super(parser, atn);
        setPredictionMode(PredictionMode.SLL);
    }

    private static final IntervalSet WORDLIKE_TOKEN_TYPES =
        new IntervalSet() {{
            // keywords
            add(GrammarLexer.OPTIONS);
            add(GrammarLexer.TOKENS);
            add(GrammarLexer.CHANNELS);
            add(GrammarLexer.IMPORT);
            add(GrammarLexer.FRAGMENT);
            add(GrammarLexer.LEXER);
            add(GrammarLexer.PARSER);
            add(GrammarLexer.GRAMMAR);
            add(GrammarLexer.PROTECTED);
            add(GrammarLexer.PUBLIC);
            add(GrammarLexer.PRIVATE);
            add(GrammarLexer.RETURNS);
            add(GrammarLexer.LOCALS);
            add(GrammarLexer.THROWS);
            add(GrammarLexer.CATCH);
            add(GrammarLexer.FINALLY);
            add(GrammarLexer.MODE);
            // atoms
            add(GrammarLexer.RULE_REF);
            add(GrammarLexer.TOKEN_REF);
            add(GrammarLexer.ID);
            // special
            add(GrammarLexer.ARG_ACTION_WORD);
            add(GrammarLexer.ACTION_REFERENCE);
            add(GrammarLexer.ACTION_WORD);
        }};

    @Override
    protected IntervalSet getWordlikeTokenTypes() {
        return WORDLIKE_TOKEN_TYPES;
    }

}
