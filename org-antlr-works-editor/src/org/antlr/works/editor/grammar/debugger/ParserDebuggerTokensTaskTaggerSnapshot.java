/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.LexerInterpreter;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.works.editor.antlr4.classification.AbstractTokensTaskTaggerSnapshot;
import org.antlr.works.editor.antlr4.classification.SimpleLexerState;
import org.antlr.works.editor.antlr4.highlighting.TokenSourceWithStateV4;
import org.antlr.works.editor.grammar.debugger.LexerDebuggerControllerTopComponent.TokenDescriptor;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
class ParserDebuggerTokensTaskTaggerSnapshot extends AbstractTokensTaskTaggerSnapshot<SimpleLexerState> {

    private final LexerInterpreterData lexerInterpreterData;

    public ParserDebuggerTokensTaskTaggerSnapshot(@NonNull DocumentSnapshot snapshot) {
        super(snapshot);
        lexerInterpreterData = (LexerInterpreterData)snapshot.getVersionedDocument().getDocument().getProperty(LexerDebuggerEditorKit.PROP_LEXER_INTERP_DATA);
    }

    protected ParserDebuggerTokensTaskTaggerSnapshot(@NonNull ParserDebuggerTokensTaskTaggerSnapshot reference, @NonNull DocumentSnapshot snapshot) {
        super(reference, snapshot);
        lexerInterpreterData = (LexerInterpreterData)snapshot.getVersionedDocument().getDocument().getProperty(LexerDebuggerEditorKit.PROP_LEXER_INTERP_DATA);
    }

    @Override
    protected SimpleLexerState getStartState() {
        return SimpleLexerState.INITIAL;
    }

    @Override
    protected TokenSourceWithStateV4<SimpleLexerState> createLexer(CharStream input, SimpleLexerState startState) {
        ATN atn = new ATNDeserializer().deserialize(lexerInterpreterData.serializedAtn.toCharArray());
        List<String> tokenNames = new ArrayList<>();
        for (TokenDescriptor tokenDescriptor : lexerInterpreterData.tokenNames) {
            tokenNames.add(tokenDescriptor.name);
        }

        String grammarFileName = lexerInterpreterData.grammarFileName;
        List<String> ruleNames = lexerInterpreterData.ruleNames;
        List<String> modeNames = lexerInterpreterData.modeNames;
        ParserDebuggerLexerWrapper lexer = new ParserDebuggerLexerWrapper(grammarFileName, tokenNames, ruleNames, modeNames, atn, input);
        startState.apply(lexer);
        return lexer;
    }

    @Override
    protected TokenSource getEffectiveTokenSource(TokenSourceWithStateV4<SimpleLexerState> lexer) {
        ATN atn = new ATNDeserializer().deserialize(lexerInterpreterData.serializedAtn.toCharArray());
        List<String> tokenNames = new ArrayList<>();
        for (TokenDescriptor tokenDescriptor : lexerInterpreterData.tokenNames) {
            tokenNames.add(tokenDescriptor.name);
        }

        String grammarFileName = lexerInterpreterData.grammarFileName;
        List<String> ruleNames = lexerInterpreterData.ruleNames;
        List<String> modeNames = lexerInterpreterData.modeNames;
        return new ParserDebuggerLexerWrapper(grammarFileName, tokenNames, ruleNames, modeNames, atn, lexer.getInputStream());
    }

    @Override
    protected ParserDebuggerTokensTaskTaggerSnapshot translateToImpl(@NonNull DocumentSnapshot targetSnapshot) {
        return new ParserDebuggerTokensTaskTaggerSnapshot(this, targetSnapshot);
    }

    private static class ParserDebuggerLexerWrapper extends LexerInterpreter implements TokenSourceWithStateV4<SimpleLexerState> {

        public ParserDebuggerLexerWrapper(String grammarFileName, Collection<String> tokenNames, Collection<String> ruleNames, Collection<String> modeNames, ATN atn, CharStream input) {
            super(grammarFileName, tokenNames, ruleNames, modeNames, atn, input);
        }

        @Override
        public SimpleLexerState getCurrentState() {
            return SimpleLexerState.createSimpleState(this);
        }

        @Override
        public void close() {
            // this lexer uses the normal shared ATN so there's need to do something special here
        }

    }
}
