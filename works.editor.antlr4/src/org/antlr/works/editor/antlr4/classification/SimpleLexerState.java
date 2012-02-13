/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
        return create(lexer._mode, lexer._modeStack);
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
        if (lexer._modeStack != null) {
            lexer._modeStack.clear();
        }

        lexer._mode = this.getMode();

        if (!this.getModeStack().isEmpty()) {
            if (lexer._modeStack == null) {
                lexer._modeStack = new ArrayDeque<Integer>();
            }

            lexer._modeStack.addAll(this.getModeStack());
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
