/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.navigation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.SingletonParserTaskProvider;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.antlr.works.editor.st4.TemplateParserDataDefinitions;
import org.antlr.works.editor.st4.experimental.CurrentTemplateContextData;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public class NavigatorUpdateParserTask implements ParserTask {

    private final Object lock = new Object();

    private NavigatorUpdateParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext parseContext, DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        synchronized (lock) {
            TemplatesPanel panel = TemplatesPanel.getInstance();
            if (panel == null) {
                return;
            }

            JTextComponent currentComponent = EditorRegistry.lastFocusedComponent();
            if (currentComponent == null) {
                return;
            }

            Document document = currentComponent.getDocument();
            if (document == null || !VersionedDocumentUtilities.getVersionedDocument(document).equals(snapshot.getVersionedDocument())) {
                return;
            }

            Future<ParserData<Description>> futureData = taskManager.getData(snapshot, TemplateParserDataDefinitions.NAVIGATOR_ROOT, EnumSet.of(ParserDataOptions.NO_UPDATE));
            ParserData<Description> parserData = futureData.get();
            if (parserData == null) {
                return;
            }

            Description root = parserData.getData();

            Future<ParserData<CurrentTemplateContextData>> futureContextData = taskManager.getData(snapshot, TemplateParserDataDefinitions.CURRENT_TEMPLATE_CONTEXT, EnumSet.of(ParserDataOptions.NO_UPDATE));
            ParserData<CurrentTemplateContextData> parserContextData = futureContextData.get();
            CurrentTemplateContextData context = null;
            if (parserContextData != null) {
                context = parserContextData.getData();
            }

            String selectedRule = context != null ? context.getTemplateName() : null;

            TemplatesPanelUI ui = panel != null ? panel.getComponent() : null;
            if (ui == null) {
                return;
            }

            ui.refresh(root, selectedRule);
        }
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Arrays.<ParserDataDefinition<?>>asList(
                TemplateParserDataDefinitions.NAVIGATOR_ROOT,
                TemplateParserDataDefinitions.CURRENT_TEMPLATE_CONTEXT);

        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Collections.<ParserDataDefinition<?>>emptyList();

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("StringTemplate Navigator Update", INPUTS, OUTPUTS, ParserTaskScheduler.INPUT_SENSITIVE_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends SingletonParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTaskImpl() {
            return new NavigatorUpdateParserTask();
        }

    }

}
