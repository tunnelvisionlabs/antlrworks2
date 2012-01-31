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
package org.antlr.netbeans.editor.fold;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.fold.Fold;
import org.netbeans.api.editor.fold.FoldType;
import org.netbeans.spi.editor.fold.FoldHierarchyTransaction;
import org.netbeans.spi.editor.fold.FoldOperation;
import org.openide.util.Exceptions;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractFoldScanner<SemanticData> {

    @SuppressWarnings("fallthrough")
    public void run(ParserData<SemanticData> parseResult) {
        final VersionedDocument versionedDocument = parseResult.getSnapshot().getVersionedDocument();
        if (versionedDocument.getDocument() == null) {
            // no need to calculate folds for unopened documents
            return;
        }

        final AbstractFoldManager foldManager = AbstractFoldManager.getFoldManager(versionedDocument);
        if (foldManager == null) {
            return;
        }

        // calculate the folds
        final List<FoldInfo> folds = calculateFolds(parseResult);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DocumentSnapshot currentSnapshot = versionedDocument.getCurrentSnapshot();
                for (int i = 0; i < folds.size(); i++) {
                    folds.set(i, folds.get(i).translateTo(currentSnapshot));
                }

                FoldOperation operation = foldManager.getOperation();
                operation.getHierarchy().lock();
                try{
                    FoldHierarchyTransaction transaction = operation.openTransaction();
                    synchronized (foldManager.currentFolds) {
                        Collections.sort(foldManager.currentFolds, FoldComparator.DEFAULT);
                        Collections.sort(folds, FoldInfoComparator.DEFAULT);

                        List<Fold> foldsToKeep = new ArrayList<Fold>();
                        List<Fold> foldsToRemove = new ArrayList<Fold>();
                        List<FoldInfo> foldsToAdd = new ArrayList<FoldInfo>();

                        int i = 0;
                        int j = 0;
                        while (i < foldManager.currentFolds.size() && j < folds.size()) {
                            Fold existingFold = foldManager.currentFolds.get(i);
                            FoldInfo existing;
                            Object extraInfo = operation.getExtraInfo(existingFold);
                            if (extraInfo instanceof FoldInfo) {
                                existing = (FoldInfo)extraInfo;
                                existing = existing.translateTo(currentSnapshot);
                            } else {
                                SnapshotPositionRegion existingRegion = new SnapshotPositionRegion(currentSnapshot, OffsetRegion.fromBounds(existingFold.getStartOffset(), existingFold.getEndOffset()));
                                existing = new FoldInfo(existingRegion, existingFold.getDescription());
                            }

                            FoldInfo next = folds.get(j);
                            int compared = FoldInfoComparator.DEFAULT.compare(existing, next);
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
                            int startOffset = foldInfo.region.getStart().getOffset();
                            int endOffset = foldInfo.region.getEnd().getOffset();
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

    protected abstract List<FoldInfo> calculateFolds(ParserData<SemanticData> parseResult);

    protected Comparator<Fold> getFoldComparator() {
        return FoldComparator.DEFAULT;
    }

    protected Comparator<FoldInfo> getFoldInfoComparator() {
        return FoldInfoComparator.DEFAULT;
    }

    protected static class FoldComparator implements Comparator<Fold> {

        public static final FoldComparator DEFAULT = new FoldComparator();

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

    protected static class FoldInfoComparator implements Comparator<FoldInfo> {

        public static final FoldInfoComparator DEFAULT = new FoldInfoComparator();

        @Override
        public int compare(FoldInfo o1, FoldInfo o2) {
            if (!o1.region.getStart().equals(o2.region.getStart())) {
                return o1.region.getStart().compareTo(o2.region.getStart());
            } else if (!o1.region.getEnd().equals(o2.region.getEnd())) {
                return o1.region.getEnd().compareTo(o2.region.getEnd());
            } else {
                return o1.blockHint.compareTo(o2.blockHint);
            }
        }

    }

    public static class FoldInfo {
        private final SnapshotPositionRegion region;
        private final String blockHint;
        private final String preview;

        public FoldInfo(@NonNull SnapshotPositionRegion region, @NonNull String blockHint) {
            this.region = region;
            this.blockHint = blockHint;
            this.preview = region.getText();
        }

        public SnapshotPositionRegion getRegion() {
            return region;
        }

        public String getBlockHint() {
            return blockHint;
        }

        public String getPreview() {
            return preview;
        }

        protected FoldInfo translateTo(@NonNull DocumentSnapshot snapshot) {
            if (snapshot.equals(region.getSnapshot())) {
                return this;
            }

            TrackingPositionRegion trackingRegion = region.getSnapshot().createTrackingRegion(region.getRegion(), TrackingPositionRegion.Bias.Exclusive);
            return new FoldInfo(trackingRegion.getRegion(snapshot), blockHint);
        }
    }

}
