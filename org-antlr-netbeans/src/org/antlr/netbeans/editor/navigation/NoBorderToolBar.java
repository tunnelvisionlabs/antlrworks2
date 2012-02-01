/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.navigation;

import java.awt.Graphics;
import javax.swing.JToolBar;

/**
 * ToolBar that doesn't paint any border.
 *
 * @author S. Aubrecht
 */
public class NoBorderToolBar extends JToolBar {

    /** Creates a new instance of NoBorderToolbar */
    public NoBorderToolBar() {
    }

    /** Creates a new instance of NoBorderToolbar
     * @param layout
     */
    public NoBorderToolBar(int layout) {
        super(layout);
    }

    @Override
    protected void paintComponent(Graphics g) {
    }
}
