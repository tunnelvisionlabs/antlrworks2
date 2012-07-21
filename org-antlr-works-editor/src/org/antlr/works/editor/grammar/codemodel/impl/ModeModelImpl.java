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
import org.antlr.works.editor.grammar.codemodel.ModeModel;
import org.antlr.works.editor.grammar.codemodel.RuleModel;

/**
 *
 * @author Sam Harwell
 */
public class ModeModelImpl extends AbstractCodeElementModel implements ModeModel {
    private final FreezableArrayList<RuleModelImpl> rules = new FreezableArrayList<RuleModelImpl>();

    public ModeModelImpl(String name, FileModelImpl file) {
        super(name, file);
    }

    @Override
    public Collection<RuleModelImpl> getRules() {
        return rules;
    }

    @Override
    public Collection<? extends RuleModel> getRules(String name) {
        return CodeModelCacheImpl.findElementsByName(getRules(), name);
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return rules;
    }

    @Override
    protected void freezeImpl() {
        rules.freeze();
        super.freezeImpl();
    }

}
