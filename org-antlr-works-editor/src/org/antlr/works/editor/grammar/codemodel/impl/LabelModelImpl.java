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
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.grammar.codemodel.LabelKind;
import org.antlr.works.editor.grammar.codemodel.LabelModel;
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class LabelModelImpl extends AbstractAttributeModel implements LabelModel {
    private LabelKind labelKind = LabelKind.UNDEFINED;

    public LabelModelImpl(String name, FileModelImpl file, Collection<? extends TerminalNode> definitions, Collection<? extends TerminalNode> uses) {
        super(name, "Label", file, definitions, uses);
    }

    @NonNull
    @Override
    public LabelKind getLabelKind() {
        return labelKind;
    }

    public void setLabelKind(LabelKind labelKind) {
        Parameters.notNull("labelKind", labelKind);
        ensureModifiable();
        this.labelKind = labelKind;
    }

    @Override
    public Collection<? extends RuleModel> getReferencedRules() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
