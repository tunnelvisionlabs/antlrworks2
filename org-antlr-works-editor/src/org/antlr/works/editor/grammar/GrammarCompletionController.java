/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.antlr.works.editor.grammar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.works.editor.grammar.GrammarCompletionProvider.GrammarCompletionQuery;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.Utilities;
import org.netbeans.spi.editor.completion.CompletionController;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionItemComparator;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;

/**
 *
 * @author Sam Harwell
 */
public class GrammarCompletionController implements CompletionController {
    private static final List<String> recentCompletions = new ArrayList<String>();
    private static final Collator recentCompletionsCollator;

    private final JTextComponent component;
    private final AsyncCompletionTask task;
    private final GrammarCompletionProvider.GrammarCompletionQuery query;

    static {
        recentCompletionsCollator = Collator.getInstance(Locale.getDefault());
        recentCompletionsCollator.setDecomposition(Collator.FULL_DECOMPOSITION);
        recentCompletionsCollator.setStrength(Collator.SECONDARY);
    }

    public GrammarCompletionController(@NonNull JTextComponent component, @NonNull CompletionTask task, int queryType) {
        this.component = component;
        this.task = (AsyncCompletionTask)task;
        this.query = (GrammarCompletionProvider.GrammarCompletionQuery)this.task.getQuery();
    }

    static void addRecentCompletion(String completion) {
        recentCompletions.remove(completion);
        if (recentCompletions.size() == 20) {
            recentCompletions.remove(0);
        }

        recentCompletions.add(completion);
    }

    static int getRecentCompletionWeight(String completion, Collator collator) {
        for (int i = recentCompletions.size() - 1; i >= 0; i--) {
            if (collator.compare(completion, recentCompletions.get(i)) == 0) {
                return i + 1;
            }
        }

        return 0;
    }

    public JTextComponent getComponent() {
        return component;
    }

    public Document getDocument() {
        return getComponent().getDocument();
    }

    GrammarCompletionQuery getQuery() {
        return query;
    }

    @Override
    public void sortItems(List<? extends CompletionItem> items, int sortType) {
        Collections.sort(items, getComparator(sortType));
    }

    @Override
    public Selection getSelection(List<? extends CompletionItem> items) {
        String completionPrefix = getCompletionPrefix();
        String evaluatedText = completionPrefix;
        while (true) {
            CompletionItem bestMatch = null;
            int bestMatchValue = 0;
            int prefixMatch = 0;
            for (CompletionItem item : items) {
                int matchValue = getMatchStrength(evaluatedText, item);
                if (matchValue > 0) {
                    if ((matchValue & (PREFIX_CASE_SENSITIVE | PREFIX)) != 0) {
                        prefixMatch++;
                    }

                    boolean improved = matchValue > bestMatchValue
                        || (matchValue == bestMatchValue && item.getInsertPrefix().toString().compareTo(bestMatch.getInsertPrefix().toString()) < 0);

                    if (improved) {
                        bestMatch = item;
                        bestMatchValue = matchValue;
                    }
                }
            }

            if (bestMatch != null) {
                int index = items.indexOf(bestMatch);
                boolean selected =
                    (!(bestMatch instanceof GrammarCompletionItem) || ((GrammarCompletionItem)bestMatch).allowInitialSelection())
                    && evaluatedText != null && !evaluatedText.isEmpty();
                boolean unique = evaluatedText.length() == completionPrefix.length() && (prefixMatch == 1);
                return new Selection(index, selected, unique);
            }

            if (evaluatedText.length() == 0) {
                break;
            }

            evaluatedText = evaluatedText.substring(0, evaluatedText.length() - 1);
        }

        return Selection.DEFAULT;
    }

    @Override
    public void defaultAction(CompletionItem bestMatch, boolean isSelected) {
        if (bestMatch instanceof GrammarCompletionItem) {
            ((GrammarCompletionItem)bestMatch).defaultAction(component, this, isSelected);
        } else {
            bestMatch.defaultAction(component);
        }
    }

    @Override
    public void processKeyEvent(KeyEvent evt, CompletionItem bestMatch, boolean isSelected) {
        if (bestMatch instanceof GrammarCompletionItem) {
            ((GrammarCompletionItem)bestMatch).processKeyEvent(evt, this, isSelected);
        } else {
            bestMatch.processKeyEvent(evt);
        }
    }

