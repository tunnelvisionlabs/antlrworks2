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
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.antlr.works.editor.grammar.codemodel.TokenData;

/**
 *
 * @author Sam Harwell
 */
public class TokenDataImpl implements TokenData {
    private final String name;
    private final String literal;
    private final FileModelImpl file;

    public TokenDataImpl(String name, String literal, FileModelImpl file) {
        this.name = name;
        this.literal = literal;
        this.file = file;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    @Override
    public Collection<? extends RuleModel> resolve() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
