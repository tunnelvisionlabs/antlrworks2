/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.navigation;

import org.antlr.works.editor.antlr4.navigation.TreeNavigatorPanel;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.highlighting.HighlightsLayer;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.ZOrder;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "LBL_ParseTreeDefinition=Parse Tree (Development)"
})
@NavigatorPanel.Registration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, position = 9999, displayName = "#LBL_ParseTreeDefinition")
public class GrammarParseTreeNavigatorPanel extends TreeNavigatorPanel {

    private static volatile GrammarParseTreeNavigatorPanel INSTANCE;

    private FileObject _currentFile;

    public GrammarParseTreeNavigatorPanel() {
        super(GrammarEditorKit.GRAMMAR_MIME_TYPE, GrammarParserDataDefinitions.REFERENCE_PARSE_TREE, GrammarParserDataDefinitions.PARSE_TREE_UI_VISIBLE);
    }

    public static GrammarParseTreeNavigatorPanel getInstance() {
        return INSTANCE;
    }

    @Override
    protected FileObject getCurrentFile() {
        return _currentFile;
    }

    public void setCurrentFile(FileObject currentFile) {
        this._currentFile = currentFile;
    }

    @Override
    public void panelActivated(Lookup context) {
        super.panelActivated(context);
        INSTANCE = this;
        _currentFile = null;
    }

    @Override
    public void panelDeactivated() {
        super.panelDeactivated();
        INSTANCE = null;
        _currentFile = null;
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=HighlightsLayerFactory.class)
    public static final class HighlightsLayerFactoryImpl implements HighlightsLayerFactory {

        @Override
        public HighlightsLayer[] createLayers(Context context) {
            return new HighlightsLayer[] {
                HighlightsLayer.create(GrammarParseTreeNavigatorPanel.class.getName(), ZOrder.DEFAULT_RACK, true, getBag(context.getDocument()))
            };
        }

    }
}
