/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.formatting;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.settings.SimpleValueNames;
import org.netbeans.modules.options.editor.spi.PreferencesCustomizer;
import org.netbeans.modules.options.editor.spi.PreviewProvider;
import org.openide.text.CloneableEditorSupport;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "SAMPLE_Default=",
    "AN_Preview=",
    "AD_Preview=",
})
public class CategorySupport implements ActionListener, DocumentListener, PreviewProvider, PreferencesCustomizer {
    // -J-Dorg.antlr.netbeans.editor.formatting.CategorySupport.level=FINE
    private static final Logger LOGGER = Logger.getLogger(CategorySupport.class.getName());

    public static final String OPTION_ID = "org.antlr.netbeans.editor.formatting.FormatOptions.ID";

    private static final int LOAD = 0;
    private static final int STORE = 1;
    private static final int ADD_LISTENERS = 2;

    private static final Map<Class<?>, ComboItem[]> COMBO_BOX_ITEMS = new HashMap<>();

    private final String mimeType;
    private final String previewText;
    private final PreviewFormatter formatter;

    private final String id;
    protected final JPanel panel;

    private final Preferences preferences;

    private final List<JComponent> components = new LinkedList<>();
    private JEditorPane previewPane;

    protected CategorySupport(String mimeType, Preferences preferences, String id, JPanel panel, String previewText, PreviewFormatter formatter) {
        this.mimeType = mimeType;
        this.preferences = preferences;
        this.id = id;
        this.panel = panel;
        this.previewText = previewText != null ? previewText : Bundle.SAMPLE_Default();
        this.formatter = formatter;

        // Scan the panel for its components
        scan(panel, components);

        // Load and hook up all the components
        loadFrom(preferences);
        addListeners();
    }

    protected void addListeners() {
        scan(ADD_LISTENERS, null);
    }

    protected void loadFrom(Preferences preferences) {
        scan(LOAD, preferences);
    }

    protected void storeTo(Preferences p) {
        scan(STORE, p);
    }

    protected void notifyChanged() {
        storeTo(preferences);
        refreshPreview();
    }

