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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.TextUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.text.Line;
import org.openide.text.NbDocument;
import org.openide.util.NbBundle.Messages;
import org.openide.util.WeakListeners;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.antlr.works.editor.grammar.debugger//LexerDebuggerController//EN",
                     autostore = false)
@TopComponent.Description(preferredID = "LexerDebuggerControllerTopComponent",
                          //iconBase="SET/PATH/TO/ICON/HERE",
                          persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "rightSlidingSide", openAtStartup = false)
@ActionID(category = "Window", id = "org.antlr.works.editor.grammar.debugger.LexerDebuggerControllerTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_LexerDebuggerControllerAction",
                                     preferredID = "LexerDebuggerControllerTopComponent")
@Messages({
    "CTL_LexerDebuggerControllerAction=Lexer Debugger Controller",
    "CTL_LexerDebuggerControllerTopComponent=Lexer",
    "HINT_LexerDebuggerControllerTopComponent=This is a lexer debugger controller window"
})
public final class LexerDebuggerControllerTopComponent extends TopComponent {

    private final PropertyChangeListener editorRegistryListener = new EditorRegistryListener();

    private static final String defaultChannelText = String.format("DEFAULT (%d)", Lexer.DEFAULT_TOKEN_CHANNEL);
    private static final String hiddenChannelText = String.format("HIDDEN (%d)", Lexer.HIDDEN);

