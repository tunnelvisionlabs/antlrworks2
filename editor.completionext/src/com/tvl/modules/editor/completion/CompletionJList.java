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
import com.tvl.spi.editor.completion.CompletionItem;
import com.tvl.spi.editor.completion.LazyCompletionItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

/**
* @author Miloslav Metelka, Dusan Balek
* @version 1.00
*/

@NbBundle.Messages({
    "completion-please-wait=Please wait...",
    "ACSN_CompletionView=Code Completion",
    "ACSD_CompletionView=Code Completion Window",
    "# {0} - selected completion item",
    "ACSN_CompletionView_SelectedItem=Selected code completion item {0}",
    "ACSN_CompletionView_NoSelectedItem=No selection",
})
public class CompletionJList extends JList<Object> {
    private static final Object COMPLETION_CONTROLLER_PROPERTY = CompletionJList.class.getName() + ".controller";
    private static final Object COMPLETION_DATA_PROPERTY = CompletionJList.class.getName() + ".data";

    private static final int DARKER_COLOR_COMPONENT = 5;

    private final RenderComponent renderComponent;
    
    private Graphics cellPreferredSizeGraphics;

    private int fixedItemHeight;
    private int maxVisibleRowCount;
    private WeakReference<JTextComponent> editorComponent;
    private int smartIndex;
    /** The current completion controller. */
    private WeakReference<CompletionController> controller;
    /** <code>true</code> if the best match is selected, otherwise <code>false</code>. */
    private boolean isSelected;
    private boolean preventSelection;
    
