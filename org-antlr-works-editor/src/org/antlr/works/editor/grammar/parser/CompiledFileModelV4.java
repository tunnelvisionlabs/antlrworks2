/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
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
import java.util.ArrayList;
import java.util.List;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.ast.GrammarRootAST;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author Sam Harwell
 */
public class CompiledFileModelV4 extends CompiledFileModel {

    private final Grammar grammar;
    private final GrammarRootAST result;
    private final List<? extends SyntaxError> syntaxErrors;
    private final List<CompiledFileModelV4> importedResults;

    public CompiledFileModelV4(FileObject fileObject) {
        super(fileObject, null);
        this.grammar = null;
        this.result = null;
        this.syntaxErrors = null;
        this.importedResults = null;
    }

    public CompiledFileModelV4(Grammar grammar, GrammarRootAST result, List<? extends SyntaxError> syntaxErrors, FileObject fileObject, CommonToken[] tokens) {
        super(fileObject, tokens);
        this.grammar = grammar;
        this.result = result;
        this.syntaxErrors = syntaxErrors;
        this.importedResults = new ArrayList<CompiledFileModelV4>();
        if (grammar.importedGrammars != null) {
            for (Grammar imported : grammar.importedGrammars) {
                CommonTokenStream importedTokenStream = (CommonTokenStream)imported.ast.tokens;
                String fileName = ((Lexer)importedTokenStream.getTokenSource()).getCharStream().getSourceName();
                FileObject importedFileObject = FileUtil.toFileObject(new File(fileName));
                @SuppressWarnings("unchecked")
                List<CommonToken> importedTokensList = importedTokenStream.getTokens();
                CommonToken[] importedTokens = importedTokensList.toArray(new CommonToken[0]);
                importedResults.add(new CompiledFileModelV4(imported, imported.ast, null, importedFileObject, importedTokens));
            }
        }
    }

    public Grammar getGrammar() {
        return grammar;
    }

    public GrammarRootAST getResult() {
        return result;
    }

    public List<CompiledFileModelV4> getImportedGrammarResults() {
        return importedResults;
    }

    @Override
    public List<? extends SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

}
