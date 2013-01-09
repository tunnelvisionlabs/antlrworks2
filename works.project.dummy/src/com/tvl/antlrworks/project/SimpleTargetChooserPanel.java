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

import java.awt.Component;
import java.io.File;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.spi.project.ui.templates.support.Templates;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.ChangeSupport;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;

/**
 *
 * @author  Petr Hrebejk
 */
final class SimpleTargetChooserPanel implements WizardDescriptor.Panel<WizardDescriptor>, ChangeListener {

    private final ChangeSupport changeSupport = new ChangeSupport(this);
    private SimpleTargetChooserPanelGUI gui;

    private WizardDescriptor wizard;
    private boolean isFolder;
    private boolean freeFileExtension;
    
    @SuppressWarnings("LeakingThisInConstructor")
    SimpleTargetChooserPanel(boolean isFolder, boolean freeFileExtension) {
        this.isFolder = isFolder;
        this.freeFileExtension = freeFileExtension;
        this.gui = null;
    }

    public @Override Component getComponent() {
        if (gui == null) {
            gui = new SimpleTargetChooserPanelGUI(isFolder, freeFileExtension);
            gui.addChangeListener(this);
        }
        return gui;
    }

    public @Override HelpCtx getHelp() {
        //XXX
        return null;
    }

    public @Override boolean isValid() {
        boolean ok = ( gui != null && gui.getTargetName() != null && gui.getTargetFolder() != null );
        
        if (!ok) {
            return false;
        }
        
        // check if the file name can be created
        FileObject template = Templates.getTemplate( wizard );

        String errorMessage = ProjectUtilities.canUseFileName(null,
                gui.getTargetFolder(), gui.getTargetName(), template.getExt(), isFolder, freeFileExtension);
        wizard.putProperty(WizardDescriptor.PROP_ERROR_MESSAGE, errorMessage);

        return errorMessage == null;
    }

    public @Override void addChangeListener(ChangeListener l) {
        changeSupport.addChangeListener(l);
    }

    public @Override void removeChangeListener(ChangeListener l) {
        changeSupport.removeChangeListener(l);
    }

    @Messages({
        "LBL_TemplatesPanel_Name=Choose File Type"
    })
    @Override public void readSettings(WizardDescriptor settings) {
        wizard = settings;
        if ( gui == null ) {
            getComponent();
        }
        
        // Try to preselect a folder            
        FileObject preselectedTarget = Templates.getTargetFolder( wizard );
        // Try to preserve the already entered target name
        String targetName = isFolder ? null : Templates.getTargetName( wizard );
        // Init values
        gui.initValues( Templates.getTemplate( wizard ), preselectedTarget, targetName );
        
        // XXX hack, TemplateWizard in final setTemplateImpl() forces new wizard's title
        // this name is used in NewFileWizard to modify the title
        Object substitute = gui.getClientProperty ("NewFileWizard_Title"); // NOI18N
        if (substitute != null) {
            wizard.putProperty ("NewFileWizard_Title", substitute); // NOI18N
        }
        
        wizard.putProperty(WizardDescriptor.PROP_CONTENT_DATA, new String[] {Bundle.LBL_TemplatesPanel_Name(), Bundle.LBL_SimpleTargetChooserPanel_Name()});
    }
    
    public @Override void storeSettings(WizardDescriptor settings) {
        if (WizardDescriptor.PREVIOUS_OPTION.equals(settings.getValue())) {
            return;
        }
        if(!WizardDescriptor.CANCEL_OPTION.equals(settings.getValue()) && isValid()) {
            if ( gui == null ) {
                getComponent();
            }
            String name = gui.getTargetName ();
            if (name != null && name.indexOf ('/') > 0) { // NOI18N
                name = name.substring (name.lastIndexOf ('/') + 1);
            }
            
            Templates.setTargetFolder(settings, getTargetFolderFromGUI());
            Templates.setTargetName(settings, name);
            NbPreferences.forModule(SimpleTargetChooserPanelGUI.class).put("directories.newfilenoproject", getTargetFolderFromGUI().getPath());
        }
        settings.putProperty("NewFileWizard_Title", null); // NOI18N
    }

    public @Override void stateChanged(ChangeEvent e) {
        changeSupport.fireChange();
    }
    
    private FileObject getTargetFolderFromGUI () {
        String folderName = gui.getTargetFolder();
        String newObject = gui.getTargetName ();
        
        if (newObject.indexOf ('/') > 0) { // NOI18N
            String path = newObject.substring (0, newObject.lastIndexOf ('/')); // NOI18N
            folderName = folderName == null || "".equals (folderName) ? path : folderName + '/' + path; // NOI18N
        }

        FileObject targetFolder;
        if ( folderName == null ) {
            throw new IllegalArgumentException();
        }
        else {            
            targetFolder = FileUtil.toFileObject( new File(folderName) );
        }

//        if ( targetFolder == null ) {
//            // XXX add deletion of the file in uninitalize ow the wizard
//            try {
//                targetFolder = FileUtil.createFolder( rootFolder, folderName );
//            } catch (IOException ioe) {
//                // Can't create the folder
//                throw new IllegalArgumentException(ioe); // ioe already annotated
//            }
//        }
        
        return targetFolder;
    }
}
