/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.nio.charset.Charset;
import org.openide.WizardDescriptor;
import org.openide.util.NbPreferences;

/**
 *
 * @author Sam Harwell
 */
public abstract class ParserDebuggerWizardOptions {
    public static String getProperty(WizardDescriptor wiz, String name, String defaultValue) {
        String result = (String)wiz.getProperty(name);
        if (result != null) {
            return result;
        }

       return NbPreferences.forModule(ParserDebuggerWizardOptions.class).get(name, defaultValue);
    }

    public static boolean getBooleanProperty(WizardDescriptor wiz, String name, boolean defaultValue) {
        String result = (String)wiz.getProperty(name);
        if (result != null) {
            return Boolean.toString(true).equals(result);
        }

        return NbPreferences.forModule(ParserDebuggerWizardOptions.class).getBoolean(name, defaultValue);
    }

    public static void setProperty(WizardDescriptor wiz, String name, String value) {
        wiz.putProperty(name, value);
        NbPreferences.forModule(ParserDebuggerWizardOptions.class).put(name, value);
    }

    public static void setBooleanProperty(WizardDescriptor wiz, String name, boolean value) {
        wiz.putProperty(name, Boolean.toString(value));
        NbPreferences.forModule(ParserDebuggerWizardOptions.class).putBoolean(name, value);
    }

    public static String getInputFile(WizardDescriptor wiz) {
        return getProperty(wiz, ParserDebuggerWizardPanel.INPUT_FILE, "");
    }

    public static String getStartRule(WizardDescriptor wiz) {
        return getProperty(wiz, ParserDebuggerWizardPanel.START_RULE, "");
    }

    public static boolean isEncodingSpecified(WizardDescriptor wiz) {
        return getBooleanProperty(wiz, ParserDebuggerWizardPanel.ENCODING_SPECIFIED, false);
    }

    public static String getEncoding(WizardDescriptor wiz) {
        return getProperty(wiz, ParserDebuggerWizardPanel.ENCODING, Charset.defaultCharset().name());
    }

    private ParserDebuggerWizardOptions() {
    }
}
