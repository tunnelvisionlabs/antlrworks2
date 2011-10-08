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
