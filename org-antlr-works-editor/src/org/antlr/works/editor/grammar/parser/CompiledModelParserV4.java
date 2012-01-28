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
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.v4.Tool;
import org.antlr.v4.tool.ANTLRMessage;
import org.antlr.v4.tool.ANTLRToolListener;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.GrammarSemanticsMessage;
import org.antlr.v4.tool.GrammarSyntaxMessage;
import org.antlr.v4.tool.ast.GrammarRootAST;
import org.antlr.works.editor.antlr3.parsing.AntlrSyntaxErrorV3;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.spi.editor.hints.Severity;
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

    @Override
    protected CompiledModelV4 parseImpl(ParserTaskManager taskManager, JTextComponent component, final DocumentSnapshot snapshot)
        throws InterruptedException, ExecutionException {

        Parameters.notNull("snapshot", snapshot);

        synchronized (lock) {
            if (snapshot.equals(lastSnapshot)) {
                return new CompiledModelV4(snapshot, lastResult);
            }
        }

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "Reparsing snapshot {0}", new Object[] { snapshot });
        }

        try {
            final List<SyntaxError> syntaxErrors = new ArrayList<SyntaxError>();
            final Tool tool = new Tool();
            tool.addListener(new ErrorListener(snapshot, tool, syntaxErrors));
            tool.libDirectory = new File(snapshot.getVersionedDocument().getFileObject().getPath()).getParent();
            GrammarRootAST root = tool.loadFromString(snapshot.getText().toString());
            Grammar grammar = null;
            if (root != null) {
                grammar = tool.createGrammar(root);
                grammar.fileName = snapshot.getVersionedDocument().getFileObject().getNameExt();
                grammar.loadImportedGrammars();
            }

            CommonTokenStream tokenStream = (CommonTokenStream)root.tokens;
            @SuppressWarnings("unchecked")
            List<CommonToken> tokenList = tokenStream.getTokens();
            CommonToken[] tokens = tokenList.toArray(new CommonToken[0]);

            CompiledFileModelV4 currentResult = new CompiledFileModelV4(grammar, root, syntaxErrors, snapshot.getVersionedDocument().getFileObject(), tokens);

            synchronized (lock) {
                lastSnapshot = snapshot;
                lastResult = currentResult;
            }

            return new CompiledModelV4(snapshot, currentResult);
        } catch (Exception ex) {
            throw new ExecutionException("An unexpected error occurred.", ex);
        }
    }

    private static class ErrorListener implements ANTLRToolListener {
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
            syntaxErrors.add(new AntlrSyntaxErrorV3(snapshot, offendingToken, e, outputMessage, Severity.ERROR));
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
            syntaxErrors.add(new AntlrSyntaxErrorV3(snapshot, offendingToken, e, outputMessage, Severity.WARNING));
        }
    }
}
