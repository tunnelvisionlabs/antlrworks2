/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codemodel;

/**
 *
 * @author Sam Harwell
 */
public enum RuleKind {

    LEXER,
    PARSER,
    /** Pseudo-rule kind for an entry in a {@code tokens{}} block.*/
    TOKEN,

    UNKNOWN,
    UNDEFINED,

}
