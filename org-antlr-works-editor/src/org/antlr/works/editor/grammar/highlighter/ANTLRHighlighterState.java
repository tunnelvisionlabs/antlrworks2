/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.highlighter;

import org.antlr.netbeans.editor.highlighting.LineStateInfo;

public class ANTLRHighlighterState implements LineStateInfo<ANTLRHighlighterState> {
    public static final ANTLRHighlighterState INITIAL =
        new ANTLRHighlighterState(ANTLRHighlighterMode.GRAMMAR, 0, false, false, false);

    public final ANTLRHighlighterMode Mode;
    public final int ActionLevel;
    public final boolean InComment;
    public final boolean InOptions;
    public final boolean InTokens;

    private final boolean dirty;
    private final boolean multiline;

    public ANTLRHighlighterState(ANTLRHighlighterMode mode, int actionLevel, boolean inComment, boolean inOptions, boolean inTokens) {
        this.Mode = mode;
        this.ActionLevel = actionLevel;
        this.InComment = inComment;
        this.InOptions = inOptions;
        this.InTokens = inTokens;
        this.dirty = false;
        this.multiline = false;
    }

    private ANTLRHighlighterState(ANTLRHighlighterState state, boolean dirty, boolean multiLine) {
        this.Mode = state.Mode;
        this.ActionLevel = state.ActionLevel;
        this.InComment = state.InComment;
        this.InOptions = state.InOptions;
        this.InTokens = state.InTokens;
        this.dirty = dirty;
        this.multiline = multiLine;
    }

    @Override
    public boolean getIsDirty() {
        return dirty;
    }

    @Override
    public boolean getIsMultiLineToken() {
        return multiline;
    }

    @Override
    public ANTLRHighlighterState createDirtyState() {
        if (getIsDirty())
            return this;

        return new ANTLRHighlighterState(this, true, false);
    }

    @Override
    public ANTLRHighlighterState createMultiLineState() {
        if (getIsMultiLineToken())
            return this;

        return new ANTLRHighlighterState(this, false, true);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ANTLRHighlighterState))
            return false;

        ANTLRHighlighterState other = (ANTLRHighlighterState)obj;
        return this.Mode.equals(other.Mode)
            && this.ActionLevel == other.ActionLevel
            && this.InComment == other.InComment
            && this.InOptions == other.InOptions
            && this.InTokens == other.InTokens
            && this.dirty == other.dirty
            && this.multiline == other.multiline;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.Mode != null ? this.Mode.hashCode() : 0);
        hash = 83 * hash + this.ActionLevel;
        hash = 83 * hash + (this.InComment ? 1 : 0);
        hash = 83 * hash + (this.InOptions ? 1 : 0);
        hash = 83 * hash + (this.InTokens ? 1 : 0);
        hash = 83 * hash + (this.dirty ? 1 : 0);
        hash = 83 * hash + (this.multiline ? 1 : 0);
        return hash;
    }
}
