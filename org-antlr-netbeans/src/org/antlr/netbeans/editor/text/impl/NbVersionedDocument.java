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
package org.antlr.netbeans.editor.text.impl;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.editor.BaseDocument;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class NbVersionedDocument implements VersionedDocument {

    private static final WeakReference<NbDocumentVersion> NullVersion = new WeakReference<NbDocumentVersion>(null);

    @NullAllowed
    private final BaseDocument document;
    @NullAllowed
    private final FileObject fileObject;

    @NonNull
    private final Map<Object, Object> properties = new HashMap<Object, Object>();

    @NonNull
    private NbNormalizedDocumentChangeCollection pendingChanges = new NbNormalizedDocumentChangeCollection();
    private Reference<NbDocumentVersion> latestVersion = NullVersion;
    private int latestVersionNumber = 0;

    public NbVersionedDocument(@NonNull BaseDocument document) {
        Parameters.notNull("document", document);

        this.document = document;
        this.document.addDocumentListener(new Listener());
        this.fileObject = null;
    }

    public NbVersionedDocument(@NonNull FileObject fileObject) {
        Parameters.notNull("fileObject", fileObject);

        this.document = null;
        this.fileObject = fileObject;
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
        if (fileObject != null) {
            return fileObject.getMIMEType();
        }

        assert document != null;
        return DocumentUtilities.getMimeType(document);
    }

    @Override
    public FileObject getFileObject() {
        if (fileObject != null) {
            return fileObject;
        }

        assert document != null;
        return NbEditorUtilities.getFileObject(document);
    }

    @Override
    public Object getProperty(Object key) {
        return properties.get(key);
    }

    @Override
    public Object putProperty(Object key, Object value) {
        return properties.put(key, value);
    }

    private @NonNull NbDocumentVersion applyChanges() {
        if (document == null) {
            assert fileObject != null;
            assert pendingChanges.isEmpty();

            NbDocumentVersion version = latestVersion.get();
            if (version != null) {
                return version;
            }

            try {
                version = new NbDocumentVersion(this, latestVersionNumber + 1, new LineTextCache(fileObject.asText()));
                latestVersion = new SoftReference<NbDocumentVersion>(version);
                latestVersionNumber = version.getVersionNumber();
                pendingChanges = new NbNormalizedDocumentChangeCollection();
                return version;
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
                throw new UnsupportedOperationException(ex);
            }
        }

        assert document != null;
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
