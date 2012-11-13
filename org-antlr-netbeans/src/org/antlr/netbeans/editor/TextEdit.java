/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor;

import javax.swing.text.BadLocationException;
import org.netbeans.api.annotations.common.NonNull;

/**
 * This interface provides the ability to apply a group of edits to a
 * {@link StyledDocument} as an atomic operation.
 *
 * @author Sam Harwell
 */
public interface TextEdit {

    /**
     * Applies the pending changes to the underlying {@link StyledDocument}.
     *
     * @return {@code true} if the document changed as a result of this call,
     * otherwise {@code false}.
     *
     * @throws IllegalStateException if the changes were already applied through
     * a previous call to {@link #apply}, or if the transaction was aborted by
     * calling {@link #dispose}, or if other changes have been made to the
     * document after this {@link TextEdit} instance was created.
     */
    boolean apply();

    /**
     * Cleans up resources used by this {@link TextEdit} instance. This method
     * should be called when the {@link TextEdit} instance is no longer required
     * whether or not the changes were applied by calling {@link #apply}. Is
     * called If this method is called without first calling {@link #apply}, the
     * edit operation is aborted and no changes are made to the underlying
     * {@link StyledDocument}.
     * <p/>
     * This method is safe to call multiple times, in which case all but the
     * first call perform no operation.
     */
    void dispose();

    /**
     * Removes a span of text from the document. If another pending edit
     * overlaps with the specified {@code span}, the remove operation is not
     * applied and this method returns {@code false}.
     *
     * @param span The span of text to remove.
     * @return {@code true} if the span is successfully removed, otherwise
     * {@code false} to indicate that another pending edit lies within the
     * specified span so this edit cannot be applied.
     *
     * @throws NullPointerException if {@code span} is {@code null}.
     *
     * @throws IllegalArgumentException if {@code span} is not a span within the
     * underlying {@link StyledDocument} of this {@link TextEdit} instance.
     *
     * @throws IllegalStateException if the changes were already applied through
     * a previous call to {@link #apply}, or if the transaction was aborted by
     * calling {@link #dispose}.
     */
    boolean remove(@NonNull DocumentSpan span);

    /**
     * Removes a span of text from the document. If another pending edit
     * overlaps with the specified span, the remove operation is not applied and
     * this method returns {@code false}.
     *
     * @param startPosition
     * @param length
     * @return {@code true} if the span is successfully removed, otherwise
     * {@code false} to indicate that another pending edit lies within the
     * specified span so this edit cannot be applied.
     *
     * @throws BadLocationException if {@code startPosition} does not lie within
     * the document, or if {@code startPosition+length} does not lie within the
     * document.
     *
     * @throws IllegalStateException if the changes were already applied through
     * a previous call to {@link #apply}, or if the transaction was aborted by
     * calling {@link #dispose}.
     */
    boolean remove(int startPosition, int length) throws BadLocationException;

    /**
     * Inserts a span of text into the document. If another pending edit
     * overlaps with the specified {@code point}, the text is not inserted and
     * this method returns {@code false}.
     *
     * @param point The position where the text should be inserted.
     * @param text The text to insert.
     * @return {@code true} if the text is successfully removed, otherwise
     * {@code false} to indicate that another pending edit overlaps with the
     * specified {@code point} so this edit cannot be applied.
     *
     * @throws NullPointerException if {@code point} is {@code null} or if
     * {@code text} is {@code null}.
     *
     * @throws IllegalArgumentException if {@code point} is not a point within
     * the underlying {@link StyledDocument} of this {@link TextEdit} instance.
     *
     * @throws IllegalStateException if the changes were already applied through
     * a previous call to {@link #apply}, or if the transaction was aborted by
     * calling {@link #dispose}.
     */
    boolean insert(DocumentPoint point, @NonNull String text);

    /**
     * Inserts a span of text into the document. If another pending edit
     * overlaps with the specified {@code position}, the text is not inserted
     * and this method returns {@code false}.
     *
     * @param position The position where the text should be inserted.
     * @param text The text to insert.
     * @return {@code true} if the text is successfully removed, otherwise
     * {@code false} to indicate that another pending edit overlaps with the
     * specified {@code position} so this edit cannot be applied.
     *
     * @throws BadLocationException if {@code position} does not lie within the
     * document.
     *
     * @throws NullPointerException if {@code text} is {@code null}.
     *
     * @throws IllegalStateException if the changes were already applied through
     * a previous call to {@link #apply}, or if the transaction was aborted by
     * calling {@link #dispose}.
     */
    boolean insert(int position, @NonNull String text) throws BadLocationException;
}
