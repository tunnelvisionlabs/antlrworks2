/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel;

import java.util.Collection;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;

/**
 *
 * @author Sam Harwell
 */
public class RuleModel {
    private final SnapshotPositionRegion ruleSpan;
    private final SnapshotPositionRegion nameSpan;
    private final String name;
    private final Collection<ParameterModel> parameters;
    private final Collection<ParameterModel> returnValues;
    private final Collection<ParameterModel> locals;
    private final Collection<LabelModel> labels;

    public RuleModel(SnapshotPositionRegion ruleSpan,
                     SnapshotPositionRegion nameSpan,
                     String name,
                     Collection<ParameterModel> parameters,
                     Collection<ParameterModel> returnValues,
                     Collection<ParameterModel> locals,
                     Collection<LabelModel> labels) {
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

    public Collection<ParameterModel> getParameters() {
        return parameters;
    }

    public Collection<ParameterModel> getReturnValues() {
        return returnValues;
    }

    public Collection<ParameterModel> getLocals() {
        return locals;
    }

    public Collection<LabelModel> getLabels() {
        return labels;
    }

    public ParameterModel getParameter(String name) {
        return findParameterByName(parameters, name);
    }

    public ParameterModel getReturnValue(String name) {
        return findParameterByName(returnValues, name);
    }

    public ParameterModel getLocal(String name) {
        return findParameterByName(locals, name);
    }

    public LabelModel getLabel(String name) {
        return findLabelByName(labels, name);
    }

    private static ParameterModel findParameterByName(Collection<ParameterModel> parameters, String name) {
        for (ParameterModel parameter : parameters) {
            if (parameter.getName().equals(name)) {
                return parameter;
            }
        }
        
        return null;
    }

    private static LabelModel findLabelByName(Collection<LabelModel> labels, String name) {
        for (LabelModel label : labels) {
            if (label.getName().equals(name)) {
                return label;
            }
        }

        return null;
    }
}
