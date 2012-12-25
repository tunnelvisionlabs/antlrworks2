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
import org.openide.util.ChangeSupport;
import org.openide.util.HelpCtx;

public class CodeGeneratorWizardPanel3 implements WizardDescriptor.Panel<WizardDescriptor> {
    public static final String GENERATE_ATN_DIAGRAMS = "generateATNDiagrams";
    public static final String DEBUG_ST = "debugST";
    public static final String FORCE_ATN = "forceATN";
    public static final String TREAT_WARNINGS_AS_ERRORS = "treatWarningsAsErrors";

    private final ChangeSupport _changeSupport = new ChangeSupport(this);

    private boolean generateATNDiagrams;
    private boolean debugST;
    private boolean forceATN;
    private boolean treatWarningsAsErrors;

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use {@link #getComponent}.
     */
    private CodeGeneratorVisualPanel3 component;

    /*package*/ ChangeSupport getChangeSupport() {
        return _changeSupport;
    }

    public boolean isGenerateATNDiagrams() {
        if (component != null) {
            return component.isGenerateATNDiagrams();
        }

        return generateATNDiagrams;
    }

    public void setGenerateATNDiagrams(boolean value) {
        if (component != null) {
            component.setGenerateATNDiagrams(value);
        }

        generateATNDiagrams = value;
    }

    public boolean isDebugST() {
        if (component != null) {
            return component.isDebugST();
        }

        return debugST;
    }

    public void setDebugST(boolean value) {
        if (component != null) {
            component.setDebugST(value);
        }

        debugST = value;
    }

    public boolean isForceATN() {
        if (component != null) {
            return component.isForceATN();
        }

        return forceATN;
    }

    public void setForceATN(boolean value) {
        if (component != null) {
            component.setForceATN(value);
        }

        forceATN = value;
    }

    public boolean isTreatWarningsAsErrors() {
        if (component != null) {
            return component.isTreatWarningsAsErrors();
        }

        return treatWarningsAsErrors;
    }

    public void setTreatWarningsAsErrors(boolean value) {
        if (component != null) {
            component.setTreatWarningsAsErrors(value);
        }

        treatWarningsAsErrors = value;
    }

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public CodeGeneratorVisualPanel3 getComponent() {
        if (component == null) {
            component = new CodeGeneratorVisualPanel3(this);
            _changeSupport.fireChange();
        }

        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx("help.key.here");
    }

    @Override
    public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
        return true;
        // If it depends on some condition (form filled out...) and
        // this condition changes (last form field filled in...) then
        // use ChangeSupport to implement add/removeChangeListener below.
        // WizardDescriptor.ERROR/WARNING/INFORMATION_MESSAGE will also be useful.
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
        setGenerateATNDiagrams(CodeGeneratorWizardOptions.Advanced.isGenerateATNDiagrams(wiz));
        setDebugST(CodeGeneratorWizardOptions.Advanced.isDebugST(wiz));
        setForceATN(CodeGeneratorWizardOptions.Advanced.isForceATN(wiz));
        setTreatWarningsAsErrors(CodeGeneratorWizardOptions.Advanced.isTreatWarningsAsErrors(wiz));
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        CodeGeneratorWizardOptions.setBooleanProperty(wiz, GENERATE_ATN_DIAGRAMS, isGenerateATNDiagrams());
        CodeGeneratorWizardOptions.setBooleanProperty(wiz, DEBUG_ST, isDebugST());
        CodeGeneratorWizardOptions.setBooleanProperty(wiz, FORCE_ATN, isForceATN());
        CodeGeneratorWizardOptions.setBooleanProperty(wiz, TREAT_WARNINGS_AS_ERRORS, isTreatWarningsAsErrors());
    }
}
