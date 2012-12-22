/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.io.IOException;
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
    "LexerDebuggerLoader_Name=Lexer Debugger Source Object"
})
@DataObject.Registration(
    displayName="#LexerDebuggerLoader_Name",
    mimeType=LexerDebuggerEditorKit.LEXER_DEBUGGER_MIME_TYPE,
    position=99999)
public class LexerDebuggerDataLoader extends UniFileLoader {

    public LexerDebuggerDataLoader() {
        super("org.antlr.works.editor.grammar.debugger.LexerDebuggerDataObject");
    }

    @Override
    protected void initialize() {
        super.initialize();
        ExtensionList extensions = new ExtensionList();
        extensions.addExtension(".ldbg");
        extensions.addExtension(".linterp");
        extensions.addMimeType(LexerDebuggerEditorKit.LEXER_DEBUGGER_MIME_TYPE);
        setExtensions(extensions);
    }

    @Override
    protected String actionsContext() {
        return "Loaders/" + LexerDebuggerEditorKit.LEXER_DEBUGGER_MIME_TYPE + "/Actions/";
    }

    @Override
    protected String defaultDisplayName() {
        return Bundle.LexerDebuggerLoader_Name();
    }

    @Override
    protected MultiDataObject createMultiObject(FileObject primaryFile) throws DataObjectExistsException, IOException {
        if (getExtensions().isRegistered(primaryFile)) {
            return new LexerDebuggerDataObject(primaryFile, this);
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
