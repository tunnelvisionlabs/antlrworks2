/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.util.NotificationIcons;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.NotificationDisplayer;
import org.openide.cookies.EditorCookie;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
    category = "Debug",
    id = "org.antlr.works.editor.grammar.debugger.InterpretCurrentLexerAction")
@ActionRegistration(
    displayName = "#CTL_InterpretCurrentLexerAction")
@ActionReference(path = "Menu/BuildProject", position = 248, separatorBefore = 223)
@Messages("CTL_InterpretCurrentLexerAction=Interpret Lexer...")
public final class InterpretCurrentLexerAction implements ActionListener {

    private final EditorCookie context;

    public InterpretCurrentLexerAction(EditorCookie context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        Document document = context.getDocument();
        if (document == null) {
            displayError("This command is only valid in the context of a document.");
            return;
        }

        String mimeType = NbEditorUtilities.getMimeType(document);
        if (!GrammarEditorKit.GRAMMAR_MIME_TYPE.equals(mimeType)) {
            displayError("This command is only valid for ANTLR grammar files.");
            return;
        }

        if (GrammarEditorKit.isLegacyMode(document)) {
            displayError("This command is not valid in legacy (ANTLR 3) mode.");
            return;
        }

        DocumentSnapshot snapshot = VersionedDocumentUtilities.getVersionedDocument(document).getCurrentSnapshot();
        LexerInterpreterData lexerInterpreterData = LexerInterpreterData.buildFromSnapshot(snapshot);
        if (lexerInterpreterData == null) {
            displayError("An error occurred while constructing a lexer ATN from the grammar.");
            return;
        }

        File inputFile = new FileChooserBuilder(InterpretCurrentLexerAction.class)
            .setTitle("Select input file")
            .showOpenDialog();

        if (inputFile == null || !inputFile.isFile()) {
            return;
        }

        if (inputFile.length() > 1024 * 1024) {
            displayError("This specified input file is too large. Please choose a file smaller than 1MB.");
            return;
        }

        try {
            FileSystem fileSystem = FileUtil.createMemoryFileSystem();
            FileObject tempFileObject = FileUtil.copyFile(FileUtil.toFileObject(inputFile), fileSystem.getRoot(), inputFile.getName(), "linterp");
            DataObject od = DataObject.find(tempFileObject);

            EditorCookie ec = od.getLookup().lookup(EditorCookie.class);
            Document opened = ec.openDocument();
            if (opened != null) {
                opened.putProperty(LexerDebuggerEditorKit.PROP_INTERP_DATA, lexerInterpreterData);
                OpenCookie oc = od.getLookup().lookup(OpenCookie.class);
                if (oc != null) {
                    doOpen(oc);
                }
            }
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
            displayError("An error occurred. See the IDE Log output window for details.");
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
            displayError("An error occurred. See the IDE Log output window for details.");
        }
    }

    private void displayError(String message) {
        NotificationDisplayer.getDefault().notify("Interpret Current Lexer", NotificationIcons.ERROR, message, null);
    }

    private void doOpen(final OpenCookie oc) {
        if (SwingUtilities.isEventDispatchThread()) {
            oc.open();
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    oc.open();
                }
            });
        }
    }
}
