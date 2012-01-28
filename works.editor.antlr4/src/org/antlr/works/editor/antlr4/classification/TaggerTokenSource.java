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

import java.util.Iterator;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.tagging.TaggedPositionRegion;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentSnapshotLine;
import org.antlr.netbeans.editor.text.NormalizedSnapshotPositionRegionCollection;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class TaggerTokenSource implements TokenSource {
    private final DocumentSnapshot snapshot;
    private final Tagger<TokenTag<Token>> tagger;
    private final SnapshotPositionRegion region;
    private final Iterable<TaggedPositionRegion<TokenTag<Token>>> tags;
    private final Iterator<TaggedPositionRegion<TokenTag<Token>>> tagIterator;
    private TokenTag<Token> previousTag;
    private CharStream input;
    private int line = -1;
    private int charPositionInLine = -1;
    private TokenFactory<?> tokenFactory = CommonTokenFactory.DEFAULT;

    public TaggerTokenSource(@NonNull Tagger<TokenTag<Token>> tagger, DocumentSnapshot snapshot) {
        this(tagger, new SnapshotPositionRegion(snapshot, 0, snapshot.length()));
    }

    public TaggerTokenSource(@NonNull Tagger<TokenTag<Token>> tagger, @NonNull SnapshotPositionRegion region) {
        this.snapshot = region.getSnapshot();
        this.tagger = tagger;
        this.region = region;
        this.tags = this.tagger.getTags(new NormalizedSnapshotPositionRegionCollection(region));
        this.tagIterator = this.tags.iterator();
    }

    @Override
    public Token nextToken() {
        if (previousTag != null && previousTag.getToken().getType() == Token.EOF) {
            return previousTag.getToken();
        }

        if (tagIterator.hasNext()) {
            previousTag = tagIterator.next().getTag();
        } else {
            previousTag = new TokenTag<Token>(tokenFactory.create(Token.EOF, null));
        }

        line = -1;
        charPositionInLine = -1;
        return previousTag.getToken();
    }

    @Override
    public int getLine() {
        updateLineInformation();
        return line;
    }

    @Override
    public int getCharPositionInLine() {
        updateLineInformation();
        return charPositionInLine;
    }

    private void updateLineInformation() {
        if (line == -1) {
            if (previousTag == null) {
                if (region.getStart().getOffset() == 0) {
                    line = 1;
                    charPositionInLine = 0;
                } else {
                    DocumentSnapshotLine snapshotLine = snapshot.findLineFromOffset(region.getStart().getOffset() + 1);
                    line = snapshotLine.getLineNumber() + 1;
                    charPositionInLine = region.getStart().getOffset() - snapshotLine.getStart().getOffset();
                }
            } else if (previousTag.getToken().getType() == Token.EOF) {
                DocumentSnapshotLine snapshotLine = snapshot.findLineFromLineNumber(snapshot.getLineCount() - 1);
                line = snapshotLine.getLineNumber() + 1;
                charPositionInLine = snapshotLine.getLength();
            } else {
                DocumentSnapshotLine snapshotLine = snapshot.findLineFromOffset(previousTag.getToken().getStopIndex() + 1);
                line = snapshotLine.getLineNumber() + 1;
                charPositionInLine = previousTag.getToken().getStopIndex() + 1 - snapshotLine.getStart().getOffset();
            }
        }
    }

    @Override
    public CharStream getInputStream() {
        if (input == null) {
            input = new DocumentSnapshotCharStream(snapshot);
        }
        return input;
    }

    @Override
    public String getSourceName() {
        return snapshot.getVersionedDocument().getFileObject().getName();
    }

    @Override
    public void setTokenFactory(TokenFactory<?> tokenFactory) {
        if (tokenFactory == null) {
            tokenFactory = CommonTokenFactory.DEFAULT;
        }

        this.tokenFactory = tokenFactory;
    }
}
