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
import java.util.Map;
import org.antlr.v4.Tool;
import org.antlr.v4.tool.LexerGrammar;
import org.antlr.v4.tool.ast.GrammarRootAST;
import org.antlr.works.editor.grammar.debugger.TracingLexer.LexerAction;

/**
 *
 * @author Sam Harwell
 */
public class TracingLexerGrammar extends LexerGrammar {
    public Map<Integer, Collection<LexerAction>> _actionsMap;

    public TracingLexerGrammar(Tool tool, GrammarRootAST ast) {
        super(tool, ast);
    }

}
