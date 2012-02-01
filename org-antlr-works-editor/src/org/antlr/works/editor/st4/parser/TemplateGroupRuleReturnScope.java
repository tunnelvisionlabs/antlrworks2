/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.parser;

import org.antlr.runtime.RuleReturnScope;
import org.antlr.runtime.tree.CommonTree;

/**
 *
 * @author Sam Harwell
 */
public class TemplateGroupRuleReturnScope extends RuleReturnScope {
    private final TemplateGroupWrapper group;
    private final CommonTree tree;

    public TemplateGroupRuleReturnScope(TemplateGroupWrapper group, CommonTree tree) {
        this.group = group;
        this.tree = tree;
    }

    public TemplateGroupWrapper getGroup() {
        return group;
    }

    @Override
    public CommonTree getTree() {
        return tree;
    }

    @Override
    public Object getStart() {
        return null;
    }

    @Override
    public Object getStop() {
        return null;
    }

}
