/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class RewriteReferenceCompletionItem extends ElementReferenceCompletionItem {

    public RewriteReferenceCompletionItem(@NonNull String element, boolean explicit) {
        super(element, explicit);
    }

    @Override
    protected String getPrefix() {
        return isExplicit() ? "$" : "";
    }

    @Override
    protected String getRightHtmlText() {
        return "Element";
    }
}
