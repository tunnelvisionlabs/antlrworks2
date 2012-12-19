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
import java.util.List;
import org.netbeans.api.annotations.common.NonNull;

/**
 * This interface controls the sorting and selection behavior of the Code
 * Completion drop down.
 *
 * @author Sam Harwell
 */
public interface CompletionController {

    /**
     * Sorts the specified list of CompletionItem items.
     *
     * @param items The list of items to sort.
     * @param sortType The desired sort type, one of
     *  {@link CompletionResultSet#PRIORITY_SORT_TYPE} or
     *  {@link CompletionResultSet#TEXT_SORT_TYPE}.
     */
    void sortItems(@NonNull List<? extends CompletionItem> items, int sortType);

    /**
     * Gets the initial selected item from a list of CompletionItem items shown
     * in a code completion drop down. The initial selection represents a "best
     * match" among the available items.
     *
     * @param items A list of all CompletionItem items shown in the code
     *  completion drop down.
     * @param declarationItems A list of all CompletionItem items shown as new
     *  declarations in the completion drop down.
     *
     * @return A {@link Selection} object containing information about the
     *  initial selection.
     */
    @NonNull Selection getSelection(@NonNull List<? extends CompletionItem> items, @NonNull List<? extends CompletionItem> declarationItems);

    /**
     * Gets invoked when user presses <code>VK_ENTER</code> key
     * or when she double-clicks on this item with the mouse cursor.
     * <br/>
     * This method gets invoked from AWT thread.
     *
     * @param bestMatch non-null completion item for which the action is invoked
     * @param isSelected whether or not the best match is currently selected
     */
    void defaultAction(@NonNull CompletionItem bestMatch, boolean isSelected);

    /**
     * Process the key pressed when this completion item was selected
     * in the completion popup window.
     * <br/>
     * This method gets invoked from AWT thread.
     *
     * @param evt non-null key event of the pressed key. It should be consumed
     *  in case the item is sensitive to the given key. The source of this
     *  event is the text component to which the corresponding action should
     *  be performed.
     * @param bestMatch non-null completion item which is the current best match
     * @param isSelected whether or not the best match is currently selected
     */
    void processKeyEvent(@NonNull KeyEvent evt, @NonNull CompletionItem bestMatch, boolean isSelected);

    /**
     * Render this item into the given graphics.
     *
     * @param g graphics to render the item into.
     * @param defaultFont default font used for rendering.
     * @param foregroundColor foreground color used for rendering.
     * @param backgroundColor color used for background.
     * @param selectedForegroundColor foreground color used for rendering selected items.
     * @param selectedBackgroundColor color used for background selected items.
     * @param width width of the area to render into.
     * @param height height of the are to render into.
     * @param item non-null completion item to render.
     * @param isBestMatch true if this item is the best match.
     * @param isSelected true if this item is selected.
     */
    void render(@NonNull Graphics g, @NonNull Font defaultFont, @NonNull Color foregroundColor,
    @NonNull Color backgroundColor, @NonNull Color selectedForegroundColor,
    @NonNull Color selectedBackgroundColor, int width, int height, @NonNull CompletionItem item,
    boolean isBestMatch, boolean isSelected);

    /**
     * When enabled for the item the instant substitution should process the item
     * in the same way like when the item is displayed and Enter key gets pressed
     * by the user.
     * <br>
     * Instant substitution is invoked when there would be just a single item
     * displayed in the completion popup window.
     * <br>
     * The implementation can invoke the {@link #defaultAction(JTextComponent)}
     * if necessary.
     * <br/>
     * This method gets invoked from AWT thread.
     *
     * @param uniqueMatch non-null unique completion item to perform the instant
     *  substitution.
     * @return <code>true</code> if the instant substitution was successfully done.
     *  <code>false</code> means that the instant substitution should not be done
     *  for this item and the completion item should normally be displayed.
     */
    boolean instantSubstitution(@NonNull CompletionItem uniqueMatch);

    /**
     * Represents an initial selected CompletionItem for code completion. This
     * item can be seen as the best match for a given input.
     */
    public static final class Selection {
        /**
         * The default selected item. The item has index 0 and is not selected
         * or unique.
         */
        public static final Selection DEFAULT = new Selection(0, false, false);

        private final int index;
        private final boolean selected;
        private final boolean unique;

        /**
         * Initializes a Selection with the specified index. The item is selected
         * but not unique.
         *
         * @param index The index of the selected CompletionItem.
         *
         * @throws IllegalArgumentException when the index argument is less than zero.
         */
        public Selection(int index) {
            this(index, true, false);
        }

        /**
         * Initializes a Selection with the specified index and selected state.
         * The item is not unique.
         *
         * @param index The index of the selected CompletionItem.
         * @param selected <code>true</code> if the match is selected, otherwise
         *  <code>false</code>.
         *
         * @throws IllegalArgumentException when the index argument is less than zero.
         */
        public Selection(int index, boolean selected) {
            this(index, selected, false);
        }

        /**
         * Initializes a Selection with the specified index, selected state, and
         * uniqueness.
         *
         * @param index The index of the selected CompletionItem.
         * @param selected <code>true</code> if the match is selected, otherwise
         *  <code>false</code>.
         * @param unique <code>true</code> if the match is unique, otherwise
         *  <code>false</code>.
         * 
         * @throws IllegalArgumentException when the index argument is less than zero.
         */
        public Selection(int index, boolean selected, boolean unique) {
            if (index < 0) {
                throw new IllegalArgumentException("The index cannot be negative."); // NOI18N
            }

            this.index = index;
            this.selected = selected;
            this.unique = unique;
        }

        /**
         *
         * @return The index of the selected CompletionItem.
         */
        public int getIndex() {
            return index;
        }

        /**
         *
         * @return <code>true</code> if the match is selected, otherwise <code>false</code>.
         */
        public boolean isSelected() {
            return selected;
        }

        /**
         *
         * @return <code>true</code> if the match is unique, otherwise <code>false</code>.
         */
        public boolean isUnique() {
            return unique;
        }
    }
}
