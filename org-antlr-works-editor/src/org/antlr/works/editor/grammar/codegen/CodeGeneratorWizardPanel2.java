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
import org.netbeans.api.annotations.common.NonNull;
import org.openide.WizardDescriptor;
import org.openide.util.ChangeSupport;
import org.openide.util.HelpCtx;

public class CodeGeneratorWizardPanel2 implements WizardDescriptor.Panel<WizardDescriptor> {
    public static final String GENERATE_LISTENER = "generateListener";
    public static final String GENERATE_VISITOR = "generateVisitor";
    public static final String USE_PACKAGE_NAME = "usePackageName";
    public static final String PACKAGE_NAME = "packageName";
    public static final String ABSTRACT_CLASSES = "abstractClasses";

    private final CodeGeneratorWizardPanel1 _locationPanel;
    private final ChangeSupport _changeSupport = new ChangeSupport(this);

    private boolean generateListener;
    private boolean generateVisitor;
    private boolean usePackageName;
    private String packageName;
    private boolean abstractClasses;

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use {@link #getComponent}.
     */
    private CodeGeneratorVisualPanel2 component;

    public CodeGeneratorWizardPanel2(@NonNull CodeGeneratorWizardPanel1 locationPanel) {
        this._locationPanel = locationPanel;
    }

    public CodeGeneratorWizardPanel1 getLocationPanel() {
        return _locationPanel;
    }

    /*package*/ ChangeSupport getChangeSupport() {
        return _changeSupport;
    }

    public boolean isGenerateListener() {
        if (component != null) {
            return component.isGenerateListener();
        }

        return generateListener;
    }

    public void setGenerateListener(boolean generateListener) {
        if (component != null) {
            component.setGenerateListener(generateListener);
        }

        this.generateListener = generateListener;
    }

    public boolean isGenerateVisitor() {
        if (component != null) {
            return component.isGenerateVisitor();
        }

        return generateVisitor;
    }

    public void setGenerateVisitor(boolean generateVisitor) {
        if (component != null) {
            component.setGenerateVisitor(generateVisitor);
        }

        this.generateVisitor = generateVisitor;
    }

    public boolean isUsePackageName() {
        if (component != null) {
            return component.isUsePackageName();
        }

        return usePackageName;
    }

    public String getPackageName() {
        if (component != null) {
            return component.getPackageName();
        }

        return packageName;
    }

    public void setPackageName(Boolean usePackageName, String packageName) {
        if (component != null) {
            component.setPackageName(usePackageName, packageName);
        }

        this.usePackageName = usePackageName;
        this.packageName = packageName;
    }

    public boolean isAbstractClasses() {
        if (component != null) {
            return component.isAbstractClasses();
        }

        return abstractClasses;
    }

    public void setAbstractClasses(boolean abstractClasses) {
        if (component != null) {
            component.setAbstractClasses(abstractClasses);
        }

        this.abstractClasses = abstractClasses;
    }

    public boolean isAbstractClassesAvailable() {
        if (_locationPanel.getTargetName() == null) {
            return false;
        }

        return _locationPanel.getTargetName().contains("sharwell/optimized");
    }

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public CodeGeneratorVisualPanel2 getComponent() {
        if (component == null) {
            component = new CodeGeneratorVisualPanel2(this);
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
        setGenerateListener(CodeGeneratorWizardOptions.Features.isGenerateListener(wiz));
        setGenerateVisitor(CodeGeneratorWizardOptions.Features.isGenerateVisitor(wiz));
        setPackageName(CodeGeneratorWizardOptions.Features.isUsePackageName(wiz),
            CodeGeneratorWizardOptions.Features.getPackageName(wiz));
        setAbstractClasses(CodeGeneratorWizardOptions.Features.isAbstractClasses(wiz));
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        CodeGeneratorWizardOptions.setBooleanProperty(wiz, GENERATE_LISTENER, isGenerateListener());
        CodeGeneratorWizardOptions.setBooleanProperty(wiz, GENERATE_VISITOR,  isGenerateVisitor());
        CodeGeneratorWizardOptions.setBooleanProperty(wiz, USE_PACKAGE_NAME,  isUsePackageName());
        CodeGeneratorWizardOptions.setProperty(wiz, PACKAGE_NAME, getPackageName());
        CodeGeneratorWizardOptions.setBooleanProperty(wiz, ABSTRACT_CLASSES, isAbstractClasses());
    }
}
