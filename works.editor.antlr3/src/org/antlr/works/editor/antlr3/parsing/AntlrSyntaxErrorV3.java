/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr3.parsing;

import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentSnapshotLine;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.spi.editor.hints.Severity;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class AntlrSyntaxErrorV3 extends SyntaxError {
    private final Token offendingToken;
    private final RecognitionException exception;

    public AntlrSyntaxErrorV3(@NonNull DocumentSnapshot snapshot, Token offendingToken, RecognitionException exception, String message, Severity severity) {
        super(getLocationFromToken(snapshot, offendingToken, exception), message, severity);
        this.offendingToken = offendingToken;
        this.exception = exception;
    }

    private static SnapshotPositionRegion getLocationFromToken(@NonNull DocumentSnapshot snapshot, Token offendingToken, RecognitionException exception) {
        Parameters.notNull("snapshot", snapshot);

        CommonToken token = offendingToken instanceof CommonToken ? (CommonToken)offendingToken : null;

        if (offendingToken != null) {
            int startOffset = token.getStartIndex();
            int endOffset = token.getStopIndex() + 1;
            if (startOffset < 0 || endOffset < startOffset) {
                return new SnapshotPositionRegion(snapshot, 0, 0);
            }

            startOffset = Math.min(startOffset, snapshot.length());
            endOffset = Math.min(endOffset, snapshot.length());
            OffsetRegion offsetRegion = OffsetRegion.fromBounds(startOffset, endOffset);
            return new SnapshotPositionRegion(snapshot, offsetRegion);
        }

        int line = exception != null ? exception.line : -1;
        if (line <= 0) {
            return new SnapshotPositionRegion(snapshot, 0, 0);
        }

        DocumentSnapshotLine snapshotLine = snapshot.findLineFromLineNumber(line);
        return snapshotLine.getRegion();
    }

    public Token getOffendingToken() {
        return offendingToken;
    }

    public RecognitionException getException() {
        return exception;
    }

}
