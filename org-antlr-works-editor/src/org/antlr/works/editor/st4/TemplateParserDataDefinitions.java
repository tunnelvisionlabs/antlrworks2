/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4;

import java.util.List;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.st4.codemodel.FileModel;
import org.antlr.works.editor.st4.experimental.CurrentTemplateContextData;
import org.antlr.works.editor.st4.experimental.generated.TemplateParser;
import org.antlr.works.editor.st4.experimental.generated.TemplateParser.GroupFileContext;
import org.antlr.works.editor.st4.parser.CompiledModel;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public class TemplateParserDataDefinitions {
    public static final ParserDataDefinition<CompiledModel> COMPILED_MODEL = new CompiledModelDataDefinition();

    public static final ParserDataDefinition<List<Anchor>> REFERENCE_ANCHOR_POINTS = new ReferenceAnchorPointsDataDefinition();
    @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_groupFile, version=4, dependents=Dependents.SELF)
    public static final ParserDataDefinition<GroupFileContext> REFERENCE_PARSE_TREE = new ReferenceParseTreeDataDefinition();

    public static final ParserDataDefinition<List<Anchor>> DYNAMIC_ANCHOR_POINTS = new DynamicAnchorPointsDataDefinition();
    public static final ParserDataDefinition<Tagger<TokenTag<Token>>> LEXER_TOKENS = new LexerTokensDataDefinition();
    public static final ParserDataDefinition<CurrentTemplateContextData> CURRENT_TEMPLATE_CONTEXT = new CurrentTemplateContextDataDefinition();
    public static final ParserDataDefinition<FileModel> FILE_MODEL = new FileModelDataDefinition();

    public static final ParserDataDefinition<Description> NAVIGATOR_ROOT = new NavigatorRootDataDefinition();
    public static final ParserDataDefinition<Boolean> NAVIGATOR_UI_VISIBLE = new NavigatorUIVisibleDataDefinition();
    public static final ParserDataDefinition<Boolean> PARSE_TREE_UI_VISIBLE = new ParseTreeUIVisibleDataDefinition();

    private TemplateParserDataDefinitions() {
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<CompiledModel> getCompiledModelDataDefinition() {
        return COMPILED_MODEL;
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<List<Anchor>> getReferenceAnchorPointsDataDefinition() {
        return REFERENCE_ANCHOR_POINTS;
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=ParserDataDefinition.class)
    @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_groupFile, version=4, dependents=Dependents.SELF)
    public static ParserDataDefinition<GroupFileContext> getReferenceParseTreeDataDefinition() {
        return REFERENCE_PARSE_TREE;
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<List<Anchor>> getDynamicAnchorPointsDataDefinition() {
        return DYNAMIC_ANCHOR_POINTS;
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<Tagger<TokenTag<Token>>> getLexerTokensDataDefinition() {
        return LEXER_TOKENS;
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<CurrentTemplateContextData> getCurrentTemplateContextDataDefinition() {
        return CURRENT_TEMPLATE_CONTEXT;
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<FileModel> getFileModelDataDefinition() {
        return FILE_MODEL;
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<Description> getNavigatorRootDataDefinition() {
        return NAVIGATOR_ROOT;
    }

    private static final class CompiledModelDataDefinition extends ParserDataDefinition<CompiledModel> {

        public CompiledModelDataDefinition() {
            super("StringTemplate Compiled Model", CompiledModel.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class ReferenceAnchorPointsDataDefinition extends ParserDataDefinition<List<Anchor>> {

        @SuppressWarnings("unchecked")
        public ReferenceAnchorPointsDataDefinition() {
            super("StringTemplate Reference Anchor Points", (Class<List<Anchor>>)(Object)List.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_groupFile, version=4, dependents=Dependents.SELF)
    private static final class ReferenceParseTreeDataDefinition extends ParserDataDefinition<GroupFileContext> {

        @RuleDependency(recognizer=TemplateParser.class, rule=TemplateParser.RULE_groupFile, version=4, dependents=Dependents.SELF)
        public ReferenceParseTreeDataDefinition() {
            super("StringTemplate Reference Parse Tree", GroupFileContext.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class DynamicAnchorPointsDataDefinition extends ParserDataDefinition<List<Anchor>> {

        @SuppressWarnings("unchecked")
        public DynamicAnchorPointsDataDefinition() {
            super("StringTemplate Dynamic Anchor Points", (Class<List<Anchor>>)(Object)List.class, false, true, ParserTaskScheduler.EDITOR_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class LexerTokensDataDefinition extends ParserDataDefinition<Tagger<TokenTag<Token>>> {

        @SuppressWarnings("unchecked")
        public LexerTokensDataDefinition() {
            super("StringTemplate Lexer Tokens", (Class<Tagger<TokenTag<Token>>>)(Object)Tagger.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class CurrentTemplateContextDataDefinition extends ParserDataDefinition<CurrentTemplateContextData> {

        public CurrentTemplateContextDataDefinition() {
            super("StringTemplate Current Template Context", CurrentTemplateContextData.class, true, true, ParserTaskScheduler.CURSOR_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class FileModelDataDefinition extends ParserDataDefinition<FileModel> {

        public FileModelDataDefinition() {
            super("StringTemplate File Model", FileModel.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class NavigatorRootDataDefinition extends ParserDataDefinition<Description> {

        public NavigatorRootDataDefinition() {
            super("StringTemplate Navigator Root", Description.class, false, true, ParserTaskScheduler.INPUT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class NavigatorUIVisibleDataDefinition extends ParserDataDefinition<Boolean> {

        public NavigatorUIVisibleDataDefinition() {
            super("StringTemplate Navigator UI Visible", Boolean.class, false, false, ParserTaskScheduler.MANUAL_TASK_SCHEDULER);
        }

    }

    private static final class ParseTreeUIVisibleDataDefinition extends ParserDataDefinition<Boolean> {

        public ParseTreeUIVisibleDataDefinition() {
            super("StringTemplate Parse Tree UI Visible", Boolean.class, false, false, ParserTaskScheduler.MANUAL_TASK_SCHEDULER);
        }

    }
}
