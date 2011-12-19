/*
 * [The "BSD license"]
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
package org.antlr.works.editor.grammar.completion;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.grammar.codemodel.AttributeModel;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.codemodel.LabelModel;
import org.antlr.works.editor.grammar.codemodel.RuleModel;
import org.antlr.works.editor.grammar.experimental.BlankGrammarParserListener;
import org.antlr.works.editor.grammar.experimental.GrammarParser.actionExpressionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.actionScopeExpressionContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser.ruleContext;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class ActionExpressionAnalyzer extends BlankGrammarParserListener {
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
    public void enterRule(actionScopeExpressionContext ctx) {
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
    public void enterRule(actionExpressionContext ctx) {
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

    private RuleModel getReferencedRule(ruleContext enclosingRule, Token reference, boolean followLabels) {
        String enclosingRuleName = enclosingRule.name.start.getText();
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
