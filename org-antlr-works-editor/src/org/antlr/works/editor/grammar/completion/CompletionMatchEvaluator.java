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

import java.text.Collator;
import java.util.Locale;
import java.util.regex.Pattern;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class CompletionMatchEvaluator {
    protected static final int EXACT_CASE_SENSITIVE = 0x0800;
    protected static final int EXACT = 0x0400;
    protected static final int PREFIX_CASE_SENSITIVE = 0x0200;
    protected static final int PREFIX = 0x0100;
    protected static final int SUBSTRING = 0x0080;
    protected static final int WORD = 0x0040;
    protected static final int VALID = 0x0020;
    protected static final int RECENTLY_USED_MASK = 0x001E;
    protected static final int CASE_SENSITIVE = 0x0001;

    private static final Collator recentCompletionsCollator;

    @NonNull
    private final String evaluatedText;
    @NonNull
    private final String lowerCaseEvaluatedText;
    @NullAllowed
    private final Pattern caseSensitiveWordMatch;
    @NullAllowed
    private final Pattern caseInsensitiveWordMatch;

    static {
        recentCompletionsCollator = Collator.getInstance(Locale.getDefault());
        recentCompletionsCollator.setDecomposition(Collator.FULL_DECOMPOSITION);
        recentCompletionsCollator.setStrength(Collator.SECONDARY);
    }

    public CompletionMatchEvaluator(@NonNull String evaluatedText) {
        Parameters.notNull("evaluatedText", evaluatedText);
        this.evaluatedText = evaluatedText;
        this.lowerCaseEvaluatedText = evaluatedText.toLowerCase(Locale.getDefault());
        this.caseSensitiveWordMatch = GrammarCompletionController.getPrefixBoundaryPattern(evaluatedText, true);
        this.caseInsensitiveWordMatch = GrammarCompletionController.getPrefixBoundaryPattern(evaluatedText, false);
    }

    public int getMatchStrength(@NonNull CompletionItem completionItem) {
        Parameters.notNull("completionItem", completionItem);

        CompletionMatchResult exact = isExactMatch(completionItem);
        CompletionMatchResult prefix = isPrefixMatch(completionItem);
        CompletionMatchResult substring = isSubstringMatch(completionItem);
        CompletionMatchResult word = isWordBoundaryMatch(completionItem);
        CompletionMatchResult valid = isValidMatch(completionItem);
        int recent = getRecentlyUsed(completionItem);

        boolean caseSensitive;
        if (exact != CompletionMatchResult.None) {
            caseSensitive = exact == CompletionMatchResult.MatchCaseSensitive;
        } else if (prefix != CompletionMatchResult.None) {
            caseSensitive = prefix == CompletionMatchResult.MatchCaseSensitive;
        } else if (substring != CompletionMatchResult.None) {
            caseSensitive = substring == CompletionMatchResult.MatchCaseSensitive;
        } else if (word != CompletionMatchResult.None) {
            caseSensitive = word == CompletionMatchResult.MatchCaseSensitive;
        } else if (valid != CompletionMatchResult.None) {
            caseSensitive = valid == CompletionMatchResult.MatchCaseSensitive;
        } else {
            caseSensitive = false;
        }

        int strength = 0;

        if (exact == CompletionMatchResult.MatchCaseSensitive) {
            strength |= EXACT_CASE_SENSITIVE;
        }

        if (prefix == CompletionMatchResult.MatchCaseSensitive) {
            strength |= PREFIX_CASE_SENSITIVE;
        }

        if (exact == CompletionMatchResult.Match) {
            strength |= EXACT;
        }

        if (prefix == CompletionMatchResult.Match) {
            strength |= PREFIX;
        }

        if (substring != CompletionMatchResult.None) {
            strength |= SUBSTRING;
        }

        if (word != CompletionMatchResult.None) {
            strength |= WORD;
        }

        if (valid != CompletionMatchResult.None) {
            strength |= VALID;
        }

        recent = Math.max(0, recent);
        recent = Math.min(255, recent);
        strength |= (recent << 1);

        if (caseSensitive) {
            strength |= CASE_SENSITIVE;
        }

        return strength;
    }

    protected @NonNull CompletionMatchResult isExactMatch(@NonNull CompletionItem completionItem) {
        Parameters.notNull("completionItem", completionItem);

        if (evaluatedText.isEmpty()) {
            return CompletionMatchResult.None;
        }

        String insertText = completionItem.getInsertPrefix().toString();
        if (evaluatedText.equals(insertText)) {
            return CompletionMatchResult.MatchCaseSensitive;
        }

        insertText = insertText.toLowerCase(Locale.getDefault());
        if (lowerCaseEvaluatedText.equals(insertText)) {
            return CompletionMatchResult.Match;
        }

        return CompletionMatchResult.None;
    }

    protected @NonNull CompletionMatchResult isPrefixMatch(@NonNull CompletionItem completionItem) {
        Parameters.notNull("completionItem", completionItem);

        if (evaluatedText.isEmpty()) {
            return CompletionMatchResult.MatchCaseSensitive;
        }

        String insertText = completionItem.getInsertPrefix().toString();
        if (insertText.startsWith(evaluatedText)) {
            return CompletionMatchResult.MatchCaseSensitive;
        }

        insertText = insertText.toLowerCase(Locale.getDefault());
        if (insertText.startsWith(lowerCaseEvaluatedText)) {
            return CompletionMatchResult.Match;
        }

        return CompletionMatchResult.None;
    }

    public @NonNull CompletionMatchResult isSubstringMatch(@NonNull CompletionItem completionItem) {
        Parameters.notNull("completionItem", completionItem);

        if (evaluatedText.isEmpty()) {
            return CompletionMatchResult.MatchCaseSensitive;
        }

        String insertText = completionItem.getInsertPrefix().toString();
        if (insertText.contains(evaluatedText)) {
            return CompletionMatchResult.MatchCaseSensitive;
        }

        insertText = insertText.toLowerCase(Locale.getDefault());
        if (insertText.contains(lowerCaseEvaluatedText)) {
            return CompletionMatchResult.Match;
        }

        return CompletionMatchResult.None;
    }

    public @NonNull CompletionMatchResult isWordBoundaryMatch(@NonNull CompletionItem completionItem) {
        Parameters.notNull("completionItem", completionItem);

        if (evaluatedText.isEmpty() || caseSensitiveWordMatch == null || caseInsensitiveWordMatch == null) {
            return CompletionMatchResult.None;
        }

        String insertText = completionItem.getInsertPrefix().toString();
        if (caseInsensitiveWordMatch.matcher(insertText).matches()) {
            if (caseSensitiveWordMatch.matcher(insertText).matches()) {
                return CompletionMatchResult.MatchCaseSensitive;
            }

            return CompletionMatchResult.Match;
        }

        return CompletionMatchResult.None;
    }

    public @NonNull CompletionMatchResult isValidMatch(@NonNull CompletionItem completionItem) {
        Parameters.notNull("completionItem", completionItem);

        return completionItem.getSortPriority() < 0 ? CompletionMatchResult.Match : CompletionMatchResult.None;
    }

    public int getRecentlyUsed(@NonNull CompletionItem completionItem) {
        Parameters.notNull("completionItem", completionItem);

        return GrammarCompletionController.getRecentCompletionWeight(completionItem.getInsertPrefix().toString(), recentCompletionsCollator);
    }
}
