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
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.codemodel.AttributeModel;
import org.antlr.works.editor.grammar.codemodel.impl.FileModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.LabelModelImpl;
import org.antlr.works.editor.grammar.codemodel.impl.RuleModelImpl;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ActionExpressionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ActionScopeExpressionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.RuleSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParserBaseListener;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class ActionExpressionAnalyzer extends GrammarParserBaseListener {
    private final FileModelImpl fileModel;
    private final RuleContext<?> finalContext;
    private final List<AttributeModel> members = new ArrayList<AttributeModel>();

    public ActionExpressionAnalyzer(@NonNull FileModelImpl fileModel, @NonNull RuleContext<?> finalContext) {
        Parameters.notNull("fileModel", fileModel);
        Parameters.notNull("finalContext", finalContext);

        this.fileModel = fileModel;
        this.finalContext = finalContext;
    }

    public List<AttributeModel> getMembers() {
        return members;
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionExpression, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0),
    })
    public void enterActionScopeExpression(ActionScopeExpressionContext ctx) {
        if (ctx.op != null && ctx.member == null) {
            /* action scope expressions are only used for rule references
             *   $ruleName::
             */

            RuleSpecContext enclosingRule = getEnclosingRuleContext(ctx);
            RuleModelImpl referencedRule = getReferencedRule(enclosingRule, ctx.ref, false);
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
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionExpression, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0),
    })
    public void enterActionExpression(ActionExpressionContext ctx) {
        if (ctx.op != null && ctx.member == null) {
            /* action expressions are used for label references (explicit or implicit)
             *   $elementName. (implicit label reference)
             *   $labelName.   (explicit label reference)
             */

            RuleSpecContext enclosingRule = getEnclosingRuleContext(ctx);
            RuleModelImpl referencedRule = getReferencedRule(enclosingRule, ctx.ref, true);
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

    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0),
    })
    private Token getName(RuleSpecContext rule) {
        if (rule.getChild(0) instanceof ParserRuleSpecContext) {
            return ((ParserRuleSpecContext)rule.getChild(0)).name;
        } else if (rule.getChild(0) instanceof LexerRuleContext) {
            return ((LexerRuleContext)rule.getChild(0)).name;
        } else {
            return null;
        }
    }

    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0)
    private RuleModelImpl getReferencedRule(RuleSpecContext enclosingRule, Token reference, boolean followLabels) {
        String enclosingRuleName = getName(enclosingRule).getText();
        RuleModelImpl ruleModel = fileModel.getRule(enclosingRuleName);
        RuleModelImpl referencedRule = null;

        if (followLabels) {
            /* first try for a label reference. even though labels are not allowed to
             * alias rule names, we want to minimize the impact of this restriction
             * on the ability of code completion to provide useful results.
             */
            LabelModelImpl label = ruleModel.getLabel(reference.getText().substring(1));
            if (label != null) {
                throw new UnsupportedOperationException("Not implemented yet.");
            }
        }

        if (referencedRule == null) {
            referencedRule = fileModel.getRule(reference.getText().substring(1));
        }

        return referencedRule;
    }

    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0)
    private static RuleSpecContext getEnclosingRuleContext(RuleContext<?> context) {
        while (context != null) {
            if (context instanceof RuleSpecContext) {
                return (RuleSpecContext)context;
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
