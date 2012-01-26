/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.antlr.works.editor.grammar;

import java.util.List;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.experimental.CurrentRuleContextData;
import org.antlr.works.editor.grammar.parser.CompiledModel;
import org.antlr.works.editor.shared.completion.Anchor;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
public class GrammarParserDataDefinitions {
    public static final ParserDataDefinition<CompiledModel> COMPILED_MODEL = new CompiledModelDataDefinition();

    public static final ParserDataDefinition<List<Anchor>> REFERENCE_ANCHOR_POINTS = new ReferenceAnchorPointsDataDefinition();
    public static final ParserDataDefinition<ParserRuleContext<Token>> REFERENCE_PARSE_TREE = new ReferenceParseTreeDataDefinition();

    public static final ParserDataDefinition<List<Anchor>> DYNAMIC_ANCHOR_POINTS = new DynamicAnchorPointsDataDefinition();
    public static final ParserDataDefinition<Tagger<TokenTag<Token>>> LEXER_TOKENS = new LexerTokensDataDefinition();
    public static final ParserDataDefinition<CurrentRuleContextData> CURRENT_RULE_CONTEXT = new CurrentRuleContextDataDefinition();
    public static final ParserDataDefinition<FileModel> FILE_MODEL = new FileModelDataDefinition();

    public static final ParserDataDefinition<Description> NAVIGATOR_ROOT = new NavigatorRootDataDefinition();

    private GrammarParserDataDefinitions() {
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
    public static ParserDataDefinition<ParserRuleContext<Token>> getReferenceParseTreeDataDefinition() {
        return REFERENCE_PARSE_TREE;
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

    private static final class ReferenceParseTreeDataDefinition extends ParserDataDefinition<ParserRuleContext<Token>> {

        @SuppressWarnings("unchecked")
        public ReferenceParseTreeDataDefinition() {
            super("Grammar Reference Parse Tree", (Class<ParserRuleContext<Token>>)(Object)ParserRuleContext.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
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
            super("Grammar Current Rule Context", CurrentRuleContextData.class, true, false, ParserTaskScheduler.CURSOR_SENSITIVE_TASK_SCHEDULER);
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
}
