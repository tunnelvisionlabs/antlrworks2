/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
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
package org.antlr.works.editor.antlr4.classification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.text.BadLocationException;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.highlighting.LineStateInfo;
import org.antlr.netbeans.editor.tagging.AbstractTagger;
import org.antlr.netbeans.editor.tagging.BaseTaggedPositionRegion;
import org.antlr.netbeans.editor.tagging.TaggedPositionRegion;
import org.antlr.netbeans.editor.text.DocumentChange;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentVersion;
import org.antlr.netbeans.editor.text.NormalizedDocumentChangeCollection;
import org.antlr.netbeans.editor.text.NormalizedSnapshotPositionRegionCollection;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.antlr4.classification.DocumentSnapshotCharStream;
import org.antlr.works.editor.antlr4.highlighting.TokenSourceWithStateV4;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractTokensTaskTaggerSnapshot<TState extends LineStateInfo<TState>> extends AbstractTagger<TokenTag<Token>> {

    @NonNull
    private final DocumentSnapshot snapshot;

    private final Object lock = new Object();
    private final ArrayList<TState> lineStates = new ArrayList<TState>();

    private Integer firstDirtyLine;
    private Integer lastDirtyLine;

    private boolean failedTimeout;

    public AbstractTokensTaskTaggerSnapshot(@NonNull DocumentSnapshot snapshot) {
        Parameters.notNull("snapshot", snapshot);
        this.snapshot = snapshot;
    }

    protected AbstractTokensTaskTaggerSnapshot(@NonNull AbstractTokensTaskTaggerSnapshot<TState> reference, @NonNull DocumentSnapshot snapshot) {
        Parameters.notNull("reference", reference);
        Parameters.notNull("snapshot", snapshot);

        if (!snapshot.getVersionedDocument().equals(reference.snapshot.getVersionedDocument())) {
            throw new IllegalArgumentException("The target snapshot is not from the same document.");
        }

        if (snapshot.getVersion().getVersionNumber() <= reference.snapshot.getVersion().getVersionNumber()) {
            throw new UnsupportedOperationException("The target snapshot must be a future version of the reference document.");
        }

        this.snapshot = snapshot;
        this.lineStates.addAll(reference.lineStates);
        this.firstDirtyLine = reference.firstDirtyLine;
        this.lastDirtyLine = reference.lastDirtyLine;
        Integer firstChangedLine = null;
        Integer lastChangedLine = null;

        for (DocumentVersion version = reference.snapshot.getVersion(); version.getVersionNumber() < snapshot.getVersion().getVersionNumber(); version = version.getNext()) {
            DocumentSnapshot source = version.getSnapshot();
            DocumentSnapshot target = version.getNext().getSnapshot();
            NormalizedDocumentChangeCollection changes = version.getChanges();
            for (int i = changes.size() - 1; i >= 0; i--) {
                DocumentChange change = changes.get(i);
                int lineCountDelta = change.getLineCountDelta();
                int oldOffset = change.getOldOffset();
                int oldLength = change.getOldLength();
                int newOffset = change.getNewOffset();
                int newLength = change.getNewLength();

                /* processChange */
                int oldStartLine = source.findLineNumber(oldOffset);
                int oldEndLine = oldLength == 0 ? oldStartLine : source.findLineNumber(oldOffset + oldLength - 1);
                if (lineCountDelta < 0) {
                    lineStates.subList(oldStartLine, oldStartLine + Math.abs(lineCountDelta)).clear();
                } else if (lineCountDelta > 0) {
                    TState endLineState = lineStates.get(oldStartLine);
                    ArrayList<TState> insertedElements = new ArrayList<TState>();
                    for (int j = 0; j < lineCountDelta; j++) {
                        insertedElements.add(endLineState);
                    }
                    lineStates.addAll(oldStartLine, insertedElements);
                }

                if (lastDirtyLine != null && lastDirtyLine > oldStartLine) {
                    lastDirtyLine += lineCountDelta;
                }

                if (lastChangedLine != null && lastChangedLine > oldStartLine) {
                    lastChangedLine += lineCountDelta;
                }

                for (int j = oldStartLine; j <= oldEndLine + lineCountDelta; j++) {
                    TState state = lineStates.get(i);
                    lineStates.set(j, state.createDirtyState());
                }

                firstChangedLine = firstChangedLine != null ? Math.min(firstChangedLine, oldStartLine) : oldStartLine;
                lastChangedLine = lastChangedLine != null ? Math.max(lastChangedLine, oldEndLine) : oldEndLine;

                /* processAfterChange */
                if (firstChangedLine != null && lastChangedLine != null) {
                    int startLine = firstChangedLine;
                    int endLineInclusive = Math.min(lastChangedLine, source.getLineCount() - 1);

                    firstChangedLine = null;
                    lastChangedLine = null;

                    /* forceRehighlightLines(startRehighlightLine, endRehighlightLine); */
                    firstDirtyLine = firstDirtyLine != null ? Math.min(firstDirtyLine, startLine) : startLine;
                    lastDirtyLine = lastDirtyLine != null ? Math.max(lastDirtyLine, endLineInclusive) : endLineInclusive;

//                    int start = currentSnapshot.findLineFromLineNumber(startLine).getStart().getOffset();
//                    int end = (endLineInclusive == lineStates.size() - 1) ? currentSnapshot.length() : currentSnapshot.findLineFromLineNumber(endLineInclusive + 1).getStart().getOffset();
//                    if (FIX_HIGHLIGHTER_UPDATE_BUG) {
//                        fireHighlightsChange(start, document.getLength());
//                    } else {
//                        fireHighlightsChange(start, end);
//                    }
                }
            }
        }
    }

    public void initialize() {
        int lineCount = snapshot.getLineCount();
        if (lineStates.size() != lineCount) {
            lineStates.ensureCapacity(lineCount);
            TState dirtyState = getStartState().createDirtyState();
            for (int i = lineStates.size(); i < lineCount; i++) {
                lineStates.add(dirtyState);
            }

            forceRehighlightLines(0, lineCount);
        }
    }

    @Override
    public Iterable<TaggedPositionRegion<TokenTag<Token>>> getTags(NormalizedSnapshotPositionRegionCollection regions) {
        Parameters.notNull("regions", regions);
        if (regions.isEmpty()) {
            return Collections.emptyList();
        }

        DocumentSnapshot requestedSnapshot = regions.get(0).getSnapshot();
        if (!requestedSnapshot.equals(snapshot)) {
            throw new UnsupportedOperationException("This tagger snapshot only supports requests from the same document.");
        }

        int startOffset = regions.get(0).getStart().getOffset();
        int endOffset = regions.get(regions.size() - 1).getEnd().getOffset();

        return getHighlights(startOffset, endOffset);
    }

    public List<TaggedPositionRegion<TokenTag<Token>>> getHighlights(int startOffset, int endOffset) {
        List<TaggedPositionRegion<TokenTag<Token>>> tags = new ArrayList<TaggedPositionRegion<TokenTag<Token>>>();
        boolean updateOffsets = true;

        if (endOffset == Integer.MAX_VALUE) {
            endOffset = snapshot.length();
        }

        OffsetRegion span = OffsetRegion.fromBounds(startOffset, endOffset);

        if (failedTimeout) {
            return tags;
        }

        boolean spanExtended = false;

        int extendMultiLineSpanToLine = 0;
        OffsetRegion extendedSpan = span;

        synchronized (lock) {
            OffsetRegion requestedSpan = span;

            ParseRequest<TState> request = adjustParseSpan(span);
            TState startState = request.getState();
            span = request.getRegion();

            CharStream input;
            try {
                input = createInputStream(span);
            } catch (BadLocationException ex) {
                return tags;
            }

            TokenSourceWithStateV4<TState> lexer = createLexer(input, startState);

            Token previousToken = null;
            boolean previousTokenEndsLine = false;

            /* this is held outside the loop because only tokens which end at the end of a line
             * impact its value.
             */
            boolean lineStateChanged = false;

            while (true)
            {
                // TODO: perform this under a read lock
                Token token = lexer.nextToken();

                // The latter is true for EOF token with span.getEnd() at the end of the document
                boolean inBounds = token.getStartIndex() < span.getEnd()
                    || token.getStopIndex() < span.getEnd();

                if (updateOffsets) {
                    int startLineCurrent;
                    if (token.getType() == Token.EOF)
                        startLineCurrent = snapshot.getLineCount();
                    else
                        startLineCurrent = snapshot.findLineNumber(token.getStartIndex());

//                    if (previousToken == null || previousToken.getLine() < startLineCurrent - 1)
//                    {
                        // endLinePrevious is the line number the previous token ended on
                        int endLinePrevious;
                        if (previousToken != null)
                            endLinePrevious = snapshot.findLineNumber(previousToken.getStopIndex() + 1);
                        else
                            endLinePrevious = snapshot.findLineNumber(span.getStart()) - 1;

                        if (startLineCurrent > endLinePrevious + 1 || (startLineCurrent == endLinePrevious + 1 && !previousTokenEndsLine))
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
//                    }
                }

                if (token.getType() == Token.EOF)
                    break;

                if (updateOffsets && isMultiLineToken(lexer, token))
                {
                    int startLine = snapshot.findLineNumber(token.getStartIndex());
                    int stopLine = snapshot.findLineNumber(token.getStopIndex() + 1);
                    for (int i = startLine; i < stopLine; i++)
                    {
                        if (!lineStates.get(i).getIsMultiLineToken())
                            extendMultiLineSpanToLine = i + 1;

                        if (inBounds)
                            setLineState(i, lineStates.get(i).createMultiLineState());
                    }
                }

                boolean tokenEndsLine = tokenEndsAtEndOfLine(lexer, token);
                if (updateOffsets && tokenEndsLine)
                {
                    TState stateAtEndOfLine = lexer.getState();
                    int line = snapshot.findLineNumber(token.getStopIndex() + 1);
                    lineStateChanged =
                        lineStates.get(line).getIsMultiLineToken()
                        || !lineStates.get(line).equals(stateAtEndOfLine);

                    // even if the state didn't change, we call SetLineState to make sure the _first/_lastChangedLine values get updated.
                    // have to check bounds for this one or the editor might not get an update (if the token ends a line)
                    if (updateOffsets && inBounds)
                        setLineState(line, stateAtEndOfLine);

                    if (lineStateChanged)
                    {
                        if (line < snapshot.getLineCount() - 1)
                        {
                            /* update the span's end position or the line state change won't be reflected
                             * in the editor
                             */
                            int endPosition = line < snapshot.getLineCount() - 2 ? snapshot.findLineFromOffset(line + 2).getStart().getOffset() : snapshot.length();
                            if (endPosition > extendedSpan.getEnd())
                            {
                                spanExtended = true;
                                extendedSpan = OffsetRegion.fromBounds(extendedSpan.getStart(), endPosition);
                            }
                        }
                    }
                }

                previousToken = token;
                previousTokenEndsLine = tokenEndsLine;

                if (token.getStartIndex() >= span.getEnd()) {
                    break;
                }

                if (token.getStopIndex() < requestedSpan.getStart()) {
                    continue;
                }

                Collection<TaggedPositionRegion<TokenTag<Token>>> tokenClassificationSpans = getTagsForToken(token);
                if (tokenClassificationSpans != null) {
                    tags.addAll(tokenClassificationSpans);
                }

                if (!inBounds) {
                    break;
                }
            }
        }

        if (updateOffsets && extendMultiLineSpanToLine > 0) {
            int endPosition = extendMultiLineSpanToLine < snapshot.getLineCount() - 1 ? snapshot.findLineFromOffset(extendMultiLineSpanToLine + 1).getStart().getOffset() : snapshot.length();
            if (endPosition > extendedSpan.getEnd()) {
                spanExtended = true;
                extendedSpan = OffsetRegion.fromBounds(extendedSpan.getStart(), endPosition);
            }
        }

        if (updateOffsets && spanExtended) {
            /* Subtract 1 from each of these because the spans include the line break on their last
             * line, forcing it to appear as the first position on the following line.
             */
            int firstLine = snapshot.findLineNumber(span.getEnd());
            int lastLine = snapshot.findLineNumber(extendedSpan.getEnd()) - 1;
            forceRehighlightLines(firstLine, lastLine);
        }

        return tags;
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

    protected ParseRequest<TState> adjustParseSpan(OffsetRegion span) {
        int start = span.getStart();
        int end = span.getEnd();

        if (firstDirtyLine != null) {
            int firstDirtyLineOffset = snapshot.findLineFromOffset(firstDirtyLine).getStart().getOffset();
            start = Math.min(start, firstDirtyLineOffset);
        }

        TState state = null;
        int startLine = snapshot.findLineNumber(start);
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

        start = snapshot.findLineFromOffset(startLine).getStart().getOffset();
        int length = end - start;
        ParseRequest<TState> request = new ParseRequest<TState>(new OffsetRegion(start, length), state);
        return request;
    }

    protected boolean tokenSkippedLines(int endLinePrevious, Token token) {
        int startLineCurrent = snapshot.findLineNumber(token.getStartIndex());
        return startLineCurrent > endLinePrevious + 1;
    }

    protected boolean isMultiLineToken(TokenSourceWithStateV4<TState> lexer, Token token) {
        /*if (lexer != null && lexer.getLine() > token.getLine()) {
            return true;
        }*/

        int startLine = snapshot.findLineNumber(token.getStartIndex());
        int stopLine = snapshot.findLineNumber(token.getStopIndex() + 1);
        return startLine != stopLine;
    }

    protected boolean tokenEndsAtEndOfLine(TokenSourceWithStateV4<TState> lexer, Token token) {
        CharStream charStream = lexer.getCharStream();
        if (charStream != null) {
            int nextCharIndex = token.getStopIndex() + 1;
            if (nextCharIndex >= charStream.size()) {
                return true;
            }

            int c = charStream.substring(token.getStopIndex() + 1, token.getStopIndex() + 1).charAt(0);
            return c == '\r' || c == '\n';
        }

        if (token.getStopIndex() + 1 >= snapshot.length()) {
            return true;
        }

        char c = snapshot.subSequence(token.getStopIndex() + 1, token.getStopIndex() + 1).charAt(0);
        return c == '\r' || c == '\n';

        /*int line = snapshot.findLineNumber(token.getStopIndex() + 1);
        int lineStart = snapshot.findLineFromOffset(line).getStart().getOffset();
        int nextLineStart = snapshot.findLineFromOffset(line + 1).getStart().getOffset();
        int lineEnd = nextLineStart - 1;
        if (lineEnd > 0 && lineEnd > lineStart) {
            char c = snapshot.charAt(lineEnd - 1);
            if (c == '\r' || c == '\n') {
                lineEnd--;
            }
        }

        return lineEnd <= token.getStopIndex() + 1 && nextLineStart >= token.getStopIndex() + 1;*/
    }

    protected CharStream createInputStream(OffsetRegion span) throws BadLocationException {
        CharStream input;
        if (span.getLength() > 1000) {
            input = new DocumentSnapshotCharStream(snapshot, span);
        } else {
            input = new DocumentSnapshotCharStream(snapshot);
        }

        input.seek(span.getStart());
        return input;
    }

    protected abstract TokenSourceWithStateV4<TState> createLexer(CharStream input, TState startState);

    protected Collection<TaggedPositionRegion<TokenTag<Token>>> getTagsForToken(Token token) {
        TokenTag<Token> tag = highlightToken(token);
        if (tag != null) {
            return Collections.<TaggedPositionRegion<TokenTag<Token>>>singleton(new BaseTaggedPositionRegion<TokenTag<Token>>(new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(token.getStartIndex(), token.getStopIndex() + 1)), tag));
        }

        return Collections.emptyList();
    }

    protected TokenTag<Token> highlightToken(Token token) {
        return new TokenTag<Token>(token);
    }

    public void forceRehighlightLines(int startLine, int endLineInclusive) {
        firstDirtyLine = firstDirtyLine != null ? Math.min(firstDirtyLine, startLine) : startLine;
        lastDirtyLine = lastDirtyLine != null ? Math.max(lastDirtyLine, endLineInclusive) : endLineInclusive;

//        int start = snapshot.findLineFromOffset(startLine).getStart().getOffset();
//        int end = (endLineInclusive == lineStates.size() - 1) ? snapshot.length() : snapshot.findLineFromOffset(endLineInclusive + 1).getStart().getOffset();
//        if (FIX_HIGHLIGHTER_UPDATE_BUG) {
//            fireHighlightsChange(start, snapshot.length());
//        } else {
//            fireHighlightsChange(start, end);
//        }
    }

    public final @NonNull AbstractTokensTaskTaggerSnapshot<TState> translateTo(@NonNull DocumentSnapshot targetSnapshot) {
        Parameters.notNull("targetSnapshot", targetSnapshot);
        if (targetSnapshot.equals(snapshot)) {
            return this;
        }

        if (!targetSnapshot.getVersionedDocument().equals(snapshot.getVersionedDocument())) {
            throw new IllegalArgumentException("The target snapshot is not from the same document.");
        }

        if (targetSnapshot.getVersion().getVersionNumber() < snapshot.getVersion().getVersionNumber()) {
            throw new UnsupportedOperationException("The target snapshot is from an earlier version of the document.");
        }

        return translateToImpl(targetSnapshot);
    }

    protected abstract AbstractTokensTaskTaggerSnapshot<TState> translateToImpl(@NonNull DocumentSnapshot targetSnapshot);

    public static class ParseRequest<TState> {
        private final OffsetRegion region;
        private final TState state;

        public ParseRequest(OffsetRegion region, TState state) {
            this.region = region;
            this.state = state;
        }

        public OffsetRegion getRegion() {
            return region;
        }

        public TState getState() {
            return state;
        }
    }
}
