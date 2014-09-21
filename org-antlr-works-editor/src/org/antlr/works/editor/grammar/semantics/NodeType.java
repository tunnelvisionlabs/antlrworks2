/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.semantics;

/**
 *
 * @author Sam Harwell
 */
public enum NodeType {

    GRAMMAR_DECL(true),
    GRAMMAR_REF(false),

    RULE_DECL(true),
    RULE_REF(false),

    TOKEN_DECL(true),
    TOKEN_REF(false),

    CHANNEL_DECL(true),
    CHANNEL_REF(false),

    MODE_DECL(true),
    MODE_REF(false),

    LABEL_DECL(true),
    LABEL_REF(false),

    ALT_LABEL(false),

    LEXER_ACTION(false),

    UNKNOWN(false),
    UNDEFINED(false),
    ;

    private final boolean definition;

    private NodeType(boolean definition) {
        this.definition = definition;
    }

    public boolean isDefinition() {
        return definition;
    }

}
