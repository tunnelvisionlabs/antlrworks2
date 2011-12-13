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
import org.antlr.netbeans.editor.text.DocumentVersion;
import org.antlr.netbeans.editor.text.NormalizedDocumentChangeCollection;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.TrackingFidelity;
import org.antlr.netbeans.editor.text.TrackingPosition;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class NbDocumentVersion implements DocumentVersion {

    @NonNull
    private final NbVersionedDocument textBuffer;
    private final int versionNumber;
    @NonNull
    private final LineTextCache lineData;

    private NbDocumentSnapshot snapshot;

    private final Object lock = new Object();
    private NormalizedDocumentChangeCollection changes;
    private NbDocumentVersion next;

    public NbDocumentVersion(@NonNull NbVersionedDocument textBuffer, int versionNumber, @NonNull LineTextCache lineData) {
        Parameters.notNull("textBuffer", textBuffer);
        Parameters.notNull("lineData", lineData);

        this.textBuffer = textBuffer;
        this.versionNumber = versionNumber;
        this.lineData = lineData;
    }

    @Override
    public VersionedDocument getVersionedDocument() {
        return textBuffer;
    }

    @Override
    public int getLength() {
        return lineData.getLength();
    }

    @Override
    public int getVersionNumber() {
        return versionNumber;
    }

    @Override
    public NormalizedDocumentChangeCollection getChanges() {
        synchronized (lock) {
            return changes;
        }
    }

    @Override
    public DocumentVersion getNext() {
        synchronized (lock) {
            return next;
        }
    }

    @NonNull LineTextCache getLineData() {
        return lineData;
    }

    public @NonNull NbDocumentSnapshot getSnapshot() {
        synchronized (this) {
            if (snapshot == null) {
                snapshot = new NbDocumentSnapshot(this);
            }

            return snapshot;
        }
    }

    @Override
    public TrackingPositionRegion createCustomTrackingRegion(OffsetRegion region, TrackingFidelity fidelity, CustomTrackToBehavior behavior) {
        Parameters.notNull("region", region);
        Parameters.notNull("fidelity", fidelity);
        Parameters.notNull("behavior", behavior);

        return new NbCustomTrackingPositionRegion(this, region, fidelity, behavior);
    }

    @Override
    public NbTrackingPosition createTrackingPosition(int position, TrackingPosition.Bias bias) {
        Parameters.notNull("bias", bias);
        return createTrackingPosition(position, bias, TrackingFidelity.Forward);
    }

    @Override
    public NbTrackingPosition createTrackingPosition(int position, TrackingPosition.Bias bias, TrackingFidelity fidelity) {
        Parameters.notNull("bias", bias);
        Parameters.notNull("fidelity", fidelity);
        if (fidelity == TrackingFidelity.Forward) {
            return new NbForwardTrackingPosition(this, position, bias);
        }

        return new NbFullTrackingPosition(this, position, bias, fidelity);
    }

    @Override
    public NbTrackingPositionRegion createTrackingRegion(OffsetRegion region, TrackingPositionRegion.Bias bias) {
        Parameters.notNull("region", region);
        Parameters.notNull("bias", bias);
        return createTrackingRegion(region, bias, TrackingFidelity.Forward);
    }

    @Override
    public NbTrackingPositionRegion createTrackingRegion(int start, int length, TrackingPositionRegion.Bias bias) {
        Parameters.notNull("bias", bias);
        return createTrackingRegion(start, length, bias, TrackingFidelity.Forward);
    }

    @Override
    public NbTrackingPositionRegion createTrackingRegion(OffsetRegion region, TrackingPositionRegion.Bias bias, TrackingFidelity fidelity) {
        Parameters.notNull("region", region);
        Parameters.notNull("bias", bias);
        Parameters.notNull("fidelity", fidelity);
        return createTrackingRegion(region.getStart(), region.getLength(), bias, fidelity);
    }

    @Override
    public NbTrackingPositionRegion createTrackingRegion(int start, int length, TrackingPositionRegion.Bias bias, TrackingFidelity fidelity) {
        Parameters.notNull("bias", bias);
        Parameters.notNull("fidelity", fidelity);
        if (length < 0) {
            throw new IndexOutOfBoundsException();
        }

        NbTrackingPosition trackingStart = createTrackingPosition(start, bias.getStartBias(), fidelity);
        NbTrackingPosition trackingEnd = createTrackingPosition(start + length, bias.getEndBias(), fidelity);
        return new NbTrackingPositionRegion(trackingStart, trackingEnd);
    }

    @NonNull NbDocumentVersion translate(@NonNull NormalizedDocumentChangeCollection changes) {
        Parameters.notNull("changes", changes);

        synchronized (lock) {
            if (this.changes != null) {
                throw new IllegalStateException();
            }

            LineTextCache nextLineData = lineData.applyChanges(changes);
            NbDocumentVersion nextVersion = new NbDocumentVersion(textBuffer, versionNumber + 1, nextLineData);
            this.changes = changes;
            this.next = nextVersion;
            return next;
        }
    }

}
