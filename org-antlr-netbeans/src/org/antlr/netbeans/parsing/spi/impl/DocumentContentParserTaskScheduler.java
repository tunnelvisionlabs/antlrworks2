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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.netbeans.api.editor.EditorRegistry;
import org.openide.util.lookup.ServiceProvider;

/**
 * A task scheduler which schedules tasks when the content of a document changes.
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=ParserTaskScheduler.class)
public class DocumentContentParserTaskScheduler extends ParserTaskScheduler {
    private final Map<Document, Set<JTextComponent>> documents = new HashMap<Document, Set<JTextComponent>>();
    private final EditorRegistryListener editorRegistryListener = new EditorRegistryListener();
    private final DocumentListener documentListener = new DocumentListenerImpl();

    @Override
    protected void initializeImpl() {
        EditorRegistry.addPropertyChangeListener(editorRegistryListener);
    }

    private class EditorRegistryListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName() == null
                || evt.getPropertyName().equals(EditorRegistry.FOCUSED_DOCUMENT_PROPERTY)
                || evt.getPropertyName().equals(EditorRegistry.FOCUS_GAINED_PROPERTY)) {

                JTextComponent editor = EditorRegistry.focusedComponent();
                if (editor == null) {
                    return;
                }

                Document document = editor.getDocument();

                Set<JTextComponent> components;
                synchronized (documents) {
                    components = documents.get(document);
                    if (components == null) {
                        components = new HashSet<JTextComponent>();
                        documents.put(document, components);
                        document.addDocumentListener(documentListener);
                        schedule(VersionedDocumentUtilities.getVersionedDocument(document));
                    }
                }

                components.add(editor);
            } else if (evt.getPropertyName().equals(EditorRegistry.COMPONENT_REMOVED_PROPERTY)) {
                JTextComponent editor = (JTextComponent)evt.getOldValue();
                if (editor == null) {
                    return;
                }

                Document document = editor.getDocument();
                synchronized (documents) {
                    Set<JTextComponent> components = documents.get(document);
                    if (components != null && components.remove(editor)) {
                        if (components.isEmpty()) {
                            document.removeDocumentListener(documentListener);
                            documents.remove(document);
                        }
                    }
                }
            }

        }
    }

    private class DocumentListenerImpl implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            schedule(VersionedDocumentUtilities.getVersionedDocument(e.getDocument()));
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            schedule(VersionedDocumentUtilities.getVersionedDocument(e.getDocument()));
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            schedule(VersionedDocumentUtilities.getVersionedDocument(e.getDocument()));
        }

    }

}
