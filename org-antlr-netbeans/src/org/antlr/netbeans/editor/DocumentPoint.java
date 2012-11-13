/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor;

import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import javax.swing.text.StyledDocument;
import org.openide.text.NbDocument;
import org.openide.util.Parameters;

/**
 * Represents a fixed position within a {@link StyledDocument}.
 *
 * @author Sam Harwell
 */
public final class DocumentPoint implements Comparable<DocumentPoint> {
    private final StyledDocument document;
    private final Position position;

    public DocumentPoint(StyledDocument document, int offset) throws BadLocationException {
        this.document = document;
        this.position = document.createPosition(offset);
    }

    public DocumentPoint(StyledDocument document, Position position) {
        this.document = document;
        this.position = position;
    }

    public StyledDocument getDocument() {
        return document;
    }

    public int getOffset() {
        return position.getOffset();
    }

    public DocumentLine getContainingLine() {
        try {
            return new DocumentLine(getDocument(), NbDocument.findLineNumber(getDocument(), getOffset()));
        } catch (BadLocationException ex) {
            throw new IllegalStateException("Not reachable if the position was originally from this document.", ex);
        }
    }

    public DocumentPoint add(int offset) throws BadLocationException {
        return new DocumentPoint(getDocument(), this.getOffset() + offset);
    }

    public DocumentPoint subtract(int offset) throws BadLocationException {
        return new DocumentPoint(getDocument(), this.getOffset() - offset);
    }

    public int subtract(DocumentPoint point) {
        Parameters.notNull("point", point);
        if (!getDocument().equals(point.getDocument())) {
            throw new IllegalArgumentException("The points must lie within the same document.");
        }
        return this.getOffset() - point.getOffset();
    }

    @Override
    public int compareTo(DocumentPoint other) {
        Parameters.notNull("other", other);
        if (!getDocument().equals(other.getDocument())) {
            throw new IllegalArgumentException("The points must lie within the same document.");
        }
        return getOffset() - other.getOffset();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DocumentPoint)) {
            return false;
        }

        DocumentPoint other = (DocumentPoint)obj;
        return (this.position == other.position || this.getOffset() == other.getOffset())
            && this.document.equals(other.document);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.document != null ? this.document.hashCode() : 0);
        hash = 89 * hash + this.position.hashCode();
        return hash;
    }

}
