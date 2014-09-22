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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.v4.Tool;
import org.antlr.v4.parse.ANTLRParser;
import org.antlr.v4.parse.TokenVocabParser;
import org.antlr.v4.tool.ANTLRMessage;
import org.antlr.v4.tool.ANTLRToolListener;
import org.antlr.v4.tool.ErrorManager;
import org.antlr.v4.tool.ErrorSeverity;
import org.antlr.v4.tool.ErrorType;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.GrammarSemanticsMessage;
import org.antlr.v4.tool.GrammarSyntaxMessage;
import org.antlr.v4.tool.GrammarTransformPipeline;
import org.antlr.v4.tool.LexerGrammar;
import org.antlr.v4.tool.ast.GrammarRootAST;
import org.antlr.works.editor.antlr3.parsing.AntlrSyntaxErrorV3;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.codemodel.FileModel;
import org.antlr.works.editor.grammar.codemodel.TokenData;
import org.antlr.works.editor.grammar.codemodel.TokenVocabModel;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.spi.editor.hints.Severity;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.Parameters;
import org.stringtemplate.v4.ST;

/**
 *
 * @author Sam Harwell
 */
public class CompiledModelParserV4 extends CompiledModelParser {
    // -J-Dorg.antlr.works.editor.grammar.parser.CompiledModelParserV4.level=FINE
    private static final Logger LOGGER = Logger.getLogger(CompiledModelParserV4.class.getName());

    private final Object lock = new Object();
    private DocumentSnapshot lastSnapshot;
    private CompiledFileModelV4 lastResult;
    private Throwable lastException;

    @Override
    protected CompiledModelV4 parseImpl(ParserTaskManager taskManager, ParseContext context, final DocumentSnapshot snapshot)
        throws InterruptedException, ExecutionException {

        Parameters.notNull("snapshot", snapshot);

        synchronized (lock) {
            if (snapshot.equals(lastSnapshot)) {
                if (lastException != null) {
                    throw new ExecutionException("An unexpected error occurred.", lastException);
                }

                return new CompiledModelV4(snapshot, lastResult);
            }

            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "Reparsing snapshot {0}", new Object[] { snapshot });
            }

