/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
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
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
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
 */

package com.tvl.modules.editor.completion;

import com.tvl.spi.editor.completion.CompletionController;
import com.tvl.spi.editor.completion.CompletionDocumentation;
import com.tvl.spi.editor.completion.CompletionItem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.editor.GuardedDocument;
import org.openide.text.CloneableEditorSupport;
import org.openide.util.NbBundle;

/**
 * Layout of the completion, documentation and tooltip popup windows.
 *
 * @author Dusan Balek, Miloslav Metelka
 */
@NbBundle.Messages({
    "# {0} - Additional items text",
    "# {1} - Shortcut hint",
    "TXT_completion_shortcut_tips={0}Press {1} Again for All Items"
})
public final class CompletionLayout {
    
    public static final int COMPLETION_ITEM_HEIGHT = 16;
    
    /**
     * Visual shift of the completion window to the left
     * so that the text in the rendered completion items.aligns horizontally
     * with the text in the document.
     */
    private static final int COMPLETION_ANCHOR_HORIZONTAL_SHIFT = 22;
    
    /**
     * Gap between caret and the displayed popup.
     */
    static final int POPUP_VERTICAL_GAP = 1;

    private Reference<JTextComponent> editorComponentRef;

    private final CompletionPopup completionPopup;
    private final DocPopup docPopup;
    private final TipPopup tipPopup;
    
    private final List<CompletionLayoutPopup> visiblePopups;
    
    @SuppressWarnings("LeakingThisInConstructor")
    CompletionLayout() {
        completionPopup = new CompletionPopup();
        completionPopup.setLayout(this);
        completionPopup.setPreferDisplayAboveCaret(false);
        docPopup = new DocPopup();
        docPopup.setLayout(this);
        docPopup.setPreferDisplayAboveCaret(false);
        tipPopup = new TipPopup();
        tipPopup.setLayout(this);
        tipPopup.setPreferDisplayAboveCaret(true);
        visiblePopups = new ArrayList<>();
    }
    
    public JTextComponent getEditorComponent() {
        return (editorComponentRef != null)
	    ? editorComponentRef.get()
	    : null;
    }

    public void setEditorComponent(JTextComponent editorComponent) {
        hideAll();
        this.editorComponentRef = new WeakReference<>(editorComponent);
    }

    private void hideAll() {
        completionPopup.hide();
        docPopup.hide();
        tipPopup.hide();
        visiblePopups.clear();
    }

    public void showCompletion(List<?> data, List<? extends CompletionItem> declarationData, String title, int anchorOffset,
    ListSelectionListener listSelectionListener, String additionalItemsText,
    String shortcutHint, CompletionController controller,
    CompletionController.Selection selection) {
        completionPopup.show(data, declarationData, title, anchorOffset, listSelectionListener, additionalItemsText, shortcutHint, controller, selection);
        if (!visiblePopups.contains(completionPopup))
            visiblePopups.add(completionPopup);
    }
    
    public boolean hideCompletion() {
        if (completionPopup.isVisible()) {
            completionPopup.hide();
            completionPopup.completionScrollPane = null;
            visiblePopups.remove(completionPopup);
            return true;
        } else { // not visible
            return false;
        }
    }
    
    public boolean isCompletionVisible() {
        return completionPopup.isVisible();
    }
    
    public @CheckForNull SelectedCompletionItem getSelectedCompletionItem() {
        return completionPopup.getSelectedCompletionItem();
    }
    
    public int getSelectedIndex() {
        return completionPopup.getSelectedIndex();
    }
    
    public void processKeyEvent(KeyEvent evt) {
        for (int i = visiblePopups.size() - 1; i >= 0; i--) {
            CompletionLayoutPopup popup = visiblePopups.get(i);
            popup.processKeyEvent(evt);
            if (evt.isConsumed())
                return;
        }
    }

    public void showDocumentation(CompletionDocumentation doc, int anchorOffset) {
        docPopup.show(doc, anchorOffset);
        if (!visiblePopups.contains(docPopup))
            visiblePopups.add(docPopup);
    }
    
