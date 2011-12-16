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
package org.antlr.netbeans.parsing.spi.impl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.netbeans.api.editor.EditorRegistry;
import org.openide.filesystems.FileObject;

/**
 * A task scheduler which schedules tasks when the active editor window changes.
 *
 * @author Sam Harwell
 */
public abstract class CurrentEditorParserTaskScheduler extends ParserTaskScheduler {

    private JTextComponent currentEditor;

    @Override
    protected void initializeImpl() {
        setEditor(EditorRegistry.focusedComponent());
        EditorRegistry.addPropertyChangeListener(new EditorRegistryListener());
    }

    protected abstract void setEditor(JTextComponent editor);

    private class EditorRegistryListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName() == null
                || evt.getPropertyName().equals(EditorRegistry.FOCUSED_DOCUMENT_PROPERTY)
                || evt.getPropertyName().equals(EditorRegistry.FOCUS_GAINED_PROPERTY)) {

                JTextComponent editor = EditorRegistry.focusedComponent();
                if (editor == currentEditor) {
                    return;
                }

                currentEditor = editor;
                if (currentEditor != null) {
                    Document document = currentEditor.getDocument();
                    VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(document);
                    FileObject fileObject = versionedDocument.getFileObject();
                    if (fileObject == null) {
                        return;
                    }
                }

                setEditor(currentEditor);
            } else if (evt.getPropertyName().equals(EditorRegistry.LAST_FOCUSED_REMOVED_PROPERTY)) {
                currentEditor = null;
                setEditor(null);
            }
        }
    }

}
