/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.navigation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.StyleConstants;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.netbeans.api.editor.settings.AttributesUtilities;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.cookies.EditorCookie;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "LBL_DisplayName=Parse Tree (Development)",
    "HINT_DisplayName=Parse Tree (Development)",
})
public abstract class TreeNavigatorPanel implements NavigatorPanel {
    // -J-Dorg.antlr.works.editor.antlr4.navigation.TreeNavigatorPanel.level=FINE
    private static final Logger LOGGER = Logger.getLogger(TreeNavigatorPanel.class.getName());

    private static final AttributeSet HIGHLIGHT = AttributesUtilities.createImmutable(StyleConstants.Background, new Color(224, 224, 224));
    private static final AttributeSet HIGHLIGHT_PREF = AttributesUtilities.createImmutable(StyleConstants.Underline, new Color(30, 255, 0));

    private JComponent _panel;
    private final ExplorerManager _manager = new ExplorerManager();

    protected TreeNavigatorPanel() {
        _manager.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (ExplorerManager.PROP_SELECTED_NODES.equals(evt.getPropertyName())) {
                    setHighlights(getCurrentFile(), _manager);
                }
            }
        });
    }

    @Override
    public String getDisplayName() {
        return Bundle.LBL_DisplayName();
    }

    @Override
    public String getDisplayHint() {
        return Bundle.HINT_DisplayName();
    }

    @Override
    public JComponent getComponent() {
        if (_panel == null) {
            final BeanTreeView view = new BeanTreeView();
            view.setRootVisible(true);
            view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            class Panel extends JPanel implements ExplorerManager.Provider, Lookup.Provider {
                private final Lookup _lookup = ExplorerUtils.createLookup(_manager, new ActionMap());
                {
                    setLayout(new BorderLayout());
                    add(view, BorderLayout.CENTER);
                }

                @Override
                public ExplorerManager getExplorerManager() {
                    return _manager;
                }

                @Override
                public Lookup getLookup() {
                    return _lookup;
                }
            }

            _panel = new Panel();
        }

        return _panel;
    }

    @Override
    public void panelActivated(Lookup context) {
    }

    @Override
    public void panelDeactivated() {
    }

    @Override
    public Lookup getLookup() {
        return null;
    }

    public void setParseTree(ParseTreeNode rootNode) {
        _manager.setRootContext(rootNode);
    }

    protected abstract FileObject getCurrentFile();

    protected static OffsetsBag getBag(Document doc) {
        OffsetsBag bag = (OffsetsBag) doc.getProperty(TreeNavigatorPanel.class);

        if (bag == null) {
            doc.putProperty(TreeNavigatorPanel.class, bag = new OffsetsBag(doc));
        }

        return bag;
    }

    protected void setHighlights(FileObject file, ExplorerManager manager) {
        if (file == null) {
            return;
        }
        try {
            DataObject od = DataObject.find(file);

            EditorCookie ec = od.getLookup().lookup(EditorCookie.class);

            if (ec == null) {
                return;
            }
            Document doc = ec.getDocument();
            if (doc == null) {
                return;
            }

            OffsetsBag bag = new OffsetsBag(doc, true);
            for (Node n : manager.getSelectedNodes()) {
                if (n instanceof OffsetProvider) {
                    OffsetProvider p = (OffsetProvider) n;

                    OffsetRegion span = p.getSpan();
                    if (span != null) {
                        bag.addHighlight(span.getStart(), span.getEnd(), HIGHLIGHT);
                    }

                    OffsetRegion emphasis = p.getEmphasis();
                    if (emphasis != null) {
                        bag.addHighlight(emphasis.getStart(), emphasis.getEnd(), HIGHLIGHT_PREF);
                    }
                }
            }

            getBag(doc).setHighlights(bag);
        } catch (DataObjectNotFoundException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

}
