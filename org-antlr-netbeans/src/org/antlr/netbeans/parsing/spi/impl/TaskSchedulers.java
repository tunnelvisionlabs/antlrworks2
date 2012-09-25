/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi.impl;

import java.util.Collection;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.openide.util.Lookup;

/**
 *
 * @author Sam Harwell
 */
public class TaskSchedulers {
    private static Collection<? extends ParserTaskScheduler> taskSchedulers;

    public static void init() {
        taskSchedulers = Lookup.getDefault().lookupAll(ParserTaskScheduler.class);
        for (ParserTaskScheduler scheduler : taskSchedulers) {
            scheduler.initialize();
        }
    }

    public static Collection<? extends ParserTaskScheduler> getSchedulers() {
        return taskSchedulers;
    }

    public static <T extends ParserTaskScheduler> T getScheduler(Class<T> clazz) {
        for (ParserTaskScheduler scheduler : taskSchedulers) {
            if (scheduler.getClass() == clazz) {
                return clazz.cast(scheduler);
            }
        }

        return null;
    }
}
