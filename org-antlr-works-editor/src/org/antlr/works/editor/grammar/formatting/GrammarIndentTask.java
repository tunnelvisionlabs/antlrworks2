/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.formatting;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.antlr4.classification.TaggerTokenSource;
import org.antlr.works.editor.antlr4.completion.CaretReachedException;
import org.antlr.works.editor.antlr4.completion.CaretToken;
import org.antlr.works.editor.antlr4.completion.CodeCompletionErrorStrategy;
import org.antlr.works.editor.antlr4.completion.CodeCompletionTokenSource;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.completion.CodeCompletionGrammarParser;
import org.antlr.works.editor.grammar.completion.GrammarForestParser;
import org.antlr.works.editor.grammar.completion.ParserCache;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParserAnchorListener;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.indent.spi.ExtraLock;
import org.netbeans.modules.editor.indent.spi.IndentTask;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NotImplementedException;

/**
 *
 * @author Sam Harwell
 */
public class GrammarIndentTask implements IndentTask {
    private static final Logger LOGGER = Logger.getLogger(GrammarIndentTask.class.getName());

    private final Context context;

    private ParserTaskManager taskManager;
    private DocumentSnapshot snapshot;
    private FileModel fileModel;
    private boolean fileModelDataFailed;

    private GrammarCodeStyle codeStyle;
    private CodeCompletionGrammarParser parser;

    public GrammarIndentTask(Context context) {
        this.context = context;
    }

    @Override
    public void reindent() throws BadLocationException {
        if (!smartReindent()) {
            fallbackReindent();
        }
    }

    public boolean smartReindent() throws BadLocationException {
        if (!(context.document() instanceof StyledDocument)) {
            return false;
        }

        StyledDocument document = (StyledDocument)context.document();

        taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
        if (taskManager == null) {
            return false;
        }

        VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(context.document());
        snapshot = versionedDocument.getCurrentSnapshot();
//        List<Anchor> anchors = getDynamicAnchorPoints();
//        if (anchors == null) {
//            return false;
//        }

        SnapshotPosition contextEndPosition = new SnapshotPosition(snapshot, context.endOffset());
        SnapshotPosition endPosition = contextEndPosition.getContainingLine().getEndIncludingLineBreak();
        SnapshotPosition endPositionOnLine = contextEndPosition.getContainingLine().getEnd();

//        Anchor enclosing = null;
//        Anchor previous = null;
//        Anchor next = null;
//
//        /*
//         * parse the current rule
//         */
//        for (Anchor anchor : anchors) {
//            // TODO: support more anchors
//            if (anchor.getRule() != GoParser.RULE_topLevelDecl) {
//                continue;
//            }
//
//            if (anchor.getSpan().getStartPosition(snapshot).getOffset() <= endPosition.getOffset()) {
//                previous = anchor;
//                if (anchor.getSpan().getEndPosition(snapshot).getOffset() > endPosition.getOffset()) {
//                    enclosing = anchor;
//                }
//            } else {
//                next = anchor;
//                break;
//            }
//        }

        ReferenceAnchors anchors = findNearestAnchors(taskManager, snapshot, endPosition.getOffset());
        int grammarType = anchors.getGrammarType();
        final Anchor previous = anchors.getPrevious();

        if (previous == null) {
            return false;
        }

        Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = taskManager.getData(snapshot, GrammarParserDataDefinitions.LEXER_TOKENS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
        Tagger<TokenTag<Token>> tagger = null;
        try {
            tagger = futureTokensData != null ? futureTokensData.get().getData() : null;
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ExecutionException ex) {
            Exceptions.printStackTrace(ex);
        }

        int regionEnd = Math.min(snapshot.length(), endPosition.getOffset() + 1);
        OffsetRegion region;
        if (anchors.getEnclosing() != null) {
            region = OffsetRegion.fromBounds(anchors.getEnclosing().getSpan().getStartPosition(snapshot).getOffset(), regionEnd);
        } else {
            // at least for now, include the previous span due to the way error handling places bounds on an anchor
            region = OffsetRegion.fromBounds(previous.getSpan().getStartPosition(snapshot).getOffset(), regionEnd);
        }

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "Reindent from anchor region: {0}.", region);
        }

