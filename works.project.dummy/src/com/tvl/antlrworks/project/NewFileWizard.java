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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.Sources;
import org.netbeans.modules.project.uiapi.ProjectChooserFactory;
import org.netbeans.spi.project.ui.templates.support.Templates;
import org.openide.WizardDescriptor;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;

import org.openide.loaders.TemplateWizard;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

@NbBundle.Messages({
    "# {0} - Title",
    "# {1} - Subtitle",
    "LBL_NewFileWizard_Message={0} {1}",
    "LBL_NewFileWizard_Title=New File",
    "LBL_NewFileWizard_Subtitle=New",
    "ACSN_NewFileWizard=New file wizard",
    "ACSD_NewFileWizard=Choose new file type",
})
public final class NewFileWizard extends TemplateWizard {

    private Project currP;
    // private String[] recommendedTypes;
    private Project getCurrentProject() {
        return currP;
    }

    private void setCurrentProject(Project p) {
        this.currP = p;
    }

    public NewFileWizard(Project project /*, String recommendedTypes[] */) {
        setCurrentProject(project);
        putProperty(ProjectChooserFactory.WIZARD_KEY_PROJECT, getCurrentProject());
        // this.recommendedTypes = recommendedTypes;        
        //setTitleFormat( new MessageFormat( "{0}") );
        addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // check ProjectChooserFactory.WIZARD_KEY_PROJECT property
                if (ProjectChooserFactory.WIZARD_KEY_PROJECT.equals(evt.getPropertyName())) {
                    Project newProject = (Project) evt.getNewValue();
                    if (!Utilities.compareObjects(getCurrentProject(), newProject)) {
                        // set the new project and force reload panels in wizard
                        setCurrentProject(newProject);
                        try {
                            //reload (DataObject.find (Templates.getTemplate (NewFileWizard.this)));
                            // bugfix #44481, check if the template is null
                            if (Templates.getTemplate(NewFileWizard.this) != null) {
                                DataObject obj = DataObject.find(Templates.getTemplate(NewFileWizard.this));

                                // read the attributes declared in module's layer
                                Object unknownIterator = obj.getPrimaryFile().getAttribute("instantiatingIterator"); //NOI18N
                                if (unknownIterator == null) {
                                    unknownIterator = obj.getPrimaryFile().getAttribute("templateWizardIterator"); //NOI18N
                                }
                                // set default NewFileIterator if no attribute is set
                                if (unknownIterator == null) {
                                    try {
                                        obj.getPrimaryFile().setAttribute("instantiatingIterator", NewFileIterator.genericFileIterator()); //NOI18N
                                    } catch (java.io.IOException e) {
                                        // can ignore it because a iterator will created though
                                    }
                                }
                                Hacks.reloadPanelsInWizard(NewFileWizard.this, obj);
                            }
                        } catch (DataObjectNotFoundException ex) {
                            Exceptions.printStackTrace(ex);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void updateState() {
        super.updateState();
        String substitute = (String) getProperty("NewFileWizard_Title"); // NOI18N
        String title;
        if (substitute == null) {
            title = Bundle.LBL_NewFileWizard_Title(); // NOI18N
        } else {
            title = Bundle.LBL_NewFileWizard_Message(Bundle.LBL_NewFileWizard_Subtitle(), substitute);
        }
        super.setTitle(title);
    }

    @Override
    public void setTitle(String ignore) {
    }

    @Override
    protected WizardDescriptor.Panel<WizardDescriptor> createTemplateChooser() {
        WizardDescriptor.Panel<WizardDescriptor> panel = new TemplateChooserPanel(getCurrentProject() /*, recommendedTypes */);
        JComponent jc = (JComponent) panel.getComponent();
        jc.getAccessibleContext().setAccessibleName(Bundle.ACSN_NewFileWizard()); // NOI18N
        jc.getAccessibleContext().setAccessibleDescription(Bundle.ACSD_NewFileWizard()); // NOI18N
        return panel;
    }

    @Override
    protected WizardDescriptor.Panel<WizardDescriptor> createTargetChooser() {
        Sources c = ProjectUtils.getSources(getCurrentProject());
        return Templates.buildSimpleTargetChooser(getCurrentProject(), c.getSourceGroups(Sources.TYPE_GENERIC)).create();
    }
}
 