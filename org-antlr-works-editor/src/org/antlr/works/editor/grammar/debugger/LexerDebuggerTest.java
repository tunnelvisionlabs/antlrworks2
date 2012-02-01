/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.works.editor.grammar.experimental.GrammarLexer;
import org.antlr.works.editor.st4.highlighter4.GroupHighlighterLexer;
import org.antlr.works.editor.st4.highlighter4.GroupHighlighterLexerBase;

/**
 *
 * @author Sam Harwell
 */
public class LexerDebuggerTest {
    private static final boolean DEBUG_TEMPLATE_FILES = true;

    private LexerDebuggerTest() {
    }

    public static List<String> getTokenNames() {
        if (DEBUG_TEMPLATE_FILES) {
            return Arrays.asList(GroupHighlighterLexerBase.tokenNames);
        } else {
            return Arrays.asList(GrammarLexer.tokenNames);
        }
    }

    public static Lexer createLexer(CharStream input) {
        if (DEBUG_TEMPLATE_FILES) {
            return new GroupHighlighterLexer(input);
        } else {
            return new GrammarLexer(input);
        }
    }

    public static List<String> getModeNames() {
        if (DEBUG_TEMPLATE_FILES) {
            return Arrays.asList(
                "DEFAULT",
                "BlockComment",
                "TemplateComment",
                "AnonymousTemplate",
                "AnonymousTemplateParameters",
                "BigStringTemplate",
                "BigStringLineTemplate",
                "TemplateExpression",
                "StringTemplate");
        } else {
            return Arrays.asList("DEFAULT", "ArgAction", "Action");
        }
    }
}
