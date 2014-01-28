/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public class ParserDebuggerParserDataDefinitions {
    private static final Logger LOGGER = Logger.getLogger(ParserDebuggerParserDataDefinitions.class.getName());

    public static final ParserDataDefinition<FileParseResult> FILE_PARSE_RESULT = new FileParseResultDataDefinition();
    public static final ParserDataDefinition<ParserRuleContext> REFERENCE_PARSE_TREE = new ReferenceParseTreeDataDefinition();
    public static final ParserDataDefinition<Tagger<TokenTag<Token>>> LEXER_TOKENS = new LexerTokensDataDefinition();
    public static final ParserDataDefinition<Boolean> PARSE_TREE_UI_VISIBLE = new ParseTreeUIVisibleDataDefinition();

    private ParserDebuggerParserDataDefinitions() {
    }

    public static <T> T tryGetData(ParserTaskManager taskManager, DocumentSnapshot snapshot, ParserDataDefinition<T> definition, Collection<ParserDataOptions> options) {
        Future<ParserData<T>> futureData = taskManager.getData(snapshot, definition, options);
        if (futureData == null) {
            return null;
        }

        try {
            ParserData<T> parserData = futureData.get();
            if (parserData == null) {
                return null;
            }

            return parserData.getData();
        } catch (InterruptedException | ExecutionException ex) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, String.format("An exception occurred while parsing '%s' data.", definition.getName()), ex);
            }
        }

        return null;
    }

    @MimeRegistration(mimeType=ParserDebuggerEditorKit.PARSER_DEBUGGER_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<FileParseResult> getFileParseResultDataDefinition() {
        return FILE_PARSE_RESULT;
    }

    @MimeRegistration(mimeType=ParserDebuggerEditorKit.PARSER_DEBUGGER_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<ParserRuleContext> getReferenceParseTreeDataDefinition() {
        return REFERENCE_PARSE_TREE;
    }

    @MimeRegistration(mimeType=ParserDebuggerEditorKit.PARSER_DEBUGGER_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<Tagger<TokenTag<Token>>> getLexerTokensDataDefinition() {
        return LEXER_TOKENS;
    }

    private static final class FileParseResultDataDefinition extends ParserDataDefinition<FileParseResult> {

        public FileParseResultDataDefinition() {
            super("Parser Debugger File Parse Result", FileParseResult.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class ReferenceParseTreeDataDefinition extends ParserDataDefinition<ParserRuleContext> {

        public ReferenceParseTreeDataDefinition() {
            super("Parser Debugger Reference Parse Tree", ParserRuleContext.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class LexerTokensDataDefinition extends ParserDataDefinition<Tagger<TokenTag<Token>>> {

        @SuppressWarnings("unchecked")
        public LexerTokensDataDefinition() {
            super("Parser Debugger Lexer Tokens", (Class<Tagger<TokenTag<Token>>>)(Object)Tagger.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class ParseTreeUIVisibleDataDefinition extends ParserDataDefinition<Boolean> {

        public ParseTreeUIVisibleDataDefinition() {
            super("Parse Tree UI Visible", Boolean.class, false, false, ParserTaskScheduler.MANUAL_TASK_SCHEDULER);
        }

    }
}
