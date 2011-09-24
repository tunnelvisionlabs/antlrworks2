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
