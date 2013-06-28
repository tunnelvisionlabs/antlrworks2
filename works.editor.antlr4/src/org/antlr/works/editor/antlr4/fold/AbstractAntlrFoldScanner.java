/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.fold;

import org.antlr.netbeans.editor.fold.AbstractFoldScanner;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 * @param <SemanticData>
 */
public abstract class AbstractAntlrFoldScanner<SemanticData> extends AbstractFoldScanner<SemanticData> {

    @CheckForNull
    protected FoldInfo createFold(@NonNull ParserRuleContext child, @NonNull String blockHint, @NonNull DocumentSnapshot snapshot) {
        Token startToken = getStartToken(child, snapshot);
        if (startToken == null) {
            return null;
        }

        Token stopToken = getStopToken(child, snapshot);
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

    @CheckForNull
    protected Token getStartToken(@NonNull ParserRuleContext child, @NonNull DocumentSnapshot snapshot) {
        return child.getStart();
    }

    @CheckForNull
    protected Token getStopToken(@NonNull ParserRuleContext child, @NonNull DocumentSnapshot snapshot) {
        Token result = child.getStop();
        if (result != null) {
            return result;
        }

        return ParseTrees.getStopSymbol(child);
    }

}
