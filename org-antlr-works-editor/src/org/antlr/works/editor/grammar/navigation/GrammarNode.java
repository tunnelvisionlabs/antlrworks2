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
    public static Image FRAGMENT_IMAGE;
    public static Image TOKEN_IMAGE;
    public static Image MODE_IMAGE;
    public static Image CHANNEL_IMAGE;

    static {
        PARSER_IMAGE = ImageUtilities.loadImage("org/antlr/works/editor/grammar/navigation/resources/parsericon.png");
        LEXER_IMAGE = ImageUtilities.loadImage("org/antlr/works/editor/grammar/navigation/resources/lexericon.png");
        FRAGMENT_IMAGE = ImageUtilities.loadImage("org/antlr/works/editor/grammar/navigation/resources/fragmenticon.png");
        TOKEN_IMAGE = ImageUtilities.loadImage("org/antlr/works/editor/grammar/navigation/resources/tokenicon.png");
        MODE_IMAGE = ImageUtilities.loadImage("org/antlr/works/editor/grammar/navigation/resources/modeicon.png");
        CHANNEL_IMAGE = ImageUtilities.loadImage("org/antlr/works/editor/grammar/navigation/resources/channelicon.png");
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
        if (getDescription() instanceof GrammarNodeDescription) {
            switch (((GrammarNodeDescription)getDescription()).getDeclarationKind()) {
            case PARSER_RULE:
                return PARSER_IMAGE;

            case LEXER_RULE:
                return LEXER_IMAGE;

            case FRAGMENT_RULE:
                return FRAGMENT_IMAGE;

            case TOKEN:
                return TOKEN_IMAGE;

            case MODE:
                return MODE_IMAGE;

            case CHANNEL:
                return CHANNEL_IMAGE;

            case UNKNOWN:
            case UNDEFINED:
            default:
                break;
            }
        }

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
        private final DeclarationKind declarationKind;

        public GrammarNodeDescription(DeclarationKind declarationKind) {
            this.declarationKind = declarationKind;
        }

        public GrammarNodeDescription(DeclarationKind declarationKind, String name) {
            super(name);
            this.declarationKind = declarationKind;
        }

        public DeclarationKind getDeclarationKind() {
            return declarationKind;
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
