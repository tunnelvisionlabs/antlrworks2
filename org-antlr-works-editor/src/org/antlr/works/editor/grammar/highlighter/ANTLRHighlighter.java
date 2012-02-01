/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.highlighter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyledDocument;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.works.editor.antlr3.highlighting.ANTLRHighlighterBase;
import org.antlr.works.editor.antlr3.highlighting.TokenSourceWithState;
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
public class ANTLRHighlighter extends ANTLRHighlighterBase<ANTLRHighlighterState> {
    public static final String DOCUMENT_PROPERTY = "grammar-highlighter";

    private static final HashSet<String> LEGACY_KEYWORDS =
        new HashSet<String>()
        {{
            add("lexer");
            add("parser");
            add("catch");
            add("finally");
            add("grammar");
            add("private");
            add("protected");
            add("public");
            add("returns");
            add("throws");
            add("tree");
            add("scope");
            add("import");
            add("fragment");
            add("tokens");
            add("options");
        }};

    private static final HashSet<String> KEYWORDS =
        new HashSet<String>(LEGACY_KEYWORDS)
        {{
            add("mode");
            add("locals");
        }};

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

    @SuppressWarnings("LeakingThisInConstructor")
    public ANTLRHighlighter(final StyledDocument document) {
        super(document);

        DocumentUtilities.addPropertyChangeListener(document, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt == null || evt.getPropertyName() == null || GrammarEditorKit.PROP_LEGACY_MODE.equals(evt.getPropertyName())) {
                    legacyMode = GrammarEditorKit.isLegacyMode(document);
                    ANTLRHighlighter.this.forceRehighlightLines(0, NbDocument.findLineRootElement(document).getElementCount() - 1);
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

    public static HashSet<String> getLegacyKeywords() {
        return LEGACY_KEYWORDS;
    }

    public static Set<String> getKeywords() {
        return Collections.unmodifiableSet(KEYWORDS);
    }

    private static AttributeSet getFontAndColors(FontColorSettings settings, String category) {
        AttributeSet attributes = settings.getTokenFontColors(category);
        return attributes;
    }

    @Override
    protected ANTLRHighlighterState getStartState() {
        return ANTLRHighlighterState.INITIAL;
    }

    @Override
    protected TokenSourceWithState<ANTLRHighlighterState> createLexer(CharStream input, ANTLRHighlighterState state) {
        return new ANTLRHighlighterLexer(input, state);
    }

    @Override
    protected AttributeSet highlightToken(CommonToken token) {
        switch (token.getType()) {
        case GrammarHighlighterLexer.IDENTIFIER:
            String text = token.getText();
            Set<String> keywords = legacyMode ? LEGACY_KEYWORDS : KEYWORDS;
            if (keywords.contains(text)) {
                return keywordAttributes;
            }
            
            if (Character.isLowerCase(text.charAt(0))) {
                return parserRuleAttributes;
            } else {
                return lexerRuleAttributes;
            }
            
        case GrammarHighlighterLexer.ValidGrammarOption:
            return validOptionAttributes;
            
        case GrammarHighlighterLexer.InvalidGrammarOption:
            return invalidOptionAttributes;
            
        case GrammarHighlighterLexer.OptionValue:
            return identifierAttributes;
            
        case GrammarHighlighterLexer.LABEL:
            return symbolDefinitionAttributes;
            
        case GrammarHighlighterLexer.COMMENT:
        case GrammarHighlighterLexer.ML_COMMENT:
            return commentAttributes;
            
        case GrammarHighlighterLexer.CHAR_LITERAL:
        case GrammarHighlighterLexer.STRING_LITERAL:
        case GrammarHighlighterLexer.DOUBLE_ANGLE_STRING_LITERAL:
            return stringLiteralAttributes;
            
        case ActionHighlighterLexer.ACTION_COMMENT:
        case ActionHighlighterLexer.ACTION_ML_COMMENT:
            return actionCommentAttributes;
            
        case ActionHighlighterLexer.ACTION:
        case ActionHighlighterLexer.ARG_ACTION:
        case ActionHighlighterLexer.ACTION_TEXT:
        case ActionHighlighterLexer.LBRACK:
        case ActionHighlighterLexer.RBRACK:
            return actionLiteralAttributes;
            
        case ActionHighlighterLexer.ACTION_CHAR_LITERAL:
        case ActionHighlighterLexer.ACTION_STRING_LITERAL:
            return actionStringLiteralAttributes;

        case ActionHighlighterLexer.ACTION_REFERENCE:
            return actionSymbolReferenceAttributes;

        case ActionHighlighterLexer.DIRECTIVE:
            return directiveAttributes;

        case ActionHighlighterLexer.REFERENCE:
            return symbolReferenceAttributes;
            
        case ActionHighlighterLexer.BANG:
        case ActionHighlighterLexer.REWRITE:
        case ActionHighlighterLexer.ROOT:
            return astOperatorAttributes;

        case ActionHighlighterLexer.INT:
            return numberLiteralAttributes;

        default:
            return null;
        }
    }
}
