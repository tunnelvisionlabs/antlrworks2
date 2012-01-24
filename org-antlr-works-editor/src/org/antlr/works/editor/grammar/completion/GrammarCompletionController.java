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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.works.editor.antlr4.completion.CompletionMatchEvaluator;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.Utilities;
import org.netbeans.spi.editor.completion.CompletionController;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class GrammarCompletionController implements CompletionController {

    /** ^([A-Z][a-z]*){2,}$ */
    private static final Pattern WORD_BOUNDARY_PREFIX =
        Pattern.compile("^([A-Z][a-z]*){2,}$");

    /** [A-Z][a-z]* */
    private static final Pattern WORD_PATTERN =
        Pattern.compile("[A-Z][a-z]*");

    private static final List<String> recentCompletions = new ArrayList<String>();

    private final JTextComponent component;
    private final AsyncCompletionTask task;
    private final GrammarCompletionQuery query;

    public GrammarCompletionController(@NonNull JTextComponent component, @NonNull CompletionTask task, int queryType) {
        this.component = component;
        this.task = (AsyncCompletionTask)task;
        this.query = (GrammarCompletionQuery)this.task.getQuery();
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
        Comparator<CompletionItem> comparator = getComparator(CompletionResultSet.TEXT_SORT_TYPE);

        String completionPrefix = getCompletionPrefix();
        String evaluatedText = completionPrefix;
        while (true) {
            CompletionItem bestMatch = null;
            int bestMatchValue = 0;
            int prefixMatch = 0;
            CompletionMatchEvaluator evaluator = new CompletionMatchEvaluator(evaluatedText);
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
                    (!(bestMatch instanceof GrammarCompletionItem) || ((GrammarCompletionItem)bestMatch).allowInitialSelection())
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

    protected @NonNull String getCompletionPrefix() {
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

    protected @NonNull Comparator<CompletionItem> getComparator(int sortType) {
        if (sortType == CompletionResultSet.PRIORITY_SORT_TYPE) {
            return GrammarCompletionItemComparator.PRIORITY_COMPARATOR;
        }

        if (sortType == CompletionResultSet.TEXT_SORT_TYPE) {
            return GrammarCompletionItemComparator.TEXT_COMPARATOR;
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
}
