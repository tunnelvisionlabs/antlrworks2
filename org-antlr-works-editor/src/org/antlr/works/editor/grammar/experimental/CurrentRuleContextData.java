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
