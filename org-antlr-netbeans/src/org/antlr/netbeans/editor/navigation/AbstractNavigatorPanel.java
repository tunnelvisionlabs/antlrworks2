/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.navigation;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractNavigatorPanel<PanelUI extends JComponent> implements NavigatorPanel {

    @NonNull
    private final String _mimeType;
    @NonNull
    private final ParserDataDefinition<?> _dataDefinition;

    private PanelUI component;

    protected AbstractNavigatorPanel(@NonNull String mimeType, @NonNull ParserDataDefinition<?> dataDefinition) {
        Parameters.notNull("mimeType", mimeType);
        Parameters.notNull("dataDefinition", dataDefinition);
        this._mimeType = mimeType;
        this._dataDefinition = dataDefinition;
    }

    @NonNull
    @Override
    public final PanelUI getComponent() {
        if (this.component == null) {
            Runnable panelFactory = new Runnable() {
                @Override
                public void run() {
                    if (component == null) {
                        component = createPanelUI();
                    }
                }
            };

            if (SwingUtilities.isEventDispatchThread()) {
                panelFactory.run();
            } else {
                try {
                    SwingUtilities.invokeAndWait(panelFactory);
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (InvocationTargetException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }

        return this.component;
    }

    @NonNull
    protected abstract PanelUI createPanelUI();

    protected void scheduleTaskManagerUpdate(@NullAllowed DataObject dataObject) {
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

        if (!_mimeType.equals(versionedDocument.getMimeType())) {
            return;
        }

        ParseContext context = new ParseContext(ParserTaskScheduler.MANUAL_TASK_SCHEDULER, versionedDocument);
        Lookup.getDefault().lookup(ParserTaskManager.class).scheduleData(context, _dataDefinition, 0, TimeUnit.MILLISECONDS);
    }
}
