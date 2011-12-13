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

import org.antlr.v4.runtime.Token;

/**
 *
 * @author Sam Harwell
 */
public abstract class ElementReferenceCompletionItem extends GrammarCompletionItem {

    private final Token label;
    private final boolean explicit;

    private String leftText;

    public ElementReferenceCompletionItem(Token label, boolean explicit) {
        this.label = label;
        this.explicit = explicit;
    }

    @Override
    public int getSortPriority() {
        return ELEMENT_REFERENCE_SORT_PRIORITY;
    }

    @Override
    public String getSortTextImpl() {
        return getPrefix() + label.getText();
    }

    @Override
    public CharSequence getInsertPrefix() {
        return getPrefix() + label.getText();
    }

    public boolean isExplicit() {
        return explicit;
    }

    protected abstract String getPrefix();

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();

            builder.append(REFERENCE_COLOR);
            builder.append(BOLD);

            builder.append(getPrefix()).append(label.getText());

            builder.append(BOLD_END);
            builder.append(COLOR_END);

            leftText = builder.toString();
        }

        return leftText;
    }

    @Override
    protected String getRightHtmlText() {
        if (explicit) {
            return "Label";
        } else {
            return "Element";
        }
    }
}
