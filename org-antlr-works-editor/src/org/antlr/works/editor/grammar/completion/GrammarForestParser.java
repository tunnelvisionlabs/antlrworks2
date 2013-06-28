/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.works.editor.antlr4.completion.AbstractForestParser;
import org.antlr.works.editor.grammar.experimental.GrammarParser;

/**
 *
 * @author Sam Harwell
 */
@RuleDependencies({
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_rules, version=0, dependents=Dependents.SELF),
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0, dependents=Dependents.SELF),
})
public class GrammarForestParser extends AbstractForestParser<CodeCompletionGrammarParser> {
    public static final GrammarForestParser RULES = new GrammarForestParser(GrammarParser.RULE_rules);
    public static final GrammarForestParser GRAMMAR_SPEC = new GrammarForestParser(GrammarParser.RULE_grammarSpec);

    private final int _startRule;

    protected GrammarForestParser(int startRule) {
        switch (startRule) {
        case GrammarParser.RULE_rules:
        case GrammarParser.RULE_grammarSpec:
            break;

        default:
            throw new IllegalArgumentException("Unknown start rule.");
        }

        _startRule = startRule;
    }

    @Override
    protected RuleContext parseImpl(CodeCompletionGrammarParser parser) {
        switch (_startRule) {
        case GrammarParser.RULE_rules:
            return parser.rules();

        case GrammarParser.RULE_grammarSpec:
            return parser.grammarSpec();

        default:
            throw new UnsupportedOperationException();
        }
    }

}
