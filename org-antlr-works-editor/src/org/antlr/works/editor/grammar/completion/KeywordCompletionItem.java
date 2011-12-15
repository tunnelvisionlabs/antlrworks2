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
import java.util.Collection;

/**
 *
 * @author Sam Harwell
 */
public class KeywordCompletionItem extends GrammarCompletionItem {

    public static final Collection<KeywordCompletionItem> KEYWORD_ITEMS =
        new ArrayList<KeywordCompletionItem>() {{
            add(new KeywordCompletionItem("lexer"));
            add(new KeywordCompletionItem("parser"));
            add(new KeywordCompletionItem("catch"));
            add(new KeywordCompletionItem("finally"));
            add(new KeywordCompletionItem("grammar"));
            add(new KeywordCompletionItem("locals"));
            add(new KeywordCompletionItem("mode"));
            add(new KeywordCompletionItem("private"));
            add(new KeywordCompletionItem("protected"));
            add(new KeywordCompletionItem("public"));
            add(new KeywordCompletionItem("returns"));
            add(new KeywordCompletionItem("throws"));
            add(new KeywordCompletionItem("tree"));
            add(new KeywordCompletionItem("scope"));
            add(new KeywordCompletionItem("import"));
            add(new KeywordCompletionItem("fragment"));
            add(new KeywordCompletionItem("tokens"));
            add(new KeywordCompletionItem("options"));
        }};

    private final String keyword;
    private String leftText;

    public KeywordCompletionItem(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public int getSortPriority() {
        return KEYWORD_SORT_PRIORITY;
    }

    @Override
    public String getSortTextImpl() {
        return keyword;
    }

    @Override
    public CharSequence getInsertPrefix() {
        return keyword;
    }

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(KEYWORD_COLOR);
            builder.append(BOLD);
            builder.append(keyword);
            builder.append(BOLD_END);
            builder.append(COLOR_END);
            leftText = builder.toString();
        }
        return leftText;
    }

}
