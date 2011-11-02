/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

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

    public Span getOldSpan();

    public String getOldText();

    public int getNewPosition();

    public int getNewLength();

    public int getNewEnd();

    public Span getNewSpan();

    public String getNewText();
}
