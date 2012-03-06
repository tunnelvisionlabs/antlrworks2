/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel.impl;

import org.antlr.works.editor.grammar.codemodel.RuleKind;

/**
 *
 * @author Sam Harwell
 */
public class TokenRuleModelImpl extends LexerRuleModelImpl {
    private final String literal;

    public TokenRuleModelImpl(String name, String literal, FileModelImpl file) {
        super(name, file);
        this.literal = literal;
    }

    @Override
    public RuleKind getRuleKind() {
        return RuleKind.TOKEN;
    }

    @Override
    public String getLiteral() {
        return literal;
    }

}