    // ActionListener implementation ---------------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        notifyChanged();
    }

    // DocumentListener implementation -------------------------------------

    @Override
    public void insertUpdate(DocumentEvent e) {
        notifyChanged();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        notifyChanged();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        notifyChanged();
    }

    // PreviewProvider methods -----------------------------------------------------

    @Override
    public JComponent getPreviewComponent() {
        if (previewPane == null) {
            previewPane = new JEditorPane();
            previewPane.getAccessibleContext().setAccessibleName(Bundle.AN_Preview());
            previewPane.getAccessibleContext().setAccessibleDescription(Bundle.AD_Preview());
            previewPane.putClientProperty("HighlightsLayerIncludes", "^org\\.netbeans\\.modules\\.editor\\.lib2\\.highlighting\\.SyntaxHighlighting$"); //NOI18N
            previewPane.setEditorKit(CloneableEditorSupport.getEditorKit(mimeType));
            previewPane.setEditable(false);
        }
        return previewPane;
    }

    @Override
    public void refreshPreview() {
        JEditorPane jep = (JEditorPane) getPreviewComponent();

        try {
            int rm = FormatOptions.TEXT_LIMIT_WIDTH.getValue(preferences);
            jep.putClientProperty("TextLimitLine", rm); //NOI18N
            jep.getDocument().putProperty(SimpleValueNames.TEXT_LINE_WRAP, ""); //NOI18N
            jep.getDocument().putProperty(SimpleValueNames.TAB_SIZE, ""); //NOI18N
            jep.getDocument().putProperty(SimpleValueNames.TEXT_LIMIT_WIDTH, ""); //NOI18N
        }
        catch( NumberFormatException e ) {
            // Ignore it
        }

        try {
            // make sure the CodeStyle class is initialized
            Class.forName(CodeStyle.class.getName(), true, CodeStyle.class.getClassLoader());
        } catch (ClassNotFoundException cnfe) {
            // ignore
        }

        jep.setIgnoreRepaint(true);
        String formattedPreviewText = formatter != null ? formatter.reformat(previewText, preferences) : previewText;
        jep.setText(formattedPreviewText);
        jep.setIgnoreRepaint(false);
        jep.scrollRectToVisible(new Rectangle(0,0,10,10) );
        jep.repaint(100);
    }

    // PreferencesCustomizer implementation --------------------------------

    @Override
    public JComponent getComponent() {
        return panel;
    }

    @Override
    public String getDisplayName() {
        return panel.getName();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }

    // PreferencesCustomizer.Factory implementation ------------------------

    public static final class Factory implements PreferencesCustomizer.Factory {

        private final String mimeType;
        private final String id;
        private final Class<? extends JPanel> panelClass;
        private final String previewText;
        private final PreviewFormatter formatter;

        public Factory(String mimeType, String id, Class<? extends JPanel> panelClass, String previewText, PreviewFormatter formatter) {
            this.mimeType = mimeType;
            this.id = id;
            this.panelClass = panelClass;
            this.previewText = previewText;
            this.formatter = formatter;
        }

        @Override
        public PreferencesCustomizer create(Preferences preferences) {
            try {
                CategorySupport categorySupport = new CategorySupport(mimeType, preferences, id, panelClass.newInstance(), previewText, formatter);
                return categorySupport;
            } catch (InstantiationException | IllegalAccessException ex) {
                LOGGER.log(Level.WARNING, "An exception occurred attempting to customize preferences.", ex);
                return null;
            }
        }
    }

    // Private methods -----------------------------------------------------

    private void performOperation(int operation, JComponent jc, AbstractFormatOption option, Preferences p) {
        switch(operation) {
        case LOAD:
            loadData(jc, option, p);
            break;

        case STORE:
            storeData(jc, option, p);
            break;

        case ADD_LISTENERS:
            addListener(jc);
            break;
        }
    }

    private void scan(int what, Preferences p ) {
        for (JComponent jc : components) {
            Object o = jc.getClientProperty(OPTION_ID);
            if (o instanceof AbstractFormatOption) {
                performOperation(what, jc, (AbstractFormatOption)o, p);
            } else if (o instanceof AbstractFormatOption[]) {
                for(AbstractFormatOption oid : (AbstractFormatOption[])o) {
                    performOperation(what, jc, oid, p);
                }
            }
        }
    }

    private void scan(Container container, List<JComponent> components) {
        for (Component c : container.getComponents()) {
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                Object o = jc.getClientProperty(OPTION_ID);
                if (o instanceof String || o instanceof String[] || o instanceof AbstractFormatOption || o instanceof AbstractFormatOption[]) {
                    components.add(jc);
                }
            }

            if (c instanceof Container) {
                scan((Container)c, components);
            }
        }
    }

    /** Very smart method which tries to set the values in the components correctly
     */
    @SuppressWarnings("unchecked")
    private void loadData( JComponent jc, AbstractFormatOption optionID, Preferences node ) {
        if ( jc instanceof JTextField ) {
            JTextField field = (JTextField)jc;
            field.setText( optionID.getValueAsString(node) );
        }
        else if ( jc instanceof JToggleButton ) {
            JToggleButton toggle = (JToggleButton)jc;
            toggle.setSelected( ((BooleanFormatOption)optionID).getValue(node) );
        }
        else if ( jc instanceof JComboBox ) {
            @SuppressWarnings("rawtypes")
            JComboBox cb  = (JComboBox)jc;
            Enum<?> value = ((EnumFormatOption<?>)optionID).getValue(node);
            @SuppressWarnings("rawtypes")
            ComboBoxModel model = createModel(value);
            cb.setModel(model);
            ComboItem item = whichItem(value, model);
            cb.setSelectedItem(item);
        }
    }

    private void storeData( JComponent jc, @NonNull AbstractFormatOption option, Preferences node ) {
        if ( jc instanceof JTextField ) {
            JTextField field = (JTextField)jc;

            String text = field.getText();

            // XXX test for numbers
            if ( option instanceof IntFormatOption ) {
                try {
                    int i = Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    return;
                }
            }

            // XXX: watch out, tabSize, spacesPerTab, indentSize and expandTabToSpaces
            // fall back on getGlopalXXX() values and not getDefaultAsXXX value,
            // which is why we must not remove them. Proper solution would be to
            // store formatting preferences to MimeLookup and not use NbPreferences.
            // The problem currently is that MimeLookup based Preferences do not support subnodes.
            if (!option.equals(FormatOptions.TAB_SIZE) &&
                !option.equals(FormatOptions.SPACES_PER_TAB) && !option.equals(FormatOptions.INDENT_SHIFT_WIDTH) &&
                option.getDefaultValueAsString().equals(text)
            ) {
                node.remove(option.getName());
            } else {
                node.put(option.getName(), text);
            }
        }
        else if ( jc instanceof JToggleButton ) {
            JToggleButton toggle = (JToggleButton)jc;
            if (!option.equals(FormatOptions.EXPAND_TABS) && ((BooleanFormatOption)option).getDefaultValue() == toggle.isSelected())
                node.remove(option.getName());
            else
                node.putBoolean(option.getName(), toggle.isSelected());
        }
        else if ( jc instanceof JComboBox ) {
            @SuppressWarnings("rawtypes")
            JComboBox cb  = (JComboBox)jc;
            // Logger.global.info( cb.getSelectedItem() + " " + optionID);
            Enum<?> value = ((ComboItem) cb.getSelectedItem()).value;
            if (((EnumFormatOption<?>)option).getDefaultValue().equals(value))
                node.remove(option.getName());
            else
                node.put(option.getName(), value.name());
        }
    }

    private void addListener( JComponent jc ) {
        if ( jc instanceof JTextField ) {
            JTextField field = (JTextField)jc;
            field.addActionListener(this);
            field.getDocument().addDocumentListener(this);
        }
        else if ( jc instanceof JToggleButton ) {
            JToggleButton toggle = (JToggleButton)jc;
            toggle.addActionListener(this);
        }
        else if ( jc instanceof JComboBox) {
            @SuppressWarnings("rawtypes")
            JComboBox cb  = (JComboBox)jc;
            cb.addActionListener(this);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static synchronized ComboBoxModel createModel( Enum<?> value ) {

        ComboItem[] items = COMBO_BOX_ITEMS.get(value.getClass());
        if (items == null) {
            EnumSet<?> enumSet = EnumSet.allOf(value.getClass());
            items = new ComboItem[enumSet.size()];
            int i = 0;
            for (Object item : enumSet) {
                items[i++] = new ComboItem((Enum)item);
            }

            COMBO_BOX_ITEMS.put(value.getClass(), items);
        }

        return new DefaultComboBoxModel(items);
    }

    private static ComboItem whichItem(Enum<?> value,
                                        @SuppressWarnings("rawtypes")
                                       ComboBoxModel model) {
        for (int i = 0; i < model.getSize(); i++) {
            ComboItem item = (ComboItem)model.getElementAt(i);
            if ( value.equals(item.value)) {
                return item;
            }
        }

        return null;
    }

    private static class ComboItem {

        Enum<?> value;
        String displayName;

        public ComboItem(Enum<?> value) {
            this.value = value;
            this.displayName = NbBundle.getMessage(value.getClass(), String.format("LBL_%s_%s", value.getClass().getSimpleName(), value.name()));
        }

        @Override
        public String toString() {
            return displayName;
        }

    }

    public interface PreviewFormatter {
        String reformat(String text, Preferences preferences);
    }

}
