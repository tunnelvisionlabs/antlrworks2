/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.antlr.works.editor.grammar.completion;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.atn.ATN;
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
        disable_global_context = true;
    }

    private static final IntervalSet WORDLIKE_TOKEN_TYPES =
        new IntervalSet() {{
            // keywords
            add(GrammarLexer.OPTIONS);
            add(GrammarLexer.TOKENS);
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
            add(GrammarLexer.TEMPLATE);
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
