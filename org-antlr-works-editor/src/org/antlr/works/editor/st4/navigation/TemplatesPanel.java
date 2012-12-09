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
import org.antlr.works.editor.st4.StringTemplateEditorKit;
import org.antlr.works.editor.st4.StringTemplateFileTypeDataObject;
import org.antlr.works.editor.st4.TemplateParserDataDefinitions;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.netbeans.spi.navigator.NavigatorPanel.Registration;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

@NbBundle.Messages({
    "LBL_templates=Templates",
    "HINT_templates=Templates"
})
@Registration(mimeType = StringTemplateEditorKit.TEMPLATE_MIME_TYPE, position = 100, displayName = "#LBL_templates")
public class TemplatesPanel implements NavigatorPanel {

    private static volatile TemplatesPanel INSTANCE;

    private TemplatesPanelUI component;

    @Override
    public String getDisplayName() {
        return Bundle.LBL_templates();
    }

    @Override
    public String getDisplayHint() {
        return Bundle.HINT_templates();
    }

    @Override
    public TemplatesPanelUI getComponent() {
        return getTemplatesPanelUI();
    }

    @Override
    public void panelActivated(Lookup context) {
        INSTANCE = this;
        scheduleTaskManagerUpdate(context.lookup(DataObject.class));
    }

    @Override
    public void panelDeactivated() {
        INSTANCE = null;
        scheduleTaskManagerUpdate(null);
        getTemplatesPanelUI().showWaitNode();
    }

    @Override
    public Lookup getLookup() {
        return getTemplatesPanelUI().getLookup();
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
        Lookup.getDefault().lookup(ParserTaskManager.class).scheduleData(context, TemplateParserDataDefinitions.NAVIGATOR_UI_VISIBLE, 0, TimeUnit.MILLISECONDS);
    }

    private synchronized TemplatesPanelUI getTemplatesPanelUI() {
        if (this.component == null) {
            this.component = new TemplatesPanelUI();
        }

        return this.component;
    }

    public static TemplatesPanel getInstance() {
        return INSTANCE;
    }

}
