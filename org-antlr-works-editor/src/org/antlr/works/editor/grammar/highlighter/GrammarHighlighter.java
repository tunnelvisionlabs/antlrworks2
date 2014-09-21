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
import java.util.Collection;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.tool.Grammar;
import org.antlr.works.editor.antlr4.highlighting.ANTLRHighlighterBaseV4;
import org.antlr.works.editor.antlr4.highlighting.TokenSourceWithStateV4;
import org.antlr.works.editor.grammar.GoToSupport;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.completion.GrammarCompletionProvider;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.settings.AttributesUtilities;
import org.netbeans.api.editor.settings.EditorStyleConstants;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.netbeans.spi.editor.highlighting.HighlightAttributeValue;
import org.openide.text.NbDocument;
import org.openide.util.Lookup;

/**
 *
 * @author Sam Harwell
 */
public class GrammarHighlighter extends ANTLRHighlighterBaseV4<GrammarHighlighterLexerState> {
    public static final String DOCUMENT_PROPERTY = "grammar-highlighter";
    private static final AttributeSet TOOLTIP =
        AttributesUtilities.createImmutable(EditorStyleConstants.Tooltip, new TooltipResolver());

    private final AttributeSet identifierAttributes;
    private final AttributeSet keywordAttributes;
    private final AttributeSet commentAttributes;
    private final AttributeSet stringLiteralAttributes;
    private final AttributeSet stringLiteralEscapeAttributes;
    private final AttributeSet stringLiteralEscapeInvalidAttributes;
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
    private final AttributeSet actionLiteralEscapeAttributes;
    private final AttributeSet actionCommentAttributes;
    private final AttributeSet actionStringLiteralAttributes;
    private final AttributeSet actionStringLiteralEscapeAttributes;
    private final AttributeSet actionSymbolReferenceAttributes;

    private boolean legacyMode;

    private GrammarHighlighterLexerWrapper lexerWrapper;

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
        identifierAttributes = getFontAndColors(settings, "identifier", true);
        keywordAttributes = getFontAndColors(settings, "keyword");
        commentAttributes = getFontAndColors(settings, "comment");
        stringLiteralAttributes = getFontAndColors(settings, "stringliteral", true);
        stringLiteralEscapeAttributes = getFontAndColors(settings, "stringliteralescape", true);
        stringLiteralEscapeInvalidAttributes = getFontAndColors(settings, "stringliteralescapeinvalid", true);
        numberLiteralAttributes = getFontAndColors(settings, "number");
        symbolDefinitionAttributes = getFontAndColors(settings, "definition", true);
        symbolReferenceAttributes = getFontAndColors(settings, "reference");
        parserRuleAttributes = getFontAndColors(settings, "parserrule", true);
        lexerRuleAttributes = getFontAndColors(settings, "lexerrule", true);
        astOperatorAttributes = getFontAndColors(settings, "astoperator");
        directiveAttributes = getFontAndColors(settings, "directive");
        validOptionAttributes = getFontAndColors(settings, "validoption");
        invalidOptionAttributes = getFontAndColors(settings, "invalidoption");
        actionLiteralAttributes = getFontAndColors(settings, "actionliteral");
        actionLiteralEscapeAttributes = getFontAndColors(settings, "actionliteralescape");
        actionCommentAttributes = getFontAndColors(settings, "actioncomment");
        actionStringLiteralAttributes = getFontAndColors(settings, "actionstringliteral");
        actionStringLiteralEscapeAttributes = getFontAndColors(settings, "actionstringliteralescape");
        actionSymbolReferenceAttributes = getFontAndColors(settings, "actionreference");

