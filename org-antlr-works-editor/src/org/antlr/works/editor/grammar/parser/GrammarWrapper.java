/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.antlr.Tool;
import org.antlr.grammar.v3.ANTLRParser;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.tool.CompositeGrammar;
import org.antlr.tool.ErrorManager;
import org.antlr.tool.Grammar;
import org.antlr.tool.GrammarAST;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;

/**
 *
 * @author Sam Harwell
 */
public class GrammarWrapper extends Grammar {
    
    private final List<CompiledFileModelV3> imported = new ArrayList<>();
    private final DocumentSnapshot snapshot;

    public GrammarWrapper(DocumentSnapshot snapshot) {
        this.snapshot = snapshot;
    }

    public GrammarWrapper(Tool tool, DocumentSnapshot snapshot) {
        super(tool);
        this.snapshot = snapshot;
    }

    public GrammarWrapper(Tool tool, String fileName, CompositeGrammar composite, DocumentSnapshot snapshot) {
        super(tool, fileName, composite);
        this.snapshot = snapshot;
    }

    public List<CompiledFileModelV3> getImportedGrammarResults() {
        return imported;
    }

    @Override
    public void importGrammar(GrammarAST grammarNameAST, String label) {
        if (tool == null) {
            return;
        }

		String grammarName = grammarNameAST.getText();
		//System.out.println("import "+gfile.getName());
		String gname = grammarName + GRAMMAR_FILE_EXTENSION;
		try {
			String fullName = tool.getLibraryFile(gname);
            File file = new File(fullName);
            if (!file.exists()) {
                return;
            }

            FileObject fileObject = FileUtil.toFileObject(file);
            if (!fileObject.isValid() || fileObject.isFolder()) {
                return;
            }

            String text = fileObject.asText();

            // the IDE always renders newlines as \n, so we have to convert line endings
            // before parsing if we want the seek operation to be accurate
            text = text.replace("\r\n", "\n");

            ANTLRStringStream input = new ANTLRStringStream(text);
            ANTLRErrorProvidingLexer lexer = new ANTLRErrorProvidingLexer(input);
            ANTLRParserTokenStream tokenStream = new ANTLRParserTokenStream(lexer);
            ANTLRErrorProvidingParser parser = new ANTLRErrorProvidingParser(tokenStream, snapshot);

            lexer.setParser(parser);
            tokenStream.setParser(parser);
            parser.setTreeAdaptor(new ANTLRErrorProvidingParser.grammar_Adaptor(parser));

            List<? extends Token> tokenList = tokenStream.getTokens();
            CommonToken[] tokens = new CommonToken[tokenList.size()];
            tokens = tokenList.toArray(tokens);

            try {
                GrammarWrapper g = new GrammarWrapper(this.tool, fullName, this.composite, this.snapshot);
                g.setFileName(fullName); // work around a bug in Grammar.setName that results in a NPE
                ANTLRParser.grammar__return result = parser.grammar_(g);
                imported.add(new CompiledFileModelV3(parser, g, result, fileObject, tokens));
            } catch (RecognitionException | RuntimeException | Error ex) {
                Exceptions.printStackTrace(ex);
            }
		}
		catch (IOException ioe) {
			ErrorManager.error(ErrorManager.MSG_CANNOT_OPEN_FILE,
							   gname,
							   ioe);
		}
    }

}
