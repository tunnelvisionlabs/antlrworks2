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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.highlighting.ANTLRHighlighterBaseV4;
import org.antlr.netbeans.editor.highlighting.TokenSourceWithStateV4;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.openide.text.NbDocument;
import org.openide.util.Lookup;

/**
 *
 * @author Sam Harwell
 */
public class GrammarHighlighter extends ANTLRHighlighterBaseV4<GrammarHighlighterLexerState> {
    public static final String DOCUMENT_PROPERTY = "grammar-highlighter";

    private final AttributeSet identifierAttributes;
    private final AttributeSet keywordAttributes;
    private final AttributeSet commentAttributes;
    private final AttributeSet stringLiteralAttributes;
    private final AttributeSet numberLiteralAttributes;
    private final AttributeSet symbolDefinitionAttributes;
    private final AttributeSet symbolReferenceAttributes;
    private final AttributeSet parserRuleAttributes;
    private final AttributeSet lexerRuleAttributes;
    private final AttributeSet astOperatorAttributes;
    private final AttributeSet directiveAttributes;
    private final AttributeSet validOptionAttributes;
    private final AttributeSet invalidOptionAttributes;
    private final AttributeSet actionLiteralAttributes;
    private final AttributeSet actionCommentAttributes;
    private final AttributeSet actionStringLiteralAttributes;
    private final AttributeSet actionSymbolReferenceAttributes;

    private boolean legacyMode;

    private GrammarHighlighterLexerWrapper lexer;

    @SuppressWarnings("LeakingThisInConstructor")
    public GrammarHighlighter(final StyledDocument document) {
        super(document);

        DocumentUtilities.addPropertyChangeListener(document, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt == null || evt.getPropertyName() == null || GrammarEditorKit.PROP_LEGACY_MODE.equals(evt.getPropertyName())) {
                    legacyMode = GrammarEditorKit.isLegacyMode(document);
                    GrammarHighlighter.this.forceRehighlightLines(0, NbDocument.findLineRootElement(document).getElementCount() - 1);
                }
            }
        });
        document.putProperty(DOCUMENT_PROPERTY, this);

        Lookup lookup = MimeLookup.getLookup(MimePath.parse(GrammarEditorKit.GRAMMAR_MIME_TYPE));
        FontColorSettings settings = lookup.lookup(FontColorSettings.class);
        identifierAttributes = getFontAndColors(settings, "identifier");
        keywordAttributes = getFontAndColors(settings, "keyword");
        commentAttributes = getFontAndColors(settings, "comment");
        stringLiteralAttributes = getFontAndColors(settings, "stringliteral");
        numberLiteralAttributes = getFontAndColors(settings, "number");
        symbolDefinitionAttributes = getFontAndColors(settings, "definition");
        symbolReferenceAttributes = getFontAndColors(settings, "reference");
        parserRuleAttributes = getFontAndColors(settings, "parserrule");
        lexerRuleAttributes = getFontAndColors(settings, "lexerrule");
        astOperatorAttributes = getFontAndColors(settings, "astoperator");
        directiveAttributes = getFontAndColors(settings, "directive");
        validOptionAttributes = getFontAndColors(settings, "validoption");
        invalidOptionAttributes = getFontAndColors(settings, "invalidoption");
        actionLiteralAttributes = getFontAndColors(settings, "actionliteral");
        actionCommentAttributes = getFontAndColors(settings, "actioncomment");
        actionStringLiteralAttributes = getFontAndColors(settings, "actionstringliteral");
        actionSymbolReferenceAttributes = getFontAndColors(settings, "actionreference");

        legacyMode = GrammarEditorKit.isLegacyMode(document);
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
    protected GrammarHighlighterLexerState getStartState() {
        return GrammarHighlighterLexerState.INITIAL;
    }

    @Override
    protected TokenSourceWithStateV4<GrammarHighlighterLexerState> createLexer(CharStream input, GrammarHighlighterLexerState startState) {
        if (lexer == null) {
            lexer = new GrammarHighlighterLexerWrapper(input, startState);
        } else {
            lexer.setState(input, startState);
        }

        return lexer;
    }

    @Override
    protected AttributeSet highlightToken(Token token) {
        switch (token.getType()) {
        // common keywords
        case GrammarHighlighterLexer.LEXER:
        case GrammarHighlighterLexer.PARSER:
        case GrammarHighlighterLexer.CATCH:
        case GrammarHighlighterLexer.FINALLY:
        case GrammarHighlighterLexer.GRAMMAR:
        case GrammarHighlighterLexer.PRIVATE:
        case GrammarHighlighterLexer.PROTECTED:
        case GrammarHighlighterLexer.PUBLIC:
        case GrammarHighlighterLexer.RETURNS  :
        case GrammarHighlighterLexer.THROWS:
        case GrammarHighlighterLexer.IMPORT:
        case GrammarHighlighterLexer.FRAGMENT :
        case GrammarHighlighterLexer.TOKENS:
        case GrammarHighlighterLexer.OPTIONS:
            return keywordAttributes;

        // v4 only keywords
        case GrammarHighlighterLexer.MODE:
        case GrammarHighlighterLexer.LOCALS:
            return !legacyMode ? keywordAttributes : getIdentifierAttributes(token.getText());

        // v3 only keywords
        case GrammarHighlighterLexer.TREE:
        case GrammarHighlighterLexer.SCOPE:
            return legacyMode ? keywordAttributes : getIdentifierAttributes(token.getText());

        case GrammarHighlighterLexer.IDENTIFIER:
            return getIdentifierAttributes(token.getText());

        case GrammarHighlighterLexer.LABEL:
            return symbolDefinitionAttributes;

        case GrammarHighlighterLexer.COMMENT:
        case GrammarHighlighterLexer.ML_COMMENT:
            return commentAttributes;

        case GrammarHighlighterLexer.CHAR_LITERAL:
        case GrammarHighlighterLexer.STRING_LITERAL:
//        case GrammarHighlighterLexer.DOUBLE_ANGLE_STRING_LITERAL:
            return stringLiteralAttributes;

        case GrammarHighlighterLexer.DIRECTIVE:
            return directiveAttributes;

        case GrammarHighlighterLexer.REFERENCE:
            return symbolReferenceAttributes;

        case GrammarHighlighterLexer.BANG:
        case GrammarHighlighterLexer.REWRITE:
        case GrammarHighlighterLexer.ROOT:
            return astOperatorAttributes;

        case GrammarHighlighterLexer.INT:
            return numberLiteralAttributes;

        case GrammarHighlighterLexer.Action_COMMENT:
        case GrammarHighlighterLexer.Action_ML_COMMENT:
            return actionCommentAttributes;

        case GrammarHighlighterLexer.ArgAction_TEXT:
        case GrammarHighlighterLexer.Action_TEXT:
            return actionLiteralAttributes;

        case GrammarHighlighterLexer.ArgAction_CHAR_LITERAL:
        case GrammarHighlighterLexer.Action_CHAR_LITERAL:
        case GrammarHighlighterLexer.ArgAction_STRING_LITERAL:
        case GrammarHighlighterLexer.Action_STRING_LITERAL:
            return actionStringLiteralAttributes;

        case GrammarHighlighterLexer.ArgAction_REFERENCE:
        case GrammarHighlighterLexer.Action_REFERENCE:
            return actionSymbolReferenceAttributes;

        default:
            return null;
        }
    }

    private AttributeSet getIdentifierAttributes(String text) {
        if (Character.isLowerCase(text.charAt(0))) {
            return parserRuleAttributes;
        } else {
            return lexerRuleAttributes;
        }
    }

}