    public CompletionJList(int maxVisibleRowCount, MouseListener mouseListener, JTextComponent editorComponent) {
        this.maxVisibleRowCount = maxVisibleRowCount;
        this.editorComponent = new WeakReference<>(editorComponent);
        addMouseListener(mouseListener);
        setFont(editorComponent.getFont());
        setLayoutOrientation(JList.VERTICAL);
        setFixedCellHeight(fixedItemHeight = Math.max(CompletionLayout.COMPLETION_ITEM_HEIGHT, getFontMetrics(getFont()).getHeight()));
        setModel(new Model(Collections.EMPTY_LIST));
        setFocusable(false);

        renderComponent = new RenderComponent();
        setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        setCellRenderer(new ListCellRenderer<Object>() {
            private final ListCellRenderer<Object> defaultRenderer = new DefaultListCellRenderer();

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isBestMatch, boolean cellHasFocus) {
                if( value instanceof CompletionItem ) {
                    CompletionItem item = (CompletionItem)value;
                    renderComponent.setItem(item);
                    renderComponent.setSelected(isBestMatch, isBestMatch && CompletionJList.this.isSelected);
                    renderComponent.setSeparator(smartIndex > 0 && smartIndex == index);
                    Color bgColor = list.getBackground();
                    Color bgSelectedColor = list.getSelectionBackground();
                    Color fgColor = list.getForeground();
                    Color fgSelectedColor = list.getSelectionForeground();
                    if ((index % 2) == 0) { // every second item slightly different
                        bgColor = new Color(
                                Math.abs(bgColor.getRed() - DARKER_COLOR_COMPONENT),
                                Math.abs(bgColor.getGreen() - DARKER_COLOR_COMPONENT),
                                Math.abs(bgColor.getBlue() - DARKER_COLOR_COMPONENT)
                        );
                    }

                    renderComponent.setColors(fgColor, bgColor, fgSelectedColor, bgSelectedColor);
                    // quick check Component.setBackground() always fires change
                    if (renderComponent.getBackground() != bgColor) {
                        renderComponent.setBackground(bgColor);
                    }
                    if (renderComponent.getForeground() != fgColor) {
                        renderComponent.setForeground(fgColor);
                    }
                    return renderComponent;

                } else {
                    return defaultRenderer.getListCellRendererComponent( list, value, index, isBestMatch, cellHasFocus);
                }
            }
        });
        getAccessibleContext().setAccessibleName(Bundle.ACSN_CompletionView());
        getAccessibleContext().setAccessibleDescription(Bundle.ACSD_CompletionView());
    }

    public boolean isPreventSelection() {
        return preventSelection;
    }

    public void setPreventSelection(boolean preventSelection) {
        this.preventSelection = preventSelection;
    }

    public @Override void paint(Graphics g) {
        Object value = (Map)(Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints")); //NOI18N
        Map<?, ?> renderingHints = (value instanceof Map) ? (Map<?, ?>)value : null;
        if (renderingHints != null && g instanceof Graphics2D) {
            Graphics2D g2d = (Graphics2D) g;
            RenderingHints oldHints = g2d.getRenderingHints();
            g2d.addRenderingHints(renderingHints);
            try {
                super.paint(g2d);
            } finally {
                g2d.setRenderingHints(oldHints);
            }
        } else {
            super.paint(g);
        }
    }
    
    void setData(List<?> data, @NonNull CompletionController controller) {
        smartIndex = -1;
        this.controller = new WeakReference<>(controller);
        // since this.controller is a weak reference, add a strong reference to
        // the editor component
        editorComponent.get().putClientProperty(COMPLETION_CONTROLLER_PROPERTY, controller);
        editorComponent.get().putClientProperty(COMPLETION_DATA_PROPERTY, data);
        if (data != null) {
            int itemCount = data.size();
            ListCellRenderer<? super Object> renderer = getCellRenderer();
            int width = 0;
            int maxWidth = getParent().getParent().getMaximumSize().width;
            boolean stop = false;
            for(int index = 0; index < itemCount; index++) {
                Object value = data.get(index);
                if (value instanceof LazyCompletionItem) {
                    maxWidth = (int)(Utilities.getUsableScreenBounds().width * CompletionLayoutPopup.COMPL_COVERAGE);
                }
                Component c = renderer.getListCellRendererComponent(this, value, index, false, false);
                if (c != null) {
                    Dimension cellSize = c.getPreferredSize();
                    if (cellSize.width > width) {
                        width = cellSize.width;
                        if (width >= maxWidth)
                            stop = true;                    
                    }
                }
                if (smartIndex < 0 && value instanceof CompletionItem && ((CompletionItem)value).getSortPriority() >= 0)
                    smartIndex = index;
                if (stop && smartIndex >= 0)
                    break;
            }
            setFixedCellWidth(width);
            ListModel<Object> lm = LazyListModel.<Object>create( new Model(data), CompletionImpl.filter, 1.0d, Bundle.completion_please_wait() ); //NOI18N
            setModel(lm);
            
            if (itemCount > 0) {
                setSelection(0, false);
            }
            int visibleRowCount = Math.min(itemCount, maxVisibleRowCount);
            setVisibleRowCount(visibleRowCount);
        }
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (isVisible()) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override public void run() {
                    updateAccessible();
                }
            });
        } else {
            AccessibleContext editorAC = editorComponent.get().getAccessibleContext();
            if (accessibleLabel != null) {
                editorAC.firePropertyChange(AccessibleContext.ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY, accessibleLabel, null);
                editorAC.firePropertyChange(AccessibleContext.ACCESSIBLE_CHILD_PROPERTY, accessibleLabel, null);
            }
            if (accessibleFakeLabel != null) {
                editorAC.firePropertyChange(AccessibleContext.ACCESSIBLE_CHILD_PROPERTY, accessibleFakeLabel, null);
            }
        }
    }

    @Override
    public void setSelectedIndex(int index) {
        super.setSelectedIndex(index);
        if (isVisible()) {
            updateAccessible();
        }
    }

    @Override
    public void addSelectionInterval(int anchor, int lead) {
        // make sure Ctrl+Click sets isSelected to true
        this.isSelected = true;
        super.addSelectionInterval(anchor, lead);
    }

    @Override
    public void setSelectionInterval(int anchor, int lead) {
        // make sure Click and Shift+Click sets isSelected to true
        this.isSelected = true;
        super.setSelectionInterval(anchor, lead);
    }

    public @NonNull CompletionController.Selection getSelection() {
        return new CompletionController.Selection(getSelectedIndex(), isSelected);
    }

    public void setSelection(@NonNull CompletionController.Selection selection) {
        setSelection(selection.getIndex(), selection.isSelected());
    }

    public void setSelection(int index, boolean isSelected) {
        this.isSelected = isSelected;
        setSelectedIndex(index);
    }
    
    private JLabel accessibleLabel;
    private JLabel accessibleFakeLabel;
    private void updateAccessible() {
        AccessibleContext editorAC = editorComponent.get().getAccessibleContext();
        if (accessibleFakeLabel == null) {
            accessibleFakeLabel = new JLabel(""); //NOI18N
            editorAC.firePropertyChange(AccessibleContext.ACCESSIBLE_CHILD_PROPERTY, null, accessibleFakeLabel);
        }
        JLabel orig = accessibleLabel;
        editorAC.firePropertyChange(AccessibleContext.ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY, accessibleLabel, accessibleFakeLabel);
        Object selectedValue = getSelectedValue();
        if (selectedValue == null) {
            selectedValue = Bundle.ACSN_CompletionView_NoSelectedItem(); //NOI18N
        }
        String accName = selectedValue instanceof Accessible ? ((Accessible) selectedValue).getAccessibleContext().getAccessibleName() : selectedValue.toString();
        accessibleLabel = new JLabel(Bundle.ACSN_CompletionView_SelectedItem(accName)); //NOI18N
        editorAC.firePropertyChange(AccessibleContext.ACCESSIBLE_CHILD_PROPERTY, null, accessibleLabel);
        editorAC.firePropertyChange(AccessibleContext.ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY, accessibleFakeLabel, accessibleLabel);
        if (orig != null) {
            editorAC.firePropertyChange(AccessibleContext.ACCESSIBLE_CHILD_PROPERTY, orig, null);
        }
    }
    
    public void up() {
        int size = getModel().getSize();
        if (size > 0) {
            int idx = (getSelectedIndex() - 1 + size) % size;
            while(idx > 0 && getModel().getElementAt(idx) == null)
                idx--;
            setSelection(idx, true);
            ensureIndexIsVisible(idx);
        }
    }

    public void down() {
        int size = getModel().getSize();
        if (size > 0) {
            int idx = (getSelectedIndex() + 1) % size;
            while(idx < size && getModel().getElementAt(idx) == null)
                idx++;
            if (idx == size)
                idx = 0;
            setSelection(idx, true);
            ensureIndexIsVisible(idx);
        }
    }

    public void pageUp() {
        if (getModel().getSize() > 0) {
            int pageSize = Math.max(getLastVisibleIndex() - getFirstVisibleIndex(), 0);
            int idx = Math.max(getSelectedIndex() - pageSize, 0);
            while(idx > 0 && getModel().getElementAt(idx) == null)
                idx--;
            setSelection(idx, true);
            ensureIndexIsVisible(idx);
        }
    }

    public void pageDown() {
        int size = getModel().getSize();
        if (size > 0) {
            int pageSize = Math.max(getLastVisibleIndex() - getFirstVisibleIndex(), 0);
            int idx = Math.min(getSelectedIndex() + pageSize, size - 1);
            while(idx < size && getModel().getElementAt(idx) == null)
                idx++;
            if (idx == size) {
                idx = Math.min(getSelectedIndex() + pageSize, size - 1);
                while(idx > 0 && getModel().getElementAt(idx) == null)
                    idx--;
            }
            setSelection(idx, true);
            ensureIndexIsVisible(idx);
        }
    }

    public void begin() {
        if (getModel().getSize() > 0) {
            setSelection(0, true);
            ensureIndexIsVisible(0);
        }
    }

    public void end() {
        int size = getModel().getSize();
        if (size > 0) {
            int idx = size - 1;
            while(idx > 0 && getModel().getElementAt(idx) == null)
                idx--;
            setSelection(idx, true);
            ensureIndexIsVisible(idx);
        }
    }

    private static final class Model extends AbstractListModel<Object> {

        WeakReference<List<?>> _data;

        public Model(List<?> data) {
            this._data = new WeakReference<List<?>>(data);
        }
        
        @Override
        public int getSize() {
            List<?> data = this._data.get();
            return data != null ? data.size() : 0;
        }

        @Override
        public Object getElementAt(int index) {
            List<?> data = this._data.get();
            if (data == null) {
                return null;
            }

            return (index >= 0 && index < data.size()) ? data.get(index) : null;
        }
    }
    
    private final class RenderComponent extends JComponent {

        private WeakReference<CompletionItem> item;
        
        private boolean isBestMatch;
        private boolean isSelected;
        private boolean separator;

        private Color fgColor;
        private Color bgColor;
        private Color fgSelectedColor;
        private Color bgSelectedColor;
        
        void setItem(CompletionItem item) {
            this.item = new WeakReference<>(item);
        }
        
        void setSelected(boolean isBestMatch, boolean isSelected) {
            this.isBestMatch = isBestMatch;
            this.isSelected = isSelected;
        }
        
        void setSeparator(boolean separator) {
            this.separator = separator;
        }

        public @Override void paintComponent(Graphics g) {
            // Although the JScrollPane without horizontal scrollbar
            // is explicitly set with a preferred size
            // it does not force its items with the only width into which
            // they can render (and still leaves them with the preferred width
            // of the widest item).
            // Therefore the item's render width is taken from the viewport's width.
            int itemRenderWidth = CompletionJList.this.getParent().getWidth();
            int height = getHeight();

            // Render the item
            controller.get().render(g, CompletionJList.this.getFont(), fgColor, bgColor,
                    fgSelectedColor, bgSelectedColor, itemRenderWidth, getHeight(),
                    item.get(), isBestMatch && !preventSelection, isSelected && !preventSelection);
            
            if (separator) {
                g.setColor(Color.gray);
                g.drawLine(0, 0, itemRenderWidth, 0);
                g.setColor(fgColor);
            }
        }

        public @Override Dimension getPreferredSize() {
            if (cellPreferredSizeGraphics == null) {
                // CompletionJList.this.getGraphics() is null
                cellPreferredSizeGraphics = java.awt.GraphicsEnvironment.
                        getLocalGraphicsEnvironment().getDefaultScreenDevice().
                        getDefaultConfiguration().createCompatibleImage(1, 1).getGraphics();
                assert (cellPreferredSizeGraphics != null);
            }
            return new Dimension(item.get().getPreferredWidth(cellPreferredSizeGraphics, CompletionJList.this.getFont()),
                    fixedItemHeight);
        }

        private void setColors(Color fgColor, Color bgColor, Color fgSelectedColor, Color bgSelectedColor) {
            this.fgColor = fgColor;
            this.bgColor = bgColor;
            this.fgSelectedColor = fgSelectedColor;
            this.bgSelectedColor = bgSelectedColor;
        }

    }

}
