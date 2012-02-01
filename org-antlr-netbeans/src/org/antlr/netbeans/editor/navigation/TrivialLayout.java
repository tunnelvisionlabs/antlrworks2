/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
/*
 * TrivialLayout.java
 *
 * Created on 20. srpen 2003, 18:33
 */
package org.antlr.netbeans.editor.navigation;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

/**
 * Trivial layout manager class used by the panels for selecting look and filter.  Simply uses the preferred size of the
 * first component and fills the rest of the space with the second, to the height of the tallest.
 *
 * @author Tim Boudreau
 */
final class TrivialLayout implements LayoutManager {
    @Override
    public void addLayoutComponent(String name, Component comp) {
        //do nothing
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        //do nothing
    }

    @Override
    public void layoutContainer(Container parent) {
        if (parent instanceof CollapsiblePanel) {
            layoutCollapsiblePanel((CollapsiblePanel) parent);
        } else {
            layoutComp(parent);
        }
    }

    /**
     * Standard layout for any container
     */
    private void layoutComp(Container parent) {
        Component[] c = parent.getComponents();
        if (c.length > 0) {
            c[ 0].setBounds(0, 0, parent.getWidth(), parent.getHeight());
        }
    }

    /**
     * Layout for CollapsiblePanel, taking into account its minimumHeight
     */
    private void layoutCollapsiblePanel(CollapsiblePanel tp) {
        Component[] c = tp.getComponents();
        if (c.length > 0) {
            Dimension d2 = c[ 0].getPreferredSize();
            if (tp.isExpanded()) {
                int top = tp.getOrientation() == CollapsiblePanel.UP ? 0 : tp.getMinimumHeight();
                int height = Math.min(tp.getHeight() - tp.getMinimumHeight(), d2.height);
                c[ 0].setBounds(0, top, tp.getWidth(), height);
            } else {
                c[ 0].setBounds(0, 0, 0, 0);
            }
        }
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        Dimension result = new Dimension(20, 10);
        Component[] c = parent.getComponents();
        CollapsiblePanel tp = (CollapsiblePanel) parent;
        if (c.length > 0) {
            Dimension d2 = c[ 0].getPreferredSize();
            result.width = d2.width;
            result.height = tp.isExpanded() ? d2.height + tp.getMinimumHeight() : tp.getMinimumHeight();
        }
        return result;
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return minimumLayoutSize(parent);
    }
}
