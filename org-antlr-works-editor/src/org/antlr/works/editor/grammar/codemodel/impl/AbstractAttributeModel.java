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
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.grammar.codemodel.AttributeModel;
import org.antlr.works.editor.grammar.codemodel.CodeElementPositionRegion;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractAttributeModel extends AbstractCodeElementModel implements AttributeModel {
    private final String type;

    private final OffsetRegion seek;
    private final OffsetRegion span;

    protected AbstractAttributeModel(String name, String type, FileModelImpl file, Collection<? extends TerminalNode> definitions, Collection<? extends TerminalNode> uses) {
        super(name, file);
        this.type = type;

        if (definitions != null && !definitions.isEmpty()) {
            TerminalNode firstDefinition = definitions.iterator().next();
            this.seek = getOffsetRegion(firstDefinition);
            this.span = this.seek;
        } else {
            this.seek = null;
            this.span = null;
        }
    }

    protected AbstractAttributeModel(String name, String type, FileModelImpl file, TerminalNode seek, ParserRuleContext span) {
        super(name, file);
        this.type = type;
        this.seek = getOffsetRegion(seek);
        this.span = getOffsetRegion(span);
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
}
