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
package org.antlr.netbeans.editor.commenting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.text.BadLocationException;
import org.antlr.netbeans.editor.DocumentPoint;
import org.antlr.netbeans.editor.DocumentSpan;
import org.antlr.netbeans.editor.TextEdit;
import org.netbeans.editor.BaseDocument;
import org.openide.util.Exceptions;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
class TextEditImpl implements TextEdit {
    final BaseDocument document;
    private final List<Change> changes = new ArrayList<Change>();

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
                        Exceptions.printStackTrace(ex);
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
