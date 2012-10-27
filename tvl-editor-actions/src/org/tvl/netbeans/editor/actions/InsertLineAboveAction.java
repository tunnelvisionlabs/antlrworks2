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
import org.netbeans.editor.BaseAction;
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.Utilities;
import org.netbeans.modules.editor.indent.api.Indent;
import org.openide.util.NbBundle;

/**
 * Insert a blank line above the current line, and place the caret there.
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    InsertLineAboveAction.insertLineAboveAction + "=Insert Line Above"
})
@EditorActionRegistration(name = InsertLineAboveAction.insertLineAboveAction)
public class InsertLineAboveAction extends BaseAction {
    // -J-Dorg.tvl.netbeans.editor.actions.InsertLineAboveAction.level=FINE
    private static final Logger LOGGER = Logger.getLogger(InsertLineAboveAction.class.getName());

    public static final String insertLineAboveAction = "insert-line-above"; // NOI18N

    public InsertLineAboveAction() {
        super(MAGIC_POSITION_RESET | UNDO_MERGE_RESET);
    }

    @Override
    public void actionPerformed(final ActionEvent evt, final JTextComponent target) {
        // shift-enter while editing aka startNewLineAction
        if (!target.isEditable() || !target.isEnabled()) {
            target.getToolkit().beep();
            return;
        }

        final BaseDocument doc = (BaseDocument)target.getDocument();
        final Indent indenter = Indent.get(doc);
        indenter.lock();
        doc.runAtomicAsUser(new Runnable() {
            @Override
            public void run() {
                try {
                    Caret caret = target.getCaret();

                    // insert new line, caret moves to the new line
                    int bolDot = Utilities.getRowStart(target, caret.getDot());
                    doc.insertString(bolDot, "\n", null); //NOI18N

                    // reindent the new line
                    Position newDotPos = doc.createPosition(bolDot);
                    indenter.reindent(bolDot);

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
        return InsertLineAboveAction.class;
    }
}
