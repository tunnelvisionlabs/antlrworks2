/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package org.antlr.works.editor.grammar.navigation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import org.antlr.netbeans.editor.navigation.FiltersDescription;
import org.antlr.netbeans.editor.navigation.FiltersManager;
import org.antlr.works.editor.grammar.navigation.GrammarNode.Description;
import org.antlr.works.editor.grammar.navigation.actions.SortByNameAction;
import org.antlr.works.editor.grammar.navigation.actions.SortBySourceAction;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

public final class GrammarRuleFilters {

    private static final String SHOW_INHERITED = "show_inherited";
    private static final String SORT_ALPHA = "sort_alpha";
    private static final String SORT_POSITION = "sort_position";

    private final GrammarRulesPanelUI ui;

    private FiltersManager filtersManager;
    private boolean naturalSort;

    private JToggleButton sortByNameButton;
    private JToggleButton sortByPositionButton;

    public GrammarRuleFilters(GrammarRulesPanelUI ui) {
        this.ui = ui;
        naturalSort = NbPreferences.forModule(GrammarRuleFilters.class).getBoolean("naturalSort", false);
    }

    public FiltersManager getInstance() {
        if (filtersManager == null) {
            filtersManager = createFilters();
        }

        return filtersManager;
    }

    public JComponent getComponent() {
        FiltersManager filters = getInstance();
        return filters.getComponent(createSortButtons());
    }

    public boolean isNaturalSort() {
        return naturalSort;
    }

    public void setNaturalSort(boolean value) {
        this.naturalSort = value;
        NbPreferences.forModule(GrammarRuleFilters.class).putBoolean("naturalSort", value);
        if (sortByNameButton != null) {
            sortByNameButton.setSelected(!value);
        }

        if (sortByPositionButton != null) {
            sortByPositionButton.setSelected(value);
        }

        ui.sort();
    }

    public Collection<Description> filter(Collection<Description> original) {
        boolean inherited = filtersManager.isSelected(SHOW_INHERITED);

        ArrayList<Description> result = new ArrayList<Description>(original.size());
        for (Description description : original) {
            if (!inherited && description.isInherited()) {
                continue;
            }

            result.add(description);
        }

        Collections.sort(result, isNaturalSort() ? Description.POSITION_COMPARATOR : Description.ALPHA_COMPARATOR);
        return result;
    }
    
    private static FiltersManager createFilters() {
        FiltersDescription desc = new FiltersDescription();
        
        desc.addFilter(SHOW_INHERITED,
                NbBundle.getMessage(GrammarRuleFilters.class, "LBL_ShowInherited"),     //NOI18N
                NbBundle.getMessage(GrammarRuleFilters.class, "LBL_ShowInheritedTip"),     //NOI18N
                false, ImageUtilities.loadImageIcon("org/antlr/works/editor/grammar/navigation/resources/filterHideInherited.png", false), //NOI18N
                null
        );
        
        return FiltersDescription.createManager(desc);
    }

    private JToggleButton[] createSortButtons() {
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

        return new JToggleButton[]{sortByNameButton, sortByPositionButton};
    }
}
