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
package org.antlr.netbeans.parsing.spi;

import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class ParseContext {
    private final VersionedDocument document;
    private final DocumentSnapshot snapshot;
    private final SnapshotPosition position;
    private final JTextComponent component;
    private final ParserTaskScheduler scheduler;

    public ParseContext(VersionedDocument document) {
        this(null, document, null, null, null);
    }

    public ParseContext(VersionedDocument document, JTextComponent component) {
        this(null, document, null, null, component);
    }

    public ParseContext(DocumentSnapshot snapshot) {
        this(null, snapshot.getVersionedDocument(), snapshot, null, null);
    }

    public ParseContext(DocumentSnapshot snapshot, JTextComponent component) {
        this(null, snapshot.getVersionedDocument(), snapshot, null, component);
    }

    public ParseContext(@NonNull SnapshotPosition position) {
        this(null, position.getSnapshot().getVersionedDocument(), position.getSnapshot(), position, null);
    }

    public ParseContext(@NonNull SnapshotPosition position, JTextComponent component) {
        this(null, position.getSnapshot().getVersionedDocument(), position.getSnapshot(), position, component);
    }

    public ParseContext(ParserTaskScheduler scheduler, VersionedDocument document) {
        this(scheduler, document, null, null, null);
    }

    public ParseContext(ParserTaskScheduler scheduler, VersionedDocument document, JTextComponent component) {
        this(scheduler, document, null, null, component);
    }

    public ParseContext(ParserTaskScheduler scheduler, DocumentSnapshot snapshot) {
        this(scheduler, snapshot.getVersionedDocument(), snapshot, null, null);
    }

    public ParseContext(ParserTaskScheduler scheduler, DocumentSnapshot snapshot, JTextComponent component) {
        this(scheduler, snapshot.getVersionedDocument(), snapshot, null, component);
    }

    public ParseContext(ParserTaskScheduler scheduler, @NonNull SnapshotPosition position) {
        this(scheduler, position.getSnapshot().getVersionedDocument(), position.getSnapshot(), position, null);
    }

    public ParseContext(ParserTaskScheduler scheduler, @NonNull SnapshotPosition position, JTextComponent component) {
        this(scheduler, position.getSnapshot().getVersionedDocument(), position.getSnapshot(), position, component);
    }

    public ParseContext(ParserTaskScheduler scheduler, VersionedDocument document, DocumentSnapshot snapshot, SnapshotPosition position, JTextComponent component) {
        Parameters.notNull("document", document);
        this.document = document;
        this.snapshot = snapshot;
        this.position = position;
        this.component = component;
        this.scheduler = scheduler;
    }

    @NonNull
    public VersionedDocument getDocument() {
        return document;
    }

    @CheckForNull
    public DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    @CheckForNull
    public SnapshotPosition getPosition() {
        return position;
    }

    @CheckForNull
    public JTextComponent getComponent() {
        return component;
    }

    @CheckForNull
    public ParserTaskScheduler getScheduler() {
        return scheduler;
    }

}
