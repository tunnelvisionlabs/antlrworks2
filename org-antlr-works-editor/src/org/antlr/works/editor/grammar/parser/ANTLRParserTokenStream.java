/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.parser;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.TokenSource;

public class ANTLRParserTokenStream extends CommonTokenStream {

    private ANTLRErrorProvidingParser parser;

    public ANTLRParserTokenStream(TokenSource tokenSource) {
        super(tokenSource);
    }

    public ANTLRErrorProvidingParser getParser() {
        return parser;
    }

    public void setParser(ANTLRErrorProvidingParser value) {
        parser = value;
    }

}
