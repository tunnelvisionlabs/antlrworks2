/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.highlighter;

import java.util.Arrays;
import org.antlr.netbeans.editor.highlighting.LineStateInfo;
import org.antlr.v4.runtime.Lexer;

/**
 *
 * @author Sam Harwell
 */
public class TemplateHighlighterLexerState implements LineStateInfo<TemplateHighlighterLexerState> {
    public static final TemplateHighlighterLexerState INITIAL = new TemplateHighlighterLexerState();
    public static final TemplateHighlighterLexerState DIRTY = new TemplateHighlighterLexerState();
    public static final TemplateHighlighterLexerState MULTILINE = new TemplateHighlighterLexerState();

    private final char openDelimiter;
    private final char closeDelimiter;
    private final int mode;
    private final int[] modeStack;

    private TemplateHighlighterLexerState() {
        this(GroupHighlighterLexer.DEFAULT_OPEN_DELIMITER, GroupHighlighterLexer.DEFAULT_CLOSE_DELIMITER, Lexer.DEFAULT_MODE, null);
    }

    public TemplateHighlighterLexerState(char openDelimiter, char closeDelimiter, int mode, int[] modeStack) {
        this.openDelimiter = openDelimiter;
        this.closeDelimiter = closeDelimiter;
        this.mode = mode;
        this.modeStack = modeStack;
    }

    public char getOpenDelimiter() {
        if (getIsDirty() || getIsMultiLineToken()) {
            throw new UnsupportedOperationException();
        }

        return openDelimiter;
    }

    public char getCloseDelimiter() {
        if (getIsDirty() || getIsMultiLineToken()) {
            throw new UnsupportedOperationException();
        }

        return closeDelimiter;
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
        if (!(obj instanceof TemplateHighlighterLexerState)) {
            return false;
        }

        return this.equals((TemplateHighlighterLexerState)obj);
    }

    public boolean equals(TemplateHighlighterLexerState other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }

        return this.getIsDirty() == other.getIsDirty()
            && this.getIsMultiLineToken() == other.getIsMultiLineToken()
            && this.getOpenDelimiter() == other.getOpenDelimiter()
            && this.getCloseDelimiter() == other.getCloseDelimiter()
            && this.getMode() == other.getMode()
            && Arrays.equals(this.getModeStack(), other.getModeStack());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.openDelimiter;
        hash = 31 * hash + this.closeDelimiter;
        hash = 31 * hash + this.mode;
        hash = 31 * hash + Arrays.hashCode(this.modeStack);
        return hash;
    }

    @Override
    public TemplateHighlighterLexerState createDirtyState() {
        return DIRTY;
    }

    @Override
    public TemplateHighlighterLexerState createMultiLineState() {
        return MULTILINE;
    }
}
