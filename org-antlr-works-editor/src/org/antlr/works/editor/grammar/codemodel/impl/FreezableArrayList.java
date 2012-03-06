/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel.impl;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Sam Harwell
 */
public class FreezableArrayList<E> extends ArrayList<E> {

    private boolean frozen;

    public boolean isFrozen() {
        return frozen;
    }

    public void freeze() {
        frozen = true;
    }

    @Override
    public E set(int index, E element) {
        ensureModifiable();
        return super.set(index, element);
    }

    @Override
    public boolean add(E e) {
        ensureModifiable();
        return super.add(e);
    }

    @Override
    public void add(int index, E element) {
        ensureModifiable();
        super.add(index, element);
    }

    @Override
    public boolean remove(Object o) {
        ensureModifiable();
        return super.remove(o);
    }

    @Override
    public void clear() {
        ensureModifiable();
        super.clear();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        ensureModifiable();
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        ensureModifiable();
        return super.addAll(index, c);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        ensureModifiable();
        super.removeRange(fromIndex, toIndex);
    }

    protected void ensureModifiable() {
        if (isFrozen()) {
            throw new IllegalStateException("The collection is frozen.");
        }
    }
}
