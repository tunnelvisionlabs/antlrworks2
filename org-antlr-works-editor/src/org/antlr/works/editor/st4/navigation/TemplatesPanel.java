/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.navigation;

import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.netbeans.spi.navigator.NavigatorPanel.Registration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

@NbBundle.Messages({
    "LBL_templates=Templates",
    "HINT_templates=Templates"
})
@Registration(mimeType = StringTemplateEditorKit.TEMPLATE_MIME_TYPE, position = 100, displayName = "#LBL_templates")
public class TemplatesPanel implements NavigatorPanel {

    private static volatile TemplatesPanel INSTANCE;

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
    public TemplatesPanelUI getComponent() {
        return getTemplatesPanelUI();
    }

    @Override
    public void panelActivated(Lookup context) {
        INSTANCE = this;
    }

    @Override
    public void panelDeactivated() {
        INSTANCE = null;
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

    public static TemplatesPanel getInstance() {
        return INSTANCE;
    }

}
