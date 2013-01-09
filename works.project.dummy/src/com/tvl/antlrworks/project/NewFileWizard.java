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

import javax.swing.JComponent;
import org.openide.WizardDescriptor;

import org.openide.loaders.TemplateWizard;
import org.openide.util.NbBundle;

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

    public NewFileWizard() {
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
        WizardDescriptor.Panel<WizardDescriptor> panel = new TemplateChooserPanel();
        JComponent jc = (JComponent) panel.getComponent();
        jc.getAccessibleContext().setAccessibleName(Bundle.ACSN_NewFileWizard()); // NOI18N
        jc.getAccessibleContext().setAccessibleDescription(Bundle.ACSD_NewFileWizard()); // NOI18N
        return panel;
    }

    @Override
    protected WizardDescriptor.Panel<WizardDescriptor> createTargetChooser() {
        return new SimpleTargetChooserPanel(false, false);
    }
}
 