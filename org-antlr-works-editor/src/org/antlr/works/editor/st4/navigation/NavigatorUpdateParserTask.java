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
import org.antlr.netbeans.editor.navigation.AbstractNavigatorUpdateWithContextParserTask;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.SingletonParserTaskProvider;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.antlr.works.editor.st4.TemplateParserDataDefinitions;
import org.antlr.works.editor.st4.experimental.CurrentTemplateContextData;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public final class NavigatorUpdateParserTask extends AbstractNavigatorUpdateWithContextParserTask<TemplatesPanel, Description, CurrentTemplateContextData> {
    private NavigatorUpdateParserTask() {
        super(TemplateParserDataDefinitions.NAVIGATOR_ROOT, TemplateParserDataDefinitions.CURRENT_TEMPLATE_CONTEXT);
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    protected TemplatesPanel getActiveNavigatorPanel() {
        return TemplatesPanel.getInstance();
    }

    @Override
    protected void refresh(ParseContext parseContext, DocumentSnapshot snapshot, TemplatesPanel panel, Description data, CurrentTemplateContextData context) {
        String selectedTemplate = context != null ? context.getTemplateName() : null;
        panel.getComponent().refresh(data, selectedTemplate);
    }

    private static final class Definition extends AbstractDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Arrays.<ParserDataDefinition<?>>asList(
                TemplateParserDataDefinitions.NAVIGATOR_ROOT,
                TemplateParserDataDefinitions.CURRENT_TEMPLATE_CONTEXT,
                TemplateParserDataDefinitions.NAVIGATOR_UI_VISIBLE);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("StringTemplate Navigator Update", INPUTS);
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
