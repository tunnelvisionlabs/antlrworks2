/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi;

import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 * @param <T>
 */
public class ParserDataDefinition<T> {
    @NonNull
    private final String name;
    @NonNull
    private final Class<T> dataType;
    private final boolean componentSpecific;
    private final boolean cacheable;
    @NullAllowed
    private final Class<? extends ParserTaskScheduler> scheduler;

    public ParserDataDefinition(@NonNull String name, @NonNull Class<T> dataType, boolean componentSpecific, boolean cacheable, @NullAllowed Class<? extends ParserTaskScheduler> scheduler) {
        Parameters.notNull("name", name);
        Parameters.notNull("dataType", dataType);

        this.name = name;
        this.dataType = dataType;
        this.componentSpecific = componentSpecific;
        this.cacheable = cacheable;
        this.scheduler = scheduler;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public Class<T> getDataType() {
        return dataType;
    }

    /** True if the data is specific to a {@link JTextComponent} instead of just a {@link Document}.
     * @return
     */
    public boolean isComponentSpecific() {
        return componentSpecific;
    }

    /** True if the data is unique for a given {@link Document} or {@link JTextComponent}
     *  and may be cached. Some data, such as the context where the caret is, may change
     *  even when the content of the document has not changed.
     * @return
     */
    public boolean isCacheable() {
        return cacheable;
    }

    public boolean isInterruptable() {
        return false;
    }

    @CheckForNull
    public Class<? extends ParserTaskScheduler> getScheduler() {
        return scheduler;
    }

    @Override
    public String toString() {
        return "Data:" + getName();
    }
}
