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
package org.antlr.netbeans.editor.highlighting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.netbeans.spi.editor.highlighting.HighlightsSequence;
import org.netbeans.spi.editor.highlighting.support.AbstractHighlightsContainer;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;

public abstract class ANTLRHighlighterBase<TState extends LineStateInfo<TState>> extends AbstractHighlightsContainer {
    private static final boolean FIX_HIGHLIGHTER_UPDATE_BUG = true;

    private static boolean timeoutReported;
    
    private final Object lock = new Object();
    private final StyledDocument document;
    private final DocumentListenerImpl documentListener;
    private final ArrayList<TState> lineStates = new ArrayList<TState>();
    
    private Integer firstDirtyLine;
    private Integer lastDirtyLine;
    private Integer firstChangedLine;
    private Integer lastChangedLine;
    
    private boolean failedTimeout;

    public ANTLRHighlighterBase(StyledDocument document) {
        this.document = document;
        this.documentListener = new DocumentListenerImpl();
    }

    protected StyledDocument getDocument() {
        return document;
    }

    public void initialize() {
        TState dirtyState = getStartState().createDirtyState();
        int lineCount = NbDocument.findLineRootElement(this.document).getElementCount();
        this.lineStates.ensureCapacity(lineCount);
        for (int i = 0; i < lineCount; i++) {
            this.lineStates.add(dirtyState);
        }

        subscribeEvents();
        forceRehighlightLines(0, lineStates.size() - 1);
    }

    @Override
    public HighlightsSequence getHighlights(int startOffset, int endOffset) {
        if (endOffset == Integer.MAX_VALUE)
            endOffset = document.getLength();
        
        Span span = Span.fromBounds(startOffset, endOffset);
        
        List<Highlight> highlights = new ArrayList<Highlight>();
        if (failedTimeout)
            return new HighlightsList(highlights);
        
        boolean spanExtended = false;
        
        int extendMultiLineSpanToLine = 0;
        Span extendedSpan = span;
        
        synchronized (lock) {
            Span requestedSpan = span;
            
            ParseRequest<TState> request = adjustParseSpan(span);
            TState startState = request.getState();
            span = request.getSpan();
            
            CharStream input;
            try {
                input = createInputStream(span);
            } catch (BadLocationException ex) {
                return HighlightsSequence.EMPTY;
            }
            
            TokenSourceWithState<TState> lexer = createLexer(input, startState);
            
            CommonToken previousToken = null;
            boolean previousTokenEndsLine = false;

            /* this is held outside the loop because only tokens which end at the end of a line
             * impact its value.
             */
            boolean lineStateChanged = false;

            while (true)
            {
                CommonToken token = (CommonToken)lexer.nextToken();

                boolean inBounds = token.getStartIndex() < span.getEndExclusive();

                int startLineCurrent;
                if (token.getType() == Token.EOF)
                    startLineCurrent = NbDocument.findLineRootElement(document).getElementCount();
                else
                    startLineCurrent = token.getLine();

                if (previousToken == null || previousToken.getLine() < startLineCurrent - 1)
                {
                    // endLinePrevious is the line number the previous token ended on
                    int endLinePrevious;
                    if (previousToken != null)
                        endLinePrevious = NbDocument.findLineNumber(document, previousToken.getStopIndex() + 1);
                    else
                        endLinePrevious = NbDocument.findLineNumber(document, span.getStart()) - 1;

                    if (startLineCurrent > endLinePrevious + 1)
                    {
                        int firstMultilineLine = endLinePrevious;
                        if (previousToken == null || previousTokenEndsLine)
                            firstMultilineLine++;

                        for (int i = firstMultilineLine; i < startLineCurrent; i++)
                        {
                            if (!lineStates.get(i).getIsMultiLineToken() || lineStateChanged)
                                extendMultiLineSpanToLine = i + 1;

                            if (inBounds)
                                setLineState(i, lineStates.get(i).createMultiLineState());
                        }
                    }
                }

                if (token.getType() == Token.EOF)
                    break;

                previousToken = token;
                previousTokenEndsLine = tokenEndsAtEndOfLine(lexer, token);

                if (isMultiLineToken(lexer, token))
                {
                    int startLine = NbDocument.findLineNumber(document, token.getStartIndex());
                    int stopLine = NbDocument.findLineNumber(document, token.getStopIndex() + 1);
                    for (int i = startLine; i < stopLine; i++)
                    {
                        if (!lineStates.get(i).getIsMultiLineToken())
                            extendMultiLineSpanToLine = i + 1;

                        if (inBounds)
                            setLineState(i, lineStates.get(i).createMultiLineState());
                    }
                }

                boolean tokenEndsLine = previousTokenEndsLine;
                if (tokenEndsLine)
                {
                    TState stateAtEndOfLine = lexer.getState();
                    int line = NbDocument.findLineNumber(document, token.getStopIndex() + 1);
                    lineStateChanged =
                        lineStates.get(line).getIsMultiLineToken()
                        || !lineStates.get(line).equals(stateAtEndOfLine);

                    // even if the state didn't change, we call SetLineState to make sure the _first/_lastChangedLine values get updated.
                    if (inBounds)
                        setLineState(line, stateAtEndOfLine);

                    if (lineStateChanged)
                    {
                        if (line < NbDocument.findLineRootElement(document).getElementCount() - 1)
                        {
                            /* update the span's end position or the line state change won't be reflected
                             * in the editor
                             */
                            int endPosition = line < NbDocument.findLineRootElement(document).getElementCount() - 2 ? NbDocument.findLineOffset(document, line + 2) : document.getLength();
                            if (endPosition > extendedSpan.getEndExclusive())
                            {
                                spanExtended = true;
                                extendedSpan = Span.fromBounds(extendedSpan.getStart(), endPosition);
                            }
                        }
                    }
                }

                if (token.getStartIndex() >= span.getEndExclusive())
                    break;

                if (token.getStopIndex() < requestedSpan.getStart())
                    continue;

                Collection<Highlight> tokenClassificationSpans = getHighlightsForToken(token);
                if (tokenClassificationSpans != null) {
                    highlights.addAll(tokenClassificationSpans);
                }
                
                if (!inBounds)
                    break;
            }
        }
        
        if (extendMultiLineSpanToLine > 0) {
            int endPosition = extendMultiLineSpanToLine < NbDocument.findLineRootElement(document).getElementCount() - 1 ? NbDocument.findLineOffset(document, extendMultiLineSpanToLine + 1) : document.getLength();
            if (endPosition > extendedSpan.getEndExclusive()) {
                spanExtended = true;
                extendedSpan = Span.fromBounds(extendedSpan.getStart(), endPosition);
            }
        }
        
        if (spanExtended) {
            /* Subtract 1 from each of these because the spans include the line break on their last
             * line, forcing it to appear as the first position on the following line.
             */
            int firstLine = NbDocument.findLineNumber(document, span.getEndExclusive());
            int lastLine = NbDocument.findLineNumber(document, extendedSpan.getEndExclusive()) - 1;
            forceRehighlightLines(firstLine, lastLine);
        }
        
        return new HighlightsList(highlights);
    }

