/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.navigation;

import java.awt.Image;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.navigation.NavigatorNode;
import org.antlr.netbeans.editor.navigation.NavigatorPanelUI;
import org.antlr.v4.tool.Grammar;
import org.openide.util.ImageUtilities;

public class GrammarNode extends NavigatorNode {
    public static Image PARSER_IMAGE;
    public static Image LEXER_IMAGE;

    static {
        PARSER_IMAGE = ImageUtilities.loadImage("org/antlr/works/editor/grammar/navigation/resources/parsericon.png");
        LEXER_IMAGE = ImageUtilities.loadImage("org/antlr/works/editor/grammar/navigation/resources/lexericon.png");
    }

    public GrammarNode(NavigatorPanelUI ui, Description description) {
        super(ui, description, GrammarNodeFactory.INSTANCE);
    }

    @Override
    public GrammarRulesPanelUI getUI() {
        return (GrammarRulesPanelUI)super.getUI();
    }

    @Override
    public void updateRecursively(Description newDescription) {
        super.updateRecursively(newDescription);

        if (newDescription != null && newDescription.getName() != null) {
            String currentRuleName = getUI().getCurrentRuleName();
            boolean currentNode = newDescription.getName().equals(currentRuleName);
            // TODO: highlight current node
        }
    }

    @Override
    public Image getIcon(int type) {
        String name = getDescription().getName();
        if (!getDescription().getChildren().isEmpty()) {
            name = getDescription().getChildren().iterator().next().getName();
        }

        if (name == null) {
            return super.getIcon(type);
        } else if (Grammar.isTokenName(name)) {
            return LEXER_IMAGE;
        } else {
            return PARSER_IMAGE;
        }
    }

    public static class GrammarNodeDescription extends Description {

        public GrammarNodeDescription() {
        }

        public GrammarNodeDescription(String name) {
            super(name);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof GrammarNodeDescription)) {
                return false;
            }

            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    protected static class GrammarNodeFactory implements Factory {
        public static final GrammarNodeFactory INSTANCE = new GrammarNodeFactory();

        @Override
        public NavigatorNode createNode(NavigatorPanelUI ui, Description key) {
            return new GrammarNode(ui, key);
        }

    }

}
