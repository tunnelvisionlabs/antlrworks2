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
import com.tvl.spi.editor.completion.CompletionItemComparator;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class BaseCompletionItemComparator extends CompletionItemComparator {
    @NonNull
    public static final Comparator<CompletionItem> PRIORITY_COMPARATOR =
        new BaseCompletionItemComparator(new PriorityComparator(), new ItemTextComparator());

    @NonNull
    public static final Comparator<CompletionItem> TEXT_COMPARATOR =
        new BaseCompletionItemComparator(new ItemTextComparator(), new PriorityComparator());

    public BaseCompletionItemComparator(@NonNull Comparator<CompletionItem> primaryComparator,
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
