/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.works.editor.antlr4.semantics.AbstractParseTreeSemanticHighlighter;
import org.antlr.works.editor.antlr4.semantics.AbstractSemanticHighlighter;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.experimental.GrammarParserBaseListener;
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
public class SemanticHighlighter extends AbstractParseTreeSemanticHighlighter<SemanticHighlighter.SemanticAnalyzerListener, ParserRuleContext<Token>> {

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
    protected ParseTree getParseTree(ParserData<? extends ParserRuleContext<Token>> parserData) {
        return parserData.getData();
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

    public static class SemanticAnalyzerListener extends GrammarParserBaseListener {
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
        public void argActionParameterEnter(argActionParameterContext ctx) {
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
        public void ruleEnter(ruleContext ctx) {
            memberContext.push(GrammarParser.RULE_rule);
        }

        @Override
        public void ruleExit(ruleContext ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_rule;
        }

        @Override
        public void ruleReturnsEnter(ruleReturnsContext ctx) {
            memberContext.push(GrammarParser.RULE_ruleReturns);
        }

        @Override
        public void ruleReturnsExit(ruleReturnsContext ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_ruleReturns;
        }

        @Override
        public void localsSpecEnter(localsSpecContext ctx) {
            memberContext.push(GrammarParser.RULE_localsSpec);
        }

        @Override
        public void localsSpecExit(localsSpecContext ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_localsSpec;
        }
    }

}
