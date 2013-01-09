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

import java.io.IOException;
import org.netbeans.modules.project.uiapi.ProjectChooserFactory;
import org.netbeans.modules.project.uiapi.Utilities;
import org.netbeans.spi.project.ui.support.CommonProjectActions;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.loaders.CreateFromTemplateHandler;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.loaders.TemplateWizard;

/**
 * Default implementations of template UI. 
 * <p>For methods which take a {@link WizardDescriptor} parameter,
 * use the wizard as passed to {@link org.openide.WizardDescriptor.InstantiatingIterator#initialize}
 * or {@link org.openide.loaders.TemplateWizard.Iterator#initialize}.
 */
public class Templates {
    
    private Templates() {}

    private static final String SET_AS_MAIN = "setAsMain"; // NOI18N
    
    /**
     * Find the template with which a custom template wizard iterator is associated.
     * <p class="nonnormative">
     * If the user selects File | New File, this will be the template chosen in the first panel.
     * If the user selects New from {@link org.netbeans.spi.project.ui.support.CommonProjectActions#newFileAction}, this will
     * be the template selected from the context submenu.
     * </p>
     * @param wizardDescriptor a file or project wizard
     * @return the corresponding template marker file (or null if not set)
     */
    public static FileObject getTemplate( WizardDescriptor wizardDescriptor ) {
        if (wizardDescriptor == null) {
            throw new IllegalArgumentException("Cannot pass a null wizardDescriptor"); // NOI18N
        }
        if ( wizardDescriptor instanceof TemplateWizard ) {
            DataObject template = ((TemplateWizard)wizardDescriptor).getTemplate();
            if (template != null) {
                return template.getPrimaryFile();            
            }
        }
        return (FileObject) wizardDescriptor.getProperty( ProjectChooserFactory.WIZARD_KEY_TEMPLATE );
    }
    
    /**
     * Find the target folder selected for a custom template wizard iterator.
     * <p class="nonnormative">
     * If the user selects File | New File
     * this may not be set, unless you have called {@link #setTargetFolder}
     * in an earlier panel (such as that created by {@link #createSimpleTargetChooser}).
     * It may however have a preselected folder, e.g. if the user invoked New from
     * the context menu of a folder.
     * </p>
     * @param wizardDescriptor a file wizard
     * @return the folder into which the user has requested this iterator create a file (or null if not set)
     */
    public static FileObject getTargetFolder( WizardDescriptor wizardDescriptor ) {
        
        if ( wizardDescriptor instanceof TemplateWizard ) {
            try {
                return ((TemplateWizard)wizardDescriptor).getTargetFolder().getPrimaryFile();
            }
            catch ( IOException e ) {
                return null;
            }
        }
        else {
            return (FileObject) wizardDescriptor.getProperty( ProjectChooserFactory.WIZARD_KEY_TARGET_FOLDER );
        }
    }

    /**
     * Find the existing sources folder selected for a custom template wizard iterator.
     * <p class="nonnormative">
     * This may not be set, unless you have {@link CommonProjectActions#newProjectAction}
     * with {@link CommonProjectActions#EXISTING_SOURCES_FOLDER} value.
     * <p>
     *
     * @param wizardDescriptor a project wizard
     * @return the existing sources folder from which the user has requested this iterator to create a project
     *
     * @since 1.3 (17th May 2005)
     */
    public static FileObject getExistingSourcesFolder( WizardDescriptor wizardDescriptor ) {         
        return (FileObject) wizardDescriptor.getProperty( CommonProjectActions.EXISTING_SOURCES_FOLDER );
    }    
    /**
     * Stores a target folder so that it can be remembered later using {@link #getTargetFolder}.
     * @param wizardDescriptor a file wizard
     * @param folder a target folder to remember
     */    
    public static void setTargetFolder( WizardDescriptor wizardDescriptor, FileObject folder ) {
        
        if ( wizardDescriptor instanceof TemplateWizard ) {
            if (folder == null) {
                //#103971
                ((TemplateWizard)wizardDescriptor).setTargetFolder( null );
            } else {
                DataFolder dataFolder = DataFolder.findFolder( folder );            
                ((TemplateWizard)wizardDescriptor).setTargetFolder( dataFolder );
            }
        }
        else {
            wizardDescriptor.putProperty( ProjectChooserFactory.WIZARD_KEY_TARGET_FOLDER, folder );
        }
    }

