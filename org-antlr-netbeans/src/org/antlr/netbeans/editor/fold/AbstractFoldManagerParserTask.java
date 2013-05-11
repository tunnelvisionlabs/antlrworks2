/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.fold;

import java.util.Collection;
import java.util.EnumSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 * @param <SemanticData>
 */
public abstract class AbstractFoldManagerParserTask<SemanticData> implements ParserTask {

    private final ParserDataDefinition<SemanticData> semanticDataDefinition;

    protected AbstractFoldManagerParserTask(ParserDataDefinition<SemanticData> semanticDataDefinition) {
        Parameters.notNull("semanticDataDefinition", semanticDataDefinition);
        this.semanticDataDefinition = semanticDataDefinition;
    }

    @Override
    public abstract ParserTaskDefinition getDefinition();

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        if (snapshot.getVersionedDocument().getDocument() == null) {
            // no code folding updates for background parsing
            return;
        }

        ParserData<SemanticData> parserData = getSemanticData(taskManager, context, snapshot);
        if (parserData == null) {
            return;
        }

        Runnable scanner = getScanner(parserData);
        scanner.run();
    }

    protected ParserData<SemanticData> getSemanticData(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot)
        throws InterruptedException, ExecutionException {

        Future<ParserData<SemanticData>> futureData = taskManager.getData(snapshot, context.getComponent(), semanticDataDefinition, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
        if (futureData == null) {
            return null;
        }

        return futureData.get();
    }

    protected abstract Runnable getScanner(ParserData<SemanticData> data);

}
