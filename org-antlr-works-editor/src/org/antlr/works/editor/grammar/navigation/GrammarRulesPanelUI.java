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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.TreePath;
import org.antlr.netbeans.editor.navigation.CollapsiblePanel;
import org.antlr.netbeans.editor.navigation.FiltersManager;
import org.antlr.netbeans.editor.navigation.ToolTipManagerEx;
import org.antlr.works.editor.grammar.navigation.GrammarNode.Description;
import org.antlr.works.editor.grammar.navigation.actions.FilterSubmenuAction;
import org.antlr.works.editor.grammar.navigation.actions.SortByNameAction;
import org.antlr.works.editor.grammar.navigation.actions.SortBySourceAction;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.BeanTreeView;
import org.openide.filesystems.FileObject;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;
import org.openide.util.RequestProcessor;
import org.openide.util.Utilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

public class GrammarRulesPanelUI extends javax.swing.JPanel implements ExplorerManager.Provider, FiltersManager.FilterChangeListener, PropertyChangeListener {
    private static final Rectangle ZERO = new Rectangle(0, 0, 1, 1);
    private static final Logger PERF_LOG = Logger.getLogger(GrammarRulesPanelUI.class.getName() + ".perf");
    private static final RequestProcessor RP = new RequestProcessor(GrammarRulesPanelUI.class.getName(), 1);

    private final ExplorerManager explorerManager = new ExplorerManager();
    private final MyBeanTreeView rulesTreeView;
    private final CollapsiblePanel filtersPanel;
    private final InstanceContent selectedNodes = new InstanceContent();
    private final Lookup lookup = new AbstractLookup(selectedNodes);
    private final GrammarRuleFilters filters;

    private Action[] actions;

    private long lastShowWaitNodeTime = -1;

    @SuppressWarnings("LeakingThisInConstructor")
    public GrammarRulesPanelUI() {
        initComponents();
        explorerManager.addPropertyChangeListener(this);

        // tree view of rules
        rulesTreeView = new MyBeanTreeView();
        this.add(rulesTreeView, BorderLayout.CENTER);

        // filters
        filtersPanel = new CollapsiblePanel();
        filtersPanel.setOrientation(CollapsiblePanel.DOWN);
        // tooltip
        KeyStroke toggleKey = KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        String keyText = Utilities.keyToString(toggleKey);
        filtersPanel.setToolTipText(NbBundle.getMessage(GrammarRulesPanelUI.class, "TIP_CollapsiblePanel", keyText));

        filters = new GrammarRuleFilters(this);
        filters.getInstance().hookChangeListener(this);
        JComponent buttons = filters.getComponent();
        buttons.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));
        filtersPanel.add(buttons);
        if ("Aqua".equals(UIManager.getLookAndFeel().getID())) {
            filtersPanel.setBackground(UIManager.getColor("NbExplorerView.background"));
        }

        actions = new Action[]
            {
                new SortByNameAction(filters),
                new SortBySourceAction(filters),
                null,
                new FilterSubmenuAction(filters.getInstance()),
            };

        this.add(filtersPanel, BorderLayout.SOUTH);

        explorerManager.setRootContext(GrammarNode.getWaitNode());

        boolean expanded = NbPreferences.forModule(GrammarRulesPanelUI.class).getBoolean("filtersCollapsiblePanel.expanded", true);
        filtersPanel.setExpanded(expanded);
        filtersPanel.addPropertyChangeListener(this);
    }

    @Override
    public boolean requestFocusInWindow() {
        boolean result = super.requestFocusInWindow();
        rulesTreeView.requestFocusInWindow();
        return result;
    }

    @Override
    public void requestFocus() {
        super.requestFocus();
        rulesTreeView.requestFocus();
    }

    public Lookup getLookup() {
        return this.lookup;
    }
