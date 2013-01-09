/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package com.tvl.antlrworks.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Set;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.util.RequestProcessor;

@ActionID(
    category = "File",
    id = "com.tvl.antlrworks.project.NewFile")
@ActionRegistration(
    iconBase = "com/tvl/antlrworks/project/resources/newFile.png",
    displayName = "#CTL_NewFile")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 100),
    @ActionReference(path = "Toolbars/File", position = 0)
})
@Messages("CTL_NewFile=New File...")
public final class NewFile implements ActionListener {
    private static final RequestProcessor RP = new RequestProcessor(NewFile.class);

    @Override
    public void actionPerformed(ActionEvent e) {
        final NewFileWizard newFileWizard = new NewFileWizard();
        RP.post(new Runnable() {

            @Override
            public void run() {
                try {
                    Set<DataObject> resultSet = newFileWizard.instantiate();
                    if (resultSet == null || resultSet.isEmpty()) {
                        return;
                    }

                    for (DataObject dataObject : resultSet) {
                        if (dataObject != null) {
                            //ProjectUtilities.openAndSelectNewObject(dataObject);
                        }
                    }
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }

        });
    }
}
