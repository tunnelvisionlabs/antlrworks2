/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.highlighter;

import java.util.HashSet;
import java.util.Set;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.Token;
import org.antlr.works.editor.antlr3.highlighting.TokenSourceWithState;

/**
 *
 * @author Sam Harwell
 */
public class ANTLRHighlighterLexer implements TokenSourceWithState<ANTLRHighlighterState> {
    private CharStream input;
    private GrammarHighlighterLexer grammarLexer;
    private ActionHighlighterLexer actionLexer;
    
    // state variables
    private ANTLRHighlighterMode mode;
    private int actionLevel;
    private boolean inComment;
    private boolean inOptions;
    private boolean inTokens;

    public ANTLRHighlighterLexer(CharStream input, ANTLRHighlighterState state) {
        this.input = input;
        grammarLexer = new GrammarHighlighterLexer(input, this);
        actionLexer = new ActionHighlighterLexer(input, this);
        setState(state);
    }

    @Override
    public CharStream getCharStream() {
        return input;
    }

    @Override
    public String getSourceName() {
        return "ANTLR Highlighter";
    }

    int getActionLevel() {
        return actionLevel;
    }

    void setActionLevel(int value) {
        actionLevel = value;
    }

    ANTLRHighlighterMode getMode() {
        return mode;
    }

    void setMode(ANTLRHighlighterMode value) {
        mode = value;
    }

    boolean getInComment() {
        return inComment;
    }

    void setInComment(boolean value) {
        inComment = value;
    }

    boolean getInDoubleAngleStringLiteral() {
        return getMode().equals(ANTLRHighlighterMode.GRAMMAR_DOUBLE_ANGLE_STRING_LITERAL);
    }

    void setInDoubleAngleStringLiteral(boolean value) {
        setMode(value ? ANTLRHighlighterMode.GRAMMAR_DOUBLE_ANGLE_STRING_LITERAL : ANTLRHighlighterMode.GRAMMAR);
    }

    boolean getInCharLiteral() {
        return getMode().equals(ANTLRHighlighterMode.ACTION_CHAR_LITERAL)
            || getMode().equals(ANTLRHighlighterMode.ARG_ACTION_CHAR_LITERAL);
    }