//
//    public RuleScanningTask getTask() {
//        return new RuleScanningTask(this);
//    }

    public void showWaitNode() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                rulesTreeView.setRootVisible(true);
                explorerManager.setRootContext(GrammarNode.getWaitNode());
                lastShowWaitNodeTime = System.currentTimeMillis();
            }
        });
    }

    public void sort() {
        GrammarNode node = getRootNode();
        if (node != null) {
            node.refreshRecursively();
        }
    }

    public GrammarRuleFilters getFilters() {
        return filters;
    }

    public void expandNode(Node node) {
        rulesTreeView.expandNode(node);
    }

    public Action[] getActions() {
        return actions;
    }

    public FileObject getFileObject() {
        GrammarNode node = getRootNode();
        if (node != null) {
            return node.getDescription().fileObject;
        } else {
            return null;
        }
    }

    @Override
    public void filterStateChanged(ChangeEvent e) {
        GrammarNode root = getRootNode();

        if ( root != null ) {
            root.refreshRecursively();
        }
    }

    boolean getScrollOnExpand() {
        return rulesTreeView == null ? true : rulesTreeView.getScrollOnExpand();
    }

    void setScrollOnExpand( boolean scroll ) {
        if( null != rulesTreeView )
            rulesTreeView.setScrollOnExpand( scroll );
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private GrammarNode getRootNode() {
        Node node = explorerManager.getRootContext();
        if (node instanceof GrammarNode) {
            return (GrammarNode)node;
        } else {
            return null;
        }
    }
    
    @Override
    public ExplorerManager getExplorerManager() {
        return explorerManager;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (ExplorerManager.PROP_SELECTED_NODES.equals(event.getPropertyName())) {
            for (Node node : (Node[])event.getOldValue()) {
                selectedNodes.remove(node);
            }
            for (Node node : (Node[])event.getNewValue()) {
                selectedNodes.add(node);
            }
        } else if (CollapsiblePanel.EXPANDED_PROPERTY.equals(event.getPropertyName())) {
            NbPreferences.forModule(GrammarRulesPanelUI.class).putBoolean("filtersCollapsiblePanel.expanded", filtersPanel.isExpanded());
        }
    }

    public void refresh(final Description description) {
        final GrammarNode rootNode = getRootNode();
        if (rootNode != null && rootNode.getDescription().fileObject.equals(description.fileObject)) {
            RP.post(new Runnable() {
                    @Override
                    public void run() {
                        rootNode.updateRecursively(description);
                    }
                });
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        rulesTreeView.setRootVisible(false);
                        explorerManager.setRootContext(new GrammarNode(description));
                        boolean scrollOnExpand = getScrollOnExpand();
                        rulesTreeView.setAutoWaitCursor(false);
                        rulesTreeView.expandAll();
                        rulesTreeView.setAutoWaitCursor(true);
                        setScrollOnExpand(scrollOnExpand);

                        if (PERF_LOG.isLoggable(Level.FINE)) {
                            long tm2 = System.currentTimeMillis();
                            long tm1 = lastShowWaitNodeTime;
                            if (tm1 != -1) {
                                lastShowWaitNodeTime = -1;
                                PERF_LOG.log(Level.FINE,
                                             String.format("GrammarRulesPanelUI refresh took: %d ms", (tm2 - tm1)),
                                             new Object[] { description.getFileObject().getName(), (tm2 - tm1) });
                            }
                        }
                    }
                });
        }
    }

    private class MyBeanTreeView extends BeanTreeView implements ToolTipManagerEx.ToolTipProvider {

        private final ToolTipManagerEx toolTipManager;

        public MyBeanTreeView() {
            toolTipManager = new ToolTipManagerEx( this );
            setUseSubstringInQuickSearch(true);
        }

        public boolean getScrollOnExpand() {
            return tree.getScrollsOnExpand();
}

        public void setScrollOnExpand( boolean scroll ) {
            this.tree.setScrollsOnExpand( scroll );
        }

        @Override
        public JComponent getComponent() {
            return tree;
        }

        @Override
        public String getToolTipText(Point loc) {
            return null;
            //ElementJavadoc doc = getDocumentation(loc);
            //return null == doc ? null : doc.getText();
        }

        /*private ElementJavadoc getDocumentation( Point loc ) {
            TreePath path = tree.getPathForLocation( loc.x, loc.y );
            if( null == path )
                return null;
            Node node = Visualizer.findNode( path.getLastPathComponent() );
            if( node instanceof ElementNode ) {
                return getJavaDocFor( (ElementNode)node );
            }
            return null;
        }*/

        @Override
        public Rectangle getToolTipSourceBounds(Point loc) {
            GrammarNode root = getRootNode();
            if ( root == null ) {
                return null;
            }
            TreePath path = tree.getPathForLocation( loc.x, loc.y );
            return null == path ? null : tree.getPathBounds( path );
        }

        @Override
        public Point getToolTipLocation( Point mouseLocation, Dimension tipSize ) {
            Point screenLocation = getLocationOnScreen();
            Rectangle sBounds = getGraphicsConfiguration().getBounds();
            Dimension compSize = getSize();
            Point res = new Point();
            Rectangle tooltipSrcRect = getToolTipSourceBounds( mouseLocation );
            //May be null, prevent the NPE, nothing will be shown anyway.
            if (tooltipSrcRect == null) {
                tooltipSrcRect = new Rectangle();
            }

            Point viewPosition = getViewport().getViewPosition();
            screenLocation.x -= viewPosition.x;
            screenLocation.y -= viewPosition.y;

            //first try bottom right
            res.x = screenLocation.x + compSize.width;
            res.y = screenLocation.y + tooltipSrcRect.y+tooltipSrcRect.height;

            if( res.x + tipSize.width <= sBounds.x+sBounds.width
                    && res.y + tipSize.height <= sBounds.y+sBounds.height ) {
                return res;
            }

            //upper right
            res.x = screenLocation.x + compSize.width;
            res.y = screenLocation.y + tooltipSrcRect.y - tipSize.height;

            if( res.x + tipSize.width <= sBounds.x+sBounds.width
                    && res.y >= sBounds.y ) {
                return res;
            }

            //lower left
            res.x = screenLocation.x - tipSize.width;
            res.y = screenLocation.y + tooltipSrcRect.y;

            if( res.x >= sBounds.x
                    && res.y + tipSize.height <= sBounds.y+sBounds.height ) {
                return res;
            }

            //upper left
            res.x = screenLocation.x - tipSize.width;
            res.y = screenLocation.y + tooltipSrcRect.y + tooltipSrcRect.height - tipSize.height;

            if( res.x >= sBounds.x && res.y >= sBounds.y ) {
                return res;
            }

            //give up (who's got such a small display anyway?)
            res.x = screenLocation.x + tooltipSrcRect.x;
            if( sBounds.y + sBounds.height - (screenLocation.y + tooltipSrcRect.y + tooltipSrcRect.height)
                > screenLocation.y + tooltipSrcRect.y - sBounds.y ) {
                res.y = screenLocation.y + tooltipSrcRect.y + tooltipSrcRect.height;
            } else {
                res.y = screenLocation.y + tooltipSrcRect.y - tipSize.height;
            }

            return res;
        }

        @Override
        public void invokeUserAction(final MouseEvent me) {
            SwingUtilities.invokeLater( new Runnable() {
                @Override
                public void run() {
                    if( null != me ) {
                        /*ElementJavadoc doc = getDocumentation( me.getPoint() );
                        JavadocTopComponent tc = JavadocTopComponent.findInstance();
                        if( null != tc ) {
                            tc.open();
                            tc.setJavadoc( doc );
                            tc.requestActive();
                        }*/
                    }
                }
            });
        }

        //#123940 start
        private boolean inHierarchy;
        private boolean doExpandAll;

        @Override
        public void addNotify() {
            super.addNotify();

            inHierarchy = true;

            if (doExpandAll) {
                super.expandAll();
                doExpandAll = false;
            }
        }

        @Override
        public void removeNotify() {
            super.removeNotify();
            inHierarchy = false;
            this.toolTipManager.hideTipWindow();
        }

        @Override
        public void expandAll() {
            super.expandAll();

            if (!inHierarchy) {
                doExpandAll = true;
            }
        }
        //#123940 end

    }
}
