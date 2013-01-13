/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.navigation;

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
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.experimental.CurrentRuleContextData;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public final class NavigatorUpdateParserTask extends AbstractNavigatorUpdateWithContextParserTask<GrammarRulesPanel, Description, CurrentRuleContextData> {
    private NavigatorUpdateParserTask() {
        super(GrammarParserDataDefinitions.NAVIGATOR_ROOT, GrammarParserDataDefinitions.CURRENT_RULE_CONTEXT);
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    protected GrammarRulesPanel getActiveNavigatorPanel() {
        return GrammarRulesPanel.getInstance();
    }

    @Override
    protected void refresh(ParseContext parseContext, DocumentSnapshot snapshot, GrammarRulesPanel panel, Description data, CurrentRuleContextData context) {
        String selectedRule = context != null ? context.getRuleName() : null;
        panel.getComponent().refresh(data, selectedRule);
    }

    private static final class Definition extends AbstractDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Arrays.<ParserDataDefinition<?>>asList(
                GrammarParserDataDefinitions.NAVIGATOR_ROOT,
                GrammarParserDataDefinitions.CURRENT_RULE_CONTEXT,
                GrammarParserDataDefinitions.NAVIGATOR_UI_VISIBLE);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Grammar Navigator Update", INPUTS);
        }
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserTaskProvider.class)
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
