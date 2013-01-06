/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.experimental;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.tool.Grammar;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarLexer;

/**
 *
 * @author Sam Harwell
 */
public class GrammarLexer extends AbstractGrammarLexer {

    private int _ruleType;

    public GrammarLexer(CharStream input) {
        super(input);
    }

    @Override
    protected void handleBeginArgAction() {
        if (inLexerRule()) {
            pushMode(LexerCharSet);
            more();
        } else {
            pushMode(ArgAction);
        }
    }

    @Override
    public Token emit() {
        if (_type == ID) {
            String firstChar = _input.getText(Interval.of(_tokenStartCharIndex, _tokenStartCharIndex));
            if (Grammar.isTokenName(firstChar)) {
                _type = TOKEN_REF;
            } else {
                _type = RULE_REF;
            }

            if (_ruleType == Token.INVALID_TYPE) {
                _ruleType = _type;
            }
        } else if (_type == SEMI) {
            _ruleType = Token.INVALID_TYPE;
        }

        return super.emit();
    }

    private boolean inLexerRule() {
        return _ruleType == TOKEN_REF;
    }

}
