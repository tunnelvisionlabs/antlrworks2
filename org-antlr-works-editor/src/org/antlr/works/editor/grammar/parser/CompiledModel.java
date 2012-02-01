/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.parser;

import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public abstract class CompiledModel {

    @NonNull
    private final DocumentSnapshot snapshot;
    @NonNull
    private final CompiledFileModel result;

    public CompiledModel(@NonNull DocumentSnapshot snapshot, @NonNull CompiledFileModel result) {
        Parameters.notNull("snapshot", snapshot);
        Parameters.notNull("result", result);

        this.snapshot = snapshot;
        this.result = result;
    }

    @NonNull
    public DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    @NonNull
    public CompiledFileModel getResult() {
        return result;
    }

}
