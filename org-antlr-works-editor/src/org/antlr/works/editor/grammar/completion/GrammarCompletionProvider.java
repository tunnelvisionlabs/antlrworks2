/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import com.tvl.spi.editor.completion.CompletionProvider;
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
import org.antlr.works.editor.grammar.codemodel.ChannelModel;
import org.antlr.works.editor.grammar.codemodel.CodeElementModel;
import org.antlr.works.editor.grammar.codemodel.CodeElementPositionRegion;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.codemodel.LexerRuleModel;
import org.antlr.works.editor.grammar.codemodel.ModeModel;
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.antlr.works.editor.grammar.codemodel.TokenData;
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.antlr.works.editor.grammar.navigation.DeclarationKind;
import org.antlr.works.editor.grammar.navigation.GrammarNode.GrammarNodeDescription;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
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

    private static final String grammarCompletionAutoPopupTriggers = "$";
    private static final String grammarCompletionSelectors = " :;[]<>";

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
        Map<String, Description> rules = new HashMap<>();

        Description rootDescription = GrammarParserDataDefinitions.tryGetData(taskManager, snapshot, GrammarParserDataDefinitions.NAVIGATOR_ROOT, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
        if (rootDescription != null) {
            Queue<Description> workList = new ArrayDeque<>();
            workList.add(rootDescription);

            while (!workList.isEmpty()) {
                Description description = workList.remove();
                if (description.getOffset() > 0) {
                    rules.put(description.getName(), description);
                }

                workList.addAll(description.getChildren());
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

                DeclarationKind declarationKind;
                if (ruleModel instanceof LexerRuleModel) {
                    if (((LexerRuleModel)ruleModel).isFragment()) {
                        declarationKind = DeclarationKind.FRAGMENT_RULE;
                    } else {
                        declarationKind = DeclarationKind.LEXER_RULE;
                    }
                } else {
                    declarationKind = DeclarationKind.PARSER_RULE;
                }

                GrammarNodeDescription description = new GrammarNodeDescription(declarationKind, ruleModel.getName());
                addSeekPositionToDescription(description, ruleModel);
                rules.put(ruleModel.getName(), description);
            }

            if (!ignoreLexerOnlyRules) {
                for (ChannelModel channelModel : fileModel.getChannels()) {
                    GrammarNodeDescription description = new GrammarNodeDescription(DeclarationKind.CHANNEL, channelModel.getName());
                    addSeekPositionToDescription(description, channelModel);
                    rules.put(channelModel.getName(), description);
                }

                for (ModeModel modeModel : fileModel.getModes()) {
                    GrammarNodeDescription modeDescription = new GrammarNodeDescription(DeclarationKind.MODE, modeModel.getName());
                    addSeekPositionToDescription(modeDescription, modeModel);
                    rules.put(modeModel.getName(), modeDescription);

                    for (RuleModel ruleModel : modeModel.getRules()) {
                        if (rules.containsKey(ruleModel.getName())) {
                            continue;
                        }

                        GrammarNodeDescription description = new GrammarNodeDescription(DeclarationKind.LEXER_RULE, ruleModel.getName());
                        addSeekPositionToDescription(description, ruleModel);
                        rules.put(ruleModel.getName(), description);
                    }
                }
            }

            for (TokenData tokenData : fileModel.getVocabulary().getTokens()) {
                if (rules.containsKey(tokenData.getName())) {
                    continue;
                }

                GrammarNodeDescription description = new GrammarNodeDescription(DeclarationKind.TOKEN, tokenData.getName());
                Collection<? extends RuleModel> resolved = tokenData.resolve();
                for (RuleModel ruleModel : resolved) {
                    if (addSeekPositionToDescription(description, ruleModel)) {
                        break;
                    }
                }

                rules.put(tokenData.getName(), description);
            }
        }

        return rules.values();
    }

    private static boolean addSeekPositionToDescription(GrammarNodeDescription description, CodeElementModel codeElementModel) {
        CodeElementPositionRegion position = codeElementModel.getSeek();
        if (position == null) {
            return false;
        }

        description.setOffset(null, position.getFileObject(), position.getOffsetRegion().getStart());
        return true;
    }
}