            try {
                final List<SyntaxError> syntaxErrors = new ArrayList<>();
                final Tool tool = new CustomTool(snapshot);
                tool.errMgr = new CustomErrorManager(tool);
                tool.addListener(new ErrorListener(snapshot, tool, syntaxErrors));
                tool.libDirectory = new File(snapshot.getVersionedDocument().getFileObject().getPath()).getParent();
                GrammarRootAST root = tool.parseGrammarFromString(snapshot.getText().toString());

                Grammar grammar = null;
                CommonToken[] tokens = null;
                if (root != null) {
                    ANTLRStringStream inputStream = (ANTLRStringStream)root.token.getInputStream();
                    inputStream.name = snapshot.getVersionedDocument().getFileObject().getPath();

                    grammar = tool.createGrammar(root);
                    grammar.fileName = snapshot.getVersionedDocument().getFileObject().getNameExt();
                    tool.process(grammar, false);

                    CommonTokenStream tokenStream = (CommonTokenStream)root.tokenStream;
                    List<? extends Token> tokenList = tokenStream.getTokens();
                    tokens = tokenList.toArray(new CommonToken[0]);
                }

                CompiledFileModelV4 currentResult = new CompiledFileModelV4(grammar, root, syntaxErrors, snapshot.getVersionedDocument().getFileObject(), tokens);
                lastSnapshot = snapshot;
                lastResult = currentResult;
                lastException = null;
                return new CompiledModelV4(snapshot, currentResult);
            } catch (Exception ex) {
                lastSnapshot = snapshot;
                lastResult = null;
                lastException = ex;
                throw new ExecutionException("An unexpected error occurred.", ex);
            }
        }
    }

    private static ParserTaskManager getTaskManager() {
        return Lookup.getDefault().lookup(ParserTaskManager.class);
    }

    public static class CustomTool extends Tool {
        private final DocumentSnapshot snapshot;

        public CustomTool(DocumentSnapshot snapshot) {
            this.snapshot = snapshot;
        }

        @NonNull
        @Override
        public Grammar createGrammar(GrammarRootAST ast) {
            final Grammar g;
            if ( ast.grammarType==ANTLRParser.LEXER ) g = new CustomLexerGrammar(this, ast);
            else g = new CustomGrammar(this, ast);

            // ensure each node has pointer to surrounding grammar
            GrammarTransformPipeline.setGrammarPtr(g, ast);
            return g;
        }

    }

    public static class CustomErrorManager extends ErrorManager {

        public CustomErrorManager(Tool tool) {
            super(tool);
        }

        @Override
        public void grammarError(ErrorType etype, String fileName, Token token, Object... args) {
            if (etype == ErrorType.IMPLICIT_STRING_DEFINITION) {
                // ignore this until CustomTokenVocabParser can extract string literals
                return;
            }

            super.grammarError(etype, fileName, token, args);
        }

        @Override
        public ST getLocationFormat() {
            return new ST("");
        }

        @Override
        public ST getMessageFormat() {
            return new ST("(<id>) <text>");
        }

        @Override
        public ST getReportFormat(ErrorSeverity severity) {
            ST st = new ST("<type>(<message.id>): <message.text>");
            st.add("type", severity.getText());
            return st;
        }

        @Override
        public boolean formatWantsSingleLineMessage() {
            return false;
        }
    }

    private static class CustomLexerGrammar extends LexerGrammar {

        public CustomLexerGrammar(CustomTool tool, GrammarRootAST ast) {
            super(tool, ast);
        }

        @Override
        public void importTokensFromTokensFile() {
            String vocab = getOptionString("tokenVocab");
            if ( vocab!=null ) {
                TokenVocabParser vparser = new CustomTokenVocabParser(this);
                Map<String,Integer> tokens = vparser.load();
                tool.log("grammar", "tokens=" + tokens);
                for (String t : tokens.keySet()) {
                    if ( t.charAt(0)=='\'' ) defineStringLiteral(t, tokens.get(t));
                    else defineTokenName(t, tokens.get(t));
                }
            }
        }

    }

    private static class CustomGrammar extends Grammar {

        public CustomGrammar(CustomTool tool, GrammarRootAST ast) {
            super(tool, ast);
        }

        @Override
        public void importTokensFromTokensFile() {
            String vocab = getOptionString("tokenVocab");
            if ( vocab!=null ) {
                TokenVocabParser vparser = new CustomTokenVocabParser(this);
                Map<String,Integer> tokens = vparser.load();
                tool.log("grammar", "tokens=" + tokens);
                for (String t : tokens.keySet()) {
                    if ( t.charAt(0)=='\'' ) defineStringLiteral(t, tokens.get(t));
                    else defineTokenName(t, tokens.get(t));
                }
            }
        }

    }

    private static class CustomTokenVocabParser extends TokenVocabParser {

        public CustomTokenVocabParser(Grammar g) {
            super(g);
        }

        @Override
        public Map<String, Integer> load() {
            File vocabFile = getImportedVocabFile();
            if (vocabFile.isFile()) {
                return super.load();
            }

            String vocabName = g.getOptionString("tokenVocab");
            if (vocabName != null && !vocabName.isEmpty()) {
                FileObject fileObject = ((CustomTool)g.tool).snapshot.getVersionedDocument().getFileObject();
                if (fileObject == null) {
                    LOGGER.log(Level.WARNING, "Could not find source for token vocabulary.");
                    return Collections.emptyMap();
                }

                // try to find a lexer in the same folder with this name
                FileObject containingFolder = fileObject.getParent();
                if (containingFolder == null) {
                    LOGGER.log(Level.WARNING, "Could not find source for token vocabulary.");
                    return Collections.emptyMap();
                }

                FileObject sourceFileObject = containingFolder.getFileObject(vocabName, "g4");
                if (sourceFileObject == null) {
                    sourceFileObject = containingFolder.getFileObject(vocabName, "g3");
                }

                if (sourceFileObject == null) {
                    sourceFileObject = containingFolder.getFileObject(vocabName, "g");
                }

                if (sourceFileObject == null) {
                    LOGGER.log(Level.WARNING, "Could not find source for token vocabulary.");
                    return Collections.emptyMap();
                }

                VersionedDocument sourceDocument = VersionedDocumentUtilities.getVersionedDocument(sourceFileObject);
                Future<ParserData<FileModel>> futureData = getTaskManager().getData(sourceDocument.getCurrentSnapshot(), GrammarParserDataDefinitions.FILE_MODEL);
                if (futureData == null) {
                    LOGGER.log(Level.WARNING, "Failed to load source for token vocabulary.");
                    return Collections.emptyMap();
                }

                ParserData<FileModel> data;
                try {
                    data = futureData.get();
                } catch (InterruptedException | ExecutionException ex) {
                    LOGGER.log(Level.WARNING, "Failed to load source for token vocabulary.");
                    return Collections.emptyMap();
                }

                FileModel fileModel = data != null ? data.getData() : null;
                if (fileModel == null) {
                    LOGGER.log(Level.WARNING, "Failed to load source for token vocabulary.");
                    return Collections.emptyMap();
                }

                TokenVocabModel vocabulary = fileModel.getVocabulary();
                Map<String, Integer> result = new HashMap<>();
                for (TokenData tokenData : vocabulary.getTokens()) {
                    result.put(tokenData.getName(), result.size());
                }
                
                return result;
            }

            return Collections.emptyMap();
        }
    }

    public static class ErrorListener implements ANTLRToolListener {
        private final DocumentSnapshot snapshot;
        private final Tool tool;
        private final List<SyntaxError> syntaxErrors;

        public ErrorListener(@NonNull DocumentSnapshot snapshot, @NonNull Tool tool, @NonNull List<SyntaxError> syntaxErrors) {
            Parameters.notNull("snapshot", snapshot);
            Parameters.notNull("tool", tool);
            Parameters.notNull("syntaxErrors", syntaxErrors);

            this.snapshot = snapshot;
            this.tool = tool;
            this.syntaxErrors = syntaxErrors;
        }

        @Override
        public void info(String string) {
        }

        @Override
        public void error(ANTLRMessage antlrm) {
            Token offendingToken = null;
            RecognitionException e = null;
            if (antlrm.getCause() instanceof RecognitionException) {
                e = (RecognitionException)antlrm.getCause();
                offendingToken = e.token;
            }

            if (antlrm instanceof GrammarSyntaxMessage) {
                e = ((GrammarSyntaxMessage)antlrm).getCause();
                offendingToken = ((GrammarSyntaxMessage)antlrm).offendingToken;
            } else if (antlrm instanceof GrammarSemanticsMessage) {
                offendingToken = ((GrammarSemanticsMessage)antlrm).offendingToken;
            }

            ST messageTemplate = tool.errMgr.getMessageTemplate(antlrm);
            String outputMessage = messageTemplate.render();
            syntaxErrors.add(new AntlrSyntaxErrorV3(getSnapshot(offendingToken), offendingToken, e, outputMessage, Severity.ERROR));
        }

        @Override
        public void warning(ANTLRMessage antlrm) {
            Token offendingToken = null;
            RecognitionException e = null;
            if (antlrm.getCause() instanceof RecognitionException) {
                e = (RecognitionException)antlrm.getCause();
                offendingToken = e.token;
            }

            if (antlrm instanceof GrammarSyntaxMessage) {
                offendingToken = ((GrammarSyntaxMessage)antlrm).offendingToken;
            } else if (antlrm instanceof GrammarSemanticsMessage) {
                offendingToken = ((GrammarSemanticsMessage)antlrm).offendingToken;
            }

            ST messageTemplate = tool.errMgr.getMessageTemplate(antlrm);
            String outputMessage = messageTemplate.render();
            syntaxErrors.add(new AntlrSyntaxErrorV3(getSnapshot(offendingToken), offendingToken, e, outputMessage, Severity.WARNING));
        }

        private DocumentSnapshot getSnapshot(Token offendingToken) {
            if (offendingToken == null) {
                return snapshot;
            }

            FileObject fileObject = getImportedGrammarFileObject(offendingToken.getInputStream().getSourceName());
            if (fileObject == null) {
                return snapshot;
            }

            VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(fileObject);
            return versionedDocument.getCurrentSnapshot();
        }

        private FileObject getImportedGrammarFileObject(String sourceName) {
            VersionedDocument versionedDocument = snapshot.getVersionedDocument();
            FileObject rootFileObject = versionedDocument.getFileObject();
            if (rootFileObject == null) {
                return null;
            }

            File sourceFile = new File(sourceName);
            File rootFile = new File(rootFileObject.getPath());
            if (sourceFile.equals(rootFile)) {
                return rootFileObject;
            }

            String fileName = sourceFile.getName();
            FileObject importedFile = rootFileObject.getParent().getFileObject(fileName);
            if (!importedFile.isData()) {
                return null;
            }

            return importedFile;
        }
    }
}
