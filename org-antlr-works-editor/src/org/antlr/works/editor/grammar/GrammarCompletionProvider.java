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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Future;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.text.TextBuffer;
import org.antlr.netbeans.editor.text.TextBufferUtilities;
import org.antlr.netbeans.editor.text.TextSnapshot;
import org.antlr.v4.runtime.ANTLRStringStream;
import org.antlr.v4.runtime.BaseRecognizer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultANTLRErrorStrategy;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.grammar.experimental.BlankGrammarParserListener;
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParser.actionBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.argActionBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.labeledAltContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.labeledElementContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.notSetContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.rewriteContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.rulerefContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.terminalContext;
import org.antlr.works.editor.grammar.experimental.GrammarParserAnchorListener;
import org.antlr.works.editor.grammar.experimental.TextSnapshotCharStream;
import org.antlr.works.editor.grammar.experimental.UpdateAnchorsTask;
import org.antlr.works.editor.grammar.highlighter.ActionHighlighterLexer;
import org.antlr.works.editor.grammar.highlighter.GrammarHighlighterLexer;
import org.antlr.works.editor.grammar.navigation.GrammarNode;
import org.antlr.works.editor.grammar.navigation.GrammarRulesPanel;
import org.antlr.works.editor.grammar.navigation.GrammarRulesPanelUI;
import org.netbeans.api.editor.completion.Completion;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.parsing.api.ParserManager;
import org.netbeans.modules.parsing.api.ResultIterator;
import org.netbeans.modules.parsing.api.Source;
import org.netbeans.modules.parsing.api.UserTask;
import org.netbeans.modules.parsing.spi.Parser.Result;
import org.netbeans.spi.editor.completion.CompletionDocumentation;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import org.openide.explorer.ExplorerManager;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

import static org.antlr.works.editor.grammar.Bundle.GCP_imported_items;
import static org.antlr.works.editor.grammar.Bundle.GCP_instance_members;

/**
 *
 * @author Sam Harwell
 */
//@MimeRegistration(mimeType="text/x-antlr3", service=CompletionProvider.class)
@NbBundle.Messages({
    "GCP-imported-items=",
    "GCP-instance-members="
})
public class GrammarCompletionProvider implements CompletionProvider {

    public static final int AUTO_QUERY_TYPE = 0x0100;

    private static String grammarCompletionAutoPopupTriggers = "$";
    private static String grammarCompletionSelectors = "";

    @Override
    public int getAutoQueryTypes(JTextComponent component, String typedText) {
        if (typedText != null && typedText.length() == 1
            && (getGrammarCompletionAutoPopupTriggers().indexOf(typedText.charAt(0)) >= 0
            || (autoPopupOnGrammarIdentifierPart() && GrammarCompletionQuery.isGrammarIdentifierPart(typedText)))) {
            if (isGrammarContext(component, component.getSelectionStart() - 1, true)) {
                return COMPLETION_QUERY_TYPE | AUTO_QUERY_TYPE;
            }
        }

        return 0;
    }

    @Override
    public CompletionTask createTask(int queryType, JTextComponent component) {
        if ((queryType & COMPLETION_QUERY_TYPE) != 0 || (queryType & TOOLTIP_QUERY_TYPE) != 0 || (queryType & DOCUMENTATION_QUERY_TYPE) != 0) {
            return new AsyncCompletionTask(new GrammarCompletionQuery(queryType, component.getSelectionStart(), true), component);
        }

        return null;
    }

    public static boolean autoPopupOnGrammarIdentifierPart() {
        return false;
    }

    public static String getGrammarCompletionAutoPopupTriggers() {
        return grammarCompletionAutoPopupTriggers;
    }

    public static String getGrammarCompletionSelectors() {
        return grammarCompletionSelectors;
    }

