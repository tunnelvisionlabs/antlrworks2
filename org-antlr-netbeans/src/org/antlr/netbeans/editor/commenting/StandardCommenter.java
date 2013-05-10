/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.commenting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import javax.swing.text.Position.Bias;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.DocumentLine;
import org.antlr.netbeans.editor.DocumentPoint;
import org.antlr.netbeans.editor.DocumentSpan;
import org.antlr.netbeans.editor.TextEdit;
import org.netbeans.editor.BaseDocument;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class StandardCommenter implements Commenter {

    private final List<CommentFormat> commentFormats;
    private final List<BlockCommentFormat> blockFormats;
    private final List<LineCommentFormat> lineFormats;
    private final boolean useLineComments;

    public StandardCommenter(CommentFormat... commentFormats) {
        this.commentFormats = Arrays.asList(Arrays.copyOf(commentFormats, commentFormats.length));
        this.blockFormats = new ArrayList<>();
        this.lineFormats = new ArrayList<>();
        for (CommentFormat format : commentFormats) {
            if (format instanceof BlockCommentFormat) {
                blockFormats.add((BlockCommentFormat)format);
            } else if (format instanceof LineCommentFormat) {
                lineFormats.add((LineCommentFormat)format);
            }
        }

        this.useLineComments = !this.lineFormats.isEmpty();
    }

    public List<CommentFormat> getCommentFormats() {
        return commentFormats;
    }

    public List<BlockCommentFormat> getBlockFormats() {
        return blockFormats;
    }

    public List<LineCommentFormat> getLineFormats() {
        return lineFormats;
    }

    public BlockCommentFormat getPreferredBlockFormat() {
        List<BlockCommentFormat> formats = getBlockFormats();
        if (formats == null || formats.isEmpty()) {
            return null;
        }

        return formats.get(0);
    }

    public LineCommentFormat getPreferredLineFormat() {
        List<LineCommentFormat> formats = getLineFormats();
        if (formats == null || formats.isEmpty()) {
            return null;
        }

        return formats.get(0);
    }

    public boolean getUseLineComments() {
        return useLineComments;
    }

    @Override
    public List<DocumentSpan> commentSpans(List<DocumentSpan> spans) throws BadLocationException {
        Parameters.notNull("spans", spans);

        List<DocumentSpan> result = new ArrayList<>();
        if (spans.isEmpty())
            return result;

        List<Position> startPositions = new ArrayList<>();
        List<Position> endPositions = new ArrayList<>();
        StyledDocument document = spans.get(0).getDocument();

        if (!(document instanceof BaseDocument)) {
            return spans;
        }

        BaseDocument baseDocument = (BaseDocument)document;
        TextEdit edit = createEdit(baseDocument);
        try {
            for (DocumentSpan span : spans) {
                DocumentSpan selection = commentSpan(span, edit);
                if (baseDocument != null) {
                    startPositions.add(baseDocument.createPosition(selection.getStart().getOffset(), Bias.Backward));
                } else {
                    startPositions.add(document.createPosition(selection.getStart().getOffset()));
                }
                endPositions.add(document.createPosition(selection.getEnd().getOffset()));
            }

            edit.apply();
        } finally {
            edit.dispose();
        }

        for (int i = 0; i < startPositions.size(); i++) {
            result.add(new DocumentSpan(document, startPositions.get(i).getOffset(), endPositions.get(i).getOffset()));
        }

        if (result.size() > 1) {
            for (int i = result.size() - 1; result.size() > 1 && i >= 0; i--) {
                if (result.get(i).isEmpty()) {
                    result.remove(i);
                }
            }
        }

        return result;
    }

    @Override
    public List<DocumentSpan> uncommentSpans(List<DocumentSpan> spans) throws BadLocationException {
        Parameters.notNull("spans", spans);

        List<DocumentSpan> result = new ArrayList<>();
        if (spans.isEmpty())
            return result;

        List<Position> startPositions = new ArrayList<>();
        List<Position> endPositions = new ArrayList<>();
        StyledDocument document = spans.get(0).getDocument();

        if (!(document instanceof BaseDocument)) {
            return spans;
        }
        
        BaseDocument baseDocument = (BaseDocument)document;
        TextEdit edit = createEdit(baseDocument);
        try {
            for (DocumentSpan span : spans) {
                DocumentSpan selection = uncommentSpan(span, edit);
                if (baseDocument != null) {
                    startPositions.add(baseDocument.createPosition(selection.getStart().getOffset(), Bias.Backward));
                } else {
                    startPositions.add(document.createPosition(selection.getStart().getOffset()));
                }
                endPositions.add(document.createPosition(selection.getEnd().getOffset()));
            }

            edit.apply();
        } finally {
            edit.dispose();
        }

        for (int i = 0; i < startPositions.size(); i++) {
            result.add(new DocumentSpan(document, startPositions.get(i).getOffset(), endPositions.get(i).getOffset()));
        }

        if (result.size() > 1) {
            for (int i = result.size() - 1; result.size() > 1 && i >= 0; i--) {
                if (result.get(i).isEmpty()) {
                    result.remove(i);
                }
            }
        }

        return result;
    }

    protected DocumentSpan commentSpan(DocumentSpan span, TextEdit edit) throws BadLocationException {
        Parameters.notNull("span", span);
        Parameters.notNull("edit", edit);

        /*
         * Use line comments if:
         *  UseLineComments is true
         *  AND one of the following is true:
         *
         *  1. there is no selected text
         *  2. on the line where the selection starts, there is only whitespace up to the selection start point
         *     AND on the line where the selection ends, there is only whitespace up to the selection end point,
         *         OR there is only whitespace from the selection end point to the end of the line
         *
         * Use block comments if:
         *  We are not using line comments
         *  AND some text is selected
         *  AND PreferredBlockFormat is not null
         */
        DocumentLine startContainingLine = span.getStart().getContainingLine();
        DocumentLine endContainingLine = span.getEnd().getContainingLine();

        if (getUseLineComments()
            && (span.isEmpty() ||
                (isNullOrWhiteSpace(startContainingLine.getText().substring(0, span.getStart().subtract(startContainingLine.getStart())))
                    && (isNullOrWhiteSpace(endContainingLine.getText().substring(0, span.getEnd().subtract(endContainingLine.getStart())))
                        || isNullOrWhiteSpace(endContainingLine.getText().substring(span.getEnd().subtract(endContainingLine.getStart()))))
               )))
        {
            span = commentLines(span, edit, getPreferredLineFormat());
        }
        else if (
            span.getLength() > 0
            && getPreferredBlockFormat() != null
            )
        {
            span = commentBlock(span, edit, getPreferredBlockFormat());
        }

        return span;
    }

    protected DocumentSpan commentLines(DocumentSpan span, TextEdit edit, LineCommentFormat format) throws BadLocationException {
        Parameters.notNull("span", span);
        Parameters.notNull("edit", edit);
        Parameters.notNull("format", format);

        /* Rules for line comments:
         *  Make sure line comments are indented as far as possible, skipping empty lines as necessary
         *  Don't comment N+1 lines when only N lines were selected by clicking in the left margin
         */
        if (span.getEnd().getContainingLine().getLineNumber() > span.getStart().getContainingLine().getLineNumber()
            && span.getEnd().getContainingLine().getStart().equals(span.getEnd())) {

            DocumentPoint start = span.getStart();
            DocumentPoint end = getLineFromLineNumber(span.getDocument(), span.getEnd().getContainingLine().getLineNumber() - 1).getStart();
            if (end.compareTo(start) < 0) {
                start = end;
            }

            span = new DocumentSpan(start, end);
        }

        int minIndex = Integer.MAX_VALUE;

        int firstLineNumber = span.getStart().getContainingLine().getLineNumber();
        int lineCount = span.getEnd().getContainingLine().getLineNumber() - span.getStart().getContainingLine().getLineNumber() + 1;
        for (int i = firstLineNumber; i < firstLineNumber + lineCount; i++) {
            DocumentLine line = getLineFromLineNumber(span.getDocument(), i);
            if (line.getText().trim().isEmpty()) {
                continue;
            }

            int index = scanToNonWhitespaceChar(line);
            minIndex = Math.min(minIndex, index);
        }

        // comment each line
        for (int line = span.getStart().getContainingLine().getLineNumber(); line <= span.getEnd().getContainingLine().getLineNumber(); line++) {
            DocumentLine l = getLineFromLineNumber(span.getDocument(), line);
            if (l.getText().trim().isEmpty()) {
                continue;
            }

            edit.insert(l.getStart().add(minIndex), format.getStartText());
        }

        return new DocumentSpan(span.getStart().getContainingLine().getStart(), span.getEnd().getContainingLine().getEnd());
    }

    protected DocumentSpan commentBlock(DocumentSpan span, TextEdit edit, BlockCommentFormat format) throws BadLocationException {
        Parameters.notNull("span", span);
        Parameters.notNull("edit", edit);
        Parameters.notNull("format", format);

        // special case no selection
        if (span.isEmpty()) {
            span = new DocumentSpan(span.getStart().getContainingLine().getStart().add(scanToNonWhitespaceChar(span.getStart().getContainingLine())), span.getEnd().getContainingLine().getEnd());
        }

        // add start comment
        edit.insert(span.getStart(), format.getStartText());
        // add end comment
        edit.insert(span.getEnd(), format.getEndText());

        return span;
    }

    protected DocumentSpan uncommentSpan(DocumentSpan span, TextEdit edit) throws BadLocationException {
        Parameters.notNull("span", span);

        @SuppressWarnings("LocalVariableHidesMemberVariable")
        boolean useLineComments = true;
        DocumentLine startContainingLine = span.getStart().getContainingLine();
        DocumentLine endContainingLine = span.getEnd().getContainingLine();

        // special case: empty span
        if (span.isEmpty()) {
            if (useLineComments) {
                span = uncommentLines(span, edit, getLineFormats());
            }
        } else {
            DocumentSpan resultSpan = tryUncommentBlock(span, edit, getBlockFormats());
            if (resultSpan != null) {
                return resultSpan;
            }

            if (useLineComments) {
                span = uncommentLines(span, edit, getLineFormats());
            }
        }

        return span;
    }

    protected DocumentSpan uncommentLines(DocumentSpan span, TextEdit edit, List<LineCommentFormat> formats) throws BadLocationException {
        Parameters.notNull("span", span);
        Parameters.notNull("formats", formats);

        if (span.getEnd().getContainingLine().getLineNumber() > span.getStart().getContainingLine().getLineNumber()
            && span.getEnd().equals(span.getEnd().getContainingLine().getStart())) {

            DocumentPoint start = span.getStart();
            DocumentPoint end = span.getEnd().getContainingLine().getStart().subtract(1).getContainingLine().getStart();
            if (end.compareTo(start) < 0) {
                start = end;
            }

            span = new DocumentSpan(start, end);
        }

        // remove line comments
        for (int line = span.getStart().getContainingLine().getLineNumber(); line <= span.getEnd().getContainingLine().getLineNumber(); line++) {
            DocumentLine currentLine = getLineFromLineNumber(span.getDocument(), line);
            int i = scanToNonWhitespaceChar(currentLine);
            String text = currentLine.getText();
            for (LineCommentFormat format : formats) {
                int commentLength = format.getStartText().length();
                if ((text.length() > i + commentLength) && text.substring(i, i + commentLength).equals(format.getStartText())) {
                    // remove line comment
                    edit.remove(currentLine.getStart().getOffset() + i, commentLength);
                }
            }
        }

        return new DocumentSpan(span.getStart().getContainingLine().getStart(), span.getEnd().getContainingLine().getEnd());
    }

    protected DocumentSpan tryUncommentBlock(DocumentSpan span, TextEdit edit, List<BlockCommentFormat> formats) throws BadLocationException {
        Parameters.notNull("span", span);
        Parameters.notNull("formats", formats);

        for (BlockCommentFormat format : formats) {
            DocumentSpan result = tryUncommentBlock(span, edit, format);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    protected DocumentSpan tryUncommentBlock(DocumentSpan span, TextEdit edit, BlockCommentFormat format) throws BadLocationException {
        Parameters.notNull("span", span);
        Parameters.notNull("format", format);

        String blockStart = format.getStartText();
        String blockEnd = format.getEndText();

        int startLength = span.getStart().getContainingLine().getLength();
        int endLength = span.getEnd().getContainingLine().getLength();

        span = trimSpan(span);

        // special case no selection: try and uncomment the current line
        if (span.isEmpty()) {
            span = new DocumentSpan(span.getStart().getContainingLine().getStart().add(scanToNonWhitespaceChar(span.getStart().getContainingLine())), span.getEnd().getContainingLine().getEnd());
        }

        // check that the comment start and end blocks are possible
        if ((span.getStart().subtract(span.getStart().getContainingLine().getStart())) + blockStart.length() <= startLength
            && (span.getEnd().subtract(span.getEnd().getContainingLine().getStart())) - blockStart.length() >= 0) {

            String startText = span.getDocument().getText(span.getStart().getOffset(), blockStart.length());
            if (startText.equals(blockStart)) {
                DocumentSpan linespan = new DocumentSpan(span.getEnd().subtract(blockEnd.length()), span.getEnd());
                String endText = linespan.getText();
                if (endText.equals(blockEnd)) {
                    // yes, block comment selected, remove it
                    edit.remove(linespan.getStart().getOffset(), linespan.getLength());
                    edit.remove(span.getStart().getOffset(), blockStart.length());
                    return span;
                }
            }
        }

        return null;
    }

    private TextEdit createEdit(BaseDocument document) {
        Parameters.notNull("document", document);

        return new TextEditImpl(document);
    }

    private static boolean isNullOrWhiteSpace(String text) {
        if (text == null) {
            return true;
        }

        return text.isEmpty() || text.trim().isEmpty();
    }

    protected static DocumentLine getLineFromLineNumber(StyledDocument document, int lineNumber) throws BadLocationException {
        return new DocumentLine(document, lineNumber);
    }

    protected static DocumentSpan trimSpan(DocumentSpan span) throws BadLocationException {
        String text = span.getText();
        int length = text.trim().length();

        int offset = 0;
        while (offset < text.length() && Character.isWhitespace(text.charAt(offset))) {
            offset++;
        }

        if (offset > 0 || length != text.length()) {
            DocumentPoint start = span.getStart().add(offset);
            return new DocumentSpan(start, start.add(length));
        }

        return span;
    }

    protected static int scanToNonWhitespaceChar(DocumentLine line) throws BadLocationException {
        Parameters.notNull("line", line);

        String text = line.getText();
        int length = text.length();
        int i = 0;
        while (i < length && Character.isWhitespace(text.charAt(i))) {
            i++;
        }

        return i;
    }
}
