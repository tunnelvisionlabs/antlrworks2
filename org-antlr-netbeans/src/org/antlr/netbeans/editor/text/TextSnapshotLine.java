/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

/**
 *
 * @author sam
 */
public interface TextSnapshotLine {

    public TextSnapshot getSnapshot();

    public int getLineNumber();

    public SnapshotPoint getStart();

    public int getLength();

    public int getLengthIncludingLineBreak();

    public SnapshotPoint getEnd();

    public SnapshotPoint getEndIncludingLineBreak();

    public SnapshotSpan getExtent();

    public SnapshotSpan getExtentIncludingLineBreak();

    public int getLineBreakLength();

    public String getText();

    public String getTextIncludingLineBreak();

    public String getLineBreakText();
}
