/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.highlighter;

import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.works.editor.antlr4.highlighting.TokenSourceWithStateV4;

/**
 *
 * @author Sam Harwell
 */
public class GrammarHighlighterLexerWrapper implements TokenSourceWithStateV4<GrammarHighlighterLexerState> {
    private static final Map<GrammarHighlighterLexerState, GrammarHighlighterLexerState> sharedStates =
        new HashMap<>();

    private final GrammarHighlighterLexer grammarLexer;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public GrammarHighlighterLexerWrapper(CharStream input, GrammarHighlighterLexerState state) {
        this.grammarLexer = new GrammarHighlighterLexer(input);
        setState(input, state);
    }

    public GrammarHighlighterLexer getLexer() {
        return grammarLexer;
    }

    @Override
    public String getSourceName() {
        return "Grammar Highlighter";
    }

    @Override
    public GrammarHighlighterLexerState getCurrentState() {
        if (grammarLexer._modeStack == null) {
            return getCachedState(grammarLexer.isInOptions(), grammarLexer.isInTokens(), grammarLexer.getRuleType(), grammarLexer._mode, null);
        }

        return getCachedState(grammarLexer.isInOptions(), grammarLexer.isInTokens(), grammarLexer.getRuleType(), grammarLexer._mode, grammarLexer._modeStack.toArray());
    }

    @Override
    public void close() {
        // this lexer uses the normal shared ATN so there's need to do something special here
    }

    private static GrammarHighlighterLexerState getCachedState(boolean inOptions, boolean inTokens, int ruleType, int mode, int[] modeStack) {
        GrammarHighlighterLexerState state = new GrammarHighlighterLexerState(inOptions, inTokens, ruleType, mode, modeStack);

        synchronized (sharedStates) {
            GrammarHighlighterLexerState cached = sharedStates.get(state);
            if (cached != null) {
                return cached;
            }

            sharedStates.put(state, state);
            return state;
        }
    }

    public void setState(CharStream input, GrammarHighlighterLexerState state) {
        grammarLexer.setInputStream(input);
        grammarLexer._mode = state.getMode();
        grammarLexer.setInOptions(state.isInOptions());
        grammarLexer.setInTokens(state.isInTokens());
        grammarLexer.setRuleType(state.getRuleType());

        grammarLexer._modeStack.clear();
        if (state.getModeStack() != null && state.getModeStack().length > 0) {
            grammarLexer._modeStack.addAll(state.getModeStack());
        }
    }

    @Override
    public Token nextToken() {
        Token token;
        do {
            token = nextTokenCore();
        } while (token == null || token.getType() == GrammarHighlighterLexer.NEWLINE);

        return token;
    }

    private Token nextTokenCore() {
        return grammarLexer.nextToken();
    }

    @Override
    public int getLine() {
        return grammarLexer.getLine();
    }

    @Override
    public int getCharPositionInLine() {
        return grammarLexer.getCharPositionInLine();
    }

    @Override
    public CharStream getInputStream() {
        return grammarLexer.getInputStream();
    }

    @Override
    public TokenFactory getTokenFactory() {
        return grammarLexer.getTokenFactory();
    }

    @Override
    public void setTokenFactory(TokenFactory tokenFactory) {
        grammarLexer.setTokenFactory(tokenFactory);
    }
}