    @Override
    public void render(Graphics g, Font defaultFont, Color foregroundColor, Color backgroundColor, Color selectedForegroundColor, Color selectedBackgroundColor, int width, int height, CompletionItem item, boolean isBestMatch, boolean isSelected) {
        boolean selected = item instanceof GrammarCompletionItem ? isSelected : isBestMatch;
        if (selected) {
            // Clear the background
            g.setColor(selectedBackgroundColor);
            g.fillRect(0, 0, width, height);
            g.setColor(selectedForegroundColor);
            if (item instanceof GrammarCompletionItem) {
                ((GrammarCompletionItem)item).render(this, g, defaultFont, foregroundColor, backgroundColor, selectedForegroundColor, selectedBackgroundColor, width, height, isBestMatch, isSelected);
            } else {
                item.render(g, defaultFont, selectedForegroundColor, selectedBackgroundColor, width, height, isBestMatch);
            }
        } else {
            // Clear the background
            g.setColor(backgroundColor);
            g.fillRect(0, 0, width, height);
            g.setColor(foregroundColor);
            if (item instanceof GrammarCompletionItem) {
                ((GrammarCompletionItem)item).render(this, g, defaultFont, foregroundColor, backgroundColor, selectedForegroundColor, selectedBackgroundColor, width, height, isBestMatch, isSelected);
            } else {
                item.render(g, defaultFont, foregroundColor, backgroundColor, width, height, isBestMatch);
            }
        }
    }

    @Override
    public boolean instantSubstitution(CompletionItem uniqueMatch) {
        if (uniqueMatch instanceof GrammarCompletionItem) {
            return ((GrammarCompletionItem)uniqueMatch).instantSubstitution(component, this);
        } else {
            return uniqueMatch.instantSubstitution(component);
        }
    }

    protected String getCompletionPrefix() {
        TrackingPositionRegion span = query.getApplicableTo();
        if (span != null) {
            VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(getDocument());
            return span.getText(textBuffer.getCurrentSnapshot());
        }

        if(getDocument() instanceof BaseDocument) {
            BaseDocument doc = (BaseDocument)getDocument();
            int caretOffset = getComponent().getSelectionStart();
            try {
                int[] block = Utilities.getIdentifierBlock(doc, caretOffset);
                if (block != null) {
                    if (!query.isExtend()) {
                        block[1] = caretOffset;
                    }
                    return doc.getText(block);
                }
            } catch (BadLocationException ble) {
            }
        }

        return "";
    }

    protected Comparator<CompletionItem> getComparator(int sortType) {
        if (sortType == CompletionResultSet.PRIORITY_SORT_TYPE) {
            return ItemComparator.PRIORITY_COMPARATOR;
        }

        if (sortType == CompletionResultSet.TEXT_SORT_TYPE) {
            return ItemComparator.TEXT_COMPARATOR;
        }

        throw new IllegalArgumentException("Invalid sort type.");
    }

    protected static final int EXACT_CASE_SENSITIVE = 0x0800;
    protected static final int EXACT = 0x0400;
    protected static final int PREFIX_CASE_SENSITIVE = 0x0200;
    protected static final int PREFIX = 0x0100;
    protected static final int SUBSTRING = 0x0080;
    protected static final int WORD = 0x0040;
    protected static final int VALID = 0x0020;
    protected static final int RECENTLY_USED_MASK = 0x001E;
    protected static final int CASE_SENSITIVE = 0x0001;


