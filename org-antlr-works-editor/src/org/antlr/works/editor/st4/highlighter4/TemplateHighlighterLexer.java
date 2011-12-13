/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.antlr.works.editor.st4.highlighter4;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import org.antlr.netbeans.editor.highlighting.TokenSourceWithStateV4;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.WritableToken;

/**
 *
 * @author Sam Harwell
 */
public class TemplateHighlighterLexer implements TokenSourceWithStateV4<TemplateHighlighterLexerState> {
    private static final Map<TemplateHighlighterLexerState, TemplateHighlighterLexerState> sharedStates =
        new HashMap<TemplateHighlighterLexerState, TemplateHighlighterLexerState>();

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

}
