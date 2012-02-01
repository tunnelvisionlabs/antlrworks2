/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
