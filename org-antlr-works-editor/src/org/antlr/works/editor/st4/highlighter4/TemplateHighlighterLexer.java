/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
        if (groupLexer._modeStack == null) {
            return getCachedState(groupLexer.getOpenDelimiter(), groupLexer.getCloseDelimiter(), groupLexer._mode, null);
        }

        int[] modes = new int[groupLexer._modeStack.size()];
        int index = 0;
        for (int mode : groupLexer._modeStack) {
            modes[index++] = mode;
        }

        return getCachedState(groupLexer.getOpenDelimiter(), groupLexer.getCloseDelimiter(), groupLexer._mode, modes);
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
        groupLexer._mode = state.getMode();
        if (state.getModeStack() != null && state.getModeStack().length > 0) {
            if (groupLexer._modeStack == null) {
                groupLexer._modeStack = new ArrayDeque<Integer>();
            } else {
                groupLexer._modeStack.clear();
            }

            for (int mode : state.getModeStack()) {
                groupLexer._modeStack.add(mode);
            }
        } else if (groupLexer._modeStack != null) {
            groupLexer._modeStack.clear();
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
