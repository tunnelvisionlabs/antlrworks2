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
import org.antlr.works.editor.grammar.codemodel.TokenVocabModel;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractTokenVocabModel extends AbstractCodeElementModel implements TokenVocabModel {

    public AbstractTokenVocabModel(String name, FileModelImpl file) {
        super(name, file);
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

}
