/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.syndiag;

import java.awt.Component;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Properties;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataEvent;
import org.antlr.netbeans.parsing.spi.ParserDataListener;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.tool.Grammar;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.codemodel.TokenData;
import org.antlr.works.editor.grammar.experimental.CurrentRuleContextData;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.AltListContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.AlternativeContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.AtomContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.EbnfSuffixContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerAltContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerAltListContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerAtomContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerBlockContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.RuleAltListContext;
import org.antlr.works.editor.grammar.experimental.generated.GrammarParserBaseListener;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.Parameters;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 *
 * @author Sam Harwell
 */
@ConvertAsProperties(dtd = "-//org.antlr.works.editor.grammar.syndiag//SyntaxDiagram//EN",
                     autostore = false)
@TopComponent.Description(preferredID = "SyntaxDiagramTopComponent",
                          //iconBase="SET/PATH/TO/ICON/HERE",
                          persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "bottomSlidingSide", openAtStartup = true)
@ActionID(category = "Window", id = "org.antlr.works.editor.grammar.syndiag.SyntaxDiagramTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_SyntaxDiagramAction",
                                     preferredID = "SyntaxDiagramTopComponent")
@NbBundle.Messages({
    "CTL_SyntaxDiagramAction=Syntax Diagram",
    "CTL_SyntaxDiagramTopComponent=Syntax",
    "HINT_SyntaxDiagramTopComponent=Grammar Rule Syntax Diagram"
})
public final class SyntaxDiagramTopComponent extends TopComponent {

    private WeakReference<DocumentSnapshot> snapshot;
    private WeakReference<GrammarParser.RuleSpecContext> context;
    private Diagram diagram;

    public SyntaxDiagramTopComponent() {
        initComponents();
        setName(Bundle.CTL_SyntaxDiagramTopComponent());
        setToolTipText(Bundle.HINT_SyntaxDiagramTopComponent());

        ParserTaskManager taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
        taskManager.addDataListener(GrammarParserDataDefinitions.CURRENT_RULE_CONTEXT, new CurrentRuleContextListener());
    }

    public static SyntaxDiagramTopComponent getInstance() {
        return (SyntaxDiagramTopComponent)WindowManager.getDefault().findTopComponent("SyntaxDiagramTopComponent");
    }

    @CheckForNull
    public DocumentSnapshot getSnapshot() {
        WeakReference<DocumentSnapshot> ref = snapshot;
        if (ref == null) {
            return null;
        }

        return ref.get();
    }

