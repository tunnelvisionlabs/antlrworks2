/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi.impl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.ref.WeakReference;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.netbeans.api.editor.EditorRegistry;
import org.openide.filesystems.FileObject;

/**
 * A task scheduler which schedules tasks when the active editor window changes.
 *
 * @author Sam Harwell
 */
public abstract class CurrentEditorParserTaskScheduler extends ParserTaskScheduler {

    private WeakReference<JTextComponent> currentEditor;

    @Override
    protected void initializeImpl() {
        setEditor(EditorRegistry.focusedComponent());
        EditorRegistry.addPropertyChangeListener(new EditorRegistryListener());
    }

    protected JTextComponent getCurrentEditor() {
        WeakReference<JTextComponent> ref = currentEditor;
        if (ref == null) {
            return null;
        }

        return ref.get();
    }

    protected abstract void setEditor(JTextComponent editor);

    private class EditorRegistryListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName() == null
                || evt.getPropertyName().equals(EditorRegistry.FOCUSED_DOCUMENT_PROPERTY)
                || evt.getPropertyName().equals(EditorRegistry.FOCUS_GAINED_PROPERTY)) {

                JTextComponent editor = EditorRegistry.focusedComponent();
                if (editor == getCurrentEditor()) {
                    return;
                }

                currentEditor = new WeakReference<>(editor);
                try {
                    if (editor != null) {
                        Document document = editor.getDocument();
                        VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(document);
                            FileObject fileObject = versionedDocument.getFileObject();
                            if (fileObject == null) {
                                return;
                            }
                    }

                    setEditor(editor);
                } catch (UnsupportedOperationException ex) {
                }
            } else if (evt.getPropertyName().equals(EditorRegistry.LAST_FOCUSED_REMOVED_PROPERTY)) {
                currentEditor = null;
                setEditor(null);
            }
        }
    }

}
