/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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

    public GrammarAnnotatedParseTree(@NonNull ParserRuleContext<Token> parseTree) {
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
