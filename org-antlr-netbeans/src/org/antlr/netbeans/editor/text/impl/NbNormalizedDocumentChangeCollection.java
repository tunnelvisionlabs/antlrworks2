/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.text.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import org.antlr.netbeans.editor.text.DocumentChange;
import org.antlr.netbeans.editor.text.NormalizedDocumentChangeCollection;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
final class NbNormalizedDocumentChangeCollection extends AbstractList<DocumentChange> implements NormalizedDocumentChangeCollection {

    private final List<NbDocumentChange> internal = new ArrayList<>();
    private boolean includesLineChanges;
    private boolean readOnly;

    void freeze() {
        readOnly = true;
    }

    @Override
    public boolean getIncludesLineChanges() {
        return includesLineChanges;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public void add(int index, DocumentChange element) {
        Parameters.notNull("element", element);
        if (isReadOnly()) {
            throw new UnsupportedOperationException();
        }

        if (!(element instanceof NbDocumentChange)) {
            throw new UnsupportedOperationException();
        }

        add(index, (NbDocumentChange)element);
    }

    public void add(int index, @NonNull NbDocumentChange element) {
        Parameters.notNull("element", element);

        if (isReadOnly()) {
            throw new UnsupportedOperationException();
        }

        if (element.getOldOffset() != element.getNewOffset()) {
            throw new UnsupportedOperationException("Move changes are not yet supported.");
        }

        if (element.getLineCountDelta() != 0) {
            includesLineChanges = true;
        }

        int mergeIndex = -1;
        int lastMergeIndex = -1;
        for (index = 0; index < internal.size() && element.getOldRegion().getEnd() >= internal.get(index).getNewOffset(); index++) {
            if (element.getOldRegion().intersectsWith(internal.get(index).getNewRegion())) {
                lastMergeIndex = index;
                if (mergeIndex < 0) {
                    mergeIndex = index;
                }
            } else if (mergeIndex >= 0) {
                break;
            }
        }

        if (mergeIndex >= 0) {
            NbDocumentChange merged = mergeChanges(element, internal.subList(mergeIndex, lastMergeIndex + 1));
            internal.set(mergeIndex, merged);
            internal.subList(mergeIndex + 1, lastMergeIndex + 1).clear();
            index = mergeIndex;
        } else {
            internal.add(index, adjustOldState(element, internal.subList(0, index)));
        }

        for (int i = index + 1; i < internal.size(); i++) {
            // always adjust the remaining ones based on the original update
            internal.set(i, adjustNewState(internal.get(i), element));
        }

        assert repOk();
    }

    @Override
    public DocumentChange get(int index) {
        return internal.get(index);
    }

    @Override
    public int size() {
        return internal.size();
    }

    private @NonNull NbDocumentChange mergeChanges(@NonNull NbDocumentChange update, @NonNull List<NbDocumentChange> existing) {
        Parameters.notNull("update", update);
        Parameters.notNull("existing", existing);

        if (existing.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (update.getOldOffset() != update.getNewOffset()) {
            throw new UnsupportedOperationException("Move operations are not supported.");
        }

        NbDocumentChange firstChange = existing.get(0);
        NbDocumentChange lastChange = existing.get(existing.size() - 1);

        int firstChangeOffset = firstChange.getNewOffset() - firstChange.getOldOffset();
        int lastChangeOffset = lastChange.getNewOffset() - lastChange.getOldOffset();
        
        int updateOriginalStart =
            update.getOldOffset() < firstChange.getNewOffset()
            ? update.getOldOffset() - firstChangeOffset
            : firstChange.getOldOffset();

        int updateOriginalEnd =
            update.getOldOffset() + update.getOldLength() > lastChange.getNewOffset() + lastChange.getNewLength()
            ? update.getOldOffset() + update.getOldLength() - lastChangeOffset - lastChange.getDelta()
            : lastChange.getOldOffset() + lastChange.getOldLength();

        int oldPosition = Math.min(updateOriginalStart, firstChange.getOldOffset());
        int oldEndPosition = Math.max(updateOriginalEnd, lastChange.getOldOffset() + lastChange.getOldLength());
        int newPosition = oldPosition + firstChangeOffset;
        int newEndPosition = oldEndPosition + lastChangeOffset + lastChange.getDelta() + update.getDelta();

        StringBuilder oldText = new StringBuilder(oldEndPosition - oldPosition);
        oldText.setLength(oldEndPosition - oldPosition);
        StringBuilder newText = new StringBuilder(newEndPosition - newPosition);
        newText.setLength(newEndPosition - newPosition);

        // fill the oldText buffer
        int fillPoint = 0;
        NbDocumentChange previousChange = null;
        for (int i = 0; i <= existing.size(); i++) {
            NbDocumentChange change = (i < existing.size()) ? existing.get(i) : null;
            int rawStart = change != null ? change.getOldOffset() : oldEndPosition;
            int rawEnd = change != null ? change.getOldOffset() + change.getOldLength() : oldEndPosition;
            int start = rawStart - oldPosition;
            int end = rawEnd - oldPosition;
            if (start > fillPoint) {
                // pull the intermediate characters from the update change
                if (previousChange == null) {
                    oldText.replace(0, start, update.getOldText().substring(0, start));
                } else {
                    int a = previousChange.getNewOffset() + previousChange.getNewLength() - update.getOldOffset();
                    int b = a + (start - fillPoint);
                    oldText.replace(fillPoint, start, update.getOldText().substring(a, b));
                }
            }

            if (start < end) {
                assert change != null : "Should have a change.";
                oldText.replace(start, end, change.getOldText());
            }

            fillPoint = end;
            previousChange = change;
        }

        // fill the newText buffer
        newText.replace(update.getNewOffset() - newPosition, update.getNewOffset() + update.getNewLength() - newPosition, update.getNewText());

        if (newPosition < update.getNewOffset()) {
            int start = 0;
            int end = update.getNewOffset() - newPosition;
            newText.replace(start, end, firstChange.getNewText().substring(0, end));
        }

        if (newEndPosition > update.getNewOffset() + update.getNewLength()) {
            int start = update.getNewOffset() + update.getNewLength() - newPosition;
            int end = newEndPosition - newPosition;
            newText.replace(start, end, lastChange.getNewText().substring(lastChange.getNewLength() - (end - start), lastChange.getNewLength()));
        }

        return new NbDocumentChange(oldPosition, oldText.toString(), newPosition, newText.toString());
    }

    private boolean repOk() {
        for (int i = 0; i < internal.size() - 1; i++) {
            NbDocumentChange current = internal.get(i);
            NbDocumentChange next = internal.get(i + 1);
            if (current.getOldOffset() + current.getOldLength() > next.getOldOffset()) {
                return false;
            }

            if (current.getNewOffset() + current.getNewLength() > next.getNewOffset()) {
                return false;
            }

            if (current.getOldRegion().intersectsWith(next.getOldRegion())) {
                return false;
            }

            if (current.getNewRegion().intersectsWith(next.getNewRegion())) {
                return false;
            }
        }

        return true;
    }

//    /**
//     * Attempts to merge two intersecting changes. If the changes are disjoint,
//     * this method returns {@code null}.
//     * <p>
//     * If {@code linked} is {@code true}, the first change is applied, and then the
//     * second change is applied to the result.
//     *
//     * <p>
//     * If {@code linked} is {@code false}, the first change is assumed to invalidate
//     * any overlapping region of the second. In other words, the target state of
//     * the first change is newer than the target state of the second change.
//     *
//     * @param linked <c>true</c> if the second change is applied to the new state of the first;
//     * <c>false</c> if the second change is applied to the old state of the first (e.g. as the
//     * result of a previous merge).
//     *
//     * @return An {@link NbDocumentChange} if the changes are successfully merged, else
//     * {@code false} if the changes are disjoint.
//     */
//    private NbDocumentChange mergeChanges(NbDocumentChange first, NbDocumentChange second, boolean linked) {
//        Parameters.notNull("first", first);
//        Parameters.notNull("second", second);
//
//        OffsetRegion firstSpan = linked ? first.getNewSpan() : first.getOldSpan();
//        OffsetRegion secondSpan = second.getOldSpan();
//        if (!firstSpan.intersectsWith(secondSpan)) {
//            return null;
//        }
//
//        int oldPosition;
//        String oldText;
//        int newPosition;
//        String newText;
//
//        if (linked) {
//            if (first.getOldPosition() < second.getOldPosition()) {
//                oldPosition = first.getOldPosition();
//                newPosition = first.getNewPosition();
//            } else {
//                oldPosition = second.getOldPosition();
//                newPosition = second.getNewPosition();
//            }
//        } else {
//            if (first.getOldPosition() < second.getOldPosition()) {
//                oldPosition = first.getOldPosition();
//                newPosition = first.getNewPosition();
//                oldText = first.getOldText();
//                if (!first.getOldSpan().contains(second.getOldSpan())) {
//                    oldText += second.getOldText().substring(first.getOldSpan().getEnd() - second.getOldSpan().getStart(), second.getOldSpan().getEnd());
//                }
//            } else {
//                oldPosition = second.getOldPosition();
//                newPosition = second.getNewPosition();
//                oldText = second.getOldText();
//                if (!second.getOldSpan().contains(first.getOldSpan())) {
//                    oldText += first.getOldText().substring(second.getOldSpan().getEnd() - first.getOldSpan().getStart(), first.getOldSpan().getEnd());
//                }
//            }
//        }
//
//        return new NbDocumentChange(oldPosition, oldText, newPosition, newText);
//    }
//
//    private NbDocumentChange mergeChanges(NbDocumentChange first, NbDocumentChange second) {
//        Parameters.notNull("first", first);
//        Parameters.notNull("second", second);
//
//        OffsetRegion firstSpan = linked ? first.getNewSpan() : first.getOldSpan();
//        OffsetRegion secondSpan = second.getOldSpan();
//        if (!firstSpan.intersectsWith(secondSpan)) {
//            return null;
//        }
//
//        int oldPosition;
//        String oldText;
//        int newPosition;
//        String newText;
//
//        if (linked) {
//            if (first.getOldPosition() < second.getOldPosition()) {
//                oldPosition = first.getOldPosition();
//                newPosition = first.getNewPosition();
//            } else {
//                oldPosition = second.getOldPosition();
//                newPosition = second.getNewPosition();
//            }
//        } else {
//            if (first.getOldPosition() < second.getOldPosition()) {
//                oldPosition = first.getOldPosition();
//                newPosition = first.getNewPosition();
//                oldText = first.getOldText();
//                if (!first.getOldSpan().contains(second.getOldSpan())) {
//                    oldText += second.getOldText().substring(first.getOldSpan().getEnd() - second.getOldSpan().getStart(), second.getOldSpan().getEnd());
//                }
//            } else {
//                oldPosition = second.getOldPosition();
//                newPosition = second.getNewPosition();
//                oldText = second.getOldText();
//                if (!second.getOldSpan().contains(first.getOldSpan())) {
//                    oldText += first.getOldText().substring(second.getOldSpan().getEnd() - first.getOldSpan().getStart(), first.getOldSpan().getEnd());
//                }
//            }
//        }
//
//        return new NbDocumentChange(oldPosition, oldText, newPosition, newText);
//    }

    private @NonNull NbDocumentChange adjustOldState(@NonNull NbDocumentChange element, @NonNull Iterable<NbDocumentChange> changes) {
        Parameters.notNull("element", element);
        Parameters.notNull("changes", changes);

        int delta = 0;
        for (NbDocumentChange change : changes) {
            assert !element.getOldRegion().intersectsWith(change.getNewRegion()) : "The changes should not intersect.";
            if (change.getNewOffset() <= element.getOldOffset()) {
                delta += change.getDelta();
            }
        }

        if (delta == 0) {
            return element;
        }

        return new NbDocumentChange(element.getOldOffset() - delta, element.getOldText(), element.getNewOffset(), element.getNewText(), element.getLineCountDelta());
    }

    private @NonNull NbDocumentChange adjustNewState(@NonNull NbDocumentChange element, @NonNull NbDocumentChange change) {
        Parameters.notNull("element", element);
        Parameters.notNull("change", change);
        assert !element.getNewRegion().intersectsWith(change.getOldRegion()) : "The changes should not intersect.";

        if (change.getDelta() == 0 || change.getOldOffset() > element.getNewOffset()) {
            return element;
        }
        
        return new NbDocumentChange(element.getOldOffset(), element.getOldText(), element.getNewOffset() + change.getDelta(), element.getNewText(), element.getLineCountDelta());
    }
}
