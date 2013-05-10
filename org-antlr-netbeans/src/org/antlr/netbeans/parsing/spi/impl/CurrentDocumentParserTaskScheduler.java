/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi.impl;

import java.lang.ref.WeakReference;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.openide.util.lookup.ServiceProvider;

/**
 * A task scheduler which schedules tasks when the active editor changes and
 * after the content of the active editor's document changes.
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=ParserTaskScheduler.class)
public class CurrentDocumentParserTaskScheduler extends CurrentEditorParserTaskScheduler {

    private WeakReference<Document> currentDocument;

    protected Document getCurrentDocument() {
        WeakReference<Document> ref = currentDocument;
        if (ref == null) {
            return null;
        }

        return ref.get();
    }

    protected VersionedDocument getVersionedDocument() {
        Document document = getCurrentDocument();
        if (document == null) {
            return null;
        }

        return VersionedDocumentUtilities.getVersionedDocument(document);
    }

    @Override
    protected int getParseDelayMilliseconds() {
        return 50;
    }

    @Override
    protected void setEditor(JTextComponent editor) {
        if (editor != null) {
            Document document = editor.getDocument();
            if (getCurrentDocument() == document) {
                return;
            }

            currentDocument = new WeakReference<>(document);

            if (!hasAssociatedDataDefinitions(getClass(), NbEditorUtilities.getMimeType(getCurrentDocument()))) {
                return;
            }

            schedule(createParseContext(getVersionedDocument(), editor));
        } else {
            currentDocument = null;
        }
    }

    protected ParseContext createParseContext(VersionedDocument versionedDocument, JTextComponent editor) {
        return new ParseContext(getClass(), versionedDocument, editor);
    }
}