    public boolean hideDocumentation() {
        if (docPopup.isVisible()) {
            docPopup.getDocumentationScrollPane().setData(null);
            docPopup.clearHistory();
            docPopup.hide();
            visiblePopups.remove(docPopup);
            return true;
        } else { // not visible
            return false;
        }
    }
    
    public boolean isDocumentationVisible() {
        return docPopup.isVisible();
    }
    
    public void clearDocumentationHistory() {
        docPopup.clearHistory();
    }
    
    public void showToolTip(JToolTip toolTip, int anchorOffset) {
        tipPopup.show(toolTip, anchorOffset);
        if (!visiblePopups.contains(tipPopup))
            visiblePopups.add(tipPopup);
    }
    
    public boolean hideToolTip() {
        if (tipPopup.isVisible()) {
            tipPopup.hide();
            visiblePopups.remove(tipPopup);
            return true;
        } else { // not visible
            return false;
        }
    }
    
    public boolean isToolTipVisible() {
        return tipPopup.isVisible();
    }

    /**
     * Layout either of the copmletion, documentation or tooltip popup.
     * <br>
     * This method can be called recursively to update other popups
     * once certain popup was updated.
     *
     * <p>
     * The rules for the displayment are the following:
     * <ul>
     *  <li> The tooltip popup should be above caret if there is enough space.
     *  <li> The completion popup should be above caret if there is enough space
     *       and the tooltip window is not displayed.
     *  <li> If both tooltip and completion popups are visible then vertically
     *       each should be on opposite side of the anchor bounds (caret).
     *  <li> Documentation should be preferrably shrinked if there is not enough
     *       vertical space.
     *  <li> Documentation anchoring should be aligned with completion.
     * </ul>
     */
    void updateLayout(CompletionLayoutPopup popup) {
        // Make sure the popup returns its natural preferred size
        popup.resetPreferredSize();

        if (popup == completionPopup) { // completion popup
            if (isToolTipVisible()) {
                // Display on opposite side than tooltip
                boolean wantAboveCaret = !tipPopup.isDisplayAboveCaret();
                if (completionPopup.isEnoughSpace(wantAboveCaret)) {
                    completionPopup.showAlongAnchorBounds(wantAboveCaret);
                } else { // not enough space -> show on same side
                    Rectangle occupiedBounds = popup.getAnchorOffsetBounds();
                    occupiedBounds = tipPopup.unionBounds(occupiedBounds);
                    completionPopup.showAlongOccupiedBounds(occupiedBounds,
                            tipPopup.isDisplayAboveCaret());
                }
                
            } else { // tooltip not visible
                popup.showAlongAnchorBounds();
            }
            
            // Update docPopup layout if necessary
            if (docPopup.isVisible()
                && (docPopup.isOverlapped(popup) || docPopup.isOverlapped(tipPopup)
                    || docPopup.getAnchorOffset() != completionPopup.getAnchorOffset()
                    || !docPopup.isShowRetainedPreferredSize())
            ) {
                updateLayout(docPopup);
            }
            
        } else if (popup == docPopup) { // documentation popup
            if (isCompletionVisible()) {
                // Documentation must sync anchoring with completion
                popup.setAnchorOffset(completionPopup.getAnchorOffset());
            }
            
            Rectangle occupiedBounds = popup.getAnchorOffsetBounds();
            occupiedBounds = tipPopup.unionBounds(completionPopup.unionBounds(occupiedBounds));

            if(CompletionSettings.getInstance(getEditorComponent()).documentationPopupNextToCC()) {
                docPopup.showAlongOrNextOccupiedBounds(completionPopup.getPopupBounds(), occupiedBounds);
            } else {
                docPopup.showAlongOccupiedBounds(occupiedBounds);
            }


        } else if (popup == tipPopup) { // tooltip popup
            popup.showAlongAnchorBounds(); // show possibly above the caret
            if (completionPopup.isOverlapped(popup) || docPopup.isOverlapped(popup)) {
                // docPopup layout will be handled as part of completion popup layout
                updateLayout(completionPopup);
            }
        }
    }
    
