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
import java.util.EnumSet;
import java.util.List;
import org.antlr.Tool;
import org.antlr.grammar.v3.ANTLRParser;
import org.antlr.grammar.v3.ANTLRParser.grammar__return;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.tool.ErrorManager;
import org.antlr.works.editor.grammar.parser.GrammarTaskInput.Input;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class GrammarParserV3 extends GrammarParser {
    private static final EnumSet<Input> DEFAULT_INPUTS = EnumSet.<Input>of(Input.ToolAST, Input.ToolImportedAST, Input.SyntaxErrors);

    private final Object lock = new Object();
    private Snapshot lastSnapshot;
    private EnumSet<Input> lastInputs;
    private GrammarFileResultV3 lastResult;

    @Override
    public GrammarParserResultV3 parseImpl(Snapshot snapshot, Task task, SourceModificationEvent sme) throws ParseException {
        Parameters.notNull("snapshot", snapshot);
        Parameters.notNull("task", task);

        EnumSet<Input> inputs;
        if (task instanceof GrammarParserResultTask) {
            inputs = ((GrammarParserResultTask)task).getTaskInputs();
        } else {
            inputs = DEFAULT_INPUTS;
        }

        synchronized (lock) {
            if (lastInputs != null && lastInputs.containsAll(inputs)) {
                if (snapshot.equals(lastSnapshot)) {
                    return new GrammarParserResultV3(snapshot, task, lastResult);
                }
            }
        }

        ANTLRStringStream input = new ANTLRStringStream(snapshot.getText().toString());
        ANTLRErrorProvidingLexer lexer = new ANTLRErrorProvidingLexer(input);
        ANTLRParserTokenStream tokenStream = new ANTLRParserTokenStream(lexer);
        ANTLRErrorProvidingParser parser = new ANTLRErrorProvidingParser(tokenStream);

        lexer.setParser(parser);
        tokenStream.setParser(parser);
        parser.setTreeAdaptor(new ANTLRErrorProvidingParser.grammar_Adaptor(parser));

        try {
            ErrorManager.setErrorListener(new ANTLRErrorProvidingParser.ErrorListener());
            Tool tool = new Tool();
            tool.setLibDirectory(new File(snapshot.getSource().getFileObject().getPath()).getParent());
            GrammarWrapper g = new GrammarWrapper(tool);
            g.setFileName(""); // work around a bug in Grammar.setName that results in a NPE
            ANTLRParser.grammar__return result = parser.grammar_(g);

            @SuppressWarnings("unchecked")
            List<Token> tokenList = tokenStream.getTokens();
            CommonToken[] tokens = new CommonToken[tokenList.size()];
            tokens = tokenList.toArray(tokens);

            GrammarFileResultV3 currentResult = new GrammarFileResultV3(parser, g, result, snapshot.getSource().getFileObject(), tokens);

            synchronized (lock) {
                lastSnapshot = snapshot;
                lastInputs = inputs;
                lastResult = currentResult;
            }

            return new GrammarParserResultV3(snapshot, task, currentResult);
        } catch (Exception ex) {
            throw new ParseException("An unexpected error occurred.", ex);
        }
    }

    public static class GrammarFileResultV3 extends GrammarFileResult {

        private final ANTLRErrorProvidingParser parser;
        private final GrammarWrapper grammar;
        private final ANTLRParser.grammar__return result;

        public GrammarFileResultV3(ANTLRErrorProvidingParser parser, GrammarWrapper grammar, grammar__return result, FileObject fileObject, CommonToken[] tokens) {
            super(fileObject, tokens);
            this.parser = parser;
            this.grammar = grammar;
            this.result = result;
        }

        public GrammarWrapper getGrammar() {
            return grammar;
        }

        public ANTLRParser.grammar__return getResult() {
            return result;
        }

        public ANTLRErrorProvidingParser getParser() {
            return parser;
        }

        @Override
        public List<? extends SyntaxError> getSyntaxErrors() {
            return getParser().getSyntaxErrors();
        }

    }

    public class GrammarParserResultV3 extends GrammarParserResult {

        public GrammarParserResultV3(Snapshot snapshot, Task task, GrammarFileResultV3 result) {
            super(snapshot, task, result);
        }

        @Override
        public GrammarFileResultV3 getResult() {
            return (GrammarFileResultV3)super.getResult();
        }

        public GrammarWrapper getRootGrammar() {
            return getResult().getGrammar();
        }

        public List<GrammarFileResultV3> getImportedGrammarResults() {
            return getRootGrammar().getImportedGrammarResults();
        }

    }

}
