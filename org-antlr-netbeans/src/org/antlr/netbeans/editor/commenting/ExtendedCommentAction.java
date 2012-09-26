/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.commenting;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.DocumentSpan;
import org.antlr.netbeans.editor.SelectionHelper;
import org.netbeans.editor.ext.ExtKit;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class ExtendedCommentAction extends ExtKit.CommentAction {
    // -J-Dorg.antlr.netbeans.editor.commenting.ExtendedCommentAction.level=FINE
    private static final Logger LOGGER = Logger.getLogger(ExtendedCommentAction.class.getName());

    private final Commenter commenter;

    public ExtendedCommentAction(Commenter commenter) {
        super(null);
        Parameters.notNull("commenter", commenter);

        this.commenter = commenter;
    }

    @Override
    public void actionPerformed(ActionEvent evt, JTextComponent target) {
        try {
            List<DocumentSpan> spans = SelectionHelper.getSelection(target);
            if (spans == null) {
                return;
            }

            List<DocumentSpan> updatedSelection = commenter.commentSpans(spans);

            if (!updatedSelection.isEmpty()) {
                SelectionHelper.setSelection(target, updatedSelection);
            }
        } catch (BadLocationException ex) {
            LOGGER.log(Level.WARNING, "An exception occurred while commenting code.", ex);
        }
    }

}
