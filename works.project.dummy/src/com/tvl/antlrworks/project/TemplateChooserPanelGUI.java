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

package com.tvl.antlrworks.project;

import static com.tvl.antlrworks.project.Bundle.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.modules.project.ui.spi.TemplateCategorySorter;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataShadow;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.AsyncGUIJob;
import org.openide.util.ChangeSupport;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;

/**
 * Provides the GUI for the template chooser panel.
 * @author Jesse Glick
 */
final class TemplateChooserPanelGUI extends javax.swing.JPanel implements PropertyChangeListener, AsyncGUIJob {
    
    /** preferred dimension of the panels */
    private static final Dimension PREF_DIM = new Dimension(500, 340);
    
    // private final String[] recommendedTypes = null;
    private final ChangeSupport changeSupport = new ChangeSupport(this);

    //Templates folder root
    private FileObject templatesFolder;

    //GUI Builder
    private TemplatesPanelGUI.Builder builder;
    private @NonNull String[] projectRecommendedTypes = new String[0];
    private String category;
    private String template;
    private boolean isWarmUp = true;
    private boolean firstTime = true;
    private ActionListener defaultActionListener;

    @Messages("LBL_TemplateChooserPanelGUI_Name=Choose File Type")
    public TemplateChooserPanelGUI() {
        this.builder = new FileChooserBuilder ();
        initComponents();
        setPreferredSize( PREF_DIM );
        setName(LBL_TemplateChooserPanelGUI_Name());
     }
    
    public void readValues (String category, String template) {
        boolean wf;
        synchronized (this) {
            this.category = category;
            this.template = template;
            wf = this.isWarmUp;
        }
        if (!wf) {
            ((TemplatesPanelGUI)this.templatesPanel).setSelectedCategoryByName (this.category);
            ((TemplatesPanelGUI)this.templatesPanel).setSelectedTemplateByName (this.template);
        }
    }

    public void addChangeListener(ChangeListener l) {
        changeSupport.addChangeListener(l);
    }
    
    public void removeChangeListener(ChangeListener l) {
        changeSupport.removeChangeListener(l);
    }
    
    private void fireChange() {
        changeSupport.fireChange();
    }

    void setDefaultActionListener( ActionListener al ) {
        this.defaultActionListener = al;
    }

    public FileObject getTemplate() {
        return ((TemplatesPanelGUI)this.templatesPanel).getSelectedTemplate ();
    }
    
    @Override public void propertyChange(PropertyChangeEvent evt) {
        fireChange();
    }
    
    
    @Override public Dimension getPreferredSize() {
        return PREF_DIM;
    }
    
    public String getCategoryName () {
        return ((TemplatesPanelGUI)this.templatesPanel).getSelectedCategoryName ();
    }

    public String getTemplateName () {
        return ((TemplatesPanelGUI)this.templatesPanel).getSelectedTemplateName ();
    }

    public void setCategory (String category) {
        ((TemplatesPanelGUI)this.templatesPanel).setSelectedCategoryByName (category);
    }
    
