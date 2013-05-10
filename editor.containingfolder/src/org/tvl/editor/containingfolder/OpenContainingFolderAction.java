/*
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.editor.containingfolder;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.util.ContextAwareAction;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.Utilities;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "CTL_OpenContainingFolder=Open Containing Folder"
})
@ActionID(id="org.tvl.editor.containingfolder.OpenContainingFolderAction", category="Window/SelectDocumentNode")
@ActionRegistration(displayName="#CTL_OpenContainingFolder", lazy=false)
@ActionReferences({
    @ActionReference(path="Shortcuts", name="DS-4"),
    @ActionReference(path="Menu/GoTo", position=2900),
    @ActionReference(path="Editors/TabActions", position=110),
})
public class OpenContainingFolderAction extends AbstractAction implements ContextAwareAction {
    private static final Logger LOGGER = Logger.getLogger(OpenContainingFolderAction.class.getName());

    private final Lookup lookup;
    private final File file;
    private final File containingFolder;

    public OpenContainingFolderAction() {
        this(null);
    }

    public OpenContainingFolderAction(Lookup lookup) {
        super(Bundle.CTL_OpenContainingFolder());
        this.lookup = lookup;

        FileObject fileObject = null;
        File folder = null;
        if (lookup != null) {
            fileObject = getFileFromLookup(lookup);
            if (fileObject != null) {
                final FileObject parent = fileObject.getParent();
                if (parent != null && parent.isFolder()) {
                    folder = FileUtil.toFile(parent);
                }
            }
        }

        this.file = fileObject != null ? FileUtil.toFile(fileObject) : null;
        this.containingFolder = folder;
    }

    @Override
    public boolean isEnabled() {
        return containingFolder != null
            && Desktop.isDesktopSupported()
            && Desktop.getDesktop().isSupported(Desktop.Action.OPEN);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (containingFolder == null) {
            return;
        }

        RequestProcessor.getDefault().post(new Runnable() {

            @Override
            public void run() {
                try {
                    if (Utilities.isWindows()) {
                        String systemRoot = System.getenv("SystemRoot");
                        File explorer = new File(systemRoot, "explorer.exe");
                        String args = null;
                        if (file != null) {
                            args = "/select,\"" + file.getAbsolutePath() + "\"";
                        }

                        Runtime.getRuntime().exec(String.format("%s %s", explorer.getAbsolutePath(), args));
                        return;
                    }

                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(containingFolder);
                } catch (IOException ex) {
                    LOGGER.log(Level.INFO, null, ex);
                }
            }

        });
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext) {
        return new OpenContainingFolderAction(actionContext);
    }

    private FileObject getFileFromLookup(Lookup lookup) {
        FileObject fileObject = lookup.lookup(FileObject.class);
        if (fileObject != null) {
            return fileObject;
        }

        DataObject dataObject = lookup.lookup(DataObject.class);
        return dataObject != null ? dataObject.getPrimaryFile() : null;
    }
}
