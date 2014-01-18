/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author Sam Harwell
 */
public class TracingLexer extends Lexer {
    public static final int INVALID_CONSTANT = Integer.MAX_VALUE;

    private final LexerTraceListener _listener;
    private final String[] _ruleNames;
    private final String[] _modeNames;
    private final String _grammarFileName;

    public TracingLexer(LexerInterpreterData data, LexerTraceListener listener, CharStream input) {
        super(input);
        _listener = listener;
        _ruleNames = data.ruleNames.toArray(new String[data.ruleNames.size()]);
        _modeNames = data.modeNames.toArray(new String[data.modeNames.size()]);
        _grammarFileName = data.grammarFileName;
    }

    @Override
    public String[] getRuleNames() {
        return _ruleNames;
    }

    @Override
    public String[] getModeNames() {
        return _modeNames;
    }

    @Override
    public String getGrammarFileName() {
        return _grammarFileName;
    }

    @Override
    public int popMode() {
        _listener.popMode();
        return super.popMode();
    }

    @Override
    public void pushMode(int m) {
        _listener.pushMode(m);
        super.pushMode(m);
    }

    @Override
    public void emit(Token token) {
        _listener.emit(token.getStartIndex(), token.getStopIndex(), token.getType(), token.getChannel());
        super.emit(token);
    }
}
