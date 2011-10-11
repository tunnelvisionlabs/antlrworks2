/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
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
 * @author sam
 */
public abstract class NavigatorNode extends AbstractNode {

    private static Node WAIT_NODE;
    private Description description;
    private OpenAction openAction;

    public NavigatorNode(Description description, Factory nodeFactory) {
        super(description.getChildren() == null ? Children.LEAF : new ElementChildren(description.getChildren(), description.getUI().getFilters(), nodeFactory), prepareLookup(description));
        this.description = description;
        setDisplayName(description.getName());
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
            return description.getUI().getActions();
        } else {
            Action[] panelActions = description.getUI().getActions();

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
            boolean scrollOnExpand = description.getUI().getScrollOnExpand();
            description.getUI().setScrollOnExpand(false);
            ((ElementChildren)ch).resetKeys(description.getChildren(), description.getUI().getFilters());
            for (Node sub : ch.getNodes()) {
                description.getUI().expandNode(sub);
                ((NavigatorNode)sub).refreshRecursively();
            }

            description.getUI().setScrollOnExpand(scrollOnExpand);
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
            ((ElementChildren)children).resetKeys(newDescription.getChildren(), newDescription.getUI().getFilters());

            // Reread nodes
            nodes = children.getNodes(true);

            for (Description newSub : newDescription.getChildren()) {
                NavigatorNode node = oldD2node.get(newSub);
                if (node != null) { // filtered out
                    if (!oldChildren.contains(newSub) && node.getChildren() != Children.LEAF) {
                        description.getUI().expandNode(node); // Make sure new nodes get expanded
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
            return NbBundle.getMessage(NavigatorNode.class, "LBL_WaitNode");
        }

        @Override
        public String getHtmlDisplayName() {
            return getDisplayName();
        }

    }

    public static final class ElementChildren extends Children.Keys<Description> {

        private final Factory nodeFactory;

        public ElementChildren(Collection<Description> descriptions, Filters filters, Factory nodeFactory) {
            this.nodeFactory = nodeFactory;
            resetKeys(descriptions, filters);
        }

        @Override
        protected Node[] createNodes(Description key) {
            return new Node[] { nodeFactory.createNode(key) };
        }

        private void resetKeys(Collection<Description> descriptions, Filters filters) {
            setKeys(filters.filter(descriptions));
        }

    }

    public interface Factory {

        public NavigatorNode createNode(Description key);

    }
}
