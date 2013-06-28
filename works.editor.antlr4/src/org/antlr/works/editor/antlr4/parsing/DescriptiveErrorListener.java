/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.parsing;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author Sam Harwell
 */
public class DescriptiveErrorListener extends BaseErrorListener {
    public static DescriptiveErrorListener INSTANCE = new DescriptiveErrorListener();

    @Override
    public <T extends Token> void syntaxError(Recognizer<T, ?> recognizer, T offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        String sourceName = recognizer.getInputStream().getSourceName();
        sourceName = !sourceName.isEmpty() ? sourceName+": " : "";
        System.err.println(sourceName+"line "+line+":"+charPositionInLine+" "+msg);
    }

}
