/*
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.netbeans.editor.actions;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.EditorActionRegistration;
import org.netbeans.editor.BaseAction;
import org.netbeans.editor.EditorUI;
import org.netbeans.editor.Utilities;
import org.openide.util.NbBundle;

/**
 * This action scrolls the editor view one page up, without affecting the caret
 * position. It is equivalent to a single click on the scroll bar in the space
 * between the slider and the down arrow at the bottom.
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "scroll-page-up=Scroll Page Up"
})
@EditorActionRegistration(name = ScrollPageUpAction.scrollPageUpAction)
public class ScrollPageUpAction extends BaseAction {

    public static final String scrollPageUpAction = "scroll-page-up"; // NOI18N

    public ScrollPageUpAction() {
    }

    @Override
    public void actionPerformed(ActionEvent evt, JTextComponent target) {
        if (target != null) {
            EditorUI editorUI = Utilities.getEditorUI(target);
            Rectangle bounds = editorUI.getExtentBounds();
            bounds.y += bounds.height - editorUI.getLineHeight();
            bounds.x += editorUI.getTextMargin().left;
            target.scrollRectToVisible(bounds);
        }
    }

    @Override
    protected Class<?> getShortDescriptionBundleClass() {
        return ScrollPageUpAction.class;
    }
}
