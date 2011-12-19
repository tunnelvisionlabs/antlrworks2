/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
