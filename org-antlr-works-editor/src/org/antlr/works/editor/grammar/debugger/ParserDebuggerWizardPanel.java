/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.ChangeSupport;
import org.openide.util.HelpCtx;

/**
 *
 * @author Sam Harwell
 */
public class ParserDebuggerWizardPanel implements WizardDescriptor.Panel<WizardDescriptor> {
    public static final String INPUT_FILE = "inputFile";
    public static final String START_RULE = "startRule";
    public static final String ENCODING_SPECIFIED = "encodingSpecified";
    public static final String ENCODING = "encoding";
    public static final String AVAILABLE_RULES = "availableRules";

    private final ChangeSupport _changeSupport = new ChangeSupport(this);

    private final List<String> _availableRules = new ArrayList<>();

    private String _inputFile;
    private String _startRule;
    private boolean _encodingSpecified;
    private String _encoding;

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, use {@link #getComponent}.
     */
    private ParserDebuggerVisualPanel component;

    /*package*/ ChangeSupport getChangeSupport() {
        return _changeSupport;
    }

    public String getInputFile() {
        if (component != null) {
            return component.getInputFile();
        }

        return _inputFile;
    }

    public void setInputFile(String value) {
        if (component != null) {
            component.setInputFile(value);
        }

        _inputFile = value;
    }

    public List<String> getAvailableRules() {
        return _availableRules;
    }
    
    public String getStartRule() {
        if (component != null) {
            return component.getStartRule();
        }

        return _startRule;
    }

    public void setStartRule(String ruleName) {
        if (component != null) {
            component.setAvailableRules(_availableRules, ruleName);
        }

        _startRule = ruleName;
    }

    public boolean isEncodingSpecified() {
        if (component != null) {
            return component.isEncodingSpecified();
        }

        return _encodingSpecified;
    }

    public void setEncodingSpecified(boolean value) {
        if (component != null) {
            component.setEncodingSpecified(value);
        }
        
        _encodingSpecified = value;
    }

    public String getEncoding() {
        if (component != null) {
            return component.getEncoding();
        }

        return _encoding;
    }

    public void setEncoding(String value) {
        if (component != null) {
            component.setEncoding(value);
        }

        _encoding = value;
    }

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public ParserDebuggerVisualPanel getComponent() {
        if (component == null) {
            component = new ParserDebuggerVisualPanel(this);
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
        String inputFile = getInputFile();
        if (inputFile == null || inputFile.isEmpty()) {
            return false;
        }

        if (!new File(inputFile).isFile()) {
            return false;
        }

        if (getStartRule() == null || getStartRule().isEmpty()) {
            return false;
        }

        if (isEncodingSpecified()) {
            if (getEncoding() == null || getEncoding().isEmpty()) {
                return false;
            }

            try {
                if (!Charset.isSupported(getEncoding())) {
                    return false;
                }
            } catch (IllegalCharsetNameException ex) {
                return false;
            } catch (IllegalArgumentException ex) {
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
        String[] availableRules = (String[])wiz.getProperty(AVAILABLE_RULES);
        if (availableRules != null) {
            _availableRules.clear();
            _availableRules.addAll(Arrays.asList(availableRules));
        }

        setInputFile(ParserDebuggerWizardOptions.getProperty(wiz, INPUT_FILE, ""));
        setStartRule(ParserDebuggerWizardOptions.getProperty(wiz, START_RULE, ""));
        setEncodingSpecified(ParserDebuggerWizardOptions.getBooleanProperty(wiz, ENCODING_SPECIFIED, false));
        setEncoding(ParserDebuggerWizardOptions.getProperty(wiz, ENCODING, Charset.defaultCharset().name()));
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        ParserDebuggerWizardOptions.setProperty(wiz, INPUT_FILE, getInputFile());
        ParserDebuggerWizardOptions.setProperty(wiz, START_RULE, getStartRule());
        ParserDebuggerWizardOptions.setBooleanProperty(wiz, ENCODING_SPECIFIED, isEncodingSpecified());
        ParserDebuggerWizardOptions.setProperty(wiz, ENCODING, getEncoding());
    }
}
