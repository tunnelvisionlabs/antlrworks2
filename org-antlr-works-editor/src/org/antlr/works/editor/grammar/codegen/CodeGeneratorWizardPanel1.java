/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codegen;

import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class CodeGeneratorWizardPanel1 implements WizardDescriptor.Panel<WizardDescriptor> {
    public static final String OUTPUT_DIRECTORY = "outputDirectory";
    public static final String LIBRARY_DIRECTORY = "libraryDirectory";
    public static final String SELECTED_TARGET = "selectedTarget";

    private String outputDirectory;
    private String libraryDirectory;
    private String targetName;

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, use {@link #getComponent}.
     */
    private CodeGeneratorVisualPanel1 component;

    public String getOutputDirectory() {
        if (component != null) {
            return component.getOutputDirectory();
        }

        return outputDirectory;
    }

    public void setOutputDirectory(String value) {
        if (component != null) {
            component.setOutputDirectory(value);
        }

        outputDirectory = value;
    }

    public String getLibraryDirectory() {
        if (component != null) {
            return component.getLibraryDirectory();
        }

        return libraryDirectory;
    }

    public void setLibraryDirectory(String value) {
        if (component != null) {
            component.setLibraryDirectory(value);
        }

        libraryDirectory = value;
    }

    public String getTargetName() {
        if (component != null) {
            return component.getTargetName();
        }

        return targetName;
    }

    public void setTargetName(String targetName) {
        if (component != null) {
            component.setTargetName(targetName);
        }

        this.targetName = targetName;
    }

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public CodeGeneratorVisualPanel1 getComponent() {
        if (component == null) {
            component = new CodeGeneratorVisualPanel1();
            if (targetName != null) {
                component.setOutputDirectory(outputDirectory);
                component.setLibraryDirectory(libraryDirectory);
                component.setTargetName(targetName);
            }
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
        // it is always OK to press Next or Finish
        return true;
    }

    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
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
