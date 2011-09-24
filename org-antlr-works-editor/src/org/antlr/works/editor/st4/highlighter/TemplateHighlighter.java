/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.highlighting.ANTLRHighlighterBase;
import org.antlr.netbeans.editor.highlighting.DocumentCharStream;
import org.antlr.netbeans.editor.highlighting.Highlight;
import org.antlr.netbeans.editor.highlighting.Span;
import org.antlr.netbeans.editor.highlighting.TokenSourceWithState;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.openide.util.Lookup;

public class TemplateHighlighter extends ANTLRHighlighterBase<TemplateHighlighterLexerState> {
    private static final List<String> keywords =
        new ArrayList<String>()
        {{
            add("group");
            add("default");
            add("import");
            add("true");
            add("false");
            add("delimiters");
        }};

    private static final List<String> expressionKeywords =
        new ArrayList<String>()
        {{
            add("if");
            add("elseif");
            add("endif");
            add("else");
            add("end");
        }};

    private final AttributeSet identifierAttributes;
    private final AttributeSet keywordAttributes;
    private final AttributeSet commentAttributes;
    private final AttributeSet stringLiteralAttributes;
    private final AttributeSet operatorAttributes;
    private final AttributeSet symbolReferenceAttributes;
    private final AttributeSet bigStringDelimiterAttributes;
    private final AttributeSet expressionDelimiterAttributes;
    private final AttributeSet anonymousTemplateDelimiterAttributes;
    private final AttributeSet escapeCharacterAttributes;
    private final AttributeSet escapeTagAttributes;

    public TemplateHighlighter(StyledDocument document) {
        super(document);

        Lookup lookup = MimeLookup.getLookup(MimePath.parse("text/x-stringtemplate4"));
        FontColorSettings settings = lookup.lookup(FontColorSettings.class);
        identifierAttributes = getFontAndColors(settings, "identifier");
        keywordAttributes = getFontAndColors(settings, "keyword");
        commentAttributes = getFontAndColors(settings, "comment");
        stringLiteralAttributes = getFontAndColors(settings, "stringliteral");
        operatorAttributes = getFontAndColors(settings, "operator");
        symbolReferenceAttributes = getFontAndColors(settings, "reference");
        bigStringDelimiterAttributes = getFontAndColors(settings, "bigStringDelimiter");
        expressionDelimiterAttributes = getFontAndColors(settings, "expressionDelimiter");
        anonymousTemplateDelimiterAttributes = getFontAndColors(settings, "anonymousTemplateDelimiter");
        escapeCharacterAttributes = getFontAndColors(settings, "escapeCharacter");
        escapeTagAttributes = getFontAndColors(settings, "escapeTag");
    }

    private static AttributeSet getFontAndColors(FontColorSettings settings, String category) {
        AttributeSet attributes = settings.getTokenFontColors(category);
        return attributes;
    }

    @Override
    protected CharStream createInputStream(Span span) {
        CharStream input = new TemplateEscapedCharStream(getDocument());
        input.seek(span.getStart());
        return input;
    }

    @Override
    protected TemplateHighlighterLexerState getStartState() {
        return TemplateHighlighterLexerState.INITIAL;
    }

    @Override
    protected TokenSourceWithState<TemplateHighlighterLexerState> createLexer(CharStream input, TemplateHighlighterLexerState startState) {
        return new TemplateHighlighterLexer(input, startState);
    }

    @Override
    protected AttributeSet highlightToken(CommonToken token) {
        switch (token.getType()) {
        case GroupHighlighterLexer.ID:
            if (keywords.contains(token.getText())) {
                return keywordAttributes;
            }
            return identifierAttributes;

        case GroupHighlighterLexer.PARAMETER_DEFINITION:
            return identifierAttributes;

        case InsideHighlighterLexer.EXPR_IDENTIFIER:
            if (expressionKeywords.contains(token.getText())) {
                return keywordAttributes;
            }
            return identifierAttributes;

        case GroupHighlighterLexer.BEGIN_BIGSTRING:
        case GroupHighlighterLexer.END_BIGSTRING:
        case GroupHighlighterLexer.BEGIN_BIGSTRINGLINE:
        case GroupHighlighterLexer.END_BIGSTRINGLINE:
            return bigStringDelimiterAttributes;

        case InsideHighlighterLexer.STRING:
        case OutsideHighlighterLexer.TEXT:
        case OutsideHighlighterLexer.QUOTE:
        case GroupHighlighterLexer.DELIMITER_SPEC:
            return stringLiteralAttributes;

        case GroupHighlighterLexer.COMMENT:
        case GroupHighlighterLexer.LINE_COMMENT:
            return commentAttributes;

        case GroupHighlighterLexer.WS:
            //return whitespaceAttributes;
            return null;

        case OutsideHighlighterLexer.LDELIM:
        case InsideHighlighterLexer.RDELIM:
            return expressionDelimiterAttributes;

        case GroupHighlighterLexer.LBRACE:
        case GroupHighlighterLexer.RBRACE:
            return anonymousTemplateDelimiterAttributes;

        case GroupHighlighterLexer.DEFINED:
        case GroupHighlighterLexer.EQUALS:
        case InsideHighlighterLexer.ELLIPSIS:
            return operatorAttributes;

        case InsideHighlighterLexer.REGION_REF:
            return symbolReferenceAttributes;

        case OutsideHighlighterLexer.ESCAPE_CHAR:
            return escapeCharacterAttributes;

        case OutsideHighlighterLexer.ESCAPE_TAG:
            return escapeTagAttributes;

        default:
            return null;
        }
    }

    @Override
    protected Collection<Highlight> getHighlightsForToken(CommonToken token) {
        if (token.getType() == GroupHighlighterLexer.LEGACY_DELIMITERS) {
            throw new UnsupportedOperationException("Not implemented yet.");
        } else {
            return super.getHighlightsForToken(token);
        }
    }

    public class TemplateEscapedCharStream extends DocumentCharStream {
        private TemplateHighlighterLexer lexer;

        public TemplateEscapedCharStream(StyledDocument document) {
            super(document);
        }

        public TemplateHighlighterLexer getLexer() {
            return lexer;
        }

        public void setLexer(TemplateHighlighterLexer value) {
            lexer = value;
        }

        @Override
        public void consume() {
            boolean consumeEscape = shouldConsumeEscape(0);
            if (consumeEscape) {
                super.consume();
            }

            super.consume();
        }

        @Override
        public int LA(int i) {
            int escapeCount = 0;
            if (i >= 1) {
                for (int j = 0; j < i; j++) {
                    if (shouldConsumeEscape(j + escapeCount)) {
                        escapeCount++;
                    }
                }
            }

            return super.LA(i + escapeCount);
        }

        private boolean shouldConsumeEscape(int offset) {
            if (getLexer() == null) {
                return false;
            }

            if (super.LA(offset + 1) != '\\') {
                return false;
            }

            boolean inString = getLexer().getOutermost() == OutermostTemplate.String;
            if (inString) {
                return super.LA(offset + 2) == '"';
            }

            boolean inBigString = getLexer().getOutermost() == OutermostTemplate.BigString
                || getLexer().getOutermost() == OutermostTemplate.BigStringLine;
            if (inBigString) {
                return super.LA(offset + 2) == '>';
            }

            return false;
        }
    }
}
