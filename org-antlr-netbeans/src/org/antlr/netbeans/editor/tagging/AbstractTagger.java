/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.tagging;

import java.util.List;
import org.netbeans.lib.editor.util.ListenerList;

/**
 *
 * @author Sam Harwell
 * @param <T>
 */
public abstract class AbstractTagger<T extends Tag> implements Tagger<T> {
    private final ListenerList<SnapshotPositionRegionEventListener> listeners =
        new ListenerList<>();

    @Override
    public final void addTagsChangeListener(SnapshotPositionRegionEventListener listener) {
        listeners.add(listener);
    }

    @Override
    public final void removeTagsChangeListener(SnapshotPositionRegionEventListener listener) {
        listeners.remove(listener);
    }

    protected final void fireTagsChanged(SnapshotPositionRegionEvent event) {
        List<SnapshotPositionRegionEventListener> temp = listeners.getListeners();
        for (SnapshotPositionRegionEventListener listener : temp) {
            listener.snapshotPositionRegionEvent(event);
        }
    }
}
