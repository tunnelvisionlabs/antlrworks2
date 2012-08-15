/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.completion;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.DecisionState;
import org.antlr.v4.runtime.atn.PlusBlockStartState;
import org.antlr.v4.runtime.atn.PlusLoopbackState;
import org.antlr.v4.runtime.atn.StarLoopEntryState;
import org.antlr.v4.runtime.atn.StarLoopbackState;
import org.antlr.v4.runtime.misc.IntegerList;
import org.antlr.v4.runtime.misc.IntervalSet;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractForestParser implements ForestParser {
    protected static final Logger LOGGER = Logger.getLogger(AbstractForestParser.class.getName());

    @Override
    public Map<RuleContext<Token>, CaretReachedException> getParseTrees(CodeCompletionParser parser) {
        List<MultipleDecisionData> potentialAlternatives = new ArrayList<MultipleDecisionData>();
        IntegerList currentPath = new IntegerList();
        Map<RuleContext<Token>, CaretReachedException> results = new IdentityHashMap<RuleContext<Token>, CaretReachedException>();
        // make sure the token stream is initialized before getting the index
        parser.getInputStream().LA(1);
        int initialToken = parser.getInputStream().index();
        while (true) {
            parser.getInputStream().seek(initialToken);
            tryParse(parser, potentialAlternatives, currentPath, results);
            if (!incrementCurrentPath(potentialAlternatives, currentPath)) {
                break;
            }
        }

        if (LOGGER.isLoggable(Level.FINE)) {
            for (Map.Entry<RuleContext<Token>, CaretReachedException> entry : results.entrySet()) {
                LOGGER.log(Level.FINE, entry.getKey().toStringTree((Parser)parser));
            }
        }

        return results;
    }

    protected boolean incrementCurrentPath(List<MultipleDecisionData> potentialAlternatives, IntegerList currentPath) {
        for (int i = currentPath.size() - 1; i >= 0; i--) {
            if (currentPath.get(i) < potentialAlternatives.get(i).alternatives.length - 1) {
                currentPath.set(i, currentPath.get(i) + 1);
                return true;
            }

            potentialAlternatives.remove(i);
            currentPath.removeAt(i);
        }

        return false;
    }

    protected void tryParse(CodeCompletionParser parser, List<MultipleDecisionData> potentialAlternatives, IntegerList currentPath, Map<RuleContext<Token>, CaretReachedException> results) {
        RuleContext<Token> parseTree;
        try {
            parser.getInterpreter().setFixedDecisions(potentialAlternatives, currentPath);
            parseTree = parseImpl(parser);
            results.put(parseTree, null);
        } catch (CaretReachedException ex) {
            if (ex.getTransitions() == null) {
                return;
            }

            for (parseTree = ex.getFinalContext(); parseTree.getParent() != null; parseTree = parseTree.getParent()) {
                // intentionally blank
            }

            if (ex.getCause() instanceof FailedPredicateException) {
                return;
            }

            if (ex.getCause() != null) {
                IntervalSet alts = new IntervalSet();
                for (ATNConfig c : ex.getTransitions().keySet()) {
                    alts.add(c.getAlt());
                }

                MultipleDecisionData decisionData = new MultipleDecisionData();
                decisionData.inputIndex = parser.getInputStream().index();
                decisionData.decision = 0;
                if (ex.getCause() != null) {
                    int stateNumber = ex.getCause().getOffendingState();
                    ATNState state = parser.getATN().states.get(stateNumber);
                    if (state instanceof StarLoopbackState) {
                        assert state.getNumberOfTransitions() == 1 && state.onlyHasEpsilonTransitions();
                        assert state.transition(0).target instanceof StarLoopEntryState;
                        state = state.transition(0).target;
                    } else if (state instanceof PlusBlockStartState && ((PlusBlockStartState)state).decision == -1) {
                        state = ((PlusBlockStartState)state).loopBackState;
                        assert state instanceof PlusLoopbackState;
                    }

                    if (state instanceof DecisionState) {
                        decisionData.decision = ((DecisionState)state).decision;
                        if (decisionData.decision <= 0) {
                            LOGGER.log(Level.FINE, "No decision number found for state {0}.", state.stateNumber);
                        }
                    } else {
                        if (state != null) {
                            LOGGER.log(Level.FINE, "No decision number found for state {0}.", state.stateNumber);
                        } else {
                            LOGGER.log(Level.FINE, "No decision number found for state <null>.");
                        }
                        // continuing is likely to never terminate
                        return;
                    }
                }
                decisionData.alternatives = alts.toArray();
                potentialAlternatives.add(decisionData);
                currentPath.add(-1);
            }
            else {
                results.put(parseTree, ex);
            }
        } catch (RecognitionException ex) {
            // not a viable path
        }
    }

    protected abstract RuleContext<Token> parseImpl(CodeCompletionParser parser);

}
