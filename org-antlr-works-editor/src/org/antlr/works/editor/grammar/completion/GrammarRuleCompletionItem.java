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
package org.antlr.works.editor.grammar;

import javax.swing.ImageIcon;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.works.editor.grammar.navigation.GrammarNode;

/**
 *
 * @author Sam Harwell
 */
public class GrammarRuleCompletionItem extends GrammarCompletionItem {

    private final Description rule;

    private String leftText;

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
        return new ImageIcon(new GrammarNode(rule).getIcon(0));
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
