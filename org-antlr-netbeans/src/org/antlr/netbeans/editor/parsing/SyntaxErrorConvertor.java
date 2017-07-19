/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.parsing;

import org.netbeans.modules.parsing.spi.indexing.ErrorsCache;

/**
 *
 * @author Sam Harwell
 */
public class SyntaxErrorConvertor implements ErrorsCache.Convertor<SyntaxError> {
    public static final SyntaxErrorConvertor INSTANCE = new SyntaxErrorConvertor();

    @Override
    public ErrorsCache.ErrorKind getKind(SyntaxError t) {
        switch (t.getSeverity()) {
        case ERROR:
            return ErrorsCache.ErrorKind.ERROR;

        case HINT:
        case VERIFIER:
        case WARNING:
        default:
            return ErrorsCache.ErrorKind.WARNING;
        }
    }

    @Override
    public int getLineNumber(SyntaxError t) {
        return t.getLocation().getStart().getContainingLine().getLineNumber() + 1;
    }

    @Override
    public String getMessage(SyntaxError t) {
        return t.getMessage();
    }

}
