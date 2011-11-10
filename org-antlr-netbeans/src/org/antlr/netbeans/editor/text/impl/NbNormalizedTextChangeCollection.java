/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import org.antlr.netbeans.editor.text.NormalizedTextChangeCollection;
import org.antlr.netbeans.editor.text.Span;
import org.antlr.netbeans.editor.text.TextChange;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
final class NbNormalizedTextChangeCollection extends AbstractList<TextChange> implements NormalizedTextChangeCollection {

    private final List<NbTextChange> internal = new ArrayList<NbTextChange>();
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
    public void add(int index, TextChange element) {
        Parameters.notNull("element", element);
        if (isReadOnly()) {
            throw new UnsupportedOperationException();
        }

        if (!(element instanceof NbTextChange)) {
            throw new UnsupportedOperationException();
        }

        add(index, (NbTextChange)element);
    }

    public void add(int index, NbTextChange element) {
        Parameters.notNull("element", element);

        if (isReadOnly()) {
            throw new UnsupportedOperationException();
        }

        if (element.getOldPosition() != element.getNewPosition()) {
            throw new UnsupportedOperationException("Move changes are not yet supported.");
        }

        if (element.getLineCountDelta() != 0) {
            includesLineChanges = true;
        }

        int mergeIndex = -1;
        int lastMergeIndex = -1;
        for (index = 0; index < internal.size() && element.getOldSpan().getEnd() >= internal.get(index).getNewPosition(); index++) {
            if (element.getOldSpan().intersectsWith(internal.get(index).getNewSpan())) {
                lastMergeIndex = index;
                if (mergeIndex < 0) {
                    mergeIndex = index;
                }
            } else if (mergeIndex >= 0) {
                break;
            }
        }

        if (mergeIndex >= 0) {
            NbTextChange merged = mergeChanges(element, internal.subList(mergeIndex, lastMergeIndex + 1));
            internal.set(mergeIndex, merged);
            internal.subList(mergeIndex + 1, lastMergeIndex + 1).clear();
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
    public TextChange get(int index) {
        return internal.get(index);
    }

    @Override
    public int size() {
        return internal.size();
    }

    private NbTextChange mergeChanges(NbTextChange update, List<NbTextChange> existing) {
        Parameters.notNull("update", update);
        Parameters.notNull("existing", existing);

        if (existing.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (update.getOldPosition() != update.getNewPosition()) {
            throw new UnsupportedOperationException("Move operations are not supported.");
        }

        NbTextChange firstChange = existing.get(0);
        NbTextChange lastChange = existing.get(existing.size() - 1);

        int firstChangeOffset = firstChange.getNewPosition() - firstChange.getOldPosition();
        int lastChangeOffset = lastChange.getNewPosition() - lastChange.getOldPosition();
        
        int updateOriginalStart =
            update.getOldPosition() < firstChange.getNewPosition()
            ? update.getOldPosition() - firstChangeOffset
            : firstChange.getOldPosition();

        int updateOriginalEnd =
            update.getOldPosition() + update.getOldLength() > lastChange.getNewPosition() + lastChange.getNewLength()
            ? update.getOldPosition() + update.getOldLength() - lastChangeOffset - lastChange.getDelta()
            : lastChange.getOldPosition() + lastChange.getOldLength();

        int oldPosition = Math.min(updateOriginalStart, firstChange.getOldPosition());
        int oldEndPosition = Math.max(updateOriginalEnd, lastChange.getOldPosition() + lastChange.getOldLength());
        int newPosition = oldPosition + firstChangeOffset;
        int newEndPosition = oldEndPosition + lastChangeOffset + lastChange.getDelta() + update.getDelta();

        StringBuilder oldText = new StringBuilder(oldEndPosition - oldPosition);
        oldText.setLength(oldEndPosition - oldPosition);
        StringBuilder newText = new StringBuilder(newEndPosition - newPosition);
        newText.setLength(newEndPosition - newPosition);

        // fill the oldText buffer
        int fillPoint = 0;
        NbTextChange previousChange = null;
        for (int i = 0; i <= existing.size(); i++) {
            NbTextChange change = (i < existing.size()) ? existing.get(i) : null;
            int rawStart = (i < existing.size()) ? change.getOldPosition() : oldEndPosition;
            int rawEnd = (i < existing.size()) ? change.getOldPosition() + change.getOldLength() : oldEndPosition;
            int start = rawStart - oldPosition;
            int end = rawEnd - oldPosition;
            if (start > fillPoint) {
                // pull the intermediate characters from the update change
                if (previousChange == null) {
                    oldText.replace(0, start, update.getOldText().substring(0, start));
                } else {
                    int a = previousChange.getNewPosition() + previousChange.getNewLength() - update.getOldPosition();
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
        newText.replace(update.getNewPosition() - newPosition, update.getNewPosition() + update.getNewLength() - newPosition, update.getNewText());

        if (newPosition < update.getNewPosition()) {
            int start = 0;
            int end = update.getNewPosition() - newPosition;
            newText.replace(start, end, firstChange.getNewText().substring(0, end));
        }

        if (newEndPosition > update.getNewPosition() + update.getNewLength()) {
            int start = update.getNewPosition() + update.getNewLength() - newPosition;
            int end = newEndPosition - newPosition;
            newText.replace(start, end, lastChange.getNewText().substring(lastChange.getNewLength() - (end - start), lastChange.getNewLength()));
        }

        return new NbTextChange(oldPosition, oldText.toString(), newPosition, newText.toString());
    }

    private boolean repOk() {
        for (int i = 0; i < internal.size() - 1; i++) {
            NbTextChange current = internal.get(i);
            NbTextChange next = internal.get(i + 1);
            if (current.getOldPosition() + current.getOldLength() > next.getOldPosition()) {
                return false;
            }

            if (current.getNewPosition() + current.getNewLength() > next.getNewPosition()) {
                return false;
            }

            if (current.getOldSpan().intersectsWith(next.getOldSpan())) {
                return false;
            }

            if (current.getNewSpan().intersectsWith(next.getNewSpan())) {
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
//     * @return An {@link NbTextChange} if the changes are successfully merged, else
//     * {@code false} if the changes are disjoint.
//     */
//    private NbTextChange mergeChanges(NbTextChange first, NbTextChange second, boolean linked) {
//        Parameters.notNull("first", first);
//        Parameters.notNull("second", second);
//
//        Span firstSpan = linked ? first.getNewSpan() : first.getOldSpan();
//        Span secondSpan = second.getOldSpan();
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
//        return new NbTextChange(oldPosition, oldText, newPosition, newText);
//    }
//
//    private NbTextChange mergeChanges(NbTextChange first, NbTextChange second) {
//        Parameters.notNull("first", first);
//        Parameters.notNull("second", second);
//
//        Span firstSpan = linked ? first.getNewSpan() : first.getOldSpan();
//        Span secondSpan = second.getOldSpan();
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
//        return new NbTextChange(oldPosition, oldText, newPosition, newText);
//    }

    private NbTextChange adjustOldState(NbTextChange element, Iterable<NbTextChange> changes) {
        Parameters.notNull("element", element);
        Parameters.notNull("changes", changes);

        int delta = 0;
        for (NbTextChange change : changes) {
            assert !element.getOldSpan().intersectsWith(change.getNewSpan()) : "The changes should not intersect.";
            if (change.getNewPosition() <= element.getOldPosition()) {
                delta += change.getDelta();
            }
        }

        if (delta == 0) {
            return element;
        }

        return new NbTextChange(element.getOldPosition() - delta, element.getOldText(), element.getNewPosition(), element.getNewText(), element.getLineCountDelta());
    }

    private NbTextChange adjustNewState(NbTextChange element, NbTextChange change) {
        Parameters.notNull("element", element);
        Parameters.notNull("change", change);
        assert !element.getNewSpan().intersectsWith(change.getOldSpan()) : "The changes should not intersect.";

        if (change.getDelta() == 0 || change.getOldPosition() > element.getNewPosition()) {
            return element;
        }
        
        return new NbTextChange(element.getOldPosition(), element.getOldText(), element.getNewPosition() + change.getDelta(), element.getNewText(), element.getLineCountDelta());
    }
}
