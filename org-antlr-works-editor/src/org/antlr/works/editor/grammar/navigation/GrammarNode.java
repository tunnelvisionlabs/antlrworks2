/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package org.antlr.works.editor.grammar.navigation;

import java.awt.Image;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.swing.Action;
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
import org.openide.util.lookup.InstanceContent.Convertor;

public class GrammarNode extends AbstractNode {

    private static Node WAIT_NODE;
    private Description description;

    public GrammarNode(Description description) {
        super(description.children == null ? Children.LEAF : new ElementChildren(description.children, description.ui.getFilters()), prepareLookup(description));
        this.description = description;
        setDisplayName(description.name);
    }

    @Override
    public Image getIcon(int type) {
        String name = description.name;
        if (description.children != null && !description.children.isEmpty()) {
            name = description.children.iterator().next().name;
        }

        if (name == null) {
            return super.getIcon(type);
        } else if (Character.isLowerCase(name.charAt(0))) {
            return ImageUtilities.loadImage("org/antlr/works/editor/grammar/navigation/resources/parsericon.png");
        } else {
            return ImageUtilities.loadImage("org/antlr/works/editor/grammar/navigation/resources/lexericon.png");
        }
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public String getDisplayName() {
        if (description.name != null) {
            return description.name;
        }

        if (description.fileObject != null) {
            return description.fileObject.getNameExt();
        }

        return null;
    }

    @Override
    public String getHtmlDisplayName() {
        return description.htmlHeader;
    }

    @Override
    public Action[] getActions(boolean context) {
        return description.ui.getActions();
        /*if (context || description.name == null) {
            return description.ui.getActions();
        } else {
            Action[] panelActions = description.ui.getActions();

            int extraActionCount = 2;
            Action[] actions = new Action[extraActionCount + panelActions.length];
            actions[0] = getOpenAction();
            actions[1] = null;

            for (int i = 0; i < panelActions.length; i++) {
                actions[extraActionCount + i] = panelActions[i];
            }

            return actions;
        }*/
    }

    @Override
    public Action getPreferredAction() {
        //return getOpenAction();
        return super.getPreferredAction();
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
            boolean scrollOnExpand = description.ui.getScrollOnExpand();
            description.ui.setScrollOnExpand(false);
            ((ElementChildren)ch).resetKeys(description.children, description.ui.getFilters());
            for (Node sub : ch.getNodes()) {
                description.ui.expandNode(sub);
                ((GrammarNode)sub).refreshRecursively();
            }

            description.ui.setScrollOnExpand(scrollOnExpand);
        }
    }

    public void updateRecursively(Description newDescription) {
        Children children = getChildren();
        if (children instanceof ElementChildren) {
            HashSet<Description> oldChildren = new HashSet<Description>(description.children);

            // Create a hashtable which maps Description to node.
            // We will then identify the nodes by the description. The trick is
            // that the new and old description are equal and have the same hashcode
            Node[] nodes = children.getNodes(true);
            HashMap<Description, GrammarNode> oldD2node = new HashMap<Description, GrammarNode>();
            for (Node node : nodes) {
                oldD2node.put(((GrammarNode)node).description, (GrammarNode)node);
            }

            // Now refresh keys
            ((ElementChildren)children).resetKeys(newDescription.children, newDescription.ui.getFilters());

            // Reread nodes
            nodes = children.getNodes(true);

            for (Description newSub : newDescription.children) {
                GrammarNode node = oldD2node.get(newSub);
                if (node != null) { // filtered out
                    if (!oldChildren.contains(newSub) && node.getChildren() != Children.LEAF) {
                        description.ui.expandNode(node); // Make sure new nodes get expanded
                    }
                    node.updateRecursively(newSub); // update the node recursively
                }
            }
        }

        Description oldDescription = description; // Remember old description
        description = newDescription; // set new descrioption to the new node
        if (oldDescription.htmlHeader != null && !oldDescription.htmlHeader.equals(description.htmlHeader)) {
            // Different headers => we need to fire displayname change
            fireDisplayNameChange(oldDescription.htmlHeader, description.htmlHeader);
        }

        /*if (oldDescription.modifiers != null && !oldDescription.modifiers.equals(newDescription.modifiers)) {
        fireIconChange();
        fireOpenedIconChange();
        }*/
    }

