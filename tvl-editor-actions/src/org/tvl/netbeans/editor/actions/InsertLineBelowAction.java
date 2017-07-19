/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.netbeans.editor.actions;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;
import org.netbeans.api.editor.EditorActionRegistration;
import org.netbeans.api.editor.document.LineDocument;
import org.netbeans.api.editor.document.LineDocumentUtils;
import org.netbeans.editor.BaseAction;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.editor.indent.api.Indent;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    InsertLineBelowAction.insertLineBelowAction + "=Insert Line Below"
})
@EditorActionRegistration(name = InsertLineBelowAction.insertLineBelowAction)
public class InsertLineBelowAction extends BaseAction {
    // -J-Dorg.tvl.netbeans.editor.actions.InsertLineBelowAction.level=FINE
    private static final Logger LOGGER = Logger.getLogger(InsertLineBelowAction.class.getName());

    /** Insert a blank line below the current line, and place the caret there. */
    public static final String insertLineBelowAction = "insert-line-below"; // NOI18N

    public InsertLineBelowAction() {
        super(MAGIC_POSITION_RESET | UNDO_MERGE_RESET);
    }

    @Override
    public void actionPerformed(final ActionEvent evt, final JTextComponent target) {
        // shift-enter while editing aka startNewLineAction
        if (!target.isEditable() || !target.isEnabled()) {
            target.getToolkit().beep();
            return;
        }

        final BaseDocument doc = (BaseDocument) target.getDocument();
        final Indent indenter = Indent.get(doc);
        indenter.lock();
        doc.runAtomicAsUser(new Runnable() {
            @Override
            public void run() {
                try {
                    Caret caret = target.getCaret();

                    // insert new line, caret moves to the new line
                    int eolDot = LineDocumentUtils.getLineEnd(LineDocumentUtils.asRequired(doc, LineDocument.class), caret.getDot());
                    doc.insertString(eolDot, "\n", null); //NOI18N

                    // reindent the new line
                    Position newDotPos = doc.createPosition(eolDot + 1);
                    indenter.reindent(eolDot + 1);

                    caret.setDot(newDotPos.getOffset());
                } catch (BadLocationException ex) {
                    LOGGER.log(Level.WARNING, "An exception occurred while executing the action.", ex);
                } finally {
                    indenter.unlock();
                }
            }
        });
    }

    @Override
    protected Class<?> getShortDescriptionBundleClass() {
        return InsertLineBelowAction.class;
    }

}
