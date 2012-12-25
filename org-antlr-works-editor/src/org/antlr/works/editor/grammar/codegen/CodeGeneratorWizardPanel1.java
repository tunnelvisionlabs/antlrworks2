/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codegen;

import java.io.File;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.ChangeSupport;
import org.openide.util.HelpCtx;

public class CodeGeneratorWizardPanel1 implements WizardDescriptor.Panel<WizardDescriptor> {
    public static final String OUTPUT_DIRECTORY = "outputDirectory";
    public static final String LIBRARY_DIRECTORY = "libraryDirectory";
    public static final String SELECTED_TARGET = "selectedTarget";

    private final ChangeSupport _changeSupport = new ChangeSupport(this);

    private String _outputDirectory;
    private String _libraryDirectory;
    private String _targetName;

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, use {@link #getComponent}.
     */
    private CodeGeneratorVisualPanel1 component;

    /*package*/ ChangeSupport getChangeSupport() {
        return _changeSupport;
    }

    public String getOutputDirectory() {
        if (component != null) {
            return component.getOutputDirectory();
        }

        return _outputDirectory;
    }

    public void setOutputDirectory(String value) {
        if (component != null) {
            component.setOutputDirectory(value);
        }

        _outputDirectory = value;
    }

    public String getLibraryDirectory() {
        if (component != null) {
            return component.getLibraryDirectory();
        }

        return _libraryDirectory;
    }

    public void setLibraryDirectory(String value) {
        if (component != null) {
            component.setLibraryDirectory(value);
        }

        _libraryDirectory = value;
    }

    public String getTargetName() {
        if (component != null) {
            return component.getTargetName();
        }

        return _targetName;
    }

    public void setTargetName(String targetName) {
        if (component != null) {
            component.setTargetName(targetName);
        }

        this._targetName = targetName;
    }

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public CodeGeneratorVisualPanel1 getComponent() {
        if (component == null) {
            component = new CodeGeneratorVisualPanel1(this);
            _changeSupport.fireChange();
        }

        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public boolean isValid() {
        String outputDirectory = getOutputDirectory();
        if (outputDirectory == null || outputDirectory.isEmpty()) {
            return false;
        }

        if (!new File(outputDirectory).isDirectory()) {
            return false;
        }

        String libraryDirectory = getLibraryDirectory();
        if (libraryDirectory != null && !libraryDirectory.isEmpty()) {
            if (!new File(libraryDirectory).isDirectory()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void addChangeListener(ChangeListener l) {
        _changeSupport.addChangeListener(l);
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
        _changeSupport.removeChangeListener(l);
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        setOutputDirectory(CodeGeneratorWizardOptions.Location.getOutputDirectory(wiz));
        setLibraryDirectory(CodeGeneratorWizardOptions.Location.getLibraryDirectory(wiz));
        setTargetName(CodeGeneratorWizardOptions.Location.getSelectedTarget(wiz));
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        CodeGeneratorWizardOptions.setProperty(wiz, OUTPUT_DIRECTORY, getOutputDirectory());
        CodeGeneratorWizardOptions.setProperty(wiz, LIBRARY_DIRECTORY, getLibraryDirectory());
        CodeGeneratorWizardOptions.setProperty(wiz, SELECTED_TARGET, getTargetName());
    }
}
