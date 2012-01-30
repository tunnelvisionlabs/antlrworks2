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

import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentSnapshotLine;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.TokenSource;

/**
 *
 * @author Sam Harwell
 */
public class DocumentSnapshotToken extends CommonToken {
    private final DocumentSnapshot snapshot;

    public DocumentSnapshotToken(TokenSource source, int type, int channel, int start, int stop) {
        super(source, type, channel, start, stop);
        CharStream inputStream = source.getInputStream();
        if (!(inputStream instanceof DocumentSnapshotCharStream)) {
            throw new IllegalArgumentException("Expected a TokenSource backed by a DocumentSnapshotCharStream.");
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
            return snapshot.findLineNumber(getStartIndex());
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
