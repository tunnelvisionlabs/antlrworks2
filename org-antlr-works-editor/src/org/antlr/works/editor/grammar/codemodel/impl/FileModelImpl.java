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
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class FileModelImpl {
    @NonNull
    private final DocumentSnapshot snapshot;
    @NonNull
    private final Collection<RuleModelImpl> rules;

    public FileModelImpl(@NonNull DocumentSnapshot snapshot, @NonNull Collection<RuleModelImpl> rules) {
        this.snapshot = snapshot;
        this.rules = rules;
    }

    @NonNull
    public Collection<RuleModelImpl> getRules() {
        return rules;
    }

    public RuleModelImpl getRule(String name) {
        for (RuleModelImpl rule : rules) {
            if (rule.getName().equals(name)) {
                return rule;
            }
        }

        return null;
    }

}
