/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.experimental;

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
class TemplateTokensTaskTaggerSnapshot extends AbstractTokensTaskTaggerSnapshot<SimpleLexerState> {

    public TemplateTokensTaskTaggerSnapshot(@NonNull DocumentSnapshot snapshot) {
        super(snapshot);
    }

    protected TemplateTokensTaskTaggerSnapshot(@NonNull TemplateTokensTaskTaggerSnapshot reference, @NonNull DocumentSnapshot snapshot) {
        super(reference, snapshot);
    }

    @Override
    protected SimpleLexerState getStartState() {
        return SimpleLexerState.INITIAL;
    }

    @Override
    protected TokenSourceWithStateV4<SimpleLexerState> createLexer(CharStream input, SimpleLexerState startState) {
        TemplateLexerWrapper lexer = new TemplateLexerWrapper(input);
        startState.apply(lexer);
        return lexer;
    }

    @Override
    protected TokenSource getEffectiveTokenSource(TokenSourceWithStateV4<SimpleLexerState> lexer) {
        return new TemplateLexerWrapper(lexer.getInputStream());
    }

    @Override
    protected TemplateTokensTaskTaggerSnapshot translateToImpl(@NonNull DocumentSnapshot targetSnapshot) {
        return new TemplateTokensTaskTaggerSnapshot(this, targetSnapshot);
    }

    private static class TemplateLexerWrapper extends TemplateLexer implements TokenSourceWithStateV4<SimpleLexerState> {

        public TemplateLexerWrapper(CharStream input) {
            super(input);
        }

        @Override
        public SimpleLexerState getCurrentState() {
            return SimpleLexerState.createSimpleState(this);
        }

        @Override
        public void close() {
            // TODO: return this lexer to the lexer cache
        }
    }
}
