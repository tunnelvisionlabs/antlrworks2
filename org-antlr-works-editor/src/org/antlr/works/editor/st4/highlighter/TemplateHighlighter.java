/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.highlighter;

import java.util.Collection;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.highlighting.Highlight;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.antlr4.highlighting.ANTLRHighlighterBaseV4;
import org.antlr.works.editor.antlr4.highlighting.TokenSourceWithStateV4;
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
        case GroupHighlighterLexer.DEFAULT:
        case GroupHighlighterLexer.IMPORT:
        case GroupHighlighterLexer.GROUP:
        case GroupHighlighterLexer.TRUE:
        case GroupHighlighterLexer.FALSE:
        case GroupHighlighterLexer.DELIMITERS:
        case GroupHighlighterLexer.IF:
        case GroupHighlighterLexer.ELSEIF:
        case GroupHighlighterLexer.ELSE:
        case GroupHighlighterLexer.ENDIF:
        case GroupHighlighterLexer.END:

        case GroupHighlighterLexer.SUPER:
        case GroupHighlighterLexer.FIRST:
        case GroupHighlighterLexer.LAST:
        case GroupHighlighterLexer.REST:
        case GroupHighlighterLexer.TRUNC:
        case GroupHighlighterLexer.STRIP:
        case GroupHighlighterLexer.TRIM:
        case GroupHighlighterLexer.LENGTH:
        case GroupHighlighterLexer.STRLEN:
        case GroupHighlighterLexer.REVERSE:
            return keywordAttributes;

        case GroupHighlighterLexer.ID:
            return identifierAttributes;

        case GroupHighlighterLexer.LBRACE:
        case GroupHighlighterLexer.RBRACE:
            return anonymousTemplateDelimiterAttributes;

        case GroupHighlighterLexer.BIGSTRING:
        case GroupHighlighterLexer.BIGSTRINGLINE:
        case GroupHighlighterLexer.BigStringTemplate_END:
        case GroupHighlighterLexer.BigStringLineTemplate_END:
            return bigStringDelimiterAttributes;

        case GroupHighlighterLexer.STRING:
            return stringLiteralAttributes;

        case GroupHighlighterLexer.LINE_COMMENT:
        case GroupHighlighterLexer.COMMENT:
            return commentAttributes;

//        case GroupHighlighterLexer.WS:
//            return whitespaceAttributes;
        case GroupHighlighterLexer.OPEN_DELIMITER:
        case GroupHighlighterLexer.CLOSE_DELIMITER:
            return expressionDelimiterAttributes;

        case GroupHighlighterLexer.ESCAPE:
            return escapeTagAttributes;

        case GroupHighlighterLexer.AnonymousTemplate_ID:
        case GroupHighlighterLexer.AnonymousTemplate_COMMA:
        case GroupHighlighterLexer.TEXT:
            return stringLiteralAttributes;

        case GroupHighlighterLexer.REGION_ID:
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
