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
import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.antlr.v4.Tool;
import org.antlr.v4.tool.ANTLRMessage;
import org.antlr.v4.tool.ANTLRToolListener;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.ast.GrammarRootAST;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
import org.netbeans.spi.editor.hints.Severity;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author sam
 */
public class GrammarParserV4 extends GrammarParser {

    protected Snapshot lastSnapshot;
    protected GrammarFileResultV4 lastResult;

    @Override
    public void parse(Snapshot snapshot, Task task, SourceModificationEvent sme) throws ParseException {
        try {
            final List<SyntaxError> syntaxErrors = new ArrayList<SyntaxError>();
            Tool tool = new Tool();
            tool.addListener(new ANTLRToolListener() {

                @Override
                public void info(String string) {
                }

                @Override
                public void error(ANTLRMessage antlrm) {
                    RecognitionException e = null;
                    if (antlrm.e instanceof RecognitionException) {
                        e = (RecognitionException)antlrm.e;
                    }

                    syntaxErrors.add(new SyntaxError(e, antlrm.toString(), Severity.ERROR));
                }

                @Override
                public void warning(ANTLRMessage antlrm) {
                    RecognitionException e = null;
                    if (antlrm.e instanceof RecognitionException) {
                        e = (RecognitionException)antlrm.e;
                    }

                    syntaxErrors.add(new SyntaxError(e, antlrm.toString(), Severity.WARNING));
                }
            });

            tool.libDirectory = new File(snapshot.getSource().getFileObject().getPath()).getParent();
            GrammarRootAST root = tool.loadFromString(snapshot.getText().toString());
            Grammar grammar = tool.createGrammar(root);
            grammar.fileName = snapshot.getSource().getFileObject().getNameExt();
            grammar.loadImportedGrammars();

            CommonTokenStream tokenStream = (CommonTokenStream)root.tokens;
            @SuppressWarnings("unchecked")
            List<CommonToken> tokenList = tokenStream.getTokens();
            CommonToken[] tokens = tokenList.toArray(new CommonToken[0]);

            GrammarFileResultV4 currentResult = new GrammarFileResultV4(grammar, root, syntaxErrors, snapshot.getSource().getFileObject(), tokens);

            synchronized (this) {
                if (SINGLE_RESULT) {
                    lastSnapshot = snapshot;
                    lastResult = currentResult;
                } else {
                    results.put(task, new GrammarParserResultV4(snapshot, task, currentResult));
                }
            }
        } catch (Exception ex) {
            throw new ParseException("An unexpected error occurred.", ex);
        }
    }

    @Override
    public GrammarParserResultV4 createResult(Task task) {
        return new GrammarParserResultV4(lastSnapshot, task, lastResult);
    }

    public static class GrammarFileResultV4 extends GrammarFileResult {

        private final Grammar grammar;
        private final GrammarRootAST result;
        private final List<? extends SyntaxError> syntaxErrors;
        private final List<GrammarFileResultV4> importedResults;

        public GrammarFileResultV4(Grammar grammar, GrammarRootAST result, List<? extends SyntaxError> syntaxErrors, FileObject fileObject, CommonToken[] tokens) {
            super(fileObject, tokens);
            this.grammar = grammar;
            this.result = result;
            this.syntaxErrors = syntaxErrors;
            this.importedResults = new ArrayList<GrammarFileResultV4>();
            if (grammar.importedGrammars != null) {
                for (Grammar imported : grammar.importedGrammars) {
                    CommonTokenStream importedTokenStream = (CommonTokenStream)imported.ast.tokens;
                    String fileName = ((Lexer)importedTokenStream.getTokenSource()).getCharStream().getSourceName();
                    FileObject importedFileObject = FileUtil.toFileObject(new File(fileName));
                    @SuppressWarnings("unchecked")
                    List<CommonToken> importedTokensList = importedTokenStream.getTokens();
                    CommonToken[] importedTokens = importedTokensList.toArray(new CommonToken[0]);
                    importedResults.add(new GrammarFileResultV4(imported, imported.ast, null, importedFileObject, importedTokens));
                }
            }
        }

        public Grammar getGrammar() {
            return grammar;
        }

        public GrammarRootAST getResult() {
            return result;
        }

        public List<GrammarFileResultV4> getImportedGrammarResults() {
            return importedResults;
        }

        @Override
        public List<? extends SyntaxError> getSyntaxErrors() {
            return syntaxErrors;
        }

    }

    public class GrammarParserResultV4 extends GrammarParserResult {

        public GrammarParserResultV4(Snapshot snapshot, Task task, GrammarFileResultV4 result) {
            super(snapshot, task, result);
        }

        @Override
        public GrammarFileResultV4 getResult() {
            return (GrammarFileResultV4)super.getResult();
        }

        public Grammar getRootGrammar() {
            return getResult().grammar;
        }

        public List<GrammarFileResultV4> getImportedGrammarResults() {
            return getResult().getImportedGrammarResults();
        }

    }

}
