/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.completion;

import javax.swing.JToolTip;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Sam Harwell
 */
public class CompletionToolTip extends JToolTip {

    private boolean _hasData;

    public CompletionToolTip(JTextComponent component) {
    }

    @Override
    public void setTipText(String tipText) {
        super.setTipText(tipText);
        _hasData = tipText != null && tipText.length() > 0;
    }

    public boolean hasData() {
        return _hasData;
    }

    public void clearData() {
        setTipText("");
        _hasData = false;
    }

}
