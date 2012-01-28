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
