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
package org.antlr.works.editor.antlr4.classification;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.antlr.netbeans.editor.highlighting.LineStateInfo;
import org.antlr.v4.runtime.Lexer;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;

/**
 *
 * @author Sam Harwell
 */
public class SimpleLexerState implements LineStateInfo<SimpleLexerState> {
    @NonNull
    public static final SimpleLexerState INITIAL = new SimpleLexerState();
    @NonNull
    public static final SimpleLexerState DIRTY = new SimpleLexerState();
    @NonNull
    public static final SimpleLexerState MULTILINE = new SimpleLexerState();

    private final int mode;
    @NonNull
    private final List<Integer> modeStack;

    public SimpleLexerState() {
        this.mode = Lexer.DEFAULT_MODE;
        this.modeStack = Collections.emptyList();
    }

    private SimpleLexerState(int mode, @NullAllowed Collection<Integer> modeStack) {
        this.mode = mode;
        if (modeStack == null || modeStack.isEmpty()) {
            this.modeStack = Collections.emptyList();
        } else {
            this.modeStack = new ArrayList<Integer>(modeStack);
        }
    }

    public static SimpleLexerState createSimpleState(@NonNull Lexer lexer) {
        return create(lexer.mode, lexer.modeStack);
    }

    private static SimpleLexerState create(int mode, @NullAllowed Collection<Integer> modeStack) {
        if (mode == Lexer.DEFAULT_MODE && (modeStack == null || modeStack.isEmpty())) {
            return INITIAL;
        }

        return new SimpleLexerState(mode, modeStack);
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
    public SimpleLexerState createDirtyState() {
        return DIRTY;
    }

    @Override
    public SimpleLexerState createMultiLineState() {
        return MULTILINE;
    }

    public int getMode() {
        return mode;
    }

    public List<Integer> getModeStack() {
        return modeStack;
    }

    public void apply(Lexer lexer) {
        if (lexer.modeStack != null) {
            lexer.modeStack.clear();
        }

        lexer.mode = this.getMode();

        if (!this.getModeStack().isEmpty()) {
            if (lexer.modeStack == null) {
                lexer.modeStack = new ArrayDeque<Integer>();
            }

            lexer.modeStack.addAll(this.getModeStack());
        }
    }

    @Override
    public int hashCode() {
        if (this == INITIAL) {
            return 0;
        } else if (getIsDirty()) {
            return -1;
        } else if (getIsMultiLineToken()) {
            return -2;
        }

        int hash = 7;
        hash = 59 * hash + this.mode;
        hash = 59 * hash + (this.modeStack != null ? this.modeStack.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof SimpleLexerState)) {
            return false;
        }

        SimpleLexerState other = (SimpleLexerState)obj;
        return this.mode == other.mode
            && this.modeStack.equals(other.modeStack);
    }
}
