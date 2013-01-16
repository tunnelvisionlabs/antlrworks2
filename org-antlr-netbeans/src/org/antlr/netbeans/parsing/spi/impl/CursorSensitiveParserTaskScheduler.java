/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi.impl;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.openide.util.lookup.ServiceProvider;

/**
 * A task scheduler which schedules tasks when the active editor window changes,
 * the content of the active document changes, and/or the caret position changes
 * within the active document.
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=ParserTaskScheduler.class)
public class CursorSensitiveParserTaskScheduler extends CurrentDocumentParserTaskScheduler {

    private final CaretListener caretListener = new CaretListenerImpl();

    @Override
    protected void setEditor(JTextComponent editor) {
        if (getCurrentEditor() != null) {
            getCurrentEditor().removeCaretListener(caretListener);
        }

        super.setEditor(editor);

        if (getCurrentEditor() != null) {
            getCurrentEditor().addCaretListener(caretListener);
        }
    }

    @Override
    protected ParseContext createParseContext(VersionedDocument versionedDocument, JTextComponent editor) {
        Caret caret = editor.getCaret();
        int offset = caret.getDot();

        SnapshotPosition position = new SnapshotPosition(versionedDocument.getCurrentSnapshot(), offset);
        return new ParseContext(getClass(), position, editor);
    }

    private class CaretListenerImpl implements CaretListener {

        @Override
        public void caretUpdate(CaretEvent e) {
            if (!hasAssociatedDataDefinitions(CursorSensitiveParserTaskScheduler.this.getClass(), NbEditorUtilities.getMimeType(getCurrentDocument()))) {
                return;
            }

            VersionedDocument document = getVersionedDocument();
            if (document == null) {
                return;
            }

            DocumentSnapshot snapshot = document.getCurrentSnapshot();
            int dotOffset = e.getDot();
            if (dotOffset < 0 || dotOffset > snapshot.length()) {
                return;
            }

            SnapshotPosition position = new SnapshotPosition(snapshot, e.getDot());
            ParseContext context = new ParseContext(CursorSensitiveParserTaskScheduler.this.getClass(), position, getCurrentEditor());
            schedule(context);
        }
    }

}
