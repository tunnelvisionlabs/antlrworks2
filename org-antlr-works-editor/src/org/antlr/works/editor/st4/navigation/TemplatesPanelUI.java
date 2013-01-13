/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.navigation;

import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.navigation.Filters;
import org.antlr.netbeans.editor.navigation.NavigatorPanelUI;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;

public class TemplatesPanelUI extends NavigatorPanelUI {

    private String currentRuleName;

    @SuppressWarnings("LeakingThisInConstructor")
    public TemplatesPanelUI() {
        super(new TemplateNode.TemplateNodeFactory());
        getTreeView().getToolTipManager().setEnabled(false);
    }

    @CheckForNull
    public String getCurrentRuleName() {
        return currentRuleName;
    }

    @Override
    protected Filters createFilters() {
        return new TemplatesFilters(this);
    }

    public void refresh(@NonNull Description description, @NullAllowed String currentRule) {
        this.currentRuleName = currentRule;
        super.refresh(description);
    }

}