        TaggerTokenSource<Token> taggerTokenSource = new TaggerTokenSource<Token>(tagger, new SnapshotPositionRegion(snapshot, region));
        TokenSource<Token> tokenSource = new CodeCompletionTokenSource(endPosition.getOffset(), taggerTokenSource);
        CommonTokenStream tokens = new CommonTokenStream(tokenSource);

        Map<RuleContext<Token>, CaretReachedException> parseTrees;
        parser = ParserCache.DEFAULT.getParser(tokens);
        try {
            parser.setBuildParseTree(true);
            parser.setErrorHandler(new CodeCompletionErrorStrategy<Token>());

            switch (previous.getRule()) {
            case GrammarParser.RULE_ruleSpec:
                parseTrees = GrammarForestParser.INSTANCE.getParseTrees(parser);
                break;

            default:
                parseTrees = null;
                break;
            }

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

                int indentationLevel = getIndent(firstNodeOnLine);

                List<Map.Entry<RuleContext<Token>, CaretReachedException>> indentList =
                    indentLevels.get(indentationLevel);
                if (indentList == null) {
                    indentList = new ArrayList<Map.Entry<RuleContext<Token>, CaretReachedException>>();
                    indentLevels.put(indentationLevel, indentList);
                }

                indentList.add(parseTree);
            }

            int indentLevel = !indentLevels.isEmpty() ? indentLevels.firstKey() : 0;
            if (indentLevels.size() > 1) {
                // TODO: resolve multiple possibilities
            }

            int startLine = NbDocument.findLineNumber(document, context.startOffset());
            int endLine;
            if (context.endOffset() <= context.startOffset()) {
                endLine = startLine;
            } else {
                endLine = NbDocument.findLineNumber(document, context.endOffset() - 1);
            }

            for (int line = startLine; line <= endLine; line++) {
                int currentOffset = NbDocument.findLineOffset(document, startLine);
                context.modifyIndent(currentOffset, indentLevel);
            }

