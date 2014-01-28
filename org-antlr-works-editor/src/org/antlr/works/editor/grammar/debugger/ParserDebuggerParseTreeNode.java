/*
 *  Copyright (c) 2014 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */

package org.antlr.works.editor.grammar.debugger;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;
import javax.swing.Action;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.antlr4.navigation.ParseTreeNode;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.editor.BaseAction;
import org.openide.util.NbBundle.Messages;

/**
 *
 * @author Sam Harwell
 */
public class ParserDebuggerParseTreeNode extends ParseTreeNode {
    private final Map<ParseTree, Transition> associatedTransitions;

    public ParserDebuggerParseTreeNode(@NonNull ParseTree tree, Map<ParseTree, Transition> associatedTransitions, @NullAllowed List<String> ruleNames) {
        super(tree, ruleNames);
        this.associatedTransitions = associatedTransitions;
    }

    @Override
    public Action[] getActions(boolean context) {
        Action[] superActions = super.getActions(context);

        if (getTree() instanceof TerminalNode) {
            Action[] actions = {
                new GoToMatchTransitionAction()
            };

            actions = TextAction.augmentList(superActions, actions);
            return actions;
        } else if (getTree() instanceof RuleNode) {
            Action[] actions = {
                new GoToRuleReferenceAction()
            };

            actions = TextAction.augmentList(superActions, actions);
            return actions;
        } else {
            return superActions;
        }
    }

    @Override
    protected ParseTreeNode createChildNode(ParseTree tree) {
        return new ParserDebuggerParseTreeNode(tree, associatedTransitions, getRuleNames());
    }

    @Messages("goto-match-transition=Go to Match Transition")
    public static class GoToMatchTransitionAction extends BaseAction {

        public GoToMatchTransitionAction() {
            putValue(NAME, Bundle.goto_match_transition());
        }

        @Override
        public void actionPerformed(ActionEvent ae, JTextComponent jtc) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Messages("goto-rule-reference=Go to Rule Reference")
    public static class GoToRuleReferenceAction extends BaseAction {

        public GoToRuleReferenceAction() {
            putValue(NAME, Bundle.goto_rule_reference());
        }

        @Override
        public void actionPerformed(ActionEvent ae, JTextComponent jtc) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

}
