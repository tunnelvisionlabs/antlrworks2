/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.EnumSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.Action;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.DocumentSpan;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.navigation.actions.OpenAction;
import org.antlr.netbeans.editor.tagging.TaggedPositionRegion;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.NormalizedSnapshotPositionRegionCollection;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.tool.Grammar;
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
        Token token = getContext(document, offset);
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
        } else if (Grammar.isTokenName(text)) {
            return "lexer rule " + text;
        } else {
            return "parser rule " + text;
        }
    }

    public static void goTo(StyledDocument document, int offset, boolean goToSource) {
        Token token = getContext(document, offset);
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
        Collection<Description> rules = GrammarCompletionProvider.getRulesFromGrammar(taskManager, versionedDocument.getCurrentSnapshot(), false);

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

        Token token = getContext(document, offset);
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

    public static Token getContext(Document document, int offset) {
        if (document instanceof AbstractDocument) {
            ((AbstractDocument)document).readLock();
        }

        try {
//            try {
                ParserTaskManager taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
                DocumentSnapshot snapshot = VersionedDocumentUtilities.getVersionedDocument(document).getCurrentSnapshot();
                Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = taskManager.getData(snapshot, GrammarParserDataDefinitions.LEXER_TOKENS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
                Tagger<TokenTag<Token>> tagger;
                try {
                    tagger = futureTokensData.get().getData();
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                    return null;
                } catch (ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                    return null;
                }

                if (tagger == null) {
                    return null;
                }

                // get the token(s) at the cursor position, with affinity both directions
                OffsetRegion region = OffsetRegion.fromBounds(Math.max(0, offset - 1), Math.min(snapshot.length(), offset + 1));
                Iterable<TaggedPositionRegion<TokenTag<Token>>> tags = tagger.getTags(new NormalizedSnapshotPositionRegionCollection(new SnapshotPositionRegion(snapshot, region)));

                // TODO: cache tokens
//                ANTLRStringStream input = new ANTLRStringStream(document.getText(0, document.getLength()));
//                GrammarLexer lexer = new GrammarLexer(input);
//                CommonTokenStream tokenStream = new CommonTokenStream(lexer);
                Token token = null;
//                for (token = tokenStream.LT(1); token != null && token.getType() != Token.EOF; token = tokenStream.LT(1)) {
//                    tokenStream.consume();
//                    if (token.getStartIndex() <= offset && token.getStopIndex() >= offset) {
//                        break;
//                    }
//                }
                for (TaggedPositionRegion<TokenTag<Token>> taggedRegion : tags) {
                    if (taggedRegion.getTag().getToken().getChannel() != Lexer.DEFAULT_TOKEN_CHANNEL) {
                        continue;
                    }

                    token = taggedRegion.getTag().getToken();
                    if (token.getStartIndex() <= offset && token.getStopIndex() >= offset) {
                        break;
                    }
                }

                if (token == null) {
                    // try again without skipping off-channel tokens
                    for (TaggedPositionRegion<TokenTag<Token>> taggedRegion : tags) {
                        token = taggedRegion.getTag().getToken();
                        if (token.getStartIndex() <= offset && token.getStopIndex() >= offset) {
                            break;
                        }
                    }
                }

                return token;
                //List<Token> tokens;
//            } catch (BadLocationException ex) {
//                Exceptions.printStackTrace(ex);
//                return false;
//            }
        } finally {
            if (document instanceof AbstractDocument) {
                ((AbstractDocument)document).readUnlock();
            }
        }
    }

}
