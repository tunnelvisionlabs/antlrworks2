/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.navigation;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;

/**
 *
 * @author Sam Harwell
 * @param <TPanel>
 * @param <TData>
 */
public abstract class AbstractNavigatorUpdateParserTask<TPanel extends AbstractNavigatorPanel<?>, TData> implements ParserTask {
    // -J-Dorg.antlr.netbeans.editor.navigation.AbstractNavigatorUpdateParserTask.level=FINE
    private static final Logger LOGGER = Logger.getLogger(AbstractNavigatorUpdateParserTask.class.getName());

    private final Object lock = new Object();

    private final ParserDataDefinition<? extends TData> rootDataDefinition;

    protected AbstractNavigatorUpdateParserTask(@NonNull ParserDataDefinition<? extends TData> rootDataDefinition) {
        this.rootDataDefinition = rootDataDefinition;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext parseContext, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        synchronized (lock) {
            TPanel panel = getActiveNavigatorPanel();
            if (panel == null) {
                LOGGER.log(Level.FINE, "Navigator update cancelled: active panel instance not found.");
                return;
            }

            try {
                if (!panel.isExpectedContext(DataObject.find(snapshot.getVersionedDocument().getFileObject()))) {
                    return;
                }
            } catch (DataObjectNotFoundException ex) {
                return;
            }

            Future<? extends ParserData<? extends TData>> futureData = taskManager.getData(snapshot, rootDataDefinition, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS));
            ParserData<? extends TData> parserData = futureData != null ? futureData.get() : null;
            TData root = parserData != null ? parserData.getData() : null;
            if (root == null) {
                return;
            }

            refresh(parseContext, snapshot, panel, root);
        }
    }

    @CheckForNull
    protected abstract TPanel getActiveNavigatorPanel();

    protected abstract void refresh(@NonNull ParseContext parseContext, @NonNull DocumentSnapshot snapshot, @NonNull TPanel panel, @NonNull TData data);

    protected static abstract class AbstractDefinition extends ParserTaskDefinition {
        protected AbstractDefinition(@NonNull String name, @NonNull Collection<? extends ParserDataDefinition<?>> inputs) {
            super(name, inputs, Collections.<ParserDataDefinition<?>>emptyList(), ParserTaskScheduler.INPUT_SENSITIVE_TASK_SCHEDULER);
        }

        protected AbstractDefinition(@NonNull String name, @NonNull Collection<? extends ParserDataDefinition<?>> inputs, @NonNull Collection<? extends ParserDataDefinition<?>> outputs) {
            super(name, inputs, outputs, ParserTaskScheduler.INPUT_SENSITIVE_TASK_SCHEDULER);
        }

        protected AbstractDefinition(@NonNull String name, @NonNull Collection<? extends ParserDataDefinition<?>> inputs, @NonNull Collection<? extends ParserDataDefinition<?>> outputs, @NullAllowed Class<? extends ParserTaskScheduler> scheduler) {
            super(name, inputs, outputs, scheduler);
        }
    }
}
