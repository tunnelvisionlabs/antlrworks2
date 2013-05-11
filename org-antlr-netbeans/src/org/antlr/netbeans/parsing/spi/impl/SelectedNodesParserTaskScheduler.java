/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi.impl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Callable;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.netbeans.modules.editor.settings.storage.api.EditorSettings;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.TopComponent;

/**
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=ParserTaskScheduler.class)
public class SelectedNodesParserTaskScheduler extends ParserTaskScheduler {

    @Override
    protected void initializeImpl() {
        TopComponent.getRegistry().addPropertyChangeListener(new TopComponentListener());
        refresh();
    }

    private void refresh() {
        ParserTaskManager taskManager = getTaskManager();
        taskManager.scheduleHighPriority(new RefreshImpl());
    }

    private class RefreshImpl implements Callable<Void> {

        @Override
        public Void call() {
            final Node[] nodes = TopComponent.getRegistry().getActivatedNodes();
            if (nodes.length == 1) {
                final DataObject dataObject = nodes[0].getLookup().lookup(DataObject.class);
                if (dataObject != null && dataObject.isValid()) {
                    final FileObject fileObject = dataObject.getPrimaryFile();
                    if (fileObject.isValid() && EditorSettings.getDefault().getAllMimeTypes().contains(fileObject.getMIMEType())) {
                        VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(fileObject);
                        ParseContext context = new ParseContext(SelectedNodesParserTaskScheduler.this.getClass(), versionedDocument);
                        schedule(context);
                        return null;
                    }
                }
            }

            cancelAll(false);
            return null;
        }
    }

    private class TopComponentListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName() == null
                || evt.getPropertyName().equals(TopComponent.Registry.PROP_ACTIVATED_NODES)) {
                refresh();
            }
        }
    }
}
