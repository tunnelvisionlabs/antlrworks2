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
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.parserRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleContext;

/**
 *
 * @author Sam Harwell
 */
public final class CurrentRuleContextData {
    private final DocumentSnapshot snapshot;
    private final int grammarType;
    private final ruleContext context;

    public CurrentRuleContextData(DocumentSnapshot snapshot, int grammarType, ruleContext context) {
        this.snapshot = snapshot;
        this.grammarType = grammarType;
        this.context = context;
    }

    public DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    public int getGrammarType() {
        return grammarType;
    }

    public ruleContext getContext() {
        return context;
    }

    public String getRuleName() {
        if (context == null) {
            return null;
        }

        Token nameToken = null;
        if (context.getChild(0) instanceof parserRuleContext) {
            nameToken = ((parserRuleContext)context.getChild(0)).name;
        } else if (context.getChild(0) instanceof lexerRuleContext) {
            nameToken = ((lexerRuleContext)context.getChild(0)).name;
        }

        if (nameToken == null) {
            return null;
        }

        return nameToken.getText();
    }
}
