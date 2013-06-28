/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuleContext;
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
    private final Map<Integer, Collection<LexerAction>> _actions;

    public TracingLexer(LexerInterpreterData data, LexerTraceListener listener, CharStream input) {
        super(input);
        _listener = listener;
        _ruleNames = data.ruleNames.toArray(new String[data.ruleNames.size()]);
        _modeNames = data.modeNames.toArray(new String[data.modeNames.size()]);
        _grammarFileName = data.grammarFileName;
        if (data.actionsMap != null) {
            _actions = data.actionsMap;
        } else {
            _actions = new HashMap<>();
        }
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

    @Override
    public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
        Collection<LexerAction> actions = _actions.get(actionIndex);
        if (actions == null) {
            return;
        }

        for (LexerAction action : actions) {
            action.call(this);
        }
    }

    public interface LexerAction {

        void call(Lexer lexer);
    }

    public static class SkipAction implements LexerAction {

        public static final SkipAction INSTANCE = new SkipAction();

        @Override
        public void call(Lexer lexer) {
            lexer.skip();
        }

        private SkipAction() {
        }
    }

    public static class MoreAction implements LexerAction {

        public static final MoreAction INSTANCE = new MoreAction();

        @Override
        public void call(Lexer lexer) {
            lexer.more();
        }

        private MoreAction() {
        }
    }

    public static class PopModeAction implements LexerAction {

        public static final PopModeAction INSTANCE = new PopModeAction();

        @Override
        public void call(Lexer lexer) {
            if (!lexer._modeStack.isEmpty()) {
                lexer.popMode();
            }
        }

        private PopModeAction() {
        }
    }

    public static class TypeAction implements LexerAction {

        private final int type;

        public TypeAction(int type) {
            this.type = type;
        }

        @Override
        public void call(Lexer lexer) {
            if (type != INVALID_CONSTANT) {
                lexer.setType(type);
            }
        }
    }

    public static class ModeAction implements LexerAction {

        private final int mode;

        public ModeAction(int mode) {
            this.mode = mode;
        }

        @Override
        public void call(Lexer lexer) {
            if (mode != INVALID_CONSTANT) {
                lexer._mode = mode;
            }
        }
    }

    public static class PushModeAction implements LexerAction {

        private final int mode;

        public PushModeAction(int type) {
            this.mode = type;
        }

        @Override
        public void call(Lexer lexer) {
            if (mode != INVALID_CONSTANT) {
                lexer.pushMode(mode);
            } else {
                // push the current mode to at least keep push/pop balanced
                lexer.pushMode(lexer._mode);
            }
        }
    }

    public static class ChannelAction implements LexerAction {

        private final int channel;

        public ChannelAction(int type) {
            this.channel = type;
        }

        @Override
        public void call(Lexer lexer) {
            if (channel != INVALID_CONSTANT) {
                lexer.setChannel(channel);
            }
        }
    }
}
