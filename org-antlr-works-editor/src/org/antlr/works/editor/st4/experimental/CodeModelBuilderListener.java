/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.st4.experimental;

import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.works.editor.st4.codemodel.FileModel;
import org.antlr.works.editor.st4.experimental.generated.TemplateParserBaseListener;

/**
 *
 * @author Sam Harwell
 */
public class CodeModelBuilderListener extends TemplateParserBaseListener {
    private final DocumentSnapshot snapshot;
    private final TokenStream tokens;

    private FileModel fileModel;

    public CodeModelBuilderListener(DocumentSnapshot snapshot, TokenStream tokens) {
        this.snapshot = snapshot;
        this.tokens = tokens;
    }

    public FileModel getFileModel() {
        return fileModel;
    }
}
