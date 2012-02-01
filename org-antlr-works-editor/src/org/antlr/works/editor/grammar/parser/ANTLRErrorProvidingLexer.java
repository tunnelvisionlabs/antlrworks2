/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.parser;

import org.antlr.grammar.v3.ANTLRLexer;
import org.antlr.runtime.CharStream;

public class ANTLRErrorProvidingLexer extends ANTLRLexer {

    private ANTLRErrorProvidingParser parser;

    public ANTLRErrorProvidingLexer(CharStream input) {
        super(input);
    }

    public ANTLRErrorProvidingParser getParser() {
        return parser;
    }

    public void setParser(ANTLRErrorProvidingParser value) {
        parser = value;
    }

}
