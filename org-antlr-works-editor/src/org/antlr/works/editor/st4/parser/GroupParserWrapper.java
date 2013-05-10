/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.parser;

import java.util.ArrayList;
import java.util.List;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.works.editor.antlr3.parsing.AntlrSyntaxErrorV3;
import org.netbeans.spi.editor.hints.Severity;
import org.stringtemplate.v4.compiler.GroupParser;

/**
 *
 * @author Sam Harwell
 */
public class GroupParserWrapper extends GroupParser {

    private final List<SyntaxError> syntaxErrors = new ArrayList<>();
    private final DocumentSnapshot snapshot;

    public GroupParserWrapper(TokenStream input, DocumentSnapshot snapshot) {
        super(input);
        this.snapshot = snapshot;
    }

    public List<SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

    @Override
    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
        //String header = getErrorHeader(e);
        String message = getErrorMessage(e, tokenNames);
        syntaxErrors.add(new AntlrSyntaxErrorV3(snapshot, e.token, e, message, Severity.ERROR));

        super.displayRecognitionError(tokenNames, e);
    }
   
}
