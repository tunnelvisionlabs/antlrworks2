/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
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
package org.antlr.netbeans.editor.navigation;

import java.awt.Image;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.swing.Action;
import org.antlr.netbeans.editor.navigation.actions.OpenAction;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.datatransfer.PasteType;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "LBL_WaitNode=Please wait..."
})
public abstract class NavigatorNode extends AbstractNode {

    private static Node WAIT_NODE;
    private final NavigatorPanelUI ui;
    private Description description;
    private OpenAction openAction;

    public NavigatorNode(@NonNull NavigatorPanelUI ui, @NonNull Description description, Factory nodeFactory) {
        super(description.getChildren() == null ? Children.LEAF : new ElementChildren(ui, description.getChildren(), nodeFactory), prepareLookup(description));
        this.ui = ui;
        this.description = description;
        setDisplayName(description.getName());
    }

    public NavigatorPanelUI getUI() {
        return ui;
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public String getDisplayName() {
        if (description.getName() != null) {
            return description.getName();
        }

        if (description.getFileObject() != null) {
            return description.getFileObject().getNameExt();
        }

        return null;
    }

    @Override
    public String getHtmlDisplayName() {
        return description.getHtmlHeader();
    }

    @Override
    public Action[] getActions(boolean context) {
        if (context || description.getName() == null || description.getFileObject() == null || (description.getChildren() != null && !description.getChildren().isEmpty())) {
            return getUI().getActions();
        } else {
            Action[] panelActions = getUI().getActions();

            int extraActionCount = 2;
            Action[] actions = new Action[extraActionCount + panelActions.length];
            actions[0] = getOpenAction();
            actions[1] = null;
            System.arraycopy(panelActions, 0, actions, extraActionCount, panelActions.length);

            return actions;
        }
    }

    @Override
    public Action getPreferredAction() {
        return getOpenAction();
    }

    @Override
    public boolean canCopy() {
        return false;
    }

    @Override
    public boolean canCut() {
        return false;
    }

    @Override
    public boolean canDestroy() {
        return false;
    }

    @Override
    public boolean canRename() {
        return false;
    }

    @Override
    public PasteType getDropType(Transferable t, int action, int index) {
        return null;
    }

    @Override
    public Transferable drag() throws IOException {
        return null;
    }

    public Description getDescription() {
        return description;
    }

    @Override
    protected void createPasteTypes(Transferable t, List<PasteType> s) {
        // do nothing
    }

    public void refreshRecursively() {
        Children ch = getChildren();
        if (ch instanceof ElementChildren) {
            boolean scrollOnExpand = getUI().getScrollOnExpand();
            getUI().setScrollOnExpand(false);
            ((ElementChildren)ch).resetKeys(description.getChildren(), getUI().getFilters());
            for (Node sub : ch.getNodes()) {
                getUI().expandNode(sub);
                ((NavigatorNode)sub).refreshRecursively();
            }

            getUI().setScrollOnExpand(scrollOnExpand);
        }
    }

    public void updateRecursively(Description newDescription) {
        Children children = getChildren();
        if (children instanceof ElementChildren) {
            HashSet<Description> oldChildren = new HashSet<Description>(description.getChildren());

            // Create a hashtable which maps Description to node.
            // We will then identify the nodes by the description. The trick is
            // that the new and old description are equal and have the same hashcode
            Node[] nodes = children.getNodes(true);
            HashMap<Description, NavigatorNode> oldD2node = new HashMap<Description, NavigatorNode>();
            for (Node node : nodes) {
                oldD2node.put(((NavigatorNode)node).description, (NavigatorNode)node);
            }

            // Now refresh keys
            ((ElementChildren)children).resetKeys(newDescription.getChildren(), getUI().getFilters());

            // Reread nodes
            nodes = children.getNodes(true);

            for (Description newSub : newDescription.getChildren()) {
                NavigatorNode node = oldD2node.get(newSub);
                if (node != null) { // filtered out
                    if (!oldChildren.contains(newSub) && node.getChildren() != Children.LEAF) {
                        getUI().expandNode(node); // Make sure new nodes get expanded
                    }
                    node.updateRecursively(newSub); // update the node recursively
                }
            }
        }

        Description oldDescription = description; // Remember old description
        description = newDescription; // set new descrioption to the new node
        if (oldDescription.getHtmlHeader() != null && !oldDescription.getHtmlHeader().equals(description.getHtmlHeader())) {
            // Different headers => we need to fire displayname change
            fireDisplayNameChange(oldDescription.getHtmlHeader(), description.getHtmlHeader());
        }

        /*if (oldDescription.modifiers != null && !oldDescription.modifiers.equals(newDescription.modifiers)) {
        fireIconChange();
        fireOpenedIconChange();
        }*/
    }

    private synchronized Action getOpenAction() {
        if (openAction == null) {
            openAction = new OpenAction(description);
        }

        return openAction;
    }

    private static Lookup prepareLookup(Description d) {
        InstanceContent instanceContent = new InstanceContent();

        instanceContent.add(d, ConvertDescriptionToFileObject);
        instanceContent.add(d, ConvertDescriptionToDataObject);

        return new AbstractLookup(instanceContent);
    }

    private static final InstanceContent.Convertor<Description, FileObject> ConvertDescriptionToFileObject =
        new InstanceContent.Convertor<Description, FileObject>() {

            @Override
            public FileObject convert(Description obj) {
                return obj.getFileObject();
            }

            @Override
            public Class<? extends FileObject> type(Description obj) {
                return FileObject.class;
            }

            @Override
            public String id(Description obj) {
                return "IL[" + obj.toString();
            }

            @Override
            public String displayName(Description obj) {
                return id(obj);
            }

        };
    private static final InstanceContent.Convertor<Description, DataObject> ConvertDescriptionToDataObject =
        new InstanceContent.Convertor<Description, DataObject>() {

            @Override
            public DataObject convert(Description obj) {
                try {
                    FileObject fileObject = obj.getFileObject();
                    return fileObject != null ? DataObject.find(fileObject) : null;
                } catch (DataObjectNotFoundException ex) {
                    return null;
                }
            }

            @Override
            public Class<? extends DataObject> type(Description obj) {
                return DataObject.class;
            }

            @Override
            public String id(Description obj) {
                return "IL[" + obj.toString();
            }

            @Override
            public String displayName(Description obj) {
                return id(obj);
            }

        };

    public static synchronized Node getWaitNode() {
        if (WAIT_NODE == null) {
            WAIT_NODE = new WaitNode();
        }

        return WAIT_NODE;
    }

    private static class WaitNode extends AbstractNode {

        private Image waitIcon = ImageUtilities.loadImage("org/antlr/netbeans/editor/navigation/resources/wait.gif");

        public WaitNode() {
            super(Children.LEAF);
        }

        @Override
        public Image getIcon(int type) {
            return waitIcon;
        }

        @Override
        public Image getOpenedIcon(int type) {
            return getIcon(type);
        }

        @Override
        public String getDisplayName() {
            return Bundle.LBL_WaitNode();
        }

        @Override
        public String getHtmlDisplayName() {
            return getDisplayName();
        }

    }

    public static final class ElementChildren extends Children.Keys<Description> {

        private final NavigatorPanelUI ui;
        private final Factory nodeFactory;

        public ElementChildren(NavigatorPanelUI ui, Collection<Description> descriptions, Factory nodeFactory) {
            this.ui = ui;
            this.nodeFactory = nodeFactory;
            resetKeys(descriptions, ui.getFilters());
        }

        @Override
        protected Node[] createNodes(Description key) {
            return new Node[] { nodeFactory.createNode(ui, key) };
        }

        private void resetKeys(Collection<Description> descriptions, Filters filters) {
            setKeys(filters.filter(descriptions));
        }

    }

    public interface Factory {

        public NavigatorNode createNode(NavigatorPanelUI ui, Description key);

    }
}
