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

/**
 *
 * @author Sam Harwell
 */
public class GrammarLexer extends AbstractGrammarLexer {

    public GrammarLexer(CharStream input) {
        super(input);
    }

    @Override
    public Token emit() {
        if (_type == ID) {
            String firstChar = _input.getText(Interval.of(_tokenStartCharIndex, _tokenStartCharIndex));
            if (Character.isUpperCase(firstChar.charAt(0))) {
                _type = TOKEN_REF;
            } else {
                _type = RULE_REF;
            }
        }

        return super.emit();
    }

}