    @CheckForNull
    public GrammarParser.RuleSpecContext getContext() {
        WeakReference<GrammarParser.RuleSpecContext> ref = context;
        if (ref == null) {
            return null;
        }

        return ref.get();
    }

    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0, dependents=Dependents.SELF)
    public void setRuleContext(@NullAllowed CurrentRuleContextData context) {
        if (!SwingUtilities.isEventDispatchThread()) {
            throw new UnsupportedOperationException();
        }

        if (context == null) {
            clearDiagram();
            return;
        }

        DocumentSnapshot currentSnapshot = getSnapshot();
        GrammarParser.RuleSpecContext currentRuleSpecContext = getContext();

        GrammarParser.RuleSpecContext ruleSpecContext = context.getContext();
        if (isSameSnapshot(getSnapshot(), context.getSnapshot()) && isSameContext(getContext(), ruleSpecContext)) {
            return;
        }

        if (diagram != null) {
            this.jScrollPane1.setViewportView(null);
            diagram = null;
        }

        this.snapshot = new WeakReference<>(context.getSnapshot());
        this.context = new WeakReference<>(ruleSpecContext);
        if (ruleSpecContext != null) {
            try {
                SyntaxBuilderListener listener = new SyntaxBuilderListener(context.getGrammarType(), context.getSnapshot(), context.getFileModel());
                new ParseTreeWalker().walk(listener, ruleSpecContext);
                this.diagram = new Diagram(listener.getRule());
                this.jScrollPane1.setViewportView(diagram);
                this.jScrollPane1.validate();
                this.diagram.getRule().updatePositions();
            } catch (NullPointerException | IllegalArgumentException ex) {
                clearDiagram();
            }
        }
    }

    private void clearDiagram() {
        if (jScrollPane1 != null) {
            this.jScrollPane1.setViewportView(null);
        }

        this.diagram = null;
    }

    private static boolean isSameSnapshot(DocumentSnapshot a, DocumentSnapshot b) {
        if (a == b) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        return a.equals(b);
    }

    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0, dependents=Dependents.SELF)
    private static boolean isSameContext(GrammarParser.RuleSpecContext a, GrammarParser.RuleSpecContext b) {
        if (a == b) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        return isSameToken(a.start, b.start) && isSameToken(a.stop, b.stop);
    }

    private static boolean isSameToken(Token a, Token b) {
        if (a == b) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        return a.getStartIndex() == b.getStartIndex()
            && a.getStopIndex() == b.getStopIndex();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();

        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(Properties properties) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        properties.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(Properties properties) {
        String version = properties.getProperty("version");
        // TODO read your settings according to their version
    }

    private class CurrentRuleContextListener implements ParserDataListener<CurrentRuleContextData> {

        @Override
        public void dataChanged(ParserDataEvent<? extends CurrentRuleContextData> event) {
            ParserData<? extends CurrentRuleContextData> parserData = event.getData();
            final CurrentRuleContextData RuleSpecContext = parserData.getData();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    SyntaxDiagramTopComponent syntaxDiagram = SyntaxDiagramTopComponent.getInstance();
                    if (syntaxDiagram != null) {
                        syntaxDiagram.setRuleContext(RuleSpecContext);
                    }
                }
            });
        }

    }

    private static class SyntaxBuilderListener extends GrammarParserBaseListener {

        private final int grammarType;
        private final DocumentSnapshot snapshot;
        private final FileModel fileModel;
        private final Deque<JComponent> nodes = new ArrayDeque<>();

        private Rule RuleSpec;
        private ParserRuleContext outermostAtom;

        public SyntaxBuilderListener(int grammarType, DocumentSnapshot snapshot, FileModel fileModel) {
            Parameters.notNull("snapshot", snapshot);
            this.grammarType = grammarType;
            this.snapshot = snapshot;
            this.fileModel = fileModel;
        }

        public Rule getRule() {
            return RuleSpec;
        }

        /*
         * RuleSpec is the top level context
         */

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.PARENTS)
        public void enterParserRuleSpec(ParserRuleSpecContext ctx) {
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            Rule RuleSpec = new Rule(ctx.name.getText());
            this.RuleSpec = RuleSpec;
            nodes.push(RuleSpec);
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.PARENTS)
        public void exitParserRuleSpec(ParserRuleSpecContext ctx) {
            assert nodes.size() == 1;
            this.RuleSpec = (Rule)nodes.pop();
//            this.RuleSpec.setupElements();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0, dependents=Dependents.PARENTS)
        public void enterLexerRule(LexerRuleContext ctx) {
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            Rule RuleSpec = new Rule(ctx.name.getText());
            this.RuleSpec = RuleSpec;
            nodes.push(RuleSpec);
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0, dependents=Dependents.PARENTS)
        public void exitLexerRule(LexerRuleContext ctx) {
            assert nodes.size() == 1;
            this.RuleSpec = (Rule)nodes.pop();
//            this.RuleSpec.setupElements();
        }

        /*
         * RuleSpecAltList and altList form the true body of a Block
         */

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleAltList, version=0, dependents=Dependents.PARENTS)
        public void enterRuleAltList(RuleAltListContext ctx) {
            enterBlock();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_altList, version=0, dependents=Dependents.PARENTS)
        public void enterAltList(AltListContext ctx) {
            enterBlock();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAltList, version=1, dependents=Dependents.PARENTS)
        public void enterLexerAltList(LexerAltListContext ctx) {
            enterBlock();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerBlock, version=1, dependents=Dependents.PARENTS)
        public void enterLexerBlock(LexerBlockContext ctx) {
            enterBlock();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleAltList, version=0, dependents=Dependents.PARENTS)
        public void exitRuleAltList(RuleAltListContext ctx) {
            exitBlock();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_altList, version=0, dependents=Dependents.PARENTS)
        public void exitAltList(AltListContext ctx) {
            exitBlock();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAltList, version=1, dependents=Dependents.PARENTS)
        public void exitLexerAltList(LexerAltListContext ctx) {
            exitBlock();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerBlock, version=1, dependents=Dependents.PARENTS)
        public void exitLexerBlock(LexerBlockContext ctx) {
            exitBlock();
        }

        /*
         * alternative (parser) and lexerAlt (lexer) are fairly straightforward
         */

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_alternative, version=5, dependents=Dependents.PARENTS)
        public void enterAlternative(AlternativeContext ctx) {
            enterAlternative();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAlt, version=3, dependents=Dependents.PARENTS)
        public void enterLexerAlt(LexerAltContext ctx) {
            enterAlternative();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_alternative, version=5, dependents=Dependents.PARENTS)
        public void exitAlternative(AlternativeContext ctx) {
            exitAlternative();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAlt, version=3, dependents=Dependents.PARENTS)
        public void exitLexerAlt(LexerAltContext ctx) {
            exitAlternative();
        }

        /*
         * TODO: handle special actions and label sections similarly (was rewrites)
         */

        /*
         * Actual elements (atoms only for a test)
         */

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAtom, version=1, dependents=Dependents.PARENTS)
        public void enterLexerAtom(LexerAtomContext ctx) {
            enterEveryAtom(ctx);
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_atom, version=5, dependents=Dependents.PARENTS)
        public void enterAtom(AtomContext ctx) {
            enterEveryAtom(ctx);
        }

        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_terminal, version=1, dependents=Dependents.PARENTS),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleref, version=5, dependents=Dependents.PARENTS),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_range, version=4, dependents=Dependents.PARENTS),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_notSet, version=1, dependents=Dependents.PARENTS),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_blockSet, version=0, dependents=Dependents.PARENTS),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_setElement, version=4, dependents=Dependents.PARENTS),
        })
        public void enterEveryAtom(ParserRuleContext ctx) {
            if (outermostAtom != null) {
                return;
            }

            outermostAtom = ctx;
            SnapshotPositionRegion sourceSpan = null;
            if (ctx.stop != null) {
                sourceSpan = new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(ctx.start.getStartIndex(), ctx.stop.getStopIndex() + 1));
            } else if (ctx.start != null) {
                sourceSpan = new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(ctx.start.getStartIndex(), ctx.start.getStopIndex() + 1));
            }

            boolean wildcard = ctx.start.getType() == GrammarParser.DOT;
            boolean hasChild = ctx.children != null && !ctx.children.isEmpty();
            boolean reference = hasChild
                && (ctx.children.get(0) instanceof GrammarParser.TerminalContext
                || ctx.children.get(0) instanceof GrammarParser.RulerefContext);
            boolean range = hasChild && ctx.children.get(0) instanceof GrammarParser.RangeContext;
            boolean notset = hasChild && ctx.children.get(0) instanceof GrammarParser.NotSetContext;
            boolean charSet = hasChild
                && ctx.children.get(0) instanceof TerminalNode
                && ((TerminalNode)ctx.children.get(0)).getSymbol().getType() == GrammarParser.LEXER_CHAR_SET;

            if (wildcard || reference) {
                String text = ctx.start.getText();
                boolean nonTerminal = !(Grammar.isTokenName(text) || text.startsWith("'"));
                if (!nonTerminal && Grammar.isTokenName(text) && RuleSpec != null && Grammar.isTokenName(RuleSpec.getRuleName())) {
                    nonTerminal = true;
                }

                if (nonTerminal) {
                    nodes.peek().add(new NonTerminal(text, sourceSpan));
                } else {
                    if (Grammar.isTokenName(text)) {
                        String literal = null;
                        for (TokenData tokenData : fileModel.getVocabulary().getTokens()) {
                            if (tokenData.getLiteral() != null && text.equals(tokenData.getName())) {
                                if (literal != null) {
                                    // multiple matches
                                    literal = null;
                                    break;
                                }

                                literal = tokenData.getLiteral();
                            }
                        }

                        if (literal != null) {
                            text = literal;
                        }
                    }

                    nodes.peek().add(new Terminal(text, sourceSpan));
                }
            } else if (range) {
                String label = "???";
                Terminal terminal = null;
                GrammarParser.RangeContext rangeContext = (GrammarParser.RangeContext)ctx.children.get(0);
                if (rangeContext.children != null && rangeContext.children.size() == 3) {
                    Token start = ((TerminalNode)rangeContext.children.get(0)).getSymbol();
                    Token end = ((TerminalNode)rangeContext.children.get(2)).getSymbol();
                    if (start != null && end != null) {
                        terminal = new RangeTerminal(start.getText(), end.getText(), sourceSpan);
                    }
                }

                if (terminal == null) {
                    terminal = new Terminal(label, sourceSpan);
                }

                nodes.peek().add(terminal);
            } else if (notset) {
                GrammarParser.NotSetContext notSetContext = (GrammarParser.NotSetContext)ctx.children.get(0);

                List<GrammarParser.SetElementContext> elementContexts;
                if (notSetContext.setElement() != null) {
                    elementContexts = Collections.singletonList(notSetContext.setElement());
                } else {
                    elementContexts = new ArrayList<>(notSetContext.blockSet().setElement());
                }

                nodes.peek().add(new SetTerminal(elementContexts, sourceSpan, true));
            } else if (charSet) {
                nodes.peek().add(new SetTerminal((TerminalNode)ctx.children.get(0), sourceSpan));
            } else {
                nodes.peek().add(new Terminal("???", sourceSpan));
            }
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAtom, version=1, dependents=Dependents.PARENTS)
        public void exitLexerAtom(LexerAtomContext ctx) {
            if (outermostAtom == ctx) {
                outermostAtom = null;
            }
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_atom, version=5, dependents=Dependents.PARENTS)
        public void exitAtom(AtomContext ctx) {
            if (outermostAtom == ctx) {
                outermostAtom = null;
            }
        }

        /*
         * suffix
         */

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ebnfSuffix, version=5, dependents=Dependents.ANCESTORS)
        public void enterEbnfSuffix(EbnfSuffixContext ctx) {
            Block block;

            boolean greedy = ctx.getChildCount() <= 1;

            switch (ctx.start.getType()) {
            case GrammarParser.QUESTION:
                {
                    block = new Block();
                    JComponent last = nodes.peek();
                    Component lastChild = last.getComponent(last.getComponentCount() - 1);
                    last.remove(last.getComponentCount() - 1);
                    Alt alt = new Alt();
                    alt.add(lastChild);

                    if (greedy) {
                        block.add(alt);
                        block.add(new Alt());
                    } else {
                        block.add(new Alt());
                        block.add(alt);
                    }

                    last.add(block);
                    break;
                }

            case GrammarParser.STAR:
            case GrammarParser.PLUS:
                {
                    block = new PlusBlock(greedy);
                    JComponent last = nodes.peek();
                    Component lastChild = last.getComponent(last.getComponentCount() - 1);
                    last.remove(last.getComponentCount() - 1);
                    Alt alt = new Alt();
                    alt.add(lastChild);
                    block.add(alt);
                    if (ctx.start.getType() == GrammarParser.STAR) {
                        Block optionalBlock = new Block();
                        alt = new Alt();
                        alt.add(block);

                        if (greedy) {
                            optionalBlock.add(alt);
                            optionalBlock.add(new Alt());
                        } else {
                            optionalBlock.add(new Alt());
                            optionalBlock.add(alt);
                        }

                        block = optionalBlock;
                    }

                    last.add(block);
                    break;
                }

            default:
                break;
            }
        }

        /*
         * helper methods
         */

        private void enterBlock() {
            if (outermostAtom != null) {
                return;
            }

            Block block = new Block();
            nodes.push(block);
        }

        private void exitBlock() {
            if (outermostAtom != null) {
                return;
            }

            Component block = nodes.pop();
            nodes.peek().add(block);
        }

        private void enterAlternative() {
            if (outermostAtom != null) {
                return;
            }

            Alt alternative = new Alt();
            nodes.push(alternative);
        }

        private void exitAlternative() {
            if (outermostAtom != null) {
                return;
            }

            Component alternative = nodes.pop();
            nodes.peek().add(alternative);
        }

    }

}
