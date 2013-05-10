/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class ParseContext {
    @NonNull
    private final Class<? extends ParserTaskScheduler> schedulerClass;
    @NonNull
    private final VersionedDocument document;

    private final DocumentSnapshot snapshot;
    private final SnapshotPosition position;
    private final Reference<JTextComponent> component;

    public ParseContext(@NonNull Class<? extends ParserTaskScheduler> scheduler, @NonNull VersionedDocument document) {
        this(scheduler, document, null, null, null);
    }

    public ParseContext(@NonNull Class<? extends ParserTaskScheduler> scheduler, @NonNull VersionedDocument document, @NullAllowed JTextComponent component) {
        this(scheduler, document, null, null, component);
    }

    public ParseContext(@NonNull Class<? extends ParserTaskScheduler> scheduler, @NonNull DocumentSnapshot snapshot) {
        this(scheduler, snapshot.getVersionedDocument(), snapshot, null, null);
    }

    public ParseContext(@NonNull Class<? extends ParserTaskScheduler> scheduler, @NonNull DocumentSnapshot snapshot, @NullAllowed JTextComponent component) {
        this(scheduler, snapshot.getVersionedDocument(), snapshot, null, component);
    }

    public ParseContext(@NonNull Class<? extends ParserTaskScheduler> scheduler, @NonNull SnapshotPosition position) {
        this(scheduler, position.getSnapshot().getVersionedDocument(), position.getSnapshot(), position, null);
    }

    public ParseContext(@NonNull Class<? extends ParserTaskScheduler> scheduler, @NonNull SnapshotPosition position, @NullAllowed JTextComponent component) {
        this(scheduler, position.getSnapshot().getVersionedDocument(), position.getSnapshot(), position, component);
    }

    public ParseContext(@NonNull Class<? extends ParserTaskScheduler> schedulerClass, @NonNull VersionedDocument document, @NullAllowed DocumentSnapshot snapshot, @NullAllowed SnapshotPosition position, @NullAllowed JTextComponent component) {
        Parameters.notNull("schedulerClass", schedulerClass);
        Parameters.notNull("document", document);

        this.schedulerClass = schedulerClass;
        this.document = document;
        this.snapshot = snapshot;
        this.position = position;
        this.component = new WeakReference<>(component);
    }

    @NonNull
    public Class<? extends ParserTaskScheduler> getSchedulerClass() {
        return schedulerClass;
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
        return component.get();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ParseContext)) {
            return false;
        }

        ParseContext other = (ParseContext)obj;
        return this.document.equals(other.document)
            && (this.snapshot == other.snapshot || (this.snapshot != null && this.snapshot.equals(other.snapshot)))
            && (this.position == other.position || (this.position != null && this.position.equals(other.position)))
            && (this.component == other.component || (this.component != null && this.component.equals(other.component)))
            && this.schedulerClass.equals(other.schedulerClass);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.document.hashCode();
        hash = 43 * hash + (this.snapshot != null ? this.snapshot.hashCode() : 0);
        hash = 43 * hash + (this.position != null ? this.position.hashCode() : 0);
        hash = 43 * hash + (this.component != null ? this.component.hashCode() : 0);
        hash = 43 * hash + this.schedulerClass.hashCode();
        return hash;
    }

}
