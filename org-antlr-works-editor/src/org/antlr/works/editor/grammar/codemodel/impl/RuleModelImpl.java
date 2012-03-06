/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel.impl;

import java.util.Collection;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;

/**
 *
 * @author Sam Harwell
 */
public class RuleModelImpl {
    private final SnapshotPositionRegion ruleSpan;
    private final SnapshotPositionRegion nameSpan;
    private final String name;
    private final Collection<ParameterModelImpl> parameters;
    private final Collection<ParameterModelImpl> returnValues;
    private final Collection<ParameterModelImpl> locals;
    private final Collection<LabelModelImpl> labels;

    public RuleModelImpl(SnapshotPositionRegion ruleSpan,
                     SnapshotPositionRegion nameSpan,
                     String name,
                     Collection<ParameterModelImpl> parameters,
                     Collection<ParameterModelImpl> returnValues,
                     Collection<ParameterModelImpl> locals,
                     Collection<LabelModelImpl> labels) {
        this.ruleSpan = ruleSpan;
        this.nameSpan = nameSpan;
        this.name = name;
        this.parameters = parameters;
        this.returnValues = returnValues;
        this.locals = locals;
        this.labels = labels;
    }

    public String getName() {
        return name;
    }

    public Collection<ParameterModelImpl> getParameters() {
        return parameters;
    }

    public Collection<ParameterModelImpl> getReturnValues() {
        return returnValues;
    }

    public Collection<ParameterModelImpl> getLocals() {
        return locals;
    }

    public Collection<LabelModelImpl> getLabels() {
        return labels;
    }

    public ParameterModelImpl getParameter(String name) {
        return findParameterByName(parameters, name);
    }

    public ParameterModelImpl getReturnValue(String name) {
        return findParameterByName(returnValues, name);
    }

    public ParameterModelImpl getLocal(String name) {
        return findParameterByName(locals, name);
    }

    public LabelModelImpl getLabel(String name) {
        return findLabelByName(labels, name);
    }

    private static ParameterModelImpl findParameterByName(Collection<ParameterModelImpl> parameters, String name) {
        for (ParameterModelImpl parameter : parameters) {
            if (parameter.getName().equals(name)) {
                return parameter;
            }
        }
        
        return null;
    }

    private static LabelModelImpl findLabelByName(Collection<LabelModelImpl> labels, String name) {
        for (LabelModelImpl label : labels) {
            if (label.getName().equals(name)) {
                return label;
            }
        }

        return null;
    }
}
