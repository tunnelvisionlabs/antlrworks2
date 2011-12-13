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

import java.lang.ref.WeakReference;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.editor.BaseDocument;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class NbVersionedDocument implements VersionedDocument {

    private static final WeakReference<NbDocumentVersion> NullVersion = new WeakReference<NbDocumentVersion>(null);

    @NonNull
    private final BaseDocument document;

    @NonNull
    private NbNormalizedDocumentChangeCollection pendingChanges = new NbNormalizedDocumentChangeCollection();
    private WeakReference<NbDocumentVersion> latestVersion = NullVersion;
    private int latestVersionNumber = 0;

    public NbVersionedDocument(@NonNull BaseDocument document) {
        Parameters.notNull("document", document);

        this.document = document;
        this.document.addDocumentListener(new Listener());
    }

    @Override
    public BaseDocument getDocument() {
        return document;
    }

    @Override
    public DocumentSnapshot getCurrentSnapshot() {
        return applyChanges().getSnapshot();
    }

    @Override
    public String getMimeType() {
        return (String)document.getProperty(BaseDocument.MIME_TYPE_PROP);
    }

    @Override
    public FileObject getFileObject() {
        Object property = document.getProperty(Document.StreamDescriptionProperty);
        if (property instanceof FileObject) {
            return (FileObject)property;
        } else if (property instanceof DataObject) {
            return ((DataObject)property).getPrimaryFile();
        }

        throw new UnsupportedOperationException("Couldn't get the document's FileObject.");
    }

    private @NonNull NbDocumentVersion applyChanges() {
        document.readLock();
        try {
            NbDocumentVersion version = latestVersion.get();
            if (pendingChanges.isEmpty() && version != null) {
                return version;
            }

            if (version == null) {
                try {
                    version = new NbDocumentVersion(this, latestVersionNumber + 1, new LineTextCache(document.getText(0, document.getLength())));
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                    throw new IllegalStateException("Shouldn't be reachable.", ex);
                }
            } else if (true) {
                version = version.translate(pendingChanges);
            }

            latestVersion = new WeakReference<NbDocumentVersion>(version);
            latestVersionNumber = version.getVersionNumber();
            pendingChanges = new NbNormalizedDocumentChangeCollection();
            return version;
        } finally {
            document.readUnlock();
        }
    }

    private void addPendingChange(@NonNull NbDocumentChange change) {
        Parameters.notNull("change", change);
        pendingChanges.add(change);
    }

    private class Listener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            if (latestVersion.get() == null) {
                return;
            }

            int offset = e.getOffset();
            String text = DocumentUtilities.getModificationText(e);
            NbDocumentChange textChange = new NbDocumentChange(offset, "", offset, text);
            addPendingChange(textChange);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (latestVersion.get() == null) {
                return;
            }

            int offset = e.getOffset();
            String text = DocumentUtilities.getModificationText(e);
            NbDocumentChange textChange = new NbDocumentChange(offset, text, offset, "");
            addPendingChange(textChange);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (latestVersion.get() == null) {
                return;
            }

            //throw new UnsupportedOperationException("Not implemented yet.");
        }
    }
}
