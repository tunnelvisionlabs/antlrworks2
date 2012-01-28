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
package org.antlr.works.editor.antlr4.completion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.DecisionState;
import org.antlr.v4.runtime.atn.StarLoopEntryState;
import org.antlr.v4.runtime.atn.StarLoopbackState;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.netbeans.api.editor.completion.Completion;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.completion.CompletionDocumentation;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractCompletionQuery extends AsyncCompletionQuery {
    // -J-Dorg.antlr.works.editor.grammar.GrammarCompletionQuery.level=FINE
    private static final Logger LOGGER = Logger.getLogger(AbstractCompletionQuery.class.getName());

    private static final int NO_ADDITIONAL_ITEMS = 0;
    private static final int ADDITIONAL_IMPORTED_ITEMS = 1;
    private static final int ADDITIONAL_MEMBER_ITEMS = 2;

    /** ^[\\$A-Za-z_][A-Za-z0-9_]*$ */
    protected static final Pattern WORD_PATTERN = Pattern.compile("^[\\$A-Za-z_][A-Za-z0-9_]*$");

    private static final String EMPTY = "";

    private final AbstractCompletionProvider completionProvider;
    private final int queryType;
    private final boolean hasTask;
    private final boolean extend;
    private int caretOffset;

    private JTextComponent component;
    private CompletionToolTip toolTip;

    protected List<CompletionItem> results;
    protected boolean possibleDeclaration;

    private CompletionDocumentation documentation;
    private String filterPrefix;
    private byte hasAdditionalItems;
    protected TrackingPositionRegion applicableTo;
    private int toolTipOffset;

    protected AbstractCompletionQuery(AbstractCompletionProvider completionProvider, int queryType, int caretOffset, boolean hasTask, boolean extend) {
        this.completionProvider = completionProvider;
        this.queryType = queryType;
        this.caretOffset = caretOffset;
        this.hasTask = hasTask;
        this.extend = extend;
    }

    public AbstractCompletionProvider getCompletionProvider() {
        return completionProvider;
    }

    public int getQueryType() {
        return queryType;
    }

    public boolean isExtend() {
        return extend;
    }

    public int getCaretOffset() {
        return caretOffset;
    }

    public JTextComponent getComponent() {
        return component;
    }

    public boolean isExplicitQuery() {
        return (queryType & AbstractCompletionProvider.AUTO_QUERY_TYPE) == 0;
    }

    public TrackingPositionRegion getApplicableTo() {
        return applicableTo;
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

            Completion.get().hideCompletion();
        }
    }

    @Override
    protected void prepareQuery(JTextComponent component) {
        this.component = component;
        if ((queryType & CompletionProvider.TOOLTIP_QUERY_TYPE) == CompletionProvider.TOOLTIP_QUERY_TYPE) {
            this.toolTip = new CompletionToolTip(component);
        }
    }

    protected ParserTaskManager getParserTaskManager() {
        return Lookup.getDefault().lookup(ParserTaskManager.class);
    }

    @Override
    @NbBundle.Messages({
        "scanning_in_progress=Scanning in progress..."
    })
    protected void query(CompletionResultSet resultSet, Document doc, int caretOffset) {
        try {
            this.caretOffset = caretOffset;
            if ((queryType & CompletionProvider.TOOLTIP_QUERY_TYPE) == CompletionProvider.TOOLTIP_QUERY_TYPE || isQueryContext(resultSet, doc, caretOffset)) {
                results = null;
                documentation = null;
                if (toolTip != null) {
                    toolTip.clearData();
                }

                applicableTo = null;
                if ((queryType & CompletionProvider.DOCUMENTATION_QUERY_TYPE) == CompletionProvider.DOCUMENTATION_QUERY_TYPE) {
                    throw new UnsupportedOperationException("Not implemented yet.");
                }

                Future<Void> value = getParserTaskManager().scheduleHighPriority(getTask((BaseDocument)doc));
                if (value != null) {
                    if (!value.isDone()) {
                        component.putClientProperty("completion-active", Boolean.FALSE);
                        resultSet.setWaitText(Bundle.scanning_in_progress());
                        value.get();
                    }

                    if ((queryType & CompletionProvider.COMPLETION_QUERY_TYPE) != 0) {
                        if (results != null) {
                            resultSet.addAllItems(results);
                        }

                        handleDeclarationItem(resultSet);
                        resultSet.setHasAdditionalItems(hasAdditionalItems != NO_ADDITIONAL_ITEMS);

                        if (hasAdditionalItems == ADDITIONAL_IMPORTED_ITEMS) {
                            resultSet.setHasAdditionalItemsText(Bundle.GCP_imported_items());
                        } else if (hasAdditionalItems == ADDITIONAL_MEMBER_ITEMS) {
                            resultSet.setHasAdditionalItemsText(Bundle.GCP_instance_members());
                        }
                    } else if ((queryType & CompletionProvider.TOOLTIP_QUERY_TYPE) == CompletionProvider.TOOLTIP_QUERY_TYPE) {
                        if (toolTip != null && toolTip.hasData()) {
                            resultSet.setToolTip(toolTip);
                        }
                    } else if ((queryType & CompletionProvider.DOCUMENTATION_QUERY_TYPE) == CompletionProvider.DOCUMENTATION_QUERY_TYPE) {
                        throw new UnsupportedOperationException("Not implemented yet.");
                    }

                    if (applicableTo != null) {
                        VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(doc);
                        resultSet.setAnchorOffset(applicableTo.getStartPosition(textBuffer.getCurrentSnapshot()).getOffset());
                    }
                }
            }
        } catch (Exception ex) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "An exception occurred while processing a completion query.", ex);
            }
        } finally {
            resultSet.finish();
        }
    }

    protected boolean isQueryContext(CompletionResultSet resultSet, Document doc, int caretOffset) {
        return getCompletionProvider().isContext(getComponent(), caretOffset, queryType);
    }

    @Override
    protected boolean canFilter(JTextComponent component) {
        filterPrefix = null;
        int newOffset = component.getSelectionStart();
        if ((queryType & CompletionProvider.COMPLETION_QUERY_TYPE) != 0) {
            if (applicableTo != null) {
                VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(component.getDocument());
                DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();
                SnapshotPositionRegion applicableSpan = getApplicableTo().getRegion(snapshot);
                int caretPosition = component.getCaretPosition();
                // not using SnapshotPositionRegion.contains because we need to use an inclusive check at the end of the span
                if (applicableSpan.getStart().getOffset() <= caretPosition && applicableSpan.getEnd().getOffset() >= caretPosition) {
                    OffsetRegion filterSpan = OffsetRegion.fromBounds(applicableSpan.getStart().getOffset(), component.getCaretPosition());
                    filterPrefix = snapshot.subSequence(filterSpan.getStart(), filterSpan.getEnd()).toString();
                    if (!filterPrefix.isEmpty() && !WORD_PATTERN.matcher(filterPrefix).matches()) {
                        filterPrefix = null;
                    }
                }

                return true;
            }
        } else if ((queryType & CompletionProvider.RESERVED_QUERY_MASK) == CompletionProvider.TOOLTIP_QUERY_TYPE) {
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
            if ((queryType & CompletionProvider.COMPLETION_QUERY_TYPE) != 0) {
                if (results != null) {
                    if (filterPrefix != null) {
                        Collection<? extends CompletionItem> filtered = getFilteredData(results, filterPrefix);
                        resultSet.addAllItems(filtered);
                        handleDeclarationItem(resultSet);
                        resultSet.setHasAdditionalItems(hasAdditionalItems > 0);
                    } else {
                        Completion.get().hideDocumentation();
                        Completion.get().hideCompletion();
                    }
                }
            } else if ((queryType & CompletionProvider.RESERVED_QUERY_MASK) == CompletionProvider.TOOLTIP_QUERY_TYPE) {
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

    protected void handleDeclarationItem(CompletionResultSet resultSet) {
        if (possibleDeclaration && !isExplicitQuery() && getApplicableTo() != null) {
            VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(component.getDocument());
            DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();
            SnapshotPositionRegion applicableSpan = getApplicableTo().getRegion(snapshot);
            if (applicableSpan.getLength() > 0) {
                resultSet.addItem(createDeclarationCompletionItem(component.getDocument(), getApplicableTo()));
            }
        }
    }

    protected abstract CompletionItem createDeclarationCompletionItem(Document document, TrackingPositionRegion applicableTo);

    protected abstract Task getTask(BaseDocument document);

    protected Collection<? extends CompletionItem> getFilteredData(List<CompletionItem> data, String prefix) {
        if (prefix.length() == 0) {
            return data;
        }

        Pattern prefixBoundaryPattern = BaseCompletionController.getPrefixBoundaryPattern(prefix, false);
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

    protected abstract class Task implements Callable<Void> {
        private final BaseDocument document;

        public Task(BaseDocument document) {
            this.document = document;
        }

        @Override
        public Void call() {
            runImpl(document);
            return null;
        }

        protected abstract void runImpl(BaseDocument document);

        protected Map<RuleContext, CaretReachedException> getParseTrees(CodeCompletionParser parser) {
            List<MultipleDecisionData> potentialAlternatives = new ArrayList<MultipleDecisionData>();
            List<Integer> currentPath = new ArrayList<Integer>();
            Map<RuleContext, CaretReachedException> results = new IdentityHashMap<RuleContext, CaretReachedException>();
            while (true) {
                tryParse(parser, potentialAlternatives, currentPath, results);
                if (!incrementCurrentPath(potentialAlternatives, currentPath)) {
                    break;
                }
            }

            if (LOGGER.isLoggable(Level.FINE)) {
                for (Map.Entry<RuleContext, CaretReachedException> entry : results.entrySet()) {
                    LOGGER.log(Level.FINE, entry.getKey().toStringTree((Parser)parser));
                }
            }

            return results;
        }

        protected boolean incrementCurrentPath(List<MultipleDecisionData> potentialAlternatives, List<Integer> currentPath) {
            for (int i = currentPath.size() - 1; i >= 0; i--) {
                if (currentPath.get(i) < potentialAlternatives.get(i).alternatives.length - 1) {
                    currentPath.set(i, currentPath.get(i) + 1);
                    return true;
                }

                potentialAlternatives.remove(i);
                currentPath.remove(i);
            }

            return false;
        }

        protected void tryParse(CodeCompletionParser parser, List<MultipleDecisionData> potentialAlternatives, List<Integer> currentPath, Map<RuleContext, CaretReachedException> results) {
            RuleContext parseTree;
            try {
                parser.getTokenStream().seek(0);
                parser.getInterpreter().setFixedDecisions(potentialAlternatives, currentPath);
                parseTree = parseImpl(parser);
                results.put(parseTree, null);
            } catch (CaretReachedException ex) {
                if (ex.getTransitions() == null) {
                    return;
                }

                for (parseTree = ex.getFinalContext(); parseTree.getParent() != null; parseTree = (RuleContext)parseTree.getParent()) {
                    // intentionally blank
                }

                if (ex.getCause() instanceof FailedPredicateException) {
                    return;
                }

                if (ex.getCause() != null) {
                    IntervalSet alts = new IntervalSet();
                    for (ATNConfig c : ex.getTransitions().keySet()) {
                        alts.add(c.alt);
                    }

                    MultipleDecisionData decisionData = new MultipleDecisionData();
                    decisionData.inputIndex = parser.getInputStream().index();
                    decisionData.decision = 0;
                    if (ex.getCause() != null) {
                        ATNState state = parser.getATN().states.get(((ParserRuleContext<?>)ex.getCause().getCtx()).s);
                        if (state instanceof StarLoopbackState) {
                            assert state.getNumberOfTransitions() == 1 && state.onlyHasEpsilonTransitions();
                            assert state.transition(0).target instanceof StarLoopEntryState;
                            state = state.transition(0).target;
                        }

                        if (state instanceof DecisionState) {
                            decisionData.decision = ((DecisionState)state).decision;
                        } else {
                            LOGGER.log(Level.FINE, "No decision number found for state {0}.", state.stateNumber);
                            // continuing is likely to never terminate
                            return;
                        }
                    }
                    decisionData.alternatives = alts.toArray();
                    potentialAlternatives.add(decisionData);
                    currentPath.add(-1);
                }
                else {
                    results.put(parseTree, ex);
                }
            } catch (RecognitionException ex) {
                // not a viable path
            }
        }

        protected abstract RuleContext parseImpl(CodeCompletionParser parser);
    }
}
