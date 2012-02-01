/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.navigation.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import org.antlr.netbeans.editor.navigation.Filters;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.actions.Presenter;

@NbBundle.Messages({
    "LBL_SortBySource=Sort by Source"
})
public class SortBySourceAction extends AbstractAction implements Presenter.Popup {
    private JRadioButtonMenuItem menuItem;
    protected Filters filters;

    public SortBySourceAction(Filters filters) {
        this.filters = filters;
        putValue(Action.NAME, Bundle.LBL_SortBySource());
        putValue(Action.SMALL_ICON, ImageUtilities.loadImageIcon("org/antlr/netbeans/editor/navigation/resources/sortPosition.png", false));
    }

    @Override
    public JMenuItem getPopupPresenter() {
        JMenuItem result = obtainMenuItem();
        updateMenuItem();
        return result;
    }

    protected final JRadioButtonMenuItem obtainMenuItem() {
        if (menuItem == null) {
            menuItem = new JRadioButtonMenuItem((String)getValue(Action.NAME));
            menuItem.setAction(this);
        }

        return menuItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        filters.setNaturalSort(true);
        updateMenuItem();
    }

    protected void updateMenuItem() {
        JRadioButtonMenuItem mi = obtainMenuItem();
        mi.setSelected(filters.isNaturalSort());
    }
}
