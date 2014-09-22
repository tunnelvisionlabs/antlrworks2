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
public class ActionReferenceCompletionItem extends ElementReferenceCompletionItem {

    public ActionReferenceCompletionItem(@NonNull String label, boolean explicit) {
        super(label, explicit);
    }

    public ActionReferenceCompletionItem(@NonNull String label, boolean explicit, String referenceKind) {
        super(label, explicit, referenceKind);
    }

    @Override
    protected String getPrefix() {
        return "$";
    }
}
