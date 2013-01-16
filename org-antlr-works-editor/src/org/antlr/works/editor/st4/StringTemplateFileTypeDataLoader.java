/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4;

import java.io.IOException;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.ExtensionList;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiDataObject.Entry;
import org.openide.loaders.UniFileLoader;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "StringTemplateLoader_Name=StringTemplate Source Object"
})
@DataObject.Registration(
    displayName="#StringTemplateLoader_Name",
    mimeType=StringTemplateEditorKit.TEMPLATE_MIME_TYPE,
    position=99999)
@ActionReferences({
    @ActionReference(id =
        @ActionID(category = "System", id = "org.openide.actions.OpenAction"), path = "Loaders/" + StringTemplateEditorKit.TEMPLATE_MIME_TYPE + "/Actions", position = 100, separatorAfter = 200),
    @ActionReference(id =
        @ActionID(category = "Edit", id = "org.openide.actions.CutAction"), path = "Loaders/" + StringTemplateEditorKit.TEMPLATE_MIME_TYPE + "/Actions", position = 300),
    @ActionReference(id =
        @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"), path = "Loaders/" + StringTemplateEditorKit.TEMPLATE_MIME_TYPE + "/Actions", position = 400, separatorAfter = 500),
    @ActionReference(id =
        @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"), path = "Loaders/" + StringTemplateEditorKit.TEMPLATE_MIME_TYPE + "/Actions", position = 600),
    @ActionReference(id =
        @ActionID(category = "System", id = "org.openide.actions.RenameAction"), path = "Loaders/" + StringTemplateEditorKit.TEMPLATE_MIME_TYPE + "/Actions", position = 700, separatorAfter = 800),
    @ActionReference(id =
        @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"), path = "Loaders/" + StringTemplateEditorKit.TEMPLATE_MIME_TYPE + "/Actions", position = 900, separatorAfter = 1000),
    @ActionReference(id =
        @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"), path = "Loaders/" + StringTemplateEditorKit.TEMPLATE_MIME_TYPE + "/Actions", position = 1100, separatorAfter = 1200),
    @ActionReference(id =
        @ActionID(category = "System", id = "org.openide.actions.ToolsAction"), path = "Loaders/" + StringTemplateEditorKit.TEMPLATE_MIME_TYPE + "/Actions", position = 1300),
    @ActionReference(id =
        @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"), path = "Loaders/" + StringTemplateEditorKit.TEMPLATE_MIME_TYPE + "/Actions", position = 1400)
})
public class StringTemplateFileTypeDataLoader extends UniFileLoader {

    public StringTemplateFileTypeDataLoader() {
        super("org.antlr.works.editor.st4.StringTemplateFileTypeDataObject");
    }

    @Override
    protected void initialize() {
        super.initialize();
        ExtensionList extensions = new ExtensionList();
        extensions.addExtension(".st");
        extensions.addExtension(".stg");
        extensions.addMimeType(StringTemplateEditorKit.TEMPLATE_MIME_TYPE);
        setExtensions(extensions);
    }

    @Override
    protected String actionsContext() {
        return "Loaders/" + StringTemplateEditorKit.TEMPLATE_MIME_TYPE + "/Actions/";
    }

    @Override
    protected String defaultDisplayName() {
        return Bundle.StringTemplateLoader_Name();
    }

    @Override
    protected MultiDataObject createMultiObject(FileObject primaryFile) throws DataObjectExistsException, IOException {
        if (getExtensions().isRegistered(primaryFile)) {
            return new StringTemplateFileTypeDataObject(primaryFile, this);
        }

        return null;
    }

    @Override
    protected FileObject findPrimaryFile(FileObject fo) {
        // never recognize folders
        if (fo.isFolder()) {
            return null;
        }

        return super.findPrimaryFile(fo);
    }

    @Override
    protected Entry createPrimaryEntry(MultiDataObject obj, FileObject primaryFile) {
        return super.createPrimaryEntry(obj, primaryFile);
    }

}
