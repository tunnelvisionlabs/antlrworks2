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

package com.tvl.antlrworks.project;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.io.CharConversionException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import static com.tvl.antlrworks.project.Bundle.*;
import org.netbeans.spi.project.AuxiliaryConfiguration;
import org.openide.cookies.EditCookie;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.URLMapper;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.Node;
import org.openide.util.ContextAwareAction;
import org.openide.util.Exceptions;
import org.openide.util.Mutex;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/** The util methods for projectui module.
 *
 * @author  Jiri Rechtacek
 */
public class ProjectUtilities {
    
//    static final String OPEN_FILES_NS = "http://www.netbeans.org/ns/projectui-open-files/1"; // NOI18N
//    static final String OPEN_FILES_ELEMENT = "open-files"; // NOI18N
//    static final String FILE_ELEMENT = "file"; // NOI18N
//
//    // support class for xtesting in OpenProjectListTest
//    static OpenCloseProjectDocument OPEN_CLOSE_PROJECT_DOCUMENT_IMPL = new OpenCloseProjectDocument () {
//        @Override
//        public boolean open (FileObject fo) {
//            DataObject dobj;
//            try {
//                dobj = DataObject.find (fo);
//            } catch (DataObjectNotFoundException donfo) {
//                assert false : "DataObject must exist for " + fo;
//                return false;
//            }
//            EditCookie ec = dobj.getLookup().lookup(EditCookie.class);
//            OpenCookie oc = dobj.getLookup().lookup(OpenCookie.class);
//            if (ec != null) {
//                ec.edit();
//            } else if (oc != null) {
//                oc.open();
//            } else {
//                ERR.log(Level.INFO, "No EditCookie nor OpenCookie for {0}", dobj);
//                return false;
//            }
//            return true;
//        }
//
//        @Override
//        public Map<Project,Set<String>> close(final Project[] projects,
//                                                    final boolean notifyUI) {
//            final Wrapper wr = new Wrapper();
//
//            wr.urls4project = new HashMap<Project,Set<String>>();
//            if (SwingUtilities.isEventDispatchThread()) {
//                doClose(projects, notifyUI, wr);
//            } else {
//                try {
//                    SwingUtilities.invokeAndWait(new Runnable() {
//                        @Override
//                         public void run() {
//                             doClose(projects, notifyUI, wr);
//                         }
//                     });
//                }
//                catch (Exception ex) {
//                    Exceptions.printStackTrace(ex);
//                }
//            }
//            return wr.urls4project;
//        }
//
//        private void doClose(Project[] projects, boolean notifyUI, Wrapper wr) {
//            List<Project> listOfProjects = Arrays.asList(projects);
//            Set<DataObject> openFiles = new HashSet<DataObject>();
//            final Set<TopComponent> tc2close = new HashSet<TopComponent>();
//            WindowManager wm = WindowManager.getDefault();
//            ERR.finer("Closing TCs");
//            for (Mode mode : wm.getModes()) {
//                //#84546 - this condituon should allow us to close just editor related TCs that are in any imaginable mode.
//                if (!wm.isEditorMode(mode)) {
//                    continue;
//                }
//                ERR.log(Level.FINER, "Closing TCs in mode {0}", mode.getName());
//                for (TopComponent tc : wm.getOpenedTopComponents(mode)) {
//                DataObject dobj = tc.getLookup().lookup(DataObject.class);
//
//                if (dobj != null) {
//                    FileObject fobj = dobj.getPrimaryFile();
//                    Project owner = FileOwnerQuery.getOwner(fobj);
//                    ERR.log(Level.FINER, "Found {0} owned by {1} in {2} of {3}", new Object[] {fobj, owner, tc.getName(), tc.getClass()});
//
//                    if (listOfProjects.contains(owner)) {
//                        if (notifyUI) {
//                            openFiles.add(dobj);
//                            tc2close.add(tc);
//                        } else if (!dobj.isModified()) {
//                            // when not called from UI, only include TCs that arenot modified
//                            tc2close.add(tc);
//                        }
//                        if (!wr.urls4project.containsKey(owner)) {
//                            // add project
//                            wr.urls4project.put(owner, new LinkedHashSet<String>());
//                        }
//                        wr.urls4project.get(owner).add(dobj.getPrimaryFile().toURL().toExternalForm());
//                    }
//                } else {
//                    ERR.log(Level.FINE, "#194243: no DataObject in lookup of {0} of {1}", new Object[] {tc.getName(), tc.getClass()});
//                }
//            }
//            }
//            if (notifyUI) {
//                for (DataObject dobj : DataObject.getRegistry().getModifiedSet()) {
//                    FileObject fobj = dobj.getPrimaryFile();
//                    Project owner = FileOwnerQuery.getOwner(fobj);
//
//                    if (listOfProjects.contains(owner) &&
//                        !openFiles.contains(dobj)) {
//                        openFiles.add(dobj);
//                    }
//                }
//            }
//            if (!notifyUI ||
//                (!openFiles.isEmpty() && ExitDialog.showDialog(openFiles))) {
//                // close documents
//                for (TopComponent tc : tc2close) {
//                    tc.close();
//                }
//            } else {
//                // signal that close was vetoed
//                if (!openFiles.isEmpty()) {
//                    wr.urls4project = null;
//                }
//            }
//        }
//    };
//
//    private static class Wrapper {
//        Map<Project,Set<String>> urls4project;
//    }
//
//    private static final Logger ERR = Logger.getLogger(ProjectUtilities.class.getName());
//
//    private ProjectUtilities() {}
//
//    public static void selectAndExpandProject( final Project p ) {
//
//        // invoke later to select the being opened project if the focus is outside ProjectTab
//        SwingUtilities.invokeLater (new Runnable () {
//
//            @Override
//            public void run () {
//                final ProjectTab ptLogial = ProjectTab.findDefault(ProjectTab.ID_LOGICAL);
//
//                Node root = ptLogial.getExplorerManager ().getRootContext ();
//                // Node projNode = root.getChildren ().findChild( p.getProjectDirectory().getName () );
//                Node projNode = null;
//                for (Node n : root.getChildren().getNodes()) {
//                    Project prj = n.getLookup().lookup(Project.class);
//                    if (prj != null && prj.getProjectDirectory().equals(p.getProjectDirectory())) {
//                        projNode = n;
//                        break;
//                    }
//                }
//                if (projNode == null) {
//                    // fallback..
//                    projNode = root.getChildren ().findChild( ProjectUtils.getInformation( p ).getName() );
//                }
//
//                if ( projNode != null ) {
//                    try {
//                        ptLogial.getExplorerManager ().setSelectedNodes( new Node[] { projNode } );
//                        ptLogial.expandNode( projNode );
//                        // ptLogial.open ();
//                        // ptLogial.requestActive ();
//                    } catch (Exception ignore) {
//                        // may ignore it
//                    }
//                }
//            }
//        });
//
//    }
//
//    /** Invokes the preferred action on given object and tries to select it in
//     * corresponding view, e.g. in logical view if possible otherwise
//     * in physical project's view.
//     * Note: execution this methods can invokes new threads to assure the action
//     * is called in EQ.
//     *
//     * @param newDo new data object
//     */
//    public static void openAndSelectNewObject (final DataObject newDo) {
//        // call the preferred action on main class
//        Mutex.EVENT.writeAccess (new Runnable () {
//            @Override
//            public void run () {
//                final Node node = newDo.getNodeDelegate ();
//                Action a = node.getPreferredAction();
//                if (a instanceof ContextAwareAction) {
//                    a = ((ContextAwareAction) a).createContextAwareInstance(node.getLookup ());
//                }
//                if (a != null) {
//                    a.actionPerformed(new ActionEvent(node, ActionEvent.ACTION_PERFORMED, "")); // NOI18N
//                }
//
//                // next action -> expand && select main class in package view
//                final ProjectTab ptLogical = ProjectTab.findDefault(ProjectTab.ID_LOGICAL);
//                final ProjectTab ptPhysical = ProjectTab.findDefault(ProjectTab.ID_PHYSICAL);
//                ProjectTab.RP.post(new Runnable() {
//                    public @Override void run() {
//                        ProjectTab tab = ptLogical;
//                        Node n = tab.findNode(newDo.getPrimaryFile());
//                        if (n == null) {
//                            tab = ptPhysical;
//                            n = tab.findNode(newDo.getPrimaryFile());
//                        }
//                        if (n != null) {
//                            tab.selectNode(n);
//                        }
//                    }
//                });
//            }
//        });
//    }
//
//    /** Makes the project tab visible
//     * @param requestFocus if set to true the project tab will not only become visible but also
//     *        will gain focus
//     */
//    public static void makeProjectTabVisible() {
//        if (Boolean.getBoolean("project.tab.no.selection")) {
//            return;
//        }
//        ProjectTab ptLogical = ProjectTab.findDefault(ProjectTab.ID_LOGICAL);
//        ptLogical.open();
//        ptLogical.requestActive();
//    }
    
