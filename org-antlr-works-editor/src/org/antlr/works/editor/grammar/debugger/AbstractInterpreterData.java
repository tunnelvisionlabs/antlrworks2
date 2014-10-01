/*
 *  Copyright (c) 2014 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */

package org.antlr.works.editor.grammar.debugger;

import java.util.List;
import org.antlr.v4.runtime.Vocabulary;

/**
 *
 * @author Sam Harwell
 */
public class AbstractInterpreterData {

    public String grammarFileName;
    public String serializedAtn;
    public Vocabulary vocabulary;
    public List<String> ruleNames;
    public int startRuleIndex;

}
