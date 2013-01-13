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
import org.antlr.netbeans.editor.navigation.AbstractNavigatorUpdateParserTask;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.SingletonParserTaskProvider;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.antlr4.navigation.ParseTreeNode;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.antlr.works.editor.st4.TemplateParserDataDefinitions;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public final class ParseTreeNavigatorUpdateParserTask extends AbstractNavigatorUpdateParserTask<TemplateParseTreeNavigatorPanel, ParserRuleContext<Token>> {
    private ParseTreeNavigatorUpdateParserTask() {
        super(TemplateParserDataDefinitions.REFERENCE_PARSE_TREE);
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    protected TemplateParseTreeNavigatorPanel getActiveNavigatorPanel() {
        return TemplateParseTreeNavigatorPanel.getInstance();
    }

    @Override
    protected void refresh(ParseContext parseContext, DocumentSnapshot snapshot, TemplateParseTreeNavigatorPanel panel, ParserRuleContext<Token> data) {
        panel.setCurrentFile(snapshot.getVersionedDocument().getFileObject());
        panel.setParseTree(new ParseTreeNode(data));
    }

    private static final class Definition extends AbstractDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Arrays.<ParserDataDefinition<?>>asList(
                TemplateParserDataDefinitions.COMPILED_MODEL,
                TemplateParserDataDefinitions.REFERENCE_PARSE_TREE,
                TemplateParserDataDefinitions.PARSE_TREE_UI_VISIBLE);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("StringTemplate Parse Tree Navigator Update", INPUTS);
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
            return new ParseTreeNavigatorUpdateParserTask();
        }

    }

}
