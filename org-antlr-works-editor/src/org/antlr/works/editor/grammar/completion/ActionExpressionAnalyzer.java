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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.text.JTextComponent;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.codemodel.AttributeModel;
import org.antlr.works.editor.grammar.codemodel.CodeElementModel;
import org.antlr.works.editor.grammar.codemodel.CodeElementPositionRegion;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.codemodel.LabelModel;
import org.antlr.works.editor.grammar.codemodel.PackageModel;
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.antlr.works.editor.grammar.codemodel.TokenVocabModel;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ActionExpressionContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ActionScopeExpressionContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.AbstractGrammarParser.RuleSpecContext;
import org.antlr.works.editor.grammar.experimental.generated.GrammarParserBaseListener;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.project.Project;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class ActionExpressionAnalyzer extends GrammarParserBaseListener {
    private final FileModel fileModel;
    private final RuleContext finalContext;
    private final List<AttributeModel> members = new ArrayList<>();

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
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionScopeExpression, version=6, dependents=Dependents.ANCESTORS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0, dependents=Dependents.SELF),
    })
    public void enterActionScopeExpression(ActionScopeExpressionContext ctx) {
        if (ctx.op != null && ctx.member == null) {
            /* action scope expressions are only used for rule references
             *   $ruleName::
             */

            RuleSpecContext enclosingRule = getEnclosingRuleContext(ctx);
            Collection<? extends RuleModel> referencedRules = getReferencedRule(enclosingRule, ctx.ref, false);
            for (RuleModel referencedRule : referencedRules) {
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
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_actionExpression, version=6, dependents=Dependents.ANCESTORS),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0, dependents=Dependents.SELF),
    })
    public void enterActionExpression(ActionExpressionContext ctx) {
        if (ctx.op != null && ctx.member == null) {
            /* action expressions are used for label references (explicit or implicit)
             *   $elementName. (implicit label reference)
             *   $labelName.   (explicit label reference)
             */

            RuleSpecContext enclosingRule = getEnclosingRuleContext(ctx);
            Collection<? extends RuleModel> referencedRules = getReferencedRule(enclosingRule, ctx.ref, true);
            for (RuleModel referencedRule : referencedRules) {
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
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0, dependents=Dependents.SELF),
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

    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0, dependents=Dependents.SELF)
    private Collection<? extends RuleModel> getReferencedRule(RuleSpecContext enclosingRule, Token reference, boolean followLabels) {
        String enclosingRuleName = getName(enclosingRule).getText();
        Collection<? extends RuleModel> ruleModels = fileModel.getRules(enclosingRuleName);

        for (RuleModel ruleModel : ruleModels) {
            Collection<? extends RuleModel> referencedRule = null;

            if (followLabels) {
                /* first try for a label reference. even though labels are not allowed to
                * alias rule names, we want to minimize the impact of this restriction
                * on the ability of code completion to provide useful results.
                */
                Collection<? extends LabelModel> label = ruleModel.getLabels(reference.getText().substring(1));
                if (!label.isEmpty()) {
                    throw new UnsupportedOperationException("Not implemented yet.");
                }
            }

            if (referencedRule == null) {
                referencedRule = fileModel.getRules(reference.getText().substring(1));
            }

            if (!referencedRule.isEmpty()) {
                return referencedRule;
            }
        }

        return null;
    }

    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=7, dependents=Dependents.DESCENDANTS)
    private static RuleSpecContext getEnclosingRuleContext(RuleContext context) {
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

        @Override
        public PackageModel getPackage() {
            return IntrinsicPackage.INSTANCE;
        }

        @Override
        public Collection<? extends CodeElementModel> getMembers() {
            return Collections.emptyList();
        }

        @Override
        public Collection<? extends CodeElementModel> getMembers(String name) {
            return Collections.emptyList();
        }

        @Override
        public CodeElementPositionRegion getSeek() {
            return null;
        }

        @Override
        public CodeElementPositionRegion getSpan() {
            return null;
        }

        @Override
        public JTextComponent navigateTo() {
            return null;
        }
    }

    private static class IntrinsicPackage implements PackageModel {
        public static final IntrinsicPackage INSTANCE = new IntrinsicPackage();

        @Override
        public Project getProject() {
            return null;
        }

        @Override
        public Collection<? extends FileModel> getFiles() {
            return Collections.emptyList();
        }

        @Override
        public Collection<? extends TokenVocabModel> getVocabularies() {
            return Collections.emptyList();
        }

        @Override
        public PackageModel getPackage() {
            return this;
        }

        @Override
        public String getName() {
            return "intrinsic";
        }

        @Override
        public Collection<? extends CodeElementModel> getMembers() {
            return Collections.emptyList();
        }

        @Override
        public Collection<? extends CodeElementModel> getMembers(String name) {
            return Collections.emptyList();
        }

        @Override
        public CodeElementPositionRegion getSeek() {
            return null;
        }

        @Override
        public CodeElementPositionRegion getSpan() {
            return null;
        }

        @Override
        public JTextComponent navigateTo() {
            return null;
        }
    }
}
