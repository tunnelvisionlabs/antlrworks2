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
package org.antlr.works.editor.st4.highlighter;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Token;
import org.antlr.works.editor.antlr3.highlighting.TokenSourceWithState;

/**
 *
 * @author Sam Harwell
 */
public class TemplateHighlighterLexer implements TokenSourceWithState<TemplateHighlighterLexerState> {
    private final CharStream input;
    private final InsideHighlighterLexer insideLexer;
    private final OutsideHighlighterLexer outsideLexer;
    private final GroupHighlighterLexer groupLexer;

    private TemplateLexerMode mode;
    private OutermostTemplate outermost;
    private int anonymousTemplateLevel;
    private boolean inComment;
    private char openDelimiter = '<';
    private char closeDelimiter = '>';

    @SuppressWarnings("LeakingThisInConstructor")
    public TemplateHighlighterLexer(CharStream input, TemplateHighlighterLexerState state) {
        this.input = input;
        this.insideLexer = new InsideHighlighterLexer(input, this);
        this.outsideLexer = new OutsideHighlighterLexer(input, this);
        this.groupLexer = new GroupHighlighterLexer(input, this);

        if (input instanceof TemplateHighlighter.TemplateEscapedCharStream) {
            ((TemplateHighlighter.TemplateEscapedCharStream)input).setLexer(this);
        }

        setState(state);
    }

    @Override
    public CharStream getCharStream() {
        return input;
    }

    @Override
    public String getSourceName() {
        return "StringTemplate Highlighter";
    }

    @Override
    public TemplateHighlighterLexerState getState() {
        return new TemplateHighlighterLexerState(mode, outermost, anonymousTemplateLevel, inComment, openDelimiter, closeDelimiter);
    }

    public final void setState(TemplateHighlighterLexerState state) {
        this.mode = state.Mode;
        this.outermost = state.Outermost;
        this.anonymousTemplateLevel = state.AnonymousTemplateLevel;
        this.inComment = state.InComment;
        this.openDelimiter = state.OpenDelimiter;
        this.closeDelimiter = state.CloseDelimiter;
    }

    public TemplateLexerMode getMode() {
        return mode;
    }

    private void setMode(TemplateLexerMode value) {
        mode = value;
    }

    public OutermostTemplate getOutermost() {
        return outermost;
    }

    private void setOutermost(OutermostTemplate value) {
        outermost = value;
    }

    public int getAnonymousTemplateLevel() {
        return anonymousTemplateLevel;
    }

    private void setAnonymousTemplateLevel(int value) {
        anonymousTemplateLevel = value;
    }

    public boolean getInComment() {
        return inComment;
    }

    public void setInComment(boolean value) {
        inComment = value;
    }

    public char getOpenDelimiter() {
        return openDelimiter;
    }

    public char getCloseDelimiter() {
        return closeDelimiter;
    }

    @Override
    public Token nextToken() {
        Token token = null;
        do {
            int position = input.index();
            token = nextTokenCore();
            // ensure progress
            if (position == input.index()) {
                input.consume();
            }
        } while (token == null || token.getType() == GroupHighlighterLexer.NEWLINE);

        return token;
    }

