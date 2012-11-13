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
import javax.swing.text.StyledDocument;
import org.openide.text.NbDocument;
import org.openide.util.Parameters;

/**
 * Represents a line of text in a {@link StyledDocument}.
 *
 * @author Sam Harwell
 */
public final class DocumentLine {
    private final StyledDocument document;
    private final int lineNumber;
    private final DocumentSpan extent;
    private final DocumentSpan extentIncludingLineBreak;
    private final int lineBreakLength;

    public DocumentLine(StyledDocument document, int lineNumber) throws BadLocationException {
        Parameters.notNull("document", document);
        if (lineNumber < 0) {
            throw new IllegalArgumentException("lineNumber must be greater than 0.");
        }
        this.document = document;
        this.lineNumber = lineNumber;
        DocumentPoint start = new DocumentPoint(document, NbDocument.findLineOffset(document, lineNumber));
        if (lineNumber < NbDocument.findLineRootElement(document).getElementCount() - 1) {
            DocumentPoint endIncludingLineBreak = new DocumentPoint(document, NbDocument.findLineOffset(document, lineNumber + 1));
            extentIncludingLineBreak = new DocumentSpan(start, endIncludingLineBreak);
            String text = extentIncludingLineBreak.getText();
            if (text.endsWith("\r\n")) {
                this.lineBreakLength = 2;
            } else {
                this.lineBreakLength = 1;
            }

            this.extent = new DocumentSpan(start, extentIncludingLineBreak.getEnd().subtract(lineBreakLength));
        } else {
            this.lineBreakLength = 0;
            DocumentPoint endIncludingLineBreak = new DocumentPoint(document, document.getLength());
            this.extentIncludingLineBreak = new DocumentSpan(start, endIncludingLineBreak);
            this.extent = this.extentIncludingLineBreak;
        }
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public DocumentPoint getStart() {
        return extent.getStart();
    }

    public DocumentPoint getEnd() {
        return extent.getEnd();
    }

    public DocumentPoint getEndIncludingLineBreak() {
        return extentIncludingLineBreak.getEnd();
    }

    public DocumentSpan getExtent() {
        return extent;
    }

    public DocumentSpan getExtentIncludingLineBreak() {
        return extentIncludingLineBreak;
    }

    public int getLength() {
        return extent.getLength();
    }

    public int getLengthIncludingLineBreak() {
        return extentIncludingLineBreak.getLength();
    }

    public String getText() throws BadLocationException {
        return extent.getText();
    }

    public String getTextIncludingLineBreak() throws BadLocationException {
        return extentIncludingLineBreak.getText();
    }

}
