/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel;

import java.util.Collection;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class FileModel {
    @NonNull
    private final DocumentSnapshot snapshot;
    @NonNull
    private final Collection<RuleModel> rules;

    public FileModel(@NonNull DocumentSnapshot snapshot, @NonNull Collection<RuleModel> rules) {
        this.snapshot = snapshot;
        this.rules = rules;
    }

    @NonNull
    public Collection<RuleModel> getRules() {
        return rules;
    }

    public RuleModel getRule(String name) {
        for (RuleModel rule : rules) {
            if (rule.getName().equals(name)) {
                return rule;
            }
        }

        return null;
    }

}
