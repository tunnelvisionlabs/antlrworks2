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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.awt.Mnemonics;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.ChangeSupport;
import org.openide.util.NbBundle.Messages;

import static com.tvl.antlrworks.project.Bundle.*;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.util.NbPreferences;

/**
 *
 * @author  phrebejk
 */
public class SimpleTargetChooserPanelGUI extends javax.swing.JPanel implements ActionListener, DocumentListener {

    /** preferred dimension of the panels */
    private static final Dimension PREF_DIM = new Dimension(500, 340);

    private String expectedExtension;
    private final ChangeSupport changeSupport = new ChangeSupport(this);
    private boolean isFolder;
    private boolean freeFileExtension;

    @SuppressWarnings("LeakingThisInConstructor")
    @Messages("LBL_SimpleTargetChooserPanel_Name=Name and Location")
    public SimpleTargetChooserPanelGUI( boolean isFolder, boolean freeFileExtension) {
        this.isFolder = isFolder;
        this.freeFileExtension = freeFileExtension;
        initComponents();

        initValues( null, null, null );

        setPreferredSize(PREF_DIM);

        browseButton.addActionListener( this );
        documentNameTextField.getDocument().addDocumentListener( this );
        folderTextField.getDocument().addDocumentListener( this );

        setName(LBL_SimpleTargetChooserPanel_Name());
    }

    @Messages({
        "LBL_SimpleTargetChooserPanelGUI_NewFilePrefix=new",
        "LBL_TargetChooser_FolderName_Label=Folder &Name:",
        "LBL_TargetChooser_ParentFolder_Label=Pa&rent Folder:",
        "LBL_TargetChooser_CreatedFolder_Label=&Created Folder:",
        "LBL_TargetChooser_FileName_Label=File &Name:",
        "LBL_TargetChooser_Folder_Label=Fo&lder:",
        "LBL_TargetChooser_CreatedFile_Label=&Created File:",
        "# sample folder name", "LBL_folder_name=folder"
    })
    final void initValues(FileObject template, FileObject preselectedFolder, String documentName) {
        if (preselectedFolder == null) {
            Preferences preferences = NbPreferences.forModule(SimpleTargetChooserPanelGUI.class);
            JFileChooser chooser = new FileChooserBuilder(SimpleTargetChooserPanelGUI.class).setDirectoriesOnly(true).createFileChooser();
            File rootFolder = chooser.getSelectedFile();
            if (rootFolder == null || !rootFolder.isDirectory()) {
                rootFolder = chooser.getCurrentDirectory();
            }

            File lastFolder = new File(preferences.get("directories.newfilenoproject", rootFolder.getPath()));
            rootFolder = lastFolder.isDirectory() ? lastFolder : rootFolder;
            preselectedFolder = FileUtil.toFileObject(rootFolder);
        }

        folderTextField.setText(FileUtil.toFile(preselectedFolder).getAbsolutePath());

        String ext = template == null ? "" : template.getExt(); // NOI18N
        expectedExtension = ext.length() == 0 ? "" : "." + ext; // NOI18N

        String displayName = null;
        try {
            if (template != null) {
                DataObject templateDo = DataObject.find (template);
                displayName = templateDo.getNodeDelegate ().getDisplayName ();
            }
        } catch (DataObjectNotFoundException ex) {
            displayName = template.getName ();
        }
        putClientProperty ("NewFileWizard_Title", displayName);// NOI18N
        if (template != null) {
            final String baseName = isFolder ? LBL_folder_name() : LBL_SimpleTargetChooserPanelGUI_NewFilePrefix() + template.getName ();
            if (documentName == null) {
                documentName = baseName;
            }
            if (preselectedFolder != null) {
                int index = 0;
                while (true) {
                    FileObject _tmp = preselectedFolder.getFileObject(documentName, template.getExt());
                    if (_tmp == null) {
                        break;
                    }
                    documentName = baseName + ++index;
                }
            }

            documentNameTextField.setText (documentName);
            documentNameTextField.selectAll ();
        }

        if (isFolder) {
            Mnemonics.setLocalizedText(jLabel3, LBL_TargetChooser_FolderName_Label());
            Mnemonics.setLocalizedText(jLabel2, LBL_TargetChooser_ParentFolder_Label());
            Mnemonics.setLocalizedText(jLabel4, LBL_TargetChooser_CreatedFolder_Label());
        } else {
            Mnemonics.setLocalizedText(jLabel3, LBL_TargetChooser_FileName_Label());
            Mnemonics.setLocalizedText(jLabel2, LBL_TargetChooser_Folder_Label());
            Mnemonics.setLocalizedText(jLabel4, LBL_TargetChooser_CreatedFile_Label());
        }
    }

