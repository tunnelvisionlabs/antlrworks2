/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author sam
 */
public interface TextChange {

    public int getDelta();

    public int getLineCountDelta();

    public int getOldPosition();

    public int getOldLength();

    public int getOldEnd();

    public @NonNull Span getOldSpan();

    public @NonNull String getOldText();

    public int getNewPosition();

    public int getNewLength();

    public int getNewEnd();

    public @NonNull Span getNewSpan();

    public @NonNull String getNewText();
}
