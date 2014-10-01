/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class KeywordCompletionItem extends GrammarCompletionItem {

    public static final IntervalSet KEYWORD_TYPES =
        new IntervalSet(GrammarLexer.LEXER,
                        GrammarLexer.PARSER,
                        GrammarLexer.CATCH,
                        GrammarLexer.FINALLY,
                        GrammarLexer.GRAMMAR,
                        GrammarLexer.LOCALS,
                        GrammarLexer.MODE,
                        GrammarLexer.PRIVATE,
                        GrammarLexer.PROTECTED,
                        GrammarLexer.PUBLIC,
                        GrammarLexer.RETURNS,
                        GrammarLexer.THROWS,
                        GrammarLexer.IMPORT,
                        GrammarLexer.FRAGMENT,
                        GrammarLexer.TOKENS,
                        GrammarLexer.CHANNELS,
                        GrammarLexer.OPTIONS);

    private static final Map<Integer, String> KEYWORDS = new HashMap<>();
    static {
        for (int i : KEYWORD_TYPES.toArray()) {
            KEYWORDS.put(i, GrammarLexer.VOCABULARY.getSymbolicName(i).toLowerCase());
        }
    }

    public static final Map<Integer, KeywordCompletionItem> KEYWORD_ITEMS = new HashMap<>();
    static {
        for (Map.Entry<Integer, String> keyword : KEYWORDS.entrySet()) {
            KEYWORD_ITEMS.put(keyword.getKey(), new KeywordCompletionItem(keyword.getValue()));
        }
    }

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
