/*
 *  Copyright (c) 2014 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */

package org.antlr.works.editor.grammar.debugger;

import java.util.ArrayList;
import java.util.Arrays;
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
public class ParserInterpreterData extends AbstractInterpreterData {

    public LexerInterpreterData lexerInterpreterData;

    public static ParserInterpreterData buildFromSnapshot(DocumentSnapshot snapshot) {
        LexerInterpreterData lexerInterpreterData = LexerInterpreterData.buildFromSnapshot(snapshot);
        if (lexerInterpreterData == null) {
            return null;
        }

        Tool tool = new Tool();

        ANTLRStringStream stream = new ANTLRStringStream(snapshot.getText());
        stream.name = snapshot.getVersionedDocument().getFileObject().getNameExt();
        GrammarRootAST ast = tool.parse(stream.name, stream);
        Grammar grammar = tool.createGrammar(ast);
        if (grammar instanceof LexerGrammar) {
            return null;
        }

        tool.process(grammar, false);

        LexerGrammar lexerGrammar = grammar.implicitLexer;
        if (lexerGrammar == null) {
            return null;
        }

        ParserInterpreterData data = new ParserInterpreterData();

        // start by filling in the lexer data
        data.lexerInterpreterData = new LexerInterpreterData();
        data.lexerInterpreterData.grammarFileName = lexerGrammar.fileName;
        data.lexerInterpreterData.serializedAtn = ATNSerializer.getSerializedAsString(lexerGrammar.atn, Arrays.asList(lexerGrammar.getRuleNames()));
        data.lexerInterpreterData.tokenNames = new ArrayList<>(Arrays.asList(getTokenNames(lexerGrammar)));
        data.lexerInterpreterData.ruleNames = new ArrayList<>(lexerGrammar.rules.keySet());
        data.lexerInterpreterData.modeNames = new ArrayList<>(lexerGrammar.modes.keySet());

        // then fill in the parser data
        data.grammarFileName = grammar.fileName;
        data.serializedAtn = ATNSerializer.getSerializedAsString(grammar.atn, Arrays.asList(grammar.getRuleNames()));
        data.tokenNames = new ArrayList<>(Arrays.asList(getTokenNames(grammar)));
        data.ruleNames = new ArrayList<>(grammar.rules.keySet());

        return data;
    }
}
