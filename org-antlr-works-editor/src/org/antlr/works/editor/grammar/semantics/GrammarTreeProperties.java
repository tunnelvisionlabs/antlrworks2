/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.semantics;

import org.antlr.netbeans.semantics.ObjectProperty;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author Sam Harwell
 */
public final class GrammarTreeProperties {

    public static final ObjectProperty<NodeType> PROP_NODE_TYPE = new ObjectProperty<NodeType>("node-type", NodeType.UNDEFINED);
    public static final ObjectProperty<Token> PROP_TARGET = new ObjectProperty<Token>("target");
    public static final ObjectProperty<Boolean> PROP_IMPLICIT_DEF = new ObjectProperty<Boolean>("implicit-def", false);
    public static final ObjectProperty<Boolean> PROP_MISSING_DEF = new ObjectProperty<Boolean>("missing-def", false);

    private GrammarTreeProperties() {
    }
}
