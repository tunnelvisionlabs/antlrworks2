/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.v4.Tool;
import org.antlr.v4.parse.ANTLRParser;
import org.antlr.v4.runtime.atn.ATNSerializer;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.LexerGrammar;
import org.antlr.v4.tool.ast.GrammarRootAST;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Sam Harwell
 */
public class LexerInterpreterData extends AbstractInterpreterData {
    private static final Logger LOGGER = Logger.getLogger(LexerInterpreterData.class.getName());

    public List<String> modeNames;

    public static LexerInterpreterData buildFromSnapshot(DocumentSnapshot snapshot) {
        Tool tool = new LexerTracingTool();

        ANTLRStringStream stream = new ANTLRStringStream(snapshot.getText());
        stream.name = snapshot.getVersionedDocument().getFileObject().getNameExt();
        GrammarRootAST ast = tool.parse(stream.name, stream);
        if (ast.grammarType == ANTLRParser.PARSER) {
            // load the correct lexer
            String vocabName = ast.getOptionString("tokenVocab");

            // try to find a lexer in the same folder with this name
            FileObject containingFolder = snapshot.getVersionedDocument().getFileObject().getParent();
            if (containingFolder == null) {
                LOGGER.log(Level.WARNING, "Could not find source for token vocabulary.");
                return null;
            }

            FileObject sourceFileObject = containingFolder.getFileObject(vocabName, "g4");
            if (sourceFileObject == null) {
                sourceFileObject = containingFolder.getFileObject(vocabName, "g3");
            }

            if (sourceFileObject == null) {
                sourceFileObject = containingFolder.getFileObject(vocabName, "g");
            }

            if (sourceFileObject == null) {
                LOGGER.log(Level.WARNING, "Could not find source for token vocabulary.");
                return null;
            }

            VersionedDocument sourceDocument = VersionedDocumentUtilities.getVersionedDocument(sourceFileObject);
            return buildFromSnapshot(sourceDocument.getCurrentSnapshot());
        }

        Grammar grammar = tool.createGrammar(ast);
        tool.process(grammar, false);

        LexerGrammar lexerGrammar;
        if (grammar instanceof LexerGrammar) {
            lexerGrammar = (LexerGrammar)grammar;
        } else {
            lexerGrammar = grammar.implicitLexer;
        }

        if (lexerGrammar == null) {
            return null;
        }

        LexerInterpreterData data = new LexerInterpreterData();
        data.grammarFileName = lexerGrammar.fileName;
        data.serializedAtn = ATNSerializer.getSerializedAsString(lexerGrammar.atn, Arrays.asList(lexerGrammar.getRuleNames()));
        data.vocabulary = lexerGrammar.getVocabulary();
        data.ruleNames = new ArrayList<>(lexerGrammar.rules.keySet());
        data.modeNames = new ArrayList<>(lexerGrammar.modes.keySet());
        return data;
    }

}
