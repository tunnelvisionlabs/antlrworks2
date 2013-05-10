/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.commenting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import org.antlr.netbeans.editor.DocumentPoint;
import org.antlr.netbeans.editor.DocumentSpan;
import org.antlr.netbeans.editor.TextEdit;
import org.netbeans.editor.BaseDocument;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
class TextEditImpl implements TextEdit {
    // -J-Dorg.antlr.netbeans.editor.commenting.TextEditImpl.level=FINE
    private static final Logger LOGGER = Logger.getLogger(TextEditImpl.class.getName());

    final BaseDocument document;
    private final List<Change> changes = new ArrayList<>();

    public TextEditImpl(BaseDocument document) {
        Parameters.notNull("document", document);
        this.document = document;
    }

    @Override
    public boolean apply() {
        Collections.sort(changes, new Comparator<Change>() {

            @Override
            public int compare(Change o1, Change o2) {
                return o1.offset - o2.offset;
            }
        });
        document.runAtomicAsUser(new Runnable() {

            @Override
            public void run() {
                for (int i = changes.size() - 1; i >= 0; i--) {
                    Change change = changes.get(i);
                    try {
                        if (change.length > 0) {
                            document.remove(change.offset, change.length);
                        }
                        if (change.newText != null && !change.newText.isEmpty()) {
                            document.insertString(change.offset, change.newText, null);
                        }
                    } catch (BadLocationException ex) {
                        LOGGER.log(Level.WARNING, "An exception occurred while applying text changes.", ex);
                    }
                }
            }
        });
        return true;
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean remove(DocumentSpan span) {
        Change change = new Change(span.getStart().getOffset(), span.getLength(), null);
        if (!canAdd(change)) {
            return false;
        }
        changes.add(change);
        return true;
    }

    @Override
    public boolean remove(int startPosition, int length) {
        Change change = new Change(startPosition, length, null);
        if (!canAdd(change)) {
            return false;
        }
        changes.add(change);
        return true;
    }

    @Override
    public boolean insert(DocumentPoint point, String text) {
        if (text == null || text.isEmpty()) {
            return true;
        }
        Change change = new Change(point.getOffset(), 0, text);
        if (!canAdd(change)) {
            return false;
        }
        changes.add(change);
        return true;
    }

    @Override
    public boolean insert(int position, String text) {
        if (text == null || text.isEmpty()) {
            return true;
        }
        Change change = new Change(position, 0, text);
        if (!canAdd(change)) {
            return false;
        }
        changes.add(change);
        return true;
    }

    private boolean canAdd(Change change) {
        for (Change existing : changes) {
            if (change.overlaps(existing)) {
                return false;
            }
        }
        return true;
    }

    private static class Change {

        public int offset;
        public int length;
        public String newText;

        public Change(int offset, int length, String newText) {
            this.offset = offset;
            this.length = length;
            this.newText = newText;
        }

        public boolean overlaps(Change other) {
            Parameters.notNull("other", other);
            if (offset > other.offset + other.length) {
                return false;
            }
            if (other.offset > offset + length) {
                return false;
            }
            return true;
        }
    }

}
