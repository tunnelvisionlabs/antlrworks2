/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
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

import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class BaseParserData<T> implements ParserData<T> {
    @NonNull
    private final ParseContext context;
    @NonNull
    private final ParserDataDefinition<T> definition;
    @NonNull
    private final DocumentSnapshot snapshot;
    @NullAllowed
    private final T data;

    public BaseParserData(@NonNull ParseContext context, @NonNull ParserDataDefinition<T> definition, @NonNull DocumentSnapshot snapshot, @NullAllowed T data) {
        Parameters.notNull("context", context);
        Parameters.notNull("definition", definition);
        Parameters.notNull("snapshot", snapshot);

        this.context = context;
        this.definition = definition;
        this.snapshot = snapshot;
        this.data = data;
    }

    @Override
    public ParseContext getContext() {
        return context;
    }

    @Override
    public ParserDataDefinition<T> getDefinition() {
        return definition;
    }

    @Override
    public DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    @Override
    public T getData() {
        return data;
    }

}
