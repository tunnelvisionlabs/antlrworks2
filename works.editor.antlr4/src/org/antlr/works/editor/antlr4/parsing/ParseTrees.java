/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.parsing;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTree.TerminalNode;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public final class ParseTrees {

    public static boolean isInContexts(@NonNull ParserRuleContext<?> context, boolean allowGaps, @NonNull int... stack) {
        Parameters.notNull("context", context);
        Parameters.notNull("stack", stack);

        if (allowGaps) {
            throw new UnsupportedOperationException("Not implemented yet.");
        }

        ParserRuleContext<?> currentContext = context;
        for (int element : stack) {
            if (currentContext.ruleIndex != element) {
                return false;
            }

            assert context.parent == null || context.parent instanceof ParserRuleContext<?>;
            if (!(context.parent instanceof ParserRuleContext<?>)) {
                return false;
            }

            currentContext = (ParserRuleContext<?>)currentContext.parent;
        }

        return true;
    }

    public static boolean isInAnyContext(Parser parser, RuleContext context, IntervalSet values) {
        return isInAnyContext(parser, context, values, true);
    }

    public static boolean isInAnyContext(Parser parser, RuleContext context, IntervalSet values, boolean checkTop) {
        return findTopContext(parser, context, values, checkTop) != null;
    }

    public static boolean isInAnyContext(ParserRuleContext<?> context, IntervalSet values) {
        return isInAnyContext(context, values, true);
    }

    public static boolean isInAnyContext(ParserRuleContext<?> context, IntervalSet values, boolean checkTop) {
        return findTopContext(context, values, checkTop) != null;
    }

    public static RuleContext findTopContext(Parser parser, RuleContext context, IntervalSet values) {
        return findTopContext(parser, context, values, true);
    }

    public static RuleContext findTopContext(Parser parser, RuleContext context, IntervalSet values, boolean checkTop) {
        if (checkTop && context instanceof ParserRuleContext<?>) {
            if (values.contains(((ParserRuleContext<?>)context).ruleIndex)) {
                return context;
            }
        }

        if (context.isEmpty()) {
            return null;
        }

        if (values.contains(parser.getATN().states.get(context.invokingState).ruleIndex)) {
            return context.parent;
        }

        return findTopContext(parser, context.parent, values, false);
    }

    public static ParserRuleContext<?> findTopContext(ParserRuleContext<?> context, IntervalSet values) {
        return findTopContext(context, values, true);
    }

    public static ParserRuleContext<?> findTopContext(ParserRuleContext<?> context, IntervalSet values, boolean checkTop) {
        if (checkTop && values.contains(context.ruleIndex)) {
            return context;
        }

        if (context.isEmpty()) {
            return null;
        }

        return findTopContext((ParserRuleContext<?>)context.parent, values, true);
    }

    public static TerminalNode<?> findTerminalNode(Collection<? extends ParseTree> children, Object symbol) {
        for (Object element : children) {
            if (!(element instanceof TerminalNode<?>)) {
                continue;
            }

            TerminalNode<?> node = (TerminalNode<?>)element;
            if (node.getSymbol() == symbol) {
                return node;
            }
        }

        return null;
    }

    public static <T> List<? extends T> emptyIfNull(@NullAllowed List<? extends T> list) {
        if (list == null) {
            return Collections.emptyList();
        }

        return list;
    }

    private ParseTrees() {
    }

}
