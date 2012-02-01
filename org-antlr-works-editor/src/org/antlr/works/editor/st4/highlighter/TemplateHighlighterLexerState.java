/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.highlighter;

import org.antlr.netbeans.editor.highlighting.LineStateInfo;

public class TemplateHighlighterLexerState implements LineStateInfo<TemplateHighlighterLexerState> {
    public static final TemplateHighlighterLexerState INITIAL =
        new TemplateHighlighterLexerState(TemplateLexerMode.Group, OutermostTemplate.None, 0, false, '<', '>');

    public final TemplateLexerMode Mode;
    public final OutermostTemplate Outermost;
    public final int AnonymousTemplateLevel;
    public final boolean InComment;
    public final char OpenDelimiter;
    public final char CloseDelimiter;

    private final boolean dirty;
    private final boolean multiLine;

    public TemplateHighlighterLexerState(TemplateLexerMode mode, OutermostTemplate outermost, int anonymousTemplateLevel, boolean inComment, char openDelimiter, char closeDelimiter) {
        this.Mode = mode;
        this.Outermost = outermost;
        this.AnonymousTemplateLevel = anonymousTemplateLevel;
        this.InComment = inComment;
        this.OpenDelimiter = openDelimiter;
        this.CloseDelimiter = closeDelimiter;
        this.dirty = false;
        this.multiLine = false;
    }

    private TemplateHighlighterLexerState(TemplateHighlighterLexerState state, boolean dirty, boolean multiLine) {
        this.Mode = state.Mode;
        this.Outermost = state.Outermost;
        this.AnonymousTemplateLevel = state.AnonymousTemplateLevel;
        this.InComment = state.InComment;
        this.OpenDelimiter = state.OpenDelimiter;
        this.CloseDelimiter = state.CloseDelimiter;
        this.dirty = dirty;
        this.multiLine = multiLine;
    }

    @Override
    public boolean getIsDirty() {
        return dirty;
    }

    @Override
    public boolean getIsMultiLineToken() {
        return multiLine;
    }

    @Override
    public TemplateHighlighterLexerState createDirtyState() {
        if (getIsDirty()) {
            return this;
        }

        return new TemplateHighlighterLexerState(this, true, multiLine);
    }

    @Override
    public TemplateHighlighterLexerState createMultiLineState() {
        if (getIsMultiLineToken()) {
            return this;
        }

        return new TemplateHighlighterLexerState(this, dirty, true);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TemplateHighlighterLexerState)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        TemplateHighlighterLexerState other = (TemplateHighlighterLexerState)obj;
        return this.Mode == other.Mode
            && this.Outermost == other.Outermost
            && this.AnonymousTemplateLevel == other.AnonymousTemplateLevel
            && this.InComment == other.InComment
            && this.OpenDelimiter == other.OpenDelimiter
            && this.CloseDelimiter == other.CloseDelimiter
            && this.dirty == other.dirty
            && this.multiLine == other.multiLine;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.Mode != null ? this.Mode.hashCode() : 0);
        hash = 71 * hash + (this.Outermost != null ? this.Outermost.hashCode() : 0);
        hash = 71 * hash + this.AnonymousTemplateLevel;
        hash = 71 * hash + (this.InComment ? 1 : 0);
        hash = 71 * hash + this.OpenDelimiter;
        hash = 71 * hash + this.CloseDelimiter;
        hash = 71 * hash + (this.dirty ? 1 : 0);
        hash = 71 * hash + (this.multiLine ? 1 : 0);
        return hash;
    }
}
