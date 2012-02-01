/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi;

/**
 *
 * @author Sam Harwell
 */
public enum ParserDataOptions {

    /**
     * If results from parsing a previous DocumentSnapshot are cached, return those
     * of having a ParserTask update the data.
     */
    ALLOW_STALE,

    /**
     * Returns <code>null</code> instead of updating the data if the data is not
     * already cached. When used with {@link #ALLOW_STALE}, {@link ParserTaskManager#getData}
     * will return cached data from an earlier snapshot if available.
     */
    NO_UPDATE,

    /**
     * If the {@link ParserTaskManager} has call a ParserTask to update the
     * requested data, wait for the task to complete before returning from
     * {@link ParserTaskManager.getData}. The task manager may (but is not
     * required to) execute the parse operation on the current thread.
     */
    SYNCHRONOUS,

}
