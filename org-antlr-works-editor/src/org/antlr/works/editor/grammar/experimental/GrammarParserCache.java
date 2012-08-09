/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.RuleDependencyChecker;
import org.antlr.works.editor.antlr4.completion.AbstractParserCache;
import org.antlr.works.editor.antlr4.parsing.DescriptiveErrorListener;
import org.antlr.works.editor.grammar.completion.ActionExpressionAnalyzer;
import org.antlr.works.editor.grammar.completion.GrammarCompletionQuery;
import org.antlr.works.editor.grammar.completion.LabelAnalyzer;
import org.antlr.works.editor.grammar.highlighter4.SemanticHighlighter;
import org.antlr.works.editor.grammar.semantics.SemanticAnalyzerListener;
import org.antlr.works.editor.grammar.syndiag.SetTerminal;
import org.antlr.works.editor.grammar.syndiag.SyntaxDiagramTopComponent;

/**
 *
 * @author Sam Harwell
 */
public class GrammarParserCache extends AbstractParserCache<Token, GrammarParser> {
    public static final GrammarParserCache DEFAULT = new GrammarParserCache();

    private static boolean dependenciesChecked;

    @Override
    protected GrammarParser createParser(TokenStream<? extends Token> input) {
        if (!dependenciesChecked) {
            checkDependencies();
        }

        GrammarParser parser = new GrammarParser(input);
        return parser;
    }

    @Override
    public GrammarParser getParser(TokenStream<? extends Token> input) {
        GrammarParser result = super.getParser(input);
        result.getInterpreter().disable_global_context = false;
        result.removeErrorListeners();
        result.addErrorListener(DescriptiveErrorListener.INSTANCE);
        result.setBuildParseTree(false);
        result.setErrorHandler(new DefaultErrorStrategy<Token>());
        return result;
    }

    private static void checkDependencies() {
        RuleDependencyChecker.checkDependencies(ActionExpressionAnalyzer.class);
        RuleDependencyChecker.checkDependencies(GrammarCompletionQuery.class);
        RuleDependencyChecker.checkDependencies(LabelAnalyzer.class);
        RuleDependencyChecker.checkDependencies(CodeModelBuilderListener.class);
        RuleDependencyChecker.checkDependencies(CurrentRuleContextData.class);
        RuleDependencyChecker.checkDependencies(CurrentRuleContextParserTask.class);
        RuleDependencyChecker.checkDependencies(GrammarParserAnchorListener.class);
        RuleDependencyChecker.checkDependencies(ReferenceAnchorsParserTask.class);
        RuleDependencyChecker.checkDependencies(SemanticAnalyzerListener.class);
        RuleDependencyChecker.checkDependencies(SemanticHighlighter.class);
        RuleDependencyChecker.checkDependencies(SyntaxDiagramTopComponent.class);
        RuleDependencyChecker.checkDependencies(SetTerminal.class);
        dependenciesChecked = true;
    }
}
