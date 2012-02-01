/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.navigation;

import java.awt.Image;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.navigation.NavigatorNode;
import org.antlr.netbeans.editor.navigation.NavigatorPanelUI;
import org.openide.util.ImageUtilities;

public class TemplateNode extends NavigatorNode {

    public TemplateNode(NavigatorPanelUI ui, Description description) {
        super(ui, description, TemplateNodeFactory.INSTANCE);
    }

    @Override
    public Image getIcon(int type) {
        //TODO: handle regions and templates separately
        return ImageUtilities.loadImage("org/antlr/works/editor/st4/navigation/resources/template_parameter_16.png");
    }

    public static class TemplateDescription extends Description {

        public TemplateDescription() {
        }

        public TemplateDescription(String name) {
            super(name);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof TemplateDescription)) {
                return false;
            }

            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    protected static class TemplateNodeFactory implements Factory {
        public static final TemplateNodeFactory INSTANCE = new TemplateNodeFactory();

        @Override
        public NavigatorNode createNode(NavigatorPanelUI ui, Description key) {
            return new TemplateNode(ui, key);
        }

    }
}
