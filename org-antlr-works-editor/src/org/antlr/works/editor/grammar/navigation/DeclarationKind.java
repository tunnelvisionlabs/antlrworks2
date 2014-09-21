/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.navigation;

/**
 *
 * @author Sam Harwell
 */
public enum DeclarationKind {
    PARSER_RULE,
    LEXER_RULE,
    FRAGMENT_RULE,
    TOKEN,
    MODE,
    CHANNEL,

    UNKNOWN,
    UNDEFINED,
}
