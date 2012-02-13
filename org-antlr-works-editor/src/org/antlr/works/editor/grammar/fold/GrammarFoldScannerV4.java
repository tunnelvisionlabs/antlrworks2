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
import org.antlr.netbeans.editor.fold.AbstractFoldScanner;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.v4.parse.ANTLRParser;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.tool.ast.GrammarAST;
import org.antlr.v4.tool.ast.GrammarRootAST;
import org.antlr.works.editor.grammar.parser.CompiledModel;
import org.antlr.works.editor.grammar.parser.CompiledModelV4;

/**
 *
 * @author Sam Harwell
 */
public class GrammarFoldScannerV4 extends AbstractFoldScanner<CompiledModel> {

    @Override
    protected List<FoldInfo> calculateFolds(ParserData<CompiledModel> baseResult) {
        CompiledModelV4 result4 = (CompiledModelV4)baseResult.getData();
        DocumentSnapshot snapshot = result4.getSnapshot();

        final List<FoldInfo> folds = new ArrayList<FoldInfo>();

        GrammarRootAST parseResult = result4.getResult().getResult();
        if (parseResult != null) {
            IntervalSet foldTypes = new IntervalSet();
            foldTypes.add(ANTLRParser.MODE);
            foldTypes.add(ANTLRParser.RULE);
            foldTypes.add(ANTLRParser.TOKENS_SPEC);
            foldTypes.add(ANTLRParser.OPTIONS);
//            foldTypes.add(ANTLRParser.AT);

            List<GrammarAST> foldNodes = parseResult.getNodesWithType(foldTypes);
            for (GrammarAST child : foldNodes) {
                String blockHint;
                switch (child.getType()) {
                case ANTLRParser.MODE:
                    blockHint = "mode " + child.getChild(0).getText();
                    break;

                case ANTLRParser.RULE:
                    blockHint = child.getChild(0).getText() + "...";
                    break;

                case ANTLRParser.TOKENS_SPEC:
                    blockHint = "tokens {...}";
                    break;

                case ANTLRParser.OPTIONS:
                    blockHint = "options {...}";
                    break;

//                case ANTLRParser.AT:
//                    if (child.getChildCount() == 2) {
//                        blockHint = "@" + child.getChild(0) + " {...}";
//                    } else if (child.getChildCount() == 3) {
//                        blockHint = "@" + child.getChild(0) + "::" + child.getChild(1).getText() + " {...}";
//                    }
//                    break;

                default:
                    continue;
                }

                if (blockHint == null || blockHint.isEmpty()) {
                    continue;
                }

                FoldInfo fold = createFold(child, blockHint, snapshot, result4.getResult().getTokens());
                if (fold != null) {
                    folds.add(fold);
                }
            }

            for (CommonToken token : result4.getResult().getTokens()) {
                switch (token.getType()) {
                case ANTLRParser.DOC_COMMENT:
                case ANTLRParser.COMMENT:
                case ANTLRParser.ACTION:
                    int startLine = snapshot.findLineNumber(token.getStartIndex());
                    int stopLine = snapshot.findLineNumber(token.getStopIndex());
                    if (startLine >= stopLine) {
                        continue;
                    }

                    String blockHint = null;
                    if (token.getType() == ANTLRParser.DOC_COMMENT) {
                        blockHint = "/** ... */";
                    } else if (token.getType() == ANTLRParser.COMMENT) {
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

    private static FoldInfo createFold(GrammarAST child, String blockHint, DocumentSnapshot snapshot, CommonToken[] tokens) {
        CommonToken startToken = tokens[child.getTokenStartIndex()];
        CommonToken stopToken = tokens[child.getTokenStopIndex()];

        if (startToken.getType() == ANTLRParser.DOC_COMMENT) {
            for (int index = child.getTokenStartIndex(); index <= child.getTokenStopIndex(); index++) {
                startToken = tokens[index];
                if (startToken.getType() != ANTLRParser.DOC_COMMENT && startToken.getChannel() == Token.DEFAULT_CHANNEL) {
                    break;
                }
            }
        }

        int startLine = snapshot.findLineNumber(startToken.getStartIndex());
        int stopLine = snapshot.findLineNumber(stopToken.getStopIndex());
        if (startLine >= stopLine) {
            return null;
        }

        SnapshotPositionRegion region = new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(startToken.getStartIndex(), stopToken.getStopIndex() + 1));
        FoldInfo fold = new FoldInfo(region, blockHint);
        return fold;
    }

}