    public LexerDebuggerControllerTopComponent() {
        initComponents();
        setName(Bundle.CTL_LexerDebuggerControllerTopComponent());
        setToolTipText(Bundle.HINT_LexerDebuggerControllerTopComponent());
        jSplitPane1.setDividerLocation(0.8);

        EditorRegistry.addPropertyChangeListener(WeakListeners.propertyChange(editorRegistryListener, EditorRegistry.class));

        lstChannels.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    JList<?> list = (JList<?>)e.getSource();
                    List<Integer> selectedChannels = new ArrayList<>();
                    for (Object item : list.getSelectedValuesList()) {
                        if (item instanceof String) {
                            if (defaultChannelText.equals(item)) {
                                selectedChannels.add(Lexer.DEFAULT_TOKEN_CHANNEL);
                            } else if (hiddenChannelText.equals(item)) {
                                selectedChannels.add(Lexer.HIDDEN);
                            } else {
                                throw new UnsupportedOperationException("unrecognized channel");
                            }
                        } else if (item instanceof Integer) {
                            selectedChannels.add((Integer)item);
                        } else {
                            throw new UnsupportedOperationException("unrecognized channel");
                        }
                    }

                    JTextComponent editor = EditorRegistry.lastFocusedComponent();
                    TraceToken[] tokens = getEditorTokens(editor);
                    if (editor == null || !(editor.getDocument() instanceof StyledDocument)) {
                        return;
                    }

                    StyledDocument document = (StyledDocument)editor.getDocument();
                    for (TraceToken token : tokens) {
                        if (selectedChannels.contains(token.getChannel())) {
                            int index = token.getStartIndex();
                            if (index >= 0 && index <= document.getLength()) {
                                int column = NbDocument.findLineColumn(document, index);
                                NbEditorUtilities.getLine(document, index, true).show(Line.ShowOpenType.OPEN, Line.ShowVisibilityType.FOCUS, column);
                                return;
                            }
                        }
                    }
                }
            }
        });

        lstChannels.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList<?> list = (JList<?>)e.getSource();
                List<Integer> selectedChannels = new ArrayList<>();
                for (Object item : list.getSelectedValuesList()) {
                    if (item instanceof String) {
                        if (defaultChannelText.equals(item)) {
                            selectedChannels.add(Lexer.DEFAULT_TOKEN_CHANNEL);
                        } else if (hiddenChannelText.equals(item)) {
                            selectedChannels.add(Lexer.HIDDEN);
                        } else {
                            throw new UnsupportedOperationException("unrecognized channel");
                        }
                    } else if (item instanceof Integer) {
                        selectedChannels.add((Integer)item);
                    } else {
                        throw new UnsupportedOperationException("unrecognized channel");
                    }
                }

                JTextComponent editor = EditorRegistry.lastFocusedComponent();
                TraceToken[] tokens = getEditorTokens(editor);
                List<TraceToken> selectedTokens = new ArrayList<>();
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

        tblTokenTypes.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    int[] selectedRows = tblTokenTypes.getSelectedRows();
                    BitSet selectedTypes = new BitSet();
                    for (int i : selectedRows) {
                        selectedTypes.set((Integer)tblTokenTypes.getValueAt(i, 2));
                    }

                    JTextComponent editor = EditorRegistry.lastFocusedComponent();
                    if (editor == null || !(editor.getDocument() instanceof StyledDocument)) {
                        return;
                    }

                    StyledDocument document = (StyledDocument)editor.getDocument();
                    TraceToken[] tokens = getEditorTokens(editor);
                    for (TraceToken token : tokens) {
                        if (token.getType() < 0) {
                            continue;
                        }

                        if (selectedTypes.get(token.getType())) {
                            int index = token.getStartIndex();
                            if (index >= 0 && index <= document.getLength()) {
                                int column = NbDocument.findLineColumn(document, index);
                                NbEditorUtilities.getLine(document, index, true).show(Line.ShowOpenType.OPEN, Line.ShowVisibilityType.FOCUS, column);
                                return;
                            }
                        }
                    }
                }
            }
        });

        tblTokenTypes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] selectedRows = tblTokenTypes.getSelectedRows();
                BitSet selectedTypes = new BitSet();
                for (int i : selectedRows) {
                    selectedTypes.set((Integer)tblTokenTypes.getValueAt(i, 2));
                }

                JTextComponent editor = EditorRegistry.lastFocusedComponent();
                TraceToken[] tokens = getEditorTokens(editor);
                List<TraceToken> selectedTokens = new ArrayList<>();
                for (TraceToken token : tokens) {
                    if (token.getType() < 0) {
                        continue;
                    }

                    if (selectedTypes.get(token.getType())) {
                        selectedTokens.add(token);
                    }
                }

                if (tokens.length > 0) {
                    editor.getDocument().putProperty(LexerDebuggerEditorKit.PROP_SELECTED_TOKENS, selectedTokens);
                }
            }
        });

        lstTokens.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    JList<?> list = (JList<?>)e.getSource();
                    JTextComponent editor = EditorRegistry.lastFocusedComponent();
                    if (editor == null || !(editor.getDocument() instanceof StyledDocument)) {
                        return;
                    }

                    StyledDocument document = (StyledDocument)editor.getDocument();
                    for (Object value : list.getSelectedValuesList()) {
                        if (value instanceof TraceToken) {
                            TraceToken token = (TraceToken)value;
                            int index = token.getStartIndex();
                            if (index >= 0 && index <= document.getLength()) {
                                int column = NbDocument.findLineColumn(document, index);
                                NbEditorUtilities.getLine(document, index, true).show(Line.ShowOpenType.OPEN, Line.ShowVisibilityType.FOCUS, column);
                                return;
                            }
                        }
                    }
                }
            }
        });

        lstTokens.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList<?> list = (JList<?>)e.getSource();
                List<TraceToken> selectedTokens = new ArrayList<>();
                for (Object value : list.getSelectedValuesList()) {
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

        lstModes.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    JList<?> list = (JList<?>)e.getSource();
                    int[] selectedModes = list.getSelectedIndices();

                    JTextComponent editor = EditorRegistry.lastFocusedComponent();
                    TraceToken[] tokens = getEditorTokens(editor);
                    if (editor == null || !(editor.getDocument() instanceof StyledDocument)) {
                        return;
                    }

                    StyledDocument document = (StyledDocument)editor.getDocument();
                    for (TraceToken token : tokens) {
                        if (Arrays.binarySearch(selectedModes, token.getMode()) >= 0) {
                            int index = token.getStartIndex();
                            if (index >= 0 && index <= document.getLength()) {
                                int column = NbDocument.findLineColumn(document, index);
                                NbEditorUtilities.getLine(document, index, true).show(Line.ShowOpenType.OPEN, Line.ShowVisibilityType.FOCUS, column);
                                return;
                            }
                        }
                    }
                }
            }
        });

        lstModes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList<?> list = (JList<?>)e.getSource();
                int[] selectedModes = list.getSelectedIndices();

                JTextComponent editor = EditorRegistry.lastFocusedComponent();
                TraceToken[] tokens = getEditorTokens(editor);
                List<TraceToken> selectedTokens = new ArrayList<>();
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
                JList<?> list = (JList<?>)e.getSource();
                JTextComponent editor = EditorRegistry.lastFocusedComponent();

                boolean showAtnTmp = false;
                boolean showDfaTmp = false;
                for (Object value : list.getSelectedValuesList()) {
                    if ("ATN".equals(value)) {
                        showAtnTmp = true;
                    } else if ("DFA".equals(value)) {
                        showDfaTmp = true;
                    }
                }

                final boolean showAtn = showAtnTmp;
                final boolean showDfa = showDfaTmp;

                TupleIntInt[] highlightedCharacters;
                if (showAtn) {
                    TupleIntInt[] atnCharacters = getEditorAtnCharacters(editor);
                    highlightedCharacters = atnCharacters;
                } else if (showDfa) {
                    TupleIntInt[] dfaCharacters = getEditorDfaCharacters(editor);
                    highlightedCharacters = dfaCharacters;
                } else {
                    highlightedCharacters = new TupleIntInt[0];
                }

                editor.getDocument().putProperty(LexerDebuggerEditorKit.PROP_SELECTED_CHARACTERS, highlightedCharacters);
            }
        });
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings({"rawtypes", "unchecked", "Convert2Diamond"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        javax.swing.JTabbedPane jTabbedPane1 = new javax.swing.JTabbedPane();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tblTokenTypes = new javax.swing.JTable();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        lstTokens = new javax.swing.JList<TraceToken>();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        lstChannels = new javax.swing.JList<Object>();
        javax.swing.JPanel jPanel6 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane5 = new javax.swing.JScrollPane();
        lstModes = new javax.swing.JList<String>();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane4 = new javax.swing.JScrollPane();
        lstLookahead = new javax.swing.JList<String>();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.GridLayout(1, 0));

        jSplitPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jSplitPane1.setDividerLocation(120);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);
        jSplitPane1.setContinuousLayout(true);

        tblTokenTypes.setAutoCreateRowSorter(true);
        tblTokenTypes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Literal", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTokenTypes);
        if (tblTokenTypes.getColumnModel().getColumnCount() > 0) {
            tblTokenTypes.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(LexerDebuggerControllerTopComponent.class, "LexerDebuggerControllerTopComponent.tblTokenTypes.columnModel.title0")); // NOI18N
            tblTokenTypes.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(LexerDebuggerControllerTopComponent.class, "LexerDebuggerControllerTopComponent.tblTokenTypes.columnModel.title1")); // NOI18N
            tblTokenTypes.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(LexerDebuggerControllerTopComponent.class, "LexerDebuggerControllerTopComponent.tblTokenTypes.columnModel.title2")); // NOI18N
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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
            .addGap(0, 505, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
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
            .addGap(0, 505, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
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
            .addGap(0, 505, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
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
            .addGap(0, 505, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
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
    private javax.swing.JList<Object> lstChannels;
    private javax.swing.JList<String> lstLookahead;
    private javax.swing.JList<String> lstModes;
    private javax.swing.JList<TraceToken> lstTokens;
    private javax.swing.JTable tblTokenTypes;
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
                Vocabulary vocabulary = (Vocabulary)document.getProperty(LexerDebuggerEditorKit.PROP_VOCABULARY);
                String[] modeNamesArray = (String[])document.getProperty(LexerDebuggerEditorKit.PROP_MODE_NAMES);
                List<String> modeNames = modeNamesArray != null ? Arrays.asList(modeNamesArray) : Collections.<String>emptyList();
                if (vocabulary == null) {
                    LexerInterpreterData lexerInterpreterData = (LexerInterpreterData)document.getProperty(LexerDebuggerEditorKit.PROP_LEXER_INTERP_DATA);
                    if (lexerInterpreterData != null) {
                        vocabulary = lexerInterpreterData.vocabulary;
                        modeNames = lexerInterpreterData.modeNames;
                    }
                }

                final Vocabulary finalVocabulary = vocabulary != null ? vocabulary : VocabularyImpl.EMPTY_VOCABULARY;
                final List<String> finalModeNames = modeNames;

                currentComponent = component;

                tblTokenTypes.setModel(new AbstractTableModel() {
                    private final List<String> literalNames = new ArrayList<>();
                    private final List<String> symbolicNames = new ArrayList<>();
                    private final List<Integer> values = new ArrayList<>();

                    {
                        // TODO: Find a better way to communicate this value
                        int maxTokenType = 1024;
                        for (int i = 0; i <= maxTokenType; i++) {
                            String literalName = finalVocabulary.getLiteralName(i);
                            String symbolicName = finalVocabulary.getSymbolicName(i);
                            if (literalName == null && symbolicName == null) {
                                continue;
                            }

                            literalNames.add(literalName != null ? literalName : "");
                            symbolicNames.add(symbolicName != null ? symbolicName : literalName);
                            values.add(i);
                        }
                    }

                    @Override
                    public int getRowCount() {
                        return literalNames.size();
                    }

                    @Override
                    public int getColumnCount() {
                        return 3;
                    }

                    @Override
                    public String getColumnName(int column) {
                        switch (column) {
                        case 0:
                            return "Name";
                        case 1:
                            return "Literal";
                        case 2:
                            return "Value";
                        default:
                            throw new IllegalArgumentException();
                        }
                    }

                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        switch (columnIndex) {
                        case 0:
                        case 1:
                            return String.class;

                        case 2:
                            return Integer.class;

                        default:
                            throw new IllegalArgumentException();
                        }
                    }

                    @Override
                    public Object getValueAt(int rowIndex, int columnIndex) {
                        switch (columnIndex) {
                        case 0:
                            return symbolicNames.get(rowIndex);
                        case 1:
                            return literalNames.get(rowIndex);
                        case 2:
                            return values.get(rowIndex);
                        default:
                            throw new IllegalArgumentException();
                        }
                    }
                });

                lstTokens.setModel(new AbstractListModel<TraceToken>() {
                    private final TraceToken[] elements = getFocusedEditorTokens();

                    @Override
                    public int getSize() {
                        return elements.length;
                    }

                    @Override
                    public TraceToken getElementAt(int index) {
                        return elements[index];
                    }

                });

                List<String> tokenNames = new ArrayList<>();
                for (int i = Token.EOF; i < 1024; i++) {
                    if (finalVocabulary.getLiteralName(i) == null && finalVocabulary.getSymbolicName(i) == null) {
                        continue;
                    }

                    tokenNames.add(finalVocabulary.getDisplayName(i));
                }

                lstTokens.setCellRenderer(new TraceTokenListCellRenderer(tokenNames));

                lstChannels.setModel(new AbstractListModel<Object>() {
                    private final Object[] elements = { defaultChannelText, hiddenChannelText };

                    @Override
                    public int getSize() {
                        return elements.length;
                    }

                    @Override
                    public Object getElementAt(int index) {
                        return elements[index];
                    }
                });

                lstModes.setModel(new AbstractListModel<String>() {
                    private final List<String> elements = finalModeNames;

                    @Override
                    public int getSize() {
                        return elements.size();
                    }

                    @Override
                    public String getElementAt(int index) {
                        return elements.get(index);
                    }
                });

                lstLookahead.setModel(new AbstractListModel<String>() {
                    private final String[] elements = { "ATN", "DFA" };

                    @Override
                    public int getSize() {
                        return elements.length;
                    }

                    @Override
                    public String getElementAt(int index) {
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

    private static TupleIntInt[] getEditorAtnCharacters(@NonNull JTextComponent component) {
        return getEditorTransitions(component, true);
    }

    private static TupleIntInt[] getEditorDfaCharacters(@NonNull JTextComponent component) {
        return getEditorTransitions(component, false);
    }

    private static TupleIntInt[] getEditorTransitions(@NonNull JTextComponent component, boolean atn) {
        TextUI ui = component.getUI();
        if (ui == null) {
            return new TupleIntInt[0];
        }

        EditorKit kit = ui.getEditorKit(component);
        if (!(kit instanceof LexerDebuggerEditorKit)) {
            return new TupleIntInt[0];
        }

        Document document = component.getDocument();
        if (document == null) {
            return new TupleIntInt[0];
        }

        if (atn) {
            return ((LexerDebuggerEditorKit)kit).getAtnTransitions(document);
        } else {
            return ((LexerDebuggerEditorKit)kit).getDfaTransitions(document);
        }
    }

    private static class TraceTokenListCellRenderer extends DefaultListCellRenderer {
        private final List<String> tokenNames;

        public TraceTokenListCellRenderer(List<String> tokenNames) {
            this.tokenNames = tokenNames;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
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
