/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author sam
 */
public interface TextSnapshotLine {

    public @NonNull TextSnapshot getSnapshot();

    public int getLineNumber();

    public @NonNull SnapshotPoint getStart();

    public int getLength();

    public int getLengthIncludingLineBreak();

    public @NonNull SnapshotPoint getEnd();

    public @NonNull SnapshotPoint getEndIncludingLineBreak();

    public @NonNull SnapshotSpan getExtent();

    public @NonNull SnapshotSpan getExtentIncludingLineBreak();

    public int getLineBreakLength();

    public @NonNull String getText();

    public @NonNull String getTextIncludingLineBreak();

    public @NonNull String getLineBreakText();
}
