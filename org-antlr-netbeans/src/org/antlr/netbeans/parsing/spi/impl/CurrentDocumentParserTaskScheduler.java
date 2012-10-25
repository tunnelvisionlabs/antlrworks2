/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi.impl;

import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.openide.util.lookup.ServiceProvider;

/**
 * A task scheduler which schedules tasks when the active editor changes and
 * after the content of the active editor's document changes.
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=ParserTaskScheduler.class)
public class CurrentDocumentParserTaskScheduler extends CurrentEditorParserTaskScheduler {

    private Document currentDocument;
    private VersionedDocument versionedDocument;

    protected Document getCurrentDocument() {
        return currentDocument;
    }

    protected VersionedDocument getVersionedDocument() {
        return versionedDocument;
    }

    @Override
    protected int getParseDelayMilliseconds() {
        return 50;
    }

    @Override
    protected void setEditor(JTextComponent editor) {
        if (editor != null) {
            Document document = editor.getDocument();
            if (currentDocument == document) {
                return;
            }

            currentDocument = document;
            versionedDocument = VersionedDocumentUtilities.getVersionedDocument(document);
            schedule(createParseContext(versionedDocument, editor));
        } else {
            currentDocument = null;
            versionedDocument = null;
        }
    }

    protected ParseContext createParseContext(VersionedDocument versionedDocument, JTextComponent editor) {
        return new ParseContext(getClass(), versionedDocument, editor);
    }
}
