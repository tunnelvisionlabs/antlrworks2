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
package org.antlr.works.editor.grammar.highlighter4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.antlr4.semantics.AbstractParseTreeSemanticHighlighter;
import org.antlr.works.editor.antlr4.semantics.AbstractSemanticHighlighter;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.experimental.BlankGrammarParserListener;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParser.argActionParameterContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.localsSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleReturnsContext;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;
import org.openide.util.Lookup;

/**
 *
 * @author Sam Harwell
 */
public class SemanticHighlighter extends AbstractParseTreeSemanticHighlighter<SemanticHighlighter.SemanticAnalyzerListener> {

    private final AttributeSet parameterDeclarationAttributes;
    private final AttributeSet returnValueDeclarationAttributes;
    private final AttributeSet localDeclarationAttributes;

    private SemanticHighlighter(@NonNull StyledDocument document) {
        super(document, GrammarParserDataDefinitions.REFERENCE_PARSE_TREE);

        Lookup lookup = MimeLookup.getLookup(MimePath.parse(GrammarEditorKit.GRAMMAR_MIME_TYPE));
        FontColorSettings settings = lookup.lookup(FontColorSettings.class);
        this.parameterDeclarationAttributes = getFontAndColors(settings, "definition");
        this.returnValueDeclarationAttributes = getFontAndColors(settings, "definition");
        this.localDeclarationAttributes = getFontAndColors(settings, "definition");
    }

    @Override
    protected SemanticAnalyzerListener createListener(ParserData<? extends ParserRuleContext<Token>> parserData) {
        return new SemanticAnalyzerListener();
    }

    @Override
    protected void updateHighlights(OffsetsBag targetContainer, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, SemanticAnalyzerListener listener) {
        OffsetsBag container = new OffsetsBag(currentSnapshot.getVersionedDocument().getDocument());
        addHighlights(container, sourceSnapshot, currentSnapshot, listener.getParameterDeclarations(), parameterDeclarationAttributes);
        addHighlights(container, sourceSnapshot, currentSnapshot, listener.getReturnValueDeclarations(), returnValueDeclarationAttributes);
        addHighlights(container, sourceSnapshot, currentSnapshot, listener.getLocalsDeclarations(), localDeclarationAttributes);
        targetContainer.setHighlights(container);
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=HighlightsLayerFactory.class)
    public static class LayerFactory extends AbstractLayerFactory {

        public LayerFactory() {
            super(SemanticHighlighter.class);
        }

        @Override
        protected AbstractSemanticHighlighter<?> createHighlighter(Context context) {
            return new SemanticHighlighter((StyledDocument)context.getDocument());
        }

    }

    public static class SemanticAnalyzerListener extends BlankGrammarParserListener {
        private final Deque<Integer> memberContext = new ArrayDeque<Integer>();

        private final List<Token> parameterDeclarations = new ArrayList<Token>();
        private final List<Token> returnValueDeclarations = new ArrayList<Token>();
        private final List<Token> localsDeclarations = new ArrayList<Token>();

        public List<Token> getParameterDeclarations() {
            return parameterDeclarations;
        }

        public List<Token> getReturnValueDeclarations() {
            return returnValueDeclarations;
        }

        public List<Token> getLocalsDeclarations() {
            return localsDeclarations;
        }

        @Override
        public void enterRule(argActionParameterContext ctx) {
            if (ctx.name != null) {
                int context = memberContext.isEmpty() ? GrammarParser.RULE_rule : memberContext.peek();
                switch (context) {
                case GrammarParser.RULE_ruleReturns:
                    returnValueDeclarations.add(ctx.name);
                    break;

                case GrammarParser.RULE_localsSpec:
                    localsDeclarations.add(ctx.name);
                    break;

                case GrammarParser.RULE_rule:
                default:
                    parameterDeclarations.add(ctx.name);
                    break;
                }
            }
        }

        @Override
        public void enterRule(ruleContext ctx) {
            memberContext.push(GrammarParser.RULE_rule);
        }

        @Override
        public void exitRule(ruleContext ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_rule;
        }

        @Override
        public void enterRule(ruleReturnsContext ctx) {
            memberContext.push(GrammarParser.RULE_ruleReturns);
        }

        @Override
        public void exitRule(ruleReturnsContext ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_ruleReturns;
        }

        @Override
        public void enterRule(localsSpecContext ctx) {
            memberContext.push(GrammarParser.RULE_localsSpec);
        }

        @Override
        public void exitRule(localsSpecContext ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_localsSpec;
        }
    }

}
