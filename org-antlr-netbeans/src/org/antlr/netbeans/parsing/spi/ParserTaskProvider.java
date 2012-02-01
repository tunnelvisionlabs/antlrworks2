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
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public interface ParserTaskProvider {

    @NonNull ParserTaskDefinition getDefinition();

    ParserTask createTask(@NonNull VersionedDocument document);

}
