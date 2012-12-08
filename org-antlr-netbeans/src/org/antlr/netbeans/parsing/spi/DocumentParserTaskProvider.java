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
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public abstract class DocumentParserTaskProvider implements ParserTaskProvider {
    private final Object lock = new Object();

    @Override
    public final ParserTask createTask(VersionedDocument document) {
        Parameters.notNull("document", document);

        synchronized (lock) {
            ParserTask result = (ParserTask)document.getProperty(getClass());
            if (result == null) {
                result = createTaskImpl(document);
                document.putProperty(getClass(), result);
            }

            return result;
        }
    }

    protected abstract ParserTask createTaskImpl(VersionedDocument document);

}
