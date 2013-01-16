/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar;

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
    "GrammarLoader_Name=Grammar Source Object"
})
@DataObject.Registration(
    displayName="#GrammarLoader_Name",
    mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE,
    position=99999)
@ActionReferences({
    @ActionReference(id =
        @ActionID(category = "System", id = "org.openide.actions.OpenAction"), path = "Loaders/" + GrammarEditorKit.GRAMMAR_MIME_TYPE + "/Actions", position = 100, separatorAfter = 200),
    @ActionReference(id =
        @ActionID(category = "Edit", id = "org.openide.actions.CutAction"), path = "Loaders/" + GrammarEditorKit.GRAMMAR_MIME_TYPE + "/Actions", position = 300),
    @ActionReference(id =
        @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"), path = "Loaders/" + GrammarEditorKit.GRAMMAR_MIME_TYPE + "/Actions", position = 400, separatorAfter = 500),
    @ActionReference(id =
        @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"), path = "Loaders/" + GrammarEditorKit.GRAMMAR_MIME_TYPE + "/Actions", position = 600),
    @ActionReference(id =
        @ActionID(category = "System", id = "org.openide.actions.RenameAction"), path = "Loaders/" + GrammarEditorKit.GRAMMAR_MIME_TYPE + "/Actions", position = 700, separatorAfter = 800),
    @ActionReference(id =
        @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"), path = "Loaders/" + GrammarEditorKit.GRAMMAR_MIME_TYPE + "/Actions", position = 900, separatorAfter = 1000),
    @ActionReference(id =
        @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"), path = "Loaders/" + GrammarEditorKit.GRAMMAR_MIME_TYPE + "/Actions", position = 1100, separatorAfter = 1200),
    @ActionReference(id =
        @ActionID(category = "System", id = "org.openide.actions.ToolsAction"), path = "Loaders/" + GrammarEditorKit.GRAMMAR_MIME_TYPE + "/Actions", position = 1300),
    @ActionReference(id =
        @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"), path = "Loaders/" + GrammarEditorKit.GRAMMAR_MIME_TYPE + "/Actions", position = 1400)
})
public class GrammarDataLoader extends UniFileLoader {

    public GrammarDataLoader() {
        super("org.antlr.works.editor.grammar.GrammarDataObject");
    }

    @Override
    protected void initialize() {
        super.initialize();
        ExtensionList extensions = new ExtensionList();
        extensions.addExtension(".g");
        extensions.addExtension(".g3");
        extensions.addExtension(".g4");
        extensions.addMimeType(GrammarEditorKit.GRAMMAR_MIME_TYPE);
        setExtensions(extensions);
    }

    @Override
    protected String actionsContext() {
        return "Loaders/" + GrammarEditorKit.GRAMMAR_MIME_TYPE + "/Actions/";
    }

    @Override
    protected String defaultDisplayName() {
        return Bundle.GrammarLoader_Name();
    }

    @Override
    protected MultiDataObject createMultiObject(FileObject primaryFile) throws DataObjectExistsException, IOException {
        if (getExtensions().isRegistered(primaryFile)) {
            return new GrammarDataObject(primaryFile, this);
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