    private static boolean isGrammarContext(JTextComponent component, int offset, boolean allowInStrings) {
        Document document = component.getDocument();
        if (document instanceof AbstractDocument) {
            ((AbstractDocument)document).readLock();
        }
        try {
            try {
                // TODO: cache tokens
                ANTLRStringStream input = new ANTLRStringStream(document.getText(0, document.getLength()));
                GrammarLexer lexer = new GrammarLexer(input);
                CommonTokenStream tokenStream = new CommonTokenStream(lexer);
                Token token;
                for (token = tokenStream.LT(1); token != null && token.getType() != Token.EOF; token = tokenStream.LT(1)) {
                    tokenStream.consume();
                    if (token.getStartIndex() <= offset && token.getStopIndex() >= offset) {
                        break;
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

                default:
                    return true;
                }

                //List<Token> tokens;
            } catch (BadLocationException ex) {
                Exceptions.printStackTrace(ex);
                return false;
            }
        } finally {
            if (document instanceof AbstractDocument) {
                ((AbstractDocument)document).readUnlock();
            }
        }
    }

    static final class GrammarCompletionQuery extends AsyncCompletionQuery {
        private static final int NO_ADDITIONAL_ITEMS = 0;
        private static final int ADDITIONAL_IMPORTED_ITEMS = 1;
        private static final int ADDITIONAL_MEMBER_ITEMS = 2;

        private static final String EMPTY = "";

        private final int queryType;
        private final boolean hasTask;
        private int caretOffset;

        private JTextComponent component;
        private CompletionToolTip toolTip;

        private List<CompletionItem> results;
        private CompletionDocumentation documentation;
        private String filterPrefix;
        private byte hasAdditionalItems;
        private int anchorOffset;
        private int toolTipOffset;

        private GrammarCompletionQuery(int queryType, int caretOffset, boolean hasTask) {
            this.queryType = queryType;
            this.caretOffset = caretOffset;
            this.hasTask = hasTask;
        }

        @Override
        protected void preQueryUpdate(JTextComponent component) {
            int newCaretOffset = component.getSelectionStart();
            if (newCaretOffset >= caretOffset) {
                try {
                    if (isGrammarIdentifierPart(component.getDocument().getText(caretOffset, newCaretOffset - caretOffset))) {
                        return;
                    }
                } catch (BadLocationException e) {
                }
            }

            Completion.get().hideCompletion();
        }

        @Override
        protected void prepareQuery(JTextComponent component) {
            this.component = component;
            if (queryType == TOOLTIP_QUERY_TYPE) {
                this.toolTip = new CompletionToolTip(component);
            }
        }

        @Override
        protected void query(CompletionResultSet resultSet, Document doc, int caretOffset) {
            try {
                this.caretOffset = caretOffset;
                if (queryType == TOOLTIP_QUERY_TYPE || isGrammarContext(component, caretOffset, true)) {
                    results = null;
                    documentation = null;
                    if (toolTip != null) {
                        toolTip.clearData();
                    }

                    anchorOffset = -1;
                    Source source = null;
                    if (queryType == DOCUMENTATION_QUERY_TYPE) {
                        throw new UnsupportedOperationException("Not implemented yet.");
                    }

                    if (source == null) {
                        source = Source.create(doc);
                    }

                    if (source != null) {
                        Future<Void> value = ParserManager.parseWhenScanFinished(Collections.singletonList(source), getTask());
                        if (!value.isDone()) {
                            component.putClientProperty("completion-active", Boolean.FALSE);
                            resultSet.setWaitText(NbBundle.getMessage(GrammarCompletionProvider.class, "scanning-in-progress"));
                            value.get();
                        }

                        if ((queryType & COMPLETION_QUERY_TYPE) != 0) {
                            if (results != null) {
                                resultSet.addAllItems(results);
                            }

                            resultSet.setHasAdditionalItems(hasAdditionalItems != NO_ADDITIONAL_ITEMS);
                            if (hasAdditionalItems == ADDITIONAL_IMPORTED_ITEMS) {
                                resultSet.setHasAdditionalItemsText(GCP_imported_items());
                            } else if (hasAdditionalItems == ADDITIONAL_MEMBER_ITEMS) {
                                resultSet.setHasAdditionalItemsText(GCP_instance_members());
                            }
                        } else if (queryType == TOOLTIP_QUERY_TYPE) {
                            if (toolTip != null && toolTip.hasData()) {
                                resultSet.setToolTip(toolTip);
                            }
                        } else if (queryType == DOCUMENTATION_QUERY_TYPE) {
                            throw new UnsupportedOperationException("Not implemented yet.");
                        }

                        if (anchorOffset > -1) {
                            resultSet.setAnchorOffset(anchorOffset);
                        }
                    }
                }
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            } finally {
                resultSet.finish();
            }
        }

        @Override
        protected boolean canFilter(JTextComponent component) {
            filterPrefix = null;
            int newOffset = component.getSelectionStart();
            if ((queryType & COMPLETION_QUERY_TYPE) != 0) {
                int offset = Math.min(anchorOffset, caretOffset);
                if (offset > -1) {
                    if (newOffset < offset) {
                        return true;
                    } else if (newOffset >= caretOffset) {
                        try {
                            // TODO: get the token at this position
                            CommonToken token = null;
                            if (token != null) {
                                int len = newOffset - offset;
                                if (len >= 0) {
                                    switch (token.getType()) {
                                    case GrammarHighlighterLexer.ParserRule:
                                    case GrammarHighlighterLexer.LexerRule:
                                    case GrammarHighlighterLexer.IDENTIFIER:
                                    case GrammarHighlighterLexer.REFERENCE:
                                    case ActionHighlighterLexer.ACTION_REFERENCE:
                                        filterPrefix = token.getText().substring(0, len);
                                        break;
                                    }
                                }
                            }

                            if (filterPrefix == null) {
                                String prefix = component.getDocument().getText(offset, newOffset - offset);
                                if (prefix.length() > 0 && getGrammarCompletionAutoPopupTriggers().indexOf(prefix.charAt(prefix.length() - 1)) >= 0) {
                                    return false;
                                }
                            } else if (filterPrefix.length() == 0) {
                                anchorOffset = newOffset;
                            }
                        } catch (BadLocationException e) {
                        }

                        return true;
                    }
                }

                return false;
            } else if (queryType == TOOLTIP_QUERY_TYPE) {
                try {
                    if (newOffset == caretOffset) {
                        filterPrefix = EMPTY;
                    } else if (newOffset - caretOffset > 0) {
                        filterPrefix = component.getDocument().getText(caretOffset, newOffset - caretOffset);
                    } else if (newOffset - caretOffset < 0) {
                        filterPrefix = newOffset > toolTipOffset ? component.getDocument().getText(newOffset, caretOffset - newOffset) : null;
                    }
                } catch (BadLocationException e) {
                }

                return (filterPrefix != null && filterPrefix.indexOf(',') == -1 && filterPrefix.indexOf('(') == -1 && filterPrefix.indexOf(')') == -1);
            }

            return false;
        }

        @Override
        protected void filter(CompletionResultSet resultSet) {
            try {
                if ((queryType & COMPLETION_QUERY_TYPE) != 0) {
                    if (results != null) {
                        if (filterPrefix != null) {
                            resultSet.addAllItems(getFilteredData(results, filterPrefix));
                            resultSet.setHasAdditionalItems(hasAdditionalItems > 0);
                        } else {
                            Completion.get().hideDocumentation();
                            Completion.get().hideCompletion();
                        }
                    }
                } else if (queryType == TOOLTIP_QUERY_TYPE) {
                    resultSet.setToolTip(toolTip != null ? toolTip : null);
                }

                resultSet.setAnchorOffset(anchorOffset);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }

            resultSet.finish();
        }

        private UserTask getTask() {
            return new Task();
        }

        private static boolean isGrammarIdentifierPart(String typedText) {
            for (int i = 0; i < typedText.length(); i++) {
                if (!Character.isJavaIdentifierPart(typedText.charAt(i))) {
                    return false;
                }
            }

            return true;
        }

        private Collection<? extends CompletionItem> getFilteredData(List<CompletionItem> data, String prefix) {
            if (prefix.length() == 0) {
                return data;
            }

            List<CompletionItem> result = new ArrayList<CompletionItem>();
            boolean camelCase = isCamelCasePrefix(prefix);
            for (CompletionItem item : data) {
                // TODO: improved filtering
                result.add(item);
                /*if (startsWith(item.getInsertPrefix().toString(), prefix)
                    || (camelCase && startsWithCamelCase(item.getInsertPrefix().toString(), prefix))) {
                    result.add(item);
                }*/
            }

            return result;
        }

        private static boolean isCamelCasePrefix(String prefix) {
            if (prefix == null || prefix.length() < 2 || !Character.isUpperCase(prefix.charAt(0))) {
                return false;
            }

            int capCount = 0;
            for (int i = 0; i < prefix.length(); i++) {
                if (Character.isUpperCase(prefix.charAt(i))) {
                    capCount++;
                    continue;
                }

                if (!Character.isJavaIdentifierPart(prefix.charAt(i))) {
                    return false;
                }
            }

            return capCount > 1;
        }

        private class Task extends UserTask {

            @Override
            public void run(ResultIterator resultIterator) throws Exception {
                results = new ArrayList<CompletionItem>();

                Result result = resultIterator.getParserResult(caretOffset);
                
                // Add context items (labels, etc). Use anchor points to optimize information gathering.
                BaseDocument document = (BaseDocument)result.getSnapshot().getSource().getDocument(false);
                if (document == null) {
                    return;
                }

                TextBuffer textBuffer = TextBufferUtilities.getTextBufferForDocument(document);
                TextSnapshot snapshot = textBuffer.getCurrentSnapshot();

                boolean inAction = false;

                Object anchorsObject = document.getProperty(UpdateAnchorsTask.class);
                @SuppressWarnings("unchecked")
                List<GrammarParserAnchorListener.Anchor> anchors = (List<GrammarParserAnchorListener.Anchor>)anchorsObject;
                if (anchors != null) {
                    GrammarParserAnchorListener.Anchor enclosing = null;
                    int grammarType = -1;

                    /*
                     * parse the current rule
                     */
                    for (GrammarParserAnchorListener.Anchor anchor : anchors) {
                        if (anchor instanceof GrammarParserAnchorListener.GrammarTypeAnchor) {
                            grammarType = ((GrammarParserAnchorListener.GrammarTypeAnchor)anchor).getGrammarType();
                            continue;
                        }

                        if (anchor.getSpan().getStartPoint(snapshot).getPosition() <= caretOffset && anchor.getSpan().getEndPoint(snapshot).getPosition() > caretOffset) {
                            enclosing = anchor;
                        } else if (anchor.getSpan().getStartPoint(snapshot).getPosition() > caretOffset) {
                            break;
                        }
                    }

                    if (enclosing != null) {
                        CharStream input = new TextSnapshotCharStream(snapshot);
                        input.seek(enclosing.getSpan().getStartPoint(snapshot).getPosition());
                        GrammarLexer lexer = new GrammarLexer(input);
                        TokenSource tokenSource = new CodeCompletionTokenSource(caretOffset, lexer);
                        CommonTokenStream tokens = new CommonTokenStream(tokenSource);

                        GrammarParser parser = new GrammarParser(tokens);
                        parser.setBuildParseTree(true);
                        parser.setErrHandler(new CodeCompletionErrorStrategy());

                        ParseTree parseTree;
                        RuleContext finalContext = null;
                        Token caretToken = null;

                        switch (enclosing.getRule()) {
                        case GrammarParser.RULE_rule:
                            try {
                                parseTree = parser.rule();
                            } catch (CaretReachedException ex) {
                                for (parseTree = ex.getFinalContext(); parseTree.getParent() != null; parseTree = parseTree.getParent()) {
                                    // intentionally blank
                                }

                                finalContext = ex.getFinalContext();
                                caretToken = ex.getCaretToken();
                            }

                            break;

                        default:
                            parseTree = null;
                            break;
                        }

                        if (parseTree != null) {
                            LabelAnalyzer labelAnalyzer = new LabelAnalyzer(finalContext);
                            new ParseTreeWalker().walk(labelAnalyzer, parseTree);

                            inAction = labelAnalyzer.isInAction();

                            if (labelAnalyzer.isInAction() || labelAnalyzer.isInRewrite()) {
                                if (!labelAnalyzer.isInAction() && labelAnalyzer.getEnclosingRuleName() != null) {
                                    results.add(new EnclosingRuleCompletionItem(0, labelAnalyzer.getEnclosingRuleName()));
                                }

                                for (Token label : labelAnalyzer.getLabels()) {
                                    results.add(new ElementReferenceCompletionItem(0, label, true));
                                }

                                if (labelAnalyzer.isInAction()) {
                                    for (Token implicit : labelAnalyzer.getUnlabeledElements()) {
                                        results.add(new ElementReferenceCompletionItem(0, implicit, false));
                                    }

                                    if (grammarType == GrammarParser.COMBINED) {
                                        Token enclosingRule = labelAnalyzer.getEnclosingRuleName();
                                        if (enclosingRule != null) {
                                            if (enclosingRule.getType() == GrammarParser.RULE_REF) {
                                                grammarType = GrammarParser.PARSER;
                                            } else {
                                                grammarType = GrammarParser.LEXER;
                                            }
                                        }
                                    }

                                    switch (grammarType) {
                                    case GrammarParser.LEXER:
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$text"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$type"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$line"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$index"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$pos"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$channel"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$start"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$stop"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$int"));
                                        break;

                                    case GrammarParser.PARSER:
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$text"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$start"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$stop"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$tree"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$st"));
                                        break;

                                    case GrammarParser.TREE:
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$text"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$start"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$tree"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$st"));
                                        break;

                                    default:
                                        // if we're unsure about the type, include all possibilities to make sure we're covered
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$text"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$type"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$line"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$index"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$pos"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$channel"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$start"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$stop"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$int"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$tree"));
                                        results.add(new GrammarCompletionItem.KeywordItem(0, "$st"));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                
                if (!inAction) {
                    // Add keywords
                    results.addAll(GrammarCompletionItem.KEYWORD_ITEMS);

                    // Add rules from the grammar
                    GrammarRulesPanelUI ui = GrammarRulesPanel.findGrammarRulesPanelUI();
                    ExplorerManager explorerManager = (ui != null) ? ui.getExplorerManager() : null;
                    Node rootContext = (explorerManager != null) ? explorerManager.getRootContext() : null;
                    if (ui != null) {
                        List<Description> rules = new ArrayList<Description>();
                        Queue<Description> workList = new ArrayDeque<Description>();
                        if (rootContext instanceof GrammarNode) {
                            workList.add(((GrammarNode)rootContext).getDescription());
                        }

                        while (!workList.isEmpty()) {
                            Description description = workList.remove();
                            if (description.getOffset() > 0) {
                                rules.add(description);
                            }

                            if (description.getChildren() != null) {
                                workList.addAll(description.getChildren());
                            }
                        }

                        for (Description rule : rules) {
                            results.add(new GrammarRuleCompletionItem(0, rule));
                        }
                    }
                }
            }

        }
        
        private static class CaretToken extends CommonToken {

            public static final int CARET_TOKEN_TYPE = -2;
            private final Token originalToken;

            public CaretToken(TokenSource source, int channel, int start, int stop) {
                super(source, CARET_TOKEN_TYPE, channel, start, stop);
                this.originalToken = null;
            }

            public CaretToken(Token oldToken) {
                super(oldToken);
                this.channel = Token.DEFAULT_CHANNEL;
                this.originalToken = oldToken;
                this.setType(CARET_TOKEN_TYPE);
            }

            public int getOriginalType() {
                return originalToken.getType();
            }
            
        }

        private static class CodeCompletionTokenSource implements TokenSource {

            private final int caretOffset;
            private final TokenSource source;

            private Token caretToken;

            public CodeCompletionTokenSource(int caretOffset, TokenSource source) {
                this.caretOffset = caretOffset;
                this.source = source;
            }

            @Override
            public Token nextToken() {
                Token token = caretToken;
                if (caretToken == null) {
                    token = source.nextToken();
                    if (token.getStopIndex() < caretOffset) {
                        // the caret is after this token, nothing special to do
                    } else if (token.getStartIndex() > caretOffset) {
                        // the token is after the caret, no need to include it
                        token = new CaretToken(source, Token.DEFAULT_CHANNEL, caretOffset, caretOffset);
                        caretToken = token;
                    } else {
                        // the caret is in the middle of this token
                        token = new CaretToken(token);
                        caretToken = token;
                    }
                }

                return token;
            }

            @Override
            public int getLine() {
                return source.getLine();
            }

            @Override
            public int getCharPositionInLine() {
                return source.getCharPositionInLine();
            }

            @Override
            public CharStream getInputStream() {
                return source.getInputStream();
            }

            @Override
            public String getSourceName() {
                return source.getSourceName();
            }

        }

        private static class CodeCompletionErrorStrategy extends DefaultANTLRErrorStrategy {

            public CodeCompletionErrorStrategy() {
            }

            @Override
            public void sync(BaseRecognizer recognizer) {
                if (recognizer.getInputStream().LA(1) == CaretToken.CARET_TOKEN_TYPE) {
                    return;
                }

                super.sync(recognizer);
            }

            /** Consume tokens until one matches the given token set */
            @Override
            public void consumeUntil(BaseRecognizer recognizer, IntervalSet set) {
                //System.out.println("consumeUntil("+set.toString(getTokenNames())+")");
                int ttype = recognizer.getInputStream().LA(1);
                while (ttype != Token.EOF && ttype != CaretToken.CARET_TOKEN_TYPE && !set.contains(ttype) ) {
                    //System.out.println("consume during recover LA(1)="+getTokenNames()[input.LA(1)]);
        			recognizer.getInputStream().consume();
                    //recognizer.consume();
                    ttype = recognizer.getInputStream().LA(1);
                }
            }

            @Override
            public void recover(BaseRecognizer recognizer) {
                if (recognizer instanceof GrammarParser
                    && recognizer.getInputStream() instanceof TokenStream
                    && ((TokenStream)recognizer.getInputStream()).LT(1) instanceof CaretToken) {

                    GrammarParser parser = (GrammarParser)recognizer;
                    CaretToken token = (CaretToken)((TokenStream)recognizer.getInputStream()).LT(1);

                    throw new CaretReachedException(parser.getContext(), token);
                }

                super.recover(recognizer);
            }

            @Override
            public Object recoverInline(BaseRecognizer recognizer) throws RecognitionException {
                if (recognizer instanceof GrammarParser
                    && recognizer.getInputStream() instanceof TokenStream
                    && ((TokenStream)recognizer.getInputStream()).LT(1) instanceof CaretToken) {

                    GrammarParser parser = (GrammarParser)recognizer;
                    CaretToken token = (CaretToken)((TokenStream)recognizer.getInputStream()).LT(1);

                    throw new CaretReachedException(parser.getContext(), token);
                }

                return super.recoverInline(recognizer);
            }
        }

        private static class CaretReachedException extends RuntimeException {

            private final RuleContext finalContext;
            private final CaretToken caretToken;

            public CaretReachedException(RuleContext finalContext, CaretToken caretToken) {
                this.finalContext = finalContext;
                this.caretToken = caretToken;
            }

            public RuleContext getFinalContext() {
                return finalContext;
            }

            public CaretToken getCaretToken() {
                return caretToken;
            }
        }

        private static class LabelAnalyzer extends BlankGrammarParserListener {

            private final Map<String, Token> labels = new HashMap<String, Token>();
            private final Map<String, Token> unlabeledElements = new HashMap<String, Token>();
            private final RuleContext finalContext;

            private Token enclosingRuleName;
            private boolean caretReached;
            private boolean inRewrite;
            private boolean inAction;

            public LabelAnalyzer(RuleContext finalContext) {
                this.finalContext = finalContext;
            }

            public final RuleContext getFinalContext() {
                return finalContext;
            }

            public final Collection<Token> getLabels() {
                return labels.values();
            }

            public final Collection<Token> getUnlabeledElements() {
                return unlabeledElements.values();
            }

            public final Token getEnclosingRuleName() {
                return enclosingRuleName;
            }

            public final boolean isCaretReached() {
                return caretReached;
            }

            public final boolean isInRewrite() {
                return inRewrite;
            }

            public final boolean isInAction() {
                return inAction;
            }

            @Override
            public void exitEveryRule(ParserRuleContext ctx) {
                checkCaretReached(ctx);
            }

            @Override
            public void enterRule(ruleContext ctx) {
                enclosingRuleName = ctx.name.start;
            }

            @Override
            public void enterRule(labeledAltContext ctx) {
                if (isCaretReached()) {
                    return;
                }

                labels.clear();
                unlabeledElements.clear();
            }

            @Override
            public void enterRule(labeledElementContext ctx) {
                if (isCaretReached()) {
                    return;
                }

                if (isInRewrite()) {
                    return;
                }

                if (!labels.containsKey(ctx.start.getText())) {
                    labels.put(ctx.start.getText(), ctx.start);
                }
            }

            @Override
            public void enterRule(terminalContext ctx) {
                if (isCaretReached()) {
                    return;
                }

                if (isInRewrite()) {
                    return;
                }

                if (isLabeledContext(ctx)) {
                    return;
                }

                if (ctx.start.getType() == GrammarParser.TOKEN_REF) {
                    if (!labels.containsKey(ctx.start.getText())) {
                        unlabeledElements.put(ctx.start.getText(), ctx.start);
                    }
                }
            }

            @Override
            public void enterRule(rulerefContext ctx) {
                if (isCaretReached()) {
                    return;
                }

                if (isInRewrite()) {
                    return;
                }

                if (isLabeledContext(ctx)) {
                    return;
                }

                if (ctx.start.getType() == GrammarParser.RULE_REF) {
                    if (!labels.containsKey(ctx.start.getText())) {
                        unlabeledElements.put(ctx.start.getText(), ctx.start);
                    }
                }
            }

            @Override
            public void enterRule(rewriteContext ctx) {
                if (isCaretReached()) {
                    return;
                }

                inRewrite = true;
            }

            @Override
            public void exitRule(rewriteContext ctx) {
                checkCaretReached(ctx);

                if (isCaretReached()) {
                    return;
                }

                inRewrite = false;
            }

            @Override
            public void enterRule(actionBlockContext ctx) {
                if (isCaretReached()) {
                    return;
                }

                inAction = true;
            }

            @Override
            public void exitRule(actionBlockContext ctx) {
                checkCaretReached(ctx);

                if (isCaretReached()) {
                    return;
                }

                inAction = false;
            }

            @Override
            public void enterRule(argActionBlockContext ctx) {
                if (isCaretReached()) {
                    return;
                }

                inAction = true;
            }

            @Override
            public void exitRule(argActionBlockContext ctx) {
                checkCaretReached(ctx);

                if (isCaretReached()) {
                    return;
                }

                inAction = false;
            }

            private void checkCaretReached(RuleContext ctx) {
                if (ctx == getFinalContext()) {
                    caretReached = true;
                }
            }

            private static boolean isLabeledContext(ParserRuleContext ctx) {
                for (RuleContext current = ctx; current != null; current = current.parent) {
                    if (current instanceof labeledElementContext) {
                        return true;
                    } else if (current instanceof notSetContext) {
                        return true;
                    }
                }

                return false;
            }
        }

    }

}