    protected void setLineState(int line, TState state) {
        synchronized (lock) {
            lineStates.set(line, state);
            if (!state.getIsDirty() && firstDirtyLine != null && firstDirtyLine.equals(line)) {
                firstDirtyLine++;
            }

            if (!state.getIsDirty() && lastDirtyLine != null && lastDirtyLine.equals(line)) {
                firstDirtyLine = null;
                lastDirtyLine = null;
            }
        }
    }

    protected abstract TState getStartState();

    protected ParseRequest<TState> adjustParseSpan(Span span) {
        int start = span.getStart();
        int end = span.getEndExclusive();

        if (firstDirtyLine != null) {
            int firstDirtyLineOffset = NbDocument.findLineOffset(document, firstDirtyLine);
            start = Math.min(start, firstDirtyLineOffset);
        }

        TState state = null;
        int startLine = NbDocument.findLineNumber(document, start);
        while (startLine > 0) {
            TState lineState = lineStates.get(startLine - 1);
            if (!lineState.getIsMultiLineToken()) {
                state = lineState;
                break;
            }

            startLine--;
        }

        if (startLine == 0) {
            state = getStartState();
        }

        start = NbDocument.findLineOffset(document, startLine);
        int length = end - start;
        ParseRequest<TState> request = new ParseRequest<TState>(new Span(start, length), state);
        return request;
    }

    protected boolean tokenSkippedLines(int endLinePrevious, CommonToken token) {
        int startLineCurrent = NbDocument.findLineNumber(this.document, token.getStartIndex());
        return startLineCurrent > endLinePrevious + 1;
    }

    protected boolean isMultiLineToken(TokenSourceWithState<TState> lexer, CommonToken token) {
        if (lexer != null && lexer.getCharStream().getLine() > token.getLine()) {
            return true;
        }

        int startLine = NbDocument.findLineNumber(this.document, token.getStartIndex());
        int stopLine = NbDocument.findLineNumber(this.document, token.getStopIndex() + 1);
        return startLine != stopLine;
    }

