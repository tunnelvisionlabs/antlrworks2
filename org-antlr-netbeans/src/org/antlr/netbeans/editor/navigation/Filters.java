/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
 * @author sam
 */
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
        ArrayList<Description> result = new ArrayList<Description>(original.size());
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
                    NbBundle.getMessage(Filters.class, "LBL_ShowInherited"),     //NOI18N
                    NbBundle.getMessage(Filters.class, "LBL_ShowInheritedTip"),     //NOI18N
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
