/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

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
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public final class ParserDebuggerParseTreeNavigatorUpdateParserTask extends AbstractNavigatorUpdateParserTask<ParserDebuggerParseTreeNavigatorPanel, FileParseResult> {
    private ParserDebuggerParseTreeNavigatorUpdateParserTask() {
        super(ParserDebuggerParserDataDefinitions.FILE_PARSE_RESULT);
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    protected ParserDebuggerParseTreeNavigatorPanel getActiveNavigatorPanel() {
        return ParserDebuggerParseTreeNavigatorPanel.getInstance();
    }

    @Override
    protected void refresh(ParseContext parseContext, DocumentSnapshot snapshot, ParserDebuggerParseTreeNavigatorPanel panel, FileParseResult data) {
        ParserInterpreterData parserInterpreterData = (ParserInterpreterData)snapshot.getVersionedDocument().getDocument().getProperty(ParserDebuggerEditorKit.PROP_PARSER_INTERP_DATA);
        panel.setCurrentFile(snapshot.getVersionedDocument().getFileObject());
        panel.setParseTree(new ParserDebuggerParseTreeNode(data.parseTree, parserInterpreterData.ruleNames, data.getErrorNodes(), data.associatedTransitions));
    }

    private static final class Definition extends AbstractDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Arrays.<ParserDataDefinition<?>>asList(
                ParserDebuggerParserDataDefinitions.FILE_PARSE_RESULT,
                ParserDebuggerParserDataDefinitions.PARSE_TREE_UI_VISIBLE);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Parser Debugger Parse Tree Navigator Update", INPUTS);
        }
    }

    @MimeRegistration(mimeType=ParserDebuggerEditorKit.PARSER_DEBUGGER_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends SingletonParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTaskImpl() {
            return new ParserDebuggerParseTreeNavigatorUpdateParserTask();
        }

    }

}
