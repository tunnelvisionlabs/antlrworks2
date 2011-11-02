/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text.impl;

import java.lang.ref.WeakReference;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import org.antlr.netbeans.editor.text.TextBuffer;
import org.antlr.netbeans.editor.text.TextSnapshot;
import org.netbeans.editor.BaseDocument;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.openide.util.Exceptions;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public class NbTextBuffer implements TextBuffer {

    private static final WeakReference<NbTextVersion> NullVersion = new WeakReference<NbTextVersion>(null);

    private final BaseDocument document;

    private NbNormalizedTextChangeCollection pendingChanges = new NbNormalizedTextChangeCollection();
    private WeakReference<NbTextVersion> latestVersion = NullVersion;
    private int latestVersionNumber = 0;

    public NbTextBuffer(BaseDocument document) {
        Parameters.notNull("document", document);

        this.document = document;
        this.document.addDocumentListener(new Listener());
    }

    @Override
    public BaseDocument getDocument() {
        return document;
    }

    @Override
    public TextSnapshot getCurrentSnapshot() {
        return applyChanges().getSnapshot();
    }
    
    private NbTextVersion applyChanges() {
        document.readLock();
        try {
            NbTextVersion version = latestVersion.get();
            if (pendingChanges.isEmpty() && version != null) {
                return version;
            }

            if (version == null) {
                try {
                    version = new NbTextVersion(this, latestVersionNumber + 1, new LineTextCache(document.getText(0, document.getLength())));
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                    throw new IllegalStateException("Shouldn't be reachable.", ex);
                }
            } else if (true) {
                version = version.translate(pendingChanges);
            }

            latestVersion = new WeakReference<NbTextVersion>(version);
            latestVersionNumber = version.getVersionNumber();
            pendingChanges = new NbNormalizedTextChangeCollection();
            return version;
        } finally {
            document.readUnlock();
        }
    }

    private void addPendingChange(NbTextChange change) {
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
            NbTextChange textChange = new NbTextChange(offset, "", offset, text);
            addPendingChange(textChange);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (latestVersion.get() == null) {
                return;
            }

            int offset = e.getOffset();
            String text = DocumentUtilities.getModificationText(e);
            NbTextChange textChange = new NbTextChange(offset, text, offset, "");
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
