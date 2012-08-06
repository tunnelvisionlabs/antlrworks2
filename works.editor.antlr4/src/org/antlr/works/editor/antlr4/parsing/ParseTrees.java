/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.parsing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public final class ParseTrees {

    public static <T extends Token> Interval getSourceInterval(@NonNull ParserRuleContext<? extends T> context) {
        Parameters.notNull("context", context);
        int startIndex = context.start.getStartIndex();
        Token stopSymbol = getStopSymbol(context);
        if (stopSymbol == null) {
            return new Interval(startIndex, startIndex - 1);
        }

        int stopIndex;
        if (stopSymbol.getType() != Token.EOF) {
            stopIndex = stopSymbol.getStopIndex();
        } else {
            TokenSource<?> tokenSource = context.getStart().getTokenSource();
            CharStream inputStream = tokenSource != null ? tokenSource.getInputStream() : null;
            if (inputStream != null) {
                stopIndex = inputStream.size() - 1;
            } else {
                stopIndex = context.start.getStartIndex() - 1;
            }
        }

        stopIndex = Math.max(stopIndex, startIndex - 1);
        return new Interval(startIndex, stopIndex);
    }

    public static <T extends Token> T getStopSymbol(@NonNull ParserRuleContext<T> context) {
        Parameters.notNull("context", context);
        if (context.stop != null) {
            return context.stop;
        }

        for (int i = context.getChildCount() - 1; i >= 0; i--) {
            @SuppressWarnings("unchecked")
            T symbol = getStopSymbol(context.getChild(i));
            if (symbol != null) {
                return symbol;
            }
        }

        return context.start;
    }

    public static <Symbol extends Token> Symbol getStopSymbol(@NonNull ParseTree<Symbol> context) {
        Parameters.notNull("context", context);

        if (context instanceof ParserRuleContext<?>) {
            return getStopSymbol((ParserRuleContext<Symbol>)context);
        } else if (context instanceof TerminalNode<?>) {
            return ((TerminalNode<Symbol>)context).getSymbol();
        }

        return null;
    }

    public static <Symbol> TerminalNode<Symbol> getStartNode(ParseTree<Symbol> context) {
        if (context == null) {
            return null;
        }

        if (context instanceof TerminalNode<?>) {
            return (TerminalNode<Symbol>)context;
        }

        for (int i = 0; i < context.getChildCount(); i++) {
            TerminalNode<Symbol> startNode = getStartNode(context.getChild(i));
            if (startNode != null) {
                return startNode;
            }
        }

        return null;
    }

    public static <Symbol> TerminalNode<Symbol> getStopNode(ParseTree<Symbol> context) {
        if (context == null) {
            return null;
        }

        if (context instanceof TerminalNode<?>) {
            return (TerminalNode<Symbol>)context;
        }

        for (int i = context.getChildCount() - 1; i >= 0; i--) {
            TerminalNode<Symbol> stopNode = getStopNode(context.getChild(i));
            if (stopNode != null) {
                return stopNode;
            }
        }

        return null;
    }

    public static boolean isInContexts(@NonNull ParserRuleContext<?> context, boolean allowGaps, @NonNull int... stack) {
        Parameters.notNull("context", context);
        Parameters.notNull("stack", stack);

        if (allowGaps) {
            throw new UnsupportedOperationException("Not implemented yet.");
        }

        ParserRuleContext<?> currentContext = context;
        for (int element : stack) {
            if (currentContext.getRuleIndex() != element) {
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

    public static <T extends Token> boolean isInAnyContext(Parser<T> parser, RuleContext<T> context, IntervalSet values) {
        return isInAnyContext(parser, context, values, true);
    }

    public static <T extends Token> boolean isInAnyContext(Parser<T> parser, RuleContext<T> context, IntervalSet values, boolean checkTop) {
        return findTopContext(parser, context, values, checkTop) != null;
    }

    public static boolean isInAnyContext(ParserRuleContext<?> context, IntervalSet values) {
        return isInAnyContext(context, values, true);
    }

    public static boolean isInAnyContext(ParserRuleContext<?> context, IntervalSet values, boolean checkTop) {
        return findTopContext(context, values, checkTop) != null;
    }

    public static <T extends Token> RuleContext<T> findTopContext(Parser<T> parser, RuleContext<T> context, IntervalSet values) {
        return findTopContext(parser, context, values, true);
    }

    public static <T extends Token> RuleContext<T> findTopContext(Parser<T> parser, RuleContext<T> context, IntervalSet values, boolean checkTop) {
        if (checkTop && values.contains(context.getRuleIndex())) {
            return context;
        }

        if (context.isEmpty()) {
            return null;
        }

        if (values.contains(parser.getATN().states.get(context.invokingState).ruleIndex)) {
            return context.parent;
        }

        return findTopContext(parser, context.parent, values, false);
    }

    public static <T extends Token> ParserRuleContext<T> findTopContext(ParserRuleContext<T> context, IntervalSet values) {
        return findTopContext(context, values, true);
    }

    public static <T extends Token> ParserRuleContext<T> findTopContext(ParserRuleContext<T> context, IntervalSet values, boolean checkTop) {
        if (checkTop && values.contains(context.getRuleIndex())) {
            return context;
        }

        if (context.isEmpty()) {
            return null;
        }

        return findTopContext((ParserRuleContext<T>)context.parent, values, true);
    }

    public static <Symbol> TerminalNode<Symbol> findTerminalNode(Collection<? extends ParseTree<Symbol>> children, Symbol symbol) {
        for (ParseTree<Symbol> element : children) {
            if (!(element instanceof TerminalNode<?>)) {
                continue;
            }

            TerminalNode<Symbol> node = (TerminalNode<Symbol>)element;
            if (node.getSymbol() == symbol) {
                return node;
            }
        }

        return null;
    }

    public static int getInvokingRule(ATN atn, RuleContext<?> context) {
        int invokingState = context.invokingState;
        if (invokingState < 0 || invokingState >= atn.states.size()) {
            return -1;
        }

        return atn.states.get(invokingState).ruleIndex;
    }

    public static <T> List<T> emptyIfNull(@NullAllowed List<T> list) {
        if (list == null) {
            return Collections.emptyList();
        }

        return list;
    }

    /** Return a list of all ancestors of this node.  The first node of
     *  list is the root and the last is the parent of this node.
     */
    @NonNull
    public static <T> List<? extends ParseTree<T>> getAncestors(@NonNull ParseTree<T> t) {
        if ( t.getParent()==null ) {
            return Collections.emptyList();
        }

        List<ParseTree<T>> ancestors = new ArrayList<ParseTree<T>>();
        t = t.getParent();
        while ( t!=null ) {
            ancestors.add(0, t); // insert at start
            t = t.getParent();
        }

        return ancestors;
    }

    private ParseTrees() {
    }

}
