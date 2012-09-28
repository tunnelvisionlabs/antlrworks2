/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.antlr4.completion.AbstractCompletionProvider;
import org.antlr.works.editor.antlr4.completion.AbstractCompletionQuery;
import org.antlr.works.editor.grammar.GoToSupport;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.codemodel.LexerRuleModel;
import org.antlr.works.editor.grammar.codemodel.ModeModel;
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.antlr.works.editor.grammar.codemodel.TokenData;
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.antlr.works.editor.grammar.navigation.GrammarNode;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.completion.CompletionProvider;
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
    private static String grammarCompletionSelectors = " :;[]<>";

    @Override
    protected AbstractCompletionQuery createCompletionQuery(int queryType, int caretOffset, boolean extend) {
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
        boolean allowInStrings = false;
        boolean allowInActions = (queryType & TRIGGERED_QUERY_TYPE) != 0;
        return isContext(token, offset, allowInStrings, allowInActions);
    }

    /*package*/ boolean isContext(Token token, int offset, boolean allowInStrings, boolean allowInActions) {
        if (token == null) {
            return false;
        }

        switch (token.getType()) {
        case GrammarLexer.LEXER_CHAR_SET:
        case GrammarLexer.ACTION_COMMENT:
            return false;

        case GrammarLexer.STRING_LITERAL:
        case GrammarLexer.DOUBLE_QUOTE_STRING_LITERAL:
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

    public static Collection<Description> getRulesFromGrammar(ParserTaskManager taskManager, DocumentSnapshot snapshot, boolean ignoreLexerOnlyRules) {
        Map<String, Description> rules = new HashMap<String, Description>();

        Description rootDescription = GrammarParserDataDefinitions.tryGetData(taskManager, snapshot, GrammarParserDataDefinitions.NAVIGATOR_ROOT, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
        if (rootDescription != null) {
            Queue<Description> workList = new ArrayDeque<Description>();
            workList.add(rootDescription);

            while (!workList.isEmpty()) {
                Description description = workList.remove();
                if (description.getOffset() > 0) {
                    rules.put(description.getName(), description);
                }

                if (description.getChildren() != null) {
                    workList.addAll(description.getChildren());
                }
            }
        }

        FileModel fileModel = GrammarParserDataDefinitions.tryGetData(taskManager, snapshot, GrammarParserDataDefinitions.FILE_MODEL, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
        if (fileModel != null) {
            for (RuleModel ruleModel : fileModel.getRules()) {
                if (ignoreLexerOnlyRules && ruleModel instanceof LexerRuleModel && ((LexerRuleModel)ruleModel).getTokenData() == null) {
                    rules.remove(ruleModel.getName());
                    continue;
                }

                if (rules.containsKey(ruleModel.getName())) {
                    continue;
                }

                rules.put(ruleModel.getName(), new GrammarNode.GrammarNodeDescription(ruleModel.getName()));
            }

            if (!ignoreLexerOnlyRules) {
                for (ModeModel modeModel : fileModel.getModes()) {
                    for (RuleModel ruleModel : modeModel.getRules()) {
                        if (rules.containsKey(ruleModel.getName())) {
                            continue;
                        }

                        rules.put(ruleModel.getName(), new GrammarNode.GrammarNodeDescription(ruleModel.getName()));
                    }
                }
            }

            for (TokenData tokenData : fileModel.getVocabulary().getTokens()) {
                if (rules.containsKey(tokenData.getName())) {
                    continue;
                }

                rules.put(tokenData.getName(), new GrammarNode.GrammarNodeDescription(tokenData.getName()));
            }
        }

        return rules.values();
    }

}
