/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi;

import org.antlr.netbeans.editor.text.VersionedDocument;

/**
 *
 * @author Sam Harwell
 */
public abstract class SingletonParserTaskProvider implements ParserTaskProvider {

    private final Object lock = new Object();
    private ParserTask parserTaskInstance;

    @Override
    public final ParserTask createTask(VersionedDocument document) {
        synchronized (lock) {
            if (parserTaskInstance == null) {
                parserTaskInstance = createTaskImpl();
            }

            return parserTaskInstance;
        }
    }

    protected abstract ParserTask createTaskImpl();

}