    /** Checks if the given file name can be created in the target folder.
     *
     * @param targetFolder target folder (e.g. source group)
     * @param folderName name of the folder relative to target folder (null or /-separated)
     * @param newObjectName name of created file
     * @param extension extension of created file
     * @param allowFileSeparator if '/' (and possibly other file separator, see {@link FileUtil#createFolder FileUtil#createFolder})
     *                           is allowed in the newObjectName
     * @return localized error message (HTML-safe) or null if all right
     */    
    @Messages({
        "# {0} - name of the file", "# {1} - an integer representing the invalid characters:", "#       0: both '/' and '\\' are invalid", "#       1: '\\' is invalid", "MSG_not_valid_filename=The filename {0} is not permitted as it contains {1,choice,0#a slash (/) or a backslash (\\)|1#a backslash (\\)}.",
        "# {0} - name of the file", "# {1} - an integer representing the invalid characters:", "#       0: both '/' and '\\' are invalid", "#       1: '\\' is invalid", "MSG_not_valid_folder=The folder name {0} is not permitted as it contains {1,choice,0#a slash (/) or a backslash (\\)|1#a backslash (\\)}.",
        "MSG_fs_or_folder_does_not_exist=The target folder does not exist.",
        "MSG_fs_is_readonly=The target folder is read-only.",
        "# {0} - name of the existing file", "MSG_file_already_exist=The file {0} already exists."
    })
    public static String canUseFileName (FileObject targetFolder, String folderName, String newObjectName,
            String extension, boolean allowFileSeparator, boolean freeFileExtension) {
        assert newObjectName != null; // SimpleTargetChooserPanel.isValid returns false if it is... XXX should it use an error label instead?

        boolean allowSlash = false;
        boolean allowBackslash = false;
        int errorVariant = 0;
        
        if (allowFileSeparator) {
            if (File.separatorChar == '\\') {
                errorVariant = 3;
                allowSlash = allowBackslash = true;
            } else {
                errorVariant = 1;
                allowSlash = true;
            }
        }
        
        if ((!allowSlash && newObjectName.indexOf('/') != -1) || (!allowBackslash && newObjectName.indexOf('\\') != -1)) {
            //if errorVariant == 3, the test above should never be true:
            assert errorVariant == 0 || errorVariant == 1 : "Invalid error variant: " + errorVariant;
            
            return MSG_not_valid_filename(safeEncode(newObjectName), errorVariant);
        }
        
        // test whether the selected folder on selected filesystem already exists
        if (targetFolder == null) {
            if (folderName == null || folderName.isEmpty()) {
                return MSG_fs_or_folder_does_not_exist();
            }

            targetFolder = FileUtil.toFileObject(new File(folderName));
            if (targetFolder == null) {
                return MSG_fs_or_folder_does_not_exist();
            }

            folderName = "";
        }
        
        // target directory should be writable
        File targetDir = folderName != null ? new File (FileUtil.toFile (targetFolder), folderName) : FileUtil.toFile (targetFolder);
        if (targetDir != null) {
            if (targetDir.exists () && ! targetDir.canWrite ()) {
                return MSG_fs_is_readonly();
            }
        } else if (! targetFolder.canWrite ()) {
            return MSG_fs_is_readonly();
        }

        // file should not already exist
        StringBuilder relFileName = new StringBuilder();
        if (folderName != null) {
            if (!allowBackslash && folderName.indexOf('\\') != -1) {
                return MSG_not_valid_folder(safeEncode(folderName), 1);
            }
            relFileName.append(folderName);
            relFileName.append('/');
        }
        relFileName.append(newObjectName);
        String ext = "";
        if (extension != null && extension.length() != 0 && (!freeFileExtension || newObjectName.indexOf('.') == -1)) {
            ext = "." + extension;
            relFileName.append(ext);
        }
        if (targetFolder.getFileObject(relFileName.toString()) != null) {
            return MSG_file_already_exist(safeEncode(newObjectName + ext));
        }
        
        // all ok
        return null;
    }
    private static String safeEncode(String text) { // #208432
        if (text.length() > 30) {
            text = text.substring(0, 30) + '…';
        }
        try {
            return XMLUtil.toElementContent(text.replaceAll("\\s+", " "));
        } catch (CharConversionException ex) {
            return text;
        }
    }
    
    
    public static class WaitCursor implements Runnable {
        