    protected boolean tokenEndsAtEndOfLine(TokenSourceWithState<TState> lexer, CommonToken token) {
        CharStream charStream = lexer.getCharStream();
        if (charStream != null) {
            int nextCharIndex = token.getStopIndex() + 1;
            if (nextCharIndex >= charStream.size()) {
                return true;
            }

            int c = charStream.substring(token.getStopIndex() + 1, token.getStopIndex() + 1).charAt(0);
            return c == '\r' || c == '\n';
        }

        int line = NbDocument.findLineNumber(document, token.getStopIndex() + 1);
        int lineStart = NbDocument.findLineOffset(document, line);
        int nextLineStart = NbDocument.findLineOffset(document, line + 1);
        int lineEnd = nextLineStart - 1;
        if (lineEnd > 0 && lineEnd > lineStart) {
            try {
                char c = document.getText(lineEnd - 1, 1).charAt(0);
                if (c == '\r' || c == '\n') {
                    lineEnd--;
                }
            } catch (BadLocationException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        return lineEnd <= token.getStopIndex() + 1 && nextLineStart >= token.getStopIndex() + 1;
    }

    protected CharStream createInputStream(Span span) throws BadLocationException {
        CharStream input;
        if (span.getLength() > 1000) {
            input = new DocumentCharStream(this.document, span);
        } else {
            input = new DocumentCharStream(this.document);
        }

        input.seek(span.getStart());
        return input;
    }

    protected abstract TokenSourceWithState<TState> createLexer(CharStream input, TState startState);

    protected Collection<Highlight> getHighlightsForToken(CommonToken token) {
        AttributeSet attributes = highlightToken(token);
        if (attributes != null && attributes.getAttributeCount() > 0) {
            return new SingleHighlightSequence(new Highlight(token.getStartIndex(), token.getStopIndex() + 1, attributes));
        }

        return Collections.emptyList();
    }

    protected AttributeSet highlightToken(CommonToken token) {
        return null;
    }

    public void forceRehighlightLines(int startLine, int endLineInclusive) {
        firstDirtyLine = firstDirtyLine != null ? Math.min(firstDirtyLine, startLine) : startLine;
        lastDirtyLine = lastDirtyLine != null ? Math.min(lastDirtyLine, endLineInclusive) : endLineInclusive;

        int start = NbDocument.findLineOffset(document, startLine);
        int end = (endLineInclusive == lineStates.size() - 1) ? document.getLength() : NbDocument.findLineOffset(document, endLineInclusive + 1);
        if (FIX_HIGHLIGHTER_UPDATE_BUG) {
            fireHighlightsChange(start, document.getLength());
        } else {
            fireHighlightsChange(start, end);
        }
    }

    protected void subscribeEvents() {
        this.document.addDocumentListener(this.documentListener);
    }

    protected void unsubscribeEvents() {
        this.document.removeDocumentListener(this.documentListener);
    }

    private final class DocumentListenerImpl implements DocumentListener {

        @Override
        public void changedUpdate(DocumentEvent e) {
            int lineCountDelta = NbDocument.findLineRootElement(document).getElementCount() - lineStates.size();
            int oldOffset = e.getOffset();
            int oldLength = e.getLength();
            int newOffset = e.getOffset();
            int newLength = e.getLength();

            processChange(lineCountDelta, oldOffset, oldLength, newOffset, newLength);
            processAfterChange();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            int lineCountDelta = NbDocument.findLineRootElement(document).getElementCount() - lineStates.size();
            int oldOffset = e.getOffset();
            int oldLength = 0;
            int newOffset = e.getOffset();
            int newLength = e.getLength();

            processChange(lineCountDelta, oldOffset, oldLength, newOffset, newLength);
            processAfterChange();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            int lineCountDelta = NbDocument.findLineRootElement(document).getElementCount() - lineStates.size();
            int oldOffset = e.getOffset();
            int oldLength = e.getLength();
            int newOffset = e.getOffset();
            int newLength = 0;

            processChange(lineCountDelta, oldOffset, oldLength, newOffset, newLength);
            processAfterChange();
        }

        private void processChange(int lineCountDelta, int oldOffset, int oldLength, int newOffset, int newLength) {
            synchronized (lock) {
                int lineNumberFromPosition = NbDocument.findLineNumber(document, newOffset);
                int num2 = NbDocument.findLineNumber(document, newOffset + newLength);
                if (lineCountDelta < 0) {
                    lineStates.subList(lineNumberFromPosition, lineNumberFromPosition + Math.abs(lineCountDelta));
                } else if (lineCountDelta > 0) {
                    TState endLineState = lineStates.get(lineNumberFromPosition);
                    List<TState> insertedElements = new ArrayList<TState>();
                    for (int i = 0; i < lineCountDelta; i++) {
                        insertedElements.add(endLineState);
                    }
                    lineStates.addAll(lineNumberFromPosition, insertedElements);
                }

                if (lastDirtyLine != null && lastDirtyLine > lineNumberFromPosition) {
                    lastDirtyLine += lineCountDelta;
                }

                if (lastChangedLine != null && lastChangedLine > lineNumberFromPosition) {
                    lastChangedLine += lineCountDelta;
                }

                for (int i = lineNumberFromPosition; i <= num2; i++) {
                    TState state = lineStates.get(i);
                    lineStates.set(i, state.createDirtyState());
                }

                firstChangedLine = firstChangedLine != null ? Math.min(firstChangedLine, lineNumberFromPosition) : lineNumberFromPosition;
                lastChangedLine = lastChangedLine != null ? Math.max(lastChangedLine, lineNumberFromPosition) : lineNumberFromPosition;
            }
        }

        private void processAfterChange() {
            if (firstChangedLine != null && lastChangedLine != null) {
                int startRehighlightLine = firstChangedLine;
                int endRehighlightLine = Math.min(lastChangedLine, NbDocument.findLineRootElement(document).getElementCount() - 1);

                firstChangedLine = null;
                lastChangedLine = null;

                forceRehighlightLines(startRehighlightLine, endRehighlightLine);
            }
        }
    }
}
