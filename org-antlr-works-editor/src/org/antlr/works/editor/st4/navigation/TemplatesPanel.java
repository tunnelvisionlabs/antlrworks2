/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.navigation;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.netbeans.spi.navigator.NavigatorPanel.Registration;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.lookup.Lookups;

@NbBundle.Messages({
    "LBL_templates=Templates",
    "HINT_templates=Templates"
})
@Registration(mimeType = "text/x-stringtemplate4", position = 100, displayName = "#LBL_templates")
public class TemplatesPanel implements NavigatorPanel {
    private static final RequestProcessor RequestProcessor = new RequestProcessor(TemplatesPanel.class.getName(), 1);

    private TemplatesPanelUI component;

    @Override
    public String getDisplayName() {
        return Bundle.LBL_templates();
    }

    @Override
    public String getDisplayHint() {
        return Bundle.HINT_templates();
    }

    @Override
    public JComponent getComponent() {
        return getTemplatesPanelUI();
    }

    @Override
    public void panelActivated(Lookup context) {
    }

    @Override
    public void panelDeactivated() {
        getTemplatesPanelUI().showWaitNode();
    }

    @Override
    public Lookup getLookup() {
        return getTemplatesPanelUI().getLookup();
    }

    private synchronized TemplatesPanelUI getTemplatesPanelUI() {
        if (this.component == null) {
            this.component = new TemplatesPanelUI();
        }

        return this.component;
    }

    private static final String PANELS_FOLDER = "/Navigator/Panels/";
    private static final String CONTENT_TYPE = StringTemplateEditorKit.TEMPLATE_MIME_TYPE;
    private static final Lookup.Template<NavigatorPanel> NAV_PANEL_TEMPLATE = new Lookup.Template<NavigatorPanel>(NavigatorPanel.class);

    public static TemplatesPanelUI findTemplatesPanelUI() {
        final TemplatesPanelUI[] result = new TemplatesPanelUI[1];
        if (SwingUtilities.isEventDispatchThread()) {
            findTemplatesPanelUI(result);
        } else {
            try {
                SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        findTemplatesPanelUI(result);
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

    private static void findTemplatesPanelUI(final TemplatesPanelUI[] result) {
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
        assert component == null || component instanceof TemplatesPanelUI;
        if (!(component instanceof TemplatesPanelUI)) {
            return;
        }

        result[0] = (TemplatesPanelUI)component;
    }
}