    @Override public void addNotify () {
        if (firstTime) {
            //77244 prevent multiple initializations..
            Utilities.attachInitJob (this, this);
            firstTime = false;
        }
        super.addNotify ();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        templatesPanel = new TemplatesPanelGUI (this.builder);

        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(templatesPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel templatesPanel;
    // End of variables declaration//GEN-END:variables

    // private static final Comparator NATURAL_NAME_SORT = Collator.getInstance();

    private static final class TemplateKey {
        final DataObject d;
        final boolean leaf;
        TemplateKey(DataObject d, boolean leaf) {
            this.d = d;
            this.leaf = leaf;
        }
        @Override public boolean equals(Object o) {
            if (!(o instanceof TemplateKey)) {
                return false;
            }
            return d == ((TemplateKey) o).d && leaf == ((TemplateKey) o).leaf;
        }
        @Override public int hashCode() {
            return d.hashCode();
        }
    }
    private final class TemplateChildren extends ChildFactory.Detachable<TemplateKey> implements ActionListener {
        
        private final DataFolder folder;
        private final boolean isRoot;
        
        TemplateChildren(DataFolder folder, boolean root) {
            this.folder = folder;
            isRoot = root;
        }
        
        @Override protected void addNotify() {
        }
        
        @Override protected void removeNotify() {
        }

        @Override protected boolean createKeys(List<TemplateKey> keys) {
            DataObject[] children = folder.getChildren();
            if (isRoot) {
                TemplateCategorySorter tcs = Lookup.getDefault().lookup(TemplateCategorySorter.class);
                List<DataObject> dobjs = new ArrayList<DataObject>();                    
                for (DataObject d : children) {
                    if (isFolderOfTemplates(d)) {
                        dobjs.add(d);
                    }
                }
                List<DataObject> sorted = tcs != null ? tcs.sort(dobjs) : dobjs;
                assert sorted.size() == dobjs.size() && new HashSet<DataObject>(dobjs).equals(new HashSet<DataObject>(sorted));
                children = sorted.toArray(new DataObject[children.length]);
            }
            
            for (DataObject d : children) {
                if (isFolderOfTemplates(d)) {
                    boolean leaf = true;
                    for (DataObject child : ((DataFolder) d).getChildren()) {
                        if (isFolderOfTemplates(child)) {
                            leaf = false;
                            break;
                        }
                    }
                    keys.add(new TemplateKey(d, leaf));
                }
            }
            return true;
        }
        
        @Override protected Node createNodeForKey(TemplateKey k) {
            return new FilterNode(k.d.getNodeDelegate(), k.leaf ? Children.LEAF : Children.create(new TemplateChildren((DataFolder) k.d, false), true));
        }
        
        @Override public void actionPerformed (ActionEvent event) {
            final String cat = getCategoryName ();
            String template =  ((TemplatesPanelGUI)TemplateChooserPanelGUI.this.templatesPanel).getSelectedTemplateName();
            refresh(false);
            setCategory (cat);
            ((TemplatesPanelGUI)TemplateChooserPanelGUI.this.templatesPanel).setSelectedTemplateByName(template);
        }
                
        
        // Private methods -----------------------------------------------------
        
        private boolean isFolderOfTemplates(DataObject d) {
            if (d instanceof DataFolder && !isTemplate((DataFolder)d))  {
                Object o = d.getPrimaryFile().getAttribute("simple"); // NOI18N
                if (o == null || Boolean.TRUE.equals(o)) {
                    return hasChildren(d);
                }
            }
            return false;
        }
        
    }
    
    private final class FileChildren extends ChildFactory<DataObject> {
        
        private DataFolder root;
                
        public FileChildren (DataFolder folder) {
            this.root = folder;
            assert this.root != null : "Root can not be null";  //NOI18N
        }
        
        @Override protected boolean createKeys(List<DataObject> keys) {
            for (DataObject dobj : root.getChildren()) {
                if (isTemplate(dobj)) {
                    if (dobj instanceof DataShadow) {
                        dobj = ((DataShadow) dobj).getOriginal();
                    }
                    keys.add(dobj);
                }
            }
            return true;
        }

        @Override protected Node createNodeForKey(DataObject d) {
            return new FilterNode(d.getNodeDelegate(), Children.LEAF);
        }
        
    }
    
  
    final class FileChooserBuilder implements TemplatesPanelGUI.Builder {
        
        @Override public Children createCategoriesChildren(DataFolder folder) {
            return Children.create(new TemplateChildren(folder, true), true);
        }
        
        @Override public Children createTemplatesChildren(DataFolder folder) {
            return Children.create(new FileChildren(folder), true);
        }

        @Override public void fireChange() {
            TemplateChooserPanelGUI.this.fireChange();
        }

        @Messages("CTL_Categories=&Categories:")
        @Override public String getCategoriesName() {
            return CTL_Categories();
        }
        
        @Messages("CTL_Files=&File Types:")
        @Override public String getTemplatesName() {
            return CTL_Files();
        }

        @Override
        public void actionPerformed( ActionEvent e ) {
            if( null != defaultActionListener ) {
                defaultActionListener.actionPerformed( e );
            }
        }
    }
    
    
    private static boolean isTemplate (DataObject dobj) {
        if (dobj.isTemplate())
            return true;
        if (dobj instanceof DataShadow) {
            return ((DataShadow)dobj).getOriginal().isTemplate();
        }
        return false;
    }
    
    private boolean hasChildren (DataObject folder) { 
        if (!(folder instanceof DataFolder)) {
            return false;
        }
        
        DataFolder f = (DataFolder) folder;
        DataObject[] ch = f.getChildren ();
        for (int i = 0; i < ch.length; i++) {
            if (isTemplate (ch[i])) {
                // XXX: how to filter link to Package template in each java types folder?
                if (!(ch[i] instanceof DataShadow)) {
                    return true;
                }
            } else if (ch[i] instanceof DataFolder && hasChildren (ch[i])) {
                return true;
            }
        }
        return false;
        
        // simplied but more counts
        //return new FileChildren (p, (DataFolder) folder).getNodesCount () > 0;
        
    }
    
    @Override public void construct() {
        this.templatesFolder = FileUtil.getConfigFile("Templates");
        ((TemplatesPanelGUI)this.templatesPanel).warmUp(this.templatesFolder);
    }
    
    @Override public void finished() {
        //In the awt
        Cursor cursor = null;
        try {
            String c,t;
            synchronized (this) {
                c = this.category;
                t = this.template;
            }
            cursor = TemplateChooserPanelGUI.this.getCursor();
            TemplateChooserPanelGUI.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            ((TemplatesPanelGUI)this.templatesPanel).doFinished (this.templatesFolder, c, t);
        } finally {
            synchronized (this) {
                isWarmUp = false;
            }
            if (cursor != null) {
                this.setCursor (cursor);
            }
        }
    }
    
}
