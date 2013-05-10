/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.navigation;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;

/**
 * @author Dafe Simonek
 */
public final class FiltersDescription {

    public static FiltersManager createManager (FiltersDescription descr) {
        return FiltersManager.create(descr);
    }

    /** List of <FilterItem> describing filters properties */
    private final List<FilterItem> filters;

    /** Creates a new instance of FiltersDescription */
    public FiltersDescription() {
        filters = new ArrayList<>();
    }

    public void addFilter (String name, String displayName, String tooltip,
            boolean isSelected, Icon selectedIcon, Icon unselectedIcon) {
        FilterItem newItem = new FilterItem(name, displayName, tooltip,
                isSelected, selectedIcon, unselectedIcon);
        filters.add(newItem);
    }

    public int getFilterCount () {
        return filters.size();
    }

    public String getName (int index) {
        return filters.get(index).name;
    }

    public String getDisplayName (int index) {
        return filters.get(index).displayName;
    }

    public String getTooltip (int index) {
        return filters.get(index).tooltip;
    }

    public Icon getSelectedIcon (int index) {
        return filters.get(index).selectedIcon;
    }

    public Icon getUnselectedIcon (int index) {
        return filters.get(index).unselectedIcon;
    }

    public boolean isSelected (int index) {
        return filters.get(index).isSelected;
    }

    static class FilterItem {
        String name;
        String displayName;
        String tooltip;
        Icon selectedIcon;
        Icon unselectedIcon;
        boolean isSelected;

        FilterItem (String name, String displayName, String tooltip,
                boolean isSelected, Icon selectedIcon, Icon unselectedIcon) {
            assert selectedIcon != null;

            this.name = name;
            this.displayName = displayName;
            this.tooltip = tooltip;
            this.selectedIcon = selectedIcon;
            this.unselectedIcon = unselectedIcon;
            this.isSelected = isSelected;
        }

    }

}
