/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.parsing;

import java.util.ArrayList;
import java.util.List;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.spi.editor.hints.Severity;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class SyntaxErrorListener implements ANTLRErrorListener<Token> {
    @NonNull
    private final DocumentSnapshot snapshot;

    @NonNull
    private final List<SyntaxError> syntaxErrors = new ArrayList<>();

    public SyntaxErrorListener(@NonNull DocumentSnapshot snapshot) {
        Parameters.notNull("snapshot", snapshot);
        this.snapshot = snapshot;
    }

    @NonNull
    public DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    @NonNull
    public List<SyntaxError> getSyntaxErrors() {
        return new ArrayList<>(syntaxErrors);
    }

    @Override
    public <T extends Token> void syntaxError(Recognizer<T, ?> recognizer, T offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        syntaxErrors.add(new AntlrSyntaxErrorV4(snapshot, offendingSymbol, e, msg, Severity.ERROR));
    }

}
