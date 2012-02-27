/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.classification;

import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;

/**
 *
 * @author Sam Harwell
 */
public class DocumentSnapshotTokenFactory implements TokenFactory<DocumentSnapshotToken> {

    private final TokenSource<? super DocumentSnapshotToken> effectiveSource;

    public DocumentSnapshotTokenFactory(TokenSource<? super DocumentSnapshotToken> effectiveSource) {
        this.effectiveSource = effectiveSource;
    }

    @Override
    public DocumentSnapshotToken create(TokenSource<? super DocumentSnapshotToken> source, int type, String text, int channel, int start, int stop, int line, int charPositionInLine) {
        if (effectiveSource != null) {
            source = effectiveSource;
        }

        DocumentSnapshotToken t = new DocumentSnapshotToken(source, type, channel, start, stop);
        t.setLine(line);
        t.setCharPositionInLine(charPositionInLine);
        if ( text!=null ) {
            t.setText(text);
        }
        return t;
    }

    @Override
    public DocumentSnapshotToken create(int type, String text) {
        return new DocumentSnapshotToken(type, text);
    }

}
