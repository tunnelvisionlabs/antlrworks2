/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.parser;

import java.util.ArrayList;
import java.util.List;
import org.antlr.grammar.v3.ANTLRParser;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MissingTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.UnwantedTokenException;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.Tree;
import org.antlr.runtime.tree.TreeNodeStream;
import org.antlr.tool.ANTLRErrorListener;
import org.antlr.tool.GrammarAST;
import org.antlr.tool.GrammarSyntaxMessage;
import org.antlr.tool.Message;
import org.antlr.tool.ToolMessage;
import org.antlr.works.editor.antlr3.parsing.AntlrSyntaxErrorV3;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.spi.editor.hints.Severity;

/**
 *
 * @author Sam Harwell
 */
public class ANTLRErrorProvidingParser extends ANTLRParser {
    
    private final List<SyntaxError> syntaxErrors = new ArrayList<>();
    private final DocumentSnapshot snapshot;

    public ANTLRErrorProvidingParser(TokenStream input, DocumentSnapshot snapshot) {
        super(input);
        this.snapshot = snapshot;
    }

    @NonNull
    public List<SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

    public String getCurrentRuleName() {
        return currentRuleName;
    }

    @Override
    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
        //String header = getErrorHeader(e);
        String message = getErrorMessage(e, tokenNames);
        syntaxErrors.add(new AntlrSyntaxErrorV3(snapshot, e != null ? e.token : null, e, message, Severity.ERROR));

        super.displayRecognitionError(tokenNames, e);
    }

    public static final class ErrorListener implements ANTLRErrorListener {
        private final DocumentSnapshot snapshot;

        public ErrorListener(DocumentSnapshot snapshot) {
            this.snapshot = snapshot;
        }

        @Override
        public void info(String string) {
        }

        @Override
        public void error(Message msg) {
            if (msg instanceof GrammarSyntaxMessage) {
                GrammarSyntaxMessage syntaxMessage = (GrammarSyntaxMessage)msg;
                Token token = syntaxMessage.offendingToken;
                if (token == null)
                    return;

                if (!(syntaxMessage.exception.input instanceof ANTLRParserTokenStream))
                    return;

                ANTLRParserTokenStream stream = (ANTLRParserTokenStream)syntaxMessage.exception.input;
                ANTLRErrorProvidingParser parser = stream.getParser();
                if (parser == null)
                    return;

                parser.syntaxErrors.add(new AntlrSyntaxErrorV3(snapshot, syntaxMessage.offendingToken, syntaxMessage.exception, msg.toString(), Severity.ERROR));
            }
        }

        @Override
        public void warning(Message msg) {
        }

        @Override
        public void error(ToolMessage tm) {
        }
    }

    private static class GrammarASTErrorNode extends GrammarAST {
        public IntStream input;
        public Token start;
        public Token stop;
        public RecognitionException trappedException;

        public GrammarASTErrorNode(TokenStream input, Token start, Token stop, RecognitionException e) {
            super(stop);
            //Console.Out.WriteLine( "start: " + start + ", stop: " + stop );
            if ( stop == null ||
                 ( stop.getTokenIndex() < start.getTokenIndex() &&
                  stop.getType() != Token.EOF) ) {
                // sometimes resync does not consume a token (when LT(1) is
                // in follow set.  So, stop will be 1 to left to start. adjust.
                // Also handle case where start is the first token and no token
                // is consumed during recovery; LT(-1) will return null.
                stop = start;
            }
            this.input = input;
            this.start = start;
            this.stop = stop;
            this.trappedException = e;
        }

        @Override
        public boolean isNil() { return false; }

        @Override
        public String getText()
        {
            String badText;
            if (start instanceof Token) {
                int i = start.getTokenIndex();
                int j = stop.getTokenIndex();
                if (stop.getType() == Token.EOF) {
                    j = ((TokenStream)input).size();
                }
                badText = ((TokenStream)input).toString(i, j);
            } else if (start instanceof Tree) {
                badText = ((TreeNodeStream)input).toString(start, stop);
            } else {
                // people should subclass if they alter the tree type so this
                // next one is for sure correct.
                badText = "<unknown>";
            }
            return badText;
        }

        @Override
        public void setText(String value) { }

        @Override
        public int getType() { return Token.INVALID_TOKEN_TYPE; }

        @Override
        public void setType(int value) { }

        @Override
        public String toString()
        {
            if (trappedException instanceof MissingTokenException)
            {
                return "<missing type: " +
                       ( (MissingTokenException)trappedException ).getMissingType() +
                       ">";
            } else if (trappedException instanceof UnwantedTokenException) {
                return "<extraneous: " +
                       ( (UnwantedTokenException)trappedException ).getUnexpectedToken() +
                       ", resync=" + getText() + ">";
            } else if (trappedException instanceof MismatchedTokenException) {
                return "<mismatched token: " + trappedException.token + ", resync=" + getText() + ">";
            } else if (trappedException instanceof NoViableAltException) {
                return "<unexpected: " + trappedException.token +
                       ", resync=" + getText() + ">";
            }
            return "<error: " + getText() + ">";
        }
    }

    public static class grammar_Adaptor extends CommonTreeAdaptor {
        private final ANTLRErrorProvidingParser parser;

        public grammar_Adaptor(ANTLRErrorProvidingParser parser) {
            this.parser = parser;
        }

        @Override
        public Object create(Token payload) {
            GrammarAST t = new GrammarAST(payload);
            if (parser != null)
                t.enclosingRuleName = parser.getCurrentRuleName();
            return t;
        }

        @Override
        public Object errorNode(TokenStream input, Token start, Token stop, RecognitionException e) {
            GrammarAST t = new GrammarASTErrorNode(input, start, stop, e);
            if (parser != null)
                t.enclosingRuleName = parser.getCurrentRuleName();

            return t;
        }
    }
}
