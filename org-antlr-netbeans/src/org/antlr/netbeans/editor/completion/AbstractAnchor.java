/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.completion;

import java.util.List;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 * This class provides a default abstract implementation of the {@link Anchor}
 * interface. The {@link #getRuleNames} method is added to provide a
 * human-readable default implementation of {@link #toString}.
 *
 * @author Sam Harwell
 */
public abstract class AbstractAnchor implements Anchor {

    private final TrackingPositionRegion span;
    private final int rule;

    /**
     *
     * @param span The span of the anchor in the source file.
     * @param rule The grammar rule associated with this anchor.
     */
    protected AbstractAnchor(@NonNull TrackingPositionRegion span, int rule) {
        Parameters.notNull("span", span);
        this.span = span;
        this.rule = rule;
    }

    @Override
    public TrackingPositionRegion getSpan() {
        return span;
    }

    @Override
    public int getRule() {
        return rule;
    }

    @Override
    public String toString() {
        String ruleName;
        List<String> ruleNames = getRuleNames();
        if (rule >= 0 && rule <= ruleNames.size()) {
            ruleName = ruleNames.get(rule);
        } else {
            ruleName = "?";
        }

        return ruleName + ": " + span.toString();
    }

    /**
     * Gets a mapping from index to display name of the rules in a grammar.
     *
     * @return A list of names of rules in the grammar.
     */
    @NonNull
    protected abstract List<String> getRuleNames();
}
