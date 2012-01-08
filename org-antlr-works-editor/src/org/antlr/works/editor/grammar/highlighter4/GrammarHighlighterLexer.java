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
package org.antlr.works.editor.grammar.highlighter4;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.LexerATNSimulator;

/**
 *
 * @author Sam Harwell
 */
public class GrammarHighlighterLexer extends GrammarHighlighterLexerBase {

    public GrammarHighlighterLexer(CharStream input) {
        super(input);
        _interp = new GrammarHighlighterATNSimulator(this, _ATN);
    }

    @Override
    public Token emit() {
        switch (type) {
        case TOKENS:
            handleAcceptPositionForKeyword("tokens");
            break;

        case OPTIONS:
            handleAcceptPositionForKeyword("options");
            break;

        case LABEL:
            handleAcceptPositionForIdentifier();
            break;

        default:
            break;
        }

        return super.emit();
    }

    private boolean handleAcceptPositionForIdentifier() {
        String tokenText = getText();
        int identifierLength = 0;
        while (identifierLength < tokenText.length() && isIdentifierChar(tokenText.charAt(identifierLength))) {
            identifierLength++;
        }

        if (getInputStream().index() > tokenStartCharIndex + identifierLength) {
            int offset = identifierLength - 1;
            getInterpreter().resetAcceptPosition(getInputStream(), tokenStartCharIndex + offset, tokenStartLine, tokenStartCharPositionInLine + offset);
            return true;
        }

        return false;
    }

    private boolean handleAcceptPositionForKeyword(String keyword) {
        if (getInputStream().index() > tokenStartCharIndex + keyword.length()) {
            int offset = keyword.length() - 1;
            getInterpreter().resetAcceptPosition(getInputStream(), tokenStartCharIndex + offset, tokenStartLine, tokenStartCharPositionInLine + offset);
            return true;
        }

        return false;
    }

    @Override
    public GrammarHighlighterATNSimulator getInterpreter() {
        return (GrammarHighlighterATNSimulator)super.getInterpreter();
    }

    private static boolean isIdentifierChar(char c) {
        return Character.isLetterOrDigit(c) || c == '_';
    }

    protected static class GrammarHighlighterATNSimulator extends LexerATNSimulator {

        public GrammarHighlighterATNSimulator(Lexer recog, ATN atn) {
            super(recog, atn);
        }

        protected void resetAcceptPosition(CharStream input, int index, int line, int charPositionInLine) {
            traceSeek(index);
            input.seek(index);
            this.line = line;
            this.charPositionInLine = charPositionInLine;
            consume(input);
        }

    }

}
