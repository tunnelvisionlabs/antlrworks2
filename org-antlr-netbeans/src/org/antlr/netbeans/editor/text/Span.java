/*
 * This file is not licensed for distribution in source or binary form.
 */
package org.antlr.netbeans.editor.text;

import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public final class Span {

    private final int start;
    private final int length;

    public Span(int start, int length) {
        if (start < 0) {
            throw new IllegalArgumentException("start cannot be negative");
        }
        if (length < 0) {
            throw new IllegalArgumentException("length cannot be negative");
        }

        this.start = start;
        this.length = length;
    }

    public static Span fromBounds(int start, int end) {
        return new Span(start, end - start);
    }

    public int getStart() {
        return start;
    }

    public int getLength() {
        return length;
    }

    public int getEnd() {
        return start + length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean contains(int position) {
        return position >= start && position < getEnd();
    }

    public boolean contains(Span span) {
        Parameters.notNull("span", span);
        return span.getStart() >= start && span.getEnd() <= getEnd();
    }

    public Span intersection(Span span) {
        Parameters.notNull("span", span);
        int commonStart = Math.max(this.start, span.start);
        int commonEnd = Math.min(getEnd(), span.getEnd());
        if (commonEnd < commonStart) {
            return null;
        }

        return fromBounds(commonStart, commonEnd);
    }

    public boolean intersectsWith(Span span) {
        Parameters.notNull("span", span);
        int commonStart = Math.max(this.start, span.start);
        int commonEnd = Math.min(getEnd(), span.getEnd());
        return commonEnd >= commonStart;
    }

    public Span overlap(Span span) {
        Parameters.notNull("span", span);
        if (isEmpty() || span.isEmpty()) {
            return null;
        }

        Span intersection = intersection(span);
        if (intersection == null || intersection.isEmpty()) {
            return null;
        }

        return intersection;
    }

    public boolean overlapsWith(Span span) {
        Parameters.notNull("span", span);
        int commonStart = Math.max(this.start, span.start);
        int commonEnd = Math.min(getEnd(), span.getEnd());
        return commonEnd > commonStart;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Span)) {
            return false;
        }

        return equals((Span)obj);
    }

    public boolean equals(Span span) {
        if (span == null) {
            return false;
        }

        if (this == span) {
            return true;
        }

        return start == span.start
            && length == span.length;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.start;
        hash = 73 * hash + this.length;
        return hash;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d)", start, getEnd());
    }
}
