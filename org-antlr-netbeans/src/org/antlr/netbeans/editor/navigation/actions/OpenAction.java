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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.navigation.Description;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.cookies.EditorCookie;
import org.openide.cookies.LineCookie;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.text.Line;
import org.openide.text.Line.ShowOpenType;
import org.openide.text.Line.ShowVisibilityType;
import org.openide.text.NbDocument;
import org.openide.util.NbBundle;
import org.openide.util.Parameters;
import org.openide.util.UserQuestionException;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "LBL_Goto=Go to Source",
    "TXT_Question=Question"
})
public final class OpenAction extends AbstractAction {
    // -J-Dorg.antlr.netbeans.editor.navigation.actions.OpenAction.level=FINE
    private static final Logger LOGGER = Logger.getLogger(OpenAction.class.getName());

    private final Description description;

    private final FileObject fileObject;
    private final int offset;

    public OpenAction(@NonNull Description description) {
        Parameters.notNull("description", description);
        if (description.getFileObject() == null) {
            throw new IllegalArgumentException("Description does not provide a file.");
        }

        this.description = description;
        this.fileObject = null;
        this.offset = 0;
        putValue(Action.NAME, Bundle.LBL_Goto());
    }

    public OpenAction(@NonNull FileObject fileObject, int offset) {
        Parameters.notNull("fileObject", fileObject);

        this.description = null;
        this.fileObject = fileObject;
        this.offset = offset;
        putValue(Action.NAME, Bundle.LBL_Goto());
    }

    public FileObject getFileObject() {
        return description != null ? description.getFileObject() : fileObject;
    }

    public int getOffset() {
        return description != null ? description.getOffset() : offset;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DataObject od = DataObject.find(getFileObject());
            EditorCookie ec = od.getLookup().lookup(EditorCookie.class);
            LineCookie lc = od.getLookup().lookup(LineCookie.class);

            if (ec != null && lc != null && getOffset() != -1) {
                StyledDocument doc;
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
                    int line = NbDocument.findLineNumber(doc, getOffset());
                    int lineOffset = NbDocument.findLineOffset(doc, line);
                    int column = getOffset() - lineOffset;

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
            LOGGER.log(Level.WARNING, "An exception occurred while opening a file.", ex);
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