    /** Method to communicate current choice of target name to a custom 
     * {@link org.openide.WizardDescriptor.InstantiatingIterator} associated with particular template.
     * @param wizardDescriptor a file wizard
     * @return the selected target name (could be null?)
     * @see org.openide.loaders.TemplateWizard#getTargetName
     * @see ProjectChooserFactory#WIZARD_KEY_TARGET_NAME
     */
    public static String getTargetName( WizardDescriptor wizardDescriptor ) {
        if ( wizardDescriptor instanceof TemplateWizard ) {
            return ((TemplateWizard)wizardDescriptor).getTargetName();
        }
        else {
            return (String) wizardDescriptor.getProperty( ProjectChooserFactory.WIZARD_KEY_TARGET_NAME );
        }
    }
    
    /** Sets the target name for given WizardDescriptor to be used from
     * custom target choosers
     * @param wizardDescriptor a file wizard
     * @param targetName a desired target name
     * @see TemplateWizard#setTargetName
     * @see ProjectChooserFactory#WIZARD_KEY_TARGET_NAME
     */
    public static void setTargetName( WizardDescriptor wizardDescriptor, String targetName ) {
        if ( wizardDescriptor instanceof TemplateWizard ) {                        
            ((TemplateWizard)wizardDescriptor).setTargetName( targetName );
        }
        else {
            wizardDescriptor.putProperty( ProjectChooserFactory.WIZARD_KEY_TARGET_NAME, targetName );
        }
    }

    /**
     * Checks whether a project wizard will set the main project.
     * (The default is false.)
     * @param wizardDescriptor a project wizard
     * @return true if it will set a main project
     * @since org.netbeans.modules.projectuiapi/1 1.47
     */
    public static boolean getDefinesMainProject(WizardDescriptor wizardDescriptor) {
        return Boolean.TRUE.equals(wizardDescriptor.getProperty(SET_AS_MAIN));
    }

    /**
     * Builder for simple target choosers.
     * The chooser is suitable for many kinds of templates.
     * The user is prompted to choose a location for the new file and a name.
     * Instantiation is handled by {@link DataObject#createFromTemplate}.
     * @param project The project to work on.
     * @param folders a nonempty list of possible roots to create the new file in
     * @return a builder which can be used to customize and then create the target chooser
     * @since org.netbeans.modules.projectuiapi/1 1.45
     */
    public static SimpleTargetChooserBuilder buildSimpleTargetChooser() {
        return new SimpleTargetChooserBuilder();
    }

    /**
     * A builder for simple target choosers.
     * @see #buildSimpleTargetChooser
     * @since org.netbeans.modules.projectuiapi/1 1.45
     */
    public static final class SimpleTargetChooserBuilder {
        WizardDescriptor.Panel<WizardDescriptor> bottomPanel;
        boolean freeFileExtension;
        SimpleTargetChooserBuilder() {
        }
        /**
         * Sets a panel which should be placed underneath the default chooser.
         * @param bottomPanel a custom bottom panel
         * @return this builder
         */
        public SimpleTargetChooserBuilder bottomPanel(WizardDescriptor.Panel<WizardDescriptor> bottomPanel) {
            this.bottomPanel = bottomPanel;
            return this;
        }
        /**
         * Permits the file extension of the created file to be customized by the user.
         * By default, the file extension is fixed to be the same as that of the template:
         * whatever is entered for the filename is taken to be a base name only.
         * In this mode, the GUI makes it possible to use an alternate extension: it
         * simply checks for a file name containing a period (<samp>.</samp>) and
         * suppresses the automatic appending of the template's extension,
         * taking the entered filename as complete.
         * @return this builder
         * @see CreateFromTemplateHandler#FREE_FILE_EXTENSION
         */
        public SimpleTargetChooserBuilder freeFileExtension() {
            this.freeFileExtension = true;
            return this;
        }
        /**
         * Creates the target chooser panel.
         * @return a wizard panel prompting the user to choose a name and location
         */
        public WizardDescriptor.Panel<WizardDescriptor> create() {
            return new SimpleTargetChooserPanel(bottomPanel, false, freeFileExtension);
        }
    }
        
}
