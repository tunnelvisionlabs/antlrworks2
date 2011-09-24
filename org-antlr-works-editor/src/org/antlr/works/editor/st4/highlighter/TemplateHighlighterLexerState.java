/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
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
