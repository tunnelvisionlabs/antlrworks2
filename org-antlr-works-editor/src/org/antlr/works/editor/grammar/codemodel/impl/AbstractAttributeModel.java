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
import java.util.Collections;
import org.antlr.works.editor.grammar.codemodel.AttributeModel;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractAttributeModel extends AbstractCodeElementModel implements AttributeModel {
    private final String type;

    public AbstractAttributeModel(String name, String type, FileModelImpl file) {
        super(name, file);
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers(String name) {
        return Collections.emptyList();
    }
}
