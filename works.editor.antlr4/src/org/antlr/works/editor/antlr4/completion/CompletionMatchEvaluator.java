/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.completion;

import com.tvl.spi.editor.completion.CompletionItem;
import java.text.Collator;
import java.util.Locale;
import java.util.regex.Pattern;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class CompletionMatchEvaluator {
    public static final int EXACT_CASE_SENSITIVE = 0x1000;
    public static final int EXACT = 0x0800;
    public static final int PREFIX_CASE_SENSITIVE = 0x0400;
    public static final int PREFIX = 0x0200;
    public static final int SUBSTRING = 0x0100;
    public static final int WORD = 0x0080;
    public static final int LETTER_ORDER = 0x0040;
    public static final int VALID = 0x0020;
    public static final int RECENTLY_USED_MASK = 0x001E;
    public static final int CASE_SENSITIVE = 0x0001;

    private static final Collator recentCompletionsCollator;

    @NonNull
    private final String evaluatedText;
    @NonNull
    private final String lowerCaseEvaluatedText;
    @NullAllowed
    private final Pattern caseSensitiveWordMatch;
    @NullAllowed
    private final Pattern caseInsensitiveWordMatch;
    @NullAllowed
    private final Pattern caseSensitiveLetterOrderMatch;
    @NullAllowed
    private final Pattern caseInsensitiveLetterOrderMatch;

    static {
        recentCompletionsCollator = Collator.getInstance(Locale.getDefault());
        recentCompletionsCollator.setDecomposition(Collator.NO_DECOMPOSITION);
        recentCompletionsCollator.setStrength(Collator.IDENTICAL);
    }

    public CompletionMatchEvaluator(@NonNull String evaluatedText) {
        Parameters.notNull("evaluatedText", evaluatedText);
        this.evaluatedText = evaluatedText;
        this.lowerCaseEvaluatedText = evaluatedText.toLowerCase(Locale.getDefault());
        this.caseSensitiveWordMatch = BaseCompletionController.getPrefixBoundaryPattern(evaluatedText, true);
        this.caseInsensitiveWordMatch = BaseCompletionController.getPrefixBoundaryPattern(evaluatedText, false);
        this.caseSensitiveLetterOrderMatch = BaseCompletionController.getLetterOrderPattern(evaluatedText, true);
        this.caseInsensitiveLetterOrderMatch = BaseCompletionController.getLetterOrderPattern(evaluatedText, false);
    }

    public int getMatchStrength(@NonNull CompletionItem completionItem) {
        Parameters.notNull("completionItem", completionItem);

        CompletionMatchResult exact = isExactMatch(completionItem);
        CompletionMatchResult prefix = isPrefixMatch(completionItem);
        CompletionMatchResult substring = isSubstringMatch(completionItem);
        CompletionMatchResult word = isWordBoundaryMatch(completionItem);
        CompletionMatchResult letterOrder = isLetterOrderMatch(completionItem);
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
        } else if (letterOrder != CompletionMatchResult.None) {
            caseSensitive = letterOrder == CompletionMatchResult.MatchCaseSensitive;
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

        if (letterOrder != CompletionMatchResult.None) {
            strength |= LETTER_ORDER;
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

    public @NonNull CompletionMatchResult isLetterOrderMatch(@NonNull CompletionItem completionItem) {
        Parameters.notNull("completionItem", completionItem);

        if (evaluatedText.isEmpty() || caseSensitiveLetterOrderMatch == null || caseInsensitiveLetterOrderMatch == null) {
            return CompletionMatchResult.None;
        }

        String insertText = completionItem.getInsertPrefix().toString();
        if (caseInsensitiveLetterOrderMatch.matcher(insertText).find()) {
            if (caseSensitiveLetterOrderMatch.matcher(insertText).find()) {
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

    protected Collator getRecentCompletionsCollator() {
        return recentCompletionsCollator;
    }

    public int getRecentlyUsed(@NonNull CompletionItem completionItem) {
        Parameters.notNull("completionItem", completionItem);

        return BaseCompletionController.getRecentCompletionWeight(completionItem.getInsertPrefix().toString(), getRecentCompletionsCollator());
    }
}
