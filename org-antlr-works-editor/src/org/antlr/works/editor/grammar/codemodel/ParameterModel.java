/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel;

import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;

/**
 *
 * @author Sam Harwell
 */
public class ParameterModel implements AttributeModel {
    @NullAllowed
    private final SnapshotPositionRegion nameSpan;
    @NullAllowed
    private final SnapshotPositionRegion typeSpan;
    @NonNull
    private final String name;
    @NonNull
    private final String type;

    public ParameterModel(@NullAllowed SnapshotPositionRegion nameSpan, @NullAllowed SnapshotPositionRegion typeSpan, @NonNull String name, @NonNull String type) {
        this.nameSpan = nameSpan;
        this.typeSpan = typeSpan;
        this.name = name;
        this.type = type;
    }

    @CheckForNull
    public SnapshotPositionRegion getNameSpan() {
        return nameSpan;
    }

    @CheckForNull
    public SnapshotPositionRegion getTypeSpan() {
        return typeSpan;
    }

    @NonNull
    @Override
    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String getType() {
        return type;
    }
}
