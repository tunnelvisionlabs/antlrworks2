/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.navigation;

import java.awt.Image;
import java.util.concurrent.Callable;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Sam Harwell
 */
public class ParseTreeNode extends AbstractNode implements OffsetProvider {

    @StaticResource
    private static final String RULE_IMAGE_PATH = "org/antlr/works/editor/antlr4/navigation/ui/rule.png";
    public static final Image RULE_IMAGE = ImageUtilities.loadImage(RULE_IMAGE_PATH);

    @StaticResource
    private static final String TERMINAL_IMAGE_PATH = "org/antlr/works/editor/antlr4/navigation/ui/terminal.png";
    public static final Image TERMINAL_IMAGE = ImageUtilities.loadImage(TERMINAL_IMAGE_PATH);

    @StaticResource
    private static final String ERROR_IMAGE_PATH = "org/antlr/works/editor/antlr4/navigation/ui/error.png";
    public static final Image ERROR_IMAGE = ImageUtilities.loadImage(ERROR_IMAGE_PATH);

    @NonNull
    private final ParseTree<?> _tree;

    public ParseTreeNode(@NonNull ParseTree<?> tree) {
        super(Children.LEAF);
        _tree = tree;

        if (tree.getChildCount() > 0) {
            setChildren(Children.createLazy(new ChildrenOfParseTreeNodeCreator()));
        }

        if (tree instanceof RuleNode) {
            RuleNode<?> ruleNode = (RuleNode<?>)tree;
            RuleContext<?> ruleContext = ruleNode.getRuleContext();
            if (ruleContext instanceof ParserRuleContext && ruleContext.getClass() != ParserRuleContext.class) {
                String contextName = ruleContext.getClass().getSimpleName();
                if (!"Context".equals(contextName) && contextName.endsWith("Context")) {
                    contextName = contextName.substring(0, contextName.length() - "Context".length());
                }

                contextName = Character.toLowerCase(contextName.charAt(0)) + contextName.substring(1);
                setDisplayName(contextName);
            } else {
                setDisplayName("Rule Node");
            }
        } else if (tree instanceof ErrorNode) {
            setDisplayName("Error Node");
        } else if (tree instanceof TerminalNode) {
            String nodeText = tree.getText();
            if (nodeText != null && !nodeText.isEmpty()) {
                nodeText = nodeText.substring(0, Math.min(nodeText.length(), 100));
                nodeText = nodeText.replace("\\", "\\\\");
                nodeText = nodeText.replace("\r", "\\r");
                nodeText = nodeText.replace("\n", "\\n");
                nodeText = nodeText.replace("\t", "\\t");
                nodeText = nodeText.replace("'", "\\'");
                setDisplayName("'" + nodeText + "'");
            } else {
                setDisplayName("Terminal Node");
            }
        }
    }

    @Override
    public Image getIcon(int type) {
        if (_tree instanceof RuleNode) {
            return RULE_IMAGE;
        } else if (_tree instanceof ErrorNode) {
            return ERROR_IMAGE;
        } else if (_tree instanceof TerminalNode) {
            return TERMINAL_IMAGE;
        }

        return super.getIcon(type);
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public OffsetRegion getSpan() {
        TerminalNode<?> startNode = ParseTrees.getStartNode(_tree);
        if (startNode == null) {
            return null;
        }

        TerminalNode<?> stopNode = ParseTrees.getStopNode(_tree);
        if (stopNode == null) {
            // rule matched epsilon
            return new OffsetRegion(startNode.getSourceInterval().a, 0);
        }

        return OffsetRegion.fromBounds(startNode.getSourceInterval().a, stopNode.getSourceInterval().b + 1);
    }

    @Override
    public OffsetRegion getSeek() {
        OffsetRegion span = getSpan();
        if (span == null) {
            return null;
        }

        return new OffsetRegion(span.getStart(), 0);
    }

    @Override
    public OffsetRegion getEmphasis() {
        return null;
    }

    protected ParseTreeNode createChildNode(ParseTree<?> tree) {
        return new ParseTreeNode(tree);
    }

    private final class ChildrenOfParseTreeNodeCreator implements Callable<Children> {

        @Override
        public Children call() throws Exception {
            Node[] childrenNodes = new Node[_tree.getChildCount()];
            for (int i = 0; i < childrenNodes.length; i++) {
                childrenNodes[i] = createChildNode(_tree.getChild(i));
            }

            return new NodeChildren(childrenNodes);
        }

    }

    private static final class NodeChildren extends Children.Keys<Node> {

        public NodeChildren(Node[] nodes) {
            setKeys(nodes);
        }

        @Override
        protected Node[] createNodes(Node key) {
            return new Node[] { key };
        }

    }

}
