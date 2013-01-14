/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.navigation;

import org.antlr.netbeans.editor.navigation.AbstractNavigatorPanel;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.antlr.works.editor.st4.TemplateParserDataDefinitions;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.spi.navigator.NavigatorPanel.Registration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

@NbBundle.Messages({
    "LBL_templates=Templates",
    "HINT_templates=Templates"
})
@Registration(mimeType = StringTemplateEditorKit.TEMPLATE_MIME_TYPE, position = 100, displayName = "#LBL_templates")
public class TemplatesPanel extends AbstractNavigatorPanel<TemplatesPanelUI> {

    private static volatile TemplatesPanel INSTANCE;

    public TemplatesPanel() {
        super(StringTemplateEditorKit.TEMPLATE_MIME_TYPE, TemplateParserDataDefinitions.NAVIGATOR_ROOT, TemplateParserDataDefinitions.NAVIGATOR_UI_VISIBLE);
    }

    @Override
    public String getDisplayName() {
        return Bundle.LBL_templates();
    }

    @Override
    public String getDisplayHint() {
        return Bundle.HINT_templates();
    }

    @Override
    public void panelActivated(Lookup context) {
        INSTANCE = this;
        super.panelActivated(context);
    }

    @Override
    public void panelDeactivated() {
        INSTANCE = null;
        super.panelDeactivated();
        getComponent().showWaitNode();
    }

    @Override
    public Lookup getLookup() {
        return getComponent().getLookup();
    }

    @CheckForNull
    public static TemplatesPanel getInstance() {
        return INSTANCE;
    }

    @Override
    protected TemplatesPanelUI createPanelUI() {
        return new TemplatesPanelUI();
    }

}
