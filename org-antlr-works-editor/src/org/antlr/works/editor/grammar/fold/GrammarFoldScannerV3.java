/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.fold;

import java.util.ArrayList;
import java.util.List;
import org.antlr.grammar.v3.ANTLRParser;
import org.antlr.netbeans.editor.fold.AbstractFoldScanner;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.works.editor.grammar.parser.CompiledModel;
import org.antlr.works.editor.grammar.parser.CompiledModelV3;

/**
 *
 * @author Sam Harwell
 */
public class GrammarFoldScannerV3 extends AbstractFoldScanner<CompiledModel> {

    @Override
    protected List<FoldInfo> calculateFolds(ParserData<CompiledModel> baseResult) {
        CompiledModelV3 result3 = (CompiledModelV3)baseResult.getData();
        DocumentSnapshot snapshot = result3.getSnapshot();

        final List<FoldInfo> folds = new ArrayList<FoldInfo>();

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

                    CommonToken startToken = result3.getResult().getTokens()[child.getTokenStartIndex()];
                    CommonToken stopToken = result3.getResult().getTokens()[child.getTokenStopIndex()];

                    if (startToken.getType() == ANTLRParser.DOC_COMMENT) {
                        for (int index = child.getTokenStartIndex(); index <= child.getTokenStopIndex(); index++) {
                            startToken = result3.getResult().getTokens()[index];
                            if (startToken.getType() != ANTLRParser.DOC_COMMENT && startToken.getChannel() != Token.HIDDEN_CHANNEL) {
                                break;
                            }
                        }
                    }

                    int startLine = snapshot.findLineNumber(startToken.getStartIndex());
                    int stopLine = snapshot.findLineNumber(stopToken.getStopIndex());
                    if (startLine >= stopLine) {
                        continue;
                    }

                    SnapshotPositionRegion region = new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(startToken.getStartIndex(), stopToken.getStopIndex() + 1));
                    FoldInfo info = new FoldInfo(region, blockHint);
                    folds.add(info);
                }
            }

            for (CommonToken token : result3.getResult().getTokens()) {
                switch (token.getType()) {
                case ANTLRParser.DOC_COMMENT:
                case ANTLRParser.ML_COMMENT:
                case ANTLRParser.ACTION:
                    int startLine = snapshot.findLineNumber(token.getStartIndex());
                    int stopLine = snapshot.findLineNumber(token.getStopIndex());
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

                    SnapshotPositionRegion region = new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(token.getStartIndex(), token.getStopIndex() + 1));
                    FoldInfo info = new FoldInfo(region, blockHint);
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
