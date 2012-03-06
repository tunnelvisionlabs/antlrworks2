/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import org.openide.modules.ModuleInstall;
import org.openide.util.Lookup;
import org.openide.util.Lookup.Result;
import org.openide.util.Lookup.Template;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "# {0} - TopComponent ID",
    "LOG_FindingWindow=Attempting to locate TopComponent with ID ''{0}''",
    "# {0} - TopComponent ID",
    "LOG_WindowOpen=TopComponent with ID ''{0}'' is open",
    "# {0} - Folder",
    "# {1} - Instance file",
    "LOG_LookupAction=Attempting to find Action instance at {0}{1}",
    "# {0} - Action name",
    "LOG_FoundAction=Found Action ''{0}''; calling actionPerformed()",
    "# {0} - TopComponent ID",
    "LOG_WindowClosed=TopComponent with ID ''{0}'' is closed or not instantiated",
    "LOG_OutputId=output",
    "LOG_Folder=Actions/View/",
    "LOG_InstanceFile=org-netbeans-core-actions-LogAction",
})
public class Installer extends ModuleInstall {
    private static final Logger LOGGER = Logger.getLogger(Installer.class.getName());

    @Override
    public void restored() {
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {

            @Override
            public void run() {
                // locate the Output Window instance
                LOGGER.log(Level.FINE, Bundle.LOG_FindingWindow(Bundle.LOG_OutputId()));
                TopComponent outputWindow = WindowManager.getDefault().findTopComponent(Bundle.LOG_OutputId());
                if (outputWindow != null && outputWindow.isOpened()) {
                    LOGGER.log(Level.FINE, Bundle.LOG_WindowOpen(Bundle.LOG_OutputId()));
                    // use Lookup to find the instance in the file system
                    LOGGER.log(Level.FINE, Bundle.LOG_LookupAction(Bundle.LOG_Folder(), Bundle.LOG_InstanceFile()));
                    Lookup pathLookup = Lookups.forPath(Bundle.LOG_Folder());
                    Template<Action> actionTemplate = new Template<Action>(Action.class, Bundle.LOG_Folder() + Bundle.LOG_InstanceFile(), null);
                    Result<Action> lookupResult = pathLookup.lookup(actionTemplate);
                    Collection<? extends Action> foundActions = lookupResult.allInstances();
                    for (Action action : foundActions) {
                        LOGGER.log(Level.FINE, Bundle.LOG_FoundAction(action));
                        action.actionPerformed(null);
                    }
                } else {
                    LOGGER.log(Level.FINE, Bundle.LOG_WindowClosed(Bundle.LOG_OutputId()));
                }
            }
        });
    }


}