    /*private synchronized Action getOpenAction() {
        if (openAction == null) {
            FileObject fileObject = description.getFileObject();
            openAction = new OpenAction(description);
        }

        return openAction;
    }*/

    private static Lookup prepareLookup(Description d) {
        InstanceContent instanceContent = new InstanceContent();

        instanceContent.add(d, ConvertDescriptionToFileObject);
        instanceContent.add(d, ConvertDescriptionToDataObject);

        return new AbstractLookup(instanceContent);
    }

    private static final Convertor<Description, FileObject> ConvertDescriptionToFileObject =
        new Convertor<Description, FileObject>() {

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
    private static final Convertor<Description, DataObject> ConvertDescriptionToDataObject =
        new Convertor<Description, DataObject>() {

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

        private Image waitIcon = ImageUtilities.loadImage("org/antlr/works/editor/grammar/navigation/resources/wait.gif");

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
            return NbBundle.getMessage(GrammarNode.class, "LBL_WaitNode");
        }

        @Override
        public String getHtmlDisplayName() {
            return getDisplayName();
        }

    }

    public static final class ElementChildren extends Children.Keys<Description> {

        public ElementChildren(Collection<Description> descriptions, GrammarRuleFilters filters) {
            resetKeys(descriptions, filters);
        }

        @Override
        protected Node[] createNodes(Description key) {
            return new Node[]{new GrammarNode(key)};
        }

        private void resetKeys(Collection<Description> descriptions, GrammarRuleFilters filters) {
            setKeys(filters.filter(descriptions));
        }

    }

    public static class Description {

        public static final Comparator<Description> ALPHA_COMPARATOR =
            new DescriptionComparator(true);
        public static final Comparator<Description> POSITION_COMPARATOR =
            new DescriptionComparator(false);

        final GrammarRulesPanelUI ui;
        FileObject fileObject;
        String name;
        Collection<Description> children;
        String htmlHeader;
        boolean inherited;
        long pos;

        public Description(GrammarRulesPanelUI ui) {
            this.ui = ui;
        }

        public Description(GrammarRulesPanelUI ui, String name) {
            this.ui = ui;
            this.name = name;
        }

        public FileObject getFileObject() {
            return fileObject;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Description)) {
                return false;
            }

            Description other = (Description)obj;
            if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
            return hash;
        }

        private static class DescriptionComparator implements Comparator<Description> {
            private final boolean alpha;

            DescriptionComparator(boolean alpha) {
                this.alpha = alpha;
            }

            @Override
            public int compare(Description d1, Description d2) {

                if (alpha) {
                    return alphaCompare(d1, d2);
                } else {
                    if (d1.inherited && !d2.inherited) {
                        return 1;
                    }
                    if (!d1.inherited && d2.inherited) {
                        return -1;
                    }
                    if (d1.inherited && d2.inherited) {
                        return alphaCompare(d1, d2);
                    }
                    return d1.pos == d2.pos ? 0 : d1.pos < d2.pos ? -1 : 1;
                }
            }

            int alphaCompare(Description d1, Description d2) {
                /*if ( k2i(d1.kind) != k2i(d2.kind) ) {
                return k2i(d1.kind) - k2i(d2.kind);
                }*/

                return d1.name.compareTo(d2.name);
            }

            /*int k2i( ElementKind kind ) {
            switch( kind ) {
            case CONSTRUCTOR:
            return 1;
            case METHOD:
            return 2;
            case FIELD:
            return 3;
            case CLASS:
            case INTERFACE:
            case ENUM:
            case ANNOTATION_TYPE:
            return 4;
            default:
            return 100;
            }
            }*/
        }
    }
}
