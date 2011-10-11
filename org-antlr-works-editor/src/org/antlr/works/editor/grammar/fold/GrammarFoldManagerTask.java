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
package org.antlr.works.editor.grammar.fold;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.grammar.v3.ANTLRParser;
import org.antlr.grammar.v3.ANTLRParser.grammar__return;
import org.antlr.netbeans.editor.navigation.CurrentDocumentStateScheduler;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.works.editor.grammar.parser.GrammarParser.GrammarParserResult;
import org.netbeans.api.editor.fold.Fold;
import org.netbeans.api.editor.fold.FoldType;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.spi.editor.fold.FoldHierarchyTransaction;
import org.netbeans.spi.editor.fold.FoldOperation;
import org.openide.filesystems.FileObject;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;

/**
 *
 * @author sam
 */
public class GrammarFoldManagerTask extends ParserResultTask<GrammarParserResult> {

    @Override
    @SuppressWarnings("fallthrough")
    public void run(GrammarParserResult result, SchedulerEvent event) {
        FileObject fileObject = result.getSnapshot().getSource().getFileObject();
        final GrammarFoldManager foldManager = GrammarFoldManager.getFoldManager(fileObject);
        if (foldManager == null) {
            return;
        }

        if (!result.getParser().getSyntaxErrors().isEmpty() && !foldManager.currentFolds.isEmpty()) {
            return;
        }

        final StyledDocument document = (StyledDocument)result.getSnapshot().getSource().getDocument(false);
        if (document == null) {
            return;
        }

        // calculate the folds
        final List<FoldInfo> folds = new ArrayList<FoldInfo>();
        grammar__return parseResult = result.getResult().getResult();
        CommonTree tree = (CommonTree)parseResult.getTree();
        if (tree != null) {
            for (Object childObject : tree.getChildren()) {
                CommonTree child = (CommonTree)childObject;
                if (child == null || child.getText() == null || child.getText().isEmpty()) {
                    continue;
                }

                if (child.getText().equals("rule") && child.getChildCount() > 0 || child.getText().startsWith("tokens") || child.getText().startsWith("options")) {
                    String blockHint = "...";
                    if (child.getText().equals("rule")) {
                        String ruleName = child.getChild(0).getText();
                        // don't try to outline the artificial tokens rule
                        if (ruleName.equals("Tokens")) {
                            continue;
                        }

                        blockHint = child.getChild(0).getText() + "...";
                    } else if (child.getText().startsWith("tokens")) {
                        // this is the special tokens{} block of a combined grammar
                        blockHint = "tokens {...}";
                    } else if (child.getText().startsWith("options")) {
                        // this is the special options{} block of a grammar
                        blockHint = "options {...}";
                    }

                    CommonToken startToken = result.getResult().getTokens()[child.getTokenStartIndex()];
                    CommonToken stopToken = result.getResult().getTokens()[child.getTokenStopIndex()];

                    if (startToken.getType() == ANTLRParser.DOC_COMMENT) {
                        for (int index = child.getTokenStartIndex(); index <= child.getTokenStopIndex(); index++) {
                            startToken = result.getResult().getTokens()[index];
                            if (startToken.getType() != ANTLRParser.DOC_COMMENT && startToken.getChannel() != Token.HIDDEN_CHANNEL) {
                                break;
                            }
                        }
                    }

                    int startLine = NbDocument.findLineNumber(document, startToken.getStartIndex());
                    int stopLine = NbDocument.findLineNumber(document, stopToken.getStopIndex() + 1);
                    if (startLine >= stopLine) {
                        continue;
                    }

                    FoldInfo info = new FoldInfo(document, startToken.getStartIndex(), stopToken.getStopIndex() + 1, blockHint);
                    folds.add(info);
                    /*Span span = new Span(startToken.StartIndex, stopToken.StopIndex - startToken.StartIndex + 1);
                    if (snapshot.GetLineNumberFromPosition(span.Start) == snapshot.GetLineNumberFromPosition(span.End))
                        continue;

                    SnapshotSpan snapshotSpan = new SnapshotSpan(antlrParseResultArgs.Snapshot, span);
                    IOutliningRegionTag tag = new OutliningRegionTag(blockHint, snapshotSpan.GetText());
                    TagSpan<IOutliningRegionTag> tagSpan = new TagSpan<IOutliningRegionTag>(snapshotSpan, tag);
                    outliningRegions.Add(tagSpan);*/
                }
            }

            for (CommonToken token : result.getResult().getTokens()) {
                switch (token.getType()) {
                case ANTLRParser.DOC_COMMENT:
                case ANTLRParser.ML_COMMENT:
                case ANTLRParser.ACTION:
                    int startLine = NbDocument.findLineNumber(document, token.getStartIndex());
                    int stopLine = NbDocument.findLineNumber(document, token.getStopIndex() + 1);
                    if (startLine >= stopLine) {
                        continue;
                    }

                    String blockHint = null;
                    if (token.getType() == ANTLRParser.DOC_COMMENT) {
                        blockHint = "/** ... */";
                    } else if (token.getType() == ANTLRParser.ML_COMMENT) {
                        blockHint = "/* ... */";
                    } else if (token.getType() == ANTLRParser.ACTION) {
                        blockHint = "{}";
                    } else {
                        throw new IllegalStateException();
                    }

                    FoldInfo info = new FoldInfo(document, token.getStartIndex(), token.getStopIndex() + 1, blockHint);
                    folds.add(info);
                    /*Span commentSpan = Span.FromBounds(token.StartIndex, token.StopIndex + 1);
                    if (snapshot.GetLineNumberFromPosition(commentSpan.Start) != snapshot.GetLineNumberFromPosition(commentSpan.End))
                    {
                        SnapshotSpan commentSnapshotSpan = new SnapshotSpan(antlrParseResultArgs.Snapshot, commentSpan);
                        IOutliningRegionTag commentTag = new OutliningRegionTag(string.Format("/*{0} ... * /", token.Type == ANTLRParser.DOC_COMMENT ? "*" : string.Empty), commentSnapshotSpan.GetText());
                        TagSpan<IOutliningRegionTag> commentTagSpan = new TagSpan<IOutliningRegionTag>(commentSnapshotSpan, commentTag);
                        outliningRegions.Add(commentTagSpan);
                    }
                    break;*/

                default:
                    continue;
                }
            }

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FoldOperation operation = foldManager.getOperation();
                    operation.getHierarchy().lock();
                    try{
                        FoldHierarchyTransaction transaction = operation.openTransaction();
                        synchronized (foldManager.currentFolds) {
                            Collections.sort(foldManager.currentFolds, FoldComparator.INSTANCE);
                            Collections.sort(folds, FoldInfoComparator.INSTANCE);

                            List<Fold> foldsToKeep = new ArrayList<Fold>();
                            List<Fold> foldsToRemove = new ArrayList<Fold>();
                            List<FoldInfo> foldsToAdd = new ArrayList<FoldInfo>();

                            int i = 0;
                            int j = 0;
                            while (i < foldManager.currentFolds.size() && j < folds.size()) {
                                Fold existingFold = foldManager.currentFolds.get(i);
                                FoldInfo existing = new FoldInfo(document, existingFold.getStartOffset(), existingFold.getEndOffset(), existingFold.getDescription());
                                FoldInfo next = folds.get(j);
                                int compared = FoldInfoComparator.INSTANCE.compare(existing, next);
                                if (compared == 0) {
                                    foldsToKeep.add(foldManager.currentFolds.get(i));
                                    i++;
                                    j++;
                                } else if (compared < 0) {
                                    // existing doesn't have a match
                                    foldsToRemove.add(foldManager.currentFolds.get(i));
                                    i++;
                                } else {
                                    // next doesn't have a match
                                    foldsToAdd.add(next);
                                    j++;
                                }
                            }
                            
                            foldsToRemove.addAll(foldManager.currentFolds.subList(i, foldManager.currentFolds.size()));
                            foldsToAdd.addAll(folds.subList(j, folds.size()));

                            for (Fold fold : foldsToRemove) {
                                operation.removeFromHierarchy(fold, transaction);
                            }

                            foldManager.currentFolds.clear();
                            foldManager.currentFolds.addAll(foldsToKeep);

                            for (FoldInfo foldInfo : foldsToAdd) {
                                FoldType foldType = new FoldType("code-block");
                                String description = foldInfo.blockHint;
                                boolean collapsed = false;
                                int startOffset = foldInfo.startIndex;
                                int endOffset = foldInfo.stopIndex;
                                int startGuardedLength = 0;
                                int endGuardedLength = 0;
                                try {
                                    Fold fold = operation.addToHierarchy(foldType, description, collapsed, startOffset, endOffset, startGuardedLength, endGuardedLength, foldInfo, transaction);
                                    foldManager.currentFolds.add(fold);
                                } catch (BadLocationException ex) {
                                    Exceptions.printStackTrace(ex);
                                }
                            }
                        }
                        transaction.commit();
                    } finally {
                        operation.getHierarchy().unlock();
                    }
                }
            });
        }
    }

    private static class FoldComparator implements Comparator<Fold> {

        public static final FoldComparator INSTANCE = new FoldComparator();

        @Override
        public int compare(Fold o1, Fold o2) {
            if (o1.getStartOffset() != o2.getStartOffset()) {
                return o1.getStartOffset() - o2.getStartOffset();
            } else if (o1.getEndOffset() != o2.getEndOffset()) {
                return o1.getEndOffset() - o2.getEndOffset();
            } else {
                return o1.getDescription().compareTo(o2.getDescription());
            }
        }

    }

    private static class FoldInfoComparator implements Comparator<FoldInfo> {

        public static final FoldInfoComparator INSTANCE = new FoldInfoComparator();

        @Override
        public int compare(FoldInfo o1, FoldInfo o2) {
            if (o1.startIndex != o2.startIndex) {
                return o1.startIndex - o2.startIndex;
            } else if (o1.stopIndex != o2.stopIndex) {
                return o1.stopIndex - o2.stopIndex;
            } else {
                return o1.blockHint.compareTo(o2.blockHint);
            }
        }

    }

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public Class<? extends Scheduler> getSchedulerClass() {
        return CurrentDocumentStateScheduler.class;
    }

    @Override
    public void cancel() {
    }

    public static class FoldInfo {
        private final StyledDocument document;
        private final int startIndex;
        private final int stopIndex;
        private final String blockHint;
        private final String preview;

        private FoldInfo(StyledDocument document, int startIndex, int stopIndex, String blockHint) {
            this.document = document;
            this.startIndex = startIndex;
            this.stopIndex = stopIndex;
            this.blockHint = blockHint;

            String preview;
            try {
                preview = document.getText(startIndex, stopIndex - startIndex);
            } catch (BadLocationException ex) {
                preview = "";
                Exceptions.printStackTrace(ex);
            }

            this.preview = preview;
        }

    }

}
