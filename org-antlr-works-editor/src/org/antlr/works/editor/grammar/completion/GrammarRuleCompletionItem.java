/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import javax.swing.ImageIcon;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.v4.tool.Grammar;
import org.antlr.works.editor.grammar.navigation.GrammarNode;
import org.antlr.works.editor.grammar.navigation.GrammarNode.GrammarNodeDescription;

/**
 *
 * @author Sam Harwell
 */
public class GrammarRuleCompletionItem extends GrammarCompletionItem {
    private static final ImageIcon PARSER_ICON;
    private static final ImageIcon LEXER_ICON;
    private static final ImageIcon FRAGMENT_ICON;
    private static final ImageIcon TOKEN_ICON;
    private static final ImageIcon MODE_ICON;
    private static final ImageIcon CHANNEL_ICON;

    private final Description rule;

    private String leftText;

    static {
        PARSER_ICON = new ImageIcon(GrammarNode.PARSER_IMAGE);
        LEXER_ICON = new ImageIcon(GrammarNode.LEXER_IMAGE);
        FRAGMENT_ICON = new ImageIcon(GrammarNode.FRAGMENT_IMAGE);
        TOKEN_ICON = new ImageIcon(GrammarNode.TOKEN_IMAGE);
        MODE_ICON = new ImageIcon(GrammarNode.MODE_IMAGE);
        CHANNEL_ICON = new ImageIcon(GrammarNode.CHANNEL_IMAGE);
    }

    public GrammarRuleCompletionItem(Description rule) {
        this.rule = rule;
    }

    @Override
    public int getSortPriority() {
        return RULE_SORT_PRIORITY;
    }

    @Override
    public String getSortTextImpl() {
        return rule.getName();
    }

    @Override
    public CharSequence getInsertPrefix() {
        return rule.getName();
    }

    @Override
    protected ImageIcon getIcon() {
        if (rule instanceof GrammarNodeDescription) {
            switch (((GrammarNodeDescription)rule).getDeclarationKind()) {
            case PARSER_RULE:
                return PARSER_ICON;

            case LEXER_RULE:
                return LEXER_ICON;

            case FRAGMENT_RULE:
                return FRAGMENT_ICON;

            case TOKEN:
                return TOKEN_ICON;

            case MODE:
                return MODE_ICON;

            case CHANNEL:
                return CHANNEL_ICON;

            case UNKNOWN:
            case UNDEFINED:
            default:
                break;
            }
        }

        String name = rule.getName();
        if (Grammar.isTokenName(name)) {
            return LEXER_ICON;
        } else {
            return PARSER_ICON;
        }
    }

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();

            builder.append(METHOD_COLOR);

            if (!rule.isInherited()) {
                builder.append(BOLD);
            }

            builder.append(rule.getName());

            if (!rule.isInherited()) {
                builder.append(BOLD_END);
            }

            builder.append(COLOR_END);
            
            leftText = builder.toString();
        }

        return leftText;
    }

}
