/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.grammar.codemodel.CodeElementPositionRegion;
import org.antlr.works.editor.grammar.codemodel.LabelModel;
import org.antlr.works.editor.grammar.codemodel.ParameterModel;
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public abstract class RuleModelImpl extends AbstractCodeElementModel implements RuleModel {
    private final FreezableArrayList<ParameterModelImpl> parameters = new FreezableArrayList<>();
    private final FreezableArrayList<ParameterModelImpl> returnValues = new FreezableArrayList<>();
    private final FreezableArrayList<ParameterModelImpl> locals = new FreezableArrayList<>();
    private final FreezableArrayList<LabelModelImpl> labels = new FreezableArrayList<>();
    @NonNull
    private final ProxyCollection<AbstractCodeElementModel> members = new ProxyCollection<AbstractCodeElementModel>(Arrays.asList(parameters, returnValues, locals, labels));

    private final OffsetRegion seek;
    private final OffsetRegion span;
    private boolean explicitEof;

    public RuleModelImpl(String name, FileModelImpl file, TerminalNode seek, ParserRuleContext span) {
        super(name, file);
        this.seek = getOffsetRegion(seek);
        this.span = getOffsetRegion(span);
    }

    @Override
    public boolean hasExplicitEof() {
        return explicitEof;
    }

    public void setExplicitEof(boolean value) {
        ensureModifiable();
        this.explicitEof = value;
    }

    @NonNull
    @Override
    public List<ParameterModelImpl> getParameters() {
        return parameters;
    }

    @Override
    public Collection<? extends ParameterModel> getParameters(String name) {
        return CodeModelCacheImpl.findElementsByName(getParameters(), name);
    }

    @NonNull
    @Override
    public List<ParameterModelImpl> getReturnValues() {
        return returnValues;
    }

    @Override
    public Collection<? extends ParameterModel> getReturnValues(String name) {
        return CodeModelCacheImpl.findElementsByName(getReturnValues(), name);
    }

    @NonNull
    @Override
    public List<ParameterModelImpl> getLocals() {
        return locals;
    }

    @Override
    public Collection<? extends ParameterModel> getLocals(String name) {
        return CodeModelCacheImpl.findElementsByName(getLocals(), name);
    }

    @NonNull
    @Override
    public List<LabelModelImpl> getLabels() {
        return labels;
    }

    @Override
    public Collection<? extends LabelModel> getLabels(String name) {
        return CodeModelCacheImpl.findElementsByName(getLabels(), name);
    }

    @NonNull
    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return members;
    }

    @Override
    public CodeElementPositionRegion getSeek() {
        if (this.seek == null) {
            return super.getSeek();
        }

        return new CodeElementPositionRegionImpl(this, seek);
    }

    @Override
    public CodeElementPositionRegion getSpan() {
        if (this.span == null) {
            return super.getSpan();
        }

        return new CodeElementPositionRegionImpl(this, span);
    }

    @Override
    protected void freezeImpl() {
        parameters.freeze();
        returnValues.freeze();
        locals.freeze();
        labels.freeze();
        super.freezeImpl();
    }
}
