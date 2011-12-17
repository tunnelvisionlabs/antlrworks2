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
package org.antlr.works.editor.grammar.completion;

import java.util.EnumSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.tagging.TaggedPositionRegion;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.NormalizedSnapshotPositionRegionCollection;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.editor.Utilities;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=CompletionProvider.class)
@NbBundle.Messages({
    "GCP-imported-items=",
    "GCP-instance-members="
})
public class GrammarCompletionProvider implements CompletionProvider {
    // -J-Dorg.antlr.works.editor.grammar.GrammarCompletionProvider.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GrammarCompletionProvider.class.getName());

    public static final int AUTO_QUERY_TYPE = 0x0100;

    private static String grammarCompletionAutoPopupTriggers = "$";
    private static String grammarCompletionSelectors = "";

    @Override
    public int getAutoQueryTypes(JTextComponent component, String typedText) {
        if (typedText != null && typedText.length() == 1
            && (getGrammarCompletionAutoPopupTriggers().indexOf(typedText.charAt(0)) >= 0
            || (autoPopupOnGrammarIdentifierPart() && GrammarCompletionQuery.isGrammarIdentifierPart(typedText)))) {

            boolean allowInStrings = false;
            boolean allowInActions = getGrammarCompletionAutoPopupTriggers().indexOf(typedText.charAt(0)) >= 0;
            if (isGrammarContext(component, component.getSelectionStart() - 1, allowInStrings, allowInActions)) {
                return COMPLETION_QUERY_TYPE | AUTO_QUERY_TYPE;
            }
        }

        return 0;
    }

    @Override
    public CompletionTask createTask(int queryType, JTextComponent component) {
        if ((queryType & COMPLETION_QUERY_TYPE) != 0 || (queryType & TOOLTIP_QUERY_TYPE) != 0 || (queryType & DOCUMENTATION_QUERY_TYPE) != 0) {
            int caretOffset = component.getSelectionStart();
            boolean extend = false;
            try {
                int[] identifier = Utilities.getIdentifierBlock(component, caretOffset);
                extend = identifier != null && caretOffset > identifier[0] && caretOffset <= identifier[1];
            } catch (BadLocationException ex) {
                Exceptions.printStackTrace(ex);
            }

            return new AsyncCompletionTask(new GrammarCompletionQuery(queryType, caretOffset, true, extend), component);
        }

        return null;
    }

    public static boolean autoPopupOnGrammarIdentifierPart() {
        return true;
    }

    public static String getGrammarCompletionAutoPopupTriggers() {
        return grammarCompletionAutoPopupTriggers;
    }

    public static String getGrammarCompletionSelectors() {
        return grammarCompletionSelectors;
    }

    /*package*/ static boolean isGrammarContext(JTextComponent component, int offset, boolean allowInStrings, boolean allowInActions) {
        Document document = component.getDocument();
        if (document instanceof AbstractDocument) {
            ((AbstractDocument)document).readLock();
        }
        try {
//            try {
                ParserTaskManager taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
                DocumentSnapshot snapshot = VersionedDocumentUtilities.getVersionedDocument(document).getCurrentSnapshot();
                Future<ParserData<Tagger<TokenTag>>> futureTokensData = taskManager.getData(snapshot, GrammarParserDataDefinitions.LEXER_TOKENS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
                Tagger<TokenTag> tagger;
                try {
                    tagger = futureTokensData.get().getData();
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                    return false;
                } catch (ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                    return false;
                }

                // get the token(s) at the cursor position, with affinity both directions
                OffsetRegion region = OffsetRegion.fromBounds(Math.max(0, offset - 1), Math.min(snapshot.length(), offset + 1));
                Iterable<TaggedPositionRegion<TokenTag>> tags = tagger.getTags(new NormalizedSnapshotPositionRegionCollection(new SnapshotPositionRegion(snapshot, region)));

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
                for (TaggedPositionRegion<TokenTag> taggedRegion : tags) {
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
                    for (TaggedPositionRegion<TokenTag> taggedRegion : tags) {
                        token = taggedRegion.getTag().getToken();
                        if (token.getStartIndex() <= offset && token.getStopIndex() >= offset) {
                            break;
                        }
                    }
                }

                if (token == null) {
                    return false;
                }

                switch (token.getType()) {
                case GrammarLexer.ACTION_COMMENT:
                    return false;

                case GrammarLexer.STRING_LITERAL:
                case GrammarLexer.DOUBLE_QUOTE_STRING_LITERAL:
                case GrammarLexer.DOUBLE_ANGLE_STRING_LITERAL:
                    return allowInStrings;

                case GrammarLexer.ARG_ACTION_WORD:
                case GrammarLexer.ACTION_WORD:
                    return allowInActions;

                case GrammarLexer.WS:
                    return true;

                default:
                    return token.getChannel() == Lexer.DEFAULT_TOKEN_CHANNEL;
                }

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
