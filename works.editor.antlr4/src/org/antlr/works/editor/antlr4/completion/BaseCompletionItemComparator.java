/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
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
package org.antlr.works.editor.antlr4.completion;

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
