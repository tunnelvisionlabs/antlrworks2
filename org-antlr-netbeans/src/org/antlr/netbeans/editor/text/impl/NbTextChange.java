/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text.impl;

import org.antlr.netbeans.editor.text.Span;
import org.antlr.netbeans.editor.text.TextChange;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public class NbTextChange implements TextChange {

    private final int oldPosition;
    private final String oldText;
    private final int newPosition;
    private final String newText;
    private final int lineCountDelta;

    public NbTextChange(int oldPosition, String oldText, int newPosition, String newText) {
        this(oldPosition, oldText, newPosition, newText, getLineCount(newText) - getLineCount(oldText));
    }

    public NbTextChange(int oldPosition, String oldText, int newPosition, String newText, int lineCountDelta) {
        this.oldPosition = oldPosition;
        this.oldText = oldText;
        this.newPosition = newPosition;
        this.newText = newText;
        this.lineCountDelta = lineCountDelta;
    }

    @Override
    public int getDelta() {
        return newText.length() - oldText.length();
    }

    @Override
    public int getLineCountDelta() {
        return lineCountDelta;
    }

    @Override
    public int getOldPosition() {
        return oldPosition;
    }

    @Override
    public int getOldLength() {
        return oldText.length();
    }

    @Override
    public int getOldEnd() {
        return oldPosition + oldText.length();
    }

    @Override
    public Span getOldSpan() {
        return new Span(oldPosition, oldText.length());
    }

    @Override
    public String getOldText() {
        return oldText;
    }

    @Override
    public int getNewPosition() {
        return newPosition;
    }

    @Override
    public int getNewLength() {
        return newText.length();
    }

    @Override
    public int getNewEnd() {
        return newPosition + newText.length();
    }

    @Override
    public Span getNewSpan() {
        return new Span(newPosition, newText.length());
    }

    @Override
    public String getNewText() {
        return newText;
    }

    private static int getLineCount(String text) {
        Parameters.notNull("text", text);

        int lines = 1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                lines++;
            }
        }

        return lines;
    }
}
