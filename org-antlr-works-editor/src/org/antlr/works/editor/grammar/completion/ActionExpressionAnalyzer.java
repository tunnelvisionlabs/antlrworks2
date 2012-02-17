/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.codemodel.AttributeModel;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.codemodel.LabelModel;
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.antlr.works.editor.grammar.experimental.GrammarParserBaseListener;
import org.antlr.works.editor.grammar.experimental.GrammarParser.actionExpressionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.actionScopeExpressionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.lexerRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.parserRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleContext;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class ActionExpressionAnalyzer extends GrammarParserBaseListener {
    private final FileModel fileModel;
    private final RuleContext finalContext;
    private final List<AttributeModel> members = new ArrayList<AttributeModel>();

    public ActionExpressionAnalyzer(@NonNull FileModel fileModel, @NonNull RuleContext finalContext) {
        Parameters.notNull("fileModel", fileModel);
        Parameters.notNull("finalContext", finalContext);

        this.fileModel = fileModel;
        this.finalContext = finalContext;
    }

    public List<AttributeModel> getMembers() {
        return members;
    }

    @Override
    public void actionScopeExpressionEnter(actionScopeExpressionContext ctx) {
        if (ctx.op != null && ctx.member == null) {
            /* action scope expressions are only used for rule references
             *   $ruleName::
             */

            ruleContext enclosingRule = getEnclosingRuleContext(ctx);
            RuleModel referencedRule = getReferencedRule(enclosingRule, ctx.ref, false);
            if (referencedRule != null) {
                // for a scope reference, we can reference the parameters, locals, return values, and labels
                members.addAll(referencedRule.getParameters());
                members.addAll(referencedRule.getReturnValues());
                members.addAll(referencedRule.getLocals());
                members.addAll(referencedRule.getLabels());
            }
        }
    }

    @Override
    public void actionExpressionEnter(actionExpressionContext ctx) {
        if (ctx.op != null && ctx.member == null) {
            /* action expressions are used for label references (explicit or implicit)
             *   $elementName. (implicit label reference)
             *   $labelName.   (explicit label reference)
             */

            ruleContext enclosingRule = getEnclosingRuleContext(ctx);
            RuleModel referencedRule = getReferencedRule(enclosingRule, ctx.ref, true);
            if (referencedRule != null) {
                // for a regular reference, we can reference the return values and labels
                members.addAll(referencedRule.getReturnValues());
                members.addAll(referencedRule.getLabels());
                members.add(new IntrinsicAttribute("start"));
                members.add(new IntrinsicAttribute("stop"));
                members.add(new IntrinsicAttribute("text"));
                members.add(new IntrinsicAttribute("tree"));
                members.add(new IntrinsicAttribute("st"));
            }
        }
    }

    private Token getName(ruleContext rule) {
        if (rule.getChild(0) instanceof parserRuleContext) {
            return ((parserRuleContext)rule.getChild(0)).name;
        } else if (rule.getChild(0) instanceof lexerRuleContext) {
            return ((lexerRuleContext)rule.getChild(0)).name;
        } else {
            return null;
        }
    }
    private RuleModel getReferencedRule(ruleContext enclosingRule, Token reference, boolean followLabels) {
        String enclosingRuleName = getName(enclosingRule).getText();
        RuleModel ruleModel = fileModel.getRule(enclosingRuleName);
        RuleModel referencedRule = null;

        if (followLabels) {
            /* first try for a label reference. even though labels are not allowed to
             * alias rule names, we want to minimize the impact of this restriction
             * on the ability of code completion to provide useful results.
             */
            LabelModel label = ruleModel.getLabel(reference.getText().substring(1));
            if (label != null) {
                throw new UnsupportedOperationException("Not implemented yet.");
            }
        }

        if (referencedRule == null) {
            referencedRule = fileModel.getRule(reference.getText().substring(1));
        }

        return referencedRule;
    }

    private static ruleContext getEnclosingRuleContext(RuleContext context) {
        while (context != null) {
            if (context instanceof ruleContext) {
                return (ruleContext)context;
            }

            context = context.parent;
        }

        return null;
    }

    private static class IntrinsicAttribute implements AttributeModel {
        private final String name;

        public IntrinsicAttribute(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getType() {
            return "";
        }

    }
}
