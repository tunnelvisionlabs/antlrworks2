/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.classification;

import org.antlr.netbeans.editor.highlighting.LineStateInfo;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.misc.IntegerStack;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;

/**
 *
 * @author Sam Harwell
 */
public class SimpleLexerState implements LineStateInfo<SimpleLexerState> {
    private static final IntegerStack EMPTY_MODE_STACK = new IntegerStack();

    @NonNull
    public static final SimpleLexerState INITIAL = new SimpleLexerState();
    @NonNull
    public static final SimpleLexerState DIRTY = new SimpleLexerState();
    @NonNull
    public static final SimpleLexerState MULTILINE = new SimpleLexerState();

    private final int mode;
    @NonNull
    private final IntegerStack modeStack;

    public SimpleLexerState() {
        this.mode = Lexer.DEFAULT_MODE;
        this.modeStack = EMPTY_MODE_STACK;
    }

    private SimpleLexerState(int mode, @NullAllowed IntegerStack modeStack) {
        this.mode = mode;
        if (modeStack == null || modeStack.isEmpty()) {
            this.modeStack = EMPTY_MODE_STACK;
        } else {
            this.modeStack = new IntegerStack(modeStack);
        }
    }

    public static SimpleLexerState createSimpleState(@NonNull Lexer lexer) {
        return create(lexer._mode, lexer._modeStack);
    }

    private static SimpleLexerState create(int mode, @NullAllowed IntegerStack modeStack) {
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

    public IntegerStack getModeStack() {
        return modeStack;
    }

    public void apply(Lexer lexer) {
        if (lexer._modeStack != null) {
            lexer._modeStack.clear();
        }

        lexer._mode = this.getMode();

        if (!this.getModeStack().isEmpty()) {
            lexer._modeStack.clear();
            lexer._modeStack.addAll(getModeStack());
        }
    }

    @Override
    public int hashCode() {
        if (this == INITIAL) {
            return 1;
        } else if (getIsDirty()) {
            return -1;
        } else if (getIsMultiLineToken()) {
            return -2;
        }

        int hash = 7;
        hash = 59 * hash + this.mode;
        hash = 59 * hash + this.modeStack.hashCode();
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
        return this.getIsDirty() == other.getIsDirty()
            && this.getIsMultiLineToken() == other.getIsMultiLineToken()
            && this.mode == other.mode
            && this.modeStack.equals(other.modeStack);
    }
}
