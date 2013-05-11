/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.navigation;

import java.lang.ref.WeakReference;
import java.util.EnumSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.Lookup;

/**
 *
 * @author Sam Harwell
 * @param <TPanel>
 * @param <TData>
 * @param <TContext>
 */
public abstract class AbstractNavigatorUpdateWithContextParserTask<TPanel extends AbstractNavigatorPanel<?>, TData, TContext> extends AbstractNavigatorUpdateParserTask<TPanel, TData> {
    private static WeakReference<ParserTaskManager> parserTaskManager =
        new WeakReference<>(null);

    private final ParserDataDefinition<? extends TContext> contextDataDefinition;

    protected AbstractNavigatorUpdateWithContextParserTask(@NonNull ParserDataDefinition<? extends TData> rootDataDefinition, @NonNull ParserDataDefinition<? extends TContext> contextDataDefinition) {
        super(rootDataDefinition);
        this.contextDataDefinition = contextDataDefinition;
    }

    private static ParserTaskManager getParserTaskManager() {
        ParserTaskManager result = parserTaskManager.get();
        if (result == null) {
            result = Lookup.getDefault().lookup(ParserTaskManager.class);
            parserTaskManager = new WeakReference<>(result);
        }

        return result;
    }

    protected abstract void refresh(@NonNull ParseContext parseContext, @NonNull DocumentSnapshot snapshot, @NonNull TPanel panel, @NonNull TData data, @NullAllowed TContext context);

    @Override
    protected final void refresh(ParseContext parseContext, DocumentSnapshot snapshot, TPanel panel, TData data) {
        ParserTaskManager taskManager = getParserTaskManager();

        TContext context = null;
        if (contextDataDefinition != null) {
            Future<? extends ParserData<? extends TContext>> futureContextData = taskManager.getData(snapshot, contextDataDefinition, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS));
            ParserData<? extends TContext> parserContextData;
            try {
                parserContextData = futureContextData != null ? futureContextData.get() : null;
            } catch (InterruptedException | ExecutionException ex) {
                parserContextData = null;
            }

            context = parserContextData != null ? parserContextData.getData() : null;
        }

        refresh(parseContext, snapshot, panel, data, context);
    }
}
