/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.highlighter;

import java.util.Arrays;
import org.antlr.netbeans.editor.highlighting.LineStateInfo;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author Sam Harwell
 */
public class GrammarHighlighterLexerState implements LineStateInfo<GrammarHighlighterLexerState> {
    public static final GrammarHighlighterLexerState INITIAL = new GrammarHighlighterLexerState();
    public static final GrammarHighlighterLexerState DIRTY = new GrammarHighlighterLexerState();
    public static final GrammarHighlighterLexerState MULTILINE = new GrammarHighlighterLexerState();

    private final boolean inOptions;
    private final boolean inTokens;
    private final int ruleType;
    private final int mode;
    private final int[] modeStack;

    private GrammarHighlighterLexerState() {
        this(false, false, Token.INVALID_TYPE, Lexer.DEFAULT_MODE, null);
    }

    public GrammarHighlighterLexerState(boolean inOptions, boolean inTokens, int ruleType, int mode, int[] modeStack) {
        this.inOptions = inOptions;
        this.inTokens = inTokens;
        this.ruleType = ruleType;
        this.mode = mode;
        this.modeStack = modeStack;
    }

    public boolean isInOptions() {
        if (getIsDirty() || getIsMultiLineToken()) {
            throw new UnsupportedOperationException();
        }

        return inOptions;
    }

    public boolean isInTokens() {
        if (getIsDirty() || getIsMultiLineToken()) {
            throw new UnsupportedOperationException();
        }

        return inTokens;
    }

    public int getRuleType() {
        if (getIsDirty() || getIsMultiLineToken()) {
            throw new UnsupportedOperationException();
        }

        return ruleType;
    }

    public int getMode() {
        if (getIsDirty() || getIsMultiLineToken()) {
            throw new UnsupportedOperationException();
        }

        return mode;
    }

    public int[] getModeStack() {
        if (getIsDirty() || getIsMultiLineToken()) {
            throw new UnsupportedOperationException();
        }

        return modeStack;
    }

    @Override
    public boolean getIsDirty() {
        return this == DIRTY;
    }

    @Override
    public boolean getIsMultiLineToken() {
        return this == MULTILINE;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GrammarHighlighterLexerState)) {
            return false;
        }

        return this.equals((GrammarHighlighterLexerState)obj);
    }

    public boolean equals(GrammarHighlighterLexerState other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }

        return this.getIsDirty() == other.getIsDirty()
            && this.getIsMultiLineToken() == other.getIsMultiLineToken()
            && this.isInOptions() == other.isInOptions()
            && this.isInTokens() == other.isInTokens()
            && this.getRuleType() == other.getRuleType()
            && this.getMode() == other.getMode()
            && Arrays.equals(this.getModeStack(), other.getModeStack());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.inOptions ? 1 : 0);
        hash = 31 * hash + (this.inTokens ? 1 : 0);
        hash = 31 * hash + this.ruleType;
        hash = 31 * hash + this.mode;
        hash = 31 * hash + Arrays.hashCode(this.modeStack);
        return hash;
    }

    @Override
    public GrammarHighlighterLexerState createDirtyState() {
        return DIRTY;
    }

    @Override
    public GrammarHighlighterLexerState createMultiLineState() {
        return MULTILINE;
    }
}
