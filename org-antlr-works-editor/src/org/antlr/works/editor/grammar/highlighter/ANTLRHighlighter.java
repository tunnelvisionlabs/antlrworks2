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
package org.antlr.works.editor.grammar.highlighter;

import java.util.HashSet;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.highlighting.ANTLRHighlighterBase;
import org.antlr.netbeans.editor.highlighting.TokenSourceWithState;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.openide.util.Lookup;

public class ANTLRHighlighter extends ANTLRHighlighterBase<ANTLRHighlighterState> {
    public static final String DOCUMENT_PROPERTY = "grammar-highlighter";

    private static final HashSet<String> keywords =
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

    public ANTLRHighlighter(StyledDocument document) {
        super(document);

        document.putProperty(DOCUMENT_PROPERTY, this);

        Lookup lookup = MimeLookup.getLookup(MimePath.parse("text/x-antlr3"));
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
