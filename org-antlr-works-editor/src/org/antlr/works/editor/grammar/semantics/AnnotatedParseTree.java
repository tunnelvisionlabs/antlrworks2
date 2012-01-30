/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.antlr.works.editor.grammar.semantics;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.antlr.netbeans.semantics.ObjectDecorator;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.works.editor.grammar.experimental.BlankGrammarParserListener;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class AnnotatedParseTree {

    private final ObjectDecorator<Tree> treeDecorator = new ObjectDecorator<Tree>();
    private final ObjectDecorator<Token> tokenDecorator = new ObjectDecorator<Token>();

    private ParserRuleContext<Token> parseTree;

    public AnnotatedParseTree(@NonNull ParserRuleContext<Token> parseTree) {
        Parameters.notNull("parseTree", parseTree);

        this.parseTree = parseTree;
    }

    @NonNull
    public ParserRuleContext<Token> getParseTree() {
        return parseTree;
    }

    public ObjectDecorator<Tree> getTreeDecorator() {
        return treeDecorator;
    }

    public ObjectDecorator<Token> getTokenDecorator() {
        return tokenDecorator;
    }

    public final void setParseTree(@NonNull ParserRuleContext<Token> parseTree) {
        setParseTree(parseTree, true);
    }

    public void setParseTree(@NonNull ParserRuleContext<Token> parseTree, boolean compactAnnotations) {
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
        final Set<Tree> trees = new HashSet<Tree>();
        final Set<Token> tokens = new HashSet<Token>();

        BlankGrammarParserListener listener = new BlankGrammarParserListener() {

            @Override
            public void enterEveryRule(ParserRuleContext<Token> ctx) {
                trees.add(ctx);
            }

            @Override
            public void visitTerminal(ParserRuleContext<Token> ctx, Token symbol) {
                tokens.add(symbol);
            }

        };

        final Set<Tree> extraTrees = new HashSet<Tree>();
        ParseTreeWalker.DEFAULT.walk(listener, parseTree);
        for (Map.Entry<? extends Tree, ?> entry : treeDecorator.getProperties().entrySet()) {
            if (!trees.contains(entry.getKey())) {
                extraTrees.add(entry.getKey());
            }
        }

        for (Tree tree : extraTrees) {
            treeDecorator.removeProperties(tree);
        }

        final Set<Token> extraTokens = new HashSet<Token>();
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
