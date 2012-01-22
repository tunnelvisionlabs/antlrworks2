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
package org.antlr.works.editor.st4.highlighter4;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.works.editor.antlr4.highlighting.TokenSourceWithStateV4;

/**
 *
 * @author Sam Harwell
 */
public class TemplateHighlighterLexer implements TokenSourceWithStateV4<TemplateHighlighterLexerState> {
    private static final Map<TemplateHighlighterLexerState, TemplateHighlighterLexerState> sharedStates =
        new HashMap<TemplateHighlighterLexerState, TemplateHighlighterLexerState>();

    private TokenFactory<?> tokenFactory = CommonTokenFactory.DEFAULT;
    private final GroupHighlighterLexer groupLexer;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public TemplateHighlighterLexer(CharStream input, TemplateHighlighterLexerState state) {
        this.groupLexer = new GroupHighlighterLexer(input, state.getOpenDelimiter(), state.getCloseDelimiter());
        setState(input, state);
    }

    @Override
    public CharStream getCharStream() {
        return groupLexer.getInputStream();
    }

    @Override
    public String getSourceName() {
        return "StringTemplate Highlighter";
    }

    @Override
    public TemplateHighlighterLexerState getState() {
        if (groupLexer.modeStack == null) {
            return getCachedState(groupLexer.getOpenDelimiter(), groupLexer.getCloseDelimiter(), groupLexer.mode, null);
        }

        int[] modes = new int[groupLexer.modeStack.size()];
        int index = 0;
        for (int mode : groupLexer.modeStack) {
            modes[index++] = mode;
        }

        return getCachedState(groupLexer.getOpenDelimiter(), groupLexer.getCloseDelimiter(), groupLexer.mode, modes);
    }

    private static TemplateHighlighterLexerState getCachedState(char openDelimiter, char closeDelimiter, int mode, int[] modeStack) {
        TemplateHighlighterLexerState state = new TemplateHighlighterLexerState(openDelimiter, closeDelimiter, mode, modeStack);

        synchronized (sharedStates) {
            TemplateHighlighterLexerState cached = sharedStates.get(state);
            if (cached != null) {
                return cached;
            }

            sharedStates.put(state, state);
            return state;
        }
    }
    public void setState(CharStream input, TemplateHighlighterLexerState state) {
        groupLexer.setInputStream(input);
        groupLexer.mode = state.getMode();
        if (state.getModeStack() != null && state.getModeStack().length > 0) {
            if (groupLexer.modeStack == null) {
                groupLexer.modeStack = new ArrayDeque<Integer>();
            } else {
                groupLexer.modeStack.clear();
            }

            for (int mode : state.getModeStack()) {
                groupLexer.modeStack.add(mode);
            }
        } else if (groupLexer.modeStack != null) {
            groupLexer.modeStack.clear();
        }

        groupLexer.setDelimiters(state.getOpenDelimiter(), state.getCloseDelimiter());
    }

    @Override
    public Token nextToken() {
        Token token;
        do {
            token = nextTokenCore();
        } while (token == null || token.getType() == GroupHighlighterLexer.NEWLINE);

        return token;
    }

    private Token nextTokenCore() {
        return groupLexer.nextToken();
    }

    @Override
    public int getLine() {
        return groupLexer.getLine();
    }

    @Override
    public int getCharPositionInLine() {
        return groupLexer.getCharPositionInLine();
    }

    @Override
    public CharStream getInputStream() {
        return groupLexer.getInputStream();
    }

    @Override
    public void setTokenFactory(TokenFactory<?> tokenFactory) {
        if (tokenFactory == null) {
            tokenFactory = CommonTokenFactory.DEFAULT;
        }

        this.tokenFactory = tokenFactory;
    }
}
