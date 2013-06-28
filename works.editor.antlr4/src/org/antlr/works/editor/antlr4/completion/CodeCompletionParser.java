/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.completion;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;

/**
 *
 * @author Sam Harwell
 */
public interface CodeCompletionParser {

    AbstractCompletionParserATNSimulator getInterpreter();

    ParserRuleContext getContext();

    TokenStream getInputStream();

    ATN getATN();

}
