/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
}
