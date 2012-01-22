/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.antlr.netbeans.parsing.spi.impl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Callable;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
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

    private VersionedDocument versionedDocument;

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
        public Void call() throws Exception {
            final Node[] nodes = TopComponent.getRegistry().getActivatedNodes();
            if (nodes.length == 1) {
                final DataObject dataObject = nodes[0].getLookup().lookup(DataObject.class);
                if (dataObject != null && dataObject.isValid()) {
                    final FileObject fileObject = dataObject.getPrimaryFile();
                    if (fileObject.isValid() && EditorSettings.getDefault().getAllMimeTypes().contains(fileObject.getMIMEType())) {
                        versionedDocument = VersionedDocumentUtilities.getVersionedDocument(fileObject);
                        if (versionedDocument != null) {
                            schedule(versionedDocument);
                            return null;
                        }
                    }
                }
            }

            versionedDocument = null;
            schedule(null);
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
