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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.automata.LexerATNFactory;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.tool.LexerGrammar;
import org.antlr.v4.tool.ast.ActionAST;
import org.antlr.v4.tool.ast.GrammarAST;
import org.antlr.works.editor.grammar.debugger.TracingLexer.ChannelAction;
import org.antlr.works.editor.grammar.debugger.TracingLexer.LexerAction;
import org.antlr.works.editor.grammar.debugger.TracingLexer.ModeAction;
import org.antlr.works.editor.grammar.debugger.TracingLexer.MoreAction;
import org.antlr.works.editor.grammar.debugger.TracingLexer.PopModeAction;
import org.antlr.works.editor.grammar.debugger.TracingLexer.PushModeAction;
import org.antlr.works.editor.grammar.debugger.TracingLexer.SkipAction;
import org.antlr.works.editor.grammar.debugger.TracingLexer.TypeAction;

/**
 *
 * @author Sam Harwell
 */
public class TracingLexerATNFactory extends LexerATNFactory {
    // -J-Dorg.antlr.works.editor.grammar.debugger.TracingLexerATNFactory.level=FINE
    private static final Logger LOGGER = Logger.getLogger(TracingLexerATNFactory.class.getName());

    private static final Map<String, Integer> COMMON_CONSTANTS =
        new HashMap<String, Integer>();
    static {
        COMMON_CONSTANTS.put("HIDDEN", Lexer.HIDDEN);
        COMMON_CONSTANTS.put("DEFAULT_TOKEN_CHANNEL", Lexer.DEFAULT_TOKEN_CHANNEL);
        COMMON_CONSTANTS.put("DEFAULT_MODE", Lexer.DEFAULT_MODE);
        COMMON_CONSTANTS.put("SKIP", Lexer.SKIP);
        COMMON_CONSTANTS.put("MORE", Lexer.MORE);
        COMMON_CONSTANTS.put("EOF", Lexer.EOF);
        COMMON_CONSTANTS.put("MAX_CHAR_VALUE", Lexer.MAX_CHAR_VALUE);
        COMMON_CONSTANTS.put("MIN_CHAR_VALUE", Lexer.MIN_CHAR_VALUE);
    }

    private List<LexerAction> _lexerActions;
    public Map<Integer, Collection<LexerAction>> _actionsMap =
        new HashMap<Integer, Collection<LexerAction>>();

    public TracingLexerATNFactory(LexerGrammar g) {
        super(g);
    }

    @Override
    public String lexerCallCommand(GrammarAST ID, GrammarAST arg) {
        createLexerAction(ID, arg);
        return super.lexerCallCommand(ID, arg);
    }

    @Override
    public String lexerCommand(GrammarAST ID) {
        createLexerAction(ID, null);
        return super.lexerCommand(ID);
    }

    @Override
    public Handle action(ActionAST action) {
        try {
            Handle result = super.action(action);
            if (_lexerActions != null && !_lexerActions.isEmpty()) {
                int actionIndex = g.lexerActions.get(action);
                _actionsMap.put(actionIndex, _lexerActions);
            }

            return result;
        } finally {
            _lexerActions = null;
        }
    }

    protected void createLexerAction(GrammarAST ID, GrammarAST arg) {
        if (_lexerActions == null) {
            _lexerActions = new ArrayList<LexerAction>();
        }

        String command = ID.getText();
        if ("skip".equals(command)) {
            _lexerActions.add(SkipAction.INSTANCE);
        } else if ("more".equals(command)) {
            _lexerActions.add(MoreAction.INSTANCE);
        } else if ("popMode".equals(command)) {
            _lexerActions.add(PopModeAction.INSTANCE);
        } else if ("mode".equals(command)) {
            String modeName = arg != null ? arg.getText() : null;
            int mode = getConstantValue(modeName);
            _lexerActions.add(new ModeAction(mode));
        } else if ("pushMode".equals(command)) {
            String modeName =arg != null ? arg.getText() : null;
            int mode = getConstantValue(modeName);
            _lexerActions.add(new PushModeAction(mode));
        } else if ("type".equals(command)) {
            String typeName = arg != null ? arg.getText() : null;
            int type = getConstantValue(typeName);
            _lexerActions.add(new TypeAction(type));
        } else if ("channel".equals(command)) {
            String channelName = arg != null ? arg.getText() : null;
            int channel = getConstantValue(channelName);
            _lexerActions.add(new ChannelAction(channel));
        } else {
            LOGGER.log(Level.WARNING, "The lexer command ''{0}'' is not yet supported by the interpreter.", command);
        }
    }

    protected int getConstantValue(String name) {
        if (name == null) {
            LOGGER.log(Level.WARNING, "Could not convert null name to constant.");
            return TracingLexer.INVALID_CONSTANT;
        }

        Integer commonConstant = COMMON_CONSTANTS.get(name);
        if (commonConstant != null) {
            return commonConstant;
        }

        int tokenType = g.getTokenType(name);
        if (tokenType != Token.INVALID_TYPE) {
            return tokenType;
        }

        List<String> modeNames = new ArrayList<String>(((LexerGrammar)g).modes.keySet());
        int mode = modeNames.indexOf(name);
        if (mode >= 0) {
            return mode;
        }

        try {
            return Integer.parseInt(name);
        } catch (NumberFormatException ex) {
            LOGGER.log(Level.WARNING, "Could not convert name ''{0}'' to constant.", name);
            return TracingLexer.INVALID_CONSTANT;
        }
    }

}
