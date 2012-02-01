/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.fold;

import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import org.netbeans.editor.CodeFoldingSideBar;
import org.netbeans.editor.SideBarFactory;

/**
 *  Java Code Folding Side Bar Factory, responsible for creating CodeFoldingSideBar
 *  Plugged via layer.xml
 *
 *  @author  Sam Harwell
 */
public class CodeFoldingSideBarFactory implements SideBarFactory {

    @Override
    public JComponent createSideBar(JTextComponent target) {
        return new CodeFoldingSideBar(target);
    }

}
