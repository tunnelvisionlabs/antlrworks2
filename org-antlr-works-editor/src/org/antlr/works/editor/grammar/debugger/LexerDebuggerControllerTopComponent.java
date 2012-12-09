/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.TextUI;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.util.WeakListeners;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
//@ConvertAsProperties(dtd = "-//org.antlr.works.editor.grammar.debugger//LexerDebuggerController//EN",
//                     autostore = false)
//@TopComponent.Description(preferredID = "LexerDebuggerControllerTopComponent",
//                          //iconBase="SET/PATH/TO/ICON/HERE",
//                          persistenceType = TopComponent.PERSISTENCE_ALWAYS)
//@TopComponent.Registration(mode = "rightSlidingSide", openAtStartup = true)
//@ActionID(category = "Window", id = "org.antlr.works.editor.grammar.debugger.LexerDebuggerControllerTopComponent")
//@ActionReference(path = "Menu/Window" /*
// * , position = 333
// */)
//@TopComponent.OpenActionRegistration(displayName = "#CTL_LexerDebuggerControllerAction",
//                                     preferredID = "LexerDebuggerControllerTopComponent")
@Messages({
    "CTL_LexerDebuggerControllerAction=LexerDebuggerController",
    "CTL_LexerDebuggerControllerTopComponent=LexerDebuggerController Window",
    "HINT_LexerDebuggerControllerTopComponent=This is a LexerDebuggerController window"
})
@SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
public final class LexerDebuggerControllerTopComponent extends TopComponent {

    private final PropertyChangeListener editorRegistryListener = new EditorRegistryListener();

    public LexerDebuggerControllerTopComponent() {
        initComponents();
        setName(Bundle.CTL_LexerDebuggerControllerTopComponent());
        setToolTipText(Bundle.HINT_LexerDebuggerControllerTopComponent());
        jSplitPane1.setDividerLocation(0.8);

        EditorRegistry.addPropertyChangeListener(WeakListeners.propertyChange(editorRegistryListener, EditorRegistry.class));
        lstChannels.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList)e.getSource();
                List<Integer> selectedChannels = new ArrayList<Integer>();
                for (Object item : list.getSelectedValues()) {
                    if (item instanceof String) {
                        if ("DEFAULT (0)".equals(item)) {
                            selectedChannels.add(0);
                        } else if ("HIDDEN (99)".equals(item)) {
                            selectedChannels.add(99);
                        } else {
                            assert false : "Unrecognized channel name.";
                        }
                    } else if (item instanceof Integer) {
                        selectedChannels.add((Integer)item);
                    } else {
                        assert false : "Unrecognized channel value.";
                    }
                }

                JTextComponent editor = EditorRegistry.lastFocusedComponent();
                TraceToken[] tokens = getEditorTokens(editor);
                List<TraceToken> selectedTokens = new ArrayList<TraceToken>();
                for (TraceToken token : tokens) {
                    if (selectedChannels.contains(token.getChannel())) {
                        selectedTokens.add(token);
                    }
                }