        legacyMode = GrammarEditorKit.isLegacyMode(document);
    }

    private static AttributeSet getFontAndColors(FontColorSettings settings, String category) {
        return getFontAndColors(settings, category, false);
    }

    private static AttributeSet getFontAndColors(FontColorSettings settings, String category, boolean tooltip) {
        AttributeSet attributes = settings.getTokenFontColors(category);
        if (tooltip) {
            attributes = AttributesUtilities.createComposite(attributes, TOOLTIP);
        }

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
        if (lexerWrapper == null) {
            lexerWrapper = new GrammarHighlighterLexerWrapper(input, startState);
        } else {
            lexerWrapper.setState(input, startState);
        }

        return lexerWrapper;
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
        case GrammarHighlighterLexer.CHANNELS:
            return !legacyMode ? keywordAttributes : getIdentifierAttributes(lexerWrapper.getLexer(), token.getText());

        // v3 only keywords
        case GrammarHighlighterLexer.TEMPLATE:
        case GrammarHighlighterLexer.TREE:
        case GrammarHighlighterLexer.SCOPE:
            return legacyMode ? keywordAttributes : getIdentifierAttributes(lexerWrapper.getLexer(), token.getText());

        case GrammarHighlighterLexer.IDENTIFIER:
            return getIdentifierAttributes(lexerWrapper.getLexer(), token.getText());

        case GrammarHighlighterLexer.LABEL:
            return symbolDefinitionAttributes;

        case GrammarHighlighterLexer.COMMENT:
        case GrammarHighlighterLexer.ML_COMMENT:
            return commentAttributes;

        case GrammarHighlighterLexer.CHAR_LITERAL:
        case GrammarHighlighterLexer.STRING_LITERAL:
        case GrammarHighlighterLexer.LexerCharSet_TEXT:
            return stringLiteralAttributes;

        case GrammarHighlighterLexer.LexerCharSet_ESCAPE:
            return stringLiteralEscapeAttributes;

        case GrammarHighlighterLexer.LexerCharSet_INVALID_ESCAPE:
            return stringLiteralEscapeInvalidAttributes;

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

        case GrammarHighlighterLexer.Action_ESCAPE:
            return actionLiteralEscapeAttributes;

        case GrammarHighlighterLexer.ArgAction_CHAR_LITERAL:
        case GrammarHighlighterLexer.Action_CHAR_LITERAL:
        case GrammarHighlighterLexer.ArgAction_STRING_LITERAL:
        case GrammarHighlighterLexer.Action_STRING_LITERAL:
            return actionStringLiteralAttributes;

        case GrammarHighlighterLexer.ArgAction_REFERENCE:
        case GrammarHighlighterLexer.Action_REFERENCE:
            return actionSymbolReferenceAttributes;

        case GrammarHighlighterLexer.ValidGrammarOption:
            return validOptionAttributes;

        case GrammarHighlighterLexer.InvalidGrammarOption:
            return invalidOptionAttributes;

        default:
            return null;
        }
    }

    private AttributeSet getIdentifierAttributes(GrammarHighlighterLexer lexer, String text) {
        if (lexer.isInOptions()) {
            return identifierAttributes;
        }

        if (Grammar.isTokenName(text)) {
            return lexerRuleAttributes;
        } else {
            return parserRuleAttributes;
        }
    }

    private static final class TooltipResolver implements HighlightAttributeValue<String> {

        @Override
        public String getValue(JTextComponent component, Document document, Object attributeKey, int startOffset, int endOffset) {
            Token token = GoToSupport.getContext(document, startOffset);
            if (token == null) {
                return "";
            }

            String ruleName;
            switch (token.getType()) {
            case GrammarParser.RULE_REF:
            case GrammarParser.TOKEN_REF:
                ruleName = token.getText();
                break;

            default:
                return "";
            }

            ParserTaskManager taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
            VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(document);
            Collection<Description> rules = GrammarCompletionProvider.getRulesFromGrammar(taskManager, versionedDocument.getCurrentSnapshot(), false);

            Description target = null;
            for (Description rule : rules) {
                if (rule.getName() != null && rule.getName().equals(ruleName)) {
                    target = rule;
                    break;
                }
            }

            if (target == null) {
                return "";
            }

            SnapshotPositionRegion region = target.getSpan();
            if (region != null) {
                TrackingPositionRegion trackingRegion = region.getSnapshot().createTrackingRegion(region.getRegion(), TrackingPositionRegion.Bias.Forward);
                return trackingRegion.getText(versionedDocument.getCurrentSnapshot());
            }

            String result = target.getHtmlHeader();
            if (result == null) {
                result = target.getName();
            }

            return result;
        }

    }

}
