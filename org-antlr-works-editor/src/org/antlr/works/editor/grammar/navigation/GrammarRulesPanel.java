/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.navigation;

import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.netbeans.spi.navigator.NavigatorPanel.Registration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

@NbBundle.Messages({
    "LBL_rules=Rules",
    "HINT_rules=Rules"
})
@Registration(mimeType = GrammarEditorKit.GRAMMAR_MIME_TYPE, position = 100, displayName = "#LBL_rules")
public class GrammarRulesPanel implements NavigatorPanel {

    private static volatile GrammarRulesPanel INSTANCE;

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
    public GrammarRulesPanelUI getComponent() {
        return getGrammarRulesPanelUI();
    }

    @Override
    public void panelActivated(Lookup context) {
        INSTANCE = this;
    }

    @Override
    public void panelDeactivated() {
        INSTANCE = null;
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

    public static GrammarRulesPanel getInstance() {
        return INSTANCE;
    }

}
