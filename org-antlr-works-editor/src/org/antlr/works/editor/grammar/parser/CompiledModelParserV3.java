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
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.antlr.Tool;
import org.antlr.grammar.v3.ANTLRParser;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.tool.ErrorManager;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class CompiledModelParserV3 extends CompiledModelParser {

    private final Object lock = new Object();
    private DocumentSnapshot lastSnapshot;
    private CompiledFileModelV3 lastResult;
    private Throwable lastException;

    @Override
    protected CompiledModelV3 parseImpl(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot)
        throws InterruptedException, ExecutionException {
        Parameters.notNull("snapshot", snapshot);

        synchronized (lock) {
            if (snapshot.equals(lastSnapshot)) {
                if (lastException != null) {
                    throw new ExecutionException("An unexpected error occurred.", lastException);
                }

                return new CompiledModelV3(snapshot, lastResult);
            }

            ANTLRStringStream input = new ANTLRStringStream(snapshot.getText().toString());
            ANTLRErrorProvidingLexer lexer = new ANTLRErrorProvidingLexer(input);
            ANTLRParserTokenStream tokenStream = new ANTLRParserTokenStream(lexer);
            ANTLRErrorProvidingParser parser = new ANTLRErrorProvidingParser(tokenStream, snapshot);

            lexer.setParser(parser);
            tokenStream.setParser(parser);
            parser.setTreeAdaptor(new ANTLRErrorProvidingParser.grammar_Adaptor(parser));

            try {
                ErrorManager.setErrorListener(new ANTLRErrorProvidingParser.ErrorListener(snapshot));
                Tool tool = new Tool();
                tool.setLibDirectory(new File(snapshot.getVersionedDocument().getFileObject().getPath()).getParent());
                GrammarWrapper g = new GrammarWrapper(tool, snapshot);
                g.setFileName(""); // work around a bug in Grammar.setName that results in a NPE
                ANTLRParser.grammar__return result = parser.grammar_(g);

                @SuppressWarnings("unchecked")
                List<Token> tokenList = tokenStream.getTokens();
                CommonToken[] tokens = new CommonToken[tokenList.size()];
                tokens = tokenList.toArray(tokens);

                CompiledFileModelV3 currentResult = new CompiledFileModelV3(parser, g, result, snapshot.getVersionedDocument().getFileObject(), tokens);
                lastSnapshot = snapshot;
                lastResult = currentResult;
                lastException = null;
                return new CompiledModelV3(snapshot, currentResult);
            } catch (Exception ex) {
                lastSnapshot = snapshot;
                lastResult = null;
                lastException = ex;
                throw new ExecutionException("An unexpected error occurred.", ex);
            }
        }

    }

}
