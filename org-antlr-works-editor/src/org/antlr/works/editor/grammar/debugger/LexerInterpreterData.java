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
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.v4.Tool;
import org.antlr.v4.runtime.atn.ATNSerializer;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.LexerGrammar;
import org.antlr.v4.tool.ast.GrammarRootAST;

/**
 *
 * @author Sam Harwell
 */
public class LexerInterpreterData extends AbstractInterpreterData {

    public List<String> modeNames;

    public static LexerInterpreterData buildFromSnapshot(DocumentSnapshot snapshot) {
        Tool tool = new LexerTracingTool();

        ANTLRStringStream stream = new ANTLRStringStream(snapshot.getText());
        stream.name = snapshot.getVersionedDocument().getFileObject().getNameExt();
        GrammarRootAST ast = tool.parse(stream.name, stream);
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
        data.tokenNames = new ArrayList<>(Arrays.asList(getTokenNames(lexerGrammar)));
        data.ruleNames = new ArrayList<>(lexerGrammar.rules.keySet());
        data.modeNames = new ArrayList<>(lexerGrammar.modes.keySet());
        return data;
    }

}
