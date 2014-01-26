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
import org.antlr.netbeans.editor.navigation.AbstractNavigatorUpdateParserTask;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.SingletonParserTaskProvider;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.works.editor.antlr4.navigation.ParseTreeNode;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public final class ParseTreeNavigatorUpdateParserTask extends AbstractNavigatorUpdateParserTask<GrammarParseTreeNavigatorPanel, ParserRuleContext> {
    private ParseTreeNavigatorUpdateParserTask() {
        super(GrammarParserDataDefinitions.REFERENCE_PARSE_TREE);
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    protected GrammarParseTreeNavigatorPanel getActiveNavigatorPanel() {
        return GrammarParseTreeNavigatorPanel.getInstance();
    }

    @Override
    protected void refresh(ParseContext parseContext, DocumentSnapshot snapshot, GrammarParseTreeNavigatorPanel panel, ParserRuleContext data) {
        panel.setCurrentFile(snapshot.getVersionedDocument().getFileObject());
        panel.setParseTree(new ParseTreeNode(data, Arrays.asList(GrammarParser.ruleNames)));
    }

    private static final class Definition extends AbstractDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Arrays.<ParserDataDefinition<?>>asList(
                GrammarParserDataDefinitions.COMPILED_MODEL,
                GrammarParserDataDefinitions.REFERENCE_PARSE_TREE,
                GrammarParserDataDefinitions.PARSE_TREE_UI_VISIBLE);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Grammar Parse Tree Navigator Update", INPUTS);
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
            return new ParseTreeNavigatorUpdateParserTask();
        }

    }

}
