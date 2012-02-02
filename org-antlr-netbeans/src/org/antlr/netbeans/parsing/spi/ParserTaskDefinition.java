/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi;

import java.util.Collection;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class ParserTaskDefinition {
    @NonNull
    private final String name;
    @NonNull
    private final Collection<ParserDataDefinition<?>> inputs;
    @NonNull
    private final Collection<ParserDataDefinition<?>> outputs;
    @NullAllowed
    private final Class<? extends ParserTaskScheduler> scheduler;

    public ParserTaskDefinition(@NonNull String name, @NonNull Collection<ParserDataDefinition<?>> inputs, @NonNull Collection<ParserDataDefinition<?>> outputs, @NullAllowed Class<? extends ParserTaskScheduler> scheduler) {
        Parameters.notNull("name", name);
        Parameters.notNull("inputs", inputs);
        Parameters.notNull("outputs", outputs);

        this.name = name;
        this.inputs = inputs;
        this.outputs = outputs;
        this.scheduler = scheduler;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public Collection<ParserDataDefinition<?>> getInputs() {
        return inputs;
    }

    @NonNull
    public Collection<ParserDataDefinition<?>> getOutputs() {
        return outputs;
    }

    @CheckForNull
    public Class<? extends ParserTaskScheduler> getScheduler() {
        return scheduler;
    }

    public boolean isInterruptable() {
        return false;
    }

    @Override
    public String toString() {
        return "Task:" + getName();
    }
}
