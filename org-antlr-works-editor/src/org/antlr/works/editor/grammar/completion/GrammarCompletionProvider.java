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
package org.antlr.works.editor.grammar.completion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.antlr4.completion.AbstractCompletionProvider;
import org.antlr.works.editor.grammar.GoToSupport;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
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
public class GrammarCompletionProvider extends AbstractCompletionProvider {
    // -J-Dorg.antlr.works.editor.grammar.GrammarCompletionProvider.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GrammarCompletionProvider.class.getName());

    private static String grammarCompletionAutoPopupTriggers = "$";
    private static String grammarCompletionSelectors = "";

    @Override
    protected AsyncCompletionQuery createCompletionQuery(int queryType, int caretOffset, boolean extend) {
        return new GrammarCompletionQuery(this, queryType, caretOffset, true, extend);
    }

    @Override
    public boolean autoPopupOnIdentifierPart() {
        return true;
    }

    @Override
    public String getCompletionAutoPopupTriggers() {
        return grammarCompletionAutoPopupTriggers;
    }

    @Override
    public String getCompletionSelectors() {
        return grammarCompletionSelectors;
    }

    @Override
    public boolean isIdentifierPart(String text) {
        return GrammarCompletionQuery.isGrammarIdentifierPart(text);
    }

    @Override
    public Token getContext(Document document, int offset) {
        return GoToSupport.getContext(document, offset);
    }

    @Override
    public boolean isContext(JTextComponent component, int offset, int queryType) {
        return isContext(getContext(component, offset), offset, true, true);
    }

    public boolean isContext(JTextComponent component, int offset, boolean allowInStrings, boolean allowInActions) {
        return isContext(getContext(component, offset), offset, allowInStrings, allowInActions);
    }

    @Override
    public boolean isContext(Token token, int offset, int queryType) {
        return isContext(token, offset, true, true);
    }

    /*package*/ boolean isContext(Token token, int offset, boolean allowInStrings, boolean allowInActions) {
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
    }

    public static Collection<Description> getRulesFromGrammar(ParserTaskManager taskManager, DocumentSnapshot snapshot) {
        Description rootDescription = null;
        Future<ParserData<Description>> futureNavigatorRootData = taskManager.getData(snapshot, GrammarParserDataDefinitions.NAVIGATOR_ROOT, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
        try {
            ParserData<Description> data = futureNavigatorRootData.get();
            if (data != null) {
                rootDescription = data.getData();
            }
        } catch (InterruptedException ex) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "An exception occurred while parsing navigator data.", ex);
            }
        } catch (ExecutionException ex) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "An exception occurred while parsing navigator data.", ex);
            }
        }

        if (rootDescription != null) {
            List<Description> rules = new ArrayList<Description>();
            Queue<Description> workList = new ArrayDeque<Description>();
            workList.add(rootDescription);

            while (!workList.isEmpty()) {
                Description description = workList.remove();
                if (description.getOffset() > 0) {
                    rules.add(description);
                }

                if (description.getChildren() != null) {
                    workList.addAll(description.getChildren());
                }
            }

            return rules;
        }

        return Collections.emptyList();
    }
}