    CompletionPopup testGetCompletionPopup() {
        return completionPopup;
    }
    
     void repaintCompletionView() {
        assert EventQueue.isDispatchThread();
        JComponent completionView = completionPopup.completionScrollPane;
        if(completionView != null && completionView.isVisible()) {
            completionView.repaint();
        }
    }
    
    private static final class CompletionPopup extends CompletionLayoutPopup {

        private JPanel stickyItemsPanel;
        private CompletionJList stickyItemsList;

        private CompletionScrollPane completionScrollPane;
        
        public void show(List<?> data, List<? extends CompletionItem> declarationData, String title, int anchorOffset,
        ListSelectionListener listSelectionListener, String additionalItemsText,
        String shortcutHint, final CompletionController controller,
        CompletionController.Selection selection) {
            
	    JTextComponent editorComponent = getEditorComponent();
	    if (editorComponent == null) {
		return;
	    }

            Dimension lastSize;
            int lastAnchorOffset = getAnchorOffset();

            if (isVisible() && ((getContentComponent() == completionScrollPane)^(shortcutHint != null))) {
                lastSize = getContentComponent().getSize();
                resetPreferredSize();

            } else { // not yet visible => create completion scrollpane
                lastSize = new Dimension(0, 0); // no last size => use (0,0)

                stickyItemsPanel = new JPanel();
                stickyItemsPanel.setLayout(new BorderLayout(0, 0));
                stickyItemsPanel.setBorder(new LineBorder(Color.black, 1));
                stickyItemsList = new CompletionJList(1, new MouseAdapter() {}, editorComponent);
                stickyItemsList.setPreventSelection(true);
                stickyItemsPanel.add(stickyItemsList, BorderLayout.CENTER);

                completionScrollPane = new CompletionScrollPane(
                    editorComponent, listSelectionListener,
                    new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent evt) {
			    JTextComponent c = getEditorComponent();
                            if (SwingUtilities.isLeftMouseButton(evt)) {
                                if (c != null && evt.getClickCount() == 2 ) {
                                    SelectedCompletionItem selectedItem
                                            = completionScrollPane.getSelectedCompletionItem();
                                    if (selectedItem != null) {
                                        Document doc = c.getDocument();
                                        if (doc instanceof GuardedDocument && ((GuardedDocument)doc).isPosGuarded(c.getSelectionEnd())) {
                                            Toolkit.getDefaultToolkit().beep();
                                        } else {
                                            LogRecord r = new LogRecord(Level.FINE, "COMPL_MOUSE_SELECT"); // NOI18N
                                            r.setParameters(new Object[] { null, completionScrollPane.getSelectedIndex(), selectedItem.getClass().getSimpleName()});
                                            CompletionImpl.uilog(r);
                                            CompletionImpl.sendUndoableEdit(doc, CloneableEditorSupport.BEGIN_COMMIT_GROUP);
                                            try {
                                                controller.defaultAction(selectedItem.getItem(), selectedItem.isSelected());
                                            } finally {
                                                CompletionImpl.sendUndoableEdit(doc, CloneableEditorSupport.END_COMMIT_GROUP);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                );

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.add(stickyItemsPanel, BorderLayout.NORTH);
                panel.add(completionScrollPane, BorderLayout.CENTER);
                if (shortcutHint != null) {
                    JLabel label = new JLabel();
                    label.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.white),
                            BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.gray), BorderFactory.createEmptyBorder(2, 2, 2, 2))));
                    label.setFont(label.getFont().deriveFont((float)label.getFont().getSize() - 2));
                    label.setHorizontalAlignment(SwingConstants.RIGHT);
                    label.setText(Bundle.TXT_completion_shortcut_tips(additionalItemsText, shortcutHint)); //NOI18N
                    panel.add(label, BorderLayout.SOUTH);
                }
                setContentComponent(panel);
            }
            // Set the new data
            getPreferredSize();
            stickyItemsList.setData(declarationData, controller);
            stickyItemsPanel.setVisible(!declarationData.isEmpty());
            completionScrollPane.setData(data, title, controller, selection);
            setAnchorOffset(anchorOffset);

            Dimension prefSize = getPreferredSize();

            boolean changePopupSize;
            if (isVisible()) {
                changePopupSize = (prefSize.height != lastSize.height)
                        || (prefSize.width != lastSize.width)
                        || anchorOffset != lastAnchorOffset;

            } else { // not visible yet
                changePopupSize = true;
            }

            if (changePopupSize) {
                // Do not change the popup's above/below caret positioning
                // when the popup is already displayed
                getLayout().updateLayout(this);
                
            } // otherwise present popup size will be retained
        }
        
