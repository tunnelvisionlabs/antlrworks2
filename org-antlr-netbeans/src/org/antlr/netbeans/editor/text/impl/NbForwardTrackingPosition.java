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
package org.antlr.netbeans.editor.text.impl;

import org.antlr.netbeans.editor.text.DocumentChange;
import org.antlr.netbeans.editor.text.DocumentVersion;
import org.antlr.netbeans.editor.text.NormalizedDocumentChangeCollection;
import org.antlr.netbeans.editor.text.TrackingFidelity;
import org.antlr.netbeans.editor.text.TrackingPosition;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class NbForwardTrackingPosition extends NbTrackingPosition {

    public NbForwardTrackingPosition(@NonNull NbDocumentVersion textVersion, int position, @NonNull TrackingPosition.Bias bias) {
        super(textVersion, position, bias);
    }

    @Override
    public TrackingFidelity getTrackingFidelity() {
        return TrackingFidelity.Forward;
    }

    @Override
    public int getOffset(DocumentVersion version, DocumentVersion cachedVersion, int cachedPosition) {
        Parameters.notNull("version", version);
        if (version.equals(this.getTextVersion())) {
            return getPosition();
        }

        if (version.equals(cachedVersion)) {
            return cachedPosition;
        }

        if (!version.getVersionedDocument().equals(this.getVersionedDocument())) {
            throw new IllegalArgumentException();
        }

        DocumentVersion sourceVersion;
        int sourcePosition;
        if (version.getVersionNumber() > cachedVersion.getVersionNumber()) {
            sourceVersion = cachedVersion;
            sourcePosition = cachedPosition;
        } else if (version.getVersionNumber() > getTextVersion().getVersionNumber()) {
            sourceVersion = getTextVersion();
            sourcePosition = getPosition();
        } else {
            throw new UnsupportedOperationException("This tracking point has forward fidelity.");
        }

        boolean positive = getBias() == TrackingPosition.Bias.Forward;
        for (DocumentVersion currentVersion = sourceVersion; currentVersion.getVersionNumber() < version.getVersionNumber(); currentVersion = currentVersion.getNext()) {
            NormalizedDocumentChangeCollection changes = currentVersion.getChanges();
            DocumentChange relevantChange = null;
            for (DocumentChange change : changes) {
                boolean relevant = sourcePosition >= change.getOldOffset();
                if (relevant) {
                    relevantChange = change;
                } else {
                    break;
                }
            }
            
            if (relevantChange != null) {
                if (sourcePosition >= relevantChange.getOldOffset() && sourcePosition <= relevantChange.getOldEnd()) {
                    sourcePosition = relevantChange.getNewOffset();
                    if (positive) {
                        sourcePosition += relevantChange.getNewLength();
                    }
                } else {
                    sourcePosition += relevantChange.getDelta();
                }
            }
        }

        return sourcePosition;
    }

    @Override
    public String toString() {
        return String.format("V%d %s@%s", getTextVersion().getVersionNumber(), getBias().toString(), getPosition());
    }
}
