/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2011 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2011 Sun Microsystems, Inc.
 */
package com.tvl.spi.editor.completion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.Utilities;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class BaseCompletionController implements CompletionController {
    private final JTextComponent component;
    private final int queryType;

    /**
     *
     * @param component The active component.
     * @param queryType The query type.
     *
     * @throws NullPointerException if the parameter value is <code>null</code>.
     */
    public BaseCompletionController(@NonNull JTextComponent component,
                                    int queryType) {
        Parameters.notNull("component", component);
        this.component = component;
        this.queryType = queryType;
    }


    /**
     * Gets the active {@link JTextComponent}.
     *
     * @return The active component.
     */
    public @NonNull JTextComponent getComponent() {
        return component;
    }

    /**
     * Gets the active {@link Document}.
     *
     * @return The active document.
     */
    public Document getDocument() {
        return getComponent().getDocument();
    }

    @Override
    public void sortItems(List<? extends CompletionItem> items, int sortType) {
        Collections.sort(items, CompletionItemComparator.get(sortType));
    }

    @Override
    public Selection getSelection(List<? extends CompletionItem> items, List<? extends CompletionItem> declarationItems) {
        String prefix = getCompletionPrefix();
        if (prefix != null && prefix.length() > 0) {
            for (int idx = 0; idx < items.size(); idx++) {
                CompletionItem item = items.get(idx);
                CharSequence text = item.getInsertPrefix();
                if (text != null && text.toString().startsWith(prefix)) {
                    boolean selected = isSelected(items, declarationItems, prefix, idx);
                    boolean unique = isUnique(items, declarationItems, prefix, idx);
                    return new Selection(idx, selected, unique);
                }
            }
        }

        /* This follows existing behavior, which is slightly different from
         * Selection.DEFAULT because this method marks the item as selected.
         */
        return new Selection(0);
    }

    @Override
    public void defaultAction(CompletionItem bestMatch, boolean isSelected) {
        if (isSelected) {
            bestMatch.defaultAction(component);
        }
    }

    @Override
    public void processKeyEvent(KeyEvent evt, CompletionItem bestMatch, boolean isSelected) {
        bestMatch.processKeyEvent(evt);
    }

    @Override
    public void render(Graphics g, Font defaultFont, Color foregroundColor,
    Color backgroundColor, Color selectedForegroundColor,
    Color selectedBackgroundColor, int width, int height, CompletionItem item, boolean isBestMatch, boolean isSelected) {
        if (isBestMatch) {
            // Clear the background
            g.setColor(selectedBackgroundColor);
            g.fillRect(0, 0, width, height);
            g.setColor(selectedForegroundColor);
            item.render(g, defaultFont, selectedForegroundColor, selectedBackgroundColor, width, height, isBestMatch);
        } else {
            // Clear the background
            g.setColor(backgroundColor);
            g.fillRect(0, 0, width, height);
            g.setColor(foregroundColor);
            item.render(g, defaultFont, foregroundColor, backgroundColor, width, height, isBestMatch);
        }
    }

    @Override
    public boolean instantSubstitution(CompletionItem uniqueMatch) {
        return uniqueMatch.instantSubstitution(component);
    }

    /**
     * Gets the current completion prefix. The default implementation returns the
     * text of the current identifier (per {@link Utilities#getIdentifierBlock})
     * up to the location of the caret.
     *
     * @return The completion prefix.
     */
    protected @CheckForNull String getCompletionPrefix() {
        if(getDocument() instanceof BaseDocument) {
            BaseDocument doc = (BaseDocument)getDocument();
            int caretOffset = getComponent().getSelectionStart();
            try {
                int[] block = Utilities.getIdentifierBlock(doc, caretOffset);
                if (block != null) {
                    block[1] = caretOffset;
                    return doc.getText(block);
                }
            } catch (BadLocationException ble) {
            }
        }

        return null;
    }

    /**
     *
     * @param items
     * @param declarationItems
     * @param prefix
     * @param index
     *
     * @return <code>true</code> if the item is selected, otherwise <code>false</code>.
     */
    protected boolean isSelected(@NonNull List<? extends CompletionItem> items,
                                 @NonNull List<? extends CompletionItem> declarationItems,
                                 @NullAllowed String prefix,
                                 int index) {
        return declarationItems.isEmpty();
    }

    /**
     *
     * @param items
     * @param declarationItems
     * @param prefix
     * @param index
     *
     * @return <code>true</code> if the selection is unique, otherwise <code>false</code>.
     */
    protected boolean isUnique(@NonNull List<? extends CompletionItem> items,
                               @NonNull List<? extends CompletionItem> declarationItems,
                               @NullAllowed String prefix,
                               int index) {
        if (items.size() != 1) {
            return false;
        }

        try {
            int caretOffset = getComponent().getSelectionStart();
            int[] block = Utilities.getIdentifierBlock(getComponent(),
                       getComponent().getSelectionStart());
            return block == null || block[1] == caretOffset;
        } catch (BadLocationException ble) {
            return false;
        }
    }
}
