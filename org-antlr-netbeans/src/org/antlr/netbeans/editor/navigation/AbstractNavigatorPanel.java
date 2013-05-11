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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.cookies.EditorCookie;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 * @param <PanelUI>
 */
public abstract class AbstractNavigatorPanel<PanelUI extends JComponent> implements NavigatorPanel {
    private final Lookup.Template<DataObject> TEMPLATE = new Lookup.Template<>(DataObject.class);

    @NonNull
    private final String _mimeType;
    @NonNull
    private final Collection<? extends ParserDataDefinition<?>> _dataDefinitions;

    private PanelUI component;

    private final LookupListener _listener = new LookupListener() {

        @Override
        public void resultChanged(LookupEvent ev) {
            Lookup.Result<DataObject> result = _result;
            if (result == null) {
                return;
            }

            for (DataObject instance : result.allInstances()) {
                scheduleTaskManagerUpdate(instance);
                // only schedule for the first item
                break;
            }
        }
    };

    private Lookup _context;
    private Lookup.Result<DataObject> _result = Lookup.EMPTY.lookup(TEMPLATE);

    protected AbstractNavigatorPanel(@NonNull String mimeType, @NonNull ParserDataDefinition<?>... dataDefinitions) {
        Parameters.notNull("mimeType", mimeType);
        Parameters.notNull("dataDefinitions", dataDefinitions);
        this._mimeType = mimeType;
        this._dataDefinitions = new ArrayList<>(Arrays.asList(dataDefinitions));
    }

    @Override
    public void panelActivated(Lookup context) {
        _context = context;
        _result = context.lookup(TEMPLATE);
        _result.addLookupListener(_listener);
        scheduleTaskManagerUpdate(context.lookup(DataObject.class));
    }

    @Override
    public void panelDeactivated() {
        _context = null;
        _result.removeLookupListener(_listener);
        _result = Lookup.EMPTY.lookup(TEMPLATE);
        scheduleTaskManagerUpdate(null);
    }

    public boolean isExpectedContext(DataObject context) {
        return _result.allInstances().contains(context);
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
                } catch (InterruptedException | InvocationTargetException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }

        return this.component;
    }

    @NonNull
    protected abstract PanelUI createPanelUI();

    protected void scheduleTaskManagerUpdate(@NullAllowed DataObject dataObject) {
        Document document = null;
        if (dataObject != null) {
            EditorCookie editorCookie = dataObject.getLookup().lookup(EditorCookie.class);
            document = editorCookie != null ? editorCookie.getDocument() : null;
        }

        VersionedDocument versionedDocument;
        if (document != null) {
            versionedDocument = VersionedDocumentUtilities.getVersionedDocument(document);
        } else if (dataObject != null) {
            versionedDocument = VersionedDocumentUtilities.getVersionedDocument(dataObject.getPrimaryFile());
        } else {
            return;
        }

        if (!_mimeType.equals(versionedDocument.getMimeType())) {
            return;
        }

        ParseContext context = new ParseContext(ParserTaskScheduler.MANUAL_TASK_SCHEDULER, versionedDocument);
        Lookup.getDefault().lookup(ParserTaskManager.class).scheduleData(context, _dataDefinitions, 0, TimeUnit.MILLISECONDS);
    }
}