    @SuppressWarnings("fallthrough")
    private Token nextTokenCore() {
        // when true, the outermost template's closing token is '>>'. otherwise, '"' is the closing token
        boolean insideBigString = getOutermost().equals(OutermostTemplate.BigString);
        boolean insideBigStringLine = getOutermost().equals(OutermostTemplate.BigStringLine);

        Token token = null;

        if (getMode() == TemplateLexerMode.Template
            || getMode() == TemplateLexerMode.AnonymousTemplateParameters) {
            if (getAnonymousTemplateLevel() > 0 && input.LA(1) == '}')
            {
                // no longer inside the template - let the group lexer prepare the closing template token
                token = groupLexer.nextToken();
                setAnonymousTemplateLevel(getAnonymousTemplateLevel() - 1);
                if (getAnonymousTemplateLevel() == 0 && getOutermost() == OutermostTemplate.None)
                    setMode(TemplateLexerMode.Group);
                else
                    setMode(TemplateLexerMode.Expression);
            }
            else if ((insideBigString && getAnonymousTemplateLevel() == 0 && input.LA(1) == '>' && input.LA(2) == '>')
                || (insideBigStringLine && getAnonymousTemplateLevel() == 0 && input.LA(1) == '%' && input.LA(2) == '>')
                || (!insideBigString && !insideBigStringLine && getAnonymousTemplateLevel() == 0 && input.LA(1) == '"'))
            {
                // no longer inside the template - let the group lexer prepare the closing template token
                token = groupLexer.nextToken();
                setMode(TemplateLexerMode.Group);
                setOutermost(OutermostTemplate.None);
                setInComment(false);
            }
            else
            {
                token = outsideLexer.nextToken();
                if (token.getType() == OutsideHighlighterLexer.LDELIM)
                {
                    //expressionLevel++;
                    setMode(TemplateLexerMode.Expression);
                }
                else if (getMode() == TemplateLexerMode.AnonymousTemplateParameters)
                {
                    switch (token.getType())
                    {
                    case OutsideHighlighterLexer.ID:
                        token.setType(OutsideHighlighterLexer.PARAMETER_DEFINITION);
                        break;

                    case OutsideHighlighterLexer.PIPE:
                        setMode(TemplateLexerMode.Template);
                        break;

                    default:
                        break;
                    }
                }
                else if (getMode() == TemplateLexerMode.Template)
                {
                    switch (token.getType())
                    {
                    case OutsideHighlighterLexer.ID:
                    case OutsideHighlighterLexer.PIPE:
                    case OutsideHighlighterLexer.COMMA:
                    case OutsideHighlighterLexer.WS:
                        token.setType(OutsideHighlighterLexer.TEXT);
                        break;

                    default:
                        break;
                    }
                }
            }
        } else if (getMode() == TemplateLexerMode.Expression) {
            if (input.LA(1) == getCloseDelimiter())
            {
                // no longer inside the expression - let the template lexer prepare the RANGLE token
                token = insideLexer.nextToken();
                setMode(TemplateLexerMode.Template);
                //Debug.Assert(expressionLevel > 0);
                //expressionLevel--;
            }
            else
            {
                token = insideLexer.nextToken();
                if (token.getType() == InsideHighlighterLexer.LBRACE)
                {
                    setAnonymousTemplateLevel(getAnonymousTemplateLevel()+1);
                    setMode(checkAnonymousTemplateForParameters());
                }
            }
        } else {
            // this case covers TemplateLexerMode.Group, DelimitersOpenSpec, DelimitersCloseSpec, and any others
            switch (input.LA(1))
            {
            case '{':
                token = groupLexer.nextToken();
                setAnonymousTemplateLevel(getAnonymousTemplateLevel() + 1);
                setMode(checkAnonymousTemplateForParameters());
                setOutermost(OutermostTemplate.None);
                break;

            case '"':
                token = groupLexer.nextToken();
                if (getMode() == TemplateLexerMode.Group)
                {
                    setMode(TemplateLexerMode.Template);
                    setOutermost(OutermostTemplate.String);
                }
                else if (getMode() == TemplateLexerMode.DelimiterOpenSpec)
                {
                    if (token.getText().length() > 2)
                        openDelimiter = token.getText().charAt(1);

                    setMode(TemplateLexerMode.DelimiterCloseSpec);
                }
                else if (getMode() == TemplateLexerMode.DelimiterCloseSpec)
                {
                    if (token.getText().length() > 2)
                        closeDelimiter = token.getText().charAt(1);

                    setMode(TemplateLexerMode.Group);
                }

                break;

            case '<':
                if (input.LA(2) == '<' || input.LA(2) == '%')
                {
                    token = groupLexer.nextToken();
                    setMode(TemplateLexerMode.Template);
                    setOutermost(token.getType() == GroupHighlighterLexer.BEGIN_BIGSTRINGLINE ? OutermostTemplate.BigStringLine : OutermostTemplate.BigString);
                    break;
                }
                else
                {
                    // want to fall through!
                }

            default:
                token = groupLexer.nextToken();

                switch (token.getType())
                {
                case GroupHighlighterLexer.ID:
                    if (token.getText().equals("delimiters"))
                        setMode(TemplateLexerMode.DelimiterOpenSpec);

                    break;

                case GroupHighlighterLexer.LEGACY_DELIMITERS:
                    setMode(TemplateLexerMode.DelimiterOpenSpec);
                    break;

                default:
                    break;
                }

                break;
            }
        }

        return token;
    }

    private TemplateLexerMode checkAnonymousTemplateForParameters() {
        int position = input.mark();
        TemplateHighlighterLexerState currentState = getState();

        try {
            setMode(TemplateLexerMode.AnonymousTemplateParameters);
            boolean previousWasArg = false;
            while (true) {
                Token token = nextToken();
                switch (token.getType()) {
                case OutsideHighlighterLexer.COMMA:
                    if (!previousWasArg) {
                        return TemplateLexerMode.Template;
                    }
                    previousWasArg = false;
                    continue;

                case OutsideHighlighterLexer.PARAMETER_DEFINITION:
                case OutsideHighlighterLexer.ID:
                    if (previousWasArg)
                        return TemplateLexerMode.Template;

                    previousWasArg = true;
                    continue;

                case OutsideHighlighterLexer.PIPE:
                    if (previousWasArg) {
                        return TemplateLexerMode.AnonymousTemplateParameters;
                    }
                    return TemplateLexerMode.Template;

                case OutsideHighlighterLexer.WS:
                case OutsideHighlighterLexer.COMMENT:
                case OutsideHighlighterLexer.NEWLINE:
                    continue;

                default:
                    return TemplateLexerMode.Template;
                }
            }
        } finally {
            input.rewind(position);
            setState(currentState);
        }
    }
}
