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
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.antlr.netbeans.editor.navigation.FiltersDescription;
import org.antlr.netbeans.editor.navigation.FiltersManager;
import org.openide.util.NbBundle;
import org.openide.util.actions.Presenter;

@NbBundle.Messages({
    "LBL_FilterSubmenu=Filters"
})
public class FilterSubmenuAction extends AbstractAction implements Presenter.Popup {
    private static final String PROP_FILTER_NAME = "nbFilterName";

    private final FiltersManager filters;

    /** Creates a new instance of FilterSubmenuAction
     * @param filters
     */
    public FilterSubmenuAction(FiltersManager filters) {
        this.filters = filters;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        Object source = ev.getSource();
        // react just on submenu items, not on submenu click itself
        if (source instanceof JCheckBoxMenuItem) {
            JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)source;
            String filterName = (String)(menuItem.getClientProperty(PROP_FILTER_NAME));
            filters.setSelected(filterName, menuItem.isSelected());
        }
    }

    @Override
    public final JMenuItem getPopupPresenter() {
        return createSubmenu();
    }
    
    private JMenuItem createSubmenu () {
        FiltersDescription filtersDesc = filters.getDescription();
        JMenuItem menu = new JMenu(Bundle.LBL_FilterSubmenu()); //NOI18N
        JMenuItem menuItem;
        String filterName;
        for (int i = 0; i < filtersDesc.getFilterCount(); i++) {
            filterName = filtersDesc.getName(i);
            menuItem = new JCheckBoxMenuItem(filtersDesc.getDisplayName(i), filters.isSelected(filterName));
            menuItem.addActionListener(this);
            menuItem.putClientProperty(PROP_FILTER_NAME, filterName);
            menu.add(menuItem);
        }

        return menu;
    }
}
