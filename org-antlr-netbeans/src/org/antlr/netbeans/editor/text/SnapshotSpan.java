/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author sam
 */
public final class SnapshotSpan {

    @NonNull
    private final TextSnapshot snapshot;
    @NonNull
    private final Span span;

    public SnapshotSpan(@NonNull TextSnapshot snapshot, int start, int length) {
        this(snapshot, new Span(start, length));
    }

    public SnapshotSpan(@NonNull TextSnapshot snapshot, @NonNull Span span) {
        Parameters.notNull("snapshot", snapshot);
        Parameters.notNull("span", span);
        if (span.getEnd() > snapshot.length()) {
            throw new IllegalArgumentException("The specified span does not lie within the snapshot.");
        }

        this.snapshot = snapshot;
        this.span = span;
    }

    public SnapshotSpan(@NonNull SnapshotPoint start, int length) {
        this(start.getSnapshot(), start.getPosition(), length);
    }

    public SnapshotSpan(@NonNull SnapshotPoint start, @NonNull SnapshotPoint end) {
        this(start, start.difference(end));
    }

    public @NonNull SnapshotPoint getStart() {
        return new SnapshotPoint(snapshot, span.getStart());
    }

    public @NonNull SnapshotPoint getEnd() {
        return new SnapshotPoint(snapshot, span.getEnd());
    }

    public int getLength() {
        return span.getLength();
    }

    public @NonNull TextSnapshot getSnapshot() {
        return snapshot;
    }

    public @NonNull Span getSpan() {
        return span;
    }

    public boolean contains(int position) {
        return span.contains(position);
    }

    public boolean contains(@NonNull SnapshotPoint point) {
        Parameters.notNull("point", point);
        if (!point.getSnapshot().equals(snapshot)) {
            throw new IllegalArgumentException();
        }

        return contains(point.getPosition());
    }

    public boolean contains(@NonNull SnapshotSpan span) {
        Parameters.notNull("span", span);
        if (!span.getSnapshot().equals(snapshot)) {
            throw new IllegalArgumentException();
        }

        return contains(span.getSpan());
    }

    public boolean contains(@NonNull Span span) {
        Parameters.notNull("span", span);
        return span.getStart() >= this.span.getStart()
            && span.getEnd() <= this.span.getEnd();
    }

    public @NonNull String getText() {
        return snapshot.subSequence(span.getStart(), span.getEnd()).toString();
    }

    public @CheckForNull SnapshotSpan intersection(@NonNull SnapshotSpan other) {
        Parameters.notNull("other", other);
        if (!other.getSnapshot().equals(snapshot)) {
            throw new IllegalArgumentException();
        }

        return intersection(other.getSpan());
    }

    public @CheckForNull SnapshotSpan intersection(@NonNull Span other) {
        Parameters.notNull("other", other);
        int newStart = Math.max(this.span.getStart(), other.getStart());
        int newEnd = Math.min(this.span.getEnd(), other.getEnd());
        if (newEnd < newStart) {
            return null;
        }

        return new SnapshotSpan(snapshot, Span.fromBounds(newStart, newEnd));
    }

    public boolean intersectsWith(@NonNull SnapshotSpan other) {
        Parameters.notNull("other", other);
        if (!other.getSnapshot().equals(snapshot)) {
            throw new IllegalArgumentException();
        }

        return intersectsWith(other.getSpan());
    }

    public boolean intersectsWith(@NonNull Span other) {
        Parameters.notNull("other", other);
        int newStart = Math.max(this.span.getStart(), other.getStart());
        int newEnd = Math.min(this.span.getEnd(), other.getEnd());
        return newEnd >= newStart;
    }

    public @CheckForNull SnapshotSpan overlap(@NonNull SnapshotSpan other) {
        Parameters.notNull("other", other);
        if (!other.getSnapshot().equals(snapshot)) {
            throw new IllegalArgumentException();
        }

        return overlap(other.getSpan());
    }

    public @CheckForNull SnapshotSpan overlap(@NonNull Span other) {
        Parameters.notNull("other", other);
        int newStart = Math.max(this.span.getStart(), other.getStart());
        int newEnd = Math.min(this.span.getEnd(), other.getEnd());
        if (newEnd <= newStart) {
            return null;
        }

        return new SnapshotSpan(snapshot, Span.fromBounds(newStart, newEnd));
    }

    public boolean overlapsWith(@NonNull SnapshotSpan other) {
        Parameters.notNull("other", other);
        if (!other.getSnapshot().equals(snapshot)) {
            throw new IllegalArgumentException();
        }

        return overlapsWith(other.getSpan());
    }

    public boolean overlapsWith(@NonNull Span other) {
        Parameters.notNull("other", other);
        int newStart = Math.max(this.span.getStart(), other.getStart());
        int newEnd = Math.min(this.span.getEnd(), other.getEnd());
        return newEnd > newStart;
    }

    public @NonNull SnapshotSpan translateTo(@NonNull TextSnapshot targetSnapshot, @NonNull SpanTrackingMode trackingMode) {
        Parameters.notNull("targetSnapshot", targetSnapshot);
        Parameters.notNull("trackingMode", trackingMode);

        PointTrackingMode startTrackingMode;
        PointTrackingMode endTrackingMode;

        switch (trackingMode) {
        case EdgeExclusive:
            startTrackingMode = PointTrackingMode.Positive;
            endTrackingMode = PointTrackingMode.Negative;
            break;

        case EdgeInclusive:
            startTrackingMode = PointTrackingMode.Negative;
            endTrackingMode = PointTrackingMode.Positive;
            break;

        case EdgePositive:
            startTrackingMode = PointTrackingMode.Positive;
            endTrackingMode = PointTrackingMode.Positive;
            break;

        case EdgeNegative:
            startTrackingMode = PointTrackingMode.Negative;
            endTrackingMode = PointTrackingMode.Negative;
            break;

        case Custom:
        default:
            throw new UnsupportedOperationException();
        }

        SnapshotPoint start = getStart().translateTo(targetSnapshot, startTrackingMode);
        SnapshotPoint end = getEnd().translateTo(targetSnapshot, endTrackingMode);
        assert targetSnapshot.getTextBuffer().equals(snapshot.getTextBuffer()) : "exception should have been thrown";
        return new SnapshotSpan(start, end);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SnapshotSpan)) {
            return false;
        }

        return equals((SnapshotSpan)obj);
    }

    public boolean equals(SnapshotSpan other) {
        if (other == null) {
            return false;
        }

        return this.snapshot.equals(other.snapshot)
            && this.span.equals(other.span);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.snapshot != null ? this.snapshot.hashCode() : 0);
        hash = 23 * hash + (this.span != null ? this.span.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        int version = getSnapshot().getVersion().getVersionNumber();
        String spanString = getSpan().toString();
        int textLength = Math.min(this.getLength(), 40);
        String text = getSnapshot().subSequence(getStart().getPosition(), getStart().getPosition() + textLength).toString();
        return String.format("v%d_%s_'%s'", version, spanString, text);
    }
}
