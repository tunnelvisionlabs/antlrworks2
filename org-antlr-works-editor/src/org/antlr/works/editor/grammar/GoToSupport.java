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
package org.antlr.works.editor.grammar;

import java.awt.event.ActionEvent;
import java.util.Collection;
import javax.swing.Action;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.DocumentSpan;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.navigation.actions.OpenAction;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.completion.GrammarCompletionProvider;
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class GoToSupport {

    private GoToSupport() {
    }

    private static FileObject getFileObject(Document document) {
        DataObject dataObject = (DataObject)document.getProperty(Document.StreamDescriptionProperty);
        return dataObject != null ? dataObject.getPrimaryFile() : null;
    }

    public static String getGoToElementToolTip(StyledDocument document, int offset, boolean goToSource, String key) {
        Token token = GrammarCompletionProvider.getGrammarContext(document, offset);
        if (token == null) {
            return "";
        }

        switch (token.getType()) {
        case GrammarParser.RULE_REF:
        case GrammarParser.TOKEN_REF:
        case GrammarParser.ACTION_REFERENCE:
            break;

        case GrammarLexer.ARG_ACTION_WORD:
            if (token.getText().charAt(0) != '$') {
                return "";
            }

            break;

        default:
            return "";
        }

        String text = token.getText();
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
        Token token = GrammarCompletionProvider.getGrammarContext(document, offset);
        if (token == null) {
            return;
        }

        String ruleName;
        switch (token.getType()) {
        case GrammarParser.RULE_REF:
        case GrammarParser.TOKEN_REF:
            ruleName = token.getText();
            break;
            
        default:
            return;
        }

        ParserTaskManager taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
        VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(document);
        Collection<Description> rules = GrammarCompletionProvider.getRulesFromGrammar(taskManager, versionedDocument.getCurrentSnapshot());

        Description target = null;
        for (Description rule : rules) {
            if (rule.getName() != null && rule.getName().equals(ruleName)) {
                target = rule;
                break;
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

        Token token = GrammarCompletionProvider.getGrammarContext(document, offset);
        if (token == null) {
            return null;
        }

        switch (token.getType()) {
        case GrammarParser.RULE_REF:
        case GrammarParser.TOKEN_REF:
        case GrammarParser.ACTION_REFERENCE:
        case GrammarParser.ARG_ACTION_WORD:
            break;

        default:
            return null;
        }

        try {
            return new DocumentSpan(document, token.getStartIndex(), token.getStopIndex() + 1);
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
    }

}
