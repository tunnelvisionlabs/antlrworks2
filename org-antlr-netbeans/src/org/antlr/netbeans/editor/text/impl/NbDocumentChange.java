/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.text.impl;

import org.antlr.netbeans.editor.text.DocumentChange;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class NbDocumentChange implements DocumentChange {

    private final int oldPosition;
    @NonNull
    private final String oldText;
    private final int newPosition;
    @NonNull
    private final String newText;
    private final int lineCountDelta;

    public NbDocumentChange(int oldPosition, @NonNull String oldText, int newPosition, @NonNull String newText) {
        this(oldPosition, oldText, newPosition, newText, getLineCount(newText) - getLineCount(oldText));
    }

    public NbDocumentChange(int oldPosition, @NonNull String oldText, int newPosition, @NonNull String newText, int lineCountDelta) {
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
    public int getOldOffset() {
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
    public OffsetRegion getOldRegion() {
        return new OffsetRegion(oldPosition, oldText.length());
    }

    @Override
    public String getOldText() {
        return oldText;
    }

    @Override
    public int getNewOffset() {
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
    public OffsetRegion getNewRegion() {
        return new OffsetRegion(newPosition, newText.length());
    }

    @Override
    public String getNewText() {
        return newText;
    }

    private static int getLineCount(@NonNull String text) {
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
