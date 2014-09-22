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
import java.util.BitSet;
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
import org.antlr.v4.runtime.misc.Utils;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.NotImplementedException;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public final class ParseTrees {

    public static Interval getSourceInterval(@NonNull ParserRuleContext context) {
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
            TokenSource tokenSource = context.getStart().getTokenSource();
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

    public static Interval getSourceInterval(@NonNull ParseTree context) {
        Parameters.notNull("context", context);

        if (context instanceof TerminalNode) {
            TerminalNode terminalNode = (TerminalNode)context;
            Token token = terminalNode.getSymbol();
            return new Interval(token.getStartIndex(), token.getStopIndex());
        } else if (context instanceof RuleNode) {
            RuleNode ruleNode = (RuleNode)context;
            RuleContext ruleContext = ruleNode.getRuleContext();
            if (ruleContext instanceof ParserRuleContext) {
                return getSourceInterval((ParserRuleContext)ruleContext);
            } else {
                Token startSymbol = getStartSymbol(context);
                Token stopSymbol = getStopSymbol(context);
                if (startSymbol == null || stopSymbol == null) {
                    return Interval.INVALID;
                }

                return new Interval(startSymbol.getStartIndex(), stopSymbol.getStopIndex());
            }
        } else {
            return Interval.INVALID;
        }
    }

    public static Token getStopSymbol(@NonNull ParserRuleContext context) {
        Parameters.notNull("context", context);
        if (context.stop != null) {
            return context.stop;
        }

        for (int i = context.getChildCount() - 1; i >= 0; i--) {
            Token symbol = getStopSymbol(context.getChild(i));
            if (symbol != null) {
                return symbol;
            }
        }

        return context.start;
    }

    public static Token getStopSymbol(@NonNull ParseTree context) {
        Parameters.notNull("context", context);

        if (context instanceof ParserRuleContext) {
            return getStopSymbol((ParserRuleContext)context);
        } else if (context instanceof TerminalNode) {
            return ((TerminalNode)context).getSymbol();
        }

        return null;
    }

    public static TerminalNode getStartNode(ParseTree context) {
        if (context == null) {
            return null;
        }

        if (context instanceof TerminalNode) {
            return (TerminalNode)context;
        }

        for (int i = 0; i < context.getChildCount(); i++) {
            TerminalNode startNode = getStartNode(context.getChild(i));
            if (startNode != null) {
                return startNode;
            }
        }

        return null;
    }

    public static Token getStartSymbol(ParseTree context) {
        TerminalNode node = getStartNode(context);
        if (node != null) {
            return node.getSymbol();
        }

        if (!(context instanceof RuleNode)) {
            return null;
        }

        RuleContext ruleContext = ((RuleNode)context).getRuleContext();
        if (ruleContext instanceof ParserRuleContext) {
            return ((ParserRuleContext)ruleContext).getStart();
        }

        return null;
    }

    public static TerminalNode getStopNode(ParseTree context) {
        if (context == null) {
            return null;
        }

        if (context instanceof TerminalNode) {
            return (TerminalNode)context;
        }

        for (int i = context.getChildCount() - 1; i >= 0; i--) {
            TerminalNode stopNode = getStopNode(context.getChild(i));
            if (stopNode != null) {
                return stopNode;
            }
        }

        return null;
    }

    public static boolean isInContexts(@NonNull ParserRuleContext context, boolean allowGaps, @NonNull int... stack) {
        Parameters.notNull("context", context);
        Parameters.notNull("stack", stack);

        if (allowGaps) {
            throw new UnsupportedOperationException("Not implemented yet.");
        }

        ParserRuleContext currentContext = context;
        for (int element : stack) {
            if (currentContext.getRuleIndex() != element) {
                return false;
            }

            currentContext = currentContext.getParent();
            if (currentContext == null) {
                return false;
            }
        }

        return true;
    }

    public static <T extends Token> boolean isInAnyContext(Parser parser, RuleContext context, IntervalSet values) {
        return isInAnyContext(parser, context, values, true);
    }

    public static <T extends Token> boolean isInAnyContext(Parser parser, RuleContext context, IntervalSet values, boolean checkTop) {
        return findTopContext(parser, context, values, checkTop) != null;
    }

    public static boolean isInAnyContext(ParserRuleContext context, IntervalSet values) {
        return isInAnyContext(context, values, true);
    }

    public static boolean isInAnyContext(ParserRuleContext context, IntervalSet values, boolean checkTop) {
        return findTopContext(context, values, checkTop) != null;
    }

    public static RuleContext findTopContext(Parser parser, RuleContext context, IntervalSet values) {
        return findTopContext(parser, context, values, true);
    }

    public static RuleContext findTopContext(Parser parser, RuleContext context, IntervalSet values, boolean checkTop) {
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

    public static ParserRuleContext findTopContext(ParserRuleContext context, IntervalSet values) {
        return findTopContext(context, values, true);
    }

    public static ParserRuleContext findTopContext(ParserRuleContext context, IntervalSet values, boolean checkTop) {
        if (checkTop && values.contains(context.getRuleIndex())) {
            return context;
        }

        if (context.isEmpty()) {
            return null;
        }

        return findTopContext((ParserRuleContext)context.parent, values, true);
    }

    @CheckForNull
    public static TerminalNode findTerminalNode(@NonNull ParseTree node, Token symbol) {
        if (symbol == null) {
            return null;
        }

        if (node instanceof TerminalNode) {
            TerminalNode terminalNode = (TerminalNode)node;
            if (Utils.equals(terminalNode.getSymbol(), symbol)) {
                return terminalNode;
            }

            return null;
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            ParseTree child = node.getChild(i);
            TerminalNode stopNode = ParseTrees.getStopNode(child);
            if (stopNode == null) {
                continue;
            }

            Token stopSymbol = stopNode.getSymbol();
            if (stopSymbol.getStopIndex() < symbol.getStartIndex()) {
                continue;
            }

            TerminalNode startNode = ParseTrees.getStartNode(child);
            assert startNode != null;

            stopSymbol = startNode.getSymbol();
            if (stopSymbol == null || stopSymbol.getStartIndex() > symbol.getStopIndex()) {
                break;
            }

            if (stopSymbol.equals(symbol)) {
                return startNode;
            }

            TerminalNode terminalNode = findTerminalNode(child, symbol);
            if (terminalNode != null) {
                return terminalNode;
            }
        }

        return null;
    }

    public static TerminalNode findTerminalNode(Collection<? extends ParseTree> children, Token symbol) {
        for (ParseTree element : children) {
            if (!(element instanceof TerminalNode)) {
                continue;
            }

            TerminalNode node = (TerminalNode)element;
            if (node.getSymbol() == symbol) {
                return node;
            }
        }

        return null;
    }

    public static int getInvokingRule(ATN atn, RuleContext context) {
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
     * @param <T>
     * @param t
     * @return
     */
    @NonNull
    public static List<? extends ParseTree> getAncestors(@NonNull ParseTree t) {
        if ( t.getParent()==null ) {
            return Collections.emptyList();
        }

        List<ParseTree> ancestors = new ArrayList<>();
        t = t.getParent();
        while ( t!=null ) {
            ancestors.add(0, t); // insert at start
            t = t.getParent();
        }

        return ancestors;
    }

    @CheckForNull
    public static RuleNode findAncestor(@NonNull ParseTree tree, int ruleIndex) {
        for (ParseTree current = tree; current != null; current = current.getParent()) {
            if (!(current instanceof RuleNode)) {
                continue;
            }

            RuleNode ruleNode = (RuleNode)current;
            if (ruleNode.getRuleContext().getRuleIndex() == ruleIndex) {
                return ruleNode;
            }
        }

        return null;
    }

    @CheckForNull
    public static RuleNode findAncestor(@NonNull ParseTree tree, @NonNull BitSet ruleIndexes) {
        for (ParseTree current = tree; current != null; current = current.getParent()) {
            if (!(current instanceof RuleNode)) {
                continue;
            }

            RuleNode ruleNode = (RuleNode)current;
            int ruleIndex = ruleNode.getRuleContext().getRuleIndex();
            if (ruleIndex < 0) {
                continue;
            }

            if (ruleIndexes.get(ruleIndex)) {
                return ruleNode;
            }
        }

        return null;
    }

    @CheckForNull
    public static <ContextClass> ContextClass findAncestor(@NonNull ParseTree tree, @NonNull Class<ContextClass> nodeType) {
        for (ParseTree current = tree; current != null; current = current.getParent()) {
            if (!(current instanceof RuleNode)) {
                continue;
            }

            RuleNode ruleNode = (RuleNode)current;
            RuleContext ruleContext = ruleNode.getRuleContext();
            if (nodeType.isInstance(ruleContext)) {
                return nodeType.cast(ruleContext);
            }
        }

        return null;
    }

    /**
     * Gets whether or not {@code tree} is an epsilon non-terminal in the parse
     * tree. An epsilon tree is a node which does not contain any
     * {@link TerminalNode} descendants.
     *
     * @param tree A node in a parse tree.
     * @return {@code true} if {@code tree} is an epsilon node in the parse
     * tree, otherwise {@code false}.
     */
    public static boolean isEpsilon(@NonNull ParseTree tree) {
        if (tree instanceof TerminalNode) {
            return false;
        }
        
        Interval sourceInterval = tree.getSourceInterval();
        return sourceInterval.b < sourceInterval.a;
    }

    /**
     * Gets whether or not {@code a} starts after the start of {@code b}.
     *
     * @param a The first tree.
     * @param b The second tree.
     * @return {@code true} if {@code a} starts after the start of {@code b}, otherwise {@code false}.
     */
    public static boolean startsAfterStartOf(@NonNull ParseTree a, @NonNull ParseTree b) {
        //TerminalNode<? extends Token> startNodeA = getStartNode(a);
        //TerminalNode<? extends Token> startNodeB = getStartNode(b);
        //if (startNodeA == null || startNodeB == null) {
        //    throw new NotImplementedException();
        //}

        Interval sourceIntervalA = a.getSourceInterval();
        Interval sourceIntervalB = b.getSourceInterval();

        //if (sourceIntervalA.a == sourceIntervalB.a) {
        //    if (isAncestorOf(a, b)) {
        //        return true;
        //    }
        //
        //    if (isEpsilon(a) || isEpsilon(b)) {
        //        // b could be a child of a later sibling of some ancestor of a
        //        throw new NotImplementedException();
        //    }
        //}

        return sourceIntervalA.a > sourceIntervalB.a;
    }

    /**
     * Gets whether or not {@code a} starts before the start of {@code b}.
     *
     * @param a The first tree.
     * @param b The second tree.
     * @return {@code true} if {@code a} starts before the start of {@code b}, otherwise {@code false}.
     */
    public static boolean startsBeforeStartOf(@NonNull ParseTree a, @NonNull ParseTree b) {
        Interval sourceIntervalA = a.getSourceInterval();
        Interval sourceIntervalB = b.getSourceInterval();
        return sourceIntervalA.a < sourceIntervalB.a;
    }

    /**
     * Gets whether or not {@code a} ends after the end of {@code b}.
     *
     * @param a The first tree.
     * @param b The second tree.
     * @return {@code true} if {@code a} ends after the end of {@code b}, otherwise {@code false}.
     */
    public static boolean endsAfterEndOf(@NonNull ParseTree a, @NonNull ParseTree b) {
        Interval sourceIntervalA = a.getSourceInterval();
        Interval sourceIntervalB = b.getSourceInterval();
        return sourceIntervalA.b > sourceIntervalB.b;
    }

    /**
     * Gets whether or not {@code a} ends before the end of {@code b}.
     *
     * @param a The first tree.
     * @param b The second tree.
     * @return {@code true} if {@code a} ends before the end of {@code b}, otherwise {@code false}.
     */
    public static boolean endsBeforeEndOf(@NonNull ParseTree a, @NonNull ParseTree b) {
        Interval sourceIntervalA = a.getSourceInterval();
        Interval sourceIntervalB = b.getSourceInterval();
        return sourceIntervalA.b < sourceIntervalB.b;
    }

    /**
     * Gets whether or not {@code a} is an ancestor of or equal to {@code b}.
     *
     * @param a The first tree.
     * @param b The second tree.
     * @return {@code true} if {@code a} is an ancestor of or is equal to {@code b}, otherwise {@code false}.
     */
    public static boolean isAncestorOf(@NonNull ParseTree a, @NonNull ParseTree b) {
        for (ParseTree current = b; current != null; current = current.getParent()) {
            if (current.equals(a)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets whether or not the first symbol of {@code tree} is the first
     * non-whitespace symbol on a line.
     *
     * @param tree The parse tree to test.
     * @return {@code true} if the only characters appearing before the first
     * token of {@code tree} on the line where {@code tree} starts are
     * whitespace characters according to {@link Character#isWhitespace}.
     */
    public static boolean elementStartsLine(ParseTree tree) {
        TerminalNode symbol = ParseTrees.getStartNode(tree);
        if (symbol == null) {
            throw new NotImplementedException();
        }

        return elementStartsLine(symbol.getSymbol());
    }

    /**
     * Gets whether or not {@code token} is the first non-whitespace symbol on a
     * line.
     *
     * @param token The token to test.
     * @return {@code true} if the only characters appearing before
     * {@code token} on the same line are whitespace characters according to
     * {@link Character#isWhitespace}.
     */
    public static boolean elementStartsLine(Token token) {
        String beginningOfLineText = token.getTokenSource().getInputStream().getText(new Interval(token.getStartIndex() - token.getCharPositionInLine(), token.getStartIndex() - 1));
        for (int i = 0; i < beginningOfLineText.length(); i++) {
            if (!Character.isWhitespace(beginningOfLineText.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets the symbol type of a parse tree terminal node. If the node is not a
     * terminal node, this method returns {@link Token#INVALID_TYPE}.
     *
     * @param node The parse tree node.
     * @return The symbol type of the terminal node. If {@code node} does not
     * implement {@link TerminalNode}, this method returns
     * {@link Token#INVALID_TYPE}.
     */
    @CheckForNull
    public static int getTerminalNodeType(@NonNull ParseTree node) {
        if (!(node instanceof TerminalNode)) {
            return Token.INVALID_TYPE;
        }

        return ((TerminalNode)node).getSymbol().getType();
    }

    /**
     * Gets a typed rule context from a parse tree node. If {@code node} is a
     * {@link RuleNode}, this method gets the {@link RuleContext} instance from
     * the node and attempts to cast the result to {@code clazz}. If
     * {@code node} is not a {@code RuleNode}, or if the context is not of type
     * {@code clazz}, this method returns {@code null}.
     *
     * @param <T> The specific rule context type.
     * @param node The parse tree node.
     * @param clazz The specific rule context type.
     * @return A typed rule context object, or {@code null} if the parse tree
     * node does not represent a rule node of this specific type.
     */
    @CheckForNull
    public static <T extends ParserRuleContext> T getTypedRuleContext(@NonNull ParseTree node, @NonNull Class<T> clazz) {
        if (!(node instanceof RuleNode)) {
            return null;
        }

        RuleContext ruleContext = ((RuleNode)node).getRuleContext();
        if (clazz.isInstance(ruleContext)) {
            return clazz.cast(ruleContext);
        }

        return null;
    }

    private ParseTrees() {
    }

}
