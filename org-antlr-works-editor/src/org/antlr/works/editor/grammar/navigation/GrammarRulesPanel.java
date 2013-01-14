/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.navigation;

import org.antlr.netbeans.editor.navigation.AbstractNavigatorPanel;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.netbeans.spi.navigator.NavigatorPanel.Registration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

@NbBundle.Messages({
    "LBL_rules=Rules",
    "HINT_rules=Rules"
})
@Registration(mimeType = GrammarEditorKit.GRAMMAR_MIME_TYPE, position = 100, displayName = "#LBL_rules")
public class GrammarRulesPanel extends AbstractNavigatorPanel<GrammarRulesPanelUI> {

    private static volatile GrammarRulesPanel INSTANCE;

    public GrammarRulesPanel() {
        super(GrammarEditorKit.GRAMMAR_MIME_TYPE, GrammarParserDataDefinitions.NAVIGATOR_ROOT, GrammarParserDataDefinitions.NAVIGATOR_UI_VISIBLE);
    }

    @Override
    public String getDisplayName() {
        return Bundle.LBL_rules();
    }

    @Override
    public String getDisplayHint() {
        return Bundle.HINT_rules();
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

    public static GrammarRulesPanel getInstance() {
        return INSTANCE;
    }

    @Override
    protected GrammarRulesPanelUI createPanelUI() {
        return new GrammarRulesPanelUI();
    }

}
