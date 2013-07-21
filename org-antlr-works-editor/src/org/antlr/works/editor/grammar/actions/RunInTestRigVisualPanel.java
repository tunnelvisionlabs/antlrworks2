/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.filesystems.FileChooserBuilder;

/**
 *
 * @author Sam Harwell
 */
public final class RunInTestRigVisualPanel extends javax.swing.JPanel {
    private final RunInTestRigWizardPanel _wizardPanel;
    private final DocumentListener _documentListener = new DocumentListener() {

        @Override
        public void insertUpdate(DocumentEvent e) {
            fireChange();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            fireChange();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            fireChange();
        }

    };

    private final ActionListener _actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            fireChange();
        }

    };

    /**
     * Creates new form RunInTestRigPanel
     */
    public RunInTestRigVisualPanel() {
        this(null);
    }

    public RunInTestRigVisualPanel(@NullAllowed RunInTestRigWizardPanel wizardPanel) {
        initComponents();
        _wizardPanel = wizardPanel;
        if (wizardPanel != null) {
            setInputFile(wizardPanel.getInputFile());
            setAvailableRules(wizardPanel.getAvailableRules(), wizardPanel.getStartRule());
            setEncodingSpecified(wizardPanel.isEncodingSpecified());
            setEncoding(wizardPanel.getEncoding());
            setShowTokens(wizardPanel.isShowTokens());
            setShowTree(wizardPanel.isShowTree());
            setShowTreeInGUI(wizardPanel.isShowTreeInGUI());
        }

        txtInputFile.getDocument().addDocumentListener(_documentListener);
        cmbStartRule.addActionListener(_actionListener);
    }

    public String getInputFile() {
        return txtInputFile.getText();
    }

    public void setInputFile(String value) {
        if (value == null) {
            value = "";
        }

        txtInputFile.setText(value);
    }

    public String getStartRule() {
        if (cmbStartRule.getSelectedItem() == null) {
            return "";
        }

        return cmbStartRule.getSelectedItem().toString();
    }

    public void setAvailableRules(List<String> rules, String selectedRule) {
        cmbStartRule.setModel(new DefaultComboBoxModel<>(rules.toArray(new String[rules.size()])));
        if (selectedRule != null) {
            cmbStartRule.setSelectedItem(selectedRule);
        }
    }

    public boolean isEncodingSpecified() {
        return chkEncoding.isSelected();
    }

    public void setEncodingSpecified(boolean value) {
        chkEncoding.setSelected(value);
    }

    public String getEncoding() {
        return txtEncoding.getText();
    }

    public void setEncoding(String value) {
        if (value == null) {
            value = Locale.getDefault().toLanguageTag();
        }

        txtEncoding.setText(value);
    }

    public boolean isShowTokens() {
        return chkShowTokens.isSelected();
    }

    public void setShowTokens(boolean value) {
        chkShowTokens.setSelected(value);
    }

    public boolean isShowTree() {
        return chkShowTree.isSelected();
    }

    public void setShowTree(boolean value) {
        chkShowTree.setSelected(value);
    }

    public boolean isShowTreeInGUI() {
        return chkShowTreeInGUI.isSelected();
    }

    public void setShowTreeInGUI(boolean value) {
        chkShowTreeInGUI.setSelected(value);
    }

    @Override
    public String getName() {
        return "TestRig Options";
    }

    private void fireChange() {
        if (_wizardPanel != null) {
            _wizardPanel.getChangeSupport().fireChange();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("Convert2Diamond")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        javax.swing.JPanel pnlInput = new javax.swing.JPanel();
        javax.swing.JLabel lblInputFile = new javax.swing.JLabel();
        txtInputFile = new javax.swing.JTextField();
        btnBrowseInput = new javax.swing.JButton();
        cmbStartRule = new javax.swing.JComboBox<String>();
        javax.swing.JLabel lblStartRule = new javax.swing.JLabel();
        chkEncoding = new javax.swing.JCheckBox();
        txtEncoding = new javax.swing.JTextField();
        pnlOptions = new javax.swing.JPanel();
        chkShowTokens = new javax.swing.JCheckBox();
        chkShowTree = new javax.swing.JCheckBox();
        chkShowTreeInGUI = new javax.swing.JCheckBox();

        pnlInput.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(RunInTestRigVisualPanel.class, "RunInTestRigVisualPanel.pnlInput.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblInputFile, org.openide.util.NbBundle.getMessage(RunInTestRigVisualPanel.class, "RunInTestRigVisualPanel.lblInputFile.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnBrowseInput, org.openide.util.NbBundle.getMessage(RunInTestRigVisualPanel.class, "RunInTestRigVisualPanel.btnBrowseInput.text")); // NOI18N
        btnBrowseInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseInputActionPerformed(evt);
            }
        });

        cmbStartRule.setEditable(true);

        org.openide.awt.Mnemonics.setLocalizedText(lblStartRule, org.openide.util.NbBundle.getMessage(RunInTestRigVisualPanel.class, "RunInTestRigVisualPanel.lblStartRule.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(chkEncoding, org.openide.util.NbBundle.getMessage(RunInTestRigVisualPanel.class, "RunInTestRigVisualPanel.chkEncoding.text")); // NOI18N

        txtEncoding.setText(org.openide.util.NbBundle.getMessage(RunInTestRigVisualPanel.class, "RunInTestRigVisualPanel.txtEncoding.text")); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, chkEncoding, org.jdesktop.beansbinding.ELProperty.create("${selected}"), txtEncoding, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout pnlInputLayout = new javax.swing.GroupLayout(pnlInput);
        pnlInput.setLayout(pnlInputLayout);
        pnlInputLayout.setHorizontalGroup(
            pnlInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInputFile)
                    .addComponent(lblStartRule)
                    .addComponent(chkEncoding))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbStartRule, 0, 307, Short.MAX_VALUE)
                    .addComponent(txtEncoding)
                    .addComponent(txtInputFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBrowseInput))
        );
        pnlInputLayout.setVerticalGroup(
            pnlInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInputLayout.createSequentialGroup()
                .addGroup(pnlInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBrowseInput)
                    .addComponent(txtInputFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInputFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbStartRule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStartRule))
                .addGap(6, 6, 6)
                .addGroup(pnlInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkEncoding)
                    .addComponent(txtEncoding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlOptions.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(RunInTestRigVisualPanel.class, "RunInTestRigVisualPanel.pnlOptions.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(chkShowTokens, org.openide.util.NbBundle.getMessage(RunInTestRigVisualPanel.class, "RunInTestRigVisualPanel.chkShowTokens.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(chkShowTree, org.openide.util.NbBundle.getMessage(RunInTestRigVisualPanel.class, "RunInTestRigVisualPanel.chkShowTree.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(chkShowTreeInGUI, org.openide.util.NbBundle.getMessage(RunInTestRigVisualPanel.class, "RunInTestRigVisualPanel.chkShowTreeInGUI.text")); // NOI18N

        javax.swing.GroupLayout pnlOptionsLayout = new javax.swing.GroupLayout(pnlOptions);
        pnlOptions.setLayout(pnlOptionsLayout);
        pnlOptionsLayout.setHorizontalGroup(
            pnlOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkShowTokens)
                    .addComponent(chkShowTree)
                    .addComponent(chkShowTreeInGUI))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOptionsLayout.setVerticalGroup(
            pnlOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOptionsLayout.createSequentialGroup()
                .addComponent(chkShowTokens)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkShowTree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkShowTreeInGUI)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseInputActionPerformed
        browseForFile(txtInputFile);
    }//GEN-LAST:event_btnBrowseInputActionPerformed

    private void browseForFile(JTextField field) {
        FileChooserBuilder builder = new FileChooserBuilder(RunInTestRigVisualPanel.class)
            .setTitle("Select input file");

        File existing = new File(field.getText());
        if (existing.isFile()) {
            builder.setFilesOnly(true);
            builder.setDefaultWorkingDirectory(existing.getParentFile());
            builder.forceUseOfDefaultWorkingDirectory(true);
        }

        File targetFile = builder.showOpenDialog();
        if (targetFile != null && targetFile.isFile()) {
            field.setText(targetFile.getAbsolutePath());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseInput;
    private javax.swing.JCheckBox chkEncoding;
    private javax.swing.JCheckBox chkShowTokens;
    private javax.swing.JCheckBox chkShowTree;
    private javax.swing.JCheckBox chkShowTreeInGUI;
    private javax.swing.JComboBox<String> cmbStartRule;
    private javax.swing.JPanel pnlOptions;
    private javax.swing.JTextField txtEncoding;
    private javax.swing.JTextField txtInputFile;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
