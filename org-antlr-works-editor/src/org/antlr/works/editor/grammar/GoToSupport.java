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
package org.antlr.works.editor.grammar;

import java.awt.event.ActionEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import javax.swing.Action;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.DocumentLine;
import org.antlr.netbeans.editor.DocumentPoint;
import org.antlr.netbeans.editor.DocumentSpan;
import org.antlr.netbeans.editor.highlighting.ANTLRHighlighterBase;
import org.antlr.netbeans.editor.highlighting.Highlight;
import org.antlr.netbeans.editor.highlighting.HighlightsList;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.works.editor.grammar.highlighter.ANTLRHighlighter;
import org.antlr.works.editor.grammar.highlighter.ActionHighlighterLexer;
import org.antlr.works.editor.grammar.highlighter.GrammarHighlighterLexer;
import org.antlr.works.editor.grammar.navigation.GrammarNode;
import org.antlr.works.editor.grammar.navigation.GrammarNode.Description;
import org.antlr.works.editor.grammar.navigation.GrammarRulesPanel;
import org.antlr.works.editor.grammar.navigation.GrammarRulesPanelUI;
import org.antlr.works.editor.grammar.navigation.actions.OpenAction;
import org.netbeans.spi.editor.highlighting.HighlightsSequence;
import org.openide.explorer.ExplorerManager;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public class GoToSupport {

    private static FileObject getFileObject(Document document) {
        DataObject dataObject = (DataObject)document.getProperty(Document.StreamDescriptionProperty);
        return dataObject != null ? dataObject.getPrimaryFile() : null;
    }

    public static String getGoToElementToolTip(StyledDocument document, int offset, boolean goToSource, String key) {
        DocumentSpan span = getIdentifierSpan(document, offset);
        if (span == null) {
            return "";
        }

        String text = span.getText();
        if (text.length() == 0) {
            return "";
        }

        if (text.charAt(0) == '$') {
            return "reference";
        } else if (Character.isUpperCase(text.charAt(0))) {
            return "lexer rule " + text;
        } else {
            return "parser rule " + text;
        }
    }

    public static void goTo(StyledDocument document, int offset, boolean goToSource) {
        GrammarRulesPanelUI ui = GrammarRulesPanel.findGrammarRulesPanelUI();
        if (ui == null) {
            return;
        }

        DocumentSpan span = getIdentifierSpan(document, offset);
        if (span == null) {
            return;
        }

        // try to find the name in the navigator
        ExplorerManager explorerManager = ui.getExplorerManager();
        if (explorerManager == null) {
            return;
        }

        Node rootContext = explorerManager.getRootContext();
        if (!(rootContext instanceof GrammarNode)) {
            return;
        }

        String text = span.getText();
        Description target = null;
        Queue<Description> workList = new ArrayDeque<Description>();
        workList.add(((GrammarNode)rootContext).getDescription());
        while (!workList.isEmpty()) {
            Description description = workList.remove();
            if (description.getName() != null && description.getName().equals(text)) {
                target = description;
                break;
            }

            if (description.getChildren() != null) {
                workList.addAll(description.getChildren());
            }
        }

        if (target == null) {
            return;
        }

        OpenAction openAction = new OpenAction(target);
        openAction.actionPerformed(new ActionEvent(target, 0, openAction.getValue(Action.NAME).toString()));
    }

    public static DocumentSpan getIdentifierSpan(StyledDocument document, int offset) {
        Parameters.notNull("document", document);

        if (getFileObject(document) == null) {
            //do nothing if FO is not attached to the document - the goto would not work anyway:
            return null;
        }

        DocumentPoint point;
        try {
            point = new DocumentPoint(document, offset);
        } catch (BadLocationException ex) {
            return null;
        }

        DocumentLine line = point.getContainingLine();
        List<Token> tokens = getTokens(document, line.getExtent());
        if (tokens == null) {
            return null;
        }

        for (Token token : tokens) {
            if (!(token instanceof CommonToken)) {
                continue;
            }

            CommonToken commonToken = (CommonToken)token;
            DocumentSpan span;
            try {
                span = new DocumentSpan(document, commonToken.getStartIndex(), commonToken.getStopIndex() + 1);
            } catch (BadLocationException ex) {
                throw new IllegalStateException("The highlights sequence should not contain invalid positions.", ex);
            }

            if (!span.contains(point)) {
                continue;
            }

            switch (token.getType()) {
            case GrammarHighlighterLexer.ParserRule:
            case GrammarHighlighterLexer.LexerRule:
            case GrammarHighlighterLexer.IDENTIFIER:
            case GrammarHighlighterLexer.REFERENCE:
            case ActionHighlighterLexer.ACTION_REFERENCE:
                return span;

            default:
                return null;
            }
        }

        return null;
    }

    private static HighlightsSequence getHighlights(StyledDocument document, DocumentSpan span) {
        Parameters.notNull("document", document);
        Parameters.notNull("span", span);

        List<Highlight> highlights = new ArrayList<Highlight>();
        if (!getHighlights(document, span, highlights, null)) {
            return null;
        }

        return new HighlightsList(highlights);
    }

    private static List<Token> getTokens(StyledDocument document, DocumentSpan span) {
        Parameters.notNull("document", document);
        Parameters.notNull("span", span);

        List<Token> tokens = new ArrayList<Token>();
        if (!getHighlights(document, span, null, tokens)) {
            return null;
        }

        return tokens;
    }

    private static boolean getHighlights(StyledDocument document, DocumentSpan span, List<Highlight> highlights, List<Token> tokens) {
        Parameters.notNull("document", document);
        Parameters.notNull("span", span);

        ANTLRHighlighterBase<?> highlighter = getHighlighter(document);
        if (highlighter == null) {
            return false;
        }

        highlighter.getHighlights(span.getStart().getOffset(), span.getEnd().getOffset(), highlights, tokens, false);
        return true;
    }

    private static ANTLRHighlighterBase<?> getHighlighter(StyledDocument document) {
        Parameters.notNull("document", document);

        Object object = document.getProperty(ANTLRHighlighter.DOCUMENT_PROPERTY);
        if (!(object instanceof ANTLRHighlighterBase)) {
            return null;
        }

        return (ANTLRHighlighterBase<?>)object;
    }

}
