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

import org.antlr.netbeans.editor.text.CustomTrackToBehavior;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentVersion;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingFidelity;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class NbCustomTrackingPositionRegion implements TrackingPositionRegion {
    @NonNull
    private final NbDocumentVersion documentVersion;
    @NonNull
    private final OffsetRegion span;
    @NonNull
    private final TrackingFidelity fidelity;
    @NonNull
    private final CustomTrackToBehavior behavior;

    public NbCustomTrackingPositionRegion(@NonNull NbDocumentVersion documentVersion, @NonNull OffsetRegion span, @NonNull TrackingFidelity fidelity, @NonNull CustomTrackToBehavior behavior) {
        Parameters.notNull("documentVersion", documentVersion);
        Parameters.notNull("span", span);
        Parameters.notNull("fidelity", fidelity);
        Parameters.notNull("behavior", behavior);

        this.documentVersion = documentVersion;
        this.span = span;
        this.fidelity = fidelity;
        this.behavior = behavior;
    }

    @Override
    public VersionedDocument getVersionedDocument() {
        return documentVersion.getVersionedDocument();
    }

    @Override
    public TrackingFidelity getTrackingFidelity() {
        return fidelity;
    }

    @Override
    public TrackingPositionRegion.Bias getBias() {
        return TrackingPositionRegion.Bias.Custom;
    }

    @Override
    public SnapshotPosition getStartPosition(DocumentSnapshot snapshot) {
        OffsetRegion targetSpan = behavior.trackRegion(this, documentVersion, snapshot.getVersion(), span);
        return new SnapshotPosition(snapshot, targetSpan.getStart());
    }

    @Override
    public SnapshotPosition getEndPosition(DocumentSnapshot snapshot) {
        OffsetRegion targetSpan = behavior.trackRegion(this, documentVersion, snapshot.getVersion(), span);
        return new SnapshotPosition(snapshot, targetSpan.getEnd());
    }

    @Override
    public SnapshotPositionRegion getRegion(DocumentSnapshot snapshot) {
        OffsetRegion targetSpan = behavior.trackRegion(this, documentVersion, snapshot.getVersion(), span);
        return new SnapshotPositionRegion(snapshot, targetSpan);
    }

    @Override
    public SnapshotPositionRegion getRegion(DocumentVersion version) {
        Parameters.notNull("version", version);
        if (!(version instanceof NbDocumentVersion)) {
            throw new UnsupportedOperationException();
        }

        OffsetRegion targetSpan = behavior.trackRegion(this, documentVersion, version, span);
        return new SnapshotPositionRegion(((NbDocumentVersion)version).getSnapshot(), targetSpan);
    }

    @Override
    public String getText(DocumentSnapshot snapshot) {
        return getRegion(snapshot).getText();
    }

}
