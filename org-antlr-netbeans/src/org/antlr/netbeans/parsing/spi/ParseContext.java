/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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