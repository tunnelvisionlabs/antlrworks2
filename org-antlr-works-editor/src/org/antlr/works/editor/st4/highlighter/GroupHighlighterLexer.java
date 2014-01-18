/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.highlighter;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.AtomTransition;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.NotSetTransition;
import org.antlr.v4.runtime.atn.RangeTransition;
import org.antlr.v4.runtime.atn.RuleTransition;
import org.antlr.v4.runtime.atn.SetTransition;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.works.editor.st4.highlighter.generated.AbstractGroupHighlighterLexer;

/**
 *
 * @author Sam Harwell
 */
public class GroupHighlighterLexer extends AbstractGroupHighlighterLexer {
    public static final char DEFAULT_OPEN_DELIMITER = '<';
    public static final char DEFAULT_CLOSE_DELIMITER = '>';

    private static final Map<Integer, ATN> delimiterToATN = new HashMap<>();
    private static final int OPEN_DELIMITER_PLACEHOLDER = '\uFFF0';
    private static final int CLOSE_DELIMITER_PLACEHOLDER = '\uFFF1';

    private final Map<ATN, GroupHighlighterATNSimulator> atnToSimulator = new IdentityHashMap<>();

    public GroupHighlighterLexer(CharStream input) {
        this(input, DEFAULT_OPEN_DELIMITER, DEFAULT_CLOSE_DELIMITER);
    }

    @SuppressWarnings("LeakingThisInConstructor")
    public GroupHighlighterLexer(CharStream input, char openDelimiter, char closeDelimiter) {
        super(input);
        _interp = getSimulatorForDelimiters(openDelimiter, closeDelimiter);
    }

    @Override
    public Token emit() {
        switch (_type) {
        case LBRACE:
            if (handleAcceptPositionForKeyword("[")) {
                pushMode(AnonymousTemplateParameters);
            }

            break;

        case DELIMITERS:
            if (handleAcceptPositionForKeyword("delimiters")) {
                pushMode(DelimitersOpenSpec);
            }

            break;

        case FIRST:
            handleAcceptPositionForKeyword("first");
            break;

        case LAST:
            handleAcceptPositionForKeyword("last");
            break;

        case REST:
            handleAcceptPositionForKeyword("rest");
            break;

        case TRUNC:
            handleAcceptPositionForKeyword("trunc");
            break;

        case STRIP:
            handleAcceptPositionForKeyword("strip");
            break;

        case TRIM:
            handleAcceptPositionForKeyword("trim");
            break;

        case LENGTH:
            handleAcceptPositionForKeyword("length");
            break;

        case STRLEN:
            handleAcceptPositionForKeyword("strlen");
            break;

        case REVERSE:
            handleAcceptPositionForKeyword("reverse");
            break;

        case DelimitersOpenSpec_DELIMITER_STRING:
            setDelimiters(getText().charAt(1), getCloseDelimiter());
            _type = STRING;
            break;

        case DelimitersCloseSpec_DELIMITER_STRING:
            setDelimiters(getOpenDelimiter(), getText().charAt(1));
            _type = STRING;
            break;

        default:
            break;
        }

        return super.emit();
    }

    private boolean handleAcceptPositionForKeyword(String keyword) {
        if (getInputStream().index() > _tokenStartCharIndex + keyword.length()) {
            int offset = keyword.length() - 1;
            getInterpreter().resetAcceptPosition(getInputStream(), _tokenStartCharIndex + offset, _tokenStartLine, _tokenStartCharPositionInLine + offset);
            return true;
        }

        return false;
    }

    @Override
    public GroupHighlighterATNSimulator getInterpreter() {
        return (GroupHighlighterATNSimulator)super.getInterpreter();
    }

    public char getOpenDelimiter() {
        return getInterpreter().openDelimiter;
    }

    public char getCloseDelimiter() {
        return getInterpreter().closeDelimiter;
    }

    public void setDelimiters(char openDelimiter, char closeDelimiter) {
        GroupHighlighterATNSimulator interpreter = getInterpreter();
        if (interpreter.openDelimiter == openDelimiter && interpreter.closeDelimiter == closeDelimiter) {
            return;
        }

        _interp = getSimulatorForDelimiters(openDelimiter, closeDelimiter);
        getInterpreter().copyState(interpreter);
    }

    @Override
    protected boolean inStringTemplateMode() {
        if (_modeStack.size() < 2) {
            return false;
        }

        // index 0 is DEFAULT_MODE, StringTemplate is always 1 inside that
        return _modeStack.get(1) == StringTemplate;
    }

    private GroupHighlighterATNSimulator getSimulatorForDelimiters(char openDelimiter, char closeDelimiter) {
        ATN atn = getATNForDelimiters(openDelimiter, closeDelimiter);
        synchronized (atnToSimulator) {
            GroupHighlighterATNSimulator simulator = atnToSimulator.get(atn);
            if (simulator == null) {
                simulator = new GroupHighlighterATNSimulator(this, atn, openDelimiter, closeDelimiter);
                atnToSimulator.put(atn, simulator);
            }

            return simulator;
        }
    }

