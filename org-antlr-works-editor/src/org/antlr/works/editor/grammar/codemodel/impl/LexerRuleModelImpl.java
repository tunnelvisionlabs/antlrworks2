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
import org.antlr.works.editor.grammar.codemodel.LexerRuleModel;
import org.antlr.works.editor.grammar.codemodel.ModeModel;
import org.antlr.works.editor.grammar.codemodel.RuleKind;
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.antlr.works.editor.grammar.codemodel.TokenData;

/**
 *
 * @author Sam Harwell
 */
public class LexerRuleModelImpl extends RuleModelImpl implements LexerRuleModel {
    private final ModeModelImpl mode;

    public LexerRuleModelImpl(String name, ModeModelImpl mode, FileModelImpl file) {
        super(name, file);
        this.mode = mode;
    }

    @Override
    public RuleKind getRuleKind() {
        return RuleKind.LEXER;
    }

    @Override
    public ModeModel getMode() {
        return mode;
    }

    @Override
    public TokenData getTokenData() {
        return new TokenData() {

            @Override
            public String getName() {
                return LexerRuleModelImpl.this.getName();
            }

            @Override
            public String getLiteral() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Collection<? extends RuleModel> resolve() {
                return Collections.singletonList(LexerRuleModelImpl.this);
            }

        };
    }

}
