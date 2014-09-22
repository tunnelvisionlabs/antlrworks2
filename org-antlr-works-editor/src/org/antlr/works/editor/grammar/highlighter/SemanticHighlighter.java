/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.highlighter;

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
import org.antlr.v4.runtime.Dependents;
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
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ArgActionParameterContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.AtomContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.BlockContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ElementContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ElementOptionContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ElementOptionsContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.GrammarTypeContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.IdContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerAtomContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerCommandContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerCommandExprContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerCommandNameContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LocalsSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ModeSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.OptionContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.RuleReturnsContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.RuleSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.GrammarParserBaseListener;
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
public class SemanticHighlighter extends AbstractParseTreeSemanticHighlighter<SemanticHighlighter.SemanticAnalyzerListener, ParserRuleContext> {

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
    protected SemanticAnalyzerListener createListener(ParserData<? extends ParserRuleContext> parserData) {
        return new SemanticAnalyzerListener();
    }

    @Override
    protected ParseTree getParseTree(ParserData<? extends ParserRuleContext> parserData) {
        return parserData.getData();
    }

    @Override
    protected void updateHighlights(OffsetsBag targetContainer, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, SemanticAnalyzerListener listener) {
        List<Tuple2<OffsetRegion, AttributeSet>> intermediateContainer = new ArrayList<>();
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

        /**
         * These are known values for an {@link ElementOptionContext} appearing
         * on a terminal reference within a parser rule in the grammar.
         */
        private static final Set<String> KnownTerminalOptions = new HashSet<String>() {{
        }};

        /**
         * These are known values for an {@link ElementOptionContext} appearing
         * on a {@link SetElementContext}.
         */
        private static final Set<String> KnownSetElementOptions = new HashSet<String>() {{
        }};

        /**
         * These are known values for an {@link ElementOptionContext} appearing
         * on an {@link AtomContext}.
         */
        private static final Set<String> KnownAtomOptions = new HashSet<String>() {{
        }};

        /**
         * These are known values for an {@link ElementOptionContext} appearing
         * on a {@link LexerAtomCOntext}.
         */
        private static final Set<String> KnownLexerAtomOptions = new HashSet<String>() {{
        }};

        /**
         * These are known values for an {@link ElementOptionContext} appearing
         * on an alternative.
         */
        private static final Set<String> KnownAlternativeOptions = new HashSet<String>() {{
            add("assoc");
        }};

        /**
         * These are known values for an {@link ElementOptionContext} appearing
         * on an embedded action.
         */
        private static final Set<String> KnownEmbeddedActionOptions = new HashSet<String>() {{
        }};

        /**
         * These are known values for an {@link ElementOptionContext} appearing
         * on a semantic predicate.
         */
        private static final Set<String> KnownSemanticPredicateOptions = new HashSet<String>() {{
            add("fail");
        }};

        /**
         * These are known values for an {@link ElementOptionContext} appearing
         * on a rule reference (non-terminal in the parser).
         */
        private static final Set<String> KnownRuleReferenceOptions = new HashSet<String>() {{
            add("p");
        }};

        private final Deque<Integer> memberContext = new ArrayDeque<>();

        private final List<Token> parameterDeclarations = new ArrayList<>();
        private final List<Token> returnValueDeclarations = new ArrayList<>();
        private final List<Token> localsDeclarations = new ArrayList<>();
        private final List<Token> invalidOptions = new ArrayList<>();
        private final List<Token> lexerCommands = new ArrayList<>();
        private final List<Token> lexerModes = new ArrayList<>();

        private final Map<String, List<Token>> possibleLexerModes = new HashMap<>();
        private final HashSet<String> lexerModeNames = new HashSet<>();

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
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarType, version=0, dependents=Dependents.PARENTS)
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
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_argActionParameter, version=7, dependents={Dependents.PARENTS, Dependents.ANCESTORS}),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=7, dependents=Dependents.DESCENDANTS),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleReturns, version=7, dependents=Dependents.DESCENDANTS),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_localsSpec, version=7, dependents=Dependents.DESCENDANTS),
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
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=3, dependents=Dependents.PARENTS)
        public void enterRuleSpec(RuleSpecContext ctx) {
            memberContext.push(GrammarParser.RULE_ruleSpec);
            ruleLevel++;
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=3, dependents=Dependents.PARENTS)
        public void exitRuleSpec(RuleSpecContext ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_ruleSpec;
            ruleLevel--;
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleReturns, version=0, dependents=Dependents.PARENTS)
        public void enterRuleReturns(RuleReturnsContext ctx) {
            memberContext.push(GrammarParser.RULE_ruleReturns);
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleReturns, version=0, dependents=Dependents.PARENTS)
        public void exitRuleReturns(RuleReturnsContext ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_ruleReturns;
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_localsSpec, version=0, dependents=Dependents.PARENTS)
        public void enterLocalsSpec(LocalsSpecContext ctx) {
            memberContext.push(GrammarParser.RULE_localsSpec);
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_localsSpec, version=0, dependents=Dependents.PARENTS)
        public void exitLocalsSpec(LocalsSpecContext ctx) {
            int context = memberContext.pop();
            assert context == GrammarParser.RULE_localsSpec;
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_modeSpec, version=3, dependents=Dependents.PARENTS),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1, dependents=Dependents.DESCENDANTS),
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
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommand, version=1, dependents=Dependents.PARENTS),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommandName, version=1, dependents=Dependents.DESCENDANTS),
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
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommand, version=1, dependents=Dependents.PARENTS),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommandName, version=1, dependents=Dependents.DESCENDANTS),
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
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommandExpr, version=1, dependents=Dependents.PARENTS),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1, dependents=Dependents.DESCENDANTS),
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
                            list = new ArrayList<>(1);
                            possibleLexerModes.put(idContext.start.getText(), list);
                        }

                        list.add(idContext.start);
                    }
                }
            }
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_block, version=0, dependents=Dependents.PARENTS)
        public void enterBlock(BlockContext ctx) {
            blockLevel++;
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_block, version=0, dependents=Dependents.PARENTS)
        public void exitBlock(BlockContext ctx) {
            blockLevel--;
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_option, version=6, dependents=Dependents.ANCESTORS),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1, dependents=Dependents.DESCENDANTS),
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
            // The first two rules together form the dependency on constructs which an element option
            // can be defined on.
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_elementOption, version=3, dependents=Dependents.PARENTS),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_elementOptions, version=5, dependents=Dependents.PARENTS),
            // This is for the actual text of the option
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1, dependents=Dependents.DESCENDANTS),
            // These are for the specific contexts an element option applies to
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_terminal, version=0, dependents=Dependents.SELF),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_setElement, version=4, dependents=Dependents.SELF),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_atom, version=0, dependents=Dependents.SELF),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAtom, version=1, dependents=Dependents.SELF),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_alternative, version=5, dependents=Dependents.SELF),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleref, version=5, dependents=Dependents.SELF),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_element, version=5, dependents=Dependents.SELF),
        })
        public void enterElementOption(ElementOptionContext ctx) {
            IdContext id = ctx.id();
            if (id == null) {
                return;
            }

            ElementOptionsContext elementOptions = (ElementOptionsContext)ctx.parent;
            if (elementOptions == null || elementOptions.parent == null) {
                // only mark as invalid if we are sure
                // TODO: is this the desired behavior?
                return;
            }

            Set<String> knownOptions;
            switch (elementOptions.parent.getRuleIndex()) {
            case GrammarParser.RULE_terminal:
                knownOptions = KnownTerminalOptions;
                break;

            case GrammarParser.RULE_setElement:
                knownOptions = KnownSetElementOptions;
                break;

            case GrammarParser.RULE_atom:
                knownOptions = ((AtomContext)elementOptions.parent).DOT() != null ? KnownAtomOptions : null;
                break;

            case GrammarParser.RULE_lexerAtom:
                knownOptions = ((LexerAtomContext)elementOptions.parent).DOT() != null ? KnownLexerAtomOptions : null;
                break;

            case GrammarParser.RULE_alternative:
                knownOptions = KnownAlternativeOptions;
                break;

            case GrammarParser.RULE_ruleref:
                knownOptions = KnownRuleReferenceOptions;
                break;

            case GrammarParser.RULE_element:
                knownOptions = ((ElementContext)elementOptions.parent).QUESTION() != null ? KnownSemanticPredicateOptions : KnownEmbeddedActionOptions;
                break;

            default:
                knownOptions = null;
                break;
            }

            if (knownOptions == null) {
                return;
            }

            String option = id.start.getText();
            boolean valid = knownOptions.contains(option);
            if (!valid) {
                invalidOptions.add(id.start);
            }
        }
    }

}
