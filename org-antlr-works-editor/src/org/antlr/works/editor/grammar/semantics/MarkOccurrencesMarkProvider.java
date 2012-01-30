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
package org.antlr.works.editor.grammar.semantics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Position;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.TrackingPosition;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.modules.editor.errorstripe.privatespi.Mark;
import org.netbeans.modules.editor.errorstripe.privatespi.MarkProvider;
import org.netbeans.modules.editor.errorstripe.privatespi.Status;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;

/**
 *
 * @author Sam Harwell
 */
public class MarkOccurrencesMarkProvider extends MarkProvider {

    private final Object lock = new Object();

    @NonNull
    private List<Mark> occurrences;

    public MarkOccurrencesMarkProvider() {
        this.occurrences = Collections.emptyList();
    }

    @Override
    public List<Mark> getMarks() {
        synchronized (lock) {
            return occurrences;
        }
    }

    public void setOccurrences(Collection<Mark> marks) {
        List<Mark> old;
        List<Mark> nue;

        synchronized (lock) {
            old = occurrences;
            occurrences = new ArrayList<Mark>(marks);
            nue = occurrences;
        }

        //#85919: fire outside the lock:
        firePropertyChange(PROP_MARKS, old, nue);
    }

    public static Collection<Mark> createMarks(final VersionedDocument document, final Collection<SnapshotPosition> bag, final Color color, final String tooltip) {
        final List<Mark> result = new LinkedList<Mark>();

        final Document doc = document.getDocument();
        doc.render(new Runnable() {
            @Override
            public void run() {
                DocumentSnapshot currentSnapshot = document.getCurrentSnapshot();
                for (SnapshotPosition position : bag) {
                    try {
                        SnapshotPosition translatedPosition = position;
                        if (!position.getSnapshot().equals(currentSnapshot)) {
                            TrackingPosition trackingPosition = position.getSnapshot().createTrackingPosition(position.getOffset(), TrackingPosition.Bias.Forward);
                            translatedPosition = trackingPosition.getPosition(currentSnapshot);
                        }

                        result.add(new MarkImpl(doc, doc.createPosition(translatedPosition.getOffset()), color, tooltip));
                    } catch (BadLocationException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
        });

        return result;
    }

    private static class MarkImpl implements Mark {

        private Document doc;
        private Position startOffset;
        private Color color;
        private String tooltip;

        public MarkImpl(Document doc, Position startOffset, Color color, String tooltip) {
            this.doc = doc;
            this.startOffset = startOffset;
            this.color = color;
            this.tooltip = tooltip;
        }

        @Override
        public int getType() {
            return TYPE_ERROR_LIKE;
        }

        @Override
        public Status getStatus() {
            return Status.STATUS_OK;
        }

        @Override
        public int getPriority() {
            return PRIORITY_DEFAULT;
        }

        @Override
        public Color getEnhancedColor() {
            return color;
        }

        @Override
        public int[] getAssignedLines() {
            int line = NbDocument.findLineNumber((StyledDocument) doc, startOffset.getOffset());

            return new int[] {line, line};
        }

        @Override
        public String getShortDescription() {
            return tooltip;
        }

    }

}
