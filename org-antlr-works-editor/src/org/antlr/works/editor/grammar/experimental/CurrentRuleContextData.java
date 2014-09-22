/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.RuleSpecContext;

/**
 *
 * @author Sam Harwell
 */
public final class CurrentRuleContextData {
    private final DocumentSnapshot snapshot;
    private final int grammarType;
    private final FileModel fileModel;
    private final RuleSpecContext context;

    public CurrentRuleContextData(DocumentSnapshot snapshot, int grammarType, FileModel fileModel, RuleSpecContext context) {
        this.snapshot = snapshot;
        this.grammarType = grammarType;
        this.fileModel = fileModel;
        this.context = context;
    }

    public DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    public int getGrammarType() {
        return grammarType;
    }

    public FileModel getFileModel() {
        return fileModel;
    }

    public RuleSpecContext getContext() {
        return context;
    }

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=3, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0, dependents=Dependents.SELF),
    })
    public String getRuleName() {
        if (context == null) {
            return null;
        }

        Token nameToken = null;
        if (context.getChild(0) instanceof ParserRuleSpecContext) {
            nameToken = ((ParserRuleSpecContext)context.getChild(0)).name;
        } else if (context.getChild(0) instanceof LexerRuleContext) {
            nameToken = ((LexerRuleContext)context.getChild(0)).name;
        }

        if (nameToken == null) {
            return null;
        }

        return nameToken.getText();
    }
}