            return true;
        } finally {
            ParserCache.DEFAULT.putParser(parser);
            parser = null;
        }
    }

    private static ReferenceAnchors findNearestAnchors(ParserTaskManager taskManager, DocumentSnapshot snapshot, int endOffset) {
        List<Anchor> anchors;
        Future<ParserData<List<Anchor>>> result =
            taskManager.getData(snapshot, GrammarParserDataDefinitions.DYNAMIC_ANCHOR_POINTS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
        try {
            anchors = result.get().getData();
        } catch (InterruptedException ex) {
            anchors = null;
        } catch (ExecutionException ex) {
            Exceptions.printStackTrace(ex);
            anchors = null;
        }

        int grammarType = -1;
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
                if (anchor instanceof GrammarParserAnchorListener.GrammarTypeAnchor) {
                    grammarType = ((GrammarParserAnchorListener.GrammarTypeAnchor)anchor).getGrammarType();
                    continue;
                }

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

        return new ReferenceAnchors(grammarType, previous, enclosing);
    }

    private FileModel getFileModel() {
        if (fileModel == null && !fileModelDataFailed) {
            Future<ParserData<FileModel>> futureFileModelData = taskManager.getData(snapshot, GrammarParserDataDefinitions.FILE_MODEL, EnumSet.of(ParserDataOptions.ALLOW_STALE, ParserDataOptions.SYNCHRONOUS));
            try {
                fileModel = futureFileModelData != null ? futureFileModelData.get().getData() : null;
                fileModelDataFailed = fileModel != null;
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
                fileModelDataFailed = true;
            } catch (ExecutionException ex) {
                Exceptions.printStackTrace(ex);
                fileModelDataFailed = true;
            }
        }

        return fileModel;
    }

    private List<Anchor> getDynamicAnchorPoints() {
        List<Anchor> anchors;
        Future<ParserData<List<Anchor>>> result =
            taskManager.getData(snapshot, GrammarParserDataDefinitions.DYNAMIC_ANCHOR_POINTS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
        try {
            anchors = result != null ? result.get().getData() : null;
        } catch (InterruptedException ex) {
            anchors = null;
        } catch (ExecutionException ex) {
            Exceptions.printStackTrace(ex);
            anchors = null;
        }

        return anchors;
    }

    private void fallbackReindent() throws BadLocationException {
        if (!(context.document() instanceof StyledDocument)) {
            return;
        }

        StyledDocument document = (StyledDocument)context.document();
        int startLine = NbDocument.findLineNumber(document, context.startOffset());

        int endLine;
        if (context.endOffset() <= context.startOffset()) {
            endLine = startLine;
        } else {
            endLine = NbDocument.findLineNumber(document, context.endOffset() - 1);
        }

        int previousIndent;
        if (startLine == 0) {
            previousIndent = 0;
        } else {
            int previousLineOffset = NbDocument.findLineOffset(document, startLine - 1);
            previousIndent = context.lineIndent(previousLineOffset);
        }

        for (int line = startLine; line <= endLine; line++) {
            int currentOffset = NbDocument.findLineOffset(document, startLine);
            int currentIndent = context.lineIndent(currentOffset);
            if (currentIndent == 0 && previousIndent > 0) {
                context.modifyIndent(currentOffset, previousIndent);
            }

            previousIndent = currentIndent;
        }
    }

    @Override
    public ExtraLock indentLock() {
        return null;
    }

    private TerminalNode<Token> findFirstNodeAfterOffset(ParseTree<Token> tree, int offset) {
        TerminalNode<Token> lastNode = ParseTrees.getStopNode(tree);
        if (lastNode == null) {
            return null;
        }

        if (lastNode.getSymbol() instanceof CaretToken) {
            throw new NotImplementedException();
        } else if (lastNode.getSymbol().getStartIndex() < offset) {
            return null;
        }

        if (tree instanceof TerminalNode) {
            return (TerminalNode<Token>)tree;
        }

        for (int i = 0; i < tree.getChildCount(); i++) {
            TerminalNode<Token> node = findFirstNodeAfterOffset(tree.getChild(i), offset);
            if (node != null) {
                return node;
            }
        }

        return null;
    }

    private GrammarCodeStyle getCodeStyle() {
        if (codeStyle == null) {
            codeStyle = GrammarCodeStyle.getDefault(context.document());
        }

        return codeStyle;
    }

    private int getIndent(final ParseTree<Token> firstNodeOnLine) throws BadLocationException {
        int nodeLineStart = -1;

        for (ParseTree<Token> current = firstNodeOnLine; current != null; current = current.getParent()) {
            if (current instanceof TerminalNode) {
                if (nodeLineStart == -1) {
                    nodeLineStart = context.lineStartOffset(((TerminalNode<Token>)current).getSymbol().getStartIndex());
                }

                continue;
            }

            if (!(current instanceof RuleNode)) {
                continue;
            }

            ParserRuleContext<Token> ruleContext = (ParserRuleContext<Token>)((RuleNode<Token>)current).getRuleContext();
            if (nodeLineStart == -1) {
                nodeLineStart = context.lineStartOffset(ruleContext.start.getStartIndex());
            }

            switch (ruleContext.getRuleIndex()) {
            case GrammarParser.RULE_parserRuleSpec:
            {
                TerminalNode<Token> colon = ((ParserRuleSpecContext)ruleContext).COLON();
                if (colon == null) {
                    continue;
                }

                // get the indent of the line where the block starts
                int blockLineOffset = context.lineStartOffset(ruleContext.start.getStartIndex());
                int blockIndent = context.lineIndent(blockLineOffset);
                if (nodeLineStart == blockLineOffset) {
                    return blockIndent;
                }

                // get the indent of the line where the colon is
                int colonLineOffset = context.lineStartOffset(colon.getSymbol().getStartIndex());
                int colonIndent = context.lineIndent(colonLineOffset);
                if (nodeLineStart == colonLineOffset) {
                    if (firstNodeOnLine != colon) {
                        continue;
                    }

                    return blockIndent + getCodeStyle().getIndentSize();
                }

                return colonIndent;
            }

            case GrammarParser.RULE_lexerRule:
            {
                TerminalNode<Token> colon = ((LexerRuleContext)ruleContext).COLON();
                if (colon == null) {
                    continue;
                }

                // get the indent of the line where the block starts
                int blockLineOffset = context.lineStartOffset(ruleContext.start.getStartIndex());
                int blockIndent = context.lineIndent(blockLineOffset);
                if (nodeLineStart == blockLineOffset) {
                    return blockIndent;
                }

                // get the indent of the line where the colon is
                int colonLineOffset = context.lineStartOffset(colon.getSymbol().getStartIndex());
                int colonIndent = context.lineIndent(colonLineOffset);
                if (nodeLineStart == colonLineOffset) {
                    if (firstNodeOnLine != colon) {
                        continue;
                    }

                    return blockIndent + getCodeStyle().getIndentSize();
                }

                return colonIndent;
            }

            case GrammarParser.RULE_modeSpec:
            {
                // get the indent of the line where the block starts
                int blockLineOffset = context.lineStartOffset(ruleContext.start.getStartIndex());
                int blockIndent = context.lineIndent(blockLineOffset);
                if (nodeLineStart == blockLineOffset) {
                    return blockIndent;
                }

                return blockIndent + getCodeStyle().getIndentSize();
            }

            case GrammarParser.RULE_tokensSpec:
            case GrammarParser.RULE_optionsSpec:
            {
                TerminalNode<Token> firstToken = ParseTrees.getStartNode(ruleContext);
                if (firstToken.getSymbol().getType() != GrammarParser.TOKENS && firstToken.getSymbol().getType() != GrammarParser.OPTIONS) {
                    continue;
                }

                // get the indent of the line where the block starts
                int blockLineOffset = context.lineStartOffset(ruleContext.start.getStartIndex());
                int blockIndent = context.lineIndent(blockLineOffset);
                if (nodeLineStart == blockLineOffset) {
                    return blockIndent;
                }

                // find the line with the brace
                int braceLineOffset = context.lineStartOffset(firstToken.getSymbol().getStopIndex() + 1);
                if (nodeLineStart <= braceLineOffset) {
                    return blockIndent;
                }

                if (firstNodeOnLine instanceof TerminalNode) {
                    // no extra indent if th efirst node on the line is the closing brace of the block
                    if (firstNodeOnLine == ruleContext.getToken(GrammarParser.RBRACE, 0)) {
                        return blockIndent;
                    }
                }

                return blockIndent + getCodeStyle().getIndentSize();
            }

            default:
                if (current.getParent() == null) {
                    int outerLineOffset = context.lineStartOffset(ruleContext.start.getStartIndex());
                    int outerIndent = context.lineIndent(outerLineOffset);
                    return outerIndent;
                }

                continue;
            }
        }

        return 0;
    }

    private static final class ReferenceAnchors {
        private final int grammarType;
        private final Anchor previous;
        private final Anchor enclosing;

        public ReferenceAnchors(int grammarType, Anchor previous, Anchor enclosing) {
            this.grammarType = grammarType;
            this.previous = previous;
            this.enclosing = enclosing;
        }

        public int getGrammarType() {
            return grammarType;
        }

        public Anchor getPrevious() {
            return previous;
        }

        public Anchor getEnclosing() {
            return enclosing;
        }
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=IndentTask.Factory.class)
    public static final class FactoryImpl implements Factory {

        @Override
        public IndentTask createTask(Context context) {
            return new GrammarIndentTask(context);
        }

    }
}
