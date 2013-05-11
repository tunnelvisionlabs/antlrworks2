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
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.antlr.Tool;
import org.antlr.grammar.v3.ANTLRParser;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.RecognitionException;
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

                List<? extends Token> tokenList = tokenStream.getTokens();
                CommonToken[] tokens = new CommonToken[tokenList.size()];
                tokens = tokenList.toArray(tokens);

                CompiledFileModelV3 currentResult = new CompiledFileModelV3(parser, g, result, snapshot.getVersionedDocument().getFileObject(), tokens);
                lastSnapshot = snapshot;
                lastResult = currentResult;
                lastException = null;
                return new CompiledModelV3(snapshot, currentResult);
            } catch (RecognitionException | RuntimeException | Error ex) {
                lastSnapshot = snapshot;
                lastResult = null;
                lastException = ex;
                throw new ExecutionException("An unexpected error occurred.", ex);
            } finally {
                ErrorManager.setErrorListener(null);
            }
        }

    }

}
