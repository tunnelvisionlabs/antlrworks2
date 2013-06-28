/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.semantics;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.antlr.netbeans.semantics.ObjectDecorator;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.works.editor.grammar.experimental.generated.GrammarParserBaseListener;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class AnnotatedParseTree {

    private final ObjectDecorator<Tree> treeDecorator = new ObjectDecorator<>();
    private final ObjectDecorator<Token> tokenDecorator = new ObjectDecorator<>();

    private ParserRuleContext parseTree;

    public AnnotatedParseTree(@NonNull ParserRuleContext parseTree) {
        Parameters.notNull("parseTree", parseTree);

        this.parseTree = parseTree;
    }

    @NonNull
    public ParserRuleContext getParseTree() {
        return parseTree;
    }

    public ObjectDecorator<Tree> getTreeDecorator() {
        return treeDecorator;
    }

    public ObjectDecorator<Token> getTokenDecorator() {
        return tokenDecorator;
    }

    public final void setParseTree(@NonNull ParserRuleContext parseTree) {
        setParseTree(parseTree, true);
    }

    public void setParseTree(@NonNull ParserRuleContext parseTree, boolean compactAnnotations) {
        Parameters.notNull("parseTree", parseTree);

        if (this.parseTree != parseTree && compactAnnotations) {
            treeDecorator.clear();
            tokenDecorator.clear();
            compactAnnotations = false;
        }

        this.parseTree = parseTree;
        if (compactAnnotations) {
            compactAnnotations();
        }
    }

    public void compactAnnotations() {
        final Set<Tree> trees = new HashSet<>();
        final Set<Token> tokens = new HashSet<>();

        GrammarParserBaseListener listener = new GrammarParserBaseListener() {

            @Override
            public void enterEveryRule(ParserRuleContext ctx) {
                trees.add(ctx);
            }

            @Override
            public void visitTerminal(TerminalNode node) {
                tokens.add(node.getSymbol());
            }

        };

        final Set<Tree> extraTrees = new HashSet<>();
        ParseTreeWalker.DEFAULT.walk(listener, parseTree);
        for (Map.Entry<? extends Tree, ?> entry : treeDecorator.getProperties().entrySet()) {
            if (!trees.contains(entry.getKey())) {
                extraTrees.add(entry.getKey());
            }
        }

        for (Tree tree : extraTrees) {
            treeDecorator.removeProperties(tree);
        }

        final Set<Token> extraTokens = new HashSet<>();
        ParseTreeWalker.DEFAULT.walk(listener, parseTree);
        for (Map.Entry<? extends Token, ?> entry : tokenDecorator.getProperties().entrySet()) {
            if (!tokens.contains(entry.getKey())) {
                extraTokens.add(entry.getKey());
            }
        }

        for (Token token : extraTokens) {
            tokenDecorator.removeProperties(token);
        }
    }

}
