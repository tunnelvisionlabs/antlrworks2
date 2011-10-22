/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
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
package org.antlr.works.editor.grammar.experimental;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Position;
import org.antlr.v4.runtime.ParserRuleContext;
import org.openide.util.Exceptions;

/**
 *
 * @author sam
 */
public class GrammarParserAnchorListener extends BlankGrammarParserListener {

    private final Stack<Position> anchorPositions = new Stack<Position>();
    private final List<Anchor> anchors = new ArrayList<Anchor>();
    private final Document document;

    public GrammarParserAnchorListener(Document document) {
        this.document = document;
    }

    public List<Anchor> getAnchors() {
        return anchors;
    }

    @Override
    public void enterRule(GrammarParser.ruleContext ctx) {
        enterAnchor(ctx);
    }

    @Override
    public void exitRule(GrammarParser.ruleContext ctx) {
        exitAnchor(ctx, GrammarParser.RULE_rule);
    }

    @Override
    public void enterRule(GrammarParser.tokenSpecContext ctx) {
        enterAnchor(ctx);
    }

    @Override
    public void exitRule(GrammarParser.tokenSpecContext ctx) {
        exitAnchor(ctx, GrammarParser.RULE_tokenSpec);
    }

    private void enterAnchor(ParserRuleContext ctx) {
        anchorPositions.push(createPosition(ctx.getStart().getStartIndex()));
    }

    private void exitAnchor(ParserRuleContext ctx, int anchorId) {
        Position startPosition = anchorPositions.pop();
        Position stopPosition = ctx.getStop() != null ? createPosition(ctx.getStop().getStopIndex() + 1) : null;
        if (startPosition != null && stopPosition != null) {
            anchors.add(createAnchor(startPosition, stopPosition, anchorId));
        }
    }

    private Position createPosition(int offset) {
        try {
            return document.createPosition(offset);
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
    }

    private static Anchor createAnchor(Position startPosition, Position endPosition, int rule) {
        return new AnchorImpl(startPosition, endPosition, rule);
    }

    public interface Anchor {

        public Position getStartPosition();

        public Position getEndPosition();

        public int getRule();

    }

    public static class AnchorImpl implements Anchor {
        private final Position startPosition;
        private final Position endPosition;
        private final int rule;

        private AnchorImpl(Position startPosition, Position endPosition, int rule) {
            this.startPosition = startPosition;
            this.endPosition = endPosition;
            this.rule = rule;
        }

        @Override
        public Position getStartPosition() {
            return startPosition;
        }

        @Override
        public Position getEndPosition() {
            return endPosition;
        }

        @Override
        public int getRule() {
            return rule;
        }

    }

}
