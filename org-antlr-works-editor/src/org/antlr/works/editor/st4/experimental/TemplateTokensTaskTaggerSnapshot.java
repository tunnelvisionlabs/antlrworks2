/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.experimental;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.WeakHashMap;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
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
    private static final Map<Thread, Reference<TemplateLexerWrapper>> lexerCache = new WeakHashMap<Thread, Reference<TemplateLexerWrapper>>();

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
    protected TokenSourceWithStateV4<Token, SimpleLexerState> createLexer(CharStream input, SimpleLexerState startState) {
        synchronized (lexerCache) {
            Reference<TemplateLexerWrapper> ref = lexerCache.get(Thread.currentThread());
            TemplateLexerWrapper lexer = ref != null ? ref.get() : null;
            if (lexer == null) {
                lexer = new TemplateLexerWrapper(input);
                lexerCache.put(Thread.currentThread(), new SoftReference<TemplateLexerWrapper>(lexer));
            } else {
                lexer.setInputStream(input);
            }

            startState.apply(lexer);
            return lexer;
        }
    }

    @Override
    protected TokenSource<Token> getEffectiveTokenSource(TokenSourceWithStateV4<Token, SimpleLexerState> lexer) {
        return new TemplateLexerWrapper(lexer.getInputStream());
    }

    @Override
    protected TemplateTokensTaskTaggerSnapshot translateToImpl(@NonNull DocumentSnapshot targetSnapshot) {
        return new TemplateTokensTaskTaggerSnapshot(this, targetSnapshot);
    }

    private static class TemplateLexerWrapper extends TemplateLexer implements TokenSourceWithStateV4<Token, SimpleLexerState> {

        public TemplateLexerWrapper(CharStream input) {
            super(input);
        }

        @Override
        public SimpleLexerState getCurrentState() {
            return SimpleLexerState.createSimpleState(this);
        }
    }
}
