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
package org.antlr.netbeans.editor;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.openide.text.NbDocument;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public final class DocumentPoint implements Comparable<DocumentPoint> {
    private final StyledDocument document;
    private final int offset;

    public DocumentPoint(StyledDocument document, int offset) {
        this.document = document;
        this.offset = offset;
    }

    public StyledDocument getDocument() {
        return document;
    }

    public int getOffset() {
        return offset;
    }

    public DocumentLine getContainingLine() throws BadLocationException {
        return new DocumentLine(document, NbDocument.findLineNumber(document, offset));
    }

    public DocumentPoint add(int offset) {
        return new DocumentPoint(document, this.offset + offset);
    }

    public DocumentPoint subtract(int offset) {
        return new DocumentPoint(document, this.offset - offset);
    }

    public int subtract(DocumentPoint point) {
        Parameters.notNull("point", point);
        if (!getDocument().equals(point.getDocument())) {
            throw new IllegalArgumentException("The points must lie within the same document.");
        }
        return this.offset - point.offset;
    }

    @Override
    public int compareTo(DocumentPoint other) {
        Parameters.notNull("other", other);
        if (!getDocument().equals(other.getDocument())) {
            throw new IllegalArgumentException("The points must lie within the same document.");
        }
        return offset - other.offset;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DocumentPoint)) {
            return false;
        }
        DocumentPoint other = (DocumentPoint)obj;
        return this.offset == other.offset && this.document.equals(other.document);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.document != null ? this.document.hashCode() : 0);
        hash = 89 * hash + this.offset;
        return hash;
    }

}
