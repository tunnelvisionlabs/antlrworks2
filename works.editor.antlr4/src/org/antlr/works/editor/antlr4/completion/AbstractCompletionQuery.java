/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.completion;

import com.tvl.api.editor.completion.Completion;
import com.tvl.spi.editor.completion.CompletionDocumentation;
import com.tvl.spi.editor.completion.CompletionItem;
import com.tvl.spi.editor.completion.CompletionProvider;
import com.tvl.spi.editor.completion.CompletionResultSet;
import com.tvl.spi.editor.completion.support.AsyncCompletionQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
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
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.editor.BaseDocument;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractCompletionQuery extends AsyncCompletionQuery {
    // -J-Dorg.antlr.works.editor.antlr4.completion.AbstractCompletionQuery.level=FINE
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

    protected List<CompletionItem> results;
    protected boolean possibleDeclaration;

    private CompletionDocumentation documentation;
    private CompletionToolTip toolTip;
    private int toolTipOffset;
    private String filterPrefix;
    private byte hasAdditionalItems;
    protected TrackingPositionRegion applicableTo;

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

    public final int getQueryType() {
        return queryType;
    }

    public final boolean isExtend() {
        return extend;
    }

    public final int getCaretOffset() {
        return caretOffset;
    }

    public final JTextComponent getComponent() {
        return component;
    }

    public final boolean isExplicitQuery() {
        return (queryType & AbstractCompletionProvider.AUTO_QUERY_TYPE) == 0;
    }

    public final CompletionDocumentation getDocumentation() {
        return documentation;
    }

    public final CompletionToolTip getToolTip() {
        return toolTip;
    }

    public final int getToolTipOffset() {
        return toolTipOffset;
    }

    public final TrackingPositionRegion getApplicableTo() {
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

            LOGGER.log(Level.FINE, "Hiding the completion query in AbstractCompletionQuery.preQueryUpdate().");
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
            Future<Void> value = query(doc, caretOffset);
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
        } catch (InterruptedException | ExecutionException | RuntimeException | Error ex) {
            LOGGER.log(Level.WARNING, "An exception occurred while processing a completion query.", ex);
        } finally {
            resultSet.finish();
        }
    }

    @CheckForNull
    protected Future<Void> query(Document doc, int caretOffset) {
        this.caretOffset = caretOffset;
        if ((queryType & CompletionProvider.TOOLTIP_QUERY_TYPE) == CompletionProvider.TOOLTIP_QUERY_TYPE || isQueryContext(doc, caretOffset)) {
            results = null;
            documentation = null;
            if (toolTip != null) {
                toolTip.clearData();
            }

            applicableTo = null;
            if ((queryType & CompletionProvider.DOCUMENTATION_QUERY_TYPE) == CompletionProvider.DOCUMENTATION_QUERY_TYPE) {
                LOGGER.log(Level.WARNING, "Documentation query support is not yet implemented.");
                return null;
            }

            Future<Void> value = getParserTaskManager().scheduleHighPriority(getTask((BaseDocument)doc));
            return value;
        }

        return null;
    }

    protected boolean isQueryContext(Document doc, int caretOffset) {
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
                LOGGER.log(Level.WARNING, e.getMessage(), e);
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
                        LOGGER.log(Level.FINE, "Hiding the completion query in AbstractCompletionQuery.filter().");
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
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
        }

        resultSet.finish();
    }

    protected void handleDeclarationItem(CompletionResultSet resultSet) {
        if (possibleDeclaration && !isExplicitQuery() && getApplicableTo() != null) {
            VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(component.getDocument());
            DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();
            SnapshotPositionRegion applicableSpan = getApplicableTo().getRegion(snapshot);
            if (applicableSpan.getLength() > 0) {
                resultSet.addDeclarationItem(createDeclarationCompletionItem(component.getDocument(), getApplicableTo()));
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
        Pattern letterOrderPattern = BaseCompletionController.getLetterOrderPattern(prefix, false);
        String lowercasePrefix = prefix.toLowerCase(Locale.getDefault());
        List<CompletionItem> result = new ArrayList<>();
        for (CompletionItem item : data) {
            String insertPrefix = item.getInsertPrefix().toString();
            if (insertPrefix.toLowerCase(Locale.getDefault()).contains(lowercasePrefix)) {
                result.add(item);
            } else if (prefixBoundaryPattern != null && prefixBoundaryPattern.matcher(insertPrefix).matches()) {
                result.add(item);
            } else if (letterOrderPattern != null && letterOrderPattern.matcher(insertPrefix).find()) {
                result.add(item);
            }
        }

        return result;
    }

    protected abstract class Task implements Callable<Void> {
        private final BaseDocument document;

        public Task(@NonNull BaseDocument document) {
            Parameters.notNull("document", document);

            this.document = document;
        }

        @NonNull
        public BaseDocument getDocument() {
            return document;
        }

        @Override
        public Void call() {
            runImpl(document);
            return null;
        }

        protected abstract void runImpl(BaseDocument document);
    }
}
