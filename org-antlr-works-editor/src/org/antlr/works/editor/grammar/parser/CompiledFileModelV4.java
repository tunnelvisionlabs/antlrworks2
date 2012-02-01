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