    void setInCharLiteral(boolean value) {
        if (getInCharLiteral() == value)
            return;

        if (getMode().equals(ANTLRHighlighterMode.ACTION)) {
            setMode(ANTLRHighlighterMode.ACTION_CHAR_LITERAL);
        } else if (getMode().equals(ANTLRHighlighterMode.ACTION_CHAR_LITERAL)) {
            setMode(ANTLRHighlighterMode.ACTION);
        } else if (getMode().equals(ANTLRHighlighterMode.ARG_ACTION)) {
            setMode(ANTLRHighlighterMode.ARG_ACTION_CHAR_LITERAL);
        } else if (getMode().equals(ANTLRHighlighterMode.ARG_ACTION_CHAR_LITERAL)) {
            setMode(ANTLRHighlighterMode.ARG_ACTION);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    boolean getInStringLiteral() {
        return getMode().equals(ANTLRHighlighterMode.ACTION_STRING_LITERAL)
            || getMode().equals(ANTLRHighlighterMode.ARG_ACTION_STRING_LITERAL);
    }

    void setInStringLiteral(boolean value) {
        if (getInStringLiteral() == value)
            return;

        if (getMode().equals(ANTLRHighlighterMode.ACTION)) {
            setMode(ANTLRHighlighterMode.ACTION_STRING_LITERAL);
        } else if (getMode().equals(ANTLRHighlighterMode.ACTION_STRING_LITERAL)) {
            setMode(ANTLRHighlighterMode.ACTION);
        } else if (getMode().equals(ANTLRHighlighterMode.ARG_ACTION)) {
            setMode(ANTLRHighlighterMode.ARG_ACTION_STRING_LITERAL);
        } else if (getMode().equals(ANTLRHighlighterMode.ARG_ACTION_STRING_LITERAL)) {
            setMode(ANTLRHighlighterMode.ARG_ACTION);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    boolean getInOptions() {
        return inOptions;
    }

    void setInOptions(boolean value) {
        inOptions = value;
    }

    boolean getInTokens() {
        return inTokens;
    }

    void setInTokens(boolean value) {
        inTokens = value;
    }

    @Override
    public ANTLRHighlighterState getState() {
        return new ANTLRHighlighterState(mode, actionLevel, inComment, inOptions, inTokens);
    }

    public final void setState(ANTLRHighlighterState state) {
        this.mode = state.Mode;
        this.actionLevel = state.ActionLevel;
        this.inComment = state.InComment;
        this.inOptions = state.InOptions;
        this.inTokens = state.InTokens;
    }

    @Override
    public Token nextToken() {
        Token token = null;
        do {
            int position = input.index();
            token = nextTokenCore();
            // ensure progress
            if (position == input.index()) {
                input.consume();
            }
        } while (token == null || token.getType() == GrammarHighlighterLexer.NEWLINE);

        return token;
    }

    private Token nextTokenCore() {
        Token token;

        if (getMode().equals(ANTLRHighlighterMode.ACTION)
                && !(getActionLevel() == 1 && (getInOptions() || getInTokens()) && input.LA(1) != '}')) {

            int nextChar = input.LA(1);
            if (nextChar == '{' && !getInComment()) {
                token = grammarLexer.nextToken();
                setActionLevel(getActionLevel() + 1);
                token.setType(GrammarHighlighterLexer.ACTION);
            } else if (nextChar == '}' && !getInComment()) {
                token = grammarLexer.nextToken();
                setActionLevel(getActionLevel() - 1);
                token.setType(GrammarHighlighterLexer.ACTION);
                if (getActionLevel() == 0) {
                    setMode(ANTLRHighlighterMode.GRAMMAR);
                    if (getInOptions() || getInTokens()) {
                        token.setType(GrammarHighlighterLexer.RCURLY);
                        setInOptions(false);
                        setInTokens(false);
                    }
                }
            } else {
                token = actionLexer.nextToken();
            }
            
        } else if (getMode().equals(ANTLRHighlighterMode.ACTION_CHAR_LITERAL)
                || getMode().equals(ANTLRHighlighterMode.ACTION_STRING_LITERAL)
                || getMode().equals(ANTLRHighlighterMode.ARG_ACTION_CHAR_LITERAL)
                || getMode().equals(ANTLRHighlighterMode.ARG_ACTION_STRING_LITERAL)) {

            token = actionLexer.nextToken();

        } else if (getMode().equals(ANTLRHighlighterMode.ARG_ACTION)) {

            if (input.LA(1) == ']') {
                token = grammarLexer.nextToken();
                setMode(ANTLRHighlighterMode.GRAMMAR);
            } else {
                token = actionLexer.nextToken();
            }
            
        } else if (getMode().equals(ANTLRHighlighterMode.GRAMMAR_DOUBLE_ANGLE_STRING_LITERAL)) {

            token = grammarLexer.nextToken();
            
        } else {

            token = grammarLexer.nextToken();
            switch (token.getType()) {
                case GrammarHighlighterLexer.LCURLY:
                    setActionLevel(getActionLevel() + 1);
                    setMode(ANTLRHighlighterMode.ACTION);
                    if ((!getInOptions() && !getInTokens()) || getActionLevel() != 1) {
                        token.setType(GrammarHighlighterLexer.ACTION);
                    }
                    break;
                    
                case GrammarHighlighterLexer.LBRACK:
                    setMode(ANTLRHighlighterMode.ARG_ACTION);
                    break;
                    
                case GrammarHighlighterLexer.IDENTIFIER:
                    if (token.getText().equals("options")) {
                        setInOptions(true);
                    } else if (token.getText().equals("tokens")) {
                        setInTokens(true);
                    } else {
                        if (getInOptions()) {
                            token.setType(GrammarHighlighterLexer.OptionValue);
                        }
                        
                        ANTLRHighlighterState currentState = getState();
                        int marker = input.mark();
                        try {
                            while (true) {
                                Token nextToken = nextTokenCore();
                                switch (nextToken.getType()) {
                                    case GrammarHighlighterLexer.NEWLINE:
                                    case GrammarHighlighterLexer.WS:
                                    case GrammarHighlighterLexer.COMMENT:
                                    //case GrammarHighlighterLexer.DOC_COMMENT:
                                    case GrammarHighlighterLexer.ML_COMMENT:
                                    //case GrammarHighlighterLexer.SL_COMMENT:
                                        continue;
                                        
                                    case GrammarHighlighterLexer.ASSIGN:
                                        if (getInOptions()) {
                                            if (isValidOption(token.getText())) {
                                                token.setType(GrammarHighlighterLexer.ValidGrammarOption);
                                            } else {
                                                token.setType(GrammarHighlighterLexer.InvalidGrammarOption);
                                            }
                                        } else if (getInTokens()) {
                                            // don't have anything to do here for now
                                        } else {
                                            token.setType(GrammarHighlighterLexer.LABEL);
                                        }

                                        break;
                                        
                                    case GrammarHighlighterLexer.PLUS_ASSIGN:
                                        token.setType(GrammarHighlighterLexer.LABEL);
                                        break;
                                        
                                    default:
                                        break;
                                }
                                
                                break;
                            }
                        } finally {
                            input.rewind(marker);
                            setState(currentState);
                        }
                    }

                    break;
                    
                default:
                    break;
            }
        }
        
        return token;
    }
    
    private static final Set<String> ValidOptions = new HashSet<String>()
        {{
                add("language");
                add("tokenVocab");
                add("TokenLabelType");
                add("superClass");
                add("filter");
                add("k");
                add("backtrack");
                add("memoize");
                add("output");
                add("rewrite");
                add("ASTLabelType");
                add("greedy");
        }};
    
    private static boolean isValidOption(String option) {
        if (option == null || option.length() == 0)
            return false;
        
        return ValidOptions.contains(option);
    }
}
