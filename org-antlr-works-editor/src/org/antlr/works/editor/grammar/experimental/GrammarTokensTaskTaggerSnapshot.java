/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.works.editor.antlr4.classification.AbstractTokensTaskTaggerSnapshot;
import org.antlr.works.editor.antlr4.classification.SimpleLexerState;
import org.antlr.works.editor.antlr4.highlighting.TokenSourceWithStateV4;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
class GrammarTokensTaskTaggerSnapshot extends AbstractTokensTaskTaggerSnapshot<SimpleLexerState> {

    public GrammarTokensTaskTaggerSnapshot(@NonNull DocumentSnapshot snapshot) {
        super(snapshot);
    }

    protected GrammarTokensTaskTaggerSnapshot(@NonNull GrammarTokensTaskTaggerSnapshot reference, @NonNull DocumentSnapshot snapshot) {
        super(reference, snapshot);
    }

    @Override
    protected SimpleLexerState getStartState() {
        return SimpleLexerState.INITIAL;
    }

    @Override
    protected TokenSourceWithStateV4<SimpleLexerState> createLexer(CharStream input, SimpleLexerState startState) {
        GrammarLexerWrapper lexer = new GrammarLexerWrapper(input);
        startState.apply(lexer);
        return lexer;
    }

    @Override
    protected TokenSource getEffectiveTokenSource(TokenSourceWithStateV4<SimpleLexerState> lexer) {
        return new GrammarLexerWrapper(lexer.getInputStream());
    }

    @Override
    protected GrammarTokensTaskTaggerSnapshot translateToImpl(@NonNull DocumentSnapshot targetSnapshot) {
        return new GrammarTokensTaskTaggerSnapshot(this, targetSnapshot);
    }

    private static class GrammarLexerWrapper extends GrammarLexer implements TokenSourceWithStateV4<SimpleLexerState> {

        public GrammarLexerWrapper(CharStream input) {
            super(input);
        }

        @Override
        public SimpleLexerState getCurrentState() {
            return SimpleLexerState.createSimpleState(this);
        }

        @Override
        public void close() {
            // this lexer uses the normal shared ATN so there's need to do something special here
        }

    }
}
