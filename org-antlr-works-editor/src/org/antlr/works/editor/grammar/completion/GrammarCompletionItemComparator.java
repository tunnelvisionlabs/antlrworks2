/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.antlr.works.editor.grammar.completion;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionItemComparator;

/**
 *
 * @author Sam Harwell
 */
public class GrammarCompletionItemComparator extends CompletionItemComparator {
    @NonNull
    protected static final Comparator<CompletionItem> PRIORITY_COMPARATOR =
        new GrammarCompletionItemComparator(new PriorityComparator(), new ItemTextComparator());

    @NonNull
    protected static final Comparator<CompletionItem> TEXT_COMPARATOR =
        new GrammarCompletionItemComparator(new ItemTextComparator(), new PriorityComparator());

    public GrammarCompletionItemComparator(@NonNull Comparator<CompletionItem> primaryComparator,
                            @NonNull Comparator<CompletionItem> secondaryComparator) {
        super(primaryComparator, secondaryComparator);
    }

    protected static class ItemTextComparator extends TextComparator {
        private final Collator secondaryCollator;
        private final Collator identicalCollator;

        public ItemTextComparator() {
            secondaryCollator = Collator.getInstance(Locale.getDefault());
            secondaryCollator.setDecomposition(Collator.FULL_DECOMPOSITION);
            secondaryCollator.setStrength(Collator.SECONDARY);

            identicalCollator = Collator.getInstance(Locale.getDefault());
            identicalCollator.setDecomposition(Collator.FULL_DECOMPOSITION);
            identicalCollator.setStrength(Collator.IDENTICAL);
        }

        @Override
        protected int compareText(CharSequence text1, CharSequence text2) {
            if (text1 == null) {
                text1 = ""; //NOI18N
            }

            if (text2 == null) {
                text2 = ""; //NOI18N
            }

            int caseInsensitive = secondaryCollator.compare(text1.toString(), text2.toString());
            if (caseInsensitive != 0) {
                return caseInsensitive;
            }

            return identicalCollator.compare(text1.toString(), text2.toString());
        }
    }
}
