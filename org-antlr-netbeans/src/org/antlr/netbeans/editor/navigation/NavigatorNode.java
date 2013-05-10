/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.navigation;

import java.awt.Image;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private final Factory factory;
    private Description description;
    private OpenAction openAction;

    public NavigatorNode(@NonNull NavigatorPanelUI ui, @NonNull Description description, Factory nodeFactory) {
        super(description.getChildren().isEmpty() ? Children.LEAF : new ElementChildren(ui, description.getChildren(), nodeFactory), prepareLookup(description));
        this.ui = ui;
        this.description = description;
        this.factory = nodeFactory;
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
        if (context || description.getName() == null || description.getFileObject() == null || !description.getChildren().isEmpty()) {
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
        if (!description.getChildren().isEmpty() && !(ch instanceof ElementChildren)) {
            ch = new ElementChildren(ui, description.getChildren(), factory);
            setChildren(ch);
        }

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
        if (!newDescription.getChildren().isEmpty() && !(children instanceof ElementChildren)) {
            children = new ElementChildren(ui, newDescription.getChildren(), factory);
            setChildren(children);
        }

        if (children instanceof ElementChildren) {
            Set<Description> oldChildren = new HashSet<>(description.getChildren());

            // Create a hashtable which maps Description to node.
            // We will then identify the nodes by the description. The trick is
            // that the new and old description are equal and have the same hashcode
            Node[] nodes = children.getNodes(true);
            HashMap<Description, NavigatorNode> oldD2node = new HashMap<>();
            for (Node node : nodes) {
                oldD2node.put(((NavigatorNode)node).description, (NavigatorNode)node);
            }

            Collection<Description> newChildren = newDescription.getChildren();

            // Now refresh keys
            ((ElementChildren)children).resetKeys(newChildren, getUI().getFilters());

            // Reread nodes
            nodes = children.getNodes(true);

            for (Description newSub : newChildren) {
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

        private final Image waitIcon = ImageUtilities.loadImage("org/antlr/netbeans/editor/navigation/resources/wait.gif");

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
