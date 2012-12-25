/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codegen;

import org.openide.WizardDescriptor;
import org.openide.util.NbPreferences;

/**
 *
 * @author Sam Harwell
 */
public abstract class CodeGeneratorWizardOptions {
    public static String getProperty(WizardDescriptor wiz, String name, String defaultValue) {
        String result = (String)wiz.getProperty(name);
        if (result != null) {
            return result;
        }

        return NbPreferences.forModule(CodeGeneratorWizardOptions.class).get(name, defaultValue);
    }

    public static boolean getBooleanProperty(WizardDescriptor wiz, String name, boolean defaultValue) {
        String result = (String)wiz.getProperty(name);
        if (result != null) {
            return Boolean.toString(true).equals(result);
        }

        return NbPreferences.forModule(CodeGeneratorWizardOptions.class).getBoolean(name, defaultValue);
    }

    public static void setProperty(WizardDescriptor wiz, String name, String value) {
        wiz.putProperty(name, value);
        NbPreferences.forModule(CodeGeneratorWizardOptions.class).put(name, value);
    }

    public static void setBooleanProperty(WizardDescriptor wiz, String name, boolean value) {
        wiz.putProperty(name, Boolean.toString(value));
        NbPreferences.forModule(CodeGeneratorWizardOptions.class).putBoolean(name, value);
    }

    public static abstract class Location {
        public static String getOutputDirectory(WizardDescriptor wiz) {
            return getProperty(wiz, CodeGeneratorWizardPanel1.OUTPUT_DIRECTORY, "");
        }

        public static String getLibraryDirectory(WizardDescriptor wiz) {
            return getProperty(wiz, CodeGeneratorWizardPanel1.LIBRARY_DIRECTORY, "");
        }

        public static String getSelectedTarget(WizardDescriptor wiz) {
            return getProperty(wiz, CodeGeneratorWizardPanel1.SELECTED_TARGET, "Java");
        }

        private Location() {
        }
    }

    public static abstract class Features {
        public static boolean isGenerateListener(WizardDescriptor wiz) {
            return getBooleanProperty(wiz, CodeGeneratorWizardPanel2.GENERATE_LISTENER, true);
        }

        public static boolean isGenerateVisitor(WizardDescriptor wiz) {
            return getBooleanProperty(wiz, CodeGeneratorWizardPanel2.GENERATE_VISITOR, false);
        }

        public static boolean isUsePackageName(WizardDescriptor wiz) {
            return getBooleanProperty(wiz, CodeGeneratorWizardPanel2.USE_PACKAGE_NAME, false);
        }

        public static String getPackageName(WizardDescriptor wiz) {
            return getProperty(wiz, CodeGeneratorWizardPanel2.PACKAGE_NAME, "");
        }

        public static boolean isAbstractClasses(WizardDescriptor wiz) {
            return getBooleanProperty(wiz, CodeGeneratorWizardPanel2.ABSTRACT_CLASSES, false);
        }

        private Features() {
        }
    }

    public static abstract class Advanced {

        public static boolean isGenerateATNDiagrams(WizardDescriptor wiz) {
            return getBooleanProperty(wiz, CodeGeneratorWizardPanel3.GENERATE_ATN_DIAGRAMS, false);
        }

        public static boolean isDebugST(WizardDescriptor wiz) {
            return getBooleanProperty(wiz, CodeGeneratorWizardPanel3.DEBUG_ST, false);
        }

        public static boolean isForceATN(WizardDescriptor wiz) {
            return getBooleanProperty(wiz, CodeGeneratorWizardPanel3.FORCE_ATN, false);
        }

        public static boolean isTreatWarningsAsErrors(WizardDescriptor wiz) {
            return getBooleanProperty(wiz, CodeGeneratorWizardPanel3.TREAT_WARNINGS_AS_ERRORS, false);
        }

        private Advanced() {
        }
    }

    private CodeGeneratorWizardOptions() {
    }
}