        public @CheckForNull SelectedCompletionItem getSelectedCompletionItem() {
            return isVisible() ? completionScrollPane.getSelectedCompletionItem() : null;
        }

        public int getSelectedIndex() {
            return isVisible() ? completionScrollPane.getSelectedIndex() : -1;
        }

        @Override
        public void processKeyEvent(KeyEvent evt) {
            if (isVisible()) {
                Object actionMapKey = completionScrollPane.getInputMap().get(
                        KeyStroke.getKeyStrokeForEvent(evt));
                
                if (actionMapKey != null) {
                    Action action = completionScrollPane.getActionMap().get(actionMapKey);
                    if (action != null) {
                        action.actionPerformed(new ActionEvent(completionScrollPane, 0, null));
                        evt.consume();
                    }
                }
            }
        }

        @Override
        protected int getAnchorHorizontalShift() {
            return COMPLETION_ANCHOR_HORIZONTAL_SHIFT;
        }

    }
    
    private static final class DocPopup extends CompletionLayoutPopup {
        
        private DocumentationScrollPane getDocumentationScrollPane() {
            return (DocumentationScrollPane)getContentComponent();
        }
        
        protected void show(CompletionDocumentation doc, int anchorOffset) {
	    JTextComponent editorComponent = getEditorComponent();
	    if (editorComponent == null) {
		return;
	    }

            if (!isVisible()) { // documentation already visible
                setContentComponent(new DocumentationScrollPane(editorComponent));
            }
            
            getDocumentationScrollPane().setData(doc);
            
            if (!isVisible()) { // do not check for size as it should remain the same
                // Set anchoring only if not displayed yet because completion
                // may have overriden the anchoring
                setAnchorOffset(anchorOffset);
                getLayout().updateLayout(this);
            } // otherwise leave present doc displayed
        }

        @Override
        public void processKeyEvent(KeyEvent evt) {
            if (isVisible()) {
                Object actionMapKey = getDocumentationScrollPane().getInputMap().get(
                        KeyStroke.getKeyStrokeForEvent(evt));
                
                if (actionMapKey != null) {
                    Action action = getDocumentationScrollPane().getActionMap().get(actionMapKey);
                    if (action != null) {
                        action.actionPerformed(new ActionEvent(getDocumentationScrollPane(), 0, null));
                        evt.consume();
                    }
                }
            }
        }
        
        public void clearHistory() {
            if (isVisible()) {
                getDocumentationScrollPane().clearHistory();
            }
        }

        @Override
        protected int getAnchorHorizontalShift() {
            return COMPLETION_ANCHOR_HORIZONTAL_SHIFT;
        }

    }
    
    private static final class TipPopup extends CompletionLayoutPopup {
        
        protected void show(JToolTip toolTip, int anchorOffset) {
            JComponent lastComponent = null;
            if (isVisible()) { // tooltip already visible
                lastComponent = getContentComponent();
            }
            
            setContentComponent(toolTip);
            setAnchorOffset(anchorOffset);

            // Check whether doc is visible and if so then display
            // on the opposite side
            if (lastComponent != toolTip) {
                getLayout().updateLayout(this);
            }
	}

        @Override
        public void processKeyEvent(KeyEvent evt) {
            if (isVisible()) {
		if (KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0).equals(
			KeyStroke.getKeyStrokeForEvent(evt))
		) {
		    evt.consume();
		    CompletionImpl.get().hideToolTip();
		}
            }
        }
        
    }
    
}
