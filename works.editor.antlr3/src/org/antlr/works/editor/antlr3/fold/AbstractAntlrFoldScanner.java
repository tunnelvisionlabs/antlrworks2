/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr3.fold;

import org.antlr.netbeans.editor.fold.AbstractFoldScanner;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;

/**
 *
 * @author Sam Harwell
 * @param <SemanticData>
 */
public abstract class AbstractAntlrFoldScanner<SemanticData> extends AbstractFoldScanner<SemanticData> {

    protected FoldInfo createFold(CommonTree child, String blockHint, DocumentSnapshot snapshot, CommonToken[] tokens) {
        CommonToken startToken = getStartToken(child, snapshot, tokens);
        if (startToken == null) {
            return null;
        }

        CommonToken stopToken = getStopToken(child, snapshot, tokens);
        if (stopToken == null) {
            return null;
        }

        int startLine = snapshot.findLineNumber(startToken.getStartIndex());
        int stopLine = snapshot.findLineNumber(stopToken.getStopIndex());
        if (startLine >= stopLine) {
            return null;
        }

        if (stopToken.getStopIndex() + 1 > snapshot.length()) {
            return null;
        }

        SnapshotPositionRegion region = new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(startToken.getStartIndex(), stopToken.getStopIndex() + 1));
        FoldInfo fold = new FoldInfo(region, blockHint);
        return fold;
    }

    protected CommonToken getStartToken(CommonTree child, DocumentSnapshot snapshot, CommonToken[] tokens) {
        return tokens[child.getTokenStartIndex()];
    }

    protected CommonToken getStopToken(CommonTree child, DocumentSnapshot snapshot, CommonToken[] tokens) {
        return tokens[child.getTokenStopIndex()];
    }

}