    protected int getMatchStrength(String evaluatedText, CompletionItem completionItem) {
        MatchResult exact = isExactMatch(evaluatedText, completionItem);
        MatchResult prefix = isPrefixMatch(evaluatedText, completionItem);
        MatchResult substring = isSubstringMatch(evaluatedText, completionItem);
        MatchResult word = isWordBoundaryMatch(evaluatedText, completionItem);
        MatchResult valid = isValidMatch(evaluatedText, completionItem);
        int recent = getRecentlyUsed(evaluatedText, completionItem);

        boolean caseSensitive;
        if (exact != MatchResult.None) {
            caseSensitive = exact == MatchResult.MatchCaseSensitive;
        } else if (prefix != MatchResult.None) {
            caseSensitive = prefix == MatchResult.MatchCaseSensitive;
        } else if (substring != MatchResult.None) {
            caseSensitive = substring == MatchResult.MatchCaseSensitive;
        } else if (word != MatchResult.None) {
            caseSensitive = word == MatchResult.MatchCaseSensitive;
        } else if (valid != MatchResult.None) {
            caseSensitive = valid == MatchResult.MatchCaseSensitive;
        } else {
            caseSensitive = false;
        }

        int strength = 0;

        if (exact == MatchResult.MatchCaseSensitive) {
            strength |= EXACT_CASE_SENSITIVE;
        }

        if (prefix == MatchResult.MatchCaseSensitive) {
            strength |= PREFIX_CASE_SENSITIVE;
        }

        if (exact == MatchResult.Match) {
            strength |= EXACT;
        }

        if (prefix == MatchResult.Match) {
            strength |= PREFIX;
        }

        if (substring != MatchResult.None) {
            strength |= SUBSTRING;
        }

        if (word != MatchResult.None) {
            strength |= WORD;
        }

        if (valid != MatchResult.None) {
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

    protected MatchResult isExactMatch(String evaluatedText, CompletionItem completionItem) {
        String insertText = completionItem.getInsertPrefix().toString();
        if (evaluatedText.equals(insertText)) {
            return MatchResult.MatchCaseSensitive;
        }

        insertText = insertText.toLowerCase(Locale.getDefault());
        evaluatedText = evaluatedText.toLowerCase(Locale.getDefault());
        if (evaluatedText.equals(insertText)) {
            return MatchResult.Match;
        }

        return MatchResult.None;
    }

    protected MatchResult isPrefixMatch(String evaluatedText, CompletionItem completionItem) {
        String insertText = completionItem.getInsertPrefix().toString();
        if (insertText.startsWith(evaluatedText)) {
            return MatchResult.MatchCaseSensitive;
        }

        insertText = insertText.toLowerCase(Locale.getDefault());
        evaluatedText = evaluatedText.toLowerCase(Locale.getDefault());
        if (insertText.startsWith(evaluatedText)) {
            return MatchResult.Match;
        }

        return MatchResult.None;
    }

    public MatchResult isSubstringMatch(String evaluatedText, CompletionItem completionItem) {
        String insertText = completionItem.getInsertPrefix().toString();
        if (insertText.contains(evaluatedText)) {
            return MatchResult.MatchCaseSensitive;
        }

        insertText = insertText.toLowerCase(Locale.getDefault());
        evaluatedText = evaluatedText.toLowerCase(Locale.getDefault());
        if (insertText.contains(evaluatedText)) {
            return MatchResult.Match;
        }

        return MatchResult.None;
    }

    public MatchResult isWordBoundaryMatch(String evaluatedText, CompletionItem completionItem) {
        return MatchResult.None;
    }

    public MatchResult isValidMatch(String evaluatedText, CompletionItem completionItem) {
        return completionItem.getSortPriority() < 0 ? MatchResult.Match : MatchResult.None;
    }

    public int getRecentlyUsed(String evaluatedText, CompletionItem completionItem) {
        return getRecentCompletionWeight(completionItem.getInsertPrefix().toString(), recentCompletionsCollator);
    }

    public enum MatchResult {
        None,
        Match,
        MatchCaseSensitive,
    }

    protected static class ItemComparator extends CompletionItemComparator {
        protected static final Comparator<CompletionItem> PRIORITY_COMPARATOR =
            new ItemComparator(new PriorityComparator(), new ItemTextComparator());
        protected static final Comparator<CompletionItem> TEXT_COMPARATOR =
            new ItemComparator(new ItemTextComparator(), new PriorityComparator());

        public ItemComparator(@NonNull Comparator<CompletionItem> primaryComparator,
                              @NonNull Comparator<CompletionItem> secondaryComparator) {
            super(primaryComparator, secondaryComparator);
        }

        protected static class ItemTextComparator extends TextComparator {
            private final Collator collator;

            public ItemTextComparator() {
                collator = Collator.getInstance(Locale.getDefault());
                collator.setDecomposition(Collator.FULL_DECOMPOSITION);
                collator.setStrength(Collator.SECONDARY);
            }

            @Override
            protected int compareText(CharSequence text1, CharSequence text2) {
                if (text1 == null) {
                    text1 = ""; //NOI18N
                }

                if (text2 == null) {
                    text2 = ""; //NOI18N
                }

                return collator.compare(text1.toString(), text2.toString());
            }
        }
    }

//    public static final class AggregateComparator<T> implements Comparator<T> {
//        private final Comparator<T>[] comparators;
//
//        public AggregateComparator(Comparator<T>... comparators) {
//            this.comparators = comparators.clone();
//        }
//
//        @Override
//        public int compare(T o1, T o2) {
//            if (o1 == o2) {
//                return 0;
//            }
//
//            for (Comparator<T> comparator : comparators) {
//                int result = comparator.compare(o1, o2);
//                if (result != 0) {
//                    return result;
//                }
//            }
//
//            return 0;
//        }
//    }
//
//    protected static class ExactMatchComparator implements Comparator<CompletionItem> {
//
//        @Override
//        public int compare(CompletionItem o1, CompletionItem o2) {
//            throw new UnsupportedOperationException("Not supported yet.");
//        }
//
//    }
}
