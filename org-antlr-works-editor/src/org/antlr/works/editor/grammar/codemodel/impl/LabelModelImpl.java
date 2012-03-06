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
import org.antlr.works.editor.grammar.codemodel.AttributeModel;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class LabelModelImpl implements AttributeModel {
    @NonNull
    private final String name;
    @NonNull
    private final Collection<SnapshotPositionRegion> uses;

    public LabelModelImpl(@NonNull String name, @NonNull Collection<SnapshotPositionRegion> uses) {
        this.name = name;
        this.uses = uses;
    }

    @NonNull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Label";
    }

    @NonNull
    public Collection<SnapshotPositionRegion> getUses() {
        return uses;
    }
}
