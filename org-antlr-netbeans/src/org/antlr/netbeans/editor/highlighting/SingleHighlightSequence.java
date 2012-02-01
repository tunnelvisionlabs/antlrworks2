/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.highlighting;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleHighlightSequence extends AbstractCollection<Highlight> {
    private final Highlight highlight;

    public SingleHighlightSequence(Highlight highlight) {
        this.highlight = highlight;
    }

    @Override
    public Iterator<Highlight> iterator() {
        return new IteratorImpl(highlight);
    }

    @Override
    public int size() {
        return 1;
    }
    
    private static class IteratorImpl implements Iterator<Highlight> {
        private final Highlight highlight;
        private int index = -1;

        public IteratorImpl(Highlight highlight) {
            this.highlight = highlight;
        }

        @Override
        public boolean hasNext() {
            return index == -1;
        }

        @Override
        public Highlight next() {
            if (index >= 0) {
                throw new NoSuchElementException();
            }

            index++;
            return highlight;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("The collection is read only.");
        }
    }
}