    private static synchronized ATN getATNForDelimiters(char openDelimiter, char closeDelimiter) {
        int key = (openDelimiter << 16) + (closeDelimiter & 0xFFFF);
        ATN atn = delimiterToATN.get(key);
        if (atn != null) {
            return atn;
        }

        atn = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
        for (ATNState state : atn.states) {
            if (state == null) {
                continue;
            }

            for (int i = 0; i < state.getNumberOfTransitions(); i++) {
                Transition t = state.transition(i);
                Transition updated = updateTransition(t, openDelimiter, closeDelimiter);
                if (updated != null) {
                    state.setTransition(i, updated);
                }
            }

            if (!state.isOptimized()) {
                continue;
            }

            for (int i = 0; i < state.getNumberOfOptimizedTransitions(); i++) {
                Transition t = state.getOptimizedTransition(i);
                Transition updated = updateTransition(t, openDelimiter, closeDelimiter);
                if (updated != null) {
                    state.setOptimizedTransition(i, updated);
                }
            }
        }

        delimiterToATN.put(key, atn);
        return atn;
    }

    private static Transition updateTransition(Transition t, char openDelimiter, char closeDelimiter) {
        Transition updated = null;
        if (t instanceof RuleTransition) {
            return null;
        } else if (t instanceof AtomTransition) {
            AtomTransition atomTransition = (AtomTransition)t;
            int newLabel;
            if (atomTransition.label == OPEN_DELIMITER_PLACEHOLDER) {
                newLabel = openDelimiter;
            } else if (atomTransition.label == CLOSE_DELIMITER_PLACEHOLDER) {
                newLabel = closeDelimiter;
            } else {
                return null;
            }

            updated = new AtomTransition(t.target, newLabel);
        } else if (t instanceof NotSetTransition) {
            NotSetTransition notSetTransition = (NotSetTransition)t;
            int removeLabel;
            int addLabel;
            if (notSetTransition.set.contains(OPEN_DELIMITER_PLACEHOLDER)) {
                removeLabel = OPEN_DELIMITER_PLACEHOLDER;
                addLabel = openDelimiter;
            } else if (notSetTransition.set.contains(CLOSE_DELIMITER_PLACEHOLDER)) {
                removeLabel = CLOSE_DELIMITER_PLACEHOLDER;
                addLabel = closeDelimiter;
            } else {
                return null;
            }

            IntervalSet set = new IntervalSet(notSetTransition.set);
            set.remove(removeLabel);
            set.add(addLabel);
            set.setReadonly(true);

            updated = new NotSetTransition(t.target, set);
        } else if (t instanceof SetTransition) {
            SetTransition setTransition = (SetTransition)t;
            int removeLabel;
            int addLabel;
            if (setTransition.set.contains(OPEN_DELIMITER_PLACEHOLDER)) {
                removeLabel = OPEN_DELIMITER_PLACEHOLDER;
                addLabel = openDelimiter;
            } else if (setTransition.set.contains(CLOSE_DELIMITER_PLACEHOLDER)) {
                removeLabel = CLOSE_DELIMITER_PLACEHOLDER;
                addLabel = closeDelimiter;
            } else {
                return null;
            }

            IntervalSet set = new IntervalSet(setTransition.set);
            set.remove(removeLabel);
            set.add(addLabel);
            set.setReadonly(true);

            updated = createSetTransition(t.target, set);
        } else if (t instanceof RangeTransition) {
            RangeTransition rangeTransition = (RangeTransition)t;
            int removeLabel;
            int addLabel;
            if (rangeTransition.from <= OPEN_DELIMITER_PLACEHOLDER && rangeTransition.to >= OPEN_DELIMITER_PLACEHOLDER) {
                removeLabel = OPEN_DELIMITER_PLACEHOLDER;
                addLabel = openDelimiter;
            } else if (rangeTransition.from <= CLOSE_DELIMITER_PLACEHOLDER && rangeTransition.to >= CLOSE_DELIMITER_PLACEHOLDER) {
                removeLabel = CLOSE_DELIMITER_PLACEHOLDER;
                addLabel = closeDelimiter;
            } else {
                return null;
            }

            IntervalSet set = IntervalSet.of(rangeTransition.from, rangeTransition.to);
            set.remove(removeLabel);
            set.add(addLabel);
            set.setReadonly(true);

            updated = createSetTransition(t.target, set);
        }

        return updated;
    }

    private static Transition createSetTransition(ATNState target, IntervalSet set) {
        if (set.getIntervals().size() == 1) {
            Interval interval = set.getIntervals().get(0);
            if (interval.a == interval.b) {
                return new AtomTransition(target, interval.a);
            } else {
                return new RangeTransition(target, interval.a, interval.b);
            }
        } else {
            return new SetTransition(target, set);
        }
    }

    protected static class GroupHighlighterATNSimulator extends LexerATNSimulator {
        private final char openDelimiter;
        private final char closeDelimiter;

        public GroupHighlighterATNSimulator(Lexer recog, ATN atn, char openDelimiter, char closeDelimiter) {
            super(recog, atn);
            this.openDelimiter = openDelimiter;
            this.closeDelimiter = closeDelimiter;
        }

        protected void resetAcceptPosition(CharStream input, int index, int line, int charPositionInLine) {
            input.seek(index);
            this.line = line;
            this.charPositionInLine = charPositionInLine;
            consume(input);
        }

    }
}
