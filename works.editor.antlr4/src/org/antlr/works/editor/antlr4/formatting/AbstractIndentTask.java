/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.formatting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.completion.ReferenceAnchors;
import org.antlr.netbeans.editor.formatting.CodeStyle;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Tuple2;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.antlr4.classification.TaggerTokenSource;
import org.antlr.works.editor.antlr4.completion.CaretReachedException;
import org.antlr.works.editor.antlr4.completion.CaretToken;
import org.antlr.works.editor.antlr4.completion.CodeCompletionTokenSource;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.indent.spi.ExtraLock;
import org.netbeans.modules.editor.indent.spi.IndentTask;
import org.openide.text.NbDocument;
import org.openide.util.Lookup;
import org.openide.util.NotImplementedException;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractIndentTask implements IndentTask {
    // -J-Dorg.antlr.works.editor.antlr4.formatting.AbstractIndentTask.level=FINE
    protected static final Logger LOGGER = Logger.getLogger(AbstractIndentTask.class.getName());

    private final Context _context;
    private final ParserTaskManager _taskManager;

    private DocumentSnapshot _snapshot;

    protected AbstractIndentTask(@NonNull Context context) {
        this(context, Lookup.getDefault().lookup(ParserTaskManager.class));
    }

    protected AbstractIndentTask(@NonNull Context context, @NonNull ParserTaskManager taskManager) {
        Parameters.notNull("context", context);
        Parameters.notNull("taskManager", taskManager);

        _context = context;
        _taskManager = taskManager;
    }

    public final Context getContext() {
        return _context;
    }

    public final ParserTaskManager getTaskManager() {
        return _taskManager;
    }

    public final DocumentSnapshot getSnapshot() {
        return _snapshot;
    }

    @Override
    public final void reindent() throws BadLocationException {
        if (!smartReindent()) {
            fallbackReindent();
        }
    }

    @Override
    public ExtraLock indentLock() {
        return null;
    }

    public boolean smartReindent() throws BadLocationException {
        if (!(getContext().document() instanceof StyledDocument)) {
            return false;
        }

        StyledDocument document = (StyledDocument)getContext().document();
        VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(getContext().document());
        _snapshot = versionedDocument.getCurrentSnapshot();

        SnapshotPosition contextEndPosition = new SnapshotPosition(getSnapshot(), getContext().endOffset());
        SnapshotPosition endPosition = contextEndPosition.getContainingLine().getEndIncludingLineBreak();
        SnapshotPosition endPositionOnLine = contextEndPosition.getContainingLine().getEnd();

        ReferenceAnchors anchors = findNearestAnchors(getTaskManager(), getSnapshot(), endPosition.getOffset());
        final Anchor previous = anchors.getPrevious();

        Tagger<TokenTag<Token>> tagger = getTagger();
        int regionEnd = Math.min(getSnapshot().length(), endPosition.getOffset() + 1);
        OffsetRegion region;
        if (anchors.getEnclosing() != null) {
            region = OffsetRegion.fromBounds(anchors.getEnclosing().getSpan().getStartPosition(getSnapshot()).getOffset(), regionEnd);
        } else if (previous != null) {
            // at least for now, include the previous span due to the way error handling places bounds on an anchor
            region = OffsetRegion.fromBounds(previous.getSpan().getStartPosition(getSnapshot()).getOffset(), regionEnd);
        } else {
            region = OffsetRegion.fromBounds(0, regionEnd);
        }

        LOGGER.log(Level.FINE, "Reindent from anchor region: {0}.", region);

        TaggerTokenSource<Token> taggerTokenSource = new TaggerTokenSource<Token>(tagger, new SnapshotPositionRegion(getSnapshot(), region));
        TokenSource<Token> tokenSource = new CodeCompletionTokenSource(endPosition.getOffset(), taggerTokenSource);
        CommonTokenStream tokens = new CommonTokenStream(tokenSource);

        Map<RuleContext<Token>, CaretReachedException> parseTrees = getParseTrees(tokens, anchors);
        if (parseTrees == null) {
            return false;
        }

        NavigableMap<Integer, List<Map.Entry<RuleContext<Token>, CaretReachedException>>> indentLevels =
            new TreeMap<Integer, List<Map.Entry<RuleContext<Token>, CaretReachedException>>>();
        for (Map.Entry<RuleContext<Token>, CaretReachedException> parseTree : parseTrees.entrySet()) {
            if (parseTree.getValue() == null) {
                continue;
            }

            ParseTree<Token> firstNodeOnLine = findFirstNodeAfterOffset(parseTree.getKey(), endPositionOnLine.getContainingLine().getStart().getOffset());
            if (firstNodeOnLine == null) {
                firstNodeOnLine = parseTree.getValue().getFinalContext();
            }

            if (firstNodeOnLine == null) {
                continue;
            }

            int indentationLevel = getIndent(parseTree, firstNodeOnLine, getContext().lineStartOffset(getContext().endOffset()));
            if (indentationLevel < 0) {
                continue;
            }

            List<Map.Entry<RuleContext<Token>, CaretReachedException>> indentList =
                indentLevels.get(indentationLevel);
            if (indentList == null) {
                indentList = new ArrayList<Map.Entry<RuleContext<Token>, CaretReachedException>>();
                indentLevels.put(indentationLevel, indentList);
            }

            indentList.add(parseTree);
        }

        if (indentLevels.isEmpty()) {
            return false;
        }

        int indentLevel = indentLevels.firstKey();
        if (indentLevels.size() > 1) {
            // TODO: resolve multiple possibilities
        }

        int startLine = NbDocument.findLineNumber(document, getContext().startOffset());
        int endLine;
        if (getContext().endOffset() <= getContext().startOffset()) {
            endLine = startLine;
        } else {
            endLine = NbDocument.findLineNumber(document, getContext().endOffset() - 1);
        }

        for (int line = startLine; line <= endLine; line++) {
            int currentOffset = NbDocument.findLineOffset(document, startLine);
            getContext().modifyIndent(currentOffset, indentLevel);
        }

        return true;
    }

    protected ReferenceAnchors findNearestAnchors(ParserTaskManager taskManager, DocumentSnapshot snapshot, int endOffset) {
        List<? extends Anchor> anchors = getDynamicAnchorPoints();

        // the innermost anchor enclosing the caret
        Anchor enclosing = null;
        // the last anchor starting before the caret
        Anchor previous = null;
        if (anchors != null) {
            Anchor next = null;

            /*
             * parse the current rule
             */
            for (Anchor anchor : anchors) {
                if (anchor.getSpan().getStartPosition(snapshot).getOffset() <= endOffset) {
                    previous = anchor;
                    if (anchor.getSpan().getEndPosition(snapshot).getOffset() > endOffset) {
                        enclosing = anchor;
                    }
                } else {
                    next = anchor;
                    break;
                }
            }
        }

        return new ReferenceAnchors(previous, enclosing);
    }

    protected <Symbol extends Token> TerminalNode<Symbol> findFirstNodeAfterOffset(ParseTree<Symbol> tree, int offset) {
        TerminalNode<Symbol> lastNode = ParseTrees.getStopNode(tree);
        if (lastNode == null) {
            return null;
        }

        if (lastNode.getSymbol() instanceof CaretToken) {
            throw new NotImplementedException();
        } else if (lastNode.getSymbol().getStartIndex() < offset) {
            return null;
        }

        if (tree instanceof TerminalNode) {
            return (TerminalNode<Symbol>)tree;
        }

        for (int i = 0; i < tree.getChildCount(); i++) {
            TerminalNode<Symbol> node = findFirstNodeAfterOffset(tree.getChild(i), offset);
            if (node != null) {
                return node;
            }
        }

        return null;
    }

    protected boolean isMultilineElement(ParseTree<? extends Token> tree) throws BadLocationException {
        TerminalNode<? extends Token> startNode = ParseTrees.getStartNode(tree);
        TerminalNode<? extends Token> stopNode = ParseTrees.getStopNode(tree);
        if (startNode == null || stopNode == null) {
            // TODO: this can't handle epsilon trees (no TerminalNode descendants)
            return false;
        }

        int startLine = startNode.getSymbol().getLine();
        int stopLine = stopNode.getSymbol().getLine();
        if (stopLine != startLine) {
            return true;
        }

        return isMultilineElement(stopNode.getSymbol());
    }

    protected boolean isMultilineElement(Token token) throws BadLocationException {
        return getContext().lineStartOffset(token.getStartIndex()) != getContext().lineStartOffset(token.getStopIndex());
    }

    protected abstract Tagger<TokenTag<Token>> getTagger();

    protected abstract CodeStyle getCodeStyle();

    protected abstract Map<RuleContext<Token>, CaretReachedException> getParseTrees(CommonTokenStream tokens, ReferenceAnchors anchors);

    protected abstract List<? extends Anchor> getDynamicAnchorPoints();

    protected abstract Set<AlignmentRequirement> getAlignmentRequirement(Map.Entry<RuleContext<Token>, CaretReachedException> parseTree, @NonNull ParseTree<? extends Token> targetElement, ParseTree<? extends Token> ancestor);

    /**
     * Gets the target element and offset which controls the indentation of
     * {@code targetElement}.
     * <p/>
     * Note: The search for ancestor elements continues when the set returned by
     * {@link #getAlignmentRequirement} contains
     * {@link AlignmentRequirement#USE_ANCESTOR}.
     * <p/>
     * Note: When {@code priorSiblings} is not {@code null}, the last element of
     * the list is an ancestor of {@code targetElement}.
     * <p/>
     * Note: It is an error if the returned element starts after
     * {@code targetElement} in the document. If the returned element starts on
     * the same line as {@code targetElement}, the search continues with the
     * parent of {@code container}.
     *
     * @param targetElement The element requiring alignment.
     * @param container An ancestor element containing the target element.
     * @param priorSiblings The children of {@code container} from the first
     * child through the the child containing {@code targetElement}. The last
     * element of {@code priorSiblings} is an ancestor of {@code targetElement}.
     * This argument may be {@code null} when the set returned by
     * {@link #getAlignmentRequirement} does not contain
     * {@link AlignmentRequirement#PRIOR_SIBLING}.
     *
     * @return Returns a tuple; the first element is the target element
     * controlling vertical alignment of the current line, and the second
     * element specifies an offset from the horizontal position of this element
     * in virtual spaces. Return {@code null} to continue searching ancestors.
     */
    @CheckForNull
    protected abstract Tuple2<? extends ParseTree<? extends Token>, Integer> getAlignmentElement(Map.Entry<RuleContext<Token>, CaretReachedException> parseTree, @NonNull ParseTree<? extends Token> targetElement, @NonNull ParseTree<? extends Token> container, @NullAllowed List<? extends ParseTree<? extends Token>> priorSiblings);

    protected int getIndent(final Map.Entry<RuleContext<Token>, CaretReachedException> parseTree, final ParseTree<? extends Token> firstNodeOnLine, int lineStartOffset) throws BadLocationException {
        for (ParseTree<? extends Token> ancestor = firstNodeOnLine; ancestor != null; ancestor = ancestor.getParent()) {
            Set<AlignmentRequirement> requirements = getAlignmentRequirement(parseTree, firstNodeOnLine, ancestor);
            if (requirements.contains(AlignmentRequirement.USE_ANCESTOR)) {
                continue;
            } else if (requirements.contains(AlignmentRequirement.IGNORE_TREE)) {
                return -1;
            }

            List<ParseTree<? extends Token>> siblings = null;
            if (requirements.contains(AlignmentRequirement.PRIOR_SIBLING)) {
                int childCount = ancestor.getChildCount();
                siblings = new ArrayList<ParseTree<? extends Token>>(childCount);
                for (int i = 0; i < childCount; i++) {
                    ParseTree<? extends Token> child = ancestor.getChild(i);
                    siblings.add(child);
                    if (ParseTrees.isAncestorOf(child, firstNodeOnLine)) {
                        break;
                    }
                }
            }

            Tuple2<? extends ParseTree<? extends Token>, Integer> alignmentElement = getAlignmentElement(parseTree, firstNodeOnLine, ancestor, siblings);
            if (alignmentElement == null) {
                continue;
            }

            Token startToken = ParseTrees.getStartSymbol(alignmentElement.getItem1());
            String beginningOfLineText = startToken.getTokenSource().getInputStream().getText(new Interval(startToken.getStartIndex() - startToken.getCharPositionInLine(), startToken.getStartIndex() - 1));
            int elementIndent = 0;
            for (int i = 0; i < beginningOfLineText.length(); i++) {
                if (beginningOfLineText.charAt(i) == '\t') {
                    elementIndent = getCodeStyle().getIndentSize() * (elementIndent / getCodeStyle().getIndentSize() + 1);
                } else {
                    elementIndent++;
                }
            }

            if (ParseTrees.getStartSymbol(firstNodeOnLine) == startToken) {
                LOGGER.log(Level.WARNING, "Attempting to indent a line relative to an element on that line.");
            }

            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "Indent {0} relative to {1} (offset {2}) => {3}", new Object[] { firstNodeOnLine, alignmentElement.getItem1(), alignmentElement.getItem2(), elementIndent + alignmentElement.getItem2() });
            }

            return elementIndent + alignmentElement.getItem2();
        }

        return -1;
    }

    protected void fallbackReindent() throws BadLocationException {
        if (!(getContext().document() instanceof StyledDocument)) {
            return;
        }

        StyledDocument document = (StyledDocument)getContext().document();
        int startLine = NbDocument.findLineNumber(document, getContext().startOffset());

        int endLine;
        if (getContext().endOffset() <= getContext().startOffset()) {
            endLine = startLine;
        } else {
            endLine = NbDocument.findLineNumber(document, getContext().endOffset() - 1);
        }

        int previousIndent;
        if (startLine == 0) {
            previousIndent = 0;
        } else {
            int previousLineOffset = NbDocument.findLineOffset(document, startLine - 1);
            previousIndent = getContext().lineIndent(previousLineOffset);
        }

        for (int line = startLine; line <= endLine; line++) {
            int currentOffset = NbDocument.findLineOffset(document, startLine);
            int currentIndent = getContext().lineIndent(currentOffset);
            if (currentIndent == 0 && previousIndent > 0) {
                getContext().modifyIndent(currentOffset, previousIndent);
            }

            previousIndent = currentIndent;
        }
    }

}
