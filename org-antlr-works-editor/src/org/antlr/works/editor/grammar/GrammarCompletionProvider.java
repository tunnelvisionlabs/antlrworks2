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
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Future;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.v4.runtime.ANTLRStringStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultANTLRErrorStrategy;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParserAnchorListener;
import org.antlr.works.editor.grammar.experimental.UpdateAnchorsTask;
import org.antlr.works.editor.grammar.highlighter.ActionHighlighterLexer;
import org.antlr.works.editor.grammar.highlighter.GrammarHighlighterLexer;
import org.antlr.works.editor.grammar.navigation.GrammarNode;
import org.antlr.works.editor.grammar.navigation.GrammarRulesPanel;
import org.antlr.works.editor.grammar.navigation.GrammarRulesPanelUI;
import org.netbeans.api.editor.completion.Completion;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
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

    private static String grammarCompletionAutoPopupTriggers = "";
    private static String grammarCompletionSelectors = "";

    @Override
    public int getAutoQueryTypes(JTextComponent component, String typedText) {
        if (typedText != null && typedText.length() == 1
            && (getGrammarCompletionAutoPopupTriggers().indexOf(typedText.charAt(0)) >= 0
            || (autoPopupOnGrammarIdentifierPart() && GrammarCompletionQuery.isGrammarIdentifierPart(typedText)))) {
            if (isGrammarContext(component, component.getSelectionStart() - 1, true)) {
                return COMPLETION_QUERY_TYPE;
            }
        }

        return 0;
    }

    @Override
    public CompletionTask createTask(int queryType, JTextComponent component) {
        if ((queryType & COMPLETION_QUERY_TYPE) != 0 || queryType == TOOLTIP_QUERY_TYPE || queryType == DOCUMENTATION_QUERY_TYPE) {
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

                // Add keywords
                results.addAll(GrammarCompletionItem.KEYWORD_ITEMS);
                
                // Add rules from the grammar
                GrammarRulesPanelUI ui = GrammarRulesPanel.findGrammarRulesPanelUI();
                ExplorerManager explorerManager = (ui != null) ? ui.getExplorerManager() : null;
                Node rootContext = (explorerManager != null) ? explorerManager.getRootContext() : null;
                if (ui != null) {
                    List<Description> rules = new ArrayList<Description>();
                    Queue<Description> workList = new ArrayDeque<Description>();
                    workList.add(((GrammarNode)rootContext).getDescription());
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
                
                // Add context items (labels, etc). Use anchor points to optimize information gathering.
                BaseDocument document = (BaseDocument)result.getSnapshot().getSource().getDocument(false);
                if (document == null) {
                    return;
                }

                Object anchorsObject = document.getProperty(UpdateAnchorsTask.class);
                @SuppressWarnings("unchecked")
                List<GrammarParserAnchorListener.Anchor> anchors = (List<GrammarParserAnchorListener.Anchor>)anchorsObject;
                if (anchors != null) {
                    GrammarParserAnchorListener.Anchor enclosing = null;
                    for (GrammarParserAnchorListener.Anchor anchor : anchors) {
                        if (anchor.getStartPosition().getOffset() <= caretOffset && anchor.getEndPosition().getOffset() > caretOffset) {
                            enclosing = anchor;
                        } else if (anchor.getStartPosition().getOffset() > caretOffset) {
                            break;
                        }
                    }

                    if (enclosing != null) {
                        document.readLock();
                        try {
                            CharStream input = new ANTLRStringStream(document.getText(0, document.getLength()));
                            input.seek(enclosing.getStartPosition().getOffset());
                            GrammarLexer lexer = new GrammarLexer(input);
                            CommonTokenStream tokens = new CommonTokenStream(lexer);

                            GrammarParser parser = new GrammarParser(tokens);
                            parser.setErrHandler(new CodeCompletionErrorStrategy());

                            ParseTree parseTree;
                            switch (enclosing.getRule()) {
                            case GrammarParser.RULE_rule:
                                parseTree = parser.rule();
                                break;

                            default:
                                parseTree = null;
                                break;
                            }


                        } finally {
                            document.readUnlock();
                        }
                    }
                }

                Collections.sort(results, new Comparator<CompletionItem>(){

                    @Override
                    public int compare(CompletionItem o1, CompletionItem o2) {
                        return o1.getSortText().toString().compareToIgnoreCase(o2.getSortText().toString());
                    }
                });
            }

        }
        
        private static class CaretToken extends CommonToken {

            private static final int CARET_TOKEN_TYPE = -2;
            private final Token originalToken;

            public CaretToken(TokenSource source, int channel, int start, int stop) {
                super(source, CARET_TOKEN_TYPE, channel, start, stop);
                this.originalToken = null;
            }

            public CaretToken(Token oldToken) {
                super(oldToken);
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

        }

    }

}
