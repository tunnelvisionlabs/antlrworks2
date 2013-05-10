/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.parser;

import java.util.ArrayList;
import java.util.List;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.runtime.CommonToken;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class CompiledFileModel {

    @NonNull
    private final FileObject fileObject;
    private final CommonToken[] tokens;

    private final GroupParserWrapper parser;
    private final TemplateGroupRuleReturnScope result;
    private final List<? extends SyntaxError> syntaxErrors;
    private final List<CompiledFileModel> importedResults;

    public CompiledFileModel(@NonNull FileObject fileObject) {
        Parameters.notNull("fileObject", fileObject);

        this.fileObject = fileObject;
        this.tokens = null;
        this.parser = null;
        this.result = null;
        this.syntaxErrors = null;
        this.importedResults = null;
    }

    public CompiledFileModel(GroupParserWrapper parser, TemplateGroupRuleReturnScope result, List<? extends SyntaxError> syntaxErrors, @NonNull FileObject fileObject, @NullAllowed CommonToken[] tokens) {
        Parameters.notNull("fileObject", fileObject);

        this.fileObject = fileObject;
        this.tokens = tokens;
        this.parser = parser;
        this.result = result;
        this.syntaxErrors = syntaxErrors;
        this.importedResults = new ArrayList<>();
//        if (grammar.importedGrammars != null) {
//            for (Grammar imported : grammar.importedGrammars) {
//                CommonTokenStream importedTokenStream = (CommonTokenStream)imported.ast.tokens;
//                String fileName = ((Lexer)importedTokenStream.getTokenSource()).getCharStream().getSourceName();
//                FileObject importedFileObject = FileUtil.toFileObject(new File(fileName));
//                @SuppressWarnings("unchecked")
//                List<CommonToken> importedTokensList = importedTokenStream.getTokens();
//                CommonToken[] importedTokens = importedTokensList.toArray(new CommonToken[0]);
//                importedResults.add(new CompiledFileModelV4(imported, imported.ast, null, importedFileObject, importedTokens));
//            }
//        }
    }

    public @NonNull FileObject getFileObject() {
        return fileObject;
    }

    public @CheckForNull CommonToken[] getTokens() {
        return tokens;
    }

    public GroupParserWrapper getParser() {
        return parser;
    }

    public TemplateGroupRuleReturnScope getResult() {
        return result;
    }

    public List<CompiledFileModel> getImportedGroupResults() {
        return importedResults;
    }

    public List<? extends SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

}
