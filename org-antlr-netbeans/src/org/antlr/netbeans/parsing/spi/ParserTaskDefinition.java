/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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

    @Override
    public String toString() {
        return "Task:" + getName();
    }
}
