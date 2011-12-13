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

package org.antlr.works.editor.grammar.navigation;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.lookup.Lookups;

@NbBundle.Messages({
    "LBL_rules=Rules",
    "HINT_rules=Rules"
})
public class GrammarRulesPanel implements NavigatorPanel {
    private static final RequestProcessor RequestProcessor = new RequestProcessor(GrammarRulesPanel.class.getName(), 1);

    private GrammarRulesPanelUI component;

    @Override
    public String getDisplayName() {
        return Bundle.LBL_rules();
    }

    @Override
    public String getDisplayHint() {
        return Bundle.HINT_rules();
    }

    @Override
    public JComponent getComponent() {
        return getGrammarRulesPanelUI();
    }

    @Override
    public void panelActivated(Lookup context) {
        getGrammarRulesPanelUI().showWaitNode();
        RequestProcessor.post(new Runnable() {
            @Override
            public void run() {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    @Override
    public void panelDeactivated() {
        getGrammarRulesPanelUI().showWaitNode();
        RequestProcessor.post(new Runnable() {
            @Override
            public void run() {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    @Override
    public Lookup getLookup() {
        return getGrammarRulesPanelUI().getLookup();
    }

    private synchronized GrammarRulesPanelUI getGrammarRulesPanelUI() {
        if (this.component == null) {
            this.component = new GrammarRulesPanelUI();
        }

        return this.component;
    }

    private static final String PANELS_FOLDER = "/Navigator/Panels/";
    private static final String CONTENT_TYPE = GrammarEditorKit.GRAMMAR_MIME_TYPE;
    private static final Lookup.Template<NavigatorPanel> NAV_PANEL_TEMPLATE = new Lookup.Template<NavigatorPanel>(NavigatorPanel.class);

    public static GrammarRulesPanelUI findGrammarRulesPanelUI() {
        final GrammarRulesPanelUI[] result = new GrammarRulesPanelUI[1];
        if (SwingUtilities.isEventDispatchThread()) {
            findGrammarRulesPanelUI(result);
        } else {
            try {
                SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        findGrammarRulesPanelUI(result);
                    }
                });
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
                return null;
            } catch (InvocationTargetException ex) {
                Exceptions.printStackTrace(ex);
                return null;
            }
        }

        return result[0];
    }

    private static void findGrammarRulesPanelUI(final GrammarRulesPanelUI[] result) {
        result[0] = null;

        String path = PANELS_FOLDER + CONTENT_TYPE;
        Lookup.Result<NavigatorPanel> lookupResult = Lookups.forPath(path).lookup(NAV_PANEL_TEMPLATE);
        Collection<? extends NavigatorPanel> panels = lookupResult.allInstances();
        assert panels.size() <= 1;
        if (panels.isEmpty()) {
            return;
        }

        NavigatorPanel panel = panels.iterator().next();
        assert panel instanceof GrammarRulesPanel;
        if (!(panel instanceof GrammarRulesPanel)) {
            return;
        }

        GrammarRulesPanel grammarRulesPanel = (GrammarRulesPanel)panel;
        JComponent component = grammarRulesPanel.getComponent();
        assert component == null || component instanceof GrammarRulesPanelUI;
        if (!(component instanceof GrammarRulesPanelUI)) {
            return;
        }

        result[0] = (GrammarRulesPanelUI)component;
    }
}
