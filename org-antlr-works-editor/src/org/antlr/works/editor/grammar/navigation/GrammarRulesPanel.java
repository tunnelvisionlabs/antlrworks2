/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.navigation;

import java.util.concurrent.TimeUnit;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.works.editor.grammar.GrammarDataObject;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.netbeans.spi.navigator.NavigatorPanel.Registration;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

@NbBundle.Messages({
    "LBL_rules=Rules",
    "HINT_rules=Rules"
})
@Registration(mimeType = GrammarEditorKit.GRAMMAR_MIME_TYPE, position = 100, displayName = "#LBL_rules")
public class GrammarRulesPanel implements NavigatorPanel {

    private static volatile GrammarRulesPanel INSTANCE;

    private GrammarRulesPanelUI component;

    @Override
    public String getDisplayName() {
        return Bundle.LBL_rules();
    }

    @Override
    public String getDisplayHint() {
        return Bundle.HINT_rules();
    }

    @Override
    public GrammarRulesPanelUI getComponent() {
        return getGrammarRulesPanelUI();
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
        getGrammarRulesPanelUI().showWaitNode();
    }

    @Override
    public Lookup getLookup() {
        return getGrammarRulesPanelUI().getLookup();
    }

    private void scheduleTaskManagerUpdate(DataObject dataObject) {
        if (dataObject != null && !(dataObject instanceof GrammarDataObject)) {
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

        ParseContext context = new ParseContext(ParserTaskScheduler.MANUAL_TASK_SCHEDULER, versionedDocument);
        Lookup.getDefault().lookup(ParserTaskManager.class).scheduleData(context, GrammarParserDataDefinitions.NAVIGATOR_UI_VISIBLE, 0, TimeUnit.MILLISECONDS);
    }

    private synchronized GrammarRulesPanelUI getGrammarRulesPanelUI() {
        if (this.component == null) {
            this.component = new GrammarRulesPanelUI();
        }

        return this.component;
    }

    public static GrammarRulesPanel getInstance() {
        return INSTANCE;
    }

}
