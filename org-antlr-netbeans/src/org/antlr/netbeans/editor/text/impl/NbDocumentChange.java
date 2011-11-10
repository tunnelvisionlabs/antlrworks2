/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
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
