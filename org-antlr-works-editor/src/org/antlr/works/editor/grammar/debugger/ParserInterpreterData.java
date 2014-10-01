/*
 *  Copyright (c) 2014 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.v4.Tool;
import org.antlr.v4.runtime.atn.ATNSerializer;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.LexerGrammar;
import org.antlr.v4.tool.ast.GrammarRootAST;
import org.antlr.works.editor.grammar.parser.CompiledModelParserV4.CustomErrorManager;
import org.antlr.works.editor.grammar.parser.CompiledModelParserV4.CustomTool;
import org.antlr.works.editor.grammar.parser.CompiledModelParserV4.ErrorListener;

/**
 *
 * @author Sam Harwell
 */
public class ParserInterpreterData extends AbstractInterpreterData {

    public LexerInterpreterData lexerInterpreterData;

    public static ParserInterpreterData buildFromSnapshot(DocumentSnapshot snapshot) {
        LexerInterpreterData lexerInterpreterData = LexerInterpreterData.buildFromSnapshot(snapshot);
        if (lexerInterpreterData == null) {
            return null;
        }

        List<SyntaxError> syntaxErrors = new ArrayList<>();
        Tool tool = new CustomTool(snapshot);
        tool.errMgr = new CustomErrorManager(tool);
        tool.addListener(new ErrorListener(snapshot, tool, syntaxErrors));
        tool.libDirectory = new File(snapshot.getVersionedDocument().getFileObject().getPath()).getParent();

        ANTLRStringStream stream = new ANTLRStringStream(snapshot.getText());
        stream.name = snapshot.getVersionedDocument().getFileObject().getNameExt();
        GrammarRootAST ast = tool.parse(stream.name, stream);
        Grammar grammar = tool.createGrammar(ast);
        if (grammar instanceof LexerGrammar) {
            return null;
        }

        tool.process(grammar, false);

        ParserInterpreterData data = new ParserInterpreterData();

        // start by filling in the lexer data
        data.lexerInterpreterData = lexerInterpreterData;

        // then fill in the parser data
        data.grammarFileName = grammar.fileName;
        data.serializedAtn = ATNSerializer.getSerializedAsString(grammar.atn, Arrays.asList(grammar.getRuleNames()));
        data.vocabulary = grammar.getVocabulary();
        data.ruleNames = new ArrayList<>(grammar.rules.keySet());

        return data;
    }
}
