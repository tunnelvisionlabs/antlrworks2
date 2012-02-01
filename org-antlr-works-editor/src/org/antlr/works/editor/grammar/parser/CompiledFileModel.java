/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.parser;

import java.util.List;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.runtime.CommonToken;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public abstract class CompiledFileModel {

    @NonNull
    private final FileObject fileObject;
    @NullAllowed
    private final CommonToken[] tokens;

    public CompiledFileModel(@NonNull FileObject fileObject, @NullAllowed CommonToken[] tokens) {
        Parameters.notNull("fileObject", fileObject);

        this.fileObject = fileObject;
        this.tokens = tokens;
    }

    public @NonNull FileObject getFileObject() {
        return fileObject;
    }

    public @CheckForNull CommonToken[] getTokens() {
        return tokens;
    }

    @NonNull
    public abstract List<? extends SyntaxError> getSyntaxErrors();

}
