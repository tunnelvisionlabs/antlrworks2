/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package org.antlr.works.editor.grammar.navigation.actions;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import javax.swing.text.StyledDocument;
import org.antlr.works.editor.grammar.navigation.GrammarNode.Description;
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
 * @author sam
 */
public final class OpenAction extends AbstractAction {

    private final Description description;

    public OpenAction(Description description) {
        Parameters.notNull("description", description);

        this.description = description;
        putValue(Action.NAME, NbBundle.getMessage(OpenAction.class, "LBL_Goto"));
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
                            NbBundle.getMessage(OpenAction.class, "TXT_Question"),
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
