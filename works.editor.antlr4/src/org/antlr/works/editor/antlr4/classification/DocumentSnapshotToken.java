/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.classification;

import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentSnapshotLine;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Tuple2;

/**
 *
 * @author Sam Harwell
 */
public class DocumentSnapshotToken extends CommonToken {
    private final DocumentSnapshot snapshot;

    public DocumentSnapshotToken(Tuple2<? extends TokenSource, CharStream> source, int type, int channel, int start, int stop) {
        super(source, type, channel, start, stop);
        CharStream inputStream = source.getItem2();
        if (!(inputStream instanceof DocumentSnapshotCharStream)) {
            throw new IllegalArgumentException(String.format("Expected a %s backed by a %s.", TokenSource.class.getSimpleName(), DocumentSnapshotCharStream.class.getSimpleName()));
        }

        DocumentSnapshotCharStream charStream = (DocumentSnapshotCharStream)inputStream;
        snapshot = charStream.getSnapshot();
    }

    public DocumentSnapshotToken(int type, String text) {
        super(type, text);
        snapshot = null;
    }

    public DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    @Override
    public int getLine() {
        if (snapshot != null) {
            return snapshot.findLineNumber(getStartIndex()) + 1;
        }

        return super.getLine();
    }

    @Override
    public int getCharPositionInLine() {
        if (snapshot != null) {
            DocumentSnapshotLine snapshotLine = snapshot.findLineFromOffset(getStartIndex());
            return getStartIndex() - snapshotLine.getStart().getOffset();
        }

        return super.getCharPositionInLine();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DocumentSnapshotToken)) {
            return false;
        }

        DocumentSnapshotToken other = (DocumentSnapshotToken)obj;
        return this.getSnapshot().equals(other.getSnapshot())
            && this.getStartIndex() == other.getStartIndex()
            && this.getStopIndex() == other.getStopIndex()
            && this.getType() == other.getType();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.snapshot != null ? this.snapshot.hashCode() : 0);
        hash = 31 * hash + getStartIndex();
        hash = 31 * hash + getStopIndex();
        hash = 31 * hash + getType();
        return hash;
    }

}
