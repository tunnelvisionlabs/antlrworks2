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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import org.antlr.netbeans.editor.navigation.actions.SortByNameAction;
import org.antlr.netbeans.editor.navigation.actions.SortBySourceAction;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "LBL_ShowInherited=Show Inherited",
    "LBL_ShowInheritedTip=Show Inherited"
})
public abstract class Filters {

    protected static final String SHOW_INHERITED = "show_inherited";
    protected static final String SORT_ALPHA = "sort_alpha";
    protected static final String SORT_POSITION = "sort_position";

    private final NavigatorPanelUI ui;

    private FiltersManager filtersManager;
    private boolean naturalSort;

    private JToggleButton sortByNameButton;
    private JToggleButton sortByPositionButton;

    public Filters(NavigatorPanelUI ui) {
        this.ui = ui;
        naturalSort = NbPreferences.forModule(getClass()).getBoolean("naturalSort", false);
    }

    public boolean isNaturalSort() {
        return naturalSort;
    }

    public void setNaturalSort(boolean value) {
        this.naturalSort = value;
        NbPreferences.forModule(getClass()).putBoolean("naturalSort", value);
        if (sortByNameButton != null) {
            sortByNameButton.setSelected(!value);
        }

        if (sortByPositionButton != null) {
            sortByPositionButton.setSelected(value);
        }

        ui.sort();
    }

    public final FiltersManager getInstance() {
        if (filtersManager == null) {
            filtersManager = createFilters();
        }

        return filtersManager;
    }

    public JComponent getComponent() {
        FiltersManager filters = getInstance();
        return filters.getComponent(createSortButtons());
    }

    public Collection<Description> filter(Collection<Description> original) {
        ArrayList<Description> result = new ArrayList<>(original.size());
        for (Description description : original) {
            if (!filterImpl(description)) {
                continue;
            }

            result.add(description);
        }

        sortImpl(result);
        return result;
    }
    
    protected boolean supportsInheritedMembers() {
        return true;
    }

    protected boolean filterImpl(Description description) {
        boolean inherited = filtersManager.isSelected(SHOW_INHERITED);
        return inherited || !description.isInherited();
    }

    protected void sortImpl(List<Description> descriptions) {
        Collections.sort(descriptions, isNaturalSort() ? Description.POSITION_COMPARATOR : Description.ALPHA_COMPARATOR);
    }

    protected FiltersManager createFilters() {
        FiltersDescription desc = new FiltersDescription();
        describeFilters(desc);
        return FiltersDescription.createManager(desc);
    }

    protected void describeFilters(FiltersDescription desc) {
        if (supportsInheritedMembers()) {
            desc.addFilter(SHOW_INHERITED,
                    Bundle.LBL_ShowInherited(),
                    Bundle.LBL_ShowInheritedTip(),
                    false, ImageUtilities.loadImageIcon("org/antlr/netbeans/editor/navigation/resources/filterHideInherited.png", false), //NOI18N
                    null
                );
        }
    }

    protected JToggleButton[] createSortButtons() {
        if (sortByNameButton == null) {
            sortByNameButton = new JToggleButton(new SortByNameAction(this));
            sortByNameButton.setToolTipText(sortByNameButton.getText());
            sortByNameButton.setText(null);
            sortByNameButton.setSelected(!naturalSort);
            sortByNameButton.setFocusable(false);
        }

        if (sortByPositionButton == null) {
            sortByPositionButton = new JToggleButton(new SortBySourceAction(this));
            sortByPositionButton.setToolTipText(sortByPositionButton.getText());
            sortByPositionButton.setText(null);
            sortByPositionButton.setSelected(naturalSort);
            sortByPositionButton.setFocusable(false);
        }

        return new JToggleButton[] { sortByNameButton, sortByPositionButton };
    }

}
