/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.navigation;

import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.navigation.Filters;
import org.antlr.netbeans.editor.navigation.NavigatorPanelUI;
import org.antlr.works.editor.grammar.navigation.GrammarNode.GrammarNodeFactory;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;

public class GrammarRulesPanelUI extends NavigatorPanelUI {

    private String currentRuleName;

    public GrammarRulesPanelUI() {
        super(new GrammarNodeFactory());
        getTreeView().getToolTipManager().setEnabled(false);
    }

    @CheckForNull
    public String getCurrentRuleName() {
        return currentRuleName;
    }

    @Override
    protected Filters createFilters() {
        return new GrammarRuleFilters(this);
    }

    public void refresh(@NonNull Description description, @NullAllowed String currentRule) {
        this.currentRuleName = currentRule;
        super.refresh(description);
    }
}
