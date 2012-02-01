/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.navigation.actions;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.navigation.Description;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.cookies.EditorCookie;
import org.openide.cookies.LineCookie;
import org.openide.cookies.OpenCookie;
import org.openide.loaders.DataObject;
import org.openide.text.Line;
import org.openide.text.Line.ShowOpenType;
import org.openide.text.Line.ShowVisibilityType;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.Parameters;
import org.openide.util.UserQuestionException;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "LBL_Goto=Go to source",
    "TXT_Question=Question"
})
public final class OpenAction extends AbstractAction {

    private final Description description;

    public OpenAction(Description description) {
        Parameters.notNull("description", description);

        this.description = description;
        putValue(Action.NAME, Bundle.LBL_Goto());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DataObject od = DataObject.find(description.getFileObject());
            EditorCookie ec = od.getLookup().lookup(EditorCookie.class);
            LineCookie lc = od.getLookup().lookup(LineCookie.class);

            if (ec != null && lc != null && description.getOffset() != -1) {
                StyledDocument doc = null;
                try {
                    doc = ec.openDocument();
                } catch (UserQuestionException uqe) {
                    final Object value = DialogDisplayer.getDefault().notify(
                            new NotifyDescriptor.Confirmation(uqe.getLocalizedMessage(),
                            Bundle.TXT_Question(),
                            NotifyDescriptor.YES_NO_OPTION));
                    if (value != NotifyDescriptor.YES_OPTION) {
                        return;
                    }

                    uqe.confirmed();
                    doc = ec.openDocument();
                }
                if (doc != null) {
                    int line = NbDocument.findLineNumber(doc, description.getOffset());
                    int lineOffset = NbDocument.findLineOffset(doc, line);
                    int column = description.getOffset() - lineOffset;

                    if (line != -1) {
                        Line l = lc.getLineSet().getCurrent(line);

                        if (l != null) {
                            doShow( l, column);
                            return;
                        }
                    }
                }
            }

            OpenCookie oc = od.getLookup().lookup(OpenCookie.class);

            if (oc != null) {
                doOpen(oc);
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private void doShow(final Line line, final int column) {
        if (SwingUtilities.isEventDispatchThread()) {
            line.show(ShowOpenType.OPEN, ShowVisibilityType.FOCUS, column);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    line.show(ShowOpenType.OPEN, ShowVisibilityType.FOCUS, column);
                }
            });
        }
    }

    private void doOpen(final OpenCookie oc) {
        if (SwingUtilities.isEventDispatchThread()) {
            oc.open();
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    oc.open();
                }
            });
        }
    }

}