                if (tokens.length > 0) {
                    editor.getDocument().putProperty(LexerDebuggerEditorKit.PROP_SELECTED_TOKENS, selectedTokens);
                }
            }
        });

        lstTokenTypes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList)e.getSource();
                int[] selectedTypes = list.getSelectedIndices();

                JTextComponent editor = EditorRegistry.lastFocusedComponent();
                TraceToken[] tokens = getEditorTokens(editor);
                List<TraceToken> selectedTokens = new ArrayList<TraceToken>();
                for (TraceToken token : tokens) {
                    if (Arrays.binarySearch(selectedTypes, token.getType()) >= 0) {
                        selectedTokens.add(token);
                    }
                }

                if (tokens.length > 0) {
                    editor.getDocument().putProperty(LexerDebuggerEditorKit.PROP_SELECTED_TOKENS, selectedTokens);
                }
            }
        });

        lstTokens.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList)e.getSource();
                List<TraceToken> selectedTokens = new ArrayList<TraceToken>();
                for (Object value : list.getSelectedValues()) {
                    if (value instanceof TraceToken) {
                        selectedTokens.add((TraceToken)value);
                    }
                }

                JTextComponent editor = EditorRegistry.lastFocusedComponent();
                TraceToken[] tokens = getEditorTokens(editor);
                if (tokens.length > 0) {
                    editor.getDocument().putProperty(LexerDebuggerEditorKit.PROP_SELECTED_TOKENS, selectedTokens);
                }
            }
        });

        lstModes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList)e.getSource();
                int[] selectedModes = list.getSelectedIndices();

                JTextComponent editor = EditorRegistry.lastFocusedComponent();
                TraceToken[] tokens = getEditorTokens(editor);
                List<TraceToken> selectedTokens = new ArrayList<TraceToken>();
                for (TraceToken token : tokens) {
                    if (Arrays.binarySearch(selectedModes, token.getMode()) >= 0) {
                        selectedTokens.add(token);
                    }
                }

                if (tokens.length > 0) {
                    editor.getDocument().putProperty(LexerDebuggerEditorKit.PROP_SELECTED_TOKENS, selectedTokens);
                }
            }
        });

        lstLookahead.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList)e.getSource();
                JTextComponent editor = EditorRegistry.lastFocusedComponent();
                final TraceToken[] tokens = getEditorTokens(editor);
                if (tokens.length == 0) {
                    return;
                }

                boolean showAtnTmp = false;
                boolean showDfaTmp = false;
                for (Object value : list.getSelectedValues()) {
                    if ("ATN".equals(value)) {
                        showAtnTmp = true;
                    } else if ("DFA".equals(value)) {
                        showDfaTmp = true;
                    }
                }

                final boolean showAtn = showAtnTmp;
                final boolean showDfa = showDfaTmp;

                final List<TraceToken> selectedTokens = new ArrayList<TraceToken>();
                if (showAtn || showDfa) {
                    LexerDebuggerEditorKit kit = (LexerDebuggerEditorKit)editor.getUI().getEditorKit(editor);
                    kit.processTrace(editor.getDocument(), new AbstractLexerTraceListener() {
                        int tokenIndex = 0;
                        boolean atn = false;

                        @Override
                        public void matchATN() {
                            atn = true;
                        }

                        @Override
                        public void failOverToATN() {
                            atn = true;
                        }

                        @Override
                        public void emit(int startIndex, int stopIndex, int type, int channel) {
                            if ((atn && showAtn) || (!atn && showDfa)) {
                                selectedTokens.add(tokens[tokenIndex]);
                            }

                            tokenIndex++;
                            atn = false;
                        }
                    });
                }

                editor.getDocument().putProperty(LexerDebuggerEditorKit.PROP_SELECTED_TOKENS, selectedTokens);
            }
        });
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        javax.swing.JTabbedPane jTabbedPane1 = new javax.swing.JTabbedPane();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        lstTokenTypes = new javax.swing.JList();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        lstTokens = new javax.swing.JList();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        lstChannels = new javax.swing.JList();
        javax.swing.JPanel jPanel6 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane5 = new javax.swing.JScrollPane();
        lstModes = new javax.swing.JList();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane4 = new javax.swing.JScrollPane();
        lstLookahead = new javax.swing.JList();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.GridLayout(1, 0));

        jSplitPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jSplitPane1.setDividerLocation(120);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);
        jSplitPane1.setContinuousLayout(true);

        jScrollPane1.setViewportView(lstTokenTypes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(LexerDebuggerControllerTopComponent.class, "LexerDebuggerControllerTopComponent.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jScrollPane3.setViewportView(lstTokens);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(LexerDebuggerControllerTopComponent.class, "LexerDebuggerControllerTopComponent.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jScrollPane2.setViewportView(lstChannels);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(LexerDebuggerControllerTopComponent.class, "LexerDebuggerControllerTopComponent.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        lstModes.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(lstModes);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(LexerDebuggerControllerTopComponent.class, "LexerDebuggerControllerTopComponent.jPanel6.TabConstraints.tabTitle"), jPanel6); // NOI18N

        jScrollPane4.setViewportView(lstLookahead);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(LexerDebuggerControllerTopComponent.class, "LexerDebuggerControllerTopComponent.jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        jSplitPane1.setLeftComponent(jTabbedPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel1);

        add(jSplitPane1);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JList lstChannels;
    private javax.swing.JList lstLookahead;
    private javax.swing.JList lstModes;
    private javax.swing.JList lstTokenTypes;
    private javax.swing.JList lstTokens;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    private class EditorRegistryListener implements PropertyChangeListener {
        private JTextComponent currentComponent;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (EditorRegistry.FOCUS_GAINED_PROPERTY.equals(evt.getPropertyName())) {
                JTextComponent component = EditorRegistry.focusedComponent();
                if (component == currentComponent) {
                    return;
                }

                Document document = component.getDocument();
                String[] tokenNamesArray = (String[])document.getProperty(LexerDebuggerEditorKit.PROP_TOKEN_NAMES);
                String[] modeNamesArray = (String[])document.getProperty(LexerDebuggerEditorKit.PROP_MODE_NAMES);
                final List<String> tokenNames = tokenNamesArray != null ? Arrays.asList(tokenNamesArray) : Collections.<String>emptyList();
                final List<String> modeNames = modeNamesArray != null ? Arrays.asList(modeNamesArray) : Collections.<String>emptyList();

                currentComponent = component;

                lstTokenTypes.setModel(new AbstractListModel() {
                    private final List<String> elements = tokenNames;

                    @Override
                    public int getSize() {
                        return elements.size();
                    }

                    @Override
                    public Object getElementAt(int index) {
                        return elements.get(index);
                    }
                });

                lstTokens.setModel(new AbstractListModel() {
                    private final TraceToken[] elements = getFocusedEditorTokens();

                    @Override
                    public int getSize() {
                        return elements.length;
                    }

                    @Override
                    public Object getElementAt(int index) {
                        return elements[index];
                    }

                });

                lstTokens.setCellRenderer(new TraceTokenListCellRenderer(tokenNames));

                lstChannels.setModel(new AbstractListModel() {
                    private final Object[] elements = { "DEFAULT (0)", "HIDDEN (99)" };

                    @Override
                    public int getSize() {
                        return elements.length;
                    }

                    @Override
                    public Object getElementAt(int index) {
                        return elements[index];
                    }
                });

                lstModes.setModel(new AbstractListModel() {
                    private final List<String> elements = modeNames;

                    @Override
                    public int getSize() {
                        return elements.size();
                    }

                    @Override
                    public Object getElementAt(int index) {
                        return elements.get(index);
                    }
                });

                lstLookahead.setModel(new AbstractListModel() {
                    private final String[] elements = { "ATN", "DFA" };

                    @Override
                    public int getSize() {
                        return elements.length;
                    }

                    @Override
                    public Object getElementAt(int index) {
                        return elements[index];
                    }
                });
            }
        }
    }

    private static TraceToken[] getFocusedEditorTokens() {
        JTextComponent component = EditorRegistry.lastFocusedComponent();
        if (component == null) {
            return new TraceToken[0];
        }

        return getEditorTokens(component);
    }

    private static TraceToken[] getEditorTokens(@NonNull JTextComponent component) {
        TextUI ui = component.getUI();
        if (ui == null) {
            return new TraceToken[0];
        }

        EditorKit kit = ui.getEditorKit(component);
        if (!(kit instanceof LexerDebuggerEditorKit)) {
            return new TraceToken[0];
        }

        Document document = component.getDocument();
        if (document == null) {
            return new TraceToken[0];
        }

        return ((LexerDebuggerEditorKit)kit).getTokens(document);
    }

    private static class TraceTokenListCellRenderer extends DefaultListCellRenderer {
        private final List<String> tokenNames;

        public TraceTokenListCellRenderer(List<String> tokenNames) {
            this.tokenNames = tokenNames;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (tokenNames != null && component instanceof JLabel && value instanceof TraceToken) {
                JLabel label = (JLabel)component;
                TraceToken token = (TraceToken)value;
                label.setText(token.toString(tokenNames));
            }

            return component;
        }

    }
}
