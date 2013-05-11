/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.experimental.CurrentRuleContextData;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.GrammarSpecContext;
import org.antlr.works.editor.grammar.parser.CompiledModel;
import org.antlr.works.editor.grammar.semantics.GrammarAnnotatedParseTree;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public class GrammarParserDataDefinitions {
    private static final Logger LOGGER = Logger.getLogger(GrammarParserDataDefinitions.class.getName());

    public static final ParserDataDefinition<CompiledModel> COMPILED_MODEL = new CompiledModelDataDefinition();

    public static final ParserDataDefinition<List<Anchor>> REFERENCE_ANCHOR_POINTS = new ReferenceAnchorPointsDataDefinition();
    public static final ParserDataDefinition<GrammarSpecContext> REFERENCE_PARSE_TREE = new ReferenceParseTreeDataDefinition();
    public static final ParserDataDefinition<GrammarAnnotatedParseTree> ANNOTATED_PARSE_TREE = new AnnotatedParseTreeDataDefinition();

    public static final ParserDataDefinition<List<Anchor>> DYNAMIC_ANCHOR_POINTS = new DynamicAnchorPointsDataDefinition();
    public static final ParserDataDefinition<Tagger<TokenTag<Token>>> LEXER_TOKENS = new LexerTokensDataDefinition();
    public static final ParserDataDefinition<CurrentRuleContextData> CURRENT_RULE_CONTEXT = new CurrentRuleContextDataDefinition();
    public static final ParserDataDefinition<FileModel> FILE_MODEL = new FileModelDataDefinition();

    public static final ParserDataDefinition<Description> NAVIGATOR_ROOT = new NavigatorRootDataDefinition();
    public static final ParserDataDefinition<Boolean> NAVIGATOR_UI_VISIBLE = new NavigatorUIVisibleDataDefinition();
    public static final ParserDataDefinition<Boolean> PARSE_TREE_UI_VISIBLE = new ParseTreeUIVisibleDataDefinition();

    private GrammarParserDataDefinitions() {
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

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<CompiledModel> getCompiledModelDataDefinition() {
        return COMPILED_MODEL;
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<List<Anchor>> getReferenceAnchorPointsDataDefinition() {
        return REFERENCE_ANCHOR_POINTS;
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<GrammarSpecContext> getReferenceParseTreeDataDefinition() {
        return REFERENCE_PARSE_TREE;
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<GrammarAnnotatedParseTree> getAnnotatedParseTreeDataDefinition() {
        return ANNOTATED_PARSE_TREE;
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<List<Anchor>> getDynamicAnchorPointsDataDefinition() {
        return DYNAMIC_ANCHOR_POINTS;
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<Tagger<TokenTag<Token>>> getLexerTokensDataDefinition() {
        return LEXER_TOKENS;
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<CurrentRuleContextData> getCurrentRuleContextDataDefinition() {
        return CURRENT_RULE_CONTEXT;
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<FileModel> getFileModelDataDefinition() {
        return FILE_MODEL;
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<Description> getNavigatorRootDataDefinition() {
        return NAVIGATOR_ROOT;
    }

    private static final class CompiledModelDataDefinition extends ParserDataDefinition<CompiledModel> {

        public CompiledModelDataDefinition() {
            super("Grammar Compiled Model", CompiledModel.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class ReferenceAnchorPointsDataDefinition extends ParserDataDefinition<List<Anchor>> {

        @SuppressWarnings("unchecked")
        public ReferenceAnchorPointsDataDefinition() {
            super("Grammar Reference Anchor Points", (Class<List<Anchor>>)(Object)List.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class ReferenceParseTreeDataDefinition extends ParserDataDefinition<GrammarSpecContext> {

        public ReferenceParseTreeDataDefinition() {
            super("Grammar Reference Parse Tree", GrammarSpecContext.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class AnnotatedParseTreeDataDefinition extends ParserDataDefinition<GrammarAnnotatedParseTree> {

        public AnnotatedParseTreeDataDefinition() {
            super("Grammar Annotated Parse Tree", GrammarAnnotatedParseTree.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class DynamicAnchorPointsDataDefinition extends ParserDataDefinition<List<Anchor>> {

        @SuppressWarnings("unchecked")
        public DynamicAnchorPointsDataDefinition() {
            super("Grammar Dynamic Anchor Points", (Class<List<Anchor>>)(Object)List.class, false, true, ParserTaskScheduler.EDITOR_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class LexerTokensDataDefinition extends ParserDataDefinition<Tagger<TokenTag<Token>>> {

        @SuppressWarnings("unchecked")
        public LexerTokensDataDefinition() {
            super("Grammar Lexer Tokens", (Class<Tagger<TokenTag<Token>>>)(Object)Tagger.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class CurrentRuleContextDataDefinition extends ParserDataDefinition<CurrentRuleContextData> {

        public CurrentRuleContextDataDefinition() {
            super("Grammar Current Rule Context", CurrentRuleContextData.class, true, true, ParserTaskScheduler.CURSOR_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class FileModelDataDefinition extends ParserDataDefinition<FileModel> {

        public FileModelDataDefinition() {
            super("Grammar File Model", FileModel.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class NavigatorRootDataDefinition extends ParserDataDefinition<Description> {

        public NavigatorRootDataDefinition() {
            super("Grammar Navigator Root", Description.class, false, true, ParserTaskScheduler.INPUT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class NavigatorUIVisibleDataDefinition extends ParserDataDefinition<Boolean> {

        public NavigatorUIVisibleDataDefinition() {
            super("Grammar Navigator UI Visible", Boolean.class, false, false, ParserTaskScheduler.MANUAL_TASK_SCHEDULER);
        }

    }

    private static final class ParseTreeUIVisibleDataDefinition extends ParserDataDefinition<Boolean> {

        public ParseTreeUIVisibleDataDefinition() {
            super("Parse Tree UI Visible", Boolean.class, false, false, ParserTaskScheduler.MANUAL_TASK_SCHEDULER);
        }

    }
}
