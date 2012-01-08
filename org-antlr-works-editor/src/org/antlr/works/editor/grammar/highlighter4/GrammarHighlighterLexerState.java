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

import java.util.Arrays;
import org.antlr.netbeans.editor.highlighting.LineStateInfo;
import org.antlr.v4.runtime.Lexer;

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
    private final int mode;
    private final int[] modeStack;

    private GrammarHighlighterLexerState() {
        this(false, false, Lexer.DEFAULT_MODE, null);
    }

    public GrammarHighlighterLexerState(boolean inOptions, boolean inTokens, int mode, int[] modeStack) {
        this.inOptions = inOptions;
        this.inTokens = inTokens;
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
            && this.getMode() == other.getMode()
            && Arrays.equals(this.getModeStack(), other.getModeStack());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.inOptions ? 1 : 0);
        hash = 31 * hash + (this.inTokens ? 1 : 0);
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
