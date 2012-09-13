/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class TaggerTokenSource<Symbol extends Token> implements TokenSource<Symbol> {
    private final DocumentSnapshot snapshot;
    private final Tagger<TokenTag<Symbol>> tagger;
    private final SnapshotPositionRegion region;
    private final Iterable<TaggedPositionRegion<TokenTag<Symbol>>> tags;
    private final Iterator<TaggedPositionRegion<TokenTag<Symbol>>> tagIterator;
    private TokenTag<Symbol> previousTag;
    private CharStream input;
    private int line = -1;
    private int charPositionInLine = -1;
    @SuppressWarnings("unchecked")
    private TokenFactory<? extends Symbol> tokenFactory = (TokenFactory<? extends Symbol>)CommonTokenFactory.DEFAULT;

    public TaggerTokenSource(@NonNull Tagger<TokenTag<Symbol>> tagger, DocumentSnapshot snapshot) {
        this(tagger, new SnapshotPositionRegion(snapshot, 0, snapshot.length()));
    }

    public TaggerTokenSource(@NonNull Tagger<TokenTag<Symbol>> tagger, @NonNull SnapshotPositionRegion region) {
        this.snapshot = region.getSnapshot();
        this.tagger = tagger;
        this.region = region;
        this.tags = this.tagger.getTags(new NormalizedSnapshotPositionRegionCollection(region));
        this.tagIterator = this.tags.iterator();
    }

    @Override
    public Symbol nextToken() {
        if (previousTag != null && previousTag.getToken().getType() == Token.EOF) {
            return previousTag.getToken();
        }

        if (tagIterator.hasNext()) {
            previousTag = tagIterator.next().getTag();
        } else {
            TokenSource<Symbol> source = this;
            String text = null;
            int channel = Token.DEFAULT_CHANNEL;
            int start = snapshot.length();
            int stop = start - 1;
            int line = snapshot.getLineCount();
            int charPositionInLine = snapshot.findLineFromLineNumber(line - 1).getLength();
            previousTag = new TokenTag<Symbol>(tokenFactory.create(source, Token.EOF, text, channel, start, stop, line, charPositionInLine));
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
        FileObject fileObject = snapshot.getVersionedDocument().getFileObject();
        if (fileObject == null) {
            return "Unknown Source File";
        }

        return fileObject.toURL().getFile();
    }

    @Override
    public TokenFactory<? extends Symbol> getTokenFactory() {
        return tokenFactory;
    }

    @Override
    public void setTokenFactory(TokenFactory<? extends Symbol> tokenFactory) {
        Parameters.notNull("tokenFactory", tokenFactory);
        this.tokenFactory = tokenFactory;
    }
}
