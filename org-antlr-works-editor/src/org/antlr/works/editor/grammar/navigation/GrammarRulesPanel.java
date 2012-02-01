/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.navigation;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.netbeans.spi.navigator.NavigatorPanel.Registration;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.lookup.Lookups;

@NbBundle.Messages({
    "LBL_rules=Rules",
    "HINT_rules=Rules"
})
@Registration(mimeType = "text/x-antlr3", position = 100, displayName = "#LBL_rules")
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
    }

    @Override
    public void panelDeactivated() {
        getGrammarRulesPanelUI().showWaitNode();
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
        assert panel != null;
        if (panel == null) {
            return;
        }

        JComponent component = panel.getComponent();
        assert component == null || component instanceof GrammarRulesPanelUI;
        if (!(component instanceof GrammarRulesPanelUI)) {
            return;
        }

        result[0] = (GrammarRulesPanelUI)component;
    }
}
