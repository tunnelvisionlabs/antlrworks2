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
import org.antlr.works.editor.grammar.codemodel.ImportDeclarationModel;

/**
 *
 * @author Sam Harwell
 */
public class ImportDeclarationModelImpl extends AbstractCodeElementModel implements ImportDeclarationModel {
    private final String target;

    public ImportDeclarationModelImpl(String name, String target, FileModelImpl file) {
        super(name, file);
        this.target = target;
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

    @Override
    public String getPath() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
