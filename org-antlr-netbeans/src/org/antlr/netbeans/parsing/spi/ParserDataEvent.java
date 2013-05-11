/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi;

import java.util.EventObject;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 * @param <T>
 */
public class ParserDataEvent<T> extends EventObject {
    private final ParserDataDefinition<T> definition;
    private final ParserData<T> data;

    public ParserDataEvent(Object source, @NonNull ParserDataDefinition<T> definition, @NonNull ParserData<T> data) {
        super(source);
        this.definition = definition;
        this.data = data;
    }

    public ParserDataDefinition<T> getDefinition() {
        return definition;
    }

    public ParserData<T> getData() {
        return data;
    }
}