    public String getTargetFolder() {

        String folderName = folderTextField.getText().trim();

        if ( folderName.length() == 0 ) {
            return null;
        }
        else {
            return folderName.replace( File.separatorChar, '/' ); // NOI18N
        }
    }

    public String getTargetName() {

        String text = documentNameTextField.getText().trim();

        if ( text.length() == 0 ) {
            return null;
        }
        else {
            return text;
        }
    }

    public void addChangeListener(ChangeListener l) {
        changeSupport.addChangeListener(l);
    }

    public void removeChangeListener(ChangeListener l) {
        changeSupport.removeChangeListener(l);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        documentNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        folderTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        fileTextField = new javax.swing.JTextField();
        targetSeparator = new javax.swing.JSeparator();
        javax.swing.JPanel bottomPanelContainer = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel3.setLabelFor(documentNameTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_FileName_Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanel1.add(documentNameTextField, gridBagConstraints);
        documentNameTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(SimpleTargetChooserPanelGUI.class).getString("AD_documentNameTextField")); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 24, 0);
        add(jPanel1, gridBagConstraints);

        jLabel2.setLabelFor(folderTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_Folder_Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 12, 0);
        add(folderTextField, gridBagConstraints);
        folderTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(SimpleTargetChooserPanelGUI.class).getString("AD_folderTextField")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(browseButton, org.openide.util.NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_Browse_Button")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 12, 0);
        add(browseButton, gridBagConstraints);
        browseButton.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(SimpleTargetChooserPanelGUI.class).getString("AD_browseButton")); // NOI18N

        jLabel4.setLabelFor(fileTextField);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(SimpleTargetChooserPanelGUI.class, "LBL_TargetChooser_CreatedFile_Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        add(jLabel4, gridBagConstraints);

        fileTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 12, 0);
        add(fileTextField, gridBagConstraints);
        fileTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(SimpleTargetChooserPanelGUI.class).getString("AD_fileTextField")); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        add(targetSeparator, gridBagConstraints);

        bottomPanelContainer.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        add(bottomPanelContainer, gridBagConstraints);

        getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getBundle(SimpleTargetChooserPanelGUI.class).getString("AD_SimpleTargetChooserPanelGUI")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JTextField documentNameTextField;
    private javax.swing.JTextField fileTextField;
    private javax.swing.JTextField folderTextField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator targetSeparator;
    // End of variables declaration//GEN-END:variables

    private String getRelativeNativeName( FileObject root, FileObject folder ) {
        assert root != null;
        String path;

        if (folder == null) {
            path = ""; // NOI18N
        }
        else {
            path = FileUtil.getRelativePath( root, folder );
        }

        return path == null ? "" : path.replace( '/', File.separatorChar ); // NOI18N
    }

    private void updateCreatedFolder() {
        String folderName = folderTextField.getText().trim();
        String documentName = documentNameTextField.getText().trim();

        String createdFileName = folderName +
            ( folderName.endsWith("/") || folderName.endsWith( File.separator ) || folderName.length() == 0 ? "" : "/" ) + // NOI18N
            documentName + (!freeFileExtension || documentName.indexOf('.') == -1 ? expectedExtension : "");

        fileTextField.setText( createdFileName.replace( '/', File.separatorChar ) ); // NOI18N
        changeSupport.fireChange();
    }


    // ActionListener implementation -------------------------------------------

    public @Override void actionPerformed(ActionEvent e) {
        if ( browseButton == e.getSource() ) {
            FileChooserBuilder builder = new FileChooserBuilder(SimpleTargetChooserPanelGUI.class)
                .setDirectoriesOnly(true);

            String targetFolder = getTargetFolder();
            File existing = targetFolder != null ? new File(targetFolder) : null;
            if (existing != null && existing.isDirectory()) {
                builder.setDefaultWorkingDirectory(existing);
                builder.forceUseOfDefaultWorkingDirectory(true);
            }

            File targetDirectory = builder.showOpenDialog();
            if (targetDirectory != null && targetDirectory.isDirectory()) {
                folderTextField.setText(targetDirectory.getAbsolutePath());
            }
        }
    }

    // DocumentListener implementation -----------------------------------------

    public @Override void changedUpdate(DocumentEvent e) {
        updateCreatedFolder();
    }

    public @Override void insertUpdate(DocumentEvent e) {
        updateCreatedFolder();
    }

    public @Override void removeUpdate(DocumentEvent e) {
        updateCreatedFolder();
    }
}
