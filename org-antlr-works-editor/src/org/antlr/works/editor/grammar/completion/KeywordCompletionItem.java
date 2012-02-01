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
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

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

    public KeywordCompletionItem(@NonNull String keyword) {
        Parameters.notNull("keyword", keyword);
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
