/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Terence Parr
 *  Copyright (c) 2011 Sam Harwell
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
import org.antlr.v4.runtime.tree.ParseTree.TerminalNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.experimental.BlankGrammarParserListener;
import org.antlr.works.editor.grammar.experimental.CurrentRuleContextData;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParser.altListContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.alternativeContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.atomContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ebnfSuffixContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.rewriteContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleAltListContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleContext;
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

    public void setRuleContext(DocumentSnapshot snapshot, int grammarType, GrammarParser.ruleContext context) {
        if (!SwingUtilities.isEventDispatchThread()) {
            throw new UnsupportedOperationException();
        }

        if (isSameSnapshot(this.snapshot, snapshot) && isSameContext(this.context, context)) {
            return;
        }

        if (diagram != null) {
            this.jScrollPane1.setViewportView(null);
            diagram = null;
        }

        this.snapshot = snapshot;
        this.context = context;
        if (context != null) {
            try {
                SyntaxBuilderListener listener = new SyntaxBuilderListener(grammarType, snapshot);
                new ParseTreeWalker().walk(listener, context);
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
                        syntaxDiagram.setRuleContext(ruleContext.getSnapshot(), ruleContext.getGrammarType(), ruleContext.getContext());
                    }
                }
            });
        }

    }

    private static class SyntaxBuilderListener extends BlankGrammarParserListener {

        private final int grammarType;
        private final DocumentSnapshot snapshot;
        private final Deque<JComponent> nodes = new ArrayDeque<JComponent>();

        private Rule rule;
        private boolean inRewrite;
        private atomContext outermostAtom;

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
        public void enterRule(ruleContext ctx) {
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            Rule rule = new Rule(ctx.name.start.getText());
            this.rule = rule;
            nodes.push(rule);
        }

        @Override
        public void exitRule(ruleContext ctx) {
            assert nodes.size() == 1;
            this.rule = (Rule)nodes.pop();
//            this.rule.setupElements();
        }

        /*
         * ruleAltList and altList form the true body of a Block
         */

        @Override
        public void enterRule(ruleAltListContext ctx) {
            enterBlock();
        }

        @Override
        public void enterRule(altListContext ctx) {
            enterBlock();
        }

        @Override
        public void exitRule(ruleAltListContext ctx) {
            exitBlock();
        }

        @Override
        public void exitRule(altListContext ctx) {
            exitBlock();
        }

        /*
         * alternative is fairly straightforward
         */

        @Override
        public void enterRule(alternativeContext ctx) {
            enterAlternative();
        }

        @Override
        public void exitRule(alternativeContext ctx) {
            exitAlternative();
        }

        /*
         * Don't want the body of the rewrite(s) in the syntax diagram
         */

        @Override
        public void enterRule(rewriteContext ctx) {
            assert !inRewrite;
            inRewrite = true;
        }

        @Override
        public void exitRule(rewriteContext ctx) {
            assert inRewrite;
            inRewrite = false;
        }

        /*
         * Actual elements (atoms only for a test)
         */

        @Override
        public void enterRule(atomContext ctx) {
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
                GrammarParser.rangeContext rangeContext = (GrammarParser.rangeContext)ctx.children.get(0);
                if (rangeContext.children != null && rangeContext.children.size() == 3) {
                    Token start = (Token)((ParseTree.TerminalNode<?>)rangeContext.children.get(0)).getSymbol();
                    Token end = (Token)((ParseTree.TerminalNode<?>)rangeContext.children.get(2)).getSymbol();
                    if (start != null && end != null) {
                        label = String.format("%s..%s", start.getText(), end.getText());
                    }
                }

                nodes.peek().add(new Terminal(label, sourceSpan));
            } else if (notset) {
                nodes.peek().add(new Terminal("~(...)", sourceSpan));
            } else {
                nodes.peek().add(new Terminal("???", sourceSpan));
            }
        }

        @Override
        public void exitRule(atomContext ctx) {
            if (outermostAtom == ctx) {
                outermostAtom = null;
            }
        }

        /*
         * suffix
         */

        @Override
        public void enterRule(ebnfSuffixContext ctx) {
            if (inRewrite) {
                return;
            }

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
