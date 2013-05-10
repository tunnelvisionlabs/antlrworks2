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
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.netbeans.api.editor.EditorRegistry;
import org.openide.util.WeakSet;
import org.openide.util.lookup.ServiceProvider;

/**
 * A task scheduler which schedules tasks when the content of a document changes.
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=ParserTaskScheduler.class)
public class DocumentContentParserTaskScheduler extends ParserTaskScheduler {
    private final Map<Document, Set<JTextComponent>> documents = new WeakHashMap<>();
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
                        components = new WeakSet<>();
                        documents.put(document, components);
                        document.addDocumentListener(documentListener);
                        VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(document);
                        ParseContext context = new ParseContext(DocumentContentParserTaskScheduler.this.getClass(), versionedDocument);
                        schedule(context);
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
            VersionedDocument document = VersionedDocumentUtilities.getVersionedDocument(e.getDocument());
            ParseContext context = new ParseContext(DocumentContentParserTaskScheduler.this.getClass(), document);
            schedule(context);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            VersionedDocument document = VersionedDocumentUtilities.getVersionedDocument(e.getDocument());
            ParseContext context = new ParseContext(DocumentContentParserTaskScheduler.this.getClass(), document);
            schedule(context);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            VersionedDocument document = VersionedDocumentUtilities.getVersionedDocument(e.getDocument());
            ParseContext context = new ParseContext(DocumentContentParserTaskScheduler.this.getClass(), document);
            schedule(context);
        }

    }

}
