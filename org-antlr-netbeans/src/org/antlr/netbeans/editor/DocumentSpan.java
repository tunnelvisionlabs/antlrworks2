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
import javax.swing.text.Position;
import javax.swing.text.StyledDocument;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public final class DocumentSpan {
    private final StyledDocument document;
    private final DocumentPoint start;
    private final DocumentPoint end;

    public DocumentSpan(StyledDocument document, int startOffset, int endOffset) throws BadLocationException {
        Parameters.notNull("document", document);
        if (startOffset < 0) {
            throw new IllegalArgumentException("startOffset must be greater than 0.");
        }
        if (endOffset < startOffset) {
            throw new IllegalArgumentException("endOffset must be greater than or equal to startOffset.");
        }

        this.document = document;
        this.start = new DocumentPoint(document, startOffset);
        this.end = new DocumentPoint(document, endOffset);
    }

    public DocumentSpan(DocumentPoint start, DocumentPoint end) {
        Parameters.notNull("start", start);
        Parameters.notNull("end", end);
        if (!start.getDocument().equals(end.getDocument())) {
            throw new IllegalArgumentException("The start and end points must lie within the same document.");
        }

        this.document = start.getDocument();
        this.start = start;
        this.end = end;
    }

    public DocumentSpan(StyledDocument document, Position start, Position end) {
        Parameters.notNull("document", document);
        Parameters.notNull("start", start);
        Parameters.notNull("end", end);

        if (end.getOffset() < start.getOffset()) {
            throw new IllegalArgumentException("endOffset must be greater than or equal to startOffset.");
        }

        this.document = document;
        this.start = new DocumentPoint(document, start);
        this.end = new DocumentPoint(document, end);
    }

    public StyledDocument getDocument() {
        return document;
    }

    public boolean isEmpty() {
        return getEnd().subtract(getStart()) == 0;
    }

    public DocumentPoint getStart() {
        return start;
    }

    public DocumentPoint getEnd() {
        return end;
    }

    public int getLength() {
        return end.subtract(start);
    }

    public boolean contains(int offset) {
        return (offset >= getStart().getOffset()) && (offset < getEnd().getOffset());
    }

    public boolean contains(DocumentPoint point) {
        Parameters.notNull("point", point);
        if (!getDocument().equals(point.getDocument())) {
            throw new IllegalArgumentException("The point must lie within the same document.");
        }

        return (point.compareTo(getStart()) >= 0) && (point.compareTo(getEnd()) < 0);
    }

    public boolean contains(DocumentSpan span) {
        Parameters.notNull("span", span);
        if (!getDocument().equals(span.getDocument())) {
            throw new IllegalArgumentException("The span must lie within the same document.");
        }

        return (span.getStart().compareTo(getStart()) >= 0) && (span.getEnd().compareTo(getEnd()) <= 0);
    }

    public String getText() {
        try {
            return document.getText(this.getStart().getOffset(), getLength());
        } catch (BadLocationException ex) {
            throw new IllegalStateException("Should not be reachable because the positions should be valid.", ex);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DocumentSpan)) {
            return false;
        }

        DocumentSpan other = (DocumentSpan)obj;
        return (this.start.equals(other.start))
            && (this.end.equals(other.end))
            && this.document.equals(other.document);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + (this.document != null ? this.document.hashCode() : 0);
        hash = 13 * hash + this.start.hashCode();
        hash = 13 * hash + this.end.hashCode();
        return hash;
    }

}
