/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.semantics;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.Tree;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class GrammarAnnotatedParseTree extends AnnotatedParseTree {

    public GrammarAnnotatedParseTree(@NonNull ParserRuleContext parseTree) {
        super(parseTree);
    }

//    @NonNull
//    public CodeElementReference getTarget(ParserRuleContext<Token> parseTree) {
//        Object property = getAnnotations().getProperty(parseTree, AlloyAnnotations.PROP_ELEMENT_REFERENCE);
//        if (!(property instanceof CodeElementReference)) {
//            return CodeElementReference.MISSING;
//        }
//
//        return (CodeElementReference)property;
//    }
//
//    @NonNull
//    public CodeElementReference getTarget(Token token) {
//        Object property = getAnnotations().getProperty(token, AlloyAnnotations.PROP_ELEMENT_REFERENCE);
//        if (!(property instanceof CodeElementReference)) {
//            return CodeElementReference.MISSING;
//        }
//
//        return (CodeElementReference)property;
//    }

    public boolean isDefinition(Token token) {
        return getNodeType(token).isDefinition();
    }

    @NonNull
    public NodeType getNodeType(Tree tree) {
        NodeType property = getTreeDecorator().getProperty(tree, GrammarTreeProperties.PROP_NODE_TYPE);
        if (property == null) {
            return NodeType.UNDEFINED;
        }

        return property;
    }

    @NonNull
    public NodeType getNodeType(Token token) {
        NodeType property = getTokenDecorator().getProperty(token, GrammarTreeProperties.PROP_NODE_TYPE);
        if (property == null) {
            return NodeType.UNDEFINED;
        }

        return property;
    }

}