        private boolean show;
        
        private WaitCursor( boolean show ) {
            this.show = show;
        }
       
        public static void show() {            
            invoke( new WaitCursor( true ) );
        }
        
        public static void hide() {
            invoke( new WaitCursor( false ) );            
        }
        
        private static void invoke( WaitCursor wc ) {
            if (GraphicsEnvironment.isHeadless()) {
                return;
            }
            if ( SwingUtilities.isEventDispatchThread() ) {
                wc.run();
            }
            else {
                SwingUtilities.invokeLater( wc );
            }
        }
        
        @Override
        public void run() {
            try {            
                JFrame f = (JFrame)WindowManager.getDefault ().getMainWindow ();
                Component c = f.getGlassPane ();
                c.setVisible ( show );
                c.setCursor (show ? Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR) : null);
            } 
            catch (NullPointerException npe) {
                Exceptions.printStackTrace(npe);
            }
        }
    }
    
//    /** Closes all documents in editor area which are owned by one of given projects.
//     * If some documents are modified then an user is notified by Save/Discard/Cancel dialog.
//     * Dialog is showed only once for all project's documents together.
//     * URLs of closed documents are stored to <code>private.xml</code>.
//     *
//     * @param p project to close
//     * @return false if the user cancelled the Save/Discard/Cancel dialog, true otherwise
//     */
//    public static boolean closeAllDocuments(Project[] projects, boolean notifyUI) {
//        if (projects == null) {
//            throw new IllegalArgumentException ("No projects are specified."); // NOI18N
//        }
//
//        if (projects.length == 0) {
//            // no projects to close, no documents will be closed
//            return true;
//        }
//
//        Map<Project,Set<String>> urls4project = OPEN_CLOSE_PROJECT_DOCUMENT_IMPL.close(projects, notifyUI);
//
//        if (urls4project != null) {
//            // store project's documents
//            // loop all project being closed
//            for (Map.Entry<Project,Set<String>> entry : urls4project.entrySet()) {
//                storeProjectOpenFiles(entry.getKey(), entry.getValue());
//            }
//        }
//
//        return urls4project != null;
//    }
//
//    static private void storeProjectOpenFiles(Project p, Set<String> urls) {
//        AuxiliaryConfiguration aux = ProjectUtils.getAuxiliaryConfiguration(p);
//        aux.removeConfigurationFragment (OPEN_FILES_ELEMENT, OPEN_FILES_NS, false);
//
//        Document xml = XMLUtil.createDocument (OPEN_FILES_ELEMENT, OPEN_FILES_NS, null, null);
//        Element fileEl;
//
//        Element openFiles = xml.createElementNS (OPEN_FILES_NS, OPEN_FILES_ELEMENT);
//
//        // loop all open files of given project
//        for (String url : urls) {
//            fileEl = openFiles.getOwnerDocument ().createElementNS(OPEN_FILES_NS, FILE_ELEMENT);
//            fileEl.appendChild(fileEl.getOwnerDocument().createTextNode(url));
//            openFiles.appendChild (fileEl);
//        }
//
//        aux.putConfigurationFragment (openFiles, false);
//    }
//
//    /** Opens the project's files read from the private <code>project.xml</code> file
//     *
//     * @param p project
//     */
//    public static void openProjectFiles (Project p) {
//        ERR.log(Level.FINE, "Trying to open files from {0}...", p);
//
//        AuxiliaryConfiguration aux = ProjectUtils.getAuxiliaryConfiguration(p);
//
//        Element openFiles = aux.getConfigurationFragment (OPEN_FILES_ELEMENT, OPEN_FILES_NS, false);
//        if (openFiles == null) {
//            return;
//        }
//
//        NodeList list = openFiles.getElementsByTagNameNS(OPEN_FILES_NS, FILE_ELEMENT);
//
//        for (int i = 0; i < list.getLength (); i++) {
//            String url = list.item (i).getChildNodes ().item (0).getNodeValue ();
//            ERR.log(Level.FINE, "Will try to open {0}", url);
//            FileObject fo;
//            try {
//                fo = URLMapper.findFileObject (new URL (url));
//            } catch (MalformedURLException mue) {
//                assert false : "MalformedURLException in " + url;
//                continue;
//            }
//            if (fo == null) {
//                ERR.log(Level.FINE, "Could not find {0}", url);
//                continue;
//            }
//
//            //#109676
//            if (FileOwnerQuery.getOwner(fo) != p) {
//                ERR.log(Level.FINE, "File {0} doesn''t belong to project at {1}", new Object[] {url, p.getProjectDirectory().getPath()});
//                continue;
//            }
//
//            OPEN_CLOSE_PROJECT_DOCUMENT_IMPL.open (fo);
//        }
//
//        // clean-up stored files
//        aux.removeConfigurationFragment (OPEN_FILES_ELEMENT, OPEN_FILES_NS, false);
//    }
//
//    // interface for handling project's documents stored in project private.xml
//    // it serves for a unit test of OpenProjectList
//    interface OpenCloseProjectDocument {
//
//        // opens stored document in the document area
//        boolean open(FileObject fo);
//
//        // closes documents of given projects and returns mapped document's urls by project
//        // it's used as base for storing documents in project private.xml
//        Map<Project,Set<String>> close(Project[] projects, boolean notifyUI);
//    }
    
}
