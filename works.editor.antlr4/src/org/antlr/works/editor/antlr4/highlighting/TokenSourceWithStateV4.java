/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.highlighting;

import org.antlr.v4.runtime.TokenSource;

/**
 *
 * @author Sam Harwell
 */
public interface TokenSourceWithStateV4<Symbol, T> extends TokenSource<Symbol> {
    public T getCurrentState();
}
