/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans;

import org.antlr.netbeans.parsing.spi.impl.TaskSchedulers;
import org.openide.modules.ModuleInstall;
import org.openide.util.RequestProcessor;
import org.openide.windows.WindowManager;

/**
 *
 * @author Sam Harwell
 */
public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        super.restored();
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run () {
                RequestProcessor.getDefault().post(new Runnable() {
                    @Override
                    public void run() {
                        TaskSchedulers.init();
                    }
                });
            }
        });
    }

}
