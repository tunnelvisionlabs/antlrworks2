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
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.grammar.codemodel.CodeElementPositionRegion;
import org.antlr.works.editor.grammar.codemodel.ModeModel;
import org.antlr.works.editor.grammar.codemodel.RuleModel;

/**
 *
 * @author Sam Harwell
 */
public class ModeModelImpl extends AbstractCodeElementModel implements ModeModel {
    private final FreezableArrayList<RuleModelImpl> rules = new FreezableArrayList<>();

    private final OffsetRegion seek;
    private final OffsetRegion span;

    public ModeModelImpl(String name, FileModelImpl file, TerminalNode seek, ParserRuleContext span) {
        super(name, file);
        this.seek = getOffsetRegion(seek);
        this.span = getOffsetRegion(span);
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
    public CodeElementPositionRegion getSeek() {
        if (this.seek == null) {
            return super.getSeek();
        }

        return new CodeElementPositionRegionImpl(this, seek);
    }

    @Override
    public CodeElementPositionRegion getSpan() {
        if (this.span == null) {
            return super.getSpan();
        }

        return new CodeElementPositionRegionImpl(this, span);
    }

    @Override
    protected void freezeImpl() {
        rules.freeze();
        super.freezeImpl();
    }

}
