/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import org.antlr.works.editor.grammar.codemodel.AttributeModel;

/**
 *
 * @author Sam Harwell
 */
public class MemberCompletionItem extends GrammarCompletionItem {
    private final AttributeModel member;

    private String leftText;
    private String rightText;

    public MemberCompletionItem(AttributeModel member) {
        this.member = member;
    }

    @Override
    protected String getSortTextImpl() {
        return member.getName();
    }

    @Override
    public int getSortPriority() {
        return MEMBER_SORT_PRIORITY;
    }

    @Override
    public CharSequence getInsertPrefix() {
        return member.getName();
    }

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(PARAMETER_NAME_COLOR);
            builder.append(member.getName());
            builder.append(COLOR_END);
            leftText = builder.toString();
        }

        return leftText;
    }

    @Override
    protected String getRightHtmlText() {
        if (rightText == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(member.getType().replace("<", "&lt;").replace(">", "&gt;"));
            rightText = builder.toString();
        }

        return rightText;
    }
}
