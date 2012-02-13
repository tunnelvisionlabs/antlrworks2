/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.highlighter4;

import java.util.ArrayDeque;
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
        new HashMap<GrammarHighlighterLexerState, GrammarHighlighterLexerState>();

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
    public CharStream getCharStream() {
        return grammarLexer.getInputStream();
    }

    @Override
    public String getSourceName() {
        return "Grammar Highlighter";
    }

    @Override
    public GrammarHighlighterLexerState getState() {
        if (grammarLexer._modeStack == null) {
            return getCachedState(grammarLexer.isInOptions(), grammarLexer.isInTokens(), grammarLexer._mode, null);
        }

        int[] modes = new int[grammarLexer._modeStack.size()];
        int index = 0;
        for (int mode : grammarLexer._modeStack) {
            modes[index++] = mode;
        }

        return getCachedState(grammarLexer.isInOptions(), grammarLexer.isInTokens(), grammarLexer._mode, modes);
    }

    private static GrammarHighlighterLexerState getCachedState(boolean inOptions, boolean inTokens, int mode, int[] modeStack) {
        GrammarHighlighterLexerState state = new GrammarHighlighterLexerState(inOptions, inTokens, mode, modeStack);

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
        if (state.getModeStack() != null && state.getModeStack().length > 0) {
            if (grammarLexer._modeStack == null) {
                grammarLexer._modeStack = new ArrayDeque<Integer>();
            } else {
                grammarLexer._modeStack.clear();
            }

            for (int mode : state.getModeStack()) {
                grammarLexer._modeStack.add(mode);
            }
        } else if (grammarLexer._modeStack != null) {
            grammarLexer._modeStack.clear();
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
    public void setTokenFactory(TokenFactory<?> tokenFactory) {
        grammarLexer.setTokenFactory(tokenFactory);
    }
}
