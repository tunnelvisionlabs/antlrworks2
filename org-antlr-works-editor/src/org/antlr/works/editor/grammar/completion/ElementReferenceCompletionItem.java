/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

/**
 *
 * @author Sam Harwell
 */
public abstract class ElementReferenceCompletionItem extends GrammarCompletionItem {

    private final String label;
    private final boolean explicit;
    private final String referenceKind;

    private String leftText;

    public ElementReferenceCompletionItem(String label, boolean explicit) {
        this(label, explicit, null);
    }

    public ElementReferenceCompletionItem(String label, boolean explicit, String referenceKind) {
        this.label = label;
        this.explicit = explicit;
        this.referenceKind = referenceKind;
    }

    @Override
    public int getSortPriority() {
        return ELEMENT_REFERENCE_SORT_PRIORITY;
    }

    @Override
    public String getSortTextImpl() {
        return getPrefix() + label;
    }

    @Override
    public CharSequence getInsertPrefix() {
        return getPrefix() + label;
    }

    public boolean isExplicit() {
        return explicit;
    }

    protected abstract String getPrefix();

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();

            builder.append(REFERENCE_COLOR);
            builder.append(BOLD);

            builder.append(getPrefix()).append(label);

            builder.append(BOLD_END);
            builder.append(COLOR_END);

            leftText = builder.toString();
        }

        return leftText;
    }

    @Override
    protected String getRightHtmlText() {
        if (referenceKind != null) {
            return referenceKind;
        } else if (explicit) {
            return "Label";
        } else {
            return "Element";
        }
    }
}
