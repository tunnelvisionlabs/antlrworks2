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
import java.util.concurrent.ExecutionException;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public interface ParserTask {

    @NonNull ParserTaskDefinition getDefinition();

    void parse(@NonNull ParserTaskManager taskManager, @NonNull ParseContext context, @NonNull DocumentSnapshot snapshot, @NonNull Collection<? extends ParserDataDefinition<?>> requestedData, @NonNull ParserResultHandler results)
        throws InterruptedException, ExecutionException;

}
