/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.navigation;

import java.util.concurrent.TimeUnit;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.works.editor.antlr4.navigation.TreeNavigatorPanel;
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.antlr.works.editor.st4.StringTemplateFileTypeDataObject;
import org.antlr.works.editor.st4.TemplateParserDataDefinitions;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.netbeans.spi.editor.highlighting.HighlightsLayer;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.ZOrder;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "LBL_ParseTreeDefinition=Parse Tree (Development)"
})
@NavigatorPanel.Registration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, position = 9999, displayName = "#LBL_ParseTreeDefinition")
public class TemplateParseTreeNavigatorPanel extends TreeNavigatorPanel {

    private static volatile TemplateParseTreeNavigatorPanel INSTANCE;

    private FileObject _currentFile;

    public static TemplateParseTreeNavigatorPanel getInstance() {
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
        scheduleTaskManagerUpdate(context.lookup(DataObject.class));
    }

    @Override
    public void panelDeactivated() {
        super.panelDeactivated();
        INSTANCE = null;
        _currentFile = null;
        scheduleTaskManagerUpdate(null);
    }

    private void scheduleTaskManagerUpdate(DataObject dataObject) {
        if (dataObject != null && !(dataObject instanceof StringTemplateFileTypeDataObject)) {
            return;
        }

        JTextComponent currentComponent = EditorRegistry.lastFocusedComponent();
        if (currentComponent == null) {
            return;
        }

        Document document = currentComponent.getDocument();
        DataObject documentDataObject = document != null ? NbEditorUtilities.getDataObject(document) : null;
        VersionedDocument versionedDocument;
        if (dataObject != null && (documentDataObject == null || !dataObject.equals(documentDataObject))) {
            versionedDocument = VersionedDocumentUtilities.getVersionedDocument(dataObject.getPrimaryFile());
        } else if (document != null) {
            versionedDocument = VersionedDocumentUtilities.getVersionedDocument(document);
        } else {
            return;
        }

        if (!StringTemplateEditorKit.TEMPLATE_MIME_TYPE.equals(versionedDocument.getMimeType())) {
            return;
        }

        ParseContext context = new ParseContext(ParserTaskScheduler.MANUAL_TASK_SCHEDULER, versionedDocument);
        Lookup.getDefault().lookup(ParserTaskManager.class).scheduleData(context, TemplateParserDataDefinitions.PARSE_TREE_UI_VISIBLE, 0, TimeUnit.MILLISECONDS);
    }

    @MimeRegistration(mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE, service=HighlightsLayerFactory.class)
    public static final class HighlightsLayerFactoryImpl implements HighlightsLayerFactory {

        @Override
        public HighlightsLayer[] createLayers(Context context) {
            return new HighlightsLayer[] {
                HighlightsLayer.create(TemplateParseTreeNavigatorPanel.class.getName(), ZOrder.DEFAULT_RACK, true, getBag(context.getDocument()))
            };
        }

    }
}
