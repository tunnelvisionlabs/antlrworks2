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
        this.importedResults = new ArrayList<CompiledFileModel>();
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
