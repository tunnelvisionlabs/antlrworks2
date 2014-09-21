/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.highlighter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.tool.Grammar;
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.antlr.works.editor.grammar.highlighter.generated.AbstractGrammarHighlighterLexer;

/**
 *
 * @author Sam Harwell
 */
public class GrammarHighlighterLexer extends AbstractGrammarHighlighterLexer {
    private boolean inOptions;
    private boolean inTokens;
    private int _ruleType;

    public GrammarHighlighterLexer(CharStream input) {
        super(input);
        _interp = new GrammarHighlighterATNSimulator(this, _ATN);
    }

    public boolean isInOptions() {
        return inOptions;
    }

    public void setInOptions(boolean inOptions) {
        this.inOptions = inOptions;
    }

    public boolean isInTokens() {
        return inTokens;
    }

    public void setInTokens(boolean inTokens) {
        this.inTokens = inTokens;
    }

    public int getRuleType() {
        return _ruleType;
    }

    public void setRuleType(int ruleType) {
        assert ruleType == GrammarLexer.TOKEN_REF || ruleType == GrammarLexer.RULE_REF || ruleType == Token.INVALID_TYPE;
        this._ruleType = ruleType;
    }

    public boolean isInLexerRule() {
        return _ruleType == GrammarLexer.TOKEN_REF;
    }

    @Override
    protected void handleBeginArgAction() {
        if (isInLexerRule()) {
            pushMode(LexerCharSet);
        } else {
            pushMode(ArgAction);
        }
    }

    @Override
    public Token emit() {
        switch (_type) {
        case TOKENS:
            handleAcceptPositionForKeyword("tokens");
            setInTokens(true);
            break;

        case OPTIONS:
            handleAcceptPositionForKeyword("options");
            setInOptions(true);
            break;

        case CHANNELS:
            handleAcceptPositionForKeyword("channels");
            break;

        case LABEL:
            handleAcceptPositionForIdentifier();
            if (isInOptions()) {
                _type = ValidGrammarOption;
            } else if (isInTokens()) {
                _type = IDENTIFIER;
            }

            break;

        case RCURLY:
            setInTokens(false);
            setInOptions(false);
            break;

        case SEMI:
            setRuleType(Token.INVALID_TYPE);
            break;

        case IDENTIFIER:
            if (_ruleType == Token.INVALID_TYPE) {
                String firstChar = _input.getText(Interval.of(_tokenStartCharIndex, _tokenStartCharIndex));
                if (Grammar.isTokenName(firstChar)) {
                    _ruleType = GrammarLexer.TOKEN_REF;
                } else {
                    _ruleType = GrammarLexer.RULE_REF;
                }
            }

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

        if (getInputStream().index() > _tokenStartCharIndex + identifierLength) {
            int offset = identifierLength - 1;
            getInterpreter().resetAcceptPosition(getInputStream(), _tokenStartCharIndex + offset, _tokenStartLine, _tokenStartCharPositionInLine + offset);
            return true;
        }

        return false;
    }

    private boolean handleAcceptPositionForKeyword(String keyword) {
        if (getInputStream().index() > _tokenStartCharIndex + keyword.length()) {
            int offset = keyword.length() - 1;
            getInterpreter().resetAcceptPosition(getInputStream(), _tokenStartCharIndex + offset, _tokenStartLine, _tokenStartCharPositionInLine + offset);
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
            input.seek(index);
            this.line = line;
            this.charPositionInLine = charPositionInLine;
            consume(input);
        }

    }

}
