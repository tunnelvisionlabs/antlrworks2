/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr3.highlighting;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.TokenSource;

/**
 *
 * @author Sam Harwell
 * @param <T>
 */
public interface TokenSourceWithState<T> extends TokenSource {
    public CharStream getCharStream();
    
    public T getState();
}
