/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.completion;

import java.util.Map;
import org.antlr.v4.runtime.RuleContext;

/**
 *
 * @author Sam Harwell
 * @param <TParser>
 */
public interface ForestParser<TParser> {

    public Map<RuleContext, CaretReachedException> getParseTrees(TParser parser);

}
