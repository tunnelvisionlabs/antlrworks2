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
package org.antlr.works.editor.grammar.fold;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.StyledDocument;
import org.antlr.grammar.v3.ANTLRParser;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.works.editor.grammar.parser.GrammarParser;
import org.antlr.works.editor.grammar.parser.GrammarParserV3;
import org.openide.text.NbDocument;

/**
 *
 * @author Sam Harwell
 */
public class GrammarFoldManagerTaskV3 extends GrammarFoldManagerTask {

    @Override
    protected List<GrammarFoldManagerTask.FoldInfo> calculateFolds(StyledDocument document, GrammarParser.GrammarParserResult result) {
        GrammarParserV3.GrammarParserResultV3 result3 = (GrammarParserV3.GrammarParserResultV3)result;

        final List<GrammarFoldManagerTask.FoldInfo> folds = new ArrayList<GrammarFoldManagerTask.FoldInfo>();

        /*if (!result.getParser().getSyntaxErrors().isEmpty() && !foldManager.currentFolds.isEmpty()) {
            return;
        }*/

        ANTLRParser.grammar__return parseResult = result3.getResult().getResult();
        CommonTree tree = (CommonTree)parseResult.getTree();
        if (tree != null) {
            for (Object childObject : tree.getChildren()) {
                CommonTree child = (CommonTree)childObject;
                if (child == null || child.getText() == null || child.getText().isEmpty()) {
                    continue;
                }

                if (child.getText().equals("rule") && child.getChildCount() > 0 || child.getText().startsWith("tokens") || child.getText().startsWith("options")) {
                    String blockHint = "...";
                    if (child.getText().equals("rule")) {
                        String ruleName = child.getChild(0).getText();
                        // don't try to outline the artificial tokens rule
                        if (ruleName.equals("Tokens")) {
                            continue;
                        }

                        blockHint = child.getChild(0).getText() + "...";
                    } else if (child.getText().startsWith("tokens")) {
                        // this is the special tokens{} block of a combined grammar
                        blockHint = "tokens {...}";
                    } else if (child.getText().startsWith("options")) {
                        // this is the special options{} block of a grammar
                        blockHint = "options {...}";
                    }

                    CommonToken startToken = result.getResult().getTokens()[child.getTokenStartIndex()];
                    CommonToken stopToken = result.getResult().getTokens()[child.getTokenStopIndex()];

                    if (startToken.getType() == ANTLRParser.DOC_COMMENT) {
                        for (int index = child.getTokenStartIndex(); index <= child.getTokenStopIndex(); index++) {
                            startToken = result.getResult().getTokens()[index];
                            if (startToken.getType() != ANTLRParser.DOC_COMMENT && startToken.getChannel() != Token.HIDDEN_CHANNEL) {
                                break;
                            }
                        }
                    }

                    int startLine = NbDocument.findLineNumber(document, startToken.getStartIndex());
                    int stopLine = NbDocument.findLineNumber(document, stopToken.getStopIndex() + 1);
                    if (startLine >= stopLine) {
                        continue;
                    }

                    GrammarFoldManagerTask.FoldInfo info = new GrammarFoldManagerTask.FoldInfo(document, startToken.getStartIndex(), stopToken.getStopIndex() + 1, blockHint);
                    folds.add(info);
                }
            }

            for (CommonToken token : result.getResult().getTokens()) {
                switch (token.getType()) {
                case ANTLRParser.DOC_COMMENT:
                case ANTLRParser.ML_COMMENT:
                case ANTLRParser.ACTION:
                    int startLine = NbDocument.findLineNumber(document, token.getStartIndex());
                    int stopLine = NbDocument.findLineNumber(document, token.getStopIndex() + 1);
                    if (startLine >= stopLine) {
                        continue;
                    }

                    String blockHint = null;
                    if (token.getType() == ANTLRParser.DOC_COMMENT) {
                        blockHint = "/** ... */";
                    } else if (token.getType() == ANTLRParser.ML_COMMENT) {
                        blockHint = "/* ... */";
                    } else if (token.getType() == ANTLRParser.ACTION) {
                        blockHint = "{}";
                    } else {
                        throw new IllegalStateException();
                    }

                    GrammarFoldManagerTask.FoldInfo info = new GrammarFoldManagerTask.FoldInfo(document, token.getStartIndex(), token.getStopIndex() + 1, blockHint);
                    folds.add(info);

                    break;

                default:
                    continue;
                }
            }
        }

        return folds;
    }

}
