/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.antlr.works.editor.grammar.experimental;

import java.util.Iterator;
import org.antlr.netbeans.editor.classification.DocumentSnapshotCharStream;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.tagging.TaggedPositionRegion;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentSnapshotLine;
import org.antlr.netbeans.editor.text.NormalizedSnapshotPositionRegionCollection;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.netbeans.api.annotations.common.NonNull;


/**
 *
 * @author Sam Harwell
 */
public class TaggerTokenSource implements TokenSource {
    private final DocumentSnapshot snapshot;
    private final Tagger<TokenTag> tagger;
    private final SnapshotPositionRegion region;
    private final Iterable<TaggedPositionRegion<TokenTag>> tags;
    private final Iterator<TaggedPositionRegion<TokenTag>> tagIterator;
    private TokenTag previousTag;
    private CharStream input;
    private int line = -1;
    private int charPositionInLine = -1;

    public TaggerTokenSource(@NonNull Tagger<TokenTag> tagger, DocumentSnapshot snapshot) {
        this(tagger, new SnapshotPositionRegion(snapshot, 0, snapshot.length()));
    }

    public TaggerTokenSource(@NonNull Tagger<TokenTag> tagger, @NonNull SnapshotPositionRegion region) {
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
            previousTag = new TokenTag(new CommonToken(Token.EOF));
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

}
