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
package org.antlr.works.editor.grammar.highlighter4;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import org.antlr.netbeans.editor.highlighting.TokenSourceWithStateV4;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;

/**
 *
 * @author Sam Harwell
 */
public class GrammarHighlighterLexerWrapper implements TokenSourceWithStateV4<GrammarHighlighterLexerState> {
    private static final Map<GrammarHighlighterLexerState, GrammarHighlighterLexerState> sharedStates =
        new HashMap<GrammarHighlighterLexerState, GrammarHighlighterLexerState>();

    private TokenFactory<?> tokenFactory = CommonTokenFactory.DEFAULT;
    private final GrammarHighlighterLexer grammarLexer;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public GrammarHighlighterLexerWrapper(CharStream input, GrammarHighlighterLexerState state) {
        this.grammarLexer = new GrammarHighlighterLexer(input);
        setState(input, state);
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
        if (grammarLexer.modeStack == null) {
            return getCachedState(grammarLexer.mode, null);
        }

        int[] modes = new int[grammarLexer.modeStack.size()];
        int index = 0;
        for (int mode : grammarLexer.modeStack) {
            modes[index++] = mode;
        }

        return getCachedState(grammarLexer.mode, modes);
    }

    private static GrammarHighlighterLexerState getCachedState(int mode, int[] modeStack) {
        GrammarHighlighterLexerState state = new GrammarHighlighterLexerState(mode, modeStack);

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
        grammarLexer.mode = state.getMode();
        if (state.getModeStack() != null && state.getModeStack().length > 0) {
            if (grammarLexer.modeStack == null) {
                grammarLexer.modeStack = new ArrayDeque<Integer>();
            } else {
                grammarLexer.modeStack.clear();
            }

            for (int mode : state.getModeStack()) {
                grammarLexer.modeStack.add(mode);
            }
        } else if (grammarLexer.modeStack != null) {
            grammarLexer.modeStack.clear();
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
        if (tokenFactory == null) {
            tokenFactory = CommonTokenFactory.DEFAULT;
        }

        this.tokenFactory = tokenFactory;
    }
}
