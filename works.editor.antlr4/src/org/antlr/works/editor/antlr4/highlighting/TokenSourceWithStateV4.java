/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.highlighting;

import java.io.Closeable;
import org.antlr.v4.runtime.TokenSource;

/**
 *
 * @author Sam Harwell
 * @param <T>
 */
public interface TokenSourceWithStateV4<T> extends TokenSource, Closeable {

    T getCurrentState();

    @Override
    void close();

}
