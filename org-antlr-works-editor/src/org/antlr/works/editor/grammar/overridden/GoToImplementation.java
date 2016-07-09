/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.overridden;

import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.api.editor.EditorActionRegistration;
import org.netbeans.api.progress.BaseProgressUtils;
import org.netbeans.editor.BaseAction;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@EditorActionRegistration(
        name = "goto-implementation",
        mimeType = GrammarEditorKit.GRAMMAR_MIME_TYPE,
        popupText = "#CTL_GoToImplementation"
)
@NbBundle.Messages({
    "CTL_GoToImplementation=Go to Implementation",
    "goto-implementation=Go to Implementation"
})
public class GoToImplementation extends BaseAction {

    public GoToImplementation() {
        super(SAVE_POSITION);
    }

    @Override
    public void actionPerformed(ActionEvent evt, JTextComponent target) {
        goToImplementation(target);
    }

    public static void goToImplementation(final JTextComponent c) {
        final Document doc = c.getDocument();
        final int caretPos = c.getCaretPosition();
        final AtomicBoolean cancel = new AtomicBoolean();

        BaseProgressUtils.runOffEventDispatchThread(new Runnable() {
            @Override
            public void run() {
                goToImplementation(c, doc, caretPos, cancel);
            }
        }, Bundle.CTL_GoToImplementation(), cancel, false);
    }

    public static void goToImplementation(final JTextComponent c, final Document document, final int caretPosition, final AtomicBoolean cancel) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
