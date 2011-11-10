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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.v4.runtime.ANTLRStringStream;
import org.antlr.v4.runtime.BaseRecognizer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultANTLRErrorStrategy;
import org.antlr.v4.runtime.ObjectStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.WritableToken;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.AtomTransition;
import org.antlr.v4.runtime.atn.DecisionState;
import org.antlr.v4.runtime.atn.NotSetTransition;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.RangeTransition;
import org.antlr.v4.runtime.atn.SetTransition;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.atn.WildcardTransition;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.misc.OrderedHashSet;
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
import org.antlr.works.editor.grammar.experimental.DocumentSnapshotCharStream;
import org.antlr.works.editor.grammar.experimental.UpdateAnchorsTask;
import org.antlr.works.editor.grammar.navigation.GrammarNode;
import org.antlr.works.editor.grammar.navigation.GrammarRulesPanel;
import org.antlr.works.editor.grammar.navigation.GrammarRulesPanelUI;
import org.netbeans.api.editor.completion.Completion;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.Utilities;
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
@MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=CompletionProvider.class)
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
            int caretOffset = component.getSelectionStart();
            boolean extend = false;
            try {
                int[] identifier = Utilities.getIdentifierBlock(component, caretOffset);
                extend = identifier != null && caretOffset > identifier[0];
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

        private static final Pattern WORD_PATTERN = Pattern.compile("^[\\$A-Za-z_][A-Za-z0-9_]*$");

        private static final String EMPTY = "";

        private final int queryType;
        private final boolean hasTask;
        private final boolean extend;
        private int caretOffset;

        private JTextComponent component;
        private CompletionToolTip toolTip;

        private List<CompletionItem> results;
        private boolean possibleDeclaration;
        private boolean possibleReference;
        private boolean possibleKeyword;

        private CompletionDocumentation documentation;
        private String filterPrefix;
        private byte hasAdditionalItems;
        private TrackingPositionRegion applicableTo;
        private int toolTipOffset;

        private GrammarCompletionQuery(int queryType, int caretOffset, boolean hasTask, boolean extend) {
            this.queryType = queryType;
            this.caretOffset = caretOffset;
            this.hasTask = hasTask;
            this.extend = extend;
        }

        public TrackingPositionRegion getApplicableTo() {
            return applicableTo;
        }

        public boolean isExtend() {
            return extend;
        }

        @Override
        protected void preQueryUpdate(JTextComponent component) {
            if (applicableTo != null) {
                int newCaretOffset = component.getSelectionStart();
                Document document = component.getDocument();
                VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(document);
                DocumentSnapshot textSnapshot = textBuffer.getCurrentSnapshot();
                SnapshotPositionRegion span = applicableTo.getRegion(textSnapshot);
                if (span.contains(newCaretOffset)) {
                    String text = span.getText();
                    if (text.isEmpty() || WORD_PATTERN.matcher(text).matches()) {
                        return;
                    }
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

                    applicableTo = null;
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

                        if (applicableTo != null) {
                            VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(doc);
                            resultSet.setAnchorOffset(applicableTo.getStartPosition(textBuffer.getCurrentSnapshot()).getOffset());
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
                if (applicableTo != null) {
                    VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(component.getDocument());
                    DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();
                    SnapshotPositionRegion applicableSpan = getApplicableTo().getRegion(snapshot);
                    int caretPosition = component.getCaretPosition();
                    // can't use SnapshotPositionRegion.contains because we need to use an inclusive check at the end of the span
                    if (applicableSpan.getStart().getOffset() <= caretPosition && applicableSpan.getEnd().getOffset() >= caretPosition) {
                        OffsetRegion filterSpan = OffsetRegion.fromBounds(applicableSpan.getStart().getOffset(), component.getCaretPosition());
                        filterPrefix = snapshot.subSequence(filterSpan.getStart(), filterSpan.getEnd()).toString();
                        if (!filterPrefix.isEmpty() && !WORD_PATTERN.matcher(filterPrefix).matches()) {
                            filterPrefix = null;
                        }
                    }
                    
                    return true;
                }
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
                            Collection<? extends CompletionItem> filtered = getFilteredData(results, filterPrefix);
                            resultSet.addAllItems(filtered);
                            if (possibleDeclaration && getApplicableTo() != null) {
                                VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(component.getDocument());
                                DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();
                                SnapshotPositionRegion applicableSpan = getApplicableTo().getRegion(snapshot);
                                if (applicableSpan.getLength() > 0) {
                                    resultSet.addItem(new DeclarationCompletionItem(component.getDocument(), getApplicableTo()));
                                }
                            }

                            resultSet.setHasAdditionalItems(hasAdditionalItems > 0);
                        } else {
                            Completion.get().hideDocumentation();
                            Completion.get().hideCompletion();
                        }
                    }
                } else if (queryType == TOOLTIP_QUERY_TYPE) {
                    resultSet.setToolTip(toolTip != null ? toolTip : null);
                }

                if (applicableTo != null) {
                    VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(component.getDocument());
                    resultSet.setAnchorOffset(applicableTo.getStartPosition(textBuffer.getCurrentSnapshot()).getOffset());
                }
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

        private static final Pattern WORD_BOUNDARY_PREFIX =
            Pattern.compile("^([A-Z][a-z]*){2,}$");

        private static final Pattern WORD_PATTERN2 =
            Pattern.compile("[A-Z][a-z]*");

        private Collection<? extends CompletionItem> getFilteredData(List<CompletionItem> data, String prefix) {
            if (prefix.length() == 0) {
                return data;
            }

            Pattern prefixBoundaryPattern = null;
            Matcher matcher = WORD_BOUNDARY_PREFIX.matcher(prefix);
            if (matcher.matches()) {
                StringBuilder pattern = new StringBuilder("^");
                for (Matcher wordMatcher = WORD_PATTERN2.matcher(prefix); wordMatcher.find(); ) {
                    String group = wordMatcher.group();
                    pattern.append("(?:(?:\\w*[a-z0-9_])?")
                        .append(Character.toUpperCase(group.charAt(0)))
                        .append("|(?:\\w*[0-9_])?")
                        .append(Character.toLowerCase(group.charAt(0)))
                        .append(")");

                    for (int j = 1; j < group.length(); j++) {
                        char ch = group.charAt(j);
                        if (Character.isLetter(ch)) {
                            pattern.append("[")
                                .append(Character.toUpperCase(ch))
                                .append(Character.toLowerCase(ch))
                                .append("]");
                        } else {
                            pattern.append(ch);
                        }
                    }
                }

                pattern.append("\\w*$");
                prefixBoundaryPattern = Pattern.compile(pattern.toString());
            }

            String lowercasePrefix = prefix.toLowerCase(Locale.getDefault());
            List<CompletionItem> result = new ArrayList<CompletionItem>();
            for (CompletionItem item : data) {
                String insertPrefix = item.getInsertPrefix().toString();
                if (insertPrefix.toLowerCase(Locale.getDefault()).contains(lowercasePrefix)) {
                    result.add(item);
                } else if (prefixBoundaryPattern != null && prefixBoundaryPattern.matcher(insertPrefix).matches()) {
                    result.add(item);
                }
            }

            return result;
        }

        private class Task extends UserTask {

            @Override
            public void run(ResultIterator resultIterator) throws Exception {
                results = new ArrayList<CompletionItem>();
                possibleDeclaration = true;
                possibleReference = (queryType & AUTO_QUERY_TYPE) != 0;
                possibleKeyword = true;

                Result result = resultIterator.getParserResult(caretOffset);
                
                // Add context items (labels, etc). Use anchor points to optimize information gathering.
                BaseDocument document = (BaseDocument)result.getSnapshot().getSource().getDocument(false);
                if (document == null) {
                    return;
                }

                VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(document);
                DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();

                boolean inAction;
                CaretToken caretToken = null;
                Map<ATNConfig, List<Transition>> transitions = null;
                int grammarType = -1;

                Object anchorsObject = document.getProperty(UpdateAnchorsTask.class);
                @SuppressWarnings("unchecked")
                List<GrammarParserAnchorListener.Anchor> anchors = (List<GrammarParserAnchorListener.Anchor>)anchorsObject;
                if (anchors != null) {
                    GrammarParserAnchorListener.Anchor enclosing = null;

                    /*
                     * parse the current rule
                     */
                    for (GrammarParserAnchorListener.Anchor anchor : anchors) {
                        if (anchor instanceof GrammarParserAnchorListener.GrammarTypeAnchor) {
                            grammarType = ((GrammarParserAnchorListener.GrammarTypeAnchor)anchor).getGrammarType();
                            continue;
                        }

                        if (anchor.getSpan().getStartPosition(snapshot).getOffset() <= caretOffset && anchor.getSpan().getEndPosition(snapshot).getOffset() > caretOffset) {
                            enclosing = anchor;
                        } else if (anchor.getSpan().getStartPosition(snapshot).getOffset() > caretOffset) {
                            break;
                        }
                    }

                    if (enclosing != null) {
                        CharStream input = new DocumentSnapshotCharStream(snapshot);
                        input.seek(enclosing.getSpan().getStartPosition(snapshot).getOffset());
                        GrammarLexer lexer = new GrammarLexer(input);
                        TokenSource tokenSource = new CodeCompletionTokenSource(caretOffset, lexer);
                        CommonTokenStream tokens = new CommonTokenStream(tokenSource);

                        GrammarParser parser = new GrammarParser(tokens) {{_interp = new CompletionParserATNSimulator(this, _ATN);}};
                        parser.setBuildParseTree(true);
                        parser.setErrHandler(new CodeCompletionErrorStrategy());

                        ParseTree parseTree;
                        RuleContext finalContext = null;

                        switch (enclosing.getRule()) {
                        case GrammarParser.RULE_rule:
                            try {
                                parseTree = parser.rules();
                            } catch (CaretReachedException ex) {
                                for (parseTree = ex.getFinalContext(); parseTree.getParent() != null; parseTree = parseTree.getParent()) {
                                    // intentionally blank
                                }

                                finalContext = ex.getFinalContext();
                                caretToken = ex.getCaretToken();
                                transitions = ex.getTransitions();
                            }

                            break;

                        default:
                            parseTree = null;
                            break;
                        }

                        if (transitions != null) {
                            possibleDeclaration = false;
                            possibleReference = false;
                            for (ATNConfig c : transitions.keySet()) {
                                for (Transition t : transitions.get(c)) {
                                    int ruleIndex = t.target.ruleIndex;
                                    if (ruleIndex == GrammarParser.RULE_id) {
                                        possibleDeclaration = true;
                                    } else if (ruleIndex == GrammarParser.RULE_ruleref
                                        || ruleIndex == GrammarParser.RULE_terminal) {
                                        possibleReference = true;
                                    }
                                }
                            }
                        }

//                        StringBuilder summary = new StringBuilder();
//                        if (transitions != null) {
//                            for (ATNConfig c : transitions.keySet()) {
//                                summary.append(c).append("\n");
//                                for (Transition t : transitions.get(c)) {
//                                    summary.append("\t").append(t).append("\n");
//                                }
//                            }
//                        }

                        if (parseTree != null) {
                            LabelAnalyzer labelAnalyzer = new LabelAnalyzer(finalContext);
                            new ParseTreeWalker().walk(labelAnalyzer, parseTree);

                            inAction = labelAnalyzer.isInAction();
                            possibleKeyword = !inAction;
                            possibleDeclaration &= !inAction;
                            possibleReference &= !inAction;

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

                            if (labelAnalyzer.isInAction() || labelAnalyzer.isInRewrite()) {
                                if (!labelAnalyzer.isInAction() && labelAnalyzer.getEnclosingRuleName() != null) {
                                    results.add(new EnclosingRuleCompletionItem(labelAnalyzer.getEnclosingRuleName()));
                                }

                                for (Token label : labelAnalyzer.getLabels()) {
                                    results.add(new ElementReferenceCompletionItem(label, true));
                                }

                                if (labelAnalyzer.isInAction()) {
                                    for (Token implicit : labelAnalyzer.getUnlabeledElements()) {
                                        results.add(new ElementReferenceCompletionItem(implicit, false));
                                    }

                                    switch (grammarType) {
                                    case GrammarParser.LEXER:
                                        results.add(new GrammarCompletionItem.KeywordItem("$text"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$type"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$line"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$index"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$pos"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$channel"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$start"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$stop"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$int"));
                                        break;

                                    case GrammarParser.PARSER:
                                        results.add(new GrammarCompletionItem.KeywordItem("$text"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$start"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$stop"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$tree"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$st"));
                                        break;

                                    case GrammarParser.TREE:
                                        results.add(new GrammarCompletionItem.KeywordItem("$text"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$start"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$tree"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$st"));
                                        break;

                                    default:
                                        // if we're unsure about the type, include all possibilities to make sure we're covered
                                        results.add(new GrammarCompletionItem.KeywordItem("$text"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$type"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$line"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$index"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$pos"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$channel"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$start"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$stop"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$int"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$tree"));
                                        results.add(new GrammarCompletionItem.KeywordItem("$st"));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                if (possibleKeyword) {
                    // Add keywords
                    results.addAll(GrammarCompletionItem.KEYWORD_ITEMS);
                }

                if (possibleReference) {
                    boolean tokenReferencesOnly = grammarType == GrammarParser.LEXER;

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
                            if (!tokenReferencesOnly || Character.isUpperCase(rule.getName().charAt(0))) {
                                results.add(new GrammarRuleCompletionItem(rule));
                            }
                        }
                    }
                }

                OffsetRegion applicableToSpan;
                if (caretToken != null && caretToken.getOriginalToken() != null && caretToken.getOriginalToken().getChannel() == Token.DEFAULT_CHANNEL) {
                    applicableToSpan = OffsetRegion.fromBounds(caretToken.getStartIndex(), caretToken.getStopIndex() + 1);
                } else {
                    int[] identifier = Utilities.getIdentifierBlock(document, caretOffset);
                    if (identifier != null) {
                        applicableToSpan = OffsetRegion.fromBounds(identifier[0], identifier[1]);
                    } else {
                        applicableToSpan = OffsetRegion.fromBounds(caretOffset, caretOffset);
                    }
                }

                applicableTo = snapshot.createTrackingRegion(applicableToSpan, TrackingPositionRegion.Bias.Inclusive);
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

            public Token getOriginalToken() {
                return originalToken;
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
                if (caretToken == null) {
                    Token token = source.nextToken();
                    if (token.getStopIndex() + 1 < caretOffset) {
                        // the caret is after this token, nothing special to do
                    } else if (token.getStartIndex() > caretOffset) {
                        // the token is after the caret, no need to include it
                        token = new CaretToken(source, Token.DEFAULT_CHANNEL, caretOffset, caretOffset);
                        caretToken = token;
                    } else {
                        if (token.getStopIndex() + 1 == caretOffset) {
                            // the caret is at the end of this token, and this
                            // isn't a word token
                            if (!WORD_PATTERN.matcher(token.getText()).matches()) {
                                return token;
                            }
                        }

                        // the caret is in the middle of or at the end of this token
                        token = new CaretToken(token);
                        caretToken = token;
                    }
                    
                    return token;
                }

                return emitEOF();
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

            private Token emitEOF() {
                CharStream input = source.getInputStream();
                WritableToken eof = new CommonToken(this,Token.EOF,
                                                    Token.DEFAULT_CHANNEL,
                                                    input.index(),input.index()-1);
                eof.setLine(getLine());
                // The character position for EOF is one beyond the position of
                // the previous token's last character
                int cpos = getCharPositionInLine();
                eof.setCharPositionInLine(cpos);
                return eof;
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
            public void recover(BaseRecognizer recognizer, RecognitionException e) {
                if (recognizer instanceof GrammarParser
                    && recognizer.getInputStream() instanceof TokenStream
                    && ((TokenStream)recognizer.getInputStream()).LT(1) instanceof CaretToken) {

//                    int stateNumber = recognizer.getContext().s;
//                    ATNState state = recognizer.getATN().states.get(stateNumber);
//                    if (state instanceof DecisionState && recognizer.getInputStream() instanceof ObjectStream) {
//                        int decision = ((DecisionState)state).decision;
//                        ParserATNSimulator simulator = recognizer.getInterpreter();
//                        int prediction = simulator.adaptivePredict((ObjectStream)recognizer.getInputStream(), decision, recognizer.getContext());
//                    }

                    GrammarParser parser = (GrammarParser)recognizer;
                    CaretToken token = (CaretToken)((TokenStream)recognizer.getInputStream()).LT(1);
                    CompletionParserATNSimulator interpreter = (CompletionParserATNSimulator)recognizer.getInterpreter();

                    throw new CaretReachedException(parser.getContext(), token, interpreter.caretTransitions, e);
                }

                super.recover(recognizer, e);
            }

            @Override
            public Object recoverInline(BaseRecognizer recognizer) throws RecognitionException {
                if (recognizer instanceof GrammarParser
                    && recognizer.getInputStream() instanceof TokenStream
                    && ((TokenStream)recognizer.getInputStream()).LT(1) instanceof CaretToken) {

                    GrammarParser parser = (GrammarParser)recognizer;
                    CaretToken token = (CaretToken)((TokenStream)recognizer.getInputStream()).LT(1);

                    CompletionParserATNSimulator interp = (CompletionParserATNSimulator)recognizer.getInterpreter();
                    int stateNumber = recognizer.getContext().s;
                    ATNState state = interp.atn.states.get(stateNumber);

                    OrderedHashSet<ATNConfig> closure = new OrderedHashSet<ATNConfig>();
                    for (int i = 0; i < state.getNumberOfTransitions(); i++) {
                        Transition transition = state.transition(i);
                        if (transition.isEpsilon()) {
                            ATNState target = transition.target;
                            ATNConfig config = new ATNConfig(target, i + 1, recognizer.getContext());
                            Set<ATNConfig> closureBusy = new HashSet<ATNConfig>();
                            interp.closure(config, closure, null, closureBusy);
                        }
                    }

                    if (!state.onlyHasEpsilonTransitions()) {
                        closure.add(new ATNConfig(state, 1, recognizer.getContext()));
                    }

                    OrderedHashSet<ATNConfig> reach = new OrderedHashSet<ATNConfig>();
                    LinkedHashMap<ATNConfig, List<Transition>> transitions = null;
                    
                    int ncl = closure.size();
                    for (int ci = 0; ci < ncl; ci++) { // TODO: foreach
                        ATNConfig c = closure.get(ci);

                        List<Transition> configTransitions = null;

                        int n = c.state.getNumberOfTransitions();
                        for (int ti = 0; ti < n; ti++) {               // for each transition
                            Transition trans = c.state.transition(ti);
                            ATNState target = interp.getReachableTarget(trans, CaretToken.CARET_TOKEN_TYPE);
                            if (target != null) {
                                if (transitions == null) {
                                    transitions = new LinkedHashMap<ATNConfig, List<Transition>>();
                                }

                                if (configTransitions == null) {
                                    configTransitions = new ArrayList<Transition>();
                                    transitions.put(c, configTransitions);
                                }

                                configTransitions.add(trans);

                                Set<ATNConfig> closureBusy = new HashSet<ATNConfig>();
                                interp.closure(new ATNConfig(c, target), reach, null, closureBusy);
                            }
                        }
                    }

                    throw new CaretReachedException(parser.getContext(), token, transitions, null);
                }

                return super.recoverInline(recognizer);
            }
        }

        private static class CompletionParserATNSimulator extends ParserATNSimulator {

            private Map<ATNConfig, List<Transition>> caretTransitions;

            public CompletionParserATNSimulator(BaseRecognizer parser, ATN atn) {
                super(parser, atn);
            }

            @Override
            public void closure(ATNConfig config, OrderedHashSet<ATNConfig> configs, DecisionState decState, Set<ATNConfig> closureBusy) {
                super.closure(config, configs, decState, closureBusy);
            }

            @Override
            public int execATN(ObjectStream input, DFA dfa, int startIndex, OrderedHashSet<ATNConfig> s0, boolean useContext) {
                if (debug) {
                    System.out.println("execATN decision " + dfa.decision + " exec LA(1)==" + getLookaheadName(input));
                }
                ATN_failover++;
                OrderedHashSet<ATNConfig> closure = new OrderedHashSet<ATNConfig>();

                closure.addAll(s0);

                if (debug) {
                    System.out.println("start state closure=" + closure);
                }

                int t = input.LA(1);
                if (t == Token.EOF && prevAccept != null) {
                    // computeStartState must have reached end of rule
                    return prevAccept.alt;
                }

                DecisionState decState = null;
                if (atn.decisionToState.size() > 0) {
                    decState = atn.decisionToState.get(dfa.decision);
                }
                if (debug) {
                    System.out.println("decision state = " + decState);
                }

                prevAccept = null;
                prevAcceptIndex = -1;
                OrderedHashSet<ATNConfig> reach = new OrderedHashSet<ATNConfig>();
                LinkedHashMap<ATNConfig, List<Transition>> transitions = null;

                while (true) { // while more work
                    if (debug) {
                        System.out.println("in reach starting closure: " + closure);
                    }

                    int ncl = closure.size();
                    for (int ci = 0; ci < ncl; ci++) { // TODO: foreach
                        ATNConfig c = closure.get(ci);
                        if (debug) {
                            System.out.println("testing " + getTokenName(t) + " at " + c.toString());
                        }

                        List<Transition> configTransitions = null;

                        int n = c.state.getNumberOfTransitions();
                        for (int ti = 0; ti < n; ti++) {               // for each transition
                            Transition trans = c.state.transition(ti);
                            ATNState target = getReachableTarget(trans, t);
                            if (target != null) {
                                if (t == CaretToken.CARET_TOKEN_TYPE) {
                                    if (transitions == null) {
                                        transitions = new LinkedHashMap<ATNConfig, List<Transition>>();
                                    }

                                    if (configTransitions == null) {
                                        configTransitions = new ArrayList<Transition>();
                                        transitions.put(c, configTransitions);
                                    }

                                    configTransitions.add(trans);
                                }

                                Set<ATNConfig> closureBusy = new HashSet<ATNConfig>();
                                closure(new ATNConfig(c, target), reach, decState, closureBusy);
                            }
                        }
                    }

                    // resolve ambig in DFAState for reach
                    Set<Integer> ambigAlts = getAmbiguousAlts(reach);
                    if (ambigAlts != null) {
                        if (debug) {
                            int i = -1;
                            if (outerContext != null && outerContext.s >= 0) {
                                i = atn.states.get(outerContext.s).ruleIndex;
                            }
                            String rname = getRuleName(i);
                            System.out.println("AMBIG dec " + dfa.decision + " in " + rname + " for alt " + ambigAlts + " upon "
                                + getInputString(input, startIndex));
                            System.out.println("REACH=" + reach);
                        }
                        dfa.conflict = true; // at least one DFA state is ambiguous
                        if (!userWantsCtxSensitive) {
                            reportConflict(startIndex, input.index(), ambigAlts, reach);
                        }

                        //				ATNState loc = atn.states.get(outerContext.s);
                        //				String rname = recog.getRuleNames()[loc.ruleIndex];
                        //				System.out.println("AMBIG orig="+outerContext.toString((BaseRecognizer)recog)+" for alt "+ambigAlts+" upon "+
                        //								   input.toString(startIndex, input.index()));
                        if (!userWantsCtxSensitive || useContext) {
                            // resolve ambiguity
                            if (decState.isGreedy) {
                                // if greedy, resolve in favor of alt coming first
                                resolveToMinAlt(reach, ambigAlts);
                            } else {
                                // if nongreedy loop, always pick exit branch to match
                                // what follows instead of re-entering loop
                                resolveNongreedyToExitBranch(reach, ambigAlts);
                            }
                        } else {
                            return retryWithContext(input, dfa, startIndex, outerContext,
                                                    closure, t, reach, ambigAlts);
                        }
                    }

                    // if reach predicts single alt, can stop

                    int uniqueAlt = getUniqueAlt(reach);
                    if (uniqueAlt != ATN.INVALID_ALT_NUMBER) {
                        if (debug) {
                            System.out.println("PREDICT alt " + uniqueAlt
                                + " decision " + dfa.decision
                                + " at index " + input.index());
                        }
                        addDFAEdge(dfa, closure, t, reach);
                        makeAcceptState(dfa, reach, uniqueAlt);
                        return uniqueAlt;
                    } else if (t == CaretToken.CARET_TOKEN_TYPE) {
                        caretTransitions = transitions;
                        break;
                    }

                    if (decState != null && !decState.isGreedy) {
                        // if we reached end of rule via exit branch, we matched
                        int exitAlt = 2;
                        ATNConfig cstop = configWithAltAtStopState(reach, exitAlt);
                        if (cstop != null) {
                            if (debug) {
                                System.out.println("nongreedy at stop state for exit branch");
                            }
                            prevAccept = cstop;
                            prevAcceptIndex = input.index();
                            break;
                        }
                    }

                    if (reach.size() == 0) {
                        break;
                    }

                    // If we matched t anywhere, need to consume and add closer-t->reach DFA edge
                    // else error if no previous accept
                    input.consume();
                    addDFAEdge(dfa, closure, t, reach);
                    t = input.LA(1);

                    // swap to avoid reallocating space
                    OrderedHashSet<ATNConfig> tmp = reach;
                    reach = closure;
                    closure = tmp;
                    reach.clear(); // TODO: THIS MIGHT BE SLOW! kills each element; realloc might be faster
                }

                if (prevAccept == null) {
                    //			System.out.println("no viable token at input "+ getLookaheadName(input) +", index "+input.index());
                    throwNoViableAlt(input, outerContext, closure, startIndex);
                }

                if (debug) {
                    System.out.println("PREDICT " + prevAccept + " index " + prevAccept.alt);
                }
                return prevAccept.alt;
            }

            private static final List<Integer> WORDLIKE_TOKEN_TYPES =
                new ArrayList<Integer>() {{
                    // keywords
                    add(GrammarLexer.OPTIONS);
                    add(GrammarLexer.TOKENS);
                    add(GrammarLexer.SCOPE);
                    add(GrammarLexer.IMPORT);
                    add(GrammarLexer.FRAGMENT);
                    add(GrammarLexer.LEXER);
                    add(GrammarLexer.PARSER);
                    add(GrammarLexer.TREE);
                    add(GrammarLexer.GRAMMAR);
                    add(GrammarLexer.PROTECTED);
                    add(GrammarLexer.PUBLIC);
                    add(GrammarLexer.PRIVATE);
                    add(GrammarLexer.RETURNS);
                    add(GrammarLexer.LOCALS);
                    add(GrammarLexer.THROWS);
                    add(GrammarLexer.CATCH);
                    add(GrammarLexer.FINALLY);
                    add(GrammarLexer.TEMPLATE);
                    add(GrammarLexer.MODE);
                    // atoms
                    add(GrammarLexer.RULE_REF);
                    add(GrammarLexer.TOKEN_REF);
                    // special
                    add(GrammarLexer.ARG_ACTION_WORD);
                    add(GrammarLexer.ACTION_REFERENCE);
                    add(GrammarLexer.ACTION_WORD);

                    Collections.sort(this);
                }};

            @Override
            public ATNState getReachableTarget(Transition trans, int ttype) {
                if (ttype == CaretToken.CARET_TOKEN_TYPE) {
                    if (trans instanceof AtomTransition) {
                        AtomTransition at = (AtomTransition)trans;
                        if (Collections.binarySearch(WORDLIKE_TOKEN_TYPES, at.label) >= 0) {
                            return at.target;
                        }
                    } else if (trans instanceof SetTransition) {
                        SetTransition st = (SetTransition)trans;
                        boolean not = trans instanceof NotSetTransition;
                        for (int t : WORDLIKE_TOKEN_TYPES) {
                            if (!not && st.set.contains(t) || not && !st.set.contains(t)) {
                                return st.target;
                            }
                        }
                    } else if (trans instanceof RangeTransition) {
                        RangeTransition rt = (RangeTransition)trans;
                        int lb = Collections.binarySearch(WORDLIKE_TOKEN_TYPES, rt.from);
                        int ub = Collections.binarySearch(WORDLIKE_TOKEN_TYPES, rt.to);
                        if (lb >= 0 || ub >= 0 || lb != ub) {
                            return rt.target;
                        }
                    } else if (trans instanceof WildcardTransition) {
                        return trans.target;
                    }

                    return null;
                }

                return super.getReachableTarget(trans, ttype);
            }

            private String getRuleName(int index) {
                if ( parser!=null && index>=0 ) return parser.getRuleNames()[index];
                return "<rule "+index+">";
            }
        }

        private static class CaretReachedException extends RuntimeException {

            private final RuleContext finalContext;
            private final CaretToken caretToken;
            private final Map<ATNConfig, List<Transition>> transitions;

            public CaretReachedException(RuleContext finalContext, CaretToken caretToken, Map<ATNConfig, List<Transition>> transitions, RecognitionException cause) {
                super(cause);
                this.finalContext = finalContext;
                this.caretToken = caretToken;
                this.transitions = transitions;
            }

            public RuleContext getFinalContext() {
                return finalContext;
            }

            public CaretToken getCaretToken() {
                return caretToken;
            }

            public Map<ATNConfig, List<Transition>> getTransitions() {
                return transitions;
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
                if (ctx.name != null) {
                    enclosingRuleName = ctx.name.start;
                }
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
