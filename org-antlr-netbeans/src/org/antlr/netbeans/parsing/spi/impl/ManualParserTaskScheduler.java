/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi.impl;

import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=ParserTaskScheduler.class)
public class ManualParserTaskScheduler extends ParserTaskScheduler {

}
