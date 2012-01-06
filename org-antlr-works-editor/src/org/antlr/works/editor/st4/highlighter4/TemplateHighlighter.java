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
package org.antlr.works.editor.st4.highlighter4;

import java.util.Collection;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.highlighting.ANTLRHighlighterBaseV4;
import org.antlr.netbeans.editor.highlighting.DocumentCharStreamV4;
import org.antlr.netbeans.editor.highlighting.Highlight;
import org.antlr.netbeans.editor.highlighting.TokenSourceWithStateV4;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.openide.util.Lookup;

/**
 *
 * @author Sam Harwell
 */
public class TemplateHighlighter extends ANTLRHighlighterBaseV4<TemplateHighlighterLexerState> {

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
    private final AttributeSet regionUseAttributes;

    private TemplateHighlighterLexer lexer;

    public TemplateHighlighter(StyledDocument document) {
        super(document);

        Lookup lookup = MimeLookup.getLookup(MimePath.parse(StringTemplateEditorKit.TEMPLATE_MIME_TYPE));
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
        this.regionUseAttributes = getFontAndColors(settings, "regionUse");
    }

    private static AttributeSet getFontAndColors(FontColorSettings settings, String category) {
        AttributeSet attributes = settings.getTokenFontColors(category);
        return attributes;
    }

    @Override
    protected CharStream createInputStream(OffsetRegion span) throws BadLocationException {
        return super.createInputStream(span);
    }

    @Override
    protected TemplateHighlighterLexerState getStartState() {
        return TemplateHighlighterLexerState.INITIAL;
    }

    @Override
    protected TokenSourceWithStateV4<TemplateHighlighterLexerState> createLexer(CharStream input, TemplateHighlighterLexerState startState) {
        if (lexer == null) {
            lexer = new TemplateHighlighterLexer(input, startState);
        } else {
            lexer.setState(input, startState);
        }

        return lexer;
    }

    @Override
    protected AttributeSet highlightToken(Token token) {
        switch (token.getType()) {
        case GroupHighlighterLexerBase.DEFAULT:
        case GroupHighlighterLexerBase.IMPORT:
        case GroupHighlighterLexerBase.GROUP:
        case GroupHighlighterLexerBase.TRUE:
        case GroupHighlighterLexerBase.FALSE:
        case GroupHighlighterLexerBase.DELIMITERS:
        case GroupHighlighterLexerBase.IF:
        case GroupHighlighterLexerBase.ELSEIF:
        case GroupHighlighterLexerBase.ELSE:
        case GroupHighlighterLexerBase.ENDIF:
        case GroupHighlighterLexerBase.END:

        case GroupHighlighterLexerBase.SUPER:
        case GroupHighlighterLexerBase.FIRST:
        case GroupHighlighterLexerBase.LAST:
        case GroupHighlighterLexerBase.REST:
        case GroupHighlighterLexerBase.TRUNC:
        case GroupHighlighterLexerBase.STRIP:
        case GroupHighlighterLexerBase.TRIM:
        case GroupHighlighterLexerBase.LENGTH:
        case GroupHighlighterLexerBase.STRLEN:
        case GroupHighlighterLexerBase.REVERSE:
            return keywordAttributes;

        case GroupHighlighterLexerBase.ID:
            return identifierAttributes;

        case GroupHighlighterLexerBase.LBRACE:
        case GroupHighlighterLexerBase.RBRACE:
            return anonymousTemplateDelimiterAttributes;

        case GroupHighlighterLexerBase.BIGSTRING:
        case GroupHighlighterLexerBase.BIGSTRINGLINE:
        case GroupHighlighterLexerBase.BigStringTemplate_END:
        case GroupHighlighterLexerBase.BigStringLineTemplate_END:
            return bigStringDelimiterAttributes;

        case GroupHighlighterLexerBase.STRING:
            return stringLiteralAttributes;

        case GroupHighlighterLexerBase.LINE_COMMENT:
        case GroupHighlighterLexerBase.COMMENT:
            return commentAttributes;

//        case GroupHighlighterLexerBase.WS:
//            return whitespaceAttributes;
        case GroupHighlighterLexerBase.OPEN_DELIMITER:
        case GroupHighlighterLexerBase.CLOSE_DELIMITER:
            return expressionDelimiterAttributes;

        case GroupHighlighterLexerBase.ESCAPE:
            return escapeTagAttributes;

        case GroupHighlighterLexerBase.AnonymousTemplate_ID:
        case GroupHighlighterLexerBase.AnonymousTemplate_COMMA:
        case GroupHighlighterLexerBase.TEXT:
            return stringLiteralAttributes;

        case GroupHighlighterLexerBase.REGION_ID:
            return regionUseAttributes;

        default:
            return null;
        }
    }

    @Override
    protected Collection<Highlight> getHighlightsForToken(Token token) {
//        if (token.getType() == GroupHighlighterLexer.LEGACY_DELIMITERS) {
//            throw new UnsupportedOperationException("Not implemented yet.");
//        } else {
            return super.getHighlightsForToken(token);
//        }
    }

//    public class TemplateEscapedCharStream extends DocumentCharStreamV4 {
//        private TemplateHighlighterLexer lexer;
//
//        public TemplateEscapedCharStream(StyledDocument document) {
//            super(document);
//        }
//
//        public TemplateHighlighterLexer getLexer() {
//            return lexer;
//        }
//
//        public void setLexer(TemplateHighlighterLexer value) {
//            lexer = value;
//        }
//
//        @Override
//        public void consume() {
//            boolean consumeEscape = shouldConsumeEscape(0);
//            if (consumeEscape) {
//                super.consume();
//            }
//
//            super.consume();
//        }
//
//        @Override
//        public int LA(int i) {
//            int escapeCount = 0;
//            if (i >= 1) {
//                for (int j = 0; j < i; j++) {
//                    if (shouldConsumeEscape(j + escapeCount)) {
//                        escapeCount++;
//                    }
//                }
//            }
//
//            return super.LA(i + escapeCount);
//        }
//
//        private boolean shouldConsumeEscape(int offset) {
//            if (getLexer() == null) {
//                return false;
//            }
//
//            if (super.LA(offset + 1) != '\\') {
//                return false;
//            }
//
//            boolean inString = getLexer().getOutermost() == OutermostTemplate.String;
//            if (inString) {
//                return super.LA(offset + 2) == '"';
//            }
//
//            boolean inBigString = getLexer().getOutermost() == OutermostTemplate.BigString
//                || getLexer().getOutermost() == OutermostTemplate.BigStringLine;
//            if (inBigString) {
//                return super.LA(offset + 2) == '>';
//            }
//
//            return false;
//        }
//    }
}
