/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.antlr.works.editor.grammar.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.antlr.Tool;
import org.antlr.grammar.v3.ANTLRParser;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonToken;
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
    
    private final List<CompiledFileModelV3> imported = new ArrayList<CompiledFileModelV3>();

    public GrammarWrapper() {
    }

    public GrammarWrapper(Tool tool) {
        super(tool);
    }

    public GrammarWrapper(Tool tool, String fileName, CompositeGrammar composite) {
        super(tool, fileName, composite);
    }

    public List<CompiledFileModelV3> getImportedGrammarResults() {
        return imported;
    }

    @Override
    @SuppressWarnings("unchecked")
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
            ANTLRErrorProvidingParser parser = new ANTLRErrorProvidingParser(tokenStream);

            lexer.setParser(parser);
            tokenStream.setParser(parser);
            parser.setTreeAdaptor(new ANTLRErrorProvidingParser.grammar_Adaptor(parser));

            List<Token> tokenList = tokenStream.getTokens();
            CommonToken[] tokens = new CommonToken[tokenList.size()];
            tokens = tokenList.toArray(tokens);

            try {
                GrammarWrapper g = new GrammarWrapper(this.tool, fullName, this.composite);
                g.setFileName(fullName); // work around a bug in Grammar.setName that results in a NPE
                ANTLRParser.grammar__return result = parser.grammar_(g);
                imported.add(new CompiledFileModelV3(parser, g, result, fileObject, tokens));
            } catch (Exception ex) {
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
