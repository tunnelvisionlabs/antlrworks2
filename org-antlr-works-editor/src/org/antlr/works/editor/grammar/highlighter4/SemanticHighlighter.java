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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Tuple2;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.works.editor.antlr4.semantics.AbstractParseTreeSemanticHighlighter;
import org.antlr.works.editor.antlr4.semantics.AbstractSemanticHighlighter;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ArgActionParameterContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.BlockContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ElementOptionContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.GrammarTypeContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.IdContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerCommandContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerCommandExprContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerCommandNameContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LocalsSpecContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ModeSpecContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.OptionContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RuleReturnsContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RuleSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParserBaseListener;
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
    private final AttributeSet invalidOptionAttributes;
    private final AttributeSet lexerCommandAttributes;
    private final AttributeSet lexerModeAttributes;

    private SemanticHighlighter(@NonNull StyledDocument document) {
        super(document, GrammarParserDataDefinitions.REFERENCE_PARSE_TREE);

        Lookup lookup = MimeLookup.getLookup(MimePath.parse(GrammarEditorKit.GRAMMAR_MIME_TYPE));
        FontColorSettings settings = lookup.lookup(FontColorSettings.class);
        this.parameterDeclarationAttributes = getFontAndColors(settings, "definition");
        this.returnValueDeclarationAttributes = getFontAndColors(settings, "definition");
        this.localDeclarationAttributes = getFontAndColors(settings, "definition");
        this.invalidOptionAttributes = getFontAndColors(settings, "invalidoption");
        this.lexerCommandAttributes = getFontAndColors(settings, "lexerCommand");
        this.lexerModeAttributes = getFontAndColors(settings, "lexerMode");
    }

    @Override
    protected SemanticAnalyzerListener createListener(ParserData<? extends ParserRuleContext<Token>> parserData) {
        return new SemanticAnalyzerListener();
    }

    @Override
    protected ParseTree<Token> getParseTree(ParserData<? extends ParserRuleContext<Token>> parserData) {
        return parserData.getData();
    }

    @Override
    protected void updateHighlights(OffsetsBag targetContainer, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, SemanticAnalyzerListener listener) {
        List<Tuple2<OffsetRegion, AttributeSet>> intermediateContainer = new ArrayList<Tuple2<OffsetRegion, AttributeSet>>();
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getParameterDeclarations(), parameterDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getReturnValueDeclarations(), returnValueDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getLocalsDeclarations(), localDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getInvalidOptions(), invalidOptionAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getLexerCommands(), lexerCommandAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getLexerModes(), lexerModeAttributes);

        OffsetsBag container = new OffsetsBag(currentSnapshot.getVersionedDocument().getDocument());
        fillHighlights(container, intermediateContainer);
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
        private static final Set<String> knownBlockOptions = new HashSet<String>() {{
            add("greedy");
            add("simrecursion_");
        }};
        private static final Set<String> knownRuleOptions = new HashSet<String>() {{
            add("context");
            add("simrecursion_");
        }};
        private static final Set<String> knownLexerGrammarOptions = new HashSet<String>() {{
            add("language");
            add("tokenVocab");
            add("TokenLabelType");
            add("superClass");
            add("filter");
            add("abstract");
        }};
        private static final Set<String> knownParserGrammarOptions = new HashSet<String>() {{
            add("tokenVocab");
            add("TokenLabelType");
            add("superClass");
            add("abstract");
        }};
        private static final Set<String> knownCombinedGrammarOptions = new HashSet<String>() {{
            add("language");
            add("tokenVocab");
            add("TokenLabelType");
            add("superClass");
            add("filter");
            add("abstract");
        }};
        private static final Set<String> knownTokenOptions = new HashSet<String>() {{
            add("assoc");
        }};
        private static final Set<String> knownSemPredOptions = new HashSet<String>() {{
            add("fail");
        }};

        private final Deque<Integer> memberContext = new ArrayDeque<Integer>();

        private final List<Token> parameterDeclarations = new ArrayList<Token>();
        private final List<Token> returnValueDeclarations = new ArrayList<Token>();
        private final List<Token> localsDeclarations = new ArrayList<Token>();
        private final List<Token> invalidOptions = new ArrayList<Token>();
        private final List<Token> lexerCommands = new ArrayList<Token>();
        private final List<Token> lexerModes = new ArrayList<Token>();

        private final Map<String, List<Token>> possibleLexerModes = new HashMap<String, List<Token>>();
        private final HashSet<String> lexerModeNames = new HashSet<String>();

        private int grammarType;
        private int ruleLevel;
        private int blockLevel;

        private int inNamedModeCommand;

        public List<Token> getParameterDeclarations() {
            return parameterDeclarations;
        }

        public List<Token> getReturnValueDeclarations() {
            return returnValueDeclarations;
        }

        public List<Token> getLocalsDeclarations() {
            return localsDeclarations;
        }

        public List<Token> getInvalidOptions() {
            return invalidOptions;
        }

        public List<Token> getLexerCommands() {
            return lexerCommands;
        }

        public List<Token> getLexerModes() {
            return lexerModes;
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarType, version=0)
        public void enterGrammarType(GrammarTypeContext ctx) {
            if (ctx.LEXER() != null) {
                grammarType = GrammarParser.LEXER;
            } else if (ctx.PARSER() != null) {
                grammarType = GrammarParser.PARSER;
            } else {
                grammarType = GrammarParser.COMBINED;
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameter, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleReturns, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_localsSpec, version=0),
        })
        public void enterArgActionParameter(ArgActionParameterContext ctx) {
            if (ctx.name != null) {
                int context = memberContext.isEmpty() ? GrammarParser.RULE_ruleSpec : memberContext.peek();
                switch (context) {
                case GrammarParser.RULE_ruleReturns:
                    returnValueDeclarations.add(ctx.name);
                    break;

                case GrammarParser.RULE_localsSpec:
                    localsDeclarations.add(ctx.name);
                    break;

                case GrammarParser.RULE_ruleSpec:
                default:
                    parameterDeclarations.add(ctx.name);
                    break;
                }
            }
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0)
        public void enterRuleSpec(RuleSpecContext ctx) {
            memberContext.push(GrammarParser.RULE_ruleSpec);
            ruleLevel++;
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0)
        public void exitRuleSpec(RuleSpecContext ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_ruleSpec;
            ruleLevel--;
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleReturns, version=0)
        public void enterRuleReturns(RuleReturnsContext ctx) {
            memberContext.push(GrammarParser.RULE_ruleReturns);
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleReturns, version=0)
        public void exitRuleReturns(RuleReturnsContext ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_ruleReturns;
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_localsSpec, version=0)
        public void enterLocalsSpec(LocalsSpecContext ctx) {
            memberContext.push(GrammarParser.RULE_localsSpec);
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_localsSpec, version=0)
        public void exitLocalsSpec(LocalsSpecContext ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_localsSpec;
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_modeSpec, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1),
        })
        public void enterModeSpec(ModeSpecContext ctx) {
            IdContext idContext = ctx.id();
            if (idContext == null || idContext.start == null) {
                return;
            }

            if (idContext.start != null) {
                lexerModes.add(idContext.start);
                lexerModeNames.add(idContext.start.getText());

                List<Token> references = possibleLexerModes.remove(idContext.start.getText());
                if (references != null) {
                    lexerModes.addAll(references);
                }
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommand, version=1),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommandName, version=0),
        })
        public void enterLexerCommand(LexerCommandContext ctx) {
            LexerCommandNameContext lexerCommandNameContext = ctx.lexerCommandName();
            if (lexerCommandNameContext == null || lexerCommandNameContext.start == null) {
                return;
            }

            if (lexerCommandNameContext.start != null) {
                lexerCommands.add(lexerCommandNameContext.start);

                if ("pushMode".equals(lexerCommandNameContext.start.getText()) || "mode".equals(lexerCommandNameContext.start.getText())) {
                    inNamedModeCommand++;
                }
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommand, version=1),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommandName, version=0),
        })
        public void exitLexerCommand(LexerCommandContext ctx) {
            LexerCommandNameContext lexerCommandNameContext = ctx.lexerCommandName();
            if (lexerCommandNameContext != null && lexerCommandNameContext.start != null) {
                if ("pushMode".equals(lexerCommandNameContext.start.getText()) || "mode".equals(lexerCommandNameContext.start.getText())) {
                    inNamedModeCommand--;
                }
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommandExpr, version=1),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1),
        })
        public void enterLexerCommandExpr(LexerCommandExprContext ctx) {
            if (inNamedModeCommand > 0) {
                IdContext idContext = ctx.id();
                if (idContext != null && idContext.start != null) {
                    if (lexerModeNames.contains(idContext.start.getText())) {
                        lexerModes.add(idContext.start);
                    } else {
                        List<Token> list = possibleLexerModes.get(idContext.start.getText());
                        if (list == null) {
                            list = new ArrayList<Token>(1);
                            possibleLexerModes.put(idContext.start.getText(), list);
                        }

                        list.add(idContext.start);
                    }
                }
            }
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_block, version=0)
        public void enterBlock(BlockContext ctx) {
            blockLevel++;
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_block, version=0)
        public void exitBlock(BlockContext ctx) {
            blockLevel--;
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_option, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1),
        })
        public void enterOption(OptionContext ctx) {
            IdContext id = ctx.id();
            if (id != null) {
                String option = id.start.getText();
                boolean valid;
                if (blockLevel > 0) {
                    valid = knownBlockOptions.contains(option);
                } else if (ruleLevel > 0) {
                    valid = knownRuleOptions.contains(option);
                } else {
                    switch (grammarType) {
                    case GrammarParser.LEXER:
                        valid = knownLexerGrammarOptions.contains(option);
                        break;
                    case GrammarParser.PARSER:
                        valid = knownParserGrammarOptions.contains(option);
                        break;
                    case GrammarParser.COMBINED:
                    default:
                        valid = knownCombinedGrammarOptions.contains(option);
                        break;
                    }
                }

                if (!valid) {
                    invalidOptions.add(id.start);
                }
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_elementOption, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1),
        })
        public void enterElementOption(ElementOptionContext ctx) {
            IdContext id = ctx.id();
            if (id != null) {
                String option = id.start.getText();
                boolean valid = knownTokenOptions.contains(option);
                if (!valid) {
                    invalidOptions.add(id.start);
                }
            }
        }
    }

}
