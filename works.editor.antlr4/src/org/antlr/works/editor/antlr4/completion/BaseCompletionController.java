/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.completion;

import com.tvl.spi.editor.completion.CompletionController;
import com.tvl.spi.editor.completion.CompletionItem;
import com.tvl.spi.editor.completion.CompletionResultSet;
import com.tvl.spi.editor.completion.CompletionTask;
import com.tvl.spi.editor.completion.support.AsyncCompletionQuery;
import com.tvl.spi.editor.completion.support.AsyncCompletionTask;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.Utilities;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class BaseCompletionController implements CompletionController {
    // -J-Dorg.antlr.works.editor.antlr4.completion.BaseCompletionController.level=FINE
    private static final Logger LOGGER = Logger.getLogger(BaseCompletionController.class.getName());

    /** ^([A-Z][a-z]*){2,}$ */
    private static final Pattern WORD_BOUNDARY_PREFIX =
        Pattern.compile("^([A-Z][a-z]*){2,}$");

    /** [A-Z][a-z]* */
    private static final Pattern WORD_PATTERN =
        Pattern.compile("[A-Z][a-z]*");

    private static final List<String> recentCompletions = new ArrayList<>();

    private final JTextComponent component;
    private final List<? extends CompletionTask> tasks;
    private final List<AsyncCompletionQuery> queries;

    public BaseCompletionController(@NonNull JTextComponent component, @NonNull List<? extends CompletionTask> tasks, @NonNull List<Integer> queryTypes) {
        this.component = component;
        this.tasks = tasks;
        this.queries = new ArrayList<>();
        for (CompletionTask task : tasks) {
            if (!(task instanceof AsyncCompletionTask)) {
                continue;
            }

            AsyncCompletionQuery query = ((AsyncCompletionTask)task).getQuery();
            queries.add(query);
        }
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

    protected List<? extends AsyncCompletionQuery> getQueries() {
        return queries;
    }

    protected CompletionMatchEvaluator getCompletionMatchEvaluator(String evaluatedText) {
        return new CompletionMatchEvaluator(evaluatedText);
    }

    public TrackingPositionRegion getApplicableTo() {
        AbstractCompletionQuery appliedQuery = null;
        for (AsyncCompletionQuery query : queries) {
            if (query instanceof AbstractCompletionQuery) {
                TrackingPositionRegion span = ((AbstractCompletionQuery)query).getApplicableTo();
                if (span != null) {
                    return span;
                }
            }
        }

        return null;
    }

    @Override
    public void sortItems(List<? extends CompletionItem> items, int sortType) {
        Collections.sort(items, getComparator(sortType));
    }

    @Override
    public Selection getSelection(List<? extends CompletionItem> items, List<? extends CompletionItem> declarationItems) {
        Comparator<CompletionItem> comparator = getComparator(CompletionResultSet.TEXT_SORT_TYPE);

        String completionPrefix = getCompletionPrefix();
        String evaluatedText = completionPrefix;
        while (true) {
            CompletionItem bestMatch = null;
            int bestMatchValue = 0;
            int prefixMatch = 0;
            CompletionMatchEvaluator evaluator = getCompletionMatchEvaluator(evaluatedText);
            for (CompletionItem item : items) {
                int matchValue = evaluator.getMatchStrength(item);
                if (matchValue > 0) {
                    if ((matchValue & (CompletionMatchEvaluator.PREFIX_CASE_SENSITIVE | CompletionMatchEvaluator.PREFIX)) != 0) {
                        prefixMatch++;
                    }

                    boolean improved = matchValue > bestMatchValue
                        || (matchValue == bestMatchValue && comparator.compare(item, bestMatch) < 0);

                    if (improved) {
                        bestMatch = item;
                        bestMatchValue = matchValue;
                    }
                }
            }

            if (bestMatch != null) {
                int index = items.indexOf(bestMatch);
                boolean selected =
                    declarationItems.isEmpty()
                    && (!(bestMatch instanceof AbstractCompletionItem) || ((AbstractCompletionItem)bestMatch).allowInitialSelection())
                    && evaluatedText != null && !evaluatedText.isEmpty();

                boolean unique = !completionPrefix.isEmpty()
                    && (prefixMatch == 1)
                    && evaluatedText.length() == completionPrefix.length();

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
        if (bestMatch instanceof AbstractCompletionItem) {
            ((AbstractCompletionItem)bestMatch).defaultAction(component, this, isSelected);
        } else {
            bestMatch.defaultAction(component);
        }
    }

    @Override
    public void processKeyEvent(KeyEvent evt, CompletionItem bestMatch, boolean isSelected) {
        if (bestMatch instanceof AbstractCompletionItem) {
            ((AbstractCompletionItem)bestMatch).processKeyEvent(evt, this, isSelected);
        } else {
            bestMatch.processKeyEvent(evt);
        }
    }

    @Override
    public void render(Graphics g, Font defaultFont, Color foregroundColor, Color backgroundColor, Color selectedForegroundColor, Color selectedBackgroundColor, int width, int height, CompletionItem item, boolean isBestMatch, boolean isSelected) {
        boolean selected = item instanceof AbstractCompletionItem ? isSelected : isBestMatch;
        if (selected) {
            // Clear the background
            g.setColor(selectedBackgroundColor);
            g.fillRect(0, 0, width, height);
            g.setColor(selectedForegroundColor);
            if (item instanceof AbstractCompletionItem) {
                ((AbstractCompletionItem)item).render(this, g, defaultFont, foregroundColor, backgroundColor, selectedForegroundColor, selectedBackgroundColor, width, height, isBestMatch, isSelected);
            } else {
                item.render(g, defaultFont, selectedForegroundColor, selectedBackgroundColor, width, height, isBestMatch);
            }
        } else {
            // Clear the background
            g.setColor(backgroundColor);
            g.fillRect(0, 0, width, height);
            g.setColor(foregroundColor);
            if (item instanceof AbstractCompletionItem) {
                ((AbstractCompletionItem)item).render(this, g, defaultFont, foregroundColor, backgroundColor, selectedForegroundColor, selectedBackgroundColor, width, height, isBestMatch, isSelected);
            } else {
                item.render(g, defaultFont, foregroundColor, backgroundColor, width, height, isBestMatch);
            }
        }
    }

    @Override
    public boolean instantSubstitution(CompletionItem uniqueMatch) {
        if (uniqueMatch instanceof AbstractCompletionItem) {
            return ((AbstractCompletionItem)uniqueMatch).instantSubstitution(component, this);
        } else {
            return uniqueMatch.instantSubstitution(component);
        }
    }

    protected @NonNull String getCompletionPrefix() {
        TrackingPositionRegion span = null;
        AbstractCompletionQuery appliedQuery = null;
        for (AsyncCompletionQuery query : queries) {
            if (query instanceof AbstractCompletionQuery) {
                span = ((AbstractCompletionQuery)query).getApplicableTo();
                if (span != null) {
                    appliedQuery = (AbstractCompletionQuery)query;
                    break;
                }
            }
        }

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
                    // if appliedQuery is null, then the provider doesn't support
                    // the new API so we use the old expected behavior and do not extend.
                    if (appliedQuery == null || !appliedQuery.isExtend()) {
                        block[1] = caretOffset;
                    }
                    return doc.getText(block);
                }
            } catch (BadLocationException ble) {
                LOGGER.log(Level.WARNING, ble.getMessage(), ble);
            }
        }

        return "";
    }

    protected @NonNull Comparator<CompletionItem> getComparator(int sortType) {
        if (sortType == CompletionResultSet.PRIORITY_SORT_TYPE) {
            return BaseCompletionItemComparator.PRIORITY_COMPARATOR;
        }

        if (sortType == CompletionResultSet.TEXT_SORT_TYPE) {
            return BaseCompletionItemComparator.TEXT_COMPARATOR;
        }

        throw new IllegalArgumentException("Invalid sort type.");
    }

    public static @CheckForNull Pattern getPrefixBoundaryPattern(@NonNull String prefix, boolean caseSensitive) {
        Parameters.notNull("prefix", prefix);

        if (prefix.isEmpty()) {
            return null;
        }

        Matcher matcher = WORD_BOUNDARY_PREFIX.matcher(prefix);
        if (matcher.matches()) {
            StringBuilder pattern = new StringBuilder("^");
            for (Matcher wordMatcher = WORD_PATTERN.matcher(prefix); wordMatcher.find(); ) {
                String group = wordMatcher.group();
                if (caseSensitive) {
                    if (Character.isUpperCase(group.charAt(0))) {
                        pattern.append("(?:\\w*[a-z0-9_])?").append(group.charAt(0));
                    } else if (Character.isLowerCase(group.charAt(0))) {
                        pattern.append("(?:\\w*[0-9_])?").append(group.charAt(0));
                    } else {
                        pattern.append("\\w*").append(Pattern.quote(group.substring(0, 1)));
                    }

                    pattern.append(Pattern.quote(group.substring(1)));
                } else {
                    pattern.append("(?:(?:\\w*[a-z0-9_])?")
                        .append(Character.toUpperCase(group.charAt(0)))
                        .append("|(?:\\w*[0-9_])?")
                        .append(Character.toLowerCase(group.charAt(0)))
                        .append(")");

                    for (int j = 1; j < group.length(); j++) {
                        char ch = group.charAt(j);
                        if (Character.isLetter(ch)) {
                            pattern.append("[")
                                .append(Character.toUpperCase(ch))
                                .append(Character.toLowerCase(ch))
                                .append("]");
                        } else {
                            pattern.append(ch);
                        }
                    }
                }
            }

            pattern.append("\\w*$");
            return Pattern.compile(pattern.toString());
        }

        return null;
    }

    public static @CheckForNull Pattern getLetterOrderPattern(@NonNull String prefix, boolean caseSensitive) {
        Parameters.notNull("prefix", prefix);
        if (prefix.isEmpty()) {
            return null;
        }

        StringBuilder pattern = new StringBuilder();
        for (int i = 0; i < prefix.length(); i++) {
            if (i > 0) {
                pattern.append(".*");
            }

            char ch = prefix.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                pattern.append(ch);
            } else {
                pattern.append('\\').append(ch);
            }
        }

        return Pattern.compile(pattern.toString(), caseSensitive ? 0 : Pattern.CASE_INSENSITIVE);
    }
}
