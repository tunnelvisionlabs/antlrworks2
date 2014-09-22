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
import java.util.Collections;
import java.util.List;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.Token;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.ast.GrammarRootAST;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Parameters;

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

    public CompiledFileModelV4(@NullAllowed Grammar grammar, @NullAllowed GrammarRootAST result, @NonNull List<? extends SyntaxError> syntaxErrors, @NonNull FileObject fileObject, @NullAllowed CommonToken[] tokens) {
        super(fileObject, tokens);
        Parameters.notNull("syntaxErrors", syntaxErrors);

        this.grammar = grammar;
        this.result = result;
        this.syntaxErrors = syntaxErrors;
        this.importedResults = new ArrayList<>();
        if (grammar != null && grammar.importedGrammars != null) {
            for (Grammar imported : grammar.importedGrammars) {
                CommonTokenStream importedTokenStream = (CommonTokenStream)imported.ast.tokenStream;
                String fileName = ((Lexer)importedTokenStream.getTokenSource()).getCharStream().getSourceName();
                FileObject importedFileObject = FileUtil.toFileObject(new File(fileName));
                List<? extends Token> importedTokensList = importedTokenStream.getTokens();
                CommonToken[] importedTokens = importedTokensList.toArray(new CommonToken[0]);
                importedResults.add(new CompiledFileModelV4(imported, imported.ast, Collections.<SyntaxError>emptyList(), importedFileObject, importedTokens));
            }
        }
    }

    @CheckForNull
    public Grammar getGrammar() {
        return grammar;
    }

    @CheckForNull
    public GrammarRootAST getResult() {
        return result;
    }

    @NonNull
    public List<CompiledFileModelV4> getImportedGrammarResults() {
        return importedResults;
    }

    @Override
    public List<? extends SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

}
