/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.antlr.netbeans;

import org.antlr.netbeans.parsing.spi.impl.TaskSchedulers;
import org.openide.modules.ModuleInstall;
import org.openide.util.RequestProcessor;
import org.openide.windows.WindowManager;

/**
 *
 * @author sam
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
