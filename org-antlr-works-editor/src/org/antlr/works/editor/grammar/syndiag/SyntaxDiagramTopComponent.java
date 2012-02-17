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
import java.util.ArrayDeque;
import java.util.Deque;
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
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.experimental.CurrentRuleContextData;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParser.altListContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.alternativeContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.atomContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ebnfSuffixContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerAltContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerAltListContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerAtomContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerBlockContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.parserRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleAltListContext;
import org.antlr.works.editor.grammar.experimental.GrammarParserBaseListener;
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
    "CTL_SyntaxDiagramTopComponent=Syntax Diagram",
    "HINT_SyntaxDiagramTopComponent=Grammar Rule Syntax Diagram"
})
public final class SyntaxDiagramTopComponent extends TopComponent {

    private DocumentSnapshot snapshot;
    private GrammarParser.ruleContext context;
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

    public void setRuleContext(@NullAllowed CurrentRuleContextData context) {
        if (!SwingUtilities.isEventDispatchThread()) {
            throw new UnsupportedOperationException();
        }

        if (context == null) {
            clearDiagram();
            return;
        }

        GrammarParser.ruleContext ruleContext = context.getContext();
        if (isSameSnapshot(this.snapshot, snapshot) && isSameContext(this.context, ruleContext)) {
            return;
        }

        if (diagram != null) {
            this.jScrollPane1.setViewportView(null);
            diagram = null;
        }

        this.snapshot = context.getSnapshot();
        this.context = context.getContext();
        if (context != null) {
            try {
                SyntaxBuilderListener listener = new SyntaxBuilderListener(context.getGrammarType(), snapshot);
                new ParseTreeWalker().walk(listener, context.getContext());
                this.diagram = new Diagram(listener.getRule());
                this.jScrollPane1.setViewportView(diagram);
                this.jScrollPane1.validate();
                this.diagram.getRule().updatePositions();
            } catch (NullPointerException ex) {
                clearDiagram();
            } catch (IllegalArgumentException ex) {
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

    private static boolean isSameContext(GrammarParser.ruleContext a, GrammarParser.ruleContext b) {
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
            final CurrentRuleContextData ruleContext = parserData.getData();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    SyntaxDiagramTopComponent syntaxDiagram = SyntaxDiagramTopComponent.getInstance();
                    if (syntaxDiagram != null) {
                        syntaxDiagram.setRuleContext(ruleContext);
                    }
                }
            });
        }

    }

    private static class SyntaxBuilderListener extends GrammarParserBaseListener {

        private final int grammarType;
        private final DocumentSnapshot snapshot;
        private final Deque<JComponent> nodes = new ArrayDeque<JComponent>();

        private Rule rule;
        private ParserRuleContext<Token> outermostAtom;

        public SyntaxBuilderListener(int grammarType, DocumentSnapshot snapshot) {
            Parameters.notNull("snapshot", snapshot);
            this.grammarType = grammarType;
            this.snapshot = snapshot;
        }

        public Rule getRule() {
            return rule;
        }

        /*
         * rule is the top level context
         */

        @Override
        public void parserRuleEnter(parserRuleContext ctx) {
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            Rule rule = new Rule(ctx.name.getText());
            this.rule = rule;
            nodes.push(rule);
        }

        @Override
        public void parserRuleExit(parserRuleContext ctx) {
            assert nodes.size() == 1;
            this.rule = (Rule)nodes.pop();
//            this.rule.setupElements();
        }

        @Override
        public void lexerRuleEnter(lexerRuleContext ctx) {
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            Rule rule = new Rule(ctx.name.getText());
            this.rule = rule;
            nodes.push(rule);
        }

        @Override
        public void lexerRuleExit(lexerRuleContext ctx) {
            assert nodes.size() == 1;
            this.rule = (Rule)nodes.pop();
//            this.rule.setupElements();
        }

        /*
         * ruleAltList and altList form the true body of a Block
         */

        @Override
        public void ruleAltListEnter(ruleAltListContext ctx) {
            enterBlock();
        }

        @Override
        public void altListEnter(altListContext ctx) {
            enterBlock();
        }

        @Override
        public void lexerAltListEnter(lexerAltListContext ctx) {
            enterBlock();
        }

        @Override
        public void lexerBlockEnter(lexerBlockContext ctx) {
            enterBlock();
        }

        @Override
        public void ruleAltListExit(ruleAltListContext ctx) {
            exitBlock();
        }

        @Override
        public void altListExit(altListContext ctx) {
            exitBlock();
        }

        @Override
        public void lexerAltListExit(lexerAltListContext ctx) {
            exitBlock();
        }

        @Override
        public void lexerBlockExit(lexerBlockContext ctx) {
            exitBlock();
        }

        /*
         * alternative (parser) and lexerAlt (lexer) are fairly straightforward
         */

        @Override
        public void alternativeEnter(alternativeContext ctx) {
            enterAlternative();
        }

        @Override
        public void lexerAltEnter(lexerAltContext ctx) {
            enterAlternative();
        }

        @Override
        public void alternativeExit(alternativeContext ctx) {
            exitAlternative();
        }

        @Override
        public void lexerAltExit(lexerAltContext ctx) {
            exitAlternative();
        }

        /*
         * TODO: handle special actions and label sections similarly (was rewrites)
         */

        /*
         * Actual elements (atoms only for a test)
         */

        @Override
        public void lexerAtomEnter(lexerAtomContext ctx) {
            enterAtom(ctx);
        }

        @Override
        public void atomEnter(atomContext ctx) {
            enterAtom(ctx);
        }

        public void enterAtom(ParserRuleContext<Token> ctx) {
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
                && (ctx.children.get(0) instanceof GrammarParser.terminalContext
                || ctx.children.get(0) instanceof GrammarParser.rulerefContext);
            boolean range = hasChild && ctx.children.get(0) instanceof GrammarParser.rangeContext;
            boolean notset = hasChild && ctx.children.get(0) instanceof GrammarParser.notSetContext;

            if (wildcard || reference) {
                String text = ctx.start.getText();
                boolean nonTerminal = Character.isLowerCase(text.charAt(0));
                if (!nonTerminal && Character.isUpperCase(text.charAt(0)) && rule != null && Character.isUpperCase(rule.getRuleName().charAt(0)))
                    nonTerminal = true;

                if (nonTerminal) {
                    nodes.peek().add(new NonTerminal(text, sourceSpan));
                } else {
                    nodes.peek().add(new Terminal(text, sourceSpan));
                }
            } else if (range) {
                String label = "???";
                Terminal terminal = null;
                GrammarParser.rangeContext rangeContext = (GrammarParser.rangeContext)ctx.children.get(0);
                if (rangeContext.children != null && rangeContext.children.size() == 3) {
                    Token start = (Token)((ParseTree.TerminalNode<?>)rangeContext.children.get(0)).getSymbol();
                    Token end = (Token)((ParseTree.TerminalNode<?>)rangeContext.children.get(2)).getSymbol();
                    if (start != null && end != null) {
                        terminal = new RangeTerminal(start.getText(), end.getText(), sourceSpan);
                    }
                }

                if (terminal == null) {
                    terminal = new Terminal(label, sourceSpan);
                }

                nodes.peek().add(terminal);
            } else if (notset) {
                nodes.peek().add(new Terminal("~(...)", sourceSpan));
            } else {
                nodes.peek().add(new Terminal("???", sourceSpan));
            }
        }

        @Override
        public void lexerAtomExit(lexerAtomContext ctx) {
            if (outermostAtom == ctx) {
                outermostAtom = null;
            }
        }

        @Override
        public void atomExit(atomContext ctx) {
            if (outermostAtom == ctx) {
                outermostAtom = null;
            }
        }

        /*
         * suffix
         */

        @Override
        public void ebnfSuffixEnter(ebnfSuffixContext ctx) {
            Block block;

            switch (ctx.start.getType()) {
            case GrammarParser.QUESTION:
                {
                    block = new Block();
                    JComponent last = nodes.peek();
                    Component lastChild = last.getComponent(last.getComponentCount() - 1);
                    last.remove(last.getComponentCount() - 1);
                    Alt alt = new Alt();
                    alt.add(lastChild);
                    block.add(alt);
                    block.add(new Alt());
                    last.add(block);
                    break;
                }

            case GrammarParser.STAR:
            case GrammarParser.PLUS:
                {
                    block = new PlusBlock();
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
                        optionalBlock.add(alt);
                        optionalBlock.add(new Alt());
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
